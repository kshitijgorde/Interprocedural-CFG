// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.applet.AppletContext;
import java.awt.Image;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import java.net.URL;
import com.spilka.client.muc.AppletAbstract;

public final class bK extends bI
{
    private AppletAbstract q;
    private String d;
    
    public bK(AppletAbstract q) {
        this.q = q;
        try {
            q = (AppletAbstract)this;
            String s = this.q.getParameter("siteID");
            final String parameter = ((bK)q).q.getParameter("nickname");
            final String r = "";
            final String q2 = "";
            final String s2 = "";
            final String w = "";
            final String y = "";
            final String t = "";
            String s3 = ((bK)q).q.getParameter("iconID");
            final String parameter2 = ((bK)q).q.getParameter("roomID");
            final String parameter3 = ((bK)q).q.getParameter("blockProfile");
            final String parameter4 = ((bK)q).q.getParameter("openProfileURL");
            final String s4 = "";
            final String parameter5 = ((bK)q).q.getParameter("helpURL");
            final String a = "";
            ((bK)q).q.getParameter("floodControl");
            final String parameter6 = ((bK)q).q.getParameter("autoPopup");
            final String parameter7 = ((bK)q).q.getParameter("KickUserHTML");
            final String parameter8 = ((bK)q).q.getParameter("logoutHTML");
            final String parameter9 = ((bK)q).q.getParameter("noLogoutButton");
            final String parameter10 = ((bK)q).q.getParameter("flaggedBackground");
            final String parameter11 = ((bK)q).q.getParameter("dateTimeStamp");
            final String parameter12 = ((bK)q).q.getParameter("signed");
            final String parameter13;
            if ((parameter13 = ((bK)q).q.getParameter("ports")) != null) {
                br.q();
                br.q(parameter13);
            }
            if (((bK)q).q.getParameter("HttpServlet") != null) {
                br.q = ((bK)q).q.getParameter("HttpServlet");
            }
            if (s == null) {
                s = ((bK)q).q.getParameter("port");
            }
            if (s3 == null) {
                s3 = ((bK)q).q.getParameter("icon");
            }
            if (parameter2 == null) {
                ((bK)q).d = ((bK)q).q.getParameter("room");
            }
            else {
                try {
                    ((bI)q).l = Integer.parseInt(parameter2);
                }
                catch (NumberFormatException ex2) {}
            }
            ((bI)q).s = ((bK)q).q.getCodeBase().getHost();
            ((bI)q).q = q2;
            ((bI)q).r = r;
            ((bI)q).w = w;
            ((bI)q).y = y;
            ((bI)q).t = t;
            if ("male".equals(s2)) {
                ((bI)q).y = 1;
            }
            else if ("female".equals(s2)) {
                ((bI)q).y = 2;
            }
            if ("true".equals(parameter3)) {
                ((bI)q).q = true;
            }
            if ("true".equals(parameter6)) {
                ((bI)q).k = true;
            }
            if ("true".equals(parameter4)) {
                ((bI)q).x = true;
            }
            if ("true".equals(parameter9)) {
                ((bI)q).y = true;
            }
            if ("true".equalsIgnoreCase(parameter11)) {
                ((bI)q).c = true;
            }
            if ("true".equalsIgnoreCase(parameter10)) {
                ((bI)q).v = true;
            }
            if ("true".equalsIgnoreCase(parameter12)) {
                ((bI)q).o = true;
            }
            if (s3 != null) {
                try {
                    ((bI)q).z = Integer.parseInt(s3);
                }
                catch (NumberFormatException ex3) {}
            }
            try {
                ((bI)q).t = Integer.parseInt(s4);
            }
            catch (NumberFormatException ex4) {}
            if (parameter5 != null) {
                try {
                    ((bI)q).w = new URL(parameter5);
                }
                catch (MalformedURLException ex5) {}
            }
            if (parameter7 != null) {
                try {
                    ((bI)q).e = new URL(parameter7);
                }
                catch (MalformedURLException ex6) {}
            }
            if (parameter8 != null) {
                try {
                    ((bI)q).r = new URL(parameter8);
                }
                catch (MalformedURLException ex7) {}
            }
            ((bI)q).a = a;
            if (((bK)q).q.getParameter("MenuItem1") != null) {
                for (int n = 1; ((bK)q).q.getParameter("MenuItem" + n) != null; ++n) {
                    ((bI)q).a.q(new MenuItem(((bK)q).q.getParameter("MenuItem" + n)), n);
                    try {
                        ((bI)q).s.q(new URL(((bK)q).q.getParameter("MenuLocation" + n)), n);
                    }
                    catch (MalformedURLException ex8) {
                        ((bI)q).a.w(n);
                    }
                }
            }
            try {
                ((bI)q).c = Integer.parseInt(s);
            }
            catch (NumberFormatException ex9) {
                ((bI)q).c = -999;
            }
            ((ba)q).e = parameter;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public bK() {
    }
    
    public final void q(final String s, final String s2, final cH ch, final int n) {
        if ("".equals("")) {
            this.q.getDocumentBase().getHost();
        }
        final URL codeBase = this.q.getCodeBase();
        System.out.println("Client.login()");
        this.q(s, s2, ch, codeBase, n, null);
    }
    
    protected final void c(final cJ cj) {
        if (this.d != null) {
            final cq y = super.y;
            try {
                synchronized (super.y) {
                    for (int i = 0; i < super.y.q(); ++i) {
                        final bk bk;
                        if ((bk = (bk)super.y.q(i)).getName().equals(this.d)) {
                            super.l = bk.q;
                            break;
                        }
                    }
                }
            }
            finally {
                final cq y2 = super.y;
            }
        }
        super.c(cj);
    }
    
    public final Image q(final URL url) {
        Image image = null;
        try {
            image = this.q.getImage(url);
        }
        catch (Exception ex) {}
        return image;
    }
    
    public final void t() {
        this.q.q();
    }
    
    public final void q(final URL url, final String s) {
        final AppletContext appletContext;
        (appletContext = this.q.getAppletContext()).showDocument(url, s);
        if (cx.w && cx.w == 1 && cx.e < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final boolean q(final cy cy) {
        return false;
    }
    
    public final int q(final cy cy) {
        return 0;
    }
}
