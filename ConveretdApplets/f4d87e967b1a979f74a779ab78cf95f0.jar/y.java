import java.util.Vector;
import en.network.Network;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class y implements Serializable
{
    protected Network p;
    private Vector d;
    private Vector a;
    private Vector n;
    protected String v;
    public int i;
    public c l;
    public static int b;
    public static int c;
    public static int e;
    public static int f;
    protected boolean g;
    
    public y() {
        this.d = new Vector(16);
        this.a = new Vector(16);
        this.n = new Vector(16);
        this.l = new c("active", this, 3, false);
        this.g = false;
        final String name = this.getClass().getName();
        this.v = name.substring(name.lastIndexOf(".") + 1);
        this.l.p(true);
    }
    
    public final void p(final Network p) {
        this.p = p;
    }
    
    public final Vector p() {
        return this.n;
    }
    
    public final void p(final v v) {
        if (!this.d.contains(v)) {
            this.d.addElement(v);
        }
    }
    
    public final boolean p(final k k) {
        if (this == k.d()) {
            this.n.addElement(k);
            return true;
        }
        if (this == k.p()) {
            this.a.addElement(k);
            return true;
        }
        return false;
    }
    
    public final String p() {
        return this.v;
    }
    
    public final boolean p() throws Exception {
        if (!this.g) {
            if (!this.initialize()) {
                throw new Exception("Error initializing node: ".concat(String.valueOf(String.valueOf(this.v))));
            }
            this.g = true;
        }
        if (this.l.p() && !this.execute()) {
            throw new Exception("Error executing node: ".concat(String.valueOf(String.valueOf(this.v))));
        }
        this.d();
        return true;
    }
    
    public final void d() throws Exception {
        for (int i = 0; i < this.n.size(); ++i) {
            ((k)this.n.elementAt(i)).p();
        }
    }
    
    public final void a() {
        this.g = false;
    }
    
    public boolean initialize() {
        return true;
    }
    
    public boolean execute() throws Exception {
        return true;
    }
    
    public void connectorChanged(final v v) throws Exception {
        if (v.p() != this) {
            System.out.println("Node: rogue connector calling Node.connectorChanged(...)");
        }
    }
    
    public final String toString() {
        return this.v;
    }
    
    static {
        y.b = 1;
        y.c = 2;
        y.e = 3;
        y.f = 4;
    }
}
