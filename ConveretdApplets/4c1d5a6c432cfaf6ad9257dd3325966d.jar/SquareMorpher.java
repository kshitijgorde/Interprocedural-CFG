import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.BitSet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SquareMorpher extends ImageMorpher
{
    int nb;
    BitSet b;
    int cote;
    int xcote;
    int ycote;
    int but;
    
    public void init(final Dimension dimension) {
        this.but = this.cote * this.cote;
        this.b = new BitSet(this.but);
        for (int i = 0; i < this.but; ++i) {
            this.b.clear(i);
        }
        this.xcote = dimension.width / this.cote + 1;
        this.ycote = dimension.height / this.cote + 1;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        boolean b = false;
        boolean b2 = true;
        while (b2) {
            final int n = (int)(Math.random() * this.cote);
            final int n2 = (int)(Math.random() * this.cote);
            if (!this.b.get(n + n2 * this.cote)) {
                b2 = false;
                this.b.set(n + n2 * this.cote);
                graphics.drawImage(image, n * this.xcote, n2 * this.ycote, (n + 1) * this.xcote, (n2 + 1) * this.ycote, n * this.xcote, n2 * this.ycote, (n + 1) * this.xcote, (n2 + 1) * this.ycote, null);
                --this.but;
                if (this.but != 0) {
                    continue;
                }
                b = true;
            }
        }
        return b;
    }
    
    public SquareMorpher() {
        this.cote = 10;
    }
}
