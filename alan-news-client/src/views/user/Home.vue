<template>
  <Header />
  <v-main class="home d-flex">
    <router-view />
  </v-main>
  <Footer />
</template>

<script>
// @ is an alias to /src
import Header from '@/components/user/Header.vue';
import Footer from '@/components/user/Footer.vue';

export default {
  name: 'HomeView',
  components: {
    Header,
    Footer,
  },
};
</script>
