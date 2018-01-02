// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.awt.Image;

public class bq extends t
{
    public static Image l;
    public static Image[] a;
    public static Hashtable g;
    public static Hashtable h;
    Image q;
    Hashtable i;
    boolean ad;
    
    public void stop() {
        if (super.e != null && (t.e || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.e.i();
        }
        aU.c();
        bi.a();
        System.runFinalization();
        System.gc();
    }
    
    public void c() {
        super.b.b();
        aU.c();
        bi.a();
        System.runFinalization();
        System.gc();
    }
    
    public void init() {
        t.l = this.getParameter("altHost");
        final String s = new String("http://" + this.getCodeBase().getHost() + "/DoookNet/");
        aP.a(s + "languages/en.lang");
        bu.a(bu.t + "," + bu.a);
        t.b = this.getParameter("language");
        t.c = this.getParameter("cabbase");
        if (t.c == null) {
            t.c = "0.0.0";
        }
        else {
            if (t.c.toLowerCase().startsWith("client_")) {
                t.c = t.c.substring(7);
            }
            if (t.c.toLowerCase().endsWith(".cab")) {
                t.c = t.c.substring(0, t.c.length() - 4);
            }
        }
        if (t.b != null) {
            try {
                try {
                    final aG ag = new aG(new ai(new GZIPInputStream(new URL(s + "languages/" + t.b + ".langz").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final aG ag2 = new aG(new ai(new URL(s + "languages/" + t.b + ".lang").openStream()));
                }
                catch (IOException ex3) {
                    final aG ag3 = new aG(new ai(new URL(s + "languages/" + t.b + ".lang").openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(t.a + " " + t.c);
        try {
            final String string = String.valueOf('d') + String.valueOf('o') + String.valueOf('o') + String.valueOf('o') + String.valueOf('k') + String.valueOf('.');
            for (int i = 65; i < 91; ++i) {
                Class.forName(string + String.valueOf((char)i));
                Class.forName(string + String.valueOf((char)(i + 32)));
            }
        }
        catch (Exception ex4) {
            return;
        }
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("textcolor");
        final String parameter3 = this.getParameter("themeID");
        final String parameter4 = this.getParameter("preferredPort");
        final String s2 = null;
        final String s3 = null;
        final String s4 = null;
        final String parameter5 = this.getParameter("preferredHost");
        final String parameter6 = this.getParameter("siteName");
        final String parameter7 = this.getParameter("bgImage");
        this.q = null;
        this.i = new Hashtable();
        this.ad = false;
        if (parameter6 != null && parameter7 != null) {
            try {
                final URL url = new URL(s + "Resources/" + parameter6 + "/" + parameter7);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.q = this.getImage(url), 0);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    this.q = null;
                }
                else {
                    final String parameter8 = this.getParameter("pos_nickname_x");
                    final String parameter9 = this.getParameter("pos_nickname_y");
                    final String parameter10 = this.getParameter("pos_connect_x");
                    final String parameter11 = this.getParameter("pos_connect_y");
                    if (parameter8 != null && parameter9 != null && parameter10 != null && parameter11 != null) {
                        this.i.put("nickname_x", parameter8);
                        this.i.put("nickname_y", parameter9);
                        this.i.put("connect_x", parameter10);
                        this.i.put("connect_y", parameter11);
                        this.ad = true;
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.q = null;
            }
        }
        else {
            this.q = null;
        }
        try {
            if (parameter != null) {
                background = u.a(parameter);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter2 != null) {
                foreground = u.a(parameter2);
            }
        }
        catch (NumberFormatException ex6) {}
        try {
            if (parameter3 != null) {
                t.d = Integer.parseInt(parameter3);
            }
        }
        catch (NumberFormatException ex7) {}
        try {
            if (parameter4 != null) {
                t.j = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex8) {}
        if (s2 != null) {
            bu.c();
            bu.a(s2);
        }
        if (parameter5 != null) {
            t.g = parameter5;
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter12 = this.getParameter("embedded");
        if (parameter12 != null && parameter12.equalsIgnoreCase("true")) {
            t.e = true;
        }
        try {
            final String parameter13 = this.getParameter(t.e ? "height" : "WindowHeight");
            if (parameter13 != null) {
                t.b = Integer.parseInt(parameter13);
            }
            final String parameter14 = this.getParameter(t.e ? "width" : "WindowWidth");
            if (parameter14 != null) {
                t.c = Integer.parseInt(parameter14);
            }
        }
        catch (NumberFormatException ex9) {}
        t.a = this;
        super.e = new ar(this);
        final String s5 = null;
        if (s5 != null && s5.equalsIgnoreCase("false")) {
            super.e.c(false);
        }
        final String s6 = null;
        if (s6 != null && s6.equalsIgnoreCase("false")) {
            super.e.d(false);
        }
        final String parameter15 = this.getParameter("BuddiesTab");
        if (parameter15 != null && parameter15.equalsIgnoreCase("true")) {
            super.e.b(true);
        }
        final String s7 = null;
        if (s7 != null && s7.equalsIgnoreCase("true")) {
            super.e.e(true);
        }
        aK.a(null);
        aK.b(null);
        aK.c(null);
        aK.d(null);
        super.b = new bt(this, super.e, super.e.g() == null, false, false, false, s3, s4);
        t.a = super.b;
        this.add("Center", super.b);
        super.b.validate();
    }
    
    public bq() {
        this.q = null;
    }
    
    static {
        bq.a = new Image[4];
        bq.g = new Hashtable();
        bq.h = new Hashtable();
    }
}
