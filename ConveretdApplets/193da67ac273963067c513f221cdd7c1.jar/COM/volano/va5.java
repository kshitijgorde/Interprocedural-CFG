// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuComponent;
import java.awt.MenuItem;
import java.util.Vector;
import java.awt.Menu;

public class va5 extends Menu
{
    public vd a;
    public Vector b;
    public String c;
    public String d;
    public MenuItem e;
    public MenuItem f;
    public MenuItem g;
    
    public va5(final vd a) {
        super(a.fb);
        this.a = a;
        this.b = new Vector();
        this.a("", "");
    }
    
    public synchronized void a(final String c, final String d) {
        this.c = c;
        this.d = d;
        this.remove(this.e);
        this.remove(this.f);
        this.remove(this.g);
        this.e = new MenuItem(vaq.a(this.a.fc, c));
        this.f = (this.b.contains(c) ? new MenuItem(vaq.a(this.a.fe, c)) : new MenuItem(vaq.a(this.a.fd, c)));
        this.g = new MenuItem(this.a.ff);
        if (this.a.fc.length() > 0) {
            this.add(this.e);
        }
        if (this.a.fd.length() > 0 && this.a.fe.length() > 0) {
            this.add(this.f);
        }
        if (this.a.ff.length() > 0) {
            this.add(this.g);
        }
        if (c.length() > 0) {
            this.e.enable();
            this.f.enable();
            return;
        }
        this.e.disable();
        this.f.disable();
    }
    
    public synchronized void a() {
        if (this.c.length() > 0) {
            if (this.b.contains(this.c)) {
                this.b.removeElement(this.c);
            }
            else {
                this.b.addElement(this.c);
            }
            this.a(this.c, this.d);
        }
    }
    
    public boolean a(final String s) {
        return this.b.contains(s);
    }
}
