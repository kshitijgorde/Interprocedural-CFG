import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class ai implements Runnable
{
    final /* synthetic */ y a;
    final /* synthetic */ TTApplet b;
    
    ai(final TTApplet b, final y a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.getContentPane().remove(this.a);
    }
}
