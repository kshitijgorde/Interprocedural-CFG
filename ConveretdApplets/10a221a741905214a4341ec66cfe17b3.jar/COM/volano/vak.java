// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class vak extends Menu
{
    public MenuItem a;
    public MenuItem b;
    
    public vak(final vd vd) {
        super(vd.fg);
        this.a = new MenuItem(vd.fh);
        this.b = new MenuItem(vd.fi);
        if (vd.fh.length() > 0) {
            this.add(this.a);
        }
        if (vd.fi.length() > 0) {
            this.add(this.b);
        }
    }
}
