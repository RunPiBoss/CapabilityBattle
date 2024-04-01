<template>
  <el-menu router mode="horizontal" class="bg-slate-50" style="height: 100%;" text-color="black" default-active="2">
    <el-menu-item v-for="v in items" :key="v.path" :index="v.path">
      <el-icon>
        <component :is="v.icon"/>
      </el-icon>
      <span class="text-base">{{ v.menuName }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { mainStore } from "../../store";
let items = ref([]);
onMounted(() => {
  items.value = mainStore().nav
})
</script>

<style scoped>
.el-menu.el-menu--horizontal{
  border: 100%;
}
</style>