<template>
  <v-container max-height="92%" class="h-92">
    <div class="text-right">
      <v-btn color="success" @click="$router.push('/dashboard/user-create')">
        <v-icon>mdi-plus</v-icon>
        Add New
      </v-btn>
    </div>
    <v-table class="my-4">
      <thead>
        <tr class="bg-blue-grey-lighten-4">
          <th>ID</th>
          <th>Avatar</th>
          <th>Username</th>
          <th>Full Name</th>
          <th>Date of Birth</th>
          <th>Gender</th>
          <th>Phone Number</th>
          <th>Email</th>
          <th>Address</th>
          <th>Post Count</th>
          <th>isActive</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id" class="text-left">
          <td>{{ user.id }}</td>
          <td>
            <v-img :src="user.avatar"></v-img>
          </td>
          <td>{{ user.username }}</td>
          <td>{{ user.firstname }} {{ user.lastname }}</td>
          <td>{{ user.dob }}</td>
          <td>{{ user.gender }}</td>
          <td>{{ user.phoneNumber }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.address }}</td>
          <td>{{ user.postCount }}</td>
          <td>
            <v-combobox
              v-model="selectedAccount"
              :items="user.isActive"
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
              <v-btn @click="deleteUser(user.id)">
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
import UserServices from '../../../services/UserServices';

export default {
  data: () => ({
    selectedActive: null,
    users: [],
    page: 1,
    size: 10,
    sortBy: 'createAt',
    totalPages: 0,
    totalElements: 0,
  }),
  mounted() {
    this.getAllUser();
  },
  methods: {
    async getAllUser() {
      UserServices.getAllUser(this.page, this.size, this.sortBy)
        .then(response => {
          this.users = response.data.content;
          this.page = response.data.page;
          this.size = response.data.size;
          this.totalElements = response.data.totalElements;
          this.totalPages = response.data.totalPages;
        })
        .catch(error => {
          console.log(error);
        });
    },

    async deleteUser(id) {
      UserServices.deleteUser(id)
        .then(() => {
          alert('User deleted successfully!!!');
          this.getAllUser();
        })
        .catch(error => {
          alert('Lỗi!!!');
          console.log(error);
        });
    },
  },
};
</script>
