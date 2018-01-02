// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vmail;

import java.net.MalformedURLException;
import java.net.URL;
import VT_6_1_0_11.dE;
import java.util.Date;
import java.text.DateFormat;
import VT_6_1_0_11.au;
import javax.swing.JComponent;
import VT_6_1_0_11.B;
import java.text.MessageFormat;
import VT_6_1_0_11.bE;
import java.applet.Applet;
import VT_6_1_0_11.ca;
import javax.swing.JApplet;
import VT_6_1_0_11.l;

public final class b extends l
{
    private a w;
    
    public b(final JApplet applet) {
        super(applet);
        this.o = "vmail";
        if (this.j == null) {
            this.j = "vmail";
        }
        this.setName("Voice E-Mail");
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
        String trim3;
        if ((trim3 = ca.a(this.t, "nid", "").trim()).equalsIgnoreCase("null")) {
            trim3 = "";
        }
        this.f("Authenticating...");
        this.a(trim, a, trim2, trim3);
    }
    
    public final void a(final bE c) {
        String b;
        if ((b = c.b("status_code")) == null) {
            b = "ok";
        }
        com.hw.client.util.a.d("status_code=" + b + ", msg=" + c.b("error_message"));
        if (b.equals("too_many_users")) {
            this.f(this.a().getString("too.many.users.connected"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("page_not_allowed")) {
            this.f(this.a().getString("untrusted.page"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("authentication_failed")) {
            this.f(this.a().getString("error_authentication_failed"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("forbidden")) {
            this.f(this.a().getString("error_access_forbidden"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("guest_not_allowed")) {
            this.f(this.a().getString("no.public.acccess"));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("invalid_screen_name")) {
            this.f(MessageFormat.format(this.a().getString("invalid.screen.name"), c.b("error_message")));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("server_error")) {
            this.f(MessageFormat.format(this.a().getString("server.error"), c.b("error_message")));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("invalid_parameter")) {
            this.f(MessageFormat.format(this.a().getString("invalid.parameter"), c.b("error_message")));
            this.v();
            this.a.e();
            this.a = null;
            return;
        }
        if (b.equals("ok")) {
            this.c = c;
            this.j = c.a("resource").b("rid");
            this.f = ca.a(c.a("rights").b("add"), ", ");
            this.d = c.a("resource").a("options");
            final B b2 = new B(this.d.a("audio_format"));
            final String trim;
            int n;
            if ((trim = this.b("max_length", "").trim()).length() > 0) {
                n = Integer.parseInt(trim);
            }
            else {
                n = ca.a(this.t, "max_message_length", 180);
            }
            this.a(this.w = new a(this, this.b));
            this.w.e().a(n);
            this.w.e().a(b2);
            if (this.a("send_voice_email")) {
                this.w.g().a(this.w.a);
            }
            if (this.a("view_archives")) {
                this.w.g().a(this.w.b);
            }
            this.w.revalidate();
            if (!this.a("send_voice_email")) {
                this.w.a.setEnabled(false);
            }
            this.w.d();
            this.f(null);
            this.F().a();
            this.revalidate();
            if (c.b("invalid_license") != null) {
                this.F().a(this, this.e("error_license_invalid"), c.b("invalid_license"), this.e("btn_ok"), "AC_CLOSE");
                return;
            }
            if (c.b("license_will_expire") != null) {
                com.hw.client.util.a.c("License will expire soon.");
                return;
            }
            if (c.b("license_is_grace") != null) {
                this.F().a(this, this.e("error_license_grace_period_title"), this.b("error_license_grace_period_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_grace")))), "" + (15L - (System.currentTimeMillis() - Long.parseLong(c.b("license_is_grace"))) / 86400000L)), this.e("btn_ok"), "AC_CLOSE");
                return;
            }
            if (c.b("license_is_locked") != null) {
                this.F().a(this, this.e("error_license_is_locked_title"), this.e("error_license_is_locked_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_locked"))))));
            }
        }
    }
    
    public final void c(final int n, final String s) {
        com.hw.client.util.a.c("Vmail.processPostVmailResponse: sc=" + n + ", msg=" + s);
        if (n == 1) {
            this.F().a();
            this.F().a(this, this.e("vmail_dlg_send_succeeded_title"), this.e("vmail_dlg_send_succeeded_msg"), this.e("btn_ok"), "AC_VMAIL_SENT_OK");
            return;
        }
        this.F().a();
        this.F().a(this, this.e("vmail_dlg_vmail_send_failed_title"), this.e("vmail_dlg_vmail_send_failed_msg"));
    }
    
    public final void a(final dE de, final String s) {
        if (s.equals("AC_VMAIL_SENT_OK")) {
            this.w.c();
            this.F().a();
            return;
        }
        super.a(de, s);
    }
    
    public final void d(final String s) {
        com.hw.client.util.a.b("processViewArchives");
        try {
            final URL url = new URL(this.t.getCodeBase(), this.l + "&nid=" + s);
            if (this.m == null) {
                this.t.getAppletContext().showDocument(url);
                return;
            }
            this.t.getAppletContext().showDocument(url, this.m);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.d("VDApplet.processViewArchives: bad url: " + ex);
        }
    }
}
