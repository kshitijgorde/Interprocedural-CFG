import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class bj implements a0
{
    public String a;
    public int b;
    public String c;
    public String d;
    public int e;
    public int[] f;
    public int g;
    private String h;
    private String i;
    public static ac j;
    public static ac k;
    private static final ac l;
    private static final ac m;
    private static final b5 n;
    
    public bj(final String a, final int b, final String s, final int e, final int[] f, final int g) {
        this.a = a;
        this.b = b;
        this.c = s;
        final int index;
        if (s != null && (index = s.indexOf("->")) > -1) {
            this.d = s.substring(index + 2, s.length());
        }
        else {
            this.d = s;
        }
        this.e = e;
        this.f = f;
        this.g = g;
        if (b == 9 || b == 10) {
            final String substring = s.substring(0, s.indexOf("->"));
            this.c = s.substring(s.indexOf("->") + 2);
            this.h = substring;
        }
    }
    
    public final void a(final bk bk) {
        if (this.b == 9 || this.b == 10) {
            final bj a = bk.a(this.h);
            this.e = a.e();
            this.f = a.h();
            this.i = a.c;
            final int index;
            if ((index = this.i.indexOf("->")) > -1) {
                this.i = this.i.substring(0, index);
            }
            else {
                this.i = null;
            }
        }
        else if (a0.a.g()) {
            a0.a.d("tried to post-init non zdate or non ztime attribute definition");
        }
    }
    
    public final String a() {
        return this.a;
    }
    
    public final int b() {
        return this.b;
    }
    
    public final String c() {
        return this.d;
    }
    
    public final boolean d() {
        return this.e != 0;
    }
    
    public final int e() {
        return this.e;
    }
    
    public final int f() {
        return this.g;
    }
    
    public final String g() {
        return a(this.b);
    }
    
    public static String a(final int n) {
        switch (n) {
            case 1: {
                return "string";
            }
            case 2: {
                return "integer";
            }
            case 3: {
                return "real";
            }
            case 4: {
                return "date";
            }
            case 5: {
                return "time";
            }
            case 6: {
                return "datetime";
            }
            case 7: {
                return "object";
            }
            case 9: {
                return "zdate";
            }
            case 10: {
                return "ztime";
            }
            case 11: {
                return "variant";
            }
            case 101: {
                return "stringarray";
            }
            case 12: {
                return "calendar";
            }
            case 8: {
                return "boolean";
            }
            default: {
                return "unknown";
            }
        }
    }
    
    public static int a(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("string")) {
                return 1;
            }
            if (s.equalsIgnoreCase("integer")) {
                return 2;
            }
            if (s.equalsIgnoreCase("real")) {
                return 3;
            }
            if (s.equalsIgnoreCase("date")) {
                return 4;
            }
            if (s.equalsIgnoreCase("time")) {
                return 5;
            }
            if (s.equalsIgnoreCase("datetime")) {
                return 6;
            }
            if (s.equalsIgnoreCase("zdate")) {
                return 9;
            }
            if (s.equalsIgnoreCase("ztime")) {
                return 10;
            }
            if (s.equalsIgnoreCase("object")) {
                return 7;
            }
            if (s.equalsIgnoreCase("variant")) {
                return 11;
            }
            if (s.equalsIgnoreCase("stringarray")) {
                return 101;
            }
            if (s.equalsIgnoreCase("boolean")) {
                return 8;
            }
            if (s.equalsIgnoreCase("calendar")) {
                return 12;
            }
        }
        return 0;
    }
    
    public final int[] h() {
        return this.f;
    }
    
    public final int b(int n) {
        if (n > 2) {
            n = 2;
        }
        else if (n < -1) {
            n = -1;
        }
        return this.f[n + 1];
    }
    
    public String toString() {
        return "MDGAttributeDefinition (Name:" + this.a + " ; Type:" + this.g() + "; RowGroup:" + this.e + "Details:" + this.g + "(" + this.f[0] + "," + this.f[1] + "," + this.f[2] + "," + this.f[3] + ") )  ";
    }
    
    public static void i() {
        bj.j.a();
        bj.k.a();
        bj.l.a();
        bj.m.a();
    }
    
    public Object a(final a3 a3, final int n, final Integer n2) throws Exception {
        Object o = null;
        if (this.b() == 7 && a3.i != null) {
            o = a3.i.get(new Integer(n));
        }
        if (o == null) {
            o = this.a(a3, this.a(a3, n), n, n2);
            if (this.b() == 7 && o != null) {
                a3.a(n, (a3)o);
            }
        }
        return o;
    }
    
    public String a(final a3 a3, final int n) {
        return a3.a(n, this.b(a3.q()));
    }
    
    public Object a(final a3 a3, String trim, final int n, final Integer n2) throws Exception {
        if (trim == null || trim.equalsIgnoreCase("_NA_")) {
            return null;
        }
        if (this.b == 1) {
            return trim;
        }
        trim = trim.trim();
        if (trim.length() == 0) {
            return null;
        }
        int b = this.b();
        if (n2 != null) {
            final int intValue = n2;
            if (intValue == 12) {
                if (b != 4 && b != 5 && b != 6 && b != 10 && b != 9) {
                    if (a0.a.g()) {
                        a0.a.d("cannot parse att with type " + a(b) + " as calendar");
                    }
                    return null;
                }
                b = intValue;
            }
            else {
                b = intValue;
            }
        }
        switch (b) {
            case 2: {
                final Long n3 = new Long(trim);
                if (n3 > 2147483647L || n3 < -2147483648L) {
                    if (a0.a.g()) {
                        a0.a.d(a3.k.as() + " " + this.a + "=" + trim + " out of Integer bounds: using 0");
                    }
                    return new Integer(0);
                }
                return new Integer((int)(Object)n3);
            }
            case 3: {
                return new Double(trim);
            }
            case 4: {
                return bj.j.a(trim);
            }
            case 5: {
                return bj.k.a(trim);
            }
            case 6: {
                return this.a(trim, "0").c();
            }
            case 9: {
                return bj.l.a(new Date(this.a(trim, this.b(a3, n)).a()));
            }
            case 10: {
                return bj.m.a(new Date(this.a(trim, this.b(a3, n)).a()));
            }
            case 7: {
                return a3.a(a3.e(), trim + a3.d());
            }
            case 12: {
                final Calendar instance = Calendar.getInstance();
                switch (this.b()) {
                    case 4:
                    case 5: {
                        instance.setTime((Date)this.a(a3, trim, n, null));
                        instance.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                        return instance;
                    }
                    case 6: {
                        instance.setTime(new Date((long)(Object)this.a(trim, "0").c()));
                        instance.setTimeZone(TimeZone.getTimeZone("GMT"));
                        return instance;
                    }
                    case 9:
                    case 10: {
                        instance.setTime(new Date((long)(Object)this.a(trim, "0").c()));
                        final b4 a4 = b4.a(a3.k, "" + instance.get(1), this.b(a3, n));
                        if (a4.g()) {
                            instance.setTimeZone(TimeZone.getTimeZone(a4.f("NAME_TIMEZONE")));
                        }
                        return instance;
                    }
                    default: {
                        if (a0.a.g()) {
                            a0.a.d("MDGAttributeDefinition got default case for Calendar value.");
                        }
                        return null;
                    }
                }
                break;
            }
            case 8: {
                if (trim.equals("0")) {
                    return new Boolean(false);
                }
                if (trim.equals("1")) {
                    return new Boolean(true);
                }
                throw new Exception("InternalParseError: can not convert " + trim + " to boolean");
            }
            case 101: {
                final byte[] bytes = trim.getBytes(a3.v());
                final bh bh = new bh(bytes, 0, bytes.length, bj.n);
                final int a5 = bh.a();
                final String[] array = new String[a5];
                for (int i = 0; i < a5; ++i) {
                    array[i] = bh.a(i).a(bj.n, a3.v());
                }
                return array;
            }
            case 11: {
                final int j = a3.i(this.a());
                if (j != 11 && j != 0) {
                    return this.a(a3, trim, n, new Integer(j));
                }
                return trim;
            }
            default: {
                if (a0.a.k()) {
                    a0.a.i(a3.k.as() + " " + this.b() + " not matches any type for " + trim);
                }
                return trim;
            }
        }
    }
    
    public final b3 a(final String s, final String s2) {
        try {
            return new b3((long)(new Double(s) * 1000.0), s2);
        }
        catch (Exception ex) {
            if (a0.a.i()) {
                a0.a.e("cannot convert " + s + " to double for timestamp check in " + this.a(), ex);
            }
            return null;
        }
    }
    
    public final String b(final a3 a3, final int n) {
        if (this.i == null) {
            return null;
        }
        return a3.e(n, this.i);
    }
    
    public final String j() {
        return this.h;
    }
    
    public String c(final a3 a3, final int n) throws Exception {
        int n2 = this.b;
        if (n2 == 11) {
            n2 = a3.i(this.a());
        }
        if (n2 == 9 || n2 == 10) {
            return a3.k.am().a(this.a(this.a(a3, n), this.b(a3, n)), a3.h(n, this.a()), a3.k, n2);
        }
        return a3.k.am().a(this.a(a3, n, null), a3.h(n, this.a()), a3.k, n2);
    }
    
    static {
        bj.j = new ac("yyyy-MM-dd");
        bj.k = new ac("HH:mm:ss");
        l = new ac("yyyy-MM-dd");
        m = new ac("HH:mm:ss");
        n = new b5(',', '\'', '\n', ' ', '\t', false);
        try {
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");
            if (timeZone == null) {
                timeZone = TimeZone.getTimeZone("ECT");
            }
            if (timeZone != null) {
                bj.j.a(timeZone);
                bj.k.a(timeZone);
            }
            else if (a0.a.g()) {
                a0.a.d("could not create TimeZone for parser of time attributes (neither for Europe/Berlin nor for ECT)");
            }
        }
        catch (Exception ex) {
            if (a0.a.g()) {
                a0.a.b("Could not set required timezone.", ex);
            }
        }
        try {
            bj.l.a(TimeZone.getTimeZone("GMT"));
            bj.m.a(TimeZone.getTimeZone("GMT"));
        }
        catch (Exception ex2) {
            if (a0.a.g()) {
                a0.a.b("MDGAttributeDefinitionDateTime: cannot assign timezone GMT to date formatter", ex2);
            }
        }
    }
}
