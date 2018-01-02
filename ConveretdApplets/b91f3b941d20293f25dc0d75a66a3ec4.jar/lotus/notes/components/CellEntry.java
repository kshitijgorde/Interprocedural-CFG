// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class CellEntry
{
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    public static final int ALIGN_BOTTOM = 4;
    public int hAlignment;
    public int vAlignment;
    public int padding;
    public Color fgColor;
    public Color bgColor;
    public LineEntry Line;
    public ColDesc ColInfo;
    public Dimension MaxExtents;
    public Dimension ClipExtents;
    public boolean bIncludeInSelection;
    public boolean bExtentsValid;
    public boolean bUseAllWidth;
    public boolean bHandleMouseEvents;
    protected int readingOrder;
    protected int direction;
    
    public CellEntry() {
        this.padding = 0;
        this.fgColor = null;
        this.bgColor = null;
        this.bHandleMouseEvents = true;
        this.readingOrder = 0;
        this.direction = 0;
        this.Line = null;
        this.bIncludeInSelection = true;
        this.MaxExtents = new Dimension(0, 0);
        this.ClipExtents = new Dimension(0, 0);
        this.hAlignment = 0;
        this.vAlignment = 3;
        this.bExtentsValid = false;
        this.bUseAllWidth = false;
    }
    
    public void SetLine(final LineEntry line) {
        this.Line = line;
    }
    
    public LineEntry GetCellContainer() {
        return this.Line;
    }
    
    public void SetColumnDescriptor(final ColDesc colInfo) {
        this.ColInfo = colInfo;
    }
    
    public ColDesc getColumnDescriptor() {
        return this.ColInfo;
    }
    
    public void setReadingOrder(final int readingOrder) {
        this.readingOrder = readingOrder;
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public boolean IncludeInSelection() {
        return this.bIncludeInSelection;
    }
    
    public Dimension GetDimension(final Graphics graphics, final Component component) {
        if (this.bExtentsValid && this.Line.isJustified()) {
            return this.ClipExtents;
        }
        this.UpdateExtents(graphics, component);
        return this.ClipExtents;
    }
    
    protected void UpdateExtents(final Graphics graphics, final Component component) {
    }
    
    public Rectangle GetClipRect(final Point point) {
        final Rectangle rectangle = new Rectangle();
        rectangle.x = this.padding + point.x + this.ColInfo.GetxPos();
        rectangle.y = point.y;
        rectangle.width = this.ColInfo.GetWidth();
        rectangle.height = this.ClipExtents.height;
        return rectangle;
    }
    
    public Rectangle GetSelectionRect(final Point point) {
        final Rectangle rectangle = new Rectangle();
        rectangle.x = point.x + this.ColInfo.GetxPos();
        rectangle.y = point.y - 2;
        rectangle.width = this.ClipExtents.width;
        rectangle.height = this.ClipExtents.height + 2;
        if (this.ColInfo.bClip) {
            rectangle.width = Math.min(rectangle.width, this.MaxExtents.width);
        }
        return rectangle;
    }
    
    public Rectangle GetRect(final Point point) {
        final Rectangle rectangle = new Rectangle();
        rectangle.x = this.padding + point.x + this.ColInfo.GetxPos();
        rectangle.y = point.y;
        rectangle.width = this.MaxExtents.width;
        rectangle.height = this.MaxExtents.height;
        return rectangle;
    }
    
    public void Paint(final Graphics graphics, final Point point, final Component component) {
    }
    
    public boolean MouseDown(final Event event) {
        return false;
    }
    
    public void setHorizontalAlignment(final int hAlignment) {
        if (hAlignment >= 0 && hAlignment <= 1) {
            this.hAlignment = hAlignment;
        }
    }
    
    public int getHorizontalAlignment() {
        return this.hAlignment;
    }
    
    public void setVerticalAlignment(final int vAlignment) {
        if (vAlignment >= 2 && vAlignment <= 4) {
            this.vAlignment = vAlignment;
        }
    }
    
    public int getVerticalAlignment() {
        return this.vAlignment;
    }
    
    public void setPadding(final int padding) {
        this.padding = padding;
    }
    
    public int getPadding() {
        return this.padding;
    }
    
    public static int handleHAlignment(final ColDesc colDesc, final int n, final int n2, final int n3) {
        if (colDesc.hAlignment == 1) {
            return toggleHorzPos(n, n2, n3);
        }
        if (colDesc.hAlignment == 2) {
            return centerHorzPos(n2, n3);
        }
        return n;
    }
    
    public static int toggleHorzPos(final int n, final int n2, final int n3) {
        return n3 - n - n2;
    }
    
    public static int centerHorzPos(final int n, final int n2) {
        return n2 / 2 - n / 2;
    }
}
