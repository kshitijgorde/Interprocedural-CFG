// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public final class cK extends OutputStream
{
    private static final cU[] a;
    private int b;
    private int c;
    private q d;
    private bd e;
    private OutputStream f;
    private ByteArrayOutputStream g;
    private cU[] h;
    private int i;
    private boolean j;
    
    public cK() {
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = cK.a;
        this.i = 0;
        this.j = false;
        this.b = -1;
    }
    
    final void a(final q d, final OutputStream f, final int i) {
        this.d = d;
        this.f = f;
        this.i = i;
        if (f == null) {
            this.g = new ByteArrayOutputStream();
        }
    }
    
    final void a(final q d) {
        this.d = d;
        this.j = true;
    }
    
    final synchronized bd a() {
        while (this.e == null) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        return this.e;
    }
    
    public final int b() {
        return this.b;
    }
    
    public final void c() {
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = 0;
        this.j = false;
    }
    
    public final void write(final int n) {
        this.write(new byte[] { (byte)n }, 0, 1);
    }
    
    public final synchronized void write(final byte[] array, final int n, final int n2) {
        if (this.d == null) {
            throw new IllegalAccessError("Stream not associated with a request");
        }
        if (this.j) {
            return;
        }
        if (this.b != -1 && this.c + n2 > this.b) {
            final IOException ex = new IOException("Tried to write too many bytes (" + (this.c + n2) + " > " + this.b + ")");
            this.d.a().a(ex, false);
            this.d.a().i();
            throw ex;
        }
        try {
            if (this.g != null) {
                this.g.write(array, n, n2);
            }
            else if (this.b != -1) {
                this.f.write(array, n, n2);
            }
            else {
                this.f.write(ci.a(array, n, n2, null, false));
            }
        }
        catch (IOException ex2) {
            this.d.a().a(ex2, true);
            this.d.a().i();
            throw ex2;
        }
        this.c += n2;
    }
    
    public final synchronized void close() {
        if (this.d == null) {
            throw new IllegalAccessError("Stream not associated with a request");
        }
        if (this.j) {
            return;
        }
        if (this.g != null) {
            this.d.a(this.g.toByteArray());
            this.d.a((cK)null);
            if (this.h.length > 0) {
                final cU[] d;
                int length = (d = this.d.d()).length;
                for (int i = 0; i < length; ++i) {
                    if (d[i].a().equalsIgnoreCase("Trailer")) {
                        System.arraycopy(d, i + 1, d, i, length - i - 1);
                        --length;
                    }
                }
                final cU[] a = bz.a(d, length + this.h.length);
                System.arraycopy(this.h, 0, a, length, this.h.length);
                this.d.a(a);
            }
            try {
                this.e = this.d.a().a(this.d, this.i);
            }
            catch (bw bw) {
                throw new IOException(bw.toString());
            }
            this.notify();
            return;
        }
        if (this.c < this.b) {
            final IOException ex = new IOException("Premature close: only " + this.c + " bytes written instead of the " + "expected " + this.b);
            this.d.a().a(ex, false);
            this.d.a().i();
            throw ex;
        }
        try {
            if (this.b == -1) {
                if (aF.a(1) && this.h.length > 0) {
                    for (int j = 0; j < this.h.length; ++j) {
                        new StringBuffer().append("       ").append(this.h[j].a()).append(": ").append(this.h[j].b()).toString();
                    }
                }
                this.f.write(ci.a(null, 0, 0, this.h, true));
            }
            this.f.flush();
        }
        catch (IOException ex2) {
            this.d.a().a(ex2, true);
            throw ex2;
        }
        finally {
            this.d.a().i();
        }
    }
    
    public final String toString() {
        return this.getClass().getName() + "[length=" + this.b + "]";
    }
    
    static {
        a = new cU[0];
    }
}
