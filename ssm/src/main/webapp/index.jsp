<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/20
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/account/saveAccount" method="post">
    账户编号 <input type="text" name="accountId"/><br>
    用户名 <input type="text" name="userName"/><br>
    金额<input type="text" name="money"/><br>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
