
<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Что у вас нового?</title>

</head>

<body class="page1">


<div class="container">
    <div class="table1">
        <div class="col-md-10 table_name">Что у вас нового?</div>
        <div class="col-md-10 table_content">
        <#if err ? has_content>
            <h4 style="color: red">Error: ${err}</h4><br/>
        </#if>
            <h4>Текст</h4>
            <form method="post"  enctype="multipart/form-data"  action="/postcreate">
            <div class="form-group">
                <textarea class="form-control" rows="3" placeholder="Введите текст" id="text" name="text"></textarea>
            </div>

            <h4>Заголовок</h4>
            <div class="form-group">
                <textarea class="form-control" rows="1" placeholder="Введите заголовок" id="title" name="title"></textarea>
            </div>
            <h4>Добавить фото</h4>
            <input  id="image" name="image" type="file"><br>
            <h4>Добавить видео </h4>
            <input id="video" name="video" type="text"><br>
                <br>
                <input class="btn btn-default share_button1" id="create" name="create" type="submit" value="Поделиться"/>
            </form>
        </div>
    </div>
</div>


</body>
</html>