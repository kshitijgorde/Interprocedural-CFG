import java.awt.Graphics;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class textObject extends scrollingObject
{
    Color StringColor;
    String name;
    Font font;
    FontMetrics fm;
    objectScroller ref;
    int limitY;
    int firstTime;
    String motion;
    int originX;
    int originY;
    
    public textObject(final objectScroller ref, final int locx, final int locy, final String name, final Font font, final URL url, final String target, final Color stringColor) {
        this.firstTime = 1;
        this.ref = ref;
        super.locx = locx;
        super.locy = locy;
        this.name = name;
        this.font = font;
        super.url = url;
        super.target = target;
        this.StringColor = stringColor;
        this.originX = super.locx;
        this.limitY = this.ref.getSize().height;
        this.originY = super.locy;
    }
    
    public void paint(final Graphics graphics) {
        if (this.originY == 0) {
            this.originY = this.ref.getSize().height;
        }
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.originX = this.ref.getSize().width / 2 - this.fm.stringWidth(this.name) / 2;
        if (!super.mouseOver) {
            graphics.setColor(this.StringColor);
            graphics.drawString(this.name, this.originX, this.originY + this.fm.getHeight() + this.fm.getDescent());
            super.fmHeight = this.fm.getHeight();
            super.fmWidth = this.fm.stringWidth(this.name);
        }
        else if (super.mouseOver) {
            super.fmHeight = this.fm.getHeight();
            super.fmWidth = this.fm.stringWidth(this.name);
            graphics.setColor(Color.blue);
            graphics.drawString(this.name, this.originX, this.originY + this.fm.getHeight() + this.fm.getDescent());
            super.mouseOver = false;
        }
        if (!super.red) {
            --this.originY;
        }
        super.locy = this.originY;
        super.locx = this.originX;
    }
    
    public void stopInc() {
        super.red = true;
    }
    
    public void unstopInc() {
        super.red = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
