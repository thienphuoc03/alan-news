<template>
  <v-container>
    <Breadcrumb :crumbs="crumbs" />

    <LatestNews />

    <v-divider></v-divider>

    <Remarkable />

    <v-divider></v-divider>
  </v-container>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';
import LatestNews from './LatestNews.vue';
import logo from '../assets/logo.svg';
import Remarkable from './Remarkable.vue';

export default {
  name: 'HelloWorld',

  components: { Breadcrumb, LatestNews, Remarkable },
  data: () => ({
    crumbs: [
      {
        title: 'Home',
        disabled: false,
        href: '/',
      },
    ],

    ecosystem: [
      {
        text: 'vuetify-loader',
        href: 'https://github.com/vuetifyjs/vuetify-loader/tree/next',
      },
      {
        text: 'github',
        href: 'https://github.com/vuetifyjs/vuetify/tree/next',
      },
      {
        text: 'awesome-vuetify',
        href: 'https://github.com/vuetifyjs/awesome-vuetify',
      },
    ],
    importantLinks: [
      {
        text: 'Chat',
        href: 'https://community.vuetifyjs.com',
      },
      {
        text: 'Made with Vuetify',
        href: 'https://madewithvuejs.com/vuetify',
      },
      {
        text: 'Twitter',
        href: 'https://twitter.com/vuetifyjs',
      },
      {
        text: 'Articles',
        href: 'https://medium.com/vuetify',
      },
    ],
    logo,
    whatsNext: [
      {
        text: 'Explore components',
        href: 'https://vuetifyjs.com',
      },
      {
        text: 'Roadmap',
        href: 'https://vuetifyjs.com/introduction/roadmap/',
      },
      {
        text: 'Frequently Asked Questions',
        href: 'https://vuetifyjs.com/getting-started/frequently-asked-questions',
      },
    ],
  }),
};
</script>
