// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Vector;

public final class cv
{
    public Vector a;
    private Socket a;
    public fj a;
    public DataInputStream a;
    public ab a;
    public az a;
    
    public cv(az a, final String s, final int n, final int n2, final boolean b) {
        this.a = new Vector();
        this.a = a;
        this.a = new Socket(s, n);
        try {
            this.a = new fj(this.a.getInputStream(), n2);
            this.a = new DataInputStream(this.a);
            this.a = new ab(new BufferedOutputStream(this.a.getOutputStream()), n2);
            new dg(this.a);
            this.a.a();
            if (b) {
                a = (az)this;
                synchronized (this) {
                    this.a.a();
                }
                final ab a2 = this.a;
                final fj a3 = this.a;
                final al al = new al(a3.a());
                a3.a = new al(a3.a());
                a2.a(al);
            }
        }
        catch (IOException ex) {
            this.a.close();
            throw ex;
        }
    }
    
    public final bw a(final int n) {
        for (int i = 0; i < this.a.size(); ++i) {
            final bw bw;
            if ((bw = this.a.elementAt(i)).a == n) {
                return bw;
            }
        }
        return null;
    }
    
    public final void a() {
        try {
            this.a.a.close();
            this.a.a.close();
            this.a.close();
        }
        catch (IOException ex) {}
    }
}
