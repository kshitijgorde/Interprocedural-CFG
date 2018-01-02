import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class cw extends a3 implements ay, cx
{
    public long a;
    public int b;
    public c5[] c;
    public c6[] d;
    public Hashtable e;
    public bh f;
    public Vector g;
    public Vector h;
    public ax i;
    public int j;
    private int[] k;
    private int[] l;
    private int[] m;
    private int[] n;
    private int[][] o;
    private boolean p;
    private Hashtable q;
    private String r;
    private String s;
    private int t;
    private int u;
    private boolean v;
    private cw w;
    private Hashtable x;
    private Hashtable y;
    private boolean z;
    
    public cw(final ax i, final String s, final int n, final bo bo, final boolean b) {
        super(i, s, n, bo, b);
        this.b = 0;
        this.e = new Hashtable();
        this.g = new Vector();
        this.h = new Vector();
        this.j = 0;
        this.k = new int[0];
        this.l = new int[0];
        this.m = new int[0];
        this.n = new int[0];
        this.q = new Hashtable();
        this.u = 3;
        this.z = true;
        this.i = i;
        this.r = a(s, i);
    }
    
    public final String ab() {
        return this.r;
    }
    
    public String ac() {
        return this.s;
    }
    
    public void j(final String s) {
        if (n.e()) {
            n.a(this.s == null, " object id is not null");
        }
        this.s = s;
    }
    
    public static String a(final String s, final ax ax) {
        final a5 a5 = new a5(s);
        a4.a(a5, ax);
        a4.b(a5, ax);
        return a5.toString();
    }
    
    public final Vector ad() {
        return this.g;
    }
    
    public a5 h() {
        return super.h();
    }
    
    public final bg ae() {
        return super.j;
    }
    
    public final boolean a(final c1 c1) {
        if (!this.g.contains(c1)) {
            synchronized (this.g) {
                boolean b = c1.b() == null;
                if (!b) {
                    b = ((c1.b().d() & 0x100) != 0x0);
                }
                if (b) {
                    int i;
                    for (i = 0; i < this.g.size(); ++i) {
                        final c2 b2 = this.g.elementAt(i).b();
                        if (b2 != null && (b2.d() & 0x100) == 0x0) {
                            break;
                        }
                    }
                    this.g.insertElementAt(c1, i);
                }
                else {
                    this.g.addElement(c1);
                }
                if ((this.g() || this.r() != -1) && this.b(c1.b())) {
                    final boolean p = this.p;
                    this.p = true;
                    this.w = this;
                    try {
                        if (ay.a.i()) {
                            ay.a.g(this.i.as() + " calling subscription " + c1.toString());
                        }
                        c1.a(this, c1);
                        final ci j = this.i.j();
                        ++j.q;
                        if (super.j && this.g()) {
                            final Enumeration<cw> elements = (Enumeration<cw>)super.i.elements();
                            while (elements.hasMoreElements()) {
                                final cw cw = elements.nextElement();
                                if (cw != null) {
                                    final c1 c2 = new c1(cw.toString(), this, this.c(c1.b()));
                                    this.y.put(c2, c1);
                                    cw.a(c2);
                                }
                                else {
                                    if (!ay.a.g()) {
                                        continue;
                                    }
                                    ay.a.d(this.i.as() + " found (inner) null obj in quotes hash in addSubscription");
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        if (ay.a.g()) {
                            ay.a.b(this.i.as() + " got exception while adding new subscription " + c1, ex);
                        }
                    }
                    this.c = null;
                    this.d = null;
                    this.p = p;
                }
            }
            return true;
        }
        if (ay.a.g()) {
            ay.a.d(this.i.as() + " subscription already exist in PushObject.addSubscription()");
        }
        return false;
    }
    
    public final boolean b(final c1 c1) {
        if (super.j && this.y != null) {
            final Vector vector = new Vector<c1>();
            final Enumeration<c1> keys = this.y.keys();
            while (keys.hasMoreElements()) {
                final c1 c2 = keys.nextElement();
                if (this.y.get(c2) == c1) {
                    vector.addElement(c2);
                }
            }
            if (vector.size() > 0) {
                final c1[] array = new c1[vector.size()];
                vector.copyInto(array);
                if (ay.a.i()) {
                    ay.a.g(this.i.as() + " unsubscribing " + array.length + " inner subscriptions");
                }
                this.i.b(array);
            }
        }
        return this.g.removeElement(c1);
    }
    
    public final boolean af() {
        return this.w != this;
    }
    
    public final c5 ag() {
        return this.c(0);
    }
    
    public final c5 c(final int n) {
        final c5[] a = this.a(new c2(510, null, n));
        if (a != null) {
            return a[0];
        }
        return null;
    }
    
    public final c5[] ah() {
        if (this.c == null || this.p) {
            this.c = this.a((c2)null);
        }
        return this.c;
    }
    
    public final c5[] a(c2 c2) {
        if (!this.f()) {
            return null;
        }
        if (c2 == null) {
            c2 = new c2(510);
        }
        final int d = c2.d();
        int[] a = c2.a();
        final Hashtable c3 = c2.c();
        final Vector vector = new Vector<c5>();
        if (a == null) {
            a = new int[this.w.k.length];
            for (int i = 0; i < this.w.k.length; a[i] = i++) {}
        }
        if (c3 == null) {
            for (int j = 0; j < a.length; ++j) {
                c5 c4 = this.w.d(a[j]);
                if (this.w != this) {
                    if (!this.x.containsKey(this.w.toString()) && ay.a.f()) {
                        final StringBuffer sb = new StringBuffer();
                        final Enumeration<Object> keys = this.x.keys();
                        while (keys.hasMoreElements()) {
                            sb.append(keys.nextElement().toString() + " ");
                        }
                        ay.a.c(this.i.as() + " couldn't find innerObj " + this.w.toString() + " in innerObj2Row (" + sb.toString() + ")");
                    }
                    c4 = this.a(c4, this.x.get(this.w.toString()));
                }
                if (c4 != null && (c4.d() & d) != 0x0) {
                    vector.addElement(c4);
                }
            }
        }
        else {
            for (int k = 0; k < a.length; ++k) {
                c5 c5 = this.w.d(a[k]);
                if (this.w != this) {
                    c5 = this.a(c5, this.x.get(this.w.toString()));
                }
                if (c5 != null) {
                    String[] array = c5.b();
                    if (array == null) {
                        if (a[k] == 0) {
                            array = this.w.a(false);
                        }
                        else {
                            array = this.w.a(true);
                        }
                        if (this.w != this) {
                            array = this.a(array);
                        }
                    }
                    if ((c5.d() & d) != 0x0) {
                        for (int l = 0; l < array.length; ++l) {
                            if (c3.containsKey(array[l])) {
                                vector.addElement(c5);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (vector.size() == 0) {
            return null;
        }
        final c5[] array2 = new c5[vector.size()];
        for (int n = 0; n < vector.size(); array2[n] = vector.elementAt(n++)) {}
        return array2;
    }
    
    public c6[] ai() {
        if (this.d != null && !this.p) {
            return this.d;
        }
        if (!this.f()) {
            return null;
        }
        if (this.p) {
            final int[] array = new int[this.w()];
            for (int i = 0; i < array.length; array[i] = i++) {}
            return this.d = new c6[] { new c6(103, array) };
        }
        final Vector vector = new Vector<Integer>();
        final Vector<int[]> vector2 = new Vector<int[]>();
        final Vector vector3 = new Vector<Integer>();
        for (int j = 0; j < this.k.length; ++j) {
            if (this.k[j] == 16) {
                vector.addElement(new Integer(j));
            }
        }
        boolean[] array2;
        if (this.b >= 0) {
            array2 = new boolean[this.w()];
            for (int k = 0; k < this.b; ++k) {
                array2[array2.length - 1 - k] = true;
            }
        }
        else {
            array2 = new boolean[this.w() - this.b];
        }
        final boolean[] array3 = new boolean[this.k.length];
        for (int l = 0; l < this.l.length; ++l) {
            if (array3[l]) {
                if (this.n[l] != l && !array2[this.n[l]] && this.l[l] != 32) {
                    array2[this.n[l]] = true;
                }
            }
            else {
                switch (this.l[l]) {
                    case 1: {
                        array2[l] = true;
                    }
                    case 32: {
                        array3[l] = true;
                        break;
                    }
                    case 4: {
                        if (this.n[l] == l) {
                            array2[l] = (array3[l] = true);
                            break;
                        }
                    }
                    case 8: {
                        final int[] b = this.b(this.n[l], l);
                        for (int n = 0; n < b.length; ++n) {
                            if (b[n] != l) {
                                array2[b[n]] = true;
                                array3[b[n]] = true;
                            }
                            else {
                                array3[b[n]] = true;
                            }
                        }
                        vector2.addElement(b);
                        break;
                    }
                }
            }
        }
        final Vector a = this.a(vector2);
        this.a(a, array2);
        int n2 = 0;
        for (int n3 = 0; n3 < array2.length; ++n3) {
            if (!array2[n3]) {
                vector3.addElement(new Integer(n3 - n2));
                ++n2;
            }
        }
        final int[] array4 = new int[vector.size()];
        final int[] array5 = new int[vector3.size()];
        int n4 = 0;
        if (vector.size() > 0) {
            ++n4;
        }
        if (vector3.size() > 0) {
            ++n4;
        }
        final c6[] d = new c6[a.size() + n4];
        int n5 = 0;
        for (int n6 = 0; n6 < a.size(); ++n6) {
            d[n5++] = new c6(102, a.elementAt(n6));
        }
        if (vector3.size() > 0) {
            for (int n7 = 0; n7 < vector3.size(); array5[n7] = vector3.elementAt(n7++)) {}
            d[n5++] = new c6(101, array5);
        }
        if (vector.size() > 0) {
            for (int n8 = 0; n8 < vector.size(); array4[n8] = vector.elementAt(n8++)) {}
            d[n5++] = new c6(100, array4);
        }
        this.d = d;
        if (this.d.length == 0) {
            return null;
        }
        return this.d;
    }
    
    public a5 j() {
        return super.j();
    }
    
    public final boolean aj() {
        return this.v;
    }
    
    public final int ak() {
        try {
            final long n = System.currentTimeMillis() % 86400000L - Long.parseLong((String)this.g("CRYPTED_RECEIVED_TIME"));
            return (n < 0L) ? -1 : ((int)n);
        }
        catch (NumberFormatException ex) {
            if (ay.a.g()) {
                ay.a.d(this.i.as() + " couldn't find crypted_received_time");
            }
            return -1;
        }
    }
    
    public boolean a(final bg bg, final int n, final String s) {
        return this.a(bg, n, s, null);
    }
    
    public boolean a(final bg bg, final int n, final String s, final du du) {
        if (ay.a.l()) {
            ay.a.j(this.i.as() + " calling init in PushObject " + ay.a.a(bg, new Integer(n), s, du));
        }
        if (this.u() != null) {
            if (a0.a.g()) {
                a0.a.b("skipping init in PushObject because of netException", this.u());
            }
            return false;
        }
        if (this.i.d() == 2) {
            c1[] array = null;
            Hashtable<c1, c1> hashtable = null;
            synchronized (this) {
                super.a(bg, n, s);
                this.a = System.currentTimeMillis();
                this.w = this;
                final ci j = this.i.j();
                ++j.g;
                if (this.h.size() > 0) {
                    for (int i = 0; i < this.h.size(); ++i) {
                        this.a((bh)this.h.elementAt(i), false);
                    }
                }
                else {
                    final int w = this.w();
                    this.k = new int[w];
                    this.m = new int[w];
                    this.o = new int[w][];
                }
                if (du != null) {
                    try {
                        this.t = Integer.parseInt(du.a().a(this.i.aj().a("NAME_HTTP_MUI")));
                    }
                    catch (Exception ex) {
                        if (ay.a.i()) {
                            ay.a.g(this.i.as() + " received no minimum-update-interval value");
                        }
                        this.t = 0;
                    }
                    final String a = du.a().a(this.i.aj().a("NAME_HTTP_DIFF"));
                    if (a != null) {
                        if (a.equals(this.i.aj().a("NAME_HTTP_DIFF_MIN"))) {
                            this.u = 3;
                        }
                        else if (a.equals(this.i.aj().a("NAME_HTTP_DIFF_KEY"))) {
                            this.u = 4;
                        }
                        else if (a.equals(this.i.aj().a("NAME_HTTP_DIFF_BACK"))) {
                            this.u = 2;
                        }
                        else if (a.equals(this.i.aj().a("NAME_HTTP_DIFF_FRONT"))) {
                            this.u = 1;
                        }
                        else {
                            this.u = 3;
                        }
                    }
                    if (super.b.c().indexOf("general/quote") > -1 && this.q() == 1 && this.b("ID_QUALITY") > -1 && this.g("ID_QUALITY") != null && this.g("ID_QUALITY").equals("RLT")) {
                        this.v = true;
                    }
                }
                this.p = true;
                this.al();
                this.z = false;
                if (super.j) {
                    hashtable = new Hashtable<c1, c1>();
                    final Vector am = this.am();
                    if (am != null && am.size() > 0) {
                        this.x = new Hashtable();
                        array = new c1[am.size() * this.g.size()];
                        int n2 = 0;
                        for (int k = 0; k < am.size(); ++k) {
                            if (ay.a.k()) {
                                ay.a.i(this.i.as() + " found inner object " + (Object)am.elementAt(k) + " in obj " + this.toString());
                            }
                            final int n3 = (this.w() == 1) ? 0 : (k + 1);
                            final String s2 = am.elementAt(k);
                            for (int l = 0; l < this.g.size(); ++l) {
                                final c1 c1 = this.g.elementAt(l);
                                hashtable.put(array[n2++] = new c1(s2, this, this.c(c1.b())), c1);
                            }
                            this.x.put(s2, new Integer(n3));
                            if (ay.a.k()) {
                                ay.a.i(this.i.as() + " added subscription for inner object " + (Object)am.elementAt(k));
                            }
                        }
                    }
                }
            }
            if (super.j) {
                boolean b = false;
                if (this.y == null) {
                    this.y = hashtable;
                    b = true;
                }
                this.i.a(array);
                if (!b) {
                    if (this.y != null) {
                        final c1[] array2 = new c1[this.y.size()];
                        int n4 = 0;
                        final Enumeration<c1> keys = (Enumeration<c1>)this.y.keys();
                        while (keys.hasMoreElements()) {
                            array2[n4++] = keys.nextElement();
                        }
                        this.i.b(array2);
                    }
                    this.y = hashtable;
                }
            }
        }
        return true;
    }
    
    public boolean a(final am am) {
        return this.a(am, Integer.MAX_VALUE);
    }
    
    public boolean a(final am am, final int n) {
        if (this.i.d() == 2 && this.u() == null) {
            if (am != null) {
                this.i.c(this);
                super.a(am);
            }
            else {
                this.i.a(this, false);
                super.q.a(-8);
                super.l = null;
            }
            this.p = true;
            (this.w = this).al();
            this.z = false;
            return true;
        }
        return false;
    }
    
    private final Vector am() {
        if (!this.g()) {
            return null;
        }
        if (super.c.a("QUOTE_REQUEST") == null) {
            return null;
        }
        final String a = this.j().a("WITH_QUOTES_MARKET");
        final String a2 = this.j().a("WITH_QUOTES_TYPE");
        final String d = this.d();
        final int w = this.w();
        final Vector<String> vector = new Vector<String>();
        if (w == 1) {
            final String e = this.e(0, "QUOTE_REQUEST");
            if (e != null && e.length() > 0) {
                vector.addElement(this.a(e, a, a2) + d);
            }
            else if (ay.a.g()) {
                ay.a.d(this.i.as() + " null quote request value of " + this);
            }
        }
        else {
            for (int i = 1; i < w; ++i) {
                final String e2 = this.e(i, "QUOTE_REQUEST");
                if (e2 != null && e2.length() > 0) {
                    vector.addElement(this.a(e2, a, a2) + d);
                }
                else if (ay.a.g()) {
                    ay.a.d(this.i.as() + " null quote request value of " + i + " throw of " + this);
                }
            }
        }
        return vector;
    }
    
    public void a(final cw w, final c1 c1) {
        final c5 ag = w.ag();
        boolean b = false;
        if (ag.d() == 2) {
            b = true;
            final Integer n = this.x.get(w.toString());
            if (n == null && ay.a.f()) {
                ay.a.c(this.i.as() + " couldn't find inner objects position in PushObjects callback for " + w);
                return;
            }
            if (ay.a.i()) {
                ay.a.g(this.i.as() + " assigning inner obj " + w + " to position nr " + n.toString() + " in obj " + this.toString());
            }
            this.a(n, w);
        }
        final c1 c2 = this.y.get(c1);
        if (c2 == null && ay.a.g()) {
            ay.a.d(this.i.as() + " couldn't find (outer) subscription for callback of inner obj " + w.toString() + " with subs " + c1);
            return;
        }
        try {
            if ((!b || (b && this.a(c2.b(), 64))) && this.d(c2.b())) {
                if (ay.a.k()) {
                    ay.a.i(this.i.as() + " invoking outer callback of subs " + c2 + " because of inner callback of subs " + c1);
                }
                this.w = w;
                this.a = System.currentTimeMillis();
                c2.a(this, c2);
            }
            else if (ay.a.k()) {
                ay.a.i(this.i.as() + " skipping outer callback of subs " + c2 + " for type " + ag.e());
            }
        }
        catch (Exception ex) {
            if (ay.a.g()) {
                ay.a.b(this.i.as() + " callback (invoked by inner callback) threw exception " + c1, ex);
            }
        }
        ++this.j;
    }
    
    public final boolean b(final c2 c2) {
        if (c2 == null || !this.f()) {
            return true;
        }
        final int d = c2.d();
        int[] a = c2.a();
        final Hashtable c3 = c2.c();
        if (a == null) {
            a = new int[this.k.length];
            for (int i = 0; i < this.k.length; a[i] = i++) {}
        }
        if (c3 == null) {
            for (int j = 0; j < a.length; ++j) {
                final c5 d2 = this.d(a[j]);
                if (d2 != null && (d2.d() & d) != 0x0) {
                    return true;
                }
            }
            if ((d & 0x100) != 0x0 && this.ai() != null) {
                return true;
            }
        }
        else {
            for (int k = 0; k < a.length; ++k) {
                final c5 d3 = this.d(a[k]);
                if (d3 != null) {
                    final String[] b = d3.b();
                    if (b == null && (d3.d() & d) != 0x0) {
                        return true;
                    }
                    if ((d3.d() & d) != 0x0) {
                        for (int l = 0; l < b.length; ++l) {
                            if (c3.containsKey(b[l])) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public final void a(final bg j, final Hashtable i) {
        super.j = j;
        super.g = j.a() - 1;
        if (i != null) {
            super.i = i;
        }
    }
    
    public final void a(final bg bg) {
        this.a(bg, null);
    }
    
    public final void a(final bh bh) {
        this.a(bh, true);
    }
    
    public final void a(final bh f, final boolean b) {
        try {
            ++this.j;
            final long currentTimeMillis = System.currentTimeMillis();
            final ci j = this.i.j();
            ++j.f;
            if ((!this.f() || this.z) && b) {
                if (ay.a.l()) {
                    ay.a.j(this.i.as() + " obj not filled; storing update-info");
                }
                this.h.addElement(f);
                return;
            }
            this.c = null;
            this.d = null;
            this.p = false;
            this.e = new Hashtable();
            this.q = new Hashtable();
            this.w = this;
            final int n = (super.j != null) ? this.w() : 0;
            final int a = super.j.a();
            final bg bg = new bg();
            final bh bh = new bh();
            for (int i = 0; i < super.j.a(0).a(); ++i) {
                bh.a(super.j.a(0, i));
            }
            bh.a(new bi(Integer.toString(Math.abs(f.a(1).b((String)null))), super.n), 3);
            bg.a(bh);
            final int max = Math.max(-f.a(1).b((String)null), this.w());
            this.k = new int[max];
            this.m = new int[max];
            this.o = new int[max][];
            int n2 = 0;
            int n3 = 0;
            int k = 2;
            while (k < f.a()) {
                final bi a2 = f.a(k++);
                if (a2.c() == 0) {
                    throw new eg("Empty diff token at " + k + ".");
                }
                final byte a3 = a2.a();
                if (a3 == 66 || a3 == 65) {
                    int intValue = 1;
                    final int intValue2 = f.a(k++).b((String)null);
                    if (a3 == 66) {
                        intValue = f.a(k++).b((String)null);
                    }
                    if (intValue2 > a || intValue + intValue2 > a) {
                        throw new eg("Wrong line indices in RANGE_COPY: start:" + intValue2 + " nr:" + intValue);
                    }
                    final int n4 = intValue2 + intValue;
                    if (intValue2 - 1 == n3) {
                        for (int l = intValue2; l < n4; ++l) {
                            bg.a(super.j.a(l));
                            this.k[n3] = 1;
                            this.m[n3] = l - 1;
                            ++n3;
                        }
                    }
                    else {
                        for (int n5 = intValue2; n5 < n4; ++n5) {
                            bg.a(super.j.a(n5));
                            this.k[n3] = 8;
                            this.m[n3] = n5 - 1;
                            ++n3;
                        }
                    }
                }
                else if (a3 == 67) {
                    final int intValue3 = f.a(k++).b((String)null);
                    if (intValue3 + k > f.a()) {
                        throw new eg("INSERT with too many tokens");
                    }
                    final bh bh2 = new bh();
                    for (int n6 = 0; n6 < intValue3; ++n6) {
                        bh2.a(f.a(k++));
                    }
                    bg.a(bh2);
                    ++n2;
                    this.k[n3] = 16;
                    this.m[n3] = n3;
                    ++n3;
                }
                else {
                    final int intValue4 = a2.b((String)null);
                    if (intValue4 >= super.j.a()) {
                        throw new eg("Line " + intValue4 + " out of range max=" + (super.j.a() - 1) + " diff=" + new String(a2.d()));
                    }
                    final Vector a4 = a(f.a(k++));
                    final bh a5 = super.j.a(intValue4);
                    this.o[n3] = new int[a4.size()];
                    this.m[n3] = intValue4 - 1;
                    this.k[n3] = ((n3 != intValue4 - 1) ? 8 : 4);
                    for (int n7 = 0; n7 < a4.size(); ++n7) {
                        final int intValue5 = a4.elementAt(n7);
                        a5.a(f.a(k++), intValue5);
                        this.o[n3][n7] = intValue5;
                    }
                    bg.a(a5);
                    ++n3;
                }
            }
            this.a(bg);
            this.a = System.currentTimeMillis();
            this.b = this.w() - n;
            if (this.b < 0) {
                for (int b2 = this.b; b2 < 0; ++b2) {
                    this.k[n3] = 32;
                    this.m[n3] = n3;
                    ++n3;
                }
            }
            this.f = f;
            this.l = new int[this.k.length - n2];
            this.n = new int[this.l.length];
            int n8 = 0;
            int n9 = 0;
            for (int n10 = 0; n10 < this.k.length; ++n10) {
                if (this.k[n10] == 16) {
                    ++n8;
                }
                else {
                    switch (this.k[n10]) {
                        case 1:
                        case 8: {
                            if (this.m[n10] == n9) {
                                this.l[n9] = 1;
                                break;
                            }
                            this.l[n9] = 8;
                            break;
                        }
                        default: {
                            this.l[n9] = this.k[n10];
                            break;
                        }
                    }
                    this.n[n9] = this.m[n10];
                    ++n9;
                }
            }
            final ci m = this.i.j();
            m.o += (int)(System.currentTimeMillis() - currentTimeMillis);
            if (this.aj()) {
                final ci j2 = this.i.j();
                j2.p += this.ak();
                final ci j3 = this.i.j();
                ++j3.h;
            }
            if (super.j && super.i != null && this.x != null) {
                final Hashtable hashtable = new Hashtable<Integer, Object>();
                for (int n11 = 0; n11 < this.m.length; ++n11) {
                    if (this.m[n11] > 0) {
                        switch (this.k[n11]) {
                            case 1:
                            case 4:
                            case 8: {
                                final Integer n12 = new Integer(this.m[n11]);
                                if (!super.i.containsKey(n12) && ay.a.f()) {
                                    ay.a.c(this.i.as() + " couldn't find inner object at position nr " + this.m[n11]);
                                    break;
                                }
                                hashtable.put(new Integer(n11), super.i.get(n12));
                                this.x.put(((a3)super.i.get(n12)).toString(), n12);
                                break;
                            }
                            case 32: {
                                if (this.y != null) {
                                    this.a(super.i.get(new Integer(n11)).toString(), false);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
                if (hashtable.size() > 0) {
                    final Enumeration<Integer> keys = hashtable.keys();
                    while (keys.hasMoreElements()) {
                        final Integer n13 = keys.nextElement();
                        super.i.put(n13, super.i.get(n13));
                    }
                }
            }
            if (b) {
                this.al();
            }
            if (super.j) {
                final c6[] ai = this.ai();
                if (super.i != null && ai != null) {
                    for (int n14 = 0; n14 < ai.length; ++n14) {
                        if (ai[n14].b() == 101) {
                            final int[] a6 = ai[n14].a();
                            for (int n15 = 0; n15 < a6.length; ++n15) {
                                final Object value = super.i.get(new Integer(a6[n15]));
                                if (value != null) {
                                    this.a(((a3)value).toString(), false);
                                }
                            }
                        }
                    }
                }
                for (int n16 = 0; n16 < this.m.length; ++n16) {
                    if (this.k[n16] == 16) {
                        super.c.a("QUOTE_REQUEST");
                        final String a7 = this.j().a("WITH_QUOTES_MARKET");
                        final String a8 = this.j().a("WITH_QUOTES_TYPE");
                        final String d = this.d();
                        final String e = this.e(n16, "QUOTE_REQUEST");
                        if (e != null && e.length() > 0) {
                            final String string = this.a(e, a7, a8) + d;
                            final c1[] array = new c1[this.g.size()];
                            for (int n17 = 0; n17 < this.g.size(); ++n17) {
                                final c1 c1 = this.g.elementAt(n17);
                                final c1 c2 = new c1(string, this, this.c(c1.b()));
                                array[n17] = c2;
                                this.y.put(c2, c1);
                            }
                            this.x.put(string, new Integer(n16));
                            this.i.a(array);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            if (ay.a.g()) {
                ay.a.b(this.i.as() + " unable to merge diff-message to new CSVObject for " + this.toString(), ex);
            }
            this.i.a(this);
        }
    }
    
    public final void al() {
        if (ay.a.i()) {
            this.an();
        }
        final Vector vector;
        synchronized (this.g) {
            vector = (Vector)this.g.clone();
        }
        c1 c1 = null;
        final long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = 0L;
        int n = 0;
        for (int i = 0; i < vector.size(); ++i) {
            if (this.b(vector.elementAt(i).b())) {
                try {
                    c1 = vector.elementAt(i);
                    if (ay.a.j()) {
                        ay.a.h(this.i.as() + " calling subscription " + (i + 1) + " of " + vector.size() + " at idx " + i + ": " + c1.toString());
                    }
                    if (ay.a.i()) {
                        currentTimeMillis2 = System.currentTimeMillis();
                    }
                    c1.a(this, c1);
                    if (ay.a.j()) {
                        ay.a.h(this.i.as() + " needed " + (System.currentTimeMillis() - currentTimeMillis2) + "ms for cb of subs " + c1.toString());
                    }
                    ++n;
                    final ci j = this.i.j();
                    ++j.q;
                }
                catch (Exception ex) {
                    if (ay.a.g()) {
                        ay.a.b(this.i.as() + " callback threw exception " + c1, ex);
                    }
                }
            }
        }
        if (ay.a.i()) {
            ay.a.g(this.i.as() + " needed " + (System.currentTimeMillis() - currentTimeMillis) + "ms for " + n + " callback invocation(s)");
        }
    }
    
    public final int[] b(int n, final int n2) {
        final Vector vector = new Vector<Integer>();
        final Integer n3 = new Integer(n2);
        vector.addElement(n3);
        Integer n4 = new Integer(n);
        vector.addElement(n4);
        this.q.put(n3, n3);
        while (!this.q.containsKey(n4) && n < this.l.length && this.l[n] != 32 && this.l[n] != 16 && this.n[n] != n2) {
            this.q.put(n4, n4);
            n = this.n[n];
            n4 = new Integer(n);
            vector.addElement(n4);
        }
        this.q.put(n4, n4);
        if (n.e()) {
            n.a(vector.size() > 1, "found no permutation in PushObject.computePermutation()");
        }
        final int[] array = new int[vector.size()];
        for (int n5 = vector.size() - 1, i = 0; i <= n5; ++i) {
            array[i] = vector.elementAt(n5 - i);
        }
        return array;
    }
    
    public final Vector a(final Vector vector) {
        final Vector<int[]> vector2 = new Vector<int[]>();
        int n = 0;
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i)[0] > n) {
                n = vector.elementAt(i)[0];
            }
        }
        final int[][] array = new int[++n][];
        final boolean[] array2 = new boolean[n];
        for (int j = 0; j < vector.size(); ++j) {
            final int[] array3 = vector.elementAt(j);
            array[array3[0]] = array3;
        }
        for (int k = 0; k < array2.length; ++k) {
            if (!array2[k]) {
                if (array[k] != null) {
                    int length = array[k].length;
                    int n2 = array[k][array[k].length - 1];
                    array2[k] = true;
                    while (n2 < array.length && array[n2] != null) {
                        length += array[n2].length - 1;
                        array2[n2] = true;
                        n2 = array[n2][array[n2].length - 1];
                    }
                    final int[] array4 = new int[length];
                    final int n3 = 0;
                    System.arraycopy(array[k], 0, array4, n3, array[k].length);
                    int n4 = n3 + array[k].length;
                    for (int n5 = array[k][array[k].length - 1]; n5 < array.length && array[n5] != null; n5 = array[n5][array[n5].length - 1]) {
                        System.arraycopy(array[n5], 1, array4, n4, array[n5].length - 1);
                        n4 += array[n5].length - 1;
                    }
                    vector2.addElement(array4);
                }
            }
        }
        return vector2;
    }
    
    public final void a(final Vector vector, final boolean[] array) {
        for (int i = 0; i < vector.size(); ++i) {
            final int[] array2 = vector.elementAt(i);
            boolean b = array[array2[0]];
            for (int j = 1; j < array2.length; ++j) {
                final boolean b2 = array[array2[j]];
                array[array2[j]] = b;
                b = b2;
            }
            array[array2[0]] = b;
        }
    }
    
    public static final Vector a(final bi bi) throws eg {
        if (bi.c() == 0) {
            throw new eg("Unexpected: diff token is null ");
        }
        final int n = bi.c() - bi.b();
        final Vector<Integer> vector = new Vector<Integer>();
        for (int i = 0, n2 = 0; i < n; ++i, n2 += 6) {
            final int n3 = ck.b[bi.a(i)];
            if ((n3 & 0x1) > 0) {
                vector.addElement(new Integer(n2));
            }
            if ((n3 & 0x2) > 0) {
                vector.addElement(new Integer(n2 + 1));
            }
            if ((n3 & 0x4) > 0) {
                vector.addElement(new Integer(n2 + 2));
            }
            if ((n3 & 0x8) > 0) {
                vector.addElement(new Integer(n2 + 3));
            }
            if ((n3 & 0x10) > 0) {
                vector.addElement(new Integer(n2 + 4));
            }
            if ((n3 & 0x20) > 0) {
                vector.addElement(new Integer(n2 + 5));
            }
        }
        return vector;
    }
    
    public c5 d(final int n) {
        if (this.p) {
            return new c5(2, null, n, n);
        }
        if (this.e.containsKey(new Integer(n))) {
            return this.e.get(new Integer(n));
        }
        final int n2 = this.k[n];
        if (n2 == 1) {
            return null;
        }
        c5 c5 = null;
        switch (n2) {
            case 8:
            case 16:
            case 32: {
                c5 = new c5(n2, null, n, this.m[n]);
                break;
            }
            case 4: {
                if (n.e()) {
                    n.a(this.o[n] != null, "found null-array in PushObject.calcUpdateEvent(row)");
                }
                String[] array;
                if (super.c.a()) {
                    final Vector vector = new Vector<Object>();
                    if (n == 0) {
                        for (int i = 0; i < this.o[n].length; ++i) {
                            final Vector b = super.c.b(this.o[n][i], this.q(), false);
                            int j = 0;
                            while (j < b.size()) {
                                vector.addElement(b.elementAt(j++));
                            }
                        }
                    }
                    else {
                        for (int k = 0; k < this.o[n].length; ++k) {
                            final Vector b2 = super.c.b(this.o[n][k], this.q(), true);
                            int l = 0;
                            while (l < b2.size()) {
                                vector.addElement(b2.elementAt(l++));
                            }
                        }
                    }
                    array = new String[vector.size()];
                    vector.copyInto(array);
                }
                else {
                    array = new String[this.o[n].length];
                    if (n == 0) {
                        for (int n3 = 0; n3 < this.o[n].length; ++n3) {
                            array[n3] = super.c.a(this.o[n][n3], this.q(), false);
                        }
                    }
                    else {
                        for (int n4 = 0; n4 < this.o[n].length; ++n4) {
                            array[n4] = super.c.a(this.o[n][n4], this.q(), true);
                        }
                    }
                }
                c5 = new c5(n2, array, n, this.m[n]);
                break;
            }
            default: {
                if (ay.a.f()) {
                    ay.a.c(this.i.as() + " unknown event-type in PushObject.calcUpdateEvent(row): " + n2);
                }
                return null;
            }
        }
        this.e.put(new Integer(n), c5);
        return c5;
    }
    
    private final c5 a(final c5 c5, final int n) {
        final String[] b = c5.b();
        int d = c5.d();
        switch (d) {
            case 4: {
                d = 128;
                break;
            }
            case 2: {
                d = 64;
                break;
            }
        }
        return new c5(d, this.a(b), n, n);
    }
    
    private final String[] a(final String[] array) {
        if (array == null) {
            return null;
        }
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = "QUOTE." + array[i];
        }
        return array2;
    }
    
    private final c2 c(final c2 c2) {
        if (c2 == null) {
            return null;
        }
        final String[] b = c2.b();
        String[] array = null;
        final int d = c2.d();
        if (b != null) {
            final Vector vector = new Vector<String>();
            for (int i = 0; i < b.length; ++i) {
                if (b[i].startsWith("QUOTE.")) {
                    vector.addElement(b[i].substring(6));
                }
            }
            array = new String[vector.size()];
            vector.copyInto(array);
        }
        int n = d & ~0x13E;
        if ((n & 0x80) != 0x0) {
            n = ((n ^ 0x80) | 0x4);
        }
        if ((n & 0x40) != 0x0) {
            n ^= 0x40;
        }
        return new c2(n | 0x2, array);
    }
    
    private final Vector k(final String s) {
        final Vector<c1> vector = new Vector<c1>();
        final Enumeration<Object> keys = this.y.keys();
        while (keys.hasMoreElements()) {
            final c1 c1 = this.y.get(keys.nextElement());
            if (c1.a().equalsIgnoreCase(s)) {
                vector.addElement(c1);
            }
        }
        return vector;
    }
    
    private final void a(final String s, final boolean b) {
        final Vector k = this.k(s);
        if (k.size() > 0) {
            final c1[] array = new c1[k.size()];
            k.copyInto(array);
            this.i.b(array);
            for (int i = 0; i < array.length; ++i) {
                this.y.remove(array[i]);
                if (this.x.remove(array[i].a()) == null && ay.a.g()) {
                    ay.a.d(this.i.as() + " couldn't find object " + array[i].a() + " for removal from innerObj2Row");
                }
            }
        }
        else if (b && ay.a.g()) {
            ay.a.d(this.i.as() + " couldn't find inner object " + s + " for unsubscription");
        }
    }
    
    private boolean a(final c2 c2, final int n) {
        return c2 == null || (c2.d() & n) != 0x0;
    }
    
    private boolean d(final c2 c2) {
        if (c2 == null) {
            return true;
        }
        final String[] b = c2.b();
        if (b == null) {
            return true;
        }
        for (int i = 0; i < b.length; ++i) {
            if (b[i].startsWith("QUOTE.")) {
                return true;
            }
        }
        return false;
    }
    
    private final void an() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.i.as() + " updates for obId=" + this.ac() + " ");
        if (this.w == this) {
            final c6[] ai = this.ai();
            if (ai != null) {
                for (int i = 0; i < ai.length; ++i) {
                    sb.append(ai[i].toString() + " ");
                }
            }
        }
        final c5[] ah = this.ah();
        if (ah != null) {
            for (int j = 0; j < ah.length; ++j) {
                if (ah[j].d() == 4) {
                    final String[] b = ah[j].b();
                    for (int k = 0; k < b.length; ++k) {
                        sb.append(b[k] + "[" + ah[j].a() + "]=" + this.e(ah[j].a(), b[k]) + " ");
                    }
                }
                else {
                    sb.append(ah[j].toString() + " ");
                }
            }
        }
        ay.a.g(sb.toString());
    }
}
