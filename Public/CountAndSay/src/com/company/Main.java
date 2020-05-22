package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите n для вывода n-ого числа последовательности");
        int n = in.nextInt();
        CountAndSay s = new CountAndSay();
        System.out.println(s.generate(n));
    }
}
