import java.awt.event.InputEvent;
import com.daysofwonder.applet.al;

// 
// Decompiled by Procyon v0.5.30
// 

class ab extends Thread
{
    final /* synthetic */ s a;
    
    ab(final s a) {
        this.a = a;
    }
    
    public void run() {
        final al al = (al)this.a.b("send");
        al.d();
        this.a.a(al, al.j(), (InputEvent)null);
        al.c(this.a.f());
        al.v();
    }
}
