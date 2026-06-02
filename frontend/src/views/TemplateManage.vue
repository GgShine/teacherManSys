<!-- 模板管理页。
  调用关系：Home.vue 左侧菜单点击 /template-manage -> 路由渲染此组件。
-->
<template>
	<el-card>
		<!-- 卡片头部插槽 -->
		<template #header>
			<div>模板管理</div>
		</template>
		<!-- 占位提示：后续替换为模板列表、增删改查表单。 -->
		<el-empty description="模板管理功能已就绪，后续可接入具体接口" />
	</el-card>
</template>

<script>
export default {
	// 组件名，供路由和调试识别。
	name: 'TemplateManage'
}
</script>
