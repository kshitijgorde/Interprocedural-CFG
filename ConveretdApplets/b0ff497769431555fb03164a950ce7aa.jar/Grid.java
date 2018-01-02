import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grid implements TGMouseHandler
{
    public static final int GRID_BOX_SIZE = 20;
    private boolean allowPainting;
    private int gridBoxSize;
    private int gridBottomY;
    private int gridLeftX;
    private int gridTopY;
    private int gridHeight;
    private int gridHeightBoxes;
    private int gridWidth;
    private int gridWidthBoxes;
    private TGCanvas canvas;
    private Turtle turtle;
    private Vector liveList;
    
    public Grid(final TGCanvas tgCanvas, final Turtle turtle, final int n, final int n2, final int n3, final int n4) {
        this(tgCanvas, turtle, n, n2, n3, n4, 20);
    }
    
    public Grid(final TGCanvas canvas, final Turtle turtle, final int gridLeftX, final int gridBottomY, final int n, final int n2, final int gridBoxSize) {
        this.canvas = canvas;
        this.turtle = turtle;
        this.gridBoxSize = gridBoxSize;
        this.gridLeftX = gridLeftX;
        this.gridBottomY = gridBottomY;
        this.gridTopY = gridBottomY + n;
        this.gridHeightBoxes = n / gridBoxSize;
        this.gridHeight = this.gridHeightBoxes * gridBoxSize;
        this.gridWidthBoxes = n2 / gridBoxSize;
        this.gridWidth = this.gridWidthBoxes * gridBoxSize;
        this.drawGrid();
        this.liveList = new Vector(this.gridHeightBoxes * this.gridWidthBoxes / 4);
        this.canvas.addMouseHandler(this);
        this.allowPainting = true;
    }
    
    public void mouseClick() {
        if (!this.allowPainting) {
            return;
        }
        if (this.isMouseInRect(this.gridLeftX, this.gridBottomY, this.gridHeight, this.gridWidth)) {
            final int mousex = this.canvas.mousex();
            final int mousey = this.canvas.mousey();
            final int xyToBoxNum = this.xyToBoxNum(mousex, mousey);
            if (this.addToLiveList(new Integer(xyToBoxNum))) {
                this.fillBoxAt(this.mouseXToBoxX(mousex), this.mouseYToBoxY(mousey), 12);
            }
            else {
                this.clearBox(xyToBoxNum);
            }
        }
    }
    
    private boolean addToLiveList(final Integer n) {
        final int intValue = n;
        final Enumeration<Integer> elements = (Enumeration<Integer>)this.liveList.elements();
        while (elements.hasMoreElements()) {
            if (intValue == elements.nextElement()) {
                return false;
            }
        }
        this.liveList.addElement(n);
        return true;
    }
    
    private void drawGrid() {
        this.turtle.setpensize(1);
        this.turtle.setpencolor(0);
        this.turtle.setheading(0);
        for (int i = this.gridLeftX; i <= this.gridLeftX + this.gridWidth; i += this.gridBoxSize) {
            this.turtle.penup();
            this.turtle.setxy(i, this.gridBottomY);
            this.turtle.pendown();
            this.turtle.forward(this.gridHeight);
        }
        this.turtle.setheading(90);
        for (int j = this.gridBottomY; j <= this.gridBottomY + this.gridHeight; j += this.gridBoxSize) {
            this.turtle.penup();
            this.turtle.setxy(this.gridLeftX, j);
            this.turtle.pendown();
            this.turtle.forward(this.gridWidth);
        }
    }
    
    private void fillBoxAt(final int n, final int n2, final int n3) {
        this.turtle.penup();
        this.turtle.setxy(n + this.gridBoxSize / 2, n2);
        this.turtle.pendown();
        this.turtle.setpencolor(n3);
        this.turtle.setpensize(this.gridBoxSize);
        this.turtle.setheading(0);
        this.turtle.forward(this.gridBoxSize);
        this.frameAt(n, n2, this.gridBoxSize, this.gridBoxSize);
    }
    
    private void frameAt(final int n, final int n2, final int n3, final int n4) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setheading(0);
        this.turtle.setpencolor(0);
        this.turtle.setpensize(1);
        for (int i = 2; i > 0; --i) {
            this.turtle.forward(n3);
            this.turtle.right(90);
            this.turtle.forward(n4);
            this.turtle.right(90);
        }
    }
    
    private boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    private int mouseXToBoxX(final int n) {
        return this.gridLeftX + Math.abs(this.gridLeftX - n) / this.gridBoxSize * this.gridBoxSize;
    }
    
    private int mouseYToBoxY(final int n) {
        return this.gridTopY - this.gridBoxSize - Math.abs(this.gridTopY - n) / this.gridBoxSize * this.gridBoxSize;
    }
    
    private int xyToBoxNum(final int n, final int n2) {
        return this.gridWidthBoxes * (Math.abs(this.gridTopY - n2) / this.gridBoxSize) + Math.abs(this.gridLeftX - n) / this.gridBoxSize;
    }
    
    public void allowPainting() {
        this.allowPainting = true;
    }
    
    public boolean clearBox(final int n) {
        for (int size = this.liveList.size(), i = 0; i < size; ++i) {
            if ((int)this.liveList.elementAt(i) == n) {
                this.fillBoxAt(this.gridLeftX + n % this.gridWidthBoxes * this.gridBoxSize, this.gridTopY - this.gridBoxSize - n / this.gridWidthBoxes * this.gridBoxSize, 7);
                this.liveList.removeElementAt(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean colorBox(final int n, final int n2) {
        if (this.addToLiveList(new Integer(n))) {
            this.fillBoxAt(this.gridLeftX + n % this.gridWidthBoxes * this.gridBoxSize, this.gridTopY - this.gridBoxSize - n / this.gridWidthBoxes * this.gridBoxSize, n2);
            return true;
        }
        return false;
    }
    
    public Enumeration enumColoredBoxes() {
        return this.liveList.elements();
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.canvas.removeMouseHandler(this);
    }
    
    public int getGridBoxSize() {
        return this.gridBoxSize;
    }
    
    public int getGridHeightInBoxes() {
        return this.gridHeightBoxes;
    }
    
    public int getGridWidthInBoxes() {
        return this.gridWidthBoxes;
    }
    
    public int getNextColoredBoxNum(final Enumeration enumeration) {
        return enumeration.nextElement();
    }
    
    public boolean isColoredBox(final int n) {
        final Enumeration<Integer> elements = this.liveList.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement() == n) {
                return true;
            }
        }
        return false;
    }
    
    public void stopPainting() {
        this.allowPainting = false;
    }
}
