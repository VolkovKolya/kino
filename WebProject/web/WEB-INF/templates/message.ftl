<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Диалог</title>
    <link href="dialogues.css" rel="stylesheet">
</head>

<body class="page1">


<div class="container">

    <div class="table1">
        <div class="col-md-10 table_name">${profile.getFirst_Name()} ${profile.getLast_Name()}</div>
        <div class="col-md-10 table_content">
            <#if message?has_content>
                <#list message as m>
            <div class="col-md-8 message1">
                <a class="pull-left" href="#">
                    <#if m.getUserImage()?has_content>
                        <img class="media-object" src="images/${m.getUserImage()}" alt="" width="64" height="64" >
                    <#else>
                        <img class="media-object" src="http://placehold.it/64x64" alt="">
                    </#if>
                </a>
                <label><p><a href="/user?id=${m.getFrom_user_id()}">${m.getUserName()} </a> ${m.getData()} </p></label>
                    <#if m.getFrom_user_id()==user.getId()>
                <form method="post">
                    <a class="pull-right" href="#">
                        <input type="hidden" id="delete" name="delete" value="${m.getId()}">
                        <input type="submit" class="btn btn-success btn-outline btn-sm button2" value="Удалить сообщение"></input>
                    </a>
                </form>
                    </#if>
                <p>${m.getText()}</p>

            </div>
                </#list>
            <#else>
                <p>Have not messages</p>
            </#if>


            <div class="well1">
                <form method="post" role="form" class="yourmessage">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" id ="text" name="text"></textarea>
                    </div>
                    <input  type="submit" class="btn btn-primary" value="Отправить"></input>
                </form>
            </div>

        </div>


    </div>
</div>
</div>


</body>
</html>