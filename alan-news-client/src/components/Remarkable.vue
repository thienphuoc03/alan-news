<template>
  <v-container class="bg-white">
    <span
      class="text-left text-h6 text-decoration-underline d-block text-uppercase text-textColor"
      >ĐÁNG CHÚ Ý</span
    >

    <v-row no-gutters>
      <v-col v-for="card in cards" :key="card.title" class="ma-2 pa-2">
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
</template>

<script>
export default {
  data: () => ({
    cards: [
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
