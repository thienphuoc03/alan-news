import axios from 'axios';

const baseUrl = process.env.SERVER_URL;

class PostService {
  async getAllPost(page, size, sortBy) {
    try {
      const response = await axios.get(`${baseUrl}/posts`, {
        params: {
          page: page,
          size: size,
          sortBy: sortBy,
        },
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async getPostById(id) {
    try {
      const response = await axios.get(`${baseUrl}/posts/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async getPostByCategoryId(id) {
    try {
      const response = await axios.get(`${baseUrl}/posts/category/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async addPost(post) {
    try {
      const response = await axios.post(`${baseUrl}/posts`, {
        title: post.title,
        thumbnail: post.thumbnail,
        description: post.description,
        body: post.body,
        categoryId: post.categoryId,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async updatePost(id, post) {
    try {
      const response = await axios.put(`${baseUrl}/posts/${id}`, {
        title: post.title,
        thumbnail: post.thumbnail,
        description: post.description,
        body: post.body,
        categoryId: post.categoryId,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async deletePost(id) {
    try {
      const response = await axios.delete(`${baseUrl}/posts/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }
}

export default new PostService();
