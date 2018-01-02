import java.util.TimeZone;
import java.util.Date;
import java.util.Locale;
import java.util.Hashtable;
import java.util.Calendar;
import java.text.DateFormatSymbols;

// 
// Decompiled by Procyon v0.5.30
// 

public class bx extends by implements a0
{
    private String a;
    public DateFormatSymbols b;
    public Calendar c;
    public String d;
    public Boolean e;
    public Boolean f;
    public static l g;
    public static long h;
    private long i;
    private boolean j;
    public static Hashtable k;
    public static byte[] l;
    public static Calendar m;
    public static Calendar n;
    public static String o;
    
    public bx(final String s, final az az) {
        this.i = 0L;
        this.j = false;
        final at aj = az.aj();
        this.d = aj.a("DEFAULT_TIMEZONE");
        this.a(s);
        String a = aj.a("DEFAULT_LANG");
        if (a == null) {
            a = "de";
        }
        this.b = new DateFormatSymbols((Locale)bx.k.get(a));
        this.c = Calendar.getInstance();
        final Boolean c = aj.c("USE_TIMEZONE");
        if (c != null) {
            this.j = c;
        }
        if (bx.o == null) {
            boolean booleanValue = false;
            final Boolean c2 = aj.c("USE_DATE_TIMEZONE");
            if (c2 != null) {
                booleanValue = c2;
            }
            if (booleanValue) {
                final Integer a2 = bz.a(az, "Europe/Berlin");
                if (a2 == null) {
                    bx.o = "1";
                }
                else {
                    bx.o = (int)a2 + "";
                }
            }
            else {
                bx.o = this.d;
            }
        }
    }
    
    public final void a(final String a) {
        this.a = a;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final String a(final Object o, final az az, final int n) {
        if (a0.a.m()) {
            a0.a.k(az.as() + " DateTimeFormatter.format " + o);
        }
        this.i = 0L;
        long n2 = 0L;
        String s = this.d;
        if (!this.j) {
            synchronized (bx.m) {
                this.i = (bx.m.get(16) + bx.m.get(15)) / 1000L;
            }
        }
        switch (n) {
            case 9:
            case 10: {
                final b3 b3 = (b3)o;
                n2 = b3.a();
                if (this.c()) {
                    s = b3.b();
                }
                if (s == null || s.length() == 0) {
                    s = this.d;
                    break;
                }
                break;
            }
            case 4: {
                n2 = ((Date)o).getTime();
                s = bx.o;
                break;
            }
            case 5: {
                synchronized (bx.n) {
                    bx.n.setTime((Date)o);
                    bx.n.set(1, bx.m.get(1));
                    bx.n.set(2, bx.m.get(2));
                    bx.n.set(6, bx.m.get(6));
                    n2 = bx.n.getTime().getTime();
                    s = this.d;
                    break;
                }
            }
            case 6: {
                n2 = (long)o;
                break;
            }
            default: {
                if (a0.a.g()) {
                    a0.a.d(az.as() + " DateTimeFormatter: unknown attribute type " + n + ", cannot format " + o + " as date");
                    break;
                }
                break;
            }
        }
        try {
            return this.a(n2, s, az, n);
        }
        catch (Exception ex) {
            throw new dx("could not format " + o + " with pattern " + this.a, ex);
        }
    }
    
    private void b() {
        final char[] charArray = this.a.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '%' && charArray.length > i + 1 && charArray[i + 1] == 'N') {
                this.e = new Boolean(true);
            }
            else if (charArray[i] == '%' && charArray.length > i + 1 && charArray[i + 1] == 'O') {
                this.f = new Boolean(true);
            }
        }
        if (this.e == null) {
            this.e = new Boolean(false);
        }
        if (this.f == null) {
            this.f = new Boolean(false);
        }
    }
    
    private boolean c() {
        if (this.e == null) {
            this.b();
        }
        return this.e;
    }
    
    private boolean d() {
        if (this.f == null) {
            this.b();
        }
        return this.f;
    }
    
    public final String a(long n, String d, final az az, final int n2) {
        if (a0.a.m()) {
            a0.a.k(az.as() + " DateTimeFormatter.formatInternal " + a0.a.a(new Long(n), d, new Date(n)));
        }
        if (n2 != 4 && !this.c()) {
            d = this.d;
        }
        if (this.d()) {
            d = "385";
        }
        long n3 = this.i;
        String d2 = "GMT";
        b4 a = null;
        if (this.j) {
            a = b4.a(az, b4.b(n), d);
        }
        if (a != null && a.g()) {
            if (a0.a.k()) {
                a0.a.i(az.as() + " DateTimeFormatter: using timezone object " + a + " for " + new Date(n));
            }
            final int a2 = a.a(n);
            d2 = a.d(a2);
            n3 = a.c(a2);
        }
        if (a0.a.k()) {
            a0.a.i(az.as() + " DateTimeFormatter: offset=" + n3 + " nameTZ=" + d2);
        }
        n = n / 1000L + n3;
        final long n4 = (long)Math.floor(n / 86400L);
        final long n5 = n - n4 * 86400L;
        final long n6 = n4 + 719469L;
        int n7 = (int)Math.floor((4L * n6 - 1L) / 146097L);
        final int n8 = (int)Math.floor((int)Math.floor(4L * n6 - 1L - 146097 * n7) / 4L);
        int n9 = (int)Math.floor((4 * n8 + 3) / 1461);
        final int n10 = (int)Math.floor(((int)Math.floor(4 * n8 + 3 - 1461 * n9) + 4) / 4);
        int n11 = (int)Math.floor((5 * n10 - 3) / 153);
        final int n12 = (int)Math.floor(((int)Math.floor(5 * n10 - 3 - 153 * n11) + 5) / 5);
        if (n11 < 10) {
            n11 += 3;
        }
        else {
            n11 -= 9;
            if (n9++ == 99) {
                n9 = 0;
                ++n7;
            }
        }
        final int abs = Math.abs((int)Math.floor(n5 / 3600L));
        final long n13 = n5 - abs * 3600;
        final int n14 = (int)Math.floor(n13 / 60L);
        final long n15 = n13 - n14 * 60;
        final int n16 = (int)Math.floor(n15);
        final int n17 = (int)n15 - n16;
        final int n18 = n7 * 100 + n9;
        final StringBuffer sb = new StringBuffer();
        final char[] charArray = this.a.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == '%') {
                final char c = charArray[i + 1];
                switch (c) {
                    case 97: {
                        sb.append(this.a(n12, n11, n18));
                        break;
                    }
                    case 65: {
                        sb.append(this.b(n12, n11, n18));
                        break;
                    }
                    case 98: {
                        sb.append(this.a(n11));
                        break;
                    }
                    case 66: {
                        sb.append(this.b(n11));
                        break;
                    }
                    case 67: {
                        sb.append(n7);
                        break;
                    }
                    case 100: {
                        sb.append(this.a(n12, 2));
                        break;
                    }
                    case 101: {
                        sb.append(n12);
                        break;
                    }
                    case 72: {
                        sb.append(this.a(abs, 2));
                        break;
                    }
                    case 73: {
                        sb.append((abs >= 13) ? (abs - 12) : abs);
                        break;
                    }
                    case 109: {
                        sb.append(this.a(n11, 2));
                        break;
                    }
                    case 77: {
                        sb.append(this.a(n14, 2));
                        break;
                    }
                    case 110: {
                        sb.append("\n");
                        break;
                    }
                    case 78: {
                        break;
                    }
                    case 79: {
                        break;
                    }
                    case 112: {
                        sb.append((abs >= 12) ? "pm" : "am");
                        break;
                    }
                    case 83: {
                        sb.append(this.a(n16, 2));
                        break;
                    }
                    case 116: {
                        sb.append("\t");
                        break;
                    }
                    case 119: {
                        sb.append(this.c(n12, n11, n18));
                        break;
                    }
                    case 85: {
                        sb.append(this.d(n12, n11, n18));
                        break;
                    }
                    case 121: {
                        sb.append(this.a(n18, 2));
                        break;
                    }
                    case 89: {
                        sb.append(n18);
                        break;
                    }
                    case 122: {
                        if (n17 > 0) {
                            sb.append(this.a(n17, 3));
                            break;
                        }
                        break;
                    }
                    case 90: {
                        sb.append(d2);
                        break;
                    }
                    case 37: {
                        sb.append("%");
                        break;
                    }
                    default: {
                        sb.append(c);
                        break;
                    }
                }
                ++i;
            }
            else {
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }
    
    public final String a(final int n, final int n2, final int n3) {
        this.e(n, n2, n3);
        return this.b.getShortWeekdays()[this.c.get(7)];
    }
    
    public final String b(final int n, final int n2, final int n3) {
        this.e(n, n2, n3);
        return this.b.getWeekdays()[this.c.get(7)];
    }
    
    private void e(final int n, final int n2, final int n3) {
        this.c.set(5, n);
        this.c.set(2, n2 - 1);
        this.c.set(1, n3);
    }
    
    public final String a(final int n) {
        return this.b.getShortMonths()[n - 1];
    }
    
    public final String b(final int n) {
        return this.b.getMonths()[n - 1];
    }
    
    public final int c(final int n, final int n2, final int n3) {
        this.e(n, n2, n3);
        return this.c.get(7);
    }
    
    public final int d(final int n, final int n2, final int n3) {
        this.e(n, n2, n3);
        return this.c.get(3);
    }
    
    public final String a(final int n, final int n2) {
        final String string = "" + n;
        final int length = string.length();
        if (length == n2) {
            return string;
        }
        if (length < n2) {
            final int n3 = n2 - length;
            if (n3 < bx.l.length) {
                try {
                    synchronized (bx.l) {
                        return new String(bx.l, 0, n3, "ISO-8859-1") + string;
                    }
                }
                catch (Exception ex) {
                    return string;
                }
            }
            return string;
        }
        return string.substring(string.length() - n2);
    }
    
    public String toString() {
        return "DateTimeFormatter[" + this.a + "]";
    }
    
    static {
        bx.g = new l(0);
        bx.h = 0L;
        bx.k = new Hashtable();
        bx.l = new byte[20];
        boolean b = false;
        try {
            bx.m = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
            bx.n = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
        }
        catch (Exception ex) {
            if (a0.a.i()) {
                a0.a.g("DateTimeFormatter: can't find timezone Europe/Berlin try to switch to ECT");
            }
            b = true;
        }
        if (b) {
            try {
                bx.m = Calendar.getInstance(TimeZone.getTimeZone("ECT"));
                bx.n = Calendar.getInstance(TimeZone.getTimeZone("ECT"));
            }
            catch (Exception ex2) {
                if (a0.a.i()) {
                    a0.a.g("DateTimeFormatter: can't find timezone ECT");
                }
            }
        }
        bx.k.put("de", new Locale("de", "DE"));
        bx.k.put("fr", new Locale("fr", "FR"));
        bx.k.put("en", new Locale("en", "US"));
        bx.k.put("it", new Locale("it", "IT"));
        bx.k.put("nl", new Locale("nl", "NL"));
        bx.k.put("es", new Locale("es", "ES"));
        bx.k.put("pt", new Locale("pt", "PT"));
        bx.k.put("cz", new Locale("cs", "CZ"));
        for (int i = 0; i < bx.l.length; ++i) {
            bx.l[i] = 48;
        }
    }
}
