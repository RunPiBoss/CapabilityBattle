<template>
    <div class="flex flex-col">
        <div class="h-2">
            好友对战模式
        </div>
        <div class="flex h-10">
        </div>
        <div class="flex justify-start space-x-24">
            <div class="w-96">
                <el-card>
                    <el-button size="large" v-if="!isReadyOne" type="warning" class="ml-32" disabled>尚未准备</el-button>
                    <el-button size="large" v-if="isReadyOne" type="success" class="ml-32" disabled>准备就绪</el-button>
                    <div class="ml-20 mt-4 block">
                        <el-avatar :size="200" :src="'api/images/' + user.avatar + '.jpg'" />
                    </div>
                    <el-button size="large" type="info" disabled class="ml-32 mt-4">姓名：{{ user.nickName }}</el-button>
                    <el-divider />
                    <div>
                        <el-button size="large" type="info" disabled class="ml-28 mt-8 w-32">天梯分数：<h1>{{ user.rating }}</h1>
                        </el-button>
                    </div>
                    <div>
                        <el-button size="large" type="info" disabled class="ml-28 mt-8 w-32">胜率:<h1>100%</h1>
                        </el-button>
                    </div>
                </el-card>
            </div>
            <div class="flex flex-col w-1/6 h-1/3">
                <el-button type="primary" disabled class="w-48">

                    <h1> 房间号：</h1>
                    <h1>{{ showRoomId() }}</h1>
                </el-button>
                <el-image class="mt-40" :src="vs" fit="contain"></el-image>
                <div class="flex flex-col space-y-4">
                    <el-button size="large" @click="handlePrepare(0)" type="warning" v-if="showCancleFlag" round
                        class="mt-24 bg-orange-100">
                        <h1 class="text-orange-400">取消准备</h1>
                    </el-button>
                    <el-button size="large" @click="handlePrepare(1)" type="warning" v-if="showPrepareFlag" round
                        class="mt-16 bg-orange-100">
                        <h1 class="text-orange-400">准备开始</h1>
                    </el-button>
                    <el-button size="large" type="warning" v-if="showCreateFlag" round class="mt-28 bg-orange-100"
                        @click="handleCreateRoom()">
                        <h1 class="text-orange-400">创建房间</h1>
                    </el-button>
                    <el-button size="large" v-if="!isReadyAll" type="warning" class="mt-24 bg-orange-100" round
                        @click="dialogVisible = true">
                        <h1 class="text-orange-400">加入房间</h1>
                    </el-button>
                    <el-button size="large" @click="handleStart()" v-if="showStartFlag" type="danger"
                        class="mt-28 bg-red-100" round>
                        <h1 style="color:red;">开始对战</h1>
                    </el-button>
                    <el-button size="large" v-if="showQuitFlag" type="primary" class="mt-16 bg-blue-100"
                        @click="handleQuit()" round>
                        <h1 class="text-sm text-blue-600">{{ showQuitText() }}房间</h1>
                    </el-button>
                </div>
            </div>
            <div class="w-96">
                <el-card>
                    <el-button size="large" v-if="!isReadyTwo" type="danger" class="ml-32" disabled>尚未准备</el-button>
                    <el-button size="large" v-if="isReadyTwo" type="success" class="ml-32" disabled>准备就绪</el-button>
                    <div class="ml-20 mt-4 block">
                        <el-avatar :size="200" :src="'api/images/' + friendData.friend.avatar + '.jpg'" />
                    </div>
                    <el-button size="large" type="info" disabled class="ml-32 mt-4">姓名：{{ friendData.friend.nickName
                    }}</el-button>
                    <el-divider />
                    <div>
                        <el-button size="large" type="info" disabled class="ml-28 mt-8 w-32">天梯分数：<h1>{{
                            friendData.friend.rating }}
                            </h1>
                        </el-button>
                    </div>
                    <div>
                        <el-button size="large" type="info" disabled class="ml-28 mt-8 w-32">胜率:<h1>100%</h1>
                        </el-button>
                    </div>
                </el-card>
            </div>
        </div>
    </div>
    <el-dialog v-model="dialogVisible" title="房间号" width="30%">
        <el-input v-model="roomNum" autocomplete="off" placeholder="请输入房间号" />
        <template #footer>
            <div class="">
                <el-button @click="dialogVisible = false" class="bg-gray-50" style="color:black;">取消</el-button>
                <el-button type="primary" class=" bg-blue-50" @click="handleJoin()" style="color:black;">
                    确认
                </el-button>
            </div>
        </template>
    </el-dialog>

    <el-dialog :before-close="handleCloseCode" v-model="diabattlelogVisible" :title="'编写代码：                   '
        + '\'' + titleData.data.name + '\''" width="90%">
        <div class="flex flex-row space-x-3">
            <el-form label-position="top" label-width="100px" disabled :model="titleData" class="w-1/2 overflow-y-scroll"
                style="max-height: 527px;">
                <el-form-item label="题目描述">
                    <el-input v-model="titleData.data.titleDescribe" disabled :rows="6" style="width: 100%"
                        type="textarea" />
                </el-form-item>
                <el-form-item label="输入格式">
                    <el-input v-model="titleData.data.inputFormat" disabled :rows="2" style="width: 100%" type="textarea" />
                </el-form-item>
                <el-form-item label="输出格式">
                    <el-input v-model="titleData.data.outputFormat" :rows="2" style="width: 100%" type="textarea" />
                </el-form-item>
                <div class="flex flex-row space-x-2">
                    <el-form-item label="样例输入" class="w-1/2">
                        <el-input v-model="titleData.data.sampleInput" readonly :rows="2" style="width: 100%"
                            type="textarea" />
                    </el-form-item>
                    <el-form-item label="样例输出" class="w-1/2">
                        <el-input v-model="titleData.data.sampleOutput" readonly :rows="2" style="width: 100%"
                            type="textarea" />
                    </el-form-item>
                </div>
                <el-form-item label="限制">
                    <div class="flex flex-row space-x-3">
                        <el-input v-model="titleData.data.timeLimit">
                            <template #prepend>时间(ms)</template>
                        </el-input>
                        <el-input v-model="titleData.data.memoryLimit">
                            <template #prepend>内存(MB)</template>
                        </el-input>
                        <el-input v-model="titleData.data.lengthLimit">
                            <template #prepend>代码长度(KB)</template>
                        </el-input>
                    </div>
                </el-form-item>
            </el-form>
            <codeMirror ref="codeValue" />
        </div>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="handleSubmit" style="color:black;">
                    提交代码
                </el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="submitCodeResult" title="提交结果" width="35%" :before-close="handleClose">
        <el-table :data="tableData" :row-class-name="tableRowClassName">
            <el-table-column align="center" type="index" min-width="250" label="测试点" />
            <el-table-column align="left" prop="result" label="测试结果" min-width="300" />
        </el-table>
        <template #footer>
            <span class="dialog-footer">
                <el-button class=" bg-blue-400" type="primary" @click="handleCloseSubmit()">
                    确定
                </el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog destroy-on-close v-model="showSettlement" title="对战结算" width="35%">
        <settlement />
    </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import vs from "../../../../assets/images/vs.png"
import { onBeforeUnmount } from "vue";
import { getToken } from "../../../../utils/auth";
import {
    join, getUserInfoById,
    createRoom, prepare, startCompetition,
    quit
} from '../../../../api'
import { ElNotification, ElMessageBox } from 'element-plus';
import { getUser, setUser } from '../../../../utils/user';
import { submitCodeApi, getHistoryOne, getUserInfo } from '../../../../api';
import { mainStore } from "../../../../store";
import codeMirror from "../../../../components/code/codeMirror.vue";
import settlement from "../../../../components/settlement/index.vue"
//对战结算
let winStatus = ref(false)
let showSettlement = ref(false);
const handleCompetitionFinish = () => {
    mainStore().setIsWin(winStatus.value)
    showSettlement.value = true
    competitionStatus.value = 0
    handleUpdateUserInfo()
}
//封装房间号以及代码和代码类型
let submitCode = reactive({
    roomId: "",
    code: "",
    codeType: ""
})
const tableRowClassName = ({ row, rowIndex }) => {
    let msg = JSON.parse(JSON.stringify(row)).result
    if (msg === '编译错误') {
        return 'warning-row'
    } else if (msg === '答案错误') {
        return 'danger-row'
    } else if (msg === '答案正确') {
        return 'success-row'
    }
    return ''
}

//code编辑框传来的代码
const codeValue = ref()
const submitCodeResult = ref(false)

//比赛状态
let competitionStatus = ref(0)
//关闭测试结果窗口
const handleCloseSubmit = () => {
    //比赛未结束
    if (competitionStatus.value === 0) {
        submitCodeResult.value = false
    } //比赛结束
    else {
        submitCodeResult.value = false
        diabattlelogVisible.value = false
        handleCompetitionFinish()
        showSettlement.value = true
    }
}
let tableData = reactive([])
//提交代码
const handleSubmit = async () => {
    tableData = []
    submitCode.roomId = status.value == 0 ? roomId.value
        : roomNum.value
    submitCode.code = codeValue.value.code
    submitCode.codeType = codeValue.value.codeType
    let res = await submitCodeApi(submitCode)
    let data = {
        result: ''
    }
    res.title.result.forEach(item => {
        data.result = item
        tableData.push(data)
    })
    if (res.title.success == false) {
        submitCodeResult.value = true
    } else {
        //获取当前对局记录
        handleGetHistoryOne(res.time)
        winStatus.value = true
        submitCodeResult.value = true
        competitionStatus.value = 1 //比赛结束标志
        handleUpdateUserInfo()
        resetCode()
    }
}
const handleGetHistoryOne = async (time) => {
    let historyOneData = {
        playerOne: user.id,
        playerTwo: friendData.friend.id,
        time
    }
    let resData = await getHistoryOne(historyOneData)
    mainStore().setCompetitionData(resData)
}
const handleCloseCode = () => {
    ElMessageBox.confirm('比赛期间不能退出比赛')
        .then(() => {
        })
        .catch(() => {
        })
    codeValue.value.code = ""
}
const resetCode = () => {
    if (status.value == 1) {
        isReadyOne.value = false
        showCancleFlag.value = false
        showPrepareFlag.value = true
    } else if (status.value == 0) {
        isReadyTwo.value = false
        showStartFlag.value = false
    }
    isReadyAll.value = false
    showQuitFlag.value = true
    codeValue.value.code = ""
}

//主人是否准备
let isReadyOne = ref(true)
//客人是否准备
let isReadyTwo = ref(false)
//所有人是否准备
let isReadyAll = ref(false)
let dialogVisible = ref(false)
let diabattlelogVisible = ref(false)
const roomId = ref()//当前创建的房间号
const roomNum = ref()//要加入的房间号
//0代表房间主人，1代表客人
let status = ref(0);
//好友信息
let friendData = reactive({
    friend: {}
})
let user = reactive(getUser())
let showStartFlag = ref(false)
let titleData = reactive({
    data: {}
})
const handleStart = async () => {
    let res = await startCompetition(roomId.value)
    if (res != null) {
        diabattlelogVisible.value = true
        titleData.data = res
    }
}
let showCancleFlag = ref(false)
let showPrepareFlag = ref(false)
const handlePrepare = async (flag) => {
    let res = await prepare(roomNum.value)
    if (flag === 1) {
        ElNotification.success({
            title: '提示信息',
            message: "准备成功"
        })
        isReadyAll.value = true
        showCancleFlag.value = true
        showPrepareFlag.value = false
        showQuitFlag.value = false
        if (status.value == 0) {
            isReadyTwo.value = true;
        } else {
            isReadyOne.value = true;
        }
    } else {
        ElNotification.success({
            title: '提示信息',
            message: "取消准备成功"
        })
        isReadyAll.value = false
        showCancleFlag.value = false
        showPrepareFlag.value = true
        showQuitFlag.value = true
        if (status.value == 0) {
            isReadyTwo.value = false;
        } else {
            isReadyOne.value = false;
        }
    }

}
const resetFlags = () => {
    friendData.friend = {}
    showQuitFlag.value = false
    diabattlelogVisible.value = false
    dialogVisible.value = false
    if (status.value == 0) {
        isReadyTwo.value = false
        isReadyAll.value = false
        showStartFlag.value = false
        showCreateFlag.value = true
        roomId.value = undefined
    } else {
        isReadyOne.value = true
        isReadyTwo.value = false
        isReadyAll.value = false
        showCancleFlag.value = false
        showCreateFlag.value = true
        showPrepareFlag.value = false
        status.value = 0
        roomNum.value = undefined
    }
}
let showQuitFlag = ref(false)
const showQuitText = () => {
    return status.value == 0 ? "解散" : "退出"
}
const handleQuit = async () => {
    let id = status.value == 0 ? roomId : roomNum
    let res = await quit(id.value)
    if (res === "退出成功" || res === '解散成功') {
        if (status.value == 0) {
            ElNotification.success({
                title: '提示信息',
                message: "房间解散成功"
            })
        } else if (status.value == 1) {
            ElNotification.success({
                title: '提示信息',
                message: "退出房间成功"
            })
        }
    } else {
        ElNotification.error({
            title: '提示信息',
            message: res
        })
    }
    resetFlags()
}
const handleJoin = async () => {
    dialogVisible.value = false
    let id = await join(roomNum.value)
    if (roomId.value >= 0) {
        let res = await quit(roomId.value)
        ElNotification({
            title: '提示信息',
            message: '解散房间成功',
            type: 'success'
        })
        roomId.value = undefined
    }
    if (id != undefined) {
        ElNotification({
            title: '提示信息',
            message: '加入房间成功',
            type: 'success'
        })
        roomId.value = undefined
        status.value = 1;
        showStartFlag.valid = false
        showCreateFlag.value = false
        showPrepareFlag.value = true
        showQuitFlag.value = true
        isReadyOne.value = false
        isReadyTwo.value = true
        handleGetUserInfo(id)
    }
}
const showRoomId = () => {
    if (status.value == 0) {
        return roomId.value
    } else {
        return roomNum.value
    }
}
const handleGetUserInfo = async (id) => {
    friendData.friend = await getUserInfoById(id)
}
let showCreateFlag = ref(true)
const handleCreateRoom = async () => {
    roomId.value = await createRoom()
    status.value = 0;
    ElNotification({
        title: "提示信息",
        message: `创建成功，房间号为：${roomId.value}`,
        type: 'success'
    })
    showCreateFlag.value = false
    showQuitFlag.value = true
}
const token = getToken();
var ws = new WebSocket(`ws://59.110.28.248:8080/capabilityBattle/ws/${token}`);
ws.onopen = function (evt) {
    console.log("Connection open ...");
};

ws.onmessage = function (evt) {
    console.log("Received Message: " + evt.data);
    if (evt.data.indexOf('>') != -1) {
        let data = evt.data.split('>')
        if (data[1] !== "") {
            ElNotification({
                title: '提示信息',
                message: "有人进入房间",
                type: 'success'
            })
            showCreateFlag.value = false
            friendData.friend = handleGetUserInfo(data[1])
        }
    } else if (evt.data === 'ready') {
        ElNotification.success({
            title: "提示信息",
            message: '对方已经准备就绪',
        })
        isReadyAll.value = true;
        showStartFlag.value = true
        if (status.value == 0) {
            isReadyTwo.value = true;
        } else {
            isReadyOne.value = true;
        }
    } else if (evt.data === 'noReady') {
        ElNotification.success({
            title: "提示信息",
            message: '对方取消准备',
        })
        isReadyAll.value = false;
        showStartFlag.value = false
        if (status.value == 0) {
            isReadyTwo.value = false;
        } else {
            isReadyOne.value = false;
        }
    } else if (evt.data === "用户退出房间") {
        ElNotification.warning({
            title: "提示信息",
            message: '对方退出房间',
        })
        friendData.friend = {}
        showCreateFlag.value = false
        isReadyTwo.value = false
        isReadyAll.value = false
        showStartFlag.value = false
        showQuitFlag.value = true
        diabattlelogVisible.value = false
    } else if (evt.data === "房主解散了房间") {
        ElNotification.warning({
            title: "提示信息",
            message: '房主解散了房间',
        })
        resetFlags()
    } else if (evt.data.indexOf('·') != -1) {
        let data = evt.data.split('·')
        titleData.data = JSON.parse(data[1])
        diabattlelogVisible.value = true
    } else if (evt.data.indexOf('_') != -1) {
        let data = evt.data.split('_')
        if (data[0] == 'other success') {
            handleGetHistoryOne(data[1])
            ElMessageBox.alert('对手已经完成比赛题目，比赛结束', '比赛结束提示', {
                confirmButtonText: '确定',
                callback: () => {
                    diabattlelogVisible.value = false
                    winStatus.value = false
                    competitionStatus.value = 1 //比赛结束标志
                    handleCompetitionFinish()
                    resetCode()
                },
            })
        }
    }
};

ws.onclose = function (evt) {
    console.log("Connection closed.");
}
// 组件被销毁之前，清空 socket 对象
onBeforeUnmount(() => {
    if (roomId.value != undefined || roomNum.value != undefined) {
        let room = roomId.value == undefined ? roomNum.value : roomId.value;
        ws.send(`close:${room}`)
    }
    // 关闭连接s
    ws.close();
    // 销毁 websocket 实例对象
    ws = null;

});
const handleUpdateUserInfo = async () => {
    let res = await getUserInfo()
    setUser(res)
}
</script>
<style lang="less">
.el-button+.el-button {
    margin-left: 0 !important;
}

.el-table .warning-row {
    --el-table-tr-bg-color: var(--el-color-warning-light-9);
}

.el-table .success-row {
    --el-table-tr-bg-color: var(--el-color-success-light-9);
}

.el-table .danger-row {
    --el-table-tr-bg-color: var(--el-color-danger-light-9);
}
</style>