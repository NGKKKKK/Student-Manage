package Java2.Luyen_oop_studentmanage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


public class Student {

    public static final DateTimeFormatter BD_Format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String FEMALE = "Female";
    private static final String MALE = "Male";
    private static final double MAX_POINT = 10.0;

    private int ID;
    private String GENDER;
    private String NAME;
    private LocalDate BIRTHDAY;
    private double POINT;

    Student(){
        ID = 0; GENDER = "#"; NAME = "#"; BIRTHDAY = LocalDate.of(0,0,0); POINT = 0;
    }

    Student(int ID, boolean isFemale, String NAME, LocalDate BIRTHDAY, double POINT){
        this.ID = ID;
        if (isFemale) GENDER = FEMALE; else GENDER = MALE;
        this.NAME = NAME;
        this.BIRTHDAY = BIRTHDAY;
        this.POINT = POINT;
    }

    public void showInfo(){
        System.out.println("========== INFO ==========");
        System.out.println("ID: " + ID);
        System.out.println("NAME: " + NAME);
        System.out.println("GENDER: " + GENDER);
        String bd = BD_Format.format(BIRTHDAY);
        System.out.println("BIRTHDAY: " + bd);
        System.out.println("POINT: " + POINT);
        System.out.println("==========================");
        //System.out.println();
    }

    public void setPoint(double POINT){
        if (POINT >= 0 && POINT < MAX_POINT) this.POINT = POINT;
        else if (POINT >= MAX_POINT) this.POINT = MAX_POINT;
    }

    public double getPoint(){
        return POINT;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return NAME;
    }

    public void changeName(String NAME){
        boolean isValid = true;
        for (int i = 0; i < NAME.length(); ++i){
            char d = NAME.charAt(i);
            if (!(Character.isLetter(d) || d == ' ')){
                isValid = false;
                break;
            }
        }
        if (isValid) this.NAME = NAME;
    }

    public void changeGender(boolean isFemale){
        if (isFemale) GENDER = FEMALE;
        else GENDER = MALE;
    }
}

class StudentSort implements Comparator<Student>{

    public int compare(Student a, Student b){
        if (a.getPoint() == b.getPoint()) return (a.getID() - b.getID());
        if (a.getPoint() > b.getPoint()) return -1;
        return 1;
    }

}
