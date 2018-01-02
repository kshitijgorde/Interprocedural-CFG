// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.listbox;

import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.AWTEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ItemEvent;
import com.objectbox.runner.util.JBLogger;
import java.awt.FontMetrics;
import java.awt.AWTEventMulticaster;
import com.objectbox.runner.gui.text.Text;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import java.awt.Image;
import com.objectbox.runner.gui.text.ExpandableText;
import java.awt.Point;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.ItemSelectable;
import com.objectbox.runner.gui.obbase.OBBase;

public class ListBox extends OBBase implements ItemSelectable, ActionListener
{
    public static final int LVXC_UNINITIALIZED = -1;
    protected int m_nWidthGap;
    protected int m_nRowSpace;
    public static final int FMT_LEFT = 0;
    public static final int FMT_CENTER = 1;
    public static final int FMT_RIGHT = 2;
    public static final int NONE = 0;
    public static final int RESIZE = 1;
    public static final int DEFAULT_COL_WIDTH = 100;
    protected int DottedLineSpace;
    protected int DottedLineFill;
    protected Rectangle m_recPCRect;
    protected Column m_colPCol;
    protected int m_lvi_iSubItem;
    protected int m_lvi_iItem;
    protected int m_PCheight;
    protected String m_lvi_pszText;
    protected Color m_colGridLines;
    protected Color m_colText;
    protected Color m_colHighlightText;
    protected Color m_colHighlightTextBackground;
    protected int m_nStyleGridLines;
    public static final int DOTTED = 0;
    public static final int SOLID = 1;
    protected String commandTopRowChanged;
    protected String commandDoubleClicked;
    protected String commandLeftColChanged;
    protected String commandItemDeleted;
    public static String commandDragDrop;
    public static final int DRAGDROP = 3001;
    protected ItemListener itemListener;
    protected ActionListener actionListener;
    protected int[] selected;
    protected int prev;
    protected boolean m_bMouseDrag;
    protected boolean m_bHilightSubItems;
    public Font defaultfont;
    protected Vector m_arrColumns;
    protected Vector m_arrItems;
    protected int m_nSubItemCount;
    protected int m_nTopRow;
    protected Point m_ptViewportOrg;
    protected int m_nItemsThisPage;
    protected boolean m_bMultipleSelections;
    protected boolean m_bColumnHeader;
    protected int m_cyHeader;
    protected boolean m_bColumnLines;
    protected boolean m_bItemLines;
    boolean m_bCaptured;
    protected boolean m_bEditModeAllowed;
    protected boolean m_bEditModeEnabled;
    protected boolean m_bOverlapEdit;
    protected int m_nColumnEdit;
    protected ListItem m_itemCurrentEdit;
    protected ExpandableText m_textEditNode;
    protected Vector m_arrImages;
    protected Vector m_arrImageIDs;
    protected int m_nOldTargetIndex;
    protected int m_nDraggingCurrent;
    protected int m_nDraggingBegin;
    protected int m_nDraggingState;
    protected Rectangle m_rectDragging;
    protected boolean m_bAutoWrap;
    private String OSName;
    protected boolean m_bDragModeEnabled;
    protected boolean m_bDragModeAllowed;
    protected boolean m_bDrawDragImage;
    protected int m_nXMouse;
    protected int m_nYMouse;
    protected Image m_imgDragPic;
    protected int m_dropTargetItem;
    protected boolean m_bDragDropDrawn;
    protected boolean m_bAllowDelete;
    protected boolean m_bShowDotRect;
    protected boolean m_bHasFocus;
    transient boolean m_bSelectedItemChanged;
    protected transient ItemTextChangedListener aItemTextChangedListener;
    public String m_oldName;
    
    static {
        ListBox.commandDragDrop = "Drag_Drop";
    }
    
    public ListBox() {
        this(false);
        this.setLayout(null);
    }
    
    public ListBox(final boolean bMultipleSelections) {
        super(false);
        this.m_nWidthGap = 11;
        this.m_nRowSpace = 0;
        this.DottedLineSpace = 1;
        this.DottedLineFill = 1;
        this.m_recPCRect = new Rectangle();
        this.m_colPCol = new Column();
        this.m_lvi_iSubItem = 0;
        this.m_lvi_iItem = 0;
        this.m_PCheight = -1;
        this.m_lvi_pszText = null;
        this.m_colGridLines = SystemColor.windowBorder;
        this.m_colText = SystemColor.textText;
        this.m_colHighlightText = SystemColor.textHighlightText;
        this.m_colHighlightTextBackground = SystemColor.textHighlight;
        this.m_nStyleGridLines = 1;
        this.commandTopRowChanged = "Top_Row_Changed";
        this.commandDoubleClicked = "Double_Clicked";
        this.commandLeftColChanged = "Left_Col_Changed";
        this.commandItemDeleted = "Item_Deleted";
        this.selected = new int[0];
        this.prev = -1;
        this.m_bMouseDrag = false;
        this.m_bHilightSubItems = true;
        this.defaultfont = new Font("Dialog", 0, 12);
        this.m_arrColumns = new Vector();
        this.m_arrItems = new Vector();
        this.m_nSubItemCount = 0;
        this.m_nTopRow = 0;
        this.m_ptViewportOrg = new Point(0, 0);
        this.m_nItemsThisPage = 0;
        this.m_bMultipleSelections = true;
        this.m_bColumnHeader = false;
        this.m_cyHeader = 0;
        this.m_bColumnLines = false;
        this.m_bItemLines = false;
        this.m_bCaptured = false;
        this.m_bEditModeAllowed = false;
        this.m_bEditModeEnabled = false;
        this.m_bOverlapEdit = false;
        this.m_nColumnEdit = 0;
        this.m_itemCurrentEdit = new ListItem();
        this.m_textEditNode = null;
        this.m_arrImages = null;
        this.m_arrImageIDs = null;
        this.m_nOldTargetIndex = -1;
        this.m_nDraggingState = 0;
        this.m_rectDragging = new Rectangle();
        this.m_bAutoWrap = true;
        this.m_bDragModeEnabled = false;
        this.m_bDragModeAllowed = true;
        this.m_bDrawDragImage = true;
        this.m_nXMouse = -100;
        this.m_nYMouse = -100;
        this.m_imgDragPic = null;
        this.m_dropTargetItem = -1;
        this.m_bDragDropDrawn = false;
        this.m_bAllowDelete = false;
        this.m_bShowDotRect = true;
        this.m_bHasFocus = false;
        this.m_bSelectedItemChanged = false;
        this.aItemTextChangedListener = null;
        this.m_oldName = "";
        this.m_bMultipleSelections = bMultipleSelections;
        super.m_bAlwaysShowScrollbar = false;
        this.setBackground(Color.white);
        this.insertColumn(0, "", 0, -1, 0, false);
        this.OSName = System.getProperty("os.name");
        (this.m_textEditNode = new ExpandableText("")).addActionListener(this);
        this.add(this.m_textEditNode);
        this.m_textEditNode.setVisible(false);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.m_textEditNode && actionEvent.getActionCommand().equals(Text.endEditing)) {
            this.changeItemText();
        }
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public synchronized void addItem(final ListItem listItem) {
        this.addItem(listItem, -1, true);
    }
    
    public synchronized void addItem(final ListItem listItem, final int image, final boolean b) {
        listItem.setImage(image);
        this.m_arrItems.insertElementAt(listItem, this.m_arrItems.size());
        if (this.m_nSubItemCount > listItem.getSubItemCount() + 1) {
            for (int i = listItem.getSubItemCount(); i < this.m_nSubItemCount - 1; ++i) {
                this.m_arrItems.elementAt(this.m_arrItems.size() - 1).addSubItem();
            }
        }
        if (b) {
            this.update();
        }
    }
    
    public synchronized void addItem(final ListItem listItem, final boolean b) {
        this.addItem(listItem, -1, b);
    }
    
    public synchronized void addItem(final Object o) {
        this.addItem(o, -1, true);
    }
    
    public synchronized void addItem(final Object o, final int n, final boolean b) {
        if (o != null && o instanceof String) {
            this.addItem((String)o, n, b);
        }
        else if (o != null && o instanceof ListItem) {
            this.addItem((ListItem)o, n, b);
        }
    }
    
    public synchronized void addItem(final Object o, final boolean b) {
        this.addItem(o, -1, b);
    }
    
    public synchronized void addItem(final String s) {
        this.addItem(s, -1, true);
    }
    
    public synchronized void addItem(final String text, final int image, final boolean b) {
        final ListItem listItem = new ListItem();
        listItem.setText(text);
        listItem.setImage(image);
        this.m_arrItems.addElement(listItem);
        if (this.m_nSubItemCount > listItem.getSubItemCount() + 1) {
            for (int i = listItem.getSubItemCount(); i < this.m_nSubItemCount - 1; ++i) {
                this.m_arrItems.elementAt(this.m_arrItems.size() - 1).addSubItem();
            }
        }
        if (b) {
            this.updateScrollbar();
            this.doLayout();
            this.repaint();
        }
    }
    
    public synchronized void addItem(final String s, final Font font) {
        this.addItem(s, font, -1);
    }
    
    public synchronized void addItem(final String text, final Font font, final int image) {
        final ListItem listItem = new ListItem();
        listItem.setText(text);
        listItem.setFont(font);
        listItem.setImage(image);
        this.m_arrItems.addElement(listItem);
        if (this.m_nSubItemCount > listItem.getSubItemCount() + 1) {
            for (int i = listItem.getSubItemCount(); i < this.m_nSubItemCount - 1; ++i) {
                this.m_arrItems.elementAt(this.m_arrItems.size() - 1).addSubItem();
            }
        }
    }
    
    public synchronized void addItem(final String s, final boolean b) {
        this.addItem(s, -1, b);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.itemListener = AWTEventMulticaster.add(this.itemListener, itemListener);
    }
    
    public void addItemTextChangedListener(final ItemTextChangedListener itemTextChangedListener) {
        this.aItemTextChangedListener = ItemTextChangedEventMulticaster.add(this.aItemTextChangedListener, itemTextChangedListener);
    }
    
    public void addNotify() {
        super.addNotify();
        final Font font = this.getFont();
        FontMetrics fontMetrics;
        if (font != null) {
            fontMetrics = this.getFontMetrics(font);
            this.defaultfont = font;
        }
        else {
            fontMetrics = this.getFontMetrics(this.defaultfont);
        }
        this.m_PCheight = fontMetrics.getHeight();
        if (this.m_arrColumns.size() == 1) {
            if (this.m_bAutoWrap) {
                this.m_arrColumns.elementAt(0).m_nCX = this.getInsideRect().width;
                this.reMeasureAllItems();
            }
            else {
                this.m_arrColumns.elementAt(0).m_nCX = this.getLogicalSize().x;
                if (this.getLogicalSize().x < this.getInsideRect().width) {
                    this.m_arrColumns.elementAt(0).m_nCX = this.getInsideRect().width;
                }
                this.reMeasureAllItems();
            }
        }
    }
    
    public synchronized void addSubItem(final int n, final String s) {
        this.addSubItem(n, s, true);
    }
    
    public synchronized void addSubItem(final int n, final String s, final boolean b) {
        if (n <= this.m_arrItems.size() - 1) {
            this.m_arrItems.elementAt(n).addSubItem(s);
            if (b && this.m_arrColumns.size() - 1 < this.m_arrItems.elementAt(n).getSubItemCount()) {
                for (int subItemCount = this.m_arrItems.elementAt(n).getSubItemCount(), i = this.m_arrColumns.size() - 1; i < subItemCount; ++i) {
                    this.insertColumn(i, "", 0, -1, -1, false);
                }
            }
        }
    }
    
    public int calcItemsInRange(final int n, final int n2, final boolean b) {
        final ListItem listItem = new ListItem();
        int n3 = 0;
        int n4 = 0;
        if (b) {
            for (int i = n2; i < this.m_arrItems.size(); ++i) {
                n3 += ((ListItem)this.m_arrItems.elementAt(i)).m_nCY;
                ++n4;
                if (n3 >= n) {
                    break;
                }
            }
        }
        else {
            for (int j = n2; j >= 0; --j) {
                n3 += ((ListItem)this.m_arrItems.elementAt(j)).m_nCY;
                ++n4;
                if (n3 >= n) {
                    break;
                }
            }
        }
        return n4;
    }
    
    public int calcRangeHeight(int n, int n2, final boolean b) {
        final ListItem listItem = new ListItem();
        boolean b2 = false;
        if (n > n2) {
            final int n3 = n;
            n = n2;
            n2 = n3;
            b2 = true;
        }
        int n4 = 0;
        for (int n5 = n; n5 < n2 || (b && n5 <= n2); ++n5) {
            final ListItem listItem2 = this.m_arrItems.elementAt(n5);
            if (listItem2.m_nCY == -1) {
                this.measureItem(n5, this.getGraphics());
            }
            n4 += listItem2.m_nCY;
        }
        if (b2) {
            n4 *= -1;
        }
        return n4;
    }
    
    protected void changeItemText() {
        if (this.m_nColumnEdit == 0) {
            this.m_itemCurrentEdit.setText(this.m_textEditNode.getText());
        }
        else {
            this.m_itemCurrentEdit.getSubItem(this.m_nColumnEdit).setText(this.m_textEditNode.getText());
        }
        this.m_textEditNode.stop();
        this.m_textEditNode.setVisible(false);
        this.reMeasureAllItems();
        this.update();
    }
    
    public synchronized void clear() {
        this.m_arrItems = new Vector();
        this.selected = new int[0];
        this.prev = -1;
        this.m_nTopRow = 0;
        this.updateScrollbar();
        this.update();
    }
    
    public int countItems() {
        return this.getItemCount();
    }
    
    public ListItem createNewItem() {
        return new ListItem();
    }
    
    public synchronized boolean deleteColumn(final int n, final boolean b) {
        final int size = this.m_arrColumns.size();
        if (n < 1 || n >= size) {
            return false;
        }
        for (int i = n + 1; i < size; ++i) {
            final Column column = this.m_arrColumns.elementAt(i);
            --column.m_iSubItem;
        }
        this.m_arrColumns.removeElementAt(n);
        for (int j = 0; j < this.m_arrItems.size(); ++j) {
            ((ListItem)this.m_arrItems.elementAt(j)).deleteSubItem(n);
        }
        --this.m_nSubItemCount;
        if (b) {
            this.updateScrollbar();
            this.doLayout();
            this.repaint();
        }
        return true;
    }
    
    public synchronized boolean deleteItem(final int n) {
        return this.deleteItem(n, 1);
    }
    
    public synchronized boolean deleteItem(final int n, final int n2) {
        return this.deleteItem(n, n2, true);
    }
    
    public synchronized boolean deleteItem(final int n, final int n2, final boolean b) {
        int n3 = n + n2 - 1;
        if (n3 > this.m_arrItems.size() - 1) {
            n3 = this.m_arrItems.size() - 1;
        }
        final Vector vector = new Vector<ListItem>();
        try {
            for (int i = 0; i < this.selected.length; ++i) {
                vector.addElement(this.m_arrItems.elementAt(this.selected[i]));
            }
        }
        catch (Throwable t) {
            JBLogger.log("Exception in Listbox::deleteItem: " + t);
        }
        for (int j = n; j <= n3; --n3, --j, ++j) {
            final ListItem listItem = this.m_arrItems.elementAt(j);
            if (this.isSelected(j)) {
                this.deselect(j);
            }
            this.m_arrItems.removeElementAt(j);
            vector.removeElement(listItem);
        }
        for (int k = 0; k < vector.size(); ++k) {
            this.select(this.getIndex(vector.elementAt(k)), false);
        }
        this.processActionEvent(new ActionEvent(this, 1001, this.commandItemDeleted));
        if (b) {
            this.updateScrollbar();
            this.doLayout();
            this.update();
        }
        return true;
    }
    
    public synchronized boolean deleteItem(final int n, final boolean b) {
        return this.deleteItem(n, 1, b);
    }
    
    public synchronized void deleteItems(final int n, final int n2) {
        if (n < 0 || n >= this.countItems() || n2 < 0 || n2 >= this.countItems()) {
            return;
        }
        this.deleteItem(n, n2 - n + 1, true);
    }
    
    public synchronized void delItem(final int n) {
        this.deleteItem(n);
    }
    
    public synchronized void delItems(final int n, final int n2) {
        this.deleteItems(n, n2);
    }
    
    public synchronized void deselect(final int n) {
        for (int i = 0; i < this.selected.length; ++i) {
            if (this.selected[i] == n) {
                final int[] selected = new int[this.selected.length - 1];
                System.arraycopy(this.selected, 0, selected, 0, i);
                System.arraycopy(this.selected, i + 1, selected, i, this.selected.length - (i + 1));
                this.selected = selected;
                this.processItemEvent(new ItemEvent(this, 701, new Integer(n), 2));
                return;
            }
        }
    }
    
    public synchronized void deselectAll() {
        this.deselectAll(true);
    }
    
    public synchronized void deselectAll(final boolean b) {
        this.selected = new int[0];
        this.prev = -1;
        if (b) {
            this.update();
        }
        this.processItemEvent(new ItemEvent(this, 701, null, 2));
    }
    
    public void doLayout() {
        synchronized (this.getTreeLock()) {
            final Scrollbar vScrollbar = this.getVScrollbar();
            final Scrollbar hScrollbar = this.getHScrollbar();
            this.getInsideRect();
            final Rectangle bounds = this.getBounds();
            final int width = bounds.width;
            final int height = bounds.height;
            if (super.m_bAlwaysShowScrollbar || this.getMaxTopRow() != 0) {
                if (this.getMaxLeftCol() != 0 || super.m_bAlwaysShowScrollbar) {
                    hScrollbar.setBounds(0, height - super.m_nHScrollbarHeight, width - super.m_nVScrollbarWidth, super.m_nHScrollbarHeight);
                }
            }
            else if (this.getMaxLeftCol() != 0 || super.m_bAlwaysShowScrollbar) {
                hScrollbar.setBounds(0, height - super.m_nHScrollbarHeight, width, super.m_nHScrollbarHeight);
            }
            if (super.m_bAlwaysShowScrollbar || this.getMaxLeftCol() != 0) {
                if (this.getMaxTopRow() > 0 || super.m_bAlwaysShowScrollbar) {
                    vScrollbar.setBounds(width - super.m_nVScrollbarWidth, 0, super.m_nVScrollbarWidth, height - super.m_nHScrollbarHeight);
                }
            }
            else if (this.getMaxTopRow() > 0 || super.m_bAlwaysShowScrollbar) {
                vScrollbar.setBounds(width - super.m_nVScrollbarWidth, 0, super.m_nVScrollbarWidth, height);
            }
        }
        // monitorexit(this.getTreeLock())
    }
    
    protected void doubleClickEvent(final int n) {
    }
    
    public void draw(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this.m_nItemsThisPage == 0) {
            this.updateScrollbar();
        }
        this.getInsideRect();
        if (this.isHeaderCtrlEnabled()) {
            this.drawHeader(graphics);
        }
        if (this.m_arrItems.size() > 0) {
            if (this.m_nDraggingState == 0) {
                this.drawInvalidItems(graphics);
            }
            if (super.vs != null && super.vs.isShowing() && super.hs != null && super.hs.isShowing()) {
                final Rectangle insideRect = this.getInsideRect();
                final Rectangle rectangle = bounds;
                final Rectangle rectangle2 = new Rectangle();
                rectangle2.x = rectangle.width - super.vs.getBounds().width;
                rectangle2.width = rectangle.width - insideRect.width;
                rectangle2.y = 0 + rectangle.height - super.hs.getBounds().height;
                rectangle2.height = rectangle.height - insideRect.height;
                graphics.clearRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            }
        }
        this.paintBorder(graphics, 0, 0, bounds.width, bounds.height, false);
    }
    
    public void drawColumnLines(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(this.m_colGridLines);
        int n = -this.m_ptViewportOrg.x;
        for (int i = 0; i < this.m_arrColumns.size(); ++i) {
            n += ((Column)this.m_arrColumns.elementAt(i)).getWidth();
            if (this.m_nStyleGridLines == 0) {
                this.drawDottedLine(graphics, n, this.m_cyHeader, n, bounds.y + bounds.height);
            }
            else {
                graphics.drawLine(n, this.m_cyHeader, n, bounds.y + bounds.height);
            }
        }
    }
    
    protected void drawDottedLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = Math.abs(n - n3);
        boolean b = false;
        if (n5 == 0) {
            n5 = Math.abs(n2 - n4);
            b = true;
        }
        for (int i = 0; i <= n5; i += this.DottedLineSpace + this.DottedLineFill) {
            if (b) {
                if (n2 < n4) {
                    graphics.drawLine(n, n2 + i, n, n2 + i + this.DottedLineFill - 1);
                }
                else {
                    graphics.drawLine(n, n4 + i, n, n4 + i + this.DottedLineFill - 1);
                }
            }
            else if (n < n3) {
                graphics.drawLine(n + i, n2, n + i + this.DottedLineFill - 1, n2);
            }
            else {
                graphics.drawLine(n3 + i, n2, n3 + i + this.DottedLineFill - 1, n2);
            }
        }
    }
    
    protected void drawDottedRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color color = graphics.getColor();
        graphics.setColor(SystemColor.control);
        graphics.drawRect(n, n2, n3, n4);
        graphics.setColor(color);
        this.drawDottedLine(graphics, n + 1, n2, n + n3, n2);
        this.drawDottedLine(graphics, n, n2, n, n2 + n4);
        this.drawDottedLine(graphics, n + n3, n2, n + n3, n2 + n4);
        this.drawDottedLine(graphics, n, n2 + n4, n + n3, n2 + n4);
    }
    
    public void drawFocusRect(final Graphics graphics, final int n, int n2, final int n3, final int n4) {
        if (this.m_bHasFocus && this.getSelectedListItem() == null) {
            final Rectangle textBounds = this.getItemAt(this.m_nTopRow).getTextBounds();
            if (this.isColumnHeader()) {
                n2 += this.getColumnHeaderHeight();
            }
            this.drawDottedRect(graphics, n + 3, n2 + 3, n3 - 6, textBounds.height);
        }
    }
    
    private void drawHeader(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle();
        final Rectangle bounds = this.getBounds();
        final Point logicalSize = this.getLogicalSize();
        rectangle.y = 0;
        rectangle.height = this.m_cyHeader - 1;
        rectangle.x = -this.m_ptViewportOrg.x;
        rectangle.width = Math.max(logicalSize.x, bounds.width + 1);
        graphics.setColor(this.getBackground());
        graphics.fillRect(rectangle.x - 2, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(SystemColor.control);
        graphics.fill3DRect(rectangle.x - 2, rectangle.y, rectangle.width, rectangle.height, true);
        this.paintBorder(graphics, rectangle.x + 2, rectangle.y + 2, rectangle.width - 2, rectangle.height - 2, true);
        if (this.m_arrColumns.size() > 1) {
            for (int size = this.m_arrColumns.size(), i = 0; i < size; ++i) {
                rectangle.width = ((Column)this.m_arrColumns.elementAt(i)).m_nCX;
                graphics.drawLine(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
                rectangle.x += rectangle.width;
            }
        }
        rectangle.x = -this.m_ptViewportOrg.x;
        for (int j = 0; j < this.m_arrColumns.size(); ++j) {
            final Column column = this.m_arrColumns.elementAt(j);
            rectangle.width = column.m_nCX - 1;
            graphics.setFont(column.m_fonFont);
            String strText = column.m_strText;
            final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
            final int stringWidth = fontMetrics.stringWidth(column.m_strText);
            String string = "";
            final int n = 0;
            if (stringWidth > column.m_nCX - this.m_nWidthGap) {
                final int stringWidth2 = fontMetrics.stringWidth(".");
                if (n + this.m_nWidthGap + stringWidth2 > column.m_nCX) {
                    string = " ";
                }
                else if (n + this.m_nWidthGap + 2 * stringWidth2 > column.m_nCX) {
                    final int n2 = n + this.m_nWidthGap + stringWidth2;
                    string = ".";
                }
                else if (n + this.m_nWidthGap + 3 * stringWidth2 > column.m_nCX) {
                    final int n3 = n + this.m_nWidthGap + stringWidth2;
                    string = "..";
                }
                else {
                    int n4;
                    for (n4 = 1; n4 < strText.length() && n + this.m_nWidthGap * 2 + fontMetrics.stringWidth(strText.substring(0, n4 - 1)) + 3 * stringWidth2 <= column.m_nCX; ++n4) {}
                    string = String.valueOf(strText.substring(0, n4 - 1)) + "...";
                    final int n5 = n + this.m_nWidthGap + fontMetrics.stringWidth(string);
                }
            }
            if (fontMetrics.stringWidth(column.m_strText) > column.m_nCX - this.m_nWidthGap) {
                strText = string;
            }
            int n6 = rectangle.x + this.m_nWidthGap;
            final int n7 = rectangle.y + rectangle.height - rectangle.height / 4;
            final int n8 = this.m_nWidthGap + fontMetrics.stringWidth(strText);
            if (column.m_nHeaderFmt == 1) {
                if (n8 < column.m_nCX) {
                    n6 = column.m_nCX / 2 - (n8 - this.m_nWidthGap) / 2 + n6 - this.m_nWidthGap;
                }
            }
            else if (column.m_nHeaderFmt == 2 && n8 < column.m_nCX) {
                n6 = column.m_nCX - (n8 - this.m_nWidthGap) + n6 - 2 * this.m_nWidthGap;
            }
            graphics.drawString(strText, n6, n7);
            rectangle.x = rectangle.x + rectangle.width + 1;
        }
    }
    
    public void drawInvalidItems(final Graphics graphics) {
        final Rectangle recPCRect = new Rectangle();
        final int size = this.m_arrItems.size();
        final Rectangle insideRect = this.getInsideRect();
        final Point logicalSize = this.getLogicalSize();
        recPCRect.y = insideRect.y;
        final Rectangle rectangle = recPCRect;
        rectangle.y += 2;
        recPCRect.x = -this.m_ptViewportOrg.x;
        recPCRect.width = logicalSize.x;
        recPCRect.height = 0;
        int n = 0;
        this.m_lvi_iItem = this.m_nTopRow;
        while (this.m_lvi_iItem < size) {
            if (this.m_arrItems.elementAt(this.m_lvi_iItem).m_nCY == -1) {
                this.m_arrItems.elementAt(this.m_lvi_iItem).m_nCY = this.measureItem(graphics);
            }
            final Rectangle rectangle2 = recPCRect;
            rectangle2.height += this.m_arrItems.elementAt(this.m_lvi_iItem).m_nCY;
            final Rectangle bounds = this.getBounds();
            bounds.x = 0;
            final Rectangle rectangle3 = bounds;
            rectangle3.width *= 2;
            if (!recPCRect.intersects(bounds)) {
                break;
            }
            this.m_recPCRect = recPCRect;
            this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.y = recPCRect.y;
            this.drawItem(graphics);
            recPCRect.y += recPCRect.height;
            recPCRect.y += this.m_nRowSpace;
            recPCRect.height = 0;
            if (recPCRect.y > insideRect.height + insideRect.y) {
                break;
            }
            ++this.m_lvi_iItem;
            ++n;
        }
        super.vs.setVisibleAmount(this.itemsThisPage(graphics));
        super.vs.setMaximum(this.m_arrItems.size());
        super.vs.setValue(this.m_nTopRow);
        super.vs.setBlockIncrement(Math.max(n, 1));
        if (this.m_bColumnLines && this.m_arrColumns.size() > 1) {
            this.drawColumnLines(graphics);
        }
        --this.m_lvi_iItem;
    }
    
    public void drawItem(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle();
        final Rectangle recPCRect = this.m_recPCRect;
        rectangle.y = recPCRect.y;
        rectangle.height = recPCRect.height;
        rectangle.x = recPCRect.x;
        rectangle.width = 0;
        for (int size = this.m_arrColumns.size(), i = 0; i < size; ++i) {
            this.m_colPCol = (Column)this.m_arrColumns.elementAt(i);
            if (size > 1) {
                rectangle.width = this.m_colPCol.m_nCX;
            }
            else {
                rectangle.width = Math.max(this.m_colPCol.m_nCX, this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.width);
            }
            if (rectangle.intersects(recPCRect)) {
                this.m_lvi_iSubItem = this.m_colPCol.m_iSubItem;
                if (this.m_lvi_iSubItem != 0) {
                    this.m_lvi_pszText = this.m_arrItems.elementAt(this.m_lvi_iItem).getSubItem(this.m_lvi_iSubItem).pszText;
                }
                else {
                    this.m_lvi_pszText = this.m_arrItems.elementAt(this.m_lvi_iItem).m_pszText;
                }
                this.m_recPCRect = new Rectangle(rectangle);
                this.drawSubItem(graphics);
            }
            rectangle.x += rectangle.width;
            this.m_recPCRect.x = rectangle.x;
        }
        if (this.m_bItemLines) {
            this.drawItemLines(graphics, recPCRect);
        }
    }
    
    public void drawItemLines(final Graphics graphics, final Rectangle rectangle) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(this.m_colGridLines);
        if (this.m_nStyleGridLines == 0) {
            this.drawDottedLine(graphics, rectangle.x, rectangle.y + rectangle.height + this.m_nRowSpace / 2 - bounds.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height + this.m_nRowSpace / 2 - bounds.y);
        }
        else {
            graphics.drawLine(rectangle.x, rectangle.y + rectangle.height + this.m_nRowSpace / 2 - bounds.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height + this.m_nRowSpace / 2 - bounds.y);
        }
    }
    
    public void drawSubItem(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this.m_lvi_pszText != null) {
            if (this.m_arrItems.elementAt(this.m_lvi_iItem).getFont() != null) {
                graphics.setFont(this.m_arrItems.elementAt(this.m_lvi_iItem).getFont());
            }
            else {
                graphics.setFont(this.defaultfont);
            }
            final int x = this.m_recPCRect.x;
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            if (!this.m_bAutoWrap) {
                String string = "";
                int n = 0;
                if (this.m_lvi_iSubItem == 0) {
                    n = this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().width + this.m_nWidthGap;
                }
                if (fontMetrics.stringWidth(this.m_lvi_pszText) + n > this.m_recPCRect.width - 2 * this.m_nWidthGap - (bounds.width - this.getInsideRect().width)) {
                    final int stringWidth = fontMetrics.stringWidth(".");
                    if (n + this.m_nWidthGap + stringWidth > this.m_recPCRect.width - (bounds.width - this.getInsideRect().width)) {
                        string = " ";
                    }
                    else if (n + this.m_nWidthGap + 2 * stringWidth > this.m_recPCRect.width - (bounds.width - this.getInsideRect().width)) {
                        final int n2 = n + this.m_nWidthGap + stringWidth;
                        string = ".";
                    }
                    else if (n + this.m_nWidthGap + 3 * stringWidth > this.m_recPCRect.width - (bounds.width - this.getInsideRect().width)) {
                        final int n3 = n + this.m_nWidthGap + 2 * stringWidth;
                        string = "..";
                    }
                    else {
                        int n4;
                        for (n4 = 1; n4 < this.m_lvi_pszText.length() && n + 2 * this.m_nWidthGap + fontMetrics.stringWidth(this.m_lvi_pszText.substring(0, n4 - 1)) + 3 * stringWidth <= this.m_recPCRect.width; ++n4) {}
                        if (n4 - 2 < 0) {
                            n4 = 2;
                        }
                        string = String.valueOf(this.m_lvi_pszText.substring(0, n4 - 2)) + "...";
                        final int n5 = n + this.m_nWidthGap + fontMetrics.stringWidth(string);
                    }
                }
                if (fontMetrics.stringWidth(this.m_lvi_pszText) + n > this.m_recPCRect.width - 2 * this.m_nWidthGap) {
                    this.m_lvi_pszText = string;
                }
                if (!this.isSelected(this.m_lvi_iItem) || (this.m_lvi_iSubItem != 0 && !this.m_bHilightSubItems)) {
                    graphics.setColor(this.m_colText);
                }
                else {
                    graphics.setColor(this.m_colHighlightTextBackground);
                    graphics.fillRect(this.m_recPCRect.x + 1, this.m_recPCRect.y - bounds.y + 1, this.m_recPCRect.width - 3, this.m_recPCRect.height - 1);
                    if (this.m_bShowDotRect && this.m_bHasFocus) {
                        this.drawDottedRect(graphics, this.m_recPCRect.x, this.m_recPCRect.y - bounds.y, this.m_recPCRect.width - 1, this.m_recPCRect.height);
                    }
                    graphics.setColor(this.m_colHighlightText);
                }
                final int x2 = this.m_recPCRect.x;
                int n6;
                if (this.m_lvi_iSubItem == 0) {
                    n6 = x2 + (this.m_nWidthGap + this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().width);
                }
                else {
                    n6 = x2 + this.m_nWidthGap;
                }
                final int n7 = this.m_recPCRect.y + this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.height - bounds.y - this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.height / 4;
                final int n8 = this.m_nWidthGap + fontMetrics.stringWidth(this.m_lvi_pszText);
                final Column column = this.m_arrColumns.elementAt(this.m_lvi_iSubItem);
                if (column.m_nFmt == 1) {
                    if (n8 < column.m_nCX) {
                        n6 = column.m_nCX / 2 - (n8 - this.m_nWidthGap) / 2 + n6 - this.m_nWidthGap;
                    }
                }
                else if (column.m_nFmt == 2 && n8 < column.m_nCX) {
                    n6 = column.m_nCX - (n8 - this.m_nWidthGap) + n6 - 2 * this.m_nWidthGap;
                }
                final int imageIndex = this.getImageIndex(this.m_arrItems.elementAt(this.m_lvi_iItem).getImage());
                if (this.m_lvi_iSubItem == 0 && imageIndex != -1 && this.m_arrImages != null && this.m_arrImages.size() > 0 && imageIndex < this.m_arrImages.size()) {
                    final int n9 = this.m_recPCRect.x + this.m_nWidthGap;
                    int n10 = this.m_recPCRect.y - bounds.y;
                    final int height2 = this.m_arrItems.elementAt(this.m_lvi_iItem).getTextBounds().height;
                    final int height3 = this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().height;
                    if (height2 > height3) {
                        n10 += (height2 - height3) / 2;
                    }
                    final Image image = this.m_arrImages.elementAt(imageIndex);
                    if (image != null) {
                        graphics.drawImage(image, n9, n10, this);
                    }
                    n6 += this.m_nWidthGap;
                }
                graphics.drawString(this.m_lvi_pszText, n6, n7);
            }
            else {
                final Vector vector = new Vector();
                Vector vector2;
                if (this.m_lvi_iSubItem == 0) {
                    vector2 = Text.wrapText(this.m_lvi_pszText, this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX - this.m_nWidthGap * 2 - this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().width, true, fontMetrics);
                }
                else {
                    vector2 = Text.wrapText(this.m_lvi_pszText, this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX - this.m_nWidthGap * 2, true, fontMetrics);
                }
                if (!this.isSelected(this.m_lvi_iItem) || (this.m_lvi_iSubItem != 0 && !this.m_bHilightSubItems)) {
                    graphics.setColor(this.m_colText);
                }
                else {
                    graphics.setColor(this.m_colHighlightTextBackground);
                    graphics.fillRect(this.m_recPCRect.x + 1, this.m_recPCRect.y - bounds.y + 1, this.m_recPCRect.width - 3, this.m_recPCRect.height - 1);
                    if (this.m_bShowDotRect && this.m_bHasFocus) {
                        this.drawDottedRect(graphics, this.m_recPCRect.x, this.m_recPCRect.y - bounds.y, this.m_recPCRect.width - 1, this.m_recPCRect.height);
                    }
                    graphics.setColor(this.m_colHighlightText);
                }
                int y = this.m_recPCRect.y;
                final int n11 = this.m_recPCRect.x + this.m_nWidthGap;
                final int n12 = height - bounds.y - height / 4;
                final Column column2 = this.m_arrColumns.elementAt(this.m_lvi_iSubItem);
                final int imageIndex2 = this.getImageIndex(this.m_arrItems.elementAt(this.m_lvi_iItem).getImage());
                if (this.m_lvi_iSubItem == 0 && imageIndex2 != -1 && this.m_arrImages != null && this.m_arrImages.size() > 0 && imageIndex2 < this.m_arrImages.size()) {
                    final int n13 = this.m_recPCRect.x + this.m_nWidthGap;
                    int n14 = this.m_recPCRect.y - bounds.y;
                    final int height4 = fontMetrics.getHeight();
                    final int height5 = this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().height;
                    if (height4 > height5) {
                        n14 += (height4 - height5) / 2;
                    }
                    final Image image2 = this.m_arrImages.elementAt(imageIndex2);
                    if (image2 != null && image2.getWidth(this) < this.m_recPCRect.width - this.m_nWidthGap) {
                        graphics.drawImage(image2, n13, n14, this);
                        final int n15 = n11 + this.m_nWidthGap;
                    }
                }
                for (int i = 0; i < vector2.size(); ++i) {
                    int n16 = this.m_recPCRect.x + this.m_nWidthGap;
                    if (this.m_lvi_iSubItem == 0) {
                        n16 += this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().width;
                    }
                    final int image3 = this.m_arrItems.elementAt(this.m_lvi_iItem).getImage();
                    if (this.m_lvi_iSubItem == 0 && image3 != -1 && this.m_arrImages != null && this.m_arrImages.size() > 0 && image3 < this.m_arrImages.size()) {
                        n16 += this.m_nWidthGap;
                    }
                    final int n17 = this.m_nWidthGap + fontMetrics.stringWidth(vector2.elementAt(i));
                    if (column2.m_nFmt == 1) {
                        if (n17 < column2.m_nCX) {
                            n16 = column2.m_nCX / 2 - (n17 - this.m_nWidthGap) / 2 + n16 - this.m_nWidthGap;
                        }
                    }
                    else if (column2.m_nFmt == 2 && n17 < column2.m_nCX) {
                        n16 = column2.m_nCX - (n17 - this.m_nWidthGap) + n16 - 2 * this.m_nWidthGap;
                    }
                    if (n17 + n16 - this.m_nWidthGap - 2 < this.m_recPCRect.x + this.m_recPCRect.width) {
                        graphics.drawString(vector2.elementAt(i), n16, y + n12);
                    }
                    y = y + height + this.m_nRowSpace;
                }
            }
        }
    }
    
    protected void drawTargetHighLight(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        final int target = this.getTarget(this.m_nXMouse, this.m_nYMouse);
        if (target == this.m_nOldTargetIndex) {
            return;
        }
        graphics.setColor(SystemColor.textHighlight);
        if (target >= 0 && !this.isSelected(target)) {
            graphics.setColor(Color.red);
            final ListItem listItem = this.m_arrItems.elementAt(target);
            graphics.drawLine(0, listItem.getTextBounds().y - bounds.y, bounds.x + this.getInsideRect().width, listItem.getTextBounds().y - bounds.y);
        }
        if (this.m_nOldTargetIndex != -1 && !this.isSelected(this.m_nOldTargetIndex)) {
            graphics.setColor(Color.red);
            final ListItem listItem2 = this.m_arrItems.elementAt(this.m_nOldTargetIndex);
            graphics.drawLine(0, listItem2.getTextBounds().y - bounds.y, this.getInsideRect().width + bounds.x, listItem2.getTextBounds().y - bounds.y);
        }
        this.m_nOldTargetIndex = target;
    }
    
    protected void editItem() {
        this.m_bEditModeEnabled = false;
        this.m_bDragModeEnabled = false;
        final Rectangle bounds = this.getBounds();
        this.m_itemCurrentEdit = this.getSelectedListItem();
        final ListItem listItem = this.m_arrItems.elementAt(this.selected[this.selected.length - 1]);
        this.m_nColumnEdit = 0;
        int n = 0;
        if (this.m_nXMouse < this.m_arrColumns.elementAt(0).getWidth() - this.m_ptViewportOrg.x) {
            this.m_textEditNode.setText(this.m_itemCurrentEdit.getText());
        }
        else {
            int n2 = this.m_arrColumns.elementAt(0).getWidth() - this.m_ptViewportOrg.x;
            int i;
            for (i = 1; i < this.m_arrColumns.size(); ++i) {
                n2 += ((Column)this.m_arrColumns.elementAt(i)).getWidth();
                if (this.m_nXMouse < n2) {
                    break;
                }
            }
            this.m_nColumnEdit = i;
            this.m_textEditNode.setText(this.m_itemCurrentEdit.getSubItem(i).getText());
            n = n2 - ((Column)this.m_arrColumns.elementAt(i)).getWidth();
        }
        this.m_textEditNode.setBorderStyle(3);
        this.m_textEditNode.setTextHIndent(1);
        this.m_textEditNode.setTextVIndent(2);
        this.m_textEditNode.selectAll();
        this.m_textEditNode.setBorderColor(Color.black);
        Font font = this.m_itemCurrentEdit.getFont();
        if (font == null) {
            font = this.defaultfont;
        }
        this.m_textEditNode.setFont(font);
        if (this.m_nColumnEdit == 0) {
            this.m_itemCurrentEdit.setText("");
        }
        else {
            this.m_itemCurrentEdit.getSubItem(this.m_nColumnEdit).setText("");
        }
        this.deselectAll();
        this.m_textEditNode.selectAll();
        this.m_textEditNode.setVisible(true);
        this.m_textEditNode.requestFocus();
        int width = listItem.getImageBounds().width;
        if (width != 0) {
            width += this.m_nWidthGap;
        }
        if (this.m_bOverlapEdit) {
            this.m_textEditNode.setFixedWidth(0);
        }
        else {
            int fixedWidth;
            if (this.m_nColumnEdit == 0) {
                fixedWidth = this.m_arrColumns.elementAt(this.m_nColumnEdit).m_nCX - this.m_nWidthGap * 2 - listItem.getImageBounds().width;
            }
            else {
                fixedWidth = this.m_arrColumns.elementAt(this.m_nColumnEdit).m_nCX - this.m_nWidthGap * 2;
            }
            this.m_textEditNode.setFixedWidth(fixedWidth);
        }
        if (this.m_nColumnEdit == 0) {
            this.m_textEditNode.setBounds(listItem.getTextBounds().x + this.m_nWidthGap - this.m_ptViewportOrg.x + width, listItem.getTextBounds().y - bounds.y, listItem.getTextBounds().width, listItem.getTextBounds().height + this.m_nRowSpace + 1);
        }
        else {
            this.m_textEditNode.setBounds(this.m_nWidthGap + n, listItem.getTextBounds().y - bounds.y, listItem.getTextBounds().width, listItem.getTextBounds().height + this.m_nRowSpace + 1);
        }
        this.m_textEditNode.update();
    }
    
    protected void fireOnItemTextChanged(final ItemTextChangedEvent itemTextChangedEvent) {
        if (this.aItemTextChangedListener == null) {
            return;
        }
        this.aItemTextChangedListener.onItemTextChanged(itemTextChangedEvent);
    }
    
    public boolean getAutoWrap() {
        return this.m_bAutoWrap;
    }
    
    public String getChangedItemName() {
        return this.m_oldName;
    }
    
    public int getColumnAlignment(final int n) {
        if (n < this.m_arrColumns.size()) {
            return this.m_arrColumns.elementAt(n).getAlignment();
        }
        return -1;
    }
    
    public Font getColumnFont(final int n) {
        if (n < this.m_arrColumns.size()) {
            return this.m_arrColumns.elementAt(n).getFont();
        }
        return null;
    }
    
    public int getColumnHeaderAlignment(final int n) {
        if (n < this.m_arrColumns.size()) {
            return this.m_arrColumns.elementAt(n).getHeaderAlignment();
        }
        return -1;
    }
    
    public int getColumnHeaderHeight() {
        return this.m_cyHeader;
    }
    
    protected int getColumnHit(int n) {
        int n2;
        int n3;
        for (n2 = 0, n3 = 0, n += this.m_ptViewportOrg.x; n3 <= n && n2 < this.m_arrColumns.size(); n3 += this.m_arrColumns.elementAt(n2++).m_nCX) {}
        return (n2 < this.m_arrColumns.size()) ? (n2 - 1) : (this.m_arrColumns.size() - 1);
    }
    
    public String getColumnText(final int n) {
        if (n < this.m_arrColumns.size()) {
            return this.m_arrColumns.elementAt(n).getText();
        }
        return null;
    }
    
    public int getColumnWidth(final int n) {
        if (n < this.m_arrColumns.size()) {
            return this.m_arrColumns.elementAt(n).getWidth();
        }
        return 0;
    }
    
    public Font getDefaultFont() {
        return this.defaultfont;
    }
    
    public int getDottedLineFill() {
        return this.DottedLineFill;
    }
    
    public int getDottedLineSpace() {
        return this.DottedLineSpace;
    }
    
    public int getDropTargetItem() {
        return this.m_dropTargetItem;
    }
    
    public Color getHighlightTextBgColor() {
        return this.m_colHighlightTextBackground;
    }
    
    public Color getHighlightTextColor() {
        return this.m_colHighlightText;
    }
    
    public boolean getHilightSubItems() {
        return this.m_bHilightSubItems;
    }
    
    protected int getImageIndex(final int n) {
        if (this.m_arrImageIDs == null) {
            return -1;
        }
        return this.m_arrImageIDs.indexOf(new Integer(n));
    }
    
    public Image getImageList(final int n) {
        final int index = this.m_arrImageIDs.indexOf(new Integer(n));
        if (this.m_arrImages == null || index < 0 || index > this.m_arrImages.size() - 1) {
            return null;
        }
        return (Image)this.m_arrImages.elementAt(index);
    }
    
    public int getIndex(final ListItem listItem) {
        return this.m_arrItems.indexOf(listItem);
    }
    
    public Rectangle getInsideRect() {
        final Rectangle bounds = this.getBounds();
        if (this.isHeaderCtrlEnabled()) {
            final Rectangle rectangle = bounds;
            rectangle.y += this.m_cyHeader;
            final Rectangle rectangle2 = bounds;
            rectangle2.height -= this.m_cyHeader;
        }
        if (super.vs != null) {
            final Rectangle rectangle3 = bounds;
            rectangle3.width -= super.m_nVScrollbarWidth - 1;
        }
        if (super.hs != null) {
            final Rectangle rectangle4 = bounds;
            rectangle4.height -= super.m_nHScrollbarHeight - 1;
        }
        return bounds;
    }
    
    public Object getItem(final int n) {
        return this.m_arrItems.elementAt(n);
    }
    
    public ListItem getItemAt(final int n) {
        return this.m_arrItems.elementAt(n);
    }
    
    public int getItemCount() {
        return this.m_arrItems.size();
    }
    
    public int getItemImage(final ListItem listItem) {
        return listItem.getImage();
    }
    
    public String[] getItems() {
        final int size = this.m_arrItems.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = ((ListItem)this.m_arrItems.elementAt(i)).getText();
        }
        return array;
    }
    
    public int getLastFullyVisibleItem() {
        final ListItem listItem = new ListItem();
        final Rectangle insideRect = this.getInsideRect();
        int n = 0;
        int i;
        for (i = this.m_nTopRow; i < this.m_arrItems.size(); ++i) {
            final ListItem listItem2 = this.m_arrItems.elementAt(i);
            if (listItem2.m_nCY == -1) {
                this.measureItem(i, this.getGraphics());
            }
            n += listItem2.m_nCY;
            if (n > insideRect.height) {
                break;
            }
        }
        return i - 1;
    }
    
    public int getLastVisibleRow() {
        if (this.itemsThisPage(this.getGraphics()) != 0) {
            return this.m_nTopRow + this.itemsThisPage(this.getGraphics()) - 1;
        }
        return this.m_nTopRow;
    }
    
    public int getLeftIndent() {
        return 0;
    }
    
    public int getLineSpacing() {
        return this.m_nRowSpace;
    }
    
    protected Point getLogicalSize() {
        final Point point = new Point(0, 0);
        point.y = this.m_arrItems.size();
        if (this.m_arrColumns.size() > 1) {
            for (int size = this.m_arrColumns.size(), i = 0; i < size; ++i) {
                final Point point2 = point;
                point2.x += this.m_arrColumns.elementAt(i).m_nCX;
            }
            point.x = Math.max(point.x, this.getInsideRect().width);
        }
        else {
            final Rectangle rectangle = new Rectangle();
            final ListItem listItem = new ListItem();
            final int size2 = this.m_arrItems.size();
            int n = 0;
            int n2 = 0;
            final Rectangle insideRect = this.getInsideRect();
            ListItem listItem2;
            for (int nTopRow = this.m_nTopRow; nTopRow < size2 && n2 < insideRect.height; n2 += listItem2.m_nCY, ++nTopRow) {
                listItem2 = this.m_arrItems.elementAt(nTopRow);
                if (listItem2.m_nCY == -1) {
                    ((ListItem)this.m_arrItems.elementAt(nTopRow)).m_nCY = this.measureItem(nTopRow, this.getGraphics());
                    listItem2.m_nCY = this.m_arrItems.elementAt(nTopRow).m_nCY;
                }
                if (!this.m_bAutoWrap) {
                    n = Math.max(n, listItem2.m_rcText.x - this.getBounds().x + listItem2.m_rcText.width + this.m_nWidthGap);
                }
                else {
                    n = Math.max(this.getInsideRect().width, this.m_arrColumns.elementAt(0).m_nCX);
                }
            }
            point.x = Math.max(n, this.m_arrColumns.elementAt(0).getWidth());
        }
        return point;
    }
    
    protected int getMaxLeftCol() {
        return this.getLogicalSize().x - this.getInsideRect().width;
    }
    
    protected int getMaxTopRow() {
        final Rectangle insideRect = this.getInsideRect();
        final int n = this.m_arrItems.size() - 1;
        new ListItem();
        int n2 = 0;
        int i;
        for (i = n; i >= 0; --i) {
            final ListItem listItem = this.m_arrItems.elementAt(i);
            this.m_lvi_iItem = i;
            n2 += this.measureItem(i, this.getGraphics());
            if (n2 > insideRect.height) {
                break;
            }
        }
        return Math.max(0, i + 1);
    }
    
    public boolean getMultipleSelections() {
        return this.m_bMultipleSelections;
    }
    
    protected int getPosFromCol(final int n) {
        int n2 = 0 - this.m_ptViewportOrg.x;
        for (int i = 0; i < n + 1; ++i) {
            n2 += ((Column)this.m_arrColumns.elementAt(i)).m_nCX;
        }
        return n2 - 1;
    }
    
    public ListItem getSelected() {
        return this.getItemAt(this.selected[this.selected.length - 1]);
    }
    
    protected int getSelected(final int n, final int n2) {
        final ListItem listItem = new ListItem();
        Column column = new Column();
        final Rectangle rectangle = new Rectangle();
        final Rectangle rectangle2 = new Rectangle();
        final Rectangle rectangle3 = new Rectangle();
        int n3 = -1;
        final Rectangle bounds = this.getBounds();
        bounds.x = 0;
        bounds.y = 0;
        final Rectangle rectangle4 = new Rectangle();
        final Rectangle insideRect = this.getInsideRect();
        insideRect.x = 0;
        if (this.isHeaderCtrlEnabled()) {
            insideRect.y = this.m_cyHeader;
        }
        else {
            insideRect.y = 0;
        }
        final int nTopRow = this.m_nTopRow;
        int n5;
        int n4 = n5 = -this.m_ptViewportOrg.x;
        if (this.m_arrColumns.size() > 1) {
            for (int i = 0; i < this.m_arrColumns.size(); ++i) {
                column = (Column)this.m_arrColumns.elementAt(i);
                n5 += column.m_nCX;
                if (n >= n4 && n < n5) {
                    break;
                }
                n4 = n4;
            }
        }
        else {
            column = this.m_arrColumns.elementAt(0);
        }
        if (n2 <= insideRect.y) {
            if (this.m_nDraggingState == 1) {
                if (this.onStartTracking(this.getColumnHit(n))) {
                    final Graphics graphics = this.getGraphics();
                    graphics.setColor(Color.red);
                    graphics.setXORMode(this.getBackground());
                    this.m_rectDragging = new Rectangle(n, this.m_cyHeader, 1, this.getInsideRect().height);
                    graphics.fillRect(this.m_rectDragging.x, this.m_rectDragging.y, this.m_rectDragging.width, this.m_rectDragging.height);
                    return -1;
                }
            }
            else {
                this.onHitColumnHeader(this.getColumnHit(n));
            }
            final Rectangle rectangle5 = new Rectangle();
            rectangle5.y = bounds.y;
            rectangle5.x = -this.m_ptViewportOrg.x;
            rectangle5.height = 0;
            rectangle5.width = 0;
            for (int size = this.m_arrColumns.size(), j = 0; j < size; ++j) {
                final Column column2 = this.m_arrColumns.elementAt(j);
                final Rectangle rectangle6 = rectangle5;
                rectangle6.width += column2.m_nCX;
                if (this.m_arrColumns.size() > 1) {
                    n3 = j - 1;
                    break;
                }
                if (rectangle5.contains(new Point(n, n2))) {
                    n3 = j - 1;
                    break;
                }
                rectangle5.x += rectangle5.width;
            }
        }
        else {
            rectangle.y = insideRect.y - 0 * this.m_nRowSpace;
            rectangle.x = -this.m_ptViewportOrg.x;
            rectangle.width = insideRect.width;
            rectangle.width = this.getLogicalSize().x;
            if (this.m_arrColumns.size() == 1) {
                rectangle.width = this.getLogicalSize().x;
            }
            rectangle.height = 0;
            for (int n6 = nTopRow; rectangle.y < insideRect.height + insideRect.y && n6 < this.m_arrItems.size(); ++n6) {
                final ListItem listItem2 = this.m_arrItems.elementAt(n6);
                if (listItem2.m_nCY == -1) {
                    listItem2.m_nCY = this.measureItem(n6, this.getGraphics());
                }
                rectangle.height = listItem2.m_nCY + this.m_nRowSpace;
                if (rectangle.contains(new Point(n, n2))) {
                    n3 = n6;
                    if (column.m_iSubItem == 0) {
                        final Rectangle rectangle7 = new Rectangle();
                        final Rectangle rectangle8 = new Rectangle();
                        rectangle7.y = rectangle.y;
                        rectangle7.height = rectangle.height + this.m_nRowSpace;
                        rectangle7.x = n4 + listItem2.m_rcText.x;
                        rectangle7.width = listItem2.m_rcText.width;
                        rectangle8.y += rectangle.y;
                        rectangle8.height = listItem2.m_rcIcon.height;
                        rectangle8.x = n4 + listItem2.m_rcIcon.x;
                        rectangle8.width = listItem2.m_rcIcon.width;
                    }
                    break;
                }
                final Rectangle rectangle9 = rectangle;
                rectangle9.y += rectangle.height;
            }
        }
        return n3;
    }
    
    public synchronized int getSelectedIndex() {
        final int[] selectedIndexes = this.getSelectedIndexes();
        return (selectedIndexes.length == 1) ? selectedIndexes[0] : -1;
    }
    
    public synchronized int[] getSelectedIndexes() {
        final int[] array = new int[this.selected.length];
        if (this.selected.length > 0) {
            array[0] = this.selected[0];
            for (int i = 1; i < this.selected.length; ++i) {
                boolean b = false;
                for (int j = 0; j < i; ++j) {
                    if (array[j] > this.selected[i]) {
                        for (int k = i; k > j; --k) {
                            array[k] = array[k - 1];
                        }
                        array[j] = this.selected[i];
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    array[i] = this.selected[i];
                }
            }
        }
        return array;
    }
    
    public String getSelectedItem() {
        if (this.selected.length > 0 && this.selected[this.selected.length - 1] < this.m_arrItems.size()) {
            return this.m_arrItems.elementAt(this.selected[this.selected.length - 1]).getText();
        }
        return null;
    }
    
    public synchronized Object[] getSelectedItems() {
        final int[] selectedIndexes = this.getSelectedIndexes();
        final Object[] array = new Object[selectedIndexes.length];
        for (int i = 0; i < selectedIndexes.length; ++i) {
            array[i] = this.getItem(selectedIndexes[i]);
        }
        return array;
    }
    
    public ListItem getSelectedListItem() {
        if (this.selected.length > 0 && this.selected[this.selected.length - 1] < this.m_arrItems.size()) {
            return this.m_arrItems.elementAt(this.selected[this.selected.length - 1]);
        }
        return null;
    }
    
    public Object[] getSelectedObjects() {
        return this.getSelectedItems();
    }
    
    protected int getTarget(final int n, final int n2) {
        return this.getSelected(n, n2);
    }
    
    public Color getTextColor() {
        return this.m_colText;
    }
    
    public int getTextIndent() {
        return this.m_nWidthGap;
    }
    
    public int getTopIndent() {
        return 0;
    }
    
    public synchronized boolean insertColumn(int iSubItem, final Column column, final boolean b) {
        final ListItem listItem = new ListItem();
        final int size = this.m_arrColumns.size();
        if (iSubItem < 0) {
            iSubItem = size;
        }
        if (iSubItem >= size) {
            this.m_arrColumns.addElement(column);
        }
        else {
            this.m_arrColumns.insertElementAt(column, iSubItem);
        }
        final int nSubItemCount = this.m_nSubItemCount;
        if (iSubItem >= nSubItemCount) {
            column.m_iSubItem = nSubItemCount;
        }
        else {
            column.m_iSubItem = iSubItem;
        }
        for (int i = column.m_iSubItem + 1; i <= size; ++i) {
            final Column column2 = this.m_arrColumns.elementAt(i);
            ++column2.m_iSubItem;
        }
        for (int j = 0; j < this.m_arrItems.size(); ++j) {
            ((ListItem)this.m_arrItems.elementAt(j)).insertSubItem(column.m_iSubItem - 1);
        }
        if (column.m_nCX == -1) {
            this.m_arrColumns.elementAt(Math.min(iSubItem, this.m_arrColumns.size() - 1)).m_nCX = 100;
        }
        this.reMeasureAllItems();
        ++this.m_nSubItemCount;
        if (b) {
            this.updateScrollbar();
            this.doLayout();
            this.repaint();
        }
        return true;
    }
    
    public boolean insertColumn(final int n, final String strText, final int nFmt, final int ncx, final int iSubItem, final boolean b) {
        final Column column = new Column();
        column.m_nCX = ncx;
        column.m_strText = strText;
        column.m_nFmt = nFmt;
        column.m_iSubItem = iSubItem;
        return this.insertColumn(n, column, b);
    }
    
    public synchronized void insertItem(final int n, final ListItem listItem) {
        this.m_arrItems.insertElementAt(listItem, n);
        if (this.m_nSubItemCount > listItem.getSubItemCount() + 1) {
            for (int i = listItem.getSubItemCount(); i < this.m_nSubItemCount - 1; ++i) {
                this.m_arrItems.elementAt(n).addSubItem();
            }
        }
        else if (this.m_nSubItemCount < listItem.getSubItemCount() + 1) {
            for (int j = this.m_nSubItemCount; j < listItem.getSubItemCount() + 1; ++j) {
                this.insertColumn(j, "", 0, -1, -1, true);
            }
        }
    }
    
    public boolean isColumnHeader() {
        return this.m_bColumnHeader;
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public boolean isHeaderCtrlEnabled() {
        return this.m_bColumnHeader;
    }
    
    public boolean isOverlapEditMode() {
        return this.m_bOverlapEdit;
    }
    
    protected boolean isRoot(final int n) {
        return false;
    }
    
    public synchronized boolean isSelected(final int n) {
        final int[] selectedIndexes = this.getSelectedIndexes();
        for (int i = 0; i < selectedIndexes.length; ++i) {
            if (selectedIndexes[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isShowDotRect() {
        return this.m_bShowDotRect;
    }
    
    protected int itemsThisPage(final Graphics graphics) {
        final ListItem listItem = new ListItem();
        final Rectangle insideRect = this.getInsideRect();
        insideRect.y -= this.getBounds().y;
        int n = 0;
        int nItemsThisPage = 0;
        if (this.m_nTopRow > this.m_arrItems.size()) {
            this.m_nTopRow = 0;
        }
        if (this.m_arrItems.size() == 0) {
            return 0;
        }
        for (int i = this.m_nTopRow; i < this.m_arrItems.size(); ++i, ++nItemsThisPage) {
            final ListItem listItem2 = this.m_arrItems.elementAt(i);
            if (listItem2.m_nCY == -1) {
                ((ListItem)this.m_arrItems.elementAt(i)).m_nCY = this.measureItem(i, graphics);
                listItem2.m_nCY = this.m_arrItems.elementAt(i).m_nCY;
            }
            final int n2 = n + listItem2.m_nCY;
            this.getFontMetrics(this.defaultfont);
            if (listItem2.getFont() != null) {
                this.getFontMetrics(listItem2.getFont());
            }
            n = n2 + this.m_nRowSpace;
            if (n > insideRect.height) {
                break;
            }
        }
        if (n < insideRect.height) {
            int j = this.m_nTopRow - 1;
            if (j >= 0) {
                while (j >= 0) {
                    final ListItem listItem3 = this.m_arrItems.elementAt(j);
                    if (listItem3.m_nCY == -1) {
                        ((ListItem)this.m_arrItems.elementAt(j)).m_nCY = this.measureItem(j, graphics);
                        listItem3.m_nCY = this.m_arrItems.elementAt(j).m_nCY;
                    }
                    n += listItem3.m_nCY;
                    if (n > insideRect.height) {
                        break;
                    }
                    --j;
                    ++nItemsThisPage;
                }
            }
        }
        return this.m_nItemsThisPage = nItemsThisPage;
    }
    
    public int measureItem(final int lvi_iItem, final Graphics graphics) {
        this.m_lvi_iItem = lvi_iItem;
        return this.measureItem(graphics);
    }
    
    protected int measureItem(final Graphics graphics) {
        final ListItem listItem = new ListItem();
        final ListItem listItem2 = this.m_arrItems.elementAt(this.m_lvi_iItem);
        listItem2.m_nCY = 0;
        listItem2.m_rcText.height = 0;
        final int size = this.m_arrColumns.size();
        int height = 0;
        int width = 0;
        for (int i = 0; i < size; ++i) {
            this.m_colPCol = (Column)this.m_arrColumns.elementAt(i);
            this.m_lvi_iSubItem = this.m_colPCol.m_iSubItem;
            if (this.m_lvi_iSubItem == 0) {
                final int imageIndex = this.getImageIndex(this.m_arrItems.elementAt(this.m_lvi_iItem).getImage());
                if (this.m_lvi_iSubItem == 0 && this.m_arrImages != null && this.m_arrImages.size() > 0 && imageIndex != -1 && imageIndex < this.m_arrImages.size()) {
                    final Image image = this.m_arrImages.elementAt(imageIndex);
                    height = image.getHeight(this);
                    width = image.getWidth(this);
                }
                this.m_arrItems.elementAt(this.m_lvi_iItem).setImageBounds(new Rectangle(0, 0, width, height));
            }
            if (this.m_colPCol.m_iSubItem > 0) {
                this.m_lvi_pszText = this.m_arrItems.elementAt(this.m_lvi_iItem).getSubItem(this.m_lvi_iSubItem).pszText;
            }
            else {
                this.m_lvi_pszText = this.m_arrItems.elementAt(this.m_lvi_iItem).m_pszText;
            }
            if (height > listItem2.m_nCY) {
                listItem2.m_nCY = height;
            }
            listItem2.m_nCY = Math.max(listItem2.m_nCY, this.measureSubItem(graphics));
            listItem2.m_rcText.height = listItem2.m_nCY;
            this.m_arrItems.elementAt(this.m_lvi_iItem).m_nCY = listItem2.m_nCY;
        }
        this.m_PCheight = listItem2.m_nCY;
        this.m_recPCRect.height = listItem2.m_nCY;
        return this.m_PCheight;
    }
    
    protected int measureSubItem(final Graphics graphics) {
        final Rectangle rectangle = new Rectangle(0, 0, this.m_colPCol.m_nCX, 0);
        if (this.m_lvi_pszText != null) {
            final Font font = this.m_arrItems.elementAt(this.m_lvi_iItem).getFont();
            FontMetrics fontMetrics;
            if (font != null) {
                fontMetrics = this.getFontMetrics(font);
            }
            else {
                fontMetrics = this.getFontMetrics(this.defaultfont);
            }
            if (this.m_bAutoWrap && this.m_lvi_pszText != null) {
                final Vector vector = new Vector();
                Vector vector2;
                if (this.m_lvi_iSubItem == 0) {
                    vector2 = Text.wrapText(this.m_lvi_pszText, this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX - this.m_nWidthGap * 2 - this.m_arrItems.elementAt(this.m_lvi_iItem).getImageBounds().width, true, fontMetrics);
                }
                else {
                    vector2 = Text.wrapText(this.m_lvi_pszText, this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX - this.m_nWidthGap * 2, true, fontMetrics);
                }
                rectangle.height = vector2.size() * fontMetrics.getHeight();
            }
            else {
                rectangle.height = fontMetrics.getHeight();
            }
            this.m_PCheight = rectangle.height;
            this.m_recPCRect.height = rectangle.height;
            this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.height = rectangle.height;
            if (this.m_arrColumns.size() > 1) {
                rectangle.width = this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX;
                this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.width = this.m_arrColumns.elementAt(this.m_lvi_iSubItem).m_nCX;
            }
            else {
                this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText.width = fontMetrics.stringWidth(this.m_lvi_pszText) + 2 * this.m_nWidthGap;
                rectangle.width = fontMetrics.stringWidth(this.m_lvi_pszText) + 2 * this.m_nWidthGap;
                if (rectangle.width > this.m_arrColumns.elementAt(0).m_nCX && !this.m_bAutoWrap) {
                    this.m_arrColumns.elementAt(0).m_nCX = Math.max(rectangle.width, this.getInsideRect().width);
                }
            }
        }
        if (this.m_lvi_iSubItem == 0) {
            this.m_arrItems.elementAt(this.m_lvi_iItem).m_rcText = new Rectangle(rectangle);
            this.m_recPCRect = new Rectangle(rectangle);
        }
        else {
            this.m_arrItems.elementAt(this.m_lvi_iItem).getSubItem(this.m_lvi_iSubItem).rcText = new Rectangle(rectangle);
        }
        return this.m_PCheight;
    }
    
    public void modifySubItem(final int n, final int n2, final String pszText) {
        if (n <= this.m_arrItems.size() - 1 && n2 > 0 && n2 <= this.m_arrItems.elementAt(n).getSubItemCount()) {
            this.m_arrItems.elementAt(n).getSubItem(n2).pszText = pszText;
        }
    }
    
    protected boolean mouseDown(final MouseEvent mouseEvent, final int nxMouse, final int nyMouse) {
        this.m_bSelectedItemChanged = false;
        if (this.m_bDragModeAllowed) {
            this.m_bDragModeEnabled = true;
        }
        this.m_bEditModeEnabled = false;
        this.m_nXMouse = nxMouse;
        this.m_nYMouse = nyMouse;
        if (this.m_nDraggingState == 0) {
            this.requestFocus();
        }
        if (mouseEvent.isShiftDown()) {
            this.m_bDragModeEnabled = false;
            this.m_bEditModeEnabled = false;
            this.selected = new int[0];
            final int selected = this.getSelected(nxMouse, nyMouse);
            if (!this.m_bMultipleSelections) {
                if (selected != -1) {
                    this.select(this.prev = selected, true);
                    this.m_bSelectedItemChanged = true;
                }
                return true;
            }
            if (selected != -1) {
                int n = (selected > this.prev) ? this.prev : selected;
                final int n2 = (selected > this.prev) ? selected : this.prev;
                if (n == -1) {
                    n = selected;
                }
                this.prev = selected;
                for (int i = n; i <= n2; ++i) {
                    this.select(i, false);
                }
                this.update();
                this.m_bSelectedItemChanged = true;
            }
            return true;
        }
        else if (mouseEvent.isControlDown()) {
            this.m_bEditModeEnabled = false;
            this.m_bDragModeEnabled = false;
            final int selected2 = this.getSelected(nxMouse, nyMouse);
            if (!this.m_bMultipleSelections) {
                if (selected2 != -1) {
                    this.select(this.prev = selected2, true);
                    this.m_bSelectedItemChanged = true;
                }
                return true;
            }
            if (this.isSelected(selected2)) {
                this.deselect(selected2);
                this.update();
                this.m_bSelectedItemChanged = true;
                this.m_bEditModeEnabled = false;
                return true;
            }
            if (selected2 != -1) {
                this.select(this.prev = selected2, true);
                this.m_bSelectedItemChanged = true;
            }
            return true;
        }
        else {
            this.m_bEditModeEnabled = false;
            final int selected3 = this.getSelected(nxMouse, nyMouse);
            if (mouseEvent.getClickCount() == 2) {
                this.doubleClickEvent(selected3);
                this.processActionEvent(new ActionEvent(this, 1001, this.commandDoubleClicked));
                return true;
            }
            if (this.isSelected(selected3)) {
                if (this.m_bDragModeAllowed) {
                    this.m_bDragModeEnabled = true;
                }
                if (this.m_bEditModeAllowed) {
                    this.m_bEditModeEnabled = true;
                }
                if (!this.m_bDragModeAllowed && !this.m_bEditModeAllowed) {
                    this.deselectAll();
                    this.select(selected3);
                }
                return true;
            }
            if (this.m_nDraggingState == 0) {
                if (selected3 == -1) {
                    this.m_bDragModeEnabled = false;
                }
                if (selected3 != -1) {
                    this.selected = new int[0];
                    this.select(this.prev = selected3, true);
                    if (mouseEvent.getClickCount() != 2) {
                        this.m_bSelectedItemChanged = true;
                    }
                }
            }
            return true;
        }
    }
    
    protected boolean mouseDrag(final MouseEvent mouseEvent, final int nxMouse, final int nyMouse) {
        this.m_bMouseDrag = true;
        this.m_bEditModeEnabled = false;
        if (this.m_nDraggingState == 1 && nxMouse - this.m_nDraggingBegin >= -3) {
            this.onMoveTracking(this.getColumnHit(nxMouse), nxMouse);
            return true;
        }
        if (this.m_nDraggingState == 1 && nxMouse - this.m_nDraggingBegin < -3) {
            return true;
        }
        if (this.m_bDragModeEnabled) {
            this.m_bEditModeEnabled = false;
            final Graphics graphics = this.getGraphics();
            if (this.m_bDragDropDrawn) {
                this.printDragItem(graphics);
            }
            else {
                this.m_bDragDropDrawn = true;
            }
            this.m_nXMouse = nxMouse;
            this.m_nYMouse = nyMouse;
            this.printDragItem(graphics);
            graphics.dispose();
        }
        return true;
    }
    
    protected boolean mouseExit(final MouseEvent mouseEvent, final int n, final int n2) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        return true;
    }
    
    protected boolean mouseMove(final MouseEvent mouseEvent, final int n, final int n2) {
        final int columnHit = this.getColumnHit(n);
        final int posFromCol = this.getPosFromCol(columnHit);
        if (n2 > 0 && n2 < this.m_cyHeader && Math.abs(n - posFromCol) <= 3 && this.m_arrColumns.size() > 1) {
            this.m_nDraggingState = 1;
            this.m_nDraggingCurrent = columnHit;
            this.m_nDraggingBegin = posFromCol - this.m_arrColumns.elementAt(this.m_nDraggingCurrent).m_nCX;
            if (!this.m_bCaptured) {
                if (this.OSName.equals("Windows NT") || this.OSName.equals("Windows 95")) {
                    this.setCursor(Cursor.getPredefinedCursor(10));
                }
                else {
                    this.setCursor(Cursor.getPredefinedCursor(13));
                }
            }
            this.m_bCaptured = true;
        }
        else if (n2 > 0 && n2 < this.m_cyHeader && Math.abs(posFromCol + this.m_arrColumns.elementAt(columnHit).m_nCX - n) <= 3 && this.m_arrColumns.size() > 1) {
            this.m_nDraggingState = 1;
            this.m_nDraggingCurrent = columnHit;
            this.m_nDraggingBegin = posFromCol;
            if (!this.m_bCaptured) {
                if (this.OSName.equals("Windows NT") || this.OSName.equals("Windows 95")) {
                    this.setCursor(Cursor.getPredefinedCursor(10));
                }
                else {
                    this.setCursor(Cursor.getPredefinedCursor(13));
                }
            }
            this.m_bCaptured = true;
        }
        else {
            this.m_nDraggingState = 0;
            if (this.m_bCaptured) {
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
            this.m_bCaptured = false;
            this.onMoveOverItem(n, n2);
        }
        return true;
    }
    
    protected boolean mouseUp(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.m_bDragModeEnabled && this.m_bMouseDrag && this.m_nDraggingState == 0) {
            this.m_bEditModeEnabled = false;
            this.m_bDragModeEnabled = false;
            this.m_bMouseDrag = false;
            final Graphics graphics = this.getGraphics();
            if (this.m_bDragDropDrawn) {
                this.printDragItem(graphics);
                this.m_bDragDropDrawn = false;
                this.m_dropTargetItem = this.getTarget(n, n2);
                this.onDropAction();
                this.processActionEvent(new ActionEvent(this, 3001, ListBox.commandDragDrop));
                this.repaint();
            }
            return true;
        }
        if (!this.m_bHasFocus) {
            this.requestFocus();
        }
        if (this.m_nDraggingState == 1) {
            this.onEndTracking(this.m_nDraggingCurrent, n);
            this.m_bDragModeEnabled = false;
            this.m_bEditModeEnabled = false;
            this.m_bDragDropDrawn = false;
            this.m_bMouseDrag = false;
            return true;
        }
        if (this.m_bEditModeEnabled || this.m_bDragModeEnabled) {
            if (this.selected != null && this.selected.length > 1) {
                this.deselectAll();
                final int selected = this.getSelected(n, n2);
                if (selected > 0) {
                    this.select(selected);
                }
            }
            else if (this.m_bEditModeEnabled) {
                this.editItem();
            }
            this.m_bDragModeEnabled = false;
            this.m_bMouseDrag = false;
        }
        this.m_bDragModeEnabled = false;
        this.m_bEditModeEnabled = false;
        this.m_nOldTargetIndex = -1;
        this.m_bMouseDrag = false;
        return true;
    }
    
    public void moveItems(final Object[] array, int index) {
        if (index == -1) {
            return;
        }
        for (int i = array.length - 1; i > -1; --i) {
            if (array[i] == this.m_arrItems.elementAt(index)) {
                return;
            }
        }
        final Vector vector = new Vector<ListItem>();
        for (int j = 0; j < this.selected.length; ++j) {
            vector.addElement(this.m_arrItems.elementAt(this.selected[j]));
        }
        this.deselectAll(false);
        for (int k = array.length - 1; k > -1; --k) {
            int index2 = this.getIndex((ListItem)array[k]);
            if (index2 > index) {
                ++index2;
            }
            this.m_arrItems.insertElementAt(array[k], index);
            this.m_arrItems.removeElementAt(index2);
            index = this.getIndex((ListItem)array[k]);
        }
        for (int l = 0; l < vector.size(); ++l) {
            this.select(this.getIndex(vector.elementAt(l)), false);
        }
        this.update();
    }
    
    protected void onDropAction() {
    }
    
    protected void onEndTracking(final int n, final int n2) {
        this.m_nDraggingState = 0;
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.m_arrColumns.elementAt(n).m_nCX = Math.max(n2 - this.m_nDraggingBegin, 20);
        if (this.getMaxTopRow() == 0) {
            this.m_nTopRow = 0;
        }
        if (this.getMaxLeftCol() == 0) {
            super.m_nLeftCol = 0;
        }
        this.reMeasureAllItems();
        this.update();
    }
    
    protected void onHitColumnHeader(final int n) {
    }
    
    protected void onLeftColChanged(final int x) {
        if (this.m_textEditNode != null && this.m_textEditNode.isShowing()) {
            this.changeItemText();
        }
        if (this.m_ptViewportOrg.x != x) {
            this.m_ptViewportOrg.x = x;
            this.updateScrollbar(false);
            this.repaint();
            this.processActionEvent(new ActionEvent(this, 1001, this.commandLeftColChanged));
        }
    }
    
    protected void onMoveOverItem(final int n, final int n2) {
    }
    
    protected void onMoveTracking(final int n, final int n2) {
        final Rectangle bounds = this.getBounds();
        final int width = bounds.width;
        final int height = bounds.height;
        final Graphics graphics = this.getGraphics();
        graphics.setColor(Color.red);
        graphics.setXORMode(this.getBackground());
        graphics.fillRect(this.m_rectDragging.x, this.m_rectDragging.y, this.m_rectDragging.width, this.m_rectDragging.height);
        this.m_rectDragging = new Rectangle(n2, this.m_cyHeader, 1, height);
        graphics.fillRect(this.m_rectDragging.x, this.m_rectDragging.y, this.m_rectDragging.width, this.m_rectDragging.height);
        this.m_arrColumns.elementAt(this.m_nDraggingCurrent).m_nCX = Math.max(n2 - this.m_nDraggingBegin, 20);
        final Graphics graphics2 = this.getGraphics();
        final Rectangle rectangle = new Rectangle(this.m_nDraggingBegin, 0, width - this.m_nDraggingBegin, this.m_cyHeader);
        graphics2.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.update(graphics2);
    }
    
    protected boolean onStartTracking(final int n) {
        return true;
    }
    
    protected void onTopRowChanged(final int nTopRow) {
        if (this.m_textEditNode != null && this.m_textEditNode.isShowing()) {
            this.changeItemText();
        }
        if (this.m_nTopRow != nTopRow) {
            this.m_nTopRow = nTopRow;
            this.updateScrollbar(false);
            this.repaint();
            this.processActionEvent(new ActionEvent(this, 1001, this.commandTopRowChanged));
        }
    }
    
    private void paintBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        graphics.setColor(SystemColor.controlLtHighlight);
        if (b) {
            graphics.drawLine(n, n2, n + n3, n2);
            graphics.drawLine(n, n2, n, n2 + n4);
        }
        else {
            graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        }
        graphics.setColor(SystemColor.controlHighlight);
        if (!b) {
            graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
            graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
        }
        graphics.setColor(SystemColor.controlShadow);
        if (b) {
            graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        }
        else {
            graphics.drawLine(n, n2, n + n3 - 1, n2);
            graphics.drawLine(n, n2, n, n2 + n4 - 1);
        }
        graphics.setColor(SystemColor.controlDkShadow);
        if (b) {
            graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
            graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        }
        else {
            graphics.drawLine(n + 1, n2 + 1, n + n3 - 2, n2 + 1);
            graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 2);
        }
    }
    
    public void printDragItem(final Graphics graphics) {
        graphics.setXORMode(this.getBackground());
        if (!this.m_bColumnHeader || this.m_nYMouse + 10 > this.m_cyHeader) {
            this.drawTargetHighLight(graphics);
            if (!this.m_bDrawDragImage) {
                return;
            }
            if (this.m_imgDragPic != null) {
                graphics.drawImage(this.m_imgDragPic, this.m_nXMouse + 10, this.m_nYMouse + 10, this);
            }
            else {
                graphics.setColor(Color.white);
                graphics.fillRect(this.m_nXMouse + 10, this.m_nYMouse + 10, 10, 10);
                graphics.setColor(Color.black);
                graphics.drawRect(this.m_nXMouse + 10, this.m_nYMouse + 10, 10, 10);
            }
        }
    }
    
    protected void processActionEvent(final ActionEvent actionEvent) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(actionEvent);
        }
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        switch (awtEvent.getID()) {
            case 501: {
                if ((((MouseEvent)awtEvent).getModifiers() & 0x4) != 0x0) {
                    return;
                }
                final Point point = ((MouseEvent)awtEvent).getPoint();
                this.mouseDown((MouseEvent)awtEvent, point.x, point.y);
                break;
            }
            case 503: {
                final Point point2 = ((MouseEvent)awtEvent).getPoint();
                this.mouseMove((MouseEvent)awtEvent, point2.x, point2.y);
                break;
            }
            case 506: {
                final Point point3 = ((MouseEvent)awtEvent).getPoint();
                this.mouseDrag((MouseEvent)awtEvent, point3.x, point3.y);
                break;
            }
            case 502: {
                final Point point4 = ((MouseEvent)awtEvent).getPoint();
                this.mouseUp((MouseEvent)awtEvent, point4.x, point4.y);
                break;
            }
        }
        super.processEvent(awtEvent);
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        switch (focusEvent.getID()) {
            case 1004: {
                this.m_bHasFocus = true;
                this.update();
                break;
            }
            case 1005: {
                this.m_bHasFocus = false;
                this.update();
                break;
            }
        }
        super.processFocusEvent(focusEvent);
    }
    
    protected void processItemEvent(final ItemEvent itemEvent) {
        if (this.itemListener != null) {
            this.itemListener.itemStateChanged(itemEvent);
        }
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getID() == 401) {
            switch (keyCode) {
                case 40: {
                    if (this.prev < this.m_arrItems.size() - 1) {
                        if (!this.m_bMultipleSelections || !keyEvent.isShiftDown()) {
                            this.selected = new int[0];
                        }
                        this.select(++this.prev, false, false);
                        this.scrollToView(this.prev, 1005);
                        this.update();
                    }
                    break;
                }
                case 38: {
                    if (this.prev > 0) {
                        if (!this.m_bMultipleSelections || !keyEvent.isShiftDown()) {
                            this.selected = new int[0];
                        }
                        this.select(--this.prev, false, false);
                        this.scrollToView(this.prev, 1004);
                        this.update();
                    }
                    break;
                }
                case 36:
                case 37: {
                    this.selected = new int[0];
                    this.select(this.prev = 0, false, false);
                    this.scrollToView(this.prev, 1006);
                    super.hs.setValue(this.prev);
                    this.onLeftColChanged(this.prev);
                    this.update();
                    break;
                }
                case 35:
                case 39: {
                    this.selected = new int[0];
                    this.select(this.prev = this.m_arrItems.size() - 1, false, false);
                    this.scrollToView(this.prev, 1007);
                    super.hs.setValue(this.prev);
                    this.onLeftColChanged(this.prev);
                    this.update();
                    break;
                }
                case 34: {
                    this.prev = this.m_nTopRow + this.itemsThisPage(this.getGraphics()) - 1;
                    if (this.isSelected(this.prev)) {
                        this.m_nTopRow = this.prev;
                        super.vs.setValue(this.prev);
                        this.onTopRowChanged(this.prev);
                        this.prev = this.m_nTopRow + this.itemsThisPage(this.getGraphics()) - 1;
                    }
                    this.selected = new int[0];
                    this.select(this.prev = Math.min(this.prev, this.m_arrItems.size() - 1), false, false);
                    this.update();
                    break;
                }
                case 33: {
                    if (this.isSelected(this.m_nTopRow)) {
                        this.m_nTopRow = Math.max(this.m_nTopRow - this.itemsThisPage(this.getGraphics()), 0);
                        super.vs.setValue(this.m_nTopRow);
                        this.onTopRowChanged(this.m_nTopRow);
                    }
                    this.selected = new int[0];
                    this.select(this.prev = this.m_nTopRow, false, false);
                    this.update();
                    break;
                }
                case 127: {
                    if (this.m_bAllowDelete && this.selected.length > 0) {
                        final int[] selectedIndexes = this.getSelectedIndexes();
                        for (int i = selectedIndexes.length - 1; i >= 0; --i) {
                            if (i != 0) {
                                this.deleteItem(selectedIndexes[i], false);
                            }
                            else {
                                this.deleteItem(selectedIndexes[i], true);
                            }
                        }
                    }
                    this.deselectAll();
                    break;
                }
                case 10: {
                    this.processItemEvent(new ItemEvent(this, 701, this.getSelectedListItem(), 1));
                    break;
                }
            }
        }
    }
    
    public void reMeasureAllItems() {
        new ListItem();
        for (int size = this.m_arrItems.size(), i = 0; i < size; ++i) {
            ((ListItem)this.m_arrItems.elementAt(i)).m_nCY = -1;
            ((ListItem)this.m_arrItems.elementAt(i)).m_rcText.height = -1;
        }
        this.updateScrollbar();
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.itemListener = AWTEventMulticaster.remove(this.itemListener, itemListener);
    }
    
    public void removeItemTextChangedListener(final ItemTextChangedListener itemTextChangedListener) {
        this.aItemTextChangedListener = ItemTextChangedEventMulticaster.remove(this.aItemTextChangedListener, itemTextChangedListener);
    }
    
    public synchronized void replaceItem(final Object o, final int n) {
        this.deleteItem(n);
        this.addItem(o, n, false);
        this.update();
    }
    
    public void scrollHorz(int n) {
        final Rectangle insideRect = this.getInsideRect();
        final Point logicalSize = this.getLogicalSize();
        final int x = this.m_ptViewportOrg.x;
        final Point ptViewportOrg = this.m_ptViewportOrg;
        ptViewportOrg.x -= n;
        this.m_ptViewportOrg.x = Math.max(0, this.m_ptViewportOrg.x);
        this.onLeftColChanged(this.m_ptViewportOrg.x = Math.min(logicalSize.x - insideRect.width, this.m_ptViewportOrg.x));
        n = x - this.m_ptViewportOrg.x;
        this.update();
        if (this.isHeaderCtrlEnabled()) {
            final Rectangle bounds = this.getBounds();
            final Rectangle rectangle = new Rectangle();
            rectangle.y = bounds.y;
            rectangle.x = bounds.x;
            rectangle.width = bounds.width;
            rectangle.height = 0;
            this.update();
        }
        super.hs.setUnitIncrement(10);
        super.hs.setBlockIncrement(this.getInsideRect().width - 10);
        super.hs.setValue(this.m_ptViewportOrg.x);
    }
    
    public void scrollToView(final int n) {
        if (!this.getVScrollbar().isShowing()) {
            return;
        }
        this.m_arrItems.size();
        final int lastVisibleRow = this.getLastVisibleRow();
        final Scrollbar vScrollbar = this.getVScrollbar();
        if (n < this.m_nTopRow) {
            int minimum = vScrollbar.getValue() - this.m_nTopRow + n + 2;
            if (minimum < vScrollbar.getMinimum()) {
                minimum = vScrollbar.getMinimum();
            }
            vScrollbar.setValue(minimum);
            this.onTopRowChanged(minimum);
        }
        else if (n > lastVisibleRow - 2) {
            int value = vScrollbar.getValue() + n - lastVisibleRow + 1;
            if (value > vScrollbar.getMaximum() - 1) {
                value = vScrollbar.getMaximum() - 1;
            }
            vScrollbar.setValue(value);
            this.onTopRowChanged(value);
        }
        if (n == 0) {
            vScrollbar.setValue(0);
            this.onTopRowChanged(0);
        }
    }
    
    public void scrollToView(final int i, final int n) {
        this.m_arrItems.size();
        final int lastVisibleRow = this.getLastVisibleRow();
        final Scrollbar vScrollbar = this.getVScrollbar();
        if (i < this.m_nTopRow && n == 1004) {
            int minimum = i;
            if (minimum < vScrollbar.getMinimum()) {
                minimum = vScrollbar.getMinimum();
            }
            vScrollbar.setValue(minimum);
            this.onTopRowChanged(minimum);
        }
        else if (n == 1004) {
            this.update();
        }
        if (i > lastVisibleRow && n == 1005) {
            int value = i;
            int n2 = this.itemsThisPage(this.getGraphics());
            while (i > this.getLastVisibleRow()) {
                final int n3 = value - this.itemsThisPage(this.getGraphics()) + 1;
                if (n3 >= vScrollbar.getMaximum() - 1) {
                    value = vScrollbar.getMaximum() - 1;
                    vScrollbar.setValue(value);
                    this.onTopRowChanged(value);
                    break;
                }
                n2 = this.itemsThisPage(this.getGraphics());
                vScrollbar.setValue(n3);
                this.m_nTopRow = n3;
                value = n3 + n2;
                this.getLastVisibleRow();
            }
            this.onTopRowChanged(value - n2);
        }
        else if (n == 1005) {
            this.update();
        }
        if (n == 1006) {
            final int minimum2 = vScrollbar.getMinimum();
            vScrollbar.setValue(minimum2);
            this.onTopRowChanged(minimum2);
        }
        if (n == 1007) {
            int n4 = this.getMaxTopRow() + 3;
            if (n4 >= vScrollbar.getMaximum() - 1) {
                n4 = vScrollbar.getMaximum() - 1;
            }
            vScrollbar.setValue(++n4);
            this.onTopRowChanged(n4);
        }
    }
    
    public void scrollVert(final int n) {
        this.scrollVert(n, false);
    }
    
    public void scrollVert(int calcItemsInRange, final boolean b) {
        if (!b) {
            final int nTopRow = this.m_nTopRow;
            calcItemsInRange = Math.min(Math.max(nTopRow + calcItemsInRange, 0), this.m_arrItems.size() - 1) - nTopRow;
            if (calcItemsInRange != 0) {
                this.m_nTopRow += calcItemsInRange;
                this.update();
            }
        }
        else {
            final int lastFullyVisibleItem = this.getLastFullyVisibleItem();
            calcItemsInRange = this.calcItemsInRange(this.calcRangeHeight(lastFullyVisibleItem, lastFullyVisibleItem + calcItemsInRange, true), this.m_nTopRow, true);
            this.scrollVert(calcItemsInRange, false);
        }
    }
    
    public synchronized void select(final int n) {
        this.select(n, true, true);
    }
    
    public synchronized void select(final int n, final boolean b) {
        this.select(n, b, false);
    }
    
    protected synchronized void select(final int n, final boolean b, final boolean b2) {
        if (n < 0 || n >= this.getItemCount()) {
            return;
        }
        for (int i = 0; i < this.selected.length; ++i) {
            if (this.selected[i] == n) {
                return;
            }
        }
        if (!this.m_bMultipleSelections) {
            (this.selected = new int[1])[0] = n;
            this.prev = n;
        }
        else {
            final int[] selected = new int[this.selected.length + 1];
            System.arraycopy(this.selected, 0, selected, 0, this.selected.length);
            selected[this.selected.length] = n;
            this.selected = selected;
            this.prev = n;
        }
        this.processItemEvent(new ItemEvent(this, 701, this.getSelectedListItem(), 1));
        if (b2) {
            this.scrollToView(n);
        }
        if (b) {
            this.update();
        }
    }
    
    public void setAllowDelete(final boolean bAllowDelete) {
        this.m_bAllowDelete = bAllowDelete;
    }
    
    public void setAutoWrap(final boolean bAutoWrap) {
        this.m_bAutoWrap = bAutoWrap;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        if (this.m_arrColumns.size() == 1) {
            if (this.m_bAutoWrap) {
                this.m_arrColumns.elementAt(0).m_nCX = this.getInsideRect().width;
                this.reMeasureAllItems();
            }
            else {
                this.m_arrColumns.elementAt(0).m_nCX = this.getLogicalSize().x;
                if (this.getLogicalSize().x < this.getInsideRect().width) {
                    this.m_arrColumns.elementAt(0).m_nCX = this.getInsideRect().width;
                }
                this.reMeasureAllItems();
            }
        }
    }
    
    public void setColumnAlignment(final int n, final int alignment) {
        if (n < this.m_arrColumns.size()) {
            this.m_arrColumns.elementAt(n).setAlignment(alignment);
            this.update();
        }
    }
    
    public void setColumnFont(final int n, final Font font) {
        if (n < this.m_arrColumns.size()) {
            this.m_arrColumns.elementAt(n).setFont(font);
            this.reMeasureAllItems();
            this.update();
        }
    }
    
    public void setColumnHeader(final boolean bColumnHeader) {
        if (!(this.m_bColumnHeader = bColumnHeader)) {
            this.m_cyHeader = 0;
        }
        else {
            this.update();
        }
    }
    
    public void setColumnHeaderAlignment(final int n, final int headerAlignment) {
        if (n < this.m_arrColumns.size()) {
            this.m_arrColumns.elementAt(n).setHeaderAlignment(headerAlignment);
            this.update();
        }
    }
    
    public void setColumnHeaderHeight(final int cyHeader) {
        this.m_cyHeader = cyHeader;
        this.update();
    }
    
    public void setColumnLines(final boolean bColumnLines) {
        this.m_bColumnLines = bColumnLines;
    }
    
    public void setColumnText(final int n, final String text) {
        if (n < this.m_arrColumns.size()) {
            this.m_arrColumns.elementAt(n).setText(text);
            this.reMeasureAllItems();
            this.update();
        }
    }
    
    public void setColumnWidth(final int n, final int width) {
        if (n < this.m_arrColumns.size()) {
            this.m_arrColumns.elementAt(n).setWidth(width);
            this.reMeasureAllItems();
            this.update();
        }
    }
    
    public void setDefaultFont(final Font defaultfont) {
        this.defaultfont = defaultfont;
    }
    
    public void setDottedLineFill(final int dottedLineFill) {
        this.DottedLineFill = dottedLineFill;
    }
    
    public void setDottedLineSpace(final int dottedLineSpace) {
        this.DottedLineSpace = dottedLineSpace;
    }
    
    public void setDragDrop(final boolean bDragModeAllowed) {
        this.m_bDragModeAllowed = bDragModeAllowed;
    }
    
    public void setDragDropImage(final Image imgDragPic) {
        this.m_imgDragPic = imgDragPic;
    }
    
    public void setDrawDragImage(final boolean bDrawDragImage) {
        this.m_bDrawDragImage = bDrawDragImage;
    }
    
    public void setEditMode(final boolean bEditModeAllowed) {
        this.m_bEditModeAllowed = bEditModeAllowed;
    }
    
    public void setFont(final Font font) {
        if (font == null) {
            return;
        }
        super.setFont(font);
        this.defaultfont = font;
        this.m_PCheight = this.getFontMetrics(font).getHeight();
    }
    
    public void setGridLineColor(final Color colGridLines) {
        this.m_colGridLines = colGridLines;
    }
    
    public void setGridLines(final boolean b) {
        this.m_bColumnLines = b;
        this.m_bItemLines = b;
    }
    
    public void setGridLineStyle(final int n) {
        if (n == 0) {
            this.m_nStyleGridLines = 0;
        }
        else {
            this.m_nStyleGridLines = 1;
        }
    }
    
    public void setHighlightTextBgColor(final Color colHighlightTextBackground) {
        this.m_colHighlightTextBackground = colHighlightTextBackground;
    }
    
    public void setHighlightTextColor(final Color colHighlightText) {
        this.m_colHighlightText = colHighlightText;
    }
    
    public void setHilightSubItems(final boolean bHilightSubItems) {
        this.m_bHilightSubItems = bHilightSubItems;
    }
    
    public int setImageList(final Image image) {
        if (this.m_arrImages == null) {
            this.m_arrImages = new Vector();
            this.m_arrImageIDs = new Vector();
        }
        final int size = this.m_arrImages.size();
        this.setImageList(image, size);
        return size;
    }
    
    public void setImageList(final Image image, final int n) {
        if (this.m_arrImages == null) {
            this.m_arrImages = new Vector();
            this.m_arrImageIDs = new Vector();
        }
        this.m_arrImages.addElement(image);
        this.m_arrImageIDs.addElement(new Integer(n));
    }
    
    public void setItemLines(final boolean bItemLines) {
        this.m_bItemLines = bItemLines;
    }
    
    public synchronized void setItems(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.addItem(array[i]);
        }
    }
    
    public void setLeftIndent(final int n) {
    }
    
    public void setLineSpacing(final int nRowSpace) {
        this.m_nRowSpace = nRowSpace;
        this.reMeasureAllItems();
    }
    
    public void setMultipleSelections(final boolean bMultipleSelections) {
        if (bMultipleSelections != this.m_bMultipleSelections) {
            this.m_bMultipleSelections = bMultipleSelections;
        }
    }
    
    public void setOverlapEditMode(final boolean bOverlapEdit) {
        this.m_bOverlapEdit = bOverlapEdit;
    }
    
    public void setShowDotRect(final boolean bShowDotRect) {
        this.m_bShowDotRect = bShowDotRect;
    }
    
    public void setTextColor(final Color colText) {
        this.m_colText = colText;
        this.repaint();
    }
    
    public void setTextIndent(final int nWidthGap) {
        this.m_nWidthGap = nWidthGap;
    }
    
    public void setTopIndent(final int n) {
    }
    
    public void updateScrollbar() {
        this.updateScrollbar(true);
    }
    
    public void updateScrollbar(final boolean b) {
        final Rectangle rectangle = new Rectangle();
        final Point logicalSize = this.getLogicalSize();
        final int itemsThisPage = this.itemsThisPage(this.getGraphics());
        final Rectangle insideRect = this.getInsideRect();
        if (logicalSize.y > itemsThisPage) {
            if (this.m_nTopRow > logicalSize.y - itemsThisPage) {
                this.scrollVert(logicalSize.y - itemsThisPage - this.m_nTopRow);
            }
            super.vs.setValues(this.m_nTopRow, itemsThisPage, 0, this.m_arrItems.size());
            super.vs.setBlockIncrement(Math.max(itemsThisPage, 1));
            this.showVScrollbar();
        }
        else {
            if (this.m_nTopRow > 0) {
                this.scrollVert(-this.m_nTopRow);
            }
            this.hideVScrollbar();
        }
        if (logicalSize.x > insideRect.width) {
            if (this.m_ptViewportOrg.x > logicalSize.x - insideRect.width) {
                this.scrollHorz(logicalSize.x - insideRect.width - this.m_ptViewportOrg.x);
            }
            super.hs.setValues(this.m_ptViewportOrg.x, insideRect.width, 0, logicalSize.x);
            super.hs.setBlockIncrement(Math.max(this.getInsideRect().width - 10, 10));
            super.hs.setUnitIncrement(10);
            this.showHScrollbar();
        }
        else {
            this.onLeftColChanged(this.m_ptViewportOrg.x = 0);
            this.hideHScrollbar();
        }
        if (b) {
            this.doLayout();
        }
    }
}
