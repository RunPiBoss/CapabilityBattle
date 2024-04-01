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
