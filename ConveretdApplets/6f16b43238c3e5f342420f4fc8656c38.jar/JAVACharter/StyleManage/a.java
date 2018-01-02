// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.StyleManage;

import java.awt.Color;
import java.util.Vector;

public class a
{
    public int f;
    public int if;
    public int p;
    public int int;
    public int for;
    public int try;
    public int m;
    public int char;
    public int new;
    public int e;
    public int d;
    public String else;
    public String o;
    public String k;
    public boolean do;
    public boolean null;
    public Vector case;
    public Color a;
    public Color j;
    public Color byte;
    public Color void;
    public Color g;
    public int long;
    public int goto;
    public int i;
    public int h;
    public Color n;
    public int l;
    public String c;
    private boolean b;
    
    public a(final int f, final String else1, final int e, final int d, final int new1, final int char1) {
        this.if = 0;
        this.p = 0;
        this.int = 0;
        this.for = 0;
        this.try = 0;
        this.m = 0;
        this.char = 0;
        this.new = 0;
        this.e = 0;
        this.d = 0;
        this.null = false;
        this.case = new Vector();
        this.a = new Color(255, 255, 255);
        this.j = new Color(255, 255, 255);
        this.byte = new Color(255, 255, 255);
        this.void = new Color(0, 0, 0);
        this.g = new Color(0, 0, 0);
        this.n = new Color(0, 0, 0);
        this.c = "verysmallfont";
        this.b = false;
        this.f = f;
        this.else = else1;
        this.e = e;
        this.d = d;
        this.new = new1;
        this.char = char1;
        this.long = e + 4;
        this.goto = d - 2;
        this.i = 7;
        this.h = 5;
        this.l = char1 + e;
        this.c = this.c;
    }
    
    public void do(final Color j) {
        this.j = j;
    }
    
    public void a(final int long1, final int goto1, final int i, final int h, final int l, final String c, final Color n) {
        this.long = long1;
        this.goto = goto1;
        this.i = i;
        this.h = h;
        this.l = l;
        this.c = c;
        this.n = n;
    }
    
    public boolean a() {
        return this.null;
    }
    
    public void for(final Color byte1) {
        this.byte = byte1;
    }
    
    public void a(final Color void1) {
        this.void = void1;
    }
    
    public void if(final Color g) {
        this.g = g;
    }
    
    public boolean if() {
        return this.b;
    }
    
    public void a(final boolean b) {
        this.b = b;
    }
}
