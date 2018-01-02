// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class b3
{
    protected boolean k;
    protected aq void;
    protected an j;
    protected bh d;
    protected boolean z;
    protected boolean h;
    protected bb x;
    protected bo[] else;
    protected int p;
    public r i;
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
    bh b;
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
    
    public b3() {
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
    
    protected void if(final bh bh) {
        bh bh2 = bh.if;
        this.C = 0;
        while (bh2 != null) {
            if (bh2.a.toLowerCase().compareTo("group") == 0) {
                ++this.C;
            }
            bh2 = bh2.for;
        }
        this.f = new char[this.C][];
        this.try = new char[this.C][][];
        bh bh3 = bh.if;
        int n = 0;
        while (bh3 != null) {
            if (bh3.a.toLowerCase().compareTo("group") == 0) {
                for (int i = 0; i < bh3.do; ++i) {
                    if (bh3.try[i].toLowerCase().compareTo("id") == 0) {
                        this.f[n] = (String.valueOf(bh3.new[i]) + "\u0000").toCharArray();
                    }
                }
                if (this.f[n] == null) {
                    this.f[n] = "\u0000".toCharArray();
                }
                int n2 = 0;
                for (bh bh4 = bh3.if; bh4 != null; bh4 = bh4.for) {
                    if (bh4.a.toLowerCase().compareTo("id") == 0) {
                        ++n2;
                    }
                }
                this.try[n] = new char[n2][];
                bh bh5 = bh3.if;
                int n3 = 0;
                while (bh5 != null) {
                    if (bh5.a.toLowerCase().compareTo("id") == 0 && bh5.case != null) {
                        this.try[n][n3] = (String.valueOf(bh5.case.do.trim()) + "\u0000").toCharArray();
                        ++n3;
                    }
                    bh5 = bh5.for;
                }
                ++n;
            }
            bh3 = bh3.for;
        }
    }
    
    protected void a(final bh bh) {
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            if (bh2.a.toLowerCase().compareTo("description") == 0 && bh2.case != null) {
                this.goto = (String.valueOf(bh2.case.do) + "\u0000").toCharArray();
            }
            else if (bh2.a.toLowerCase().compareTo("title") == 0 && bh2.case != null) {
                this.m = (String.valueOf(bh2.case.do) + "\u0000").toCharArray();
            }
            else if (bh2.a.toLowerCase().compareTo("copyright") == 0 && bh2.case != null) {
                this.e = bh2.case.do;
            }
            else if (bh2.a.toLowerCase().compareTo("author") == 0 && bh2.case != null) {
                this.new = bh2.case.do;
            }
            else if ((bh2.a.toLowerCase().compareTo("licence") == 0 || bh2.a.toLowerCase().compareTo("license") == 0) && bh2.case != null) {
                this.s = bh2.case.do;
            }
        }
    }
    
    void a(final bo bo) {
        if (this.else == null || this.else.length == this.p) {
            final bo[] else1 = new bo[this.p + 10];
            for (int i = 0; i < this.p; ++i) {
                else1[i] = this.else[i];
            }
            this.else = else1;
        }
        this.else[this.p] = bo;
        ++this.p;
    }
    
    protected void if() {
        if (this.x == null) {
            try {
                final p p = new p();
                this.x = new bb(this);
                p.a("\u0000".toCharArray());
                this.x.a(p);
                this.x.a();
            }
            catch (a5 a5) {
                a5.a();
                System.out.print(this.c + this.x.do().try());
                a5.a(this.x.do());
                this.x = null;
            }
        }
    }
    
    protected void a(final char[] array, final bi bi) {
    }
    
    protected void if(final char[] array, final bi bi) {
    }
    
    protected bi a(final char[] array, final char[] array2) {
        return an.a(new bi());
    }
    
    protected bi do(final char[] array, final char[] array2) {
        return an.a(new bi());
    }
    
    public void a(final char[] array, final char[] array2, final bi bi) {
        for (int i = 0; i < this.C; ++i) {
            if (a.a.a.a.i.do(array, this.f[i]) == 0) {
                for (int j = 0; j < this.try[i].length; ++j) {
                    for (int k = 0; k < this.p; ++k) {
                        if (a.a.a.a.i.do(this.try[i][j], this.else[k].f) == 0) {
                            this.else[k].a(array2, bi);
                        }
                    }
                }
            }
        }
        for (int l = 0; l < this.p; ++l) {
            if (a.a.a.a.i.do(array, this.else[l].f) == 0) {
                this.else[l].a(array2, bi);
                return;
            }
        }
        if (a.a.a.a.i.do(array, b3.char) == 0) {
            if (a.a.a.a.i.do(array2, b3.r) == 0) {
                if (bi.char == 1) {
                    this.i.D = false;
                    if (!bi.long) {
                        this.i.a = false;
                    }
                    else {
                        this.i.a = true;
                    }
                    this.i.a(true);
                }
                this.long = true;
            }
            else if (a.a.a.a.i.do(array2, b3.case) == 0) {
                if (bi.char == 1) {
                    this.i.a = false;
                    if (!bi.long) {
                        this.i.D = false;
                    }
                    else {
                        this.i.D = true;
                    }
                    this.i.a(true);
                }
                this.long = true;
            }
            else if (a.a.a.a.i.do(array2, b3.int) == 0) {
                if (bi.char == 4) {
                    if (a.a.a.a.i.do(bi.int, b3.n) == 0) {
                        this.i.m = 0;
                    }
                    if (a.a.a.a.i.do(bi.int, b3.for) == 0) {
                        this.i.m = 1;
                    }
                    if (a.a.a.a.i.do(bi.int, b3.g) == 0) {
                        this.i.m = 2;
                    }
                }
                this.long = true;
            }
            else if (a.a.a.a.i.do(array2, b3.a) == 0) {
                if (bi.char == 4) {
                    if (a.a.a.a.i.do(bi.int, b3.n) == 0) {
                        this.i.m = 0;
                    }
                    if (a.a.a.a.i.do(bi.int, b3.for) == 0) {
                        this.i.m = 1;
                    }
                    if (a.a.a.a.i.do(bi.int, b3.g) == 0) {
                        this.i.m = 2;
                    }
                }
                float for1 = az.do(bi) / 100.0f;
                if (for1 < 0.01 || for1 > 100.0f) {
                    for1 = 1.0f;
                }
                this.i.for = for1;
            }
            else if (a.a.a.a.i.do(array2, b3.A) == 0) {
                if (bi.char == 4) {
                    this.i.B = new String(bi.int, 0, a.a.a.a.i.a(bi.int));
                }
            }
            else if (a.a.a.a.i.do(array2, b3.w) == 0) {
                float g = -19582.0f;
                if (bi.char == 3) {
                    g = (float)bi.else;
                }
                else if (bi.char == 2) {
                    g = bi.case;
                }
                if (g != -19582.0f) {
                    an.g = g;
                    if (an.g > 0.0f) {
                        an.g = 1.0f - g / 1000.0f;
                    }
                    if (an.g >= 1.0f || an.g < 0.0f) {
                        an.g = 0.0f;
                    }
                }
            }
        }
        else if (a.a.a.a.i.do(array, an.r) == 0) {
            this.a(array2, bi);
        }
        else if (a.a.a.a.i.do(array, an.V) == 0) {
            this.if(array2, bi);
        }
        else if (a.a.a.a.i.do(array, an.new) == 0) {
            for (int n = 0; n < this.p; ++n) {
                if (this.else[n].int == 1) {
                    this.else[n].a(array2, bi);
                }
            }
            return;
        }
        if (a.a.a.a.i.do(array, an.H) == 0) {
            for (int n2 = 0; n2 < this.p; ++n2) {
                if (this.else[n2].int == 2) {
                    this.else[n2].a(array2, bi);
                }
            }
            return;
        }
        if (a.a.a.a.i.do(array, an.U) == 0) {
            for (int n3 = 0; n3 < this.p; ++n3) {
                if (this.else[n3].int == 3) {
                    this.else[n3].a(array2, bi);
                }
            }
            return;
        }
        if (a.a.a.a.i.do(array, an.q) == 0) {
            for (int n4 = 0; n4 < this.p; ++n4) {
                if (this.else[n4].int == 4) {
                    this.else[n4].a(array2, bi);
                }
            }
        }
    }
    
    public bi if(final char[] array, final char[] array2) {
        if (a.a.a.a.i.do(array, an.r) == 0) {
            return this.a(array, array2);
        }
        if (a.a.a.a.i.do(array, an.V) == 0) {
            return this.do(array, array2);
        }
        if (a.a.a.a.i.do(array, b3.char) == 0) {
            final bi bi = new bi();
            if (a.a.a.a.i.do(array2, an.u) == 0) {
                bi.char = 2;
                bi.case = this.i.width;
                return bi;
            }
            if (a.a.a.a.i.do(array2, an.as) == 0) {
                bi.char = 2;
                bi.case = this.i.height;
                return bi;
            }
            if (a.a.a.a.i.do(array2, b3.r) == 0) {
                bi.char = 1;
                bi.long = this.i.a;
                return bi;
            }
            if (a.a.a.a.i.do(array2, b3.A) == 0) {
                bi.char = 4;
                bi.int = (String.valueOf(this.i.B) + "\u0000").toCharArray();
                return bi;
            }
        }
        else {
            if (a.a.a.a.i.do(array, b3.v) == 0) {
                final bi bi2 = new bi();
                bi2.char = 4;
                final String parameter = this.i.getParameter(new String(array2, 0, a.a.a.a.i.a(array2)));
                if (parameter != null) {
                    bi2.int = (String.valueOf(parameter) + "\u0000").toCharArray();
                }
                else {
                    bi2.int = "\u0000".toCharArray();
                }
                return bi2;
            }
            if (a.a.a.a.i.do(array, b3.o) == 0) {
                final bi bi3 = new bi();
                if (a.a.a.a.i.do(array2, an.ad) == 0) {
                    bi3.char = 2;
                    bi3.case = this.i.K.goto;
                    return bi3;
                }
                if (a.a.a.a.i.do(array2, an.ac) == 0) {
                    bi3.char = 2;
                    bi3.case = this.i.K.else;
                    return bi3;
                }
            }
            else {
                if (a.a.a.a.i.do(array, b3.u) == 0) {
                    final bi bi4 = new bi();
                    bi4.char = 4;
                    bi4.int = (String.valueOf(this.new) + "\u0000").toCharArray();
                    return bi4;
                }
                if (a.a.a.a.i.do(array, b3.byte) == 0 || a.a.a.a.i.do(array, b3.if) == 0) {
                    final bi bi5 = new bi();
                    bi5.char = 4;
                    bi5.int = (String.valueOf(this.s) + "\u0000").toCharArray();
                    return bi5;
                }
                if (a.a.a.a.i.do(array, b3.y) == 0) {
                    final bi bi6 = new bi();
                    bi6.char = 4;
                    bi6.int = (String.valueOf(this.e) + "\u0000").toCharArray();
                    return bi6;
                }
                if (a.a.a.a.i.do(array, b3.do) == 0) {
                    final bi bi7 = new bi();
                    bi7.char = 4;
                    bi7.int = this.goto;
                    return bi7;
                }
                if (a.a.a.a.i.do(array, b3.q) == 0) {
                    final bi bi8 = new bi();
                    bi8.char = 4;
                    bi8.int = this.m;
                    return bi8;
                }
                if (this.b != null && a.a.a.a.i.a(array) > 0 && (array[0] == 'g' || array[0] == 'G') && (array[1] == 'p' || array[1] == 'P') && (array[2] == 's' || array[2] == 'S')) {
                    final String lowerCase = new String(array, 0, a.a.a.a.i.a(array)).toLowerCase();
                    final String lowerCase2 = new String(array2, 0, a.a.a.a.i.a(array2)).toLowerCase();
                    if (this.b != null) {
                        final bi a = this.a(this.b, lowerCase, lowerCase2);
                        if (a != null) {
                            return a;
                        }
                    }
                }
            }
        }
        int i = 0;
        while (i < this.p) {
            if (a.a.a.a.i.do(array, this.else[i].f) == 0) {
                if (a.a.a.a.i.do(array2, an.do) == 0) {
                    final bi bi9 = new bi();
                    bi9.char = 4;
                    bi9.int = this.else[i].case;
                    return bi9;
                }
                return this.else[i].a(array2);
            }
            else {
                ++i;
            }
        }
        final bi bi10 = new bi();
        bi10.char = 4;
        return an.a(bi10);
    }
    
    private bi a(bh for1, final String s, final String s2) {
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
                    final bi bi = new bi();
                    bi.char = 4;
                    bi.int = (String.valueOf(for1.case.do) + "\u0000").toCharArray();
                    return bi;
                }
            }
            if (for1.if != null) {
                final bi a = this.a(for1.if, s, s2);
                if (a != null) {
                    return a;
                }
            }
            for1 = for1.for;
        }
        return null;
    }
    
    private bi a(final String s) {
        final bi bi = new bi();
        if (s.toLowerCase().compareTo("true") == 0) {
            bi.char = 1;
            bi.long = true;
            return bi;
        }
        if (s.toLowerCase().compareTo("false") == 0) {
            bi.char = 1;
            bi.long = false;
            return bi;
        }
        try {
            final Integer decode = Integer.decode(s);
            bi.char = 2;
            bi.case = decode;
        }
        catch (Exception ex) {
            try {
                final Float n = new Float(s);
                bi.char = 3;
                bi.else = n;
            }
            catch (Exception ex2) {
                bi.char = 4;
                bi.int = (String.valueOf(s) + "\u0000").toCharArray();
            }
        }
        return bi;
    }
    
    public void a(final char[] array, final int n) {
        char[] array2 = null;
        final String s = new String(array, 0, a.a.a.a.i.a(array));
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
