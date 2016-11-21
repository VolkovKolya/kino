<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>


    <title>Подписчики</title>

    <link href="dialogues.css" rel="stylesheet">

</head>

<body class="page1">


<div class="container">
    <div class="table1">
        <div class="col-md-10 table_name">Подписчики</div>
        <div class="col-md-10 table_content">

            <#if users?has_content>
            <#list users as u>
            <div class="col-md-8 follower1">
                <#if u.getImage()?has_content>
                    <img src="images/${u.getImage()}" alt="ALT NAME" class="pull-left span2 clearfix" style='margin-right:10px' width="80" height="80">
                <#else>
                    <img src="http://www.clipartkid.com/images/235/home-elemental-undertakings-blog-elemental-undertakings-9opHcI-clipart.jpg" width="80" height="80" alt="ALT NAME" class="pull-left span2 clearfix" style='margin-right:10px' width="80" height="80">
                </#if>
                <div class=" col-md-4 caption1">
                    <h4>
                        <a href="/user?id=${u.getId()}">${u.getFirst_Name()} ${u.getLast_Name()}</a>
                    </h4>
                    <a href="/message?id=${u.getId()}">Написать сообщение</a>

                </div>
                <div class=" col-md-4 buttons1" align="center">
                    <#if id=user.getId()>
                        <form method="post" id="${u.getId()}">
                        <#if u.getFollower()?has_content>
                            <input type="hidden" id="subscription" name="subscription" value="${u.getId()}" form="${u.getId()}">
                            <span ><input type="submit" class="btn btn-default btn-outline follower_button" value="Отписаться" form="${u.getId()}"></input></span>
                        <#else>
                            <input type="hidden" id="follower" name="follower" value="${u.getId()}" form="${u.getId()}">
                            <span ><input type="submit" class="btn btn-default btn-outline follower_button" value="Подписаться"form="${u.getId()}"></input></span>
                        </#if>
                        </form>
                    </#if>
                </div>
            </div>
            </#list>
            <#else>
                <p>Have not followers</p>
            </#if>


        </div>
    </div>
</div>

</body>
</html>