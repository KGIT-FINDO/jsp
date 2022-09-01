package signIn.controller;

import Action.Action;
import Action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInController implements Action{
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] phone = {"010", "011", "017"};
        String[] bornMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] bornDate = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] gender = {"남","여", "선택안함"};

        request.setAttribute("phone", phone);//phone속성 키 이름에 object타입으로 phone 배열값을 저장. 관례적으로 속성키 이름과 값객체는 이름을 같게함.
        request.setAttribute("bornMonth", bornMonth);
        request.setAttribute("bornDate", bornDate);
        request.setAttribute("gender", gender);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);//기존 매핑주소 값을 유지하고, 속성키 이름과 값도 유지한다.
        forward.setPath("./view/member/member_join.jsp");
        return forward;

    }
}
