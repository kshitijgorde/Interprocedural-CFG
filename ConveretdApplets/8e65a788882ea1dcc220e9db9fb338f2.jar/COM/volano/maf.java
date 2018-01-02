// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.MenuComponent;
import java.awt.MenuItem;
import java.util.Vector;
import java.awt.Menu;

public class maf extends Menu
{
    public md a;
    public Vector b;
    public String c;
    public String d;
    public MenuItem e;
    public MenuItem f;
    public MenuItem g;
    
    public maf(final md a) {
        super(a.e8);
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
        this.e = new MenuItem(mz.a(this.a.e9, c));
        this.f = (this.b.contains(c) ? new MenuItem(mz.a(this.a.fb, c)) : new MenuItem(mz.a(this.a.fa, c)));
        this.g = new MenuItem(this.a.fc);
        if (this.a.e9.length() > 0) {
            this.add(this.e);
        }
        if (this.a.fa.length() > 0 && this.a.fb.length() > 0) {
            this.add(this.f);
        }
        if (this.a.fc.length() > 0) {
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
