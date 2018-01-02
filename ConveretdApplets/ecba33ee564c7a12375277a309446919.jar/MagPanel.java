import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MagPanel extends Panel
{
    int[] fMag;
    int[] fPhase;
    boolean setup;
    int fSigLength;
    int fAmplitude;
    int fCoefs;
    Image fImage;
    Graphics fGraphics;
    
    public MagPanel() {
        this.setup = false;
        this.setBackground(Color.white);
    }
    
    public Dimension minimumSize() {
        return new Dimension(150, 90);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.setup) {
            this.fImage = this.createImage(this.getSize().width, this.getSize().height);
            this.fGraphics = this.fImage.getGraphics();
            this.setup = true;
        }
        this.fGraphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        final int n = 5;
        final int n2 = 5;
        final int n3 = this.getSize().width / 2 + 5;
        final int n4 = n3 - 5;
        this.fGraphics.setColor(Color.lightGray);
        this.fGraphics.drawLine(n2, 0, n2, this.getSize().height);
        this.fGraphics.drawLine(0, this.getSize().height - 4, n4, this.getSize().height - 4);
        this.fGraphics.drawLine(n3, 0, n3, this.getSize().height);
        this.fGraphics.drawLine(n3, this.getSize().height - 4, this.getSize().width, this.getSize().height - 4);
        this.fGraphics.drawLine(n3 - 2, 4, n3 + 2, 4);
        this.fGraphics.setColor(Color.black);
        if (this.fMag != null) {
            for (int i = 0; i <= this.fCoefs; ++i) {
                if (n2 + n * i <= n4) {
                    this.fGraphics.setColor(Color.red);
                    this.fGraphics.drawLine(n2 + n * i, this.getSize().height - 4, n2 + n * i, this.getSize().height - 4 - this.fMag[i]);
                    this.fGraphics.drawLine(n2 + n * i + 1, this.getSize().height - 4, n2 + n * i + 1, this.getSize().height - 4 - this.fMag[i]);
                }
                this.fGraphics.setColor(Color.blue);
                this.fGraphics.drawLine(n3 + n * i, this.getSize().height - 4, n3 + n * i, this.getSize().height - 4 - this.fPhase[i]);
                this.fGraphics.drawLine(n3 + n * i + 1, this.getSize().height - 4, n3 + n * i + 1, this.getSize().height - 4 - this.fPhase[i]);
            }
        }
        this.fGraphics.setColor(Color.black);
        this.fGraphics.drawString("Magnitude spectrum", n2 + 5, 13);
        this.fGraphics.drawString("Phase spectrum", n3 + 5, 13);
        graphics.drawImage(this.fImage, 0, 0, this);
    }
    
    public void set(final int[] fMag, final int[] fPhase, final int fCoefs) {
        this.fMag = fMag;
        this.fPhase = fPhase;
        this.fCoefs = fCoefs;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
