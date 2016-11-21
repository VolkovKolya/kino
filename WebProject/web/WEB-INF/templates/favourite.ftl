<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Избранное</title>
    <link href="css/news.css" rel="stylesheet">

</head>

<body class="page1">


<div class="container">


    <div class="row">
        <div id="table1" class="col-md-6 table1">
            <div id="films" class="panel panel-success">
                <div class="panel-heading panel1">
                    <h3 id="panel2" class="panel-title">Избранное </h3>
                </div>
                <table class="table table-hover" id="task-table">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Название</th>
                        <th>Жанр</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if favourite?has_content>
                        <#list favourite as f>
                    <tr>
                        <td>${f.getNumber()}</td>
                        <td><a href="/movie?id=${f.getId()}">${f.getName()}<a></td>
                        <td>${f.getGenre()}</td>
                        <#if profile.getId()==user.getId()>
                            <form method="post">
                                <td>
                                <a class="pull-right" href="#">
                                    <input type="hidden" id="delete" name="delete" value="${f.getId()}">
                                    <input type="submit" class="btn btn-success btn-outline btn-sm button2" value="Удалить"></input>
                                </a>
                                </td>
                            </form>
                        </#if>
                    </tr>
                        </#list>
                    <#else>
                    <p>Have not favourite films</p>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



</body>
</html>