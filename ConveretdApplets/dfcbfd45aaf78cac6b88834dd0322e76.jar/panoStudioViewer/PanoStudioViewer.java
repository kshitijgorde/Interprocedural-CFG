// 
// Decompiled by Procyon v0.5.30
// 

package panoStudioViewer;

import java.awt.image.VolatileImage;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Locale;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

public class PanoStudioViewer extends Applet implements ImageObserver
{
    static int w;
    static int U;
    static int \u00fc;
    static int \u00e4;
    _A H;
    boolean \u00ea;
    C \u00e2;
    private E I;
    private Image \u00ce;
    private boolean d;
    private int º;
    private int ª;
    protected boolean V;
    String l;
    protected E \u00f3;
    protected E r;
    protected D \u00f6;
    protected Image Y;
    protected Image T;
    protected int \u00fb;
    protected int \u00fa;
    protected int j;
    protected int i;
    boolean \u00c7;
    Point m;
    Point \u00db;
    long g;
    long \u00cb;
    double \u00c2;
    double \u00c0;
    boolean c;
    double \u00fe;
    double \u00dd;
    double \u00f5;
    int N;
    String[] ¢;
    private boolean \u00e0;
    String £;
    String \u00f0;
    String S;
    String \u00e8;
    String G;
    String \u00d2;
    String \u00d1;
    String \u00d0;
    int \u00d3;
    int \u00e7;
    boolean \u00c1;
    double \u00f2;
    double z;
    double \u00dc;
    double e;
    double E;
    double \u00fd;
    double \u00ee;
    double a;
    double P;
    boolean B;
    double h;
    double A;
    double \u00c4;
    double ¤;
    double \u00e5;
    double \u00ef;
    String K;
    String \u00e9;
    String C;
    boolean f;
    boolean \u00cc;
    int \u00e1;
    boolean q;
    Color \u00c9;
    int \u00df;
    int _;
    Color \u00cd;
    Color \u00c6;
    int \u00f4;
    int k;
    Color \u00c3;
    Color M;
    int u;
    int L;
    int \u00f1;
    int J;
    int \u00d5;
    int \u00da;
    int t;
    boolean Z;
    boolean \u00cf;
    int Q;
    int R;
    boolean W;
    boolean o;
    boolean p;
    int \u00c5;
    long n;
    int \u00f9;
    Cursor \u00ec;
    Cursor \u00d6;
    Cursor \u00e3;
    Cursor \u00d9;
    Cursor v;
    Cursor s;
    Cursor \u00de;
    Cursor \u00e6;
    Cursor b;
    Cursor ¥;
    Cursor \u00d8;
    Cursor \u00ca;
    _C[] X;
    protected Image µ;
    protected boolean \u00eb;
    String D;
    boolean \u00f8;
    boolean \u00ed;
    Image \u00d4;
    Graphics O;
    _B \u00c8;
    static byte[] F;
    
    static {
        PanoStudioViewer.w = 0;
        PanoStudioViewer.U = 1;
        PanoStudioViewer.\u00fc = 2;
        PanoStudioViewer.\u00e4 = 3;
        PanoStudioViewer.F = new byte[] { -1, -125, -125, -125, -125, -116, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -126, -11, -6, -6, -6, -27, 123, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -126, -6, -2, -46, -37, -2, -14, -99, -124, 107, -125, -127, 114, -1, -1, 126, 122, 93, -125, 110, -1, -1, 108, 118, -125, 114, -91, -1, 119, 126, 97, -126, 110, 101, 118, -123, 126, 116, -1, 123, 126, 107, 113, -126, 97, 100, -125, 113, -1, -77, 99, 126, -123, 121, -118, -1, -126, -6, -2, -125, 112, -2, -4, 103, -119, -2, -9, -10, -4, -118, 117, -2, -8, -19, -2, -15, -122, 93, -60, -2, -15, -3, -104, -127, -73, -2, -10, -2, 92, -43, -2, -17, -2, -27, -127, -108, -2, -12, -2, -2, -24, -5, -2, -25, -117, 92, -11, -2, -17, -2, -78, -53, -126, -6, -2, -13, -7, -2, -34, 118, 94, 117, 115, -57, -2, -72, 111, -2, -2, 102, -19, -2, -115, -128, -2, -54, 77, -7, -3, 96, -74, -2, -46, -108, 92, 103, -120, 99, -6, -2, -109, -108, -2, -34, 115, -2, -2, 86, -4, -2, -104, 95, 107, -124, -116, -2, -12, -76, -126, -6, -2, -35, -55, -75, -109, -115, -120, -22, -26, -34, -2, -65, 110, -2, -2, 73, -32, -2, -120, -93, -2, -74, 97, -13, -2, 118, -74, -2, -75, -1, 93, -65, -11, -53, -5, -2, -102, -108, -2, -55, 97, -2, -2, 48, -6, -2, -115, 91, -40, -12, -51, -2, -5, -76, -126, -6, -2, -82, -1, -1, -1, 124, -9, -2, 87, -57, -2, -66, 108, -2, -2, 77, -32, -2, -103, 124, -2, -45, 77, -5, -11, 104, -74, -2, -72, -1, -127, -2, -30, 64, -6, -2, -105, -108, -2, -54, 100, -2, -2, 52, -6, -2, 115, -79, -2, -95, 125, -2, -5, -79, -124, -13, -9, -78, -1, -1, -1, -106, -66, -2, -4, -61, -9, -69, 86, -9, -8, 78, -38, -9, -83, -114, -90, -4, -2, -15, -114, -87, -78, -9, -75, -1, 108, -18, -2, -26, -42, -9, -124, -115, -9, -59, 99, -9, -8, 54, -14, -9, -119, 127, -6, -2, -57, -12, -11, -100, -1, -73, -73, -55, -1, -1, -1, -1, -82, -77, -89, -83, -80, -75, -60, -71, -71, -57, -71, -72, -48, -1, -66, -82, -72, -80, -1, -1, -70, -71, -59, -1, -1, -90, -72, -85, -72, -72, -63, -68, -72, -68, -60, -71, -71, -57, -71, -71, -1, -63, -91, -83, -84, -73, -73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 123, 87, 94, 93, 98, -92, -1, -1, -1, -62, -109, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -117, 121, 121, -90, -117, 121, 122, -78, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 78, -107, -19, -2, -4, -26, 120, -100, -1, 105, 74, -85, -86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 111, -33, -33, -121, 115, -33, -33, -97, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -69, 126, -2, -2, -9, -5, -2, -6, 126, -98, 124, -7, -8, -116, -1, -1, -1, -1, -1, -1, -1, -1, -1, -61, -104, -117, 107, -2, -2, -114, 101, -70, -70, -106, -1, -1, -93, -106, -102, -65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -85, -71, -2, -2, 59, 99, -58, -64, 117, 80, -58, -2, -5, -120, 39, 120, -121, 115, 70, -123, -121, 121, -89, 68, -84, -72, -88, -2, -2, -114, 77, 119, 120, -122, -63, 66, -115, -69, -77, 114, 118, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -69, -106, -2, -2, -40, -105, 85, 48, -126, -110, -2, -2, -2, -2, 51, -33, -2, -59, 72, -4, -2, -91, 81, -43, -2, -14, -7, -2, -2, -114, 126, -2, -2, -105, 79, -63, -2, -19, -6, -3, -108, -74, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 94, -74, -6, -2, -2, -2, -52, 69, 102, -65, -2, -5, -110, 56, -33, -2, -59, 72, -4, -2, -99, 88, -2, -2, 123, -115, -2, -2, -114, 126, -2, -2, 122, 107, -2, -3, 91, -105, -2, -14, -112, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -75, 83, 23, 59, 127, -58, -2, -2, -86, -102, -98, -2, -8, -91, -122, -33, -2, -60, 69, -4, -2, -103, 110, -2, -2, 127, 114, -2, -2, -114, 126, -2, -2, 110, -104, -2, -5, 123, 117, -2, -2, -97, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -122, -57, -22, -29, 50, 43, -5, -2, -51, -99, -97, -2, -8, -106, 123, -33, -2, -66, 51, -4, -2, -102, 102, -2, -2, 105, 108, -2, -2, -114, 126, -2, -2, 114, -113, -2, -4, 88, 114, -2, -5, -101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -88, -91, -2, -2, -42, -44, -2, -2, -90, -80, -108, -2, -6, 99, 46, -47, -2, -20, -75, -2, -2, -93, 90, -10, -2, -95, -76, -2, -2, -114, 126, -2, -2, -117, 99, -4, -2, 119, -69, -2, -39, -89, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 92, -41, -2, -2, -2, -2, -55, -118, -1, 111, -4, -2, -2, 93, -127, -2, -2, -31, -14, -2, -85, -121, -94, -2, -2, -18, -2, -2, -114, 126, -2, -2, -93, -116, -115, -5, -2, -2, -19, 123, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -42, -128, -120, -93, -96, -116, -97, -1, -1, -106, -123, -91, -99, -112, -125, -114, -97, 121, -110, -106, -95, -1, 124, -110, -98, 124, -106, -105, -86, -109, -106, -105, -73, -1, -105, -128, -96, -101, -121, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -52, -121, -122, -103, -1, -57, -121, -121, -121, -121, -122, -101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -99, -52, -10, -82, -52, 105, -23, -10, 120, -86, -10, -77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -42, -118, -2, -21, 93, 99, -2, -6, 44, 69, 99, 98, -41, 121, 112, -123, 118, -126, -121, 125, 125, -116, 120, 126, -127, -120, 123, 126, -111, 124, 108, -123, 120, -126, -1, 127, 126, 106, 120, 119, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 106, -4, -2, 58, -112, -2, -53, 81, -76, -2, -98, 57, -78, -2, -16, -2, -65, 15, -36, -2, 57, -112, -2, -81, 26, -4, -15, 22, -97, -2, -15, -2, -48, 68, 122, -2, -11, -2, -76, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -92, -69, -2, 123, -47, -2, -100, -90, -74, -2, -116, 72, -2, -25, 36, -32, -2, 80, -123, -2, 127, -36, -2, -12, 102, -2, -90, 50, -2, -14, 45, -51, -2, 102, 108, -2, -8, -121, -77, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 121, -2, -31, -4, -14, -96, -61, -74, -2, -116, 109, -2, -4, -17, -17, -17, 126, 63, -5, -23, -2, -57, -2, -23, -2, 95, 99, -2, -3, -17, -17, -17, -125, 108, -2, -36, -56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 113, -16, -2, -2, -74, -36, -57, -74, -2, -115, 63, -2, -22, 38, 106, 88, 117, -123, -52, -2, -4, 49, -14, -2, -23, -115, 105, -4, -12, 44, 105, 90, 41, 117, -2, -40, -41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -66, -96, -5, -5, -105, -1, -57, -76, -6, -88, 103, -90, -3, -2, -3, -73, -69, -37, -126, -6, -47, 59, -84, -5, -85, -1, -88, -104, -5, -2, -3, -62, 115, -127, -6, -43, -41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -69, -76, -75, -1, -1, -1, -74, -76, -62, -1, -72, -91, -79, -89, -54, -1, -1, -1, -76, -69, -1, -71, -76, -1, -1, -1, -64, -93, -79, -87, -62, -1, -66, -76, -73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    }
    
    public PanoStudioViewer() {
        this.\u00ea = false;
        this.I = null;
        this.\u00ce = null;
        this.d = true;
        this.V = false;
        this.l = " v1.10";
        this.\u00f3 = null;
        this.r = null;
        this.\u00f6 = null;
        this.Y = null;
        this.T = null;
        this.\u00fb = 0;
        this.\u00fa = 0;
        this.j = 4;
        this.i = 4;
        this.\u00c7 = false;
        this.m = new Point();
        this.\u00db = new Point();
        this.g = -1L;
        this.\u00cb = -1L;
        this.\u00c2 = 0.0;
        this.\u00c0 = 0.0;
        this.N = 1;
        this.\u00e0 = false;
        this.£ = "";
        this.\u00f0 = null;
        this.S = null;
        this.\u00e8 = "";
        this.G = "";
        this.\u00d2 = "";
        this.\u00d1 = "";
        this.\u00d0 = "";
        this.\u00d3 = PanoStudioViewer.w;
        this.\u00e7 = 1;
        this.\u00c1 = false;
        this.\u00f2 = 0.0;
        this.z = 0.0;
        this.\u00dc = 90.0;
        this.e = 0.0;
        this.E = 360.0;
        this.\u00fd = -90.0;
        this.\u00ee = 90.0;
        this.a = 2.0;
        this.P = 160.0;
        this.B = false;
        this.h = 0.03490658503988659;
        this.A = 0.0;
        this.\u00c4 = 1.0;
        this.¤ = 5000.0;
        this.\u00e5 = 0.5;
        this.\u00ef = 1.4;
        this.K = "Laden:";
        this.\u00e9 = "";
        this.C = "";
        this.f = false;
        this.\u00cc = false;
        this.\u00e1 = -16777216;
        this.q = false;
        this.\u00df = 0;
        this._ = 16777215;
        this.\u00cd = new Color(this.\u00df);
        this.\u00c6 = new Color(this._);
        this.\u00f4 = 0;
        this.k = 16777215;
        this.\u00c3 = new Color(this.\u00f4);
        this.M = new Color(this.k);
        this.u = 0;
        this.L = 1;
        this.\u00f1 = 0;
        this.J = 0;
        this.\u00d5 = 128;
        this.\u00da = 192;
        this.t = 255;
        this.Z = true;
        this.\u00cf = true;
        this.Q = 24;
        this.R = 20;
        this.W = true;
        this.o = false;
        this.p = false;
        this.\u00c5 = 0;
        this.n = 0L;
        this.\u00f9 = 0;
        this.X = null;
        this.µ = null;
        this.\u00eb = false;
        this.D = "Copyright © 2005, www.tshsoft.com";
        this.\u00f8 = true;
        this.\u00ed = false;
        this.\u00d4 = null;
        this.O = null;
    }
    
    final void A(final Graphics graphics, final Graphics graphics2) {
        if (this.µ != null && graphics2 != null && graphics != null && this.\u00f6 != null) {
            final int v = this.r.V;
            final int q = this.r.Q;
            graphics.drawImage(this.µ, 0, 0, null);
            if (this.\u00eb) {
                this.\u00e2.A(graphics, v, q);
            }
            if (this.\u00e2 != null) {
                final Rectangle a = this.\u00e2.A(v, q, this.\u00eb);
                graphics.setColor(this.\u00cd);
                String s;
                if ((!this.\u00e0 || this.T == null) && this.d && this.A(this.\u00fb, this.\u00fa, this.\u00ce, this.º, this.ª)) {
                    s = this.D;
                }
                else if (this.\u00eb && this.\u00e2.t != null) {
                    s = this.\u00e2.t.C;
                }
                else {
                    final int a2;
                    if ((a2 = this.\u00f6.A(this.\u00fb, this.\u00fa)) != 0) {
                        s = this.B(a2);
                        if (s == null || s == "") {
                            s = this.\u00e9;
                        }
                    }
                    else {
                        s = this.\u00e9;
                    }
                }
                if (this.W) {
                    this.B(s);
                }
                else if (s != null) {
                    final String a3 = this.A(s, a.width - 6, graphics2);
                    final int n = a.x + 6;
                    final int n2 = (this.\u00e2.E == 0) ? (a.y + a.height - 6) : (a.y + graphics.getFontMetrics().getHeight());
                    graphics.drawString(a3, n + 1, n2 + 1);
                    graphics.setColor(this.\u00c6);
                    graphics.drawString(a3, n, n2);
                }
            }
            if (this.p && this.\u00c5 > 0) {
                final String string = 1000.0 / (this.n / this.\u00c5) + " fps";
                graphics.setColor(this.\u00cd);
                graphics.drawString(string, 5, 3 + graphics2.getFontMetrics().getHeight());
                graphics.setColor(this.\u00c6);
                graphics.drawString(string, 4, 2 + graphics2.getFontMetrics().getHeight());
            }
            if (this.q) {
                graphics.setColor(this.\u00c9);
                graphics.drawLine(0, 0, v - 1, 0);
                graphics.drawLine(v - 1, 0, v - 1, q - 1);
                graphics.drawLine(v - 1, q - 1, 0, q - 1);
                graphics.drawLine(0, q - 1, 0, 0);
            }
            if (this.\u00e0 && this.T != null) {
                graphics.drawImage(this.T, v - this.T.getWidth(null) - this.j, this.i, null);
            }
            else if (this.\u00ce != null && this.d) {
                graphics.drawImage(this.\u00ce, this.º, this.ª, null);
            }
        }
        else if (graphics2 != null && graphics != null) {
            Rectangle clipBounds = graphics.getClipBounds();
            final Dimension size = this.getSize();
            if (clipBounds == null) {
                clipBounds = new Rectangle(0, 0, size.width, size.height);
            }
            graphics.setColor(this.\u00c3);
            graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            if (this.Y != null) {
                graphics.drawImage(this.Y, (size.width - this.Y.getWidth(null)) / 2, (2 * size.height / 3 - this.Y.getHeight(null)) / 2, null);
            }
            final Rectangle rectangle = new Rectangle();
            rectangle.x = size.width / 10 * 2;
            rectangle.width = size.width / 10 * 6;
            rectangle.y = 2 * size.height / 3;
            rectangle.height = size.height / 25;
            final Rectangle rectangle3;
            final Rectangle rectangle2 = rectangle3 = new Rectangle(rectangle);
            ++rectangle3.x;
            final Rectangle rectangle4 = rectangle2;
            ++rectangle4.y;
            final Color color = new Color(((this.M.getBlue() + this.\u00c3.getBlue() + 1) / 2 & 0xFF) | ((this.M.getGreen() + this.\u00c3.getGreen() + 1) / 2 & 0xFF) << 8 | ((this.M.getRed() + this.\u00c3.getRed() + 1) / 2 & 0xFF) << 16);
            final Rectangle rectangle5 = new Rectangle(rectangle.x, rectangle.y, rectangle.width * this.\u00f9 / 100, rectangle.height);
            final Rectangle rectangle6 = new Rectangle(rectangle5.x + 1, rectangle5.y + 1, rectangle5.width, rectangle5.height);
            graphics.setColor(color);
            graphics.drawRect(rectangle6.x, rectangle6.y, rectangle6.width, rectangle6.height);
            graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            graphics.setColor(this.M);
            graphics.fillRect(rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height);
            graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.drawString(String.valueOf(this.K) + " " + this.\u00f9 + " %", rectangle.x + 4, rectangle.y + rectangle.height + 4 + graphics.getFontMetrics().getHeight());
        }
    }
    
    final String A(final String s, final int n, final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (fontMetrics.stringWidth(s) <= n) {
            return s;
        }
        final String s2 = "...";
        int n2 = s.length() - 3;
        if (n2 <= 0) {
            return "";
        }
        String s3;
        for (s3 = String.valueOf(s.substring(0, n2)) + s2; fontMetrics.stringWidth(s3) > n && --n2 > 1; s3 = String.valueOf(s.substring(0, n2)) + s2) {}
        return s3;
    }
    
    public final void paint(final Graphics graphics) {
        try {
            if (graphics == null) {
                return;
            }
            if (this.\u00f6 == null) {
                this.\u00c8.A();
                this.\u00c8.A(graphics);
            }
            else {
                synchronized (this.\u00f6) {
                    this.\u00c8.A();
                    this.\u00c8.A(graphics);
                }
                // monitorexit(this.\u00f6)
            }
            graphics.drawImage(this.\u00d4, 0, 0, this);
        }
        catch (Exception ex) {}
        this.\u00ed = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    int A(final String s, final char c, final int n) {
        boolean b = false;
        if (s == null) {
            return -1;
        }
        for (int length = s.length(), i = n; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\"' && i > 0 && s.charAt(i - 1) != '\\') {
                b = !b;
            }
            else if (char1 == c && !b) {
                return i;
            }
        }
        return -1;
    }
    
    String A(final String s) {
        final StringBuffer sb = new StringBuffer(0);
        try {
            if (s == null) {
                return null;
            }
            final int length = s.length();
            if (length < 6) {
                return s;
            }
            int i;
            for (i = 0; i < length - 5; ++i) {
                if (s.substring(i, i + 6).equalsIgnoreCase("&quot;")) {
                    sb.append('\"');
                    i += 5;
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
            sb.append(s.substring(i, length));
        }
        catch (Exception ex) {
            return "";
        }
        return sb.toString();
    }
    
    String F(final String s) {
        if (s == null) {
            return null;
        }
        int length;
        int n;
        for (length = s.length(), n = 0; n < length && (s.charAt(n) == ' ' || s.charAt(n) == '\n' || s.charAt(n) == '\t' || s.charAt(n) == '\r'); ++n) {}
        final int n2 = n;
        if (n2 == length) {
            return null;
        }
        int n3;
        for (n3 = length - 1; n3 >= n2 && (s.charAt(n3) == ' ' || s.charAt(n3) == '\n' || s.charAt(n3) == '\t' || s.charAt(n3) == '\r'); --n3) {}
        final int n4 = n3;
        if (n4 < n2) {
            return null;
        }
        return s.substring(n2, n4 + 1);
    }
    
    String A(final String s, final String s2) {
        try {
            String s3 = null;
            if (s != null) {
                int n = 0;
                if (s2 == null) {
                    return null;
                }
                int a;
                while ((a = this.A(s, ';', n)) >= 0) {
                    final String f;
                    if ((f = this.F(s.substring(n, a))).startsWith(String.valueOf(s2) + "=")) {
                        s3 = this.F(f.substring(f.indexOf(61) + 1));
                        break;
                    }
                    n = a + 1;
                }
            }
            else {
                try {
                    s3 = this.getParameter(s2);
                }
                catch (Exception ex) {
                    s3 = null;
                }
            }
            return this.A(s3);
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    void C() {
        this.\u00c1 = true;
        this.\u00fd = -90.0;
        this.\u00ee = 90.0;
        this.e = 0.0;
        this.E = 360.0;
        this.a = 5.0;
        this.P = 165.0;
        this.z = 0.0;
        this.\u00f2 = 0.0;
        this.\u00dc = 90.0;
        this.\u00d3 = PanoStudioViewer.w;
        this.G = "";
        this.\u00d2 = "";
        this.\u00d1 = "";
        this.\u00d0 = "";
        this.B = false;
        this.h = 0.03490658503988659;
        this.A = 0.0;
        this.\u00c4 = 1.0;
        this.¤ = 5000.0;
        this.f = false;
        this.\u00cc = false;
        this.q = false;
        this.\u00e1 = -16777216;
        this.\u00c9 = new Color(this.\u00e1);
        this.\u00df = 0;
        this.\u00cd = new Color(this.\u00df);
        this._ = 16777215;
        this.\u00c6 = new Color(this._);
        this.\u00f4 = 0;
        this.M = new Color(this.k);
        this.k = 16777215;
        this.\u00c3 = new Color(this.\u00f4);
        this.Y = null;
        this.T = null;
        this.\u00e5 = 0.5;
        this.\u00ef = 1.2;
        this.\u00eb = false;
        this.u = 0;
        this.L = 0;
        this.\u00f1 = 0;
        this.J = 0;
        this.\u00d5 = 128;
        this.\u00da = 192;
        this.t = 255;
        this.Z = true;
        this.\u00cf = true;
        this.Q = 24;
        this.R = 20;
        this.X = new _C[256];
    }
    
    int A(final String s, final int n) {
        int int1 = n;
        try {
            int1 = Integer.parseInt(s, 16);
        }
        catch (Exception ex) {}
        return int1;
    }
    
    void C(final String s) {
    }
    
    void B() {
        this.E((String)null);
        int n = 2;
        this.N = 1;
        while (this.A(null, "file" + n) != null) {
            ++n;
            ++this.N;
        }
        (this.¢ = new String[this.N])[0] = this.£;
        for (int i = 1; i < this.N; ++i) {
            this.¢[i] = this.A(null, "file" + (i + 1));
        }
        final String a = this.A(null, "startWith");
        int int1 = 1;
        if (a != null) {
            try {
                int1 = Integer.parseInt(a);
            }
            catch (Exception ex) {}
        }
        if (--int1 < 0 || int1 >= this.N) {
            int1 = 0;
        }
        this.£ = this.¢[int1];
        final String a2 = this.A(null, (int1 == 0) ? "params" : ("params" + (int1 + 1)));
        final int index;
        final int index2;
        if (a2 != null && (index = a2.indexOf(123, 0)) >= 0 && (index2 = a2.indexOf(125, index)) >= 0) {
            this.E(a2.substring(index + 1, index2));
        }
        final byte[] b = this.B(this.B(this.£, "txt"), false);
        if (b != null) {
            final String s = new String(b);
            if (s.indexOf("<html>") == -1 && s.indexOf("<HTML>") == -1) {
                this.E(s);
            }
        }
    }
    
    void E(final String s) {
        final String a = this.A(s, "file");
        if (a != null) {
            this.£ = a;
        }
        final String a2 = this.A(null, "key");
        if (a2 != null) {
            this.G = a2;
        }
        final String a3 = this.A(null, "key2");
        if (a3 != null) {
            this.\u00d2 = a3;
        }
        final String a4 = this.A(null, "key3");
        if (a4 != null) {
            this.\u00d1 = a4;
        }
        final String a5 = this.A(null, "key4");
        if (a5 != null) {
            this.\u00d0 = a5;
        }
        final String a6 = this.A(s, "waitImage");
        if (a6 != null) {
            this.\u00f0 = a6;
        }
        final String a7 = this.A(s, "logo");
        if (a7 != null) {
            this.S = a7;
        }
        final String a8 = this.A(s, "logoUrl");
        if (a8 != null) {
            this.\u00e8 = a8;
        }
        final String a9 = this.A(s, "logoX");
        if (a9 != null) {
            try {
                this.j = Integer.parseInt(a9);
            }
            catch (Exception ex) {}
        }
        final String a10 = this.A(s, "logoY");
        if (a10 != null) {
            try {
                this.i = Integer.parseInt(a10);
            }
            catch (Exception ex2) {}
        }
        final String a11 = this.A(s, "bgWait");
        if (a11 != null) {
            this.k = this.A(a11, 0);
            this.\u00c3 = new Color(this.k);
        }
        final String a12 = this.A(s, "fgWait");
        if (a12 != null) {
            this.\u00f4 = this.A(a12, -1);
            this.M = new Color(this.\u00f4);
        }
        final String a13 = this.A(s, "autoRotate");
        if (a13 != null) {
            if (a13.equals("true")) {
                this.B = true;
            }
            else {
                this.B = false;
            }
        }
        final String a14 = this.A(s, "autoPanRate");
        if (a14 != null) {
            try {
                this.h = Double.valueOf(a14);
                this.h *= 0.017453292519943295;
            }
            catch (Exception ex3) {}
            if (this.h < -1.7453292519943295) {
                this.h = -1.7453292519943295;
            }
            else if (this.h > 1.7453292519943295) {
                this.h = 1.7453292519943295;
            }
        }
        final String a15 = this.A(s, "autoTiltRate");
        if (a15 != null) {
            try {
                this.A = Double.valueOf(a15);
                this.A *= 0.017453292519943295;
            }
            catch (Exception ex4) {}
            if (this.A < -0.8726646259971648) {
                this.A = -0.8726646259971648;
            }
            else if (this.A > 0.8726646259971648) {
                this.A = 0.8726646259971648;
            }
        }
        final String a16 = this.A(s, "autoZoomRate");
        if (a16 != null) {
            try {
                this.\u00c4 = Double.valueOf(a16);
            }
            catch (Exception ex5) {}
            if (this.\u00c4 < 0.1) {
                this.\u00c4 = 0.1;
            }
            else if (this.\u00c4 > 2.0) {
                this.\u00c4 = 2.0;
            }
        }
        final String a17 = this.A(s, "autoRotateRestart");
        if (a17 != null) {
            try {
                this.¤ = Integer.parseInt(a17) * 1000;
            }
            catch (Exception ex6) {}
            if (this.¤ < 0.0) {
                this.¤ = 0.0;
            }
        }
        final String a18 = this.A(s, "loadString");
        if (a18 != null) {
            this.K = a18;
        }
        final String a19 = this.A(s, "title");
        if (a19 != null) {
            this.\u00e9 = a19;
        }
        final String a20 = this.A(s, "quality");
        if (a20 != null) {
            try {
                this.\u00e7 = Integer.parseInt(a20);
            }
            catch (Exception ex7) {}
            if (this.\u00e7 < 0) {
                this.\u00e7 = 0;
            }
            else if (this.\u00e7 > 4) {
                this.\u00e7 = 4;
            }
        }
        final String a21 = this.A(s, "pan");
        if (a21 != null) {
            try {
                this.\u00f2 = Double.valueOf(a21);
            }
            catch (Exception ex8) {}
            while (this.\u00f2 < 0.0) {
                this.\u00f2 += 360.0;
            }
            while (this.\u00f2 >= 360.0) {
                this.\u00f2 -= 360.0;
            }
        }
        final String a22 = this.A(s, "minpan");
        if (a22 != null) {
            try {
                this.e = Double.valueOf(a22);
            }
            catch (Exception ex9) {}
            if (this.e < 0.0) {
                this.e = 0.0;
            }
            if (this.e > 360.0) {
                this.e = 360.0;
            }
        }
        final String a23 = this.A(s, "maxpan");
        if (a23 != null) {
            try {
                this.E = Double.valueOf(a23);
            }
            catch (Exception ex10) {}
            if (this.E < 0.0) {
                this.E = 0.0;
            }
            if (this.E > 360.0) {
                this.E = 360.0;
            }
        }
        final String a24 = this.A(s, "tilt");
        if (a24 != null) {
            try {
                this.z = Double.valueOf(a24);
            }
            catch (Exception ex11) {}
            if (this.z < -90.0) {
                this.z = -90.0;
            }
            if (this.z > 90.0) {
                this.z = 90.0;
            }
        }
        final String a25 = this.A(s, "mintilt");
        if (a25 != null) {
            try {
                this.\u00fd = Double.valueOf(a25);
            }
            catch (Exception ex12) {}
            if (this.\u00fd < -90.0) {
                this.\u00fd = -90.0;
            }
            if (this.\u00fd > 90.0) {
                this.\u00fd = 90.0;
            }
        }
        final String a26 = this.A(s, "maxtilt");
        if (a26 != null) {
            try {
                this.\u00ee = Double.valueOf(a26);
            }
            catch (Exception ex13) {}
            if (this.\u00ee < -90.0) {
                this.\u00ee = -90.0;
            }
            if (this.\u00ee > 90.0) {
                this.\u00ee = 90.0;
            }
        }
        final String a27 = this.A(s, "hfov");
        if (a27 != null) {
            try {
                this.\u00dc = Double.valueOf(a27);
            }
            catch (Exception ex14) {}
            if (this.\u00dc < 2.0) {
                this.\u00dc = 5.0;
            }
            if (this.\u00dc > 160.0) {
                this.\u00dc = 165.0;
            }
        }
        final String a28 = this.A(s, "minhfov");
        if (a28 != null) {
            try {
                this.a = Double.valueOf(a28);
            }
            catch (Exception ex15) {}
            if (this.a < 2.0) {
                this.a = 5.0;
            }
            if (this.a > 165.0) {
                this.a = 165.0;
            }
        }
        final String a29 = this.A(s, "maxhfov");
        if (a29 != null) {
            try {
                this.P = Double.valueOf(a29);
            }
            catch (Exception ex16) {}
            if (this.P < 2.0) {
                this.P = 5.0;
            }
            if (this.P > 165.0) {
                this.P = 165.0;
            }
        }
        final String a30 = this.A(s, "spherical");
        if (a30 != null) {
            if (a30.equals("true")) {
                this.\u00c1 = true;
            }
            else {
                this.\u00c1 = false;
            }
        }
        final String a31 = this.A(s, "borderColor");
        if (a31 != null) {
            this.\u00e1 = this.A(a31, 0);
            this.\u00c9 = new Color(this.\u00e1);
        }
        final String a32 = this.A(s, "shadowColor");
        if (a32 != null) {
            this.\u00df = this.A(a32, 0);
            this.\u00cd = new Color(this.\u00df);
        }
        final String a33 = this.A(s, "textColor");
        if (a33 != null) {
            this._ = this.A(a33, -1);
            this.\u00c6 = new Color(this._);
        }
        final String a34 = this.A(s, "showHotspots");
        if (a34 != null) {
            if (a34.equals("true")) {
                this.f = true;
            }
            else {
                this.f = false;
            }
        }
        final String a35 = this.A(s, "hotspotZoom");
        if (a35 != null) {
            if (a35.equals("true")) {
                this.\u00cc = true;
            }
            else {
                this.f = false;
            }
        }
        final String a36 = this.A(s, "showBorder");
        if (a36 != null) {
            if (a36.equals("true")) {
                this.q = true;
            }
            else {
                this.q = false;
            }
        }
        final String a37 = this.A(s, "lang");
        if (a37 != null) {
            if (a37.equals("en")) {
                this.\u00d3 = PanoStudioViewer.U;
            }
            else if (a37.equals("fr")) {
                this.\u00d3 = PanoStudioViewer.\u00fc;
            }
            else if (a37.equals("tr")) {
                this.\u00d3 = PanoStudioViewer.\u00e4;
            }
            else {
                this.\u00d3 = PanoStudioViewer.w;
            }
        }
        final String a38 = this.A(s, "mass");
        if (a38 != null) {
            try {
                this.\u00e5 = Double.valueOf(a38);
            }
            catch (Exception ex17) {}
            if (this.\u00e5 < 0.1) {
                this.\u00e5 = 0.1;
            }
            else if (this.\u00e5 > 2.0) {
                this.\u00e5 = 2.0;
            }
        }
        final String a39 = this.A(s, "mouseSen");
        if (a39 != null) {
            try {
                this.\u00ef = Double.valueOf(a39);
            }
            catch (Exception ex18) {}
            if (this.\u00ef < 0.1) {
                this.\u00ef = 0.1;
            }
            else if (this.\u00ef > 2.0) {
                this.\u00e5 = 2.0;
            }
        }
        final String a40 = this.A(s, "showToolbar");
        if (a40 != null) {
            if (a40.equals("true")) {
                this.\u00eb = true;
            }
            else {
                this.\u00eb = false;
            }
        }
        final String a41 = this.A(s, "tbLayout");
        if (a41 != null) {
            try {
                this.u = Integer.parseInt(a41);
            }
            catch (Exception ex19) {}
            if (this.u < 0) {
                this.u = 0;
            }
            else if (this.u > 2) {
                this.u = 2;
            }
        }
        final String a42 = this.A(s, "tbPosition");
        if (a42 != null) {
            try {
                this.L = Integer.parseInt(a42);
            }
            catch (Exception ex20) {}
            if (this.L != 0 && this.L != 1) {
                this.L = 0;
            }
        }
        final String a43 = this.A(s, "tbButtonType");
        if (a43 != null) {
            try {
                this.\u00f1 = Integer.parseInt(a43);
            }
            catch (Exception ex21) {}
            if (this.\u00f1 != 0 && this.\u00f1 != 1) {
                this.\u00f1 = 0;
            }
        }
        final String a44 = this.A(s, "tbColorProfile");
        if (a44 != null) {
            try {
                this.J = Integer.parseInt(a44);
            }
            catch (Exception ex22) {}
            if (this.J < 0 || this.J >= 5) {
                this.J = 0;
            }
        }
        final String a45 = this.A(s, "tbBGOpacity");
        if (a45 != null) {
            try {
                this.\u00d5 = Integer.parseInt(a45);
            }
            catch (Exception ex23) {}
            if (this.\u00d5 < 0 || this.\u00d5 > 255) {
                this.\u00d5 = 255;
            }
        }
        final String a46 = this.A(s, "tbButtonOpacity");
        if (a46 != null) {
            try {
                this.\u00da = Integer.parseInt(a46);
            }
            catch (Exception ex24) {}
            if (this.\u00da < 0 || this.\u00da > 255) {
                this.\u00da = 255;
            }
        }
        final String a47 = this.A(s, "tbHotButtonOpacity");
        if (a47 != null) {
            try {
                this.t = Integer.parseInt(a47);
            }
            catch (Exception ex25) {}
            if (this.t < 0 || this.t > 255) {
                this.t = 255;
            }
        }
        final String a48 = this.A(s, "tbShowPlayButton");
        if (a48 != null) {
            if (a48.equals("true")) {
                this.Z = true;
            }
            else {
                this.Z = false;
            }
        }
        final String a49 = this.A(s, "tbShowBackground");
        if (a49 != null) {
            if (a49.equals("true")) {
                this.\u00cf = true;
            }
            else {
                this.\u00cf = false;
            }
        }
        final String a50 = this.A(s, "tbButtonWidth");
        if (a50 != null) {
            try {
                this.Q = Integer.parseInt(a50);
            }
            catch (Exception ex26) {}
            if (this.Q < 18 || this.Q > 48) {
                this.Q = 24;
            }
        }
        final String a51 = this.A(s, "tbButtonHeight");
        if (a51 != null) {
            try {
                this.R = Integer.parseInt(a51);
            }
            catch (Exception ex27) {}
            if (this.R < 18 || this.R > 48) {
                this.R = 24;
            }
        }
        final String a52 = this.A(s, "useStatusBar");
        if (a52 != null) {
            if (a52.equals("true")) {
                this.W = true;
            }
            else {
                this.W = false;
            }
        }
        for (int i = 0; i < 255; ++i) {
            boolean b = true;
            String a53 = this.A(s, "hotspot" + i);
            if (a53 != null) {
                b = false;
            }
            if (a53 == null) {
                a53 = "";
            }
            final _C c = new _C();
            c.F = a53;
            final String a54 = this.A(s, "target" + i);
            if (a54 != null) {
                c.C = a54;
                b = false;
            }
            final String a55 = this.A(s, "comment" + i);
            if (a55 != null) {
                c.E = a55;
                b = false;
            }
            final String a56 = this.A(s, "hscolor" + i);
            if (a56 != null) {
                b = false;
                try {
                    final int int1 = Integer.parseInt(a56, 16);
                    c.B = (int1 & 0xFF0000);
                    c.A = (int1 & 0xFF00);
                    c.D = (int1 & 0xFF);
                }
                catch (Exception ex28) {}
            }
            if (!b) {
                this.X[i] = c;
            }
            if (b) {
                break;
            }
        }
    }
    
    String B(final String s, final String s2) {
        int char1 = 32;
        int i;
        for (i = s.length() - 1; i >= 0; --i) {
            char1 = s.charAt(i);
            if (char1 == 46) {
                break;
            }
            if (char1 == 47) {
                break;
            }
            if (char1 == 92) {
                break;
            }
        }
        if (char1 != 46) {
            return new String(String.valueOf(s) + "." + s2);
        }
        return String.valueOf(s.substring(0, i)) + "." + s2;
    }
    
    protected void F() {
        if (this.£ == null || this.£.equals("")) {
            System.err.println("Parameter \"file\" undefiniert!");
            System.exit(0);
        }
        if (this.Y == null && this.\u00f0 != null) {
            final byte[] b = this.B(this.\u00f0, false);
            if (b != null) {
                this.Y = this.A(b);
                this.update(this.getGraphics());
            }
        }
        if (this.S != null) {
            final byte[] b2 = this.B(this.S, false);
            if (b2 != null) {
                this.T = this.A(b2);
            }
        }
        this.\u00f6 = null;
        this.\u00f3 = null;
        System.gc();
        Image a = null;
        final byte[] b3 = this.B(this.£, true);
        if (b3 != null) {
            a = this.A(b3);
        }
        if (a == null) {
            System.err.println("Konnte " + this.£ + " nicht als Bild \u00f6ffnen!");
            System.exit(0);
        }
        try {
            this.\u00f3 = new E(a);
            System.gc();
        }
        catch (Exception ex) {
            return;
        }
        final byte[] b4 = this.B(this.B(this.£, "bmp"), false);
        boolean a2 = false;
        if (b4 != null) {
            try {
                a2 = this.\u00f3.A(b4);
            }
            catch (Exception ex2) {}
        }
        if (!a2) {
            this.f = false;
        }
        this.\u00f6 = new D(this.\u00f3, this.e, this.E, this.\u00fd, this.\u00ee, this.\u00c1, this.E - this.e == 360.0, a2 && this.f, a2);
        final Dimension size = this.getSize();
        if (this.r == null || this.r.V != size.width || this.r.Q != size.height) {
            this.r = new E(size.width, size.height, true);
            this.µ = this.createImage(this.r.W);
        }
        this.\u00f6.A(this.r);
        for (int n = 0; this.X[n] != null && n < 255 && this.X[n] != null; ++n) {
            this.\u00f6.A(this.X[n], n);
        }
        this.\u00f6.b = this.\u00e7;
        this.\u00f6.B(this.a * 3.141592653589793 / 180.0, this.P * 3.141592653589793 / 180.0);
        this.\u00f6.A(this.\u00f2 * 3.141592653589793 / 180.0, this.z * 3.141592653589793 / 180.0, this.\u00dc * 3.141592653589793 / 180.0);
        this.A(this.I = new E(56, 29), PanoStudioViewer.F, this.V ? 255 : 160);
        if (this.I != null) {
            this.\u00ce = this.createImage(this.I.W);
        }
        this.d = true;
        this.\u00ea = true;
        this.\u00ed = true;
        this.repaint();
        System.gc();
    }
    
    public void init() {
        System.out.println("PanoramaStudio Viewer v1.10 © 2005-2006, www.tshsoft.com\nTobias H\u00fcllmandel Softwareentwicklung");
        this.setLayout(new BorderLayout());
        this.repaint();
        final String property = System.getProperty("java.version");
        if ((property.charAt(0) == '1' && property.charAt(2) < '4') || !this.\u00f8) {
            this.\u00c8 = new _B();
        }
        else {
            this.\u00c8 = (_B)new _F();
        }
        Label_0147: {
            if (property.charAt(0) == '1') {
                if (property.charAt(2) < '4') {
                    break Label_0147;
                }
            }
            try {
                Class.forName("panoStudioViewer.B").getConstructor(Class.forName("panoStudioViewer.PanoStudioViewer")).newInstance(this);
            }
            catch (Exception ex) {}
        }
        if (property.charAt(0) == '1' && property.charAt(2) < '2') {
            new _D();
            this.V = true;
        }
        else {
            new _E();
        }
        System.gc();
        this.enableEvents(8L);
        this.enableEvents(16L);
        this.enableEvents(32L);
        try {
            this.setCursor(this.\u00ca);
        }
        catch (Exception ex2) {}
        this.C();
        this.B();
        this.A(false);
        this.setVisible(true);
        this.F();
    }
    
    private final void A(final boolean b) {
        String s = this.getCodeBase().getHost();
        if (b) {
            s = "www." + s;
        }
        if (s.equals("")) {
            this.\u00e0 = true;
            return;
        }
        for (String s2 = new String(s); s.length() < 32; s = String.valueOf(s) + s2) {}
        final byte[] array = new byte[32];
        for (int i = 0; i < 32; ++i) {
            array[i] = 0;
        }
        byte[] bytes = new byte[32];
        try {
            bytes = s.toLowerCase(Locale.ENGLISH).getBytes("ISO-8859-1");
        }
        catch (Exception ex) {}
        int n = 0;
        for (int j = 0; j < bytes.length; ++j) {
            n += (bytes[j] & 0xFF);
        }
        for (int k = 0; k < 12; ++k) {
            array[k] = (byte)(((bytes[k] & 0xFF) + (bytes[k + 12] & 0xFF) + n + 7 * k) % 10);
        }
        String string = "";
        for (int l = 0; l < 12; ++l) {
            string = String.valueOf(string) + (array[l] & 0xFF);
        }
        this.\u00e0 = (string.equals(this.G) || string.equals(this.\u00d2) || string.equals(this.\u00d1) || string.equals(this.\u00d0));
        if (!this.\u00e0 && !s.startsWith("www.")) {
            this.A(true);
        }
    }
    
    public synchronized void start() {
        if (this.H != null) {
            return;
        }
        Runtime.getRuntime().freeMemory();
        this.\u00e2 = new C(this);
        final Dimension size = this.getSize();
        this.º = size.width - 57;
        this.ª = ((this.\u00e2 != null && this.\u00e2.E == 1) ? (size.height - 1 - 29) : 1);
        this.H = new _A(this);
        if (this.r == null || this.r.V != size.width || this.r.Q != size.height) {
            this.r = new E(size.width, size.height, true);
            this.µ = this.createImage(this.r.W);
        }
        this.\u00f6.A(this.r);
        this.\u00f6.A();
        this.\u00cb = System.currentTimeMillis();
        this.g = -1L;
        this.H.start();
        this.repaint();
        this.A(0, 0, false);
    }
    
    protected synchronized void E() {
        if (this.H == null) {
            return;
        }
        if (this.H.isAlive()) {
            try {
                this.H.checkAccess();
                this.H.stop();
            }
            catch (SecurityException ex) {
                this.H.destroy();
            }
        }
        this.H = null;
        this.\u00d4 = null;
    }
    
    protected void A(final int n, int n2, final boolean b) {
        try {
            if (this.\u00f6 == null) {
                if (this.getCursor() != this.\u00ca) {
                    this.setCursor(this.\u00ca);
                }
                return;
            }
            final int a = this.\u00f6.A(this.\u00fb, this.\u00fa);
            if (!b && (((!this.\u00e0 || this.T == null || !this.\u00e8.equals("")) && this.d && this.A(this.\u00fb, this.\u00fa, this.\u00ce, this.º, this.ª)) || (this.\u00e0 && this.T != null && !this.\u00e8.equals("") && this.A(this.\u00fb, this.\u00fa, this.T, this.r.V - this.T.getWidth(null) - this.j, this.i)))) {
                if (this.getCursor() != this.¥) {
                    this.\u00ed = true;
                    this.setCursor(this.¥);
                }
            }
            else if (!b || (b && n == 0 && n2 == 0)) {
                if (this.\u00eb && this.\u00e2 != null && (this.\u00e2.D(this.\u00fb, this.\u00fa) != null || this.\u00e2.l != null)) {
                    this.setCursor(this.\u00d8);
                    return;
                }
                if (this.\u00f6 != null) {
                    if (a != 0) {
                        if (this.getCursor() != this.¥) {
                            this.setCursor(this.¥);
                        }
                    }
                    else if (this.getCursor() != this.\u00ec) {
                        this.setCursor(this.\u00ec);
                    }
                }
                else {
                    this.setCursor(this.\u00ec);
                }
            }
            else if (n == 0 && n2 == 0) {
                if (this.\u00eb && this.\u00e2 != null && (this.\u00e2.D(this.\u00fb, this.\u00fa) != null || this.\u00e2.l != null)) {
                    this.setCursor(this.\u00d8);
                    return;
                }
                if (a != 0) {
                    if (this.getCursor() != this.¥) {
                        this.setCursor(this.¥);
                    }
                }
                else {
                    this.setCursor(this.\u00ec);
                }
            }
            else if (n2 >= 0) {
                if (a != 0) {
                    if (this.getCursor() != this.¥) {
                        this.setCursor(this.¥);
                    }
                    return;
                }
                if (n > 4 * n2) {
                    this.setCursor(this.\u00e3);
                }
                else if (-n > 4 * n2) {
                    this.setCursor(this.\u00d6);
                }
                else if (4 * n > n2) {
                    this.setCursor(this.b);
                }
                else if (-4 * n > n2) {
                    this.setCursor(this.\u00e6);
                }
                else {
                    this.setCursor(this.v);
                }
            }
            else {
                if (a != 0) {
                    if (this.getCursor() != this.¥) {
                        this.setCursor(this.¥);
                    }
                    return;
                }
                n2 = -n2;
                if (n > 4 * n2) {
                    this.setCursor(this.\u00e3);
                }
                else if (-n > 4 * n2) {
                    this.setCursor(this.\u00d6);
                }
                else if (4 * n > n2) {
                    this.setCursor(this.\u00de);
                }
                else if (-4 * n > n2) {
                    this.setCursor(this.s);
                }
                else {
                    this.setCursor(this.\u00d9);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    protected void A(final int n) {
        if (this.\u00f6 == null) {
            return;
        }
        if (n < 0 || n > 255) {
            return;
        }
        final String s = this.\u00f6.d[n];
        final String s2 = this.\u00f6.j[n];
        try {
            if (s2 != null && s2.equals("myself")) {
                this.D(s);
            }
            else if (s2 != null) {
                URL url = null;
                try {
                    url = new URL(this.getDocumentBase(), s);
                }
                catch (Exception ex) {}
                if (url != null) {
                    this.getAppletContext().showDocument(url, s2);
                }
            }
            else {
                URL url2 = null;
                try {
                    url2 = new URL(this.getDocumentBase(), s);
                }
                catch (Exception ex2) {}
                if (url2 != null) {
                    this.getAppletContext().showDocument(url2);
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    protected String B(final int n) {
        if (this.\u00f6 == null) {
            return "";
        }
        if (n < 0 || n > 255) {
            return "";
        }
        return this.\u00f6.w[n];
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
    
    protected static BufferedImage A(final Image image, final int n) {
        final BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), n);
        bufferedImage.createGraphics().drawImage(image, null, null);
        return bufferedImage;
    }
    
    protected void D() {
        int b = this.\u00f6.b + 1;
        if (b > 4) {
            b = 0;
        }
        this.\u00f6.b = b;
        this.\u00ea = true;
    }
    
    public void A() {
        if (!this.Z && !this.o) {
            return;
        }
        this.o = !this.o;
        this.g = System.currentTimeMillis();
    }
    
    public void B(final String c) {
        if (c == null) {
            return;
        }
        if (c.equals(this.C)) {
            return;
        }
        this.showStatus(this.C = c);
    }
    
    protected void D(final String £) {
        System.currentTimeMillis();
        try {
            synchronized (this.\u00f6) {
                if (£ == null) {
                    // monitorexit(this.\u00f6)
                    return;
                }
                int n = -1;
                for (int i = 0; i < this.¢.length; ++i) {
                    if (£.equals(this.¢[i])) {
                        n = i;
                        break;
                    }
                }
                boolean b = false;
                if (n != -1) {
                    this.C();
                    this.E((String)null);
                    if (n == 0) {
                        b = true;
                    }
                    final String a = this.A(null, (n == 0) ? "params" : ("params" + (n + 1)));
                    if (a != null) {
                        b = true;
                        final int index;
                        final int index2;
                        if ((index = a.indexOf(123, 0)) >= 0 && (index2 = a.indexOf(125, index)) >= 0) {
                            this.E(a.substring(index + 1, index2));
                        }
                    }
                }
                this.£ = £;
                final byte[] b2 = this.B(this.B(this.£, "txt"), false);
                if (b2 != null) {
                    this.E(new String(b2));
                }
                else if (!b) {
                    // monitorexit(this.\u00f6)
                    return;
                }
            }
            // monitorexit(this.\u00f6 = null)
            this.E();
            this.paint(this.getGraphics());
            this.\u00f6 = null;
            this.F();
            this.start();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void openPanorama(final String s) {
        this.D(s);
    }
    
    public void startAutoRotate() {
        if (!this.o) {
            this.A();
        }
    }
    
    public void stopAutoRotate() {
        if (this.o) {
            this.A();
        }
    }
    
    public void toggleHotspots() {
        if (this.\u00f6 == null) {
            return;
        }
        if (this.\u00f6.y) {
            this.\u00f6.X = !this.\u00f6.X;
            this.\u00ea = true;
        }
    }
    
    public void setAutoRotate(final double h, final double a, final double \u00e4) {
        this.h = h;
        this.h *= 0.017453292519943295;
        if (this.h < -1.7453292519943295) {
            this.h = -1.7453292519943295;
        }
        else if (this.h > 1.7453292519943295) {
            this.h = 1.7453292519943295;
        }
        this.A = a;
        this.A *= 0.017453292519943295;
        if (this.A < -0.8726646259971648) {
            this.A = -0.8726646259971648;
        }
        else if (this.A > 0.8726646259971648) {
            this.A = 0.8726646259971648;
        }
        this.\u00c4 = \u00e4;
        if (this.\u00c4 < 0.1) {
            this.\u00c4 = 0.1;
        }
        else if (this.\u00c4 > 2.0) {
            this.\u00c4 = 2.0;
        }
    }
    
    public void showToolbar() {
        this.\u00eb = true;
        this.\u00ed = true;
    }
    
    public void hideToolbar() {
        this.\u00eb = false;
        this.\u00ed = true;
    }
    
    public void setView(final double n, final double n2, final double n3) {
        if (this.\u00f6 == null) {
            return;
        }
        final boolean o = this.o;
        this.o = false;
        this.g = System.currentTimeMillis();
        this.c = true;
        this.\u00fe = n * 3.141592653589793 / 180.0;
        this.\u00dd = n2 * 3.141592653589793 / 180.0;
        this.\u00f5 = n3 * 3.141592653589793 / 180.0;
        this.o = o;
    }
    
    public boolean isRotating() {
        return this.o;
    }
    
    protected synchronized void A(final double n, final double n2, final double n3) {
        if (this.\u00f6 == null) {
            return;
        }
        final double i = this.\u00f6.I;
        final double £ = this.\u00f6.£;
        final double t = this.\u00f6.T;
        final double \u00fe = i + n;
        final double \u00fd = £ + n2;
        final double \u00f5 = t * n3;
        this.g = System.currentTimeMillis();
        this.o = false;
        this.c = true;
        this.\u00fe = \u00fe;
        this.\u00dd = \u00fd;
        this.\u00f5 = \u00f5;
    }
    
    public void panLeft() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(-t / 40.0, 0.0, 1.0);
        }
        catch (Exception ex) {}
    }
    
    public void panRight() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(t / 40.0, 0.0, 1.0);
        }
        catch (Exception ex) {}
    }
    
    public void panUp() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(0.0, -t / 40.0, 1.0);
        }
        catch (Exception ex) {}
    }
    
    public void panDown() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(0.0, t / 40.0, 1.0);
        }
        catch (Exception ex) {}
    }
    
    public void zoomIn() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(0.0, 0.0, 0.9615384615384615);
        }
        catch (Exception ex) {}
    }
    
    public void zoomOut() {
        if (this.\u00f6 == null) {
            return;
        }
        final double t = this.\u00f6.T;
        try {
            this.A(0.0, 0.0, 1.04);
        }
        catch (Exception ex) {}
    }
    
    protected void C(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.\u00fb = x;
        this.\u00fa = y;
        if (!this.\u00c7) {
            return;
        }
        if (this.\u00f6 == null) {
            return;
        }
        this.\u00db.x = mouseEvent.getX();
        this.\u00db.y = mouseEvent.getY();
    }
    
    protected synchronized void processKeyEvent(final KeyEvent keyEvent) {
        if (this.\u00c7) {
            return;
        }
        final int keyCode = keyEvent.getKeyCode();
        if (keyCode == 0) {
            return;
        }
        final char keyChar = keyEvent.getKeyChar();
        final int id = keyEvent.getID();
        double i = this.\u00f6.I;
        double £ = this.\u00f6.£;
        double t = this.\u00f6.T;
        boolean b = false;
        if (id == 401 && (keyChar == '+' || keyCode == 16 || keyChar == '-' || keyCode == 17 || keyCode == 37 || keyChar == '4' || keyChar == 'a' || keyCode == 39 || keyChar == '6' || keyChar == 'd' || keyCode == 38 || keyChar == '8' || keyChar == 'w' || keyCode == 40 || keyChar == '2' || keyChar == 's')) {
            if (System.currentTimeMillis() - keyEvent.getWhen() < 400L) {
                if (keyChar == '+' || keyCode == 16) {
                    b = true;
                    t /= 1.04;
                }
                else if (keyChar == '-' || keyCode == 17) {
                    b = true;
                    t *= 1.04;
                }
                else if (keyCode == 37 || keyChar == '4' || keyChar == 'a') {
                    b = true;
                    i -= t / 40.0;
                }
                else if (keyCode == 39 || keyChar == '6' || keyChar == 'd') {
                    b = true;
                    i += t / 40.0;
                }
                else if (keyCode == 38 || keyChar == '8' || keyChar == 'w') {
                    b = true;
                    £ -= t / 40.0;
                }
                else if (keyCode == 40 || keyChar == '2' || keyChar == 's') {
                    b = true;
                    £ += t / 40.0;
                }
                if (b) {
                    this.g = System.currentTimeMillis();
                    this.o = false;
                }
                this.c = true;
                this.\u00fe = i;
                this.\u00dd = £;
                this.\u00f5 = t;
            }
        }
        else if (id == 402 && (keyChar == '+' || keyCode == 16 || keyChar == '-' || keyChar == '\u0011' || keyCode == 37 || keyChar == '4' || keyChar == 'a' || keyCode == 39 || keyChar == '6' || keyChar == 'd' || keyCode == 38 || keyChar == '8' || keyChar == 'w' || keyCode == 40 || keyChar == '2' || keyChar == 's')) {
            this.c = false;
        }
        else if (id == 401 && keyChar == 'h') {
            this.toggleHotspots();
        }
        else if (id == 401 && keyChar == 'i') {
            this.D();
        }
        else if (id == 401 && keyChar == 't') {
            this.\u00eb = !this.\u00eb;
            this.\u00ed = true;
        }
        else {
            if (id != 401 || (keyCode != 10 && keyChar != ' ')) {
                return;
            }
            this.A();
            this.\u00ed = true;
        }
        keyEvent.consume();
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 503) {
            this.B(mouseEvent);
        }
        else if (mouseEvent.getID() == 506) {
            this.C(mouseEvent);
        }
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 500) {
            this.E(mouseEvent);
        }
        else if (mouseEvent.getID() == 501) {
            this.A(mouseEvent);
        }
        else if (mouseEvent.getID() == 502) {
            this.D(mouseEvent);
        }
    }
    
    protected void B(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.\u00fb = x;
        this.\u00fa = y;
        if (this.\u00f6 == null) {
            if (this.getCursor() != this.\u00ca) {
                this.setCursor(this.\u00ca);
            }
            return;
        }
        if ((this.\u00e0 && this.T != null) || !this.d || !this.A(x, y, this.\u00ce, this.º, this.ª)) {
            if (!this.\u00e0 || this.T == null || this.\u00e8.equals("") || !this.A(this.\u00fb, this.\u00fa, this.T, this.r.V - this.T.getWidth(null) - this.j, this.i)) {
                if (this.\u00eb && this.\u00e2 != null) {
                    final C._A t = this.\u00e2.t;
                    final C._A a = this.\u00e2.A(x, y);
                    if (t != a) {
                        this.\u00ed = true;
                    }
                    if (a != null) {
                        try {
                            this.setCursor(this.\u00d8);
                        }
                        catch (Exception ex) {}
                        return;
                    }
                }
                if (this.\u00f6 != null && this.\u00f6.A(x, y) != 0) {
                    try {
                        if (this.getCursor() != this.¥) {
                            this.\u00ed = true;
                        }
                        this.setCursor(this.¥);
                    }
                    catch (Exception ex2) {}
                    return;
                }
                try {
                    if (this.getCursor() != this.\u00ec) {
                        this.\u00ed = true;
                    }
                    this.setCursor(this.\u00ec);
                }
                catch (Exception ex3) {}
                return;
            }
        }
        try {
            if (this.getCursor() != this.¥) {
                this.\u00ed = true;
            }
            this.setCursor(this.¥);
        }
        catch (Exception ex4) {}
    }
    
    protected void E(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if ((!this.\u00e0 || this.T == null) && this.d && this.A(x, y, this.\u00ce, this.º, this.ª)) {
            if (Math.abs(this.m.x - x) > 3 || Math.abs(this.m.y - y) > 3) {
                return;
            }
            try {
                this.getAppletContext().showDocument(new URL("http://www.tshsoft.com"), "_blank");
            }
            catch (Exception ex) {}
        }
        else if (this.\u00e0 && this.T != null && !this.\u00e8.equals("") && this.A(this.\u00fb, this.\u00fa, this.T, this.r.V - this.T.getWidth(null) - this.j, this.i)) {
            if (Math.abs(this.m.x - x) > 3 || Math.abs(this.m.y - y) > 3) {
                return;
            }
            try {
                this.getAppletContext().showDocument(new URL(this.\u00e8));
            }
            catch (Exception ex2) {}
        }
        else {
            if (this.\u00eb && this.\u00e2 != null && this.\u00e2.D(x, y) != null) {
                return;
            }
            if (this.\u00f6 != null) {
                if (Math.abs(this.m.x - x) > 3 || Math.abs(this.m.y - y) > 3) {
                    return;
                }
                final int a = this.\u00f6.A(x, y);
                if (a != 0) {
                    if (this.\u00cc && this.\u00f6 != null) {
                        if (this.H != null) {
                            this.H.A = true;
                        }
                        final Point a2 = this.\u00f6.A(x, y, this.\u00f6.I, this.\u00f6.£, this.\u00f6.T);
                        final D._A a3 = this.\u00f6.A(a2.x, (double)a2.y);
                        while (this.\u00f6.T > this.\u00f6.n) {
                            final double t = this.\u00f6.T;
                            this.\u00f6.A(this.\u00f6.I * 0.8 + a3.B * 0.2, this.\u00f6.£ * 0.8 + a3.A * 0.2, this.\u00f6.T);
                            this.\u00f6.A(this.\u00f6.I, this.\u00f6.£, this.\u00f6.T * 0.9);
                            this.\u00f6.A();
                            this.update(this.getGraphics());
                            if (t <= this.\u00f6.T) {
                                break;
                            }
                        }
                    }
                    if (this.H != null) {
                        this.H.A = false;
                    }
                    this.A(a);
                }
            }
        }
    }
    
    protected void A(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.m.x = x;
        this.m.y = y;
        this.\u00db.x = x;
        this.\u00db.y = y;
        this.\u00c2 = 0.0;
        this.\u00c0 = 0.0;
        if (this.\u00eb && this.\u00e2 != null) {
            final C._A l = this.\u00e2.l;
            final C._A c = this.\u00e2.C(x, y);
            if (l != c) {
                this.\u00ea = true;
            }
            if (c != null) {
                if (c.D != null) {
                    this.g = System.currentTimeMillis();
                    this.o = false;
                }
                return;
            }
        }
        this.o = false;
        this.\u00c7 = true;
        this.g = System.currentTimeMillis();
    }
    
    protected void D(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x0) {
            return;
        }
        if (this.\u00c7) {
            this.g = System.currentTimeMillis();
        }
        this.\u00c7 = false;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.\u00eb && this.\u00e2 != null) {
            if (this.\u00e2.l != null) {
                if (this.\u00e2.D(x, y) == this.\u00e2.l) {
                    if (this.\u00e2.l.G != null) {
                        this.\u00e2.l.G.A(this, null);
                    }
                }
                else {
                    this.\u00ea = true;
                }
            }
            this.\u00e2.l = null;
            final C._A t = this.\u00e2.t;
            final C._A a = this.\u00e2.A(x, y);
            if (t != a) {
                this.\u00ea = true;
            }
            if (a != null) {
                try {
                    this.setCursor(this.\u00d8);
                }
                catch (Exception ex) {}
                return;
            }
        }
        if (this.\u00f6 != null && this.\u00f6.A(mouseEvent.getX(), mouseEvent.getY()) != 0) {
            try {
                this.setCursor(this.¥);
            }
            catch (Exception ex2) {}
            return;
        }
        try {
            this.setCursor(this.\u00ec);
        }
        catch (Exception ex3) {}
    }
    
    Image A(final byte[] array) {
        if (array == null) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            return null;
        }
        if (image.getWidth(null) <= 0) {
            return null;
        }
        return image;
    }
    
    byte[] A(final String s, final boolean b) {
        final File file = new File(s);
        final byte[] array = null;
        byte[] array2;
        try {
            if (!file.isFile()) {
                return null;
            }
            final long length = file.length();
            if (length <= 0L) {
                return null;
            }
            array2 = new byte[(int)length];
            final FileInputStream fileInputStream = new FileInputStream(file);
            for (int n = 32768, n2 = 0; n2 < length; n2 += n) {
                if (fileInputStream.read(array2, n2, ((int)length - n2 >= n) ? n : ((int)(length - n2))) == -1) {
                    fileInputStream.close();
                    return null;
                }
                if (b) {
                    this.\u00f9 = n2 * 100 / (int)length;
                    this.update(this.getGraphics());
                }
            }
            fileInputStream.close();
            if (b) {
                this.\u00f9 = 100;
                this.update(this.getGraphics());
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array2;
    }
    
    byte[] A(final InputStream inputStream, long n, final boolean b) {
        final int n2 = 16384;
        if (n <= 0L) {
            n = n2;
        }
        byte[] array = new byte[(int)n];
        final byte[] array2 = new byte[n2];
        int n3 = 0;
        try {
            int read;
            while ((read = inputStream.read(array2, 0, array2.length)) > 0) {
                if (n3 + read > array.length) {
                    final byte[] array3 = array;
                    array = new byte[n3 + read];
                    System.arraycopy(array3, 0, array, 0, n3);
                }
                System.arraycopy(array2, 0, array, n3, read);
                n3 += read;
                if (b) {
                    this.\u00f9 = n3 * 100 / (int)n;
                    this.update(this.getGraphics());
                }
            }
            if (b) {
                this.\u00f9 = 100;
                this.update(this.getGraphics());
            }
        }
        catch (Exception ex) {
            return null;
        }
        if (n3 == 0) {
            return null;
        }
        return array;
    }
    
    byte[] B(final String s, final boolean b) {
        final byte[] a = this.A(s, b);
        if (a != null) {
            return a;
        }
        try {
            final URLConnection openConnection = new URL(this.getDocumentBase(), s).openConnection();
            openConnection.setUseCaches(true);
            int contentLength = 0;
            try {
                contentLength = openConnection.getContentLength();
            }
            catch (Exception ex) {}
            final InputStream inputStream = openConnection.getInputStream();
            final byte[] a2 = this.A(inputStream, contentLength, b);
            inputStream.close();
            if (a2 != null) {
                return a2;
            }
        }
        catch (Exception ex2) {}
        try {
            final URLConnection openConnection2 = new URL(this.getCodeBase(), s).openConnection();
            openConnection2.setUseCaches(true);
            int contentLength2 = 0;
            try {
                contentLength2 = openConnection2.getContentLength();
            }
            catch (Exception ex3) {}
            final InputStream inputStream2 = openConnection2.getInputStream();
            final byte[] a3 = this.A(inputStream2, contentLength2, b);
            inputStream2.close();
            if (a3 != null) {
                return a3;
            }
        }
        catch (Exception ex4) {}
        return null;
    }
    
    public void destroy() {
        try {
            if (this.H.isAlive()) {
                this.H.checkAccess();
                this.H.stop();
            }
        }
        catch (Exception ex) {}
        this.H = null;
    }
    
    public String getAppletInfo() {
        return "PanoramaStudio Viewer " + this.l + " © 2005, www.tshsoft.com";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "file", "filename/url", "panorama image file" }, { "fileN", "filename/url", "additional panorama image files, where N is 2,3,..." }, { "startWith", "int", "number of initial panorama (default: 1)" }, { "params", "parameter list", "list of specific parameters for the first panorama. Syntax: { param1=value1; param2=value2; }" }, { "paramsN", "parameter list", "same as params for panorama #N." }, { "waitImage", "filename/url", "optional image displayed while loading" }, { "logo", "filename/url", "optional image displayed as logo" }, { "logoUrl", "filename/url", "hyperlink associated with the logo image" }, { "logoX", "int", "horizontal logo image position starting from the right" }, { "logoY", "int", "vertical logo image position starting from the top" }, { "bgWait", "hex number", "load screen background color (default: 000000)" }, { "fgWait", "hex number", "load screen foreground color (default: FFFFFF)" }, { "autoRotate", "boolean (true/false)", "enable auto rotate (default: false)" }, { "autoPanRate", "float", "auto-rotate pan speed (deg/sec) (default: 2.0)" }, { "autoTiltRate", "float", "auto-rotate tilt speed (deg/sec) (default: 0.0)" }, { "autoZoomRate", "float", "auto-rotate zoom speed (default: 1.0)" }, { "autoRotateRestart", "int", "auto-rotate restart after user interaction (seconds) (default: 5)" }, { "loadString", "string", "wait-text displayed while loading" }, { "title", "string", "title of panorama" }, { "quality", "0-4", "quality setting (0: lowest/fastest,1: low(default), 2: med,3: hi,4: dynamic)" }, { "hotspotN", "filename/url", "filename/url for hotspot number N, where N is 0-255" }, { "targetN", "string", "html-target or 'myself' for hotspot number N, where N is 0-255" }, { "commentN", "string", "short comment for hotspot number N, where N is 0-255" }, { "hscolorN", "hex number", "color for hotspot number N, where N is 0-255" }, { "pan", "float", "pan; horizontal position" }, { "minpan", "float", "minimum pan position (horizontal) (default: 0.0)" }, { "maxpan", "float", "maximum pan position (horizontal) (default: 360.0)" }, { "tilt", "float", "tilt; vertical position" }, { "mintilt", "float", "minimum tilt position (vertical)" }, { "maxtilt", "float", "maximum tilt position (vertical)" }, { "hfov", "float", "default horizontal field-of-view" }, { "minhfov", "float (5-165)", "min. horizontal field-of-view (default: 5.0)" }, { "maxhfov", "float (5-165)", "max. horizontal field-of-view (default: 165.0)" }, { "spherical", "boolean (true/false)", "spherical or cylindrical panorama (default: true)" }, { "borderColor", "hex number", "border color (default: 000000)" }, { "shadowColor", "hex number", "text shadow color (default: 000000)" }, { "textColor", "hex number", "text color (default: FFFFFF)" }, { "showHotspots", "boolean (true/false)", "show hotspots (default: false)" }, { "showBorder", "boolean (true/false)", "draw a border line around the window (default: false)" }, { "mass", "float (0.1-2.0)", "mouse mass (default: 0.5)" }, { "mouseSen", "float (0.1-2.0)", "mouse (default: 1.2)" }, { "tbLayout", "0-2", "3 different toolbar layouts (default: 0)" }, { "tbPosition", "0-1", "toolbar position (0: bottom,1: top) (default: 0)" }, { "tbButtonType", "0-1", "2 different button types" }, { "tbColorProfile", "0-4", "5 different toolbar color profiles" }, { "tbBGOpacity", "0-255", "opacity of the toolbar background (default: 128)" }, { "tbButtonOpacity", "0-255", "opacity of the toolbar buttons (default: 192)" }, { "tbHotButtonOpacity", "0-255", "opacity of the toolbar hot buttons (default: 255)" }, { "tbShowPlayButton", "boolean (true/false)", "display a 'play' button in the toolbar" }, { "tbShowBackground", "boolean (true/false)", "display the toolbar background" }, { "tbButtonWidth", "int (18-48)", "width of the toolbar buttons" }, { "tbButtonHeight", "int (18-48)", "height of the toolbar buttons" }, { "useStatusBar", "boolean (true/false)", "shows messages in the browser's status bar and not in the applet window (default: false)" } };
    }
    
    void A(final E e, final byte[] array, final int n) {
        final int n2 = n << 24;
        final int v = e.V;
        for (int q = e.Q, i = 0; i < q; ++i) {
            for (int j = 0; j < v; ++j) {
                final int n3 = array[i * v + j] & 0xFF;
                if (n3 != 255) {
                    e.f[i * v + j] = (n3 | n3 << 8 | n3 << 16 | n2);
                }
            }
        }
    }
    
    boolean A(int n, int n2, final Image image, final int n3, final int n4) {
        n -= n3;
        n2 -= n4;
        return image != null && (n >= 0 && n2 >= 0 && n < image.getWidth(null) && n2 < image.getHeight(null));
    }
    
    static /* synthetic */ void access$2(final PanoStudioViewer panoStudioViewer, final boolean d) {
        panoStudioViewer.d = d;
    }
    
    static /* synthetic */ void access$4(final PanoStudioViewer panoStudioViewer, final Image \u00ee) {
        panoStudioViewer.\u00ce = \u00ee;
    }
    
    protected class _C
    {
        String F;
        String C;
        String E;
        int B;
        int A;
        int D;
        
        protected _C() {
            this.F = null;
            this.C = null;
            this.E = null;
            this.B = 8388608;
            this.A = 32768;
            this.D = 255;
        }
    }
    
    private class _B
    {
        void A() {
            if (PanoStudioViewer.this.\u00d4 == null) {
                int width = PanoStudioViewer.this.getSize().width;
                int height = PanoStudioViewer.this.getSize().height;
                if (width == 0) {
                    width = 2;
                }
                if (height == 0) {
                    height = 2;
                }
                PanoStudioViewer.this.\u00d4 = PanoStudioViewer.this.createImage(width, height);
                if (PanoStudioViewer.this.\u00d4 != null) {
                    PanoStudioViewer.this.O = PanoStudioViewer.this.\u00d4.getGraphics();
                }
            }
        }
        
        void A(final Graphics graphics) {
            PanoStudioViewer.this.A(PanoStudioViewer.this.O, graphics);
        }
    }
    
    private class _F extends _B
    {
        void A() {
            if (PanoStudioViewer.this.\u00d4 == null) {
                int width = PanoStudioViewer.this.getSize().width;
                int height = PanoStudioViewer.this.getSize().height;
                if (width == 0) {
                    width = 2;
                }
                if (height == 0) {
                    height = 2;
                }
                if (PanoStudioViewer.this.\u00d4 == null) {
                    PanoStudioViewer.this.\u00d4 = PanoStudioViewer.this.createVolatileImage(width, height);
                }
                if (PanoStudioViewer.this.\u00d4 != null) {
                    PanoStudioViewer.this.O = ((VolatileImage)PanoStudioViewer.this.\u00d4).createGraphics();
                }
            }
        }
        
        void A(final Graphics graphics) {
            VolatileImage volatileImage = (VolatileImage)PanoStudioViewer.this.\u00d4;
            if (volatileImage == null) {
                return;
            }
            final Graphics o = PanoStudioViewer.this.O;
            do {
                if (volatileImage.validate(PanoStudioViewer.this.getGraphicsConfiguration()) == 2) {
                    int width = PanoStudioViewer.this.getSize().width;
                    int height = PanoStudioViewer.this.getSize().height;
                    if (width == 0) {
                        width = 2;
                    }
                    if (height == 0) {
                        height = 2;
                    }
                    PanoStudioViewer.this.\u00d4 = PanoStudioViewer.this.createVolatileImage(width, height);
                    volatileImage = (VolatileImage)PanoStudioViewer.this.\u00d4;
                    PanoStudioViewer.this.O = volatileImage.createGraphics();
                }
                PanoStudioViewer.this.A(PanoStudioViewer.this.O, graphics);
            } while (volatileImage.contentsLost());
        }
    }
    
    private class _D
    {
        _D() {
            if (PanoStudioViewer.this.\u00ec == null) {
                PanoStudioViewer.this.\u00ec = new Cursor(13);
            }
            if (PanoStudioViewer.this.\u00d6 == null) {
                PanoStudioViewer.this.\u00d6 = new Cursor(10);
            }
            if (PanoStudioViewer.this.\u00e3 == null) {
                PanoStudioViewer.this.\u00e3 = new Cursor(11);
            }
            if (PanoStudioViewer.this.\u00d9 == null) {
                PanoStudioViewer.this.\u00d9 = new Cursor(8);
            }
            if (PanoStudioViewer.this.v == null) {
                PanoStudioViewer.this.v = new Cursor(9);
            }
            if (PanoStudioViewer.this.s == null) {
                PanoStudioViewer.this.s = new Cursor(6);
            }
            if (PanoStudioViewer.this.\u00de == null) {
                PanoStudioViewer.this.\u00de = new Cursor(7);
            }
            if (PanoStudioViewer.this.\u00e6 == null) {
                PanoStudioViewer.this.\u00e6 = new Cursor(4);
            }
            if (PanoStudioViewer.this.b == null) {
                PanoStudioViewer.this.b = new Cursor(5);
            }
            if (PanoStudioViewer.this.¥ == null) {
                PanoStudioViewer.this.¥ = new Cursor(12);
            }
            if (PanoStudioViewer.this.\u00d8 == null) {
                PanoStudioViewer.this.\u00d8 = new Cursor(0);
            }
            if (PanoStudioViewer.this.\u00ca == null) {
                PanoStudioViewer.this.\u00ca = new Cursor(3);
            }
        }
    }
    
    private class _E extends _D
    {
        _E() {
            final Toolkit toolkit = PanoStudioViewer.this.getToolkit();
            Dimension bestCursorSize = new Dimension(0, 0);
            int maximumCursorColors = 0;
            try {
                bestCursorSize = toolkit.getBestCursorSize(32, 32);
                maximumCursorColors = toolkit.getMaximumCursorColors();
            }
            catch (Exception ex) {}
            if (!bestCursorSize.equals(new Dimension(0, 0)) && maximumCursorColors != 0) {
                panoStudioViewer.A.A();
                PanoStudioViewer.this.\u00ec = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.I, new Point(16, 16), new Dimension(32, 32), "cursorMove");
                PanoStudioViewer.this.\u00d6 = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.A, new Point(16, 16), new Dimension(32, 32), "cursorLeft");
                PanoStudioViewer.this.\u00e3 = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.M, new Point(16, 16), new Dimension(32, 32), "cursorRight");
                PanoStudioViewer.this.\u00d9 = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.C, new Point(16, 16), new Dimension(32, 32), "cursorUp");
                PanoStudioViewer.this.v = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.K, new Point(16, 16), new Dimension(32, 32), "cursorDown");
                PanoStudioViewer.this.s = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.H, new Point(16, 16), new Dimension(32, 32), "cursorLeftUp");
                PanoStudioViewer.this.\u00de = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.E, new Point(16, 16), new Dimension(32, 32), "cursorRightUp");
                PanoStudioViewer.this.\u00e6 = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.G, new Point(16, 16), new Dimension(32, 32), "cursorLeftDown");
                PanoStudioViewer.this.b = panoStudioViewer.A.A(toolkit, panoStudioViewer.A.J, new Point(16, 16), new Dimension(32, 32), "cursorRightDown");
            }
            new _D();
        }
    }
    
    class _A extends Thread
    {
        PanoStudioViewer B;
        boolean A;
        
        public _A(final PanoStudioViewer b) {
            this.A = false;
            this.B = b;
        }
        
        public void run() {
            try {
                int \u00e5 = 0;
                int n = 0;
                long n2 = 0L;
                final long[] array = new long[4];
                int n3 = 0;
                while (true) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (PanoStudioViewer.this.\u00f6 != null) {
                        if ((!PanoStudioViewer.this.\u00e0 || PanoStudioViewer.this.T == null) && PanoStudioViewer.this.d) {
                            if (currentTimeMillis - PanoStudioViewer.this.\u00cb > 20000L) {
                                PanoStudioViewer.access$2(PanoStudioViewer.this, false);
                                PanoStudioViewer.this.\u00ed = true;
                            }
                            else if (currentTimeMillis - PanoStudioViewer.this.\u00cb >= 18000L) {
                                PanoStudioViewer.this.A(PanoStudioViewer.this.I, PanoStudioViewer.F, (PanoStudioViewer.this.V ? 255 : 160) * (20000 - (int)(currentTimeMillis - PanoStudioViewer.this.\u00cb)) / 2000);
                                PanoStudioViewer.access$4(PanoStudioViewer.this, PanoStudioViewer.this.createImage(PanoStudioViewer.this.I.W));
                                PanoStudioViewer.this.\u00ed = true;
                            }
                        }
                        long n4 = currentTimeMillis - n2;
                        if (n4 == 0L) {
                            n4 = 5L;
                        }
                        if (n2 == 0L) {
                            n4 = 0L;
                        }
                        n2 = currentTimeMillis;
                        array[n3++] = n4;
                        n3 %= array.length;
                        int n5 = 0;
                        long n6 = 0L;
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i] != 0L) {
                                n6 += array[i];
                                ++n5;
                            }
                        }
                        if (n5 != 0) {
                            n6 /= n5;
                        }
                        if (n6 == 0L) {
                            n6 = 5L;
                        }
                        final long n7 = n6;
                        boolean b = false;
                        if (PanoStudioViewer.this.\u00e2 != null && PanoStudioViewer.this.\u00eb && PanoStudioViewer.this.\u00e2.l != null && PanoStudioViewer.this.\u00e2.l.D != null) {
                            b = true;
                        }
                        if (PanoStudioViewer.this.\u00c7 || b) {
                            PanoStudioViewer.this.c = false;
                        }
                        if (PanoStudioViewer.this.c && currentTimeMillis - PanoStudioViewer.this.g >= 700L) {
                            PanoStudioViewer.this.c = false;
                        }
                        final double j = PanoStudioViewer.this.\u00f6.I;
                        final double £ = PanoStudioViewer.this.\u00f6.£;
                        double t = PanoStudioViewer.this.\u00f6.T;
                        double n8 = 0.0;
                        double n9 = 0.0;
                        if (PanoStudioViewer.this.\u00c7 || PanoStudioViewer.this.\u00c2 != 0.0 || PanoStudioViewer.this.\u00c0 != 0.0) {
                            if (PanoStudioViewer.this.\u00c7) {
                                n8 = PanoStudioViewer.this.\u00db.x - PanoStudioViewer.this.m.x;
                                n9 = PanoStudioViewer.this.\u00db.y - PanoStudioViewer.this.m.y;
                            }
                            else {
                                PanoStudioViewer.this.g = System.currentTimeMillis();
                            }
                            final int n10 = PanoStudioViewer.this.r.V / 2;
                            if (n8 > n10) {
                                n8 = n10;
                            }
                            else if (n8 < -n10) {
                                n8 = -n10;
                            }
                            if (n9 > n10) {
                                n9 = n10;
                            }
                            else if (n9 < -n10) {
                                n9 = -n10;
                            }
                            n9 *= PanoStudioViewer.this.\u00ef;
                            n8 *= PanoStudioViewer.this.\u00ef;
                            if (Math.abs(n8) <= 2.0) {
                                n8 = 0.0;
                            }
                            if (Math.abs(n9) <= 2.0) {
                                n9 = 0.0;
                            }
                            PanoStudioViewer.this.A((int)n8, (int)n9, PanoStudioViewer.this.\u00c7);
                            if (PanoStudioViewer.this.\u00e5 != 0.0) {
                                boolean b2 = false;
                                if (n8 == 0.0 && n9 == 0.0) {
                                    b2 = true;
                                    n8 = -PanoStudioViewer.this.\u00c2;
                                    n9 = -PanoStudioViewer.this.\u00c0;
                                }
                                double n11 = n6;
                                if (n11 > 1000.0) {
                                    n11 = 1000.0;
                                }
                                final double n12 = (n8 - PanoStudioViewer.this.\u00c2) / (PanoStudioViewer.this.\u00e5 / 2.0);
                                final double n13 = (n9 - PanoStudioViewer.this.\u00c0) / (PanoStudioViewer.this.\u00e5 / 2.0);
                                n8 = n12 * n11 / 1000.0 + PanoStudioViewer.this.\u00c2;
                                n9 = n13 * n11 / 1000.0 + PanoStudioViewer.this.\u00c0;
                                if (b2) {
                                    if ((PanoStudioViewer.this.\u00c2 > 0.0 && n8 < 0.0) || (PanoStudioViewer.this.\u00c2 < 0.0 && n8 > 0.0)) {
                                        n8 = 0.0;
                                    }
                                    if ((PanoStudioViewer.this.\u00c0 > 0.0 && n9 < 0.0) || (PanoStudioViewer.this.\u00c0 < 0.0 && n9 > 0.0)) {
                                        n9 = 0.0;
                                    }
                                }
                            }
                            if (Math.abs(n8) < 0.5 && Math.abs(n9) < 0.5) {
                                n8 = 0.0;
                                n9 = 0.0;
                            }
                            if (PanoStudioViewer.this.\u00c7 || PanoStudioViewer.this.\u00c2 != 0.0) {
                                PanoStudioViewer.this.\u00c2 = ((!PanoStudioViewer.this.\u00c7 && ((n8 > 0.0 && PanoStudioViewer.this.\u00c2 < 0.0) || (n8 < 0.0 && PanoStudioViewer.this.\u00c2 > 0.0))) ? 0.0 : n8);
                            }
                            if (PanoStudioViewer.this.\u00c7 || PanoStudioViewer.this.\u00c0 != 0.0) {
                                PanoStudioViewer.this.\u00c0 = ((!PanoStudioViewer.this.\u00c7 && ((n9 > 0.0 && PanoStudioViewer.this.\u00c0 < 0.0) || (n9 < 0.0 && PanoStudioViewer.this.\u00c0 > 0.0))) ? 0.0 : n9);
                            }
                        }
                        final boolean b3 = n8 != 0.0 || n9 != 0.0;
                        final boolean b4 = (!PanoStudioViewer.this.\u00c7 && n8 == 0.0 && n9 == 0.0 && !PanoStudioViewer.this.c && (PanoStudioViewer.this.g == -1L || currentTimeMillis - PanoStudioViewer.this.g >= PanoStudioViewer.this.¤) && PanoStudioViewer.this.B) || ((!PanoStudioViewer.this.\u00c7 || PanoStudioViewer.this.c) && PanoStudioViewer.this.o);
                        if (b4) {
                            PanoStudioViewer.this.o = true;
                        }
                        final boolean a = PanoStudioViewer.this.\u00f6.A(n7, (PanoStudioViewer.this.\u00c7 && b3) || PanoStudioViewer.this.c || b, b4);
                        if (PanoStudioViewer.this.\u00c7 || ((n8 != 0.0 || n9 != 0.0) && !PanoStudioViewer.this.c && !PanoStudioViewer.this.o) || b) {
                            final long n14 = n6;
                            double n18;
                            double n19;
                            if (b && PanoStudioViewer.this.\u00e2.l != null && PanoStudioViewer.this.\u00e2.l.D != null) {
                                final D._A a2 = PanoStudioViewer.this.\u00f6.new _A();
                                a2.B = j;
                                a2.A = £;
                                a2.C = t;
                                PanoStudioViewer.this.\u00e2.l.D.A(this.B, a2);
                                final double n15 = (a2.B - PanoStudioViewer.this.\u00f6.I) * n14 / 1000.0;
                                final double n16 = (a2.A - PanoStudioViewer.this.\u00f6.£) * n14 / 1000.0;
                                final double n17 = (a2.C - PanoStudioViewer.this.\u00f6.T) * n14 / 1000.0;
                                n18 = PanoStudioViewer.this.\u00f6.I + n15;
                                n19 = PanoStudioViewer.this.\u00f6.£ + n16;
                                t = PanoStudioViewer.this.\u00f6.T + n17;
                            }
                            else {
                                final int n20 = PanoStudioViewer.this.r.V / 2;
                                final double n21 = n8 / n20 * PanoStudioViewer.this.\u00f6.T * n14 / 1000.0;
                                final double n22 = n9 / n20 * PanoStudioViewer.this.\u00f6.T * n14 / 1000.0;
                                n18 = j + n21;
                                n19 = £ + n22;
                            }
                            PanoStudioViewer.this.\u00f6.A(n18, n19, t);
                            PanoStudioViewer.this.\u00f6.A();
                            PanoStudioViewer.this.\u00ed = true;
                        }
                        else if (PanoStudioViewer.this.c) {
                            PanoStudioViewer.this.\u00f6.A(PanoStudioViewer.this.\u00fe, PanoStudioViewer.this.\u00dd, PanoStudioViewer.this.\u00f5);
                            PanoStudioViewer.this.\u00f6.A();
                            PanoStudioViewer.this.A((int)n8, (int)n9, false);
                            PanoStudioViewer.this.\u00ed = true;
                        }
                        else if (b4) {
                            double n23 = j + PanoStudioViewer.this.h * (n6 / 1000.0);
                            final double n24 = £ + PanoStudioViewer.this.A * (n6 / 1000.0);
                            if (PanoStudioViewer.this.\u00c4 == 0.0) {
                                PanoStudioViewer.this.\u00c4 = 1.0;
                            }
                            final double n25 = t * (1.0 + (PanoStudioViewer.this.\u00c4 - 1.0) * (n6 / 1000.0));
                            if (n23 < 0.0) {
                                n23 += 6.283185307179586;
                            }
                            else if (n23 >= 6.283185307179586) {
                                n23 -= 6.283185307179586;
                            }
                            PanoStudioViewer.this.\u00f6.A(n23, n24, n25);
                            if ((PanoStudioViewer.this.h < 0.0 && n23 < PanoStudioViewer.this.\u00f6.I) || (PanoStudioViewer.this.h > 0.0 && n23 > PanoStudioViewer.this.\u00f6.I)) {
                                PanoStudioViewer.this.h = -PanoStudioViewer.this.h;
                            }
                            if ((PanoStudioViewer.this.A < 0.0 && n24 < PanoStudioViewer.this.\u00f6.£) || (PanoStudioViewer.this.A > 0.0 && n24 > PanoStudioViewer.this.\u00f6.£)) {
                                PanoStudioViewer.this.A = -PanoStudioViewer.this.A;
                            }
                            if ((PanoStudioViewer.this.\u00c4 < 1.0 && n25 < PanoStudioViewer.this.\u00f6.T) || (PanoStudioViewer.this.\u00c4 > 1.0 && n25 > PanoStudioViewer.this.\u00f6.T)) {
                                PanoStudioViewer.this.\u00c4 = 1.0 / PanoStudioViewer.this.\u00c4;
                            }
                            PanoStudioViewer.this.\u00f6.A();
                            PanoStudioViewer.this.A((int)n8, (int)n9, false);
                            PanoStudioViewer.this.\u00ed = true;
                        }
                        else if (a || PanoStudioViewer.this.\u00ea) {
                            PanoStudioViewer.this.\u00ea = false;
                            PanoStudioViewer.this.\u00f6.A();
                            PanoStudioViewer.this.\u00ed = true;
                        }
                        if (n6 > 5L) {
                            n += (int)n6;
                            ++\u00e5;
                            PanoStudioViewer.this.\u00c5 = \u00e5;
                            PanoStudioViewer.this.n = n;
                        }
                    }
                    if (PanoStudioViewer.this.\u00ed) {
                        PanoStudioViewer.this.\u00ed = false;
                        PanoStudioViewer.this.paint(PanoStudioViewer.this.getGraphics());
                    }
                    try {
                        final long n26 = System.currentTimeMillis() - currentTimeMillis;
                        if (n26 <= 10L) {
                            Thread.sleep(10L - n26);
                        }
                        while (this.A) {
                            Thread.sleep(10L);
                        }
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex) {
                System.err.println("Exception in render thread: " + ex.toString());
            }
        }
    }
}
