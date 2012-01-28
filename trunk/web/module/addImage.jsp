<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/patientimage/scripts/jquery.form.js"></script>
<script type="text/javascript">
    $j(document).ready(function() {
        var options = {
            target:'#uploadMessage',
            success: showResponse,
            clearForm: true
        };
        $j('#uploadForm').ajaxForm(options);
    });
    
    function showResponse(responseText, statusText){
        location.reload();
    }
</script>

<form id="uploadForm" 
      action="${pageContext.request.contextPath}/moduleServlet/patientimage/PatientImageUpload?identifier=<%=request.getParameter("identifier")%>"  
      method="post" enctype="multipart/form-data">
    <input size="30" type="file" name="file"/><br/>
    <input type="submit" value="Upload Image" />
</form>
<div id="uploadMessage"></div>