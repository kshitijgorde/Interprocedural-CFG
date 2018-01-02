// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.awt.bb;
import ji.util.d;
import java.awt.Dimension;
import java.awt.Point;
import ji.io.h;
import ji.util.i;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Component;
import ji.v1base.bl;

public class gt extends bl implements an
{
    private boolean a;
    private do b;
    private boolean c;
    private String d;
    
    public gt(final Component component, final Frame frame, final bj bj, final String s, final String s2) {
        super(frame, s, false);
        this.a = false;
        this.d = null;
        this.c = false;
        this.a(component, bj, s, s2);
    }
    
    public gt(final Component component, final Dialog dialog, final bj bj, final String s, final String s2) {
        super(dialog, s, false);
        this.a = false;
        this.d = null;
        this.c = false;
        this.a(component, bj, s, s2);
    }
    
    public void a(final Component component, final bj bj, final String s, final String d) {
        if (i.c(124)) {
            h.d(d, "Progress dialog: init, parent=".concat(String.valueOf(String.valueOf(component))));
        }
        this.d = d;
        (this.b = new do(true, this.getBackground(), d)).setEnabled(false);
        this.b.setAcceptFocus(false);
        this.d().add(this.b);
        this.setSize(300, 70);
        this.b();
    }
    
    private void b() {
        final Dimension screenSize = this.getToolkit().getScreenSize();
        final Point point = new Point(0, 0);
        final Dimension size = this.getSize();
        final Point point2 = point;
        point2.x += (screenSize.width - size.width) / 2;
        final Point point3 = point;
        point3.y += (screenSize.height - size.height) / 2;
        if (point.x < 0) {
            point.x = 0;
        }
        if (point.y < 0) {
            point.y = 0;
        }
        this.setLocation(point.x, point.y);
    }
    
    public void a() {
        this.b.releaseResources();
        this.b = null;
    }
    
    public void hide() {
        this.a = false;
        super.hide();
    }
    
    public void show() {
        if (i.c(124)) {
            h.d(this.d, "Progress dialog: dialog shown, parent=".concat(String.valueOf(String.valueOf(this.getParent()))));
        }
        this.c = true;
        try {
            super.show();
            this.a = true;
        }
        catch (Exception ex) {
            if (i.c(124)) {
                h.d(this.d, "Progress dialog: show failure");
            }
            ji.util.d.a(ex);
            this.a = false;
        }
        final abv abv = new abv(this);
        if (this.a) {
            new bb(this.d, abv).start();
            this.b.setBackground(this.getBackground());
            this.b.e();
            this.repaint();
        }
    }
    
    public void bf(final int n) {
        this.b.a(n);
        this.b.e();
    }
    
    public void ba(final String s) {
        if (!this.c) {
            this.show();
        }
        this.b.a(s, this.getGraphics());
        this.b.e();
    }
}
