import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import gamelib.AVEntry;
import gamelib.ActionBuffer;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class StatsBox extends OffComponent implements MouseListener, KeyListener
{
    private Image img;
    
    StatsBox(final ActionBuffer actionBuffer) {
        super(actionBuffer);
        super.buffer.addKeyListener(this);
        super.buffer.addMouseListener(this);
        this.img = new AVEntry("Statistics.gif", 0).getImage();
        if (this.img == null) {
            this.removeSelf();
            ((Field)super.buffer).generate();
            return;
        }
        final Rectangle bounds = super.buffer.getBounds();
        this.setSize(this.img.getWidth(super.buffer), this.img.getHeight(super.buffer));
        this.setPosition((bounds.width - super.bounds.width) / 2, (bounds.height - super.bounds.height) / 2);
        this.setVisible(true);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.removeSelf();
        ((Field)super.buffer).generate();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.removeSelf();
        ((Field)super.buffer).generate();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, super.bounds.x, super.bounds.y, super.buffer);
        }
        graphics.setColor(Color.black);
        final Player[] players = ((Field)super.buffer).players;
        for (int i = 0; i < players.length; ++i) {
            graphics.drawString(String.valueOf(players[i].name) + " won " + String.valueOf(players[i].vics) + " battles", super.bounds.x + 180, super.bounds.y + 90 + i * 18);
        }
    }
    
    public void removeSelf() {
        super.removeSelf();
        super.buffer.removeKeyListener(this);
        super.buffer.removeMouseListener(this);
    }
}
