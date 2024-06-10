export function setMenu(menu){
    return localStorage.setItem("menu",menu)
}

export function getMenu(){
    return localStorage.getItem("menu")
}

export function removeMenu(){
    return localStorage.removeItem("menu")
}