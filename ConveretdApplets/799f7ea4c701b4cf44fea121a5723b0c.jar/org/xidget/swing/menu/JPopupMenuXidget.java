// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.JPopupMenu;
import javax.swing.JComponent;
import java.awt.Container;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.swing.feature.GenericContainerFeature;
import org.xidget.swing.feature.SwingContainerTextWidgetFeature;
import org.xidget.swing.feature.SwingContainerWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JPopupMenuXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private IWidgetContainerFeature containerFeature;
    private JPopupMenuWidgetCreationFeature creationFeature;
    private IFeatured basicFeatureSet;
    
    @Override
    protected void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new SwingContainerWidgetFeature(this);
        this.textFeature = new SwingContainerTextWidgetFeature(this);
        this.containerFeature = new GenericContainerFeature(this);
        this.creationFeature = new JPopupMenuWidgetCreationFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == ITextWidgetFeature.class) {
            return (T)this.textFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJPopupMenu();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJPopupMenu();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJPopupMenu();
        }
        if (clazz == JPopupMenu.class) {
            return (T)this.creationFeature.getJPopupMenu();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
