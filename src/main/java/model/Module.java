package model;

public class Module {
    private int subjectID;
    private String subjectName;
    private byte numberOfCredits;
    private String lecturer;

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public void setGroup(byte group) {
        this.group = group;
    }

    public void setTeam(byte team) {
        this.team = team;
    }

    private int lecturerID;
    private byte group;
    private byte team;
    private byte schoolDay;
    private byte classesStart;
    private byte numberOfLessons;

    public byte getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(byte numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    private String classroom;
    private String className;

    public Module() {
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public byte getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(byte numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public byte getSchoolDay() {
        return schoolDay;
    }

    public void setSchoolDay(byte schoolDay) {
        this.schoolDay = schoolDay;
    }

    public byte getClassesStart() {
        return classesStart;
    }

    public void setClassesStart(byte classesStart) {
        this.classesStart = classesStart;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return subjectID + "\t|\t" +
                subjectName + "\t|\t" +
                numberOfCredits +"\t|\t"+
                lecturer + "\t|\t" +
                lecturerID + "\t|\t" +
                group + "\t|\t" +
                team + "\t|\t" +
                schoolDay +"\t|\t"+
                classesStart +"\t|\t"+
                numberOfLessons +"\t|\t"+
                classroom + "\t|\t" +
                className;
    }
}
