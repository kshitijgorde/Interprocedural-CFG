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

public class y extends z
{
    public static Image c;
    public static Image[] a;
    public static Hashtable a;
    public static Hashtable b;
    Image d;
    Hashtable c;
    boolean V;
    
    public void stop() {
        if (super.c != null && (z.W || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.c.i();
        }
        bM.r();
        ai.r();
        System.runFinalization();
        System.gc();
    }
    
    public void reset() {
        super.b.d();
        bM.r();
        ai.r();
        System.runFinalization();
        System.gc();
    }
    
    public void init() {
        z.O = this.getParameter("altHost");
        final String s = new String("http://" + this.getCodeBase().getHost() + "/DoookNet/");
        z.H = this.getParameter("language");
        z.I = this.getParameter("cabbase");
        if (z.I == null) {
            z.I = "0.0.0";
        }
        else {
            if (z.I.toLowerCase().startsWith("chatmaster_")) {
                z.I = z.I.substring(11);
            }
            if (z.I.toLowerCase().endsWith(".cab")) {
                z.I = z.I.substring(0, z.I.length() - 4);
            }
        }
        if (z.H != null) {
            try {
                try {
                    final ao ao = new ao(new v(new GZIPInputStream(new URL(s + "languages/" + z.H + ".langz").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final ao ao2 = new ao(new v(new URL(s + "languages/" + z.H + ".lang").openStream()));
                }
                catch (IOException ex3) {
                    final ao ao3 = new ao(new v(new URL(s + "languages/" + z.H + ".lang").openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(z.G + " " + z.I);
        try {
            final String string = String.valueOf('d') + String.valueOf('o') + String.valueOf('o') + String.valueOf('o') + String.valueOf('k') + String.valueOf('.');
            for (int i = 65; i < 91; ++i) {
                Class.forName(string + String.valueOf((char)i));
                Class.forName(string + String.valueOf((char)(i + 32)));
            }
        }
        catch (Exception ex4) {}
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("textcolor");
        final String parameter3 = this.getParameter("themeID");
        final String parameter4 = this.getParameter("preferredPort");
        final String s2 = null;
        final String s3 = null;
        String string2 = null;
        final String parameter5 = this.getParameter("preferredHost");
        final String parameter6 = this.getParameter("siteName");
        final String parameter7 = this.getParameter("bgImage");
        this.d = null;
        this.c = new Hashtable();
        this.V = false;
        if (parameter6 != null && parameter7 != null) {
            try {
                final URL url = new URL(s + "Resources/" + parameter6 + "/" + parameter7);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.d = this.getImage(url), 0);
                mediaTracker.waitForAll();
                if (mediaTracker.isErrorAny()) {
                    this.d = null;
                }
                else {
                    final String parameter8 = this.getParameter("pos_nickname_x");
                    final String parameter9 = this.getParameter("pos_nickname_y");
                    final String parameter10 = this.getParameter("pos_password_x");
                    final String parameter11 = this.getParameter("pos_password_y");
                    final String parameter12 = this.getParameter("pos_invisible_x");
                    final String parameter13 = this.getParameter("pos_invisible_y");
                    final String parameter14 = this.getParameter("pos_ghost_x");
                    final String parameter15 = this.getParameter("pos_ghost_y");
                    final String parameter16 = this.getParameter("pos_connect_x");
                    final String parameter17 = this.getParameter("pos_connect_y");
                    if (parameter8 != null && parameter9 != null && parameter10 != null && parameter11 != null && parameter12 != null && parameter13 != null && parameter14 != null && parameter15 != null && parameter16 != null && parameter17 != null) {
                        this.c.put("nickname_x", parameter8);
                        this.c.put("nickname_y", parameter9);
                        this.c.put("password_x", parameter10);
                        this.c.put("password_y", parameter11);
                        this.c.put("invisible_x", parameter12);
                        this.c.put("invisible_y", parameter13);
                        this.c.put("ghost_x", parameter14);
                        this.c.put("ghost_y", parameter15);
                        this.c.put("connect_x", parameter16);
                        this.c.put("connect_y", parameter17);
                        this.V = true;
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                this.d = null;
            }
        }
        else {
            this.d = null;
        }
        try {
            if (string2 != null) {
                final a a = new a();
                a.a(string2);
                string2 = a.toString();
            }
        }
        catch (Exception ex5) {}
        try {
            if (parameter != null) {
                background = bd.a(parameter);
            }
        }
        catch (NumberFormatException ex6) {}
        try {
            if (parameter2 != null) {
                foreground = bd.a(parameter2);
            }
        }
        catch (NumberFormatException ex7) {}
        try {
            if (parameter3 != null) {
                z.X = Integer.parseInt(parameter3);
            }
        }
        catch (NumberFormatException ex8) {}
        try {
            if (parameter4 != null) {
                z.Y = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex9) {}
        if (parameter5 != null) {
            z.J = parameter5;
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter18 = this.getParameter("embedded");
        if (parameter18 != null && parameter18.equalsIgnoreCase("true")) {
            z.W = true;
        }
        try {
            final String parameter19 = this.getParameter(z.W ? "height" : "WindowHeight");
            if (parameter19 != null) {
                z.V = Integer.parseInt(parameter19);
            }
            final String parameter20 = this.getParameter(z.W ? "width" : "WindowWidth");
            if (parameter20 != null) {
                z.W = Integer.parseInt(parameter20);
            }
        }
        catch (NumberFormatException ex10) {}
        z.a = this;
        super.c = new cI(this);
        final String s4 = null;
        if (s4 != null && s4.equalsIgnoreCase("false")) {
            super.c.d(false);
        }
        final String s5 = null;
        if (s5 != null && s5.equalsIgnoreCase("false")) {
            super.c.e(false);
        }
        final String s6 = null;
        if (s6 != null && s6.equalsIgnoreCase("true")) {
            super.c.b(true);
        }
        super.b = new n(this, super.c, super.c.f() == null, true, false, super.c.u == -999, s2, s3, string2);
        z.a = super.b;
        this.add("Center", super.b);
        super.b.validate();
    }
    
    public y() {
        this.d = null;
    }
    
    static {
        y.a = new Image[4];
        y.a = new Hashtable();
        y.b = new Hashtable();
    }
}
