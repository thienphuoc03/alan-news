<template>
  <v-container>
    <Breadcrumb :crumbs="crumbs" />

    <!-- Begin Latest News -->
    <v-card flat>
      <v-container class="d-flex justify-center bg-white">
        <!-- slides -->
        <v-carousel cycle hide-delimiters show-arrows="hover" class="w-50 mr-3">
          <v-carousel-item v-for="(slide, i) in slides" :key="i">
            <v-sheet>
              <v-card style="cursor: pointer" @click="$router.push('/about')">
                <v-img
                  title="Lần đầu trải nghiệm Bing mới tích hợp ChatGPT, kết quả thật bất
              ngờ"
                  cover
                  src="https://genk.mediacdn.vn/zoom/540_336/139269124445442048/2023/2/10/avatar1676018337557-1676018338032137192370.jpg"
                  max-height="80%"
                ></v-img>

                <v-card-subtitle class="text-left">Công nghệ</v-card-subtitle>

                <p
                  class="text-h6 d-inline-block text-left px-2 py-2"
                  height="auto"
                  width="auto"
                  style="word-break: break-word"
                  title="Lần đầu trải nghiệm Bing mới tích hợp ChatGPT, kết quả thật bất
              ngờ"
                >
                  Lần đầu trải nghiệm Bing mới tích hợp ChatGPT, kết quả thật
                  bất ngờ
                </p>
              </v-card>
            </v-sheet>
          </v-carousel-item>
        </v-carousel>

        <v-card flat class="w-50 ml-3 text-left">
          <v-row dense>
            <v-col v-for="card in cards1" :key="card.title" :cols="card.flex">
              <v-card>
                <v-img
                  :src="card.src"
                  class="align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="200px"
                  cover
                >
                </v-img>

                <v-card-subtitle class="text-left">Công nghệ</v-card-subtitle>

                <p class="px-2 py-2">{{ card.title }}</p>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
      </v-container>
    </v-card>
    <!-- End Latest News -->

    <v-divider></v-divider>

    <!-- Begin Remarkable -->
    <v-container class="bg-white">
      <span
        class="text-left text-h6 text-decoration-underline d-block text-uppercase text-textColor"
        >ĐÁNG CHÚ Ý</span
      >

      <v-row no-gutters>
        <v-col v-for="card in cards2" :key="card.title" class="ma-2 pa-2">
          <v-sheet>
            <v-card>
              <v-img
                :src="card.src"
                class="align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
                cover
              >
              </v-img>

              <v-card-subtitle class="text-left">Công nghệ</v-card-subtitle>

              <p class="px-2 py-2 text-left">{{ card.title }}</p>
            </v-card>
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>
    <!-- End Remarkable -->

    <v-divider></v-divider>

    <!-- Begin General News -->
    <v-container>
      <v-row>
        <v-col cols="6">
          <v-sheet>
            <v-card class="text-left my-3">
              <v-card-title class="d-flex justify-space-between align-center">
                Công nghệ

                <a
                  class="text-body-2 text-right"
                  style="cursor: pointer"
                  title="Công nghệ"
                  >Xem tất cả >>></a
                >
              </v-card-title>

              <v-divider></v-divider>

              <div v-for="card in cards2" :key="card.id">
                <v-divider inset></v-divider>

                <v-card
                  flat
                  class="w-100 text-left d-flex justify-center align-center px-3"
                >
                  <v-img :src="card.src" width="150" height="120"> </v-img>

                  <div>
                    <v-card-item>
                      <p>{{ card.title }}</p>

                      <v-card-subtitle>{{ card.categori }}</v-card-subtitle>
                    </v-card-item>

                    <v-card-text> {{ card.description }} </v-card-text>
                  </div>
                </v-card>
              </div>
            </v-card>
          </v-sheet>
        </v-col>

        <v-col cols="6">
          <v-card class="text-left my-3" max-width="600">
            <v-card-title class="d-flex justify-space-between align-center">
              Thủ Thuật

              <a
                class="text-body-2 text-right"
                style="cursor: pointer"
                title="Công nghệ"
                >Xem tất cả >>></a
              >
            </v-card-title>

            <v-divider></v-divider>

            <div v-for="card in cards2" :key="card.id">
              <v-divider inset></v-divider>

              <v-card
                flat
                class="w-100 text-left d-flex justify-center align-center px-3"
              >
                <v-img :src="card.src" width="150" height="120"> </v-img>

                <div>
                  <v-card-item>
                    <p>{{ card.title }}</p>

                    <v-card-subtitle>{{ card.categori }}</v-card-subtitle>
                  </v-card-item>

                  <v-card-text> {{ card.description }} </v-card-text>
                </div>
              </v-card>
            </div>
          </v-card>
        </v-col>

        <v-col cols="8">
          <v-card class="text-left my-3" max-width="600">
            <v-card-title class="d-flex justify-space-between align-center">
              Đời sống

              <a
                class="text-body-2 text-right"
                style="cursor: pointer"
                title="Đời sống"
                >Xem tất cả >>></a
              >
            </v-card-title>

            <v-divider></v-divider>

            <div v-for="card in cards2" :key="card.id">
              <v-divider inset></v-divider>

              <v-card
                flat
                class="w-100 text-left d-flex justify-center align-center px-3"
              >
                <v-img :src="card.src" width="150" height="120"> </v-img>

                <div>
                  <v-card-item>
                    <p>{{ card.title }}</p>

                    <v-card-subtitle>{{ card.categori }}</v-card-subtitle>
                  </v-card-item>

                  <v-card-text> {{ card.description }} </v-card-text>
                </div>
              </v-card>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

    <!-- End General News -->
  </v-container>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';

export default {
  name: 'HelloWorld',

  components: { Breadcrumb },
  data: () => ({
    crumbs: [
      {
        title: 'Home',
        disabled: false,
        href: '/',
      },
    ],

    ecosystem: [
      {
        text: 'vuetify-loader',
        href: 'https://github.com/vuetifyjs/vuetify-loader/tree/next',
      },
      {
        text: 'github',
        href: 'https://github.com/vuetifyjs/vuetify/tree/next',
      },
      {
        text: 'awesome-vuetify',
        href: 'https://github.com/vuetifyjs/awesome-vuetify',
      },
    ],
    importantLinks: [
      {
        text: 'Chat',
        href: 'https://community.vuetifyjs.com',
      },
      {
        text: 'Made with Vuetify',
        href: 'https://madewithvuejs.com/vuetify',
      },
      {
        text: 'Twitter',
        href: 'https://twitter.com/vuetifyjs',
      },
      {
        text: 'Articles',
        href: 'https://medium.com/vuetify',
      },
    ],
    whatsNext: [
      {
        text: 'Explore components',
        href: 'https://vuetifyjs.com',
      },
      {
        text: 'Roadmap',
        href: 'https://vuetifyjs.com/introduction/roadmap/',
      },
      {
        text: 'Frequently Asked Questions',
        href: 'https://vuetifyjs.com/getting-started/frequently-asked-questions',
      },
    ],
    colors: [
      'indigo',
      'warning',
      'pink darken-2',
      'red lighten-1',
      'deep-purple accent-4',
    ],
    slides: ['First', 'Second', 'Third', 'Fourth', 'Fifth'],
    cards1: [
      {
        title:
          'Một cách nhìn đơn giản, một cách hiểu phổ thông hơn về AI, máy học và mạng neuron',
        src: 'https://genk.mediacdn.vn/zoom/260_162/2017/photo1512994990478-1512994990495.jpg',
        flex: 12,
      },
      {
        title:
          'Trò chuyện với Chủ tịch trẻ nhất của Samsung về Chat GPT, những chiếc Galaxy S23 làm từ lưới đánh cá và sứ mệnh mới của tập đoàn',
        src: 'https://genk.mediacdn.vn/zoom/80_50/139269124445442048/2023/2/11/ngang-1676125030466806871909.jpg',
        flex: 6,
      },
      {
        title:
          'So sánh Google và ChatGPT, đâu là ứng dụng tìm kiếm tốt nhất hiện nay?',
        src: 'https://genk.mediacdn.vn/zoom/80_50/139269124445442048/2023/2/12/avatar1676180764537-1676180770990776165753.jpg',
        flex: 6,
      },
    ],
    cards2: [
      {
        title:
          'Một cách nhìn đơn giản, một cách hiểu phổ thông hơn về AI, máy học và mạng neuron',
        src: 'https://genk.mediacdn.vn/zoom/260_162/2017/photo1512994990478-1512994990495.jpg',
      },
      {
        title:
          'Trò chuyện với Chủ tịch trẻ nhất của Samsung về Chat GPT, những chiếc Galaxy S23 làm từ lưới đánh cá và sứ mệnh mới của tập đoàn',
        src: 'https://genk.mediacdn.vn/zoom/80_50/139269124445442048/2023/2/11/ngang-1676125030466806871909.jpg',
      },
      {
        title:
          'So sánh Google và ChatGPT, đâu là ứng dụng tìm kiếm tốt nhất hiện nay?',
        src: 'https://genk.mediacdn.vn/zoom/80_50/139269124445442048/2023/2/12/avatar1676180764537-1676180770990776165753.jpg',
      },
      {
        title:
          'So sánh Google và ChatGPT, đâu là ứng dụng tìm kiếm tốt nhất hiện nay? 1',
        src: 'https://genk.mediacdn.vn/zoom/80_50/139269124445442048/2023/2/11/ngang-1676125030466806871909.jpg',
      },
    ],
  }),
};
</script>
