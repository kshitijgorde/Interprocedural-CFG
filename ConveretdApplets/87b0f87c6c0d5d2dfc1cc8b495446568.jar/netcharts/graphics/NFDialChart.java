// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.net.URL;
import netcharts.util.NFParamException;
import netcharts.util.NFParam;
import netcharts.util.NFParamDef;
import java.awt.Event;
import java.awt.Polygon;
import netcharts.util.NFUtil;
import netcharts.util.NFDebug;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;
import java.util.Enumeration;
import java.applet.Applet;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;

public class NFDialChart extends NFGraph
{
    private Vector a;
    private Vector b;
    private Vector c;
    private Hashtable d;
    private Hashtable e;
    private Hashtable f;
    private boolean g;
    private boolean h;
    public static final int TOPRIGHT = 1;
    public static final int TOP = 2;
    public static final int TOPLEFT = 3;
    public static final int LEFT = 4;
    public static final int BOTTOMLEFT = 5;
    public static final int BOTTOM = 6;
    public static final int BOTTOMRIGHT = 7;
    public static final int RIGHT = 8;
    public static final int NONE = 0;
    private int i;
    private static final int j = 10;
    private int k;
    private int l;
    private int m;
    private int n;
    Rectangle o;
    Rectangle p;
    private boolean q;
    private boolean r;
    private boolean s;
    private NFHand t;
    private NFDialSector u;
    private int v;
    private int w;
    private boolean x;
    private int y;
    private NFDialSector z;
    private NFHand aa;
    private int ab;
    private int ac;
    private StringBuffer ad;
    
    public NFDialChart(final Applet applet) {
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Vector();
        this.d = new Hashtable();
        this.e = new Hashtable();
        this.f = new Hashtable();
        this.g = false;
        this.h = false;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = null;
        this.x = false;
        this.y = 0;
        this.z = null;
        this.aa = null;
        this.ab = 0;
        this.ac = 0;
        this.ad = null;
        this.initGraph(applet);
    }
    
    public NFDialChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.a = new Vector();
        this.b = new Vector();
        this.c = new Vector();
        this.d = new Hashtable();
        this.e = new Hashtable();
        this.f = new Hashtable();
        this.g = false;
        this.h = false;
        this.i = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = null;
        this.x = false;
        this.y = 0;
        this.z = null;
        this.aa = null;
        this.ab = 0;
        this.ac = 0;
        this.ad = null;
        this.initGraph(applet);
        this.reshape(n, n2, n3, n4);
    }
    
    public void dwellChanged() {
        this.rebuildAllActiveLabels();
    }
    
    public void setScale(final double n) {
        super.setScale(n);
        for (int i = 0; i < this.c.size(); ++i) {
            final NFDial nfDial = this.c.elementAt(i);
            final Enumeration elements = nfDial.sectors.elements();
            while (elements.hasMoreElements()) {
                final NFDialSector nfDialSector = elements.nextElement();
                if (nfDialSector.label != null) {
                    nfDialSector.label.setScale(n);
                }
            }
            final Enumeration elements2 = nfDial.hands.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().setScale(n);
            }
            if (nfDial.ticLabel != null) {
                nfDial.ticLabel.setScale(n);
            }
        }
    }
    
    public void setSectorLabelPos(final String s, final double labelPos) {
        final NFDialSector sector = this.findSector(s);
        if (sector != null) {
            sector.labelPos = labelPos;
        }
    }
    
    public NFDialSector findSector(final String s) {
        for (int i = 0; i < this.a.size(); ++i) {
            final NFDialSector nfDialSector = this.a.elementAt(i);
            if (nfDialSector.name.equals(s)) {
                return nfDialSector;
            }
        }
        return null;
    }
    
    public NFDial findDial(final String s) {
        for (int i = 0; i < this.c.size(); ++i) {
            final NFDial nfDial = this.c.elementAt(i);
            if (nfDial.name.equals(s)) {
                return nfDial;
            }
        }
        return null;
    }
    
    public NFHand findHand(final String s) {
        for (int i = 0; i < this.b.size(); ++i) {
            final NFHand nfHand = this.b.elementAt(i);
            if (nfHand.getName().equals(s)) {
                return nfHand;
            }
        }
        return null;
    }
    
    public void addSector(final String s, final String s2, final Color color, final double n, final double n2, final String s3) {
        this.addSector(s, s2, color, n, n2, s3, null);
    }
    
    public void addSector(final String s, final Color color, final double n, final double n2, final String s2) {
        this.addSector(s, s, color, n, n2, s2, null);
    }
    
    public void addSector(final String s, final String s2, final Color color, final double orp, final double irp, final String s3, final NFActiveLabel activeLabel) {
        final NFDial dial = this.findDial(s3);
        if (dial != null) {
            final NFDialSector nfDialSector = new NFDialSector();
            nfDialSector.name = s.toString();
            if (s2 == null) {
                nfDialSector.text = nfDialSector.name;
            }
            else {
                nfDialSector.text = s2.toString();
            }
            nfDialSector.color = color;
            (nfDialSector.label = new NFLabel()).setLabel("OFF");
            nfDialSector.label.setComponent(this);
            nfDialSector.dial = dial;
            nfDialSector.orp = orp;
            nfDialSector.irp = irp;
            nfDialSector.rect = new Rectangle();
            nfDialSector.center = new Point(0, 0);
            nfDialSector.activeLabel = activeLabel;
            dial.sectors.addElement(nfDialSector);
            this.a.addElement(nfDialSector);
        }
    }
    
    public void addSectorData(final String s, final double n, final double n2) {
        this.addSectorData(this.findSector(s), n, n2);
    }
    
    public void addSectorData(final NFDialSector nfDialSector, final double startValue, final double stopValue) {
        if (nfDialSector != null) {
            nfDialSector.startValue = startValue;
            nfDialSector.stopValue = stopValue;
            if (nfDialSector.stopValue < nfDialSector.startValue && nfDialSector.dial.max - nfDialSector.dial.min > 0.0) {
                nfDialSector.stopValue = this.a(nfDialSector.stopValue, nfDialSector.startValue, nfDialSector.dial.max, nfDialSector.dial.min);
            }
        }
    }
    
    private double a(double n, final double n2, final double n3, final double n4) {
        while (n < n2) {
            n += n3 - n4;
        }
        return n;
    }
    
    public void addHand(final String s, final Color color, final Color color2, final String s2) {
        this.addHand(s, color, color2, 2, 20, 10, 0.3, 1, 0.0, s2, null);
    }
    
    public void addHand(final String s, final Color tipColor, final Color shaftColor, final int style, final int tipLength, final int tipWidth, final double shaftLength, final int shaftWidth, final double value, final String s2, final NFActiveLabel activeLabel) {
        final NFDial dial = this.findDial(s2);
        if (dial != null) {
            final NFHand nfHand = new NFHand(s);
            nfHand.setStyle(style);
            nfHand.setValue(value);
            nfHand.setTipLength(tipLength);
            nfHand.setTipWidth(tipWidth);
            nfHand.setShaftLength(shaftLength);
            nfHand.setShaftWidth(shaftWidth);
            nfHand.setTipColor(tipColor);
            nfHand.setShaftColor(shaftColor);
            nfHand.setDial(dial);
            nfHand.setActiveLabel(activeLabel);
            dial.hands.addElement(nfHand);
            this.b.addElement(nfHand);
        }
    }
    
    public void addDial(final String s, final double n, final double n2, final double n3, final int n4, final NFLine nfLine) {
        this.addDial(s, n, n2, n3, n4, nfLine, null);
    }
    
    public void addDial(final String name, final double startAngle, final double stopAngle, final double radiusPercentage, final int ticLocation, final NFLine borderLine, final NFActiveLabel activeLabel) {
        final NFDial nfDial = new NFDial();
        nfDial.name = name;
        nfDial.startAngle = startAngle;
        nfDial.stopAngle = stopAngle;
        nfDial.radiusPercentage = radiusPercentage;
        nfDial.ticLocation = ticLocation;
        nfDial.borderLine = borderLine;
        nfDial.sectors = new Vector();
        nfDial.hands = new Vector();
        nfDial.ticLabel.setLabel("OFF");
        nfDial.activeLabel = activeLabel;
        this.c.addElement(nfDial);
    }
    
    public void addDialScale(final String s, final double min, final double max, final double step) {
        final NFDial dial = this.findDial(s);
        if (dial != null) {
            dial.min = min;
            dial.max = max;
            dial.step = step;
            dial.spacing = new NFSpacing(dial.min, dial.max, dial.step);
        }
    }
    
    public void setSectorLabel(final String s, final NFLabel label) {
        final NFDialSector sector = this.findSector(s);
        if (sector != null) {
            sector.label = label;
            if (this.a(label)) {
                label.setLabel(sector.text.toString());
                label.setScale(super.scale);
                label.setComponent(this);
            }
        }
    }
    
    public int getSectorIndex(final NFDialSector nfDialSector) {
        return this.a.indexOf(nfDialSector);
    }
    
    public int getHandIndex(final NFHand nfHand) {
        return this.b.indexOf(nfHand);
    }
    
    public NFActiveLabel getSectorActiveLabel(final int n) {
        if (this.a == null || n < 0 || n >= this.a.size()) {
            return null;
        }
        return this.getSectorActiveLabel(this.a.elementAt(n));
    }
    
    public NFActiveLabel getSectorActiveLabel(final String s) {
        return this.getSectorActiveLabel(this.findSector(s));
    }
    
    public NFActiveLabel getSectorActiveLabel(final NFDialSector nfDialSector) {
        if (nfDialSector != null) {
            return this.d.get(nfDialSector.text);
        }
        return null;
    }
    
    public NFActiveLabel getDialActiveLabel(final int n) {
        if (this.c == null || n < 0 || n >= this.c.size()) {
            return null;
        }
        return this.getDialActiveLabel(this.c.elementAt(n));
    }
    
    public NFActiveLabel getDialActiveLabel(final String s) {
        return this.getDialActiveLabel(this.findDial(s));
    }
    
    public NFActiveLabel getDialActiveLabel(final NFDial nfDial) {
        if (nfDial != null) {
            return this.f.get(nfDial.name);
        }
        return null;
    }
    
    public NFActiveLabel getHandActiveLabel(final int n) {
        if (this.b == null || n < 0 || n >= this.b.size()) {
            return null;
        }
        return this.getHandActiveLabel(this.b.elementAt(n));
    }
    
    public NFActiveLabel getHandActiveLabel(final String s) {
        return this.getHandActiveLabel(this.findHand(s));
    }
    
    public NFActiveLabel getHandActiveLabel(final NFHand nfHand) {
        if (nfHand != null) {
            return this.e.get(nfHand.getName());
        }
        return null;
    }
    
    public void deleteSector(final String s) {
        final NFDialSector sector = this.findSector(s);
        if (sector != null) {
            if (super.dwell != null) {
                super.dwell.removeLabel(this.getSectorActiveLabel(sector));
            }
            sector.dial.sectors.removeElement(sector);
            this.a.removeElement(sector);
        }
    }
    
    public void deleteHand(final String s) {
        final NFHand hand = this.findHand(s);
        if (hand != null) {
            if (super.dwell != null) {
                super.dwell.removeLabel(this.getHandActiveLabel(hand));
            }
            hand.getDial().hands.removeElement(hand);
            this.b.removeElement(hand);
        }
    }
    
    public void deleteDial(final String s) {
        final NFDial dial = this.findDial(s);
        if (dial != null) {
            final Enumeration elements = dial.sectors.elements();
            while (elements.hasMoreElements()) {
                final NFDialSector nfDialSector = elements.nextElement();
                if (super.dwell != null) {
                    super.dwell.removeLabel(this.getSectorActiveLabel(nfDialSector));
                }
                this.a.removeElement(nfDialSector);
            }
            final Enumeration elements2 = dial.hands.elements();
            while (elements2.hasMoreElements()) {
                final NFHand nfHand = elements2.nextElement();
                if (super.dwell != null) {
                    super.dwell.removeLabel(this.getHandActiveLabel(nfHand));
                }
                this.b.removeElement(nfHand);
            }
            dial.sectors.removeAllElements();
            dial.hands.removeAllElements();
            this.c.removeElement(dial);
        }
    }
    
    public void removeHandActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                super.dwell.removeLabel(nfActiveLabel);
            }
        }
        this.e.clear();
    }
    
    public void removeDialActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.f.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                super.dwell.removeLabel(nfActiveLabel);
            }
        }
        this.f.clear();
    }
    
    public void removeSectorActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.d.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                super.dwell.removeLabel(nfActiveLabel);
            }
        }
        this.d.clear();
    }
    
    public void addHandActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.b.size(); ++i) {
            final NFHand nfHand = this.b.elementAt(i);
            this.addHandActiveLabel(nfHand.getName(), this.a(nfHand.getActiveLabel(), "Hands:" + i));
        }
    }
    
    public void addDialActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.c.size(); ++i) {
            final NFDial nfDial = this.c.elementAt(i);
            this.addDialActiveLabel(nfDial.name, this.a(nfDial.activeLabel, "Dials:" + i));
        }
    }
    
    public void rebuildAllActiveLabels() {
        this.rebuildAllActiveLabels(true, true, true);
    }
    
    public void rebuildAllActiveLabels(final boolean b, final boolean b2, final boolean b3) {
        this.removeDialActiveLabels();
        this.removeSectorActiveLabels();
        this.removeHandActiveLabels();
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.c.size(); ++i) {
            final NFDial nfDial = this.c.elementAt(i);
            if (b) {
                this.addDialActiveLabel(nfDial.name, this.a(nfDial.activeLabel, "Dials:" + i));
            }
            for (int n = 0; b2 && n < nfDial.sectors.size(); ++n) {
                final NFDialSector nfDialSector = nfDial.sectors.elementAt(n);
                this.addSectorActiveLabel(nfDialSector.text, this.a(nfDialSector.activeLabel, "Sectors:" + this.a.indexOf(nfDialSector)));
            }
            for (int n2 = 0; b3 && n2 < nfDial.hands.size(); ++n2) {
                final NFHand nfHand = nfDial.hands.elementAt(n2);
                this.addHandActiveLabel(nfHand.getName(), this.a(nfHand.getActiveLabel(), "Hands:" + this.b.indexOf(nfHand)));
            }
        }
    }
    
    public void addHandActiveLabel(final String s, final NFActiveLabel nfActiveLabel) {
        if (nfActiveLabel != null && super.dwell != null && s != null) {
            this.e.put(s, nfActiveLabel);
            super.dwell.addLabel(nfActiveLabel);
        }
    }
    
    public void addSectorActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFDialSector nfDialSector = this.a.elementAt(i);
            this.addSectorActiveLabel(nfDialSector.text, this.a(nfDialSector.activeLabel, "Sectors:" + i));
        }
    }
    
    public void addSectorActiveLabel(final String s, final NFActiveLabel nfActiveLabel) {
        if (nfActiveLabel != null && super.dwell != null && s != null) {
            this.d.put(s, nfActiveLabel);
            super.dwell.addLabel(nfActiveLabel);
        }
    }
    
    public void addDialActiveLabel(final String s, final NFActiveLabel nfActiveLabel) {
        if (nfActiveLabel != null && super.dwell != null && s != null) {
            this.f.put(s, nfActiveLabel);
            super.dwell.addLabel(nfActiveLabel);
        }
    }
    
    public void resetHandActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                nfActiveLabel.reset();
            }
        }
    }
    
    public void resetSectorActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.d.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                nfActiveLabel.reset();
            }
        }
    }
    
    public void resetDialActiveLabels() {
        if (super.dwell == null) {
            return;
        }
        final Enumeration<NFActiveLabel> elements = this.f.elements();
        while (elements.hasMoreElements()) {
            final NFActiveLabel nfActiveLabel = elements.nextElement();
            if (nfActiveLabel != null) {
                nfActiveLabel.reset();
            }
        }
    }
    
    public void deleteAllSectors() {
        this.removeSectorActiveLabels();
        for (int i = 0; i < this.c.size(); ++i) {
            ((NFDial)this.c.elementAt(i)).sectors.removeAllElements();
        }
        this.a.removeAllElements();
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
    }
    
    public void deleteAllDials() {
        this.deleteAllSectors();
        this.deleteAllHands();
        this.removeDialActiveLabels();
        this.c.removeAllElements();
    }
    
    public void deleteAllHands() {
        this.removeHandActiveLabels();
        for (int i = 0; i < this.c.size(); ++i) {
            ((NFDial)this.c.elementAt(i)).hands.removeAllElements();
        }
        this.b.removeAllElements();
    }
    
    private boolean a(final NFLabel nfLabel) {
        return nfLabel != null && nfLabel.getLabel() != null && nfLabel.getLabel().length() > 0 && !nfLabel.getLabel().equals("OFF");
    }
    
    private void a() {
        if (super.legend != null) {
            super.legend.clearDataSets();
        }
        for (int i = 0; i < this.a.size(); ++i) {
            final NFDialSector nfDialSector = this.a.elementAt(i);
            if (super.legend != null) {
                final NFDataSeries nfDataSeries = new NFDataSeries();
                if (this.a(nfDialSector.label)) {
                    nfDataSeries.name = nfDialSector.label.getLabel();
                }
                else {
                    nfDataSeries.name = nfDialSector.text;
                }
                nfDataSeries.c = nfDialSector.color;
                super.legend.addDataSet(nfDataSeries);
            }
        }
    }
    
    private double[] a(final NFDialSector nfDialSector, final double n) {
        final double[] array = new double[2];
        if (nfDialSector == null || nfDialSector.dial == null) {
            return null;
        }
        double n2 = (nfDialSector.startValue - nfDialSector.dial.min) / (nfDialSector.dial.max - nfDialSector.dial.min) * (nfDialSector.dial.stopAngle - nfDialSector.dial.startAngle) + nfDialSector.dial.startAngle;
        double n3 = (nfDialSector.stopValue - nfDialSector.dial.min) / (nfDialSector.dial.max - nfDialSector.dial.min) * (nfDialSector.dial.stopAngle - nfDialSector.dial.startAngle) + nfDialSector.dial.startAngle;
        if (this.u == nfDialSector) {
            n2 += n;
            n3 += n;
        }
        final double n4 = n3 - n2;
        if (nfDialSector.dial.startAngle > nfDialSector.dial.stopAngle && Math.abs(nfDialSector.dial.stopAngle - nfDialSector.dial.startAngle) != 360.0) {
            if (nfDialSector.dial.stopAngle > n2) {
                n2 += 360.0;
            }
            if (nfDialSector.dial.startAngle < n2 + n4) {
                n2 -= 360.0;
            }
        }
        if (Math.abs(nfDialSector.dial.stopAngle - nfDialSector.dial.startAngle) != 360.0) {
            if (n2 > nfDialSector.dial.startAngle) {
                n2 = nfDialSector.dial.startAngle;
            }
            if (n2 < nfDialSector.dial.stopAngle && nfDialSector.dial.max < nfDialSector.dial.min) {
                n2 = nfDialSector.dial.stopAngle;
            }
            if (n2 + n4 < nfDialSector.dial.stopAngle) {
                n2 = nfDialSector.dial.stopAngle - n4;
            }
        }
        array[1] = (array[0] = n2) + n4;
        return array;
    }
    
    private void a(final NFDialSector nfDialSector) {
        if (nfDialSector != null) {
            final double[] a = this.a(nfDialSector, this.l);
            if (a != null) {
                nfDialSector.angle = a[0];
                nfDialSector.arc = a[1] - a[0];
            }
        }
    }
    
    private double a(final NFHand nfHand, final double n) {
        if (nfHand == null || nfHand.getDial() == null) {
            return 0.0;
        }
        final NFDial dial = nfHand.getDial();
        double n2 = (nfHand.getValue() - dial.min) / (dial.max - dial.min) * (dial.stopAngle - dial.startAngle) + dial.startAngle;
        if (this.t == nfHand) {
            n2 += n;
        }
        if (dial.startAngle > dial.stopAngle && Math.abs(dial.stopAngle - dial.startAngle) != 360.0) {
            if (dial.stopAngle > n2) {
                n2 += 360.0;
            }
            if (dial.startAngle < n2) {
                n2 -= 360.0;
            }
        }
        if (Math.abs(dial.stopAngle - dial.startAngle) != 360.0) {
            if (n2 > dial.startAngle) {
                n2 = dial.startAngle;
            }
            if (n2 < dial.stopAngle) {
                n2 = dial.stopAngle;
            }
        }
        return n2;
    }
    
    private void a(final NFHand nfHand) {
        if (nfHand != null) {
            nfHand.setAngle(this.a(nfHand, this.k));
        }
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle rectangle) {
        Rectangle p2 = new Rectangle();
        final Dimension dimension = new Dimension(rectangle.width, rectangle.height);
        final Dimension dimension2 = new Dimension(0, 0);
        double ticLabelPos = 1.0;
        double ticLabelPos2 = 1.0;
        final Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.c.size() == 0) {
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
        if (!this.q && !this.r && !this.s && this.o != null && this.p != null && this.o.x == rectangle.x && this.o.y == rectangle.y && this.o.width == rectangle.width && this.o.height == rectangle.height) {
            p2 = this.p;
            this.a(this.u);
            this.a(this.t);
        }
        else {
            for (int i = 0; i < this.c.size(); ++i) {
                final NFDial nfDial = this.c.elementAt(i);
                final Rectangle rectangle3 = new Rectangle();
                rectangle3.width = (int)(rectangle.width * nfDial.radiusPercentage);
                rectangle3.height = (int)(rectangle.height * nfDial.radiusPercentage);
                rectangle3.x = rectangle.x + (rectangle.width - rectangle3.width) / 2;
                rectangle3.y = rectangle.y + (rectangle.height - rectangle3.height) / 2;
                if (nfDial.ticLabelPos >= 1.0 && this.a(nfDial.ticLabel)) {
                    Dimension a = new Dimension(0, 0);
                    final Dimension dimension3 = new Dimension(0, 0);
                    final boolean b = nfDial.ticLabels == null || nfDial.ticLabels.size() == 0;
                    for (int j = 0; j < nfDial.spacing.size(); ++j) {
                        final double nthValue = nfDial.spacing.getNthValue(j);
                        final double n = (nthValue - nfDial.min) / (nfDial.max - nfDial.min) * (nfDial.stopAngle - nfDial.startAngle) + nfDial.startAngle;
                        final String label = nfDial.ticLabel.getLabel();
                        final String label2 = b ? NFUtil.formatNumericValue(nthValue, nfDial.formatType, nfDial.formatStr, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol()) : NFUtil.getString(nfDial.ticLabels, j, null);
                        if (label2 != null) {
                            nfDial.ticLabel.setLabel(label2);
                            a = this.a(graphics, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, n, 0.0, nfDial.ticLabel, a, nfDial.ticLabelPos, dimension3);
                            nfDial.ticLabel.setLabel(label);
                        }
                    }
                    final Dimension dimension4 = new Dimension(rectangle3.width, rectangle3.height);
                    if (nfDial.ticLabelPos <= 1.0) {
                        final Dimension dimension5 = dimension4;
                        dimension5.width += 2 * a.width;
                        final Dimension dimension6 = dimension4;
                        dimension6.height += 2 * a.height;
                    }
                    else {
                        dimension4.width = (int)(dimension4.width * nfDial.ticLabelPos + 2 * a.width);
                        dimension4.height = (int)(dimension4.height * nfDial.ticLabelPos + 2 * a.height);
                    }
                    if (dimension4.width > dimension.width) {
                        dimension.width = dimension4.width;
                        dimension2.width = dimension3.width;
                        ticLabelPos = nfDial.ticLabelPos;
                    }
                    if (dimension4.height > dimension.height) {
                        dimension.height = dimension4.height;
                        dimension2.height = dimension3.height;
                        ticLabelPos2 = nfDial.ticLabelPos;
                    }
                }
                final Enumeration elements = nfDial.sectors.elements();
                while (elements.hasMoreElements()) {
                    this.a(elements.nextElement());
                }
                this.q = false;
                final Enumeration elements2 = nfDial.hands.elements();
                while (elements2.hasMoreElements()) {
                    this.a(elements2.nextElement());
                }
                this.r = false;
                if (nfDial.sectors != null && nfDial.sectors.size() > 0) {
                    Dimension a2 = new Dimension(0, 0);
                    final Dimension dimension7 = new Dimension(0, 0);
                    final Dimension dimension8 = new Dimension(rectangle3.width, rectangle3.height);
                    double n2 = 1.0;
                    double n3 = 1.0;
                    final Enumeration elements3 = nfDial.sectors.elements();
                    while (elements3.hasMoreElements()) {
                        final Dimension dimension9 = new Dimension(0, 0);
                        final NFDialSector nfDialSector = elements3.nextElement();
                        this.a(nfDialSector, rectangle3);
                        if (this.a(nfDialSector.label)) {
                            a2 = this.a(graphics, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, nfDialSector.angle, nfDialSector.arc, nfDialSector.label, a2, nfDialSector.labelPos, dimension9);
                            if (nfDialSector.labelPos <= 1.0) {
                                if (rectangle3.width + 2 * a2.width > dimension8.width) {
                                    dimension8.width = rectangle3.width + 2 * a2.width;
                                    n2 = nfDialSector.labelPos;
                                    dimension7.width = dimension9.width;
                                }
                                if (rectangle3.height + 2 * a2.height <= dimension8.height) {
                                    continue;
                                }
                                dimension8.height = rectangle3.height + 2 * a2.height;
                                n3 = nfDialSector.labelPos;
                                dimension7.height = dimension9.height;
                            }
                            else {
                                if ((int)(rectangle3.width * nfDialSector.labelPos + 2 * a2.width) > dimension8.width) {
                                    dimension8.width = (int)(rectangle3.width * nfDialSector.labelPos + 2 * a2.width);
                                    n2 = nfDialSector.labelPos;
                                    dimension7.width = dimension9.width;
                                }
                                if ((int)(rectangle3.height * nfDialSector.labelPos + 2 * a2.height) <= dimension8.height) {
                                    continue;
                                }
                                dimension8.height = (int)(rectangle3.height * nfDialSector.labelPos + 2 * a2.height);
                                n3 = nfDialSector.labelPos;
                                dimension7.height = dimension9.height;
                            }
                        }
                    }
                    if (dimension8.width > dimension.width) {
                        dimension.width = dimension8.width;
                        dimension2.width = dimension7.width;
                        ticLabelPos = n2;
                    }
                    if (dimension8.height > dimension.height) {
                        dimension.height = dimension8.height;
                        dimension2.height = dimension7.height;
                        ticLabelPos2 = n3;
                    }
                    final Enumeration elements4 = nfDial.sectors.elements();
                    a2.width = 0;
                    a2.height = 0;
                    dimension8.width = rectangle3.width;
                    dimension8.height = rectangle3.height;
                    double orp = 1.0;
                    double orp2 = 1.0;
                    while (elements4.hasMoreElements()) {
                        final NFDialSector nfDialSector2 = elements4.nextElement();
                        int thickness;
                        if (nfDialSector2.borderLine != null) {
                            thickness = nfDialSector2.borderLine.getThickness();
                            if (thickness < 1) {
                                thickness = 1;
                            }
                        }
                        else {
                            thickness = 0;
                        }
                        final double n4 = nfDialSector2.orp * rectangle3.width;
                        final double n5 = nfDialSector2.orp * rectangle3.height;
                        if (n4 + thickness > dimension8.width) {
                            dimension8.width = (int)NFUtil.rint(n4 + thickness);
                            a2.width = (int)NFUtil.rint(thickness / 2.0);
                            orp = nfDialSector2.orp;
                        }
                        if (n5 + thickness > dimension8.height) {
                            dimension8.height = (int)NFUtil.rint(n5 + thickness);
                            a2.height = (int)NFUtil.rint(thickness / 2.0);
                            orp2 = nfDialSector2.orp;
                        }
                    }
                    if (dimension8.width > dimension.width) {
                        dimension.width = dimension8.width;
                        dimension2.width = a2.width;
                        ticLabelPos = orp;
                    }
                    if (dimension8.height > dimension.height) {
                        dimension.height = dimension8.height;
                        dimension2.height = a2.height;
                        ticLabelPos2 = orp2;
                    }
                }
                if (nfDial.ticLocation == 2) {
                    final Dimension dimension10 = new Dimension(rectangle3.width, rectangle3.height);
                    dimension10.width = (int)((1.0 + nfDial.ticLength) * rectangle3.width);
                    dimension10.height = (int)((1.0 + nfDial.ticLength) * rectangle3.height);
                    if (dimension10.width > dimension.width) {
                        dimension.width = dimension10.width;
                        ticLabelPos = 1.0 + nfDial.ticLength;
                    }
                    if (dimension10.height > dimension.height) {
                        dimension.height = dimension10.height;
                        ticLabelPos2 = 1.0 + nfDial.ticLength;
                    }
                }
            }
            p2.width = (int)((rectangle.width - 2 * dimension2.width) / ticLabelPos);
            p2.height = (int)((rectangle.height - 2 * dimension2.height) / ticLabelPos2);
            p2.x = rectangle.x + (rectangle.width - p2.width) / 2;
            p2.y = rectangle.y + (rectangle.height - p2.height) / 2;
            dimension.width = p2.width;
            dimension.height = p2.height;
            dimension2.width = 0;
            dimension2.height = 0;
            double radiusPercentage = 1.0;
            double radiusPercentage2 = 1.0;
            for (int k = 0; k < this.c.size(); ++k) {
                final NFDial nfDial2 = this.c.elementAt(k);
                final Rectangle rectangle4 = new Rectangle();
                rectangle4.width = (int)(p2.width * nfDial2.radiusPercentage);
                rectangle4.height = (int)(p2.height * nfDial2.radiusPercentage);
                rectangle4.x = p2.x + (p2.width - rectangle4.width) / 2;
                rectangle4.y = p2.y + (p2.height - rectangle4.height) / 2;
                if (nfDial2.borderLine != null) {
                    int thickness2 = nfDial2.borderLine.getThickness();
                    if (thickness2 < 1) {
                        thickness2 = 1;
                    }
                    if (rectangle4.width + thickness2 > dimension.width) {
                        dimension2.width = thickness2;
                        dimension.width = rectangle4.width + thickness2;
                        radiusPercentage = nfDial2.radiusPercentage;
                    }
                    if (rectangle4.height + thickness2 > dimension.height) {
                        dimension2.height = thickness2;
                        dimension.height = rectangle4.height + thickness2;
                        radiusPercentage2 = nfDial2.radiusPercentage;
                    }
                }
            }
            if (dimension2.width > 0 || dimension2.height > 0) {
                final int width = (int)((p2.width - dimension2.width) / radiusPercentage);
                final int height = (int)((p2.height - dimension2.height) / radiusPercentage2);
                p2.x += (p2.width - width) / 2;
                p2.y += (p2.height - height) / 2;
                p2.width = width;
                p2.height = height;
            }
        }
        this.resetDialActiveLabels();
        this.resetSectorActiveLabels();
        this.resetHandActiveLabels();
        switch (this.i) {
            case 1: {
                p2.x = -p2.width;
                p2.width *= 2;
                p2.height *= 2;
                break;
            }
            case 2: {
                p2.height *= 2;
                break;
            }
            case 3: {
                p2.width *= 2;
                p2.height *= 2;
                break;
            }
            case 4: {
                p2.width *= 2;
                break;
            }
            case 5: {
                p2.y = -p2.height;
                p2.width *= 2;
                p2.height *= 2;
                break;
            }
            case 6: {
                p2.y = -p2.height;
                p2.height *= 2;
                break;
            }
            case 7: {
                p2.x = -p2.width;
                p2.y = -p2.height;
                p2.width *= 2;
                p2.height *= 2;
                break;
            }
            case 8: {
                p2.x = -p2.width;
                p2.width *= 2;
                break;
            }
        }
        for (int l = 0; l < this.c.size(); ++l) {
            final NFDial nfDial3 = this.c.elementAt(l);
            final Rectangle rectangle5 = new Rectangle();
            rectangle5.width = (int)(p2.width * nfDial3.radiusPercentage);
            rectangle5.height = (int)(p2.height * nfDial3.radiusPercentage);
            rectangle5.x = p2.x + (p2.width - rectangle5.width) / 2;
            rectangle5.y = p2.y + (p2.height - rectangle5.height) / 2;
            if (rectangle5.width <= 0 || rectangle5.height <= 0) {
                NFDebug.print("*** Insufficient room to display graph ***");
                this.fireGraphTooSmall(new Dimension(rectangle2.width, rectangle2.height));
                return;
            }
            this.m = rectangle5.x + rectangle5.width / 2;
            this.n = rectangle5.y + rectangle5.height / 2;
            this.a(nfDial3.border = new Polygon(), rectangle5, nfDial3.startAngle, nfDial3.stopAngle - nfDial3.startAngle, 0, true);
            nfDial3.fill = new Polygon(nfDial3.border.xpoints, nfDial3.border.ypoints, nfDial3.border.npoints);
            final Polygon bounds = new Polygon(nfDial3.border.xpoints, nfDial3.border.ypoints, nfDial3.border.npoints);
            if (nfDial3.borderStyle == 3 && Math.abs(nfDial3.stopAngle - nfDial3.startAngle) != 360.0) {
                nfDial3.border.addPoint(this.m, this.n);
                nfDial3.border.addPoint(nfDial3.border.xpoints[0], nfDial3.border.ypoints[0]);
            }
            else if (nfDial3.borderStyle == 4 && Math.abs(nfDial3.stopAngle - nfDial3.startAngle) != 360.0) {
                nfDial3.border.addPoint(nfDial3.border.xpoints[0], nfDial3.border.ypoints[0]);
            }
            if (nfDial3.fillStyle == 3) {
                nfDial3.fill.addPoint(this.m, this.n);
            }
            if (nfDial3.fillStyle == 4 || nfDial3.borderStyle == 4) {
                bounds.addPoint(bounds.xpoints[0], bounds.ypoints[0]);
            }
            else if (Math.abs(nfDial3.stopAngle - nfDial3.startAngle) != 360.0) {
                bounds.addPoint(this.m, this.n);
                bounds.addPoint(bounds.xpoints[0], bounds.ypoints[0]);
            }
            if (this.getDialActiveLabel(nfDial3) != null) {
                this.getDialActiveLabel(nfDial3).setBounds(bounds);
            }
            if (nfDial3.fillColor != null && nfDial3.fillStyle != 0) {
                graphics.setColor(nfDial3.fillColor);
                graphics.fillArc(rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height, (int)NFUtil.rint(nfDial3.startAngle), (int)NFUtil.rint(nfDial3.stopAngle - nfDial3.startAngle));
                if (NFUtil.getJDKVersion() >= 1.2 && nfDial3.pattern != null && nfDial3.pattern.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, nfDial3.fill, (nfDial3.borderLine != null) ? nfDial3.borderLine.getColor() : null, nfDial3.pattern);
                }
            }
            final Enumeration elements5 = nfDial3.sectors.elements();
            while (elements5.hasMoreElements()) {
                final NFDialSector nfDialSector3 = elements5.nextElement();
                this.a(graphics, nfDialSector3, rectangle5, nfDial3.fillColor);
                if (this.a(nfDialSector3.label)) {
                    this.a(graphics, rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height, nfDialSector3.angle, nfDialSector3.arc, nfDialSector3.label, nfDialSector3.labelPos);
                }
            }
            if (nfDial3.ticLocation != 0) {
                for (int n6 = 0; n6 < nfDial3.spacing.size(); ++n6) {
                    final double n7 = 3.141592653589793 * (((nfDial3.spacing.getNthValue(n6) - nfDial3.min) / (nfDial3.max - nfDial3.min) * (nfDial3.stopAngle - nfDial3.startAngle) + nfDial3.startAngle) / 180.0);
                    final int n8 = rectangle5.width / 2;
                    final int n9 = rectangle5.height / 2;
                    int n10;
                    int n11;
                    if (nfDial3.ticLocation == 2) {
                        n10 = this.m + (int)NFUtil.rint(Math.cos(n7) * n8 * (1.0 + nfDial3.ticLength));
                        n11 = this.n - (int)NFUtil.rint(Math.sin(n7) * n9 * (1.0 + nfDial3.ticLength));
                    }
                    else {
                        n10 = this.m + (int)NFUtil.rint(Math.cos(n7) * n8 * (1.0 - nfDial3.ticLength));
                        n11 = this.n - (int)NFUtil.rint(Math.sin(n7) * n9 * (1.0 - nfDial3.ticLength));
                    }
                    NFLine.draw(graphics, n10, n11, this.m + (int)NFUtil.rint(Math.cos(n7) * n8), this.n - (int)NFUtil.rint(Math.sin(n7) * n9), nfDial3.ticWidth, 1, nfDial3.ticColor, null, null, super.scale);
                }
            }
            if (nfDial3.borderLine != null) {
                if (nfDial3.borderLine.getThickness() == 1) {
                    final Color color = graphics.getColor();
                    graphics.setColor(nfDial3.borderLine.getColor());
                    graphics.drawArc(rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height, (int)NFUtil.rint(nfDial3.startAngle), (int)NFUtil.rint(nfDial3.stopAngle - nfDial3.startAngle));
                    if (nfDial3.borderStyle != 0 && Math.abs(nfDial3.stopAngle - nfDial3.startAngle) < 360.0) {
                        final int npoints = nfDial3.border.npoints;
                        final Polygon border = nfDial3.border;
                        if (nfDial3.borderStyle == 3) {
                            if (npoints >= 3) {
                                graphics.drawLine(border.xpoints[npoints - 1], border.ypoints[npoints - 1], border.xpoints[npoints - 2], border.ypoints[npoints - 2]);
                                graphics.drawLine(border.xpoints[npoints - 2], border.ypoints[npoints - 2], border.xpoints[npoints - 3], border.ypoints[npoints - 3]);
                            }
                        }
                        else if (npoints >= 2) {
                            graphics.drawLine(border.xpoints[npoints - 1], border.ypoints[npoints - 1], border.xpoints[npoints - 2], border.ypoints[npoints - 2]);
                        }
                    }
                    graphics.setColor(color);
                }
                else {
                    nfDial3.borderLine.drawPoly(graphics, nfDial3.border);
                }
            }
            if (nfDial3.ticLabel != null && this.a(nfDial3.ticLabel)) {
                final boolean b2 = nfDial3.ticLabels == null || nfDial3.ticLabels.size() == 0;
                for (int n12 = 0; n12 < nfDial3.spacing.size(); ++n12) {
                    final double nthValue2 = nfDial3.spacing.getNthValue(n12);
                    final double n13 = (nthValue2 - nfDial3.min) / (nfDial3.max - nfDial3.min) * (nfDial3.stopAngle - nfDial3.startAngle) + nfDial3.startAngle;
                    final String label3 = nfDial3.ticLabel.getLabel();
                    final String label4 = b2 ? NFUtil.formatNumericValue(nthValue2, nfDial3.formatType, nfDial3.formatStr, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol()) : NFUtil.getString(nfDial3.ticLabels, n12, null);
                    if (label4 != null) {
                        nfDial3.ticLabel.setLabel(label4);
                        if (this.a(nfDial3.ticLabel)) {
                            this.a(graphics, rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height, n13, 0.0, nfDial3.ticLabel, nfDial3.ticLabelPos);
                        }
                        nfDial3.ticLabel.setLabel(label3);
                    }
                }
            }
            final Enumeration elements6 = nfDial3.hands.elements();
            while (elements6.hasMoreElements()) {
                final NFHand nfHand = elements6.nextElement();
                nfHand.draw(graphics, rectangle5);
                final NFActiveLabel handActiveLabel = this.getHandActiveLabel(nfHand);
                if (handActiveLabel != null) {
                    final Polygon activePoly = nfHand.getActivePoly();
                    if (activePoly != null) {
                        handActiveLabel.setBounds(activePoly);
                    }
                    else {
                        final Point destination = nfHand.getDestination();
                        if (destination == null) {
                            continue;
                        }
                        handActiveLabel.setBounds(destination.x - 10, destination.y - 10, 20, 20);
                    }
                }
            }
        }
        this.o = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.p = p2;
        this.s = false;
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, null);
        }
    }
    
    private void a(final NFDialSector nfDialSector, final Rectangle rectangle) {
        nfDialSector.rect.x = rectangle.x;
        nfDialSector.rect.y = rectangle.y;
        nfDialSector.rect.width = rectangle.width;
        nfDialSector.rect.height = rectangle.height;
        nfDialSector.center.x = nfDialSector.rect.x + nfDialSector.rect.width / 2;
        nfDialSector.center.y = nfDialSector.rect.y + nfDialSector.rect.height / 2;
    }
    
    private void a(final Polygon polygon, final Rectangle rectangle, double n, final double n2, final int n3, final boolean b) {
        final int n4 = rectangle.width / 2;
        final int n5 = rectangle.height / 2;
        final int n6 = rectangle.x + n4;
        final int n7 = rectangle.y + n5;
        final double n8 = n;
        final double n9 = n + n2;
        final double n10 = 1.0;
        if (b) {
            do {
                final double n11 = 3.141592653589793 * (n / 180.0);
                polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n11) * n4), n7 - (int)NFUtil.rint(Math.sin(n11) * n5) + n3);
                if (n2 < 0.0) {
                    n -= n10;
                }
                else {
                    n += n10;
                }
            } while ((n2 < 0.0) ? (n > n9) : (n < n9));
            final double n12 = 3.141592653589793 * (n9 / 180.0);
            polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n12) * n4), n7 - (int)NFUtil.rint(Math.sin(n12) * n5) + n3);
        }
        else {
            n += n2;
            do {
                final double n13 = 3.141592653589793 * (n / 180.0);
                polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n13) * n4), n7 - (int)NFUtil.rint(Math.sin(n13) * n5) + n3);
                if (n2 < 0.0) {
                    n += n10;
                }
                else {
                    n -= n10;
                }
            } while ((n2 < 0.0) ? (n < n8) : (n > n8));
            final double n14 = 3.141592653589793 * (n8 / 180.0);
            polygon.addPoint(n6 + (int)NFUtil.rint(Math.cos(n14) * n4), n7 - (int)NFUtil.rint(Math.sin(n14) * n5) + n3);
        }
    }
    
    private void a(final Graphics graphics, final NFDialSector nfDialSector, final Rectangle rectangle, final Color color) {
        final int n = rectangle.x + rectangle.width / 2;
        final int n2 = rectangle.y + rectangle.height / 2;
        nfDialSector.poly = new Polygon();
        final Rectangle rectangle2 = new Rectangle();
        rectangle2.width = (int)(nfDialSector.orp * rectangle.width);
        rectangle2.height = (int)(nfDialSector.orp * rectangle.height);
        rectangle2.x = rectangle.x + (rectangle.width - rectangle2.width) / 2;
        rectangle2.y = rectangle.y + (rectangle.height - rectangle2.height) / 2;
        this.a(nfDialSector, rectangle2);
        this.a(nfDialSector.poly, rectangle2, nfDialSector.angle, nfDialSector.arc, 0, true);
        final Rectangle rectangle3 = new Rectangle();
        rectangle3.width = (int)(nfDialSector.irp * rectangle.width);
        rectangle3.height = (int)(nfDialSector.irp * rectangle.height);
        rectangle3.x = rectangle.x + (rectangle.width - rectangle3.width) / 2;
        rectangle3.y = rectangle.y + (rectangle.height - rectangle3.height) / 2;
        this.a(nfDialSector.poly, rectangle3, nfDialSector.angle, nfDialSector.arc, 0, false);
        nfDialSector.poly.addPoint(nfDialSector.poly.xpoints[0], nfDialSector.poly.ypoints[0]);
        graphics.setColor(nfDialSector.color);
        graphics.fillArc(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, (int)NFUtil.rint(nfDialSector.angle), (int)NFUtil.rint(nfDialSector.arc));
        if (NFUtil.getJDKVersion() >= 1.2 && nfDialSector.pattern != null && nfDialSector.pattern.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, nfDialSector.poly, (nfDialSector.borderLine != null) ? nfDialSector.borderLine.getColor() : null, nfDialSector.pattern);
        }
        graphics.setColor(color);
        graphics.fillArc(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, (int)NFUtil.rint(nfDialSector.angle), (int)NFUtil.rint(nfDialSector.arc));
        if (nfDialSector.borderLine != null) {
            if (nfDialSector.borderLine.getThickness() == 1) {
                final Color color2 = graphics.getColor();
                graphics.setColor(nfDialSector.borderLine.getColor());
                graphics.drawArc(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, (int)nfDialSector.angle, (int)nfDialSector.arc);
                graphics.setColor(color2);
            }
            else {
                nfDialSector.borderLine.drawPoly(graphics, nfDialSector.poly);
            }
        }
        if (this.getSectorActiveLabel(nfDialSector) != null) {
            this.getSectorActiveLabel(nfDialSector).setBounds(nfDialSector.poly);
        }
    }
    
    private void a(final Graphics graphics, int n, int n2, int n3, int n4, double n5, final double n6, final NFLabel nfLabel, final double n7) {
        for (n5 = (n5 + n6 / 2.0) % 360.0; n5 < 0.0; n5 += 360.0) {}
        while (n5 > 360.0) {
            n5 -= 360.0;
        }
        n += n3 / 2;
        n2 += n4 / 2;
        n3 /= 2;
        n4 /= 2;
        final Point a = this.a(n, n2, n3, n4, n5, n7);
        if (n7 > 1.0) {
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
        nfLabel.draw(graphics);
    }
    
    private Dimension a(final Graphics graphics, int n, int n2, int n3, int n4, double n5, final double n6, final NFLabel nfLabel, final Dimension dimension, final double n7, final Dimension dimension2) {
        final Dimension bounds = nfLabel.getBounds(graphics);
        if (n7 > 1.0) {
            final int width = (int)((n3 * n7 + bounds.width / 2 - n3) / 2.0);
            final int height = (int)((n4 * n7 + bounds.height / 2 - n4) / 2.0);
            if (width > dimension.width) {
                dimension.width = width;
                dimension2.width = bounds.width;
            }
            if (height > dimension.height) {
                dimension.height = height;
                dimension2.height = bounds.height;
            }
            return dimension;
        }
        n5 = (n5 + n6 / 2.0) % 360.0;
        final int n8 = n;
        final int n9 = n + n3;
        final int n10 = n2;
        final int n11 = n2 + n4;
        n += n3 / 2;
        n2 += n4 / 2;
        n3 /= 2;
        n4 /= 2;
        if (n7 <= 1.0) {
            bounds.width /= 2;
            bounds.height /= 2;
        }
        final int width2 = this.a(n, n2, n3, n4, 0.0, n7).x + bounds.width - n9;
        if (width2 > dimension.width) {
            dimension.width = width2;
            dimension2.width = nfLabel.getBounds(graphics).width;
        }
        final int width3 = n8 - (this.a(n, n2, n3, n4, 180.0, n7).x - bounds.width);
        if (width3 > dimension.width) {
            dimension.width = width3;
            dimension2.width = nfLabel.getBounds(graphics).width;
        }
        final int height2 = n10 - (this.a(n, n2, n3, n4, 90.0, n7).y - bounds.height);
        if (height2 > dimension.height) {
            dimension.height = height2;
            dimension2.height = nfLabel.getBounds(graphics).height;
        }
        final int height3 = this.a(n, n2, n3, n4, 270.0, n7).y + bounds.height - n11;
        if (height3 > dimension.height) {
            dimension.height = height3;
            dimension2.height = nfLabel.getBounds(graphics).height;
        }
        return dimension;
    }
    
    private Point a(final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        final double n7 = 3.141592653589793 * (n5 / 180.0);
        final Point point = new Point(0, 0);
        point.x = n + (int)NFUtil.rint(Math.cos(n7) * n3 * n6);
        point.y = n2 - (int)NFUtil.rint(Math.sin(n7) * n4 * n6);
        return point;
    }
    
    private NFDialSector a(final int n, final int n2) {
        for (int i = this.c.size() - 1; i >= 0; --i) {
            final NFDial nfDial = this.c.elementAt(i);
            for (int j = nfDial.sectors.size() - 1; j >= 0; --j) {
                final NFDialSector nfDialSector = nfDial.sectors.elementAt(j);
                if (nfDialSector.poly != null && nfDialSector.poly.inside(n, n2)) {
                    return nfDialSector;
                }
            }
        }
        return null;
    }
    
    private NFHand b(final int n, final int n2) {
        for (int i = this.c.size() - 1; i >= 0; --i) {
            final NFDial nfDial = this.c.elementAt(i);
            for (int j = nfDial.hands.size() - 1; j >= 0; --j) {
                final NFHand nfHand = nfDial.hands.elementAt(j);
                if (nfHand.contains(n, n2)) {
                    return nfHand;
                }
            }
        }
        return null;
    }
    
    public synchronized boolean mouseDown(final Event event, final int v, final int w) {
        if (super.mouseDown(event, v, w)) {
            return true;
        }
        if (!this.h && !this.g) {
            return true;
        }
        this.x = true;
        this.c(v, w);
        this.v = v;
        this.w = w;
        return true;
    }
    
    private void c(final int n, final int n2) {
        for (int i = this.c.size() - 1; i >= 0; --i) {
            final NFDial nfDial = this.c.elementAt(i);
            for (int j = nfDial.hands.size() - 1; j >= 0; --j) {
                final NFHand t = nfDial.hands.elementAt(j);
                if (t.contains(n, n2)) {
                    if (this.h) {
                        this.t = t;
                    }
                    else {
                        this.t = null;
                    }
                    this.u = null;
                    this.y = this.k;
                    return;
                }
            }
            for (int k = nfDial.sectors.size() - 1; k >= 0; --k) {
                final NFDialSector u = nfDial.sectors.elementAt(k);
                if (u.poly != null && u.poly.inside(n, n2)) {
                    if (this.g) {
                        this.u = u;
                    }
                    else {
                        this.u = null;
                    }
                    this.t = null;
                    this.y = this.l;
                    return;
                }
            }
        }
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.x) {
            this.d(n, n2);
            return true;
        }
        return super.mouseDrag(event, n, n2);
    }
    
    public boolean abortPreDrag(final double n, final double n2, final double n3, final double n4) {
        if (super.observers == null) {
            return false;
        }
        final Enumeration<NFDragObserver> elements = (Enumeration<NFDragObserver>)super.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            final boolean b = true;
            if (this.z != null) {
                nextElement.preDrag(this.z, n, n2, n3, n4);
            }
            else if (this.aa != null) {
                nextElement.preDrag(this.aa, n, n2, n3, n4);
            }
            if (!b) {
                this.z = null;
                this.aa = null;
                return true;
            }
        }
        this.z = null;
        this.aa = null;
        return false;
    }
    
    public void notifyPostDrag(final double n, final double n2) {
        if (super.observers == null || (this.z == null && this.aa == null)) {
            return;
        }
        final Enumeration<NFDragObserver> elements = super.observers.elements();
        while (elements.hasMoreElements()) {
            final NFDragObserver nextElement = elements.nextElement();
            if (!(nextElement instanceof NFDragObserver)) {
                continue;
            }
            if (this.z != null) {
                nextElement.postDrag(this.z, n, n2);
            }
            else {
                if (this.aa == null) {
                    continue;
                }
                nextElement.postDrag(this.aa, n, n2);
            }
        }
        this.z = null;
        this.aa = null;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.x) {
            this.d(n, n2);
            if (this.u != null) {
                final double n3 = (this.u.angle - this.u.dial.startAngle) / (this.u.dial.stopAngle - this.u.dial.startAngle) * (this.u.dial.max - this.u.dial.min) + this.u.dial.min;
                final double n4 = n3 + (this.u.stopValue - this.u.startValue);
                this.addSectorData(this.u, n3, n4);
                this.z = this.u;
                this.u = null;
                if (this.y != this.l) {
                    this.notifyPostDrag(n3, n4);
                }
                this.l = 0;
            }
            else if (this.t != null) {
                final NFDial dial = this.t.getDial();
                final double value = (this.t.getAngle() - dial.startAngle) / (dial.stopAngle - dial.startAngle) * (dial.max - dial.min) + dial.min;
                this.t.setValue(value);
                this.aa = this.t;
                this.t = null;
                if (this.y != this.k) {
                    this.notifyPostDrag(value, value);
                }
                this.k = 0;
            }
            this.x = false;
        }
        return super.mouseUp(event, n, n2);
    }
    
    private void d(final int v, final int w) {
        if (v == this.v && w == this.w) {
            return;
        }
        final int n = (int)((Math.atan2(v - this.m, w - this.n) - Math.atan2(this.v - this.m, this.w - this.n)) / 6.283185307179586 * 360.0);
        if (n > 4 || n < -4) {
            if (!this.a(n)) {
                return;
            }
            this.v = v;
            this.w = w;
        }
    }
    
    private boolean a(final int n) {
        int i = 0;
        if (this.u != null) {
            i = this.l + n;
        }
        else if (this.t != null) {
            i = this.k + n;
        }
        while (i < 0) {
            i += 360;
        }
        while (i >= 360) {
            i -= 360;
        }
        if (this.u != null) {
            this.z = this.u;
            final double[] a = this.a(this.u, this.l);
            final double[] a2 = this.a(this.u, i);
            if (a != null && a2 != null && this.abortPreDrag((a[0] - this.u.dial.startAngle) / (this.u.dial.stopAngle - this.u.dial.startAngle) * (this.u.dial.max - this.u.dial.min) + this.u.dial.min, (a[1] - this.u.dial.startAngle) / (this.u.dial.stopAngle - this.u.dial.startAngle) * (this.u.dial.max - this.u.dial.min) + this.u.dial.min, (a2[0] - this.u.dial.startAngle) / (this.u.dial.stopAngle - this.u.dial.startAngle) * (this.u.dial.max - this.u.dial.min) + this.u.dial.min, (a2[1] - this.u.dial.startAngle) / (this.u.dial.stopAngle - this.u.dial.startAngle) * (this.u.dial.max - this.u.dial.min) + this.u.dial.min)) {
                this.u = null;
                return false;
            }
        }
        else if (this.t != null) {
            this.aa = this.t;
            final NFDial dial = this.t.getDial();
            final double n2 = (this.a(this.t, this.k) - dial.startAngle) / (dial.stopAngle - dial.startAngle) * (dial.max - dial.min) + dial.min;
            final double n3 = (this.a(this.t, i) - dial.startAngle) / (dial.stopAngle - dial.startAngle) * (dial.max - dial.min) + dial.min;
            if (this.abortPreDrag(n2, n2, n3, n3)) {
                this.t = null;
                return false;
            }
        }
        if (this.u != null) {
            this.l = i;
        }
        else if (this.t != null) {
            this.k = i;
        }
        this.display(true);
        return true;
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        super.param.defineChartType("ChartType", 12);
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NONE", new Integer(0));
        hashtable.put("TOPRIGHT", new Integer(1));
        hashtable.put("TOP", new Integer(2));
        hashtable.put("TOPLEFT", new Integer(3));
        hashtable.put("LEFT", new Integer(4));
        hashtable.put("BOTTOMLEFT", new Integer(5));
        hashtable.put("BOTTOM", new Integer(6));
        hashtable.put("BOTTOMRIGHT", new Integer(7));
        hashtable.put("RIGHT", new Integer(8));
        super.param.defineSymbol("DialClip", hashtable, new Integer(0));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("Dial-name", null));
        vector.addElement(super.param.defineNumber("Dial-startAngle", new Double(0.0)));
        vector.addElement(super.param.defineNumber("Dial-stopAngle", new Double(0.0)));
        vector.addElement(super.param.defineNumber("Dial-radius", new Double(0.0)));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("NONE", new Integer(0));
        hashtable2.put("INSIDE", new Integer(1));
        hashtable2.put("OUTSIDE", new Integer(2));
        vector.addElement(super.param.defineSymbol("Dial-ticLocation", hashtable2, new Integer(1)));
        super.param.defineVector("Dials", super.param.defineTuple("Dial", vector));
        final Hashtable<String, Integer> hashtable3 = new Hashtable<String, Integer>();
        hashtable3.put("FLOAT", new Integer(2));
        hashtable3.put("INTEGER", new Integer(1));
        hashtable3.put("DECIMAL", new Integer(5));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineString("DialFormat-name", null));
        vector2.addElement(super.param.defineSymbol("DialFormatType", hashtable3, new Integer(2)));
        vector2.addElement(super.param.defineString("DialFormatStr"));
        super.param.defineVector("DialFormats", super.param.defineTuple("DialFormat", vector2));
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineString("DialTic-name", null));
        vector3.addElement(super.param.defineColor("DialTic-color", Color.black));
        vector3.addElement(super.param.defineNumber("DialTic-width", new Integer(1)));
        vector3.addElement(super.param.defineNumber("DialTic-length", new Double(5.0)));
        super.param.defineVector("DialTics", super.param.defineTuple("DialTic", vector3));
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        super.param.defineLabel("DialTicLabelStyle", vector4);
        vector4.insertElementAt(super.param.defineString("DialTicStyles-name", null), 0);
        vector4.insertElementAt(super.param.defineNumber("DialTicStyles-labelPos", new Double(1.1)), 2);
        super.param.defineVector("DialTicLabelStyles", super.param.defineTuple("DialTicLabelStyle", vector4));
        final Vector<NFParamDef> vector5 = new Vector<NFParamDef>();
        vector5.addElement(super.param.defineString("DialTicLabel-name", null));
        super.param.defineVector("DialTicLabels", super.param.defineTuple("DialTicLabel", vector5, true));
        final Vector<NFParamDef> vector6 = new Vector<NFParamDef>();
        vector6.addElement(super.param.defineString("DialScale-name", null));
        vector6.addElement(super.param.defineNumber("DialScale-min", new Double(0.0)));
        vector6.addElement(super.param.defineNumber("DialScale-max", new Double(10.0)));
        vector6.addElement(super.param.defineNumber("DialScale-step", new Double(1.0)));
        super.param.defineVector("DialScale", super.param.defineTuple("DialScaleTuple", vector6));
        final Vector<NFParamDef> vector7 = new Vector<NFParamDef>();
        vector7.addElement(super.param.defineString("DialBorder-name", null));
        super.param.defineLine("DialBorder", vector7);
        final Hashtable<String, Integer> hashtable4 = new Hashtable<String, Integer>();
        hashtable4.put("NONE", new Integer(0));
        hashtable4.put("CENTER", new Integer(3));
        hashtable4.put("ENDTOEND", new Integer(4));
        vector7.addElement(super.param.defineSymbol("DialBorder-style", hashtable4, new Integer(0)));
        super.param.defineVector("DialBorders", super.param.defineTuple("DialBorder", vector7));
        final Vector<NFParamDef> vector8 = new Vector<NFParamDef>();
        vector8.addElement(super.param.defineString("DialFill-name", null));
        vector8.addElement(super.param.defineColor("DialFill-color", null));
        vector8.addElement(super.param.defineSymbol("DialFill-style", hashtable4, new Integer(3)));
        super.param.defineVector("DialFills", super.param.defineTuple("DialFill", vector8));
        final Vector<NFParamDef> vector9 = new Vector<NFParamDef>();
        vector9.addElement(super.param.defineString("DialActiveLabel-name", null));
        super.param.defineActiveLabel("DialActiveLabel", vector9);
        super.param.defineVector("DialActiveLabels", super.param.defineTuple("DialActiveLabel", vector9));
        super.param.defineString("DialActiveLabelsEnabled", "ON");
        final Vector<NFParamDef> vector10 = new Vector<NFParamDef>();
        vector10.addElement(super.param.defineString("DialDelete-name", null));
        super.param.defineVector("DialDelete", super.param.defineTuple("DialDeleteTuple", vector10));
        final Vector<NFParamDef> vector11 = new Vector<NFParamDef>();
        vector11.addElement(super.param.defineString("Sector-name", null));
        vector11.addElement(super.param.defineColor("Sector-color", null));
        vector11.addElement(super.param.defineString("Sector-dial", null));
        vector11.addElement(super.param.defineNumber("Sector-outerRadius", new Integer(0)));
        vector11.addElement(super.param.defineNumber("Sector-innerRadius", new Integer(0)));
        vector11.addElement(super.param.defineString("Sector-label", null));
        super.param.defineVector("Sectors", super.param.defineTuple("Sector", vector11));
        final Vector<NFParamDef> vector12 = new Vector<NFParamDef>();
        vector12.addElement(super.param.defineString("SectorActiveLabel-name", null));
        super.param.defineActiveLabel("SectorActiveLabel", vector12);
        super.param.defineVector("SectorActiveLabels", super.param.defineTuple("SectorActiveLabel", vector12));
        super.param.defineString("SectorActiveLabelsEnabled", "ON");
        final Vector<NFParamDef> vector13 = new Vector<NFParamDef>();
        vector13.addElement(super.param.defineString("SectorBorder-name", null));
        super.param.defineLine("SectorBorder", vector13);
        super.param.defineVector("SectorBorders", super.param.defineTuple("SectorBorder", vector13));
        final Vector<NFParamDef> vector14 = new Vector<NFParamDef>();
        vector14.addElement(super.param.defineString("SectorData-name", null));
        vector14.addElement(super.param.defineNumber("SectorData-start", new Double(0.0)));
        vector14.addElement(super.param.defineNumber("SectorData-stop", new Double(0.0)));
        super.param.defineVector("SectorData", super.param.defineTuple("SectorDataTuple", vector14));
        final Vector<NFParamDef> vector15 = new Vector<NFParamDef>();
        super.param.defineLabel("SectorLabel", vector15);
        vector15.insertElementAt(super.param.defineString("SectorLabel-name", null), 0);
        vector15.insertElementAt(super.param.defineNumber("SectorLabel-labelPos", new Double(1.1)), 2);
        super.param.defineVector("SectorLabels", super.param.defineTuple("SectorLabel", vector15));
        final Vector<NFParamDef> vector16 = new Vector<NFParamDef>();
        vector16.addElement(super.param.defineString("SectorDelete-name", null));
        super.param.defineVector("SectorDelete", super.param.defineTuple("SectorDeleteTuple", vector16));
        super.param.defineString("SectorDrag", "OFF");
        final Vector<NFParamDef> vector17 = new Vector<NFParamDef>();
        vector17.addElement(super.param.defineString("Hand-name", null));
        vector17.addElement(super.param.defineColor("Hand-tipColor", null));
        vector17.addElement(super.param.defineColor("Hand-shaftColor", null));
        vector17.addElement(super.param.defineString("Hand-dial", null));
        super.param.defineVector("Hands", super.param.defineTuple("Hand", vector17));
        final Vector<NFParamDef> vector18 = new Vector<NFParamDef>();
        vector18.addElement(super.param.defineString("HandData-name", null));
        vector18.addElement(super.param.defineNumber("HandData-value", new Double(0.0)));
        vector18.addElement(super.param.defineNumber("HandData-length", new Double(0.0)));
        super.param.defineVector("HandData", super.param.defineTuple("HandDataTuple", vector18));
        final Vector<NFParamDef> vector19 = new Vector<NFParamDef>();
        vector19.addElement(super.param.defineString("HandStyle-name", null));
        final Hashtable<String, Integer> hashtable5 = new Hashtable<String, Integer>();
        hashtable5.put("NONE", new Integer(0));
        hashtable5.put("LINE", new Integer(1));
        hashtable5.put("BLOCK", new Integer(2));
        hashtable5.put("SHARP", new Integer(3));
        hashtable5.put("ROUND", new Integer(4));
        vector19.addElement(super.param.defineSymbol("HandStyle-tipstyle", hashtable5, new Integer(2)));
        vector19.addElement(super.param.defineNumber("HandStyle-basewidth", new Integer(0)));
        vector19.addElement(super.param.defineNumber("HandStyle-tipwidth", new Integer(0)));
        super.param.defineVector("HandStyles", super.param.defineTuple("HandStyle", vector19));
        final Vector<NFParamDef> vector20 = new Vector<NFParamDef>();
        vector20.addElement(super.param.defineString("HandActiveLabel-name", null));
        super.param.defineActiveLabel("HandActiveLabel", vector20);
        super.param.defineVector("HandActiveLabels", super.param.defineTuple("HandActiveLabel", vector20));
        super.param.defineString("HandActiveLabelsEnabled", "ON");
        final Vector<NFParamDef> vector21 = new Vector<NFParamDef>();
        vector21.addElement(super.param.defineString("HandDelete-name", null));
        super.param.defineVector("HandDelete", super.param.defineTuple("HandDeleteTuple", vector21));
        super.param.defineString("HandDrag", "OFF");
        definePatternFill(super.param, "DialFillPatterns");
        definePatternFill(super.param, "SectorFillPatterns");
    }
    
    public static void definePatternFill(final NFParam nfParam, final String s) {
        final Vector patternFillParamTuple = NFPatternFill.getPatternFillParamTuple(nfParam, s);
        patternFillParamTuple.insertElementAt(nfParam.defineString(s + "Name", null), 0);
        nfParam.defineVector(s, nfParam.defineTuple(s + "Tuple", patternFillParamTuple));
    }
    
    public void loadSectorPatternFill(final NFParam nfParam, final String s) throws NFParamException {
        if (nfParam.changed(s)) {
            super.graphChanged = true;
            final Vector vector = (Vector)nfParam.get(s);
            for (int i = 0; i < vector.size(); ++i) {
                final Vector vector2 = (Vector)NFUtil.getObject(vector, i, null);
                final NFDialSector sector = this.findSector(NFUtil.getString(vector2, 0, null));
                if (sector != null) {
                    final Vector vector3 = (Vector)vector2.clone();
                    vector3.removeElementAt(0);
                    sector.pattern = NFPatternFill.loadPatternFill(vector3);
                }
            }
        }
    }
    
    public void loadDialPatternFill(final NFParam nfParam, final String s) throws NFParamException {
        if (nfParam.changed(s)) {
            super.graphChanged = true;
            final Vector vector = (Vector)nfParam.get(s);
            for (int i = 0; i < vector.size(); ++i) {
                final Vector vector2 = (Vector)NFUtil.getObject(vector, i, null);
                final NFDial dial = this.findDial(NFUtil.getString(vector2, 0, null));
                if (dial != null) {
                    final Vector vector3 = (Vector)vector2.clone();
                    vector3.removeElementAt(0);
                    dial.pattern = NFPatternFill.loadPatternFill(vector3);
                }
            }
        }
    }
    
    protected synchronized void loadParams() throws Exception {
        final boolean b = super.legend != null;
        super.loadParams();
        if (super.legend != null && !b) {
            this.a();
        }
        boolean b2 = false;
        int n = 0;
        int n2 = 0;
        boolean b3 = false;
        if (super.param.changed("Dials")) {
            super.graphChanged = true;
            this.s = true;
            b2 = true;
            n = 1;
            n2 = 1;
            b3 = true;
            final Vector vector = (Vector)super.param.get("Dials");
            this.deleteAllDials();
            if (vector != null) {
                int n3 = 0;
                final Enumeration<Vector<String>> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    final Vector<String> vector2 = elements.nextElement();
                    final String s = vector2.elementAt(0);
                    final double doubleValue = ((Number)vector2.elementAt(1)).doubleValue();
                    final double doubleValue2 = ((Number)vector2.elementAt(2)).doubleValue();
                    double n4;
                    double n5;
                    if (doubleValue < doubleValue2) {
                        n4 = -(doubleValue - 90.0);
                        n5 = -(doubleValue2 - 90.0);
                    }
                    else {
                        n4 = doubleValue - 90.0;
                        n5 = doubleValue2 - 90.0;
                    }
                    final double doubleValue3 = ((Number)vector2.elementAt(3)).doubleValue();
                    final double n6 = (doubleValue3 < 0.0) ? 0.0 : doubleValue3;
                    final double n7 = ((n6 > 100.0) ? 100.0 : n6) / 100.0;
                    final int intValue = ((Number)vector2.elementAt(4)).intValue();
                    final NFLine nfLine = new NFLine(null);
                    nfLine.setColor(Color.black);
                    nfLine.setScale(super.scale);
                    final NFArrow nfArrow = new NFArrow();
                    nfArrow.setStyle(4);
                    nfArrow.setWidth(nfLine.getThickness());
                    nfLine.setArrows(null, nfArrow);
                    this.addDial(s, n4, n5, n7, intValue, nfLine);
                    ++n3;
                }
            }
        }
        if (b2 || super.param.changed("DialScale")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector3 = (Vector)super.param.get("DialScale");
            if (vector3 != null) {
                final Enumeration<Vector<String>> elements2 = vector3.elements();
                while (elements2.hasMoreElements()) {
                    final Vector<String> vector4 = elements2.nextElement();
                    this.addDialScale(vector4.elementAt(0), ((Number)vector4.elementAt(1)).doubleValue(), ((Number)vector4.elementAt(2)).doubleValue(), ((Number)vector4.elementAt(3)).doubleValue());
                }
            }
        }
        if (b2 || super.param.changed("DialBorders")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector5 = (Vector)super.param.get("DialBorders");
            if (vector5 != null) {
                final Enumeration<Vector<String>> elements3 = vector5.elements();
                while (elements3.hasMoreElements()) {
                    final Vector<String> vector6 = elements3.nextElement();
                    final NFDial dial = this.findDial(vector6.elementAt(0));
                    if (dial != null) {
                        dial.borderLine = NFLine.loadParams(super.param, vector6, 1);
                        if (dial.borderLine != null) {
                            final NFArrow nfArrow2 = new NFArrow();
                            nfArrow2.setStyle(4);
                            int thickness = dial.borderLine.getThickness();
                            if (thickness < 1) {
                                thickness = 1;
                            }
                            nfArrow2.setWidth(thickness);
                            dial.borderLine.setArrows(null, nfArrow2);
                            dial.borderLine.setScale(super.scale);
                        }
                        dial.borderStyle = ((Number)vector6.elementAt(4)).intValue();
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialFills")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector7 = (Vector)super.param.get("DialFills");
            if (vector7 != null) {
                final Enumeration<Vector<String>> elements4 = vector7.elements();
                while (elements4.hasMoreElements()) {
                    final Vector<String> vector8 = elements4.nextElement();
                    final NFDial dial2 = this.findDial(vector8.elementAt(0));
                    if (dial2 != null) {
                        dial2.fillColor = (Color)vector8.elementAt(1);
                        dial2.fillStyle = ((Number)vector8.elementAt(2)).intValue();
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialTicLabelStyles")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector9 = (Vector)super.param.get("DialTicLabelStyles");
            if (vector9 != null) {
                final Enumeration<Vector> elements5 = vector9.elements();
                while (elements5.hasMoreElements()) {
                    final Vector vector10 = (Vector)elements5.nextElement().clone();
                    final String s2 = vector10.elementAt(0);
                    vector10.removeElementAt(0);
                    final NFDial dial3 = this.findDial(s2);
                    if (dial3 != null) {
                        final double number = NFUtil.getNumber(vector10, 1, 1.1);
                        vector10.removeElementAt(1);
                        if (dial3.ticLabel == null) {
                            dial3.ticLabel = new NFLabel();
                        }
                        NFLabel.loadParams(dial3.ticLabel, vector10, 0);
                        dial3.ticLabel.setComponent(this);
                        dial3.ticLabel.setScale(super.scale);
                        dial3.ticLabelPos = number;
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialFormats")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector11 = (Vector)super.param.get("DialFormats");
            if (vector11 != null) {
                final Enumeration<Vector<String>> elements6 = vector11.elements();
                while (elements6.hasMoreElements()) {
                    final Vector<String> vector12 = elements6.nextElement();
                    final NFDial dial4 = this.findDial(vector12.elementAt(0));
                    if (dial4 != null) {
                        dial4.formatType = NFUtil.getNumber(vector12, 1, 2);
                        dial4.formatStr = NFUtil.getString(vector12, 2, null);
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialTics")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector13 = (Vector)super.param.get("DialTics");
            if (vector13 != null) {
                final Enumeration<Vector<String>> elements7 = vector13.elements();
                while (elements7.hasMoreElements()) {
                    final Vector<String> vector14 = elements7.nextElement();
                    final NFDial dial5 = this.findDial(vector14.elementAt(0));
                    if (dial5 != null) {
                        Color black = (Color)vector14.elementAt(1);
                        if (black == null) {
                            black = Color.black;
                        }
                        final int intValue2 = ((Number)vector14.elementAt(2)).intValue();
                        final double doubleValue4 = ((Number)vector14.elementAt(3)).doubleValue();
                        final double n8 = (doubleValue4 < 0.0) ? 0.0 : doubleValue4;
                        final double ticLength = ((n8 > 100.0) ? 100.0 : n8) / 100.0;
                        dial5.ticColor = black;
                        dial5.ticLength = ticLength;
                        dial5.ticWidth = intValue2;
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialTicLabels")) {
            super.graphChanged = true;
            this.s = true;
            final Vector vector15 = (Vector)super.param.get("DialTicLabels");
            if (vector15 != null) {
                final Enumeration<Vector> elements8 = vector15.elements();
                while (elements8.hasMoreElements()) {
                    final Vector ticLabels = (Vector)elements8.nextElement().clone();
                    final NFDial dial6 = this.findDial(ticLabels.elementAt(0));
                    if (dial6 != null) {
                        ticLabels.removeElementAt(0);
                        dial6.ticLabels = ticLabels;
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialActiveLabels") || super.param.changed("DialActiveLabelsEnabled")) {
            b3 = true;
            final Vector vector16 = (Vector)super.param.get("DialActiveLabels");
            if (vector16 != null) {
                final Enumeration<Vector<String>> elements9 = vector16.elements();
                while (elements9.hasMoreElements()) {
                    final Vector<String> vector17 = elements9.nextElement();
                    final NFDial dial7 = this.findDial(vector17.elementAt(0));
                    if (dial7 != null) {
                        dial7.activeLabel = NFActiveLabel.loadParams(super.param, vector17, 1);
                    }
                }
            }
        }
        if (b2 || super.param.changed("DialDelete")) {
            super.graphChanged = true;
            this.s = true;
            super.legendChanged = true;
            final Vector vector18 = (Vector)super.param.get("DialDelete");
            if (vector18 != null) {
                final Enumeration<Vector<String>> elements10 = vector18.elements();
                while (elements10.hasMoreElements()) {
                    final String s3 = elements10.nextElement().elementAt(0);
                    if (s3 != null && s3.equals("ALL")) {
                        this.deleteAllDials();
                    }
                    else {
                        if (s3 == null) {
                            continue;
                        }
                        this.deleteDial(s3);
                    }
                }
                this.a();
            }
        }
        if (super.param.changed("DialClip")) {
            super.graphChanged = true;
            super.legendChanged = true;
            this.i = NFUtil.getNumber(super.param.get("DialClip"), 0);
        }
        this.loadDialPatternFill(super.param, "DialFillPatterns");
        if (n != 0 || super.param.changed("Sectors")) {
            super.graphChanged = true;
            super.legendChanged = true;
            this.q = true;
            n = 1;
            b3 = true;
            final Vector vector19 = (Vector)super.param.get("Sectors");
            this.deleteAllSectors();
            if (vector19 != null) {
                int n9 = 0;
                final Enumeration<Vector<String>> elements11 = vector19.elements();
                while (elements11.hasMoreElements()) {
                    final Vector<String> vector20 = elements11.nextElement();
                    final String s4 = vector20.elementAt(0);
                    Color defaultColor = (Color)vector20.elementAt(1);
                    if (defaultColor == null) {
                        defaultColor = this.defaultColor(n9);
                    }
                    this.addSector(s4, vector20.elementAt(5), defaultColor, ((Number)vector20.elementAt(3)).doubleValue() / 100.0, ((Number)vector20.elementAt(4)).doubleValue() / 100.0, vector20.elementAt(2));
                    ++n9;
                }
                this.a();
            }
        }
        if (n != 0 || super.param.changed("SectorActiveLabels") || super.param.changed("SectorActiveLabelsEnabled")) {
            b3 = true;
            final Vector vector21 = (Vector)super.param.get("SectorActiveLabels");
            if (vector21 != null) {
                final Enumeration<Vector<String>> elements12 = vector21.elements();
                while (elements12.hasMoreElements()) {
                    final Vector<String> vector22 = elements12.nextElement();
                    final NFDialSector sector = this.findSector(vector22.elementAt(0));
                    if (sector != null) {
                        sector.activeLabel = NFActiveLabel.loadParams(super.param, vector22, 1);
                    }
                }
            }
        }
        if (n != 0 || super.param.changed("SectorData")) {
            super.graphChanged = true;
            this.q = true;
            final Vector vector23 = (Vector)super.param.get("SectorData");
            if (vector23 != null) {
                final Enumeration<Vector<String>> elements13 = vector23.elements();
                while (elements13.hasMoreElements()) {
                    final Vector<String> vector24 = elements13.nextElement();
                    this.addSectorData(vector24.elementAt(0), ((Number)vector24.elementAt(1)).doubleValue(), ((Number)vector24.elementAt(2)).doubleValue());
                }
            }
        }
        if (n != 0 || super.param.changed("SectorBorders")) {
            super.graphChanged = true;
            this.q = true;
            final Enumeration<Vector<String>> elements14 = (Enumeration<Vector<String>>)((Vector)super.param.get("SectorBorders")).elements();
            while (elements14.hasMoreElements()) {
                final Vector<String> vector25 = elements14.nextElement();
                final NFDialSector sector2 = this.findSector(vector25.elementAt(0));
                if (sector2 != null) {
                    sector2.borderLine = NFLine.loadParams(super.param, vector25, 1);
                    if (sector2.borderLine == null) {
                        continue;
                    }
                    final NFArrow nfArrow3 = new NFArrow();
                    nfArrow3.setStyle(4);
                    int thickness2 = sector2.borderLine.getThickness();
                    if (thickness2 < 1) {
                        thickness2 = 1;
                    }
                    nfArrow3.setWidth(thickness2);
                    sector2.borderLine.setArrows(null, nfArrow3);
                    sector2.borderLine.setScale(super.scale);
                }
            }
        }
        if (n != 0 || super.param.changed("SectorLabels")) {
            super.graphChanged = true;
            this.q = true;
            super.legendChanged = true;
            final Vector vector26 = (Vector)super.param.get("SectorLabels");
            if (vector26 != null) {
                final Enumeration<Vector> elements15 = vector26.elements();
                while (elements15.hasMoreElements()) {
                    final Vector vector27 = (Vector)elements15.nextElement().clone();
                    final String s5 = vector27.elementAt(0);
                    vector27.removeElementAt(0);
                    final double number2 = NFUtil.getNumber(vector27, 1, 1.1);
                    vector27.removeElementAt(1);
                    final NFLabel nfLabel = new NFLabel();
                    nfLabel.loadParams(vector27);
                    nfLabel.setComponent(this);
                    nfLabel.setScale(super.scale);
                    this.setSectorLabel(s5, nfLabel);
                    this.setSectorLabelPos(s5, number2);
                }
            }
            this.a();
        }
        if (n != 0 || super.param.changed("SectorDelete")) {
            super.graphChanged = true;
            this.q = true;
            super.legendChanged = true;
            final Vector vector28 = (Vector)super.param.get("SectorDelete");
            if (vector28 != null) {
                final Enumeration<Vector<String>> elements16 = vector28.elements();
                while (elements16.hasMoreElements()) {
                    final String s6 = elements16.nextElement().elementAt(0);
                    if (s6 != null && s6.equals("ALL")) {
                        this.deleteAllSectors();
                    }
                    else {
                        if (s6 == null) {
                            continue;
                        }
                        this.deleteSector(s6);
                    }
                }
                this.a();
            }
        }
        if (n != 0 || super.param.changed("SectorDrag")) {
            final String s7 = (String)super.param.get("SectorDrag");
            if (s7 != null) {
                this.g = s7.equalsIgnoreCase("ON");
            }
        }
        this.loadSectorPatternFill(super.param, "SectorFillPatterns");
        if (n2 != 0 || super.param.changed("Hands")) {
            super.graphChanged = true;
            this.r = true;
            n2 = 1;
            final Vector vector29 = (Vector)super.param.get("Hands");
            this.deleteAllHands();
            if (vector29 != null) {
                int n10 = 0;
                final Enumeration<Vector<String>> elements17 = vector29.elements();
                while (elements17.hasMoreElements()) {
                    final Vector<String> vector30 = elements17.nextElement();
                    final String s8 = vector30.elementAt(0);
                    Color defaultColor2 = (Color)vector30.elementAt(1);
                    if (defaultColor2 == null) {
                        defaultColor2 = this.defaultColor(n10);
                    }
                    Color defaultColor3 = (Color)vector30.elementAt(2);
                    if (defaultColor3 == null) {
                        defaultColor3 = this.defaultColor(n10);
                    }
                    this.addHand(s8, defaultColor2, defaultColor3, vector30.elementAt(3));
                    ++n10;
                }
            }
        }
        if (n2 != 0 || super.param.changed("HandActiveLabels") || super.param.changed("HandActiveLabelsEnabled")) {
            b3 = true;
            final Vector vector31 = (Vector)super.param.get("HandActiveLabels");
            if (vector31 != null) {
                final Enumeration<Vector<String>> elements18 = vector31.elements();
                while (elements18.hasMoreElements()) {
                    final Vector<String> vector32 = elements18.nextElement();
                    final NFHand hand = this.findHand(vector32.elementAt(0));
                    if (hand != null) {
                        hand.setActiveLabel(NFActiveLabel.loadParams(super.param, vector32, 1));
                    }
                }
            }
        }
        if (n2 != 0 || super.param.changed("HandStyles")) {
            super.graphChanged = true;
            this.r = true;
            final Vector vector33 = (Vector)super.param.get("HandStyles");
            if (vector33 != null) {
                final Enumeration<Vector<String>> elements19 = vector33.elements();
                while (elements19.hasMoreElements()) {
                    final Vector<String> vector34 = elements19.nextElement();
                    final String s9 = vector34.elementAt(0);
                    final int intValue3 = ((Number)vector34.elementAt(1)).intValue();
                    final int intValue4 = ((Number)vector34.elementAt(2)).intValue();
                    final int intValue5 = ((Number)vector34.elementAt(3)).intValue();
                    final NFHand hand2 = this.findHand(s9);
                    if (hand2 != null) {
                        hand2.setStyle(intValue3);
                        hand2.setTipWidth(intValue4);
                        hand2.setShaftWidth(intValue5);
                    }
                }
            }
        }
        if (n2 != 0 || super.param.changed("HandData")) {
            super.graphChanged = true;
            this.r = true;
            final Vector vector35 = (Vector)super.param.get("HandData");
            if (vector35 != null) {
                final Enumeration<Vector<String>> elements20 = vector35.elements();
                while (elements20.hasMoreElements()) {
                    final Vector<String> vector36 = elements20.nextElement();
                    final String s10 = vector36.elementAt(0);
                    final double doubleValue5 = ((Number)vector36.elementAt(1)).doubleValue();
                    final double doubleValue6 = ((Number)vector36.elementAt(2)).doubleValue();
                    final double shaftLength = ((Double.isNaN(doubleValue6) || doubleValue6 <= 0.0) ? 100.0 : doubleValue6) / 100.0;
                    final NFHand hand3 = this.findHand(s10);
                    if (hand3 != null) {
                        hand3.setValue(doubleValue5);
                        hand3.setShaftLength(shaftLength);
                    }
                }
            }
        }
        if (n2 != 0 || super.param.changed("HandDelete")) {
            super.graphChanged = true;
            this.r = true;
            final Vector vector37 = (Vector)super.param.get("HandDelete");
            if (vector37 != null) {
                final Enumeration<Vector<String>> elements21 = vector37.elements();
                while (elements21.hasMoreElements()) {
                    final String s11 = elements21.nextElement().elementAt(0);
                    if (s11 != null && s11.equals("ALL")) {
                        this.deleteAllHands();
                    }
                    else {
                        if (s11 == null) {
                            continue;
                        }
                        this.deleteHand(s11);
                    }
                }
            }
        }
        if (n2 != 0 || super.param.changed("HandDrag")) {
            final String s12 = (String)super.param.get("HandDrag");
            if (s12 != null) {
                this.h = s12.equalsIgnoreCase("ON");
            }
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.setAllMapComponent(this, super.notesets);
            for (int size = super.notesets.size(), i = 0; i < size; ++i) {
                ((NFNoteSet)super.notesets.elementAt(i)).setAxisMap(null);
            }
        }
        if (b3) {
            this.rebuildAllActiveLabels(NFUtil.getString(super.param.get("DialActiveLabelsEnabled"), "ON").equals("ON"), NFUtil.getString(super.param.get("SectorActiveLabelsEnabled"), "ON").equals("ON"), NFUtil.getString(super.param.get("HandActiveLabelsEnabled"), "ON").equals("ON"));
        }
    }
    
    private NFActiveLabel a(final NFActiveLabel nfActiveLabel, final String s) {
        NFActiveLabel nfActiveLabel2;
        if (super.dwell != null && super.dwell.getSelectMode()) {
            nfActiveLabel2 = new NFActiveLabel(s, null, null);
        }
        else if (nfActiveLabel != null) {
            nfActiveLabel2 = nfActiveLabel;
        }
        else {
            nfActiveLabel2 = new NFActiveLabel();
        }
        if (nfActiveLabel2 != null) {
            nfActiveLabel2.selectedItemParam = s.substring(0, s.lastIndexOf(":"));
            nfActiveLabel2.selectedItemIndex = Integer.parseInt(s.substring(s.lastIndexOf(":") + 1));
        }
        return nfActiveLabel2;
    }
    
    public StringBuffer getParam(final String s, StringBuffer ad) {
        if (ad == null) {
            if (this.ad == null) {
                this.ad = new StringBuffer();
            }
            ad = this.ad;
            ad.setLength(0);
        }
        if (s.equalsIgnoreCase("SectorData")) {
            for (int i = 0; i < this.a.size(); ++i) {
                final NFDialSector nfDialSector = this.a.elementAt(i);
                if (i > 0) {
                    ad.append(",");
                }
                ad.append("(\"" + nfDialSector.name + "\", " + NFUtil.sprintf(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol(), "%f", new Double(nfDialSector.startValue)) + ", " + NFUtil.sprintf(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol(), "%f", new Double(nfDialSector.stopValue)) + ")");
            }
        }
        else if (s.equalsIgnoreCase("HandData")) {
            for (int j = 0; j < this.b.size(); ++j) {
                final NFHand nfHand = this.b.elementAt(j);
                if (j > 0) {
                    ad.append(",");
                }
                ad.append("(\"" + nfHand.getName() + "\", " + NFUtil.sprintf(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol(), "%f", new Double(nfHand.getValue())) + ", " + NFUtil.sprintf(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol(), "%f", new Double(nfHand.getShaftLength() * 100.0)) + ")");
            }
        }
        return ad;
    }
}
