<template>
  <div class="home">
    <section>
      <v-parallax dark src="/img/home.svg" height="800">
        <v-row align="center" justify="center">
          <v-col class="text-center" cols="12">
            <h1 class="display-1 font-weight-bold mb-12">
              Have an image you want to run through an object detector?
            </h1>
            <v-btn
              class="subheading"
              color="primary"
              router
              to="/image-detection"
              >Let's go!</v-btn
            >
          </v-col>
        </v-row>
      </v-parallax>
    </section>

    <section style="background-color: #f7f7f7;">
      <v-container fill-height>
        <v-row align="center" justify="center">
          <v-col class="text-center mb-12" cols="12">
            <h1 class="display-1 font-weight-bold my-8 text-uppercase">
              Features
            </h1>
            <span class="subtitle-2">Here's how to get started!</span>
          </v-col>

          <v-col class="text-center" cols="12" lg="3">
            <div align="center" justify="center" class="setheight">
              <v-img src="/img/select.svg" max-width="300px"></v-img>
            </div>
            <h3 class="subtitle-1 font-weight-bold mt-12">Select an image</h3>
            <span class="subtitle-2">
              Select an image that you wish to run the object detection on from
              your computer.
            </span>
          </v-col>

          <v-col class="text-center" cols="12" lg="3">
            <div align="center" justify="center" class="setheight">
              <v-img src="/img/submit.svg" max-width="300px"></v-img>
            </div>
            <h3 class="subtitle-1 font-weight-bold mt-12">Click on submit</h3>
            <span class="subtitle-2"
              >Once you have selected your image, click on the submit
              button!</span
            >
          </v-col>

          <v-col class="text-center" cols="12" lg="3">
            <div align="center" justify="center" class="setheight">
              <v-img src="/img/received.svg" max-width="300px"></v-img>
            </div>
            <h3 class="subtitle-1 font-weight-bold mt-12">
              Wait to receive the detected image
            </h3>
            <span class="subtitle-2"
              >Sit back and relax while our object detector does the job!</span
            >
          </v-col>
        </v-row>
      </v-container>
    </section>
  </div>
</template>

<script>
export default {
  name: 'home'
}
</script>

<style scoped>
.setheight {
  height: 280px;
}
</style>
