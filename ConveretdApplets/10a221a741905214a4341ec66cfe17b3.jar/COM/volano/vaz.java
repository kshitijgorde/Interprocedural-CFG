// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class vaz extends Menu
{
    public MenuItem a;
    public MenuItem b;
    public MenuItem c;
    
    public vaz(final vd vd) {
        super(vd.fq);
        this.a = new MenuItem(vd.fr);
        this.b = new MenuItem(vd.fs);
        this.c = new MenuItem(vd.ft);
        if (vd.fr.length() > 0) {
            this.add(this.a);
        }
        if (vd.fs.length() > 0) {
            this.add(this.b);
        }
        if (vd.ft.length() > 0) {
            this.add(this.c);
        }
    }
}
