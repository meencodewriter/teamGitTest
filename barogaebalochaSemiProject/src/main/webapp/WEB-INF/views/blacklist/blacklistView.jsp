<%@page import="semi.team.baro.blacklist.model.vo.Blacklist"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Blacklist bla = (Blacklist)request.getAttribute("bla");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .tbl {
    	width: 90%;
    	margin: 0 auto;
	    border-spacing: 0px;
    }
    .tbl th,.tbl td {
        padding: 10px;
        text-align: center;
    } 
    #blacklistView th, #blacklistView td{
        border: 1px solid #ccc;
        font-size: 13px;
    }
    #blacklistView th{
        background-color: #ccc;
        color: #fcfcfc;
    }
    .blacklist-content th{
    	min-hight: 300px;
    }
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
		<div class="page-content">
			<div class="page-title">
				<h2>신고상세</h2>
			</div>
			<table class="tbl" id="blacklistView">
				<tr>
					<th colspan="4">신고내용</th>
				</tr>
				<tr>
					<th>작성자</th>
					<td><%=bla.getMemberId() %></td>
					<th>작성일</th>
					<td style="color:#ccc;"><%=bla.getRegDate() %></td>
				</tr>
				<tr>
					<th>신고대상</th>
					<td><%=bla.getBlackMember() %>
					<th>처리상태</th>
					<td><%=bla.getBlackStatus() %></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><%=bla.getBlackTitle() %></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3"><%=bla.getBlackFilepath() %></td>
				</tr>
				<tr>
				<td colspan="4">
					<div class="mercenary-content">
						<%=bla.getBlackContentBr() %>
					</div>
				</td>
			</tr>
				<tr>
					<td colspan="4"><a href="/" class="btn1 bc2">목록으로</a></td>
				</tr>
			</table>
		</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>



