// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Event;
import java.awt.event.ComponentEvent;
import java.awt.Insets;
import java.awt.Canvas;

public class bm extends bE
{
    protected dw q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    protected dH q;
    private bk q;
    private int q;
    protected p q;
    private aG q;
    private az q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (bC.w.z()) {
            q = bi.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new ad(70, 20);
            }
            else {
                q = new ad(s3, 70, 20);
            }
            ((ad)q).q(s4, null);
        }
        else if (q instanceof H) {
            ((H)q).q(s4, null);
        }
        else {
            ((ad)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final A a) {
        if (a.q != null && a.q.o != 0) {
            a.d = a.q.o;
        }
        this.q.q(a);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        super.componentResized(componentEvent);
        if (this.q != null) {
            this.q.q();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
                    if (this.q instanceof ad) {
                        ((ad)this.q).r();
                    }
                    else {
                        ((H)this.q).q();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final String q;
                    if ((q = this.q.q(true)).length() > 0) {
                        this.q.w(q, this.q.s, -1, this.q.q.getForeground().getRGB());
                        this.q.r();
                    }
                    if (cK.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.w) {
                    this.q.y(this.q);
                    return true;
                }
                if (event.target == this.e) {
                    this.w();
                    break;
                }
                if (event.arg instanceof URL) {
                    this.q.q((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q() {
        this.q.w();
    }
    
    public void dispose() {
        System.out.println("Close private conversation with:" + this.q);
        super.dispose();
        this.q.o.w(this.q);
    }
    
    public void w() {
    }
    
    public boolean q() {
        return false;
    }
    
    public bm(final dH q, final p q2, final bk q3) {
        super(true);
        this.setBackground(bC.w.q);
        final Dimension screenSize;
        int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
        int n2 = screenSize.height / 2 - 20;
        if (n > 400) {
            n = 400;
        }
        if (n2 > 375) {
            n2 = 375;
        }
        this.setSize(n, n2);
        this.q = q;
        this.q = q2.s;
        this.q = new aG(this.q, this.q);
        this.q = q2;
        String s;
        final int lastIndex;
        if ((lastIndex = (s = q2.a).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(B.q(be.w("Private Conversation with %1"), new String[] { this.q.q(s) }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final dT dt = new dT();
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        (this.q = q3).setFont(bC.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bZ bz = new bZ(q3);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        dt.add(bz);
        this.q = new dw((ap)q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        dt.add(this.q);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        boolean b = (!cK.w || cK.w != 1) && q.q(20) && q2.q(21) && q2.s != q.s;
        if (!this.q()) {
            b = false;
        }
        final String r = bC.w.r();
        final String s2 = b ? "_3" : "_2";
        this.w = this.q(r, "profile_request" + s2, be.w("Profile"), B.q(be.w("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { q2.a }));
        this.q = this.q(r, "send_small" + s2, be.w("Send"), be.w("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.w.getFontMetrics(cb.w)).stringWidth(be.w("Send"));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(be.w("Profile"))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.e = this.q(r, "file" + s2, be.w("File Transfer"), B.q(be.w("Click here to send a file to %1."), new String[] { q2.a }));
        }
        gridBagConstraints.insets = ((this.w instanceof H || this.q instanceof H || this.e instanceof H) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.q instanceof H) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.q instanceof ad) {
            final as as = new as(this.q);
            gridBagLayout.setConstraints(as, gridBagConstraints);
            dt.add(as);
        }
        else {
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            dt.add(this.q);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.w instanceof H) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (this.w instanceof ad) {
            this.w.resize(stringWidth2, 20);
        }
        dt.add(this.w);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            dt.add(this.e);
        }
        if (this.q instanceof ad) {
            this.q.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        final ap ap = (ap)this.q;
        final Dimension size = this.getSize();
        final ap ap2 = ap;
        Object q4;
        if (cU.q()) {
            q4 = new cR(ap2, q2);
        }
        else {
            q4 = new cy(this, ap2, q2, size.width, size.height);
        }
        this.q = (az)q4;
        if (cU.q()) {
            this.setMenuBar(((cR)this.q).q(this));
        }
        else {
            this.add(((cg)this.q).q(), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        new Thread(new bs(this)).start();
    }
}
