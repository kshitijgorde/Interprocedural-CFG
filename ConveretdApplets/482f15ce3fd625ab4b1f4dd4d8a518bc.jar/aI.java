import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aI
{
    private Image[] a;
    private MemoryImageSource[] a;
    public int[][] a;
    public boolean[] a;
    
    public aI() {
        this.a = new int[64][160];
        this.a = new MemoryImageSource[64];
        this.a = new Image[64];
        for (int i = 0; i < 64; ++i) {
            (this.a[i] = new MemoryImageSource(8, 12, this.a[i], 0, 8)).setAnimated(true);
            this.a[i] = Toolkit.getDefaultToolkit().createImage(this.a[i]);
        }
        this.a = new boolean[64];
    }
    
    public final void a(final Graphics graphics) {
        for (int i = 0; i < 64; ++i) {
            final int n = i % 8;
            final int n2 = i / 8;
            if (!this.a[i]) {
                graphics.setColor(Color.gray);
                for (int j = 0; j < this.a[i].length; ++j) {
                    this.a[i][j] = (0x40000000 | (this.a[i][j] & 0xFFFFFF));
                }
            }
            else {
                graphics.setColor(Color.white);
            }
            final int n3 = n * 28 + 16;
            final int n4 = n2 * 28 + 8;
            this.a[i].newPixels();
            graphics.drawImage(this.a[i], n3 + 1, n4 + 1, 24, 36, null);
            graphics.drawRect(n3, n4, 25, 25);
        }
    }
}
