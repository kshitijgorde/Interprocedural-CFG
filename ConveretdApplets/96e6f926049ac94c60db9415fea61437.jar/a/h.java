// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.cb;
import neat.kb;
import neat.system.f;

public class h extends c
{
    private static f k;
    private static /* synthetic */ Class l;
    private static String[] z;
    
    protected zb a(final kb kb) {
        final neat.cb cb = (neat.cb)((mb)this.a()).i.c(kb);
        if (cb == null) {
            return null;
        }
        if (!(cb instanceof zb)) {
            throw new RuntimeException(h.z[0] + cb);
        }
        return (zb)cb;
    }
    
    protected boolean b(final kb kb) {
        final zb a = this.a(kb);
        if (a == null) {
            return false;
        }
        final e d = this.i.d();
        return d != null && d.a(a);
    }
    
    protected void a(final sb sb) {
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof mb)) {
            throw new RuntimeException(h.z[1] + gb);
        }
        final mb mb = (mb)gb;
    }
    
    public static h a() {
        return (h)h.k.a();
    }
    
    public void f() {
        h.k.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "] \u001d\u0015p~/S\n1b\"S\r8p-\u001c\tpx:S\u0010?ei\u0012^\u0004p:\u00187$t$ \u00161u&\u000429\u007f\"I".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0011';
                            break;
                        }
                        case 1: {
                            c2 = 'I';
                            break;
                        }
                        case 2: {
                            c2 = 's';
                            break;
                        }
                        case 3: {
                            c2 = '~';
                            break;
                        }
                        default: {
                            c2 = 'P';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "E!\u001a\rpb!\u0012\u001a?fi\u001c\u0018px=\u0016\u0013p|<\u0000\nps,S\u001fpE(\u0000\u0015\u0019e,\u001e-8p-\u001c\tpx$\u0003\u00125|,\u001d\n1e \u001c\u0010j".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0011';
                            break;
                        }
                        case 1: {
                            c4 = 'I';
                            break;
                        }
                        case 2: {
                            c4 = 's';
                            break;
                        }
                        case 3: {
                            c4 = '~';
                            break;
                        }
                        default: {
                            c4 = 'P';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "pg\u001b".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0011';
                            break;
                        }
                        case 1: {
                            c6 = 'I';
                            break;
                        }
                        case 2: {
                            c6 = 's';
                            break;
                        }
                        case 3: {
                            c6 = '~';
                            break;
                        }
                        default: {
                            c6 = 'P';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                h.z = z;
                h.k = new f((h.l != null) ? h.l : (h.l = a(h.z[2])));
                return;
            }
            continue;
        }
    }
}
