import java.awt.Component;
import java.awt.PopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class a0 extends Thread
{
    public PopupMenu a;
    public Component b;
    public int c;
    public int d;
    public q e;
    public d f;
    
    public a0(final PopupMenu a, final Component b, final int c, final int d, final q e, final d f) {
        super("popup");
        this.f = f;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public final void run() {
        try {
            Thread.currentThread();
            Thread.yield();
            this.a.show(this.b, this.c, this.d);
            if (this.e != null) {
                this.e.a(false);
            }
        }
        finally {
            this.a();
        }
    }
    
    public void a() {
        this.a = null;
        this.b = null;
    }
}
