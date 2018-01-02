// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.applet;

import java.awt.Window;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.feature.SwingContainerTextWidgetFeature;
import org.xidget.feature.BindFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.config.TagProcessor;
import org.xmodel.ModelObject;
import javax.swing.JApplet;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JAppletXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private JAppletWidgetCreationFeature creationFeature;
    private IWidgetContainerFeature containerFeature;
    private IFeatured basicFeatureSet;
    
    public JAppletXidget(final JApplet applet) {
        this.creationFeature = new JAppletWidgetCreationFeature(applet);
        try {
            this.startConfig(null, null, new ModelObject("dummy"));
        }
        catch (Exception ex) {}
    }
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new JAppletWidgetFeature(this);
        this.textFeature = new SwingContainerTextWidgetFeature(this);
        this.containerFeature = new JAppletContainerFeature(this);
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
        if (clazz == ITextWidgetFeature.class) {
            return (T)this.textFeature;
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
            return (T)this.creationFeature.getJApplet();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJApplet();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJApplet();
        }
        if (clazz == Window.class) {
            return (T)this.creationFeature.getJApplet();
        }
        if (clazz == JApplet.class) {
            return (T)this.creationFeature.getJApplet();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
