// 
// Decompiled by Procyon v0.5.30
// 

public abstract class aa
{
    public boolean a;
    
    public aa() {
        this.a = false;
    }
    
    public abstract String a(final f p0, final h p1, final String p2, final Throwable p3);
    
    public abstract String toString();
    
    public String a(final Throwable t, final f f) {
        if (t != null) {
            final StringBuffer sb = new StringBuffer();
            this.a(t, sb, f);
            return sb.toString();
        }
        return null;
    }
    
    private void a(final Throwable t, final StringBuffer sb, final f f) {
        sb.append(this.a(t) + ":" + t.getMessage());
        if (f.b(y.b())) {
            sb.append("\nTrace:" + dh.a(t));
        }
        if (t instanceof q) {
            final Throwable a = ((q)t).a();
            if (a != null) {
                sb.append(" DUE TO: ");
                this.a(a, sb, f);
            }
        }
    }
    
    public String a(final Throwable t) {
        if (t == null) {
            return "null";
        }
        final String name = t.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }
    
    public String a(final String s) {
        return s;
    }
}
