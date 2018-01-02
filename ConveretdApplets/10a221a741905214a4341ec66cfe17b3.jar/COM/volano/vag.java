// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class vag extends Menu
{
    public MenuItem a;
    public MenuItem b;
    public MenuItem c;
    
    public vag(final vd vd) {
        super(vd.ey);
        this.a = new MenuItem(vd.ez);
        this.b = new MenuItem(vd.e0);
        this.c = new MenuItem(vd.e1);
        if (vd.ez.length() > 0) {
            this.add(this.a);
        }
        if (vd.e0.length() > 0) {
            this.add(this.b);
        }
        if (vd.e1.length() > 0) {
            if (this.countItems() > 0) {
                this.addSeparator();
            }
            this.add(this.c);
        }
    }
}
