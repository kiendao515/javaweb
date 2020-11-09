<%----%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File</title>
</head>
<body>
<h1>Upload file with servlet </h1>
<form action="api/v1/upload-file/" method="post" enctype="multipart/form-data">
    <label>Select file </label>
    <input type="file" name="file" multiple="multiple">
    <input type="submit" value="Upload">
</form>
</body>
</html>
