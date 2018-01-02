import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SwapableArray extends Drawable
{
    private static final int DELTA_X = 17;
    private static final int WIDTH = 10;
    private static final int X_CENTER = 3;
    private static final int Y_LINE = 222;
    private static final int Y_ARROW = 230;
    private static final int MAX_LINES = 29;
    private static final int PAUSE = 1000;
    private static final Color BAR_COLOR;
    private static final Color SORTED_COLOR;
    private int[] array;
    private int size;
    private int yIncrement;
    private boolean[] sorted;
    private int leftValue;
    private int rightValue;
    private int lineCount;
    private DrawableArrow leftArrow;
    private DrawableArrow rightArrow;
    private QuicksortBar bar;
    private HorizontalLine[] lines;
    private DrawableBar bar1;
    private DrawableBar bar2;
    
    static {
        BAR_COLOR = Screen.DARK_BLUE;
        SORTED_COLOR = Screen.DARK_RED;
    }
    
    public SwapableArray(final Position position, final int size) {
        this.yIncrement = 2;
        this.lineCount = 0;
        this.leftArrow = new DrawableArrow();
        this.rightArrow = new DrawableArrow();
        this.bar = new QuicksortBar(new Position(0, 0));
        this.lines = new HorizontalLine[29];
        this.bar1 = new DrawableBar(new Position(0, 0));
        this.bar2 = new DrawableBar(new Position(0, 0));
        super.x = position.x();
        super.y = position.y();
        this.size = size;
    }
    
    private void activateBar(final int from, final DrawableBar bar) {
        bar.reposition(new Position(this.xPos(from), this.yPos(from)));
        bar.setHeight(this.height(from));
        if (Screen.panel.isInteractive()) {
            Screen.canvas.acceptClick(bar);
        }
        else {
            Screen.controls.nextStep(false, true);
            bar.show();
        }
    }
    
    public void doCopy(final int from, final int to, final SwapableArray copy) {
        final int temp = this.array[from];
        this.activateBar(from, this.bar1);
        if (Screen.panel.isInteractive()) {
            Screen.canvas.awaitClick("Click Bar To Move", true, false);
        }
        else {
            Screen.controls.nextStep(true, true);
        }
        copy.array[to] = temp;
        final Position toPosition = new Position(copy.xPos(to), copy.yPos(to));
        this.array[from] = (copy.array[to] = 0);
        this.moveBar(from, toPosition, this.bar1);
        this.bar1.show();
        Screen.canvas.awaitMovingCompletion();
        copy.array[to] = temp;
        this.bar1.hide();
    }
    
    public void draw(final Graphics g) {
    }
    
    public boolean drawObject(final Graphics g) {
        if (this.sorted == null) {
            return false;
        }
        boolean moving = false;
        for (int index = 1; index <= this.size; ++index) {
            g.setColor(SwapableArray.BAR_COLOR);
            if (this.sorted[index]) {
                g.setColor(SwapableArray.SORTED_COLOR);
            }
            g.fillRect(this.xPos(index), this.yPos(index), 10, this.height(index));
        }
        moving |= this.bar.drawObject(g);
        moving |= this.bar1.drawObject(g);
        moving |= this.bar2.drawObject(g);
        g.setColor(SwapableArray.SORTED_COLOR);
        if (!Screen.panel.isInteractive()) {
            moving |= this.leftArrow.drawObject(g);
            moving |= this.rightArrow.drawObject(g);
        }
        for (int line = 0; line < this.lineCount; ++line) {
            this.lines[line].drawObject(g);
        }
        moving |= super.drawObject(g);
        return moving;
    }
    
    public void finishRotate(final int left, final int right) {
        this.array[left] = this.leftValue;
        this.bar1.hide();
        this.bar2.hide();
    }
    
    public void finishSwap(final int left, final int right) {
        this.array[left] = this.rightValue;
        this.array[right] = this.leftValue;
        this.bar1.hide();
        this.bar2.hide();
    }
    
    private int height(final int index) {
        return this.array[index] * this.yIncrement;
    }
    
    public void hideArrows() {
        this.leftArrow.hide();
        this.rightArrow.hide();
        this.bar.hide();
    }
    
    public void init() {
        this.hideArrows();
        this.lineCount = 0;
    }
    
    public void makeLine(final int first, final int last, final int level) {
        this.lines[this.lineCount] = new HorizontalLine(this.xPos(first), this.xPos(last) + 10, 222 + level * 2);
        this.lines[this.lineCount++].show();
    }
    
    public void markSorted(final int index) {
        this.sorted[index] = true;
    }
    
    public void moveArrows(final int left, final int right) {
        final Position[] positions = { new Position(this.xPos(left) + 10, 230) };
        this.leftArrow.moveTo(positions);
        positions[0] = new Position(this.xPos(right), 230);
        this.rightArrow.moveTo(positions);
        Screen.canvas.awaitMovingCompletion();
    }
    
    private void moveBar(final int from, final Position to, final DrawableBar bar) {
        final Position[] positions = { to };
        bar.moveTo(positions);
    }
    
    public void positionArrows(final int left, final int right) {
        this.leftArrow.reposition(new Position(this.xPos(left) + 10, 230));
        this.leftArrow.setDirection(new Position(this.xPos(left + 1), 230), true);
        this.rightArrow.reposition(new Position(this.xPos(right), 230));
        this.rightArrow.setDirection(new Position(this.xPos(right - 1), 230), true);
    }
    
    public void setBar(final int first, final int last) {
        this.bar.reposition(new Position(this.xPos(first), this.yPos(first)));
        this.bar.setDimensions(this.height(first), this.xPos(first), this.xPos(last));
    }
    
    public void setValues(final int[] array, final int size) {
        this.array = array;
        this.size = size;
        this.sorted = new boolean[size + 1];
        for (int index = 1; index <= size; ++index) {
            this.sorted[index] = false;
        }
        this.lineCount = 0;
    }
    
    public void showArrows() {
        this.leftArrow.show();
        this.rightArrow.show();
        this.bar.show();
    }
    
    public void startRotate(final int left, final int right) {
        this.activateBar(right, this.bar1);
        if (Screen.panel.isInteractive()) {
            Screen.canvas.awaitClick("Click The Bar To Insert", true, false);
            this.activateBar(left, this.bar2);
            Screen.canvas.awaitClick("Click Location To Insert", true, false);
        }
        else {
            Screen.controls.nextStep(true, true);
        }
        final Position leftPosition = new Position(this.xPos(left), this.yPos(right));
        this.bar2.hide();
        this.moveBar(right, leftPosition, this.bar1);
        this.leftValue = this.array[right];
        for (int index = right; index > left; --index) {
            this.array[index] = this.array[index - 1];
        }
        this.array[left] = 0;
        this.bar1.show();
    }
    
    public void startSwap(final int left, final int right) {
        this.activateBar(left, this.bar1);
        this.activateBar(right, this.bar2);
        if (Screen.panel.isInteractive()) {
            Screen.canvas.awaitClick("Click Bars To Swap", true, false);
        }
        else {
            Screen.controls.nextStep(true, true);
        }
        final Position rightPosition = new Position(this.xPos(right), this.yPos(left));
        this.moveBar(left, rightPosition, this.bar1);
        this.leftValue = this.array[left];
        final Position leftPosition = new Position(this.xPos(left), this.yPos(right));
        this.moveBar(right, leftPosition, this.bar2);
        this.rightValue = this.array[right];
        this.array[left] = 0;
        this.array[right] = 0;
        this.bar1.show();
        this.bar2.show();
    }
    
    public void toggleHeight() {
        if (this.yIncrement == 2) {
            this.yIncrement = 1;
        }
        else {
            this.yIncrement = 2;
        }
    }
    
    private int xPos(final int index) {
        return super.x + 3 + (index - 1) * 17;
    }
    
    private int yPos(final int index) {
        return super.y - this.height(index);
    }
}
