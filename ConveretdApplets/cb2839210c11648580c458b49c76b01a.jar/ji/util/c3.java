// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.image.c2;
import java.awt.image.ColorModel;

public class c3
{
    public String a;
    public int b;
    public int c;
    public ColorModel d;
    public c2 e;
    
    public c3(final int b, final int c, final String a, final c2 e) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.b = b;
        this.c = c;
        this.a = a;
        this.e = e;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiGrabContext - ").append(this.b).append(" * ").append(this.c)));
    }
    
    public final void a() {
        try {
            if (this.e != null) {
                this.e.i();
            }
        }
        catch (Exception ex) {}
        this.e = null;
        this.d = null;
        this.a = null;
    }
}
