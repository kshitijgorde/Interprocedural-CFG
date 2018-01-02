// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

public class MoogFilter
{
    private int samplingFrq;
    private double resonance;
    private double f;
    private double k;
    private double p;
    private double r;
    private double y1;
    private double y2;
    private double y3;
    private double y4;
    private double oldx;
    private double oldy1;
    private double oldy2;
    private double oldy3;
    private double yMax;
    private double yMin;
    
    public MoogFilter(final int samplingFrq) {
        this.samplingFrq = samplingFrq;
    }
    
    public void printStatus() {
        System.out.println("yMax: " + this.yMax);
        System.out.println("yMin: " + this.yMin);
    }
    
    public void setFilterParams(final int n, final double n2) {
        this.f = 2.0 * n / this.samplingFrq;
        this.k = 3.6 * this.f - 1.6 * this.f * this.f - 1.0;
        this.p = (this.k + 1.0) * 0.5;
        this.r = n2 * Math.exp((1.0 - this.p) * 1.386249);
    }
    
    public void performFilter(final int[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            final double oldx = array[i] / 32768.0 - this.r * this.y4;
            if (array[i] != 0) {}
            this.y1 = oldx * this.p + this.oldx * this.p - this.k * this.y1;
            this.y2 = this.y1 * this.p + this.oldy1 * this.p - this.k * this.y2;
            this.y3 = this.y2 * this.p + this.oldy2 * this.p - this.k * this.y3;
            this.y4 = this.y3 * this.p + this.oldy3 * this.p - this.k * this.y4;
            this.y4 -= this.y4 * this.y4 * this.y4 / 6.0;
            this.oldx = oldx;
            this.oldy1 = this.y1;
            this.oldy2 = this.y2;
            this.oldy3 = this.y3;
            if (this.y4 > this.yMax) {
                this.yMax = this.y4;
            }
            if (this.y4 < this.yMin) {
                this.yMin = this.y4;
            }
            if (this.y4 > 0.9) {
                this.y4 = 0.9;
            }
            if (this.y4 < -0.9) {
                this.y4 = -0.9;
            }
            array[i] = (int)(this.y4 * 32768.0);
        }
    }
}
