/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] tiles;
    // int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // string representation of this board
    public String toString() {
        int n = this.dimension();

        StringBuilder arrayRep = new StringBuilder();
        arrayRep.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arrayRep.append(" " + tiles[i][j]);
            }
            arrayRep.append("\n");
        }
        return arrayRep.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int n = this.dimension();
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((tiles[i][j] != 0) && (tiles[i][j] != i * n + j + 1)) {
                    res++;
                }
            }
        }
        return res;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int n = this.dimension();
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = tiles[i][j];
                if (val > 0) {
                    int valRow = Math.floorDiv(val - 1, n);
                    int valCol = (val - 1) % n;
                    res += Math.abs(valRow - i);
                    res += Math.abs(valCol - j);
                }

            }
        }
        return res;
    }

    // // is this board the goal board?
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    //
    // does this board equal y?
    public boolean equals(Object y) {
        Board yBoard = (Board) y;
        if (y == null) {
            return false;
        }
        if (this.dimension() != yBoard.dimension()) {
            return false;
        }
        else {
            int n = this.dimension();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tiles[i][j] != yBoard.tiles[i][j]) {
                        return false;
                    }

                }
            }
            return true;

        }
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int n = this.dimension();
        // find 0
        int row_0 = 0;
        int col_0 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = tiles[i][j];
                if (val == 0) {
                    row_0 = i;
                    col_0 = j;
                }
            }
        }
        List<Board> res = new ArrayList<>();

        // define result in 4 cases
        if (col_0 > 0) {
            int[][] res1 = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == row_0 && j == col_0 - 1) {
                        res1[i][j] = 0;
                    }
                    else if (i == row_0 && j == col_0) {
                        res1[i][j] = tiles[i][col_0 - 1];
                    }
                    else {
                        res1[i][j] = tiles[i][j];
                    }
                }
            }
            res.add(new Board(res1));
        }

        if (col_0 < n - 1) {
            int[][] res2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == row_0 && j == col_0) {
                        res2[i][j] = tiles[i][col_0 + 1];
                    }
                    else if (i == row_0 && j == col_0 + 1) {
                        res2[i][j] = 0;
                    }
                    else {
                        res2[i][j] = tiles[i][j];
                    }
                }
            }
            res.add(new Board(res2));
        }

        if (row_0 > 0) {
            int[][] res3 = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == row_0 - 1 && j == col_0) {
                        res3[i][j] = 0;
                    }
                    else if (i == row_0 && j == col_0) {
                        res3[i][j] = tiles[row_0 - 1][j];
                    }
                    else {
                        res3[i][j] = tiles[i][j];
                    }
                }
            }

            res.add(new Board(res3));
        }

        if (row_0 < n - 1) {
            int[][] res4 = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == row_0 && j == col_0) {
                        res4[i][j] = tiles[i + 1][col_0];
                    }
                    else if (i == row_0 + 1 && j == col_0) {
                        res4[i][j] = 0;
                    }
                    else {
                        res4[i][j] = tiles[i][j];
                    }
                }
            }
            res.add(new Board(res4));
        }


        return res;


    }

    //
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int n = tiles.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }

        if (copy[0][0] == 0) swap(copy, 0, 1, 1, 1);
        else if (copy[0][1] == 0) swap(copy, 0, 0, 1, 0);
        else swap(copy, 0, 0, 0, 1);

        Board twinBoard = new Board(copy);
        return twinBoard;
    }

    private void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }


    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] values = { { 8, 1, 2 }, { 3, 4, 9 }, { 7, 6, 0 } };
        // int[][] values_b = { { 8, 1, 3 }, { 4, 0, 2 }, { 7, 6, 5 } };
        Board x = new Board(values);
        // Board y = new Board(values_b);
        // System.out.println(x);
        // System.out.println(x.hamming());
        // System.out.println(x.manhattan());
        // System.out.println(x.equals(y));
        for (Board t :
                x.neighbors()) {
            System.out.println(t);
        }
    }


}
