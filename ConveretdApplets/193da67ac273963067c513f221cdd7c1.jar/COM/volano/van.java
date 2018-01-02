// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class van extends Menu
{
    public MenuItem a;
    public MenuItem b;
    
    public van(final vd vd) {
        super(vd.e6);
        this.a = new MenuItem(vd.e7);
        this.b = new MenuItem(vd.e8);
        if (vd.e7.length() > 0) {
            this.add(this.a);
        }
        if (vd.e8.length() > 0) {
            this.add(this.b);
        }
    }
}
