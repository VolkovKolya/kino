<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<head>
    <title>Редактирование личной информации</title>
    <link href="css/registration2.css" rel="stylesheet">
</head>

<body class="page1">



<div class="container">
    <form method="post" enctype="multipart/form-data" class="form-signin" role="form" >
        <h2 class="form-signin-heading">Редактирование личной информации</h2>

    <#if err ? has_content>
        <h4 style="color: red">Error: ${err}</h4><br/>
    </#if>

        <h4>Добавить фото</h4>
        <input name="image" type="file"><br>
        <div id="fields">
            <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Имя" value="${user.getFirst_Name()}" required>
            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Фамилия" value=" ${user.getLast_Name()}" required>
            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль"  >
            <input type="password" class="form-control" id="passwordAgain" name="passwordAgain" placeholder="Повторите пароль" >
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" <#if user.getEmail()?has_content>
            value="${user.getEmail()}"
            </#if>>
            <input type="text" class="form-control" id="country" name="country" placeholder="Cтрана" <#if user.getCountry()?has_content>
            value="${user.getCountry()}"
            </#if>>
            <input type="text" class="form-control" id="city" name="city" placeholder="Город" <#if user.getCity()?has_content>
            value="${user.getCity()}"
            </#if>>
            <input type="text" class="form-control" id="about" name="about" placeholder="О себе" <#if user.getAbout()?has_content>
            value="${user.getAbout()}"
            </#if> >
        </div>
        <div id="genre">
            <p><label>Любимые жанры</label></p>
        <#if genre?has_content>
            <#list genre as g>
                <p><input type="checkbox" name="${g.getName()}" value="${g.getName()}" <#if g.getIs()?has_content>checked</#if> >${g.getName()}</p>
            </#list>
        </#if>
        </div>
        <input class="btn btn-lg btn-primary btn-block" id="registration" name="registration" type="submit" value="Сохранить изменения"/>
    </form>
</div>

</body>
</html>