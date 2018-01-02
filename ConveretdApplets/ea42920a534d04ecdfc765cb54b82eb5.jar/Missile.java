import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Missile extends OffComponent
{
    static int[] heads;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected Warhead head;
    boolean call_impact;
    
    static {
        Missile.heads = new int[] { 1, 1, 3, 1, 3, 5 };
    }
    
    Missile(final ActionBuffer actionBuffer, final int n, final int n2, final float n3, final float n4, final Warhead head) {
        super(actionBuffer, new Rectangle(n - 2, n2 - 2, 6, 6));
        this.call_impact = true;
        this.head = head;
        this.setVisible(true);
        for (int i = 0; i < super.buffer.childs.size(); ++i) {
            if (super.buffer.childs.elementAt(i) instanceof Ground) {
                super.buffer.childs.removeElement(this);
                super.buffer.childs.insertElementAt(this, i);
                break;
            }
        }
        this.x = n;
        this.y = n2;
        this.dx = n4 / 10.0f * (float)Math.sin(6.283185307179586 * n3 / 360.0);
        this.dy = -(n4 / 10.0f * (float)Math.cos(6.283185307179586 * n3 / 360.0));
        this.setActive(true);
    }
    
    protected void go() {
        this.x += this.dx;
        this.y += this.dy;
        this.setPosition((int)this.x - 2, (int)this.y - 2);
        this.dy += 0.3f;
        final Field field = (Field)super.buffer;
        final float n = 1.9f * field.sky.wind - this.dx;
        this.dx += ((n < 0.0f) ? -1 : 1) * n * n / 512.0f;
        if (this.y >= field.ground.getHorizon((int)this.x)) {
            if (this.call_impact) {
                field.actt.impactAt((int)this.x);
            }
            this.removeSelf();
            this.head.ignite((int)this.x, field.ground.getHorizon((int)this.x));
        }
    }
    
    protected void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillOval((int)this.x - 2, (int)this.y - 2, 6, 6);
        graphics.setColor(new Color(80, 80, 80));
        graphics.fillOval((int)this.x - 1, (int)this.y - 1, 4, 4);
    }
}
