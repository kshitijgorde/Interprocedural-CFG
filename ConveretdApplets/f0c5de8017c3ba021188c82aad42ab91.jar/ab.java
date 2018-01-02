import java.util.Date;
import java.util.TimeZone;

// 
// Decompiled by Procyon v0.5.30
// 

public class ab
{
    private String a;
    private long b;
    private long c;
    private int d;
    private int e;
    private String f;
    private ac g;
    private TimeZone h;
    
    public ab() {
        this.d = 2;
        this.e = 2;
        this.f = ".";
        this.g = new ac("HH:mm:ss");
    }
    
    public ab(final String s, int d, int e, final String f, final TimeZone timeZone) {
        this.d = 2;
        this.e = 2;
        this.f = ".";
        this.g = new ac("HH:mm:ss");
        if (s == null) {
            throw new IllegalArgumentException("Format string in TimeFactory constructor is null.");
        }
        if (s.indexOf("S") > -1) {
            throw new IllegalArgumentException("Format string in TimeFactory contains an indication of having milliseconds formatting instructions: 'S'");
        }
        this.g = new ac(s);
        if (d > 2) {
            d = 2;
        }
        else if (d < 1) {
            d = 1;
        }
        this.d = d;
        if (e > 2) {
            e = 2;
        }
        else if (e < 1) {
            e = 1;
        }
        this.e = e;
        if (f != null) {
            this.f = f;
        }
        this.a(timeZone);
    }
    
    public ab(final String s, final int n, final int n2, final String s2) {
        this(s, n, n2, s2, null);
    }
    
    private void a(TimeZone default1) {
        if (default1 == null) {
            default1 = TimeZone.getDefault();
        }
        this.h = default1;
        this.g.a(default1);
    }
    
    private TimeZone c() {
        return this.h;
    }
    
    public final synchronized String a(final boolean b) {
        this.e();
        if (b) {
            final StringBuffer sb = new StringBuffer();
            if (this.e == 1) {
                switch (this.d) {
                    case 2: {
                        sb.append(this.c % 1000L + this.f);
                        break;
                    }
                    case 1: {
                        sb.append(this.d() + this.f);
                        break;
                    }
                }
            }
            sb.append(this.a);
            if (this.e == 2) {
                switch (this.d) {
                    case 2: {
                        sb.append(this.f + this.c % 1000L);
                        break;
                    }
                    case 1: {
                        sb.append(this.f + this.d());
                        break;
                    }
                }
            }
            return sb.toString();
        }
        return this.a;
    }
    
    public final String a() {
        final String string = System.currentTimeMillis() + "";
        return string.substring(0, string.length() - 3) + "." + string.substring(string.length() - 3, string.length());
    }
    
    public final String b() {
        this.e();
        return this.c + "";
    }
    
    private final String d() {
        final String b = this.b();
        return b.substring(b.length() - 3, b.length());
    }
    
    private final void e() {
        this.c = System.currentTimeMillis();
        if (this.c >= this.b + 1000L) {
            this.a = this.g.a(new Date());
            this.b = this.c / 1000L * 1000L;
        }
    }
    
    public String toString() {
        return "TimeFactory[" + this.hashCode() + "]" + this.g + " /tz:" + this.c().getID();
    }
}
