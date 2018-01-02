import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageButton extends ImageLabel
{
    protected static final int defaultBorderWidth = 4;
    protected static final Color defaultBorderColor;
    private boolean mouseIsDown;
    private int darkness;
    private Image grayImage;
    
    static {
        defaultBorderColor = new Color(160, 160, 160);
    }
    
    public ImageButton() {
        this.mouseIsDown = false;
        this.darkness = -5263441;
        this.grayImage = null;
        this.setBorders();
    }
    
    public ImageButton(final Image image) {
        super(image);
        this.mouseIsDown = false;
        this.darkness = -5263441;
        this.grayImage = null;
        this.setBorders();
    }
    
    public ImageButton(final String s) {
        super(s);
        this.mouseIsDown = false;
        this.darkness = -5263441;
        this.grayImage = null;
        this.setBorders();
    }
    
    public ImageButton(final URL url) {
        super(url);
        this.mouseIsDown = false;
        this.darkness = -5263441;
        this.grayImage = null;
        this.setBorders();
    }
    
    public ImageButton(final URL url, final String s) {
        super(url, s);
        this.mouseIsDown = false;
        this.darkness = -5263441;
        this.grayImage = null;
        this.setBorders();
    }
    
    public boolean action(final Event event, final Object o) {
        this.debug("Clicked on button for " + this.getImageString() + ".");
        return false;
    }
    
    private void createGrayImage(final Graphics graphics) {
        this.grayImage = this.createImage(new FilteredImageSource(this.getImage().getSource(), new GrayFilter(this.darkness)));
        final int border = this.getBorder();
        if (this.hasExplicitSize()) {
            this.prepareImage(this.grayImage, this.getWidth() - 2 * border, this.getHeight() - 2 * border, this);
        }
        else {
            this.prepareImage(this.grayImage, this);
        }
        super.paint(graphics);
    }
    
    private void drawBorder(final boolean b) {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(this.getBorderColor());
        int n = 0;
        int n2 = 0;
        int width = this.getWidth();
        int height = this.getHeight();
        for (int border = this.getBorder(), i = 0; i < border; ++i) {
            graphics.draw3DRect(n, n2, width, height, b);
            ++n;
            ++n2;
            width -= 2;
            height -= 2;
        }
    }
    
    public int getDarkness() {
        return this.darkness;
    }
    
    public Image getGrayImage() {
        return this.grayImage;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mouseIsDown = true;
        final Graphics graphics = this.getGraphics();
        final int border = this.getBorder();
        if (this.hasExplicitSize()) {
            graphics.drawImage(this.grayImage, border, border, this.getWidth() - 2 * border, this.getHeight() - 2 * border, this);
        }
        else {
            graphics.drawImage(this.grayImage, border, border, this);
        }
        this.drawBorder(false);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.mouseIsDown) {
            this.paint(this.getGraphics());
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mouseIsDown = false;
        if (this.inside(n, n2)) {
            this.paint(this.getGraphics());
            event.id = 1001;
            event.arg = this.getImage();
            return this.action(event, event.arg);
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.grayImage == null) {
            this.createGrayImage(graphics);
        }
        this.drawBorder(true);
    }
    
    private void setBorders() {
        this.setBorder(4);
        this.setBorderColor(ImageButton.defaultBorderColor);
    }
    
    public void setDarkness(final int darkness) {
        this.darkness = darkness;
    }
    
    public void setGrayImage(final Image grayImage) {
        this.grayImage = grayImage;
    }
}
