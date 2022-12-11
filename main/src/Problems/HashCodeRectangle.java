package Problems;

import java.util.HashMap;
import java.util.Random;

public class HashCodeRectangle {
    public static void main(String[] args) {
        int testLimit = 1000000;

        HashMap<Rectangle, ?> map = new HashMap<>();

        long start = System.nanoTime();
        for (int i = 0; i < testLimit; i++) {
            Rectangle r = new Rectangle(getRandom(), getRandom());
            map.put(r, null);
        }
        long end = System.nanoTime();
        System.out.print("XOR ");
        System.out.println(end - start);


        HashMap<Rectangle, ?> map1 = new HashMap<>();

        start = System.nanoTime();
        for (int i = 0; i < testLimit; i++) {
            Rectangle r = new Rectangle(getRandom(), getRandom(), new PolynomialRectangleHash(10));
            map1.put(r, null);
        }
        end = System.nanoTime();
        System.out.print("Polynomial a=10 ");
        System.out.println(end - start);


        HashMap<Rectangle, ?> map2 = new HashMap<>();
        start = System.nanoTime();
        for (int i = 0; i < testLimit; i++) {
            Rectangle r = new Rectangle(getRandom(), getRandom(), new PolynomialRectangleHash(101));
            map2.put(r, null);
        }
        end = System.nanoTime();
        System.out.print("Polynomial a=101 ");
        System.out.println(end - start);
    }

    private static class PolynomialRectangleHash implements RectangleHash {
        private final int a;

        public PolynomialRectangleHash(int coefficient) {
            a = coefficient;
        }

        @Override
        public int hashCodeCalc(int width, int height) {
            // since the object only have width and height and the order of weight is not that important
            return width + a * height;
        }
    }

    interface RectangleHash {
        int hashCodeCalc(int width, int height);
    }

    public static class Rectangle implements Comparable<Rectangle> {

        private static class DefaultComputerHash implements RectangleHash {

            @Override
            public int hashCodeCalc(int width, int height) {
                return width ^ height;
            }
        }

        private final int width;
        private final int height;

        private final RectangleHash hashObj;


        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
            this.hashObj = new DefaultComputerHash();
        }

        public Rectangle(int width, int height, RectangleHash hashObj) {
            this.width = width;
            this.height = height;
            this.hashObj = new DefaultComputerHash();
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        /**
         * space-complexity O(1)
         * time-complexity O(1) all used operations in it are O(1)
         */
        @Override
        public int compareTo(Rectangle o) {
            int widthDifference = width - o.width;
            if (widthDifference == 0) {
                return height - o.height;
            }
            return widthDifference;
        }

        @Override
        public String toString() {
            return "(" + this.width + ", " + this.height + ")";
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // equal to itself

            // different class instance
            if (o == null || getClass() != o.getClass()) return false;

            // casting
            Rectangle rectangle = (Rectangle) o;

            return width == rectangle.width && height == rectangle.height;
        }

        @Override
        public int hashCode() {
            return hashObj.hashCodeCalc(width, height);
        }
    }

    private static int getRandom() {
        Random random = new Random();
        return random.nextInt(100 - 1) + 1;
    }
}
