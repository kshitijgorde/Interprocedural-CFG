// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.JFreeChart;
import java.util.EventObject;

public class ChartChangeEvent extends EventObject
{
    public static final int GENERAL = 1;
    public static final int NEW_DATASET = 2;
    public static final int UPDATED_DATASET = 3;
    private int type;
    private JFreeChart chart;
    
    public ChartChangeEvent(final Object source) {
        this(source, null, 1);
    }
    
    public ChartChangeEvent(final Object source, final JFreeChart chart) {
        this(source, chart, 1);
    }
    
    public ChartChangeEvent(final Object source, final JFreeChart chart, final int type) {
        super(source);
        this.chart = chart;
        this.type = type;
    }
    
    public JFreeChart getChart() {
        return this.chart;
    }
    
    public void setChart(final JFreeChart chart) {
        this.chart = chart;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
}
