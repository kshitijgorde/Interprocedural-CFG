// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public class d extends c
{
    private static f f;
    private h g;
    private static /* synthetic */ Class h;
    private static String z;
    
    private void a(final h g) {
        this.g = g;
    }
    
    public void e() {
        this.g.j(this.a());
        super.e();
    }
    
    public static d a(final h h, final i i) {
        final d d = (d)neat.d.f.a();
        d.a((neat.f)i);
        d.a(h);
        return d;
    }
    
    public void f() {
        d.f.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public d() {
        this.g = null;
    }
    
    static {
        final char[] charArray = "\u000b&U5D\u0001".toCharArray();
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
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = 'C';
                            break;
                        }
                        case 2: {
                            c2 = '4';
                            break;
                        }
                        case 3: {
                            c2 = 'A';
                            break;
                        }
                        default: {
                            c2 = 'j';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                d.z = new String(charArray).intern();
                d.f = new f((d.h != null) ? d.h : (d.h = b(d.z)));
                return;
            }
            continue;
        }
    }
}
