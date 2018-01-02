// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot2d;

import java.awt.Color;

public class Colormap
{
    private double minValue;
    private double maxValue;
    private double scale;
    private Color[] colors;
    private int[][] colorIndicies;
    
    public Colormap(final double minValue, final double maxValue) {
        this.minValue = 0.0;
        this.maxValue = 1.0;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.init();
        this.updateScale();
    }
    
    public Colormap() {
        this.minValue = 0.0;
        this.maxValue = 1.0;
        this.init();
        this.updateScale();
    }
    
    private void init() {
        final int N = 256;
        this.colors = new Color[N];
        this.colorIndicies = new int[N][3];
        for (int i = 0; i < N; ++i) {
            this.colorIndicies[i][0] = i;
            this.colorIndicies[i][1] = i;
            this.colorIndicies[i][2] = i;
            this.colors[i] = new Color(i, i, i);
        }
    }
    
    private final int getColorIndex(final double value) {
        int index = (int)((value - this.minValue) * this.scale);
        if (index < 0) {
            index = 0;
        }
        if (index >= this.colors.length) {
            index = this.colors.length - 1;
        }
        return index;
    }
    
    public Color getColor(final double value) {
        return this.colors[this.getColorIndex(value)];
    }
    
    public int[] getColorIndicies(final double value) {
        return this.colorIndicies[this.getColorIndex(value)];
    }
    
    public void setMinValue(final double minValue) {
        this.minValue = minValue;
        this.updateScale();
    }
    
    public void setMaxValue(final double maxValue) {
        this.maxValue = maxValue;
        this.updateScale();
    }
    
    private void updateScale() {
        this.scale = this.colors.length / (this.maxValue - this.minValue);
    }
}
