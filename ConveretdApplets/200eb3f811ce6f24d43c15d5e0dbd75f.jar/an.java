// 
// Decompiled by Procyon v0.5.30
// 

public class an extends Exception implements q
{
    private Throwable a;
    
    public an(final String s) {
        super(s);
    }
    
    public an(final String s, final Throwable a) {
        this(s);
        this.a = a;
    }
    
    public an(final Throwable t) {
        this("", t);
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
        return "NestedException";
    }
}
