package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var repo = new StudentsRepository();

        var list = repo.getList();

        for (var student: list) {
            System.out.println(student);
        }

        var grades = new HashSet<Grade>();
        grades.add(new Grade(0,(short)4, 1));
        grades.add(new Grade(0,(short)9, 2));

        var st1 = repo.getById(2);

        st1.setGrades(grades);

        repo.update(st1);

    }
}
