package dev;

/**
 * It's noon. The sun is directly over head. There are several people holding rectangular umbrellas. How much shade do
 * the umbrellas cast?
 * <p>
 * You have a Collection of umbrella locations and sizes (height, with, x, y). (x,y) is the lower left corner of the
 * umbrella. All numbers are positive integers.
 *
 *    6  +-----+
 *    5  |  A  | +-------+
 *    4  |  +--+-+-+  B  |
 *    3  |  |  | +-+-----+             (source of truth)
 *    2  +--+--+   |   +----+           view from above
 *    1     |   C  |   | D  |
 *    0     +------+   +----+
 *       01234567891111111111
 *                 0123456789
 *
 *       ! (x,y,w,h)
 *       A (0,2,7,5)
 *       B (8,3,9,3)
 *       C (3,0,8,5)
 *       D (14,0,6,3)
 */
public class Main {
    public static void main(String[] args) {
        Rectangle a = new Rectangle("A",0, 2, 7, 5);
        Rectangle b = new Rectangle("B", 8, 3, 9, 3);
        Rectangle c = new Rectangle("C", 3, 0, 8, 5);
        Rectangle d = new Rectangle("D", 14, 0, 6, 3);

        printArea(a);
        printArea(a,b,c,d);
    }

    private static void printArea(Rectangle ...umbrellas) {
        System.out.println("adding up...");
        for(Rectangle umbrella : umbrellas) {
            System.out.println(" ===> " + umbrella.toString());
        }
        System.out.println( "  total shaded area is " + shadedArea(umbrellas));
    }

    private static int shadedArea(Rectangle... umbrellas) {
        Rectangle virtualTotalUmbrella = umbrellas[0];
        int area = virtualTotalUmbrella.area();
        System.out.println("area: " + area );

        for( int i = 1; i < umbrellas.length; i++) {
            Rectangle umbrella = umbrellas[i];
            area += umbrella.area() - umbrella.intersection(virtualTotalUmbrella).area();
            System.out.println("area 1: " + area );
            virtualTotalUmbrella = virtualTotalUmbrella.combineAsRectangle(umbrella);
        }
        return area;
    }


    public static class Rectangle {
        public final String name;
        public final int height;
        public final int width;
        public final int x;
        public final int y;

        public Rectangle(String name, int height, int width, int x, int y) {
            this.name = name;
            this.height = height;
            this.width = width;
            this.x = x;
            this.y = y;
        }

        public Rectangle intersection(Rectangle that) {
            System.out.println("area 2: " + that.toString() );
            return new Rectangle(name + " ⋂ " + that.name, Math.max(this.x, that.x),
                    Math.max(this.y, that.y),
                    Math.min(this.x + this.width, that.x + that.width),
                    Math.min(this.y + this.height, that.y + that.height));
        }

        public int area() {
            return height * width;
        }

        /** Return the smallest rectangle that includes this and the target */
        public Rectangle combineAsRectangle(Rectangle target) {
            return new Rectangle(name + " X " + target.name, Math.min(this.x, target.x),
                    Math.min(this.y, target.y),
                    Math.max(this.x + this.width, target.x + target.width),
                    Math.max(this.y + this.height, target.y + target.height));
        }

        public String toString() {
            return name + "(" + x + ", " + y + ", " + width + ", " + height + ")";
        }
    }
}