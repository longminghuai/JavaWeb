<template>
	<div class="center">
		<h1>湖南省志愿填报系统</h1>
		<div class="logon">
			<div :class="overlaylong">
				<div class="overlaylong-Signin" v-if="disfiex == 0">
					<h2 class="overlaylongH2">登录</h2>
					<input type="text" placeholder="用户名" v-model="loginUser">
					<input type="password" placeholder="密码" v-model="loginPassword">
					<button class="inupbutton" @click="handleLogin">登录</button>
				</div>
				<div class="overlaylong-Signup" v-if="disfiex == 1">
					<h2 class="overlaylongH2">注册账号</h2>
					<input type="text" placeholder="用户名" v-model="registerUser">
					<input type="password" placeholder="密码" v-model="registerPassword">
					<button class="inupbutton" @click="handleRegister">注册</button>
				</div>
			</div>
			<div :class="overlaytitle">
				<div class="overlaytitle-Signin" v-if="disfiex == 0">
					<h2 class="overlaytitleH2">Hello,Friend!</h2>
					<p class="overlaytitleP">
						输入你的个人信息，开始你的旅程
					</p>
					<div class="buttongohs" @click="Signin">注册</div>
				</div>
				<div class="overlaytitle-Signup" v-if="disfiex == 1">
					<h2 class="overlaytitleH2">Welcome Back!</h2>
					<p class="overlaytitleP">请登录你的个人信息，保持与我们的联系</p>
					<div class="buttongohs" @click="Signup">登录</div>
				</div>
			</div>
		</div>
	</div>
</template>

<style>
.center {
    width: 100vw;
    height: 100vh;
    background-image: url('../assets/dc.jpg'); 
    background-size: cover;
    background-repeat: no-repeat;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

h1 {
    font-size: 30px;
    color: black;
}

.logon {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
    /* position: relative;
    overflow: hidden; */
    width: 768px;
    max-width: 100%;
    min-height: 480px;
    margin-top: 20px;
    display: flex;
    background: -webkit-linear-gradient(right, #4284db, #29eac4);
}

.overlaylong {
    border-radius: 10px 0 0 10px;
    width: 50%;
    height: 100%;
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
}

.overlaylongleft {
    border-radius: 0px 10px 10px 0px;
    width: 50%;
    height: 100%;
    background-color: #fff;
    transform: translateX(100%);
    transition: transform 0.6s ease-in-out;
    display: flex;
    align-items: center;
    justify-content: center;
}

.overlaylongright {
    border-radius: 10px 0 0 10px;
    width: 50%;
    height: 100%;
    background-color: #fff;
    transform: translateX(0%);
    transition: transform 0.6s ease-in-out;
    display: flex;
    align-items: center;
    justify-content: center;
}

.overlaytitle {
    border-radius: 0px 10px 10px 0px;
    width: 50%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0);
    display: flex;
    align-items: center;
    justify-content: center;
}


.overlaytitleH2 {
    font-size: 30px;
    color: #fff;
    margin-top: 20px;
}

.overlaytitleP {
    font-size: 15px;
    color: #fff;
    margin-top: 20px;
}

.overlaytitleleft {
    border-radius: 0px 10px 10px 0px;
    width: 50%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0);
    display: flex;
    align-items: center;
    justify-content: center;
    transform: translateX(0%);
    transition: transform 0.6s ease-in-out;
}

.overlaytitleright {
    border-radius: 0px 10px 10px 0px;
    width: 50%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0);
    display: flex;
    align-items: center;
    justify-content: center;
    transform: translateX(-100%);
    transition: transform 0.6s ease-in-out;
}

.overlaytitle-Signin {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.overlaytitle-Signup {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.buttongohs {
    width: 180px;
    height: 40px;
    border-radius: 50px;
    border: 1px solid #fff;
    color: #fff;
    font-size: 15px;
    text-align: center;
    line-height: 40px;
    margin-top: 40px;
}

.overlaylongH2 {
    font-size: 25px;
    color: black;
    /* width: 250px; */
}

.overlaylong-Signin {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.overlaylong-Signup {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

input {
    background-color: #eee;
    border: none;
    padding: 12px 15px;
    margin: 10px 0;
    width: 240px;
}
h3{
    font-size: 10px;
    margin-top: 10px;
    cursor: pointer;
}
.inupbutton{
    background-color: #29eac4;
    border: none;
    width: 180px;
    height: 40px;
    border-radius: 50px;
    font-size: 15px;
    color: #fff;	
    text-align: center;
    line-height: 40px;
    margin-top: 30px;
}
.inupbutton {
    background-color: #29eac4;
    border: none;
    width: 180px;
    height: 40px;
    border-radius: 50px;
    font-size: 15px;
    color: #fff;	
    text-align: center;
    line-height: 40px;
    margin-top: 30px;
    transition: background-color 0.3s ease;
}

.inupbutton:hover {
    background-color: #2db2a6;
}

.inupbutton:active {
    background-color: #1f8b7a;
}

</style>

<script>
import axios from 'axios'
	export default {
		data() {
			return {
				overlaylong: 'overlaylong',
				overlaytitle: 'overlaytitle',
				disfiex: 0,
				loginUser: '',
				loginPassword: '',
				registerUser: '',
				registerPassword: ''
			}
		},
		methods: {
			Signin() {
				this.overlaylong = "overlaylongleft"
				this.overlaytitle = "overlaytitleright"
				setTimeout(() => {
					this.disfiex = 1
				}, 200)
			},
			Signup() {
				this.overlaylong = "overlaylongright"
				this.overlaytitle = "overlaytitleleft"
				setTimeout(() => {
					this.disfiex = 0
				}, 200)
			},
			handleLogin() {
                if (this.loginUser == null || this.loginUser == '') {
                    alert("账号不能为空");
                    return;
                }
                if (this.loginPassword == null || this.loginPassword == '') {
                    alert("密码不能为空");
                    return;
                }
                console.log(this.loginUser);

                axios.post("http://localhost:8080/login", {
                    username: this.loginUser,
                    password: this.loginPassword
                })
                .then((response) => {
                    console.log(response.data);
                    // 处理成功返回的结果
                    if (response.data.code !== 1) {
                    alert(response.data.msg);
                    return;
                    } else {
                    if (this.$router.currentRoute.path !== "/home") {
                        this.$router.push("/home");
                    }
                    }
                })
                .catch((error) => {
                    console.error('Error during login request:', error);
                });

                console.log('登录', this.loginUser, this.loginPassword);
            },

			handleRegister() {
				// 在这里处理注册逻辑
				console.log('注册', this.registerUser, this.registerPassword);
			}
		}
	}
</script>
