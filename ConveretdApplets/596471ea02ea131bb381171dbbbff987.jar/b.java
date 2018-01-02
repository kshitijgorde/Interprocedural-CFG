import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class b extends F
{
    private J b;
    private boolean c;
    
    public b(final U u, final int n, final int n2) {
        super(u, 256, 240);
    }
    
    public final void a() {
        if (this.b == null) {
            this.addMouseListener(this.b = new J(this));
        }
        super.a();
    }
    
    public final void a(final boolean b) {
        this.c = true;
    }
    
    public final void b(final boolean b) {
        if (!W.i) {
            this.setFocusable(true);
            this.requestFocus();
            W.i = true;
        }
        super.b(b);
        if (this.c) {
            this.a.a.d();
        }
    }
}
