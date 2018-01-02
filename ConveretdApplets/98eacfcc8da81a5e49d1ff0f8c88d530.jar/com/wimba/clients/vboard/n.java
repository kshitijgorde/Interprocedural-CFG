// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Arrays;
import VT_6_1_0_11.dE;
import java.util.Enumeration;
import VT_6_1_0_11.aR;
import javax.swing.tree.TreeNode;
import VT_6_1_0_11.cl;
import VT_6_1_0_11.co;
import javax.swing.ListCellRenderer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import java.text.DateFormat;
import VT_6_1_0_11.B;
import java.text.MessageFormat;
import VT_6_1_0_11.bE;
import VT_6_1_0_11.au;
import javax.swing.tree.TreePath;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observer;
import VT_6_1_0_11.bt;
import java.io.InputStream;
import VT_6_1_0_11.dv;
import VT_6_1_0_11.aH;
import VT_6_1_0_11.aM;
import javax.swing.JComponent;
import com.hw.client.util.a;
import java.applet.Applet;
import VT_6_1_0_11.ca;
import javax.swing.JApplet;
import java.util.Hashtable;
import java.util.Vector;
import VT_6_1_0_11.t;
import VT_6_1_0_11.aN;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeExpansionListener;
import java.awt.event.KeyListener;
import VT_6_1_0_11.l;

public final class n extends l implements KeyListener, TreeExpansionListener, TreeSelectionListener
{
    private aN w;
    private p z;
    private b A;
    private f B;
    private q C;
    private t D;
    private String E;
    private String F;
    private Vector G;
    private boolean H;
    private String I;
    private String J;
    private Hashtable K;
    private boolean L;
    
    public n(final JApplet applet) {
        super(applet);
        this.J = null;
        this.K = new Hashtable();
        this.o = "board";
        if (this.D()) {
            this.setName(this.a().getString("voice.board"));
            return;
        }
        if (this.G()) {
            this.setName(this.a().getString("voice.podcaster"));
            return;
        }
        if (this.H()) {
            this.setName(this.a().getString("voice.presentation"));
        }
    }
    
    public final String z() {
        if (this.F == null) {
            this.F = ca.a(this.t, "w_type", "board").trim();
            if (!this.F.equals("board") && !this.F.equals("presentation") && !this.F.equals("pc")) {
                this.F = "board";
            }
        }
        return this.F;
    }
    
    public final boolean D() {
        return this.z().equals("board");
    }
    
    public final boolean G() {
        return this.z().equals("pc");
    }
    
    public final boolean H() {
        return this.z().equals("presentation");
    }
    
    protected final void p() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.initGUI");
        }
        String trim;
        if ((trim = ca.a(this.t, "login", "").trim()).equalsIgnoreCase("null")) {
            trim = "";
        }
        final String a = ca.a(this.t, "password", "");
        String trim2;
        if ((trim2 = ca.a(this.t, "screen_name", "").trim()).equalsIgnoreCase("null")) {
            trim2 = "";
        }
        this.I = ca.a(this.t, "filter_screen_name", "").trim();
        this.w = new aN(this);
        this.B = new f(this);
        this.A = new b(this, this.b, this.H());
        this.z = new p(this, this.b);
        this.C = new q(this);
        this.a(this.z);
        this.a(this.A, 3);
        this.a(this.B, 4);
        this.a(this.C, 6);
        this.a(this.w, 7);
        this.revalidate();
        new m(this, this.z);
        this.z.addKeyListener(this);
        this.addKeyListener(this);
        this.f(null);
        this.z.setVisible(true);
        if (trim.length() != 0 || trim2.length() != 0 || this.h != null) {
            this.a(trim, a, trim2, this.h);
            return;
        }
        this.w.setVisible(true);
    }
    
    public final boolean I() {
        return this.D() && ca.a(this.T()) && (this.f.contains("board_admin") || (this.a("forward_message") && this.b("show_forward", "true").equals("true")));
    }
    
    public final boolean J() {
        return this.f.contains("board_admin") || (this.a("import_audio") && this.b("import_audio", "true").equals("true"));
    }
    
    public final boolean K() {
        return !this.G() && (this.f.contains("board_admin") || (this.a("reorder_message") && this.b("reorder", "true").equals("true")));
    }
    
    private boolean Y() {
        return this.D() && this.a("publish_message");
    }
    
    public final boolean L() {
        return this.f.contains("board_admin") || (this.a("compose_message") && this.b("show_compose", "true").equals("true"));
    }
    
    public final boolean M() {
        return this.f.contains("board_admin") || (this.a("reply_message") && this.b("show_reply", "true").equals("true"));
    }
    
    public final void a(final aH ah) {
        this.z.o().p();
        this.A.a(ah);
    }
    
    public final void N() {
        this.z.o().p();
        this.A.a().a(this.O().c().a().j(), this.Z());
        this.A.b(this.O().c().a());
    }
    
    public final bt O() {
        return (bt)this.z.e().getSelectionModel();
    }
    
    public final void P() {
        this.z.o().p();
        this.A.d();
    }
    
    public final void b(final aH ah) {
        this.z.o().p();
        this.B.a(ah);
    }
    
    private t Z() {
        final String b = this.O().b();
        if (this.D != null) {
            if (this.E.equals(b)) {
                return this.D;
            }
            this.D.close();
        }
        this.E = b;
        return this.D = new t(this.g(b), false, null, 0, 0);
    }
    
    private URL g(String string) {
        try {
            string = ca.a(this.t) + "/audio?rid=" + this.j + "&mid=" + string;
            return new URL(this.g, string);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("VboardDesktopPane.getPlayUrl: MalformedURLException", ex);
            return null;
        }
    }
    
    public final URL Q() {
        try {
            return new URL(this.g, ca.a(this.t) + "/pc.xml?action=display_rss&rid=" + this.j);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("VboardDesktopPane.getRssUrl: MalformedURLException", ex);
            return null;
        }
    }
    
    public final void c(final aH ah) {
        if (ah.g()) {
            try {
                final String string = new URL(this.g, this.k).toExternalForm() + "/board?action=export&rid=" + this.j + "&mid=" + ah.a() + "&language=" + ca.a(this.t, "language", "en").trim();
                com.hw.client.util.a.d("VboardDesktopPane.exportMessage: ExportUrl= " + string);
                this.t.getAppletContext().showDocument(new URL(string), "_blank");
            }
            catch (MalformedURLException ex) {
                com.hw.client.util.a.b("VboardDesktopPane.publishMessage: ", ex);
            }
        }
    }
    
    public final void R() {
        this.z.o().p();
        if (this.a("delete_message")) {
            if (this.O().c() != null) {
                this.F().a(this, this.e("main_dlg_confirm_del_title"), (this.O().a(null).length == 1) ? ((this.D() || this.G()) ? this.e("main_dlg_confirm_del_msg") : this.e("main_dlg_confirm_del_vp_msg")) : ((this.D() || this.G()) ? this.e("main_dlg_confirm_dels_msg") : this.e("main_dlg_confirm_dels_vp_msg")), this.e("btn_yes"), "AC_OK_DELETE_MESSAGE", this.e("btn_no"), "AC_CANCEL");
            }
        }
        else {
            this.F().a(this, this.e("main_dlg_del_forbidden_title"), this.e("main_dlg_del_forbidden_msg"), this.e("btn_ok"), "AC_CLOSE");
        }
    }
    
    public final void a(final bE c) {
        String b;
        if ((b = c.b("status_code")) == null) {
            b = "ok";
        }
        com.hw.client.util.a.d("VboardDesktopPane.processAuthResponse: status_code=" + b + ", msg=" + c.b("error_message"));
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
            this.F().a(null, this.e("main_dlg_channel_error_title"), MessageFormat.format(this.e("server.error.reload.page"), c.b("error_message")));
            this.a.e();
            return;
        }
        if (b.equals("invalid_parameter")) {
            this.F().a(null, this.e("main_dlg_channel_error_title"), MessageFormat.format(this.e("invalid.parameter"), c.b("error_message")));
            this.a.e();
            return;
        }
        if (b.equals("ok")) {
            this.c = c;
            com.hw.client.util.a.d("VboardDesktopPane.processAuthResponse: Session created");
            this.j = c.a("resource").b("rid");
            this.e = c.a("rights");
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: rights: " + this.e.a());
            }
            this.f = ca.a(this.e.b("add"), ", ");
            final bE a;
            String s;
            if ((a = this.e.a("parameters")) == null || (s = a.b("filtered_names")) == null) {
                s = ca.a(this.t, "filtered_names", "");
            }
            this.n = ca.a(s, ",;");
            this.d = c.a("resource").a("options");
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: " + this.d.a());
            }
            this.p = c.a("resource").b("title");
            this.A.a().a(new B(this.d.a("audio_format")));
            final String trim;
            int n;
            if ((trim = this.b("max_length", "").trim()).length() > 0) {
                n = Integer.parseInt(trim);
            }
            else {
                n = ca.a(this.t, "max_message_length", 180);
            }
            this.A.a().a(n);
            if (this.D()) {
                if (this.L()) {
                    this.z.g().a(this.z.a);
                }
                if (this.M()) {
                    this.z.g().a(this.z.b);
                }
                if (this.a("edit_message")) {
                    this.z.g().a(this.z.l);
                }
                if (this.a("delete_message")) {
                    this.z.g().a(this.z.k);
                }
                if (this.I() && !this.G()) {
                    this.z.g().a(this.z.m);
                }
                if (this.a("import_message")) {
                    this.z.g().a(this.z.n);
                }
                if (this.a("export_message")) {
                    this.z.g().a(this.z.o);
                }
                if (this.Y()) {
                    this.z.g().a(this.z.p);
                }
                this.z.g().a(this.z.j());
            }
            else if (this.H()) {
                if (this.L()) {
                    this.z.g().a(this.z.a);
                }
                if (this.a("edit_message")) {
                    this.z.g().a(this.z.l);
                }
                if (this.M() && !this.G()) {
                    this.z.g().a(this.z.b);
                }
                if (this.a("delete_message")) {
                    this.z.g().a(this.z.k);
                }
                this.z.g().a(this.z.j());
            }
            else if (this.G()) {
                if (this.L()) {
                    this.z.g().a(this.z.a);
                }
                if (this.a("edit_message")) {
                    this.z.g().a(this.z.l);
                }
                if (this.a("delete_message")) {
                    this.z.g().a(this.z.k);
                }
                if (this.a("import_message")) {
                    this.z.g().a(this.z.n);
                }
                if (this.a("export_message")) {
                    this.z.g().a(this.z.o);
                }
                this.z.g().b(this.z.r);
                this.z.g().b(this.z.q);
                this.z.g().b(this.z.s);
                this.z.g().a(this.z.j());
            }
            if (!this.f.contains("board_admin") && !this.b("filter", "true").equals("false") && this.z.r() != null) {
                this.z.invalidate();
                this.z.r().setVisible(false);
                this.z.validate();
            }
            this.f(null);
            this.F().a();
            this.w.setVisible(false);
            this.ac();
            this.requestFocus();
            if (c.b("invalid_license") != null) {
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: invalid license");
                }
                this.F().a(this, this.e("error_license_invalid"), c.b("invalid_license"), this.e("btn_ok"), "AC_INVALID_LICENSE");
            }
            else if (c.b("license_will_expire") != null) {
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: License will expire, but print nothing");
                }
                this.aa();
                this.s();
            }
            else if (c.b("license_is_grace") != null) {
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: grace period");
                }
                this.F().a(this, this.e("error_license_grace_period_title"), this.b("error_license_grace_period_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_grace")))), "" + (15L - (System.currentTimeMillis() - Long.parseLong(c.b("license_is_grace"))) / 86400000L)), this.e("btn_ok"), "AC_INVALID_LICENSE");
            }
            else {
                if (c.b("license_is_locked") == null) {
                    if (com.hw.client.util.a.a()) {
                        com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: valid license... retrieving message list");
                    }
                    this.aa();
                    this.s();
                    return;
                }
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VboardDesktopPane.processAuthenticateResponse: is locked");
                }
                this.F().a(this, this.e("error_license_is_locked_title"), this.e("error_license_is_locked_msg", DateFormat.getDateInstance(3).format(new Date(Long.parseLong(c.b("license_is_locked"))))));
            }
        }
        else {
            com.hw.client.util.a.e("VboardDesktopPane.processAuthResponse: unknown status code=" + b);
        }
    }
    
    private void aa() {
        if (this.a("list_messages")) {
            this.b((String)null);
        }
    }
    
    public final void t() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.retrieveMessageListByUserRequest");
        }
        if (!this.q) {
            this.J = (String)this.z.q().getSelectedItem();
            if (!this.ab()) {
                this.c(this.j, this.J);
                return;
            }
            this.b((String)null);
        }
    }
    
    private boolean ab() {
        return this.J == null || this.J.equals("All");
    }
    
    public final boolean y() {
        return super.y() || !this.ab();
    }
    
    public final void b(final Vector vector) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.processUserListResponse");
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
        if (this.z.q() != null) {
            this.z.q().setModel(model);
            this.z.q().setRenderer(new o(this));
            if (!this.I.equalsIgnoreCase("null")) {
                this.z.q().setSelectedItem(this.I);
            }
        }
    }
    
    public final void b(final Hashtable hashtable) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.processMessageListByUserResponse");
        }
        this.F().a();
        ((co)this.z.e().getModel()).a(null, hashtable.get("root"));
        this.z.e().setSelectionRow(0);
    }
    
    public final void a(final String s, final Vector vector) {
        com.hw.client.util.a.c("Vboard.processMessageListResponse");
        this.F().a();
        ((co)this.z.e().getModel()).a(s, vector);
        this.z.e().requestFocus();
        final cl b;
        if ((b = this.z.e().b()) != null && b.a() != null) {
            for (int n = 1, rowCount = this.z.e().getRowCount(), n2 = 0; n2 < rowCount && n != 0; ++n2) {
                final TreePath pathForRow;
                final String string = (pathForRow = this.z.e().getPathForRow(n2)).toString();
                if (b.a().toString().startsWith(string.substring(0, string.length() - 1))) {
                    if (com.hw.client.util.a.a()) {
                        com.hw.client.util.a.b("VboardDesktopPane.processMessageListResponse - path to expand = " + pathForRow);
                    }
                    this.z.e().expandPath(pathForRow);
                    n = 0;
                }
            }
        }
        if (this.F.equals("presentation")) {
            final String a = ca.a(this.t, "associated_url_target", "presentation_content");
            if (this.z.e().getRowCount() > 0) {
                if (this.O().c() == null) {
                    this.z.e().setSelectionRow(0);
                }
                final String e = this.O().c().a().e();
                try {
                    if (e != null && e.trim().length() > 0 && !e.trim().equalsIgnoreCase("http://") && !e.trim().equalsIgnoreCase("https://")) {
                        this.t.getAppletContext().showDocument(new URL(e), a);
                    }
                }
                catch (MalformedURLException ex) {
                    com.hw.client.util.a.d("VboardDesktopPane.processMessageListResponse: Associated url is not valid: " + e);
                }
                final aH a2 = this.O().c().a();
                this.z.p().a(a2);
                if (a2.h()) {
                    this.c(a2.a());
                }
                return;
            }
            URL url = null;
            try {
                url = new URL(this.g, ca.a(this.t) + "/vpresentation/WVP_start_" + ca.a(this.t, "language", "en").trim() + ".html");
                this.t.getAppletContext().showDocument(url, a);
            }
            catch (MalformedURLException ex2) {
                com.hw.client.util.a.d("VboardDesktopPane.processMessageListResponse: presentation content url is not valid: " + url.getPath());
            }
        }
    }
    
    public final void d(final String s, final String s2) {
        com.hw.client.util.a.c("VboardDesktopPane.processMessageTextResponse: messageId=" + s);
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.processMessageTextResponse: messageId=" + s + ", text=" + s2);
        }
        if (((co)this.z.e().getModel()).b(s) != null) {
            this.z.p().a(s, s2);
            if (this.A.isVisible()) {
                this.F().a();
                this.A.a(s2);
            }
        }
    }
    
    public final void b(final int n, final String s) {
        com.hw.client.util.a.c("Vboard.processPostMessageResponse: sc=" + n + ", msg=" + s);
        if (n == 1) {
            this.F().a();
            this.A.c();
            this.z.e().expandPath(this.z.e().getSelectionPath());
            return;
        }
        this.F().a();
        this.F().a(this, this.e("main_dlg_post_failed_title"), this.e("main_dlg_post_failed_msg"), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void c(final Hashtable hashtable) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.processAllMessagesResponse");
        }
        this.F().a();
        if (this.z.q() != null) {
            this.z.q().setSelectedIndex(0);
        }
        final Vector<Object> vector = hashtable.get("root");
        ((co)this.z.e().getModel()).a(null, vector);
        for (int i = 0; i < vector.size(); ++i) {
            this.a(hashtable, new aH(vector.get(i)));
        }
        this.a(new TreePath(((co)this.z.e().getModel()).getRoot()), true);
        this.q = false;
        this.r = false;
        this.s = false;
    }
    
    private void a(final Hashtable hashtable, final aH ah) {
        final Vector<Object> vector = hashtable.get(ah.a());
        ((co)this.z.e().getModel()).a(ah.a(), vector);
        if (vector != null && vector.size() > 0) {
            for (int i = 0; i < vector.size(); ++i) {
                this.a(hashtable, new aH(vector.get(i)));
            }
        }
    }
    
    public final void A() {
        if (this.z.q() != null) {
            this.z.q().setSelectedIndex(0);
        }
        final TreePath treePath;
        final TreeNode treeNode;
        if ((treeNode = (TreeNode)(treePath = new TreePath(((co)this.z.e().getModel()).getRoot())).getLastPathComponent()).getChildCount() >= 0) {
            final Enumeration children = treeNode.children();
            while (children.hasMoreElements()) {
                this.a(treePath.pathByAddingChild(children.nextElement()), false);
            }
        }
    }
    
    private void a(final TreePath treePath, final boolean b) {
        final aR ar;
        if ((ar = (aR)treePath.getLastPathComponent()).getChildCount() >= 0) {
            final Enumeration children = ar.children();
            while (children.hasMoreElements()) {
                this.a(treePath.pathByAddingChild(children.nextElement()), b);
            }
        }
        if (this.r) {
            final aH a;
            if ((a = ar.a()) == null) {
                return;
            }
            final String s;
            if ((s = this.K.get(a.a())) != null && s.equals("+")) {
                if (b) {
                    this.z.e().expandPath(treePath);
                }
            }
            else if (!b) {
                this.z.e().collapsePath(treePath);
            }
        }
        else {
            if (b) {
                this.z.e().expandPath(treePath);
                return;
            }
            this.z.e().collapsePath(treePath);
        }
    }
    
    public final void g(final int n, final String s) {
        com.hw.client.util.a.c("Vboard.processEditMessageResponse: sc=" + n + ", msg=" + s);
        this.D = null;
        if (n == 1) {
            this.F().a();
            this.A.c();
            return;
        }
        this.F().a();
        this.F().a(this, this.e("main_dlg_post_failed_title"), this.e("main_dlg_post_failed_msg"), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void d(final int n, final String s) {
        com.hw.client.util.a.c("Vboard.processForwardMessageResponse: sc=" + n + ", msg=" + s);
        if (n == 1) {
            this.F().a();
            this.B.a();
            return;
        }
        this.F().a();
        this.F().a(this, this.e("main_dlg_fwd_failed_title"), this.e("main_dlg_fwd_failed_msg"), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void a(int i, final Vector vector) {
        com.hw.client.util.a.c("VboardDesktopPane.processDeleteMessageResponse: sc=" + i);
        if (i == 1) {
            String s;
            for (i = 0; i < vector.size(); ++i) {
                s = vector.elementAt(i);
                if (com.hw.client.util.a.a()) {
                    com.hw.client.util.a.b("VboardDesktopPane.processDeleteMessageResponse, removing " + s);
                }
                ((co)this.z.e().getModel()).a(s);
            }
            this.F().a();
            com.hw.client.util.a.b(" VboardDesktopPane.processDeleteMessageResponse: deleting the metadata in the message Panel and the textarea");
            this.z.p().a((aH)null);
            this.z.p().a(null, "");
            this.z.o().a(null, (InputStream)null);
            return;
        }
        this.F().a();
        this.F().a(this, this.e("main_dlg_delete_failed_title"), this.e("main_dlg_delete_failed_msg"), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void e(final int n, final String s) {
        com.hw.client.util.a.c("VboardDesktopPane.processReorderMessagesResponse: sc=" + n + ", msg=" + s);
        if (n == 1) {
            if (com.hw.client.util.a.a()) {
                com.hw.client.util.a.b("VboardDesktopPane.processReorderMessagesResponse: succeed");
            }
            this.F().a();
            return;
        }
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.processReorderMessagesResponse: failed : " + s);
        }
        this.F().a();
        this.F().a(this, this.e("main_dlg_reorder_failed_title"), this.e("main_dlg_reorder_failed_msg", this.e(s)), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final Vector S() {
        return this.n;
    }
    
    public final void a(final dE de, final String s) {
        if (s.equals("AC_OK_DELETE_MESSAGE")) {
            this.F().a(null, this.e("main_dlg_delete_title"), this.e("main_dlg_delete_msg"));
            this.a(new Vector((Collection<? extends E>)Arrays.asList(this.O().a())));
            return;
        }
        if (s.equals("AC_OK_CANCEL_COMPOSE")) {
            this.F().a();
            if (this.A.isVisible()) {
                this.A.c();
                return;
            }
            if (this.B.isVisible()) {
                this.B.a();
                return;
            }
            if (this.B.isVisible()) {
                this.B.a();
            }
        }
        else {
            if (s.equals("AC_CANCEL_CANCEL_COMPOSE")) {
                this.F().a();
                return;
            }
            if (s.equals("AC_CANCEL")) {
                this.F().a();
                return;
            }
            if (s.equals("AC_INVALID_LICENSE")) {
                this.aa();
                return;
            }
            super.a(de, s);
        }
    }
    
    public final String T() {
        final String b;
        if ((b = this.l().a("user").b("email")) == null || b.equals("")) {
            com.hw.client.util.a.d("VboardDesktopPane.getEmail: No email address configured.");
        }
        return b;
    }
    
    public final String U() {
        final String b;
        if ((b = this.l().a("user").b("screen_name")) == null || b.equals("")) {
            com.hw.client.util.a.d("VboardDesktopPane.getScreenName: No screen name is configured.");
        }
        return b;
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        System.out.println("VboardDesktopPane.keyPressed: getKeyChar: " + keyEvent.getKeyChar());
        System.out.println("VboardDesktopPane.keyPressed: getKeyCode: " + keyEvent.getKeyCode());
        System.out.println("VboardDesktopPane.keyPressed: getKeyLocation: " + keyEvent.getKeyLocation());
        if (keyEvent.getKeyCode() == 116) {
            this.a(true);
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void C() {
        final com.wimba.clients.vboard.a a;
        (a = new com.wimba.clients.vboard.a(this, this.O().getSelectionCount() > 0)).b();
        if (a.a()) {
            this.b().a(a.c(), a.d());
        }
    }
    
    public final void V() {
        try {
            this.t.getAppletContext().showDocument(new URL(this.g, ca.a(this.t) + "/pc.xml?action=pcast&rid=" + this.j), "_blank");
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("VboardDesktopPane.showPCastDialog: MalformedURLException", ex);
        }
    }
    
    public final void B() {
        final r r;
        (r = new r(this)).d();
        if (this.z.q() != null) {
            this.z.q().setSelectedIndex(0);
        }
        if (r.b()) {
            boolean state = false;
            if (this.D()) {
                state = r.a().getState();
            }
            this.C.a(r.c(), state);
        }
    }
    
    public final void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.valueChanged(TreeSelectionEvent)");
        }
        this.z.o().p();
        this.ac();
    }
    
    private void ac() {
        final boolean h = this.H;
        this.H = false;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.updateButtons: current Thread: " + Thread.currentThread().getName());
        }
        final int selectionCount;
        final boolean b = (selectionCount = this.O().getSelectionCount()) == 1;
        final boolean b2 = selectionCount > 0;
        final boolean b3 = b && this.O().c().a().g();
        this.z.o().e(b3);
        this.z.o().g(b3);
        if (b3) {
            this.z.o().a(this.O().c().a().j(), this.Z());
            this.z.o().r().c(this.j);
            this.z.o().r().a(this.O().b());
        }
        this.z.p.setEnabled(b && this.Y() && b3);
        this.z.m.setEnabled(b && this.I());
        JButton button = null;
        boolean enabled = false;
        Label_0293: {
            if (this.G()) {
                button = this.z.b;
            }
            else {
                button = this.z.b;
                if (b && this.M()) {
                    enabled = true;
                    break Label_0293;
                }
            }
            enabled = false;
        }
        button.setEnabled(enabled);
        this.z.l.setEnabled(b && this.a("edit_message"));
        this.z.k.setEnabled(b2 && this.a("delete_message"));
        this.z.o.setEnabled(this.a("export_message"));
        if (b && this.F.equals("presentation") && this.O().getSelectionPath().getPathCount() == 2) {
            final String a = ca.a(this.t, "associated_url_target", "presentation_content");
            final String e = this.O().c().a().e();
            try {
                if (e != null && e.trim().length() > 0 && !e.trim().equalsIgnoreCase("http://") && !e.trim().equalsIgnoreCase("https://")) {
                    this.t.getAppletContext().showDocument(new URL(e), a);
                }
            }
            catch (MalformedURLException ex) {
                com.hw.client.util.a.d("VboardDesktopPane.clickedOnItem: Associated url is not valid: " + e);
            }
        }
        if (b) {
            final aH a2 = this.O().c().a();
            this.z.p().a(a2);
            if (a2.h()) {
                this.c(a2.a());
            }
        }
        if (b3) {
            if (this.z.c() || (this.z.d() && h)) {
                com.hw.client.util.a.b("VboardDesktopPane.updateButtons: Auto start play for the selected message");
                this.z.o().a(0.0);
            }
        }
        else if (this.z.d() && h) {
            com.hw.client.util.a.b("VboardDesktopPane.updateButtons: No audio but as cont. play is enabled, selecting next message for playback");
            SwingUtilities.invokeLater(new c(this));
        }
    }
    
    public final void treeExpanded(final TreeExpansionEvent treeExpansionEvent) {
        final aR ar = (aR)treeExpansionEvent.getPath().getLastPathComponent();
        this.K.put(ar.a().a(), "+");
        if (this.J != null && !this.J.equals("All")) {
            return;
        }
        if (!this.q) {
            this.b(ar.a().a());
        }
    }
    
    public final void treeCollapsed(final TreeExpansionEvent treeExpansionEvent) {
        this.K.put(((aR)treeExpansionEvent.getPath().getLastPathComponent()).a().a(), "-");
    }
    
    public final Vector W() {
        if (this.G == null) {
            String s;
            if (this.b("short_title", "").trim().equalsIgnoreCase("true")) {
                s = "author";
            }
            else {
                final String trim;
                if ((trim = this.b("message_headings", "").trim()).length() != 0) {
                    s = trim.trim();
                }
                else {
                    s = ca.a(this.t, "message_headings", "default");
                }
            }
            this.G = ca.a(s, ",; ");
        }
        return this.G;
    }
    
    public final boolean X() {
        return this.L;
    }
    
    public final void c(final boolean l) {
        this.L = l;
    }
    
    public final void f() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardDesktopPane.playAutoStopped");
        }
        if (this.z.d()) {
            SwingUtilities.invokeLater(new c(this));
        }
    }
    
    static p a(final n n) {
        return n.z;
    }
    
    static boolean a(final n n, final boolean h) {
        return n.H = h;
    }
}
