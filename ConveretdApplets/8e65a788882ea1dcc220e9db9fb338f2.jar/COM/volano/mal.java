// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;

public class mal extends Menu
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
    
    public mal(final md md) {
        super(md.ep);
        (this.a = new Menu(md.eq)).add(new CheckboxMenuItem("Serif"));
        this.a.add(new CheckboxMenuItem("SansSerif"));
        this.a.add(new CheckboxMenuItem("Monospaced"));
        this.b = new Menu(md.er);
        this.c = new CheckboxMenuItem(md.es);
        this.d = new CheckboxMenuItem(md.et);
        this.e = new CheckboxMenuItem(md.eu);
        this.f = new CheckboxMenuItem(md.ev);
        this.b.add(this.c);
        this.b.add(this.d);
        this.b.add(this.e);
        this.b.add(this.f);
        this.g = new MenuItem(md.ew);
        this.h = new MenuItem(md.ex);
        this.i = new CheckboxMenuItem(md.ey);
        this.j = new CheckboxMenuItem(md.ez);
        this.k = new CheckboxMenuItem(md.e0);
        this.l = new CheckboxMenuItem(md.e1);
        this.m = new CheckboxMenuItem(md.e2);
        this.i.setState(md.i);
        this.j.setState(md.j);
        this.k.setState(md.k);
        this.l.setState(md.l);
        this.m.setState(md.m);
        if (md.eq.length() > 0) {
            this.add(this.a);
        }
        if (md.er.length() > 0) {
            this.add(this.b);
        }
        if (md.ew.length() > 0) {
            this.add(this.g);
        }
        if (md.ex.length() > 0) {
            this.add(this.h);
        }
        if (this.countItems() > 0) {
            this.addSeparator();
        }
        if (md.ey.length() > 0) {
            this.add(this.i);
        }
        if (md.ez.length() > 0) {
            this.add(this.j);
        }
        if (md.e0.length() > 0) {
            this.add(this.k);
        }
        if (md.e1.length() > 0) {
            this.add(this.l);
        }
        if (md.e2.length() > 0) {
            this.add(this.m);
        }
        this.a(md.bj);
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
