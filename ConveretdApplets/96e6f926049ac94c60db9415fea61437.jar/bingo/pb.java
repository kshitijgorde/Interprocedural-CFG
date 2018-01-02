// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class pb extends bb
{
    private static f d;
    public neat.cb e;
    public kb f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void b() {
        pb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)pb.d.a();
    }
    
    public static pb a() {
        return (pb)pb.d.a();
    }
    
    public void g() {
        super.g();
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
    
    public pb() {
        this.e = null;
        this.f = null;
    }
    
    static {
        final char[] charArray = "\\\u001aXof\u0010\u0003T".toCharArray();
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
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = 's';
                            break;
                        }
                        case 2: {
                            c2 = '6';
                            break;
                        }
                        case 3: {
                            c2 = '\b';
                            break;
                        }
                        default: {
                            c2 = '\t';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                pb.z = new String(charArray).intern();
                pb.d = new f((pb.g != null) ? pb.g : (pb.g = a(pb.z)));
                return;
            }
            continue;
        }
    }
}
