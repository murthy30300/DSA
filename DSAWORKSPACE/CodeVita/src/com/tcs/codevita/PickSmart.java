package com.tcs.codevita;
import java.util.*;
import java.awt.geom.*;
import java.io.*;

public class PickSmart {
    static final double EPS = 1e-9;

    static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = round2(x);
            this.y = round2(y);
        }
        static double round2(double v) {
            return Math.round(v * 100.0) / 100.0;
        }
        static double round2raw(double v) {
            return Math.round(v * 100.0) / 100.0;
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) return false;
            Point p = (Point)o;
            return Math.abs(p.x - x) < 1e-6 && Math.abs(p.y - y) < 1e-6;
        }
        @Override
        public int hashCode() {
            return Objects.hash(round2raw(x), round2raw(y));
        }
        public String toString() { return "(" + x + "," + y + ")"; }
    }

    static class Segment {
        Point a, b;
        Segment(Point a, Point b) { this.a = a; this.b = b; }
    }

    static double cross(double ax, double ay, double bx, double by) {
        return ax * by - ay * bx;
    }

    static List<Point> segmentIntersections(Point p, Point p2, Point q, Point q2) {
        List<Point> res = new ArrayList<>();
        double r_x = p2.x - p.x;
        double r_y = p2.y - p.y;
        double s_x = q2.x - q.x;
        double s_y = q2.y - q.y;

        double denom = cross(r_x, r_y, s_x, s_y);
        double qmp_x = q.x - p.x;
        double qmp_y = q.y - p.y;

        if (Math.abs(denom) > 1e-9) {
            double t = cross(qmp_x, qmp_y, s_x, s_y) / denom;
            double u = cross(qmp_x, qmp_y, r_x, r_y) / denom;
            if (t >= -1e-9 && t <= 1 + 1e-9 && u >= -1e-9 && u <= 1 + 1e-9) {
                double ix = p.x + t * r_x;
                double iy = p.y + t * r_y;
                res.add(new Point(ix, iy));
            }
        } else {
            if (Math.abs(cross(qmp_x, qmp_y, r_x, r_y)) < 1e-9) {
                double rr2 = r_x * r_x + r_y * r_y;
                if (rr2 < 1e-12) return res;
                double t0 = ((q.x - p.x) * r_x + (q.y - p.y) * r_y) / rr2;
                double t1 = ((q2.x - p.x) * r_x + (q2.y - p.y) * r_y) / rr2;
                double ta = Math.min(t0, t1);
                double tb = Math.max(t0, t1);
                double overlapStart = Math.max(0.0, ta);
                double overlapEnd = Math.min(1.0, tb);
                if (overlapEnd + 1e-9 >= overlapStart) {
                    double sx = p.x + overlapStart * r_x;
                    double sy = p.y + overlapStart * r_y;
                    double ex = p.x + overlapEnd * r_x;
                    double ey = p.y + overlapEnd * r_y;
                    res.add(new Point(sx, sy));
                    if (Math.hypot(ex - sx, ey - sy) > 1e-6) {
                        res.add(new Point(ex, ey));
                    }
                }
            }
        }
        return res;
    }

    static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.hypot(dx, dy);
    }

    static double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Segment> sticks = new ArrayList<>();
        double totalLen = 0.0;
        for (int i = 0; i < n; i++) {
            double x1 = sc.nextDouble();
            double y1 = sc.nextDouble();
            double x2 = sc.nextDouble();
            double y2 = sc.nextDouble();
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            sticks.add(new Segment(p1, p2));
            totalLen += dist(p1, p2);
        }

        List<Set<Point>> pointsOnSegment = new ArrayList<>();
        for (int i = 0; i < n; i++) pointsOnSegment.add(new HashSet<>());
        for (int i = 0; i < n; i++) {
            pointsOnSegment.get(i).add(sticks.get(i).a);
            pointsOnSegment.get(i).add(sticks.get(i).b);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                List<Point> inters = segmentIntersections(sticks.get(i).a, sticks.get(i).b, sticks.get(j).a, sticks.get(j).b);
                for (Point p : inters) {
                    pointsOnSegment.get(i).add(p);
                    pointsOnSegment.get(j).add(p);
                }
            }
        }

        Map<Point, Integer> pointIndex = new LinkedHashMap<>();
        List<Point> indexToPoint = new ArrayList<>();
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Segment s = sticks.get(i);
            List<Point> pts = new ArrayList<>(pointsOnSegment.get(i));
            final double dx = s.b.x - s.a.x;
            final double dy = s.b.y - s.a.y;
            pts.sort((p1, p2) -> {
                double t1 = (Math.abs(dx) > Math.abs(dy)) ? ((p1.x - s.a.x) / (dx == 0 ? 1 : dx)) : ((p1.y - s.a.y) / (dy == 0 ? 1 : dy));
                double t2 = (Math.abs(dx) > Math.abs(dy)) ? ((p2.x - s.a.x) / (dx == 0 ? 1 : dx)) : ((p2.y - s.a.y) / (dy == 0 ? 1 : dy));
                return Double.compare(t1, t2);
            });
            for (int k = 0; k + 1 < pts.size(); k++) {
                Point p1 = pts.get(k);
                Point p2 = pts.get(k + 1);
                if (dist(p1, p2) < 1e-8) continue;
                if (!pointIndex.containsKey(p1)) {
                    pointIndex.put(p1, indexToPoint.size());
                    indexToPoint.add(p1);
                }
                if (!pointIndex.containsKey(p2)) {
                    pointIndex.put(p2, indexToPoint.size());
                    indexToPoint.add(p2);
                }
                int u = pointIndex.get(p1);
                int v = pointIndex.get(p2);
                adj.computeIfAbsent(u, kk -> new HashSet<>()).add(v);
                adj.computeIfAbsent(v, kk -> new HashSet<>()).add(u);
            }
        }

        if (pointIndex.size() == 0) {
            System.out.println("Abandoned");
            return;
        }

        int m = indexToPoint.size();
        int[] parent = new int[m];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[m];
        List<Integer> cycle = null;

        for (int start = 0; start < m && cycle == null; start++) {
            if (visited[start]) continue;
            Deque<Integer> stack = new ArrayDeque<>();
            Deque<Integer> iterIdx = new ArrayDeque<>();
            stack.push(start);
            iterIdx.push(-1);
            parent[start] = -1;

            while (!stack.isEmpty() && cycle == null) {
                int u = stack.peek();
                stack.pop();
                int _marker = iterIdx.pop();

                if (!visited[u]) {
                    visited[u] = true;
                    if (adj.containsKey(u)) {
                        for (int v : adj.get(u)) {
                            if (!visited[v]) {
                                parent[v] = u;
                                stack.push(v);
                                iterIdx.push(-1);
                            } else {
                                if (v != parent[u]) {
                                    List<Integer> temp = new ArrayList<>();
                                    int cur = u;
                                    temp.add(cur);
                                    while (cur != v && parent[cur] != -1) {
                                        cur = parent[cur];
                                        temp.add(cur);
                                    }
                                    if (cur == v && temp.size() >= 3) {
                                        Collections.reverse(temp);
                                        cycle = new ArrayList<>(temp);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (cycle == null || cycle.size() < 3) {
            System.out.println("Abandoned");
            return;
        }

        double polyArea = 0.0;
        double usedLength = 0.0;
        int k = cycle.size();
        for (int i = 0; i < k; i++) {
            Point p1 = indexToPoint.get(cycle.get(i));
            Point p2 = indexToPoint.get(cycle.get((i + 1) % k));
            polyArea += p1.x * p2.y - p2.x * p1.y;
            usedLength += dist(p1, p2);
        }
        polyArea = Math.abs(polyArea) / 2.0;

        double leftover = totalLen - usedLength;
        if (leftover < 0) leftover = 0;

        double computerArea = (leftover * leftover) / (4.0 * Math.PI);

        if (polyArea > computerArea + 1e-9) {
            System.out.println("Kalyan");
        } else {
            System.out.println("Computer");
        }
    }
}
