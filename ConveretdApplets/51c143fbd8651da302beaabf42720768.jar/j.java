import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class j extends MouseAdapter
{
    private final ab a;
    
    j(final ab a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            final String e;
            final String s = e = this.a.b.b.e();
            if (!d.r) {
                if (e.indexOf(" ") <= 0) {
                    return;
                }
                ab.a(this.a).f(s, " ");
            }
            ab.a(this.a).l(ab.a(this.a).e(e, " "));
        }
    }
}
