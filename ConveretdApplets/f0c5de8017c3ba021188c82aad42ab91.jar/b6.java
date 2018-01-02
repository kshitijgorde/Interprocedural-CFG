// 
// Decompiled by Procyon v0.5.30
// 

public class b6 extends p
{
    private int a;
    private int b;
    private String c;
    
    public b6(final String s) {
        super(s);
        this.a = -1;
        this.b = -1;
        this.c = null;
    }
    
    public b6(final String s, final Throwable t) {
        super(s, t);
        this.a = -1;
        this.b = -1;
        this.c = null;
    }
    
    public String toString() {
        return "CSVParseException";
    }
    
    public String getMessage() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.getMessage());
        if (this.c != null) {
            sb.append(this.c);
            if (this.a > -1) {
                sb.append(" L:" + this.a);
            }
            if (this.b > -1) {
                sb.append(" C:" + this.b);
            }
        }
        return sb.toString();
    }
}
