import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import HelloWorld from '../components/HelloWorld.vue';
import NewsView from '../views/NewsView.vue';
import NewsDetailView from '../views/NewsDetailView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [
      { path: '', name: 'home', component: HelloWorld },
      { path: '/news', name: 'news', component: NewsView },
      { path: '/about', name: 'about', component: AboutView },
      { path: '/news/1', name: 'newsDetail', component: NewsDetailView },
    ],
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
