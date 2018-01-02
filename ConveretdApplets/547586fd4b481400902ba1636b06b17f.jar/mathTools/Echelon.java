// 
// Decompiled by Procyon v0.5.30
// 

package mathTools;

public class Echelon
{
    private double[][] A;
    private int[] P;
    private int m;
    private int n;
    
    public Echelon(final double[][] array) {
        this.m = array.length;
        this.n = array[0].length;
        this.A = new double[this.m][this.n];
        this.P = new int[this.n - 1];
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < this.n; ++j) {
                this.A[i][j] = array[i][j];
            }
        }
        for (int k = 0; k < this.n - 1; ++k) {
            this.P[k] = k;
        }
        this.reduce();
    }
    
    public int rank() {
        int min;
        for (min = Math.min(this.m - 1, this.n - 2); min >= 0 && this.A[min][min] == 0.0; --min) {}
        return 1 + min;
    }
    
    public double value(final int n, final int n2) {
        return this.A[n][n2];
    }
    
    public int[] perm() {
        final int[] array = new int[this.n - 1];
        for (int i = 0; i < this.n - 1; ++i) {
            array[i] = this.P[i];
        }
        return array;
    }
    
    public double soln(final int n) {
        int n2;
        for (n2 = 0; n2 < this.n - 1 && this.P[n2] != n; ++n2) {}
        if (n2 == this.n - 1) {
            return Double.NaN;
        }
        if (n2 < this.rank()) {
            return this.A[n2][this.n - 1] / this.A[n2][n2];
        }
        return Double.NaN;
    }
    
    private void switchRows(final int n, final int n2) {
        for (int i = 0; i < this.n; ++i) {
            final double n3 = this.A[n][i];
            this.A[n][i] = this.A[n2][i];
            this.A[n2][i] = n3;
        }
    }
    
    private void switchCols(final int n, final int n2) {
        for (int i = 0; i < this.m; ++i) {
            final double n3 = this.A[i][n];
            this.A[i][n] = this.A[i][n2];
            this.A[i][n2] = n3;
        }
        final int n4 = this.P[n];
        this.P[n] = this.P[n2];
        this.P[n2] = n4;
    }
    
    private void addRow(final int n, final int n2, final double n3) {
        for (int i = 0; i < this.n; ++i) {
            final double[] array = this.A[n2];
            final int n4 = i;
            array[n4] += n3 * this.A[n][i];
        }
    }
    
    private void getPivot(final int n, final int n2) {
        int n3 = n;
        int n4 = n2;
        double n5 = Math.abs(this.A[n][n2]);
        for (int i = n; i < this.m; ++i) {
            for (int j = n2; j < this.n - 1; ++j) {
                if (Math.abs(this.A[i][j]) > n5) {
                    n5 = Math.abs(this.A[i][j]);
                    n3 = i;
                    n4 = j;
                }
            }
        }
        this.switchRows(n, n3);
        this.switchCols(n2, n4);
        if (Math.abs(this.A[n][n2]) < 1.0E-12) {
            for (int k = n; k < this.m; ++k) {
                for (int l = n2; l < this.n - 1; ++l) {
                    this.A[k][l] = 0.0;
                }
            }
        }
    }
    
    private void reduce() {
        for (int i = 0; i < Math.min(this.m, this.n - 1); ++i) {
            this.getPivot(i, i);
            if (this.A[i][i] == 0.0) {
                break;
            }
            for (int j = 0; j < this.m; ++j) {
                if (j != i) {
                    this.addRow(i, j, -this.A[j][i] / this.A[i][i]);
                    this.A[j][i] = 0.0;
                }
            }
        }
    }
}
