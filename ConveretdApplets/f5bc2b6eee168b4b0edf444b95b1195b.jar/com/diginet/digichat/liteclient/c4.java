// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.liteclient;

import java.awt.Image;
import java.awt.Menu;
import com.diginet.digichat.util.filetransfer.ag;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.diginet.digichat.util.i;
import com.diginet.digichat.client.User2;
import com.diginet.digichat.client.bf;
import com.diginet.digichat.client.x;
import java.awt.Component;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.client.ad;
import com.diginet.digichat.common.a1;
import com.diginet.digichat.client.ChatMessage;
import com.diginet.digichat.common.User;
import java.applet.AppletContext;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import com.diginet.digichat.awt.am;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.client.au;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.diginet.digichat.util.tunnel3.ap;
import com.diginet.digichat.common.b;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import com.diginet.digichat.util.c3;
import com.diginet.digichat.common.v;
import com.diginet.digichat.network.t;
import java.awt.Frame;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.client.h;

public class c4 extends h
{
    private DigiChatAppletAbstract a;
    private String b;
    
    protected final void d() {
        super.b4 = null;
        super.b5 = null;
        super.aj.c();
        super.al.c();
        super.an.c();
        super.ao.c();
        super.ap.c();
        super.ar.c();
        super.as.c();
        super.at.c();
        super.ah.c();
        super.az.c();
        this.c();
        super.o = -1;
        if (super.af != null) {
            final Frame b = super.af.b();
            super.af.a();
            super.af = null;
            if (b != null) {
                b.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.ae != null) {
            try {
                super.ae.close();
                super.w.close();
                super.x.close();
            }
            catch (Exception ex2) {}
        }
        super.ae = null;
        super.x = null;
        super.w = null;
    }
    
    public final void b(final t t) {
        switch (t.b()) {
            case 67585: {
                this.ae(t);
                break;
            }
            case 65794: {
                this.d(t);
                break;
            }
            case 66049: {
                this.z(t);
                break;
            }
            case 50400777: {
                this.aa(t);
                break;
            }
            case 66305: {
                this.i(t);
                break;
            }
            case 66308: {
                this.e(t);
                break;
            }
            case 66306: {
                this.f(t);
                break;
            }
            case 66307: {
                this.ag(t);
                break;
            }
            case 66561: {
                this.af(t);
                break;
            }
            case 66816:
            case 50400771: {
                this.ab(t);
                break;
            }
            case 66817: {
                this.ah(t);
                break;
            }
            case 67330: {
                this.p(t);
                break;
            }
            case 67331: {
                this.g(t);
                break;
            }
            case 67333: {
                this.ai(t);
                break;
            }
            case 67334: {
                this.h(t);
                break;
            }
            case 67584: {
                this.a(t);
                break;
            }
            case 67586: {
                this.c(t);
                break;
            }
            case 68608: {
                this.ac(t);
                break;
            }
            case 67338: {
                this.al(t);
                break;
            }
            case 67341: {
                this.an(t);
                break;
            }
            case 67843: {
                this.j(t);
                break;
            }
            case 50400787: {
                this.y(t);
                break;
            }
        }
    }
    
    public final void a(final String s, final String s2, final v v, final String s3, final int n) throws IOException {
        String s4 = "";
        if (com.diginet.digichat.util.c3.g) {
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
        this.a(s, s2, v, this.a.getCodeBase(), n, s4);
    }
    
    public final void a(final String s, final String s2, final v v, final URL ba, final int n, final String s3) throws IOException {
        try {
            this.a();
            super.bc = false;
            super.ap.c();
            super.ba = ba;
            this.g(s);
            int n2;
            if (com.diginet.digichat.util.c3.e && com.diginet.digichat.util.c3.c < 65792 && com.diginet.digichat.util.c3.b == 1) {
                n2 = 2;
            }
            else {
                n2 = 256;
            }
            boolean b = false;
            if (DigiChatAppletAbstract.preferredPort != 0) {
                try {
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.b.c) {
                        if (com.diginet.digichat.common.b.d == "") {
                            throw new Exception("HttpServlet address not specified");
                        }
                        super.ae = new ap(com.diginet.digichat.common.b.d);
                    }
                    else {
                        super.ae = new Socket(ba.getHost(), DigiChatAppletAbstract.preferredPort);
                    }
                    super.x = new DataOutputStream(new BufferedOutputStream(super.ae.getOutputStream(), 256));
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.b.c) {
                        super.w = new DataInputStream(super.ae.getInputStream());
                    }
                    else {
                        super.w = new DataInputStream(new BufferedInputStream(super.ae.getInputStream(), n2));
                    }
                    b = this.n();
                }
                catch (Exception ex8) {}
            }
            if (!b) {
                for (int n3 = 0; !b && n3 < com.diginet.digichat.common.b.a.size(); ++n3) {
                    final int a = com.diginet.digichat.common.b.a(n3);
                    try {
                        if (a != DigiChatAppletAbstract.preferredPort) {
                            if (a == com.diginet.digichat.common.b.c) {
                                if (com.diginet.digichat.common.b.d == "") {
                                    throw new Exception("HttpServlet address not specified");
                                }
                                super.ae = new ap(com.diginet.digichat.common.b.d);
                            }
                            else {
                                super.ae = new Socket(ba.getHost(), a);
                            }
                            super.x = new DataOutputStream(new BufferedOutputStream(super.ae.getOutputStream(), 256));
                            if (com.diginet.digichat.common.b.a(n3) == com.diginet.digichat.common.b.c) {
                                super.w = new DataInputStream(super.ae.getInputStream());
                            }
                            else {
                                super.w = new DataInputStream(new BufferedInputStream(super.ae.getInputStream(), n2));
                            }
                            b = this.n();
                        }
                    }
                    catch (Exception ex) {
                        if (n3 == com.diginet.digichat.common.b.a.size() - 1) {
                            throw ex;
                        }
                    }
                }
            }
            if (b) {
                if (super.ac != null && super.ac.isAlive()) {
                    super.ac.suspend();
                }
                if (super.ad != null && super.ad.isAlive()) {
                    super.ad.suspend();
                }
                super.ac = new au(this);
                super.ad = new Thread(this, "Decoder");
                super.bb = false;
                super.ad.start();
                final t t = new t(65793, 1);
                t.a(0, 0, n);
                t.a(0, 1, super.bj);
                t.a(0, 0, s);
                t.a(0, 1, "en");
                t.a(0, 2, s3);
                t.a(0, 3, s2);
                t.a(0, 0, v);
                if (!this.q() && super.bj != -999) {
                    t.f(0, 2);
                }
                if (this.s()) {
                    t.f(0, 3);
                }
                this.ap(t);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex2) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex7, this).setVisible(true);
        }
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext = this.a.getAppletContext();
        appletContext.showDocument(url, s);
        if (com.diginet.digichat.util.c3.e && com.diginet.digichat.util.c3.b == 1 && com.diginet.digichat.util.c3.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final void a(final String s, final User user, final int n, final int n2, final boolean b, final long n3, final int n4) {
        if (super.af != null) {
            int x = user.x();
            if (x == this.x()) {
                x = n;
            }
            user.getName();
            final int a = user.a;
            final ChatMessage chatMessage = new ChatMessage(this.c(s), user, n != -1 && n != -3 && n != -2 && !b, null);
            chatMessage.a(n == -3);
            if (chatMessage.p) {
                final ad ad = (ad)super.ag.d(x);
                if (ad != null) {
                    ad.a(chatMessage);
                    return;
                }
            }
            chatMessage.n = user.c;
            super.af.a(chatMessage);
            if (((a6)super.al.d(super.b)).b()) {
                ++super.o;
            }
        }
    }
    
    protected final void a(final t t) {
        this.t(t.m);
        this.b(t.a(0));
        super.y = t.c(0, 0);
        if (!"Admin".equals(super.y)) {
            if (DigiChatAppletAbstract.embedded) {
                (super.af = new c5(this, null)).setVisible(false);
                DigiChatAppletAbstract.login.setVisible(false);
                DigiChatAppletAbstract.applet.add(super.af.c());
                ((c5)super.af).validate();
            }
            else {
                super.af = (x)new c9(this).b();
            }
        }
    }
    
    protected final void c(final t t) {
        super.g = true;
        if (this.b != null) {
            super.al.a(false);
            try {
                synchronized (super.al) {
                    for (int i = 0; i < super.al.b(); ++i) {
                        final a6 a6 = (a6)super.al.c(i);
                        if (a6.getName().equals(this.b)) {
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
        this.a(super.bi);
        final bf bf = (bf)super.al.d(super.bj);
        final bf bf2 = (bf)super.al.d(super.bh);
        if (bf == null || (bf.k == null && bf2.k != null)) {
            this.a(bf2);
        }
        else {
            this.a(bf);
        }
    }
    
    protected final void b() {
        if (!super.g) {
            super.b = super.bh;
        }
    }
    
    protected final void z(final t t) {
        for (int i = 0; i < t.c(); ++i) {
            final User2 user2 = (User2)super.aj.d(t.a(i, 0));
            if (user2 != null) {
                this.a(user2, t.a(i, 1), t.i(), t.h());
            }
        }
    }
    
    protected final void a(final User2 user2, final int n, final long n2, final int n3) {
        final int b = user2.b;
        final bf bf = (bf)super.al.d(n);
        final bf bf2 = (bf)super.al.d(user2.b);
        this.b();
        if (user2.x() == this.x()) {
            if (bf2 != null) {
                bf2.b = false;
            }
            bf.b = true;
            super.b = n;
            if (super.af != null) {
                if (bf2 != null && (bf2.b() || bf.b())) {
                    this.a(bf2, bf);
                }
                if (bf.a() && bf.b()) {
                    this.v();
                }
                if (!super.bc) {
                    this.c(false);
                }
                super.af.b(bf);
                if (!super.af.isVisible()) {
                    super.af.setVisible(true);
                }
                super.af.c().validate();
            }
        }
        if (bf != null) {
            final bf bf3 = bf;
            ++bf3.c;
            if (super.af != null) {
                super.af.c(bf);
            }
        }
        if (bf2 != null) {
            final bf bf4 = bf2;
            --bf4.c;
            if (super.af != null) {
                super.af.c(bf2);
            }
        }
        if (n == super.b && user2.b != n) {
            if (bf != null && !bf.u(57)) {
                final ChatMessage chatMessage = new ChatMessage(StringSubst.Substitute(LanguageSupport.translate("(This user has entered %1)"), new String[] { this.c(bf.getName()) }), user2, false, null);
                if (super.af != null) {
                    super.af.a(chatMessage);
                }
            }
        }
        else if (user2.b == super.b && n != super.b && bf2 != null && bf != null && !bf2.u(57)) {
            final ChatMessage chatMessage2 = new ChatMessage(StringSubst.Substitute(LanguageSupport.translate("(This user has moved to %1)"), new String[] { this.c(bf.getName()) }), user2, false, null);
            if (super.af != null) {
                super.af.a(chatMessage2);
            }
        }
        user2.b = n;
        if (super.af != null) {
            if (super.bc || n == super.b) {
                super.af.a(user2, true);
            }
            else if (!super.bc && b == super.b) {
                super.af.a(user2);
            }
        }
    }
    
    protected final void d(final t t) {
        this.b();
        for (int i = 0; i < t.c(); ++i) {
            final User2 user2 = (User2)super.aj.d(t.a(i, 0));
            if (user2 != null) {
                final bf bf = (bf)super.al.d(user2.b);
                if (bf != null) {
                    final bf bf2 = bf;
                    --bf2.c;
                    if (super.af != null) {
                        super.af.c(bf);
                    }
                    if (user2.b == super.b && !bf.u(57)) {
                        final String c = t.c(i, 0);
                        String s;
                        if (c == null) {
                            s = StringSubst.Substitute(LanguageSupport.translate("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        else {
                            s = this.c(c) + " " + StringSubst.Substitute(LanguageSupport.translate("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        final ChatMessage chatMessage = new ChatMessage(s, user2, false, null);
                        if (super.af != null) {
                            super.af.a(chatMessage);
                        }
                    }
                }
                if (super.af != null) {
                    super.af.a(user2);
                }
                final ad ad = (ad)super.ag.d(user2.x());
                if (ad != null) {
                    ad.dispose();
                }
                super.aj.a(true);
                try {
                    super.aj.c(user2);
                }
                finally {
                    super.aj.a();
                }
            }
        }
    }
    
    protected final void e(final t t) {
        for (int i = 0; i < t.c(); ++i) {
            User user = (User)super.aj.d(t.a(i, 0));
            if (user == null && this.s() && t.e(0, 3)) {
                user = new User(-999, t.c(0, 1));
                user.a = t.a(0, 1);
            }
            if (user == null) {
                user = new User(-999, "Guest");
                user.a = super.bi;
            }
            if (user != null) {
                if (user.x() == this.x()) {
                    final int m = t.m;
                }
                if (!user.d) {
                    this.a(t.c(i, 0), user, t.m, t.l, true, t.i(), t.h());
                }
            }
        }
    }
    
    protected final void ac(final t t) {
        final String[] array = new String[t.c()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = t.c(i, 0);
        }
        new am(super.af.b(), LanguageSupport.translate("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected final void f(final t t) {
        if (super.af != null) {
            for (int i = 0; i < t.c(); ++i) {
                super.af.a(new ChatMessage(this.c(t.c(i, 1)), this.c(t.c(i, 0)), t.m != -1, null, -999, false, false));
            }
        }
    }
    
    protected final void i(final t t) {
        this.b();
        for (int i = 0; i < t.c(); ++i) {
            User user = (User)super.aj.d(t.a(i, 0));
            if (user == null && this.s() && t.e(0, 3)) {
                user = new User(-999, t.c(0, 1));
                user.a = t.a(0, 1);
            }
            if (user != null) {
                if (user.x() == this.x()) {
                    final int m = t.m;
                }
                if (t.c(i, 0) == null) {
                    return;
                }
                if (!user.d) {
                    this.a(t.c(i, 0), user, t.m, t.l, false, t.i(), t.h());
                }
            }
        }
    }
    
    protected final void g(final t t) {
        super.ai.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                a1 a2 = (a1)super.ai.d(a);
                if (t.e(i, 63)) {
                    if (a2 != null) {
                        super.ai.f(a);
                    }
                }
                else {
                    if (a2 == null) {
                        a2 = new a1(a, t.c(i, 0));
                        super.ai.a(a2);
                    }
                    else {
                        a2.g(t.c(i, 0));
                    }
                    a2.b(t.a(i));
                    if (t.e(i, 62)) {
                        super.bi = a;
                    }
                }
            }
        }
        finally {
            super.ai.a();
        }
    }
    
    protected final void h(final t t) {
        this.b();
        super.aj.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                User2 user2 = (User2)super.aj.d(a);
                if (t.e(i, 63)) {
                    if (user2 != null) {
                        final bf bf = (bf)super.al.d(t.a(i, 2));
                        if (bf != null) {
                            final bf bf2 = bf;
                            --bf2.c;
                            if (super.af != null) {
                                super.af.c(bf);
                            }
                        }
                        super.aj.f(a);
                        if (super.af != null) {
                            super.af.a(user2);
                        }
                        final ad ad = (ad)super.ag.d(user2.x());
                        if (ad != null) {
                            ad.dispose();
                        }
                    }
                }
                else {
                    final String c = this.c(t.c(i, 0));
                    if (user2 == null) {
                        user2 = new User2(a, c);
                        super.aj.a(user2);
                    }
                    else {
                        user2.g(c);
                    }
                    user2.a = t.a(i, 1);
                    user2.b = t.a(i, 2);
                    user2.b(t.a(i));
                    final String c2 = t.c(i, 1);
                    final String c3 = t.c(i, 2);
                    if (c2 != null) {
                        user2.e = c2;
                    }
                    if (c3 != null) {
                        user2.f = c3;
                    }
                    if (a == this.x()) {
                        this.g(user2.getName());
                        if (user2.a != -999) {
                            super.cc = user2.a;
                        }
                    }
                    if (super.af != null) {
                        super.af.a(user2, false);
                    }
                }
            }
        }
        finally {
            super.aj.a();
        }
    }
    
    protected final void j(final t t) {
        super.a5 = t.a(0);
        super.cr = t.a(0, 3);
        super.cs = t.a(0, 4);
        super.ct = t.a(0, 5);
    }
    
    public final void c() {
        this.a.reset();
    }
    
    public final void a(final ChatMessage chatMessage, final User user) {
        final int x = user.x();
        if (((a6)super.al.d(user.b)).a() && user.u(59) && !super.bm) {
            return;
        }
        ad ad = (ad)super.ag.d(x);
        if (ad == null) {
            ad = new da(super.af.b(), this, x);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.ag.b() % 4) {
                case 0: {
                    n3 = 0;
                    n4 = 0;
                    break;
                }
                case 1: {
                    n3 = 0;
                    n4 = n + 10;
                    break;
                }
                case 2: {
                    n3 = n2 + 10;
                    n4 = 0;
                    break;
                }
                default: {
                    n3 = n2 + 10;
                    n4 = n + 10;
                    break;
                }
            }
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            ad.reshape(2 + n4, 10 + n3, n, n2);
            super.ag.a(ad, x);
        }
        if (chatMessage != null) {
            ad.a(chatMessage);
        }
        ad.setVisible(true);
    }
    
    public final void a(final int n) {
        final t t = new t(67334, 1);
        t.a(0, 0, this.x());
        t.a(0, 1, n);
        t.a(0, 0, this.getName());
        t.m = -1;
        t.l = -1;
        this.ap(t);
    }
    
    public final boolean a(final ag ag) {
        return false;
    }
    
    public final void a(final User user) {
    }
    
    public final int b(final ag ag) {
        return -1;
    }
    
    public final void d(final int n) {
    }
    
    public final void e(final int n) {
    }
    
    public final void a(final int n, final User user) {
    }
    
    public final void a(final Menu menu) {
    }
    
    protected final void a(final User2 user2, final int n, final long n2, final long n3, final int n4) {
    }
    
    public final Image a(final String s, final boolean b) {
        return null;
    }
}
