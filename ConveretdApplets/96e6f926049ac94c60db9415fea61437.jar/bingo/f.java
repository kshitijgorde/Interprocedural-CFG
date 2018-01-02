// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.graphics.renderer.m;
import neat.system.cb;

public class f implements cb
{
    private static neat.system.f a;
    public int b;
    public int c;
    public int d;
    public int e;
    public float f;
    public boolean g;
    public boolean h;
    public m i;
    public m j;
    public boolean k;
    public boolean l;
    public m m;
    public m n;
    private static /* synthetic */ Class o;
    private static String z;
    
    public static f a() {
        return (f)f.a.a();
    }
    
    public void f() {
        bingo.f.a.a(this);
    }
    
    public void g() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = 0.0f;
        this.g = false;
        this.h = false;
        this.k = false;
        this.l = false;
    }
    
    public void h() {
        this.i = null;
        this.j = null;
        this.m = null;
        this.n = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public f() {
        this.i = null;
        this.j = null;
        this.m = null;
        this.n = null;
    }
    
    static {
        final char[] charArray = "!\n\u0010E}m\u0005".toCharArray();
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
                            c2 = 'C';
                            break;
                        }
                        case 1: {
                            c2 = 'c';
                            break;
                        }
                        case 2: {
                            c2 = '~';
                            break;
                        }
                        case 3: {
                            c2 = '\"';
                            break;
                        }
                        default: {
                            c2 = '\u0012';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                f.z = new String(charArray).intern();
                f.a = new neat.system.f((f.o != null) ? f.o : (f.o = a(f.z)));
                return;
            }
            continue;
        }
    }
}
