// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a.a;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import pa.a.b.a.g;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.MediaTracker;
import pa.a.b.a.f;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.net.URL;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.Container;
import pa.a.a.a.a;
import java.awt.Image;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class d extends Applet implements ActionListener
{
    private static final String p = "images/objectbar.gif";
    private c f;
    private byte[] i;
    private String[] q;
    private Frame b;
    private float j;
    private float n;
    private float e;
    private float g;
    private float byte;
    private float do;
    private float w;
    private float d;
    private float char;
    private float int;
    private float goto;
    private String null;
    private String try;
    private float A;
    private boolean if;
    private String a;
    private int h;
    private int k;
    private int else;
    private int l;
    private int long;
    private int v;
    private int new;
    private int u;
    private String z;
    private boolean c;
    private float m;
    private String r;
    private String o;
    private Image t;
    private a case;
    private pa.a.b.a.a s;
    private pa.a.a.b.a for;
    private boolean void;
    
    public d() {
        this.i = new byte[256];
        this.q = new String[64];
    }
    
    public void Add_hotspot(final int n, final String s) {
        if (n >= 63) {
            return;
        }
        this.q[n] = s;
        this.void = true;
    }
    
    public void CenterZoomIn() {
        if (this.f != null) {
            this.f.d();
        }
    }
    
    public void CenterZoomOut() {
        if (this.f != null) {
            this.f.else();
        }
    }
    
    public void ClearParameters() {
        this.b = null;
        this.null = null;
        this.try = null;
        this.A = 0.17453294f;
        this.if = false;
        this.a = null;
        this.h = 0;
        this.k = 0;
        this.else = 0;
        this.l = 0;
        this.long = 255;
        this.v = 255;
        this.new = 255;
        this.z = null;
        this.c = false;
        this.m = 20.0f;
        this.r = null;
        this.o = null;
    }
    
    private void a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null) {
            this.b = (Frame)container;
        }
    }
    
    private void for() {
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
        if (this.f == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.f.if(array);
        return array[0] * 180.0f / 3.1415927f;
    }
    
    private String a(final DataInputStream dataInputStream, final boolean b) throws EOFException, IOException {
        int i = 0;
        while (true) {
            this.i[0] = dataInputStream.readByte();
            if (b) {
                if (this.i[0] != 34 && this.i[0] != 32 && this.i[0] != 9 && this.i[0] != 13 && this.i[0] != 10) {
                    break;
                }
                continue;
            }
            else {
                if (this.i[0] != 34 && this.i[0] != 61 && this.i[0] != 32 && this.i[0] != 91 && this.i[0] != 9 && this.i[0] != 13 && this.i[0] != 10) {
                    break;
                }
                continue;
            }
        }
        while (true) {
            if (b) {
                if (this.i[i] == 34 || this.i[i] == 44 || this.i[i] == 91 || this.i[i] == 9 || this.i[i] == 13) {
                    break;
                }
                if (this.i[i] == 10) {
                    break;
                }
            }
            else {
                if (this.i[i] == 34 || this.i[i] == 44 || this.i[i] == 32 || this.i[i] == 91 || this.i[i] == 9 || this.i[i] == 13) {
                    break;
                }
                if (this.i[i] == 10) {
                    break;
                }
            }
            ++i;
            try {
                this.i[i] = dataInputStream.readByte();
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
            array[i] = this.i[i];
            --i;
        }
        return new String(array, 0);
    }
    
    public float GetYaw() {
        if (this.f == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        this.f.if(array);
        return array[1] * 180.0f / 3.1415927f;
    }
    
    public float GetZoom() {
        if (this.f == null) {
            return 1.0f;
        }
        return this.f.c() * 180.0f / 3.1415927f;
    }
    
    public void HideHotSpots() {
        if (this.f != null) {
            this.f.void();
        }
    }
    
    private void int() {
        for (int i = 0; i < 64; ++i) {
            final String s = this.q[i];
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
                    this.f.a(i, floatValue, floatValue2, floatValue3, floatValue4, a, a2, a3, b);
                }
                catch (Exception ex5) {}
            }
        }
    }
    
    private void a(final String s) {
        boolean b = false;
        this.z = null;
        this.u = 0;
        this.j = -1.5707964f;
        this.n = 1.5707964f;
        this.e = 0.0f;
        this.g = 0.0f;
        this.byte = -100.0f;
        this.do = -100.0f;
        this.w = -100.0f;
        this.d = -100.0f;
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), s).openStream());
            while (true) {
                if (this.i[0] == 117) {
                    if (this.i[1] == 114 && this.i[2] == 108 && this.i[3] == 32 && this.i[4] == 34) {
                        break;
                    }
                    if (this.i[1] == 114 && this.i[2] == 108 && this.i[3] == 32 && this.i[4] == 91) {
                        b = true;
                        break;
                    }
                }
                this.i[0] = this.i[1];
                this.i[1] = this.i[2];
                this.i[2] = this.i[3];
                this.i[3] = this.i[4];
                this.i[4] = dataInputStream.readByte();
            }
            this.z = this.a(dataInputStream, true);
            Label_0281: {
                if (!b) {
                    break Label_0281;
                }
                this.z = this.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = this.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = this.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.u = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.u = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.u = 2;
                            }
                            else {
                                this.u = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.j = Float.valueOf(this.a(dataInputStream, false));
                            this.n = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.e = Float.valueOf(this.a(dataInputStream, false));
                            this.g = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.byte = Float.valueOf(this.a(dataInputStream, false));
                            this.do = Float.valueOf(this.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.w = Float.valueOf(this.a(dataInputStream, false));
                            this.d = Float.valueOf(this.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex) {}
            }
        }
        catch (Exception ex2) {
            this.z = null;
            this.u = 0;
            this.j = -1.5707964f;
            this.n = 1.5707964f;
            this.e = 0.0f;
            this.g = 0.0f;
            this.byte = -100.0f;
            this.do = -100.0f;
            this.w = -100.0f;
            this.d = -100.0f;
        }
        if (this.byte == -100.0f) {
            this.byte = this.j;
        }
        if (this.do == -100.0f) {
            this.do = this.n;
        }
        if (this.w == -100.0f) {
            this.w = this.e;
        }
        if (this.d == -100.0f) {
            this.d = this.g;
        }
    }
    
    public void Reset() {
        if (this.f != null) {
            this.f.long();
        }
    }
    
    public void ResetApplet() {
        if (this.f != null) {
            this.f.int();
            this.removeAll();
            this.f = null;
            System.gc();
        }
        this.a();
        this.add(this.do(), "South");
        this.add(this.f = new c(this.getDocumentBase(), this.getAppletContext(), this.a, this.h, this.k, this.else, this.l, this.if, this.long, this.v, this.new, this.size(), this.b, this.null, this.try, this.r, this.o, this.for), "Center");
        this.invalidate();
        this.validate();
        this.f.a(this.z, this.u, this.j, this.n, this.e, this.g, this.byte, this.do, this.w, this.d, this.m, this.c);
        final float[] array = { this.char, this.int, 0.0f };
        this.f.a(this.char, this.int, 0.0f, this.goto);
        this.f.a(this.A, false);
        this.f.a(this.goto);
        this.f.a(array);
        this.f.a(this.goto);
        this.int();
        this.f.for();
    }
    
    public void SetView(float n, float n2, float n3, final long n4) {
        final float[] array = new float[3];
        if (this.f == null) {
            return;
        }
        this.f.try();
        final float b = this.f.b();
        this.f.a(1.0E-4f, false);
        n *= 0.017453292f;
        n2 *= 0.017453292f;
        n3 *= 0.017453292f;
        if (n4 < 1L) {
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.f.a(array);
            this.f.a(n3);
            this.f.ar = true;
        }
        else {
            final float[] array2 = new float[3];
            this.f.if(array2);
            final float c = this.f.c();
            long n6;
            for (long n5 = n6 = System.currentTimeMillis(); n6 - n5 < n4; n6 = System.currentTimeMillis()) {
                final float n7 = (n6 - n5) / n4;
                array[0] = array2[0] + (n - array2[0]) * n7;
                array[1] = array2[1] + (n2 - array2[1]) * n7;
                array[2] = 0.0f;
                final float n8 = c + (n3 - c) * n7;
                this.f.a(array);
                this.f.a(n8);
                this.f.ar = true;
            }
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            this.f.a(array);
            this.f.a(n3);
            this.f.ar = true;
        }
        this.f.a(b, false);
    }
    
    public void Set_altIcon(final String r) {
        this.r = r;
    }
    
    public void Set_altSplash(final String o) {
        this.o = o;
    }
    
    public void Set_autospin(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.m = Float.valueOf(a);
            }
            catch (Exception ex) {
                this.m = 20.0f;
            }
        }
    }
    
    public void Set_backgroundColor(String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.long = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.v = Integer.valueOf(s);
                s = this.a(dataInputStream, false);
                this.new = Integer.valueOf(s);
            }
            catch (Exception ex) {
                this.long = 255;
                this.v = 255;
                this.new = 255;
            }
        }
    }
    
    public void Set_backgroundImage(final String a) {
        this.a = a;
    }
    
    public void Set_displayHeight(final String s) {
        try {
            this.l = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.l = this.size().height;
        }
    }
    
    public void Set_displayWidth(final String s) {
        try {
            this.else = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.else = this.size().width;
        }
    }
    
    public void Set_file(final String s) {
        if (s != null) {
            this.a(s);
        }
    }
    
    public void Set_foregroundFrame(final String s) {
        if (s != null) {
            this.if = s.equalsIgnoreCase("true");
        }
    }
    
    public void Set_infoPage(final String s) {
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                this.null = this.a(dataInputStream, false);
            }
            catch (Exception ex) {
                this.null = null;
            }
            try {
                this.try = this.a(dataInputStream, false);
            }
            catch (Exception ex2) {
                this.try = null;
            }
        }
    }
    
    public void Set_initialView(String s) {
        this.char = 0.0f;
        if (this.g - this.e < 0.001f) {
            this.int = 3.1415927f;
        }
        else {
            this.int = (this.g - this.e) * 0.5f;
        }
        this.goto = 0.87266463f;
        if (s != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(s));
            try {
                s = this.a(dataInputStream, false);
                this.char = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.int = Float.valueOf(s) * 3.1415927f / 180.0f;
                s = this.a(dataInputStream, false);
                this.goto = Float.valueOf(s) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.char = 0.0f;
                if (this.e == this.g) {
                    this.int = 3.1415927f;
                }
                else {
                    this.int = (this.g - this.e) * 0.5f;
                }
                this.goto = 0.87266463f;
            }
        }
    }
    
    public void Set_leftMargin(final String s) {
        try {
            this.h = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.h = 0;
        }
    }
    
    public void Set_minZoomAngle(String a) {
        if (a != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(a));
            try {
                a = this.a(dataInputStream, false);
                this.A = Float.valueOf(a) * 3.1415927f / 180.0f;
            }
            catch (Exception ex) {
                this.A = 0.17453294f;
            }
        }
    }
    
    public void Set_showHotspots(final String s) {
        if (s != null) {
            this.c = s.equalsIgnoreCase("true");
        }
        if (this.f != null) {
            if (this.c) {
                this.f.byte();
            }
            else {
                this.f.void();
            }
        }
    }
    
    public void Set_topMargin(final String s) {
        try {
            this.k = Integer.parseInt(s);
        }
        catch (Exception ex) {
            this.k = 0;
        }
    }
    
    public void ShowHotSpots() {
        if (this.f != null) {
            this.f.byte();
        }
    }
    
    public void ShowInfo() {
        if (this.f != null) {
            this.f.e();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("logo")) {
            this.f.e();
        }
        if (actionEvent.getActionCommand().equals("zoomIn")) {
            this.f.a(0);
        }
        if (actionEvent.getActionCommand().equals("zoomOut")) {
            this.f.a(1);
        }
        if (actionEvent.getActionCommand().equals("pan")) {
            this.f.a(2);
        }
        if (actionEvent.getActionCommand().equals("reset")) {
            this.f.long();
        }
        if (actionEvent.getActionCommand().equals("hotspot")) {
            if (this.for.if("hotspot") == pa.a.a.a.a.goto) {
                this.HideHotSpots();
            }
            else {
                this.ShowHotSpots();
            }
        }
    }
    
    public String getAppletInfo() {
        return "MGI Software Viewer--Java version for Panoramas 3.5, (c) 2000 MGI Software Corp.";
    }
    
    private pa.a.b.a.a do() {
        this.s = new pa.a.b.a.a(Integer.parseInt(this.getParameter("width")));
        this.for = new pa.a.a.b.a(5);
        final String[] array = { "zoomIn", "zoomOut", "pan", "reset", "hotspot" };
        final a[] array2 = { new a(pa.a.b.a.f.a("zoomIn_on"), pa.a.b.a.f.a("zoomIn_off"), "zoomIn", false, pa.a.a.a.a.char, this.getAppletContext(), "zoomIn"), new a(pa.a.b.a.f.a("zoomOut_on"), pa.a.b.a.f.a("zoomOut_off"), "zoomOut", false, pa.a.a.a.a.char, this.getAppletContext(), "zoomOut"), new a(pa.a.b.a.f.a("pan_on"), pa.a.b.a.f.a("pan_off"), "pan", false, pa.a.a.a.a.char, this.getAppletContext(), "Pan"), new a(pa.a.b.a.f.a("reset_on"), pa.a.b.a.f.a("reset_off"), "reset", true, pa.a.a.a.a.null, this.getAppletContext(), "Reset"), null };
        boolean b = false;
        for (int i = 0; i < this.q.length; ++i) {
            if (this.q[i] != null) {
                b = true;
            }
        }
        if (b) {
            array2[4] = new a(pa.a.b.a.f.a("hotspot_on"), pa.a.b.a.f.a("hotspot_off"), "hotspot", true, pa.a.a.a.a.byte, this.getAppletContext(), "hotspot");
        }
        else {
            array2[4] = new a(pa.a.b.a.f.a("hotspot_disabled"));
        }
        for (int j = 0; j < 5; ++j) {
            array2[j].a(this);
        }
        this.for.a(array2, array, 5);
        this.for.a();
        this.for.setVisible(true);
        if (this.r != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Image image = this.getImage(this.getDocumentBase(), this.r);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
            pa.a.b.a.f.a(image, image);
        }
        (this.case = new a(pa.a.b.a.f.a("logo_off"), pa.a.b.a.f.a("logo"), "logo", true, pa.a.a.a.a.long, this.getAppletContext(), "logo")).a(this);
        this.s.add(this.case, "East");
        this.s.add(this.for, "West");
        this.s.setVisible(true);
        return this.s;
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final InputStream a = pa.a.b.a.g.a("images/objectbar.gif");
            final byte[] array = new byte[32768];
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            mediaTracker.addImage(this.t = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray()), 0);
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {}
        pa.a.b.a.f.a(this.t);
    }
    
    public void paint(final Graphics graphics) {
        if (this.void) {
            this.void = false;
            this.int();
        }
        super.paint(graphics);
    }
    
    private void if() {
        final String string = this.getDocumentBase().toString();
        if (string != null) {
            final DataInputStream dataInputStream = new DataInputStream(new StringBufferInputStream(string));
            final float int1 = this.int;
            final float char1 = this.char;
            final float goto1 = this.goto;
            try {
                for (int i = 46; i != 63; i = (char)dataInputStream.readByte()) {}
                if (this.a(dataInputStream, false).equalsIgnoreCase("initialView")) {
                    this.char = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.int = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                    this.goto = Float.valueOf(this.a(dataInputStream, false)) * 3.1415927f / 180.0f;
                }
            }
            catch (Exception ex) {
                this.int = int1;
                this.char = char1;
                this.goto = goto1;
            }
        }
    }
    
    public void start() {
        if (this.f == null) {
            this.ClearParameters();
            this.for();
            this.ResetApplet();
        }
    }
    
    public void stop() {
        if (this.f != null) {
            this.f.int();
            this.f = null;
        }
        this.removeAll();
        this.b = null;
        System.gc();
    }
}
