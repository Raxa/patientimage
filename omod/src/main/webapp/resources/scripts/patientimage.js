/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

$j(document).ready(function() {
    $j(".takePhoto").colorbox({
        iframe:true, 
        innerWidth:360, 
        innerHeight:380
    });
    $j(".takePhoto").colorbox(
    {
        onComplete:function()
        { 
            $j("#colorbox").css('width',(parseInt($j("#colorbox").css('width'))+40)+'px');
            $j("#colorbox").css('height',(parseInt($j("#colorbox").css('height'))+40)+'px');
        },
        onClosed: function(){
            location.reload();
        }
    });
    $j(".addImage").colorbox(
    {
        onComplete:function()
        { 
            $j("#colorbox").css('width',(parseInt($j("#colorbox").css('width'))+40)+'px');
            $j("#colorbox").css('height',(parseInt($j("#colorbox").css('height'))+40)+'px');
        }
    });
    
    var html = $j("#patientDashboardHeader").html();
    var imgTable = document.createElement("table");
    imgTable.width = "99%";
    var headerRow = imgTable.insertRow(0);
    var headerCell = headerRow.insertCell(0);
    headerCell.width = "95%";
    var imgCell = headerRow.insertCell(1);
    headerCell.innerHTML = html;
    var imgThumbnail = $j('<div>').append($j('#patientimg').clone()).remove();
    $j(imgCell).append($j(imgThumbnail.html()).attr('id','imgThumbnail').attr('height','120').attr('width','120').attr('style','border: 1px solid #8FABC7'));
    $j("#patientDashboardHeader").html(imgTable);
});

function showImagePopup(){
    $j("#popup").toggle(100);
}