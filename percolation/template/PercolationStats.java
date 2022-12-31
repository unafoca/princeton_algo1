/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thres_perc_arr;
    private int n_trials;
    private int n;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n_trials = trials;
        this.n = n;
        // this.sum_thres = 0;
        this.thres_perc_arr = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int cnt_perc = 0;

            while (!p.percolates()) {
                int rand_row = StdRandom.uniformInt(this.n) + 1;
                int rand_col = StdRandom.uniformInt(this.n) + 1;
                int rand = (rand_row - 1) * this.n + rand_col;
                while (p.isOpen(rand_row, rand_col)) {
                    rand_row = StdRandom.uniformInt(this.n) + 1;
                    rand_col = StdRandom.uniformInt(this.n) + 1;
                    rand = (rand_row - 1) * this.n + rand_col;
                }
                p.open(rand_row, rand_col);
                cnt_perc += 1;

            }
            this.thres_perc_arr[i] = (double) cnt_perc / (this.n * this.n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.thres_perc_arr);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.thres_perc_arr);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.n_trials);

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.n_trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats perc_test = new PercolationStats(Integer.parseInt(args[0]),
                                                          Integer.parseInt(args[1]));
        // System.out.println(Arrays.toString(perc_test.thres_perc_arr));
        System.out.println("mean                    = " + perc_test.mean());
        System.out.println("stddev                  = " + perc_test.stddev());
        System.out.println("95% confidence interval = [" + perc_test.confidenceLo() + ", "
                                   + perc_test.confidenceHi() + "]");


    }

}




