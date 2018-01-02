// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import org.xmodel.Xlate;
import javax.swing.JComponent;
import org.xidget.IXidget;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class YAxisWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private Axis axis;
    
    public YAxisWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected JComponent createSwingWidget() {
        return this.axis = new YAxis(Xlate.get(this.xidget.getConfig(), "left", true));
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.axis };
    }
    
    public JComponent getComponent() {
        return this.axis;
    }
    
    public Axis getAxis() {
        return this.axis;
    }
}
