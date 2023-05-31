import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import CurtainVib from '../views/CurtainVib.vue'
import CurtainVibCharts from '../views/CurtainVibCharts.vue'
import DamageFilter from '../views/DamageFilter.vue'

Vue.use(VueRouter)

const routes = [
    //主路由
    {
        path: '/',
        component: Main,
        name:'home',
        //redirect: '/login', //重定向
        children: [
            //子路由
            { path: 'home', component: Home },
            { path: 'curtainvib', component: CurtainVib },
            { path: 'curtainvibcharts', component: CurtainVibCharts },
            { path: 'damagefilter', component: DamageFilter },
        ]
    },
    {
        path: '/login',
        name:'login',
        component: Login
    }
]

const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})

export default router
