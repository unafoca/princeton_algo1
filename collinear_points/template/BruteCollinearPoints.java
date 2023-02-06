/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints {

    private Point[] points;
    private int n_point;
    private int n_seg;
    private LineSegment[] lineSeg;

    public BruteCollinearPoints(Point[] points) {
        this.points = points;
        n_point = points.length;
        LineSegment[] tmp_lineseg = new LineSegment[
                n_point * (n_point - 1) * (n_point - 2) * (n_point - 3) / 24];

        int pointer = 0;

        for (int i1 = 0; i1 < n_point - 3; i1++) {
            Point p1 = points[i1];
            // Comparator<Point> comp = p1.slopeOrder();
            for (int i2 = i1 + 1; i2 < n_point - 2; i2++) {
                Point p2 = points[i2];
                for (int i3 = i2 + 1; i3 < n_point - 1; i3++) {
                    Point p3 = points[i3];
                    for (int i4 = i3 + 1; i4 < n_point; i4++) {
                        Point p4 = points[i4];
                        if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4)) {
                            Point[] tempArr = { p1, p2, p3, p4 };
                            Arrays.sort(tempArr);
                            tmp_lineseg[pointer] = new LineSegment(tempArr[0], tempArr[3]);
                            pointer++;
                        }


                    }
                }
            }
        }
        n_seg = pointer;
        lineSeg = new LineSegment[pointer];
        for (int i = 0; i < pointer; i++) {
            lineSeg[i] = tmp_lineseg[i];
        }
    }

    ;    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return n_seg;
    }

    ;        // the number of line segments

    public LineSegment[] segments() {
        return lineSeg;
    }

    ;

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 4);
        Point p3 = new Point(3, 6);
        Point p4 = new Point(4, 8);
        Point p5 = new Point(5, 10);
        Point p6 = new Point(2, 2);
        // inp = new Point[] {p1,p2,p3,p4}
        BruteCollinearPoints test = new BruteCollinearPoints(
                new Point[] { p1, p2, p3, p4, p6 });
        System.out.println(test.numberOfSegments());
    }
}
