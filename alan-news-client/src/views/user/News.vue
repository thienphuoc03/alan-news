<template>
  <v-container>
    <Breadcrumb :crumbs="crumbs" />

    <span
      class="text-left text-h6 text-decoration-underline d-block text-uppercase text-textColor"
      style="cursor: pointer"
      ><a>Công nghệ</a></span
    >

    <div v-for="post in posts" :key="post.id">
      <v-divider></v-divider>

      <v-card
        flat
        class="w-100 text-left d-flex justify-center align-center my-3"
        @click="$router.push(`/news/${post.id}`)"
      >
        <v-img :src="post.thumbnail" width="300" height="200"> </v-img>

        <div>
          <v-card-item>
            <v-card-title>{{ post.title }}</v-card-title>

            <v-card-subtitle>{{ post.category }}</v-card-subtitle>
          </v-card-item>

          <v-card-text> {{ post.description }} </v-card-text>
        </div>
      </v-card>
    </div>

    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                v-model="page"
                class="my-4"
                :length="posts.totalPages"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-container>
</template>

<script>
import Breadcrumb from '../../components/user/Breadcrumb.vue';
import PostServices from '../../services/PostServices';

export default {
  components: {
    Breadcrumb,
  },
  data: () => ({
    crumbs: [
      {
        title: 'Home',
        disabled: false,
        href: '/',
      },
      {
        title: 'News',
        disabled: false,
        href: '/news',
      },
    ],

    posts: [],

    page: 1,
    size: 10,
    sortBy: 'createAt',
  }),

  methods: {
    async getAllPost() {
      PostServices.getAllPost(this.page, this.size, this.sortBy)
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
