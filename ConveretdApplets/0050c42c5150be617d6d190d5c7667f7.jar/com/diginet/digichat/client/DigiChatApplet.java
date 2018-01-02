// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.diginet.digichat.awt.e;
import java.awt.Color;
import com.diginet.digichat.common.d;
import java.io.IOException;
import com.esial.util.LanguageSupport;
import java.io.InputStream;
import com.esial.internationalGUI.c;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import com.diginet.digichat.common.b;
import java.awt.Component;

public class DigiChatApplet extends DigiChatAppletAbstract
{
    private y messagesPanel;
    
    public void reset() {
        if (this.messagesPanel != null) {
            this.remove(this.messagesPanel);
            this.messagesPanel = null;
            this.add("Center", super.loginPanel);
        }
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
                    final LanguageSupport languageSupport = new LanguageSupport(new c(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName + "z").openStream())));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    final LanguageSupport languageSupport2 = new LanguageSupport(new c(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName).openStream()));
                }
                catch (IOException ex2) {
                    final LanguageSupport languageSupport3 = new LanguageSupport(new c(new URL(codeBase.toString() + "Resources/languages/" + DigiChatAppletAbstract.langName).openStream()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        DigiChatAppletAbstract.OEM_DigiChat = LanguageSupport.translate("DigiChat");
        System.out.println(DigiChatAppletAbstract.OEM_DigiChat + " " + d.a());
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
                background = e.a(parameter);
            }
        }
        catch (NumberFormatException ex3) {}
        try {
            if (parameter2 != null) {
                foreground = e.a(parameter2);
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
        super.chatUser = new f(this);
        final String parameter11 = this.getParameter("RoomsTab");
        if (parameter11 != null && parameter11.equalsIgnoreCase("false")) {
            super.chatUser.d(false);
        }
        final String parameter12 = this.getParameter("UsersTab");
        if (parameter12 != null && parameter12.equalsIgnoreCase("false")) {
            super.chatUser.e(false);
        }
        final String parameter13 = this.getParameter("BuddiesTab");
        if (parameter13 != null && parameter13.equalsIgnoreCase("true")) {
            super.chatUser.a(true);
        }
        final String parameter14 = this.getParameter("LeftAlignLogo");
        if (parameter14 != null && parameter14.equalsIgnoreCase("true")) {
            super.chatUser.h(true);
        }
        final String parameter15 = this.getParameter("ShowCategories");
        if (parameter15 != null && parameter15.equalsIgnoreCase("true")) {
            super.chatUser.i(true);
        }
        final String parameter16 = this.getParameter("suppressUserList");
        if (parameter16 != null && parameter16.equalsIgnoreCase("true")) {
            super.chatUser.f(true);
        }
        final String parameter17 = this.getParameter("quickLoad");
        if (parameter17 != null && parameter17.equalsIgnoreCase("true")) {
            super.chatUser.g(true);
        }
        m.a(this.getParameter("font1"));
        m.b(this.getParameter("font1b"));
        m.c(this.getParameter("font2"));
        m.d(this.getParameter("font2b"));
        super.loginPanel = new n(super.chatUser, super.chatUser.getName() == null, false, false, false, parameter6, parameter7);
        DigiChatAppletAbstract.login = super.loginPanel;
        this.add("Center", super.loginPanel);
        super.loginPanel.validate();
    }
    
    public void stop() {
        if (super.chatUser != null && (DigiChatAppletAbstract.embedded || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.chatUser.h();
        }
    }
    
    public void showMessagesStatus() {
        this.remove(super.loginPanel);
        this.add("Center", this.messagesPanel = new y(super.chatUser));
        this.validateTree();
        this.doLayout();
    }
    
    public y getMessagesPanel() {
        return this.messagesPanel;
    }
    
    public static String A_B_C_D(final String s) {
        final char[] array = new char[s.length()];
        s.getChars(0, s.length(), array, 0);
        char c = '\0';
        for (int i = 0; i < array.length; ++i) {
            final char[] array2 = array;
            final int n = i;
            final char c2 = (char)(array[i] - '\u0001');
            final char c3 = c;
            ++c;
            array2[n] = (char)(c2 ^ c3);
        }
        return new String(array);
    }
}
