import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.*;

public class MaxSideLength {
    Point[] points;
    Point[] closestPair = new Point[2];

    public void setPoints(Point[] points) {
        this.points = points;
      //  this.closestPair = new Point[2];
    }

    public MaxSideLength(Point[] points) {
        this.points = points;
    }


    void sortPoints() {
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point first, Point second)
            {
                if(first.x == second.x)
                    return first.y - second.y;
                return first.x - second.x;
            }
        });
    }

    public double calcMaxSideLength(){
        sortPoints();
        double minm_distance = FindClosestPair(0, points.length);
        return max(abs(closestPair[0].x - closestPair[1].x) ,
                abs(closestPair[0].y - closestPair[1].y)  );
    }

    double bruteForce(int start_index, int end_index){
        double d = Integer.MAX_VALUE;
        for(int i=start_index; i<end_index; i++){
            for(int j=i+1; j<end_index; j++) {
                double x = distance(points[i], points[j]);
                if(x < d) {
                    double y;
                    if(closestPair[0] == null)
                         y = Integer.MAX_VALUE;
                    else
                         y = distance(closestPair[0], closestPair[1]);
                    if(x < y) {
                        closestPair[0] = points[i];
                        closestPair[1] = points[j];
                    }
                    d = distance(points[i], points[j]);
                }
            }
        }
        return d;
    }
    double FindClosestPair(int start_index, int end_index){
        if ((end_index - start_index) <= 3) {
            return bruteForce(start_index, end_index);
        }
        int mid_index = start_index + (end_index - start_index) / 2;
        double sigma_l = FindClosestPair(start_index, mid_index);
        double sigma_r = FindClosestPair(mid_index, end_index);
        double sigma_min = min(sigma_r, sigma_l);
        ArrayList<Point> points_in_strip = new ArrayList<Point>();
        for(int i=start_index; i<end_index; i++){
            if(abs(points[i].x -  points[mid_index].x) <= sigma_min)
                points_in_strip.add(points[i]);
        }
        return minInStrip(points_in_strip, sigma_min);
    }

    double distance(Point p1, Point p2){
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y)
        );
    }

    double minInStrip(ArrayList<Point> points_in_strip, double prev_min){
        double d = prev_min;
        int n = min(points_in_strip.size(), 7);
        for(int i=0; i<points_in_strip.size(); i++){
            for(int j=1; i+j< n; j++){
                double x = distance(points_in_strip.get(i), points_in_strip.get(i + j));
                if(x < prev_min) {
                    double y;
                    if(closestPair[0] == null)
                        y = Integer.MAX_VALUE;
                    else
                        y = distance(closestPair[0], closestPair[1]);
                    if(x < y) {
                        closestPair[0] = points_in_strip.get(i);
                        closestPair[1] = points_in_strip.get(i + j);
                    }
                    d = distance(points_in_strip.get(i), points_in_strip.get(i + j));
                }
            }
        }
        return d;
    }

   /* public static void main(String args[]) {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(1, 3);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(100, 1);
        Point p5 = new Point(1, 100);
        Point p6 = new Point(1, 9);
        Point p7 = new Point(6,3);

        MaxSideLength m = new MaxSideLength(new Point[]{p1, p2, p3, p4, p5, p6, p7});
        m.sortPoints();
        System.out.println(m.calcMaxSideLength());
       // System.out.println(m.FindMaxSideLength());
    }*/
}
