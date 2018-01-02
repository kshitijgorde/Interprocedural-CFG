// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Event;
import lotus.notes.util.Bidi;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Point;
import lotus.notes.util.TreeListEntry;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import lotus.notes.util.TreeList;
import java.util.Vector;
import java.awt.Canvas;

public class OutlineView extends Canvas
{
    public static final int OVPAINT = 0;
    public static final int OVUPDATECOUNTS = 1;
    public static final int OVGETENTRYATPOS = 2;
    public static final int OVISENTRYVISIBLE = 3;
    public static final int OVFORCEPAINT = 4;
    public static final int SELECTION_BORDER_NONE = 0;
    public static final int SELECTION_BORDER_BOX = 1;
    public static final int SELECTION_BORDER_FILL = 2;
    private static final boolean DEBUG_EVENT = false;
    private static final boolean DEBUG = false;
    private Vector vColDescVectors;
    private TreeList tree;
    private LineEntry FirstVisibleEntry;
    private LineEntry LastVisibleEntry;
    private LineEntry SelectedEntry;
    private LineEntry MouseOverEntry;
    private int VertScrollOffset;
    protected int HorzScrollOffset;
    private Dimension ViewDimension;
    private int InterlineSpacing;
    private boolean bNeedVScrollbar;
    private boolean bNeedHScrollbar;
    private boolean bNeedsTraverse;
    protected int selectionBorderStyle;
    protected Image offscreenBuffer;
    protected boolean doubleBufferEnabled;
    protected boolean offscreenBufferInvalid;
    private int direction;
    protected Image bgImage;
    protected NImage bgNImage;
    public Color altrowColor;
    public int currentRow;
    public boolean allvisible;
    private int ExpandedCount;
    private int VisibleCount;
    private boolean horizontalLayout;
    private Rectangle ViewRect;
    private Rectangle LineRect;
    
    public OutlineView(final Dimension dimension) {
        this.bNeedsTraverse = true;
        this.selectionBorderStyle = 1;
        this.offscreenBuffer = null;
        this.doubleBufferEnabled = true;
        this.offscreenBufferInvalid = true;
        this.direction = 0;
        this.bgImage = null;
        this.bgNImage = null;
        this.altrowColor = null;
        this.currentRow = 0;
        this.allvisible = true;
        this.horizontalLayout = false;
        this.ViewRect = new Rectangle();
        this.LineRect = new Rectangle();
        this.tree = new TreeList();
        this.FirstVisibleEntry = null;
        this.VertScrollOffset = 0;
        this.HorzScrollOffset = 0;
        this.InterlineSpacing = 0;
        this.ViewDimension = new Dimension(dimension);
        this.vColDescVectors = new Vector();
        final LineEntry lineEntry = null;
        this.MouseOverEntry = lineEntry;
        this.SelectedEntry = lineEntry;
        final boolean b = false;
        this.VisibleCount = (b ? 1 : 0);
        this.ExpandedCount = (b ? 1 : 0);
        final boolean b2 = false;
        this.bNeedHScrollbar = b2;
        this.bNeedVScrollbar = b2;
    }
    
    public void printAll(final Graphics graphics) {
        System.out.println("OutlineView.printAll CALLED");
        this.setDoubleBufferEnabled(false);
        this.paint(graphics);
        this.setDoubleBufferEnabled(true);
    }
    
    public void setHScrollOffset(final int horzScrollOffset) {
        this.HorzScrollOffset = horzScrollOffset;
    }
    
    public synchronized void setDoubleBufferEnabled(final boolean doubleBufferEnabled) {
        this.doubleBufferEnabled = doubleBufferEnabled;
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public synchronized boolean isDoubleBufferEnabled() {
        return this.doubleBufferEnabled;
    }
    
    public void SetInterlineSpacing(final int interlineSpacing) {
        this.InterlineSpacing = interlineSpacing;
    }
    
    public int GetColDescVectorSize() {
        return this.vColDescVectors.size();
    }
    
    public void AddColumnDescVector(final Vector vector) {
        this.vColDescVectors.addElement(vector);
    }
    
    public void RemoveColumnDescVector(final Vector vector) {
        if (this.vColDescVectors.contains(vector)) {
            this.vColDescVectors.removeElement(vector);
        }
    }
    
    public Vector GetColumnDescVector(final int n) {
        if (n + 1 > this.vColDescVectors.size()) {
            return null;
        }
        return this.vColDescVectors.elementAt(n);
    }
    
    public void AddLineEntry(final LineEntry firstVisibleEntry, final LineEntry lineEntry) {
        this.tree.AddEntry(firstVisibleEntry, lineEntry);
        if (this.FirstVisibleEntry == null) {
            this.FirstVisibleEntry = firstVisibleEntry;
        }
        this.UpdateCounts(true, firstVisibleEntry);
    }
    
    public void RemoveLineEntry(final LineEntry lineEntry) {
        this.tree.RemoveEntry(lineEntry);
        this.UpdateCounts(false, lineEntry);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean doubleBufferEnabled = this.isDoubleBufferEnabled();
        final int width = this.size().width;
        final int height = this.size().height;
        Graphics graphics2;
        if (doubleBufferEnabled) {
            if (this.offscreenBufferInvalid) {
                if (this.offscreenBuffer != null) {
                    this.offscreenBuffer.flush();
                }
                this.offscreenBuffer = this.createImage(width, height);
                this.offscreenBufferInvalid = false;
            }
            graphics2 = this.offscreenBuffer.getGraphics();
        }
        else {
            graphics2 = graphics;
        }
        final Color foreground = this.getForeground();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, width, height);
        graphics2.setColor(foreground);
        if (this.bgImage != null) {
            this.bgNImage.paint(graphics2, 0, 0, width, height);
        }
        this.TraverseEntries(0, graphics2, null, null);
        if (doubleBufferEnabled) {
            graphics.drawImage(this.offscreenBuffer, 0, 0, null);
            graphics2.dispose();
        }
    }
    
    public void ScrollLineArray(final int n, final int n2) {
        this.ScrollLineArray(n, n2, true);
    }
    
    public void ScrollLineArray(final int n, final int n2, final boolean b) {
        LineEntry firstVisibleEntry = this.FirstVisibleEntry;
        if (firstVisibleEntry == null) {
            firstVisibleEntry = (LineEntry)this.tree.getFirst();
        }
        if (firstVisibleEntry == null) {
            return;
        }
        final int n3 = (n <= 0) ? 1 : 0;
        for (int i = 0; i < n2; ++i) {
            if (n3 == 0) {
                int n4;
                if (firstVisibleEntry.getChild() != null && !firstVisibleEntry.IsCollapsed()) {
                    n4 = TreeList.FULL;
                }
                else {
                    n4 = TreeList.CURRENTANDUP;
                }
                final TreeList tree = this.tree;
                firstVisibleEntry = (LineEntry)TreeList.Traverse(firstVisibleEntry, n3, n4);
            }
            else {
                final TreeList tree2 = this.tree;
                firstVisibleEntry = (LineEntry)TreeList.TraverseBackwardFull(firstVisibleEntry);
                if (firstVisibleEntry != null) {
                    while (this.ParentCollapsed(firstVisibleEntry)) {
                        final LineEntry lineEntry = (LineEntry)firstVisibleEntry.getParent();
                        if (lineEntry != null) {
                            firstVisibleEntry = lineEntry;
                        }
                    }
                }
            }
            if (firstVisibleEntry == null) {
                break;
            }
            this.FirstVisibleEntry = firstVisibleEntry;
        }
        this.bNeedsTraverse = true;
        if (b) {
            this.repaint();
        }
    }
    
    protected boolean IsLineVisible(final LineEntry lineEntry, final Point point) {
        final Dimension getLineBounds = lineEntry.GetLineBounds();
        this.ViewRect.reshape(this.HorzScrollOffset, this.VertScrollOffset, this.ViewDimension.width, this.ViewDimension.height);
        this.LineRect.reshape(point.x, point.y, getLineBounds.width, getLineBounds.height);
        return this.LineRect.intersects(this.ViewRect);
    }
    
    protected boolean IsLineClipped(final LineEntry lineEntry, final Point point) {
        final Dimension getLineBounds = lineEntry.GetLineBounds();
        this.ViewRect.reshape(this.HorzScrollOffset, this.VertScrollOffset, this.bNeedVScrollbar ? (this.ViewDimension.width - 13) : this.ViewDimension.width, this.bNeedHScrollbar ? (this.ViewDimension.height - 13) : this.ViewDimension.height);
        this.LineRect.reshape(point.x, point.y, getLineBounds.width, getLineBounds.height);
        final Rectangle intersection = this.ViewRect.intersection(this.LineRect);
        if (this.horizontalLayout) {
            return intersection.x != this.LineRect.x || intersection.width != this.LineRect.width;
        }
        return intersection.y != this.LineRect.y || intersection.height != this.LineRect.height;
    }
    
    public void reshape(final int n, final int n2, final int width, final int height) {
        this.offscreenBufferInvalid = true;
        this.ViewDimension.width = width;
        this.ViewDimension.height = height;
        this.TraverseEntries(1, null, null, null);
        super.reshape(n, n2, width, height);
    }
    
    public Dimension preferredSize() {
        return this.ViewDimension;
    }
    
    public Dimension minimumSize() {
        return this.ViewDimension;
    }
    
    public void DrawSelRect(final LineEntry lineEntry, final boolean b) {
        this.DrawSelRect(this.getGraphics(), lineEntry, b);
    }
    
    public void DrawSelRect(final Graphics graphics, final LineEntry lineEntry, final boolean b) {
        final Rectangle getSelectionRect = lineEntry.GetSelectionRect(graphics, this);
        lineEntry.setSelected(!b);
        if (this.selectionBorderStyle == 1) {
            graphics.setXORMode(this.getBackground());
            if (this.direction == 0) {
                graphics.drawRect(getSelectionRect.x + this.HorzScrollOffset, getSelectionRect.y - 1, this.size().width - this.HorzScrollOffset, getSelectionRect.height + 1);
            }
            else {
                graphics.drawRect(Bidi.toggleHorzPos(getSelectionRect.x + this.HorzScrollOffset, this.size().width - this.HorzScrollOffset, this.size().width), getSelectionRect.y - 1, this.size().width - this.HorzScrollOffset, getSelectionRect.height + 1);
            }
            graphics.setPaintMode();
        }
        else if (this.selectionBorderStyle == 2) {
            lineEntry.Paint(graphics, lineEntry, new Point(this.HorzScrollOffset, getSelectionRect.y), this, this.bgImage);
        }
    }
    
    public boolean ParentCollapsed(final LineEntry lineEntry) {
        for (LineEntry lineEntry2 = (LineEntry)lineEntry.getParent(); null != lineEntry2; lineEntry2 = (LineEntry)lineEntry2.getParent()) {
            if (lineEntry2.IsCollapsed()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            if (event.id == 505) {
                if (this.MouseOverEntry != null) {
                    this.MouseOverEntry.setMouseOverEntry(false);
                    this.MouseOverEntry = null;
                    this.repaint();
                }
                return super.handleEvent(event);
            }
            if (event.id == 501 || event.id == 503) {
                int n = 0;
                final Point point = new Point(event.x + this.HorzScrollOffset, event.y);
                if (this.direction == 1) {
                    point.x = this.size().width - point.x;
                }
                final LineEntry traverseEntries = this.TraverseEntries(2, null, point, null);
                if (this.MouseOverEntry != null && this.MouseOverEntry != traverseEntries) {
                    this.MouseOverEntry.setMouseOverEntry(false);
                    n = 1;
                    this.MouseOverEntry = null;
                }
                if (null == traverseEntries) {
                    if (n != 0) {
                        this.repaint();
                    }
                    return true;
                }
                if (event.id == 501) {
                    traverseEntries.setMouseOverEntry(false);
                    traverseEntries.MouseDown(event, point.x, point.y);
                }
                else if (traverseEntries.IsMouseOverEnabled() && this.MouseOverEntry != traverseEntries) {
                    (this.MouseOverEntry = traverseEntries).setMouseOverEntry(true);
                    n = 1;
                }
                if (n != 0) {
                    this.repaint();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public synchronized LineEntry TraverseEntries(final int n, final Graphics graphics, final Point point, final LineEntry lineEntry) {
        Graphics graphics2 = graphics;
        LineEntry lineEntry2 = null;
        int n2 = n;
        if (n == 4) {
            n2 = 0;
        }
        if (n2 == 1 || n2 == 0) {
            this.bNeedVScrollbar = false;
        }
        if (graphics2 == null) {
            graphics2 = this.getGraphics();
            if (graphics2 == null) {
                return null;
            }
        }
        if (null == this.tree.getFirst()) {
            if (n2 == 0) {
                final int width = this.size().width;
                final int height = this.size().height;
                graphics2.drawString("Reading Documents...", this.size().width, this.size().height);
            }
            return null;
        }
        if (this.FirstVisibleEntry == null) {
            this.FirstVisibleEntry = (LineEntry)this.tree.getFirst();
        }
        else if (this.FirstVisibleEntry != this.tree.getFirst()) {
            if (this.horizontalLayout) {
                this.bNeedHScrollbar = true;
            }
            else {
                this.bNeedVScrollbar = true;
            }
        }
        LineEntry firstVisibleEntry = this.FirstVisibleEntry;
        final Rectangle rectangle = new Rectangle();
        final Point point3;
        final Point point2 = point3 = new Point(0, 4);
        point3.x += this.HorzScrollOffset;
        if (n2 == 1 || n2 == 0) {
            this.VisibleCount = 0;
            this.currentRow = 0;
        }
        while (firstVisibleEntry != null) {
            if (!firstVisibleEntry.isJustified()) {
                firstVisibleEntry.ComputeDimension(graphics2, this);
            }
            if (!this.IsLineVisible(firstVisibleEntry, point2)) {
                if (this.horizontalLayout) {
                    this.bNeedHScrollbar = true;
                }
                else {
                    this.bNeedVScrollbar = true;
                }
                this.allvisible = false;
                break;
            }
            if (this.IsLineClipped(firstVisibleEntry, point2)) {
                --this.VisibleCount;
                if (this.horizontalLayout) {
                    this.bNeedHScrollbar = true;
                }
                else {
                    this.bNeedVScrollbar = true;
                }
                this.allvisible = false;
            }
            else {
                this.allvisible = true;
            }
            if (n2 == 3 && firstVisibleEntry == lineEntry) {
                if (!this.IsLineClipped(firstVisibleEntry, point2)) {
                    lineEntry2 = lineEntry;
                    break;
                }
                break;
            }
            else {
                if (n2 == 0) {
                    if (n == 0) {
                        if (this.currentRow % 2 != 0 && this.bgImage == null && this.altrowColor != null) {
                            firstVisibleEntry.setBackgroundColor(this.altrowColor);
                        }
                        else if (this.altrowColor != null) {
                            firstVisibleEntry.setBackgroundColor(null);
                        }
                        ++this.currentRow;
                        firstVisibleEntry.Paint(graphics2, firstVisibleEntry, point2, this, this.bgImage);
                    }
                    if (firstVisibleEntry == this.SelectedEntry && this.selectionBorderStyle != 2) {
                        this.DrawSelRect(graphics2, firstVisibleEntry, false);
                    }
                }
                if (n2 == 1 || n2 == 0) {
                    ++this.VisibleCount;
                }
                if (!this.IsLineClipped(firstVisibleEntry, point2)) {
                    this.LastVisibleEntry = firstVisibleEntry;
                }
                final Dimension getLineBounds = firstVisibleEntry.GetLineBounds();
                if (n2 == 2) {
                    rectangle.x = point2.x;
                    rectangle.y = point2.y;
                    if (this.horizontalLayout) {
                        rectangle.width = getLineBounds.width;
                    }
                    else {
                        rectangle.width = this.ViewDimension.width;
                    }
                    rectangle.height = getLineBounds.height;
                    if (rectangle.inside(point.x, point.y)) {
                        lineEntry2 = firstVisibleEntry;
                        break;
                    }
                }
                if (this.horizontalLayout) {
                    final Point point4 = point2;
                    point4.x += getLineBounds.width;
                }
                else {
                    final Point point5 = point2;
                    point5.y += getLineBounds.height + this.InterlineSpacing + firstVisibleEntry.getVerticalOffset();
                }
                int n3 = TreeList.CURRENTANDUP;
                if (!firstVisibleEntry.IsCollapsed() && firstVisibleEntry.getChild() != null) {
                    n3 = TreeList.FULL;
                }
                final TreeList tree = this.tree;
                firstVisibleEntry = (LineEntry)TreeList.Traverse(firstVisibleEntry, 0, n3);
            }
        }
        if (n2 == 1) {
            this.bNeedsTraverse = false;
        }
        return lineEntry2;
    }
    
    public void paintOneLine(final LineEntry lineEntry) {
        lineEntry.Paint(this.getGraphics(), lineEntry, lineEntry.getOrigin(), this, this.bgImage);
    }
    
    public boolean IsVScrollbarNeeded() {
        return this.bNeedVScrollbar;
    }
    
    public boolean IsHScrollbarNeeded(final int n) {
        if (this.ViewDimension.width > 0 && n > this.ViewDimension.width) {
            return this.bNeedHScrollbar = true;
        }
        return this.bNeedHScrollbar = false;
    }
    
    public int GetTopLevelCount() {
        return this.tree.getTopLevelEntries();
    }
    
    public int GetExpandedCount() {
        return this.countAllExpandedChildren();
    }
    
    public int GetVisibleCount() {
        return this.VisibleCount;
    }
    
    public void UpdateCounts() {
        this.TraverseEntries(1, null, null, null);
        this.updateExpandedCount();
    }
    
    public void UpdateCounts(final boolean b, final LineEntry lineEntry) {
        this.TraverseEntries(1, null, null, null);
        if (lineEntry == null) {
            return;
        }
        if (lineEntry.getChild() == null) {
            return;
        }
        final int countExpandedChildren = this.countExpandedChildren(lineEntry);
        if (b) {
            this.ExpandedCount += countExpandedChildren;
        }
        else {
            this.ExpandedCount -= countExpandedChildren;
        }
    }
    
    public void setLineTree(final TreeList tree, final LineEntry firstVisibleEntry) {
        if (null == tree) {
            return;
        }
        this.tree = tree;
        this.MouseOverEntry = null;
        this.SelectedEntry = null;
        this.FirstVisibleEntry = firstVisibleEntry;
        this.ExpandedCount = this.countAllExpandedChildren();
        this.TraverseEntries(1, null, null, null);
    }
    
    public void updateExpandedCount() {
        this.ExpandedCount = this.countAllExpandedChildren();
    }
    
    public int countExpandedChildren(final LineEntry lineEntry) {
        int n = 0;
        LineEntry lineEntry2 = (LineEntry)lineEntry.getChild();
        if (lineEntry2 != null) {
            while (lineEntry2 != null) {
                n = ++n + this.countExpandedChildren(lineEntry2);
                final TreeList tree = this.tree;
                lineEntry2 = (LineEntry)TreeList.Traverse(lineEntry2, 0, TreeList.CURRENT);
            }
        }
        return n;
    }
    
    private int countAllExpandedChildren() {
        return this.tree.getNumberOfEntries() - this.tree.getTopLevelEntries();
    }
    
    public LineEntry getFirstVisibleEntry() {
        if (this.bNeedsTraverse) {
            this.TraverseEntries(1, null, null, null);
        }
        return this.FirstVisibleEntry;
    }
    
    public LineEntry getLastVisibleEntry() {
        if (this.bNeedsTraverse) {
            this.TraverseEntries(1, null, null, null);
        }
        return this.LastVisibleEntry;
    }
    
    public void setselectionBorderStyle(final int selectionBorderStyle) {
        this.selectionBorderStyle = selectionBorderStyle;
    }
    
    public void setSelectedEntry(final LineEntry selectedEntry) {
        if (selectedEntry != null) {
            if (this.SelectedEntry != null) {
                this.SelectedEntry.setSelected(false);
            }
            (this.SelectedEntry = selectedEntry).setSelected(true);
            this.repaint();
        }
    }
    
    public LineEntry getSelectedEntry() {
        return this.SelectedEntry;
    }
    
    public void invalidateAllLines() {
        final int full = TreeList.FULL;
        for (LineEntry lineEntry = (LineEntry)this.tree.getFirst(); lineEntry != null; lineEntry = (LineEntry)TreeList.Traverse(lineEntry, 0, full)) {
            lineEntry.setJustified(false);
            final TreeList tree = this.tree;
        }
    }
    
    public void setBackgroundImage(final Image bgImage, final int n) {
        this.bgImage = bgImage;
        this.bgNImage = new NImage(this.bgImage, this, n);
    }
    
    public void setHorizontalLayout(final boolean horizontalLayout) {
        this.horizontalLayout = horizontalLayout;
    }
}
