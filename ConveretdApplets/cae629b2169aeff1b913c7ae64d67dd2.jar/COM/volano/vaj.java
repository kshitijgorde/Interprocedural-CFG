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
        super(vd.eq);
        this.a = new MenuItem(vd.er);
        this.b = new MenuItem(vd.es);
        this.c = new MenuItem(vd.et);
        if (vd.er.length() > 0) {
            this.add(this.a);
        }
        if (vd.es.length() > 0) {
            this.add(this.b);
        }
        if (vd.et.length() > 0) {
            if (this.countItems() > 0) {
                this.addSeparator();
            }
            this.add(this.c);
        }
    }
}
