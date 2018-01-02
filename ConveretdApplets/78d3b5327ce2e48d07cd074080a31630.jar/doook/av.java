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

public final class av extends at
{
    public static bi a;
    private String o;
    
    public final void a(final String s, final String s2, final r r, final String s3, final int n) {
        String s4 = "";
        if (bs.e) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, av.a);
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
            s4 = av.a.getDocumentBase().getHost();
        }
        if (s4.length() == 0) {
            s4 = "localhost";
        }
        System.out.println("ChatClient.login()");
        this.a(s, s2, r, new URL("http://" + av.a.getCodeBase().getHost() + "/DoookNet/"), n, s4);
    }
    
    protected final void t(final aJ aj) {
        if (this.o != null) {
            super.f.a(false);
            try {
                synchronized (super.f) {
                    for (int i = 0; i < super.f.b(); ++i) {
                        final aW aw = (aW)super.f.a(i);
                        if (aw.d().equals(this.o)) {
                            super.H = aw.e();
                            break;
                        }
                    }
                }
            }
            finally {
                super.f.f();
            }
        }
        super.t(aj);
    }
    
    public final void f() {
    }
    
    public final void c() {
        final String parameter = av.a.getParameter("siteID");
        final String parameter2 = av.a.getParameter("nickname");
        final String x = null;
        final String v = null;
        final Object o = null;
        final String w = null;
        final String y = null;
        final String z = null;
        final String s = null;
        final Object o2 = null;
        final Object o3 = null;
        final String s2 = null;
        final String s3 = null;
        final String u = null;
        final String parameter3 = av.a.getParameter("floodControl");
        final String parameter4 = av.a.getParameter("autoPopup");
        final String parameter5 = av.a.getParameter("KickUserHTML");
        final String parameter6 = av.a.getParameter("logoutHTML");
        final String parameter7 = av.a.getParameter("noLogoutButton");
        final String s4 = null;
        final String s5 = null;
        final String s6 = null;
        super.A = av.a.getCodeBase().getHost();
        if (v != null) {
            super.v = v;
        }
        if (x != null) {
            super.x = x;
        }
        if (w != null) {
            super.w = w;
        }
        if (y != null) {
            super.y = y;
        }
        if (z != null) {
            super.z = z;
        }
        if ("male".equals(o)) {
            super.M = 1;
        }
        else if ("female".equals(o)) {
            super.M = 0;
        }
        if ("true".equals(o2)) {
            super.F = true;
        }
        if ("true".equals(parameter4)) {
            super.E = true;
        }
        if ("true".equals(o3)) {
            super.I = true;
        }
        if ("true".equals(parameter7)) {
            super.h = true;
        }
        if ("true".equalsIgnoreCase(s5)) {
            super.J = true;
        }
        if ("true".equalsIgnoreCase(s4)) {
            super.K = true;
        }
        if ("true".equalsIgnoreCase(s6)) {
            super.y = true;
        }
        if (s != null) {
            try {
                super.I = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {}
        }
        if (s2 != null) {
            try {
                super.L = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (s3 != null) {
            try {
                super.d = new URL(s3);
            }
            catch (MalformedURLException ex3) {}
        }
        if (parameter5 != null) {
            try {
                super.e = new URL(parameter5);
            }
            catch (MalformedURLException ex4) {}
        }
        if (parameter6 != null) {
            try {
                super.f = new URL(parameter6);
            }
            catch (MalformedURLException ex5) {}
        }
        if (u != null) {
            super.u = u;
        }
        super.j = 250;
        if (parameter2 != null) {
            super.M = true;
        }
        if (parameter3 != null) {
            try {
                super.j = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex6) {}
        }
        if (av.a.getParameter("MenuItem1") != null) {
            for (int n = 1; av.a.getParameter("MenuItem" + n) != null; ++n) {
                super.q.a(new MenuItem(av.a.getParameter("MenuItem" + n)), n);
                try {
                    super.r.a(new URL(av.a.getParameter("MenuLocation" + n)), n);
                }
                catch (MalformedURLException ex7) {
                    super.q.c(n);
                }
            }
        }
        try {
            super.K = Integer.parseInt(parameter);
        }
        catch (NumberFormatException ex8) {
            super.K = -999;
        }
        try {
            new URL(new String("http://" + super.A + "/DoookNet/Config/" + parameter + ".class")).openStream().close();
            super.N = true;
        }
        catch (IOException ex9) {}
        catch (Exception ex10) {}
        this.b(parameter2);
    }
    
    public final Image a(final URL url) {
        return av.a.getImage(url);
    }
    
    public final void g() {
        av.a.reset();
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = av.a.getAppletContext();
        appletContext.showDocument(url, s);
        if (bs.d && bs.t == 1 && bs.g < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public av(final bi a) {
        av.a = a;
        try {
            this.c();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
