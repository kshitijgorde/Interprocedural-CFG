import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class imageObject extends scrollingObject
{
    Image name;
    objectScroller ref;
    int limitY;
    int firstTime;
    String motion;
    int originX;
    int originY;
    
    public imageObject(final objectScroller ref, final int locx, final int locy, final Image name, final URL url, final String target) {
        this.firstTime = 1;
        this.ref = ref;
        super.locx = locx;
        super.locy = locy;
        this.name = name;
        super.url = url;
        super.target = target;
        this.originX = super.locx;
        this.limitY = this.ref.getSize().height;
        this.originY = super.locy;
    }
    
    public void paint(final Graphics graphics) {
        super.fmWidth = this.name.getWidth(this.ref);
        super.fmHeight = this.name.getHeight(this.ref);
        if (this.originY == 0) {
            this.originY = this.ref.getSize().height;
        }
        this.originX = this.ref.getSize().width / 2 - this.name.getWidth(this.ref) / 2;
        if (!super.mouseOver) {
            graphics.drawImage(this.name, this.originX, this.originY, this.ref);
        }
        else if (super.mouseOver) {
            graphics.setColor(Color.blue);
            graphics.drawRect(this.originX - 2, this.originY - 2, this.name.getWidth(this.ref) + 1, this.name.getHeight(this.ref) + 1);
            graphics.drawImage(this.name, this.originX, this.originY, this.ref);
            this.ref.showStatus("URL:" + super.url + "                Target Frame:" + super.target);
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
