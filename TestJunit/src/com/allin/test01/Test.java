package com.allin.test01;

/**
 * @author: allin
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello Junit");
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(10, 20));

        System.out.println(calculator.sub(30, 10));
    }
}
