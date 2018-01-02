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

public class bM extends s
{
    protected Q q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    protected bH q;
    private bC q;
    private int q;
    protected bp q;
    private cj q;
    private at q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (be.w.h()) {
            q = cc.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new e(70, 20);
            }
            else {
                q = new e(s3, 70, 20);
            }
            ((e)q).q(s4, null);
        }
        else if (q instanceof n) {
            ((n)q).q(s4, null);
        }
        else {
            ((e)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final bF bf) {
        if (bf.q != null && bf.q.d != 0) {
            bf.s = bf.q.d;
        }
        this.q.q(bf);
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
                if (event.key == 10 || event.key == cx.q) {
                    if (this.q instanceof e) {
                        ((e)this.q).r();
                    }
                    else {
                        ((n)this.q).q();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final String q;
                    if ((q = this.q.q(true)).length() > 0) {
                        this.q.q(q, this.q.q(), -1, this.q.q().getForeground().getRGB());
                        this.q.e();
                    }
                    if (cx.q) {
                        this.q.q().requestFocus();
                    }
                    return true;
                }
                if (event.target == this.w) {
                    this.q.e(this.q);
                    return true;
                }
                if (event.target == this.e) {
                    this.q();
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
    
    public final void w() {
        this.q.w();
    }
    
    public void dispose() {
        System.out.println("Close private conversation with:" + this.q);
        super.dispose();
        this.q.q.w(this.q);
    }
    
    public void q() {
    }
    
    public boolean q() {
        return false;
    }
    
    public bM(final bH q, final bp q2, final bC q3) {
        super(true);
        this.setBackground(be.w.q);
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
        this.q = q2.q();
        this.q = new cj(this.q, this.q);
        this.q = q2;
        String s;
        final int lastIndex;
        if ((lastIndex = (s = q2.getName()).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(cv.q(cv.q("Private Conversation with %1"), new String[] { this.q.q(s) }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final q q4 = new q();
        this.setLayout(gridBagLayout);
        q4.setLayout(gridBagLayout);
        (this.q = q3).setFont(be.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final r r = new r(q3);
        gridBagLayout.setConstraints(r, gridBagConstraints);
        q4.add(r);
        this.q = new Q((bI)q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        q4.add(this.q);
        q4.setBackground(be.w.i);
        q4.setForeground(be.w.u);
        boolean b = (!cx.w || cx.w != 1) && q.q(20) && q2.q(21) && q2.q() != q.q();
        if (!this.q()) {
            b = false;
        }
        final String w = be.w.w();
        final String s2 = b ? "_3" : "_2";
        this.w = this.q(w, "profile_request" + s2, cv.q("Profile"), cv.q(cv.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { q2.getName() }));
        this.q = this.q(w, "send_small" + s2, cv.q("Send"), cv.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.w.getFontMetrics(k.w)).stringWidth(cv.q("Send"));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(cv.q("Profile"))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.e = this.q(w, "file" + s2, cv.q("File Transfer"), cv.q(cv.q("Click here to send a file to %1."), new String[] { q2.getName() }));
        }
        gridBagConstraints.insets = ((this.w instanceof n || this.q instanceof n || this.e instanceof n) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.q instanceof n) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.q instanceof e) {
            final d d = new d(this.q);
            gridBagLayout.setConstraints(d, gridBagConstraints);
            q4.add(d);
        }
        else {
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            q4.add(this.q);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.w instanceof n) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (this.w instanceof e) {
            this.w.resize(stringWidth2, 20);
        }
        q4.add(this.w);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            q4.add(this.e);
        }
        if (this.q instanceof e) {
            this.q.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        final bI bi = (bI)this.q;
        final Dimension size = this.getSize();
        final bI bi2 = bi;
        Object q5;
        if (ar.q()) {
            q5 = new aT(bi2, q2);
        }
        else {
            q5 = new aG(this, bi2, q2, size.width, size.height);
        }
        this.q = (at)q5;
        if (ar.q()) {
            this.setMenuBar(((aT)this.q).q(this));
        }
        else {
            this.add(((ay)this.q).q(), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(q4, gridBagConstraints);
        this.add(q4);
        new Thread(new cm(this)).start();
    }
}
