// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import edu.wise.exceptions.DataNotFoundException;
import VisualNumerics.math.Statistics;

public class Data
{
    private static double[][] data;
    private static double[] YbarArr;
    private static double[] YpredArr;
    private static int regType;
    public static final boolean DEBUG = false;
    
    static {
        Data.data = new double[][] { new double[0], new double[0] };
        Data.regType = 0;
    }
    
    public Data() {
        this(2, 10);
    }
    
    public Data(final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.addCase(Math.random() * 10.0, Math.random() * 10.0);
        }
    }
    
    public Data(final double[] array, final double[] array2) {
        int n = array.length;
        if (array2.length > array.length) {
            n = array2.length;
        }
        (Data.data = new double[2][n])[0] = array;
        Data.data[1] = array2;
        Data.YbarArr = new double[Data.data[1].length];
        Data.YpredArr = new double[Data.data[1].length];
        update();
    }
    
    public void addCase(final double n, final double n2) {
        final double[] array = new double[Data.data[0].length + 1];
        final double[] array2 = new double[Data.data[1].length + 1];
        final double[] ybarArr = new double[Data.data[1].length + 1];
        final double[] ypredArr = new double[Data.data[1].length + 1];
        Data.YbarArr = ybarArr;
        Data.YpredArr = ypredArr;
        for (int i = 0; i < Data.data[0].length; ++i) {
            array[i] = Data.data[0][i];
            array2[i] = Data.data[1][i];
        }
        array[Data.data[0].length] = n;
        array2[Data.data[1].length] = n2;
        Data.data[0] = array;
        Data.data[1] = array2;
        update();
    }
    
    public static void update() {
        final double average = Statistics.average(Data.data[1]);
        switch (Data.regType) {
            case 0: {
                final double[] linearFit = Statistics.linearFit(Data.data[0], Data.data[1]);
                for (int i = 0; i < Data.data[0].length; ++i) {
                    Data.YbarArr[i] = average;
                    Data.YpredArr[i] = linearFit[0] + linearFit[1] * Data.data[0][i];
                }
                break;
            }
            case 1: {
                for (int j = 0; j < Data.data[0].length; ++j) {
                    Data.YpredArr[j] = (Data.YbarArr[j] = average);
                }
                break;
            }
        }
    }
    
    public void delPt(final int n) {
        if (Data.data[0].length > 0 && Data.data[1].length > 0) {
            final double[] array = new double[Data.data[0].length - 1];
            final double[] array2 = new double[Data.data[1].length - 1];
            final double[] ybarArr = new double[Data.data[1].length - 1];
            final double[] ypredArr = new double[Data.data[1].length - 1];
            int n2 = -1;
            for (int i = 0; i < Data.data[0].length; ++i) {
                if (i != n) {
                    ++n2;
                    array[n2] = Data.data[0][i];
                    array2[n2] = Data.data[1][i];
                    ybarArr[n2] = Data.YbarArr[i];
                    ypredArr[n2] = Data.YpredArr[i];
                }
            }
            Data.data[0] = array;
            Data.data[1] = array2;
            Data.YbarArr = ybarArr;
            Data.YpredArr = ypredArr;
            update();
        }
    }
    
    public void changePt(final double n, final double n2, final int n3) {
        if (n3 >= 0 && n3 < Data.data[0].length) {
            Data.data[0][n3] = n;
            Data.data[1][n3] = n2;
            update();
        }
    }
    
    public double[] getXArr() {
        return Data.data[0];
    }
    
    public double[] getYArr() {
        return Data.data[1];
    }
    
    public double getXi(final int n) throws DataNotFoundException {
        try {
            return Data.data[0][n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("Data.getX(" + n + ")");
        }
    }
    
    public double getYi(final int n) throws DataNotFoundException {
        try {
            return Data.data[1][n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new DataNotFoundException("Data.getY(" + n + ")");
        }
    }
    
    public double[] getYbarArr() {
        return Data.YbarArr;
    }
    
    public double[] getYpredArr() {
        return Data.YpredArr;
    }
    
    public int getRegType() {
        return Data.regType;
    }
    
    public void setXArr(final double[] array) {
        Data.data[0] = array;
        update();
    }
    
    public void setYArr(final double[] array) {
        Data.data[1] = array;
        update();
    }
    
    public void setXi(final int n, final double n2) {
        if (n >= 0 && n < Data.data[0].length) {
            Data.data[0][n] = n2;
            update();
        }
    }
    
    public void setYi(final int n, final double n2) {
        if (n >= 0 && n < Data.data[1].length) {
            Data.data[1][n] = n2;
            update();
        }
    }
    
    public void setYpredArr(final double[] ypredArr) {
        Data.YpredArr = ypredArr;
    }
    
    public void setRegType(final int regType) {
        Data.regType = regType;
        update();
    }
}
