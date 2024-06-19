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
              <span slot="title">智能志愿填报?</span>
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
                        <el-form-item label="年份">
                            <el-select v-model="formInline.province" filterable placeholder="请选择高考年份" style="width: 150px;">
                            <el-option
                                v-for="item in options"
                                :key="item"
                                :label="item"
                                :value="item"
                                >
                            </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="类别">
                            <el-select v-model="formInline.type" filterable placeholder="请选择类别" style="width: 150px;">
                            <el-option
                                v-for="item in options_type"
                                :key="item"
                                :label="item"
                                :value="item"
                                >
                            </el-option>
                            </el-select>
                        </el-form-item>
                        <el_form-item label="分数">
                            <el-input
                            v-model="formInline.score"
                            placeholder="请输入您要搜索的分数"
                            ></el-input>
                        </el_form-item>
                    </el-form>
                    <div class="onetoone">
                        <el-table
                            :data="tableData"
                            style="width: 100%">
                            <el-table-column
                                prop="score"
                                label="分数"
                                width="180">
                            </el-table-column>
                            <el-table-column
                                prop="seg"
                                label="所在分数段"
                                width="180">
                            </el-table-column>
                            <el-table-column
                                prop="count"
                                label="该分数段人数">
                            </el-table-column>
                            <el-table-column
                                prop="total"
                                label="合计">
                            </el-table-column>
                            <el-table-column
                                prop="remark"
                                label="备注">
                            </el-table-column>
                            <el-table-column fixed="right" label="操作" width="100">
                                <template slot-scope="scope">
                                <el-button
                                    type="text"
                                    size="small"
                                    @click="openEditForm(scope.row)"
                                    >修改</el-button
                                >
                                <el-button
                                    type="text"
                                    size="small"
                                    @click="deleteCollege(scope.row.exam_number)"
                                    >删除</el-button
                                >
                                </template>
                            </el-table-column>
                        </el-table>
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
          2023
        ],
        options_type:[
            "物理","历史"
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