<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Findo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/stylesheet.css" />
<script src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/javascript.js"></script>
<script src="https://kit.fontawesome.com/08cf8c1353.js" crossorigin="anonymous"></script>
</head>
<body>
<%-- 전체적인 움직임 줄려면 wrapper class 이용하기; --%>
	<div class="wrapper_index">

		<%-- header --%>
		<%-- header 전체를 움직이고 싶으면 header class 이용하기 --%>
		<div class="header">
		
			<div class="header_top">
				<div class="header_top_togglebutton">
					<button id="header_top_togglebutton"><i class="fa-solid fa-bars"></i></button>
				</div>
				<div class="header_top_logo">
					<img src="../images/logo_black.png" alt="logo_black" />
				</div>
				<div class="header_top_icons">
					<a href="./index_bell.jsp"><button id="header_top_bell"><i class="fa-solid fa-bell"></i></button></a>
					<button id="header_top_switch"><i class="fa-solid fa-toggle-on"></i></button>
					<a href="./index_search.jsp"><button id="header_top_search"><i class="fa-solid fa-magnifying-glass"></i></button></a>
				</div>
			</div>
			<%-- block --%>
			
			<div class="header_middle">
				<a href="./index.jsp"><input type="button" value="홈" /></a>
				<a href="#"><input type="button" value="포트폴리오" /></a>
				<a href="#"><input type="button" value="커뮤니티" /></a>
				<a href="#"><input type="button" value="뉴스" /></a>
			</div>
			
			<div class="header_bottom">
			
				<div class="header_bottom_write">
					<p>로그인을 하려면 아래의 버튼을 누르시오</p>
				</div>
				<div class="header_bottom_button">
					<a href="./signin.jsp"><input type="button" value="로그인" /></a>
				</div>
			</div> <%-- heaer_bottom class 닫는 마지막 /div --%>
			
		</div> <%-- header class 닫는 마지막 /div --%>
		
		
		<%-- ******	plz cut here	header part split to /include/heaer.jsp 	******	--%>
		
		
		<%-- main --%>
		<%-- main 전체를 움직이고 싶으면 main class 이용하기 --%>
		<div class="main">
		
			<%-- main_top 움직이고 싶으면 main_top 이용하기 --%>
			<div class="main_top">top</div>
			<div class="main_top_top">1</div>
			<div class="main_top_middle">2</div>
			<div class="main_top_bottom">3</div>
			
			<%-- main_middle 움지이고 싶으면 main_middle 이용하기 --%>
			<div class="main_middle">middle</div>
			<div class="main_middle_top">4</div>
			<div class="main_middle_middle">5</div>
			<div class="main_middle_bottom">6</div>
			
			<%-- main_bottomd 움직이고 싶으면 main_bottom 이용하기 --%>
			<div class="main_bottom">bottom</div>
			<div class="main_bottom_top">7</div>
			<div class="main_bottom_middle">8</div>	
			<div class="main_bottom_bottom">9</div>	
			
		</div>
		
		<%-- ******	please cut here!	footer part split to /include/footer.jsp 	******	--%>
		
		<%-- footer --%>
		<%-- footer 전체를 움직이고 싶으면 footer class 이용하기 --%>
		<div class="footer">
		
			<div class="footer_top">
			
				<div class="footer_top_list">
					<li><a href="#">Home</a></li>
					<li><a href="#">Company</a></li>
					<li><a href="#">How To Findo</a></li>
				</div>
				
				<div class="footer_top_list">
					<li><a href="#">Investment</a></li>
					<li><a href="#">Products</a></li>
					<li><a href="#">Career</a></li>
				</div>
				
				<div class="footer_top_list">
					<li><a href="#">FAQ</a></li>
					<li><a href="#">Management</a></li>
					<li><a href="#">Asset</a></li>				
				</div>
				
			</div>
			
			
			<div class="footer_middle">증권사사진</div>
			
			<div class="footer_bottom">
			
				<div class="footer_bottom_top_list">
					<li><a href="#">핀도이용약관</a></li>
					<li><a href="#">전자금융거래이용약관</a></li>
				</div>
				
				<div class="footer_bottom_top_list">
					<li><a href="#">개인정보처리방침</a></li>
					<li><a href="#">경영공지</a></li>
				</div>
				
			</div>
			
			<div class="footer_bottom_middle">
				<p>주식회사 핀도</p>
				<p>사업자등록번호 502-10923-12</p>
				<p>대표 정범수</p>
				<p>서울특별시 강남구 강남대로 84길(역삼동)</p>
			</div>
			
			<div class="footer_bottom_bottom">
				<div class="footer_bottom_bottom_top">
					<span>Copyright © FINDO Corp. All Rights Reserved.</span>
				</div>
				<div class="footer_bottom_bottom_middle">
					<button><a href="#"><i class="fa-brands fa-square-facebook"></i></a></button>
					<button><a href="#"><i class="fa-brands fa-square-instagram"></i></a></button>
					<button><a href="#"><i class="fa-brands fa-square-twitter"></i></a></button>
				</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>