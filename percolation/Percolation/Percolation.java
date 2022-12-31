/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private WeightedQuickUnionUF connect_uf;
    private int[] open_arr;
    private int open_sum;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        this.open_sum = 0;
        this.connect_uf = new WeightedQuickUnionUF(n * n + 2);
        this.open_arr = new int[n * n + 2];
        this.open_arr[0] = 1;
        this.open_arr[n * n + 1] = 1;
        for (int i = 1; i <= n * n; i++) {
            this.open_arr[i] = 0;
        }

        // connect top spot and bottom spot to first row and last row
        for (int i = 1; i <= n; i++) {
            this.connect_uf.union(0, i);
            this.connect_uf.union(n * n + 1, n * n + 1 - i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // get neighbors in 4 directions
        int indCurr = (row - 1) * n + col;
        int indUp = (row - 2) * n + col;
        int indDown = row * n + col;
        int indLeft = (row - 1) * n + col - 1;
        int indRight = (row - 1) * n + col + 1;

        // update open_arr
        this.open_arr[indCurr] = 1;

        // update open_sum
        this.open_sum += 1;

        // update connect_uf if needed
        if (indUp > 0 && this.open_arr[indUp] == 1) {
            this.connect_uf.union(indUp, indCurr);
        }
        if (indDown <= n * n && this.open_arr[indDown] == 1) {
            this.connect_uf.union(indDown, indCurr);
        }
        if (col > 1 && this.open_arr[indLeft] == 1) {
            this.connect_uf.union(indLeft, indCurr);
        }
        if (col < n && this.open_arr[indRight] == 1) {
            this.connect_uf.union(indRight, indCurr);
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.open_arr[(row - 1) * n + col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return this.isOpen(row, col) && (this.connect_uf.find(0) == this.connect_uf.find(
                (row - 1) * n + col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.open_sum;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.connect_uf.find(0) == this.connect_uf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1, 2);
        p.open(2, 1);

        System.out.println(p.connect_uf.find(0));
        System.out.println(p.connect_uf.find(1));
        System.out.println(p.connect_uf.find(2));
        System.out.println(p.connect_uf.find(3));
        System.out.println(p.connect_uf.find(4));
        System.out.println(p.connect_uf.find(5));
        // p.open(2, 2);
        // p.open(3, 2);
        // p.open(4, 1);
        System.out.println(p.percolates());

        // boolean res = test.isFull(3, 2);
        // System.out.println(res);
    }
}
