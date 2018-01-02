// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import org.jfree.chart.entity.ChartEntity;
import java.awt.event.MouseEvent;

public class ChartMouseEvent
{
    private JFreeChart chart;
    private MouseEvent trigger;
    private ChartEntity entity;
    
    public ChartMouseEvent(final JFreeChart chart, final MouseEvent trigger, final ChartEntity entity) {
        this.chart = chart;
        this.trigger = trigger;
        this.entity = entity;
    }
    
    public JFreeChart getChart() {
        return this.chart;
    }
    
    public MouseEvent getTrigger() {
        return this.trigger;
    }
    
    public ChartEntity getEntity() {
        return this.entity;
    }
}
