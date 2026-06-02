import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import TemplateManage from '../views/TemplateManage.vue'
import ArchiveSubmit from '../views/ArchiveSubmit.vue'
import ArchiveReview from '../views/ArchiveReview.vue'
import ArchiveQuery from '../views/ArchiveQuery.vue'

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: { title: '登录' }
    },
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: { requiresAuth: true, title: '首页' },
        children: [
            {
                path: '/template-manage',
                name: 'TemplateManage',
                component: TemplateManage,
                meta: { requiresAuth: true, roles: [4] } // 仅学校管理员
            },
            {
                path: '/archive-submit',
                name: 'ArchiveSubmit',
                component: ArchiveSubmit,
                meta: { requiresAuth: true, roles: [1, 2, 3, 4] }
            },
            {
                path: '/archive-review',
                name: 'ArchiveReview',
                component: ArchiveReview,
                meta: { requiresAuth: true, roles: [2, 3, 4] } // 管理员角色
            },
            {
                path: '/archive-query',
                name: 'ArchiveQuery',
                component: ArchiveQuery,
                meta: { requiresAuth: true, roles: [1, 2, 3, 4] }
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router