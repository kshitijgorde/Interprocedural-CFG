// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.fb;
import neat.kb;
import neat.system.f;
import neat.bb;

public class qb extends bb
{
    private static f d;
    public kb e;
    public fb f;
    public int g;
    public boolean h;
    public kb i;
    public fb j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b() {
        qb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)qb.d.a();
    }
    
    public static qb a() {
        return (qb)qb.d.a();
    }
    
    public void g() {
        super.g();
        this.g = 1;
        this.h = true;
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
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
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
    
    public qb() {
        this.e = null;
        this.f = null;
        this.i = null;
        this.j = null;
    }
    
    static {
        final char[] charArray = "`++\u007f".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0092: {
                if (n > 1) {
                    break Label_0092;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0001';
                            break;
                        }
                        case 1: {
                            c2 = '\u0005';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = '\u001d';
                            break;
                        }
                        default: {
                            c2 = '>';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                qb.z = new String(charArray).intern();
                qb.d = new f((qb.k != null) ? qb.k : (qb.k = a(qb.z)));
                return;
            }
            continue;
        }
    }
}
