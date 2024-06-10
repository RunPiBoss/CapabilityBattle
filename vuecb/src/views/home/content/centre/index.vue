<template>
  <div>
    <el-row>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple">
          <el-avatar :size="400" :src="'api/images/' + user.avatar + '.jpg'" />
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple-light">
          <el-card class="box-card" style="background-color: #f3f4f5">
            用户名
          </el-card>
          <el-card class="box-card">
            {{ user.nickName }}
          </el-card>

          <el-card class="box-card" style="background-color: #f3f4f5">
            邮箱
          </el-card>
          <el-card class="box-card">
            {{ user.email }}
          </el-card>

          <el-card class="box-card" style="background-color: #f3f4f5">
            天梯分
          </el-card>
          <el-card class="box-card">
            {{ user.rating }}
          </el-card>

        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple">
        </div>
      </el-col>
    </el-row>
    <el-upload :headers="headers" class="avatar-uploader" action="api/user/uploadAvatar" :show-file-list="false"
      :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">

      <!--            <img v-if="imageUrl" :src="imageUrl" class="avatar" />-->
      <el-icon class="avatar-uploader-icon">
        上传头像
      </el-icon>

    </el-upload>

  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { getToken } from '../../../../utils/auth';
import { setUser, getUser } from '../../../../utils/user';
const headers = {
  token: getToken()
}
const user = getUser();

const imageUrl = ref('')
const handleAvatarSuccess = (
  response,
  uploadFile
) => {
  imageUrl.value = "/api/" + response.data
  let user = getUser()
  user.avatar = response.data
  setUser(user)
  history.go(0)
}

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>