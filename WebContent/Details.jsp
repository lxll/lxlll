<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<body>
	<h3>Details</h3>
	<s:form action="backHome">
        <s:textfield name="book.isbn" label="ISBN" />
        <s:textfield name="book.title" label="Title" />
        <s:textfield name="book.publishdate" label="Publishdate" />
        <s:textfield name="book.publisher" label="Publisher" />
        <s:textfield name="book.price" label="Price" />
        <s:textfield name="author.name" label="Author Name" />
        <s:textfield name="author.age" label="Author Age" />
        <s:textfield name="author.country" label="Author Country" />
        <s:submit value="Back ListPage" />
    </s:form>
</body>
</html>