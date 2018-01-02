// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class vaj extends Menu
{
    public MenuItem a;
    public MenuItem b;
    public MenuItem c;
    
    public vaj(final vd vd) {
        super(vd.eo);
        this.a = new MenuItem(vd.ep);
        this.b = new MenuItem(vd.eq);
        this.c = new MenuItem(vd.er);
        if (vd.ep.length() > 0) {
            this.add(this.a);
        }
        if (vd.eq.length() > 0) {
            this.add(this.b);
        }
        if (vd.er.length() > 0) {
            if (this.countItems() > 0) {
                this.addSeparator();
            }
            this.add(this.c);
        }
    }
}
