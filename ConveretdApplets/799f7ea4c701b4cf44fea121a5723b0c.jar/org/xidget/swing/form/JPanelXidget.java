// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.form;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.canvas.CanvasFeature;
import org.xidget.swing.feature.GenericContainerFeature;
import org.xidget.swing.feature.SwingTitleFeature;
import org.xidget.swing.feature.SwingContainerTextWidgetFeature;
import org.xidget.swing.feature.SwingContainerWidgetFeature;
import org.xidget.feature.AnchorLayoutFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.canvas.ICanvasFeature;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.IIconFeature;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.ifeature.ILayoutFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JPanelXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private ILayoutFeature layoutFeature;
    private ITitleFeature titleFeature;
    private IIconFeature iconFeature;
    private JPanelWidgetCreationFeature creationFeature;
    private IWidgetContainerFeature containerFeature;
    private ICanvasFeature canvasFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.layoutFeature = new AnchorLayoutFeature(this);
        this.widgetFeature = new SwingContainerWidgetFeature(this);
        this.textFeature = new SwingContainerTextWidgetFeature(this);
        this.titleFeature = new SwingTitleFeature(this);
        this.iconFeature = new TabIconFeature(this);
        this.creationFeature = new JPanelWidgetCreationFeature(this);
        this.containerFeature = new GenericContainerFeature(this);
        this.canvasFeature = new CanvasFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == ILayoutFeature.class) {
            return (T)this.layoutFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == ITextWidgetFeature.class) {
            return (T)this.textFeature;
        }
        if (clazz == ITitleFeature.class) {
            return (T)this.titleFeature;
        }
        if (clazz == IIconFeature.class) {
            return (T)this.iconFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == ICanvasFeature.class) {
            return (T)this.canvasFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJPanel();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJPanel();
        }
        if (clazz == JPanel.class) {
            return (T)this.creationFeature.getJPanel();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJPanel();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
