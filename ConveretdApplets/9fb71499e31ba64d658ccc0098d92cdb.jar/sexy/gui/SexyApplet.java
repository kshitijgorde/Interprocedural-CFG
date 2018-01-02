// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.FontMetrics;
import java.awt.Color;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Component;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

public class SexyApplet extends Applet implements Runnable
{
    public int a;
    public String b;
    public String c;
    public e d;
    public String e;
    public Vector f;
    public boolean g;
    public k h;
    public int i;
    public int j;
    public boolean k;
    public int l;
    public String m;
    public Vector n;
    public boolean o;
    public Image p;
    public boolean q;
    public int r;
    public boolean s;
    public Vector t;
    public Object u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public String z;
    public String aa;
    public Font ab;
    public Font ac;
    public long ad;
    public long ae;
    public long af;
    public Hashtable ag;
    public Vector ah;
    public Hashtable ai;
    public int[] aj;
    public int[] ak;
    public Hashtable al;
    public boolean am;
    public String an;
    public Hashtable ao;
    
    public Image createImage(final int n, final int n2) {
        ++this.r;
        final Image image = super.createImage(n, n2);
        this.t.addElement(image);
        return image;
    }
    
    public Image createImage(final ImageProducer imageProducer) {
        ++this.r;
        final Image image = super.createImage(imageProducer);
        this.t.addElement(image);
        return image;
    }
    
    public synchronized boolean gotFocus(final Event event, final Object o) {
        try {
            this.h.g();
        }
        catch (Throwable t) {
            this.a(t);
        }
        return super.gotFocus(event, o);
    }
    
    public synchronized boolean lostFocus(final Event event, final Object o) {
        try {
            this.h.d();
        }
        catch (Throwable t) {
            this.a(t);
        }
        return super.lostFocus(event, o);
    }
    
    public synchronized void g() {
        if (!this.k) {
            this.k = true;
            if (this.d != null) {
                this.d.a();
            }
            for (int i = 0; i < this.t.size(); ++i) {
                ((Image)this.t.elementAt(i)).flush();
            }
            this.t.removeAllElements();
        }
    }
    
    public u a(final Font font) {
        u u = this.al.get(font);
        if (u == null) {
            u = new u(this, font);
            this.al.put(font, u);
        }
        return u;
    }
    
    public synchronized void a(final Image image) {
        this.ai.put(new Point(image.getWidth(null), image.getHeight(null)), image);
    }
    
    public boolean a(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        return !parameter.equalsIgnoreCase("0") && !parameter.equalsIgnoreCase("off") && !parameter.equalsIgnoreCase("no") && !parameter.equalsIgnoreCase("false");
    }
    
    public void SyncShutdown() {
        this.g();
        while (this.y) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void a(final int i, final int j) {
        this.i = i;
        this.j = j;
        this.h.b(this.i, this.j);
        this.k();
        this.h.c();
    }
    
    public void p() {
        this.ae = System.currentTimeMillis();
    }
    
    public void q() {
        this.w();
    }
    
    public void r() {
        try {
            final Applet t = this.t();
            t.getClass().getMethod("AppletLoaded", (Class<?>[])null).invoke(t, (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    public String a(final String s) {
        return this.ao.get(s);
    }
    
    public synchronized void reshape(final int n, final int n2, final int i, final int j) {
        try {
            super.reshape(n, n2, i, j);
            if (this.q) {
                this.a(i, j);
                return;
            }
            this.i = i;
            this.j = j;
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    public synchronized boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.q) {
            return true;
        }
        try {
            return this.h.c(n, n2);
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public void s() {
        if (!this.am) {
            this.a("StatsFail", "Not in group");
            return;
        }
        this.am = false;
    }
    
    public Applet t() {
        final Enumeration<Applet> applets = this.getAppletContext().getApplets();
        while (applets.hasMoreElements()) {
            final Applet applet = applets.nextElement();
            if (applet.getClass().getName().equals("SexyUserTracker")) {
                return applet;
            }
        }
        return null;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.q) {
            return true;
        }
        try {
            if ((event.modifiers & 0x4) != 0x0) {
                return this.h.a(n, n2, -1);
            }
            return this.h.a(n, n2, 1);
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public void destroy() {
        System.out.println("Destroy At: " + System.currentTimeMillis());
        try {
            this.g();
        }
        catch (Throwable t) {
            this.a(t);
        }
        super.destroy();
    }
    
    public void k() {
    }
    
    public Font a(final String s, final int n, int n2) {
        if (this.aj == null) {
            final Graphics graphics = this.p.getGraphics();
            this.aj = new int[this.ak.length];
            int n3 = 0;
            int i = 0;
            for (int j = 0; j < this.ak.length; ++j) {
                while (i <= this.ak[j]) {
                    ++n3;
                    graphics.setFont(new Font("SansSerif", 1, n3));
                    i = graphics.getFontMetrics().stringWidth("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ");
                }
                this.aj[j] = n3 - 1;
            }
            graphics.dispose();
        }
        if (n2 >= 0 && n2 < this.ak.length) {
            n2 = this.aj[n2];
        }
        return new Font(s, n, n2);
    }
    
    public u b(final String s, final int n, final int n2) {
        return this.a(this.a(s, n, n2));
    }
    
    public void u() {
        if (this.q) {
            return;
        }
        this.a(this.i, this.j);
        this.q = true;
        this.y = true;
        new Thread(this).start();
    }
    
    public synchronized boolean keyUp(final Event event, final int n) {
        try {
            return this.h.b(n, event.shiftDown(), event.controlDown());
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public synchronized void a(final String z, final String aa) {
        if (!this.k) {
            this.x = true;
            this.z = z;
            this.aa = aa;
            this.w = true;
            this.repaint();
            System.out.println("FATAL ERROR: " + z);
            System.out.println("    DETAILS: " + aa);
            this.g();
        }
    }
    
    public String a(final short n) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        return "" + array[n >> 12 & 0xF] + array[n >> 8 & 0xF] + array[n >> 4 & 0xF] + array[n & 0xF];
    }
    
    public void b(final String s) {
        this.a(s, null);
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.q) {
            return true;
        }
        try {
            int clickCount = event.clickCount;
            if ((event.modifiers & 0x4) != 0x0) {
                clickCount = -clickCount;
            }
            return this.h.b(n, n2, clickCount);
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public void a(final String s, final char c) {
        if (!this.am) {
            this.a("StatsFail", "Not in group");
            return;
        }
        this.an = this.an + this.a((short)s.length()) + s;
        this.an += c;
    }
    
    public void run() {
        try {
            this.z();
            int n = 0;
            int n2 = 0;
            if (!this.k) {
                this.ae = System.currentTimeMillis();
                int n3 = 0;
                while (!this.k) {
                    ++n;
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.ae >= this.l - 5 && ++n3 < 10) {
                        if (!this.v) {
                            this.l();
                        }
                        this.ae += this.l;
                        if (currentTimeMillis - this.ae > 1000L) {
                            this.ae = currentTimeMillis - 1000L;
                        }
                    }
                    else {
                        n3 = 0;
                        this.v();
                        final long n4 = System.currentTimeMillis() - this.ae;
                        if (n4 < this.l) {
                            try {
                                ++n2;
                                Thread.currentThread();
                                Thread.sleep(this.l - n4);
                            }
                            catch (InterruptedException ex) {}
                        }
                    }
                    if (this.v && System.currentTimeMillis() - this.ad >= 10000L) {
                        this.g();
                    }
                }
            }
            if (this.n.size() != 0) {
                System.out.println("Unfreed Widget Managers: " + this.n.size());
                while (this.n.size() > 0) {
                    this.b(this.n.elementAt(0));
                }
            }
        }
        catch (Throwable t) {
            this.a(t);
        }
        try {
            if (this.x) {
                if (!this.a("noErrorPage", false)) {
                    this.aa();
                }
            }
            else if (this.a("StatsUpload", false)) {
                this.x();
            }
            this.y();
        }
        catch (Throwable t2) {
            t2.printStackTrace();
        }
        System.out.println("Applet thread shut down");
        this.y = false;
    }
    
    public void init() {
        this.ao.put("DIALOG_BUTTON_YES", "Yes");
        this.ao.put("DIALOG_BUTTON_NO", "No");
        new o(this, "AppletLoaded", null);
        try {
            this.d();
            if (this.e == null) {
                System.out.println("WARNING: mProdName not set");
            }
            if (this.a == 0) {
                System.out.println("WARNING: mVersion not set");
            }
            if (this.c == null) {
                System.out.println("WARNING: mBuildDate not set");
            }
            this.u();
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    public void a(final Throwable t) {
        t.printStackTrace();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        t.printStackTrace(new PrintStream(byteArrayOutputStream));
        this.a("exception", new String(byteArrayOutputStream.toByteArray()));
    }
    
    public synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.q) {
            return true;
        }
        try {
            return this.h.f(n, n2);
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public void stop() {
        this.v = true;
        this.ad = System.currentTimeMillis();
    }
    
    public void d() {
        this.r();
        this.p = this.createImage(1, 1);
        this.a(this.h = new k(this, this));
        this.requestFocus();
    }
    
    public synchronized void v() {
        final Enumeration<k> elements = this.n.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().h();
        }
    }
    
    public void w() {
        try {
            final Applet t = this.t();
            t.getClass().getMethod("ResourcesLoaded", (Class<?>[])null).invoke(t, (Object[])null);
        }
        catch (Exception ex) {}
    }
    
    public String c(final String s) {
        String string = s;
        for (int i = 0; i < string.length(); ++i) {
            final String s2 = " %?&+\n\r\t";
            final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
            final char char1 = string.charAt(i);
            if (char1 <= ' ' || s2.indexOf(char1) != -1) {
                string = string.substring(0, i) + "%" + array[char1 / '\u0010'] + "" + array[char1 % '\u0010'] + string.substring(i + 1);
                i += 2;
            }
        }
        return string;
    }
    
    public synchronized void a(final k k) {
        this.n.addElement(k);
    }
    
    public synchronized void l() {
        final Enumeration<k> elements = this.n.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().f();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void x() {
        System.out.println("Raw Stats Size: " + this.an.length());
        System.out.println("Stats: " + this.an);
        try {
            final URLConnection openConnection = new URL("http://www.popcap.com/record_java_stats.php").openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(("prod=" + this.c(this.e)).getBytes());
            outputStream.write(("&ver=" + this.a).getBytes());
            if (this.b != null) {
                outputStream.write(("&variation=" + this.b).getBytes());
            }
            final String parameter = this.getParameter("RandID");
            if (parameter != null) {
                outputStream.write(("&randid=" + parameter).getBytes());
            }
            outputStream.write(("&secsloaded=" + (int)(System.currentTimeMillis() - this.af) / 1000).getBytes());
            outputStream.write(("&stats=" + this.c(this.an)).getBytes());
            outputStream.flush();
            outputStream.close();
            final InputStream inputStream = openConnection.getInputStream();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
                System.out.println("S> " + line);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void start() {
        this.v = false;
    }
    
    public synchronized void b(final k k) {
        if (this.h.i.size() > 0) {
            System.out.println("Unfreed Widgets in Manager: " + this.h.i.size());
        }
        this.n.removeElement(k);
        k.b();
    }
    
    public void d(final String s) {
        if (this.am) {
            this.a("StatsFail", "StatsGroupBegin reentry");
            return;
        }
        this.am = true;
        this.an = this.an + this.a((short)s.length()) + s;
        this.an += "G";
    }
    
    public void y() {
    }
    
    public synchronized boolean keyDown(final Event event, final int n) {
        try {
            return this.h.a(n, event.shiftDown(), event.controlDown());
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
    
    public String h(final int n) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        return "" + array[n >> 28 & 0xF] + array[n >> 24 & 0xF] + array[n >> 20 & 0xF] + array[n >> 16 & 0xF] + array[n >> 12 & 0xF] + array[n >> 8 & 0xF] + array[n >> 4 & 0xF] + array[n & 0xF];
    }
    
    public SexyApplet() {
        this.f = new Vector();
        this.k = false;
        this.l = 20;
        this.m = "";
        this.n = new Vector();
        this.o = false;
        this.t = new Vector();
        this.ag = new Hashtable();
        this.ah = new Vector();
        this.ai = new Hashtable();
        this.ak = new int[] { 0, 60, 60, 101, 123, 163, 194, 222, 254, 300, 327, 353, 372, 418, 456, 475, 518, 526, 586, 620, 632, 669, 698, 743, 770, 798, 844, 883, 901, 934, 964, 987, 1018, 1056, 1095, 1107, 1153, 1200, 1216, 1253, 1273, 1319, 1351, 1381, 1415, 1436, 1469, 1512, 1546, 1574, 1607, 1631, 1675, 1694, 1732, 1772, 1797, 1833, 1856, 1893, 1930, 1953, 1988, 2019, 2060, 2089, 2114, 2149, 2186, 2216, 2253, 2271, 2314 };
        this.al = new Hashtable();
        this.an = "";
        this.ao = new Hashtable();
        this.af = System.currentTimeMillis();
        this.ac = new Font("SansSerif", 0, 14);
        this.ab = new Font("SansSerif", 0, 24);
        try {
            this.s = (new Integer(this.getParameter("debug")) != 0);
        }
        catch (Exception ex) {}
    }
    
    public void z() {
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this) {
            if (this.w) {
                try {
                    graphics.setColor(new Color(255, 0, 0));
                    graphics.fillRect(0, 0, this.i, this.j);
                    graphics.setColor(new Color(255, 255, 255));
                    graphics.fillRect(4, 4, this.i - 8, this.j - 8);
                    boolean b = false;
                    graphics.setFont(this.ab);
                    final FontMetrics fontMetrics = graphics.getFontMetrics();
                    String z;
                    String aa;
                    if (this.z.equalsIgnoreCase("loading")) {
                        z = "Loading Error";
                        aa = "Not all required resources could be loaded";
                    }
                    else if (this.z.equalsIgnoreCase("exception")) {
                        z = "Fatal Error";
                        aa = "An unrecoverable error has occured";
                    }
                    else if (this.z.equalsIgnoreCase("version")) {
                        z = "Invalid Version";
                        aa = "A newer version of this applet is required";
                        b = true;
                    }
                    else {
                        z = this.z;
                        aa = this.aa;
                    }
                    graphics.setColor(new Color(128, 0, 0));
                    graphics.drawString(z, Math.max((this.i - fontMetrics.stringWidth(z)) / 2, 6), 64);
                    graphics.setFont(this.ac);
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                    graphics.drawString(aa, Math.max((this.i - fontMetrics2.stringWidth(aa)) / 2, 6), this.j / 2);
                    String s;
                    if (b) {
                        s = "Please close all browser windows before trying again";
                    }
                    else {
                        s = "Please refresh the page and try again";
                    }
                    graphics.drawString(s, (this.i - fontMetrics2.stringWidth(s)) / 2, this.j - 48);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                this.h.a(graphics);
            }
        }
    }
    
    public void aa() {
        try {
            String parameter = this.getParameter("errorURL");
            if (parameter == null) {
                parameter = "http://www.popcap.com/err_main.php";
            }
            String s;
            if (parameter.indexOf(63) == -1) {
                s = parameter + "?";
            }
            else {
                s = parameter + "&";
            }
            String s2 = "err=" + this.c(this.z);
            if (this.e != null) {
                s2 = s2 + "&prod=" + this.c(this.e);
            }
            if (this.a != 0) {
                s2 = s2 + "&ver=" + this.a;
            }
            this.getAppletContext().showDocument(new URL(s + s2));
            final URLConnection openConnection = new URL("http://www.popcap.com/err_record.php").openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(s2.getBytes());
            if (this.c != null) {
                outputStream.write(("&buildDate=" + this.c(this.c)).getBytes());
            }
            if (this.aa != null) {
                outputStream.write(("&det=" + this.c(this.aa)).getBytes());
            }
            String concat = "";
            for (int i = 0; i < this.f.size(); ++i) {
                concat = concat.concat((String)this.f.elementAt(i) + "\r\n");
            }
            outputStream.write(("&debugLines=" + this.c(concat)).getBytes());
            outputStream.flush();
            outputStream.close();
            final InputStream inputStream = openConnection.getInputStream();
            String line;
            while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
                System.out.println("> " + line);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void e(final String s) {
        System.out.println(s);
        this.f.addElement(s);
    }
    
    public void finalize() throws Throwable {
        System.out.println("Applet finalized");
    }
    
    public boolean ab() {
        boolean b = false;
        boolean b2 = true;
        final String parameter = this.getParameter("hosterr");
        final String parameter2 = this.getParameter("hosterrhash");
        if (parameter != null && parameter2 != null && parameter2.equals(new q(parameter + "SACHH").a())) {
            b2 = false;
            final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
            int n = -1;
            while (true) {
                String s = "";
                int length = -1;
                boolean b3 = false;
                for (int i = 0; i <= n; ++i) {
                    final int index = lowerCase.indexOf(".", length + 1);
                    if (index != -1) {
                        if (i != n) {
                            s = s + lowerCase.substring(length + 1, index) + ".";
                        }
                        length = index;
                    }
                    else {
                        if (length == lowerCase.length()) {
                            b3 = true;
                        }
                        length = lowerCase.length();
                    }
                }
                if (b3) {
                    break;
                }
                if (n != -1) {
                    s += "*";
                }
                if (length != lowerCase.length()) {
                    if (n != -1) {
                        s += ".";
                    }
                    s += lowerCase.substring(length + 1);
                }
                final q q = new q(s + "SACHH");
                int n2 = 1;
                String parameter3;
                do {
                    parameter3 = this.getParameter("host" + n2++);
                    if (parameter3 != null && parameter3.equals(q.a())) {
                        b = true;
                        break;
                    }
                } while (parameter3 != null);
                ++n;
            }
            if (!b) {
                System.out.println("HOST NOT VALID: " + lowerCase);
                try {
                    this.getAppletContext().showDocument(new URL(parameter), "_top");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (b2) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.popcap.com"), "_top");
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            System.out.println("INVALID HOST VALIDATOR ERROR URL");
        }
        if (!b) {
            this.g();
        }
        return b;
    }
    
    public void f(final String s) {
        this.a(s, 'E');
    }
    
    public void a(final String s, final int n) {
        this.a(s, 'I');
        this.an += this.h(n);
    }
    
    public void b(final String s, final String s2) {
        this.a(s, 'S');
        this.an = this.an + this.a((short)s2.length()) + s2;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.q) {
            return true;
        }
        try {
            return this.h.e(n, n2);
        }
        catch (Throwable t) {
            this.a(t);
            return true;
        }
    }
}
