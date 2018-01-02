import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public static final int char = 10;
    public static final int try = 20;
    public static final int else = 30;
    public static final int do = 40;
    public static final int long = 50;
    int a;
    int case;
    int new;
    int goto;
    int null;
    int c;
    int byte;
    int int;
    int d;
    int for;
    Color b;
    Image void;
    m[] if;
    
    public l() {
        this.a = -1;
        this.case = 40;
    }
    
    public l(final l l) {
        this.a = -1;
        this.case = 40;
        if (l != null) {
            this.a = l.a;
            this.case = l.case;
            this.new = l.new;
            this.goto = l.goto;
            this.null = l.null;
            this.c = l.c;
            this.byte = l.byte;
            this.int = l.int;
            this.d = l.d;
            this.for = l.for;
            this.b = l.b;
            this.void = l.void;
            if (l.if != null) {
                this.if = new m[l.if.length];
                for (int i = 0; i < l.if.length; ++i) {
                    this.if[i] = l.if[i];
                }
            }
        }
    }
}
