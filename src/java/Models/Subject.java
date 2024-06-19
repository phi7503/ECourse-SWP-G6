package Models;

public class Subject {
    private int SubjectID;
    private String SubjectName;

    public Subject() {
    }

    public Subject(int SubjectID, String SubjectName) {
        this.SubjectID = SubjectID;
        this.SubjectName = SubjectName;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }
}
