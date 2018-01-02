// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.tree;

import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IAsyncFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.feature.tree.TreeExpandFeature;
import org.xidget.feature.ScriptFeature;
import org.xidget.IXidget;
import org.xidget.feature.BindFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.tree.ITreeExpandFeature;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.Xidget;

public class SubTreeXidget extends Xidget
{
    private IBindFeature bindFeature;
    private IScriptFeature scriptFeature;
    private ITreeExpandFeature expandFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private ISelectionWidgetFeature selectionWidgetFeature;
    
    @Override
    protected void createFeatures() {
        this.bindFeature = new BindFeature(this, new String[] { "tree" });
        this.scriptFeature = new ScriptFeature(this);
        this.expandFeature = new TreeExpandFeature(this);
        this.selectionModelFeature = new SubTreeSelectionModelFeature(this);
        this.selectionUpdateFeature = new SubTreeSelectionUpdateFeature(this);
        this.selectionWidgetFeature = new SubTreeSelectionWidgetFeature(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == IScriptFeature.class) {
            return (T)this.scriptFeature;
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
        if (clazz == ITreeExpandFeature.class) {
            return (T)this.expandFeature;
        }
        if (clazz == ITreeWidgetFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IWidgetFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IAsyncFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IWidgetCreationFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        return super.getFeature(clazz);
    }
}
