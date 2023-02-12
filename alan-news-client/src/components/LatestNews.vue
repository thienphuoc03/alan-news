<template>
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
                Lần đầu trải nghiệm Bing mới tích hợp ChatGPT, kết quả thật bất
                ngờ
              </p>
            </v-card>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>

      <v-card flat class="w-50 ml-3 text-left">
        <v-row dense>
          <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
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
</template>

<script>
export default {
  data: () => ({
    colors: [
      'indigo',
      'warning',
      'pink darken-2',
      'red lighten-1',
      'deep-purple accent-4',
    ],
    slides: ['First', 'Second', 'Third', 'Fourth', 'Fifth'],
    cards: [
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
  }),
};
</script>
