package com.sm.builder;

public class FactoryModel {


    interface Shape{
        void draw();
    }
    class Reactangle implements Shape{
        @Override
        public void draw() {
            System.out.println("rectangle");
        }
    }
    class Circle implements Shape{
        @Override
        public void draw() {
            System.out.println("circle");
        }
    }
    public Shape getShape(String shape){
        if ("shape".equals(shape))
            return new Reactangle();
        else return new Circle();
    }

    public static void main(String[] args) {
        Shape shape = new FactoryModel().getShape("circle");
        shape.draw();
    }
}
