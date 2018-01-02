// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class wb extends bb
{
    private static f d;
    public int e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int j;
    public int k;
    public neat.cb l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public void b() {
        wb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)wb.d.a();
    }
    
    public static wb a() {
        return (wb)wb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 1;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0;
        this.k = 0;
    }
    
    public void h() {
        if (this.l != null) {
            this.l.f();
            this.l = null;
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
    
    public wb() {
        this.l = null;
    }
    
    static {
        final char[] charArray = "~>\u0002L\u00192 \u000e".toCharArray();
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
                            c2 = '\u001c';
                            break;
                        }
                        case 1: {
                            c2 = 'W';
                            break;
                        }
                        case 2: {
                            c2 = 'l';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = 'v';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                wb.z = new String(charArray).intern();
                wb.d = new f((wb.m != null) ? wb.m : (wb.m = a(wb.z)));
                return;
            }
            continue;
        }
    }
}
