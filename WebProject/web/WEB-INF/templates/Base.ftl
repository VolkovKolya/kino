<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sticky-footer-navbar.css" rel="stylesheet">


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>

<body class="page1">

<!-- Fixed navbar -->
<div id="header1" class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand logo1" href="/posts"><img src="images/logo.jpg" height=50px width=130px></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav menu1">
                <li><a href="/posts">Новости</a></li>
                <li><a href="/movies">Фильмы</a></li>
                <li><a href="/users">Пользователи</a></li>
                <#if user?has_content>
                    <li><a href="/user?id=${user.getId()}">Моя страница</a></li>
                </#if>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Вход/Выход <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <#if user?has_content>
                            <li><a href="/logout">Выйти</a></li>
                        <#else>
                            <li><a href="/login" data-toggle="modal" data-target="#login-modal">Войти</a></li>
                        </#if>
                    </ul>
                </li>
                <li><h3 style="color: red"> <#if err?has_content>
                    <p>${err}</p>
                </#if></h3></li>
            </ul>
        </div>
    </div>
</div>
<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="display: none;">
    <div class="modal-dialog">
        <div class="loginmodal-container">
            <h1>Войдите в свой аккаунт</h1><br>
            <form  method="post"  action="/login">

                <input type="text" id="login" name="login" placeholder="Логин">
                <input type="password" id = "password" name="password" placeholder="Пароль">
                <label>
                    <input type="checkbox" id="remember" name="remember" checked> Запомнить меня
                </label>
                <input type="submit" name="login" class="login loginmodal-submit" value="Войти">
            </form>

            <div class="login-help">
                <a href="/registration" class="registration_label" value="Регистрация">Регистрация</a>
            </div>
        </div>
    </div>
</div>

<div class="container">

</div>

<div id="footer">
    <div class="container">
        <p class="text-muted">Это знак. Плыви обратно, если еще помнишь, как.</p>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</body>
</html>