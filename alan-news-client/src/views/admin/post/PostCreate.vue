<template>
  <v-container>
    <div class="text-uppercase font-weight-bold text-h5">New Post</div>

    <div class="mt-4">
      <v-form ref="form" @submit.prevent="submitForm">
        <v-text-field v-model="title" label="Title" required></v-text-field>
        <v-text-field
          v-model="description"
          label="Description"
          required
          multi-line
        ></v-text-field>
        <v-file-input
          v-model="thumbnail"
          label="Thumbnail"
          accept="image/*"
        ></v-file-input>
        <v-select
          v-model="category"
          :items="categories.name"
          label="Category"
          required
        ></v-select>
        <v-textarea v-model="content" label="Content" required></v-textarea>
        <v-btn type="submit" block class="mt-2 bg-success">Submit</v-btn>
      </v-form>
    </div>
  </v-container>
</template>
<script>
import CategoryServices from '../../../services/CategoryServices';
import PostServices from '../../../services/PostServices';

export default {
  data: () => ({
    title: '',
    description: '',
    thumbnail: null,
    category: '',
    categories: [],
    content: '',
    titleNew: '',
    descriptionNew: '',
    thumbnailNew: null,
    categoryNew: '',
    contentNew: '',
  }),

  methods: {
    async GetAllCategory() {
      CategoryServices.getAllCategory()
        .then(response => {
          this.categories = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },

    async AddPost() {
      const post = {
        title: this.title,
        description: this.description,
        thumbnail: this.thumbnail,
        categoryId: this.category,
        body: this.content,
      };
      PostServices.addPost(post)
        .then(this.$snackbar.open('Post created successfully'))
        .catch(error => {
          console.log(error);
        });
    },

    async UpdatePost(id) {
      const post = {
        title: this.titleNew,
        description: this.descriptionNew,
        thumbnail: this.thumbnailNew,
        categoryId: this.categoryNew,
        body: this.contentNew,
      };
      PostServices.updatePost(id, post)
        .then(() => {
          this.$snackbar.open('Post updated successfully');
          this.$router.push('/dashboard/posts');
        })

        .catch(error => {
          console.log(error);
        });
    },
  },
};
</script>
