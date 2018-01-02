// 
// Decompiled by Procyon v0.5.30
// 

package com.spilka.client;

import java.awt.Image;
import java.awt.Component;
import a.bq;
import a.cl;
import a.be;
import a.aq;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import a.ce;
import java.awt.Color;
import a.cs;
import a.ch;
import java.io.IOException;
import a.al;
import java.io.InputStream;
import a.k;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import a.bl;
import a.h;

public class ClientApplet extends h
{
    public final void q() {
        super.q.q();
        if (this.q != null) {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i].flush();
                this.w[i].flush();
            }
        }
    }
    
    public void init() {
        h.q(this);
        final URL codeBase = this.getCodeBase();
        this.w = this.getParameter("altHost");
        bl.q(bl.q);
        this.q = this.getParameter("language");
        if (this.q != null) {
            try {
                try {
                    final al al = new al(new k(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + this.q + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final al al2 = new al(new k(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
                catch (IOException ex) {
                    final al al3 = new al(new k(new URL(codeBase.toString() + "Resources/languages/" + this.q).openStream()));
                }
            }
            catch (Exception ex2) {}
        }
        cs.e = al.q(ch.getChatName());
        System.out.println(cs.e);
        Color background = Color.white;
        Color foreground = Color.black;
        Image image = null;
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
                background = ce.q(parameter);
            }
        }
        catch (NumberFormatException ex3) {}
        try {
            if (parameter3 != null) {
                foreground = ce.q(parameter3);
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
            bl.q();
            bl.q(parameter6);
        }
        if (parameter2 != null) {
            image = this.getImage(this.getCodeBase(), "Resources/" + parameter2);
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
        super.q = new aq(this);
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
        be.q(this.getParameter("font1"));
        be.w(this.getParameter("font1b"));
        be.e(this.getParameter("font2"));
        be.r(this.getParameter("font2b"));
        this.add("Center", super.q = new cl(this, super.q, super.q.o == null, false, false, false, parameter7, parameter8, image));
        super.q.validate();
        if (this.q.o == null || this.q.o.trim().equals("")) {
            this.q.e();
        }
        this.getParameter("name");
        try {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = this.getImage(new URL(this.getCodeBase() + ce.q("\"5C?EB35CZCD1BCZCD1B/") + i + ".gif"));
                this.w[i] = this.getImage(new URL(this.getCodeBase() + ce.q("\"5C?EB35CZCD1DEC5CZCD1DEC/") + i + ".gif"));
            }
        }
        catch (Exception ex8) {}
    }
    
    public void stop() {
        System.out.println("Stop applet");
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i].flush();
            this.w[i].flush();
        }
        if (super.q != null && (this.q || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.q.y();
        }
    }
    
    public ClientApplet() {
        cs.q = 0;
    }
    
    public void avCheck(final boolean b, final boolean b2) {
        System.out.println("isCam=" + b + ", isMic=" + b2);
    }
}
