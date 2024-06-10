<template>
  <el-menu router mode="horizontal" class="bg-slate-50" style="height: 100%;" text-color="black" default-active="2">
    <el-menu-item v-for="v in items" :key="v.path" :index="v.path" v-show="v.id != 7 && v.id != 8">
      <LadderHistory v-if="v.id == 3" :message="items" />
      <el-icon v-show="v.id!=3 && v.id!= 7 && v.id!= 8">
        <component :is="v.icon"/>
      </el-icon>
      <span v-show="v.id!=3 && v.id != 7 && v.id != 8" class="text-base">{{ v.menuName }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { mainStore } from "../../store";
import LadderHistory from "@/components/ladder_history/index.vue";

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