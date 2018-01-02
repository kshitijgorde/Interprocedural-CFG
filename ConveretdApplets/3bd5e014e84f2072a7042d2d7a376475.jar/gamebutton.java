import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class gamebutton extends Panel implements MouseListener
{
    int butno;
    int butval;
    picture pic;
    board game;
    
    public gamebutton(final picture pic, final board game, final int n) {
        this.game = game;
        this.pic = pic;
        this.butval = n;
        this.butno = n;
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, 0, height);
        graphics.drawLine(0, 0, width, 0);
        if (this.butno == this.game.leeg && !this.game.startsit()) {
            graphics.setColor(this.game.leegkleur[this.game.leegkleurnr]);
            graphics.fillRect(1, 1, width, height);
            return;
        }
        graphics.drawImage(this.pic.ppic[this.butval], 1, 1, width, height, this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.game.process(this);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
