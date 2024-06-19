<!-- <template>
  <div class="college-card">
    <div class="college-info">
      <img :src="college.logo" class="college-logo" alt="Logo">
      <div class="college-details">
        <div class="college-header">
          <h2 class="college-name">{{ college.name }}</h2>
          <div class="college-location">
            <i class="el-icon-location"></i>
            <span>{{ college.location.city }}</span>
          </div>
          <span v-if="college.isHot" class="hot-icon">🔥</span>
        </div>
        <div class="college-tags">
          <span class="tag" v-for="tag in college.tags" :key="tag">{{ tag }}</span>
        </div>
      </div>
    </div>
    <div class="college-actions">
      <el-button type="text" size="small" @click="viewDetails(college.id)">录取概率</el-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    college: {
      type: Object,
      required: true
    }
  },
  methods: {
    viewDetails(collegeId) {
      this.$emit('view-details', collegeId);
    }
  }
};
</script>

<style scoped>
.college-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid #e8e8e8;
  border-radius: 8px;
  padding: 20px;
}

.college-info {
  display: flex;
  align-items: center;
}

.college-logo {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-right: 20px;
}

.college-details {
  display: flex;
  flex-direction: column;
}

.college-header {
  display: flex;
  align-items: center;
}

.college-name {
  font-size: 18px;
  margin: 0;
  margin-right: 10px;
}

.college-location {
  display: flex;
  align-items: center;
  color: #888;
}

.hot-icon {
  color: red;
  margin-left: 10px;
}

.college-tags {
  display: flex;
  flex-wrap: wrap;
  margin-top: 10px;
}

.tag {
  background: #f0f0f0;
  border-radius: 3px;
  padding: 5px 10px;
  margin-right: 10px;
  margin-top: 5px;
}

.college-actions {
  display: flex;
  align-items: center;
}
</style> -->
<template>
  <div>
      <el-container>
        <el-aside width="200px" class="aside-menu">
        <h1 class="aside-title">志愿填报系统</h1>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">智能志愿填报🔥</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-message"></i>
            <span slot="title">查大学</span>
          </el-menu-item>
          <el-submenu index="2-1">
            <template slot="title">
              <router-link to="/admission">院校库</router-link>
            </template>
          </el-submenu>
          <el-submenu index="2-2">
            <template slot="title">
              <router-link to="/admission_briefly">分数榜</router-link>
            </template>
          </el-submenu>
          <el-menu-item index="3">
            <i class="el-icon-message"></i>
            <span slot="title">查专业</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-message"></i>
            <span slot="title">我的志愿表</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-message"></i>
            <span slot="title">高考话题</span>
          </el-menu-item>
          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-message"></i>
              一分一段
            </template>
            <el-menu-item index="6-1">
              <router-link to="/ls">历史类</router-link>
            </el-menu-item>
            <el-menu-item index="6-2">
              <router-link to="/wl">物理类</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="7">
            <template slot="title">
              <i class="el-icon-message"></i>
              系统管理
            </template>
            <el-menu-item index="7-1">
              <router-link to="/class">用户管理</router-link>
            </el-menu-item>
            <el-menu-item index="7-2">
              <router-link to="/student">角色管理</router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
          <el-container>
              <el-header>
                  <el-row>
                      <el-col :span="1">
                          <div>
                              <el-button icon="el-icon-menu" size="mini" @click="menuHandler"></el-button>
                          </div>
                      </el-col>
                      <el-col :span="1"><div class="table">首页</div></el-col>
                      <el-col :span="20"><div class="table">&nbsp;</div></el-col>
                      <el-col :span="2">
                          <!--   头像下拉菜单 -->
                          <el-dropdown trigger="click">
                              <div class="circle">
                                  <el-avatar :size="50" :src="imgUrl"></el-avatar>
                              </div>
                              <el-dropdown-menu slot="dropdown">
                                  <el-dropdown-item icon="el-icon-plus">我的</el-dropdown-item>
                                  <el-dropdown-item icon="el-icon-circle-plus">账单</el-dropdown-item>
                                  <el-dropdown-item icon="el-icon-circle-plus-outline">消息</el-dropdown-item>
                                  <el-dropdown-item icon="el-icon-check">退出</el-dropdown-item>
                              </el-dropdown-menu>
                          </el-dropdown>
                      </el-col>
                  </el-row>
              </el-header>
              <el-main style="border: 1px solid #eee">
                  <el-form :inline="true" :model="formInline" class="demo-form-inline">
                    <el_form-item label="校名">
                      <el-input
                        v-model="formInline.college_name"
                        placeholder="请输入您要搜索的学校"
                      ></el-input>
                    </el_form-item>
                    <el-form-item label="院校所属">
                      <el-select v-model="formInline.province" filterable placeholder="请选择院校地区" style="width: 150px;">
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                          >
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="院校类型">
                      <el-select v-model="formInline.type_name" filterable placeholder="选择院校类型" style="width: 150px;">
                        <el-option
                          v-for="item in options_collegetype"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="院校层次">
                      <el-select v-model="formInline.level_name" placeholder="选择院校层次" style="width: 150px;">
                        <el-option
                          v-for="item in options_collegecc"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>

                    </el-form-item>

                    <el-form-item label="办学类型">
                      <el-select v-model="formInline.nature_name" placeholder="选择办学类型" style="width: 150px;">
                        <el-option
                          v-for="item in options_mcollegetype"
                          :key="item"
                          :label="item"
                          :value="item">
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item>
                      <el-checkbox :true-label="1" :false-label="2" v-model="formInline.f985">985</el-checkbox>
                      <el-checkbox :true-label="1" :false-label="2" v-model="formInline.f211">211</el-checkbox>
                      <el-checkbox :true-label="1" :false-label="0" v-model="formInline.dual_class_name">双一流</el-checkbox>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">查询</el-button>
                    </el-form-item>
                  </el-form>
                  <div class="university-list">
                    <div class="university-item" v-for="university in universities" :key="university.name">
                      <div class="university-logo">
                        <img :src="require(`@/assets/image/${university.id}.jpg`)" alt="logo" />
                      </div>
                      <div class="university-details">
                        <div class="university-header">
                          <div class="university-name">
                            {{ university.name }}
                          </div>
                          <div class="university-location">
                            <span class="location-icon"></span>
                            {{ university.location.province }}
                            {{ university.location.city }}
                          </div>
                        </div>
                        <div class="university-info">
                          <span v-if="university.f985==1" :key="university.f985" class="info-badge">985</span>
                          <span v-if="university.f211==1" :key="university.f211" class="info-badge">211</span>
                          <span v-if="university.dual_class_name==1" :key="university.dual_class_name" class="info-badge">双一流</span>
                          <span v-if="university.level_name!=null" :key="university.level_name" class="info-badge">{{ university.level_name }}</span>
                          <span v-if="university.nature_name!=null" :key="university.nature_name" class="info-badge">{{ university.nature_name }}</span>
                          <span v-if="university.type_name!=null" :key="university.type_name" class="info-badge">{{ university.type_name }}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="block">
                      <el-pagination
                          @size-change="handleSizeChange"
                          @current-change="handleCurrentChange"
                          :current-page="currentPage4"
                          :page-sizes="[5, 10, 15, 20]"
                          :page-size="pagesize"
                          layout="total, sizes, prev, pager, next, jumper"
                          :total="this.total"
                      >
                      </el-pagination>
                  </div>
              </el-main>
          </el-container>
      </el-container>
  </div>
</template>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
.table {
color: #C0C4CC;
line-height: 60px;
}
.circle{
  height: 60px;
}
.el-avatar{
  margin: 5px 0 0 0;
}
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


<script>
import axios from "axios";
export default {
  name: "HelloWorld",
data() {
  return {
    //菜单控制
    isCollapse: false,
    //头像地址
    imgUrl:require('../assets/logo.png'),
    pagesize: 10,
      total: 10,
      page: 1,
      tableData: [],
      formInline: {
        user: "",
        region: "",
      },
      options : [
        {
          value:"全部",
          label:'全部'
        },{
          value:'北京',
          label:'北京'
        },{
          value:'天津',
          label:'天津'
        },{
          value:'河北',
          label:'河北'
        },{
          value:'山西',
          label:'山西'
        },{
          value:'内蒙古',
          label:'内蒙古'
        },{
          value:'辽宁',
          label:'辽宁'
        },{
          value:'吉林',
          label:'吉林'
        },{
          value:'黑龙江',
          label:'黑龙江'
        },{
          value:'上海',
          label:'上海'
        },{
          value:'江苏',
          label:'江苏'
        },{
          value:'浙江',
          label:'浙江'
        },{
          value:'安徽',
          label:'安徽'
        },{
          value:'福建',
          label:'福建'
        },{
          value:'江西',
          label:'江西'
        },{
          value:'山东',
          label:'山东'
        },{
          value:'河南',
          label:'河南'
        },{
          value:'湖北',
          label:'湖北'
        },{
          value:'湖南',
          label:'湖南'
        },{
          value:'广东',
          label:'广东'
        },{
          value:'广西',
          label:'广西'
        },{
          value:'海南',
          label:'海南'
        },{
          value:'重庆',
          label:'重庆'
        },{
          value:'四川',
          label:'四川'
        },{
          value:'贵州',
          label:'贵州'
        },{
          value:'云南',
          label:'云南'
        },{
          value:'西藏',
          label:'西藏'
        },{
          value:'陕西',
          label:'陕西'
        },{
          value:'甘肃',
          label:'甘肃'
        },{
          value:'青海',
          label:'青海'
        },{
          value:'宁夏',
          label:'宁夏'
        },{
          value:'新疆',
          label:'新疆'
        },{
          value:'香港',
          label:'香港'
        },{
          value:'澳门',
          label:'澳门'
        },{
          value:'台湾',
          label:'台湾'
        }
      ],
      options_collegetype:[
      {
          value:'全部',
          label:'全部'
        },{
          value:'综合',
          label:'综合'
        },{
          value:'理工',
          label:'理工'
        },{
          value:'农林',
          label:'农林'
        },{
          value:'医药',
          label:'医药'
        },{
          value:'师范',
          label:'师范'
        },{
          value:'语言',
          label:'语言'
        },{
          value:'财经',
          label:'财经'
        },
        {
          value:'政法',
          label:'政法'
        },
        {
          value:'体育',
          label:'体育'
        },
        {
          value:'艺术',
          label:'艺术'
        },
        {
          value:'民族',
          label:'民族'
        },
        {
          value:'军事',
          label:'军事'
        },
        {
          value:'其它',
          label:'其它'
        },
      ],
      options_collegecc:[
        {
          value:'普通本科',
          label:'普通本科'
        },
        {
          value:'专科（高职）',
          label:'专科（高职）'
        }
      ],
      options_mcollegetype:[
        "公办","民办","中外合作办学","内地与港澳台地区合作办学","独立学院","境外高校独立办学","其它"
      ],
      universities: [
        
      ]
  }
},
methods: {
    menuHandler(){
        this.$store.commit('MENUHANDLER')
    },
    onSubmit() {
        //点击查询按钮时触发
        axios
          .get("http://localhost:8080/college", {
            params: {
              page: this.page,
              pagesize: this.pagesize,
              college_name: this.formInline.college_name,
              province: this.formInline.province,
              type_name:this.formInline.type_name,
              nature_name:this.formInline.nature_name,
              level_name:this.formInline.level_name,
              dual_class_name:this.formInline.dual_class_name,
              f985:this.formInline.f985,
              f211:this.formInline.f211
            },
          })
          .then((result) => {
            console.log(result.data.data);
            this.universities = result.data.data.rows;
            this.total = result.data.data.total;
          });
      },
      handleClick(row) {
        console.log(row);
      },
      handleSizeChange(val) {
        // 当改变每个分页大小时触发
        axios
          .get("http://localhost:8080/college", {
            params: {
              page: this.page,
              pagesize: val,
              college_name: this.formInline.college_name,
              province: this.formInline.province,
              type_name:this.formInline.type_name,
              nature_name:this.formInline.nature_name,
              level_name:this.formInline.level_name,
              dual_class_name:this.formInline.dual_class_name,
              f985:this.formInline.f985,
              f211:this.formInline.f211
            },
          })
          .then((result) => {
            // 接收成功返回的结果
            console.log(result.data.data.rows);
            this.universities = result.data.data.rows;
            this.pagesize = val;
            this.total = result.data.data.total;
          });
        console.log(`每页 ${val} 条`);
      },

      handleCurrentChange(val) {
        //当改变当前页时触发
        axios
          .get("http://localhost:8080/college", {
            params: {
              page: val,
              pagesize: this.pagesize,
              college_name: this.formInline.college_name,
              province: this.formInline.province,
              type_name:this.formInline.type_name,
              nature_name:this.formInline.nature_name,
              level_name:this.formInline.level_name,
              dual_class_name:this.formInline.dual_class_name,
              f985:this.formInline.f985,
              f211:this.formInline.f211
            },
          })
          .then((result) => {
            //接收成功返回的结果
            console.log(result.data.data.rows);
            this.universities = result.data.data.rows;
            this.page = val;
          });
        console.log(`当前页: ${val}`);
      },
  },
  //钩子方法
mounted() {
    axios.get("http://localhost:8080/college").then((result) => {
      //接收成功返回的结果
      console.log(result.data.data.rows);
      this.universities = result.data.data.rows;
      this.total = result.data.data.total;
    });
  },
}
</script>