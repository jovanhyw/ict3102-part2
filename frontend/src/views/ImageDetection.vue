<template>
  <div class="image-detection">
    <v-row align="center" justify="center">
      <v-col cols="12" lg="2">
        <v-form @submit.prevent="uploadFile">
          <v-file-input
            small-chips
            prepend-icon="mdi-camera"
            label="Select an image"
            accept="image/*"
            v-model="imgFile"
          ></v-file-input>
          <div align="center" justify="center">
            <v-btn color="primary" type="submit" :loading="submitBtnLoading"
              >Submit</v-btn
            >
          </div>
        </v-form>
      </v-col>
    </v-row>

    <v-row align="center" justify="center">
      <v-col cols="12" lg="4" v-if="resJson.length !== 0">
        <div align="center" justify="center" class="subtitle-2">
          {{ resJson }}
        </div>
      </v-col>
      <v-col cols="12" v-if="responseImg !== null">
        <v-img :src="responseImg" aspect-ratio="3" contain></v-img>
      </v-col>
    </v-row>

    <!-- <v-container>
      <v-row>
        <v-col cols="6" lg="2" offset="2">
          <v-form @submit.prevent="uploadFile">
            <v-file-input
              small-chips
              prepend-icon="mdi-camera"
              label="Select an image"
              accept="image/*"
              v-model="imgFile"
            ></v-file-input>
            <div align="center" justify="center">
              <v-btn color="primary" type="submit" :loading="submitBtnLoading"
                >Submit</v-btn
              >
            </div>
          </v-form>
        </v-col>

        <v-col cols="6" offset="1" v-if="responseImg !== null">
          <v-img :src="responseImg" aspect-ratio="1.5" contain></v-img>
        </v-col>
      </v-row>
    </v-container>-->
  </div>
</template>

<script>
// @ is an alias to /src
import ApiService from '@/services/api.service'

export default {
  name: 'image-detection',
  components: {},
  data() {
    return {
      submitBtnLoading: false,
      imgFile: null,
      responseImg: null,
      resJson: []
    }
  },
  methods: {
    uploadFile() {
      this.submitBtnLoading = true

      let formData = new FormData()
      formData.append('image', this.imgFile)

      ApiService.customRequest({
        method: 'post',
        url: '/upload',
        data: formData,
        config: { headers: { 'Content-Type': 'multipart/form-data' } }
      })
        .then(res => {
          this.submitBtnLoading = false

          // let rawResponse = res.data
          // let base64image = btoa(
          //   String.fromCharCode(...new Uint8Array(rawResponse))
          // )

          this.responseImg = 'data:image/jpeg;base64,' + res.data
          // this.resJson = res.data
        })
        .catch(err => {
          this.submitBtnLoading = false
          console.log('Error')
          console.log(JSON.stringify(err))
        })
    }
  }
}
</script>

<style scoped></style>
