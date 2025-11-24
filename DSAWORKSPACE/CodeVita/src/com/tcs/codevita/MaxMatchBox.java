package com.tcs.codevita;
import java.util.*;

public class MaxMatchBox {
    static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        Point[] polygon = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            polygon[i] = new Point(x, y);
        }
        
        double maxVolume = 0.0;
        double maxPossibleH = findMaxPossibleH(polygon);
        
        for (double H = 0.1; H <= maxPossibleH + 1e-9; H += 0.1) {
            if (isValidHeight(polygon, H)) {
                double baseArea = calculateBaseArea(polygon, H);
                double volume = baseArea * H;
                if (volume > maxVolume) {
                    maxVolume = volume;
                }
            }
        }
        
        System.out.printf("%.2f\n", maxVolume);
        scanner.close();
    }
    
    private static double findMaxPossibleH(Point[] polygon) {
        double minEdgeLength = Double.MAX_VALUE;
        int n = polygon.length;
        
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            double edgeLength = distance(polygon[i], polygon[next]);
            minEdgeLength = Math.min(minEdgeLength, edgeLength);
        }
        
        return Math.max(0, (minEdgeLength - 0.1) / 2.0);
    }
    
    private static boolean isValidHeight(Point[] polygon, double H) {
        int n = polygon.length;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            double edgeLength = distance(polygon[i], polygon[next]);
            if (edgeLength - 2 * H < 0.1 - 1e-9) {
                return false;
            }
        }
        return true;
    }
    
    private static double calculateBaseArea(Point[] polygon, double H) {
        int n = polygon.length;
        Point[] offsetPolygon = new Point[n];
        
        double area = calculatePolygonArea(polygon);
        double perimeter = calculatePerimeter(polygon);
        
        return area - perimeter * H + 4 * H * H;
    }
    
    private static double calculatePolygonArea(Point[] polygon) {
        double area = 0.0;
        int n = polygon.length;
        
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += polygon[i].x * polygon[j].y - polygon[j].x * polygon[i].y;
        }
        
        return Math.abs(area) / 2.0;
    }
    
    private static double calculatePerimeter(Point[] polygon) {
        double perimeter = 0.0;
        int n = polygon.length;
        
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            perimeter += distance(polygon[i], polygon[j]);
        }
        
        return perimeter;
    }
    
    private static double distance(Point a, Point b) {
        return Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
    }
}