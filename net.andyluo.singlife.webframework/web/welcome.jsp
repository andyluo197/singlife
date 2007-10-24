<%@ page import="java.util.*" %>
<%@ page import="net.andyluo.singlife.webframework.back.*, net.andyluo.singlife.webframework.services.*" %>
<html>
<font style="background-color: #E0EEE0;"><h3>Welcome to Sing Life</h3></font>
<b>An OSGi based web framework.</b>
<hr>
SingLife is a web framework for simplifying web development. We provide a whole architecture based on OSGi. With the benefit of OSGi Service Architecture, we can easily add modules to enhance the functionality of the framework.
<jsp:useBean id="welcomeM" class="net.andyluo.singlife.webframework.back.WelcomeDescriptionM" scope="application" />
<%
ArrayList<WelcomeDescriptionService> services = welcomeM.getWelcomeDescriptionServices();
for(int index=0; index<services.size(); index++)
{
	WelcomeDescriptionService service = services.get(index);
	%><br><font style="background-color: #E0EEE0;"><h3><%=service.getTitle() %></h3></font>
<b><%=service.getSummary() %></b>
<hr><%=service.getDescription() %>
<%
 }%>
</html>