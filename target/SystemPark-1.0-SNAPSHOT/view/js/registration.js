let buttonSendForm = document.getElementById("buttonSend");

buttonSendForm.onclick = () => {
    let user = makeUserObject();

    if(validateUser(user) == true){
        delete user["repeatPassword"];
        sendDataForm(user);
    }
}

function makeUserObject(){
    let user = {};
    let fieldsForm = document.getElementsByClassName("field");
    let fieldsRadio = document.getElementsByClassName("fieldRadio");

    for(let i=0; i<fieldsForm.length; i++){
        let name = fieldsForm[i].name;
        let value = fieldsForm[i].value;
        user[name] = value;
    }

    for(let i=0; i<fieldsRadio.length; i++){
        if(fieldsRadio[i].checked){
            let name = fieldsRadio[i].name;
            let value = fieldsRadio[i].value;
            user[name] = value;
            break;
        }
    }

    return user;
}

function validateUser(user){
    //user - это обьект с введёными данными пользователя

    //регулярки
    let templateFullName = /^([a-zA-Z]+)$|^([а-яА-ЯёЁ]+)$/;
    let templateLoginAndPassword = /^[a-zA-Z0-9_]+$/;
    let templateEmail = /^[\w_.]{3,}@([a-z]{2,})\.[a-z]{2,4}$/;

    //div на главной странице, куда записываю информацию и ошибки
    let textErrors = document.getElementById("divInfo");
    let statusOperation = false;

    if(templateFullName.test(user.name) != true){
        textErrors.innerHTML = "Name error";
    }else if (templateFullName.test(user.middleName) != true){
        textErrors.innerHTML = "Error in middle name";
    }else if (templateFullName.test(user.lastName) != true){
        textErrors.innerHTML = "Last name error";
    }else if (templateLoginAndPassword.test(user.login) != true){
        textErrors.innerHTML = "Error in login";
    }else if (templateLoginAndPassword.test(user.password) != true){
        textErrors.innerHTML = "Error in password";
    }else if (user.password != user.repeatPassword){
        textErrors.innerHTML = "Passwords aren't the same";
    }else if (templateEmail.test(user.email) != true){
        textErrors.innerHTML = "Error in email";
    }else{
        statusOperation = true;
        textErrors.innerHTML = "";
    }

    return statusOperation;
}

function sendDataForm(user){
    let infoLoad = document.getElementById("divInfo");
    let json = JSON.stringify(user);
    
    /* Настройка AJAX запроса */
    let xhr = newXMLHttpRequest();
    xhr.open("POST", "/systempark/registration");
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(json);

    /* Обработка ответа с сервера */
    xhr.onload = () => {
        if (xhr.status == 200) {
            let resultOperation = Number(xhr.response);
            
            if(resultOperation === -1){
                infoLoad.innerHTML = "Пользователь с таким логином или email существует!";
            }else if(resultOperation === 1){
                alert("Ваш аккаунт создан! Нажмите ОК - для перехода на страницу входа");
                window.location.href = 'login.html';
            }else if(resultOperation === 0){
                alert("Ошибка операции на сервере");
            }
        } else {
            alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
        }
    }
}


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