// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Label;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Event;

public abstract class bs extends G
{
    public w q;
    public dW q;
    protected g q;
    private g y;
    protected g w;
    protected g e;
    protected g r;
    protected g t;
    private String q;
    public bw q;
    public dz q;
    protected boolean w;
    protected String[] q;
    public C q;
    protected y q;
    protected y w;
    public j q;
    public j w;
    private String w;
    private String e;
    private String r;
    private String t;
    private String y;
    private String u;
    
    public abstract bZ q();
    
    public abstract void q(final bw p0);
    
    public abstract boolean q(final bZ p0);
    
    public abstract void q(final bZ p0);
    
    public boolean q(final Event event) {
        return false;
    }
    
    public boolean q() {
        return true;
    }
    
    public final String[] q() {
        return this.q;
    }
    
    public final void q(final y y) {
        this.q.w(y);
    }
    
    public final void q(final y y, final int n) {
        this.q.q(y, n);
    }
    
    public void w(final bZ bz) {
        if (bz == null) {
            this.q.e();
            this.w.e();
            this.e.e();
            this.r.e();
            this.t.e();
        }
        else {
            if (bz.q(63)) {
                this.w.q(eb.q("Restore"));
            }
            else {
                this.w.q(eb.q("Remove"));
            }
            if (this.q.w((aF)bz)) {
                this.q.q();
            }
            else {
                this.q.e();
            }
            if (bz.q && this.q.w((aF)bz) && !bz.q(62)) {
                this.w.q();
            }
            else {
                this.w.e();
            }
            if (bz == this.q.q(0)) {
                this.t.q();
                this.r.e();
            }
            else if (bz == this.q.w(this.q.w() - 1)) {
                this.r.q();
                this.t.e();
            }
            else {
                this.t.q();
                this.r.q();
            }
        }
        if (this.q.r() > 0) {
            this.e.q();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final bZ bz = (bZ)this.q.q();
                if (this.q == null && ((event.target == this.q && this.q.q()) || event.target == this.q) && bz != null) {
                    if (!this.q()) {
                        return true;
                    }
                    super.q = true;
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    (this.q = new bw(this.q, this, bz, this.w, this.q)).q(true);
                    this.setCursor(Cursor.getDefaultCursor());
                    this.q.requestFocus();
                }
                else if ((this.q == null || !this.q.isVisible()) && event.target == this.y) {
                    final bZ q;
                    if ((q = this.q()) != null) {
                        super.q = true;
                        this.setCursor(Cursor.getPredefinedCursor(3));
                        (this.q = new bw(this.q, this, q, this.w, this.q)).q(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        this.q.requestFocus();
                    }
                }
                else if (event.target == this.w) {
                    if (bz.q(63)) {
                        super.q = true;
                        bz.o(63);
                        this.q.w(bz, false);
                        this.w.q(this.r);
                        this.e();
                    }
                    else {
                        super.q = true;
                        bz.i(63);
                        this.q.w(bz, true);
                        this.w.q(this.t);
                    }
                    this.q.q((aF)bz);
                    this.q.requestFocus();
                }
                else if (event.target == this.e) {
                    for (int i = 0; i < this.q.r(); ++i) {
                        super.q = true;
                        final bZ bz2 = (bZ)this.q.q(i);
                        if (this.e.q().equals(this.w)) {
                            if (bz2.q && this.q.w((aF)bz2) && !bz2.q(62)) {
                                bz2.i(63);
                                this.q.w(bz2, true);
                            }
                        }
                        else {
                            bz2.o(63);
                            this.q.w(bz2, false);
                            this.e();
                        }
                        this.q.q((aF)bz2);
                    }
                    if (this.e.q().equals(this.w)) {
                        this.e.q(this.e);
                    }
                    else {
                        this.e.q(this.w);
                    }
                    this.q.requestFocus();
                }
                else if (event.target == this.r) {
                    final int w;
                    if ((w = this.q.w()) > 0) {
                        this.q(bz, (bZ)this.q.q(w - 1));
                    }
                }
                else {
                    final int w2;
                    if (event.target == this.t && (w2 = this.q.w()) < this.q.q() - 1) {
                        this.q(bz, (bZ)this.q.q(w2 + 1));
                    }
                }
                return true;
            }
            case 701: {
                super.q = true;
                this.w((bZ)event.arg);
                return true;
            }
            case 702: {
                this.w(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    private void q(final bZ bz, final bZ bz2) {
        final int q = bz2.q();
        bz2.e(bz.q());
        bz.e(q);
        this.q.q(bz2, bz2.q());
        this.q.q(bz, bz.q());
        this.q.w();
        super.q = true;
        this.w(bz);
    }
    
    public void w() {
        this.q.r();
        this.q.e();
    }
    
    public void e() {
    }
    
    public void e(final bZ bz) {
        final int q = this.q.q((aF)bz);
        this.q.q(bz);
        if (q == -1) {
            this.q.e(bz);
            if (bz.q(62)) {
                this.q.q(bz, true);
            }
            if (this.q != null) {
                final bZ q2;
                if ((q2 = this.q()) != null) {
                    this.q.q(q2);
                }
                else {
                    this.q.dispose();
                    this.q = null;
                }
            }
        }
        else {
            this.q.q(bz, q);
            if (this.q != null) {
                this.q.dispose();
                this.q = null;
            }
        }
        this.w(bz);
    }
    
    public final void r() {
        this.q.dispose();
        this.q = null;
    }
    
    public final String q(final Object o) {
        final String lowerCase = this.q.toLowerCase();
        if (o instanceof g) {
            if (((g)o).q()) {
                if (o == this.y) {
                    return ec.q(eb.q("Click here to add a new %1."), new String[] { lowerCase });
                }
                if (o == this.q) {
                    return ec.q(eb.q("Click here to edit the selected %1."), new String[] { lowerCase });
                }
                if (o == this.w) {
                    if (this.w.q().equals(eb.q("Remove"))) {
                        return ec.q(eb.q("Click here to remove the selected %1."), new String[] { lowerCase });
                    }
                    return ec.q(eb.q("Click here to restore the selected %1."), new String[] { lowerCase });
                }
                else {
                    if (o == this.e) {
                        return eb.q("Click here to remove all entries.");
                    }
                    if (o == this.r) {
                        return ec.q(eb.q("Click here to move the selected %1 up."), new String[] { lowerCase });
                    }
                    if (o == this.t) {
                        return ec.q(eb.q("Click here to move the selected %1 down."), new String[] { lowerCase });
                    }
                }
            }
            else {
                if (this.q.w() < 0) {
                    return ec.q(eb.q("This button is disabled because no %1 is selected."), new String[] { lowerCase });
                }
                if (o == this.q) {
                    return ec.q(eb.q("This button is disabled because the selected %1 cannot be edited."), new String[] { lowerCase });
                }
                return ec.q(eb.q("This button is disabled because the selected %1 cannot be removed."), new String[] { lowerCase });
            }
        }
        return null;
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final int q() {
        return this.q.q();
    }
    
    public final bZ q(final int n) {
        return (bZ)this.q.q(n);
    }
    
    public final bZ w(final int n) {
        return (bZ)this.q.w(n);
    }
    
    public bs(final cV cv, final String s, final String q) {
        super(s);
        this.w = eb.q("Remove All");
        this.e = eb.q("Restore All");
        this.r = eb.q("Remove");
        this.t = eb.q("Restore");
        this.y = eb.q("Add");
        this.u = eb.q("Edit");
        eb.q("Move Up");
        eb.q("Move Down");
        this.q = (dz)cv;
        this.q = new g(80, 20);
        this.y = new g(80, 20);
        this.w = new g(80, 20);
        this.e = new g(80, 20);
        if (this.r == null) {
            this.r = (g)dI.q(null, "up_arrow", "up_arrow.gif", this.q, 30, 30);
        }
        if (this.t == null) {
            this.t = (g)dI.q(null, "dn_arrow", "dn_arrow.gif", this.q, 30, 30);
        }
        new Canvas().setSize(35, 25);
        this.q = new j();
        new Canvas().setSize(35, 25);
        this.w = new j();
        this.q = null;
        this.w = false;
        this.q = null;
        this.q = new C(null, "default");
        this.q = new y("ID");
        this.w = new y(eb.q("Name"), "name");
        this.q = (dz)cv;
        this.q = q;
        this.q = new dW(10, 1000);
        (this.q = new w()).w(this.q);
        this.q.w(this.w);
        this.q.q(true);
        this.q.w(true);
        this.w.w(true);
        this.q.q(this.q);
        this.q.q(2);
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        this.q.resize(250, 220);
        final t t = new t(this.q);
        layout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.q.getFontMetrics(this.q.getFont())).stringWidth(this.u);
        final int stringWidth2 = fontMetrics.stringWidth(this.w);
        final int stringWidth3 = fontMetrics.stringWidth(this.y);
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (stringWidth3 > stringWidth) {
            stringWidth = stringWidth3;
        }
        stringWidth += 20;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        if (!(this instanceof bz) || this.q.q(49)) {
            layout.setConstraints(this.q, gridBagConstraints);
            this.q.q(this.u);
            this.q.resize(stringWidth, 20);
            panel.add(this.q);
        }
        if (!(this instanceof bz) || this.q.q(49) || this.q.q(2)) {
            this.w.q(this.r);
            this.w.resize(stringWidth, 20);
            layout.setConstraints(this.w, gridBagConstraints);
            panel.add(this.w);
            this.e.q(this.w);
            this.e.resize(stringWidth, 20);
            layout.setConstraints(this.e, gridBagConstraints);
            panel.add(this.e);
        }
        if (!(this instanceof bz) || this.q.q(49) || this.q.q(1)) {
            this.y.q(this.y);
            this.y.resize(stringWidth, 20);
            layout.setConstraints(this.y, gridBagConstraints);
            panel.add(this.y);
        }
        gridBagConstraints.anchor = 15;
        panel.add(new Label(), gridBagConstraints);
        gridBagConstraints.weighty = 0.1;
        panel.add(this.r, gridBagConstraints);
        gridBagConstraints.weighty = 0.0;
        panel.add(this.t, gridBagConstraints);
        this.r.setVisible(false);
        this.t.setVisible(false);
        this.q(panel, 1, 1.0f, 1.0f);
    }
}
