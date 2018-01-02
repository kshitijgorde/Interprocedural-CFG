import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    public Vector bc;
    public int v;
    
    public i() {
        this.v = -1;
        this.bc = new Vector();
    }
    
    public final void au(final d d) {
        this.bc.addElement(d);
        if (d.p && d.v > this.v) {
            this.v = d.v;
        }
    }
}
