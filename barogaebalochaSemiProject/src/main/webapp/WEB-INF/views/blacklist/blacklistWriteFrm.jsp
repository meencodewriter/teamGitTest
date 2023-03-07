<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.tbl {
    width: 80%;
    border-spacing: 0px;
    border-collapse: collapse;
    margin: 0 auto;
    }
    .tbl th,.tbl td {
        padding: 10px;
        text-align: center;
    }   
    .blacklist-input-wrap{
        padding: 20px;
    }
    .input-form{
        padding: 5px;
    }
    #blacklistWrite th, #blacklistWrite td{
        border: 1px solid #ccc;
    }
    #mercenaryWrite th{
        color: #181818;
    }
    #DDDD{
    	text-align: left;
    }
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="page-content">
			<div class="page-title">
				<h2>신고</h2>
			</div>
			<%if( m != null) {%>
			<div class="blacklist-input-wrap">
			<form action="/blacklistInsert.do" method="post" enctype="multipart/form-data">
				<table class="tbl" id="blacklistWrite">
					<tr>
						<th colspan="1">제목</th>
						<td colspan="3"><input type="text" class="input-form" name="blackTitle" required>
					</tr>
					<tr>
						<th colspan="1">작성자</th>
						<td colspan="3">
						<%=m.getMemberId() %>
						<input type="hidden" name="writeMemberNo" value="<%=m.getMemberNo() %>">						
						</td>
					</tr>
					<tr>
						<th>신고 할 아이디</th>
						<td><input type="text" class="input-form" name="blackMember"></td>
						<th>첨부파일</th>
						<td><input type="file" name="upfile" class="input-form"></td>
					</tr>
					<tr>
						<td colspan="4" id="DDDD"><textarea style="min-height: 500px;" class="input-form" name="blackContent" id="blackContent"></textarea></td>
					</tr>
					<tr>
						<td colspan="4"><input type="submit" class="btn1 bs4" value="작성완료"></td> 
					</tr>
				</table>
			</form>
			</div>
			<%}else {%>
				<script>
					alert("[ERROR] 옳지 않은 접근입니다.");
				</script>
			<%} %>
			
		</div>
		<script type="text/javascript">
		$("#blackContent").summernote({
			height : 400,
			lang : "ko-KR",
			callbacks : {
				onImageUpload : function(files) {
					uploadImage(files[0],this)
				}
			}
		});
		</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>