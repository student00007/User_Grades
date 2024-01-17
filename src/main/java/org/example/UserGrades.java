package org.example;

import java.util.*;

public class UserGrades extends HashMap{

    private Map<String, List<Integer>> mapStudentGrades;

    private List<Integer> getListByName(String name) {
        if(mapStudentGrades.get(name) == null) {
            throw new RuntimeException("Список не содержит данного ученика");
        }
        return mapStudentGrades.get(name);
    }

    public UserGrades() {
        mapStudentGrades = new HashMap<>();
    }

    public void addMapStudentGrades(String name, List<Integer> listGrades) {
        mapStudentGrades.put(name, listGrades);
    }

    public void removeMapStudentGrades(String name) {

        mapStudentGrades.remove(name);
    }

    public List<Integer> getStudentGrades(String name) {
        return getListByName(name);
    }

    public Set<Entry<String, List<Integer>>> getMap() {

        if(mapStudentGrades.entrySet().isEmpty()){
            throw new RuntimeException("Список учеников пуст");}
        return mapStudentGrades.entrySet();
    }

    public void updateMapStudentGrades(String name, int oldGrade, int newGrade) {
        if(!mapStudentGrades.containsKey(name)){
            throw new RuntimeException("Список не содержит данного ученика");
        }
        if (getListByName(name).indexOf(oldGrade) != -1){
            getListByName(name).set(getListByName(name).indexOf(oldGrade), newGrade);
        }
    }


    @Override
    public String toString() {
        return "UserGrades{" +
                 mapStudentGrades +
                '}';
    }
}
