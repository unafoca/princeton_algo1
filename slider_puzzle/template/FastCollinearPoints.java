/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int n_point;
    private ArrayList<LineSegment> lineSeg = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        n_point = points.length;

        Point[] points_copy = points.clone();
        Arrays.sort(points_copy);

        for (int i1 = 0; i1 < n_point - 3; i1++) {
            // Point[] tmp_points = new Point[n_point - 1];
            // int pointer = 0;
            Point curr_point = points_copy[i1];
            Comparator<Point> comp = curr_point.slopeOrder();
            Arrays.sort(points);
            Arrays.sort(points, comp);

            double slope_curr = -1.0 / 0.0;
            int counter = 1;
            int ind = 1;
            for (int i2 = 1; i2 < n_point; i2++) {
                if (curr_point.slopeTo(points[i2]) == slope_curr) {
                    counter++;
                }
                else if (curr_point.slopeTo(points[i2]) > slope_curr) {
                    if (counter >= 3 && ind == 1) {
                        lineSeg.add(new LineSegment(curr_point, points[i2 - 1]));
                    }
                    if (curr_point.compareTo(points[i2]) < 0) {
                        ind = 1;
                    }
                    else {
                        ind = 0;
                    }
                    counter = 1;
                    slope_curr = curr_point.slopeTo(points[i2]);
                }

            }
            if (counter >= 3 && ind == 1) {
                lineSeg.add(new LineSegment(curr_point, points[n_point - 1]));
            }

        }
    }     // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return lineSeg.size();
    }        // the number of line segments

    public LineSegment[] segments() {
        return lineSeg.toArray((new LineSegment[lineSeg.size()]));
    }         // the line segments

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 4);
        Point p3 = new Point(3, 6);
        Point p4 = new Point(4, 8);
        Point p5 = new Point(5, 10);

        FastCollinearPoints test = new FastCollinearPoints(
                new Point[] { p1, p2, p3, p4, p5 });
        System.out.println(test.numberOfSegments());
        System.out.println(Arrays.toString(test.segments()));
        // System.out.println(points);
    }
}
