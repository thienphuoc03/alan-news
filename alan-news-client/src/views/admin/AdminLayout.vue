<template>
  <v-layout class="d-flex justify-center">
    <SideBar />

    <v-main>
      <AppBar />
      <router-view />
      <FooterAdmin />
    </v-main>
  </v-layout>
</template>
<script>
import SideBar from '../../components/admin/SideBar.vue';
import FooterAdmin from '../../components/admin/FooterAdmin.vue';
import AppBar from '../../components/admin/AppBar.vue';

export default { components: { SideBar, FooterAdmin, AppBar } };
</script>
