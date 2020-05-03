/******************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 *****************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean grid[][];
    private int numberOfOpenSites;
    private WeightedQuickUnionUF unionUF;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        this.n = n;
        this.grid = new boolean[n][n];
        this.unionUF = new WeightedQuickUnionUF(n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            openSite(row, col);
            numberOfOpenSites++;
        }
    }

    private void openSite(int row, int col) {
        int idX = convertToIdx(row, col);

        if (0 != col && grid[row][col - 1])
            unionUF.union(idX, idX - 1);

        if (n - 1 != col && grid[row][col + 1])
            unionUF.union(idX, idX + 1);

        if (0 != row && grid[row - 1][col])
            unionUF.union(idX, idX - n);

        if (n - 1 != row && grid[row + 1][col])
            unionUF.union(idX, idX + n);
    }

    private int convertToIdx(int row, int col) {
        return row * n + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return !grid[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        // For each element in op row check untill found or exited
        // that whether or not it is connected with
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int p = convertToIdx(1, i);
                int q = convertToIdx(n - 1, j);

                if (unionUF.connected(p, q)) {
                    return true;
                }
            }
        }

        return false;

    }

    // test client (optional)
    public static void main(String[] args) {

        Percolation perc = new Percolation(4);
        StdOut.println("No sites open. Does your system percolates? "
                               + (perc.percolates() ? "Yes" : "No"));
        StdOut.println("Is 1,1 full " + perc.isFull(1, 1));
        perc.open(1, 1);

        StdOut.println("Is 1,1 full  now? " + perc.isFull(1, 1));

        StdOut.println("Single site open. Does your system percolates? "
                               + (perc.percolates() ? "Yes" : "No"));
        perc.open(0, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(3, 3);
        StdOut.println("One more sites open. Total 4/16. Does your system percolates? "
                               + (perc.percolates() ? "Yes" : "No"));

    }

}
