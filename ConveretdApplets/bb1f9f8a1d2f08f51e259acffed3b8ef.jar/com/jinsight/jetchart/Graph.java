// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.io.File;
import java.util.Observer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Graph extends GradientPanel implements MouseListener, MouseMotionListener
{
    public static final String ENCODING_ABORTED = "=S.h:1S*X?:R?s;<";
    public static final int VALUE = 0;
    public static final int LABEL_AND_VALUE = 1;
    static final int d = 0;
    static final int e = 1;
    static final int f = 2;
    public static final int DAY_INCREMENT = 0;
    public static final int WEEK_INCREMENT = 1;
    public static final int MONTH_INCREMENT = 2;
    public static final int YEAR_INCREMENT = 3;
    String[] g;
    double[] h;
    Vector i;
    Vector j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    Color q;
    String[] r;
    String s;
    Font t;
    Color u;
    String v;
    Font w;
    Color x;
    boolean y;
    boolean z;
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    boolean F;
    AbstractSerie G;
    AbstractSerie H;
    Note I;
    double J;
    double K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private boolean R;
    boolean S;
    private boolean T;
    private int U;
    private int V;
    private boolean W;
    Font X;
    boolean Y;
    boolean Z;
    boolean ba;
    boolean bb;
    boolean bc;
    boolean bd;
    private boolean be;
    boolean bf;
    boolean bg;
    boolean bh;
    boolean bi;
    boolean bj;
    boolean bk;
    boolean bl;
    boolean bm;
    String bn;
    String bo;
    int bp;
    int bq;
    m br;
    boolean bs;
    int bt;
    int bu;
    f bv;
    String bw;
    String bx;
    int by;
    int bz;
    int bA;
    int bB;
    int bC;
    private j bD;
    YAxis bE;
    XAxis bF;
    Legend bG;
    Grid bH;
    p bI;
    q bJ;
    ToolTip bK;
    private Image bL;
    private r bM;
    private Frame bN;
    Slice bO;
    private int bP;
    private int bQ;
    private Rectangle bR;
    private Rectangle bS;
    private Rectangle bT;
    private Vector bU;
    Vector bV;
    private Vector bW;
    private int bX;
    private int bY;
    private int bZ;
    private int ca;
    private int cb;
    private boolean cc;
    boolean cd;
    private Image ce;
    private Graphics cf;
    boolean cg;
    double ch;
    double ci;
    boolean cj;
    double ck;
    boolean cl;
    
    public void addSerieListener(final SerieListener serieListener) {
        this.bW.addElement(serieListener);
    }
    
    public void removeSerieListener(final SerieListener serieListener) {
        this.bW.removeElement(serieListener);
    }
    
    public void addSerie(final AbstractSerie abstractSerie) {
        if (abstractSerie instanceof GraphSerie && this.bI == null) {
            this.bI = new p(this);
            this.bJ = null;
        }
        if (abstractSerie instanceof PieSerie && this.bJ == null) {
            this.bJ = new q(this);
            this.bI = null;
        }
        this.i.addElement(abstractSerie);
        abstractSerie.a(this);
        if (abstractSerie instanceof StackBarSerie) {
            StackBarSerie.X = this;
        }
        this.bG.y = true;
        this.cg = true;
    }
    
    public Vector getSeries() {
        return this.i;
    }
    
    public void removeSerie(final AbstractSerie abstractSerie) {
        if (this.i.contains(abstractSerie)) {
            this.i.removeElement(abstractSerie);
            Label_0053: {
                if (abstractSerie instanceof PieSerie) {
                    this.bJ = null;
                    if (!GraphSerie.G) {
                        break Label_0053;
                    }
                }
                if (this.i.size() == 0) {
                    this.bI = null;
                }
            }
            this.bG.y = true;
            this.cg = true;
        }
    }
    
    public void removeAllSeries() {
        this.i.removeAllElements();
        this.bJ = null;
        this.bI = null;
        this.bG.y = true;
        this.cg = true;
    }
    
    public void addNote(final Note note) {
        if (this.j == null) {
            this.j = new Vector();
        }
        this.j.addElement(note);
        this.cg = true;
    }
    
    public void removeNote(final Note note) {
        if (this.j != null) {
            this.j.removeElement(note);
            this.cg = true;
        }
    }
    
    public void removeAllNotes() {
        if (this.j != null) {
            this.j.removeAllElements();
            this.cg = true;
        }
    }
    
    public void setLabels(final String[] g) {
        if (this.bx == null) {
            this.g = g;
        }
        this.cg = true;
    }
    
    public void setAutoLabelSpacingEnabled(final boolean bl) {
        this.bl = bl;
        this.cg = true;
    }
    
    public void setLabelSections(final int bc) {
        this.bC = bc;
    }
    
    public void setLabelMarkEnabled(final boolean z) {
        this.z = z;
    }
    
    public void setValueMarkEnabled(final boolean a) {
        this.A = a;
    }
    
    public void setServletApplication(final boolean offScreenGraphEnabled) {
        this.setOffScreenGraphEnabled(offScreenGraphEnabled);
    }
    
    public void setOffScreenGraphEnabled(final boolean bm) {
        this.bm = bm;
    }
    
    public void setDraggingEnabled(final boolean y) {
        this.y = y;
    }
    
    public void setSerieDraggingEnabled(final boolean b) {
        this.B = b;
    }
    
    public void setStartDate(final String bx) {
        this.cg = true;
        this.bx = bx;
    }
    
    public void setDateIncrement(final int by) {
        this.by = by;
        this.bz = 1;
    }
    
    public void setDateIncrement(final int by, final int bz) {
        this.by = by;
        if (bz < 1) {
            this.bz = 1;
            if (!GraphSerie.G) {
                return;
            }
        }
        this.bz = bz;
    }
    
    public void setAutoRangeOff(final boolean cj) {
        this.cj = cj;
        this.cg = true;
    }
    
    public void setMinRangeValue(final double ch) {
        this.ch = ch;
        this.cg = true;
    }
    
    public void setMaxRangeValue(final double ci) {
        this.ci = ci;
        this.cg = true;
    }
    
    public void setRangeIncrement(final double ck) {
        Label_0022: {
            if (ck <= 0.0) {
                this.ck = 1.0;
                if (!GraphSerie.G) {
                    break Label_0022;
                }
            }
            this.ck = ck;
        }
        this.cg = true;
    }
    
    public void setToolTipEnabled(final boolean z) {
        this.Z = z;
    }
    
    public void setMultipleToolTipsEnabled(final boolean ba) {
        this.ba = ba;
    }
    
    public void setToolTipLabel(final String bo) {
        this.bo = bo;
    }
    
    public void setToolTipType(final int bt) {
        this.bt = bt;
    }
    
    public void setToolTipDelay(int bu) {
        if (bu < 100) {
            bu = 100;
        }
        this.bu = bu;
    }
    
    public void setToolTipTimerEnabled(final boolean bb) {
        this.bb = bb;
    }
    
    public void addImageEncodingObserver(final Observer observer) {
        if (this.bU == null) {
            this.bU = new Vector();
        }
        this.bU.addElement(observer);
    }
    
    public void removeImageEncodingObserver(final Observer observer) {
        if (this.bU != null && this.bU.contains(observer)) {
            this.bU.removeElement(observer);
        }
    }
    
    public void setVDepth(int o) {
        if (o < 0) {
            o = 0;
        }
        if (o > 30) {
            o = 30;
        }
        this.o = o;
        this.cg = true;
    }
    
    public void setHDepth(int p) {
        if (p < 0) {
            p = 0;
        }
        if (p > 30) {
            p = 30;
        }
        this.p = p;
        this.cg = true;
    }
    
    public void setTitle(final String[] r) {
        this.r = r;
        this.cg = true;
    }
    
    public void setTitleForeground(final Color q) {
        this.q = q;
    }
    
    public void setTitleFont(final Font x) {
        this.X = x;
        this.cg = true;
    }
    
    public void setValuesTitle(final String s) {
        this.s = s;
        this.cg = true;
    }
    
    public void setValuesTitleFont(final Font t) {
        this.t = t;
        this.cg = true;
    }
    
    public void setValuesTitleForeground(final Color u) {
        this.u = u;
    }
    
    public void setValuesTitleBackground(final Color color) {
    }
    
    public void setLabelsTitle(final String v) {
        this.v = v;
        this.cg = true;
    }
    
    public void setLabelsTitleFont(final Font w) {
        this.w = w;
        this.cg = true;
    }
    
    public void setLabelsTitleForeground(final Color x) {
        this.x = x;
    }
    
    public void setLabelsTitleBackground(final Color color) {
    }
    
    public void setValueFormat(final String bw) {
        this.bw = bw;
        this.cg = true;
    }
    
    public void setLeftMargin(final int k) {
        this.k = k;
        this.cg = true;
    }
    
    public void setRightMargin(final int l) {
        this.l = l;
        this.cg = true;
    }
    
    public void setTopMargin(final int m) {
        this.m = m;
        this.cg = true;
    }
    
    public void setBottomMargin(final int n) {
        this.n = n;
        this.cg = true;
    }
    
    public void setGridEnabled(final boolean y) {
        this.Y = y;
    }
    
    public void setHorizontalGraphEnabled(final boolean bf) {
        this.bf = bf;
        this.cg = true;
    }
    
    public void setStartOnAxisEnabled(final boolean bg) {
        this.bg = bg;
        this.cg = true;
    }
    
    public void set3DEnabled(final boolean bc) {
        this.bc = bc;
        this.cg = true;
    }
    
    public void set3DSeriesInLineEnabled(final boolean bh) {
        this.bh = bh;
        this.cg = true;
    }
    
    public void setShowValuesEnabled(final boolean b) {
        this.bE.a(b);
        this.bF.a(b);
        this.cg = true;
    }
    
    public void setVerticalLabelsEnabled(final boolean bi) {
        this.bi = bi;
        this.cg = true;
    }
    
    public void setVerticalLabelsOpacityEnabled(final boolean bj) {
        this.bj = bj;
    }
    
    public void setAxisTitleOpacityEnabled(final boolean bk) {
        this.bk = bk;
    }
    
    public void setShowLabelsEnabled(final boolean b) {
        this.bE.b(b);
        this.bF.b(b);
        this.cg = true;
    }
    
    public YAxis getYAxis() {
        return this.bE;
    }
    
    public XAxis getXAxis() {
        return this.bF;
    }
    
    public Legend getLegend() {
        return this.bG;
    }
    
    public Grid getGrid() {
        return this.bH;
    }
    
    public ToolTip getToolTip() {
        return this.bK;
    }
    
    public void setLegendEnabled(final boolean bd) {
        this.bd = bd;
        this.cg = true;
    }
    
    public void gifEncode(final File file, final int n) throws IOException {
        final boolean g = GraphSerie.G;
        Label_0102: {
            if (this.bm) {
                if (this.bN == null) {
                    (this.bN = new Frame()).setSize(this.getSize());
                    this.bN.add(a("\u001bX#s;*"), this);
                    this.bN.addNotify();
                    this.bN.validate();
                }
                if (!g) {
                    break Label_0102;
                }
            }
            if (this.bN != null) {
                this.bN.remove(this);
                this.bN.dispose();
                this.bN = null;
            }
        }
        Image image = this.createImage(this.getSize().width, this.getSize().height);
        if (image == null) {
            throw new GraphException(a("\fU(u;xJ,t~9Smb,*R?'**D$i9xI\"'=*X,s;xR+a-;O(b0xZ?f.0\u0013") + a("x~%b=3\u001d$a~,U('-=I\u0002a8\u000b^?b;6z?f.0x#f<4X)/wxP(s67Ymp?+\u001d>b*xI\"'**H()"));
        }
        this.paint(image.getGraphics());
        if (n != 100) {
            image = image.getScaledInstance(image.getWidth(null) * n / 100, image.getHeight(null) * n / 100, 1);
        }
        try {
            this.bM = new s(image, new FileOutputStream(file));
            if (this.bU != null) {
                int n2 = 0;
                while (true) {
                    Label_0262: {
                        if (!g) {
                            break Label_0262;
                        }
                        this.bM.addObserver((Observer)this.bU.elementAt(n2));
                        ++n2;
                    }
                    if (n2 < this.bU.size()) {
                        continue;
                    }
                    break;
                }
            }
            this.bM.c();
            this.bM.f();
            this.bM = null;
        }
        catch (IOException ex) {
            if (ex.getMessage().equals(a("\u001ao\bF\u0015\u0007n\u0004@\u0010\u001dy"))) {
                throw new IOException(a("=S.h:1S*X?:R?s;<"));
            }
            ex.printStackTrace();
            if (g) {}
        }
    }
    
    public void stopEncoding() {
        if (this.bM != null) {
            this.bM.b();
        }
    }
    
    public void refresh() {
        this.cg = true;
        this.F = true;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.bA != width || this.bB != height) {
            this.ce = this.createImage(this.getSize().width, this.getSize().height);
            this.bA = width;
            this.bB = height;
            this.cg = true;
        }
        if (this.cl) {
            this.cl = false;
            int n = 0;
            while (true) {
                Label_0108: {
                    if (!g) {
                        break Label_0108;
                    }
                    this.bK.r[n] = null;
                    ++n;
                }
                if (n < this.bK.r.length) {
                    continue;
                }
                break;
            }
        }
        super.paint(this.cf = this.ce.getGraphics());
        if (this.i.size() != 0) {
            o o;
            if (this.bI != null) {
                o = this.bI;
            }
            else {
                o = this.bJ;
            }
            if (this.cg && this.bx != null) {
                this.g = o.c();
            }
            if (this.g == null) {
                throw new GraphException(a("\u001bR8k:xS\"s~>T#c~4\\/b2+\u0013mW2=\\>b~1S+h,5\u001d!f<=Q>')1I%'") + a(",U('\u0019*\\=op+X9K?:X!tvq\u001d b*0R))"));
            }
            Label_0565: {
                if (this.bJ == null) {
                    if (this.cg) {
                        int n2 = 0;
                        while (true) {
                            while (true) {
                                Label_0307: {
                                    if (!g) {
                                        break Label_0307;
                                    }
                                    final Object element = this.i.elementAt(n2);
                                    if (element instanceof OHLCSerie) {
                                        this.bc = false;
                                        this.bf = false;
                                        ((OHLCSerie)this.i.elementAt(n2)).p = false;
                                    }
                                    ++n2;
                                }
                                if (n2 < this.i.size()) {
                                    continue;
                                }
                                break;
                            }
                            this.b();
                            final Object element = this;
                            if (g) {
                                continue;
                            }
                            break;
                        }
                        this.d();
                    }
                    this.bI.a(this.cf);
                    int n3 = 0;
                    p p;
                    while (true) {
                        while (true) {
                            Label_0381: {
                                if (!g) {
                                    break Label_0381;
                                }
                                final Object o2 = this.i.elementAt(n3);
                                final GraphSerie graphSerie = (GraphSerie)o2;
                                if (graphSerie.p) {
                                    graphSerie.d();
                                }
                                ++n3;
                            }
                            if (n3 < this.i.size()) {
                                continue;
                            }
                            break;
                        }
                        this.bI.b(this.cf);
                        Object o2;
                        p = (p)(o2 = this.bI);
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    p.c(this.cf);
                    if (!g) {
                        break Label_0565;
                    }
                }
                if (this.i.size() > 1) {
                    throw new GraphException(a("\u0019\u001d\u001dn;xn(u7=\u001d%f-x_(b0x\\)c;<\u001d9h~,U('9*\\=o7;\u001d.h0,X5spxt#'*0T>'-1I8f*1R#+~") + a("1Nmi1,\u001d=b,5T9s;<\u001d9h~9Y)'37O('*0\\#'16Xmt;*T()"));
                }
                if (this.cg) {
                    this.bJ.addObserver(this.i.elementAt(0));
                    if (this.bV.size() == 0) {
                        this.bV.addElement(this.i.elementAt(0));
                    }
                }
                this.bf = false;
                this.ba = false;
                this.bJ.a(this.cf);
                this.bF.a(this.cf);
                this.bE.a(this.cf);
            }
            this.a(this.cf);
            if (this.bd) {
                this.bG.a(this.cf);
            }
            if (this.j != null) {
                int n4 = 0;
                while (true) {
                    Label_0628: {
                        if (!g) {
                            break Label_0628;
                        }
                        ((Note)this.j.elementAt(n4)).a(this.cf);
                        ++n4;
                    }
                    if (n4 < this.j.size()) {
                        continue;
                    }
                    break;
                }
            }
            this.bK.a(this.cf);
        }
        if (this.bD == null) {
            this.bD = new j();
        }
        this.bD.a(this, this.cf);
        this.bD = null;
        graphics.drawImage(this.ce, 0, 0, this);
        this.cg = false;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final boolean g = GraphSerie.G;
        this.cl = true;
        int n = 0;
        while (true) {
            while (true) {
                Label_0033: {
                    if (!g) {
                        break Label_0033;
                    }
                    ((AbstractSerie)this.i.elementAt(n)).j = false;
                    ++n;
                }
                if (n < this.i.size()) {
                    continue;
                }
                break;
            }
            this.bs = false;
            this.repaint();
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.i.size() == 0) {
            return;
        }
        if (this.bs && this.Z) {
            this.bs = false;
            this.repaint();
        }
        if (this.S && mouseEvent.getClickCount() == 2) {
            this.I.g = !this.I.g;
            this.repaint();
            return;
        }
        this.L = mouseEvent.getX();
        this.M = mouseEvent.getY();
        final boolean b = !this.c() && this.bI.v();
        if (mouseEvent.getClickCount() == 2 && this.y && !this.E && !this.S && ((b && !this.E) || (!this.E && !this.R && (!this.bd || !this.bG.b(mouseEvent.getPoint()))))) {
            this.W = !this.W;
        }
        this.T = ((mouseEvent.getModifiers() & 0x1) != 0x0);
        if (this.y) {
            this.N = this.m;
            this.O = this.n;
            this.P = this.k;
            this.Q = this.l;
            if (this.bd && this.bG.b(mouseEvent.getPoint())) {
                this.cc = true;
                this.cb = this.bG.getPosition();
                this.bX = this.L - this.bG.f().x;
                this.bY = this.M - this.bG.f().y;
                this.bG.setPosition(3);
            }
        }
        this.bR.x = this.getSize().width * 60 / 100;
        this.bR.y = 0;
        this.bR.width = this.getSize().width;
        this.bR.height = this.getSize().height * 60 / 100;
        this.bS.x = 0;
        this.bS.y = this.getSize().height * 60 / 100;
        this.bS.width = this.getSize().width;
        this.bS.height = this.getSize().height;
        this.bT.x = 0;
        this.bT.y = 0;
        this.bT.width = this.getSize().width * 40 / 100;
        this.bT.height = this.getSize().height * 60 / 100;
        if (this.R) {
            final PieSerie pieSerie = this.i.elementAt(0);
            if (!this.T) {
                this.U = this.bJ.a(pieSerie, this.L, this.M);
                this.V = pieSerie.w;
                if (!GraphSerie.G) {
                    return;
                }
            }
            this.bO = pieSerie.getSlice(this.L, this.M);
            if (this.bO != null) {
                this.bP = this.bO.a();
                this.bQ = this.bJ.a(this.bO, this.L, this.M);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean g = GraphSerie.G;
        this.a(mouseEvent);
        if (this.G != null && !this.G.a(0, mouseEvent.getX(), mouseEvent.getY())) {
            this.G.j = false;
        }
        Label_0108: {
            if (this.bJ == null) {
                this.setCursor(Cursor.getPredefinedCursor(0));
                if (!g) {
                    break Label_0108;
                }
            }
            if (this.i.elementAt(0).getSlice(mouseEvent.getX(), mouseEvent.getY()) != null) {
                this.setCursor(Cursor.getPredefinedCursor(12));
                if (!g) {
                    break Label_0108;
                }
            }
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        if (this.C || this.D) {
            this.cg = true;
        }
        if (this.C) {
            this.E = false;
            this.C = false;
        }
        this.D = false;
        if (this.cc) {
            this.cd = true;
            final Rectangle f = this.bG.f();
            Label_0324: {
                if (this.bR.contains(f.x, f.y)) {
                    this.bG.setPosition(0);
                    if (this.bG.h) {
                        this.bG.setOrientation(0);
                    }
                    if (!g) {
                        break Label_0324;
                    }
                }
                if (this.bT.contains(f.x, f.y)) {
                    this.bG.setPosition(2);
                    if (this.bG.h) {
                        this.bG.setOrientation(0);
                    }
                    if (!g) {
                        break Label_0324;
                    }
                }
                if (this.bS.contains(f.x, f.y)) {
                    this.bG.setPosition(1);
                    if (this.bG.h) {
                        this.bG.setOrientation(1);
                    }
                    if (!g) {
                        break Label_0324;
                    }
                }
                this.bG.setPosition(this.cb);
            }
            this.cc = false;
            this.bG.a(false);
        }
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final boolean g = GraphSerie.G;
        synchronized (this.bv) {
            while (true) {
                while (true) {
                    Label_0036: {
                        if (!g) {
                            break Label_0036;
                        }
                        try {
                            final Graph graph = this;
                            graph.bv.wait();
                        }
                        catch (InterruptedException ex) {
                            if (g) {}
                        }
                    }
                    if (!this.bv.c) {
                        continue;
                    }
                    break;
                }
                this.bv.c = false;
                this.bp = mouseEvent.getX();
                this.bq = mouseEvent.getY();
                final Graph graph = this;
                if (g) {
                    continue;
                }
                break;
            }
            Label_0401: {
                if (this.j == null || (this.j != null && !this.f())) {
                    this.R = false;
                    Label_0392: {
                        if (!this.bd || (this.bd && this.bG.f() != null && !this.bG.b(mouseEvent.getPoint()))) {
                            this.E = false;
                            this.g();
                            int n = 0;
                            while (true) {
                            Label_0359:
                                while (true) {
                                    Label_0347: {
                                        if (!g) {
                                            break Label_0347;
                                        }
                                        final Object element = this.bV.elementAt(n);
                                        final AbstractSerie abstractSerie = (AbstractSerie)element;
                                        Label_0344: {
                                            if (abstractSerie instanceof PieSerie) {
                                                this.bO = ((PieSerie)abstractSerie).getSlice(this.bp, this.bq);
                                                if (this.bO != null) {
                                                    this.G = abstractSerie;
                                                    this.R = true;
                                                    if (this.getCursor().getType() != 12) {
                                                        this.setCursor(Cursor.getPredefinedCursor(12));
                                                    }
                                                    if (!g) {
                                                        break Label_0359;
                                                    }
                                                }
                                                if (this.getCursor().getType() != 0) {
                                                    this.setCursor(Cursor.getPredefinedCursor(0));
                                                }
                                                this.R = false;
                                                this.G = null;
                                                if (!g) {
                                                    break Label_0344;
                                                }
                                            }
                                            if (abstractSerie.a(0, this.bp, this.bq)) {
                                                this.G = abstractSerie;
                                                if (this.B) {
                                                    this.a(abstractSerie, this.bp, this.bq);
                                                }
                                                if (!g) {
                                                    break Label_0359;
                                                }
                                            }
                                            if (!this.E) {
                                                this.setCursor(Cursor.getPredefinedCursor(0));
                                            }
                                            this.G = null;
                                        }
                                        ++n;
                                    }
                                    if (n < this.bV.size()) {
                                        continue;
                                    }
                                    break;
                                }
                                final Object element = this;
                                if (g) {
                                    continue;
                                }
                                break;
                            }
                            this.bs = true;
                            if (!g) {
                                break Label_0392;
                            }
                        }
                        if (this.bG.b(mouseEvent.getPoint())) {
                            this.a();
                        }
                    }
                    if (!g) {
                        break Label_0401;
                    }
                }
                this.a();
            }
            Label_0448: {
                if (this.Z) {
                    if (this.bb) {
                        this.e();
                        if (!g) {
                            break Label_0448;
                        }
                    }
                    if (this.bs && this.bK.a() == 0) {
                        this.bv.a();
                    }
                }
            }
            this.repaint();
            this.bv.c = true;
            this.bv.notifyAll();
        }
        // monitorexit(this.bv)
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean g = GraphSerie.G;
        if (this.i.size() == 0) {
            return;
        }
        this.bs = false;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        Label_0714: {
            if (!this.S) {
                Label_0535: {
                    if (this.B && this.E && !this.R) {
                        Label_0217: {
                            if (!this.C) {
                                if (!(this.G instanceof g)) {
                                    this.C = true;
                                    if (this.F) {
                                        this.F = false;
                                        this.J = this.bI.b(this.bI.i());
                                        this.K = this.bI.a(this.bI.i());
                                    }
                                }
                                if (!g) {
                                    break Label_0217;
                                }
                            }
                            this.bI.a((GraphSerie)this.G, x, y);
                            final SerieEvent serieEvent = new SerieEvent(this, this.G, false, x, y, mouseEvent.getClickCount());
                            int n = 0;
                            while (true) {
                                Label_0205: {
                                    if (!g) {
                                        break Label_0205;
                                    }
                                    ((SerieListener)this.bW.elementAt(n)).serieDragged(serieEvent);
                                    ++n;
                                }
                                if (n < this.bW.size()) {
                                    continue;
                                }
                                break;
                            }
                        }
                        if (!g) {
                            break Label_0535;
                        }
                    }
                    if (this.y) {
                        this.D = (!this.cc && !this.R);
                        if (!this.R) {
                            this.setCursor(this.W ? Cursor.getPredefinedCursor(13) : (this.cc ? Cursor.getPredefinedCursor(13) : Cursor.getPredefinedCursor(11)));
                            if (!this.cc) {
                                this.m = this.N + y - this.M;
                                this.l = this.Q - (x - this.L);
                                if (this.W) {
                                    this.n = this.O - (y - this.M);
                                    this.k = this.P + x - this.L;
                                }
                                if (!g) {
                                    break Label_0535;
                                }
                            }
                            if (this.bd && (this.bG.b(mouseEvent.getPoint()) || this.cc)) {
                                final Point point = mouseEvent.getPoint();
                                this.bG.a(new Point(point.x - this.bX, point.y - this.bY));
                                final Rectangle f = this.bG.f();
                                if (this.bR.contains(f.x, f.y) || this.bT.contains(f.x, f.y) || this.bS.contains(f.x, f.y)) {
                                    this.bG.a(true);
                                    if (!g) {
                                        break Label_0535;
                                    }
                                }
                                this.bG.a(false);
                            }
                        }
                    }
                }
                Label_0690: {
                    if (this.R) {
                        final PieSerie pieSerie = this.i.elementAt(0);
                        if (!this.T) {
                            pieSerie.setAngleOffset(this.V + (this.bJ.a(pieSerie, x, y) - this.U));
                            if (!g) {
                                break Label_0690;
                            }
                        }
                        if (this.bO != null && this.bJ.a(pieSerie, this.bO, x, y)) {
                            final int position = this.bP + (this.bJ.a(this.bO, x, y) - this.bQ);
                            if (this.bO.a() + position < 0) {
                                this.bO.setPosition(0);
                                if (!g) {
                                    break Label_0690;
                                }
                            }
                            this.bO.setPosition(position);
                        }
                    }
                }
                if (!g) {
                    break Label_0714;
                }
            }
            this.I.setLocation(x - this.bZ, y - this.ca);
        }
        this.repaint();
    }
    
    private void a() {
        this.setCursor(Cursor.getPredefinedCursor(0));
        if (this.H != null) {
            int n = 0;
            while (true) {
                Label_0041: {
                    if (!GraphSerie.G) {
                        break Label_0041;
                    }
                    ((AbstractSerie)this.i.elementAt(n)).j = false;
                    ++n;
                }
                if (n < this.i.size()) {
                    continue;
                }
                break;
            }
        }
        this.bs = false;
    }
    
    private void a(final MouseEvent mouseEvent) {
        final boolean g = GraphSerie.G;
        if (this.G != null) {
            if ((!this.c() && this.bI.v()) || (this.bd && !this.bG.b(mouseEvent.getPoint()))) {
                if (this.G instanceof PieSerie) {
                    this.G.f = this.bO.getIndex();
                }
                final SerieEvent serieEvent = new SerieEvent(this, this.G, false, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getClickCount());
                int n = 0;
                while (true) {
                    Label_0182: {
                        if (!g) {
                            break Label_0182;
                        }
                        final SerieListener serieListener = this.bW.elementAt(n);
                        Label_0179: {
                            if (mouseEvent.getID() == 500) {
                                serieListener.serieClicked(serieEvent);
                                if (!g) {
                                    break Label_0179;
                                }
                            }
                            if (mouseEvent.getID() == 502) {
                                serieListener.serieReleased(serieEvent);
                            }
                        }
                        ++n;
                    }
                    if (n < this.bW.size()) {
                        continue;
                    }
                    break;
                }
            }
        }
        else if (this.bG.m != null) {
            int f = 0;
            while (true) {
                Label_0399: {
                    if (!g) {
                        break Label_0399;
                    }
                    final Rectangle rectangle = this.bG.m[f];
                    if (rectangle != null && rectangle.contains(mouseEvent.getPoint())) {
                        AbstractSerie abstractSerie;
                        if (this.i.elementAt(0) instanceof PieSerie) {
                            abstractSerie = this.i.elementAt(0);
                            abstractSerie.f = f;
                        }
                        else {
                            abstractSerie = this.i.elementAt(f);
                        }
                        final SerieEvent serieEvent2 = new SerieEvent(this, abstractSerie, true, mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getClickCount());
                        int n2 = 0;
                        while (true) {
                            Label_0381: {
                                if (!g) {
                                    break Label_0381;
                                }
                                final SerieListener serieListener2 = this.bW.elementAt(n2);
                                Label_0378: {
                                    if (mouseEvent.getID() == 500) {
                                        serieListener2.serieClicked(serieEvent2);
                                        if (!g) {
                                            break Label_0378;
                                        }
                                    }
                                    if (mouseEvent.getID() == 502) {
                                        serieListener2.serieReleased(serieEvent2);
                                    }
                                }
                                ++n2;
                            }
                            if (n2 >= this.bW.size()) {
                                return;
                            }
                            continue;
                        }
                    }
                    else {
                        ++f;
                    }
                }
                if (f < this.bG.m.length) {
                    continue;
                }
                break;
            }
        }
    }
    
    private void a(final AbstractSerie abstractSerie, final int n, final int n2) {
        final boolean g = GraphSerie.G;
        if (!(abstractSerie instanceof g)) {
            this.E = true;
            boolean b = true;
            Label_0275: {
                if (abstractSerie instanceof BarSerie) {
                    final BarSerie barSerie = (BarSerie)abstractSerie;
                    final Rectangle rectangle = barSerie.S[barSerie.f];
                    final double n3 = barSerie.getValues()[barSerie.f];
                    if (!this.bf) {
                        Label_0174: {
                            if (n3 >= 0.0) {
                                final int y = rectangle.y;
                                if (n2 > y + 5 || n2 < y || !rectangle.contains(n, n2)) {
                                    b = false;
                                    this.E = false;
                                }
                                if (!g) {
                                    break Label_0174;
                                }
                            }
                            final int n4 = rectangle.y + rectangle.height;
                            if (n2 < n4 - 5 || n2 > n4 || !rectangle.contains(n, n2)) {
                                b = false;
                                this.E = false;
                            }
                        }
                        if (!g) {
                            break Label_0275;
                        }
                    }
                    if (n3 >= 0.0) {
                        final int n5 = rectangle.x + rectangle.width;
                        if (n < n5 - 5 || n > n5 || !rectangle.contains(n, n2)) {
                            b = false;
                            this.E = false;
                        }
                        if (!g) {
                            break Label_0275;
                        }
                    }
                    final int x = rectangle.x;
                    if (n < x || n > x + 5 || !rectangle.contains(n, n2)) {
                        b = false;
                        this.E = false;
                    }
                }
            }
            if (b) {
                Label_0310: {
                    if (!this.bf) {
                        this.setCursor(Cursor.getPredefinedCursor(8));
                        if (!g) {
                            break Label_0310;
                        }
                    }
                    this.setCursor(Cursor.getPredefinedCursor(10));
                }
                if (!g) {
                    return;
                }
            }
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    private void b() {
        if (!this.bf) {
            this.bE.setTitle(this.s);
            this.bE.setTitleFont(this.t);
            this.bE.setTitleForeground(this.u);
            this.bF.setTitle(this.v);
            this.bF.setTitleFont(this.w);
            this.bF.setTitleForeground(this.x);
            if (!GraphSerie.G) {
                return;
            }
        }
        this.bF.setTitle(this.s);
        this.bF.setTitleFont(this.t);
        this.bF.setTitleForeground(this.u);
        this.bE.setTitle(this.v);
        this.bE.setTitleFont(this.w);
        this.bE.setTitleForeground(this.x);
    }
    
    private void a(final Graphics graphics) {
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setColor(this.q);
        if (this.X == null) {
            this.X = new Font(a("\u000b\\#t\r=O$a"), 1, 10);
        }
        graphics.setFont(this.X);
        if (this.r != null) {
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.X);
            int n = 0;
            int n2 = 0;
            while (true) {
                Label_0159: {
                    if (!GraphSerie.G) {
                        break Label_0159;
                    }
                    graphics.drawString(this.r[n2], (this.getSize().width - fontMetrics.stringWidth(this.r[n2])) / 2, this.m + fontMetrics.getMaxAscent() + n);
                    n += fontMetrics.getMaxDescent() + fontMetrics.getLeading() + fontMetrics.getMaxAscent();
                    ++n2;
                }
                if (n2 < this.r.length) {
                    continue;
                }
                break;
            }
        }
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    boolean c() {
        final boolean g = GraphSerie.G;
        boolean b = false;
        int n = 0;
        while (true) {
        Label_0046:
            while (true) {
                Label_0035: {
                    if (!g) {
                        break Label_0035;
                    }
                    final boolean b2 = this.i.elementAt(n) instanceof PieSerie;
                    final boolean b3;
                    if (b3) {
                        b = true;
                        if (!g) {
                            break Label_0046;
                        }
                    }
                    ++n;
                }
                if (n < this.i.size()) {
                    continue;
                }
                break;
            }
            final boolean b3 = b;
            if (!g) {
                return b3;
            }
            continue;
        }
    }
    
    private void d() {
        final boolean g = GraphSerie.G;
        this.bI.deleteObservers();
        this.bV.removeAllElements();
        final Vector s = this.bI.s();
        while (true) {
            Label_0376: {
                if (!this.bf) {
                    int y = this.i.size() - 1;
                    Vector vector;
                    while (true) {
                        while (true) {
                            Label_0104: {
                                if (!g) {
                                    break Label_0104;
                                }
                                final Object element = this.i.elementAt(y);
                                if (!(((GraphSerie)element) instanceof StackBarSerie)) {
                                    this.bI.addObserver((Observer)this.i.elementAt(y));
                                    this.bV.addElement(this.i.elementAt(y));
                                }
                                --y;
                            }
                            if (y >= 0) {
                                continue;
                            }
                            break;
                        }
                        Object element;
                        vector = (Vector)(element = s);
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    if (vector != null) {
                        y = 0;
                        while (true) {
                            Label_0168: {
                                if (!g) {
                                    break Label_0168;
                                }
                                s.elementAt(y).Y = y;
                                this.bI.addObserver(s.elementAt(y));
                                this.bV.addElement(s.elementAt(y));
                                ++y;
                            }
                            if (y < s.size()) {
                                continue;
                            }
                            break;
                        }
                    }
                    if (!g) {
                        break Label_0376;
                    }
                }
                int y;
                while (true) {
                    Label_0237: {
                        if (this.bh || s == null) {
                            break Label_0237;
                        }
                        y = 0;
                        while (true) {
                            Label_0229: {
                                if (!g) {
                                    break Label_0229;
                                }
                                this.bI.addObserver(s.elementAt(y));
                                this.bV.addElement(s.elementAt(y));
                                ++y;
                            }
                            if (y < s.size()) {
                                continue;
                            }
                            break;
                        }
                    }
                    y = 0;
                    if (g) {
                        continue;
                    }
                    break;
                }
                while (true) {
                    while (true) {
                        Label_0304: {
                            if (!g) {
                                break Label_0304;
                            }
                            final Object element2 = this.i.elementAt(y);
                            if (!(((GraphSerie)element2) instanceof StackBarSerie)) {
                                this.bI.addObserver((Observer)this.i.elementAt(y));
                                this.bV.addElement(this.i.elementAt(y));
                            }
                            ++y;
                        }
                        if (y < this.i.size()) {
                            continue;
                        }
                        break;
                    }
                    final Object element2 = this;
                    if (g) {
                        continue;
                    }
                    break;
                }
                if (!this.bh || s == null) {
                    break Label_0376;
                }
                y = 0;
                while (true) {
                    Label_0368: {
                        if (!g) {
                            break Label_0368;
                        }
                        this.bI.addObserver(s.elementAt(y));
                        final Graph graph = this;
                        graph.bV.addElement(s.elementAt(y));
                        ++y;
                    }
                    if (y < s.size()) {
                        continue;
                    }
                    break;
                }
            }
            final Graph graph = this;
            if (!g) {
                Label_0437: {
                    if (!this.bf) {
                        this.bI.addObserver(this.bF);
                        this.bI.addObserver(this.bE);
                        if (!g) {
                            break Label_0437;
                        }
                    }
                    this.bI.addObserver(this.bE);
                    this.bI.addObserver(this.bF);
                }
                if (this.Y) {
                    this.bI.addObserver(this.bH);
                }
                return;
            }
            continue;
        }
    }
    
    private void e() {
        (this.br = new m(this)).setPriority(1);
        this.br.start();
    }
    
    private boolean f() {
        final boolean g = GraphSerie.G;
        this.S = false;
        this.I = null;
        int n = this.j.size() - 1;
        while (true) {
        Label_0135:
            while (true) {
                Label_0131: {
                    if (!g) {
                        break Label_0131;
                    }
                    final Object element = this.j.elementAt(n);
                    final Note i = (Note)element;
                    if (i.a(this.bp, this.bq)) {
                        this.S = true;
                        this.R = false;
                        this.I = i;
                        this.bZ = this.bp - i.d;
                        this.ca = this.bq - i.e;
                        this.setCursor(Cursor.getPredefinedCursor(0));
                        if (this.G != null) {
                            this.G.j = false;
                        }
                        this.G = null;
                        if (!g) {
                            break Label_0135;
                        }
                    }
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
            final Object element = this;
            if (!g) {
                return this.S;
            }
            continue;
        }
    }
    
    private void g() {
        final boolean g = GraphSerie.G;
        if (this.H != null) {
            this.H.j = false;
        }
        int n = 0;
        while (true) {
            Label_0084: {
                if (!g) {
                    break Label_0084;
                }
                final AbstractSerie h = this.bV.elementAt(n);
                if (!(h instanceof PieSerie) && h.a(2, this.bp, this.bq)) {
                    h.j = true;
                    this.H = h;
                    if (this.bc && !g) {
                        return;
                    }
                }
                ++n;
            }
            if (n < this.bV.size()) {
                continue;
            }
            break;
        }
    }
    
    public Graph() {
        this.i = new Vector();
        this.k = 10;
        this.l = 10;
        this.m = 10;
        this.n = 10;
        this.o = 10;
        this.p = 5;
        this.q = Color.black;
        this.t = new Font(a("\u000b\\#t\r=O$a"), 0, 10);
        this.u = Color.black;
        this.w = new Font(a("\u000b\\#t\r=O$a"), 0, 10);
        this.x = Color.black;
        this.z = true;
        this.A = true;
        this.F = true;
        this.W = true;
        this.X = new Font(a("\u000b\\#t\r=O$a"), 1, 10);
        this.bb = true;
        this.bd = true;
        this.bh = true;
        this.bl = true;
        this.bn = "";
        this.bu = 500;
        this.bw = a("{\rc7n");
        this.bz = 1;
        this.bR = new Rectangle(0, 0, 0, 0);
        this.bS = new Rectangle(0, 0, 0, 0);
        this.bT = new Rectangle(0, 0, 0, 0);
        this.bV = new Vector();
        this.bW = new Vector();
        this.cg = true;
        this.ck = 1.0;
        this.bE = new YAxis(this);
        this.bF = new XAxis(this);
        this.bG = new Legend(this);
        this.bH = new Grid(this);
        this.bK = new ToolTip(this);
        this.bv = new f(this);
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public Graph(final String[] g) {
        this();
        this.g = g;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'X';
                            break;
                        }
                        case 1: {
                            c2 = '=';
                            break;
                        }
                        case 2: {
                            c2 = 'M';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = '^';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
