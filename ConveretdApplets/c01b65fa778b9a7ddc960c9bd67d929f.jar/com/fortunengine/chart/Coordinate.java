// 
// Decompiled by Procyon v0.5.30
// 

package com.fortunengine.chart;

public class Coordinate
{
    private static final int MAX_STEPS = 9;
    private double[] step_value;
    private double[] increase_value;
    private double[] base_value;
    private double MaxValue;
    private double MinValue;
    private int dataPieces;
    
    public Coordinate() {
        this.step_value = new double[10];
        this.increase_value = new double[10];
        this.base_value = new double[10];
        this.MaxValue = 1.0;
        this.MinValue = 0.0;
        this.dataPieces = 4;
        this.step_value[0] = 0.01;
        this.increase_value[0] = 4.9E-4;
        this.base_value[0] = 5.0E-4;
        for (int i = 1; i < 9; ++i) {
            this.step_value[i] = this.step_value[i - 1] * 10.0;
            this.increase_value[i] = this.increase_value[i - 1] * 10.0;
            this.base_value[i] = this.base_value[i - 1] * 10.0;
        }
    }
    
    public double adjustCoordinate(final double n, final double n2) {
        final double n3 = n - n2;
        double n4;
        if (n3 == 0.0) {
            n4 = this.step_value[0];
        }
        else {
            n4 = n3 / (this.dataPieces - 1);
        }
        int i;
        for (i = 0; i < 9; ++i) {
            if (n4 <= this.step_value[i]) {
                n4 = (int)(Object)new Double((n4 + this.increase_value[i]) / this.base_value[i]) * this.base_value[i];
                break;
            }
        }
        if (i == 9) {
            --i;
            n4 = (int)(Object)new Double((n4 + this.increase_value[i]) / this.base_value[i]) * this.base_value[i];
        }
        int n5;
        if (n2 >= 0.0) {
            n5 = (int)(Object)new Double(n2 / n4);
        }
        else {
            n5 = (int)(Object)new Double(n2 / n4 - 0.9);
        }
        this.MinValue = n5 * n4;
        this.MaxValue = this.MinValue + this.dataPieces * n4;
        return this.step_value[i];
    }
    
    public double getNewMaxValue() {
        return this.MaxValue;
    }
    
    public double getNewMinValue() {
        return this.MinValue;
    }
    
    public void setDataPieces(final int dataPieces) {
        this.dataPieces = dataPieces;
    }
}
