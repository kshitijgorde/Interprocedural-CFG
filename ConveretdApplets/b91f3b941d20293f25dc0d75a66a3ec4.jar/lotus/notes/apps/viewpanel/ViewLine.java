// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.awt.Color;
import java.awt.Dimension;
import lotus.notes.util.Bidi;
import java.awt.Image;
import java.awt.Point;
import lotus.notes.components.OutlineView;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Graphics;
import lotus.notes.components.CellEntry;
import java.awt.Component;
import lotus.notes.components.TwistieCell;
import lotus.notes.components.LineEntry;

public class ViewLine extends LineEntry
{
    public String UNID;
    public String collectionPos;
    public int children;
    public boolean wasFullyExpanded;
    public boolean isDocument;
    public boolean isCategoryTotal;
    public ViewLineRange range;
    private TwistieCell twistieCell;
    private ViewPanel viewPanel;
    private boolean isLastEntry;
    private boolean isFirstEntry;
    
    public ViewLine(final ViewPanel viewPanel, final ViewLineRange range, final Component component, final String unid) {
        super(component);
        this.UNID = null;
        this.collectionPos = null;
        this.children = 0;
        this.wasFullyExpanded = false;
        this.isDocument = true;
        this.isCategoryTotal = false;
        this.range = null;
        this.twistieCell = null;
        this.viewPanel = null;
        this.isLastEntry = false;
        this.isFirstEntry = false;
        this.UNID = unid;
        this.range = range;
        this.viewPanel = viewPanel;
    }
    
    public void AddCell(final CellEntry cellEntry) {
        super.AddCell(cellEntry);
        if (cellEntry instanceof TwistieCell) {
            this.twistieCell = (TwistieCell)cellEntry;
        }
    }
    
    public void RemoveCell(final CellEntry cellEntry) {
        super.RemoveCell(cellEntry);
        if (cellEntry instanceof TwistieCell) {
            this.twistieCell = null;
        }
    }
    
    public TwistieCell getTwistieCell() {
        return this.twistieCell;
    }
    
    public void SetUNID(final String unid) {
        this.UNID = unid;
    }
    
    public String getUNID() {
        return this.UNID;
    }
    
    public void SetCollectionPos(final String s) {
        this.collectionPos = new String(s);
    }
    
    public String GetCollectionPos() {
        return this.collectionPos;
    }
    
    public int GetCollectionPosAsInt() {
        final String collectionPos = this.collectionPos;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= collectionPos.length() - 1; ++i) {
            final char char1 = collectionPos.charAt(i);
            if (char1 == '.') {
                break;
            }
            sb.append(char1);
        }
        return Integer.parseInt(sb.toString());
    }
    
    public void SetChildCount(final int children) {
        this.children = children;
    }
    
    public int GetChildCount() {
        return this.children;
    }
    
    public Rectangle GetSelectionRect(final Graphics graphics, final Component component) {
        final Rectangle getSelectionRect = super.GetSelectionRect(graphics, component);
        getSelectionRect.x = 1;
        return getSelectionRect;
    }
    
    public boolean MouseDown(final Event event, final int n, final int n2) {
        boolean mouseDown = super.MouseDown(event, n, n2);
        if (!mouseDown && super.ParentComponent instanceof OutlineView) {
            ((OutlineView)super.ParentComponent).setSelectedEntry(this);
        }
        if (!mouseDown && (this.isDocument || this.GetChildCount() > 0)) {
            if (this.viewPanel != null) {
                boolean b = false;
                boolean b2 = false;
                final boolean doubleClick = this.isDoubleClick(event);
                if (doubleClick) {
                    b2 = false;
                    b = true;
                }
                else if (this.viewPanel.isPreviewMode()) {
                    b = true;
                    b2 = true;
                }
                if (((!doubleClick && this.getTwistieCell() == null) || (doubleClick && !this.isDocument)) && this.GetChildCount() > 0) {
                    if (!this.wasFullyExpanded) {
                        this.viewPanel.expandSelectedDocument();
                    }
                    else if (this.wasFullyExpanded) {
                        this.viewPanel.collapseSelectedDocument();
                    }
                }
                if (b && this.isDocument) {
                    this.viewPanel.openDocumentByUNID(this.UNID, b2);
                }
            }
            mouseDown = true;
        }
        return mouseDown;
    }
    
    public void Paint(final Graphics graphics, final LineEntry lineEntry, final Point origin, final Component component, final Image image) {
        this.setOrigin(origin);
        final int direction = this.getDirection();
        final Dimension size = component.size();
        final int width = size.width;
        if (this.viewPanel.isSelectionMarginVisible()) {
            final int selectionMarginWidth = this.viewPanel.getSelectionMarginWidth();
            Graphics graphics2;
            if (direction == 0) {
                graphics2 = graphics.create(0, 0, selectionMarginWidth, size.height);
            }
            else {
                graphics2 = graphics.create(Bidi.toggleHorzPos(selectionMarginWidth, 0, size.width), 0, selectionMarginWidth, size.height);
            }
            final int n = 2;
            graphics2.setColor(component.getBackground());
            for (int i = 0; i < n; ++i) {
                ((CellEntry)super.vCells.elementAt(i)).Paint(graphics2, new Point(0, origin.y), component);
            }
            final int n2 = width - selectionMarginWidth;
            Graphics graphics3;
            if (direction == 0) {
                graphics3 = graphics.create(selectionMarginWidth, 0, n2, size.height);
            }
            else {
                graphics3 = graphics.create(0, 0, n2, size.height);
            }
            if (super.bgColor != null) {
                final Color color = graphics.getColor();
                graphics3.setColor(super.bgColor);
                graphics3.fillRect(0, origin.y - 1, component.size().width, super.LineDimension.height);
                graphics3.setColor(color);
            }
            for (int size2 = super.vCells.size(), j = 2; j < size2; ++j) {
                ((CellEntry)super.vCells.elementAt(j)).Paint(graphics3, new Point(origin.x - selectionMarginWidth, origin.y), component);
            }
        }
        else {
            final Graphics create = graphics.create(0, 0, size.width, size.height);
            if (super.bgColor != null) {
                create.setColor(super.bgColor);
                create.fillRect(0, origin.y - 1, width, super.LineDimension.height);
            }
            super.Paint(create, lineEntry, origin, component, image);
        }
    }
    
    public boolean isJustified() {
        return false;
    }
    
    public void setFirstEntry(final boolean isFirstEntry) {
        this.isFirstEntry = isFirstEntry;
    }
    
    public boolean isFirstEntry() {
        return this.isFirstEntry;
    }
    
    public void setLastEntry(final boolean isLastEntry) {
        this.isLastEntry = isLastEntry;
    }
    
    public boolean isLastEntry() {
        return this.isLastEntry;
    }
}
