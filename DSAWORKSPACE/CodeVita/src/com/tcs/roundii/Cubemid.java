package com.tcs.roundii;
import java.io.*;
import java.util.*;


import java.util.*;
import java.io.*;

public class Cubemid {

    static int S;
    static char[][][] grid;

    // Movements for D cells (same layer)
    static final int[][] FLAT_DIRS = {
        {0, 1, 0},  // down row
        {0, -1, 0}, // up row
        {0, 0, 1},  // right col
        {0, 0, -1}  // left col
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine().trim());
        grid = new char[S][S][S];

        // Read cube
        for (int layer = 0; layer < S; layer++) {
            for (int row = 0; row < S; row++) {
                String line = br.readLine().trim();
                for (int col = 0; col < S; col++) {
                    grid[layer][row][col] = line.charAt(col);
                }
            }
        }

        // Start
        String[] s = br.readLine().split(" ");
        int sl = Integer.parseInt(s[0]);
        int sr = Integer.parseInt(s[1]);
        int sc = Integer.parseInt(s[2]);

        // Goal
        String[] g = br.readLine().split(" ");
        int gl = Integer.parseInt(g[0]);
        int gr = Integer.parseInt(g[1]);
        int gc = Integer.parseInt(g[2]);

        System.out.println(bfs(sl, sr, sc, gl, gr, gc));
    }

    static int bfs(int sl, int sr, int sc, int gl, int gr, int gc) {
        boolean[][][] vis = new boolean[S][S][S];
        Queue<int[]> q = new LinkedList<>();

        if (!walkable(grid[sl][sr][sc])) return -1;

        q.add(new int[]{sl, sr, sc, 0});
        vis[sl][sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int l = cur[0], r = cur[1], c = cur[2], d = cur[3];

            if (l == gl && r == gr && c == gc) return d;

            char ch = grid[l][r][c];

            // D: flat floor movement
            if (ch == 'D') {
                for (int[] mv : FLAT_DIRS) {
                    int nl = l, nr = r + mv[1], nc = c + mv[2];
                    if (valid(nl, nr, nc) && walkable(grid[nl][nr][nc]) && !vis[nl][nr][nc]) {
                        vis[nl][nr][nc] = true;
                        q.add(new int[]{nl, nr, nc, d + 1});
                    }
                }
            }

            // R: up & right
            if (ch == 'R') {
                int nl = l - 1, nr = r, nc = c + 1;
                if (valid(nl, nr, nc) && walkable(grid[nl][nr][nc]) && !vis[nl][nr][nc]) {
                    vis[nl][nr][nc] = true;
                    q.add(new int[]{nl, nr, nc, d + 1});
                }
            }

            // L: up & left
            if (ch == 'L') {
                int nl = l - 1, nr = r, nc = c - 1;
                if (valid(nl, nr, nc) && walkable(grid[nl][nr][nc]) && !vis[nl][nr][nc]) {
                    vis[nl][nr][nc] = true;
                    q.add(new int[]{nl, nr, nc, d + 1});
                }
            }

            // F: slope down & forward (diagonal)
            if (ch == 'F') {
                int nl = l + 1, nr = r + 1, nc = c;
                if (valid(nl, nr, nc) && walkable(grid[nl][nr][nc]) && !vis[nl][nr][nc]) {
                    vis[nl][nr][nc] = true;
                    q.add(new int[]{nl, nr, nc, d + 1});
                }
            }

            // B: slope up & backward (diagonal)
            if (ch == 'B') {
                int nl = l - 1, nr = r - 1, nc = c;
                if (valid(nl, nr, nc) && walkable(grid[nl][nr][nc]) && !vis[nl][nr][nc]) {
                    vis[nl][nr][nc] = true;
                    q.add(new int[]{nl, nr, nc, d + 1});
                }
            }
        }

        return -1;
    }

    static boolean walkable(char c) {
        return c != 'E'; // All except E are walkable
    }

    static boolean valid(int l, int r, int c) {
        return l >= 0 && l < S && r >= 0 && r < S && c >= 0 && c < S;
    }
}
