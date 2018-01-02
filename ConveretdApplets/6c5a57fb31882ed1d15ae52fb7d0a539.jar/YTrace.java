import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class YTrace
{
    private Vector items;
    private int x1;
    private int x2;
    
    public YTrace() {
        this.items = new Vector(128, 128);
        this.x1 = 9999;
        this.x2 = 0;
    }
    
    public void clear() {
        this.items.removeAllElements();
        this.x1 = 9999;
        this.x2 = 0;
    }
    
    public Item at(final int n) {
        return this.items.elementAt(n);
    }
    
    public int getItemCount() {
        return this.items.size();
    }
    
    public void align() {
        final int size = this.items.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            final Item item = this.items.elementAt(i);
            if (item.x + item.dx1 + item.dx2 > n) {
                n = item.x + item.dx1 + item.dx2;
            }
        }
        for (int j = 0; j < size; ++j) {
            final Item item2 = this.items.elementAt(j);
            item2.dx2 = n - item2.x - item2.dx1;
        }
    }
    
    public Item at(final int n, final int i, final boolean b) {
        final int size = this.items.size();
        if (size <= 0) {
            return null;
        }
        if (b && (n < this.x1 || n > this.x2)) {
            return null;
        }
        final Item item = this.items.elementAt(0);
        final Item item2 = this.items.elementAt(size - 1);
        if (i < item.y || i >= item2.y + item2.dy) {
            return null;
        }
        int n2 = size * (i - item.y) / (item2.y + item2.dy - item.y);
        if (n2 >= size) {
            n2 = size - 1;
        }
        Item item3 = this.items.elementAt(n2);
        if (i < item3.y) {
            while (i < item3.y) {
                --n2;
                item3 = this.items.elementAt(n2);
            }
        }
        else {
            while (i >= item3.y + item3.dy) {
                ++n2;
                item3 = this.items.elementAt(n2);
            }
        }
        if (!b || (n >= item3.x && n < item3.x + item3.dx1 + item3.dx2 && i >= item3.y && i < item3.y + item3.dy)) {
            return item3;
        }
        return null;
    }
    
    public void add(final Item item) {
        this.items.addElement(item);
        item.trace = this;
        item.iTrace = this.items.size() - 1;
        if (item.x < this.x1) {
            this.x1 = item.x;
        }
        if (item.x + item.dx1 + item.dx2 > this.x2) {
            this.x2 = item.x + item.dx1 + item.dx2;
        }
    }
}
