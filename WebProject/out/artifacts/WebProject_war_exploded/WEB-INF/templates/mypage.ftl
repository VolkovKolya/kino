<#ftl encoding="utf-8"/>
<#include "Base.ftl">
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Имя</title>

    <link href="news.css">

</head>

<body class="page1">


<div class="container">
    <div class="row ">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad profile1">
            <div class="panel panel-info">
                <div class="panel-heading ">
                    <h3 class="panel-title ">${profile.getFirst_Name()} ${profile.getLast_Name()}</h3>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center">
                            <#if profile.getImage()?has_content>
                                <img alt="User Pic"
                                     src="images/${profile.getImage()}"
                                     class="img-circle img-responsive"
                                     width="300" height="300">
                            <#else>
                                <img alt="User Pic"
                                     src="http://www.clipartkid.com/images/235/home-elemental-undertakings-blog-elemental-undertakings-9opHcI-clipart.jpg"
                                     class="img-circle img-responsive"
                                     width="300" height="300">
                            </#if>


                            <a href="/followers?id=${profile.getId()}" class="btn btn-primary button3">Подписчики</a>
                            <a href="/subscription?id=${profile.getId()}" class="btn btn-primary button3">Подписки</a>
                            <a href="/favourite?id=${profile.getId()}" class="btn btn-primary button3">Избранное</a>
                            <#if profile.getId()==user.getId()>
                                <a href="/postcreate" class="btn btn-primary button3">Добавить новость</a>
                            </#if>
                        </div>
                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Логин:</td>
                                    <td>${profile.getLogin()}</td>
                                </tr>
                                <tr>
                                    <td>Имя фамилия:</td>
                                    <td>${profile.getFirst_Name()} ${profile.getLast_Name()}</td>
                                </tr>
                                <tr>
                                    <td>Почта:</td>
                                    <td>
                                    <#if profile.getEmail()?has_content>
                                        ${profile.getEmail()}
                                    </#if>
                                    </td>
                                </tr>

                                <tr>
                                <tr>
                                    <td>Старана</td>
                                    <td>
                                    <#if profile.getCountry()?has_content>
                                        ${profile.getCountry()}
                                    </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Город</td>
                                    <td>
                                    <#if profile.getCity()?has_content>
                                        ${profile.getCity()}
                                    </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>О себе</td>
                                    <td>
                                    <#if profile.getAbout()?has_content>
                                        ${profile.getAbout()}
                                    </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Любимые жанры</td>
                                    <td>
                                        <#if genre?has_content>
                                            <#list genre as g>
                                            ${g},
                                            </#list>
                                        </#if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <#if profile.getId()!=user.getId()>
                                <form method="post">
                                <#if flag?has_content>
                                    <input type="hidden" id="subscription" name="subscription" value="${profile.getId()}">
                                    <span ><input type="submit" class="btn btn-primary button4" value="Отписаться"></input></span>
                                <#else>
                                    <input type="hidden" id="follower" name="follower" value="${profile.getId()}">
                                    <span ><input type="submit" class="btn btn-primary button4" value="Подписаться"></input></span>
                                </#if>

                                <a href="/message?id=${profile.getId()}" class="btn btn-primary button4">Написать сообщение</a>
                                </form>
                                <#else>
                                    <a href="/settings" class="btn btn-primary button4">Редактировать</a>
                                    <a href="/messages" class="btn btn-primary button4">Мои сообщения</a>
                            </#if>

                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>