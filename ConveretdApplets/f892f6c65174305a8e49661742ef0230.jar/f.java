import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public static final int new = 10;
    public static final int long = 100;
    public int case;
    public Font goto;
    public Color int;
    public Color byte;
    public Color a;
    public String void;
    public String try;
    public String for;
    public u null;
    public Color c;
    public Image b;
    public int char;
    public int do;
    public int d;
    public int if;
    public k[] else;
    
    public f() {
        this.case = 10;
        this.int = Color.white;
        this.byte = Color.red;
        this.a = Color.yellow;
    }
    
    public f(final f f) {
        this.case = 10;
        this.int = Color.white;
        this.byte = Color.red;
        this.a = Color.yellow;
        if (f != null) {
            this.case = f.case;
            this.goto = f.goto;
            this.int = f.int;
            this.byte = f.byte;
            this.a = f.a;
            this.void = f.void;
            this.try = f.try;
            this.for = f.for;
            this.null = f.null;
            this.c = f.c;
            this.b = f.b;
            this.char = f.char;
            this.do = f.do;
            this.d = f.d;
            this.if = f.if;
        }
    }
}
