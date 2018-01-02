// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.zip.DataFormatException;

public class aH
{
    private final byte[] b;
    private int c;
    public boolean a;
    private static /* synthetic */ boolean d;
    
    public aH(final byte[] b) {
        this.c = 0;
        this.a = true;
        this.b = b;
    }
    
    public final void a(final int n) {
        this.c -= n;
        if (this.c < 0) {
            throw new RuntimeException("Rewound buffer too far");
        }
    }
    
    public final void b(final int c) {
        if (!aH.d && c < 0) {
            throw new AssertionError();
        }
        this.c = c;
        if (this.c >= this.b.length) {
            throw new RuntimeException("Jumped over the edge!");
        }
    }
    
    public final int a() {
        return this.c;
    }
    
    public final void a(final String s) {
        if (!aH.d && s == null) {
            throw new AssertionError();
        }
        final int c = this.c;
        final char[] charArray;
        final int length = (charArray = s.toCharArray()).length;
        int i = 0;
        while (true) {
            while (i < length) {
                if (this.d() != charArray[i]) {
                    this.c = c;
                    final boolean b = false;
                    if (!b) {
                        throw new DataFormatException("Buffer pattern match failed on '" + s + "'");
                    }
                    this.c += s.length();
                    return;
                }
                else {
                    ++i;
                }
            }
            this.c = c;
            final boolean b = true;
            continue;
        }
    }
    
    private byte d() {
        return this.b[this.c++];
    }
    
    private void e(final int n) {
        this.b[this.c++] = (byte)n;
    }
    
    public final int b() {
        if (this.a) {
            return (this.d() & 0xFF) << 8 | (this.d() & 0xFF);
        }
        return (this.d() & 0xFF) | (this.d() & 0xFF) << 8;
    }
    
    public final void c(int n) {
        if (this.a) {
            this.e(n >> 8 & 0xFF);
            this.e(n & 0xFF);
            return;
        }
        this.e(n & 0xFF);
        n >>= 8;
        this.e(n & 0xFF);
    }
    
    public final void d(int n) {
        if (this.a) {
            this.e(n >> 24);
            this.e(n >> 16 & 0xFF);
            this.e(n >> 8 & 0xFF);
            this.e(n & 0xFF);
            return;
        }
        this.e(n & 0xFF);
        n >>= 8;
        this.e(n & 0xFF);
        n >>= 8;
        this.e(n & 0xFF);
        n >>= 8;
        this.e(n & 0xFF);
    }
    
    public final int c() {
        if (this.a) {
            return (this.d() & 0xFF) << 24 | (this.d() & 0xFF) << 16 | (this.d() & 0xFF) << 8 | (this.d() & 0xFF);
        }
        return (this.d() & 0xFF) | (this.d() & 0xFF) << 8 | (this.d() & 0xFF) << 16 | (this.d() & 0xFF) << 24;
    }
    
    static {
        aH.d = !aH.class.desiredAssertionStatus();
    }
}
