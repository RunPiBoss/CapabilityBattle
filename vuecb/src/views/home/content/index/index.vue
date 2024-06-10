<<<<<<< HEAD
<template>
  <div>
    <!--     轮播图-->
    <div>
      <el-carousel type="card" height="450px" :interval="10000">
        <el-carousel-item v-for="item in imgs" :key="item">
          <router-link to="friend">
            <img :src="item" alt="" style="width: 100%">
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </div>

    <!--    Cpability Battle论坛-->
    <div style="margin: 30px 0">
      <div style="margin: 10px 0">
        <div style="border-bottom: 1px solid orangered; padding: 10px 0; font-size: 20px">Cpability Battle论坛</div>
        <div style="padding: 10px 0">
          <el-input size="small" type="textarea" v-model="commentForm.content"></el-input>
        </div>
        <div class="pd-10" style="text-align: right">
          <el-button type="primary" plain @click="save">评论</el-button>
        </div>
      </div>
    </div>

    <!--      评论列表-->
    <div>
      <div v-for="item in comments" :key="item.id" style="border-bottom: 1px solid #ccc; padding: 10px 0; ">
        <div style="display: flex">
          <!--  头像 -->
          <div style="width: 100px; text-align: center">
            <el-image :src="this.imagUrlFront+item.avatar+this.imageUrlBack"
                      style="width: 50px; height: 50px; border-radius: 50%"></el-image>
          </div>
          <!--  内容 -->
          <div style="flex: 1; font-size: 14px; padding: 5px 0; line-height: 25px">
            <b>{{ item.nickname }}：</b>
            <span>{{ item.content }}</span>
            <div style="display: flex; line-height: 20px; margin-top: 5px">
              <div style="width: 200px;">
                <!--                <i class="el-icon-time"></i>-->
                <el-icon>
                  <Clock/>
                </el-icon>
                <span style="margin-left: 5px">{{ item.time }}</span>
              </div>
              <div style="text-align: right; flex: 1">
                <el-button style="margin-left: 5px" type="text" @click="handleReply(item.id)">回复
                </el-button>
                <el-button type="text" style="color: red" @click="del(item.id)"
                           v-if="user.id === item.userId">删除
                </el-button>
              </div>
            </div>
          </div>
          <!-- 回复 -->
          <div v-if="item.children.length" style="padding-left: 200px;">
            <el-collapse accordion>
              <el-collapse-item title="展开评论">
                <template #title>
                  More Comments<el-icon class="header-icon">
                  <info-filled />
                </el-icon>
                </template>
                <div v-for="(subItem,index) in item.children" :key="subItem.id"
                     style="background-color: #f0f0f0; padding: 5px 20px">
                  <!--                <div v-if="index == 0">-->
                  <div style="font-size: 14px; padding: 5px 0; line-height: 25px">
                    <div>
                      <b style="color: #3a8ee6" v-if="subItem.pnickname">@{{ subItem.pnickname }}</b>
                    </div>
                    <div style="padding-left: 5px">
                      <b>{{ subItem.nickname }}：</b>
                      <span>{{ subItem.content }}</span>
                    </div>
                    <div style="display: flex; line-height: 20px; margin-top: 5px; padding-left: 5px">
                      <div style="width: 200px;">
                        <!--                    <i class="el-icon-time"></i>-->
                        <el-icon>
                          <Clock/>
                        </el-icon>
                        <span style="margin-left: 5px">{{ subItem.time }}</span>
                      </div>
                      <div style="text-align: right; flex: 1">
                        <el-button style="margin-left: 5px" type="text"
                                   @click="handleReply(subItem.id)">回复
                        </el-button>
                        <el-button type="text" style="color: red" @click="del(subItem.id)"
                                   v-if="user.id === subItem.userId">删除
                        </el-button>
                        <!--                        这里是模拟实现展开功能-->
                        <!--                        <div v-show="item.children.length > 1&&index === 0" style="font-weight: bold; margin-top: 5px">-->
                        <!--                          <span style="margin-right: 5px;font-size: x-small">展开更多</span>-->
                        <!--                          <el-icon size="18px">-->
                        <!--                            <More/>-->
                        <!--                          </el-icon>-->
                        <!--                        </div>-->
                      </div>
                    </div>
                  </div>
                  <!--              </div>-->
                </div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </div>
      </div>
    </div>

    <!--    回复弹出框-->
    <el-dialog title="回复" v-model="dialogFormVisible" width="50%">
      <el-form label-width="80px" size="small">
        <el-form-item label="回复内容">
          <el-input type="textarea" v-model="commentForm.contentReply" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false" size="small">取 消</el-button>
          <el-button type="primary" @click="save" plain size="small">确 定</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script>
import {onMounted, reactive} from "vue";
import {delComment, getHistory, loadComment, saveComment} from "@/api/index.js";

export default {
  // name: "ArticleDetail",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      comments: [],
      commentForm: {},
      // id: this.$route.query.id,
      dialogFormVisible: false,
      imagUrlFront: "http://59.110.28.248:4000/api/images/",
      imageUrlBack: ".jpg",

      imgs: [
        'https://blog.lxpstar.top//img/2024/06/01111741.png',
        'https://blog.lxpstar.top//img/2024/06/01111810.png',
        'https://blog.lxpstar.top//img/2024/06/01111835.png',
        'https://blog.lxpstar.top//img/2024/06/01111903.png',
        'https://blog.lxpstar.top//img/2024/06/01113011.png',
        'https://blog.lxpstar.top//img/2024/06/01113225.png'
      ],
    }
  },
  created() {
    this.loadComment()
  },
  methods: {
    // 获取评论内容，查询
    async loadComment() {
      // 创建一个响应式的数组，数据变换，相关组件自动更新
      // let tableData = reactive([])
      // onMounted(async () => {
      //  可以写在这里面
      // });
      let data = await loadComment()
      this.comments = data;
      // console.log(data);

      // this.request.get("/comment/tree/").then(res => {
      //   this.comments = res.data
      //   console.log(this.comments)
      // })
    },
    // 增加
    async save() {
      if (!this.user.id) {
        this.$message.warning("请登录后操作")
        return
      }
      // this.commentForm.articleId = this.id 文章id
      if (this.commentForm.contentReply) {
        this.commentForm.content = this.commentForm.contentReply
      }
      console.log(this.commentForm);
      let res = await saveComment(this.commentForm);
      // 不用判断状态码，在request.js中已经判断过了，直接判断有无数据就可以了
      if (res !== null) {
        this.$message.success("评论成功")
        this.commentForm = {}  // 初始化评论对象内容
        this.loadComment()
        this.dialogFormVisible = false
      } else {
        this.$message.error(res.msg)
      }
      // this.request.post("/comment/add", this.commentForm).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("评论成功")
      //     this.commentForm = {}  // 初始化评论对象内容
      //     this.loadComment()
      //     this.dialogFormVisible = false
      //   } else {
      //     this.$message.error(res.msg)
      //   }
      // })
    },
    // 删除
    async del(id) {
      let res = await delComment(id);
      // 不用判断状态码，在request.js中已经判断过了
      if (res !== null) {
        this.$message.success("删除成功")
        this.loadComment();
      } else {
        this.$message.error("删除失败")
      }

      // this.request.delete("/comment/" + id).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("删除成功")
      //     this.loadComment()
      //   } else {
      //     this.$message.error("删除失败")
      //   }
      // })
    },
    handleReply(pid) {
      this.commentForm = {pid: pid}
      this.dialogFormVisible = true
    },
  }
}
</script>

<style scoped>
.print-box {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  /* 添加其他你想要的样式 */
}
=======
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
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
</style>