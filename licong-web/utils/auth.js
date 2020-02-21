import Cookies from 'js-cookie'
const TokenKey='User-Token'

export function setUser(token){
    Cookies.set(TokenKey,token, { expires: 15 })
}

export function getUser(){
    return {
        token: Cookies.get(TokenKey),
    }
}

export function removeUser(){
    Cookies.remove(TokenKey)
}
