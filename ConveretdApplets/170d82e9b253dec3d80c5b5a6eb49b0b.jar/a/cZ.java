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

public class cZ extends u
{
    protected U q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    protected cU q;
    private cP q;
    private int q;
    protected cz q;
    private dP q;
    private aI q;
    
    public Insets insets() {
        final Insets insets = super.insets();
        return new Insets(insets.top - 3, insets.left, insets.bottom, insets.right);
    }
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (cf.w.k()) {
            q = dI.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new g(70, 20);
            }
            else {
                q = new g(s3, 70, 20);
            }
            ((g)q).q(s4, null);
        }
        else if (q instanceof p) {
            ((p)q).q(s4, null);
        }
        else {
            ((g)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final cS cs) {
        if (cs.q != null && cs.q.f != 0) {
            cs.d = cs.q.f;
        }
        this.q.q(cs);
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
                if (event.key == 10 || event.key == ef.q) {
                    if (this.q instanceof g) {
                        ((g)this.q).r();
                    }
                    else {
                        ((p)this.q).q();
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
                        this.q.r();
                    }
                    if (ef.q) {
                        this.q.q().requestFocus();
                    }
                    return true;
                }
                if (event.target == this.w) {
                    this.q.e(this.q);
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
    
    public final void e() {
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
    
    public cZ(final cU q, final cz q2, final cP q3) {
        super(true);
        this.setBackground(cf.w.q);
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
        this.q = new dP(this.q, this.q);
        this.q = q2;
        String s;
        final int lastIndex;
        if ((lastIndex = (s = q2.getName()).lastIndexOf(".")) >= 0 && lastIndex + 4 == s.length()) {
            s = s.substring(0, lastIndex);
        }
        this.setTitle(ec.q(eb.q("Private Conversation with %1"), new String[] { this.q.q(s) }));
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final s s2 = new s();
        this.setLayout(gridBagLayout);
        s2.setLayout(gridBagLayout);
        (this.q = q3).setFont(cf.w.w());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(q3);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        s2.add(t);
        this.q = new U((cV)q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        s2.add(this.q);
        s2.setBackground(cf.w.i);
        s2.setForeground(cf.w.u);
        boolean b = (!ef.w || ef.w != 1) && q.q(20) && q2.q(21) && q2.q() != q.q();
        if (!this.q()) {
            b = false;
        }
        final String e = cf.w.e();
        final String s3 = b ? "_3" : "_2";
        this.w = this.q(e, "profile_request" + s3, eb.q("Profile"), ec.q(eb.q("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { q2.getName() }));
        this.q = this.q(e, "send_small" + s3, eb.q("Send"), eb.q("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.w.getFontMetrics(m.w)).stringWidth(eb.q("Send"));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(eb.q("Profile"))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.e = this.q(e, "file" + s3, eb.q("File Transfer"), ec.q(eb.q("Click here to send a file to %1."), new String[] { q2.getName() }));
        }
        gridBagConstraints.insets = ((this.w instanceof p || this.q instanceof p || this.e instanceof p) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.q instanceof p) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.q instanceof g) {
            final f f = new f(this.q);
            gridBagLayout.setConstraints(f, gridBagConstraints);
            s2.add(f);
        }
        else {
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            s2.add(this.q);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.w instanceof p) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        if (this.w instanceof g) {
            this.w.resize(stringWidth2, 20);
        }
        s2.add(this.w);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            s2.add(this.e);
        }
        if (this.q instanceof g) {
            this.q.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 2;
        final cV cv = (cV)this.q;
        final Dimension size = this.getSize();
        final cV cv2 = cv;
        Object q4;
        if (aG.q()) {
            q4 = new bk(cv2, q2);
        }
        else {
            q4 = new aV(this, cv2, q2, size.width, size.height);
        }
        this.q = (aI)q4;
        if (aG.q()) {
            this.setMenuBar(((bk)this.q).q(this));
        }
        else {
            this.add(((aN)this.q).q(), gridBagConstraints);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s2, gridBagConstraints);
        this.add(s2);
        new Thread(new dS(this)).start();
    }
}
