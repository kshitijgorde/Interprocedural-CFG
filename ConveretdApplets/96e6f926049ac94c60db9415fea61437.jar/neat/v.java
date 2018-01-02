// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class v implements cb
{
    public static int a;
    public static double b;
    private static f c;
    private int d;
    private int[] e;
    private long f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void a() {
        final long f = this.f;
        this.d = 0;
        for (int i = 0; i < 250; ++i) {
            this.e[i] = this.d();
        }
        for (int j = 0; j < 250; ++j) {
            if (this.d() > 16384) {
                final int[] e = this.e;
                final int n = j;
                e[n] |= 0x8000;
            }
        }
        int n2 = 32768;
        int n3 = 65535;
        for (int k = 0; k < 16; ++k) {
            final int n4 = 11 * k + 3;
            final int[] e2 = this.e;
            final int n5 = n4;
            e2[n5] &= n3;
            final int[] e3 = this.e;
            final int n6 = n4;
            e3[n6] |= n2;
            n3 >>= 1;
            n2 >>= 1;
        }
        this.f = f;
    }
    
    public int b() {
        int n;
        if (this.d >= 147) {
            n = this.d - 147;
        }
        else {
            n = this.d + 103;
        }
        final int[] e = this.e;
        final int d = this.d;
        final int n2 = e[d] ^ this.e[n];
        e[d] = n2;
        final int n3 = n2;
        if (this.d >= 249) {
            this.d = 0;
        }
        else {
            ++this.d;
        }
        return n3;
    }
    
    public int a(final int n) {
        return this.b() * n >> v.a;
    }
    
    public float c() {
        return (float)(this.b() / v.b);
    }
    
    private int d() {
        this.f = this.f * 22695477L + 1L;
        return (int)(this.f >> 16 & 0x7FFFL);
    }
    
    public void b(final int n) {
        this.f = n;
        this.a();
    }
    
    public static v e() {
        return (v)v.c.a();
    }
    
    public void f() {
        v.c.a(this);
    }
    
    public void g() {
        this.b((int)(Math.random() * v.b));
    }
    
    public void h() {
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public v() {
        this.e = new int[250];
    }
    
    static {
        final char[] charArray = "\u0006H\u001aG$\u001e".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'h';
                            break;
                        }
                        case 1: {
                            c2 = '-';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = '3';
                            break;
                        }
                        default: {
                            c2 = '\n';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                v.z = new String(charArray).intern();
                v.a = 16;
                v.b = 1 << v.a;
                v.c = new f((v.g != null) ? v.g : (v.g = a(v.z)));
                return;
            }
            continue;
        }
    }
}
