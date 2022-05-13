
package education;


/**
 * Student class has a name, a number, a midterm grade and a final grade
 */
public class Student {
    private String name;
    private double midterm_grade;
    private double final_grade;
    private int number;


    /**
     * @param name Student Name
     * @param number Student Number
     * @param midterm_grade Student Midterm Grade
     * @param final_grade Student Final Grade
     */
    public Student(String name, double midterm_grade, double final_grade, int number){
        this.name = name;
        this.final_grade = final_grade;
        this.midterm_grade = midterm_grade;
        this.number = number;
    }


    /**
     * @return Student's name
     */
    public String getName(){return this.name;}

    /**
     * @return Student's midterm grade
     */
    public double getMidterm(){return this.midterm_grade;}

    /**
     * @return Student's final grade
     */
    public double getFinal(){return this.final_grade;}

    /**
     * @return Student's number
     */
    public int getNumber(){return this.number;}

    /**
     * @param name Set name
     */
    public void setName(String name){this.name = name;}

    /**
     * @param midterm_grade Set midterm grade
     */
    public void setMidterm(double midterm_grade){this.midterm_grade = midterm_grade;}

    /**
     * @param final_grade Set final grade
     */
    public void setFinal(double final_grade){this.final_grade = final_grade;}

    /**
     * @param number Set student number
     */
    public void setNumber(int number){this.number = number;}

    /**
     * @return Student object's details as a String.
     */
    @Override
    public String toString(){
        return "Name: "+name+ ", Number: "+number+", Midterm Grade: "+ midterm_grade+", Final Grade: "+final_grade;
    }
}
