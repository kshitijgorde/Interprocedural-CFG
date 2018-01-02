import java.util.Enumeration;
import java.util.Date;
import java.util.Vector;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class bm implements a0
{
    public static Hashtable a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Hashtable g;
    private String h;
    private String i;
    private Locale j;
    private DecimalFormatSymbols k;
    public Hashtable l;
    private long m;
    private Character n;
    private Character o;
    private String p;
    private String q;
    private Character r;
    private Character s;
    
    public bm(final az az, final bm bm) throws Exception {
        this.g = new Hashtable();
        this.l = new Hashtable();
        this.m = Long.MAX_VALUE;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        final long currentTimeMillis = System.currentTimeMillis();
        String s = "VERSION=" + az.aj().a("FORMAT_VERSION") + "&BLOCKSIZE=ALL";
        if (az.k) {
            s = s + "&" + az.aj().a("AUTH_ID_NAME") + "=" + az.aj().a("AUTH_ID_VALUE");
        }
        final a3[] a = a3.a(az, new String[] { "system/format_number_def_list?" + s, "system/format_date_def_list?" + s, "system/format_info_list?" + s, "customer/descriptor?" + s });
        this.m = Long.MAX_VALUE;
        for (int i = 0; i < a.length; ++i) {
            final long m = a[i].m();
            if (m < this.m) {
                this.m = m;
            }
        }
        final a3 a2 = a[0];
        final a3 a3 = a[1];
        final a3 a4 = a[2];
        final a3 a5 = a[3];
        if (a2.r() != 0 || a3.r() != 0 || a4.r() != 0 || a5.r() != 0) {
            throw new Exception("Did not get required format information from MDG object server");
        }
        this.h = az.aj().a("DEFAULT_LANG");
        final String f = a5.f("ALLOWED_LANGS");
        if (this.h == null || (f != null && f.indexOf(this.h) < 0)) {
            this.h = a5.f("DEFAULT_LANG");
        }
        this.i = a5.f("ID_COUNTRY");
        if (this.h != null && this.i != null) {
            this.j = new Locale(this.h, this.i);
        }
        else {
            this.j = Locale.getDefault();
            if (a0.a.g()) {
                a0.a.d(az.as() + " could not retrieve Locale information from " + a5.k());
            }
        }
        int j = 1;
        while (j < a4.w()) {
            if (this.h != null && this.h.equals(a4.e(j, "ID_LANG_ISO"))) {
                this.b = a4.e(j, "DEC_POINT");
                this.c = a4.e(j, "THOUSAND_SEP");
                this.e = a4.e(j, "PERCENT_PRE");
                this.f = a4.e(j, "PERCENT_POST");
                this.d = a4.e(j, "DEFAULT_VALUE");
                this.k = new DecimalFormatSymbols(this.j);
                if (this.b != null && this.b.length() > 0) {
                    this.k.setDecimalSeparator(this.b.charAt(0));
                    this.r = new Character(this.b.charAt(0));
                }
                if (this.c != null && this.c.length() > 0) {
                    this.k.setGroupingSeparator(this.c.charAt(0));
                    this.s = new Character(this.c.charAt(0));
                    break;
                }
                break;
            }
            else {
                ++j;
            }
        }
        for (int k = 1; k < a3.w(); ++k) {
            if (this.h != null && this.h.equals(a3.e(k, "ID_LANG_ISO"))) {
                final String e = a3.e(k, "ID_TYPE");
                this.g.put(e, new bw(e, null, "0", a3.e(k, "DEFINITION"), false));
            }
        }
        final Vector<bw> vector = new Vector<bw>();
        for (int l = 1; l < a2.w(); ++l) {
            final String e2 = a2.e(l, "MAX_VALUE");
            final String e3 = a2.e(l, "ID_TYPE");
            final String e4 = a2.e(l, "FLAG_INC_MAX_VALUE");
            final String e5 = a2.e(l, "DEFINITION");
            if (e2.indexOf("DEFAULT") == -1) {
                vector.addElement(new bw(e3, e2, e4, e5, true));
            }
            else {
                this.g.put(e3, new bw(e3, (e2.indexOf("DEFAULT") != -1) ? null : e2, e4, e5, true));
            }
        }
        for (int n = 0; n < vector.size(); ++n) {
            final bw bw = vector.elementAt(n);
            final bw bw2 = this.g.get(bw.a);
            if (bw2 == null) {
                throw new Exception("There was not default FormatDefinition for " + bw.a + " available");
            }
            bw2.a(bw);
            this.g.put(bw2.a, bw2);
        }
        this.a(az);
        if (bm != null) {
            this.p = bm.p;
            this.q = bm.q;
            this.n = bm.n;
            this.o = bm.o;
            this.b();
        }
        final long n2 = System.currentTimeMillis() - currentTimeMillis;
        if (a0.a.h()) {
            a0.a.f(az.as() + " RETRIEVED Formatter information  (" + n2 + "ms) which expires at " + new Date(this.a()));
        }
    }
    
    public final long a() {
        return this.m;
    }
    
    private final void a(final az az) {
        final Enumeration<String> keys = this.g.keys();
        while (keys.hasMoreElements()) {
            final bw bw = this.g.get(keys.nextElement());
            this.a(bw, az);
            if (!bw.a()) {
                for (int i = 0; i < bw.g.length; ++i) {
                    this.a(bw.g[i], az);
                }
            }
        }
    }
    
    public final String a(final Object o, final String s, final az az, final int n) {
        if (o == null) {
            if (this.d != null) {
                return this.d;
            }
            return "";
        }
        else {
            if (o instanceof String) {
                return (String)o;
            }
            if (s == null) {
                return o.toString();
            }
            by a = null;
            try {
                a = this.a(o, s);
            }
            catch (Exception ex) {
                if (a0.a.g()) {
                    a0.a.b(az.as() + " did not get Formatter for " + o + ":" + s, ex);
                }
            }
            if (a == null) {
                return o.toString();
            }
            return a.a(o, az, n);
        }
    }
    
    private final void a(final bw bw, final az az) {
        final String b = bw.b;
        if (!bw.e) {
            bw.f = new bx(b, az);
            if (bw.a.indexOf("TIME") > -1) {
                bw.f.a = 3;
            }
            else if (bw.a.indexOf("DATE") > -1) {
                bw.f.a = 2;
            }
            else if (a0.a.g()) {
                a0.a.d("did not find DATE or TIME as part of name in FormatDefinition");
            }
        }
        else {
            final StringBuffer sb = new StringBuffer("");
            String s = "";
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            boolean b5 = false;
            int digit = 0;
            int n = 0;
            final char[] charArray = b.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                final char c = charArray[i];
                switch (c) {
                    case 84: {
                        b4 = true;
                        break;
                    }
                    case 77: {
                        s += c;
                        if (i < charArray.length - 1) {
                            s += charArray[++i];
                            break;
                        }
                        break;
                    }
                    case 68: {
                        s += c;
                        if (i < charArray.length - 1) {
                            s += charArray[++i];
                            break;
                        }
                        break;
                    }
                    case 67: {
                        while (i < charArray.length - 1) {
                            if (!Character.isDigit(charArray[++i])) {
                                --i;
                                break;
                            }
                        }
                        break;
                    }
                    case 115: {
                        b5 = true;
                        int n2 = 0;
                        while (i < charArray.length - 1) {
                            final char c2 = charArray[++i];
                            if (!Character.isDigit(c2)) {
                                --i;
                                break;
                            }
                            n += Character.digit(c2, 10) * (int)Math.pow(10.0, n2);
                            ++n2;
                        }
                        break;
                    }
                    case 43: {
                        b2 = true;
                        break;
                    }
                    case 80: {
                        b3 = true;
                        break;
                    }
                    default: {
                        digit = Character.digit(charArray[i], 10);
                        break;
                    }
                }
            }
            if (b3 && this.e != null && this.e.length() > 0) {
                sb.append("'" + this.e + "'");
            }
            if (b4) {
                sb.append((digit > 0 || b5) ? "###,##0." : "###,##0");
            }
            else {
                sb.append("0.");
            }
            for (int j = 0; j < digit; ++j) {
                sb.append("0");
            }
            if (b5) {
                if (n > 0) {
                    final int n3 = n - digit;
                    if (n3 > 0) {
                        for (int k = 0; k < n3; ++k) {
                            sb.append("#");
                        }
                    }
                }
                else {
                    sb.append("##########");
                }
            }
            if (b3 && this.f != null) {
                sb.append("'" + this.f + "'");
            }
            String s2 = sb.toString();
            if (b2) {
                s2 = "+" + s2 + ";-" + s2;
            }
            if (s.length() == 0) {
                final b0 f = new b0(s2);
                if (this.k != null) {
                    f.a(this.k);
                }
                bw.f = f;
            }
            else {
                final b2 f2 = new b2(s2, s);
                if (this.k != null) {
                    f2.a(this.k);
                }
                bw.f = f2;
            }
        }
    }
    
    public final by a(final Object o, final String s) throws Exception {
        if (s == null) {
            return null;
        }
        by by = null;
        final bw bw = this.g.get(s);
        if (o != null && bw != null && !bw.a()) {
            double abs;
            try {
                abs = Math.abs(new Double(o.toString()));
            }
            catch (NumberFormatException ex) {
                throw new Exception("FormatDefinition indicating not to be default has value " + o + "; " + ex);
            }
            if (abs < 1.0) {
                by = bw.a(abs);
            }
            else {
                by = bw.f;
            }
        }
        else if (bw != null) {
            by = bw.f;
        }
        if (by != null) {
            return by;
        }
        if (bw != null) {
            return bw.f;
        }
        if (s.startsWith("GEN_")) {
            return null;
        }
        final int index = s.indexOf(95);
        String string = "";
        if (index > -1) {
            string = "GEN" + s.substring(index);
        }
        final bw bw2 = this.g.get(string);
        if (bw2 != null) {
            return bw2.f;
        }
        return null;
    }
    
    public final void b() {
        if (this.k != null) {
            if (this.n != null) {
                this.k.setDecimalSeparator(this.n);
            }
            if (this.o != null) {
                this.k.setGroupingSeparator(this.o);
            }
        }
        else {
            this.k = new DecimalFormatSymbols(this.j);
            if (this.n != null) {
                this.k.setDecimalSeparator(this.n);
            }
            if (this.o != null) {
                this.k.setGroupingSeparator(this.o);
            }
        }
        if (this.n != null || this.o != null || this.p != null || this.q != null) {
            final Enumeration<Object> keys = this.g.keys();
            while (keys.hasMoreElements()) {
                final bw bw = this.g.get(keys.nextElement());
                if (bw.e && (this.n != null || this.o != null)) {
                    ((b0)bw.f).a(this.k);
                }
                else {
                    if (bw.e) {
                        continue;
                    }
                    if (this.p != null && bw.f.a == 2) {
                        ((bx)bw.f).a(this.p);
                    }
                    if (this.q == null || bw.f.a != 3) {
                        continue;
                    }
                    ((bx)bw.f).a(this.q);
                }
            }
        }
    }
    
    public final boolean a(final String s) {
        return this.g.containsKey(s);
    }
    
    static {
        bm.a = new Hashtable();
    }
}
