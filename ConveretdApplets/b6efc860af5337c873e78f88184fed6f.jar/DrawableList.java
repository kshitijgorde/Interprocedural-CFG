import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableList extends Drawable
{
    private static final int HEIGHT = 22;
    protected static final int DELTA_X = 17;
    protected static final int OFFSET_Y = 15;
    protected static final int INSET_X = 2;
    protected int count;
    protected int valueX;
    private int width;
    
    public DrawableList(final Position position, final int size) {
        this.count = 0;
        this.reposition(position);
        this.valueX = super.x + 2;
        this.width = size * 17 + 4;
    }
    
    public void draw(final Graphics g) {
        g.setColor(Screen.DARK_RED);
        g.fillRect(super.x, super.y, this.width, 22);
    }
}
