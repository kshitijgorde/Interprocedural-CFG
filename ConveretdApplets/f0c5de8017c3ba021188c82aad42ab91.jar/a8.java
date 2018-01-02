import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class a8
{
    private Hashtable a;
    private Vector b;
    
    public a8() {
        this.a = new Hashtable();
        this.b = new Vector();
    }
    
    public final void a(final br br) {
        if (n.b()) {
            n.b("ModuleManager Registering module " + br);
        }
        if (br != null) {
            if (this.b.contains(br.d())) {
                this.b.removeElement(br.d());
            }
            this.b.addElement(br.d());
            this.a.put(br.d(), br);
        }
    }
    
    public final br a(final String s) {
        if (this.a.get(s) == null && n.b()) {
            n.b("ModuleManager:: Did not find module " + s);
        }
        return this.a.get(s);
    }
    
    public final void a() {
        this.a(null, 1);
    }
    
    public final void b() {
        this.a(null, 3);
    }
    
    public final void a(final t t) {
        this.a(t, 2);
    }
    
    private final void a(final t t, final int n) {
        for (int i = 0; i < this.b.size(); ++i) {
            final String s = this.b.elementAt(i);
            try {
                final br br = this.a.get(s);
                switch (n) {
                    case 1: {
                        br.a();
                        break;
                    }
                    case 2: {
                        br.a(t);
                        break;
                    }
                    default: {
                        br.c();
                        break;
                    }
                }
            }
            catch (Exception ex) {
                if (n.a()) {
                    n.a("ModuleManager: Could not handle module: " + s, ex);
                }
            }
        }
    }
}
