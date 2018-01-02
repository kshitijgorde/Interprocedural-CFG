// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Image;
import java.net.MalformedURLException;
import com.diginet.digichat.common.p;
import java.awt.Event;
import com.esial.util.c;
import com.diginet.digichat.awt.MenuPanel;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import com.diginet.digichat.util.dx;
import com.diginet.digichat.common.e;
import com.diginet.digichat.awt.MenuItem;

public final class g extends h
{
    private String b;
    private DigiChatAppletAbstract a;
    private MenuItem itmSett;
    private MenuItem itmLogo;
    
    public final void a(final String s, final String s2, final e e, final String s3, final int n) throws IOException {
        String s4 = "";
        if (dx.f) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, this.a);
                final Class[] array = { Class.forName("java.lang.String") };
                final Object invoke2 = invoke.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke, new String("document"));
                s4 = new URL((String)invoke2.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke2, new String("URL"))).getHost();
            }
            catch (NoClassDefFoundError noClassDefFoundError) {}
            catch (ClassNotFoundException ex) {}
            catch (NoSuchMethodException ex2) {}
            catch (IllegalAccessException ex3) {}
            catch (InvocationTargetException ex4) {}
            catch (Exception ex5) {}
        }
        if ("".equals(s4) || s4 == null) {
            s4 = this.a.getDocumentBase().getHost();
        }
        if (s4.length() == 0) {
            s4 = "localhost";
        }
        System.out.println("ChatClient.login()");
        this.a(s, s2, e, this.a.getCodeBase(), n, s4);
    }
    
    public final void c() throws IOException {
    }
    
    public void createOptions(final MenuPanel menuPanel) {
        if (!DigiChatAppletAbstract.lite) {
            menuPanel.addSeparator();
            menuPanel.add(this.itmSett = new MenuItem(com.esial.util.c.a("Settings")));
        }
        if (!super.e) {
            if (DigiChatAppletAbstract.lite) {
                menuPanel.addSeparator();
            }
            menuPanel.add(this.itmLogo = new MenuItem(com.esial.util.c.a("Logout")));
        }
    }
    
    public boolean a(final Event event) {
        if (event.target == this.itmSett) {
            this.c(0);
        }
        else {
            if (event.target != this.itmLogo) {
                return super.a(event);
            }
            this.u();
        }
        return true;
    }
    
    public void setRoom(final bd bd) {
    }
    
    public void setProf(final bd bd) {
    }
    
    public void callAdv(final bd bd) {
    }
    
    public void closeAdv(final bd bd) {
    }
    
    public Object getUpload(final Object o) {
        return null;
    }
    
    public final void b() throws IOException {
        String s = this.a.getParameter("siteID");
        final String parameter = this.a.getParameter("nickname");
        final String parameter2 = this.a.getParameter("email");
        final String parameter3 = this.a.getParameter("realname");
        final String parameter4 = this.a.getParameter("gender");
        final String parameter5 = this.a.getParameter("url");
        final String parameter6 = this.a.getParameter("exitmessage");
        final String parameter7 = this.a.getParameter("comments");
        String s2 = this.a.getParameter("iconID");
        final String parameter8 = this.a.getParameter("roomID");
        final String parameter9 = this.a.getParameter("blockProfile");
        final String parameter10 = this.a.getParameter("openProfileURL");
        final String parameter11 = this.a.getParameter("age");
        final String parameter12 = this.a.getParameter("helpURL");
        final String parameter13 = this.a.getParameter("brandURL");
        final String parameter14 = this.a.getParameter("shieldURL");
        final String parameter15 = this.a.getParameter("floodControl");
        final String parameter16 = this.a.getParameter("ports");
        final String parameter17 = this.a.getParameter("autoPopup");
        final String parameter18 = this.a.getParameter("noLogoutButton");
        final String parameter19 = this.a.getParameter("flaggedBackground");
        final String parameter20 = this.a.getParameter("signed");
        final String parameter21 = this.a.getParameter("KickUserHTML");
        final String parameter22 = this.a.getParameter("logoutHTML");
        final String parameter23 = this.a.getParameter("dateTimeStamp");
        if (this.a.getParameter("HttpServlet") != null) {
            com.diginet.digichat.common.p.d = this.a.getParameter("HttpServlet");
        }
        if (s == null) {
            s = this.a.getParameter("port");
        }
        if (s != null) {
            try {
                super.br = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                super.br = -999;
            }
        }
        if (parameter2 != null) {
            super.bg = parameter2;
        }
        if (parameter3 != null) {
            super.be = parameter3;
        }
        if ("male".equals(parameter4)) {
            super.bt = 1;
        }
        else if ("female".equals(parameter4)) {
            super.bt = 0;
        }
        if (parameter5 != null) {
            super.bf = parameter5;
        }
        if (parameter6 != null) {
            super.bh = parameter6;
        }
        if (parameter7 != null) {
            super.bi = parameter7;
        }
        if (s2 == null) {
            s2 = this.a.getParameter("icon");
        }
        if (s2 != null) {
            try {
                super.bp = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (parameter8 == null) {
            this.b = this.a.getParameter("room");
        }
        else {
            try {
                super.a6 = Integer.parseInt(parameter8);
            }
            catch (NumberFormatException ex3) {}
        }
        if ("true".equals(parameter9)) {
            super.b6 = true;
        }
        if ("true".equals(parameter10)) {
            super.b9 = true;
        }
        if (parameter11 != null) {
            try {
                super.bs = Integer.parseInt(parameter11);
            }
            catch (NumberFormatException ex4) {}
        }
        if (parameter12 != null) {
            try {
                super.urlHelp = new URL(parameter12);
            }
            catch (MalformedURLException ex5) {}
        }
        if (parameter13 != null) {
            try {
                super.urlBrand = new URL(parameter13);
            }
            catch (MalformedURLException ex6) {}
        }
        if (parameter14 != null) {
            try {
                super.urlShield = new URL(parameter14);
            }
            catch (MalformedURLException ex7) {}
        }
        super.l = 250;
        if (parameter15 != null) {
            try {
                super.l = Integer.parseInt(parameter15);
            }
            catch (NumberFormatException ex8) {}
        }
        if (parameter16 != null) {
            com.diginet.digichat.common.p.a();
            com.diginet.digichat.common.p.a(parameter16);
        }
        if ("true".equals(parameter17)) {
            super.bb = true;
        }
        if ("true".equals(parameter18)) {
            super.e = true;
        }
        if ("true".equalsIgnoreCase(parameter19)) {
            super.cb = true;
        }
        if ("true".equalsIgnoreCase(parameter20)) {
            super.i = true;
        }
        if (parameter21 != null) {
            try {
                super.bn = new URL(parameter21);
            }
            catch (MalformedURLException ex9) {}
        }
        if (parameter22 != null) {
            try {
                super.bo = new URL(parameter22);
            }
            catch (MalformedURLException ex10) {}
        }
        if ("true".equalsIgnoreCase(parameter23)) {
            super.ca = true;
        }
        if (this.a.getParameter("MenuItem1") != null) {
            for (int n = 1; this.a.getParameter(String.valueOf("MenuItem").concat(String.valueOf(n))) != null; ++n) {
                super.an.a(new MenuItem(this.a.getParameter(String.valueOf("MenuItem").concat(String.valueOf(n)))), n);
                try {
                    super.ao.a(new URL(this.a.getParameter(String.valueOf("MenuLocation").concat(String.valueOf(n)))), n);
                }
                catch (MalformedURLException ex11) {
                    super.an.g(n);
                }
            }
        }
        super.bj = this.a.getCodeBase().getHost();
        this.d(parameter);
    }
    
    public final Image a(final URL url) {
        return this.a.getImage(url);
    }
    
    public final AudioClip b(final URL url) {
        return this.a.getAudioClip(url);
    }
    
    public final void j() {
        this.a.reset();
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = this.a.getAppletContext();
        appletContext.showDocument(url, s);
        if (dx.e && dx.b == 1 && dx.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    protected Scripts createScripts() {
        return new Scripts(this.a);
    }
    
    public g(final DigiChatAppletAbstract a) {
        this.a = a;
        try {
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.a8 = true;
        super.a9 = false;
    }
}
