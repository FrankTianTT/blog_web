import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login'),
    meta: {
      title: "请登录"
    }
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      title: "首页"
    }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/Register'),
    meta: {
      title: "注册新用户"
    }
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/views/admin/Admin'),
    redirect: "/admin/userManage",
    meta: {
      title: "后台管理"
    },
    children: [
      {
        path: '/admin/userManage',
        name: 'UserManage',
        component: () => import('@/views/admin/children/UserManage')
      },
      {
        path: '/admin/articleManage',
        name: 'ArticleManage',
        component: () => import('@/views/admin/children/ArticleManage')
      }, {
        path: '/admin/commentManage',
        name: 'CommentManage',
        component: () => import('@/views/admin/children/CommentManage')
      }
    ]
  },
  {
    path: "/user",
    name: "Layout",
    component: () => import("@/views/Layout"),
    children: [
      {
        path: '/user/blogHome',
        name: 'BlogHome',
        component: () => import("@/views/user/BlogHome"),
        meta: {
          title: "个人主页"
        }
      },
      {
        path: '/user/blogWrite',
        name: 'BlogWrite',
        component: () => import("@/views/user/BlogWrite"),
        meta: {
          title: "写文章"
        }
      }
    ]
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
