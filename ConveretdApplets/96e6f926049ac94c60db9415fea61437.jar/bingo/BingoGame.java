// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.f;
import a.y;

public class BingoGame extends y
{
    private static f pb;
    private static /* synthetic */ Class qb;
    private static String rb;
    
    public static BingoGame a() {
        return (BingoGame)BingoGame.pb.a();
    }
    
    public void f() {
        BingoGame.pb.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final char[] charArray = "\u00064\u0000\u001a1J\u001f\u0007\u00139\u000b\u001a\u000f\u0010;".toCharArray();
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
                            c2 = 'd';
                            break;
                        }
                        case 1: {
                            c2 = ']';
                            break;
                        }
                        case 2: {
                            c2 = 'n';
                            break;
                        }
                        case 3: {
                            c2 = '}';
                            break;
                        }
                        default: {
                            c2 = '^';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                BingoGame.rb = new String(charArray).intern();
                BingoGame.pb = new f((BingoGame.qb != null) ? BingoGame.qb : (BingoGame.qb = b(BingoGame.rb)));
                return;
            }
            continue;
        }
    }
}
