// Styles
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/styles';
import colors from 'vuetify/lib/util/colors';

// Vuetify
import { createVuetify } from 'vuetify';

export default createVuetify({
  theme: {
    themes: {
      light: {
        dark: false,
        colors: {
          primary: colors.blue.lighten2, // #64B5F6
          secondary: colors.red.lighten4, // #FFCDD2
          error: '#f44336',
          info: '#2196F3',
          success: '#4caf50',
          warning: '#fb8c00',
          textColor: colors.grey.darken3,
        },
      },
      dark: {},
    },
  },
});
