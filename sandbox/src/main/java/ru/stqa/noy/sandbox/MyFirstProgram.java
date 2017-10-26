package ru.stqa.noy.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

    Point p1 = new Point();
    p1.x = 1;
    p1.y = 4;

    Point p2 = new Point();
    p2.x = 4;
    p2.y = 1;
    
    System.out.println("Расстояние между двумя точками p1 и p2" + " = " + distance(p1,p2));


  }

   public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p2.x - p1.x,2) + Math.pow(p2.y - p1.y,2));


  }
}