import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    public Vector ba;
    public int v;
    
    public i() {
        this.v = -1;
        this.ba = new Vector();
    }
    
    public final void ax(final d d) {
        this.ba.addElement(d);
        if (d.p && d.v > this.v) {
            this.v = d.v;
        }
    }
}
