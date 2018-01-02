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
        super(vd.e8);
        this.a = new MenuItem(vd.e9);
        this.b = new MenuItem(vd.fa);
        if (vd.e9.length() > 0) {
            this.add(this.a);
        }
        if (vd.fa.length() > 0) {
            this.add(this.b);
        }
    }
}
