<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>post</title>
    <link href="news.css" rel="stylesheet">


</head>

<body class="page1">



<div class="container">
    <div class="row">

        <!-- Blog Post Content Column -->
        <div class="col-lg-8 news1">

            <!-- Blog Post -->

            <!-- Title -->
            <h1>${post.getTitle()}</h1>

            <!-- Preview Image -->
            <div class="span2">
            <#if post.getImage()?has_content>
                <a href="images/${post.getImage()}" class="thumbnail">
                    <img class="img-responsive" src="images/${post.getImage()}" alt="">
                </a>
            </#if>
            </div>

            <!-- Post Content -->
            <p class="lead">${post.getText()}</p>
            <#if post.getVideo()?has_content>
                <iframe width="560" height="315" src="${post.getVideo()}" frameborder="0" allowfullscreen></iframe>
            </#if>
            <p><span class="glyphicon glyphicon-time"></span>${post.getData()} </p>
            <!-- Author -->
            <p class="lead">
                by <a href="/user?id=${post.getUser_id()}">${post.getNameUser()}</a>
            </p>
            <h3>(${size}) likes</h3>
            <#if user?has_content>
            <form method="post">
            <#if flag="true">
                <input type="hidden" id="like" name="like" value="delete">
                <span ><input type="submit" class="like-Unlike" value="Не нравится"></input></span>
                <#else>
                    <input type="hidden" id="like" name="like" value="add">
                    <span  ><input type="submit" class="like-Unlike" value = "Мне нравится"></input></span>
            </#if>
            </form>

            </#if>

            <hr>
            <#if user?has_content>
            <div class="well">
                <h4>Напишите комментраий</h4>
                <form method="post" role="form" action="/post?id=${post.getId()}">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" id ="text" name="text"></textarea>
                    </div>
                    <input  type="submit" class="btn btn-primary" value="Отправить"></input>
                </form>
            </div>

            <hr>

            <!-- Posted Comments -->

            <#if comments?has_content>
             <#list comments as c>
            <div class="media">
                <a class="pull-left" href="#">
                    <#if c.getUserImage()?has_content>
                        <img class="media-object" src="images/${c.getUserImage()}" alt="" width="64" height="64" >
                    <#else>
                        <img class="media-object" src="http://placehold.it/64x64" alt="">
                    </#if>
                </a>
                <div class="media-body">
                    <h4 class="media-heading"><a href="/user?id=${c.getUser_id()}">${c.getUserName()}</a>
                        <small>${c.getData()}</small>
                    </h4>
                    ${c.getText()}
                </div>
            </div>
             </#list>
            <#else>
                <p>News have not comment</p>
            </#if>
            <#else>
                <h2>Чтобы добавлять/просматривать коментарий авторизируйтесь</h2>
            </#if>

        </div>
    </div>
</div>


</div>


</body>
</html>