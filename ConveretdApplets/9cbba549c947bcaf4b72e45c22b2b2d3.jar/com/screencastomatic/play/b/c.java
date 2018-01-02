// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.b;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.io.EOFException;
import com.screencastomatic.play.q;
import java.io.InputStream;

class c extends InputStream
{
    private int b;
    private g c;
    final /* synthetic */ b a;
    
    public c(final b a, final long n) {
        this.a = a;
        this.a((int)(n / 204800L));
        this.b = (int)(n % 204800L);
    }
    
    public g a() {
        return this.c;
    }
    
    public int read() {
        final byte[] array = { 0 };
        final int read = this.read(array);
        return (read == -1) ? read : (array[0] & 0xFF);
    }
    
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, int n, final int n2) {
        if (this.b()) {
            return -1;
        }
        int i = 0;
        while (i < n2) {
            if (this.a.e != null) {
                throw new RuntimeException("Download failed with this error.", this.a.e);
            }
            if (this.c.d && this.c.b == this.b) {
                q.a("Throwing EOF since we're at the end of the last buffer.");
                throw new EOFException("At end of buffers.");
            }
            if (this.c.b <= this.b) {
                if (this.c.b == 204800) {
                    this.a(this.c.a + 1);
                    this.b = 0;
                }
                else {
                    this.a(this.c.a);
                    try {
                        q.a("Download behind so wait some for it.");
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex) {
                        q.a(ex);
                        break;
                    }
                }
            }
            final int min = Math.min(n2 - i, this.c.b - this.b);
            try {
                System.arraycopy(this.c.c, this.b, array, n, min);
                n += min;
                this.b += min;
                i += min;
            }
            catch (Exception ex2) {
                q.a("Exception with arraycopy but ignoring...");
            }
        }
        return i;
    }
    
    public long skip(final long n) {
        if (this.b()) {
            q.a("Skip asked to skip when done for bytes: " + n);
            return 0L;
        }
        q.a("Skip asked to skip bytes: " + n);
        final int n2 = (int)(this.c.a * 204800 + this.b + n);
        final int n3 = n2 / 204800;
        if (this.c.a != n3) {
            this.a(n3);
        }
        this.b = n2 % 204800;
        return n;
    }
    
    public void close() {
    }
    
    public int available() {
        final g c = this.a.f.a.c(this.c.a);
        if (c == null) {
            return 0;
        }
        int n = this.c.b - this.b;
        if (c.a > this.c.a) {
            n += (c.a - this.c.a) * 204800 + c.b;
        }
        return n;
    }
    
    public boolean b() {
        return this.c.d && this.b == this.c.b;
    }
    
    private void a(final int n) {
        this.c = this.a.f.a.a(n);
        if (this.a.f.b != null && !this.a.f.b.a(this.c)) {
            this.a.f.b.a();
            this.a.f.b = null;
        }
        final g c = this.a.f.a.c(this.c.a);
        if (c.a - this.c.a >= this.a.c()) {
            if (this.a.f.b == null) {
                q.a("Not looking to restart thread since last buffer is so far a head (last: " + c.a + " moveTo: " + this.c.a + ")");
            }
            return;
        }
        if (this.a.f.b == null) {
            q.a("Starting new download since moving to: " + this.c.a);
            this.a.f.b = new a(this.a, this.c);
        }
    }
}
