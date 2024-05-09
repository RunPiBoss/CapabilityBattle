<template>  
  <div>  
    <!-- 输入框 -->  
    <textarea v-model="currentInput" @keyup.enter="addInput" placeholder="请输入文字后按回车..."></textarea>  
  
    <!-- 打印文字的方框 -->  
    <div class="print-box" v-for="(input, index) in userInputs" :key="index">  
      {{ input }}  
    </div>  
  </div>  
</template>  
  
<script>  
export default {  
  data() {  
    return {  
      currentInput: '', // 当前输入的文字  
      userInputs: [], // 存储所有输入的文字  
    };  
  },  
  mounted() {  
    // 在组件挂载后，从sessionStorage中恢复用户输入（如果有的话）  
    const savedInputs = sessionStorage.getItem('userInputs');  
    if (savedInputs) {  
      this.userInputs = JSON.parse(savedInputs);  
    }  
  },  
  beforeDestroy() {  
    // 在组件销毁前，将用户输入保存到sessionStorage中  
    sessionStorage.setItem('userInputs', JSON.stringify(this.userInputs));  
  },  
  methods: {  
    addInput() {  
      // 如果当前输入不为空，则添加到userInputs数组中  
      if (this.currentInput.trim() !== '') {  
        this.userInputs.push(this.currentInput);  
        // 清空当前输入框的内容  
        this.currentInput = '';  
      }  
    },  
  },  
};  
</script>  
  
<style scoped>  
.print-box {  
  border: 1px solid #ccc;  
  padding: 10px;  
  margin-bottom: 10px;  
  /* 添加其他你想要的样式 */  
}  
</style>