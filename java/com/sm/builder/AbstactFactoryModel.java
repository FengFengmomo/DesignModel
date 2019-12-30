package com.sm.builder;

public class AbstactFactoryModel {
    interface Shape{
        void draw();
    }
    class Reactangle implements Shape {
        @Override
        public void draw() {
            System.out.println("rectangle");
        }
    }
    class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("circle");
        }
    }
    class ShapeFactory implements AbstractFactory{
        @Override
        public Color getColor(String color) {
            return null;
        }

        public  Shape getShape(String shape){
            if ("shape".equals(shape))
                return new Reactangle();
            else return new Circle();
        }
    }

    interface Color{
        void getColor();
    }
    class Red implements Color{
        @Override
        public void getColor() {
            System.out.println("red");
        }
    }
    class Blue implements Color{
        @Override
        public void getColor() {
            System.out.println("blue");
        }
    }
    class ColorFactory implements AbstractFactory{
        @Override
        public Shape getShape(String shape) {
            return null;
        }

        public Color getColor(String color){
            if ("red".equals(color))
                return new Red();
            else return new Blue();
        }
    }
    interface  AbstractFactory{
         Shape getShape(String shape);
         Color getColor(String color);
    }
    public  AbstractFactory getFactory(String factory){
        if ("shapeFactory".equals(factory)){
            return new ShapeFactory();
        }else return new ColorFactory();
    }
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstactFactoryModel().getFactory("shapeFactory");
        Shape shape = abstractFactory.getShape("circle");
        shape.draw();
        Color red = abstractFactory.getColor("red");
        red.getColor();
    }
}
