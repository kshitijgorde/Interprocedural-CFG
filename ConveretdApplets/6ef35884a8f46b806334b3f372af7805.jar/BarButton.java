import java.awt.Graphics;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

class BarButton extends BarItem
{
    FontMetrics fm;
    static Font bfont;
    Image img;
    ImageObserver ImOb;
    URL url;
    String text;
    String stat;
    int imgSize;
    boolean pressed;
    
    public BarButton(final int x, final int y, final int maxX, final int maxY, final Image img, final ImageObserver imOb, final URL url, final String text, final String comm, final String stat) {
        this.pressed = false;
        super.comm = comm;
        super.x = x;
        super.y = y;
        super.maxX = maxX;
        super.maxY = maxY;
        this.img = img;
        this.ImOb = imOb;
        this.url = url;
        this.text = text;
        this.stat = stat;
        BarButton.bfont = new Font(BarItem.font.getFamily(), BarItem.font.getStyle(), BarItem.font.getSize());
        this.imgSize = ((BarItem.font.getSize() > 16) ? BarItem.font.getSize() : 16);
    }
    
    public URL clicked() {
        return this.url;
    }
    
    public String getStat() {
        return this.stat;
    }
    
    public void Show(final Graphics graphics) {
        if (this.fm == null) {
            this.fm = graphics.getFontMetrics(BarItem.font);
            while (this.fm.stringWidth(this.text) > super.maxX - super.x - (this.imgSize + 9)) {
                if (this.text.length() < 4) {
                    this.text = "";
                    break;
                }
                this.text = String.valueOf(this.text.substring(0, this.text.length() - 4)) + "...";
            }
        }
        graphics.setColor(BarItem.BuCol);
        graphics.fill3DRect(super.x, super.y, super.maxX - super.x, super.maxY - super.y, !this.pressed);
        if (this.img != null) {
            graphics.drawImage(this.img, super.x + 3, super.y + 3, this.imgSize, this.imgSize, this.ImOb);
        }
        graphics.setFont(BarButton.bfont);
        graphics.setColor(BarItem.TCol);
        graphics.drawString(this.text, super.x + this.imgSize + 6, super.y + (super.maxY - super.y + this.fm.getHeight() - this.fm.getHeight() / 2) / 2);
    }
    
    public void mouseDown(final Graphics graphics) {
        this.pressed = true;
        this.Show(graphics);
    }
    
    public void mouseUp(final Graphics graphics) {
        this.pressed = false;
        this.Show(graphics);
    }
}
