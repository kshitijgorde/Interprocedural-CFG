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

public class aH extends aV
{
    protected ci q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    protected cq q;
    private aF q;
    private int q;
    protected l q;
    private S q;
    private ag q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (aU.w.j()) {
            q = aD.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new M(70, 20);
            }
            else {
                q = new M(s3, 70, 20);
            }
            ((M)q).q(s4, null);
        }
        else if (q instanceof w) {
            ((w)q).q(s4, null);
        }
        else {
            ((M)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final r r) {
        if (r.q != null && r.q.o != 0) {
            r.s = r.q.o;
        }
        this.q.q(r);
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
                if (event.key == 10 || event.key == bF.q) {
                    if (this.q instanceof M) {
                        ((M)this.q).r();
                    }
                    else {
                        ((w)this.q).q();
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
                        this.q.e();
                    }
                    if (bF.q) {
                        this.q.q.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.w) {
                    this.q.r(this.q);
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
        this.q.q.w(this.q);
    }
    
    public void w() {
    }
    
    public boolean q() {
        return false;
    }
    
    public aH(final cq q, final l q2, final aF q3) {
        super(true);
        this.setBackground(aU.w.q);
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
        this.q = new S(this.q, this.q);
        this.q = q2;
        String s;
        final int lastIndex;
        if ((lastIndex = (s = q2.o).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(a.s.q(ak.q("Private Conversation with %1"), new String[] { this.q.q(s) }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cy cy = new cy();
        this.setLayout(gridBagLayout);
        cy.setLayout(gridBagLayout);
        (this.q = q3).setFont(aU.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bd bd = new bd(q3);
        gridBagLayout.setConstraints(bd, gridBagConstraints);
        cy.add(bd);
        this.q = new ci((W)q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        cy.add(this.q);
        cy.setBackground(aU.w.i);
        cy.setForeground(aU.w.u);
        boolean b = (!bF.w || bF.w != 1) && q.q(20) && q2.q(21) && q2.s != q.s;
        if (!this.q()) {
            b = false;
        }
        final String e = aU.w.e();
        final String s2 = b ? "_3" : "_2";
        this.w = this.q(e, "profile_request" + s2, ak.q("Profile"), a.s.q(ak.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { q2.o }));
        this.q = this.q(e, "send_small" + s2, ak.q("Send"), ak.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.w.getFontMetrics(bf.w)).stringWidth(ak.q("Send"));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(ak.q("Profile"))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.e = this.q(e, "file" + s2, ak.q("File Transfer"), a.s.q(ak.q("Click here to send a file to %1."), new String[] { q2.o }));
        }
        gridBagConstraints.insets = ((this.w instanceof w || this.q instanceof w || this.e instanceof w) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.q instanceof w) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.q instanceof M) {
            final Z z = new Z(this.q);
            gridBagLayout.setConstraints(z, gridBagConstraints);
            cy.add(z);
        }
        else {
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            cy.add(this.q);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.w instanceof w) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (this.w instanceof M) {
            this.w.resize(stringWidth2, 20);
        }
        cy.add(this.w);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            cy.add(this.e);
        }
        if (this.q instanceof M) {
            this.q.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        final W w = (W)this.q;
        final Dimension size = this.getSize();
        final W w2 = w;
        Object q4;
        if (bN.q()) {
            q4 = new bL(w2, q2);
        }
        else {
            q4 = new bw(this, w2, q2, size.width, size.height);
        }
        this.q = (ag)q4;
        if (bN.q()) {
            this.setMenuBar(((bL)this.q).q(this));
        }
        else {
            this.add(((bj)this.q).q(), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cy, gridBagConstraints);
        this.add(cy);
        new Thread(new aL(this)).start();
    }
}
