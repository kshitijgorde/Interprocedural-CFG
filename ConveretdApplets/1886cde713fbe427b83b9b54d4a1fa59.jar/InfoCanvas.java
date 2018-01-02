import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class InfoCanvas extends Canvas
{
    private StringGradient sg1;
    private StringGradient sg2;
    private int found;
    public FindIt2Frame fi2f;
    private Color bgcolor;
    private Image icIm;
    private Graphics gicIm;
    boolean start;
    boolean infoRun;
    
    public InfoCanvas(final FindIt2Frame fi2f) {
        this.start = true;
        this.infoRun = true;
        this.setBackground(this.bgcolor = fi2f.bgColor);
        this.fi2f = fi2f;
        this.sg1 = new StringGradient(this);
        this.sg2 = new StringGradient(this);
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.start) {
                this.icIm = this.fi2f.getEmptyImage(300, 60);
                this.gicIm = this.icIm.getGraphics();
                this.start = false;
            }
            this.gicIm.setColor(this.bgcolor);
            this.gicIm.fillRect(0, 0, 400, 400);
            this.gicIm.drawImage(this.sg1.getGradient("Level: " + this.fi2f.lvl.getLevel(), new Font("Arial", 1, 20), this.bgcolor, Color.yellow, "green", 15), 5, 0, this);
            this.gicIm.drawImage(this.sg2.getGradient("Score: " + this.fi2f.getScore(), new Font("Arial", 1, 20), this.bgcolor, Color.yellow, "green", 15), 5, 25, this);
            for (int i = 0; i < 5; ++i) {
                this.gicIm.drawImage(this.fi2f.wrongIcon, 135 + 25 * i, 20, this);
            }
            for (int j = 0; j < this.fi2f.itemFound; ++j) {
                this.gicIm.drawImage(this.fi2f.rightIcon, 135 + 25 * j, 20, this);
            }
            graphics.drawImage(this.icIm, 0, 0, this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void resetData() {
        this.found = 0;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
