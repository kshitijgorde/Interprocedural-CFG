// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.net.URL;
import netcharts.util.NFParamException;
import netcharts.util.NFParam;
import java.util.Hashtable;
import netcharts.util.NFParamDef;
import java.awt.Event;
import netcharts.util.NFColor;
import java.awt.Polygon;
import netcharts.util.NFSort;
import netcharts.util.NFDebug;
import java.awt.Component;
import java.awt.Point;
import netcharts.util.NFUtil;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Vector;
import netcharts.util.NFCompare;

public class NFPiechart extends NFGraph implements NFCompare
{
    public static final int ON = 0;
    public static final int OFF = 1;
    public static final int NONE = 0;
    public static final int EXTERIOR = 1;
    public static final int RADIAL = 2;
    public static final int PERCENTAGE = 0;
    public static final int DATA = 1;
    public static final int LABEL = 2;
    public static final int PERCENTAGE_FLOAT = 3;
    public static final int PERCENTAGE_INT = 4;
    public static final int EXTERIORLABELOFFSET = 30;
    public static final int EXTERIORLABELINNEROFFSET = 5;
    private Vector a;
    private double b;
    private double c;
    private int d;
    private int e;
    private int f;
    private int g;
    private String h;
    private String i;
    private Vector j;
    private NFLine k;
    private int l;
    private int m;
    private Vector n;
    private Vector o;
    private Vector p;
    private String q;
    private String r;
    private NFLine s;
    private String t;
    private String u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean aa;
    private Vector ab;
    private boolean ac;
    private boolean ad;
    private NFLabel ae;
    private NFLabel af;
    private int ag;
    private int ah;
    private Dimension ai;
    private int aj;
    private int ak;
    private Dimension al;
    private int am;
    private static final int an = 10;
    private int ao;
    private Rectangle ap;
    private int aq;
    private int ar;
    private int as;
    private int at;
    private int au;
    private int av;
    private boolean aw;
    private int ax;
    private boolean ay;
    private NFPieSlice az;
    private int a0;
    private int a1;
    private StringBuffer a2;
    
    public NFPiechart(final Applet applet) {
        this.b = 1.1;
        this.c = this.b;
        this.d = -1;
        this.e = this.d;
        this.f = -1;
        this.g = this.f;
        this.h = null;
        this.i = this.h;
        this.j = null;
        this.k = null;
        this.l = 1;
        this.m = this.l;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = "\n";
        this.r = this.q;
        this.s = null;
        this.t = "Slices";
        this.u = this.t;
        this.v = 0;
        this.w = true;
        this.x = false;
        this.y = this.x;
        this.z = false;
        this.aa = this.z;
        this.ab = new Vector();
        this.ac = false;
        this.ad = false;
        this.ae = new NFLabel();
        this.af = this.ae;
        this.ag = -1;
        this.ah = -1;
        this.ai = new Dimension(this.ag, this.ag);
        this.aj = -1;
        this.ak = -1;
        this.al = new Dimension(this.aj, this.aj);
        this.ao = 40;
        this.aq = 0;
        this.ar = this.aq;
        this.as = 0;
        this.at = 0;
        this.aw = false;
        this.ax = 0;
        this.ay = false;
        this.az = null;
        this.a0 = 0;
        this.a1 = 0;
        this.a2 = null;
        this.a = new Vector();
        this.initPiechart();
        this.initGraph(applet);
    }
    
    protected void initPiechart() {
        (this.k = new NFLine(null)).setStyle(1);
        this.k.setThickness(1);
        this.k.setColor(Color.black);
        final NFArrow nfArrow = new NFArrow();
        nfArrow.setStyle(4);
        nfArrow.setWidth(this.k.getThickness());
        this.k.setArrows(null, nfArrow);
    }
    
    public NFPiechart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this(applet);
        this.reshape(n, n2, n3, n4);
    }
    
    public void dwellChanged() {
        this.removeSliceActiveLabels();
        this.addSliceActiveLabels();
    }
    
    public void setScale(final double n) {
        super.setScale(n);
        final Enumeration<NFPieSlice> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final NFPieSlice nfPieSlice = elements.nextElement();
            if (nfPieSlice.e != null) {
                nfPieSlice.e.setScale(n);
            }
        }
    }
    
    public void setLabelPos(final double c) {
        this.c = c;
    }
    
    private int a(final Vector vector) {
        final Enumeration elements = vector.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            elements.nextElement();
            ++n;
        }
        return n;
    }
    
    public void addSlice(final String s, final double n, final Color color) {
        this.addSlice(s, n, color, null);
    }
    
    private String a(final double n) {
        return this.a(n, this.g, this.i);
    }
    
    private String a(final double n, final int n2, final String s) {
        if (n <= 0.0 || Double.isNaN(n)) {
            return "NaN";
        }
        switch (n2) {
            case 1:
            case 2:
            case 5: {
                return NFUtil.formatNumericValue(n, n2, s, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
            }
            default: {
                return Double.toString(n);
            }
        }
    }
    
    public void addSlice(final String s, final double c, final Color d, final NFActiveLabel f) {
        final NFPieSlice initSlice = this.initSlice(s);
        initSlice.c = c;
        initSlice.d = d;
        initSlice.f = f;
        if (super.dwell != null && f != null) {
            super.dwell.addLabel(f);
        }
        this.a.addElement(initSlice);
    }
    
    public NFPieSlice initSlice(final String s) {
        final NFPieSlice nfPieSlice = new NFPieSlice();
        nfPieSlice.a = s.toString();
        nfPieSlice.c = 1.0;
        nfPieSlice.d = null;
        nfPieSlice.e = null;
        nfPieSlice.b = "";
        nfPieSlice.k = 0.0;
        nfPieSlice.m = new Rectangle();
        nfPieSlice.n = new Point(0, 0);
        nfPieSlice.o = new Point(0, 0);
        nfPieSlice.p = new Point(0, 0);
        nfPieSlice.s = new NFPieSide();
        nfPieSlice.t = new NFPieSide();
        nfPieSlice.f = null;
        nfPieSlice.r = new Point(0, 0);
        nfPieSlice.q = new Point(0, 0);
        (nfPieSlice.e = new NFLabel()).setComponent(this);
        return nfPieSlice;
    }
    
    public void setSliceLabel(final String s, final NFLabel e) {
        final Enumeration<NFPieSlice> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final NFPieSlice nfPieSlice = elements.nextElement();
            if (nfPieSlice.a.equals(s)) {
                nfPieSlice.e = e;
                nfPieSlice.b = e.getLabel();
                if (e != null) {
                    e.setScale(super.scale);
                }
            }
        }
    }
    
    public NFActiveLabel getSliceActiveLabel(final int n) {
        if (this.a == null || n < 0 || n >= this.a.size()) {
            return null;
        }
        return this.a.elementAt(n).f;
    }
    
    public void deleteSlice(final String s) {
        for (int i = this.a.size() - 1; i >= 0; --i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.a.equals(s)) {
                if (super.dwell != null && nfPieSlice.f != null) {
                    super.dwell.removeLabel(nfPieSlice.f);
                }
                this.a.removeElementAt(i);
            }
        }
    }
    
    public void removeSliceActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.f != null) {
                super.dwell.removeLabel(nfPieSlice.f);
            }
            nfPieSlice.f = null;
        }
    }
    
    public void addSliceActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            nfPieSlice.f = this.a(i, nfPieSlice.c);
            super.dwell.addLabel(nfPieSlice.f);
        }
    }
    
    public void resetSliceActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.f != null) {
                nfPieSlice.f.reset();
            }
        }
    }
    
    public void deleteAllSlices() {
        this.removeSliceActiveLabels();
        this.a.removeAllElements();
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
    }
    
    private boolean a(final NFLabel nfLabel) {
        return nfLabel != null && nfLabel.getLabel() != null && nfLabel.getLabel().length() > 0;
    }
    
    private void a() {
        final Enumeration<NFPieSlice> elements = this.a.elements();
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
        this.am = 0;
        while (elements.hasMoreElements()) {
            final NFPieSlice nfPieSlice = elements.nextElement();
            if (this.a(nfPieSlice.e) && nfPieSlice.c > 0.0 && !Double.isNaN(nfPieSlice.c)) {
                if (super.legend != null) {
                    final NFDataSeries nfDataSeries = new NFDataSeries();
                    nfDataSeries.name = nfPieSlice.b;
                    final Color fillColor = (nfPieSlice.u == null || nfPieSlice.u.bg == null) ? nfPieSlice.d : nfPieSlice.u.bg;
                    final Color c = (nfPieSlice.u == null || nfPieSlice.u.fg == null) ? Color.black : nfPieSlice.u.fg;
                    nfDataSeries.fillColor = fillColor;
                    nfDataSeries.c = c;
                    nfDataSeries.pattern = nfPieSlice.u;
                    super.legend.addDataSet(nfDataSeries);
                }
                ++this.am;
            }
        }
    }
    
    public void setAngle(final int ar) {
        this.ar = ar;
        while (this.ar < 0) {
            this.ar += 360;
        }
        while (this.ar >= 360) {
            this.ar -= 360;
        }
    }
    
    protected void drawGraph(final Graphics graphics, Rectangle rectangle) {
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.ap = rectangle;
        if (this.a.size() == 0) {
            if (super.notesets != null && super.notesets.size() > 0) {
                NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
            }
            return;
        }
        if (rectangle.width <= 0 || rectangle.height <= 0) {
            NFDebug.print("*** Insufficient room to display graph ***");
            this.fireGraphTooSmall(new Dimension(rectangle2.width, rectangle2.height));
            return;
        }
        double n = 0.0;
        final Enumeration<NFPieSlice> elements = (Enumeration<NFPieSlice>)this.a.elements();
        while (elements.hasMoreElements()) {
            final NFPieSlice nfPieSlice = elements.nextElement();
            if (nfPieSlice.c > 0.0 && !Double.isNaN(nfPieSlice.c)) {
                n += nfPieSlice.c;
            }
        }
        double g = this.ar;
        final Enumeration elements2 = this.a.elements();
        double n2 = 0.0;
        while (elements2.hasMoreElements()) {
            final NFPieSlice nfPieSlice2 = elements2.nextElement();
            if (nfPieSlice2.c > 0.0) {
                if (Double.isNaN(nfPieSlice2.c)) {
                    continue;
                }
                nfPieSlice2.g = g;
                nfPieSlice2.l = nfPieSlice2.c / n * 100.0;
                final double n3 = 360.0 * nfPieSlice2.c / n;
                nfPieSlice2.h = NFUtil.rint(n3);
                for (n2 += n3 - nfPieSlice2.h; n2 > 1.0; --n2) {
                    final NFPieSlice nfPieSlice3 = nfPieSlice2;
                    ++nfPieSlice3.h;
                }
                if (!elements2.hasMoreElements() && n2 > 0.0 && nfPieSlice2.h + nfPieSlice2.g < 360 + this.ar) {
                    final NFPieSlice nfPieSlice4 = nfPieSlice2;
                    ++nfPieSlice4.h;
                }
                g += nfPieSlice2.h;
                if (g >= 360.0) {
                    g -= 360.0;
                }
                if (this.p != null && this.p.size() > 0) {
                    final StringBuffer sb = new StringBuffer();
                    for (int n4 = 0; n4 < this.p.size() && n4 < 3; ++n4) {
                        final Integer n5 = this.p.elementAt(n4);
                        if (n4 > 0) {
                            sb.append(this.r);
                        }
                        if (n5 == 0 || n5 == 3) {
                            sb.append(this.a(nfPieSlice2.l, 2, "%0.2f%"));
                        }
                        if (n5 == 4) {
                            sb.append(this.a(nfPieSlice2.l, 1, "%d%"));
                        }
                        if (n5 == 1) {
                            sb.append(this.a(nfPieSlice2.c));
                        }
                        if (n5 == 2) {
                            sb.append(nfPieSlice2.b);
                        }
                    }
                    nfPieSlice2.e.setLabel(sb.toString());
                }
                else {
                    nfPieSlice2.e.setLabel(this.a(nfPieSlice2.c));
                }
            }
        }
        if (this.am > 0 && this.m == 2) {
            Dimension a = new Dimension();
            final Enumeration<NFPieSlice> elements3 = (Enumeration<NFPieSlice>)this.a.elements();
            a.width = 0;
            a.height = 0;
            while (elements3.hasMoreElements()) {
                final NFPieSlice nfPieSlice5 = elements3.nextElement();
                if (nfPieSlice5.c > 0.0) {
                    if (Double.isNaN(nfPieSlice5.c)) {
                        continue;
                    }
                    this.a(nfPieSlice5, rectangle);
                    if (!this.a(nfPieSlice5.e)) {
                        continue;
                    }
                    a = this.a(graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height, nfPieSlice5.g, nfPieSlice5.h, nfPieSlice5.e, a);
                }
            }
            if (this.c <= 1.0) {
                final Rectangle rectangle3 = rectangle;
                rectangle3.width -= 2 * a.width;
                final Rectangle rectangle4 = rectangle;
                rectangle4.height -= 2 * a.height;
                final Rectangle rectangle5 = rectangle;
                rectangle5.x += a.width;
                final Rectangle rectangle6 = rectangle;
                rectangle6.y += a.height;
            }
            else {
                final int width = (int)((rectangle.width - 2 * a.width) / this.c);
                final int height = (int)((rectangle.height - 2 * a.height) / this.c);
                final Rectangle rectangle7 = rectangle;
                rectangle7.x += (rectangle.width - width) / 2;
                final Rectangle rectangle8 = rectangle;
                rectangle8.y += (rectangle.height - height) / 2;
                rectangle.width = width;
                rectangle.height = height;
            }
        }
        int width2 = 0;
        if (this.am > 0 && this.m == 1) {
            if (this.n == null) {
                this.n = new Vector();
            }
            else {
                this.n.removeAllElements();
            }
            if (this.o == null) {
                this.o = new Vector();
            }
            else {
                this.o.removeAllElements();
            }
            final Enumeration<NFPieSlice> elements4 = (Enumeration<NFPieSlice>)this.a.elements();
            while (elements4.hasMoreElements()) {
                final NFPieSlice nfPieSlice6 = elements4.nextElement();
                if (nfPieSlice6.c > 0.0) {
                    if (Double.isNaN(nfPieSlice6.c)) {
                        continue;
                    }
                    this.a(nfPieSlice6, rectangle);
                    this.a(this.n, this.o, nfPieSlice6);
                    if (!this.a(nfPieSlice6.e)) {
                        continue;
                    }
                    final Dimension bounds = nfPieSlice6.e.getBounds(graphics);
                    if (bounds.width <= width2) {
                        continue;
                    }
                    width2 = bounds.width;
                }
            }
            width2 += 30;
            final Rectangle rectangle9 = rectangle;
            rectangle9.width -= 2 * width2;
            final Rectangle rectangle10 = rectangle;
            rectangle10.x += width2;
        }
        double k = 0.0;
        for (int i = 0; i < this.a.size(); ++i) {
            final NFPieSlice nfPieSlice7 = this.a.elementAt(i);
            if (nfPieSlice7.c > 0.0) {
                if (!Double.isNaN(nfPieSlice7.c)) {
                    if (nfPieSlice7.k > k) {
                        k = nfPieSlice7.k;
                    }
                }
            }
        }
        if (k > 0.0) {
            final double n6 = k / (1.0 + 2.0 * k);
            final int n7 = (int)NFUtil.rint(n6 * rectangle.width);
            final int n8 = (int)NFUtil.rint(n6 * rectangle.height);
            final Rectangle rectangle11 = rectangle;
            rectangle11.x += n7;
            final Rectangle rectangle12 = rectangle;
            rectangle12.y += n8;
            final Rectangle rectangle13 = rectangle;
            rectangle13.width -= n7 + n7;
            final Rectangle rectangle14 = rectangle;
            rectangle14.height -= n8 + n8;
        }
        final boolean b = this.ai.width != this.ag || this.ai.height != this.ah || this.al.width != this.aj || this.al.height != this.ak;
        if (this.ai != null && ((this.al.width > 0 && rectangle.width > this.al.width) || (this.al.height > 0 && rectangle.height > this.al.height))) {
            final int n9 = (this.al.width > 0) ? (this.al.width - rectangle.width) : 0;
            final int n10 = (this.al.height > 0) ? (this.al.height - rectangle.height) : 0;
            if (n9 < 0) {
                final Rectangle rectangle15 = rectangle;
                rectangle15.x += -n9 / 2;
                final Rectangle rectangle16 = rectangle;
                rectangle16.width += n9;
            }
            if (n10 < 0) {
                final Rectangle rectangle17 = rectangle;
                rectangle17.y += -n10 / 2;
                final Rectangle rectangle18 = rectangle;
                rectangle18.height += n10;
            }
        }
        if (this.al != null && ((this.ai.width > 0 && rectangle.width < this.ai.width) || (this.ai.height > 0 && rectangle.height < this.ai.height))) {
            final int n11 = (this.ai.width > 0) ? (rectangle.width - this.ai.width) : 0;
            final int n12 = (this.ai.height > 0) ? (rectangle.height - this.ai.height) : 0;
            if (n11 < 0) {
                final Rectangle rectangle19 = rectangle;
                rectangle19.x += n11 / 2;
                final Rectangle rectangle20 = rectangle;
                rectangle20.width += -n11;
            }
            if (n12 < 0) {
                final Rectangle rectangle21 = rectangle;
                rectangle21.y += n12 / 2;
                final Rectangle rectangle22 = rectangle;
                rectangle22.height += -n12;
            }
        }
        if (this.e < 0) {
            this.ao = rectangle.height / 7;
            if (this.ao < 5) {
                this.ao = 5;
            }
        }
        else {
            this.ao = this.e;
        }
        final Rectangle rectangle23 = rectangle;
        rectangle23.height -= this.ao;
        if (this.k != null) {
            final int n13 = this.k.getThickness() / 2;
            if (n13 > 0) {
                final Rectangle rectangle24 = rectangle;
                rectangle24.x += n13;
                final Rectangle rectangle25 = rectangle;
                rectangle25.y += n13;
                final Rectangle rectangle26 = rectangle;
                rectangle26.width -= n13 + n13;
                final Rectangle rectangle27 = rectangle;
                rectangle27.height -= n13 + n13;
            }
        }
        final Rectangle rectangle28 = rectangle;
        ++rectangle28.x;
        final Rectangle rectangle29 = rectangle;
        ++rectangle29.y;
        final Rectangle rectangle30 = rectangle;
        rectangle30.width -= 2;
        final Rectangle rectangle31 = rectangle;
        rectangle31.height -= 2;
        if (rectangle.width <= 0 || rectangle.height <= 0) {
            NFDebug.print("*** Insufficient room to display graph ***");
            this.fireGraphTooSmall(new Dimension(rectangle2.width, rectangle2.height));
            return;
        }
        if (this.w) {
            final Rectangle rectangle32 = new Rectangle();
            if (rectangle.width > rectangle.height) {
                rectangle32.width = rectangle.height;
                rectangle32.height = rectangle.height;
            }
            else {
                rectangle32.width = rectangle.width;
                rectangle32.height = rectangle.width;
            }
            rectangle32.x = rectangle.x - (rectangle32.width - rectangle.width) / 2;
            rectangle32.y = rectangle.y - (rectangle32.height - rectangle.height) / 2;
            rectangle = rectangle32;
        }
        this.as = rectangle.x + rectangle.width / 2;
        this.at = rectangle.y + rectangle.height / 2;
        final Enumeration<NFPieSlice> elements5 = (Enumeration<NFPieSlice>)this.a.elements();
        while (elements5.hasMoreElements()) {
            final NFPieSlice nfPieSlice8 = elements5.nextElement();
            if (nfPieSlice8.c > 0.0) {
                if (Double.isNaN(nfPieSlice8.c)) {
                    continue;
                }
                this.a(nfPieSlice8, rectangle);
            }
        }
        if (this.ao > 0) {
            this.a(graphics, k);
        }
        this.resetSliceActiveLabels();
        final Enumeration<NFPieSlice> elements6 = (Enumeration<NFPieSlice>)this.a.elements();
        while (elements6.hasMoreElements()) {
            final NFPieSlice nfPieSlice9 = elements6.nextElement();
            if (nfPieSlice9.c > 0.0) {
                if (Double.isNaN(nfPieSlice9.c)) {
                    continue;
                }
                this.a(graphics, nfPieSlice9, nfPieSlice9.m);
            }
        }
        if (this.am > 0 && this.m == 2) {
            final Enumeration<NFPieSlice> elements7 = (Enumeration<NFPieSlice>)this.a.elements();
            while (elements7.hasMoreElements()) {
                final NFPieSlice nfPieSlice10 = elements7.nextElement();
                if (nfPieSlice10.c > 0.0) {
                    if (Double.isNaN(nfPieSlice10.c)) {
                        continue;
                    }
                    if (!this.a(nfPieSlice10.e)) {
                        continue;
                    }
                    this.a(graphics, nfPieSlice10.m.x, nfPieSlice10.m.y, nfPieSlice10.m.width, nfPieSlice10.m.height, nfPieSlice10.g, nfPieSlice10.h, nfPieSlice10.e, nfPieSlice10.d);
                }
            }
        }
        if (this.am > 0 && this.m == 1) {
            this.a(this.o, this.n, rectangle2, graphics, width2, b ? rectangle : null);
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
        }
        this.ap = rectangle;
    }
    
    public void clearDiscardedLabels() {
        this.ac = false;
        this.ab.removeAllElements();
    }
    
    public void setTrackDiscardedLabels(final boolean ad) {
        this.ad = ad;
    }
    
    public boolean getTrackDiscardedLabels() {
        return this.ad;
    }
    
    public boolean hasDiscardedLabels() {
        return this.ac;
    }
    
    public Vector getDiscardedLabels() {
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < this.ab.size(); ++i) {
            vector.addElement(new String((String)this.ab.elementAt(i)));
        }
        return vector;
    }
    
    private void a(final Vector vector, final Graphics graphics, final Rectangle rectangle, final int n, final int n2) {
        if (vector == null || vector.size() < 1) {
            return;
        }
        Polygon polygon = null;
        Polygon polygon2 = null;
        final int n3 = rectangle.height / 2 + rectangle.y;
        final NFPieSlice nfPieSlice = vector.elementAt(0);
        final Point point = new Point(Math.abs(nfPieSlice.q.x - nfPieSlice.n.x), Math.abs(nfPieSlice.q.y - nfPieSlice.n.y));
        final double sqrt = Math.sqrt(point.x * point.x + point.y * point.y);
        int n4;
        if (n2 < 0) {
            n4 = (int)((sqrt + 5.0) * Math.cos(3.141592653589793));
        }
        else {
            n4 = (int)((sqrt + 5.0) * Math.cos(0.0));
        }
        final int n5 = (int)(Math.abs(n4 + nfPieSlice.n.x - n) * 0.5) * n2;
        NFSort.qsort(vector, this);
        this.a(vector, graphics, rectangle);
        NFSort.qsort(vector, new NFPieSliceSorter());
        for (int i = 0; i < vector.size(); ++i) {
            final NFPieSlice nfPieSlice2 = vector.elementAt(i);
            if (nfPieSlice2.c > 0.0 && !Double.isNaN(nfPieSlice2.c)) {
                if (this.a(nfPieSlice2.e)) {
                    final Dimension bounds = nfPieSlice2.e.getBounds(graphics);
                    nfPieSlice2.e.setPos(n + bounds.width / 2 * n2, nfPieSlice2.r.y);
                    if (this.aa) {
                        final NFRegion region = nfPieSlice2.e.getRegion();
                        region.setColor(nfPieSlice2.d);
                        nfPieSlice2.e.setRegion(region);
                    }
                    nfPieSlice2.e.draw(graphics);
                    final int n6 = nfPieSlice2.r.y - bounds.height / 2;
                    final int n7 = nfPieSlice2.r.y + bounds.height / 2;
                    final Color color = this.y ? nfPieSlice2.d : null;
                    final int n8 = (nfPieSlice2.o.y < nfPieSlice2.p.y) ? nfPieSlice2.o.y : nfPieSlice2.p.y;
                    final int n9 = (nfPieSlice2.o.y > nfPieSlice2.p.y) ? nfPieSlice2.o.y : nfPieSlice2.p.y;
                    if (nfPieSlice2.q.y == nfPieSlice2.r.y) {
                        this.a(graphics, color, nfPieSlice2.q.x, nfPieSlice2.q.y, n, nfPieSlice2.q.y);
                    }
                    else if (nfPieSlice2.r.y > n8 + 1 && nfPieSlice2.r.y < n9 - 1) {
                        final int abs = Math.abs(nfPieSlice2.r.y - nfPieSlice2.n.y);
                        this.a(graphics, color, nfPieSlice2.n.x + (int)Math.sqrt(sqrt * sqrt - abs * abs) * n2, nfPieSlice2.r.y, n, nfPieSlice2.r.y);
                    }
                    else if ((nfPieSlice2.q.y > n3 && nfPieSlice2.r.y > n3 && nfPieSlice2.r.y > nfPieSlice2.q.y) || (nfPieSlice2.q.y <= n3 && nfPieSlice2.r.y <= n3 && nfPieSlice2.r.y < nfPieSlice2.q.y)) {
                        this.a(graphics, color, nfPieSlice2.q.x, nfPieSlice2.q.y, nfPieSlice2.q.x + n5, nfPieSlice2.r.y);
                        this.a(graphics, color, nfPieSlice2.q.x + n5, nfPieSlice2.r.y, n, nfPieSlice2.r.y);
                    }
                    else {
                        final int n10 = 5 * n2;
                        final int n11 = n5 * -1;
                        final int n12 = nfPieSlice2.q.x + n10;
                        final int y = nfPieSlice2.q.y;
                        final int n13 = n + n11;
                        final int y2 = nfPieSlice2.r.y;
                        Polygon polygon3;
                        if (polygon == null) {
                            polygon = new Polygon();
                            polygon2 = new Polygon();
                            polygon.addPoint(n12, y);
                            polygon.addPoint(n13, y2);
                            polygon2.addPoint(n12, y);
                            polygon2.addPoint(n13, y2);
                            if (nfPieSlice2.q.y < nfPieSlice2.n.y) {
                                polygon3 = polygon;
                            }
                            else {
                                polygon3 = polygon2;
                            }
                        }
                        else {
                            if (nfPieSlice2.q.y < nfPieSlice2.n.y) {
                                polygon3 = polygon;
                            }
                            else {
                                polygon3 = polygon2;
                            }
                            this.a(nfPieSlice2, n + n11, n12, y, n13, y2, n10, polygon3);
                        }
                        this.a(nfPieSlice2, sqrt, polygon3);
                        this.a(graphics, color, nfPieSlice2.q.x, nfPieSlice2.q.y, polygon3.xpoints[0], nfPieSlice2.q.y);
                        this.a(graphics, color, polygon3.xpoints[0], nfPieSlice2.q.y, n + n11, nfPieSlice2.r.y);
                        this.a(graphics, color, n + n11, nfPieSlice2.r.y, n, nfPieSlice2.r.y);
                    }
                }
            }
        }
    }
    
    private void a(final NFPieSlice nfPieSlice, final double n, final Polygon polygon) {
        final int n2 = (nfPieSlice.n.x - polygon.xpoints[0]) * -1;
        final int n3 = (nfPieSlice.n.x - polygon.xpoints[1]) * -1;
        final int n4 = nfPieSlice.n.y - polygon.ypoints[0];
        final int n5 = nfPieSlice.n.y - polygon.ypoints[1];
        final int n6 = (n2 < n3) ? n2 : n3;
        final int n7 = (n4 < n5) ? n4 : n5;
        final int n8 = (n4 > n5) ? n4 : n5;
        final double n9 = (n4 - n5) / (n2 - n3);
        if (n9 == 0.0) {
            return;
        }
        final double n10 = n4 - n9 * n2;
        double n11 = 0.0;
        int n12 = 0;
        int n13 = 0;
        for (int i = n7; i < n8; i += 10) {
            final int n14 = (int)((i - n10) / n9);
            final double sqrt = Math.sqrt(n14 * n14 + i * i);
            if (sqrt <= n) {
                final double abs = Math.abs(sqrt - n);
                if (abs > n11) {
                    n11 = abs;
                    n12 = n14;
                    n13 = i;
                }
            }
        }
        if (n11 == 0.0) {
            return;
        }
        final double atan2 = Math.atan2(n13, n12);
        final double n15 = ((int)((n + 5.0) * Math.sin(atan2)) - n5) / ((int)((n + 5.0) * Math.cos(atan2)) - n3);
        if (n15 == 0.0) {
            return;
        }
        polygon.xpoints[0] = nfPieSlice.n.x + (int)((n4 - (n5 - n15 * n3)) / n15);
    }
    
    private void a(final NFPieSlice nfPieSlice, final int n, int n2, final int n3, final int n4, final int n5, final int n6, final Polygon polygon) {
        if (n6 < 0 && (n2 < n || n4 < n)) {
            polygon.xpoints[0] = n;
            polygon.xpoints[1] = n;
            polygon.ypoints[0] = n3;
            polygon.ypoints[1] = n5;
            return;
        }
        if (n6 > 0 && (n2 > n || n4 > n)) {
            polygon.xpoints[0] = n;
            polygon.xpoints[1] = n;
            polygon.ypoints[0] = n3;
            polygon.ypoints[1] = n5;
            return;
        }
        if (n6 > 0) {}
        final int n7 = (nfPieSlice.n.x - n2) * -1;
        final int n8 = (nfPieSlice.n.x - n4) * -1;
        final int n9 = nfPieSlice.n.y - n3;
        final int n10 = nfPieSlice.n.y - n5;
        final int n11 = (nfPieSlice.n.x - polygon.xpoints[0]) * -1;
        final int n12 = (nfPieSlice.n.x - polygon.xpoints[1]) * -1;
        final int n13 = nfPieSlice.n.y - polygon.ypoints[0];
        final int n14 = nfPieSlice.n.y - polygon.ypoints[1];
        if (n11 == n12) {
            if (n9 < n13 || n9 < n14 || n10 < n13 || n10 < n14) {
                polygon.xpoints[0] = n;
                polygon.xpoints[1] = n;
                polygon.ypoints[0] = n3;
                polygon.ypoints[1] = n5;
                return;
            }
            polygon.xpoints[0] = n2;
            polygon.xpoints[1] = n4;
            polygon.ypoints[0] = n3;
            polygon.ypoints[1] = n5;
        }
        else {
            final double n15 = (n9 - n10) / (n7 - n8);
            final double n16 = n9 - n15 * n7;
            final double n17 = (n13 - n14) / (n11 - n12);
            final double n18 = n13 - n17 * n11;
            if (n15 == n17) {
                polygon.xpoints[0] = n2;
                polygon.xpoints[1] = n4;
                polygon.ypoints[0] = n3;
                polygon.ypoints[1] = n5;
                polygon.npoints = 2;
                return;
            }
            final double n19 = (n16 - n18) / (n17 - n15);
            final double n20 = n15 * n19 + n16;
            final int n21 = (n7 < n8) ? n7 : n8;
            final int n22 = (n7 > n8) ? n7 : n8;
            final int n23 = (n9 < n10) ? n9 : n10;
            final int n24 = (n9 > n10) ? n9 : n10;
            if (n19 > n21 && n19 < n22 && n20 > n23 && n20 < n24) {
                n2 += n6;
                this.a(nfPieSlice, n, n2, n3, n4, n5, n6, polygon);
            }
            else {
                polygon.xpoints[0] = n2;
                polygon.xpoints[1] = n4;
                polygon.ypoints[0] = n3;
                polygon.ypoints[1] = n5;
                polygon.npoints = 2;
            }
        }
    }
    
    private void a(final Vector vector, final Vector vector2, final Rectangle rectangle, final Graphics graphics, final int n, final Rectangle rectangle2) {
        if (this.ad) {
            this.clearDiscardedLabels();
        }
        int n2;
        int n3;
        if (rectangle2 == null) {
            n2 = rectangle.x + rectangle.width - (n - 30);
            n3 = rectangle.x + (n - 30);
        }
        else {
            n2 = rectangle2.x + rectangle2.width + 5;
            n3 = rectangle2.x - 5;
        }
        this.a(vector, graphics, rectangle, n2, 1);
        this.a(vector2, graphics, rectangle, n3, -1);
    }
    
    private void a(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        if (graphics == null) {
            return;
        }
        if (this.s == null) {
            NFLine.draw(graphics, n, n2, n3, n4, 1, 1, (color != null) ? color : Color.black, null, null, super.scale);
            return;
        }
        final Color color2 = this.s.getColor();
        if (color != null) {
            this.s.setColor(color);
        }
        this.s.draw(graphics, n, n2, n3, n4);
        this.s.setColor(color2);
    }
    
    public int compare(final Object o, final Object o2) {
        final NFPieSlice nfPieSlice = (NFPieSlice)o;
        final NFPieSlice nfPieSlice2 = (NFPieSlice)o2;
        if (nfPieSlice.q.y > nfPieSlice2.q.y) {
            return 1;
        }
        if (nfPieSlice.q.y < nfPieSlice2.q.y) {
            return -1;
        }
        double n = nfPieSlice.g + nfPieSlice.h / 2.0;
        double n2 = nfPieSlice2.g + nfPieSlice2.h / 2.0;
        if (n > 360.0) {
            n -= 360.0;
        }
        if (n2 > 360.0) {
            n2 -= 360.0;
        }
        if (n > 90.0 && n <= 270.0) {
            if (n > n2) {
                return 1;
            }
            return -1;
        }
        else if (n >= 0.0 && n2 >= 0.0) {
            if (n > n2) {
                return -1;
            }
            return 1;
        }
        else if (n >= 270.0 && n2 >= 270.0) {
            if (n > n2) {
                return 1;
            }
            return -1;
        }
        else {
            if (n > 0.0) {
                return -1;
            }
            return 1;
        }
    }
    
    private void a(final Vector vector, final Graphics graphics, final int n) {
        int n2 = 0;
        int n3 = -1;
        double n4 = 360.0;
        for (int i = 0; i < vector.size(); ++i) {
            final NFPieSlice nfPieSlice = vector.elementAt(i);
            if (nfPieSlice.c > 0.0) {
                if (!Double.isNaN(nfPieSlice.c)) {
                    final Dimension bounds = nfPieSlice.e.getBounds(graphics);
                    double h;
                    if (nfPieSlice.h > 360.0) {
                        h = nfPieSlice.h % 360.0;
                    }
                    else {
                        h = nfPieSlice.h;
                    }
                    if (nfPieSlice.h < n4) {
                        n4 = h;
                        n3 = i;
                    }
                    n2 += bounds.height;
                }
            }
        }
        if (n2 > n && vector.size() > 0) {
            final NFPieSlice nfPieSlice2 = vector.elementAt(n3);
            vector.removeElementAt(n3);
            if (this.ad) {
                this.ab.addElement(new String(nfPieSlice2.e.getLabel()));
                this.ac = true;
            }
            this.a(vector, graphics, n);
        }
    }
    
    private void a(final Vector vector, final Graphics graphics, final Rectangle rectangle) {
        NFUtil.rint(vector.size() / 2.0);
        if (vector == null || vector.size() < 1) {
            return;
        }
        NFSort.qsort(vector, new NFPieSliceSorter());
        this.a(vector, graphics, rectangle.height);
        if (vector.size() < 1) {
            return;
        }
        final NFPieSlice nfPieSlice = vector.elementAt(0);
        nfPieSlice.e.getBounds(graphics);
        nfPieSlice.r.y = nfPieSlice.q.y;
        NFPieSlice nfPieSlice2 = nfPieSlice;
        NFPieSlice nfPieSlice3 = nfPieSlice;
        for (int i = 1; i < vector.size(); ++i) {
            final NFPieSlice nfPieSlice4 = vector.elementAt(i);
            final Dimension bounds = nfPieSlice4.e.getBounds(graphics);
            if (nfPieSlice4.q.y <= nfPieSlice2.q.y) {
                if (nfPieSlice4.q.y + bounds.height > nfPieSlice2.r.y) {
                    nfPieSlice4.r.y = nfPieSlice2.r.y - bounds.height;
                }
                else {
                    nfPieSlice4.r.y = nfPieSlice4.q.y;
                }
                nfPieSlice2 = nfPieSlice4;
            }
            else {
                if (nfPieSlice4.q.y - bounds.height < nfPieSlice3.r.y) {
                    nfPieSlice4.r.y = nfPieSlice3.r.y + bounds.height;
                }
                else {
                    nfPieSlice4.r.y = nfPieSlice4.q.y;
                }
                nfPieSlice3 = nfPieSlice4;
            }
        }
        NFSort.qsort(vector, this);
        if (nfPieSlice3.r.y + nfPieSlice3.e.getBounds(graphics).height / 2 > rectangle.height) {
            int n = rectangle.height + rectangle.y;
            for (int j = vector.size() - 1; j >= 0; --j) {
                final NFPieSlice nfPieSlice5 = vector.elementAt(j);
                final Dimension bounds2 = nfPieSlice5.e.getBounds(graphics);
                if (nfPieSlice5.r.y + bounds2.height / 2 > n) {
                    nfPieSlice5.r.y = n - bounds2.height / 2;
                }
                n = nfPieSlice5.r.y - bounds2.height / 2;
            }
        }
        if (nfPieSlice2.r.y - nfPieSlice2.e.getBounds(graphics).height / 2 < rectangle.y) {
            int y = rectangle.y;
            for (int k = 0; k < vector.size(); ++k) {
                final NFPieSlice nfPieSlice6 = vector.elementAt(k);
                final Dimension bounds3 = nfPieSlice6.e.getBounds(graphics);
                if (nfPieSlice6.r.y - bounds3.height / 2 < y) {
                    nfPieSlice6.r.y = y + bounds3.height / 2;
                }
                y = nfPieSlice6.r.y + bounds3.height / 2;
            }
        }
    }
    
    private void a(final Vector vector, final Vector vector2, final NFPieSlice nfPieSlice) {
        double n = nfPieSlice.g + nfPieSlice.h / 2.0;
        if (n >= 360.0) {
            n -= 360.0;
        }
        if (n > 90.0 && n <= 270.0) {
            vector.addElement(nfPieSlice);
        }
        else {
            vector2.addElement(nfPieSlice);
        }
    }
    
    private void a(final NFPieSlice nfPieSlice, final Rectangle rectangle) {
        nfPieSlice.m.x = rectangle.x;
        nfPieSlice.m.y = rectangle.y;
        nfPieSlice.m.width = rectangle.width;
        nfPieSlice.m.height = rectangle.height;
        if (nfPieSlice.k > 0.0 || this.m == 1) {
            double n = nfPieSlice.g + nfPieSlice.h / 2.0;
            if (n >= 360.0) {
                n -= 360.0;
            }
            final double n2 = nfPieSlice.k * rectangle.width;
            final double n3 = nfPieSlice.k * rectangle.height;
            final double n4 = 3.141592653589793 * (n / 180.0);
            final Rectangle m = nfPieSlice.m;
            m.x += (int)NFUtil.rint(Math.cos(n4) * n2);
            final Rectangle i = nfPieSlice.m;
            i.y -= (int)NFUtil.rint(Math.sin(n4) * n3);
        }
        nfPieSlice.n.x = nfPieSlice.m.x + nfPieSlice.m.width / 2;
        nfPieSlice.n.y = nfPieSlice.m.y + nfPieSlice.m.height / 2;
        final double n5 = nfPieSlice.m.width / 2;
        final double n6 = nfPieSlice.m.height / 2;
        if (this.m == 1) {
            double n7 = nfPieSlice.g + nfPieSlice.h / 2.0;
            if (n7 >= 360.0) {
                n7 -= 360.0;
            }
            final double n8 = 3.141592653589793 * (n7 / 180.0);
            nfPieSlice.q.x = nfPieSlice.n.x;
            nfPieSlice.q.y = nfPieSlice.n.y;
            final Point q = nfPieSlice.q;
            q.x += (int)NFUtil.rint(Math.cos(n8) * n5);
            final Point q2 = nfPieSlice.q;
            q2.y -= (int)NFUtil.rint(Math.sin(n8) * n6);
        }
        final double n9 = 3.141592653589793 * (nfPieSlice.g / 180.0);
        nfPieSlice.o.x = nfPieSlice.n.x + (int)NFUtil.rint(Math.cos(n9) * n5);
        nfPieSlice.o.y = nfPieSlice.n.y - (int)NFUtil.rint(Math.sin(n9) * n6);
        nfPieSlice.i = nfPieSlice.g + nfPieSlice.h;
        while (nfPieSlice.i >= 360.0) {
            nfPieSlice.i -= 360.0;
        }
        final double n10 = 3.141592653589793 * (nfPieSlice.i / 180.0);
        nfPieSlice.p.x = nfPieSlice.n.x + (int)NFUtil.rint(Math.cos(n10) * n5);
        nfPieSlice.p.y = nfPieSlice.n.y - (int)NFUtil.rint(Math.sin(n10) * n6);
    }
    
    private void a(final Graphics graphics, final NFPieSlice nfPieSlice, final Rectangle rectangle) {
        final int n = rectangle.x + rectangle.width / 2;
        final int n2 = rectangle.y + rectangle.height / 2;
        (nfPieSlice.j = new Polygon()).addPoint(n, n2);
        NFGraphSymbol.generateArc(nfPieSlice.j, rectangle, nfPieSlice.g, nfPieSlice.h, 0);
        nfPieSlice.j.addPoint(n, n2);
        graphics.setColor(nfPieSlice.d);
        if (NFUtil.getJDKVersion() >= 1.2 && nfPieSlice.u != null && nfPieSlice.u.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, nfPieSlice.j, (this.k != null) ? this.k.getColor() : null, nfPieSlice.u);
        }
        else {
            graphics.fillArc(rectangle.x, rectangle.y, rectangle.width, rectangle.height, (int)nfPieSlice.g, (int)nfPieSlice.h);
        }
        if (this.k != null && this.k.getThickness() > 0) {
            if (this.k.getThickness() == 1) {
                final Color color = graphics.getColor();
                final Color color2 = this.k.getColor();
                if (color2 != null) {
                    graphics.setColor(color2);
                }
                graphics.drawArc(rectangle.x, rectangle.y, rectangle.width, rectangle.height, (int)nfPieSlice.g, (int)nfPieSlice.h);
                graphics.drawLine(nfPieSlice.n.x, nfPieSlice.n.y, nfPieSlice.o.x, nfPieSlice.o.y);
                graphics.drawLine(nfPieSlice.n.x, nfPieSlice.n.y, nfPieSlice.p.x, nfPieSlice.p.y);
                graphics.setColor(color);
            }
            else {
                this.k.drawPoly(graphics, nfPieSlice.j);
            }
        }
        if (nfPieSlice.f != null) {
            nfPieSlice.f.setBounds(nfPieSlice.j);
            this.a(nfPieSlice);
        }
    }
    
    private void a(final Graphics graphics, final double n) {
        final Vector vector = new Vector<NFPieSlice>();
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.c > 0.0) {
                if (!Double.isNaN(nfPieSlice.c)) {
                    int j;
                    for (j = 0; j < vector.size(); ++j) {
                        final NFPieSlice nfPieSlice2 = vector.elementAt(j);
                        if (nfPieSlice.m.y + nfPieSlice.m.height < nfPieSlice2.m.y + nfPieSlice2.m.height) {
                            vector.insertElementAt(nfPieSlice, j);
                            break;
                        }
                    }
                    if (j == vector.size()) {
                        vector.addElement(nfPieSlice);
                    }
                }
            }
        }
        final int size2 = vector.size();
        for (int k = 0; k < size2; ++k) {
            this.a(graphics, vector.elementAt(k), true);
        }
        if (n > 0.0) {
            this.a(graphics);
        }
        for (int l = 0; l < size2; ++l) {
            this.a(graphics, vector.elementAt(l), false);
        }
    }
    
    private void a(final Graphics graphics, final NFPieSlice nfPieSlice, final boolean b) {
        graphics.setColor(NFColor.darker(nfPieSlice.d));
        double g = nfPieSlice.g;
        final double h = nfPieSlice.h;
        while (g >= 360.0) {
            g -= 360.0;
        }
        if (g < 180.0 && g + h > 360.0) {
            this.a(graphics, 180.0, 181.0, nfPieSlice, b);
        }
        else if (g >= 180.0 && h - (360.0 - g) > 180.0) {
            this.a(graphics, g, 360.0 - g, nfPieSlice, b);
            graphics.setColor(NFColor.darker(nfPieSlice.d));
            this.a(graphics, 180.0, h - (360.0 - g + 180.0), nfPieSlice, b);
        }
        else if (g >= 180.0 && g + h <= 360.0) {
            this.a(graphics, g, h, nfPieSlice, b);
        }
        else if (g >= 180.0 || g + h >= 180.0) {
            if (g < 180.0 && g + h <= 360.0) {
                this.a(graphics, 180.0, h - (180.0 - g), nfPieSlice, b);
            }
            else if (g >= 180.0 && g + h > 360.0) {
                this.a(graphics, g, 360.0 - g + 1.0, nfPieSlice, b);
            }
        }
    }
    
    private void a(final Graphics graphics) {
        final Vector vector = new Vector<NFPieSide>();
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.c > 0.0) {
                if (!Double.isNaN(nfPieSlice.c)) {
                    nfPieSlice.s.a = this.a(nfPieSlice.s.a, nfPieSlice.n.x, nfPieSlice.n.y, nfPieSlice.o.x, nfPieSlice.o.y);
                    nfPieSlice.s.c = NFColor.darker(nfPieSlice.d);
                    nfPieSlice.s.d = nfPieSlice.u;
                    this.a(vector, nfPieSlice.s, nfPieSlice.g);
                    nfPieSlice.t.a = this.a(nfPieSlice.t.a, nfPieSlice.n.x, nfPieSlice.n.y, nfPieSlice.p.x, nfPieSlice.p.y);
                    nfPieSlice.t.c = NFColor.darker(nfPieSlice.d);
                    nfPieSlice.t.d = nfPieSlice.u;
                    this.a(vector, nfPieSlice.t, nfPieSlice.i);
                }
            }
        }
        for (int j = 0; j < vector.size(); ++j) {
            final NFPieSide nfPieSide = vector.elementAt(j);
            graphics.setColor(nfPieSide.c);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPieSide.d != null && nfPieSide.d.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, nfPieSide.a, (this.k != null) ? this.k.getColor() : null, nfPieSide.d);
            }
            else {
                graphics.fillPolygon(nfPieSide.a);
            }
            if (this.k != null && this.k.getThickness() > 0) {
                this.k.drawPoly(graphics, nfPieSide.a);
            }
        }
    }
    
    private void a(final Vector vector, final NFPieSide nfPieSide, final double n) {
        if (n < 90.0) {
            nfPieSide.b = 90.0 - n;
        }
        else if (n >= 90.0 && n <= 270.0) {
            nfPieSide.b = n - 90.0;
        }
        else {
            nfPieSide.b = 180.0 - (n - 270.0);
        }
        for (int i = 0; i < vector.size(); ++i) {
            if (nfPieSide.b < vector.elementAt(i).b) {
                vector.insertElementAt(nfPieSide, i);
                return;
            }
        }
        vector.addElement(nfPieSide);
    }
    
    private Polygon a(Polygon polygon, final int n, final int n2, final int n3, final int n4) {
        if (polygon == null) {
            polygon = new Polygon();
            polygon.addPoint(n, n2);
            polygon.addPoint(n, n2 + this.ao);
            polygon.addPoint(n3, n4 + this.ao);
            polygon.addPoint(n3, n4);
            polygon.addPoint(n, n2);
        }
        else {
            polygon.xpoints[0] = n;
            polygon.ypoints[0] = n2;
            polygon.xpoints[1] = n;
            polygon.ypoints[1] = n2 + this.ao;
            polygon.xpoints[2] = n3;
            polygon.ypoints[2] = n4 + this.ao;
            polygon.xpoints[3] = n3;
            polygon.ypoints[3] = n4;
            polygon.xpoints[4] = n;
            polygon.ypoints[4] = n2;
        }
        return polygon;
    }
    
    private void a(final Graphics graphics, final double n, final double n2, final NFPieSlice nfPieSlice, final boolean b) {
        final NFPatternFill u = nfPieSlice.u;
        final Rectangle m = nfPieSlice.m;
        final Polygon arcShadow = NFGraphSymbol.createArcShadow(m, n, n2, this.ao - 1, true);
        if (NFUtil.getJDKVersion() >= 1.2 && u != null && u.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, arcShadow, (this.k != null) ? this.k.getColor() : null, u);
        }
        else if (b) {
            graphics.fillArc(m.x, m.y + this.ao, m.width, m.height, (int)n, (int)n2);
        }
        else {
            graphics.fillPolygon(arcShadow);
            for (int i = 0; i < this.ao; ++i) {
                graphics.drawArc(m.x, m.y + i, m.width, m.height, (int)n, (int)n2);
            }
        }
        if (!b && this.k != null && this.k.getThickness() > 0) {
            if (this.k.getThickness() == 1) {
                final Color color = graphics.getColor();
                final Color color2 = this.k.getColor();
                if (color2 != null) {
                    graphics.setColor(color2);
                }
                graphics.drawArc(m.x, m.y + this.ao, m.width, m.height, (int)n, (int)n2);
                graphics.drawLine(nfPieSlice.o.x, nfPieSlice.o.y, nfPieSlice.o.x, nfPieSlice.o.y + this.ao - 1);
                graphics.drawLine(nfPieSlice.p.x, nfPieSlice.p.y, nfPieSlice.p.x, nfPieSlice.p.y + this.ao - 1);
                graphics.setColor(color);
            }
            else {
                this.k.drawPoly(graphics, arcShadow);
            }
        }
    }
    
    private void a(final Graphics graphics, int n, int n2, int n3, int n4, double n5, final double n6, final NFLabel nfLabel, final Color color) {
        n5 = (n5 + n6 / 2.0) % 360.0;
        n += n3 / 2;
        n2 += n4 / 2;
        n3 /= 2;
        n4 /= 2;
        final Point a = this.a(n, n2, n3, n4, n5);
        if (this.c > 1.0) {
            final Dimension bounds = nfLabel.getBounds(graphics);
            if (n5 < 70.0 || n5 > 290.0) {
                final Point point = a;
                point.x += bounds.width / 2;
            }
            else if (n5 > 110.0 && n5 < 250.0) {
                final Point point2 = a;
                point2.x -= bounds.width / 2;
            }
            if (n5 > 20.0 && n5 < 160.0) {
                final Point point3 = a;
                point3.y -= bounds.height / 2;
            }
            else if (n5 > 200.0 && n5 < 340.0) {
                final Point point4 = a;
                point4.y += bounds.height / 2;
            }
        }
        nfLabel.setPos(a.x, a.y);
        if (this.aa) {
            final NFRegion region = nfLabel.getRegion();
            region.setColor(color);
            nfLabel.setRegion(region);
        }
        nfLabel.draw(graphics);
    }
    
    private Dimension a(final Graphics graphics, int n, int n2, int n3, int n4, double n5, final double n6, final NFLabel nfLabel, final Dimension dimension) {
        final Dimension bounds = nfLabel.getBounds(graphics);
        if (this.c > 1.0) {
            if (bounds.width > dimension.width) {
                dimension.width = bounds.width;
            }
            if (bounds.height > dimension.height) {
                dimension.height = bounds.height;
            }
            return dimension;
        }
        n5 = (n5 + n6 / 2.0) % 360.0;
        final int n7 = n;
        final int n8 = n + n3;
        final int n9 = n2;
        final int n10 = n2 + n4 + this.ao;
        n += n3 / 2;
        n2 += n4 / 2;
        n3 /= 2;
        n4 /= 2;
        if (this.c <= 1.0) {
            bounds.width /= 2;
            bounds.height /= 2;
        }
        final int width = this.a(n, n2, n3, n4, 0.0).x + bounds.width - n8;
        if (width > dimension.width) {
            dimension.width = width;
        }
        final int width2 = n7 - (this.a(n, n2, n3, n4, 180.0).x - bounds.width);
        if (width2 > dimension.width) {
            dimension.width = width2;
        }
        final int height = n9 - (this.a(n, n2, n3, n4, 90.0).y - bounds.height);
        if (height > dimension.height) {
            dimension.height = height;
        }
        final int height2 = this.a(n, n2, n3, n4, 270.0).y + bounds.height - n10;
        if (height2 > dimension.height) {
            dimension.height = height2;
        }
        return dimension;
    }
    
    private Point a(final int n, final int n2, final int n3, final int n4, final double n5) {
        final double n6 = 3.141592653589793 * (n5 / 180.0);
        final Point point = new Point(0, 0);
        point.x = n + (int)NFUtil.rint(Math.cos(n6) * n3 * this.c);
        point.y = n2 - (int)NFUtil.rint(Math.sin(n6) * n4 * this.c);
        if (this.c > 1.0 && n5 > 200.0 && n5 < 340.0) {
            final Point point2 = point;
            point2.y += this.ao;
        }
        return point;
    }
    
    private NFPieSlice a(final int n, final int n2) {
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.c > 0.0) {
                if (!Double.isNaN(nfPieSlice.c)) {
                    if (nfPieSlice.j != null && nfPieSlice.j.inside(n, n2)) {
                        return nfPieSlice;
                    }
                }
            }
        }
        return null;
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (super.mouseDown(event, n, n2)) {
            return true;
        }
        if (event.metaDown()) {
            this.az = this.a(n, n2);
            if (this.az != null) {
                this.ay = true;
                this.au = n;
                this.av = n2;
                return true;
            }
        }
        this.aw = true;
        this.ax = this.ar;
        this.au = n;
        this.av = n2;
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.aw) {
            this.b(n, n2);
            return true;
        }
        if (this.ay) {
            this.c(n, n2);
            return true;
        }
        return super.mouseDrag(event, n, n2);
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.aw) {
            this.b(n, n2);
            if (this.ax != this.ar) {
                this.notifyPostDrag(this.ar, 0.0);
            }
            this.aw = false;
        }
        if (this.ay) {
            this.c(n, n2);
            this.notifyPostDrag(this.ar, 0.0);
            this.ay = false;
        }
        return super.mouseUp(event, n, n2);
    }
    
    private void b(final int au, final int av) {
        if (au == this.au && av == this.av) {
            return;
        }
        final int n = (int)((Math.atan2(au - this.as, av - this.at) - Math.atan2(this.au - this.as, this.av - this.at)) / 6.283185307179586 * 360.0);
        if (n > 4 || n < -4) {
            if (!this.a(n)) {
                return;
            }
            this.au = au;
            this.av = av;
        }
    }
    
    private boolean a(final int n) {
        int i;
        for (i = this.ar + n; i < 0; i += 360) {}
        while (i >= 360) {
            i -= 360;
        }
        if (this.abortPreDrag(this.ar, 0.0, i, 0.0)) {
            return false;
        }
        this.ar = i;
        this.display(true);
        return true;
    }
    
    private void c(final int n, final int n2) {
        if (n == this.au && n2 == this.av) {
            return;
        }
        final int n3 = this.au - this.as;
        final int n4 = this.av - this.at;
        final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        final int n5 = n - this.as;
        final int n6 = n2 - this.at;
        final double n7 = Math.sqrt(n5 * n5 + n6 * n6) - sqrt;
        if (n7 < 4.0 && n7 > -4.0) {
            return;
        }
        if (this.az == null || this.az.j == null || !this.az.j.inside(n, n2)) {
            this.au = n;
            this.av = n2;
            return;
        }
        if (this.abortPreDrag(this.ar, 0.0, this.ar, 0.0)) {
            return;
        }
        final int n8 = this.az.n.x - this.as;
        final int n9 = this.az.n.y - this.at;
        final double sqrt2 = Math.sqrt(n8 * n8 + n9 * n9);
        if (sqrt2 > 0.0) {
            final NFPieSlice az = this.az;
            az.k *= (sqrt2 + n7) / sqrt2;
        }
        else {
            this.az.k = n7 / this.az.m.width;
        }
        if (this.az.k < 0.0) {
            this.az.k = 0.0;
        }
        this.display(true);
        this.au = n;
        this.av = n2;
    }
    
    private void b(final double n) {
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            final NFPieSlice nfPieSlice = this.a.elementAt(i);
            if (nfPieSlice.c > 0.0) {
                if (!Double.isNaN(nfPieSlice.c)) {
                    final NFPieSlice nfPieSlice2 = nfPieSlice;
                    nfPieSlice2.k += n;
                    if (nfPieSlice.k < 0.0) {
                        nfPieSlice.k = 0.0;
                    }
                }
            }
        }
        this.notifyPostDrag(this.ar, 0.0);
        this.display(true);
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1003: {
                this.b(-0.1);
                break;
            }
            case 1002: {
                this.b(0.1);
                break;
            }
            case 1004:
            case 1006: {
                this.a(event.shiftDown() ? 30 : 5);
                break;
            }
            case 1005:
            case 1007: {
                this.a(event.shiftDown() ? -30 : -5);
                break;
            }
            default: {
                return super.keyDown(event, n);
            }
        }
        return false;
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        super.param.defineChartType("ChartType", 5);
        super.param.defineNumber("LabelPos", new Double(1.1));
        super.param.defineNumber("Pie3DDepth", new Integer(-1));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineNumber("SliceValue", new Integer(10)));
        vector.addElement(super.param.defineColor("SliceColor", null));
        final int defineLabel = super.param.defineLabel("SliceLabel", vector, false);
        super.param.defineRegion("SliceRegion", vector);
        super.param.defineVector("Slices", super.param.defineTuple("Slice", vector));
        this.a0 = 2;
        this.a1 = this.a0 + defineLabel;
        super.param.defineActiveLabel("ActiveLabels");
        super.param.defineString("ActiveLabelsEnabled", "ON");
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("FLOAT", new Integer(2));
        hashtable.put("INTEGER", new Integer(1));
        hashtable.put("DECIMAL", new Integer(5));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineSymbol("SliceFormatType", hashtable, new Integer(2)));
        vector2.addElement(super.param.defineString("SliceFormatStr", null));
        super.param.defineTuple("SliceFormat", vector2);
        super.param.defineLine("SliceBorder");
        super.param.defineVector("SlicePos", super.param.defineNumber("SlicePosItem", new Double(0.0)));
        super.param.defineNumber("PieAngle", new Integer(0));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("EXTERIOR", new Integer(1));
        hashtable2.put("RADIAL", new Integer(2));
        hashtable2.put("NONE", new Integer(0));
        super.param.defineSymbol("SliceLabelStyle", hashtable2, new Integer(1));
        final Hashtable<String, Integer> hashtable3 = new Hashtable<String, Integer>();
        hashtable3.put("PERCENTAGE", new Integer(0));
        hashtable3.put("PERCENTAGE_INT", new Integer(4));
        hashtable3.put("PERCENTAGE_FLOAT", new Integer(3));
        hashtable3.put("DATA", new Integer(1));
        hashtable3.put("LABEL", new Integer(2));
        super.param.defineVector("SliceLabelContent", super.param.defineSymbol("Content", hashtable3, new Integer(1)));
        super.param.defineString("SliceLabelContentDelimiter", new String("\n"));
        super.param.defineVector("SliceData", super.param.defineNumber("SliceDataValue", new Integer(10)));
        super.param.defineVector("SliceLabels", super.param.defineString("SliceLabelStr", null));
        super.param.defineVector("SliceColors", super.param.defineColor("SliceColor"));
        final Hashtable<String, Integer> hashtable4 = new Hashtable<String, Integer>();
        hashtable4.put("ON", new Integer(0));
        hashtable4.put("OFF", new Integer(1));
        super.param.defineSymbol("SliceSort", hashtable4, new Integer(0));
        super.param.defineLine("SliceLabelLine");
        super.param.defineLabel("SliceLabel");
        super.param.defineRegion("SliceLabelBox");
        final Hashtable<String, Integer> hashtable5 = new Hashtable<String, Integer>();
        hashtable5.put("ON", new Integer(0));
        hashtable5.put("OFF", new Integer(1));
        super.param.defineSymbol("PieSquare", hashtable5, new Integer(1));
        NFPatternFill.definePatternFillParam(super.param, "SliceFillPattern");
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineNumber("MinWidth", null));
        vector3.addElement(super.param.defineNumber("MinHeight", null));
        vector3.addElement(super.param.defineNumber("MaxWidth", null));
        vector3.addElement(super.param.defineNumber("MaxWidth", null));
        super.param.defineTuple("PieSize", vector3);
    }
    
    protected synchronized void loadParams() throws Exception {
        final boolean b = super.legend != null;
        super.loadParams();
        if (super.legend != null && !b) {
            this.a();
        }
        if (super.param.changed("LabelPos")) {
            super.graphChanged = true;
            this.setLabelPos(NFUtil.getNumber(super.param.get("LabelPos"), this.b));
        }
        if (super.param.changed("SliceLabelStyle")) {
            super.graphChanged = true;
            this.m = NFUtil.getNumber(super.param.get("SliceLabelStyle"), this.l);
        }
        if (super.param.changed("Pie3DDepth")) {
            super.graphChanged = true;
            this.e = NFUtil.getNumber(super.param.get("Pie3DDepth"), this.d);
        }
        if (super.param.changed("PieAngle")) {
            super.graphChanged = true;
            this.setAngle(NFUtil.getNumber(super.param.get("PieAngle"), this.aq));
        }
        if (super.param.changed("PieSquare")) {
            super.graphChanged = true;
            this.w = (NFUtil.getNumber(super.param.get("PieSquare"), this.v) == 0);
        }
        if (super.param.changed("SliceBorder")) {
            super.graphChanged = true;
            this.k = NFLine.loadParams(super.param, super.param.get("SliceBorder"));
            if (this.k != null) {
                final NFArrow nfArrow = new NFArrow();
                nfArrow.setStyle(4);
                nfArrow.setWidth(this.k.getThickness());
                this.k.setArrows(null, nfArrow);
            }
        }
        if (super.param.changed("SliceLabelContent")) {
            super.graphChanged = true;
            super.legendChanged = true;
            this.p = (Vector)super.param.get("SliceLabelContent");
        }
        if (super.param.changed("SliceLabelContentDelimiter")) {
            super.graphChanged = true;
            this.r = NFUtil.getString(super.param.get("SliceLabelContentDelimiter"), this.q);
        }
        boolean b2 = false;
        if (super.param.changed("Slices") || super.param.changed("SliceFormat") || super.param.changed("ActiveLabels") || super.param.changed("ActiveLabelsEnabled") || super.param.changed("SliceData") || super.param.changed("SliceLabels") || super.param.changed("SliceLabel") || super.param.changed("SliceLabelBox") || super.param.changed("SliceFillPattern") || super.param.changed("SliceColors")) {
            super.graphChanged = true;
            super.legendChanged = true;
            b2 = true;
            final boolean equals = NFUtil.getString(super.param.get("ActiveLabelsEnabled"), "ON").equals("ON");
            this.j = NFActiveLabel.loadAllParams(super.param, "ActiveLabels");
            for (int i = 0; i < this.j.size(); ++i) {
                final NFActiveLabel nfActiveLabel = this.j.elementAt(i);
                if (nfActiveLabel.label == null || nfActiveLabel.label.length() == 0) {
                    nfActiveLabel.autoLabel = false;
                }
                else {
                    nfActiveLabel.autoLabel = true;
                }
            }
            final Vector vector = (Vector)super.param.get("SliceFormat");
            this.g = NFUtil.getNumber(vector, 0, this.f);
            this.i = NFUtil.getString(vector, 1, this.h);
            if (super.param.changed("Slices")) {
                final Vector vector2 = (Vector)super.param.get("Slices");
                this.u = "Slices";
                this.deleteAllSlices();
                int n = 0;
                final Enumeration<Vector<Number>> elements = vector2.elements();
                while (elements.hasMoreElements()) {
                    final Vector<Number> vector3 = elements.nextElement();
                    final String string = "slice" + new Integer(n).toString();
                    final double doubleValue = vector3.elementAt(0).doubleValue();
                    Color defaultColor = (Color)vector3.elementAt(1);
                    if (defaultColor == null) {
                        defaultColor = this.defaultColor(n);
                    }
                    NFActiveLabel a;
                    if (equals) {
                        a = this.a(n, doubleValue);
                    }
                    else {
                        a = null;
                    }
                    this.addSlice(string, doubleValue, defaultColor, a);
                    final NFLabel loadParams = NFLabel.loadParams(super.param, vector3, this.a0, false);
                    final NFRegion loadParams2 = NFRegion.loadParams(super.param, vector3, this.a1);
                    loadParams.setComponent(this);
                    loadParams.setRegion(loadParams2);
                    this.setSliceLabel(string, loadParams);
                    ++n;
                }
            }
            if (super.param.changed("SliceData")) {
                final Vector vector4 = (Vector)super.param.get("SliceData");
                this.u = "SliceData";
                if (vector4 != null && vector4.size() > 0) {
                    for (int j = 0; j < vector4.size(); ++j) {
                        if (j >= this.a.size()) {
                            this.createHiddenSlice(j);
                        }
                        final NFPieSlice nfPieSlice = this.a.elementAt(j);
                        nfPieSlice.c = vector4.elementAt(j);
                        if (equals) {
                            nfPieSlice.f = this.a(j, nfPieSlice.c);
                        }
                        else {
                            if (super.dwell != null && nfPieSlice.f != null) {
                                super.dwell.removeLabel(nfPieSlice.f);
                            }
                            nfPieSlice.f = null;
                        }
                        if (super.dwell != null && nfPieSlice.f != null) {
                            super.dwell.addLabel(nfPieSlice.f);
                        }
                    }
                }
                for (int k = (vector4 == null) ? 0 : vector4.size(); k < this.a.size(); ++k) {
                    ((NFPieSlice)this.a.elementAt(k)).c = -1.0;
                }
            }
            if (super.param.changed("SliceColors")) {
                super.layoutChanged = true;
                final Vector vector5 = (Vector)super.param.get("SliceColors");
                if (vector5 != null && vector5.size() > 0) {
                    for (int l = 0; l < vector5.size(); ++l) {
                        if (l >= this.a.size()) {
                            this.createHiddenSlice(l);
                        }
                        final NFPieSlice nfPieSlice2 = this.a.elementAt(l);
                        final Color color = vector5.elementAt(l);
                        nfPieSlice2.d = ((color == null) ? this.defaultColor(l) : color);
                    }
                }
                for (int n2 = (vector5 == null) ? 0 : vector5.size(); n2 < this.a.size(); ++n2) {
                    ((NFPieSlice)this.a.elementAt(n2)).d = this.defaultColor(n2);
                }
            }
            if (super.param.changed("SliceLabels")) {
                final Vector vector6 = (Vector)super.param.get("SliceLabels");
                if (vector6 != null && vector6.size() > 0) {
                    for (int n3 = 0; n3 < vector6.size(); ++n3) {
                        if (n3 >= this.a.size()) {
                            this.createHiddenSlice(n3);
                        }
                        final NFPieSlice nfPieSlice3 = this.a.elementAt(n3);
                        nfPieSlice3.b = vector6.elementAt(n3);
                        NFDebug.print(128L, "Just set labelstring of " + nfPieSlice3.b + " for " + n3 + "th element");
                    }
                }
                for (int n4 = (vector6 == null) ? 0 : vector6.size(); n4 < this.a.size(); ++n4) {
                    ((NFPieSlice)this.a.elementAt(n4)).b = "";
                }
            }
            if (super.param.changed("SliceLabel") || super.param.changed("SliceLabelBox")) {
                final Vector vector7 = (Vector)super.param.get("SliceLabel");
                final Vector vector8 = (Vector)super.param.get("SliceLabelBox");
                if (vector7 != null) {
                    this.af = NFLabel.loadParams(super.param, vector7);
                    final NFRegion loadParams3 = NFRegion.loadParams(super.param, vector8);
                    if (loadParams3 != null) {
                        if (loadParams3.getColor() == null) {
                            this.aa = true;
                        }
                        else {
                            this.aa = false;
                        }
                    }
                    this.af.setRegion(loadParams3);
                    for (int n5 = 0; n5 < this.a.size(); ++n5) {
                        ((NFPieSlice)this.a.elementAt(n5)).e = new NFLabel(this.af);
                    }
                }
            }
            if (super.param.changed("SliceFillPattern")) {
                this.loadPatternFill(super.param, "SliceFillPattern");
            }
            this.a();
        }
        if (super.param.changed("SlicePos") || b2) {
            super.graphChanged = true;
            final Vector vector9 = (Vector)super.param.get("SlicePos");
            if (vector9 != null) {
                for (int n6 = 0; n6 < this.a.size(); ++n6) {
                    final NFPieSlice nfPieSlice4 = this.a.elementAt(n6);
                    if (n6 < vector9.size()) {
                        final Double n7 = vector9.elementAt(n6);
                        if (n7 != null) {
                            if (!n7.isNaN()) {
                                nfPieSlice4.k = n7;
                                if (nfPieSlice4.k < 0.0) {
                                    nfPieSlice4.k = 0.0;
                                }
                                if (nfPieSlice4.k > 2.0) {
                                    final NFPieSlice nfPieSlice5 = nfPieSlice4;
                                    nfPieSlice5.k /= 100.0;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.setAllMapComponent(this, super.notesets);
            for (int size = super.notesets.size(), n8 = 0; n8 < size; ++n8) {
                ((NFNoteSet)super.notesets.elementAt(n8)).setAxisMap(null);
            }
        }
        if (super.param.changed("SliceLabelLine")) {
            super.graphChanged = true;
            this.s = NFLine.loadParams(super.param, super.param.get("SliceLabelLine"));
            if (this.s == null || (this.s != null && this.s.getColor() == null)) {
                this.y = true;
            }
            else {
                this.y = false;
            }
        }
        if (super.param.changed("PieSize")) {
            super.graphChanged = true;
            final Vector vector10 = (Vector)super.param.get("PieSize");
            this.ai.width = NFUtil.getNumber(vector10, 0, this.ag);
            this.ai.height = NFUtil.getNumber(vector10, 1, this.ah);
            this.al.width = NFUtil.getNumber(vector10, 2, this.aj);
            this.al.height = NFUtil.getNumber(vector10, 3, this.ak);
        }
    }
    
    protected void loadPatternFill(final NFParam nfParam, final String s) throws NFParamException {
        if (nfParam.changed(s)) {
            super.graphChanged = true;
            final NFPatternFill[] loadPatternFillParam = NFPatternFill.loadPatternFillParam(nfParam, s);
            for (int i = 0; i < this.a.size(); ++i) {
                ((NFPieSlice)this.a.elementAt(i)).u = ((loadPatternFillParam == null || i >= loadPatternFillParam.length) ? null : loadPatternFillParam[i]);
            }
        }
    }
    
    public void createHiddenSlice(final int n) {
        final NFPieSlice initSlice = this.initSlice("slice" + n);
        initSlice.c = -1.0;
        initSlice.d = this.defaultColor(n);
        initSlice.e = new NFLabel(this.af);
        this.a.addElement(initSlice);
    }
    
    public void clean() {
        super.clean();
        this.deleteAllSlices();
        this.clearDiscardedLabels();
        this.s = null;
        this.y = this.x;
        this.aa = this.z;
        this.m = this.l;
        this.u = this.t;
        this.af = this.ae;
        this.initPiechart();
        if (this.o != null) {
            this.o.removeAllElements();
        }
        if (this.n != null) {
            this.n.removeAllElements();
        }
    }
    
    private NFActiveLabel a(final int n, final double n2) {
        NFActiveLabel nfActiveLabel;
        if (super.dwell != null && super.dwell.getSelectMode()) {
            nfActiveLabel = new NFActiveLabel("Slice:" + n, null, null);
        }
        else {
            if (this.j != null && n < this.j.size()) {
                nfActiveLabel = this.j.elementAt(n);
            }
            else {
                nfActiveLabel = new NFActiveLabel();
            }
            final String label = nfActiveLabel.label;
            if (label == null || label.length() == 0 || !nfActiveLabel.autoLabel) {
                nfActiveLabel.label = this.a(n2);
            }
        }
        this.a(nfActiveLabel, n);
        return nfActiveLabel;
    }
    
    private void a(final NFPieSlice nfPieSlice) {
        this.a(nfPieSlice.f, nfPieSlice, this.a.indexOf(nfPieSlice));
    }
    
    private void a(final NFActiveLabel nfActiveLabel, final int n) {
        this.a(nfActiveLabel, (this.a == null || n >= this.a.size()) ? null : this.a.elementAt(n), n);
    }
    
    private void a(final NFActiveLabel nfActiveLabel, final NFPieSlice nfPieSlice, final int selectedItemIndex) {
        nfActiveLabel.selectedItemParam = this.u;
        nfActiveLabel.selectedItemIndex = selectedItemIndex;
        if (nfActiveLabel.selectedItemLabels == null) {
            nfActiveLabel.selectedItemLabels = new Vector();
        }
        else {
            nfActiveLabel.selectedItemLabels.removeAllElements();
        }
        if (super.legend != null) {
            nfActiveLabel.selectedItemLabels.addElement(new String[] { "LegendLabel", super.legend.getLegendLabel(selectedItemIndex) });
        }
        if (nfPieSlice != null && nfPieSlice.b != null) {
            nfActiveLabel.selectedItemLabels.addElement(new String[] { "SliceLabel", nfPieSlice.b });
        }
    }
    
    public StringBuffer getParam(final String s, StringBuffer a2) {
        if (a2 == null) {
            if (this.a2 == null) {
                this.a2 = new StringBuffer();
            }
            a2 = this.a2;
            a2.setLength(0);
        }
        if (s.equalsIgnoreCase("SlicePos")) {
            for (int i = 0; i < this.a.size(); ++i) {
                final NFPieSlice nfPieSlice = this.a.elementAt(i);
                if (i > 0) {
                    a2.append(",");
                }
                a2.append(nfPieSlice.k + "");
            }
            return a2;
        }
        if (s.equalsIgnoreCase("PieAngle")) {
            a2.append(this.ar + "");
            return a2;
        }
        if (s.equalsIgnoreCase("LabelPos")) {
            a2.append(this.c + "");
            return a2;
        }
        if (s.equalsIgnoreCase("Pie3DDepth")) {
            a2.append(this.e + "");
            return a2;
        }
        return a2;
    }
}
