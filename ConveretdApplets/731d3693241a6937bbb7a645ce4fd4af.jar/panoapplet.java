import pa.a.a.a.d.b;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import pa.a.a.a.c.a;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Component;
import java.net.URL;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.Container;
import java.awt.Image;
import java.awt.Frame;
import pa.a.a.a.a.c;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class panoapplet extends Applet
{
    public static final String byte = "©1998 Live Picture Corp. All rights reserved.";
    private c d;
    private byte[] g;
    private String[] o;
    private Frame null;
    private float h;
    private float l;
    private float c;
    private float e;
    private float try;
    private float do;
    private float t;
    private float b;
    private float case;
    private float for;
    private float else;
    private String long;
    private String new;
    private float w;
    private boolean if;
    private String a;
    private int f;
    private int i;
    private int char;
    private int j;
    private int goto;
    private int s;
    private int int;
    private int v;
    private int r;
    private String u;
    private boolean void;
    private float k;
    private String p;
    private String m;
    private static final String n = "Zoom_ToolBar.gif";
    private Image q;
    
    public panoapplet() {
        this.g = new byte[256];
        this.o = new String[64];
    }
    
    public void Add_hotspot(final int n, final String s) {
        if (n >= 63) {
            return;
        }
        this.o[n] = s;
    }
    
    public void CenterZoomIn() {
        if (this.d != null) {
            this.d.h();
        }
    }
    
    public void CenterZoomOut() {
        if (this.d != null) {
            this.d.c();
        }
    }
    
    public void ClearParameters() {
        this.null = null;
        this.long = null;
        this.new = null;
        this.w = 0.17453294f;
        this.if = false;
        this.a = null;
        this.f = 0;
        this.i = 0;
        this.char = 0;
        this.j = 0;
        this.goto = 255;
        this.s = 255;
        this.int = 255;
        this.v = 2;
        this.u = null;
        this.void = false;
        this.k = 20.0f;
        this.p = null;
        this.m = null;
    }
    
    private void a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null) {
            this.null = (Frame)container;
        }
    }
    
    private void do() {
        this.if(this.getParameter("toolbar"));
        this.Set_backgroundImage(this.getParameter("backgroundImage"));
        this.Set_leftMargin(this.getParameter("leftMargin"));
        this.Set_topMargin(this.getParameter("topMargin"));
        this.Set_displayWidth(this.getParameter("displayWidth"));
        this.Set_displayHeight(this.getParameter("displayHeight"));
        this.Set_infoPage(this.getParameter("infoPage"));
        this.Set_foregroundFrame(this.getParameter("foregroundFrame"));
        this.Set_showHotspots(this.getParameter("showHotspots"));
        this.Set_minZoomAngle(this.getParameter("minZoomAngle"));
        this.Set_backgroundColor(this.getParameter("backgroundColor"));
        this.Set_file(this.getParameter("file"));
        this.Set_initialView(this.getParameter("initialView"));
        this.if();
        this.Set_autospin(this.getParameter("autospin"));
        this.Set_altSplash(this.getParameter("altSplash"));
        this.Set_altIcon(this.getParameter("altIcon"));
        int n = 1;
        while (true) {
            final String parameter = this.getParameter("hotspot" + n);
            if (parameter == null) {
                break;
            }
            this.Add_hotspot(n - 1, parameter);
            ++n;
        }
    }
    
    public float GetPitch() {
        if (this.d == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.d.if(array);
        return array[0] * 180.0f / 3.1415927f;
    }
    
    private String a(final DataInputStream dataInputStream, final boolean b) throws EOFException, IOException {
        int i = 0;
        while (true) {
            this.g[0] = dataInputStream.readByte();
            if (b) {
                if (this.g[0] != 34 && this.g[0] != 32 && this.g[0] != 9 && this.g[0] != 13 && this.g[0] != 10) {
                    break;
                }
                continue;
            }
            else {
                if (this.g[0] != 34 && this.g[0] != 61 && this.g[0] != 32 && this.g[0] != 91 && this.g[0] != 9 && this.g[0] != 13 && this.g[0] != 10) {
                    break;
                }
                continue;
            }
        }
        while (true) {
            if (b) {
                if (this.g[i] == 34 || this.g[i] == 91 || this.g[i] == 9 || this.g[i] == 13) {
                    break;
                }
                if (this.g[i] == 10) {
                    break;
                }
            }
            else {
                if (this.g[i] == 34 || this.g[i] == 44 || this.g[i] == 32 || this.g[i] == 91 || this.g[i] == 9 || this.g[i] == 13) {
                    break;
                }
                if (this.g[i] == 10) {
                    break;
                }
            }
            ++i;
            try {
                this.g[i] = dataInputStream.readByte();
                continue;
            }
            catch (Exception ex) {}
            break;
        }
        if (i == 0) {
            return null;
        }
        final byte[] array = new byte[i];
        --i;
        while (i >= 0) {
            array[i] = this.g[i];
            --i;
        }
        return new String(array, 0);
    }
    
    public float GetYaw() {
        if (this.d == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.d.if(array);
        return array[1] * 180.0f / 3.1415927f;
    }
    
    public float GetZoom() {
        if (this.d == null) {
            return 1.0f;
        }
        return this.d.g() * 180.0f / 3.1415927f;
    }
    
    public void HideHotSpots() {
        if (this.d != null) {
            this.d.char();
        }
    }
    
    private void for() {
        for (int i = 0; i < 64; ++i) {
            final String s = this.o[i];
            if (s != null) {
                final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
                try {
                    final float floatValue = Float.valueOf(this.a(dataInputStream, false));
                    final float floatValue2 = Float.valueOf(this.a(dataInputStream, false));
                    final float floatValue3 = Float.valueOf(this.a(dataInputStream, false));
                    final float floatValue4 = Float.valueOf(this.a(dataInputStream, false));
                    String a;
                    try {
                        a = this.a(dataInputStream, false);
                    }
                    catch (Exception ex) {
                        a = null;
                    }
                    String a2;
                    try {
                        a2 = this.a(dataInputStream, true);
                    }
                    catch (Exception ex2) {
                        a2 = null;
                    }
                    String a3;
                    try {
                        a3 = this.a(dataInputStream, false);
                    }
                    catch (Exception ex3) {
                        a3 = null;
                    }
                    boolean b;
                    try {
                        b = true;
                        if (this.a(dataInputStream, false).equalsIgnoreCase("false")) {
                            b = false;
                        }
                    }
                    catch (Exception ex4) {
                        b = true;
                    }
                    this.d.a(i, floatValue, floatValue2, floatValue3, floatValue4, a, a2, a3, b);
                }
                catch (Exception ex5) {}
            }
        }
    }
    
    private void a(final String s) {
        boolean b = false;
        this.u = null;
        this.r = 0;
        this.h = -1.5707964f;
        this.l = 1.5707964f;
        this.c = 0.0f;
        this.e = 0.0f;
        this.try = -100.0f;
        this.do = -100.0f;
        this.t = -100.0f;
        this.b = -100.0f;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), s).openStream());
            while (true) {
                if (this.g[0] == 117) {
                    if (this.g[1] == 114 && this.g[2] == 108 && this.g[3] == 32 && this.g[4] == 34) {
                        break;
                    }
                    if (this.g[1] == 114 && this.g[2] == 108 && this.g[3] == 32 && this.g[4] == 91) {
                        b = true;
                        break;
                    }
                }
                this.g[0] = this.g[1];
                this.g[1] = this.g[2];
                this.g[2] = this.g[3];
                this.g[3] = this.g[4];
                this.g[4] = dataInputStream.readByte();
            }
            this.u = this.a(dataInputStream, true);
            Label_0281: {
                if (!b) {
                    break Label_0281;
                }
                this.u = this.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = this.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = this.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.r = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.r = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.r = 2;
                            }
                            else {
                                this.r = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.h = Float.valueOf(this.a(dataInputStream, false));
                            this.l = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.c = Float.valueOf(this.a(dataInputStream, false));
                            this.e = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.try = Float.valueOf(this.a(dataInputStream, false));
                            this.do = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.t = Float.valueOf(this.a(dataInputStream, false));
                            this.b = Float.valueOf(this.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex) {}
            }
        }
        catch (Exception ex2) {
            this.u = null;
            this.r = 0;
            this.h = -1.5707964f;
            this.l = 1.5707964f;
            this.c = 0.0f;
            this.e = 0.0f;
            this.try = -100.0f;
            this.do = -100.0f;
            this.t = -100.0f;
            this.b = -100.0f;
        }
        if (this.try == -100.0f) {
            this.try = this.h;
        }
        if (this.do == -100.0f) {
            this.do = this.l;
        }
        if (this.t == -100.0f) {
            this.t = this.c;
        }
        if (this.b == -100.0f) {
            this.b = this.e;
        }
    }
    
    public void Reset() {
        if (this.d != null) {
            this.d.byte();
        }
    }
    
    public void ResetApplet() {
        if (this.d != null) {
            this.d.else();
            this.remove(this.d);
            this.d = null;
            System.gc();
        }
        this.a();
        this.add(this.d = new c(this.getDocumentBase(), this.getAppletContext(), this.a, this.f, this.i, this.char, this.j, this.if, this.goto, this.s, this.int, this.v, this.size(), this.null, this.long, this.new, this.p, this.m));
        this.d.a(this.u, this.r, this.h, this.l, this.c, this.e, this.try, this.do, this.t, this.b, this.k, this.void);
        final float[] array = { this.case, this.for, 0.0f };
        this.d.a(this.case, this.for, 0.0f, this.else);
        this.d.a(this.w, false);
        this.d.a(this.else);
        this.d.a(array);
        this.d.a(this.else);
        this.for();
        this.d.new();
    }
    
    public void SetView(float n, float n2, float n3, final long n4) {
        final float[] array = new float[3];
        if (this.d == null) {
            return;
        }
        final float f = this.d.f();
        this.d.a(1.0E-4f, false);
        n *= 0.017453292f;
        n2 *= 0.017453292f;
        n3 *= 0.017453292f;
        if (n4 < 1L) {
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.d.a(array);
            this.d.a(n3);
            this.d.new = true;
        }
        else {
            final float[] array2 = new float[3];
            this.d.if(array2);
            final float g = this.d.g();
            long n6;
            for (long n5 = n6 = System.currentTimeMillis(); n6 - n5 < n4; n6 = System.currentTimeMillis()) {
                final float n7 = (n6 - n5) / n4;
                array[0] = array2[0] + (n - array2[0]) * n7;
                array[1] = array2[1] + (n2 - array2[1]) * n7;
                array[2] = 0.0f;
                final float n8 = g + (n3 - g) * n7;
                this.d.a(array);
                this.d.a(n8);
                this.d.new = true;
            }
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.d.a(array);
            this.d.a(n3);
            this.d.new = true;
        }
        this.d.a(f, false);
    }
    
    public void Set_altIcon(final String p) {
        this.p = p;
    }
    
    public void Set_altSplash(final String m) {
        this.m = m;
    }
    
    public void Set_autospin(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.k = Float.valueOf(a);
            }
            catch (Exception ex) {
                this.k = 20.0f;
            }
        }
    }
    
    public void Set_backgroundColor(String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.goto = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.s = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.int = Integer.valueOf(s);
            }
            catch (Exception ex) {
                this.goto = 255;
                this.s = 255;
                this.int = 255;
            }
        }
    }
    
    public void Set_backgroundImage(final String a) {
        this.a = a;
    }
    
    public void Set_displayHeight(final String s) {
        try {
            this.j = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.j = this.size().height;
        }
    }
    
    public void Set_displayWidth(final String s) {
        try {
            this.char = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.char = this.size().width;
        }
    }
    
    public void Set_file(final String s) {
        if (s != null) {
            this.a(s);
        }
    }
    
    public void Set_foregroundFrame(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.if = true;
        }
    }
    
    public void Set_infoPage(final String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                this.long = this.a(dataInputStream, false);
            }
            catch (Exception ex) {
                this.long = null;
            }
            try {
                this.new = this.a(dataInputStream, false);
            }
            catch (Exception ex2) {
                this.new = null;
            }
        }
    }
    
    public void Set_initialView(String s) {
        this.case = 0.0f;
        if (this.e - this.c < 0.001f) {
            this.for = 3.1415927f;
        }
        else {
            this.for = (this.e - this.c) * 0.5f;
        }
        this.else = 0.87266463f;
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.case = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.for = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.else = Float.valueOf(s) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.case = 0.0f;
                if (this.c == this.e) {
                    this.for = 3.1415927f;
                }
                else {
                    this.for = (this.e - this.c) * 0.5f;
                }
                this.else = 0.87266463f;
            }
        }
    }
    
    public void Set_leftMargin(final String s) {
        try {
            this.f = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.f = 0;
        }
    }
    
    public void Set_minZoomAngle(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.w = Float.valueOf(a) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.w = 0.17453294f;
            }
        }
    }
    
    public void Set_showHotspots(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.void = true;
        }
    }
    
    public void if(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.v = 0;
            }
            else if (s.equalsIgnoreCase("off")) {
                this.v = 1;
            }
            else if (s.equalsIgnoreCase("collapsed")) {
                this.v = 2;
            }
        }
    }
    
    public void Set_topMargin(final String s) {
        try {
            this.i = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.i = 0;
        }
    }
    
    public void ShowHotSpots() {
        if (this.d != null) {
            this.d.if();
        }
    }
    
    public void ShowInfo() {
        if (this.d != null) {
            this.d.goto();
        }
    }
    
    public String getAppletInfo() {
        return "MGI Software Viewer--Java version for ZOOM 2D Images 3.5, (c) 2000 MGI Software Corp.";
    }
    
    public void init() {
        this.setLayout(null);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final InputStream a = pa.a.a.a.c.a.a("Zoom_ToolBar.gif");
            final byte[] array = new byte[32768];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            mediaTracker.addImage(this.q = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray()), 0);
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {}
        pa.a.a.a.d.b.if(this.q);
    }
    
    public static void a(final String[] array) {
        final Frame frame = new Frame("Pano Player");
        final panoapplet panoapplet = new panoapplet();
        panoapplet.init();
        panoapplet.start();
        frame.add("Center", panoapplet);
        frame.resize(300, 200);
        frame.show();
    }
    
    private void if() {
        final String string = this.getDocumentBase().toString();
        if (string != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(string));
            final float for1 = this.for;
            final float case1 = this.case;
            final float else1 = this.else;
            try {
                for (int i = 46; i != 63; i = (char)dataInputStream.readByte()) {}
                if (this.a(dataInputStream, false).equalsIgnoreCase("initialView")) {
                    this.case = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.for = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.else = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                }
            }
            catch (Exception ex) {
                this.for = for1;
                this.case = case1;
                this.else = else1;
            }
        }
    }
    
    public void start() {
        if (this.d == null) {
            this.removeAll();
            this.ClearParameters();
            this.do();
            this.ResetApplet();
        }
    }
    
    public void stop() {
        if (this.d != null) {
            this.d.else();
            this.d = null;
        }
        Label_0042: {
            if (pa.a.a.a.c.a.for) {
                if (pa.a.a.a.c.a.a) {
                    break Label_0042;
                }
            }
            try {
                this.removeAll();
            }
            catch (Exception ex) {}
        }
        this.null = null;
    }
}
