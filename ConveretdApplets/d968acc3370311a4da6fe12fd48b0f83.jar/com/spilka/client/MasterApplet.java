// 
// Decompiled by Procyon v0.5.30
// 

package com.spilka.client;

import java.awt.Component;
import a.cM;
import a.cG;
import a.dz;
import a.ea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import a.dV;
import a.ep;
import java.awt.Color;
import a.a;
import com.spilka.client.muc.ChatNames;
import java.io.IOException;
import a.eb;
import java.io.InputStream;
import a.en;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import com.spilka.client.muc.AppletAbstract;

public class MasterApplet extends AppletAbstract
{
    public final void q() {
        super.q.e();
        for (int i = 0; i < this.w.length; ++i) {
            this.w[i].flush();
        }
    }
    
    public void init() {
        super.init();
        this.w = this.getParameter("altHost");
        final URL codeBase = this.getCodeBase();
        this.q = this.getParameter("language");
        if (this.q != null) {
            try {
                try {
                    final eb eb = new eb(new en(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + this.q + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final eb eb2 = new eb(new en(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
                catch (IOException ex2) {
                    final eb eb3 = new eb(new en(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        a.e = eb.q(ChatNames.getChatName());
        System.out.println(a.e);
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("backgroundImage");
        final String parameter3 = this.getParameter("textcolor");
        final String parameter4 = this.getParameter("themeID");
        final String parameter5 = this.getParameter("preferredPort");
        final String parameter6 = this.getParameter("COPPA1");
        final String parameter7 = this.getParameter("COPPA2");
        String s = this.getParameter("password");
        try {
            if (s != null) {
                final ep ep;
                (ep = new ep()).q(s);
                s = ep.toString();
            }
        }
        catch (Exception ex3) {}
        try {
            if (parameter != null) {
                background = dV.q(parameter);
            }
        }
        catch (NumberFormatException ex4) {}
        try {
            if (parameter3 != null) {
                foreground = dV.q(parameter3);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter4 != null) {
                this.e = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex6) {}
        try {
            if (parameter5 != null) {
                this.r = Integer.parseInt(parameter5);
            }
        }
        catch (NumberFormatException ex7) {}
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter8;
        if ((parameter8 = this.getParameter("embedded")) != null && parameter8.equalsIgnoreCase("true")) {
            this.q = true;
        }
        try {
            final String parameter9;
            if ((parameter9 = this.getParameter(this.q ? "height" : "WindowHeight")) != null) {
                this.q = Integer.parseInt(parameter9);
            }
            final String parameter10;
            if ((parameter10 = this.getParameter(this.q ? "width" : "WindowWidth")) != null) {
                this.w = Integer.parseInt(parameter10);
            }
        }
        catch (NumberFormatException ex8) {}
        AppletAbstract.q = this;
        String host;
        if ((host = this.getCodeBase().getHost()) == null || host.equals("")) {
            host = "127.0.0.1";
        }
        a.w = host;
        ea.w();
        super.q = new dz(this);
        final String parameter11;
        if ((parameter11 = this.getParameter("RoomsTab")) != null && parameter11.equalsIgnoreCase("false")) {
            super.q.w(false);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("UsersTab")) != null && parameter12.equalsIgnoreCase("false")) {
            super.q.e(false);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("''''''''''")) != null && parameter13.equalsIgnoreCase("true")) {
            super.q.q(true);
        }
        this.add("Center", super.q = new cG(this, super.q, super.q.getName() == null, s == null, false, super.q.v == -999, parameter6, parameter7, s, parameter2));
        super.q.validate();
        if (this.q.getName() == null || this.q.getName().trim().equals("")) {
            this.q.i();
        }
        this.getParameter("name");
        ea.q();
        try {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = this.getImage(new URL(this.getCodeBase() + dV.q("\"5C?EB35CZCD1BCZCD1B/") + i + ".gif"));
                this.w[i] = this.getImage(new URL(this.getCodeBase() + dV.q("\"5C?EB35CZCD1DEC5CZCD1DEC/") + i + ".gif"));
            }
        }
        catch (Exception ex9) {}
    }
    
    public void stop() {
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i].flush();
            this.w[i].flush();
        }
        if (super.q != null && (this.q || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.q.w();
        }
    }
    
    public MasterApplet() {
        a.q = 1;
    }
    
    public void avCheck(final boolean b, final boolean b2) {
        System.out.println("isCam=" + b + ", isMic=" + b2);
    }
}
