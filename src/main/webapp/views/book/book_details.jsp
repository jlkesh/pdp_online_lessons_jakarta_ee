<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<div class="row p-2">
    <div class="col-md-4">
        <p><b>CODE :</b> ${book.getId()}</p>
        <h3>Title : <i>${book.getTitle()}</i></h3>
        <h3>Type : ${book.getFile().getExtension()}</h3>
        <p><b>Size:</b> ${book.getFile().getSize() / 1024/1024} MB</p>
        <p><b>Description :</b> ${book.getDescription()}</p>
        <a href="/file/download?fileID=${book.getFile().getId()}" class="btn btn-success"> Download </a>
    </div>
    <div class="col-md-8">
        <iframe src="/storage/show?filename=${book.getFile().getGeneratedName()}" width="100%" height="800px"></iframe>
    </div>

</div>

<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
