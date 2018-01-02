import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class z2 extends Canvas
{
    public String addr_str;
    public boolean button_show;
    public int iheight;
    public Image image;
    public int index;
    public int iwidth;
    public boolean original;
    public boolean paint_enlarge_only;
    public URL pict;
    public int picture_height;
    public String picture_protocol;
    public int picture_width;
    public int x_offset;
    public int xx;
    public int y_offset;
    public int yy;
    
    public void draw_picture(final Image image, final String addr_str) {
        this.image = image;
        this.addr_str = addr_str;
        this.update(this.getGraphics());
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= 32) {
            if (n4 > 0) {
                this.repaint();
            }
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.addr_str.equals("")) {
            this.iwidth = this.image.getWidth(null);
            final float n = new Float(this.image.getHeight(null)) / new Float(this.image.getWidth(null));
            this.iheight = this.image.getHeight(null);
            if (this.iheight >= this.yy) {
                this.iwidth = Math.round(this.yy / n);
                this.iheight = this.yy;
                this.original = false;
            }
            if (this.iwidth >= this.xx) {
                this.iheight = Math.round(this.xx * n);
                this.iwidth = this.xx;
                this.original = false;
            }
            this.picture_height = this.iheight;
            this.x_offset = 0;
            this.y_offset = 0;
            if (this.iheight < this.yy) {
                this.y_offset = (this.yy - this.iheight) / 2;
            }
            if (!this.paint_enlarge_only) {
                if (!this.original) {
                    graphics.drawImage(this.image, this.x_offset, this.y_offset, this.iwidth, this.iheight, this.getBackground(), this);
                }
                else {
                    graphics.drawImage(this.image, this.x_offset, this.y_offset, this.getBackground(), this);
                }
            }
            if (this.button_show && this.image.getWidth(null) > 0) {
                if (z1.z141) {
                    graphics.setFont(new Font("Helvetica", 1, 14));
                    graphics.setColor(Color.black);
                    final String z23 = z1.z23;
                    graphics.drawString(z23, this.x_offset + 4, this.y_offset + 16);
                    graphics.drawString(z23, this.x_offset + 6, this.y_offset + 16);
                    graphics.drawString(z23, this.x_offset + 5, this.y_offset + 15);
                    graphics.drawString(z23, this.x_offset + 5, this.y_offset + 17);
                    graphics.setColor(Color.white);
                    graphics.drawString(z23, this.x_offset + 5, 16 + this.y_offset);
                    z1.z141 = false;
                }
                else if (this.iwidth < this.xx) {
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(this.iwidth, this.y_offset, this.xx - this.iwidth, 20);
                }
            }
        }
    }
    
    z2() {
        this.original = false;
        this.xx = 0;
        this.yy = 0;
        this.picture_width = 0;
        this.picture_height = 0;
        this.button_show = false;
        this.x_offset = 0;
        this.y_offset = 0;
        this.iwidth = 0;
        this.iheight = 0;
        this.paint_enlarge_only = false;
        this.addr_str = "";
        this.picture_protocol = "";
        this.index = 0;
    }
}
