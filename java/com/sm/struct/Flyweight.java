package com.sm.struct;

import java.util.HashMap;

/**
 * 主要用于减少创建对象的数量，以减少内存占用和提高性能。
 *
 * 在有大量对象时，有可能会造成内存溢出，我们把其中共同的部分抽象出来，如果有相同的业务请求，直接返回在内存中已有的对象，避免重新创建
 */
public class Flyweight {

    interface Shape {
        void draw();
    }
    static class Circle implements Shape {
        private String color;
        private int x;
        private int y;
        private int radius;

        public Circle(String color){
            this.color = color;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Circle: Draw() [Color : " + color
                    +", x : " + x +", y :" + y +", radius :" + radius);
        }
    }
    static class ShapeFactory {
        private static final HashMap<String, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = (Circle)circleMap.get(color);

            if(circle == null) {
                circle = new Circle(color);
                circleMap.put(color, circle);
                System.out.println("Creating circle of color : " + color);
            }
            return circle;
        }
    }
}
