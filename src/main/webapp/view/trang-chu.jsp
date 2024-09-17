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
    <title>Trang chủ</title>
</head>
<body>
    <div>
        <form action="/sinh-vien/search">
            Tên:<input type="text" name="value"><br>
            Lớp:
            <select name="lopId">
                <c:forEach items="${lopList}" var="item">
                    <option value="${item.id}">${item.ma}</option>
                </c:forEach>
            </select><br>
            <button type="submit">Search</button>
            <a href="/sinh-vien/top3"><button type="button">Top 3</button></a>
        </form>
    </div>
    <div>
        <form action="/sinh-vien/add" method="post">
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
            <button type="submit">Add</button>
        </form>
    </div>
    <table border="1">
        <thead>
            <th>ID</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Tuổi</th>
            <th>Địa chỉ</th>
            <th>Giới tính</th>
            <th>Lớp</th>
            <th>Action</th>
        </thead>
        <tbody>
        <c:forEach items="${sinhVienList}" var="item" varStatus="loop">
            <tr>
                <td>${item.id}</td>
                <td>${item.ma}</td>
                <td>${item.ten}</td>
                <td>${item.tuoi}</td>
                <td>${item.diaChi}</td>
                <td>${item.gioiTinh == 1 ? "Nam" : "Nữ"}</td>
                <td>${item.lopId.ten}</td>
                <td>
                    <a href="/sinh-vien/detail?id=${item.id}"><button>Detail</button></a>
                    <a href="/sinh-vien/view-update?id=${item.id}"><button>Update</button></a>
                    <button type="button" onclick="deleteClick(${item.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <script>
        function deleteClick(id) {
            let confirm = window.confirm("Bạn có chắc muốn xóa không?");
            if(confirm) {
                window.location.href = '/sinh-vien/delete?id=' + id;
            }
        }
    </script>

</body>
</html>
