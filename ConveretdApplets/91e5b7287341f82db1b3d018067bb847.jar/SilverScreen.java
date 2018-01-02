import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class SilverScreen extends Canvas
{
    int width;
    int height;
    SlideProjector slideProjector;
    
    public SilverScreen(final int width, final int height, final SlideProjector slideProjector) {
        this.width = width;
        this.height = height;
        this.slideProjector = slideProjector;
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        this.size();
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, this.width, this.height);
    }
    
    public void displayImage(final Image image) {
        final Graphics graphics = this.getGraphics();
        final int nextInt = new Random().nextInt();
        final int projectorWidth = this.slideProjector.getProjectorWidth();
        final int projectorHeight = this.slideProjector.getProjectorHeight();
        final int delay = this.slideProjector.getDelay();
        switch (nextInt % 7) {
            case 0: {
                int n = projectorWidth;
                for (int i = projectorWidth; i >= 0; --i) {
                    graphics.drawImage(image, n, i, this);
                    --n;
                    for (int j = 0; j < delay; ++j) {}
                }
                break;
            }
            case 1: {
                for (int k = projectorWidth; k >= 0; --k) {
                    graphics.drawImage(image, k, 0, this);
                    for (int l = 0; l < delay; ++l) {}
                }
                break;
            }
            case 2: {
                for (int n2 = projectorWidth; n2 >= 0; --n2) {
                    graphics.drawImage(image, 0, n2, this);
                    for (int n3 = 0; n3 < delay; ++n3) {}
                }
                break;
            }
            case 3: {
                for (int n4 = 0; n4 < projectorWidth; ++n4) {
                    graphics.drawImage(image, 0, n4 - projectorWidth, this);
                    for (int n5 = 0; n5 < delay; ++n5) {}
                }
                break;
            }
            case 4: {
                int n6 = 0;
                for (int n7 = 0; n7 < projectorWidth; ++n7) {
                    graphics.drawImage(image, n7 - projectorWidth, 0, this);
                    ++n6;
                    for (int n8 = 0; n8 < delay; ++n8) {}
                }
                break;
            }
            case 5: {
                int n9 = 0;
                for (int n10 = 0; n10 < projectorHeight; ++n10) {
                    graphics.drawImage(image, n9 - projectorHeight, n10 - projectorHeight, this);
                    ++n9;
                    for (int n11 = 0; n11 < delay; ++n11) {}
                }
                break;
            }
            default: {
                for (int n12 = projectorWidth; n12 >= 0; --n12) {
                    graphics.drawImage(image, 0, n12, this);
                    for (int n13 = 0; n13 < delay; ++n13) {}
                }
                break;
            }
        }
        this.getToolkit().sync();
    }
    
    public void clearDisplay() {
        final Graphics graphics = this.getGraphics();
        this.size();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.width, this.height);
    }
}
