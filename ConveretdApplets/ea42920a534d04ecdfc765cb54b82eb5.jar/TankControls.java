import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import java.awt.event.KeyListener;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class TankControls extends OffComponent implements KeyListener
{
    boolean block;
    private float olda;
    private float oldv;
    private float oldh;
    
    TankControls(final ActionBuffer actionBuffer) {
        super(actionBuffer, new Rectangle(0, 0, 120, 60));
        this.block = false;
        super.buffer.addKeyListener(this);
        this.setVisible(true);
        this.setActive(true);
    }
    
    protected void go() {
        final Tank actt = ((Field)super.buffer).actt;
        if (actt == null) {
            return;
        }
        if (actt.a != this.olda || actt.v != this.oldv || actt.h != this.oldh) {
            this.olda = actt.a;
            this.oldv = actt.v;
            this.oldh = actt.h;
            this.repaint();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final Tank actt = ((Field)super.buffer).actt;
        if (!this.block && actt != null) {
            final int[] keymap = actt.owner.keymap;
            if (keyEvent.getKeyCode() == keymap[0]) {
                final Tank tank = actt;
                --tank.a;
                if (actt.a < -180.0f) {
                    final Tank tank2 = actt;
                    tank2.a += 360.0f;
                }
            }
            else if (keyEvent.getKeyCode() == keymap[1]) {
                final Tank tank3 = actt;
                ++tank3.a;
                if (actt.a > 180.0f) {
                    final Tank tank4 = actt;
                    tank4.a -= 360.0f;
                }
            }
            else if (keyEvent.getKeyCode() == keymap[2]) {
                final Tank tank5 = actt;
                --tank5.v;
                if (actt.v < 0.0f) {
                    actt.v = 0.0f;
                }
            }
            else if (keyEvent.getKeyCode() == keymap[3]) {
                final Tank tank6 = actt;
                ++tank6.v;
            }
            else {
                if (keyEvent.getKeyCode() != keymap[4]) {
                    return;
                }
                actt.shoot();
            }
            actt.repaint();
            Thread.yield();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    protected void paint(final Graphics graphics) {
        final Tank actt = ((Field)super.buffer).actt;
        if (actt != null) {
            graphics.setColor(Color.white);
            graphics.drawString("Structure:  " + String.valueOf((int)actt.h), 10, 16);
            graphics.drawString("Velocity:  " + String.valueOf((int)actt.v), 10, 30);
            graphics.drawString("Angle:  " + String.valueOf((int)actt.a), 10, 44);
        }
    }
    
    public void removeSelf() {
        super.buffer.removeKeyListener(this);
        super.removeSelf();
    }
}
