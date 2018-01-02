// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.net.URLConnection;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import com.diginet.digichat.common.Theme;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.diginet.digichat.util.aj;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Event;
import com.diginet.digichat.util.filetransfer.cb;
import com.diginet.digichat.util.filetransfer.b6;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import com.diginet.digichat.common.b5;
import com.diginet.digichat.util.i;
import com.diginet.digichat.common.be;
import java.io.ByteArrayOutputStream;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.common.d;
import com.diginet.digichat.common.a6;
import com.diginet.digichat.common.a1;
import com.diginet.digichat.common.User;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import com.diginet.digichat.awt.am;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.diginet.digichat.util.tunnel3.ap;
import com.diginet.digichat.common.b;
import java.io.IOException;
import com.diginet.digichat.common.v;
import java.lang.reflect.Constructor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import com.diginet.digichat.util.c3;
import java.awt.Button;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import com.diginet.digichat.network.t;
import java.awt.Frame;
import java.util.Enumeration;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import com.diginet.digichat.util.filetransfer.ag;
import java.awt.Dialog;
import java.awt.Image;
import java.applet.AudioClip;
import com.diginet.digichat.client.plugin.bc;
import java.util.Properties;
import java.util.Hashtable;

public abstract class g extends h
{
    protected DigiChatAppletAbstract a;
    protected ah b;
    private Hashtable c;
    private Hashtable d;
    protected Hashtable e;
    protected Hashtable f;
    protected Hashtable g;
    protected Properties h;
    protected Properties i;
    private bc j;
    public boolean k;
    protected String l;
    protected String m;
    protected String n;
    protected String o;
    protected String p;
    protected String q;
    protected String r;
    protected String s;
    protected String t;
    protected String u;
    protected String v;
    protected String w;
    protected String x;
    protected String y;
    protected boolean z;
    protected boolean aa;
    protected boolean ab;
    protected int ac;
    protected int ad;
    private AudioClip ae;
    private Image af;
    private Image ag;
    private Image ah;
    protected boolean ai;
    protected boolean aj;
    protected int ak;
    protected int al;
    private bf am;
    protected int an;
    
    protected final void d() {
        super.b4 = null;
        super.b5 = null;
        super.ai.c();
        super.aj.c();
        super.al.c();
        super.am.c();
        super.an.c();
        super.ao.c();
        super.ap.c();
        super.ar.c();
        super.as.c();
        super.at.c();
        super.ah.c();
        super.az.c();
        this.e.clear();
        this.f.clear();
        if (super.ag != null) {
            for (int i = 0; i < super.ag.b(); ++i) {
                ((ad)super.ag.c(i)).dispose();
            }
        }
        final Enumeration<Dialog> elements = (Enumeration<Dialog>)this.d.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement = elements.nextElement();
            if (nextElement instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<ag> elements2 = (Enumeration<ag>)this.c.elements();
        while (elements2.hasMoreElements()) {
            final ag nextElement2 = elements2.nextElement();
            if (nextElement2 instanceof ag) {
                nextElement2.d();
            }
        }
        super.o = -1;
        super.p = -1;
        this.o = null;
        this.p = null;
        this.q = null;
        this.l = null;
        this.n = null;
        this.n = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.c();
        if (super.af != null || super.db) {
            final Frame frame = super.db ? null : super.af.b();
            if (super.da && super.e && super.j) {
                try {
                    final File file = new File(System.getProperty("user.home") + File.separator + "." + DigiChatAppletAbstract.OEM_DigiChat);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    final FileOutputStream fileOutputStream = new FileOutputStream(new File(file, this.getName()));
                    final Properties properties = new Properties();
                    ((Hashtable<String, String>)properties).put("buddylist.width", this.ak + "");
                    ((Hashtable<String, String>)properties).put("buddylist.height", this.al + "");
                    ((Hashtable<String, String>)properties).put("buddylist.displayHelp", this.ai + "");
                    properties.save(fileOutputStream, "");
                }
                catch (Exception ex) {}
            }
            if (!super.db) {
                super.af.a();
                super.af = null;
            }
            if (this.b != null) {
                this.b.setVisible(false);
            }
            this.b = null;
            if (frame != null) {
                frame.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex2) {}
        if (super.ae != null) {
            try {
                super.w.close();
                super.x.close();
                super.ae.close();
            }
            catch (Exception ex3) {}
        }
        super.ae = null;
        super.x = null;
        super.w = null;
        super.bb = true;
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
            case 33621777: {
                this.f(t);
                break;
            }
            case 66306: {
                this.h(t);
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
            case 67073: {
                this.j(t);
                break;
            }
            case 67074: {
                this.g(t);
                break;
            }
            case 67329: {
                this.k(t);
                break;
            }
            case 67330: {
                this.p(t);
                break;
            }
            case 33621776: {
                this.am(t);
                break;
            }
            case 67331: {
                this.m(t);
                break;
            }
            case 67333: {
                this.ai(t);
                break;
            }
            case 67334: {
                this.o(t);
                break;
            }
            case 67584: {
                this.c(t);
                break;
            }
            case 67586: {
                this.a(t);
                break;
            }
            case 67843: {
                this.n(t);
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
            case 33621775: {
                this.l(t);
                break;
            }
            case 50400768: {
                this.r(t);
                break;
            }
            case 50400769: {
                this.s(t);
                break;
            }
            case 50400770: {
                this.t(t);
                break;
            }
            case 50400772: {
                this.u(t);
                break;
            }
            case 50400788: {
                this.w(t);
                break;
            }
            case 50400789: {
                this.x(t);
                break;
            }
            case 50400790:
            case 50400792: {
                this.v(t);
                break;
            }
            case 50400778:
            case 50400779:
            case 50400780: {
                this.q(t);
                break;
            }
            case 50400782: {
                this.aj(t);
                break;
            }
            case 50400783: {
                this.ak(t);
                break;
            }
            case 50400781: {
                this.ad(t);
                break;
            }
            case 50400787: {
                this.y(t);
                break;
            }
        }
    }
    
    public final boolean e() {
        return super.t;
    }
    
    public abstract AudioClip b(final URL p0);
    
    public final Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public final Image a(final String s, final boolean b, final int n, final boolean b2) {
        try {
            URL url;
            if (b) {
                url = new URL(super.ba, "Resources/" + super.y + "/" + s);
            }
            else {
                url = new URL(super.ba, "Resources/" + s);
            }
            final Image a = this.a(url);
            if (a != null) {
                super.bd.addImage(a, b2 ? n : super.k);
                super.bd.statusID(b2 ? n : super.k, true);
                if (!b2) {
                    ++super.k;
                }
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public abstract Image a(final URL p0);
    
    public final void f() {
        if (this.k) {
            if (this.af == null) {
                this.af = this.a("videoIcon.gif", true);
            }
            if (this.af == null) {
                this.af = this.a("videoIcon.gif", false);
            }
            if (this.ag == null) {
                this.ag = this.a("videoInactiveIcon.gif", true);
            }
            if (this.ag == null) {
                this.ag = this.a("videoBusyIcon.gif", false);
            }
            if (this.ah == null) {
                this.ah = this.a("videoBroadcastIcon.gif", true);
            }
            if (this.ah == null) {
                this.ah = this.a("videoIcon.gif", false);
            }
            try {
                this.ae = this.b(new URL(super.ba, "Sounds/connected.au"));
            }
            catch (Exception ex) {
                this.ae = null;
            }
        }
    }
    
    public final Image a(final String s, final boolean b) {
        try {
            MediaTracker bd;
            if (super.bd == null) {
                final Button button;
                bd = new MediaTracker(button);
                button = new Button();
            }
            else {
                bd = super.bd;
            }
            final MediaTracker mediaTracker = bd;
            URL url;
            if (b) {
                url = new URL(super.ba, "Resources/" + super.y + "/" + s);
            }
            else {
                url = new URL(super.ba, "Resources/" + s);
            }
            if (!super.v) {
                Image a = this.a(url);
                if (a != null) {
                    final int n = super.k++;
                    mediaTracker.addImage(a, n);
                    try {
                        mediaTracker.waitForID(n);
                    }
                    catch (InterruptedException ex) {}
                    if (mediaTracker.isErrorID(n)) {
                        a = null;
                    }
                }
                return a;
            }
            if (com.diginet.digichat.util.c3.c > 65792) {
                try {
                    final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("HEAD");
                    final InputStream inputStream = httpURLConnection.getInputStream();
                    final String headerField = httpURLConnection.getHeaderField(0);
                    inputStream.close();
                    httpURLConnection.disconnect();
                    if (headerField.indexOf("200") == -1) {
                        return null;
                    }
                }
                catch (FileNotFoundException ex2) {
                    return null;
                }
                catch (ClassCastException ex3) {}
                catch (Exception ex4) {
                    return null;
                }
            }
            else {
                try {
                    final Class<?> forName = Class.forName("sun.net.www.protocol.http.HttpURLConnection");
                    final Constructor[] constructors = forName.getConstructors();
                    Object instance = new Object();
                    final Method[] methods = forName.getMethods();
                    final Method[] array = new Method[4];
                    for (int i = 0; i < constructors.length; ++i) {
                        final Constructor constructor = constructors[i];
                        final Class[] parameterTypes = constructor.getParameterTypes();
                        if (parameterTypes[0].equals(Class.forName("java.net.URL")) && parameterTypes[1].equals(Class.forName("java.lang.String")) && parameterTypes[2].toString().equals("int")) {
                            instance = constructor.newInstance(url, url.getHost(), new Integer(url.getPort()));
                            break;
                        }
                    }
                    for (int j = 0; j < methods.length; ++j) {
                        final Class<?>[] parameterTypes2 = methods[j].getParameterTypes();
                        if ("setRequestMethod".equals(methods[j].getName())) {
                            array[0] = methods[j];
                        }
                        else if ("getInputStream".equals(methods[j].getName())) {
                            array[1] = methods[j];
                        }
                        else if ("getHeaderField".equals(methods[j].getName()) && parameterTypes2[0].toString().equals("int")) {
                            array[2] = methods[j];
                        }
                        else if ("disconnect".equals(methods[j].getName())) {
                            array[3] = methods[j];
                        }
                        else if ("setRequestMethod".equals(array[0].getName()) && "getHeaderField".equals(array[1].getName()) && "getInputStream".equals(array[2].getName()) && "disconnect".equals(array[3].getName())) {
                            break;
                        }
                    }
                    for (int k = 0; k < array.length; ++k) {
                        Object[] array2 = { null };
                        switch (k) {
                            case 0: {
                                array2[0] = "HEAD";
                                try {
                                    array[k].invoke(instance, array2);
                                }
                                catch (Exception ex5) {}
                            }
                            case 1: {
                                array2 = null;
                                try {
                                    ((InputStream)array[k].invoke(instance, array2)).close();
                                }
                                catch (Exception ex6) {}
                            }
                            case 2: {
                                array2[0] = new Integer(0);
                                try {
                                    final String s2 = (String)array[k].invoke(instance, array2);
                                }
                                catch (Exception ex7) {}
                            }
                            case 3: {
                                final Object[] array3 = null;
                                try {
                                    array[k].invoke(instance, array3);
                                }
                                catch (Exception ex8) {}
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex9) {
                    return null;
                }
            }
            final Image a2 = this.a(url);
            if (a2 != null) {
                mediaTracker.addImage(a2, super.k);
                mediaTracker.statusID(super.k, true);
                ++super.k;
                return a2;
            }
            return null;
        }
        catch (MalformedURLException ex10) {
            return null;
        }
    }
    
    protected final void c(final URL url) {
        if (super.be == null && (com.diginet.digichat.util.c3.c >= 66048 || !com.diginet.digichat.util.c3.g)) {
            if (this == null) {
                throw null;
            }
            new Thread(new ao(this, url)).start();
        }
    }
    
    public abstract void a(final String p0, final String p1, final v p2, final String p3, final int p4) throws IOException;
    
    public final void a(final String s, final String s2, final v v, final URL ba, final int n, final String s3) throws IOException {
        try {
            super.bc = false;
            super.ap.c();
            String s4;
            if (DigiChatAppletAbstract.altHost != null) {
                s4 = DigiChatAppletAbstract.altHost;
            }
            else {
                s4 = ba.getHost();
            }
            super.ba = ba;
            this.g(s);
            this.c(super.ba);
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
                        super.ae = new Socket(s4, DigiChatAppletAbstract.preferredPort);
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
                catch (Exception ex9) {}
            }
            if (!b) {
                Exception ex = null;
                for (int n3 = 0; !b && n3 < com.diginet.digichat.common.b.a.size(); ++n3) {
                    final int a = com.diginet.digichat.common.b.a(n3);
                    try {
                        if (a != DigiChatAppletAbstract.preferredPort) {
                            if (a == com.diginet.digichat.common.b.c) {
                                if (com.diginet.digichat.common.b.d == "") {
                                    System.err.println("HttpServlet Address is not specified");
                                    throw ex;
                                }
                                super.ae = new ap(com.diginet.digichat.common.b.d);
                            }
                            else {
                                super.ae = new Socket(s4, a);
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
                    catch (Exception ex2) {
                        ex = ex2;
                        if (n3 == com.diginet.digichat.common.b.a.size() - 1) {
                            throw ex2;
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
                if (this.u(23)) {
                    t.f(0, 23);
                }
                if (this.s()) {
                    t.f(0, 3);
                }
                if (super.j && (!com.diginet.digichat.util.c3.e || com.diginet.digichat.util.c3.b != 1)) {
                    t.f(0, 21);
                    t.f(0, 20);
                }
                this.ap(t);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex3) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex3, this).setVisible(true);
        }
        catch (InterruptedIOException ex4) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex4, this).setVisible(true);
        }
        catch (NoRouteToHostException ex5) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex5, this).setVisible(true);
        }
        catch (SecurityException ex6) {
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex6, this).setVisible(true);
        }
        catch (IOException ex7) {
            ex7.printStackTrace();
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex7, this).setVisible(true);
        }
        catch (Exception ex8) {
            ex8.printStackTrace();
            this.c();
            new am(super.bg, StringSubst.Substitute(LanguageSupport.translate("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex8, this).setVisible(true);
        }
        super.g = false;
        super.h = false;
        super.i = false;
    }
    
    public void a(final URL url, final String s) {
    }
    
    public final void a(final int n) {
        if (super.be != null && n > 0 && n <= super.be.length && super.be[n - 1] != null) {
            try {
                super.be[n - 1].stop();
                super.be[n - 1].play();
            }
            catch (Throwable t) {}
        }
    }
    
    public final void a(final String s, final User user, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.a(s, user, n, n2, b, false, n3, n4, null);
    }
    
    public final void a(final String s, final User user, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a) {
        if (super.af != null || super.db) {
            int x = user.x();
            if (x == this.x()) {
                x = n;
            }
            user.getName();
            final int a2 = user.a;
            a1 a3;
            if (super.c8 && b && n == -1) {
                if (user.u(59)) {
                    if (super.a1 == null) {
                        super.a1 = new a1(-999, "answer.gif");
                        super.a1.a = this.a(super.a1.getName(), true, 40);
                    }
                    a3 = super.a1;
                }
                else {
                    if (super.a0 == null) {
                        super.a0 = new a1(-999, "question.gif");
                        super.a0.a = this.a(super.a0.getName(), true, 40);
                    }
                    a3 = super.a0;
                }
            }
            else {
                a3 = (a1)super.ai.d(a2);
            }
            final ChatMessage chatMessage = new ChatMessage(this.c(s), user, n != -1 && n != -3 && n != -2 && !b, a3);
            chatMessage.a(n == -3 || n == -2);
            chatMessage.a(n3, n4);
            if (b2) {
                chatMessage.g = a[0];
            }
            else {
                chatMessage.a = a;
            }
            if (chatMessage.p || user.u(66)) {
                final a7 a4 = user.u(66) ? ((bb)super.ag.d(x)) : ((a7)super.ag.d(x));
                if (a4 != null) {
                    a4.a(chatMessage);
                }
                else if (super.bp || super.db) {
                    this.a(chatMessage, user);
                }
                else {
                    super.af.a(chatMessage);
                }
            }
            else {
                if (super.db) {
                    return;
                }
                chatMessage.n = user.c;
                if (n == -3 || n == -2) {
                    this.a(chatMessage);
                }
                else {
                    super.af.a(chatMessage);
                    if (((a6)super.al.d(super.b)).b()) {
                        ++super.o;
                    }
                }
            }
        }
    }
    
    protected final void c(final t t) {
        this.t(t.m);
        this.b(t.a(0));
        this.a(7, !this.z);
        this.a(64, this.aa);
        this.a(65, !this.ab);
        super.y = t.c(0, 0);
        this.k = t.e(-1, 50);
        super.db = (t.e(-1, 46) && super.db);
        super.dc = super.db;
        com.diginet.digichat.client.h.a[0] = LanguageSupport.translate("Bass");
        com.diginet.digichat.client.h.a[1] = LanguageSupport.translate("Bell");
        com.diginet.digichat.client.h.a[2] = LanguageSupport.translate("Castanet");
        com.diginet.digichat.client.h.a[3] = LanguageSupport.translate("Chime");
        com.diginet.digichat.client.h.a[4] = LanguageSupport.translate("Conga");
        com.diginet.digichat.client.h.a[5] = LanguageSupport.translate("Cow Bell");
        com.diginet.digichat.client.h.a[6] = LanguageSupport.translate("Double Bell");
        com.diginet.digichat.client.h.a[7] = LanguageSupport.translate("Drum Roll");
        com.diginet.digichat.client.h.a[8] = LanguageSupport.translate("Harp");
        if (!"Admin".equals(super.y)) {
            if (!super.db) {
                if (super.df.getBackground()) {
                    super.df.backgroundImage = this.a(super.df.getFullDirectory() + "background.gif", true);
                }
                if (super.df.getChatBackground()) {
                    super.df.chatBackgroundImage = this.a(super.df.getFullDirectory() + "chatbackground.gif", true);
                }
                if (super.e && super.j) {
                    try {
                        this.j();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (DigiChatAppletAbstract.embedded) {
                    (super.af = new bh(this, null, false)).setVisible(false);
                    DigiChatAppletAbstract.login.setVisible(false);
                    DigiChatAppletAbstract.applet.add(super.af.c());
                    ((bh)super.af).validate();
                }
                else {
                    super.af = (x)new b4(this).a();
                }
            }
            else {
                ((DigiChatApplet)DigiChatAppletAbstract.applet).showMessagesStatus();
            }
            this.f();
        }
    }
    
    protected void a(final t t) {
        super.g = true;
        super.h = true;
        super.b = -999;
        this.f();
        if (super.ai.d(super.cc) == null) {
            this.b(super.bi);
        }
        else {
            this.b(super.cc);
        }
        final bf bf = (bf)super.al.d(super.bj);
        final bf bf2 = (bf)super.al.d(super.bh);
        if (super.db) {
            this.a((bf)super.al.d(super.bk));
        }
        else if (bf == null || (bf.k == null && bf2.k != null)) {
            this.a(bf2);
        }
        else {
            this.a(bf);
        }
        if (super.af != null && ((av)super.af).f() != null) {
            ((av)super.af).f().d();
        }
    }
    
    protected final void g() {
        if (!com.diginet.digichat.common.d.a) {
            return;
        }
        if (!super.g && !super.h) {
            if (super.af != null) {
                ((av)super.af).f().d();
            }
            int b;
            if (super.al.d(super.bj) == null) {
                b = super.bh;
            }
            else {
                b = super.bj;
            }
            super.h = true;
            final bf bf = (bf)super.al.d(b);
            if (bf != null && bf.c && bf.k != null) {
                super.b = -999;
                new ax(super.db ? new Frame() : super.af.b(), this, bf).setVisible(true);
            }
            super.b = b;
        }
    }
    
    protected final void a(final User2 user2, final int n, final long n2, final long n3, final int n4) {
        final int b = user2.b;
        final bf bf = (bf)super.al.d(n);
        final bf bf2 = (bf)super.al.d(user2.b);
        this.u(26);
        this.g();
        if (user2.x() == this.x()) {
            if (bf.u(44)) {}
            if (bf2 != null) {
                bf2.b = false;
            }
            bf.b = true;
            final bg bg = (bg)super.ak.d(bf.e);
            if (bf2 != null && bf.e != bf2.e) {
                final bg bg2 = (bg)super.ak.d(bf2.e);
                if (bg2 != null) {
                    bg2.c = false;
                }
            }
            if (bg != null) {
                bg.c = true;
            }
            super.b = n;
            if (super.af != null) {
                if (bf2 != null && (bf2.b() || bf.b())) {
                    this.a(bf2, bf);
                }
                if (bf.a() && bf.b()) {
                    this.v();
                }
                super.af.b(bf);
                if (bf.u(53)) {
                    super.af.b(false);
                }
                else {
                    super.af.b(true);
                }
                if (super.c4) {
                    if (n2 > 0L) {
                        super.af.a(n2);
                    }
                    else if (bf.u(53)) {
                        super.af.a("Closed");
                    }
                    else {
                        super.af.a("Open");
                    }
                }
                if (bf.a() && super.c7) {
                    super.af.c(true);
                }
                else if (!bf.a() && super.c7) {
                    super.af.c(false);
                }
                if (!super.bc) {
                    this.c(false);
                }
                if (!super.af.isVisible()) {
                    super.af.setVisible(true);
                }
                super.af.c().validate();
            }
        }
        else if (bf.u(44)) {}
        if (bf != null) {
            bf.c = this.p(bf.x());
            if (bf2 != null && bf != null) {
                if (bf2.x() != bf.x() && (!user2.u(23) || this.u(24))) {
                    final bf bf3 = bf;
                    ++bf3.c;
                }
            }
            else if (bf2 == null && bf != null && (!user2.u(23) || this.u(24))) {
                final bf bf4 = bf;
                ++bf4.c;
            }
            if (super.af != null) {
                super.af.c(bf);
            }
        }
        if (bf2 != null) {
            bf2.c = this.p(bf2.x());
            if (bf2 != null && bf != null && bf2.x() != bf.x() && (!user2.u(23) || this.u(24))) {
                final bf bf5 = bf2;
                --bf5.c;
            }
            if (super.af != null) {
                super.af.c(bf2);
            }
        }
        if (bf2 != null && bf.e != bf2.e && super.af != null) {
            super.af.a((bg)super.ak.d(1000));
        }
        if (n == super.b && user2.b != n) {
            if (bf != null && !super.db && !bf.u(57) && (!user2.u(23) || this.u(24))) {
                final ChatMessage chatMessage = new ChatMessage(StringSubst.Substitute(LanguageSupport.translate("(This user has entered %1)"), new String[] { this.c(bf.getName()) }), user2, false, (a1)super.ai.d(user2.a));
                chatMessage.a(n3, n4);
                this.a(super.cj);
                if (super.af != null) {
                    super.af.a(chatMessage);
                }
            }
        }
        else if (user2.b == super.b && n != super.b && bf2 != null && bf != null && !super.db && !bf2.u(57) && (!user2.u(23) || this.u(24))) {
            final ChatMessage chatMessage2 = new ChatMessage(StringSubst.Substitute(LanguageSupport.translate("(This user has moved to %1)"), new String[] { this.c(bf.getName()) }), user2, false, (a1)super.ai.d(user2.a));
            chatMessage2.a(n3, n4);
            this.a(super.cn);
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
            this.a(user2, false, false);
        }
    }
    
    protected final void d(final t t) {
        this.u(26);
        this.g();
        for (int i = 0; i < t.c(); ++i) {
            final User2 user2 = (User2)super.aj.d(t.a(i, 0));
            if (user2 != null) {
                final bf bf = (bf)super.al.d(user2.b);
                final az az = new az(user2.x(), user2.getName());
                az.a = false;
                if (bf != null) {
                    if (!user2.u(23) || this.u(24)) {
                        final bf bf2 = bf;
                        --bf2.c;
                    }
                    if (super.af != null && super.ak.b() <= 1) {
                        super.af.c(bf);
                    }
                    if (user2.b == super.b && !bf.u(57) && !super.db && (!user2.u(23) || this.u(24))) {
                        final String c = t.c(i, 0);
                        String s;
                        if (c == null) {
                            s = StringSubst.Substitute(LanguageSupport.translate("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        else {
                            s = this.c(c) + " " + StringSubst.Substitute(LanguageSupport.translate("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        final ChatMessage chatMessage = new ChatMessage(s, user2, false, (a1)super.ai.d(user2.a));
                        chatMessage.a(t.i(), t.h());
                        this.a(super.cn);
                        if (super.af != null) {
                            super.af.a(chatMessage);
                        }
                    }
                }
                if (super.af != null) {
                    super.af.a(user2);
                    ((av)super.af).a(az, false, false);
                }
                final a7 a7 = (a7)super.ag.d(user2.x());
                if (a7 != null) {
                    a7.dispose();
                }
                if (this.aj && this.k) {
                    this.l(user2.x());
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
    
    public final void h() {
        if (super.da && super.af != null) {
            final Frame b = super.af.b();
            if (b != null) {
                this.ak = b.size().width;
                this.al = b.size().height;
            }
        }
        super.h();
    }
    
    protected final void e(final t t) {
        for (int i = 0; i < t.c(); ++i) {
            if (t.d() != null) {
                this.a(t.d());
            }
            User user = (User)super.aj.d(t.a(i, 0));
            if (user == null && this.s() && t.e(0, 3)) {
                user = new User(-999, t.c(0, 1));
                user.a = t.a(0, 1);
            }
            if (user == null) {
                user = new User(-999, t.c(0, 1));
                user.a = super.bi;
            }
            if (user != null) {
                int n = user.x();
                if (n == this.x()) {
                    n = t.m;
                }
                if (!user.d) {
                    switch (t.m) {
                        case -3:
                        case -2:
                        case -1: {
                            if (user.c && !super.da && !super.db) {
                                this.a(super.cl);
                            }
                            else if (!super.da && !super.db) {
                                this.a(super.ck);
                            }
                            break;
                        }
                        default: {
                            if (super.ag.a(n)) {
                                this.a(super.ck);
                            }
                            else {
                                this.a(super.cm);
                            }
                            break;
                        }
                    }
                    if (t.d() != null) {
                        this.a(t.c(i, 0), user, t.m, t.l, true, false, t.i(), t.h(), t.d());
                    }
                    else {
                        this.a(t.c(i, 0), user, t.m, t.l, true, t.i(), t.h());
                    }
                }
            }
        }
    }
    
    protected final void f(final t t) {
        if (super.c7 && super.af != null) {
            super.af.a(t.a(0, 0), t.a(0, 1));
        }
    }
    
    protected final void g(final t t) {
        final User user = (User)super.aj.d(t.a(0, 0));
        if (user != null) {
            final t t2 = new t(67073, 1);
            t2.m = user.x();
            t2.a(0, 0, this.x());
            if (!super.cv || user.u(39)) {
                t2.a(0, 1, super.cg);
                if (super.bt != null && super.bt.length() > 0) {
                    t2.a(0, 0, super.bt);
                }
                if (super.by != null && super.by.length() > 0) {
                    t2.a(0, 1, super.by);
                }
                if (super.bu != null && super.bu.length() > 0) {
                    t2.a(0, 2, super.bu);
                }
                if (super.bw != null && super.bw.length() > 0) {
                    t2.a(0, 3, super.bw);
                }
                if (super.bv != null && super.bv.length() > 0) {
                    t2.a(0, 4, super.bv);
                }
                if (super.ch != -999) {
                    t2.f(0, super.ch);
                }
                if (this.h.size() > 0) {
                    try {
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        this.h.save(byteArrayOutputStream, "client data");
                        byteArrayOutputStream.flush();
                        t2.a(byteArrayOutputStream.toByteArray());
                    }
                    catch (Exception ex) {}
                }
            }
            this.ap(t2);
        }
    }
    
    protected final void h(final t t) {
        this.u(26);
        if (super.af != null) {
            for (int i = 0; i < t.c(); ++i) {
                final ChatMessage chatMessage = new ChatMessage(this.c(t.c(i, 1)), this.c(t.c(i, 0)), false, (a1)super.ai.d(t.a(i, 0)), -999, false, false);
                chatMessage.a(t.i(), t.h());
                super.af.a(chatMessage);
                if (t.m == this.x()) {
                    if (!super.da) {
                        this.a(super.cm);
                    }
                    else if (!super.da) {
                        this.a(super.ck);
                    }
                }
            }
        }
    }
    
    protected final void i(final t t) {
        this.g();
        for (int i = 0; i < t.c(); ++i) {
            User user = (User)super.aj.d(t.a(i, 0));
            if (user == null && this.s() && t.e(0, 3)) {
                user = new User(-999, t.c(0, 1));
                user.a = t.a(0, 1);
            }
            if (user != null) {
                int n = user.x();
                if (n == this.x()) {
                    n = t.m;
                }
                if (t.m != this.x() && t.e(0, 20)) {
                    return;
                }
                if (t.c(i, 0) == null) {
                    return;
                }
                if (!user.d) {
                    if (!t.o) {
                        switch (t.m) {
                            case -3:
                            case -2:
                            case -1: {
                                if (user.c && !super.da && !super.db) {
                                    this.a(super.cl);
                                }
                                else if (!super.da && !super.db) {
                                    this.a(super.ck);
                                }
                                break;
                            }
                            default: {
                                if (super.ag.a(n)) {
                                    this.a(super.ck);
                                }
                                else {
                                    this.a(super.cm);
                                }
                                break;
                            }
                        }
                    }
                    if (t.d() != null) {
                        this.a(t.c(i, 0), user, t.m, t.l, false, t.e(0, 20), t.i(), t.h(), t.d());
                    }
                    else {
                        this.a(t.c(i, 0), user, t.m, t.l, false, t.i(), t.h());
                    }
                }
            }
        }
    }
    
    protected final void j(final t t) {
        if (this.k && this.j != null && this.j.a(t)) {
            return;
        }
        for (int i = 0; i < t.c(); ++i) {
            final User user = (User)super.aj.d(t.a(i, 0));
            if (user != null) {
                if (super.cy) {
                    final String c = t.c(i, 2);
                    if (c != null) {
                        try {
                            this.a(new URL(c), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    final bd bd = new bd(super.db ? new Frame() : super.af.b(), this, user, t, i);
                }
            }
        }
    }
    
    protected final void k(final t t) {
        super.a8 = t.a(-1);
        super.am.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                be be = (be)super.am.d(a);
                if (t.e(i, 63)) {
                    if (be != null) {
                        super.am.f(a);
                    }
                }
                else {
                    if (be == null) {
                        be = new be(a, t.c(i, 0));
                        super.am.a(be);
                    }
                    else {
                        be.g(t.c(i, 0));
                    }
                    be.d = t.a(i, 1);
                    be.e = t.a(i, 2);
                    be.b = t.c(i, 1);
                    be.a = t.c(i, 2);
                    be.c = t.c(i, 3);
                    be.b(t.a(i));
                    be.f = this.a("banners/" + be.a, true, 30);
                    if (super.af != null) {
                        ((av)super.af).a(be);
                    }
                }
            }
        }
        finally {
            super.am.a();
        }
    }
    
    protected final void l(final t t) {
        for (int c = t.c(), i = 0; i < c; ++i) {
            final b5 b5 = new b5(t.a(i, 0), t.c(i, 0));
            b5.b(t.a(i));
            b5.c = t.c(i, 1);
            if (!b5.u(63)) {
                b5.b = this.a("emoticons/" + b5.c, true, 50);
            }
            com.diginet.digichat.common.b5.a(b5);
        }
    }
    
    protected final void m(final t t) {
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
                    a2.a = this.a("userIcons/" + a2.getName(), true, 40);
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
    
    protected final void n(final t t) {
        super.b1 = t.c(0, 0);
        super.b2 = t.c(0, 1);
        super.b6 = t.c(0, 2);
        super.a5 = t.a(0);
        super.co = t.a(0, 0);
        super.cp = t.a(0, 1);
        super.cq = t.a(0, 2);
        super.cr = t.a(0, 3);
        super.cs = t.a(0, 4);
        super.ct = t.a(0, 5);
        if (super.af != null) {
            ((av)super.af).b(super.b1);
            if (((av)super.af).f() != null) {
                ((av)super.af).f().b((super.cp > 0) ? super.cp : 7);
            }
        }
    }
    
    protected final void o(final t t) {
        this.g();
        super.aj.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                User2 user2 = (User2)super.aj.d(a);
                if (t.e(i, 63)) {
                    if (user2 != null) {
                        final bf bf = (bf)super.al.d(t.a(i, 2));
                        if (bf != null) {
                            if (!user2.u(23) || this.u(24)) {
                                final bf bf2 = bf;
                                --bf2.c;
                            }
                            if (super.af != null && super.ak.b() <= 1) {
                                super.af.c(bf);
                            }
                        }
                        final az az = new az(user2.x(), user2.getName());
                        az.a = false;
                        super.aj.f(a);
                        if (super.af != null) {
                            super.af.a(user2);
                            ((av)super.af).a(az, false, false);
                        }
                        final a7 a2 = (a7)super.ag.d(user2.x());
                        if (a2 != null) {
                            a2.dispose();
                        }
                    }
                }
                else {
                    final String c = this.c(t.c(i, 0));
                    String name = null;
                    if (user2 == null) {
                        user2 = new User2(a, c, this.k, t.e(i, 64));
                        user2.b = this.af;
                        user2.c = this.ag;
                        user2.d = this.ah;
                        if (!user2.u(23) || this.u(24)) {
                            super.aj.a(user2);
                        }
                    }
                    else {
                        name = user2.getName();
                        user2.g(c);
                    }
                    user2.a = t.a(i, 1);
                    user2.a = (a1)super.ai.d(user2.a);
                    user2.b = t.a(i, 2);
                    user2.b(t.a(i));
                    user2.a(7, this.k);
                    user2.a(6, !this.k);
                    if (user2.u(5)) {
                        user2.e = super.df.available;
                    }
                    else {
                        user2.e = super.df.unavailable;
                    }
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
                    this.a(user2, name, false, false);
                    if (super.af != null && user2.x() == this.x()) {
                        ((av)super.af).b(user2);
                    }
                }
            }
        }
        finally {
            super.aj.a();
        }
    }
    
    protected final void p(final t t) {
        super.al.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                final bf bf = (bf)super.al.d(a);
                if ((!t.e(i, 63) || t.c(i, 0) != null) && bf == null) {
                    String c = t.c(i, 0);
                    if (c == null) {
                        System.err.println("null room name received.");
                        c = new String("no name");
                    }
                    final bf bf2 = new bf(a, this.c(c));
                    bf2.a = this.ah;
                    super.al.a(bf2);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            super.al.a();
        }
        super.p(t);
    }
    
    protected final void a(final User2 user2, final boolean b, final boolean b2) {
        this.a(user2, null, b, b2);
    }
    
    protected final void a(final User2 user2, final String s, final boolean b, final boolean b2) {
        az az;
        if (s != null && !user2.getName().equals(s)) {
            az = new az(user2.x(), s);
            az.a = false;
        }
        else {
            az = new az(user2.x(), user2.getName());
            az.a = true;
            az.a = user2.a;
            az.a = user2.a;
            az.b = user2.b;
            if (az.b != -999) {
                az.b = (bf)super.al.d(az.b);
            }
            az.b(user2.z());
            az.e = user2.e;
            az.b = this.af;
            az.c = this.ag;
            az.d = this.ah;
        }
        boolean a = false;
        if (super.af != null) {
            a = ((av)super.af).a(az, b, false);
        }
        if (b2) {
            this.b(az.getName(), a);
        }
    }
    
    protected final void i() throws Exception {
        if (super.e && super.j && super.bo != null && super.bo.length() > 0) {
            final File file = new File(System.getProperty("user.home") + File.separator + "." + DigiChatAppletAbstract.OEM_DigiChat);
            if (!file.exists()) {
                file.mkdirs();
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(new File(file, super.ce + "_" + this.getName()));
            final StringTokenizer stringTokenizer = new StringTokenizer(super.bo, ",");
            final Properties properties = new Properties();
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                ((Hashtable<String, String>)properties).put(n + "", stringTokenizer.nextToken());
                ++n;
            }
            properties.save(fileOutputStream, "");
        }
    }
    
    protected final void j() throws Exception {
        if (super.e && super.j) {
            final File file = new File(System.getProperty("user.home") + File.separator + "." + DigiChatAppletAbstract.OEM_DigiChat + File.separator + super.ce + "_" + this.getName());
            if (file.exists()) {
                final FileInputStream fileInputStream = new FileInputStream(file);
                final Properties properties = new Properties();
                properties.load(fileInputStream);
                String bo = "";
                final Enumeration<Object> keys = ((Hashtable<Object, V>)properties).keys();
                if (keys.hasMoreElements()) {
                    bo += ((Hashtable<K, String>)properties).get(keys.nextElement());
                }
                while (keys.hasMoreElements()) {
                    bo = bo + "," + ((Hashtable<K, String>)properties).get(keys.nextElement());
                }
                if (bo.length() > 0) {
                    super.bo = bo;
                }
            }
        }
    }
    
    protected final void q(final t t) {
        if (t.b() == 50400778) {
            final int a = t.a(0, 0);
            final int a2 = t.a(0, 1);
            if (a == this.x()) {
                ad ad = (ad)super.ag.d(a2);
                if (ad == null) {
                    ad = new bb(this, a2);
                }
                if (ad instanceof bb) {
                    if (((User2)super.aj.d(a2)).u(5)) {
                        ((bb)ad).i();
                    }
                    else {
                        ((bb)ad).j();
                    }
                }
                super.ag.a(ad, a2);
            }
            else {
                if (!this.u(5)) {
                    return;
                }
                if (((User)super.aj.d(a)).d) {
                    this.b(a, "The session with %1 has been declined");
                    return;
                }
                ad ad2 = (ad)super.ag.d(a);
                if (ad2 == null) {
                    ad2 = new bb(this, a);
                }
                if (ad2 instanceof bb) {
                    try {
                        ((bb)ad2).b(super.ab);
                    }
                    catch (IllegalArgumentException ex) {
                        System.out.println("Error Creating Messages Prompt: " + ex.getMessage());
                        ((bb)ad2).b("ADFBV");
                    }
                }
                super.ag.a(ad2, a);
            }
        }
        else if (t.b() == 50400779) {
            if (super.aj.d(t.a(0, 0)) == null) {
                final am am = new am(new Frame(), LanguageSupport.translate("Notice"), StringSubst.Substitute(LanguageSupport.translate(t.c(0, 0)), new String[] { LanguageSupport.translate("The target user") }), this);
                am.setModal(true);
                am.setVisible(true);
                return;
            }
            final ad ad3 = (ad)super.ag.d(t.a(0, 0));
            if (ad3 == null) {
                return;
            }
            super.ag.f(t.a(0, 0));
            if (!(ad3 instanceof bb)) {
                ad3.dispose();
                return;
            }
            final am am2 = new am(new Frame(), LanguageSupport.translate("Notice"), (super.aj.d(t.a(0, 0)) == null) ? StringSubst.Substitute(LanguageSupport.translate(t.c(0, 0)), new String[] { LanguageSupport.translate("The target user") }) : StringSubst.Substitute(LanguageSupport.translate(t.c(0, 0)), new String[] { ((User)super.aj.d(t.a(0, 0))).getName() }), this);
            am2.setModal(false);
            am2.setVisible(true);
            ((bb)ad3).dispose();
        }
        else if (t.b() == 50400780) {
            final ad ad4 = (ad)super.ag.d(t.a(0, 0));
            if (ad4 == null || !(ad4 instanceof bb)) {
                return;
            }
            ((bb)ad4).k();
        }
    }
    
    public final void a(final ChatMessage chatMessage, final User user) {
        if (user.x() == this.x()) {
            return;
        }
        final int x = user.x();
        if (((a6)super.al.d(user.b)).a() && user.u(59) && !super.bm) {
            return;
        }
        ad ad = (ad)super.ag.d(x);
        if (ad == null && !super.db && !user.u(66)) {
            ad = new a7(super.af.b(), this, x);
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
            if (n > 375) {
                n = 375;
            }
            if (n2 > 375) {
                n2 = 375;
            }
            ad.reshape(2 + n4, 10 + n3, n, n2);
            ad.setVisible(true);
            super.ag.a(ad, x);
        }
        else if (super.db || user.u(66)) {
            ad = new bb(this, x);
            if (!ad.isVisible()) {
                return;
            }
            final Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
            int n5 = screenSize2.width / 2 - 20;
            int n6 = screenSize2.height / 2 - 20;
            int n7 = 0;
            int n8 = 0;
            switch (super.ag.b() % 4) {
                case 0: {
                    n7 = 0;
                    n8 = 0;
                    break;
                }
                case 1: {
                    n7 = 0;
                    n8 = n5 + 10;
                    break;
                }
                case 2: {
                    n7 = n6 + 10;
                    n8 = 0;
                    break;
                }
                default: {
                    n7 = n6 + 10;
                    n8 = n5 + 10;
                    break;
                }
            }
            if (n5 > 375) {
                n5 = 375;
            }
            if (n6 > 375) {
                n6 = 375;
            }
            ad.reshape(2 + n8, 10 + n7, n5, n6);
            super.ag.a(ad, x);
        }
        if (chatMessage != null) {
            ad.a(chatMessage);
        }
    }
    
    public final void b(final int n) {
        final t t = new t(67334, 1);
        t.a(0, 0, this.x());
        t.a(0, 1, n);
        t.a(0, 0, this.getName());
        t.a(0, this.z());
        t.m = -1;
        t.l = -1;
        this.ap(t);
    }
    
    public final void a(final boolean t) {
        super.t = t;
    }
    
    public final void c(final int n) {
        if (super.af.b() != null) {
            super.af.b().setCursor(3);
        }
        if (this.b == null) {
            this.b = new ah(super.db ? new Frame() : super.af.b(), this);
        }
        if (n >= 0) {
            this.b.a(n);
        }
        if (this.b.isVisible()) {
            this.b.toFront();
        }
        else {
            this.b.setVisible(true);
        }
        if (super.af.b() != null) {
            super.af.b().setCursor(0);
        }
    }
    
    public final void k() {
        if (super.af.b() != null) {
            super.af.e();
        }
    }
    
    public final void b(final String s, final boolean b) {
        if (super.af.b() != null) {
            super.af.b().setCursor(3);
            String s2;
            if (b) {
                s2 = StringSubst.Substitute(LanguageSupport.translate("%1 is already in buddy list."), new String[] { s });
            }
            else {
                s2 = StringSubst.Substitute(LanguageSupport.translate("Buddy %1 has been added."), new String[] { s });
            }
            new am(super.af.b(), LanguageSupport.translate("Add Buddy Status"), s2, this).setVisible(true);
            super.af.b().setCursor(0);
        }
    }
    
    public final boolean a(final ag ag) {
        if (this.d.get(ag.c()) == null) {
            this.d.put(ag.c(), ag);
            return true;
        }
        return false;
    }
    
    public final void a(final User user) {
        this.d.remove(user);
    }
    
    public final int b(final ag ag) {
        this.a(ag.c());
        if (super.aj.d(ag.c().x()) == null) {
            ag.d();
        }
        final byte b = (byte)new Random().nextInt();
        final byte b2 = (b < 0) ? ((byte)(-b)) : b;
        final t t = new t(50400768, 1);
        t.a(0, 0, this.x());
        t.a(0, 1, b2);
        t.a(0, 0, ag.a());
        t.a(0, 1, ag.b());
        t.m = ag.c().x();
        t.l = -1;
        this.ap(t);
        final t t2 = new t(66305, 1);
        t2.a(0, 0, "[File Transfer Requested: file name " + ag.a() + ", size " + this.a(ag.b()) + ". Please click this message to accept.]");
        t2.a(0, 0, this.x());
        t2.f(0, 20);
        t2.a(new byte[] { b2 });
        t2.m = ag.c().x();
        t2.l = -1;
        this.ap(t2);
        this.c.put(new Integer(b2), ag);
        return b2;
    }
    
    protected final void r(final t t) {
        this.c.put(new Integer(t.a(0, 1)), new b6((User)super.aj.d(t.a(0, 0)), t.c(0, 0), t.c(0, 1), t.a(0, 1)));
    }
    
    protected final void s(final t t) {
        synchronized (this.c) {
            final ag remove = this.c.remove(new Integer(t.a(0, 1)));
            if (remove instanceof ag) {
                remove.d();
            }
        }
        // monitorexit(this.c)
    }
    
    public final void d(final int n) {
        synchronized (this.c) {
            final b6 value = this.c.get(new Integer(n));
            if (value == null) {
                // monitorexit(this.c)
                return;
            }
            if (value instanceof b6) {
                if (super.aj.d(value.a.x()) == null) {
                    this.c.remove(new Integer(n));
                }
                else {
                    this.c.put(new Integer(n), com.diginet.digichat.util.filetransfer.cb.a(this, value));
                }
            }
        }
        // monitorexit(this.c)
    }
    
    public final void a(final int n, final User user) {
        final t t = new t(50400770, 1);
        t.a(0, 0, this.x());
        t.a(0, 1, n);
        t.m = user.x();
        t.l = -1;
        this.ap(t);
    }
    
    protected final void t(final t t) {
        final ag ag = this.c.get(new Integer(t.a(0, 1)));
        if (ag == null) {
            return;
        }
        ag.e();
    }
    
    public final void e(final int n) {
        final ag ag = this.c.remove(new Integer(n));
        if (ag == null) {
            return;
        }
        final t t = new t(50400769, 1);
        t.a(0, 0, this.x());
        t.a(0, 1, n);
        t.m = ag.c().x();
        t.l = -1;
        this.ap(t);
        final t t2 = new t(66305, 1);
        t2.a(0, 0, "[File Transfer Cancelled: file name " + ag.a() + ", size " + this.a(ag.b()) + ".]");
        t2.a(0, 0, this.x());
        t2.f(0, 20);
        t2.a(new byte[] { (byte)n });
        t2.m = ag.c().x();
        t2.l = -1;
        this.ap(t2);
    }
    
    public final String a(final String s) {
        if (s.length() < 4) {
            return s + " bytes";
        }
        try {
            double n = Long.parseLong(s) / 1024L;
            boolean b = false;
            if (n > 1024.0) {
                n /= 1024.0;
                b = true;
            }
            String s2 = Math.floor(n * 1000.0 + 5.0) / 1000.0 + "";
            final int index = s2.indexOf(46);
            final int n2 = s2.length() - 1;
            if (index < 0) {
                s2 += ".00";
            }
            else if (index == 0) {
                s2 = "0" + s2;
            }
            else if (index == n2) {
                s2 += "00";
            }
            else if (index == n2 - 1) {
                s2 += "0";
            }
            else if (index + 2 < n2) {
                s2 = s2.substring(0, index + 3);
            }
            return s2 + (b ? " MB" : " KB");
        }
        catch (NumberFormatException ex) {
            return s + " bytes";
        }
    }
    
    public final boolean a(final Event event) {
        return this.a(event, true);
    }
    
    protected final boolean a(final Event event, final boolean b) {
        if (this.k && this.g.containsKey(((MenuItem)event.target).getLabel())) {
            final String label = ((MenuItem)event.target).getLabel();
            this.a(label, (String)this.g.get(label));
            return true;
        }
        return !b || super.a(event);
    }
    
    public final void a(final Menu menu) {
        if (!this.k) {
            return;
        }
        final Enumeration<String> keys = this.g.keys();
        while (keys.hasMoreElements()) {
            menu.add(new MenuItem(keys.nextElement()));
        }
        if (this.g.size() > 0) {
            menu.addSeparator();
        }
    }
    
    public final void a(final String s, final String s2) {
        if (!this.k) {
            return;
        }
        if (this.j == null) {
            this.j = new bc(this);
        }
        this.j.a(s, s2, null, false);
    }
    
    public final void u(final t t) {
        if (!this.k) {
            return;
        }
        if (this.j == null) {
            this.j = new bc(this);
        }
        this.j.b(t);
    }
    
    protected final void v(final t t) {
        switch (t.b()) {
            case 50400792: {
                this.o = ((t.c(0, 0) != null) ? (t.c(0, 0).equals("null") ? "" : t.c(0, 0)) : "");
                this.p = ((t.c(0, 1) != null) ? (t.c(0, 1).equals("null") ? "" : t.c(0, 1)) : "");
                this.q = ((t.c(0, 2) != null) ? (t.c(0, 2).equals("null") ? "" : t.c(0, 2)) : "");
                this.v = ((t.c(0, 3) != null) ? (t.c(0, 3).equals("null") ? "" : t.c(0, 3)) : "");
                this.w = ((t.c(0, 4) != null) ? (t.c(0, 4).equals("null") ? "" : t.c(0, 4)) : "");
                this.x = ((t.c(0, 5) != null) ? (t.c(0, 5).equals("null") ? "" : t.c(0, 5)) : "");
                this.y = ((t.c(0, 6) != null) ? (t.c(0, 6).equals("null") ? "" : t.c(0, 6)) : "");
                t.a(super.a5, 43, t.e(0, 43));
                break;
            }
            case 50400790: {
                this.l = ((t.c(0, 0) != null) ? (t.c(0, 0).equals("null") ? "" : t.c(0, 0)) : "");
                this.m = ((t.c(0, 1) != null) ? (t.c(0, 1).equals("null") ? "" : t.c(0, 1)) : "");
                this.n = ((t.c(0, 2) != null) ? (t.c(0, 2).equals("null") ? "" : t.c(0, 2)) : "");
                this.r = ((t.c(0, 3) != null) ? (t.c(0, 3).equals("null") ? "" : t.c(0, 3)) : "");
                this.s = ((t.c(0, 4) != null) ? (t.c(0, 4).equals("null") ? "" : t.c(0, 4)) : "");
                this.t = ((t.c(0, 5) != null) ? (t.c(0, 5).equals("null") ? "" : t.c(0, 5)) : "");
                this.u = ((t.c(0, 6) != null) ? (t.c(0, 6).equals("null") ? "" : t.c(0, 6)) : "");
                break;
            }
        }
    }
    
    protected final void w(final t t) {
        final int a = t.a(0, 0);
        final User user = (User)super.aj.d(a);
        if (this.e.get(user) != null) {
            return;
        }
        if (this.f.get(user) != null && this.b(user.getName())) {
            this.j(a);
            if (this.b(user.getName())) {
                return;
            }
        }
        this.a(super.cm);
        final am am = new am((super.af == null) ? new Frame() : super.af.b(), LanguageSupport.translate("Incoming Chat Request"), new String[] { LanguageSupport.translate("Ok"), LanguageSupport.translate("Cancel") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("%1 is requesting an AV chat.To accept this request click OK, to reject this request click Cancel. (In order for DigiChat AV to work properly, all popup blockers must be disabled.)"), new String[] { user.getName() }) }, new b8(this, a), this);
        am.setModal(false);
        am.setVisible(!t.a(super.a5, 43));
        this.e.put(user, am);
        if (t.a(super.a5, 43)) {
            am.c();
        }
    }
    
    protected final void x(final t t) {
        final int a = t.a(0, 1);
        final User user = (User)super.aj.d(a);
        final am am = this.e.remove(user);
        if (am != null) {
            am.dispose();
            am.setVisible(false);
        }
        final User user2 = (User)super.aj.d(a);
        if (t.a(0, 0) == 0) {
            new am((super.af == null) ? new Frame() : super.af.b(), LanguageSupport.translate("Chat Invitation Declined"), new String[] { LanguageSupport.translate("Ok") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("%1 declined your AV chat invitation."), new String[] { user2.getName() }) }, this.m()).setVisible(true);
            return;
        }
        this.f.put(user, user);
        if (!t.a(super.a5, 43)) {
            new am((super.af == null) ? new Frame() : super.af.b(), StringSubst.Substitute(LanguageSupport.translate("Chat Invitation Accepted."), new String[] { user2.getName() }), new String[] { LanguageSupport.translate("Ok") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("%1 has accepted your AV chat invitation"), new String[] { user2.getName() }) }, this.m()).setVisible(true);
        }
        this.a(user2.getName(), a, true);
    }
    
    protected final void a(final String s, final int n, final boolean b) {
        try {
            final String s2 = new String();
            if (this.l() == null || "".equals(this.l())) {
                new am((super.af == null) ? new Frame() : super.af.b(), StringSubst.Substitute(LanguageSupport.translate(""), new String[0]), new String[] { LanguageSupport.translate("OK") }, new String[] { LanguageSupport.translate("This Chat Site is not configured for AV conversation, please contact the site's administrator for support") }, this.m()).setVisible(true);
                return;
            }
            final String string = this.l() + "?uID=" + URLEncoder.encode(this.x() + "", "UTF-8");
            String s3;
            if (b) {
                if (this.a.getParameter("arabicVideoSupport") != null) {
                    s3 = string + "&user1=" + URLEncoder.encode(this.x() + "", "UTF-8") + "&user2=" + URLEncoder.encode(n + "", "UTF-8");
                }
                else {
                    s3 = string + "&user1=" + URLEncoder.encode(this.getName(), "UTF-16") + "&user2=" + URLEncoder.encode(s, "UTF-16");
                }
            }
            else if (this.a.getParameter("arabicVideoSupport") != null) {
                s3 = string + "&user1=" + URLEncoder.encode(n + "", "UTF-8") + "&user2=" + URLEncoder.encode(this.x() + "", "UTF-8");
            }
            else {
                s3 = string + "&user1=" + URLEncoder.encode(s, "UTF-16") + "&user2=" + URLEncoder.encode(this.getName(), "UTF-16");
            }
            this.b(new URL(s3 + "&siteID=" + super.ce + "&serverID=default" + "&limitID=" + this.ad), s + this.getName());
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public final void a(final User2 user2) {
        final String s = new String();
        final String name = user2.getName();
        try {
            if (this.f(this.ac) == null || "".equals(this.f(this.ac))) {
                new am((super.af == null) ? new Frame() : super.af.b(), StringSubst.Substitute(LanguageSupport.translate(""), new String[0]), new String[] { LanguageSupport.translate("OK") }, new String[] { LanguageSupport.translate("This Chat Site is not configured for One to Many AV Broadcasting, please contact the site's administrator for support") }, this.m()).setVisible(true);
                return;
            }
            this.a(new URL(this.f(this.ac) + "?uID=" + URLEncoder.encode(this.getName(), "UTF-16") + "&user1=" + URLEncoder.encode(name, "UTF-16") + "&user2=" + URLEncoder.encode(this.getName(), "UTF-16") + "&siteID=" + super.ce + "&serverID=default" + "&limitID=" + this.ad), name + this.x(), true, false);
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public final void b(final URL url, final String s) {
        this.a(url, s, false, false);
    }
    
    public final void a(final URL url, final String s, final boolean b, final boolean b2) {
        try {
            final Class<?> forName = Class.forName("netscape.javascript.JSObject");
            final Object invoke = forName.getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(null, this.a);
            final Method[] methods = forName.getMethods();
            Method method = methods[0];
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals("call")) {
                    method = methods[i];
                    break;
                }
            }
            String s2 = new String("openFlash");
            if (!b) {
                switch (this.ac) {
                    case 2: {
                        s2 = "openFlash";
                        break;
                    }
                    case 1: {
                        s2 = "openFlashSmall";
                        break;
                    }
                    case 0: {
                        s2 = "openFlashNone";
                        break;
                    }
                }
            }
            else if (b && !b2) {
                switch (this.ac) {
                    case 2: {
                        s2 = "openFlashReceiverLarge";
                        break;
                    }
                    case 1: {
                        s2 = "openFlashReceiverSmall";
                        break;
                    }
                    case 0: {
                        s2 = "openFlashReceiverNone";
                        break;
                    }
                }
            }
            else if (b && b2) {
                s2 = "openFlashBroadcaster";
            }
            final Object[] array = { s2, { url, s } };
            if (method != null) {
                method.invoke(invoke, array);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean b(final String s) {
        return false;
    }
    
    public final String f(final int n) {
        String s = null;
        switch (n) {
            case 2: {
                s = ((this.w == null) ? this.s : ("".equals(this.w) ? this.s : this.w));
                break;
            }
            case 1: {
                s = ((this.x == null) ? this.t : ("".equals(this.x) ? this.t : this.x));
                break;
            }
            case 0: {
                s = ((this.y == null) ? this.u : ("".equals(this.y) ? this.u : this.y));
                break;
            }
            default: {
                s = ((this.w == null) ? this.s : ("".equals(this.w) ? this.s : this.w));
                break;
            }
        }
        return (s == null) ? null : (s.equals("") ? null : s);
    }
    
    public final String l() {
        return this.g(this.ac);
    }
    
    public final String g(final int n) {
        String s = null;
        switch (n) {
            case 2: {
                s = ((this.o == null) ? this.l : ("".equals(this.o) ? this.l : this.o));
                break;
            }
            case 1: {
                s = ((this.p == null) ? this.m : ("".equals(this.p) ? this.m : this.p));
                break;
            }
            case 0: {
                s = ((this.q == null) ? this.n : ("".equals(this.q) ? this.n : this.q));
                break;
            }
            default: {
                s = ((this.o == null) ? this.l : ("".equals(this.o) ? this.l : this.o));
                break;
            }
        }
        return s;
    }
    
    public final boolean b(final User2 user2, final boolean b, final boolean b2) {
        if (user2.x() == this.x() || user2.u(6)) {
            return false;
        }
        if (user2.u(64)) {
            this.a(user2);
        }
        else {
            this.h(user2.x());
        }
        return true;
    }
    
    public final boolean b(final User2 user2) {
        return this.a(user2, false);
    }
    
    public final boolean a(final User2 user2, final boolean b) {
        return this.b(user2, true, b);
    }
    
    public final boolean c(final User2 user2) {
        return this.b(user2, true);
    }
    
    public final boolean b(final User2 user2, final boolean b) {
        return this.b(user2, false, b);
    }
    
    public final Theme m() {
        return super.df;
    }
    
    public final void d(final User2 user2) {
        if (super.cb != null) {
            final Properties properties = new Properties();
            ((Hashtable<String, String>)properties).put("NAME1", String.valueOf(this.getName()));
            ((Hashtable<String, String>)properties).put("NAME2", String.valueOf(user2.getName()));
            try {
                this.a(super.cb, properties).close();
            }
            catch (IOException ex) {}
            this.a(null, (User)user2);
        }
    }
    
    public final InputStream a(final URL url, final Properties properties) throws IOException {
        final URLConnection openConnection = url.openConnection();
        openConnection.setDoOutput(true);
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        final StringBuffer sb = new StringBuffer();
        final Enumeration<String> keys = ((Hashtable<String, V>)properties).keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(URLEncoder.encode(s)).append('=').append(URLEncoder.encode(properties.getProperty(s)));
            sb.append('&');
        }
        final String substring = sb.toString().substring(0, sb.length() - 1);
        openConnection.setRequestProperty("Content-length", String.valueOf(substring.length()));
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(openConnection.getOutputStream(), "UTF-8"));
        printWriter.print(substring);
        printWriter.flush();
        printWriter.close();
        return openConnection.getInputStream();
    }
    
    public final void h(final int m) {
        final User user = (User)super.aj.d(m);
        if (user == null) {
            return;
        }
        if (this.f.get(user) != null && this.b(user.getName())) {
            return;
        }
        if (this.e.get(user) != null) {
            return;
        }
        final t t = new t(50400788, 1);
        t.m = m;
        t.l = -1;
        t.a(0, 0, this.x());
        this.ap(t);
        final am am = new am((super.af == null) ? new Frame() : super.af.b(), LanguageSupport.translate("Awaiting AV chat response"), new String[] { LanguageSupport.translate("Close") }, new String[] { StringSubst.Substitute(LanguageSupport.translate("You have requested an AV chat with %1 (In order for DigiChat AV to work properly, all popup blockers must be disabled.)"), new String[] { user.getName() }) }, new b8(this, m), this);
        am.setModal(false);
        am.setVisible(!com.diginet.digichat.network.t.a(super.a5, 43));
        this.e.put(user, am);
    }
    
    public final void i(final int n) {
    }
    
    public final void j(final int m) {
        final t t = new t(50400789, 1);
        t.m = m;
        t.l = -1;
        t.a(0, 0, 1);
        t.a(0, 1, this.x());
        this.ap(t);
    }
    
    protected final void k(final int n) {
        final User user = (User)super.aj.d(n);
        if (this.e.get(user) != null) {
            this.e.remove(user);
        }
        this.f.put(user, user);
    }
    
    protected final void l(final int n) {
        final User user = (User)super.aj.d(n);
        final Object remove = this.e.remove(user);
        if (remove != null) {
            ((am)remove).dispose();
        }
        this.f.remove(user);
    }
    
    public final void m(final int m) {
        final t t = new t(50400789, 1);
        t.m = m;
        t.l = -1;
        t.a(0, 0, 0);
        t.a(0, 1, this.x());
        this.ap(t);
    }
    
    public g() {
        this.c = new Hashtable();
        this.d = new Hashtable();
        this.e = new Hashtable();
        this.f = new Hashtable();
        this.g = new Hashtable();
        this.h = new Properties();
        this.i = new Properties();
        this.j = null;
        this.k = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = 2;
        this.ad = Integer.MAX_VALUE;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = true;
        this.aj = true;
        this.ak = (com.diginet.digichat.util.c3.e ? 262 : 272);
        this.al = 1000;
        this.am = null;
        this.an = 6;
    }
}
