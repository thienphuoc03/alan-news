<template>
  <v-app-bar class="d-flex item-center">
    <v-container class="d-flex align-center py-4 item-center">
      <v-app-bar-title class="pl-0">
        <div class="d-flex align-center font-weight-bold">
          <a href="#" class="text-decoration-none">
            <v-avatar
              rounded="0"
              image="https://cdn.vuetifyjs.com/docs/images/logos/v.png"
            />

            Alan News
          </a>
        </div>
      </v-app-bar-title>

      <!-- menu -->
      <Menu />

      <v-spacer></v-spacer>

      <!-- Search input -->
      <v-text-field
        v-model="search"
        :loading="loading"
        density="compact"
        variant="solo"
        label="Search news..."
        append-inner-icon="mdi-magnify"
        single-line
        hide-details
        @click:append-inner="onSearch"
      ></v-text-field>

      <div class="ml-3 d-flex justify-center item-center">
        <!-- Notification icon -->
        <div v-if="isLogin">
          <v-menu open-on-hover location="bottom end">
            <template #activator="{ props }">
              <v-btn icon v-bind="props">
                <v-badge :content="newNotification" color="error">
                  <v-icon icon="mdi-bell" size="small"></v-icon>
                </v-badge>
              </v-btn>
            </template>

            <v-list min-width="200" max-width="300">
              <v-list-item
                v-for="(notification, index) in notifications"
                :key="index"
                class="h-auto text-truncate"
              >
                <v-list-item-title
                  class="text-truncate"
                  :class="[
                    notification.seen === false ? 'bg-blue-lighten-5' : '',
                  ]"
                >
                  <v-btn class="text-caption" @click="setSeen(notification)">
                    {{ notification.content }}
                  </v-btn>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>

        <!-- Account icon -->
        <div class="ml-3">
          <v-menu open-on-hover location="bottom end">
            <template #activator="{ props }">
              <v-btn icon v-bind="props">
                <v-icon icon="mdi-account-circle"></v-icon>
              </v-btn>
            </template>

            <!-- if user is logged -->
            <v-list v-if="isLogin" min-width="150" max-width="200">
              <v-list-item v-for="(account, index) in accounts" :key="index">
                <v-list-item-title>
                  <v-btn class="text-body-2">
                    <v-icon :icon="account.icon" class="mr-2"></v-icon>
                    {{ account.title }}
                  </v-btn>
                </v-list-item-title>
              </v-list-item>
            </v-list>

            <!-- else -->
            <v-list v-else min-width="150" max-width="200">
              <v-list-item>
                <v-list-item-title>
                  <v-btn class="text-body-2">
                    <v-icon icon="mdi-login-variant" class="mr-2"></v-icon>
                    Login
                  </v-btn>
                </v-list-item-title>

                <v-list-item-title>
                  <v-btn class="text-body-2">
                    <v-icon icon="mdi-account-plus" class="mr-2"></v-icon>
                    Register
                  </v-btn>
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </div>
      <v-btn
        :prepend-icon="
          theme === 'light' ? 'mdi-weather-sunny' : 'mdi-weather-night'
        "
        @click="onChangeTheme"
      ></v-btn>
    </v-container>
  </v-app-bar>
</template>

<script>
import Menu from '@/components/Menu.vue';

export default {
  components: {
    Menu,
  },
  data: () => ({
    isLogin: false,
    search: '',
    newNotification: 0,
    loaded: false,
    loading: false,
    theme: 'light',
    accounts: [
      { icon: 'mdi-account', title: 'Profile' },
      { icon: 'mdi-access-point', title: 'Click Me' },
      { icon: 'mdi-access-point', title: 'Click Me' },
      { icon: 'mdi-logout', title: 'Logout' },
    ],
    notifications: [
      {
        content: 'phuoc liked your post',
        seen: false,
      },
      {
        content: 'phuoc commented on your post',
        seen: false,
      },
      {
        content: 'phuoc liked your post',
        seen: true,
      },
      {
        content: 'phuoc commented on your post',
        seen: true,
      },
    ],
  }),
  mounted() {
    this.countNewNotification();
  },
  methods: {
    onChangeTheme() {
      this.theme = this.theme === 'light' ? 'dark' : 'light';
    },

    onSearch() {
      this.loading = true;

      setTimeout(() => {
        this.loading = false;
        this.loaded = true;
        this.search = '';
      }, 2000);
    },

    countNewNotification() {
      this.newNotification = 0;
      this.notifications.map(value => {
        if (value.seen === false) {
          this.newNotification++;
        }
      });
    },

    setSeen(notification) {
      notification.seen = true;
      this.countNewNotification();
    },
  },
};
</script>
