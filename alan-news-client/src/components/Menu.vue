<template>
  <div v-for="link in menus" :key="link" class="text-center">
    <div v-if="link.title !== 'Tin tức'">
      <v-menu open-on-hover location="bottom end">
        <template #activator="{ props }">
          <v-btn v-bind="props" @click="$router.push(link.href)">
            {{ link.title }}
            <!-- <v-icon icon="mdi-account-circle"></v-icon> -->
          </v-btn>
        </template>
      </v-menu>
    </div>

    <div v-else>
      <v-menu open-on-hover location="bottom end">
        <template #activator="{ props }">
          <v-btn v-bind="props" @click="$router.push(link.href)">
            {{ link.title }}
            <v-icon icon="mdi-chevron-down"></v-icon>
          </v-btn>
        </template>

        <v-list min-width="150" max-width="300">
          <v-list-item v-for="(category, index) in categories" :key="index">
            <v-list-item-title>
              <v-btn class="text-body-2 w-100 d-flex justify-start">
                {{ category.name }}
              </v-btn>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </div>
</template>

<script>
export default {
  data: () => ({
    menus: [
      {
        title: 'Trang chủ',
        disabled: false,
        href: '/',
      },
      {
        title: 'Tin tức',
        disabled: false,
        href: '/news',
      },
      {
        title: 'Về chúng tôi',
        disabled: false,
        href: '/about',
      },
      {
        title: 'Liên hệ',
        disabled: false,
        href: '#',
      },
    ],

    categories: [
      {
        name: 'Mobile',
      },
      {
        name: 'Tin ICT',
      },
      {
        name: 'Internet',
      },
      {
        name: 'Khám phá',
      },
      {
        name: 'Công nghệ',
      },
      {
        name: 'Thủ thuật',
      },
      {
        name: 'Sống',
      },
    ],
  }),
};
</script>
