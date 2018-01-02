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

public abstract class G extends cV
{
    protected cc q;
    protected M q;
    private ad t;
    private ad y;
    protected ad q;
    protected ad w;
    protected ad e;
    protected ad r;
    private String q;
    protected dK q;
    public cT q;
    protected boolean q;
    protected String[] q;
    protected bV q;
    protected aX q;
    protected aX w;
    public dS q;
    public dS w;
    private String w;
    private String e;
    private String r;
    private String t;
    private String y;
    private String u;
    
    public abstract bp q();
    
    public abstract void q(final dK p0);
    
    public abstract boolean q(final bp p0);
    
    public abstract void q(final bp p0);
    
    public boolean q(final Event event) {
        return false;
    }
    
    public final String[] q() {
        return this.q;
    }
    
    public final void q(final aX ax) {
        this.q.w(ax);
    }
    
    public final void q(final aX ax, final int n) {
        this.q.q(ax, n);
    }
    
    public void w(final bp bp) {
        if (bp == null) {
            this.t.e();
            this.q.e();
            this.w.e();
            this.e.e();
            this.r.e();
        }
        else {
            if (bp.q(63)) {
                this.q.q(be.w("Restore"));
            }
            else {
                this.q.q(be.w("Remove"));
            }
            if (this.q.w((bJ)bp)) {
                this.t.q();
            }
            else {
                this.t.e();
            }
            if (bp.t && this.q.w((bJ)bp) && !bp.q(62)) {
                this.q.q();
            }
            else {
                this.q.e();
            }
            if (bp == this.q.q(0)) {
                this.r.q();
                this.e.e();
            }
            else if (bp == this.q.w(this.q.w - 1)) {
                this.e.q();
                this.r.e();
            }
            else {
                this.r.q();
                this.e.q();
            }
        }
        if (this.q.r() > 0) {
            this.w.q();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final bp bp = (bp)this.q.q();
                if (this.q == null && ((event.target == this.q && this.t.q()) || event.target == this.t) && bp != null) {
                    super.w = true;
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    (this.q = new dK(this.q, this, bp, this.q, this.q)).setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                    this.q.requestFocus();
                }
                else if ((this.q == null || !this.q.isVisible()) && event.target == this.y) {
                    final bp q;
                    if ((q = this.q()) != null) {
                        super.w = true;
                        this.setCursor(Cursor.getPredefinedCursor(3));
                        (this.q = new dK(this.q, this, q, this.q, this.q)).setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        this.q.requestFocus();
                    }
                }
                else if (event.target == this.q) {
                    if (bp.q(63)) {
                        super.w = true;
                        bp.d(63);
                        this.q.w(bp, false);
                        this.q.q(this.r);
                        this.e();
                    }
                    else {
                        super.w = true;
                        bp.s(63);
                        this.q.w(bp, true);
                        this.q.q(this.t);
                    }
                    this.q.q((bJ)bp);
                    this.q.requestFocus();
                }
                else if (event.target == this.w) {
                    for (int i = 0; i < this.q.r(); ++i) {
                        super.w = true;
                        final bp bp2 = (bp)this.q.q(i);
                        if (this.w.q.equals(this.w)) {
                            bp2.s(63);
                            this.q.w(bp2, true);
                        }
                        else {
                            bp2.d(63);
                            this.q.w(bp2, false);
                            this.e();
                        }
                        this.q.q((bJ)bp2);
                    }
                    if (this.w.q.equals(this.w)) {
                        this.w.q(this.e);
                    }
                    else {
                        this.w.q(this.w);
                    }
                    this.q.requestFocus();
                }
                else if (event.target == this.e) {
                    final int w;
                    if ((w = this.q.w()) > 0) {
                        this.q(bp, (bp)this.q.q(w - 1));
                    }
                }
                else {
                    final int w2;
                    if (event.target == this.r && (w2 = this.q.w()) < this.q.q() - 1) {
                        this.q(bp, (bp)this.q.q(w2 + 1));
                    }
                }
                return true;
            }
            case 701: {
                super.w = true;
                this.w((bp)event.arg);
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
    
    private void q(final bp bp, final bp bp2) {
        final int s = bp2.s;
        bp2.s = bp.s;
        bp.s = s;
        this.q.q(bp2, bp2.s);
        this.q.q(bp, bp.s);
        this.q.w();
        super.w = true;
        this.w(bp);
    }
    
    public void w() {
        this.q.r();
        this.q.q();
    }
    
    public void e() {
    }
    
    public void e(final bp bp) {
        final int q = this.q.q((bJ)bp);
        this.q.q(bp);
        if (q == -1) {
            this.q.e(bp);
            if (bp.q(62)) {
                this.q.q(bp, true);
            }
            if (this.q != null) {
                final bp q2;
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
            this.q.q(bp, q);
            if (this.q != null) {
                this.q.dispose();
                this.q = null;
            }
        }
        this.w(bp);
    }
    
    public final void r() {
        this.q.dispose();
        this.q = null;
    }
    
    public final String q(final Object o) {
        final String lowerCase = this.q.toLowerCase();
        if (o instanceof ad) {
            if (((ad)o).q()) {
                if (o == this.y) {
                    return B.q(be.w("Click here to add a new %1."), new String[] { lowerCase });
                }
                if (o == this.t) {
                    return B.q(be.w("Click here to edit the selected %1."), new String[] { lowerCase });
                }
                if (o == this.q) {
                    if (this.q.q.equals(be.w("Remove"))) {
                        return B.q(be.w("Click here to remove the selected %1."), new String[] { lowerCase });
                    }
                    return B.q(be.w("Click here to restore the selected %1."), new String[] { lowerCase });
                }
                else {
                    if (o == this.w) {
                        return be.w("Click here to remove all entries.");
                    }
                    if (o == this.e) {
                        return B.q(be.w("Click here to move the selected %1 up."), new String[] { lowerCase });
                    }
                    if (o == this.r) {
                        return B.q(be.w("Click here to move the selected %1 down."), new String[] { lowerCase });
                    }
                }
            }
            else {
                if (this.q.w() < 0) {
                    return B.q(be.w("This button is disabled because no %1 is selected."), new String[] { lowerCase });
                }
                if (o == this.t) {
                    return B.q(be.w("This button is disabled because the selected %1 cannot be edited."), new String[] { lowerCase });
                }
                return B.q(be.w("This button is disabled because the selected %1 cannot be removed."), new String[] { lowerCase });
            }
        }
        return null;
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final int q() {
        return this.q.q;
    }
    
    public final bp q(final int n) {
        return (bp)this.q.q(n);
    }
    
    public final bp w(final int n) {
        return (bp)this.q.w(n);
    }
    
    public G(final ap ap, final String s, final String q) {
        super(s);
        this.w = be.w("Remove All");
        this.e = be.w("Restore All");
        this.r = be.w("Remove");
        this.t = be.w("Restore");
        this.y = be.w("Add");
        this.u = be.w("Edit");
        be.w("Move Up");
        be.w("Move Down");
        this.q = (cT)ap;
        this.t = new ad(80, 20);
        this.y = new ad(80, 20);
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        if (this.e == null) {
            this.e = (ad)bi.q(null, "up_arrow", "up_arrow.gif", this.q, 30, 30);
        }
        if (this.r == null) {
            this.r = (ad)bi.q(null, "dn_arrow", "dn_arrow.gif", this.q, 30, 30);
        }
        new Canvas().setSize(35, 25);
        this.q = new dS();
        new Canvas().setSize(35, 25);
        this.w = new dS();
        this.q = null;
        this.q = false;
        this.q = null;
        this.q = new bV(null, "default");
        this.q = new aX("ID");
        this.w = new aX(be.w("Name"), "name");
        this.q = (cT)ap;
        this.q = q;
        this.q = new M(10, 1000);
        (this.q = new cc()).w(this.q);
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
        final bZ bz = new bZ(this.q);
        layout.setConstraints(bz, gridBagConstraints);
        panel.add(bz);
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.t.getFontMetrics(this.t.getFont())).stringWidth(this.u);
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
        if (!(this instanceof ei) || this.q.q(49)) {
            layout.setConstraints(this.t, gridBagConstraints);
            this.t.q(this.u);
            this.t.resize(stringWidth, 20);
            panel.add(this.t);
        }
        if (!(this instanceof ei) || this.q.q(49) || this.q.q(2)) {
            this.q.q(this.r);
            this.q.resize(stringWidth, 20);
            layout.setConstraints(this.q, gridBagConstraints);
            panel.add(this.q);
            this.w.q(this.w);
            this.w.resize(stringWidth, 20);
            layout.setConstraints(this.w, gridBagConstraints);
            panel.add(this.w);
        }
        if (!(this instanceof ei) || this.q.q(49) || this.q.q(1)) {
            this.y.q(this.y);
            this.y.resize(stringWidth, 20);
            layout.setConstraints(this.y, gridBagConstraints);
            panel.add(this.y);
        }
        gridBagConstraints.anchor = 15;
        panel.add(new Label(), gridBagConstraints);
        gridBagConstraints.weighty = 0.1;
        panel.add(this.e, gridBagConstraints);
        gridBagConstraints.weighty = 0.0;
        panel.add(this.r, gridBagConstraints);
        this.e.setVisible(false);
        this.r.setVisible(false);
        this.q(panel, 1, 1.0f, 1.0f);
    }
}
