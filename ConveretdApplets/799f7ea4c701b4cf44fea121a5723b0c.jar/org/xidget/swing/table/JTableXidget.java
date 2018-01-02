// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import javax.swing.JTable;
import javax.swing.JComponent;
import java.awt.Component;
import org.xidget.ifeature.model.IPartialSelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.swing.feature.BasicFeatureSet;
import org.xidget.feature.model.SelectionUpdateFeature;
import org.xidget.feature.model.SelectionModelFeature;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.feature.BindFeature;
import org.xidget.feature.tree.ColumnSetFeature;
import org.xidget.IXidget;
import org.xidget.feature.tree.RowSetFeature;
import org.xidget.IFeatured;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISelectionModelFeature;
import org.xidget.ifeature.tree.IColumnWidthFeature;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.ifeature.tree.IColumnSetFeature;
import org.xidget.ifeature.tree.IRowSetFeature;
import org.xidget.Xidget;

public class JTableXidget extends Xidget
{
    private IRowSetFeature rowSetFeature;
    private IColumnSetFeature columnSetFeature;
    private IBindFeature bindFeature;
    private IWidgetFeature widgetFeature;
    private IWidgetContainerFeature containerFeature;
    private ITreeWidgetFeature treeWidgetFeature;
    private IColumnWidthFeature columnWidthFeature;
    private JTableWidgetCreationFeature creationFeature;
    private ISelectionModelFeature selectionModelFeature;
    private ISelectionUpdateFeature selectionUpdateFeature;
    private IFeatured basicFeatureSet;
    
    public void createFeatures() {
        this.rowSetFeature = new RowSetFeature(this);
        this.columnSetFeature = new ColumnSetFeature(this);
        this.bindFeature = new BindFeature(this, new String[] { "text", "combo", "button" });
        this.widgetFeature = new SwingWidgetFeature(this);
        this.containerFeature = new JTableContainerFeature(this);
        this.treeWidgetFeature = new JTableWidgetFeature(this);
        this.columnWidthFeature = new JTableColumnWidthFeature(this);
        this.creationFeature = new JTableWidgetCreationFeature(this);
        this.selectionModelFeature = new SelectionModelFeature(this);
        this.selectionUpdateFeature = new SelectionUpdateFeature(this);
        this.basicFeatureSet = new BasicFeatureSet(this);
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IRowSetFeature.class) {
            return (T)this.rowSetFeature;
        }
        if (clazz == IColumnSetFeature.class) {
            return (T)this.columnSetFeature;
        }
        if (clazz == IWidgetFeature.class) {
            return (T)this.widgetFeature;
        }
        if (clazz == IWidgetContainerFeature.class) {
            return (T)this.containerFeature;
        }
        if (clazz == ITreeWidgetFeature.class) {
            return (T)this.treeWidgetFeature;
        }
        if (clazz == IColumnWidthFeature.class) {
            return (T)this.columnWidthFeature;
        }
        if (clazz == IWidgetCreationFeature.class) {
            return (T)this.creationFeature;
        }
        if (clazz == ISelectionModelFeature.class) {
            return (T)this.selectionModelFeature;
        }
        if (clazz == ISelectionUpdateFeature.class) {
            return (T)this.selectionUpdateFeature;
        }
        if (clazz == ISelectionWidgetFeature.class) {
            return (T)this.treeWidgetFeature;
        }
        if (clazz == IPartialSelectionWidgetFeature.class) {
            return (T)this.treeWidgetFeature;
        }
        if (clazz == IBindFeature.class) {
            return (T)this.bindFeature;
        }
        if (clazz == Component.class) {
            return (T)this.creationFeature.getJScrollPane();
        }
        if (clazz == JComponent.class) {
            return (T)this.creationFeature.getJScrollPane();
        }
        if (clazz == JTable.class) {
            return (T)this.creationFeature.getJTable();
        }
        final T feature = this.basicFeatureSet.getFeature(clazz);
        if (feature != null) {
            return feature;
        }
        return super.getFeature(clazz);
    }
}
