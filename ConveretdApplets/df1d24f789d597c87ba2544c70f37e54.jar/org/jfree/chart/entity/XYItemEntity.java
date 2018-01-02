// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.awt.Shape;
import org.jfree.data.XYDataset;

public class XYItemEntity extends ChartEntity
{
    private transient XYDataset dataset;
    private int series;
    private int item;
    
    public XYItemEntity(final Shape area, final XYDataset dataset, final int series, final int item, final String toolTipText, final String urlText) {
        super(area, toolTipText, urlText);
        this.dataset = dataset;
        this.series = series;
        this.item = item;
    }
    
    public XYDataset getDataset() {
        return this.dataset;
    }
    
    public void setDataset(final XYDataset dataset) {
        this.dataset = dataset;
    }
    
    public int getSeriesIndex() {
        return this.series;
    }
    
    public void setSeriesIndex(final int series) {
        this.series = series;
    }
    
    public int getItem() {
        return this.item;
    }
    
    public void setItem(final int item) {
        this.item = item;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XYItemEntity && super.equals(obj)) {
            final XYItemEntity ie = (XYItemEntity)obj;
            return this.series == ie.series && this.item == ie.item;
        }
        return false;
    }
    
    public XYItemEntity(final Shape area, final String toolTipText, final int series, final int item) {
        super(area, toolTipText);
        this.series = series;
        this.item = item;
    }
    
    public XYItemEntity(final Shape area, final String toolTipText, final String urlText, final int series, final int item) {
        super(area, toolTipText, urlText);
        this.series = series;
        this.item = item;
    }
    
    public int getSeries() {
        return this.series;
    }
    
    public void setSeries(final int series) {
        this.series = series;
    }
}
