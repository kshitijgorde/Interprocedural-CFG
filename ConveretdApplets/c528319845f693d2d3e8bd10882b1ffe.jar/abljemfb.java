import java.awt.Component;
import java.awt.Event;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemfb extends Button
{
    abljema a;
    int b;
    int c;
    int d;
    char e;
    int f;
    int g;
    int h;
    int i;
    String j;
    abljemtf k;
    boolean l;
    private int m;
    static int n;
    
    abljemfb(final int n, final byte[] array, final int n2, final abljema abljema) {
        this(n, abljema.c(array, n2 + 1, 2), abljema.c(array, n2 + 3, 3), null, new String(array, 0, n2 + abljemfb.n, n), abljema, null, '?');
    }
    
    abljemfb(final abljema abljema, final String s, final String s2, final abljemtf abljemtf, final char c) {
        this(0, abljemtf.ab, abljemtf.ac, s, s2, abljema, abljemtf, c);
    }
    
    abljemfb(final int n, final int n2, final int n3, final String s, final abljema abljema) {
        this(n, n2, n3, null, s, abljema, null, '?');
    }
    
    abljemfb(final int d, final int b, final int c, String j, final String i, final abljema a, final abljemtf k, final char e) {
        this.l = false;
        this.m = 9;
        this.hide();
        this.a = a;
        this.d = d;
        this.b = b;
        this.c = c;
        this.j = i;
        this.e = e;
        this.k = k;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        if (j == null) {
            if (this.j.equals("ENT")) {
                j = " Enter ";
            }
            else if (this.j.equals("DWN")) {
                j = "<<";
            }
            else if (this.j.equals("UPP")) {
                j = ">>";
            }
            else {
                j = this.j;
            }
        }
        this.setLabel(j);
        if (this.j.equals("Macro") && this.a.fb.e0.h) {
            this.a();
        }
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        String s = null;
        switch (this.j.length()) {
            case 3: {
                s = this.j;
                break;
            }
            case 2: {
                s = this.j.substring(0, 1).concat("0").concat(this.j.substring(1, 2));
                break;
            }
            default: {
                if (this.j.equals("Macro")) {
                    this.a.fb.a(this);
                    return true;
                }
                final String[] e = this.a.fb.e(this.j);
                if (this.k != null && e[0].length() > 0) {
                    this.k.c(e[0]);
                }
                s = e[1];
                break;
            }
        }
        if (this.k != null) {
            this.k.k();
        }
        if (this.a.fb.e0.h) {
            this.a.fb.e0.d(s);
        }
        this.a.fb.g(s);
        return true;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (this.a.b6 && n == 9 && this.a.e3 != 0) {
            final int abs = Math.abs(this.a.e3);
            if (abs > 0 && abs <= this.a.e2) {
                final abljemtf abljemtf = this.a.e0[abs];
                abljemtf.c(this.a.e3 > 0);
                abljemtf.requestFocus();
                return true;
            }
        }
        return super.keyUp(event, n);
    }
    
    public void a() {
        if (this.d < 6) {
            this.hide();
            this.c -= this.m;
            this.d += this.m;
            this.setLabel("Stop Recording");
            this.h = 0;
        }
    }
    
    public void b() {
        if (this.d > 6) {
            this.hide();
            this.c += this.m;
            this.d -= this.m;
            this.setLabel("Macro");
            this.h = 0;
        }
    }
    
    static int a(final byte[] array, final int n, final abljema abljema) {
        final int c = abljema.c(array, n + 6, 2);
        if (!abljema.bu || !abljema.he) {
            if (c < 1 || c > 99) {
                abljem.d("jem error 71, " + c);
            }
            else {
                final abljemfb abljemfb = new abljemfb(c, array, n, abljema);
                if (abljemfb == null) {
                    abljem.d("jem error 72");
                }
                else {
                    abljema.fb.add(abljemfb);
                }
            }
        }
        return abljemfb.n + c;
    }
    
    static {
        abljemfb.n = 8;
    }
}
