package com.thec.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thec.entity.Comment;
import com.thec.entity.Result;
import com.thec.service.CommentService;
import com.thec.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @version 2024/6/3 16:30
 * @autor luoxiaopi 1794586882163@qq.com
 * @since JDK8
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 新增或者更新
    @PostMapping("/add")
    public Result save(@RequestBody Comment comment, @RequestHeader String token) {
        System.out.println("tokne="+token);
        if (comment.getId() == null) { // 新增评论
            try {
                System.out.println("try执行了");
                String subject = JwtUtil.parseJWT(token.replace("Bearer ", "")).getSubject();
                System.out.println("subject="+subject);
//                JSONObject userJson = JSON.parseObject(subject);
//                System.out.println("userJson="+userJson);
//                Integer userId = userJson.getInteger("id");
//                System.out.println("userId="+userId);
                // 这里使用Integer数据太小了，无法转换就会id错误
                Long userId = Long.parseLong(subject);
                System.out.println("subject="+subject);
                comment.setUserId(userId);
                comment.setTime(DateUtil.now());
                if (comment.getPid() != null) {  // 判断如果是回复，进行处理
                    Integer pid = comment.getPid();
                    Comment pComment = commentService.getById(pid);
                    if (pComment.getOriginId() != null) {  // 如果当前回复的父级有祖宗，那么就设置相同的祖宗
                        comment.setOriginId(pComment.getOriginId());
                    } else {  // 否则就设置父级为当前回复的祖宗
                        comment.setOriginId(comment.getPid());
                    }
                }
            } catch (Exception e) {
                // 处理异常
                System.out.println(e);
//                return new Result<>(400, "无效的Token");
            }
        }
        commentService.saveOrUpdate(comment); //mybatis的IService类
        stringRedisTemplate.opsForValue().set("comment_" + comment.getId(), JSON.toJSONString(comment));
        System.out.println("save方法执行了");
        return new Result<>(200, "添加成功",comment);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commentService.removeById(id);
        // Remove the comment from Redis cache
        stringRedisTemplate.delete("comment_" + id);
//        return Result.success();
        return new Result(200,"该评论删除成功");
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.removeByIds(ids);
        // Remove the comments from Redis cache
        for (Integer id : ids) {
            stringRedisTemplate.delete("comment_" + id);
        }
//        return Result.success();
        return null;
    }

    //    查询所有的数据
    @GetMapping
    public Result findAll() {
        System.out.println("findAll方法执行了");
        // 尝试从Redis缓存中获取数据
        String jsonStr = stringRedisTemplate.opsForValue().get("comment_all");
        if (StrUtil.isBlank(jsonStr)) {
            // 如果缓存中没有数据，从数据库中获取数据
            List<Comment> comments = commentService.findCommentDetail();
            // 将数据同步到Redis缓存中
            stringRedisTemplate.opsForValue().set("comment_all", JSONUtil.toJsonStr(comments));
            return new Result<>(200,"评论加载成功",comments);
//            return Result.success(comments);
        } else {
            // 如果缓存中有数据，直接返回缓存中的数据
            List<Comment> cachedComments = JSONUtil.toList(JSONUtil.parseArray(jsonStr), Comment.class);
//            return Result.success(cachedComments);
            return new Result<>(200,"评论加载成功",cachedComments);
        }
//        return new Result<>(400,"评论加载失败")
    }

    @GetMapping("/tree")
    public Result findTree() {
        List<Comment> articleComments = commentService.findCommentDetail();  // 查询所有的评论和回复数据
        // 查询评论数据（不包括回复）,过滤出没有originId（表示没有父评论）的评论，存储在originList中
//        System.out.println(articleComments);
        List<Comment> originList = articleComments.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());
//        System.out.println("originList="+originList);
        // 设置评论数据的子节点，也就是回复内容
        for (Comment origin : originList) {
            // comments里面是二级回复内容
            List<Comment> comments = articleComments.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());  // 表示回复对象集合
//            System.out.println("comments="+comments);
            comments.forEach(comment -> {
                // articleComments中每一个的id=二级回复的pid的那一项
                Optional<Comment> pComment = articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst();  // 找到当前评论的父级
//                System.out.println("pComment="+pComment);
                pComment.ifPresent((v -> {  // 找到父级评论的用户id和用户昵称，并设置给当前的回复对象
                    comment.setPUserId(v.getUserId());
                    comment.setPNickname(v.getNickname());
                }));
            });
            origin.setChildren(comments);
        }

        // Update or insert the comments into Redis cache
        for (Comment comment : originList) {
            stringRedisTemplate.opsForValue().set("comment_" + comment.getId(), String.valueOf(comment));
            if (comment.getChildren() != null) {
                for (Comment reply : comment.getChildren()) {
                    stringRedisTemplate.opsForValue().set("comment_" + reply.getId(), String.valueOf(reply));
                }
            }
        }
        return new Result(200,"查询评论树成功",originList);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);

        // Update or insert the comment into Redis cache
        stringRedisTemplate.opsForValue().set("comment_" + comment.getId(), String.valueOf(comment));
//        return Result.success(commentService.getById(id));
        return null;
    }

    //    分页查询的结果数据
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
//        return Result.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
        return null;
    }

}



