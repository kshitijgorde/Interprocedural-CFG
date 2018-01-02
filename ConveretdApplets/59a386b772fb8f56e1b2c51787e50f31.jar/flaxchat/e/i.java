// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.io.Serializable;

public final class i implements Serializable
{
    private char[] a;
    private int b;
    private boolean c;
    
    public i() {
        this(16);
    }
    
    public i(final int n) {
        this.a = new char[n];
        this.c = false;
    }
    
    public int a() {
        return this.b;
    }
    
    private final void b() {
        final char[] a = new char[this.a.length];
        System.arraycopy(this.a, 0, a, 0, this.b);
        this.a = a;
        this.c = false;
    }
    
    private void a(final int n) {
        int n2 = (this.a.length + 1) * 2;
        if (n > n2) {
            n2 = n;
        }
        final char[] a = new char[n2];
        System.arraycopy(this.a, 0, a, 0, this.b);
        this.a = a;
        this.c = false;
    }
    
    public void b(final int b) {
        final boolean a = g.a;
        if (b < 0) {
            throw new StringIndexOutOfBoundsException(b);
        }
        if (b > this.a.length) {
            this.a(b);
        }
        if (this.b >= b) {
            this.b = b;
            if (this.c) {
                this.b();
            }
            return;
        }
        while (true) {
            Label_0074: {
                if (!this.c) {
                    break Label_0074;
                }
                this.b();
                if (!a) {
                    break Label_0074;
                }
                this.a[this.b] = '\0';
                ++this.b;
            }
            if (this.b >= b) {
                return;
            }
            continue;
        }
    }
    
    public i a(final Object o) {
        return this.a(String.valueOf(o));
    }
    
    public i a(String value) {
        if (value == null) {
            value = String.valueOf(value);
        }
        final int length = value.length();
        final int b = this.b + length;
        if (b > this.a.length) {
            this.a(b);
        }
        value.getChars(0, length, this.a, this.b);
        this.b = b;
        return this;
    }
    
    public i a(final char c) {
        final int n = this.b + 1;
        if (n > this.a.length) {
            this.a(n);
        }
        this.a[this.b++] = c;
        return this;
    }
    
    public String toString() {
        return new String(this.a, 0, this.b);
    }
    
    public i a(final int n, int b) {
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (b > this.b) {
            b = this.b;
        }
        if (n > b) {
            throw new StringIndexOutOfBoundsException();
        }
        final int n2 = b - n;
        if (n2 > 0) {
            System.arraycopy(this.a, n + n2, this.a, n, this.b - b);
            this.b -= n2;
        }
        return this;
    }
    
    public i c() {
        return this.a(0, this.a());
    }
    
    public boolean b(final String s) {
        return this.toString().regionMatches(true, 0, s, 0, s.length());
    }
}
