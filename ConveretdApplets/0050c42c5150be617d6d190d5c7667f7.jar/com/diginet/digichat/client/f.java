// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.util.Hashtable;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.MenuItem;
import java.net.MalformedURLException;
import com.diginet.digichat.common.b;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.network.t;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import com.diginet.digichat.util.c3;
import com.diginet.digichat.common.v;

public final class f extends g
{
    private String a;
    
    public final void a(final String s, final String s2, final v v, final String s3, final int n) throws IOException {
        String s4 = "";
        if (com.diginet.digichat.util.c3.g) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, super.a);
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
            s4 = super.a.getDocumentBase().getHost();
        }
        if (s4.length() == 0) {
            s4 = "localhost";
        }
        System.out.println("ChatClient.login()");
        this.a(s, s2, v, super.a.getCodeBase(), n, s4);
    }
    
    protected final void a(final t t) {
        if (this.a != null) {
            super.al.a(false);
            try {
                synchronized (super.al) {
                    for (int i = 0; i < super.al.b(); ++i) {
                        final a6 a6 = (a6)super.al.c(i);
                        if (a6.getName().equals(this.a)) {
                            super.bj = a6.x();
                            break;
                        }
                    }
                }
                // monitorexit(super.al)
            }
            finally {
                super.al.a();
            }
        }
        super.a(t);
    }
    
    public final void a() throws IOException {
    }
    
    public final void b() throws IOException {
        String s = super.a.getParameter("siteID");
        final String parameter = super.a.getParameter("nickname");
        final String parameter2 = super.a.getParameter("email");
        final String parameter3 = super.a.getParameter("realname");
        final String parameter4 = super.a.getParameter("gender");
        final String parameter5 = super.a.getParameter("url");
        final String parameter6 = super.a.getParameter("profileURLLink");
        final String parameter7 = super.a.getParameter("exitmessage");
        final String parameter8 = super.a.getParameter("comments");
        String s2 = super.a.getParameter("iconID");
        String parameter9 = super.a.getParameter("roomID");
        final String parameter10 = super.a.getParameter("blockProfile");
        final String parameter11 = super.a.getParameter("openProfileURL");
        final String parameter12 = super.a.getParameter("age");
        final String parameter13 = super.a.getParameter("helpURL");
        final String parameter14 = super.a.getParameter("buddyList");
        final String parameter15 = super.a.getParameter("floodControl");
        final String parameter16 = super.a.getParameter("autoPopup");
        final String parameter17 = super.a.getParameter("KickUserHTML");
        final String parameter18 = super.a.getParameter("logoutHTML");
        final String parameter19 = super.a.getParameter("noLogoutButton");
        final String parameter20 = super.a.getParameter("flaggedBackground");
        final String parameter21 = super.a.getParameter("dateTimeStamp");
        final String parameter22 = super.a.getParameter("signed");
        final String parameter23 = super.a.getParameter("menuName");
        final String parameter24 = super.a.getParameter("scrollback");
        final String parameter25 = super.a.getParameter("moderatedUserListSort");
        final String parameter26 = super.a.getParameter("countDownTimer");
        final String parameter27 = super.a.getParameter("moderatedQACount");
        final String parameter28 = super.a.getParameter("useModQAIcons");
        final String parameter29 = super.a.getParameter("focusPrivateMessage");
        final String parameter30 = super.a.getParameter("editProfileURL");
        final String parameter31 = super.a.getParameter("IM");
        final String parameter32 = super.a.getParameter("initiateURL");
        final String parameter33 = super.a.getParameter("viewProfileURL");
        final String parameter34 = super.a.getParameter("authRoomLogin");
        final String parameter35 = super.a.getParameter("authRoomPassword");
        final String parameter36 = super.a.getParameter("messagesEnabled");
        final String parameter37 = super.a.getParameter("messagesButtonsSet");
        final String parameter38 = super.a.getParameter("messagesLogoEnabled");
        final String parameter39 = super.a.getParameter("privateMessageTimeOutMS");
        final String parameter40 = super.a.getParameter("showDialogAvatar");
        final String parameter41 = super.a.getParameter("offsiteAvatarURL");
        final String parameter42 = super.a.getParameter("AVSendDisabled");
        final String parameter43 = super.a.getParameter("VideoResolution");
        final String parameter44 = super.a.getParameter("AVConnectionLimit");
        final String parameter45 = super.a.getParameter("AVBroadcaster");
        final String parameter46 = super.a.getParameter("AVIMDisabled");
        final String parameter47 = super.a.getParameter("AVDefaultState");
        if (super.a.getParameter("HttpServlet") != null) {
            com.diginet.digichat.common.b.d = super.a.getParameter("HttpServlet");
        }
        if (s == null) {
            s = super.a.getParameter("port");
        }
        if (s2 == null) {
            s2 = super.a.getParameter("icon");
        }
        if (parameter36 != null && parameter36.equalsIgnoreCase("true")) {
            this.a(66, super.db = true);
            parameter9 = "\u0000";
            super.ab = ((parameter37 != null && !parameter37.equals("")) ? parameter37 : super.ab);
            super.dd = (parameter38 != null && parameter38.equalsIgnoreCase("true"));
        }
        if (parameter40 != null && !parameter40.equalsIgnoreCase("") && super.db) {
            super.de = parameter40.equalsIgnoreCase("true");
        }
        if (parameter41 != null && !parameter41.equalsIgnoreCase("")) {
            super.aa = parameter41;
        }
        if (parameter39 != null && !parameter39.equals("")) {
            try {
                super.n = Integer.parseInt(parameter39);
            }
            catch (NumberFormatException ex) {
                System.out.println("privateMessageTimeOutMS must be set to a legitimate numerical value");
                super.n = 0;
            }
        }
        if (parameter9 == null) {
            this.a = super.a.getParameter("room");
        }
        else {
            try {
                super.bj = Integer.parseInt(parameter9);
            }
            catch (NumberFormatException ex2) {}
        }
        super.bz = super.a.getCodeBase().getHost();
        if (parameter3 != null) {
            super.bt = parameter3;
        }
        if (parameter2 != null) {
            super.bw = parameter2;
        }
        if (parameter5 != null) {
            super.bu = parameter5;
        }
        if (parameter6 != null) {
            super.bv = parameter6;
        }
        if (parameter7 != null) {
            super.bx = parameter7;
        }
        if (parameter8 != null) {
            super.by = parameter8;
        }
        if (parameter23 != null) {
            super.b0 = parameter23;
        }
        if (parameter24 != null) {
            super.cf = Integer.parseInt(parameter24);
        }
        if (parameter30 != null) {
            try {
                super.ca = new URL(parameter30);
            }
            catch (MalformedURLException ex3) {}
        }
        if (parameter32 != null) {
            try {
                super.cb = new URL(parameter32);
            }
            catch (MalformedURLException ex4) {}
        }
        if (parameter33 != null) {
            super.b3 = parameter33;
        }
        if (parameter34 != null) {
            super.b4 = parameter34;
        }
        if (parameter35 != null) {
            super.b5 = parameter35;
        }
        if ("male".equals(parameter4)) {
            super.ch = 1;
        }
        else if ("female".equals(parameter4)) {
            super.ch = 0;
        }
        if ("true".equalsIgnoreCase(parameter10)) {
            super.cv = true;
        }
        if ("true".equalsIgnoreCase(parameter16) && !super.db) {
            super.bp = true;
        }
        if ("true".equalsIgnoreCase(parameter11)) {
            super.cy = true;
        }
        if ("true".equalsIgnoreCase(parameter19)) {
            super.f = true;
        }
        if ("true".equalsIgnoreCase(parameter21)) {
            super.cz = true;
        }
        if ("true".equalsIgnoreCase(parameter20)) {
            super.c0 = true;
        }
        if ("true".equalsIgnoreCase(parameter22)) {
            super.j = true;
        }
        if ("true".equalsIgnoreCase(parameter25)) {
            super.c3 = true;
        }
        if ("true".equalsIgnoreCase(parameter26)) {
            super.c4 = true;
        }
        if ("true".equalsIgnoreCase(parameter27)) {
            super.c7 = true;
        }
        if ("true".equalsIgnoreCase(parameter28)) {
            super.c8 = true;
        }
        if ("true".equalsIgnoreCase(parameter29) || super.db) {
            super.c9 = true;
        }
        if ("true".equalsIgnoreCase(parameter31) && !super.db) {
            super.da = true;
        }
        if (s2 != null) {
            try {
                super.cc = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex5) {}
        }
        if (parameter12 != null) {
            try {
                super.cg = Integer.parseInt(parameter12);
            }
            catch (NumberFormatException ex6) {}
        }
        if (parameter13 != null) {
            try {
                super.b7 = new URL(parameter13);
            }
            catch (MalformedURLException ex7) {}
        }
        if (parameter17 != null) {
            try {
                super.b8 = new URL(parameter17);
            }
            catch (MalformedURLException ex8) {}
        }
        if (parameter18 != null) {
            try {
                super.b9 = new URL(parameter18);
            }
            catch (MalformedURLException ex9) {}
        }
        if (parameter14 != null) {
            super.bo = parameter14;
        }
        super.m = 250;
        if (parameter15 != null) {
            try {
                super.m = Integer.parseInt(parameter15);
            }
            catch (NumberFormatException ex10) {}
        }
        if (super.a.getParameter("MenuItem1") != null) {
            for (int n = 1; super.a.getParameter("MenuItem" + n) != null; ++n) {
                super.aw.a(new MenuItem(super.a.getParameter("MenuItem" + n)), n);
                try {
                    super.ax.a(new URL(super.a.getParameter("MenuLocation" + n)), n);
                    if (super.a.getParameter("MenuTarget" + n) != null) {
                        super.ay.a(super.a.getParameter("MenuTarget" + n), n);
                    }
                    else {
                        super.ay.a("_blank", n);
                    }
                }
                catch (MalformedURLException ex11) {
                    super.aw.f(n);
                }
            }
        }
        if (super.a.getParameter("PluginName1") != null) {
            for (int n2 = 1; super.a.getParameter("PluginName" + n2) != null && super.a.getParameter("PluginClass" + n2) != null; ++n2) {
                try {
                    super.g.put(super.a.getParameter("PluginName" + n2), super.a.getParameter("PluginClass" + n2));
                }
                catch (NullPointerException ex12) {}
            }
        }
        if (super.a.getParameter("CustomParam1") != null) {
            for (int n3 = 1; super.a.getParameter("CustomParam" + n3) != null; ++n3) {
                if (super.a.getParameter("CustomValue" + n3) == null) {
                    break;
                }
                try {
                    ((Hashtable<String, String>)super.h).put("CustomParam" + n3, super.a.getParameter("CustomParam" + n3));
                    ((Hashtable<String, String>)super.h).put("CustomValue" + n3, super.a.getParameter("CustomValue" + n3));
                }
                catch (NullPointerException ex13) {}
            }
        }
        try {
            super.ce = Integer.parseInt(s);
        }
        catch (NumberFormatException ex14) {
            super.ce = -999;
        }
        this.g(parameter);
        if (parameter42 != null && parameter42.equalsIgnoreCase("true")) {
            super.z = true;
        }
        if (parameter46 != null && parameter46.equalsIgnoreCase("true")) {
            super.ab = true;
        }
        if (parameter45 != null && parameter45.equalsIgnoreCase("true")) {
            super.aa = true;
        }
        if (parameter43 != null) {
            if (parameter43.equalsIgnoreCase("large")) {
                super.ac = 2;
            }
            else if (parameter43.equalsIgnoreCase("small")) {
                super.ac = 1;
            }
            else if (parameter43.equalsIgnoreCase("none")) {
                super.ac = 0;
            }
        }
        if (parameter47 != null && !parameter47.equals("")) {
            if (parameter47.equalsIgnoreCase("INACTIVE") && !super.z) {
                super.an = 6;
            }
            if (parameter47.equalsIgnoreCase("IMUSER") && !super.ab) {
                super.an = 65;
            }
            if (parameter47.equalsIgnoreCase("BROADCASTER") && super.aa) {
                super.an = 64;
            }
        }
        if (parameter44 != null) {
            try {
                super.ad = Integer.parseInt(parameter44);
            }
            catch (NumberFormatException ex15) {}
        }
    }
    
    public final Image a(final URL url) {
        return super.a.getImage(url);
    }
    
    public final AudioClip b(final URL url) {
        return super.a.getAudioClip(url);
    }
    
    public final void c() {
        super.a.reset();
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = super.a.getAppletContext();
        appletContext.showDocument(url, s);
        if (com.diginet.digichat.util.c3.e && com.diginet.digichat.util.c3.b == 1 && com.diginet.digichat.util.c3.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public f(final DigiChatAppletAbstract a) {
        super.a = a;
        try {
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
