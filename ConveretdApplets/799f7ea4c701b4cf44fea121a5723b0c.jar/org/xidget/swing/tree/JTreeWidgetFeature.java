// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import javax.swing.tree.TreePath;
import java.util.List;
import org.xidget.ifeature.tree.ITreeExpandFeature;
import javax.swing.JTree;
import java.util.HashMap;
import org.xidget.tree.Row;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Map;
import org.xidget.IXidget;
import org.xidget.ifeature.tree.ITreeWidgetFeature;

public class JTreeWidgetFeature implements ITreeWidgetFeature
{
    private IXidget xidget;
    private Map<StatefulContext, Row> contexts;
    
    public JTreeWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.contexts = new HashMap<StatefulContext, Row>();
    }
    
    @Override
    public void insertRows(final Row row, final int n, final Row[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.contexts.put(array[i].getContext(), array[i]);
        }
        ((CustomTreeModel)this.xidget.getFeature(JTree.class).getModel()).insertRows(row, n, array);
        final ITreeExpandFeature treeExpandFeature = this.xidget.getFeature(ITreeExpandFeature.class);
        for (int j = 0; j < array.length; ++j) {
            treeExpandFeature.rowAdded(array[j]);
        }
    }
    
    @Override
    public void removeRows(final Row row, final int n, final Row[] array, final boolean b) {
        final ITreeExpandFeature treeExpandFeature = this.xidget.getFeature(ITreeExpandFeature.class);
        for (int i = 0; i < array.length; ++i) {
            treeExpandFeature.rowRemoved(array[i]);
        }
        final CustomTreeModel customTreeModel = (CustomTreeModel)this.xidget.getFeature(JTree.class).getModel();
        for (int j = 0; j < array.length; ++j) {
            this.contexts.remove(array[j].getContext());
        }
        customTreeModel.removeRows(row, n, array, b);
    }
    
    @Override
    public void commit(final Row row) {
        ((CustomTreeModel)this.xidget.getFeature(JTree.class).getModel()).commit(row);
    }
    
    @Override
    public List<Row> getChildren(final Row row) {
        return row.getChildren();
    }
    
    @Override
    public Row findRow(final StatefulContext context) {
        if (!this.contexts.containsKey(context)) {
            final Row row = (Row)((CustomTreeModel)this.xidget.getFeature(JTree.class).getModel()).getRoot();
            row.setContext(context);
            this.contexts.put(context, row);
        }
        return this.contexts.get(context);
    }
    
    @Override
    public boolean isVisible(final Row row) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        return tree.isVisible(new TreePath(((CustomTreeModel)tree.getModel()).createPath(row)));
    }
    
    @Override
    public void setTitle(final int n, final String s) {
    }
    
    @Override
    public void setColumnWidth(final int n, final int n2) {
    }
    
    @Override
    public void updateCell(final Row row, final int n) {
        ((CustomTreeModel)this.xidget.getFeature(JTree.class).getModel()).updateCells(row);
    }
}
