package com.tcs.roundii;
import java.io.*;
import java.util.*;

public class ASCII {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        List<HouseSpec> specs = parseInput(line.trim());
        List<char[][]> canvases = new ArrayList<>();
        List<Integer> baseRows = new ArrayList<>();   // base row index inside each canvas
        List<Integer> widths = new ArrayList<>();

        for (HouseSpec s : specs) {
            RenderedHouse rh = renderHouse(s);
            canvases.add(rh.canvas);
            baseRows.add(rh.baseRow);
            widths.add(rh.canvas[0].length);
        }

        char[][] global = mergeCanvases(canvases, baseRows);
        printCanvas(global);
    }

    // --- Parsing ---
    static class HouseSpec {
        int length, height;
        char dir;
        HouseSpec(int l, int h, char d) { length = l; height = h; dir = d; }
    }

    static List<HouseSpec> parseInput(String line) {
        List<HouseSpec> list = new ArrayList<>();
        String[] parts = line.split("\\s+");
        for (String p : parts) {
            // pattern like 6x3U
            p = p.trim();
            if (p.isEmpty()) continue;
            int xIdx = p.indexOf('x');
            if (xIdx < 0 || p.length() < xIdx + 3) continue;
            String lenStr = p.substring(0, xIdx);
            int i = xIdx + 1;
            // read digits until non-digit
            int j = i;
            while (j < p.length() && Character.isDigit(p.charAt(j))) j++;
            String hStr = p.substring(i, j);
            char d = p.charAt(j);
            int len = Integer.parseInt(lenStr);
            int hei = Integer.parseInt(hStr);
            list.add(new HouseSpec(len, hei, d));
        }
        return list;
    }

    // --- RenderedHouse container ---
    static class RenderedHouse {
        char[][] canvas;
        int baseRow; // index of base row inside canvas (0-based)
        RenderedHouse(char[][] c, int b) { canvas = c; baseRow = b; }
    }

    // --- House rendering (uses U as base, transforms for others) ---
    static RenderedHouse renderHouse(HouseSpec s) {
        int length = s.length;
        int height = s.height;
        char dir = s.dir;

        int roofH = (dir == 'H') ? 1 : (int) Math.ceil(length / 2.0);
        // base U-style house
        RenderedHouse up = renderUp(length, height, roofH);

        if (dir == 'U' || dir == 'H') {
            return up;
        } else if (dir == 'L') {
            return new RenderedHouse(mirrorHorizontal(up.canvas), up.baseRow);
        } else if (dir == 'R') {
            // right tilt - for our convention, also horizontal mirror of U
            return new RenderedHouse(mirrorHorizontal(up.canvas), up.baseRow);
        } else { // 'D'
            // vertical flip so roof appears below base
            char[][] flipped = mirrorVertical(up.canvas);
            // after vertical flip the base row index changes:
            int newBase = flipped.length - 1 - up.baseRow;
            return new RenderedHouse(flipped, newBase);
        }
    }

    // Construct an "U" house with base at bottom of the returned canvas
    static RenderedHouse renderUp(int length, int height, int roofH) {
        int width = length + 2 * roofH;
        int rows = roofH + height + 1;
        char[][] canvas = new char[rows][width];
        for (int r = 0; r < rows; r++) Arrays.fill(canvas[r], ' ');

        // Roof - slanted symmetric: '/' on left side, '\' on right side
        for (int r = 0; r < roofH; r++) {
            int leftCol = r;
            int rightCol = width - 1 - r;
            canvas[r][leftCol] = '/';
            canvas[r][rightCol] = '\\';
        }

        int wallLeft = roofH;
        int wallRight = roofH + length - 1;

        // Walls (left '@', right '&') occupy the rows between roof and base
        for (int w = 0; w < height; w++) {
            int row = roofH + w;
            canvas[row][wallLeft] = '@';
            canvas[row][wallRight] = '&';
        }

        // Base row
        int baseRow = roofH + height;
        for (int c = wallLeft; c <= wallRight; c++) {
            canvas[baseRow][c] = '#';
        }

        return new RenderedHouse(canvas, baseRow);
    }

    // --- transformations ---
    static char[][] mirrorHorizontal(char[][] c) {
        int rows = c.length;
        int cols = c[0].length;
        char[][] out = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int cc = 0; cc < cols; cc++) {
                char ch = c[r][cols - 1 - cc];
                // when mirroring, swap slash directions to keep visual sense
                if (ch == '/') ch = '\\';
                else if (ch == '\\') ch = '/';
                out[r][cc] = ch;
            }
        }
        return out;
    }

    static char[][] mirrorVertical(char[][] c) {
        int rows = c.length;
        int cols = c[0].length;
        char[][] out = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int cc = 0; cc < cols; cc++) {
                char ch = c[rows - 1 - r][cc];
                // when flipping vertically, keep slashes but it may change their "directional" meaning;
                // here we keep characters as-is for a simple vertical flip.
                out[r][cc] = ch;
            }
        }
        return out;
    }

    // --- merging canvases so their bases align on global baseline ---
    static char[][] mergeCanvases(List<char[][]> canvases, List<Integer> baseRows) {
        int n = canvases.size();
        int maxAbove = 0, maxBelow = 0;
        int totalWidth = 0;
        for (int i = 0; i < n; i++) {
            char[][] c = canvases.get(i);
            int rows = c.length;
            int base = baseRows.get(i);
            int above = base; // rows above base (0..base-1)
            int below = rows - base - 1;
            if (above > maxAbove) maxAbove = above;
            if (below > maxBelow) maxBelow = below;
            totalWidth += c[0].length;
        }
        int globalRows = maxAbove + 1 + maxBelow;
        char[][] global = new char[globalRows][totalWidth];
        for (int r = 0; r < globalRows; r++) Arrays.fill(global[r], ' ');

        int curCol = 0;
        int baseline = maxAbove; // global row index of base
        for (int i = 0; i < n; i++) {
            char[][] c = canvases.get(i);
            int base = baseRows.get(i);
            int rows = c.length;
            int cols = c[0].length;
            // top row in global to place c[0] is:
            int topRow = baseline - base;
            for (int r = 0; r < rows; r++) {
                int gr = topRow + r;
                for (int cc = 0; cc < cols; cc++) {
                    char ch = c[r][cc];
                    if (ch != ' ') {
                        global[gr][curCol + cc] = ch;
                    }
                }
            }
            curCol += cols;
        }
        return global;
    }

    static void printCanvas(char[][] canvas) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : canvas) {
            // trim trailing spaces
            int end = row.length - 1;
            while (end >= 0 && row[end] == ' ') end--;
            for (int i = 0; i <= end; i++) sb.append(row[i]);
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
