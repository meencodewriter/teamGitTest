<%@ page import="semi.team.baro.member.model.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Member m = (Member)session.getAttribute("m");
    %>
<!DOCTYPE html>
<html lang="ko"></html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REF</title>
    <!-- bootstrap  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- 구글 아이콘 -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <!-- 기본css -->
    <link rel="stylesheet" href="/css/default.css">
    <link rel="stylesheet" href="/css/design.css">
    <link rel="stylesheet" href="/css/locationInfo.css">
    <link rel="stylesheet" href="/css/locationList.css">
    <link rel="stylesheet" href="/css/matchInfo.css">
    <link rel="stylesheet" href="/css/matchingPage.css">
    <link rel="stylesheet" href="/css/matchingWriteFrm.css">
    <link rel="stylesheet" href="/css/memberInfo.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/notice.css">
    <script src="js/jquery-3.6.0.js"></script>
    <style type="text/css">
    .update-remove-btnbox { display: flex; justify-content: end; }
    </style>
</head>
<body>
    <header>
        <div class="header-wrap">
            <div class="site-logo">
                <a href="/"><img src="img/FUTSALDATE.png"></a>
            </div>
            <div class="header-content">
                <div class="header-link">
                <%if(m == null) {%>
                    <a href="/joinFrm.do">JOIN</a>
                    <a href="/loginFrm.do">LOGIN</a>
                <%} else {%>
                    <a href="/logout.do">LOGOUT</a>
                	<a href="/mypage.do?memberId=<%=m.getMemberId() %>"><%=m.getMemberName() %>의 라커룸</a>
                <%} %>
                </div>
                <%if( m!=null && m.getMemberLevel()==1){%>
                <div class="nav">
	                    <ul class="navi">
	                        <li><a href="/adminPage.do?reqPage=1">회원관리</a></li>
	                        <li><a href="/locationInsert.do">구장등록</a></li>
	                        <li><a href="adminBlacklistList.do?reqPage=1">신고관리</a></li>
	                        <li>
	                            <a href="#" name="search">메뉴</a>
	                            <ul class="menu">
	                                <li><a href="/matchingList.do?requestPage=1" class="searchBox">MATCHING</a></li>
	                                <li><a href="/mercenaryList.do?reqPage=1" class="searchBox">용병모집</a></li>
	                                <li><a href="/locationList.do?requestPage=1" class="searchBox">구장</a></li>
	                                <li><a href="/noticeList.do?noticePage=1" class="searchBox">공지사항</a></li>
	                                <li><a href="/freeBoardList.do?boardPage=1" class="searchBox">게시판</a></li>
	                            </ul>
	                        </li>
	                    </ul>
	                </div>
                <%}else {%>                
                <div class="nav">
                    <ul class="navi">
                        <li><a href="/matchingList.do?requestPage=1">MATCHING</a></li>
                        <li><a href="/mercenaryList.do?reqPage=1">용병모집</a></li>
                        <li><a href="/locationList.do?requestPage=1">구장</a></li>
                        <li><a href="/noticeList.do?noticePage=1">공지사항</a></li>
                        <li><a href="/freeBoardList.do?boardPage=1">게시판</a></li>
                    </ul>
                </div>
                <%} %>
            </div>
        </div>
         <script>
			$(".menu").prev().append("<span class='more'></span>");
			$(".more").on("click",function(event){
			    $(this).parent().next().slideToggle();
			    $(this).toggleClass("active");
			    event.stopPropagation();
			});
			$(".more").parent().on("click",function(){
			    $(this).children().last().click();
			});
		</script>
    </header>