// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import edu.wise.utils.FormatUtils;

public class DataToScreen
{
    private int[] x;
    private int[] y;
    private int[] YbarArr;
    private int[] YpredArr;
    public int width;
    public int height;
    private boolean fixedRange;
    public final boolean DEBUG = false;
    private Plot plot;
    
    public DataToScreen(final int width, final int height, final Plot plot) {
        this.width = width;
        this.height = height;
        this.plot = plot;
        this.fixedRange = true;
        plot.setMinMax(plot.xmin, plot.xmax, plot.ymin, plot.ymax);
        this.update(false);
    }
    
    public void update(final boolean b) {
        this.screenMinMax(b);
        this.x = this.toScreenCoordinates(this.plot.getXArr(), true);
        this.y = this.toScreenCoordinates(this.plot.getYArr(), false);
    }
    
    public void screenMinMax() {
    }
    
    public void screenMinMax(final boolean b) {
        this.screenMinMax();
    }
    
    protected double[] convertToActual(int n, int n2) {
        final double n3 = this.width - this.plot.leftOffset - this.plot.rightOffset;
        final double n4 = this.height - this.plot.topOffset - this.plot.bottomOffset;
        n -= this.plot.leftOffset;
        n2 -= this.plot.topOffset;
        final double n5 = n;
        final double n6 = n2;
        double n7 = n5 / n3;
        double n8 = (n4 - n6) / n4;
        if (n7 < this.plot.xmin) {
            n7 = this.plot.xmin;
        }
        if (n7 > this.plot.xmax) {
            n7 = this.plot.xmax;
        }
        if (n8 < this.plot.ymin) {
            n8 = this.plot.ymin;
        }
        if (n8 > this.plot.ymax) {
            n8 = this.plot.ymax;
        }
        return new double[] { this.plot.xmin + this.plot.xrange * n7, this.plot.ymin + this.plot.yrange * n8 };
    }
    
    private double[] convertToPercent(final double[] array, final boolean b) {
        final double[] array2 = new double[array.length];
        double n;
        double n2;
        if (b) {
            n = this.plot.xmin;
            n2 = this.plot.xrange;
        }
        else {
            n = this.plot.ymin;
            n2 = this.plot.yrange;
        }
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] - n) / n2;
        }
        return array2;
    }
    
    public double toScreenCoordinates(final double n, final boolean b) {
        return this.toScreenCoordinates(new double[] { n }, b)[0];
    }
    
    public int[] toScreenCoordinates(final double[] array, final boolean b) {
        final double[] array2 = new double[array.length];
        final int[] array3 = new int[array2.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        for (int i = 0; i < array.length; ++i) {
            if (b) {
                if (array2[i] < this.plot.xmin) {
                    array2[i] = this.plot.xmin;
                }
                if (array2[i] > this.plot.xmax) {
                    array2[i] = this.plot.xmax;
                }
            }
            else {
                if (array2[i] < this.plot.ymin) {
                    array2[i] = this.plot.ymin;
                }
                if (array2[i] > this.plot.ymax) {
                    array2[i] = this.plot.ymax;
                }
            }
        }
        final double[] convertToPercent = this.convertToPercent(array2, b);
        for (int j = 0; j < array.length; ++j) {
            if (b) {
                array3[j] = this.plot.leftOffset + (int)Math.round(convertToPercent[j] * (this.width - this.plot.leftOffset - this.plot.rightOffset));
            }
            else {
                array3[j] = this.height - this.plot.bottomOffset - (int)Math.round(convertToPercent[j] * (this.height - this.plot.bottomOffset - this.plot.topOffset));
            }
        }
        return array3;
    }
    
    public String[] getLabels(final int n, final boolean b) {
        return this.getLabels(n, 1, b);
    }
    
    public String[] getLabels(final int n, final int n2, final boolean b) {
        final String[] array = new String[n];
        double n3;
        double n4;
        if (b) {
            n3 = this.plot.getXRange();
            n4 = this.plot.getXmin();
        }
        else {
            n3 = this.plot.getYRange();
            n4 = this.plot.getYmin();
        }
        final double n5 = n3 / (n - 1);
        for (int i = 0; i < array.length; ++i) {
            array[i] = FormatUtils.rounder_str(n4 + n5 * i, n2, true);
        }
        return array;
    }
    
    public int[] mirrorArray(final int[] array) {
        final int[] array2 = new int[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i];
            array2[array2.length - 1 - i] = array[i];
        }
        return array2;
    }
    
    public void fixedRange(final boolean fixedRange) {
        this.fixedRange = fixedRange;
    }
}
