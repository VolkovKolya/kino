<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<head>
    <title>Регистрация</title>
    <link href="css/registration2.css" rel="stylesheet">
</head>

<body class="page1">



<div class="container">
    <form method="post" enctype="multipart/form-data" class="form-signin" role="form" >
        <h2 class="form-signin-heading">Регистрация</h2>

        <#if err ? has_content>
        <h4 style="color: red">Error: ${err}</h4><br/>
        </#if>

        <h4>Добавить фото</h4>
        <input name="image" type="file"><br>
        <div id="fields"><input type="text" class="form-control" id="login" name="login" placeholder="Логин" required autofocus>
            <input type="text" class="form-control" id="first_name" name="first_name" placeholder="Имя" required>
            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Фамилия" required>
            <input type="password" class="form-control" id="password" name="password" placeholder="Пароль" required>
            <input type="password" class="form-control" id="passwordAgain" name="passwordAgain" placeholder="Повторите пароль" required>
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" >
            <input type="text" class="form-control" id="country" name="country" placeholder="Cтрана" >
            <input type="text" class="form-control" id="city" name="city" placeholder="Город" >
            <input type="text" class="form-control" id="about" name="about" placeholder="О себе" >
        </div>
        <div id="genre">
            <p><label>Любимые жанры</label></p>
            <#if genre?has_content>
                <#list genre as g>
                    <p><input type="checkbox" name="${g}" value="${g}">${g}</p>
                </#list>
            </#if>
        </div>
        <input class="btn btn-lg btn-primary btn-block" id="registration" name="registration" type="submit" value="Зарегистрироваться"/>
    </form>
</div>


</body>
</html>