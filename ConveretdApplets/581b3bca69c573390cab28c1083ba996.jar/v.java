import java.awt.Menu;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.Event;
import java.awt.MenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends u
{
    private static final boolean q;
    private static final boolean r;
    
    v(final bb bb) {
        super(bb);
    }
    
    void a(final a a) {
        if (a.isConsumed()) {
            return;
        }
        try {
            switch (a.id) {
                case 1001: {
                    final String label = ((MenuItem)a.target).getLabel();
                    if (label.equals("Spin")) {
                        super.d.a(new w(super.d));
                        break;
                    }
                    if (label.equals("Go Home")) {
                        super.d.a(new t(super.d));
                        break;
                    }
                    if (label.equals("Zoom In")) {
                        final float[] f;
                        final float[] array = f = super.d.b().f();
                        final int n = 3;
                        f[n] *= 1.5f;
                        super.d.a(new t(super.d, array));
                        break;
                    }
                    if (label.equals("Zoom Out")) {
                        final float[] f2;
                        final float[] array2 = f2 = super.d.b().f();
                        final int n2 = 3;
                        f2[n2] /= 1.5f;
                        super.d.a(new t(super.d, array2));
                        break;
                    }
                    if (label.equals("Show Toolbar")) {
                        super.d.b().B = true;
                        super.d.b().g();
                        break;
                    }
                    if (label.equals("Hide Toolbar")) {
                        super.d.b().B = false;
                        super.d.b().g();
                        break;
                    }
                    if (label.equals("Help")) {
                        super.d.b().j();
                        break;
                    }
                    break;
                }
                case 501: {
                    if (this.a((Event)a)) {
                        if (!super.d.a((r)null)) {
                            break;
                        }
                        final PopupMenu popupMenu = (PopupMenu)this.d();
                        super.d.b().add(popupMenu);
                        popupMenu.show(super.d.b(), a.x, a.y);
                        break;
                    }
                    else {
                        if (a.modifiers != 0) {
                            break;
                        }
                        if (!super.d.a(this)) {
                            break;
                        }
                        super.n.move(a.x, a.y);
                        super.o.move(a.x, a.y);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (super.d.c(this) || !this.a((Event)a)) {
                        super.a(a);
                        break;
                    }
                    if (!super.d.a((r)null)) {
                        break;
                    }
                    final PopupMenu popupMenu2 = (PopupMenu)this.d();
                    super.d.b().add(popupMenu2);
                    popupMenu2.show(super.d.b(), a.x, a.y);
                    break;
                }
                default: {
                    super.a(a);
                    break;
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    protected boolean a(final Event event) {
        return (v.r && event.id == 502 && (event.modifiers & 0x4) == 0x4) || (v.q && event.id == 501 && event.controlDown());
    }
    
    protected Menu d() {
        final PopupMenu popupMenu = new PopupMenu();
        final float n = super.d.b().f()[3];
        final MenuItem menuItem = new MenuItem("Zoom In");
        menuItem.setEnabled(n < super.d.b().h().d() - 1.0E-4f);
        popupMenu.add(menuItem);
        final MenuItem menuItem2 = new MenuItem("Zoom Out");
        menuItem2.setEnabled(n > super.d.b().h().e() + 1.0E-4f);
        popupMenu.add(menuItem2);
        popupMenu.addSeparator();
        if (super.d.b().E) {
            final MenuItem menuItem3 = new MenuItem("Spin");
            menuItem3.setEnabled(super.d.b().i() == null);
            popupMenu.add(menuItem3);
        }
        popupMenu.add(new MenuItem("Go Home"));
        popupMenu.addSeparator();
        MenuItem menuItem4;
        if (super.d.b().B) {
            menuItem4 = new MenuItem("Hide Toolbar");
        }
        else {
            menuItem4 = new MenuItem("Show Toolbar");
        }
        popupMenu.add(menuItem4);
        popupMenu.addSeparator();
        popupMenu.add(new MenuItem("Help"));
        return popupMenu;
    }
    
    static {
        q = System.getProperty("os.name").startsWith("Mac");
        r = System.getProperty("os.name").startsWith("Win");
    }
}
