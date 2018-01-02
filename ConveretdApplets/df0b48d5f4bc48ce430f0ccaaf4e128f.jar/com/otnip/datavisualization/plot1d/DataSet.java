// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot1d;

import com.otnip.math.Statistics;

public class DataSet
{
    private String name;
    private double[] x;
    private double[] y;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private boolean alwaysIncreasingInX;
    private DataSetRenderingPreferences renderingPrefrences;
    
    public DataSet(final double[] x, final double[] y) {
        this.name = "Default Data Set Name";
        this.alwaysIncreasingInX = true;
        this.renderingPrefrences = new DataSetRenderingPreferences();
        this.x = x;
        this.y = y;
        this.init();
    }
    
    private void init() {
        this.calculateXBounds();
        this.calculateYBounds();
        for (int N = this.x.length, i = 1; i < N; ++i) {
            this.alwaysIncreasingInX &= (this.x[i] >= this.x[i - 1]);
        }
    }
    
    private void calculateXBounds() {
        this.minX = Statistics.min(this.x);
        this.maxX = Statistics.max(this.x);
    }
    
    private void calculateYBounds() {
        this.minY = Statistics.min(this.y);
        this.maxY = Statistics.max(this.y);
    }
    
    private void calculateBounds() {
        this.calculateXBounds();
        this.calculateYBounds();
    }
    
    public double[] getX() {
        return this.x;
    }
    
    public double[] getY() {
        return this.y;
    }
    
    public boolean isAlwaysIncreasingInX() {
        return this.alwaysIncreasingInX;
    }
    
    public double getMinX() {
        return this.minX;
    }
    
    public double getMaxX() {
        return this.maxX;
    }
    
    public double getMinY() {
        return this.minY;
    }
    
    public double getMaxY() {
        return this.maxY;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public DataSetRenderingPreferences getRenderingPreferences() {
        return this.renderingPrefrences;
    }
}
