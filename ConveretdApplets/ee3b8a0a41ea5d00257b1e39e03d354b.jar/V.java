import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class V extends Canvas
{
    private boolean createImage;
    private int dispose;
    private Image drawImage;
    private Image flush;
    private Image getGraphics;
    private Image getHeight;
    private int getSize;
    private int getWidth;
    private int paint;
    private int repaint;
    private int setSize;
    private int I;
    private ztmPlayer Z;
    private Image C;
    private Graphics B;
    
    V(final Image drawImage, final Image flush, final Image getGraphics, final Image getHeight, final ztmPlayer z, final int getSize, final int getWidth) {
        this.repaint = 2;
        this.Z = z;
        this.drawImage = drawImage;
        this.flush = flush;
        this.getGraphics = getGraphics;
        this.getHeight = getHeight;
        this.repaint = this.getGraphics.getWidth(null) >> 1;
        this.setSize = this.drawImage.getWidth(null);
        this.I = this.drawImage.getHeight(null);
        this.C = this.Z.createImage(this.setSize, this.I);
        this.B = this.C.getGraphics();
        if (getSize >= 0 && getWidth > 0) {
            this.getSize = getSize;
            this.getWidth = getWidth;
        }
        else {
            this.getSize = 2 + this.repaint;
            this.getWidth = this.setSize - this.getSize - this.getSize;
        }
        this.paint = this.getWidth - this.getSize;
        super.setSize(this.getSize());
    }
    
    final int I() {
        return this.paint;
    }
    
    final int Z() {
        return this.getSize;
    }
    
    final void I(float n) {
        if (1.0f < n) {
            n = 1.0f;
        }
        else if (0.0f > n) {
            n = 0.0f;
        }
        this.dispose = (int)(n * this.paint);
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        this.B.drawImage(this.drawImage, 0, 0, null);
        if (this.createImage && this.dispose > 0) {
            if (this.dispose >= this.getWidth) {
                this.B.drawImage(this.flush, 0, 0, null);
            }
            else {
                final Image image = this.createImage(this.getSize + this.dispose, this.I);
                if (null != image) {
                    final Graphics graphics2 = image.getGraphics();
                    graphics2.drawImage(this.flush, 0, 0, null);
                    this.B.drawImage(image, 0, 0, null);
                    graphics2.dispose();
                    image.flush();
                }
            }
        }
        if (this.createImage) {
            this.B.drawImage((this.dispose != 0) ? this.getHeight : this.getGraphics, this.getSize - this.repaint + this.dispose, 0, this);
        }
        this.Z.i.I(graphics);
        graphics.drawImage(this.C, 0, 0, null);
    }
    
    public final void setEnabled(final boolean createImage) {
        if (!(this.createImage = createImage)) {
            this.dispose = 0;
        }
        this.repaint();
    }
    
    public final Dimension getSize() {
        return new Dimension(this.setSize, this.I);
    }
}
