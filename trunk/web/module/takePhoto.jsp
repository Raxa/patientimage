<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/patientimage/flash/swfobject.js"></script>
<script>
    function getPageContext(){
        var fullPath = document.location.href;
        var arr = fullPath.split('/');
        var callPath = arr[0]+"/"+arr[1]+"/"+arr[2]+"/"+arr[3]+"/";
        return callPath;
    }
    
    function getPatientId(){
        var fullPath = document.location.href;
        var arr = fullPath.split('=');
        var callPath = arr[1];
        return callPath;
    }
    
</script>
<script type="text/javascript">
    <!-- For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. --> 
    var swfVersionStr = "10.0.0";
    <!-- To use express install, set to playerProductInstall.swf, otherwise the empty string. -->
    var xiSwfUrlStr = "${pageContext.request.contextPath}/moduleResources/patientimage/flash/playerProductInstall.swf";
    var flashvars = {};
    var params = {};
    params.quality = "high";
    params.bgcolor = "#ffffff";
    params.allowscriptaccess = "sameDomain";
    params.allowfullscreen = "true";
    var attributes = {};
    attributes.id = "ImageAcquirer";
    attributes.name = "ImageAcquirer";
    attributes.align = "middle";
    swfobject.embedSWF(
    "${pageContext.request.contextPath}/moduleResources/patientimage/flash/ImageAcquirer.swf", "flashContent", 
    "320", "360", 
    swfVersionStr, xiSwfUrlStr, 
    flashvars, params, attributes);
    <!-- JavaScript enabled so display the flashContent div in case it is not replaced with a swf object. -->
    swfobject.createCSS("#flashContent", "display:block;text-align:left;");
</script>
<div id="flashContent">
    <p>
        To view this page ensure that Adobe Flash Player version 10.0.0 or greater is installed. 
    </p>
</div>

<noscript>
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="320" height="360" id="ImageAcquirer">
        <param name="movie" value="ImageAcquirer.swf" />
        <param name="quality" value="high" />
        <param name="bgcolor" value="#ffffff" />
        <param name="allowScriptAccess" value="sameDomain" />
        <param name="allowFullScreen" value="true" />
        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/moduleResources/patientimage/flash/ImageAcquirer.swf" width="320" height="270">
            <param name="quality" value="high" />
            <param name="bgcolor" value="#ffffff" />
            <param name="allowScriptAccess" value="sameDomain" />
            <param name="allowFullScreen" value="true" />
            <!--<![endif]-->
            <!--[if gte IE 6]>-->
            <p> 
                Either scripts and active content are not permitted to run or Adobe Flash Player version 10.0.0 or greater is not installed.
            </p>
            <!--<![endif]-->
            <a href="http://www.adobe.com/go/getflashplayer">
                <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
            </a>
            <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
    </object>
</noscript>	