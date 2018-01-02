// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import javax.swing.table.TableColumnModel;
import org.xidget.tree.Cell;
import java.awt.Rectangle;
import org.xmodel.xpath.expression.IContext;
import java.util.List;
import java.awt.Point;
import java.util.Iterator;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.feature.tree.ColumnWidthFeature;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.xmodel.IModelObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.dnd.DropTarget;
import javax.swing.table.TableModel;
import javax.swing.JComponent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.event.ChangeEvent;
import java.awt.Insets;
import org.xidget.ifeature.tree.IColumnWidthFeature;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import org.xidget.tree.Row;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IDragAndDropFeature;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetAdapter;
import javax.swing.event.ListSelectionEvent;
import org.xidget.IXidget;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.TableColumnModelListener;
import java.awt.event.ComponentListener;
import java.awt.dnd.DropTargetListener;
import javax.swing.event.ListSelectionListener;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JTableWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private ListSelectionListener selectionListener;
    private DropTargetListener dndListener;
    private ComponentListener componentListener;
    private TableColumnModelListener columnResizeListener;
    private JScrollPane jscrollPane;
    private JTable jtable;
    
    public JTableWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.selectionListener = new ListSelectionListener() {
            private boolean updating;
            
            @Override
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (this.updating) {
                    return;
                }
                this.updating = true;
                try {
                    JTableWidgetCreationFeature.this.updateSelection();
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
                if (JTableWidgetCreationFeature.this.canDrop(dropTargetDragEvent.getLocation())) {
                    dropTargetDragEvent.acceptDrag(1);
                }
                else {
                    dropTargetDragEvent.rejectDrag();
                }
            }
            
            @Override
            public void dragOver(final DropTargetDragEvent dropTargetDragEvent) {
                if (JTableWidgetCreationFeature.this.canDrop(dropTargetDragEvent.getLocation())) {
                    dropTargetDragEvent.acceptDrag(1);
                }
                else {
                    dropTargetDragEvent.rejectDrag();
                }
            }
            
            @Override
            public void drop(final DropTargetDropEvent dropTargetDropEvent) {
                final StatefulContext access$1 = JTableWidgetCreationFeature.this.createDropContext(dropTargetDropEvent.getLocation());
                final Row access$2 = JTableWidgetCreationFeature.this.getRowAt(dropTargetDropEvent.getLocation());
                if (access$2 != null) {
                    final IDragAndDropFeature dragAndDropFeature = access$2.getTable().getFeature(IDragAndDropFeature.class);
                    if (dragAndDropFeature != null && dragAndDropFeature.isDropEnabled()) {
                        dragAndDropFeature.drop(access$1);
                        return;
                    }
                }
                final IDragAndDropFeature dragAndDropFeature2 = JTableWidgetCreationFeature.this.xidget.getFeature(IDragAndDropFeature.class);
                if (dragAndDropFeature2 != null && dragAndDropFeature2.isDropEnabled()) {
                    dragAndDropFeature2.drop(access$1);
                }
            }
        };
        this.componentListener = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent componentEvent) {
                final IColumnWidthFeature columnWidthFeature = JTableWidgetCreationFeature.this.xidget.getFeature(IColumnWidthFeature.class);
                final Insets insets = JTableWidgetCreationFeature.this.jscrollPane.getInsets();
                columnWidthFeature.setTotalWidth(JTableWidgetCreationFeature.this.jscrollPane.getWidth() - insets.left - insets.right);
            }
        };
        this.columnResizeListener = new TableColumnModelListener() {
            @Override
            public void columnMarginChanged(final ChangeEvent changeEvent) {
                final TableColumn resizingColumn = JTableWidgetCreationFeature.this.jtable.getTableHeader().getResizingColumn();
                if (resizingColumn == null) {
                    return;
                }
                final int access$6 = JTableWidgetCreationFeature.this.getColumnIndex(resizingColumn);
                if (access$6 < 0) {
                    return;
                }
                final IColumnWidthFeature columnWidthFeature = JTableWidgetCreationFeature.this.xidget.getFeature(IColumnWidthFeature.class);
                final int width = resizingColumn.getWidth();
                if (width > 0 && width != columnWidthFeature.getWidth(access$6) && columnWidthFeature.isResizeable(access$6)) {
                    columnWidthFeature.setFreeWidth(access$6, width, width, 0);
                }
                final Insets insets = JTableWidgetCreationFeature.this.jscrollPane.getInsets();
                columnWidthFeature.setTotalWidth(JTableWidgetCreationFeature.this.jscrollPane.getWidth() - insets.left - insets.right);
            }
            
            @Override
            public void columnAdded(final TableColumnModelEvent tableColumnModelEvent) {
            }
            
            @Override
            public void columnMoved(final TableColumnModelEvent tableColumnModelEvent) {
            }
            
            @Override
            public void columnRemoved(final TableColumnModelEvent tableColumnModelEvent) {
            }
            
            @Override
            public void columnSelectionChanged(final ListSelectionEvent listSelectionEvent) {
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        (this.jtable = new JTable(new CustomTableModel(this.xidget))).setDragEnabled(true);
        this.jtable.setRowHeight(24);
        this.jtable.setDropTarget(new DropTarget(this.jtable, this.dndListener));
        this.jtable.setShowGrid(true);
        this.jtable.setShowHorizontalLines(true);
        this.jtable.setShowVerticalLines(true);
        this.jtable.setGridColor(Color.LIGHT_GRAY);
        this.jtable.getTableHeader().setVisible(false);
        this.jtable.getTableHeader().setReorderingAllowed(false);
        this.jtable.setDefaultRenderer(IModelObject.class, new CustomCellRenderer());
        this.jtable.setDefaultEditor(IModelObject.class, new CustomCellEditor());
        this.jtable.getSelectionModel().addListSelectionListener(this.selectionListener);
        this.jscrollPane = new JScrollPane(this.jtable);
        this.xidget.getFeature((Class<ColumnWidthFeature>)IColumnWidthFeature.class).configure(this.xidget);
        this.jtable.setAutoResizeMode(0);
        this.jtable.addComponentListener(this.componentListener);
        this.jscrollPane.addComponentListener(this.componentListener);
        this.jtable.getColumnModel().addColumnModelListener(this.columnResizeListener);
        return this.jscrollPane;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.jscrollPane, this.jtable };
    }
    
    public JScrollPane getJScrollPane() {
        return this.jscrollPane;
    }
    
    public JTable getJTable() {
        return this.jtable;
    }
    
    public void updateSelection() {
        this.xidget.getFeature(ISelectionUpdateFeature.class).updateModel();
        final Iterator<IXidget> iterator = this.xidget.getChildren().iterator();
        while (iterator.hasNext()) {
            final ISelectionUpdateFeature selectionUpdateFeature = iterator.next().getFeature(ISelectionUpdateFeature.class);
            if (selectionUpdateFeature != null) {
                selectionUpdateFeature.updateModel();
            }
        }
    }
    
    private Row getRowAt(final Point point) {
        final int rowAtPoint = this.jtable.rowAtPoint(point);
        if (rowAtPoint < 0) {
            return null;
        }
        final List<Row> rows = ((CustomTableModel)this.jtable.getModel()).getRows();
        if (rowAtPoint >= rows.size()) {
            return null;
        }
        return rows.get(rowAtPoint);
    }
    
    private StatefulContext createDropContext(final Point point) {
        final int rowAtPoint = this.jtable.rowAtPoint(point);
        final int columnAtPoint = this.jtable.columnAtPoint(point);
        final Row row = this.getRowAt(point);
        final StatefulContext statefulContext = (row != null) ? row.getContext() : null;
        final StatefulContext statefulContext2 = new StatefulContext(statefulContext, statefulContext.getObject().getParent());
        statefulContext2.set("row", statefulContext.getObject());
        statefulContext2.set("rowIndex", rowAtPoint);
        statefulContext2.set("columnIndex", columnAtPoint);
        final Rectangle cellRect = this.jtable.getCellRect(rowAtPoint, columnAtPoint, false);
        statefulContext2.set("insert", (point.y < cellRect.y + cellRect.height / 2) ? rowAtPoint : (rowAtPoint + 1));
        final Cell cell = row.getCell(columnAtPoint);
        statefulContext2.set("cell", (cell != null) ? cell.source : null);
        return statefulContext2;
    }
    
    private boolean canDrop(final Point point) {
        final StatefulContext dropContext = this.createDropContext(point);
        final Row row = this.getRowAt(point);
        if (row != null) {
            final IDragAndDropFeature dragAndDropFeature = row.getTable().getFeature(IDragAndDropFeature.class);
            if (dragAndDropFeature != null && dragAndDropFeature.isDropEnabled()) {
                return dragAndDropFeature.canDrop(dropContext);
            }
        }
        final IDragAndDropFeature dragAndDropFeature2 = this.xidget.getFeature(IDragAndDropFeature.class);
        return dragAndDropFeature2 != null && dragAndDropFeature2.canDrop(dropContext);
    }
    
    private int getColumnIndex(final TableColumn tableColumn) {
        final TableColumnModel columnModel = this.jtable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); ++i) {
            if (tableColumn == columnModel.getColumn(i)) {
                return i;
            }
        }
        return -1;
    }
}
