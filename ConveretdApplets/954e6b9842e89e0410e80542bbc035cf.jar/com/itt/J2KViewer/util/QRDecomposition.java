// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.Serializable;

public class QRDecomposition implements Serializable
{
    private static final long serialVersionUID = 1L;
    private double[][] QR;
    private int m;
    private int n;
    private double[] Rdiag;
    
    public QRDecomposition(final Matrix matrix) {
        this.QR = matrix.getArrayCopy();
        this.m = matrix.getRowDimension();
        this.n = matrix.getColumnDimension();
        this.Rdiag = new double[this.n];
        for (int i = 0; i < this.n; ++i) {
            double sqrt = 0.0;
            for (int j = i; j < this.m; ++j) {
                System.out.println("nrm = " + sqrt);
                System.out.println("QR[i][k] = " + this.QR[j][i]);
                sqrt = Math.sqrt(Math.pow(sqrt, 2.0) + Math.pow(this.QR[j][i], 2.0));
            }
            if (sqrt != 0.0) {
                if (this.QR[i][i] < 0.0) {
                    sqrt = -sqrt;
                }
                for (int k = i; k < this.m; ++k) {
                    final double[] array = this.QR[k];
                    final int n = i;
                    array[n] /= sqrt;
                }
                final double[] array2 = this.QR[i];
                final int n2 = i;
                ++array2[n2];
                for (int l = i + 1; l < this.n; ++l) {
                    double n3 = 0.0;
                    for (int n4 = i; n4 < this.m; ++n4) {
                        n3 += this.QR[n4][i] * this.QR[n4][l];
                    }
                    final double n5 = -n3 / this.QR[i][i];
                    for (int n6 = i; n6 < this.m; ++n6) {
                        final double[] array3 = this.QR[n6];
                        final int n7 = l;
                        array3[n7] += n5 * this.QR[n6][i];
                    }
                }
            }
            this.Rdiag[i] = -sqrt;
        }
    }
    
    public boolean isFullRank() {
        for (int i = 0; i < this.n; ++i) {
            if (this.Rdiag[i] == 0.0) {
                return false;
            }
        }
        return true;
    }
    
    public Matrix getH() {
        final Matrix matrix = new Matrix(this.m, this.n);
        final double[][] array = matrix.getArray();
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < this.n; ++j) {
                if (i >= j) {
                    array[i][j] = this.QR[i][j];
                }
                else {
                    array[i][j] = 0.0;
                }
            }
        }
        return matrix;
    }
    
    public Matrix getR() {
        final Matrix matrix = new Matrix(this.n, this.n);
        final double[][] array = matrix.getArray();
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                if (i < j) {
                    array[i][j] = this.QR[i][j];
                }
                else if (i == j) {
                    array[i][j] = this.Rdiag[i];
                }
                else {
                    array[i][j] = 0.0;
                }
            }
        }
        return matrix;
    }
    
    public Matrix getQ() {
        final Matrix matrix = new Matrix(this.m, this.n);
        final double[][] array = matrix.getArray();
        for (int i = this.n - 1; i >= 0; --i) {
            for (int j = 0; j < this.m; ++j) {
                array[j][i] = 0.0;
            }
            array[i][i] = 1.0;
            for (int k = i; k < this.n; ++k) {
                if (this.QR[i][i] != 0.0) {
                    double n = 0.0;
                    for (int l = i; l < this.m; ++l) {
                        n += this.QR[l][i] * array[l][k];
                    }
                    final double n2 = -n / this.QR[i][i];
                    for (int n3 = i; n3 < this.m; ++n3) {
                        final double[] array2 = array[n3];
                        final int n4 = k;
                        array2[n4] += n2 * this.QR[n3][i];
                    }
                }
            }
        }
        return matrix;
    }
    
    public Matrix solve(final Matrix matrix) {
        if (matrix.getRowDimension() != this.m) {
            throw new IllegalArgumentException("Matrix row dimensions must agree.");
        }
        if (!this.isFullRank()) {
            throw new RuntimeException("Matrix is rank deficient.");
        }
        final int columnDimension = matrix.getColumnDimension();
        final double[][] arrayCopy = matrix.getArrayCopy();
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < columnDimension; ++j) {
                double n = 0.0;
                for (int k = i; k < this.m; ++k) {
                    n += this.QR[k][i] * arrayCopy[k][j];
                }
                final double n2 = -n / this.QR[i][i];
                for (int l = i; l < this.m; ++l) {
                    final double[] array = arrayCopy[l];
                    final int n3 = j;
                    array[n3] += n2 * this.QR[l][i];
                }
            }
        }
        for (int n4 = this.n - 1; n4 >= 0; --n4) {
            for (int n5 = 0; n5 < columnDimension; ++n5) {
                final double[] array2 = arrayCopy[n4];
                final int n6 = n5;
                array2[n6] /= this.Rdiag[n4];
            }
            for (int n7 = 0; n7 < n4; ++n7) {
                for (int n8 = 0; n8 < columnDimension; ++n8) {
                    final double[] array3 = arrayCopy[n7];
                    final int n9 = n8;
                    array3[n9] -= arrayCopy[n4][n8] * this.QR[n7][n4];
                }
            }
        }
        return new Matrix(arrayCopy, this.n, columnDimension).getMatrix(0, this.n - 1, 0, columnDimension - 1);
    }
}
