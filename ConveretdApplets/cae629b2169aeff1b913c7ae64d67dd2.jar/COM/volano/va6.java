// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class va6 extends Menu
{
    public MenuItem a;
    public MenuItem b;
    public MenuItem c;
    
    public va6(final vd vd) {
        super(vd.fi);
        this.a = new MenuItem(vd.fj);
        this.b = new MenuItem(vd.fk);
        this.c = new MenuItem(vd.fl);
        if (vd.fj.length() > 0) {
            this.add(this.a);
        }
        if (vd.fk.length() > 0) {
            this.add(this.b);
        }
        if (vd.fl.length() > 0) {
            this.add(this.c);
        }
    }
}
