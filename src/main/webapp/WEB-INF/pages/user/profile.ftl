<#import "layout.ftl" as layout>
<!DOCTYPE html>
<html lang="en">
    <#if username??>
        <@layout.header "Profile ${username}"/>
    <#else>
        <@layout.header "Profile"/>
    </#if>
<body>
    <#if username??>
        <@layout.user_menu "${user_id}" "${username}"/>
    <#else>
        <@layout.user_menu "${user_id}" "User" />
    </#if>
<div class="container" style="padding: 30px; width: 100%">
    <#if devices_count??>
        <#if devices_count == 0>
        <div class="row">
            <div class="thumbnail">
                <p align="center">You don't have any devices.</p>
                <p align="center"><a href="/user/addDevice?user_id=${user_id}" class="btn btn-primary" role="button">Add a device</a></p>
            </div>
        </div>
    <#else>
        <p align="right" style="margin-top: 10px">You have ${devices_count} devices.</p>
        <div class="row">
            <#assign elementCount =0>
            <#if devices??>
            <#list devices as device>
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail">
                        <img src="/resources/images/lamp_small.png" alt="Living room" border="0">
                        <div class="caption">
                            <h3>
                                <#if device["deviceName"]??>
                                ${device["deviceName"]}
                                <#else>
                                    no data
                                </#if>
                            </h3>
                            <p>
                            <#if device["co2Min"]??>
                                CO<sub>2</sub> minimum level: ${device["co2Min"]} ppm.
                            </#if>
                            </p><p>
                            <#if device["delay"]??>
                                Delay: ${device["delay"]} milliseconds.
                            </#if>
                            </p>
                            <p><a href="/user/statistic" class="btn btn-primary" role="button">Chart</a> <a href="/user" class="btn btn-default" role="button">Table</a></p>
                        </div>
                    </div>
                </div>
                <#assign elementCount=elementCount+1>
            </#list>
            </#if>
        </div>
    </#if>
    </#if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
<@layout.footer />
</html>
