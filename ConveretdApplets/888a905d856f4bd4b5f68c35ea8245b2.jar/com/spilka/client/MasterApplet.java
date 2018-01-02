// 
// Decompiled by Procyon v0.5.30
// 

package com.spilka.client;

import java.awt.Component;
import a.cs;
import a.dE;
import a.cT;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import a.ds;
import a.dD;
import java.awt.Color;
import a.dN;
import com.spilka.client.muc.ChatNames;
import java.io.IOException;
import a.be;
import java.io.InputStream;
import a.o;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import a.m;

public class MasterApplet extends m
{
    public final void q() {
        super.q.w();
        for (int i = 0; i < this.w.length; ++i) {
            this.w[i].flush();
        }
    }
    
    public void init() {
        this.w = this.getParameter("altHost");
        final URL codeBase = this.getCodeBase();
        this.q = this.getParameter("language");
        if (this.q != null) {
            try {
                try {
                    final be be = new be(new o(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + this.q + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final be be2 = new be(new o(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
                catch (IOException ex) {
                    final be be3 = new be(new o(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
            }
            catch (Exception ex2) {}
        }
        dN.e = be.w(ChatNames.getChatName());
        System.out.println(dN.e);
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
                final dD dd;
                (dd = new dD()).q(s);
                s = dd.toString();
            }
        }
        catch (Exception ex3) {}
        try {
            if (parameter != null) {
                background = ds.q(parameter);
            }
        }
        catch (NumberFormatException ex4) {}
        try {
            if (parameter3 != null) {
                foreground = ds.q(parameter3);
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
        m.q(this);
        super.q = new cT(this);
        final String parameter11;
        if ((parameter11 = this.getParameter("RoomsTab")) != null && parameter11.equalsIgnoreCase("false")) {
            super.q.e(false);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("UsersTab")) != null && parameter12.equalsIgnoreCase("false")) {
            super.q.r(false);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("''''''''''")) != null && parameter13.equalsIgnoreCase("true")) {
            super.q.q(true);
        }
        this.add("Center", super.q = new dE(this, super.q, super.q.a == null, s == null, false, super.q.S == -999, parameter6, parameter7, s, parameter2));
        super.q.validate();
        if (this.q.a == null || this.q.a.trim().equals("")) {
            this.q.e();
        }
        this.getParameter("name");
        try {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = this.getImage(new URL(this.getCodeBase() + ds.q("\"5C?EB35CZCD1BCZCD1B/") + i + ".gif"));
                this.w[i] = this.getImage(new URL(this.getCodeBase() + ds.q("\"5C?EB35CZCD1DEC5CZCD1DEC/") + i + ".gif"));
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
            super.q.u();
        }
    }
    
    public MasterApplet() {
        dN.q = 1;
    }
    
    public void avCheck(final boolean b, final boolean b2) {
        System.out.println("isCam=" + b + ", isMic=" + b2);
    }
}
