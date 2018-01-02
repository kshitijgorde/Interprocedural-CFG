import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    private n[] do;
    private Component try;
    private static int new;
    private Graphics if;
    private int for;
    private int a;
    private int int;
    
    public f(final Component try1) {
        this.try = try1;
    }
    
    static void a(final int new1) {
        f.new = new1;
    }
    
    public void a(final n[] do1) {
        this.do = do1;
    }
    
    public void if() {
        this.int = 0;
        this.a = 0;
        for (int i = 0; i < this.do.length; ++i) {
            this.do[i].a(new Point(0, this.int));
            this.int += this.do[i].for();
            if (this.a < this.do[i].if()) {
                this.a = this.do[i].if();
            }
            if (i < this.do.length - 1) {
                this.int += f.new;
            }
        }
    }
    
    public void a(final int for1, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        this.for = for1;
        for (int i = 0; i < this.do.length; ++i) {
            final n n6 = this.do[i];
            final int n7 = n6.do().y - n;
            if (n7 + n6.for() + a() > 0 && n7 < n3) {
                if (n6.if) {
                    n6.a(this.for, n2, n4, n5);
                    n6.if = false;
                }
                else if (b) {
                    n6.a(this.for, n2, n4, n5);
                }
            }
            else {
                n6.if = true;
            }
        }
    }
    
    public void a(final int n, final int n2, final Dimension dimension) {
        if (this.if == null) {
            this.if = this.try.getGraphics();
        }
        for (int i = 0; i < this.do.length; ++i) {
            if (!this.do[i].if) {
                this.do[i].a(this.if, n, n2, dimension);
            }
        }
    }
    
    public void a(final n n, final int n2, final int n3, final Dimension dimension, final int n4, final int n5) {
        if (!n.if) {
            n.a(this.for, dimension.width, n4, n5);
            n.a(this.if, n2, n3, dimension);
        }
    }
    
    public n a(final int n, final int n2) {
        n n3 = null;
        for (int i = 0; i < this.do.length; ++i) {
            final Point do1 = this.do[i].do();
            if (new Rectangle(do1.x, do1.y, this.do[i].if(), this.do[i].for()).contains(n, n2)) {
                n3 = this.do[i];
                break;
            }
        }
        return n3;
    }
    
    public Image if(final int n, final int n2) {
        Image image = null;
        if (this.try != null) {
            image = this.try.createImage(n, n2);
        }
        return image;
    }
    
    public Dimension do() {
        return new Dimension(this.a, this.int);
    }
    
    public static int a() {
        return f.new;
    }
}
