<!-- 档案审核页。
  调用关系：Home.vue 菜单 /archive-review -> 路由渲染此组件。
-->
<template>
	<el-card>
		<!-- 头部区域 -->
		<template #header>
			<div>档案审核</div>
		</template>
		<!-- 当前为占位内容，后续将接入审核列表与审批动作。 -->
		<el-empty description="档案审核流程页面已创建，后续可接入审核列表" />
	</el-card>
</template>

<script>
export default {
	// 组件名。
	name: 'ArchiveReview'
}
</script>
