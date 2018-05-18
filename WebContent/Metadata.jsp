<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="helper.DBConnection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.DatabaseMetaData" %>
<%@ page import="helper.Tables" %>
<%@ page import="helper.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="style.css">
   <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<title>Metadata</title>
</head>
<%@include file="navbar.jsp"%>
<body>

<div class="div1"><center><a href="./login.jsp"><img src ="http://i68.tinypic.com/33ju1p4.png"></a></center>
		<h2>Database Metadata</h2>
<center>

<%

ArrayList<Tables> tables= new ArrayList<Tables>();

Connection connection=DBConnection.getConnection();
DatabaseMetaData databaseMetaData;
try {
	databaseMetaData = connection.getMetaData();
	ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
	System.out.println("Printing TABLE_TYPE \"TABLE\" ");
	System.out.println("----------------------------------");
	while(resultSet.next())
	{
		Tables table= new Tables();
	  
	    table.setName(resultSet.getString("TABLE_NAME"));
	    ResultSet columns = databaseMetaData.getColumns(null,null, resultSet.getString("TABLE_NAME"), null);
	    
	    ArrayList<String> attributes = new ArrayList<String>();
		ArrayList<String> types=new ArrayList<String>();
		ArrayList<String> sizes=new ArrayList<String>();
		Constants cons= new Constants();
		
	    while(columns.next())
	    {
	        String columnName = columns.getString("COLUMN_NAME");
	        attributes.add(columnName);
	        String datatype = columns.getString("DATA_TYPE");
	        types.add(cons.getType(datatype));
	        String columnsize = columns.getString("COLUMN_SIZE");
	        sizes.add(columnsize);
	        String decimaldigits = columns.getString("DECIMAL_DIGITS");
	        String isNullable = columns.getString("IS_NULLABLE");
	        String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
	      
	    }
	    
	    table.setAttributes(attributes);
	    table.setTypes(types);
	    table.setSizes(sizes);
	    tables.add(table);
	}
	
	for(Tables tab:tables)
	{
		%>
		
		<table  width="600px">
		<tr style="font-size: 25px">
			<th>Table Name: <%= tab.getName()%></th>
			</tr>
			<tr>
			<th>Attributes</th>
			<th>Types</th>
			
			</tr>
			<%for(int i=0;i<tab.getAttributes().size();i++)
			{ %>
			<tr>
			<th><%= tab.getAttributes().get(i)%></th>
			<th><%= tab.getTypes().get(i) %></th>
			
			</tr>
			<%} %>
		</table>
		<%} 
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>
</center>
</body>
</html>