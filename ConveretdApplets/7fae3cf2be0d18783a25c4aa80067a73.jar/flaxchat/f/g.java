// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.f;

import flaxchat.d.b;

public class g
{
    private String a;
    private String b;
    private String c;
    private static String[] z;
    
    public g(final String s, final String s2) {
        this.b(s);
        this.a(s2);
    }
    
    public void a(final String b) {
        this.b = b;
        this.c = this.b.toLowerCase();
    }
    
    public void b(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public boolean b() {
        return this.a.indexOf(64) >= 0;
    }
    
    public boolean c() {
        return this.a.indexOf(43) >= 0;
    }
    
    public boolean d() {
        return this.a.indexOf(126) >= 0;
    }
    
    public boolean e() {
        return this.a.indexOf(38) >= 0;
    }
    
    public boolean f() {
        return this.a.indexOf(37) >= 0;
    }
    
    public String g() {
        return this.b;
    }
    
    public String toString() {
        if (this.b()) {
            return String.valueOf('\u0003') + flaxchat.d.b.c(g.z[3]) + "@" + this.g();
        }
        if (this.d()) {
            return String.valueOf('\u0003') + flaxchat.d.b.c(g.z[2]) + "~" + this.g();
        }
        if (this.e()) {
            return String.valueOf('\u0003') + flaxchat.d.b.c(g.z[1]) + "&" + this.g();
        }
        if (this.f()) {
            return String.valueOf('\u0003') + flaxchat.d.b.c(g.z[0]) + "%" + this.g();
        }
        if (this.c()) {
            return String.valueOf('\u0003') + flaxchat.d.b.c(g.z[4]) + "+" + this.g();
        }
        return String.valueOf(this.a()) + this.g();
    }
    
    public boolean equals(final Object o) {
        return o instanceof g && ((g)o).c.equals(this.c);
    }
    
    public int hashCode() {
        return this.c.hashCode();
    }
    
    static {
        g.z = new String[] { z(z("\u001fH\u000b%/\u0007|\u0014&\u00124F\u000b,\u0012")), z(z("\u0004F\u0017\u0016\u0013\u0012[$,\f\u0018[")), z(z("\u0018^\t&\u0012\"Z\u00021#\u0018E\b1")), z(z("\u0018Y\u0014\u0016\u0013\u0012[$,\f\u0018[")), z(z("\u0001F\u000e \u0005\"Z\u00021#\u0018E\b1")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '`';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = ')';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = 'C';
                    break;
                }
                default: {
                    c2 = '`';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
