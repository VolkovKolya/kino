<#ftl encoding="utf-8"/>
<#include "Base.ftl">

<!DOCTYPE html>
<html lang="en">
<head>


    <title>Мои сообщения</title>

    <link href="dialogues.css" rel="stylesheet">

</head>

<body class="page1">

<div class="container">
    <div class="row">
        <div class="box-body no-padding">
            <div class="mailbox-controls">
                <h2 align=center>Мои Сообщения</h2>
            </div>
            <hr>
            <div class="table-responsive mailbox-messages">
                <table class="table table-hover table-striped">
                    <tbody>
                    <#if message?has_content>
                        <#list message as m>

                            <tr>
                                <td class="mailbox-image">
                                    <#if m.getUser().getImage()?has_content>
                                        <img class="media-object" src="images/${m.getUser().getImage()}" alt="" width="64" height="64" >
                                    <#else>
                                        <img class="media-object" src="http://placehold.it/64x64" alt="">
                                    </#if>
                                </td>
                                <td class="mailbox-star"><a href="#"><i class="fa fa-star text-yellow"></i></a></td>
                                <td class="mailbox-name"><a href="/message?id=${m.getUser().getId()}"><h4>${m.getUser().getFirst_Name()} ${m.getUser().getLast_Name()}<h4></a></td>
                                <td class="mailbox-subject"><h4><b>${m.getUserName()} </b> : ${m.getText()}</h4></td>
                                <td class="mailbox-attachment"></td>
                                <td class="mailbox-date">${m.getData()}</td>
                            </tr>
                        </#list>
                    <#else>
                    <p>Have not messages</p>
                    </#if>

                    </tbody>
                </table><!-- /.table -->
            </div><!-- /.mail-box-messages -->
        </div><!-- /.box-body -->
        <hr>


        <div class="box-footer no-padding">
            <div class="mailbox-controls">
            </div>
        </div>
    </div><!-- /. box -->
</div><!-- /.col -->
</div><!-- /.row -->
</div>
</div>

</body>
</html>