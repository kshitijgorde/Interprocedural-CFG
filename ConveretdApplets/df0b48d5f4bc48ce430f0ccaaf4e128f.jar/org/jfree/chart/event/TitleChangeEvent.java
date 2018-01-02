// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.event;

import org.jfree.chart.title.Title;

public class TitleChangeEvent extends ChartChangeEvent
{
    private Title title;
    
    public TitleChangeEvent(final Title title) {
        super(title);
        this.title = title;
    }
    
    public Title getTitle() {
        return this.title;
    }
}
