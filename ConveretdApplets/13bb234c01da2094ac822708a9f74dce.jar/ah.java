import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class ah implements Runnable
{
    final /* synthetic */ y a;
    final /* synthetic */ TTApplet b;
    
    ah(final TTApplet b, final y a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.a.g();
        this.b.getContentPane().add(this.a, "Center");
        this.b.getContentPane().validate();
        this.b.getContentPane().repaint();
    }
}
