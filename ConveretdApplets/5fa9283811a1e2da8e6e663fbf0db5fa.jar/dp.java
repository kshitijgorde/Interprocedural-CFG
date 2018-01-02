import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

public class dp
{
    public static final Event p;
    public int p;
    
    public final void p(int p) {
        if (p >= 97 && p <= 122) {
            p -= 32;
        }
        this.p = p;
    }
    
    public final int p() {
        return this.p;
    }
    
    public dp() {
        this.p = 0;
    }
    
    static {
        p = new Event(null, 0, null);
    }
}
