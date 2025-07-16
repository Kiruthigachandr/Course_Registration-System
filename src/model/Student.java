package model;

public class Student {
    private String rollNo;
    private String name;
    private String branch;
    private int semester;
    private String campus;
    private String regulations;

    public Student(String rollNo, String name, String branch, int semester, String campus, String regulations) {
        this.rollNo = rollNo;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
        this.campus = campus;
        this.regulations = regulations;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public int getSemester() {
        return semester;
    }

    public String getCampus() {
        return campus;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    @Override
    public String toString() {
        return name + " (" + rollNo + ")";
    }
}
