// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.f;
import neat.system.cb;

public class m implements cb
{
    private static f a;
    public boolean b;
    public neat.system.graphics.renderer.m c;
    public int d;
    public int e;
    public int f;
    public int g;
    public float h;
    public float i;
    public int j;
    public int k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public static m a() {
        return (m)m.a.a();
    }
    
    public void f() {
        m.a.a(this);
    }
    
    public void g() {
        this.b = true;
        this.f = -1;
        this.g = -1;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0;
        this.k = 0;
    }
    
    public void h() {
        this.c = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public m() {
        this.c = null;
    }
    
    static {
        final char[] charArray = ">h\f\u001aZrl".toCharArray();
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
                            c2 = '\\';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = '}';
                            break;
                        }
                        default: {
                            c2 = '5';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                m.z = new String(charArray).intern();
                m.a = new f((m.l != null) ? m.l : (m.l = a(m.z)));
                return;
            }
            continue;
        }
    }
}
