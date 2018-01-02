// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.List;
import java.net.MalformedURLException;
import com.hw.client.util.AbstractApplet;
import java.util.Locale;
import com.hw.client.util.a;
import java.applet.Applet;
import javax.swing.JApplet;
import com.wimba.clients.vboard.e;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.Vector;

public abstract class l extends cz implements ai, au, cf, Runnable
{
    public aD a;
    protected du b;
    protected bE c;
    protected bE d;
    protected bE e;
    protected Vector f;
    public URL g;
    public String h;
    public String i;
    public String j;
    public String k;
    protected String l;
    protected String m;
    protected Vector n;
    private Thread w;
    private Vector z;
    protected String o;
    private String A;
    public String p;
    protected boolean q;
    protected boolean r;
    protected boolean s;
    private ResourceBundle B;
    private e C;
    public JApplet t;
    
    public l(final JApplet t) {
        VT_6_1_0_11.h.a(this.t = t);
        com.hw.client.util.a.a(t, 3);
        com.hw.client.util.a.a(true);
        this.B = ResourceBundle.getBundle("strings", new Locale(ca.a(t, "language", "en").trim()));
        if (!this.c() && t.getDocumentBase().toExternalForm().trim().toLowerCase().startsWith("file")) {
            com.hw.client.util.a.e("VTDesktopPane.onInit: Wrong doc base=" + t.getDocumentBase());
            return;
        }
        if (!AbstractApplet.a()) {
            com.hw.client.util.a.e("VTDesktopPane.onInit: User clicked No in Security Warning. Wimba Applet can not be run");
            this.f(this.e("security_warning"));
            return;
        }
        this.h = ca.a(t, "nid", "").trim();
        if (this.h.length() == 0) {
            this.h = null;
        }
        if (this.h == null) {
            this.i = ca.a(t, "mid", "").trim();
            if (this.i.length() == 0) {
                this.i = null;
            }
            this.j = ca.a(t, "rid", ca.a(t, "board_id", ca.a(t, "room", (String)null)));
            if (this.j != null) {
                this.j = this.j.trim();
            }
        }
        com.hw.client.util.a.d("VTDesktopPane.onInit: rid=" + this.j + ", mid=" + this.i + ", nid=" + this.h);
        try {
            this.g = new URL(t.getParameter("server_url"));
            com.hw.client.util.a.d("Voice Authoring Server URL=" + this.g);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.e("VTDesktopPane.onInit: Invalid parameter server_url=" + t.getParameter("server_url"));
            this.f(this.e("invalid_parameter", "server_url"));
            return;
        }
        t.getDocumentBase().toExternalForm();
        t.getCodeBase().toExternalForm();
        this.k = ca.a(t, "context_path", "/wimba").trim();
        this.l = t.getParameter("view_archives_url");
        this.m = t.getParameter("view_archives_target");
        if (this.m != null && this.m.trim().length() == 0) {
            this.m = null;
        }
        if (this.w == null) {
            (this.w = new Thread(this, "GUI_BUILDER_THREAD")).start();
        }
    }
    
    public void a(final au au, final String s, final String s2) {
        this.F().a(au, this.e("comp_dlg_override_title"), this.e("comp_dlg_override_msg"), this.e("btn_yes"), s, this.e("btn_no"), s2);
    }
    
    public final ResourceBundle a() {
        return this.B;
    }
    
    protected final e b() {
        if (this.C == null) {
            this.C = new e(this);
        }
        return this.C;
    }
    
    public final String a(String s, final String s2) {
        if (this.z == null) {
            this.z = ca.a(this.b("disable_applet_params", "").toLowerCase(), ",; ");
        }
        if ((s = this.b(s, "").trim()).length() == 0 && !this.z.contains(s2)) {
            s = ca.a(this.t, s2, "");
        }
        return s;
    }
    
    protected boolean c() {
        final String parameter;
        return (parameter = this.t.getParameter("_debug")) != null && parameter.equals("_1234_");
    }
    
    public void run() {
        this.d();
    }
    
    protected void d() {
        com.hw.client.util.a.d("VTDesktopPane.initCommunicationChannel");
        this.f(this.e("connecting_server"));
        (this.a = new aD(this.g, "0")).a(this);
        this.a.c();
    }
    
    public final bE l() {
        return this.c;
    }
    
    public final String b(String b, final String s) {
        if ((b = this.d.b(b)) == null || b.trim().length() == 0) {
            return s;
        }
        return b;
    }
    
    public final boolean a(final String s) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.hasRight: " + s + "(" + this.f.contains(s) + ")");
        }
        return this.f.contains(s);
    }
    
    public final du m() {
        return this.b;
    }
    
    public final void a(final String s, final String s2, final String s3) {
        this.a(s, s2, s3, null);
    }
    
    public final void a(final String s, final String s2, final String s3, final String s4) {
        com.hw.client.util.a.d("VTDesktopPane.authenticate");
        if (this.F() != null) {
            this.F().a(null, this.e("main_dlg_auth_title"), this.e("main_dlg_auth_msg"));
        }
        final bE be = new bE();
        if (s4 != null && s4.length() > 0) {
            be.a("nid", s4);
        }
        else {
            final bE be2 = new bE();
            if (s != null) {
                be2.a("login", s);
            }
            if (s2 != null) {
                be2.a("password", s2);
            }
            if (s3 != null) {
                be2.a("screen_name", s3);
            }
            final bE be3 = new bE();
            if (this.j != null) {
                be3.a("rid", this.j);
            }
            be.a("user", be2);
            be.a("resource", be3);
            if (this.i != null) {
                final bE be4;
                (be4 = new bE()).a("mid", this.i);
                be.a("message", be4);
            }
        }
        be.a("doc_base", this.t.getDocumentBase().toExternalForm());
        if (this.c()) {
            be.a("doc_base", "http://");
        }
        be.a("application", this.o);
        this.a(new cm(1, be.a()));
    }
    
    protected final void n() {
        com.hw.client.util.a.d("VTDesktopPane.initAudioManager");
        this.f(this.e("initializing_audio"));
        (this.b = R.a(this.t)).a(this);
        this.b.b();
    }
    
    public void o() {
        try {
            if (this.w != null) {
                this.w = ca.a(this.w);
            }
        }
        catch (RuntimeException ex) {
            com.hw.client.util.a.b("VTDesktopPane.close: ", ex);
        }
        try {
            com.hw.client.util.a.d("VTDesktopPane.close: Closing AudioManager");
            if (this.b != null) {
                this.b.c();
                this.b = null;
            }
        }
        catch (RuntimeException ex2) {
            com.hw.client.util.a.a("VTDesktopPane.close RuntimeException while closing AudioManager", ex2);
        }
        try {
            com.hw.client.util.a.d("VTDesktopPane.close: Closing Channel");
            if (this.a != null) {
                this.v();
                this.a.e();
                this.a = null;
            }
        }
        catch (RuntimeException ex3) {
            com.hw.client.util.a.a("VTDesktopPane.close RuntimeException while closing Channel", ex3);
        }
    }
    
    public final void k() {
        com.hw.client.util.a.d("VTDesktopPane.audioInitSucceeded");
        this.p();
    }
    
    protected abstract void p();
    
    public final void j() {
        com.hw.client.util.a.e("VTDesktopPane.audioInitFailed");
        this.f(this.e("error_audio_init_failed"));
    }
    
    public final void a(final List list) {
    }
    
    public final void a(final int n) {
    }
    
    public final void b(final int n) {
    }
    
    public final void g() {
    }
    
    public final void e() {
    }
    
    public final void a(final long n) {
    }
    
    public final void d(final long n) {
    }
    
    public final void h() {
    }
    
    public final void e(final long n) {
    }
    
    public void f() {
    }
    
    public final void b(final long n) {
    }
    
    public final void f(final long n) {
    }
    
    public final void i() {
    }
    
    public final void c(final long n) {
    }
    
    public final void a(final int n, final String s) {
        com.hw.client.util.a.d("VTDesktopPane.audioDeviceError: type=" + 1 + ", desc=" + s);
        this.F().a(this, this.e("i18n_audio_dev_busy_title"), this.e("i18n_audio_dev_busy_msg"), this.e("btn_ok"), "AC_CLOSE");
    }
    
    public final void q() {
        com.hw.client.util.a.d("VTDesktopPane: Communication with the server is established");
        this.n();
    }
    
    public void r() {
        com.hw.client.util.a.e("VTDesktopPane.channelError");
        if (this.F() != null) {
            this.F().a(this, this.e("main_dlg_channel_error_title"), this.e("main_dlg_channel_error_msg"), this.e("btn_ok"), "AC_CLOSE");
            return;
        }
        this.f(this.e("error_server_connection"));
    }
    
    public final void a(final Object o) {
        final cm cm;
        final int b;
        if ((b = (cm = new cm(o)).b()) == 101) {
            this.a(new bE((Hashtable)cm.a(1)));
            return;
        }
        if (cm.b() == 102) {
            this.a(cm.c(1), cm.d(2));
            return;
        }
        if (cm.b() == 119) {
            this.c((Hashtable)cm.a(1));
            return;
        }
        if (cm.b() == 104) {
            this.b(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 109) {
            this.c(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 110) {
            this.f(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 105) {
            this.d(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 103) {
            this.d(cm.c(1), cm.c(2));
            return;
        }
        if (cm.b() == 107) {
            this.a(cm.b(1), (Vector)cm.a(2));
            return;
        }
        if (cm.b() == 111) {
            this.d((Hashtable)cm.a(1));
            return;
        }
        if (cm.b() == 112) {
            this.d((String)cm.a(1));
            return;
        }
        if (cm.b() == 113) {
            this.e(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 118) {
            this.g(cm.b(1), cm.c(2));
            return;
        }
        if (cm.b() == 120) {
            this.b(cm.d(1));
            return;
        }
        if (cm.b() == 121) {
            this.b((Hashtable)cm.a(1));
            return;
        }
        com.hw.client.util.a.e("Error: VTDesktopPane.processObject: unknown type=" + b);
    }
    
    public void a(final dE de, final String s) {
        if (s.equals("AC_CLOSE")) {
            this.F().a();
        }
    }
    
    public final void b(final String s) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendMessageListRequest");
        }
        this.F().a(null, this.e("main_dlg_list_title"), this.e("main_dlg_list_msg"));
        this.a(new cm(2, s, this.D(), Boolean.toString(this.y())));
    }
    
    public final void s() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendUserListRequest");
        }
        this.F().a(null, this.e("main_dlg_list_title"), this.e("main_dlg_user_list_msg"));
        this.a(new cm(24));
    }
    
    public void t() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.retrieveMessageListByUserRequest");
        }
    }
    
    public final void c(final String s, final String s2) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendMessageListByUserRequest");
        }
        if (!s2.equals("All")) {
            this.s = true;
        }
        else {
            this.s = false;
        }
        this.F().a(null, this.e("main_dlg_list_title"), this.e("main_dlg_list_msg"));
        this.a(new cm(25, s, s2, Boolean.toString(this.y())));
    }
    
    public final void a(final boolean r) {
        this.q = true;
        this.r = r;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendExpandAllRequest, justRefresh=" + r);
        }
        this.F().a(null, this.e("main_dlg_expand_all_title"), this.r ? this.e("main_dlg_refresh_msg") : this.e("main_dlg_expand_all_msg"));
        this.a(new cm(23, this.D(), Boolean.toString(this.y())));
    }
    
    public final void c(final String s) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendMessageTextRequest");
        }
        this.a(new cm(3, s));
    }
    
    public final void a(final B b) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendStartAudio");
        }
        this.a(new cm(4, b.a().a()));
    }
    
    public final void a(final byte[] array) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendAudioChunk");
        }
        this.a(new cm(5, array));
    }
    
    public final void c(final int n) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendStopAudio");
        }
        this.a(new cm(11, new Integer(n)));
    }
    
    public final void u() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendCancelAudio");
        }
        this.a(new cm(6));
    }
    
    public final void v() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendExit");
        }
        this.a(new cm(12));
    }
    
    public final void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendSendVmailRequest");
        }
        this.a(new cm(13, s, s2, s3, s4, s5, s6));
    }
    
    public final void a(final aH ah, final String s, final String s2, final String s3) {
        final String s4 = (ah != null) ? ah.a() : null;
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendPostMessageRequest");
        }
        this.a(new cm(7, s4, s, s2, s3, this.D(), Boolean.toString(this.y())));
        this.s();
        if (this.s) {
            this.a(false);
            this.s = false;
        }
    }
    
    public final void b(final String s, final String s2, final String s3, final String s4) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendEditMessageRequest");
        }
        this.a(new cm(22, s, s2, s3, s4, this.D(), Boolean.toString(this.y())));
        this.s();
        if (this.s) {
            this.a(false);
            this.s = false;
        }
    }
    
    public final void a(final aH ah, final String s, final String s2, final Vector vector) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendSimpleMessageRequest");
        }
        if (ah != null) {
            this.a(new cm(8, ah.a(), s, s2, vector));
        }
    }
    
    public final void a(final Vector vector) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendDeleteMessageRequest messageId=" + vector);
        }
        this.a(new cm(10, vector, this.D(), Boolean.toString(this.y())));
    }
    
    public void w() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendPostRecordingRequest");
        }
        this.a(new cm(14));
    }
    
    public final void a(final Hashtable hashtable) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendVoiceDirectEvent");
        }
        this.a(new cm(15, hashtable));
    }
    
    public final void x() {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendViewArchives");
        }
        this.a(new cm(16));
    }
    
    public final void b(final boolean b) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendSetArchiving");
        }
        this.a(new cm(17, b));
    }
    
    public final void a(final String s, final int n, final String s2) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendReorderMessagesRequest: messageId: " + s + " - position: " + n + " - reference: " + s2);
        }
        this.a(new cm(18, s, new Integer(n), s2, this.D(), Boolean.toString(this.y())));
    }
    
    private void a(final cm cm) {
        this.a.b(cm.a());
    }
    
    public void a(final bE be) {
    }
    
    public void a(final String s, final Vector vector) {
    }
    
    public void b(final Vector vector) {
    }
    
    public void b(final Hashtable hashtable) {
    }
    
    public void d(final String s, final String s2) {
    }
    
    public void b(final int n, final String s) {
    }
    
    public void c(final int n, final String s) {
    }
    
    public void c(final Hashtable hashtable) {
    }
    
    public void d(final int n, final String s) {
    }
    
    public void a(final int n, final Vector vector) {
    }
    
    public void e(final int n, final String s) {
    }
    
    public void f(final int n, final String s) {
    }
    
    public void g(final int n, final String s) {
    }
    
    public void d(final Hashtable hashtable) {
    }
    
    public void d(final String s) {
    }
    
    private String D() {
        if (this.A == null) {
            this.A = ca.a(this.t, "sortby", "messageposition");
        }
        return this.A;
    }
    
    public boolean y() {
        final String trim;
        return (trim = this.b("chrono_order", "").trim()).equalsIgnoreCase("true") || (!trim.equalsIgnoreCase("false") && ca.a(this.t, "display_top_level_messages_in_chrono_order", false));
    }
    
    public final String e(final String s) {
        return this.B.getString(s);
    }
    
    public final String e(final String s, final String s2) {
        final Vector vector;
        (vector = new Vector()).add(s2);
        return new MessageFormat(this.e(s)).format(vector.toArray());
    }
    
    public final String b(final String s, final String s2, final String s3) {
        final Vector vector = new Vector<String>();
        if (s2 != null) {
            vector.add(s2);
        }
        if (s3 != null) {
            vector.add(s3);
        }
        return new MessageFormat(this.e(s)).format(vector.toArray());
    }
    
    public final String c(final String s, final String s2, final String s3, final String s4) {
        final Vector vector;
        (vector = new Vector<String>()).add(s2);
        vector.add(s3);
        vector.add(s4);
        return new MessageFormat(this.e(s)).format(vector.toArray());
    }
    
    public String z() {
        return this.o;
    }
    
    public void A() {
    }
    
    public void B() {
    }
    
    public void C() {
    }
}
