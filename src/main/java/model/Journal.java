package model;

public class Journal {
    int id;

    int grade;

    int idCourse;

    int idUser;

    String status;

    public Journal(int id, int grade, int idCourse, int idUser, String status) {
        this.id = id;
        this.grade = grade;
        this.idCourse = idCourse;
        this.idUser = idUser;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
