<template>
  <v-container>
    <div class="text-uppercase font-weight-bold text-h5">New User</div>

    <div class="mt-4">
      <v-sheet class="mx-auto">
        <v-form @submit.prevent>
          <v-row no-gutters>
            <v-col cols="12" md="7">
              <v-sheet class="pa-2 ma-2">
                <div>
                  <v-file-input
                    v-model="selectedFile"
                    label="Choose an image"
                    prepend-icon="mdi-camera"
                  ></v-file-input>
                  <div v-if="imageUrl">
                    <img :src="imageUrl" />
                  </div>
                </div>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="6">
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="firstname"
                  :rules="[rules.min, rules.required]"
                  :counter="20"
                  label="First name"
                  required
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col>
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="lastname"
                  :rules="[rules.min, rules.required]"
                  :counter="20"
                  label="Last name"
                  required
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="6">
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="username"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Username"
                  required
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col>
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="password"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Password"
                  required
                  :type="show1 ? 'text' : 'password'"
                  :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  @click:append="show1 = !show1"
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="6">
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="dob"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Date of Birth"
                  required
                  type="date"
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="6">
              <v-sheet class="pa-2 ma-2">
                <v-combobox
                  label="Gender"
                  :items="['Male', 'Female', 'Other']"
                ></v-combobox>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="6">
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="phoneNumber"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Phone Number"
                  required
                  type="number"
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col>
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="email"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Email"
                  required
                  type="email"
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="12">
              <v-sheet class="pa-2 ma-2">
                <v-text-field
                  v-model="address"
                  :rules="[rules.required]"
                  :counter="20"
                  label="Address"
                  required
                ></v-text-field>
              </v-sheet>
            </v-col>

            <v-col cols="12" md="3">
              <v-sheet class="pa-2 ma-2">
                <v-combobox
                  label="Role"
                  :items="['ADMIN', 'MOD', 'USER']"
                ></v-combobox>
              </v-sheet>
            </v-col>
          </v-row>

          <v-btn type="submit" block class="mt-2 bg-success">Submit</v-btn>
        </v-form>
      </v-sheet>
    </div>
  </v-container>
</template>
<script>
import UserServices from '../../../services/UserServices';

export default {
  data: () => ({
    show1: false,
    rules: {
      required: value => !!value || 'Field is required.',
      min: v => v.length >= 6 || 'Min 6 characters',
    },
    selectedFile: null,
    imageUrl: null,
    username: '',
    firstname: '',
    lastname: '',
    password: '',
    dob: '',
    gender: '',
    phoneNumber: '',
    email: '',
    address: '',
    role: '',
    avatar: '',
  }),
  watch: {
    selectedFile(newFile) {
      if (newFile) {
        // Create a URL for the selected file and set it as the image URL
        this.imageUrl = URL.createObjectURL(newFile);
      } else {
        // Clear the image URL if no file is selected
        this.imageUrl = null;
      }
    },
  },
  methods: {
    async AddUser() {
      const user = {
        username: this.username,
        password: this.password,
        firstname: this.firstname,
        lastname: this.lastname,
        dob: this.dob,
        gender: this.gender,
        phoneNumber: this.phoneNumber,
        email: this.email,
        address: this.address,
        role: this.role,
        avatar: this.avatar,
      };
      UserServices.addUser(user)
        .then(this.$snackbar.open('User created successfully'))
        .catch(error => {
          console.log(error);
        });
    },
  },
};
</script>
