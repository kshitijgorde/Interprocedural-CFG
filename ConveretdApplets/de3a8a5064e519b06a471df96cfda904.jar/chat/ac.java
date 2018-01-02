// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Panel;

public abstract class ac extends Panel
{
    private static Color a;
    protected y a;
    protected bh a;
    
    public final boolean a(final au au) {
        return this.a.a((a)au);
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    protected final void a(final au au) {
        int n;
        if (au.a(25) && au.a(18)) {
            n = 2;
        }
        else if (au.a(24) && au.a(23)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (au.a) {
            this.a.a(au, Color.red, Color.pink, new Color(15658734), n);
        }
        else if (au.c != 0) {
            this.a.a(au, new Color(au.c), ac.a, new Color((au.f == 0) ? 15658734 : au.f), n);
        }
        else {
            this.a.a(au, Color.black, Color.white, new Color((au.f == 0) ? 15658734 : au.f), n);
        }
        if (au.b) {
            this.a.a(au, true);
            return;
        }
        this.a.a(au, false);
    }
    
    public final void a(final au au, final boolean b) {
        synchronized (this.a) {
            if ((this.a.a(24) || !au.a(23)) && (this.a.a(52) || !au.a(18) || au.g == this.a.g)) {
                final int a;
                if ((a = this.a.a((a)au)) == -1) {
                    if (b) {
                        this.a.c(au);
                    }
                }
                else {
                    this.a.a(au, a);
                }
                this.a(au);
            }
        }
    }
    
    public ac() {
        (this.a = new y()).setSize(175, 2);
    }
    
    static {
        new Color(153);
        new Color(10079487);
        new Color(16711680);
        ac.a = new Color(10079487);
        new Color(39168);
    }
}
