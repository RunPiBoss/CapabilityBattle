<template>
    <div class="drag-ball" draggable="true" @dragstart="dragstart($event)" @dragend="dragend($event)"
        :style="`right:${elRight}px;bottom:${elBottom}px`">
        <span>
        </span>
    </div>
</template>
<script lang="ts" setup>
import { ref } from "vue";

const startclientX = ref(0); //记录开始的横坐标位置
const startclientY = ref(0); //记录开始的纵坐标位置
const elRight = ref(50); //定位-初始位置
const elBottom = ref(70); //定位-初始位置
// 拖拽开始事件
const dragstart = (e: any) => {
    // 记录拖拽元素初始位置
    startclientX.value = e.clientX;
    startclientY.value = e.clientY;
};
// 拖拽完成事件
const dragend = (e: any) => {
    console.log("eeeeee", e);
    let x = startclientX.value - e.clientX; // 计算偏移量
    let y = startclientY.value - e.clientY;
    elRight.value += x; // 实现拖拽元素随偏移量移动
    elBottom.value += y;
};
</script>
<style scoped lang="less">
.drag-ball {
    cursor: pointer;
    position: absolute;
    width: 150px;
    height: 50px;
    background: #242b3a;
    box-shadow: 0px 6px 16px -8px rgb(0 0 0 / 8%),
        0px 9px 28px 0px rgb(0 0 0 / 5%), 0px 12px 48px 16px rgb(0 0 0 / 3%);
    border-radius: 23px;
    text-align: center;
    color: #fff;
    line-height: 50px;
}
</style>