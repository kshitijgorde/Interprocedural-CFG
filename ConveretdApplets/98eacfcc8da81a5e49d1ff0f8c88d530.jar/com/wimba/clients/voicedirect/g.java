// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import VT_6_1_0_11.an;
import java.util.Date;
import java.text.DateFormat;
import VT_6_1_0_11.bj;
import VT_6_1_0_11.B;
import java.text.MessageFormat;
import VT_6_1_0_11.au;
import VT_6_1_0_11.bE;
import VT_6_1_0_11.aM;
import javax.swing.JComponent;
import java.applet.Applet;
import VT_6_1_0_11.ca;
import com.hw.client.util.a;
import VT_6_1_0_11.h;
import javax.swing.JApplet;
import VT_6_1_0_11.aN;
import VT_6_1_0_11.l;

public final class g extends l
{
    private aN w;
    private com.wimba.clients.voicedirect.l z;
    private String A;
    
    public g(final JApplet applet) {
        super(applet);
        this.o = "voicedirect";
        this.setBorder(VT_6_1_0_11.h.h());
        this.setName(this.a().getString("blackboard.voice.direct"));
    }
    
    protected final void p() {
        com.hw.client.util.a.b("VoiceDirectApplet.initGUI");
        String trim;
        if ((trim = ca.a(this.t, "login", "").trim()).equalsIgnoreCase("null")) {
            trim = "";
        }
        final String a = ca.a(this.t, "password", "");
        String trim2;
        if ((trim2 = ca.a(this.t, "screen_name", "").trim()).equalsIgnoreCase("null")) {
            trim2 = "";
        }
        this.w = new aN(this);
        this.a(this.z = new com.wimba.clients.voicedirect.l(this));
        this.a(this.w, 7);
        this.revalidate();
        this.f(null);
        this.z.setVisible(true);
        if (trim.length() != 0 || trim2.length() != 0 || this.h != null) {
            com.hw.client.util.a.b("login or screen name not null");
            this.a(trim, a, trim2, this.h);
        }
        else {
            this.w.setVisible(true);
        }
        com.hw.client.util.a.b("VoiceDirectApplet.initGUI ends");
    }
    
    public final void a(final bE c) {
        String b;
        if ((b = c.b("status_code")) == null) {
            b = "ok";
        }
        com.hw.client.util.a.d("VoiceDirectApplet.processAuthResponse sc=" + b);
        if (!b.equals("ok")) {
            com.hw.client.util.a.d("status_code=" + b + ", msg=" + c.b("error_message"));
        }
        if (b.equals("too_many_users")) {
            this.F().a(null, this.e("main_dlg_too_many_users_title"), this.e("main_dlg_too_many_users_msg"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("page_not_allowed")) {
            this.F().a(null, this.e("main_dlg_untrusted_page_title"), this.e("main_dlg_untrusted_page_msg"));
            this.v();
            this.a.e();
            return;
        }
        if (b.equals("authentication_failed")) {
            this.w.a(this.e("error_authentication_failed"));
            this.F().a();
            this.w.setVisible(true);
            return;
        }
        if (b.equals("forbidden")) {
            this.w.a(this.e("error_access_forbidden"));
            this.F().a();
            this.w.setVisible(true);
            return;
        }
        if (b.equals("guest_not_allowed")) {
            this.w.a(this.e("error_guest_not_allowed"));
            this.F().a();
            this.w.setVisible(true);
            return;
        }
        if (b.equals("invalid_screen_name")) {
            this.w.a(this.e("error_invalid_screen_name"));
            this.F().a();
            this.w.setVisible(true);
            return;
        }
        if (b.equals("server_error")) {
            this.F().a(null, this.a().getString("main_dlg_channel_error_title"), MessageFormat.format(this.a().getString("server.error.reload.page"), c.b("error_message")));
            this.a.e();
            return;
        }
        if (b.equals("invalid_parameter")) {
            this.F().a(null, this.a().getString("main_dlg_channel_error_title"), MessageFormat.format(this.a().getString("invalid.parameter"), c.b("error_message")));
            this.a.e();
            return;
        }
        if (b.equals("ok")) {
            this.c = c;
            com.hw.client.util.a.d("Session created");
            this.j = c.a("resource").b("rid");
            this.A = c.a("user").b("screen_name");
            this.f = ca.a(c.a("rights").b("add"), ", ");
            this.d = c.a("resource").a("options");
            this.z.c().a(new B(this.d.a("audio_format")));
            if (this.a("view_archives") || this.a("manage_archives")) {
                this.z.g().a(this.z.k);
            }
            if (this.a("manage_archives")) {
                this.z.g().a(this.z.j());
            }
            this.z.c().d();
            this.f(null);
            this.F().a();
            this.w.setVisible(false);
            if (bj.e()) {
                this.z.b.a.requestFocus();
            }
            this.z.d();
            if (c.b("invalid_license") != null) {
                this.F().a(this, this.e("error_license_invalid"), c.b("invalid_license"), this.e("btn_ok"), "AC_CLOSE");
            }
            else if (c.b("license_will_expire") != null) {
                com.hw.client.util.a.c("License will expire soon");
            }
            else {
                if (c.b("license_is_grace") == null) {
                    if (c.b("license_is_locked") != null) {
                        this.F().a(this, this.e("error_license_is_locked_title"), this.e("error_license_is_locked_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_locked"))))));
                    }
                    return;
                }
                this.F().a(this, this.e("error_license_grace_period_title"), this.b("error_license_grace_period_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_grace")))), "" + (15L - (System.currentTimeMillis() - Long.parseLong(c.b("license_is_grace"))) / 86400000L)), this.e("btn_ok"), "AC_CLOSE");
            }
        }
        else {
            com.hw.client.util.a.d("VoiceDirectApplet.processAuthResponse: unknown status code=" + b);
        }
    }
    
    public final String D() {
        return this.A;
    }
    
    public final void d(final int n) {
        this.z.a.a(n);
    }
    
    public final void a(final an an) {
        this.a(an.e());
    }
    
    public final void d(final Hashtable hashtable) {
        this.z.a(an.a(hashtable));
    }
    
    public final void d(final String s) {
        com.hw.client.util.a.c("processViewArchives nid=" + s);
        try {
            final URL url = new URL(this.t.getCodeBase(), this.l + "&nid=" + s);
            if (this.m == null) {
                this.t.getAppletContext().showDocument(url);
                return;
            }
            this.t.getAppletContext().showDocument(url, this.m);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.d("VoiceDirectApplet.processViewArchives: bad url: " + ex);
        }
    }
}
