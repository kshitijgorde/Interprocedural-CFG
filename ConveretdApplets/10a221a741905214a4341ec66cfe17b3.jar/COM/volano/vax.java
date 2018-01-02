// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class vax extends Menu
{
    public MenuItem a;
    
    public vax(final vd vd) {
        super(vd.fj);
        this.a = new MenuItem(vd.fk);
        if (vd.fk.length() > 0) {
            this.add(this.a);
        }
    }
}
