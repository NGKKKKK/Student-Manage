package Java2.Luyen_oop_studentmanage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final int MAX_STUDENT = 30;

    private static boolean findByName(ArrayList<Student> ClassA, String NAME){
        int sz = ClassA.size();
        boolean isExist = false;
        for (int i = 0 ; i < sz ; ++i){
            String name = ClassA.get(i).getName();
            if (name.contains(NAME)) {
                if (!isExist) isExist = true;
                ClassA.get(i).showInfo();
                System.out.println();
            }
        }
        if (!isExist){
            System.out.println("There's no one with that name in this class. Please try again.");
            System.out.println();
            return false;
        }
        return true;
    }

    private static boolean findByID(ArrayList<Student> ClassA, int ID){
        int sz = ClassA.size();
        if (ID <= 0){
            System.out.println("Invalid ID. Please try again.");
            System.out.println();
            return false;
        }
        if (ID > sz){
            System.out.println("The ID exceeds the number of students. Please try again.");
            System.out.println();
            return false;
        }
        ClassA.get(ID-1).showInfo();
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Student> ClassA =  new ArrayList<>();
        int ID = 1;
        try(BufferedReader read = new BufferedReader(new FileReader("Java2/Luyen_oop_studentmanage/INPUT.txt"))){
            String line;
            while(ID <= MAX_STUDENT){
                line = read.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\|");
                String NAME = tmp[0].trim();
                LocalDate BIRTHDAY = LocalDate.parse(tmp[1].trim(), Student.BD_Format);
                boolean gd = (tmp[2].trim().equals("Female"));
                double POINT = Double.parseDouble(tmp[3]);
                ClassA.add(new Student(ID++, gd, NAME, BIRTHDAY, POINT));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Number of students in this class: " + ClassA.size());
            System.out.print("Enter ID or NAME: ");String n = sc.nextLine();
            System.out.println();
            if (!Character.isLetter(n.charAt(0))){
                int n_new = Integer.parseInt(n);
                if (findByID(ClassA, n_new)) break;
            }
            else{
                if (findByName(ClassA, n)) break;
            }
        }
        sc.close();
    }
}
