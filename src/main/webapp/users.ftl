<html lang="en">
<#include "base.ftl">

<#macro  title>Users</#macro>

<#macro content>

    <#if users?has_content>
        Таблица юзеров:
        <br>
        <br>
        Имя Фамилия Логин
        <br>
        <#list users as u>
            ${u.name} ${u.lastName} ${u.login}
            <br>
        </#list>
    </#if>


</#macro>
</html>