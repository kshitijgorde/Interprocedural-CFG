// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;

public class vak extends Menu
{
    public Menu a;
    public Menu b;
    public CheckboxMenuItem c;
    public CheckboxMenuItem d;
    public CheckboxMenuItem e;
    public CheckboxMenuItem f;
    public MenuItem g;
    public MenuItem h;
    public CheckboxMenuItem i;
    public CheckboxMenuItem j;
    public CheckboxMenuItem k;
    public CheckboxMenuItem l;
    public CheckboxMenuItem m;
    private CheckboxMenuItem n;
    private CheckboxMenuItem o;
    
    public vak(final vd vd) {
        super(vd.eu);
        (this.a = new Menu(vd.ev)).add(new CheckboxMenuItem("Serif"));
        this.a.add(new CheckboxMenuItem("SansSerif"));
        this.a.add(new CheckboxMenuItem("Monospaced"));
        this.b = new Menu(vd.ew);
        this.c = new CheckboxMenuItem(vd.ex);
        this.d = new CheckboxMenuItem(vd.ey);
        this.e = new CheckboxMenuItem(vd.ez);
        this.f = new CheckboxMenuItem(vd.e0);
        this.b.add(this.c);
        this.b.add(this.d);
        this.b.add(this.e);
        this.b.add(this.f);
        this.g = new MenuItem(vd.e1);
        this.h = new MenuItem(vd.e2);
        this.i = new CheckboxMenuItem(vd.e3);
        this.j = new CheckboxMenuItem(vd.e4);
        this.k = new CheckboxMenuItem(vd.e5);
        this.l = new CheckboxMenuItem(vd.e6);
        this.m = new CheckboxMenuItem(vd.e7);
        this.i.setState(vd.i);
        this.j.setState(vd.j);
        this.k.setState(vd.k);
        this.l.setState(vd.l);
        this.m.setState(vd.m);
        if (vd.ev.length() > 0) {
            this.add(this.a);
        }
        if (vd.ew.length() > 0) {
            this.add(this.b);
        }
        if (vd.e1.length() > 0) {
            this.add(this.g);
        }
        if (vd.e2.length() > 0) {
            this.add(this.h);
        }
        if (this.countItems() > 0) {
            this.addSeparator();
        }
        if (vd.e3.length() > 0) {
            this.add(this.i);
        }
        if (vd.e4.length() > 0) {
            this.add(this.j);
        }
        if (vd.e5.length() > 0) {
            this.add(this.k);
        }
        if (vd.e6.length() > 0) {
            this.add(this.l);
        }
        if (vd.e7.length() > 0) {
            this.add(this.m);
        }
        this.a(vd.bn);
    }
    
    public int a(final CheckboxMenuItem checkboxMenuItem) {
        if (checkboxMenuItem == this.c) {
            return 0;
        }
        if (checkboxMenuItem == this.d) {
            return 2;
        }
        if (checkboxMenuItem == this.e) {
            return 1;
        }
        if (checkboxMenuItem == this.f) {
            return 3;
        }
        return 0;
    }
    
    public void a(final String s) {
        for (int countItems = this.a.countItems(), i = 0; i < countItems; ++i) {
            final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)this.a.getItem(i);
            if (s.equals(checkboxMenuItem.getLabel())) {
                this.b(checkboxMenuItem);
            }
        }
    }
    
    public void b(final CheckboxMenuItem n) {
        if (this.n != null) {
            this.n.setState(false);
        }
        n.setState(true);
        this.n = n;
    }
    
    public void a(final int n) {
        CheckboxMenuItem checkboxMenuItem = null;
        switch (n) {
            case 0: {
                checkboxMenuItem = this.c;
                break;
            }
            case 2: {
                checkboxMenuItem = this.d;
                break;
            }
            case 1: {
                checkboxMenuItem = this.e;
                break;
            }
            case 3: {
                checkboxMenuItem = this.f;
                break;
            }
            default: {
                checkboxMenuItem = this.c;
                break;
            }
        }
        this.c(checkboxMenuItem);
    }
    
    public void c(final CheckboxMenuItem o) {
        if (this.o != null) {
            this.o.setState(false);
        }
        o.setState(true);
        this.o = o;
    }
    
    public void a(final Font font) {
        if (font != null) {
            this.a(font.getName());
            this.a(font.getStyle());
        }
    }
}
