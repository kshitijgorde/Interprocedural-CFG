// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Image;

final class df extends bh
{
    private Image a;
    private dm a;
    
    df(final Image a, final dm a2) {
        super(a);
        this.a = a;
        this.a = a2;
    }
    
    public final boolean a(final Event event, final int n, final int n2) {
        try {
            this.a.getAppletContext().showDocument(new URL("http://" + this.a.f + "/"), "_blank");
        }
        catch (MalformedURLException ex) {}
        return super.a(event, n, n2);
    }
    
    public final void a(final ei ei) {
        ei.a(this.a, -40, 0, this);
        ei.a(Color.black);
        ei.c(0, 0, 100, 180);
        ei.a(this.a());
        ei.b(-40, 0, 0, 180);
        ei.b(101, 0, 141, 180);
    }
}
