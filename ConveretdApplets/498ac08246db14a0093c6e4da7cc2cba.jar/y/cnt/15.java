// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Vector;

public class 15 implements 2
{
    Vector b;
    public Socket d;
    6 e;
    DataInputStream f;
    14 g;
    7 h;
    9 i;
    int j;
    
    public 15(final 7 h, final String s, final int n, final int j, final boolean b) throws IOException {
        this.b = new Vector();
        this.h = h;
        this.j = j;
        this.d = new Socket(s, n);
        try {
            this.e = new 6(this.d.getInputStream(), j);
            this.f = new DataInputStream(this.e);
            this.g = new 14(new BufferedOutputStream(this.d.getOutputStream()), j);
            this.i = new 9(this.g);
            this.e.j();
            if (b) {
                synchronized (this) {
                    this.g.Ja();
                }
                this.g.Fa(this.e.i());
            }
        }
        catch (IOException ex) {
            this.d.close();
            throw ex;
        }
    }
    
    12 Za(final int n) {
        for (int i = 0; i < this.b.size(); ++i) {
            final 12 12 = this.b.elementAt(i);
            if (12.a == n) {
                return 12;
            }
        }
        return null;
    }
    
    public void c() throws IOException {
        final int k = this.e.k();
        if (k == 111) {
            final 12 12 = new 12(this.e.l(), this.e.m(), this.i, this.g, this);
            this.b.addElement(12);
            this.h.r(12);
            return;
        }
        if (k == 102) {
            this.h.u(this.e.m());
            return;
        }
        if (k == 99) {
            final 12 za = this.Za(this.e.l());
            this.h.s(za);
            this.b.removeElement(za);
            return;
        }
        if (k == 100) {
            this.h.t(this.Za(this.e.l()), this.f, this.e.o());
            this.e.q();
            return;
        }
        if (k == 101) {
            this.h.t(this.Za(this.e.l()), this.f, this.e.p());
            this.e.q();
            return;
        }
        throw new IOException("Illegal connection proxy command: " + k);
    }
    
    public void d(final String s) throws IOException {
        this.g.La(s);
    }
    
    public void e() throws IOException {
        this.g.Ya();
    }
    
    public void f() {
        try {
            this.g.b.close();
            this.e.e.close();
            this.d.close();
        }
        catch (IOException ex) {}
    }
}
