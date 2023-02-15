<template>
  <v-container class="bg-white my-6">
    <Breadcrumb :crumbs="crumbs" />

    <v-card class="text-left px-6 py-6">
      <div class="header-news">
        <span class="text-h6">
          {{ post.title }}
        </span>

        <div class="d-flex justify-start align-center text-caption">
          <span class="text-red">post.createdBy</span>
          <v-icon
            icon="mdi-clock-outline"
            size="20"
            color="gray"
            class="ml-2"
          ></v-icon>
          <p class="text-textColor">post.createdAt</p>
        </div>

        <div class="d-flex justify-end text-caption">
          <v-btn class="rounded-pill">
            <v-icon icon="mdi-play" class="mr-1"></v-icon> Đọc bài - 4:16
          </v-btn>
        </div>
      </div>

      <v-divider class="my-6"></v-divider>

      <div class="content-news">
        {{ post.body }}
      </div>
    </v-card>

    <v-card class="mt-4 text-left">
      <v-card-title class="d-flex justify-start align-center text-h5 pt-4">
        <v-icon icon="mdi-drag-vertical" color="red"></v-icon>
        TIN CÙNG CHUYÊN MỤC
      </v-card-title>

      <v-row>
        <v-col v-for="item in posts" :key="item.id" cols="12" sm="4">
          <v-sheet class="ma-2 pa-2">
            <v-card
              style="cursor: pointer"
              @click="$router.push(`/news/${item.id}`)"
            >
              <v-img
                :title="item.title"
                cover
                :src="item.thumbnail"
                max-height="80%"
              ></v-img>

              <v-card-subtitle class="text-left">Công nghệ</v-card-subtitle>

              <p
                class="text-h6 d-inline-block text-left px-2 py-2"
                height="auto"
                width="auto"
                style="word-break: break-word"
                :title="item.title"
              >
                {{ item.title }}
              </p>
            </v-card>
          </v-sheet>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>
<script>
import Breadcrumb from '../../components/user/Breadcrumb.vue';
import PostServices from '../../services/PostServices';

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
        title: 'Internet',
        disabled: false,
        href: '/news',
      },
    ],

    post: [],
    posts: [],
  }),
  mounted() {
    this.getPostById();
    this.getPostByCategoryId();
  },
  methods: {
    async getPostById() {
      PostServices.getPostById(this.$route.params.id)
        .then(response => {
          this.post = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },

    async getPostByCategoryId(id) {
      PostServices.getPostByCategoryId(id)
        .then(response => {
          this.posts = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped></style>
