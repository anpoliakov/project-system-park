<%@ page import="model.entity.Park" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="current_parks">
    <h2>Ваши парки:</h2>
    <table cellspacing="1" class="table_parks">
        <tr>
            <th>Park's name</th> <th>Show plants</th> </th> <th>Delete</th>
        </tr>
        <%
            //получаю список парков - для определённого пользователя
            List<Park> parks = (List<Park>) request.getSession().getAttribute("parks");
            for (Park park : parks) {
                out.print("<tr>" +
                            "<td>"+park.getName()+"</td>" +
                            "<td><button class='but_show_plants' value="+park.getId()+"> SHOW </button></td>" +
                            "<td><button class='but_del_park' value="+park.getId()+">X</button></td>"+
                        "</tr>");
            }
        %>
    </table>
</div>

<div class="addition_parks">
    <p>New park's name: <input type="text" class="name_new_park"></p>
    <input type="button" value="Add park" class="add_new_park">
</div>


