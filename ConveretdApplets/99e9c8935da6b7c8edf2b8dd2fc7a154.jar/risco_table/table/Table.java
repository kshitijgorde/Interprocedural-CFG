// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import risco_table.util.ColumnSortable;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import risco_table.TableCell.ITableCell;
import java.beans.PropertyChangeEvent;
import java.awt.Graphics;
import java.awt.Font;
import risco_table.graphics.StringFormatter;
import java.awt.FontMetrics;
import java.awt.Component;
import risco_table.util.SwapListener;
import risco_table.util.ColumnSortableVector;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.PopupMenu;
import risco_table.graphics.BorderLowered2;
import java.awt.Scrollbar;
import java.util.Vector;
import risco_table.graphics.Border;
import java.awt.Color;
import java.awt.ItemSelectable;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeListener;
import java.awt.Container;

public class Table extends Container implements PropertyChangeListener, AdjustmentListener, ItemSelectable, ColumnSortListener
{
    public static final int VERTICAL_SCROLLBAR_OFF = 0;
    public static final int VERTICAL_SCROLLBAR_AUTOMATIC = 1;
    public static final int VERTICAL_SCROLLBAR_ON = 2;
    public static final int HORIZONTAL_SCROLLBAR_OFF = 0;
    public static final int HORIZONTAL_SCROLLBAR_AUTOMATIC = 1;
    public static final int HORIZONTAL_SCROLLBAR_ON = 2;
    private static Color defaultBackground;
    private static Color defaultForeground;
    private static Border defaultBorder;
    private Border border;
    private int borderTop;
    private int borderLeft;
    private int borderRight;
    private int borderBottom;
    private Vector rows;
    private Vector columns;
    private boolean widthsInitialized;
    private Titlebar titlebar;
    private boolean showTitlebar;
    private int titlebarSkip;
    private DataArea dataArea;
    private Scrollbar horizontalScrollbar;
    private Scrollbar verticalScrollbar;
    private int horizontalScrollMode;
    private int verticalScrollMode;
    private int verticalScrollbarWidth;
    private int verticalScrollbarSkip;
    private boolean hasVerticalScrollbar;
    private int horizontalScrollbarHeight;
    private int horizontalScrollbarSkip;
    private boolean hasHorizontalScrollbar;
    int w;
    int h;
    boolean isDragging;
    private int[] m_aOriginalColumnOrder;
    private int m_iButtonColumnToCheck;
    private String m_sButtonColumnTextToCheck;
    
    static {
        Table.defaultBackground = Color.white;
        Table.defaultForeground = Color.black;
        Table.defaultBorder = new BorderLowered2();
    }
    
    public Table() {
        this.widthsInitialized = false;
        this.showTitlebar = true;
        this.dataArea = null;
        this.horizontalScrollMode = 1;
        this.verticalScrollMode = 1;
        this.m_aOriginalColumnOrder = null;
        this.m_iButtonColumnToCheck = 2;
        this.m_sButtonColumnTextToCheck = "warning";
        this.columns = new Vector();
        this.rows = new Vector();
        this.initialize();
    }
    
    public Table(final Vector columnDefinitions) {
        this.widthsInitialized = false;
        this.showTitlebar = true;
        this.dataArea = null;
        this.horizontalScrollMode = 1;
        this.verticalScrollMode = 1;
        this.m_aOriginalColumnOrder = null;
        this.m_iButtonColumnToCheck = 2;
        this.m_sButtonColumnTextToCheck = "warning";
        this.columns = columnDefinitions;
        this.rows = new Vector();
        this.initialize();
    }
    
    public Table(final Vector columnDefinitions, final Vector rows) {
        this.widthsInitialized = false;
        this.showTitlebar = true;
        this.dataArea = null;
        this.horizontalScrollMode = 1;
        this.verticalScrollMode = 1;
        this.m_aOriginalColumnOrder = null;
        this.m_iButtonColumnToCheck = 2;
        this.m_sButtonColumnTextToCheck = "warning";
        this.columns = columnDefinitions;
        this.rows = rows;
        this.initialize();
    }
    
    public void add(final PopupMenu popup) {
        this.dataArea.add(popup);
    }
    
    public void addActionListener(final ActionListener listener) {
        this.dataArea.addActionListener(listener);
    }
    
    public void addItemListener(final ItemListener listener) {
        this.dataArea.addItemListener(listener);
    }
    
    public void addRow(final TableRow row) {
        synchronized (this.rows) {
            this.rows.addElement(row);
            this.rowsAdded(1);
        }
        // monitorexit(this.rows)
    }
    
    public void adjustColumnWidth(final int columnIndex) {
        final int[] widths = this.titlebar.getColumnWidths();
        widths[columnIndex] = this.measureColumnWidth(columnIndex);
        this.setColumnWidths(widths);
    }
    
    public void adjustColumnWidths() {
        final int[] widths = this.titlebar.getColumnWidths();
        for (int i = 0; i < widths.length; ++i) {
            widths[i] = this.measureColumnWidth(i);
        }
        this.setColumnWidths(widths);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        if (((Scrollbar)e.getSource()).getOrientation() == 0) {
            this.titlebar.setScrollPos(-e.getValue());
            this.titlebar.paint(this.titlebar.getGraphics());
        }
    }
    
    public void check(final int index) {
        this.check(index, true);
    }
    
    public void check(final int index, final boolean state) {
        synchronized (this.rows) {
            this.dataArea.check(index, state);
        }
        // monitorexit(this.rows)
    }
    
    public void checkAll() {
        synchronized (this.rows) {
            this.dataArea.checkAll();
        }
        // monitorexit(this.rows)
    }
    
    public void checkIndexes(final int[] indexes) throws IllegalArgumentException {
        synchronized (this.rows) {
            this.dataArea.setCheckedIndexes(indexes);
        }
        // monitorexit(this.rows)
    }
    
    private void checkScrollbars() {
        final Dimension dataDim = this.dataArea.getMinimumSize();
        final int insideWidth = this.getSize().width - this.borderLeft - this.borderRight;
        final int insideHeight = this.getSize().height - this.borderTop - this.borderBottom;
        if (this.horizontalScrollMode == 0) {
            this.hasHorizontalScrollbar = false;
        }
        else if (this.horizontalScrollMode == 2) {
            this.hasHorizontalScrollbar = true;
        }
        else if (dataDim.width <= insideWidth - this.verticalScrollbarWidth) {
            this.hasHorizontalScrollbar = false;
        }
        else if (dataDim.width > insideWidth) {
            this.hasHorizontalScrollbar = true;
        }
        else if (this.verticalScrollMode == 2) {
            this.hasHorizontalScrollbar = true;
        }
        else if (this.verticalScrollMode == 0) {
            this.hasHorizontalScrollbar = false;
        }
        else if (this.rows.size() * dataDim.height > insideHeight - this.titlebarSkip) {
            this.hasHorizontalScrollbar = true;
        }
        else {
            this.hasHorizontalScrollbar = false;
        }
        this.horizontalScrollbarSkip = (this.hasHorizontalScrollbar ? this.horizontalScrollbarHeight : 0);
        if (this.verticalScrollMode == 0) {
            this.hasVerticalScrollbar = false;
        }
        else if (this.verticalScrollMode == 2) {
            this.hasVerticalScrollbar = true;
        }
        else if (this.rows.size() * dataDim.height > insideHeight - this.titlebarSkip - this.horizontalScrollbarSkip) {
            this.hasVerticalScrollbar = true;
        }
        else {
            this.hasVerticalScrollbar = false;
        }
        this.verticalScrollbarSkip = (this.hasVerticalScrollbar ? this.verticalScrollbarWidth : 0);
    }
    
    private void checkTitlebar() {
        final Dimension titlebarSize = this.titlebar.getPreferredSize();
        if (this.showTitlebar) {
            this.titlebarSkip = titlebarSize.height;
        }
        else {
            this.titlebarSkip = 0;
        }
    }
    
    public void deselect(final int index) {
        this.select(index, false);
    }
    
    public void deselectAll() {
        synchronized (this.rows) {
            this.dataArea.deselectAll(true);
        }
        // monitorexit(this.rows)
    }
    
    public Color getBackground() {
        final Color c = super.getBackground();
        if (c == null) {
            return Table.defaultBackground;
        }
        return c;
    }
    
    public Border getBorder() {
        return this.border;
    }
    
    public int[] getCheckedIndexes() {
        synchronized (this.rows) {
            // monitorexit(this.rows)
            return this.dataArea.getCheckedIndexes();
        }
    }
    
    public int[] getColumnWidths() {
        return this.titlebar.getColumnWidths();
    }
    
    public DataArea getDataArea() {
        return this.dataArea;
    }
    
    public static Border getDefaultBorder() {
        return Table.defaultBorder;
    }
    
    public Color getForeground() {
        final Color c = super.getForeground();
        if (c == null) {
            return Table.defaultForeground;
        }
        return c;
    }
    
    public int getHorizontalScrollMode() {
        return this.horizontalScrollMode;
    }
    
    public Dimension getMinimumSize() {
        final Dimension title = this.titlebar.getMinimumSize();
        final Dimension data = this.dataArea.getMinimumSize();
        final int titleHeight = this.showTitlebar ? title.height : 0;
        return new Dimension(title.width + this.verticalScrollbarWidth + this.borderLeft + this.borderRight, titleHeight + data.height + this.horizontalScrollbarHeight + this.borderTop + this.borderBottom);
    }
    
    public Dimension getPreferredSize() {
        final Dimension title = this.titlebar.getPreferredSize();
        final Dimension data = this.dataArea.getPreferredSize();
        final int titleHeight = this.showTitlebar ? title.height : 0;
        return new Dimension(title.width + this.borderLeft + this.borderRight + this.verticalScrollbarWidth, titleHeight + data.height + this.borderTop + this.borderBottom + this.horizontalScrollbarHeight);
    }
    
    public Vector getRows() {
        return this.rows;
    }
    
    public int getSelectedIndex() {
        synchronized (this.rows) {
            // monitorexit(this.rows)
            return this.dataArea.getSelectedIndex();
        }
    }
    
    public int[] getSelectedIndexes() {
        synchronized (this.rows) {
            // monitorexit(this.rows)
            return this.dataArea.getSelectedIndexes();
        }
    }
    
    public Object getSelectedObject() {
        synchronized (this.rows) {
            final int i = this.dataArea.getSelectedIndex();
            if (i != -1) {
                // monitorexit(this.rows)
                return this.rows.elementAt(i);
            }
            // monitorexit(this.rows)
            return null;
        }
    }
    
    public Object[] getSelectedObjects() {
        return this.dataArea.getSelectedObjects();
    }
    
    public int getSelectionMode() {
        return this.dataArea.getSelectionMode();
    }
    
    public boolean getShowCheckmarks() {
        return this.dataArea.getShowCheckmarks();
    }
    
    public boolean getShowGrid() {
        return this.dataArea.getShowGrid();
    }
    
    public boolean getShowLineNumbers() {
        return this.dataArea.getShowLineNumbers();
    }
    
    public boolean getShowTitlebar() {
        return this.showTitlebar;
    }
    
    public Titlebar getTitlebar() {
        return this.titlebar;
    }
    
    public int getVerticalScrollMode() {
        return this.verticalScrollMode;
    }
    
    private void initialize() {
        this.setLayout(null);
        this.setBorder(Table.defaultBorder);
        if (this.horizontalScrollMode != 0) {
            this.horizontalScrollbar = new Scrollbar(0);
        }
        if (this.verticalScrollMode != 0) {
            this.verticalScrollbar = new Scrollbar(1);
        }
        (this.dataArea = new DataArea(this, this.verticalScrollbar, this.horizontalScrollbar, this.columns, this.rows)).addAdjustmentListener(this);
        this.titlebar = new Titlebar(this.columns);
        if (this.rows instanceof ColumnSortableVector) {
            this.titlebar.setCanSort(true);
            this.titlebar.addColumnSortListener(this);
            ((ColumnSortableVector)this.rows).addSwapListener(this.dataArea);
        }
        this.titlebar.addPropertyChangeListener(this);
        this.titlebar.setReferenceColumnWidth(this.dataArea.getReferenceColumnWidth());
        this.add(this.titlebar);
        this.add(this.dataArea);
        if (this.horizontalScrollbar != null) {
            this.add(this.horizontalScrollbar);
        }
        if (this.verticalScrollbar != null) {
            this.add(this.verticalScrollbar);
        }
    }
    
    private void initScrollbarHeight() {
        if (this.horizontalScrollbarHeight == 0 && this.horizontalScrollbar != null) {
            this.horizontalScrollbarHeight = this.horizontalScrollbar.getPreferredSize().height;
        }
    }
    
    private void initScrollbarWidth() {
        if (this.verticalScrollbarWidth == 0 && this.verticalScrollbar != null) {
            this.verticalScrollbarWidth = this.verticalScrollbar.getPreferredSize().width;
        }
    }
    
    public boolean isChecked(final int index) {
        synchronized (this.rows) {
            // monitorexit(this.rows)
            return this.dataArea.isChecked(index);
        }
    }
    
    public boolean isSelected(final int index) {
        synchronized (this.rows) {
            // monitorexit(this.rows)
            return this.dataArea.isSelected(index);
        }
    }
    
    private int measureColumnWidth(final int columnIndex) {
        final ColumnDefinition cd = this.columns.elementAt(columnIndex);
        int width = 0;
        switch (cd.getWidthAdjustment()) {
            case 0: {
                width = this.titlebar.actualColumnWidth(cd.getPreferredWidth(null, null));
                break;
            }
            case 1: {
                width = this.titlebar.actualColumnWidth(cd.getPreferredWidth(this.getFontMetrics(this.dataArea.getFont()), null));
                break;
            }
            case 2: {
                width = this.titlebar.actualColumnWidth(cd.getPreferredWidth(this.getFontMetrics(this.titlebar.getFont()), Titlebar.getDefaultFormatter()));
                break;
            }
            default: {
                StringFormatter formatter = cd.getDataFormatter();
                if (formatter == null) {
                    formatter = DataArea.getDefaultFormatter();
                }
                Font f = cd.getDataFont();
                if (f == null) {
                    f = this.dataArea.getFont();
                }
                FontMetrics fontMetrics = this.getFontMetrics(f);
                synchronized (this.rows) {
                    for (int i = 0; i < this.rows.size(); ++i) {
                        final String s = this.rows.elementAt(i).getStrings()[columnIndex].toString();
                        formatter.measureString(fontMetrics, s, 0, 0, 32767, 32767);
                        if (formatter.getPreferredWidth() > width) {
                            width = formatter.getPreferredWidth();
                        }
                    }
                }
                // monitorexit(this.rows)
                if (!this.showTitlebar) {
                    width = this.dataArea.actualColumnWidth(width);
                    break;
                }
                formatter = cd.getTitleFormatter();
                if (formatter == null) {
                    formatter = Titlebar.getDefaultFormatter();
                }
                f = cd.getTitleFont();
                if (f == null) {
                    f = this.titlebar.getFont();
                }
                fontMetrics = this.getFontMetrics(f);
                formatter.measureString(fontMetrics, cd.getTitle(), 0, 0, width, 32767);
                final int w = formatter.getPreferredWidth();
                if (w > width) {
                    width = this.titlebar.actualColumnWidth(w);
                    break;
                }
                width = this.titlebar.actualColumnWidth(width);
                break;
            }
        }
        return width;
    }
    
    public void paint(final Graphics g) {
        if (this.isDragging) {
            return;
        }
        this.initScrollbarHeight();
        this.initScrollbarWidth();
        synchronized (this.rows) {
            this.sizeComponents();
            if (!this.widthsInitialized) {
                this.adjustColumnWidths();
                this.widthsInitialized = true;
            }
            this.readjustHorizontalScrolling(false);
            this.readjustVerticalScrolling();
            this.paintComponents();
        }
        // monitorexit(this.rows)
        this.border.paint(g, 0, 0, this.w, this.h);
    }
    
    private void paintComponents() {
        if (this.showTitlebar) {
            if (!this.isDragging) {
                this.titlebar.paint(this.titlebar.getGraphics());
            }
        }
        else {
            this.titlebar.adjustColumns(this.dataArea.getSize().width);
        }
        this.dataArea.paint(this.dataArea.getGraphics());
        if (this.hasVerticalScrollbar && this.hasHorizontalScrollbar) {
            final Graphics g = this.getGraphics();
            g.setColor(this.getBackground());
            g.fillRect(this.w - this.borderRight - this.verticalScrollbarWidth, this.h - this.borderBottom - this.horizontalScrollbarHeight, this.verticalScrollbarWidth, this.horizontalScrollbarHeight);
        }
    }
    
    public void propertyChange(final PropertyChangeEvent e) {
        if (e.getSource() == this.titlebar) {
            synchronized (this.rows) {
                if (e.getPropertyName() == "mouseStateChangeEvent" && (this.dataArea.getSelectionMode() == 2 || this.dataArea.getSelectionMode() == 3) && (int)e.getNewValue() <= this.dataArea.getReferenceColumnWidth() - 5) {
                    this.dataArea.selectAll();
                }
                if (e.getPropertyName() == "ColumnWidthsChanged") {
                    this.dataArea.setColumnWidths(this.titlebar.getColumnWidths());
                    this.sizeComponents();
                    this.paintComponents();
                }
                else if (e.getPropertyName() == "draggingStateChanged") {
                    if ((int)e.getNewValue() >= 0) {
                        this.isDragging = true;
                    }
                    else {
                        this.readjustHorizontalScrolling(true);
                        this.paintComponents();
                        this.isDragging = false;
                    }
                }
                else if (e.getPropertyName() == "COLUMN_SWAP_EVENT") {
                    final int iNewVal = (int)e.getNewValue();
                    final int iOldVal = (int)e.getOldValue();
                    int iDir = 1;
                    if (iOldVal > iNewVal) {
                        iDir = -1;
                    }
                    for (int j = 0; j < this.rows.size(); ++j) {
                        final TableRow row = this.rows.elementAt(j);
                        final ITableCell[] aStrings = row.getStrings();
                        for (int i = iOldVal; i != iNewVal; i += iDir) {
                            final ITableCell oTableCell = aStrings[i];
                            aStrings[i] = aStrings[i + iDir];
                            aStrings[i + iDir] = oTableCell;
                        }
                    }
                    for (int k = iOldVal; k != iNewVal; k += iDir) {
                        final int iTmp = this.m_aOriginalColumnOrder[k];
                        this.m_aOriginalColumnOrder[k] = this.m_aOriginalColumnOrder[k + iDir];
                        this.m_aOriginalColumnOrder[k + iDir] = iTmp;
                    }
                    this.dataArea.paint(this.dataArea.getGraphics());
                }
            }
            // monitorexit(this.rows)
        }
    }
    
    private void readjustHorizontalScrolling(final boolean repaint) {
        if (this.titlebar.getScrollPos() != 0) {
            final int actualWidth = this.titlebar.getSize().width;
            final int preferredWidth = this.titlebar.getPreferredSize().width;
            if (this.titlebar.getScrollPos() + preferredWidth < actualWidth) {
                int newValue;
                if (preferredWidth <= actualWidth) {
                    newValue = 0;
                }
                else {
                    newValue = actualWidth - preferredWidth;
                }
                this.titlebar.setScrollPos(newValue);
                this.dataArea.setHorizontalScrollPos(newValue);
                if (this.horizontalScrollbar != null) {
                    this.horizontalScrollbar.setValue(-newValue);
                }
                if (repaint) {
                    this.paintComponents();
                }
            }
        }
    }
    
    private void readjustVerticalScrolling() {
        if (this.dataArea.getVerticalScrollPos() != 0) {
            final int lineCount = this.rows.size();
            final int visible = this.dataArea.getCompletelyVisibleCount();
            if (this.dataArea.getVerticalScrollPos() + lineCount < visible) {
                int newValue;
                if (lineCount <= visible) {
                    newValue = 0;
                }
                else {
                    newValue = visible - lineCount;
                }
                this.dataArea.setVerticalScrollPos(newValue);
                if (this.verticalScrollbar != null) {
                    this.verticalScrollbar.setValue(-newValue);
                }
            }
        }
    }
    
    public void removeActionListener(final ActionListener listener) {
        this.dataArea.removeActionListener(listener);
    }
    
    public void removeAllRows() {
        synchronized (this.rows) {
            final int count = this.rows.size();
            this.rows.removeAllElements();
            this.rowsRemoved(0, count);
        }
        // monitorexit(this.rows)
    }
    
    public void removeItemListener(final ItemListener listener) {
        this.dataArea.removeItemListener(listener);
    }
    
    public void removeRowsByIndex(final int[] indexes) {
        synchronized (this.rows) {
            for (int i = indexes.length - 1; i >= 0; --i) {
                this.rows.removeElementAt(indexes[i]);
                this.dataArea.rowsRemoved(indexes[i], indexes[i] + 1);
            }
            this.dataArea.updateVerticalScrollbar();
            boolean repaint = true;
            if (this.verticalScrollMode == 1) {
                final boolean oldHasVerticalScrollbar = this.hasVerticalScrollbar;
                this.checkScrollbars();
                if (oldHasVerticalScrollbar != this.hasVerticalScrollbar) {
                    this.sizeComponents();
                    repaint = false;
                }
            }
            if (repaint) {
                this.dataArea.paintVisibleRows(this.dataArea.getGraphics());
            }
        }
        // monitorexit(this.rows)
    }
    
    public void removeSelectedRows() {
        synchronized (this.rows) {
            this.removeRowsByIndex(this.dataArea.getSelectedIndexes());
        }
        // monitorexit(this.rows)
    }
    
    public void repaint(final long l, final int x, final int y, final int w, final int h) {
        if (!this.isDragging) {
            super.repaint(l, x, y, w, h);
        }
    }
    
    public void rowsAdded(final int count) {
        synchronized (this.rows) {
            this.rowsInserted(this.rows.size() - count, count);
        }
        // monitorexit(this.rows)
    }
    
    public void rowsInserted(final int startEntry, final int count) {
        synchronized (this.rows) {
            boolean repaint = true;
            final int pastEntry = startEntry + count;
            this.dataArea.rowsInserted(startEntry, pastEntry);
            this.dataArea.updateVerticalScrollbar();
            if (this.verticalScrollMode == 1) {
                final boolean oldHasVerticalScrollbar = this.hasVerticalScrollbar;
                this.checkScrollbars();
                if (oldHasVerticalScrollbar != this.hasVerticalScrollbar) {
                    this.sizeComponents();
                    repaint = false;
                }
            }
            if (repaint && startEntry >= -this.dataArea.getVerticalScrollPos() && startEntry <= -this.dataArea.getVerticalScrollPos() + this.dataArea.getVisibleCount()) {
                this.dataArea.paintVisibleRows(this.dataArea.getGraphics());
            }
        }
        // monitorexit(this.rows)
    }
    
    public void rowsRemoved(final int count) {
        synchronized (this.rows) {
            this.rowsRemoved(this.rows.size(), count);
        }
        // monitorexit(this.rows)
    }
    
    public void rowsRemoved(final int startEntry, final int count) {
        synchronized (this.rows) {
            final int pastEntry = startEntry + count;
            boolean repaint = true;
            this.dataArea.rowsRemoved(startEntry, pastEntry);
            this.dataArea.updateVerticalScrollbar();
            if (this.verticalScrollMode == 1) {
                final boolean oldHasVerticalScrollbar = this.hasVerticalScrollbar;
                this.checkScrollbars();
                if (oldHasVerticalScrollbar != this.hasVerticalScrollbar) {
                    this.sizeComponents();
                    repaint = false;
                }
            }
            if (repaint && pastEntry > -this.dataArea.getVerticalScrollPos() && startEntry <= -this.dataArea.getVerticalScrollPos() + this.dataArea.getVisibleCount()) {
                this.dataArea.paintVisibleRows(this.dataArea.getGraphics());
            }
        }
        // monitorexit(this.rows)
    }
    
    public void rowsUpdated(final int startRow, final int count) {
        synchronized (this.rows) {
            final int pastRow = startRow + count;
            if (pastRow > -this.dataArea.getVerticalScrollPos() && startRow <= -this.dataArea.getVerticalScrollPos() + this.dataArea.getVisibleCount()) {
                this.dataArea.paintVisibleRows(this.dataArea.getGraphics());
            }
        }
        // monitorexit(this.rows)
    }
    
    public void scrollToRow(final int row) {
        this.dataArea.scrollToRow(row);
    }
    
    public void select(final int index) {
        this.select(index, true);
    }
    
    public void select(final int index, final boolean state) throws IllegalArgumentException {
        synchronized (this.rows) {
            final int mode = this.dataArea.getSelectionMode();
            if (state) {
                final int curSelection = this.dataArea.getSelectedIndex();
                if (mode == 1 || mode == 0) {
                    if (curSelection != index) {
                        if (curSelection >= 0) {
                            this.dataArea.select(curSelection, false);
                        }
                        this.dataArea.moveFocus(index);
                        this.dataArea.select(index, state);
                        this.dataArea.fireItemEvent(new ItemEvent(this, 701, this.rows.elementAt(index), 1));
                    }
                }
                else {
                    this.dataArea.select(index, state);
                }
            }
            else if (mode != 1) {
                this.dataArea.select(index, state);
                if (mode == 0) {
                    this.dataArea.fireItemEvent(new ItemEvent(this, 701, this.rows.elementAt(index), 2));
                }
            }
        }
        // monitorexit(this.rows)
    }
    
    public void selectAll() {
        synchronized (this.rows) {
            this.dataArea.selectAll();
        }
        // monitorexit(this.rows)
    }
    
    public void selectIndexes(final int[] indexes) throws IllegalArgumentException {
        synchronized (this.rows) {
            this.dataArea.setSelectedIndexes(indexes);
        }
        // monitorexit(this.rows)
    }
    
    public void setBorder(final Border border) {
        this.border = border;
        final Insets insets = border.getInsets();
        this.borderTop = insets.top;
        this.borderLeft = insets.left;
        this.borderRight = insets.right;
        this.borderBottom = insets.bottom;
    }
    
    public void setCheckedIndexes(final int[] indexes) throws IllegalArgumentException {
        synchronized (this.rows) {
            this.dataArea.setCheckedIndexes(indexes);
        }
        // monitorexit(this.rows)
    }
    
    public void setColumnWidths(final int[] widths) {
        this.titlebar.setColumnWidths(widths);
        this.dataArea.setColumnWidths(widths);
        this.repaint();
    }
    
    public static void setDefaultBorder(final Border border) {
        Table.defaultBorder = border;
    }
    
    public void setEnabled(final boolean enable) {
        super.setEnabled(enable);
        if (this.horizontalScrollbar != null) {
            this.horizontalScrollbar.setEnabled(enable);
        }
        if (this.verticalScrollbar != null) {
            this.verticalScrollbar.setEnabled(enable);
        }
        if (this.titlebar != null) {
            this.titlebar.setEnabled(enable);
        }
        if (this.dataArea != null) {
            this.dataArea.setEnabled(enable);
        }
    }
    
    public void setHorizontalScrollMode(final int scrollMode) {
        this.horizontalScrollMode = scrollMode;
    }
    
    public void setRows(final Vector data) {
        synchronized (this.rows) {
            final Vector oldRows = this.rows;
            this.rows = data;
            ((ColumnSortableVector)this.rows).addSwapListener(this.dataArea);
            this.dataArea.setRows(data);
            if (oldRows.size() < this.rows.size()) {
                this.rowsAdded(this.rows.size() - oldRows.size());
            }
            else if (oldRows.size() > this.rows.size()) {
                this.rowsRemoved(oldRows.size() - this.rows.size());
            }
            else {
                this.repaint();
            }
        }
        // monitorexit(this.rows)
    }
    
    public void setSelectionMode(final int mode) {
        synchronized (this.rows) {
            this.dataArea.setSelectionMode(mode);
        }
        // monitorexit(this.rows)
    }
    
    public void setShowCheckmarks(final boolean b) {
        this.dataArea.setShowCheckmarks(b);
        this.titlebar.setReferenceColumnWidth(this.dataArea.getReferenceColumnWidth());
    }
    
    public void setShowButtons(final boolean b) {
        this.dataArea.setShowButtons(b);
        this.titlebar.setReferenceColumnWidth(this.dataArea.getReferenceColumnWidth());
        if (b) {
            for (int i = 0; i < this.rows.size(); ++i) {
                final String sTemp = this.rows.elementAt(i).getStrings()[this.m_iButtonColumnToCheck].toString().trim().toLowerCase();
                if (sTemp.compareTo(this.m_sButtonColumnTextToCheck) == 0) {
                    this.getDataArea().setShowButtonAcceptBitset(i, true);
                }
            }
        }
    }
    
    public void setButtonColumnToCheck(final int i) {
        this.m_iButtonColumnToCheck = i;
    }
    
    public void setButtonColumnTextToCheck(final String s) {
        this.m_sButtonColumnTextToCheck = s;
    }
    
    public void setShowGrid(final boolean b) {
        this.dataArea.setShowGrid(b);
    }
    
    public void setShowLineNumbers(final boolean b) {
        this.dataArea.setShowLineNumbers(b);
        this.titlebar.setReferenceColumnWidth(this.dataArea.getReferenceColumnWidth());
    }
    
    public void setShowTitlebar(final boolean b) {
        if (this.showTitlebar != b) {
            this.showTitlebar = b;
            this.titlebar.setVisible(b);
            if (this.isVisible()) {
                this.repaint();
            }
        }
    }
    
    public void setSorting(final int column, final boolean ascending) {
        this.titlebar.setSortColumn(column);
        this.titlebar.setSortAscending(ascending);
    }
    
    public void setTableData(final Vector columns, final Vector rows) {
        this.titlebar.setColumns(columns, null);
        this.columns = columns;
        this.rows = rows;
        this.initialize();
        this.m_aOriginalColumnOrder = new int[columns.size()];
        for (int i = 0; i < columns.size(); ++i) {
            this.m_aOriginalColumnOrder[i] = i;
        }
    }
    
    public int getOriginalColumnIndex(final int iVal) {
        if (this.m_aOriginalColumnOrder != null && this.m_aOriginalColumnOrder.length > 0 && iVal >= 0 && iVal < this.m_aOriginalColumnOrder.length) {
            for (int i = 0; i < this.m_aOriginalColumnOrder.length; ++i) {
                if (iVal == this.m_aOriginalColumnOrder[i]) {
                    return i;
                }
            }
        }
        return iVal;
    }
    
    public void setVerticalScrollMode(final int scrollMode) {
        this.verticalScrollMode = scrollMode;
    }
    
    private void sizeComponents() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.checkTitlebar();
        this.checkScrollbars();
        if (this.showTitlebar) {
            this.titlebar.setVisible(true);
            this.titlebar.setBounds(this.borderLeft, this.borderTop, this.w - this.borderLeft - this.borderRight - this.verticalScrollbarSkip, this.titlebarSkip);
        }
        else {
            this.titlebar.setVisible(false);
        }
        if (this.horizontalScrollbar != null) {
            this.horizontalScrollbar.setBounds(this.borderLeft, this.h - this.borderBottom - this.horizontalScrollbarHeight, this.w - this.borderLeft - this.borderRight - this.verticalScrollbarSkip, this.horizontalScrollbarHeight);
        }
        if (this.hasHorizontalScrollbar) {
            this.horizontalScrollbar.setVisible(true);
        }
        else {
            this.horizontalScrollbar.setVisible(false);
        }
        if (this.verticalScrollbar != null) {
            this.verticalScrollbar.setBounds(this.w - this.borderRight - this.verticalScrollbarWidth, this.borderTop, this.verticalScrollbarWidth, this.h - this.borderTop - this.borderBottom - this.horizontalScrollbarSkip);
        }
        if (this.hasVerticalScrollbar) {
            this.dataArea.setBounds(this.borderLeft, this.borderTop + this.titlebarSkip, this.w - this.borderLeft - this.borderRight - this.verticalScrollbarSkip, this.h - this.borderTop - this.borderBottom - this.horizontalScrollbarSkip - this.titlebarSkip);
            this.verticalScrollbar.setVisible(true);
        }
        else {
            this.verticalScrollbar.setVisible(false);
            this.dataArea.setBounds(this.borderLeft, this.borderTop + this.titlebarSkip, this.w - this.borderLeft - this.borderRight - this.verticalScrollbarSkip, this.h - this.borderTop - this.borderBottom - this.horizontalScrollbarSkip - this.titlebarSkip);
        }
    }
    
    public void sortColumn(final ColumnSortEvent e) {
        if (this.rows.size() > 1) {
            final ColumnSortableVector v = (ColumnSortableVector)this.rows;
            v.setAscending(e.getAscending());
            Label_0148: {
                if (e.getMultisort()) {
                    this.titlebar.setMultisort(true);
                    this.titlebar.setSortColumn(e.getColumnIndexes());
                    this.titlebar.setSortAscending(e.getAscendingFlags());
                    synchronized (v) {
                        v.sort(e.getColumnIndexes(), e.getColumnTypes(), e.getAscendingFlags(), e.getDateFormats(), v.elementAt(0));
                        // monitorexit(v)
                        break Label_0148;
                    }
                }
                this.titlebar.setMultisort(false);
                synchronized (v) {
                    v.sort(e.getColumnIndex(), v.elementAt(0), e.getColumnType(), e.getDateFormat());
                }
                // monitorexit(v)
            }
            this.dataArea.repaint();
            if (this.dataArea.getSelectionMode() == 1) {
                if (!this.rows.isEmpty()) {
                    this.dataArea.deselectAll(false);
                    this.dataArea.select(0);
                    this.dataArea.moveFocus(0);
                }
            }
            else if (this.dataArea.getSelectionMode() == 0 && !this.rows.isEmpty()) {
                this.dataArea.deselectAll(false);
                this.dataArea.moveFocus(0);
            }
        }
    }
    
    public void uncheck(final int index) {
        this.check(index, false);
    }
    
    public void uncheckAll() {
        synchronized (this.rows) {
            this.dataArea.uncheckAll(true);
        }
        // monitorexit(this.rows)
    }
}
