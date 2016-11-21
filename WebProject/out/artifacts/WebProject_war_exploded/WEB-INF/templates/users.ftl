<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Пользователи</title>

    <link href="css/dialogues.css" rel="stylesheet">

    <script type="application/javascript">
        var f = function() {
            $.ajax({
                'url': '/ajax-search',
                'data': {
                    'q': $("#q").val()
                },
                'method': 'get',
                'success': function(obj) {
                    $("#res").html(obj.result.join(", "));
                }
            });
        }
    </script>
</head>

<body class="page1">


<div class="container">
    <div class="table1">
        <div class="col-md-10 table_name">Пользователи</div>
        <div class="col-md-10 table_content">
            <div class="row">
                <div class="col-md-4 col-md-offset-3 input1">
                    <form action="" class="search-form">
                        <div class="input-group" style="width: 350px;">
                            <input type="text" autocomplete="off" class="form-control" placeholder="введите имя" name="search" id="q" oninput="f()">
                    <span class="input-group-btn">
                        <button class="btn" id="srchbtn" type="btn-default">
                            <i class="fa fa-search" aria-hidden="true">
                                <span class="glyphicon glyphicon-search"></span>
                            </i>
                        </button>
                    </span>

                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="res"/>
                </div>
            </div>
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
                        <#if user?has_content>
                        <a href="/message?id=${u.getId()}">Написать сообщение</a>
                        </#if>
                    </div>
                    <div class=" col-md-4 buttons1" align="center">
                        <#if user?has_content>
                            <#if u.getId()!= user.getId()>
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
                        </#if>
                    </div>
                </div>
            </#list>
        <#else>
            <p>Users not found</p>
        </#if>

        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</body>
</html>