import java.io.IOException;
import java.awt.Event;
import java.io.StringBufferInputStream;
import java.io.EOFException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Container;
import java.io.InputStream;
import zp.a.a.a.c.e;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import zp.a.a.a.d.a;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Frame;
import zp.a.a.a.b.b;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class zoompanoapplet extends Applet
{
    public static final String int = "©2002 iSeeMedia Inc. All rights reserved.";
    private b l;
    private byte[] q;
    private String[] s;
    private Frame d;
    private float r;
    private float f;
    private float n;
    private float void;
    private float char;
    private float long;
    private float t;
    private float e;
    private float c;
    private float j;
    private float k;
    private String m;
    private String goto;
    private float b;
    private boolean i;
    private boolean u;
    private String v;
    private int if;
    private int else;
    private int for;
    private int z;
    private int h;
    private int w;
    private int a;
    private int p;
    private int try;
    private String g;
    private boolean o;
    private float do;
    private String case;
    private String byte;
    private static final String null = "Zoom_Toolbar.gif";
    private Image new;
    
    public zoompanoapplet() {
        this.q = new byte[256];
        this.s = new String[64];
    }
    
    public void init() {
        this.setLayout(null);
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final InputStream a = zp.a.a.a.d.a.a("Zoom_Toolbar.gif");
            final byte[] array = new byte[32768];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            mediaTracker.addImage(this.new = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray()), 0);
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {}
        zp.a.a.a.c.e.if(this.new);
    }
    
    private void do() {
        this.a(this.getParameter("toolbar"));
        this.Set_backgroundImage(this.getParameter("backgroundImage"));
        this.Set_leftMargin(this.getParameter("leftMargin"));
        this.Set_topMargin(this.getParameter("topMargin"));
        this.Set_displayWidth(this.getParameter("displayWidth"));
        this.Set_displayHeight(this.getParameter("displayHeight"));
        this.Set_infoPage(this.getParameter("infoPage"));
        this.Set_foregroundFrame(this.getParameter("foregroundFrame"));
        this.Set_showHotspots(this.getParameter("showHotspots"));
        this.Set_minZoomAngle(this.getParameter("minZoomAngle"));
        this.Set_enableZoomPastMax(this.getParameter("enableZoomPastMax"));
        this.Set_backgroundColor(this.getParameter("backgroundColor"));
        this.Set_file(this.getParameter("file"));
        this.Set_initialView(this.getParameter("initialView"));
        this.a();
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
    
    private void for() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null) {
            this.d = (Frame)container;
        }
    }
    
    private void if(final String s) {
        boolean b = false;
        this.g = null;
        this.try = 0;
        this.r = -1.5707964f;
        this.f = 1.5707964f;
        this.n = 0.0f;
        this.void = 0.0f;
        this.char = -100.0f;
        this.long = -100.0f;
        this.t = -100.0f;
        this.e = -100.0f;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), s).openStream());
            while (true) {
                if (this.q[0] == 117) {
                    if (this.q[1] == 114 && this.q[2] == 108 && this.q[3] == 32 && this.q[4] == 34) {
                        break;
                    }
                    if (this.q[1] == 114 && this.q[2] == 108 && this.q[3] == 32 && this.q[4] == 91) {
                        b = true;
                        break;
                    }
                }
                this.q[0] = this.q[1];
                this.q[1] = this.q[2];
                this.q[2] = this.q[3];
                this.q[3] = this.q[4];
                this.q[4] = dataInputStream.readByte();
            }
            this.g = this.a(dataInputStream, true);
            Label_0281: {
                if (!b) {
                    break Label_0281;
                }
                this.g = this.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = this.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = this.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.try = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.try = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.try = 2;
                            }
                            else {
                                this.try = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.r = Float.valueOf(this.a(dataInputStream, false));
                            this.f = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.n = Float.valueOf(this.a(dataInputStream, false));
                            this.void = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.char = Float.valueOf(this.a(dataInputStream, false));
                            this.long = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.t = Float.valueOf(this.a(dataInputStream, false));
                            this.e = Float.valueOf(this.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex) {}
            }
        }
        catch (Exception ex2) {
            this.g = null;
            this.try = 0;
            this.r = -1.5707964f;
            this.f = 1.5707964f;
            this.n = 0.0f;
            this.void = 0.0f;
            this.char = -100.0f;
            this.long = -100.0f;
            this.t = -100.0f;
            this.e = -100.0f;
        }
        if (this.char == -100.0f) {
            this.char = this.r;
        }
        if (this.long == -100.0f) {
            this.long = this.f;
        }
        if (this.t == -100.0f) {
            this.t = this.n;
        }
        if (this.e == -100.0f) {
            this.e = this.void;
        }
    }
    
    private void if() {
        for (int i = 0; i < 64; ++i) {
            final String s = this.s[i];
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
                    this.l.a(i, floatValue, floatValue2, floatValue3, floatValue4, a, a2, a3, b);
                }
                catch (Exception ex5) {}
            }
        }
    }
    
    public void start() {
        this.removeAll();
        this.ClearParameters();
        this.do();
        this.ResetApplet();
    }
    
    public void stop() {
        if (this.l != null) {
            this.l.do();
            this.l = null;
        }
        Label_0039: {
            if (zp.a.a.a.d.a.byte) {
                if (zp.a.a.a.d.a.case) {
                    break Label_0039;
                }
            }
            try {
                this.removeAll();
            }
            catch (Exception ex) {}
        }
        this.d = null;
        System.gc();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            System.exit(0);
        }
        return super.handleEvent(event);
    }
    
    private void a() {
        final String string = this.getDocumentBase().toString();
        if (string != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(string));
            final float j = this.j;
            final float c = this.c;
            final float k = this.k;
            try {
                for (int i = 46; i != 63; i = (char)dataInputStream.readByte()) {}
                if (this.a(dataInputStream, false).equalsIgnoreCase("initialView")) {
                    this.c = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.j = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.k = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                }
            }
            catch (Exception ex) {
                this.j = j;
                this.c = c;
                this.k = k;
            }
        }
    }
    
    private String a(final DataInputStream dataInputStream, final boolean b) throws EOFException, IOException {
        int i = 0;
        while (true) {
            this.q[0] = dataInputStream.readByte();
            if (b) {
                if (this.q[0] != 34 && this.q[0] != 32 && this.q[0] != 9 && this.q[0] != 13 && this.q[0] != 10) {
                    break;
                }
                continue;
            }
            else {
                if (this.q[0] != 34 && this.q[0] != 61 && this.q[0] != 32 && this.q[0] != 91 && this.q[0] != 9 && this.q[0] != 13 && this.q[0] != 10) {
                    break;
                }
                continue;
            }
        }
        while (true) {
            if (b) {
                if (this.q[i] == 34 || this.q[i] == 44 || this.q[i] == 91 || this.q[i] == 9 || this.q[i] == 13) {
                    break;
                }
                if (this.q[i] == 10) {
                    break;
                }
            }
            else {
                if (this.q[i] == 34 || this.q[i] == 44 || this.q[i] == 32 || this.q[i] == 91 || this.q[i] == 9 || this.q[i] == 13) {
                    break;
                }
                if (this.q[i] == 10) {
                    break;
                }
            }
            ++i;
            try {
                this.q[i] = dataInputStream.readByte();
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
            array[i] = this.q[i];
            --i;
        }
        return new String(array, 0);
    }
    
    public static void a(final String[] array) {
        final Frame frame = new Frame("Pano Player");
        final zoompanoapplet zoompanoapplet = new zoompanoapplet();
        zoompanoapplet.init();
        zoompanoapplet.start();
        frame.add("Center", zoompanoapplet);
        frame.resize(300, 200);
        frame.show();
    }
    
    public void ResetApplet() {
        if (this.l != null) {
            this.l.do();
            this.remove(this.l);
            this.l = null;
            System.gc();
        }
        this.for();
        this.add(this.l = new b(this.getDocumentBase(), this.getAppletContext(), this.v, this.if, this.else, this.for, this.z, this.u, this.h, this.w, this.a, this.p, this.size(), this.d, this.i, this.m, this.goto, this.case, this.byte));
        this.l.a(this.g, this.try, this.r, this.f, this.n, this.void, this.char, this.long, this.t, this.e, this.do, this.o);
        final float[] array = { this.c, this.j, 0.0f };
        this.l.a(this.c, this.j, 0.0f, this.k);
        this.l.a(this.b, false);
        this.l.a(this.k);
        this.l.a(array);
        this.l.a(this.k);
        this.if();
        this.l.char();
    }
    
    public void ClearParameters() {
        this.d = null;
        this.m = null;
        this.goto = null;
        this.b = 0.08726647f;
        this.i = false;
        this.u = false;
        this.v = null;
        this.if = 0;
        this.else = 0;
        this.for = 0;
        this.z = 0;
        this.h = 255;
        this.w = 255;
        this.a = 255;
        this.p = 2;
        this.g = null;
        this.o = false;
        this.do = 20.0f;
        this.case = null;
        this.byte = null;
    }
    
    public void a(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("on")) {
                this.p = 0;
            }
            else if (s.equalsIgnoreCase("off")) {
                this.p = 1;
            }
            else if (s.equalsIgnoreCase("collapsed")) {
                this.p = 2;
            }
        }
    }
    
    public void Set_initialView(String s) {
        this.c = 0.0f;
        if (this.void - this.n < 0.001f) {
            this.j = 3.1415927f;
        }
        else {
            this.j = (this.void - this.n) * 0.5f;
        }
        this.k = 0.87266463f;
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.c = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.j = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.k = Float.valueOf(s) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.c = 0.0f;
                if (this.n == this.void) {
                    this.j = 3.1415927f;
                }
                else {
                    this.j = (this.void - this.n) * 0.5f;
                }
                this.k = 0.87266463f;
            }
        }
    }
    
    public void Set_backgroundImage(final String v) {
        this.v = v;
    }
    
    public void Set_altSplash(final String byte1) {
        this.byte = byte1;
    }
    
    public void Set_altIcon(final String case1) {
        this.case = case1;
    }
    
    public void Set_leftMargin(final String s) {
        try {
            this.if = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.if = 0;
        }
    }
    
    public void Set_topMargin(final String s) {
        try {
            this.else = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.else = 0;
        }
    }
    
    public void Set_displayWidth(final String s) {
        try {
            this.for = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.for = this.size().width;
        }
    }
    
    public void Set_displayHeight(final String s) {
        try {
            this.z = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.z = this.size().height;
        }
    }
    
    public void Set_infoPage(final String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                this.m = this.a(dataInputStream, false);
            }
            catch (Exception ex) {
                this.m = null;
            }
            try {
                this.goto = this.a(dataInputStream, false);
            }
            catch (Exception ex2) {
                this.goto = null;
            }
        }
    }
    
    public void Set_showHotspots(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.o = true;
        }
    }
    
    public void Set_foregroundFrame(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.u = true;
        }
    }
    
    public void Set_enableZoomPastMax(final String s) {
        if (s != null && s.equalsIgnoreCase("on")) {
            this.i = true;
        }
    }
    
    public void Set_backgroundColor(String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.h = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.w = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.a = Integer.valueOf(s);
            }
            catch (Exception ex) {
                this.h = 255;
                this.w = 255;
                this.a = 255;
            }
        }
    }
    
    public void Set_file(final String s) {
        if (s != null) {
            this.if(s);
        }
    }
    
    public void Set_minZoomAngle(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.b = Float.valueOf(a) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.b = 0.08726647f;
            }
        }
    }
    
    public void Set_autospin(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.do = Float.valueOf(a);
            }
            catch (Exception ex) {
                this.do = 20.0f;
            }
        }
    }
    
    public void Add_hotspot(final int n, final String s) {
        if (n >= 63) {
            return;
        }
        this.s[n] = s;
    }
    
    public void SetView(float n, float n2, float n3, final long n4) {
        final float[] array = new float[3];
        if (this.l == null) {
            return;
        }
        if (!this.l.O) {
            this.l.try();
        }
        final float c = this.l.c();
        this.l.a(1.0E-4f, false);
        n *= 0.017453292f;
        n2 *= 0.017453292f;
        n3 *= 0.017453292f;
        if (n4 < 1L) {
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.l.a(array);
            this.l.a(n3);
            this.l.try = true;
        }
        else {
            final float[] array2 = new float[3];
            this.l.if(array2);
            final float i = this.l.i();
            long n6;
            for (long n5 = n6 = System.currentTimeMillis(); n6 - n5 < n4; n6 = System.currentTimeMillis()) {
                final float n7 = (n6 - n5) / n4;
                array[0] = array2[0] + (n - array2[0]) * n7;
                array[1] = array2[1] + (n2 - array2[1]) * n7;
                array[2] = 0.0f;
                final float n8 = i + (n3 - i) * n7;
                this.l.a(array);
                this.l.a(n8);
                this.l.try = true;
            }
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.l.a(array);
            this.l.a(n3);
            this.l.try = true;
        }
        this.l.a(c, false);
    }
    
    public void ShowHotSpots() {
        if (this.l != null) {
            this.l.for();
        }
    }
    
    public void HideHotSpots() {
        if (this.l != null) {
            this.l.if();
        }
    }
    
    public void Reset() {
        if (this.l != null) {
            this.l.new();
        }
    }
    
    public void a(final int n) {
        if (this.l != null) {
            this.l.a(n);
        }
    }
    
    public void CenterZoomIn() {
        if (this.l != null) {
            this.l.g();
        }
    }
    
    public void CenterZoomOut() {
        if (this.l != null) {
            this.l.b();
        }
    }
    
    public String getAppletInfo() {
        return "MGI Software Viewer--Java version for ZOOM 2D Images 3.5, (c) 2000 MGI Software Corp.";
    }
    
    public float GetPitch() {
        if (this.l == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.l.if(array);
        return array[0] * 180.0f / 3.1415927f;
    }
    
    public float GetYaw() {
        if (this.l == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.l.if(array);
        return array[1] * 180.0f / 3.1415927f;
    }
    
    public float GetZoom() {
        if (this.l == null) {
            return 1.0f;
        }
        return this.l.i() * 180.0f / 3.1415927f;
    }
}
