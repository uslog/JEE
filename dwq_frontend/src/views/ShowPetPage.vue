<template>
  <div>
    <div v-if="pet">
      <h2>{{ pet.name }}</h2>
      <img :src="pet.avatar" alt="Pet Image" />
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      pet: null
    };
  },
  mounted() {
    axios.get('/api/auth/pets/8')
        .then(response => {
          this.pet = response.data;
          this.pet.avatar="data:image/png;base64,"+this.pet.avatar
          console.log(this.pet)
        })
        .catch(error => {
          console.error('Error fetching pet data:', error);
        });
  }
};
</script>
