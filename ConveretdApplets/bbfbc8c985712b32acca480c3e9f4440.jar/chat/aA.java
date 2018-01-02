// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Panel;

public abstract class aA extends Panel
{
    private static Color a;
    protected K a;
    protected cs a;
    
    public final boolean a(final aZ az) {
        return this.a.a((a)az);
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    protected final void a(final aZ az) {
        int n;
        if (az.a(25) && az.a(18)) {
            n = 2;
        }
        else if (az.a(24) && az.a(23)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (az.c) {
            this.a.a(az, Color.red, Color.pink, new Color(15658734), n);
        }
        else if (az.c != 0) {
            this.a.a(az, new Color(az.c), aA.a, new Color((az.f == 0) ? 15658734 : az.f), n);
        }
        else {
            this.a.a(az, Color.black, Color.white, new Color((az.f == 0) ? 15658734 : az.f), n);
        }
        if (az.d) {
            this.a.b(az, true);
            return;
        }
        this.a.b(az, false);
    }
    
    public final void a(final aZ az, final boolean b) {
        synchronized (this.a) {
            if ((this.a.a(24) || !az.a(23)) && (this.a.a(52) || !az.a(18) || az.i == this.a.i)) {
                final int a;
                if ((a = this.a.a((a)az)) == -1) {
                    if (b) {
                        this.a.c(az);
                    }
                }
                else {
                    this.a.a(az, a);
                }
                this.a(az);
            }
        }
    }
    
    public aA() {
        (this.a = new K()).setSize(175, 2);
    }
    
    static {
        new Color(153);
        new Color(10079487);
        new Color(16711680);
        aA.a = new Color(10079487);
        new Color(39168);
    }
}
