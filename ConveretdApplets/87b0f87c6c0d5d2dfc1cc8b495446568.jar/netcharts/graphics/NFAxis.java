// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Enumeration;
import java.awt.Component;
import java.util.Hashtable;
import netcharts.util.NFParamDef;
import netcharts.util.NFParam;
import java.awt.Event;
import netcharts.util.NFColor;
import java.util.Date;
import netcharts.util.NF11Util;
import java.awt.Point;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import netcharts.util.NFUtil;
import netcharts.util.NFDebug;
import netcharts.util.NFTimeUnit;
import netcharts.util.NFDate;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Vector;

public class NFAxis
{
    public static final int TicDefault = 0;
    public static final int TicTop = 1;
    public static final int TicLeft = 2;
    public static final int TicRight = 3;
    public static final int TicBottom = 4;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DATE = 3;
    public static final int SIMPLEDATE = 4;
    public static final int DECIMAL = 5;
    public static final int NORMAL = 1;
    public static final int SKIP = 2;
    public static final int STAGGER = 3;
    public static final int SKIPSTAGGER = 4;
    public static final int AUTOSKIP = 5;
    public static final int AUTOSTAGGER = 6;
    public static final int AUTO = 7;
    public static final int LINEAR = 0;
    public static final int LOG = 1;
    public static final int AlignCenter = 1;
    public static final int AlignLeft = 2;
    public static final int AlignRight = 4;
    public static final int AlignTop = 8;
    public static final int AlignBottom = 16;
    public static final int Integer = 1;
    public static final int Float = 2;
    public static final int Date = 3;
    public boolean autoscale;
    public boolean autoscroll;
    protected boolean scaleSpecified;
    private final boolean a = false;
    private final int b = 32767;
    private NFSpacing c;
    private NFSpacing d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private Vector l;
    private Vector m;
    private Vector n;
    private static int o;
    private int p;
    private double q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private static double v;
    private double w;
    private static double x;
    private double y;
    private Object z;
    private Object aa;
    private Object ab;
    private int ac;
    private static Color ad;
    private Color ae;
    private NFLabel af;
    private NFLabel ag;
    private int ah;
    private int ai;
    private int aj;
    private static int ak;
    private int al;
    private static int am;
    private int an;
    private static boolean ao;
    private boolean ap;
    private static int aq;
    private int ar;
    private int as;
    private boolean at;
    private double au;
    private double av;
    private Rectangle aw;
    private Rectangle ax;
    private boolean ay;
    private boolean az;
    private static int a0;
    private int a1;
    private static String a2;
    private String a3;
    private static Object a4;
    private Object a5;
    private static Object a6;
    private Object a7;
    private NFDate a8;
    private NFTimeUnit a9;
    private static Color ba;
    private Color bb;
    private NFActiveRegion bc;
    private Rectangle bd;
    private static final int be = 10;
    public boolean reverseActiveLabels;
    private Vector bf;
    private double bg;
    private String bh;
    private String bi;
    private boolean bj;
    private boolean bk;
    private static int bl;
    private int bm;
    private static double bn;
    private double bo;
    private Vector bp;
    private Vector bq;
    private Vector br;
    private Vector bs;
    protected int groupSize;
    protected char groupSymbol;
    protected char decSymbol;
    protected int beginMargin;
    protected int endMargin;
    private Rectangle[] bt;
    private int[] bu;
    private int[] bv;
    private int[] bw;
    private int[] bx;
    private int by;
    private static int bz;
    private int b0;
    private int b1;
    private static final int b2 = 1;
    private static final int b3 = 2;
    private static final int b4 = 3;
    private static final int b5 = 4;
    private int b6;
    private int b7;
    private int b8;
    private static int b9;
    
    public NFAxis(final int n, final int n2, final int n3, final int n4) {
        this.autoscale = true;
        this.autoscroll = true;
        this.scaleSpecified = false;
        this.c = null;
        this.d = null;
        this.k = 3;
        this.l = new Vector();
        this.m = new Vector();
        this.n = new Vector();
        this.p = NFAxis.o;
        this.q = 0.0;
        this.r = true;
        this.s = true;
        this.t = true;
        this.u = true;
        this.w = NFAxis.v;
        this.y = NFAxis.x;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = 0;
        this.ae = NFAxis.ad;
        this.af = new NFLabel();
        this.ag = null;
        this.ah = 0;
        this.ai = 2;
        this.aj = 1;
        this.al = NFAxis.ak;
        this.an = NFAxis.am;
        this.ap = NFAxis.ao;
        this.ar = NFAxis.aq;
        this.as = 1;
        this.at = false;
        this.au = this.w;
        this.av = this.y - this.w;
        this.aw = new Rectangle();
        this.ax = new Rectangle();
        this.ay = false;
        this.az = false;
        this.a1 = NFAxis.a0;
        this.a3 = NFAxis.a2;
        this.a5 = NFAxis.a4;
        this.a7 = NFAxis.a6;
        this.a8 = new NFDate();
        this.a9 = new NFTimeUnit("1d");
        this.bb = NFAxis.ba;
        this.bc = null;
        this.bd = null;
        this.reverseActiveLabels = false;
        this.bf = null;
        this.bg = 0.0;
        this.bh = "Bottom";
        this.bi = null;
        this.bj = false;
        this.bk = false;
        this.bm = NFAxis.bl;
        this.bo = NFAxis.bn;
        this.bp = new Vector();
        this.bq = new Vector();
        this.br = new Vector();
        this.bs = new Vector();
        this.groupSize = 3;
        this.groupSymbol = ',';
        this.decSymbol = '.';
        this.beginMargin = 0;
        this.endMargin = 0;
        this.bt = null;
        this.bu = null;
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.by = 2;
        this.b0 = NFAxis.bz;
        this.b1 = 50;
        this.b6 = 0;
        this.b7 = 0;
        this.b8 = 0;
        this.setAxis(n, n2, n3, n4);
        this.a8.setHours(0);
        this.a8.setMinutes(0);
        this.a8.setSeconds(0);
    }
    
    public static NFAxis defaultAxis(final String s) {
        NFAxis nfAxis;
        if (s.startsWith("Top")) {
            nfAxis = new NFAxis(0, 0, 100, 0);
            nfAxis.setTicPosition(1);
        }
        else if (s.startsWith("Bottom")) {
            nfAxis = new NFAxis(0, 0, 100, 0);
            nfAxis.setTicPosition(4);
        }
        else if (s.startsWith("Left")) {
            nfAxis = new NFAxis(0, 0, 0, 100);
            nfAxis.setTicPosition(2);
        }
        else {
            nfAxis = new NFAxis(0, 0, 0, 100);
            nfAxis.setTicPosition(3);
        }
        nfAxis.setType(new String(s));
        return nfAxis;
    }
    
    public void setType(final String bh) {
        this.bh = bh;
    }
    
    public String getType() {
        return this.bh;
    }
    
    private void a(final String s) {
        NFDebug.print(256L, s);
    }
    
    public void setAxis(final int e, final int f, final int i, final int j) {
        this.e = e;
        this.f = f;
        this.g = e + i;
        this.h = f + j;
        this.i = i;
        this.j = j;
        if (this.ah == 0) {
            if (i == 0) {
                this.ah = 2;
            }
            else {
                this.ah = 4;
            }
        }
        this.setup();
        this.b();
    }
    
    public NFSpacing getSpacing() {
        return this.c;
    }
    
    public void setSpacing(final NFSpacing d) {
        this.d = d;
        this.setup();
    }
    
    public void setTicLabels(final Vector vector) {
        this.setTicLabels(vector, null);
    }
    
    public Vector getTicLabels() {
        return this.l;
    }
    
    public void setTicLabels(final Vector l, final String bi) {
        this.l = l;
        this.bi = bi;
        if (this.l != null && this.l.size() > 0) {
            this.bj = true;
        }
        double n = this.w;
        double y = this.y;
        if (this.at) {
            n = this.au;
            y = this.au + this.av;
        }
        this.setSpacing(new NFSpacing(n, y, this.l.size() - 1));
    }
    
    public void setTicLocations(final Vector vector) {
        final Vector vector2 = new Vector<Number>();
        for (int n = 0; vector != null && n < vector.size(); ++n) {
            final Number element = vector.elementAt(n);
            if (element != null && (!(element instanceof Number) || !Double.isNaN(element.doubleValue()))) {
                vector2.addElement(element);
            }
        }
        final NFSpacing spacing = (vector2 == null || vector2.size() <= 0) ? null : new NFSpacing(vector2);
        this.bk = false;
        this.setSpacing(spacing);
        this.bk = (spacing != null);
    }
    
    public void loadScaleSet(final Vector vector) {
        this.clearZoomHistory();
        this.emptyScaleSets();
        NFAxisScaleSetElement nfAxisScaleSetElement = null;
        for (int n = 0; vector != null && n < vector.size(); ++n) {
            final Vector vector2 = vector.elementAt(n);
            if (vector2 != null) {
                final NFAxisScaleSetElement nfAxisScaleSetElement2 = new NFAxisScaleSetElement();
                nfAxisScaleSetElement2.min = NFUtil.getObject(vector2, 0, null);
                if (nfAxisScaleSetElement != null && !nfAxisScaleSetElement.max.equals(nfAxisScaleSetElement2.min)) {
                    nfAxisScaleSetElement2.min = nfAxisScaleSetElement.max;
                }
                nfAxisScaleSetElement2.max = NFUtil.getObject(vector2, 1, null);
                nfAxisScaleSetElement2.step = NFUtil.getObject(vector2, 2, null);
                nfAxisScaleSetElement2.percent = NFUtil.getNumber(vector2, 3, 0.0);
                if (nfAxisScaleSetElement2.percent > 100.0) {
                    nfAxisScaleSetElement2.percent = 100.0;
                }
                if (nfAxisScaleSetElement2.percent < 0.0) {
                    nfAxisScaleSetElement2.percent = 0.0;
                }
                if (nfAxisScaleSetElement2.min != null && nfAxisScaleSetElement2.max != null && !Double.isNaN(nfAxisScaleSetElement2.percent) && nfAxisScaleSetElement2.percent != 0.0 && (!(nfAxisScaleSetElement2.max instanceof Number) || !Double.isNaN(((Number)nfAxisScaleSetElement2.max).doubleValue())) && (!(nfAxisScaleSetElement2.min instanceof Number) || !Double.isNaN(((Number)nfAxisScaleSetElement2.min).doubleValue()))) {
                    this.bq.addElement(nfAxisScaleSetElement2);
                    nfAxisScaleSetElement = nfAxisScaleSetElement2;
                }
            }
        }
        if (this.at) {
            this.modifyScaleSetsFromScroll();
        }
        final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(0, true);
        final NFAxisScaleSetElement scaleSetElement2 = this.getScaleSetElement(this.getScaleSetSize(true) - 1, true);
        this.setScaleSpecified(nfAxisScaleSetElement != null);
        this.a((scaleSetElement == null) ? NFAxis.v : this.getValue(scaleSetElement.min), (scaleSetElement2 == null) ? NFAxis.x : this.getValue(scaleSetElement2.max), true);
    }
    
    protected static int getExponent(final double n, final boolean b, final double n2) {
        int n3 = 0;
        double pow = Math.pow(n2, n3);
        if (n > pow) {
            for (double n4 = Math.pow(n2, ++n3); n != n4; n4 = Math.pow(n2, ++n3)) {
                if (n > pow && n < n4) {
                    return n3 - 1;
                }
                pow = n4;
            }
            if (b) {
                return n3 - 1;
            }
            return n3;
        }
        else if (n < pow) {
            for (double n5 = Math.pow(n2, --n3); n < n5 || n > pow; pow = n5, n5 = Math.pow(n2, --n3)) {}
            if (b) {
                return n3 + 1;
            }
            return n3;
        }
        else {
            if (b) {
                return n3 - 1;
            }
            return n3;
        }
    }
    
    protected void buildLogScaleSet(final double n, final double n2, final double n3) {
        this.clearZoomHistory();
        this.emptyScaleSets();
        final int exponent = getExponent(n, false, n3);
        final int exponent2 = getExponent(n2, true, n3);
        if (exponent == exponent2) {
            final NFAxisScaleSetElement nfAxisScaleSetElement = new NFAxisScaleSetElement();
            nfAxisScaleSetElement.min = new Double(n);
            nfAxisScaleSetElement.max = new Double(n2);
            nfAxisScaleSetElement.percent = 100.0;
            nfAxisScaleSetElement.step = new Double(1.0);
            this.bq.addElement(nfAxisScaleSetElement);
        }
        else {
            final double n4 = (NFUtil.log(n2, n3) - exponent2) / 1.0;
            final double n5 = 1.0 - (NFUtil.log(n, n3) - exponent) / 1.0;
            final double n6 = n5 + n4 + (exponent2 - exponent - 1);
            int i = exponent;
            final NFAxisScaleSetElement nfAxisScaleSetElement2 = new NFAxisScaleSetElement();
            nfAxisScaleSetElement2.min = new Double(n);
            nfAxisScaleSetElement2.max = new Double(Math.pow(n3, ++i));
            nfAxisScaleSetElement2.percent = n5 / n6 * 100.0;
            nfAxisScaleSetElement2.step = new Double(1.0);
            this.bq.addElement(nfAxisScaleSetElement2);
            while (i < exponent2) {
                final NFAxisScaleSetElement nfAxisScaleSetElement3 = new NFAxisScaleSetElement();
                nfAxisScaleSetElement3.min = new Double(Math.pow(n3, i));
                nfAxisScaleSetElement3.max = new Double(Math.pow(n3, i + 1));
                nfAxisScaleSetElement3.percent = 1.0 / n6 * 100.0;
                nfAxisScaleSetElement3.step = new Double(1.0);
                this.bq.addElement(nfAxisScaleSetElement3);
                ++i;
            }
            final NFAxisScaleSetElement nfAxisScaleSetElement4 = new NFAxisScaleSetElement();
            nfAxisScaleSetElement4.min = new Double(Math.pow(n3, i));
            nfAxisScaleSetElement4.max = new Double(n2);
            nfAxisScaleSetElement4.percent = n4 / n6 * 100.0;
            nfAxisScaleSetElement4.step = new Double(1.0);
            this.bq.addElement(nfAxisScaleSetElement4);
        }
    }
    
    public void setTicLayout(final int an, final int al, final int b0) {
        this.an = an;
        if (an == 2 || an == 4) {
            this.al = al;
        }
        else {
            this.al = 0;
        }
        if (b0 > 0 && (an == 6 || an == 7)) {
            this.by = b0 + 1;
            this.b0 = 0;
        }
        else if (b0 > 0 && (an == 3 || an == 4)) {
            this.b0 = b0;
            this.by = b0 + 1;
        }
        else {
            this.by = 2;
            this.b0 = 0;
        }
    }
    
    public void changeTicLabels(final Vector l) {
        this.l = l;
    }
    
    public void setTicLength(final int ac) {
        this.ac = ac;
    }
    
    public void setTicPosition(final int ah) {
        this.ah = ah;
    }
    
    public int getTicPosition() {
        return this.ah;
    }
    
    public void setTicLabelType(final int ai) {
        this.ai = ai;
    }
    
    public void setTicLabelAlignment(final int aj) {
        this.aj = aj;
    }
    
    public void setTicLabel(final NFLabel af) {
        this.af = af;
        this.ac = 0;
        af.setScale(this.bg);
    }
    
    public void setTitleLabel(final NFLabel ag) {
        final NFActiveLabel nfActiveLabel = (this.ag == null) ? null : this.ag.getActiveLabel();
        if (nfActiveLabel != null && this.bc != null) {
            this.bc.removeLabel(nfActiveLabel);
        }
        if ((this.ag = ag) == null) {
            return;
        }
        NFActiveLabel activeLabel = ag.getActiveLabel();
        if (activeLabel == null) {
            activeLabel = new NFActiveLabel();
        }
        activeLabel.selectedItemParam = new String(removeModifierFromName(this.getType()) + "AxisTitle" + getModifierOfName(this.getType()));
        activeLabel.selectedItemIndex = 0;
        if (this.bc != null) {
            this.bc.addLabel(activeLabel);
        }
        ag.setActiveLabel(activeLabel);
    }
    
    public NFLabel getTitleLabel() {
        return this.ag;
    }
    
    public NFLabel getTicLabel() {
        return this.af;
    }
    
    public void setAxisColor(final Color ae) {
        this.ae = ae;
    }
    
    public void clearTicLabels() {
        this.l = new Vector();
        this.bj = false;
        this.setup();
    }
    
    public void setTicDivisions(final double w, final double y, final int p3) {
        this.w = w;
        this.y = y;
        this.p = p3;
        this.q = 0.0;
        this.setup();
    }
    
    public void setTicDivisions(final Object o, final Object o2, final Object o3) {
        this.w = this.getValue(o);
        this.y = this.getValue(o2);
        this.p = 0;
        this.q = this.getValue(o3);
        this.setup();
    }
    
    public void setTicDivisions(final double w, final double y, final double q) {
        this.w = w;
        this.y = y;
        this.p = 0;
        this.q = q;
        this.setup();
    }
    
    public void setMinMax(final double w, final double y) {
        this.w = w;
        this.y = y;
        this.setup();
    }
    
    public boolean setMinMaxRect(int max, int max2, int n, int n2) {
        if (!this.at) {
            return false;
        }
        double n4;
        double n5;
        if (this.i != 0) {
            final int n3 = max;
            max = Math.max(max, this.getX1());
            n -= max - n3;
            n = Math.min(max + n, this.getX1() + this.getDX()) - max;
            if (n <= 0) {
                return false;
            }
            n4 = this.unMapValue(max);
            n5 = this.unMapValue(max + n);
        }
        else {
            if (this.j == 0) {
                return false;
            }
            final int n6 = max2;
            max2 = Math.max(max2, this.getY1() + this.getDY());
            n2 -= max2 - n6;
            n2 = Math.min(max2 + n2, this.getY1()) - max2;
            if (n2 <= 0) {
                return false;
            }
            n5 = this.unMapValue(max2);
            n4 = this.unMapValue(max2 + n2);
        }
        this.pushZoomIn();
        return this.c(n4, n5);
    }
    
    public void pushZoomOut() {
        this.pushMinMax(this.bs);
    }
    
    public void pushZoomIn() {
        this.pushMinMax(this.br);
    }
    
    public void pushMinMax(final Vector vector) {
        final double[] array = { this.w, this.y, this.q };
        if (vector.size() > 0) {
            final double[] array2 = vector.elementAt(vector.size() - 1);
            if (array2[0] == array[0] && array2[1] == array[1] && array2[2] == array[2]) {
                return;
            }
        }
        vector.addElement(array);
    }
    
    public void clearZoomHistory() {
        this.br.removeAllElements();
        this.bs.removeAllElements();
    }
    
    public int getZoomInHistorySize() {
        return this.br.size();
    }
    
    public int getZoomOutHistorySize() {
        return this.bs.size();
    }
    
    public double[] popZoomOut() {
        return this.popMinMax(this.bs);
    }
    
    public double[] popZoomIn() {
        return this.popMinMax(this.br);
    }
    
    public double[] popMinMax(final Vector vector) {
        if (vector.size() == 0) {
            return null;
        }
        final double[] array = vector.elementAt(vector.size() - 1);
        vector.removeElementAt(vector.size() - 1);
        return array;
    }
    
    public void setScrollLimits(final double w, final double n) {
        this.au = w;
        this.av = n - w;
        if (this.w < w) {
            this.w = w;
        }
        if (this.y > n) {
            this.y = n;
        }
        if (this.w == this.y) {
            this.w = w;
            this.y = n;
        }
        if (w > 0.0 && this.bm == 1) {
            this.buildLogScaleSet(w, n, this.getScaleModeBase());
        }
        this.modifyScaleSetsFromScroll();
        this.setup();
    }
    
    protected void modifyScaleSetsFromScroll() {
        final double au = this.au;
        final double n = this.au + this.av;
        Vector scaleSetFromMinMax = new Vector<NFAxisScaleSetElement>();
        double n2 = 0.0;
        double n3 = 0.0;
        for (int i = 0; i < this.bq.size(); ++i) {
            final NFAxisScaleSetElement nfAxisScaleSetElement = this.bq.elementAt(i);
            final double value = this.getValue(nfAxisScaleSetElement.min);
            final double value2 = this.getValue(nfAxisScaleSetElement.max);
            if (value > n || value2 < au) {
                n2 += nfAxisScaleSetElement.percent;
            }
            else {
                final double max = Math.max(au, value);
                if (max != value) {
                    nfAxisScaleSetElement.min = new Double(max);
                }
                final double min = Math.min(n, value2);
                if (min != value2) {
                    nfAxisScaleSetElement.max = new Double(min);
                }
                if (max == min) {
                    n2 += nfAxisScaleSetElement.percent;
                }
                else {
                    final NFAxisScaleSetElement nfAxisScaleSetElement2 = nfAxisScaleSetElement;
                    nfAxisScaleSetElement2.percent += n2;
                    n3 += nfAxisScaleSetElement.percent;
                    n2 = 0.0;
                    scaleSetFromMinMax.addElement(nfAxisScaleSetElement);
                }
            }
        }
        if (scaleSetFromMinMax.equals(this.bq)) {
            return;
        }
        if (n3 != 100.0 && scaleSetFromMinMax.size() > 0) {
            final NFAxisScaleSetElement nfAxisScaleSetElement3 = scaleSetFromMinMax.elementAt(scaleSetFromMinMax.size() - 1);
            nfAxisScaleSetElement3.percent += 100.0 - n3;
        }
        if (scaleSetFromMinMax.size() == 0) {
            scaleSetFromMinMax = this.createScaleSetFromMinMax();
        }
        this.bq = scaleSetFromMinMax;
    }
    
    public void setScrollLimits(final Object o, final Object o2) {
        this.setScrollLimits(this.getValue(o), this.getValue(o2));
    }
    
    public double[] getScrollLimits() {
        return new double[] { this.au, this.au + this.av };
    }
    
    public void setScroll(final boolean at) {
        this.at = at;
        if (this.at) {
            this.as = 15;
        }
        else {
            this.as = 1;
        }
    }
    
    public double getMin() {
        return this.w;
    }
    
    public double getMax() {
        return this.y;
    }
    
    public double getStepSize() {
        return this.q;
    }
    
    public int getMinCoord() {
        switch (this.ah) {
            case 1:
            case 4: {
                return (this.e < this.g) ? this.e : this.g;
            }
            case 2:
            case 3: {
                return (this.f < this.h) ? this.f : this.h;
            }
            default: {
                return 0;
            }
        }
    }
    
    public int getMaxCoord() {
        switch (this.ah) {
            case 1:
            case 4: {
                return (this.e > this.g) ? this.e : this.g;
            }
            case 2:
            case 3: {
                return (this.f > this.h) ? this.f : this.h;
            }
            default: {
                return 0;
            }
        }
    }
    
    public boolean getChanged() {
        return this.az || this.ay;
    }
    
    public boolean getShowAxis() {
        return this.t;
    }
    
    public void showAxis(final boolean t) {
        this.t = t;
    }
    
    public void showAxisLine(final boolean u) {
        this.u = u;
    }
    
    public void showAxisAndLine(final boolean t, final boolean u) {
        this.t = t;
        this.u = u;
    }
    
    public boolean getShowTics() {
        return this.r;
    }
    
    public void showTics(final boolean r) {
        this.r = r;
    }
    
    public void showTicLabels(final boolean s) {
        this.s = s;
    }
    
    public void showTicsAndLabels(final boolean r, final boolean s) {
        this.r = r;
        this.s = s;
    }
    
    public int getTicLength() {
        return this.ac;
    }
    
    public Dimension getBounds(final Graphics graphics) {
        this.bd = this.getRect(graphics, this.bd);
        return new Dimension(this.bd.width, this.bd.height);
    }
    
    public Rectangle getRect(final Graphics graphics) {
        return this.getRect(graphics, null);
    }
    
    public Rectangle getRect(final Graphics graphics, Rectangle rectangle) {
        final Dimension dimension = new Dimension(0, 0);
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        if (rectangle == null) {
            rectangle = new Rectangle(0, 0, 0, 0);
        }
        rectangle.x = this.getX1();
        rectangle.y = this.getY1();
        rectangle.width = 0;
        rectangle.height = 0;
        this.a(graphics, false, rectangle);
        this.b(graphics, false, rectangle, dimension, nfRegionBorder);
        this.c(graphics, false, rectangle, dimension, nfRegionBorder);
        this.a(graphics, false, rectangle, dimension, nfRegionBorder);
        if (this.ah == 1 || this.ah == 4) {
            final Rectangle rectangle2 = rectangle;
            rectangle2.width += this.endMargin;
        }
        else {
            final Rectangle rectangle3 = rectangle;
            rectangle3.height += this.endMargin;
        }
        return rectangle;
    }
    
    private void a(final Graphics graphics, final boolean b, final Rectangle rectangle, Dimension bounds, final NFRegionBorder nfRegionBorder) {
        if (this.ag == null) {
            return;
        }
        bounds = this.ag.getBounds(graphics, bounds, nfRegionBorder);
        Rectangle rectangle2 = null;
        switch (this.ah) {
            case 4: {
                rectangle2 = new Rectangle(rectangle.x, rectangle.y + rectangle.height + this.k, rectangle.width, bounds.height);
                rectangle.height += bounds.height + this.k;
                break;
            }
            case 1: {
                rectangle2 = new Rectangle(rectangle.x, rectangle.y - bounds.height - this.k, rectangle.width, bounds.height);
                rectangle.y -= bounds.height + this.k;
                rectangle.height += bounds.height + this.k;
                break;
            }
            case 3: {
                rectangle2 = new Rectangle(rectangle.x + rectangle.width + this.k, rectangle.y, bounds.width, rectangle.height);
                rectangle.width += bounds.width + this.k;
                break;
            }
            case 2: {
                rectangle2 = new Rectangle(rectangle.x - bounds.width - this.k, rectangle.y, bounds.width, rectangle.height);
                rectangle.x -= bounds.width + this.k;
                rectangle.width += bounds.width + this.k;
                break;
            }
        }
        if (b) {
            this.ag.externallyJustify(rectangle2);
            this.ag.draw(graphics);
        }
    }
    
    private void a(final Graphics graphics, final int n, int n2, int n3, final String label, final int n4, final boolean b, final Dimension dimension) {
        this.af.setLabel(label);
        if (this.af.getJustify() == -1) {
            switch (this.ah) {
                case 1:
                case 4: {
                    this.af.setJustify(4);
                    break;
                }
                case 2: {
                    this.af.setJustify(0);
                    break;
                }
                case 3: {
                    this.af.setJustify(1);
                    break;
                }
            }
        }
        int n5 = 0;
        int n6 = 0;
        if (n4 != 1 || this.n != null) {
            n5 = n2 - dimension.width / 2;
            n6 = n3 - dimension.height / 2;
        }
        if (n4 != 1) {
            if (this.ah == 1 || this.ah == 4) {
                if ((n4 & 0x4) == 0x4) {
                    n2 -= dimension.width / 2;
                }
                else if ((n4 & 0x2) == 0x2) {
                    n2 += dimension.width / 2;
                }
            }
            else if ((n4 & 0x8) == 0x8) {
                n3 += dimension.height / 2;
            }
            else if ((n4 & 0x10) == 0x10) {
                n3 -= dimension.height / 2;
            }
        }
        if (b) {
            this.af.draw(graphics, n2, n3);
        }
        else {
            this.af.draw(graphics, n2, n3, null, null);
        }
        if (this.n != null) {
            this.a(n, n5, n6, dimension.width, dimension.height);
        }
    }
    
    protected double getNthValue(final int n) {
        if (this.c.getDataType() == 1) {
            return this.c.getNthValue(n);
        }
        return this.getValue(this.c.getNthObject(n));
    }
    
    public Point getNthPoint(final int n) {
        return this.mapValue(this.getNthValue(n));
    }
    
    public Point getNthPoint(final int n, final Point point) {
        return this.mapValue(this.getNthValue(n), point);
    }
    
    public Point mapValue(final double n) {
        if (Double.isNaN(n)) {
            return null;
        }
        return this.mapValue(n, new Point(0, 0));
    }
    
    protected void emptyScaleSets() {
        this.emptyScaleSet(false);
        this.emptyScaleSet(true);
    }
    
    protected void emptyScaleSet() {
        this.emptyScaleSet(false);
    }
    
    protected void emptyScaleSet(final boolean b) {
        if (b) {
            this.bq.removeAllElements();
        }
        else {
            this.bp.removeAllElements();
        }
    }
    
    protected NFSpacing getScaleSetSpacing() {
        return this.getScaleSetSpacing(false);
    }
    
    protected NFSpacing getScaleSetSpacing(final boolean b) {
        final Vector vector = new Vector<Double>();
        if (this.getScaleMode() == 1 && this.getScaleSetSize(b) > 0) {
            final double scaleModeBase = this.getScaleModeBase();
            for (int exponent = getExponent(this.getValue(this.getScaleSetElement(0, b).min), false, scaleModeBase), i = 0; i < this.getScaleSetSize(b); ++i, ++exponent) {
                final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(i, b);
                final double pow = Math.pow(scaleModeBase, exponent);
                double value = this.getValue(scaleSetElement.min);
                final double value2 = this.getValue(scaleSetElement.max);
                final Double n = new Double(value);
                if (!vector.contains(n)) {
                    vector.addElement(n);
                }
                int n2 = 1;
                if (pow < value) {
                    final double n3 = pow;
                    while (n2 < scaleModeBase - 1.0) {
                        if (n3 + n2 * pow > value) {
                            value = n3;
                            break;
                        }
                        ++n2;
                    }
                }
                while (value + n2 * pow < value2 && n2 < scaleModeBase - 1.0) {
                    final Double n4 = new Double(value + n2 * pow);
                    if (!vector.contains(n4)) {
                        vector.addElement(n4);
                    }
                    ++n2;
                }
                if (i + 1 >= this.getScaleSetSize(b)) {
                    final Double n5 = new Double(value2);
                    if (!vector.contains(n5)) {
                        vector.addElement(n5);
                    }
                }
            }
        }
        else {
            for (int j = 0; j < this.getScaleSetSize(b); ++j) {
                final NFAxisScaleSetElement scaleSetElement2 = this.getScaleSetElement(j, b);
                final NFSpacing nfSpacing = new NFSpacing(this.getValue(scaleSetElement2.min), this.getValue(scaleSetElement2.max), this.getValue(scaleSetElement2.step));
                for (int k = 0; k < nfSpacing.size() - ((j != this.getScaleSetSize(b) - 1) ? 1 : 0); ++k) {
                    vector.addElement(new Double(nfSpacing.getNthValue(k)));
                }
            }
        }
        return new NFSpacing(vector);
    }
    
    protected int getScaleSetElementByValue(final double n) {
        return this.getScaleSetElementByValue(n, false);
    }
    
    protected int getScaleSetElementByValue(final double n, final boolean b) {
        for (int i = 0; i < this.getScaleSetSize(b); ++i) {
            final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(i, b);
            if (n >= this.getValue(scaleSetElement.min) && n <= this.getValue(scaleSetElement.max)) {
                return i;
            }
        }
        if (n < this.getValue(this.getScaleSetElement(0, b).min)) {
            return 0;
        }
        if (n > this.getValue(this.getScaleSetElement(this.getScaleSetSize(b) - 1, b).max)) {
            return this.getScaleSetSize(b) - 1;
        }
        return -1;
    }
    
    public int getScaleSetElementByPercent(final double n) {
        return this.getScaleSetElementByPercent(n, false);
    }
    
    public int getScaleSetElementByPercent(final double n, final boolean b) {
        double n2 = 0.0;
        for (int i = 0; i < this.getScaleSetSize(b); ++i) {
            n2 += this.getScaleSetElement(i, b).percent;
            if (n <= n2) {
                return i;
            }
        }
        if (n < this.getScaleSetElement(0, b).percent) {
            return 0;
        }
        if (n > n2) {
            return this.getScaleSetSize(b) - 1;
        }
        return -1;
    }
    
    protected int getScaleSetSize() {
        return this.getScaleSetSize(false);
    }
    
    protected int getScaleSetSize(final boolean b) {
        if (b) {
            Vector vector = this.bq;
            if (vector.size() == 0) {
                vector = this.createScaleSetFromMinMax();
            }
            return vector.size();
        }
        return this.bp.size();
    }
    
    protected NFAxisScaleSetElement getScaleSetElement(final int n) {
        return this.getScaleSetElement(n, false);
    }
    
    protected NFAxisScaleSetElement getScaleSetElement(final int n, final boolean b) {
        if (n < 0) {
            return null;
        }
        Vector vector;
        if (b) {
            vector = this.bq;
            if (vector.size() == 0) {
                vector = this.createScaleSetFromMinMax();
            }
        }
        else {
            vector = this.bp;
        }
        if (n >= vector.size()) {
            return null;
        }
        return vector.elementAt(n);
    }
    
    protected double getScaleSetPercentageTotal(final int n, final int n2) {
        return this.getScaleSetPercentageTotal(n, n2, false);
    }
    
    protected double getScaleSetPercentageTotal(final int n, final int n2, final boolean b) {
        double n3 = 0.0;
        for (int i = n; i < n2; ++i) {
            n3 += this.getScaleSetElement(i, b).percent;
        }
        return n3;
    }
    
    protected Vector createScaleSetFromMinMax() {
        final Vector<NFAxisScaleSetElement> vector = new Vector<NFAxisScaleSetElement>();
        final NFAxisScaleSetElement nfAxisScaleSetElement = new NFAxisScaleSetElement();
        nfAxisScaleSetElement.min = new Double(this.at ? this.au : this.w);
        nfAxisScaleSetElement.max = new Double(this.at ? (this.au + this.av) : this.y);
        nfAxisScaleSetElement.step = new Double(this.q);
        nfAxisScaleSetElement.percent = 100.0;
        vector.addElement(nfAxisScaleSetElement);
        return vector;
    }
    
    protected void adjustScaleSet() {
        this.emptyScaleSet();
        for (int i = 0; i < this.getScaleSetSize(true); ++i) {
            this.bp.addElement(new NFAxisScaleSetElement(this.getScaleSetElement(i, true)));
        }
        final int scaleSetElementByValue = this.getScaleSetElementByValue(this.w);
        final int scaleSetElementByValue2 = this.getScaleSetElementByValue(this.y);
        for (int j = 0; j < scaleSetElementByValue; ++j) {
            this.bp.removeElementAt(0);
        }
        int n = scaleSetElementByValue2 - scaleSetElementByValue + 1;
        while (this.getScaleSetSize() != scaleSetElementByValue2 - scaleSetElementByValue + 1) {
            this.bp.removeElementAt(this.getScaleSetSize() - 1);
            ++n;
        }
        final int n2 = 0;
        final int n3 = this.getScaleSetSize() - 1;
        this.getScaleSetElement(n2).percent -= (this.w - this.getValue(this.getScaleSetElement(n2).min)) / (this.getValue(this.getScaleSetElement(n2).max) - this.getValue(this.getScaleSetElement(n2).min)) * this.getScaleSetElement(n2).percent;
        this.getScaleSetElement(n2).min = new Double(this.w);
        this.getScaleSetElement(n3).percent -= (1.0 - (this.y - this.getValue(this.getScaleSetElement(n3).min)) / (this.getValue(this.getScaleSetElement(n3).max) - this.getValue(this.getScaleSetElement(n3).min))) * this.getScaleSetElement(n3).percent;
        this.getScaleSetElement(n3).max = new Double(this.y);
        double n4 = 0.0;
        for (int k = 0; k < this.getScaleSetSize(); ++k) {
            n4 += this.getScaleSetElement(k).percent;
        }
        final double n5 = 100.0 / n4;
        double n6 = 0.0;
        for (int l = 0; l < this.getScaleSetSize() - 1; ++l) {
            final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(l);
            scaleSetElement.percent *= n5;
            n6 += this.getScaleSetElement(l).percent;
        }
        this.getScaleSetElement(this.getScaleSetSize() - 1).percent = 100.0 - n6;
    }
    
    public Point mapValue(final double n, final Point point) {
        if (Double.isNaN(n)) {
            return null;
        }
        final double percentage = this.getPercentage(n);
        point.x = this.getX1() + (int)NFUtil.rint(this.getDX() * percentage);
        point.y = this.getY1() + (int)NFUtil.rint(this.getDY() * percentage);
        if (point.x < -32767) {
            point.x = -32767;
        }
        else if (point.x > 32767) {
            point.x = 32767;
        }
        if (point.y < -32767) {
            point.y = -32767;
        }
        else if (point.y > 32767) {
            point.y = 32767;
        }
        return point;
    }
    
    public double getPercentage(final double n) {
        return this.getPercentage(n, this.w, false);
    }
    
    public double getPercentage(double n, final double n2, final boolean b) {
        final int scaleSetElementByValue = this.getScaleSetElementByValue(n, b);
        final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(scaleSetElementByValue, b);
        final double scaleSetPercentageTotal = this.getScaleSetPercentageTotal(this.getScaleSetElementByValue(n2, b), scaleSetElementByValue, b);
        final double value = this.getValue(scaleSetElement.min);
        final double value2 = this.getValue(scaleSetElement.max);
        final double percent = scaleSetElement.percent;
        final double n3 = (this.getScaleMode() == 1) ? NFUtil.log(value2, this.getScaleModeBase()) : value2;
        final double n4 = (this.getScaleMode() == 1) ? NFUtil.log(value, this.getScaleModeBase()) : value;
        n = ((this.getScaleMode() == 1) ? NFUtil.log(n, this.getScaleModeBase()) : n);
        if (n3 - n4 == 0.0) {
            n -= n4;
        }
        else {
            n = (n - n4) / (n3 - n4);
        }
        return n * percent / 100.0 + scaleSetPercentageTotal / 100.0;
    }
    
    public Point mapValue(final Object o) {
        return this.mapValue(this.getValue(o));
    }
    
    public double unMapValue(final double n) {
        return this.unMapValue(n, false);
    }
    
    public double unMapValue(final double n, final boolean b) {
        double n2;
        double n3;
        if (this.j == 0) {
            n2 = this.getDX();
            n3 = this.getX1();
        }
        else {
            if (this.i != 0) {
                return n;
            }
            n2 = this.getDY();
            n3 = this.getY1();
        }
        final int scaleSetElementByPercent = this.getScaleSetElementByPercent((n - n3) / n2 * 100.0, b);
        final NFAxisScaleSetElement scaleSetElement = this.getScaleSetElement(scaleSetElementByPercent, b);
        final double scaleSetPercentageTotal = this.getScaleSetPercentageTotal(this.getScaleSetElementByValue(this.w), scaleSetElementByPercent, b);
        final double value = this.getValue(scaleSetElement.min);
        final double value2 = this.getValue(scaleSetElement.max);
        final double n4 = (this.getScaleMode() == 1) ? NFUtil.log(value2, this.getScaleModeBase()) : value2;
        final double n5 = (this.getScaleMode() == 1) ? NFUtil.log(value, this.getScaleModeBase()) : value;
        final double n6 = (n - n3 - n2 * (scaleSetPercentageTotal / 100.0)) / (scaleSetElement.percent / 100.0 * n2) * (n4 - n5) + n5;
        return (this.getScaleMode() == 1) ? Math.pow(this.getScaleModeBase(), n6) : n6;
    }
    
    public double getValue(final Object o) {
        if (o == null) {
            return 0.0;
        }
        NFDate a8;
        if (this.a5 instanceof NFDate) {
            a8 = (NFDate)this.a5;
        }
        else {
            a8 = this.a8;
        }
        NFTimeUnit a9;
        if (this.a7 instanceof NFTimeUnit) {
            a9 = (NFTimeUnit)this.a7;
        }
        else {
            a9 = this.a9;
        }
        return a9.getValue(o, a8);
    }
    
    private boolean a(final Point point) {
        if (this.ah == 1 || this.ah == 4) {
            return point.x >= this.getXMin() && point.x <= this.getXMax();
        }
        return point.y >= this.getYMin() && point.y <= this.getYMax();
    }
    
    private boolean a(final Rectangle rectangle) {
        if (this.ah == 1 || this.ah == 4) {
            return rectangle.x >= this.getXMin() && rectangle.x <= this.getXMax();
        }
        return rectangle.y >= this.getYMin() && rectangle.y <= this.getYMax();
    }
    
    private void b(final Graphics graphics, final boolean b, final Rectangle rectangle, Dimension bounds, final NFRegionBorder nfRegionBorder) {
        if (this.ac <= 0) {
            this.af.setLabel("0");
            bounds = this.af.getBounds(graphics, bounds, nfRegionBorder);
            this.ac = bounds.width;
            if (this.ac < 3) {
                this.ac = 3;
            }
            if (this.at) {
                this.as = this.ac + 3;
            }
            if (this.i == 0) {
                this.b1 = (int)(1.5 * bounds.height);
            }
            else {
                this.b1 = (int)(1.5 * bounds.width);
            }
            if (this.b1 < 50) {
                this.b1 = 50;
            }
        }
        if (!this.r) {
            return;
        }
        this.a(rectangle, this.as + this.ac);
        if (!b) {
            return;
        }
        int ac = 0;
        int ac2 = 0;
        int as = 0;
        int as2 = 0;
        switch (this.ah) {
            case 4: {
                ac = 0;
                ac2 = this.ac;
                as = 0;
                as2 = this.as;
                break;
            }
            case 1: {
                ac = 0;
                ac2 = -this.ac;
                as = 0;
                as2 = -this.as;
                break;
            }
            case 3: {
                ac = this.ac;
                ac2 = 0;
                as = this.as;
                as2 = 0;
                break;
            }
            case 2: {
                ac = -this.ac;
                ac2 = 0;
                as = -this.as;
                as2 = 0;
                break;
            }
        }
        final int size = this.c.size();
        Point nthPoint = new Point(0, 0);
        for (int i = 0; i < size; ++i) {
            nthPoint = this.getNthPoint(i, nthPoint);
            if (this.a(nthPoint)) {
                final Point point = nthPoint;
                point.x += as;
                final Point point2 = nthPoint;
                point2.y += as2;
                if (this.al > 0 && i % (this.al + 1) != 0) {
                    if (this.ap) {
                        graphics.drawLine(nthPoint.x, nthPoint.y, nthPoint.x + ac / 2, nthPoint.y + ac2 / 2);
                    }
                }
                else {
                    graphics.drawLine(nthPoint.x, nthPoint.y, nthPoint.x + ac, nthPoint.y + ac2);
                }
            }
        }
    }
    
    private void a(final Rectangle rectangle, final int n) {
        if (rectangle == null) {
            return;
        }
        switch (this.ah) {
            case 4: {
                if (rectangle.height < n) {
                    rectangle.height = n;
                    break;
                }
                break;
            }
            case 1: {
                if (rectangle.height < n) {
                    rectangle.y = rectangle.y + rectangle.height - n;
                    rectangle.height = n;
                    break;
                }
                break;
            }
            case 3: {
                if (rectangle.width < n) {
                    rectangle.width = n;
                    break;
                }
                break;
            }
            case 2: {
                if (rectangle.width < n) {
                    rectangle.x = rectangle.x + rectangle.width - n;
                    rectangle.width = n;
                    break;
                }
                break;
            }
        }
    }
    
    private Point a(final int n, final Dimension dimension, Point nthPoint) {
        final int n2 = this.ac + this.k;
        nthPoint = this.getNthPoint(n, nthPoint);
        switch (this.ah) {
            case 1: {
                final Point point = nthPoint;
                point.y -= this.as + (n2 + dimension.height / 2);
                break;
            }
            case 2: {
                final Point point2 = nthPoint;
                point2.x -= this.as + (n2 + dimension.width / 2);
                break;
            }
            case 3: {
                final Point point3 = nthPoint;
                point3.x += this.as + (n2 + dimension.width / 2);
                break;
            }
            case 4: {
                final Point point4 = nthPoint;
                point4.y += this.as + (n2 + dimension.height / 2);
                break;
            }
        }
        return nthPoint;
    }
    
    private void a(final Graphics graphics, final Color color) {
        final Rectangle rect = this.getRect(graphics);
        graphics.setColor(color);
        if (this.ah == 4) {
            graphics.fillRect(rect.x - 5, rect.y + 1 + this.ac, rect.width + 10, rect.height - this.ac);
        }
        else if (this.ah == 1) {
            graphics.fillRect(rect.x - 5, rect.y, rect.width + 10, rect.height - this.ac);
        }
        else if (this.ah != 2) {
            if (this.ah == 3) {}
        }
    }
    
    private void a(final Graphics graphics, Dimension dimension, final NFRegionBorder nfRegionBorder, final int n) {
        if (this.bt == null || this.bt.length != n) {
            this.bt = new Rectangle[n];
        }
        Point a = new Point(0, 0);
        for (int i = 0; i < n; ++i) {
            this.af.setLabel(this.getNthLabel(i));
            dimension = this.af.getBounds(graphics, dimension, nfRegionBorder);
            a = this.a(i, dimension, a);
            if (this.bt[i] == null) {
                this.bt[i] = new Rectangle(0, 0, 0, 0);
            }
            this.bt[i].x = a.x;
            this.bt[i].y = a.y;
            this.bt[i].width = dimension.width;
            this.bt[i].height = dimension.height;
        }
        if (this.an == 7 || this.an == 6 || this.an == 5) {
            this.af.setLabel("W");
            dimension = this.af.getBounds(graphics, dimension, nfRegionBorder);
            this.a(dimension.width / 2);
        }
        if (this.b0 > 0) {
            if (this.bw == null || this.bw.length != this.by) {
                this.bw = new int[this.by];
            }
            if (this.bx == null || this.bx.length != this.by) {
                this.bx = new int[this.by];
            }
            for (int j = 0; j < this.by; ++j) {
                this.bw[j] = 0;
                this.bx[j] = 0;
            }
            int n2 = 0;
            for (int k = 0; k < this.bt.length; ++k) {
                if (this.al <= 0 || k % (this.al + 1) == 0) {
                    if (n2 >= this.b0) {
                        n2 = 0;
                    }
                    switch (this.ah) {
                        case 1:
                        case 4: {
                            if (this.bt[k].height > this.bw[n2]) {
                                this.bw[n2] = this.bt[k].height;
                                break;
                            }
                            break;
                        }
                        case 2:
                        case 3: {
                            if (this.bt[k].width > this.bx[n2]) {
                                this.bx[n2] = this.bt[k].width;
                                break;
                            }
                            break;
                        }
                    }
                    ++n2;
                }
            }
            int n3 = 0;
            int n4 = 0;
            for (int l = 0; l < this.by; ++l) {
                final int n5 = this.bw[l];
                this.bw[l] = n4;
                n4 += n5 + 1;
                final int n6 = this.bx[l];
                this.bx[l] = n3;
                n3 += n6 + 1;
            }
            int n7 = 0;
            for (int n8 = 0; n8 < this.bt.length; ++n8) {
                if (this.al <= 0 || n8 % (this.al + 1) == 0) {
                    if (n7 >= this.b0) {
                        n7 = 0;
                    }
                    switch (this.ah) {
                        case 4: {
                            final Rectangle rectangle = this.bt[n8];
                            rectangle.y += this.bw[n7];
                            break;
                        }
                        case 1: {
                            final Rectangle rectangle2 = this.bt[n8];
                            rectangle2.y -= this.bw[n7];
                            break;
                        }
                        case 3: {
                            final Rectangle rectangle3 = this.bt[n8];
                            rectangle3.x += this.bx[n7];
                            break;
                        }
                        case 2: {
                            final Rectangle rectangle4 = this.bt[n8];
                            rectangle4.x -= this.bx[n7];
                            break;
                        }
                    }
                    ++n7;
                }
            }
        }
    }
    
    private void a(final int n) {
        final Point point = new Point(0, 0);
        int n2 = 0;
        boolean b = true;
        this.al = 0;
        this.b0 = 0;
        if (this.an != 7 && this.an != 6 && this.an != 5) {
            return;
        }
        final boolean b2 = this.ah == 4 || this.ah == 1;
        if (this.bu == null || this.bu.length != this.by || this.bv == null || this.bv.length != this.by) {
            this.bu = new int[this.by];
            this.bv = new int[this.by];
        }
        while (b) {
            b = false;
            for (int i = 0; i < this.by; ++i) {
                this.bu[i] = java.lang.Integer.MIN_VALUE;
                this.bv[i] = java.lang.Integer.MAX_VALUE;
            }
            for (int n3 = 0; n3 < this.bt.length && !b; ++n3) {
                if (this.al <= 0 || n3 % (this.al + 1) == 0) {
                    if (n2 >= this.b0) {
                        n2 = 0;
                    }
                    if (b2) {
                        final int n4 = this.bt[n3].width / 2;
                        if (this.bu[n2] >= this.bt[n3].x - n4 - n) {
                            b = true;
                        }
                        this.bu[n2] = this.bt[n3].x + n4;
                    }
                    else {
                        final int n5 = this.bt[n3].height / 2;
                        if (this.bv[n2] <= this.bt[n3].y + n5 + n) {
                            b = true;
                        }
                        this.bv[n2] = this.bt[n3].y - n5;
                    }
                    ++n2;
                }
            }
            if (b) {
                if (this.an == 7 && this.b0 >= this.by) {
                    ++this.al;
                }
                if ((this.an == 6 || this.an == 7) && this.b0 < this.by) {
                    ++this.b0;
                }
                if (this.an == 5) {
                    ++this.al;
                }
                if (this.an == 6 && this.b0 >= this.by) {
                    break;
                }
                continue;
            }
        }
    }
    
    private void c(final Graphics graphics, final boolean b, final Rectangle rectangle, Dimension bounds, final NFRegionBorder nfRegionBorder) {
        if (!this.s) {
            return;
        }
        final int size = this.c.size();
        Point a = new Point(0, 0);
        boolean b2 = true;
        if (b && this.bb != null) {
            this.a(graphics, this.bb);
        }
        int x = 0;
        int y = 0;
        int n = 0;
        int n2 = 0;
        if (rectangle != null) {
            x = rectangle.x;
            n = rectangle.x + rectangle.width;
            y = rectangle.y;
            n2 = rectangle.y + rectangle.height;
        }
        if (this.an == 1 || this.an == 2) {
            this.bt = null;
        }
        else {
            this.a(graphics, bounds, nfRegionBorder, size);
        }
        for (int i = 0; i < size; ++i) {
            if (this.al <= 0 || i % (this.al + 1) == 0) {
                final String nthLabel = this.getNthLabel(i);
                this.af.setLabel(nthLabel);
                if (this.bt != null) {
                    a.x = this.bt[i].x;
                    a.y = this.bt[i].y;
                    bounds.width = this.bt[i].width;
                    bounds.height = this.bt[i].height;
                }
                else {
                    bounds = this.af.getBounds(graphics, bounds, nfRegionBorder);
                    a = this.a(i, bounds, a);
                }
                if (b && this.a(a)) {
                    this.a(graphics, i, a.x, a.y, nthLabel, this.aj, b2, bounds);
                    b2 = (nthLabel == null || nthLabel.length() == 0);
                }
                if (rectangle != null) {
                    final int width = bounds.width;
                    final int height = bounds.height;
                    final int n3 = width / 2;
                    final int n4 = height / 2;
                    switch (this.ah) {
                        case 4: {
                            if (a.x - n3 < x) {
                                x = a.x - n3;
                            }
                            if (a.x + n3 > n) {
                                n = a.x + n3;
                            }
                            if (a.y + n4 > n2) {
                                n2 = a.y + n4;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            if (a.x - n3 < x) {
                                x = a.x - n3;
                            }
                            if (a.x + n3 > n) {
                                n = a.x + n3;
                            }
                            if (a.y - n4 < y) {
                                y = a.y - n4;
                                break;
                            }
                            break;
                        }
                        case 3: {
                            if (a.x + n3 > n) {
                                n = a.x + n3;
                            }
                            if (a.y - n4 < y) {
                                y = a.y - n4;
                            }
                            if (a.y + n4 > n2) {
                                n2 = a.y + n4;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (a.x - n3 < x) {
                                x = a.x - n3;
                            }
                            if (a.y - n4 < y) {
                                y = a.y - n4;
                            }
                            if (a.y + n4 > n2) {
                                n2 = a.y + n4;
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (rectangle != null) {
            rectangle.x = x;
            rectangle.y = y;
            rectangle.width = n - x;
            rectangle.height = n2 - y;
        }
    }
    
    protected String getNthLabel(final int i) {
        if (this.l == null || this.l.size() == 0) {
            if (i < 0 || i >= this.c.size()) {
                return "";
            }
            String label = null;
            int size = this.m.size();
            if (i < size) {
                label = this.m.elementAt(i);
            }
            if (label == null) {
                label = this.getLabel(this.getNthValue(i));
                while (i >= size) {
                    this.m.addElement(null);
                    ++size;
                }
                this.m.setElementAt(label, i);
            }
            return label;
        }
        else {
            if (i < 0 || i >= this.l.size()) {
                return "";
            }
            return this.l.elementAt(i);
        }
    }
    
    public String getLabel(final double n) {
        return this.getLabel(n, this.a3);
    }
    
    public String getLabel(final double n, final String s) {
        switch (this.a1) {
            case 1:
            case 2:
            case 5: {
                return NFUtil.formatNumericValue(n, this.a1, s, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
            }
            case 3:
            case 4: {
                NFTimeUnit nfTimeUnit;
                if (this.a7 != null && this.a7 instanceof NFTimeUnit) {
                    nfTimeUnit = ((NFTimeUnit)this.a7).mult(n);
                }
                else {
                    nfTimeUnit = this.a9.mult(n);
                }
                NFDate a8;
                if (this.a5 != null && this.a5 instanceof NFDate) {
                    a8 = (NFDate)this.a5;
                }
                else {
                    a8 = this.a8;
                }
                final NFDate addToDate = nfTimeUnit.addToDate(a8);
                if (s == null || s.length() <= 0) {
                    return addToDate.format("M%D\n%h:%m:%s");
                }
                if (this.a1 == 4 && NFUtil.getJDKVersion() >= 1.1) {
                    return NF11Util.formatDate(addToDate, s);
                }
                return addToDate.format(s);
            }
            default: {
                return "ERROR";
            }
        }
    }
    
    public String getLabel(final Object o) {
        return this.getLabel(o, this.a3);
    }
    
    public String getLabel(final Object o, final String s) {
        if (o == null) {
            return "NULL";
        }
        if (o instanceof NFDate) {
            final NFDate nfDate = (NFDate)o;
            if (s == null || s.length() <= 0) {
                return nfDate.format("M%D\n%h:%m:%s");
            }
            if (this.a1 == 4 && NFUtil.getJDKVersion() >= 1.1) {
                return NF11Util.formatDate(nfDate, s);
            }
            return nfDate.format(s);
        }
        else {
            if (o instanceof NFTimeUnit) {
                return ((NFTimeUnit)o).toString();
            }
            if (o instanceof Number) {
                return this.getLabel(((Number)o).doubleValue(), s);
            }
            return "ERROR";
        }
    }
    
    public double reasonableStep(final double n, final double n2) {
        int n3;
        if (this.i == 0) {
            n3 = ((this.j < 0) ? (-this.j / this.b1) : (this.j / this.b1));
        }
        else if (this.j == 0) {
            n3 = ((this.i < 0) ? (-this.i / this.b1) : (this.i / this.b1));
        }
        else {
            n3 = (int)Math.sqrt(this.i * this.i + this.j * this.j) / this.b1;
        }
        if (n3 < 1) {
            n3 = 1;
        }
        double n4;
        double n5;
        for (n4 = (n2 - n) / n3, n5 = 1.0; n4 > 100.0; n4 /= 10.0, n5 *= 10.0) {}
        while (n4 != 0.0 && n4 < 1.0) {
            n4 *= 10.0;
            n5 /= 10.0;
        }
        int n6 = 0;
        switch (this.a1) {
            default: {
                if (n4 < 2.0) {
                    n6 = 1;
                    break;
                }
                if (n4 < 3.0) {
                    n6 = 2;
                    break;
                }
                if (n4 < 7.5) {
                    n6 = 5;
                    break;
                }
                if (n4 < 17.5) {
                    n6 = 10;
                    break;
                }
                if (n4 < 22.5) {
                    n6 = 20;
                    break;
                }
                if (n4 < 35.0) {
                    n6 = 25;
                    break;
                }
                if (n4 < 75.0) {
                    n6 = 50;
                    break;
                }
                n6 = 100;
                break;
            }
            case 3:
            case 4: {
                double n7;
                if (this.a7 != null && this.a7 instanceof NFTimeUnit) {
                    n7 = ((NFTimeUnit)this.a7).getSeconds();
                }
                else {
                    n7 = this.a9.getSeconds();
                }
                if (n7 < 3600.0) {
                    if (n4 < 2.0) {
                        n6 = 1;
                        break;
                    }
                    if (n4 < 3.0) {
                        n6 = 2;
                        break;
                    }
                    if (n4 < 5.0) {
                        n6 = 4;
                        break;
                    }
                    if (n4 < 9.0) {
                        n6 = 5;
                        break;
                    }
                    if (n4 < 12.5) {
                        n6 = 10;
                        break;
                    }
                    if (n4 < 22.5) {
                        n6 = 15;
                        break;
                    }
                    if (n4 < 45.0) {
                        n6 = 30;
                        break;
                    }
                    n6 = 60;
                    break;
                }
                else if (n7 < 86400.0) {
                    if (n4 < 3.0) {
                        n6 = 2;
                        break;
                    }
                    if (n4 < 5.0) {
                        n6 = 4;
                        break;
                    }
                    if (n4 < 9.0) {
                        n6 = 6;
                        break;
                    }
                    if (n4 < 12.0) {
                        n6 = 8;
                        break;
                    }
                    if (n4 < 24.0) {
                        n6 = 12;
                        break;
                    }
                    if (n4 < 36.0) {
                        n6 = 24;
                        break;
                    }
                    n6 = 48;
                    break;
                }
                else if (n7 < 2600000.0) {
                    if (n4 < 1.0) {
                        n6 = 1;
                        break;
                    }
                    if (n4 < 4.0) {
                        n6 = (int)n4;
                        break;
                    }
                    if (n4 < 10.0) {
                        n6 = 7;
                        break;
                    }
                    if (n4 < 22.0) {
                        n6 = 14;
                        break;
                    }
                    if (n4 < 43.0) {
                        n6 = 28;
                        break;
                    }
                    n6 = 56;
                    break;
                }
                else {
                    if (n4 < 2.0) {
                        n6 = 1;
                        break;
                    }
                    if (n4 < 4.0) {
                        n6 = 3;
                        break;
                    }
                    if (n4 < 7.0) {
                        n6 = 6;
                        break;
                    }
                    if (n4 < 13.0) {
                        n6 = 12;
                        break;
                    }
                    n6 = (int)n4;
                    break;
                }
                break;
            }
        }
        return n6 * n5;
    }
    
    public void setup() {
        this.a(this.w, this.y);
    }
    
    private void a(final double n, final double n2) {
        this.adjustScaleSet();
        if (!this.bk) {
            if (this.d != null) {
                this.c = this.d;
            }
            else if (this.getScaleMode() == 1 || this.getScaleSetSize() > 1) {
                this.c = this.getScaleSetSpacing();
            }
            else if (this.q > 0.0) {
                this.c = new NFSpacing(n, n2, this.q);
            }
            else if (this.p > 0) {
                this.c = new NFSpacing(n, n2, this.p);
            }
            else {
                this.c = new NFSpacing(n, n2, this.reasonableStep(n, n2));
            }
        }
        this.m.removeAllElements();
    }
    
    private void a() {
        NFDebug.print("Axis: position      = (" + this.e + "," + this.f + ") to (" + this.g + "," + this.h + ")");
        NFDebug.print("      showAxis      = " + this.t);
        NFDebug.print("      showAxisLine  = " + this.u);
        NFDebug.print("      showTics      = " + this.r);
        NFDebug.print("      showTicLabels = " + this.s);
        NFDebug.print("      ticColor      = " + this.ae);
        NFDebug.print("      sliderOn      = " + this.at);
    }
    
    public void display(final Graphics graphics) {
        if (this.at && this.ay && !this.az) {
            this.a(graphics, true, null);
            this.ay = false;
            return;
        }
        this.e();
        if (!this.t) {
            return;
        }
        if (this.at) {
            this.b();
        }
        this.az = false;
        this.ay = false;
        final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        final Dimension dimension = new Dimension(0, 0);
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        rectangle.x = this.getXMin();
        rectangle.width = this.getXMax() - this.getXMin();
        rectangle.y = this.getYMin();
        rectangle.height = this.getYMax() - this.getYMin();
        this.a(graphics, this.u, rectangle);
        if (!this.u) {
            graphics.setColor(this.ae);
        }
        this.c(graphics, this.s, rectangle, dimension, nfRegionBorder);
        this.b(graphics, this.r, rectangle, dimension, nfRegionBorder);
        this.a(graphics, this.ag != null, rectangle, dimension, nfRegionBorder);
    }
    
    private void b() {
        final Rectangle aw = this.aw;
        int n = this.getXMax() - this.getXMin() + 1;
        if (n < 0) {
            n = 0;
        }
        int n2 = this.getYMax() - this.getYMin() + 1;
        if (n2 < 0) {
            n2 = 0;
        }
        switch (this.ah) {
            case 1: {
                aw.x = this.getXMin();
                aw.y = this.getYMin() - this.as + 1;
                aw.width = n;
                aw.height = this.as;
                break;
            }
            case 4: {
                aw.x = this.getXMin();
                aw.y = this.getYMin();
                aw.width = n;
                aw.height = this.as;
                break;
            }
            case 2: {
                aw.x = this.getXMin() - this.as + 1;
                aw.y = this.getYMin();
                aw.width = this.as;
                aw.height = n2;
                break;
            }
            case 3: {
                aw.x = this.getXMin();
                aw.y = this.getYMin();
                aw.width = this.as;
                aw.height = n2;
                break;
            }
        }
        this.c();
    }
    
    private void c() {
        final Rectangle aw = this.aw;
        final Rectangle ax = this.ax;
        double percentage = this.getPercentage(this.w, this.au, true);
        if (percentage < 0.0) {
            percentage = 0.0;
        }
        double n = this.getPercentage(this.y, this.au, true) - percentage;
        if (n > 1.0) {
            n = 1.0;
        }
        int n2 = 0;
        switch (this.ah) {
            case 1:
            case 4: {
                ax.x = aw.x + (int)NFUtil.rint(aw.width * percentage);
                ax.y = aw.y + 2;
                ax.width = (int)NFUtil.rint(aw.width * n);
                if (this.ar > 0 && ax.width < this.ar) {
                    ax.width = this.ar;
                    n2 = 1;
                }
                ax.height = aw.height - 4;
                ax.x = Math.max(aw.x, ax.x);
                ax.x = Math.min(aw.x + aw.width - 1, ax.x);
                if (ax.x + ax.width <= aw.x + aw.width) {
                    break;
                }
                if (n2 != 0) {
                    final Rectangle rectangle = ax;
                    rectangle.x -= ax.x + ax.width - (aw.x + aw.width);
                    break;
                }
                final Rectangle rectangle2 = ax;
                rectangle2.width -= ax.x + ax.width - (aw.x + aw.width);
                break;
            }
            case 2:
            case 3: {
                ax.width = aw.width - 4;
                ax.height = (int)NFUtil.rint(aw.height * n) - 1;
                if (this.ar > 0 && ax.height < this.ar) {
                    ax.height = this.ar;
                    n2 = 1;
                }
                ax.x = aw.x + 2;
                ax.y = aw.y + aw.height - (int)NFUtil.rint(aw.height * percentage) - ax.height;
                ax.y = Math.max(aw.y, ax.y);
                ax.y = Math.min(aw.y + aw.height - 1, ax.y);
                if (ax.y + ax.height <= aw.y + aw.height) {
                    break;
                }
                if (n2 != 0) {
                    final Rectangle rectangle3 = ax;
                    rectangle3.y -= ax.y + ax.height - (aw.y + aw.height);
                    break;
                }
                final Rectangle rectangle4 = ax;
                rectangle4.height -= ax.y + ax.height - (aw.y + aw.height);
                break;
            }
        }
    }
    
    public boolean isSliderOn() {
        return this.at;
    }
    
    private void a(final Graphics graphics, final boolean b, final Rectangle rectangle) {
        if (!this.at || this.as <= 1) {
            if (b) {
                graphics.setColor(this.ae);
                graphics.drawLine(this.e, this.f, this.g, this.h);
            }
            this.a(rectangle, 1);
            return;
        }
        if (this.ax.width < 1) {
            this.ax.width = 1;
        }
        if (this.ax.height < 1) {
            this.ax.height = 1;
        }
        if (b) {
            graphics.setColor(NFColor.get("xd0d0d0"));
            graphics.fillRect(this.aw.x, this.aw.y, this.aw.width, this.aw.height);
            graphics.setColor(this.ae);
            graphics.drawRect(this.aw.x, this.aw.y, this.aw.width - 1, this.aw.height - 1);
            graphics.drawLine(this.e, this.f, this.g, this.h);
            graphics.fill3DRect(this.ax.x, this.ax.y, this.ax.width, this.ax.height, true);
        }
        this.a(rectangle, this.as);
    }
    
    public NFSpacing copyPoints(final int n, final int n2) {
        final NFSpacing nfSpacing = new NFSpacing(this.c.size());
        for (int i = 0; i < nfSpacing.size(); ++i) {
            final Point nthPoint;
            final Point point = nthPoint = this.getNthPoint(i);
            nthPoint.x += n;
            final Point point2 = point;
            point2.y += n2;
            nfSpacing.setNthPoint(i, point);
        }
        return nfSpacing;
    }
    
    public boolean mouseDown(final Event event, final int b7, final int b8) {
        this.b6 = 0;
        if (!this.at || !this.aw.inside(b7, b8)) {
            return false;
        }
        if (!this.ax.inside(b7, b8)) {
            this.b6 = 2;
            return true;
        }
        if (event.metaDown() || event.shiftDown() || event.controlDown()) {
            if (event.shiftDown()) {
                this.b6 = 4;
            }
            else {
                this.b6 = 3;
            }
            return true;
        }
        switch (this.ah) {
            case 1:
            case 4: {
                if (this.ax.x <= this.aw.x && this.ax.x + this.ax.width >= this.aw.x + this.aw.width) {
                    return true;
                }
                break;
            }
            case 2:
            case 3: {
                if (this.ax.y <= this.aw.y && this.ax.y + this.ax.height >= this.aw.y + this.aw.height) {
                    return true;
                }
                break;
            }
        }
        this.b6 = 1;
        this.b7 = b7;
        this.b8 = b8;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int b7, final int b8) {
        if (this.b6 != 1) {
            return false;
        }
        switch (this.ah) {
            case 1:
            case 4: {
                final Rectangle ax = this.ax;
                ax.x += b7 - this.b7;
                if (this.ax.x < this.aw.x) {
                    this.ax.x = this.aw.x;
                }
                if (this.ax.x + this.ax.width > this.aw.x + this.aw.width) {
                    this.ax.x = this.aw.x + this.aw.width - this.ax.width;
                    break;
                }
                break;
            }
            case 2:
            case 3: {
                final Rectangle ax2 = this.ax;
                ax2.y += b8 - this.b8;
                if (this.ax.y < this.aw.y) {
                    this.ax.y = this.aw.y;
                }
                if (this.ax.y + this.ax.height > this.aw.y + this.aw.height) {
                    this.ax.y = this.aw.y + this.aw.height - this.ax.height;
                    break;
                }
                break;
            }
        }
        this.b7 = b7;
        this.b8 = b8;
        this.ay = true;
        this.az = false;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.b6 == 0) {
            return false;
        }
        switch (this.b6) {
            case 1: {
                double n3 = this.w;
                switch (this.ah) {
                    case 1:
                    case 4: {
                        n3 = this.unMapValue(this.ax.x, true);
                        break;
                    }
                    case 2:
                    case 3: {
                        n3 = this.unMapValue(this.ax.y + this.ax.height, true);
                        break;
                    }
                }
                this.scrollTo(n3);
                break;
            }
            case 2: {
                switch (this.ah) {
                    case 1:
                    case 4: {
                        if (n < this.ax.x) {
                            this.scroll(1006);
                            break;
                        }
                        this.scroll(1007);
                        break;
                    }
                    case 2:
                    case 3: {
                        if (n2 < this.ax.y) {
                            this.scroll(1004);
                            break;
                        }
                        this.scroll(1005);
                        break;
                    }
                }
                break;
            }
            case 3: {
                this.zoomOut();
                break;
            }
            case 4: {
                this.zoomIn();
                break;
            }
        }
        this.ay = false;
        this.az = true;
        this.autoscale = false;
        this.b6 = 0;
        return true;
    }
    
    public boolean scroll(final int n) {
        if (!this.at) {
            return false;
        }
        switch (n) {
            case 1006:
            case 1007: {
                if (this.ah != 1 && this.ah != 4) {
                    return false;
                }
                break;
            }
            case 1004:
            case 1005: {
                if (this.ah != 2 && this.ah != 3) {
                    return false;
                }
                break;
            }
            default: {
                NFDebug.print("NFAxis: Unknown direction = " + n);
                return false;
            }
        }
        final double d = this.d();
        switch (n) {
            case 1005:
            case 1006: {
                return this.scrollTo(this.w - d);
            }
            case 1004:
            case 1007: {
                return this.scrollTo(this.w + d);
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean scrollTo(double au) {
        this.clearZoomHistory();
        final double n = this.y - this.w;
        double n2 = au + n;
        if (this.abortPreScroll(au, n2)) {
            return false;
        }
        if (this.d == null) {
            final double d = this.d();
            au = NFUtil.rint(au / d) * d;
            n2 = NFUtil.rint(n2 / d) * d;
        }
        if (au < this.au) {
            au = this.au;
            n2 = au + n;
        }
        if (n2 > this.au + this.av) {
            n2 = this.au + this.av;
            au = n2 - n;
        }
        this.a(au, n2, false);
        this.notifyPostScroll();
        return true;
    }
    
    private void b(final double n, final double n2) {
        this.a(n, n2, true);
    }
    
    private void a(final double w, final double y, final boolean b) {
        this.w = w;
        this.y = y;
        this.ay = false;
        this.az = true;
        this.autoscale = false;
        if (b && this.q > 0.0) {
            this.q = this.reasonableStep(this.w, this.y);
        }
        this.setup();
    }
    
    private boolean c(double n, double min) {
        if (this.abortPreScroll(n, min)) {
            return false;
        }
        if (this.d == null) {
            final double d = this.d();
            n = NFUtil.rint(n / d) * d;
            min = NFUtil.rint(min / d) * d;
            if (n == min) {
                min = Math.min(n + d, this.au + this.av);
            }
            if (n == min) {
                n = Math.max(min - d, this.au);
            }
        }
        if (n < this.au) {
            n = this.au;
        }
        if (min > this.au + this.av) {
            min = this.au + this.av;
        }
        this.b(n, min);
        this.notifyPostScroll();
        return true;
    }
    
    public boolean zoomOut() {
        if (!this.at) {
            return false;
        }
        final double n = (this.y - this.w) / 2.0;
        if (this.getZoomInHistorySize() > 0) {
            final double[] popZoomIn = this.popZoomIn();
            this.setTicDivisions(popZoomIn[0], popZoomIn[1], popZoomIn[2]);
            return true;
        }
        this.pushZoomOut();
        return this.c(this.w - n, this.y + n);
    }
    
    public boolean zoomIn() {
        if (!this.at) {
            return false;
        }
        final double n = (this.y - this.w) / 4.0;
        if (this.getZoomInHistorySize() == 0 && this.getZoomOutHistorySize() > 0) {
            final double[] popZoomOut = this.popZoomOut();
            this.setTicDivisions(popZoomOut[0], popZoomOut[1], popZoomOut[2]);
            return true;
        }
        this.pushZoomIn();
        return this.c(this.w + n, this.y - n);
    }
    
    public boolean zoomHome() {
        if (!this.at) {
            return false;
        }
        this.clearZoomHistory();
        return this.c(this.au, this.au + this.av);
    }
    
    private double d() {
        try {
            double q = this.getNthValue(1) - this.getNthValue(0);
            if (q == 0.0) {
                q = this.q;
            }
            if (q == 0.0) {
                q = 1.0;
            }
            return q;
        }
        catch (Throwable t) {
            return 1.0;
        }
    }
    
    protected static String removeModifierFromName(final String s) {
        return s.substring(0, getParameterModifierIndexForType(s) + 1);
    }
    
    protected static String getModifierOfName(final String s) {
        return s.substring(getParameterModifierIndexForType(s) + 1, s.length());
    }
    
    protected static int getParameterModifierIndexForType(final String s) {
        final int n = s.length() - 1;
        int n2;
        for (n2 = s.length() - 1; Character.isDigit(s.charAt(n2)) && n2 >= 0; --n2) {}
        return n2;
    }
    
    public static void defineAxis(final NFParam nfParam, final String s, final String s2) {
        defineAxis(nfParam, s, s2, false);
    }
    
    public static void defineAxis(final NFParam nfParam, String removeModifierFromName, final String s, final boolean b) {
        final Vector vector = new Vector<NFParamDef>();
        final String modifierOfName = getModifierOfName(removeModifierFromName);
        removeModifierFromName = removeModifierFromName(removeModifierFromName);
        final String string = removeModifierFromName + "Axis" + modifierOfName;
        final String string2 = removeModifierFromName + "Labels" + modifierOfName;
        final String string3 = removeModifierFromName + "Format" + modifierOfName;
        final String string4 = removeModifierFromName + "Color" + modifierOfName;
        final String string5 = removeModifierFromName + "Tics" + modifierOfName;
        final String string6 = removeModifierFromName + "Scale" + modifierOfName;
        final String string7 = removeModifierFromName + "Scroll" + modifierOfName;
        final String string8 = removeModifierFromName + "ActiveLabels" + modifierOfName;
        final String string9 = removeModifierFromName + "TicLayout" + modifierOfName;
        final String string10 = removeModifierFromName + "AxisTitle" + modifierOfName;
        final String string11 = removeModifierFromName + "AxisTitleBox" + modifierOfName;
        final String string12 = removeModifierFromName + "AxisTitleActiveLabel" + modifierOfName;
        final String string13 = removeModifierFromName + "ScaleMode" + modifierOfName;
        final String string14 = removeModifierFromName + "TicLocations" + modifierOfName;
        final String string15 = removeModifierFromName + "ScaleSet" + modifierOfName;
        final String string16 = removeModifierFromName + "DrawMinorTics" + modifierOfName;
        final String string17 = removeModifierFromName + "MinimumThumbSize" + modifierOfName;
        final String string18 = removeModifierFromName + "Margins" + modifierOfName;
        if (b) {
            nfParam.defineAlias(string, removeModifierFromName(string));
            nfParam.defineAlias(string2, removeModifierFromName(string2));
            nfParam.defineAlias(string3, removeModifierFromName(string3));
            nfParam.defineAlias(string4, removeModifierFromName(string4));
            nfParam.defineAlias(string5, removeModifierFromName(string5));
            nfParam.defineAlias(string6, removeModifierFromName(string6));
            nfParam.defineAlias(string7, removeModifierFromName(string7));
            nfParam.defineAlias(string8, removeModifierFromName(string8));
            nfParam.defineAlias(string9, removeModifierFromName(string9));
            nfParam.defineAlias(string10, removeModifierFromName(string10));
            nfParam.defineAlias(string11, removeModifierFromName(string11));
            nfParam.defineAlias(string12, removeModifierFromName(string12));
            nfParam.defineAlias(string13, removeModifierFromName(string13));
            nfParam.defineAlias(string14, removeModifierFromName(string14));
            nfParam.defineAlias(string15, removeModifierFromName(string15));
            nfParam.defineAlias(string16, removeModifierFromName(string16));
            nfParam.defineAlias(string17, removeModifierFromName(string17));
            nfParam.defineAlias(string18, removeModifierFromName(string18));
            return;
        }
        NFAxis.b9 = nfParam.defineLabel(removeModifierFromName + "Axis" + modifierOfName, vector, false);
        nfParam.remove(removeModifierFromName + "Axis" + modifierOfName + "Label");
        vector.removeElementAt(0);
        vector.addElement(nfParam.defineDate(removeModifierFromName + s + "Min" + modifierOfName, null));
        vector.addElement(nfParam.defineDate(removeModifierFromName + s + "Max" + modifierOfName, null));
        vector.addElement(nfParam.defineDate(removeModifierFromName + s + "Step" + modifierOfName, null));
        vector.addElement(nfParam.defineColor(removeModifierFromName + "AxisColor" + modifierOfName, Color.black));
        vector.addElement(nfParam.defineDate(removeModifierFromName + "AxisScrollMin" + modifierOfName, null));
        vector.addElement(nfParam.defineDate(removeModifierFromName + "AxisScrollMax" + modifierOfName, null));
        nfParam.defineTuple(string, vector);
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("FLOAT", new Integer(2));
        hashtable.put("INTEGER", new Integer(1));
        hashtable.put("DATE", new Integer(3));
        hashtable.put("SIMPLEDATE", new Integer(4));
        hashtable.put("DECIMAL", new Integer(5));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(nfParam.defineSymbol(removeModifierFromName + "FormatType" + modifierOfName, hashtable, new Integer(2)));
        vector2.addElement(nfParam.defineString(removeModifierFromName + "FormatStr" + modifierOfName));
        vector2.addElement(nfParam.defineDate(removeModifierFromName + "FormatBase" + modifierOfName));
        vector2.addElement(nfParam.defineDate(removeModifierFromName + "FormatUnit" + modifierOfName));
        nfParam.defineTuple(string3, vector2);
        nfParam.defineVector(string2, nfParam.defineString("Label"));
        nfParam.defineColor(string4, null);
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        final int defineLabel = nfParam.defineLabel(removeModifierFromName + "Tics" + modifierOfName, vector3);
        vector3.addElement(nfParam.defineColor(removeModifierFromName + "BGColor" + modifierOfName, null));
        for (int i = 0; i < defineLabel; ++i) {
            vector3.elementAt(i).val = null;
        }
        nfParam.defineTuple(string5, vector3);
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(nfParam.defineDate(removeModifierFromName + "ScaleMin" + modifierOfName, null));
        vector4.addElement(nfParam.defineDate(removeModifierFromName + "ScaleMax" + modifierOfName, null));
        vector4.addElement(nfParam.defineDate(removeModifierFromName + "ScaleStep" + modifierOfName, null));
        nfParam.defineTuple(string6, vector4);
        final Vector<NFParamDef> vector5 = new Vector<NFParamDef>();
        vector5.addElement(nfParam.defineDate(removeModifierFromName + "ScrollMin" + modifierOfName, null));
        vector5.addElement(nfParam.defineDate(removeModifierFromName + "ScrollMax" + modifierOfName, null));
        nfParam.defineTuple(string7, vector5);
        nfParam.defineActiveLabel(string8);
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("NORMAL", new Integer(1));
        hashtable2.put("SKIP", new Integer(2));
        hashtable2.put("STAGGER", new Integer(3));
        hashtable2.put("SKIPSTAGGER", new Integer(4));
        hashtable2.put("AUTOSKIP", new Integer(5));
        hashtable2.put("AUTOSTAGGER", new Integer(6));
        hashtable2.put("AUTO", new Integer(7));
        final Vector<NFParamDef> vector6 = new Vector<NFParamDef>();
        vector6.addElement(nfParam.defineSymbol(removeModifierFromName + "LayoutMode" + modifierOfName, hashtable2, new Integer(1)));
        vector6.addElement(nfParam.defineNumber(removeModifierFromName + "SkipCount" + modifierOfName, new Integer(0)));
        vector6.addElement(nfParam.defineNumber(removeModifierFromName + "Stagger" + modifierOfName, new Integer(0)));
        nfParam.defineTuple(string9, vector6);
        nfParam.defineString(string16, NFAxis.ao ? "ON" : "OFF");
        nfParam.defineNumber(string17, new Integer(NFAxis.aq));
        nfParam.defineLabel(string10, true, true);
        nfParam.defineRegion(string11);
        nfParam.defineActiveLabel(string12);
        final Hashtable<String, Integer> hashtable3 = new Hashtable<String, Integer>();
        hashtable3.put("LINEAR", new Integer(0));
        hashtable3.put("LOG", new Integer(1));
        final Vector<NFParamDef> vector7 = new Vector<NFParamDef>();
        vector7.addElement(nfParam.defineSymbol(removeModifierFromName + "ScaleModeType" + modifierOfName, hashtable3, new Integer(0)));
        vector7.addElement(nfParam.defineNumber(removeModifierFromName + "ScaleModeBase" + modifierOfName, new Double(2.718281828459045)));
        nfParam.defineTuple(string13, vector7);
        nfParam.defineVector(string14, nfParam.defineDate(removeModifierFromName + "TicLocationElement" + modifierOfName, null));
        final Vector<NFParamDef> vector8 = new Vector<NFParamDef>();
        vector8.addElement(nfParam.defineDate(removeModifierFromName + "ScaleSetMin" + modifierOfName, null));
        vector8.addElement(nfParam.defineDate(removeModifierFromName + "ScaleSetMax" + modifierOfName, null));
        vector8.addElement(nfParam.defineDate(removeModifierFromName + "ScaleSetStep" + modifierOfName, null));
        vector8.addElement(nfParam.defineNumber(removeModifierFromName + "ScaleSetPercent" + modifierOfName, null));
        nfParam.defineVector(string15, nfParam.defineTuple(removeModifierFromName + "ScaleSetTuple" + modifierOfName, vector8));
        final Vector<NFParamDef> vector9 = new Vector<NFParamDef>();
        vector9.addElement(nfParam.defineNumber(removeModifierFromName + "BeginMargin" + modifierOfName, null));
        vector9.addElement(nfParam.defineNumber(removeModifierFromName + "EndMargin" + modifierOfName, null));
        nfParam.defineTuple(string18, vector9);
    }
    
    public static NFAxis loadAxisFormat(final NFParam nfParam, final String s, NFAxis defaultAxis) throws Exception {
        final Vector vector = (Vector)nfParam.get(removeModifierFromName(s) + "Format" + getModifierOfName(s));
        if (defaultAxis == null) {
            defaultAxis = defaultAxis(s);
        }
        defaultAxis.a1 = NFUtil.getNumber(vector, 0, NFAxis.a0);
        defaultAxis.a3 = NFUtil.getString(vector, 1, NFAxis.a2);
        defaultAxis.a5 = NFUtil.getObject(vector, 2, NFAxis.a4);
        defaultAxis.a7 = NFUtil.getObject(vector, 3, NFAxis.a6);
        defaultAxis.setType(new String(s));
        return defaultAxis;
    }
    
    public void loadAxisTics(final Vector vector) {
        this.loadAxisTics(vector, 0.0);
    }
    
    public void loadAxisTics(final Vector vector, final double scale) {
        final boolean b = !NFUtil.getString(vector, 0, "OFF").equals("OFF");
        this.showTicsAndLabels(b, b);
        this.showAxisLine(b);
        this.bb = NFUtil.getColor(vector, NFParam.labelParamCount(), NFAxis.ba);
        this.getTicLabel().loadParams(vector);
        this.setScale(scale);
        this.ac = 0;
    }
    
    public void setScale(final double n) {
        this.bg = n;
        if (this.af != null) {
            this.af.setScale(n);
        }
        this.ac = 0;
    }
    
    public static NFAxis loadAxis(final NFParam nfParam, final String s, final NFAxis nfAxis, final NFActiveRegion nfActiveRegion) throws Exception {
        return loadAxis(null, nfParam, s, nfAxis, nfActiveRegion);
    }
    
    public static NFAxis loadAxis(final Component component, final NFParam nfParam, final String s, NFAxis nfAxis, final NFActiveRegion nfActiveRegion) throws Exception {
        final String modifierOfName = getModifierOfName(s);
        final String removeModifierFromName = removeModifierFromName(s);
        final String string = removeModifierFromName + "Axis" + modifierOfName;
        final String string2 = removeModifierFromName + "Labels" + modifierOfName;
        final String string3 = removeModifierFromName + "Format" + modifierOfName;
        final String string4 = removeModifierFromName + "Color" + modifierOfName;
        final String string5 = removeModifierFromName + "Tics" + modifierOfName;
        final String string6 = removeModifierFromName + "Scale" + modifierOfName;
        final String string7 = removeModifierFromName + "Scroll" + modifierOfName;
        final String string8 = removeModifierFromName + "ActiveLabels" + modifierOfName;
        final String string9 = removeModifierFromName + "TicLayout" + modifierOfName;
        final String string10 = removeModifierFromName + "AxisTitle" + modifierOfName;
        final String string11 = removeModifierFromName + "AxisTitleBox" + modifierOfName;
        final String string12 = removeModifierFromName + "AxisTitleActiveLabel" + modifierOfName;
        final String string13 = removeModifierFromName + "ScaleMode" + modifierOfName;
        final String string14 = removeModifierFromName + "TicLocations" + modifierOfName;
        final String string15 = removeModifierFromName + "ScaleSet" + modifierOfName;
        final String string16 = removeModifierFromName + "DrawMinorTics" + modifierOfName;
        final String string17 = removeModifierFromName + "MinimumThumbSize" + modifierOfName;
        final String string18 = removeModifierFromName + "Margins" + modifierOfName;
        boolean b = false;
        if (nfParam.changed(string3)) {
            b = true;
            nfAxis = loadAxisFormat(nfParam, s, nfAxis);
            if (nfAxis != null) {
                b = true;
            }
        }
        if (nfParam.changed(string)) {
            final Vector vector = (Vector)nfParam.get(string);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            if (vector == null || vector.size() == 0) {
                nfAxis.showTicsAndLabels(false, false);
                nfAxis.showAxisLine(false);
            }
            vector.insertElementAt("", 0);
            NFLabel loadParams = NFLabel.loadParams(nfParam, vector, false);
            if (loadParams == null) {
                loadParams = new NFLabel();
            }
            loadParams.setComponent(component);
            nfAxis.setTicLabel(loadParams);
            final Object object = NFUtil.getObject(vector, NFAxis.b9, null);
            final Object object2 = NFUtil.getObject(vector, NFAxis.b9 + 1, null);
            final Object object3 = NFUtil.getObject(vector, NFAxis.b9 + 2, null);
            final Color color = NFUtil.getColor(vector, NFAxis.b9 + 3, NFAxis.ad);
            final Object object4 = NFUtil.getObject(vector, NFAxis.b9 + 4, null);
            final Object object5 = NFUtil.getObject(vector, NFAxis.b9 + 5, null);
            vector.removeElementAt(0);
            nfAxis.a(object, object2, object3);
            nfAxis.setAxisColor(color);
            nfAxis.a(object4, object5);
        }
        if (nfParam.changed(string4)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.setAxisColor(NFUtil.getColor(nfParam.get(string4), NFAxis.ad));
        }
        if (nfParam.changed(string18)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            final Vector vector2 = (Vector)nfParam.get(string18);
            nfAxis.beginMargin = NFUtil.getNumber(vector2, 0, 0);
            if (nfAxis.beginMargin < 0) {
                nfAxis.beginMargin = 0;
            }
            nfAxis.endMargin = NFUtil.getNumber(vector2, 1, 0);
            if (nfAxis.endMargin < 0) {
                nfAxis.endMargin = 0;
            }
        }
        if (nfParam.changed(string5)) {
            final Vector vector3 = (Vector)nfParam.get(string5);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.loadAxisTics(vector3, nfParam.getChartScale());
            nfAxis.af.setComponent(component);
        }
        if (nfParam.changed(string6)) {
            final Vector vector4 = (Vector)nfParam.get(string6);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.a(NFUtil.getObject(vector4, 0, null), NFUtil.getObject(vector4, 1, null), NFUtil.getObject(vector4, 2, null));
        }
        if (nfParam.changed(string7)) {
            final Vector vector5 = (Vector)nfParam.get(string7);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.a(NFUtil.getObject(vector5, 0, null), NFUtil.getObject(vector5, 1, null));
        }
        if (nfParam.changed(string2)) {
            final Vector vector6 = (Vector)nfParam.get(string2);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            if (vector6 != null && vector6.size() > 0) {
                nfAxis.setTicLabels(vector6, string2);
                nfAxis.autoscale = false;
            }
            else {
                nfAxis.clearTicLabels();
                nfAxis.setSpacing(null);
            }
        }
        if (nfParam.changed(string8) && nfActiveRegion != null && nfAxis != null) {
            if (nfAxis.n == null) {
                nfAxis.n = new Vector();
            }
            else {
                if (nfAxis.bc != null) {
                    nfAxis.bc.removeLabel(nfAxis.n);
                }
                nfAxis.n.removeAllElements();
            }
            nfAxis.bc = nfActiveRegion;
            nfAxis.setActiveLabels(NFActiveLabel.loadAllParams(nfParam, string8));
        }
        else if (nfAxis != null && nfAxis.bc == null && nfActiveRegion != null) {
            nfAxis.bc = nfActiveRegion;
        }
        if (nfParam.changed(string9)) {
            final Vector vector7 = (Vector)nfParam.get(string9);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.setTicLayout(NFUtil.getNumber(vector7, 0, NFAxis.am), NFUtil.getNumber(vector7, 1, NFAxis.ak), NFUtil.getNumber(vector7, 2, NFAxis.bz));
        }
        if (nfParam.changed(string16)) {
            final String s2 = (String)nfParam.get(string16);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.ap = ((s2 == null) ? NFAxis.ao : s2.equalsIgnoreCase("ON"));
        }
        if (nfParam.changed(string17)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.ar = NFUtil.getNumber(nfParam.get(string17), NFAxis.aq);
        }
        if (nfParam.changed(string10)) {
            final Vector vector8 = (Vector)nfParam.get(string10);
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
                nfAxis.showTics(false);
                nfAxis.showTicLabels(false);
                nfAxis.showAxisLine(false);
            }
            NFLabel titleLabel = nfAxis.getTitleLabel();
            if (titleLabel == null) {
                titleLabel = NFLabel.loadParams(nfParam, vector8, 0, true, true);
                if (titleLabel != null) {
                    titleLabel.setComponent(component);
                }
            }
            else if (vector8 == null || vector8.size() == 0) {
                titleLabel = null;
            }
            else {
                NFLabel.loadParams(titleLabel, vector8, 0, true, true);
            }
            nfAxis.setTitleLabel(titleLabel);
        }
        if (nfParam.changed(string13)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            final Vector vector9 = (Vector)nfParam.get(string13);
            final int number = NFUtil.getNumber(vector9, 0, NFAxis.bl);
            nfAxis.setScaleModeBase(NFUtil.getNumber(vector9, 1, NFAxis.bn));
            nfAxis.setScaleMode(number);
        }
        if (nfParam.changed(string14)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.setTicLocations((Vector)nfParam.get(string14));
        }
        if (nfParam.changed(string15) && (nfAxis == null || nfAxis.getScaleMode() != 1)) {
            b = true;
            if (nfAxis == null) {
                nfAxis = defaultAxis(s);
            }
            nfAxis.loadScaleSet((Vector)nfParam.get(string15));
        }
        if (nfAxis != null && nfAxis.ag != null && nfParam.changed(string11)) {
            b = true;
            nfAxis.ag.setRegion(NFRegion.loadParams(nfParam, nfParam.get(string11)));
        }
        if (nfAxis != null && nfAxis.ag != null && nfParam.changed(string12)) {
            final NFActiveLabel activeLabel = nfAxis.ag.getActiveLabel();
            if (activeLabel != null && nfActiveRegion != null) {
                nfActiveRegion.removeLabel(activeLabel);
            }
            final Vector loadAllParams = NFActiveLabel.loadAllParams(nfParam, string12);
            NFActiveLabel activeLabel2;
            if (loadAllParams != null && loadAllParams.size() > 0) {
                activeLabel2 = loadAllParams.elementAt(0);
            }
            else {
                activeLabel2 = new NFActiveLabel();
            }
            if (nfActiveRegion != null) {
                nfActiveRegion.addLabel(activeLabel2);
            }
            activeLabel2.selectedItemParam = new String(string10);
            activeLabel2.selectedItemIndex = 0;
            nfAxis.ag.setActiveLabel(activeLabel2);
        }
        if (nfAxis != null) {
            nfAxis.setType(new String(s));
        }
        if (b) {
            return nfAxis;
        }
        return null;
    }
    
    public void setDefaultTicDivisions() {
        this.setTicDivisions(NFAxis.v, NFAxis.x, NFAxis.o);
    }
    
    public void setScaleSpecified(final boolean scaleSpecified) {
        this.scaleSpecified = scaleSpecified;
    }
    
    public boolean isScaleSpecified() {
        return this.scaleSpecified;
    }
    
    protected static Object getValidNumber(Object o) {
        if (o != null && o instanceof Double && ((Double)o).isNaN()) {
            o = null;
        }
        return o;
    }
    
    public Object getDefinedMin() {
        return this.z;
    }
    
    public Object getDefinedMax() {
        return this.aa;
    }
    
    public Object getDefinedStep() {
        return this.ab;
    }
    
    private void a(final Object o, final Object o2, final Object o3) {
        this.clearZoomHistory();
        this.emptyScaleSets();
        this.z = getValidNumber(o);
        this.aa = getValidNumber(o2);
        this.ab = getValidNumber(o3);
        if (o == null || o2 == null || (o instanceof Double && ((Double)o).isNaN()) || (o2 instanceof Double && ((Double)o2).isNaN()) || this.getValue(o) == this.getValue(o2)) {
            if (o == null || o2 == null) {
                this.setDefaultTicDivisions();
            }
            else {
                this.p = 0;
            }
            this.autoscale = true;
            this.setScaleSpecified(false);
            return;
        }
        this.autoscale = false;
        this.setScaleSpecified(true);
        double value = this.getValue(o);
        double value2 = this.getValue(o2);
        if (value > value2) {
            final double n = value2;
            value2 = value;
            value = n;
        }
        double value3 = this.getValue(o3);
        if (value3 < 0.0) {
            value3 = 0.0;
        }
        if (value > 0.0 && this.bm == 1) {
            this.buildLogScaleSet(value, value2, this.getScaleModeBase());
        }
        this.setTicDivisions(value, value2, value3);
    }
    
    private void a(final Object o, final Object o2) {
        if (o == null || o2 == null || (o instanceof Double && ((Double)o).isNaN()) || (o2 instanceof Double && ((Double)o2).isNaN())) {
            this.setScroll(this.autoscroll = false);
        }
        else {
            final double value = this.getValue(o);
            final double value2 = this.getValue(o2);
            this.setScroll(true);
            if (value != value2) {
                this.autoscroll = false;
                this.setScrollLimits(this.getValue(o), this.getValue(o2));
            }
            else {
                this.autoscroll = true;
            }
        }
    }
    
    public String toString() {
        return new String("min:" + this.w + "," + "max:" + this.y + "," + "ticStepSize:" + this.q);
    }
    
    private void e() {
        if (this.n == null) {
            return;
        }
        for (int size = this.n.size(), i = 0; i < size; ++i) {
            ((NFActiveLabel)this.n.elementAt(i)).setBounds(-1, -1, 0, 0);
        }
    }
    
    protected boolean autoGeneratedLabel(final int n) {
        if (this.bj && this.l != null) {
            return n >= this.l.size();
        }
        return this.autoscale || this.l == null || this.l.size() == 0;
    }
    
    private void a(final int i, final int n, final int n2, final int n3, final int n4) {
        if (this.n == null || i < 0) {
            return;
        }
        NFActiveLabel nfActiveLabel = null;
        if (i >= this.n.size()) {
            while (i >= this.n.size()) {
                nfActiveLabel = new NFActiveLabel();
                this.n.addElement(nfActiveLabel);
                if (this.bc != null) {
                    this.bc.addLabel(nfActiveLabel);
                }
                nfActiveLabel.selectedItemParam = (this.autoGeneratedLabel(i) ? (removeModifierFromName(this.getType()) + "Axis" + getModifierOfName(this.getType())) : ((this.bi == null) ? (removeModifierFromName(this.getType()) + "Labels" + getModifierOfName(this.getType())) : this.bi));
                nfActiveLabel.selectedItemIndex = this.n.size() - 1;
            }
        }
        else {
            nfActiveLabel = this.n.elementAt(i);
        }
        nfActiveLabel.setBounds(n, n2, n3, n4);
    }
    
    public int getScaleMode() {
        if (this.getMin() <= 0.0) {
            return 0;
        }
        return this.bm;
    }
    
    public void setScaleMode(final int n) {
        switch (n) {
            case 1: {
                this.bm = 1;
                if (this.w > 0.0) {
                    this.buildLogScaleSet(this.w, this.y, this.getScaleModeBase());
                    break;
                }
                break;
            }
            default: {
                this.bm = 0;
                break;
            }
        }
    }
    
    public double getScaleModeBase() {
        return this.bo;
    }
    
    public void setScaleModeBase(final double bo) {
        if (bo > 1.0) {
            this.bo = bo;
        }
    }
    
    public void setActiveLabels(final Vector vector) {
        if (this.n != null) {
            if (this.bc != null) {
                this.bc.removeLabel(this.n);
            }
            this.n.removeAllElements();
        }
        if (vector != null) {
            final Vector<NFActiveLabel> n = new Vector<NFActiveLabel>();
            for (int i = 0; i < vector.size(); ++i) {
                final NFActiveLabel nfActiveLabel = vector.elementAt(i);
                nfActiveLabel.selectedItemParam = (this.autoGeneratedLabel(i) ? (removeModifierFromName(this.getType()) + "Axis" + getModifierOfName(this.getType())) : ((this.bi == null) ? (removeModifierFromName(this.getType()) + "Labels" + getModifierOfName(this.getType())) : this.bi));
                nfActiveLabel.selectedItemIndex = i;
                if (this.bc != null) {
                    this.bc.addLabel(vector, i);
                }
                if (this.reverseActiveLabels) {
                    n.insertElementAt(nfActiveLabel, 0);
                }
                else {
                    n.addElement(nfActiveLabel);
                }
            }
            this.n = n;
        }
    }
    
    public void addObserver(final Object o) {
        if (this.bf == null) {
            this.bf = new Vector();
        }
        this.bf.addElement(o);
    }
    
    public void removeObserver(final Object o) {
        if (this.bf != null) {
            this.bf.removeElement(o);
        }
    }
    
    public boolean abortPreScroll(final double n, final double n2) {
        if (this.bf == null) {
            return false;
        }
        final Enumeration<NFScrollObserver> elements = this.bf.elements();
        while (elements.hasMoreElements()) {
            final NFScrollObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFScrollObserver)) {
                continue;
            }
            if (!nextElement.preScroll(this, this.au, this.au + this.av, this.w, this.y, n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public void notifyPostScroll() {
        if (this.bf == null) {
            return;
        }
        final Enumeration<NFScrollObserver> elements = this.bf.elements();
        while (elements.hasMoreElements()) {
            final NFScrollObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFScrollObserver)) {
                continue;
            }
            nextElement.postScroll(this, this.au, this.au + this.av, this.w, this.y);
        }
    }
    
    public void setNumberFormat(final int groupSize, final char groupSymbol, final char decSymbol) {
        this.groupSize = groupSize;
        this.groupSymbol = groupSymbol;
        this.decSymbol = decSymbol;
        this.m.removeAllElements();
        this.az = true;
    }
    
    public char getGroupSymbol() {
        return this.groupSymbol;
    }
    
    public int getGroupSize() {
        return this.groupSize;
    }
    
    public char getDecimalSymbol() {
        return this.decSymbol;
    }
    
    public int getSkipCount() {
        return this.al;
    }
    
    public int getStaggerLevels() {
        return this.b0;
    }
    
    public boolean getDrawMinorTics() {
        return this.ap;
    }
    
    public int getMinimumThumbSize() {
        return this.ar;
    }
    
    protected int getXMin() {
        return Math.min(this.getX1(), this.getX1() + this.getDX());
    }
    
    protected int getXMax() {
        return Math.max(this.getX1(), this.getX1() + this.getDX());
    }
    
    protected int getYMin() {
        return Math.min(this.getY1(), this.getY1() + this.getDY());
    }
    
    protected int getYMax() {
        return Math.max(this.getY1(), this.getY1() + this.getDY());
    }
    
    protected int getX1() {
        switch (this.ah) {
            case 1:
            case 4: {
                return this.e + this.beginMargin;
            }
            default: {
                return this.e;
            }
        }
    }
    
    protected int getDX() {
        switch (this.ah) {
            case 1:
            case 4: {
                return this.i - this.beginMargin - this.endMargin;
            }
            default: {
                return this.i;
            }
        }
    }
    
    protected int getY1() {
        switch (this.ah) {
            case 2:
            case 3: {
                return this.f - this.beginMargin;
            }
            default: {
                return this.f;
            }
        }
    }
    
    protected int getDY() {
        switch (this.ah) {
            case 2:
            case 3: {
                return this.j + (this.beginMargin + this.endMargin);
            }
            default: {
                return this.j;
            }
        }
    }
    
    static {
        NFAxis.o = 0;
        NFAxis.v = 0.0;
        NFAxis.x = 100.0;
        NFAxis.ad = Color.black;
        NFAxis.ak = 0;
        NFAxis.am = 1;
        NFAxis.ao = true;
        NFAxis.aq = -1;
        NFAxis.a0 = 2;
        NFAxis.a2 = null;
        NFAxis.a4 = null;
        NFAxis.a6 = null;
        NFAxis.ba = null;
        NFAxis.bl = 0;
        NFAxis.bn = 2.718281828459045;
        NFAxis.bz = 0;
    }
}
