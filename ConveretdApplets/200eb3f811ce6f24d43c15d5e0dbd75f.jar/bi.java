import java.io.UnsupportedEncodingException;

// 
// Decompiled by Procyon v0.5.30
// 

public class bi
{
    public final byte[] a;
    public final int b;
    public final int c;
    
    public bi(final String s, final String s2) throws UnsupportedEncodingException {
        try {
            this.a = s.getBytes(s2);
            this.c = this.a.length;
        }
        catch (UnsupportedEncodingException ex) {
            if (n.a()) {
                n.a("Cannot use charset [" + s2 + "]", ex);
            }
            throw ex;
        }
        this.b = 0;
    }
    
    public bi(final byte[] a, final int b, final int c) {
        this.b = b;
        this.c = c;
        this.a = a;
    }
    
    public final byte a() {
        return this.a[this.b];
    }
    
    public final byte a(final int n) {
        if (n < 0 || this.b + n > this.c) {
            throw new ArrayIndexOutOfBoundsException("index not in Token i:" + n + "s:" + this.b + "l:" + (this.c - this.b));
        }
        return this.a[this.b + n];
    }
    
    public final int b() {
        return this.b;
    }
    
    public final int c() {
        return this.c;
    }
    
    public final String toString() {
        return this.a(b5.a, null);
    }
    
    public final String a(final String s) {
        return this.a(b5.a, s);
    }
    
    public final String a(final b5 b5, final String s) {
        if (s == null) {
            return this.a(b5) ? this.b(b5) : new String(this.a, this.b, this.c - this.b);
        }
        try {
            return this.a(b5) ? this.b(b5) : new String(this.a, this.b, this.c - this.b, s);
        }
        catch (UnsupportedEncodingException ex) {
            if (n.a()) {
                n.a("unsupported encoding", ex);
            }
            return this.a(b5) ? this.b(b5) : new String(this.a, this.b, this.c - this.b);
        }
    }
    
    public final Integer b(final String s) {
        if (this.c == 0) {
            throw new b6("toInteger: empty token.");
        }
        try {
            return new Integer(Integer.parseInt(this.a(s), 10));
        }
        catch (Exception ex) {
            throw new b6("conversion to integer failed.", ex);
        }
    }
    
    public final boolean a(final b5 b5) {
        return this.c > 0 && this.a[this.b] == b5.c;
    }
    
    public final boolean equals(final Object o) {
        if (o == null || !(o instanceof bi)) {
            return false;
        }
        final bi bi = (bi)o;
        return this.c == bi.c() && this.toString().equals(bi.toString());
    }
    
    public byte[] d() {
        return this.a;
    }
    
    private String b(final b5 b5) {
        if (this.c == 0) {
            return "";
        }
        if (n.e()) {
            n.a(this.a[this.b] == b5.c, "unquote: no starting quote.");
            n.a(this.a[this.c - 1] == b5.c, "unquote: no ending quote.");
        }
        final StringBuffer sb = new StringBuffer(this.c - this.b);
        for (int i = this.b + 1; i < this.c - 1; ++i) {
            final byte b6 = this.a[i];
            if (b6 == b5.c) {
                if (n.e()) {
                    n.a(this.a[i + 1] == b5.c, "following is not quote.");
                }
                ++i;
            }
            sb.append((char)(0xFF & b6));
        }
        return sb.toString();
    }
}
