// frontend/src/utils/request.js
// Axios HTTP 客户端。
import axios from 'axios'
// Element Plus 消息提示组件。
import { ElMessage } from 'element-plus'
// token 相关工具：请求头注入和 401 时清理登录态。
import { getToken, removeToken } from './auth'

// 创建axios实例
// 调用方：frontend/src/api/*.js 的每个 API 方法都会使用这个实例。
const service = axios.create({
    // 默认后端地址。也可由环境变量 VUE_APP_BASE_API 覆盖。
    baseURL: process.env.VUE_APP_BASE_API || 'http://localhost:8080/api',
    // 超时时间，防止请求长期挂起。
    timeout: 15000
})

// 请求拦截器
// 每个请求发出前都会执行。
service.interceptors.request.use(
    config => {
        // 从本地读取 token，用于鉴权。
        const token = getToken()
        if (token) {
            // 按 Bearer 规范注入 Authorization 请求头。
            config.headers['Authorization'] = `Bearer ${token}`
        }
        // 返回处理后的请求配置。
        return config
    },
    error => {
        // 请求配置阶段异常。
        console.log(error)
        return Promise.reject(error)
    }
)

// 响应拦截器
// 所有 API 返回后会先进入这里统一判断 code。
service.interceptors.response.use(
    response => {
        // 后端统一响应结构：{ code, msg, data }。
        const res = response.data

        // 非 200 视为业务失败。
        if (res.code !== '200') {
            ElMessage({
                // 后端有 msg 用后端文案，否则给默认文案。
                message: res.msg || 'Error',
                type: 'error',
                duration: 5 * 1000
            })

            // 未授权时清理 token 并回到登录页。
            if (res.code === '401') {
                removeToken()
                window.location.href = '/login'
            }

            // 向上抛出错误，让调用方 catch。
            return Promise.reject(new Error(res.msg || 'Error'))
        } else {
            // 业务成功：返回标准响应对象给上层 API 调用者。
            return res
        }
    },
    error => {
        // HTTP 层面失败（网络错误、超时、5xx 等）。
        console.log('err' + error)
        ElMessage({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
        // 向上抛出错误给页面层处理。
        return Promise.reject(error)
    }
)

// 导出统一请求实例，供 api 模块使用。
export default service