package com.company;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class DirectorsInterface {

    public static void main(String[] args) {

        School school = new School("COPERNICUS PRIMARY SCHOOL");
        Scanner sc = new Scanner(System.in);

        System.out.printf("\n\t\tWELCOME TO THE %s SYSTEM\n\n", school.getName());

        while(true) {
            System.out.println("<MENU> \tWhat action you would like to take ?");
            System.out.println("\t\t1. Add a new class");
            System.out.println("\t\t2. Add a new student");
            System.out.println("\t\t3. Add a grade");
            System.out.println("\t\t4. Move a student to another class");
            System.out.println("\t\t5. Show all classes and a number of students");
            System.out.println("\t\t6. Show all students");
            System.out.println("\t\t7. Show all grades for a particular student");
            System.out.println("\t\t8. Show grades in chosen period of time for a particular student");
            System.out.println("\t\t9. Show particular student averages");
            System.out.println("\t\t10. Show a students ranking");
            System.out.println("\t\t11. Remove a student");

            System.out.print("\n<INPUT> Select an option >> ");

            int option;
            String exitSymbol = "0";

            try {
                option = sc.nextInt();
            }catch (InputMismatchException inputMismatchException){
                System.out.println("-----------------------------------------");
                System.out.println("<ERROR> There is no such option. Try again.");
                System.out.println("-----------------------------------------");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            System.out.println("-----------------------------------------");

            switch (option){
                case 1:{
                    String className;
                    do{
                        System.out.print("<INPUT> Enter a class name (or 0 to quit) >> ");
                        className = sc.nextLine().toUpperCase();
                        if (school.checkIfClassExists(className)) {
                            System.out.println("<ERROR> The class name already exists.");
                        }
                    }while(school.checkIfClassExists(className) && !className.equals(exitSymbol));

                    if (className.equals(exitSymbol) ){
                        break;
                    }

                    school.createNewClass(className);
                    System.out.println("<OUTPUT> The class " + className + " has been created");
                    break;
                }

                case 2: {

                    if(school.getClasses().isEmpty()){
                        System.out.println("<ERROR> No classes available in the school, create one before adding a new student.");
                        break;
                    }

                    String studentFirstName;
                    String studentSecondName = null;

                    do{
                        System.out.print("<INPUT> Enter student first name (or 0 to quit) >> ");
                        studentFirstName = sc.nextLine();

                        if(studentFirstName.equals(exitSymbol)){
                            break;
                        }
                        System.out.print("<INPUT> Enter student second name (or 0 to quit) >> ");
                        studentSecondName = sc.nextLine();

                        if(studentSecondName.equals(exitSymbol)){
                            break;
                        }

                        if (school.checkIfStudentExists(studentFirstName,studentSecondName)) {
                            System.out.println("<ERROR> The student already exists. Try again.");
                        }

                    }while(school.checkIfStudentExists(studentFirstName,studentSecondName));

                    if(studentFirstName.equals(exitSymbol)){
                        break;
                    }
                    else if ( studentSecondName == null || !studentSecondName.equals(exitSymbol) ){

                        Student newStudent = school.createNewStudent(studentFirstName,studentSecondName);

                        System.out.println("<OUTPUT> Available classes are: ");
                        school.showAllTheClassesAndStudentsNumber();
                        String className;
                        System.out.print("<INPUT> Enter a class name to assign the student (or 0 to quit) >> ");
                        do{
                            className = sc.nextLine().toUpperCase(Locale.ENGLISH);

                            if (!school.checkIfClassExists(className)) {
                                System.out.print("<ERROR> The class does not exist. Try again. ");
                            }
                        }while(!school.checkIfClassExists(className));

                        if(className.equals(exitSymbol)){
                            break;
                        }

                        school.assignStudentToClass(newStudent, className);
                        System.out.printf("<OUTPUT> The student %s %s has been assigned to the class %s\n", studentFirstName, studentSecondName, className);
                        break;
                    }
                    break;

                }

                case 3:{

                    if(school.getSortedStudentsList().isEmpty()){
                        System.out.println("<ERROR> No students available in the school, create one before adding a new grade.");
                        break;
                    }

                    System.out.println("<INPUT> Choose a student to add a grade. Available students are: ");
                    school.showAllTheStudents();

                    System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                    int userInputStudent = sc.nextInt();
                    if(userInputStudent == 0){
                        break;
                    }
                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);
                    System.out.println("<OUTPUT> " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + "has been selected");

                    System.out.println("<OUTPUT> SUBJECTS:");
                    selectedStudent.printSubjects();
                    System.out.println("<INPUT> Select a subject that you want to add a grade to (or 0 to quit) >> ");
                    int userInputSubject = sc.nextInt();
                    if(userInputSubject == 0){
                        break;
                    }

                    for(int i=0; i<selectedStudent.getSubjects().length; i++){
                        if(selectedStudent.getSubjects()[i].ordinal() == userInputSubject-1 ){
                            Grade grade = selectedStudent.addGrade(selectedStudent.getSubjects()[i]);
                            System.out.printf("<OUTPUT> Grade (%d) has been added to %s for %s %s on  \n", userInputStudent, selectedStudent.getSubjects()[i].getName(),  selectedStudent.getFirstName(), selectedStudent.getSecondName());
                        }
                    }
                    break;
                }

                case 4:{

                    if(school.getSortedStudentsList().isEmpty()){
                        System.out.println("<ERROR> No students available in the school, create one before adding a new grade.");
                        break;
                    }else if(school.getClasses().size() < 2){
                        System.out.println("<ERROR> There is less than 2 classes in the school.");
                        break;
                    }

                    int userInputStudent;

                    while(true) {
                        System.out.println("<OUTPUT> Choose a student to be moved. Available students are: ");
                        school.showAllTheStudents();

                        System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                        userInputStudent = sc.nextInt();
                        if (userInputStudent == 0) {
                            continue;
                        } else if (userInputStudent < 1 || userInputStudent > school.getSortedStudentsList().size()) {
                            System.out.println("<ERROR> There is no such student. Select a value from 0 to " + school.getSortedStudentsList().size());
                            continue;
                        }
                        break;
                    }

                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);
                    System.out.println("<OUTPUT> " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + "has been selected");

                    System.out.println("<OUTPUT> Choose a class to move a student. Available classes (student number) are: ");
                    school.showAllTheClassesAndStudentsNumber();
                    String className;
                    do{
                        System.out.print("<INPUT> Enter a class name (or 0 to quit) >> ");
                        className = sc.nextLine().toUpperCase();

                        if (!school.checkIfClassExists(className)) {
                            System.out.print("<ERROR> The class does not exist, try again: ");
                        } else if( className.equals(selectedStudent.getaClass().getClassName())){
                            System.out.println("<ERROR> Cannot move student to the same class. Pleas pick another one.\n");
                        }
                    }while(!school.checkIfClassExists(className) || className.equals(selectedStudent.getaClass().getClassName()));

                    if(className.equals(exitSymbol)){
                        break;
                    }

                    school.moveStudentToAnotherClass(selectedStudent,className);

                    System.out.printf("<OUTPUT> The student " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + " " + "has been moved to the " + className + " class.");
                    break;
                }

                case 5:{

                    if(school.getClasses().isEmpty()){
                        System.out.println( "<OUTPUT> There are no classes or students in the school");
                        break;
                    }

                    System.out.println( "<OUTPUT> " + school.getName() + " classes & student number: ");
                    school.showAllTheClassesAndStudentsNumber();
                    break;
                }

                case 6:{

                    if(school.getSortedStudentsList().isEmpty()){
                        System.out.println( "<OUTPUT> There are no students in the school");
                        break;
                    }

                    System.out.println("<OUTPUT> " + school.getName() + "'s students list:");
                    school.showAllTheStudents();
                    System.out.println();
                    break;
                }

                case 7:{
                    System.out.printf("<OUTPUT> List of the students");
                    school.showAllTheStudents();
                    System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                    int userInputStudent = sc.nextInt();

                    if(userInputStudent == 0){
                        break;
                    }

                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);

                    System.out.println("<OUTPUT> " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + " grades are: ");
                    selectedStudent.showStudentGrades();
                    break;
                }

                case 8:{
                    System.out.println("<OUTPUT> List of the students: ");
                    school.showAllTheStudents();
                    System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                    int userInputStudent = sc.nextInt();

                    if(userInputStudent == 0){
                        break;
                    }

                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);

                    LocalDate startingDate;
                    LocalDate endingDate;

                    do {
                        System.out.print("<INPUT> Set starting date >> ");
                        startingDate = selectedStudent.setGradeDate(sc);
                        System.out.println();
                        System.out.print("<INPUT> Set ending date >> ");
                        endingDate = selectedStudent.setGradeDate(sc);

                        if (startingDate.isAfter(endingDate)) {
                            System.out.printf("<ERROR> Starting date cannot be a later date than ending date. Try again.");
                        }
                    } while (startingDate.isAfter(endingDate));

                    System.out.println("<OUTPUT> " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + " grades between " + startingDate.toString() + " and " + endingDate.toString() + " are: ");
                    selectedStudent.showStudentGradesInTimePeriod(startingDate,endingDate);
                    break;
                }

                case 9:{
                    System.out.println("<OUTPUT> List of the students");
                    school.showAllTheStudents();
                    System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                    int userInputStudent = sc.nextInt();

                    if(userInputStudent == 0){
                        break;
                    }

                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);

                    System.out.println("<OUTPUT> " + selectedStudent.getFirstName() + " " + selectedStudent.getSecondName() + " averages are: ");
                    selectedStudent.showStudentAverages();
                    break;
                }

                case 10:{

                    List<Student> sortedStudents = school.sortStudentsByTotalAverage();

                    System.out.println("<OUTPUT> Students ranking: ");

                    for (int k=0; k<sortedStudents.size(); k++){

                        Student currentStudent = sortedStudents.get(k);

                        System.out.print("\t" + (k+1) + ":\t");
                        System.out.printf("\t%s %s (%.2f)\n", currentStudent.getFirstName(), currentStudent.getSecondName(), currentStudent.getTotalAverage());
                    }

                    break;
                }


                case 11:{
                    if(school.getSortedStudentsList().isEmpty()){
                        System.out.println("<ERROR> No students available in the school.");
                        break;
                    }
                    int userInputStudent;

                    while(true) {
                        System.out.println("<OUTPUT> Choose a student to be removed. Available students are: ");
                        school.showAllTheStudents();

                        System.out.print("<INPUT> Select a student (or 0 to quit) >> ");
                        userInputStudent = sc.nextInt();
                        if (userInputStudent == 0) {
                            continue;
                        } else if (userInputStudent < 1 || userInputStudent > school.getSortedStudentsList().size()) {
                            System.out.println("<ERROR> There is no such student. Select a value from 0 to " + school.getSortedStudentsList().size());
                            continue;
                        }
                        break;
                    }

                    Student selectedStudent = school.getSortedStudentsList().get(userInputStudent -1);

                    school.removeStudent(selectedStudent);
                    System.out.printf("<OUTPUT> The student %s %s has been removed", selectedStudent.getFirstName(), selectedStudent.getSecondName());

                    break;

                }



                default:{
                    System.out.println("<ERROR> There is no such option. Try again.");
                    break;
                }


            }
            System.out.println("-----------------------------------------");
        }
    }
}
