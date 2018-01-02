// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class am
{
    public static final int int = 0;
    public static final int case = 1;
    public static final int try = 2;
    public static final int a = 3;
    public static final int new = 4;
    public static final int long = 5;
    public static final int void = 6;
    public static final int f = 7;
    public static final int e = 0;
    public static final int h = 1;
    public static final int char = 2;
    public static final int do = 3;
    int for;
    int goto;
    int else;
    int if;
    boolean[] c;
    boolean i;
    char[][] b;
    int byte;
    int g;
    a4 d;
    
    public am() {
        this.byte = 0;
        this.b = new char[10][];
        this.c = new boolean[10];
        this.d = new a4();
    }
    
    public void a(final char[] array, final boolean b) {
        if (array == null || array[0] == '\0') {
            return;
        }
        if (this.b.length == this.byte) {
            final char[][] b2 = new char[this.byte + 10][];
            final boolean[] c = new boolean[this.byte + 10];
            for (int i = 0; i < this.byte; ++i) {
                b2[i] = this.b[i];
                c[i] = this.c[i];
            }
            this.b = b2;
            this.c = c;
        }
        this.b[this.byte] = array;
        this.c[this.byte] = b;
        ++this.byte;
    }
    
    public void a() {
        this.for = 3;
        this.goto = 0;
        this.else = 0;
        this.if = 0;
        this.i = false;
        this.byte = 0;
    }
}
