// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import jlog.$BI.$I4;
import jlog.$T5.util.$OKD;
import java.util.Enumeration;
import java.awt.Container;
import java.awt.Polygon;
import java.awt.Point;
import jlog.$H4;
import java.awt.Component;

public class $NKD extends Component implements $H4
{
    public static final boolean debug = true;
    Point p1;
    Point p2;
    $NKD prev;
    $NKD next;
    
    public Polygon $BLD() {
        final Point[] $wkd = this.$WKD();
        final int length = $wkd.length;
        final int[] array = new int[length];
        final int[] array2 = new int[length];
        int n = length;
        while (n-- != 0) {
            array[n] = $wkd[n].x;
            array2[n] = $wkd[n].y;
        }
        return new Polygon(array, array2, length);
    }
    
    public void $QKD(final Container container) {
        final Enumeration $rib = this.$RIB();
        while ($rib.hasMoreElements()) {
            container.add($rib.nextElement());
        }
    }
    
    public Enumeration $RIB() {
        return new $OKD(this.getLines());
    }
    
    public void $SKD() {
        if (this.prev != null) {
            this.prev.next = null;
            this.prev = null;
        }
        if (this.next != null) {
            this.next.prev = null;
            this.next = null;
        }
        final Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
        }
    }
    
    public Point $TKD() {
        return this.p1;
    }
    
    public Point $UKD() {
        return this.p2;
    }
    
    public Enumeration $VKD() {
        return new $OKD(this.$WKD());
    }
    
    public Point[] $WKD() {
        int n = 1;
        $NKD $nkd;
        for ($nkd = this.getFirst(); $nkd.next != null && $nkd.next != this; $nkd = $nkd.next) {
            ++n;
        }
        final Point[] array = new Point[n + 1];
        while (true) {
            array[n] = new Point($nkd.p2);
            if (--n == 0) {
                break;
            }
            $nkd = $nkd.prev;
        }
        array[n] = new Point($nkd.p1);
        return array;
    }
    
    public $NKD $XKD() {
        return this.prev;
    }
    
    public void $YKD(final Point point) {
        this.move(point.x, point.y, this.p2.x, this.p2.y);
    }
    
    public void $ZKD(final Point point) {
        this.move(this.p1.x, this.p1.y, point.x, point.y);
    }
    
    public $NKD(final Point p2, final Point p3) {
        this.p1 = null;
        this.p2 = null;
        this.prev = null;
        this.next = null;
        this.p1 = p2;
        this.p2 = p3;
        $I4.$QQ(p2);
        $I4.$QQ(p3);
        if (this.prev != null) {
            this.prev.next = this;
        }
        this.setBounds();
    }
    
    public $NKD(final Polygon polygon) {
        this.p1 = null;
        this.p2 = null;
        this.prev = null;
        this.next = null;
        $I4.$QQ(polygon);
        $I4.$QQ(polygon.npoints);
        $I4.$QQ(polygon.npoints <= polygon.xpoints.length);
        $I4.$QQ(polygon.npoints <= polygon.ypoints.length);
        $NKD $nkd = this;
        this.p1 = new Point(polygon.xpoints[0], polygon.xpoints[0]);
        if (polygon.npoints == 1) {
            this.p2 = new Point(this.p1.x, this.p1.y);
        }
        else {
            this.p2 = new Point(polygon.xpoints[1], polygon.ypoints[1]);
            for (int i = 2; i < polygon.npoints; ++i) {
                $nkd = new $NKD($nkd, new Point(polygon.xpoints[i], polygon.ypoints[i]));
            }
        }
        if (this.p1.equals($nkd.p2)) {
            this.p1 = $nkd.p2;
        }
        this.setBounds();
    }
    
    public $NKD(final $NKD prev, final Point p2) {
        this.p1 = null;
        this.p2 = null;
        this.prev = null;
        this.next = null;
        $I4.$QQ(prev);
        $I4.$QQ(p2);
        $I4.$QQ(prev.next == null);
        this.prev = prev;
        prev.next = this;
        this.p2 = p2;
        this.p1 = prev.p2;
        this.setBounds();
        final Container parent = prev.getParent();
        if (parent != null) {
            parent.add(this);
        }
    }
    
    public $NKD getFirst() {
        $NKD prev;
        for (prev = this; prev.prev != null && prev.prev != this; prev = prev.prev) {}
        return prev;
    }
    
    public $NKD[] getLines() {
        int n = 1;
        $NKD $nkd;
        for ($nkd = this.getFirst(); $nkd.next != null && $nkd.next != this; $nkd = $nkd.next) {
            ++n;
        }
        final $NKD[] array = new $NKD[n];
        while (n-- != 0) {
            array[n] = $nkd;
            $nkd = $nkd.prev;
        }
        return array;
    }
    
    public $NKD getNext() {
        return this.next;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(Math.max(this.p1.x, this.p2.x) - Math.min(this.p1.x, this.p2.x) + 1, Math.max(this.p1.y, this.p2.y) - Math.min(this.p1.y, this.p2.y) + 1);
    }
    
    public void insert(final Point p) {
        $I4.$QQ(this.p1);
        $I4.$QQ(this.p2);
        if (p.equals(this.p1) || p.equals(this.p2)) {
            return;
        }
        final $NKD $nkd = new $NKD(p, this.p2);
        $nkd.prev = this;
        $nkd.next = this.next;
        this.p2 = p;
        if (this.next != null) {
            this.next.prev = $nkd;
        }
        this.next = $nkd;
        final Container parent = this.getParent();
        if (parent != null) {
            parent.add($nkd);
        }
        this.setBounds();
        this.next.setBounds();
    }
    
    public void move(final int n, final int n2, final int n3, final int n4) {
        if (n != this.p1.x || n3 != this.p2.x || n2 != this.p1.y || n4 != this.p2.y) {
            this.p1.move(n, n2);
            this.p2.move(n3, n4);
            this.setBounds();
        }
    }
    
    public void move(final Point point, final Point point2) {
        this.move(point.x, point.y, point2.x, point2.y);
    }
    
    public void paint(Graphics create) {
        create = create.create();
        create.setXORMode(Color.green);
        final int min = Math.min(this.p1.x, this.p2.x);
        final int min2 = Math.min(this.p1.y, this.p2.y);
        create.translate(-min, -min2);
        create.drawLine(this.p1.x, this.p1.y, this.p2.x, this.p2.y);
        create.translate(min, min2);
        create.setPaintMode();
        super.paint(create);
        create.dispose();
    }
    
    public void removeAll(final Container container) {
        final Enumeration $rib = this.$RIB();
        while ($rib.hasMoreElements()) {
            container.remove($rib.nextElement());
        }
    }
    
    public void setBounds() {
        final int min = Math.min(this.p1.x, this.p2.x);
        final int min2 = Math.min(this.p1.y, this.p2.y);
        final Dimension preferredSize = this.getPreferredSize();
        final Rectangle bounds = this.getBounds();
        if (preferredSize.width != bounds.width || preferredSize.height != bounds.height) {
            this.setSize(preferredSize.width, preferredSize.height);
        }
        if (min != bounds.x || min2 != bounds.y) {
            this.setLocation(min, min2);
        }
    }
    
    public void setEnabledAll(final boolean enabled) {
        final Enumeration $rib = this.$RIB();
        while ($rib.hasMoreElements()) {
            $rib.nextElement().setEnabled(enabled);
        }
    }
    
    public void setVisibleAll(final boolean visible) {
        final Enumeration $rib = this.$RIB();
        while ($rib.hasMoreElements()) {
            $rib.nextElement().setVisible(visible);
        }
    }
    
    public void translate(final int n, final int n2) {
        final Enumeration $rib = this.$RIB();
        while ($rib.hasMoreElements()) {
            $rib.nextElement().translate(n, n2);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
