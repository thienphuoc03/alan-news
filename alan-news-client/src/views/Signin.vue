<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="10">
        <v-card class="elevation-6 my-auto">
          <v-window v-model="step">
            <!-- Sign In -->
            <v-window-item :value="1">
              <v-row>
                <v-col cols="12" md="6">
                  <v-card-text class="mt-12">
                    <h1 class="text-center mb-2">Sign in to Your Account</h1>
                    <h5 class="text-center text-textColor">
                      Sign in to your account so you can continue builiding
                      <br />and editing your onboarding flows
                    </h5>
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-form
                          v-model="formSignin"
                          @submit.prevent="handleLogin"
                        >
                          <v-text-field
                            v-model="username"
                            label="Username"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            class="mt-16"
                            :counter="20"
                            :rules="[rules.required, rules.min]"
                            clearable
                          />
                          <v-text-field
                            v-model="password"
                            label="Password"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            :rules="[rules.required, rules.min]"
                            clearable
                            :type="show1 ? 'text' : 'password'"
                            :append-inner-icon="
                              show1 ? 'mdi-eye' : 'mdi-eye-off'
                            "
                            @click:append="show1 = !show1"
                          />
                          <v-row>
                            <v-col cols="12" sm="7">
                              <v-checkbox
                                label="Remember Me"
                                class="mt-n1"
                                color="blue"
                              >
                              </v-checkbox>
                            </v-col>
                            <v-col cols="12" sm="5">
                              <span class="caption blue--text"
                                ><a href="#">Forgot password</a></span
                              >
                            </v-col>
                          </v-row>
                          <v-btn
                            :disabled="!formSignin"
                            color="blue"
                            dark
                            block
                            tile
                            >Sign in</v-btn
                          >

                          <h5 class="text-center grey--text mt-4 mb-3">
                            Or Log in using
                          </h5>
                          <div
                            class="d-flex justify-space-between align-center mx-10 mb-16"
                          >
                            <v-btn icon>
                              <i class="fa-brands fa-google text-red"></i>
                            </v-btn>
                            <v-btn icon>
                              <i class="fa-brands fa-facebook text-blue"></i>
                            </v-btn>
                            <v-btn icon>
                              <i
                                class="fa-brands fa-twitter text-light-blue"
                              ></i>
                            </v-btn>
                          </div>
                        </v-form>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
                <v-col cols="12" md="6" class="bg-blue rounded-bl-xl">
                  <div style="text-align: center; padding: 180px 0">
                    <v-card-text class="white--text">
                      <h3 class="text-center">Don't Have an Account Yet?</h3>
                      <h6 class="text-center">
                        Let's get you all set up so you can start creating your
                        your first<br />
                        onboarding experience
                      </h6>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="step++">SIGN UP</v-btn>
                    </div>
                    <div>
                      <p class="my-3">Or</p>
                      <v-btn icon @click="$router.push('/')">
                        <i class="fa-solid fa-house"></i>
                      </v-btn>
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-window-item>

            <!-- Sign Up -->
            <v-window-item :value="2">
              <v-row>
                <v-col cols="12" md="6" class="bg-blue rounded-br-xl">
                  <div style="text-align: center; padding: 180px 0">
                    <v-card-text class="white--text">
                      <h3 class="text-center">Already Signed up?</h3>
                      <h6 class="text-center">
                        Sign in to your account so you can continue building
                        and<br />
                        editing your onboarding flows
                      </h6>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="step--">Sign in</v-btn>
                    </div>
                  </div>
                </v-col>

                <v-col cols="12" md="6">
                  <v-card-text class="mt-12">
                    <h1 class="text-center mb-2">Sign Up for an Account</h1>
                    <h5 class="text-center grey--text">
                      Let's get you all set up so you can start creating your
                      <br />
                      first onboarding experiance
                    </h5>
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-form
                          v-model="formSignup"
                          @submit.prevent="handleRegister"
                        >
                          <v-row>
                            <v-col cols="12" sm="6">
                              <v-text-field
                                v-model="firstname"
                                label="First Name"
                                outlined
                                dense
                                color="blue"
                                autocomplete="false"
                                class="mt-4"
                                :counter="20"
                                :rules="[rules.required, rules.min]"
                                clearable
                              />
                            </v-col>
                            <v-col cols="12" sm="6">
                              <v-text-field
                                v-model="lastname"
                                label="Last Name"
                                outlined
                                dense
                                color="blue"
                                autocomplete="false"
                                class="mt-4"
                                :counter="20"
                                :rules="[rules.required, rules.min]"
                                clearable
                              />
                            </v-col>
                          </v-row>
                          <v-text-field
                            v-model="username_signup"
                            label="Username"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            :counter="20"
                            :rules="[rules.required, rules.min]"
                            clearable
                          />
                          <v-text-field
                            v-model="password_signup"
                            label="Password"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            :rules="[rules.required, rules.min]"
                            clearable
                            :type="show2 ? 'text' : 'password'"
                            :append-inner-icon="
                              show2 ? 'mdi-eye' : 'mdi-eye-off'
                            "
                            @click:append="show2 = !show2"
                          />
                          <v-text-field
                            v-model="repassword"
                            label="RePassword"
                            outlined
                            dense
                            color="blue"
                            autocomplete="false"
                            :rules="[rules.required, rules.min]"
                            clearable
                            :type="show3 ? 'text' : 'password'"
                            :append-inner-icon="
                              show3 ? 'mdi-eye' : 'mdi-eye-off'
                            "
                            @click:append="show3 = !show3"
                          />
                          <v-row>
                            <v-col cols="12" sm="7">
                              <v-checkbox
                                label="I Accept AAE"
                                class="mt-n1"
                                color="blue"
                              >
                              </v-checkbox>
                            </v-col>
                            <v-col cols="12" sm="5">
                              <span class="caption blue--text ml-n4"
                                >Terms &Conditions</span
                              >
                            </v-col>
                          </v-row>
                          <v-btn
                            :disabled="!formSignup"
                            color="blue"
                            dark
                            block
                            tile
                            >Sign up</v-btn
                          >

                          <h5 class="text-center grey--text mt-4 mb-3">
                            Or Sign up using
                          </h5>
                          <div
                            class="d-flex justify-space-between align-center mx-10 mb-11"
                          >
                            <v-btn icon>
                              <i class="fa-brands fa-google text-red"></i>
                            </v-btn>
                            <v-btn icon>
                              <i class="fa-brands fa-facebook text-blue"></i>
                            </v-btn>
                            <v-btn icon>
                              <i
                                class="fa-brands fa-twitter text-light-blue"
                              ></i>
                            </v-btn>
                          </div>
                        </v-form>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-window-item>
          </v-window>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    step: 1,
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    username_signup: '',
    password_signup: '',
    formSignin: false,
    formSignup: false,
    show1: false,
    show2: false,
    show3: false,
    rules: {
      required: value => !!value || 'Field is required.',
      min: v => v.length >= 6 || 'Min 6 characters',
    },
  }),
  computed: {
    currentUser() {
      return this.$store.getters.isLoggedIn;
    },
  },
  mounted() {},
  methods: {
    async handleLogin() {
      if (!this.form) return;
      let user = {
        username: this.username,
        password: this.password,
      };
      this.$store
        .dispatch('/auth/signin', user)
        .then(() => {
          this.$router.push('/');
        })
        .catch(() => {
          alert('Incorrect Username or Password');
        });
    },

    async handleRegister() {
      let user = {
        firstname: this.firstname,
        lastname: this.lastname,
        username: this.username_signup,
        password: this.password,
      };
      this.$store
        .dispatch('/auth/signup', user)
        .then(() => {
          this.$router.push('/');
        })
        .catch(() => {
          alert('Username already exists');
        });
    },
  },
};
</script>
<style scoped>
.v-application .rounded-bl-xl {
  border-bottom-left-radius: 300px !important;
}
.v-application .rounded-br-xl {
  border-bottom-right-radius: 300px !important;
}
</style>
