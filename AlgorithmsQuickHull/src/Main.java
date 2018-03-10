//CS4306 #04
//Brent Einolf
//000719724
//ProgAssignment 1
//2/25/2018
//BRENT EINOLF
//Score:

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Test List
        ArrayList<Point> toBeSorted = new ArrayList<>();

        //Container for hull
        ArrayList<Point> hull = new ArrayList<>();

        //Test Points
        Point p3 = new Point(0,3);
        Point p4 = new Point(1,1);
        Point p5 = new Point(2,2);
        Point p6 = new Point(4,4);
        Point p7 = new Point(0,0);
        Point p8 = new Point(1,2);
        Point p9 = new Point(3,1);
        Point p10 = new Point(3,3);

        toBeSorted.add(p3);
        toBeSorted.add(p4);
        toBeSorted.add(p5);
        toBeSorted.add(p6);
        toBeSorted.add(p7);
        toBeSorted.add(p8);
        toBeSorted.add(p9);
        toBeSorted.add(p10);


        hull = QuickHull(toBeSorted);

        System.out.println("First Set of test points OUTPUT");
        for(int i=0; i<=hull.size()-1; i++) {
            System.out.print(hull.get(i).getXval() + ",");
            System.out.print(hull.get(i).getYval());
            System.out.println("");
        }

        toBeSorted.clear();

        //Test Points Set 2
        Point testp1 = new Point(1,6);
        Point testp2 = new Point(4,15);
        Point testp3 = new Point(7,7);
        Point testp4 = new Point(10,13);
        Point testp5 = new Point(11,6);
        Point testp6 = new Point(11,18);
        Point testp7 = new Point(11,21);
        Point testp8 = new Point(12,10);
        Point testp9 = new Point(15,18);
        Point testp10 = new Point(16,6);
        Point testp11 = new Point(18,3);
        Point testp12 = new Point(18,12);
        Point testp13 = new Point(19,15);
        Point testp14 = new Point(22,19);


        toBeSorted.add(testp1);
        toBeSorted.add(testp2);
        toBeSorted.add(testp3);
        toBeSorted.add(testp4);
        toBeSorted.add(testp5);
        toBeSorted.add(testp6);
        toBeSorted.add(testp6);
        toBeSorted.add(testp7);
        toBeSorted.add(testp8);
        toBeSorted.add(testp9);
        toBeSorted.add(testp10);
        toBeSorted.add(testp11);
        toBeSorted.add(testp12);
        toBeSorted.add(testp13);
        toBeSorted.add(testp14);

        hull = QuickHull(toBeSorted);

        System.out.println("Second Set of test points OUTPUT");
        for(int i=0; i<=hull.size()-1; i++) {
            System.out.print(hull.get(i).getXval() + ",");
            System.out.print(hull.get(i).getYval());
            System.out.println("");
        }

    }

    static ArrayList<Point> QuickHull(ArrayList<Point> toFind) {

        ArrayList<Point> convexHull = new ArrayList<>();

        if (toFind.size() <= 3) {
            throw new IllegalArgumentException("Must be greater than 3 points to find hull");
        }

        //Find leftmost and rightmost X values
        Point minX = toFind.get(0);
        Point maxX = toFind.get(0);

        for (int i = 1; i <= (toFind.size() - 1); i++) {
            Point point = toFind.get(i);
            if (point.getXval() > maxX.getXval()) {
                maxX = point;
            } else if(point.getXval() < minX.getXval()) {
                minX = point;
            }

        }

        //Find points to the left and right of the line drawn between the extreme values
        ArrayList<Point> leftOfLine = new ArrayList<>();
        ArrayList<Point> rightOfLine = new ArrayList<>();
        for (int i = 0; i <= (toFind.size() - 1); i++) {
            Point point = toFind.get(i);
            if(!point.equals(minX) || !point.equals(maxX)) {
                if(point.isOnLeft(minX,maxX)) {
                    leftOfLine.add(point);
                }
                else {
                    rightOfLine.add(point);
                }
            }
        }


        ArrayList<Point> division = new ArrayList<>();

        division = Subset(leftOfLine, minX, maxX);
        convexHull.addAll(division);
        convexHull.add(maxX);

        division = Subset(rightOfLine, maxX, minX);
        convexHull.addAll(division);

        return convexHull;
    }

    static ArrayList<Point> Subset(ArrayList<Point> set, Point min, Point max) {

        //break condition
        if (set.size() <= 1) {
            return set;
        }

        ArrayList<Point> hullPoints = new ArrayList<Point>();

        //Find the point that is the maximum distance from line
        Point maxDistPoint = new Point(0,0);
        float currMaxDistance = -1;

        for(int i = 0; i <= set.size() - 1; i++) {
            Point point = set.get(i);
            float distance = point.distFromLine(min, max);
            if (distance > currMaxDistance) {
                currMaxDistance = distance;
                maxDistPoint = point;
            }
        }

        //remove from subset to add to Hull later
        set.remove(maxDistPoint);


        //split problem into 2 subsets
        ArrayList<Point> subSet1 = new  ArrayList<>();
        ArrayList<Point> subSet2 = new  ArrayList<>();

        for (int i = 0; i <= set.size() - 1; i++) {
            Point point = set.get(i);
            if (point.isOnLeft(min, maxDistPoint)) {
                subSet1.add(point);
            } else if (point.isOnLeft(min, maxDistPoint)) {
                subSet2.add(point);
            }
        }

        //ignore all values not in subsets;
        set.clear();

        ArrayList<Point> partition = Subset(subSet1, min, maxDistPoint);
        hullPoints.addAll(partition);
        hullPoints.add(maxDistPoint);

        partition = Subset(subSet2, maxDistPoint, max);
        hullPoints.addAll(partition);

        return hullPoints;
    }
}
