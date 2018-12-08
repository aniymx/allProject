
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>file upload test</h2>
<form action="/upload/uploadTest" method="post" enctype="multipart/form-data">
    文件上传<input type="file" name="upload">
    <br/>
    <input type="submit" value="提交"/>
</form>
<br/>
使用跨服务器进行文件的上传
<form action="/upload/fileUploads" method="post" enctype="multipart/form-data">
    文件上传<input type="file" name="upload">
    <br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
