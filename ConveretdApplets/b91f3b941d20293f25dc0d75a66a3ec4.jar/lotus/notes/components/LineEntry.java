// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.util.Vector;
import lotus.notes.util.TreeListEntry;

public class LineEntry extends TreeListEntry
{
    private boolean bIsSelected;
    private boolean bCollapsed;
    protected Vector vCells;
    protected Dimension LineDimension;
    private Point Origin;
    private boolean bDrawSelRect;
    protected Color bgColor;
    private Color fgColor;
    private Color selectedBgColor;
    private Color selectedColor;
    private Color mouseOverBgColor;
    private Color mouseOverColor;
    private boolean bDrawMouseOver;
    private boolean bMouseOverEntry;
    private boolean bJustified;
    protected Component ParentComponent;
    private boolean horizontalLayout;
    private int EntryHeight;
    private int EntryWidth;
    protected int direction;
    protected long whenSave;
    protected static final int DBLCLICK_INTERVAL = 1200;
    boolean bUseClickCount;
    
    public LineEntry(final Component parentComponent) {
        this.bgColor = Color.white;
        this.fgColor = Color.black;
        this.selectedBgColor = null;
        this.selectedColor = Color.white;
        this.mouseOverBgColor = null;
        this.mouseOverColor = Color.white;
        this.direction = 0;
        this.whenSave = 0L;
        this.bUseClickCount = (System.getProperty("os.name").indexOf("Mac") == -1 && System.getProperty("os.name").indexOf("Linux") == -1);
        this.horizontalLayout = false;
        final boolean b = false;
        this.EntryWidth = (b ? 1 : 0);
        this.EntryHeight = (b ? 1 : 0);
        this.bIsSelected = false;
        this.bCollapsed = false;
        this.vCells = null;
        this.bDrawSelRect = true;
        final boolean b2 = false;
        this.bMouseOverEntry = b2;
        this.bDrawMouseOver = b2;
        this.LineDimension = new Dimension(0, 0);
        this.Origin = new Point(0, 0);
        this.bJustified = false;
        this.ParentComponent = parentComponent;
    }
    
    public boolean IsSelectable() {
        return this.bDrawSelRect;
    }
    
    public void SetSelectable(final boolean bDrawSelRect) {
        this.bDrawSelRect = bDrawSelRect;
    }
    
    public boolean IsSelected() {
        return this.bIsSelected;
    }
    
    public void setSelected(final boolean bIsSelected) {
        this.bIsSelected = bIsSelected;
    }
    
    public boolean IsMouseOverEnabled() {
        return this.bDrawMouseOver;
    }
    
    public void SetMouseOverEnable(final boolean bDrawMouseOver) {
        this.bDrawMouseOver = bDrawMouseOver;
    }
    
    public boolean IsMouseOver() {
        return this.bMouseOverEntry;
    }
    
    public void setMouseOverEntry(final boolean bMouseOverEntry) {
        this.bMouseOverEntry = bMouseOverEntry;
    }
    
    public boolean IsCollapsed() {
        return this.bCollapsed;
    }
    
    public boolean isJustified() {
        return this.bJustified;
    }
    
    public void setJustified(final boolean bJustified) {
        this.bJustified = bJustified;
    }
    
    public void Collapse() {
        this.bCollapsed = true;
        ((OutlineView)this.ParentComponent).UpdateCounts(false, this);
    }
    
    public void Expand() {
        this.bCollapsed = false;
        ((OutlineView)this.ParentComponent).UpdateCounts(true, this);
    }
    
    public void setEntryHeight(final int entryHeight) {
        this.EntryHeight = entryHeight;
    }
    
    public int getEntryHeight() {
        return this.EntryHeight;
    }
    
    public void setEntryWidth(final int entryWidth) {
        this.EntryWidth = entryWidth;
    }
    
    public int getEntryWidth() {
        return this.EntryWidth;
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public void AddCell(final CellEntry cellEntry) {
        if (this.vCells == null) {
            this.vCells = new Vector();
        }
        cellEntry.SetLine(this);
        this.vCells.addElement(cellEntry);
        this.bJustified = false;
    }
    
    public void RemoveCell(final CellEntry cellEntry) {
        if (this.vCells.contains(cellEntry)) {
            this.vCells.removeElement(cellEntry);
        }
        cellEntry.SetLine(null);
        this.bJustified = false;
    }
    
    public void ComputeDimension(final Graphics graphics, final Component component) {
        final Dimension lineDimension = new Dimension(0, 0);
        if (null != this.vCells) {
            for (int size = this.vCells.size(), i = 0; i < size; ++i) {
                final Dimension getDimension = this.vCells.elementAt(i).GetDimension(graphics, component);
                final Dimension dimension = lineDimension;
                dimension.width += getDimension.width;
                lineDimension.height = Math.max(lineDimension.height, getDimension.height);
            }
        }
        if (this.EntryWidth != 0) {
            lineDimension.width = this.EntryWidth;
        }
        if (this.EntryHeight != 0) {
            lineDimension.height = this.EntryHeight;
        }
        this.LineDimension = lineDimension;
        this.bJustified = true;
    }
    
    public Dimension GetLineBounds() {
        return this.LineDimension;
    }
    
    public Rectangle GetSelectionRect(final Graphics graphics, final Component component) {
        final int size = this.vCells.size();
        Rectangle rectangle = null;
        for (int i = 0; i < size; ++i) {
            final CellEntry cellEntry = this.vCells.elementAt(i);
            if (cellEntry.IncludeInSelection()) {
                final Rectangle getSelectionRect = cellEntry.GetSelectionRect(this.Origin);
                if (rectangle == null) {
                    rectangle = getSelectionRect;
                }
                else {
                    if (getSelectionRect.x + getSelectionRect.width > rectangle.x + rectangle.width) {
                        rectangle.width = getSelectionRect.x + getSelectionRect.width;
                    }
                    rectangle.height = Math.max(rectangle.height, getSelectionRect.height);
                }
                final int width = component.size().width;
                if (rectangle.x + rectangle.width >= width) {
                    rectangle.width = width - rectangle.x - 1;
                    break;
                }
            }
        }
        if (null == rectangle) {
            return new Rectangle(0, 0, 0, 0);
        }
        return rectangle;
    }
    
    public void Paint(final Graphics graphics, final LineEntry lineEntry, final Point origin, final Component component, final Image image) {
        this.setOrigin(origin);
        for (int size = this.vCells.size(), i = 0; i < size; ++i) {
            ((CellEntry)this.vCells.elementAt(i)).Paint(graphics, origin, component);
        }
    }
    
    public boolean MouseDown(final Event event, final int n, final int n2) {
        final CellEntry cellFromPos = this.CellFromPos(n, n2);
        boolean mouseDown = false;
        if (cellFromPos != null) {
            mouseDown = cellFromPos.MouseDown(event);
        }
        return mouseDown;
    }
    
    protected void setOrigin(final Point point) {
        this.Origin.x = point.x;
        this.Origin.y = point.y;
    }
    
    public Point getOrigin() {
        return this.Origin;
    }
    
    public CellEntry CellFromPos(final int n, final int n2) {
        for (int size = this.vCells.size(), i = 0; i < size; ++i) {
            final CellEntry cellEntry = this.vCells.elementAt(i);
            final Rectangle getRect = cellEntry.GetRect(this.Origin);
            getRect.height = this.LineDimension.height;
            if (getRect.inside(n, n2)) {
                return cellEntry;
            }
        }
        return null;
    }
    
    public int GetCellCount() {
        if (this.vCells != null) {
            return this.vCells.size();
        }
        return 0;
    }
    
    public CellEntry GetCellAtIndex(final int n) {
        if (this.vCells != null) {
            return this.vCells.elementAt(n);
        }
        return null;
    }
    
    public void setForegroundColor(final Color fgColor) {
        this.fgColor = fgColor;
    }
    
    public Color getBackgroundColor() {
        return this.bgColor;
    }
    
    public Color getForegroundColor() {
        return this.fgColor;
    }
    
    public void setBackgroundColor(final Color bgColor) {
        this.bgColor = bgColor;
    }
    
    public void setSelectedColors(final Color selectedColor, final Color selectedBgColor) {
        this.selectedColor = selectedColor;
        this.selectedBgColor = selectedBgColor;
    }
    
    public Color getSelectedColor() {
        return this.selectedColor;
    }
    
    public Color getSelectedBgColor() {
        return this.selectedBgColor;
    }
    
    public void setMouseOverColors(final Color mouseOverColor, final Color mouseOverBgColor) {
        this.mouseOverColor = mouseOverColor;
        this.mouseOverBgColor = mouseOverBgColor;
    }
    
    public Color getMouseOverColor() {
        return this.mouseOverColor;
    }
    
    public Color getMouseOverBgColor() {
        return this.mouseOverBgColor;
    }
    
    public int getVerticalOffset() {
        return 0;
    }
    
    public void setHorizontalLayout(final boolean horizontalLayout) {
        this.horizontalLayout = horizontalLayout;
    }
    
    public boolean getHorizontalLayout() {
        return this.horizontalLayout;
    }
    
    protected boolean isDoubleClick(final Event event) {
        boolean b = false;
        if (this.bUseClickCount) {
            b = (event.clickCount > 1);
        }
        else if (this.whenSave == 0L) {
            this.whenSave = event.when;
        }
        else {
            b = (event.when - this.whenSave < 1200L);
            if (b) {
                this.whenSave = 0L;
            }
            else {
                this.whenSave = event.when;
            }
        }
        return b;
    }
}
