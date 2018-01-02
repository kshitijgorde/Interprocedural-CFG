// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;

public class b
{
    public Image new;
    public int[] do;
    public int a;
    public int for;
    public int if;
    private a int;
    
    public b(final int a, final int for1) {
        this.a = a;
        this.for = for1;
        this.if = this.a * this.for;
        this.do = null;
        this.new = null;
        this.int = null;
        try {
            this.do = new int[this.a * this.for];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.do = null;
        }
        if (this.do != null) {
            this.int = new a(this.a, this.for, this.do);
        }
    }
    
    public void a(final int n, final int n2, final int n3) {
        final int n4 = 0xFF000000 | n << 16 | n2 << 8 | n3;
        for (int i = 0; i < this.if; ++i) {
            this.do[i] = n4;
        }
    }
    
    public void a() {
        if (this.new == null) {
            try {
                if (this.int != null) {
                    this.new = Toolkit.getDefaultToolkit().createImage(this.int);
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.new = null;
            }
        }
        else if (this.int != null) {
            this.int.a();
        }
    }
    
    public void if() {
        if (this.new != null) {
            this.new.flush();
        }
        this.new = null;
        this.do = null;
        this.int = null;
    }
}
