import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import HelloWorld from '../components/HelloWorld.vue';
import News from '../components/News.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    children: [
      { path: '', name: 'home', component: HelloWorld },
      { path: '/news', name: 'news', component: News },
      { path: '/about', name: 'about', component: AboutView },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
