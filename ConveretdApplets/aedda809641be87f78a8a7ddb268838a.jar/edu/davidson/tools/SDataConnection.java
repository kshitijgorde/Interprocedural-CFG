// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

import edu.davidson.numerics.Parser;

public class SDataConnection
{
    int series;
    SDataSource ds;
    SDataListener dl;
    String xStr;
    String yStr;
    double[] x;
    double[] y;
    Parser xparser;
    Parser yparser;
    String[] vars;
    int smooth;
    int stride;
    int strideCounter;
    double[] tempX;
    double[] tempY;
    double lastX;
    double lastY;
    boolean block;
    
    public SDataConnection(final SDataSource ds, final SDataListener dl, final int series, final String s, final String s2) {
        this.ds = null;
        this.dl = null;
        this.x = null;
        this.y = null;
        this.xparser = null;
        this.yparser = null;
        this.vars = null;
        this.smooth = 1;
        this.stride = 1;
        this.strideCounter = 1;
        this.tempX = new double[this.smooth];
        this.tempY = new double[this.smooth];
        this.lastX = 0.0;
        this.lastY = 0.0;
        this.block = false;
        this.series = series;
        this.ds = ds;
        this.dl = dl;
        this.xStr = s;
        this.yStr = s2;
        this.vars = ds.getVarStrings();
        if (this.vars == null) {
            System.out.println("Error:Data source variables have not been initialized.");
        }
        else {
            this.setXStr(s);
            this.setYStr(s2);
        }
        dl.clearSeries(series);
    }
    
    public void setSmooth(final int smooth) {
        if (smooth < 2) {
            this.smooth = 1;
            this.tempX = null;
            this.tempY = null;
            return;
        }
        this.smooth = smooth;
        this.tempX = new double[this.smooth];
        this.tempY = new double[this.smooth];
        for (int i = 0; i < this.smooth; ++i) {
            this.tempX[i] = this.lastX;
            this.tempY[i] = this.lastY;
        }
    }
    
    public void setStride(final int n) {
        this.stride = Math.max(1, n);
        this.strideCounter = 1;
    }
    
    public boolean setXStr(final String s) {
        this.xStr = s.trim();
        if (this.xStr.equals("") || this.xStr.equals("0")) {
            this.xparser = null;
            return true;
        }
        this.xparser = new Parser(this.vars.length);
        for (int i = 0; i < this.vars.length; ++i) {
            this.xparser.defineVariable(i + 1, this.vars[i]);
        }
        this.xparser.define(this.xStr);
        this.xparser.parse();
        if (this.xparser.getErrorCode() != 0) {
            System.out.println("Failed to parse horizontal datasource): ".concat(String.valueOf(String.valueOf(this.xStr))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error: ").append(this.xparser.getErrorString()).append(" at function 1, position ").append(this.xparser.getErrorPosition()))));
            for (int j = 0; j < this.vars.length; ++j) {
                System.out.println("vars ".concat(String.valueOf(String.valueOf(this.vars[j]))));
            }
            return false;
        }
        return true;
    }
    
    public boolean setYStr(final String s) {
        this.yStr = s.trim();
        if (this.yStr.equals("") || this.yStr.equals("0")) {
            this.yparser = null;
            return true;
        }
        this.yparser = new Parser(this.vars.length);
        for (int i = 0; i < this.vars.length; ++i) {
            this.yparser.defineVariable(i + 1, this.vars[i]);
        }
        this.yparser.define(this.yStr);
        this.yparser.parse();
        if (this.yparser.getErrorCode() != 0) {
            System.out.println("Failed to parse vertical datasource: ".concat(String.valueOf(String.valueOf(this.yStr))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error: ").append(this.yparser.getErrorString()).append(" at function 1, position ").append(this.yparser.getErrorPosition()))));
            for (int j = 0; j < this.vars.length; ++j) {
                System.out.println("vars ".concat(String.valueOf(String.valueOf(this.vars[j]))));
            }
            return false;
        }
        return true;
    }
    
    public void registerDatum() {
        if (this.block) {
            return;
        }
        --this.strideCounter;
        if (this.strideCounter > 0) {
            return;
        }
        this.strideCounter = this.stride;
        final double[][] variables = this.ds.getVariables();
        if (variables == null || this.ds.getVarStrings() == null) {
            return;
        }
        if (this.ds.getVarStrings()[0].equals("blocked")) {
            return;
        }
        if (this.ds.getVarStrings()[0].equals("surfacedata") && variables.length > 1) {
            this.dl.addData(this.ds, this.series, null, null);
        }
        final int length = variables.length;
        if (length == 1) {
            if (this.xparser != null) {
                this.lastX = this.xparser.evaluate(variables[0]);
            }
            else {
                this.lastX = 0.0;
            }
            if (this.yparser != null) {
                this.lastY = this.yparser.evaluate(variables[0]);
            }
            else {
                this.lastY = 0.0;
            }
            if (this.smooth > 1) {
                double n = 0.0;
                double n2 = 0.0;
                for (int i = 1; i < this.smooth; ++i) {
                    this.tempX[i - 1] = this.tempX[i];
                    this.tempY[i - 1] = this.tempY[i];
                    n += this.tempX[i];
                    n2 += this.tempY[i];
                }
                this.tempX[this.smooth - 1] = this.lastX;
                this.tempY[this.smooth - 1] = this.lastY;
                this.dl.addDatum(this.ds, this.series, (n + this.lastX) / this.smooth, (n2 + this.lastY) / this.smooth);
            }
            else {
                this.dl.addDatum(this.ds, this.series, this.lastX, this.lastY);
            }
            return;
        }
        if (this.x == null || this.x.length != length) {
            this.x = new double[length];
        }
        if (this.y == null || this.y.length != length) {
            this.y = new double[length];
        }
        for (int j = 0; j < length; ++j) {
            if (this.xparser != null) {
                this.x[j] = this.xparser.evaluate(variables[j]);
            }
            else {
                this.x[j] = 0.0;
            }
            if (this.yparser != null) {
                this.y[j] = this.yparser.evaluate(variables[j]);
            }
            else {
                this.y[j] = 0.0;
            }
        }
        if (this.smooth > 2) {
            this.x = this.smoothData(this.x);
            this.y = this.smoothData(this.y);
        }
        this.dl.addData(this.ds, this.series, this.x, this.y);
    }
    
    private double[] smoothData(final double[] array) {
        final int length = array.length;
        final int n = this.smooth / 2;
        final double[] array2 = new double[length];
        for (int i = n; i < length - n; ++i) {
            double n2 = 0.0;
            for (int j = -n; j <= n; ++j) {
                n2 += array[i + j];
            }
            array2[i] = n2 / (2 * n + 1);
        }
        return array2;
    }
    
    public void clearData() {
        this.dl.clearSeries(this.series);
    }
    
    public void deleteData() {
        this.dl.deleteSeries(this.series);
    }
    
    public final SDataSource getDataSource() {
        return this.ds;
    }
    
    public final SDataListener getDataListener() {
        return this.dl;
    }
}
