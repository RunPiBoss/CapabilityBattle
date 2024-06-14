<template>
    <div class="container">
        <h2>{{ state.isWin === true ? '恭喜你获胜了！' : '很遗憾，你输了。' }}</h2>
        <div class="player-info">
            <el-avatar :size="120" :src="state.opponent.avatar" />
            <div>{{ state.opponent.name }}</div>
        </div>

        <div class="match-info">
            <div>对局时间：{{ state.match.time }}</div>
            <div>对局结果：{{ state.isWin === true ? '胜利' : '失败' }}</div>
            <div>对局加分：{{ state.match.score }}</div>
            <div>综合分数：{{ state.match.rating }}</div>
        </div>
    </div>
</template>

<script setup>
import { getUser } from '../../utils/user';
import { reactive, onMounted } from 'vue';
import { mainStore } from '../../store';
let state = reactive({
    isWin: '',
    opponent: {
        name: getUser().nickName,
        avatar: "api/images/" + getUser().avatar + '.jpg',
    },
    match: {
        time: '',
        score: '',
        rating: ''
    },
})

onMounted(() => {
    state.match.time = mainStore().competitionData.time
    state.match.score = mainStore().competitionData.playAdd
    state.match.rating = mainStore().competitionData.rating
    state.isWin = mainStore().isWin;
})

const backToHome = () => {
    // 回到首页的操作

}
const playAgain = () => {
    // 再来一局的操作

}

</script>

<style>
.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
}

.player-info {
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.match-info {
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.btn-group {
    margin-top: 50px;
}
</style>