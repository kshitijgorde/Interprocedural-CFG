// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

import xfunctions.graphs.CoordinateRect;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.Vector;

public class BezierFunction extends Function
{
    private Vector segments;
    private int derivativeLevel;
    private int caseNum;
    private int prevCaseNum;
    static final Color lightMagenta;
    private BezierSegment segmentThatWasHit;
    private int segmentNumThatWasHit;
    private boolean endpointWasHit;
    private boolean itemOnLeftWasHit;
    
    static {
        lightMagenta = new Color(255, 180, 255);
    }
    
    public BezierFunction() {
        this.caseNum = -1;
        (this.segments = new Vector()).addElement(new BezierSegment(-5.0, 5.0));
        super.arity = 1;
        super.preferredXmin = -5.0;
        super.preferredXmax = 5.0;
        super.preferredYmin = -5.0;
        super.preferredYmax = 5.0;
        (super.params = new Variable[1])[0] = new Variable("x");
    }
    
    public BezierFunction(final BezierFunction bezierFunction) {
        this.caseNum = -1;
        this.setName(bezierFunction.getName());
        super.arity = 1;
        (super.params = new Variable[1])[0] = new Variable("x");
        super.preferredXmin = bezierFunction.preferredXmin;
        super.preferredXmax = bezierFunction.preferredXmax;
        super.preferredYmin = bezierFunction.preferredYmin;
        super.preferredYmax = bezierFunction.preferredYmax;
        this.derivativeLevel = bezierFunction.derivativeLevel;
        this.segments = new Vector();
        for (int i = 0; i < bezierFunction.segments.size(); ++i) {
            this.segments.addElement(new BezierSegment((BezierSegment)bezierFunction.segments.elementAt(i)));
        }
        for (int j = 0; j < this.segments.size(); ++j) {
            final BezierSegment bezierSegment = this.segments.elementAt(j);
            if (bezierSegment.leftNeighbor != null && j > 0) {
                bezierSegment.leftNeighbor = this.segments.elementAt(j - 1);
            }
            if (bezierSegment.rightNeighbor != null && j < this.segments.size() - 1) {
                bezierSegment.rightNeighbor = this.segments.elementAt(j + 1);
            }
        }
    }
    
    BezierFunction(final TableFunction tableFunction) {
        this.caseNum = -1;
        this.setName(tableFunction.getName());
        super.arity = 1;
        (super.params = new Variable[1])[0] = new Variable("x");
        super.preferredXmin = tableFunction.preferredXmin;
        super.preferredXmax = tableFunction.preferredXmax;
        super.preferredYmin = tableFunction.preferredYmin;
        super.preferredYmax = tableFunction.preferredYmax;
        this.derivativeLevel = tableFunction.getDerivativeLevel();
        this.segments = new Vector();
        if (tableFunction.getPointCount() < 2) {
            return;
        }
        RealPoint2D point = tableFunction.getPoint(0);
        for (int i = 1; i < tableFunction.getPointCount(); ++i) {
            final RealPoint2D point2 = tableFunction.getPoint(i);
            final BezierSegment bezierSegment = new BezierSegment(point.getX(), point2.getX());
            bezierSegment.set_y1(point.getY());
            bezierSegment.set_y2(point2.getY());
            this.segments.addElement(bezierSegment);
            point = point2;
        }
        for (int j = 0; j < this.segments.size(); ++j) {
            final BezierSegment bezierSegment2 = this.segments.elementAt(j);
            if (j > 0) {
                final BezierSegment bezierSegment3 = this.segments.elementAt(j - 1);
                bezierSegment2.set_d1((bezierSegment2.getY2() - bezierSegment3.getY1()) / (bezierSegment2.getX2() - bezierSegment3.getX1()));
            }
            else {
                bezierSegment2.set_d1((bezierSegment2.getY2() - bezierSegment2.getY1()) / (bezierSegment2.getX2() - bezierSegment2.getX1()));
            }
            if (j < this.segments.size() - 1) {
                final BezierSegment bezierSegment4 = this.segments.elementAt(j + 1);
                bezierSegment2.set_d2((bezierSegment2.getY1() - bezierSegment4.getY2()) / (bezierSegment2.getX1() - bezierSegment4.getX2()));
            }
            else {
                bezierSegment2.set_d2((bezierSegment2.getY2() - bezierSegment2.getY1()) / (bezierSegment2.getX2() - bezierSegment2.getX1()));
            }
        }
        for (int k = 0; k < this.segments.size(); ++k) {
            final BezierSegment bezierSegment5 = this.segments.elementAt(k);
            if (k > 0) {
                bezierSegment5.leftNeighbor = this.segments.elementAt(k - 1);
            }
            if (k < this.segments.size() - 1) {
                bezierSegment5.rightNeighbor = this.segments.elementAt(k + 1);
            }
        }
    }
    
    public BezierFunction(final String name, final double[][] array) {
        this.caseNum = -1;
        this.setName(name);
        super.arity = 1;
        (super.params = new Variable[1])[0] = new Variable("x");
        this.segments = new Vector();
        for (int i = 0; i < array.length; ++i) {
            final BezierSegment bezierSegment = new BezierSegment(array[i][0], array[i][3]);
            bezierSegment.set_y1(array[i][1]);
            bezierSegment.set_y2(array[i][4]);
            bezierSegment.set_d1(array[i][2]);
            bezierSegment.set_d2(array[i][5]);
            this.segments.addElement(bezierSegment);
        }
        for (int j = 0; j < array.length; ++j) {
            final BezierSegment bezierSegment2 = this.segments.elementAt(j);
            if (j > 0) {
                if (array[j][1] == array[j - 1][4]) {
                    bezierSegment2.leftNeighbor = this.segments.elementAt(j - 1);
                    if (array[j][2] == array[j - 1][5]) {
                        bezierSegment2.smoothOnLeft = true;
                    }
                }
                bezierSegment2.x1NotInDomain = true;
            }
            if (j < array.length - 1 && array[j][4] == array[j + 1][1]) {
                bezierSegment2.rightNeighbor = this.segments.elementAt(j + 1);
                if (array[j][5] == array[j + 1][2]) {
                    bezierSegment2.smoothOnRight = true;
                }
            }
        }
    }
    
    public void stealDataFrom(final BezierFunction bezierFunction) {
        super.preferredXmin = bezierFunction.preferredXmin;
        super.preferredXmax = bezierFunction.preferredXmax;
        super.preferredYmin = bezierFunction.preferredYmin;
        super.preferredYmax = bezierFunction.preferredYmax;
        this.derivativeLevel = bezierFunction.derivativeLevel;
        this.segments = bezierFunction.segments;
    }
    
    public double eval(final double n) {
        this.prevCaseNum = this.caseNum;
        final int size = this.segments.size();
        int i = 0;
        while (i < size) {
            final BezierSegment bezierSegment = this.segments.elementAt(i);
            final double x2 = bezierSegment.getX2();
            if (n < x2 || (n == x2 && bezierSegment.getClosedOnRight())) {
                this.caseNum = i;
                if (this.derivativeLevel == 0) {
                    return bezierSegment.value(n);
                }
                if (n == bezierSegment.getX1()) {
                    if (i == 0) {
                        return bezierSegment.derivativeValue(n, this.derivativeLevel);
                    }
                    BezierSegment bezierSegment2 = this.segments.elementAt(i - 1);
                    if (bezierSegment2.getX1() == bezierSegment2.getX2()) {
                        if (i == 1) {
                            return (bezierSegment2.value(n) == bezierSegment.value(n)) ? bezierSegment.derivativeValue(n, this.derivativeLevel) : Double.NaN;
                        }
                        bezierSegment2 = this.segments.elementAt(i - 2);
                    }
                    for (int j = 0; j < this.derivativeLevel; ++j) {
                        if (bezierSegment2.derivativeValue(n, j) != bezierSegment.derivativeValue(n, j)) {
                            return Double.NaN;
                        }
                    }
                    final double derivativeValue = bezierSegment2.derivativeValue(n, this.derivativeLevel);
                    if (derivativeValue == bezierSegment.derivativeValue(n, this.derivativeLevel)) {
                        return derivativeValue;
                    }
                    return Double.NaN;
                }
                else {
                    if (n < x2) {
                        return bezierSegment.derivativeValue(n, this.derivativeLevel);
                    }
                    if (i == size - 1) {
                        return bezierSegment.derivativeValue(n, this.derivativeLevel);
                    }
                    BezierSegment bezierSegment3 = this.segments.elementAt(i + 1);
                    if (bezierSegment3.getX1() == bezierSegment3.getX2()) {
                        if (i == size - 2) {
                            return (bezierSegment3.value(n) == bezierSegment.value(n)) ? bezierSegment.derivativeValue(n, this.derivativeLevel) : Double.NaN;
                        }
                        bezierSegment3 = this.segments.elementAt(i + 2);
                    }
                    for (int k = 0; k < this.derivativeLevel; ++k) {
                        if (bezierSegment3.derivativeValue(n, k) != bezierSegment.derivativeValue(n, k)) {
                            return Double.NaN;
                        }
                    }
                    final double derivativeValue2 = bezierSegment3.derivativeValue(n, this.derivativeLevel);
                    if (derivativeValue2 == bezierSegment.derivativeValue(n, this.derivativeLevel)) {
                        return derivativeValue2;
                    }
                    return Double.NaN;
                }
            }
            else {
                ++i;
            }
        }
        this.caseNum = size;
        return Double.NaN;
    }
    
    public double eval(final double n, final double n2) {
        throw new IllegalArgumentException("Wrong number or parameters in function evaluation.");
    }
    
    public double eval(final double[] array) {
        if (array == null || array.length != 1) {
            throw new IllegalArgumentException("Wrong number or parameters in function evaluation.");
        }
        return this.eval(array[0]);
    }
    
    public Function derivative(final int n) {
        if (n != 1) {
            throw new IllegalArgumentException("Can't take derivative of a 1-argument function w.r.t. argument number " + n + ".");
        }
        final BezierFunction bezierFunction2;
        final BezierFunction bezierFunction = bezierFunction2 = new BezierFunction(this);
        ++bezierFunction2.derivativeLevel;
        return bezierFunction;
    }
    
    public boolean checkCases() {
        return this.prevCaseNum == this.caseNum;
    }
    
    private static Point getHandleEndpoint(final int n, final int n2, final double n3, final boolean b) {
        double n4;
        if (Math.abs(n3) > 1.0E10) {
            n4 = 0.0;
        }
        else {
            n4 = 15.0 / Math.sqrt(1.0 + n3 * n3);
        }
        double sqrt = Math.sqrt(225.0 - n4 * n4);
        if (n3 < 0.0) {
            sqrt = -sqrt;
        }
        final double n5 = Math.round(n4);
        final double n6 = Math.round(sqrt);
        int n7;
        int n8;
        if (b) {
            n7 = n + (int)n5;
            n8 = n2 - (int)n6;
        }
        else {
            n7 = n - (int)n5;
            n8 = n2 + (int)n6;
        }
        return new Point(n7, n8);
    }
    
    private static void putHandle(final Graphics graphics, final int n, final int n2, final double n3, final boolean b) {
        final Point handleEndpoint = getHandleEndpoint(n, n2, n3, b);
        graphics.setColor(Color.lightGray);
        graphics.drawLine(n, n2, handleEndpoint.x, handleEndpoint.y);
        graphics.setColor(Color.black);
        graphics.fillRect(handleEndpoint.x - 1, handleEndpoint.y - 1, 3, 3);
    }
    
    public void drawDirect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final double n7, final double n8, final boolean b) {
        if (n2 <= n || n4 <= n3) {
            throw new IllegalArgumentException("Illegal drawing region.");
        }
        final double n9 = (n2 - n - 1) / (n6 - n5);
        final double n10 = (n4 - n3 - 1) / (n8 - n7);
        final double n11 = 1.0 / n9;
        int size;
        final int n12 = size = this.segments.size();
        for (int i = 0; i < n12; ++i) {
            if (((BezierSegment)this.segments.elementAt(i)).getX2() >= n5) {
                size = i;
                break;
            }
        }
        if (size >= n12) {
            return;
        }
        int n13 = size;
        for (int j = n12 - 1; j > size; --j) {
            if (((BezierSegment)this.segments.elementAt(j)).getX1() < n6) {
                n13 = j;
                break;
            }
        }
        if (b) {
            for (int k = size; k <= n13; ++k) {
                final BezierSegment bezierSegment = this.segments.elementAt(k);
                final double x1 = bezierSegment.getX1();
                final double x2 = bezierSegment.getX2();
                putHandle(graphics, (int)(n + (x1 - n5) * n9), (int)(n3 + (n8 - bezierSegment.getY1()) * n10), bezierSegment.derivativeValue(x1, 1) * n10 / n9, true);
                putHandle(graphics, (int)(n + (x2 - n5) * n9), (int)(n3 + (n8 - bezierSegment.getY2()) * n10), bezierSegment.derivativeValue(x2, 1) * n10 / n9, false);
            }
        }
        graphics.setColor(Color.magenta);
        for (int l = size; l <= n13; ++l) {
            final BezierSegment bezierSegment2 = this.segments.elementAt(l);
            final double x3 = bezierSegment2.getX1();
            final double y1 = bezierSegment2.getY1();
            int n14 = (int)(n + (x3 - n5) * n9);
            int n15 = (int)(n3 + (n8 - y1) * n10);
            final double x4 = bezierSegment2.getX2();
            for (double n16 = x3 + 3.0 * n11; n16 < x4; n16 += 3.0 * n11) {
                final double value = bezierSegment2.value(n16);
                final int n17 = (int)(n + (n16 - n5) * n9);
                final int n18 = (int)(n3 + (n8 - value) * n10);
                graphics.drawLine(n14, n15, n17, n18);
                n14 = n17;
                n15 = n18;
            }
            graphics.drawLine(n14, n15, (int)(n + (x4 - n5) * n9), (int)(n3 + (n8 - bezierSegment2.getY2()) * n10));
        }
        BezierSegment bezierSegment3 = this.segments.elementAt(size);
        final double x5 = bezierSegment3.getX1();
        final double y2 = bezierSegment3.getY1();
        final int n19 = (int)(n + (x5 - n5) * n9);
        final int n20 = (int)(n3 + (n8 - y2) * n10);
        graphics.setColor(bezierSegment3.getClosedOnLeft() ? Color.magenta : BezierFunction.lightMagenta);
        graphics.fillOval(n19 - 2, n20 - 2, 4, 4);
        for (int n21 = size + 1; n21 <= n13; ++n21) {
            final double x6 = bezierSegment3.getX2();
            final double y3 = bezierSegment3.getY2();
            final int n22 = (int)(n + (x6 - n5) * n9);
            final int n23 = (int)(n3 + (n8 - y3) * n10);
            final BezierSegment bezierSegment4 = this.segments.elementAt(n21);
            final double y4 = bezierSegment4.getY1();
            final int n24 = (int)(n3 + (n8 - y4) * n10);
            if (y4 == y3) {
                graphics.setColor(Color.magenta);
                graphics.fillRect(n22 - 2, n24 - 2, 4, 4);
            }
            else {
                graphics.setColor(bezierSegment3.getClosedOnRight() ? Color.magenta : BezierFunction.lightMagenta);
                graphics.fillOval(n22 - 2, n23 - 2, 4, 4);
                graphics.setColor(bezierSegment4.getClosedOnLeft() ? Color.magenta : BezierFunction.lightMagenta);
                graphics.fillOval(n22 - 2, n24 - 2, 4, 4);
            }
            bezierSegment3 = bezierSegment4;
        }
        final int n25 = (int)(n + (bezierSegment3.getX2() - n5) * n9);
        final int n26 = (int)(n3 + (n8 - bezierSegment3.getY2()) * n10);
        graphics.setColor(bezierSegment3.getClosedOnRight() ? Color.magenta : BezierFunction.lightMagenta);
        graphics.fillOval(n25 - 2, n26 - 2, 4, 4);
    }
    
    public void rescale(final double[] array, final double[] array2) {
        super.preferredXmin = array2[0];
        super.preferredXmax = array2[1];
        super.preferredYmin = array2[2];
        super.preferredYmax = array2[3];
        for (int i = 0; i < this.segments.size(); ++i) {
            ((BezierSegment)this.segments.elementAt(i)).rescale(array, array2);
        }
    }
    
    public boolean doNewPoint(final double n, final double n2, final double n3, final double n4) {
        for (int size = this.segments.size(), i = 0; i < size; ++i) {
            final BezierSegment bezierSegment = this.segments.elementAt(i);
            final BezierSegment doNewPoint = bezierSegment.doNewPoint(n, n2, n3, n4);
            if (doNewPoint != null) {
                this.segments.insertElementAt(doNewPoint, i + 1);
                return true;
            }
            if (n < bezierSegment.getX1()) {
                return false;
            }
        }
        return false;
    }
    
    public boolean findHit(final int n, final int n2, final CoordinateRect coordinateRect, final boolean b, final int n3) {
        final int size = this.segments.size();
        double n4 = n3 * 3;
        final double pixelWidth = coordinateRect.getPixelWidth();
        final double pixelHeight = coordinateRect.getPixelHeight();
        final double pixelToX = coordinateRect.pixelToX(n);
        final double pixelToY = coordinateRect.pixelToY(n2);
        for (int i = 0; i < size; ++i) {
            final BezierSegment bezierSegment = this.segments.elementAt(i);
            final double n5 = (pixelToX - bezierSegment.getX1()) / pixelWidth;
            final double n6 = (pixelToY - bezierSegment.getY1()) / pixelHeight;
            final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
            if (sqrt <= n3 && sqrt < n4) {
                this.segmentThatWasHit = bezierSegment;
                this.segmentNumThatWasHit = i;
                n4 = sqrt;
                this.itemOnLeftWasHit = true;
                this.endpointWasHit = true;
            }
            final double n7 = (pixelToX - bezierSegment.getX2()) / pixelWidth;
            final double n8 = (pixelToY - bezierSegment.getY2()) / pixelHeight;
            final double sqrt2 = Math.sqrt(n7 * n7 + n8 * n8);
            if (sqrt2 <= n3 && sqrt2 < n4) {
                this.segmentThatWasHit = bezierSegment;
                this.segmentNumThatWasHit = i;
                n4 = sqrt2;
                this.itemOnLeftWasHit = false;
                this.endpointWasHit = true;
            }
            if (b) {
                final Point handleEndpoint = getHandleEndpoint(coordinateRect.xToPixel(bezierSegment.getX1()), coordinateRect.yToPixel(bezierSegment.getY1()), bezierSegment.getD1() * pixelWidth / pixelHeight, true);
                final double n9 = n - handleEndpoint.x;
                final double n10 = n2 - handleEndpoint.y;
                final double sqrt3 = Math.sqrt(n9 * n9 + n10 * n10);
                if (sqrt3 <= n3 && sqrt3 < n4) {
                    this.segmentThatWasHit = bezierSegment;
                    this.segmentNumThatWasHit = i;
                    n4 = sqrt3;
                    this.itemOnLeftWasHit = true;
                    this.endpointWasHit = false;
                }
                final Point handleEndpoint2 = getHandleEndpoint(coordinateRect.xToPixel(bezierSegment.getX2()), coordinateRect.yToPixel(bezierSegment.getY2()), bezierSegment.getD2() * pixelWidth / pixelHeight, false);
                final double n11 = n - handleEndpoint2.x;
                final double n12 = n2 - handleEndpoint2.y;
                final double sqrt4 = Math.sqrt(n11 * n11 + n12 * n12);
                if (sqrt4 <= n3 && sqrt4 < n4) {
                    this.segmentThatWasHit = bezierSegment;
                    this.segmentNumThatWasHit = i;
                    n4 = sqrt4;
                    this.itemOnLeftWasHit = false;
                    this.endpointWasHit = false;
                }
            }
        }
        return n4 < n3 * 2;
    }
    
    public void doJoinAfterFindHit() {
        if (this.segmentThatWasHit == null || !this.endpointWasHit) {
            return;
        }
        this.segmentThatWasHit = null;
        if (this.itemOnLeftWasHit) {
            if (this.segmentNumThatWasHit == 0) {
                return;
            }
            if (this.segments.elementAt(this.segmentNumThatWasHit - 1).join(this.segments.elementAt(this.segmentNumThatWasHit))) {
                this.segments.removeElementAt(this.segmentNumThatWasHit);
            }
        }
        else {
            if (this.segmentNumThatWasHit == this.segments.size() - 1) {
                return;
            }
            if (this.segments.elementAt(this.segmentNumThatWasHit).join(this.segments.elementAt(this.segmentNumThatWasHit + 1))) {
                this.segments.removeElementAt(this.segmentNumThatWasHit + 1);
            }
        }
    }
    
    public void doBreakAfterFindHit(final CoordinateRect coordinateRect) {
        if (this.segmentThatWasHit == null) {
            return;
        }
        final double pixelWidth = coordinateRect.getPixelWidth();
        final double pixelHeight = coordinateRect.getPixelHeight();
        if (this.endpointWasHit) {
            if (this.itemOnLeftWasHit) {
                if (this.segmentThatWasHit.leftNeighbor == null) {
                    if (this.segmentNumThatWasHit > 1) {
                        final BezierSegment leftNeighbor = this.segments.elementAt(this.segmentNumThatWasHit - 1);
                        leftNeighbor.set_y2(this.segmentThatWasHit.getY1());
                        leftNeighbor.set_d2(this.segmentThatWasHit.getD1());
                        this.segmentThatWasHit.leftNeighbor = leftNeighbor;
                        leftNeighbor.rightNeighbor = this.segmentThatWasHit;
                        this.segmentThatWasHit.smoothOnLeft = true;
                        leftNeighbor.smoothOnRight = true;
                    }
                }
                else if (this.segmentNumThatWasHit > 1) {
                    final BezierSegment bezierSegment = this.segments.elementAt(this.segmentNumThatWasHit - 1);
                    this.segmentThatWasHit.leftNeighbor = null;
                    bezierSegment.rightNeighbor = null;
                    final int n = (this.segmentThatWasHit.getD1() < -1.0) ? -3 : 3;
                    this.segmentThatWasHit.set_y1(this.segmentThatWasHit.getY1() + n * pixelHeight);
                    bezierSegment.set_y2(bezierSegment.getY2() - n * pixelHeight);
                }
            }
            else {
                final int size = this.segments.size();
                if (this.segmentThatWasHit.rightNeighbor == null) {
                    if (this.segmentNumThatWasHit < size - 1) {
                        final BezierSegment rightNeighbor = this.segments.elementAt(this.segmentNumThatWasHit + 1);
                        rightNeighbor.set_y1(this.segmentThatWasHit.getY2());
                        rightNeighbor.set_d1(this.segmentThatWasHit.getD2());
                        this.segmentThatWasHit.rightNeighbor = rightNeighbor;
                        rightNeighbor.leftNeighbor = this.segmentThatWasHit;
                        this.segmentThatWasHit.smoothOnRight = true;
                        rightNeighbor.smoothOnLeft = true;
                    }
                }
                else if (this.segmentNumThatWasHit < size - 1) {
                    final BezierSegment bezierSegment2 = this.segments.elementAt(this.segmentNumThatWasHit + 1);
                    this.segmentThatWasHit.rightNeighbor = null;
                    bezierSegment2.leftNeighbor = null;
                    final int n2 = (this.segmentThatWasHit.getD2() < -1.0) ? -3 : 3;
                    this.segmentThatWasHit.set_y2(this.segmentThatWasHit.getY2() - n2 * pixelHeight);
                    bezierSegment2.set_y1(bezierSegment2.getY1() + n2 * pixelHeight);
                }
            }
        }
        else if (this.itemOnLeftWasHit && this.segmentNumThatWasHit > 0 && this.segmentThatWasHit.leftNeighbor != null) {
            final BezierSegment leftNeighbor2 = this.segmentThatWasHit.leftNeighbor;
            if (this.segmentThatWasHit.smoothOnLeft) {
                leftNeighbor2.smoothOnRight = false;
                this.segmentThatWasHit.smoothOnLeft = false;
                final int xToPixel = coordinateRect.xToPixel(this.segmentThatWasHit.getX1());
                final int yToPixel = coordinateRect.yToPixel(this.segmentThatWasHit.getY1());
                final Point handleEndpoint = getHandleEndpoint(xToPixel, yToPixel, leftNeighbor2.getD2() * pixelWidth / pixelHeight, false);
                if (this.segmentThatWasHit.getD1() < 0.0) {
                    leftNeighbor2.set_d2((yToPixel - (handleEndpoint.y + (xToPixel - handleEndpoint.x) * 0.2)) / (handleEndpoint.x - (yToPixel - handleEndpoint.y) * 0.2 - xToPixel) * pixelWidth / pixelWidth);
                }
                else {
                    leftNeighbor2.set_d2((yToPixel - (handleEndpoint.y - (xToPixel - handleEndpoint.x) * 0.2)) / (handleEndpoint.x + (yToPixel - handleEndpoint.y) * 0.2 - xToPixel) * pixelWidth / pixelWidth);
                }
            }
            else {
                leftNeighbor2.set_d2(this.segmentThatWasHit.getD1());
                leftNeighbor2.smoothOnRight = true;
                this.segmentThatWasHit.smoothOnLeft = true;
            }
        }
        else if (this.segmentThatWasHit.rightNeighbor != null) {
            final BezierSegment rightNeighbor2 = this.segmentThatWasHit.rightNeighbor;
            if (this.segmentThatWasHit.smoothOnRight) {
                rightNeighbor2.smoothOnLeft = false;
                this.segmentThatWasHit.smoothOnRight = false;
                final int xToPixel2 = coordinateRect.xToPixel(this.segmentThatWasHit.getX2());
                final int yToPixel2 = coordinateRect.yToPixel(this.segmentThatWasHit.getY2());
                final Point handleEndpoint2 = getHandleEndpoint(xToPixel2, yToPixel2, rightNeighbor2.getD1() * pixelWidth / pixelHeight, true);
                if (this.segmentThatWasHit.getD2() < 0.0) {
                    rightNeighbor2.set_d1((yToPixel2 - (handleEndpoint2.y + (xToPixel2 - handleEndpoint2.x) * 0.2)) / (handleEndpoint2.x - (yToPixel2 - handleEndpoint2.y) * 0.2 - xToPixel2) * pixelWidth / pixelWidth);
                }
                else {
                    rightNeighbor2.set_d1((yToPixel2 - (handleEndpoint2.y - (xToPixel2 - handleEndpoint2.x) * 0.2)) / (handleEndpoint2.x + (yToPixel2 - handleEndpoint2.y) * 0.2 - xToPixel2) * pixelWidth / pixelWidth);
                }
            }
            else {
                rightNeighbor2.set_d1(this.segmentThatWasHit.getD2());
                rightNeighbor2.smoothOnLeft = true;
                this.segmentThatWasHit.smoothOnRight = true;
            }
        }
        this.segmentThatWasHit = null;
    }
    
    public void continueDrag(final int n, final int n2, final CoordinateRect coordinateRect) {
        if (this.endpointWasHit) {
            final double pixelToY = coordinateRect.pixelToY(n2);
            if (this.itemOnLeftWasHit) {
                this.segmentThatWasHit.set_y1(pixelToY);
            }
            else {
                this.segmentThatWasHit.set_y2(pixelToY);
            }
        }
        else if (this.itemOnLeftWasHit) {
            final int xToPixel = coordinateRect.xToPixel(this.segmentThatWasHit.getX1());
            final int yToPixel = coordinateRect.yToPixel(this.segmentThatWasHit.getY1());
            double n3;
            if (n <= xToPixel) {
                if (this.segmentThatWasHit.getD1() > 0.0) {
                    n3 = 100.0 * coordinateRect.getPixelHeight() / coordinateRect.getPixelWidth();
                }
                else {
                    n3 = -100.0 * coordinateRect.getPixelHeight() / coordinateRect.getPixelWidth();
                }
            }
            else {
                n3 = -(n2 - yToPixel) / (n - xToPixel) * coordinateRect.getPixelWidth() / coordinateRect.getPixelHeight();
            }
            this.segmentThatWasHit.set_d1(n3);
        }
        else {
            final int xToPixel2 = coordinateRect.xToPixel(this.segmentThatWasHit.getX2());
            final int yToPixel2 = coordinateRect.yToPixel(this.segmentThatWasHit.getY2());
            double n4;
            if (n >= xToPixel2) {
                if (this.segmentThatWasHit.getD2() > 0.0) {
                    n4 = 100.0 * coordinateRect.getPixelHeight() / coordinateRect.getPixelWidth();
                }
                else {
                    n4 = -100.0 * coordinateRect.getPixelHeight() / coordinateRect.getPixelWidth();
                }
            }
            else {
                n4 = -(n2 - yToPixel2) / (n - xToPixel2) * coordinateRect.getPixelWidth() / coordinateRect.getPixelHeight();
            }
            this.segmentThatWasHit.set_d2(n4);
        }
    }
    
    public void endDrag() {
        this.segmentThatWasHit = null;
    }
}
