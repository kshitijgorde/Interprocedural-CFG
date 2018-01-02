// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

public class Pick
{
    public Shape shape;
    public int vertex;
    public int[][] pixelPts;
    public double refX;
    public double refY;
    public boolean copy;
    
    public Pick(final Shape shape, final int vertex, final double refX, final double refY, final int[][] pixelPts) {
        this.copy = false;
        this.shape = shape;
        this.vertex = vertex;
        this.pixelPts = pixelPts;
        this.refX = refX;
        this.refY = refY;
    }
    
    public int[][] getPts() {
        return this.pixelPts;
    }
    
    public double getRefX() {
        return this.refX;
    }
    
    public double getRefY() {
        return this.refY;
    }
    
    public int getVertex() {
        return this.vertex;
    }
    
    public boolean isCopy() {
        return this.copy;
    }
    
    public void moveBy(final int n, final int n2) {
        for (int i = 0; i < this.pixelPts[0].length; ++i) {
            final int[] array = this.pixelPts[0];
            final int n3 = i;
            array[n3] += n;
            final int[] array2 = this.pixelPts[1];
            final int n4 = i;
            array2[n4] += n2;
        }
    }
    
    public void setCopy(final boolean copy) {
        this.copy = copy;
    }
    
    public void setRefX(final double refX) {
        this.refX = refX;
    }
    
    public void setRefY(final double refY) {
        this.refY = refY;
    }
    
    public String toString() {
        return "Pick[" + this.shape + ", refpt=" + this.refX + "," + this.refY + ", first pixel pt=" + this.pixelPts[0][0] + "," + this.pixelPts[1][0] + "]";
    }
}
