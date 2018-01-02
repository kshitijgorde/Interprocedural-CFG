// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.util.d;
import ji.res.s;

public class er extends Exception
{
    private Exception a;
    private String b;
    private String c;
    private String d;
    
    public String toString() {
        if (this.getMessage() != null) {
            return "Annotation Exception: ".concat(String.valueOf(String.valueOf(this.getMessage())));
        }
        return "Annotation Exception";
    }
    
    public er(final String s) {
        super(s);
    }
    
    public er(final String s, final Exception a, final String d) {
        this.a = a;
        this.b = s.a(526, s);
        this.c = s.a(188, s);
        this.d = d;
    }
    
    public final String getMessage() {
        if (ji.util.d.by(this.a.getMessage())) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append("\n(").append(this.a).append(")\n").append(this.c).append(": ").append(this.d)));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b))).append("\n(").append(this.a).append(")")));
    }
    
    public final Exception a() {
        return this.a;
    }
}
