<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
 <title>Book List</title>
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
 <h2>Book List</h2>
    <s:form >
        <table cellspacing="0">
            <thead>
                <tr>
                    <!-- <th>Select</th> -->
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Operation</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="books">
                    <tr>
                        <!-- <td><input type="checkbox" name="isbns" value='<s:property value="isbn" />' /></td> -->
                        <td><s:property value="isbn" /></td>
                        <td><s:property value="title" /></td>
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
    </s:form>  
    <s:form action="FindBooks">
    	<s:textfield name="authorName" label="Author" />
    	<s:submit value="find book of him/her" />
    </s:form>  
</body>
</html>