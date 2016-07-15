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
<script src="${pageContext.request.contextPath}/resources/js/javawebdeploy/new.js"></script>
</head>
<body style="font-family: 'Roboto', 'Droid Sans Fallback', '微软雅黑';min-height: 100vh;display: flex;flex-direction: column;">

	<nav>
		<div class="nav-wrapper">
			<a href="${pageContext.request.contextPath}/" class="brand-logo center">自动化部署平台</a>
		</div>
	</nav>

	<form id="form-new" method="post" action="${pageContext.request.contextPath}/edit/linux" style="flex: 1 0 auto;">
		<div class="container" style="padding-top: 20px;">

			<div class="row">
				<div class="col s12">
					<div class="card">
						<div class="card-content">
							<span class="card-title black-text">新建Linux服务器</span>
								<input type="hidden" name="id" value="${linux.id }">
								<input type="hidden" name="projectid" value="${linux.projectid }">
								<div class="row">
									<div class="input-field col s6">
										<input type="text" id="input-ip" name="ip" value="${linux.ip }">
										<label for="input-ip">IP</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-loginname" name="loginname" value="${linux.loginname }">
										<label for="input-loginname">loginname</label>
									</div>
									<div class="input-field col s6">
										<input type="password" id="input-password" name="password" value="${linux.password}">
										<label for="input-password">password</label>
									</div>
									<div class="input-field col s6">
										<input type="text" id="input-version" name="version" value="${linux.version }">
										<label for="input-version">version</label>
									</div>
									<div class="input-field col s12">
										<input type="text" id="input-path" name="path" value="${linux.path }">
										<label for="input-path">WEB服务器路径</label>
									</div>
								</div>

						</div>
						<div class="card-action">
							<p>
								<button type="submit" class="btn waves-light waves-effect white-text">提交</button>
							</p>
						</div>
					</div>
				</div>
			</div>

		</div>
	</form>
	
	<footer class="page-footer" style="padding-top: 0; margin-top: 40px;">
      <div class="footer-copyright">
        <div class="container">
 www.imydao.comright © 2016 <a class="grwww.imydao.comt-lighten-4" href="http://xxgblog.com" target="_blank">http://xxgblog.com</a>. All rights reserved.
        <a class="grey-text text-lighten-4 right" href="https://github.com" target="_blank">GitHub</a>
        </div>
      </div>
    </footer>

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