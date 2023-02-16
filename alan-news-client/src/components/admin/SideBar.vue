<template>
  <v-navigation-drawer
    expand-on-hover
    rail
    rail-width="120"
    app
    style="position: fixed"
  >
    <v-list>
      <v-list-item
        prepend-avatar="https://randomuser.me/api/portraits/women/85.jpg"
        title="Admin Admin"
        subtitle="admin@gmail.com"
      ></v-list-item>
    </v-list>

    <v-divider></v-divider>

    <v-list density="compact" nav class="text-left">
      <v-list-item
        prepend-icon="mdi-monitor-dashboard"
        title="Dashboard"
        value="Dashboard"
        @click="$router.push('/dashboard')"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-chart-bar"
        title="Statistical"
        value="statistical"
      ></v-list-item>

      <v-divider></v-divider>

      <v-list-item
        prepend-icon="mdi-table-account"
        title="User Management"
        value="users-management"
        @click="$router.push('/users')"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-table-large-plus"
        title="Cate Managementgories"
        value="categories-management"
        @click="$router.push('/categories')"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-table-edit"
        title="Post Managements"
        value="posts-management"
        @click="$router.push('/posts')"
      ></v-list-item>

      <v-divider></v-divider>

      <v-list-item
        prepend-icon="mdi-account-box"
        title="Profile"
        value="profile"
      ></v-list-item>
      <v-list-item
        prepend-icon="mdi-cog"
        title="Setting"
        value="setting"
      ></v-list-item>
    </v-list>

    <template #append>
      <div class="pa-1">
        <v-btn
          block
          class="bg-teal-lighten-2 text-white"
          @click="$router.push('/auth/signin')"
        >
          <v-icon icon="mdi-logout-variant" class="pr-5"></v-icon>
          Logout
        </v-btn>
      </div>
    </template>
  </v-navigation-drawer>
</template>
