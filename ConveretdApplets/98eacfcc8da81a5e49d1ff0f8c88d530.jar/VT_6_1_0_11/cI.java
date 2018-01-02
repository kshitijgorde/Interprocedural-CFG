// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;
import java.io.InputStream;
import java.util.List;

public final class cI
{
    private List a;
    private ck b;
    private int c;
    private int d;
    private InputStream e;
    private boolean f;
    
    public cI() {
        this.a = new Vector();
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = false;
    }
    
    public cI(final InputStream e) {
        this.a = new Vector();
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = e;
        this.f = false;
    }
    
    public final void a() {
        final byte[] b;
        if ((b = this.c().b()) == null) {
            throw new IOException("Unable to read Speex Header");
        }
        if (b.length != 80) {
            throw new IOException("Speex header is wrong size");
        }
        if (b[0] != 83 || b[1] != 112 || b[2] != 101 || b[3] != 101 || b[4] != 120 || b[5] != 32 || b[6] != 32 || b[7] != 32) {
            throw new IOException("Speex header doesn't begin with 'Speex   '");
        }
        if (ck.a(b, 32) != 80) {
            throw new IOException("Speex header is wrong size");
        }
        this.c().b();
    }
    
    public final synchronized void b() {
        while (this.c > 0) {
            this.a.remove(0);
            --this.c;
        }
    }
    
    private synchronized void a(final ck ck) {
        if (this.f) {
            throw new IllegalStateException("The Packer has been closed.");
        }
        if (ck == null) {
            throw new IllegalArgumentException("Cannot Add a null Page.");
        }
        this.a.add(ck);
        if (ck.c() == 4L) {
            this.f = true;
        }
    }
    
    public final synchronized aY c() {
        if (this.b == null || this.d >= this.b.e()) {
            this.b = this.e();
        }
        if (this.b != null) {
            if (this.b.b() && this.b.e() == 0) {
                throw new EOFException("The UnPacker has returned all Packets");
            }
            final aY a = this.b.a(this.d++);
            if (this.e != null) {
                a.a();
            }
            return a;
        }
        else {
            if (this.f) {
                throw new EOFException("The UnPacker has returned all Packets");
            }
            return null;
        }
    }
    
    public final boolean d() {
        if (this.c < this.a.size() && (this.b == null || this.d >= this.b.e())) {
            this.b = this.e();
        }
        if (this.b != null && this.d < this.b.e()) {
            final aY a;
            return (a = this.b.a(this.d)) != null && a.f();
        }
        return this.e != null && this.e.available() >= 28 && this.e.available() > 537;
    }
    
    private ck e() {
        Label_0109: {
            if (this.c < this.a.size()) {
                this.b = this.a.get(this.c++);
            }
            else {
                if (this.e != null) {
                    try {
                        this.a(this.b = new ck(this.e));
                        ++this.c;
                        break Label_0109;
                    }
                    catch (EOFException ex) {
                        this.b = null;
                        this.f = true;
                        break Label_0109;
                    }
                    catch (IOException ex2) {}
                }
                this.b = null;
            }
        }
        this.d = 0;
        return this.b;
    }
}
