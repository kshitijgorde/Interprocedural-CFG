// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import java.awt.MediaTracker;

public class e implements a
{
    private MediaTracker for;
    private int int;
    private h do;
    
    public e(final MediaTracker mediaTracker, final int n) {
        this(mediaTracker, n, null);
    }
    
    public e(final MediaTracker for1, final int int1, final h do1) {
        this.for = for1;
        this.int = int1;
        this.do = do1;
    }
    
    public void a() {
        if (this.do != null) {
            this.do.a();
        }
        try {
            this.for.waitForID(this.int);
        }
        catch (InterruptedException ex) {}
        if (this.do != null) {
            this.do.if();
        }
    }
}
