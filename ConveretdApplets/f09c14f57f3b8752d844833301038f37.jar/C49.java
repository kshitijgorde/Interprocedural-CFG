import java.util.Observable;
import java.awt.Font;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Vector;
import java.util.Observer;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class C49 extends Canvas implements C46, Observer
{
    C03 V;
    boolean W;
    int X;
    Vector Y;
    int Z;
    int[][] bd;
    int be;
    int bf;
    int bg;
    int bh;
    Dimension bi;
    int bj;
    int bk;
    int bl;
    Image bn;
    int bo;
    String bp;
    Color bq;
    int br;
    int bs;
    public String bu;
    C44 bw;
    int by;
    long bz;
    char[] bA;
    int bC;
    Color bD;
    String bF;
    C38 bG;
    long bH;
    int bI;
    Dimension bJ;
    int bK;
    int bL;
    C17 bM;
    int bN;
    C34 bP;
    long bQ;
    Color bR;
    int bS;
    String bT;
    Rectangle bU;
    int bW;
    int bX;
    int bY;
    C15 bZ;
    Rectangle cb;
    int cc;
    int cd;
    boolean ce;
    C45 cf;
    int ch;
    double ci;
    Rectangle cj;
    int ck;
    int cl;
    int cm;
    boolean cn;
    boolean co;
    int cp;
    double cq;
    C18 cr;
    String ct;
    boolean cu;
    
    public void a() {
        this.Y = new Vector();
        this.bT = null;
        this.be = 0;
        final double n = -1.0;
        this.ci = n;
        this.cq = n;
        this.ck = Integer.MAX_VALUE;
        this.cd = Integer.MAX_VALUE;
        this.bW = 0;
        this.bK = 0;
        this.bG = null;
        this.bU = null;
        this.cp = -1;
        this.bZ = null;
        this.ce = false;
        this.cn = false;
        this.bM = null;
        this.cb = null;
        this.cj = null;
        this.bH = -1L;
        this.cr = null;
        this.by = -1;
        this.bl = -1;
        this.bf = -1;
        this.bZ = null;
    }
    
    public int b(final C20 c20, final int n, final int n2, final int n3) {
        for (int i = 0; i < this.cm; ++i) {
            if (this.bd[i][0] == n2 && this.bd[i][1] == n && this.bd[i][2] == n3) {
                return c20.SelObject(i);
            }
        }
        c20.CreatePen2(n2 - 1, n3, n, this.cm);
        final int selObject = c20.SelObject(this.cm);
        this.bd[this.cm][0] = n2;
        this.bd[this.cm][1] = n;
        this.bd[this.cm][2] = n3;
        ++this.cm;
        return selObject;
    }
    
    protected void c(final Event event) {
        if (this.by == -1) {
            return;
        }
        this.cp = -1;
        if (Math.abs(this.by - event.x) > 1 || Math.abs(this.bl - event.y) > 1) {
            final Point i = this.I(new Point(this.by, this.bl));
            final Point j = this.I(new Point(event.x, event.y));
            final Point point = new Point(this.cb.width / 2 + this.cb.x - (j.x - i.x), this.cb.height / 2 + this.cb.y - (j.y - i.y));
            this.L(1.0, point.x, point.y);
            this.by = -1;
            this.bl = -1;
        }
    }
    
    public void update(final Graphics graphics) {
        final Image image = this.createImage(this.size().width, this.size().height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.size().width, this.size().height);
        graphics2.setColor(this.getForeground());
        this.paint(graphics2);
        graphics2.dispose();
        graphics.drawImage(image, 0, 0, null);
    }
    
    private void e(final Graphics graphics) {
        graphics.getColor();
        final C15 c15 = new C15(this, graphics, this.size().width, this.size().height);
        c15.Q = this.cf.P.RentedBoothsHighlighted;
        c15.bt = this.cf.P.UnrentedBoothsHighlighted;
        c15.bi = this.cf.P.FavoriteBoothsHighlighted;
        c15.P = this.cf.P.zoomBoothHighlighted;
        c15.T = this.cf.P.ZoomBoothColor;
        if (this.bq != null) {
            c15.d(this.bq);
            c15.y(true);
        }
        else {
            c15.o(Color.black);
            c15.y(false);
        }
        final int height = this.size().height;
        for (int i = 0; i < this.Y.size(); ++i) {
            final C35 c16 = this.Y.elementAt(i);
            if (c16 == null) {
                System.out.println("w=null! i=" + i);
                return;
            }
            if (!(c16 instanceof C28)) {
                c16.b(c15, this.bN, this.cq, this.bY, this.bh, this.ci, this.bs, height);
            }
            else if (((C28)c16).a(this.cj)) {
                c16.b(c15, this.bN, this.cq, this.bY, this.bh, this.ci, this.bs, height);
            }
        }
        this.w(graphics);
        this.p(graphics);
        if (graphics != null) {
            graphics.dispose();
            graphics.finalize();
        }
        if (this.cc++ > 5) {
            System.gc();
            this.cc = 0;
        }
    }
    
    public void f() {
        this.L(0.5, this.cb.x + this.cb.width / 2, this.cb.y + this.cb.height / 2);
    }
    
    public void g(final C17 c17) {
        this.H(c17.a());
    }
    
    public void i() {
        if (this.bU == null) {
            this.y();
        }
        else {
            this.H(this.bU);
        }
    }
    
    private void j(final int n, final int n2) {
        if (this.cb == null) {
            return;
        }
        this.bY = 0;
        this.bs = 0;
        this.cq = 1.0 / Math.max(this.cb.width / n, this.cb.height / n2);
        this.ci = this.cq;
        this.bN = this.cb.x;
        this.bh = this.cb.y;
        final Point i = this.I(new Point(0, 0));
        final Point j = this.I(new Point(this.size().width, this.size().height));
        this.cj = new Rectangle(i.x, i.y - Math.abs(j.y - i.y), j.x - i.x, Math.abs(j.y - i.y));
    }
    
    public void k(final String bt) {
        this.bT = bt;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setPaintMode();
        if (!this.ce && this.cr != null) {
            try {
                this.ce = true;
                this.cr.j(this);
                this.p(graphics);
                return;
            }
            catch (Throwable t) {
                System.out.println("Err par " + t);
                t.printStackTrace();
                if (!t.getMessage().equals("done")) {
                    System.out.println("Error while reading file " + t + "");
                }
                this.cf.i(t);
                return;
            }
        }
        try {
            if (this.cr == null && this.cu) {
                this.cu = false;
                this.cf.b();
            }
            if (this.bw != null) {
                this.bw.k();
            }
            this.e(graphics);
        }
        catch (Throwable t2) {
            System.out.println("ERROR IN P:" + t2);
            if (!t2.getMessage().equals("done")) {
                System.out.println("Error while reading file " + t2 + "");
            }
            t2.printStackTrace(System.out);
        }
        if (this.bw != null) {
            this.bw.d();
        }
    }
    
    public void p(final Graphics graphics) {
        if (this.bp != null) {
            graphics.setColor(Color.gray);
            graphics.setFont(new Font("TimesRoman", 1, 14));
            int stringWidth = graphics.getFontMetrics(graphics.getFont()).stringWidth(this.bp);
            if (stringWidth < 50) {
                stringWidth = 50;
            }
            graphics.drawString(this.bp, this.size().width / 2 - stringWidth / 2, 20);
        }
        if (this.bn != null) {
            graphics.drawImage(this.bn, this.size().width - 50 - this.bn.getWidth(null), this.size().height - 50 - this.bn.getHeight(null), null);
        }
    }
    
    public void q() {
        this.L(2.0, this.cb.x + this.cb.width / 2, this.cb.y + this.cb.height / 2);
    }
    
    public void r() {
        if (this.cf.P.IsBlackAndWhite) {
            this.bq = Color.white;
        }
        else {
            this.bq = null;
        }
        this.repaint();
    }
    
    public void s(final C44 bw) {
        this.bw = bw;
    }
    
    public C49(final C45 cf) {
        this.bu = "";
        this.bz = 0L;
        this.bQ = 0L;
        this.cc = 0;
        this.by = -1;
        this.bf = -1;
        this.be = 0;
        this.bP = null;
        this.bA = new char[] { 'E', 'v', 'a', 'l', 'u', 'a', 't', 'i', 'o', 'n', ' ', 'C', 'o', 'p', 'y' };
        this.bF = new String(this.bA);
        this.cn = false;
        this.ce = false;
        this.bq = null;
        this.bD = null;
        this.bR = Color.white;
        this.cp = -1;
        this.bL = 4;
        this.bJ = new Dimension(200, 200);
        this.bi = new Dimension(800, 478);
        this.bH = -1L;
        this.cu = true;
        this.br = -1;
        this.bg = -1;
        this.co = false;
        this.W = false;
        this.cm = 0;
        this.bd = new int[1000][3];
        this.Y = new Vector();
        this.cf = cf;
        this.cl = 1;
        final double n = -1.0;
        this.ci = n;
        this.cq = n;
        this.ck = Integer.MAX_VALUE;
        this.cd = Integer.MAX_VALUE;
        this.bW = 0;
        this.bK = 0;
        (this.V = new C03(this)).addObserver(this);
        this.V.c("ExpocadVR Copyright 2006");
        this.V.g();
        this.V.c("Zoom In");
        this.V.c("Zoom Out");
        this.V.c("Zoom Window");
        this.V.c("Zoom All");
        this.V.g();
        this.V.c("Black \\ White Background");
        this.V.c("Highlight Rented Booths");
        this.V.c("Highlight Available Booths");
        this.bn = cf.s();
    }
    
    private void t() {
        this.cp = -1;
        this.by = -1;
        this.bl = -1;
        this.bf = 504;
    }
    
    public void u() {
        this.ct = null;
        this.Y.removeAllElements();
        if (this.bw != null) {
            this.bw.g();
        }
    }
    
    public void v(final String ct) {
        this.ct = ct;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (n3 > 0 && n4 > 0) {
            this.j(this.size().width, this.size().height);
        }
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    private void w(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (C12.f()) {
            graphics.setColor(Color.lightGray);
            graphics.setFont(new Font("TimesRoman", 1, 68));
            graphics.drawString(this.bF, Math.max(this.size().width / 2 - 295, 30), Math.max(this.size().height / 2 - 60, 50));
        }
        if (C12.a()) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.red);
            final Font font = graphics.getFont();
            graphics.setFont(new Font("Arial", 1, 18));
            graphics.drawString("WARNING: ExpoVR2 has expired, to purchase go to www.expocad.com.", 10, 100);
            graphics.setColor(color);
            graphics.setFont(font);
        }
    }
    
    public void x(final C17 bm) {
        if (this.cb != null) {
            return;
        }
        if (this.ct == null || this.ct.equals(bm.c())) {
            this.bM = bm;
            this.cb = bm.a();
        }
    }
    
    protected void y() {
        int p = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int n = Integer.MIN_VALUE;
        int n2 = Integer.MIN_VALUE;
        for (int i = 0; i < this.Y.size(); ++i) {
            final C35 c35 = this.Y.elementAt(i);
            if (c35 instanceof C28 && ((C28)c35).d() && !(c35 instanceof C37)) {
                final C28 c36 = (C28)c35;
                if (c36.P < p) {
                    p = c36.P;
                }
                if (c36.B < b) {
                    b = c36.B;
                }
                if (n < c36.P + c36.H) {
                    n = c36.P + c36.H;
                }
                if (n2 < c36.B + c36.J) {
                    n2 = c36.B + c36.J;
                }
            }
        }
        this.H(this.bU = new Rectangle(p, b, n - p, n2 - b));
    }
    
    protected void z(final Event event) {
        if (this.by == -1) {
            return;
        }
        this.cp = -1;
        if (Math.abs(this.by - event.x) > 5 && Math.abs(this.bl - event.y) > 5) {
            final double n = Math.abs(event.y - this.bl) / this.size().height;
            final Point i = this.I(new Point(Math.min(this.by, event.x) + Math.abs(event.x - this.by) / 2, Math.min(this.bl, event.y) + Math.abs(event.y - this.bl) / 2));
            this.L(n, i.x, i.y);
            this.bk = -1;
            this.Z = -1;
        }
        else if (Math.abs(this.by - event.x) > 5 || Math.abs(this.bl - event.y) > 5) {
            final double n2 = 2.0 * (1.0 + Math.abs(event.y - this.bl) / this.size().height);
            final int n3 = this.size().width / 2;
            final int n4 = this.size().height / 2;
            if (n2 < 0.1) {
                return;
            }
            final Point j = this.I(new Point(n3, n4));
            this.L(n2, j.x, j.y);
            this.bk = -1;
            this.Z = -1;
        }
    }
    
    public void A(final boolean w) {
        this.W = w;
    }
    
    public boolean handleEvent(final Event event) {
        this.bu = "";
        if (C12.c()) {
            return true;
        }
        if (event.id == 502 && (event.modifiers & 0x4) != 0x0 && !this.V.a()) {
            this.V.d(event.x, event.y);
            this.V.e(this.getGraphics());
            return true;
        }
        if (this.bw != null && this.bw.o(event)) {
            return true;
        }
        if (this.bu != "") {
            return true;
        }
        if (this.V.f(event)) {
            return true;
        }
        if (event.id == 502 && (event.modifiers & 0x4) == 0x0) {
            this.V.b();
            this.repaint();
        }
        if (event.id == 501) {
            this.by = event.x;
            this.bl = event.y;
            this.bk = event.x;
            this.Z = event.y;
            this.bS = this.by;
            this.ch = this.bl;
            this.bL = 4;
            this.br = -1;
            this.bg = -1;
        }
        else if (event.id == 502 && this.bf != 504) {
            this.bL = -1;
            this.B(event);
            if (this.cl == 0) {
                this.z(event);
                this.cl = 1;
            }
            else if (this.cl == 1) {
                System.currentTimeMillis();
                this.c(event);
            }
        }
        else if (event.id == 506) {
            this.B(event);
        }
        if (event.id == 501) {
            this.by = event.x;
            this.bl = event.y;
        }
        else if (event.id == 502) {
            this.by = -1;
            this.bl = -1;
        }
        else if (event.id == 506) {
            this.bk = event.x;
            this.Z = event.y;
        }
        this.bf = event.id;
        return super.handleEvent(event);
    }
    
    private void B(final Event event) {
        if (this.cl == 0) {
            this.bI = Math.max(event.x, this.by);
            this.bX = Math.max(event.y, this.bl);
            this.bS = Math.min(event.x, this.by);
            this.ch = Math.min(event.y, this.bl);
        }
        else {
            this.bS = event.x;
            this.ch = event.y;
        }
        final Graphics graphics = this.getGraphics();
        if (this.by != -1 && this.cl == 1) {
            graphics.copyArea(0, 0, this.size().width, this.size().height, event.x - this.bk, event.y - this.Z);
            if (this.bq != null) {
                graphics.setColor(this.bq);
            }
            else {
                graphics.setColor(Color.black);
            }
            if (event.y - this.Z > 0) {
                graphics.fillRect(0, 0, this.size().width, event.y - this.Z);
            }
            else {
                graphics.fillRect(0, this.size().height + event.y - this.Z, this.size().width, Math.abs(event.y - this.Z));
            }
            if (event.x - this.bk > 0) {
                graphics.fillRect(0, 0, event.x - this.bk, this.size().height);
            }
            else {
                graphics.fillRect(this.size().width + event.x - this.bk, 0, Math.abs(event.x - this.bk), this.size().height);
            }
            graphics.dispose();
            return;
        }
        graphics.setXORMode(Color.green);
        switch (this.cp) {
            case 4: {
                graphics.drawLine(this.bj, this.bC, this.X, this.bo);
                break;
            }
            case 2: {
                graphics.drawRect(this.bj, this.bC, this.X - this.bj, this.bo - this.bC);
                break;
            }
        }
        if (this.by != -1) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.green);
            if (this.cl == 0) {
                this.cp = 2;
            }
            graphics.setColor(color);
        }
        if (this.bL == -1) {
            this.cp = -1;
        }
        switch (this.cp) {
            case 4: {
                graphics.drawLine(this.bS, this.ch, this.bI, this.bX);
                break;
            }
            case 2: {
                graphics.drawRect(this.bS, this.ch, this.bI - this.bS, this.bX - this.ch);
                break;
            }
        }
        this.bj = this.bS;
        this.bC = this.ch;
        this.X = this.bI;
        this.bo = this.bX;
        graphics.dispose();
    }
    
    public void C(final int n, final int n2) {
        final Point i = this.I(new Point(n, n2));
        final Point j = this.I(new Point(0, 0));
        final Point point = new Point(this.cb.width / 2 + this.cb.x - (i.x - j.x), this.cb.height / 2 + this.cb.y - (i.y - j.y));
        this.L(1.0, point.x, point.y);
    }
    
    public void D(final String bp) {
        this.bp = bp;
    }
    
    public void update(final Observable observable, final Object o) {
        if (observable instanceof C03 && o instanceof String) {
            if (o == "ExpocadVR Copyright 2003") {
                this.cf.P.Copyright();
            }
            else if (o == "Zoom In") {
                this.f();
            }
            else if (o == "Zoom Out") {
                this.q();
            }
            else if (o == "Zoom Window") {
                this.G(0);
            }
            else if (o == "Zoom All") {
                this.i();
            }
            else if (o == "Black \\ White Background") {
                this.cf.P.SetWhiteBackground();
            }
            else if (o == "Highlight Rented Booths") {
                this.cf.P.HighlightRentedBooths();
            }
            else if (o == "Highlight Available Booths") {
                this.cf.P.HighlightAvailableBooths();
            }
            else if (o == "Highlight Favorite Booths") {
                this.cf.P.HighlightFavoriteBoothsMenuOption();
            }
            else if (o == "Add booth to favorites") {
                this.cf.P.AddBoothToFavorites();
            }
        }
    }
    
    public void E(final C17 c17) {
        this.H(c17.a());
    }
    
    public void F(final C20 c20) {
        for (int i = 0; i < this.cm; ++i) {
            c20.DelObject(this.cm);
        }
        this.cm = 0;
    }
    
    public void G(final int cl) {
        this.cl = cl;
        this.cf.c(cl, this);
    }
    
    public void H(final Rectangle cb) {
        this.cb = cb;
        this.L(1.0, this.cb.x + this.cb.width / 2, this.cb.y + this.cb.height / 2);
    }
    
    public Point I(final Point point) {
        return new Point((int)(this.bN + (point.x + this.bY) / this.cq), (int)(this.bh + (this.size().height - point.y + this.bs) / this.ci));
    }
    
    public void J(final C15 c15, final C35 c16) {
        c16.b(c15, this.bN, this.cq, this.bY, this.bh, this.ci, this.bs, this.size().height);
    }
    
    public void L(final double n, final int n2, final int n3) {
        if (this.cb == null) {
            System.out.println("Warning #43435 ");
            return;
        }
        final int n4 = (int)(this.cb.width * n);
        final int n5 = (int)(this.cb.height * n);
        this.cb = new Rectangle(n2 - n4 / 2, n3 - n5 / 2, n4, n5);
        this.j(this.size().width, this.size().height);
        this.repaint();
        this.bQ = System.currentTimeMillis();
        this.bz = this.bQ;
    }
    
    public void M() {
        final double n = -1.0;
        this.ci = n;
        this.cq = n;
        this.ck = Integer.MAX_VALUE;
        this.cd = Integer.MAX_VALUE;
        this.bW = 0;
        this.bK = 0;
        this.bG = null;
        this.bU = null;
        this.cp = -1;
        this.bZ = null;
        this.ce = false;
        this.cn = false;
        this.bM = null;
        this.bH = -1L;
        this.cr = null;
        this.by = -1;
        this.bl = -1;
        this.bf = -1;
        this.bZ = null;
    }
    
    public void N(final C35 c35) {
        if (this.bZ == null && this.size().width > 0 && this.size().height > 0) {
            this.bZ = new C15(this, this.getGraphics(), this.size().width, this.size().height);
            if (this.bq != null) {
                this.bZ.d(this.bq);
                this.bZ.y(true);
            }
            else {
                this.bZ.o(Color.black);
                this.bZ.y(false);
            }
            if (this.bG != null) {
                this.bZ.i(this.bG);
            }
        }
        if (this.cq == -1.0) {
            int n = 0;
            while (n < 3 && (this.size().width < 0 || this.size().height < 0)) {
                this.resize(500, 500);
                this.layout();
                if (this.preferredSize().width > 0 && this.preferredSize().height > 0) {
                    this.resize(this.preferredSize());
                }
                ++n;
                try {
                    System.out.println("SLEEPING");
                    Thread.currentThread();
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
            this.j(this.size().width, this.size().height);
        }
        if (this.bZ != null) {
            c35.b(this.bZ, this.bN, this.cq, this.bY, this.bh, this.ci, this.bs, this.size().height);
        }
    }
    
    public Dimension minimumSize() {
        return this.bJ;
    }
    
    public void O(final C35 c35) {
        this.Y.addElement(c35);
        if (c35 instanceof C38) {
            this.bG = (C38)c35;
        }
        if (c35 instanceof C17 && this.ct != null && this.ct.equals(((C17)c35).c())) {
            this.x((C17)c35);
        }
        if ((this.cb != null || !(c35 instanceof C28)) && !this.W) {
            this.N(c35);
        }
        if (c35 instanceof C28 && !(c35 instanceof C37)) {
            final C28 c36 = (C28)c35;
            if (c36.P < this.ck) {
                this.ck = c36.P;
            }
            if (c36.B < this.cd) {
                this.cd = c36.B;
            }
            if (this.bW < c36.P + c36.H) {
                this.bW = c36.P + c36.H;
            }
            if (this.bK < c36.B + c36.J) {
                this.bK = c36.B + c36.J;
            }
        }
    }
    
    public void P(final int n, final boolean b) {
        for (int size = this.Y.size(), i = 0; i < size; ++i) {
            final C35 c35 = this.Y.elementAt(i);
            if (c35 instanceof C28) {
                final C28 c36 = (C28)c35;
                if (c36.g() == n || n == 0) {
                    c36.h(b);
                }
            }
        }
    }
    
    public void Q(final C18 cr) {
        this.cr = cr;
    }
    
    public void R() {
        this.bU = new Rectangle(this.ck, this.cd, this.bW - this.ck, this.bK - this.cd);
        this.j(this.size().width, this.size().height);
        if (this.cb == null) {
            this.cb = this.bU;
            this.i();
        }
        this.cn = true;
        this.bZ = null;
        this.bH = System.currentTimeMillis();
        this.t();
        if (this.W) {
            this.e(this.getGraphics());
        }
        this.W = false;
    }
    
    public Dimension preferredSize() {
        if (this.size().width > 0 && this.size().height > 0) {
            return this.size();
        }
        return this.bi;
    }
}
