// 
// Decompiled by Procyon v0.5.30
// 

package com.spilka.client;

import java.awt.Component;
import a.bz;
import a.bt;
import a.k;
import a.bK;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import a.cl;
import java.awt.Color;
import a.bw;
import java.io.IOException;
import a.cv;
import java.io.InputStream;
import a.cF;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import a.br;
import a.cu;
import a.a;
import com.spilka.client.muc.AppletAbstract;

public class ClientApplet extends AppletAbstract
{
    public final void q() {
        super.q.w();
        if (this.q != null) {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i].flush();
                this.w[i].flush();
            }
        }
    }
    
    public void init() {
        super.init();
        AppletAbstract.q = this;
        String host;
        if ((host = this.getCodeBase().getHost()) == null || host.equals("")) {
            host = "127.0.0.1";
        }
        a.w = host;
        cu.w();
        final URL codeBase = this.getCodeBase();
        this.w = this.getParameter("altHost");
        br.q(br.w);
        this.q = this.getParameter("language");
        if (this.q != null) {
            try {
                try {
                    final cv cv = new cv(new cF(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + this.q + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final cv cv2 = new cv(new cF(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
                catch (IOException ex2) {
                    final cv cv3 = new cv(new cF(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        a.e = cv.q(bw.getChatName());
        System.out.println(a.e);
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("backgroundImage");
        final String parameter3 = this.getParameter("textcolor");
        final String parameter4 = this.getParameter("themeID");
        final String parameter5 = this.getParameter("preferredPort");
        final String parameter6 = this.getParameter("ports");
        final String parameter7 = this.getParameter("COPPA1");
        final String parameter8 = this.getParameter("COPPA2");
        try {
            if (parameter != null) {
                background = cl.q(parameter);
            }
        }
        catch (NumberFormatException ex3) {}
        try {
            if (parameter3 != null) {
                foreground = cl.q(parameter3);
            }
        }
        catch (NumberFormatException ex4) {}
        try {
            if (parameter4 != null) {
                this.e = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter5 != null) {
                this.r = Integer.parseInt(parameter5);
            }
        }
        catch (NumberFormatException ex6) {}
        if (parameter6 != null) {
            br.q();
            br.q(parameter6);
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter9;
        if ((parameter9 = this.getParameter("embedded")) != null && parameter9.equalsIgnoreCase("true")) {
            this.q = true;
        }
        try {
            final String parameter10;
            if ((parameter10 = this.getParameter(this.q ? "height" : "WindowHeight")) != null) {
                this.q = Integer.parseInt(parameter10);
            }
            final String parameter11;
            if ((parameter11 = this.getParameter(this.q ? "width" : "WindowWidth")) != null) {
                this.w = Integer.parseInt(parameter11);
            }
        }
        catch (NumberFormatException ex7) {}
        super.q = new bK(this);
        final String parameter12;
        if ((parameter12 = this.getParameter("RoomsTab")) != null && parameter12.equalsIgnoreCase("false")) {
            super.q.w(false);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("UsersTab")) != null && parameter13.equalsIgnoreCase("false")) {
            super.q.e(false);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter("BuddiesTab")) != null && parameter14.equalsIgnoreCase("true")) {
            super.q.q(true);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter("suppressUserList")) != null && parameter15.equalsIgnoreCase("true")) {
            super.q.r(true);
        }
        k.q(this.getParameter("font1"));
        k.w(this.getParameter("font1b"));
        k.e(this.getParameter("font2"));
        k.r(this.getParameter("font2b"));
        this.add("Center", super.q = new bt(this, super.q, super.q.getName() == null, false, false, false, parameter7, parameter8, parameter2));
        super.q.validate();
        if (this.q.getName() == null || this.q.getName().trim().equals("")) {
            this.q.u();
        }
        this.getParameter("name");
        cu.q();
    }
    
    public void stop() {
        System.out.println("Stop applet");
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i].flush();
            this.w[i].flush();
        }
        if (super.q != null && (this.q || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.q.w();
        }
    }
    
    public ClientApplet() {
        a.q = 0;
    }
    
    public void avCheck(final boolean b, final boolean b2) {
        System.out.println("isCam=" + b + ", isMic=" + b2);
    }
}
