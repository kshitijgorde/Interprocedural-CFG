import java.awt.FontMetrics;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class CircuitElm implements Editable
{
    static double voltageRange;
    static int colorScaleCount;
    static Color[] colorScale;
    static double currentMult;
    static double powerMult;
    static Point ps1;
    static Point ps2;
    static CirSim sim;
    static Color whiteColor;
    static Color selectColor;
    static Color lightGrayColor;
    static Font unitsFont;
    public static NumberFormat showFormat;
    public static NumberFormat shortFormat;
    public static NumberFormat noCommaFormat;
    static final double pi = 3.141592653589793;
    int x;
    int y;
    int x2;
    int y2;
    int flags;
    int[] nodes;
    int voltSource;
    int dx;
    int dy;
    int dsign;
    double dn;
    double dpx1;
    double dpy1;
    Point point1;
    Point point2;
    Point lead1;
    Point lead2;
    double[] volts;
    double current;
    double curcount;
    Rectangle boundingBox;
    boolean noDiagonal;
    public boolean selected;
    
    int getDumpType() {
        return 0;
    }
    
    Class getDumpClass() {
        return this.getClass();
    }
    
    int getDefaultFlags() {
        return 0;
    }
    
    static void initClass(final CirSim sim) {
        CircuitElm.unitsFont = new Font("SansSerif", 0, 10);
        CircuitElm.sim = sim;
        CircuitElm.colorScale = new Color[CircuitElm.colorScaleCount];
        for (int i = 0; i != CircuitElm.colorScaleCount; ++i) {
            final double n = i * 2.0 / CircuitElm.colorScaleCount - 1.0;
            if (n < 0.0) {
                final int n2 = (int)(128.0 * -n) + 127;
                final int n3 = (int)(127.0 * (1.0 + n));
                CircuitElm.colorScale[i] = new Color(n2, n3, n3);
            }
            else {
                final int n4 = (int)(128.0 * n) + 127;
                final int n5 = (int)(127.0 * (1.0 - n));
                CircuitElm.colorScale[i] = new Color(n5, n4, n5);
            }
        }
        CircuitElm.ps1 = new Point();
        CircuitElm.ps2 = new Point();
        (CircuitElm.showFormat = NumberFormat.getInstance()).setMaximumFractionDigits(2);
        (CircuitElm.shortFormat = NumberFormat.getInstance()).setMaximumFractionDigits(1);
        (CircuitElm.noCommaFormat = NumberFormat.getInstance()).setMaximumFractionDigits(10);
        CircuitElm.noCommaFormat.setGroupingUsed(false);
    }
    
    CircuitElm(final int n, final int n2) {
        this.x2 = n;
        this.x = n;
        this.y2 = n2;
        this.y = n2;
        this.flags = this.getDefaultFlags();
        this.allocNodes();
        this.initBoundingBox();
    }
    
    CircuitElm(final int x, final int y, final int x2, final int y2, final int flags) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.flags = flags;
        this.allocNodes();
        this.initBoundingBox();
    }
    
    void initBoundingBox() {
        (this.boundingBox = new Rectangle()).setBounds(min(this.x, this.x2), min(this.y, this.y2), abs(this.x2 - this.x) + 1, abs(this.y2 - this.y) + 1);
    }
    
    void allocNodes() {
        this.nodes = new int[this.getPostCount() + this.getInternalNodeCount()];
        this.volts = new double[this.getPostCount() + this.getInternalNodeCount()];
    }
    
    String dump() {
        final int dumpType = this.getDumpType();
        return ((dumpType < 127) ? ((char)dumpType + " ") : (dumpType + " ")) + this.x + " " + this.y + " " + this.x2 + " " + this.y2 + " " + this.flags;
    }
    
    void reset() {
        for (int i = 0; i != this.getPostCount() + this.getInternalNodeCount(); ++i) {
            this.volts[i] = 0.0;
        }
        this.curcount = 0.0;
    }
    
    void draw(final Graphics graphics) {
    }
    
    void setCurrent(final int n, final double current) {
        this.current = current;
    }
    
    double getCurrent() {
        return this.current;
    }
    
    void doStep() {
    }
    
    void delete() {
    }
    
    void startIteration() {
    }
    
    double getPostVoltage(final int n) {
        return this.volts[n];
    }
    
    void setNodeVoltage(final int n, final double n2) {
        this.volts[n] = n2;
        this.calculateCurrent();
    }
    
    void calculateCurrent() {
    }
    
    void setPoints() {
        this.dx = this.x2 - this.x;
        this.dy = this.y2 - this.y;
        this.dn = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        this.dpx1 = this.dy / this.dn;
        this.dpy1 = -this.dx / this.dn;
        this.dsign = ((this.dy == 0) ? sign(this.dx) : sign(this.dy));
        this.point1 = new Point(this.x, this.y);
        this.point2 = new Point(this.x2, this.y2);
    }
    
    void calcLeads(final int n) {
        if (this.dn < n || n == 0) {
            this.lead1 = this.point1;
            this.lead2 = this.point2;
            return;
        }
        this.lead1 = this.interpPoint(this.point1, this.point2, (this.dn - n) / (2.0 * this.dn));
        this.lead2 = this.interpPoint(this.point1, this.point2, (this.dn + n) / (2.0 * this.dn));
    }
    
    Point interpPoint(final Point point, final Point point2, final double n) {
        final Point point3 = new Point();
        this.interpPoint(point, point2, point3, n);
        return point3;
    }
    
    void interpPoint(final Point point, final Point point2, final Point point3, final double n) {
        final int n2 = point2.x - point.x;
        final int n3 = point2.y - point.y;
        point3.x = (int)Math.floor(point.x * (1.0 - n) + point2.x * n + 0.48);
        point3.y = (int)Math.floor(point.y * (1.0 - n) + point2.y * n + 0.48);
    }
    
    void interpPoint(final Point point, final Point point2, final Point point3, final double n, double n2) {
        final int n3 = point2.x - point.x;
        final int n4 = point2.y - point.y;
        final int n5 = point2.y - point.y;
        final int n6 = point.x - point2.x;
        n2 /= Math.sqrt(n5 * n5 + n6 * n6);
        point3.x = (int)Math.floor(point.x * (1.0 - n) + point2.x * n + n2 * n5 + 0.48);
        point3.y = (int)Math.floor(point.y * (1.0 - n) + point2.y * n + n2 * n6 + 0.48);
    }
    
    Point interpPoint(final Point point, final Point point2, final double n, final double n2) {
        final Point point3 = new Point();
        this.interpPoint(point, point2, point3, n, n2);
        return point3;
    }
    
    void interpPoint2(final Point point, final Point point2, final Point point3, final Point point4, final double n, double n2) {
        final int n3 = point2.x - point.x;
        final int n4 = point2.y - point.y;
        final int n5 = point2.y - point.y;
        final int n6 = point.x - point2.x;
        n2 /= Math.sqrt(n5 * n5 + n6 * n6);
        point3.x = (int)Math.floor(point.x * (1.0 - n) + point2.x * n + n2 * n5 + 0.48);
        point3.y = (int)Math.floor(point.y * (1.0 - n) + point2.y * n + n2 * n6 + 0.48);
        point4.x = (int)Math.floor(point.x * (1.0 - n) + point2.x * n - n2 * n5 + 0.48);
        point4.y = (int)Math.floor(point.y * (1.0 - n) + point2.y * n - n2 * n6 + 0.48);
    }
    
    void draw2Leads(final Graphics graphics) {
        this.setVoltageColor(graphics, this.volts[0]);
        drawThickLine(graphics, this.point1, this.lead1);
        this.setVoltageColor(graphics, this.volts[1]);
        drawThickLine(graphics, this.lead2, this.point2);
    }
    
    Point[] newPointArray(int i) {
        Point[] array;
        for (array = new Point[i]; i > 0; array[--i] = new Point()) {}
        return array;
    }
    
    void drawDots(final Graphics graphics, final Point point, final Point point2, double n) {
        if (CircuitElm.sim.stoppedCheck.getState() || n == 0.0 || !CircuitElm.sim.dotsCheckItem.getState()) {
            return;
        }
        final int n2 = point2.x - point.x;
        final int n3 = point2.y - point.y;
        final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
        graphics.setColor(Color.yellow);
        final int n4 = 16;
        n %= n4;
        if (n < 0.0) {
            n += n4;
        }
        for (double n5 = n; n5 < sqrt; n5 += n4) {
            graphics.fillRect((int)(point.x + n5 * n2 / sqrt) - 1, (int)(point.y + n5 * n3 / sqrt) - 1, 4, 4);
        }
    }
    
    Polygon calcArrow(final Point point, final Point point2, final double n, final double n2) {
        final Polygon polygon = new Polygon();
        final Point point3 = new Point();
        final Point point4 = new Point();
        final int n3 = point2.x - point.x;
        final int n4 = point2.y - point.y;
        final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        polygon.addPoint(point2.x, point2.y);
        this.interpPoint2(point, point2, point3, point4, 1.0 - n / sqrt, n2);
        polygon.addPoint(point3.x, point3.y);
        polygon.addPoint(point4.x, point4.y);
        return polygon;
    }
    
    Polygon createPolygon(final Point point, final Point point2, final Point point3) {
        final Polygon polygon = new Polygon();
        polygon.addPoint(point.x, point.y);
        polygon.addPoint(point2.x, point2.y);
        polygon.addPoint(point3.x, point3.y);
        return polygon;
    }
    
    Polygon createPolygon(final Point point, final Point point2, final Point point3, final Point point4) {
        final Polygon polygon = new Polygon();
        polygon.addPoint(point.x, point.y);
        polygon.addPoint(point2.x, point2.y);
        polygon.addPoint(point3.x, point3.y);
        polygon.addPoint(point4.x, point4.y);
        return polygon;
    }
    
    Polygon createPolygon(final Point[] array) {
        final Polygon polygon = new Polygon();
        for (int i = 0; i != array.length; ++i) {
            polygon.addPoint(array[i].x, array[i].y);
        }
        return polygon;
    }
    
    void drag(int x2, int y2) {
        x2 = CircuitElm.sim.snapGrid(x2);
        y2 = CircuitElm.sim.snapGrid(y2);
        if (this.noDiagonal) {
            if (Math.abs(this.x - x2) < Math.abs(this.y - y2)) {
                x2 = this.x;
            }
            else {
                y2 = this.y;
            }
        }
        this.x2 = x2;
        this.y2 = y2;
        this.setPoints();
    }
    
    void move(final int n, final int n2) {
        this.x += n;
        this.y += n2;
        this.x2 += n;
        this.y2 += n2;
        this.boundingBox.move(n, n2);
        this.setPoints();
    }
    
    boolean allowMove(final int n, final int n2) {
        final int n3 = this.x + n;
        final int n4 = this.y + n2;
        final int n5 = this.x2 + n;
        final int n6 = this.y2 + n2;
        for (int i = 0; i != CircuitElm.sim.elmList.size(); ++i) {
            final CircuitElm elm = CircuitElm.sim.getElm(i);
            if (elm.x == n3 && elm.y == n4 && elm.x2 == n5 && elm.y2 == n6) {
                return false;
            }
            if (elm.x == n5 && elm.y == n6 && elm.x2 == n3 && elm.y2 == n4) {
                return false;
            }
        }
        return true;
    }
    
    void movePoint(final int n, final int n2, final int n3) {
        if (n == 0) {
            this.x += n2;
            this.y += n3;
        }
        else {
            this.x2 += n2;
            this.y2 += n3;
        }
        this.setPoints();
    }
    
    void drawPosts(final Graphics graphics) {
        for (int i = 0; i != this.getPostCount(); ++i) {
            final Point post = this.getPost(i);
            this.drawPost(graphics, post.x, post.y, this.nodes[i]);
        }
    }
    
    void stamp() {
    }
    
    int getVoltageSourceCount() {
        return 0;
    }
    
    int getInternalNodeCount() {
        return 0;
    }
    
    void setNode(final int n, final int n2) {
        this.nodes[n] = n2;
    }
    
    void setVoltageSource(final int n, final int voltSource) {
        this.voltSource = voltSource;
    }
    
    int getVoltageSource() {
        return this.voltSource;
    }
    
    double getVoltageDiff() {
        return this.volts[0] - this.volts[1];
    }
    
    boolean nonLinear() {
        return false;
    }
    
    int getPostCount() {
        return 2;
    }
    
    int getNode(final int n) {
        return this.nodes[n];
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.point2 : null);
    }
    
    void drawPost(final Graphics graphics, final int n, final int n2, final int n3) {
        if (CircuitElm.sim.dragElm == null && !this.needsHighlight() && CircuitElm.sim.getCircuitNode(n3).links.size() == 2) {
            return;
        }
        if (CircuitElm.sim.mouseMode == 2 || CircuitElm.sim.mouseMode == 3) {
            return;
        }
        this.drawPost(graphics, n, n2);
    }
    
    void drawPost(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(CircuitElm.whiteColor);
        graphics.fillOval(n - 3, n2 - 3, 7, 7);
    }
    
    void setBbox(int n, int n2, int n3, int n4) {
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        this.boundingBox.setBounds(n, n2, n3 - n + 1, n4 - n2 + 1);
    }
    
    void setBbox(final Point point, final Point point2, final double n) {
        this.setBbox(point.x, point.y, point2.x, point2.y);
        final int n2 = point2.y - point.y;
        final int n3 = point.x - point2.x;
        final int n4 = (int)(this.dpx1 * n);
        final int n5 = (int)(this.dpy1 * n);
        this.adjustBbox(point.x + n4, point.y + n5, point.x - n4, point.y - n5);
    }
    
    void adjustBbox(int min, int min2, int max, int max2) {
        if (min > max) {
            final int n = min;
            min = max;
            max = n;
        }
        if (min2 > max2) {
            final int n2 = min2;
            min2 = max2;
            max2 = n2;
        }
        min = min(this.boundingBox.x, min);
        min2 = min(this.boundingBox.y, min2);
        max = max(this.boundingBox.x + this.boundingBox.width - 1, max);
        max2 = max(this.boundingBox.y + this.boundingBox.height - 1, max2);
        this.boundingBox.setBounds(min, min2, max - min, max2 - min2);
    }
    
    void adjustBbox(final Point point, final Point point2) {
        this.adjustBbox(point.x, point.y, point2.x, point2.y);
    }
    
    boolean isCenteredText() {
        return false;
    }
    
    void drawCenteredText(final Graphics graphics, final String s, int n, final int n2, final boolean b) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(s);
        if (b) {
            n -= stringWidth / 2;
        }
        graphics.drawString(s, n, n2 + fontMetrics.getAscent() / 2);
        this.adjustBbox(n, n2 - fontMetrics.getAscent() / 2, n + stringWidth, n2 + fontMetrics.getAscent() / 2 + fontMetrics.getDescent());
    }
    
    void drawValues(final Graphics graphics, final String s, final double n) {
        if (s == null) {
            return;
        }
        graphics.setFont(CircuitElm.unitsFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(s);
        graphics.setColor(CircuitElm.whiteColor);
        final int n2 = fontMetrics.getAscent() / 2;
        int x2;
        int y2;
        if (this instanceof RailElm || this instanceof SweepElm) {
            x2 = this.x2;
            y2 = this.y2;
        }
        else {
            x2 = (this.x2 + this.x) / 2;
            y2 = (this.y2 + this.y) / 2;
        }
        final int n3 = (int)(this.dpx1 * n);
        final int n4 = (int)(this.dpy1 * n);
        if (n3 == 0) {
            graphics.drawString(s, x2 - stringWidth / 2, y2 - abs(n4) - 2);
        }
        else {
            int n5 = x2 + abs(n3) + 2;
            if (this instanceof VoltageElm || (this.x < this.x2 && this.y > this.y2)) {
                n5 = x2 - (stringWidth + abs(n3) + 2);
            }
            graphics.drawString(s, n5, y2 + n4 + n2);
        }
    }
    
    void drawCoil(final Graphics graphics, final int n, final Point location, final Point point, final double n2, final double n3) {
        distance(location, point);
        final int n4 = 30;
        final double n5 = 1.0 / n4;
        CircuitElm.ps1.setLocation(location);
        for (int i = 0; i != n4; ++i) {
            final double n6 = (i + 1) * 6.0 * n5 % 2.0 - 1.0;
            double sqrt = Math.sqrt(1.0 - n6 * n6);
            if (sqrt < 0.0) {
                sqrt = -sqrt;
            }
            this.interpPoint(location, point, CircuitElm.ps2, i * n5, sqrt * n);
            this.setVoltageColor(graphics, n2 + (n3 - n2) * i / n4);
            drawThickLine(graphics, CircuitElm.ps1, CircuitElm.ps2);
            CircuitElm.ps1.setLocation(CircuitElm.ps2);
        }
    }
    
    static void drawThickLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2, n3, n4);
        graphics.drawLine(n + 1, n2, n3 + 1, n4);
        graphics.drawLine(n, n2 + 1, n3, n4 + 1);
        graphics.drawLine(n + 1, n2 + 1, n3 + 1, n4 + 1);
    }
    
    static void drawThickLine(final Graphics graphics, final Point point, final Point point2) {
        graphics.drawLine(point.x, point.y, point2.x, point2.y);
        graphics.drawLine(point.x + 1, point.y, point2.x + 1, point2.y);
        graphics.drawLine(point.x, point.y + 1, point2.x, point2.y + 1);
        graphics.drawLine(point.x + 1, point.y + 1, point2.x + 1, point2.y + 1);
    }
    
    static void drawThickPolygon(final Graphics graphics, final int[] array, final int[] array2, final int n) {
        int i;
        for (i = 0; i != n - 1; ++i) {
            drawThickLine(graphics, array[i], array2[i], array[i + 1], array2[i + 1]);
        }
        drawThickLine(graphics, array[i], array2[i], array[0], array2[0]);
    }
    
    static void drawThickPolygon(final Graphics graphics, final Polygon polygon) {
        drawThickPolygon(graphics, polygon.xpoints, polygon.ypoints, polygon.npoints);
    }
    
    static void drawThickCircle(final Graphics graphics, final int n, final int n2, final int n3) {
        final double n4 = 0.017453292519943295;
        final double n5 = n3 * 0.98;
        for (int i = 0; i != 360; i += 20) {
            drawThickLine(graphics, (int)(Math.cos(i * n4) * n5 + n), (int)(Math.sin(i * n4) * n5 + n2), (int)(Math.cos((i + 20) * n4) * n5 + n), (int)(Math.sin((i + 20) * n4) * n5 + n2));
        }
    }
    
    static String getVoltageDText(final double n) {
        return getUnitText(Math.abs(n), "V");
    }
    
    static String getVoltageText(final double n) {
        return getUnitText(n, "V");
    }
    
    static String getUnitText(final double n, final String s) {
        final double abs = Math.abs(n);
        if (abs < 1.0E-14) {
            return "0 " + s;
        }
        if (abs < 1.0E-9) {
            return CircuitElm.showFormat.format(n * 1.0E12) + " p" + s;
        }
        if (abs < 1.0E-6) {
            return CircuitElm.showFormat.format(n * 1.0E9) + " n" + s;
        }
        if (abs < 0.001) {
            return CircuitElm.showFormat.format(n * 1000000.0) + " " + CirSim.muString + s;
        }
        if (abs < 1.0) {
            return CircuitElm.showFormat.format(n * 1000.0) + " m" + s;
        }
        if (abs < 1000.0) {
            return CircuitElm.showFormat.format(n) + " " + s;
        }
        if (abs < 1000000.0) {
            return CircuitElm.showFormat.format(n * 0.001) + " k" + s;
        }
        if (abs < 1.0E9) {
            return CircuitElm.showFormat.format(n * 1.0E-6) + " M" + s;
        }
        return CircuitElm.showFormat.format(n * 1.0E-9) + " G" + s;
    }
    
    static String getShortUnitText(final double n, final String s) {
        final double abs = Math.abs(n);
        if (abs < 1.0E-13) {
            return null;
        }
        if (abs < 1.0E-9) {
            return CircuitElm.shortFormat.format(n * 1.0E12) + "p" + s;
        }
        if (abs < 1.0E-6) {
            return CircuitElm.shortFormat.format(n * 1.0E9) + "n" + s;
        }
        if (abs < 0.001) {
            return CircuitElm.shortFormat.format(n * 1000000.0) + CirSim.muString + s;
        }
        if (abs < 1.0) {
            return CircuitElm.shortFormat.format(n * 1000.0) + "m" + s;
        }
        if (abs < 1000.0) {
            return CircuitElm.shortFormat.format(n) + s;
        }
        if (abs < 1000000.0) {
            return CircuitElm.shortFormat.format(n * 0.001) + "k" + s;
        }
        if (abs < 1.0E9) {
            return CircuitElm.shortFormat.format(n * 1.0E-6) + "M" + s;
        }
        return CircuitElm.shortFormat.format(n * 1.0E-9) + "G" + s;
    }
    
    static String getCurrentText(final double n) {
        return getUnitText(n, "A");
    }
    
    static String getCurrentDText(final double n) {
        return getUnitText(Math.abs(n), "A");
    }
    
    void updateDotCount() {
        this.curcount = this.updateDotCount(this.current, this.curcount);
    }
    
    double updateDotCount(final double n, final double n2) {
        if (CircuitElm.sim.stoppedCheck.getState()) {
            return n2;
        }
        return n2 + n * CircuitElm.currentMult % 8.0;
    }
    
    void doDots(final Graphics graphics) {
        this.updateDotCount();
        if (CircuitElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.point2, this.curcount);
        }
    }
    
    void doAdjust() {
    }
    
    void setupAdjust() {
    }
    
    void getInfo(final String[] array) {
    }
    
    int getBasicInfo(final String[] array) {
        array[1] = "I = " + getCurrentDText(this.getCurrent());
        array[2] = "Vd = " + getVoltageDText(this.getVoltageDiff());
        return 3;
    }
    
    void setVoltageColor(final Graphics graphics, final double n) {
        if (this.needsHighlight()) {
            graphics.setColor(CircuitElm.selectColor);
            return;
        }
        if (!CircuitElm.sim.voltsCheckItem.getState()) {
            if (!CircuitElm.sim.powerCheckItem.getState()) {
                graphics.setColor(CircuitElm.whiteColor);
            }
            return;
        }
        int n2 = (int)((n + CircuitElm.voltageRange) * (CircuitElm.colorScaleCount - 1) / (CircuitElm.voltageRange * 2.0));
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= CircuitElm.colorScaleCount) {
            n2 = CircuitElm.colorScaleCount - 1;
        }
        graphics.setColor(CircuitElm.colorScale[n2]);
    }
    
    void setPowerColor(final Graphics graphics, final boolean b) {
        if (!CircuitElm.sim.powerCheckItem.getState()) {
            return;
        }
        this.setPowerColor(graphics, this.getPower());
    }
    
    void setPowerColor(final Graphics graphics, double n) {
        n *= CircuitElm.powerMult;
        double n2 = (n < 0.0) ? (-n) : n;
        if (n2 > 1.0) {
            n2 = 1.0;
        }
        final int n3 = 128 + (int)(n2 * 127.0);
        final int n4 = (int)(128.0 * (1.0 - n2));
        if (n > 0.0) {
            graphics.setColor(new Color(n3, n4, n4));
        }
        else {
            graphics.setColor(new Color(n4, n3, n4));
        }
    }
    
    void setConductanceColor(final Graphics graphics, double n) {
        n *= CircuitElm.powerMult;
        double n2 = (n < 0.0) ? (-n) : n;
        if (n2 > 1.0) {
            n2 = 1.0;
        }
        final int n3 = (int)(n2 * 255.0);
        graphics.setColor(new Color(n3, n3, n3));
    }
    
    double getPower() {
        return this.getVoltageDiff() * this.current;
    }
    
    double getScopeValue(final int n) {
        return (n == 1) ? this.getPower() : this.getVoltageDiff();
    }
    
    String getScopeUnits(final int n) {
        return (n == 1) ? "W" : "V";
    }
    
    public EditInfo getEditInfo(final int n) {
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
    }
    
    boolean getConnection(final int n, final int n2) {
        return true;
    }
    
    boolean hasGroundConnection(final int n) {
        return false;
    }
    
    boolean isWire() {
        return false;
    }
    
    boolean canViewInScope() {
        return this.getPostCount() <= 2;
    }
    
    boolean comparePair(final int n, final int n2, final int n3, final int n4) {
        return (n == n3 && n2 == n4) || (n == n4 && n2 == n3);
    }
    
    boolean needsHighlight() {
        return CircuitElm.sim.mouseElm == this || this.selected;
    }
    
    boolean isSelected() {
        return this.selected;
    }
    
    void setSelected(final boolean selected) {
        this.selected = selected;
    }
    
    void selectRect(final Rectangle rectangle) {
        this.selected = rectangle.intersects(this.boundingBox);
    }
    
    static int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    static int sign(final int n) {
        return (n < 0) ? -1 : ((n == 0) ? 0 : 1);
    }
    
    static int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    static int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    static double distance(final Point point, final Point point2) {
        final double n = point.x - point2.x;
        final double n2 = point.y - point2.y;
        return Math.sqrt(n * n + n2 * n2);
    }
    
    Rectangle getBoundingBox() {
        return this.boundingBox;
    }
    
    boolean needsShortcut() {
        return false;
    }
    
    static {
        CircuitElm.voltageRange = 5.0;
        CircuitElm.colorScaleCount = 32;
    }
}
