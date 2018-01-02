// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.cb;
import neat.system.f;

public class jb extends hb
{
    private static f h;
    public neat.cb i;
    public neat.cb j;
    public neat.cb k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public b a() {
        return a.e.l();
    }
    
    public void b() {
        jb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)jb.h.a();
    }
    
    public static jb a() {
        return (jb)jb.h.a();
    }
    
    public void g() {
        super.g();
        this.f = 0;
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
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
    
    public jb() {
        this.i = null;
        this.j = null;
        this.k = null;
    }
    
    static {
        final char[] charArray = "\fA\u001ez".toCharArray();
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
                            c2 = 'm';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = 't';
                            break;
                        }
                        case 3: {
                            c2 = '\u0018';
                            break;
                        }
                        default: {
                            c2 = 'M';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                jb.z = new String(charArray).intern();
                jb.h = new f((jb.l != null) ? jb.l : (jb.l = a(jb.z)));
                return;
            }
            continue;
        }
    }
}
