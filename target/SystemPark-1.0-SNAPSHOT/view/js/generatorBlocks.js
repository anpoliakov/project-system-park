// JS который генерирует меню для определённого пользователя
const blockHeader = document.getElementsByClassName("header")[0];
let loginPerson = getCookie("loginPerson");
let idPerson = getCookie("idPerson");
let rolePerson = getCookie("rolePerson");

//как только загрузилось DOM дерево
document.addEventListener("DOMContentLoaded", () => {

  //Если пользователь авторизирован
  if(loginPerson != null && idPerson != null && rolePerson != null){
    let blockAuthorized = document.createElement("div");
    blockAuthorized.className = "block_authorized";
    blockAuthorized.innerHTML = '<button id="button_logout" class="but_menu">Logout</button>';
    blockHeader.appendChild(blockAuthorized);

    let blockAvatar = document.createElement("div");
    blockAvatar.className = "block_avatar";
    blockAvatar.innerHTML = "<img src='img/avatar.jpg' width='70' height='68'>";
    blockAuthorized.append(blockAvatar);

    //добавляем слушателя на кнопку Logout
    let butLogout = document.getElementById("button_logout");
    butLogout.addEventListener("click", logout);
  
    //создаём кнопки для опредеёлнного пользователя
    generatorForRole(rolePerson);

  }else{
    let blockUnauthorized = document.createElement("div");
    blockUnauthorized.className = "block_unauthorized";
    blockUnauthorized.innerHTML = '<a href="registration.html" class="header_link">Registration</a><a href="login.html" class="header_link">Login</a>';
    blockHeader.appendChild(blockUnauthorized);
  }
});

function generatorForRole(role){
  console.log("Роль получили - " + rolePerson);

  switch(role){
    case "owner":
      let blockOwner = document.createElement("div");
      blockOwner.className = "block_special_role";
      blockOwner.innerHTML = '<button id="button_parks" class="but_menu"> Parks </button>\
                              <button id="button_action" class="but_menu"> Action </button>\
                              <button id="button_plants" class="but_menu"> Plants </button>\
                              <button id="button_tasks" class="but_menu"> Tasks </button>';
      blockHeader.appendChild(blockOwner);

      //добавляем слушателя на кнопку "Parks"
      let butParks = document.getElementById("button_parks");
      butParks.addEventListener("click", showParks);

      //добавляем слушателя на кнопку "Action"
      // let butAction = document.getElementById("button_action");
      // butAction.addEventListener("click", showAction);

      //добавляем слушателя на кнопку "Plants"
      let butPlants = document.getElementById("button_plants");
      butPlants.addEventListener("click", showPlants);

      //добавляем слушателя на кнопку "Tasks"
      let butTasks = document.getElementById("button_tasks");
      butTasks.addEventListener("click", methodForTasks);
    break;

    case "forester":
    break;

    case "admin":
    break;
  }
}
  
//Метод для получения cookie
function getCookie(name) {
  let matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? decodeURIComponent(matches[1]) : null;
}