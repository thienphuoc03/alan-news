<template>
  <v-app-bar
    color="teal-lighten-2"
    flat
    class="w-100"
    app
    style="position: fixed"
  >
    <v-app-bar-title
      class="font-weight-bold text-uppercase"
      v-text="$route.name"
    ></v-app-bar-title>

    <v-spacer></v-spacer>

    <v-text-field
      :loading="loading"
      density="compact"
      variant="solo"
      label="Search templates"
      append-inner-icon="mdi-magnify"
      single-line
      hide-details
      max-width="100"
      @click:append-inner="onClick"
    ></v-text-field>

    <div class="mx-3"></div>

    <v-btn icon>
      <v-icon>mdi-bell</v-icon>
    </v-btn>

    <v-btn icon>
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: 'AppBar',

  props: {
    value: {
      type: Boolean,
      default: false,
    },
  },

  data: () => ({
    notifications: [
      'Mike John Responded to your email',
      'You have 5 new tasks',
      "You're now friends with Andrew",
      'Another Notification',
      'Another one',
    ],
    loaded: false,
    loading: false,
  }),
  methods: {
    onClick() {
      this.loading = true;

      setTimeout(() => {
        this.loading = false;
        this.loaded = true;
      }, 2000);
    },
  },
};
</script>
