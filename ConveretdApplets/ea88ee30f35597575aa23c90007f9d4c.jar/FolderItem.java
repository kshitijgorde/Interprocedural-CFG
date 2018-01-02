import java.awt.Event;
import java.net.URL;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class FolderItem extends Item
{
    int TABSTOP;
    int INDENT;
    public int m_spacing;
    Parser m_treejit;
    Rectangle m_rect;
    boolean m_expanded;
    public Vector m_list;
    public FolderItem m_parent;
    
    public Rectangle getTitleRect() {
        return super.getRect();
    }
    
    public void initalise(final Applet applet) {
        super.initalise(applet);
        if (super.m_icon != null) {
            this.INDENT = super.m_icon.getWidth(applet) / 2;
            if (this.INDENT < 1) {
                this.INDENT = 4;
            }
            if (this.INDENT > 15) {
                this.INDENT = 15;
            }
            this.TABSTOP = this.INDENT + 10;
        }
        if (super.m_font != null) {
            applet.getFontMetrics(super.m_font);
        }
        else {
            applet.getFontMetrics(applet.getFont());
        }
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().initalise(applet);
        }
    }
    
    private final void drawLine(final Graphics graphics, int n, int n2, final int n3, final int n4) {
        while (n < n3 || n2 < n4) {
            graphics.drawLine(n, n2, n, n2);
            if (n != n3) {
                n += 2;
            }
            if (n2 != n4) {
                n2 += 2;
            }
        }
    }
    
    public Item findItem(final URL url, final boolean b) {
        if (url == null) {
            return null;
        }
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item = elements.nextElement();
            if (url.sameFile(item.m_doc)) {
                if (b) {
                    this.expand(true);
                }
                return item;
            }
            final Item item2;
            if (item instanceof FolderItem && (item2 = ((FolderItem)item).findItem(url, b)) != null) {
                if (b) {
                    this.expand(true);
                }
                return item2;
            }
        }
        return null;
    }
    
    public void draw(final Graphics graphics, final TreeApp treeApp, final int n) {
        super.draw(graphics, treeApp, n);
        if (!this.m_expanded || (n & 0x1) == 0x0) {
            return;
        }
        final int n2 = this.INDENT + this.m_rect.x;
        int n3 = super.getRect().height + this.m_rect.y;
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item = elements.nextElement();
            item.draw(graphics, treeApp, n);
            graphics.setColor(treeApp.m_detailColour);
            if (item instanceof FolderItem) {
                final FolderItem folderItem = (FolderItem)item;
                final int n4 = folderItem.getRect().y + folderItem.getTitleRect().height / 2 - 4;
                final int n5 = n2 - 4;
                graphics.drawRect(n5, n4, 8, 8);
                graphics.setColor(treeApp.m_plusColour);
                graphics.drawLine(n5 + 2, n4 + 4, n5 + 6, n4 + 4);
                if (!folderItem.isExpanded()) {
                    graphics.drawLine(n5 + 4, n4 + 2, n5 + 4, n4 + 6);
                }
                graphics.setColor(treeApp.m_detailColour);
                this.drawLine(graphics, n5 + 8, n4 + 4, n2 + (this.TABSTOP - this.INDENT), n4 + 4);
                this.drawLine(graphics, n2, n3, n2, n4);
                n3 = n4 + 8;
            }
            else {
                final int n6 = item.getRect().y + item.getRect().height / 2;
                this.drawLine(graphics, n2, n3, n2, n6);
                this.drawLine(graphics, n2, n6, n2 + (this.TABSTOP - this.INDENT), n6);
                n3 = n6;
            }
        }
    }
    
    public boolean replaceItem(final Item item, final Item item2) {
        final int index;
        if ((index = this.m_list.indexOf(item)) != -1) {
            this.m_list.setElementAt(item2, index);
            if (item2 instanceof FolderItem) {
                ((FolderItem)item2).m_parent = this;
            }
            return true;
        }
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item3 = elements.nextElement();
            if (item3 instanceof FolderItem && ((FolderItem)item3).replaceItem(item, item2)) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean isExpanded() {
        return this.m_expanded;
    }
    
    public final void expand(final boolean expanded) {
        this.m_expanded = expanded;
    }
    
    public Item getLastItem(final boolean b) {
        if ((!this.isExpanded() && !b) || this.isEmpty()) {
            return this;
        }
        if (b) {
            this.expand(b);
        }
        final Item item = this.m_list.lastElement();
        if (item instanceof FolderItem) {
            return ((FolderItem)item).getLastItem(b);
        }
        return item;
    }
    
    public Item getFirstItem(final boolean b) {
        if ((!this.isExpanded() && !b) || this.isEmpty()) {
            return this;
        }
        if (b) {
            this.expand(b);
        }
        return this.m_list.firstElement();
    }
    
    protected Item getNext(final int n, final boolean b) {
        if (b) {
            this.expand(b);
        }
        Item item;
        try {
            item = this.m_list.elementAt(n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            item = null;
        }
        if (item instanceof FolderItem) {
            final Item next = ((FolderItem)item).getNext(item, b);
            if (next != item) {
                return next;
            }
        }
        if (this.m_list.size() > n + 1) {
            Item item2;
            try {
                item2 = this.m_list.elementAt(n + 1);
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                item2 = null;
            }
            return item2;
        }
        return null;
    }
    
    public Item getNext(final Item item, final boolean b) {
        if (item == this) {
            return this.getFirstItem(b);
        }
        if ((!this.isExpanded() && !b) || this.isEmpty()) {
            return null;
        }
        final int index;
        if ((index = this.m_list.indexOf(item)) != -1) {
            Item next = this.getNext(index, b);
            if (next == null) {
                next = item;
            }
            return next;
        }
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item2 = elements.nextElement();
            if (item2 instanceof FolderItem) {
                final Item next2 = ((FolderItem)item2).getNext(item, b);
                if (next2 != null && b) {
                    this.expand(b);
                }
                if (next2 == item) {
                    if (elements.hasMoreElements()) {
                        return elements.nextElement();
                    }
                    return item;
                }
                else {
                    if (next2 != null) {
                        return next2;
                    }
                    continue;
                }
            }
        }
        return null;
    }
    
    public final Enumeration elements() {
        return this.m_list.elements();
    }
    
    public Item hitTest(final int n, final int n2) {
        if (!this.m_rect.inside(n, n2)) {
            return null;
        }
        if (super.hitTest(n, n2) != null) {
            return this;
        }
        final Rectangle rectangle = new Rectangle(10, 10);
        rectangle.x = this.m_rect.x + this.INDENT - 4;
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item = elements.nextElement();
            final Item hitTest;
            if ((hitTest = item.hitTest(n, n2)) != null) {
                return hitTest;
            }
            if (!(item instanceof FolderItem)) {
                continue;
            }
            final FolderItem folderItem = (FolderItem)item;
            folderItem.getTitleRect();
            rectangle.y = folderItem.getRect().y + folderItem.getTitleRect().height / 2 - 4;
            if (rectangle.inside(n, n2)) {
                return item;
            }
        }
        return null;
    }
    
    public final void addElement(final Object o) {
        this.m_list.addElement(o);
        if (o instanceof FolderItem) {
            ((FolderItem)o).m_parent = this;
        }
    }
    
    public boolean activate(final int n, final Event event, final Applet applet) {
        if (n == 1) {
            if (this.m_treejit != null) {
                this.m_treejit.start();
                this.m_treejit = null;
            }
            this.expand(!this.m_expanded);
            return true;
        }
        if (event != null) {
            if (!super.getRect().inside(event.x, event.y)) {
                if (this.m_treejit != null) {
                    this.m_treejit.start();
                    this.m_treejit = null;
                }
                this.expand(!this.m_expanded);
                return true;
            }
            super.activate(n, event, applet);
        }
        else {
            super.activate(n, event, applet);
        }
        return false;
    }
    
    protected Item getPrev(final int n, final boolean b) {
        if (n <= 0) {
            return this;
        }
        if (n - 1 >= 0) {
            Item lastItem;
            try {
                lastItem = this.m_list.elementAt(n - 1);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                lastItem = null;
            }
            if (lastItem != null && lastItem instanceof FolderItem) {
                lastItem = ((FolderItem)lastItem).getLastItem(b);
            }
            return lastItem;
        }
        return null;
    }
    
    public Item getPrev(final Item item, final boolean b) {
        if (item == this) {
            return item;
        }
        if ((!this.isExpanded() && !b) || this.isEmpty()) {
            return null;
        }
        final int index = this.m_list.indexOf(item);
        if (index != -1) {
            return this.getPrev(index, b);
        }
        final Enumeration elements = this.elements();
        while (elements.hasMoreElements()) {
            final Item item2 = elements.nextElement();
            if (item2 instanceof FolderItem) {
                final FolderItem folderItem = (FolderItem)item2;
                final Item prev = folderItem.getPrev(item, b);
                if (prev == item) {
                    return this.getPrev(this.m_list.indexOf(folderItem), b);
                }
                if (prev != null) {
                    if (b) {
                        this.expand(b);
                    }
                    return prev;
                }
                continue;
            }
        }
        return null;
    }
    
    public void copy(final FolderItem folderItem) {
        super.copy((Item)folderItem);
        this.m_expanded = folderItem.isExpanded();
        this.m_rect = new Rectangle();
        this.m_list = new Vector();
        final Enumeration elements = folderItem.elements();
        this.m_list.removeAllElements();
        while (elements.hasMoreElements()) {
            this.m_list.addElement(elements.nextElement());
        }
        this.m_list.trimToSize();
    }
    
    public final boolean isEmpty() {
        return this.m_list.isEmpty();
    }
    
    public Rectangle layout(int x, final int y) {
        final Rectangle layout = super.layout(x, y);
        this.m_rect.x = x;
        this.m_rect.y = y;
        int width = layout.width;
        int height = layout.height;
        if (this.m_expanded && this.m_list != null) {
            x += this.TABSTOP;
            final Enumeration elements = this.elements();
            while (elements.hasMoreElements()) {
                final Rectangle layout2 = elements.nextElement().layout(x, y + height);
                if (layout2.width > width) {
                    width = layout2.width;
                }
                height += layout2.height;
            }
            width += this.TABSTOP;
        }
        this.m_rect.width = width;
        this.m_rect.height = height + this.m_spacing;
        return this.m_rect;
    }
    
    public FolderItem(final String s) {
        super(s);
        this.TABSTOP = 20;
        this.INDENT = 4;
        this.m_expanded = false;
        this.m_rect = new Rectangle();
        this.m_list = new Vector();
    }
    
    public FolderItem(final Item item) {
        super(item);
        this.TABSTOP = 20;
        this.INDENT = 4;
        this.m_expanded = false;
        this.m_rect = new Rectangle();
        this.m_list = new Vector();
    }
    
    public FolderItem(final FolderItem folderItem) {
        super(folderItem.getTitle());
        this.TABSTOP = 20;
        this.INDENT = 4;
        this.copy(folderItem);
    }
    
    public Rectangle getRect() {
        return this.m_rect;
    }
}
