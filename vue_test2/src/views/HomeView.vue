<!-- <template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script> -->


<template>
  <div>
      <el-container>
          <el-aside width="200px" style="border: 1px solid #eee; background-color: #545c64; color: #fff">
              <h1 style="background-color: #545c64; color: #fff; margin: 0; padding: 10px;">
                  志愿填报系统
              </h1>
              <el-menu
                  default-active="2"
                  class="el-menu-vertical-demo"
                  @open="handleOpen"
                  @close="handleClose"
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b"
              >
                  <el-submenu index="1">
                  <template slot="title">
                      <i class="el-icon-location"></i>
                      <span>导航一</span>
                  </template>
                  <el-menu-item-group>
                      <template slot="title">分组一</template>
                      <el-menu-item index="1-1">选项1</el-menu-item>
                      <el-menu-item index="1-2">选项2</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="分组2">
                      <el-menu-item index="1-3">选项3</el-menu-item>
                  </el-menu-item-group>
                  <el-submenu index="1-4">
                      <template slot="title">选项4</template>
                      <el-menu-item index="1-4-1">选项1</el-menu-item>
                  </el-submenu>
                  </el-submenu>
                  <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span slot="title"><router-link to="/emp">导航二</router-link></span>
                  </el-menu-item>
                  <el-menu-item index="3" disabled>
                  <i class="el-icon-document"></i>
                  <span slot="title">导航三</span>
                  </el-menu-item>
                  <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span slot="title">导航四</span>
                  </el-menu-item>
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
                      <el-form-item label="校名">
                          <el-input
                          v-model="formInline.college_name"
                          placeholder="请输入您要搜索的学校"
                          ></el-input>
                      </el-form-item>
                      <el-form-item label="校址">
                          <el-input
                          v-model="formInline.college_address"
                          placeholder="请输入您要搜索的学校地址"
                          ></el-input>
                      </el-form-item>
                      <el-form-item label="录取批次" style="flex-grow: 1">
                          <el-select
                          v-model="formInline.college_batch"
                          placeholder="请选择录取批次"
                          >
                          <el-option label="本科一批" value="本科一批"></el-option>
                          <el-option label="本科二批" value="本科二批"></el-option>
                          <el-option label="本科提前批" value="本科提前批"></el-option>
                          <el-option label="专科一批" value="专科一批"></el-option>
                          <el-option label="专科二批" value="专科二批"></el-option>
                          </el-select>
                      </el-form-item>
                      <el-form-item>
                          <el-button type="primary" @click="onSubmit">查询</el-button>
                          <el-button
                          type="primary"
                          @click="showAddCollegeForm = true"
                          style="margin-left: 200px"
                          >新增院校</el-button
                          >
                      </el-form-item>
                  </el-form>
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
                  <h1>
                      <el-table :data="tableData" border style="width: 100%">
                          <el-table-column prop="id" label="编号" width="50">
                          </el-table-column>
                          <el-table-column prop="name" label="校名" width="200">
                          </el-table-column>
                          <el-table-column prop="location.city" label="校址" width="400">
                          </el-table-column>
                          <el-table-column prop="dual_class_name" label="双一流" width="150">
                          </el-table-column>
                          <el-table-column prop="f985" label="985" width="100">
                          </el-table-column>
                          <el-table-column prop="f211" label="211" width="100">
                          </el-table-column>
                          <el-table-column prop="type_name" label="类型" width="150">
                          </el-table-column>
                          <el-table-column prop="nature_name" label="性质" width="150">
                          </el-table-column>
                          <el-table-column prop="level_name" label="层次" width="150">
                          </el-table-column>
                          <el-table-column fixed="right" label="操作" width="200">
                          <template slot-scope="scope">
                              <el-button type="text" size="small" @click="openEditForm(scope.row)">
                              修改
                              </el-button>
                              <el-button type="text" size="small" @click="deleteCollege(scope.row.id)">
                              删除
                              </el-button>
                          </template>
                          </el-table-column>
                      </el-table>
                  </h1>
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
</style>


<script>
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
  }
},
methods: {
  menuHandler(){
      this.$store.commit('MENUHANDLER')
  }
}
}
</script>