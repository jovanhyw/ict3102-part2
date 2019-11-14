<template>
  <v-app>
    <NavbarPublic />

    <v-content>
      <router-view></router-view>
    </v-content>

    <Footer />
  </v-app>
</template>

<script>
import NavbarPublic from '@/components/NavbarPublic'
import Footer from '@/components/Footer'

export default {
  name: 'App',
  components: {
    NavbarPublic,
    Footer
  },
  data: () => ({
    //
  })
}
</script>
