<!-- <template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template> -->


<template>
  <div class="university-list">
    <div class="university-item" v-for="university in universities" :key="university.name">
      <div class="university-logo">
        <img :src="university.logo" alt="logo" />
      </div>
      <div class="university-details">
        <div class="university-header">
          <div class="university-name">
            {{ university.name }}
          </div>
          <div class="university-location">
            <span class="location-icon"></span>
            {{ university.location }}
          </div>
        </div>
        <div class="university-info">
          <span v-for="info in university.info" :key="info" class="info-badge">{{ info }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      universities: [
        {
          logo: '../assets/image/2347.jpg',
          name: '贵州工商职业学院',
          location: '贵州贵阳市',
          info: ['专科（高职）', '财经类', '民办'],
        },
        {
          logo: 'https://static-data.gaokao.cn/upload/logo/2347.jpg',
          name: '清华大学',
          location: '北京市海淀区',
          info: ['本科', '综合类', '公办', '985', '211', '双一流', '强基计划'],
        },
        {
          logo: 'https://static-data.gaokao.cn/upload/logo/2347.jpg',
          name: '北京大学',
          location: '北京市海淀区',
          info: ['本科', '综合类', '公办', '985', '211', '双一流', '强基计划'],
        },
      ],
    };
  },
};
</script>

<style scoped>
.university-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.university-item {
  display: flex;
  align-items: center;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #fff;
}

.university-logo img {
  width: 80px;
  height: 80px;
  margin-right: 20px;
}

.university-details {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.university-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.university-name {
  font-size: 18px;
  font-weight: bold;
  margin-right: 10px;
}

.university-location {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #888;
  padding: 5px 10px;
  border: 1px solid #ddd;
  border-radius: 25px;
  background-color: #f5f5f5;
}

.location-icon {
  width: 16px;
  height: 16px;
  background-image: url('../assets/OIP.jpg');
  background-size: cover;
  margin-right: 5px;
}

.university-info {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}

.info-badge {
  background: #f5f5f5;
  padding: 3px 6px;
  border-radius: 3px;
  font-size: 12px;
}
</style>
