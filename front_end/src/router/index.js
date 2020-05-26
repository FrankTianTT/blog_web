import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login'),
    meta:{
      title:"请登录"
    }
  },
  {
    path:'/',
    name:'Home',
    redirect:'/login',
    meta:{
      title:"首页"
    }
  },
  {
    path:'/register',
    name:'register',
    component:()=>import('@/views/Register'),
    meta:{
      title:"注册新用户"
    }
  },
  {
    path:'/admin',
    name:'admin',
    component:()=>import('@/views/Admin'),
    meta:{
      title:"后台管理"
    }
  },
  {
    path:'/blogHome',
    name:'blogHome',
    component:()=>import("@/views/BlogHome"),
    meta:{
      title:"个人主页"
    }
  }
]

const createRouter = () => new VueRouter({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes
})
const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}


export default router
