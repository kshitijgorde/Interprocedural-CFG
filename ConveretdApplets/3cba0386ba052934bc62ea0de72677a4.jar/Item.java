import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Item
{
    public String[] text;
    public int nLines;
    public URL link;
    public String target;
    public Item next;
    public Item prev;
    public Item down;
    public Item downLast;
    public Item up;
    public int x;
    public int y;
    public int dx1;
    public int dx2;
    public int dy;
    public int baseline;
    public int[] textlen;
    public int level;
    public YTrace trace;
    public int iTrace;
    public Image[] ShapeImage;
    public Image[] TextImage;
    public int advance;
    public boolean expanded;
    public boolean InitialExpanded;
    public boolean ScrollType;
    public boolean NoIcon;
    
    public Item() {
        this.expanded = false;
        this.InitialExpanded = false;
        this.ScrollType = false;
        this.NoIcon = false;
        this.ShapeImage = new Image[4];
        this.TextImage = new Image[4];
        this.text = new String[4];
        this.textlen = new int[4];
        for (int i = 0; i < 4; ++i) {
            this.ShapeImage[i] = null;
            this.TextImage[i] = null;
            this.text[i] = null;
        }
    }
    
    public void reset() {
        if (this.down != null) {
            this.down.reset();
        }
        if (this.next != null) {
            this.next.reset();
        }
        this.expanded = this.InitialExpanded;
    }
    
    public void expandRecursive() {
        if (this.down != null) {
            this.down.expandRecursive();
        }
        this.expanded = true;
        if (this.next != null) {
            this.next.expandRecursive();
        }
    }
    
    public void collapseRecursive() {
        if (this.downLast != null) {
            this.downLast.collapseRecursive();
        }
        this.expanded = false;
        if (this.prev != null) {
            this.prev.collapseRecursive();
        }
    }
    
    public void CalculateSize(final Look_n_Feel[] array, final Component component) {
        FontMetrics fontMetrics = null;
        if (this.down != null) {
            this.down.CalculateSize(array, component);
        }
        if (this.next != null) {
            this.next.CalculateSize(array, component);
        }
        if (this.level > 0) {
            fontMetrics = component.getFontMetrics(array[this.level].font);
            array[this.level].texthi = fontMetrics.getHeight();
        }
        int height;
        if (this.TextImage[0] == null) {
            height = array[this.level].texthi * this.nLines;
        }
        else {
            height = this.TextImage[0].getHeight(component);
            if (this.TextImage[1] == null) {
                this.TextImage[1] = this.TextImage[0];
            }
            if (this.TextImage[2] == null) {
                this.TextImage[2] = this.TextImage[0];
            }
            if (this.TextImage[3] == null) {
                this.TextImage[3] = this.TextImage[1];
            }
        }
        if (this.ShapeImage[0] != null) {
            if (this.ShapeImage[1] == null) {
                this.ShapeImage[1] = this.ShapeImage[0];
            }
            if (this.ShapeImage[2] == null) {
                this.ShapeImage[2] = this.ShapeImage[1];
                if (this.down != null) {
                    this.ShapeImage[1] = this.ShapeImage[0];
                }
            }
            if (this.ShapeImage[3] == null) {
                this.ShapeImage[3] = this.ShapeImage[2];
            }
        }
        if (this.ShapeImage[0] == null || this.ShapeImage[0].getHeight(component) <= height) {
            if (height > 0) {
                this.dy = height;
            }
            else {
                this.dy = array[this.level].texthi;
            }
        }
        else {
            this.dy = this.ShapeImage[0].getHeight(component);
        }
        if (this.NoIcon) {
            this.dx1 = 0;
        }
        else if (this.ShapeImage[0] != null) {
            this.dx1 = this.ShapeImage[0].getWidth(component);
        }
        else if (this.TextImage[0] == null && this.nLines == 0 && this.level > 0) {
            this.dx1 = 0;
        }
        else if (this.down != null && array[this.level].ShapeType >= 0) {
            this.dx1 = array[this.level].texthi;
        }
        else if (this.down == null && array[this.level].IconType >= 0) {
            this.dx1 = array[this.level].texthi;
        }
        else {
            this.dx1 = 0;
        }
        if (this.nLines == 0) {
            this.baseline = this.dy - height;
        }
        else if (this.nLines == 1) {
            this.baseline = this.dy - height + fontMetrics.getAscent();
        }
        else {
            this.baseline = fontMetrics.getAscent();
        }
        if (this.TextImage[0] == null) {
            this.dx2 = 0;
            for (int i = 0; i < this.nLines; ++i) {
                this.textlen[i] = fontMetrics.stringWidth(this.text[i]);
                if (this.textlen[i] > this.dx2) {
                    this.dx2 = this.textlen[i];
                }
            }
            return;
        }
        this.dx2 = this.TextImage[0].getWidth(component);
    }
    
    public void align(final Look_n_Feel[] array, final int n, final boolean b, final int n2) {
        int dx1 = 0;
        int dx2 = 0;
        for (Item next = this; next != null; next = next.next) {
            if (next.down != null) {
                next.down.align(array, n, b, n2);
            }
            if (next.dx1 > dx1) {
                dx1 = next.dx1;
            }
            if (next.dx2 > dx2) {
                dx2 = next.dx2;
            }
        }
        for (Item next2 = this; next2 != null; next2 = next2.next) {
            if (next2.dx2 != 0 && (!b || this.level != 1) && !next2.NoIcon) {
                next2.dx1 = dx1;
            }
            if (n == 1 && (!b || this.level != 1)) {
                next2.dx2 = dx2 + dx1 - next2.dx1;
            }
            if ((!b || next2.level != 1) && next2.down != null && !this.ScrollType) {
                next2.advance = n2 + next2.dx1 - next2.down.dx1;
            }
        }
    }
}
