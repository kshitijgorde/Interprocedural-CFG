import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class display extends Panel
{
    picture pic;
    
    public display(final imgslide imgslide, final picture pic) {
        this.pic = pic;
        this.setSize(imgslide.game.getSize());
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.pic.pic, 0, 0, this.getSize().width, this.getSize().height, this);
    }
}
