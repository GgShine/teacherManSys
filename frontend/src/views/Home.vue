<!-- 首页主布局。
  调用关系：登录成功后 this.$router.push('/') -> 路由渲染 Home.vue。
  Home.vue 内部的 <router-view /> 再渲染业务子页面。
-->
<template>
	<el-container style="height: 100vh;">
		<!-- 顶部栏：系统名 + 用户信息 + 退出 -->
		<el-header style="display: flex; align-items: center; justify-content: space-between;">
			<div>教室业务档案管理系统</div>
			<div>
				<!-- 当前用户名显示 -->
				<span style="margin-right: 12px;">{{ userName }}</span>
				<!-- 退出按钮：调用 logout -->
				<el-button type="danger" plain size="small" @click="logout">退出登录</el-button>
			</div>
		</el-header>
		<el-container>
			<!-- 左侧菜单：使用 router 模式，点击即路由跳转 -->
			<el-aside width="220px" style="border-right: 1px solid #eee;">
				<el-menu :default-active="$route.path" router>
					<el-menu-item index="/template-manage">模板管理</el-menu-item>
					<el-menu-item index="/archive-submit">档案提交</el-menu-item>
					<el-menu-item index="/archive-review">档案审核</el-menu-item>
					<el-menu-item index="/archive-query">档案查询</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				<!-- 子路由出口：TemplateManage / ArchiveSubmit 等组件在这里显示 -->
				<router-view />
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
export default {
	// 组件名称。
	name: 'Home',
	computed: {
		// 用户显示名。
		// 数据来源：store.state.user，由 Login.vue 登录成功后 SET_USER 写入。
		userName() {
			return (this.$store.state.user && this.$store.state.user.realName) || '用户'
		}
	},
	methods: {
		// 退出登录。
		// 调用链：按钮@click -> logout -> store.LOGOUT -> router.push('/login')。
		logout() {
			this.$store.commit('LOGOUT')
			this.$router.push('/login')
		}
	}
}
</script>
