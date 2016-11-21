
<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Добавление нового фильма</title>

</head>

<body class="page1">


<div class="container">
    <div class="table1">
        <div class="col-md-10 table_name">Добавление нового фильма</div>
        <div class="col-md-10 table_content">
        <#if err ? has_content>
            <h4 style="color: red">Error: ${err}</h4><br/>
        </#if>
            <h4>Опсиание</h4>
            <form method="post"  enctype="multipart/form-data"  >
                <div class="form-group">
                    <textarea class="form-control" rows="3" placeholder="Введите описание" id="description" name="description"></textarea>
                </div>

                <h4>Название</h4>
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name"  required>
                </div>
                <h4>Продюсер</h4>
                <div class="form-group">
                    <input type="text" class="form-control" id="producer" name="producer"  required>
                </div>
                <h4>Актеры</h4>
                <div class="form-group">
                    <input type="text" class="form-control" id="actors" name="actors"  required>
                </div>
                <h4>Год выхода в прокат</h4>
                <div class="form-group">
                    <input type="text" class="form-control" id="year" name="year"  required>
                </div>
                <h4>Страна</h4>
                <div class="form-group">
                    <input type="text" class="form-control" id="country" name="country"  required>
                </div>
                <h4>Добавить фото</h4>
                <input  id="image" name="image" type="file"><br>
                <h4>Добавить видео </h4>
                <input id="video" name="video" type="text"><br>
                <br>
                <div id="genre">
                    <p><label>Жанр фильма</label></p>
                <#if genre?has_content>
                    <#list genre as g>
                        <p><input type="checkbox" name="${g}" value="${g}">${g}</p>
                    </#list>
                </#if>
                </div>
                <br>
                <input class="btn btn-default share_button1" id="create" name="create" type="submit" value="Поделиться"/>
            </form>
        </div>
    </div>
</div>


</body>
</html>