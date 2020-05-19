let buttonSendForm = document.getElementById('registerButton');

buttonSendForm.onclick = function(){
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
    // regExp выражения (шаблоны)
    let templateFullName = /^([a-zA-Z]+)$|^([а-яА-ЯёЁ]+)$/;
    let templateLoginAndPassword = /^[a-zA-Z0-9_]+$/;
    let templateEmail = /^[\w_.]{3,}@([a-z]{2,})\.[a-z]{2,4}$/;

    let divWithErrors = document.getElementById("divWithErrors");
    let statusOperation = false;

    if(templateFullName.test(user.name) != true){
        divWithErrors.innerText = "Name error";
    }else if (templateFullName.test(user.middleName) != true){
        divWithErrors.innerText = "Error in middle name";
    }else if (templateFullName.test(user.lastName) != true){
        divWithErrors.innerText = "Last name error";
    }else if (templateLoginAndPassword.test(user.login) != true){
        divWithErrors.innerText = "Error in login";
    }else if (templateLoginAndPassword.test(user.password) != true){
        divWithErrors.innerText = "Error in password";
    }else if (user.password != user.repeatPassword){
        divWithErrors.innerText = "Passwords aren't the same";
    }else if (templateEmail.test(user.email) != true){
        divWithErrors.innerText = "Error in email";
    }else{
        statusOperation = true;
        divWithErrors.innerText = "";
    }
    return statusOperation;
}

function sendDataForm(user){
    let json = JSON.stringify(user);
    console.log(json); 
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
    
            if (xhr.status == 200 || xhr.status == 304) {
                console.log("OK"); 
            } else {
                alert("ERROR");
            }
    
        }
    }

    // xhr.responseType = "text";
    xhr.open("POST", "/systempark/mainservlet", true); 
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(json);
}