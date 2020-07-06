<%@ page import="model.entity.Plant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="current_plants">
    <h2>Растения выбранного парка:</h2>
    <table cellspacing="1" class="table_parks">
        <tr>
            <th>Plant's name</th> <th>Description</th> <th>Planting</th> <th>Survey</th> <th>Delete</th>
        </tr>
        <%
            //получаю список парков - для определённого пользователя
            List<Plant> plants = (List<Plant>) request.getSession().getAttribute("plants");
            for (Plant plant : plants) {
                out.print("<tr>" +
                            "<td>"+plant.getName()+"</td>" +
                            "<td>"+plant.getDescription()+"</td>" +
                            "<td>"+plant.getPlanting()+"</td>" +
                            "<td>"+plant.getSurvey()+"</td>" +
                            "<td><button class='but_del_plant' value="+plant.getId()+">X</button></td>"+
                        "</tr>");
            }
        %>
    </table>
    <button class="return_in_parks">Return back</button>
</div>

<div class="addition_plants">
    <p>Name new plant: <input type="text" class="name_new_plant"></p>
    <p>Description: <input type="text" class="description_new_plant"></p>

    <input type="button" value="Add plant" class="add_new_plant">
</div>
