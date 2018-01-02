// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.kb;
import neat.system.s;
import a.y;
import neat.system.f;
import a.eb;

public class BingoRoot extends eb
{
    private static f z;
    private static /* synthetic */ Class A;
    private static String B;
    
    protected a.y x() {
        return BingoGame.a();
    }
    
    public void receiveEvent(final s s) {
        super.receiveEvent(s);
    }
    
    public static BingoRoot a(final kb kb) {
        final BingoRoot bingoRoot = (BingoRoot)BingoRoot.z.a();
        bingoRoot.a(kb, BingoRoot.i);
        return bingoRoot;
    }
    
    public void f() {
        BingoRoot.z.a(this);
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
        final char[] charArray = "vR\u000b\u0012*:y\f\u001b\"{i\n\u001a1".toCharArray();
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
                            c2 = '\u0014';
                            break;
                        }
                        case 1: {
                            c2 = ';';
                            break;
                        }
                        case 2: {
                            c2 = 'e';
                            break;
                        }
                        case 3: {
                            c2 = 'u';
                            break;
                        }
                        default: {
                            c2 = 'E';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                BingoRoot.B = new String(charArray).intern();
                BingoRoot.z = new f((BingoRoot.A != null) ? BingoRoot.A : (BingoRoot.A = b(BingoRoot.B)));
                return;
            }
            continue;
        }
    }
}
