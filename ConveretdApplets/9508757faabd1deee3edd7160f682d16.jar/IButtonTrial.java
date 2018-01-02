import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class IButtonTrial extends Canvas
{
    private String name;
    private Image image;
    private Image grayImage;
    private int mouseEvent;
    private Font font;
    private int height;
    private int width;
    
    public IButtonTrial(final String name, final int width, final int height) {
        this.mouseEvent = 505;
        this.setBackground(Color.lightGray);
        this.name = name;
        this.setFont(this.font = new Font("SansSerif", 0, 13));
        this.resize(this.width = width, this.height = height);
    }
    
    public IButtonTrial(final String name, final Font font, final int width, final int height) {
        this.mouseEvent = 505;
        this.setBackground(Color.lightGray);
        this.name = name;
        this.setFont(this.font = font);
        this.resize(this.width = width, this.height = height);
    }
    
    public IButtonTrial(final String name, final Font font, final Image image, final int width, final int height) {
        this.mouseEvent = 505;
        this.image = image;
        this.grayImage = this.createImage(new FilteredImageSource(image.getSource(), new GrayFilterTrial()));
        this.setBackground(Color.lightGray);
        this.name = name;
        this.setFont(this.font = font);
        this.resize(this.width = width, this.height = height);
    }
    
    public IButtonTrial(final String name, final Image image, final int width, final int height) {
        this.mouseEvent = 505;
        this.image = image;
        this.grayImage = this.createImage(new FilteredImageSource(image.getSource(), new GrayFilterTrial()));
        this.setBackground(Color.lightGray);
        this.name = name;
        this.setFont(this.font = new Font("SansSerif", 0, 13));
        this.resize(this.width = width, this.height = height);
    }
    
    private void centerDisplay(final String s, final Image image, final Graphics graphics, final Color color, final int n, final int n2) {
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        graphics.setColor(color);
        final int stringWidth = fontMetrics.stringWidth(s);
        final int height = fontMetrics.getHeight();
        final int n3 = (n - stringWidth) / 2;
        final int n4 = (n2 - height) / 2 + fontMetrics.getAscent();
        if (image == null) {
            graphics.drawString(s, n3, n4);
        }
        else {
            final int width = image.getWidth(this);
            final int height2 = image.getHeight(this);
            final int n5 = n2 - 8;
            graphics.drawImage(image, (n - width) / 2, (n2 - (height + height2)) / 2, this);
            graphics.drawString(s, n3, n5);
        }
    }
    
    private void iconDown(final Graphics graphics) {
        this.getFontMetrics(this.font);
        graphics.setColor(new Color(200, 200, 200));
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, false);
        graphics.draw3DRect(2, 2, this.width - 5, this.height - 5, false);
        this.centerDisplay(this.name, this.image, graphics, Color.black, this.width, this.height);
    }
    
    private void iconNorm(final Graphics graphics) {
        this.getFontMetrics(this.font);
        graphics.setColor(Color.lightGray);
        graphics.clearRect(0, 0, this.width, this.height);
        this.centerDisplay(this.name, this.grayImage, graphics, Color.black, this.width, this.height);
    }
    
    private void iconUp(final Graphics graphics) {
        this.getFontMetrics(this.font);
        graphics.setColor(new Color(200, 200, 200));
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, true);
        graphics.draw3DRect(2, 2, this.width - 5, this.height - 5, true);
        this.centerDisplay(this.name, this.image, graphics, Color.black, this.width, this.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mouseEvent = event.id;
        this.repaint();
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.mouseEvent = event.id;
        this.repaint();
        return super.mouseEnter(event, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseEvent = event.id;
        this.repaint();
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mouseEvent = event.id;
        this.repaint();
        return super.mouseUp(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.mouseEvent == 504) {
            this.iconUp(graphics);
        }
        else if (this.mouseEvent == 505) {
            this.iconNorm(graphics);
        }
        else if (this.mouseEvent == 501) {
            this.iconDown(graphics);
        }
        else if (this.mouseEvent == 502) {
            this.iconUp(graphics);
        }
    }
}
