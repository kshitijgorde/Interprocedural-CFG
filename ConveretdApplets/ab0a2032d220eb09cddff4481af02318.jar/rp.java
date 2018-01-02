import java.util.Observable;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Observer;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp extends Canvas implements Observer, ItemListener, ActionListener
{
    public static final boolean iEb = false;
    public static final boolean jEb = false;
    public static final int kEb = 2;
    public static final int lEb = 0;
    public static final int mEb = 1;
    public static final int nEb = 2;
    public static final int oEb = 3;
    public static final int pEb = 4;
    private int qEb;
    private int rEb;
    private Tp[] sEb;
    public Up o;
    public Vp[] tEb;
    public Vp[] uEb;
    private Vector[] vEb;
    private Vector[] wEb;
    private Vector[] xEb;
    private Vector[] yEb;
    private Class zEb;
    private Wp AEb;
    private int BEb;
    private int CEb;
    private boolean DEb;
    private Color ja;
    private Color EEb;
    private Color FEb;
    private Color GEb;
    private Color V;
    private Color HEb;
    private Dimension MDb;
    private Graphics NDb;
    private Image ODb;
    private boolean[] IEb;
    private boolean[] JEb;
    private int KEb;
    private int LEb;
    private double[] MEb;
    private Rectangle[] NEb;
    private int OEb;
    private int PEb;
    private boolean[] QEb;
    private boolean REb;
    private boolean SEb;
    private boolean TEb;
    private boolean UEb;
    private boolean VEb;
    private int WEb;
    private int XEb;
    private Color YEb;
    private Color ZEb;
    private int _Fb;
    public Tp aFb;
    public Xp bFb;
    public static final int cFb = 0;
    public static final int dFb = 1;
    private int eFb;
    private boolean fFb;
    private boolean gFb;
    private PopupMenu hFb;
    private volatile yDb;
    private int iFb;
    private int jFb;
    private int kFb;
    private String lFb;
    private String mFb;
    private String nFb;
    private String oFb;
    private int pFb;
    private int qFb;
    private int rFb;
    Yp sFb;
    Yp tFb;
    Yp uFb;
    Yp vFb;
    Yp wFb;
    Yp xFb;
    Yp yFb;
    Yp zFb;
    Yp AFb;
    Yp BFb;
    Yp CFb;
    Yp DFb;
    Yp EFb;
    MenuItem FFb;
    Tp GFb;
    private boolean HFb;
    int p;
    int q;
    private DecimalFormat IFb;
    private DecimalFormat JFb;
    private Image KFb;
    private Graphics LFb;
    private int MFb;
    private int NFb;
    private static int OFb;
    private int PFb;
    static Class QFb;
    static Class RFb;
    static Class SFb;
    static Class TFb;
    
    public rp(final int n, final volatile yDb) {
        this.rEb = 0;
        this.HEb = Color.red;
        this.WEb = -1;
        this.XEb = -1;
        this._Fb = 1;
        this.eFb = 1;
        this.fFb = true;
        this.gFb = true;
        this.lFb = "\u3a29\u3a66\u3a55\u3a42\u3a4f\u3a56\u3a42\u3a57\u3a4a\u3a4c\u3a4d\u3a03\u3a75\u3a46\u3a51\u3a50\u3a4a\u3a4c\u3a4d\u3a03\u3a0e\u3a03\u3a4d\u3a4c\u3a57\u3a03\u3a45\u3a4c\u3a51\u3a03\u3a53\u3a56\u3a41\u3a4f\u3a4a\u3a40\u3a03\u3a56\u3a50\u3a46\u3a0d\u3a29";
        this.mFb = "\u3a65\u3a4d\u3a60\u3a4b\u3a42\u3a51\u3a57\u3a50\u3a03\u3a73\u3a51\u3a4c\u3a45\u3a46\u3a50\u3a50\u3a4a\u3a4c\u3a4d\u3a42\u3a4f\u3a03\u3a0b\u3a40\u3a0a\u3a03\u3a74\u3a67\u3a03\u3a70\u3a6c\u3a65\u3a77\u3a74\u3a62\u3a71\u3a66\u3a03\u3a4b\u3a57\u3a57\u3a53\u3a19\u3a0c\u3a0c\u3a54\u3a54\u3a54\u3a0d\u3a54\u3a47\u3a50\u3a4c\u3a45\u3a57\u3a54\u3a42\u3a51\u3a46\u3a0d\u3a40\u3a4c\u3a4e";
        this.nFb = "\u3a66\u3a55\u3a42\u3a4f\u3a56\u3a42\u3a57\u3a4a\u3a4c\u3a4d\u3a03\u3a75\u3a46\u3a51\u3a50\u3a4a\u3a4c\u3a4d";
        this.oFb = "\u3a65\u3a4d\u3a60\u3a4b\u3a42\u3a51\u3a57\u3a50\u3a03\u3a73\u3a51\u3a4c\u3a45\u3a46\u3a50\u3a50\u3a4a\u3a4c\u3a4d\u3a42\u3a4f\u3a03\u3a10\u3a0d\u3a13\u3a03\u3a66\u3a55\u3a42\u3a4f\u3a56\u3a42\u3a57\u3a4a\u3a4c\u3a4d\u3a03\u3a75\u3a46\u3a51\u3a50\u3a4a\u3a4c\u3a4d\u3a03\u3a0e\u3a03\u3a4d\u3a4c\u3a57\u3a03\u3a45\u3a4c\u3a51\u3a03\u3a53\u3a56\u3a41\u3a4f\u3a4a\u3a40\u3a03\u3a56\u3a50\u3a46\u3a0d";
        this.pFb = 992607;
        this.qFb = 4470274;
        this.rFb = 2381613;
        this.GFb = new Tp();
        this.HFb = false;
        this.p = 0;
        this.q = 0;
        this.IFb = new DecimalFormat("#,##0.00");
        this.JFb = new DecimalFormat("#,##0.00");
        this.KFb = null;
        this.LFb = null;
        this.MFb = 0;
        this.NFb = 0;
        this.PFb = 0;
        if (n < 1) {
            throw new IllegalArgumentException("MaxPanelCount must be > 0");
        }
        if (yDb == null) {
            throw new IllegalArgumentException("Resource cannot be NULL");
        }
        this.yDb = yDb;
        this.LEb = n;
        this.KEb = n;
        this.MEb = this.b(n);
        this.aa();
        this.o = new Up();
        this.uEb = new Vp[this.KEb];
        this.tEb = new Vp[this.KEb];
        this.vEb = new Vector[this.KEb];
        this.wEb = new Vector[this.KEb];
        this.xEb = new Vector[this.KEb];
        this.yEb = new Vector[this.KEb];
        this.sEb = new Tp[this.KEb];
        this.IEb = new boolean[this.KEb];
        this.JEb = new boolean[this.KEb];
        this.NEb = new Rectangle[this.KEb];
        this.QEb = new boolean[this.KEb];
        for (int i = 0; i < this.KEb; ++i) {
            this.tEb[i] = new Vp(1);
            this.uEb[i] = new Vp(0);
            this.vEb[i] = new Vector();
            this.wEb[i] = new Vector();
            this.xEb[i] = new Vector();
            this.yEb[i] = new Vector();
            (this.sEb[i] = new Tp()).setFont(new Font("SansSerif", 0, 12));
            if (i > 0) {
                this.sEb[i].c(true);
                this.sEb[i].f(true);
            }
            this.IEb[i] = false;
            this.JEb[i] = false;
            this.uEb[i].V = null;
            this.tEb[i].V = null;
            this.NEb[i] = new Rectangle(0, 0, 0, 0);
            if (i == 0) {
                this.QEb[i] = true;
            }
            else {
                this.QEb[i] = false;
            }
        }
        this.ja = Color.lightGray;
        this.EEb = null;
        this.FEb = null;
        this.GEb = null;
        this.V = null;
        this.setBackground(this.ja);
        this.qEb = 2;
        this.MDb = null;
        this.NDb = null;
        this.ODb = null;
        this.zEb = ((rp.QFb == null) ? (rp.QFb = class$("_q")) : rp.QFb);
        this.AEb = null;
        this.BEb = 4;
        this.DEb = true;
        this.REb = true;
        (this.aFb = new Tp()).d(false);
        this.aFb.e(false);
        this.aFb.setColor(Color.red);
        (this.bFb = new Xp()).d(false);
        this.bFb.e(false);
        this.YEb = Color.blue;
        this.ZEb = Color.green;
        this.ba();
        this.addMouseListener(new aq(this));
        this.addMouseMotionListener(new bq(this));
        this.i(true);
        this.v(true);
        this.w(true);
        this.x(true);
        this.y(true);
        this.GFb.e(false);
        this.iFb = a.b(this.mFb);
        this.GFb.d(false);
        this.GFb.setFont(new Font("SansSerif", 1, 11));
        this.GFb.b(this.ja);
        this.GFb.setColor(this.o.x.Ba);
    }
    
    private void ba() {
        this.hFb = new PopupMenu();
        (this.sFb = new Yp(this.yDb._("menuGridVisible"), true)).addItemListener(this);
        this.hFb.add(this.sFb._());
        (this.tFb = new Yp(this.yDb._("menuTitleVisible"), true)).addItemListener(this);
        this.hFb.add(this.tFb._());
        (this.uFb = new Yp(this.yDb._("menuLegendVisible"), true)).addItemListener(this);
        this.hFb.add(this.uFb._());
        (this.vFb = new Yp(this.yDb._("menuCrosshairVisible"), true)).addItemListener(this);
        this.hFb.add(this.vFb._());
        (this.wFb = new Yp(this.yDb._("menuBuySellSignalsVisible"), true)).addItemListener(this);
        this.hFb.add(this.wFb._());
        this.hFb.addSeparator();
        (this.xFb = new Yp(this.yDb._("menuTrendLine"), true)).addItemListener(this);
        this.hFb.add(this.xFb._());
        (this.yFb = new Yp(this.yDb._("menuSupportResistance"), false)).addItemListener(this);
        this.hFb.add(this.yFb._());
        (this.zFb = new Yp(this.yDb._("menuFibonacciRetracements"), false)).addItemListener(this);
        this.hFb.add(this.zFb._());
        (this.AFb = new Yp(this.yDb._("menuRegularRetracements"), false)).addItemListener(this);
        this.hFb.add(this.AFb._());
        this.hFb.addSeparator();
        (this.BFb = new Yp(this.yDb._("menuToolModeAuto"), true)).addItemListener(this);
        this.hFb.add(this.BFb._());
        (this.CFb = new Yp(this.yDb._("menuToolModeDraw"), false)).addItemListener(this);
        this.hFb.add(this.CFb._());
        (this.DFb = new Yp(this.yDb._("menuToolModeMove"), false)).addItemListener(this);
        this.hFb.add(this.DFb._());
        (this.EFb = new Yp(this.yDb._("menuToolModeDelete"), false)).addItemListener(this);
        this.hFb.add(this.EFb._());
        this.hFb.addSeparator();
        if (Yp._a) {
            this.FFb = new MenuItem(this.yDb._("menuDeleteAllTools"));
        }
        else {
            this.FFb = new MenuItem("   " + this.yDb._("menuDeleteAllTools"));
        }
        this.FFb.addActionListener(this);
        this.hFb.add(this.FFb);
        this.add(this.hFb);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem && actionEvent.getSource() == this.FFb) {
            this.ca();
            this.repaint();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.sFb._()) {
            this.x(this.sFb.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.tFb._()) {
            this.v(this.tFb.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.uFb._()) {
            this.w(this.uFb.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.vFb._()) {
            this.i(this.vFb.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.wFb._()) {
            this.y(this.wFb.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.xFb._()) {
            this._((rp.QFb == null) ? (rp.QFb = class$("_q")) : rp.QFb);
        }
        else if (itemEvent.getSource() == this.yFb._()) {
            this._((rp.RFb == null) ? (rp.RFb = class$("cq")) : rp.RFb);
        }
        else if (itemEvent.getSource() == this.zFb._()) {
            this._((rp.SFb == null) ? (rp.SFb = class$("dq")) : rp.SFb);
        }
        else if (itemEvent.getSource() == this.AFb._()) {
            this._((rp.TFb == null) ? (rp.TFb = class$("eq")) : rp.TFb);
        }
        else if (itemEvent.getSource() == this.BFb._()) {
            this.S(4);
        }
        else if (itemEvent.getSource() == this.CFb._()) {
            this.S(1);
        }
        else if (itemEvent.getSource() == this.DFb._()) {
            this.S(2);
        }
        else if (itemEvent.getSource() == this.EFb._()) {
            this.S(3);
        }
    }
    
    public static void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.fillOval(n - n5 / 2, n2 - n5 / 2, n5, n5);
        if (n == n3 && n2 == n4) {
            return;
        }
        if (Math.abs(n3 - n) > Math.abs(n4 - n2)) {
            int n6;
            if (n3 > n) {
                n6 = 1;
            }
            else {
                n6 = -1;
            }
            final int n7 = (n4 - n2) * 10000 / Math.abs(n3 - n);
            int n8 = n2;
            int n9 = n2;
            int n10 = n9 * 10000 + 5000;
            int n11 = n;
            while (true) {
                if (n9 != n8) {
                    graphics.drawOval(n11 - n5 / 2, n8 - n5 / 2, n5, n5);
                    n8 = n9;
                }
                graphics.drawOval(n11 - n5 / 2, n9 - n5 / 2, n5, n5);
                if (n11 == n3) {
                    break;
                }
                n10 += n7;
                n9 = n10 / 10000;
                n11 += n6;
            }
        }
        else {
            int n12;
            if (n4 > n2) {
                n12 = 1;
            }
            else {
                n12 = -1;
            }
            final int n13 = (n3 - n) * 10000 / Math.abs(n4 - n2);
            int n14 = n2;
            int n15 = n;
            int n16 = n;
            int n17 = n16 * 10000 + 5000;
            while (true) {
                if (n16 != n15) {
                    graphics.drawOval(n15 - n5 / 2, n14 - n5 / 2, n5, n5);
                    n15 = n16;
                }
                graphics.drawOval(n16 - n5 / 2, n14 - n5 / 2, n5, n5);
                if (n14 == n4) {
                    break;
                }
                n14 += n12;
                n17 += n13;
                n16 = n17 / 10000;
            }
        }
    }
    
    private double[] b(final int n) {
        final double[] array = new double[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                if (n <= 3) {
                    array[i] = 3.0;
                }
                else {
                    array[i] = 3.6;
                }
            }
            else {
                array[i] = 1.0;
            }
        }
        return array;
    }
    
    public synchronized void T(final int kEb) {
        if (kEb < 1 || kEb > this.LEb) {
            throw new IllegalArgumentException("Panel count must be in range: [1," + this.LEb + "]");
        }
        for (int i = kEb + 1; i < this.LEb; ++i) {
            this.U(i);
            this.N(i);
        }
        this.KEb = kEb;
        this.MEb = this.b(this.KEb);
        this.aa();
    }
    
    public synchronized void z(final boolean fFb) {
        this.fFb = fFb;
    }
    
    private void V(final int n) {
        if (n != 0 && n != 1) {
            throw new IllegalArgumentException("Axis must be: MultiChart.LETFT_AXIS or MultiChart.RIGHT_AXIS");
        }
    }
    
    public synchronized void W(final int n) {
        this.V(n);
        if (n == 0) {
            this.eFb = 0;
        }
        else if (n == 1) {
            this.eFb = 1;
        }
    }
    
    private void aa() {
        double n = 0.0;
        for (int i = 0; i < this.KEb; ++i) {
            n += this.MEb[i];
        }
        if (n == 0.0) {
            n = 1.0;
        }
        for (int j = 0; j < this.KEb; ++j) {
            final double[] mEb = this.MEb;
            final int n2 = j;
            mEb[n2] /= n;
        }
    }
    
    public synchronized Tp _(final int n) {
        return this.sEb[n];
    }
    
    public synchronized Np b() {
        return this.o.a();
    }
    
    public synchronized Np b(final int n) {
        return this.b(n, this.eFb);
    }
    
    public synchronized Np b(final int n, final int n2) {
        this.V(n2);
        if (n2 == 0) {
            return this.uEb[n].a();
        }
        return this.tEb[n].a();
    }
    
    public void b(final int n, final String text) {
        this.sEb[n].setText(text);
    }
    
    public synchronized Color _() {
        return this.ja;
    }
    
    public synchronized void b(final Color ja) {
        this.setBackground(this.ja = ja);
        this.GFb.b(ja);
    }
    
    public synchronized void d(final Color eEb) {
        this.EEb = eEb;
    }
    
    public synchronized void e(final Color fEb) {
        this.FEb = fEb;
    }
    
    public synchronized void f(final Color gEb) {
        this.GEb = gEb;
    }
    
    public synchronized void g(final Color v) {
        this.V = v;
        this.o.V = v;
        for (int i = 0; i < this.H(); ++i) {
            this.uEb[i].V = null;
            this.tEb[i].V = v;
        }
    }
    
    public synchronized void x(final boolean y) {
        this.o.Y = y;
        for (int i = 0; i < this.H(); ++i) {
            this.uEb[i].Y = y;
            this.tEb[i].Y = y;
        }
        this.sFb.setState(this.o.Y);
    }
    
    public synchronized boolean c() {
        return this.o.Y;
    }
    
    public synchronized void h(final Color hEb) {
        this.HEb = hEb;
    }
    
    public synchronized void a(final int n, final fq fq) {
        this.b(n, this.eFb, fq);
    }
    
    public synchronized void b(final int n, final int n2, final fq fq) {
        this.V(n2);
        if (n2 == 0) {
            this.vEb[n].addElement(fq);
        }
        else {
            this.wEb[n].addElement(fq);
        }
    }
    
    public synchronized fq a(final int n, final int n2) {
        return this._(n, this.eFb, n2);
    }
    
    public synchronized fq _(final int n, final int n2, final int n3) {
        this.V(n2);
        if (n2 == 0) {
            return this.vEb[n].elementAt(n3);
        }
        return this.wEb[n].elementAt(n3);
    }
    
    public synchronized void b(final fq fq) {
        for (int i = 0; i < this.i(); ++i) {
            if (this.vEb[i].contains(fq)) {
                this.vEb[i].removeElement(fq);
            }
            if (this.wEb[i].contains(fq)) {
                this.wEb[i].removeElement(fq);
            }
        }
    }
    
    public synchronized void b(final int n, final fq fq) {
        if (this.vEb[n].contains(fq)) {
            this.vEb[n].removeElement(fq);
        }
        if (this.wEb[n].contains(fq)) {
            this.wEb[n].removeElement(fq);
        }
    }
    
    public synchronized void U(final int n) {
        this.vEb[n].removeAllElements();
        this.wEb[n].removeAllElements();
    }
    
    public synchronized void f(final int n, final int n2) {
        this.V(n2);
        if (n2 == 0) {
            this.vEb[n].removeAllElements();
        }
        if (n2 == 1) {
            this.wEb[n].removeAllElements();
        }
    }
    
    public synchronized void N(final int n) {
        this.xEb[n].removeAllElements();
        this.yEb[n].removeAllElements();
    }
    
    public synchronized void ca() {
        for (int i = 0; i < this.H(); ++i) {
            this.N(i);
        }
    }
    
    public synchronized void g(final int n, final int n2) {
        this.V(n2);
        if (n2 == 0) {
            this.xEb[n].removeAllElements();
        }
        if (n2 == 1) {
            this.yEb[n].removeAllElements();
        }
    }
    
    public synchronized int a(final int n) {
        return this.a(n, this.eFb);
    }
    
    public synchronized int a(final int n, final int n2) {
        this.V(n2);
        if (n2 == 0) {
            return this.vEb[n].size();
        }
        return this.wEb[n].size();
    }
    
    public synchronized void i(final Color color) {
        this.ZEb = color;
        for (int i = 0; i < this.H(); ++i) {
            for (int j = 0; j < this.xEb[i].size(); ++j) {
                ((Wp)this.xEb[i].elementAt(j)).setColor(color);
            }
            for (int k = 0; k < this.yEb[i].size(); ++k) {
                ((Wp)this.yEb[i].elementAt(k)).setColor(color);
            }
        }
    }
    
    public synchronized void X(final int fb) {
        this._Fb = fb;
        for (int i = 0; i < this.H(); ++i) {
            for (int j = 0; j < this.xEb[i].size(); ++j) {
                ((Wp)this.xEb[i].elementAt(j)).k(fb);
            }
            for (int k = 0; k < this.yEb[i].size(); ++k) {
                ((Wp)this.yEb[i].elementAt(k)).k(fb);
            }
        }
    }
    
    public synchronized void b(final int n, final Wp wp) {
        this.b(n, this.eFb, wp);
    }
    
    public synchronized void b(final int n, final int n2, final Wp wp) {
        this.V(n2);
        if (n2 == 0) {
            this.xEb[n].addElement(wp);
        }
        if (n2 == 1) {
            this.yEb[n].addElement(wp);
        }
    }
    
    public synchronized Wp _(final int n, final int n2) {
        return this._(n, this.eFb, n2);
    }
    
    public synchronized Wp _(final int n, final int n2, final int n3) {
        this.V(n2);
        if (n2 == 0) {
            return this.xEb[n].elementAt(n3);
        }
        return this.yEb[n].elementAt(n3);
    }
    
    public synchronized void _(final Wp wp) {
        for (int i = 0; i < this.H(); ++i) {
            if (this.xEb[i].contains(wp)) {
                this.xEb[i].removeElement(wp);
            }
            if (this.yEb[i].contains(wp)) {
                this.yEb[i].removeElement(wp);
            }
        }
    }
    
    public synchronized void _(final int n, final Wp wp) {
        if (this.xEb[n].contains(wp)) {
            this.xEb[n].removeElement(wp);
        }
        if (this.yEb[n].contains(wp)) {
            this.yEb[n].removeElement(wp);
        }
    }
    
    public synchronized int b(final int n) {
        return this.b(n, this.eFb);
    }
    
    public synchronized int b(final int n, final int n2) {
        this.V(n2);
        if (n2 == 0) {
            return this.xEb[n].size();
        }
        return this.yEb[n].size();
    }
    
    public synchronized int i() {
        return this.KEb;
    }
    
    public synchronized int H() {
        return this.LEb;
    }
    
    public synchronized void _(final Class zEb) {
        this.zEb = zEb;
        if (zEb == ((rp.QFb == null) ? (rp.QFb = class$("_q")) : rp.QFb)) {
            this.xFb.setState(true);
            this.yFb.setState(false);
            this.zFb.setState(false);
            this.AFb.setState(false);
        }
        if (zEb == ((rp.RFb == null) ? (rp.RFb = class$("cq")) : rp.RFb)) {
            this.xFb.setState(false);
            this.yFb.setState(true);
            this.zFb.setState(false);
            this.AFb.setState(false);
        }
        else if (zEb == ((rp.SFb == null) ? (rp.SFb = class$("dq")) : rp.SFb)) {
            this.xFb.setState(false);
            this.yFb.setState(false);
            this.zFb.setState(true);
            this.AFb.setState(false);
        }
        else if (zEb == ((rp.TFb == null) ? (rp.TFb = class$("eq")) : rp.TFb)) {
            this.xFb.setState(false);
            this.yFb.setState(false);
            this.zFb.setState(false);
            this.AFb.setState(true);
        }
    }
    
    public void da() {
        this.PFb = 0;
        this.repaint();
    }
    
    public void A(final boolean hFb) {
        this.HFb = hFb;
        this.PFb = 0;
    }
    
    public void ea() {
        this.gFb = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void Y(final int n) {
        int y = n;
        if (y == 0) {
            if (this.REb) {
                y += Math.max(rp.OFb, this.aFb.a(this.getGraphics())) + 3;
            }
            else {
                y += rp.OFb + 2;
            }
        }
        else {
            y += 2;
        }
        final int width = this.getSize().width;
        int n2 = this.getSize().height - y - 1;
        if (n2 < 0) {
            n2 = 0;
        }
        for (int i = 0; i < this.KEb; ++i) {
            this.NEb[i].x = 1;
            this.NEb[i].y = y;
            this.NEb[i].width = width;
            this.NEb[i].height = (int)(this.MEb[i] * n2 + 0.5);
            y += this.NEb[i].height;
        }
    }
    
    private double[] e() {
        final int n = this.a(0, 1) + this.a(0, 0);
        final fq[] array = new fq[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.a(0, 1); ++i) {
            if (this._(0, 1, i).b() <= this._(0, 1, i)._()) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = array[n2].b());
                array4[n2] = array[n2]._();
                ++n2;
            }
        }
        for (int j = 0; j < this.a(0, 0); ++j) {
            if (this._(0, 0, j).b() <= this._(0, 0, j)._()) {
                array[n2] = this._(0, 0, j);
                array2[n2] = (array3[n2] = array[n2].b());
                array4[n2] = array[n2]._();
                ++n2;
            }
        }
        int n3 = 0;
        for (int k = 0; k < n2; ++k) {
            final int n4 = array4[k] - array3[k] + 1;
            if (n4 > n3) {
                n3 = n4;
            }
        }
        if (n3 == 0) {
            return null;
        }
        final gq gq = new gq((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double a = array[l].a(array2[l]);
                    if (a < n5) {
                        n5 = a;
                    }
                }
            }
            if (!b) {
                break;
            }
            gq.a(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].a(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return gq._();
    }
    
    public synchronized double[] f() {
        final int n = this.a(0, 1) + this.a(0, 0);
        final fq[] array = new fq[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.a(0, 1); ++i) {
            if (this._(0, 1, i).f() >= 0) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].f();
                ++n2;
            }
        }
        for (int j = 0; j < this.a(0, 0); ++j) {
            if (this._(0, 0, j).f() >= 0) {
                array[n2] = this._(0, 0, j);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].f();
                ++n2;
            }
        }
        int n3 = 0;
        for (int k = 0; k < n2; ++k) {
            final int n4 = array4[k] - array3[k] + 1;
            if (n4 > n3) {
                n3 = n4;
            }
        }
        if (n3 == 0) {
            return null;
        }
        final gq gq = new gq((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double a = array[l].a(array2[l]);
                    if (a < n5) {
                        n5 = a;
                    }
                }
            }
            if (!b) {
                break;
            }
            gq.a(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].a(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return gq._();
    }
    
    public synchronized double[] g() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.a(0, 1); ++i) {
            if (this._(0, 1, i).b() <= this._(0, 1, i)._()) {
                n = Math.min(n, this._(0, 1, i).g());
                n2 = Math.max(n2, this._(0, 1, i).h());
            }
        }
        for (int j = 0; j < this.a(0, 0); ++j) {
            if (this._(0, 0, j).b() <= this._(0, 0, j)._()) {
                n = Math.min(n, this._(0, 0, j).g());
                n2 = Math.max(n2, this._(0, 0, j).h());
            }
        }
        if (n == Double.POSITIVE_INFINITY || n == Double.NEGATIVE_INFINITY || n2 == Double.POSITIVE_INFINITY || n2 == Double.NEGATIVE_INFINITY) {
            return null;
        }
        return new double[] { n, n2 };
    }
    
    private int d(final Graphics graphics) {
        int n = 0;
        for (int i = 0; i < this.a(0, 0); ++i) {
            final fq _ = this._(0, 0, i);
            if (_.getName() != null && _.getName().length() > 0) {
                ++n;
            }
        }
        for (int j = 0; j < this.a(0, 1); ++j) {
            final fq _2 = this._(0, 1, j);
            if (_2.getName() != null && _2.getName().length() > 0) {
                ++n;
            }
        }
        if (n == 0) {
            return 0;
        }
        final int[] array = new int[n];
        final String[] array2 = new String[n];
        final Color[] array3 = new Color[n];
        int n2 = 0;
        for (int k = 0; k < this.a(0, 0); ++k) {
            final fq _3 = this._(0, 0, k);
            if (_3.getName() != null && _3.getName().length() > 0) {
                if (_3 instanceof hq) {
                    array[n2] = ((hq)_3).e();
                }
                else if (_3 instanceof iq) {
                    array[n2] = ((iq)_3).e();
                }
                else {
                    array[n2] = 0;
                }
                array2[n2] = _3.getName();
                array3[n2] = _3.Ba;
                ++n2;
            }
        }
        for (int l = 0; l < this.a(0, 1); ++l) {
            final fq _4 = this._(0, 1, l);
            if (_4.getName() != null && _4.getName().length() > 0) {
                if (_4 instanceof hq) {
                    array[n2] = ((hq)_4).e();
                }
                else if (_4 instanceof iq) {
                    array[n2] = ((iq)_4).e();
                }
                else {
                    array[n2] = 0;
                }
                array2[n2] = _4.getName();
                array3[n2] = _4.Ba;
                ++n2;
            }
        }
        this.bFb.G(0);
        this.bFb.F(3);
        final int n3 = 5;
        final int y = this.NEb[0].y;
        int n4 = n3;
        int n5 = y;
        int max = 0;
        int max2 = 0;
        int n6 = 0;
        for (int n7 = 0; n7 < array2.length; ++n7) {
            this.bFb.setText(array2[n7]);
            this.bFb.k(array[n7]);
            this.bFb.setColor(array3[n7]);
            final int _5 = this.bFb._(graphics);
            if (n4 > n3 && n4 + _5 >= this.NEb[0].width - 5) {
                n4 = n3;
                n5 += max;
            }
            max = Math.max(max, this.bFb.a(graphics));
            n4 += this.bFb._(graphics) + 2;
            max2 = Math.max(max2, n4);
            n6 = n5 + max;
        }
        if (this.GEb != null) {
            graphics.setColor(this.GEb);
        }
        else {
            graphics.setColor(this.ja);
        }
        graphics.fillRect(n3, y, max2 - n3, n6 - y);
        graphics.setColor(this.o.Ba);
        graphics.drawRect(n3, y, max2 - n3, n6 - y);
        int n8 = n3;
        int n9 = y;
        int max3 = 0;
        for (int n10 = 0; n10 < array2.length; ++n10) {
            this.bFb.setText(array2[n10]);
            this.bFb.k(array[n10]);
            this.bFb.setColor(array3[n10]);
            final int _6 = this.bFb._(graphics);
            if (n8 > n3 && n8 + _6 >= this.NEb[0].width - 5) {
                n8 = n3;
                n9 += max3;
            }
            this.bFb._(graphics, n8, n9);
            max3 = Math.max(max3, this.bFb.a(graphics));
            n8 += this.bFb._(graphics) + 2;
        }
        return n6;
    }
    
    private synchronized void g(final Graphics graphics) {
        if (this.o.a() instanceof jq && this.gFb) {
            this.fa();
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setClip(0, 0, width, height);
        this.h(graphics);
        if (this.EEb != null) {
            graphics.setColor(this.EEb);
            graphics.drawLine(0, 0, width - 1, 0);
            graphics.drawLine(0, 0, 0, height - 1);
        }
        if (this.FEb != null) {
            graphics.setColor(this.FEb);
            graphics.drawLine(0, height - 1, width - 1, height - 1);
            graphics.drawLine(width - 1, 0, width - 1, height - 1);
        }
        this.Y(0);
        if (this.VEb) {
            this.Y(this.d(graphics));
        }
        this.ga();
        this.i(graphics);
        for (int i = 0; i < this.KEb; ++i) {
            this.b(i, graphics);
            this._(i, graphics);
            this.a(i, graphics);
            if (this.AEb != null) {
                this.AEb._(graphics, this.NEb[this.CEb], this.ja, this.b(), this.b(this.CEb));
                this.AEb.a(this.p, this.q, this.b(), this.b(this.CEb));
            }
            if (this.DEb && this.o.a() instanceof jq && ((jq)this.o.a()).d() > 1) {
                this.l(i, graphics);
            }
        }
        this.ha();
    }
    
    public synchronized void fa() {
        final Np a = this.o.a();
        if (a instanceof jq) {
            ((jq)a)._(this.e());
            this.gFb = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.HFb) {
            if (this.NDb != null && this.ODb != null) {
                graphics.drawImage(this.ODb, 0, 0, this);
                if (this.PFb > 0) {
                    this.b(graphics, this.PFb);
                }
            }
            else {
                graphics.setColor(this.ja);
                graphics.setClip(0, 0, size.width, size.height);
                this.h(graphics);
                if (this.EEb != null) {
                    graphics.setColor(this.EEb);
                    graphics.drawLine(0, 0, size.width - 1, 0);
                    graphics.drawLine(0, 0, 0, size.height - 1);
                }
                if (this.FEb != null) {
                    graphics.setColor(this.FEb);
                    graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
                    graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                }
                if (this.PFb > 0) {
                    this.b(graphics, this.PFb);
                }
            }
        }
        else if (size.width > 0 && size.height > 0) {
            if (this.ODb == null || this.NDb == null || this.MDb == null || size.width != this.MDb.width || size.height != this.MDb.height) {
                if (this.NDb != null) {
                    this.NDb.dispose();
                }
                if (this.ODb != null) {
                    this.ODb.flush();
                }
                this.ODb = this.createImage(size.width, size.height);
                this.NDb = this.ODb.getGraphics();
                this.MDb = size;
            }
            this.g(this.NDb);
            graphics.drawImage(this.ODb, 0, 0, this);
            if (this.PFb > 0) {
                this.b(graphics, this.PFb);
            }
        }
    }
    
    private void _(final int n, final Graphics graphics) {
        final int y = this.NEb[n].y;
        final int width = this.NEb[n].width;
        final int height = this.NEb[n].height;
        final int _ = this.sEb[n]._(graphics);
        final int a = this.sEb[n].a(graphics);
        if (this.sEb[n].a()) {
            this.rEb = 0;
        }
        else {
            this.rEb = 1;
        }
        graphics.setClip(this.NEb[n]);
        if ((this.UEb || this.a(0) == 0) && !this.sEb[n].isEmpty()) {
            final String text = this.sEb[n].getText();
            if (this.a(n, 1) >= 0) {
                this.sEb[n].setText(text + this.a(n));
            }
            switch (this.qEb) {
                case 2: {
                    this.sEb[n].G(0);
                    this.sEb[n].F(3);
                    if (this.a(0, 0) == 0 && this.a(0, 1) == 0) {
                        final int n2 = (width - _) / 2;
                        if (this.GEb != null) {
                            graphics.setColor(this.GEb);
                            graphics.fillRect(n2, y, _ + 2 * this.rEb, a + 2 * this.rEb);
                        }
                        graphics.setColor(this.o.Ba);
                        graphics.drawRect(n2, y, _ + 2 * this.rEb, a + 2 * this.rEb);
                        this.sEb[n].d(n2 + this.rEb, y + this.rEb);
                        break;
                    }
                    this.sEb[n].d(this.uEb[n].A() + this.rEb, y + this.rEb);
                    break;
                }
            }
            this.sEb[n].b(graphics);
            this.sEb[n].setText(text);
        }
    }
    
    private void h(final Graphics graphics) {
        graphics.setColor(this.ja);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    
    private void ga() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.KEb; ++i) {
            double n4;
            double n3 = n4 = Double.POSITIVE_INFINITY;
            double n6;
            double n5 = n6 = Double.NEGATIVE_INFINITY;
            if (this.vEb[i].size() != 0 || this.wEb[i].size() != 0) {
                if (this.o._()) {
                    n = Math.min(n, this.b(i));
                    n2 = Math.max(n2, this.e(i));
                }
                else {
                    n = this.o.i();
                    n2 = this.o.j();
                }
                if (!this.uEb[i]._()) {
                    n4 = this.uEb[i].i();
                    n6 = this.uEb[i].j();
                }
                if (!this.tEb[i]._()) {
                    n3 = this.tEb[i].i();
                    n5 = this.tEb[i].j();
                }
                if (this.vEb[i].size() > 0 && this.uEb[i]._()) {
                    n4 = Math.min(n4, this._(i, 0));
                    n6 = Math.max(n6, this.e(i, 0));
                    this.uEb[i]._(n4, n6);
                }
                if (this.wEb[i].size() > 0 && this.tEb[i]._()) {
                    n3 = Math.min(n3, this._(i, 1));
                    n5 = Math.max(n5, this.e(i, 1));
                    this.tEb[i]._(n3, n5);
                }
                if (this.fFb && i == 0 && this.vEb[i].size() > 0 && this.wEb[i].size() > 0) {
                    final double min = Math.min(n4, n3);
                    final double max = Math.max(n6, n5);
                    this.uEb[i]._(min, max);
                    this.tEb[i]._(min, max);
                }
                else if (n4 > n6 || n3 > n5) {
                    if (n4 > n6) {
                        this.uEb[i]._(1.0, 10.0);
                    }
                    if (n3 > n5) {
                        this.tEb[i]._(1.0, 10.0);
                    }
                }
            }
        }
        if (n > n2) {
            n = 1.0;
            n2 = 100.0;
        }
        this.o._(n, n2);
    }
    
    private void h(final int n, final int n2) {
        for (int i = 0; i < this.H(); ++i) {
            for (int j = 0; j < this.xEb[i].size(); ++j) {
                final Wp wp = this.xEb[i].elementAt(j);
                if (wp instanceof cq) {
                    ((cq)wp).a(n, n2);
                }
            }
            for (int k = 0; k < this.yEb[i].size(); ++k) {
                final Wp wp2 = this.yEb[i].elementAt(k);
                if (wp2 instanceof cq) {
                    ((cq)wp2).a(n, n2);
                }
            }
        }
    }
    
    private void b(final int n, final Graphics graphics) {
        graphics.setClip(this.NEb[n]);
        this.IEb[n] = (this.JEb[n] = false);
        if (this.vEb[n].size() == 0 && this.wEb[n].size() == 0) {
            return;
        }
        this.m(n, graphics);
        this.o.W = this.tEb[n].B() + 1;
        this.o.X = this.tEb[n].C() - 1;
        this.uEb[n].W = this.o.j() + 1;
        this.uEb[n].X = this.o.u() - 1;
        this.tEb[n].W = this.o.j() + 1;
        this.tEb[n].X = this.o.u() - 1;
        this.h(this.o.j() + 1, this.o.u() - 1);
        graphics.setColor(this.o.Ba);
        graphics.drawRect(this.uEb[n].A(), this.uEb[n].B(), this.tEb[n].A() - this.uEb[n].A(), this.tEb[n].C() - this.uEb[n].B());
        if (this.GEb != null) {
            graphics.setColor(this.GEb);
            graphics.fillRect(this.uEb[n].A() + 1, this.uEb[n].B() + 1, this.tEb[n].A() - this.uEb[n].A() - 1, this.tEb[n].C() - this.uEb[n].B() - 1);
        }
        this.tEb[n].A(0);
        this.uEb[n].A(0);
        if (this.UEb && this._(n).b()) {
            int n2;
            if (this.sEb[n].a()) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            final int n3 = n2 + this._(n).a(graphics);
            this.tEb[n].A(n3);
            this.uEb[n].A(n3);
        }
        if (this.vEb[n].size() > 0) {
            this.IEb[n] = this.uEb[n].a(graphics);
        }
        if (this.wEb[n].size() > 0) {
            this.JEb[n] = this.tEb[n].a(graphics);
        }
        final boolean a = this.o.a(graphics);
        this.IEb[n] = (this.IEb[n] && a);
        this.JEb[n] = (this.JEb[n] && a);
    }
    
    private void i(final Graphics graphics) {
        this.PEb = 5;
        for (int i = 0; i < this.KEb; ++i) {
            this.uEb[i].setBounds(this.NEb[i]);
            if (this.a(i, 0) > 0) {
                this.PEb = Math.max(this.PEb, this.uEb[i].g(graphics));
            }
        }
        this.OEb = this.getSize().width - 5;
        for (int j = 0; j < this.KEb; ++j) {
            this.tEb[j].setBounds(this.NEb[j]);
            if (this.a(j, 1) > 0) {
                this.OEb = Math.min(this.OEb, this.tEb[j].g(graphics));
            }
        }
    }
    
    private void m(final int n, final Graphics graphics) {
        this.o.m(this.QEb[n]);
        this.o.n(this.QEb[n]);
        this.o.setBounds(this.NEb[n]);
        this.uEb[n].setBounds(this.NEb[n]);
        this.tEb[n].setBounds(this.NEb[n]);
        switch (this.o.v()) {
            case 0: {
                this.uEb[n].C(this.o.m(graphics));
                this.tEb[n].C(this.o.m(graphics));
                this.uEb[n].D(this.NEb[n].y + this.NEb[n].height - 1);
                this.tEb[n].D(this.NEb[n].y + this.NEb[n].height - 1);
                break;
            }
            case 1: {
                this.uEb[n].D(this.o.m(graphics));
                this.tEb[n].D(this.o.m(graphics));
                this.uEb[n].C(this.NEb[n].y);
                this.tEb[n].C(this.NEb[n].y);
                break;
            }
        }
        this.o.H(this.PEb);
        this.o.I(this.OEb);
        this.uEb[n].E(this.PEb);
        this.tEb[n].E(this.OEb);
    }
    
    private double e(final int n) {
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.vEb[n].size(); ++i) {
            n2 = Math.max(n2, ((fq)this.vEb[n].elementAt(i)).b());
        }
        for (int j = 0; j < this.wEb[n].size(); ++j) {
            n2 = Math.max(n2, ((fq)this.wEb[n].elementAt(j)).b());
        }
        return n2;
    }
    
    private double b(final int n) {
        double n2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.vEb[n].size(); ++i) {
            n2 = Math.min(n2, ((fq)this.vEb[n].elementAt(i))._());
        }
        for (int j = 0; j < this.wEb[n].size(); ++j) {
            n2 = Math.min(n2, ((fq)this.wEb[n].elementAt(j))._());
        }
        return n2;
    }
    
    private double e(final int n, final int n2) {
        double n3 = Double.NEGATIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.vEb[n].size(); ++i) {
                n3 = Math.max(n3, ((fq)this.vEb[n].elementAt(i)).a());
            }
        }
        else {
            for (int j = 0; j < this.wEb[n].size(); ++j) {
                n3 = Math.max(n3, ((fq)this.wEb[n].elementAt(j)).a());
            }
        }
        return n3;
    }
    
    private double _(final int n, final int n2) {
        double n3 = Double.POSITIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.vEb[n].size(); ++i) {
                n3 = Math.min(n3, ((fq)this.vEb[n].elementAt(i)).l());
            }
        }
        else {
            for (int j = 0; j < this.wEb[n].size(); ++j) {
                n3 = Math.min(n3, ((fq)this.wEb[n].elementAt(j)).l());
            }
        }
        return n3;
    }
    
    private void a(final int n, final Graphics graphics) {
        if (!this.IEb[n] && !this.JEb[n]) {
            return;
        }
        final int j = this.o.j();
        final int u = this.o.u();
        if (n == 1) {
            if (this.JEb[n]) {
                final int b = this.tEb[n].B();
                graphics.setClip(j + 1, b + 1, u - j, this.tEb[n].C() - b);
                for (int i = 0; i < this.wEb[n].size(); ++i) {
                    final fq fq = this.wEb[n].elementAt(i);
                    if (!(fq instanceof kq) || (fq instanceof kq && this.TEb)) {
                        fq.b(graphics, this.b(), this.b(n, 1));
                    }
                }
            }
            if (this.IEb[n]) {
                final int b2 = this.uEb[n].B();
                graphics.setClip(j + 1, b2 + 1, u - j, this.uEb[n].C() - b2);
                for (int k = 0; k < this.vEb[n].size(); ++k) {
                    final fq fq2 = this.vEb[n].elementAt(k);
                    if (!(fq2 instanceof kq) || (fq2 instanceof kq && this.TEb)) {
                        fq2.b(graphics, this.b(), this.b(n, 0));
                    }
                }
            }
        }
        else {
            if (this.IEb[n]) {
                final int b3 = this.uEb[n].B();
                graphics.setClip(j + 1, b3 + 1, u - j, this.uEb[n].C() - b3);
                for (int l = 0; l < this.vEb[n].size(); ++l) {
                    final fq fq3 = this.vEb[n].elementAt(l);
                    if (!(fq3 instanceof kq) || (fq3 instanceof kq && this.TEb)) {
                        fq3.b(graphics, this.b(), this.b(n, 0));
                    }
                }
            }
            if (this.JEb[n]) {
                final int b4 = this.tEb[n].B();
                graphics.setClip(j + 1, b4 + 1, u - j, this.tEb[n].C() - b4);
                for (int n2 = 0; n2 < this.wEb[n].size(); ++n2) {
                    final fq fq4 = this.wEb[n].elementAt(n2);
                    if (!(fq4 instanceof kq) || (fq4 instanceof kq && this.TEb)) {
                        fq4.b(graphics, this.b(), this.b(n, 1));
                    }
                }
            }
        }
    }
    
    private void l(final int n, final Graphics graphics) {
        if (!this.IEb[n] && !this.JEb[n]) {
            return;
        }
        graphics.setClip(this.NEb[n]);
        if (this.IEb[n]) {
            for (int i = 0; i < this.xEb[n].size(); ++i) {
                ((Wp)this.xEb[n].elementAt(i)).a(graphics, this.NEb[n], this.ja, this.b(), this.b(n, 0));
            }
        }
        if (this.JEb[n]) {
            for (int j = 0; j < this.yEb[n].size(); ++j) {
                ((Wp)this.yEb[n].elementAt(j)).a(graphics, this.NEb[n], this.ja, this.b(), this.b(n, 1));
            }
        }
    }
    
    public synchronized void n(final double n, final double n2) {
        for (int i = 0; i < this.KEb; ++i) {
            for (int j = 0; j < this.vEb[i].size(); ++j) {
                ((fq)this.vEb[i].elementAt(j)).l(n, n2);
            }
            for (int k = 0; k < this.wEb[i].size(); ++k) {
                ((fq)this.wEb[i].elementAt(k)).l(n, n2);
            }
        }
    }
    
    public synchronized void B(final boolean b) {
        for (int i = 0; i < this.KEb; ++i) {
            for (int j = 0; j < this.vEb[i].size(); ++j) {
                ((fq)this.vEb[i].elementAt(j))._(b);
            }
            for (int k = 0; k < this.wEb[i].size(); ++k) {
                ((fq)this.wEb[i].elementAt(k))._(b);
            }
        }
    }
    
    private void ia() {
        switch (this.BEb) {
            case 4: {
                this.setCursor(Cursor.getPredefinedCursor(1));
                break;
            }
            case 1: {
                this.setCursor(Cursor.getPredefinedCursor(1));
                break;
            }
            case 2: {
                this.setCursor(Cursor.getPredefinedCursor(13));
                break;
            }
            case 3: {
                this.setCursor(Cursor.getPredefinedCursor(12));
                break;
            }
        }
    }
    
    public void S(final int bEb) {
        switch (this.BEb = bEb) {
            case 4: {
                this.BFb.setState(true);
                this.CFb.setState(false);
                this.DFb.setState(false);
                this.EFb.setState(false);
                break;
            }
            case 1: {
                this.BFb.setState(false);
                this.CFb.setState(true);
                this.DFb.setState(false);
                this.EFb.setState(false);
                break;
            }
            case 2: {
                this.BFb.setState(false);
                this.CFb.setState(false);
                this.DFb.setState(true);
                this.EFb.setState(false);
                break;
            }
            case 3: {
                this.BFb.setState(false);
                this.CFb.setState(false);
                this.DFb.setState(false);
                this.EFb.setState(true);
                break;
            }
        }
        this.ia();
    }
    
    private synchronized void b(final MouseEvent mouseEvent) {
        if (this.hFb != null) {
            this.hFb.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private int f(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < this.i(); ++i) {
            if (n2 >= this.NEb[i].y && n2 <= this.NEb[i].y + this.NEb[i].height) {
                n3 = i;
            }
        }
        if (n3 == -1 || this.a(n3) == 0) {
            return -1;
        }
        return n3;
    }
    
    public synchronized void C(final boolean rEb) {
        this.REb = rEb;
    }
    
    public synchronized void i(final boolean sEb) {
        this.SEb = sEb;
        this.vFb.setState(this.SEb);
    }
    
    public synchronized boolean d() {
        return this.SEb;
    }
    
    public synchronized void y(final boolean tEb) {
        this.TEb = tEb;
        this.wFb.setState(this.TEb);
    }
    
    public synchronized boolean e() {
        return this.TEb;
    }
    
    public synchronized void v(final boolean uEb) {
        this.UEb = uEb;
        this.tFb.setState(this.UEb);
    }
    
    public synchronized void w(final boolean vEb) {
        this.VEb = vEb;
        this.uFb.setState(this.VEb);
    }
    
    public synchronized boolean f() {
        return this.UEb;
    }
    
    public synchronized boolean g() {
        return this.VEb;
    }
    
    public synchronized void j(final Color yEb) {
        this.YEb = yEb;
    }
    
    private void i(final int n, final int xEb) {
        final int y = this.NEb[0].y;
        if (xEb < y) {
            return;
        }
        final int wEb = (int)this.b().b(this.a(0, this.a(0) - 1).a(n));
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.GEb != null) {
            graphics.setXORMode(this.GEb);
        }
        else {
            graphics.setXORMode(this.ja);
        }
        graphics.setColor(this.YEb);
        graphics.drawLine(wEb, y, wEb, this.getSize().height - 2);
        graphics.drawLine(1, xEb, this.getSize().width - 2, xEb);
        graphics.setPaintMode();
        this.WEb = wEb;
        this.XEb = xEb;
    }
    
    private void ha() {
        final int y = this.NEb[0].y;
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.GEb != null) {
            graphics.setXORMode(this.GEb);
        }
        else {
            graphics.setXORMode(this.ja);
        }
        graphics.setColor(this.YEb);
        if (this.WEb > 0 && this.XEb > 0) {
            graphics.drawLine(this.WEb, y, this.WEb, this.getSize().height - 2);
            graphics.drawLine(1, this.XEb, this.getSize().width - 2, this.XEb);
        }
        final int n = -1;
        this.XEb = n;
        this.WEb = n;
        graphics.setPaintMode();
    }
    
    private void o() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.HFb) {
            return;
        }
        graphics.setColor(this.ja);
        graphics.fillRect(2, 2, this.getSize().width - 4, this.aFb.a(graphics) + 1);
    }
    
    private Vp _(final int n) {
        if (this.eFb == 0) {
            return this.uEb[n];
        }
        return this.tEb[n];
    }
    
    private String _(final int n, final double n2) {
        if (n > 0) {
            return this._(n).a(n2);
        }
        final int y = this.tEb[n].y();
        int n3;
        if (n2 >= 1.0) {
            n3 = 0;
        }
        else {
            n3 = (int)Math.ceil(Math.abs(lq.a(n2)));
        }
        final int n4 = (int)Math.ceil(lq.a(n2));
        if (y > 0 && y > n3) {
            n3 = y;
        }
        this.IFb.setMaximumFractionDigits(n3);
        this.IFb.setMinimumFractionDigits(n3);
        this.IFb.setMaximumIntegerDigits(n4 + 1);
        this.IFb.setMinimumIntegerDigits(1);
        this.IFb.setGroupingSize(3);
        this.IFb.setGroupingUsed(true);
        return this.IFb.format(n2);
    }
    
    private int _(final double n, final double n2, final int n3) {
        if (this.HFb) {
            return -1;
        }
        final StringBuffer sb = new StringBuffer();
        int b = -1;
        fq a = null;
        if (this.a(0) > 0) {
            a = this.a(0, this.a(0) - 1);
            b = a.b(n);
        }
        if (b < 0) {
            return b;
        }
        sb.append("Y: " + this._(n3, n2));
        final String a2 = a.a();
        if (a2 != null) {
            if (a2.length() > 0) {
                sb.append(" |" + a2 + ": " + this.o.d(a.a(b)));
            }
            else {
                sb.append(" |" + a2 + this.o.d(a.a(b)));
            }
        }
        for (int i = 0; i < this.i(); ++i) {
            for (int j = this.a(i) - 1; j >= 0; --j) {
                final fq a3 = this.a(i, j);
                for (int k = 0; k < a3.h(); ++k) {
                    final String b2 = a3.b(k);
                    if (b2 != null) {
                        if (b2.length() > 0) {
                            sb.append(" |" + b2 + ": " + this._(i, a3.b(b, k)));
                        }
                        else {
                            sb.append(" |" + b2 + this._(i, a3.b(b, k)));
                        }
                    }
                }
                if (i == 0 && j == this.a(i) - 1 && b > 0) {
                    final double b3 = a3.b(b - 1, a3.h() - 1);
                    if (b3 != 0.0) {
                        final double n4 = 100.0 * (a3.b(b, a3.h() - 1) - b3) / b3;
                        sb.append(" (");
                        if (n4 > 0.0) {
                            sb.append("+");
                        }
                        final String _ = this._(i, 0.0);
                        int n5 = 1;
                        if (_.length() > 4) {
                            n5 = 2;
                        }
                        this.JFb.setMaximumFractionDigits(Math.min(n5, 2));
                        sb.append(this.JFb.format(n4));
                        sb.append("%)");
                    }
                }
            }
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return b;
        }
        this.aFb.setText(sb.toString());
        graphics.setClip(2, 2, this.getSize().width - 5, this.aFb.a(graphics) + 1);
        this.aFb._(graphics, 4, 2);
        return b;
    }
    
    private synchronized void b(final Graphics graphics, final int n) {
        if (!this.isShowing()) {
            return;
        }
        int nFb = rp.OFb;
        if (this.REb) {
            nFb = Math.max(this.aFb.a(graphics), nFb);
        }
        final int mFb = this.getSize().width - 11;
        final int n2 = 5;
        final int n3 = 2;
        if (mFb < 1) {
            return;
        }
        if (graphics == null) {
            return;
        }
        if (this.KFb == null || this.LFb == null || mFb != this.MFb || nFb != this.NFb) {
            if (this.LFb != null) {
                this.LFb.dispose();
            }
            this.NFb = nFb;
            this.MFb = mFb;
            if (this.KFb != null) {
                this.KFb.flush();
            }
            this.KFb = this.createImage(this.MFb, this.NFb);
            this.LFb = this.KFb.getGraphics();
        }
        if (this.LFb == null) {
            return;
        }
        final int min = Math.min(this.MFb, (int)(this.MFb * (n / 100.0)));
        this.LFb.setColor(this.ja);
        this.LFb.fillRect(0, 0, this.MFb, this.NFb);
        this.LFb.setColor(this.HEb);
        this.LFb.fillRect(0, (this.NFb - rp.OFb) / 2, min, rp.OFb);
        graphics.drawImage(this.KFb, n2, n3, this);
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final int intValue = (int)o;
        this.b(graphics, intValue);
        this.PFb = intValue;
    }
    
    private String a(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append(" [");
        int f = -1;
        if (this.a(n) > 0) {
            f = this.a(n, this.a(n) - 1).f();
        }
        if (f < 0) {
            return "";
        }
        for (int i = this.a(n) - 1; i >= 0; --i) {
            final fq a = this.a(n, i);
            final int f2 = a.f();
            for (int j = 0; j < a.h(); ++j) {
                final String b = a.b(j);
                if (b != null) {
                    if (n == 0) {
                        if (b.length() > 0) {
                            String s = "";
                            if (j > 0) {
                                s = "  ";
                            }
                            sb.append(s + b + ": " + this._(n, a.b(f2, j)));
                        }
                        else {
                            sb.append("  " + b + this._(n, a.b(f2, j)));
                        }
                    }
                    else if (b.length() > 0) {
                        sb.append(this._(n, a.b(f2, j)));
                    }
                    else {
                        sb.append(this._(n, a.b(f2, j)));
                    }
                }
            }
        }
        sb.append("]");
        if (this.a(n) > 0) {
            final fq a2 = this.a(n, this.a(n) - 1);
            final int f3 = a2.f();
            if (n == 0 && f3 > 0) {
                final double b2 = a2.b(f3 - 1, a2.h() - 1);
                if (b2 != 0.0) {
                    final double n2 = 100.0 * (a2.b(f3, a2.h() - 1) - b2) / b2;
                    sb.append(" (");
                    if (n2 > 0.0) {
                        sb.append("+");
                    }
                    final String _ = this._(n, 0.0);
                    int maximumFractionDigits = 1;
                    if (_.length() > 4) {
                        maximumFractionDigits = 2;
                    }
                    this.JFb.setMaximumFractionDigits(maximumFractionDigits);
                    sb.append(this.JFb.format(n2));
                    sb.append("%)");
                }
            }
        }
        return sb.toString();
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static void b(final rp rp) {
        rp.ia();
    }
    
    static void a(final rp rp) {
        rp.ha();
    }
    
    static void _(final rp rp) {
        rp.o();
    }
    
    static void _(final rp rp, final MouseEvent mouseEvent) {
        rp.b(mouseEvent);
    }
    
    static boolean b(final rp rp) {
        return rp.HFb;
    }
    
    static boolean _(final rp rp) {
        return rp.gFb;
    }
    
    static int b(final rp rp) {
        return rp.BEb;
    }
    
    static int b(final rp rp, final int cEb) {
        return rp.CEb = cEb;
    }
    
    static Rectangle[] _(final rp rp) {
        return rp.NEb;
    }
    
    static int a(final rp rp) {
        return rp.CEb;
    }
    
    static Class b(final rp rp) {
        return rp.zEb;
    }
    
    static Wp b(final rp rp, final Wp aEb) {
        return rp.AEb = aEb;
    }
    
    static Color b(final rp rp) {
        return rp.ZEb;
    }
    
    static Wp a(final rp rp) {
        return rp.AEb;
    }
    
    static int _(final rp rp) {
        return rp._Fb;
    }
    
    static Color a(final rp rp) {
        return rp.ja;
    }
    
    static int _(final rp rp, final int n, final int n2) {
        return rp.f(n, n2);
    }
    
    static boolean a(final rp rp) {
        return rp.REb;
    }
    
    static int _(final rp rp, final double n, final double n2, final int n3) {
        return rp._(n, n2, n3);
    }
    
    static boolean f(final rp rp) {
        return rp.SEb;
    }
    
    static void a(final rp rp, final int n, final int n2) {
        rp.i(n, n2);
    }
    
    static {
        rp.OFb = 6;
    }
}
