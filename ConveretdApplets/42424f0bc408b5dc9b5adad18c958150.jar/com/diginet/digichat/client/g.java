// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import com.diginet.digichat.common.b;
import com.diginet.digichat.common.a4;
import com.diginet.digichat.network.v;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import com.diginet.digichat.util.ch;
import com.diginet.digichat.common.x;

public final class g extends h
{
    private DigiChatAppletAbstract a;
    private String b;
    
    public final void a(final String s, final String s2, final x x, final String s3, final int n) throws IOException {
        String s4 = "";
        if (ch.f) {
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
        this.a(s, s2, x, this.a.getCodeBase(), n, s4);
    }
    
    protected final void a(final v v) {
        if (this.b != null) {
            super.ab.a(false);
            try {
                synchronized (super.ab) {
                    for (int i = 0; i < super.ab.b(); ++i) {
                        final a4 a4 = (a4)super.ab.c(i);
                        if (a4.r().equals(this.b)) {
                            super.a4 = a4.q();
                            break;
                        }
                    }
                }
                // monitorexit(super.ab)
            }
            finally {
                super.ab.a();
            }
        }
        super.a(v);
    }
    
    public final void a() throws IOException {
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
        final String parameter13 = this.a.getParameter("buddyList");
        final String parameter14 = this.a.getParameter("floodControl");
        final String parameter15 = this.a.getParameter("autoPopup");
        final String parameter16 = this.a.getParameter("KickUserHTML");
        final String parameter17 = this.a.getParameter("logoutHTML");
        final String parameter18 = this.a.getParameter("noLogoutButton");
        final String parameter19 = this.a.getParameter("flaggedBackground");
        final String parameter20 = this.a.getParameter("dateTimeStamp");
        final String parameter21 = this.a.getParameter("signed");
        if (this.a.getParameter("HttpServlet") != null) {
            com.diginet.digichat.common.b.d = this.a.getParameter("HttpServlet");
        }
        if (s == null) {
            s = this.a.getParameter("port");
        }
        if (s2 == null) {
            s2 = this.a.getParameter("icon");
        }
        if (parameter8 == null) {
            this.b = this.a.getParameter("room");
        }
        else {
            try {
                super.a4 = Integer.parseInt(parameter8);
            }
            catch (NumberFormatException ex) {}
        }
        super.bh = this.a.getCodeBase().getHost();
        if (parameter3 != null) {
            super.bc = parameter3;
        }
        if (parameter2 != null) {
            super.be = parameter2;
        }
        if (parameter5 != null) {
            super.bd = parameter5;
        }
        if (parameter6 != null) {
            super.bf = parameter6;
        }
        if (parameter7 != null) {
            super.bg = parameter7;
        }
        if ("male".equals(parameter4)) {
            super.br = 1;
        }
        else if ("female".equals(parameter4)) {
            super.br = 0;
        }
        if ("true".equals(parameter9)) {
            super.b4 = true;
        }
        if ("true".equals(parameter15)) {
            super.a9 = true;
        }
        if ("true".equals(parameter10)) {
            super.b7 = true;
        }
        if ("true".equals(parameter18)) {
            super.d = true;
        }
        if ("true".equalsIgnoreCase(parameter20)) {
            super.b8 = true;
        }
        if ("true".equalsIgnoreCase(parameter19)) {
            super.b9 = true;
        }
        if ("true".equalsIgnoreCase(parameter21)) {
            super.h = true;
        }
        if (s2 != null) {
            try {
                super.bn = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (parameter11 != null) {
            try {
                super.bq = Integer.parseInt(parameter11);
            }
            catch (NumberFormatException ex3) {}
        }
        if (parameter12 != null) {
            try {
                super.bk = new URL(parameter12);
            }
            catch (MalformedURLException ex4) {}
        }
        if (parameter16 != null) {
            try {
                super.bl = new URL(parameter16);
            }
            catch (MalformedURLException ex5) {}
        }
        if (parameter17 != null) {
            try {
                super.bm = new URL(parameter17);
            }
            catch (MalformedURLException ex6) {}
        }
        if (parameter13 != null) {
            super.a8 = parameter13;
        }
        super.k = 250;
        if (parameter14 != null) {
            try {
                super.k = Integer.parseInt(parameter14);
            }
            catch (NumberFormatException ex7) {}
        }
        if (this.a.getParameter("MenuItem1") != null) {
            for (int n = 1; this.a.getParameter("MenuItem" + n) != null; ++n) {
                super.am.a(new MenuItem(this.a.getParameter("MenuItem" + n)), n);
                try {
                    super.an.a(new URL(this.a.getParameter("MenuLocation" + n)), n);
                }
                catch (MalformedURLException ex8) {
                    super.am.f(n);
                }
            }
        }
        try {
            super.bp = Integer.parseInt(s);
        }
        catch (NumberFormatException ex9) {
            super.bp = -999;
        }
        this.d(parameter);
    }
    
    public final Image a(final URL url) {
        return this.a.getImage(url);
    }
    
    public final AudioClip b(final URL url) {
        return this.a.getAudioClip(url);
    }
    
    public final void c() {
        this.a.reset();
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = this.a.getAppletContext();
        appletContext.showDocument(url, s);
        if (ch.e && ch.b == 1 && ch.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public g(final DigiChatAppletAbstract a) {
        this.a = a;
        try {
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
