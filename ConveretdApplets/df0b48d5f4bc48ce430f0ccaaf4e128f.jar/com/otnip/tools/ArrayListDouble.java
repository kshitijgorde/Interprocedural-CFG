// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

public class ArrayListDouble
{
    double[] array;
    int currentPos;
    
    public ArrayListDouble() {
        this.array = null;
        this.currentPos = 0;
        this.array = new double[1024];
    }
    
    public ArrayListDouble(final int N) {
        this.array = null;
        this.currentPos = 0;
        this.array = new double[N];
    }
    
    public void add(final double x) {
        if (this.currentPos == this.array.length) {
            final double[] newArray = new double[this.array.length * 2];
            System.arraycopy(this.array, 0, newArray, 0, this.currentPos);
            this.array = newArray;
        }
        this.array[this.currentPos++] = x;
    }
    
    public void add(final double[] x, final int offset, final int N) {
        for (int i = 0; i < N; ++i) {
            this.add(x[offset + i]);
        }
    }
    
    public void add(final double[] x) {
        this.add(x, 0, x.length);
    }
    
    public double get(final int n) {
        return this.array[n];
    }
    
    public int length() {
        return this.currentPos;
    }
    
    public double[] getArrayCopy() {
        final double[] newArray = new double[this.currentPos];
        System.arraycopy(this.array, 0, newArray, 0, this.currentPos);
        return newArray;
    }
    
    public double[] getArrayPointer() {
        return this.array;
    }
    
    public void clear() {
        this.currentPos = 0;
    }
}
