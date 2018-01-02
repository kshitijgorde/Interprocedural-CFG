import java.util.TimeZone;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends aa
{
    public int a;
    private ab b;
    
    public z() {
        this(2);
    }
    
    public z(final int n) {
        this.a(n);
    }
    
    public final void a(int a) {
        if (a > 3) {
            a = 2;
        }
        if (a < 1) {
            a = 1;
        }
        switch (this.a = a) {
            case 1: {
                this.b = new ab("yyyy-MM-dd'T'HH:mm:ss", 1, 2, ".");
                break;
            }
            case 2: {
                this.b = new ab("yyyy-MM-dd'T'HH:mm:ss'Z'", 1, 1, "", TimeZone.getTimeZone("GMT"));
                break;
            }
            case 3: {
                this.b = new ab("yyyy-MM-dd'T'HH:mm:ss", 1, 1, "");
                break;
            }
        }
    }
    
    public String a(final f f, final h h, String a, final Throwable t) {
        final String a2 = this.a(t, f);
        a = this.a(a);
        String e = f.e();
        if (e == null || e.length() == 0) {
            e = null;
        }
        switch (this.a) {
            case 2:
            case 3: {
                return this.b.a(false) + "(" + this.b.a() + ") " + h.b() + ((e == null) ? "" : (" " + e)) + " " + "[" + Thread.currentThread().getName() + "]@" + y.a() + " " + this.a(a) + ((a2 != null) ? (":" + a2) : "") + "\n";
            }
            default: {
                return this.b.a(true) + " [" + Thread.currentThread().getName() + "] " + h.b() + ((e == null) ? "" : (" " + e)) + " " + a + ((a2 != null) ? (": " + a2) : "") + "\n";
            }
        }
    }
    
    public String toString() {
        return "FixedPatternFormatter@" + this.hashCode() + "[" + b(this.a) + "]";
    }
    
    public static String b(final int n) {
        switch (n) {
            case 1: {
                return "PATTERN_CLIENT";
            }
            case 2: {
                return "PATTERN_SERVER";
            }
            case 3: {
                return "PATTERN_SERVER_LOCALTIME";
            }
            default: {
                return "PATTERN_UNDEFINED";
            }
        }
    }
}
