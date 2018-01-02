// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.Image;
import java.applet.AppletContext;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public final class bk extends bl
{
    public final void a(final String s, final String s2, final ap ap, String s3, final int n) {
        s3 = (String)(super.d ? super.a.getCodeBase() : new URL("http://" + s3 + "/DigiChat/DigiClasses/"));
        if (aZ.c && super.d) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(this, super.a);
                final Class[] array = { Class.forName("java.lang.String") };
                final Object invoke2;
                new URL((String)(invoke2 = invoke.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke, new String("document"))).getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke2, new String("URL"))).getHost();
            }
            catch (NoClassDefFoundError noClassDefFoundError) {}
            catch (ClassNotFoundException ex) {}
            catch (NoSuchMethodException ex2) {}
            catch (IllegalAccessException ex3) {}
            catch (InvocationTargetException ex4) {}
            catch (Exception ex5) {}
        }
        String string;
        if ((string = this.a(new Object[] { "window.location.href" }).toString()).length() == 0) {
            string = "localhost";
        }
        System.out.println("ChatClient.login()");
        this.a(s, s2, ap, (URL)s3, n, super.d ? string : null);
    }
    
    public final void a(final String s, final String s2, final ap ap, final URL url, final int n, final String s3) {
        super.a(s, s2, ap, url, n, s3);
    }
    
    public final void h() {
        if (super.f != null && super.f.length() > 0) {
            this.a(super.f);
        }
    }
    
    private Object a(final Object[] array) {
        try {
            return JSObject.getWindow((Applet)super.a).call("eval", array);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext;
        (appletContext = super.a.getAppletContext()).showDocument(url, s);
        if (aZ.b && aZ.b == 1 && aZ.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final Image a(final URL url) {
        if (super.d && aZ.c <= 66048) {
            return super.a.getImage(url);
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public final void d() {
        super.a.a();
    }
    
    public bk(aV a) {
        super.a = a;
        super.d = true;
        try {
            a = (aV)this;
            String s = super.a.getParameter("siteID");
            final String parameter = ((bh)a).a.getParameter("nickname");
            final String parameter2 = ((bh)a).a.getParameter("floodControl");
            final String parameter3 = ((bh)a).a.getParameter("ports");
            final String parameter4 = ((bh)a).a.getParameter("autoPopup");
            final String parameter5 = ((bh)a).a.getParameter("noLogoutButton");
            final String parameter6 = ((bh)a).a.getParameter("flaggedBackground");
            ((bh)a).a.getParameter("signed");
            if (parameter3 != null) {
                ((bh)a).a.a.removeAllElements();
                ((bh)a).a.a(parameter3);
            }
            else {
                ((bh)a).a.a.removeAllElements();
                ((bh)a).a.a(((bh)a).a.a);
            }
            if (((bh)a).a.getParameter("HttpServlet") != null) {
                ((bh)a).a.getParameter("HttpServlet");
            }
            if (s == null) {
                s = ((bh)a).a.getParameter("port");
            }
            ((bh)a).e = ((bh)a).a.getCodeBase().getHost();
            if ("true".equals(parameter5)) {
                ((bh)a).e = true;
            }
            if ("true".equals(parameter4)) {
                ((bh)a).l = true;
            }
            if ("true".equalsIgnoreCase(parameter6)) {
                ((bh)a).n = true;
            }
            ((bh)a).i = 250;
            if (parameter2 != null) {
                try {
                    ((bh)a).i = Integer.parseInt(parameter2);
                }
                catch (NumberFormatException ex2) {}
            }
            if (((bh)a).a.getParameter("MenuItem1") != null) {
                for (int n = 1; ((bh)a).a.getParameter("MenuItem" + n) != null; ++n) {
                    ((bh)a).n.a(new MenuItem(((bh)a).a.getParameter("MenuItem" + n)), n);
                    try {
                        ((bh)a).o.a(new URL(((bh)a).a.getParameter("MenuLocation" + n)), n);
                    }
                    catch (MalformedURLException ex3) {
                        ((bh)a).n.b(n);
                    }
                }
            }
            if (s != null) {
                try {
                    ((bh)a).o = Integer.parseInt(s);
                }
                catch (NumberFormatException ex4) {
                    ((bh)a).o = -999;
                }
            }
            ((an)a).c = parameter;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        super.k = true;
    }
}
