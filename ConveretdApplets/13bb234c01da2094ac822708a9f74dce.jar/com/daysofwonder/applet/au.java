// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Container;
import java.awt.Window;
import java.awt.Frame;
import java.applet.AudioClip;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.applet.AppletContext;
import java.util.Properties;
import java.util.zip.ZipEntry;
import com.daysofwonder.util.UIProperties;
import java.io.ByteArrayInputStream;
import java.util.zip.ZipInputStream;
import java.util.Enumeration;
import java.net.URLConnection;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import com.daysofwonder.b.a.a;
import com.daysofwonder.b.b;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.ImageProducer;
import javax.swing.SwingUtilities;
import java.net.MalformedURLException;
import com.daysofwonder.util.r;
import java.awt.Component;
import com.daysofwonder.util.k;
import com.daysofwonder.util.t;
import java.net.URL;
import java.net.Socket;
import java.util.Hashtable;
import com.daysofwonder.util.q;
import javax.swing.JApplet;

public abstract class au extends JApplet implements K, q, Runnable
{
    public static boolean d;
    public static boolean e;
    public boolean f;
    protected Thread g;
    protected boolean h;
    protected int i;
    protected static String j;
    protected static Hashtable k;
    protected Socket l;
    protected aM m;
    protected aG n;
    protected u o;
    protected URL p;
    protected URL q;
    protected URL r;
    protected URL s;
    protected URL t;
    protected URL u;
    protected URL v;
    protected URL w;
    protected URL z;
    protected URL A;
    protected URL B;
    protected URL C;
    protected URL D;
    protected aj E;
    protected String F;
    protected String G;
    protected String H;
    protected String I;
    protected String J;
    protected boolean K;
    protected static String L;
    protected static boolean M;
    protected String N;
    protected String O;
    protected String P;
    private static final char[] a;
    protected static final String[] Q;
    protected String R;
    protected String S;
    protected String T;
    protected String U;
    protected String[][] V;
    private boolean b;
    
    public au() {
        this.f = true;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.V = new String[][] { { "server", "string", "Server address" }, { "port", "1-65535", "Server port" }, { "login", "string", "login name" }, { "pass", "string", "password" }, { "help", "string", "help page url" }, { "buy", "string", "buy page url" }, { "forum", "string", "forum page url" }, { "schedule", "string", "schedule page url" }, { "ping", "string", "ping url" }, { "table", "string", "table id for resurrect or autojoin" }, { "tpass", "string", "table pass for resurrect or autojoin" }, { "tname", "string", "table name for resurrect or autojoin" }, { "resurrect", "string", "true or false or nothing" }, { "news", "string", "welcome news" }, { "err", "string", "error url" }, { "noconnect", "string", "no connect url" }, { "locale", "string", "locale" } };
    }
    
    public void init() {
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.z = null;
        this.B = null;
        this.C = null;
        this.F = null;
        this.A = null;
        this.D = null;
        this.G = System.getProperty("java.vendor", "");
        this.H = System.getProperty("java.version", "");
        this.I = System.getProperty("os.name", "");
        this.J = System.getProperty("os.version", "");
        System.out.println("system: " + this.G + "," + this.H + "," + this.I + "," + this.J);
        try {
            if (this.I.equals("Windows 95") && this.H.equals("1.1")) {
                this.K = true;
            }
            if (this.I.equals("Mac OS X")) {
                this.b = true;
                au.e = true;
            }
            if (this.I.equals("Mac OS X") && this.J.startsWith("10.2")) {
                System.out.println("sync is true, hwaccel is " + System.getProperty("com.apple.hwaccel"));
                System.setProperty("com.apple.hwaccel", "false");
                System.out.println("sync is true, hwaccel is " + System.getProperty("com.apple.hwaccel"));
            }
            if (this.I.equals("Mac OS X") || this.I.equals("Linux")) {
                System.out.println("sync is true");
                au.d = true;
            }
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
        }
        com.daysofwonder.applet.y.a(this);
        com.daysofwonder.util.k.a(new k());
        com.daysofwonder.util.k.a(this);
        this.o = new u(this);
        au.j = "";
        if (this.getParameter("news") != null && this.getParameter("news").length() > 0) {
            final String parameter = this.getParameter("news");
            try {
                this.F = new String(com.daysofwonder.util.r.a(parameter), "8859_1");
            }
            catch (Exception ex2) {
                com.daysofwonder.util.t.a(ex2);
            }
        }
        if (this.getParameter("skin") != null && this.getParameter("skin").length() > 0) {
            au.M = true;
            this.N = this.getParameter("skin");
        }
        if (this.getParameter("locale") != null && this.getParameter("locale").length() > 0) {
            au.L = this.getParameter("locale");
        }
        else if (this.getParameter("local") != null && this.getParameter("local").length() > 0) {
            au.L = this.getParameter("locale");
        }
        else {
            au.L = "en";
        }
        boolean b = false;
        for (int i = 0; i < au.Q.length; ++i) {
            if (au.L.equals(au.Q[i])) {
                b = true;
                break;
            }
        }
        if (!b) {
            com.daysofwonder.util.t.a("WARNING: unknown locale: " + au.L + " using en instead");
            au.L = "en";
        }
        if (this.getParameter("help") != null && this.getParameter("help").length() > 0) {
            try {
                this.p = new URL(this.getParameter("help"));
            }
            catch (MalformedURLException ex3) {
                this.p = null;
            }
        }
        if (this.getParameter("forum") != null && this.getParameter("forum").length() > 0) {
            try {
                this.q = new URL(this.getParameter("forum"));
            }
            catch (MalformedURLException ex4) {
                this.q = null;
            }
        }
        if (this.getParameter("buy") != null && this.getParameter("buy").length() > 0) {
            try {
                this.r = new URL(this.getParameter("buy"));
            }
            catch (MalformedURLException ex5) {
                this.r = null;
            }
        }
        if (this.getParameter("schedule") != null && this.getParameter("schedule").length() > 0) {
            try {
                this.s = new URL(this.getParameter("schedule"));
            }
            catch (MalformedURLException ex6) {
                this.s = null;
            }
        }
        if (this.getParameter("ping") != null && this.getParameter("ping").length() > 0) {
            try {
                this.t = new URL(this.getParameter("ping"));
            }
            catch (MalformedURLException ex7) {
                this.t = null;
            }
        }
        if (this.getParameter("newsurl") != null && this.getParameter("newsurl").length() > 0) {
            try {
                this.v = new URL(this.getParameter("newsurl"));
            }
            catch (MalformedURLException ex8) {
                this.v = null;
            }
        }
        if (this.getParameter("err") != null && this.getParameter("err").length() > 0) {
            try {
                this.u = new URL(this.getParameter("err"));
            }
            catch (MalformedURLException ex9) {
                this.u = null;
            }
        }
        if (this.getParameter("quit") != null && this.getParameter("quit").length() > 0) {
            try {
                this.w = new URL(this.getParameter("quit"));
            }
            catch (MalformedURLException ex10) {
                this.w = null;
            }
        }
        if (this.getParameter("quitlobby") != null && this.getParameter("quitlobby").length() > 0) {
            try {
                this.z = new URL(this.getParameter("quitlobby"));
            }
            catch (MalformedURLException ex11) {
                this.z = null;
            }
        }
        if (this.getParameter("pm") != null && this.getParameter("pm").length() > 0) {
            try {
                this.C = new URL(this.getParameter("pm"));
            }
            catch (MalformedURLException ex12) {
                this.C = null;
            }
        }
        if (this.getParameter("reconnect") != null && this.getParameter("reconnect").length() > 0) {
            try {
                this.A = new URL(this.getParameter("reconnect"));
            }
            catch (MalformedURLException ex13) {
                this.A = null;
            }
        }
        if (this.getParameter("countdown") != null && this.getParameter("countdown").length() > 0) {
            try {
                this.B = new URL(this.getParameter("countdown"));
            }
            catch (MalformedURLException ex14) {
                this.B = null;
            }
        }
        if (this.getParameter("noconnect") != null && this.getParameter("noconnect").length() > 0) {
            try {
                this.D = new URL(this.getParameter("noconnect"));
            }
            catch (MalformedURLException ex15) {
                this.D = null;
            }
        }
        try {
            SwingUtilities.invokeAndWait(new G(this));
        }
        catch (Exception ex16) {}
    }
    
    public void start() {
        System.out.println("----> IN START");
        if (this.g == null) {
            System.out.println("----> STARTING...");
            (this.g = new Thread(com.daysofwonder.util.k.a(), this)).start();
            if (this.E == null) {
                (this.E = new aj(this)).start();
                com.daysofwonder.util.t.a("starting ping thread");
            }
        }
    }
    
    public void stop() {
        System.out.println("----> STOPPING...");
        if (this.g != null) {
            if (this.E != null) {
                this.E.interrupt();
                this.E = null;
            }
            System.out.println("----> int...");
            try {
                this.g.interrupt();
            }
            catch (Exception ex) {
                System.out.println("----> interrupt failed");
            }
            this.g = null;
            if (this.n != null) {
                System.out.println("----> abort game...");
                this.n.au();
                this.n.e();
            }
            if (this.m != null) {
                this.m.g();
            }
            this.j();
            this.m();
            this.b();
        }
    }
    
    public void destroy() {
        this.m();
        this.b();
    }
    
    protected void j() {
        try {
            SwingUtilities.invokeAndWait(new F(this));
        }
        catch (Exception ex) {}
    }
    
    public void b() {
        System.out.println("base");
    }
    
    public synchronized Image a(final ImageProducer imageProducer) {
        final Image image = this.createImage(imageProducer);
        this.o.a(image);
        this.o.a();
        return image;
    }
    
    public synchronized Image a(final byte[] array) {
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        this.o.a(image);
        this.o.a();
        return image;
    }
    
    public synchronized b a(final String s) {
        if (s == null) {
            return null;
        }
        if (this.i <= 0) {
            this.a(this.o, null, null);
        }
        Image value = au.k.get(s);
        if (!(value instanceof Image)) {
            new g(this.z(), "Error loading image: " + s, true, new String[] { "The applet couldn't find: " + s, "in the image file", "Try to flush your browser", "or Java plug-in Cache", "Then restart your browser and come back" }, 0).a();
            value = null;
        }
        return new a(value);
    }
    
    public Image b(final String s) {
        return ((a)this.a(s)).b();
    }
    
    public synchronized Image a(final URL url, final u u) {
        try {
            final Image image = this.getImage(url);
            u.a(image);
            return image;
        }
        catch (Exception ex) {
            System.out.println("Unable to open image at: " + url);
            return null;
        }
    }
    
    public synchronized void a(final u u, final ad ad, final c c) {
        com.daysofwonder.util.t.a("LoadImages: " + this.i);
        ++this.i;
        if (this.i > 1) {
            com.daysofwonder.util.t.a("Images already loaded: " + this.i);
            return;
        }
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            int n = 1;
            final URLConnection openConnection = new URL(this.getCodeBase(), x()).openConnection();
            openConnection.setDefaultUseCaches(true);
            openConnection.setUseCaches(true);
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
            int int1 = 0;
            try {
                int1 = dataInputStream.readInt();
            }
            catch (IOException ex) {
                com.daysofwonder.util.t.a("Error: " + ex.getMessage());
                com.daysofwonder.util.t.a(ex);
                this.a("reading image.gif", ex);
            }
            System.out.println("Reading " + int1 + " imgs ");
            while (true) {
                try {
                    while (true) {
                        final String utf = dataInputStream.readUTF();
                        final int int2 = dataInputStream.readInt();
                        System.currentTimeMillis();
                        System.out.println("Loading: " + utf + " size " + int2);
                        if (ad != null) {
                            ad.a(utf, n, int1);
                        }
                        final byte[] array = new byte[int2];
                        dataInputStream.readFully(array);
                        final Image image = defaultToolkit.createImage(array);
                        au.k.put("img/" + utf, image);
                        u.a(image);
                        ++n;
                    }
                }
                catch (EOFException ex4) {}
                catch (IOException ex2) {
                    com.daysofwonder.util.t.a("Error: " + ex2.getMessage());
                    com.daysofwonder.util.t.a(ex2);
                    this.a("image.gif truncation", ex2);
                    continue;
                }
                break;
            }
        }
        catch (IOException ex3) {
            com.daysofwonder.util.t.a("Error: " + ex3.getMessage());
            com.daysofwonder.util.t.a(ex3);
            this.a("general loading error", ex3);
        }
        if (!u.a(c)) {
            com.daysofwonder.util.t.a("Some images are erroneous");
            final Enumeration<String> keys = (Enumeration<String>)au.k.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final Object value = au.k.get(s);
                if (value instanceof Image && u.d((Image)value)) {
                    System.out.println("Error on: " + s);
                }
            }
        }
        final Enumeration<String> keys2 = (Enumeration<String>)au.k.keys();
        while (keys2.hasMoreElements()) {
            final String s2 = keys2.nextElement();
            if (!(au.k.get(s2) instanceof Image)) {
                com.daysofwonder.util.t.b("Unknown image: " + s2);
                com.daysofwonder.util.t.b("image is: " + au.k.get(s2));
            }
        }
    }
    
    public Hashtable a(final Class clazz, final String s, final aF af) {
        final Hashtable<String, byte[]> hashtable = new Hashtable<String, byte[]>();
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            com.daysofwonder.util.t.a("loadArchive: " + this.getCodeBase() + " file: \"" + s + "\"");
            final URL url = new URL(this.getCodeBase(), s);
            com.daysofwonder.util.t.a("loadArchive: " + url);
            final URLConnection openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(true);
            openConnection.setUseCaches(true);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            final int contentLength = openConnection.getContentLength();
            com.daysofwonder.util.t.a("content length: " + contentLength);
            final ZipInputStream zipInputStream = new ZipInputStream(new X(bufferedInputStream, contentLength, af));
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                com.daysofwonder.util.t.a("reading Entry: " + nextEntry.getName() + " size: " + nextEntry.getSize());
                final long currentTimeMillis2 = System.currentTimeMillis();
                final byte[] array = new byte[(int)nextEntry.getSize()];
                int n = 0;
                int read;
                for (int i = (int)nextEntry.getSize(); i > 0; i -= read) {
                    read = zipInputStream.read(array, n, i);
                    n += read;
                }
                com.daysofwonder.util.t.a("reading Entry took : " + (System.currentTimeMillis() - currentTimeMillis2) + " ms");
                if (nextEntry.getName().endsWith(".gif") || nextEntry.getName().endsWith(".jpg")) {
                    if (nextEntry.getName().startsWith("board") || nextEntry.getName().equals("hit.gif")) {
                        com.daysofwonder.util.t.a("putting as bytes: " + nextEntry.getName() + " size: " + array);
                        hashtable.put(nextEntry.getName(), array);
                    }
                    else if (!nextEntry.getName().startsWith("small")) {
                        final Image image = defaultToolkit.createImage(array);
                        com.daysofwonder.util.t.a("putting as images: " + nextEntry.getName());
                        this.o.a(image);
                        hashtable.put(nextEntry.getName(), (byte[])(Object)new a(image));
                    }
                    else {
                        final Image image2 = defaultToolkit.createImage(array);
                        com.daysofwonder.util.t.a("putting as images: " + nextEntry.getName());
                        this.o.a(image2);
                        hashtable.put(nextEntry.getName(), (byte[])(Object)image2);
                    }
                }
                else if (nextEntry.getName().endsWith(".properties")) {
                    hashtable.put(nextEntry.getName(), (byte[])(Object)new UIProperties(new ByteArrayInputStream(array), "UTF8"));
                }
                else {
                    hashtable.put(nextEntry.getName(), array);
                }
                zipInputStream.closeEntry();
            }
            zipInputStream.close();
            bufferedInputStream.close();
            com.daysofwonder.util.t.a("loading archive took: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            final long currentTimeMillis3 = System.currentTimeMillis();
            this.o.a((c)null);
            com.daysofwonder.util.t.a("waiting images: " + (System.currentTimeMillis() - currentTimeMillis3) + " ms");
        }
        catch (MalformedURLException ex) {
            com.daysofwonder.applet.y.a("Map loading exception, bad url", ex);
            com.daysofwonder.util.t.a(ex);
        }
        catch (IOException ex2) {
            com.daysofwonder.applet.y.a("Map loading exception, ioexception", ex2);
            com.daysofwonder.util.t.a(ex2);
        }
        return hashtable;
    }
    
    public Image d(final String s) {
        Image image;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(au.j + s);
            final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available];
            int i = available;
            do {
                i -= resourceAsStream.read(array, available - i, i);
            } while (i > 0);
            image = defaultToolkit.createImage(array);
        }
        catch (Exception ex) {
            System.out.println("Unable to open image at: " + au.j + s);
            return null;
        }
        return image;
    }
    
    public int k() {
        return 0;
    }
    
    public String l() {
        String property = "1.0.0";
        String property2 = "";
        final Properties properties = new Properties();
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/applet.dist.build.number");
            properties.load(resourceAsStream);
            resourceAsStream.close();
        }
        catch (Exception ex3) {
            try {
                final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("/build.number");
                if (resourceAsStream2 != null) {
                    properties.load(resourceAsStream2);
                    resourceAsStream2.close();
                }
            }
            catch (Exception ex) {
                com.daysofwonder.util.t.a(ex);
            }
        }
        final int int1 = Integer.parseInt(properties.getProperty("build.number", "1"));
        try {
            final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("/version.properties");
            properties.load(resourceAsStream3);
            resourceAsStream3.close();
            property = properties.getProperty("version");
            property2 = properties.getProperty("git.head");
        }
        catch (Exception ex2) {
            com.daysofwonder.util.t.a(ex2);
        }
        return property + Integer.toString(int1) + ((property2.length() >= 6) ? ("-" + property2.substring(0, 6)) : "");
    }
    
    public InputStream e(final String s) {
        try {
            return this.getClass().getClassLoader().getResourceAsStream(au.j + s);
        }
        catch (Exception ex) {
            System.out.println("Unable to open stream at: " + au.j + s);
            return null;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x40) != 0x0) {
            this.o.b(image);
        }
        else if ((n & 0x30) != 0x0) {
            this.o.c(image);
            if ((n & 0x10) != 0x0) {
                return false;
            }
        }
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    protected void m() {
        System.out.println("Closing socket if possible...");
        if (this.l != null) {
            try {
                System.out.println("Closing socket...");
                this.l.close();
                this.l = null;
            }
            catch (Exception ex) {}
        }
    }
    
    protected void a(final String s, final int n) {
        try {
            com.daysofwonder.util.t.a("connecting to server: " + s + ":" + n);
            this.l = new Socket(s, n);
        }
        catch (IOException ex) {
            com.daysofwonder.util.t.a("Boum + " + ex.getMessage());
            throw ex;
        }
    }
    
    public void n() {
        if (this.p != null) {
            final AppletContext appletContext = this.getAppletContext();
            if (appletContext != null) {
                appletContext.showDocument(this.p, "_blank");
            }
        }
    }
    
    public void a(final URL url) {
        com.daysofwonder.util.t.a("redirecting to " + url);
        if (url != null) {
            final AppletContext appletContext = this.getAppletContext();
            if (appletContext != null) {
                appletContext.showDocument(url, "_self");
            }
        }
    }
    
    public void o() {
        this.a(this.z);
    }
    
    public boolean p() {
        return this.z != null;
    }
    
    public boolean q() {
        return this.C != null;
    }
    
    public void f(final String s) {
        if (this.C != null) {
            try {
                final StringBuffer sb = new StringBuffer();
                sb.append(this.C.toString()).append('=').append(s);
                this.getAppletContext().showDocument(new URL(sb.toString()), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public boolean r() {
        return this.p != null;
    }
    
    public void s() {
        if (this.q != null) {
            this.getAppletContext().showDocument(this.q, "_blank");
        }
    }
    
    public void t() {
        if (this.r != null) {
            this.getAppletContext().showDocument(this.r, "_blank");
        }
    }
    
    public boolean u() {
        return this.r != null;
    }
    
    public void v() {
        if (this.s != null) {
            this.getAppletContext().showDocument(this.s, "_blank");
        }
    }
    
    public boolean w() {
        return this.s != null;
    }
    
    public abstract void c();
    
    public String g(final String s) {
        String string = s;
        for (int i = 0; i < string.length(); ++i) {
            final String s2 = " %?&+\n\r\t";
            final char char1 = string.charAt(i);
            if (char1 <= ' ' || s2.indexOf(char1) != -1) {
                string = string.substring(0, i) + "%" + au.a[char1 / '\u0010'] + "" + au.a[char1 % '\u0010'] + string.substring(i + 1);
                i += 2;
            }
        }
        return string;
    }
    
    public void a(final Thread thread, final Throwable t) {
        if (thread != null && t != null) {
            this.a(thread.getName(), t);
        }
    }
    
    public void a(final String s, final Throwable t) {
        if (this.u != null) {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final PrintStream printStream = new PrintStream(byteArrayOutputStream);
                t.printStackTrace(printStream);
                if (s != null) {
                    printStream.println("&hint=" + s);
                }
                if (this.O != null) {
                    printStream.println("&login=" + this.O);
                }
                if (this.n != null) {
                    printStream.println("&state=" + ("[s=" + this.n.aq() + ",l=" + this.n.aA() + ",r=" + this.n.an() + "]"));
                }
                if (this.I != null && this.J != null && this.G != null && this.H != null) {
                    printStream.println("&vers=" + this.I + "-" + this.J + "-" + this.G + "-" + this.H);
                }
                final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.u, this.u.getFile() + "?err=" + this.g(com.daysofwonder.util.r.a(byteArrayOutputStream.toByteArray()))).openConnection();
                httpURLConnection.setRequestMethod("GET");
                System.out.println(httpURLConnection.getResponseCode());
                httpURLConnection.disconnect();
            }
            catch (Exception ex) {
                t.a(ex);
            }
        }
    }
    
    public boolean f() {
        return au.M;
    }
    
    public String g() {
        return this.N;
    }
    
    public String h() {
        return au.L;
    }
    
    public static String x() {
        if (au.L.equals("en")) {
            return "tt-images.gif";
        }
        return "tt-images-" + au.L + ".gif";
    }
    
    public void a(final AudioClip audioClip) {
        if (this.f) {
            audioClip.play();
        }
    }
    
    public boolean y() {
        return this.f;
    }
    
    public void a(final boolean f) {
        this.f = f;
    }
    
    public AudioClip h(final String s) {
        return this.getAudioClip(this.getCodeBase(), s);
    }
    
    public Frame z() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame) && !(container instanceof Window); container = ((Frame)container).getParent()) {}
        return (Frame)container;
    }
    
    public boolean A() {
        return this.U != null && this.R != null && this.S != null && this.T != null;
    }
    
    public String B() {
        return this.T;
    }
    
    public String C() {
        return this.R;
    }
    
    public String D() {
        return this.S;
    }
    
    public void e() {
        this.S = null;
        this.U = null;
    }
    
    public boolean d() {
        return au.d;
    }
    
    public b b(final b b) {
        return b;
    }
    
    public b a(final b b) {
        return new a(this.a(new FilteredImageSource(((a)b).b().getSource(), new com.daysofwonder.applet.b(128, 255, 0, 0))));
    }
    
    public z a(final com.daysofwonder.b.a a, final ap ap) {
        return new z(a, ap, false);
    }
    
    public UIProperties c(final String s) {
        return new UIProperties(this.e(s), "UTF8");
    }
    
    public String i() {
        return "";
    }
    
    public abstract aE a(final Object p0, final ap p1, final int p2, final int p3);
    
    public boolean E() {
        return this.b;
    }
    
    static {
        a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        Q = new String[] { "en", "fr", "de", "jp" };
    }
}
