<script>
import { ArrowDown } from '@element-plus/icons-vue'
export default {
  name: "ladder_history",
  props:{
    message:{}
  },
  mounted() {
    // console.log(this.message);
  }
}
</script>
<template>
  <el-icon>
    <documentCopy/>
  </el-icon>
  <el-dropdown :hide-on-click="false">
    <span class="text-base"  style="color: black">
      {{message[2].menuName}}
      <!--      <el-icon class="el-icon&#45;&#45;right">-->
      <!--        <arrow-down />-->
      <!--      </el-icon>-->
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <router-link to="/ladder_history">
          <el-dropdown-item :icon="message[6].icon">{{message[6].menuName}}</el-dropdown-item>
        </router-link>
        <router-link to="/history">
          <el-dropdown-item :icon="message[7].icon">{{message[7].menuName}}</el-dropdown-item>
        </router-link>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<style scoped>

</style>