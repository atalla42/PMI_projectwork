package education;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Main Class
 */
public class Education {


    
    static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     * Displays a student management menu:
     *             1- List all students
     *             2- Add a student
     *             3- Modify a student
     *             4- Delete a student
     *             0- Save and exit
     */
    public static void main(String[] args) {
        XmlTool xmlTool = new XmlTool();
        
        
        
        ArrayList<Student> students = xmlTool.readUsersFromXml("records.xml");
        
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listStudents(students);
                case 2 -> addNewStudent(students);
                case 3 -> modifyStudent(students);
                case 4 -> deleteStudent(students);
            }
            System.out.println("1 - List students");
            System.out.println("2 - Add new student");
            System.out.println("3 - Modify a student");
            System.out.println("4 - Delete a student");
            System.out.println("0 - Save and exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
        }

        xmlTool.saveUsersToXml(students, "records.xml");

    }

    /**
     * @param students lists all the students from array list
     */
    private static void listStudents(ArrayList<Student> students) {
        for(Student student:students){
            System.out.println(student.toString());
        }
    }

    /**
     * @param students adds a new student
     */
    private static void addNewStudent (ArrayList<Student> students) throws InputMismatchException {
        String name = null;
        int number = 0;
        double midterm = 0;
        double finalgrade = 0;
        try {
            System.out.print("Enter student's name: ");
            name = scanner.nextLine();
            System.out.print("Enter student's school number: ");
            number = scanner.nextInt();
            System.out.print("Enter student's midterm grade: ");
            midterm = scanner.nextDouble();
            System.out.print("Enter student's final grade: ");
            finalgrade = scanner.nextDouble();
        }catch(InputMismatchException exception){
            System.out.println("Not valid, student not added");
            scanner.nextLine();
            return;
        }
        
        students.add(new Student(name, midterm, finalgrade, number));
        System.out.println("Student added");
    }

    /**
     * @param students deletes student
     * @return true or false if the student deletion accomplished
     */
    private static boolean deleteStudent(ArrayList<Student> students) throws InputMismatchException{
        int number;
        try {
            System.out.print("Enter student's school number: ");
            number = scanner.nextInt();
        }catch(InputMismatchException exception){
            System.out.println("Not valid, student not deleted");
            scanner.nextLine();
            return false;
        }
        for(int i=0;i<students.size();i++){
            if(students.get(i).getNumber() == number){
                students.remove(i);
                System.out.println("Student deleted");
                return true;
            }
        }
        System.out.println("Student not found");
        return false;
    }

    /**
     * @param students modifies a student
     * @return true or false if modifying the student accomplished
     */
    private static boolean modifyStudent(ArrayList<Student> students) throws InputMismatchException{
        int number;
        try{
            System.out.print("Enter student's school number: ");
            number = scanner.nextInt();
        }catch(InputMismatchException exception){
            System.out.println("Not valid, student not modified");
            scanner.nextLine();
            return false;
        }
        for(int i=0;i<students.size();i++){
            if(students.get(i).getNumber() == number){
                modifyStudentHelper(students.get(i));
                return true;
            }
        }
        System.out.println("Student not found");
        return false;
        
    }

    /**
     * @param student gets information about the student to modify
     */
    private static void modifyStudentHelper(Student student) throws InputMismatchException{
        String name = null;
        int number = 0;
        double midterm = 0;
        double finalgrade = 0;
        try {
            System.out.print("Enter student's name: ");
            name = scanner.next();
            System.out.print("Enter student's school number: ");
            number = scanner.nextInt();
            System.out.print("Enter student's midterm grade: ");
            midterm = scanner.nextDouble();
            System.out.print("Enter student's final grade: ");
            finalgrade = scanner.nextDouble();
        }catch(InputMismatchException exception){
            System.out.println("Not valid, student not modified");
            scanner.nextLine();
            return;
        }
        
        student.setName(name);
        student.setNumber(number);
        student.setMidterm(midterm);
        student.setFinal(finalgrade);
    }
    
    
}
