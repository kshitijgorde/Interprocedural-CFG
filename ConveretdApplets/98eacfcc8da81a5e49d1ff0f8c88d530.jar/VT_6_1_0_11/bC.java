// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.SocketException;
import java.io.InputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;

final class bC
{
    private int a;
    private aj b;
    private bc c;
    private Socket d;
    private cq e;
    private av f;
    private static J g;
    private i h;
    private long i;
    private int j;
    
    bC(final int a, Socket d, final aj b) {
        this.d = null;
        this.f = null;
        this.j = 0;
        this.a = a;
        this.b = b;
        this.h = new i();
        d = d;
        new StringBuffer().append("Demux: Initializing Stream Demultiplexor (").append(this.hashCode()).append(")").toString();
        this.d = d;
        this.c = new bc(d.getInputStream());
        this.e = null;
        this.i = -1L;
        (this.f = bC.g.a(this)).b();
    }
    
    final void a(final bd bd, final q q) {
        synchronized (this.h) {
            if (this.d == null) {
                throw new cp();
            }
            this.h.a(new cq(bd, q, this));
        }
    }
    
    final K a(final bd bd) {
        cq cq;
        synchronized (this.h) {
            for (cq = (cq)this.h.b(); cq != null && cq.b != bd; cq = (cq)this.h.c()) {}
        }
        if (cq != null) {
            return cq.a;
        }
        return null;
    }
    
    final void a() {
        if (this.f != null) {
            this.f.a();
        }
    }
    
    final int a(final byte[] array, int a, int n, final cq cq, int i) {
        if (cq.e != null) {
            cq.e.fillInStackTrace();
            throw cq.e;
        }
        if (cq.d) {
            return -1;
        }
        cq cq2;
        while ((cq2 = (cq)this.h.a()) != null && cq2 != cq) {
            try {
                cq2.a.a(i);
                continue;
            }
            catch (IOException ex2) {
                final IOException ex = ex2;
                if (ex2 instanceof InterruptedIOException) {
                    throw ex;
                }
                cq.e.fillInStackTrace();
                throw cq.e;
            }
            break;
        }
        synchronized (this) {
            if (cq.e != null) {
                cq.e.fillInStackTrace();
                throw cq.e;
            }
            if (cq.b.j != 1) {
                new StringBuffer().append("Demux: Reading for stream ").append(cq.a.hashCode()).toString();
            }
            if (this.f != null) {
                this.f.b();
            }
            try {
                if (i != this.j) {
                    new StringBuffer().append("Demux: Setting timeout to ").append(i).append(" ms").toString();
                    this.d.setSoTimeout(i);
                    this.j = i;
                }
                int n2 = 0;
                switch (cq.b.j) {
                    case 1: {
                        if ((n2 = this.c.read(array, a, n)) == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        break;
                    }
                    case 2: {
                        n2 = -1;
                        this.b(cq);
                        break;
                    }
                    case 3: {
                        if ((n2 = this.c.read(array, a, n)) == -1) {
                            this.b(cq);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        i = cq.b.i;
                        if (n > i - cq.a.b) {
                            n = i - cq.a.b;
                        }
                        if ((n2 = this.c.read(array, a, n)) == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        if (cq.a.b + n2 == i) {
                            this.b(cq);
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (this.i == -1L) {
                            this.i = ci.a(this.c);
                        }
                        if (this.i <= 0L) {
                            cq.b.a(this.c);
                            n2 = -1;
                            this.b(cq);
                            this.i = -1L;
                            break;
                        }
                        if (n > this.i) {
                            n = (int)this.i;
                        }
                        if ((n2 = this.c.read(array, a, n)) == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        this.i -= n2;
                        if (this.i == 0L) {
                            this.c.read();
                            this.c.read();
                            this.i = -1L;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        final byte[] a2 = cq.a(this.c);
                        final int[] b = cq.b(this.c);
                        if ((n2 = this.c.read(array, a, n)) == -1) {
                            throw new EOFException("Premature EOF encountered");
                        }
                        if ((a = this.c.a(a2, b)) != -1) {
                            n2 -= a;
                            this.b(cq);
                            break;
                        }
                        break;
                    }
                    default: {
                        throw new Error("Internal Error in StreamDemultiplexor: Invalid cd_type " + cq.b.j);
                    }
                }
                this.a();
                return n2;
            }
            catch (InterruptedIOException ex3) {
                this.a();
                throw ex3;
            }
            catch (IOException ex4) {
                aF.a(4, "Demux: ", ex4);
                this.a(ex4, true);
                throw cq.e;
            }
            catch (dh dh) {
                aF.a(4, "Demux: ", dh);
                this.a(new IOException(dh.toString()), true);
                throw cq.e;
            }
        }
    }
    
    final synchronized long a(final long n, final cq cq) {
        if (cq.e != null) {
            cq.e.fillInStackTrace();
            throw cq.e;
        }
        if (cq.d) {
            return 0L;
        }
        final int a;
        if ((a = this.a(new byte[(int)n], 0, (int)n, cq, 0)) == -1) {
            return 0L;
        }
        return a;
    }
    
    final synchronized int a(final cq cq) {
        if (cq != null && cq.e != null) {
            cq.e.fillInStackTrace();
            throw cq.e;
        }
        if (cq != null && cq.d) {
            return 0;
        }
        final int available = this.c.available();
        if (cq == null) {
            return available;
        }
        switch (cq.b.j) {
            case 2: {
                return 0;
            }
            case 1: {
                if (available > 0) {
                    return 1;
                }
                return 0;
            }
            case 3: {
                return available;
            }
            case 4: {
                final int n = cq.b.i - cq.a.b;
                if (available < n) {
                    return available;
                }
                return n;
            }
            case 5: {
                return available;
            }
            case 6: {
                return available;
            }
            default: {
                throw new Error("Internal Error in StreamDemultiplexor: Invalid cd_type " + cq.b.j);
            }
        }
    }
    
    final synchronized void a(final IOException ex, final boolean b) {
        if (this.d == null) {
            return;
        }
        new StringBuffer().append("Demux: Closing all streams and socket (").append(this.hashCode()).append(")").toString();
        try {
            this.c.close();
        }
        catch (IOException ex2) {}
        try {
            this.d.close();
        }
        catch (IOException ex3) {}
        this.d = null;
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
        this.b.c.b(this);
        if (ex != null) {
            synchronized (this.h) {
                this.b(ex, b);
            }
        }
    }
    
    private void b(final IOException ex, final boolean f) {
        cp c = null;
        cp cp = null;
        for (cq cq = (cq)this.h.b(); cq != null; cq = (cq)this.h.c()) {
            if (cq.b.l) {
                cq.e = ex;
            }
            else {
                final cp e = new cp(ex.getMessage());
                if (c == null) {
                    c = e;
                }
                e.a = cq.c;
                e.b = cq.b;
                e.e = ex;
                e.f = f;
                e.c = c;
                final cp cp2 = e;
                final cp cp3 = cp;
                final cp d = cp2;
                if (cp3 != null) {
                    if (cp3.d != null) {
                        d.d = cp3.d;
                    }
                    cp3.d = d;
                }
                cp = e;
                cq.e = e;
            }
            this.h.b(cq);
        }
    }
    
    private void b(final cq cq) {
        synchronized (this.h) {
            if (cq != this.h.a()) {
                return;
            }
            new StringBuffer().append("Demux: Closing stream ").append(cq.a.hashCode()).toString();
            cq.d = true;
            this.h.b(cq);
        }
        final Throwable t;
        if (t == this.e) {
            this.a(new IOException("Premature end of Keep-Alive"), false);
            return;
        }
        this.b();
    }
    
    final synchronized void b() {
        synchronized (this.h) {
            for (cq cq = (cq)this.h.b(); cq != null && cq.a.a; cq = (cq)this.h.c()) {
                if (cq == this.e) {
                    cq cq2;
                    do {
                        cq2 = (cq)this.h.a();
                        this.h.b(cq2);
                    } while (cq2 != cq);
                    this.a(new IOException("Premature end of Keep-Alive"), false);
                    return;
                }
            }
        }
    }
    
    final synchronized Socket c() {
        if (this.e != null) {
            return null;
        }
        if (this.f != null) {
            this.f.b();
        }
        return this.d;
    }
    
    final synchronized void b(final bd bd) {
        synchronized (this.h) {
            if (this.h.a() == null) {
                this.a(new IOException("Premature end of Keep-Alive"), false);
                return;
            }
            if (this.f != null) {
                this.f.c();
                this.f = null;
            }
            cq e = null;
            for (cq e2 = (cq)this.h.b(); e2 != null; e2 = (cq)this.h.c()) {
                if (e2.b == bd) {
                    this.e = e2;
                    new StringBuffer().append("Demux: stream ").append(bd.c.hashCode()).append(" marked for close").toString();
                    this.b();
                    return;
                }
                if (this.e == e2) {
                    return;
                }
                e = e2;
            }
            if (e == null) {
                return;
            }
            this.e = e;
            this.b();
            new StringBuffer().append("Demux: stream ").append(e.a.hashCode()).append(" marked for close").toString();
        }
    }
    
    final void d() {
        new StringBuffer().append("Demux: Aborting socket (").append(this.hashCode()).append(")").toString();
        synchronized (this.h) {
            for (cq cq = (cq)this.h.b(); cq != null; cq = (cq)this.h.c()) {
                if (cq.b.a != null) {
                    cq.b.a.f();
                }
                if (cq.e == null) {
                    cq.e = new IOException("Request aborted by user");
                }
            }
            if (this.d != null) {
                try {
                    try {
                        this.d.setSoLinger(false, 0);
                    }
                    catch (SocketException ex) {}
                    try {
                        this.c.close();
                    }
                    catch (IOException ex2) {}
                    try {
                        this.d.close();
                    }
                    catch (IOException ex3) {}
                    this.d = null;
                    if (this.f != null) {
                        this.f.c();
                        this.f = null;
                    }
                }
                catch (NullPointerException ex4) {}
                this.b.c.b(this);
            }
        }
    }
    
    protected final void finalize() {
        this.a(null, false);
        super.finalize();
    }
    
    public final String toString() {
        String s = null;
        switch (this.a) {
            case 0: {
                s = "HTTP";
                break;
            }
            case 1: {
                s = "HTTPS";
                break;
            }
            case 2: {
                s = "SHTTP";
                break;
            }
            case 3: {
                s = "HTTP_NG";
                break;
            }
            default: {
                throw new Error("HTTPClient Internal Error: invalid protocol " + this.a);
            }
        }
        return this.getClass().getName() + "[Protocol=" + s + "]";
    }
    
    static J e() {
        return bC.g;
    }
    
    static {
        bC.g = null;
        (bC.g = new J(60)).start();
        new cW();
    }
}
