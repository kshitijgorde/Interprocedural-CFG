import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class XPanel extends Component
{
    private int Width;
    private int Height;
    private Color BGCol;
    private int[] naPanel;
    private Image panImg;
    private MemoryImageSource memImg;
    public boolean isAntiAlias;
    public boolean isModified;
    
    XPanel(final int width, final int height) {
        this.Width = 320;
        this.Height = 200;
        this.BGCol = Color.black;
        this.isAntiAlias = true;
        this.isModified = true;
        this.Width = width;
        this.Height = height;
        this.naPanel = new int[width * height];
        for (int i = 0; i < width * height; ++i) {
            this.naPanel[i] = -16777216;
        }
        (this.memImg = new MemoryImageSource(width, height, this.naPanel, 0, width)).setAnimated(true);
        this.panImg = this.createImage(this.memImg);
    }
    
    public Image getImage() {
        this.refresh();
        return this.panImg;
    }
    
    public void refresh() {
        if (this.isModified) {
            this.memImg.newPixels();
            this.isModified = false;
        }
    }
    
    public void fill(final int n) {
        for (int i = 0; i < this.Width * this.Height; ++i) {
            this.naPanel[i] = n;
        }
        this.BGCol = new Color(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF);
        this.isModified = true;
    }
    
    public void fill(final Color bgCol) {
        final int n = 0xFF000000 | bgCol.getRed() << 16 | bgCol.getGreen() << 8 | bgCol.getBlue();
        for (int i = 0; i < this.Width * this.Height; ++i) {
            this.naPanel[i] = n;
        }
        this.BGCol = bgCol;
        this.isModified = true;
    }
    
    public void fill(final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.Width, this.Height, this.naPanel, 0, this.Width);
        this.isModified = true;
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getWidth() {
        return this.Width;
    }
    
    public int getHeight() {
        return this.Height;
    }
    
    public Color getBGColor() {
        return this.BGCol;
    }
    
    public void setPixelC(final int n, final int n2, final Color color) {
        if (n >= 0 && n < this.Width && n2 >= 0 && n2 < this.Height) {
            this.naPanel[n + n2 * this.Width] = (0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue());
        }
        this.isModified = true;
    }
    
    public Color getPixelC(final int n, final int n2) {
        if (n >= 0 && n < this.Width && n2 >= 0 && n2 < this.Height) {
            final int n3 = this.naPanel[n + n2 * this.Width];
            return new Color(n3 >> 16 & 0xFF, n3 >> 8 & 0xFF, n3 & 0xFF);
        }
        return null;
    }
    
    public int getPixel(final int n, final int n2) {
        if (n >= 0 && n < this.Width && n2 >= 0 && n2 < this.Height) {
            return this.naPanel[n + n2 * this.Width];
        }
        return -1;
    }
    
    public void setPixel(final int n, final int n2, final int n3) {
        if (n >= 0 && n < this.Width && n2 >= 0 && n2 < this.Height) {
            this.naPanel[n + n2 * this.Width] = n3;
        }
        this.isModified = true;
    }
    
    public void drawRect(int n, int n2, int n3, int n4, final int n5) {
        if (n > n3) {
            final int n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 > n4) {
            final int n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        for (int i = n; i <= n3; ++i) {
            for (int j = n2; j <= n4; ++j) {
                this.setPixel(i, j, n5);
            }
        }
    }
}
