// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.applets;

import com.iseemedia.apps.tourclients40.players.e;
import com.iseemedia.apps.tourclients40.players.f;
import java.awt.Event;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Container;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.Date;
import java.util.Random;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import com.iseemedia.apps.tourclients40.toolbar.a;
import java.awt.Frame;
import com.iseemedia.apps.tourclients40.players.h;
import java.applet.Applet;

public class b extends Applet
{
    public h a;
    private String h;
    private String i;
    private String j;
    public String b;
    private int k;
    private int l;
    private float m;
    private float n;
    private float o;
    private float p;
    private Frame q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private float B;
    private float C;
    private boolean D;
    private int E;
    private float F;
    private String G;
    private a H;
    private String I;
    private Image J;
    private String K;
    private String L;
    private String M;
    private String N;
    private String O;
    private AudioClip P;
    private boolean Q;
    int c;
    private Color R;
    private String S;
    private boolean T;
    private String U;
    private Vector V;
    private Vector W;
    private int X;
    private int Y;
    public boolean d;
    private boolean Z;
    private String aa;
    private String ab;
    Thread e;
    private int ac;
    private Font ad;
    private String ae;
    public boolean f;
    protected int g;
    
    public b() {
        this.b = "";
        this.k = 700;
        this.l = 500;
        this.m = 0.0f;
        this.n = 1.0f;
        this.o = 0.0f;
        this.p = 1.0f;
        this.r = -1.5707964f;
        this.s = 1.5707964f;
        this.t = 0.0f;
        this.u = 0.0f;
        this.v = -100.0f;
        this.w = -100.0f;
        this.x = -100.0f;
        this.y = -100.0f;
        this.z = 0.0f;
        this.A = 1.0f;
        this.B = 0.4f;
        this.I = "";
        this.Q = true;
        this.c = 0;
        this.S = null;
        this.T = false;
        this.U = "";
        this.d = false;
        this.Z = false;
        this.aa = "";
        this.ab = "";
        new Random(System.currentTimeMillis());
        this.ac = 20;
        this.ae = "Arial Black, Font.BOLD, 12";
        this.f = false;
        this.g = 1;
    }
    
    public final void init() {
        this.enableEvents(4L);
        final Date date = new Date();
        this.setLayout(null);
        this.H = new a();
        this.I = this.H.f;
        this.J = this.f(this.I);
        final String g;
        if ((g = this.H.g).equals("false")) {
            System.out.println(this.H.k + " " + this.H.j + "." + date);
        }
        else {
            System.out.println(this.H.k + " " + this.H.j + "." + date + ":" + g);
        }
        try {
            com.iseemedia.apps.tourclients40.toolbar.a.a(this.J);
        }
        catch (Exception ex) {
            System.err.println("FileInputHandler: Error loading Toolbar Image" + ex);
            throw new RuntimeException(ex.getMessage());
        }
        try {
            com.iseemedia.apps.tourclients40.toolbar.a.a(this.J);
        }
        catch (Exception ex2) {
            System.err.println("FileInputHandler: Error loading Toolbar Image" + ex2);
            throw new RuntimeException(ex2.getMessage());
        }
        if (this.x == -100.0f) {
            this.x = this.t;
        }
        if (this.y == -100.0f) {
            this.y = this.u;
        }
        if (this.v == -100.0f) {
            this.v = this.r;
        }
        if (this.w == -100.0f) {
            this.w = this.s;
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.i != null && this.a == null) {
            graphics.setFont(this.ad);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final Dimension size = this.getSize();
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.RED);
            graphics.drawString(this.i, 0 + (size.width >> 1) - (fontMetrics.stringWidth(this.i) >> 1), 0 + (size.height >> 1));
            if (this.j != null) {
                graphics.drawString(this.j, 0 + (size.width >> 1) - (fontMetrics.stringWidth(this.j) >> 1), 0 + (size.height >> 1) + fontMetrics.getHeight());
            }
        }
    }
    
    private Image f(final String s) {
        Image image = null;
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final InputStream a = com.iseemedia.apps.tourclients40.resource.a.a(s);
            final byte[] array = new byte[32768];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            image = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {}
        return image;
    }
    
    private void g() {
        this.K = this.getParameter("webhost");
        this.L = this.getParameter("xmlfile");
        this.M = this.getParameter("imagehost");
        this.O = this.getParameter("backgroundcolor");
        final String parameter = this.getParameter("toolbar");
        final String parameter2 = this.getParameter("speed");
        if (parameter != null) {
            com.iseemedia.apps.tourclients40.players.h.l = Integer.parseInt(parameter);
        }
        else {
            com.iseemedia.apps.tourclients40.players.h.l = 1;
        }
        if (this.O == null) {
            this.O = new String("#000000");
        }
        this.R = new Color(Integer.parseInt(this.O.substring(1), 16));
        this.f = this.e(this.getParameter("progressBar"));
        if (this.f) {
            this.Q = false;
        }
        this.V = new Vector();
        (this.W = new Vector()).insertElementAt(this.L, 0);
        this.V = this.d(this.getParameter("autoplay"));
        if (this.V != null && this.V.size() != 0) {
            this.X = this.V.size();
            this.d = true;
        }
        if (this.W != null && this.W.size() != 0) {
            this.Y = this.W.size();
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("font")) != null) {
            this.ae = parameter3;
        }
        final String[] array = { "Arial Black", "Font.BOLD", "12" };
        final StringTokenizer stringTokenizer = new StringTokenizer(this.ae, ",");
        int n;
        try {
            for (n = 0; n < 3 && stringTokenizer.hasMoreTokens(); ++n) {
                array[n] = stringTokenizer.nextToken().trim();
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Need the re format input");
            return;
        }
        if (n < 3) {
            return;
        }
        this.ad = new Font(array[0], this.g(array[1]), Integer.parseInt(array[2]));
        this.a(0.0f, 0.0f, 180.0f);
        this.loadXML(this.L);
        final int int1;
        if (parameter2 != null && (int1 = Integer.parseInt(parameter2)) > -101 && int1 < 101) {
            this.b(parameter2);
        }
    }
    
    private int g(final String s) {
        final String[] array = { "Font.BOLD", "Font.CENTER_BASELINE", "Font.HANGING_BASELINE", "Font.ITALIC", "Font.PLAIN", "Font.ROMAN_BASELINE", "Font.TRUETYPE_FONT" };
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (s.equals(array[i])) {
                n = i;
                break;
            }
        }
        if (n == array.length) {
            n = 0;
        }
        return n;
    }
    
    private void h() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null) {
            this.q = (Frame)container;
        }
    }
    
    private URL h(final String s) {
        URL url = null;
        try {
            if (this.K == null) {
                url = new URL(this.getDocumentBase(), s);
            }
            else {
                url = new URL(this.K + s);
            }
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    public final String[] a(final String s, final String s2) {
        BufferedReader bufferedReader = null;
        final Vector vector = new Vector<String>();
        try {
            final URL h = this.h(s);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(h.openStream()));
            }
            catch (IOException ex) {
                System.out.println(ex.toString());
            }
            catch (Exception ex2) {
                System.out.println(ex2.getMessage());
            }
            while (true) {
                String line;
                try {
                    if ((line = bufferedReader.readLine()) == null) {
                        break;
                    }
                }
                catch (IOException ex3) {
                    break;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(line, s2);
                while (stringTokenizer.hasMoreElements()) {
                    vector.addElement(stringTokenizer.nextToken());
                }
            }
            bufferedReader.close();
        }
        catch (MalformedURLException ex4) {}
        catch (IOException ex5) {}
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    private void i() {
        this.N = null;
        this.F = 0.0f;
        this.m = 0.0f;
        this.o = 0.0f;
        this.n = 1.0f;
        this.p = 1.0f;
        this.t = 0.0f;
        this.u = 0.0f;
        this.x = this.t;
        this.y = this.u;
        this.r = -1.5707964f;
        this.s = 1.5707964f;
        this.v = this.r;
        this.w = this.s;
    }
    
    public void loadXML(final String s) {
        this.i();
        final String[] a;
        if ((a = this.a(s, " <>\t\r\n")).length <= 0) {
            this.i = "Invalid XML file name";
            System.out.println("Invalid XML file name");
            return;
        }
        for (int i = 0; i < a.length; ++i) {
            if (a[i].equalsIgnoreCase("ImageNavigator")) {
                while (!a[i + 1].equalsIgnoreCase("/ImageNavigator")) {
                    ++i;
                    if (a[i].equalsIgnoreCase("audioFile")) {
                        if (!this.d || this.Q) {
                            ++i;
                            if (!this.Z) {
                                this.N = a[i];
                            }
                            else if (this.aa.equalsIgnoreCase(s)) {
                                this.N = this.ab;
                                this.Z = false;
                            }
                            else {
                                this.N = a[i];
                            }
                        }
                    }
                    if (a[i].equalsIgnoreCase("ViewerType")) {
                        ++i;
                        this.b = a[i];
                    }
                    if (a[i].equalsIgnoreCase("ImageFile")) {
                        ++i;
                        String string;
                        for (string = a[i]; -1 == string.indexOf(".fpx") && -1 == string.indexOf(".ivu"); string = string + "%20" + a[i]) {
                            ++i;
                        }
                        this.c(string);
                    }
                    if (this.b.equalsIgnoreCase("ZoomPANO")) {
                        if (a[i].equalsIgnoreCase("initialView")) {
                            try {
                                final float floatValue = Float.valueOf(a[i + 1]);
                                final float floatValue2 = Float.valueOf(a[i + 2]);
                                float floatValue3;
                                if ((floatValue3 = Float.valueOf(a[i + 3])) == 0.0f) {
                                    floatValue3 = 80.0f;
                                }
                                this.a(floatValue, floatValue2, floatValue3);
                            }
                            catch (NumberFormatException ex) {}
                        }
                        if (a[i].equalsIgnoreCase("autospin")) {
                            ++i;
                            this.S = a[i];
                            this.b(a[i]);
                        }
                        if (a[i].equalsIgnoreCase("panotype")) {
                            ++i;
                            this.a(a[i]);
                        }
                        if (a[i].equalsIgnoreCase("vFov")) {
                            this.b(a[i + 1], a[i + 2]);
                        }
                        if (a[i].equalsIgnoreCase("hFov")) {
                            this.c(a[i + 1], a[i + 2]);
                        }
                        if (a[i].equalsIgnoreCase("pitchRange")) {
                            this.d(a[i + 1], a[i + 2]);
                        }
                        if (a[i].equalsIgnoreCase("YawRange")) {
                            this.e(a[i + 1], a[i + 2]);
                        }
                    }
                    if (this.b.equalsIgnoreCase("Zoom2D") && a[i].equalsIgnoreCase("initialView")) {
                        try {
                            this.m = Float.valueOf(a[i + 1]);
                            this.n = Float.valueOf(a[i + 2]);
                            this.o = Float.valueOf(a[i + 3]);
                            this.p = Float.valueOf(a[i + 4]);
                            i += 4;
                        }
                        catch (NumberFormatException ex2) {}
                    }
                }
            }
        }
        if (this.S != null) {
            final int intValue = Integer.valueOf(this.S);
            final Dimension size = this.size();
            if ((intValue > 30 || intValue < -30) && size.height > this.l && size.width > this.k) {
                if (intValue < 0) {
                    this.b("-30");
                    return;
                }
                this.b("30");
            }
        }
    }
    
    public final void b(final String s, final String s2) {
        this.r = -1.5707964f;
        this.s = 1.5707964f;
        try {
            this.r = Float.valueOf(s);
            this.s = Float.valueOf(s2);
        }
        catch (Exception ex) {
            this.r = -1.5707964f;
            this.s = 1.5707964f;
        }
        if (this.r < -1.5707964f) {
            this.r = -1.5707964f;
        }
        if (this.s > 1.5707964f) {
            this.s = 1.5707964f;
        }
    }
    
    public final void c(final String s, final String s2) {
        this.t = 0.0f;
        this.u = 0.0f;
        try {
            this.t = Float.valueOf(s);
            this.u = Float.valueOf(s2);
        }
        catch (Exception ex) {
            this.t = 0.0f;
            this.u = 0.0f;
        }
        if (this.t < 0.0f) {
            this.t = 0.0f;
        }
        if (this.u > 6.2831855f) {
            this.u = 6.2831855f;
        }
    }
    
    public final void d(final String s, final String s2) {
        this.v = this.r;
        this.w = this.s;
        try {
            this.v = Float.valueOf(s);
            this.w = Float.valueOf(s2);
        }
        catch (Exception ex) {
            this.v = this.r;
            this.w = this.s;
        }
        if (this.v < this.r) {
            this.v = this.r;
        }
        if (this.w > this.s) {
            this.w = this.s;
        }
    }
    
    public final void e(final String s, final String s2) {
        this.x = -100.0f;
        this.y = 100.0f;
        try {
            this.x = Float.valueOf(s);
            this.y = Float.valueOf(s2);
        }
        catch (Exception ex) {
            this.x = -100.0f;
            this.y = -100.0f;
        }
        if (this.x < this.t + 0.1f) {
            this.x = this.t + 0.1f;
        }
        if (this.y > this.u - 0.1f) {
            this.y = this.u - 0.1f;
        }
        if (this.x > this.y) {
            this.x = 0.0f;
            this.y = 0.0f;
        }
    }
    
    public final void a(final String s) {
        this.E = 90;
        if (s.equalsIgnoreCase("SPHERE")) {
            this.E = 90;
            return;
        }
        if (s.equalsIgnoreCase("CYLINDER")) {
            this.E = 91;
            return;
        }
        if (s.equalsIgnoreCase("CUBE")) {
            this.E = 92;
            return;
        }
        this.E = 90;
    }
    
    public final void b(final String s) {
        if (s != null) {
            try {
                this.F = -Float.valueOf(s);
            }
            catch (Exception ex) {
                this.F = 0.0f;
            }
        }
    }
    
    public final void a(final float n, final float n2, final float n3) {
        this.z = 0.0f;
        if (this.u - this.t < 0.001f) {
            this.A = 3.1415927f;
        }
        else {
            this.A = (this.u - this.t) * 0.5f;
        }
        this.B = 3.1415927f;
        this.z = n * 3.1415927f / 180.0f;
        this.A = n2 * 3.1415927f / 180.0f;
        this.B = n3 * 3.1415927f / 180.0f;
    }
    
    public void Set_File(final String u) {
        this.T = true;
        this.U = u;
    }
    
    public final void start() {
        this.removeAll();
        this.a();
        this.g();
        this.ResetApplet();
        ++this.c;
    }
    
    public final void stop() {
        this.c();
        if (this.a != null) {
            this.a.r();
            this.a = null;
        }
        Label_0043: {
            if (com.iseemedia.apps.tourclients40.resource.a.c) {
                if (com.iseemedia.apps.tourclients40.resource.a.a) {
                    break Label_0043;
                }
            }
            try {
                this.removeAll();
            }
            catch (Exception ex) {}
        }
        this.q = null;
        System.gc();
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            System.exit(0);
        }
        return super.handleEvent(event);
    }
    
    public float GetYaw() {
        if (this.a == null) {
            return 0.0f;
        }
        return this.a.d();
    }
    
    public void SetToolbar(final String s) {
        if (s != null) {
            com.iseemedia.apps.tourclients40.players.h.l = Integer.parseInt(s);
            return;
        }
        com.iseemedia.apps.tourclients40.players.h.l = 0;
    }
    
    public void ResetApplet() {
        if (this.T) {
            this.loadXML(this.U);
            this.T = false;
        }
        Color u = new Color(255, 255, 255);
        boolean b = false;
        if (this.a != null) {
            b = true;
            u = this.a.u();
            this.a.r();
            this.c();
            this.removeAll();
            this.a = null;
            System.gc();
        }
        this.h();
        this.setBackground(u);
        if (this.q != null && b) {
            this.q.setBackground(u);
        }
        if (this.b.equalsIgnoreCase("ZOOM2d")) {
            this.add(this.a = new f(this.getDocumentBase(), this.getAppletContext(), this.size(), this.q, this.D, this.G, this, this.H));
            this.a.C = this.f;
            this.a.a(this.h, this.Q);
            this.a.a(this.m, this.n, this.o, this.p, true);
            this.a.a(this.O);
            this.a.a(this.R);
        }
        else if (this.b.equalsIgnoreCase("ZOOMPANO")) {
            this.a = new e(this.getDocumentBase(), this.getAppletContext(), this.size(), this.q, this.D, this.G, this, this.H);
            ((e)this.a).a = this.ad;
            this.add(this.a);
            System.gc();
            this.a.C = this.f;
            this.a.a(this.O);
            this.a.a(this.R);
            this.a.a(this.z, this.A, 0.0f, this.B);
            this.a.a(this.C, false);
            this.a.a(this.h, this.E, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.F, this.Q);
            final float[] array = { this.z, this.A, 0.0f };
            this.a.a(this.B);
            this.a.a(array);
        }
        if (this.a == null) {
            return;
        }
        this.Set_Font(this.ae);
        this.Q = true;
        this.f = false;
        this.a.D = this.d;
        if (this.d) {
            this.a.b(this.ac * 1000);
        }
        System.gc();
        this.a.q();
    }
    
    public final void a() {
        this.q = null;
        this.C = 0.0f;
        this.D = false;
        this.F = 0.0f;
        this.G = null;
        this.d = false;
        this.Z = false;
        this.aa = "";
        this.ab = "";
        this.ac = 20;
        this.ae = "Arial Black, Font.BOLD, 12";
        this.f = false;
    }
    
    public final void c(final String s) {
        this.h = this.M + s;
    }
    
    public final void b() {
        if (!this.d) {
            this.d();
            return;
        }
        (this.e = new Thread(new com.iseemedia.apps.tourclients40.applets.a(this), "audio")).start();
    }
    
    public final void c() {
        if (this.P != null) {
            this.P.stop();
        }
    }
    
    public final void d() {
        if (this.N != null) {
            try {
                if (this.K == null) {
                    (this.P = this.getAudioClip(this.getDocumentBase(), this.N)).play();
                }
                else {
                    (this.P = this.getAudioClip(new URL(this.K + this.N))).play();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final Vector d(final String s) {
        if (s == null) {
            return null;
        }
        final Vector<String> vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int n = 0;
        int n2 = 0;
        try {
            while (stringTokenizer.hasMoreTokens()) {
                final String trim;
                if (!(trim = stringTokenizer.nextToken().trim()).equalsIgnoreCase("repeat")) {
                    final String i;
                    if ((i = this.i(trim)) != null && i.length() != 0) {
                        vector.insertElementAt(i, n2);
                        ++n2;
                    }
                    this.W.insertElementAt(trim, n);
                }
                else if (trim.equalsIgnoreCase("repeat")) {
                    final String trim2;
                    if ((trim2 = stringTokenizer.nextToken().trim()) == null) {
                        this.ac = 20;
                        break;
                    }
                    this.ac = Integer.parseInt(trim2);
                    break;
                }
                ++n;
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Need the re format input");
            return null;
        }
        if (this.V != null) {
            return vector;
        }
        return null;
    }
    
    private String i(final String s) {
        this.i();
        String s2 = "";
        final String[] a = this.a(s, " <>\t\r\n");
        for (int i = 0; i < a.length; ++i) {
            if (a[i].equalsIgnoreCase("ImageNavigator")) {
                while (!a[i + 1].equalsIgnoreCase("/ImageNavigator")) {
                    ++i;
                    if (a[i].equalsIgnoreCase("audioFile")) {
                        ++i;
                        s2 = a[i];
                    }
                }
            }
        }
        return s2;
    }
    
    public void Set_Font(final String ae) {
        if (ae == null) {
            this.ae = "Arial Black, Font.BOLD, 12";
        }
        if (this.a != null) {
            this.a.b(ae);
            this.ae = ae;
        }
    }
    
    public final boolean e(final String s) {
        return s != null && s.equalsIgnoreCase("on");
    }
    
    public final void e() {
        this.g = (this.g + 1) % this.Y;
        this.Set_File(this.U = this.W.elementAt(this.g));
        this.f();
    }
    
    public final void f() {
        if (this.a != null) {
            this.a.r();
            this.remove(this.a);
            this.a = null;
            System.gc();
        }
        this.h();
        if (this.T) {
            this.loadXML(this.U);
            if (this.T) {
                this.T = false;
            }
        }
        if (this.b.equalsIgnoreCase("ZOOM2d")) {
            this.add(this.a = new f(this.getDocumentBase(), this.getAppletContext(), this.size(), this.q, this.D, this.G, this, this.H));
            this.a.C = this.f;
            this.a.b(5000);
            this.a.a(this.h, this.Q);
            this.a.a(this.m, this.n, this.o, this.p, true);
        }
        else if (this.b.equalsIgnoreCase("ZOOMPANO")) {
            this.add(this.a = new e(this.getDocumentBase(), this.getAppletContext(), this.size(), this.q, this.D, this.G, this, this.H));
            this.a.C = this.f;
            this.a.b(this.ac * 1000);
            this.a.a(this.z, this.A, 0.0f, this.B);
            this.a.a(this.C, false);
            this.a.a(this.h, this.E, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.F, this.Q);
            final float[] array = { this.z, this.A, 0.0f };
            this.a.a(this.B);
            this.a.a(array);
        }
        this.Set_Font(this.ae);
        this.Q = false;
        this.f = false;
        this.a.D = this.d;
        this.a.q();
    }
    
    public void Pause() {
        this.a.u = true;
    }
    
    static final String a(final b b, final String n) {
        return b.N = n;
    }
    
    static final Vector a(final b b) {
        return b.V;
    }
    
    static final AudioClip a(final b b, final AudioClip p2) {
        return b.P = p2;
    }
    
    static final String b(final b b) {
        return b.N;
    }
    
    static final AudioClip c(final b b) {
        return b.P;
    }
    
    static final int d(final b b) {
        return b.ac;
    }
    
    static final int e(final b b) {
        return b.X;
    }
}
