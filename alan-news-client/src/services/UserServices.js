import axios from 'axios';

const baseUrl = process.env.SERVER_URL;

class UserServices {
  async getAllUser(page, size, sortBy) {
    try {
      const response = await axios.get(`${baseUrl}/users`, {
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

  async getUserById(id) {
    try {
      const response = await axios.get(`${baseUrl}/users/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async getUserProfile(username) {
    try {
      const response = await axios.get(`${baseUrl}/users/${username}/profile`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async getAllPostByUsername(username, page, size, sortBy) {
    try {
      const response = await axios.get(`${baseUrl}/users/${username}/posts`, {
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

  async addUser(user) {
    try {
      const response = await axios.post(`${baseUrl}/users`, {
        firstName: user.firstName,
        lastName: user.lastName,
        username: user.username,
        password: user.password,
        gender: user.gender,
        dob: user.dob,
        phoneNumber: user.phoneNumber,
        email: user.email,
        address: user.address,
        avatar: user.avatar,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async updateUser(id, user) {
    try {
      const response = await axios.put(`${baseUrl}/users/${id}`, {
        firstName: user.firstName,
        lastName: user.lastName,
        username: user.username,
        password: user.password,
        gender: user.gender,
        dob: user.dob,
        phoneNumber: user.phoneNumber,
        email: user.email,
        address: user.address,
        avatar: user.avatar,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async deleteUser(id) {
    try {
      const response = await axios.delete(`${baseUrl}/users/${id}`);

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }

  async giveRole(userId, roleId) {
    try {
      const response = await axios.put(`${baseUrl}/users/give-role`, {
        userId: userId,
        roleId: roleId,
      });

      return Promise.resolve(response);
    } catch (error) {
      console.log('Call API get all error:', error);
      return Promise.reject(error);
    }
  }
}

export default new UserServices();
