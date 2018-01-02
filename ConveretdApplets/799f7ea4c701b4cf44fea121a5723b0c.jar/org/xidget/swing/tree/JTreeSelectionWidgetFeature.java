// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTree;
import java.util.LinkedHashMap;
import javax.swing.tree.TreePath;
import org.xidget.tree.Row;
import java.util.Map;
import org.xidget.IXidget;
import org.xidget.ifeature.model.IPartialSelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;

public class JTreeSelectionWidgetFeature implements ISelectionWidgetFeature, IPartialSelectionWidgetFeature
{
    private IXidget xidget;
    private Map<Row, TreePath> pathCache;
    private Map<Object, Row> rowCache;
    
    public JTreeSelectionWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.rowCache = new LinkedHashMap<Object, Row>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<Object, Row> entry) {
                return this.size() > 100;
            }
        };
        this.pathCache = new LinkedHashMap<Row, TreePath>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<Row, TreePath> entry) {
                return this.size() > 100;
            }
        };
    }
    
    @Override
    public void select(final Object o) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        final TreePath path = this.getPath((CustomTreeModel)tree.getModel(), o);
        if (path != null) {
            tree.addSelectionPath(path);
        }
    }
    
    @Override
    public void deselect(final Object o) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        final TreePath path = this.getPath((CustomTreeModel)tree.getModel(), o);
        if (path != null) {
            tree.removeSelectionPath(path);
        }
    }
    
    @Override
    public void setSelection(final List<?> list) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        tree.clearSelection();
        final CustomTreeModel customTreeModel = (CustomTreeModel)tree.getModel();
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            final TreePath path = this.getPath(customTreeModel, iterator.next());
            if (path != null) {
                tree.addSelectionPath(path);
            }
        }
    }
    
    @Override
    public List<?> getSelection() {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final TreePath[] selectionPaths = this.xidget.getFeature(JTree.class).getSelectionPaths();
        if (selectionPaths != null) {
            TreePath[] array;
            for (int length = (array = selectionPaths).length, i = 0; i < length; ++i) {
                list.add(((Row)array[i].getLastPathComponent()).getContext().getObject());
            }
        }
        return list;
    }
    
    @Override
    public void select(final IXidget xidget, final Object o) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        final TreePath path = this.getPath(xidget, (CustomTreeModel)tree.getModel(), o);
        if (path != null) {
            tree.addSelectionPath(path);
        }
    }
    
    @Override
    public void deselect(final IXidget xidget, final Object o) {
        final JTree tree = this.xidget.getFeature(JTree.class);
        final TreePath path = this.getPath(xidget, (CustomTreeModel)tree.getModel(), o);
        if (path != null) {
            tree.removeSelectionPath(path);
        }
    }
    
    @Override
    public void setSelection(final IXidget xidget, final List<?> list) {
        final Iterator<?> iterator = this.getSelection(xidget).iterator();
        while (iterator.hasNext()) {
            this.deselect(iterator.next());
        }
        final JTree tree = this.xidget.getFeature(JTree.class);
        final CustomTreeModel customTreeModel = (CustomTreeModel)tree.getModel();
        final Iterator<?> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            final TreePath path = this.getPath(xidget, customTreeModel, iterator2.next());
            if (path != null) {
                tree.addSelectionPath(path);
            }
        }
    }
    
    @Override
    public List<?> getSelection(final IXidget xidget) {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final TreePath[] selectionPaths = this.xidget.getFeature(JTree.class).getSelectionPaths();
        if (selectionPaths != null) {
            TreePath[] array;
            for (int length = (array = selectionPaths).length, i = 0; i < length; ++i) {
                final Row row = (Row)array[i].getLastPathComponent();
                if (row.getTable().getParent() == xidget) {
                    list.add(row.getContext().getObject());
                }
            }
        }
        return list;
    }
    
    private TreePath getPath(final CustomTreeModel customTreeModel, final Object o) {
        return this.getPath(null, customTreeModel, o);
    }
    
    private TreePath getPath(final IXidget xidget, final CustomTreeModel customTreeModel, final Object o) {
        Row object = this.rowCache.get(o);
        if (object == null || (xidget != null && object.getTable().getParent() != xidget)) {
            object = customTreeModel.findObject(xidget, o);
            if (object == null) {
                return null;
            }
            this.rowCache.put(o, object);
        }
        final Row parent = object.getParent();
        if (parent == null) {
            return new TreePath(customTreeModel.createPath(object));
        }
        TreePath treePath = this.pathCache.get(parent);
        if (treePath == null) {
            treePath = new TreePath(customTreeModel.createPath(parent));
            this.pathCache.put(parent, treePath);
        }
        return treePath.pathByAddingChild(object);
    }
}
