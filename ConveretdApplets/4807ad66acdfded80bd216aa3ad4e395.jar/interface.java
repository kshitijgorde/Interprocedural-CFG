import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface
{
    private Image Da;
    private Image Ea;
    private int width;
    private int height;
    
    public interface(final Image da, final int width, final int height) {
        this.Da = da;
        this.width = width;
        this.height = height;
        if (da != null) {
            if (width <= 0) {
                this.width = da.getWidth(null);
            }
            if (height <= 0) {
                this.height = da.getHeight(null);
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
        if (this.Ea == null) {
            this.b(component);
        }
        graphics.drawImage(this.Ea, n, 0, component);
    }
    
    private void b(final Component component) {
        this.Ea = component.createImage(this.width, this.height);
        final Graphics graphics = this.Ea.getGraphics();
        if (this.Da == null || this.width > this.Da.getWidth(null) || this.height > this.Da.getHeight(null)) {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(color);
        }
        if (this.Da != null) {
            graphics.drawImage(this.Da, (this.width - this.Da.getWidth(null)) / 2, (this.height - this.Da.getHeight(null)) / 2, component);
        }
    }
}
