// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import xfunctions.functions.Expression;
import xfunctions.functions.Variable;
import xfunctions.graphs.DisplayCanvas;

class Graph3DCanvas extends DisplayCanvas implements Runnable
{
    Variable xVar;
    Variable yVar;
    Expression zExp;
    int zRotate;
    int yRotate;
    double xmin;
    double xmax;
    double ymin;
    double ymax;
    double zmin;
    double zmax;
    int style;
    int gridSize;
    private Thread runner;
    private boolean die;
    private boolean drawing;
    private boolean axesOn;
    private long lastScrollTime;
    private int[] xPoly;
    private int[] yPoly;
    private Point tempPt;
    private double[][] points;
    private static final int scrollDelay = 500;
    private boolean doingScrollDelay;
    private double zSine;
    private double zCosine;
    private double ySine;
    private double yCosine;
    private double xFactor;
    private double yFactor;
    private double zFactor;
    private double xCenter;
    private double yCenter;
    private double zCenter;
    private int xStart;
    private int yStart;
    private int xStop;
    private int yStop;
    private int xDir;
    private int yDir;
    private double deltaX;
    private double deltaY;
    private int currentX;
    private int currentY;
    private boolean xChangesQuicker;
    private static final Color lightBlue;
    private Color fillColor;
    
    static {
        lightBlue = new Color(200, 200, 255);
    }
    
    Graph3DCanvas(final Variable xVar, final Variable yVar) {
        this.zExp = null;
        this.style = 1;
        this.gridSize = 21;
        this.drawing = false;
        this.axesOn = true;
        this.lastScrollTime = 0L;
        this.xPoly = new int[4];
        this.yPoly = new int[4];
        this.tempPt = new Point(0, 0);
        this.points = new double[4][3];
        this.doingScrollDelay = false;
        this.xVar = xVar;
        this.yVar = yVar;
    }
    
    public void releaseResources() {
        if (this.runner != null && this.runner.isAlive()) {
            this.die = true;
            synchronized (this) {
                this.notify();
                try {
                    this.wait(300L);
                }
                catch (InterruptedException ex) {}
                if (this.die) {
                    this.runner.stop();
                    System.out.println("Had to force graph3D runner to stop.");
                }
            }
        }
        this.runner = null;
        this.die = false;
        super.releaseResources();
    }
    
    synchronized void setStyle(final int style) {
        if (style != this.style) {
            this.style = style;
            this.invalidateCanvas();
        }
    }
    
    synchronized void setGridSize(final int n) {
        if (n != this.gridSize) {
            this.gridSize = n + 1;
            this.invalidateCanvas();
        }
    }
    
    synchronized void setExpression(final Expression zExp) {
        if (zExp != this.zExp) {
            this.zExp = zExp;
            this.invalidateCanvas();
        }
    }
    
    synchronized void setRotation(final int zRotate, final int yRotate) {
        if (zRotate != this.zRotate || yRotate != this.yRotate) {
            this.zRotate = zRotate;
            this.yRotate = yRotate;
            this.lastScrollTime = System.currentTimeMillis();
            this.doingScrollDelay = true;
            this.zCosine = Math.cos(this.zRotate * 3.141592653589793 / 180.0);
            this.zSine = Math.sin(this.zRotate * 3.141592653589793 / 180.0);
            this.yCosine = Math.cos(this.yRotate * 3.141592653589793 / 180.0);
            this.ySine = Math.sin(this.yRotate * 3.141592653589793 / 180.0);
            this.invalidateCanvas();
        }
    }
    
    synchronized void continueDrawing() {
        if (!this.drawing) {
            return;
        }
    }
    
    public synchronized void invalidateCanvas() {
        if (this.zExp != null) {
            this.setUpDrawing();
            this.drawing = true;
            this.axesOn = (this.style == 0);
        }
        else {
            this.drawing = false;
            this.axesOn = true;
        }
        this.notify();
        super.invalidateCanvas();
    }
    
    public synchronized void setErrorMessage(final String errorMessage) {
        this.drawing = false;
        this.zExp = null;
        super.setErrorMessage(errorMessage);
    }
    
    synchronized void setYLimits(final double ymin, final double ymax) {
        this.ymin = ymin;
        this.ymax = ymax;
        this.invalidateCanvas();
    }
    
    synchronized void setLimits(final double[] array) {
        if (this.xmin == array[0] && this.xmax == array[1] && this.ymin == array[2] && this.ymin == array[3] && this.zmin == array[4] && this.zmax == array[5]) {
            return;
        }
        this.xmin = array[0];
        this.xmax = array[1];
        this.ymin = array[2];
        this.ymax = array[3];
        this.zmin = array[4];
        this.zmax = array[5];
        this.xCenter = (this.xmin + this.xmax) / 2.0;
        this.zCenter = (this.zmin + this.zmax) / 2.0;
        this.yCenter = (this.ymax + this.ymin) / 2.0;
        this.xFactor = 2.0 / (this.xmax - this.xmin);
        this.yFactor = 2.0 / (this.ymax - this.ymin);
        this.zFactor = 2.0 / (this.zmax - this.zmin);
        final double n = 1.5 * (this.ymax - this.ymin) / 2.0;
        final double n2 = this.yCenter - n;
        final double n3 = this.yCenter + n;
        final double n4 = 1.5 * (this.zmax - this.zmin) / 2.0;
        super.coords.setLimits(n2, n3, this.zCenter - n4, this.zCenter + n4);
        if (array.length == 7) {
            this.gridSize = (int)Math.round(array[6]) + 1;
        }
        this.invalidateCanvas();
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    if (this.drawing) {
                        this.wait(50L);
                    }
                    else {
                        this.wait();
                    }
                }
                catch (InterruptedException ex) {}
            }
            if (!this.die) {
                synchronized (this) {
                    if (this.doingScrollDelay) {
                        if (System.currentTimeMillis() - this.lastScrollTime <= 500L) {
                            continue;
                        }
                        this.doingScrollDelay = false;
                        this.invalidateCanvas();
                    }
                    else {
                        this.doDrawStep();
                    }
                    continue;
                }
                break;
            }
            break;
        }
        this.die = false;
        synchronized (this) {
            this.notify();
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.getOSG() == null && this.zExp != null) {
            this.drawing = true;
            this.currentX = this.xStart;
            this.currentY = this.yStart;
            this.notify();
        }
    }
    
    protected void drawExtraStuff(final Graphics graphics) {
        if (this.axesOn || this.doingScrollDelay) {
            this.putAxes(graphics);
        }
    }
    
    private void setUpDrawing() {
        if (this.zRotate < -90) {
            this.xStart = this.gridSize - 1;
            this.yStart = 0;
        }
        else if (this.zRotate < 0) {
            this.xStart = 0;
            this.yStart = 0;
        }
        else if (this.zRotate < 90) {
            this.xStart = 0;
            this.yStart = this.gridSize - 1;
        }
        else {
            this.xStart = this.gridSize - 1;
            this.yStart = this.gridSize - 1;
        }
        if (this.xStart == 0) {
            this.xDir = 1;
            this.xStop = this.gridSize - 1;
        }
        else {
            this.xDir = -1;
            this.xStop = 0;
        }
        if (this.yStart == 0) {
            this.yDir = 1;
            this.yStop = this.gridSize - 1;
        }
        else {
            this.yDir = -1;
            this.yStop = 0;
        }
        this.xChangesQuicker = ((this.zRotate + 180) / 45 % 2 == 1);
        this.currentX = this.xStart;
        this.currentY = this.yStart;
        this.deltaX = (this.xmax - this.xmin) / (this.gridSize - 1);
        this.deltaY = (this.ymax - this.ymin) / (this.gridSize - 1);
        if (this.runner == null || !this.runner.isAlive()) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    private void putAxes(final Graphics graphics) {
        if (this.zExp == null || this.doingScrollDelay) {
            final Point transform = this.transform(this.xmin, this.ymin, 0.0);
            final Point transform2 = this.transform(this.xmin, this.ymax, 0.0);
            final Point transform3 = this.transform(this.xmax, this.ymax, 0.0);
            final Point transform4 = this.transform(this.xmax, this.ymin, 0.0);
            graphics.setColor(Graph3DCanvas.lightBlue);
            graphics.drawLine(transform.x, transform.y, transform2.x, transform2.y);
            graphics.drawLine(transform2.x, transform2.y, transform3.x, transform3.y);
            graphics.drawLine(transform3.x, transform3.y, transform4.x, transform4.y);
            graphics.drawLine(transform4.x, transform4.y, transform.x, transform.y);
        }
        graphics.setColor(Color.blue);
        double xmin;
        if (this.xmin <= 0.0 && this.xmax >= 0.0) {
            xmin = 0.0;
        }
        else {
            xmin = this.xmin;
        }
        double ymin;
        if (this.ymin <= 0.0 && this.ymax >= 0.0) {
            ymin = 0.0;
        }
        else {
            ymin = this.ymin;
        }
        double zmin;
        if (this.zmin <= 0.0 && this.zmax >= 0.0) {
            zmin = 0.0;
        }
        else {
            zmin = this.zmin;
        }
        final Point transform5 = this.transform(this.xmin, ymin, zmin);
        final Point transform6 = this.transform(this.xmax, ymin, zmin);
        putDoubleLine(graphics, transform5.x, transform5.y, transform6.x, transform6.y);
        if (transform5.x == transform6.x && transform5.y == transform6.y) {
            graphics.drawString("x", transform6.x + 3, transform6.y + 5);
        }
        else {
            final double length = length(transform5, transform6);
            graphics.drawString("x", transform6.x + (int)((transform6.x - transform5.x) / length * 10.0), transform6.y + (int)((transform6.y - transform5.y) / length * 10.0));
        }
        final Point transform7 = this.transform(xmin, this.ymin, zmin);
        final Point transform8 = this.transform(xmin, this.ymax, zmin);
        putDoubleLine(graphics, transform7.x, transform7.y, transform8.x, transform8.y);
        if (transform7.x == transform8.x && transform7.y == transform8.y) {
            graphics.drawString("y", transform8.x + 3, transform8.y + 5);
        }
        else {
            final double length2 = length(transform7, transform8);
            graphics.drawString("y", transform8.x + (int)((transform8.x - transform7.x) / length2 * 10.0), transform8.y + (int)((transform8.y - transform7.y) / length2 * 10.0));
        }
        final Point transform9 = this.transform(xmin, ymin, this.zmin);
        final Point transform10 = this.transform(xmin, ymin, this.zmax);
        putDoubleLine(graphics, transform9.x, transform9.y, transform10.x, transform10.y);
        if (transform9.x == transform10.x && transform9.y == transform10.y) {
            graphics.drawString("z", transform10.x + 3, transform10.y + 5);
        }
        else {
            final double length3 = length(transform9, transform10);
            graphics.drawString("z", transform10.x + (int)((transform10.x - transform9.x) / length3 * 10.0), transform10.y + (int)((transform10.y - transform9.y) / length3 * 10.0));
        }
    }
    
    private Point transform(final double n, final double n2, final double n3) {
        final Point point = new Point(0, 0);
        this.points[0][0] = n;
        this.points[0][1] = n2;
        this.points[0][2] = n3;
        this.transform(this.points[0], point);
        return point;
    }
    
    private void transform(final double[] array, final Point point) {
        array[0] = (array[0] - this.xCenter) * this.xFactor;
        array[1] = (array[1] - this.yCenter) * this.yFactor;
        array[2] = (array[2] - this.zCenter) * this.zFactor;
        final double n = array[0] * this.zCosine - array[1] * this.zSine;
        array[1] = array[0] * this.zSine + array[1] * this.zCosine;
        array[0] = n;
        final double n2 = array[2] * this.yCosine - array[0] * this.ySine;
        array[0] = array[2] * this.ySine + array[0] * this.yCosine;
        array[2] = n2;
        point.x = super.coords.xToPixel(array[1] / this.yFactor + this.yCenter);
        point.y = super.coords.yToPixel(array[2] / this.zFactor + this.zCenter);
    }
    
    private static void putDoubleLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2, n3, n4);
        if (Math.abs(n2 - n4) > Math.abs(n - n3)) {
            graphics.drawLine(n + 1, n2, n3 + 1, n4);
        }
        else {
            graphics.drawLine(n, n2 + 1, n3, n4 + 1);
        }
    }
    
    private static double length(final double n, final double n2) {
        return Math.sqrt(n * n + n2 * n2);
    }
    
    private static double length(final Point point, final Point point2) {
        return length(point.x - point2.x, point.y - point2.y);
    }
    
    private void makePoly(final int n, final int n2) {
        this.points[0][0] = this.xmin + this.deltaX * n;
        this.points[0][1] = this.ymin + this.deltaY * n2;
        this.points[1][0] = this.xmin + this.deltaX * (n + this.xDir);
        this.points[1][1] = this.ymin + this.deltaY * n2;
        this.points[2][0] = this.xmin + this.deltaX * (n + this.xDir);
        this.points[2][1] = this.ymin + this.deltaY * (n2 + this.yDir);
        this.points[3][0] = this.xmin + this.deltaX * n;
        this.points[3][1] = this.ymin + this.deltaY * (n2 + this.yDir);
        this.xVar.setValue(this.points[3][0]);
        this.yVar.setValue(this.points[3][1]);
        this.points[3][2] = this.zExp.value();
        this.xVar.setValue(this.points[0][0]);
        this.yVar.setValue(this.points[0][1]);
        this.points[0][2] = this.zExp.value();
        if (Double.isNaN(this.points[0][2]) || Double.isInfinite(this.points[0][2]) || !this.zExp.checkCases()) {
            return;
        }
        this.xVar.setValue(this.points[1][0]);
        this.yVar.setValue(this.points[1][1]);
        this.points[1][2] = this.zExp.value();
        if (Double.isNaN(this.points[1][2]) || Double.isInfinite(this.points[1][2]) || !this.zExp.checkCases()) {
            return;
        }
        this.xVar.setValue(this.points[2][0]);
        this.yVar.setValue(this.points[2][1]);
        this.points[2][2] = this.zExp.value();
        if (Double.isNaN(this.points[2][2]) || Double.isInfinite(this.points[2][2]) || !this.zExp.checkCases()) {
            return;
        }
        this.xVar.setValue(this.points[3][0]);
        this.yVar.setValue(this.points[3][1]);
        this.points[3][2] = this.zExp.value();
        if (Double.isNaN(this.points[3][2]) || Double.isInfinite(this.points[3][2]) || !this.zExp.checkCases()) {
            return;
        }
        this.transform(this.points[0], this.tempPt);
        this.xPoly[0] = this.tempPt.x;
        this.yPoly[0] = this.tempPt.y;
        this.transform(this.points[1], this.tempPt);
        this.xPoly[1] = this.tempPt.x;
        this.yPoly[1] = this.tempPt.y;
        this.transform(this.points[2], this.tempPt);
        this.xPoly[2] = this.tempPt.x;
        this.yPoly[2] = this.tempPt.y;
        this.transform(this.points[3], this.tempPt);
        this.xPoly[3] = this.tempPt.x;
        this.yPoly[3] = this.tempPt.y;
        if (this.style == 0) {
            this.fillColor = null;
        }
        else if (this.style == 1) {
            this.fillColor = Color.white;
        }
        else {
            final double n3 = this.points[1][0] - this.points[0][0];
            final double n4 = this.points[1][1] - this.points[0][1];
            final double n5 = this.points[1][2] - this.points[0][2];
            final double n6 = this.points[3][0] - this.points[0][0];
            final double n7 = this.points[3][1] - this.points[0][1];
            double n8 = n4 * (this.points[3][2] - this.points[0][2]) - n7 * n5;
            final double n9 = n5 * n6 - n6 * n5;
            final double n10 = n3 * n7 - n6 * n4;
            final double sqrt = Math.sqrt(n8 * n8 + n9 * n9 + n10 * n10);
            int n11 = 0;
            if (sqrt < 1.0E-100) {
                this.fillColor = null;
            }
            else {
                final double n12 = (2.0 * n8 + n9 + n10) / (sqrt * Math.sqrt(6.0));
                if (this.xDir == 1 != (this.yDir == 1)) {
                    n8 = -n8;
                }
                if (n8 < 0.0) {
                    n11 = (int)Math.abs(140.0 * n12);
                }
                else {
                    n11 = 30 + (int)Math.abs(200.0 * n12);
                }
            }
            this.fillColor = new Color(n11, n11, n11);
        }
    }
    
    private void drawPoly(final Graphics graphics) {
        if (this.fillColor != null) {
            graphics.setColor(this.fillColor);
            graphics.fillPolygon(this.xPoly, this.yPoly, 4);
        }
        if (this.style != 2) {
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.xPoly, this.yPoly, 4);
        }
    }
    
    private void doDrawStep() {
        if (!this.drawing || this.zExp == null) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        final Graphics osg = this.getOSG();
        if (this.xChangesQuicker) {
            for (int i = this.xStart; i != this.xStop; i += this.xDir) {
                this.makePoly(i, this.currentY);
                this.drawPoly(graphics);
                if (osg != null) {
                    this.drawPoly(osg);
                }
            }
            this.currentY += this.yDir;
            if (this.currentY == this.yStop) {
                this.drawing = false;
            }
        }
        else {
            for (int j = this.yStart; j != this.yStop; j += this.yDir) {
                this.makePoly(this.currentX, j);
                this.drawPoly(graphics);
                if (osg != null) {
                    this.drawPoly(osg);
                }
            }
            this.currentX += this.xDir;
            if (this.currentX == this.xStop) {
                this.drawing = false;
            }
        }
        graphics.dispose();
    }
}
