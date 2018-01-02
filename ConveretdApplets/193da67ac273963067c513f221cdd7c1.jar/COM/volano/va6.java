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
        super(vd.fg);
        this.a = new MenuItem(vd.fh);
        this.b = new MenuItem(vd.fi);
        this.c = new MenuItem(vd.fj);
        if (vd.fh.length() > 0) {
            this.add(this.a);
        }
        if (vd.fi.length() > 0) {
            this.add(this.b);
        }
        if (vd.fj.length() > 0) {
            this.add(this.c);
        }
    }
}
