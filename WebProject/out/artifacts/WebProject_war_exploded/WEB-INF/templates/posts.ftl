<#ftl encoding="utf-8"/>
<#include "Base.ftl">
<!DOCTYPE html>
<html lang="en">
<head>


    <title>Новости</title>

    <link href="news.css" rel="stylesheet">

</head>

<body class="page1">
<div class="container">
    <form method="get"  action="/posts">
    <div class="panel-body">
        <!--<input type="text" class="form-control" id="task-table-filter" data-action="filter"-->
        <!--data-filters="#task-table" placeholder="Filter Tasks"/>-->
        <div class="col-md-4">
            <select id="sort" name="sort" class="form-control">
                <option  value="last">Новые</option>
                <option  value="popular">Популярные</option>
            </select>
        </div>
        <div class="col-md-4">
            <input class="btn btn-success btn-outline btn-sm button2" type="submit" value="Применить"></input>
        </div>
    </div>
    </form>
    <#if posts?has_content>
        <#list posts as p>
                <div class="row">
                <div class="span8">
                    <div class="row">
                        <div class="span8">
                            <h4><strong><a href="/post?id=${p.getId()}">${p.getTitle()}</a></strong></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span2">
                            <#if p.getImage()?has_content>
                            <a href="images/${p.getImage()}" class="thumbnail">
                                <img src="images/${p.getImage()}" alt="">
                            </a>
                            </#if>
                        </div>
                        <div class="span6">
                            <p>
                            ${p.getText()} (<a href="/post?id=${p.getId()}">Читать подробнее</a>)
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="span8">
                            <p>
                                <i class="icon-user"></i> by <a href="/user?id=${p.getUser_id()}">${p.getNameUser()}</a>
                                | <i class="icon-calendar"></i> ${p.getData()}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
                <hr>
        </#list>
    <#else>
        <p>Sait have not news</p>
    </#if>



</div>


</body>
</html>