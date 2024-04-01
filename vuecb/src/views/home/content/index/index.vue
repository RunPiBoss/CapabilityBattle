<template>
    <div class="flex pl">
        <div class="mt-1  flex flex-col">
            <el-card v-for="leftinfo in leftinfos" class="box-card mb-8" style="width: 700px;">
                <template #header shadow>
                    <div class="card-header">
                        <span>{{ leftinfo.header }}</span>

                    </div>
                </template>
                <div class="text item">{{ leftinfo.body }} </div>

            </el-card>
        </div>
        <div class="mt-1 ml-6">
            <el-card v-for="rightinfo in rightinfos" class="box-card mb-8" style="width: 350px;">
                <template #header shadow>
                    <div class="card-header">
                        <span>{{ rightinfo.header }}</span>
                    </div>
                </template>
                <div class="text item">{{ rightinfo.body }}</div>
            </el-card>

        </div>
    </div>
</template>

<script setup>
import { ref, onBeforeUnmount } from "vue";
import { reactive } from 'vue'

// do not use same name with ref
const form = reactive({
    name: '',
    region: '',
    date1: '',
    date2: '',
    delivery: false,
    type: [],
    resource: '',
    desc: '',
})
let leftinfos =
    [
        {
            header: "最新通知1",
            body: "Hey, hi Codeforces!\nIt's mesanu, flamestorm and me and we are very excited to invite you to Codeforces Round 871 (Div. 4)! It starts on Saturday, May 6, 2023 at 22:35UTC+8.\n We worked swiftly to tailor the problems for this contest, so we hope you will enjoy them! We tried to make the statements short, epic and concise."
        },


        {
            header: "算法分享",
            body: "树形DP\n树的最长路径\n—就是找树的直径\n情况1: 无权图\n1. 任选一点u，求到该点最长路径的点p\n2. 求到p最长路径的点q，pq之间的距离就是树的最长路径\n算法证明： https://www.acwing.com/video/414/ 很巧妙hh可以回顾\n情况2: 有权图\n1. 任选一个点，作为根结点（树中的所有点都可以作为根），向下拓展\n2. dfs(root, father) 返回root向下的最长路径长 + 更新树的最长路径\n3. father 记录每个点的父节点，根结点father = 0，意思是没有父节点\n"
        },
        {
            header: "开发通知",
            body: "该系统基本功能已完善"
        },
    ]
let rightinfos = [

    {
        header: "开发人员",
        body: "107 chen34\n108 aysayy \n 109 huhhhhaaa \n 110 zxj 巨牛 \n tutu"
    },
    {
        header: "特别感谢",
        body: "jmu\n teacher zhang\n "
    }
    ,
    {
        header: "i want to say",
        body: "肝\n冲不动了\n头发掉光了"
    }

]
const currentDate = ref(new Date())
const onSubmit = () => {
    console.log('submit!')
}
</script>

<style scoped>
:deep(.el-card__header) {
    background-color: #f3f4f5;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.pl {
    white-space: pre-line;
}
</style>