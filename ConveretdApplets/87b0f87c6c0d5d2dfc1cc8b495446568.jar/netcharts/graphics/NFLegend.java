// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;
import java.util.Hashtable;
import netcharts.util.NFParamDef;
import netcharts.util.NFParam;
import java.awt.Component;
import netcharts.util.NFColor;
import java.awt.Rectangle;
import java.applet.Applet;
import netcharts.util.NFUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

public class NFLegend extends NFDraggable
{
    private NFLabel a;
    private NFRegion b;
    private NFActiveRegion c;
    private Vector d;
    private Vector e;
    private Vector f;
    private int g;
    private int h;
    private int i;
    private Object j;
    private Object k;
    private Object l;
    private Object m;
    protected static String XAXIS_DEFAULT;
    protected static String YAXIS_DEFAULT;
    private String n;
    private String o;
    private NFAxisMap p;
    private int q;
    private int r;
    private int s;
    private int t;
    private Point u;
    private Dimension v;
    private Dimension w;
    private String x;
    private Vector y;
    private Vector z;
    private int aa;
    private int ab;
    private int ac;
    private int[] ad;
    private int[] ae;
    private int af;
    private int ag;
    private double ah;
    
    public NFLegend() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = new Vector();
        this.g = 1;
        this.h = 0;
        this.i = 4;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = NFLegend.XAXIS_DEFAULT;
        this.o = NFLegend.YAXIS_DEFAULT;
        this.p = null;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = new Point(0, 0);
        this.v = new Dimension(0, 0);
        this.w = new Dimension(0, 0);
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = 5;
        this.ab = 0;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.ah = 0.0;
        super.debugType = 1024L;
        super.debugName = "NFLegend";
    }
    
    public NFLegend(final NFActiveRegion c) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = new Vector();
        this.g = 1;
        this.h = 0;
        this.i = 4;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = NFLegend.XAXIS_DEFAULT;
        this.o = NFLegend.YAXIS_DEFAULT;
        this.p = null;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = new Point(0, 0);
        this.v = new Dimension(0, 0);
        this.w = new Dimension(0, 0);
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = 5;
        this.ab = 0;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.ah = 0.0;
        this.c = c;
        super.debugType = 1024L;
        super.debugName = "NFLegend";
    }
    
    public void setDwell(final NFActiveRegion c) {
        if (this.c != null) {
            this.c.removeLabel(this.y);
        }
        if ((this.c = c) == null) {
            return;
        }
        if (c.getSelectMode()) {
            this.y = c.createSelectLabels(null, this.getItemCount() + 1);
        }
        else {
            this.y = this.z;
            if (this.y != null) {
                c.addLabels(this.y, this.y.size());
            }
        }
        if (this.y == null) {
            this.y = new Vector();
        }
        for (int i = 0; i < this.y.size(); ++i) {
            final NFActiveLabel nfActiveLabel = this.y.elementAt(i);
            nfActiveLabel.selectedItemParam = ((this.d != null && this.d.size() > 0) ? "LegendItems" : "Legend");
            nfActiveLabel.selectedItemIndex = i;
        }
        if (this.y.size() != this.getItemCount()) {
            for (int j = this.y.size(); j < this.getItemCount(); ++j) {
                final NFActiveLabel nfActiveLabel2 = new NFActiveLabel();
                nfActiveLabel2.selectedItemParam = ((this.d != null && this.d.size() > 0) ? "LegendItems" : "Legend");
                nfActiveLabel2.selectedItemIndex = j;
                this.y.addElement(nfActiveLabel2);
                c.addLabel(nfActiveLabel2);
            }
        }
    }
    
    public void setLabel(final NFLabel a) {
        this.a = a;
        if (a != null) {
            a.setScale(this.ah);
        }
    }
    
    public void setRegion(final NFRegion b) {
        this.b = b;
        if (b != null) {
            b.setScale(this.ah);
        }
    }
    
    public void setScale(final double scale) {
        this.ah = scale;
        if (this.a != null) {
            this.a.setScale(scale);
        }
        if (this.b != null) {
            this.b.setScale(scale);
        }
    }
    
    public void setItems(final Vector d) {
        this.d = d;
    }
    
    public void setLabels(final Vector e) {
        this.e = e;
    }
    
    public void setDepth(final int ab) {
        this.ab = ab;
    }
    
    public int getDepth() {
        return this.ab;
    }
    
    public void clearDataSets() {
        this.debug("NFLegend: clearDataSets()");
        this.f.removeAllElements();
    }
    
    public void addDataSet(final NFDataSeries nfDataSeries) {
        this.debug("NFLegend: addDataSet = " + nfDataSeries.name);
        this.f.addElement(nfDataSeries);
        final NFActiveLabel nfActiveLabel = new NFActiveLabel();
        nfActiveLabel.selectedItemParam = ((this.d != null && this.d.size() > 0) ? "LegendItems" : "Legend");
        if (this.y == null) {
            this.y = new Vector();
        }
        this.y.addElement(nfActiveLabel);
        nfActiveLabel.selectedItemIndex = this.y.size() - 1;
        if (this.c != null) {
            this.c.addLabel(nfActiveLabel);
        }
    }
    
    public void removeDataSet(final NFDataSeries nfDataSeries) {
        this.debug("NFLegend: removeDataSet = " + nfDataSeries.name);
        this.f.removeElement(nfDataSeries);
    }
    
    public void setAxisMap(final NFAxisMap p) {
        this.p = p;
    }
    
    public boolean enabled() {
        if (this.a == null || this.a.getLabel() == null || this.a.getLabel().equals("OFF")) {
            this.debug("NFLegend: legend disabled by label");
            return false;
        }
        if (this.d != null && this.d.size() > 0) {
            return true;
        }
        if (this.f != null && this.f.size() > 0) {
            return true;
        }
        this.debug("NFLegend: legend disabled because of no data");
        return false;
    }
    
    public int getItemCount() {
        if (this.d != null && this.d.size() > 0) {
            return this.d.size();
        }
        if (this.f != null) {
            return this.f.size();
        }
        return 0;
    }
    
    public int getLegendIndex(final NFActiveLabel nfActiveLabel) {
        if (this.y == null) {
            return -1;
        }
        for (int i = 0; i < this.y.size(); ++i) {
            if (this.y.elementAt(i) == nfActiveLabel) {
                return i;
            }
        }
        return -1;
    }
    
    public NFActiveLabel getLegendActiveLabel(final int n) {
        if (this.y == null || n < 0 || n >= this.y.size()) {
            return null;
        }
        return this.y.elementAt(n);
    }
    
    public void drawLegendSymbol(final Graphics graphics, final int n, final int n2, final NFDataSeries nfDataSeries, final Color color) {
        final int n3 = this.w.width / 2;
        final int n4 = this.w.height / 2;
        final int n5 = n - n3;
        final int n6 = n2 - n4;
        if (nfDataSeries.c == null) {
            graphics.setColor(color);
        }
        else {
            graphics.setColor(nfDataSeries.c);
        }
        if (nfDataSeries.sym == null && nfDataSeries.line == null && nfDataSeries.type != -1) {
            final int n7 = (int)(this.w.height * 0.5);
            final int n8 = (int)(this.w.height * 0.5);
            if (nfDataSeries.fillColor != null) {
                graphics.setColor(nfDataSeries.fillColor);
            }
            if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                NF12GraphicsUtil.patternFillRect(graphics, n - n8 / 2, n2 - n7 / 2, n8, n7, (nfDataSeries.line == null) ? nfDataSeries.c : nfDataSeries.line.getColor(), nfDataSeries.pattern);
            }
            else {
                graphics.fillRect(n - n8 / 2, n2 - n7 / 2, n8, n7);
            }
            graphics.setColor(Color.black);
            graphics.drawRect(n - n8 / 2, n2 - n7 / 2, n8, n7);
            return;
        }
        if (nfDataSeries.line != null) {
            if (nfDataSeries.line.getColor() == null) {
                graphics.setColor(color);
            }
            nfDataSeries.line.draw(graphics, n5 + 2, n2, n5 + this.w.width - 2, n2);
        }
        if (nfDataSeries.sym != null) {
            graphics.setColor((nfDataSeries.sym.getColor() == null) ? ((nfDataSeries.c == null) ? color : nfDataSeries.c) : nfDataSeries.sym.getColor());
            final int size = nfDataSeries.sym.size;
            if (nfDataSeries.line == null) {
                nfDataSeries.sym.size = (int)(Math.min(this.w.width, this.w.height) * 0.5);
            }
            else {
                nfDataSeries.sym.size = (int)(Math.min(this.w.width, this.w.height) * 0.5);
            }
            final NFLine borderLine = nfDataSeries.sym.getBorderLine();
            int thickness = 1;
            if (borderLine != null) {
                thickness = borderLine.getThickness();
                borderLine.setThickness(1);
            }
            switch (nfDataSeries.sym.type) {
                default: {
                    if (this.ah > 0.0) {
                        final NFGraphSymbol sym = nfDataSeries.sym;
                        sym.size /= (int)this.ah;
                    }
                    nfDataSeries.sym.draw(graphics, n, n2, nfDataSeries.pattern);
                    break;
                }
                case 9: {
                    if (this.ab > 0) {
                        nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2 - nfDataSeries.sym.size / 6, 2 * nfDataSeries.sym.size / 3, 2 * nfDataSeries.sym.size / 3, nfDataSeries.sym.size / 3, 1, nfDataSeries.pattern);
                        break;
                    }
                    nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2 - nfDataSeries.sym.size / 2, nfDataSeries.sym.size, nfDataSeries.sym.size, 0, 1, nfDataSeries.pattern);
                    break;
                }
                case 7: {
                    nfDataSeries.sym.draw(graphics, n, n6, (int)(this.w.height * 0.8), nfDataSeries.pattern);
                    break;
                }
                case 8: {
                    nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2, nfDataSeries.sym.size, nfDataSeries.pattern);
                    break;
                }
                case 13: {
                    nfDataSeries.sym.draw(graphics, n - 2 * nfDataSeries.sym.size / 3, n2 - nfDataSeries.sym.size / 2, nfDataSeries.sym.size, 2 * nfDataSeries.sym.size / 3, nfDataSeries.sym.size / 3, 1, nfDataSeries.pattern);
                    break;
                }
                case 14: {
                    nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2 - nfDataSeries.sym.size / 2, nfDataSeries.sym.size, 2 * nfDataSeries.sym.size / 3, nfDataSeries.sym.size / 3, 1, nfDataSeries.pattern);
                    break;
                }
                case 10:
                case 11:
                case 12: {
                    if (this.ab > 0) {
                        nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2 - nfDataSeries.sym.size / 6, nfDataSeries.sym.size, 2 * nfDataSeries.sym.size / 3, nfDataSeries.sym.size / 3, 1, nfDataSeries.pattern);
                        break;
                    }
                    nfDataSeries.sym.draw(graphics, n - nfDataSeries.sym.size / 2, n2 - nfDataSeries.sym.size / 2, nfDataSeries.sym.size, nfDataSeries.sym.size, 0, 1, nfDataSeries.pattern);
                    break;
                }
            }
            nfDataSeries.sym.size = size;
            if (borderLine != null) {
                borderLine.setThickness(thickness);
            }
        }
    }
    
    public void drawLegend(final Applet applet, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor, final Vector vector) {
        this.drawLegend(applet, graphics, rectangle, n, nfColor, vector, null);
    }
    
    public void drawLegend(final Applet applet, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor, final Vector vector, final Vector vector2) {
        this.drawLegend((Component)applet, graphics, rectangle, n, nfColor, vector, vector2);
    }
    
    public void drawLegend(final Component component, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor, final Vector vector) {
        this.drawLegend(component, graphics, rectangle, n, nfColor, vector, null);
    }
    
    public void drawLegend(final Component component, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor, final Vector vector, final Vector vector2) {
        int y = rectangle.y;
        int x = rectangle.x;
        final int size = vector.size();
        Dimension dimension = new Dimension(0, 0);
        final NFRegionBorder nfRegionBorder = new NFRegionBorder();
        if (this.x.length() > 0) {
            this.a.setLabel(this.x);
            dimension = this.a.getBounds(graphics, dimension, nfRegionBorder);
            this.a.externallyJustify(new Rectangle(x, y, (this.g == 1) ? rectangle.width : (dimension.width + this.aa), dimension.height), dimension, this.a.getJustify());
            this.a.draw(component, graphics);
            if (this.c != null && this.c.getSelectMode()) {
                this.setActiveLabel(size, this.a.getX() - dimension.width / 2, this.a.getY() - dimension.height / 2, dimension.width, dimension.height);
            }
            if (this.g == 1) {
                y += dimension.height;
            }
            else {
                x += dimension.width + this.aa;
            }
        }
        int n2 = 0;
        int n3 = 0;
        int n5;
        final int n4 = n5 = x + this.w.width + this.aa;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = vector.elementAt(i);
            String name;
            if (vector2 != null) {
                if (i < vector2.size()) {
                    name = vector2.elementAt(i);
                }
                else {
                    name = null;
                }
            }
            else {
                name = nfDataSeries.name;
            }
            if (name != null) {
                if (!name.equals("")) {
                    this.a.setLabel(name);
                    dimension = this.a.getBounds(graphics, dimension, nfRegionBorder);
                    this.a.externallyJustify(new Rectangle(n5, y, this.ad[n3], this.ae[n2]), dimension, this.a.getJustify());
                    this.a.draw(component, graphics);
                    this.setActiveLabel(i, this.a.getX() - dimension.width / 2, this.a.getY() - this.ae[n2] / 2, dimension.width, this.ae[n2]);
                    this.drawLegendSymbol(graphics, n5 - this.aa - this.w.width / 2, y + this.ae[n2] / 2, nfDataSeries, nfColor.getDefaultColor(i));
                    n5 += this.ad[n3] + this.w.width + this.aa + this.aa;
                    if (++n3 >= this.ag) {
                        n5 = n4;
                        y += this.ae[n2];
                        n3 = 0;
                        ++n2;
                    }
                }
            }
        }
    }
    
    public Dimension getLegendSize(final Rectangle rectangle, final Vector vector, final Graphics graphics) {
        return this.getLegendSize(rectangle, vector, null, graphics);
    }
    
    public Dimension getLegendSize(final Rectangle rectangle, final Vector vector, final Vector vector2, final Graphics graphics) {
        final Dimension dimension = new Dimension(0, 0);
        Dimension bounds = new Dimension(0, 0);
        final Dimension dimension2 = new Dimension(0, 0);
        final Dimension dimension3 = new Dimension(0, 0);
        final int size = vector.size();
        final Dimension dimension4 = new Dimension(0, 0);
        NFRegionBorder border = new NFRegionBorder();
        if (this.x.length() > 0) {
            this.a.setLabel(this.x);
            bounds = this.a.getBounds(graphics, bounds, border);
        }
        if (this.b != null) {
            border = this.b.getBorder(border);
            if (border != null) {
                dimension3.width = border.left + border.right + 6;
                final Dimension dimension5 = dimension3;
                dimension5.height += border.top + border.bottom + 6;
            }
        }
        int n = 0;
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries nfDataSeries = vector.elementAt(i);
            if (nfDataSeries.line != null) {
                final int minimumVisibleStyleWidth = nfDataSeries.line.getMinimumVisibleStyleWidth();
                if (minimumVisibleStyleWidth > n) {
                    n = minimumVisibleStyleWidth;
                }
            }
        }
        this.a.setLabel("X");
        this.w = this.a.getBounds(graphics, this.w, border);
        final Dimension w = this.w;
        w.width *= 2;
        if (n > 0 && n + 4 > this.w.width) {
            this.w.width = n + 4;
        }
        this.af = 0;
        this.ag = 0;
        int n2 = 0;
        int n3 = 0;
        if (this.g == 1) {
            this.ag = 1;
        }
        else {
            this.ag = size;
        }
        if (this.ac > 1) {
            this.ag = this.ac;
            if (this.ag > size) {
                this.ag = size;
            }
        }
        if (this.ac < 0) {
            int width;
            if (this.l != null) {
                width = (int)(this.scaleValue(rectangle, "X", this.l) - this.scaleValue(rectangle, "X", new Double(0.0)));
            }
            else {
                width = rectangle.width;
            }
            int height;
            if (this.m != null) {
                height = (int)(this.scaleValue(rectangle, "Y", this.m) - this.scaleValue(rectangle, "Y", new Double(0.0)));
            }
            else {
                height = rectangle.height;
            }
            n2 = width - dimension3.width;
            n3 = height - dimension3.height;
            if (this.x.length() > 0) {
                if (this.g == 1) {
                    n3 -= bounds.height;
                }
                else {
                    n2 -= bounds.width + this.aa;
                }
            }
        }
        this.ad = new int[size];
        this.ae = new int[size];
        while (true) {
            this.af = this.a(size, this.ag);
            this.a(graphics, vector, size, this.ag, this.af, dimension4, border, vector2);
            dimension.width = 0;
            for (int j = 0; j < this.ag; ++j) {
                if (j > 0 && dimension.width > 0) {
                    final Dimension dimension6 = dimension;
                    dimension6.width += this.aa;
                }
                if (this.ad[j] != 0) {
                    final Dimension dimension7 = dimension;
                    dimension7.width += this.w.width + this.aa;
                    final Dimension dimension8 = dimension;
                    dimension8.width += this.ad[j];
                }
            }
            dimension.height = 0;
            for (int k = 0; k < this.af; ++k) {
                final Dimension dimension9 = dimension;
                dimension9.height += this.ae[k];
            }
            if (this.ac >= 0) {
                break;
            }
            if (this.g == 1) {
                if (this.ag >= size || dimension.height <= n3) {
                    break;
                }
                ++this.ag;
            }
            else {
                if (this.ag <= 1 || dimension.width <= n2) {
                    break;
                }
                --this.ag;
            }
        }
        int width2;
        int height2;
        if (this.g == 1) {
            final int n4 = (bounds.width > dimension.width) ? bounds.width : dimension.width;
            final int n5 = dimension.height + bounds.height;
            width2 = n4 + dimension3.width;
            height2 = n5 + dimension3.height;
        }
        else {
            width2 = dimension.width + dimension3.width;
            if (bounds.width > 0) {
                width2 += bounds.width + this.aa;
            }
            if (dimension.height < bounds.height) {
                dimension.height = bounds.height;
            }
            height2 = dimension.height + dimension3.height;
        }
        if (this.l != null) {
            final int n6 = (int)(this.scaleValue(rectangle, "X", this.l) - this.scaleValue(rectangle, "X", new Double(0.0)));
            if (width2 > n6) {
                width2 = n6;
            }
        }
        if (this.m != null) {
            final int n7 = (int)(this.scaleValue(rectangle, "Y", this.m) - this.scaleValue(rectangle, "Y", new Double(0.0)));
            if (height2 > n7) {
                height2 = n7;
            }
        }
        dimension4.width = width2;
        dimension4.height = height2;
        return dimension4;
    }
    
    private int a(final int n, final int n2) {
        int n3 = n / n2;
        if (n2 * n3 != n) {
            ++n3;
        }
        return n3;
    }
    
    private void a(final Graphics graphics, final Vector vector, final int n, final int n2, final int n3, Dimension bounds, final NFRegionBorder nfRegionBorder, final Vector vector2) {
        for (int i = 0; i < n2; ++i) {
            this.ad[i] = 0;
        }
        for (int j = 0; j < n3; ++j) {
            this.ae[j] = 0;
        }
        int n4 = 0;
        int n5 = 0;
        for (int k = 0; k < n; ++k) {
            final NFDataSeries nfDataSeries = vector.elementAt(k);
            String name;
            if (vector2 != null && k < vector2.size()) {
                name = vector2.elementAt(k);
            }
            else {
                name = nfDataSeries.name;
            }
            if (name != null) {
                if (!name.equals("")) {
                    this.a.setLabel(name);
                    bounds = this.a.getBounds(graphics, bounds, nfRegionBorder);
                    if (bounds.width > this.ad[n4]) {
                        this.ad[n4] = bounds.width;
                    }
                    if (bounds.height > this.ae[n5]) {
                        this.ae[n5] = bounds.height;
                    }
                    if (++n4 >= n2) {
                        n4 = 0;
                        ++n5;
                    }
                }
            }
        }
    }
    
    public Point getLegendPos(final Rectangle rectangle, final Dimension dimension) {
        final int n = dimension.width / 2;
        final int n2 = dimension.height / 2;
        int n3 = 0;
        int n4 = 0;
        switch (this.h) {
            case 6: {
                n3 = rectangle.x;
                n4 = rectangle.y;
                break;
            }
            case 2: {
                n3 = rectangle.x + rectangle.width / 2 - n;
                n4 = rectangle.y;
                break;
            }
            case 5: {
                n3 = rectangle.x + rectangle.width - dimension.width;
                n4 = rectangle.y;
                break;
            }
            case 1: {
                n3 = rectangle.x;
                n4 = rectangle.y + rectangle.height / 2 - n2;
                break;
            }
            case 0: {
                n3 = rectangle.x + rectangle.width - dimension.width;
                n4 = rectangle.y + rectangle.height / 2 - n2;
                break;
            }
            case 8: {
                n3 = rectangle.x;
                n4 = rectangle.y + rectangle.height - dimension.height;
                break;
            }
            case 3: {
                n3 = rectangle.x + rectangle.width / 2 - n;
                n4 = rectangle.y + rectangle.height - dimension.height;
                break;
            }
            case 7: {
                n3 = rectangle.x + rectangle.width - dimension.width;
                n4 = rectangle.y + rectangle.height - dimension.height;
                break;
            }
            case 4: {
                final double scaleValue = this.scaleValue(rectangle, "X", this.j);
                final double scaleValue2 = this.scaleValue(rectangle, "Y", this.k);
                if (NFCoord.getMapType(this.n) != -1) {
                    n3 = rectangle.x + (int)scaleValue;
                }
                else {
                    n3 = (int)scaleValue;
                }
                if (NFCoord.getMapType(this.o) != -1) {
                    n4 = rectangle.y + (int)scaleValue2;
                }
                else {
                    n4 = (int)scaleValue2;
                }
                switch (this.i) {
                    case 2: {
                        n3 -= dimension.width / 2;
                        break;
                    }
                    case 5: {
                        n3 -= dimension.width;
                        break;
                    }
                    case 1: {
                        n4 -= dimension.height / 2;
                        break;
                    }
                    case 4: {
                        n3 -= dimension.width / 2;
                        n4 -= dimension.height / 2;
                        break;
                    }
                    case 0: {
                        n3 -= dimension.width;
                        n4 -= dimension.height / 2;
                        break;
                    }
                    case 8: {
                        n4 -= dimension.height;
                        break;
                    }
                    case 3: {
                        n3 -= dimension.width / 2;
                        n4 -= dimension.height;
                        break;
                    }
                    case 7: {
                        n3 -= dimension.width;
                        n4 -= dimension.height;
                        break;
                    }
                }
                break;
            }
            default: {
                n3 = rectangle.x;
                n4 = rectangle.y;
                break;
            }
        }
        return new Point(n3, n4);
    }
    
    public double scaleValue(final Rectangle rectangle, final String s, final Object o) {
        if (s.equals("X")) {
            return NFCoord.getPixel(o, this.n, this.p, rectangle.width, -1.0);
        }
        return NFCoord.getPixel(o, this.o, this.p, rectangle.height, -1.0);
    }
    
    public boolean isManualLayout() {
        return this.h == 4;
    }
    
    public boolean isInside() {
        return this.isManualLayout();
    }
    
    public boolean isOutside() {
        return !this.isManualLayout();
    }
    
    public void adjustRect(final Graphics graphics, final Rectangle rectangle, final int n) {
        if (!this.enabled()) {
            return;
        }
        Vector vector = this.d;
        if (vector == null || vector.size() == 0) {
            vector = this.f;
        }
        this.a(rectangle, this.getLegendSize(rectangle, vector, this.e, graphics), n);
    }
    
    public void draw(final Applet applet, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor) {
        this.draw((Component)applet, graphics, rectangle, n, nfColor);
    }
    
    public void draw(final Component component, final Graphics graphics, final Rectangle rectangle, final int n, final NFColor nfColor) {
        if (!this.enabled()) {
            return;
        }
        Vector vector = this.d;
        if (vector == null || vector.size() == 0) {
            vector = this.f;
        }
        this.q = rectangle.x;
        this.r = rectangle.y;
        this.s = rectangle.width;
        this.t = rectangle.height;
        final Dimension legendSize = this.getLegendSize(rectangle, vector, this.e, graphics);
        final Point legendPos = this.getLegendPos(rectangle, legendSize);
        this.a(rectangle, legendSize, n);
        this.u.x = legendPos.x;
        this.u.y = legendPos.y;
        this.v.width = legendSize.width;
        this.v.height = legendSize.height;
        final int width = legendSize.width;
        if (this.b != null) {
            final NFRegionBorder border = this.b.getBorder();
            this.b.draw(graphics, legendPos.x, legendPos.y, legendSize.width, legendSize.height);
            if (border != null) {
                final Point point = legendPos;
                point.x += border.left + 3;
                final Point point2 = legendPos;
                point2.y += border.top + 3;
                final Dimension dimension = legendSize;
                dimension.width -= border.left + border.right + 6;
                final Dimension dimension2 = legendSize;
                dimension2.height -= border.top + border.bottom + 6;
            }
        }
        if (this.c != null) {
            this.c.resetLabels(this.y);
        }
        final Graphics create = graphics.create(legendPos.x, legendPos.y, legendSize.width, legendSize.height);
        create.translate(-legendPos.x, -legendPos.y);
        this.drawLegend(component, create, new Rectangle(legendPos.x, legendPos.y, legendSize.width, legendSize.height), n, nfColor, vector, this.e);
        create.dispose();
    }
    
    private void a(final Rectangle rectangle, final Dimension dimension, final int n) {
        switch (this.h) {
            case 6: {
                if (this.g == 1) {
                    rectangle.x += dimension.width + n;
                    rectangle.width -= dimension.width + n;
                    break;
                }
                rectangle.y += dimension.height + n;
                rectangle.height -= dimension.height + n;
                break;
            }
            case 1: {
                rectangle.x += dimension.width + n;
                rectangle.width -= dimension.width + n;
                break;
            }
            case 8: {
                if (this.g == 1) {
                    rectangle.x += dimension.width + n;
                    rectangle.width -= dimension.width + n;
                    break;
                }
                rectangle.height -= dimension.height + n;
                break;
            }
            case 2: {
                rectangle.y += dimension.height + n;
                rectangle.height -= dimension.height + n;
                break;
            }
            case 3: {
                rectangle.height -= dimension.height + n;
                break;
            }
            case 5: {
                if (this.g == 1) {
                    rectangle.width -= dimension.width + n;
                    break;
                }
                rectangle.y += dimension.height + n;
                rectangle.height -= dimension.height + n;
                break;
            }
            case 0: {
                rectangle.width -= dimension.width + n;
                break;
            }
            case 7: {
                if (this.g == 1) {
                    rectangle.width -= dimension.width + n;
                    break;
                }
                rectangle.height -= dimension.height + n;
                break;
            }
        }
    }
    
    public boolean loadParams(final NFParam nfParam, final String s) throws Exception {
        boolean b = false;
        if (nfParam.changed(s)) {
            this.a = NFLabel.loadParams(nfParam, nfParam.get(s));
            if (this.a == null) {
                this.x = "";
            }
            else {
                this.x = this.a.getLabel();
                if (this.a.getJustify() == -1) {
                    this.a.setJustify(1);
                }
            }
            this.setDwell(this.c);
            b = true;
        }
        if (nfParam.changed(s + "Box")) {
            this.b = NFRegion.loadParams(nfParam, nfParam.get(s + "Box"));
            b = true;
        }
        if (nfParam.changed(s + "Items")) {
            b = true;
            if (this.d == null) {
                this.d = new Vector();
            }
            else {
                this.d.removeAllElements();
            }
            final Vector vector = (Vector)nfParam.get(s + "Items");
            if (vector == null || vector.size() == 0) {
                return b;
            }
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final Vector<Color> vector2 = vector.elementAt(i);
                final NFDataSeries nfDataSeries = new NFDataSeries();
                nfDataSeries.name = (String)vector2.elementAt(0);
                nfDataSeries.c = vector2.elementAt(1);
                nfDataSeries.type = -1;
                nfDataSeries.sym = NFGraphSymbol.loadParams(nfParam, vector2, 2);
                nfDataSeries.line = NFLine.loadParams(nfParam, vector2, NFParam.gsParamCount() + 2);
                if (nfDataSeries.line != null && nfDataSeries.line.getColor() == null) {
                    nfDataSeries.line.setColor(nfDataSeries.c);
                }
                nfDataSeries.fillColor = null;
                nfDataSeries.pattern = NFPatternFill.loadPatternFill(vector2, NFParam.gsParamCount() + NFParam.lineParamCount() + 2);
                this.d.addElement(nfDataSeries);
            }
            this.setDwell(this.c);
        }
        if (nfParam.changed(s + "Layout")) {
            b = true;
            final Vector vector3 = (Vector)nfParam.get(s + "Layout");
            if (vector3 == null || vector3.size() <= 0) {
                this.g = 1;
                this.h = 0;
                this.j = null;
                this.k = null;
                this.i = 4;
                this.ac = -1;
            }
            else {
                this.g = vector3.elementAt(0).intValue();
                this.h = vector3.elementAt(1).intValue();
                this.j = vector3.elementAt(2);
                this.k = vector3.elementAt(3);
                this.i = vector3.elementAt(4).intValue();
                this.ac = vector3.elementAt(5).intValue();
            }
        }
        if (nfParam.changed(s + "BoxSize")) {
            b = true;
            final Vector vector4 = (Vector)nfParam.get(s + "BoxSize");
            if (vector4 == null || vector4.size() <= 0) {
                this.l = null;
                this.m = null;
            }
            else {
                this.l = vector4.elementAt(0);
                this.m = vector4.elementAt(1);
            }
        }
        if (nfParam.changed(s + "Axis")) {
            b = true;
            final Vector vector5 = (Vector)nfParam.get(s + "Axis");
            this.n = NFUtil.getString(vector5, 0, NFLegend.XAXIS_DEFAULT);
            this.o = NFUtil.getString(vector5, 1, NFLegend.YAXIS_DEFAULT);
        }
        if (nfParam.changed(s + "ActiveLabels")) {
            b = true;
            this.z = NFActiveLabel.loadAllParams(nfParam, s + "ActiveLabels");
            this.setDwell(this.c);
        }
        if (nfParam.changed(s + "Labels")) {
            b = true;
            this.e = (Vector)nfParam.get(s + "Labels");
        }
        return b;
    }
    
    public static void defineParams(final NFParam nfParam, final String s) {
        nfParam.defineLabel(s);
        nfParam.defineRegion(s + "Box");
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineString(s + "ItemsString", ""));
        vector.addElement(nfParam.defineColor(s + "ItemsColor", null));
        nfParam.defineGraphSymbol(s + "Items", vector);
        for (int i = 2; i < vector.size(); ++i) {
            final NFParamDef nfParamDef = vector.elementAt(i);
            if (nfParamDef.param.equals(s + "ItemsSymType")) {
                nfParamDef.val = new Integer(0);
            }
            if (nfParamDef.param.equals(s + "ItemsSymSize")) {
                nfParamDef.val = new Integer(0);
            }
        }
        nfParam.defineLine(s + "Items", vector);
        NFPatternFill.getPatternFillParamTuple(vector, nfParam, s + "Items" + "PatternFill");
        nfParam.defineVector(s + "Items", nfParam.defineTuple(s + "ItemsTuple", vector));
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("VERTICAL", new Integer(1));
        hashtable.put("HORIZONTAL", new Integer(2));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(nfParam.defineSymbol(s + "LayoutType", hashtable, new Integer(1)));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("TOPLEFT", new Integer(6));
        hashtable2.put("TOP", new Integer(2));
        hashtable2.put("TOPRIGHT", new Integer(5));
        hashtable2.put("LEFT", new Integer(1));
        hashtable2.put("CENTER", new Integer(4));
        hashtable2.put("RIGHT", new Integer(0));
        hashtable2.put("BOTTOMLEFT", new Integer(8));
        hashtable2.put("BOTTOM", new Integer(3));
        hashtable2.put("BOTTOMRIGHT", new Integer(7));
        vector2.addElement(nfParam.defineSymbol(s + "LayoutLocation", hashtable2, new Integer(0)));
        vector2.addElement(nfParam.defineDate(s + "LayoutX", new Double(50.0)));
        vector2.addElement(nfParam.defineDate(s + "LayoutY", new Double(50.0)));
        vector2.addElement(nfParam.defineSymbol(s + "LayoutJustify", hashtable2, new Integer(4)));
        vector2.addElement(nfParam.defineNumber(s + "LayoutColCount", new Integer(-1)));
        nfParam.defineTuple(s + "Layout", vector2);
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(nfParam.defineDate(s + "MaxWidth", new Double(100.0)));
        vector3.addElement(nfParam.defineDate(s + "MaxHeight", new Double(100.0)));
        nfParam.defineTuple(s + "BoxSize", vector3);
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(nfParam.defineString(s + "Xaxis", null));
        vector4.addElement(nfParam.defineString(s + "Yaxis", null));
        nfParam.defineTuple(s + "Axis", vector4);
        nfParam.defineActiveLabel(s + "ActiveLabels");
        nfParam.defineVector(s + "Labels", nfParam.defineString(s + "Label", null));
    }
    
    public void removeActiveLabels() {
        try {
            if (this.c != null) {
                this.c.removeLabel(this.y);
            }
            this.y.removeAllElements();
        }
        catch (Exception ex) {
            this.debug("Could not remove active labels");
            this.debug(ex.toString());
        }
    }
    
    public void setActiveLabel(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.y.elementAt(n).setBounds(n2, n3, n4, n5);
        }
        catch (NullPointerException ex) {}
        catch (Exception ex2) {
            this.debug("NFLegend: Could not set active label " + n);
        }
    }
    
    public boolean isDraggable(final Event event, final int n, final int n2) {
        return this.h == 4 && n >= this.u.x && n <= this.u.x + this.v.width && n2 >= this.u.y && n2 <= this.u.y + this.v.height;
    }
    
    public double[] getCoords() {
        super.curCoord[0] = NFCoord.getValue(this.j, this.n, this.p);
        super.curCoord[1] = NFCoord.getValue(this.k, this.o, this.p);
        return super.curCoord;
    }
    
    public double[] moveRelative(final Event event, final int n, final int n2) {
        if (n == 0 && n2 == 0) {
            return null;
        }
        super.newCoord[0] = NFCoord.getValue(NFCoord.getPixel(this.j, this.n, this.p, this.s, -1.0) + n, this.n, this.p, this.s);
        super.newCoord[1] = NFCoord.getValue(NFCoord.getPixel(this.k, this.o, this.p, this.t, -1.0) + n2, this.o, this.p, this.t);
        return super.newCoord;
    }
    
    public boolean dragTo(final double[] array) {
        this.j = new Double(array[0]);
        this.k = new Double(array[1]);
        return true;
    }
    
    public String getLegendLabel(final int n) {
        return this.getLegendLabel(null, n);
    }
    
    public String getLegendLabel(final NFDataSeries nfDataSeries, final int n) {
        int n2 = n - 1;
        if (n2 < 0) {
            n2 = 0;
        }
        if (this.e != null && n2 < this.e.size()) {
            return (String)this.e.elementAt(n2);
        }
        if (this.f != null && n2 < this.f.size()) {
            NFDataSeries nfDataSeries2;
            if (nfDataSeries != null && this.f.contains(nfDataSeries)) {
                nfDataSeries2 = nfDataSeries;
            }
            else {
                nfDataSeries2 = this.f.elementAt(n2);
            }
            return nfDataSeries2.name;
        }
        return null;
    }
    
    static {
        NFLegend.XAXIS_DEFAULT = "PERCENT";
        NFLegend.YAXIS_DEFAULT = "PERCENT";
    }
}
