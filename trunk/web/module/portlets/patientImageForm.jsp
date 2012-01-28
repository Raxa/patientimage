<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/patientimage/css/colorbox.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/patientimage/css/patientimage.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/patientimage/scripts/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/patientimage/scripts/patientimage.js"></script>

<h2><spring:message code="patientimage.link.name" /></h2>
<br/>
<div>
    <table align="center">
        <tr>
            <td style="position: relative">
                <b class="boxHeader imageHeader">Patient Image
                    <a href="javascript: showImagePopup()" id="editImage" style="float:right;"><img src="images/add.gif"/></a>
                </b>
                <div class="box imageBox" id="imageBox" style="cursor: pointer" onclick="showImagePopup()">
                    <c:choose>
                        <c:when test="${model.patient_image == null}">
                            <img alt="" id="patientimg" src="${pageContext.request.contextPath}/moduleResources/patientimage/images/${patient.gender}.png" />
                            <div class="textOverlay">No Image Available</div>
                        </c:when>
                        <c:otherwise>
                            <img alt="" id="patientimg" height="300" width="300" src="${pageContext.request.contextPath}/moduleServlet/patientimage/ImageServlet?image=${model.patient_image}" />
                        </c:otherwise>
                    </c:choose>
                </div>
            </td>
            <td style="position: fixed;" valign="top">
                <table id="popup" style="border:1px solid #8FABC7; display: none">
                    <tr><td style="background: #8FABC7; color: white">Add/Edit Image</td></tr>
                    <tr><td><a class='addImage' href="${pageContext.request.contextPath}/module/patientimage/addImage.htm?identifier=${patient.patientId}">Upload Patient Image</a></td></tr>
                    <tr><td><a class='takePhoto' href="${pageContext.request.contextPath}/module/patientimage/takePhoto.htm?identifier=${patient.patientId}">Take Photo (requires Webcam)</a></td></tr>
                </table>
            </td>
        </tr>
    </table>
</div>