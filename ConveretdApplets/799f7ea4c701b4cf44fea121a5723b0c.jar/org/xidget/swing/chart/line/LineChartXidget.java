// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IPointsFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class LineChartXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private LineChartWidgetCreationFeature creationFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new SwingWidgetFeature(this);
        this.creationFeature = new LineChartWidgetCreationFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IPointsFeature.class) {
            return (T)this.creationFeature.getLineChart();
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getComponent();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getComponent();
        }
        if (clazz == LineChart.class) {
            return (T)this.creationFeature.getLineChart();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
