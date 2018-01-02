// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

class be extends av
{
    static final double ef;
    static final int ec = 640;
    static final int dX = 480;
    static final int d7 = 0;
    static final int d5 = 20;
    static final int dZ = 45;
    static final int d0 = 15;
    static int ea;
    static int ei;
    static int d4;
    static final int dV = 1;
    static final int dU = 2;
    static final int dK = 3;
    static final int dE = 4;
    static final int d1 = 5;
    static final int dF = 6;
    static final int dL = 7;
    static final int dC = 8;
    static final int d2 = 9;
    static final int d6 = 10;
    static String ej;
    static String el;
    static String dW;
    static String em;
    static int dG;
    Rectangle eg;
    Rectangle eh;
    f dD;
    ap ed;
    Point ek;
    int dR;
    int eo;
    aj dN;
    int ee;
    int d8;
    boolean dP;
    boolean dQ;
    ax en;
    a0 dT;
    az d9;
    boolean dS;
    int dI;
    boolean eb;
    boolean d3;
    y dO;
    private boolean dH;
    private a7 dM;
    private static final int dJ = 10;
    private Image dY;
    private long ep;
    
    public be(final ChartManager chartManager, final DataSource dataSource, final boolean b, final boolean b2) {
        super(chartManager, dataSource, new Insets(be.ei, 15, be.d4, be.ea));
        this.eg = null;
        this.eh = null;
        this.ek = new Point(0, 0);
        this.dN = null;
        this.dP = false;
        this.dQ = false;
        this.d9 = null;
        this.dS = false;
        this.dI = -1;
        this.eb = false;
        this.d3 = false;
        this.dH = false;
        this.dM = new a7("ChartLock");
        this.ep = 0L;
        this.dD = new f(this, this.b(), this.case().bottom);
        final a a = new a();
        this.addMouseListener(a);
        this.addMouseMotionListener(a);
        this.dO = new y(this, b, b2);
        if (dataSource.D() > 0) {
            this.a(true);
        }
        this.d8 = 1;
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize;
        final Dimension dimension = minimumSize = super.getMinimumSize();
        minimumSize.height += 10;
        return dimension;
    }
    
    public void a(final bi try1) {
        super.a(try1);
        this.dD.try = try1;
    }
    
    protected void a(final boolean b, final boolean b2) {
        super.a(b, b2);
        if (this.dO != null) {
            this.dO.a(this.b(), this.char());
        }
        this.dD.a(this.b(), this.case().bottom);
    }
    
    protected void E() {
        int n = 0;
        boolean b = true;
        float n2;
        if (super.bK.gz) {
            n2 = super.bK.gV;
        }
        else {
            n2 = this.char() / super.bK.gV;
        }
        for (float n3 = 0.0f; n3 < this.char(); n3 += n2) {
            if (b) {
                this.else().setColor(super.bK.gN);
            }
            else {
                this.else().setColor(super.bK.gM);
            }
            b = !b;
            final int round = Math.round(n3 + n2);
            this.else().fillRect(0, n, this.b(), round - n + 1);
            n = round;
        }
        if (super.parent.X()) {
            super.parent.eP.a(10, 10);
            super.parent.eP.a(this.else());
        }
    }
    
    public void if(final DataSource dataSource) {
        if (this.dO != null && this.dO.int() != null) {
            this.dO.int().try(dataSource);
        }
        super.if(dataSource);
        this.a(true);
    }
    
    protected void a(final Graphics graphics, final int n) {
        final int n2 = this.case().left + this.g(n);
        final int n3 = this.case().top + this.i(n);
        if (n2 >= this.case().left && n2 <= this.case().left + this.b() && n3 >= this.case().top && n3 <= this.case().top + this.char()) {
            graphics.drawLine(this.case().left + 1, n3, this.case().left + this.b() - 1, n3);
            super.bD.a(graphics, n3);
            this.do(graphics, n);
        }
    }
    
    protected void if(final Graphics graphics, final int n) {
        final int n2 = this.case().left + this.g(n);
        final int n3 = this.case().top + this.i(n);
        final Color color = graphics.getColor();
        graphics.setColor(super.bD.do);
        if (n2 >= this.case().left && n2 <= this.case().left + this.b() && n3 >= this.case().top && n3 <= this.case().top + this.char()) {
            graphics.drawLine(this.case().left + 1, n3, this.case().left + this.b() - 1, n3);
            graphics.drawLine(n2, n3, n2, this.char() + 20);
            super.bD.a(graphics, n3);
            this.dO.int().for(graphics, n);
        }
        graphics.setColor(color);
    }
    
    private void do(final Graphics graphics, final int n) {
    }
    
    public void B() {
        if (this.d8 == 2 && this.dP) {
            final Rectangle a = this.a(this.eg);
            final int n = this.dO.null() - this.dO.byte();
            final int n2 = (int)Math.ceil(a.x * n / this.b());
            final int n3 = (int)Math.floor((a.x + a.width) * n / this.b());
            if (n3 - n2 >= 4) {
                this.dO.do(this.dO.byte() + n2, this.dO.byte() + n3);
                this.a(true);
            }
            if (this.eh != null) {
                super.parent.a(this.eh, true);
            }
            this.dP = false;
            this.dQ = false;
            return;
        }
        if (this.d8 == 3 && this.dP) {
            this.dO.long();
            this.a(true);
            this.dP = false;
            this.dQ = false;
        }
    }
    
    protected Image w() {
        if (this.dY == null) {
            this.dY = this.createImage(this.getSize().width, this.case().top - 1);
        }
        return this.dY;
    }
    
    protected void c() {
        super.c();
        this.dY = null;
    }
    
    private void null(final Graphics graphics) {
        final Image w = this.w();
        this.case(w.getGraphics());
        graphics.drawImage(w, 0, 0, this);
    }
    
    private void case(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(super.bK.gY);
        graphics.fillRect(0, 0, this.b(), this.case().top);
        final Font font = graphics.getFont();
        final Font byte1 = super.bD.byte;
        graphics.setFont(a0.if);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final ChartBody int1 = this.dO.int();
        final DataSource al = int1.al();
        graphics.setColor(int1.ft);
        String s;
        if (al.r().for().equals("")) {
            s = new String(al.r().do() + " , ");
        }
        else {
            s = new String(al.r().for() + " : " + al.r().do() + " , ");
        }
        final String string = al.C().toString();
        final int n = fontMetrics.getAscent() + fontMetrics.getLeading();
        final int left = this.case().left;
        final String string2 = ", # " + new Integer(this.dO.null() - this.dO.byte() + 1).toString();
        String s2 = "";
        if (al.m() >= 0) {
            final int m = al.m();
            if (m > 0) {
                s2 = ", " + be.dW + " " + m + " " + be.em;
            }
            else {
                s2 = ", " + be.el;
            }
        }
        final String concat = s.concat(string).concat(string2).concat(s2);
        graphics.drawString(concat, left, n);
        int n2 = left + (fontMetrics.stringWidth(concat) + 5);
        for (int i = 0; i < this.ed.if(); ++i) {
            final CompositeAnalysis if1 = this.ed.if(i);
            if (if1 instanceof MovAvg) {
                final MovAvg movAvg = (MovAvg)if1;
                final String string3 = ", MA" + new Integer(movAvg.bW).toString() + " = " + super.parent.eZ.a(movAvg.i());
                graphics.setColor(movAvg.bU);
                graphics.drawString(string3, n2, n);
                n2 += fontMetrics.stringWidth(string3) + 5;
            }
        }
        int left2 = this.case().left;
        final int n3 = n + (n + 5);
        for (int j = 0; j < this.dO.int.size(); ++j) {
            final ChartBody int2 = this.dO.int(j);
            if (j != this.dO.do()) {
                final DataSource al2 = int2.al();
                graphics.setColor(this.dO.int(j).ft);
                final String if2 = al2.r().if();
                final Rectangle rectangle = new Rectangle(left2, n3 - (fontMetrics.getAscent() + fontMetrics.getLeading()), fontMetrics.getAscent() - 2, fontMetrics.getAscent() - 2);
                if (int2.fn) {
                    graphics.fillRect(rectangle.getLocation().x, rectangle.getLocation().y, rectangle.width, rectangle.height);
                }
                else {
                    graphics.drawRect(rectangle.getLocation().x, rectangle.getLocation().y, rectangle.width, rectangle.height);
                }
                final int n4 = left2 + (rectangle.width + 2);
                final Rectangle rectangle2 = new Rectangle(n4, n3 - (fontMetrics.getAscent() + fontMetrics.getLeading()), fontMetrics.stringWidth(if2) + rectangle.width + 7, fontMetrics.getAscent() + fontMetrics.getLeading());
                int2.a(rectangle2, rectangle);
                graphics.drawString(if2, n4, n3);
                left2 = n4 + (rectangle2.width + 5);
            }
        }
        graphics.setColor(color);
        graphics.setFont(font);
    }
    
    private void int(final Graphics graphics) {
        aj aj = null;
        if (!this.dQ) {
            this.dR = this.l(super.bR.x);
            super.bR.x = this.g(this.dR);
            if (this.ee != 4) {
                super.bR.y = this.i(this.dR);
                this.a(graphics, this.dR);
            }
            else {
                final Point br = super.bR;
                br.x += this.case().left;
                this.a(graphics, super.bR.x, super.bR.y);
                final Point br2 = super.bR;
                br2.x -= this.case().left;
                final Point br3 = super.bR;
                br3.y -= this.case().top;
            }
            this.ek.setLocation(super.bR);
        }
        else if (this.dQ && !this.dP) {
            this.eo = this.l(super.bR.x);
            super.bR.x = this.g(this.eo);
            if (this.ee != 4) {
                super.bR.y = this.i(this.eo);
                this.a(graphics, this.eo);
            }
            else {
                final Point br4 = super.bR;
                br4.x += this.case().left;
                this.a(graphics, super.bR.x, super.bR.y);
                final Point br5 = super.bR;
                br5.x -= this.case().left;
                final Point br6 = super.bR;
                br6.y -= this.case().top;
            }
            if (this.ee != 3) {
                graphics.drawLine(this.case().left + this.ek.x, this.case().top + this.ek.y, this.case().left + super.bR.x, this.case().top + super.bR.y);
            }
        }
        else {
            if (this.d9 != null && this.d9.isVisible()) {
                return;
            }
            this.eo = this.l(super.bR.x);
            if (this.ee != 4) {
                super.bR.y = this.i(this.eo);
            }
            this.eo += this.dO.byte();
            this.dR += this.dO.byte();
            int n = 1;
            if (this.d9 != null) {
                final int new1 = this.d9.new;
                float n2 = this.dR;
                float k = this.k(this.dR);
                float n3 = this.eo;
                float i = this.k(this.eo);
                if (this.dO.int() instanceof as) {
                    final float j = this.j(this.dR);
                    final float l = this.j(this.eo);
                    final float f = this.f(this.dR);
                    final float f2 = this.f(this.eo);
                    if (j < l) {
                        k = j;
                        i = f2;
                    }
                    else {
                        k = f;
                        i = l;
                    }
                }
                if (n2 > n3) {
                    final float n4 = n2;
                    n2 = n3;
                    n3 = n4;
                    final float n5 = k;
                    k = i;
                    i = n5;
                }
                final float n6 = k - i;
                this.d9.setVisible(true);
                this.getParent().requestFocus();
                int n7 = 0;
                if (this.d9.try > 0) {
                    for (int n8 = 0; n8 < this.d9.try; ++n8) {
                        final float n9 = this.d9.else[n8] / 100.0f * n6 + i;
                        aj aj2;
                        if (new1 == 1) {
                            aj2 = new aj(this.null().b6.elementAt((int)n2), k, this.null().b6.elementAt((int)n3), n9, 4);
                        }
                        else {
                            aj2 = new aj(this.null().b6.elementAt((int)n2), n9, this.null().b6.elementAt((int)n3), n9, 4);
                        }
                        aj2.a(this);
                        n7 += this.a(aj2);
                        aj2.a(this.else(), 0, 0);
                    }
                }
                if (n7 != 0) {
                    this.a(true);
                    this.d();
                    this.null().bO.a(this.else());
                    this.ed.a(this.else());
                }
            }
            else {
                if (this.dR != this.eo && (this.ee == 1 || this.ee == 2)) {
                    aj = new aj(this.null().b6.elementAt(this.dR), this.k(this.dR), this.null().b6.elementAt(this.eo), this.k(this.eo), this.ee);
                    aj.a(this);
                    n = this.a(aj);
                    aj.a(this.else(), 0, 0);
                }
                else if (this.ee == 3) {
                    aj = new aj(this.null().b6.elementAt(this.eo), this.k(this.eo), this.null().b6.elementAt(this.eo), this.k(this.eo), this.ee);
                    aj.a(this);
                    n = this.a(aj);
                    aj.a(this.else(), 0, 0);
                }
                else if (this.ee == 4) {
                    if (this.ek.x == super.bR.x || Math.abs(this.ek.y - super.bR.y + this.case().top) < 5) {
                        this.dQ = false;
                        this.dP = false;
                        this.dR = -1;
                        return;
                    }
                    aj = new aj(this.null().b6.elementAt(this.dR), super.bC + (this.char() - this.ek.y - this.byte().bottom) * 1 / super.bI, this.null().b6.elementAt(this.eo), super.bC + (this.char() - super.bR.y + this.case().top - this.byte().bottom) * 1 / super.bI, this.ee);
                    aj.a(this);
                    n = this.a(aj);
                    aj.a(this.else(), 0, 0);
                }
                else if (this.ee == 7) {
                    if (this.ek.x == super.bR.x || Math.abs(this.ek.y - super.bR.y + this.case().top) < 5) {
                        this.dQ = false;
                        this.dP = false;
                        this.dR = -1;
                        return;
                    }
                    this.null().bO.do(7);
                    this.a(true);
                    this.d();
                    this.null().bO.a(this.else());
                    final ah ah = new ah(this);
                    ah.a(this);
                    final aj[] e = ah.e();
                    for (int n10 = 0; n10 < e.length; ++n10) {
                        aj = e[n10];
                        aj.a(this.else(), 0, 0);
                        n = this.a(aj);
                    }
                }
                else if (this.ee == 8) {
                    final ag r = super.parent.R();
                    if (r.isVisible()) {
                        return;
                    }
                    r.setTitle("Gann Mesh");
                    r.setVisible(true);
                    this.getParent().requestFocus();
                    if (r.do[0] == null) {
                        this.dQ = false;
                        this.dP = false;
                        this.dR = -1;
                        return;
                    }
                    this.null().bO.do(8);
                    this.a(true);
                    this.d();
                    this.null().bO.a(this.else());
                    final Point point = (Point)r.do[0];
                    final int intValue = (int)r.do[1];
                    aj = new ao(this, point.x * intValue, point.y * intValue);
                    aj.a(this);
                    aj.a(this.else(), 0, 0);
                    n = this.a(aj);
                }
                if (n == 0) {
                    aj.a(this.else(), 0, 0);
                }
                else if (n == -1) {
                    this.a(true);
                    this.d();
                    this.null().bO.a(this.else());
                    this.ed.a(this.else());
                }
            }
            graphics.drawImage(this.long(), this.case().left, this.case().top, this);
            this.dQ = false;
            this.dP = false;
            this.dR = -1;
        }
    }
    
    private int a(final aj aj) {
        final int a = this.null().bO.a(aj);
        super.parent.if(this.null(), super.parent.eH);
        return a;
    }
    
    private void F() {
        this.null().bO.a();
        super.parent.if(this.null(), super.parent.eH);
    }
    
    private void h(final int n) {
        if (n >= 0) {
            this.null().bO.a(n);
            super.parent.if(this.null(), super.parent.eH);
        }
    }
    
    protected void a(final Graphics graphics) {
        this.dM.do();
        try {
            final DataSource x = this.dO.int().al().x();
            x.o().do();
            try {
                this.d3 = false;
                if (x.D() == 0) {
                    this.for(graphics);
                }
                else {
                    this.else(graphics);
                    this.if(graphics);
                }
            }
            finally {
                x.o().a();
            }
        }
        finally {
            this.dM.a();
        }
    }
    
    private void else(final Graphics graphics) {
        super.parent.eZ.a(this.null().p(), this.null().A());
        this.B();
        super.bC = this.dO.try();
        super.bF = this.dO.a();
        if (super.bC == super.bF) {
            super.bF *= 1.05;
            super.bC *= 0.95;
        }
        super.bI = (this.char() - this.byte().bottom - this.byte().top) / (super.bF - super.bC);
        super.bJ = this.dO.goto();
        if (this.try()) {
            super.bD.do();
            this.dD.a(true);
            super.parent.eR.a();
            this.ed.a();
            this.d();
            this.ed.a(this.else());
            this.null().bO.a(this);
            this.null().bO.a(this.else());
        }
        this.null(graphics);
        graphics.setColor(super.bK.gP);
        graphics.drawRect(this.case().left - 1, this.case().top - 1, this.b() + 1, this.char() + 1);
        switch (this.d8) {
            case 1: {
                this.char(graphics);
                break;
            }
            case 2: {
                this.D();
            }
            case 3: {
                this.new(graphics);
                break;
            }
            case 4: {
                this.byte(graphics);
                break;
            }
            case 5: {
                this.void(graphics);
                break;
            }
            case 7: {
                this.long(graphics);
                break;
            }
            case 6: {
                this.x();
                break;
            }
            case 8: {
                this.try(graphics);
                break;
            }
            case 9:
            case 10: {
                this.goto(graphics);
                break;
            }
        }
    }
    
    private void goto(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        if (!this.dQ) {
            this.dN = null;
            this.eo = this.null().bO.a(super.bR.x, super.bR.y);
            if (this.eo == -1) {
                this.d8 = 1;
                this.dQ = false;
                this.dP = false;
                this.setCursor(new Cursor(0));
            }
            else {
                this.null().bO.if(this.eo).if(graphics, this.case().left, this.case().top);
                this.dR = this.eo;
                this.dP = false;
            }
        }
        else {
            if (this.dN == null) {
                (this.dN = this.null().bO.if(this.eo).void()).do(0.0f);
                this.dN.for((float)(this.null().D() - 1));
                if (this.d8 == 10) {
                    this.a(true);
                    this.d();
                    this.ed.a(this.else());
                    this.h(this.eo);
                    this.null().bO.a(this.else());
                }
            }
            final int l = this.l(super.bR.x);
            this.dN.a(l + this.dO.byte(), super.bC + (this.char() - (this.dN.if() * this.g(l) + (super.bR.y - this.case().top) - this.dN.if() * (super.bR.x - this.case().left)) - this.byte().bottom) * 1.0f / super.bI);
            this.dN.a(this);
            if (this.dQ && !this.dP) {
                graphics.drawImage(this.long(), this.case().left, this.case().top, this);
                graphics.clipRect(this.case().left, this.case().top, this.b(), this.char());
                this.dN.a(graphics, this.case().left, this.case().top);
            }
            else {
                final int a = this.a(this.dN);
                if (a == 0) {
                    this.dN.a(this.else(), 0, 0);
                }
                else if (a == -1) {
                    this.a(true);
                    this.d();
                    this.null().bO.a(this.else());
                    this.ed.a(this.else());
                }
                graphics.drawImage(this.long(), this.case().left, this.case().top, this);
                this.dQ = false;
                this.d3 = true;
                this.dR = -1;
            }
        }
    }
    
    private void try(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        if (!super.parent.eO && (this.dS || (!this.dS && this.dI == -1))) {
            this.eo = this.l(super.bR.x);
            this.dS = false;
            this.dI = this.eo + this.dO.byte();
        }
        this.if(graphics, this.dI - this.dO.byte());
        this.dT.if(this.dI);
    }
    
    private void x() {
        this.h(-1);
        this.null().bO.a();
        this.d8 = 1;
        this.dQ = false;
        this.dP = false;
        this.setCursor(new Cursor(0));
        this.a(true);
        this.d();
        this.ed.a(this.else());
        this.repaint();
        this.d3 = true;
    }
    
    private void long(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        this.eo = this.null().bO.a(super.bR.x, super.bR.y);
        if (this.eo == -1) {
            this.d8 = 1;
            this.dQ = false;
            this.dP = false;
            this.setCursor(new Cursor(0));
        }
        else {
            this.null().bO.if(this.eo).if(graphics, this.case().left, this.case().top);
            this.dR = this.eo;
            final aj if1 = this.null().bO.if(this.eo);
            this.en.a(if1, this.eo, if1.if((float)this.dO.null()), if1.if((float)(this.dO.null() + 1)));
        }
    }
    
    private void void(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        this.eo = this.null().bO.a(super.bR.x, super.bR.y);
        if (this.eo == -1) {
            this.d8 = 1;
            this.dQ = false;
            this.dP = false;
            this.setCursor(new Cursor(0));
        }
        else if (!this.dQ) {
            this.null().bO.if(this.eo).if(graphics, this.case().left, this.case().top);
            this.dR = this.eo;
        }
        else {
            this.a(true);
            this.d();
            this.ed.a(this.else());
            this.h(this.eo);
            this.null().bO.a(this.else());
            graphics.drawImage(this.long(), this.case().left, this.case().top, this);
            this.dQ = false;
            this.d3 = true;
            this.dR = -1;
        }
    }
    
    private void byte(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        this.int(graphics);
        this.d3 = true;
    }
    
    private void new(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        if (this.d8 == 3) {
            this.d8 = 1;
            this.dQ = false;
            this.dP = false;
            this.setCursor(new Cursor(0));
        }
    }
    
    private void D() {
        if (this.d8 == 2 && this.dQ) {
            if (this.eh != null) {
                super.parent.a(this.eh, false);
            }
            final Rectangle a = this.a(this.eg);
            super.parent.a(a, true);
            this.eh = null;
            this.eh = a;
        }
    }
    
    private void char(final Graphics graphics) {
        graphics.drawImage(this.long(), this.case().left, this.case().top, this);
        super.bD.a(graphics);
        this.dD.if(graphics);
        if (((ChartManager)this.getParent()).e1 && !this.eb) {
            this.a(graphics, super.bR.x, super.bR.y);
        }
    }
    
    private void for(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Font font = graphics.getFont();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(super.bK.gW);
        graphics.setFont(a0.if);
        final String s = new String(be.ej + "  [" + this.null().r().do() + "]");
        graphics.drawString(s, (size.width - graphics.getFontMetrics().stringWidth(s)) / 2, size.height / 2);
        graphics.setFont(font);
        final Container parent = super.parent.getParent();
        if (parent instanceof AppletChart) {
            ((AppletChart)parent).animate(be.ej);
        }
    }
    
    public Insets A() {
        return new Insets(4, 4, 5, 5);
    }
    
    private Rectangle a(final Rectangle rectangle) {
        int n = rectangle.x;
        int n2 = rectangle.y;
        int width = rectangle.width;
        int height = rectangle.height;
        if (width < 0) {
            width = 0 - width;
            n = n - width + 1;
            if (n < 0) {
                width += n;
                n = 0;
            }
        }
        if (n + width > this.case().left + this.b()) {
            width = this.case().left + this.b() - n;
        }
        if (n2 + height > this.case().top + this.char()) {
            height = this.case().top + this.char() - n2;
        }
        if (width == 0) {
            width = 1;
        }
        if (height == 0) {
            height = 1;
        }
        if (n < this.case().left) {
            width += n - this.case().left;
            n = this.case().left;
        }
        if (n2 < this.case().top) {
            height += n2 - this.case().top;
            n2 = this.case().top;
        }
        return new Rectangle(n - this.case().left, n2 - this.case().top, width, height);
    }
    
    private static int a(final int n, final int n2, final int n3, final int n4) {
        final int abs = Math.abs(n - n3);
        final int abs2 = Math.abs(n2 - n4);
        return abs * abs + abs2 * abs2;
    }
    
    public bk y() {
        return this.dO.if();
    }
    
    public int g(final int n) {
        return this.dO.if(n);
    }
    
    public int i(final int n) {
        return this.dO.a(n, super.bR, this.ee);
    }
    
    public float k(final int n) {
        return this.dO.if(n, this.ee);
    }
    
    public float f(final int n) {
        return this.dO.byte(n);
    }
    
    public float j(final int n) {
        return this.dO.a(n);
    }
    
    private int l(final int n) {
        return this.dO.try(n);
    }
    
    protected void d() {
        if (this.dO.case()) {
            this.E();
            super.bC = this.dO.try();
            super.bF = this.dO.a();
            if (super.bC == super.bF) {
                super.bF *= 1.05;
                super.bC *= 0.95;
            }
            super.bI = (this.char() - this.byte().bottom - this.byte().top) / (super.bF - super.bC);
            super.bJ = this.dO.goto();
            final Graphics else1 = this.else();
            final ChartBody int1 = this.dO.int();
            for (int i = 0; i < this.dO.new(); ++i) {
                final ChartBody int2 = this.dO.int(i);
                if (int1 != int2 && int2.fn && int2.ad() > 0) {
                    int2.a(else1, false);
                }
            }
            if (int1.ad() > 0) {
                int1.a(else1, true);
            }
        }
    }
    
    public a7 z() {
        return this.dM;
    }
    
    public void if(final boolean dh) {
        this.dH = dh;
        this.a(true);
        this.dO.int().try(true);
        this.repaint();
    }
    
    public boolean C() {
        return this.dH;
    }
    
    static {
        ef = Math.log(10.0);
        be.ea = 70;
        be.ei = 15;
        be.d4 = 40;
        be.ej = "Loading data. Please wait!";
        be.el = "REAL-TIME data";
        be.dW = "Delayed";
        be.em = "mins.";
        be.dG = 0;
    }
    
    class a extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (be.this.parent.X() && be.this.parent.eP.contains(x - 15, y - be.ei)) {
                be.this.parent.eP.do();
                return;
            }
            for (int i = 0; i < be.this.dO.int.size(); ++i) {
                if (i != be.this.dO.do() && (be.this.dO.int(i).e6.contains(x, y) || be.this.dO.int(i).fw.contains(x, y))) {
                    be.this.a(true);
                    if (be.this.dO.int(i).e6.contains(x, y)) {
                        be.this.dO.do(i);
                    }
                    if (be.this.dO.int(i).fw.contains(x, y)) {
                        be.this.dO.new(i);
                    }
                    be.this.ed.a(be.this.dO.int());
                    return;
                }
            }
            if ((mouseEvent.getModifiers() & 0x4) != 0x4) {
                if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                    switch (be.this.d8) {
                        case 2: {
                            be.this.dQ = true;
                            be.this.eg = new Rectangle(x, y, 0, 0);
                            be.this.eh = null;
                            break;
                        }
                        case 4: {
                            be.this.dQ = true;
                            be.this.bR.setLocation(x, y);
                            break;
                        }
                        case 5: {
                            be.this.dQ = true;
                            break;
                        }
                        case 7: {
                            be.this.dQ = true;
                            break;
                        }
                        case 9:
                        case 10: {
                            be.this.dQ = true;
                            be.this.bR.setLocation(x, y);
                            break;
                        }
                        case 8: {
                            be.this.parent.eO = !be.this.parent.eO;
                            break;
                        }
                    }
                }
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (be.this.d8 == 2) {
                this.a(mouseEvent);
            }
            if (be.this.d8 == 4 || be.this.d8 == 9 || be.this.d8 == 10) {
                be.this.bR.setLocation(mouseEvent.getX(), mouseEvent.getY());
                be.this.repaint();
            }
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            be.this.eb = false;
            if (be.this.parent.X() && be.this.parent.eP.contains(mouseEvent.getX() - 15, mouseEvent.getY() - be.ei)) {
                be.this.eb = true;
            }
            if (System.currentTimeMillis() - be.this.ep > 20L) {
                be.this.bR.setLocation(mouseEvent.getX(), mouseEvent.getY());
                be.this.ep = System.currentTimeMillis();
                be.this.dS = true;
                be.this.parent.a(be.this.parent.eS, mouseEvent.getX(), mouseEvent.getY(), false);
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            be.this.bL = true;
            be.this.bR.setLocation(mouseEvent.getX(), mouseEvent.getY());
            if ((mouseEvent.getModifiers() & 0x4) == 0x4) {
                if (be.this.d8 == 1) {
                    be.this.d8 = 8;
                    if (be.this.dT == null) {
                        be.this.parent.T();
                    }
                    be.this.parent.eO = false;
                    be.this.dT.else = true;
                    be.this.dR = -1;
                }
                else {
                    if (be.this.en != null && be.this.en.isVisible()) {
                        be.this.en.setVisible(false);
                    }
                    if (be.this.dT != null && be.this.dT.isVisible()) {
                        be.this.dT.setVisible(false);
                    }
                    be.this.d8 = 1;
                    be.this.parent.eO = false;
                }
                be.this.dQ = false;
                be.this.dP = false;
                be.this.setCursor(new Cursor(0));
                be.this.parent.a(be.this.parent.eS, mouseEvent.getX(), mouseEvent.getY(), true);
                return;
            }
            be.this.dP = true;
            if (be.this.d8 == 2) {
                this.a(mouseEvent);
            }
            else {
                be.this.repaint();
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            be.this.bL = true;
            be.this.bR.setLocation(mouseEvent.getX(), mouseEvent.getY());
            be.this.parent.a(be.this.parent.eS, mouseEvent.getX(), mouseEvent.getY(), false);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            be.this.bL = false;
            be.this.parent.a(be.this.parent.eS, -1, -1, false);
        }
        
        void a(final MouseEvent mouseEvent) {
            be.this.eg.setSize(mouseEvent.getX() - be.this.eg.x, mouseEvent.getY() - be.this.eg.y);
            be.this.repaint();
        }
    }
}
