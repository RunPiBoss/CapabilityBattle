import axios from "axios";
import { getToken } from "./auth";
import { useRouter } from "vue-router";
import { removeToken } from "../utils/auth";
import { ElNotification } from "element-plus";
const router = useRouter();
const service = axios.create({
  baseURL: "api",
});

service.interceptors.request.use((config) => {
  config.headers["token"] = getToken() || "";
  return config;
});

<<<<<<< HEAD
/*
处理请求后响应的数据，如果code为 200，表示请求成功，直接返回响应数据data。
如果code为 401，表示未授权，会弹出一个错误提示框（ElNotification）显示消息msg，然后执行一些其他操作，比如调用removeToken()移除用户令牌并重定向到登录页。
对于其他code值，也会弹出错误提示框显示消息msg。
*/
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
service.interceptors.response.use(
  (res) => {
    let { code, data, msg } = res.data;
    if (code == 200) {
      return data;
    } else if (code == 401) {
      ElNotification({
        title: "提示信息",
        message: msg,
        type: "error",
      });
      removeToken();
      router.push({
        path: "/login",
      });
    } else {
      ElNotification({
        title: "提示信息",
        message: msg,
        type: "error",
      });
    }
  },
  (err) => {
    return Promise.reject(err);
  }
);

export default service;
