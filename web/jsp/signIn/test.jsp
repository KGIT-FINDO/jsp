<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>서블릿 MVC 회원가입폼</title>
    <script type="text/javascript" src="./js/jquery.js"></script>
    <script src="member.js"></script>

</head>
<body>
<div id="mJoin_wrap">
    <h2 class="mJoin_title">회원가입</h2>
    <form name="m" method="post" action="member_join_ok.do" onsubmit="return join_check();"/>
        <%-- 폼태그내에서 enctype="multipart/form-data" 속성값을 지정하면 post로 전달된 이진파일(binary mode file)
        을 처리할 수 있다. 특히 파일첨부한 자료실 기능을 만들려면 반드시 이 속성값을 지정해야 한다.폼태그에서 method속성을 생략하면 기본값이
        get이고,get인 경우는 자료실 기능을 만들수 없다.자료실 기능을 만들려면 라이브러리가 필요하다. 이 라이브러리는 WEB-INF/lib
        폴더에 넣어줘야  한다. --%>
        <table id="mJoin_t">
            <tr>
                <th>회원 아이디</th>
                <td><input name="id" id="id" size="14" /> <input type="button" value="ID중복검색" onclick="join_check();" />
                    <span id="idcheck"></span> <%-- jQuery 아작스 비동기식 프로그램으로 가져온 데이터가 출력되는 부분이다. --%>
                </td>
            </tr>

            <tr>
                <th>비밀번호</th>
                <td><input type="password" name="password" id="password" size="14" />
            </tr>

            <tr>
                <th>비밀번호 확인</th>
                <td><input type="password" name="password2" id="password2" size="14" /></td>
            </tr>

            <tr>
                <th>회원이름</th>
                <td><input name="name" id="name" size="14" /></td>
            </tr>

            <tr>
                <th>생일</th>
                <td><input name="birth" id="birth" size="6" maxlength="11" /></td>
            </tr>

            <tr>
                <th>성별</th>
                <td><input name="gender" id="gender" size="3" maxlength="11" /></td>
            </tr>

            <tr>
                <th>이메일</th>
                <td>
                    <input name="email" id="email" size="30" />
                </td>
            </tr>

            <tr>
                <th>폰번호</th>
                <td><input name="phoneNum" id="phoneNum" size="4" maxlength="11" /></td>
            </tr>

        </table>
        <div id="mJoin_menu">
            <input type="submit" value="회원가입" /> <input type="reset" value="가입취소"
                                                        onclick="$('#id').focus();" />
        </div>
    </form>
</div>

</body>
</html>