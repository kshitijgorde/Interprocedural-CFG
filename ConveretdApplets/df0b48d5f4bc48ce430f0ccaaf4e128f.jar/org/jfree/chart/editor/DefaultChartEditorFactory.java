// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.editor;

import org.jfree.chart.JFreeChart;

public class DefaultChartEditorFactory implements ChartEditorFactory
{
    public ChartEditor createEditor(final JFreeChart chart) {
        return new DefaultChartEditor(chart);
    }
}
