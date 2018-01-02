// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.kb;
import neat.system.f;
import neat.bb;

public class q extends bb
{
    private static f d;
    public kb e;
    public boolean f;
    public float g;
    private static /* synthetic */ Class h;
    private static String z;
    
    public static q a() {
        return (q)q.d.a();
    }
    
    public void b() {
        q.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)q.d.a();
    }
    
    public void g() {
        super.g();
        this.f = false;
        this.g = 1.0f;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
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
    
    public q() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "c\u0007\nDH~\u001b\u0018D\u0003`L\fB\u0007}\n\u0002S\u0015#\u0010\u000e^\u0002h\u0010\u000eBH|".toCharArray();
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
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = 'b';
                            break;
                        }
                        case 2: {
                            c2 = 'k';
                            break;
                        }
                        case 3: {
                            c2 = '0';
                            break;
                        }
                        default: {
                            c2 = 'f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                q.z = new String(charArray).intern();
                q.d = new f((q.h != null) ? q.h : (q.h = a(q.z)));
                return;
            }
            continue;
        }
    }
}
