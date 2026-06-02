// Vuex 4 创建方法。
import { createStore } from 'vuex'
// 本地缓存工具：token/user 的读写和清理。
import { getToken, setToken, removeToken, getUser, setUser, removeUser } from '@/utils/auth'

// 全局状态仓库。
// 调用链：main.js -> app.use(store) -> 任意组件通过 this.$store 使用。
const store = createStore({
	// 全局状态定义。
	state: {
		// 初始化时优先从 localStorage 读取，支持页面刷新后登录态保持。
		token: getToken() || '',
		// 当前登录用户信息。
		user: getUser() || null
	},
	// 同步修改状态的方法。调用方包括 Login.vue、Home.vue 等组件。
	mutations: {
		// 设置 token：由 Login.vue 登录成功后调用。
		SET_TOKEN(state, token) {
			state.token = token || ''
			if (token) {
				// token 存本地，供 request.js 请求拦截器读取。
				setToken(token)
			} else {
				// token 为空时移除本地缓存。
				removeToken()
			}
		},
		// 设置用户信息：由 Login.vue 登录成功后调用。
		SET_USER(state, user) {
			state.user = user || null
			if (user) {
				// 用户信息持久化，供刷新后恢复。
				setUser(user)
			} else {
				// 清空用户缓存。
				removeUser()
			}
		},
		// 登出：由 Home.vue 点击退出登录调用。
		LOGOUT(state) {
			state.token = ''
			state.user = null
			// 同步清理本地缓存，避免残留登录态。
			removeToken()
			removeUser()
		}
	},
	// 派生状态：路由守卫和组件常用。
	getters: {
		// 当前 token。
		token: state => state.token,
		// 当前用户对象。
		user: state => state.user,
		// 当前角色 ID：路由守卫用来做角色鉴权。
		roleId: state => (state.user ? state.user.roleId : null)
	}
})

// 导出仓库实例，供 main.js 使用。
export default store
