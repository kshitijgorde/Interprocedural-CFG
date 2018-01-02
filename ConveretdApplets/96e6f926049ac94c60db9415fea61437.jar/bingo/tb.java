// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.eb;
import neat.cb;
import neat.kb;
import neat.system.f;
import neat.bb;

public class tb extends bb
{
    private static f d;
    public kb e;
    public neat.cb f;
    public neat.cb g;
    public float h;
    public eb i;
    private static /* synthetic */ Class j;
    private static String z;
    
    public void b() {
        tb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)tb.d.a();
    }
    
    public static tb a() {
        return (tb)tb.d.a();
    }
    
    public void g() {
        super.g();
        this.h = 1.0f;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
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
    
    public tb() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = null;
    }
    
    static {
        final char[] charArray = "aH\u000b#v-U\u0007".toCharArray();
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
                            c2 = '\u0003';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = 'e';
                            break;
                        }
                        case 3: {
                            c2 = 'D';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                tb.z = new String(charArray).intern();
                tb.d = new f((tb.j != null) ? tb.j : (tb.j = a(tb.z)));
                return;
            }
            continue;
        }
    }
}
