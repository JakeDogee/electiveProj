package model;

public class Course {
    private int id;

    private String name;

    private int duration;

    private int studNumb;

    public Course(int id, String name, int duration, int studNumb, int id_teacher, String theme) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.studNumb = studNumb;
        this.id_teacher = id_teacher;
        this.theme = theme;
    }

    private int id_teacher;

    private String theme;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStudNumb(int studNumb) {
        this.studNumb = studNumb;
    }

    public void setTeacherId(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getStudNumb() {
        return studNumb;
    }

    public int getTeacherId() {
        return id_teacher;
    }

    public String getTheme() {
        return theme;
    }

}
