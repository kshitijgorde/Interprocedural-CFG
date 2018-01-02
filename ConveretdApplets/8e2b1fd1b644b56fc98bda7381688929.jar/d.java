import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Observable;
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

public class d extends Canvas implements Observer, ItemListener, ActionListener
{
    public static final int dta = 2;
    public static final int eta = 0;
    public static final int fta = 1;
    public static final int gta = 2;
    public static final int hta = 3;
    public static final int ita = 4;
    public static final int jta = 5;
    private int kta;
    private int lta;
    private g[] mta;
    public h q;
    public i[] r;
    public i[] s;
    private Vector[] nta;
    private Vector[] ota;
    private Vector[] pta;
    private Vector[] qta;
    private Class rta;
    private UserDrawTool sta;
    private int tta;
    private int uta;
    private boolean vta;
    private Color Asa;
    private Color wta;
    private Color xta;
    private Color yta;
    private Color lsa;
    private Color zta;
    private Dimension ea;
    private Graphics fa;
    private Image ga;
    private boolean[] Ata;
    private boolean[] Bta;
    private int Cta;
    private int Dta;
    private double[] Eta;
    private Rectangle[] Fta;
    private int Gta;
    private int Hta;
    private boolean[] Ita;
    private boolean Jta;
    private boolean Kta;
    private boolean Lta;
    private boolean Mta;
    private boolean Nta;
    private int Ota;
    private int Pta;
    private Color Qta;
    private Color Rta;
    private int Sta;
    public g v;
    public j w;
    public static final int Tta = 0;
    public static final int Uta = 1;
    private int Vta;
    private boolean Wta;
    private boolean Xta;
    private PopupMenu Yta;
    private switch xa;
    k Zta;
    k _ua;
    k aua;
    k bua;
    k cua;
    k dua;
    k eua;
    k fua;
    k gua;
    k hua;
    k iua;
    k jua;
    k kua;
    k lua;
    MenuItem mua;
    private g nua;
    private int oua;
    private boolean pua;
    private String qua;
    private String rua;
    private String sua;
    private String tua;
    private final byte[] uua;
    g vua;
    private boolean wua;
    int Hra;
    int Ira;
    private DecimalFormat xua;
    private DecimalFormat yua;
    private Image zua;
    private Graphics Aua;
    private int Bua;
    private int Cua;
    private static int Dua;
    private int x;
    static Class Eua;
    static Class Fua;
    static Class Gua;
    static Class Hua;
    static Class Iua;
    
    public d(final int n, final switch xa) {
        this.lta = 0;
        this.zta = Color.red;
        this.Ota = -1;
        this.Pta = -1;
        this.Sta = 1;
        this.Vta = 1;
        this.Wta = true;
        this.Xta = true;
        this.nua = new g();
        this.oua = Integer.MAX_VALUE;
        this.pua = true;
        this.qua = null;
        this.rua = null;
        this.sua = null;
        this.tua = null;
        this.uua = new byte[] { 0, 0, 0, 0, 0, 0, 115, -53, 115, -50, 72, 44, 42, 41, 86, -48, 85, 112, 45, 75, -52, 41, 77, 44, -55, -52, -49, 83, 40, 75, 45, 42, 6, -46, 0, 16, -7, 8, 122, 29, 0, 0, 0 };
        this.vua = new g();
        this.wua = false;
        this.Hra = 0;
        this.Ira = 0;
        this.xua = new DecimalFormat("#,##0.00");
        this.yua = new DecimalFormat("#,##0.00");
        this.zua = null;
        this.Aua = null;
        this.Bua = 0;
        this.Cua = 0;
        this.x = 0;
        if (n < 1) {
            throw new IllegalArgumentException("MaxPanelCount must be > 0");
        }
        if (xa == null) {
            throw new IllegalArgumentException("Resource cannot be NULL");
        }
        this.nua.K(1);
        this.nua.L(1);
        this.xa = xa;
        this.Dta = n;
        this.Cta = n;
        this.Eta = this.b(n);
        this.aa();
        this.q = new h();
        this.s = new i[this.Cta];
        this.r = new i[this.Cta];
        this.nta = new Vector[this.Cta];
        this.ota = new Vector[this.Cta];
        this.pta = new Vector[this.Cta];
        this.qta = new Vector[this.Cta];
        this.mta = new g[this.Cta];
        this.Ata = new boolean[this.Cta];
        this.Bta = new boolean[this.Cta];
        this.Fta = new Rectangle[this.Cta];
        this.Ita = new boolean[this.Cta];
        for (int i = 0; i < this.Cta; ++i) {
            this.r[i] = new i(1);
            this.s[i] = new i(0);
            this.nta[i] = new Vector();
            this.ota[i] = new Vector();
            this.pta[i] = new Vector();
            this.qta[i] = new Vector();
            (this.mta[i] = new g()).setFont(new Font("SansSerif", 0, 12));
            if (i > 0) {
                this.mta[i].J(true);
                this.mta[i].K(true);
            }
            this.Ata[i] = false;
            this.Bta[i] = false;
            this.s[i].lsa = null;
            this.r[i].lsa = null;
            this.Fta[i] = new Rectangle(0, 0, 0, 0);
            if (i == 0) {
                this.Ita[i] = true;
            }
            else {
                this.Ita[i] = false;
            }
        }
        this.Asa = Color.lightGray;
        this.wta = null;
        this.xta = null;
        this.yta = null;
        this.lsa = null;
        this.setBackground(this.Asa);
        this.kta = 2;
        this.ea = null;
        this.fa = null;
        this.ga = null;
        this.rta = ((d.Eua == null) ? (d.Eua = class$("TrendLine")) : d.Eua);
        this.sta = null;
        this.tta = 4;
        this.vta = true;
        this.Jta = true;
        (this.v = new g()).f(false);
        this.v.e(false);
        this.v.setColor(Color.red);
        (this.w = new j()).f(false);
        this.w.e(false);
        this.Qta = Color.blue;
        this.Rta = Color.green;
        this.ba();
        this.addMouseListener(new m(this));
        this.addMouseMotionListener(new n(this));
        this.M(true);
        this.N(true);
        this.O(true);
        this.P(true);
        this.Q(true);
    }
    
    private void ba() {
        this.Yta = new PopupMenu();
        (this.Zta = new k(this.xa.a("menuGridVisible"), true)).addItemListener(this);
        this.Yta.add(this.Zta._());
        (this._ua = new k(this.xa.a("menuTitleVisible"), true)).addItemListener(this);
        this.Yta.add(this._ua._());
        (this.aua = new k(this.xa.a("menuLegendVisible"), true)).addItemListener(this);
        this.Yta.add(this.aua._());
        (this.bua = new k(this.xa.a("menuCrosshairVisible"), true)).addItemListener(this);
        this.Yta.add(this.bua._());
        (this.cua = new k(this.xa.a("menuBuySellSignalsVisible"), true)).addItemListener(this);
        this.Yta.add(this.cua._());
        this.Yta.addSeparator();
        (this.dua = new k(this.xa.a("menuTrendLine"), true)).addItemListener(this);
        this.Yta.add(this.dua._());
        (this.eua = new k(this.xa.a("menuSupportResistance"), false)).addItemListener(this);
        this.Yta.add(this.eua._());
        (this.fua = new k(this.xa.a("menuFibonacciRetracements"), false)).addItemListener(this);
        this.Yta.add(this.fua._());
        (this.gua = new k(this.xa.a("menuRegularRetracements"), false)).addItemListener(this);
        this.Yta.add(this.gua._());
        (this.hua = new k(this.xa.a("menuTextTool"), false)).addItemListener(this);
        this.Yta.add(this.hua._());
        this.Yta.addSeparator();
        (this.iua = new k(this.xa.a("menuToolModeAuto"), true)).addItemListener(this);
        this.Yta.add(this.iua._());
        (this.jua = new k(this.xa.a("menuToolModeDraw"), false)).addItemListener(this);
        this.Yta.add(this.jua._());
        (this.kua = new k(this.xa.a("menuToolModeMove"), false)).addItemListener(this);
        this.Yta.add(this.kua._());
        (this.lua = new k(this.xa.a("menuToolModeDelete"), false)).addItemListener(this);
        this.Yta.add(this.lua._());
        this.Yta.addSeparator();
        if (k.qsa) {
            this.mua = new MenuItem(this.xa.a("menuDeleteAllTools"));
        }
        else {
            this.mua = new MenuItem("   " + this.xa.a("menuDeleteAllTools"));
        }
        this.mua.addActionListener(this);
        this.Yta.add(this.mua);
        this.add(this.Yta);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem && actionEvent.getSource() == this.mua) {
            this.t();
            this.repaint();
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.Zta._()) {
            this.P(this.Zta.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this._ua._()) {
            this.N(this._ua.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.aua._()) {
            this.O(this.aua.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.bua._()) {
            this.M(this.bua.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.cua._()) {
            this.Q(this.cua.getState());
            this.repaint();
        }
        else if (itemEvent.getSource() == this.dua._()) {
            this.b((d.Eua == null) ? (d.Eua = class$("TrendLine")) : d.Eua);
        }
        else if (itemEvent.getSource() == this.eua._()) {
            this.b((d.Fua == null) ? (d.Fua = class$("SupportResistance")) : d.Fua);
        }
        else if (itemEvent.getSource() == this.fua._()) {
            this.b((d.Gua == null) ? (d.Gua = class$("FibonacciRetracements")) : d.Gua);
        }
        else if (itemEvent.getSource() == this.gua._()) {
            this.b((d.Hua == null) ? (d.Hua = class$("RegularRetracements")) : d.Hua);
        }
        else if (itemEvent.getSource() == this.hua._()) {
            this.b((d.Iua == null) ? (d.Iua = class$("TextTool")) : d.Iua);
        }
        else if (itemEvent.getSource() == this.iua._()) {
            this.V(4);
        }
        else if (itemEvent.getSource() == this.jua._()) {
            this.V(1);
        }
        else if (itemEvent.getSource() == this.kua._()) {
            this.V(2);
        }
        else if (itemEvent.getSource() == this.lua._()) {
            this.V(3);
        }
    }
    
    public static void _(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
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
    
    public synchronized void j(final int cta) {
        if (cta < 1 || cta > this.Dta) {
            throw new IllegalArgumentException("Panel count must be in range: [1," + this.Dta + "]");
        }
        for (int i = cta + 1; i < this.Dta; ++i) {
            this.n(i);
            this.i(i);
        }
        this.Cta = cta;
        this.Eta = this.b(this.Cta);
        this.aa();
    }
    
    public synchronized void d(final boolean wta) {
        this.Wta = wta;
    }
    
    private void W(final int n) {
        if (n != 0 && n != 1) {
            throw new IllegalArgumentException("Axis must be: MultiChart.LETFT_AXIS or MultiChart.RIGHT_AXIS");
        }
    }
    
    private void aa() {
        double n = 0.0;
        for (int i = 0; i < this.Cta; ++i) {
            n += this.Eta[i];
        }
        if (n == 0.0) {
            n = 1.0;
        }
        for (int j = 0; j < this.Cta; ++j) {
            final double[] eta = this.Eta;
            final int n2 = j;
            eta[n2] /= n;
        }
    }
    
    public synchronized g b(final int n) {
        return this.mta[n];
    }
    
    public synchronized o a() {
        return this.q._();
    }
    
    public synchronized o _(final int n) {
        return this.a(n, this.Vta);
    }
    
    public synchronized o a(final int n, final int n2) {
        this.W(n2);
        if (n2 == 0) {
            return this.s[n]._();
        }
        return this.r[n]._();
    }
    
    public void _(final int n, final String text) {
        this.mta[n].setText(text);
    }
    
    public synchronized Color b() {
        return this.Asa;
    }
    
    public synchronized void a(final Color asa) {
        this.setBackground(this.Asa = asa);
        this.vua.a(asa);
    }
    
    public synchronized void f(final Color wta) {
        this.wta = wta;
    }
    
    public synchronized void g(final Color xta) {
        this.xta = xta;
    }
    
    public synchronized void c(final Color yta) {
        this.yta = yta;
    }
    
    public synchronized void d(final Color lsa) {
        this.lsa = lsa;
        this.q.lsa = lsa;
        for (int i = 0; i < this.m(); ++i) {
            this.s[i].lsa = null;
            this.r[i].lsa = lsa;
        }
    }
    
    public synchronized void P(final boolean osa) {
        this.q.osa = osa;
        for (int i = 0; i < this.m(); ++i) {
            this.s[i].osa = osa;
            this.r[i].osa = osa;
        }
        this.Zta.setState(this.q.osa);
    }
    
    public synchronized boolean N() {
        return this.q.osa;
    }
    
    public synchronized void h(final Color zta) {
        this.zta = zta;
    }
    
    public synchronized void _(final int n, final p p2) {
        this._(n, this.Vta, p2);
    }
    
    public synchronized void _(final int n, final int n2, final p p3) {
        this.W(n2);
        if (n2 == 0) {
            this.nta[n].addElement(p3);
        }
        else {
            this.ota[n].addElement(p3);
        }
    }
    
    public synchronized p _(final int n, final int n2) {
        return this._(n, this.Vta, n2);
    }
    
    public synchronized p _(final int n, final int n2, final int n3) {
        this.W(n2);
        if (n2 == 0) {
            return this.nta[n].elementAt(n3);
        }
        return this.ota[n].elementAt(n3);
    }
    
    public synchronized void b(final p p) {
        for (int i = 0; i < this.n(); ++i) {
            if (this.nta[i].contains(p)) {
                this.nta[i].removeElement(p);
            }
            if (this.ota[i].contains(p)) {
                this.ota[i].removeElement(p);
            }
        }
    }
    
    public synchronized void a(final int n, final p p2) {
        if (this.nta[n].contains(p2)) {
            this.nta[n].removeElement(p2);
        }
        if (this.ota[n].contains(p2)) {
            this.ota[n].removeElement(p2);
        }
    }
    
    public synchronized void n(final int n) {
        this.nta[n].removeAllElements();
        this.ota[n].removeAllElements();
    }
    
    public synchronized void c(final int n, final int n2) {
        this.W(n2);
        if (n2 == 0) {
            this.nta[n].removeAllElements();
        }
        if (n2 == 1) {
            this.ota[n].removeAllElements();
        }
    }
    
    public synchronized void i(final int n) {
        this.pta[n].removeAllElements();
        this.qta[n].removeAllElements();
    }
    
    public synchronized void t() {
        for (int i = 0; i < this.m(); ++i) {
            this.i(i);
        }
    }
    
    public synchronized void d(final int n, final int n2) {
        this.W(n2);
        if (n2 == 0) {
            this.pta[n].removeAllElements();
        }
        if (n2 == 1) {
            this.qta[n].removeAllElements();
        }
    }
    
    public synchronized int a(final int n) {
        return this.b(n, this.Vta);
    }
    
    public synchronized int b(final int n, final int n2) {
        this.W(n2);
        if (n2 == 0) {
            return this.nta[n].size();
        }
        return this.ota[n].size();
    }
    
    public synchronized void j(final Color color) {
        this.Rta = color;
        for (int i = 0; i < this.m(); ++i) {
            for (int j = 0; j < this.pta[i].size(); ++j) {
                ((UserDrawTool)this.pta[i].elementAt(j)).setColor(color);
            }
            for (int k = 0; k < this.qta[i].size(); ++k) {
                ((UserDrawTool)this.qta[i].elementAt(k)).setColor(color);
            }
        }
    }
    
    public synchronized void v(final int sta) {
        this.Sta = sta;
        for (int i = 0; i < this.m(); ++i) {
            for (int j = 0; j < this.pta[i].size(); ++j) {
                ((UserDrawTool)this.pta[i].elementAt(j)).I(sta);
            }
            for (int k = 0; k < this.qta[i].size(); ++k) {
                ((UserDrawTool)this.qta[i].elementAt(k)).I(sta);
            }
        }
    }
    
    public synchronized void a(final int n, final UserDrawTool userDrawTool) {
        this.b(n, this.Vta, userDrawTool);
    }
    
    public synchronized void b(final int n, final int n2, final UserDrawTool userDrawTool) {
        this.W(n2);
        if (n2 == 0) {
            this.pta[n].addElement(userDrawTool);
        }
        if (n2 == 1) {
            this.qta[n].addElement(userDrawTool);
        }
    }
    
    public synchronized q[] _() {
        final Vector vector = new Vector<q>();
        for (int i = 0; i < this.n(); ++i) {
            for (int j = 0; j < this._(i); ++j) {
                vector.addElement(new q(i, this.b(i, j)));
            }
        }
        final q[] array = new q[vector.size()];
        for (int k = 0; k < array.length; ++k) {
            array[k] = vector.elementAt(k);
        }
        return array;
    }
    
    public synchronized void b(final q[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null && array[i].A < this.Dta) {
                this.a(array[i].A, array[i].wra);
            }
        }
    }
    
    public synchronized UserDrawTool b(final int n, final int n2) {
        return this._(n, this.Vta, n2);
    }
    
    public synchronized UserDrawTool _(final int n, final int n2, final int n3) {
        this.W(n2);
        if (n2 == 0) {
            return this.pta[n].elementAt(n3);
        }
        return this.qta[n].elementAt(n3);
    }
    
    public synchronized void a(final UserDrawTool userDrawTool) {
        for (int i = 0; i < this.m(); ++i) {
            if (this.pta[i].contains(userDrawTool)) {
                this.pta[i].removeElement(userDrawTool);
            }
            if (this.qta[i].contains(userDrawTool)) {
                this.qta[i].removeElement(userDrawTool);
            }
        }
    }
    
    public synchronized void b(final int n, final UserDrawTool userDrawTool) {
        if (this.pta[n].contains(userDrawTool)) {
            this.pta[n].removeElement(userDrawTool);
        }
        if (this.qta[n].contains(userDrawTool)) {
            this.qta[n].removeElement(userDrawTool);
        }
    }
    
    public synchronized int _(final int n) {
        return this.a(n, this.Vta);
    }
    
    public synchronized int a(final int n, final int n2) {
        this.W(n2);
        if (n2 == 0) {
            return this.pta[n].size();
        }
        return this.qta[n].size();
    }
    
    public synchronized int n() {
        return this.Cta;
    }
    
    public synchronized int m() {
        return this.Dta;
    }
    
    public synchronized void b(final Class rta) {
        this.rta = rta;
        if (rta == ((d.Eua == null) ? (d.Eua = class$("TrendLine")) : d.Eua)) {
            this.dua.setState(true);
            this.eua.setState(false);
            this.fua.setState(false);
            this.gua.setState(false);
            this.hua.setState(false);
        }
        if (rta == ((d.Fua == null) ? (d.Fua = class$("SupportResistance")) : d.Fua)) {
            this.dua.setState(false);
            this.eua.setState(true);
            this.fua.setState(false);
            this.gua.setState(false);
            this.hua.setState(false);
        }
        else if (rta == ((d.Gua == null) ? (d.Gua = class$("FibonacciRetracements")) : d.Gua)) {
            this.dua.setState(false);
            this.eua.setState(false);
            this.fua.setState(true);
            this.gua.setState(false);
            this.hua.setState(false);
        }
        else if (rta == ((d.Hua == null) ? (d.Hua = class$("RegularRetracements")) : d.Hua)) {
            this.dua.setState(false);
            this.eua.setState(false);
            this.fua.setState(false);
            this.gua.setState(true);
            this.hua.setState(false);
        }
        else if (rta == ((d.Iua == null) ? (d.Iua = class$("TextTool")) : d.Iua)) {
            this.dua.setState(false);
            this.eua.setState(false);
            this.fua.setState(false);
            this.gua.setState(false);
            this.hua.setState(true);
        }
    }
    
    public void A() {
        this.x = 0;
        this.repaint();
    }
    
    public void g(final boolean wua) {
        this.wua = wua;
        this.x = 0;
    }
    
    public void v() {
        this.Xta = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void X(final int n) {
        int y = n;
        if (y == 0) {
            if (this.Jta) {
                y += Math.max(d.Dua, this.v._(this.getGraphics())) + 3;
            }
            else {
                y += d.Dua + 2;
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
        for (int i = 0; i < this.Cta; ++i) {
            this.Fta[i].x = 1;
            this.Fta[i].y = y;
            this.Fta[i].width = width;
            this.Fta[i].height = (int)(this.Eta[i] * n2 + 0.5);
            y += this.Fta[i].height;
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int width, int n4) {
        int y = n3 + n + 4;
        n4 = n4 - n - 4;
        if (n4 < 0) {
            n4 = 0;
        }
        for (int i = 0; i < this.Cta; ++i) {
            this.Fta[i].x = n2 + 1;
            this.Fta[i].y = y;
            this.Fta[i].width = width;
            this.Fta[i].height = (int)(this.Eta[i] * n4 + 0.5);
            y += this.Fta[i].height;
        }
    }
    
    private double[] aa() {
        final int n = this.b(0, 1) + this.b(0, 0);
        final p[] array = new p[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).b() <= this._(0, 1, i)._()) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = array[n2].b());
                array4[n2] = array[n2]._();
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
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
        final r r = new r((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double f = array[l].f(array2[l]);
                    if (f < n5) {
                        n5 = f;
                    }
                }
            }
            if (!b) {
                break;
            }
            r.a(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].f(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return r.Y();
    }
    
    public synchronized double[] d() {
        final int n = this.b(0, 1) + this.b(0, 0);
        final p[] array = new p[n];
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        int n2 = 0;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).H() >= 0) {
                array[n2] = this._(0, 1, i);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].H();
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
            if (this._(0, 0, j).H() >= 0) {
                array[n2] = this._(0, 0, j);
                array2[n2] = (array3[n2] = 0);
                array4[n2] = array[n2].H();
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
        final r r = new r((int)(n3 * 1.1));
        while (true) {
            double n5 = Double.POSITIVE_INFINITY;
            boolean b = false;
            for (int l = 0; l < n2; ++l) {
                if (array2[l] >= array3[l] && array2[l] <= array4[l]) {
                    b = true;
                    final double f = array[l].f(array2[l]);
                    if (f < n5) {
                        n5 = f;
                    }
                }
            }
            if (!b) {
                break;
            }
            r.a(n5);
            for (int n6 = 0; n6 < n2; ++n6) {
                while (array2[n6] >= array3[n6] && array2[n6] <= array4[n6] && array[n6].f(array2[n6]) <= n5) {
                    final int[] array5 = array2;
                    final int n7 = n6;
                    ++array5[n7];
                }
            }
        }
        return r.Y();
    }
    
    private static byte a(final byte[] array, final int n) {
        if (array == null || n > array.length - 1) {
            return 0;
        }
        return array[n];
    }
    
    private void ca() {
        this.nua.setText(super.a(this.uua));
        final super a = this.a();
        if (a != null) {
            final byte[] a2 = a.a(this.qua);
            final byte[] b = a.b("" + a.getName() + " " + a.G() + "  " + this.sua);
            int n = 0;
            for (int i = 0; i < Math.max(a2.length, b.length); ++i) {
                n += Math.abs(a(a2, i) - a(b, i));
            }
            final byte[] a3 = a.a(this.rua);
            final byte[] b2 = a.b("" + a.getName() + " " + a.G() + "  " + this.tua);
            int n2 = 0;
            for (int j = 0; j < Math.max(a3.length, b2.length); ++j) {
                n2 += Math.abs(a(a3, j) - a(b2, j));
            }
            this.oua = Math.min(n, n2);
        }
    }
    
    public synchronized double[] e() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.b(0, 1); ++i) {
            if (this._(0, 1, i).b() <= this._(0, 1, i)._()) {
                n = Math.min(n, this._(0, 1, i).f());
                n2 = Math.max(n2, this._(0, 1, i).g());
            }
        }
        for (int j = 0; j < this.b(0, 0); ++j) {
            if (this._(0, 0, j).b() <= this._(0, 0, j)._()) {
                n = Math.min(n, this._(0, 0, j).f());
                n2 = Math.max(n2, this._(0, 0, j).g());
            }
        }
        if (n == Double.POSITIVE_INFINITY || n == Double.NEGATIVE_INFINITY || n2 == Double.POSITIVE_INFINITY || n2 == Double.NEGATIVE_INFINITY) {
            return null;
        }
        return new double[] { n, n2 };
    }
    
    private int a(final Graphics graphics, final int n) {
        int n2 = 0;
        for (int i = 0; i < this.b(0, 0); ++i) {
            final p _ = this._(0, 0, i);
            if (_.getName() != null && _.getName().length() > 0) {
                ++n2;
            }
        }
        for (int j = 0; j < this.b(0, 1); ++j) {
            final p _2 = this._(0, 1, j);
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
            final p _3 = this._(0, 0, k);
            if (_3.getName() != null && _3.getName().length() > 0) {
                if (_3 instanceof s) {
                    array[n3] = ((s)_3).X();
                }
                else if (_3 instanceof t) {
                    array[n3] = ((t)_3).X();
                }
                else {
                    array[n3] = 0;
                }
                array2[n3] = _3.getName();
                array3[n3] = _3.T;
                ++n3;
            }
        }
        for (int l = 0; l < this.b(0, 1); ++l) {
            final p _4 = this._(0, 1, l);
            if (_4.getName() != null && _4.getName().length() > 0) {
                if (_4 instanceof s) {
                    array[n3] = ((s)_4).X();
                }
                else if (_4 instanceof t) {
                    array[n3] = ((t)_4).X();
                }
                else {
                    array[n3] = 0;
                }
                array2[n3] = _4.getName();
                array3[n3] = _4.T;
                ++n3;
            }
        }
        this.w.K(0);
        this.w.L(3);
        final int n4 = n + 5;
        final int y = this.Fta[0].y;
        int n5 = n4;
        int n6 = y;
        int max = 0;
        int max2 = 0;
        int n7 = 0;
        for (int n8 = 0; n8 < array2.length; ++n8) {
            this.w.setText(array2[n8]);
            this.w.I(array[n8]);
            this.w.setColor(array3[n8]);
            final int b = this.w.b(graphics);
            if (n5 > n4 && n5 + b >= this.Fta[0].width - 5) {
                n5 = n4;
                n6 += max;
            }
            max = Math.max(max, this.w._(graphics));
            n5 += this.w.b(graphics) + 2;
            max2 = Math.max(max2, n5);
            n7 = n6 + max;
        }
        if (this.yta != null) {
            graphics.setColor(this.yta);
        }
        else {
            graphics.setColor(this.Asa);
        }
        graphics.fillRect(n4, y, max2 - n4, n7 - y);
        graphics.setColor(this.q.T);
        graphics.drawRect(n4, y, max2 - n4, n7 - y);
        int n9 = n4;
        int n10 = y;
        int max3 = 0;
        for (int n11 = 0; n11 < array2.length; ++n11) {
            this.w.setText(array2[n11]);
            this.w.I(array[n11]);
            this.w.setColor(array3[n11]);
            final int b2 = this.w.b(graphics);
            if (n9 > n4 && n9 + b2 >= this.Fta[0].width - 5) {
                n9 = n4;
                n10 += max3;
            }
            this.w.a(graphics, n9, n10);
            max3 = Math.max(max3, this.w._(graphics));
            n9 += this.w.b(graphics) + 2;
        }
        return n7;
    }
    
    private synchronized void _(final Graphics graphics) {
        if (this.q._() instanceof u && this.Xta) {
            this.y();
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setClip(0, 0, width, height);
        this.f(graphics);
        if (this.wta != null) {
            graphics.setColor(this.wta);
            graphics.drawLine(0, 0, width - 1, 0);
            graphics.drawLine(0, 0, 0, height - 1);
        }
        if (this.xta != null) {
            graphics.setColor(this.xta);
            graphics.drawLine(0, height - 1, width - 1, height - 1);
            graphics.drawLine(width - 1, 0, width - 1, height - 1);
        }
        this.X(0);
        if (this.Nta) {
            this.X(this.a(graphics, 0));
        }
        this.da();
        this.g(graphics);
        if (this.oua == Integer.MAX_VALUE) {
            this.ca();
        }
        for (int i = 0; i < this.Cta; ++i) {
            this._(i, graphics);
            this.a(i, graphics);
            this.b(i, graphics);
            for (int j = 0; j < this.oua; ++j) {
                if (i > 0) {
                    break;
                }
                this.nua.a(graphics, this.Fta[i].x + this.Fta[i].width / 2, this.Fta[i].y + this.Fta[i].height / 2);
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
            if (this.sta != null) {
                this.sta.b(graphics, this.a(this.uta), this.Asa, this.a(), this._(this.uta));
                this.sta.a(this.Hra, this.Ira, this.a(), this._(this.uta));
            }
            if (this.vta && this.q._() instanceof u && ((u)this.q._()).W() > 1) {
                this.f(i, graphics);
            }
        }
        this.ea();
    }
    
    public synchronized void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.y();
        graphics.setClip(n, n2, n3, n4);
        graphics.setColor(this.Asa);
        graphics.fillRect(n, n2, n3, n4);
        this.a(graphics, 0, n, n2, n3, n4);
        if (this.Nta) {
            this.a(graphics, this.a(graphics, n) - n2, n, n2, n3, n4);
        }
        this.da();
        this.b(graphics, n, n3);
        if (this.oua == Integer.MAX_VALUE) {
            this.ca();
        }
        for (int i = 0; i < this.Cta; ++i) {
            this._(i, graphics);
            this.a(i, graphics);
            this.b(i, graphics);
            for (int j = 0; j < this.oua; ++j) {
                if (i > 0) {
                    break;
                }
                this.nua.a(graphics, this.Fta[i].x + this.Fta[i].width / 2, this.Fta[i].y + this.Fta[i].height / 2);
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
            if (this.vta && this.q._() instanceof u && ((u)this.q._()).W() > 1) {
                this.f(i, graphics);
            }
        }
        this.Xta = true;
    }
    
    public synchronized void y() {
        final o _ = this.q._();
        if (_ instanceof u) {
            ((u)_).b(this.aa());
            this.Xta = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.wua) {
            if (this.fa != null && this.ga != null) {
                graphics.drawImage(this.ga, 0, 0, this);
                if (this.x > 0) {
                    this.b(graphics, this.x);
                }
            }
            else {
                graphics.setColor(this.Asa);
                graphics.setClip(0, 0, size.width, size.height);
                this.f(graphics);
                if (this.wta != null) {
                    graphics.setColor(this.wta);
                    graphics.drawLine(0, 0, size.width - 1, 0);
                    graphics.drawLine(0, 0, 0, size.height - 1);
                }
                if (this.xta != null) {
                    graphics.setColor(this.xta);
                    graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
                    graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                }
                if (this.x > 0) {
                    this.b(graphics, this.x);
                }
            }
        }
        else if (size.width > 0 && size.height > 0) {
            if (this.ga == null || this.fa == null || this.ea == null || size.width != this.ea.width || size.height != this.ea.height) {
                if (this.fa != null) {
                    this.fa.dispose();
                }
                if (this.ga != null) {
                    this.ga.flush();
                }
                this.ga = this.createImage(size.width, size.height);
                this.fa = this.ga.getGraphics();
                this.ea = size;
            }
            this._(this.fa);
            graphics.drawImage(this.ga, 0, 0, this);
            if (this.x > 0) {
                this.b(graphics, this.x);
            }
        }
    }
    
    private void a(final int n, final Graphics graphics) {
        final int y = this.Fta[n].y;
        final int width = this.Fta[n].width;
        final int height = this.Fta[n].height;
        final int b = this.mta[n].b(graphics);
        final int _ = this.mta[n]._(graphics);
        if (this.mta[n].i()) {
            this.lta = 0;
        }
        else {
            this.lta = 1;
        }
        graphics.setClip(this.Fta[n]);
        if ((this.Mta || this.a(0) == 0) && !this.mta[n].isEmpty()) {
            final String text = this.mta[n].getText();
            if (this.b(n, 1) >= 0) {
                this.mta[n].setText(text + this.i(n));
            }
            switch (this.kta) {
                case 2: {
                    this.mta[n].K(0);
                    this.mta[n].L(3);
                    if (this.b(0, 0) == 0 && this.b(0, 1) == 0) {
                        final int n2 = (width - b) / 2;
                        if (this.yta != null) {
                            graphics.setColor(this.yta);
                            graphics.fillRect(n2, y, b + 2 * this.lta, _ + 2 * this.lta);
                        }
                        graphics.setColor(this.q.T);
                        graphics.drawRect(n2, y, b + 2 * this.lta, _ + 2 * this.lta);
                        this.mta[n].n(n2 + this.lta, y + this.lta);
                        break;
                    }
                    this.mta[n].n(this.s[n].ea() + this.lta, y + this.lta);
                    break;
                }
            }
            this.mta[n].a(graphics);
            this.mta[n].setText(text);
        }
    }
    
    private void f(final Graphics graphics) {
        graphics.setColor(this.Asa);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    
    private void da() {
        double n = Double.POSITIVE_INFINITY;
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.Cta; ++i) {
            double n4;
            double n3 = n4 = Double.POSITIVE_INFINITY;
            double n6;
            double n5 = n6 = Double.NEGATIVE_INFINITY;
            if (this.nta[i].size() != 0 || this.ota[i].size() != 0) {
                if (this.q.h()) {
                    n = Math.min(n, this.g(i));
                    n2 = Math.max(n2, this.h(i));
                }
                else {
                    n = this.q.h();
                    n2 = this.q.i();
                }
                if (!this.s[i].h()) {
                    n4 = this.s[i].h();
                    n6 = this.s[i].i();
                }
                if (!this.r[i].h()) {
                    n3 = this.r[i].h();
                    n5 = this.r[i].i();
                }
                if (this.nta[i].size() > 0 && this.s[i].h()) {
                    n4 = Math.min(n4, this._(i, 0));
                    n6 = Math.max(n6, this.l(i, 0));
                    this.s[i]._(n4, n6);
                }
                if (this.ota[i].size() > 0 && this.r[i].h()) {
                    n3 = Math.min(n3, this._(i, 1));
                    n5 = Math.max(n5, this.l(i, 1));
                    this.r[i]._(n3, n5);
                }
                if (this.Wta && i == 0 && this.nta[i].size() > 0 && this.ota[i].size() > 0) {
                    final double min = Math.min(n4, n3);
                    final double max = Math.max(n6, n5);
                    this.s[i]._(min, max);
                    this.r[i]._(min, max);
                }
                else if (n4 > n6 || n3 > n5) {
                    if (n4 > n6) {
                        this.s[i]._(1.0, 10.0);
                    }
                    if (n3 > n5) {
                        this.r[i]._(1.0, 10.0);
                    }
                }
            }
        }
        if (n > n2) {
            n = 1.0;
            n2 = 100.0;
        }
        this.q._(n, n2);
    }
    
    private void e(final int n, final int n2) {
        for (int i = 0; i < this.m(); ++i) {
            for (int j = 0; j < this.pta[i].size(); ++j) {
                final UserDrawTool userDrawTool = this.pta[i].elementAt(j);
                if (userDrawTool instanceof SupportResistance) {
                    ((SupportResistance)userDrawTool).m(n, n2);
                }
            }
            for (int k = 0; k < this.qta[i].size(); ++k) {
                final UserDrawTool userDrawTool2 = this.qta[i].elementAt(k);
                if (userDrawTool2 instanceof SupportResistance) {
                    ((SupportResistance)userDrawTool2).m(n, n2);
                }
            }
        }
    }
    
    private void _(final int n, final Graphics graphics) {
        graphics.setClip(this.Fta[n]);
        this.Ata[n] = (this.Bta[n] = false);
        if (this.nta[n].size() == 0 && this.ota[n].size() == 0) {
            return;
        }
        this.g(n, graphics);
        this.q.msa = this.r[n].fa() + 1;
        this.q.nsa = this.r[n].ga() - 1;
        this.s[n].msa = this.q.Z() + 1;
        this.s[n].nsa = this.q._a() - 1;
        this.r[n].msa = this.q.Z() + 1;
        this.r[n].nsa = this.q._a() - 1;
        this.e(this.q.Z() + 1, this.q._a() - 1);
        graphics.setColor(this.q.T);
        graphics.drawRect(this.s[n].ea(), this.s[n].fa(), this.r[n].ea() - this.s[n].ea(), this.r[n].ga() - this.s[n].fa());
        if (this.yta != null) {
            graphics.setColor(this.yta);
            graphics.fillRect(this.s[n].ea() + 1, this.s[n].fa() + 1, this.r[n].ea() - this.s[n].ea() - 1, this.r[n].ga() - this.s[n].fa() - 1);
        }
        this.r[n].N(0);
        this.s[n].N(0);
        if (this.Mta && this.b(n).M()) {
            int n2;
            if (this.mta[n].i()) {
                n2 = 0;
            }
            else {
                n2 = 1;
            }
            final int n3 = n2 + this.b(n)._(graphics);
            this.r[n].N(n3);
            this.s[n].N(n3);
        }
        if (this.nta[n].size() > 0) {
            this.Ata[n] = this.s[n].a(graphics);
        }
        if (this.ota[n].size() > 0) {
            this.Bta[n] = this.r[n].a(graphics);
        }
        final boolean a = this.q.a(graphics);
        this.Ata[n] = (this.Ata[n] && a);
        this.Bta[n] = (this.Bta[n] && a);
    }
    
    private void g(final Graphics graphics) {
        this.Hta = 5;
        for (int i = 0; i < this.Cta; ++i) {
            this.s[i].setBounds(this.Fta[i]);
            if (this.b(i, 0) > 0) {
                this.Hta = Math.max(this.Hta, this.s[i].d(graphics));
            }
        }
        this.Gta = this.getSize().width - 5;
        for (int j = 0; j < this.Cta; ++j) {
            this.r[j].setBounds(this.Fta[j]);
            if (this.b(j, 1) > 0) {
                this.Gta = Math.min(this.Gta, this.r[j].d(graphics));
            }
        }
    }
    
    private void b(final Graphics graphics, final int n, final int n2) {
        this.Hta = n + 5;
        for (int i = 0; i < this.Cta; ++i) {
            this.s[i].setBounds(this.Fta[i]);
            if (this.b(i, 0) > 0) {
                this.Hta = Math.max(this.Hta, this.s[i].d(graphics));
            }
        }
        this.Gta = n + n2 - 5;
        for (int j = 0; j < this.Cta; ++j) {
            this.r[j].setBounds(this.Fta[j]);
            if (this.b(j, 1) > 0) {
                this.Gta = Math.min(this.Gta, this.r[j].d(graphics));
            }
        }
    }
    
    private void g(final int n, final Graphics graphics) {
        this.q.H(this.Ita[n]);
        this.q.I(this.Ita[n]);
        this.q.setBounds(this.Fta[n]);
        this.s[n].setBounds(this.Fta[n]);
        this.r[n].setBounds(this.Fta[n]);
        switch (this.q.aa()) {
            case 0: {
                this.s[n].P(this.q.j(graphics));
                this.r[n].P(this.q.j(graphics));
                this.s[n].Q(this.Fta[n].y + this.Fta[n].height - 1);
                this.r[n].Q(this.Fta[n].y + this.Fta[n].height - 1);
                break;
            }
            case 1: {
                this.s[n].Q(this.q.j(graphics));
                this.r[n].Q(this.q.j(graphics));
                this.s[n].P(this.Fta[n].y);
                this.r[n].P(this.Fta[n].y);
                break;
            }
        }
        this.q.S(this.Hta);
        this.q.T(this.Gta);
        this.s[n].R(this.Hta);
        this.r[n].R(this.Gta);
    }
    
    private double h(final int n) {
        double n2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < this.nta[n].size(); ++i) {
            n2 = Math.max(n2, ((p)this.nta[n].elementAt(i)).b());
        }
        for (int j = 0; j < this.ota[n].size(); ++j) {
            n2 = Math.max(n2, ((p)this.ota[n].elementAt(j)).b());
        }
        return n2;
    }
    
    private double g(final int n) {
        double n2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.nta[n].size(); ++i) {
            n2 = Math.min(n2, ((p)this.nta[n].elementAt(i))._());
        }
        for (int j = 0; j < this.ota[n].size(); ++j) {
            n2 = Math.min(n2, ((p)this.ota[n].elementAt(j))._());
        }
        return n2;
    }
    
    private double l(final int n, final int n2) {
        double n3 = Double.NEGATIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.nta[n].size(); ++i) {
                n3 = Math.max(n3, ((p)this.nta[n].elementAt(i)).a());
            }
        }
        else {
            for (int j = 0; j < this.ota[n].size(); ++j) {
                n3 = Math.max(n3, ((p)this.ota[n].elementAt(j)).a());
            }
        }
        return n3;
    }
    
    private double _(final int n, final int n2) {
        double n3 = Double.POSITIVE_INFINITY;
        if (n2 == 0) {
            for (int i = 0; i < this.nta[n].size(); ++i) {
                n3 = Math.min(n3, ((p)this.nta[n].elementAt(i)).k());
            }
        }
        else {
            for (int j = 0; j < this.ota[n].size(); ++j) {
                n3 = Math.min(n3, ((p)this.ota[n].elementAt(j)).k());
            }
        }
        return n3;
    }
    
    private void b(final int n, final Graphics graphics) {
        if (!this.Ata[n] && !this.Bta[n]) {
            return;
        }
        final int z = this.q.Z();
        final int a = this.q._a();
        if (n == 1) {
            if (this.Bta[n]) {
                final int fa = this.r[n].fa();
                graphics.setClip(z + 1, fa + 1, a - z, this.r[n].ga() - fa);
                for (int i = 0; i < this.ota[n].size(); ++i) {
                    final p p2 = this.ota[n].elementAt(i);
                    if (!(p2 instanceof v) || (p2 instanceof v && this.Lta)) {
                        p2.b(graphics, this.a(), this.a(n, 1));
                    }
                }
            }
            if (this.Ata[n]) {
                final int fa2 = this.s[n].fa();
                graphics.setClip(z + 1, fa2 + 1, a - z, this.s[n].ga() - fa2);
                for (int j = 0; j < this.nta[n].size(); ++j) {
                    final p p3 = this.nta[n].elementAt(j);
                    if (!(p3 instanceof v) || (p3 instanceof v && this.Lta)) {
                        p3.b(graphics, this.a(), this.a(n, 0));
                    }
                }
            }
        }
        else {
            if (this.Ata[n]) {
                final int fa3 = this.s[n].fa();
                graphics.setClip(z + 1, fa3 + 1, a - z, this.s[n].ga() - fa3);
                for (int k = 0; k < this.nta[n].size(); ++k) {
                    final p p4 = this.nta[n].elementAt(k);
                    if (!(p4 instanceof v) || (p4 instanceof v && this.Lta)) {
                        p4.b(graphics, this.a(), this.a(n, 0));
                    }
                }
            }
            if (this.Bta[n]) {
                final int fa4 = this.r[n].fa();
                graphics.setClip(z + 1, fa4 + 1, a - z, this.r[n].ga() - fa4);
                for (int l = 0; l < this.ota[n].size(); ++l) {
                    final p p5 = this.ota[n].elementAt(l);
                    if (!(p5 instanceof v) || (p5 instanceof v && this.Lta)) {
                        p5.b(graphics, this.a(), this.a(n, 1));
                    }
                }
            }
        }
    }
    
    private Rectangle a(final int n) {
        if (this.pua) {
            return new Rectangle(this.s[n].ea(), this.s[n].fa(), this.r[n].ea() - this.s[n].ea() + 1, this.r[n].ga() - this.s[n].fa() + 1);
        }
        return this.Fta[n];
    }
    
    private void f(final int n, final Graphics graphics) {
        if (!this.Ata[n] && !this.Bta[n]) {
            return;
        }
        graphics.setClip(this.a(n));
        if (this.Ata[n]) {
            for (int i = 0; i < this.pta[n].size(); ++i) {
                ((UserDrawTool)this.pta[n].elementAt(i))._(graphics, this.a(n), this.Asa, this.a(), this.a(n, 0));
            }
        }
        if (this.Bta[n]) {
            for (int j = 0; j < this.qta[n].size(); ++j) {
                ((UserDrawTool)this.qta[n].elementAt(j))._(graphics, this.a(n), this.Asa, this.a(), this.a(n, 1));
            }
        }
    }
    
    public synchronized void n(final double n, final double n2) {
        for (int i = 0; i < this.Cta; ++i) {
            for (int j = 0; j < this.nta[i].size(); ++j) {
                ((p)this.nta[i].elementAt(j)).c(n, n2);
            }
            for (int k = 0; k < this.ota[i].size(); ++k) {
                ((p)this.ota[i].elementAt(k)).c(n, n2);
            }
        }
    }
    
    public synchronized void k(final boolean b) {
        for (int i = 0; i < this.Cta; ++i) {
            for (int j = 0; j < this.nta[i].size(); ++j) {
                ((p)this.nta[i].elementAt(j)).F(b);
            }
            for (int k = 0; k < this.ota[i].size(); ++k) {
                ((p)this.ota[i].elementAt(k)).F(b);
            }
        }
    }
    
    private void fa() {
        switch (this.tta) {
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
    
    public void V(final int tta) {
        switch (this.tta = tta) {
            case 4: {
                this.iua.setState(true);
                this.jua.setState(false);
                this.kua.setState(false);
                this.lua.setState(false);
                break;
            }
            case 1: {
                this.iua.setState(false);
                this.jua.setState(true);
                this.kua.setState(false);
                this.lua.setState(false);
                break;
            }
            case 2: {
                this.iua.setState(false);
                this.jua.setState(false);
                this.kua.setState(true);
                this.lua.setState(false);
                break;
            }
            case 3: {
                this.iua.setState(false);
                this.jua.setState(false);
                this.kua.setState(false);
                this.lua.setState(true);
                break;
            }
        }
        this.fa();
    }
    
    private synchronized void _(final MouseEvent mouseEvent) {
        if (this.Yta != null) {
            this.Yta.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
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
    
    private super a() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof super); container = container.getParent()) {}
        if (container instanceof super) {
            return (super)container;
        }
        return null;
    }
    
    private void R(final boolean b) {
        final super a = this.a();
        if (a != null) {
            a.a(b);
        }
    }
    
    private int d(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < this.n(); ++i) {
            if (n2 >= this.Fta[i].y && n2 <= this.Fta[i].y + this.Fta[i].height) {
                n3 = i;
            }
        }
        if (n3 == -1 || this.a(n3) == 0) {
            return -1;
        }
        return n3;
    }
    
    public synchronized void S(final boolean jta) {
        this.Jta = jta;
    }
    
    public synchronized void M(final boolean kta) {
        this.Kta = kta;
        this.bua.setState(this.Kta);
    }
    
    public synchronized boolean O() {
        return this.Kta;
    }
    
    public synchronized void Q(final boolean lta) {
        this.Lta = lta;
        this.cua.setState(this.Lta);
    }
    
    public synchronized boolean P() {
        return this.Lta;
    }
    
    public synchronized void N(final boolean mta) {
        this.Mta = mta;
        this._ua.setState(this.Mta);
    }
    
    public synchronized void O(final boolean nta) {
        this.Nta = nta;
        this.aua.setState(this.Nta);
    }
    
    public synchronized boolean Q() {
        return this.Mta;
    }
    
    public synchronized boolean R() {
        return this.Nta;
    }
    
    public synchronized void i(final Color qta) {
        this.Qta = qta;
    }
    
    private void f(final int n, final int pta) {
        final int y = this.Fta[0].y;
        if (pta < y) {
            return;
        }
        final int ota = (int)this.a().b(this._(0, this.a(0) - 1).f(n));
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.yta != null) {
            graphics.setXORMode(this.yta);
        }
        else {
            graphics.setXORMode(this.Asa);
        }
        graphics.setColor(this.Qta);
        graphics.drawLine(ota, y, ota, this.getSize().height - 2);
        graphics.drawLine(1, pta, this.getSize().width - 2, pta);
        graphics.setPaintMode();
        this.Ota = ota;
        this.Pta = pta;
    }
    
    private void ea() {
        final int y = this.Fta[0].y;
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.yta != null) {
            graphics.setXORMode(this.yta);
        }
        else {
            graphics.setXORMode(this.Asa);
        }
        graphics.setColor(this.Qta);
        if (this.Ota > 0 && this.Pta > 0) {
            graphics.drawLine(this.Ota, y, this.Ota, this.getSize().height - 2);
            graphics.drawLine(1, this.Pta, this.getSize().width - 2, this.Pta);
        }
        final int n = -1;
        this.Pta = n;
        this.Ota = n;
        graphics.setPaintMode();
    }
    
    private void ga() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (this.wua) {
            return;
        }
        graphics.setColor(this.Asa);
        graphics.fillRect(2, 2, this.getSize().width - 4, this.v._(graphics) + 1);
    }
    
    private i _(final int n) {
        if (this.Vta == 0) {
            return this.s[n];
        }
        return this.r[n];
    }
    
    private String a(final int n, final double n2) {
        if (n > 0) {
            return this._(n).a(n2);
        }
        final int da = this.r[n].da();
        int n3;
        if (n2 >= 1.0) {
            n3 = 0;
        }
        else {
            n3 = (int)Math.ceil(Math.abs(abstract.n(n2)));
        }
        final int n4 = (int)Math.ceil(abstract.n(n2));
        if (da > 0 && da > n3) {
            n3 = da;
        }
        this.xua.setMaximumFractionDigits(n3);
        this.xua.setMinimumFractionDigits(n3);
        this.xua.setMaximumIntegerDigits(n4 + 1);
        this.xua.setMinimumIntegerDigits(1);
        this.xua.setGroupingSize(3);
        this.xua.setGroupingUsed(true);
        return this.xua.format(n2);
    }
    
    private int b(final double n, final double n2, final int n3) {
        if (this.wua) {
            return -1;
        }
        final StringBuffer sb = new StringBuffer();
        int b = -1;
        p _ = null;
        if (this.a(0) > 0) {
            _ = this._(0, this.a(0) - 1);
            b = _.b(n);
        }
        if (b < 0) {
            return b;
        }
        sb.append("Y: " + this.a(n3, n2));
        final String f = _.F();
        if (f != null) {
            if (f.length() > 0) {
                sb.append(" |" + f + ": " + this.q.h(_.f(b)));
            }
            else {
                sb.append(" |" + f + this.q.h(_.f(b)));
            }
        }
        for (int i = 0; i < this.n(); ++i) {
            for (int j = this.a(i) - 1; j >= 0; --j) {
                final p _2 = this._(i, j);
                for (int k = 0; k < _2.Y(); ++k) {
                    final String _3 = _2._(k);
                    if (_3 != null) {
                        if (_3.length() > 0) {
                            sb.append(" |" + _3 + ": " + this.a(i, _2.b(b, k)));
                        }
                        else {
                            sb.append(" |" + _3 + this.a(i, _2.b(b, k)));
                        }
                    }
                }
                if (i == 0 && j == this.a(i) - 1 && b > 0) {
                    final double b2 = _2.b(b - 1, _2.Y() - 1);
                    if (b2 != 0.0) {
                        final double n4 = 100.0 * (_2.b(b, _2.Y() - 1) - b2) / b2;
                        sb.append(" (");
                        if (n4 > 0.0) {
                            sb.append("+");
                        }
                        final String a = this.a(i, 0.0);
                        int n5 = 1;
                        if (a.length() > 4) {
                            n5 = 2;
                        }
                        this.yua.setMaximumFractionDigits(Math.min(n5, 2));
                        sb.append(this.yua.format(n4));
                        sb.append("%)");
                    }
                }
            }
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return b;
        }
        this.v.setText(sb.toString());
        graphics.setClip(2, 2, this.getSize().width - 5, this.v._(graphics) + 1);
        this.v.a(graphics, 4, 2);
        return b;
    }
    
    private synchronized void b(final Graphics graphics, final int n) {
        if (!this.isShowing()) {
            return;
        }
        int cua = d.Dua;
        if (this.Jta) {
            cua = Math.max(this.v._(graphics), cua);
        }
        final int bua = this.getSize().width - 11;
        final int n2 = 5;
        final int n3 = 2;
        if (bua < 1) {
            return;
        }
        if (graphics == null) {
            return;
        }
        if (this.zua == null || this.Aua == null || bua != this.Bua || cua != this.Cua) {
            if (this.Aua != null) {
                this.Aua.dispose();
            }
            this.Cua = cua;
            this.Bua = bua;
            if (this.zua != null) {
                this.zua.flush();
            }
            this.zua = this.createImage(this.Bua, this.Cua);
            this.Aua = this.zua.getGraphics();
        }
        if (this.Aua == null) {
            return;
        }
        final int min = Math.min(this.Bua, (int)(this.Bua * (n / 100.0)));
        this.Aua.setColor(this.Asa);
        this.Aua.fillRect(0, 0, this.Bua, this.Cua);
        this.Aua.setColor(this.zta);
        this.Aua.fillRect(0, (this.Cua - d.Dua) / 2, min, d.Dua);
        graphics.drawImage(this.zua, n2, n3, this);
    }
    
    public synchronized void update(final Observable observable, final Object o) {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final int intValue = (int)o;
        this.b(graphics, intValue);
        this.x = intValue;
    }
    
    private String i(final int n) {
        final StringBuffer sb = new StringBuffer();
        sb.append(" [");
        int h = -1;
        if (this.a(n) > 0) {
            h = this._(n, this.a(n) - 1).H();
        }
        if (h < 0) {
            return "";
        }
        for (int i = this.a(n) - 1; i >= 0; --i) {
            final p _ = this._(n, i);
            final int h2 = _.H();
            for (int j = 0; j < _.Y(); ++j) {
                final String _2 = _._(j);
                if (_2 != null) {
                    if (n == 0) {
                        if (_2.length() > 0) {
                            String s = "";
                            if (j > 0) {
                                s = "  ";
                            }
                            sb.append(s + _2 + ": " + this.a(n, _.b(h2, j)));
                        }
                        else {
                            sb.append("  " + _2 + this.a(n, _.b(h2, j)));
                        }
                    }
                    else {
                        sb.append(this.a(n, _.b(h2, j)));
                    }
                }
            }
        }
        sb.append("]");
        if (this.a(n) > 0) {
            final p _3 = this._(n, this.a(n) - 1);
            final int h3 = _3.H();
            if (n == 0 && h3 > 0) {
                final double b = _3.b(h3 - 1, _3.Y() - 1);
                if (b != 0.0) {
                    final double n2 = 100.0 * (_3.b(h3, _3.Y() - 1) - b) / b;
                    sb.append(" (");
                    if (n2 > 0.0) {
                        sb.append("+");
                    }
                    final String a = this.a(n, 0.0);
                    int maximumFractionDigits = 1;
                    if (a.length() > 4) {
                        maximumFractionDigits = 2;
                    }
                    this.yua.setMaximumFractionDigits(maximumFractionDigits);
                    sb.append(this.yua.format(n2));
                    sb.append("%)");
                }
            }
        }
        return sb.toString();
    }
    
    public synchronized break b(final boolean b) {
        return new break(this, b);
    }
    
    public synchronized void a(final OutputStream outputStream, final int n, final int n2) {
        Graphics graphics = null;
        try {
            graphics = new BufferedImage(n, n2, 1).createGraphics();
            this.a(graphics, 0, 0, n, n2);
        }
        finally {
            if (graphics != null) {
                graphics.dispose();
            }
            this.repaint();
        }
    }
    
    public void z(final String qua) {
        this.qua = qua;
    }
    
    public void A(final String rua) {
        this.rua = rua;
    }
    
    public void B(final String sua) {
        this.sua = sua;
    }
    
    public void C(final String tua) {
        this.tua = tua;
    }
    
    public synchronized void T(final boolean pua) {
        this.pua = pua;
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static void a(final d d) {
        d.fa();
    }
    
    static void _(final d d) {
        d.ea();
    }
    
    static void b(final d d) {
        d.ga();
    }
    
    static void a(final d d, final MouseEvent mouseEvent) {
        d._(mouseEvent);
    }
    
    static boolean a(final d d) {
        return d.wua;
    }
    
    static boolean b(final d d) {
        return d.Xta;
    }
    
    static int b(final d d) {
        return d.tta;
    }
    
    static int _(final d d, final int uta) {
        return d.uta = uta;
    }
    
    static Rectangle[] _(final d d) {
        return d.Fta;
    }
    
    static int a(final d d) {
        return d.uta;
    }
    
    static Class a(final d d) {
        return d.rta;
    }
    
    static UserDrawTool _(final d d, final UserDrawTool sta) {
        return d.sta = sta;
    }
    
    static Color a(final d d) {
        return d.Rta;
    }
    
    static UserDrawTool _(final d d) {
        return d.sta;
    }
    
    static int _(final d d) {
        return d.Sta;
    }
    
    static void _(final d d, final boolean b) {
        d.R(b);
    }
    
    static Frame a(final d d) {
        return d.a();
    }
    
    static switch _(final d d) {
        return d.xa;
    }
    
    static Rectangle a(final d d, final int n) {
        return d.a(n);
    }
    
    static Color _(final d d) {
        return d.Asa;
    }
    
    static int _(final d d, final int n, final int n2) {
        return d.d(n, n2);
    }
    
    static boolean _(final d d) {
        return d.Jta;
    }
    
    static int b(final d d, final double n, final double n2, final int n3) {
        return d.b(n, n2, n3);
    }
    
    static boolean h(final d d) {
        return d.Kta;
    }
    
    static void b(final d d, final int n, final int n2) {
        d.f(n, n2);
    }
    
    static {
        d.Dua = 6;
    }
}
