import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class i extends MouseAdapter
{
    private final z a;
    
    i(final z a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            final String e;
            final String s = e = this.a.b.b.e();
            if (fb.m == 0) {
                if (e.indexOf(" ") <= 0) {
                    return;
                }
                z.a(this.a).f(s, " ");
            }
            z.a(this.a).l(z.a(this.a).e(e, " "));
        }
    }
}
