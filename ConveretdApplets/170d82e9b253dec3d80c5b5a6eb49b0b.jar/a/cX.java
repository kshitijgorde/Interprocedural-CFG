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

public class cX extends cV
{
    public AppletAbstract q;
    private String f;
    
    public final void q(final String s, final String s2, final ep ep, final int n) {
        String host = "";
        if ("".equals(host)) {
            host = this.q.getDocumentBase().getHost();
        }
        if (host.length() == 0) {
            host = "localhost";
        }
        final URL codeBase = this.q.getCodeBase();
        System.out.println("Client.login()");
        this.q(s, s2, ep, codeBase, n, super.y ? host : null);
    }
    
    public void m(final es es) {
        if (this.f != null) {
            final dW y = super.y;
            try {
                synchronized (super.y) {
                    for (int i = 0; i < super.y.q(); ++i) {
                        final cr cr;
                        if ((cr = (cr)super.y.q(i)).getName().equals(this.f)) {
                            super.z = cr.r;
                            break;
                        }
                    }
                }
            }
            finally {
                final dW y2 = super.y;
            }
        }
        super.m(es);
    }
    
    public final void o() {
        String s = this.q.getParameter("siteID");
        final String parameter = this.q.getParameter("nickname");
        final String r = "";
        final String q = "";
        final String s2 = "";
        final String e = "";
        final String u = "";
        final String y = "";
        String s3 = this.q.getParameter("iconID");
        final String parameter2 = this.q.getParameter("roomID");
        final String parameter3 = this.q.getParameter("blockProfile");
        final String parameter4 = this.q.getParameter("openProfileURL");
        final String s4 = "";
        final String parameter5 = this.q.getParameter("helpURL");
        final String s5 = "";
        this.q.getParameter("floodControl");
        final String parameter6 = this.q.getParameter("autoPopup");
        final String parameter7 = this.q.getParameter("KickUserHTML");
        final String parameter8 = this.q.getParameter("logoutHTML");
        final String parameter9 = this.q.getParameter("noLogoutButton");
        final String parameter10 = this.q.getParameter("flaggedBackground");
        final String parameter11 = this.q.getParameter("dateTimeStamp");
        final String parameter12 = this.q.getParameter("signed");
        final String parameter13;
        if ((parameter13 = this.q.getParameter("ports")) != null) {
            cE.q();
            cE.q(parameter13);
        }
        if (this.q.getParameter("HttpServlet") != null) {
            cE.q = this.q.getParameter("HttpServlet");
        }
        if (s == null) {
            s = this.q.getParameter("port");
        }
        if (s3 == null) {
            s3 = this.q.getParameter("icon");
        }
        if (parameter2 == null) {
            this.f = this.q.getParameter("room");
        }
        else {
            try {
                super.z = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex) {}
        }
        super.d = this.q.getCodeBase().getHost();
        super.q = q;
        super.r = r;
        super.e = e;
        super.u = u;
        super.y = y;
        if ("male".equals(s2)) {
            super.w = 1;
        }
        else if ("female".equals(s2)) {
            super.w = 2;
        }
        if ("true".equals(parameter3)) {
            super.w = true;
        }
        if ("true".equals(parameter6)) {
            super.l = true;
        }
        if ("true".equals(parameter4)) {
            super.c = true;
        }
        if ("true".equals(parameter9)) {
            super.u = true;
        }
        if ("true".equalsIgnoreCase(parameter11)) {
            super.v = true;
        }
        if ("true".equalsIgnoreCase(parameter10)) {
            super.b = true;
        }
        if ("true".equalsIgnoreCase(parameter12)) {
            super.p = true;
        }
        if (s3 != null) {
            try {
                super.x = Integer.parseInt(s3);
            }
            catch (NumberFormatException ex2) {}
        }
        try {
            super.q = Integer.parseInt(s4);
        }
        catch (NumberFormatException ex3) {}
        if (parameter5 != null) {
            try {
                super.w = new URL(parameter5);
            }
            catch (MalformedURLException ex4) {}
        }
        if (parameter7 != null) {
            try {
                super.e = new URL(parameter7);
            }
            catch (MalformedURLException ex5) {}
        }
        if (parameter8 != null) {
            try {
                super.r = new URL(parameter8);
            }
            catch (MalformedURLException ex6) {}
        }
        super.s = s5;
        if (this.q.getParameter("MenuItem1") != null) {
            for (int n = 1; this.q.getParameter("MenuItem" + n) != null; ++n) {
                super.s.q(new MenuItem(this.q.getParameter("MenuItem" + n)), n);
                try {
                    super.d.q(new URL(this.q.getParameter("MenuLocation" + n)), n);
                }
                catch (MalformedURLException ex7) {
                    super.s.w(n);
                }
            }
        }
        try {
            super.v = Integer.parseInt(s);
        }
        catch (NumberFormatException ex8) {
            super.v = -999;
        }
        super.t = parameter;
    }
    
    public final Image q(final URL url) {
        Image image = null;
        try {
            image = this.q.getImage(url);
        }
        catch (Exception ex) {}
        return image;
    }
    
    public void y() {
        this.q.q();
    }
    
    public void q(final URL url, final String s) {
        final AppletContext appletContext;
        (appletContext = this.q.getAppletContext()).showDocument(url, s);
        if (ef.w && ef.w == 1 && ef.e < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final boolean q(final eg eg) {
        return false;
    }
    
    public final int q(final eg eg) {
        return 0;
    }
}
