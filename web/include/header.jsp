<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Findo</title>
	<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
			crossorigin="anonymous">
	<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous"></script>
	<link rel="stylesheet" href="front2/css/stylesheet.css" />
	<script src="front2/js/jquery.js"></script>
	<script type="text/javascript" src="front2/js/javascript.js"></script>
	<script src="https://kit.fontawesome.com/08cf8c1353.js"
			crossorigin="anonymous"></script>
</head>
<body>
<%-- 전체적인 움직임 줄려면 wrapper class 이용하기; --%>
<div class="wrapper_index">

	<%-- header --%>
	<%-- header 전체를 움직이고 싶으면 header class 이용하기 --%>
	<div class="header">

		<div class="header_top">
			<div class="header_top_togglebutton">
				<button id="header_top_togglebutton">
					<i class="fa-solid fa-bars fa-2x"></i>
				</button>
			</div>
			<div class="header_top_logo"></div>
			<div class="header_top_icons">
				<a href="/front2/html/index_bell.jsp"><button id="header_top_bell">
					<i class="fa-solid fa-bell fa-2x"></i>
				</button></a> <a href="/front2/html/index_search.jsp"><button id="header_top_search">
				<i class="fa-solid fa-magnifying-glass fa-2x"></i>
			</button></a>
			</div>
		</div>
		<%-- block --%>

		<div class="header_middle">
			<a href="index.do"><input type="button" value="홈" /></a> <a
				href="portfolio.do"><input type="button" value="포트폴리오" /></a>
			<a href="board_list.do"><input type="button"
										   value="커뮤니티" /></a> <a href="board_list.do"><input
				type="button" value="뉴스" /></a>
		</div>

		<div class="header_bottom">

			
		</div>
		<%-- heaer_bottom class 닫는 마지막 /div --%>

	</div>