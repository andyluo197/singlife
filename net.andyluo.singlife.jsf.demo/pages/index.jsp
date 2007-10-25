<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>第一个</title>
</head>
<body>
   <f:view>
       <h:form>
       <h:outputText style="color:red" value="#{user.errMsg}" />
           <h3>名称</h3>
           名称： <h:inputText value="#{user.id}"/><p>
           <h:commandButton value="提交" action="#{user.verify}"/>
       </h:form>
   </f:view>
</body>
</html>