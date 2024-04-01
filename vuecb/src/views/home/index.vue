<template>
    <div class="common-layout">
        <el-container class="layout-one flex flex-col">
            <el-header class="flex flex-row space-x-2 bg-slate-50">
                <div class="w-1/3 border-b-2">
                    <el-menu mode="horizontal" class="bg-slate-50" style="height: 100%;" text-color="black">
                        <el-menu-item>
                            <span class="text-2xl font-mono">Cpability Battle</span>
                        </el-menu-item>
                    </el-menu>
                </div>
                <div class="w-4/6">
                    <NavMenu />
                </div>
                <div class="w-2/5 pt-1 flex items-center justify-start space-x-8">
                    <div class="block">
                        <el-avatar shape="square" :size="50" :src="squareUrl" />
                    </div>
                    <span class="flex items-center space-x-1">
                        <el-icon>
                            <Avatar />
                        </el-icon>
                        <el-dropdown>
                            {{ user.nickName }}
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </span>
                </div>
            </el-header>
            <el-main class="lg:pl-28 xl:pl-40 2xl:pl-44 pr-30">
                <router-view />
            </el-main>
        </el-container>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import { getUser, removeUser } from "../../utils/user";
import { removeToken } from "../../utils/auth";
import { logout } from '../../api/index'
import { useRouter } from "vue-router";
import NavMenu from './navMenu.vue';
import { ElNotification } from 'element-plus'

const user = reactive(getUser())
const squareUrl = "api/images/" + user.avatar + ".jpg"
const router = useRouter()

const handleLogout = async () => {
    await logout()
    removeToken()
    removeUser()
    ElNotification({
        title: '提示信息',
        message: '注销成功',
        type: 'success'
    })
    router.push("/login")
}
</script>

<style lang="less" scoped>
.common-layout {
    height: 100%;
}

.layout-one {
    height: 100%;
}

:deep(.el-header) {
    padding: 0;
}
</style>