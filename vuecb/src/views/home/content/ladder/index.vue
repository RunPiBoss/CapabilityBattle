<template>
  <div class="flex">
    <div class="flex flex-col w-2/3 border-2 p-4 border-green-700 border-solid">
      <div class="flex justify-center ml-80 h-12">
        <div class="h-2"><el-button type="warning" disabled class="w-48">
            <h1> 房间号：</h1>
            <h1>{{ roomId }}</h1>
          </el-button></div>
      </div>
      <div class="flex justify-start">
        <div class="w-96">
          <el-card>
            <el-button size="large" v-if="!isReadyMySelf" type="warning" class="ml-32" disabled>尚未准备</el-button>
            <el-button size="large" v-if="isReadyMySelf" type="success" class="ml-32" disabled>准备就绪</el-button>
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
        <div class="flex flex-col w-1/4 justify-center ml-24">
          <el-image class=" h-2/3" :src="vs" fit="contain"></el-image>
          <div class="flex flex-col space-y-4 h-1/3">
            <el-button size="large" @click="handlePrepare(0)" type="warning" v-if="showCancleFlag"
              class="mt-16 bg-orange-100">
              <h1 class="text-orange-400">取消准备</h1>
            </el-button>
            <el-button size="large" @click="handlePrepare(1)" type="warning" v-if="showPrepareFlag"
              class="mt-16 bg-orange-100">
              <h1 class="text-orange-400">准备开始</h1>
            </el-button>
            <el-button size="large" type="warning" v-if="showCreateFlag" class="mt-16 bg-orange-100"
              @click="handleCreateRoom()">
              <h1 class="text-orange-400">创建房间</h1>
            </el-button>
            <el-button size="large" @click="handleStart()" v-if="showStartFlag" type="danger" class="mt-16 bg-red-100">
              <h1 style="color:red;">开始对战</h1>
            </el-button>
            <el-button size="large" v-if="showQuitFlag" type="warning" class="mt-16 bg-orange-100" @click="handleQuit()">
              <h1 class="text-sm text-orange-400">退出房间</h1>
            </el-button>
            <el-button size="large" @click="dialogVisible = true" v-if="showJoinFlag" type="warning"
              class="mt-16 bg-orange-100">
              <h1 class="text-sm text-orange-400">加入房间</h1>
            </el-button>
          </div>
        </div>
      </div>

    </div>
    <div class="flex flex-col w-1/4">
      <div class="h-2/5 border-solid border-2 border-green-700">
        <el-card class="box-card" style="height: 100%;">
          <template #header>
            <div class="card-header">
              <span>排行榜</span>
              <el-button class="button" text>Ranking</el-button>
            </div>
          </template>
          <div v-for="(item, index) in rankingData.data" :key="item" class="flex justify-around mb-3">
            <span class="text-lg">{{ index + 1 }}</span>
            <el-avatar shape="square" :size="35" :src="'api/images/' + item.avatar + '.jpg'" />
            <span class=" text-lg">{{ item.nickName }}</span>
            <span class=" text-lg ml-10">{{ item.cnt }}场</span>
          </div>
        </el-card>
      </div>
      <div class="h-3/5 border-solid border-2 border-green-700">
        <el-card class="box-card" style="height: 100%;">
          <template #header>
            <div class="card-header">
              <span>房间选手</span>
              <el-button class="button" text>player</el-button>
            </div>
          </template>
          <div v-for="(item, index) in players.playerArray" :key="item" class="flex mb-3">
            <el-avatar shape="square" :size="40" :src="'api/images/' + item.avatar + '.jpg'" />
            <span class=" text-lg ml-10">{{ item.nickName }}</span>
            <el-button class="ml-10 bg-green-300" size="small" @click="handlePrepare(0)" type="success"
              v-if="players.status[index].ready === true">
              <h1 class="text-green-800">准备就绪</h1>
            </el-button>
            <el-button class="ml-10 bg-red-300" size="small" @click="handlePrepare(1)" type="danger"
              v-if="players.status[index].ready === false">
              <h1 class="text-red-800">未准备</h1>
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
          <el-input v-model="titleData.data.titleDescribe" disabled :rows="6" style="width: 100%" type="textarea" />
        </el-form-item>
        <el-form-item label="输入格式">
          <el-input v-model="titleData.data.inputFormat" disabled :rows="2" style="width: 100%" type="textarea" />
        </el-form-item>
        <el-form-item label="输出格式">
          <el-input v-model="titleData.data.outputFormat" :rows="2" style="width: 100%" type="textarea" />
        </el-form-item>
        <div class="flex flex-row space-x-2">
          <el-form-item label="样例输入" class="w-1/2">
            <el-input v-model="titleData.data.sampleInput" readonly :rows="2" style="width: 100%" type="textarea" />
          </el-form-item>
          <el-form-item label="样例输出" class="w-1/2">
            <el-input v-model="titleData.data.sampleOutput" readonly :rows="2" style="width: 100%" type="textarea" />
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
import vs from "../../../../assets/images/vs.png"
import { reactive, ref, onBeforeUnmount } from "vue";
import { getUser, setUser } from '../../../../utils/user';
import {
  createLadderRoom, joinLadderRoom, getUserInfoById, getUserInfo,
<<<<<<< HEAD
  quitLadderRoom, ladderPrepare, startLadderCompetition, submitLadderTitle, getLadderHistoryOne
=======
  quitLadderRoom, ladderPrepare, startLadderCompetition, submitLadderTitle, getLadderHistory
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
} from '../../../../api'
import { mainStore } from "../../../../store";
import { ElNotification, ElMessageBox } from 'element-plus';
import codeMirror from "../../../../components/code/codeMirror.vue";
import { getToken } from "../../../../utils/auth";
import settlement from "../../../../components/settlement/index.vue"
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
//比赛状态
let competitionStatus = ref(0)
//关闭测试结果窗口

//对战结算
let winStatus = ref(false)
let showSettlement = ref(false);
const handleCompetitionFinish = () => {
  mainStore().setIsWin(winStatus.value)
  showSettlement.value = true
  handleUpdateUserInfo()
  competitionStatus.value = 0;
}
const handleUpdateUserInfo = async () => {
  let res = await getUserInfo()
  setUser(res)
}
//code编辑框传来的代码
const codeValue = ref()
const submitCodeResult = ref(false)
let tableData = reactive([])
let rankingData = reactive({
  data: []
})

//对象数组排序算法
function sortBy(attr, rev) {
  if (rev == undefined) { rev = 1 } else { (rev) ? 1 : -1; }
  return function (a, b) {
    a = a[attr];
    b = b[attr];
    if (a < b) { return rev * -1 }
    if (a > b) { return rev * 1 }
    return 0;
  }
}

//提交代码
const handleSubmit = async () => {
  tableData = []
  submitCode.roomId = roomId.value
  submitCode.code = codeValue.value.code
  submitCode.codeType = codeValue.value.codeType
  let res = await submitLadderTitle(submitCode)
  let data = {
    result: ''
  }
  res.title.result.forEach(item => {
    data.result = item
    tableData.push(data)
  })
  if (res.rankingMap != undefined) {
    rankingData.data = []
    Object.keys(res.rankingMap).forEach(element => {
      if (element != '@type') {
        let data = {
          avatar: res.rankingMap[element].avatar,
          nickName: res.rankingMap[element].nickName,
          cnt: res.rankingMap[element].cnt
        }
        rankingData.data.push(data)
      }
    })
    rankingData.data.sort(sortBy('cnt', -1))
  }
  if (res.title.success == false) {
    submitCodeResult.value = true
  } else {
    //获取当前对局记录
    handleGetHistoryOne(res.time)
    winStatus.value = true
    submitCodeResult.value = true
    //比赛结束标志
    competitionStatus.value = 1
    handleUpdateUserInfo()
    resetCode()
  }
}
const resetCode = () => {
  if (isOwner.value == false) {
    showCancleFlag.value = false
    showPrepareFlag.value = true
    players.status.forEach(element => {
      if (element.id != ownerId.value) {
        element.ready = false
      }
    })
    isReadyMySelf.value = false
  } else if (isOwner.value == true) {
    showStartFlag.value = false
    players.status.forEach(element => {
      if (element.id != user.id) {
        element.ready = false
      }
    })
  }
  showQuitFlag.value = true
  codeValue.value.code = ""
}
const handleGetHistoryOne = async (time, winnerId) => {
  let historyOneData = {
    myId: user.id,
    time
  }
<<<<<<< HEAD
  let resData = await getLadderHistoryOne(historyOneData)
=======
  let resData = await getLadderHistory(historyOneData)
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
  mainStore().setCompetitionData(resData)
}
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
let user = reactive(getUser())
const roomId = ref()//当前创建的房间号
const isOwner = ref()//标识是否为房主
const handleCreateRoom = async () => {
  roomId.value = await createLadderRoom()
  ElNotification({
    title: "提示信息",
    message: `创建成功，房间号为：${roomId.value}`,
    type: 'success'
  })
  isReadyMySelf.value = true
  isOwner.value = true
  showCreateFlag.value = false
  showJoinFlag.value = false
  showQuitFlag.value = true
}
let showStartFlag = ref(false)
let showCancleFlag = ref(false)
let showPrepareFlag = ref(false)
let showCreateFlag = ref(true)
let showQuitFlag = ref(false)
let showJoinFlag = ref(true)
let isReadyMySelf = ref(false)
let roomNum = ref()
let ownerId = ref()
const token = getToken();
let dialogVisible = ref(false)
const handleJoin = async () => {
  dialogVisible.value = false
  let data = await joinLadderRoom(roomNum.value)
  ownerId.value = data.owner
  Object.keys(data.readyMap).forEach(element => {
    if (element !== '@type' && element != user.id) {
      handleGetUserInfo(element)
      let user = {
        id: element,
        ready: data.readyMap[element]
      }
      players.status.push(user)
    }
  });
  if (data.rankingMap != undefined) {
    Object.keys(data.rankingMap).forEach(element => {
      if (element != '@type') {
        let rank = {
          avatar: data.rankingMap[element].avatar,
          nickName: data.rankingMap[element].nickName,
          cnt: data.rankingMap[element].cnt
        }
        rankingData.data.push(rank)
      }
    })
    rankingData.data.sort(sortBy('cnt', -1))
  }
  ElNotification({
    title: '提示信息',
    message: '加入房间成功',
    type: 'success'
  })
  isReadyMySelf.value = false
  isOwner.value = false
  showCreateFlag.value = false
  showPrepareFlag.value = true
  showCancleFlag.value = false
  showQuitFlag.value = true
  showJoinFlag.value = false
  roomId.value = roomNum.value
}
const handleQuit = async () => {
  let res = await quitLadderRoom(roomId.value)
  if (res === "退出成功" || res === '解散成功') {
    if (isOwner.value == true) {
      ElNotification.success({
        title: '提示信息',
        message: "房间解散成功"
      })
    } else if (isOwner.value == false) {
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
const resetFlags = () => {
  players.playerArray = []
  players.status = []
  isReadyMySelf.value = false
  showQuitFlag.value = false
  dialogVisible.value = false
  diabattlelogVisible.value = false
  showCreateFlag.value = true
  showJoinFlag.value = true
  showPrepareFlag.value = false
  showStartFlag.value = false
  showCancleFlag.value = false
  competitionStatus.value = 0;
  rankingData.data = []
  roomId.value = undefined
}
const players = reactive({
  playerArray: [],
  status: []
})
const handlePrepare = async () => {
  let res = await ladderPrepare(roomId.value)
  if (res === 1) {
    ElNotification.success({
      title: '提示信息',
      message: "准备成功"
    })
    showCancleFlag.value = true
    showPrepareFlag.value = false
    showQuitFlag.value = false
  } else {
    ElNotification.success({
      title: '提示信息',
      message: "取消准备成功"
    })
    showCancleFlag.value = false
    showPrepareFlag.value = true
    showQuitFlag.value = true
  }
  isReadyMySelf.value = true

}
const handleGetUserInfo = async (id) => {
  let user = await getUserInfoById(id)
  players.playerArray.push(user)
}
//题目数据
let titleData = reactive({
  data: {}
})
//打开代码界面
let diabattlelogVisible = ref(false)
const handleStart = async () => {
  let res = await startLadderCompetition(roomId.value)
  if (res != null) {
    diabattlelogVisible.value = true
    titleData.data = res
  }
}
//关闭代码对战界面
const handleCloseCode = () => {
  ElMessageBox.confirm('比赛期间不能退出比赛')
    .then(() => {
    })
    .catch(() => {
    })
  codeValue.value.code = ""
}
<<<<<<< HEAD
// 这里监听的地址是后端的地址
var ws = new WebSocket(`ws://59.110.28.248:8080/capabilityBattle/ws/${token}`);
=======
var ws = new WebSocket(`ws://127.0.0.1:8080/capabilityBattle/ws/${token}`);
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
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
      let user = {
        id: data[1],
        ready: false
      }
      players.status.push(user)
      showCreateFlag.value = false
      showStartFlag.value = false
      handleGetUserInfo(data[1])
    }
  } else if (evt.data.indexOf('!') != -1) {
    let data = evt.data.split('!')
    ElNotification.warning({
      title: "提示信息",
      message: '有用户退出房间',
    })
    players.playerArray = players.playerArray.filter(e => e.id != data[1])
    players.status = players.status.filter(e => e.id != data[1])
  } else if (evt.data === "房主解散了房间") {
    ElNotification.warning({
      title: "提示信息",
      message: '房主解散了房间',
    })
    resetFlags()
  } else if (evt.data.indexOf('#') != -1) {
    //标识是否为最后一个准备
    let cnt = 0;
    let data = evt.data.split('#')
    if (data[0] === 'ready') {
      ElNotification.success({
        title: "提示信息",
        message: '对方已经准备就绪',
      })
      players.status.forEach(element => {
        if (element.id === data[1] && element.ready === false) {
          element.ready = true
        }
      })
      players.status.forEach(element => {
        if (element.id === data[1] && element.ready === false) {
          cnt++;
        }
      })
      if (cnt == 0 && isOwner.value == true) {
        showStartFlag.value = true
      }
    } else if (data[0] === 'noReady') {
      ElNotification.success({
        title: "提示信息",
        message: '对方取消准备',
      })
      players.status.forEach(element => {
        if (element.id === data[1] && element.ready === true) {
          element.ready = false
        }
      })
      showStartFlag.value = false
    }
  } else if (evt.data.indexOf('$') != -1) {
    let data = evt.data.split('$')
    titleData.data = JSON.parse(data[1])
    diabattlelogVisible.value = true
  } else if (evt.data.indexOf('%') != -1) {
    let data = evt.data.split('%')
    if (data[0] == 'other success') {
      handleGetHistoryOne(data[1])
      let res = JSON.parse(data[2])
      rankingData.data = []
      Object.keys(res).forEach(element => {
        if (element != "@type") {
          let data = {
            avatar: res[element].avatar,
            nickName: res[element].nickName,
            cnt: res[element].cnt
          }
          rankingData.data.push(data)
        }
      })
      //降序排
      rankingData.data.sort(sortBy('cnt', -1))
      resetCode()
      ElMessageBox.alert('对手已经完成比赛题目，比赛结束', '比赛结束提示', {
        confirmButtonText: '确定',
        callback: () => {
          diabattlelogVisible.value = false
          winStatus.value = false
          competitionStatus.value = 1 //比赛结束标志
          handleCompetitionFinish()
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
  // 关闭连接
  ws.close();
  // 销毁 websocket 实例对象
  ws = null;
});
</script>

<style lang="less">
:deep(.el-aside) {
  height: 700px;
}

.el-button+.el-button {
  margin-left: 0 !important;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
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