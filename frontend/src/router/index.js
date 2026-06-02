// 创建路由实例与历史模式方法。
import { createRouter, createWebHistory } from 'vue-router'
// 全局状态，用于导航守卫读取 token 和角色。
import store from '@/store'
// 登录页组件：未登录用户默认会跳转到此页。
import Login from '../views/Login.vue'
// 主框架页组件：登录后进入该布局并渲染子路由。
import Home from '../views/Home.vue'
// 子页面组件：模板管理。
import TemplateManage from '../views/TemplateManage.vue'
// 子页面组件：档案提交。
import ArchiveSubmit from '../views/ArchiveSubmit.vue'
// 子页面组件：档案审核。
import ArchiveReview from '../views/ArchiveReview.vue'
// 子页面组件：档案查询。
import ArchiveQuery from '../views/ArchiveQuery.vue'

// 路由表。
// 由 createRouter({ routes }) 消费，并在 App.vue 的 <router-view /> 中按路径渲染。
const routes = [
    {
        // 登录路由：由守卫在未登录时重定向过来。
        path: '/login',
        name: 'Login',
        component: Login,
        meta: { title: '登录' }
    },
    {
        // 主布局路由：所有业务页面都作为其 children 渲染到 Home.vue 的 <router-view />。
        path: '/',
        name: 'Home',
        component: Home,
        meta: { requiresAuth: true, title: '首页' },
        children: [
            {
                // 学校管理员专用功能。
                path: '/template-manage',
                name: 'TemplateManage',
                component: TemplateManage,
                meta: { requiresAuth: true, roles: [4] } // 仅学校管理员
            },
            {
                // 全角色可访问的提交流程页。
                path: '/archive-submit',
                name: 'ArchiveSubmit',
                component: ArchiveSubmit,
                meta: { requiresAuth: true, roles: [1, 2, 3, 4] }
            },
            {
                // 管理角色可访问审核页。
                path: '/archive-review',
                name: 'ArchiveReview',
                component: ArchiveReview,
                meta: { requiresAuth: true, roles: [2, 3, 4] } // 管理员角色
            },
            {
                // 全角色可访问查询页。
                path: '/archive-query',
                name: 'ArchiveQuery',
                component: ArchiveQuery,
                meta: { requiresAuth: true, roles: [1, 2, 3, 4] }
            }
        ]
    }
]

// 生成路由器实例。该实例在 main.js 中被 app.use(router) 调用。
const router = createRouter({
    // HTML5 history 模式，URL 不带 #。
    history: createWebHistory(),
    // 使用上方定义的路由表。
    routes
})

// 全局前置守卫：每次路由跳转前都会执行。
// 调用链：main.js -> app.use(router) -> 路由跳转时触发 beforeEach。
router.beforeEach((to, from, next) => {
    // 从 store 获取登录态 token。
    const token = store.getters.token
    // 从 store 获取当前用户角色。
    const roleId = store.getters.roleId

    // 目标路由要求登录，但当前无 token，跳登录页。
    if (to.meta.requiresAuth && !token) {
        next('/login')
        return
    }

    // 已登录用户访问登录页时，直接回首页。
    if (token && to.path === '/login') {
        next('/')
        return
    }

    // 有角色要求但当前角色不匹配，降级跳转到可访问的查询页。
    if (to.meta.roles && to.meta.roles.length > 0 && !to.meta.roles.includes(roleId)) {
        next('/archive-query')
        return
    }

    // 其余情况允许通过。
    next()
})

// 导出路由实例，供 main.js 注入。
export default router