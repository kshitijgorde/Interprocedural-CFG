import com.daysofwonder.util.t;
import com.daysofwonder.applet.au;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

public class L extends ae
{
    private AudioClip b;
    private au c;
    
    public L(final au c) {
        try {
            this.c = c;
            this.b = c.h("turn.au");
        }
        catch (Exception ex) {
            t.a(ex);
            this.b = null;
        }
    }
    
    public void a(final int n) {
        if (n == 1 && this.b != null) {
            this.c.a(this.b);
        }
    }
    
    public void a(final int n, final int n2, final float n3, final boolean b) {
    }
    
    public void b(final int n) {
    }
}
