// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import javax.accessibility.Accessible;
import java.awt.Panel;
import java.awt.GridLayout;
import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.dw;
import com.diginet.digichat.awt.t;
import java.awt.Insets;
import com.diginet.digichat.awt.DragContainer;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.bj;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.net.URL;
import com.diginet.digichat.network.v;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import java.awt.Component;
import com.diginet.digichat.awt.r;
import java.awt.Image;
import com.diginet.digichat.awt.bk;
import java.awt.Point;
import com.diginet.digichat.common.j;
import java.awt.Canvas;
import java.awt.Frame;
import com.diginet.digichat.awt.DragListener;
import com.diginet.digichat.util.s;
import com.diginet.digichat.awt.z;

public class aa extends z implements s, DragListener
{
    protected boolean fInv;
    protected Frame frmParent;
    protected TextPanel txpMess;
    protected Canvas b;
    protected Canvas c;
    protected Canvas d;
    protected Canvas btnCheck;
    protected i e;
    protected a0 f;
    protected j h;
    private Point pntDrag;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        s a = null;
        if (this.e.cc.l() && s != null) {
            final Image a2 = this.e.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_up.gif")), true);
            final Image a3 = this.e.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_dn.gif")), true);
            if (a2 != null && a3 != null) {
                a = bk.a(a2, a3, null);
                ((bk)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new r(70, 20);
            }
            else {
                a = new r(s3, 70, 20);
            }
            ((r)a).a(s4, null);
        }
        return (r)a;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public void a(final bf bf) {
        this.f.a(bf);
    }
    
    public Point getPoint() {
        return this.pntDrag;
    }
    
    public void startDrag(final Component component, final int n, final int n2) {
    }
    
    public void endDrag(final Component component, final int n, final int n2) {
        this.pntDrag.setLocation(n, n2);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.h instanceof Buddy) {
                    ((Buddy)this.h).closeMess();
                    break;
                }
                break;
            }
            case 401: {
                if (event.key == 27) {
                    this.txpMess.appendText("\n");
                    break;
                }
                if (event.key != 10 && event.key != dx.a) {
                    break;
                }
                if (this.b instanceof r) {
                    ((r)this.b).e();
                }
                else {
                    ((bk)this.b).a();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.b) {
                    final String message;
                    if ((message = this.txpMess.getMessage()).length() > 0) {
                        if (this.h.w() != -999 || !(this.h instanceof Buddy)) {
                            this.e.a(message, this.h.w(), -1, this.txpMess.getStyle());
                        }
                        else {
                            this.e.sendMess(message, (Buddy)this.h, this.txpMess.getStyle(), this.fInv);
                            if (this.fInv) {
                                this.dispose();
                                return true;
                            }
                        }
                        this.f.e();
                    }
                    if (dx.d) {
                        this.txpMess.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.c) {
                    if (this.h instanceof Buddy) {
                        ((Buddy)this.h).callProf(this.frmParent, this.e);
                    }
                    else {
                        final v v = new v(67074, 1);
                        v.a(0, 0, this.e.w());
                        v.k = this.h.w();
                        this.e.an(v);
                    }
                    return true;
                }
                if (event.target == this.d) {
                    this.c();
                    break;
                }
                if (event.target == this.btnCheck) {
                    ((Buddy)this.h).checkBuddy();
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.e.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a() {
        this.f.d();
    }
    
    public void dispose() {
        super.dispose();
        if (this.h instanceof Buddy) {
            if (this.fInv) {
                ((Buddy)this.h).bhInv = null;
            }
            else {
                ((Buddy)this.h).bhPriv = null;
            }
        }
        else {
            this.e.z.g(this.h.w());
        }
    }
    
    public void c() {
    }
    
    public boolean d() {
        return false;
    }
    
    public aa(final Frame frmParent, final i e, final j h, final a0 f, final boolean fInv) {
        this.frmParent = frmParent;
        this.setBackground(e.cc.c);
        this.e = e;
        this.h = h;
        this.pntDrag = new Point(0, 0);
        this.fInv = fInv;
        this.setTitle(a5.a(com.esial.util.c.a(fInv ? "Send Invitation to %1" : "Private Conversation with %1"), new String[] { (this.h instanceof Buddy) ? ((Buddy)this.h).getName() : this.h.x() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bj main = new bj();
        main.setLayout(layout);
        this.txpMess = new TextPanel(e, this, this, "Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        (this.f = f).setFont(e.cc.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(f);
        layout.setConstraints(t, gridBagConstraints);
        main.add(t);
        this.txpMess.setFont(dw.c);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.txpMess, gridBagConstraints);
        main.add(this.txpMess);
        main.setBackground(e.cc.d);
        main.setForeground(e.cc.clrInnText);
        boolean b = (!dx.e || dx.b != 1) && e.i(20) && this.h.i(21) && this.h.w() != e.w();
        if (!this.d()) {
            b = false;
        }
        final String f2 = e.cc.f();
        final String s = b ? "_3" : "_2";
        this.b = this.a(f2, String.valueOf("send_small").concat(String.valueOf(s)), com.esial.util.c.a("Send"), com.esial.util.c.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        Accessible accessible = (this.b instanceof r) ? new u(this.b) : this.b;
        this.c = this.a(f2, String.valueOf("profile_request").concat(String.valueOf(s)), com.esial.util.c.a("Profile"), a5.a(com.esial.util.c.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.h.x() }));
        this.btnCheck = null;
        if (h instanceof Buddy && ((Buddy)h).isCheckable()) {
            this.btnCheck = this.a(f2, String.valueOf("check_buddy").concat(String.valueOf(s)), com.esial.util.c.a("Check"), a5.a(com.esial.util.c.a("Click here to check %1."), new String[] { this.h.x() }));
        }
        Canvas c;
        if (b) {
            final Canvas a = this.a(f2, String.valueOf("file").concat(String.valueOf(s)), com.esial.util.c.a("File Transfer"), a5.a(com.esial.util.c.a("Click here to send a file to %1."), new String[] { this.h.x() }));
            this.d = a;
            c = a;
            final Panel panel = new Panel(new GridLayout(1, 0));
            panel.add((Component)accessible);
            panel.add(this.c);
            if (this.btnCheck != null) {
                panel.add(this.btnCheck);
            }
            accessible = panel;
        }
        else if (this.btnCheck == null) {
            c = this.c;
        }
        else {
            final Panel panel2 = (Panel)(c = (Canvas)new Panel(new GridLayout(1, 0)));
            panel2.add(this.c);
            panel2.add(this.btnCheck);
        }
        final Panel panel3 = new Panel(new GridLayout(0, 1, 0, 2));
        panel3.add((Component)accessible);
        panel3.add(c);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(panel3, gridBagConstraints);
        main.add(panel3);
        this.setMain(main);
    }
}
