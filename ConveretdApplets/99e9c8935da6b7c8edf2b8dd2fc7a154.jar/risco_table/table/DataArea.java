// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import risco_table.util.Util;
import java.awt.Insets;
import risco_table.TableCell.ITableCell;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import risco_table.graphics.BorderSingle;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Adjustable;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.AWTEventMulticaster;
import risco_table.graphics.BorderRaised;
import risco_table.util.DragScroller;
import java.awt.FontMetrics;
import java.awt.PopupMenu;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Scrollbar;
import java.util.BitSet;
import java.awt.Graphics;
import java.awt.Image;
import risco_table.graphics.CustomButton;
import risco_table.graphics.CheckMark;
import java.util.Vector;
import java.awt.Font;
import risco_table.graphics.Border;
import java.awt.Color;
import risco_table.graphics.StringFormatter;
import risco_table.util.SwapListener;
import java.awt.event.FocusListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class DataArea extends Component implements MouseListener, KeyListener, MouseMotionListener, AdjustmentListener, FocusListener, SwapListener
{
    private static StringFormatter defaultFormatter;
    private static StringFormatter lineNumberFormatter;
    private static Color gridLinesColor;
    private static int checkMarkColumnWidth;
    private static int lineNumberColumnWidth;
    private static int buttonColumnWidth;
    private static Color referenceColumnForeground;
    private static Color referenceColumnBackground;
    private static Border referenceColumnBorder;
    private static Font referenceColumnFont;
    public static final int SELECT_ZERO_OR_ONE = 0;
    public static final int SELECT_ONE = 1;
    public static final int SELECT_MANY = 2;
    public static final int SELECT_MANY_CONTINUOUS = 3;
    public static final int DRAG_CURSOR_RANGE = 5;
    private Table parentTable;
    private Vector columns;
    private Vector rows;
    private int[] columnWidths;
    private boolean showGrid;
    private int currentFocus;
    private int currentSelection;
    private int selectionCount;
    private int lineHeight;
    private int hScrollPos;
    private int vScrollPos;
    private boolean scrollingOptimization;
    private boolean checkingSelects;
    private boolean m_bSyncChecksWithSelections;
    private boolean m_bAllowRowDragDrop;
    private boolean m_bDragging;
    private int m_iMouseX;
    private int m_iMouseY;
    private int m_iDragRowIndex;
    private Color[] selectionBackground;
    private Color[] selectionForeground;
    private boolean showLineNumbers;
    private boolean showCheckmarks;
    private boolean showButtons;
    private int referenceColumnWidth;
    private double lineHeightFactor;
    private int preferredHeight;
    private boolean hasFocus;
    private CheckMark checkMark;
    private CustomButton buttonAccept;
    private CustomButton buttonModify;
    public final int DEFAULT_BUF_SIZE = 80;
    private char[] charBuf;
    private int charBufResetCounter;
    private Image offScreen;
    private Graphics offScreenGraphics;
    private BitSet selections;
    private int selectionMode;
    private BitSet checkmarks;
    private BitSet buttonPressedAccept;
    private BitSet buttonShowAccept;
    private Scrollbar vScrollbar;
    private Scrollbar hScrollbar;
    private ActionListener actionListener;
    private ItemListener itemListener;
    private AdjustmentListener adjustmentListener;
    private PopupMenu popup;
    private Color fg;
    private Color bg;
    private Font f;
    private FontMetrics fm;
    private int w;
    private int h;
    private int lastDragLine;
    private DragScroller scroller;
    private Image acceptButtonNormalImage;
    private Image acceptButtonPressedImage;
    private Image modifyButtonNormalImage;
    private Image modifyButtonPressedImage;
    private String m_sOnRowDblClick;
    private String m_sOnRowClick;
    private String m_sOnAcceptClick;
    private String m_sOnModifyClick;
    
    static {
        DataArea.defaultFormatter = new StringFormatter();
        DataArea.lineNumberFormatter = new StringFormatter(8, 4);
        DataArea.gridLinesColor = Color.lightGray;
        DataArea.checkMarkColumnWidth = 22;
        DataArea.lineNumberColumnWidth = 30;
        DataArea.buttonColumnWidth = 60;
        DataArea.referenceColumnForeground = Color.black;
        DataArea.referenceColumnBackground = Color.getHSBColor(0.613821f, 0.191589f, 0.839216f);
        setReferenceColumnBorder(new BorderRaised());
        setReferenceColumnFont(new Font("SansSerif", 0, 10));
    }
    
    public DataArea(final Table parent, final Scrollbar vScrollbar, final Scrollbar hScrollbar, final Vector columnDefinitions, final Vector rows) {
        this.showGrid = true;
        this.currentFocus = 0;
        this.currentSelection = -1;
        this.selectionCount = 0;
        this.lineHeight = 1;
        this.hScrollPos = 0;
        this.vScrollPos = 0;
        this.checkingSelects = false;
        this.m_bSyncChecksWithSelections = false;
        this.m_bAllowRowDragDrop = false;
        this.m_bDragging = false;
        this.m_iMouseX = -1;
        this.m_iMouseY = -1;
        this.m_iDragRowIndex = -1;
        this.showLineNumbers = false;
        this.showCheckmarks = false;
        this.showButtons = true;
        this.lineHeightFactor = 1.0;
        this.preferredHeight = -1;
        this.charBuf = new char[80];
        this.charBufResetCounter = 0;
        this.selections = new BitSet();
        this.selectionMode = 2;
        this.checkmarks = new BitSet();
        this.buttonPressedAccept = new BitSet();
        this.buttonShowAccept = new BitSet();
        this.m_sOnRowDblClick = new String();
        this.m_sOnRowClick = new String();
        this.m_sOnAcceptClick = new String();
        this.m_sOnModifyClick = new String();
        this.parentTable = parent;
        this.setScrollbars(vScrollbar, hScrollbar);
        this.columns = columnDefinitions;
        this.rows = rows;
        this.initialize();
    }
    
    int actualColumnWidth(final int netWidth) {
        return netWidth + 4 + 4;
    }
    
    public void add(final PopupMenu popup) {
        super.add(this.popup = popup);
    }
    
    void addActionListener(final ActionListener listener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, listener);
    }
    
    public void addAdjustmentListener(final AdjustmentListener l) {
        this.adjustmentListener = AWTEventMulticaster.add(this.adjustmentListener, l);
    }
    
    void addItemListener(final ItemListener listener) {
        this.itemListener = AWTEventMulticaster.add(this.itemListener, listener);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        synchronized (this.rows) {
            int scrollTo;
            if (this.scrollingOptimization) {
                scrollTo = e.getValue();
                final EventQueue q = Toolkit.getDefaultToolkit().getSystemEventQueue();
                for (AWTEvent queuedEvent = q.peekEvent(); queuedEvent != null; queuedEvent = q.peekEvent()) {
                    if (!(queuedEvent instanceof AdjustmentEvent)) {
                        break;
                    }
                    try {
                        scrollTo = ((AdjustmentEvent)q.getNextEvent()).getValue();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                scrollTo = e.getValue();
            }
            this.fireAdjustmentEvent(new AdjustmentEvent((Adjustable)e.getSource(), e.getID(), e.getAdjustmentType(), scrollTo));
            final Scrollbar s = (Scrollbar)e.getSource();
            if (s == this.vScrollbar) {
                this.scrollTo(-scrollTo);
            }
            else if (s == this.hScrollbar) {
                this.setHorizontalScrollPos(-scrollTo);
                this.paint(this.getGraphics());
            }
        }
        // monitorexit(this.rows)
    }
    
    void check(final int index) {
        this.check(index, true);
    }
    
    void check(final int index, final boolean state) {
        if (this.checkmarks.get(index) != state) {
            if (state) {
                this.checkmarks.set(index);
            }
            else {
                this.checkmarks.clear(index);
            }
            if (index >= -this.vScrollPos && index <= -this.vScrollPos + this.getVisibleCount()) {
                this.paintLine(index);
            }
        }
    }
    
    public void setAcceptButtonNormalImage(final Image iImage) {
        this.acceptButtonNormalImage = iImage;
    }
    
    public Image getAcceptButtonNormalImage() {
        return this.acceptButtonNormalImage;
    }
    
    public void setAcceptButtonPressedImage(final Image iImage) {
        this.acceptButtonPressedImage = iImage;
    }
    
    public Image getAcceptButtonPressedImage() {
        return this.acceptButtonPressedImage;
    }
    
    public void setModifyButtonNormalImage(final Image iImage) {
        this.modifyButtonNormalImage = iImage;
    }
    
    public Image getModifyButtonNormalImage() {
        return this.modifyButtonNormalImage;
    }
    
    public void setModifyButtonPressedImage(final Image iImage) {
        this.modifyButtonPressedImage = iImage;
    }
    
    public Image getModifyButtonPressedImage() {
        return this.modifyButtonPressedImage;
    }
    
    void setButtonPressedAcceptBitset(final int index, final boolean state) {
        if (this.buttonPressedAccept.get(index) != state) {
            if (state) {
                this.buttonPressedAccept.set(index);
            }
            else {
                this.buttonPressedAccept.clear(index);
            }
            if (index >= -this.vScrollPos && index <= -this.vScrollPos + this.getVisibleCount()) {
                this.paintLine(index);
            }
        }
    }
    
    public void setShowButtonAcceptBitset(final int index, final boolean state) {
        if (this.buttonShowAccept.get(index) != state) {
            if (state) {
                this.buttonShowAccept.set(index);
            }
            else {
                this.buttonShowAccept.clear(index);
            }
        }
    }
    
    void checkAll() {
        for (int i = 0; i < this.rows.size(); ++i) {
            this.check(i, true);
        }
    }
    
    private void clickLine(final int line, final boolean shift, final boolean control, final boolean repaint) {
        if (line < 0 || line >= this.rows.size()) {
            return;
        }
        switch (this.selectionMode) {
            case 1: {
                this.clickLineOne(line, shift, control, repaint);
                break;
            }
            case 0: {
                this.clickLineZeroOrOne(line, shift, control, repaint);
                break;
            }
            case 2: {
                this.clickLineMany(line, shift, control, repaint);
                break;
            }
            case 3: {
                this.clickLineManyContinuous(line, shift, control, repaint);
                break;
            }
        }
    }
    
    public void clickLineMany(final int line, final boolean shift, final boolean control, final boolean repaint) {
        if (control) {
            int what;
            if (shift) {
                int start = this.currentSelection;
                if (start < 0) {
                    start = this.currentFocus;
                }
                if (start < 0) {
                    start = 0;
                }
                this.currentSelection = start;
                this.moveFocus(line, true, false);
                this.selectRange(start, line);
                what = 1;
            }
            else {
                this.moveFocus(line, true, false);
                if (!this.isSelected(line)) {
                    this.select(this.currentSelection = line, true);
                    what = 1;
                }
                else {
                    this.select(line, false);
                    what = 2;
                }
            }
            this.fireItemEvent(new ItemEvent(this.parentTable, 701, this.rows.elementAt(line), what));
        }
        else {
            this.clickLineManyContinuous(line, shift, control, repaint);
        }
    }
    
    private void clickLineManyContinuous(final int line, final boolean shift, final boolean control, final boolean repaint) {
        if (shift) {
            int start = this.currentSelection;
            if (start < 0) {
                start = this.currentFocus;
            }
            if (start < 0) {
                start = 0;
            }
            this.moveFocus(line, true, true);
            this.selectRangeExclusive(start, line);
            this.currentSelection = start;
            this.fireItemEvent(new ItemEvent(this.parentTable, 701, this.rows.elementAt(line), 1));
        }
        else {
            if (this.selectionCount > 1) {
                this.deselectAll(true);
            }
            this.clickLineZeroOrOne(line, shift, control, repaint);
        }
    }
    
    public void clickLineOne(final int line, final boolean shift, final boolean control, final boolean repaint) {
        if (!this.isSelected(line)) {
            this.clickLineZeroOrOne(line, false, false, repaint);
        }
    }
    
    public void clickLineZeroOrOne(final int line, final boolean shift, final boolean control, final boolean repaint) {
        if (this.isSelected(line)) {
            if (control) {
                this.moveFocus(line, true, false);
                if (this.currentSelection == line) {
                    this.currentSelection = -1;
                }
                --this.selectionCount;
                this.selections.clear(line);
                if (repaint) {
                    this.paintLine(line);
                }
                this.fireItemEvent(new ItemEvent(this.parentTable, 701, this.rows.elementAt(line), 2));
            }
            else {
                this.moveFocus(line, true, repaint);
                this.currentSelection = line;
            }
        }
        else {
            this.moveFocus(line, true, false);
            if (this.currentSelection >= 0) {
                this.deselect(this.currentSelection);
            }
            this.selections.set(line);
            this.currentSelection = line;
            ++this.selectionCount;
            if (repaint) {
                this.paintLine(line);
            }
            this.fireItemEvent(new ItemEvent(this.parentTable, 701, this.rows.elementAt(line), 1));
        }
    }
    
    void deselect(final int index) {
        this.select(index, false);
    }
    
    void deselectAll(final boolean redraw) {
        if (redraw) {
            final int firstIndex = -this.vScrollPos;
            for (int pastIndex = firstIndex + this.getVisibleCount(), i = firstIndex; i < pastIndex; ++i) {
                if (this.selections.get(i)) {
                    this.selections.clear(i);
                    this.paintLine(i);
                }
            }
        }
        this.selections = new BitSet();
        this.selectionCount = 0;
        this.currentSelection = -1;
    }
    
    private void eraseBottom(final Graphics g, final int y) {
        if (y < this.h) {
            g.setColor(this.bg);
            g.fillRect(0, y, this.w, this.h - y);
            g.drawLine(0, y, this.w, this.h);
        }
    }
    
    public void finalize() throws Throwable {
        this.flush();
        super.finalize();
    }
    
    private int findLine(final int y) {
        final int line = y / this.lineHeight - this.vScrollPos;
        if (line < 0 || line >= this.rows.size()) {
            return -1;
        }
        return line;
    }
    
    void fireActionEvent(final ActionEvent e) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(e);
        }
    }
    
    void fireAdjustmentEvent(final AdjustmentEvent e) {
        if (this.adjustmentListener != null) {
            this.adjustmentListener.adjustmentValueChanged(e);
        }
    }
    
    void fireItemEvent(final ItemEvent e) {
        if (this.itemListener != null) {
            this.itemListener.itemStateChanged(e);
        }
    }
    
    private void flush() {
        if (this.offScreen != null) {
            this.offScreen.flush();
            this.offScreen = null;
        }
    }
    
    public void focusGained(final FocusEvent e) {
        if (this.offScreenGraphics == null) {
            return;
        }
        synchronized (this.rows) {
            if (!this.hasFocus) {
                this.hasFocus = true;
                if (this.rows.size() > 0) {
                    this.paintLine(this.currentFocus);
                }
            }
        }
        // monitorexit(this.rows)
    }
    
    public void focusLost(final FocusEvent e) {
        if (this.offScreenGraphics == null || this.getGraphics() == null) {
            return;
        }
        synchronized (this.rows) {
            if (this.hasFocus) {
                this.hasFocus = false;
                if (this.rows.size() > 0) {
                    this.paintLine(this.currentFocus);
                }
            }
        }
        // monitorexit(this.rows)
    }
    
    int[] getCheckedIndexes() {
        if (!this.showCheckmarks) {
            return null;
        }
        final Vector checked = new Vector();
        for (int i = 0; i < this.rows.size(); ++i) {
            if (this.checkmarks.get(i)) {
                checked.addElement(new Integer(i));
            }
        }
        final int[] indexes = new int[checked.size()];
        for (int j = 0; j < indexes.length; ++j) {
            indexes[j] = checked.elementAt(j);
        }
        return indexes;
    }
    
    public boolean getSyncChecksWithSelections() {
        return this.m_bSyncChecksWithSelections;
    }
    
    public void setSyncChecksWithSelections(final boolean bValue) {
        this.m_bSyncChecksWithSelections = bValue;
    }
    
    public boolean getCheckingSelects() {
        return this.checkingSelects;
    }
    
    public int[] getColumnWidths() {
        return this.columnWidths;
    }
    
    int getCompletelyVisibleCount() {
        return this.h / this.lineHeight;
    }
    
    public static StringFormatter getDefaultFormatter() {
        return DataArea.defaultFormatter;
    }
    
    public static Color getGridLinesColor() {
        return DataArea.gridLinesColor;
    }
    
    boolean getHasFillerLine() {
        return this.rows.size() * this.lineHeight > this.h && this.h % this.lineHeight != 0;
    }
    
    int getHorizontalScrollPos() {
        return this.hScrollPos;
    }
    
    public static StringFormatter getLineNumberFormatter() {
        return DataArea.lineNumberFormatter;
    }
    
    public Dimension getMinimumSize() {
        if (this.columns == null) {
            return new Dimension(10, 10);
        }
        int width = 0;
        this.lineHeight = 0;
        if (this.columnWidths == null) {
            this.setDefaultWidths();
        }
        for (int i = 0; i < this.columnWidths.length; ++i) {
            width += this.columnWidths[i];
        }
        for (int i = 0; i < this.columns.size(); ++i) {
            final int temp = this.columns.elementAt(i).getPreferredDataLineHeight(this.getFontMetrics(this.getFont()));
            if (temp > this.lineHeight) {
                this.lineHeight = temp;
            }
        }
        this.lineHeight *= (int)this.lineHeightFactor;
        this.lineHeight += 2;
        return new Dimension(width + this.getReferenceColumnWidth(), this.lineHeight);
    }
    
    public Dimension getPreferredSize() {
        final Dimension d = this.getMinimumSize();
        if (this.preferredHeight == -1) {
            d.height *= this.rows.size();
        }
        else {
            d.height *= this.preferredHeight;
        }
        return d;
    }
    
    public static Color getReferenceColumnBackground() {
        return DataArea.referenceColumnBackground;
    }
    
    public static Border getReferenceColumnBorder() {
        return DataArea.referenceColumnBorder;
    }
    
    public static Font getReferenceColumnFont() {
        return DataArea.referenceColumnFont;
    }
    
    public static Color getReferenceColumnForeground() {
        return DataArea.referenceColumnForeground;
    }
    
    public int getReferenceColumnWidth() {
        int width = 0;
        if (this.showLineNumbers) {
            width += DataArea.lineNumberColumnWidth;
        }
        if (this.showCheckmarks) {
            width += DataArea.checkMarkColumnWidth;
        }
        if (this.showButtons) {
            width += DataArea.buttonColumnWidth * 2;
        }
        return width;
    }
    
    int getSelectedIndex() {
        if (this.selectionCount == 1 && this.currentSelection >= 0) {
            return this.currentSelection;
        }
        return -1;
    }
    
    int[] getSelectedIndexes() {
        final int[] indexes = new int[this.selectionCount];
        int i = 0;
        int j = 0;
        while (i < this.rows.size()) {
            if (this.selections.get(i)) {
                indexes[j++] = i;
            }
            ++i;
        }
        return indexes;
    }
    
    public Object[] getSelectedObjects() {
        synchronized (this.rows) {
            final int[] indexes = this.getSelectedIndexes();
            final Object[] objects = new Object[indexes.length];
            for (int i = 0; i < indexes.length; ++i) {
                objects[i] = this.rows.elementAt(indexes[i]);
            }
            // monitorexit(this.rows)
            return objects;
        }
    }
    
    int getSelectionMode() {
        return this.selectionMode;
    }
    
    boolean getShowCheckmarks() {
        return this.showCheckmarks;
    }
    
    public boolean getShowGrid() {
        return this.showGrid;
    }
    
    boolean getShowLineNumbers() {
        return this.showLineNumbers;
    }
    
    int getVerticalScrollPos() {
        return this.vScrollPos;
    }
    
    int getVisibleCount() {
        final int c = this.h / this.lineHeight;
        if (c >= this.rows.size()) {
            return this.rows.size();
        }
        if (this.h % this.lineHeight != 0) {
            return c + 1;
        }
        return c;
    }
    
    private void initialize() {
        try {
            final SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkAwtEventQueueAccess();
            }
            this.scrollingOptimization = true;
        }
        catch (Exception e) {
            this.scrollingOptimization = false;
        }
        if (this.columns == null) {
            this.selectionBackground = new Color[0];
            this.selectionForeground = new Color[0];
        }
        else {
            this.selectionBackground = new Color[this.columns.size()];
            this.selectionForeground = new Color[this.columns.size()];
        }
        final Color selBg = new Color(3238597);
        final Color selFg = Color.white;
        for (int i = 0; i < this.selectionBackground.length; ++i) {
            this.selectionBackground[i] = selBg;
            this.selectionForeground[i] = selFg;
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    final boolean isChecked(final int index) {
        return this.checkmarks.get(index);
    }
    
    final boolean isPressedAccept(final int index) {
        return this.buttonPressedAccept.get(index);
    }
    
    public boolean isShowAccept(final int index) {
        return this.buttonShowAccept.get(index);
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    final boolean isSelected(final int index) {
        return this.selections.get(index);
    }
    
    private void keyDown(final KeyEvent e) {
        final int code = e.getKeyCode();
        int next = 0;
        if (!this.isSelected(this.currentFocus) && !e.isShiftDown() && !e.isControlDown()) {
            this.clickLine(this.currentFocus, e.isShiftDown(), e.isControlDown(), true);
        }
        else {
            next = Math.min(this.rows.size() - 1, this.currentFocus + 1);
            final int lastVisible = -this.vScrollPos + this.getCompletelyVisibleCount();
            if (next >= lastVisible) {
                if (e.isControlDown() && !e.isShiftDown()) {
                    this.moveFocus(next, true, false);
                }
                else {
                    this.clickLine(next, e.isShiftDown(), e.isControlDown(), false);
                }
                this.scrollTo(this.vScrollPos - 1);
                if (this.vScrollbar != null) {
                    this.vScrollbar.setValue(-(this.vScrollPos - 1));
                }
            }
            else if (e.isControlDown() && !e.isShiftDown()) {
                this.moveFocus(next, true, true);
            }
            else {
                this.clickLine(next, e.isShiftDown(), e.isControlDown(), true);
            }
        }
    }
    
    private void keyPageDown(final KeyEvent e) {
        int completelyVisibleCount = this.getCompletelyVisibleCount();
        if (completelyVisibleCount > this.rows.size()) {
            completelyVisibleCount = this.rows.size();
        }
        if (this.currentFocus < -this.vScrollPos + completelyVisibleCount - 1) {
            if (!e.isShiftDown()) {
                this.moveFocus(-this.vScrollPos + completelyVisibleCount - 1, true, true);
            }
            else {
                this.clickLine(-this.vScrollPos + completelyVisibleCount - 1, e.isShiftDown(), e.isControlDown(), true);
            }
        }
        else {
            int pos = this.vScrollPos - completelyVisibleCount;
            if (pos < -this.rows.size() + completelyVisibleCount) {
                pos = -this.rows.size() + completelyVisibleCount;
            }
            if (!e.isShiftDown()) {
                this.moveFocus(Math.min(-pos + completelyVisibleCount - 1, this.rows.size() - 1), true, false);
                this.scrollTo(pos);
                if (this.vScrollbar != null) {
                    this.vScrollbar.setValue(-pos);
                }
            }
            else {
                this.clickLine(Math.min(-pos + completelyVisibleCount - 1, this.rows.size() - 1), e.isShiftDown(), e.isControlDown(), true);
                this.scrollTo(pos);
                if (this.vScrollbar != null) {
                    this.vScrollbar.setValue(-pos);
                }
            }
        }
    }
    
    private void keyPageUp(final KeyEvent e) {
        if (this.currentFocus > -this.vScrollPos) {
            if (!e.isShiftDown()) {
                this.moveFocus(-this.vScrollPos, true, true);
            }
            else {
                this.clickLine(-this.vScrollPos, e.isShiftDown(), e.isControlDown(), true);
            }
        }
        else {
            final int pos = Math.min(this.vScrollPos + this.getCompletelyVisibleCount(), 0);
            if (!e.isShiftDown()) {
                this.moveFocus(-pos, true, false);
                this.scrollTo(pos);
                if (this.vScrollbar != null) {
                    this.vScrollbar.setValue(-pos);
                }
            }
            else {
                this.clickLine(-pos, e.isShiftDown(), e.isControlDown(), true);
                this.scrollTo(pos);
                if (this.vScrollbar != null) {
                    this.vScrollbar.setValue(-pos);
                }
            }
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        final int code = e.getKeyCode();
        switch (code) {
            case 32: {
                synchronized (this.rows) {
                    if (this.showCheckmarks) {
                        this.check(this.currentFocus, !this.isChecked(this.currentFocus));
                    }
                    this.clickLine(this.currentFocus, e.isShiftDown(), e.isControlDown(), true);
                    // monitorexit(this.rows)
                    break;
                }
            }
            case 10: {
                this.fireActionEvent(new ActionEvent(this.parentTable, 1001, null));
                if (this.m_sOnRowDblClick.length() > 0) {
                    this.execJavaScript(this.m_sOnRowDblClick);
                    break;
                }
                break;
            }
            case 40: {
                synchronized (this.rows) {
                    this.keyDown(e);
                }
                // monitorexit(this.rows)
                if (this.m_sOnRowClick.length() > 0) {
                    this.execJavaScript(this.m_sOnRowClick);
                    break;
                }
                break;
            }
            case 38: {
                synchronized (this.rows) {
                    this.keyUp(e);
                }
                // monitorexit(this.rows)
                if (this.m_sOnRowClick.length() > 0) {
                    this.execJavaScript(this.m_sOnRowClick);
                    break;
                }
                break;
            }
            case 34: {
                synchronized (this.rows) {
                    this.keyPageDown(e);
                    // monitorexit(this.rows)
                    break;
                }
            }
            case 33: {
                synchronized (this.rows) {
                    this.keyPageUp(e);
                }
                // monitorexit(this.rows)
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
    
    private void keyUp(final KeyEvent e) {
        final int code = e.getKeyCode();
        int next = 0;
        if (!this.isSelected(this.currentFocus) && !e.isShiftDown() && !e.isControlDown()) {
            this.clickLine(this.currentFocus, e.isShiftDown(), e.isControlDown(), true);
            return;
        }
        next = Math.max(0, this.currentFocus - 1);
        final int top = -this.vScrollPos;
        if (next < top) {
            if (e.isControlDown() && !e.isShiftDown()) {
                this.moveFocus(next, true, false);
            }
            else {
                this.clickLine(next, e.isShiftDown(), e.isControlDown(), false);
            }
            this.scrollTo(this.vScrollPos + 1);
            if (this.vScrollbar != null) {
                this.vScrollbar.setValue(-(this.vScrollPos + 1));
            }
        }
        else if (e.isControlDown() && !e.isShiftDown()) {
            this.moveFocus(next, true, true);
        }
        else {
            this.clickLine(next, e.isShiftDown(), e.isControlDown(), true);
        }
    }
    
    public int getFocusRow() {
        return this.currentFocus;
    }
    
    public void setRowClickHandler(final String sRowDblClick, final String sRowClick) {
        this.m_sOnRowClick = sRowClick;
        this.m_sOnRowDblClick = sRowDblClick;
    }
    
    public void setButtonClickHandler(final String sAcceptClick, final String sModifyClick) {
        this.m_sOnAcceptClick = sAcceptClick;
        this.m_sOnModifyClick = sModifyClick;
    }
    
    public void mouseClicked(final MouseEvent e) {
        e.getClickCount();
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
    
    public void setColumns(final Vector columns) {
        this.columns = columns;
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (!this.isEnabled()) {
            return;
        }
        final int y = e.getY();
        final int leftInset = this.getReferenceColumnWidth();
        if (this.m_iMouseX <= leftInset && this.m_bAllowRowDragDrop) {
            final int iOffset = this.m_iMouseY - e.getY();
            if (iOffset > 5 || iOffset < -5) {
                this.m_bDragging = true;
                this.m_iDragRowIndex = this.findLine(this.m_iMouseY);
                this.simulateRowDragging(e);
                return;
            }
            if (this.m_bDragging) {
                this.simulateRowDragging(e);
                return;
            }
        }
        if (this.scroller == null) {
            final Rectangle r = new Rectangle(leftInset, 0, this.w - leftInset, this.getCompletelyVisibleCount() * this.lineHeight);
            this.scroller = new DragScroller(r, this, this);
        }
        this.scroller.drag(e.getX(), y, e.getModifiers() | 0x1);
        if (e.getX() > leftInset && y >= 0 && y <= this.getCompletelyVisibleCount() * this.lineHeight) {
            synchronized (this.rows) {
                final int line = this.findLine(e.getY());
                if (line >= 0 && line != this.lastDragLine) {
                    this.clickLine(line, true, e.isControlDown(), true);
                    this.lastDragLine = line;
                }
            }
            // monitorexit(this.rows)
        }
    }
    
    private void simulateRowDragging(final MouseEvent e) {
        final BorderSingle oBorder = new BorderSingle();
        this.getParent().paint(this.getParent().getGraphics());
        final Dimension oDim = this.getParent().getSize();
        oBorder.paint(this.getParent().getGraphics(), this.getReferenceColumnWidth() + 2, this.lineHeight - 5 + e.getY(), oDim.width - this.getReferenceColumnWidth() - 4, this.lineHeight);
        this.setCursor(Cursor.getPredefinedCursor(8));
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        if (!this.isEnabled()) {
            return;
        }
        this.m_iMouseX = e.getX();
        this.m_iMouseY = e.getY();
        this.m_bDragging = false;
        this.requestFocus();
        boolean bExecAccept = false;
        boolean bExecModify = false;
        synchronized (this.rows) {
            final int line = this.findLine(e.getY());
            if (line == -1) {
                // monitorexit(this.rows)
                return;
            }
            if (this.showCheckmarks && this.m_bSyncChecksWithSelections) {
                this.lastDragLine = line;
                if (e.getX() > this.getReferenceColumnWidth()) {
                    this.clickLine(line, e.isShiftDown(), e.isControlDown(), true);
                }
                else {
                    this.clickLine(line, e.isShiftDown(), true, true);
                }
                // monitorexit(this.rows)
                return;
            }
            if (this.showCheckmarks) {
                if (e.getX() <= this.getReferenceColumnWidth()) {
                    this.check(line, !this.isChecked(line));
                    if (this.checkingSelects) {
                        this.clickLine(this.lastDragLine = line, e.isShiftDown(), e.isControlDown(), true);
                    }
                    else {
                        this.moveFocus(line, true, true);
                    }
                    // monitorexit(this.rows)
                    return;
                }
            }
            else if (this.showButtons) {
                if (e.getX() <= this.getReferenceColumnWidth()) {
                    this.moveFocus(line, true, true);
                    if (e.getX() <= this.getReferenceColumnWidth() / 2 - 5) {
                        bExecAccept = true;
                        this.setButtonPressedAcceptBitset(line, !this.isPressedAccept(line));
                    }
                    else if (e.getX() >= this.getReferenceColumnWidth() / 2 + 5) {
                        bExecModify = true;
                    }
                }
                else {
                    this.clickLine(this.lastDragLine = line, e.isShiftDown(), e.isControlDown(), true);
                }
            }
            else if (e.getX() > this.getReferenceColumnWidth()) {
                this.clickLine(this.lastDragLine = line, e.isShiftDown(), e.isControlDown(), true);
            }
        }
        // monitorexit(this.rows)
        if (bExecAccept) {
            this.execJavaScript(this.m_sOnAcceptClick);
        }
        if (bExecModify) {
            this.execJavaScript(this.m_sOnModifyClick);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (!this.isEnabled()) {
            return;
        }
        if (this.m_bDragging) {
            this.m_bDragging = false;
            final int iLine = this.findLine(e.getY());
            this.shiftRows(this.m_iDragRowIndex, iLine);
            this.setCursor(Cursor.getDefaultCursor());
            this.getParent().paint(this.getParent().getGraphics());
        }
        if (this.scroller != null) {
            this.scroller = null;
        }
        if (e.getClickCount() == 2) {
            this.fireActionEvent(new ActionEvent(this.parentTable, 1001, null));
            if (this.m_sOnRowDblClick.length() > 0) {
                this.execJavaScript(this.m_sOnRowDblClick);
            }
        }
        if (this.m_sOnRowClick.length() > 0) {
            this.execJavaScript(this.m_sOnRowClick);
        }
    }
    
    public void moveFocus(final int row) {
        this.moveFocus(row, true, true);
    }
    
    private void moveFocus(final int line, final boolean redrawPrev, final boolean redrawNext) {
        if (this.currentFocus != line) {
            final int oldFocus = this.currentFocus;
            this.currentFocus = line;
            if (redrawPrev) {
                this.paintLine(oldFocus);
            }
            if (redrawNext) {
                this.paintLine(line);
            }
        }
    }
    
    public void paint(final Graphics g) {
        final Dimension d = this.getSize();
        this.w = d.width;
        this.h = d.height;
        this.prepareOffScreen(g);
        this.referenceColumnWidth = this.getReferenceColumnWidth();
        this.bg = this.getBackground();
        this.fg = this.getForeground();
        this.f = this.getFont();
        this.fm = this.getFontMetrics(this.f);
        if (this.showCheckmarks && this.checkMark == null) {
            this.checkMark = new CheckMark(this, true);
        }
        if (this.showButtons && this.buttonAccept == null) {
            this.buttonAccept = new CustomButton(this, this.acceptButtonNormalImage, this.acceptButtonPressedImage, "Accept");
        }
        if (this.showButtons && this.buttonModify == null) {
            this.buttonModify = new CustomButton(this, this.modifyButtonNormalImage, this.modifyButtonPressedImage, "Modify");
        }
        synchronized (this.rows) {
            this.updateVerticalScrollbar();
            this.updateHorizontalScrollbar();
            this.paintVisibleRows(g);
        }
        // monitorexit(this.rows)
        if (this.charBufResetCounter % 4096 == 0) {
            this.charBuf = new char[80];
            this.charBufResetCounter = 0;
        }
        else {
            ++this.charBufResetCounter;
        }
    }
    
    private void paintFocus(final Graphics g, int right) {
        if (!this.isEnabled()) {
            return;
        }
        final int left = this.getReferenceColumnWidth();
        final int bottom = this.lineHeight - 1 - (this.showGrid ? 1 : 0);
        --right;
        g.setColor(Color.white);
        g.setXORMode(Color.black);
        for (int x = left; x < right; x += 2) {
            g.drawLine(x, 0, x, 0);
        }
        for (int x = left; x < right; x += 2) {
            g.drawLine(x, bottom, x, bottom);
        }
        for (int y = 1; y < bottom; y += 2) {
            g.drawLine(left, y, left, y);
        }
        for (int y = 1; y < bottom; y += 2) {
            g.drawLine(right, y, right, y);
        }
        g.setPaintMode();
    }
    
    private void paintLine(final int line) {
        this.paintLine(this.getGraphics(), line, 0, this.columns.size());
    }
    
    private void paintLine(final Graphics graphics, final int line, final int startCol, final int pastCol) {
        final Graphics g = this.offScreenGraphics;
        final Shape oldClip = g.getClip();
        int x = 0;
        final boolean selected = this.isSelected(line);
        TableRow row = null;
        if (this.showCheckmarks && this.m_bSyncChecksWithSelections) {
            if (selected) {
                this.checkmarks.set(line);
            }
            else {
                this.checkmarks.clear(line);
            }
        }
        row = this.rows.elementAt(line);
        final ITableCell[] strings = row.getStrings();
        Color[] backgrounds;
        Color[] foregrounds;
        if (selected && this.isEnabled()) {
            backgrounds = this.selectionBackground;
            foregrounds = this.selectionForeground;
        }
        else {
            backgrounds = row.getBackgrounds();
            foregrounds = row.getForegrounds();
        }
        if (startCol == 0) {
            final Insets insets = DataArea.referenceColumnBorder.getInsets();
            g.setColor(DataArea.referenceColumnBackground);
            g.fillRect(0, 0, this.referenceColumnWidth, this.lineHeight);
            g.setColor(DataArea.referenceColumnForeground);
            g.setFont(DataArea.referenceColumnFont);
            if (this.showLineNumbers) {
                final String s = String.valueOf(line + 1);
                s.getChars(0, s.length(), this.charBuf, 0);
                DataArea.lineNumberFormatter.formatString(g, g.getFontMetrics(DataArea.referenceColumnFont), String.valueOf(line + 1), insets.left, insets.top, DataArea.lineNumberColumnWidth - insets.right - insets.left - 4, this.lineHeight - insets.top - insets.bottom);
                x = DataArea.lineNumberColumnWidth;
            }
            if (this.showCheckmarks) {
                this.checkMark.paint(g, this.isChecked(line), x, 0, DataArea.checkMarkColumnWidth, this.lineHeight);
                x += DataArea.checkMarkColumnWidth;
            }
            if (this.showButtons) {
                if (this.isShowAccept(line)) {
                    this.buttonAccept.paint(g, this.isPressedAccept(line), x, 0, DataArea.buttonColumnWidth, this.lineHeight);
                }
                x += DataArea.buttonColumnWidth;
                this.buttonModify.paint(g, false, x, 0, DataArea.buttonColumnWidth, this.lineHeight);
                x += DataArea.buttonColumnWidth;
            }
            if (this.showGrid) {
                DataArea.referenceColumnBorder.paint(g, 0, 0, this.referenceColumnWidth, this.lineHeight);
            }
            g.clipRect(x, 0, this.w - x, this.lineHeight);
        }
        final int focusLeft;
        x = (focusLeft = x + this.hScrollPos);
        for (int i = startCol; i < pastCol; ++i) {
            final ColumnDefinition currentCol = this.columns.elementAt(i);
            final int colWidth = this.columnWidths[i];
            if (backgrounds != null) {
                g.setColor(backgrounds[i]);
            }
            else {
                Color c;
                if (line % 2 == 0) {
                    c = currentCol.getDataBackgroundEven();
                }
                else {
                    c = currentCol.getDataBackground();
                }
                if (c == null) {
                    g.setColor(this.bg);
                }
                else {
                    g.setColor(c);
                }
            }
            g.fillRect(x, 0, colWidth, this.lineHeight);
            if (foregrounds != null) {
                g.setColor(foregrounds[i]);
            }
            else {
                Color c;
                if (line % 2 == 0) {
                    c = currentCol.getDataForegroundEven();
                }
                else {
                    c = currentCol.getDataForeground();
                }
                if (c == null) {
                    g.setColor(this.fg);
                }
                else {
                    g.setColor(c);
                }
            }
            StringFormatter formatter = currentCol.getDataFormatter();
            if (formatter == null) {
                formatter = DataArea.defaultFormatter;
            }
            final Font curFont = currentCol.getDataFont();
            FontMetrics m;
            if (curFont == null) {
                g.setFont(this.f);
                m = this.fm;
            }
            else {
                g.setFont(curFont);
                m = currentCol.getDataFontMetrics();
            }
            final String s = strings[i].toString();
            if (currentCol.getWrapDataCells()) {
                formatter.formatString(g, m, s, x + 4, 1, colWidth - 4 - 4, this.lineHeight - 1 - 1);
            }
            else {
                if (s.length() > this.charBuf.length) {
                    this.charBuf = new char[s.length()];
                }
                s.getChars(0, s.length(), this.charBuf, 0);
                formatter.drawChars(g, m, this.charBuf, 0, s.length(), x + 4, 1 + formatter.calcBaselineOffset(m, this.lineHeight - 1 - 1), colWidth - 4 - 4);
            }
            x += colWidth;
            if (this.showGrid) {
                g.setColor(DataArea.gridLinesColor);
                g.drawLine(x - 1, 0, x - 1, this.lineHeight - 1);
            }
        }
        if (x < this.w) {
            g.setColor(this.bg);
            g.fillRect(x, 0, this.w - x, this.lineHeight);
        }
        g.setClip(oldClip);
        if (this.showGrid) {
            g.setColor(DataArea.gridLinesColor);
            g.drawLine(this.referenceColumnWidth, this.lineHeight - 1, x - 1, this.lineHeight - 1);
        }
        if (this.hasFocus && line == this.currentFocus) {
            this.paintFocus(g, Math.min(this.w, x));
        }
        final int destY = (this.vScrollPos + line) * this.lineHeight;
        graphics.drawImage(this.offScreen, 0, destY, this.w, destY + this.lineHeight, 0, 0, this.w, this.lineHeight, null);
    }
    
    private void paintLines(final Graphics g, final int start, int past) {
        final int lineCount = this.rows.size();
        if (past > lineCount) {
            past = lineCount;
        }
        for (int i = start; i < past; ++i) {
            this.paintLine(g, i, 0, this.columns.size());
        }
        if (past == lineCount) {
            this.eraseBottom(g, (this.vScrollPos + past) * this.lineHeight);
        }
    }
    
    void paintVisibleRows(final Graphics g) {
        final int firstIndex = -this.vScrollPos;
        final int pastIndex = firstIndex + this.getVisibleCount();
        this.paintLines(g, firstIndex, pastIndex);
    }
    
    private void prepareOffScreen(final Graphics g) {
        if (this.offScreen == null) {
            this.offScreen = this.createImage(this.w, this.lineHeight);
        }
        else if (this.offScreen.getWidth(null) != this.w || this.offScreen.getHeight(null) != this.lineHeight) {
            this.offScreen.flush();
            this.offScreen = this.createImage(this.w, this.lineHeight);
        }
        this.offScreenGraphics = this.offScreen.getGraphics();
    }
    
    public void processMouseEvent(final MouseEvent e) {
        if (e.isPopupTrigger() && this.popup != null) {
            this.popup.show(this, e.getX(), e.getY());
        }
        else {
            super.processMouseEvent(e);
        }
    }
    
    void removeActionListener(final ActionListener listener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, listener);
    }
    
    public void removeAdjustmentListenner(final AdjustmentListener l) {
        this.adjustmentListener = AWTEventMulticaster.remove(this.adjustmentListener, l);
    }
    
    void removeItemListener(final ItemListener listener) {
        this.itemListener = AWTEventMulticaster.remove(this.itemListener, listener);
    }
    
    void rowsInserted(final int startRow, final int pastRow) {
        final int count = pastRow - startRow;
        if (this.showCheckmarks) {
            this.checkmarks = Util.insertBits(this.checkmarks, startRow, count);
        }
        if (this.showButtons) {
            this.buttonPressedAccept = Util.insertBits(this.buttonPressedAccept, startRow, count);
            this.buttonShowAccept = Util.insertBits(this.buttonShowAccept, startRow, count);
        }
        this.selections = Util.insertBits(this.selections, startRow, count);
        if (startRow <= this.currentSelection) {
            if (this.currentSelection >= 0) {
                this.currentSelection += count;
            }
            this.currentFocus += count;
        }
        if (this.rows.size() - count > this.getCompletelyVisibleCount() && startRow <= -this.vScrollPos) {
            this.vScrollPos -= pastRow - startRow;
            final int maxVScrollPos = -(this.rows.size() - this.getCompletelyVisibleCount());
            this.vScrollPos = Math.max(this.vScrollPos, maxVScrollPos);
        }
    }
    
    void rowsRemoved(final int startRow, final int pastRow) {
        if (this.showCheckmarks) {
            this.checkmarks = Util.removeBits(this.checkmarks, startRow, pastRow);
        }
        if (this.showButtons) {
            this.buttonPressedAccept = Util.removeBits(this.buttonPressedAccept, startRow, pastRow);
            this.buttonShowAccept = Util.removeBits(this.buttonShowAccept, startRow, pastRow);
        }
        if (this.currentSelection >= startRow && this.currentSelection < pastRow) {
            this.currentSelection = -1;
        }
        else if (this.currentSelection >= pastRow) {
            this.currentSelection -= pastRow - startRow;
        }
        if (this.currentSelection < 0) {
            this.currentSelection = -1;
        }
        if (this.currentFocus >= pastRow) {
            this.currentFocus -= pastRow - startRow;
        }
        else if (this.currentFocus >= this.rows.size()) {
            this.currentFocus = this.rows.size() - 1;
        }
        if (this.currentFocus < 0) {
            this.currentFocus = 0;
        }
        for (int i = startRow; i < pastRow; ++i) {
            if (this.selections.get(i)) {
                --this.selectionCount;
            }
        }
        this.selections = Util.removeBits(this.selections, startRow, pastRow);
        if (startRow < -this.vScrollPos) {
            this.vScrollPos += pastRow - startRow;
        }
        if (this.vScrollPos < 0 && -this.vScrollPos > this.rows.size() - this.getCompletelyVisibleCount()) {
            this.vScrollPos = -(this.rows.size() - this.getCompletelyVisibleCount());
        }
        if (this.vScrollPos > 0) {
            this.vScrollPos = 0;
        }
    }
    
    void scrollTo(final int newScrollPos) {
        final Graphics g = this.getGraphics();
        final int visibleCount = this.getVisibleCount();
        if (newScrollPos < this.vScrollPos) {
            final int delta = this.vScrollPos - newScrollPos;
            final int remainingLine = this.getHasFillerLine() ? 1 : 0;
            if (delta >= visibleCount) {
                this.vScrollPos = newScrollPos;
                this.paintLines(g, -this.vScrollPos, -this.vScrollPos + visibleCount);
            }
            else {
                final int topCopyLine = delta;
                final int bottomCopyLine = visibleCount - remainingLine;
                g.copyArea(0, topCopyLine * this.lineHeight, this.w, (bottomCopyLine - topCopyLine) * this.lineHeight, 0, -delta * this.lineHeight);
                this.vScrollPos = newScrollPos;
                this.paintLines(g, -this.vScrollPos + bottomCopyLine - delta, -this.vScrollPos + visibleCount);
                final int bottom = (this.vScrollPos + this.rows.size()) * this.lineHeight;
                if (bottom < this.h) {
                    this.eraseBottom(g, bottom);
                }
            }
        }
        else if (newScrollPos > this.vScrollPos) {
            final int delta = newScrollPos - this.vScrollPos;
            final int remainingLine = this.getHasFillerLine() ? 1 : 0;
            if (delta >= visibleCount) {
                this.vScrollPos = newScrollPos;
                this.paintLines(g, -this.vScrollPos, -this.vScrollPos + visibleCount);
            }
            else {
                final int topCopyLine = 0;
                final int bottomCopyLine = visibleCount - delta;
                g.copyArea(0, 0, this.w, (visibleCount - delta) * this.lineHeight, 0, delta * this.lineHeight);
                this.vScrollPos = newScrollPos;
                this.paintLines(g, -this.vScrollPos, -this.vScrollPos + delta);
            }
        }
    }
    
    public void scrollToRow(final int row) {
        if (row >= -this.vScrollPos && row < -this.vScrollPos + this.getCompletelyVisibleCount()) {
            return;
        }
        int newScrollPos = Math.min(row, this.rows.size() - this.getCompletelyVisibleCount());
        if (newScrollPos < 0) {
            newScrollPos = 0;
        }
        this.scrollTo(-newScrollPos);
        if (this.vScrollbar != null) {
            this.vScrollbar.setValue(newScrollPos);
        }
    }
    
    void select(final int index) {
        this.select(index, true);
    }
    
    void select(final int index, final boolean state) {
        if (this.selections.get(index) != state) {
            if (state) {
                ++this.selectionCount;
                this.selections.set(index);
            }
            else {
                --this.selectionCount;
                this.selections.clear(index);
            }
            if (this.selectionCount == 0) {
                this.currentSelection = -1;
            }
            else if (this.selectionCount == 1) {
                this.currentSelection = index;
            }
            if (index >= -this.vScrollPos && index <= -this.vScrollPos + this.getVisibleCount()) {
                this.paintLine(index);
            }
        }
    }
    
    void selectAll() {
        for (int i = 0; i < this.rows.size(); ++i) {
            this.select(i, true);
        }
    }
    
    private void selectRange(final int first, final int last) {
        int top;
        int bottom;
        if (first <= last) {
            top = first;
            bottom = last;
        }
        else {
            top = last;
            bottom = first;
        }
        for (int i = top; i <= bottom; ++i) {
            this.select(i, true);
        }
    }
    
    private void selectRangeExclusive(final int first, final int last) {
        int top;
        int bottom;
        if (first <= last) {
            top = first;
            bottom = last;
        }
        else {
            top = last;
            bottom = first;
        }
        for (int i = 0; i < top; ++i) {
            this.select(i, false);
        }
        for (int i = top; i <= bottom; ++i) {
            this.select(i, true);
        }
        for (int i = bottom + 1; i < this.rows.size(); ++i) {
            this.select(i, false);
        }
    }
    
    void setCheckedIndexes(final int[] indexes) throws IllegalArgumentException {
        this.uncheckAll(false);
        try {
            for (int i = 0; i < indexes.length; ++i) {
                this.check(indexes[i], true);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
        finally {
            if (this.getGraphics() != null) {
                this.paint(this.getGraphics());
            }
        }
        if (this.getGraphics() != null) {
            this.paint(this.getGraphics());
        }
    }
    
    public void setCheckingSelects(final boolean state) {
        this.checkingSelects = state;
    }
    
    public static void setCheckMarkColumnWidth(final int w) {
        DataArea.checkMarkColumnWidth = w;
    }
    
    public void setColumnWidths(final int[] widths) throws IllegalArgumentException {
        if (widths == null || this.columns.size() != widths.length) {
            throw new IllegalArgumentException("# of columns differs from # of widths.");
        }
        this.columnWidths = widths;
    }
    
    public static void setDefaultFormatter(final StringFormatter f) {
        DataArea.defaultFormatter = f;
    }
    
    private void setDefaultWidths() {
        this.columnWidths = new int[this.columns.size()];
        for (int i = 0; i < this.columnWidths.length; ++i) {
            this.columnWidths[i] = this.columns.elementAt(i).getPreferredWidth(this.getFontMetrics(this.getFont()), DataArea.defaultFormatter);
            final int[] columnWidths = this.columnWidths;
            final int n = i;
            columnWidths[n] += 8;
        }
    }
    
    public void setEnabled(final boolean enable) {
        super.setEnabled(enable);
        this.paintVisibleRows(this.getGraphics());
    }
    
    public static void setGridLinesColor(final Color c) {
        DataArea.gridLinesColor = c;
    }
    
    void setHorizontalScrollPos(final int pos) {
        this.hScrollPos = pos;
    }
    
    public static void setLineNumberColumnWidth(final int w) {
        DataArea.lineNumberColumnWidth = w;
    }
    
    public static void setLineNumberFormatter(final StringFormatter f) {
        DataArea.lineNumberFormatter = f;
    }
    
    public void setPreferredHeight(final int numberOfRows) {
        this.preferredHeight = numberOfRows;
    }
    
    public static void setReferenceColumnBackground(final Color c) {
        DataArea.referenceColumnBackground = c;
    }
    
    public static void setReferenceColumnBorder(final Border b) {
        b.setBackground(DataArea.referenceColumnBackground);
        b.setForeground(DataArea.referenceColumnForeground);
        DataArea.referenceColumnBorder = b;
    }
    
    public static void setReferenceColumnFont(final Font f) {
        DataArea.referenceColumnFont = f;
    }
    
    public static void setReferenceColumnForeground(final Color c) {
        DataArea.referenceColumnForeground = c;
    }
    
    public void setRowHeightFactor(final double factor) {
        this.lineHeightFactor = factor;
    }
    
    public void setRows(final Vector rows) {
        this.rows = rows;
    }
    
    public void setAllowRowDragDrop(final boolean bVal) {
        this.m_bAllowRowDragDrop = bVal;
    }
    
    public boolean getAllowRowDragDrop() {
        return this.m_bAllowRowDragDrop;
    }
    
    void setScrollbars(final Scrollbar vScrollbar, final Scrollbar hScrollbar) {
        if (this.vScrollbar != null) {
            this.vScrollbar.removeAdjustmentListener(this);
        }
        if (this.hScrollbar != null) {
            this.hScrollbar.removeAdjustmentListener(this);
        }
        vScrollbar.addAdjustmentListener(this);
        hScrollbar.addAdjustmentListener(this);
        this.vScrollbar = vScrollbar;
        this.hScrollbar = hScrollbar;
    }
    
    void setSelectedIndexes(final int[] indexes) throws IllegalArgumentException {
        this.deselectAll(false);
        try {
            for (int i = 0; i < indexes.length; ++i) {
                this.select(indexes[i], true);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
        finally {
            this.paintVisibleRows(this.getGraphics());
        }
        this.paintVisibleRows(this.getGraphics());
    }
    
    void setSelectionMode(final int mode) {
        this.deselectAll(true);
        this.selectionMode = mode;
        if (mode == 1 && this.rows.size() > 0) {
            this.selections.set(0);
            this.currentSelection = 0;
            this.currentFocus = 0;
            this.selectionCount = 1;
        }
        else {
            this.currentSelection = -1;
            this.currentFocus = 0;
            this.selectionCount = 0;
        }
        if (this.getGraphics() != null) {
            this.paint(this.getGraphics());
        }
    }
    
    void setShowCheckmarks(final boolean b) {
        this.showCheckmarks = b;
    }
    
    void setShowButtons(final boolean b) {
        this.showButtons = b;
    }
    
    public void setShowGrid(final boolean b) {
        this.showGrid = b;
    }
    
    void setShowLineNumbers(final boolean b) {
        this.showLineNumbers = b;
    }
    
    void setVerticalScrollPos(final int pos) {
        this.vScrollPos = pos;
    }
    
    public void shiftRows(final int iFrom, final int iTo) {
        int iDir = 1;
        if (iFrom < 0 || iTo < 0) {
            return;
        }
        if (iFrom > iTo) {
            iDir = -1;
        }
        for (int i = iFrom; i != iTo; i += iDir) {
            final Object tmp = this.rows.elementAt(i);
            this.rows.setElementAt(this.rows.elementAt(i + iDir), i);
            this.rows.setElementAt(tmp, i + iDir);
            this.swapped(i, i + iDir);
        }
    }
    
    public void swapped(final int i, final int j) {
        if (this.showCheckmarks) {
            final boolean jIsSet = this.checkmarks.get(j);
            if (this.checkmarks.get(i)) {
                this.checkmarks.set(j);
            }
            else {
                this.checkmarks.clear(j);
            }
            if (jIsSet) {
                this.checkmarks.set(i);
            }
            else {
                this.checkmarks.clear(i);
            }
        }
        if (this.showButtons) {
            boolean jIsSet = this.buttonPressedAccept.get(j);
            if (this.buttonPressedAccept.get(i)) {
                this.buttonPressedAccept.set(j);
            }
            else {
                this.buttonPressedAccept.clear(j);
            }
            if (jIsSet) {
                this.buttonPressedAccept.set(i);
            }
            else {
                this.buttonPressedAccept.clear(i);
            }
            jIsSet = this.buttonShowAccept.get(j);
            if (this.buttonShowAccept.get(i)) {
                this.buttonShowAccept.set(j);
            }
            else {
                this.buttonShowAccept.clear(j);
            }
            if (jIsSet) {
                this.buttonShowAccept.set(i);
            }
            else {
                this.buttonShowAccept.clear(i);
            }
        }
        boolean jIsSet = this.selections.get(j);
        if (this.selections.get(i)) {
            this.selections.set(j);
        }
        else {
            this.selections.clear(j);
        }
        if (jIsSet) {
            this.selections.set(i);
        }
        else {
            this.selections.clear(i);
        }
    }
    
    void uncheck(final int index) {
        this.check(index, false);
    }
    
    void uncheckAll(final boolean redraw) {
        if (redraw) {
            final int firstIndex = -this.vScrollPos;
            for (int pastIndex = firstIndex + this.getVisibleCount(), i = firstIndex; i < pastIndex; ++i) {
                if (this.checkmarks.get(i)) {
                    this.checkmarks.clear(i);
                    this.paintLine(i);
                }
            }
        }
        this.checkmarks = new BitSet();
    }
    
    void updateHorizontalScrollbar() {
        if (this.hScrollbar == null) {
            return;
        }
        final int value = this.hScrollbar.getValue();
        final int visible = this.getSize().width;
        final int max = this.getPreferredSize().width;
        this.hScrollbar.setValues(value, visible, 0, max);
        this.hScrollbar.setBlockIncrement(visible);
        this.hScrollbar.setUnitIncrement(50);
    }
    
    void updateVerticalScrollbar() {
        if (this.vScrollbar == null) {
            return;
        }
        final int visible = this.getVisibleCount();
        final int filler = this.getHasFillerLine() ? 1 : 0;
        this.vScrollbar.setValues(-this.vScrollPos, visible, 0, this.rows.size() + filler);
        this.vScrollbar.setBlockIncrement(visible - filler);
    }
}
