package com.company;

public class Main {

    public static void main(String[] args) {
        var repo = new StudentsRepository();

        //var stud1 = new Student(0, "Jānis", "Ozols","222");

        //var newId = repo.add(stud1);

        var stud2 = new Student(1, "Augusts3", "Lin3","1113");

        repo.update(stud2);

        for (var student : repo.getList()) {
            System.out.println(student);
        }

        var stud3 = repo.getById(2);

        System.out.println(stud3);

        repo.delete(1);
    }
}
