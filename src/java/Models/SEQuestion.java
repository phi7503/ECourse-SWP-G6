package Models;

public class SEQuestion {
    private int securityQuestionID;
    private String question;

    public SEQuestion() {
    }

    public SEQuestion(int securityQuestionID, String question) {
        this.securityQuestionID = securityQuestionID;
        this.question = question;
    }

    

    public int getSecurityQuestionID() {
        return securityQuestionID;
    }

    public void setSecurityQuestionID(int securityQuestionID) {
        this.securityQuestionID = securityQuestionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "SEQuestion{" + "securityQuestionID=" + securityQuestionID + ", question=" + question + '}';
    }
    
    
}
