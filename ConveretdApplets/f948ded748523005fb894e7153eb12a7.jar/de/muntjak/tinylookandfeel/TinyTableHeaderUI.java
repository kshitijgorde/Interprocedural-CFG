// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Component;
import de.muntjak.tinylookandfeel.controlpanel.TinyTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.table.TableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableColumnModelEvent;
import java.awt.event.MouseEvent;
import de.muntjak.tinylookandfeel.table.SortableTableData;
import java.awt.Point;
import java.util.Vector;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.event.TableColumnModelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import de.muntjak.tinylookandfeel.table.TinyTableHeaderRenderer;
import java.util.HashMap;
import javax.swing.plaf.basic.BasicTableHeaderUI;

public class TinyTableHeaderUI extends BasicTableHeaderUI
{
    public static final String ROLLOVER_COLUMN_KEY = "rolloverColumn";
    public static final String SORTED_COLUMN_KEY = "sortedColumn";
    public static final String SORTING_DIRECTION_KEY = "sortingDirection";
    private static final int ADD_COLUMN = 0;
    private static final int REMOVE_COLUMN = 1;
    private static final int REPLACE_COLUMN = 2;
    private static final int MINIMUM_DRAG_DISTANCE = 5;
    private static final HashMap sortingCache;
    protected SortableTableHandler handler;
    protected TinyTableHeaderRenderer headerRenderer;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$javax$swing$Icon;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$String;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTableHeaderUI();
    }
    
    protected void installListeners() {
        super.installListeners();
        this.headerRenderer = new TinyTableHeaderRenderer();
        this.header.setDefaultRenderer(this.headerRenderer);
        this.handler = new SortableTableHandler();
        this.header.addMouseListener(this.handler);
        this.header.addMouseMotionListener(this.handler);
        this.header.getColumnModel().addColumnModelListener(this.handler);
        final SortingInformation sortingInformation = TinyTableHeaderUI.sortingCache.get(this.header);
        if (sortingInformation != null) {
            this.handler.restoreSortingInformation(this.header, sortingInformation);
        }
    }
    
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.handler.removeSortingInformation();
        this.header.removeMouseListener(this.handler);
        this.header.removeMouseMotionListener(this.handler);
        this.header.getColumnModel().removeColumnModelListener(this.handler);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        final Dimension preferredSize = super.getPreferredSize(component);
        preferredSize.height = Math.max(16, preferredSize.height);
        return preferredSize;
    }
    
    public void sortColumns(final int[] array, final int[] array2, final JTable table) {
        if (this.handler == null) {
            return;
        }
        this.handler.sortColumns(array, array2, table);
    }
    
    public void setHorizontalAlignments(final int[] horizontalAlignments) {
        if (this.headerRenderer == null) {
            return;
        }
        this.headerRenderer.setHorizontalAlignments(horizontalAlignments);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        sortingCache = new HashMap();
    }
    
    private class SortableTableHandler implements MouseListener, MouseMotionListener, TableColumnModelListener
    {
        private int rolloverColumn;
        private int pressedColumn;
        private Vector sortedViewColumns;
        private Vector sortedModelColumns;
        private Vector sortingDirections;
        private boolean mouseInside;
        private boolean mouseDragged;
        private boolean inDrag;
        private Point pressedPoint;
        
        private SortableTableHandler() {
            this.rolloverColumn = -1;
            this.pressedColumn = -1;
            this.sortedViewColumns = new Vector();
            this.sortedModelColumns = new Vector();
            this.sortingDirections = new Vector();
            this.mouseInside = false;
            this.mouseDragged = false;
            this.inDrag = false;
        }
        
        void sortColumns(final int[] array, final int[] array2, final JTable table) {
            if (array == null) {
                throw new IllegalArgumentException("columns argument may not be null");
            }
            if (array2 == null) {
                throw new IllegalArgumentException("directions argument may not be null");
            }
            if (array.length != array2.length) {
                throw new IllegalArgumentException("columns argument and directions argument must be of equal length");
            }
            if (array.length > table.getColumnCount()) {
                throw new IllegalArgumentException("Length of columns argument is greater than number of table columns");
            }
            final JTableHeader tableHeader = table.getTableHeader();
            final SortableTableData tableModel = this.getTableModel(tableHeader);
            if (tableModel == null) {
                return;
            }
            this.sortedViewColumns.clear();
            this.sortedModelColumns.clear();
            this.sortingDirections.clear();
            for (int i = 0; i < array.length; ++i) {
                this.sortedViewColumns.add(new Integer(array[i]));
                this.sortedModelColumns.add(new Integer(this.getModelColumn(tableHeader, array[i])));
                this.sortingDirections.add(new Integer(array2[i]));
            }
            tableHeader.putClientProperty("sortedColumn", this.vectorToIntArray(this.sortedViewColumns));
            tableHeader.putClientProperty("sortingDirection", this.vectorToIntArray(this.sortingDirections));
            tableModel.sortColumns(this.vectorToIntArray(this.sortedModelColumns), this.vectorToIntArray(this.sortingDirections), table);
            tableHeader.repaint();
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            this.mouseInside = true;
            if (this.mouseDragged) {
                return;
            }
            final SortableTableData tableModel = this.getTableModel(mouseEvent.getSource());
            if (tableModel == null) {
                return;
            }
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
            if (!tableModel.isColumnSortable(this.getModelColumnAt(mouseEvent))) {
                if (this.rolloverColumn != -1) {
                    this.rolloverColumn = -1;
                    tableHeader.putClientProperty("rolloverColumn", null);
                }
            }
            else {
                this.rolloverColumn = columnAtPoint;
                tableHeader.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
            }
            tableHeader.repaint();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            this.mouseInside = false;
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            if (this.inDrag && tableHeader.getReorderingAllowed()) {
                return;
            }
            if (this.getTableModel(mouseEvent.getSource()) == null) {
                return;
            }
            if (this.rolloverColumn != -1) {
                this.rolloverColumn = -1;
                tableHeader.putClientProperty("rolloverColumn", null);
                tableHeader.repaint();
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.inDrag = false;
            if (mouseEvent.isPopupTrigger()) {
                return;
            }
            if (!this.mouseInside) {
                this.mouseDragged = false;
                return;
            }
            final SortableTableData tableModel = this.getTableModel(mouseEvent.getSource());
            if (tableModel == null) {
                this.mouseDragged = false;
                return;
            }
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
            if (columnAtPoint == -1) {
                this.mouseDragged = false;
                return;
            }
            final int modelColumn = this.getModelColumnAt(mouseEvent);
            if (!tableModel.isColumnSortable(modelColumn)) {
                if (this.rolloverColumn != -1) {
                    this.rolloverColumn = -1;
                    tableHeader.putClientProperty("rolloverColumn", null);
                }
            }
            else {
                this.rolloverColumn = columnAtPoint;
                tableHeader.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
            }
            if (this.mouseDragged) {
                this.mouseDragged = false;
                return;
            }
            if (!tableModel.isColumnSortable(modelColumn)) {
                return;
            }
            if (this.pressedColumn != columnAtPoint) {
                return;
            }
            final Integer n = new Integer(columnAtPoint);
            if (this.sortedViewColumns.contains(n)) {
                final int index = this.sortedViewColumns.indexOf(n);
                if (mouseEvent.isAltDown()) {
                    this.sortedViewColumns.remove(index);
                    this.sortedModelColumns.remove(index);
                    this.sortingDirections.remove(index);
                }
                else if ((mouseEvent.isControlDown() && tableModel.supportsMultiColumnSort()) || this.sortedModelColumns.size() == 1) {
                    int n2;
                    if ((int)this.sortingDirections.get(index) != 2) {
                        n2 = 2;
                    }
                    else {
                        n2 = 1;
                    }
                    this.sortingDirections.remove(index);
                    this.sortingDirections.add(index, new Integer(n2));
                }
                else {
                    int n3;
                    if ((int)this.sortingDirections.get(index) != 2) {
                        n3 = 2;
                    }
                    else {
                        n3 = 1;
                    }
                    this.sortedViewColumns.clear();
                    this.sortedModelColumns.clear();
                    this.sortingDirections.clear();
                    this.sortedViewColumns.add(n);
                    this.sortedModelColumns.add(new Integer(this.getModelColumn(mouseEvent, columnAtPoint)));
                    this.sortingDirections.add(new Integer(n3));
                }
            }
            else {
                if (mouseEvent.isAltDown()) {
                    return;
                }
                if (mouseEvent.isControlDown() && tableModel.supportsMultiColumnSort()) {
                    this.sortedViewColumns.add(n);
                    this.sortedModelColumns.add(new Integer(this.getModelColumn(mouseEvent, columnAtPoint)));
                    this.sortingDirections.add(new Integer(1));
                }
                else {
                    this.sortedViewColumns.clear();
                    this.sortedModelColumns.clear();
                    this.sortingDirections.clear();
                    this.sortedViewColumns.add(n);
                    this.sortedModelColumns.add(new Integer(this.getModelColumn(mouseEvent, columnAtPoint)));
                    this.sortingDirections.add(new Integer(1));
                }
            }
            tableHeader.putClientProperty("sortedColumn", this.vectorToIntArray(this.sortedViewColumns));
            tableHeader.putClientProperty("sortingDirection", this.vectorToIntArray(this.sortingDirections));
            tableModel.sortColumns(this.vectorToIntArray(this.sortedModelColumns), this.vectorToIntArray(this.sortingDirections), tableHeader.getTable());
            tableHeader.repaint();
        }
        
        private int[] vectorToIntArray(final Vector vector) {
            final int[] array = new int[vector.size()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = vector.get(i);
            }
            return array;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.isPopupTrigger()) {
                return;
            }
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            this.pressedPoint = mouseEvent.getPoint();
            this.pressedColumn = tableHeader.columnAtPoint(this.pressedPoint);
            this.mouseDragged = false;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            final SortableTableData tableModel = this.getTableModel(mouseEvent.getSource());
            if (tableModel == null) {
                return;
            }
            this.inDrag = true;
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            if (tableHeader.getResizingColumn() != null && !this.mouseDragged) {
                this.mouseDragged = true;
            }
            if (!tableHeader.getReorderingAllowed() && !tableModel.isColumnSortable(this.getModelColumnAt(mouseEvent))) {
                tableHeader.putClientProperty("rolloverColumn", null);
                tableHeader.repaint();
                return;
            }
            if (!this.mouseDragged && this.isMouseDragged(mouseEvent.getPoint(), this.pressedPoint)) {
                this.mouseDragged = true;
            }
            if (!this.mouseInside) {
                tableHeader.putClientProperty("rolloverColumn", null);
            }
            else {
                if (!tableHeader.getReorderingAllowed()) {
                    final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
                    if (columnAtPoint != this.rolloverColumn) {
                        this.rolloverColumn = columnAtPoint;
                    }
                }
                if (this.rolloverColumn != -1) {
                    tableHeader.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
                }
            }
            tableHeader.repaint();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (!this.mouseInside) {
                return;
            }
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
            if (columnAtPoint == -1) {
                return;
            }
            final SortableTableData tableModel = this.getTableModel(mouseEvent.getSource());
            if (tableModel == null) {
                return;
            }
            if (!tableModel.isColumnSortable(this.getModelColumnAt(mouseEvent))) {
                if (this.rolloverColumn != -1) {
                    this.rolloverColumn = -1;
                    tableHeader.putClientProperty("rolloverColumn", null);
                    tableHeader.repaint();
                }
                return;
            }
            if (columnAtPoint != this.rolloverColumn) {
                this.rolloverColumn = columnAtPoint;
                tableHeader.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
                tableHeader.repaint();
            }
        }
        
        public void columnAdded(final TableColumnModelEvent tableColumnModelEvent) {
            this.removeSorting();
        }
        
        public void columnMoved(final TableColumnModelEvent tableColumnModelEvent) {
            if (tableColumnModelEvent.getFromIndex() == tableColumnModelEvent.getToIndex()) {
                return;
            }
            if (TinyTableHeaderUI.this.header == null) {
                return;
            }
            if (this.rolloverColumn == tableColumnModelEvent.getFromIndex()) {
                this.rolloverColumn = tableColumnModelEvent.getToIndex();
                if (this.mouseInside) {
                    TinyTableHeaderUI.this.header.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
                }
            }
            final int[] vectorToIntArray = this.vectorToIntArray(this.sortedViewColumns);
            boolean b = false;
            for (int i = 0; i < vectorToIntArray.length; ++i) {
                if (vectorToIntArray[i] == tableColumnModelEvent.getFromIndex()) {
                    vectorToIntArray[i] = tableColumnModelEvent.getToIndex();
                    b = true;
                }
                else if (vectorToIntArray[i] == tableColumnModelEvent.getToIndex()) {
                    vectorToIntArray[i] = tableColumnModelEvent.getFromIndex();
                    b = true;
                }
            }
            if (b) {
                this.sortedViewColumns.clear();
                for (int j = 0; j < vectorToIntArray.length; ++j) {
                    this.sortedViewColumns.add(new Integer(vectorToIntArray[j]));
                }
                TinyTableHeaderUI.this.header.putClientProperty("sortedColumn", this.vectorToIntArray(this.sortedViewColumns));
            }
        }
        
        public void columnRemoved(final TableColumnModelEvent tableColumnModelEvent) {
            this.removeSorting();
        }
        
        public void columnMarginChanged(final ChangeEvent changeEvent) {
        }
        
        public void columnSelectionChanged(final ListSelectionEvent listSelectionEvent) {
        }
        
        private void removeSorting() {
            if (TinyTableHeaderUI.this.header == null) {
                return;
            }
            if (this.rolloverColumn != -1) {
                this.rolloverColumn = -1;
                TinyTableHeaderUI.this.header.putClientProperty("rolloverColumn", new Integer(this.rolloverColumn));
            }
            this.sortedModelColumns.clear();
            this.sortedViewColumns.clear();
            this.sortingDirections.clear();
            TinyTableHeaderUI.this.header.putClientProperty("sortingDirection", null);
            TinyTableHeaderUI.this.header.putClientProperty("sortedColumn", null);
            TinyTableHeaderUI.this.header.repaint();
        }
        
        void removeSortingInformation() {
            if (TinyTableHeaderUI.this.header == null) {
                return;
            }
            final SortableTableData tableModel = this.getTableModel(TinyTableHeaderUI.this.header);
            if (tableModel == null) {
                return;
            }
            TinyTableHeaderUI.sortingCache.put(TinyTableHeaderUI.this.header, new SortingInformation(this.sortedViewColumns, this.sortedModelColumns, this.sortingDirections));
            tableModel.sortColumns(new int[0], new int[0], TinyTableHeaderUI.this.header.getTable());
            TinyTableHeaderUI.this.header.repaint();
        }
        
        void restoreSortingInformation(final JTableHeader tableHeader, final SortingInformation sortingInformation) {
            if (tableHeader == null) {
                return;
            }
            final SortableTableData tableModel = this.getTableModel(tableHeader);
            if (tableModel == null) {
                return;
            }
            this.sortedViewColumns = sortingInformation.sortedViewColumns;
            this.sortedModelColumns = sortingInformation.sortedModelColumns;
            this.sortingDirections = sortingInformation.sortingDirections;
            tableModel.sortColumns(this.vectorToIntArray(this.sortedModelColumns), this.vectorToIntArray(this.sortingDirections), tableHeader.getTable());
            tableHeader.repaint();
        }
        
        private SortableTableData getTableModel(final Object o) {
            return this.getTableModel((JTableHeader)o);
        }
        
        private SortableTableData getTableModel(final JTableHeader tableHeader) {
            final JTable table = tableHeader.getTable();
            if (table == null) {
                return null;
            }
            final TableModel model = table.getModel();
            if (!(model instanceof SortableTableData)) {
                return null;
            }
            return (SortableTableData)model;
        }
        
        private int getModelColumnAt(final MouseEvent mouseEvent) {
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
            if (columnAtPoint == -1) {
                return -1;
            }
            return tableHeader.getColumnModel().getColumn(columnAtPoint).getModelIndex();
        }
        
        private int getModelColumn(final MouseEvent mouseEvent, final int n) {
            if (n == -1) {
                return -1;
            }
            return this.getModelColumn((JTableHeader)mouseEvent.getSource(), n);
        }
        
        private int getModelColumn(final JTableHeader tableHeader, final int n) {
            return tableHeader.getColumnModel().getColumn(n).getModelIndex();
        }
        
        private boolean isMouseDragged(final Point point, final Point point2) {
            return Math.abs(point.x - point2.x) >= 5;
        }
        
        private void showHeaderPopup(final MouseEvent mouseEvent) {
            final SortableTableData tableModel = this.getTableModel(mouseEvent.getSource());
            if (tableModel == null) {
                return;
            }
            final JTableHeader tableHeader = (JTableHeader)mouseEvent.getSource();
            final int columnAtPoint = tableHeader.columnAtPoint(mouseEvent.getPoint());
            final JPopupMenu popupMenu = new JPopupMenu();
            final JMenu menu = new JMenu("Add");
            final JMenuItem menuItem = new JMenuItem("Double Column");
            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).addColumn((TinyTableHeaderUI.class$java$lang$Double == null) ? (TinyTableHeaderUI.class$java$lang$Double = TinyTableHeaderUI.class$("java.lang.Double")) : TinyTableHeaderUI.class$java$lang$Double, columnAtPoint);
                }
            });
            menu.add(menuItem);
            final JMenuItem menuItem2 = new JMenuItem("Icon Column");
            menuItem2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).addColumn((TinyTableHeaderUI.class$javax$swing$Icon == null) ? (TinyTableHeaderUI.class$javax$swing$Icon = TinyTableHeaderUI.class$("javax.swing.Icon")) : TinyTableHeaderUI.class$javax$swing$Icon, columnAtPoint);
                }
            });
            menu.add(menuItem2);
            final JMenuItem menuItem3 = new JMenuItem("Integer Column");
            menuItem3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).addColumn((TinyTableHeaderUI.class$java$lang$Integer == null) ? (TinyTableHeaderUI.class$java$lang$Integer = TinyTableHeaderUI.class$("java.lang.Integer")) : TinyTableHeaderUI.class$java$lang$Integer, columnAtPoint);
                }
            });
            menu.add(menuItem3);
            final JMenuItem menuItem4 = new JMenuItem("String Column");
            menuItem4.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).addColumn((TinyTableHeaderUI.class$java$lang$String == null) ? (TinyTableHeaderUI.class$java$lang$String = TinyTableHeaderUI.class$("java.lang.String")) : TinyTableHeaderUI.class$java$lang$String, columnAtPoint);
                }
            });
            menu.add(menuItem4);
            popupMenu.add(menu);
            popupMenu.addSeparator();
            final JMenuItem menuItem5 = new JMenuItem("Remove Column");
            menuItem5.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).removeColumn(columnAtPoint);
                }
            });
            if (((TinyTableModel)tableModel).getColumnCount() < 2) {
                menuItem5.setEnabled(false);
            }
            popupMenu.add(menuItem5);
            popupMenu.addSeparator();
            final JMenuItem menuItem6 = new JMenuItem("Remove all Rows");
            menuItem6.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).removeAllRows();
                }
            });
            if (((TinyTableModel)tableModel).getRowCount() == 0) {
                menuItem6.setEnabled(false);
            }
            popupMenu.add(menuItem6);
            popupMenu.addSeparator();
            final JMenuItem menuItem7 = new JMenuItem("Create new Data");
            menuItem7.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    ((TinyTableModel)tableModel).createNewData();
                }
            });
            if (((TinyTableModel)tableModel).getRowCount() > 0) {
                menuItem7.setEnabled(false);
            }
            popupMenu.add(menuItem7);
            popupMenu.show(tableHeader, mouseEvent.getX(), 0);
        }
    }
    
    private class SortingInformation
    {
        private Vector sortedViewColumns;
        private Vector sortedModelColumns;
        private Vector sortingDirections;
        
        SortingInformation(final Vector sortedViewColumns, final Vector sortedModelColumns, final Vector sortingDirections) {
            this.sortedViewColumns = sortedViewColumns;
            this.sortedModelColumns = sortedModelColumns;
            this.sortingDirections = sortingDirections;
        }
    }
}
