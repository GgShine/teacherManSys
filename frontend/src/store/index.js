import { createStore } from 'vuex'
import { getToken, setToken, removeToken, getUser, setUser, removeUser } from '@/utils/auth'

const store = createStore({
	state: {
		token: getToken() || '',
		user: getUser() || null
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token || ''
			if (token) {
				setToken(token)
			} else {
				removeToken()
			}
		},
		SET_USER(state, user) {
			state.user = user || null
			if (user) {
				setUser(user)
			} else {
				removeUser()
			}
		},
		LOGOUT(state) {
			state.token = ''
			state.user = null
			removeToken()
			removeUser()
		}
	},
	getters: {
		token: state => state.token,
		user: state => state.user,
		roleId: state => (state.user ? state.user.roleId : null)
	}
})

export default store
