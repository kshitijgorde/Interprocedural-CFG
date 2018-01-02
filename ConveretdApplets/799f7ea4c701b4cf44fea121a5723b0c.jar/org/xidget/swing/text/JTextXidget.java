// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.text;

import javax.swing.text.JTextComponent;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SingleValueUpdateFeature;
import org.xidget.feature.model.SingleValueModelFeature;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xidget.ifeature.model.ISingleValueModelFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JTextXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private ISingleValueModelFeature singleValueModelFeature;
    private ISingleValueUpdateFeature singleValueUpdateFeature;
    private ISingleValueWidgetFeature singleValueWidgetFeature;
    private JTextComponentWidgetCreationFeature creationFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new SwingWidgetFeature(this);
        this.textFeature = new JTextComponentTextWidgetFeature(this);
        this.singleValueModelFeature = new SingleValueModelFeature(this);
        this.singleValueUpdateFeature = new SingleValueUpdateFeature(this);
        this.singleValueWidgetFeature = new JTextComponentSingleValueWidgetFeature(this);
        this.creationFeature = new JTextComponentWidgetCreationFeature(this);
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
        if (clazz == ISingleValueModelFeature.class) {
            return (T)this.singleValueModelFeature;
        }
        if (clazz == ISingleValueUpdateFeature.class) {
            return (T)this.singleValueUpdateFeature;
        }
        if (clazz == ISingleValueWidgetFeature.class) {
            return (T)this.singleValueWidgetFeature;
        }
        if (clazz == ILabelFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getContainer();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getContainer();
        }
        if (clazz == JTextComponent.class) {
            return (T)this.creationFeature.getTextWidget();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
