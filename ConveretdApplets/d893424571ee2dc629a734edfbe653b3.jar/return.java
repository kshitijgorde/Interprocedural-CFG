import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class return
{
    private Image pa;
    private Image qa;
    private int width;
    private int height;
    
    public return(final Image pa, final int width, final int height) {
        this.pa = pa;
        this.width = width;
        this.height = height;
        if (pa != null) {
            if (width <= 0) {
                this.width = pa.getWidth(null);
            }
            if (height <= 0) {
                this.height = pa.getHeight(null);
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
    
    public void b(final Component component, final Graphics graphics, final int n) {
        if (this.qa == null) {
            this.a(component);
        }
        graphics.drawImage(this.qa, n, 0, component);
    }
    
    private void a(final Component component) {
        this.qa = component.createImage(this.width, this.height);
        final Graphics graphics = this.qa.getGraphics();
        if (this.pa == null || this.width > this.pa.getWidth(null) || this.height > this.pa.getHeight(null)) {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(color);
        }
        if (this.pa != null) {
            graphics.drawImage(this.pa, (this.width - this.pa.getWidth(null)) / 2, (this.height - this.pa.getHeight(null)) / 2, component);
        }
    }
}
