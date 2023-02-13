import { createApp } from 'vue';
import App from './App.vue';

// plugins
import vuetify from './plugins/vuetify';

/* import font awesome icon component */
import '@fortawesome/fontawesome-free/css/all.css';
loadFonts();

import { loadFonts } from './plugins/webfontloader';

import router from './router';
import store from './store';

import './assets/styles/main.css';

const app = createApp(App).use(store);

app.use(router).use(vuetify).mount('#app');
