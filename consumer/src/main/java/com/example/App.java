/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example;


public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        new Library().someLibraryMethod();
        System.out.println(new App().getGreeting());
    }
}
