<template>
  <v-footer color="teal-lighten-2">
    <div class="px-4 py-2 w-100 text-right">
      {{ new Date().getDate() }}/{{ new Date().getMonth() }}/{{
        new Date().getFullYear()
      }}
      —
      <strong>Vuetify</strong>
    </div>
  </v-footer>
</template>

<script>
export default {
  data: () => ({}),
};
</script>
