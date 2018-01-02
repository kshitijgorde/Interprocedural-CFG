// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.table;

import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IAsyncFeature;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.feature.DragAndDropFeature;
import org.xidget.feature.ScriptFeature;
import org.xidget.feature.model.SelectionUpdateFeature;
import org.xidget.feature.model.SelectionModelFeature;
import org.xidget.feature.BindFeature;
import org.xidget.feature.tree.ColumnSetFeature;
import org.xidget.IXidget;
import org.xidget.feature.tree.RowSetFeature;
import org.xidget.ifeature.IDragAndDropFeature;
import org.xidget.ifeature.IScriptFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.ifeature.tree.IColumnSetFeature;
import org.xidget.ifeature.tree.IRowSetFeature;
import org.xidget.Xidget;

public class SubTableXidget extends Xidget
{
    private IRowSetFeature rowSetFeature;
    private IColumnSetFeature columnSetFeature;
    private IBindFeature bindFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private ISelectionWidgetFeature selectionWidgetFeature;
    private IScriptFeature scriptFeature;
    private IDragAndDropFeature dndFeature;
    
    public void createFeatures() {
        this.rowSetFeature = new RowSetFeature(this);
        this.columnSetFeature = new ColumnSetFeature(this);
        this.bindFeature = new BindFeature(this, new String[] { "text", "combo", "button" });
        this.selectionModelFeature = new SelectionModelFeature(this);
        this.selectionUpdateFeature = new SelectionUpdateFeature(this);
        this.selectionWidgetFeature = new SubTableSelectionWidgetFeature(this);
        this.scriptFeature = new ScriptFeature(this);
        this.dndFeature = new DragAndDropFeature(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IColumnSetFeature.class) {
            return (T)this.columnSetFeature;
        }
        if (clazz == IRowSetFeature.class) {
            return (T)this.rowSetFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
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
        if (clazz == ITreeWidgetFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IAsyncFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IWidgetCreationFeature.class) {
            return this.getParent().getFeature(clazz);
        }
        if (clazz == IScriptFeature.class) {
            return (T)this.scriptFeature;
        }
        if (clazz == IDragAndDropFeature.class) {
            return (T)this.dndFeature;
        }
        return super.getFeature(clazz);
    }
}
