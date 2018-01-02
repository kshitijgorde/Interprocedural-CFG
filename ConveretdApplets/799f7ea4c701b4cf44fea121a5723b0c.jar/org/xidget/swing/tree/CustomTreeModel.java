// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import org.xmodel.xpath.expression.StatefulContext;
import javax.swing.SwingUtilities;
import java.util.Iterator;
import org.xidget.ifeature.tree.ITreeExpandFeature;
import org.xmodel.util.Fifo;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import java.util.List;
import org.xidget.tree.Row;
import org.xidget.IXidget;
import javax.swing.tree.TreeModel;

public class CustomTreeModel implements TreeModel
{
    private IXidget xidget;
    private Row root;
    private List<TreeModelListener> listeners;
    private boolean swingWillHaveCollapsed;
    private boolean expandOnCommit;
    
    public CustomTreeModel(final IXidget xidget) {
        this.xidget = xidget;
        this.listeners = new ArrayList<TreeModelListener>(3);
        (this.root = new Row(null)).setExpanded(true);
    }
    
    @Override
    public void addTreeModelListener(final TreeModelListener treeModelListener) {
        this.listeners.add(treeModelListener);
    }
    
    @Override
    public void removeTreeModelListener(final TreeModelListener treeModelListener) {
        this.listeners.remove(treeModelListener);
    }
    
    public Object[] createPath(Row parent) {
        final ArrayList<Row> list = new ArrayList<Row>();
        if (parent != null) {
            while (parent != null) {
                list.add(0, parent);
                parent = parent.getParent();
            }
        }
        else {
            list.add(0, this.root);
        }
        return list.toArray();
    }
    
    public Row findObject(final Object o) {
        return this.findObject(null, o);
    }
    
    public Row findObject(final IXidget xidget, final Object o) {
        return this.findObject(xidget, this.root, o);
    }
    
    public Row findObject(final IXidget xidget, final Row row, final Object o) {
        if (row != this.root) {
            final IXidget table = row.getTable();
            if ((xidget == null || table == null || table.getParent() == xidget) && row.getContext().getObject().equals(o)) {
                return row;
            }
        }
        final Fifo<Row> fifo = new Fifo<Row>();
        fifo.push(row);
        while (!fifo.empty()) {
            final Row row2 = fifo.pop();
            row2.getTree(this.xidget).getFeature(ITreeExpandFeature.class).expand(row2);
            for (final Row row3 : row2.getChildren()) {
                final IXidget table2 = row3.getTable();
                if ((xidget == null || table2 == null || table2.getParent() == xidget) && row3.getContext().getObject().equals(o)) {
                    return row3;
                }
                fifo.push(row3);
            }
        }
        return null;
    }
    
    public void insertRows(final Row row, final int n, final Row[] array) {
        if (this.swingWillHaveCollapsed) {
            this.expandOnCommit = true;
        }
        this.swingWillHaveCollapsed = false;
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = n + i;
        }
        this.fireTreeNodesInserted(this, this.createPath(row), array2, array);
    }
    
    public void removeRows(final Row row, final int n, final Row[] array, final boolean b) {
        if (b) {
            this.swingWillHaveCollapsed = true;
        }
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = n + i;
        }
        this.fireTreeNodesRemoved(this, this.createPath(row), array2, array);
    }
    
    public void commit(final Row row) {
        if (row.isExpanded() && (row.getParent() == null || this.expandOnCommit)) {
            final ExpandRunnable expandRunnable = new ExpandRunnable((ExpandRunnable)null);
            expandRunnable.row = row;
            SwingUtilities.invokeLater(expandRunnable);
            this.swingWillHaveCollapsed = false;
            this.expandOnCommit = false;
        }
    }
    
    @Override
    public Object getChild(final Object o, final int n) {
        return ((o == null) ? this.root.getChildren() : ((Row)o).getChildren()).get(n);
    }
    
    @Override
    public int getChildCount(final Object o) {
        final List<Row> list = (o == null) ? this.root.getChildren() : ((Row)o).getChildren();
        if (list == null) {
            return 0;
        }
        return list.size();
    }
    
    @Override
    public int getIndexOfChild(final Object o, final Object o2) {
        final List<Row> list = (o == null) ? this.root.getChildren() : ((Row)o).getChildren();
        if (list == null) {
            return -1;
        }
        return list.indexOf(o2);
    }
    
    @Override
    public Object getRoot() {
        return this.root;
    }
    
    @Override
    public boolean isLeaf(final Object o) {
        final StatefulContext context = ((Row)o).getContext();
        if (context != null && context.getObject().isDirty()) {
            return false;
        }
        final List<Row> list = (o == null) ? this.root.getChildren() : ((Row)o).getChildren();
        return list == null || list.size() == 0;
    }
    
    @Override
    public void valueForPathChanged(final TreePath treePath, final Object o) {
    }
    
    public void updateCells(final Row row) {
        this.fireTreeNodesChanged(this, this.createPath(row), new int[0], new Row[0]);
    }
    
    protected void fireTreeNodesChanged(final Object o, final Object[] array, final int[] array2, final Object[] array3) {
        if (this.listeners.size() == 0) {
            return;
        }
        final TreeModelEvent treeModelEvent = new TreeModelEvent(o, array, array2, array3);
        TreeModelListener[] array4;
        for (int length = (array4 = this.listeners.toArray(new TreeModelListener[0])).length, i = 0; i < length; ++i) {
            array4[i].treeNodesChanged(treeModelEvent);
        }
    }
    
    protected void fireTreeNodesInserted(final Object o, final Object[] array, final int[] array2, final Object[] array3) {
        if (this.listeners.size() == 0) {
            return;
        }
        final TreeModelEvent treeModelEvent = new TreeModelEvent(o, array, array2, array3);
        TreeModelListener[] array4;
        for (int length = (array4 = this.listeners.toArray(new TreeModelListener[0])).length, i = 0; i < length; ++i) {
            array4[i].treeNodesInserted(treeModelEvent);
        }
    }
    
    protected void fireTreeNodesRemoved(final Object o, final Object[] array, final int[] array2, final Object[] array3) {
        if (this.listeners.size() == 0) {
            return;
        }
        final TreeModelEvent treeModelEvent = new TreeModelEvent(o, array, array2, array3);
        TreeModelListener[] array4;
        for (int length = (array4 = this.listeners.toArray(new TreeModelListener[0])).length, i = 0; i < length; ++i) {
            array4[i].treeNodesRemoved(treeModelEvent);
        }
    }
    
    protected void fireTreeStructureChanged(final Object o, final Object[] array) {
        if (this.listeners.size() == 0) {
            return;
        }
        final TreeModelEvent treeModelEvent = new TreeModelEvent(o, array);
        TreeModelListener[] array2;
        for (int length = (array2 = this.listeners.toArray(new TreeModelListener[0])).length, i = 0; i < length; ++i) {
            array2[i].treeStructureChanged(treeModelEvent);
        }
    }
    
    private void fireTreeStructureChanged(final Object o, final TreePath treePath) {
        throw new InternalError("Badly shrinked");
    }
    
    private class ExpandRunnable implements Runnable
    {
        public Row row;
        
        @Override
        public void run() {
            CustomTreeModel.this.xidget.getFeature(JTree.class).expandPath(new TreePath(CustomTreeModel.this.createPath(this.row)));
        }
    }
}
