<template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadImage">Upload</button>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      selectedFile: null,
    };
  },
  methods: {
    onFileChange(e) {
      this.selectedFile = e.target.files[0];
    },
    uploadImage() {
      const formData = new FormData();
      formData.append('image', this.selectedFile);
      axios.post('http://116.62.32.192:3000/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
          .then(response => {
            console.log(response.data);
            formData.append('image', this.selectedFile);
            axios.post("/")
          })
          .catch(error => {
            console.error('Error:', error);
            // 处理错误情况
          });
    }
  }
};
</script>
