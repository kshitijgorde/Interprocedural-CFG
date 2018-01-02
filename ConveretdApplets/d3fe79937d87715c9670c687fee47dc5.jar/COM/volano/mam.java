// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuItem;
import java.awt.Menu;

public class mam extends Menu
{
    public MenuItem a;
    public MenuItem b;
    public MenuItem c;
    
    public mam(final md md) {
        super(md.fd);
        this.a = new MenuItem(md.fe);
        this.b = new MenuItem(md.ff);
        this.c = new MenuItem(md.fg);
        if (md.fe.length() > 0) {
            this.add(this.a);
        }
        if (md.ff.length() > 0) {
            this.add(this.b);
        }
        if (md.fg.length() > 0) {
            this.add(this.c);
        }
    }
}
