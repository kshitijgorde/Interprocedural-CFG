// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tabs;

import javax.swing.JTabbedPane;
import java.awt.Container;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SelectionUpdateFeature;
import org.xidget.feature.model.SelectionModelFeature;
import org.xidget.swing.feature.SwingTitleFeature;
import org.xidget.swing.feature.SwingContainerTextWidgetFeature;
import org.xidget.swing.feature.SwingContainerWidgetFeature;
import org.xidget.IXidget;
import org.xidget.IFeatured;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.ITitleFeature;
import org.xidget.ifeature.ITextWidgetFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JTabbedPaneXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private ITextWidgetFeature textFeature;
    private ITitleFeature titleFeature;
    private JTabbedPaneWidgetCreationFeature creationFeature;
    private IWidgetContainerFeature containerFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private ISelectionWidgetFeature selectionWidgetFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.bindFeature = new JTabbedPaneBindFeature(this);
        this.widgetFeature = new SwingContainerWidgetFeature(this);
        this.textFeature = new SwingContainerTextWidgetFeature(this);
        this.titleFeature = new SwingTitleFeature(this);
        this.creationFeature = new JTabbedPaneWidgetCreationFeature(this);
        this.containerFeature = new JTabbedPaneContainerFeature(this);
        this.selectionModelFeature = new SelectionModelFeature(this);
        this.selectionUpdateFeature = new SelectionUpdateFeature(this);
        this.selectionWidgetFeature = new JTabbedPaneSelectionWidgetFeature(this);
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
            return (T)this.titleFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
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
            return (T)this.creationFeature.getJTabbedPane();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJTabbedPane();
        }
        if (clazz == Container.class) {
            return (T)this.creationFeature.getJTabbedPane();
        }
        if (clazz == JTabbedPane.class) {
            return (T)this.creationFeature.getJTabbedPane();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
