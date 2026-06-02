<!-- 档案查询页。
  调用关系：Home.vue 菜单 /archive-query -> 路由渲染此组件。
-->
<template>
	<el-card>
		<!-- 头部区域 -->
		<template #header>
			<div>档案查询</div>
		</template>
		<!-- 当前为占位内容，后续将接入查询条件、表格和详情。 -->
		<el-empty description="档案查询页面已创建，后续可接入搜索与详情" />
	</el-card>
</template>

<script>
export default {
	// 组件名。
	name: 'ArchiveQuery'
}
</script>
