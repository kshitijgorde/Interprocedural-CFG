// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bl
{
    protected boolean m;
    protected ae n;
    protected ac q;
    protected a2 d;
    protected boolean goto;
    protected boolean l;
    protected ax case;
    protected a8[] i;
    protected int r;
    public l h;
    public boolean do;
    String if;
    String int;
    String s;
    char[] v;
    char[] k;
    a2 f;
    static final char[] byte;
    static final char[] char;
    static final char[] a;
    static final char[] p;
    static final char[] new;
    static final char[] try;
    static final char[] void;
    static final char[] t;
    static final char[] c;
    static final char[] b;
    static final char[] for;
    static final char[] o;
    static final char[] else;
    static final char[] long;
    static final char[] e;
    static final char[] g;
    static final char[] j;
    static final char[] u;
    
    static {
        byte = new char[] { 'o', 'n', 'd', 'i', 's', 'p', 'l', 'a', 'y', '(', ')', '\0' };
        char = new char[] { 'a', 'p', 'p', 'l', 'e', 't', '\0' };
        a = new char[] { 'm', 'o', 'u', 's', 'e', '\0' };
        p = new char[] { 'o', 'n', 'u', 'p', 'd', 'a', 't', 'e', '(', ')', '\0' };
        new = new char[] { 'a', 'u', 't', 'h', 'o', 'r', '\0' };
        try = new char[] { 'c', 'o', 'p', 'y', 'r', 'i', 'g', 'h', 't', '\0' };
        void = new char[] { 'l', 'i', 'c', 'e', 'n', 'c', 'e', '\0' };
        t = new char[] { 'l', 'i', 'c', 'e', 'n', 's', 'e', '\0' };
        c = new char[] { 't', 'i', 't', 'l', 'e', '\0' };
        b = new char[] { 'd', 'e', 's', 'c', 'r', 'i', 'p', 't', 'i', 'o', 'n', '\0' };
        for = new char[] { 'f', 'u', 'l', 'l', 's', 'c', 'r', 'e', 'e', 'n', '\0' };
        o = new char[] { 'f', 'u', 'l', 'l', 's', 'c', 'r', 'e', 'e', 'n', '2', '\0' };
        else = new char[] { 'a', 'n', 't', 'i', 'a', 'l', 'i', 'a', 's', 'i', 'n', 'g', '\0' };
        long = new char[] { 'n', 'o', 'n', 'e', '\0' };
        e = new char[] { 'e', 'v', 'e', 'r', 'y', 't', 'i', 'm', 'e', '\0' };
        g = new char[] { 'o', 'n', 's', 't', 'o', 'p', '\0' };
        j = new char[] { 'm', 'o', 'u', 's', 'e', 's', 'p', 'e', 'e', 'd', '\0' };
        u = new char[] { 't', 'r', 'a', 'n', 's', 'i', 't', 'i', 'o', 'n', '\0' };
    }
    
    public bl() {
        this.m = false;
        this.n = null;
        this.q = null;
        this.d = null;
        this.goto = false;
        this.l = false;
        this.case = null;
        this.i = null;
        this.r = 0;
        this.do = false;
        this.if = "";
        this.int = "";
        this.s = "";
        this.v = new char[1];
        this.k = new char[1];
        this.f = null;
    }
    
    protected void a(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("description") == 0) {
                this.k = (String.valueOf(a3.case.if) + "\u0000").toCharArray();
            }
            else if (a3.a.toLowerCase().compareTo("title") == 0) {
                this.v = (String.valueOf(a3.case.if) + "\u0000").toCharArray();
            }
            else if (a3.a.toLowerCase().compareTo("copyright") == 0) {
                this.if = a3.case.if;
            }
            else if (a3.a.toLowerCase().compareTo("author") == 0) {
                this.int = a3.case.if;
            }
            else if (a3.a.toLowerCase().compareTo("licence") == 0 || a3.a.toLowerCase().compareTo("license") == 0) {
                this.s = a3.case.if;
            }
        }
    }
    
    void a(final a8 a8) {
        if (this.i == null || this.i.length == this.r) {
            final a8[] i = new a8[this.r + 10];
            for (int j = 0; j < this.r; ++j) {
                i[j] = this.i[j];
            }
            this.i = i;
        }
        this.i[this.r] = a8;
        ++this.r;
    }
    
    protected void if() {
        if (this.case == null) {
            try {
                final j j = new j();
                this.case = new ax(this);
                j.a("\u0000".toCharArray());
                this.case.a(j);
                this.case.a();
            }
            catch (ar ar) {
                ar.a(this.case.for());
                this.case = null;
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
        for (int i = 0; i < this.r; ++i) {
            if (a.a.a.a.g.if(array, this.i[i].e) == 0) {
                this.i[i].a(array2, a3);
                return;
            }
        }
        if (a.a.a.a.g.if(array, bl.char) == 0) {
            if (a.a.a.a.g.if(array2, bl.for) == 0) {
                if (a3.char == 1) {
                    this.h.A = false;
                    if (!a3.long) {
                        this.h.if = false;
                    }
                    else {
                        this.h.if = true;
                    }
                    this.h.if();
                }
                this.do = true;
            }
            else if (a.a.a.a.g.if(array2, bl.o) == 0) {
                if (a3.char == 1) {
                    this.h.if = false;
                    if (!a3.long) {
                        this.h.A = false;
                    }
                    else {
                        this.h.A = true;
                    }
                    this.h.if();
                }
                this.do = true;
            }
            else if (a.a.a.a.g.if(array2, bl.else) == 0) {
                if (a3.char == 4) {
                    if (a.a.a.a.g.if(a3.int, bl.long) == 0) {
                        this.h.g = 0;
                    }
                    if (a.a.a.a.g.if(a3.int, bl.g) == 0) {
                        this.h.g = 1;
                    }
                    if (a.a.a.a.g.if(a3.int, bl.e) == 0) {
                        this.h.g = 2;
                    }
                }
                this.do = true;
            }
            else if (a.a.a.a.g.if(array2, bl.j) == 0) {
                if (a3.char == 4) {
                    if (a.a.a.a.g.if(a3.int, bl.long) == 0) {
                        this.h.g = 0;
                    }
                    if (a.a.a.a.g.if(a3.int, bl.g) == 0) {
                        this.h.g = 1;
                    }
                    if (a.a.a.a.g.if(a3.int, bl.e) == 0) {
                        this.h.g = 2;
                    }
                }
                float try1 = al.do(a3) / 100.0f;
                if (try1 < 0.01 || try1 > 100.0f) {
                    try1 = 1.0f;
                }
                this.h.try = try1;
            }
            else if (a.a.a.a.g.if(array2, bl.u) == 0 && a3.char == 4) {
                this.h.w = new String(a3.int, 0, a.a.a.a.g.a(a3.int));
            }
        }
        else if (a.a.a.a.g.if(array, ac.m) == 0) {
            this.a(array2, a3);
        }
        else if (a.a.a.a.g.if(array, ac.O) == 0) {
            this.if(array2, a3);
        }
        else if (a.a.a.a.g.if(array, ac.int) == 0) {
            for (int j = 0; j < this.r; ++j) {
                if (this.i[j].for == 1) {
                    this.i[j].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.if(array, ac.B) == 0) {
            for (int k = 0; k < this.r; ++k) {
                if (this.i[k].for == 2) {
                    this.i[k].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.if(array, ac.N) == 0) {
            for (int l = 0; l < this.r; ++l) {
                if (this.i[l].for == 3) {
                    this.i[l].a(array2, a3);
                }
            }
            return;
        }
        if (a.a.a.a.g.if(array, ac.l) == 0) {
            for (int n = 0; n < this.r; ++n) {
                if (this.i[n].for == 4) {
                    this.i[n].a(array2, a3);
                }
            }
        }
    }
    
    public a3 if(final char[] array, final char[] array2) {
        if (a.a.a.a.g.if(array, ac.m) == 0) {
            return this.a(array, array2);
        }
        if (a.a.a.a.g.if(array, ac.O) == 0) {
            return this.do(array, array2);
        }
        if (a.a.a.a.g.if(array, bl.char) == 0) {
            final a3 a3 = new a3();
            if (a.a.a.a.g.if(array2, ac.p) == 0) {
                a3.char = 2;
                a3.case = this.h.width;
                return a3;
            }
            if (a.a.a.a.g.if(array2, ac.aj) == 0) {
                a3.char = 2;
                a3.case = this.h.height;
                return a3;
            }
            if (a.a.a.a.g.if(array2, bl.for) == 0) {
                a3.char = 1;
                a3.long = this.h.if;
                return a3;
            }
            if (a.a.a.a.g.if(array2, bl.u) == 0) {
                a3.char = 4;
                a3.int = (String.valueOf(this.h.w) + "\u0000").toCharArray();
                return a3;
            }
        }
        if (a.a.a.a.g.if(array, bl.a) == 0) {
            final a3 a4 = new a3();
            if (a.a.a.a.g.if(array2, ac.V) == 0) {
                a4.char = 2;
                a4.case = this.h.I.goto;
                return a4;
            }
            if (a.a.a.a.g.if(array2, ac.U) == 0) {
                a4.char = 2;
                a4.case = this.h.I.else;
                return a4;
            }
        }
        if (a.a.a.a.g.if(array, bl.new) == 0) {
            final a3 a5 = new a3();
            a5.char = 4;
            a5.int = (String.valueOf(this.int) + "\u0000").toCharArray();
            return a5;
        }
        if (a.a.a.a.g.if(array, bl.void) == 0 || a.a.a.a.g.if(array, bl.t) == 0) {
            final a3 a6 = new a3();
            a6.char = 4;
            a6.int = (String.valueOf(this.s) + "\u0000").toCharArray();
            return a6;
        }
        if (a.a.a.a.g.if(array, bl.try) == 0) {
            final a3 a7 = new a3();
            a7.char = 4;
            a7.int = (String.valueOf(this.if) + "\u0000").toCharArray();
            return a7;
        }
        if (a.a.a.a.g.if(array, bl.b) == 0) {
            final a3 a8 = new a3();
            a8.char = 4;
            a8.int = this.k;
            return a8;
        }
        if (a.a.a.a.g.if(array, bl.c) == 0) {
            final a3 a9 = new a3();
            a9.char = 4;
            a9.int = this.v;
            return a9;
        }
        if (this.f != null && a.a.a.a.g.a(array) > 0 && (array[0] == 'g' || array[0] == 'G') && (array[1] == 'p' || array[1] == 'P') && (array[2] == 's' || array[2] == 'S')) {
            final String lowerCase = new String(array, 0, a.a.a.a.g.a(array)).toLowerCase();
            final String lowerCase2 = new String(array2, 0, a.a.a.a.g.a(array2)).toLowerCase();
            if (this.f != null) {
                final a3 a10 = this.a(this.f, lowerCase, lowerCase2);
                if (a10 != null) {
                    return a10;
                }
            }
        }
        int i = 0;
        while (i < this.r) {
            if (a.a.a.a.g.if(array, this.i[i].e) == 0) {
                if (a.a.a.a.g.if(array2, ac.do) == 0) {
                    final a3 a11 = new a3();
                    a11.char = 4;
                    a11.int = this.i[i].byte;
                    return a11;
                }
                return this.i[i].a(array2);
            }
            else {
                ++i;
            }
        }
        final a3 a12 = new a3();
        a12.char = 4;
        return ac.a(a12);
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
                else if (for1.case != null && for1.case.if != null) {
                    final a3 a3 = new a3();
                    a3.char = 4;
                    a3.int = (String.valueOf(for1.case.if) + "\u0000").toCharArray();
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
        this.h.r.if(array2);
        this.h.v.a(array2);
    }
    
    public void a() {
        this.n = null;
        this.q = null;
        this.d = null;
        this.case = null;
        for (int i = 0; i < this.r; ++i) {
            this.i[i] = null;
        }
        this.i = null;
        this.h = null;
    }
}
