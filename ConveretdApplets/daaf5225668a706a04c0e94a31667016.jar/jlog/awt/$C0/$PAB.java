// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$C0;

import java.awt.Container;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;

public class $PAB implements $CAB
{
    public Image image;
    public boolean $QAB;
    public Color $YH;
    public Rectangle bounds;
    
    public $PAB(final Color $yh, final Image image) {
        this.image = null;
        this.$QAB = true;
        this.$YH = null;
        this.bounds = null;
        this.image = image;
        this.$YH = $yh;
    }
    
    public void paint(final Graphics graphics, final Component component) {
        Rectangle rectangle = graphics.getClipRect();
        if (rectangle == null) {
            return;
        }
        final Rectangle bounds = this.bounds;
        if (bounds != null) {
            rectangle = rectangle.intersection(bounds);
            if (rectangle == null) {
                return;
            }
        }
        final Dimension size = component.size();
        if (size == null || size.width < 1 || size.height < 1) {
            return;
        }
        final Image image = this.image;
        Color color = this.$YH;
        if (color == null) {
            color = component.getBackground();
        }
        if (color != null && image == null) {
            graphics.setColor(color);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        if (image == null) {
            return;
        }
        if (!this.$QAB) {
            graphics.drawImage(image, 0, 0, size.width, size.height, color, component);
        }
        else {
            final int width = image.getWidth(component);
            final int height = image.getHeight(component);
            if (width < 1 || height < 1) {
                return;
            }
            final Point location = component.location();
            for (Container container = component.getParent(); container != null; container = container.getParent()) {
                final Point location2 = component.location();
                location.translate(location2.x, location2.y);
            }
            location.x %= width;
            if (location.x != 0) {
                location.x -= width;
            }
            location.y %= height;
            if (location.y != 0) {
                location.y -= height;
            }
            final Point point = location;
            point.x += rectangle.x / width * width;
            final Point point2 = location;
            point2.y += rectangle.y / height * height;
            for (int i = location.y; i < rectangle.y + rectangle.height; i += height) {
                for (int j = location.x; j < rectangle.x + rectangle.width; j += width) {
                    graphics.drawImage(image, j, i, color, component);
                }
            }
        }
    }
}
