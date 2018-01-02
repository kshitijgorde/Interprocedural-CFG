import java.util.StringTokenizer;
import java.util.Hashtable;
import com.is_teledata.livehtml.LHTML;

// 
// Decompiled by Procyon v0.5.30
// 

public class dc extends c1 implements cx, dd, d
{
    private String a;
    private String b;
    private String[] c;
    private String[] d;
    private String[] e;
    private String[] f;
    private int g;
    private int h;
    private int i;
    private int[] j;
    private Integer k;
    private long l;
    private double m;
    private boolean n;
    private boolean o;
    private Boolean p;
    private boolean q;
    private cw r;
    private LHTML s;
    private de t;
    private w[] u;
    
    public static dc a(final String s, final String s2, final String[] array, final LHTML lhtml) {
        final w[] array2 = new w[array.length];
        for (int i = 0; i < array.length; array2[i] = new w(array[i++])) {}
        int c;
        if (i("event", array2)) {
            c = c("event", array2);
        }
        else {
            c = 254;
        }
        String[] h;
        if (i("att", array2)) {
            h = h("att", array2);
        }
        else {
            if ((c & 0xDE) != 0x0 && d.a.f()) {
                d.a.c("Missing Attribute-Names in LHTMLSubscription! Couldn't find att-property in attrDescr!");
                d.a.c("Registering ALL attributes.");
            }
            h = null;
        }
        if ((c & 0x3FFFFFFF) == 0x0) {
            c |= 0x1FE;
        }
        final int n = c & 0x3FFFFFFF;
        int[] g;
        if (i("row", array2)) {
            g = g("row", array2);
        }
        else {
            g = null;
        }
        return new dc(s, s2, array, new c2(n, h, g), lhtml);
    }
    
    public dc(final String b, final String a, final String[] array, final c2 c2, final LHTML s) {
        super(a, c2);
        this.p = new Boolean(false);
        this.q = false;
        this.t = new de();
        this.b = b;
        this.a = a;
        this.s = s;
        this.u = new w[array.length];
        for (int i = 0; i < array.length; this.u[i] = new w(array[i++])) {}
        this.c = c2.b();
        if (i("att_reset", this.u)) {
            this.d = h("att_reset", this.u);
        }
        else {
            this.d = this.c;
        }
        if (i("cb_attset", this.u)) {
            this.o = b("cb_attset", this.u);
        }
        else {
            this.o = false;
        }
        if (i("event", this.u)) {
            this.i = c("event", this.u);
        }
        else {
            this.i = 6;
        }
        this.j = c2.a();
        if (i("row_reset", this.u)) {
            this.k = d("row_reset", this.u);
        }
        else {
            this.k = null;
        }
        if (i("mode", this.u)) {
            this.g = c("mode", this.u);
        }
        else if (i("state", this.u)) {
            this.g = 2;
        }
        else {
            this.g = 0;
        }
        if (i("mode_reset", this.u)) {
            this.h = c("mode_reset", this.u);
        }
        else {
            this.h = this.g;
        }
        if (i("time_reset", this.u)) {
            this.l = e("time_reset", this.u);
            this.l += 100L;
            this.l = this.l / 100L * 100L;
            if ((this.i & 0xC0000000) == 0x0) {
                this.i |= 0x40000000;
            }
        }
        else {
            this.l = 2000L;
        }
        if (i("init", this.u)) {
            this.m = f("init", this.u);
            this.n = true;
        }
        else {
            this.n = false;
        }
        if (i("state", this.u)) {
            this.e = a("state", 3, this.u);
        }
        else {
            this.e = new String[] { "undefined_state", "undefined_state", "undefined_state" };
        }
        if (i("state_reset", this.u)) {
            this.f = a("state_reset", 3, this.u);
        }
        else {
            this.f = this.e;
        }
        final c1 c3 = new c1(this.a, this, c2);
        if (d.a.i()) {
            d.a.g("created LHTMLSubscription " + c3.toString());
        }
    }
    
    public void c() {
        synchronized (this.p) {
            this.p = new Boolean(true);
            this.r = null;
            this.s = null;
            if (this.t != null) {
                for (int i = 0; i < this.t.a(); ++i) {
                    final df df = (df)this.t.a(i);
                    if (df != null) {
                        df.d();
                    }
                }
            }
            this.t = null;
        }
    }
    
    public void a(final boolean q) {
        this.q = q;
    }
    
    public void a(final cw r, final c1 c1) {
        if (this.q) {
            return;
        }
        synchronized (this.p) {
            if (this.p) {
                return;
            }
            if (!r.f()) {
                if (d.a.i()) {
                    d.a.g("received callback for non-valid object " + r + "; skipping callback");
                }
                return;
            }
            synchronized (this.r = r) {
                final c5[] a = this.r.a(c1.b());
                Object ai = null;
                if (!this.r.af()) {
                    ai = this.r.ai();
                }
                final c2 b = c1.b();
                if (a == null && ai == null) {
                    if (d.a.g()) {
                        d.a.d("received callback for sub " + c1.toString() + " with no update or operation events");
                    }
                    return;
                }
                if (d.a.i()) {
                    d.a.g("received callback for sub " + c1 + " with operations " + ai + " and updates " + a);
                }
                df df = null;
                if (ai != null && (this.i & 0x100) != 0x0) {
                    if (d.a.i()) {
                        d.a.g("handling " + ai.length + " operation events");
                    }
                    int n = 0;
                    final StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < ai.length; ++i) {
                        final c6 c2 = ai[i];
                        final int[] a2 = c2.a();
                        switch (c2.b()) {
                            case 102: {
                                sb.append(LHTML.DELIMITER + "P");
                                Object a3 = this.t.a(a2[0]);
                                for (int j = 1; j < a2.length; ++j) {
                                    final Object a4 = this.t.a(a2[j]);
                                    this.t.b(a3, a2[j]);
                                    a3 = a4;
                                }
                                this.t.b(a3, a2[0]);
                                break;
                            }
                            case 101: {
                                sb.append(LHTML.DELIMITER + "R");
                                for (int k = 0; k < a2.length; ++k) {
                                    this.t.d(a2[k]);
                                }
                                break;
                            }
                            case 100: {
                                sb.append(LHTML.DELIMITER + "I");
                                for (int l = 0; l < a2.length; ++l) {
                                    df = new df(this.k, this);
                                    if (this.n) {
                                        df.a(this.m);
                                    }
                                    this.t.a(df, a2[l]);
                                }
                                break;
                            }
                            case 103: {
                                sb.append(LHTML.DELIMITER + "C");
                                for (int n2 = 0; n2 < a2.length; ++n2) {
                                    if (!this.t.c(a2[n2])) {
                                        df = new df(this.k, this);
                                        if (this.n) {
                                            df.a(this.m);
                                        }
                                        this.t.b(df, a2[n2]);
                                    }
                                }
                                break;
                            }
                        }
                        int n3 = 0;
                        for (int n4 = 0; n4 < a2.length; ++n4) {
                            if (a2[n4] != 0) {
                                ++n3;
                            }
                        }
                        sb.append(LHTML.DELIMITER + n3);
                        for (int n5 = 0; n5 < a2.length; ++n5) {
                            if (a2[n5] != 0) {
                                sb.append(LHTML.DELIMITER + a2[n5]);
                                ++n;
                            }
                        }
                        n += 2;
                    }
                    this.s.addMessage(LHTML.DELIMITER + this.b + LHTML.DELIMITER + 256 + LHTML.DELIMITER + LHTML.DELIMITER + n + sb.toString());
                }
                if (a != null) {
                    Object o = null;
                    int length;
                    if (this.g == 0 || this.g == 1) {
                        length = this.c.length;
                    }
                    else {
                        length = 1;
                    }
                    for (int n6 = 0; n6 < a.length; ++n6) {
                        boolean b2 = false;
                        String s = "";
                        final int a5 = a[n6].a();
                        final Hashtable a6 = a[n6].a(b);
                        final int d = a[n6].d();
                        if ((d & this.i) != 0x0) {
                            b2 = true;
                            switch (this.g) {
                                case 0: {
                                    if (this.o || a6 == null) {
                                        for (int n7 = 0; n7 < length; ++n7) {
                                            s = s + LHTML.DELIMITER + this.a(this.r, a5, this.c[n7], true);
                                        }
                                        break;
                                    }
                                    for (int n8 = 0; n8 < length; ++n8) {
                                        if (a6.containsKey(this.c[n8])) {
                                            s = s + LHTML.DELIMITER + this.a(this.r, a5, this.c[n8], true);
                                        }
                                        else {
                                            s += LHTML.DELIMITER;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.o || a6 == null) {
                                        for (int n9 = 0; n9 < length; ++n9) {
                                            s = s + LHTML.DELIMITER + this.a(this.r, a5, this.c[n9], false);
                                        }
                                        break;
                                    }
                                    for (int n10 = 0; n10 < length; ++n10) {
                                        if (a6.containsKey(this.c[n10])) {
                                            s = s + LHTML.DELIMITER + this.a(this.r, a5, this.c[n10], false);
                                        }
                                        else {
                                            s += LHTML.DELIMITER;
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    s = s + LHTML.DELIMITER + this.e[2];
                                    break;
                                }
                                case 3: {
                                    if (this.r.b(this.c[0]) >= 0) {
                                        o = this.r.g(a5, this.c[0]);
                                    }
                                    else if (d.a.g()) {
                                        d.a.d("Can't find attribute " + this.c[0] + " in PushSubscription.onvPushCallback");
                                    }
                                    if (!(o instanceof Number)) {
                                        s = s + LHTML.DELIMITER + this.e[2];
                                        break;
                                    }
                                    final double doubleValue = ((Number)o).doubleValue();
                                    if (doubleValue > 0.0) {
                                        s = s + LHTML.DELIMITER + this.e[0];
                                        break;
                                    }
                                    if (doubleValue < 0.0) {
                                        s = s + LHTML.DELIMITER + this.e[1];
                                        break;
                                    }
                                    s = s + LHTML.DELIMITER + this.e[2];
                                    break;
                                }
                                case 4: {
                                    if (this.r.b(this.c[0]) >= 0) {
                                        o = this.r.g(a5, this.c[0]);
                                    }
                                    else if (d.a.g()) {
                                        d.a.d("Can't find attribute " + this.c[0] + " in LHTMLSubscription.onvPushCallback");
                                    }
                                    if (this.t.c(a5)) {
                                        df = (df)this.t.a(a5);
                                    }
                                    else {
                                        df = new df(this.k, this);
                                        if (this.n) {
                                            df.a(this.m);
                                        }
                                        this.t.b(df, a5);
                                    }
                                    if (o instanceof Number) {
                                        final double doubleValue2 = ((Number)o).doubleValue();
                                        if (df.c() && (a[n6].d() & 0x2) == 0x0) {
                                            final double a7 = df.a();
                                            if (doubleValue2 > a7) {
                                                s = s + LHTML.DELIMITER + this.e[0];
                                            }
                                            else if (doubleValue2 < a7) {
                                                s = s + LHTML.DELIMITER + this.e[1];
                                            }
                                            else {
                                                s = s + LHTML.DELIMITER + this.e[2];
                                            }
                                        }
                                        else {
                                            s = s + LHTML.DELIMITER + this.e[2];
                                        }
                                        df.a(doubleValue2);
                                        break;
                                    }
                                    s = s + LHTML.DELIMITER + this.e[2];
                                    break;
                                }
                            }
                        }
                        if (s == "" && b2) {
                            if (d.a.f()) {
                                d.a.c("Unexpected (empty) object attribute in LHTMLSubscription.pushCallback");
                            }
                        }
                        else {
                            this.s.addMessage(LHTML.DELIMITER + this.b + LHTML.DELIMITER + d + LHTML.DELIMITER + a5 + LHTML.DELIMITER + length + s);
                            if ((this.i & 0xC0000000) != 0x0) {
                                if (df == null) {
                                    if (this.t.c(a5)) {
                                        df = (df)this.t.a(a5);
                                    }
                                    else {
                                        df = new df(this.k, this);
                                        this.t.b(df, a5);
                                    }
                                }
                                df.a((this.i & Integer.MIN_VALUE) != 0x0);
                                df = null;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void a(final df df) {
        if (this.q) {
            return;
        }
        final int n = ((this.i & Integer.MIN_VALUE) != 0x0) ? Integer.MIN_VALUE : 1073741824;
        String s = "";
        Object o = null;
        int length;
        if (this.h == 0 || this.h == 1) {
            length = this.d.length;
        }
        else {
            length = 1;
        }
        final Integer b = df.b();
        int intValue = -1;
        boolean b2 = false;
        if (b == null) {
            for (int i = 0; i < this.t.a(); ++i) {
                if (df == this.t.a(i)) {
                    intValue = i;
                    b2 = true;
                    break;
                }
            }
        }
        else {
            intValue = b;
        }
        if (!b2 && d.a.f()) {
            d.a.c("Couldn't find row-number in LHTMLSubscription.rowCallback()");
        }
        synchronized (this.p) {
            if (this.p) {
                return;
            }
            synchronized (this.r) {
                switch (this.h) {
                    case 0: {
                        for (int j = 0; j < length; ++j) {
                            s = s + LHTML.DELIMITER + this.a(this.r, intValue, this.d[j], true);
                        }
                        break;
                    }
                    case 1: {
                        for (int k = 0; k < length; ++k) {
                            s = s + LHTML.DELIMITER + this.a(this.r, intValue, this.d[k], false);
                        }
                        break;
                    }
                    case 2: {
                        s = s + LHTML.DELIMITER + this.f[2];
                        break;
                    }
                    case 3: {
                        if (this.r.b(this.d[0]) >= 0) {
                            o = this.r.g(intValue, this.d[0]);
                        }
                        else if (d.a.g()) {
                            d.a.d("Can't find attribute " + this.d[0] + " in LHTMLSubscription.rowCallback");
                        }
                        if (!(o instanceof Number)) {
                            s = s + LHTML.DELIMITER + this.f[2];
                            break;
                        }
                        final double doubleValue = ((Number)o).doubleValue();
                        if (doubleValue > 0.0) {
                            s = s + LHTML.DELIMITER + this.f[0];
                            break;
                        }
                        if (doubleValue < 0.0) {
                            s = s + LHTML.DELIMITER + this.f[1];
                            break;
                        }
                        s = s + LHTML.DELIMITER + this.f[2];
                        break;
                    }
                    case 4: {
                        if (this.r.b(this.d[0]) >= 0) {
                            o = this.r.g(intValue, this.d[0]);
                        }
                        else if (d.a.g()) {
                            d.a.d("Can't find attribute " + this.d[0] + " in LHTMLSubscription.rowCallback");
                        }
                        if (o instanceof Number) {
                            final double doubleValue2 = ((Number)o).doubleValue();
                            if (df.c()) {
                                final double a = df.a();
                                if (doubleValue2 > a) {
                                    s = s + LHTML.DELIMITER + this.f[0];
                                }
                                else if (doubleValue2 < a) {
                                    s = s + LHTML.DELIMITER + this.f[1];
                                }
                                else {
                                    s = s + LHTML.DELIMITER + this.f[2];
                                }
                            }
                            else {
                                s = s + LHTML.DELIMITER + this.f[2];
                            }
                            df.a(doubleValue2);
                            break;
                        }
                        s = s + LHTML.DELIMITER + this.f[2];
                        break;
                    }
                }
            }
            this.s.addMessage(LHTML.DELIMITER + this.b + LHTML.DELIMITER + n + LHTML.DELIMITER + intValue + LHTML.DELIMITER + length + s);
        }
    }
    
    public long d() {
        return this.l;
    }
    
    public int e() {
        return this.i;
    }
    
    public cb f() {
        synchronized (this.p) {
            if (this.s != null) {
                return this.s.getScheduler();
            }
            return null;
        }
    }
    
    private String a(final a3 a3, final int n, final String s, final boolean b) {
        if (a3.b(s) >= 0) {
            return b ? a3.i(n, s) : a3.e(n, s);
        }
        if (d.a.g()) {
            d.a.d("Couldn't find attribute " + s + " in row " + n + " of obj " + a3 + " in LHTMLSubscription.checkValue");
        }
        return "";
    }
    
    private static String a(final String s, final w[] array) {
        String a = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].m(s)) {
                a = array[i].a(s, array[i]);
                break;
            }
        }
        return a;
    }
    
    private static boolean b(final String s, final w[] array) {
        final String a = a(s, array);
        return a != null && !a.equals("0") && !a.equalsIgnoreCase("false");
    }
    
    private static int c(final String s, final w[] array) {
        final Integer d = d(s, array);
        if (d != null) {
            return d;
        }
        return Integer.MIN_VALUE;
    }
    
    private static Integer d(final String s, final w[] array) {
        final String a = a(s, array);
        Integer n = null;
        if (a != null) {
            try {
                n = new Integer(a);
            }
            catch (Exception ex) {
                if (d.a.g()) {
                    d.a.d("Failed to convert attribute " + s + " in LHTMLSubscription.getIntegerAttribute: " + a);
                }
            }
        }
        return n;
    }
    
    private static long e(final String s, final w[] array) {
        final String a = a(s, array);
        long long1 = Long.MIN_VALUE;
        if (a != null) {
            try {
                long1 = Long.parseLong(a);
            }
            catch (Exception ex) {
                if (d.a.g()) {
                    d.a.d("Failed to convert attribute " + s + " in LHTMLSubscription.getLongAttribute: " + a);
                }
            }
        }
        return long1;
    }
    
    private static double f(final String s, final w[] array) {
        final String a = a(s, array);
        double n = Double.MIN_VALUE;
        if (a != null) {
            try {
                n = new Double(a);
            }
            catch (Exception ex) {
                if (d.a.g()) {
                    d.a.d("Failed to convert attribute " + s + " in LHTMLSubscription.getDoubleAttribute: " + a);
                }
            }
        }
        return n;
    }
    
    private static int[] g(final String s, final w[] array) {
        final String a = a(s, array);
        if (a == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a, ",");
        final int[] array2 = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                array2[n++] = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (Exception ex) {
                if (d.a.g()) {
                    d.a.d("Failed to parse intArray " + s + " in LHTMLSubscription.getIntAttributes");
                }
                return null;
            }
        }
        return array2;
    }
    
    private static String[] h(final String s, final w[] array) {
        return a(s, -1, array);
    }
    
    private static String[] a(final String s, int countTokens, final w[] array) {
        String a = a(s, array);
        if (a == null) {
            if (d.a.g()) {
                d.a.d("Unable to find attribute " + s + " in PushSubscription.getAttributes");
            }
            a = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a, ",");
        String[] array2;
        if (countTokens >= 0) {
            array2 = new String[countTokens];
        }
        else {
            countTokens = stringTokenizer.countTokens();
            array2 = new String[countTokens];
        }
        int i = 0;
        String nextToken = "undefined";
        while (stringTokenizer.hasMoreTokens()) {
            if (i >= countTokens) {
                break;
            }
            nextToken = stringTokenizer.nextToken();
            array2[i++] = nextToken;
        }
        while (i < countTokens) {
            array2[i++] = nextToken;
        }
        return array2;
    }
    
    private static boolean i(final String s, final w[] array) {
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].m(s)) {
                b = true;
                break;
            }
        }
        return b;
    }
}
