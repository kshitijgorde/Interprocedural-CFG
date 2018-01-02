// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import java.awt.Insets;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import risco_table.graphics.BorderSingle;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import risco_table.graphics.BorderRaisedWithLine;
import java.beans.PropertyChangeSupport;
import java.awt.Cursor;
import risco_table.graphics.DragConstraints;
import risco_table.graphics.SortIcon;
import risco_table.graphics.StringFormatter;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import risco_table.graphics.Border;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class Titlebar extends Component implements MouseListener, MouseMotionListener
{
    private boolean m_bAllowColumnDragDrop;
    public static final String dimensionChangedEvent = "DimensionChanged";
    public static final String columnWidthsChangedEvent = "ColumnWidthsChanged";
    public static final String draggingStateChangedEvent = "draggingStateChanged";
    public static final String mouseStateChangeEvent = "mouseStateChangeEvent";
    public static final int NOT_DEFINED = -1;
    public static final int LEFT_MARGIN = 4;
    public static final int RIGHT_MARGIN = 4;
    public static final int TOP_MARGIN = 1;
    public static final int BOTTOM_MARGIN = 1;
    public static final int DRAG_CURSOR_RANGE = 5;
    private static Border border;
    private static int borderTop;
    private static int borderLeft;
    private static int borderRight;
    private static int borderBottom;
    private Vector columns;
    private int[] columnWidths;
    private boolean[] columnResizeableFlags;
    private boolean[] columnAdjustableFlags;
    private boolean[] columnSortableFlags;
    private int sortColumn;
    private boolean sortAscending;
    private boolean canSort;
    private boolean adjustToFit;
    private int scrollPos;
    private boolean doubleBuffer;
    private Image offScreen;
    private int minimumColumnWidth;
    private int adjustmentIndex;
    private String m_sOnSort;
    private int barWidth;
    private static Color defaultBackground;
    private static Color defaultForeground;
    private static Font defaultFont;
    private static StringFormatter defaultFormatter;
    private SortIcon sortIcon;
    private int referenceColumnWidth;
    private int w;
    private int h;
    private int lastDragX;
    private int savePreferredWidth;
    private int savePreferredHeight;
    private int dragIndex;
    private int mouseOverIndex;
    private boolean mouseOverSortFeedback;
    private DragConstraints dragConstraints;
    private Cursor savedCursor;
    private PropertyChangeSupport propertyChange;
    private ColumnSortListener columnSortListener;
    private boolean multisort;
    private boolean[] ascendingFlags;
    private int[] sortColumns;
    private int m_iStartColumnX;
    private int m_iEndColumnX;
    private int m_iDragColumnIndex;
    private int m_iMouseX;
    private boolean m_bDragging;
    public static final String COLUMN_SWAP_EVENT = "COLUMN_SWAP_EVENT";
    
    static {
        setBorder(new BorderRaisedWithLine());
        Titlebar.defaultBackground = Color.lightGray;
        Titlebar.defaultForeground = Color.black;
        Titlebar.defaultFont = new Font("SansSerif", 0, 10);
        Titlebar.defaultFormatter = new StringFormatter();
    }
    
    public void setAllowColumnDragDrop(final boolean bVal) {
        this.m_bAllowColumnDragDrop = bVal;
    }
    
    public boolean getAllowColumnDragDrop() {
        return this.m_bAllowColumnDragDrop;
    }
    
    private void setSortParams(final int x) {
        int pos = this.scrollPos + this.referenceColumnWidth;
        for (int i = 0; i < this.columnWidths.length; ++i) {
            if (x > pos + 5 && x < pos + this.columnWidths[i] - 5) {
                this.m_iStartColumnX = pos + 5;
                this.m_iEndColumnX = pos + this.columnWidths[i] - 5;
                if (this.columnSortableFlags == null || this.columnSortableFlags[i]) {
                    return;
                }
            }
            pos += this.columnWidths[i];
        }
    }
    
    private int findDragColumnIndex(final int x) {
        int pos = this.scrollPos + this.referenceColumnWidth;
        for (int i = 0; i < this.columnWidths.length; ++i) {
            if (x > pos && x < pos + this.columnWidths[i]) {
                return i;
            }
            pos += this.columnWidths[i];
        }
        return -1;
    }
    
    public Vector getColumns() {
        return this.columns;
    }
    
    public void setMultisort(final boolean msort) {
        if (!(this.multisort = msort)) {
            this.sortColumns = null;
            this.ascendingFlags = null;
        }
    }
    
    public void setSortColumn(final int[] columns) {
        this.sortColumns = columns;
        this.sortColumn = columns[0];
        this.setMultisort(true);
    }
    
    public void setSortAscending(final boolean[] asc) {
        this.ascendingFlags = asc;
        this.sortAscending = this.ascendingFlags[0];
        this.setMultisort(true);
    }
    
    public int[] getSortColumnMulti() {
        return this.sortColumns;
    }
    
    public boolean[] getSortAscendingMulti() {
        return this.ascendingFlags;
    }
    
    public boolean getMultisort() {
        return this.multisort;
    }
    
    private final boolean isSortColumn(final int col) {
        if (this.multisort) {
            for (int k = 0; k < this.sortColumns.length; ++k) {
                if (this.sortColumns[k] == col) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private final boolean isAscendingColumn(final int col) {
        if (this.multisort) {
            for (int k = 0; k < this.sortColumns.length; ++k) {
                if (this.sortColumns[k] == col) {
                    return this.ascendingFlags[k];
                }
            }
        }
        return false;
    }
    
    public Titlebar() {
        this.m_bAllowColumnDragDrop = true;
        this.columns = null;
        this.columnWidths = null;
        this.columnResizeableFlags = null;
        this.columnAdjustableFlags = null;
        this.columnSortableFlags = null;
        this.sortColumn = -1;
        this.sortAscending = true;
        this.canSort = false;
        this.adjustToFit = true;
        this.scrollPos = 0;
        this.doubleBuffer = true;
        this.offScreen = null;
        this.minimumColumnWidth = 5;
        this.adjustmentIndex = 0;
        this.m_sOnSort = "";
        this.barWidth = -1;
        this.dragIndex = -1;
        this.mouseOverIndex = -1;
        this.mouseOverSortFeedback = true;
        this.propertyChange = new PropertyChangeSupport(this);
        this.columnSortListener = null;
        this.multisort = false;
        this.m_iStartColumnX = -1;
        this.m_iEndColumnX = -1;
        this.m_iDragColumnIndex = -1;
        this.m_iMouseX = -1;
        this.m_bDragging = false;
        this.initialize();
    }
    
    public Titlebar(final Vector columns) {
        this.m_bAllowColumnDragDrop = true;
        this.columns = null;
        this.columnWidths = null;
        this.columnResizeableFlags = null;
        this.columnAdjustableFlags = null;
        this.columnSortableFlags = null;
        this.sortColumn = -1;
        this.sortAscending = true;
        this.canSort = false;
        this.adjustToFit = true;
        this.scrollPos = 0;
        this.doubleBuffer = true;
        this.offScreen = null;
        this.minimumColumnWidth = 5;
        this.adjustmentIndex = 0;
        this.m_sOnSort = "";
        this.barWidth = -1;
        this.dragIndex = -1;
        this.mouseOverIndex = -1;
        this.mouseOverSortFeedback = true;
        this.propertyChange = new PropertyChangeSupport(this);
        this.columnSortListener = null;
        this.multisort = false;
        this.m_iStartColumnX = -1;
        this.m_iEndColumnX = -1;
        this.m_iDragColumnIndex = -1;
        this.m_iMouseX = -1;
        this.m_bDragging = false;
        this.columns = columns;
        this.columnAdjustableFlags = new boolean[columns.size()];
        this.initialize();
    }
    
    int actualColumnWidth(final int netWidth) {
        return netWidth + 4 + 4 + Titlebar.borderLeft + Titlebar.borderRight;
    }
    
    public void addColumnSortListener(final ColumnSortListener listener) {
        this.columnSortListener = listener;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.addPropertyChangeListener(listener);
    }
    
    void adjustColumns(final int w) {
        if (!this.adjustToFit) {
            return;
        }
        final int startColumn = this.dragIndex + 1;
        int availableWidth = w - this.getReferenceColumnWidth();
        int preferredWidth = 0;
        int adjustableColumnCount = this.columnWidths.length - startColumn;
        for (int i = 0; i < startColumn; ++i) {
            availableWidth -= this.columnWidths[i];
            this.columnAdjustableFlags[i] = false;
        }
        for (int i = startColumn; i < this.columnWidths.length; ++i) {
            preferredWidth += this.columnWidths[i];
            if (this.columnResizeableFlags != null && !this.columnResizeableFlags[i]) {
                this.columnAdjustableFlags[i] = false;
                --adjustableColumnCount;
            }
            else {
                this.columnAdjustableFlags[i] = true;
            }
        }
        if (adjustableColumnCount == 0) {
            return;
        }
        if (preferredWidth < availableWidth) {
            final int stretchAmount = (availableWidth - preferredWidth) / adjustableColumnCount;
            int remainder = (availableWidth - preferredWidth) % adjustableColumnCount;
            for (int j = startColumn; j < this.columnWidths.length; ++j) {
                if (this.columnAdjustableFlags[j]) {
                    final int[] columnWidths = this.columnWidths;
                    final int n2 = j;
                    columnWidths[n2] += stretchAmount;
                    if (remainder > 0) {
                        final int[] columnWidths2 = this.columnWidths;
                        final int n3 = j;
                        columnWidths2[n3] += remainder;
                        remainder = 0;
                    }
                }
            }
        }
        else if (preferredWidth > availableWidth) {
            int widthToReduce = preferredWidth - availableWidth;
            final int n = this.columnWidths.length - startColumn;
            final int shrinkAmount = widthToReduce / adjustableColumnCount;
            int remainder2 = widthToReduce % adjustableColumnCount;
            while (widthToReduce > 0 && adjustableColumnCount > 0) {
                final int currentCol = this.adjustmentIndex % n + startColumn;
                if (this.columnAdjustableFlags[currentCol]) {
                    if (this.columnWidths[currentCol] - remainder2 >= this.minimumColumnWidth) {
                        widthToReduce -= remainder2;
                        final int[] columnWidths3 = this.columnWidths;
                        final int n4 = currentCol;
                        columnWidths3[n4] -= remainder2;
                        remainder2 = 0;
                        if (this.columnWidths[currentCol] - shrinkAmount >= this.minimumColumnWidth) {
                            widthToReduce -= shrinkAmount;
                            final int[] columnWidths4 = this.columnWidths;
                            final int n5 = currentCol;
                            columnWidths4[n5] -= shrinkAmount;
                        }
                        else {
                            widthToReduce -= this.columnWidths[currentCol] - this.minimumColumnWidth;
                            remainder2 += shrinkAmount - (this.columnWidths[currentCol] - this.minimumColumnWidth);
                            this.columnWidths[currentCol] = this.minimumColumnWidth;
                        }
                    }
                    else {
                        widthToReduce -= this.columnWidths[currentCol] - this.minimumColumnWidth;
                        remainder2 -= this.columnWidths[currentCol] - this.minimumColumnWidth;
                        this.columnWidths[currentCol] = this.minimumColumnWidth;
                    }
                    if (this.columnWidths[currentCol] == this.minimumColumnWidth) {
                        --adjustableColumnCount;
                        this.columnAdjustableFlags[currentCol] = false;
                    }
                }
                ++this.adjustmentIndex;
            }
        }
    }
    
    private void dragColumn(final int col, final int dragX) {
        final int[] columnWidths = this.columnWidths;
        columnWidths[col] += dragX;
        this.paint(this.getGraphics());
        this.propertyChange.firePropertyChange("ColumnWidthsChanged", null, null);
    }
    
    private void eraseRight(final Graphics g, final int x) {
        g.setColor(this.getBackground());
        g.fillRect(x, 0, this.w - x, this.h);
    }
    
    public void finalize() throws Throwable {
        this.flush();
        super.finalize();
    }
    
    private int findDragIndex(final int x) {
        int lastIndex = -1;
        int localDragXPos = this.scrollPos + this.referenceColumnWidth;
        for (int i = 0; i < this.columnWidths.length; ++i) {
            localDragXPos += this.columnWidths[i];
            if ((this.columnResizeableFlags == null || this.columnResizeableFlags[i]) && x > localDragXPos - 5 && x < localDragXPos + 5) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }
    
    private int findSortIndex(final int x) {
        int pos = this.scrollPos + this.referenceColumnWidth;
        int i = 0;
        while (i < this.columnWidths.length) {
            if (x > pos + 5 && x < pos + this.columnWidths[i] - 5) {
                if (this.columnSortableFlags == null || this.columnSortableFlags[i]) {
                    return i;
                }
                return -1;
            }
            else {
                pos += this.columnWidths[i];
                ++i;
            }
        }
        return -1;
    }
    
    public void flush() {
        if (this.offScreen != null) {
            this.offScreen.flush();
            this.offScreen = null;
        }
    }
    
    public boolean getAdjustToFit() {
        return this.adjustToFit;
    }
    
    public Color getBackground() {
        final Color c = super.getBackground();
        if (c == null) {
            return Titlebar.defaultBackground;
        }
        return c;
    }
    
    public int getBarWidth() {
        return this.barWidth;
    }
    
    public boolean getCanSort() {
        return this.canSort;
    }
    
    public boolean[] getColumnResizeableFlags() {
        return this.columnResizeableFlags;
    }
    
    public boolean[] getColumnSortableFlags() {
        return this.columnSortableFlags;
    }
    
    public int[] getColumnWidths() {
        return this.columnWidths;
    }
    
    public static Color getDefaultBackground() {
        return Titlebar.defaultBackground;
    }
    
    public static Font getDefaultFont() {
        return Titlebar.defaultFont;
    }
    
    public static Color getDefaultForeground() {
        return Titlebar.defaultForeground;
    }
    
    public static StringFormatter getDefaultFormatter() {
        return Titlebar.defaultFormatter;
    }
    
    public boolean getDoubleBuffer() {
        return this.doubleBuffer;
    }
    
    public Font getFont() {
        final Font f = super.getFont();
        if (f == null) {
            return Titlebar.defaultFont;
        }
        return f;
    }
    
    public Color getForeground() {
        final Color c = super.getForeground();
        if (c == null) {
            return Titlebar.defaultForeground;
        }
        return c;
    }
    
    public int getMinimumColumnWidth() {
        return this.minimumColumnWidth;
    }
    
    public Dimension getMinimumSize() {
        int width;
        if (this.barWidth == -1) {
            width = 8 + Titlebar.borderLeft + Titlebar.borderRight + this.referenceColumnWidth;
        }
        else {
            width = this.barWidth;
        }
        final int height = 2 + Titlebar.borderTop + Titlebar.borderBottom;
        return new Dimension(width, height);
    }
    
    public boolean getMouseOverSortFeedback() {
        return this.mouseOverSortFeedback;
    }
    
    public Dimension getPreferredSize() {
        if (this.columns == null || this.columns.size() == 0) {
            return this.getMinimumSize();
        }
        int preferredHeight = 0;
        int preferredWidth = 0;
        if (this.columnWidths == null) {
            this.setDefaultWidths();
        }
        for (int i = 0; i < this.columnWidths.length; ++i) {
            preferredWidth += this.columnWidths[i];
        }
        for (int i = 0; i < this.columns.size(); ++i) {
            final int temp = this.columns.elementAt(i).getPreferredTitleHeight(this.getFontMetrics(this.getFont()), Titlebar.defaultFormatter, this.columnWidths[i] - Titlebar.borderLeft - Titlebar.borderRight - 4 - 4);
            if (temp > preferredHeight) {
                preferredHeight = temp;
            }
        }
        preferredHeight += Titlebar.borderTop + Titlebar.borderBottom + 1 + 1;
        if (this.barWidth == -1) {
            return new Dimension(preferredWidth + this.referenceColumnWidth, preferredHeight);
        }
        return new Dimension(this.barWidth, preferredHeight);
    }
    
    public int getReferenceColumnWidth() {
        return this.referenceColumnWidth;
    }
    
    public int getScrollPos() {
        return this.scrollPos;
    }
    
    public boolean getSortAscending() {
        return this.sortAscending;
    }
    
    public int getSortColumn() {
        return this.sortColumn;
    }
    
    protected SortIcon getSortIconInstance() {
        if (this.sortIcon == null) {
            this.sortIcon = new SortIcon(this);
        }
        return this.sortIcon;
    }
    
    private void initialize() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (this.dragIndex >= 0) {
            final int x = this.dragConstraints.dragHorizontally(e.getX());
            this.dragColumn(this.dragIndex, x - this.lastDragX);
            this.lastDragX = x;
        }
        if (this.m_iDragColumnIndex >= 0 && this.m_bAllowColumnDragDrop) {
            final int iOffset = this.m_iMouseX - e.getX();
            if (iOffset > 5 || iOffset < -5) {
                this.m_bDragging = true;
                this.simulateColDragging(e);
            }
            else if (this.m_bDragging) {
                this.simulateColDragging(e);
            }
        }
    }
    
    private void simulateColDragging(final MouseEvent e) {
        final BorderSingle oBorder = new BorderSingle();
        this.getParent().paint(this.getParent().getGraphics());
        final Dimension oDim = this.getParent().getSize();
        oBorder.paint(this.getParent().getGraphics(), this.m_iStartColumnX - 5 - this.m_iMouseX + e.getX(), 2, this.m_iEndColumnX - this.m_iStartColumnX + 10, oDim.height - 4);
        this.setCursor(Cursor.getPredefinedCursor(11));
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.savedCursor = this.getCursor();
    }
    
    public void mouseExited(final MouseEvent e) {
        if (this.savedCursor != null) {
            this.setCursor(this.savedCursor);
        }
        if (this.canSort) {
            this.mouseOverIndex = -1;
            this.paint(this.getGraphics());
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
        if (!this.isEnabled()) {
            return;
        }
        final int mouseX = e.getX();
        if (this.canSort && this.mouseOverSortFeedback) {
            final int oldMouseOverIndex = this.mouseOverIndex;
            this.mouseOverIndex = this.findSortIndex(mouseX);
            if (oldMouseOverIndex != this.mouseOverIndex) {
                this.paint(this.getGraphics());
            }
        }
        if (this.findDragIndex(mouseX) >= 0) {
            this.setCursor(Cursor.getPredefinedCursor(11));
        }
        else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        if (!this.isEnabled()) {
            return;
        }
        this.m_iMouseX = e.getX();
        this.m_bDragging = false;
        this.m_iDragColumnIndex = -1;
        this.propertyChange.firePropertyChange("mouseStateChangeEvent", new Integer(0), new Integer(this.m_iMouseX));
        this.setSortParams(this.m_iMouseX);
        final Integer oldDragIndex = new Integer(this.dragIndex);
        this.dragIndex = this.findDragIndex(this.m_iMouseX);
        if (this.dragIndex >= 0) {
            final Dimension d = this.getPreferredSize();
            this.savePreferredWidth = d.width;
            this.savePreferredHeight = d.height;
            int maxLeft = this.scrollPos + this.referenceColumnWidth;
            int i;
            for (i = 0; i < this.dragIndex; ++i) {
                maxLeft += this.columnWidths[i];
            }
            this.dragConstraints = new DragConstraints(maxLeft + this.minimumColumnWidth, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            this.lastDragX = this.dragConstraints.dragHorizontally(maxLeft + this.columnWidths[i]);
            this.propertyChange.firePropertyChange("draggingStateChanged", oldDragIndex, new Integer(this.dragIndex));
        }
        else {
            this.m_iDragColumnIndex = this.findDragColumnIndex(this.m_iMouseX);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.dragIndex != -1) {
            final Integer oldDragIndex = new Integer(this.dragIndex);
            this.dragIndex = -1;
            this.propertyChange.firePropertyChange("draggingStateChanged", oldDragIndex, new Integer(this.dragIndex));
            final Dimension d = this.getPreferredSize();
            if (this.savePreferredWidth != d.width || this.savePreferredHeight != d.height) {
                this.propertyChange.firePropertyChange("DimensionChanged", new Dimension(this.savePreferredWidth, this.savePreferredHeight), d);
            }
        }
        final int mouseX = e.getX();
        if (!this.m_bDragging && this.dragIndex < 0 && this.canSort && this.columnSortListener != null && mouseX >= this.m_iStartColumnX && mouseX <= this.m_iEndColumnX) {
            final int sortIndex = this.findSortIndex(mouseX);
            if (sortIndex >= 0) {
                if (sortIndex == this.sortColumn) {
                    this.sortAscending = !this.sortAscending;
                }
                else {
                    this.sortColumn = sortIndex;
                    this.sortAscending = true;
                }
                this.setCursor(Cursor.getPredefinedCursor(3));
                final ColumnDefinition oCol = this.columns.elementAt(this.sortColumn);
                if (this.m_sOnSort.length() > 0) {
                    this.execJavaScript(this.m_sOnSort);
                }
                this.columnSortListener.sortColumn(new ColumnSortEvent(this, this.sortColumn, this.sortAscending, oCol.getColumnType(), oCol.getDateFormat()));
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
        if (this.m_iDragColumnIndex >= 0) {
            if (this.m_bDragging) {
                this.getParent().paint(this.getParent().getGraphics());
                this.m_bDragging = false;
                int iTargetCol = this.findDragColumnIndex(mouseX);
                if (iTargetCol < 0) {
                    iTargetCol = this.getTargetCol(mouseX);
                }
                if (iTargetCol >= 0) {
                    this.shiftElements(this.m_iDragColumnIndex, iTargetCol);
                    this.propertyChange.firePropertyChange("COLUMN_SWAP_EVENT", new Integer(this.m_iDragColumnIndex), new Integer(iTargetCol));
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
        this.paint(this.getGraphics());
        if (this.adjustToFit) {
            this.propertyChange.firePropertyChange("ColumnWidthsChanged", null, null);
        }
    }
    
    public void execJavaScript(final String sVal) {
        String sJSResult = null;
        boolean bSuccess = false;
        try {
            Method getw = null;
            Method eval = null;
            Object jswin = null;
            final Class c = Class.forName("netscape.javascript.JSObject");
            final Method[] ms = c.getMethods();
            for (int i = 0; i < ms.length; ++i) {
                if (ms[i].getName().compareTo("getWindow") == 0) {
                    getw = ms[i];
                }
                else if (ms[i].getName().compareTo("eval") == 0) {
                    eval = ms[i];
                }
            }
            final Object[] a = { this.getParent().getParent() };
            jswin = getw.invoke(c, a);
            a[0] = sVal;
            final Object oResult = eval.invoke(jswin, a);
            if (oResult instanceof String) {
                sJSResult = (String)oResult;
            }
            else {
                sJSResult = oResult.toString();
            }
            bSuccess = true;
        }
        catch (InvocationTargetException ite) {
            sJSResult = new StringBuffer().append(ite.getTargetException()).toString();
        }
        catch (Exception e) {
            sJSResult = e.toString();
        }
        if (!bSuccess) {
            System.out.println("eval failed with error " + sJSResult);
        }
    }
    
    private int getTargetCol(final int mouseX) {
        if (this.m_iMouseX != mouseX) {
            int iBegin = 0;
            int iEnd = this.columnWidths.length;
            int iDir = 1;
            if (this.m_iMouseX < mouseX) {
                iBegin = this.columnWidths.length - 1;
                iEnd = 0;
                iDir = -1;
            }
            for (int i = iBegin; i != iEnd; i += iDir) {
                if (this.columnWidths[i] > 0) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private void shiftElements(final int iFrom, final int iTo) {
        int iDir = 1;
        if (iFrom > iTo) {
            iDir = -1;
        }
        for (int i = iFrom; i != iTo; i += iDir) {
            final ColumnDefinition colTmp = this.columns.elementAt(i);
            this.columns.setElementAt(this.columns.elementAt(i + iDir), i);
            this.columns.setElementAt(colTmp, i + iDir);
            final int iTmp = this.columnWidths[i];
            this.columnWidths[i] = this.columnWidths[i + iDir];
            this.columnWidths[i + iDir] = iTmp;
            boolean bTmp = this.columnResizeableFlags[i];
            this.columnResizeableFlags[i] = this.columnResizeableFlags[i + iDir];
            this.columnResizeableFlags[i + iDir] = bTmp;
            bTmp = this.columnAdjustableFlags[i];
            this.columnAdjustableFlags[i] = this.columnAdjustableFlags[i + iDir];
            this.columnAdjustableFlags[i + iDir] = bTmp;
            bTmp = this.columnSortableFlags[i];
            this.columnSortableFlags[i] = this.columnSortableFlags[i + iDir];
            this.columnSortableFlags[i + iDir] = bTmp;
            if (this.sortColumn == i) {
                this.sortColumn = i + iDir;
            }
            if (this.sortColumns != null) {
                for (int j = 0; j < this.sortColumns.length; ++j) {
                    if (this.sortColumns[j] == i) {
                        this.sortColumns[j] = i + iDir;
                    }
                    else if (this.sortColumns[j] == i + iDir) {
                        this.sortColumns[j] = i;
                    }
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        final Graphics g = this.prepareOffScreen(graphics);
        int x = this.scrollPos + this.referenceColumnWidth;
        if (this.columns != null && this.columns.size() > 0) {
            if (this.columnWidths == null) {
                this.setDefaultWidths();
            }
            this.adjustColumns(this.w);
            for (int i = 0; i < this.columns.size(); ++i) {
                final ColumnDefinition cd = this.columns.elementAt(i);
                this.paintOneTitle(g, i, x, cd);
                x += this.columnWidths[i];
            }
        }
        this.paintReferenceColumn(g);
        if (x < this.w) {
            this.eraseRight(g, x);
        }
        if (this.doubleBuffer) {
            graphics.drawImage(this.offScreen, 0, 0, this.w, this.h, 0, 0, this.w, this.h, null);
        }
    }
    
    private void paintOneTitle(final Graphics g, final int column, final int x, final ColumnDefinition cd) {
        int insideWidth = this.columnWidths[column];
        int insideHeight = this.h;
        if (insideWidth == 0) {
            return;
        }
        Color bg = cd.getTitleBackground();
        if (bg == null) {
            bg = this.getBackground();
        }
        Titlebar.border.setBackground(bg);
        Titlebar.border.paint(g, x, 0, insideWidth, insideHeight);
        int insideX = x + Titlebar.borderLeft;
        int insideY = Titlebar.borderTop;
        insideWidth -= Titlebar.borderLeft + Titlebar.borderRight;
        insideHeight -= Titlebar.borderTop + Titlebar.borderBottom;
        g.setColor(bg);
        g.fillRect(x + Titlebar.borderLeft, Titlebar.borderTop, insideWidth, insideHeight);
        insideX += 4;
        ++insideY;
        insideWidth -= 8;
        insideHeight -= 2;
        if (insideX + insideWidth >= 0 && insideX < this.w) {
            if (this.canSort && (this.columnSortableFlags == null || this.columnSortableFlags[column]) && (column == this.sortColumn || column == this.mouseOverIndex || (this.multisort && this.isSortColumn(column)))) {
                int cardinality = -1;
                String sCard = "";
                if (this.multisort && this.isSortColumn(column)) {
                    for (int k = 0; k < this.sortColumns.length; ++k) {
                        if (this.sortColumns[k] == column) {
                            cardinality = k + 1;
                        }
                    }
                    if (cardinality > 0) {
                        sCard = cardinality + ") ";
                    }
                }
                if (sCard.length() > 0) {
                    this.paintOneTitleString(g, cd, insideX, insideY, insideWidth - 9, insideHeight, sCard);
                }
                else {
                    this.paintOneTitleString(g, cd, insideX, insideY, insideWidth - 9, insideHeight);
                }
                if (insideWidth > 9) {
                    if (!this.multisort && column == this.sortColumn) {
                        final boolean direction = (column == this.mouseOverIndex) ? (!this.sortAscending) : this.sortAscending;
                        this.getSortIconInstance().paint(g, direction, insideX + insideWidth - 9, 0, 9, this.h);
                    }
                    else if (this.multisort && this.isSortColumn(column)) {
                        final boolean direction = (column == this.mouseOverIndex) ? (!this.isAscendingColumn(column)) : this.isAscendingColumn(column);
                        this.getSortIconInstance().paint(g, direction, insideX + insideWidth - 9, 0, 9, this.h);
                    }
                    else {
                        this.getSortIconInstance().paint(g, true, insideX + insideWidth - 9, 0, 9, this.h);
                    }
                }
            }
            else {
                this.paintOneTitleString(g, cd, insideX, insideY, insideWidth, insideHeight);
            }
        }
    }
    
    private void paintOneTitleString(final Graphics g, final ColumnDefinition cd, final int x, final int y, final int width, final int height) {
        Font f = cd.getTitleFont();
        FontMetrics m;
        if (f == null) {
            f = this.getFont();
            m = this.getFontMetrics(f);
        }
        else {
            m = cd.getTitleFontMetrics();
        }
        final Shape oldClip = g.getClip();
        final int left = Math.max(x, 0);
        final int right = Math.min(x + width, this.w);
        g.setClip(left, y, right - left, height);
        g.setFont(f);
        Color c = cd.getTitleForeground();
        if (c == null) {
            c = this.getForeground();
        }
        g.setColor(c);
        StringFormatter formatter = cd.getTitleFormatter();
        if (formatter == null) {
            formatter = Titlebar.defaultFormatter;
        }
        formatter.formatString(g, m, cd.getTitle(), x, y, width, height);
        g.setClip(oldClip);
    }
    
    private void paintOneTitleString(final Graphics g, final ColumnDefinition cd, final int x, final int y, final int width, final int height, final String sCard) {
        Font f = cd.getTitleFont();
        FontMetrics m;
        if (f == null) {
            f = this.getFont();
            m = this.getFontMetrics(f);
        }
        else {
            m = cd.getTitleFontMetrics();
        }
        final Shape oldClip = g.getClip();
        final int left = Math.max(x, 0);
        final int right = Math.min(x + width, this.w);
        g.setClip(left, y, right - left, height);
        g.setFont(f);
        Color c = cd.getTitleForeground();
        if (c == null) {
            c = this.getForeground();
        }
        g.setColor(c);
        StringFormatter formatter = cd.getTitleFormatter();
        if (formatter == null) {
            formatter = Titlebar.defaultFormatter;
        }
        formatter.formatString(g, m, String.valueOf(sCard) + cd.getTitle(), x, y, width, height);
        g.setClip(oldClip);
    }
    
    private void paintReferenceColumn(final Graphics g) {
        if (this.referenceColumnWidth == 0) {
            return;
        }
        int insideWidth = this.referenceColumnWidth;
        int insideHeight = this.h;
        final Color bg = this.getBackground();
        Titlebar.border.setBackground(bg);
        Titlebar.border.paint(g, 0, 0, insideWidth, insideHeight);
        insideWidth -= Titlebar.borderLeft + Titlebar.borderRight;
        insideHeight -= Titlebar.borderTop + Titlebar.borderBottom;
        g.setColor(bg);
        g.fillRect(Titlebar.borderLeft, Titlebar.borderTop, insideWidth, insideHeight);
    }
    
    private Graphics prepareOffScreen(final Graphics g) {
        if (this.doubleBuffer && this.w > 0 && this.h > 0) {
            if (this.offScreen == null) {
                this.offScreen = this.createImage(this.w, this.h);
            }
            else if (this.offScreen.getWidth(null) != this.w || this.offScreen.getHeight(null) != this.h) {
                this.offScreen.flush();
                this.offScreen = this.createImage(this.w, this.h);
            }
            return this.offScreen.getGraphics();
        }
        return g;
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.propertyChange.removePropertyChangeListener(listener);
    }
    
    public void setAdjustToFit(final boolean b) {
        this.adjustToFit = b;
    }
    
    public void setBarWidth(final int explicitWidth) {
        this.barWidth = explicitWidth;
    }
    
    public static void setBorder(final Border border) {
        Titlebar.border = border;
        final Insets insets = border.getInsets();
        Titlebar.borderTop = insets.top;
        Titlebar.borderLeft = insets.left;
        Titlebar.borderRight = insets.right;
        Titlebar.borderBottom = insets.bottom;
    }
    
    public void setCanSort(final boolean canSort) {
        this.canSort = canSort;
    }
    
    public void setColumnResizeableFlags(final boolean[] flags) throws IllegalArgumentException {
        if (flags == null || this.columns.size() != flags.length) {
            throw new IllegalArgumentException("# of columns differs from # of resizeable flags.");
        }
        this.columnResizeableFlags = flags;
    }
    
    public void setColumns(final Vector columns, final int[] widths) throws IllegalArgumentException {
        if (widths != null && columns.size() != widths.length) {
            throw new IllegalArgumentException("# of columns differs from # of widths.");
        }
        this.columns = columns;
        this.columnWidths = widths;
        this.columnAdjustableFlags = new boolean[columns.size()];
    }
    
    public void setColumnSortableFlags(final boolean[] flags) throws IllegalArgumentException {
        if (flags == null || this.columns.size() != flags.length) {
            throw new IllegalArgumentException("# of columns differs from # of sortable flags.");
        }
        this.columnSortableFlags = flags;
    }
    
    public void setColumnWidths(final int[] widths) throws IllegalArgumentException {
        if (widths == null || this.columns.size() != widths.length) {
            throw new IllegalArgumentException("# of columns differs from # of widths.");
        }
        this.columnWidths = widths;
    }
    
    public static void setDefaultBackground(final Color c) {
        Titlebar.defaultBackground = c;
    }
    
    public static void setDefaultFont(final Font f) {
        Titlebar.defaultFont = f;
    }
    
    public static void setDefaultForeground(final Color c) {
        Titlebar.defaultForeground = c;
    }
    
    public static void setDefaultFormatter(final StringFormatter formatter) {
        Titlebar.defaultFormatter = formatter;
    }
    
    protected void setDefaultWidths() {
        this.columnWidths = new int[this.columns.size()];
        for (int i = 0; i < this.columnWidths.length; ++i) {
            this.columnWidths[i] = this.columns.elementAt(i).getPreferredWidth(this.getFontMetrics(this.getFont()), Titlebar.defaultFormatter);
            this.columnWidths[i] = this.actualColumnWidth(this.columnWidths[i]);
        }
    }
    
    public void setDoubleBuffer(final boolean newState) {
        if (!(this.doubleBuffer = newState)) {
            this.flush();
        }
    }
    
    public void setMinimumColumnWidth(final int newMinimumColumnWidth) {
        this.minimumColumnWidth = newMinimumColumnWidth;
    }
    
    public void setMouseOverSortFeedback(final boolean sortFeedback) {
        this.mouseOverSortFeedback = sortFeedback;
    }
    
    public void setReferenceColumnWidth(final int width) {
        this.referenceColumnWidth = width;
    }
    
    public void setScrollPos(final int pos) {
        this.scrollPos = pos;
    }
    
    public void setSortAscending(final boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
    
    public void setSortColumn(final int sortColumn) {
        this.sortColumn = sortColumn;
    }
    
    public void setOnSort(final String sVal) {
        this.m_sOnSort = sVal;
    }
}
