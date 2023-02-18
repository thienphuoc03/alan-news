<template>
  <v-container max-height="92%" class="h-92">
    <div class="text-right">
      <v-btn color="success" @click="$router.push('/dashboard/create-create')">
        <v-icon>mdi-plus</v-icon>
        Add New
      </v-btn>
    </div>
    <v-table class="my-4">
      <thead>
        <tr class="bg-blue-grey-lighten-4">
          <th>ID</th>
          <th>Thumbnail</th>
          <th>Title</th>
          <th>Description</th>
          <th>User Id</th>
          <th>Category</th>
          <th>isActive</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="post in posts" :key="post.id" class="text-left">
          <td>{{ post.id }}</td>
          <td>
            <v-img :src="post.thumbnail"></v-img>
          </td>
          <td>{{ post.title }}</td>
          <td>{{ post.description }}</td>
          <td>{{ post.userId }}</td>
          <td>{{ post.category }}</td>
          <td>
            <v-combobox
              v-model="selectedAccount"
              :items="post.isActive"
              label="Select account"
              :class="selectedAccount.isActive ? 'success' : 'error'"
            >
              <template #selection="{ item }">
                <v-list-item-content
                  :class="item.isActive ? 'success' : 'error'"
                >
                  {{ item.name }}
                </v-list-item-content>
              </template>
            </v-combobox>
          </td>
          <td>
            <div class="d-flex justify-space-around">
              <v-btn>
                <v-icon color="warning"> mdi-pencil </v-icon>
              </v-btn>
              <v-btn>
                <v-icon color="error"> mdi-delete </v-icon>
              </v-btn>
            </div>
          </td>
        </tr>
      </tbody>
    </v-table>

    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                v-model="page"
                class="my-4"
                :length="totalPages"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-container>
</template>

<script>
import PostServices from '../../../services/PostServices';

export default {
  data: () => ({
    selectedActive: null,
    posts: [],
    page: 1,
    size: 10,
    sortBy: 'createAt',
    totalPages: 0,
    totalElements: 0,
  }),
  mounted() {
    this.getAllPost();
  },
  methods: {
    async getAllPost() {
      PostServices.getAllPost(this.page, this.size, this.sortBy)
        .then(response => {
          this.posts = response.data.content;
          this.page = response.data.page;
          this.size = response.data.size;
          this.totalElements = response.data.totalElements;
          this.totalPages = response.data.totalPages;
        })
        .catch(error => {
          console.log(error);
        });
    },
  },
};
</script>
