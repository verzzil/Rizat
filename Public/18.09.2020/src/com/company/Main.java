package com.company;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsersRepositoryJdbcImpl postgres = new UsersRepositoryJdbcImpl();
        postgres.createConnection();

        List<User> man = postgres.findAllByAge(75);
        for (User chelik : man) {
            System.out.println(chelik);
        }


    }
}
