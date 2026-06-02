<template>
	<el-container style="height: 100vh;">
		<el-header style="display: flex; align-items: center; justify-content: space-between;">
			<div>教室业务档案管理系统</div>
			<div>
				<span style="margin-right: 12px;">{{ userName }}</span>
				<el-button type="danger" plain size="small" @click="logout">退出登录</el-button>
			</div>
		</el-header>
		<el-container>
			<el-aside width="220px" style="border-right: 1px solid #eee;">
				<el-menu :default-active="$route.path" router>
					<el-menu-item index="/template-manage">模板管理</el-menu-item>
					<el-menu-item index="/archive-submit">档案提交</el-menu-item>
					<el-menu-item index="/archive-review">档案审核</el-menu-item>
					<el-menu-item index="/archive-query">档案查询</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				<router-view />
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
export default {
	name: 'Home',
	computed: {
		userName() {
			return (this.$store.state.user && this.$store.state.user.realName) || '用户'
		}
	},
	methods: {
		logout() {
			this.$store.commit('LOGOUT')
			this.$router.push('/login')
		}
	}
}
</script>
