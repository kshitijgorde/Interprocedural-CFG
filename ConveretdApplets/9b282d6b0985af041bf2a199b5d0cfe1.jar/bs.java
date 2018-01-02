import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class bs extends bq
{
    bs(final URL url) {
        super(url);
    }
    
    protected void a(final w w, final int n) throws InterruptedException {
        this.a(w, Toolkit.getDefaultToolkit().getImage(super.d));
        this.a(new f(2));
    }
    
    protected void a(final w w, final Image image) throws InterruptedException {
        super.a(w, image);
        w.a("hFOV", new Float(6.2831855f));
        w.a("vFOV", new Float(6.2831855f * (w.c().height - 4) / w.c().width));
    }
}
