import jwtDecode from "jwt-decode";
import http from "./httpService";
//import { apiUrl } from "../config.json";
import axios from "axios";

const apiEndpoint = 'http://localhost:8088/api/auth';
const tokenKey = "token";
const RegisterForm = (email,password)=>{

    return axios
    .post(apiEndpoint + "/register", {
    email,
    password,

    })
    .then((response)=> { 
        if(response.data.accessToken){
            localStorage.setItem("user", JSON.stringify(response.data)  );
        }
        return response.data;

    }) 
};
const LoginForm = (email,password)=>{
    return axios.post(apiEndpoint +"/login",{
        email,
        password,
    }).then((response)=>{
        if(response.data.accessToken){
            localStorage.setItem("user",JSON.stringify(response.data));
        }
        return response.data;
    });
};
const Logout = () => {
    localStorage.removeItem("user");
  };
  
  const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
  };
  
  

http.setJwt(getJwt());

// export async function LoginForm(email, password){
//     const {data: jwt} = await http.post(apiEndpoint, {email, password});
//     localStorage.setItem(tokenKey, jwt);
// }

 export function loginWithJwt(jwt){
     localStorage.setItem(tokenKey, jwt);
 }

// export async function Logout(){
//     localStorage.removeItem(tokenKey);
// }

// export function getCurrentUser(){
//     try{
//         const jwt = localStorage.getItem(tokenKey);
//         return jwtDecode(jwt);
//     }catch(ex){
//         return null;
//     }
// }

function getJwt(){
    return localStorage.getItem(tokenKey);
}

export default {
    LoginForm,
     loginWithJwt,
    Logout,
    getCurrentUser,
    getJwt
};