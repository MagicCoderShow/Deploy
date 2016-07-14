<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<title>自动化部署平台</title>
<link rel="stylesheet" href="http://cdn.bootcss.com/materialize/0.97.0/css/materialize.min.css">
<link href="${pageContext.request.contextPath}/resources/css/icon.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑'; min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/" class="brand-logo center">自动化部署平台</a>
		</div>
	</nav>

	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
						<li class="tab col s6"><a href="#java-web-deploy">Java Web项目部署</a></li>
				</ul>
			</div>
		</div>
				<div id="java-web-deploy" class="row">
					<div class="row">
						<div class="input-field col s12 m6 offset-m2">
							<nav>
								<div class="nav-wrapper">
									<form>
										<div class="input-field">
											<input id="java-web-deploy-search" type="search">
											<label for="java-web-deploy-search">
												<i class="material-icons" style="line-height: inherit;">search</i>
											</label>
											<i class="material-icons">close</i>
										</div>
									</form>
								</div>
							</nav>
						</div>
						<div class="input-field col s12 m4">
							<a class="waves-effect waves-light btn red lighten-2" href="${pageContext.request.contextPath}/new/project" style="line-height: 64px; height: 64px;">创建</a>
						</div>
					</div>
					<table class="hoverable">
						<thead>
						<tr>
							<td>项目名称</td>
							<td>ID</td>
							<td>contextPath</td>
							<td>详情</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${projects}">
							<tr>
								<td>${item.name}</td>
								<td>${item.id}</td>
								<td>${item.contextPath}</td>
								<td><a href="${pageContext.request.contextPath}/javawebdeploy/detail/${item.id}" class="btn waves-effect waves-light red lighten-2">详情</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
		
	</div>
	
	<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
        Copyright © 2016 <a class="grey-text text-lighten-4" href="http://www.imydao.com" target="_blank">http://www.imydao.com</a>. All rights reserved.
        <a class="grey-text text-lighten-4 right" href="https://github.com" target="_blank">GitHub</a>
        </div>
      </div>
    </footer>
	
</body>
</html>