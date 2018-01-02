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

public class aX extends bi
{
    public static Image n;
    public static Image[] a;
    public static Hashtable d;
    public static Hashtable e;
    Image o;
    Hashtable f;
    boolean ad;
    
    public void stop() {
        if (super.j != null && (bi.ae || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.j.n();
        }
        bu.b();
        h.b();
        System.runFinalization();
        System.gc();
    }
    
    public void reset() {
        super.b.g();
        bu.b();
        h.b();
        System.runFinalization();
        System.gc();
    }
    
    public void init() {
        bi.Y = this.getParameter("altHost");
        final String s = new String("http://" + this.getCodeBase().getHost() + "/DoookNet/");
        aI.f(s + "languages/en.lang");
        L.a(L.l + "," + L.g);
        bi.R = this.getParameter("language");
        bi.S = this.getParameter("cabbase");
        if (bi.S == null) {
            bi.S = "0.0.0";
        }
        else {
            if (bi.S.toLowerCase().startsWith("client_")) {
                bi.S = bi.S.substring(7);
            }
            if (bi.S.toLowerCase().endsWith(".cab")) {
                bi.S = bi.S.substring(0, bi.S.length() - 4);
            }
        }
        if (bi.R != null) {
            try {
                try {
                    final ar ar = new ar(new g(new GZIPInputStream(new URL(s + "languages/" + bi.R + ".langz").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final ar ar2 = new ar(new g(new URL(s + "languages/" + bi.R + ".lang").openStream()));
                }
                catch (IOException ex3) {
                    final ar ar3 = new ar(new g(new URL(s + "languages/" + bi.R + ".lang").openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(bi.Q + " " + bi.S);
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
        this.o = null;
        this.f = new Hashtable();
        this.ad = false;
        if (parameter6 != null && parameter7 != null) {
            try {
                final URL url = new URL(s + "Resources/" + parameter6 + "/" + parameter7);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.o = this.getImage(url), 0);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    this.o = null;
                }
                else {
                    final String parameter8 = this.getParameter("pos_nickname_x");
                    final String parameter9 = this.getParameter("pos_nickname_y");
                    final String parameter10 = this.getParameter("pos_connect_x");
                    final String parameter11 = this.getParameter("pos_connect_y");
                    if (parameter8 != null && parameter9 != null && parameter10 != null && parameter11 != null) {
                        this.f.put("nickname_x", parameter8);
                        this.f.put("nickname_y", parameter9);
                        this.f.put("connect_x", parameter10);
                        this.f.put("connect_y", parameter11);
                        this.ad = true;
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.o = null;
            }
        }
        else {
            this.o = null;
        }
        try {
            if (parameter != null) {
                background = aD.a(parameter);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter2 != null) {
                foreground = aD.a(parameter2);
            }
        }
        catch (NumberFormatException ex6) {}
        try {
            if (parameter3 != null) {
                bi.at = Integer.parseInt(parameter3);
            }
        }
        catch (NumberFormatException ex7) {}
        try {
            if (parameter4 != null) {
                bi.au = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex8) {}
        if (s2 != null) {
            L.f();
            L.a(s2);
        }
        if (parameter5 != null) {
            bi.T = parameter5;
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter12 = this.getParameter("embedded");
        if (parameter12 != null && parameter12.equalsIgnoreCase("true")) {
            bi.ae = true;
        }
        try {
            final String parameter13 = this.getParameter(bi.ae ? "height" : "WindowHeight");
            if (parameter13 != null) {
                bi.ar = Integer.parseInt(parameter13);
            }
            final String parameter14 = this.getParameter(bi.ae ? "width" : "WindowWidth");
            if (parameter14 != null) {
                bi.as = Integer.parseInt(parameter14);
            }
        }
        catch (NumberFormatException ex9) {}
        bi.b = this;
        super.j = new av(this);
        final String s5 = null;
        if (s5 != null && s5.equalsIgnoreCase("false")) {
            super.j.c(false);
        }
        final String s6 = null;
        if (s6 != null && s6.equalsIgnoreCase("false")) {
            super.j.d(false);
        }
        final String s7 = null;
        if (s7 != null && s7.equalsIgnoreCase("true")) {
            super.j.a(true);
        }
        final String s8 = null;
        if (s8 != null && s8.equalsIgnoreCase("true")) {
            super.j.e(true);
        }
        ay.a(null);
        ay.d(null);
        ay.e(null);
        ay.b(null);
        super.b = new ap(this, super.j, super.j.d() == null, false, false, false, s3, s4);
        bi.a = super.b;
        this.add("Center", super.b);
        super.b.validate();
    }
    
    public aX() {
        this.o = null;
    }
    
    static {
        aX.a = new Image[4];
        aX.d = new Hashtable();
        aX.e = new Hashtable();
    }
}
