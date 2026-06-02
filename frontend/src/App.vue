<!-- 根组件。
  调用关系：main.js 中 createApp(App) 会把此组件作为应用入口。
  此组件本身只负责提供路由出口，具体页面由 router 决定。
-->
<template>
	<!-- 路由出口：/login 渲染 Login.vue，/ 渲染 Home.vue。 -->
	<router-view />
</template>

<script>
export default {
	// 组件名称，方便调试工具识别。
	name: 'App'
}
</script>
