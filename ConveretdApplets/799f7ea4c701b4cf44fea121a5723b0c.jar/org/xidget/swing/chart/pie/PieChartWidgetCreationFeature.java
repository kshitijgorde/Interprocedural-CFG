// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.pie;

import javax.swing.JComponent;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class PieChartWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private PieChart pie;
    
    public PieChartWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.pie = new PieChart();
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.pie };
    }
    
    public JComponent getComponent() {
        return this.pie;
    }
    
    public PieChart getPieChart() {
        return this.pie;
    }
}
