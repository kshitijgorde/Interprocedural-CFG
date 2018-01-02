import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import gamelib.AVEntry;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class FieldControls extends OffComponent implements MouseListener, MouseMotionListener
{
    private static Image tanki;
    private static Image playeri;
    private boolean in;
    private boolean tanks;
    private int sel;
    
    FieldControls(final ActionBuffer actionBuffer) {
        super(actionBuffer, new Rectangle(-1000, -1000, 70, 118));
        this.sel = -1;
        this.setPosition(super.buffer.getBounds().width - super.bounds.width, super.buffer.getBounds().height - 32);
        super.buffer.addMouseListener(this);
        super.buffer.addMouseMotionListener(this);
        if (FieldControls.playeri == null) {
            FieldControls.playeri = new AVEntry("Players.gif", 0).getImage();
        }
        if (FieldControls.tanki == null) {
            FieldControls.tanki = new AVEntry("Tanks.gif", 0).getImage();
        }
        this.setVisible(true);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Field field = (Field)super.buffer;
        if (this.in && this.sel >= 0) {
            if (this.tanks) {
                field.tpp = this.sel + 1;
            }
            else {
                field.pnum = this.sel;
            }
            field.init();
            field.generate();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        super.buffer.requestFocus();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.in = false;
        this.setPosition(super.bounds.x, super.buffer.getBounds().height - 32);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int sel = this.sel;
        final boolean in = this.in;
        final boolean tanks = this.tanks;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (super.bounds.contains(x, y)) {
            this.in = true;
            if (y < super.bounds.y + 32) {
                if (x < super.bounds.x + 36) {
                    this.tanks = false;
                }
                else {
                    this.tanks = true;
                }
                this.sel = -1;
            }
            else {
                this.sel = (y - super.bounds.y - 35) / 16;
                if (this.sel > 4) {
                    this.sel = 4;
                }
            }
        }
        else {
            this.in = false;
        }
        if (this.in && !in) {
            this.setPosition(super.bounds.x, super.buffer.getBounds().height - 118);
        }
        if (!this.in && in) {
            this.setPosition(super.bounds.x, super.buffer.getBounds().height - 32);
        }
        if (this.sel != sel || this.tanks != tanks) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void paint(final Graphics graphics) {
        graphics.setColor(new Color(128, 128, 128));
        if (FieldControls.playeri != null) {
            graphics.drawImage(FieldControls.playeri, super.bounds.x, super.bounds.y, super.buffer);
            if (this.in) {
                if (this.tanks && FieldControls.tanki != null) {
                    graphics.drawImage(FieldControls.tanki, super.bounds.x, super.bounds.y + 31, super.buffer);
                }
                if (this.sel >= 0) {
                    graphics.fillArc(super.bounds.x - 2, super.bounds.y + 36 + 16 * this.sel, 7, 14, 270, 180);
                    graphics.fillArc(super.bounds.x + 65, super.bounds.y + 36 + 16 * this.sel, 7, 14, 90, 180);
                }
            }
            else {
                graphics.drawLine(super.bounds.x, super.bounds.y + 31, super.bounds.x + 35, super.bounds.y + 31);
            }
        }
    }
    
    public void removeSelf() {
        super.buffer.removeMouseMotionListener(this);
        super.buffer.removeMouseListener(this);
        super.removeSelf();
    }
}
