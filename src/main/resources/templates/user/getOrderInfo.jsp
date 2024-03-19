<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserInfo</title>
</head>
<body>
<h2>Information about user: </h2>
<br>
<div>Name: ${requestScope.userInfoDTO.name}</div>
<div>LastName: ${requestScope.userInfoDTO.lastName}</div>
<div>Salary: ${requestScope.userInfoDTO.salary}</div>
<br>
<h3>Order list: </h3>
<div>Product: ${requestScope.orderInfo.productName}</div>
<div>Price: ${requestScope.orderInfo.productPrice} $$$</div>
</body>
</html>