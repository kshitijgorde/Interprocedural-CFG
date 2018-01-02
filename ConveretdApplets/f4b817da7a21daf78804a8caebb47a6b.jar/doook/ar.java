// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.applet.AppletContext;
import java.awt.Image;
import java.io.IOException;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public final class ar extends aW
{
    public static t b;
    private String u;
    
    public final void a(final String s, final String s2, final M m, final String s3, final int n) {
        String s4 = "";
        if (doook.F.c) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, ar.b);
                final Class[] array = { Class.forName("java.lang.String") };
                final Object invoke2 = invoke.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke, new String("document"));
                s4 = new URL((String)invoke2.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke2, new String("URL"))).getHost();
            }
            catch (NoClassDefFoundError noClassDefFoundError) {}
            catch (ClassNotFoundException ex) {}
            catch (NoSuchMethodException ex2) {}
            catch (IllegalAccessException ex3) {}
            catch (InvocationTargetException ex4) {}
            catch (Exception ex5) {}
        }
        if ("".equals(s4) || s4 == null) {
            s4 = ar.b.getDocumentBase().getHost();
        }
        if (s4.length() == 0) {
            s4 = "localhost";
        }
        System.out.println("ChatClient.login()");
        this.a(s, s2, m, new URL("http://" + ar.b.getCodeBase().getHost() + "/DoookNet/"), n, s4);
    }
    
    protected final void a(final V v) {
        if (this.u != null) {
            super.e.a(false);
            try {
                synchronized (super.e) {
                    for (int i = 0; i < super.e.a(); ++i) {
                        final a a = (a)super.e.a(i);
                        if (a.g().equals(this.u)) {
                            super.F = a.b();
                            break;
                        }
                    }
                }
            }
            finally {
                super.e.c();
            }
        }
        super.a(v);
    }
    
    public final void j() {
    }
    
    public final void k() {
        final String parameter = ar.b.getParameter("siteID");
        final String parameter2 = ar.b.getParameter("serverID");
        final String parameter3 = ar.b.getParameter("nickname");
        final String f = null;
        final String d = null;
        final Object o = null;
        final String e = null;
        final String g = null;
        final String h = null;
        final String s = null;
        final Object o2 = null;
        final Object o3 = null;
        final String s2 = null;
        final String s3 = null;
        final String c = null;
        final String parameter4 = ar.b.getParameter("floodControl");
        final String parameter5 = ar.b.getParameter("autoPopup");
        final String parameter6 = ar.b.getParameter("KickUserHTML");
        final String parameter7 = ar.b.getParameter("logoutHTML");
        final String parameter8 = ar.b.getParameter("noLogoutButton");
        final String s4 = null;
        final String s5 = null;
        final String s6 = null;
        super.I = ar.b.getCodeBase().getHost();
        if (d != null) {
            super.D = d;
        }
        if (f != null) {
            super.F = f;
        }
        if (e != null) {
            super.E = e;
        }
        if (g != null) {
            super.G = g;
        }
        if (h != null) {
            super.H = h;
        }
        if ("male".equals(o)) {
            super.L = 1;
        }
        else if ("female".equals(o)) {
            super.L = 0;
        }
        if ("true".equals(o2)) {
            super.L = true;
        }
        if ("true".equals(parameter5)) {
            super.K = true;
        }
        if ("true".equals(o3)) {
            super.O = true;
        }
        if ("true".equals(parameter8)) {
            super.b = true;
        }
        if ("true".equalsIgnoreCase(s5)) {
            super.P = true;
        }
        if ("true".equalsIgnoreCase(s4)) {
            super.Q = true;
        }
        if ("true".equalsIgnoreCase(s6)) {
            super.e = true;
        }
        if (s != null) {
            try {
                super.G = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {}
        }
        if (s2 != null) {
            try {
                super.K = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (s3 != null) {
            try {
                super.d = new URL(s3);
            }
            catch (MalformedURLException ex3) {}
        }
        if (parameter6 != null) {
            try {
                super.e = new URL(parameter6);
            }
            catch (MalformedURLException ex4) {}
        }
        if (parameter7 != null) {
            try {
                super.f = new URL(parameter7);
            }
            catch (MalformedURLException ex5) {}
        }
        if (c != null) {
            super.C = c;
        }
        super.k = 250;
        if (parameter3 != null) {
            super.S = true;
        }
        if (parameter4 != null) {
            try {
                super.k = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex6) {}
        }
        if (ar.b.getParameter("MenuItem1") != null) {
            for (int n = 1; ar.b.getParameter("MenuItem" + n) != null; ++n) {
                super.p.a(new MenuItem(ar.b.getParameter("MenuItem" + n)), n);
                try {
                    super.q.a(new URL(ar.b.getParameter("MenuLocation" + n)), n);
                }
                catch (MalformedURLException ex7) {
                    super.p.b(n);
                }
            }
        }
        try {
            super.I = Integer.parseInt(parameter);
            super.J = Integer.parseInt(parameter2);
        }
        catch (NumberFormatException ex8) {
            super.I = -999;
        }
        try {
            new URL(new String("http://" + super.I + "/DoookNet/Config/" + parameter + ".class")).openStream().close();
            super.T = true;
        }
        catch (IOException ex9) {}
        catch (Exception ex10) {}
        this.f(parameter3);
    }
    
    public final Image a(final URL url) {
        return ar.b.getImage(url);
    }
    
    public final void l() {
        ar.b.c();
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = ar.b.getAppletContext();
        appletContext.showDocument(url, s);
        if (doook.F.b && doook.F.f == 1 && doook.F.a < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public ar(final t b) {
        ar.b = b;
        try {
            this.k();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
