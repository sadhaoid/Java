package org.simplerental;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Student[] allstudents = new Student[5];
        allstudents[0] = new Student("1","male",90.5);
        allstudents[1]= new Student("2","female",91.5);
        allstudents[2]= new Student("3","male",92.5);
        allstudents[3]= new Student("4","female",93.5);
        allstudents[4]= new Student("5","male",94.5);

        ClassDataInterface cdi = new ClassDataInterfaceImpl2(allstudents);
        cdi.printAllStudentInfos();
        cdi.printAverageScore();
    }





}