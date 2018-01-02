import java.util.Enumeration;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Life extends Applet implements TGMouseHandler, Runnable
{
    private static final int CANVAS_HEIGHT = 500;
    private static final int CANVAS_WIDTH = 500;
    private static final int BUTTON_FONT_SIZE = 15;
    private static final int BUTTON_WIDTH = 85;
    private static final int BUTTON_HEIGHT = 24;
    private static final int BUTTON_TEXT_X_OFFSET = 2;
    private static final int BUTTON_TEXT_Y_OFFSET = 8;
    private static final int CLEAR_LEFT_X = -200;
    private static final int CLEAR_BOTTOM_Y = -200;
    private static final int GO_LEFT_X = -95;
    private static final int GO_BOTTOM_Y = -200;
    private static final int MORLES_LEFT_X = 115;
    private static final int MORLES_BOTTOM_Y = -200;
    private static final int STEP_LEFT_X = 10;
    private static final int STEP_BOTTOM_Y = -200;
    private static final int STOP_LEFT_X = -42;
    private static final int STOP_BOTTOM_Y = -200;
    private static final int GRID_BOX_SIZE = 20;
    private static final int GRID_BOTTOM_Y = -160;
    private static final int GRID_LEFT_X = -200;
    private static final int GRID_HEIGHT = 360;
    private static final int GRID_WIDTH = 400;
    private boolean more;
    private boolean running;
    private int cellCols;
    private int cellRows;
    private int cellSize;
    private int[][] cells;
    private Grid grid;
    private TGCanvas canvas;
    private Thread lifeRunningThread;
    private Turtle turtle;
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.canvas = new TGCanvas(500, 500)).setBackground(Color.white);
        this.add("Center", this.canvas);
        this.canvas.addMouseHandler(this);
        (this.turtle = new Turtle(this.canvas)).hideturtle();
    }
    
    public void start() {
        this.cellSize = 20;
        this.more = true;
        this.doClear();
        this.cellCols = this.grid.getGridWidthInBoxes();
        this.cellRows = this.grid.getGridHeightInBoxes();
        this.cells = new int[this.cellRows][this.cellCols];
        this.running = false;
    }
    
    public void stop() {
        this.doStop();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, 499, 499);
    }
    
    public void run() {
        while (this.lifeRunningThread == Thread.currentThread()) {
            this.nextWorld();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void mouseClick() {
        if (this.running) {
            if (this.isMouseInRect(-42, -200, 24, 85)) {
                this.doStop();
            }
        }
        else if (this.isMouseInRect(-200, -200, 24, 85)) {
            this.doClear();
        }
        else if (this.isMouseInRect(-95, -200, 24, 85)) {
            this.doGo();
        }
        else if (this.isMouseInRect(10, -200, 24, 85)) {
            this.doStep();
        }
        else if (this.isMouseInRect(115, -200, 24, 85)) {
            this.doMoreLess();
        }
    }
    
    private void addNewborn(final Vector vector) {
        final Enumeration<Integer> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.grid.colorBox(elements.nextElement(), 12);
        }
    }
    
    private void buttonAt(final int n, final int n2, final String s) {
        this.eraseButtonAt(n, n2);
        this.turtle.setpencolor(0);
        this.frameAt(n, n2, 24, 85, 2);
        this.turtle.penup();
        this.turtle.setxy(n + 2, n2 + 8);
        this.turtle.pendown();
        this.turtle.setlabelheight(15);
        this.turtle.label(s);
    }
    
    private Vector computeDead() {
        final Vector<Integer> vector = new Vector<Integer>(this.cellCols * this.cellRows / 4);
        final Enumeration enumColoredBoxes = this.grid.enumColoredBoxes();
        while (enumColoredBoxes.hasMoreElements()) {
            final int nextColoredBoxNum = this.grid.getNextColoredBoxNum(enumColoredBoxes);
            final int n = this.cells[nextColoredBoxNum / this.cellCols][nextColoredBoxNum % this.cellCols];
            if (n != 2) {
                if (n == 3) {
                    continue;
                }
                vector.addElement(new Integer(nextColoredBoxNum));
            }
        }
        return vector;
    }
    
    private void computeNeighbors() {
        final Enumeration enumColoredBoxes = this.grid.enumColoredBoxes();
        while (enumColoredBoxes.hasMoreElements()) {
            this.incrAllNeighbors(enumColoredBoxes.nextElement());
        }
    }
    
    private Vector computeNewborn() {
        final Vector<Integer> vector = new Vector<Integer>(this.cellCols * this.cellRows / 4);
        for (int i = 0; i < this.cellCols * this.cellRows; ++i) {
            if (this.cells[i / this.cellCols][i % this.cellCols] == 3 && !this.grid.isColoredBox(i)) {
                vector.addElement(new Integer(i));
            }
        }
        return vector;
    }
    
    private void doClear() {
        this.canvas.clean();
        if (this.grid != null) {
            try {
                this.grid.finalize();
            }
            catch (Throwable t) {}
        }
        this.grid = new Grid(this.canvas, this.turtle, -200, -160, 360, 400, this.cellSize);
        this.buttonAt(-200, -200, "Clear");
        this.buttonAt(-95, -200, "Go");
        this.buttonAt(10, -200, "Step");
        this.buttonAt(115, -200, this.more ? "MoreCells" : "LessCells");
    }
    
    private void doGo() {
        this.running = true;
        this.eraseButtonAt(-200, -200);
        this.eraseButtonAt(-95, -200);
        this.eraseButtonAt(10, -200);
        this.eraseButtonAt(115, -200);
        this.buttonAt(-42, -200, "Stop");
        this.grid.stopPainting();
        (this.lifeRunningThread = new Thread(this, "life")).setDaemon(true);
        this.lifeRunningThread.start();
    }
    
    private void doMoreLess() {
        if (this.more) {
            this.cellSize /= 2;
        }
        else {
            this.cellSize *= 2;
        }
        this.more = !this.more;
        this.doClear();
        this.cellCols = this.grid.getGridWidthInBoxes();
        this.cellRows = this.grid.getGridHeightInBoxes();
        this.cells = new int[this.cellRows][this.cellCols];
    }
    
    private void doStep() {
        this.nextWorld();
    }
    
    private void doStop() {
        this.lifeRunningThread = null;
        this.running = false;
        this.eraseButtonAt(-42, -200);
        this.buttonAt(-200, -200, "Clear");
        this.buttonAt(-95, -200, "Go");
        this.buttonAt(10, -200, "Step");
        this.buttonAt(115, -200, this.more ? "MoreCells" : "LessCells");
        this.grid.allowPainting();
    }
    
    private void eraseButtonAt(final int n, final int n2) {
        final int n3 = 4;
        this.turtle.penup();
        this.turtle.setxy(n - n3, n2 + 12);
        this.turtle.pendown();
        this.turtle.setpencolor(7);
        this.turtle.setpensize(24 + 2 * n3);
        this.turtle.setheading(90);
        this.turtle.forward(85 + 2 * n3);
    }
    
    private void frameAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setpensize(n5);
        this.turtle.setpencolor(0);
        this.turtle.setheading(0);
        for (int i = 2; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
            this.turtle.forward(n4);
            this.turtle.right(90);
        }
    }
    
    private void incrAllNeighbors(final int n) {
        this.incrENeighbor(n);
        this.incrNNeighbor(n);
        this.incrNENeighbor(n);
        this.incrNWNeighbor(n);
        this.incrSNeighbor(n);
        this.incrSENeighbor(n);
        this.incrSWNeighbor(n);
        this.incrWNeighbor(n);
    }
    
    private void incrCell(final int n) {
        if (n < 0 || n >= this.cellCols * this.cellRows) {
            System.err.print("Life.incrCell: bad cellNum[0.." + this.cellCols * this.cellRows + "]");
            System.err.println(" = " + n);
        }
        final int n2 = n % this.cellCols;
        final int[] array = this.cells[n / this.cellCols];
        final int n3 = n2;
        ++array[n3];
    }
    
    private void incrENeighbor(int n) {
        if (++n % this.cellCols > 0) {
            this.incrCell(n);
        }
    }
    
    private void incrNNeighbor(int n) {
        if ((n -= this.cellCols) >= 0) {
            this.incrCell(n);
        }
    }
    
    private void incrNENeighbor(int n) {
        if ((n -= this.cellCols) >= 0 && ++n % this.cellCols > 0) {
            this.incrCell(n);
        }
    }
    
    private void incrNWNeighbor(int n) {
        if ((n -= this.cellCols) >= 0 && --n >= 0 && n % this.cellCols < this.cellCols - 1) {
            this.incrCell(n);
        }
    }
    
    private void incrSNeighbor(int n) {
        if ((n += this.cellCols) < this.cellCols * this.cellRows) {
            this.incrCell(n);
        }
    }
    
    private void incrSENeighbor(int n) {
        if ((n += this.cellCols) < this.cellCols * this.cellRows && ++n < this.cellCols * this.cellRows && n % this.cellCols > 0) {
            this.incrCell(n);
        }
    }
    
    private void incrSWNeighbor(int n) {
        if ((n += this.cellCols) < this.cellCols * this.cellRows && --n % this.cellCols < this.cellCols - 1) {
            this.incrCell(n);
        }
    }
    
    private void incrWNeighbor(int n) {
        if (--n >= 0 && n % this.cellCols < this.cellCols - 1) {
            this.incrCell(n);
        }
    }
    
    private boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    private void nextWorld() {
        this.zapCells();
        this.computeNeighbors();
        final Vector computeDead = this.computeDead();
        final Vector computeNewborn = this.computeNewborn();
        this.removeDead(computeDead);
        this.addNewborn(computeNewborn);
    }
    
    private void removeDead(final Vector vector) {
        final Enumeration<Integer> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final int intValue = elements.nextElement();
            if (!this.grid.clearBox(intValue)) {
                System.err.println("Life.removeDead: " + intValue);
            }
        }
    }
    
    private void zapCells() {
        for (int i = 0; i < this.cellRows; ++i) {
            for (int j = 0; j < this.cellCols; ++j) {
                this.cells[i][j] = 0;
            }
        }
    }
}
