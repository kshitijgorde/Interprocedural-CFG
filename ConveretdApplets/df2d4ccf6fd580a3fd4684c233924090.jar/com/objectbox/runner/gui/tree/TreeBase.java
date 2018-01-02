// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.tree;

import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import com.objectbox.runner.gui.text.Text;
import java.awt.image.ImageObserver;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.runner.gui.listbox.Column;
import java.awt.Point;
import java.awt.Graphics;
import com.objectbox.runner.gui.listbox.ItemTextChangedEvent;
import java.awt.Rectangle;
import com.objectbox.runner.gui.listbox.ListItem;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.Vector;
import java.io.Serializable;
import com.objectbox.runner.gui.listbox.ListBox;

public class TreeBase extends ListBox implements Serializable
{
    public static final int ILLEGAL = 3000;
    public static final int OPENFOLDER = 3002;
    public static final int SELECTION_CHANGE = 3003;
    public static final int ITEM_COLLAPSED = 3004;
    public static final int ITEM_EXPANDED = 3005;
    public static final int DOUBLE_CLICK = 3006;
    public static final String commandDragDrop = "Drag_Drop";
    public static final String commandOpenFolder = "Open_Folder";
    public static final String commandSelectionChanged = "Selection_Changed";
    public static final String commandIllegalAction = "Illegal_Action";
    public static final String commandItemCollapsed = "Item_Collapsed";
    public static final String commandItemExpanded = "Item_Expanded";
    public static final int TVGN_NEXT = 0;
    public static final int TVGN_PREVIOUS = 1;
    public static final int TVGN_CHILD = 2;
    public static final int TVGN_PARENT = 3;
    public static final int TVGN_ROOT = 4;
    public static final int TVGN_CARET = 5;
    public static final int TVGN_FIRSTVISIBLE = 6;
    public static final int TVGN_NEXTVISIBLE = 7;
    public static final int TVGN_PREVIOUSVISIBLE = 8;
    public static final int TVGN_DROPHILITE = 9;
    public static final int TVGN_FIRSTSELECTED = 10;
    public static final int TVGN_NEXTSELECTED = 11;
    public static final int TVGN_PREVIOUSSELECTED = 12;
    public static final int TVE_COLLAPSE = 0;
    public static final int TVE_EXPAND = 1;
    public static final int TVE_TOGGLE = 2;
    Node m_itemEventTarget;
    protected Vector m_arrRoots;
    protected int m_nIndent;
    private int m_nButtonSize;
    protected boolean m_bHideDisabledItems;
    protected boolean m_bHierarchyLines;
    protected Color m_colHierarchyLines;
    protected int m_nStyleHierarchyLines;
    protected boolean m_bLinesAtRoot;
    protected boolean m_bButton;
    protected int m_nWordGap;
    protected Node m_NNewlyExpandedNode;
    protected Node m_NNewlyCollapsedNode;
    protected boolean hasfocus;
    Node m_itemDropTarget;
    Node m_nodeCurrentEdit;
    
    public TreeBase() {
        this.m_arrRoots = null;
        this.m_nIndent = 18;
        this.m_nButtonSize = 8;
        this.m_bHideDisabledItems = false;
        this.m_bHierarchyLines = true;
        this.m_colHierarchyLines = SystemColor.windowBorder;
        this.m_nStyleHierarchyLines = 0;
        this.m_bLinesAtRoot = true;
        this.m_bButton = true;
        this.m_nWordGap = 4;
        this.m_NNewlyExpandedNode = null;
        this.m_NNewlyCollapsedNode = null;
        this.hasfocus = true;
        this.m_nodeCurrentEdit = new Node();
        super.m_nRowSpace = 3;
    }
    
    public TreeBase(final String s, final int n, final int n2, final ActionListener actionListener) {
        this(false);
        this.initRootNode(s, n, n2);
        super.actionListener = actionListener;
    }
    
    public TreeBase(final boolean b) {
        super(b);
        this.m_arrRoots = null;
        this.m_nIndent = 18;
        this.m_nButtonSize = 8;
        this.m_bHideDisabledItems = false;
        this.m_bHierarchyLines = true;
        this.m_colHierarchyLines = SystemColor.windowBorder;
        this.m_nStyleHierarchyLines = 0;
        this.m_bLinesAtRoot = true;
        this.m_bButton = true;
        this.m_nWordGap = 4;
        this.m_NNewlyExpandedNode = null;
        this.m_NNewlyCollapsedNode = null;
        this.hasfocus = true;
        this.m_nodeCurrentEdit = new Node();
        super.m_nRowSpace = 3;
    }
    
    protected int addChildrenToListCtrl(final Node node, int addChildrenToListCtrl) {
        if (addChildrenToListCtrl == -1) {
            return addChildrenToListCtrl;
        }
        for (Node node2 = (Node)node.getFirstChild(); node2 != null; node2 = (Node)node2.getNextSibling()) {
            ListItem listItem = node2.getListItem();
            if (listItem == null) {
                listItem = this.createNewItem();
                node2.bindToListItem(listItem);
            }
            super.insertItem(++addChildrenToListCtrl, listItem);
            if (node2.isExpanded() && node2.hasChildren()) {
                addChildrenToListCtrl = this.addChildrenToListCtrl(node2, addChildrenToListCtrl++);
            }
        }
        return addChildrenToListCtrl;
    }
    
    protected boolean addNodeToListBox(final Node node) {
        int nodeIndex;
        if (this.isRoot((Node)node.getParent()) && node.getPrevSibling() == null) {
            nodeIndex = 1 + this.nodeIndex((Node)node.getParent());
        }
        else {
            final Node prevVisibleItem = this.getPrevVisibleItem(node);
            if (prevVisibleItem == null) {
                return false;
            }
            nodeIndex = this.nodeIndex(prevVisibleItem);
            ++nodeIndex;
        }
        final ListItem newItem = super.createNewItem();
        node.bindToListItem(newItem);
        newItem.setHeight(-1);
        super.insertItem(nodeIndex, newItem);
        if (node.hasChildren() && node.isExpanded()) {
            for (Node node2 = (Node)node.getFirstChild(); node2 != null; node2 = (Node)node2.getNextSibling()) {
                this.addNodeToListBox(node2);
            }
        }
        return true;
    }
    
    public int addRootItem(final Node node) {
        if (this.m_arrRoots == null) {
            this.m_arrRoots = new Vector();
        }
        this.m_arrRoots.addElement(node);
        this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1).expand();
        final ListItem listItem = new ListItem();
        this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1).bindToListItem(listItem);
        this.addItem(listItem, false);
        this.addChildrenToListCtrl(this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1), this.nodeIndex(this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1)));
        return this.m_arrRoots.size() - 1;
    }
    
    protected Rectangle calcButtonRect() {
        final Rectangle bounds = this.getBounds();
        final Node node = this.getNodeAt(super.m_lvi_iItem);
        if (this.isRoot(node) || !node.hasChildren()) {
            return new Rectangle(0, 0, 0, 0);
        }
        final ListItem item = super.getItemAt(super.m_lvi_iItem);
        final int distanceFromRoot = node.getDistanceFromRoot();
        this.measureText();
        final int y = super.m_recPCRect.y;
        int n = super.m_recPCRect.x - this.m_nButtonSize + this.getIndent() * (distanceFromRoot - 1) - (this.m_bLinesAtRoot ? 0 : this.getIndent()) + bounds.x + super.m_nWidthGap;
        final int n2 = y + (item.getTextBounds().y + item.getImageBounds().height / 2 - 2);
        final int nButtonSize = this.m_nButtonSize;
        if (this.m_bLinesAtRoot) {
            n += this.getIndent();
        }
        final Rectangle rectangle = new Rectangle();
        rectangle.x = n - nButtonSize / 2 - bounds.x - super.m_recPCRect.x - super.m_ptViewportOrg.x;
        rectangle.y = n2 - nButtonSize / 2 - bounds.y - super.m_recPCRect.y;
        rectangle.width = nButtonSize;
        rectangle.height = nButtonSize;
        return rectangle;
    }
    
    protected void changeItemText() {
        if (super.m_nColumnEdit == 0) {
            this.m_nodeCurrentEdit.setText(super.m_textEditNode.getText());
            super.m_textEditNode.setVisible(false);
            this.measureItem(this.nodeIndex(this.m_nodeCurrentEdit), this.getGraphics());
            if (super.m_textEditNode.getText().compareTo(super.m_oldName) != 0) {
                this.fireOnItemTextChanged(new ItemTextChangedEvent(this));
                super.m_oldName = super.m_textEditNode.getText();
            }
            this.updateScrollbar();
            this.validate();
        }
        else {
            super.changeItemText();
        }
    }
    
    public int createRootNode() {
        return this.createRootNode("", -1, -1);
    }
    
    public int createRootNode(final String s, final int n, final int n2) {
        if (this.m_arrRoots == null) {
            this.initRootNode(s, n, n2);
            return 0;
        }
        this.m_arrRoots.addElement(new Node(s, n, n2));
        this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1).expand();
        final ListItem listItem = new ListItem();
        this.m_arrRoots.elementAt(this.m_arrRoots.size() - 1).bindToListItem(listItem);
        this.addItem(listItem);
        return this.m_arrRoots.size() - 1;
    }
    
    protected boolean deletedUnChildedItem(final Node node) {
        return this.deleteUnChildedItem(node, true);
    }
    
    protected boolean deleteUnChildedItem(final Node node, final boolean b) {
        if (node.getFirstChild() != null) {
            return false;
        }
        node.detachFromTree();
        if (b && this.nodeInListBox(node)) {
            this.delNodeFromListBox(node);
        }
        return true;
    }
    
    protected boolean delNodeFromListBox(final Node node) {
        return this.delNodeFromListBox(node, true);
    }
    
    protected boolean delNodeFromListBox(final Node node, final boolean b) {
        super.deleteItem(this.nodeIndex(node), b);
        return true;
    }
    
    public boolean delNodeFromTree(final Node node, final boolean b) {
        if (node.hasChildren()) {
            this.removeChildrenFromListCtrl(node, this.nodeIndex(node), false);
        }
        this.delNodeFromListBox(node, false);
        node.detachFromTree();
        if (b) {
            this.updateScrollbar();
            this.doLayout();
            this.repaint();
        }
        return true;
    }
    
    protected void doubleClickEvent(final int n) {
        if (n == -1) {
            return;
        }
    }
    
    protected void drawButton(final Graphics graphics) {
        final Rectangle calcButtonRect = this.calcButtonRect();
        if (!((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).hasChildren()) {
            return;
        }
        final int n = calcButtonRect.width / 3;
        final Point point = new Point(calcButtonRect.width / 2 + calcButtonRect.x, calcButtonRect.height / 2 + calcButtonRect.y);
        graphics.setColor(this.getBackground());
        graphics.fillRect(calcButtonRect.x, calcButtonRect.y, calcButtonRect.width, calcButtonRect.height);
        graphics.setColor(this.m_colHierarchyLines);
        if (calcButtonRect.x + calcButtonRect.width / 2 + 1 < super.m_arrColumns.elementAt(0).getWidth()) {
            graphics.drawRect(calcButtonRect.x, calcButtonRect.y, calcButtonRect.width, calcButtonRect.height);
            graphics.drawLine(point.x - n + 1, point.y, point.x + n, point.y);
            if (!((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).isExpanded()) {
                graphics.drawLine(point.x, point.y - n + 1, point.x, point.y + n);
            }
        }
    }
    
    public void drawHierarchyLines(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(this.m_colHierarchyLines);
        final Point point = new Point();
        final boolean selected = this.isSelected(super.m_lvi_iItem);
        final int n = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getDistanceFromRoot() - 1;
        final boolean bLinesAtRoot = this.m_bLinesAtRoot;
        point.y = super.m_recPCRect.y;
        final ListItem listItem = super.m_arrItems.elementAt(super.m_lvi_iItem);
        int n2;
        if (super.m_arrImages != null && super.m_arrImages.size() > 0) {
            point.x = -super.m_ptViewportOrg.x + this.m_nButtonSize + this.getIndent() * n - (bLinesAtRoot ? 0 : this.getIndent());
            final Point point2 = point;
            point2.y += listItem.getImageBounds().y + listItem.getImageBounds().height / 2;
            n2 = listItem.getImageBounds().x - point.x - 1 - super.m_nWidthGap - super.m_ptViewportOrg.x;
            final Point point3 = point;
            point3.y -= bounds.y;
            final Point point4 = point;
            point4.x += super.m_nWidthGap;
        }
        else {
            point.x = -super.m_ptViewportOrg.x + this.m_nButtonSize + this.getIndent() * n - (bLinesAtRoot ? 0 : this.getIndent());
            final Point point5 = point;
            point5.y += listItem.getTextBounds().y + listItem.getTextBounds().height / 2;
            n2 = listItem.getTextBounds().x - point.x - 1;
            final Point point6 = point;
            point6.y -= listItem.getTextBounds().y;
            final Point point7 = point;
            point7.y -= bounds.y;
        }
        final Point point8 = point;
        point8.x += bounds.x;
        if ((point.y - super.m_ptViewportOrg.y) % 2 != 0) {
            final Point point9 = point;
            ++point9.y;
        }
        final Point point10 = point;
        ++point10.x;
        if (!this.isRoot(super.m_lvi_iItem) && point.x < super.m_arrColumns.elementAt(0).getWidth()) {
            this.drawHorzHierarchyLine(graphics, selected, point.x, point.x + n2, point.y);
        }
        if (super.m_lvi_iItem > 0) {
            final int y = listItem.getTextBounds().y;
            if (!this.isRoot(super.m_lvi_iItem) && point.x - this.getBounds().x < super.m_arrColumns.elementAt(0).getWidth()) {
                this.drawVertHierarchyLine(graphics, selected, point.x, point.y, y - bounds.y - 3);
            }
        }
        if (((Node)listItem.getItemData()).hasChildren() && ((Node)listItem.getItemData()).isExpanded()) {
            int n3;
            if (super.m_arrImages != null && super.m_arrImages.size() > 0) {
                n3 = listItem.getImageBounds().height / 2;
            }
            else {
                n3 = listItem.getTextBounds().height / 2;
            }
            if (point.x + this.getIndent() - this.getBounds().x < super.m_arrColumns.elementAt(0).getWidth()) {
                this.drawVertHierarchyLine(graphics, selected, point.x + this.getIndent(), point.y + n3 + 2, super.m_recPCRect.height + point.y);
            }
        }
        if (this.isRoot(super.m_lvi_iItem)) {
            return;
        }
        Node node = (Node)listItem.getItemData();
        for (int i = n + 1; i >= (bLinesAtRoot ? 0 : 1); --i) {
            if (node.getNextSibling() != null && point.x - this.getBounds().x < super.m_arrColumns.elementAt(0).getWidth()) {
                if (super.m_arrImages == null || super.m_arrImages.size() < 0) {
                    this.drawVertHierarchyLine(graphics, selected, point.x, point.y, listItem.getTextBounds().height + listItem.getTextBounds().y - bounds.y + listItem.getTextBounds().height / 4);
                }
                else {
                    this.drawVertHierarchyLine(graphics, selected, point.x, point.y, super.m_recPCRect.height + super.m_recPCRect.y - bounds.y + super.m_recPCRect.height / 4);
                }
            }
            node = (Node)node.getParent();
            final Point point11 = point;
            point11.x -= this.getIndent();
            point.y = super.m_recPCRect.y - 2 - bounds.y;
        }
    }
    
    public void drawHorzHierarchyLine(final Graphics graphics, final boolean b, int n, int n2, final int n3) {
        final Rectangle bounds = this.getBounds();
        n -= bounds.x;
        n2 -= bounds.x;
        if (n2 < n) {
            final int n4 = n;
            n = n2;
            n2 = n4;
        }
        if ((n + super.m_ptViewportOrg.x) % 2 != 0) {
            ++n;
        }
        if (this.m_nStyleHierarchyLines == 0) {
            this.drawDottedLine(graphics, n, n3, n2, n3);
        }
        else {
            graphics.drawLine(n, n3, n2, n3);
        }
    }
    
    public void drawSubItem(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (super.m_lvi_iSubItem == 0) {
            final int measureText = this.measureText();
            this.isSelected(super.m_lvi_iItem);
            final Node node = (Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData();
            String text = node.getText();
            int n;
            if (!node.isExpanded()) {
                n = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getImage();
            }
            else {
                n = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getExpandedImage();
            }
            graphics.setColor(this.getBackground());
            final Rectangle recPCRect = super.m_recPCRect;
            final Rectangle textBounds = super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds();
            textBounds.y = super.m_recPCRect.y + 2;
            if (super.m_arrColumns.size() > 1) {
                textBounds.width = Math.min(textBounds.width, super.m_recPCRect.x + super.m_arrColumns.elementAt(0).getWidth() - super.m_nWidthGap);
            }
            final Rectangle recPCRect2 = super.m_recPCRect;
            recPCRect2.width = 0;
            if (super.m_arrImages != null && super.m_arrImages.size() > 0) {
                if (this.getImageIndex(n) >= super.m_arrImages.size()) {
                    JBLogger.log("Warning:  Image " + n + " does not exist!");
                    n = 0;
                }
                final ListItem listItem = super.m_arrItems.elementAt(super.m_lvi_iItem);
                final Point point2;
                final Point point = point2 = new Point(super.m_recPCRect.x + listItem.getImageBounds().x, super.m_recPCRect.y + listItem.getImageBounds().y);
                ++point2.y;
                final Image imageList = this.getImageList(n);
                if (imageList != null && point.x + super.m_nWidthGap < super.m_arrColumns.elementAt(0).getWidth()) {
                    graphics.drawImage(imageList, point.x, point.y - bounds.y, this);
                }
                recPCRect2.width = point.x;
            }
            recPCRect.x = textBounds.x + textBounds.width;
            recPCRect.width = super.m_recPCRect.width;
            graphics.fillRect(recPCRect.x, recPCRect.y - bounds.y, recPCRect.width, recPCRect.height);
            super.m_recPCRect.x = textBounds.x;
            if (this.m_bHierarchyLines) {
                this.drawHierarchyLines(graphics);
            }
            if (this.m_bButton) {
                this.drawButton(graphics);
            }
            recPCRect.x = textBounds.x - super.m_nWidthGap / 2;
            recPCRect.width = textBounds.width + super.m_nWidthGap / 2;
            recPCRect.y = super.m_recPCRect.y;
            recPCRect.height = measureText * 1;
            graphics.setFont(super.m_arrItems.elementAt(super.m_lvi_iItem).getFont());
            graphics.getFontMetrics();
            graphics.setColor(this.getBackground());
            if (text != null) {
                if (super.m_bAutoWrap) {
                    final String text2 = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getText();
                    Font font = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getFont();
                    if (font == null) {
                        font = super.defaultfont;
                    }
                    graphics.setFont(font);
                    final FontMetrics fontMetrics = this.getFontMetrics(font);
                    final int n2 = super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds().x + super.m_nWidthGap;
                    final Vector wrapText = Text.wrapText(text, super.m_arrColumns.elementAt(super.m_lvi_iSubItem).getWidth() - super.m_nWidthGap * 2 - n2, true, fontMetrics);
                    int n3 = fontMetrics.stringWidth(text2);
                    if (wrapText.size() > 1) {
                        n3 = super.m_arrColumns.elementAt(super.m_lvi_iSubItem).getWidth() - super.m_nWidthGap * 2 - n2;
                    }
                    if (n3 > super.m_arrColumns.elementAt(0).getWidth() - (textBounds.x - 2 + super.m_nWidthGap)) {
                        n3 = textBounds.width;
                    }
                    graphics.setColor(SystemColor.textText);
                    if (!this.isSelected(super.m_lvi_iItem)) {
                        graphics.setColor(Color.black);
                    }
                    else {
                        graphics.setColor(SystemColor.textHighlight);
                        final int max = Math.max(measureText, super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height);
                        if (super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height <= measureText) {
                            graphics.fillRect(textBounds.x - 1 + super.m_nWidthGap - super.m_ptViewportOrg.x, textBounds.y - bounds.y - 1, n3 + 3, measureText - 1);
                            if (super.m_bHasFocus) {
                                this.drawDottedRect(graphics, textBounds.x - 2 + super.m_nWidthGap - super.m_ptViewportOrg.x, textBounds.y - bounds.y - 2, n3 + 4, measureText);
                            }
                        }
                        else {
                            graphics.fillRect(textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x + 1, textBounds.y + (max / 2 - measureText / 2) - bounds.y - 2, n3 + 3, measureText - 1);
                            if (super.m_bHasFocus) {
                                this.drawDottedRect(graphics, textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x, textBounds.y + (max / 2 - measureText / 2) - 1 - bounds.y - 2, n3 + 4, measureText);
                            }
                        }
                        graphics.setXORMode(this.getBackground());
                        graphics.setPaintMode();
                        graphics.setColor(SystemColor.textHighlightText);
                    }
                    final int max2 = Math.max(measureText, super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height);
                    final int height = fontMetrics.getHeight();
                    int n4;
                    int n5;
                    if (super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height <= measureText) {
                        n4 = textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x;
                        n5 = textBounds.y - 2 - bounds.y + height - height / 4;
                    }
                    else {
                        n4 = textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x;
                        n5 = textBounds.y + measureText + (max2 / 2 - measureText / 2) - 1 - bounds.y - 2;
                    }
                    for (int i = 0; i < wrapText.size(); ++i) {
                        graphics.drawString(wrapText.elementAt(i), n4, n5);
                        n5 += height;
                    }
                }
                else {
                    Font font2 = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getFont();
                    if (font2 == null) {
                        font2 = super.defaultfont;
                    }
                    graphics.setFont(font2);
                    final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
                    if (super.m_arrColumns.size() > 1) {
                        String string = "";
                        int n6 = 0;
                        if (super.m_lvi_iSubItem == 0) {
                            n6 = super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().width + super.m_nWidthGap;
                        }
                        final int n7 = fontMetrics2.stringWidth(text) + n6;
                        final int width = super.m_arrColumns.elementAt(0).getWidth();
                        final int n8 = textBounds.x - super.m_ptViewportOrg.x - 3 - this.getIndent();
                        if (n7 > width - n8) {
                            final int stringWidth = fontMetrics2.stringWidth(".");
                            if (n6 + super.m_nWidthGap + stringWidth > width - n8) {
                                string = " ";
                            }
                            else if (n6 + super.m_nWidthGap + 2 * stringWidth > width - n8) {
                                final int n9 = n6 + super.m_nWidthGap + stringWidth;
                                string = ".";
                            }
                            else if (n6 + super.m_nWidthGap + 3 * stringWidth > width - n8) {
                                final int n10 = n6 + super.m_nWidthGap + 2 * stringWidth;
                                string = "..";
                            }
                            else {
                                int n11;
                                for (n11 = 1; n11 < text.length() && n6 + 2 * super.m_nWidthGap + fontMetrics2.stringWidth(text.substring(0, n11 - 1)) + 3 * stringWidth <= width - n8 + this.getIndent(); ++n11) {}
                                if (n11 - 2 < 0) {
                                    n11 = 2;
                                }
                                string = String.valueOf(text.substring(0, n11 - 2)) + "...";
                                final int n12 = n6 + super.m_nWidthGap + fontMetrics2.stringWidth(string);
                            }
                        }
                        if (fontMetrics2.stringWidth(text) + n6 > width - n8) {
                            text = string;
                        }
                    }
                    if (!this.isSelected(super.m_lvi_iItem)) {
                        graphics.setColor(SystemColor.textText);
                    }
                    else {
                        graphics.setColor(SystemColor.textHighlight);
                        final ListItem listItem2 = super.m_arrItems.elementAt(super.m_lvi_iItem);
                        final int max3 = Math.max(measureText, listItem2.getImageBounds().height);
                        final int min = Math.min(fontMetrics2.stringWidth(text), super.m_arrColumns.elementAt(0).getWidth() - listItem2.getTextBounds().x - super.m_nWidthGap - 4);
                        if (super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height <= measureText && super.m_arrColumns.size() == 1) {
                            graphics.fillRect(textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x - 2, textBounds.y - bounds.y, min + 3, measureText - 1);
                            if (super.m_bHasFocus) {
                                this.drawDottedRect(graphics, textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x - 3, textBounds.y - bounds.y - 1, min + 4, measureText);
                            }
                        }
                        else {
                            graphics.fillRect(textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x - 2, textBounds.y + (max3 / 2 - measureText / 2) - bounds.y - 1, min + 3, measureText - 1);
                            if (super.m_bHasFocus) {
                                this.drawDottedRect(graphics, textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x - 3, textBounds.y + (max3 / 2 - measureText / 2) - bounds.y - 2, min + 4, measureText);
                            }
                        }
                        graphics.setXORMode(this.getBackground());
                        graphics.setPaintMode();
                        graphics.setColor(SystemColor.textHighlightText);
                    }
                    final int max4 = Math.max(measureText, super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height);
                    final int height2 = fontMetrics2.getHeight();
                    if (super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height <= measureText) {
                        final int n13 = textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x;
                        final int n14 = textBounds.y - 2 - bounds.y + height2 - height2 / 4;
                    }
                    else {
                        final int n15 = textBounds.x + super.m_nWidthGap - super.m_ptViewportOrg.x;
                        final int n16 = textBounds.y + measureText + (max4 / 2 - measureText / 2) - 1 - bounds.y - 2;
                    }
                    final int n17 = super.m_recPCRect.x + super.m_nWidthGap - super.m_ptViewportOrg.x + 2;
                    final int n18 = super.m_recPCRect.y + super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds().height - bounds.y - super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds().height / 4;
                    final int n19 = super.m_nWidthGap + fontMetrics2.stringWidth(super.m_lvi_pszText);
                    final Column column = super.m_arrColumns.elementAt(super.m_lvi_iSubItem);
                    graphics.drawString(text, n17, n18);
                }
            }
        }
        else {
            super.drawSubItem(graphics);
        }
    }
    
    public void drawTargetHighLight(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(SystemColor.textHighlight);
        final int target = this.getTarget(super.m_nXMouse, super.m_nYMouse);
        if (target != super.m_nOldTargetIndex) {
            if (super.m_nOldTargetIndex != -1 && !this.isSelected(super.m_nOldTargetIndex)) {
                super.m_lvi_iItem = super.m_nOldTargetIndex;
                final ListItem listItem = super.m_arrItems.elementAt(super.m_nOldTargetIndex);
                final Node node = (Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData();
                Font font = node.getFont();
                if (font == null) {
                    font = super.defaultfont;
                }
                graphics.setFont(font);
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                final String text = node.getText();
                int n;
                if (fontMetrics.stringWidth(text) + listItem.getTextBounds().x < super.m_arrColumns.elementAt(0).getWidth() - this.getIndent()) {
                    n = fontMetrics.stringWidth(text) + 4;
                }
                else {
                    n = super.m_arrColumns.elementAt(0).getWidth() - listItem.getTextBounds().x - super.m_nWidthGap;
                }
                graphics.fillRect(listItem.getTextBounds().x + super.m_nWidthGap - 2, listItem.getTextBounds().y - 2 - bounds.y, n, listItem.getTextBounds().height);
            }
            if ((super.m_nOldTargetIndex = target) != -1 && !this.isSelected(target)) {
                super.m_lvi_iItem = target;
                final ListItem listItem2 = super.m_arrItems.elementAt(target);
                final Node node2 = (Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData();
                Font font2 = node2.getFont();
                if (font2 == null) {
                    font2 = super.defaultfont;
                }
                graphics.setFont(font2);
                final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
                final String text2 = node2.getText();
                int n2;
                if (fontMetrics2.stringWidth(text2) + listItem2.getTextBounds().x < super.m_arrColumns.elementAt(0).getWidth() - this.getIndent()) {
                    n2 = fontMetrics2.stringWidth(text2) + 4;
                }
                else {
                    n2 = super.m_arrColumns.elementAt(0).getWidth() - listItem2.getTextBounds().x - super.m_nWidthGap;
                }
                graphics.fillRect(listItem2.getTextBounds().x + super.m_nWidthGap - 2, listItem2.getTextBounds().y - 2 - bounds.y, n2, listItem2.getTextBounds().height);
            }
        }
    }
    
    public void drawVertHierarchyLine(final Graphics graphics, final boolean b, int n, int n2, int n3) {
        if (n3 < n2) {
            final int n4 = n2;
            n2 = n3;
            n3 = n4;
        }
        n -= this.getBounds().x;
        if ((n2 + super.m_ptViewportOrg.y) % 2 != 0 && this.m_nStyleHierarchyLines == 0) {
            --n2;
        }
        if (this.m_nStyleHierarchyLines == 0) {
            this.drawDottedLine(graphics, n, n2, n, n3);
        }
        else {
            graphics.drawLine(n, n2, n, n3);
        }
    }
    
    public void editItem() {
        super.m_nColumnEdit = 0;
        final Rectangle bounds = this.getBounds();
        super.m_bEditModeEnabled = false;
        super.m_bDragModeEnabled = false;
        if (super.selected.length > 1) {
            final int n = super.selected[super.selected.length - 1];
            super.selected = new int[0];
            this.select(n);
        }
        this.m_nodeCurrentEdit = this.getSelectedNode();
        if (this.isRoot(this.m_nodeCurrentEdit)) {
            return;
        }
        final ListItem listItem = super.m_arrItems.elementAt(super.selected[super.selected.length - 1]);
        super.m_textEditNode.setText(this.m_nodeCurrentEdit.getText());
        super.m_textEditNode.setBorderStyle(3);
        super.m_textEditNode.setTextHIndent(1);
        super.m_textEditNode.setTextVIndent(2);
        super.m_textEditNode.selectAll();
        super.m_textEditNode.setBorderColor(Color.black);
        Font font = this.m_nodeCurrentEdit.getFont();
        if (font == null) {
            font = super.defaultfont;
        }
        super.m_textEditNode.setFont(font);
        super.m_textEditNode.selectAll();
        this.m_nodeCurrentEdit.setText("");
        super.m_textEditNode.requestFocus();
        super.m_textEditNode.setBounds(listItem.getTextBounds().x - 2 + super.m_nWidthGap - super.m_ptViewportOrg.x, listItem.getTextBounds().y - bounds.y - 2, listItem.getTextBounds().width + 2, listItem.getTextBounds().height + listItem.getTextBounds().height / 4);
        super.m_textEditNode.setVisible(true);
        super.m_textEditNode.update();
    }
    
    public boolean expand(final Node node, final int n) {
        return this.expand(node, n, true);
    }
    
    public boolean expand(final Node node, final int n, final boolean b) {
        this.getSelectedNode();
        int n2;
        if (n == 2) {
            if (node.isExpanded()) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
        }
        else {
            n2 = n;
        }
        final int nodeIndex = this.nodeIndex(node);
        switch (n2) {
            case 0: {
                if (!node.isExpanded()) {
                    return true;
                }
                node.collapse();
                final Vector vector = new Vector<ListItem>();
                for (int i = 0; i < super.selected.length; ++i) {
                    vector.addElement(super.m_arrItems.elementAt(super.selected[i]));
                }
                this.deselectAll(false);
                this.removeChildrenFromListCtrl(this.m_NNewlyCollapsedNode = node, nodeIndex, false);
                super.selected = new int[0];
                for (int j = 0; j < vector.size(); ++j) {
                    this.select(this.getIndex(vector.elementAt(j)), false);
                }
                this.reMeasureAllItems();
                if (b) {
                    this.update();
                }
                this.processActionEvent(new ActionEvent(this, 3004, "Item_Collapsed"));
                break;
            }
            case 1: {
                if (node.isExpanded()) {
                    return true;
                }
                final Vector vector2 = new Vector<ListItem>();
                for (int k = 0; k < super.selected.length; ++k) {
                    vector2.addElement(super.m_arrItems.elementAt(super.selected[k]));
                }
                this.deselectAll(false);
                node.expand();
                this.m_NNewlyExpandedNode = node;
                if (node.hasChildren()) {
                    this.addChildrenToListCtrl(node, nodeIndex);
                    this.processActionEvent(new ActionEvent(this, 3005, "Item_Expanded"));
                }
                else {
                    this.processActionEvent(new ActionEvent(this, 3002, "Open_Folder"));
                }
                super.selected = new int[0];
                for (int l = 0; l < vector2.size(); ++l) {
                    this.select(this.getIndex(vector2.elementAt(l)), false);
                }
                this.reMeasureAllItems();
                if (b) {
                    this.update();
                }
                break;
            }
        }
        return true;
    }
    
    protected Node expandSelectedItem(final int n) {
        final Node selectedNode = this.getSelectedNode();
        if (!this.expand(selectedNode, n)) {
            return null;
        }
        return selectedNode;
    }
    
    public Node getChildItem(final Node node) {
        final TreeNodeX treeNodeX = new TreeNodeX();
        Node node2;
        if (node == null) {
            node2 = this.m_arrRoots.elementAt(0);
        }
        else {
            node2 = node;
        }
        return (Node)node2.getFirstChild();
    }
    
    public Node getFirstSelectedItem() {
        final Node node = null;
        if (this.getSelectedItems().length == 0) {
            return node;
        }
        return this.getNodeAt(this.getSelectedIndexes()[0]);
    }
    
    public Node getFirstVisibleItem() {
        Node node = null;
        final int nTopRow = super.m_nTopRow;
        if (nTopRow != -1) {
            node = this.getNodeAt(nTopRow);
        }
        return node;
    }
    
    public int getIndent() {
        return this.m_nIndent;
    }
    
    public Node getLastVisibleItem(final Node node) {
        final int itemCount = this.getItemCount();
        if (itemCount < 0) {
            return null;
        }
        return this.getNodeAt(itemCount - 1);
    }
    
    public Node getNewlyCollapsedNode() {
        return this.m_NNewlyCollapsedNode;
    }
    
    public Node getNewlyExpandedNode() {
        return this.m_NNewlyExpandedNode;
    }
    
    public Node getNextItem(final Node node, final int n) {
        switch (n) {
            case 0: {
                return this.getNextSiblingItem(node);
            }
            case 1: {
                return this.getPrevSiblingItem(node);
            }
            case 2: {
                return this.getChildItem(node);
            }
            case 3: {
                return this.getParentItem(node);
            }
            case 4: {
                return this.getRootItem(node);
            }
            case 6: {
                return this.getFirstVisibleItem();
            }
            case 7: {
                return this.getNextVisibleItem(node);
            }
            case 8: {
                return this.getPrevVisibleItem(node);
            }
            case 10: {
                return this.getFirstSelectedItem();
            }
            case 11: {
                return this.getNextSelectedItem(node);
            }
            case 12: {
                return this.getPrevSelectedItem(node);
            }
            default: {
                return null;
            }
        }
    }
    
    public Node getNextItemInDisplayOrder(final Node node) {
        return (Node)node.getNextInDisplayOrder();
    }
    
    public Node getNextSelectedItem(final Node node) {
        int nodeIndex = this.nodeIndex(node);
        if (nodeIndex >= 0) {
            int i = 0;
            while (i < this.getSelectedIndexes().length) {
                if (nodeIndex == this.getSelectedIndexes()[i]) {
                    if (i == this.getSelectedIndexes().length - 1) {
                        nodeIndex = -1;
                        break;
                    }
                    nodeIndex = i + 1;
                    break;
                }
                else {
                    ++i;
                }
            }
            if (nodeIndex != -1) {
                return this.getNodeAt(nodeIndex);
            }
        }
        else {
            Node node2 = node;
            while ((node2 = (Node)node2.getPrevInDisplayOrder()) != null) {
                if (node2.isVisible()) {
                    return this.getNextSelectedItem(node2);
                }
            }
        }
        return null;
    }
    
    public Node getNextSiblingItem(final Node node) {
        Node node2;
        if (node == null) {
            node2 = this.m_arrRoots.elementAt(0);
        }
        else {
            node2 = node;
        }
        return (Node)node2.getNextSibling();
    }
    
    public Node getNextVisibleItem(final Node node) {
        int nodeIndex = this.nodeIndex(node);
        if (nodeIndex >= 0) {
            if (++nodeIndex < this.getItemCount()) {
                return this.getNodeAt(nodeIndex);
            }
        }
        else if (node != null) {
            for (Node node2 = (Node)node.getNextInDisplayOrder(); node2 != null; node2 = (Node)node2.getNextInDisplayOrder()) {
                if (node2.isVisible()) {
                    return node2;
                }
            }
        }
        return null;
    }
    
    public Node getNode(final ListItem listItem) {
        if (listItem == null) {
            return null;
        }
        return (Node)listItem.getItemData();
    }
    
    public Node getNodeAt(final int n) {
        final ListItem item = super.getItemAt(n);
        Node node;
        if (item != null) {
            node = (Node)item.getItemData();
        }
        else {
            node = null;
        }
        return node;
    }
    
    public Node getParentItem(final Node node) {
        TreeNodeX treeNodeX;
        if (node == null) {
            treeNodeX = this.m_arrRoots.elementAt(0);
        }
        else {
            treeNodeX = node;
        }
        if (!this.isRoot((Node)treeNodeX)) {
            while (treeNodeX != null && !this.isRoot((Node)treeNodeX.getParent())) {
                treeNodeX = (TreeNodeX)treeNodeX.getParent();
            }
            return (Node)treeNodeX.getFirstChild();
        }
        return (Node)this.m_arrRoots.elementAt(0).getFirstChild();
    }
    
    public Node getPrevSelectedItem(final Node node) {
        int nodeIndex = this.nodeIndex(node);
        if (nodeIndex >= 0) {
            for (int i = 0; i < this.getSelectedIndexes().length; ++i) {
                if (nodeIndex == this.getSelectedIndexes()[i]) {
                    nodeIndex = i - 1;
                    break;
                }
            }
            if (nodeIndex != -1) {
                return this.getNodeAt(nodeIndex);
            }
        }
        else if (node != null) {
            for (Node node2 = (Node)node.getNextInDisplayOrder(); node2 != null; node2 = (Node)node2.getNextInDisplayOrder()) {
                if (node2.isVisible()) {
                    return this.getPrevSelectedItem(node2);
                }
            }
        }
        return null;
    }
    
    public Node getPrevSiblingItem(final Node node) {
        Node node2;
        if (node == null) {
            node2 = this.m_arrRoots.elementAt(0);
        }
        else {
            node2 = node;
        }
        return (Node)node2.getPrevSibling();
    }
    
    public Node getPrevVisibleItem(final Node node) {
        if (!this.isRoot(node)) {
            int nodeIndex = this.nodeIndex(node);
            if (nodeIndex >= 0) {
                if (--nodeIndex != -1) {
                    return this.getNodeAt(nodeIndex);
                }
            }
            else {
                Node node2 = node;
                while ((node2 = (Node)node2.getPrevInDisplayOrder()) != null) {
                    if (this.nodeIndex(node2) != -1) {
                        return node2;
                    }
                }
            }
        }
        return null;
    }
    
    public Node getRoot(final int n) {
        if (this.m_arrRoots.size() > n) {
            return this.m_arrRoots.elementAt(n);
        }
        return null;
    }
    
    public Node getRootItem() {
        return this.getRootItem(null);
    }
    
    public Node getRootItem(final Node node) {
        if (this.m_arrRoots == null) {
            return null;
        }
        Node node2;
        if (node == null) {
            node2 = this.m_arrRoots.elementAt(0);
        }
        else {
            node2 = node;
        }
        if (!this.isRoot(node2)) {
            while (node2 != null && !this.isRoot((Node)node2.getParent())) {
                node2 = (Node)node2.getParent();
            }
            return (Node)node2.getFirstChild();
        }
        return (Node)this.m_arrRoots.elementAt(0).getFirstChild();
    }
    
    protected int getSelected(final int n, final int n2) {
        if (super.m_textEditNode != null && super.m_textEditNode.isShowing()) {
            this.changeItemText();
        }
        final int selected = super.getSelected(n, n2);
        if (selected > -1) {
            final ListItem item = this.getItemAt(selected);
            final Node node = (Node)item.getItemData();
            Font font = node.getFont();
            if (font == null) {
                font = super.defaultfont;
            }
            final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
            int n3;
            if (fontMetrics.stringWidth(node.getText()) < item.getTextBounds().width) {
                n3 = fontMetrics.stringWidth(node.getText());
            }
            else {
                n3 = item.getTextBounds().width;
            }
            final Rectangle rectangle = new Rectangle(item.getTextBounds().x - 2 + super.m_nWidthGap, n2 - 2, n3, 4);
            if (super.m_arrImages != null && super.m_arrImages.size() > 0) {
                final Rectangle rectangle2 = rectangle;
                rectangle2.width += item.getImageBounds().width;
                final Rectangle rectangle3 = rectangle;
                rectangle3.width += this.getIndent() / 2;
                rectangle.x = item.getImageBounds().x - super.m_ptViewportOrg.x;
            }
            if (rectangle.contains(n, n2)) {
                if (this.isSelected(selected)) {
                    rectangle.x = item.getTextBounds().x - 2 + super.m_nWidthGap - super.m_ptViewportOrg.x;
                    if (rectangle.contains(n, n2) && super.selected.length > 1 && super.m_bEditModeEnabled && !super.m_bDragModeEnabled) {
                        super.selected = new int[0];
                        this.select(selected, false);
                    }
                    return selected;
                }
                return selected;
            }
            else {
                super.m_lvi_iItem = selected;
                super.m_recPCRect = new Rectangle(item.getTextBounds().x - 2 + super.m_nWidthGap, n2 - 2, n3, 4);
                if (this.calcButtonRect().contains(n, n2)) {
                    this.expand(node, 2, true);
                    super.m_bEditModeEnabled = false;
                    super.m_bDragModeEnabled = false;
                    return -1;
                }
            }
        }
        return -1;
    }
    
    public Node getSelectedNode() {
        if (super.selected.length > 0 && super.selected[super.selected.length - 1] < super.m_arrItems.size()) {
            return (Node)super.m_arrItems.elementAt(super.selected[super.selected.length - 1]).getItemData();
        }
        return null;
    }
    
    protected int getTarget(final int n, final int n2) {
        return super.getSelected(n, n2);
    }
    
    protected void initRootNode() {
        if (this.m_arrRoots == null) {
            (this.m_arrRoots = new Vector()).addElement(new Node());
        }
        final ListItem listItem = new ListItem();
        this.m_arrRoots.elementAt(0).bindToListItem(listItem);
        this.addItem(listItem);
    }
    
    public void initRootNode(final String s, final int n, final int n2) {
        if (this.m_arrRoots == null) {
            (this.m_arrRoots = new Vector()).addElement(new Node(s, n, n2));
        }
        final ListItem listItem = new ListItem();
        this.m_arrRoots.elementAt(0).bindToListItem(listItem);
        this.addItem(listItem);
    }
    
    public Node insertItem(final Node node, final Node node2, final Node node3) {
        if (this.m_arrRoots == null) {
            this.initRootNode();
        }
        Node node4;
        if ((node4 = node2) == null) {
            node4 = this.m_arrRoots.elementAt(0);
        }
        final Node node5 = new Node();
        if (!node4.addChild(node, node3)) {
            return null;
        }
        node.collapse();
        if (this.shouldBeInListBox(node)) {
            this.addNodeToListBox(node);
        }
        return node;
    }
    
    public Node insertItem(final Node node, final Node node2, final Node node3, final int image, final int expandedImage) {
        node.setImage(image);
        node.setExpandedImage(expandedImage);
        return this.insertItem(node, node2, node3);
    }
    
    public Node insertItem(final Node node, final Node node2, final Node node3, final int n, final int n2, final Font font) {
        node.setFont(font);
        return this.insertItem(node, node2, node3, n, n2);
    }
    
    public Node insertItem(final String text, final Node node, final Node node2) {
        final Node node3 = new Node();
        node3.setText(text);
        return this.insertItem(node3, node, node2);
    }
    
    public Node insertItem(final String text, final Node node, final Node node2, final int n, final int n2) {
        final Node node3 = new Node();
        node3.setText(text);
        return this.insertItem(node3, node, node2, n, n2);
    }
    
    public Node insertItem(final String text, final Node node, final Node node2, final int n, final int n2, final Font font) {
        final Node node3 = new Node();
        node3.setText(text);
        return this.insertItem(node3, node, node2, n, n2, font);
    }
    
    protected boolean isChildOf(final Object[] array, final Node node) {
        if (node == null) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (node.isAncestor((TreeNode)((ListItem)array[i]).getItemData())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isExpanded(final Node node) {
        return node.isExpanded();
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public boolean isRoot(final int n) {
        for (int i = 0; i < this.m_arrRoots.size(); ++i) {
            if (this.nodeIndex((Node)this.m_arrRoots.elementAt(i)) == n) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isRoot(final Node node) {
        return this.m_arrRoots.contains(node);
    }
    
    public int measureSubItem(final Graphics graphics) {
        try {
            if (super.m_lvi_iSubItem == 0) {
                final int n = this.getIndent() * (((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getDistanceFromRoot() - 1) + this.getIndent();
                int n2;
                if (super.m_arrImages != null && super.m_arrImages.size() != 0) {
                    final Image imageList = this.getImageList(((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getImage());
                    super.m_arrItems.elementAt(super.m_lvi_iItem).setImageBounds(new Rectangle(n + super.m_nWidthGap, 0, imageList.getWidth(this), imageList.getHeight(this)));
                    n2 = super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().width + n + super.m_nWidthGap;
                }
                else {
                    n2 = n + this.m_nWordGap;
                }
                Font font = super.m_arrItems.elementAt(super.m_lvi_iItem).getFont();
                if (font == null) {
                    font = super.defaultfont;
                }
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                if (super.m_bAutoWrap) {
                    super.m_arrItems.elementAt(super.m_lvi_iItem).setTextBounds(n2, 0, Math.min(super.m_arrColumns.elementAt(0).getWidth(), fontMetrics.stringWidth(((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getText()) + n2 + super.m_nWidthGap), 0);
                }
                else {
                    super.m_arrItems.elementAt(super.m_lvi_iItem).setTextBounds(n2, 0, fontMetrics.stringWidth(((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getText()) + n2 + super.m_nWidthGap, 0);
                }
                super.m_arrItems.elementAt(super.m_lvi_iItem).setTextHeight(this.measureText());
                super.m_recPCRect = new Rectangle(super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds());
                return super.m_recPCRect.height = Math.max(super.m_recPCRect.height, super.m_arrItems.elementAt(super.m_lvi_iItem).getImageBounds().height);
            }
            return super.measureSubItem(graphics);
        }
        catch (Exception ex) {
            return 1;
        }
    }
    
    protected int measureText() {
        final Rectangle rectangle = new Rectangle();
        rectangle.y = 0;
        rectangle.height = 0;
        Font font = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getFont();
        if (font == null) {
            font = super.defaultfont;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        super.m_lvi_pszText = ((Node)super.m_arrItems.elementAt(super.m_lvi_iItem).getItemData()).getText();
        if (super.m_bAutoWrap && super.m_lvi_pszText != null) {
            rectangle.height = Text.wrapText(super.m_lvi_pszText, super.m_arrColumns.elementAt(super.m_lvi_iSubItem).getWidth() - super.m_nWidthGap * 2 - (super.m_arrItems.elementAt(super.m_lvi_iItem).getTextBounds().x + super.m_nWidthGap + (this.getInsideRect().width + super.m_nVScrollbarWidth - this.getBounds().width)), true, fontMetrics).size() * fontMetrics.getHeight();
        }
        else {
            rectangle.height = fontMetrics.getHeight();
        }
        return rectangle.height;
    }
    
    protected boolean mouseUp(final MouseEvent mouseEvent, final int n, final int n2) {
        if ((super.m_bDragModeEnabled && super.m_bMouseDrag && super.m_nDraggingState == 0) || super.m_nDraggingState == 1) {
            return super.mouseUp(mouseEvent, n, n2);
        }
        final int[] selectedIndexes = this.getSelectedIndexes();
        if (selectedIndexes != null && selectedIndexes.length > 1) {
            return super.mouseUp(mouseEvent, n, n2);
        }
        if (!super.m_bEditModeAllowed || n < super.m_arrColumns.elementAt(0).getWidth() - super.m_ptViewportOrg.x) {
            return super.mouseUp(mouseEvent, n, n2);
        }
        super.isSelected(super.getSelected(n, n2));
        return true;
    }
    
    public void moveItems(final Object[] array, final int n) {
        if (n == -1) {
            return;
        }
        this.moveItems(array, super.m_arrItems.elementAt(n));
    }
    
    public void moveItems(final Object[] array, final ListItem listItem) {
        if (listItem == null) {
            return;
        }
        final Node node = (Node)listItem.getItemData();
        for (int i = array.length - 1; i > -1; --i) {
            final Node node2 = (Node)((ListItem)array[i]).getItemData();
            if (array[i] == listItem) {
                return;
            }
            if (node.isAncestor(node2)) {
                return;
            }
            if (node2.isParent(node)) {
                return;
            }
            if (this.isRoot(node2)) {
                return;
            }
        }
        this.deselectAll(false);
        for (int j = 0; j < array.length; ++j) {
            final Node node3 = (Node)((ListItem)array[j]).getItemData();
            if (!this.isChildOf(array, node3)) {
                if (node3.hasChildren()) {
                    this.removeChildrenFromListCtrl(node3, this.nodeIndex(node3), false);
                }
                this.delNodeFromListBox(node3, false);
                node3.detachFromTree();
                node.addChild(node3);
                if (this.shouldBeInListBox(node3)) {
                    this.addNodeToListBox(node3);
                }
            }
        }
    }
    
    public int nodeIndex(final Node node) {
        return this.nodeIndex(node, 0);
    }
    
    public int nodeIndex(final Node node, final int n) {
        for (int i = n; i < this.getItemCount(); ++i) {
            if (node == this.getNodeAt(i)) {
                return i;
            }
        }
        return -1;
    }
    
    protected boolean nodeInListBox(final Node node) {
        return this.nodeIndex(node) != -1;
    }
    
    protected void processActionEvent(final ActionEvent actionEvent) {
        if (super.actionListener != null) {
            super.actionListener.actionPerformed(actionEvent);
            return;
        }
        Node node = this.getSelectedNode();
        final Container parent = this.getParent();
        if (parent != null) {
            int n;
            if (actionEvent.getActionCommand().equals("Drag_Drop")) {
                n = 3001;
            }
            else if (actionEvent.getActionCommand().equals("Open_Folder")) {
                n = 3002;
            }
            else if (actionEvent.getActionCommand().equals("Selection_Changed")) {
                n = 3003;
            }
            else if (actionEvent.getActionCommand().equals("Item_Collapsed")) {
                n = 3004;
                node = this.m_itemEventTarget;
            }
            else if (actionEvent.getActionCommand().equals("Item_Expanded")) {
                n = 3005;
                node = this.m_itemEventTarget;
            }
            else if (actionEvent.getActionCommand().equals(super.commandDoubleClicked)) {
                n = 3006;
            }
            else {
                n = 3000;
            }
            parent.postEvent(new Event(this, n, node));
        }
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getID() == 401) {
            switch (keyCode) {
                case 37: {
                    if (this.getSelectedNode() != null && this.getSelectedNode().isExpanded()) {
                        this.expand(this.getSelectedNode(), 0, true);
                        super.prev = this.nodeIndex(this.getSelectedNode());
                        break;
                    }
                    return;
                }
                case 39: {
                    if (this.getSelectedNode() != null) {
                        if (!this.getSelectedNode().isExpanded()) {
                            this.expand(this.getSelectedNode(), 1, true);
                            super.prev = this.nodeIndex(this.getSelectedNode());
                        }
                        this.reMeasureAllItems();
                        this.update();
                    }
                    return;
                }
            }
        }
        if (keyCode != 39 && keyCode != 37) {
            super.processKeyEvent(keyEvent);
        }
    }
    
    protected void removeChildrenFromListCtrl(final Node node, final int n) {
        this.removeChildrenFromListCtrl(node, n, true);
    }
    
    protected void removeChildrenFromListCtrl(final Node node, final int n, final boolean b) {
        if (n == -1) {
            return;
        }
        int n2 = 0;
        for (int n3 = n + 1; n3 < this.getItemCount() && node.isDescendant(this.getNodeAt(n3)); ++n3, ++n2) {}
        final int n4 = n + 1 + n2 - 1;
        for (int i = super.selected.length - 1; i >= 0; --i) {
            final int n5 = super.selected[i];
            if (n5 > n && n5 < n4) {
                final int nodeIndex = this.nodeIndex(this.getNodeAt(n5));
                if (this.isSelected(nodeIndex)) {
                    this.deselect(nodeIndex);
                }
            }
        }
        if (n2 > 0) {
            if (b) {
                super.deleteItem(n + 1, n2);
            }
            else {
                super.deleteItem(n + 1, n2, false);
            }
        }
    }
    
    public void setHasFocus(final boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    
    public void setHierarchyLineColor(final Color colHierarchyLines) {
        this.m_colHierarchyLines = colHierarchyLines;
    }
    
    public void setHierarchyLineStyle(final int nStyleHierarchyLines) {
        this.m_nStyleHierarchyLines = nStyleHierarchyLines;
    }
    
    public void setIndent(final int nIndent) {
        this.m_nIndent = nIndent;
        this.reMeasureAllItems();
        this.update();
    }
    
    protected boolean shouldBeInListBox(final Node node) {
        return ((Node)node.getParent()).isExpanded() && this.nodeIndex((Node)node.getParent()) != -1;
    }
}
