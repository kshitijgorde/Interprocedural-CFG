// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

class v
{
    final u a;
    private String b;
    private String c;
    private static String z;
    
    v(final u a) {
        this.a = a;
    }
    
    v(final u a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    String a() {
        return this.b;
    }
    
    String b() {
        return this.c;
    }
    
    int c() {
        return t.d(this.c);
    }
    
    double d() {
        return t.e(this.c);
    }
    
    boolean e() {
        int c;
        final int n = c = this.c();
        if (RotationImageFilter.a == 0) {
            if (n > 0) {
                c = (true ? 1 : 0);
            }
            else {
                c = (false ? 1 : 0);
            }
        }
        return c != 0;
    }
    
    void a(final String b) {
        this.b = b;
    }
    
    void b(final String c) {
        this.c = c;
    }
    
    void a(final int n) {
        this.c = Integer.toString(n);
    }
    
    void a(final double n) {
        this.c = Double.toString(n);
        v v = this;
        if (RotationImageFilter.a == 0) {
            if (!this.c.endsWith(cfg8.v.z)) {
                return;
            }
            v = this;
        }
        v.c = this.c.substring(0, this.c.length() - 2);
    }
    
    void a(final boolean b) {
        this.c = (b ? "1" : "0");
    }
    
    static {
        final char[] charArray = "/l".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0001';
                            break;
                        }
                        case 1: {
                            c2 = '\\';
                            break;
                        }
                        case 2: {
                            c2 = '^';
                            break;
                        }
                        case 3: {
                            c2 = 'Y';
                            break;
                        }
                        default: {
                            c2 = 'D';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                v.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
