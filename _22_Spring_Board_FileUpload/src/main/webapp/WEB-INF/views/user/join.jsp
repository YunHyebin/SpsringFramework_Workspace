<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.form-wrapper {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	#joinForm {
		width: 250px;
		text-align: center;
	}
	
	#joinForm .label-wrapper {
		margin-top: 20px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	#joinForm input {
		width: 100%;
	}
	
	#joinForm div {
		display: flex;
		align-items: center;
	}
	
	#btnIdCheck {
		width: 50px;
	}
</style>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<div class="form-wrapper">
		<form id="joinForm" action="/user/join.do" method="post">
			<input type="hidden" id="joinMsg" value="${joinMsg }">
			<h3>회원가입</h3>
			<div class="label-wrapper">
				<label for="userId">아이디</label>
			</div>
			<div>
				<input type="text" id="userId" name="userId" required style="width: auto;">
				<button type="button" id="btnIdCheck" style="width: 70px">중복체크</button>
			</div>
			<div class="label-wrapper">
				<label for="userPw">비밀번호</label>
			</div>
			<input type="password" id="userPw" name="userPw" required>
			<p id="pwValidation" style="color: red; font-size: 0.8rem;">
				비밀번호는 영문자, 숫자, 특수문자 조합의 9자리 이상으로 설정해주세요.
			</p>
			<div class="label-wrapper">
				<label for="userPwCheck">비밀번호 확인</label>
			</div>
			<input type="password" id="userPwCheck" name="userPwCheck" required>
			<p id="pwCheckResult" style="font-size: 0.8rem;"></p>
			<div class="label-wrapper">
				<label for="userNm">이름</label>
			</div>
			<input type="text" id="userName" name="userName" required>
			<div class="label-wrapper">
				<label for="userMail">이메일</label>
			</div>
			<input type="email" id="userEmail" name="userEmail" required>
			<div class="label-wrapper">
				<label for="userTel">전화번호</label>
			</div>
			<input type="text" id="userTel" name="userTel" placeholder="숫자만 입력하세요.">
			<div style="display: block; margin: 20px auto;">
				<button type="submit">회원가입</button>
			</div>
		</form>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
	<script>
		$(function() {
			$("#pwValidation").hide();
			$("#pwCheckResult").hide();
			
			//joinForm 서브밋될 때 유효성검사해줄 변수들
			//id중복체크 여부
			let checkId = false;
			//비밀번호 유효성 검사 여부
			let pwValidation = false;
			//비밀번호 일치 여부
			let checkPw = false;
			
			//id 중복체크
			$("#btnIdCheck").on("click", () => {
				if($("#userId").val() === "") {
					alert("아이디를 입력하세요.");
					$("#userId").focus();
					return;
				}
				
				//ajax로 db조회
				$.ajax({
					url: "/user/idCheck.do",
					type: "post",
					data: $("#joinForm").serialize(),
					success: (obj) => {
						console.log(obj);
						//JSON.parse(): json String을 json 데이터로 변환
						console.log(JSON.parse(obj));
						const jsonObj = JSON.parse(obj);
						
						if(jsonObj.msg === "duplicatedId"){
							checkId = false;
							alert("중복된 아이디입니다.")
							$("#userId").focus();
						}else{
							if(confirm("사용가능한 아이디입니다. " + $("#userId").val()
									+ "를(을) 사용하시겠습니까?")){
								checkId = true;
								$("#btnIdCheck").attr("disabled", true);
							}
						}
						
						/* //obj가 duplicatedId나 idOk로 나온다.
						if(obj === "duplicatedid"){
							checkId = false;
							alert("중복된 아이디입니다.")
							$("#userId").focus();
						}else{
							if(confirm("사용가능한 아이디입니다." + $("#userId").val()
									+ "를(을) 사용하시겠습니까?")){
								checkId = true;
								$("#btnIdCheck").attr("disabled", true);
							}
						} */
					},
					error: (error) => {
						console.log(error);
					}
				});
			});
			
			//중복체크 이후 아이디 변경했을 때 다시 중복체크 되도록
			$("#userId").on("change", () => {
				checkid = false;
				$("#btnIdCheck").attr("disabled", false);
			});
			
			//비밀번호 유효성검사 정규식
			const validatePassword = (character) => {
				return /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{9,}$/.test(character);
			}
			
			//비밀번호 변경시 유효성 검사
			$("#userPw").on("change", () => {
				if(validatePassword($("#userPw").val())) {
					pwValidation = true;
					$("#pwValidation").hide();
				} else {
					pwValidation = false;
					$("#pwValidation").show();
					$("#userPw").focus();
				}
				
				//비밀번호 확인까지 완료후 다시 비밀번호 재설정 할 때
				if($("#userPw").val() === $("#userPwCheck").val()) {
					checkPw = true;
					$("#pwCheckResult").css("color", "green");
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
				} else {
					checkPw = false;
					$("#pwCheckResult").css("color", "red");
					$("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
				}
			});
			
			//비밀번호 확인과 비밀번호가 일치하는 지 여부 검사
			$("#userPwCheck").on("change", () => {
				$("#pwCheckResult").show();
				
				if($("#userPw").val() === $("#userPwCheck").val()) {
					checkPw = true;
					$("#pwCheckResult").css("color", "green");
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
				} else {
					checkPw = false;
					$("#pwCheckResult").css("color", "red");
					$("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
				}
			});
			
			//joinForm 서브밋될 때 유효성 검사
			$("#joinForm").on("submit", (e) => {
				//id 중복체크가 되지 않았거나 중복된 아이디를 사용했을 때
				/* if(!checkId) {
					e.preventDefault();
					alert("아이디 중복체크를 진행하세요.");
					$("#userId").focus();
					return;
				} */
				
				//비밀번호 유효성 검사가 맞지 않을 때
				if(!pwValidation) {
					e.preventDefault();
					alert("비밀번호는 영문자, 숫자, 특수문자 조합의 9자리 이상으로 설정하세요.");
					$("#userPw").focus();
					return;
				}
				
				//비밀번호가 일치하지 않을 때
				if(!checkPw) {
					e.preventDefault();
					alert("비밀번호가 일치하지 않습니다.");
					$("#userPwCheck").focus();
					return;
				}
			});
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>