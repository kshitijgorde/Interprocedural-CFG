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
        super(vd.fb);
        this.a = new MenuItem(vd.fc);
        if (vd.fc.length() > 0) {
            this.add(this.a);
        }
    }
}
