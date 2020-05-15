let buttonSendForm = document.getElementById('registerButton');

buttonSendForm.onclick = function(){
    // event.preventDefault();
    let user = makeUserObject();
    // if(validateUser(user)){
        
    // }
    console.log(user);
    let str = "Hello 505";
    let regex1 = /\d+/;
    console.log(str.match(regex1));
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
    let str = "Hello";
    str.match()
}