// Vue 3 应用工厂方法。该方法由浏览器加载入口脚本后直接执行。
import { createApp } from 'vue'
// 根组件，最终由 createApp(App) 作为根节点挂载。
import App from './App.vue'
// 路由实例，供 app.use(router) 注入到全局。
import router from './router'
// Vuex 状态仓库，供 app.use(store) 注入到全局。
import store from './store'
// Element Plus 组件库插件。
import ElementPlus from 'element-plus'
// Element Plus 全局样式，供所有 UI 组件使用。
import 'element-plus/dist/index.css'

// 创建 Vue 应用实例，后续 app.use / app.mount 都基于它。
const app = createApp(App)

// 注册全局状态管理，给 Login.vue、Home.vue 等页面通过 this.$store 调用。
app.use(store)
// 注册路由系统，给 App.vue 的 <router-view /> 渲染页面。
app.use(router)
// 注册 Element Plus 组件库。
app.use(ElementPlus)

// 挂载到 public/index.html 中的 #app 容器。
// 如果 #app 不存在，会出现白屏并在控制台报 mount target selector "#app" returned null。
app.mount('#app')