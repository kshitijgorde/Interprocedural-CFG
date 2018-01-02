import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Observable;
import java.awt.MenuComponent;
import java.awt.Container;
import java.awt.Frame;
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

public class continue extends Canvas implements Observer, ItemListener, ActionListener
{
    public static final int xoa = 2;
    public static final int yoa = 0;
    public static final int zoa = 1;
    public static final int Aoa = 2;
    public static final int Boa = 3;
    public static final int Coa = 4;
    public static final int Doa = 5;
    public static final int Eoa = 6;
    private int Foa;
    private int Goa;
    private goto[] Hoa;
    public final rka;
    public for[] roa;
    public for[] soa;
    private Vector[] Ioa;
    private Vector[] Joa;
    private Vector[] Koa;
    private Vector[] Loa;
    private Class Moa;
    private UserDrawTool Noa;
    private int Ooa;
    private int Poa;
    private boolean Qoa;
    private Color pma;
    private Color rma;
    private Color Roa;
    private Color Soa;
    private Color Toa;
    private Color Uma;
    private Color Uoa;
    private Dimension Gja;
    private Graphics Hja;
    private Image Ija;
    private boolean[] Voa;
    private boolean[] Woa;
    private int Xoa;
    private int Yoa;
    private double[] Zoa;
    private Rectangle[] _pa;
    private int apa;
    private int bpa;
    private boolean[] cpa;
    private boolean dpa;
    private boolean epa;
    private boolean fpa;
    private boolean gpa;
    private boolean hpa;
    private boolean ipa;
    private int jpa;
    private int kpa;
    private Color lpa;
    private Color mpa;
    private int npa;
    public goto uoa;
    public if voa;
    public static final int opa = 0;
    public static final int ppa = 1;
    private int qpa;
    private boolean rpa;
    private boolean spa;
    private PopupMenu tpa;
    private o Ca;
    k upa;
    k vpa;
    k wpa;
    k xpa;
    k ypa;
    k zpa;
    k Apa;
    k Bpa;
    k Cpa;
    k Dpa;
    k Epa;
    k Fpa;
    k Gpa;
    k Hpa;
    k Ipa;
    k Jpa;
    k Kpa;
    MenuItem Lpa;
    MenuItem Mpa;
    private goto Npa;
    private int Opa;
    private boolean Ppa;
    private boolean Qpa;
    private String Rpa;
    private String Spa;
    private String Tpa;
    private String Upa;
    private final byte[] Vpa;
    goto woa;
    goto Wpa;
    private boolean Xpa;
    private int nka;
    private int oka;
    private int pka;
    private int qka;
    private boolean Ypa;
    int ska;
    int tka;
    private DecimalFormat Zpa;
    private DecimalFormat _qa;
    private Image aqa;
    private Graphics bqa;
    private int cqa;
    private int dqa;
    private static int eqa;
    private int gb;
    static Class fqa;
    static Class gqa;
    static Class hqa;
    static Class iqa;
    static Class jqa;
    static Class kqa;
    static Class lqa;
    static Class e;
    static Class mqa;
    
    public continue(final int n, final o ca) {
        this.Goa = 0;
        this.Uoa = Color.red;
        this.jpa = -1;
        this.kpa = -1;
        this.npa = 1;
        this.qpa = 1;
        this.rpa = true;
        this.spa = true;
        this.Npa = new goto();
        this.Opa = Integer.MAX_VALUE;
        this.Ppa = true;
        this.Qpa = false;
        this.Rpa = null;
        this.Spa = null;
        this.Tpa = null;
        this.Upa = null;
        this.Vpa = new byte[] { 0, 0, 0, 0, 0, 0, 115, -53, 115, -50, 72, 44, 42, 41, 86, -48, 85, 40, -51, -53, -55, 76, 78, -51, 43, 78, 77, 81, -48, 40, 41, -54, 76, -52, -47, 84, 40, 75, 45, 42, -50, -52, -49, 3, 0, -80, 99, 127, 57, 37, 0, 0, 0 };
        this.woa = new goto();
        this.Wpa = new goto();
        this.Xpa = false;
        this.nka = 0;
        this.oka = 0;
        this.pka = 0;
        this.qka = 0;
        this.Ypa = false;
        this.ska = 0;
        this.tka = 0;
        this.Zpa = new DecimalFormat("#,##0.00");
        this._qa = new DecimalFormat("#,##0.00");
        this.aqa = null;
        this.bqa = null;
        this.cqa = 0;
        this.dqa = 0;
        this.gb = 0;
        if (n < 1) {
            throw new IllegalArgumentException("MaxPanelCount must be > 0");
        }
        if (ca == null) {
            throw new IllegalArgumentException("Resource cannot be NULL");
        }
        this.Npa.i(1);
        this.Npa.j(1);
        this.Ca = ca;
        this.Yoa = n;
        this.Xoa = n;
        this.Zoa = this._(n);
        this.I();
        this.rka = new final();
        this.soa = new for[this.Xoa];
        this.roa = new for[this.Xoa];
        this.Ioa = new Vector[this.Xoa];
        this.Joa = new Vector[this.Xoa];
        this.Koa = new Vector[this.Xoa];
        this.Loa = new Vector[this.Xoa];
        this.Hoa = new goto[this.Xoa];
        this.Voa = new boolean[this.Xoa];
        this.Woa = new boolean[this.Xoa];
        this._pa = new Rectangle[this.Xoa];
        this.cpa = new boolean[this.Xoa];
        for (int i = 0; i < this.Xoa; ++i) {
            this.roa[i] = new for(1);
            this.soa[i] = new for(0);
            this.Ioa[i] = new Vector();
            this.Joa[i] = new Vector();
            this.Koa[i] = new Vector();
            this.Loa[i] = new Vector();
            (this.Hoa[i] = new goto()).setFont(new Font("SansSerif", 0, 12));
            if (i > 0) {
                this.Hoa[i].k(true);
                this.Hoa[i].l(true);
            }
            this.Voa[i] = false;
            this.Woa[i] = false;
            this.soa[i].Uma = null;
            this.roa[i].Uma = null;
            this._pa[i] = new Rectangle(0, 0, 0, 0);
            if (i == 0) {
                this.cpa[i] = true;
            }
            else {
                this.cpa[i] = false;
            }
        }
        this.pma = Color.lightGray;
        this.rma = null;
        this.Roa = null;
        this.Soa = null;
        this.Toa = null;
        this.Uma = null;
        this.setBackground(this.pma);
        this.Foa = 2;
        this.Gja = null;
        this.Hja = null;
        this.Ija = null;
        this.Moa = ((continue.fqa == null) ? (continue.fqa = class$("TrendLine")) : continue.fqa);
        this.Noa = null;
        this.Ooa = 4;
        this.Qoa = true;
        this.dpa = true;
        (this.uoa = new goto()).i(false);
        this.uoa.h(false);
        this.uoa.setColor(Color.red);
        (this.voa = new if()).i(false);
        this.voa.h(false);
        this.lpa = Color.blue;
        this.mpa = Color.green;
        this.J();
        this.addMouseListener(new l(this));
        this.addMouseMotionListener(new m(this));
        this.D(true);
        this.E(true);
        this.F(true);
        this.G(true);
        this.H(true);
        this.I(true);
        this.woa.h(false);
        this.woa.i(false);
        this.woa.setColor(Color.black);
        this.woa.i(1);
        this.woa.j(3);
        this.woa.k(true);
    }
    
    private void J() {
        this.tpa = new PopupMenu();
        (this.upa = new k(this.Ca.b("menuGridVisible"), true)).addItemListener(this);
        this.tpa.add(this.upa.b());
        (this.vpa = new k(this.Ca.b("menuTitleVisible"), true)).addItemListener(this);
        this.tpa.add(this.vpa.b());
        (this.wpa = new k(this.Ca.b("menuLegendVisible"), true)).addItemListener(this);
        this.tpa.add(this.wpa.b());
        (this.xpa = new k(this.Ca.b("menuAnnotationsVisible"), true)).addItemListener(this);
        this.tpa.add(this.xpa.b());
        (this.ypa = new k(this.Ca.b("menuCrosshairVisible"), true)).addItemListener(this);
        this.tpa.add(this.ypa.b());
        (this.zpa = new k(this.Ca.b("menuBuySellSignalsVisible"), true)).addItemListener(this);
        this.tpa.add(this.zpa.b());
        this.tpa.addSeparator();
        (this.Apa = new k(this.Ca.b("menuTrendLine"), true)).addItemListener(this);
        this.tpa.add(this.Apa.b());
        (this.Bpa = new k(this.Ca.b("menuSupportResistance"), false)).addItemListener(this);
        this.tpa.add(this.Bpa.b());
        (this.Cpa = new k(this.Ca.b("menuFibonacciRetracements"), false)).addItemListener(this);
        this.tpa.add(this.Cpa.b());
        (this.Dpa = new k(this.Ca.b("menuRegularRetracements"), false)).addItemListener(this);
        this.tpa.add(this.Dpa.b());
        (this.Epa = new k(this.Ca.b("menuTextTool"), false)).addItemListener(this);
        this.tpa.add(this.Epa.b());
        (this.Fpa = new k(this.Ca.b("menuArrowUp"), false)).addItemListener(this);
        this.tpa.add(this.Fpa.b());
        (this.Gpa = new k(this.Ca.b("menuArrowDown"), false)).addItemListener(this);
        this.tpa.add(this.Gpa.b());
        this.tpa.addSeparator();
        (this.Hpa = new k(this.Ca.b("menuToolModeAuto"), true)).addItemListener(this);
        this.tpa.add(this.Hpa.b());
        (this.Ipa = new k(this.Ca.b("menuToolModeDraw"), false)).addItemListener(this);
        this.tpa.add(this.Ipa.b());
        (this.Jpa = new k(this.Ca.b("menuToolModeMove"), false)).addItemListener(this);
        this.tpa.add(this.Jpa.b());
        (this.Kpa = new k(this.Ca.b("menuToolModeDelete"), false)).addItemListener(this);
        this.tpa.add(this.Kpa.b());
        this.tpa.addSeparator();
        if (k.uka) {
            this.Lpa = new MenuItem(this.Ca.b("menuDeleteAllTools"));
        }
        else {
            this.Lpa = new MenuItem("   " + this.Ca.b("menuDeleteAllTools"));
        }
        this.Lpa.addActionListener(this);
        this.tpa.add(this.Lpa);
        if (k.uka) {
            this.Mpa = new MenuItem(this.Ca.b("menuSetAxisMargins"));
        }
        else {
            this.Mpa = new MenuItem("   " + this.Ca.b("menuSetAxisMargins"));
        }
        this.Mpa.addActionListener(this);
        if (this.Xpa) {
            this.tpa.add(this.Mpa);
        }
        this.add(this.tpa);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem) {
            if (actionEvent.getSource() == this.Lpa) {
                this.B();
                this.repaint();
            }
            else if (actionEvent.getSource() == this.Mpa) {
                this.J(false);
                final Lh lh = new Lh(this.a(), this.Ca, this.pka, this.qka, this.nka, this.oka);
                lh.show();
                if (lh.a()) {
                    this.nka = lh._a();
                    this.oka = lh.aa();
                    this.pka = lh.ba();
                    this.qka = lh.ca();
                    this.repaint();
                }
                this.J(true);
            }
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.upa.b()) {
            this.H(this.upa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.vpa.b()) {
            this.E(this.vpa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.wpa.b()) {
            this.F(this.wpa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.xpa.b()) {
            this.G(this.xpa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.ypa.b()) {
            this.D(this.ypa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.zpa.b()) {
            this.I(this.zpa.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.Apa.b()) {
            this._((continue.fqa == null) ? (continue.fqa = class$("TrendLine")) : continue.fqa);
        }
        else if (itemEvent.getSource() == this.Bpa.b()) {
            this._((continue.gqa == null) ? (continue.gqa = class$("SupportResistance")) : continue.gqa);
        }
        else if (itemEvent.getSource() == this.Cpa.b()) {
            this._((continue.hqa == null) ? (continue.hqa = class$("FibonacciRetracements")) : continue.hqa);
        }
        else if (itemEvent.getSource() == this.Dpa.b()) {
            this._((continue.iqa == null) ? (continue.iqa = class$("RegularRetracements")) : continue.iqa);
        }
        else if (itemEvent.getSource() == this.Epa.b()) {
            this._((continue.jqa == null) ? (continue.jqa = class$("TextTool")) : continue.jqa);
        }
        else if (itemEvent.getSource() == this.Fpa.b()) {
            this._((continue.kqa == null) ? (continue.kqa = class$("ArrowUp")) : continue.kqa);
        }
        else if (itemEvent.getSource() == this.Gpa.b()) {
            this._((continue.lqa == null) ? (continue.lqa = class$("ArrowDown")) : continue.lqa);
        }
        else if (itemEvent.getSource() == this.Hpa.b()) {
            this.X(4);
        }
        else if (itemEvent.getSource() == this.Ipa.b()) {
            this.X(1);
        }
        else if (itemEvent.getSource() == this.Jpa.b()) {
            this.X(2);
        }
        else if (itemEvent.getSource() == this.Kpa.b()) {
            this.X(3);
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
    
    private double[] _(final int n) {
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
    
    public synchronized void N(final int xoa) {
        if (xoa < 1 || xoa > this.Yoa) {
            throw new IllegalArgumentException("Panel count must be in range: [1," + this.Yoa + "]");
        }
        for (int i = xoa + 1; i < this.Yoa; ++i) {
            this.O(i);
            this.M(i);
        }
        this.Xoa = xoa;
        this.Zoa = this._(this.Xoa);
        this.I();
    }
    
    public synchronized void x(final boolean rpa) {
        this.rpa = rpa;
    }
    
    private void Y(final int n) {
        if (n != 0 && n != 1) {
            throw new IllegalArgumentException("Axis must be: MultiChart.LETFT_AXIS or MultiChart.RIGHT_AXIS");
        }
    }
    
    private void I() {
        double n = 0.0;
        for (int i = 0; i < this.Xoa; ++i) {
            n += this.Zoa[i];
        }
        if (n == 0.0) {
            n = 1.0;
        }
        for (int j = 0; j < this.Xoa; ++j) {
            final double[] zoa = this.Zoa;
            final int n2 = j;
            zoa[n2] /= n;
        }
    }
    
    public synchronized goto a(final int n) {
        return this.Hoa[n];
    }
    
    public synchronized throws _() {
        return this.rka.b();
    }
    
    public synchronized throws _(final int n) {
        return this._(n, this.qpa);
    }
    
    public synchronized throws _(final int n, final int n2) {
        this.Y(n2);
        if (n2 == 0) {
            return this.soa[n].b();
        }
        return this.roa[n].b();
    }
    
    public void C(final String text) {
        this.woa.setText(text);
    }
    
    public void a(final int n, final String text) {
        this.Hoa[n].setText(text);
    }
    
    public synchronized Color a() {
        return this.pma;
    }
    
    public synchronized void g(final Color pma) {
        this.setBackground(this.pma = pma);
        this.Wpa.g(pma);
    }
    
    public synchronized void f(final Color rma) {
        this.rma = rma;
    }
    
    public synchronized void j(final Color roa) {
        this.Roa = roa;
    }
    
    public synchronized void k(final Color soa) {
        this.Soa = soa;
    }
    
    public synchronized void h(final Color toa) {
        this.Toa = toa;
    }
    
    public synchronized void i(final Color uma) {
        this.Uma = uma;
        this.rka.Uma = uma;
        for (int i = 0; i < this.N(); ++i) {
            this.soa[i].Uma = null;
            this.roa[i].Uma = uma;
        }
    }
    
    public synchronized void H(final boolean tma) {
        this.rka.Tma = tma;
        for (int i = 0; i < this.N(); ++i) {
            this.soa[i].Tma = tma;
            this.roa[i].Tma = tma;
        }
        this.upa.setState(this.rka.Tma);
    }
    
    public synchronized boolean T() {
        return this.rka.Tma;
    }
    
    public synchronized void l(final Color uoa) {
        this.Uoa = uoa;
    }
    
    public synchronized void b(final int n, final instanceof instanceof1) {
        this.b(n, this.qpa, instanceof1);
    }
    
    public synchronized void b(final int n, final int n2, final instanceof instanceof1) {
        this.Y(n2);
        if (n2 == 0) {
            this.Ioa[n].addElement(instanceof1);
        }
        else {
            this.Joa[n].addElement(instanceof1);
        }
    }
    
    public synchronized instanceof a(final int n, final int n2) {
        return this._(n, this.qpa, n2);
    }
    
    public synchronized instanceof _(final int n, final int n2, final int n3) {
        this.Y(n2);
        if (n2 == 0) {
            return this.Ioa[n].elementAt(n3);
        }
        return this.Joa[n].elementAt(n3);
    }
    
    public synchronized void a(final instanceof instanceof1) {
        for (int i = 0; i < this.ea(); ++i) {
            if (this.Ioa[i].contains(instanceof1)) {
                this.Ioa[i].removeElement(instanceof1);
            }
            if (this.Joa[i].contains(instanceof1)) {
                this.Joa[i].removeElement(instanceof1);
            }
        }
    }
    
    public synchronized void _(final int n, final instanceof instanceof1) {
        if (this.Ioa[n].contains(instanceof1)) {
            this.Ioa[n].removeElement(instanceof1);
        }
        if (this.Joa[n].contains(instanceof1)) {
            this.Joa[n].removeElement(instanceof1);
        }
    }
    
    public synchronized void O(final int n) {
        this.Ioa[n].removeAllElements();
        this.Joa[n].removeAllElements();
    }
    
    public synchronized void g(final int n, final int n2) {
        this.Y(n2);
        if (n2 == 0) {
            this.Ioa[n].removeAllElements();
        }
        if (n2 == 1) {
            this.Joa[n].removeAllElements();
        }
    }
    
    public synchronized void M(final int n) {
        this.Koa[n].removeAllElements();
        this.Loa[n].removeAllElements();
    }
    
    public synchronized void B() {
        for (int i = 0; i < this.N(); ++i) {
            this.M(i);
        }
    }
    
    public synchronized void h(final int n, final int n2) {
        this.Y(n2);
        if (n2 == 0) {
            this.Koa[n].removeAllElements();
        }
        if (n2 == 1) {
            this.Loa[n].removeAllElements();
        }
    }
    
    public synchronized int a(final int n) {
        return this.b(n, this.qpa);
    }
    
    public synchronized int b(final int n, final int n2) {
        this.Y(n2);
        if (n2 == 0) {
            return this.Ioa[n].size();
        }
        return this.Joa[n].size();
    }
    
    public synchronized void n(final Color mpa) {
        this.mpa = mpa;
    }
    
    public synchronized void P(final int npa) {
        this.npa = npa;
    }
    
    public synchronized void a(final int n, final UserDrawTool userDrawTool) {
        this.b(n, this.qpa, userDrawTool);
    }
    
    public synchronized void b(final int n, final int n2, final UserDrawTool userDrawTool) {
        this.Y(n2);
        if (n2 == 0) {
            this.Koa[n].addElement(userDrawTool);
        }
        if (n2 == 1) {
            this.Loa[n].addElement(userDrawTool);
        }
    }
    
    public synchronized Mh[] b() {
        final Vector vector = new Vector<Mh>();
        for (int i = 0; i < this.ea(); ++i) {
            for (int j = 0; j < this.b(i); ++j) {
                vector.addElement(new Mh(i, this.b(i, j)));
            }
        }
        final Mh[] array = new Mh[vector.size()];
        for (int k = 0; k < array.length; ++k) {
            array[k] = vector.elementAt(k);
        }
        return array;
    }
    
    public synchronized void _(final Mh[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].jb < this.Yoa) {
                this.a(array[i].jb, array[i].dka);
            }
        }
    }
    
    public synchronized UserDrawTool b(final int n, final int n2) {
        return this.a(n, this.qpa, n2);
    }
    
    public synchronized UserDrawTool a(final int n, final int n2, final int n3) {
        this.Y(n2);
        if (n2 == 0) {
            return this.Koa[n].elementAt(n3);
        }
        return this.Loa[n].elementAt(n3);
    }
    
    public synchronized void _(final UserDrawTool userDrawTool) {
        for (int i = 0; i < this.N(); ++i) {
            if (this.Koa[i].contains(userDrawTool)) {
                this.Koa[i].removeElement(userDrawTool);
            }
            if (this.Loa[i].contains(userDrawTool)) {
                this.Loa[i].removeElement(userDrawTool);
            }
        }
    }
    
    public synchronized void b(final int n, final UserDrawTool userDrawTool) {
        if (this.Koa[n].contains(userDrawTool)) {
            this.Koa[n].removeElement(userDrawTool);
        }
        if (this.Loa[n].contains(userDrawTool)) {
            this.Loa[n].removeElement(userDrawTool);
        }
    }
    
    public synchronized int b(final int n) {
        return this._(n, this.qpa);
    }
    
    public synchronized int _(final int n, final int n2) {
        this.Y(n2);
        if (n2 == 0) {
            return this.Koa[n].size();
        }
        return this.Loa[n].size();
    }
    
    public synchronized int ea() {
        return this.Xoa;
    }
    
    public synchronized int N() {
        return this.Yoa;
    }
    
    public synchronized void _(final Class moa) {
        this.Moa = moa;
        if (moa == ((continue.fqa == null) ? (continue.fqa = class$("TrendLine")) : continue.fqa)) {
            this.Apa.setState(true);
            this.Bpa.setState(false);
            this.Cpa.setState(false);
            this.Dpa.setState(false);
            this.Epa.setState(false);
            this.Fpa.setState(false);
            this.Gpa.setState(false);
        }
        if (moa == ((continue.gqa == null) ? (continue.gqa = class$("SupportResistance")) : continue.gqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(true);
            this.Cpa.setState(false);
            this.Dpa.setState(false);
            this.Epa.setState(false);
            this.Fpa.setState(false);
            this.Gpa.setState(false);
        }
        else if (moa == ((continue.hqa == null) ? (continue.hqa = class$("FibonacciRetracements")) : continue.hqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(false);
            this.Cpa.setState(true);
            this.Dpa.setState(false);
            this.Epa.setState(false);
            this.Fpa.setState(false);
            this.Gpa.setState(false);
        }
        else if (moa == ((continue.iqa == null) ? (continue.iqa = class$("RegularRetracements")) : continue.iqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(false);
            this.Cpa.setState(false);
            this.Dpa.setState(true);
            this.Epa.setState(false);
            this.Fpa.setState(false);
            this.Gpa.setState(false);
        }
        else if (moa == ((continue.jqa == null) ? (continue.jqa = class$("TextTool")) : continue.jqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(false);
            this.Cpa.setState(false);
            this.Dpa.setState(false);
            this.Epa.setState(true);
            this.Fpa.setState(false);
            this.Gpa.setState(false);
        }
        else if (moa == ((continue.kqa == null) ? (continue.kqa = class$("ArrowUp")) : continue.kqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(false);
            this.Cpa.setState(false);
            this.Dpa.setState(false);
            this.Epa.setState(false);
            this.Fpa.setState(true);
            this.Gpa.setState(false);
        }
        else if (moa == ((continue.lqa == null) ? (continue.lqa = class$("ArrowDown")) : continue.lqa)) {
            this.Apa.setState(false);
            this.Bpa.setState(false);
            this.Cpa.setState(false);
            this.Dpa.setState(false);
            this.Epa.setState(false);
            this.Fpa.setState(false);
            this.Gpa.setState(true);
        }
    }
    
    public void H() {
        this.gb = 0;
        this.repaint();
    }
    
    public void y(final boolean ypa) {
        this.Ypa = ypa;
        this.gb = 0;
    }
    
    public void D() {
        this.spa = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void Z(final int n) {
        int y = n;
        if (y == 0) {
            if (this.dpa) {
                y += Math.max(continue.eqa, this.uoa._(this.getGraphics())) + 3;
            }
            else {
                y += continue.eqa + 2;
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
        for (int i = 0; i < this.Xoa; ++i) {
            this._pa[i].x = 1;
            this._pa[i].y = y;
            this._pa[i].width = width;
            this._pa[i].height = (int)(this.Zoa[i] * n2 + 0.5);
            y += this._pa[i].height;
        }
    }
    
    private void _(final Graphics graphics, final int n, final int n2, final int n3, final int width, int n4) {
        int y = n3 + n + 4;
        n4 = n4 - n - 4;
        if (n4 < 0) {
            n4 = 0;
        }
        for (int i = 0; i < this.Xoa; ++i) {
            this._pa[i].x = n2 + 1;
            this._pa[i].y = y;
            this._pa[i].width = width;
            this._pa[i].height = (int)(this.Zoa[i] * n4 + 0.5);
            y += this._pa[i].height;
        }
    }
    
    private double[] fa() {
        final int n = this.b(0, 1) + this.b(0, 0);
        final instanceof[] array = new instanceof[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).u() <= this._(0, 1, i).v()) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = array[n2].u());
                array4[n2] = array[n2].v();
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
            if (this._(0, 0, j).u() <= this._(0, 0, j).v()) {
                array[n2] = this._(0, 0, j);
                array2[n2] = (array3[n2] = array[n2].u());
                array4[n2] = array[n2].v();
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
        final Nh nh = new Nh((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double e = array[l].e(array2[l]);
                    if (e < n5) {
                        n5 = e;
                    }
                }
            }
            if (!b) {
                break;
            }
            nh.b(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].e(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return nh.e();
    }
    
    public synchronized double[] da() {
        final int n = this.b(0, 1) + this.b(0, 0);
        final instanceof[] array = new instanceof[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).A() >= 0) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].A();
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
            if (this._(0, 0, j).A() >= 0) {
                array[n2] = this._(0, 0, j);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].A();
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
        final Nh nh = new Nh((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double e = array[l].e(array2[l]);
                    if (e < n5) {
                        n5 = e;
                    }
                }
            }
            if (!b) {
                break;
            }
            nh.b(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].e(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return nh.e();
    }
    
    private static byte b(final byte[] array, final int n) {
        if (array == null || n > array.length - 1) {
            return 0;
        }
        return array[n];
    }
    
    private void K() {
        this.Npa.setText(n._(this.Vpa));
        final n a = this.a();
        if (a != null) {
            final byte[] b = catch.b(this.Rpa);
            final byte[] _ = catch._("" + a.getName() + " " + n.M() + "  " + this.Tpa);
            int n = 0;
            for (int i = 0; i < Math.max(b.length, _.length); ++i) {
                n += Math.abs(b(b, i) - b(_, i));
            }
            final byte[] b2 = catch.b(this.Spa);
            final byte[] _2 = catch._("" + a.getName() + " " + n.M() + "  " + this.Upa);
            int n2 = 0;
            for (int j = 0; j < Math.max(b2.length, _2.length); ++j) {
                n2 += Math.abs(b(b2, j) - b(_2, j));
            }
            this.Opa = Math.min(n, n2);
            if (this.Qpa && this.Opa != 0) {
                this.Opa = Integer.MIN_VALUE;
            }
        }
    }
    
    private void L() {
        this.Npa.setText(n._(this.Vpa));
        final n a = this.a();
        if (a != null) {
            final byte[] b = catch.b(this.Rpa);
            final byte[] _ = catch._(" " + a.getName() + " " + n.M() + "  " + this.Tpa);
            int n = 0;
            for (int i = 0; i < Math.max(b.length, _.length); ++i) {
                n += Math.abs(b(b, i) - b(_, i));
            }
            final byte[] b2 = catch.b(this.Spa);
            final byte[] _2 = catch._(" " + a.getName() + " " + n.M() + "  " + this.Upa);
            int n2 = 0;
            for (int j = 0; j < Math.max(b2.length, _2.length); ++j) {
                n2 += Math.abs(b(b2, j) - b(_2, j));
            }
            this.Opa = Math.min(n, n2);
        }
    }
    
    public synchronized double[] ea() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).u() <= this._(0, 1, i).v()) {
                n = Math.min(n, this._(0, 1, i).e());
                n2 = Math.max(n2, this._(0, 1, i).f());
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
            if (this._(0, 0, j).u() <= this._(0, 0, j).v()) {
                n = Math.min(n, this._(0, 0, j).e());
                n2 = Math.max(n2, this._(0, 0, j).f());
            }
        }
        if (n == Double.POSITIVE_INFINITY || n == Double.NEGATIVE_INFINITY || n2 == Double.POSITIVE_INFINITY || n2 == Double.NEGATIVE_INFINITY) {
            return null;
        }
        return new double[] { n, n2 };
    }
    
    private int _(final Graphics graphics, final int n) {
        int n2 = 0;
        for (int i = 0; i < this.b(0, 0); ++i) {
            final instanceof _ = this._(0, 0, i);
            if (_.getName() != null && _.getName().length() > 0) {
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 1); ++j) {
            final instanceof _2 = this._(0, 1, j);
            if (_2.getName() != null && _2.getName().length() > 0) {
                ++n2;
            }
        }
        if (n2 == 0) {
            return 0;
        }
        final int[] array = new int[n2];
        final String[] array2 = new String[n2];
        final Color[] array3 = new Color[n2];
        int n3 = 0;
        for (int k = 0; k < this.b(0, 0); ++k) {
            final instanceof _3 = this._(0, 0, k);
            if (_3.getName() != null && _3.getName().length() > 0) {
                if (_3 instanceof import) {
                    array[n3] = ((import)_3).x();
                }
                else if (_3 instanceof public) {
                    array[n3] = ((public)_3).x();
                }
                else {
                    array[n3] = 0;
                }
                array2[n3] = _3.getName();
                array3[n3] = _3.xa;
                ++n3;
            }
        }
        for (int l = 0; l < this.b(0, 1); ++l) {
            final instanceof _4 = this._(0, 1, l);
            if (_4.getName() != null && _4.getName().length() > 0) {
                if (_4 instanceof import) {
                    array[n3] = ((import)_4).x();
                }
                else if (_4 instanceof public) {
                    array[n3] = ((public)_4).x();
                }
                else {
                    array[n3] = 0;
                }
                array2[n3] = _4.getName();
                array3[n3] = _4.xa;
                ++n3;
            }
        }
        this.voa.i(0);
        this.voa.j(3);
        final int n4 = n + 5;
        final int y = this._pa[0].y;
        int n5 = n4;
        int n6 = y;
        int max = 0;
        int max2 = 0;
        int n7 = 0;
        for (int n8 = 0; n8 < array2.length; ++n8) {
            this.voa.setText(array2[n8]);
            this.voa.h(array[n8]);
            this.voa.setColor(array3[n8]);
            final int b = this.voa.b(graphics);
            if (n5 > n4 && n5 + b >= this._pa[0].width - 5) {
                n5 = n4;
                n6 += max;
            }
            max = Math.max(max, this.voa._(graphics));
            n5 += this.voa.b(graphics) + 2;
            max2 = Math.max(max2, n5);
            n7 = n6 + max;
        }
        if (this.Toa != null) {
            graphics.setColor(this.Toa);
        }
        else {
            graphics.setColor(this.pma);
        }
        graphics.fillRect(n4, y, max2 - n4, n7 - y);
        graphics.setColor(this.rka.xa);
        graphics.drawRect(n4, y, max2 - n4, n7 - y);
        int n9 = n4;
        int n10 = y;
        int max3 = 0;
        for (int n11 = 0; n11 < array2.length; ++n11) {
            this.voa.setText(array2[n11]);
            this.voa.h(array[n11]);
            this.voa.setColor(array3[n11]);
            final int b2 = this.voa.b(graphics);
            if (n9 > n4 && n9 + b2 >= this._pa[0].width - 5) {
                n9 = n4;
                n10 += max3;
            }
            this.voa._(graphics, n9, n10);
            max3 = Math.max(max3, this.voa._(graphics));
            n9 += this.voa.b(graphics) + 2;
        }
        return n7;
    }
    
    private synchronized void _(final Graphics graphics) {
        if (this.rka.b() instanceof try && this.spa) {
            this.G();
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setClip(0, 0, width, height);
        this.c(graphics);
        if (this.Roa != null) {
            graphics.setColor(this.Roa);
            graphics.drawLine(0, 0, width - 1, 0);
            graphics.drawLine(0, 0, 0, height - 1);
        }
        if (this.Soa != null) {
            graphics.setColor(this.Soa);
            graphics.drawLine(0, height - 1, width - 1, height - 1);
            graphics.drawLine(width - 1, 0, width - 1, height - 1);
        }
        this.Z(0);
        if (this.hpa) {
            this.Z(this._(graphics, 0));
        }
        this.M();
        this.d(graphics);
        if (this.Opa == Integer.MAX_VALUE) {
            this.K();
        }
        if (this.Opa == Integer.MIN_VALUE) {
            this.L();
        }
        for (int i = 0; i < this.Xoa; ++i) {
            this.b(i, graphics);
            this._(i, graphics);
            this.a(i, graphics);
            this.c(i, graphics);
            for (int j = 0; j < this.Opa; ++j) {
                if (i > 0) {
                    break;
                }
                this.Npa._(graphics, this._pa[i].x + this._pa[i].width / 2, this._pa[i].y + this._pa[i].height / 2);
                if (width / 2 > 0) {
                    break;
                }
                if (height / 2 > 0) {
                    break;
                }
                if (j % 2 == 0) {
                    break;
                }
            }
            if (this.Noa != null) {
                this.Noa.a(graphics, this.b(this.Poa), this.b(), this._(), this._(this.Poa));
                this.Noa.a(this.ska, this.tka, this._(), this._(this.Poa));
            }
            if (this.Qoa && this.rka.b() instanceof try && ((try)this.rka.b()).da() > 1) {
                this.d(i, graphics);
            }
        }
        this.N();
    }
    
    public synchronized int f(final Graphics graphics) {
        if (this.woa.isEmpty()) {
            return 0;
        }
        return this.woa._(graphics);
    }
    
    public synchronized void b(final Graphics graphics, final int n, final int n2, final int n3, int n4) {
        this.G();
        graphics.setClip(n, n2, n3, n4);
        graphics.setColor(this.pma);
        graphics.fillRect(n, n2, n3, n4);
        if (this.rma != null) {
            graphics.setColor(this.rma);
            graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        }
        final int f = this.f(graphics);
        n4 -= f;
        this._(graphics, 0, n, n2, n3, n4);
        if (this.hpa) {
            this._(graphics, this._(graphics, n) - n2, n, n2, n3, n4);
        }
        this.M();
        this.g(graphics, n, n3);
        if (this.Opa == Integer.MAX_VALUE) {
            this.K();
        }
        if (this.Opa == Integer.MIN_VALUE) {
            this.L();
        }
        for (int i = 0; i < this.Xoa; ++i) {
            this.b(i, graphics);
            this._(i, graphics);
            this.a(i, graphics);
            this.c(i, graphics);
            for (int j = 0; j < this.Opa; ++j) {
                if (i > 0) {
                    break;
                }
                this.Npa._(graphics, this._pa[i].x + this._pa[i].width / 2, this._pa[i].y + this._pa[i].height / 2);
                if (n + n3 / 2 > 0) {
                    break;
                }
                if (n2 + n4 / 2 > 0) {
                    break;
                }
                if (j % 2 == 0) {
                    break;
                }
            }
            if (this.Qoa && this.rka.b() instanceof try && ((try)this.rka.b()).da() > 1) {
                this.d(i, graphics);
            }
        }
        if (f > 0) {
            graphics.setClip(n, n2 + n4 - 1, n3, f + 1);
            this.woa._(graphics, n + n3 / 2, n2 + n4 - 1);
        }
        this.spa = true;
    }
    
    public synchronized void G() {
        final throws b = this.rka.b();
        if (b instanceof try) {
            ((try)b)._(this.fa());
            this.spa = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.Ypa) {
            if (this.Hja != null && this.Ija != null) {
                graphics.drawImage(this.Ija, 0, 0, this);
                if (this.gb > 0) {
                    this._(graphics, this.gb);
                }
            }
            else {
                graphics.setColor(this.pma);
                graphics.setClip(0, 0, size.width, size.height);
                this.c(graphics);
                if (this.Roa != null) {
                    graphics.setColor(this.Roa);
                    graphics.drawLine(0, 0, size.width - 1, 0);
                    graphics.drawLine(0, 0, 0, size.height - 1);
                }
                if (this.Soa != null) {
                    graphics.setColor(this.Soa);
                    graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
                    graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                }
                if (this.gb > 0) {
                    this._(graphics, this.gb);
                }
            }
        }
        else if (size.width > 0 && size.height > 0) {
            if (this.Ija == null || this.Hja == null || this.Gja == null || size.width != this.Gja.width || size.height != this.Gja.height) {
                if (this.Hja != null) {
                    this.Hja.dispose();
                }
                if (this.Ija != null) {
                    this.Ija.flush();
                }
                this.Ija = this.createImage(size.width, size.height);
                this.Hja = this.Ija.getGraphics();
                this.Gja = size;
            }
            this._(this.Hja);
            graphics.drawImage(this.Ija, 0, 0, this);
            if (this.gb > 0) {
                this._(graphics, this.gb);
            }
        }
    }
    
    private void _(final int n, final Graphics graphics) {
        final int y = this._pa[n].y;
        final int width = this._pa[n].width;
        final int height = this._pa[n].height;
        final int b = this.Hoa[n].b(graphics);
        final int _ = this.Hoa[n]._(graphics);
        if (this.Hoa[n].g()) {
            this.Goa = 0;
        }
        else {
            this.Goa = 1;
        }
        graphics.setClip(this._pa[n]);
        if ((this.gpa || this.a(0) == 0) && !this.Hoa[n].isEmpty()) {
            final String text = this.Hoa[n].getText();
            if (this.b(n, 1) >= 0) {
                this.Hoa[n].setText(text + this.c(n));
            }
            switch (this.Foa) {
                case 2: {
                    this.Hoa[n].i(0);
                    this.Hoa[n].j(3);
                    if (this.b(0, 0) == 0 && this.b(0, 1) == 0) {
                        final int n2 = (width - b) / 2;
                        if (this.Toa != null) {
                            graphics.setColor(this.Toa);
                            graphics.fillRect(n2, y, b + 2 * this.Goa, _ + 2 * this.Goa);
                        }
                        graphics.setColor(this.rka.xa);
                        graphics.drawRect(n2, y, b + 2 * this.Goa, _ + 2 * this.Goa);
                        this.Hoa[n].f(n2 + this.Goa, y + this.Goa);
                        break;
                    }
                    this.Hoa[n].f(this.soa[n].D() + this.Goa, y + this.Goa);
                    break;
                }
            }
            this.Hoa[n].a(graphics);
            this.Hoa[n].setText(text);
        }
    }
    
    private void c(final Graphics graphics) {
        graphics.setColor(this.pma);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    
    private void M() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.Xoa; ++i) {
            double n4;
            double n3 = n4 = Double.POSITIVE_INFINITY;
            double n6;
            double n5 = n6 = Double.NEGATIVE_INFINITY;
            if (this.Ioa[i].size() != 0 || this.Joa[i].size() != 0) {
                if (this.rka.Q()) {
                    n = Math.min(n, this.f(i));
                    n2 = Math.max(n2, this.g(i));
                }
                else {
                    n = this.rka.i();
                    n2 = this.rka.S();
                }
                if (!this.soa[i].Q()) {
                    n4 = this.soa[i].i();
                    n6 = this.soa[i].S();
                }
                if (!this.roa[i].Q()) {
                    n3 = this.roa[i].i();
                    n5 = this.roa[i].S();
                }
                if (this.Ioa[i].size() > 0 && this.soa[i].Q()) {
                    n4 = Math.min(n4, this.a(i, 0));
                    n6 = Math.max(n6, this.l(i, 0));
                    this.soa[i].j(n4, n6);
                }
                if (this.Joa[i].size() > 0 && this.roa[i].Q()) {
                    n3 = Math.min(n3, this.a(i, 1));
                    n5 = Math.max(n5, this.l(i, 1));
                    this.roa[i].j(n3, n5);
                }
                if (this.rpa && i == 0 && this.Ioa[i].size() > 0 && this.Joa[i].size() > 0) {
                    final double min = Math.min(n4, n3);
                    final double max = Math.max(n6, n5);
                    this.soa[i].j(min, max);
                    this.roa[i].j(min, max);
                }
                else if (n4 > n6 || n3 > n5) {
                    if (n4 > n6) {
                        this.soa[i].j(1.0, 10.0);
                    }
                    if (n3 > n5) {
                        this.roa[i].j(1.0, 10.0);
                    }
                }
            }
        }
        if (n > n2) {
            n = 1.0;
            n2 = 100.0;
        }
        this.rka.j(n, n2);
    }
    
    private void i(final int n, final int n2) {
        for (int i = 0; i < this.N(); ++i) {
            for (int j = 0; j < this.Koa[i].size(); ++j) {
                final UserDrawTool userDrawTool = this.Koa[i].elementAt(j);
                if (userDrawTool instanceof SupportResistance) {
                    ((SupportResistance)userDrawTool).b(n, n2);
                }
            }
            for (int k = 0; k < this.Loa[i].size(); ++k) {
                final UserDrawTool userDrawTool2 = this.Loa[i].elementAt(k);
                if (userDrawTool2 instanceof SupportResistance) {
                    ((SupportResistance)userDrawTool2).b(n, n2);
                }
            }
        }
    }
    
    private boolean b(final int n) {
        for (int i = 0; i < this.Ioa[n].size(); ++i) {
            if (this.Ioa[n].elementAt(i) instanceof synchronized) {
                return true;
            }
        }
        for (int j = 0; j < this.Joa[n].size(); ++j) {
            if (this.Joa[n].elementAt(j) instanceof synchronized) {
                return true;
            }
        }
        return false;
    }
    
    private void b(final int n, final Graphics graphics) {
        graphics.setClip(this._pa[n]);
        if (n == 0) {
            this.roa[n].H(this.oka);
            this.roa[n].I(this.nka);
            this.soa[n].H(this.oka);
            this.soa[n].I(this.nka);
            this.rka.H(this.qka);
            this.rka.I(this.pka);
        }
        else {
            this.roa[n].H(0);
            this.roa[n].I(0);
            this.soa[n].H(0);
            this.soa[n].I(0);
        }
        this.Voa[n] = (this.Woa[n] = false);
        if (this.Ioa[n].size() == 0 && this.Joa[n].size() == 0) {
            return;
        }
        this.e(n, graphics);
        this.rka.Vma = this.roa[n].E() + 1;
        this.rka.Wma = this.roa[n].F() - 1;
        this.soa[n].Vma = this.rka.fa() + 1;
        this.soa[n].Wma = this.rka.ga() - 1;
        this.roa[n].Vma = this.rka.fa() + 1;
        this.roa[n].Wma = this.rka.ga() - 1;
        this.i(this.rka.fa() + 1, this.rka.ga() - 1);
        graphics.setColor(this.rka.xa);
        graphics.drawRect(this.soa[n].D(), this.soa[n].E(), this.roa[n].D() - this.soa[n].D(), this.roa[n].F() - this.soa[n].E());
        if (this.Toa != null) {
            graphics.setColor(this.Toa);
            graphics.fillRect(this.soa[n].D() + 1, this.soa[n].E() + 1, this.roa[n].D() - this.soa[n].D() - 1, this.roa[n].F() - this.soa[n].E() - 1);
        }
        if (this.gpa && this.a(n).P()) {
            int n2;
            if (this.Hoa[n].g()) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            final int n3 = n2 + this.a(n)._(graphics);
            this.roa[n].H(Math.max(this.roa[n].K(), n3));
            this.soa[n].H(Math.max(this.soa[n].K(), n3));
        }
        if (this.ipa && this.b(n)) {
            this.roa[n].H(this.roa[n].K() + 5 + 4);
            this.roa[n].I(this.roa[n].L() + 5 + 4);
            this.soa[n].H(this.soa[n].K() + 5 + 4);
            this.soa[n].I(this.soa[n].L() + 5 + 4);
        }
        if (this.Ioa[n].size() > 0) {
            this.Voa[n] = this.soa[n].a(graphics);
        }
        if (this.Joa[n].size() > 0) {
            this.Woa[n] = this.roa[n].a(graphics);
        }
        final boolean a = this.rka.a(graphics);
        this.Voa[n] = (this.Voa[n] && a);
        this.Woa[n] = (this.Woa[n] && a);
    }
    
    private void d(final Graphics graphics) {
        this.bpa = 5;
        for (int i = 0; i < this.Xoa; ++i) {
            this.soa[i].setBounds(this._pa[i]);
            if (this.b(i, 0) > 0) {
                this.bpa = Math.max(this.bpa, this.soa[i].i(graphics));
            }
        }
        this.apa = this.getSize().width - 5;
        for (int j = 0; j < this.Xoa; ++j) {
            this.roa[j].setBounds(this._pa[j]);
            if (this.b(j, 1) > 0) {
                this.apa = Math.min(this.apa, this.roa[j].i(graphics));
            }
        }
    }
    
    private void g(final Graphics graphics, final int n, final int n2) {
        this.bpa = n + 5;
        for (int i = 0; i < this.Xoa; ++i) {
            this.soa[i].setBounds(this._pa[i]);
            if (this.b(i, 0) > 0) {
                this.bpa = Math.max(this.bpa, this.soa[i].i(graphics));
            }
        }
        this.apa = n + n2 - 5;
        for (int j = 0; j < this.Xoa; ++j) {
            this.roa[j].setBounds(this._pa[j]);
            if (this.b(j, 1) > 0) {
                this.apa = Math.min(this.apa, this.roa[j].i(graphics));
            }
        }
    }
    
    private void e(final int n, final Graphics graphics) {
        this.rka.d(this.cpa[n]);
        this.rka.e(this.cpa[n]);
        this.rka.setBounds(this._pa[n]);
        this.soa[n].setBounds(this._pa[n]);
        this.roa[n].setBounds(this._pa[n]);
        switch (this.rka.G()) {
            case 0: {
                this.soa[n].y(this.rka.c(graphics));
                this.roa[n].y(this.rka.c(graphics));
                this.soa[n].z(this._pa[n].y + this._pa[n].height - 1);
                this.roa[n].z(this._pa[n].y + this._pa[n].height - 1);
                break;
            }
            case 1: {
                this.soa[n].z(this.rka.c(graphics));
                this.roa[n].z(this.rka.c(graphics));
                this.soa[n].y(this._pa[n].y);
                this.roa[n].y(this._pa[n].y);
                break;
            }
        }
        this.rka.J(this.bpa);
        this.rka.K(this.apa);
        this.soa[n].A(this.bpa);
        this.roa[n].A(this.apa);
    }
    
    private double g(final int n) {
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.Ioa[n].size(); ++i) {
            n2 = Math.max(n2, ((instanceof)this.Ioa[n].elementAt(i)).l());
        }
        for (int j = 0; j < this.Joa[n].size(); ++j) {
            n2 = Math.max(n2, ((instanceof)this.Joa[n].elementAt(j)).l());
        }
        return n2;
    }
    
    private double f(final int n) {
        double n2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.Ioa[n].size(); ++i) {
            n2 = Math.min(n2, ((instanceof)this.Ioa[n].elementAt(i)).k());
        }
        for (int j = 0; j < this.Joa[n].size(); ++j) {
            n2 = Math.min(n2, ((instanceof)this.Joa[n].elementAt(j)).k());
        }
        return n2;
    }
    
    private double l(final int n, final int n2) {
        double n3 = Double.NEGATIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.Ioa[n].size(); ++i) {
                final instanceof instanceof1 = this.Ioa[n].elementAt(i);
                if (!(instanceof1 instanceof synchronized) || this.ipa) {
                    n3 = Math.max(n3, instanceof1.n());
                }
            }
        }
        else {
            for (int j = 0; j < this.Joa[n].size(); ++j) {
                final instanceof instanceof2 = this.Joa[n].elementAt(j);
                if (!(instanceof2 instanceof synchronized) || this.ipa) {
                    n3 = Math.max(n3, instanceof2.n());
                }
            }
        }
        return n3;
    }
    
    private double a(final int n, final int n2) {
        double n3 = Double.POSITIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.Ioa[n].size(); ++i) {
                final instanceof instanceof1 = this.Ioa[n].elementAt(i);
                if (!(instanceof1 instanceof synchronized) || this.ipa) {
                    n3 = Math.min(n3, instanceof1.m());
                }
            }
        }
        else {
            for (int j = 0; j < this.Joa[n].size(); ++j) {
                final instanceof instanceof2 = this.Joa[n].elementAt(j);
                if (!(instanceof2 instanceof synchronized) || this.ipa) {
                    n3 = Math.min(n3, instanceof2.m());
                }
            }
        }
        return n3;
    }
    
    private void a(final int n, final Graphics graphics) {
        if (!this.Voa[n] && !this.Woa[n]) {
            return;
        }
        final int fa = this.rka.fa();
        final int ga = this.rka.ga();
        if (n == 1) {
            if (this.Woa[n]) {
                final int e = this.roa[n].E();
                graphics.setClip(fa + 1, e + 1, ga - fa, this.roa[n].F() - e);
                for (int i = 0; i < this.Joa[n].size(); ++i) {
                    final instanceof instanceof1 = this.Joa[n].elementAt(i);
                    if (!(instanceof1 instanceof switch) || this.fpa) {
                        if (!(instanceof1 instanceof synchronized)) {
                            instanceof1.b(graphics, this._(), this._(n, 1));
                        }
                    }
                }
            }
            if (this.Voa[n]) {
                final int e2 = this.soa[n].E();
                graphics.setClip(fa + 1, e2 + 1, ga - fa, this.soa[n].F() - e2);
                for (int j = 0; j < this.Ioa[n].size(); ++j) {
                    final instanceof instanceof2 = this.Ioa[n].elementAt(j);
                    if (!(instanceof2 instanceof switch) || this.fpa) {
                        if (!(instanceof2 instanceof synchronized)) {
                            instanceof2.b(graphics, this._(), this._(n, 0));
                        }
                    }
                }
            }
        }
        else {
            if (this.Voa[n]) {
                final int e3 = this.soa[n].E();
                graphics.setClip(fa + 1, e3 + 1, ga - fa, this.soa[n].F() - e3);
                for (int k = 0; k < this.Ioa[n].size(); ++k) {
                    final instanceof instanceof3 = this.Ioa[n].elementAt(k);
                    if (!(instanceof3 instanceof switch) || this.fpa) {
                        if (!(instanceof3 instanceof synchronized)) {
                            instanceof3.b(graphics, this._(), this._(n, 0));
                        }
                    }
                }
            }
            if (this.Woa[n]) {
                final int e4 = this.roa[n].E();
                graphics.setClip(fa + 1, e4 + 1, ga - fa, this.roa[n].F() - e4);
                for (int l = 0; l < this.Joa[n].size(); ++l) {
                    final instanceof instanceof4 = this.Joa[n].elementAt(l);
                    if (!(instanceof4 instanceof switch) || this.fpa) {
                        if (!(instanceof4 instanceof synchronized)) {
                            instanceof4.b(graphics, this._(), this._(n, 1));
                        }
                    }
                }
            }
        }
    }
    
    private void c(final int n, final Graphics graphics) {
        if (!this.Voa[n] && !this.Woa[n]) {
            return;
        }
        graphics.setClip(this._pa[n]);
        if (this.Voa[n]) {
            for (int i = 0; i < this.Ioa[n].size(); ++i) {
                final instanceof instanceof1 = this.Ioa[n].elementAt(i);
                if (instanceof1 instanceof synchronized) {
                    if (this.ipa) {
                        instanceof1.b(graphics, this._(), this._(n, 0));
                    }
                }
            }
        }
        if (this.Woa[n]) {
            for (int j = 0; j < this.Joa[n].size(); ++j) {
                final instanceof instanceof2 = this.Joa[n].elementAt(j);
                if (instanceof2 instanceof synchronized) {
                    if (this.ipa) {
                        instanceof2.b(graphics, this._(), this._(n, 1));
                    }
                }
            }
        }
    }
    
    private Rectangle b(final int n) {
        if (this.Ppa) {
            return new Rectangle(this.soa[n].D(), this.soa[n].E(), this.roa[n].D() - this.soa[n].D() + 1, this.roa[n].F() - this.soa[n].E() + 1);
        }
        return this._pa[n];
    }
    
    private void d(final int n, final Graphics graphics) {
        if (!this.Voa[n] && !this.Woa[n]) {
            return;
        }
        graphics.setClip(this.b(n));
        if (this.Voa[n]) {
            for (int i = 0; i < this.Koa[n].size(); ++i) {
                ((UserDrawTool)this.Koa[n].elementAt(i)).b(graphics, this.b(n), this.b(), this._(), this._(n, 0));
            }
        }
        if (this.Woa[n]) {
            for (int j = 0; j < this.Loa[n].size(); ++j) {
                ((UserDrawTool)this.Loa[n].elementAt(j)).b(graphics, this.b(n), this.b(), this._(), this._(n, 1));
            }
        }
    }
    
    public synchronized void l(final double n, final double n2) {
        for (int i = 0; i < this.Xoa; ++i) {
            for (int j = 0; j < this.Ioa[i].size(); ++j) {
                ((instanceof)this.Ioa[i].elementAt(j)).k(n, n2);
            }
            for (int k = 0; k < this.Joa[i].size(); ++k) {
                ((instanceof)this.Joa[i].elementAt(k)).k(n, n2);
            }
        }
    }
    
    public synchronized void B(final boolean b) {
        for (int i = 0; i < this.Xoa; ++i) {
            for (int j = 0; j < this.Ioa[i].size(); ++j) {
                ((instanceof)this.Ioa[i].elementAt(j)).j(b);
            }
            for (int k = 0; k < this.Joa[i].size(); ++k) {
                ((instanceof)this.Joa[i].elementAt(k)).j(b);
            }
        }
    }
    
    private void O() {
        switch (this.Ooa) {
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
    
    public void X(final int ooa) {
        switch (this.Ooa = ooa) {
            case 4: {
                this.Hpa.setState(true);
                this.Ipa.setState(false);
                this.Jpa.setState(false);
                this.Kpa.setState(false);
                break;
            }
            case 1: {
                this.Hpa.setState(false);
                this.Ipa.setState(true);
                this.Jpa.setState(false);
                this.Kpa.setState(false);
                break;
            }
            case 2: {
                this.Hpa.setState(false);
                this.Ipa.setState(false);
                this.Jpa.setState(true);
                this.Kpa.setState(false);
                break;
            }
            case 3: {
                this.Hpa.setState(false);
                this.Ipa.setState(false);
                this.Jpa.setState(false);
                this.Kpa.setState(true);
                break;
            }
        }
        this.O();
    }
    
    private synchronized void b(final MouseEvent mouseEvent) {
        if (this.tpa != null) {
            this.tpa.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private Frame a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container instanceof Frame) {
            return (Frame)container;
        }
        return null;
    }
    
    private n a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof n); container = container.getParent()) {}
        if (container instanceof n) {
            return (n)container;
        }
        return null;
    }
    
    private void J(final boolean b) {
        final n a = this.a();
        if (a != null) {
            a.b(b);
        }
    }
    
    private int n(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < this.ea(); ++i) {
            if (n2 >= this._pa[i].y && n2 <= this._pa[i].y + this._pa[i].height) {
                n3 = i;
            }
        }
        if (n3 == -1 || this.a(n3) == 0) {
            return -1;
        }
        return n3;
    }
    
    public synchronized void K(final boolean dpa) {
        this.dpa = dpa;
    }
    
    public synchronized void D(final boolean epa) {
        this.epa = epa;
        this.ypa.setState(this.epa);
    }
    
    public synchronized boolean U() {
        return this.epa;
    }
    
    public synchronized void P() {
        this.tpa.remove(this.zpa.b());
    }
    
    public synchronized void I(final boolean fpa) {
        this.fpa = fpa;
        this.zpa.setState(this.fpa);
    }
    
    public synchronized boolean V() {
        return this.fpa;
    }
    
    public synchronized void E(final boolean gpa) {
        this.gpa = gpa;
        this.vpa.setState(this.gpa);
    }
    
    public synchronized void F(final boolean hpa) {
        this.hpa = hpa;
        this.wpa.setState(this.hpa);
    }
    
    public synchronized void G(final boolean ipa) {
        this.ipa = ipa;
        this.xpa.setState(this.ipa);
    }
    
    public synchronized boolean W() {
        return this.gpa;
    }
    
    public synchronized boolean X() {
        return this.hpa;
    }
    
    public synchronized boolean Y() {
        return this.ipa;
    }
    
    public synchronized void m(final Color lpa) {
        this.lpa = lpa;
    }
    
    private Color b() {
        if (this.Toa != null) {
            return this.Toa;
        }
        return this.pma;
    }
    
    private void j(final int n, final int kpa) {
        final int y = this._pa[0].y;
        if (kpa < y) {
            return;
        }
        final int jpa = (int)this._().b(this.a(0, this.a(0) - 1).e(n));
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.setXORMode(this.b());
        graphics.setColor(this.lpa);
        graphics.drawLine(jpa, y, jpa, this.getSize().height - 2);
        graphics.drawLine(1, kpa, this.getSize().width - 2, kpa);
        graphics.setPaintMode();
        this.jpa = jpa;
        this.kpa = kpa;
    }
    
    private void N() {
        final int y = this._pa[0].y;
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.setXORMode(this.b());
        graphics.setColor(this.lpa);
        if (this.jpa > 0 && this.kpa > 0) {
            graphics.drawLine(this.jpa, y, this.jpa, this.getSize().height - 2);
            graphics.drawLine(1, this.kpa, this.getSize().width - 2, this.kpa);
        }
        final int n = -1;
        this.kpa = n;
        this.jpa = n;
        graphics.setPaintMode();
    }
    
    private void Q() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.Ypa) {
            return;
        }
        graphics.setColor(this.pma);
        graphics.fillRect(2, 2, this.getSize().width - 4, this.uoa._(graphics) + 1);
    }
    
    private for a(final int n) {
        if (this.qpa == 0) {
            return this.soa[n];
        }
        return this.roa[n];
    }
    
    private String b(final int n, final double n2) {
        if (n > 0) {
            return this.a(n).e(n2);
        }
        final int j = this.roa[n].J();
        int n3;
        if (n2 >= 1.0) {
            n3 = 0;
        }
        else {
            n3 = (int)Math.ceil(Math.abs(d.l(n2)));
        }
        final int n4 = (int)Math.ceil(d.l(n2));
        if (j > 0 && j > n3) {
            n3 = j;
        }
        this.Zpa.setMaximumFractionDigits(n3);
        this.Zpa.setMinimumFractionDigits(n3);
        this.Zpa.setMaximumIntegerDigits(n4 + 1);
        this.Zpa.setMinimumIntegerDigits(1);
        this.Zpa.setGroupingSize(3);
        this.Zpa.setGroupingUsed(true);
        return this.Zpa.format(n2);
    }
    
    private int b(final double n, final double n2, final int n3) {
        if (this.Ypa) {
            return -1;
        }
        final StringBuffer sb = new StringBuffer();
        int b = -1;
        instanceof a = null;
        if (this.a(0) > 0) {
            a = this.a(0, this.a(0) - 1);
            b = a.b(n);
        }
        if (b < 0) {
            return b;
        }
        sb.append("Y: " + this.b(n3, n2));
        final String l = a.L();
        if (l != null) {
            if (l.length() > 0) {
                sb.append(" |" + l + ": " + this.rka.g(a.e(b)));
            }
            else {
                sb.append(" |" + l + this.rka.g(a.e(b)));
            }
        }
        for (int i = 0; i < this.ea(); ++i) {
            for (int j = this.a(i) - 1; j >= 0; --j) {
                final instanceof a2 = this.a(i, j);
                for (int k = 0; k < a2.w(); ++k) {
                    final String a3 = a2.a(k);
                    if (a3 != null) {
                        if (a3.length() > 0) {
                            sb.append(" |" + a3 + ": " + this.b(i, a2._(b, k)));
                        }
                        else {
                            sb.append(" |" + a3 + this.b(i, a2._(b, k)));
                        }
                    }
                }
                if (i == 0 && j == this.a(i) - 1 && b > 0) {
                    final double _ = a2._(b - 1, a2.w() - 1);
                    if (_ != 0.0) {
                        final double n4 = 100.0 * (a2._(b, a2.w() - 1) - _) / _;
                        sb.append(" (");
                        if (n4 > 0.0) {
                            sb.append("+");
                        }
                        final String b2 = this.b(i, 0.0);
                        int n5 = 1;
                        if (b2.length() > 4) {
                            n5 = 2;
                        }
                        this._qa.setMaximumFractionDigits(Math.min(n5, 2));
                        sb.append(this._qa.format(n4));
                        sb.append("%)");
                    }
                }
            }
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return b;
        }
        this.uoa.setText(sb.toString());
        graphics.setClip(2, 2, this.getSize().width - 5, this.uoa._(graphics) + 1);
        this.uoa._(graphics, 4, 2);
        return b;
    }
    
    private synchronized void _(final Graphics graphics, final int n) {
        if (!this.isShowing()) {
            return;
        }
        int dqa = continue.eqa;
        if (this.dpa) {
            dqa = Math.max(this.uoa._(graphics), dqa);
        }
        final int cqa = this.getSize().width - 11;
        final int n2 = 5;
        final int n3 = 2;
        if (cqa < 1) {
            return;
        }
        if (graphics == null) {
            return;
        }
        if (this.aqa == null || this.bqa == null || cqa != this.cqa || dqa != this.dqa) {
            if (this.bqa != null) {
                this.bqa.dispose();
            }
            this.dqa = dqa;
            this.cqa = cqa;
            if (this.aqa != null) {
                this.aqa.flush();
            }
            this.aqa = this.createImage(this.cqa, this.dqa);
            this.bqa = this.aqa.getGraphics();
        }
        if (this.bqa == null) {
            return;
        }
        final int min = Math.min(this.cqa, (int)(this.cqa * (n / 100.0)));
        this.bqa.setColor(this.pma);
        this.bqa.fillRect(0, 0, this.cqa, this.dqa);
        this.bqa.setColor(this.Uoa);
        this.bqa.fillRect(0, (this.dqa - continue.eqa) / 2, min, continue.eqa);
        graphics.drawImage(this.aqa, n2, n3, this);
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final int intValue = (int)o;
        this._(graphics, intValue);
        this.gb = intValue;
    }
    
    private String c(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append(" [");
        int a = -1;
        if (this.a(n) > 0) {
            a = this.a(n, this.a(n) - 1).A();
        }
        if (a < 0) {
            return "";
        }
        for (int i = this.a(n) - 1; i >= 0; --i) {
            final instanceof a2 = this.a(n, i);
            final int a3 = a2.A();
            for (int j = 0; j < a2.w(); ++j) {
                final String a4 = a2.a(j);
                if (a4 != null) {
                    if (n == 0) {
                        if (a4.length() > 0) {
                            String s = "";
                            if (j > 0) {
                                s = "  ";
                            }
                            sb.append(s + a4 + ": " + this.b(n, a2._(a3, j)));
                        }
                        else {
                            sb.append("  " + a4 + this.b(n, a2._(a3, j)));
                        }
                    }
                    else {
                        sb.append(this.b(n, a2._(a3, j)));
                    }
                }
            }
        }
        sb.append("]");
        if (this.a(n) > 0) {
            final instanceof a5 = this.a(n, this.a(n) - 1);
            final int a6 = a5.A();
            if (n == 0 && a6 > 0) {
                final double _ = a5._(a6 - 1, a5.w() - 1);
                if (_ != 0.0) {
                    final double n2 = 100.0 * (a5._(a6, a5.w() - 1) - _) / _;
                    sb.append(" (");
                    if (n2 > 0.0) {
                        sb.append("+");
                    }
                    final String b = this.b(n, 0.0);
                    int maximumFractionDigits = 1;
                    if (b.length() > 4) {
                        maximumFractionDigits = 2;
                    }
                    this._qa.setMaximumFractionDigits(maximumFractionDigits);
                    sb.append(this._qa.format(n2));
                    sb.append("%)");
                }
            }
        }
        return sb.toString();
    }
    
    public synchronized Oh a(final boolean b) {
        return new Oh(this, b);
    }
    
    public synchronized void _(final OutputStream outputStream, final int n, final int n2) {
        Graphics graphics = null;
        try {
            graphics = new BufferedImage(n, n2, 1).createGraphics();
            this.b(graphics, 0, 0, n, n2);
        }
        finally {
            if (graphics != null) {
                graphics.dispose();
            }
            this.repaint();
        }
    }
    
    public synchronized void a(final OutputStream outputStream, final String s, final int n, final int n2) {
        Graphics graphics = null;
        Exception ex = null;
        try {
            final BufferedImage bufferedImage = new BufferedImage(n, n2, 1);
            graphics = bufferedImage.createGraphics();
            this.b(graphics, 0, 0, n, n2);
            try {
                Class.forName("javax.imageio.ImageIO").getMethod("write", Class.forName("java.awt.image.RenderedImage"), (continue.e == null) ? (continue.e = class$("java.lang.String")) : continue.e, (continue.mqa == null) ? (continue.mqa = class$("java.io.OutputStream")) : continue.mqa).invoke(null, bufferedImage, s, outputStream);
            }
            catch (Exception ex2) {
                ex = ex2;
            }
        }
        finally {
            if (graphics != null) {
                graphics.dispose();
            }
            this.repaint();
            if (ex != null) {
                throw ex;
            }
        }
    }
    
    public void D(final String rpa) {
        this.Rpa = rpa;
    }
    
    public void L(final boolean qpa) {
        this.Qpa = qpa;
    }
    
    public void E(final String spa) {
        this.Spa = spa;
    }
    
    public void F(final String tpa) {
        this.Tpa = tpa;
    }
    
    public void G(final String upa) {
        this.Upa = upa;
    }
    
    public synchronized void M(final boolean ppa) {
        this.Ppa = ppa;
    }
    
    public void N(final boolean xpa) {
        if (xpa != this.Xpa) {
            if (xpa) {
                this.tpa.add(this.Mpa);
            }
            else {
                this.tpa.remove(this.Mpa);
            }
            this.Xpa = xpa;
        }
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static void a(final continue continue1) {
        continue1.O();
    }
    
    static void _(final continue continue1) {
        continue1.N();
    }
    
    static void b(final continue continue1) {
        continue1.Q();
    }
    
    static void b(final continue continue1, final MouseEvent mouseEvent) {
        continue1.b(mouseEvent);
    }
    
    static boolean b(final continue continue1) {
        return continue1.Ypa;
    }
    
    static boolean _(final continue continue1) {
        return continue1.spa;
    }
    
    static int a(final continue continue1) {
        return continue1.Ooa;
    }
    
    static int b(final continue continue1, final int poa) {
        return continue1.Poa = poa;
    }
    
    static Rectangle[] a(final continue continue1) {
        return continue1._pa;
    }
    
    static int _(final continue continue1) {
        return continue1.Poa;
    }
    
    static Class a(final continue continue1) {
        return continue1.Moa;
    }
    
    static UserDrawTool b(final continue continue1, final UserDrawTool noa) {
        return continue1.Noa = noa;
    }
    
    static Color b(final continue continue1) {
        return continue1.mpa;
    }
    
    static UserDrawTool b(final continue continue1) {
        return continue1.Noa;
    }
    
    static int b(final continue continue1) {
        return continue1.npa;
    }
    
    static void _(final continue continue1, final boolean b) {
        continue1.J(b);
    }
    
    static Frame b(final continue continue1) {
        return continue1.a();
    }
    
    static o b(final continue continue1) {
        return continue1.Ca;
    }
    
    static Rectangle a(final continue continue1, final int n) {
        return continue1.b(n);
    }
    
    static Color a(final continue continue1) {
        return continue1.b();
    }
    
    static int b(final continue continue1, final int n, final int n2) {
        return continue1.n(n, n2);
    }
    
    static boolean a(final continue continue1) {
        return continue1.dpa;
    }
    
    static int _(final continue continue1, final double n, final double n2, final int n3) {
        return continue1.b(n, n2, n3);
    }
    
    static boolean m(final continue continue1) {
        return continue1.epa;
    }
    
    static void b(final continue continue1, final int n, final int n2) {
        continue1.j(n, n2);
    }
    
    static {
        continue.eqa = 6;
    }
}
