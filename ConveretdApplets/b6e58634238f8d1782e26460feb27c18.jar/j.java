import java.awt.Component;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public final class j
{
    public d a;
    public String b;
    public int c;
    public String d;
    public int e;
    public long f;
    public long g;
    
    public j(final d a) {
        this.b = "127.0.0.1";
        this.c = 8180;
        this.e = 2;
        this.f = 60000L;
        this.g = 86400000L;
        this.a = a;
    }
    
    public final void a(final Properties properties) {
        final String property;
        if ((property = properties.getProperty("Server")) != null) {
            this.b = property;
        }
        final String property2;
        if ((property2 = properties.getProperty("Port")) != null) {
            try {
                final Integer c;
                if ((c = this.a.d().c(property2)) < 0) {
                    this.a.c().a((Component)this.a.s, 4, new Object[] { c, "Port", "negative" });
                }
                else {
                    this.c = c;
                }
            }
            catch (Exception ex) {
                this.a.c().a((Component)this.a.s, 4, new Object[] { property2, "Port", ex.getMessage() });
            }
        }
        this.d = properties.getProperty("Tunnel");
        final String property3;
        if ((property3 = properties.getProperty("NumRequestAttempts")) != null) {
            try {
                final Integer c2;
                if ((c2 = this.a.d().c(property3)) < 1) {
                    this.a.c().a((Component)this.a.s, 4, new Object[] { c2, "NumRequestAttempts", "less than 1" });
                }
                else {
                    this.e = c2;
                }
            }
            catch (Exception ex2) {
                this.a.c().a((Component)this.a.s, 4, new Object[] { property3, "NumRequestAttempts", ex2.getMessage() });
            }
        }
        final String property4;
        if ((property4 = properties.getProperty("MaxReRequestDelay")) != null) {
            try {
                final Double d = this.a.d().d(property4);
                if (d >= 1.0) {
                    this.f = Math.round(1000.0 * d);
                }
                else {
                    this.a.c().a((Component)this.a.s, 4, new Object[] { property4, "MaxReRequestDelay", "less than 1.0" });
                }
            }
            catch (Exception ex3) {
                this.a.c().a((Component)this.a.s, 4, new Object[] { property4, "MaxReRequestDelay", ex3.getMessage() });
            }
        }
    }
    
    public final r a(final String s, final String s2, final String s3) {
        return new r(this.a, s, s2, s3);
    }
    
    public final void a() {
        this.b = null;
        this.d = null;
        this.a = null;
    }
}
