import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

public class at extends ar
{
    public v a;
    
    public at() {
    }
    
    public at(final String s, final v v, final v v2) {
        super(s, v, v2, ap.a(), av.a());
    }
    
    public final void a(final a2 a2) throws aw {
        if (!a2.f()) {
            throw new aw("invalid config object");
        }
        try {
            final Enumeration b = this.b();
            while (b.hasMoreElements()) {
                final String s = b.nextElement();
                if (this.o(s) == null && a2.b(s) >= 0) {
                    this.c(s, a2.f(s));
                }
            }
            final String f = a2.f(this.a("AUTH_ID_NAME"));
            final String f2 = a2.f(this.a("AUTH_ID_NAME") + "_EXPIRES");
            this.c("AUTH_ID_VALUE", f);
            if (f2 != null) {
                this.c("AUTH_ID_EXPIRES", f2);
            }
        }
        catch (Exception ex) {
            throw new aw("InternalError:UnexpectedException ", ex);
        }
    }
    
    public void c(final String s, final String s2) {
        super.b(s, s2);
    }
    
    public String o(final String s) {
        if (this.a != null) {
            return this.a.a(s, this.a);
        }
        return null;
    }
    
    public String p(final String s) {
        final StringBuffer sb = new StringBuffer();
        final Enumeration b = this.b();
        while (b.hasMoreElements()) {
            final String s2 = b.nextElement();
            sb.append(s2);
            sb.append('=');
            sb.append(this.a(s2));
            sb.append(s);
        }
        return sb.toString();
    }
}
