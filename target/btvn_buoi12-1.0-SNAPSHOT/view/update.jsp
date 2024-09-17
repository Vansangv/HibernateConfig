<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ngcoo.giapw_
  Date: 29-Mar-2024
  Time: 8:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
    <div>
        <form action="/sinh-vien/update?id=${detail.id}" method="post">
            Mã:<input type="text" name="ma" value="${detail.ma}"><br>
            Tên:<input type="text" name="ten" value="${detail.ten}"><br>
            Tuổi:<input type="text" name="tuoi" value="${detail.tuoi}"><br>
            Địa chỉ:<input type="text" name="diaChi" value="${detail.diaChi}"><br>
            Giới tính:
            <input type="radio" name="gioiTinh" value="1" ${detail.gioiTinh == 1 || detail.gioiTinh == null ? 'checked' : ''}>Nam
            <input type="radio" name="gioiTinh" value="0" ${detail.gioiTinh == 0 ? 'checked' : ''}>Nữ <br>
            Lớp:
            <select name="lopId">
                <c:forEach items="${lopList}" var="item">
                    <option value="${item.id}" ${item.id eq detail.lopId.id ? 'selected' : ''}>${item.ten}</option>
                </c:forEach>
            </select><br>
            <button type="submit">Update</button>
        </form>
    </div>
</body>
</html>
