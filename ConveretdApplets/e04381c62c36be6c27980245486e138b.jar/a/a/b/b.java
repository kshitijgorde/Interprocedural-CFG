// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;

public class b
{
    public Image if;
    public int[] a;
    public int new;
    public int do;
    public int int;
    private a for;
    
    public b(final int new1, final int do1) {
        this.new = new1;
        this.do = do1;
        this.int = this.new * this.do;
        this.a = null;
        this.if = null;
        this.for = null;
        try {
            this.a = new int[this.new * this.do];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.a = null;
        }
        if (this.a != null) {
            this.for = new a(this.new, this.do, this.a);
        }
    }
    
    public void a(final int n, final int n2, final int n3) {
        final int n4 = 0xFF000000 | n << 16 | n2 << 8 | n3;
        for (int i = 0; i < this.int; ++i) {
            this.a[i] = n4;
        }
    }
    
    public void a() {
        if (this.if == null) {
            try {
                if (this.for != null) {
                    this.if = Toolkit.getDefaultToolkit().createImage(this.for);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.if = null;
            }
        }
        else if (this.for != null) {
            this.for.a();
        }
    }
    
    public void if() {
        if (this.if != null) {
            this.if.flush();
        }
        this.if = null;
        this.a = null;
        this.for = null;
    }
}
