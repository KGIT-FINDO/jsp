package member;

public class MemberVo implements SignIn {
    private String id;
    private String password;
    private String name;
    private String birth;
    private String gender;
    private String email;
    private String phoneNum;

    public MemberVo(){

    }

    public MemberVo(String id, String password, String name, String birth, String gender, String email, String phoneNum){
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phoneNum = phoneNum;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public void save() {
        //나중에 회원정보를 DB에 넘길때 사용할 메서드임.
    }

    @Override
    public boolean checkId(){
        //나중에 아이디 중복여부 체크할거임.
        return false;
    }
}
