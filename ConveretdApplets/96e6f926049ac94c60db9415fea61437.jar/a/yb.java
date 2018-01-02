// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.f;
import neat.bb;

public final class yb extends bb
{
    private static f d;
    public ec commands;
    public fc e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public void b() {
        yb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)yb.d.a();
    }
    
    public static yb a() {
        return (yb)yb.d.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
        if (this.commands != null) {
            this.commands.f();
            this.commands = null;
        }
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public yb() {
        this.commands = null;
        this.e = null;
    }
    
    static {
        final char[] charArray = "\u0016\f6\u0017".toCharArray();
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
                            c2 = 'w';
                            break;
                        }
                        case 1: {
                            c2 = '\"';
                            break;
                        }
                        case 2: {
                            c2 = 'O';
                            break;
                        }
                        case 3: {
                            c2 = 'u';
                            break;
                        }
                        default: {
                            c2 = '\u001f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                yb.z = new String(charArray).intern();
                yb.d = new f((yb.f != null) ? yb.f : (yb.f = a(yb.z)));
                return;
            }
            continue;
        }
    }
}
