import java.util.Locale;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class a3 extends a4 implements a0
{
    private static b7 a;
    private static Hashtable b;
    public bk c;
    private int d;
    public String e;
    public String f;
    public String g;
    public Object h;
    public Hashtable i;
    public boolean j;
    public az k;
    private String l;
    private int m;
    
    public a3() {
    }
    
    public a3(final az k, final String s, final int d, final bo h, final boolean b) {
        super(k, s, b);
        this.k = k;
        this.h = h;
        this.d = d;
    }
    
    public void a(final az az) {
        if (az.k) {
            return;
        }
        super.a(az);
        this.g = super.b.b("WITH_QUOTES");
        if (this.g != null) {
            super.b.a("QUALITY", this.g);
            this.j = true;
        }
        super.b.d("/" + super.c + ".csv");
        super.d = super.b.toString();
    }
    
    public void a(final a3 a3) {
        if (this.h == null) {
            this.h = a3.h;
        }
        if (this.h instanceof Vector) {
            ((Vector)this.h).addElement(a3.h);
        }
        else {
            final Vector<Object> h = new Vector<Object>();
            h.addElement(this.h);
            h.addElement(a3.h);
            this.h = h;
        }
    }
    
    public void b(final a3 a3) {
        if (this.h != null) {
            if (this.h instanceof Vector) {
                final Vector vector = (Vector)this.h;
                for (int i = 0; i < vector.size(); ++i) {
                    vector.elementAt(i).a(a3);
                }
            }
            else {
                ((bo)this.h).a(a3);
            }
        }
        this.h = null;
    }
    
    public a3 b() {
        if (this.d != 0) {
            return null;
        }
        final bp bp = (bp)this.k.e("CACHE_MODULE");
        if (bp != null) {
            final a3 a3 = (a3)bp.b().a(this.l());
            if (a3 != null) {
                if (a0.a.i()) {
                    a0.a.g(this.k.as() + " cache with status:" + a3.r() + " " + a3);
                }
                final a1 i = this.k.i();
                ++i.d;
            }
            return a3;
        }
        return null;
    }
    
    public void c() {
        if (this.d != 2) {
            final bp bp = (bp)this.k.e("CACHE_MODULE");
            if (bp != null) {
                if ("true".equals(this.k.aj().a("CACHE_ADJUST_EXPIRY_TIME"))) {
                    final long aw = this.k.aw();
                    if (aw != Long.MIN_VALUE && super.f > 0L) {
                        if (a0.a.j()) {
                            a0.a.h("insertIntoCache: adding " + aw + " to " + super.f);
                        }
                        super.f += aw;
                    }
                }
                bp.b().a(this.l(), this, this.p(), this.m());
            }
        }
    }
    
    public bk a(final String s) {
        return new bk(this.k, s, this.k(), false);
    }
    
    public boolean a(final bg bg, final int n, final String s) {
        return this.a(bg, n, s, false);
    }
    
    public boolean a(final bg bg, final int n, final String s, final boolean b) {
        if (!super.a(bg, n, s)) {
            return false;
        }
        if (this.r() == 0) {
            this.c = bk.a(this);
            if (this.c.r() == 0) {
                if (b) {
                    String a = null;
                    try {
                        a = this.c.a("AMOUNT").a(this, 0);
                    }
                    catch (Exception ex) {}
                    if (a == null || a.equals("0")) {
                        super.q.a(100);
                        this.c();
                        final a1 i = this.k.i();
                        ++i.i;
                    }
                }
            }
            else {
                super.q.a(-3);
                final a1 j = this.k.i();
                ++j.h;
            }
        }
        else {
            this.c();
            final a1 k = this.k.i();
            ++k.i;
        }
        if (this.r() == 0) {
            this.c();
            final a1 l = this.k.i();
            ++l.f;
        }
        else {
            final a1 m = this.k.i();
            ++m.g;
        }
        this.b(this);
        if (a0.a.i()) {
            a0.a.g(this.k.as() + " init:" + this.t() + ": " + this.l() + " expires at " + this.m() / 1000L + " in " + this.aa() / 1000L + " s");
        }
        if (!this.f() && a0.a.g()) {
            a0.a.d(this.k.as() + " inited invalid MDGObject: " + this.l() + ":" + this.t());
        }
        if (a0.a.l()) {
            a0.a.j(this.k.as() + " init with " + this.y());
        }
        return true;
    }
    
    public boolean a(final am am) {
        if (!super.a(am)) {
            return false;
        }
        this.b(this);
        final a1 i = this.k.i();
        ++i.g;
        if (am instanceof ct) {
            final a1 j = this.k.i();
            ++j.k;
        }
        else if (am instanceof al) {
            final a1 k = this.k.i();
            ++k.j;
        }
        else if (am instanceof dl) {
            final a1 l = this.k.i();
            ++l.l;
        }
        if (a0.a.g()) {
            a0.a.b(this.k.as() + " init: " + this.l() + ":" + this.t(), am);
        }
        if (a0.a.l()) {
            a0.a.j(this.k.as() + " init with " + this.y());
        }
        return true;
    }
    
    public static final a3 a(final az az, final String s, final int n) {
        if (a0.a.l()) {
            a0.a.j("MDGObject.getInstance" + a0.a.a(az, s, new Integer(n)));
        }
        return a(az, new String[] { s }, n)[0];
    }
    
    public static final a3 a(final az az, final String s) {
        return a(az, s, 0);
    }
    
    public static final a3[] a(final az az, final String[] array, final int n) {
        if (a0.a.l()) {
            a0.a.j("MDGObject.getArray" + a0.a.a(az, array, new Integer(n)));
        }
        int c = 0;
        final long currentTimeMillis = System.currentTimeMillis();
        final a3[] array2 = new a3[array.length];
        int n2 = 0;
        int n3 = 0;
        final a9 a9 = new a9(az);
        for (int i = 0; i < array.length; ++i) {
            switch (a9.a(new a3(az, array[i], n, new bn(array2, i), true))) {
                case 2: {
                    ++n2;
                    break;
                }
                case 1: {
                    ++n3;
                    break;
                }
            }
        }
        final String a10 = az.aj().a("URI_PREFIX");
        final bc ao = az.ao();
        final String ar = az.ar();
        final long n4 = 0L;
        bd bd = null;
        if (a9.size() > 0) {
            final a1 j = az.i();
            j.e += a9.size();
            bd = new bd(az, a9, ao, a10, n4, ar);
        }
        int a11 = 0;
        int b = 0;
        if (bd != null) {
            a11 = bd.a();
            b = bd.b();
        }
        final Vector a12 = a9.a();
        if (a12.size() > 0) {
            final a9 a13 = new a9(az);
            for (int k = 0; k < a12.size(); ++k) {
                a12.elementAt(k).a(a13);
            }
            c = a13.c();
            final a1 l = az.i();
            l.e += a13.size();
            if (a13.size() > 0) {
                final bd bd2 = new bd(az, a13, ao, a10, n4, ar);
                a11 += bd2.a();
                b += bd2.b();
            }
        }
        final long n5 = System.currentTimeMillis() - currentTimeMillis;
        final a1 m = az.i();
        m.a += array.length;
        final a1 i2 = az.i();
        i2.b += array.length + c;
        final a1 i3 = az.i();
        i3.s += (int)n5;
        if (a0.a.h()) {
            a0.a.f(az.as() + " got " + array.length + "+" + c + " requests in " + n5 + "ms " + " cached:" + n2 + " dup:" + n3 + " mdg_err:" + b + " net_err:" + a11);
        }
        return array2;
    }
    
    public static final a3[] a(final az az, final String[] array) {
        return a(az, array, az.ap());
    }
    
    public final String d() {
        final String a = this.j().a("QUALITY");
        return "&DETAILS=" + this.q() + ((a != null) ? ("&QUALITY=" + a) : "");
    }
    
    private final void a(final a9 a9) {
        if (!this.g()) {
            return;
        }
        if (this.c.a("QUOTE_REQUEST") == null) {
            return;
        }
        final String a10 = this.j().a("WITH_QUOTES_MARKET");
        final String a11 = this.j().a("WITH_QUOTES_TYPE");
        final String d = this.d();
        final int w = this.w();
        if (w == 1) {
            final String e = this.e(0, "QUOTE_REQUEST");
            if (e != null && e.length() > 0) {
                a9.a(new a3(this.e(), this.a(e, a10, a11) + d, this.d, new bv(this, 0), true));
            }
            else if (a0.a.g()) {
                a0.a.d(this.k.as() + " null quote request value of " + this);
            }
        }
        else {
            for (int i = 1; i < w; ++i) {
                final String e2 = this.e(i, "QUOTE_REQUEST");
                if (e2 != null && e2.length() > 0) {
                    a9.a(new a3(this.e(), this.a(e2, a10, a11) + d, this.d, new bv(this, i), true));
                }
                else if (a0.a.g()) {
                    a0.a.d(this.k.as() + " null quote request value of " + i + "th row of " + this);
                }
            }
        }
    }
    
    public String a(final String s, final String s2, final String s3) {
        final x x = new x(s);
        if (s2 != null) {
            x.a("MARKET", s2);
            x.b("ID_EXCHANGE");
        }
        if (s3 != null) {
            x.a("TYPE_QUOTES", s3);
        }
        return x.toString();
    }
    
    public final az e() {
        return this.k;
    }
    
    public final boolean f() {
        return this.r() == 0 && this.c != null;
    }
    
    public final boolean g() {
        return this.f() && (this.c.a("AMOUNT") == null || this.d("AMOUNT") > 0);
    }
    
    public final String[] a(final boolean b) {
        if (this.f()) {
            return this.c.a(b, this.q());
        }
        return new String[0];
    }
    
    public int b(final String s) {
        if (!this.f()) {
            return -1;
        }
        final int index = s.indexOf(".");
        String string = null;
        if (index > 0) {
            string = s.substring(0, index) + "_REQUEST";
        }
        final bj h = this.h(s);
        if (h == null) {
            return -1;
        }
        if (h.b(this.q()) < 0) {
            return -1;
        }
        if (string == null) {
            return h.e();
        }
        final bj h2 = this.h(string);
        if (f.n()) {
            a0.a.a(h2 == null, "found inner definition, but no attribute for the MDGObjectValue for [" + string + "]");
        }
        if (h2 == null) {
            return -1;
        }
        if (h2.b(this.q()) < 0) {
            return -1;
        }
        return h2.e();
    }
    
    private Object a(final int n, final String s, final Class clazz, final String s2) {
        final Object a = this.a(n, s, s2);
        if (clazz.isInstance(a)) {
            return a;
        }
        if (a != null) {
            this.b(n, s, a + " is not of type " + s2, "get" + s2 + "Value", null);
        }
        return this.k.aj().b("DEFAULT_" + s2.toUpperCase(Locale.ENGLISH) + "_VALUE");
    }
    
    public final Double a(final int n, final String s) {
        return (Double)this.a(n, s, et.f, "DateTime");
    }
    
    public final Double c(final String s) {
        return this.a(0, s);
    }
    
    public final Integer b(final int n, final String s) {
        return (Integer)this.a(n, s, et.c, "Int");
    }
    
    public final Integer d(final String s) {
        return this.b(0, s);
    }
    
    public final String c(final int n, final String s) {
        return (String)this.a(n, s, et.h, "String");
    }
    
    public final String e(final String s) {
        return this.c(0, s);
    }
    
    public final a3 d(final int n, final String s) {
        if (s == null) {
            return null;
        }
        if (this.i().indexOf("WITH_QUOTES") == -1) {
            return null;
        }
        final Object a = this.a(n, s + "_REQUEST", "MDGObject");
        if (a instanceof a3) {
            return (a3)a;
        }
        if (this.k.aj().i("DEFAULT_USE_DUMMY_QUOTE_VALUE")) {
            return new a3(this.k, "general/quote", 0, null, true);
        }
        return null;
    }
    
    public final String e(final int n, final String s) {
        try {
            final int index;
            if ((index = s.indexOf(".")) > 0) {
                return (String)this.a(s, n, index, 3, null);
            }
            return this.c.a(s).a(this, n);
        }
        catch (Throwable t) {
            try {
                this.a(n, s, "getRawValue", this.f(n, s), t);
            }
            catch (Exception ex) {
                this.b(n, s, "InternalError:UnknownException " + bl.a(ex), "getRawValue", ex);
            }
            return null;
        }
    }
    
    public final String f(final String s) {
        return this.e(0, s);
    }
    
    public final String f(final int n, final String s) {
        if (this.r() != 0) {
            return "InternalError: checkRawValue with getStatusCode() == " + this.r();
        }
        if (this.c == null) {
            return "InternalError: checkRawValue with metaDescriptor == null";
        }
        final bj a = this.c.a(s);
        if (a == null) {
            return "Invalid attribute name [" + s + "]";
        }
        final int b = a.b(this.q());
        if (b < 0) {
            return "Attribute not available at details " + this.q();
        }
        if (n > this.w() - 1) {
            return "Requested row " + n + " larger than number of rows " + this.w();
        }
        if (b > this.b(n)) {
            return "InternalError: requested field " + b + " larger than number of fields " + this.b(n) + " in row " + n;
        }
        return null;
    }
    
    public final Object g(final String s) {
        return this.g(0, s);
    }
    
    public final Object g(final int n, final String s) {
        return this.a(n, s, "getValue");
    }
    
    public final bj h(final String s) {
        if (this.f()) {
            if (this.j && s != null && s.startsWith("QUOTE.")) {
                try {
                    return this.d(this.z() ? 1 : 0, "QUOTE").h(s.substring(6));
                }
                catch (Exception ex) {
                    return null;
                }
            }
            return this.c.a(s);
        }
        return null;
    }
    
    public final String h(final int n, final String s) {
        if (s != null && s.indexOf(".") > -1) {
            if (a0.a.g()) {
                a0.a.d("cannot resolve format group of [" + s + "] - get embedded object first");
            }
            return null;
        }
        final bj h = this.h(s);
        if (h == null) {
            return null;
        }
        String s2 = h.c();
        if (s2 == null || !this.f()) {
            return null;
        }
        if (s2.indexOf("depends") > -1) {
            try {
                String s3 = null;
                int n2;
                if ((n2 = s.indexOf("FIELD")) > -1) {
                    s3 = "FIELD";
                }
                else if ((n2 = s.indexOf("FIGURE")) > -1) {
                    s3 = "FIGURE";
                }
                if (n2 > -1) {
                    final String substring = s.substring(n2 + s3.length());
                    final String f = this.f("FORMAT_" + s3 + substring);
                    if (f != null && f.length() > 0) {
                        s2 = f;
                    }
                    else if (a0.a.j()) {
                        a0.a.h("could not get format group by FORMAT_" + s3 + substring);
                    }
                }
                else if (a0.a.g()) {
                    a0.a.d("neither \"FIELD\" nor \"FIGURE\" are contained in the attribute claiming to be a \"depends on field\" format group [" + s + "], trying FORMAT_" + s);
                }
            }
            catch (Exception ex) {
                if (a0.a.k()) {
                    a0.a.g("cannot resolve \"depends on field\" format group", ex);
                }
            }
        }
        if (s2.startsWith("XXX_")) {
            String s4 = null;
            final String substring2 = s2.substring(s2.indexOf("_"));
            if (this.l == null) {
                if (this.m == 3) {
                    s4 = this.e(n, "CODE_TOOL");
                }
                else if (this.m == 4) {
                    s4 = this.e(n, "ID_TOOL");
                }
                else {
                    final int b = this.b("CODE_TOOL");
                    if (b == 1) {
                        s4 = this.e(n, "CODE_TOOL");
                        this.m = 3;
                    }
                    else if (b == 0) {
                        s4 = this.e(0, "CODE_TOOL");
                        this.l = s4;
                    }
                    else {
                        final int b2 = this.b("ID_TOOL");
                        if (b2 == 1) {
                            s4 = this.e(n, "ID_TOOL");
                            this.m = 4;
                        }
                        else if (b2 == 0) {
                            final String e = this.e(0, "ID_TOOL");
                            this.l = e;
                            s4 = e;
                            this.l = s4;
                        }
                    }
                }
                if (s4 == null || s4.length() == 0 || bz.a(this.l) == null) {
                    s4 = "GEN";
                    this.l = null;
                }
            }
            else {
                s4 = this.l;
            }
            s2 = s4 + substring2;
            boolean a = false;
            try {
                a = this.k.am().a(s2);
            }
            catch (Exception ex2) {
                if (a0.a.g()) {
                    a0.a.b("cannot access format container from MDGSession", ex2);
                }
            }
            if (!a) {
                s2 = "GEN" + substring2;
            }
        }
        return s2;
    }
    
    public final int i(final String s) {
        if (s != null && s.indexOf(".") > -1) {
            if (a0.a.g()) {
                a0.a.d("cannot resolve type for [" + s + "] - get embedded object first");
            }
            return 0;
        }
        final bj h = this.h(s);
        if (h == null) {
            if (a0.a.k()) {
                a0.a.i(this.k.as() + " resolveType:att[" + s + "] not found");
            }
            return 0;
        }
        try {
            final int b = h.b();
            if (b != 11) {
                return b;
            }
            final int index = s.indexOf("_");
            if (index <= 0) {
                throw new Exception(this.k.as() + " resolveType:no underscore found in attributename [" + s + "]");
            }
            final bj h2 = this.h("TYPE" + s.substring(index));
            if (h2 == null) {
                throw new Exception(this.k.as() + " resolveType: no typedef found");
            }
            return bj.a(this.f(h2.a()));
        }
        catch (Exception ex) {
            if (a0.a.k()) {
                a0.a.g(this.k.as() + " problems when resolving type of [" + s + "]", ex);
            }
            return 0;
        }
    }
    
    public String i(final int n, final String s) {
        if (a0.a.m()) {
            a0.a.k(this.k.as() + " MDGObject.getFormattedValue " + a0.a.a(this.x(), new Integer(n), s));
        }
        String s2 = null;
        if (this.f()) {
            try {
                final int index;
                if ((index = s.indexOf(".")) > 0) {
                    s2 = (String)this.a(s, n, index, 1, null);
                }
                else {
                    s2 = this.h(s).c(this, n);
                }
            }
            catch (Throwable t) {
                this.a(n, s, "getFormattedValue", this.j(n, s), t);
            }
        }
        if (s2 == null) {
            s2 = this.k.aj().g("DEFAULT_FORMATTED_VALUE");
        }
        return s2;
    }
    
    public final void a(final int n, final String s, final String s2, String s3, final Throwable t) {
        if (s3 == null) {
            s3 = "InternalError: UnknownException";
        }
        this.b(n, s, s3, s2, t);
    }
    
    public final void b(final int n, final String s, final String s2, final String s3, final Throwable t) {
        if (a0.a.g()) {
            a0.a.c(this.k.as() + " access " + this + "->" + s + "[" + n + "]" + " with " + s3 + ": " + s2 + ": " + t.getMessage(), t);
        }
        if (a0.a.j()) {
            a0.a.f(this.k.as() + " access " + this + "->" + s + "[" + n + "] with " + s3 + ": " + s2, t);
        }
    }
    
    public bc a() {
        return this.k.ao();
    }
    
    public final a3 a(final int n, final a3 a3) {
        if (this.i == null) {
            this.i = new Hashtable();
        }
        return this.i.put(new Integer(n), a3);
    }
    
    public final Object a(final int n, final String s, final String s2) {
        try {
            Integer n2 = null;
            if (s2.indexOf("Calendar") > -1) {
                n2 = new Integer(12);
            }
            final int index;
            if ((index = s.indexOf(".")) > 0) {
                return this.a(s, n, index, 2, n2);
            }
            return this.h(s).a(this, n, n2);
        }
        catch (Throwable t) {
            try {
                this.a(n, s, s2, this.j(n, s), t);
            }
            catch (Exception ex) {
                this.b(n, s, "InternalError:UnknownException ", s2, ex);
            }
            return null;
        }
    }
    
    public static void a(final a3 a3, final a3 a4) {
        a4.a(a3, a4);
        a3.c = a4.c;
        a3.d = a4.d;
        a3.e = a4.e;
        a3.f = a4.f;
        a3.g = a4.g;
        a3.j = a4.j;
        a3.i = a4.i;
        if (a3.h == null) {
            a3.h = a4.h;
        }
    }
    
    private String j(final int n, final String s) {
        final String f = this.f(n, s);
        if (f != null) {
            return f;
        }
        final String e = this.e(n, s);
        if (e == null) {
            return null;
        }
        final int b = this.c.a(s).b();
        if (b < 101) {
            return this.a(e, b, s, n);
        }
        try {
            a3.a.a(e);
        }
        catch (Exception ex) {
            return "InternalError: parsing of csv array " + bl.a(ex);
        }
        return null;
    }
    
    private String a(final String s, final int n, final String s2, final int n2) {
        try {
            this.c.a(s2).a(this, s, n2, null);
        }
        catch (Throwable t) {
            return "InternalServerError: parse for [" + s2 + "] as " + bj.a(n) + " with " + s + "; due to " + t;
        }
        return null;
    }
    
    private Object a(final String s, final int n, final int n2, final int n3, final Integer n4) throws Exception {
        final String substring = s.substring(0, n2);
        final String substring2 = s.substring(n2 + 1, s.length());
        final a3 d = this.d(n, substring);
        final bj h = d.h(substring2);
        switch (n3) {
            case 1: {
                return h.c(d, 0);
            }
            case 2: {
                return h.a(d, 0, n4);
            }
            case 3: {
                return h.a(d, 0);
            }
            default: {
                throw new Exception("wrong OUTTYPE in getInnerValue = " + n3);
            }
        }
    }
    
    static {
        a3.a = new b7(',');
        (a3.b = new Hashtable()).put("general", "GEN");
        a3.b.put("stocks", "STO");
        a3.b.put("warrants", "WAR");
        a3.b.put("currencies", "CUR");
        a3.b.put("funds", "FUN");
        a3.b.put("indices", "IND");
        a3.b.put("interests", "INT");
        a3.b.put("certificates", "CER");
        a3.b.put("countries", "COU");
        a3.b.put("ipos", "IPO");
        a3.b.put("portfolio", "POR");
    }
}
