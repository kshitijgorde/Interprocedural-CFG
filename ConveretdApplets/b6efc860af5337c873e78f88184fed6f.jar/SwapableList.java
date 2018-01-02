import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class SwapableList extends DrawableList
{
    private static final int VERTICAL_OFFSET = 18;
    private static final int STRING_MOVES = 3;
    protected int[] list;
    private boolean[] sorted;
    private Color sortedColor;
    private DrawableString value1;
    private DrawableString value2;
    
    public SwapableList(final Position position, final int size, final Color sortedColor) {
        super(position, size);
        this.value1 = new DrawableString(new Position(0, 0));
        this.value2 = new DrawableString(new Position(0, 0));
        this.list = new int[size + 1];
        this.sorted = new boolean[size + 1];
        for (int index = 1; index <= size; ++index) {
            this.sorted[index] = false;
        }
        this.sortedColor = sortedColor;
    }
    
    private Position ListStringPosition(final int from, final int to, final int move) {
        switch (move) {
            case 0: {
                return new Position(super.x + 2 + (from - 1) * 17, super.y + 15);
            }
            case 1: {
                return new Position(super.x + 2 + (from - 1) * 17, super.y + 15 - 18);
            }
            case 2: {
                return new Position(super.x + 2 + (to - 1) * 17, super.y + 15 - 18);
            }
            case 3: {
                return new Position(super.x + 2 + (to - 1) * 17, super.y + 15);
            }
            default: {
                return null;
            }
        }
    }
    
    public void draw(final Graphics g) {
        super.draw(g);
        this.drawObject(g);
    }
    
    public boolean drawObject(final Graphics g) {
        boolean moving = false;
        super.draw(g);
        int numberX = super.x + 2;
        for (int i = 1; i <= super.count; ++i) {
            if (this.sorted[i]) {
                g.setColor(this.sortedColor);
            }
            else {
                g.setColor(Color.white);
            }
            if (this.list[i] >= 0) {
                g.drawString(String.valueOf(this.list[i]), numberX, super.y + 15);
            }
            numberX += 17;
        }
        moving |= this.value1.drawObject(g);
        moving |= this.value2.drawObject(g);
        return moving;
    }
    
    public void finishRotate(final int left, final int right) {
        this.list[left] = -this.list[left];
        this.value1.hide();
    }
    
    public void finishSwap(final int left, final int right) {
        final int leftValue = -this.list[left];
        final int rightValue = -this.list[right];
        this.list[left] = rightValue;
        this.list[right] = leftValue;
        this.value1.hide();
        this.value2.hide();
    }
    
    public void markSorted(final int index) {
        this.sorted[index] = true;
    }
    
    private void move(final int from, final int to, final DrawableString value) {
        final Position[] positions = new Position[3];
        value.show();
        value.setString(String.valueOf(this.list[from]));
        value.reposition(this.ListStringPosition(from, to, 0));
        for (int move = 0; move < 3; ++move) {
            positions[move] = this.ListStringPosition(from, to, move + 1);
        }
        value.moveTo(positions);
    }
    
    public void nullify(final int index) {
        this.list[index] = -1;
    }
    
    public void setValues(final int[] values, final int size) {
        for (int index = 1; index <= size; ++index) {
            this.list[index] = values[index];
            this.sorted[index] = false;
        }
        super.count = size;
    }
    
    public void startRotate(final int left, final int right) {
        this.move(right, left, this.value1);
        final int temp = this.list[right];
        for (int index = right; index > left; --index) {
            this.list[index] = this.list[index - 1];
        }
        this.list[left] = -temp;
    }
    
    public void startSwap(final int left, final int right) {
        this.move(left, right, this.value1);
        this.move(right, left, this.value2);
        this.list[left] = -this.list[left];
        this.list[right] = -this.list[right];
    }
}
