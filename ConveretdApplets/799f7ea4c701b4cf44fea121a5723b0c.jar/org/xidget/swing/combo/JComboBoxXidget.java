// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xidget.ifeature.model.ISingleValueModelFeature;
import org.xidget.ifeature.model.IMultiValueUpdateFeature;
import org.xidget.ifeature.model.IMultiValueModelFeature;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SelectionUpdateFeature;
import org.xidget.feature.model.SelectionModelFeature;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.model.IMultiValueWidgetFeature;
import org.xidget.feature.model.MultiValueUpdateFeature;
import org.xidget.feature.model.MultiValueModelFeature;
import org.xidget.feature.model.SingleValueUpdateFeature;
import org.xidget.feature.model.SingleValueModelFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JComboBoxXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private SingleValueModelFeature singleValueModelFeature;
    private SingleValueUpdateFeature singleValueUpdateFeature;
    private MultiValueModelFeature multiValueModelFeature;
    private MultiValueUpdateFeature multiValueUpdateFeature;
    private IMultiValueWidgetFeature multiValueWidgetFeature;
    private JComboBoxSingleValueWidgetFeature singleValueWidgetFeature;
    private JComboBoxWidgetCreationFeature creationFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionWidgetFeature selectionWidgetFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new BindFeature(this);
        this.widgetFeature = new SwingWidgetFeature(this);
        this.textFeature = new JComboBoxTextWidgetFeature(this);
        this.creationFeature = new JComboBoxWidgetCreationFeature(this);
        this.multiValueModelFeature = new MultiValueModelFeature(this);
        this.multiValueUpdateFeature = new MultiValueUpdateFeature(this);
        this.multiValueWidgetFeature = new JComboBoxMultiValueWidgetFeature(this);
        this.singleValueModelFeature = new SingleValueModelFeature(this);
        this.singleValueUpdateFeature = new SingleValueUpdateFeature(this);
        this.singleValueWidgetFeature = new JComboBoxSingleValueWidgetFeature(this);
        this.selectionModelFeature = new SelectionModelFeature(this);
        this.selectionUpdateFeature = new SelectionUpdateFeature(this);
        this.selectionWidgetFeature = new JComboBoxSelectionWidgetFeature(this);
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
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == ILabelFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IMultiValueModelFeature.class) {
            return (T)this.multiValueModelFeature;
        }
        if (clazz == IMultiValueUpdateFeature.class) {
            return (T)this.multiValueUpdateFeature;
        }
        if (clazz == IMultiValueWidgetFeature.class) {
            return (T)this.multiValueWidgetFeature;
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
        if (clazz == ISelectionModelFeature.class) {
            return (T)this.selectionModelFeature;
        }
        if (clazz == ISelectionUpdateFeature.class) {
            return (T)this.selectionUpdateFeature;
        }
        if (clazz == ISelectionWidgetFeature.class) {
            return (T)this.selectionWidgetFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getContainer();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getContainer();
        }
        if (clazz == JComboBox.class) {
            return (T)this.creationFeature.getComboBox();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
