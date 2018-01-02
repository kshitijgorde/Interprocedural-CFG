import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import util.ImageHolder;
import java.awt.Component;
import java.awt.Point;
import java.awt.Color;
import util.ImageBlot;
import util.ImageBlotter;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Design extends Observable implements Runnable
{
    public static final int OUTSIDE_MODE = 0;
    public static final int INSIDE_CONTRA_MODE = 1;
    public static final int INSIDE_MODE = 2;
    ImageBlotter blotter;
    ImageBlot blot;
    Mobile m;
    Stabile s;
    double initAngle;
    double maxAngle;
    Color fgColor;
    Color bgColor;
    PPoint center;
    double canvasSizeW;
    int canvasSizePixels;
    int sideMode;
    boolean adaptive;
    double angleQuantum;
    int stepCount;
    boolean completed;
    int blotCount;
    
    protected void init(final Mobile m, final Stabile s, final double initAngle, final double maxAngle, final double canvasSizeW, final int canvasSizePixels, final double n, final double n2, final double angleQuantum, final int sideMode) {
        this.completed = false;
        this.adaptive = false;
        this.m = m;
        this.s = s;
        this.initAngle = initAngle;
        this.maxAngle = maxAngle;
        this.sideMode = sideMode;
        this.canvasSizeW = canvasSizeW;
        this.canvasSizePixels = canvasSizePixels;
        this.center = this.s.getCenter();
        this.blot = new ImageBlot(n, n2);
        this.blotter = new ImageBlotter(this.canvasSizeW / 2.0, this.canvasSizeW / 2.0, canvasSizePixels, canvasSizePixels, Color.black, Color.white, this.blot);
        this.angleQuantum = angleQuantum;
    }
    
    public Design(final Mobile mobile, final Stabile stabile, final double n, final double n2, final double n3, final int n4, final double n5, final double n6, final int n7, final int n8) {
        this.init(mobile, stabile, n, n + 6.283185307179586 * n2, n3, n4, n5 / 2.0, n6 / 2.0, 6.283185307179586 / n7, n8);
    }
    
    public Design(final int n, final int n2, final int n3, final int n4, final int n5, final Point point, final int n6, final boolean b) {
        final double n7 = n / 2.0;
        final double n8 = n7 * (n2 / 100.0);
        final double n9 = n3 / 2.0;
        final PPoint pPoint = new PPoint(point.x - n5 / 2.0, point.y - n5 / 2.0);
        final double n10 = n4 * 6.283185307179586;
        final CircleMobile circleMobile = new CircleMobile(n7, n8);
        final CircleStabile circleStabile = new CircleStabile(pPoint, n9);
        final double n11 = n6 / 2.0;
        this.init(circleMobile, circleStabile, 0.0, n10, n5 * 1.0, n5, n11, 1.5 * n11, 3.141592653589793 / (180.0 / n6), b ? 2 : 0);
    }
    
    public void setAdaptive(final boolean adaptive) {
        this.adaptive = adaptive;
    }
    
    public boolean isAdaptive() {
        return this.adaptive;
    }
    
    public boolean isCompleted() {
        return this.completed;
    }
    
    public void run() {
        try {
            this.blotCount = 0;
            final long currentTimeMillis = System.currentTimeMillis();
            final PPoint pPoint = new PPoint();
            final PPoint pPoint2 = new PPoint();
            this.stepCount = (int)Math.rint((this.maxAngle - this.initAngle) / this.angleQuantum + 0.5);
            int n = this.stepCount / 50;
            if (n < 1) {
                n = 1;
            }
            int i = 0;
            while (i < this.stepCount) {
                this.doStep(i, pPoint, pPoint2);
                if (++i % n == 0) {
                    this.message(new Integer(i));
                }
            }
            this.completed = true;
            this.message("Done (" + (System.currentTimeMillis() - currentTimeMillis) + "ms. elapsed time, " + this.blotCount + " points applied.)");
        }
        catch (OutOfMemoryError outOfMemoryError) {
            this.blotter.release();
            System.gc();
            this.message("Error: ran out of memory.");
        }
    }
    
    private final void doStep(final int n, final PPoint pPoint, final PPoint pPoint2) {
        final boolean b = this.sideMode == 2;
        final boolean b2 = this.sideMode != 0;
        if (!this.adaptive || n == 0) {
            final double angleToDistance = this.m.angleToDistance(this.initAngle + n * this.angleQuantum);
            double n2;
            if (b) {
                n2 = -angleToDistance;
            }
            else {
                n2 = angleToDistance;
            }
            this.m.findPen(this.s.distanceToPoint(n2), this.s.distanceToNormal(n2, b2), angleToDistance, b, pPoint);
            this.blotter.applyBlot(pPoint.x, pPoint.y);
            ++this.blotCount;
            pPoint2.x = pPoint.x;
            pPoint2.y = pPoint.y;
        }
        else {
            double angleQuantum = this.angleQuantum;
            final double inverseTransformWidth = this.blotter.inverseTransformWidth(this.blot.fgrad + this.blot.bgrad);
            int n3 = 1;
            double distanceTo;
            do {
                final double angleToDistance2 = this.m.angleToDistance(this.initAngle + ((n - 1) * n3 + 1) * angleQuantum);
                double n4;
                if (b) {
                    n4 = -angleToDistance2;
                }
                else {
                    n4 = angleToDistance2;
                }
                this.m.findPen(this.s.distanceToPoint(n4), this.s.distanceToNormal(n4, b2), angleToDistance2, b, pPoint);
                distanceTo = pPoint.distanceTo(pPoint2);
                if (distanceTo > inverseTransformWidth) {
                    n3 *= 2;
                    angleQuantum /= 2.0;
                }
            } while (distanceTo > inverseTransformWidth && n3 < 8);
            if (n3 == 1) {
                this.blotter.applyBlot(pPoint.x, pPoint.y);
                pPoint2.x = pPoint.x;
                pPoint2.y = pPoint.y;
                ++this.blotCount;
            }
            else {
                for (int i = 1; i <= n3; ++i) {
                    final double angleToDistance3 = this.m.angleToDistance(this.initAngle + ((n - 1) * n3 + i) * angleQuantum);
                    double n5;
                    if (b) {
                        n5 = -angleToDistance3;
                    }
                    else {
                        n5 = angleToDistance3;
                    }
                    this.m.findPen(this.s.distanceToPoint(n5), this.s.distanceToNormal(n5, b2), angleToDistance3, b, pPoint);
                    this.blotter.applyBlot(pPoint.x, pPoint.y);
                    ++this.blotCount;
                    pPoint2.x = pPoint.x;
                    pPoint2.y = pPoint.y;
                }
            }
        }
    }
    
    public String formatMessage(final Object o) {
        if (o instanceof Number) {
            final int intValue = ((Number)o).intValue();
            final StringBuffer sb = new StringBuffer();
            sb.append(intValue);
            sb.append(" of ");
            sb.append(this.stepCount);
            sb.append(" (");
            sb.append(intValue * 100 / this.stepCount);
            sb.append("%)");
            return sb.toString();
        }
        return o.toString();
    }
    
    public ImageHolder getImage(final Component component, final Color color, final Color color2, final boolean b) {
        final int[] array = new int[2];
        this.blotter.setColors(color, color2);
        return new ImageHolder(array[0], array[1], component.createImage(this.blotter.getProducer(null, b, array)));
    }
    
    public ImageBlotter getBlotter() {
        return this.blotter;
    }
    
    public void release() {
        this.blotter.release();
    }
    
    public void message(final Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }
}
