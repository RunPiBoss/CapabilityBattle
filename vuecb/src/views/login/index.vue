<template>
    <div id="login">
        <div id="contain">
            <div id="left_card">
                <h1 class="text-xl mb-2">欢迎使用代码对战平台</h1>
                <h1 class="text-2xl uppercase">Welcome to Capability Battle</h1>
            </div>
            <div id="right_card">
                <el-card class="el-card">
                    <h2 class="text-xl">欢迎登录</h2>
                    <el-form class="login" :model="temp" ref="ruleFormRef" :rules="rules">
                        <el-form-item prop="userName" class="w-96 ml-8">
                            <input v-shake type="text" v-model="temp.userName" placeholder="请输入账号" />
                        </el-form-item>
                        <el-form-item prop="password" class="w-96 ml-8">
                            <input v-shake type="password" v-model="temp.password" placeholder="请输入密码" />
                        </el-form-item>
                    </el-form>
                    <div class="message">
                        <span v-html="error"></span>
                    </div>
                    <div id="btn">
                        <button class="loginbtn" @click="handleLogin(ruleFormRef)">登陆</button>
                      <div class="others">没有账号？<span class="others_word cursor-pointer text-secondary hover:text-blue-50" @click="$router.push('/register')">立即注册</span></div>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { login } from "../../api";
import { setToken } from "../../utils/auth";
import { getUserInfo } from '../../api';
import { useRouter } from "vue-router";
import { setUser } from "../../utils/user"
import { ElNotification } from 'element-plus';
const rules = reactive({
    userName: [
        { required: true, message: 'Please input Activity userName', trigger: 'blur' },
        { min: 2, max: 5, message: 'Length should be 2 to 5', trigger: 'blur' },
    ],
    password: [
        { required: true, message: 'Please input Activity password', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],
})
const temp = reactive({
    userName: "zs",
    password: "123"
})

const router = useRouter()
const ruleFormRef = ref();
const handleLogin = (formEl) => {
    formEl.validate(async valid => {
        if (valid) {
            let res = await login(temp)
            if (res.token != null) {
                setToken(res.token)
                let user = await getUserInfo()
                setUser(user)
                ElNotification({
                    title: '提示信息',
                    message: '登陆成功',
                    type: 'success'
                })
                router.push("/index")
            }
        } else {
            console.log('error submit!')
        }
    })
}
</script>

<style lang="less" scoped>
@keyframes animate {
    0% {
        filter: hue-rotate(0deg);
    }

    100% {
        filter: hue-rotate(360deg);
    }
}

#login {
    position: relative;
    width: 100vw;
    height: 100vh;
    background-image: url(../../assets/images/vuecb.jpg);
    background-size: 100% 100%;
    background-color: #a7a8bd;

    #contain {
        height: 400px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        border-radius: 25px;
        border: 1px solid black;
        background-color: rgba(255, 255, 255, 0.1) !important;
        backdrop-filter: blur(5px);
        box-shadow: -5px -5px 10px rgb(39, 65, 65),
            5px 5px 20px aqua;
        /* 5秒 infinite循环播放无限次 linear匀速  */
        animation: animate 5s linear infinite;
    }
}

#contain {
    display: flex;
    flex-direction: row;
    text-align: center;
    align-items: center;

    #left_card {
        width: 500px;

        h1 {
            color: white;
            white-space: nowrap;
        }

        span {
            font-size: 1.2rem;
            text-align: center;
            color: white;
            white-space: nowrap;
        }
    }

    #right_card {
        width: 500px;

        .el-card {
            margin: 0 45px;
            border-radius: 25px;
            background-color: rgba(255, 255, 255, 0.1);
        }
    }
}

#right_card {
    display: flex;
    justify-content: center;
    align-items: center;

    h2 {
        margin-bottom: 5px;
    }

    .login {
        input {
            width: 80%;
            height: 45px;
            margin-top: 10px;
            border: 1px solid white;
            background-color: rgba(255, 255, 255, 0.5);
            border-radius: 10px;
            font-size: inherit;
            padding-left: 20px;
            outline: none;
        }
    }

    .remember {
        float: right;
        height: 26px;
        text-align: center;
        font-size: 1rem;
        position: relative;

        .radio {
            height: 1rem;
            width: 1rem;
            vertical-align: middle;
            margin-top: -2px;
            opacity: 0;
        }

        label {
            position: absolute;
            left: -2px;
            top: 5px;
            height: 1rem;
            width: 1rem;
            vertical-align: middle;
            margin-top: -2px;
            border-radius: 50%;
            border: 1px solid black;
        }

        //radio选中后修改labe内的内容 :after 选择器在被选元素的内容后面插入内容。
        input:checked+label::after {
            content: "";
            width: 0.6rem;
            height: 0.6rem;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            border-radius: 50%;
            background-color: rgba(207, 38, 38, 0.8);
            border: 1px solid rgba(207, 38, 38, 0.8);
        }
    }

    .message {
        margin-top: 30px;
        font-size: 1.2rem;
        color: red;
    }

    .loginbtn {
        width: 84%;
        height: 40px;
        margin-left: 4px;
        border-radius: 10px;
        background-color: rgba(207, 38, 38, 0.8);
    }

    .others{
        //width: 50%;
        margin-top: 2%;

    }

    .others_word{
      font-weight: bold;

    }
}
</style>