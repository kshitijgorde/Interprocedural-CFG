// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bl
{
    protected boolean k;
    protected ae void;
    protected ac j;
    protected a2 d;
    protected boolean z;
    protected boolean h;
    protected ax x;
    protected a8[] else;
    protected int p;
    public l i;
    public boolean long;
    public String B;
    protected int c;
    protected char[][] f;
    protected char[][][] try;
    int C;
    String e;
    String new;
    String s;
    char[] m;
    char[] goto;
    a2 b;
    static final char[] t;
    static final char[] char;
    static final char[] v;
    static final char[] o;
    static final char[] l;
    static final char[] u;
    static final char[] y;
    static final char[] byte;
    static final char[] if;
    static final char[] q;
    static final char[] do;
    static final char[] r;
    static final char[] case;
    static final char[] w;
    static final char[] int;
    static final char[] n;
    static final char[] g;
    static final char[] for;
    static final char[] a;
    static final char[] A;
    
    static {
        t = new char[] { 'o', 'n', 'd', 'i', 's', 'p', 'l', 'a', 'y', '(', ')', '\0' };
        char = new char[] { 'a', 'p', 'p', 'l', 'e', 't', '\0' };
        v = new char[] { 'a', 'p', 'p', 'l', 'e', 't', 'p', 'a', 'r', 'a', 'm', '\0' };
        o = new char[] { 'm', 'o', 'u', 's', 'e', '\0' };
        l = new char[] { 'o', 'n', 'u', 'p', 'd', 'a', 't', 'e', '(', ')', '\0' };
        u = new char[] { 'a', 'u', 't', 'h', 'o', 'r', '\0' };
        y = new char[] { 'c', 'o', 'p', 'y', 'r', 'i', 'g', 'h', 't', '\0' };
        byte = new char[] { 'l', 'i', 'c', 'e', 'n', 'c', 'e', '\0' };
        if = new char[] { 'l', 'i', 'c', 'e', 'n', 's', 'e', '\0' };
        q = new char[] { 't', 'i', 't', 'l', 'e', '\0' };
        do = new char[] { 'd', 'e', 's', 'c', 'r', 'i', 'p', 't', 'i', 'o', 'n', '\0' };
        r = new char[] { 'f', 'u', 'l', 'l', 's', 'c', 'r', 'e', 'e', 'n', '\0' };
        case = new char[] { 'f', 'u', 'l', 'l', 's', 'c', 'r', 'e', 'e', 'n', '2', '\0' };
        w = new char[] { 's', 'm', 'o', 'o', 't', 'h', 's', 't', 'o', 'p', '\0' };
        int = new char[] { 'a', 'n', 't', 'i', 'a', 'l', 'i', 'a', 's', 'i', 'n', 'g', '\0' };
        n = new char[] { 'n', 'o', 'n', 'e', '\0' };
        g = new char[] { 'e', 'v', 'e', 'r', 'y', 't', 'i', 'm', 'e', '\0' };
        for = new char[] { 'o', 'n', 's', 't', 'o', 'p', '\0' };
        a = new char[] { 'm', 'o', 'u', 's', 'e', 's', 'p', 'e', 'e', 'd', '\0' };
        A = new char[] { 't', 'r', 'a', 'n', 's', 'i', 't', 'i', 'o', 'n', '\0' };
    }
    
    public bl() {
        this.k = false;
        this.void = null;
        this.j = null;
        this.d = null;
        this.z = false;
        this.h = false;
        this.x = null;
        this.else = null;
        this.p = 0;
        this.long = false;
        this.B = null;
        this.c = 0;
        this.f = null;
        this.try = null;
        this.C = 0;
        this.e = "";
        this.new = "";
        this.s = "";
        this.m = new char[1];
        this.goto = new char[1];
        this.b = null;
    }
    
    protected void if(final a2 a2) {
        a2 a3 = a2.if;
        this.C = 0;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("group") == 0) {
                ++this.C;
            }
            a3 = a3.for;
        }
        this.f = new char[this.C][];
        this.try = new char[this.C][][];
        a2 a4 = a2.if;
        int n = 0;
        while (a4 != null) {
            if (a4.a.toLowerCase().compareTo("group") == 0) {
                for (int i = 0; i < a4.do; ++i) {
                    if (a4.try[i].toLowerCase().compareTo("id") == 0) {
                        this.f[n] = (String.valueOf(a4.new[i]) + "\u0000").toCharArray();
                    }
                }
                if (this.f[n] == null) {
                    this.f[n] = "\u0000".toCharArray();
                }
                int n2 = 0;
                for (a2 a5 = a4.if; a5 != null; a5 = a5.for) {
                    if (a5.a.toLowerCase().compareTo("id") == 0) {
                        ++n2;
                    }
                }
                this.try[n] = new char[n2][];
                a2 a6 = a4.if;
                int n3 = 0;
                while (a6 != null) {
                    if (a6.a.toLowerCase().compareTo("id") == 0 && a6.case != null) {
                        this.try[n][n3] = (String.valueOf(a6.case.do.trim()) + "\u0000").toCharArray();
                        ++n3;
                    }
                    a6 = a6.for;
                }
                ++n;
            }
            a4 = a4.for;
        }
    }
    
    protected void a(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("description") == 0 && a3.case != null) {
                this.goto = (String.valueOf(a3.case.do) + "\u0000").toCharArray();
            }
            else if (a3.a.toLowerCase().compareTo("title") == 0 && a3.case != null) {
                this.m = (String.valueOf(a3.case.do) + "\u0000").toCharArray();
            }
            else if (a3.a.toLowerCase().compareTo("copyright") == 0 && a3.case != null) {
                this.e = a3.case.do;
            }
            else if (a3.a.toLowerCase().compareTo("author") == 0 && a3.case != null) {
                this.new = a3.case.do;
            }
            else if ((a3.a.toLowerCase().compareTo("licence") == 0 || a3.a.toLowerCase().compareTo("license") == 0) && a3.case != null) {
                this.s = a3.case.do;
            }
        }
    }
    
    void a(final a8 a8) {
        if (this.else == null || this.else.length == this.p) {
            final a8[] else1 = new a8[this.p + 10];
            for (int i = 0; i < this.p; ++i) {
                else1[i] = this.else[i];
            }
            this.else = else1;
        }
        this.else[this.p] = a8;
        ++this.p;
    }
    
    protected void if() {
        if (this.x == null) {
            try {
                final j j = new j();
                this.x = new ax(this);
                j.a("\u0000".toCharArray());
                this.x.a(j);
                this.x.a();
            }
            catch (ar ar) {
                ar.a();
                System.out.print(this.c + this.x.do().try());
                ar.a(this.x.do());
                this.x = null;
            }
        }
    }
    
    protected void a(final char[] array, final a3 a3) {
    }
    
    protected void if(final char[] array, final a3 a3) {
    }
    
    protected a3 a(final char[] array, final char[] array2) {
        return ac.a(new a3());
    }
    
    protected a3 do(final char[] array, final char[] array2) {
        return ac.a(new a3());
    }
    
    public void a(final char[] array, final char[] array2, final a3 a3) {
        for (int i = 0; i < this.C; ++i) {
            if (a.a.a.a.g.do(array, this.f[i]) == 0) {
                for (int j = 0; j < this.try[i].length; ++j) {
                    for (int k = 0; k < this.p; ++k) {
                        if (a.a.a.a.g.do(this.try[i][j], this.else[k].f) == 0) {
                            this.else[k].a(array2, a3);
                        }
                    }
                }
            }
        }
        for (int l = 0; l < this.p; ++l) {
            if (a.a.a.a.g.do(array, this.else[l].f) == 0) {
                this.else[l].a(array2, a3);
                return;
            }
        }
        if (a.a.a.a.g.do(array, bl.char) == 0) {
            if (a.a.a.a.g.do(array2, bl.r) == 0) {
                if (a3.char == 1) {
                    this.i.D = false;
                    if (!a3.long) {
                        this.i.a = false;
                    }
                    else {
                        this.i.a = true;
                    }
                    this.i.a(true);
                }
                this.long = true;
            }
            else if (a.a.a.a.g.do(array2, bl.case) == 0) {
                if (a3.char == 1) {
                    this.i.a = false;
                    if (!a3.long) {
                        this.i.D = false;
                    }
                    else {
                        this.i.D = true;
                    }
                    this.i.a(true);
                }
                this.long = true;
            }
            else if (a.a.a.a.g.do(array2, bl.int) == 0) {
                if (a3.char == 4) {
                    if (a.a.a.a.g.do(a3.int, bl.n) == 0) {
                        this.i.m = 0;
                    }
                    if (a.a.a.a.g.do(a3.int, bl.for) == 0) {
                        this.i.m = 1;
                    }
                    if (a.a.a.a.g.do(a3.int, bl.g) == 0) {
                        this.i.m = 2;
                    }
                }
                this.long = true;
            }
            else if (a.a.a.a.g.do(array2, bl.a) == 0) {
                if (a3.char == 4) {
                    if (a.a.a.a.g.do(a3.int, bl.n) == 0) {
                        this.i.m = 0;
                    }
                    if (a.a.a.a.g.do(a3.int, bl.for) == 0) {
                        this.i.m = 1;
                    }
                    if (a.a.a.a.g.do(a3.int, bl.g) == 0) {
                        this.i.m = 2;
                    }
                }
                float for1 = al.do(a3) / 100.0f;
                if (for1 < 0.01 || for1 > 100.0f) {
                    for1 = 1.0f;
                }
                this.i.for = for1;
            }
            else if (a.a.a.a.g.do(array2, bl.A) == 0) {
                if (a3.char == 4) {
                    this.i.B = new String(a3.int, 0, a.a.a.a.g.a(a3.int));
                }
            }
            else if (a.a.a.a.g.do(array2, bl.w) == 0) {
                float g = -19582.0f;
                if (a3.char == 3) {
                    g = (float)a3.else;
                }
                else if (a3.char == 2) {
                    g = a3.case;
                }
                if (g != -19582.0f) {
                    ac.g = g;
                    if (ac.g > 0.0f) {
                        ac.g = 1.0f - g / 1000.0f;
                    }
                    if (ac.g >= 1.0f || ac.g < 0.0f) {
                        ac.g = 0.0f;
                    }
                }
            }
        }
        else if (a.a.a.a.g.do(array, ac.r) == 0) {
            this.a(array2, a3);
        }
        else if (a.a.a.a.g.do(array, ac.V) == 0) {
            this.if(array2, a3);
        }
        else if (a.a.a.a.g.do(array, ac.new) == 0) {
            for (int n = 0; n < this.p; ++n) {
                if (this.else[n].int == 1) {
                    this.else[n].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.do(array, ac.H) == 0) {
            for (int n2 = 0; n2 < this.p; ++n2) {
                if (this.else[n2].int == 2) {
                    this.else[n2].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.do(array, ac.U) == 0) {
            for (int n3 = 0; n3 < this.p; ++n3) {
                if (this.else[n3].int == 3) {
                    this.else[n3].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.do(array, ac.q) == 0) {
            for (int n4 = 0; n4 < this.p; ++n4) {
                if (this.else[n4].int == 4) {
                    this.else[n4].a(array2, a3);
                }
            }
        }
    }
    
    public a3 if(final char[] array, final char[] array2) {
        if (a.a.a.a.g.do(array, ac.r) == 0) {
            return this.a(array, array2);
        }
        if (a.a.a.a.g.do(array, ac.V) == 0) {
            return this.do(array, array2);
        }
        if (a.a.a.a.g.do(array, bl.char) == 0) {
            final a3 a3 = new a3();
            if (a.a.a.a.g.do(array2, ac.u) == 0) {
                a3.char = 2;
                a3.case = this.i.width;
                return a3;
            }
            if (a.a.a.a.g.do(array2, ac.as) == 0) {
                a3.char = 2;
                a3.case = this.i.height;
                return a3;
            }
            if (a.a.a.a.g.do(array2, bl.r) == 0) {
                a3.char = 1;
                a3.long = this.i.a;
                return a3;
            }
            if (a.a.a.a.g.do(array2, bl.A) == 0) {
                a3.char = 4;
                a3.int = (String.valueOf(this.i.B) + "\u0000").toCharArray();
                return a3;
            }
        }
        else {
            if (a.a.a.a.g.do(array, bl.v) == 0) {
                final a3 a4 = new a3();
                a4.char = 4;
                final String parameter = this.i.getParameter(new String(array2, 0, a.a.a.a.g.a(array2)));
                if (parameter != null) {
                    a4.int = (String.valueOf(parameter) + "\u0000").toCharArray();
                }
                else {
                    a4.int = "\u0000".toCharArray();
                }
                return a4;
            }
            if (a.a.a.a.g.do(array, bl.o) == 0) {
                final a3 a5 = new a3();
                if (a.a.a.a.g.do(array2, ac.ad) == 0) {
                    a5.char = 2;
                    a5.case = this.i.K.goto;
                    return a5;
                }
                if (a.a.a.a.g.do(array2, ac.ac) == 0) {
                    a5.char = 2;
                    a5.case = this.i.K.else;
                    return a5;
                }
            }
            else {
                if (a.a.a.a.g.do(array, bl.u) == 0) {
                    final a3 a6 = new a3();
                    a6.char = 4;
                    a6.int = (String.valueOf(this.new) + "\u0000").toCharArray();
                    return a6;
                }
                if (a.a.a.a.g.do(array, bl.byte) == 0 || a.a.a.a.g.do(array, bl.if) == 0) {
                    final a3 a7 = new a3();
                    a7.char = 4;
                    a7.int = (String.valueOf(this.s) + "\u0000").toCharArray();
                    return a7;
                }
                if (a.a.a.a.g.do(array, bl.y) == 0) {
                    final a3 a8 = new a3();
                    a8.char = 4;
                    a8.int = (String.valueOf(this.e) + "\u0000").toCharArray();
                    return a8;
                }
                if (a.a.a.a.g.do(array, bl.do) == 0) {
                    final a3 a9 = new a3();
                    a9.char = 4;
                    a9.int = this.goto;
                    return a9;
                }
                if (a.a.a.a.g.do(array, bl.q) == 0) {
                    final a3 a10 = new a3();
                    a10.char = 4;
                    a10.int = this.m;
                    return a10;
                }
                if (this.b != null && a.a.a.a.g.a(array) > 0 && (array[0] == 'g' || array[0] == 'G') && (array[1] == 'p' || array[1] == 'P') && (array[2] == 's' || array[2] == 'S')) {
                    final String lowerCase = new String(array, 0, a.a.a.a.g.a(array)).toLowerCase();
                    final String lowerCase2 = new String(array2, 0, a.a.a.a.g.a(array2)).toLowerCase();
                    if (this.b != null) {
                        final a3 a11 = this.a(this.b, lowerCase, lowerCase2);
                        if (a11 != null) {
                            return a11;
                        }
                    }
                }
            }
        }
        int i = 0;
        while (i < this.p) {
            if (a.a.a.a.g.do(array, this.else[i].f) == 0) {
                if (a.a.a.a.g.do(array2, ac.do) == 0) {
                    final a3 a12 = new a3();
                    a12.char = 4;
                    a12.int = this.else[i].case;
                    return a12;
                }
                return this.else[i].a(array2);
            }
            else {
                ++i;
            }
        }
        final a3 a13 = new a3();
        a13.char = 4;
        return ac.a(a13);
    }
    
    private a3 a(a2 for1, final String s, final String s2) {
        while (for1 != null) {
            if (for1.a.toLowerCase().compareTo(s) == 0) {
                if (s2.length() > 0) {
                    for (int i = 0; i < for1.do; ++i) {
                        if (for1.try[i].toLowerCase().compareTo(s2) == 0) {
                            return this.a(for1.new[i]);
                        }
                    }
                }
                else if (for1.case != null && for1.case.do != null) {
                    final a3 a3 = new a3();
                    a3.char = 4;
                    a3.int = (String.valueOf(for1.case.do) + "\u0000").toCharArray();
                    return a3;
                }
            }
            if (for1.if != null) {
                final a3 a4 = this.a(for1.if, s, s2);
                if (a4 != null) {
                    return a4;
                }
            }
            for1 = for1.for;
        }
        return null;
    }
    
    private a3 a(final String s) {
        final a3 a3 = new a3();
        if (s.toLowerCase().compareTo("true") == 0) {
            a3.char = 1;
            a3.long = true;
            return a3;
        }
        if (s.toLowerCase().compareTo("false") == 0) {
            a3.char = 1;
            a3.long = false;
            return a3;
        }
        try {
            final Integer decode = Integer.decode(s);
            a3.char = 2;
            a3.case = decode;
        }
        catch (Exception ex) {
            try {
                final Float n = new Float(s);
                a3.char = 3;
                a3.else = n;
            }
            catch (Exception ex2) {
                a3.char = 4;
                a3.int = (String.valueOf(s) + "\u0000").toCharArray();
            }
        }
        return a3;
    }
    
    public void a(final char[] array, final int n) {
        char[] array2 = null;
        final String s = new String(array, 0, a.a.a.a.g.a(array));
        switch (n) {
            case 0: {
                array2 = ("hotspotentered(\"" + s + "\")\u0000").toCharArray();
                break;
            }
            case 1: {
                array2 = ("hotspotexited(\"" + s + "\")\u0000").toCharArray();
                break;
            }
            case 2: {
                array2 = ("hotspotpressed(\"" + s + "\")\u0000").toCharArray();
                break;
            }
            case 3: {
                array2 = ("hotspotreleased(\"" + s + "\")\u0000").toCharArray();
                break;
            }
            case 4: {
                array2 = ("hotspotclicked(\"" + s + "\")\u0000").toCharArray();
                break;
            }
        }
        this.i.w.if(array2);
        this.i.A.a(array2);
    }
    
    public void a() {
        this.void = null;
        this.j = null;
        this.d = null;
        if (this.x != null) {
            this.x.for();
        }
        this.x = null;
        for (int i = 0; i < this.p; ++i) {
            if (this.else[i] != null) {
                this.else[i].if();
            }
            this.else[i] = null;
        }
        this.p = 0;
        this.else = null;
        this.i = null;
        this.e = null;
        this.new = null;
        this.s = null;
        this.m = null;
        this.goto = null;
        this.b = null;
    }
}
