// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

public class TickLabelEntity extends ChartEntity implements Cloneable, Serializable
{
    public TickLabelEntity(final Shape area, final String toolTipText, final String urlText) {
        super(area, toolTipText, urlText);
    }
}
