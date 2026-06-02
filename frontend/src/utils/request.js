// frontend/src/utils/request.js
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { getToken, removeToken } from './auth'

// 创建axios实例
const service = axios.create({
    baseURL: process.env.VUE_APP_BASE_API || 'http://localhost:8080/api',
    timeout: 15000
})

// 请求拦截器
service.interceptors.request.use(
    config => {
        const token = getToken()
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data

        if (res.code !== '200') {
            ElMessage({
                message: res.msg || 'Error',
                type: 'error',
                duration: 5 * 1000
            })

            if (res.code === '401') {
                removeToken()
                window.location.href = '/login'
            }

            return Promise.reject(new Error(res.msg || 'Error'))
        } else {
            return res
        }
    },
    error => {
        console.log('err' + error)
        ElMessage({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)

export default service