package com.company;

import java.io.PrintStream;

import java.util.*;

public  class Main {
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        out.println(new Complex(1.0, 2).pow(0.5));
        out.println(new Complex(1.0, 2).sqrt());
    }
}