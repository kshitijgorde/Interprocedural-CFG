// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Dimension;
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

public class aI extends aU
{
    protected cg q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    protected co q;
    private aG q;
    private int q;
    protected l q;
    private T q;
    private ah q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (aT.w.g()) {
            q = aE.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new N(70, 20);
            }
            else {
                q = new N(s3, 70, 20);
            }
            ((N)q).q(s4, null);
        }
        else if (q instanceof x) {
            ((x)q).q(s4, null);
        }
        else {
            ((N)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final s s) {
        this.q.q(s);
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
                if (event.key == 10 || event.key == bE.q) {
                    if (this.q instanceof N) {
                        ((N)this.q).r();
                    }
                    else {
                        ((x)this.q).q();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final String q;
                    if ((q = this.q.q(true)).length() > 0) {
                        this.q.w(q, this.q.a, -1, this.q.q.getForeground().getRGB());
                        this.q.e();
                    }
                    if (bE.q) {
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
    
    public aI(final co q, final l q2, final aG q3) {
        super(true);
        this.setBackground(aT.w.q);
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
        this.q = q2.a;
        this.q = new T(this.q, this.q);
        this.q = q2;
        String s;
        final int lastIndex;
        if ((lastIndex = (s = q2.o).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(t.q(al.q("Private Conversation with %1"), new String[] { this.q.q(s) }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cv cv = new cv();
        this.setLayout(gridBagLayout);
        cv.setLayout(gridBagLayout);
        (this.q = q3).setFont(aT.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bc bc = new bc(q3);
        gridBagLayout.setConstraints(bc, gridBagConstraints);
        cv.add(bc);
        this.q = new cg((W)q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        cv.add(this.q);
        cv.setBackground(aT.w.i);
        cv.setForeground(aT.w.u);
        boolean b = (!bE.w || bE.w != 1) && q.q(20) && q2.q(21) && q2.a != q.a;
        if (!this.q()) {
            b = false;
        }
        final String e = aT.w.e();
        final String s2 = b ? "_3" : "_2";
        this.w = this.q(e, "profile_request" + s2, al.q("Profile"), t.q(al.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { q2.o }));
        this.q = this.q(e, "send_small" + s2, al.q("Send"), al.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.w.getFontMetrics(be.w)).stringWidth(al.q("Send"));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(al.q("Profile"))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.e = this.q(e, "file" + s2, al.q("File Transfer"), t.q(al.q("Click here to send a file to %1."), new String[] { q2.o }));
        }
        gridBagConstraints.insets = ((this.w instanceof x || this.q instanceof x || this.e instanceof x) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.q instanceof x) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.q instanceof N) {
            final ab ab = new ab(this.q);
            gridBagLayout.setConstraints(ab, gridBagConstraints);
            cv.add(ab);
        }
        else {
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            cv.add(this.q);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.w instanceof x) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (this.w instanceof N) {
            this.w.resize(stringWidth2, 20);
        }
        cv.add(this.w);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            cv.add(this.e);
        }
        if (this.q instanceof N) {
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
        if (bM.q()) {
            q4 = new bK(w2, q2);
        }
        else {
            q4 = new bv(this, w2, q2, size.width, size.height);
        }
        this.q = (ah)q4;
        if (bM.q()) {
            this.setMenuBar(((bK)this.q).q(this));
        }
        else {
            this.add(((bi)this.q).q(), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cv, gridBagConstraints);
        this.add(cv);
    }
}
