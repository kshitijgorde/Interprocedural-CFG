import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dr implements ak
{
    public ds a;
    private int b;
    
    public dr() {
        this.a = new ds();
        this.b = 0;
    }
    
    public dr(final int b) {
        this();
        this.b = b;
    }
    
    public final int a(final do do1, final aj aj, final Vector vector, final bf bf, final bb bb, final j j) {
        if (ak.a.l()) {
            ak.a.j("HttpMsgReader.read: hdr=" + do1.toString() + " : " + this.hashCode());
        }
        try {
            if (do1.a() == 2) {
                if (ak.a.l()) {
                    ak.a.j("going to read from push stream " + aj + " requestStrings " + vector);
                }
                this.a.a(aj, -1, vector, bf, bb, this, j);
                return 2;
            }
            byte[] array;
            if (do1.d()) {
                array = aj.a(do1.b());
            }
            else {
                array = aj.b();
            }
            final du du = new du(do1, array);
            if (du.g && ak.a.i()) {
                ak.a.g("decompressed msg from " + du.e + " to " + du.d + " bytes");
            }
            boolean b;
            if (do1.a() == 0) {
                if (ak.a.l()) {
                    ak.a.j("counter " + this.b());
                }
                if (do1.f() == 200) {
                    b = bf.a(du, this.b(), bb);
                }
                else {
                    bf.c(new ct(do1.g(), do1.f()), this.b() - Integer.MAX_VALUE, null);
                    b = true;
                }
            }
            else if (do1.a() == 3) {
                b = bf.a(du, this.b(), bb);
            }
            else {
                final int c = do1.c();
                aj.a(new ByteArrayInputStream(du.b()));
                final int a = this.a.a(aj, c, vector, bf, bb, this, j);
                if (a < c) {
                    b = false;
                    dj.a(bf, j, new am("Not enough responses"), a);
                }
                else {
                    b = true;
                }
            }
            this.a();
            return b ? 0 : 1;
        }
        catch (Exception ex) {
            aj.d();
            if (ak.a.i()) {
                ak.a.e("exiting read with " + ex, ex);
            }
            dj.a(bf, j, ex, this.b());
            return 1;
        }
    }
    
    public final void a() {
        synchronized (this) {
            ++this.b;
        }
    }
    
    public final int b() {
        synchronized (this) {
            return this.b;
        }
    }
}
