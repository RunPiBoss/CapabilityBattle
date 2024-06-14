package com.thec.socket;

import com.thec.config.RedisCache;
import com.thec.entity.User;
import com.thec.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 定义websocket服务器端，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址
 *
 * @author thec
 */
@Component
@ServerEndpoint("/ws/{token}")
@Slf4j
public class WebSocket {

    /**
     * 实例一个session，这个session是websocket的session
     */
    private Session session;

    private Integer userId;

    private static final String ROOM_KEY_PREFIX = "room:";
    /**
     * 存放websocket的集合
     */
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();


    private static ConcurrentHashMap<Integer, WebSocket> concurrentHashMap = new ConcurrentHashMap<>();

    private static RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        WebSocket.redisCache = redisCache;
    }

    /**
     * 前端请求时一个websocket时
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) {
        this.session = session;
        String id = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                session.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (id != "" && id != null) {
            userId = Integer.valueOf(id);
            webSocketSet.add(this);
            concurrentHashMap.put(Integer.valueOf(id), this);
            log.info("【websocket消息】有新的连接, 总数:{}", webSocketSet.size());
        }
    }

    /**
     * 前端关闭时一个websocket时
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】 用户id:" + userId + "连接断开, 总数:{}", webSocketSet.size());
    }

    /**
     * 前端向后端发送消息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        String[] strings = message.split(":");
        if ("close".equals(strings[0])) {
            String roomKey = ROOM_KEY_PREFIX + strings[1];

            Integer playerTwo = null;
            Integer playerOne = null;
            if (redisCache.existsKey(roomKey)) {
                playerOne = Integer.valueOf(redisCache.getCacheMapValue(roomKey, "playerOne"));
                if (redisCache.getCacheMap(roomKey).containsKey("playerTwo")) {
                    playerTwo = Integer.valueOf(redisCache.getCacheMapValue(roomKey, "playerTwo"));
                    if (playerTwo.equals(userId)) {
                        sendMessageById("用户退出房间", playerOne);
                        redisCache.delCacheMapValue(roomKey, "playerTwo");
                        redisCache.setCacheMapValue(roomKey, "prepare", "0");
                        redisCache.delCacheMapValue(roomKey, "titleSet");
                    } else {
                        sendMessageById("房主解散了房间", playerTwo);
                        redisCache.deleteObject(roomKey);
                    }
                } else {
                    sendMessageById("退出成功", userId);
                    redisCache.deleteObject(roomKey);
                }
            }
        }
    }

    /**
     * 新增一个方法用于主动向客户端发送消息
     *
     * @param message
     */
    public static void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息, message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageById(String message, Integer id) {
        for (Map.Entry<Integer, WebSocket> entry : concurrentHashMap.entrySet()) {
            try {
                if (entry.getKey().equals(id)) {
                    entry.getValue().session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageToOther(String message, Map<String, User> map) {
        for (String id : map.keySet()) {
            if (!id.equals("@type")) {
                sendMessageById(message, Integer.parseInt(id));
            }
        }
    }
}