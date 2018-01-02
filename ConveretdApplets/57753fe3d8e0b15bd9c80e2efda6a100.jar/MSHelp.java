import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSHelp extends Canvas
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private MoonStar moonStar;
    private Image background;
    
    public MSHelp(final MoonStar moonStar, final Image background) {
        this.moonStar = moonStar;
        this.background = background;
        this.setBackground(moonStar.colors[0]);
    }
    
    public void add() {
        this.moonStar.add(this);
        this.repaint();
    }
    
    public void remove() {
        this.moonStar.remove(this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, 320, 320, this);
    }
}
