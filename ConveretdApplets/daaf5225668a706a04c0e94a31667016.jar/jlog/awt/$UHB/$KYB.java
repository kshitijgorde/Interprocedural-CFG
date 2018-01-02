// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Vector;

public class $KYB
{
    private Vector $MR;
    private Dimension $LYB;
    
    public void $MYB(final int n, final int n2, final int n3, final int n4) {
        if (n3 < 1 || n4 < 1) {
            return;
        }
        Rectangle union = new Rectangle(n, n2, n3, n4);
        synchronized (this.$MR) {
            final boolean empty = this.isEmpty();
            int i = 0;
            while (i < this.$MR.size()) {
                final Rectangle rectangle = this.$MR.elementAt(i);
                if (union.intersects(rectangle)) {
                    this.$MR.removeElementAt(i);
                    union = union.union(rectangle);
                    i = 0;
                }
                else {
                    ++i;
                }
            }
            final Dimension $lyb = this.$LYB;
            if ($lyb != null && union.width < $lyb.width && union.height < $lyb.height) {
                int j = 0;
                while (j < this.$MR.size()) {
                    final Rectangle union2 = this.$MR.elementAt(j).union(union);
                    if (union2.width <= $lyb.width && union2.height <= $lyb.height) {
                        this.$MR.removeElementAt(j);
                        union = union2;
                        j = 0;
                    }
                    else {
                        ++j;
                    }
                }
            }
            this.$MR.addElement(union);
            if (empty) {
                this.$MR.notify();
            }
        }
        // monitorexit(this.$MR)
    }
    
    public void $MYB(final Rectangle rectangle) {
        if (rectangle == null) {
            return;
        }
        this.$MYB(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Rectangle $PYB() throws InterruptedException {
        return this.$PYB(Integer.MAX_VALUE, null);
    }
    
    public Rectangle $PYB(final int n, final Rectangle rectangle) throws InterruptedException {
        synchronized (this.$MR) {
            while (this.isEmpty()) {
                if (n > 0) {
                    this.$MR.wait(n);
                }
                if (n != Integer.MAX_VALUE && this.isEmpty()) {
                    throw new InterruptedException("timeout");
                }
            }
            int n2 = 0;
            if (rectangle != null) {
                for (int i = 0; i < this.$MR.size(); ++i) {
                    if (((Rectangle)this.$MR.elementAt(n2)).intersects(rectangle)) {
                        n2 = i;
                        break;
                    }
                }
            }
            final Rectangle rectangle2 = this.$MR.elementAt(n2);
            this.$MR.removeElementAt(n2);
            // monitorexit(this.$MR)
            return rectangle2;
        }
    }
    
    public Dimension $SYB() {
        return new Dimension(this.$LYB.width, this.$LYB.height);
    }
    
    public void $TYB(final Dimension dimension) {
        this.$LYB.width = dimension.width;
        this.$LYB.height = dimension.height;
    }
    
    public $KYB() {
        this.$MR = new Vector();
        this.$LYB = new Dimension(160, 120);
    }
    
    public Rectangle flush() {
        Rectangle union = null;
        synchronized (this.$MR) {
            final Enumeration<Rectangle> elements = this.$MR.elements();
            if (elements.hasMoreElements()) {
                union = elements.nextElement();
            }
            while (elements.hasMoreElements()) {
                union = union.union(elements.nextElement());
            }
            this.$MR.removeAllElements();
        }
        // monitorexit(this.$MR)
        return union;
    }
    
    public boolean isEmpty() {
        return this.$MR.isEmpty();
    }
}
