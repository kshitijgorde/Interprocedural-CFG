import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class b extends G
{
    private K b;
    private boolean c;
    
    public b(final V v, final int n, final int n2) {
        super(v, 256, 240);
    }
    
    public final void a() {
        if (this.b == null) {
            this.addMouseListener(this.b = new K(this));
        }
        super.a();
    }
    
    public final void a(final boolean b) {
        this.c = true;
    }
    
    public final void b(final boolean b) {
        if (!X.i) {
            this.setFocusable(true);
            this.requestFocus();
            X.i = true;
        }
        super.b(b);
        if (this.c) {
            this.a.a.d();
        }
    }
}
