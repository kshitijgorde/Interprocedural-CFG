import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Shape extends Sprite
{
    public static final Color orange;
    public static final int TYPE = 13;
    public static final int COLOR = 14;
    public static final int FILL = 15;
    public static final int STARTANGLE = 16;
    public static final int ARCANGLE = 17;
    public static final Integer LINE;
    public static final Integer RECTANGLE;
    public static final Integer OVAL;
    public static final Integer ARC;
    public Color color;
    public int type;
    public int startAngle;
    public int arcAngle;
    public boolean fill;
    Rectangle rectangle;
    
    public Shape() {
        this.set(new int[] { 13, 14, 15, 16, 17 }, new Object[] { Shape.LINE, Color.black, Sprite.TRUE, Sprite.ZERO, Sprite.ZERO });
        this.rectangle = new Rectangle();
    }
    
    public void set(final int n, final Object o) {
        switch (n) {
            case 14: {
                this.color = (Color)o;
            }
            case 13: {
                this.type = (int)o;
            }
            case 15: {
                this.fill = (boolean)o;
            }
            case 16: {
                this.startAngle = (int)o;
            }
            case 17: {
                this.arcAngle = (int)o;
            }
            default: {
                super.set(n, o);
            }
        }
    }
    
    public final void draw(final Graphics graphics) {
        if (super.visible) {
            this.rectangle.setBounds(super.x, super.y, super.width, super.height);
            graphics.setColor(this.color);
            if (this.type == Shape.LINE) {
                graphics.drawLine(this.rectangle.x, this.rectangle.y, this.rectangle.x + this.rectangle.width, this.rectangle.y + this.rectangle.height);
                return;
            }
            if (this.type == Shape.RECTANGLE) {
                if (this.fill) {
                    graphics.fillRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
                    return;
                }
                graphics.drawRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
            }
            else if (this.type == Shape.OVAL) {
                if (this.fill) {
                    graphics.fillOval(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
                    return;
                }
                graphics.drawOval(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
            }
            else {
                if (this.fill) {
                    graphics.fillArc(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height, this.startAngle, this.arcAngle);
                    return;
                }
                graphics.drawArc(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height, this.startAngle, this.arcAngle);
            }
        }
    }
    
    static {
        orange = new Color(255, 128, 0);
        LINE = Sprite.ZERO;
        RECTANGLE = Sprite.ONE;
        OVAL = Sprite.TWO;
        ARC = Sprite.THREE;
    }
}
