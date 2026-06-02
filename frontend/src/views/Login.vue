<!-- frontend/src/views/Login.vue -->
<!-- 登录页模板。
  调用关系：路由 /login -> router 渲染该组件 -> 用户点击登录调用 handleLogin。
-->
<template>
  <div class="login-container">
    <el-card class="login-card">
      <!-- 系统标题 -->
      <h2 style="text-align: center; margin-bottom: 30px;">教室业务档案管理系统</h2>
      <!-- 登录表单：绑定 loginForm，校验规则为 loginRules -->
      <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <!-- 用户名输入框 -->
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <!-- 密码输入框，回车触发登录 -->
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" @keyup.enter="handleLogin"></el-input>
        </el-form-item>
        <el-form-item>
          <!-- 登录按钮：loading 由 data.loading 控制 -->
          <el-button type="primary" @click="handleLogin" :loading="loading" style="width: 100%;">
            {{ loading ? '登录中...' : '登录' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
// 登录 API 方法：内部调用 request.js 发起 POST /user/login。
import { login } from '@/api/user'

export default {
  // 组件名，供 Vue Devtools 与调试使用。
  name: 'Login',
  data() {
    return {
      // 表单模型：由输入框 v-model 双向绑定。
      loginForm: {
        username: '',
        password: ''
      },
      // 表单校验规则：由 el-form 校验器使用。
      loginRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度不能少于6位', trigger: 'blur' }
        ]
      },
      // 登录按钮加载态。
      loading: false
    }
  },
  methods: {
    // 登录方法。
    // 调用链：按钮@click / 回车@keyup.enter -> handleLogin -> login(api/user.js) -> request.js。
    handleLogin() {
      // 先执行前端表单校验。
      this.$refs.loginFormRef.validate(valid => {
        if (valid) {
          // 进入请求中状态。
          this.loading = true
          // 调用后端登录接口。
          login(this.loginForm).then(response => {
            // 按后端返回结构读取 token 与 user。
            const { token, user } = response.data
            // 写入全局 store，并持久化到 localStorage。
            this.$store.commit('SET_TOKEN', token)
            this.$store.commit('SET_USER', user)

            // 根据用户角色跳转到不同页面
            // 当前先统一跳转首页框架。
            this.$router.push('/')
          }).catch(() => {
            // 请求失败时关闭 loading。
            this.loading = false
          }).finally(() => {
            // 无论成功失败都兜底关闭 loading。
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>
/* 登录页容器：居中布局 + 全屏高度 */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

/* 登录卡片尺寸 */
.login-card {
  width: 400px;
  padding: 20px;
}
</style>