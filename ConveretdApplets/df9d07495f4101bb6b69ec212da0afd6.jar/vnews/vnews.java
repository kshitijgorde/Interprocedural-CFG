// 
// Decompiled by Procyon v0.5.30
// 

package vnews;

import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class vnews extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    final int D = 60;
    final int a = 1;
    URL F;
    Image Q;
    Image B;
    Image M;
    Graphics s;
    Graphics e;
    Thread n;
    int U;
    int O;
    int P;
    int d;
    int j;
    int p;
    int C;
    int H;
    int b;
    int m;
    int T;
    Color w;
    Color v;
    Color J;
    Color G;
    Color[] K;
    Color[] i;
    Color r;
    Color t;
    Color[] W;
    int g;
    int[] V;
    Image[] L;
    boolean R;
    boolean S;
    boolean I;
    boolean q;
    boolean k;
    int A;
    FontMetrics u;
    FontMetrics o;
    FontMetrics h;
    FontMetrics c;
    Vector f;
    a N;
    a E;
    a[] z;
    private String l;
    
    public void init() {
        this.d();
        this.c();
        final String parameter = this.getParameter("dataURL");
        if (parameter != null) {
            try {
                if (this.getDocumentBase().getProtocol().equalsIgnoreCase("file://")) {
                    this.F = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), String.valueOf(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf(File.separator))) + File.separator + parameter);
                }
                else if (parameter.charAt(0) == '/') {
                    this.F = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), parameter);
                }
                else {
                    this.F = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), String.valueOf(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf("/"))) + "/" + parameter);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        final boolean b = false;
        this.p = (b ? 1 : 0);
        this.j = (b ? 1 : 0);
        this.m = 0;
        this.R = false;
        this.S = false;
        this.I = false;
        this.A = -1;
    }
    
    public void start() {
        if (this.n == null) {
            (this.n = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.n != null) {
            this.n.stop();
            this.n = null;
        }
    }
    
    public void destroy() {
        this.s.dispose();
        this.e.dispose();
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }
    
    public void run() {
        while (!this.a(this.F)) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.repaint();
            try {
                Thread.sleep(Math.max(0L, currentTimeMillis + 600L - System.currentTimeMillis()));
            }
            catch (Exception ex) {}
        }
        while (true) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            this.repaint();
            try {
                Thread.sleep(Math.max(0L, currentTimeMillis2 + 60L - System.currentTimeMillis()));
            }
            catch (Exception ex2) {}
        }
    }
    
    protected boolean a(final URL url) {
        final String s = new String();
        final String s2 = new String();
        final String s3 = new String();
        final String s4 = new String();
        String s5 = new String();
        String nextToken = new String();
        String nextToken2 = new String();
        int n = 0;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            int n2 = -1;
            int n3 = 0;
            int t = 0;
            final Vector vector = new Vector<a>();
            if (this.getDocumentBase().getHost().toLowerCase().indexOf(this.l) < 0) {
                ++n2;
                ++n3;
                t += 2;
                vector.addElement(new a("Free Stuff", this.o, this.U - this.P, "", "", n2, n3 - 1, t - 2));
                vector.addElement(new a("Free Tools for Web Masters", this.o, this.U - this.P, "http://www.europa.bc.ca/software", "_top", n2, n3, t - 1));
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, "\n");
                while (stringTokenizer.hasMoreTokens()) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "|");
                    if (stringTokenizer2.countTokens() < 2) {
                        System.out.println("Wrong File format");
                    }
                    else {
                        final String nextToken3 = stringTokenizer2.nextToken();
                        final String nextToken4 = stringTokenizer2.nextToken();
                        if (stringTokenizer2.hasMoreElements()) {
                            nextToken = stringTokenizer2.nextToken();
                            if (stringTokenizer2.hasMoreElements()) {
                                nextToken2 = stringTokenizer2.nextToken();
                            }
                            else {
                                nextToken2 = "_blank";
                            }
                        }
                        if (s5.compareTo(nextToken3) != 0) {
                            ++n2;
                            n3 = 0;
                            s5 = nextToken3;
                        }
                        vector.addElement(new a(nextToken4, this.u, this.U - this.P, nextToken, nextToken2, n2, n3, t));
                        ++n3;
                        ++t;
                        n = n3;
                    }
                }
            }
            bufferedReader.close();
            this.g = n2 + 1;
            this.T = t;
            this.V = new int[this.g];
            this.z = new a[vector.size()];
            for (int i = 0; i < this.z.length; ++i) {
                this.z[i] = vector.elementAt(i);
                final int[] v = this.V;
                final int j = this.z[i].i();
                ++v[j];
            }
            this.W = new Color[this.g];
            this.K = new Color[this.g];
            this.i = new Color[this.g];
            this.b();
            this.d();
            this.c();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.k = true;
            return false;
        }
        if (n == 0) {
            this.k = true;
            return false;
        }
        int n4 = 0;
        this.L = new Image[this.g];
        for (int k = 0; k < this.g; ++k) {
            this.L[k] = this.createImage(this.U, this.z[n4].a());
            final Graphics graphics = this.L[k].getGraphics();
            graphics.setColor(this.W[k]);
            graphics.fillRect(0, 0, this.U, this.z[n4].a());
            n4 += this.V[k];
        }
        this.f.removeAllElements();
        this.E = this.z[0];
        this.h = this.E.f();
        this.E.a(this.O + this.h.getAscent());
        this.f.addElement(this.E);
        this.q = true;
        this.k = false;
        return this.q;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.q) {
            this.s.setColor(this.w);
            this.s.fillRect(0, 0, this.U, this.O);
            this.s.setFont(this.o.getFont());
            this.s.setColor(this.J);
            if (this.k) {
                this.s.drawString("Can't read news", this.P, this.O / 2);
            }
            else {
                this.s.drawString("Loading...", this.P, this.O / 2);
                this.s.drawString("Please wait", this.P, this.O / 2 + this.o.getHeight());
            }
            this.e.drawImage(this.Q, this.d, this.d, this);
            graphics.drawImage(this.B, 0, 0, this);
            return;
        }
        if (this.A >= 0) {
            this.a();
        }
        if (this.f.isEmpty() && !this.R) {
            return;
        }
        this.s.setColor(this.w);
        this.s.fillRect(0, 0, this.U, this.O);
        int c = 0;
        if (!this.f.isEmpty()) {
            this.E = this.f.elementAt(0);
            this.h = this.E.f();
            int n = this.h.getLeading() + this.h.getAscent();
            if (this.E.c() + this.E.a() - n <= this.m) {
                this.f.removeElementAt(0);
                if (!this.f.isEmpty()) {
                    this.E = this.f.elementAt(0);
                }
            }
            if (this.E.d() && this.E.c() <= n) {
                this.N = this.E;
                this.R = true;
                this.m = this.N.a();
                if (!this.f.isEmpty()) {
                    this.f.removeElementAt(0);
                }
                if (!this.f.isEmpty()) {
                    this.E = this.f.elementAt(0);
                    this.h = this.E.f();
                    n = this.h.getLeading() + this.h.getAscent();
                }
            }
            if (this.E.d() && this.R && this.E.c() <= this.m + n) {
                this.R = false;
                this.m = 0;
                this.f.insertElementAt(this.N, 0);
            }
            for (int i = 0; i < this.f.size(); ++i) {
                this.E = (a)this.f.elementAt(i);
                this.h = this.E.f();
                final int n2 = this.h.getLeading() + this.h.getAscent();
                c = this.E.c();
                final int n3 = c - n2;
                if (this.S && Math.max(n3, this.m) < this.p && this.p < n3 + this.E.a()) {
                    if (this.E.d()) {
                        this.t = this.i[this.E.i()];
                        if (this.I) {
                            this.A = this.E.i();
                        }
                    }
                    else {
                        this.r = this.G;
                        if (this.I) {
                            this.a(this.E.h(), this.E.e());
                        }
                    }
                }
                else if (this.E.d()) {
                    this.t = this.K[this.E.i()];
                }
                else {
                    this.r = this.J;
                }
                this.a(this.E);
                this.E.a(c - this.C);
            }
            final int a = this.E.a();
            final int n4 = this.E.k() + 1;
            if (c + a <= this.O) {
                if (this.g == 1 && n4 == this.T) {
                    this.E = new a(this.z[n4 % this.T + 1]);
                }
                else {
                    this.E = new a(this.z[n4 % this.T]);
                }
                this.h = this.E.f();
                if (this.E.d()) {
                    c += this.b;
                }
                this.E.a(c + a + this.H);
                this.f.addElement(this.E);
            }
        }
        if (this.R) {
            this.s.setColor(this.w);
            this.s.fillRect(0, 0, this.U, this.m);
            if (this.S && this.p > 0 && this.p < this.m) {
                this.t = this.i[this.N.i()];
                if (this.I) {
                    this.A = this.N.i();
                }
            }
            else {
                this.t = this.K[this.N.i()];
            }
            this.a(this.N);
        }
        this.e.drawImage(this.Q, this.d, this.d, this);
        graphics.drawImage(this.B, 0, 0, this);
    }
    
    public void a() {
        this.f.removeAllElements();
        this.R = true;
        int n = 0;
        for (int i = 0; i < this.A; ++i) {
            for (int j = 0; j < this.V[i]; ++j) {
                ++n;
            }
        }
        this.N = this.z[n];
        this.h = this.N.f();
        final int n2 = this.h.getLeading() + this.h.getAscent();
        this.N.a(n2);
        this.m = this.N.a();
        int k = n2 + this.m;
        do {
            ++n;
            if (this.g == 1 && n % this.T == 0) {
                ++n;
            }
            this.E = new a(this.z[n % this.T]);
            int n3 = k + this.H;
            if (this.E.d()) {
                n3 += this.b;
            }
            this.E.a(n3);
            this.f.addElement(this.E);
            k = n3 + this.E.a();
        } while (k < this.O);
        this.A = -1;
        this.I = false;
    }
    
    public void a(final a a) {
        int c = a.c();
        final Vector b = a.b();
        if (b.size() != 0) {
            if (a.d()) {
                this.s.setColor(this.t);
                this.s.setFont(this.o.getFont());
                for (int i = 0; i < b.size(); ++i) {
                    final String s = b.elementAt(i);
                    this.s.drawImage(this.L[a.i()], 0, c - this.o.getAscent() - this.o.getLeading(), this);
                    this.s.drawString(s, this.P, c);
                    c += this.o.getHeight();
                }
                return;
            }
            this.s.setColor(this.r);
            this.s.setFont(this.u.getFont());
            for (int j = 0; j < b.size(); ++j) {
                this.s.drawString(b.elementAt(j), this.P, c);
                c += this.u.getHeight();
            }
        }
    }
    
    public void a(final String s, final String s2) {
        try {
            this.getAppletContext().showDocument(new URL(s), s2);
        }
        catch (Exception ex) {
            System.out.println("Bad URL: " + s + " " + s2);
        }
        this.I = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.j = mouseEvent.getX() - this.d;
        this.p = mouseEvent.getY() - this.d;
        this.setCursor(new Cursor(12));
        this.S = true;
        this.C = 0;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.j = 0;
        this.p = 0;
        this.C = 1;
        this.setCursor(new Cursor(0));
        this.S = false;
        this.I = false;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.j = mouseEvent.getX() - this.d;
        this.p = mouseEvent.getY() - this.d;
        this.I = true;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.j = mouseEvent.getX() - this.d;
        this.p = mouseEvent.getY() - this.d;
        this.S = true;
        this.C = 0;
        this.repaint();
    }
    
    public void b() {
        this.w = Color.decode("#FFFFFF");
        if (this.getParameter("backColor") != null) {
            try {
                this.w = Color.decode(this.getParameter("backColor"));
            }
            catch (NumberFormatException ex) {
                System.out.println("Bad 'backColor ' value");
                System.out.println("Default is used");
            }
        }
        this.v = Color.decode("#3366CC");
        if (this.getParameter("borderColor") != null) {
            try {
                this.v = Color.decode(this.getParameter("borderColor"));
            }
            catch (NumberFormatException ex2) {
                System.out.println("Bad 'borderColor ' value");
                System.out.println("Default is used");
            }
        }
        for (int i = 0; i < this.g; ++i) {
            this.W[i] = Color.decode("#FF0000");
            this.K[i] = Color.decode("#0000FF");
            this.i[i] = Color.decode("#CCCCCC");
            final String string = Integer.toString(i);
            if (this.getParameter("headerBackColor" + string) != null) {
                try {
                    this.W[i] = Color.decode(this.getParameter("headerBackColor" + string));
                }
                catch (NumberFormatException ex3) {
                    System.out.println("Bad 'headerBackColor" + string + "' value");
                    System.out.println("Default is used");
                }
            }
            if (this.getParameter("headerColor" + string) != null) {
                try {
                    this.K[i] = Color.decode(this.getParameter("headerColor" + string));
                }
                catch (NumberFormatException ex4) {
                    System.out.println("Bad 'headerColor" + string + "' value");
                    System.out.println("Default is used");
                }
            }
            if (this.getParameter("headerHLColor" + string) != null) {
                try {
                    this.i[i] = Color.decode(this.getParameter("headerHLColor" + string));
                }
                catch (NumberFormatException ex5) {
                    System.out.println("Bad 'headerHLColor" + string + "' value");
                    System.out.println("Default is used");
                }
            }
        }
        this.J = Color.decode("#000000");
        if (this.getParameter("newsColor") != null) {
            try {
                this.J = Color.decode(this.getParameter("newsColor"));
            }
            catch (NumberFormatException ex6) {
                System.out.println("Bad 'newsColor ' value");
                System.out.println("Default is used");
            }
        }
        this.G = Color.decode("#FF0000");
        if (this.getParameter("newsHLColor") != null) {
            try {
                this.G = Color.decode(this.getParameter("newsHLColor"));
            }
            catch (NumberFormatException ex7) {
                System.out.println("Bad 'newsHLColor ' value");
                System.out.println("Default is used");
            }
        }
    }
    
    public void c() {
        int max;
        int max2;
        if ("" != "" && "" != "") {
            final MediaTracker mediaTracker = new MediaTracker(this);
            try {
                this.M = this.getImage(new URL(""), "");
            }
            catch (MalformedURLException ex) {}
            mediaTracker.addImage(this.M, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex2) {}
            max = Math.max(0, this.M.getWidth(this));
            max2 = Math.max(0, this.M.getHeight(this));
        }
        else {
            max = 0;
            max2 = 0;
        }
        this.d = 0;
        if (this.getParameter("borderWidth") != null) {
            try {
                this.d = Math.max(0, Math.min(this.getSize().width / 2, Integer.parseInt(this.getParameter("borderWidth"))));
            }
            catch (NumberFormatException ex3) {
                System.out.println("Bad 'borderWidth' value");
                System.out.println("Default is used");
            }
        }
        this.P = 10;
        int width = this.getSize().width;
        int height = this.getSize().height;
        if (width < 1 || height < 1) {
            width = 150;
            height = 250;
            System.out.println("Bad width/height value");
            System.out.println("Default is used");
        }
        this.U = width - 2 * this.d;
        this.O = height - 2 * this.d - max2;
        this.Q = this.createImage(this.U, this.O);
        this.s = this.Q.getGraphics();
        this.B = this.createImage(width, height);
        (this.e = this.B.getGraphics()).setColor(this.v);
        this.e.fillRect(0, 0, width, height);
        if ("" != "" && "" != "") {
            this.e.drawImage(this.M, (width - max) / 2, height - max2, this);
        }
        this.C = 1;
    }
    
    public void d() {
        int int1 = 10;
        if (this.getParameter("newsFontSize") != null) {
            try {
                int1 = Integer.parseInt(this.getParameter("newsFontSize"));
            }
            catch (NumberFormatException ex) {
                System.out.println("Bad 'newsFontSize' value");
                System.out.println("Default is used");
            }
        }
        String parameter;
        if (this.getParameter("newsFont") != null) {
            parameter = this.getParameter("newsFont");
        }
        else {
            parameter = "SansSerif";
        }
        this.u = this.getFontMetrics(new Font("newsFont", 0, int1));
        this.c = this.getFontMetrics(new Font(parameter, 1, int1));
        int int2 = 12;
        if (this.getParameter("headerFontSize") != null) {
            try {
                int2 = Integer.parseInt(this.getParameter("headerFontSize"));
            }
            catch (NumberFormatException ex2) {
                System.out.println("Bad 'headerFontSize' value");
                System.out.println("Default is used");
            }
        }
        String parameter2;
        if (this.getParameter("headerFont") != null) {
            parameter2 = this.getParameter("headerFont");
        }
        else {
            parameter2 = "SansSerif";
        }
        this.o = this.getFontMetrics(new Font(parameter2, 0, int2));
        this.H = Math.max(4, (int)(0.75 * int1));
        this.b = Math.max(8, 2 * int2);
    }
    
    public vnews() {
        this.L = new Image[5];
        this.f = new Vector();
        this.N = new a();
        this.E = new a();
        this.l = "@";
    }
}
