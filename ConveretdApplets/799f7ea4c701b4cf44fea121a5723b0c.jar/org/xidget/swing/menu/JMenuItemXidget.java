// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import javax.swing.AbstractButton;
import java.awt.Container;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SingleValueUpdateFeature;
import org.xidget.feature.model.SingleValueModelFeature;
import org.xidget.swing.feature.GenericContainerFeature;
import org.xidget.swing.feature.AbstractButtonTextWidgetFeature;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.swing.feature.AbstractButtonIconFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xidget.ifeature.model.ISingleValueModelFeature;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IIconFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JMenuItemXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IIconFeature iconFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private IWidgetContainerFeature containerFeature;
    private ISingleValueModelFeature singleValueModelFeature;
    private ISingleValueUpdateFeature singleValueUpdateFeature;
    private ISingleValueWidgetFeature singleValueWidgetFeature;
    private JMenuItemWidgetCreationFeature creationFeature;
    private IFeatured basicFeatureSet;
    
    @Override
    protected void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.iconFeature = new AbstractButtonIconFeature(this);
        this.widgetFeature = new SwingWidgetFeature(this);
        this.textFeature = new AbstractButtonTextWidgetFeature(this);
        this.containerFeature = new GenericContainerFeature(this);
        this.singleValueModelFeature = new SingleValueModelFeature(this);
        this.singleValueUpdateFeature = new SingleValueUpdateFeature(this);
        this.singleValueWidgetFeature = new JMenuItemSingleValueWidgetFeature(this);
        this.creationFeature = new JMenuItemWidgetCreationFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IIconFeature.class) {
            return (T)this.iconFeature;
        }
        if (clazz == ILabelFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == ITextWidgetFeature.class) {
            return (T)this.textFeature;
        }
        if (clazz == ISingleValueModelFeature.class) {
            return (T)this.singleValueModelFeature;
        }
        if (clazz == ISingleValueUpdateFeature.class) {
            return (T)this.singleValueUpdateFeature;
        }
        if (clazz == ISingleValueWidgetFeature.class) {
            return (T)this.singleValueWidgetFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJMenuItem();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJMenuItem();
        }
        if (clazz == JMenuItem.class) {
            return (T)this.creationFeature.getJMenuItem();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJMenuItem();
        }
        if (clazz == AbstractButton.class) {
            return (T)this.creationFeature.getJMenuItem();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
