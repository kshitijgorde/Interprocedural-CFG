// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class map extends Menu
{
    public MenuItem a;
    public MenuItem b;
    
    public map(final md md) {
        super(md.e3);
        this.a = new MenuItem(md.e4);
        this.b = new MenuItem(md.e5);
        if (md.e4.length() > 0) {
            this.add(this.a);
        }
        if (md.e5.length() > 0) {
            this.add(this.b);
        }
    }
}
