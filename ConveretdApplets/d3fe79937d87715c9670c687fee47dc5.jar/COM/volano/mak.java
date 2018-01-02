// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class mak extends Menu
{
    public MenuItem a;
    
    public mak(final md md) {
        super(md.e6);
        this.a = new MenuItem(md.e7);
        if (md.e7.length() > 0) {
            this.add(this.a);
        }
    }
}
