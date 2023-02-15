import axios from 'axios';

const baseUrl = process.env.SERVER_URL;

class CategoryService {
  async getAllCategory(page, size, sortBy) {
    try {
      const response = await axios.get(`${baseUrl}/categories`, {
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

  async getCategoryById(id) {
    try {
      const response = await axios.get(`${baseUrl}/categories/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async addCategory(name) {
    try {
      const response = await axios.post(`${baseUrl}/categories`, {
        name: name,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async updateCategory(id, name) {
    try {
      const response = await axios.put(`${baseUrl}/categories/${id}`, {
        name: name,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async deleteCategory(id) {
    try {
      const response = await axios.delete(`${baseUrl}/categories/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }
}

export default new CategoryService();
