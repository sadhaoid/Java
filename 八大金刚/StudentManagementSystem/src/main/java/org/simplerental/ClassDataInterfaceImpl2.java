package org.simplerental;

public class ClassDataInterfaceImpl2 implements ClassDataInterface{
    private Student[] students;
    public ClassDataInterfaceImpl2(Student[] students) {
        this.students = students;
    }

    @Override
    public void printAllStudentInfos() {
        int maletotal = 0;
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            System.out.println(student);
            if(student.getSex() == "male"){
                maletotal += 1;
            }
        }
        System.out.println("male: " + maletotal);
        System.out.println("female: " + (students.length-maletotal));

    }

    @Override
    public void printAverageScore() {

        Student student0 = students[0];
        double maxScore = student0.getGrade();
        double minScore = student0.getGrade();
        double sum = student0.getGrade();
        for (int i = 1; i < students.length; i++) {
            Student student = students[i];
            sum += student.getGrade();
            if(student.getGrade() > maxScore){
                maxScore = student.getGrade();
            }
            if (student.getGrade() < minScore){
                minScore = student.getGrade();
            }
        }

        System.out.println("Average: " + (sum - minScore - maxScore )/ (students.length-2));

    }


}
