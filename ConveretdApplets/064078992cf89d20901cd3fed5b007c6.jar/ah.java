// 
// Decompiled by Procyon v0.5.30
// 

public class ah
{
    public static final int int = 0;
    public static final int case = 1;
    public static final int try = 2;
    public static final int a = 3;
    public static final int new = 4;
    public static final int long = 5;
    public static final int void = 6;
    public static final int e = 7;
    public static final int d = 0;
    public static final int g = 1;
    public static final int char = 2;
    public static final int do = 3;
    int for;
    int goto;
    int else;
    int if;
    boolean h;
    String[] b;
    int byte;
    int f;
    al c;
    
    public ah() {
        this.byte = 0;
        this.b = new String[10];
        this.c = new al();
    }
    
    public void a(final String s) {
        if (s == null || s.length() == 0) {
            return;
        }
        if (this.b.length == this.byte) {
            final String[] b = new String[this.byte + 10];
            for (int i = 0; i < this.byte; ++i) {
                b[i] = this.b[i];
            }
            this.b = b;
        }
        this.b[this.byte] = s;
        ++this.byte;
    }
    
    public void a() {
        this.for = 3;
        this.goto = 0;
        this.else = 0;
        this.if = 0;
        this.h = false;
        this.byte = 0;
    }
}
