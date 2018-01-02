// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;

public class d
{
    private Vector for;
    private int a;
    private int int;
    private int if;
    private int do;
    
    public d() {
        this.for = new Vector();
    }
    
    public void a(final f f) {
        this.for.addElement(f);
    }
    
    public void if() {
        if (this.for != null) {
            final Enumeration<f> elements = this.for.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().do();
            }
            this.for.removeAllElements();
            this.for = null;
        }
    }
    
    public void a(final int a, final int int1, final int if1, final int do1) {
        this.a = a;
        this.int = int1;
        this.if = if1;
        this.do = do1;
    }
    
    public void a(final Rectangle rectangle) {
        this.a = rectangle.x;
        this.int = rectangle.y;
        this.if = rectangle.width;
        this.do = rectangle.height;
    }
    
    public void a(final boolean visible) {
        final Enumeration<f> elements = this.for.elements();
        while (elements.hasMoreElements()) {
            final f f = elements.nextElement();
            f.setVisible(visible);
            f.repaint();
        }
        if (visible) {
            this.a();
        }
    }
    
    public void a() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final Enumeration<f> elements = this.for.elements();
        while (elements.hasMoreElements()) {
            final Dimension preferredSize = elements.nextElement().getPreferredSize();
            n += preferredSize.width;
            n2 += preferredSize.height;
            ++n3;
        }
        if (n <= this.if) {
            final int n4 = (this.if - n) / n3;
            int n5 = this.a + n4 / 2;
            final Enumeration<f> elements2 = this.for.elements();
            while (elements2.hasMoreElements()) {
                final f f = elements2.nextElement();
                final Dimension preferredSize2 = f.getPreferredSize();
                f.setBounds(n5, this.int + (this.do - preferredSize2.height) / 2, preferredSize2.width, preferredSize2.height);
                n5 += f.getSize().width + n4;
            }
        }
        else if (n2 <= this.do) {
            final int n6 = (this.do - n2) / n3;
            int n7 = this.int + n6 / 2;
            final Enumeration<f> elements3 = this.for.elements();
            while (elements3.hasMoreElements()) {
                final f f2 = elements3.nextElement();
                final Dimension preferredSize3 = f2.getPreferredSize();
                f2.setBounds(this.a + (this.if - preferredSize3.width) / 2, n7, preferredSize3.width, preferredSize3.height);
                n7 += preferredSize3.height + n6;
            }
        }
    }
}
