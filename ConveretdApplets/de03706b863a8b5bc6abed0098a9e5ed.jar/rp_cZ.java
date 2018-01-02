import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_cZ extends JPanel implements MouseListener, rp_ai, rp_eY
{
    rp_dl a;
    public rp_dv a;
    private boolean a;
    
    public rp_cZ(final rp_dv a, final rp_dl a2) {
        this.a = false;
        this.a = a;
        this.a = a2;
        this.setToolTipText(null);
        this.addMouseListener(this);
    }
    
    private int a() {
        Container parent = null;
        try {
            parent = this.getParent().getParent().getParent();
        }
        catch (Exception ex) {}
        if (parent != null && parent instanceof rp_bA) {
            return ((rp_bA)parent).a;
        }
        return 120;
    }
    
    String a() {
        return this.a.a().b();
    }
    
    final String b() {
        if (this.a == null) {
            return rp_au.a("pg1");
        }
        final rp_fT a = this.a.a();
        final String a2 = this.a();
        if (a.a() == 2) {
            return rp_aJ.a(a2);
        }
        if (a.a() == 1) {
            final rp_eJ rp_eJ = (rp_eJ)a;
            String s = a2;
            final String b;
            if ((b = rp_eJ.b) != null) {
                s = s + "<br>" + b;
            }
            final String f;
            if ((f = rp_eJ.f) != null && (b == null || 0 != f.compareTo(b))) {
                s = s + "<br>" + f;
            }
            final String string = rp_au.a("Wd") + rp_au.a.a().a(this.a.a());
            final String string2 = rp_au.a("Dd") + rp_au.a.a().a(this.a.d());
            String s2;
            if (this.a.b()) {
                s2 = s + "<br><br><b>" + rp_au.a("nava1") + "<br>" + rp_au.a("nava2") + "</b>";
            }
            else {
                s2 = s + "<br>" + string + "<br>" + string2;
                final Object a3;
                if ((a3 = this.a.a("desc")) != null && a3 instanceof String) {
                    s2 = s2 + "<br>" + (String)a3;
                }
            }
            return rp_aJ.a("<table><tr><td valign=\"top\" align=\"left\">" + s2 + "</td><td valign=\"top\"></td></tr></table>");
        }
        return "";
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (this.getToolTipText() == null) {
            this.setToolTipText(this.b());
        }
        graphics.setColor(this.getParent().getBackground());
        final Rectangle clipBounds = graphics.getClipBounds();
        graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        final Dimension dimension = new Dimension(this.getWidth(), this.getHeight());
        Color color = null;
        Color color2 = null;
        if (this.a) {
            color = rp_aJ.j;
            color2 = rp_aJ.k;
        }
        else if (this.a != null) {
            if (this.a.b()) {
                color = rp_aJ.m;
                color2 = rp_aJ.n;
            }
            else {
                final Object a;
                if ((a = this.a.a("c-fill")) != null && a instanceof Color) {
                    color2 = (Color)a;
                }
                final Object a2;
                if ((a2 = this.a.a("c-border")) != null && a2 instanceof Color) {
                    color = (Color)a2;
                }
            }
        }
        if (this.a != null) {
            this.a.a(graphics, dimension, new Dimension(this.a.d, this.a.e), color, color2, false, this.a(), rp_au.a.a(), this, false);
            return;
        }
        rp_dv.a(graphics, dimension, null);
    }
    
    public final JComponent a() {
        return this;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.a != null && mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == 1 && this.a.a()) {
            rp_au.a.a(this.a);
            rp_au.a.requestFocus();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.a == null || !this.a.a()) {
            rp_bd.a(null, rp_au.a("wrn"), rp_au.a("pl1"), rp_au.a("cl"));
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.repaint();
        this.a = true;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.repaint();
        this.a = false;
    }
    
    public final void a(final Object o) {
        this.repaint();
    }
    
    public final void a() {
    }
}
