import java.awt.Color;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.awt.Component;
import java.awt.MediaTracker;
import ABLwidgets.new_font;
import ABLwidgets.font_metrics;
import java.awt.Frame;
import ABLwidgets.web_context;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljem extends Applet implements Runnable
{
    abljema a;
    private String b;
    boolean c;
    public String d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    String[] i;
    String j;
    String k;
    String l;
    String m;
    private Object n;
    private String o;
    private String p;
    private String q;
    private String r;
    private abljema s;
    private String t;
    private String u;
    private String v;
    
    public abljem() {
        this.c = false;
        this.e = false;
        this.f = false;
        this.g = true;
        this.h = false;
        this.l = "";
        this.m = "";
        this.n = new Object();
        this.q = "RCDPBKINI";
        this.r = "CONNECT";
    }
    
    public void init() {
        this.a();
    }
    
    private void a() {
        final String s = "HTTPS";
        this.l = this.a("runid", this.l);
        if (this.l.length() > 0) {
            this.m = String.valueOf(this.l) + ABLwidgets.abljem.a;
        }
        this.k = abljembn.a;
        this.b("\n" + this.k + "  (" + abljemza.fileType() + ")");
        this.a = new abljema(this, this.k, this.m);
        this.a.f = (this.c ? new web_context(this.d) : new web_context(this));
        abljemeh.a(this);
        this.a.z = this.a("key_to_canvas", false);
        this.a.ee = this.a("product_title", "BusinessLink for JAVA");
        if (this.e(this.a.b(this.a("user_agent", (String)null)))) {
            return;
        }
        String au = this.a("window_title", "BusinessLink for Java  -  AS/400 session");
        if (au.equalsIgnoreCase("*BLANK")) {
            au = "";
        }
        this.a.au = au;
        this.a.ai = this.a("title_session_prefix", this.a.ai);
        final String a = this.a("adjust_font_metrics", (String)null);
        if (a != null && a.length() > 0) {
            this.a.fd = ((this.a.fe == '\0') ? a.charAt(0) : this.a.fe);
        }
        font_metrics.a(new Frame());
        if (this.a.ce) {
            this.a(this.a);
        }
        this.a.da = this.a("Allow_Auto_login", this.a.da);
        if (this.a("setuser0987654321", false)) {
            this.a.ay = "987654321";
            this.b("user09 initially set to " + this.a.ay);
        }
        final String a2 = this.a("font_file", (String)null);
        if (a2 != null) {
            new_font.a(this.a.f.c(), a2, 0);
        }
        final String a3 = this.a("URL_Protocol", (String)null);
        if (a3 != null) {
            if (!a3.equalsIgnoreCase(s)) {
                this.e("Invalid URL_Protocol parameter value " + a3);
                return;
            }
            this.e = true;
        }
        final String a4 = this.a("connection_protocol", (String)null);
        if (a4 != null) {
            if (a4.equalsIgnoreCase("HTTPS_RC4")) {
                this.a.gn = true;
                this.e = true;
            }
            else {
                if (!a4.equalsIgnoreCase("UNSECURED_AUTO_LOGIN")) {
                    this.e("Invalid Connection_Protocol parameter value " + a4);
                    return;
                }
                this.a.gn = true;
            }
        }
        if (this.a.f.c().getProtocol().equalsIgnoreCase(s) && this.a.da) {
            this.a.go = true;
            this.a.gn = true;
            this.e = true;
        }
        if (this.c) {
            if (this.e) {
                this.e("Must load as applet using https for secure connection");
                return;
            }
        }
        else {
            if (this.a.f.b().getProtocol().equalsIgnoreCase(s)) {
                this.a.gm = true;
            }
            if (this.e && !this.a.f.c().getProtocol().equalsIgnoreCase(s)) {
                this.e("https: must be used for secure connection");
                return;
            }
        }
        if (this.a.gn) {
            String a5 = this.a("applet_link", (String)null);
            if (a5 != null && a5.length() < 50) {
                a5 = null;
            }
            if (this.e(this.b(a5, true))) {
                return;
            }
        }
        this.a.io = this.a("HTTP_Tunnel_Handler", this.a.io);
        this.a.ik = this.a("Tunnel_URL_vary", this.a.ik);
        final String a6 = this.a("HTTP_tunnel", (String)null);
        this.a.gq = this.a("tunnel_ssl", this.a.gq);
        final String a7 = this.a("connection_port", (String)null);
        final String a8 = this.a("connection_host", (String)null);
        final String s2 = "allow_132_column";
        if (this.a(s2, (String)null) != null) {
            this.a.bp = true;
            this.a.bo = this.a(s2, false);
            this.a.bq = this.a.bo;
        }
        this.a.eb = this.a("Use_Enhanced_Datastream", this.a.eb);
        this.a.ea = this.a("USEDALIND", this.a.ea);
        if (this.e(this.f(this.q))) {
            return;
        }
        if (this.a.d0) {
            this.a.az = true;
            this.b("Link to session server not used, playing back recorded screens");
        }
        else {
            this.a(this.a, a8, a7, a6);
            if (!this.a.az) {
                this.e("Unable to make TCP/IP connection");
                return;
            }
        }
        this.g = false;
        final String a9 = this.a("option_button_image", (String)null);
        String s3 = this.a("login_image", (String)null);
        if (s3 == null) {
            s3 = this.a("logon_image", (String)null);
        }
        final String a10 = this.a("filelist_image", (String)null);
        final String a11 = this.a("file_cabinet_image", (String)null);
        final String a12;
        if ((a12 = this.a("connection_security", (String)null)) != null) {
            if (a12.equalsIgnoreCase("Basic")) {
                this.a.c1 = this.a.c3;
            }
            else if (a12.equalsIgnoreCase("None")) {
                this.a.c1 = this.a.c3;
                this.a.c0 = true;
            }
            else if (a12.equalsIgnoreCase("Name")) {
                this.a.c1 = this.a.c4;
            }
            else if (a12.equalsIgnoreCase("Pass")) {
                this.a.c1 = this.a.c5;
            }
            else if (a12.equalsIgnoreCase("Secure")) {
                this.a.c1 = this.a.c6;
            }
            else {
                if (!a12.equalsIgnoreCase("Secure_Authenticated")) {
                    this.e("Invalid Connection_Security parameter value " + a12);
                    return;
                }
                this.a.c1 = this.a.c7;
            }
        }
        this.a.r = this.a("alt_digit_map_type", this.a.r);
        this.a.t = this.a("dot_comma_map_type", this.a.t);
        final String a13;
        if ((a13 = this.a("alt_digit_action", (String)null)) != null) {
            if (a13.equalsIgnoreCase("PAx")) {
                this.a.s = 'P';
            }
            else if (a13.equalsIgnoreCase("session")) {
                this.a.s = 'S';
            }
            else {
                this.b("Invalid Alt_Digit_Action parameter value " + a13);
            }
        }
        final String a14;
        if ((a14 = this.a("enter_key_action", (String)null)) != null) {
            if (a14.equalsIgnoreCase("Enter")) {
                this.a.a4 = false;
                this.a.a3 = true;
            }
            else if (a14.equalsIgnoreCase("field_exit")) {
                this.a.a4 = true;
                this.a.a3 = true;
            }
            else if (a14.equalsIgnoreCase("fieldexit")) {
                this.a.a4 = true;
                this.a.a3 = true;
            }
            else {
                this.b("Invalid Enter_key_Action parameter value " + a14);
            }
        }
        final String a15;
        if ((a15 = this.a("overpunch_numeric_fields", (String)null)) != null) {
            if (a15.equalsIgnoreCase("Always")) {
                this.a.a6 = 'A';
            }
            else if (a15.equalsIgnoreCase("Never")) {
                this.a.a6 = 'N';
            }
            else {
                this.b("Invalid Overpunch_Numeric_Fields parameter value " + a15);
            }
        }
        if (!(this.a.h2 = this.a("Use_styler", this.a.h2))) {
            this.b("Styler not used");
        }
        this.a.br = this.a("Show_Sizing_Rectangles", this.a.br);
        this.a.m = this.a("Enable_Relay", this.a.m);
        if (this.a.m) {
            if (this.k.charAt(this.k.length() - 1) == 'R') {
                if (abljemza.authorized("UniversalListen") == null) {
                    this.a.m = false;
                    this.b("Relay not supported by unsigned applet");
                }
                else {
                    this.b("Relay enabled");
                }
            }
            else {
                this.a.m = false;
                this.b("Relay not supported by this applet version");
            }
        }
        this.a.n = this.a("Enable_Relay_Logging", this.a.n);
        this.a.he = !this.a("TextMode_Buttons", !this.a.he);
        final String a16;
        if ((a16 = this.a("Display_Mode", (String)null)) != null) {
            if (a16.equalsIgnoreCase("GUI")) {
                this.a.bt = false;
                this.a.bs = true;
            }
            if (a16.equalsIgnoreCase("Text")) {
                this.a.bt = true;
                this.a.bs = true;
            }
        }
        if (this.j != null && this.j.length() > 0) {
            switch (this.j.charAt(0)) {
                case 'T': {
                    this.a.he = true;
                    this.a.bt = true;
                    this.a.bs = true;
                    break;
                }
                case 't': {
                    this.a.he = false;
                    this.a.bt = true;
                    this.a.bs = true;
                    break;
                }
                case 'g': {
                    this.a.he = false;
                    this.a.bt = false;
                    this.a.bs = true;
                    break;
                }
                case 'G': {
                    this.a.he = true;
                    this.a.bt = false;
                    this.a.bs = true;
                    break;
                }
            }
        }
        this.a.a5 = this.a("Plus_Field_Exit", this.a.a5);
        final String s4 = "Delete_Without_Retrieve";
        final String a17;
        if ((a17 = this.a(s4, (String)null)) != null) {
            this.a.hj = true;
            this.a.hf = this.a(s4, this.a.hf);
        }
        final String s5 = "AutoRetrieve_URLs";
        final String a18;
        if ((a18 = this.a(s5, (String)null)) != null) {
            this.a.hk = true;
            if (a18.equalsIgnoreCase("Binary")) {
                this.a.hm = true;
            }
            else {
                this.a.hg = this.a(s5, this.a.hg);
                this.a.hm = this.a.hg;
            }
        }
        final String a19;
        if ((a19 = this.a("AutoDelete_URLs_Keeping", (String)null)) != null) {
            this.a.hl = true;
            this.a.hh = abljema.d(a19);
            if (this.a.hh < 0) {
                this.a.hh = 0;
            }
        }
        this.a.hi = this.a("AutoDelete_Delay", this.a.hi);
        this.a.ho = this.a("AutoDelete_Binary_Prints", this.a.ho);
        this.a.hn = this.a("Initial_Binary_Prints", this.a.hn);
        if (this.a.hn && !this.a.ho) {
            this.a.hn = false;
            this.b("Initial_Binary_Prints Yes ignored because AutoDelete_Binary_Prints not set to Yes");
        }
        this.a.bl = this.a("Allow_prtbin_Dialog_Cancel", this.a.bl);
        this.a.hs = this.a("Hide_Available_Files_List", this.a.hs);
        this.a.ht = this.a("Hide_Initial_File_List", this.a.ht);
        this.a.hu = this.a("Hide_Auto_login", this.a.hu);
        this.a.hv = this.a("Lock_GuiStyle_Window_Size", this.a.hv);
        this.a.a9 = this.a("Drop_NDW_Fields", this.a.a9);
        final String s6 = "Allow_Window_Close";
        final String a20;
        if ((a20 = this.a(s6, (String)null)) != null) {
            if (a20.equalsIgnoreCase("atSignon")) {
                this.a.a7 = 'S';
            }
            else {
                this.a.a7 = (char)(this.a(s6, this.a.a7 == 'Y') ? 89 : 78);
            }
        }
        this.a.a8 = this.a("close_denial_popup_text", (String)null);
        this.a.ib = this.a("stylesheet_user", this.a.ib);
        this.a.ib = this.a("guistyle_configuration", this.a.ib);
        this.a.ic = this.a("guistyle_path", this.a.ic);
        this.a.id = this.a("guistyle_path_chain", this.a.id);
        this.a.c = this.a("guistyle_load_timeout", this.a.c);
        this.a.b = this.a("tunnel_receive_timeout", this.a.b);
        this.a.a = this.a("key_receive_timeout", this.a.a);
        try {
            if (s3 != null) {
                this.a.cs = this.a.f.a(this.a.f.c(), s3);
            }
            if (a10 != null) {
                this.a.ct = this.a.f.a(this.a.f.c(), a10);
            }
            if (a9 != null) {
                this.a.cr = this.a.f.a(this.a.f.c(), a9);
            }
            if (a11 != null) {
                this.a.cu = this.a.f.a(this.a.f.c(), a11);
            }
        }
        catch (Throwable t) {
            d("Image load start failed: " + t.toString());
        }
        this.a.ev = false;
        this.a.ew = false;
        if (!this.a.ce) {
            this.a(this.a);
        }
        if (this.a.aq != null) {
            au = au.concat("  (").concat(this.a.aq).concat(")");
        }
        this.a.av = true;
        if (a2 != null) {
            this.a.x = new_font.a(new_font.b(a2), 0, 1).getName();
        }
        final abljemf fb = new abljemf(au, this.a);
        (this.a.fb = fb).a(this);
        this.a.fb.b(true);
        if (this.c && this.i != null && this.i.length > 4) {
            fb.a(this.i[1], this.i[2], this.i[3], this.i[4]);
        }
        this.a.cv = new MediaTracker(fb);
        if (this.a.cs != null) {
            this.a.cv.addImage(this.a.cs, this.a.cx);
        }
        if (this.a.cr != null) {
            this.a.cv.addImage(this.a.cr, this.a.cw);
        }
        if (this.a.ct != null) {
            this.a.cv.addImage(this.a.ct, this.a.cy);
        }
        if (this.a.cu != null) {
            this.a.cv.addImage(this.a.cu, this.a.cz);
        }
        this.a.cv.checkAll(true);
        if (this.a.ct == null && this.a.cs != null) {
            this.a.ct = this.a.cs;
            this.a.cy = this.a.cx;
        }
        if (this.a.cu == null && this.a.ct != null) {
            this.a.cu = this.a.ct;
            this.a.cz = this.a.cy;
        }
        this.a.dx.append(abljema.b("XAADISPLAYWINDOW", 20));
        if (!this.a.bs) {
            this.a.dx.append(abljema.b("XAADISPLAYMODE", 20));
        }
        if (!this.a.hj || !this.a.hk || !this.a.hl) {
            this.a.dx.append(abljema.b("XAARETRIEVE", 20));
        }
        if (!this.a.a3) {
            this.a.dx.append(abljema.b("XAAENTERKEYACTION", 20));
        }
        this.a.dx.append(abljema.b("XAM*", 20));
        this.a.de = true;
        final Thread fc = new Thread(fb, String.valueOf(this.m) + "abljemf");
        this.a.fc = fc;
        this.a.bn = new abljemr(this.a);
        this.a.bm = new abljems(this.a);
        if (!this.a.d0 && (this.a.c1 > this.a.c3 || this.a.c8) && (!this.a.c8 || !this.a.hu)) {
            this.a.g8.d();
        }
        this.a(fb, fc);
    }
    
    public void start() {
        this.f = true;
    }
    
    private void a(final abljemf abljemf, final Thread thread) {
        if (this.a.h) {
            return;
        }
        this.a.h = true;
        if (!this.a.az) {
            return;
        }
        this.a.start();
        this.a.h = true;
        this.b = null;
        try {
            this.a.cv.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.a.c1 <= this.a.j.c2 && !this.a.j.av) {
            abljemf.show();
        }
        if (this.a.c1 == this.a.j.c3) {
            this.a.db = true;
        }
        thread.start();
        this.a.bn.start();
        this.a.bm.start();
        if (this.a.d0) {
            final String c = this.a.bn.c();
            if (c != null) {
                this.e(c);
            }
        }
    }
    
    public void stop() {
        this.f = false;
    }
    
    public String reloadGuiStyle() {
        return this.a.c();
    }
    
    public String setGuiStylePath(final String ic) {
        this.a.ic = ic;
        return null;
    }
    
    public String setGuiStyleConfiguration(final String ib) {
        this.a.ib = ib;
        return null;
    }
    
    public String status() {
        boolean az = false;
        try {
            if (this.a != null) {
                az = this.a.az;
            }
        }
        catch (Throwable t) {}
        if (this.g && !az) {
            return "10 Starting up";
        }
        if (az) {
            if (!this.a.a0) {
                return "20 Logging on";
            }
            return "30 Logged on";
        }
        else {
            if (this.a == null) {
                return "00 Ended";
            }
            return "01 Ending";
        }
    }
    
    public String injectKeystrokes(final String s) {
        return this.a.a(s);
    }
    
    private boolean e(final String a2) {
        if (a2 == null || a2.length() == 0) {
            return false;
        }
        this.b(this.a.a2 = a2);
        if (!this.h) {
            this.b("End guistyle (startup failed)");
        }
        final abljemem abljemem = new abljemem(this.a.ee, this.a.a2);
        return true;
    }
    
    private String b(final String s, final boolean b) {
        String s2 = this.c(s, b);
        if (s2 != "Session key retrieval failed") {
            return s2;
        }
        final int a = this.a("session_key_attempts", 1);
        final int a2 = this.a("session_key_interval", 1);
        int n;
        for (n = 1; n < a && s2 == "Session key retrieval failed"; s2 = this.c(s, b), ++n) {
            d("Session key retrieval failed attempt " + n + ", will retry in " + a2 + " sec");
            abljema.a((long)(a2 * 1000));
        }
        if (s2 == null) {
            d("Session key retrieval succeeded on attempt " + n);
        }
        return s2;
    }
    
    private String c(final String s, final boolean b) {
        try {
            final URL c = this.a.f.c();
            String s2;
            if (s != null) {
                s2 = "/*JEMSEC2/".concat(s);
            }
            else if (b) {
                s2 = "*JEMSEC1".concat(String.valueOf(new Date().getTime()));
            }
            else {
                s2 = "/*JEMSEC1".concat(String.valueOf(new Date().getTime()));
            }
            final URL url = new URL(c, s2);
            final byte[] a = this.a.a(this.a.a(url));
            if (a == null) {
                return "Session key retrieval failed";
            }
            return this.a(this.a, a, url.getProtocol().equalsIgnoreCase("HTTPS"));
        }
        catch (MalformedURLException ex) {
            this.b(ex.toString());
            return "Malformed URL";
        }
    }
    
    private String a(final abljema abljema, final byte[] array, final boolean b) {
        final int length = array.length;
        final String s = new String(array, 0);
        if (length < 3) {
            return new String(array, 0, 0, length);
        }
        final String s2 = new String(array, 0, 0, 2);
        final String s3 = new String(array, 0, 0, 3);
        int c;
        int n;
        int gz;
        int n2;
        if (s2.equals("OK")) {
            if (length < 13) {
                return "Key reply OK but short";
            }
            c = 1;
            n = 2;
            gz = 5;
            n2 = 3;
        }
        else {
            if (s3.equals("ERR") && length > 3) {
                return s.substring(3);
            }
            if (!s3.equals("SK1")) {
                return new String("Key reply invalid: ").concat(s);
            }
            final int n3 = 3;
            if (length < n3 + 9) {
                return "Key reply error 1";
            }
            abljema.gv = new String(array, 0, n3, 9);
            if (array[n3] > 32) {
                abljema.c8 = true;
            }
            else if (!b) {
                abljema.gn = false;
                return null;
            }
            final int n4 = n3 + 9;
            if (length < n4 + 5) {
                return "Key reply error 2";
            }
            final int c2 = abljema.c(array, n4, 4);
            final int n5 = n4 + 5;
            if (c2 > 0) {
                abljema.gw = new String(array, 0, n5, c2);
            }
            final int n6 = n5 + c2;
            if (length < n6 + 5) {
                return "Key reply error 3";
            }
            c = abljema.c(array, n6, 4);
            final int n7 = (n = n6 + 5) + c;
            if (length < n7 + 5) {
                return "Key reply error 4";
            }
            final int c3 = abljema.c(array, n7, 4);
            final int n8 = (n2 = n7 + 5) + c3;
            if (length < n8) {
                return "Key reply error 5";
            }
            abljema.d(array, n2, c3);
            gz = c3 / 4;
            if (length > n8 + 5) {
                final int c4 = abljema.c(array, n8, 4);
                final int n10;
                int n9 = (n10 = n8 + 5) + c4;
                if (length < n9) {
                    return "Key reply error 6";
                }
                this.b = new String(array, 0, n10, c4);
                if (length > n9) {
                    ++n9;
                }
            }
        }
        abljema.gx = new byte[c];
        for (int i = 0, n11 = n; i < c; ++i, ++n11) {
            abljema.gx[i] = array[n11];
        }
        abljema.gz = gz;
        abljema.gy = new byte[2 * abljema.gz];
        for (int j = 0, n12 = n2; j < abljema.gy.length; ++j, ++n12) {
            abljema.gy[j] = array[n12];
            array[n12] = 0;
        }
        abljema.dc = true;
        return null;
    }
    
    private void a(final abljema s, final String t, final String u, final String v) {
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.f(this.r);
        this.s = null;
    }
    
    private boolean b(final abljema abljema, String host, final String s, final String s2) {
        int d = 43856;
        if (s != null) {
            if (abljema.d(s) > 0) {
                d = abljema.d(s);
            }
            if (s.equalsIgnoreCase("HTTP")) {
                d = 80;
            }
        }
        if (this.c && host == null) {
            host = abljema.f.c().getHost();
        }
        abljema.az = false;
        if (s2 != null && s2.equalsIgnoreCase("always")) {
            abljema.az = abljema.c(true);
        }
        else {
            if (s != null) {
                this.b("Using port: " + s);
            }
            try {
                if (host != null) {
                    this.b("Connecting to: " + host);
                    abljema.ar = new Socket(host, d, true);
                    if (abljema.ar != null) {
                        this.b("TCP connected");
                    }
                }
                else {
                    abljema.aq = abljema.f.b().getHost();
                    abljema.ar = new Socket(InetAddress.getByName(abljema.aq), d, true);
                }
                if (abljema.ar != null) {
                    abljema.as = new DataInputStream(new BufferedInputStream(abljema.ar.getInputStream()));
                    abljema.at = new DataOutputStream(new BufferedOutputStream(abljema.ar.getOutputStream()));
                    if (abljema.as != null) {
                        if (abljema.at != null) {
                            if (s != null && s.equalsIgnoreCase("HTTP")) {
                                abljema.at.writeBytes("JEM1\n");
                                abljema.at.flush();
                            }
                            abljema.az = true;
                        }
                    }
                }
            }
            catch (Exception ex) {
                this.b("Socket connection failed: " + ex.toString());
            }
        }
        if (!abljema.az && s2 != null && s2.equalsIgnoreCase("fallback")) {
            this.b("Attempting tunneled connection");
            abljema.az = abljema.c(false);
        }
        if (abljema.ie) {
            this.b("HTTP" + (abljema.gp ? "S" : "") + " Tunneling started");
        }
        if (abljema.az && !abljema.ie) {
            abljema.hp = true;
            abljema.a2 = "No server response (TCP/IP ok)";
        }
        return abljema.az;
    }
    
    private void a(final abljema abljema) {
        abljema.g8 = new abljemnp(abljema, abljema.ee, this.a("login_user_prompt", "Name"), this.a("login_passphrase_prompt", "Passphrase"));
        abljemem.f = abljema.g8.getBackground();
    }
    
    public String a(final String s, final String s2) {
        String s3 = this.c ? null : this.getParameter(s);
        if (s3 == null) {
            return s2;
        }
        int length = s3.length();
        if (length > 0) {
            if (s3.charAt(0) == '\"') {
                s3 = s3.substring(1);
                --length;
            }
            if (length > 0 && s3.charAt(length - 1) == '\"') {
                s3 = s3.substring(0, length - 1);
                --length;
            }
        }
        if (length == 0) {
            return s2;
        }
        return s3;
    }
    
    public char a(final String s, final char c) {
        final String a = this.a(s, (String)null);
        if (a == null || a.length() == 0) {
            return c;
        }
        return a.charAt(0);
    }
    
    public Color a(final String s, final Color color) {
        final String s2 = this.c ? null : this.getParameter(s);
        if (s2 != null) {
            return abljema.g(s2);
        }
        return color;
    }
    
    public Color[] a(final String s, final Color[] array) {
        final String s2 = this.c ? null : this.getParameter(s);
        if (s2 == null) {
            return array;
        }
        final int index = s2.indexOf(59);
        if (index < 1 || index > s2.length() - 2) {
            return array;
        }
        return new Color[] { abljema.g(s2.substring(0, index)), abljema.g(s2.substring(index + 1)) };
    }
    
    public boolean a(final String s, final boolean b) {
        return this.a(this.c ? null : this.getParameter(s), b, String.valueOf(s) + " parameter");
    }
    
    public boolean a(final String s, final boolean b, final String s2) {
        if (s != null && s.length() > 0) {
            final String upperCase = s.toUpperCase();
            if (upperCase.equals("Y") || upperCase.equals("YES") || upperCase.equals("1") || upperCase.equals("ON") || upperCase.equals("TRUE")) {
                return true;
            }
            if (upperCase.equals("N") || upperCase.equals("NO") || upperCase.equals("0") || upperCase.equals("OFF") || upperCase.equals("FALSE")) {
                return false;
            }
            if (s2 != null) {
                this.b("Invalid " + s2 + " value: " + s);
            }
        }
        return b;
    }
    
    public int a(final String s, final int n) {
        final String s2 = this.c ? null : this.getParameter(s);
        if (s2 != null) {
            return abljema.d(s2);
        }
        return n;
    }
    
    private String f(final String o) {
        synchronized (this.n) {
            this.p = "Inline thread " + o + " did not set result";
            this.o = o;
            new Thread(this, String.valueOf(this.m) + "abljemInline" + this.o).start();
            try {
                this.n.wait();
            }
            catch (InterruptedException ex) {}
            // monitorexit(this.n)
            return this.p;
        }
    }
    
    public void run() {
        try {
            if (this.o.equals(this.q)) {
                this.p = this.a.h();
            }
            if (this.o.equals(this.r)) {
                this.p = null;
                this.b(this.s, this.t, this.u, this.v);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.p = "Inline thread " + this.o + " failed - see Java Console";
        }
        synchronized (this.n) {
            this.n.notify();
        }
        // monitorexit(this.n)
    }
    
    public void a(final String s) {
        ABLwidgets.abljem.a(s);
    }
    
    public void b(final String s) {
        ABLwidgets.abljem.b(s);
    }
    
    public static void main(final String[] i) {
        final abljem abljem = new abljem();
        if (i.length < 1) {
            System.out.println("Parameters for " + abljem.k + ": hostURL x y wid hgh switches");
            System.out.println("  hostURL = URL of GUIStyle control file (required)");
            System.out.println("  x,y,wid,hgh = emulation window position and size (optional)");
            System.out.println("  switches = m");
            System.out.println("    m = mode T/t/g/G for pureText/text/gui(w/text)/Gui(w/pureText)");
            return;
        }
        abljem.c = true;
        abljem.i = i;
        if (i.length > 0) {
            abljem.d = i[0];
        }
        if (i.length > 5) {
            abljem.j = i[5];
        }
        else if (i.length > 1) {
            abljem.j = i[1];
        }
        abljem.init();
        abljem.start();
    }
    
    public static void c(final String s) {
        ABLwidgets.abljem.a(s);
    }
    
    public static void d(final String s) {
        ABLwidgets.abljem.b(s);
    }
}
