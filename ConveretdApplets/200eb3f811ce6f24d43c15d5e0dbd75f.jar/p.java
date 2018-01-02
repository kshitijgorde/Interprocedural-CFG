// 
// Decompiled by Procyon v0.5.30
// 

public class p extends RuntimeException implements q
{
    private Throwable a;
    
    public p(final String s) {
        super(s);
    }
    
    public p(final String s, final Throwable a) {
        super(s);
        this.a = a;
    }
    
    public Throwable a() {
        return this.a;
    }
    
    public final String b() {
        return super.getMessage();
    }
    
    public String getMessage() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb, this);
        return sb.toString();
    }
    
    private void a(final StringBuffer sb, final Throwable t) {
        if (t == null) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" DUE TO:");
        }
        if (t instanceof q) {
            sb.append(((q)t).b() + "  ");
            this.a(sb, ((q)t).a());
        }
        else {
            sb.append(t.getMessage() + "  ");
        }
    }
    
    public String toString() {
        return "NestedRuntimeException";
    }
}
