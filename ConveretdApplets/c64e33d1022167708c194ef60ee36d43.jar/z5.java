import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class z5 extends Canvas
{
    public String addr_str;
    private String z0;
    public int button_x;
    public int button_y;
    public Image image;
    public boolean original;
    public URL pict;
    public int picture_height;
    public String picture_protocol;
    public int picture_width;
    public boolean selected;
    public int xx;
    public int yy;
    
    public void draw_picture(final Image image, final String addr_str) {
        this.image = image;
        this.addr_str = addr_str;
        this.repaint();
    }
    
    public String getLabel() {
        return this.z0;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= 32) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.addr_str.equals("")) {
            graphics.setColor(Color.orange);
            graphics.fillRect(0, 0, this.picture_width, this.picture_height);
            graphics.setColor(Color.white);
            final Font font = new Font("Helvetica", 1, 13);
            graphics.setFont(font);
            graphics.getFontMetrics(font);
        }
    }
    
    public void setLabel(final String z0) {
        this.z0 = z0;
    }
    
    public z5(final String z0) {
        this.original = false;
        this.selected = false;
        this.xx = 0;
        this.yy = 0;
        this.picture_width = 0;
        this.picture_height = 0;
        this.button_x = 0;
        this.button_y = 0;
        this.addr_str = "";
        this.picture_protocol = "";
        this.z0 = z0;
    }
}
