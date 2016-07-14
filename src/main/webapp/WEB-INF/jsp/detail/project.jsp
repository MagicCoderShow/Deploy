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
<style type="text/css">
	.spinner {
		margin: 100px auto;
		width: 32px;
		height: 32px;
		position: relative;
	}

	.cube1, .cube2 {
		background-color: #67CF22;
		width: 30px;
		height: 30px;
		position: absolute;
		top: 0;
		left: 0;

		-webkit-animation: cubemove 1.8s infinite ease-in-out;
		animation: cubemove 1.8s infinite ease-in-out;
	}

	.cube2 {
		-webkit-animation-delay: -0.9s;
		animation-delay: -0.9s;
	}

	@-webkit-keyframes cubemove {
		25% { -webkit-transform: translateX(42px) rotate(-90deg) scale(0.5) }
		50% { -webkit-transform: translateX(42px) translateY(42px) rotate(-180deg) }
		75% { -webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5) }
		100% { -webkit-transform: rotate(-360deg) }
	}

	@keyframes cubemove {
		25% {
			transform: translateX(42px) rotate(-90deg) scale(0.5);
			-webkit-transform: translateX(42px) rotate(-90deg) scale(0.5);
		}
		50% {
			transform: translateX(42px) translateY(42px) rotate(-179deg);
			-webkit-transform: translateX(42px) translateY(42px) rotate(-179deg);
		}
		50.1% {
			transform: translateX(42px) translateY(42px) rotate(-180deg);
			-webkit-transform: translateX(42px) translateY(42px) rotate(-180deg);
		}
		75% {
			transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
			-webkit-transform: translateX(0px) translateY(42px) rotate(-270deg) scale(0.5);
		}
		100% {
			transform: rotate(-360deg);
			-webkit-transform: rotate(-360deg);
		}
	}
</style>
<script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/materialize/0.97.0/js/materialize.min.js"></script>
<script type="text/javascript">
	var DEPLOY_PATH = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/detail.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑';min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/" class="brand-logo center">自动化部署平台</a>
		</div>
	</nav>

	<div class="container" style="padding-top: 20px; width: 90%;flex: 1 0 auto;">
		
		<div class="row">
			<div class="col s12">
				<div class="card">
					<div class="card-content">
						<span class="card-title red-text">${project.name}</span>
						<p>名称：${project.name}</p>
						<p>ID：<span id="text-uuid">${project.id}</span></p>
						<p>Maven profile：${project.profile}</p>
						<p>contextPath：${project.contextPath}</p>
						<p>端口号：${project.port}</p>
						<c:if test="${project.type == 1}">
						<p>SVN地址：${project.url}</p>
						</c:if>
						<c:if test="${project.type == 2}">
						<p>GIT地址：${project.url}</p>
						</c:if>
						<p>WAR：<a target="_blank" href="${pageContext.request.contextPath}/upload?filepath=${project.war}">${project.filename}</a></p>
					</div>
					<div class="input-field col s12 m4">
							<a class="waves-effect waves-light btn red lighten-2" href="${pageContext.request.contextPath}/new/linux?projectid=${project.id}" style="line-height: 64px; height: 64px;">创建linux服务器</a>
						</div>
					<table class="hoverable">
						<thead>
						<tr>
							<td>服务器IP</td>
							<td>服务器路径</td>
							<td>version</td>
							<td>详情</td>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${linuxs}">
							<tr>
								<td>${item.ip}</td>
								<td>${item.path}</td>
								<td>${item.version}</td>
								<td>
									<a href="${pageContext.request.contextPath}/detail/linux/${item.id}" class="btn waves-effect waves-light red lighten-2">详情</a>
									<a href="${pageContext.request.contextPath}/new/linux?id=${item.id}" class="btn waves-effect waves-light red lighten-2">编辑</a>
									<a href="${pageContext.request.contextPath}/delete/linux?id=${item.id}&projectid=${item.projectid}" class="btn waves-effect waves-light red lighten-2">删除</a>
									<a class="btn waves-light waves-effect white-text" id="btn-deploy-linux" data-linuxid="${item.id }">部署</a>
									<a class="btn waves-light waves-effect white-text" id="btn-restart-linux" data-linuxid="${item.id }">重启</a>
									<a class="btn waves-light waves-effect white-text" id="btn-stop-linux" data-linuxid="${item.id }">停止</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="card-action">
						<p>
							<a class="btn waves-light waves-effect white-text" id="btn-deploy">部署</a>
							<a class="btn waves-light waves-effect white-text" id="btn-restart">重启</a>
							<a class="btn waves-light waves-effect white-text" id="btn-stop">停止</a>
						</p>
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

	<div id="loader-modal" class="modal" style="background-color: transparent; box-shadow: none;">
		<div class="modal-content">
			<div class="spinner">
				<div class="cube1"></div>
				<div class="cube2"></div>
			</div>
		</div>
	</div>

	<div id="layer-modal" class="modal modal-fixed-footer">
		<div class="modal-content" style="background-color: black; color: #ccc; font-size: 0.9em;">
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>

	<div id="alert-modal" class="modal">
		<div class="modal-content">
			<p class="grey-text">提示</p>
			<p class="text-alert"></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>

</body>
</html>