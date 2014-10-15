<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
iiiiiiiiiii
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>Find Books hehe</title>
    <style type="text/css">
        table {
            border: 1px solid black;
            border-collapse: collapse;
        }
        
        table thead tr th {
            border: 1px solid black;
            padding: 3px;
            background-color: #cccccc;
        }
        
        table tbody tr td {
            border: 1px solid black;
            padding: 3px;
        }
    </style>
</head>
<body>
	<s:form action="backHome">
    	<table cellspacing="0">
            <thead>
                <tr>
                    <!-- <th>Select</th> -->
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>PublishDate</th>
                    <th>Publisher</th>
                    <th>Price</th>
                    <th>Operation</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="booksFind">
                	<tr>
                        <td><s:property value="isbn" /></td>
                        <td><s:property value="title" /></td>
                        <td><s:property value="publishdate" /></td>
                        <td><s:property value="publisher" /></td>
                        <td><s:property value="price" /></td>
                        <td>
                            <a href='<s:url action="Details"><s:param name="isbn" value="isbn" /></s:url>'>
                                Details
                            </a>
                            &nbsp;
                            <a href='<s:url action="Delete"><s:param name="isbn" value="isbn" /></s:url>'>
                                Delete
                            </a>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <s:submit value="Back ListPage" />
    </s:form>  
</body>
</html>
