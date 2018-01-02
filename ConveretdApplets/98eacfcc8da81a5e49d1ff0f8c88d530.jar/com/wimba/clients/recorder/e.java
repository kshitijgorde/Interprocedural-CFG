// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import java.awt.Rectangle;
import VT_6_1_0_11.cS;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import com.hw.client.util.c;
import VT_6_1_0_11.cP;
import java.awt.Font;
import VT_6_1_0_11.aT;
import VT_6_1_0_11.au;
import VT_6_1_0_11.dv;
import java.io.InputStream;
import java.util.Observable;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import VT_6_1_0_11.B;
import VT_6_1_0_11.ca;
import javax.swing.JComponent;
import com.hw.client.util.a;
import VT_6_1_0_11.bE;
import javax.swing.JApplet;
import javax.swing.JPanel;
import VT_6_1_0_11.t;
import java.util.Observer;
import VT_6_1_0_11.l;

public final class e extends l implements Observer
{
    private d w;
    private t z;
    private JPanel A;
    
    public e(final JApplet applet) {
        super(applet);
        this.o = "recorder";
        if (this.j == null) {
            this.j = "recorder";
        }
        this.setName(this.a().getString("voice.authoring"));
    }
    
    public final void r() {
        this.w.setVisible(false);
        this.f(this.e("error_communication"));
    }
    
    public final void a(bE c) {
        String b;
        if ((b = c.b("status_code")) == null) {
            b = "ok";
        }
        com.hw.client.util.a.c("RecorderDesktopPane.processAuthResponse sc=" + b);
        if (!b.equals("ok")) {
            com.hw.client.util.a.c("RecorderDesktopPane.processAuthResponse: status_code=" + b + ", msg=" + c.b("error_message"));
        }
        if (b.equals("too_many_users")) {
            this.f(this.e("error_too_many_users"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("page_not_allowed")) {
            this.f(this.e("error_page_not_allowed"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("authentication_failed")) {
            this.f(this.e("error_authentication_failed"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("forbidden")) {
            this.f(this.e("error_access_forbidden"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("guest_not_allowed")) {
            this.f(this.e("error_no_public_access"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("invalid_screen_name")) {
            this.f(this.e("error_invalid_screen_name") + "\n" + c.b("error_message"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("server_error")) {
            this.f(this.e("error_server") + "\n" + c.b("error_message"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("invalid_parameter")) {
            this.f(this.e("invalid_parameter", c.b("error_message")));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("ok")) {
            this.a(this.w = new d(this, this.b));
            com.hw.client.util.a.d("RecorderDesktopPane.processAuthResponse: Session created");
            if (c.b("invalid_license") != null) {
                com.hw.client.util.a.c("License invalid");
                this.w.d().a("License invalid");
                this.w.d().o();
            }
            else if (c.b("license_is_locked") != null) {
                com.hw.client.util.a.c("License expired");
                this.w.d().a("License expired");
                this.setEnabled(false);
                this.w.setEnabled(false);
                this.w.d().setEnabled(false);
                this.w.d().f(true);
            }
            else {
                if (c.b("license_will_expire") != null) {
                    com.hw.client.util.a.c("License will expire soon");
                }
                else if (c.b("license_is_grace") != null) {
                    com.hw.client.util.a.c("License expired");
                    this.w.d().a("License expired");
                }
                this.c = c;
                this.j = c.a("resource").b("rid");
                final bE a;
                if ((a = c.a("message")) != null) {
                    this.i = a.b("mid");
                }
                com.hw.client.util.a.d("RecorderDesktopPane.processAuthResponse: mid=" + this.i);
                if (this.i != null && this.i.trim().length() == 0) {
                    this.i = null;
                }
                this.f = ca.a(c.a("rights").b("add"), ", ");
                this.d = c.a("resource").a("options");
                final B b2 = new B(this.d.a("audio_format"));
                int n;
                if ((n = ca.a(this.t, "max_message_length", -1)) <= 0) {
                    final String trim;
                    if ((trim = this.b("max_length", "").trim()).length() > 0) {
                        n = Integer.parseInt(trim);
                    }
                    else {
                        n = 180;
                    }
                }
                if (n <= 0) {
                    n = 180;
                }
                if (n > 1200) {
                    n = 1200;
                }
                this.w.d().a(b2);
                this.w.d().a(n);
                this.w.d().r().c(this.j);
                this.w.d().r().a(this.i);
                final boolean b3 = !this.a("auto_validate", "auto_validate").trim().equalsIgnoreCase("false");
                final boolean b4 = !this.a("play_last", "play_last").trim().equalsIgnoreCase("false");
                this.w.a(b3);
                if (this.a("record_message") && b4 && this.i != null) {
                    c = null;
                    try {
                        if (this.h != null && this.h.length() != 0) {
                            c = (bE)(ca.a(this.t) + "/audio?nid=" + this.h);
                        }
                        else {
                            c = (bE)(ca.a(this.t) + "/audio?rid=" + this.j + "&mid=" + this.i);
                        }
                        (this.z = new t(new URL(this.g, (String)c), false, this, 0, 0)).a();
                    }
                    catch (MalformedURLException ex) {
                        com.hw.client.util.a.d("RecorderDesktopPane.processAuthResponse: MalformedURLException while loading urlis for " + (String)c);
                    }
                }
                if (!this.a("record_message")) {
                    this.w.d().i().setEnabled(false);
                    com.hw.client.util.a.e("RecorderDesktopPane.processAuthResponse: Does not have right to record the message");
                }
            }
            this.F().a();
            this.w.c();
            this.f(null);
            this.revalidate();
        }
    }
    
    public final void w() {
        this.w.d().b(3);
        super.w();
    }
    
    public final void f(final int n, final String s) {
        com.hw.client.util.a.c("RecordcerApplet.processPostRecordingResponse: sc=" + n + ", msg=" + s);
        if (n == 1) {
            this.w.d().b(0);
            this.w.d().g(true);
            return;
        }
        this.w.setVisible(false);
        this.f(this.e("error_server"));
        this.a.e();
    }
    
    public final void p() {
        String trim;
        if ((trim = ca.a(this.t, "login", "").trim()).equalsIgnoreCase("null")) {
            trim = "";
        }
        final String a = ca.a(this.t, "password", "");
        String trim2;
        if ((trim2 = ca.a(this.t, "screen_name", "undefined").trim()).equalsIgnoreCase("null")) {
            trim2 = "";
        }
        this.f(this.e("connecting_server"));
        if (trim.length() != 0 || trim2.length() != 0 || this.h != null) {
            this.a(trim, a, trim2, this.h);
        }
    }
    
    public final void update(final Observable observable, final Object o) {
        if (this.z != null && observable == this.z.b && o == VT_6_1_0_11.t.a) {
            final B b = new B(this.z.b(), this.z);
            this.w.d().a(b, this.z);
            this.w.b = b;
            this.w.a = this.z;
            if (this.i != null) {
                this.w.d().g(true);
            }
        }
    }
    
    public final void o() {
        super.o();
        try {
            this.z.close();
        }
        catch (Exception ex) {}
    }
    
    public final void a(final au au, final String actionCommand, final String actionCommand2) {
        final Rectangle bounds = this.w.d().m().getBounds();
        if (this.A == null) {
            final b b = new b(this, au);
            final aT at;
            (at = new aT(this.e("comp_dlg_override_msg"), 0)).setFont(new Font("Dialog", 0, 12));
            at.setAlignmentX(0.5f);
            final cP cp;
            (cp = new cP(this.e("btn_yes"))).setIcon(com.hw.client.util.c.a("/images/common/yes-16.png"));
            cp.setActionCommand(actionCommand);
            cp.addActionListener(b);
            cp.setOpaque(false);
            cp.setAlignmentX(0.5f);
            final cP cp2;
            (cp2 = new cP(this.e("btn_no"))).setIcon(com.hw.client.util.c.a("/images/common/no-16.png"));
            cp2.setActionCommand(actionCommand2);
            cp2.addActionListener(b);
            cp2.setOpaque(false);
            cp2.setAlignmentX(0.5f);
            final JPanel panel;
            (panel = new JPanel()).setLayout(new BoxLayout(panel, 0));
            panel.setOpaque(false);
            panel.setAlignmentX(0.5f);
            panel.add(Box.createHorizontalGlue());
            panel.add(cp);
            panel.add(Box.createRigidArea(new Dimension(8, 2)));
            panel.add(cp2);
            panel.add(Box.createHorizontalGlue());
            (this.A = new cS("/images/recorder/bg_middle.png")).setBounds(this.w.d().m().getBounds());
            this.A.setVisible(false);
            this.A.setLayout(new BoxLayout(this.A, 1));
            this.A.add(Box.createVerticalGlue());
            this.A.add(at);
            this.A.add(panel);
            this.A.add(Box.createVerticalGlue());
            this.add(this.A, 5);
        }
        this.A.setBounds(bounds);
        this.A.setVisible(true);
    }
    
    static JPanel a(final e e) {
        return e.A;
    }
}
