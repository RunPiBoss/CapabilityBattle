<template>
    <div>
        <el-table :data="tableData" :default-sort="{ prop: 'rating', order: 'descending' }" style="width: 90%">
            <el-table-column label="排名" type="index" width="180" />
            <el-table-column prop="userName" label="用户" width="180" />
            <el-table-column prop="nickName" label="昵称" width="180" />
            <el-table-column prop="rating" label="战力值" width="180" />
        </el-table>
    </div>
</template>
  
<script setup>
import { onBeforeMount, onMounted, reactive } from "vue";
import { getAllRanking } from "../../../../api";
let tableData = reactive([])
onMounted(async () => {
    let date = await getAllRanking()
    date.forEach(element => {
        tableData.push(element)
    });
    console.log(tableData);

})
</script>

<style scoped></style>