// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.util.Vector;

public class b4
{
    private String a;
    private String b;
    private Vector c;
    private int d;
    private int e;
    
    public b4(final String a, final String b) {
        this.a = null;
        this.b = null;
        this.c = new Vector();
        this.d = 0;
        this.e = 0;
        try {
            this.a = a;
            this.b = b;
            this.e();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final int a() {
        return this.e;
    }
    
    public final boolean b() {
        return this.d < this.e;
    }
    
    public final String c() {
        if (this.d < this.e) {
            return this.c.elementAt(this.d++);
        }
        return null;
    }
    
    private final void e() {
        final int n = 0;
        int n2 = 1;
        int i = this.a.indexOf(this.b, n);
        if (i >= 0) {
            this.c.addElement(this.a.substring(0, i));
            while (i >= 0) {
                ++n2;
                final int n3 = i + this.b.length();
                if (n3 < this.a.length()) {
                    i = this.a.indexOf(this.b, n3);
                    if (i < 0) {
                        this.c.addElement(this.a.substring(n3));
                    }
                    else {
                        this.c.addElement(this.a.substring(n3, i));
                    }
                }
                else {
                    this.c.addElement(this.a.substring(n3));
                    i = -1;
                }
            }
        }
        else {
            this.c.addElement(this.a);
        }
        this.e = this.c.size();
        this.d = 0;
    }
    
    public final void d() {
        try {
            if (this.c != null) {
                this.c.removeAllElements();
                this.c = null;
            }
        }
        catch (Exception ex) {}
    }
}
