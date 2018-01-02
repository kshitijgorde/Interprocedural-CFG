// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import a.h;
import a.c;

public abstract class p extends c
{
    public static boolean k;
    private static String z;
    
    public bingo.bb a() {
        final h e = this.i.e();
        if (e == null || !(e instanceof bingo.bb)) {
            throw new RuntimeException(p.z + e);
        }
        return (bingo.bb)e;
    }
    
    public bingo.y b() {
        return this.a().z();
    }
    
    public abstract void f();
    
    static {
        final char[] charArray = "Q\u0004\u001bT\u0011\u007f\u0011\u0013\\\u0011{\u0010\u0005E\u0011t\u0000VD_r\u0000\u0004\u0011vw\b\u0013ePe\u000eZ\u0011Sc\u0011VEY\u007f\u0016VXB6\u000b\u0019E\u000b".toCharArray();
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
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = 'e';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = '1';
                            break;
                        }
                        default: {
                            c2 = '1';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                p.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
