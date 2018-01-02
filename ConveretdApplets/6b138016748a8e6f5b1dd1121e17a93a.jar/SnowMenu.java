import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnowMenu extends KLMenu
{
    Image a166;
    Image a167;
    Graphics a168;
    int a169;
    int a170;
    int a92;
    int a171;
    int a172;
    int a173;
    int a174;
    Rectangle[] a175;
    int count;
    
    public void init() {
        this.a171 = this.a77("flakes", 60);
        this.a172 = this.a77("gust", 10);
        this.a173 = this.a77("wind", 3);
        this.a174 = this.a77("gravity", 2);
        this.a166 = this.getImage(this.getDocumentBase(), this.getParameter("snowflakes"));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.a166, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.a167 = this.createImage(this.size().width, this.size().height);
        this.a168 = this.a167.getGraphics();
        this.a169 = this.a166.getWidth(this) / this.a92;
        this.a170 = this.a166.getHeight(this);
        this.a175 = new Rectangle[this.a171];
        for (int i = 0; i < this.a171; ++i) {
            this.a175[i] = new Rectangle((int)(Math.random() * this.size().width), (int)(Math.random() * this.size().height), (int)(Math.random() * this.a92), 0);
        }
        super.init("SnowMenu http://www.javabase.co.uk/");
    }
    
    public void render() {
        this.a168.drawImage(super.a59, 0, 0, this);
        ++this.count;
        final double n = (2.0 + Math.sin(this.count * this.a172 / 100)) * this.a173;
        for (int i = 0; i < this.a171; ++i) {
            this.a178(this.a175[i]);
            final Rectangle rectangle = this.a175[i];
            rectangle.x += (int)(Math.cos((-this.count + 2 * i + this.a175[i].y) / 50) * n / 2.0);
            final Rectangle rectangle2 = this.a175[i];
            rectangle2.y += 5 + this.a174 - i % 5;
            if (this.a175[i].x > this.size().width) {
                this.a175[i].x = -this.a169;
            }
            if (this.a175[i].x + this.a169 < 0) {
                this.a175[i].x = this.size().width;
            }
            if (this.a175[i].y > this.size().height) {
                this.a175[i].y = -this.a169;
            }
            if (this.a175[i].y + this.a169 < 0) {
                this.a175[i].y = this.size().height;
            }
            this.a175[i].width = (this.count + i) / 4 % 2 + i % 5;
        }
        super.render();
    }
    
    protected void a18(final Rectangle rectangle, final int n) {
        super.a48.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height).drawImage(this.a167, -rectangle.x, -rectangle.y, this);
    }
    
    protected void a86() {
        super.fading = true;
        for (int i = 0; i < super.a61.length; ++i) {
            if (super.optionOver == i) {
                if (super.a38[i] != super.a36 - 1) {
                    super.a38[i] = super.a36 - 1;
                }
            }
            else if (super.a38[i] > 0) {
                final int[] a38 = super.a38;
                final int n = i;
                --a38[n];
                super.fading = true;
            }
        }
        this.render();
    }
    
    private void a178(final Rectangle rectangle) {
        final Graphics create = this.a168.create(rectangle.x, rectangle.y, this.a169, this.a170);
        Thread.yield();
        create.drawImage(this.a166, -rectangle.width * this.a169, 0, this);
    }
    
    public SnowMenu() {
        this.a92 = 10;
    }
}
