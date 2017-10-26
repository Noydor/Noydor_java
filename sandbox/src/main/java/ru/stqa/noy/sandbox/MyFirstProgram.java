package ru.stqa.noy.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

    Point p1 = new Point(1,4);
    Point p2 = new Point(4,1);

    System.out.println("Расстояние между двумя точками p1 и p2" + " = " + p1.distance(p2));

  }

}