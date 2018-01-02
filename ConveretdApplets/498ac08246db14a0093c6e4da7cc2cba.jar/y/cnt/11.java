// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.DataInputStream;
import java.io.IOException;

public class 11 implements 7
{
    String a;
    String b;
    String c;
    15 d;
    1 e;
    boolean f;
    
    public 11(final String a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
        try {
            (this.d = new 15(this, a, 11999, 32768, false)).d("presence");
            new 8(this.d);
        }
        catch (IOException ex) {
            if (this.d != null) {
                this.d.f();
                this.d = null;
            }
        }
    }
    
    public void va() {
        1 e = null;
        synchronized (this) {
            e = this.e;
            this.f = true;
        }
        if (this.d != null) {
            if (e != null) {
                e.a();
            }
            try {
                this.d.e();
            }
            catch (IOException ex) {}
        }
    }
    
    public void r(final 12 12) {
        synchronized (this) {
            if (!this.f) {
                this.e = new 1(12);
            }
        }
        synchronized (12) {
            12.d.x(65);
            12.d.y(this.b);
            12.d.y(this.c);
            try {
                12.Ca();
            }
            catch (IOException ex) {}
        }
    }
    
    public void s(final 12 12) {
    }
    
    public void t(final 12 12, final DataInputStream dataInputStream, final int n) {
    }
    
    public void u(final String s) {
    }
}
