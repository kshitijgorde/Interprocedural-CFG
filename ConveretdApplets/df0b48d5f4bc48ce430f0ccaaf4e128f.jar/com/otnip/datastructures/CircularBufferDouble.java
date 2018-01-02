// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datastructures;

public class CircularBufferDouble
{
    private double[] data;
    private int currentIndex;
    
    public CircularBufferDouble(final int bufferSize) {
        this.data = null;
        this.currentIndex = 0;
        this.data = new double[bufferSize];
    }
    
    public void add(final double x) {
        this.data[this.currentIndex++] = x;
        if (this.currentIndex == this.data.length) {
            this.currentIndex = 0;
        }
    }
    
    public double[] getArrayPointer() {
        return this.data;
    }
    
    public int getCurrentIndex() {
        return this.currentIndex;
    }
    
    public double[] getData(final double[] data) {
        System.arraycopy(this.data, this.currentIndex, data, 0, this.data.length - this.currentIndex);
        System.arraycopy(this.data, 0, data, this.data.length - this.currentIndex, this.currentIndex);
        return data;
    }
    
    public double[] getData() {
        return this.getData(new double[this.data.length]);
    }
}
