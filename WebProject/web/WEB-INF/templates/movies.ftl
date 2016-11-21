<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>


    <title>Фильмы</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="login.css" rel="stylesheet">
    <link href="css/news.css" rel="stylesheet">

</head>

<body class="page1">



<div class="container">
    <div class="search1">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <h4>Найти фильм</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-3 input1">
                <form action="" class="search-form">
                    <div class="input-group" style="width: 200px;">
                        <input type="text" autocomplete="off" class="form-control" placeholder="введите название" name="search" id="search" >
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

    </div>

    <div class="row">
        <div class="col-md-6 table1">
            <div class="panel panel-success">
                <div class="panel-heading panel1">
                    <h3 class="panel-title">Фильмы</h3>
                </div>
                <div class="panel-body">
                    <!--<input type="text" class="form-control" id="task-table-filter" data-action="filter"-->
                    <!--data-filters="#task-table" placeholder="Filter Tasks"/>-->
                    <form method="get" id="genres">
                    <div class="col-md-4">
                        Выберете жанр
                        <select id="radius" name="genre" class="form-control">
                            <option name="все" value="">все</option>
                            <#if gg?has_content>
                            <#list gg as g>
                                <option name="${g}" value="${g}">${g}</option>
                            </#list>
                            </#if>

                        </select>
                    </div>
                    <div class="col-md-4">
                        <input class="btn btn-success btn-outline btn-sm button2" form="genres"  type="submit" value="Применить"></input>
                    </div>
                    </form>
                </div>
                <table class="table table-hover" id="task-table">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Название</th>
                        <th>Жанр</th>
                        <th>Оценка пользователей</th>
                    </tr>
                    </thead>
                    <tbody>

                    <#if films?has_content>
                        <#list films as f>
                        <tr>
                            <td>${f.getNumber()}</td>
                            <td><a href="/movie?id=${f.getId()}">${f.getName()}<a></td>
                            <td><#if f.getGenre()?has_content>${f.getGenre()}</#if></td>
                            <td><#if f.getEstimate()?has_content>${f.getEstimate()}</#if> </td>

                        </tr>
                        </#list>
                    <#else>
                    <p>Sait have not films</p>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>