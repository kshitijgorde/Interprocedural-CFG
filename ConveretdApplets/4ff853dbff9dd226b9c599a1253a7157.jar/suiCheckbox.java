import java.awt.Dimension;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiCheckbox extends Canvas
{
    Image image;
    String title;
    int width;
    int height;
    boolean check;
    boolean group;
    suiCheckboxGroup schg;
    int myy;
    int myd;
    int myx;
    
    suiCheckbox(final String title, final Image image) {
        this.check = false;
        this.group = false;
        this.image = image;
        this.title = title;
        this.myy = this.getFontMetrics(new Font("Helvetica", 1, 14)).getHeight();
        this.myd = this.getFontMetrics(new Font("Helvetica", 1, 14)).getDescent();
        this.myx = this.getFontMetrics(new Font("Helvetica", 1, 14)).stringWidth(title);
        this.height = Math.max(this.myy, image.getHeight(this)) + 8;
        this.width = this.myx + image.getWidth(this) + this.height;
    }
    
    suiCheckbox(final String title, final Image image, final suiCheckboxGroup schg, final boolean check) {
        this.check = false;
        this.group = false;
        this.image = image;
        this.title = title;
        this.check = check;
        this.group = true;
        this.schg = schg;
        this.myy = this.getFontMetrics(new Font("Helvetica", 1, 14)).getHeight();
        this.myd = this.getFontMetrics(new Font("Helvetica", 1, 14)).getDescent();
        this.myx = this.getFontMetrics(new Font("Helvetica", 1, 14)).stringWidth(title);
        this.height = Math.max(this.myy, image.getHeight(this)) + 8;
        this.width = this.myx + image.getWidth(this) + this.height;
        schg.sch[schg.count] = this;
        if (check) {
            schg.curr = this;
        }
        ++schg.count;
        schg.refresh();
    }
    
    public void paint(final Graphics graphics) {
        this.myy = graphics.getFontMetrics().getHeight();
        this.myd = graphics.getFontMetrics().getDescent();
        this.myx = graphics.getFontMetrics().stringWidth(this.title);
        this.height = Math.max(this.myy, this.image.getHeight(this));
        this.setSize(this.width = this.myx + this.image.getWidth(this) + this.height + 4, this.height);
        if (!this.group) {
            if (!this.check) {
                graphics.setColor(new Color(220, 220, 220));
                graphics.drawLine(6, 6, this.height - 7, 6);
                graphics.drawLine(6, 6, 6, this.height - 7);
                graphics.drawLine(7, 7, this.height - 8, 7);
                graphics.drawLine(7, 7, 7, this.height - 8);
                graphics.setColor(new Color(90, 90, 90));
                graphics.drawLine(6, this.height - 7, this.height - 7, this.height - 7);
                graphics.drawLine(this.height - 7, this.height - 7, this.height - 7, 6);
                graphics.drawLine(7, this.height - 8, this.height - 8, this.height - 8);
                graphics.drawLine(this.height - 8, this.height - 8, this.height - 8, 7);
                graphics.setColor(this.getBackground());
                graphics.fillRect(8, 8, this.height - 16, this.height - 16);
            }
            else {
                graphics.setColor(new Color(90, 90, 90));
                graphics.drawLine(6, 6, this.height - 7, 6);
                graphics.drawLine(6, 6, 6, this.height - 7);
                graphics.drawLine(7, 7, this.height - 8, 7);
                graphics.drawLine(7, 7, 7, this.height - 8);
                graphics.setColor(new Color(220, 220, 220));
                graphics.drawLine(6, this.height - 7, this.height - 7, this.height - 7);
                graphics.drawLine(this.height - 7, this.height - 7, this.height - 7, 6);
                graphics.drawLine(7, this.height - 8, this.height - 8, this.height - 8);
                graphics.drawLine(this.height - 8, this.height - 8, this.height - 8, 7);
                graphics.setColor(this.getBackground().darker());
                graphics.fillRect(8, 8, this.height - 16, this.height - 16);
            }
        }
        else if (!this.check) {
            graphics.setColor(new Color(220, 220, 220));
            graphics.drawArc(6, 6, this.height - 13, this.height - 13, 45, 180);
            graphics.drawArc(7, 7, this.height - 15, this.height - 15, 40, 180);
            graphics.setColor(new Color(90, 90, 90));
            graphics.drawArc(6, 6, this.height - 13, this.height - 13, 225, 180);
            graphics.drawArc(7, 7, this.height - 15, this.height - 15, 220, 180);
            graphics.setColor(this.getBackground());
            graphics.fillOval(8, 8, this.height - 17, this.height - 17);
        }
        else {
            graphics.setColor(new Color(90, 90, 90));
            graphics.drawArc(6, 6, this.height - 13, this.height - 13, 45, 180);
            graphics.drawArc(7, 7, this.height - 15, this.height - 15, 40, 180);
            graphics.setColor(new Color(220, 220, 220));
            graphics.drawArc(6, 6, this.height - 13, this.height - 13, 225, 180);
            graphics.drawArc(7, 7, this.height - 15, this.height - 15, 220, 180);
            graphics.setColor(this.getBackground().darker());
            graphics.fillOval(8, 8, this.height - 17, this.height - 17);
        }
        graphics.drawImage(this.image, this.height, this.height / 2 - this.image.getHeight(this) / 2, this);
        graphics.setColor(Color.black);
        graphics.drawString(this.title, this.width - this.myx - 2, this.height / 2 + this.myy / 2 - this.myd);
        if (this.check) {
            graphics.setColor(Color.blue);
            graphics.drawRect(this.height, 0, this.image.getWidth(this) + this.myx + 2, this.height - 1);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.group && !this.check) {
            this.check = !this.check;
            this.schg.curr = this;
            this.schg.refresh();
        }
        else if (!this.group) {
            this.check = !this.check;
            this.repaint();
        }
        return true;
    }
    
    public void setState(final boolean check) {
        this.check = check;
    }
    
    public boolean getState() {
        return this.check;
    }
    
    public void setCheckboxGroup(final suiCheckboxGroup schg) {
        this.schg = schg;
        this.group = true;
    }
    
    public suiCheckboxGroup getCheckboxGroup() {
        return this.schg;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
}
