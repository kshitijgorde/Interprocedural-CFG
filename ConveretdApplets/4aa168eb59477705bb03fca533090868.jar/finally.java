import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class finally
{
    private Image lb;
    private Image mb;
    private int width;
    private int height;
    
    public finally(final Image lb, final int width, final int height) {
        this.lb = lb;
        this.width = width;
        this.height = height;
        if (lb != null) {
            if (width <= 0) {
                this.width = lb.getWidth(null);
            }
            if (height <= 0) {
                this.height = lb.getHeight(null);
            }
        }
        else if (width <= 0) {
            this.width = 5;
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void _(final Component component, final Graphics graphics, final int n) {
        if (this.mb == null) {
            this.a(component);
        }
        graphics.drawImage(this.mb, n, 0, component);
    }
    
    private void a(final Component component) {
        this.mb = component.createImage(this.width, this.height);
        final Graphics graphics = this.mb.getGraphics();
        if (this.lb == null || this.width > this.lb.getWidth(null) || this.height > this.lb.getHeight(null)) {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(color);
        }
        if (this.lb != null) {
            graphics.drawImage(this.lb, (this.width - this.lb.getWidth(null)) / 2, (this.height - this.lb.getHeight(null)) / 2, component);
        }
    }
}
