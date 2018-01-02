import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class TraversalList extends DrawableList
{
    private static final Color BOX_COLOR;
    private String[] list;
    
    static {
        BOX_COLOR = Screen.DARK_RED;
    }
    
    public TraversalList(final Position position, final int size) {
        super(position, size);
        this.list = new String[size];
        super.color = TraversalList.BOX_COLOR;
    }
    
    public void append(final String value, final DrawableString number) {
        final Position[] positions = { null };
        number.setString(value);
        number.show();
        positions[0] = new Position(super.valueX, super.y + 15);
        number.moveTo(positions);
        Screen.canvas.awaitMovingCompletion();
        number.hide();
        this.list[super.count++] = value;
        super.valueX += 17;
    }
    
    public void draw(final Graphics g) {
        super.draw(g);
        int numberX = super.x + 2;
        g.setFont(Screen.screen.getFont());
        g.setColor(Color.white);
        for (int i = 0; i < super.count; ++i) {
            g.drawString(this.list[i], numberX, super.y + 15);
            numberX += 17;
        }
    }
    
    public void init() {
        super.count = 0;
        super.valueX = super.x + 2;
    }
}
