import axios from 'axios';
import TokenService from './refresh.token';

class AuthServices {
  async login(user) {
    return await axios
      .post('/auth/signin', {
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
    return await axios.post('/auth/signup', {
      userName: user.userName,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      phone: user.phone,
      password: user.password,
      typeUser: { id: user.typeUserId },
    });
  }
}

export default new AuthServices();
