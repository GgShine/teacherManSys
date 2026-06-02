<!-- 档案提交页。
  调用关系：Home.vue 菜单 /archive-submit -> 路由渲染此组件。
-->
<template>
	<el-card>
		<!-- 头部区域 -->
		<template #header>
			<div>档案提交</div>
		</template>
		<!-- 当前为占位内容，后续将接入提交表单和文件上传。 -->
		<el-empty description="档案提交流程页面已创建，后续可接入表单与上传" />
	</el-card>
</template>

<script>
export default {
	// 组件名。
	name: 'ArchiveSubmit'
}
</script>
