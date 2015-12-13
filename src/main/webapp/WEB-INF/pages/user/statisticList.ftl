<#import "../layout.ftl" as layout>
<!DOCTYPE html>
<html lang="en">
    <@layout.header "SmartAir - Statistics"/>
<body>
<nav class="navbar" role="navigation">
    <div class="container menu-container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><img src="/resources/images/common/leave.png" height="50px" alt="logo" border="0"><img src="/resources/images/common/logo.png" height="50px" alt="logo" border="0"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right menu-text-style">
                <li><a href="/user/profile">User</a></li>
                <li><a href="/logout">Log Out</a></li>
            </ul><!--/.nav-collapse -->
        </div>

    </div>
</nav>

<div class="container" style="margin-top: 50px">



    <div class="starter-template">
        <h1 align="center" class="dark-gray-text">Statistics</h1>
        <h2 align="center" class="gray-text" style="font-size: 16px">Show statistics as a <a href="/user/statistic">chart</a></h2>

    <#assign  size =  data?size >
    <#if per_page?? >
        <#assign rows_perpage = per_page>
    <#else>
        <#assign rows_perpage = 10>
    </#if>

    <ul class="pagination">
        <#if 0 < page >
            <li> <a href="/user/page/${page-1}">prev</a></li>
        </#if>

        <#if rows_perpage-1 < size >
            <li><a href="/user/page/${page+1}">next</a>  </li>
        </#if>

    </ul>

    <#assign elementCount =0>
        <table class="table table-striped" >
            <tr>
                <th>Date/Time</th>
                <th>Posted by</th>
                <th>Temperature (*C)</th>
                <th>CO<sub>2</sub> (ppm)</th>
                <th>Humidity</th>
            </tr>
        <#list data as entity>

            <tr>
                <td >
                    <#if entity["date"]??>
                    <#--${entity["date"]?datetime?string("dd/MM/yyyy hh:mm:ss a Z")}-->
                    ${entity["date"]?datetime}
                    <#else>
                        no data
                    </#if>
               </td>
                <td ><#if entity["deviceId"]??>
                ${entity["deviceId"]}
                <#else>
                    no data
                </#if>
                </td>
                <td> <#if entity["temperature"]??>
                ${entity["temperature"]}
                <#else>
                    no data
                </#if></td>
                <td > <#if entity["co2"]??>
                ${entity["co2"]}
                <#else>
                    no data
                </#if></td>

                <td style="text-align:center;">
                    <#if entity["humidity"]??>
                    ${entity["humidity"]}
                    <#else>
                        no data
                    </#if>
                </td>
            </tr>

            <#assign elementCount=elementCount+1>
        </#list>
        </table>
        <br>

    <ul class="pagination">
        <#if tag??>
            <#if 0 < page >
                <li><a href="/tag/${tag}/${page-1}">prev</a></li>
            </#if>

            <li><a href="/tag/${tag}/${page+1}">next</a></li>
        <#else>
            <#if 0 < page >
                <li> <a href="/user/page/${page-1}">prev</a></li>
            </#if>

            <#if rows_perpage -1 < elementCount>
                <li><a href="/user/page/${page+1}">next</a></li>
            </#if>
        </#if>
    </ul>
        </div>
</div><!-- /.container -->

<@layout.scripts/>

</body>
<@layout.footer/>
</html>
