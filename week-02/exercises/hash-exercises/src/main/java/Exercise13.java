import learn.Student;
import learn.Vehicle;

import java.util.HashMap;

public class Exercise13 {

    public static void main(String[] args) {
        HashMap<Integer, Student> students = new HashMap<Integer, Student>();

//        Add at least three students to the map using their studentId as the key.
        Student student1 = new Student(1, "a", "b");
        students.put(1, student1);

        Student student2 = new Student(2, "c", "d");
        students.put(2, student2);

        Student student3 = new Student(3, "e", "f");
        students.put(3, student3);

        System.out.printf("%s %s %s%n", students.get(1).getStudentId(), students.get(1).getFirstName(), students.get(1).getLastName());

//        Retrieve one student and display them.
//        Delete one student.
        students.remove(2);


//                Display all students.
        for (Student l : students.values()) {
            System.out.printf("%s %s %s%n", l.getStudentId(), l.getFirstName(), l.getLastName());
        }
    }

    }

