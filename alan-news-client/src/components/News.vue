<template>
  <v-container>
    <Breadcrumb :crumbs="crumbs" />

    <span
      class="text-left text-h6 text-decoration-underline d-block text-uppercase text-textColor"
      style="cursor: pointer"
      ><a>Công nghệ</a></span
    >

    <div v-for="card in cards" :key="card.id">
      <v-divider></v-divider>

      <v-card
        flat
        class="w-100 text-left d-flex justify-center align-center my-3"
        @Click="$router.push('/news/1')"
      >
        <v-img :src="card.src" width="300" height="200"> </v-img>

        <div>
          <v-card-item>
            <v-card-title>{{ card.title }}</v-card-title>

            <v-card-subtitle>{{ card.category }}</v-card-subtitle>
          </v-card-item>

          <v-card-text> {{ card.description }} </v-card-text>
        </div>
      </v-card>
    </div>

    <div class="text-center">
      <v-container>
        <v-row justify="center">
          <v-col cols="8">
            <v-container class="max-width">
              <v-pagination
                v-model="page"
                class="my-4"
                :length="15"
              ></v-pagination>
            </v-container>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </v-container>
</template>

<script>
import Breadcrumb from './Breadcrumb.vue';

export default {
  components: {
    Breadcrumb,
  },
  data: () => ({
    crumbs: [
      {
        title: 'Home',
        disabled: false,
        href: '/',
      },
      {
        title: 'News',
        disabled: false,
        href: '/news',
      },
    ],

    cards: [
      {
        id: 1,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 2,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 3,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 4,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/13/avatar1676281850016-16762818512281966358553.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 5,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 6,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 7,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/13/avatar1676281850016-16762818512281966358553.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 8,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 9,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 10,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
      {
        id: 11,
        title:
          'realme ra mắt điện thoại sạc 240W nhanh nhất thế giới, giá chỉ hơn 11 triệu đồng',
        src: 'https://genk.mediacdn.vn/zoom/260_162/139269124445442048/2023/2/9/avatar1675936290616-16759362913211433552399.jpg',
        description:
          'realme GT Neo5 là mẫu smartphone mới nhất của realme, nổi bật với hiệu năng mạnh mẽ và khả năng sạc siêu nhanh nhờ công nghệ sạc 240W đầu tiên trên thị trường.',
        category: 'Mobile',
        createAt: '3 ngày trước',
      },
    ],

    page: 1,
  }),
};
</script>
