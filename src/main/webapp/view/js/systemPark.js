//Разлогирование пользователя
function logout(){
  let xhr = newXMLHttpRequest();
  xhr.open("GET", "/systempark/logout", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let resultOperation = Number(xhr.response);

      // если выход прошёл успешно
      if(resultOperation === 1){
        window.location = "systemPark.html";
      }else{
        console.log("Ошибка выхода из кабинета, попробуйте ещё раз.");
      }
    } else {
        alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }
xhr.send();
}

/* BEGIN: МЕТОДЫ для работы с PARKS */
function showParks(){
  let xhr = newXMLHttpRequest();
  xhr.open("GET", "/systempark/parksservlet", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let content = document.getElementsByClassName("content")[0];
      content.innerHTML = xhr.response;
      settingListenerButAddPark();

    } else {
      alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }
  xhr.send();
}

function addNewPark(){
  console.log("Добавляем новый парк.");
  let nameNewPark = document.getElementsByClassName("name_new_park")[0].value;
  let body = "name="+nameNewPark+"&action=ADD_PARK";

  let xhr = newXMLHttpRequest();
  xhr.open("POST", "/systempark/parksservlet", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); 
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let contentFromServer = xhr.response;

      let content = document.getElementsByClassName("content")[0];
      content.innerHTML = contentFromServer;
      settingListenerButAddPark();
    } else {
      alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }

  xhr.send(body);
}

function delPark(idDelPark){
  let body = "idDelPark="+idDelPark+"&action=DEL_PARK";
  console.log("УДАЛЯЕМ ПАРК: " + body);

  let xhr = newXMLHttpRequest();
  xhr.open("POST", "/systempark/parksservlet", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); 
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let contentFromServer = xhr.response;
      let content = document.getElementsByClassName("content")[0];
      content.innerHTML = contentFromServer;
      settingListenerButAddPark();

    } else {
      alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }

  xhr.send(body);
}

function settingListenerButAddPark(){
  let butAddNewPark = document.getElementsByClassName("add_new_park")[0];
  butAddNewPark.addEventListener("click", addNewPark);

  //устанавливаем на каждую кнопку "X" слушателя
  let butsDelParks = document.getElementsByClassName("but_del_park");
  for(let i = 0; i<butsDelParks.length; i++){
    let valueButton = butsDelParks[i].value;

    butsDelParks[i].addEventListener("click", function(){
      delPark(valueButton);
    });
  }
}
/* END: МЕТОДЫ для работы с PARKS */





function methodForAction(){
  let xhr = newXMLHttpRequest();
  xhr.open("GET", "/systempark/systempark", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let resultOperation = xhr.response;
      console.log(resultOperation);
      let cont = document.getElementsByClassName("content")[0];
      cont.innerHTML = xhr.response;
    } else {
        alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }
  xhr.send();
}

function methodForPlants(){
  let xhr = newXMLHttpRequest();
  xhr.open("GET", "/systempark/systempark", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let resultOperation = xhr.response;
      console.log(resultOperation);
      let cont = document.getElementsByClassName("content")[0];
      cont.innerHTML = xhr.response;
    } else {
        alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }
  xhr.send();
}

function methodForTasks(){
  let xhr = newXMLHttpRequest();
  xhr.open("GET", "/systempark/systempark", true); 
  xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
  
  xhr.onload = () => {
    if (xhr.status == 200) {
      let resultOperation = xhr.response;
      console.log(resultOperation);
      let cont = document.getElementsByClassName("content")[0];
      cont.innerHTML = xhr.response;
    } else {
        alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
    }
  }
  xhr.send();
}


//XMLHttpRequest для различных браузеров
function newXMLHttpRequest() {

  var xmlreq = false;
  
  if (window.XMLHttpRequest) {
  
    // Создадим XMLHttpRequest объект для не-Microsoft браузеров
    xmlreq = new XMLHttpRequest();
  
  } else if (window.ActiveXObject) {
  
    // Создадим XMLHttpRequest с помощью MS ActiveX
    try {
      // Попробуем создать XMLHttpRequest для поздних версий
      // Internet Explorer
  
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
  
    } catch (e1) {
  
      // Не удалось создать требуемый ActiveXObject
  
      try {
        // Пробуем вариант, который поддержат более старые версии
        //  Internet Explorer
  
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
  
      } catch (e2) {
  
        // Не в состоянии создать XMLHttpRequest с помощью ActiveX
      }
    }
  }
  
  return xmlreq;
}