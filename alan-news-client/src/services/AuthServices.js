import axios from 'axios';
import TokenService from './refresh.token';

const baseUrl = process.env.SERVER_URL;

class AuthServices {
  async login(user) {
    return await axios
      .post(`${baseUrl}/auth/signin`, {
        userName: user.userName,
        password: user.password,
      })
      .then(response => {
        TokenService.setUser(response.data);
        return response.data;
      });
  }

  logout() {
    TokenService.removeUser();
  }

  async register(user) {
    return await axios.post(`${baseUrl}/auth/signup`, {
      userName: user.userName,
      email: user.email,
      password: user.password,
    });
  }
}

export default new AuthServices();
