<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--
	Spectral by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
	<head>
		<title>Board</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="/resources/assets/css/noscript.css" /></noscript>
	</head>
	
	<style>
			
			#main > header {
				padding: 3em 0 2em 0;
			}
			
			.small-width{display:none;}
			
			select{
				display:inline;
				width:15%
			}
			
			#keyword{
				display:inline;
				width:60%
			}
			
			@media (max-width: 918px){
				.writer {display: none;}
				.regDate	 {display: none;}
				.updateDate {display: none;}
				.small-width{display:block;}
				.big-width{display:none}
				select{width:100%;}
				#keyword{width:100%;}
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
							<h2 style="text-align:center">게시판 목록</h2>
				</header>
						<section class="wrapper style5">
							<div class="inner">
								<h3><a href="/board/register${pageMaker.cri.getListLink()}" class="button small">글 등록</a></h3>
									<div class="table-wrapper">
										<table>
											<thead>
													<tr class="tHead">
														<th class="bno">번호</th>
														<th class="title">제목</th>
														<th class="writer">작성자</th>
														<th class="regDate">작성일</th>
														<th class="updateDate">수정일</th>
													</tr>
												</thead>
											<tbody>
												<c:forEach var="board" items="${list}">
														<tr class="tBody">
															<td class="bno">${board.bno}</td>
															<td class="title"><a class="goGet" href="${board.bno}">${board.title}</a></td>
															<td class="writer">${board.writer}</td>
															<td class="regDate">${board.regDate}</td>
															<td class="updateDate">${board.updateDate}</td>
														</tr>
													</c:forEach>
											</tbody>
											<tfoot>
											</tfoot>
										</table>
									</div>
									
									<form action="/board/list" method="get" id="searchForm">
											<div class="row gtr-uniform">
												<div class="col-12">
													<div style="text-align:center">
														<select name="type">
															<option value="" ${pageMaker.cri.type == null ? 'selected' : ' '}>검색 기준</option>
															<option value="T" ${pageMaker.cri.type == 'T' ? 'selected' : ' '}>제목</option>
															<option value="C" ${pageMaker.cri.type == 'C' ? 'selected' : ' '}>내용</option>
															<option value="W" ${pageMaker.cri.type == 'W' ? 'selected' : ' '}>작성자</option>
															<option value="TC" ${pageMaker.cri.type == 'TC' ? 'selected' : ' '}>제목 또는 내용</option>
															<option value="TW" ${pageMaker.cri.type == 'TW' ? 'selected' : ' '}>제목 또는 작성자</option>
															<option value="TCW" ${pageMaker.cri.type == 'TCW' ? 'selected' : ' '}>전체 검색</option>
														</select>
														<input id="keyword" name="keyword" type="text" value="${pageMaker.cri.keyword}">
														<input name="pageNum" type="hidden" value="${pageMaker.cri.pageNum}">
														<input name="amount" type="hidden" value="${pageMaker.cri.amount}">
														<a style="vertical-align: middle" href="#" class="search button primary icon solid fa-search">검색</a>
													</div>
												</div>
											</div>
										</form>
							
							<div class="big-width" style="text-align:center">
											<c:if test="${pageMaker.prev}">
												<a class="changePage" href="${pageMaker.startPage - 1}"><code>&lt;</code></a>
											</c:if>
											<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
												<c:choose>
													<c:when test="${num eq pageMaker.cri.pageNum}">
														<code>${num}</code>
													</c:when>
													<c:otherwise>
														<a class="changePage" href="${num}"><code>${num}</code></a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											<c:if test="${pageMaker.next}">
												<a class="changePage" href="${pageMaker.endPage + 1}"><code>&gt;</code></a>
											</c:if>
										</div>
										
										<div class="small-width" style="text-align:center">
											<c:if test="${pageMaker.cri.pageNum > 1}">
												<a class="changePage" href="${pageMaker.cri.pageNum - 1}"><code>&lt;</code></a>
											</c:if>
											<code>${pageMaker.cri.pageNum}</code>
											<c:if test="${pageMaker.cri.pageNum < pageMaker.realEnd}">
												<a class="changePage" href="${pageMaker.cri.pageNum + 1}"><code>&gt;</code></a>
											</c:if>
										</div>
								
							</div>
						</section>
						</article>
			</div>
		<form id="actionForm" action="/board/list" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="type" value="${pageMaker.cri.type}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
			</form>	
			
		<!-- Scripts -->
			<script src="/resources/assets/js/jquery.min.js"></script>
			<script src="/resources/assets/js/jquery.scrollex.min.js"></script>
			<script src="/resources/assets/js/jquery.scrolly.min.js"></script>
			<script src="/resources/assets/js/browser.min.js"></script>
			<script src="/resources/assets/js/breakpoints.min.js"></script>
			<script src="/resources/assets/js/util.js"></script>
			<script src="/resources/assets/js/main.js"></script>
			
			<script>
			var actionForm = $("#actionForm");
			var searchForm = $("#searchForm");
				
			function searchCheck(){
				//searchForm.find("option:selected").val()
				//사용자가 선택한 select option의 값을 가지고 온다.
				//만약 조건식으로 사용하게 되면, 값이 없을 때 false 값이 있을 때에는 true이다.
				if(!searchForm.find("option:selected").val()){
					alert("검색 종류를 선택하세요");
					return false;
				}
				
				if(!searchForm.find("input[name='keyword']").val()){
					alert("키워드를 입력하세요");
					return false;
				}
				
				searchForm.find("input[name='pageNum']").val("1");
				searchForm.submit();
			}
			
			$("#keyword").keydown(function(key){
				if(key.keyCode == 13){
					searchCheck();
				}
			})
			
			
			$(".changePage").on("click", function(e){
				e.preventDefault();
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
				actionForm.submit();
			});
			
			$(".goGet").on("click", function(e){
				e.preventDefault();
				actionForm.append("<input type='hidden' name='bno' value=' " + $(this).attr("href") +" '>");
				actionForm.attr("action", "/board/get");
				actionForm.submit();
			});
			
			$("#searchForm a").on("click", function(e){
				e.preventDefault();
				//console.log("테스트 : "+ !searchForm.find("option:selected").val());
				searchCheck();
				
				
			});
			
				/* alert("${result}"); */
				result = "${result}" //javascript에서 el문 쓸때는 ""안에 쓴다
				$(document).ready(function(){
					if(result == '' || history.state){ //url에 기록이 없으면 if문에 들어온다
						return;
					}
					
					if(parseInt(result) > 0){
						alert("게시글 " + parseInt(result) + "번이 등록되었습니다.");
					}
					
					history.replaceState({}, null, null); //뒤로가기 했을 때 이동하는 거 막기
				})
				
			</script>

	</body>
</html>