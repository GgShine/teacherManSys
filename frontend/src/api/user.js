// 统一请求实例。
// 内部含 token 注入与统一错误处理逻辑（见 utils/request.js）。
import request from '@/utils/request'

// 登录接口。
// 调用方：views/Login.vue 的 handleLogin。
export function login(data) {
    // 返回 Promise，结果会先经过 request.js 响应拦截器。
    return request({
        // 对应后端 UserController#login。
        url: '/user/login',
        method: 'post',
        // 请求体：{ username, password }。
        data
    })
}

// 获取用户信息接口。
// 预留给需要按 id 刷新用户信息的页面。
export function getUserInfo(id) {
    return request({
        // 对应后端 UserController#getUserInfo。
        url: `/user/${id}`,
        method: 'get'
    })
}
