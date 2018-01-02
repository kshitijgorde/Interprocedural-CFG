// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb.comp;

import java.math.BigDecimal;

public class MandelbrotTask implements Runnable
{
    private final BigDecimal xmin;
    private final BigDecimal xmax;
    private final BigDecimal yval;
    private final int columnCount;
    private final int rowNumber;
    private final int maxIterations;
    private final boolean highPrecision;
    private int jobNumber;
    private volatile boolean done;
    private int[] iterationCounts;
    private int[][] xs;
    private int[] y;
    private int[] work1;
    private int[] work2;
    private int[] work3;
    private int[] zx;
    private int[] zy;
    private int digits;
    private int chunks;
    private BigDecimal twoTo16;
    private static final double log2of10;
    
    public MandelbrotTask(final int rowNumber, final BigDecimal xmin, final BigDecimal xmax, final BigDecimal yval, final int columnCount, final int maxIterations, final boolean highPrecision) {
        this.twoTo16 = new BigDecimal(65536);
        this.xmin = xmin;
        this.xmax = xmax;
        this.yval = yval;
        this.columnCount = columnCount;
        this.rowNumber = rowNumber;
        this.maxIterations = maxIterations;
        this.highPrecision = highPrecision;
    }
    
    public BigDecimal getXmin() {
        return this.xmin;
    }
    
    public BigDecimal getXmax() {
        return this.xmax;
    }
    
    public BigDecimal getYval() {
        return this.yval;
    }
    
    public int getColumnCount() {
        return this.columnCount;
    }
    
    public int getMaxIterations() {
        return this.maxIterations;
    }
    
    public boolean isHighPrecision() {
        return this.highPrecision;
    }
    
    public int getJobNumber() {
        return this.jobNumber;
    }
    
    public void setJobNumber(final int jobNumber) {
        this.jobNumber = jobNumber;
    }
    
    public int getRowNumber() {
        return this.rowNumber;
    }
    
    public int[] getResults() {
        return this.iterationCounts;
    }
    
    void setResults(final int[] iterationCounts) {
        this.iterationCounts = iterationCounts;
    }
    
    void makeDone() {
        this.done = true;
    }
    
    boolean isDone() {
        return this.done;
    }
    
    public void run() {
        this.iterationCounts = new int[this.columnCount];
        if (this.highPrecision) {
            this.digits = this.xmin.scale();
            this.createHPData();
            for (int i = 0; i < this.columnCount; ++i) {
                this.iterationCounts[i] = this.countIterations(this.xs[i], this.y);
            }
        }
        else {
            final double doubleValue = this.xmin.doubleValue();
            final double doubleValue2 = this.xmax.doubleValue();
            final double doubleValue3 = this.yval.doubleValue();
            final double n = (doubleValue2 - doubleValue) / (this.columnCount - 1);
            for (int j = 0; j < this.columnCount; ++j) {
                this.iterationCounts[j] = this.countIterations(doubleValue + n * j, doubleValue3);
            }
        }
    }
    
    private int countIterations(final double n, final double n2) {
        int n3 = 0;
        double n6;
        for (double n4 = n, n5 = n2; n3 < this.maxIterations && n4 * n4 + n5 * n5 < 8.0; n5 = 2.0 * n4 * n5 + n2, n4 = n6, ++n3) {
            n6 = n4 * n4 - n5 * n5 + n;
        }
        return n3;
    }
    
    private int countIterations(final int[] array, final int[] array2) {
        System.arraycopy(array, 0, this.zx, 0, this.chunks);
        System.arraycopy(array2, 0, this.zy, 0, this.chunks);
        int i;
        for (i = 0; i < this.maxIterations; ++i) {
            System.arraycopy(this.zx, 0, this.work2, 0, this.chunks);
            this.multiply(this.work2, this.zx, this.chunks);
            System.arraycopy(this.zy, 0, this.work1, 0, this.chunks);
            this.multiply(this.work1, this.zy, this.chunks);
            System.arraycopy(this.work1, 0, this.work3, 0, this.chunks);
            this.add(this.work1, this.work2, this.chunks);
            if ((this.work1[0] & 0xFFF8) != 0x0 && (this.work1[0] & 0xFFF8) != 0xFFF0) {
                break;
            }
            this.negate(this.work3, this.chunks);
            this.add(this.work2, this.work3, this.chunks);
            this.add(this.work2, array, this.chunks);
            System.arraycopy(this.zx, 0, this.work1, 0, this.chunks);
            this.add(this.work1, this.zx, this.chunks);
            this.multiply(this.work1, this.zy, this.chunks);
            this.add(this.work1, array2, this.chunks);
            System.arraycopy(this.work1, 0, this.zy, 0, this.chunks);
            System.arraycopy(this.work2, 0, this.zx, 0, this.chunks);
        }
        return i;
    }
    
    private void createHPData() {
        this.chunks = (int)(this.digits * MandelbrotTask.log2of10) / 16 + 2;
        this.convert(this.y = new int[this.chunks + 1], this.yval, this.chunks + 1);
        this.xs = new int[this.columnCount][];
        this.convert(this.xs[0] = new int[this.chunks + 1], this.xmin, this.chunks + 1);
        if (this.columnCount > 1) {
            final int[] array = new int[this.chunks + 1];
            this.convert(array, this.xmax.subtract(this.xmin).setScale(2 * this.xmax.scale()).divide(new BigDecimal(this.columnCount - 1), 6), this.chunks + 1);
            for (int i = 1; i < this.columnCount; ++i) {
                this.add(this.xs[i] = this.xs[i - 1].clone(), array, this.chunks + 1);
            }
        }
        this.zx = new int[this.chunks];
        this.zy = new int[this.chunks];
        this.work1 = new int[this.chunks];
        this.work2 = new int[this.chunks];
        this.work3 = new int[this.chunks];
    }
    
    private void convert(final int[] array, BigDecimal bigDecimal, final int n) {
        boolean b = false;
        if (bigDecimal.signum() == -1) {
            b = true;
            bigDecimal = bigDecimal.negate();
        }
        array[0] = bigDecimal.intValue();
        for (int i = 1; i < n; ++i) {
            bigDecimal = bigDecimal.subtract(new BigDecimal(array[i - 1]));
            bigDecimal = bigDecimal.multiply(this.twoTo16);
            array[i] = bigDecimal.intValue();
        }
        if (b) {
            this.negate(array, n);
        }
    }
    
    private void add(final int[] array, final int[] array2, final int n) {
        int n2 = 0;
        for (int i = n - 1; i >= 0; --i) {
            final int n3 = i;
            array[n3] += array2[i];
            final int n4 = i;
            array[n4] += n2;
            n2 = array[i] >>> 16;
            final int n5 = i;
            array[n5] &= 0xFFFF;
        }
    }
    
    private void multiply(final int[] array, final int[] array2, final int n) {
        final boolean b = (array[0] & 0x8000) != 0x0;
        if (b) {
            this.negate(array, n);
        }
        final boolean b2 = (array2[0] & 0x8000) != 0x0;
        if (b2) {
            this.negate(array2, n);
        }
        if (array[0] == 0) {
            for (int i = 0; i < n; ++i) {
                this.work3[i] = 0;
            }
        }
        else {
            int n2 = 0;
            for (int j = n - 1; j >= 0; --j) {
                this.work3[j] = array[0] * array2[j] + n2;
                n2 = this.work3[j] >>> 16;
                final int[] work3 = this.work3;
                final int n3 = j;
                work3[n3] &= 0xFFFF;
            }
        }
        for (int k = 1; k < n; ++k) {
            int l = n - k;
            int n4 = array[k] * array2[l] >>> 16;
            --l;
            int n5;
            for (n5 = n - 1; l >= 0; --l, --n5) {
                final int[] work4 = this.work3;
                final int n6 = n5;
                work4[n6] += array[k] * array2[l] + n4;
                n4 = this.work3[n5] >>> 16;
                final int[] work5 = this.work3;
                final int n7 = n5;
                work5[n7] &= 0xFFFF;
            }
            while (n4 != 0 && n5 >= 0) {
                final int[] work6 = this.work3;
                final int n8 = n5;
                work6[n8] += n4;
                n4 = this.work3[n5] >>> 16;
                final int[] work7 = this.work3;
                final int n9 = n5;
                work7[n9] &= 0xFFFF;
                --n5;
            }
        }
        System.arraycopy(this.work3, 0, array, 0, n);
        if (b2) {
            this.negate(array2, n);
        }
        if (b != b2) {
            this.negate(array, n);
        }
    }
    
    private void negate(final int[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            array[i] = 65535 - array[i];
        }
        final int n2 = n - 1;
        ++array[n2];
        for (int n3 = n - 1; n3 > 0 && (array[n3] & 0x10000) != 0x0; --n3) {
            final int n4 = n3;
            array[n4] &= 0xFFFF;
            final int n5 = n3 - 1;
            ++array[n5];
        }
        final int n6 = 0;
        array[n6] &= 0xFFFF;
    }
    
    static {
        log2of10 = Math.log(10.0) / Math.log(2.0);
    }
}
