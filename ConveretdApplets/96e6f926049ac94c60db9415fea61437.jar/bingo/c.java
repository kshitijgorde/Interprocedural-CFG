// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.graphics.renderer.m;
import a.db;
import neat.system.f;
import neat.system.cb;

public class c implements cb
{
    private static f a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public db j;
    public m k;
    public float l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public static c a() {
        return (c)c.a.a();
    }
    
    public void f() {
        bingo.c.a.a(this);
    }
    
    public void g() {
        this.b = false;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
        this.i = -1;
        this.l = 0.0f;
    }
    
    public void h() {
        this.j = null;
        this.k = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public c() {
        this.j = null;
    }
    
    static {
        final char[] charArray = "\u001cE\u0017$IPO".toCharArray();
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
                            c2 = '~';
                            break;
                        }
                        case 1: {
                            c2 = ',';
                            break;
                        }
                        case 2: {
                            c2 = 'y';
                            break;
                        }
                        case 3: {
                            c2 = 'C';
                            break;
                        }
                        default: {
                            c2 = '&';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                c.z = new String(charArray).intern();
                c.a = new f((c.m != null) ? c.m : (c.m = a(c.z)));
                return;
            }
            continue;
        }
    }
}
