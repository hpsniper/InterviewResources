package interview.coding;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/*
    Return the area inside the intersection of 2 rectangles given as 2 pairs of 2 points.
 */
class Rectangle {
    Point bottomLeft;
    Point topRight;

    Rectangle(Point bottomLeft, Point topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    int getLength() {
        return topRight.x - bottomLeft.x;
    }

    int getHeight() {
        return topRight.y - bottomLeft.y;
    }

    int getArea() {
        return getLength() * getHeight();
    }
}

public class IntersectingRectangle {

    public int findIntersectingRectangleArea(Rectangle r1, Rectangle r2) {
        Rectangle intersection = new Rectangle(findInnerBottomLeft(r1, r2), findInnerTopRight(r1, r2));
        return intersection.getArea();
    }

    private Point findInnerBottomLeft(Rectangle r1, Rectangle r2) {
        int x = Math.max(r1.bottomLeft.x, r2.bottomLeft.x);
        int y = Math.max(r1.bottomLeft.y, r2.bottomLeft.y);

        return new Point(x,y);
    }

    private Point findInnerTopRight(Rectangle r1, Rectangle r2) {
        int x = Math.min(r1.topRight.x, r2.topRight.x);
        int y = Math.min(r1.topRight.y, r2.topRight.y);

        return new Point(x,y);
    }

    public static void printTest(int xA1, int yA1, int xA2, int yA2,
                                 int xB1, int yB1, int xB2, int yB2, int expected) {
        IntersectingRectangle ir = new IntersectingRectangle();

        // example 1
        Point p1 = new Point(xA1, yA1);
        Point p2 = new Point(xA2, yA2);
        Rectangle r1 = new Rectangle(p1, p2);
        Point p3 = new Point(xB1, yB1);
        Point p4 = new Point(xB2, yB2);
        Rectangle r2 = new Rectangle(p3, p4);

        int actual = ir.findIntersectingRectangleArea(r1, r2);
        System.out.println(String.format("Expected: %d Actual: %d - %b", expected, actual, expected == actual));
    }

    public static void main(String[] args) {
        IntersectingRectangle ir = new IntersectingRectangle();

        // example 1
        printTest(0,0,3,2,2,1,5,3,1);

        // example 2
        printTest(0,2,3,4,1,1,5,3,2);

        // example 3
        printTest(0,0,2,2,1,0,3,2,2);

        // example 3
        printTest(0,0,2,2,0,0,2,2,4);

        // example 4 no overlap
        printTest(0,0,2,2,2,2,4,4,0);

        // example 5 all negative points
        printTest(-5,-5,-3,-1,-7,-9,-3,-3,4);

        // example 6 all negative points
        printTest(-4,-3,-1,-1,-5,-4,-2,-2,2);

        // example 7 some negative points
        printTest(1,-3,4,-1,3,-2,4,-1,1);
    }
}
