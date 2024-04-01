import { getUserInfo} from '../api'
export function setUser(user){
    return localStorage.setItem("user",JSON.stringify(user))
}

export function getUser(){
    return JSON.parse(localStorage.getItem("user"))
}

export function removeUser(){
    return localStorage.removeItem("user")
}
