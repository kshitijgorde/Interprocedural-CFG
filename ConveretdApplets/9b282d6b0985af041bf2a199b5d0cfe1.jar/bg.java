import java.awt.Menu;
import java.awt.Point;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.Event;

// 
// Decompiled by Procyon v0.5.30
// 

class bg extends bf
{
    protected static final boolean q;
    protected static final boolean r;
    
    bg(final bo bo) {
        super(bo);
    }
    
    static {
        q = System.getProperty("os.name").startsWith("Mac");
        r = System.getProperty("os.name").startsWith("Win");
    }
    
    protected boolean a(final Event event) {
        return (bg.r && event.id == 502 && (event.modifiers & 0x4) == 0x4) || (bg.q && event.id == 501 && event.controlDown());
    }
    
    public void a(final a a) {
        try {
            switch (a.id) {
                case 1001: {
                    final String label = ((MenuItem)a.target).getLabel();
                    if (label.equals("Spin")) {
                        super.d.b(new bh(super.d));
                        return;
                    }
                    if (label.equals("Go Home")) {
                        super.d.b(new be(super.d));
                        return;
                    }
                    if (label.equals("Zoom In")) {
                        final float[] h;
                        final float[] array = h = super.d.b().h();
                        final int n = 3;
                        h[n] *= 1.5f;
                        super.d.b(new bd(super.d, array));
                        return;
                    }
                    if (label.equals("Zoom Out")) {
                        final float[] h2;
                        final float[] array2 = h2 = super.d.b().h();
                        final int n2 = 3;
                        h2[n2] /= 1.5f;
                        super.d.b(new bd(super.d, array2));
                        return;
                    }
                    break;
                }
                case 501: {
                    if (a.isConsumed()) {
                        break;
                    }
                    if (this.a((Event)a)) {
                        if (super.d.b((bb)null)) {
                            final PopupMenu popupMenu = (PopupMenu)this.d();
                            super.d.b().add(popupMenu);
                            popupMenu.show(super.d.b(), a.x, a.y);
                            return;
                        }
                        break;
                    }
                    else {
                        if (a.modifiers == 0 && super.d.b(this)) {
                            super.n.move(a.x, a.y);
                            super.o.move(a.x, a.y);
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
                    if (super.d.a(this)) {
                        if (a.modifiers != 0) {
                            break;
                        }
                        super.d.c(this);
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
                        if (super.d.b((bb)null)) {
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
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    protected Menu d() {
        final PopupMenu popupMenu = new PopupMenu();
        final float n = super.d.b().h()[3];
        final MenuItem menuItem = new MenuItem("Zoom In");
        menuItem.setEnabled(n < super.d.b().i().d() - 1.0E-4f);
        popupMenu.add(menuItem);
        final MenuItem menuItem2 = new MenuItem("Zoom Out");
        menuItem2.setEnabled(n > super.d.b().i().c() + 1.0E-4f);
        popupMenu.add(menuItem2);
        popupMenu.addSeparator();
        if (bh.h != 0.0f) {
            final MenuItem menuItem3 = new MenuItem("Spin");
            menuItem3.setEnabled(super.d.b().b() == null);
            popupMenu.add(menuItem3);
        }
        popupMenu.add(new MenuItem("Go Home"));
        return popupMenu;
    }
}
