<template>
  <v-container>
    <!-- Breadcrumbs -->
    <Breadcrumb :crumbs="crumbs" />

    <!-- Main -->
    <div class="about">
      <h1>This is an about page</h1>
    </div>
  </v-container>
</template>

<script>
import Breadcrumb from '../../components/user/Breadcrumb.vue';

export default {
  components: { Breadcrumb },
  data: () => ({
    crumbs: [
      {
        title: 'Home',
        disabled: false,
        href: '/',
      },

      {
        title: 'About',
        disabled: false,
        href: '/about',
      },
    ],

    // breadcrumbs: [
    //   {
    //     title: 'Home',
    //     disabled: false,
    //     href: '/',
    //   },

    //   {
    //     title: 'About',
    //     disabled: false,
    //     href: '/about',
    //   },
    // ],
  }),
};
</script>
