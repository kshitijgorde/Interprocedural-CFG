// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import javax.swing.JTree;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.model.IPartialSelectionWidgetFeature;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SelectionUpdateFeature;
import org.xidget.feature.model.SelectionModelFeature;
import org.xidget.feature.tree.TreeExpandFeature;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.tree.ITreeExpandFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class JTreeXidget extends Xidget
{
    private IBindFeature bindFeature;
    private ITreeExpandFeature expandFeature;
    private JTreeWidgetFeature treeWidgetFeature;
    private JTreeWidgetCreationFeature creationFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private ISelectionWidgetFeature selectionWidgetFeature;
    private IWidgetFeature widgetFeature;
    private IFeatured basicFeatureSet;
    
    @Override
    protected void createFeatures() {
        this.bindFeature = new BindFeature(this, new String[] { "tree" });
        this.creationFeature = new JTreeWidgetCreationFeature(this);
        this.widgetFeature = new SwingWidgetFeature(this);
        this.expandFeature = new TreeExpandFeature(this);
        this.treeWidgetFeature = new JTreeWidgetFeature(this);
        this.selectionModelFeature = new SelectionModelFeature(this);
        this.selectionUpdateFeature = new SelectionUpdateFeature(this);
        this.selectionWidgetFeature = new JTreeSelectionWidgetFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == ITreeExpandFeature.class) {
            return (T)this.expandFeature;
        }
        if (clazz == ITreeWidgetFeature.class) {
            return (T)this.treeWidgetFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
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
        if (clazz == IPartialSelectionWidgetFeature.class) {
            return (T)this.selectionWidgetFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJScrollPane();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJScrollPane();
        }
        if (clazz == JTree.class) {
            return (T)this.creationFeature.getJTree();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
