package org.simplerental;

import javax.xml.namespace.QName;

public class ClassDataInterfaceImpl1 implements ClassDataInterface{
    private Student[] students;
    public ClassDataInterfaceImpl1(Student[] students){
        this.students = students;
    }

    @Override
    public void printAllStudentInfos() {
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            System.out.println(student);
        }


    }

    @Override
    public void printAverageScore() {
        Double sum = 0.0;
        for (int i = 0; i < students.length; i++) {
            Student student = students[i];
            sum += student.getGrade();
        }
        System.out.println("Average Score: " + sum/ students.length);

    }


}
