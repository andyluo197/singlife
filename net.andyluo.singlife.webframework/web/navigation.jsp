<%@ page import="java.util.*, org.osgi.framework.*" %>
<%@ page import="net.andyluo.singlife.webframework.services.*, net.andyluo.singlife.webframework.beans.*" %>
<html>
<head>
<title>Navigation Area for SingLife</title>
<script type="text/javascript">
function displayDiv(divIndex)
{
	var display = document.getElementById("div" + divIndex).style.display;
	if(display == "none")
	{
		document.getElementById("div"+divIndex).style.display = "";
		document.getElementById("divImg" + divIndex).src = "images/expand.jpg";
	}
	else
	{
		document.getElementById("div"+divIndex).style.display = "none";
		document.getElementById("divImg" + divIndex).src = "images/folder.jpg";
	}
}
</script>
</head>
<body>
	<a href="/fw/welcome.jsp" target="content">Welcome</a>
	<jsp:useBean id="navigationM" class="net.andyluo.singlife.webframework.back.NavigationM" scope="application" />
	<%
	ArrayList<NavigationService> services = navigationM.getNavigationServices();
	for(int index=0; index<services.size(); index++)
	{
		NavigationService service = services.get(index);
		%><br><a href="#" onclick="displayDiv(<%=index%>)"><img id="divImg<%=index %>" src="images/folder.jpg" style="border:none;"><font style="background-color: #F5F5F5;"><%=service.getTitle()%></font></a>
		<div id="div<%=index%>" style="display: none;"><ul><%
		ArrayList<NavigationItem> items = service.getNavigationItems();
		for(int j=0; j<items.size(); j++)
		{
		NavigationItem item = items.get(j);
		%>
		<li>
		<a href="<%=item.getLocation()%>" target="content"><%=item.getTitle() %></a>
		</li>
	 <%
		 }%></ul></div><%
	 }%>
</body>
</html>