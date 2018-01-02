import java.awt.Menu;
import java.awt.Point;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

class bf extends be
{
    protected static final boolean q;
    protected static final boolean r;
    
    bf(final bn bn) {
        super(bn);
    }
    
    static {
        q = System.getProperty("os.name").startsWith("Mac");
        r = System.getProperty("os.name").startsWith("Win");
    }
    
    protected boolean a(final Event event) {
        return (bf.r && event.id == 502 && (event.modifiers & 0x4) == 0x4) || (bf.q && event.id == 501 && event.controlDown());
    }
    
    public void a(final a a) {
        switch (a.id) {
            case 1001: {
                final String label = ((MenuItem)a.target).getLabel();
                if (label.equals("Spin")) {
                    super.d.a(new bg(super.d));
                    return;
                }
                if (label.equals("Go Home")) {
                    super.d.a(new bd(super.d));
                    return;
                }
                if (label.equals("Zoom In")) {
                    final float[] a2;
                    final float[] array = a2 = super.d.b().a();
                    final int n = 3;
                    a2[n] *= 1.5f;
                    super.d.a(new bc(super.d, array));
                    return;
                }
                if (label.equals("Zoom Out")) {
                    final float[] a3;
                    final float[] array2 = a3 = super.d.b().a();
                    final int n2 = 3;
                    a3[n2] /= 1.5f;
                    super.d.a(new bc(super.d, array2));
                    return;
                }
                break;
            }
            case 501: {
                if (a.isConsumed()) {
                    break;
                }
                if (this.a((Event)a)) {
                    if (super.d.a((ba)null)) {
                        final PopupMenu popupMenu = (PopupMenu)this.d();
                        super.d.b().add(popupMenu);
                        popupMenu.show(super.d.b(), a.x, a.y);
                        return;
                    }
                    break;
                }
                else {
                    if (a.modifiers == 0 && super.d.a(this)) {
                        final Point point = new Point(a.x, a.y);
                        super.o = point;
                        super.n = point;
                        return;
                    }
                    break;
                }
                break;
            }
            case 502: {
                if (a.isConsumed()) {
                    break;
                }
                if (super.d.c(this)) {
                    if (a.modifiers != 0) {
                        break;
                    }
                    super.d.b(this);
                    if (super.d.b().a(new Point(a.x, a.y))) {
                        super.d.b().a(13);
                        return;
                    }
                    break;
                }
                else {
                    if (!this.a((Event)a)) {
                        super.d.b().a(13);
                        return;
                    }
                    if (super.d.a((ba)null)) {
                        final PopupMenu popupMenu2 = (PopupMenu)this.d();
                        super.d.b().add(popupMenu2);
                        popupMenu2.show(super.d.b(), a.x, a.y);
                        return;
                    }
                    break;
                }
                break;
            }
            default: {
                super.a(a);
                break;
            }
        }
    }
    
    protected Menu d() {
        final PopupMenu popupMenu = new PopupMenu();
        final float n = super.d.b().a()[3];
        final MenuItem menuItem = new MenuItem("Zoom In");
        menuItem.setEnabled(n < super.d.b().b().d() - 1.0E-4f);
        popupMenu.add(menuItem);
        final MenuItem menuItem2 = new MenuItem("Zoom Out");
        menuItem2.setEnabled(n > super.d.b().b().c() + 1.0E-4f);
        popupMenu.add(menuItem2);
        popupMenu.addSeparator();
        if (bg.h != 0.0f) {
            final MenuItem menuItem3 = new MenuItem("Spin");
            menuItem3.setEnabled(super.d.b().d() == null);
            popupMenu.add(menuItem3);
        }
        popupMenu.add(new MenuItem("Go Home"));
        return popupMenu;
    }
}
