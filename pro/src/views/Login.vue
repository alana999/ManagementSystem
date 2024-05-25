<template>
  <div>
    <input v-model="loginForm.username" placeholder="Username">
    <input v-model="loginForm.password" type="password" placeholder="Password">
    <button @click="submitLogin">Login</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    submitLogin() {
      axios.post('/api/login', this.loginForm)
          .then(response => {
            console.log('Login successful:', response.data);
            // 这里可以将用户信息存储到Vuex或其他状态管理库，或重定向到其他页面
          })
          .catch(error => {
            console.error('Login failed:', error.response.data.error);
            // 处理错误，显示登录失败消息
          });
    }
  }
}
</script>
