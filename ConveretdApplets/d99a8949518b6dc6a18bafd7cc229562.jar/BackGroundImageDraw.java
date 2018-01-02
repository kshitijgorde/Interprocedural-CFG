import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class BackGroundImageDraw
{
    public static final int CENTER = 0;
    public static final int MOSAIC = 1;
    public static final int STRETCH = 2;
    
    public static void draw(final Component component, final Image image, final int n) {
        draw(component.getGraphics(), new Rectangle(component.getSize()), image, n, component);
    }
    
    public static void draw(final Graphics graphics, final Rectangle rectangle, final Image image, final int n, final ImageObserver imageObserver) {
        switch (n) {
            case 0: {
                drawCenter(graphics, rectangle, image, imageObserver);
            }
            case 1: {
                drawMosaic(graphics, rectangle, image, imageObserver);
            }
            case 2: {
                graphics.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, imageObserver);
            }
            default: {}
        }
    }
    
    protected static void drawCenter(final Graphics graphics, final Rectangle rectangle, final Image image, final ImageObserver imageObserver) {
        graphics.drawImage(image, rectangle.x + (rectangle.width - image.getWidth(imageObserver)) / 2, rectangle.x + (rectangle.height - image.getHeight(imageObserver)) / 2, imageObserver);
    }
    
    protected static void drawMosaic(final Graphics graphics, final Rectangle rectangle, final Image image, final ImageObserver imageObserver) {
        final int width = image.getWidth(imageObserver);
        final int height = image.getHeight(imageObserver);
        int i = 0;
        if (width <= 0 || height <= 0) {
            return;
        }
        while (i < rectangle.height) {
            for (int j = 0; j < rectangle.width; j += width) {
                graphics.drawImage(image, rectangle.x + j, rectangle.y + i, imageObserver);
            }
            i += height;
        }
    }
    
    protected static void drawStrech(final Graphics graphics, final Rectangle rectangle, final Image image, final ImageObserver imageObserver) {
        graphics.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, imageObserver);
    }
}
