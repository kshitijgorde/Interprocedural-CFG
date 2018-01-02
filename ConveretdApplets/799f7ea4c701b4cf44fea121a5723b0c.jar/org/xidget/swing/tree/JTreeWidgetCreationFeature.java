// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.tree;

import org.xidget.tree.Cell;
import java.awt.Rectangle;
import org.xmodel.xpath.expression.IContext;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.tree.TreeCellRenderer;
import java.awt.dnd.DropTarget;
import javax.swing.tree.TreeModel;
import javax.swing.JComponent;
import java.awt.Point;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IDragAndDropFeature;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetAdapter;
import java.util.List;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import javax.swing.event.TreeSelectionEvent;
import org.xidget.tree.Row;
import org.xidget.ifeature.tree.ITreeExpandFeature;
import javax.swing.event.TreeExpansionEvent;
import org.xidget.IXidget;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import java.awt.dnd.DropTargetListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionListener;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JTreeWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private TreeExpansionListener expandListener;
    private TreeSelectionListener selectionListener;
    private DropTargetListener dndListener;
    private JScrollPane jscrollPane;
    private JTree jtree;
    
    public JTreeWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.expandListener = new TreeExpansionListener() {
            @Override
            public void treeExpanded(final TreeExpansionEvent treeExpansionEvent) {
                JTreeWidgetCreationFeature.this.xidget.getFeature(ITreeExpandFeature.class).expand((Row)treeExpansionEvent.getPath().getLastPathComponent());
            }
            
            @Override
            public void treeCollapsed(final TreeExpansionEvent treeExpansionEvent) {
                JTreeWidgetCreationFeature.this.xidget.getFeature(ITreeExpandFeature.class).collapse((Row)treeExpansionEvent.getPath().getLastPathComponent());
            }
        };
        this.selectionListener = new TreeSelectionListener() {
            private boolean updating;
            
            @Override
            public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
                if (this.updating) {
                    return;
                }
                this.updating = true;
                try {
                    final ISelectionUpdateFeature selectionUpdateFeature = JTreeWidgetCreationFeature.this.xidget.getFeature(ISelectionUpdateFeature.class);
                    final TreePath[] paths = treeSelectionEvent.getPaths();
                    final ArrayList<TreePath> list = new ArrayList<TreePath>();
                    final ArrayList<TreePath> list2 = new ArrayList<TreePath>();
                    for (int i = 0; i < paths.length; ++i) {
                        if (treeSelectionEvent.isAddedPath(i)) {
                            list.add(paths[i]);
                        }
                        else {
                            list2.add(paths[i]);
                        }
                    }
                    List access$1;
                    for (int j = 0; j < list2.size(); j += access$1.size()) {
                        final IXidget tree = ((Row)list2.get(j).getLastPathComponent()).getTree(JTreeWidgetCreationFeature.this.xidget);
                        access$1 = JTreeWidgetCreationFeature.this.getNextSelectionGroup(treeSelectionEvent, list2, j);
                        selectionUpdateFeature.modelDeselect(access$1);
                        if (tree != JTreeWidgetCreationFeature.this.xidget) {
                            tree.getFeature(ISelectionUpdateFeature.class).modelDeselect(access$1);
                        }
                    }
                    List access$2;
                    for (int k = 0; k < list.size(); k += access$2.size()) {
                        final IXidget tree2 = ((Row)list.get(k).getLastPathComponent()).getTree(JTreeWidgetCreationFeature.this.xidget);
                        access$2 = JTreeWidgetCreationFeature.this.getNextSelectionGroup(treeSelectionEvent, list, k);
                        selectionUpdateFeature.modelSelect(access$2);
                        if (tree2 != JTreeWidgetCreationFeature.this.xidget) {
                            tree2.getFeature(ISelectionUpdateFeature.class).modelSelect(access$2);
                        }
                    }
                }
                finally {
                    this.updating = false;
                }
                this.updating = false;
            }
        };
        this.dndListener = new DropTargetAdapter() {
            @Override
            public void dragEnter(final DropTargetDragEvent dropTargetDragEvent) {
                if (JTreeWidgetCreationFeature.this.canDrop(dropTargetDragEvent.getLocation())) {
                    dropTargetDragEvent.acceptDrag(1);
                }
                else {
                    dropTargetDragEvent.rejectDrag();
                }
            }
            
            @Override
            public void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
                if (JTreeWidgetCreationFeature.this.canDrop(dropTargetDragEvent.getLocation())) {
                    dropTargetDragEvent.acceptDrag(1);
                }
                else {
                    dropTargetDragEvent.rejectDrag();
                }
            }
            
            @Override
            public void drop(final DropTargetDropEvent dropTargetDropEvent) {
                final StatefulContext access$3 = JTreeWidgetCreationFeature.this.createDropContext(dropTargetDropEvent.getLocation());
                final Point location = dropTargetDropEvent.getLocation();
                final Row row = (Row)JTreeWidgetCreationFeature.this.jtree.getClosestPathForLocation(location.x, location.y).getLastPathComponent();
                if (row != null) {
                    final IDragAndDropFeature dragAndDropFeature = row.getTable().getFeature(IDragAndDropFeature.class);
                    if (dragAndDropFeature != null && dragAndDropFeature.isDropEnabled()) {
                        dragAndDropFeature.drop(access$3);
                        return;
                    }
                }
                final IDragAndDropFeature dragAndDropFeature2 = JTreeWidgetCreationFeature.this.xidget.getFeature(IDragAndDropFeature.class);
                if (dragAndDropFeature2 != null && dragAndDropFeature2.isDropEnabled()) {
                    dragAndDropFeature2.drop(access$3);
                }
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        (this.jtree = new JTree(new CustomTreeModel(this.xidget))).setDragEnabled(true);
        try {
            this.jtree.setDropTarget(new DropTarget());
            this.jtree.getDropTarget().addDropTargetListener(this.dndListener);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        this.jtree.setCellRenderer(new CustomTreeCellRenderer());
        this.jtree.setShowsRootHandles(true);
        this.jtree.setRootVisible(false);
        this.jtree.putClientProperty("JTree.lineStyle", "Angled");
        this.jtree.addTreeExpansionListener(this.expandListener);
        this.jtree.addTreeSelectionListener(this.selectionListener);
        (this.jscrollPane = new JScrollPane(this.jtree)).setBorder(BorderFactory.createEmptyBorder());
        return this.jscrollPane;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jscrollPane, this.jtree };
    }
    
    public JScrollPane getJScrollPane() {
        return this.jscrollPane;
    }
    
    public JTree getJTree() {
        return this.jtree;
    }
    
    private List<Object> getNextSelectionGroup(final TreeSelectionEvent treeSelectionEvent, final List<TreePath> list, final int n) {
        final ArrayList<Object> list2 = new ArrayList<Object>();
        final IXidget tree = ((Row)list.get(n).getLastPathComponent()).getTree(this.xidget);
        for (int i = n; i < list.size(); ++i) {
            final Row row = (Row)list.get(i).getLastPathComponent();
            if (tree != row.getTree(this.xidget)) {
                break;
            }
            list2.add(row.getContext().getObject());
        }
        return list2;
    }
    
    private StatefulContext createDropContext(final Point point) {
        final TreePath closestPathForLocation = this.jtree.getClosestPathForLocation(point.x, point.y);
        if (closestPathForLocation == null) {
            return null;
        }
        final Row row = (Row)closestPathForLocation.getLastPathComponent();
        final int index = row.getParent().getChildren().indexOf(row);
        final int n = 0;
        final StatefulContext statefulContext = (row != null) ? row.getContext() : null;
        final StatefulContext statefulContext2 = new StatefulContext(statefulContext, statefulContext.getObject().getParent());
        statefulContext2.set("row", statefulContext.getObject());
        statefulContext2.set("rowIndex", index);
        statefulContext2.set("columnIndex", n);
        final Rectangle pathBounds = this.jtree.getPathBounds(closestPathForLocation);
        statefulContext2.set("insert", (point.y < pathBounds.y + pathBounds.height / 2) ? index : (index + 1));
        final Cell cell = row.getCell(n);
        statefulContext2.set("cell", (cell != null) ? cell.source : null);
        return statefulContext2;
    }
    
    private boolean canDrop(final Point point) {
        final StatefulContext dropContext = this.createDropContext(point);
        final TreePath closestPathForLocation = this.jtree.getClosestPathForLocation(point.x, point.y);
        if (closestPathForLocation == null) {
            return false;
        }
        final Row row = (Row)closestPathForLocation.getLastPathComponent();
        if (row != null) {
            final IDragAndDropFeature dragAndDropFeature = row.getTable().getFeature(IDragAndDropFeature.class);
            if (dragAndDropFeature != null && dragAndDropFeature.isDropEnabled()) {
                return dragAndDropFeature.canDrop(dropContext);
            }
        }
        final IDragAndDropFeature dragAndDropFeature2 = this.xidget.getFeature(IDragAndDropFeature.class);
        return dragAndDropFeature2 != null && dragAndDropFeature2.canDrop(dropContext);
    }
}
