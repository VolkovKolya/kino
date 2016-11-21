<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Название фильма</title>

    <link href="bootstrap.css" rel="stylesheet">
    <link href="login.css" rel="stylesheet">
    <link href="css/news.css" rel="stylesheet">

</head>

<body class="page1">


<div class="container">
    <div class="row">

        <div id="film1" class="col-lg-8">

            <h1>${film.getName()}</h1>

            <!-- Preview Image -->
            <div class="span2">
            <#if film.getImage()?has_content>
                <a href="images/${film.getImage()}" class="thumbnail">
                    <img class="img-responsive" src="images/${film.getImage()}" alt="">
                </a>
            </#if>
            </div>


            <div class=" col-md-9 col-lg-9 ">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td>Название:</td>
                        <td>${film.getName()}</td>
                    </tr>
                    <tr>
                        <td>Год:</td>
                        <td>${film.getYear()}</td>
                    </tr>

                    <tr>
                        <td>Старана:</td>
                        <td>${film.getCountry()}</td>
                    </tr>
                    <tr>
                        <td>Режиссёр:</td>
                        <td>${film.getProducer()}</td>
                    </tr>
                    <tr>
                        <td>Актеры:</td>
                        <td>${film.getActors()}</td>
                    </tr>
                    <tr>
                        <td>Жанр</td>
                        <td><#if film.getGenre()?has_content>
                        ${film.getGenre()}
                        </#if></td>
                    </tr>
                    <tr>
                        <td>Средняя оценка</td>
                        <td>
                        <#if film.getEstimate()?has_content>
                        ${film.getEstimate()}
                        </#if>
                        </td>

                    </tr>
                    </tbody>
                </table>
                <h3>Описание</h3>
                <p>${film.getDescription()}</p>
                <p>
                <h3>Трейлер</h3>
                <#if film.getVideo()?has_content>
                <iframe width="560" height="315" src="${film.getVideo()}" frameborder="0" allowfullscreen></iframe>
                </#if>

                <hr>





                <hr>
            <#if user?has_content>
                <div class="well">
                    <h4>Напишите комментраий</h4>
                    <form id="comment" method="post" role="form" >
                        <div class="form-group">
                            <textarea class="form-control" rows="3" id ="text" name="text" form="comment"></textarea>
                        </div>
                        <input  type="submit" class="btn btn-primary" value="Отправить" form="comment"></input>
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
                    <p>Film have not comment</p>
                </#if>
            <#else>
                <h2>Чтобы добавлять/просматривать коментарий авторизируйтесь</h2>
            </#if>

            </div>
        </div>
    </div>

    <#if user?has_content>
    <div class=" buttons">
        <form method="post" id="favourite">
        <#if fav?has_content>
            <input type="hidden" id="subscription" name="delete" value="${film.getId()}" form="favourite">
            <span ><input type="submit" class="btn btn-primary" value="Удалить из избранного" form="favourite"></input></span>
        <#else>
            <input type="hidden" id="follower" name="add" value="${film.getId()}" form="favourite">
            <span ><input type="submit" class="btn btn-primary" value="Добавить в избранное"form="favourite"></input></span>
        </#if>
        </form>
        <form method="post" id="estimate">
            <#if est?has_content>
                <input id="estimate" name="estimate" type="number" placeholder="Ваша оценка = ${est.getEstimate()} " class="form-control input-md" max="10" min="0">
                <span ><input type="submit" class="btn btn-primary" value="Изменить оценку"form="estimate"></input></span>
            <#else>
                <input id="estimate" name="estimate" type="number" placeholder="Ваша оценка" class="form-control input-md" max="10" min="0">
                <span ><input type="submit" class="btn btn-primary" value="Оценить"form="estimate"></input></span>
            </#if>
        </form>
    </div>
    </#if>
</div>



</body>
</html>