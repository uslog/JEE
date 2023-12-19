import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/LoginPage.vue')
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: () => import('@/views/welcome/RegisterPage.vue')
                }, {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/views/welcome/ForgetPage.vue')
                },
                {
                    path: 'pet',
                    name: 'welcome-submit',
                    component: () => import('@/views/PetPage.vue')
                },
                {
                    path: 'showPet',
                    name: 'welcome-show',
                    component: () => import('@/views/ShowPetPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import('@/views/IndexView.vue'),
        }
    ]
})

router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized(); // 检查用户是否未登录

    // 定义一个数组，包含不需要登录就能访问的路由名称
    const publicPages = ['welcome-login', 'welcome-register', 'welcome-forget','welcome-submit','welcome-show'];

    // 检查即将访问的路由是否在公开页面列表中
    const isPublicPage = publicPages.includes(to.name);

    if (!isUnauthorized && isPublicPage) {
        // 如果用户已登录且试图访问公开页面之一，重定向到首页
        next('/index');
    } else if (isUnauthorized && !isPublicPage) {
        // 如果用户未登录且试图访问非公开页面，重定向到登录页面
        next({
            name: 'welcome-login',
            query: { message: '请先登录' }
        });
    } else {
        // 允许其他情况的正常路由导航
        next();
    }
});
export default router