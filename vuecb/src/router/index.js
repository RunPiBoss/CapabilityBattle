import { createRouter, createWebHistory } from "vue-router";
import { getNavData } from "../api";
import { mainStore } from "../store";
import { getToken } from "../utils/auth";
const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/login/index.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/register/index.vue"),
  },
  {
    path: "/home",
    name: "home",
    component: () => import("../views/home/index.vue"),
    children: [
      {
        path: "/ladder_history",
        name: "ladder_history",
        component: () => import("../components/ladder_history/index.vue"),
      },
      {
        path: "/history",
        name: "history",
        component: () => import("../views/home/content/history/index.vue"),
      },
    ]
  }
];
const router = new createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach(async (to, from, next) => {
  if (to.path === "/login" || to.path === "/register") {
    next();
  } else {
    if (getToken() == null) {
      alert("您当前尚未登录，请在登录后进行操作");
      router.push({
        path: "/login",
      });
    } else {
      if (mainStore() && mainStore().nav.length == 0) {
        let data = await getNavData();
        mainStore().setNav(data);
        const navData = fn(data);
        router.addRoute(navData);
        next({ path: to.path });
      } else {
        next();
      }
    }
  }
});

function fn(data) {
  let homeRoutes = routes.filter((v) => v.path == "/home")[0];
  homeRoutes.children = [];
  data.forEach((item) => {
    homeRoutes.children.push({
      path: item.path,
      name: item.name,
      component: () =>
        import(`../views/home/content/${item.component}/index.vue`),
    });
  });
  return homeRoutes;
}
export default router;
