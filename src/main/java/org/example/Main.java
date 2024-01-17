package org.example;

import java.io.*;
import java.util.*;

public class Main {

    private static final String MENU = """
            
            Выберете необходимое действие:
            a. Добавьте нового ученика
            b. Удалите ученика
            c. Обновите оценку ученика
            d. Просмотр оценок всех учащихся
            f. Просмотр оценок конкретного учащегося
            """;
    public static void main(String[] args) throws IOException {

        UserGrades userGrades = new UserGrades();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вы хотите загрузить файл с оценками? да или нет");
        String load = scanner.next();
        if (load.equals("да")){
            System.out.println("Загружен");
        }

        File file = new File(
                "userGrades.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] parts = line.split(",");
            String key = parts[0].trim();
            String[] valuesStr = parts[1].trim().split(" ");
            List<Integer> values = new LinkedList<>();
            for (String value : valuesStr) {
                values.add(Integer.parseInt(value));
            }
            userGrades.addMapStudentGrades(key, values);
        }

        while (true) {
            System.out.println(MENU);
            String point = scanner.next();

            switch (point) {
                case "a":
                    System.out.println("Введите имя нового ученика");
                    String studentName = scanner.next();
                    List<Integer> listGrades = new LinkedList<>();

                    while(true){
                        String str = scanner.nextLine();
                        if(str.equals("exit")){
                            break;
                        }
                        try {
                            listGrades.add(Integer.parseInt(str));
                        }
                        catch (NumberFormatException e){
                            System.out.println("Введите число или 'exit' для завершения:");
                        }
                    }
                    userGrades.addMapStudentGrades(studentName, listGrades);
                    System.out.println(userGrades);
                    break;

                case "b":
                    System.out.println("Ведите имя удаляемого ученика");
                    String removeStudentName = scanner.next();
                    userGrades.removeMapStudentGrades(removeStudentName);
                    System.out.println(userGrades);
                    break;

                case "c":
                    System.out.println("Введите имя ученика у которого нужно изменить оценку");
                    String name = scanner.next();
                    try{
                        System.out.println(userGrades.getStudentGrades(name));
                        System.out.println("Введите оценку ученика, которую нужно изменить");
                        int oldGrade = scanner.nextInt();
                        System.out.println("Введите оценку на которую нужно изменить");
                        int newGrade = scanner.nextInt();
                        userGrades.updateMapStudentGrades(name, oldGrade, newGrade);
                        System.out.println(userGrades);
                    }
                    catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "d":

                    try{
                        System.out.println(userGrades.getMap());}
                    catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case "f":
                    System.out.println("Ввведите имя учащегося, оценки которого хотите посмотреть");
                    String showName = scanner.next();
                    try {
                        System.out.println(userGrades.getStudentGrades(showName));
                    }
                    catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}



