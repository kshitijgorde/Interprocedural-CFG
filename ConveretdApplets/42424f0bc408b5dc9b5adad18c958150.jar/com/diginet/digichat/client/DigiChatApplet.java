// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import com.diginet.digichat.awt.p;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.diginet.digichat.awt.f;
import java.awt.Color;
import com.diginet.digichat.common.e;
import java.io.IOException;
import com.esial.util.d;
import java.io.InputStream;
import com.esial.internationalGUI.c;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import com.diginet.digichat.common.b;

public class DigiChatApplet extends DigiChatAppletAbstract
{
    public void reset() {
        super.loginPanel.c();
    }
    
    public void init() {
        final URL codeBase = this.getCodeBase();
        DigiChatAppletAbstract.altHost = this.getParameter("altHost");
        b.a(b.e + "," + b.c);
        DigiChatAppletAbstract.langName = this.getParameter("language");
        if (DigiChatAppletAbstract.langName != null) {
            try {
                try {
                    final d d = new d(new c(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final d d2 = new d(new c(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName).openStream()));
                }
                catch (IOException ex2) {
                    final d d3 = new d(new c(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName).openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        DigiChatAppletAbstract.OEM_DigiChat = d.a("DigiChat");
        System.out.println(DigiChatAppletAbstract.OEM_DigiChat + " " + e.a());
        Color background = Color.white;
        Color foreground = Color.black;
        final String parameter = this.getParameter("background");
        final String parameter2 = this.getParameter("textcolor");
        final String parameter3 = this.getParameter("themeID");
        final String parameter4 = this.getParameter("preferredPort");
        final String parameter5 = this.getParameter("ports");
        final String parameter6 = this.getParameter("COPPA1");
        final String parameter7 = this.getParameter("COPPA2");
        try {
            if (parameter != null) {
                background = f.a(parameter);
            }
        }
        catch (NumberFormatException ex3) {}
        try {
            if (parameter2 != null) {
                foreground = f.a(parameter2);
            }
        }
        catch (NumberFormatException ex4) {}
        try {
            if (parameter3 != null) {
                DigiChatAppletAbstract.preferredTheme = Integer.parseInt(parameter3);
            }
        }
        catch (NumberFormatException ex5) {}
        try {
            if (parameter4 != null) {
                DigiChatAppletAbstract.preferredPort = Integer.parseInt(parameter4);
            }
        }
        catch (NumberFormatException ex6) {}
        if (parameter5 != null) {
            b.a();
            b.a(parameter5);
        }
        this.setBackground(background);
        this.setForeground(foreground);
        this.setLayout(new BorderLayout());
        final String parameter8 = this.getParameter("embedded");
        if (parameter8 != null && parameter8.equalsIgnoreCase("true")) {
            DigiChatAppletAbstract.embedded = true;
        }
        try {
            final String parameter9 = this.getParameter(DigiChatAppletAbstract.embedded ? "height" : "WindowHeight");
            if (parameter9 != null) {
                DigiChatAppletAbstract.initialWindowHeight = Integer.parseInt(parameter9);
            }
            final String parameter10 = this.getParameter(DigiChatAppletAbstract.embedded ? "width" : "WindowWidth");
            if (parameter10 != null) {
                DigiChatAppletAbstract.initialWindowWidth = Integer.parseInt(parameter10);
            }
        }
        catch (NumberFormatException ex7) {}
        DigiChatAppletAbstract.applet = this;
        super.chatUser = new g(this);
        final String parameter11 = this.getParameter("RoomsTab");
        if (parameter11 != null && parameter11.equalsIgnoreCase("false")) {
            super.chatUser.c(false);
        }
        final String parameter12 = this.getParameter("UsersTab");
        if (parameter12 != null && parameter12.equalsIgnoreCase("false")) {
            super.chatUser.d(false);
        }
        final String parameter13 = this.getParameter("BuddiesTab");
        if (parameter13 != null && parameter13.equalsIgnoreCase("true")) {
            super.chatUser.a(true);
        }
        final String parameter14 = this.getParameter("suppressUserList");
        if (parameter14 != null && parameter14.equalsIgnoreCase("true")) {
            super.chatUser.e(true);
        }
        p.a(this.getParameter("font1"));
        p.b(this.getParameter("font1b"));
        p.c(this.getParameter("font2"));
        p.d(this.getParameter("font2b"));
        super.loginPanel = new q(super.chatUser, super.chatUser.r() == null, false, false, false, parameter6, parameter7);
        DigiChatAppletAbstract.login = super.loginPanel;
        this.add("Center", super.loginPanel);
        super.loginPanel.validate();
    }
    
    public void stop() {
        if (super.chatUser != null && (DigiChatAppletAbstract.embedded || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.chatUser.o();
        }
    }
}
