import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class HiddenNode extends Drawable
{
    protected static final int RADIUS = 15;
    protected int level;
    
    public HiddenNode() {
    }
    
    public HiddenNode(final Position position, final int level) {
        this.reposition(position);
        this.level = level;
    }
    
    public void draw(final Graphics g) {
        g.setColor(Screen.DARK_RED);
        g.fillOval(super.x - 15, super.y - 15, 30, 30);
    }
    
    public boolean inside(final Position position) {
        int width = 15;
        int height = 15;
        if (this.isHidden()) {
            for (int i = 5; i > this.level; --i) {
                width *= 2;
            }
            height *= 2;
        }
        return position.x() > super.x - width && position.x() < super.x + width && position.y() > super.y - height && position.y() < super.y + height;
    }
    
    public boolean isHidden() {
        return true;
    }
}
