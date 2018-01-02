// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import java.util.Iterator;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xidget.Creator;
import javax.swing.JComponent;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class LineChartWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private LineChart chart;
    
    public LineChartWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        this.findGraphAxes(this.chart = new LineChart());
        return this.chart;
    }
    
    private void findGraphAxes(final LineChart lineChart) {
        final Creator instance = Creator.getInstance();
        final IModelObject parent = this.xidget.getConfig().getParent();
        for (final IModelObject modelObject : parent.getChildren("xaxis")) {
            final IXidget xidget = instance.findXidget(modelObject);
            if (xidget != null) {
                lineChart.addAxis(Xlate.get(modelObject, "name", "x"), xidget.getFeature(Axis.class));
            }
        }
        for (final IModelObject modelObject2 : parent.getChildren("yaxis")) {
            final IXidget xidget2 = instance.findXidget(modelObject2);
            if (xidget2 != null) {
                lineChart.addAxis(Xlate.get(modelObject2, "name", "y"), xidget2.getFeature(Axis.class));
            }
        }
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.chart };
    }
    
    public JComponent getComponent() {
        return this.chart;
    }
    
    public LineChart getLineChart() {
        return this.chart;
    }
}
