import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fu extends rp_cZ implements ActionListener
{
    public rp_fu(final rp_dv rp_dv, final rp_dl rp_dl) {
        super(rp_dv, rp_dl);
        if (rp_dv != null) {
            rp_dv.a("custom", new Boolean(true));
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (this.a != null && rp_au.a.a().a() >= 10 && mouseEvent.getClickCount() == 1 && mouseEvent.getButton() == 3) {
            final rp_fb a = rp_au.a.a();
            final JPopupMenu popupMenu = new JPopupMenu();
            final JMenuItem menuItem;
            (menuItem = new JMenuItem(a.a(0, "pl5"))).addActionListener(this);
            menuItem.setActionCommand("E");
            popupMenu.add(menuItem);
            final JMenuItem menuItem2;
            (menuItem2 = new JMenuItem(a.a(0, "pl6"))).addActionListener(this);
            menuItem2.setActionCommand("D");
            popupMenu.add(menuItem2);
            popupMenu.show(this, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        super.mouseClicked(mouseEvent);
    }
    
    final String a() {
        if (this.a == null) {
            return null;
        }
        final rp_fT a;
        if ((a = this.a.a()) == null) {
            return null;
        }
        return a.d;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("D")) {
            final rp_fK a = rp_au.a.a();
            try {
                final rp_bg rp_bg;
                if ((rp_bg = new rp_bg(a.a(32, "&sku=" + rp_C.b(this.a.a().b())))).a()) {
                    final rp_dl a2;
                    if ((a2 = this.a).b.remove(this)) {
                        a2.a(true);
                    }
                }
                else {
                    rp_bg.a(a);
                }
            }
            catch (Exception ex) {}
        }
        if (actionEvent.getActionCommand().equals("E")) {
            final rp_dv rp_dv = (rp_dv)this.a.clone();
            rp_dv.a(new rp_dK(rp_dv.a()));
            final rp_cv rp_cv;
            if (a(rp_cv = new rp_cv(0, 0, 0, 0.0, rp_dv, null), 31)) {
                this.a.a().d = rp_cv.a;
                this.a.a("desc", rp_cv.a());
                this.a.b(rp_cv.a.a(), rp_cv.a.b());
                this.a.a("c-border", rp_cv.a());
                this.a.a("c-fill", rp_cv.b());
                this.a.a = rp_cv.a.a;
                this.setToolTipText(this.b());
            }
        }
    }
    
    public static boolean a(final rp_cv rp_cv, final int n) {
        final rp_fZ a;
        if ((a = rp_au.a().a.a()) == null) {
            return false;
        }
        final String a2 = rp_cv.a;
        final rp_fK a3 = rp_au.a.a();
    Label_0032:
        while (rp_cv.a(a3.a())) {
            final String a4 = rp_cv.a;
            final rp_dl a5 = a.a;
            final String s = a4;
            final rp_dl rp_dl = a5;
            final int b = a5.b();
            int i = 0;
            while (true) {
                while (i < b) {
                    final rp_cZ rp_cZ;
                    if ((rp_cZ = (rp_cZ)rp_dl.a(i)) != null && s.equals(rp_cZ.a())) {
                        final rp_cZ rp_cZ3;
                        final rp_cZ rp_cZ2 = rp_cZ3 = rp_cZ;
                        if (rp_cZ2 != null && (n == 30 || (n == 31 && !a2.equals(a4)))) {
                            final rp_fb a6 = rp_au.a.a();
                            rp_bd.a(a3.a(), a6.a(0, "err"), a6.a(0, "pl4"), a6.a(0, "cl"));
                            continue Label_0032;
                        }
                        if (!rp_C.b(a4)) {
                            final rp_fb a7 = rp_au.a.a();
                            rp_bd.a(a3.a(), a7.a(0, "err"), a7.a(0, "Nm") + ' ' + a7.a(0, "pl2"), a7.a(0, "cl"));
                            continue Label_0032;
                        }
                        final String a8;
                        if (!rp_C.b(a8 = rp_cv.a())) {
                            final rp_fb a9 = rp_au.a.a();
                            rp_bd.a(a3.a(), a9.a(0, "err"), a9.a(0, "pl3") + ' ' + a9.a(0, "pl2"), a9.a(0, "cl"));
                            continue Label_0032;
                        }
                        final int a10 = rp_cv.a.a();
                        final int b2 = rp_cv.a.b();
                        final Color a11 = rp_cv.a();
                        final Color b3 = rp_cv.b();
                        String s2 = String.format("&template=%s&desc=%s&layer=%d&width=%d&depth=%d&clrborder=%s&clrfill=%s", rp_C.e(rp_cv.a.a()), rp_C.e(a8), rp_cv.a.a, a10, b2, rp_C.e(rp_C.a(a11)), rp_C.e(rp_C.a(b3)));
                        if (n == 31) {
                            s2 = s2 + "&sku=" + rp_C.e(a2) + "&newsku=" + rp_C.e(a4);
                        }
                        if (n == 30) {
                            s2 = s2 + "&sku=" + rp_C.e(a4);
                        }
                        rp_C.a(10, "PARAMS: " + s2);
                        try {
                            final rp_bg rp_bg;
                            if (!(rp_bg = new rp_bg(a3.a(n, s2))).a()) {
                                rp_bg.a(a3);
                                return false;
                            }
                            if (n == 30) {
                                final rp_fT rp_fT;
                                (rp_fT = new rp_eJ()).d = a4;
                                rp_cv.a.a(rp_fT);
                                if (a8 != null && a8.length() > 0) {
                                    rp_cv.a.a("desc", a8);
                                }
                                if (a11 != null) {
                                    rp_cv.a.a("c-border", a11);
                                }
                                if (b3 != null) {
                                    rp_cv.a.a("c-fill", b3);
                                }
                                final rp_fu rp_fu = new rp_fu(rp_cv.a, a.a);
                                a.a.a(rp_cv.a.a(), rp_cv.a.b());
                                final rp_dl a12 = a.a;
                                final rp_fu rp_fu2 = rp_fu;
                                final rp_dl rp_dl2 = a12;
                                if (a12.b.add(rp_fu2)) {
                                    rp_dl2.a(true);
                                }
                            }
                            if (n == 31) {
                                a.a.a(rp_cv.a.a(), rp_cv.a.b());
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            return false;
                        }
                        return true;
                    }
                    else {
                        ++i;
                    }
                }
                rp_cZ rp_cZ3;
                final rp_cZ rp_cZ2 = rp_cZ3 = null;
                continue;
            }
        }
        return false;
    }
}
