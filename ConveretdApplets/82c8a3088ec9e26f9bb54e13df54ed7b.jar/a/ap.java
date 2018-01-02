// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.applet.AppletContext;
import java.awt.Image;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import java.net.URL;

public final class ap extends W
{
    private h q;
    private String d;
    
    public ap(h q) {
        this.q = q;
        try {
            q = (h)this;
            String s = this.q.getParameter("siteID");
            final String parameter = ((ap)q).q.getParameter("nickname");
            final String e = "";
            final String q2 = "";
            final String s2 = "";
            final String w = "";
            final String t = "";
            final String r = "";
            String s3 = ((ap)q).q.getParameter("iconID");
            final String parameter2 = ((ap)q).q.getParameter("roomID");
            final String parameter3 = ((ap)q).q.getParameter("blockProfile");
            final String parameter4 = ((ap)q).q.getParameter("openProfileURL");
            final String s4 = "";
            final String parameter5 = ((ap)q).q.getParameter("helpURL");
            final String a = "";
            ((ap)q).q.getParameter("floodControl");
            final String parameter6 = ((ap)q).q.getParameter("autoPopup");
            final String parameter7 = ((ap)q).q.getParameter("KickUserHTML");
            final String parameter8 = ((ap)q).q.getParameter("logoutHTML");
            final String parameter9 = ((ap)q).q.getParameter("noLogoutButton");
            final String parameter10 = ((ap)q).q.getParameter("flaggedBackground");
            final String parameter11 = ((ap)q).q.getParameter("dateTimeStamp");
            final String parameter12 = ((ap)q).q.getParameter("signed");
            final String parameter13;
            if ((parameter13 = ((ap)q).q.getParameter("ports")) != null) {
                bk.q();
                bk.q(parameter13);
            }
            if (((ap)q).q.getParameter("HttpServlet") != null) {
                ((ap)q).q.getParameter("HttpServlet");
            }
            if (s == null) {
                s = ((ap)q).q.getParameter("port");
            }
            if (s3 == null) {
                s3 = ((ap)q).q.getParameter("icon");
            }
            if (parameter2 == null) {
                ((ap)q).d = ((ap)q).q.getParameter("room");
            }
            else {
                try {
                    ((W)q).k = Integer.parseInt(parameter2);
                }
                catch (NumberFormatException ex) {}
            }
            ((W)q).s = ((ap)q).q.getCodeBase().getHost();
            ((W)q).q = q2;
            ((W)q).e = e;
            ((W)q).w = w;
            ((W)q).t = t;
            ((W)q).r = r;
            if ("male".equals(s2)) {
                ((W)q).w = 1;
            }
            else if ("female".equals(s2)) {
                ((W)q).w = 2;
            }
            if ("true".equals(parameter3)) {
                ((W)q).q = true;
            }
            if ("true".equals(parameter6)) {
                ((W)q).k = true;
            }
            if ("true".equals(parameter4)) {
                ((W)q).x = true;
            }
            if ("true".equals(parameter9)) {
                ((W)q).y = true;
            }
            if ("true".equalsIgnoreCase(parameter11)) {
                ((W)q).c = true;
            }
            if ("true".equalsIgnoreCase(parameter10)) {
                ((W)q).v = true;
            }
            if ("true".equalsIgnoreCase(parameter12)) {
                ((W)q).o = true;
            }
            if (s3 != null) {
                try {
                    ((W)q).l = Integer.parseInt(s3);
                }
                catch (NumberFormatException ex2) {}
            }
            try {
                ((W)q).q = Integer.parseInt(s4);
            }
            catch (NumberFormatException ex3) {}
            if (parameter5 != null) {
                try {
                    ((W)q).w = new URL(parameter5);
                }
                catch (MalformedURLException ex4) {}
            }
            if (parameter7 != null) {
                try {
                    ((W)q).e = new URL(parameter7);
                }
                catch (MalformedURLException ex5) {}
            }
            if (parameter8 != null) {
                try {
                    ((W)q).r = new URL(parameter8);
                }
                catch (MalformedURLException ex6) {}
            }
            ((W)q).a = a;
            if (((ap)q).q.getParameter("MenuItem1") != null) {
                for (int n = 1; ((ap)q).q.getParameter("MenuItem" + n) != null; ++n) {
                    ((W)q).a.q(new MenuItem(((ap)q).q.getParameter("MenuItem" + n)), n);
                    try {
                        ((W)q).s.q(new URL(((ap)q).q.getParameter("MenuLocation" + n)), n);
                    }
                    catch (MalformedURLException ex7) {
                        ((W)q).a.w(n);
                    }
                }
            }
            try {
                ((W)q).x = Integer.parseInt(s);
            }
            catch (NumberFormatException ex8) {
                ((W)q).x = -999;
            }
            ((aJ)q).o = parameter;
        }
        catch (Exception ex9) {}
    }
    
    public ap() {
    }
    
    public final void q(final String s, final String s2, final ck ck, final int n) {
        if ("".equals("")) {
            this.q.getDocumentBase().getHost();
        }
        final URL codeBase = this.q.getCodeBase();
        System.out.println("Client.login()");
        this.q(s, s2, ck, codeBase, n, null);
    }
    
    protected final void w(final cp cp) {
        if (this.d != null) {
            try {
                synchronized (super.y) {
                    for (int i = 0; i < super.y.q; ++i) {
                        final bw bw;
                        if ((bw = (bw)super.y.q(i)).o.equals(this.d)) {
                            super.k = bw.s;
                            break;
                        }
                    }
                }
            }
            finally {}
        }
        super.w(cp);
    }
    
    public final Image q(final URL url) {
        Image image = null;
        try {
            image = this.q.getImage(url);
        }
        catch (Exception ex) {}
        return image;
    }
    
    public final void r() {
        this.q.q();
    }
    
    public final void q(final URL url, final String s) {
        final AppletContext appletContext;
        (appletContext = this.q.getAppletContext()).showDocument(url, s);
        if (bD.w && bD.w == 1 && bD.e < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final boolean q(final cj cj) {
        return false;
    }
    
    public final int q(final cj cj) {
        return 0;
    }
}
