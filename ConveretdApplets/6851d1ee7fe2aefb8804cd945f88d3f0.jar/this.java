import java.util.Hashtable;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class this
{
    public final int id;
    public final String name;
    public final Color xa;
    public final String vla;
    private static final Hashtable wla;
    
    private static int a(final String s) {
        if (s == null) {
            return 0;
        }
        final Integer n = this.wla.get(s);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public this(final String s) {
        final u u = new u(":");
        if (s != null) {
            u.m(s);
            if (u.a() > 0) {
                this.name = u.b(0);
            }
            else {
                this.name = null;
            }
            if (u.a() > 1) {
                Color xa;
                try {
                    xa = Color.decode(u.b(1));
                }
                catch (NumberFormatException ex) {
                    xa = Color.blue;
                }
                this.xa = xa;
            }
            else if (this.name != null) {
                this.xa = Color.blue;
            }
            else {
                this.xa = null;
            }
            if (u.a() > 2) {
                this.vla = u.b(2);
            }
            else {
                this.vla = null;
            }
            this.id = a(this.name);
        }
        else {
            this.id = 0;
            this.name = null;
            this.xa = null;
            this.vla = null;
        }
    }
    
    static {
        (wla = new Hashtable()).put("ArrowUp", new Integer(1));
        this.wla.put("ArrowDown", new Integer(2));
    }
}
