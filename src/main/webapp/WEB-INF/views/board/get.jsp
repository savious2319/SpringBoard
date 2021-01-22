<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
		<title>Board Detail</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="/resources/assets/css/noscript.css" /></noscript>
	</head>
	<style>
			
			#main > header {
				padding: 3em 0 2em 0;
			}
		</style>
<body class="is-preload">

	<!-- Page Wrapper -->
			<div id="page-wrapper">

				<!-- Header -->
					<header id="header">
						<h1><a href="/board/list${cri.getListLink()}">Board</a></h1>
					</header>

				<!-- Main -->
				<article id="main">
				<header>
							<h2 style="text-align:center">게시글 상세보기</h2>
				</header>
						<section class="wrapper style5">
							<div class="inner">
									<h3><a href="/board/list${cri.getListLink()}" class="button small">목록 보기</a></h3>
									<div class="content">
							<div class="form">
								<form method="post" action="/board/remove">
									<input type="hidden" name="pageNum" value="${cri.pageNum}">
									<input type="hidden" name="amount" value="${cri.amount}">
									<div class="fields">
										<div class="field">
											<h4>번호</h4>
											<input name="bno" type="text" value="${board.bno}" readonly/>
										</div>
										<div class="field">
											<h4>제목</h4>
											<input name="title" type="text" value="${board.title}" readonly/>
										</div>
										<div class="field">
											<h4>내용</h4>
											<textarea name="content" rows="6" style="resize:none" readonly>${board.content}</textarea>
										</div>
										<div class="field">
											<h4>작성자</h4>
											<input style="margin-bottom:4%" name="writer" type="text" value="${board.writer}" readonly/>
										</div>
										
										<c:if test="${fn:length(files) > 0}">
										<h4>첨부파일</h4>
										<div class="table-wrapper">
										<table>
											<c:forEach var="file" items="${files}">
										<tr height="30px">
											<td>
												<a href="/board/download?file_name=${file.getFileName()}">${file.getFileName()}</a>
											</td>
										</tr>
											</c:forEach>
										</table>
									</div>
									</c:if>
										
									</div>
									<ul class="actions special">
										<li>
											<input type="button" class="button" value="수정" onclick="location.href='/board/modify${cri.getListLink()}&bno=${board.bno}'"/>
											<input type="submit" class="button" value="삭제"/>
										</li>
									</ul>
								</form>
							</div>
										</div>
							
								
							</div>
						</section>
						</article>
			</div>
			<!-- Scripts -->
			<script src="/resources/assets/js/jquery.min.js"></script>
			<script src="/resources/assets/js/jquery.scrollex.min.js"></script>
			<script src="/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="/resources/assets/js/browser.min.js"></script>
			<script src="/resources/assets/js/breakpoints.min.js"></script>
			<script src="/resources/assets/js/util.js"></script>
			<script src="/resources/assets/js/main.js"></script>
</body>
</html>