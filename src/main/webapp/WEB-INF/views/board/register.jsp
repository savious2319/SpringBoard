<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<title>Board Registration</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="/resources/assets/css/noscript.css" /></noscript>
	</head>
		<style>
			
			#main > header {
				padding: 3em 0 2em 0;
			}
			
			input{
				color:black;
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
							<h2 style="text-align:center">게시글 작성하기</h2>
				</header>
						<section class="wrapper style5">
							<div class="inner">
									<h3><a href="/board/list${cri.getListLink()}" class="button small">목록 보기</a></h3>
								<div class="content">
							<div class="form">
								<form method="post" action="/board/register" id="registForm" enctype="multipart/form-data">

									<div class="fields">
										<div class="field">
											<h4>제목</h4>
											<input name="title" placeholder="Title" type="text" />
										</div>
										<div class="field">
											<h4>내용</h4>
											<textarea name="content" rows="6" placeholder="Content" style="resize:none"></textarea>
										</div>
										<div class="field">
											<h4>작성자</h4>
											<input style="margin-bottom:4%" name="writer" placeholder="Writer" type="text" />
										</div>
										<div class="table-wrapper">
										<table>
										<tr height="30px">
												<td align="center" width="150px">
													<div align="center">파일 첨부</div>
												</td>
												<td style="padding-left: 10px;"><input name="board_file1"
													type="file" /> <input type="button" value="파일 첨부 삭제"
													onclick="resetFile($('input[name=board_file1]'))" /></td>
										</tr>
										<tr height="30px">
											<td align="center" width="150px">
												<div align="center">파일 첨부</div>
											</td>
											<td style="padding-left: 10px;"><input name="board_file2"
												type="file" /> <input type="button" value="파일 첨부 삭제"
												onclick="resetFile($('input[name=board_file2]'))" /></td>
										</tr>
										<tr height="30px">
											<td align="center" width="150px">
												<div align="center">파일 첨부</div>
											</td>
											<td style="padding-left: 10px;"><input name="board_file3"
												type="file" /> <input type="button" value="파일 첨부 삭제"
												onclick="resetFile($('input[name=board_file3]'))" /></td>
										</tr>
										</table>
									</div>
										
									</div>
									
									<ul class="actions special">
										<li><input type="submit" class="button" value="등록" /></li>
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

			<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
			<script src="//code.jquery.com/jquery-migrate-1.2.1.js"></script>
</body>
<script>
			function resetFile(fileName) {
			
				if ($.browser.msie) {
					// ie 일때 input[type=file] init. 
					$(fileName).replaceWith($(fileName).clone(true));
				} else {
					// other browser 일때 input[type=file] init. 
					fileName.val("");
				}
			
			}

		window.onpageshow = function(event){
				//현재 페이지를 뒤로가기 접근했을 때
			if(event.persisted  || window.performance && window.performance.navigation.type == 2){
				alert("만료된 페이지입니다.");
				history.forward();
			}			
		}
	</script>
</html>