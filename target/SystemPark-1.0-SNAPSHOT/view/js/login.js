const buttonSubmit = document.getElementById("submit-form");
const infoDiv = document.getElementById("divInfo");

buttonSubmit.onclick = () => {
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    if(isCorrectInput(login,password) === true){
        //под вопросом надобность noCache - устраняет кэширование запроса?!
        let bodyRequest = "login="+encodeURIComponent(login)+"&password="+encodeURIComponent(password) + "&noCache=" + (Math.random() * (9999 - 1000) + 1000);
        sendDataForm(bodyRequest);
    } 
}

function isCorrectInput(login, password){
    let isCorrect = false;
    const verificationTemplate = /^[a-zA-Z0-9_]+$/;

    if(verificationTemplate.test(login) == false){
        infoDiv.innerHTML = "Invalid login";
    }else if(verificationTemplate.test(password) == false){
        infoDiv.innerHTML = "Invalid password";
    }else{
        isCorrect = true;
        infoDiv.innerHTML = " ";
    }
    return isCorrect;
}

function sendDataForm(bodyRequest){
    /* Настройка AJAX запроса */
    let xhr = newXMLHttpRequest();
    xhr.open("POST", "/systempark/login", true); 
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded'); // кодировку можно не указывать?
    xhr.send(bodyRequest);

    console.log("Send: >" + bodyRequest);

    /* Обработка ответа с сервера */
    xhr.onload = () => {
        console.log("Ответ сервера получен, статус - " + xhr.status);

        if (xhr.status == 200) {
            console.log("ANSWER: " + xhr.response);
            let statusRespons = Number(xhr.response);

            if(statusRespons == 1){
                window.location.href = 'systemPark.html';
                
            }else if (statusRespons == -1){
                infoDiv.innerHTML = "Такого пользователя не существует.";
            }else{
                infoDiv = "ERROR - " + xhr.statusText;
            }
        } else {
            alert("ERROR, status: " + xhr.status + ", statusText: " + xhr.statusText);
        }
    }
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