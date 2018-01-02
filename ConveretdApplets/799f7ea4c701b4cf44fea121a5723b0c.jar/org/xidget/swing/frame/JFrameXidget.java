// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.frame;

import javax.swing.JFrame;
import java.awt.Window;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.feature.ToplevelWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JFrameXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private JFrameWidgetCreationFeature creationFeature;
    private IWidgetContainerFeature containerFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new ToplevelWidgetFeature(this);
        this.creationFeature = new JFrameWidgetCreationFeature(this);
        this.containerFeature = new JFrameContainerFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == ITitleFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getFrame();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getFrame();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getFrame();
        }
        if (clazz == Window.class) {
            return (T)this.creationFeature.getFrame();
        }
        if (clazz == JFrame.class) {
            return (T)this.creationFeature.getFrame();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
