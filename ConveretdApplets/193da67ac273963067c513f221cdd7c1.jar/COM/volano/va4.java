// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class va4 extends Menu
{
    public MenuItem a;
    
    public va4(final vd vd) {
        super(vd.e9);
        this.a = new MenuItem(vd.fa);
        if (vd.fa.length() > 0) {
            this.add(this.a);
        }
    }
}
