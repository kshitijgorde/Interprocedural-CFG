import java.awt.event.ItemListener;
import java.awt.Menu;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.Event;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;

// 
// Decompiled by Procyon v0.5.30
// 

class be extends bd
{
    private static final boolean r;
    private static final boolean s;
    private CheckboxMenuItem t;
    private CheckboxMenuItem u;
    private CheckboxMenuItem v;
    
    be(final bk bk) {
        super(bk);
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
                        this.d.a(new bf(this.d, 5));
                        break;
                    }
                    if (label.equals("Go To Initial View")) {
                        this.d.a(new bc(this.d));
                        break;
                    }
                    if (label.equals("Zoom In")) {
                        final float[] j;
                        final float[] array = j = this.d.b().j();
                        final int n = 3;
                        j[n] *= 1.5f;
                        this.d.a(new bc(this.d, array));
                        break;
                    }
                    if (label.equals("Zoom Out")) {
                        final float[] i;
                        final float[] array2 = i = this.d.b().j();
                        final int n2 = 3;
                        i[n2] /= 1.5f;
                        this.d.a(new bc(this.d, array2));
                        break;
                    }
                    if (label.equals("Show Toolbar")) {
                        this.d.b().D = true;
                        this.d.b().k();
                        break;
                    }
                    if (label.equals("Hide Toolbar")) {
                        this.d.b().D = false;
                        this.d.b().k();
                        break;
                    }
                    if (label.equals("Help")) {
                        this.d.b().o();
                        break;
                    }
                    break;
                }
                case 501: {
                    final IpixViewer a2 = this.d.a;
                    a2.bl |= IpixViewer.bo;
                    if (this.a((Event)a)) {
                        if (!this.d.a((y)null)) {
                            break;
                        }
                        if (this.d.b().F) {
                            final PopupMenu popupMenu = (PopupMenu)this.d();
                            this.d.b().add(popupMenu);
                            popupMenu.show(this.d.b(), a.x, a.y);
                            break;
                        }
                        break;
                    }
                    else {
                        if ((a.modifiers & 0x8) != 0x0) {
                            break;
                        }
                        if ((a.modifiers & 0x4) != 0x0) {
                            break;
                        }
                        super.a(a);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (this.d.c(this) || !this.a((Event)a)) {
                        super.a(a);
                        break;
                    }
                    if (!this.d.a((y)null)) {
                        break;
                    }
                    if (this.d.b().F) {
                        final PopupMenu popupMenu2 = (PopupMenu)this.d();
                        this.d.b().add(popupMenu2);
                        popupMenu2.show(this.d.b(), a.x, a.y);
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
        catch (NullPointerException ex) {}
    }
    
    protected boolean a(final Event event) {
        return (be.s && event.id == 502 && (event.modifiers & 0x4) == 0x4) || (be.r && event.id == 501 && event.controlDown());
    }
    
    protected Menu d() {
        final PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(new MenuItem("Go To Initial View"));
        if (this.d.b().O) {
            final MenuItem menuItem = new MenuItem("Spin");
            menuItem.setEnabled(this.d.b().m() == null);
            popupMenu.add(menuItem);
        }
        popupMenu.addSeparator();
        MenuItem menuItem2;
        if (this.d.b().D) {
            menuItem2 = new MenuItem("Hide Toolbar");
        }
        else {
            menuItem2 = new MenuItem("Show Toolbar");
        }
        popupMenu.add(menuItem2);
        popupMenu.addSeparator();
        if (this.d.b().l().b().getProperty("spts") != null) {
            final v v = new v(this, this.d);
            this.t = new CheckboxMenuItem("Enable Hot Spots", this.d.b().G);
            this.u = new CheckboxMenuItem("Show Hot Spot Targets", this.d.b().H);
            this.v = new CheckboxMenuItem("Enable Popup Text", this.d.b().I);
            this.t.addItemListener(v);
            this.u.addItemListener(v);
            this.v.addItemListener(v);
            popupMenu.add(this.t);
            popupMenu.add(this.u);
            popupMenu.add(this.v);
            popupMenu.addSeparator();
        }
        popupMenu.add(new MenuItem("Help"));
        return popupMenu;
    }
    
    static {
        r = System.getProperty("os.name").startsWith("Mac");
        s = System.getProperty("os.name").startsWith("Win");
    }
}
