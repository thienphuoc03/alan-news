import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/user/Home.vue';
import About from '../views/user/About.vue';
import Index from '../components/user/Index.vue';
import News from '../views/user/News.vue';
import NewsDetail from '../views/user/NewsDetail.vue';
import Signin from '../views/user/Signin.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    children: [
      { path: '', name: 'home', component: Index },
      { path: '/news', name: 'news', component: News },
      { path: '/about', name: 'about', component: About },
      { path: '/news/:id', name: 'newsDetail', component: NewsDetail },
    ],
  },

  {
    path: '/auth/signin',
    name: 'signin',
    component: Signin,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// router.beforeEach((to, from, next) => {
//   let publicPages = ['/auth/login', '/'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = JSON.parse(localStorage.getItem('user'));

//   const role = localStorage.getItem('user').role;
//   if (loggedIn != null && (role === 'ROLE_ADMIN' || role === 'ROLE_MOD')) {
//     publicPages = ['/dashboard', '/dashboard/profile'];
//   } else if (loggedIn != null && role === 'ROLE_USER') {
//     publicPages = ['/', '/news', '/about'];
//   }
//   if (authRequired && !loggedIn) {
//     next('/');
//   } else {
//     next();
//   }
// });

export default router;
