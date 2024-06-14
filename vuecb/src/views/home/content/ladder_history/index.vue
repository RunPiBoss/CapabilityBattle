<template>
  <div>
    <h1 class="text-2xl font-mono" style="text-align: center">天梯对战记录</h1>
    <el-table :data="tableData" style="width: 90%">
      <el-table-column prop="ownerId" label="房主" width="180" />
      <el-table-column prop="time" label="对战日期" sortable width="180" />
      <el-table-column prop="winnerId" label="获胜者" width="180" />
      <el-table-column label="状态">
        <template v-slot="scope">
          <el-tag v-if="scope.row.playAdd > 0" type="success">胜利</el-tag>
          <el-tag v-if="scope.row.playAdd === 0" type="info">平局</el-tag>
          <el-tag v-if="scope.row.playAdd < 0" type="danger">失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="playAdd" label="+/-" width="180" />
      <el-table-column prop="rating" label="战力值" width="180" />
    </el-table>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, reactive } from "vue";
import {getHistory, getLadderHistory, getLadderHistoryOne} from "../../../../api";
// let tableData = reactive(sessionStorage.getItem("history") || [])
let tableData = reactive([])
onMounted(async () => {
  let date = await getLadderHistory()
  date.forEach(element => {
    tableData.push(element)
  });
  console.log(tableData)
})
</script>

<style scoped></style>