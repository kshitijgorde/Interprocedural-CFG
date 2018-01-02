// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class c extends b
{
    private int h;
    protected a.y i;
    public static int j;
    private static String z;
    
    public int a() {
        return this.h;
    }
    
    void a(final a.y i) {
        this.i = i;
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof hb)) {
            throw new RuntimeException(c.z + gb);
        }
        this.h = ((hb)gb).f;
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
        this.i = null;
    }
    
    public abstract void f();
    
    public c() {
        this.i = null;
    }
    
    static {
        final char[] charArray = "7Rqx\u000b\u0010RyoD\u0014\u001awm\u000b\nN}f\u000b\u000eOk\u007f\u000b\u0001_8j\u000b3ObqG\u0006slnF0RyoD\u0014\u001aqf[\u000f_unE\u0017[lbD\r\u0000".toCharArray();
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
                            c2 = 'c';
                            break;
                        }
                        case 1: {
                            c2 = ':';
                            break;
                        }
                        case 2: {
                            c2 = '\u0018';
                            break;
                        }
                        case 3: {
                            c2 = '\u000b';
                            break;
                        }
                        default: {
                            c2 = '+';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                c.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
