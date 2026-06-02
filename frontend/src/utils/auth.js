// 本地存储中的 token 键名。
const TOKEN_KEY = 'archives_token'
// 本地存储中的用户信息键名。
const USER_KEY = 'archives_user'

// 读取 token。
// 调用方：store/index.js 初始化 state.token、request.js 请求拦截器。
export function getToken() {
	return localStorage.getItem(TOKEN_KEY)
}

// 写入 token。
// 调用方：store 中 SET_TOKEN mutation。
export function setToken(token) {
	localStorage.setItem(TOKEN_KEY, token)
}

// 删除 token。
// 调用方：store LOGOUT、request.js 收到 401 时。
export function removeToken() {
	localStorage.removeItem(TOKEN_KEY)
}

// 读取用户对象。
// 调用方：store/index.js 初始化 state.user。
export function getUser() {
	// 先取字符串。
	const raw = localStorage.getItem(USER_KEY)
	// 没有缓存时返回 null。
	if (!raw) {
		return null
	}
	try {
		// 反序列化为对象。
		return JSON.parse(raw)
	} catch (e) {
		// 若 JSON 损坏，清理缓存避免后续异常。
		removeUser()
		return null
	}
}

// 写入用户对象。
// 调用方：store 中 SET_USER mutation。
export function setUser(user) {
	localStorage.setItem(USER_KEY, JSON.stringify(user))
}

// 删除用户对象缓存。
// 调用方：store LOGOUT、SET_USER(null)。
export function removeUser() {
	localStorage.removeItem(USER_KEY)
}
