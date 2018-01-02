import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class SquareMorpher2 extends ImageMorpher
{
    int nb;
    int cote;
    int xcote;
    int ycote;
    int but;
    int x;
    int y;
    
    public void init(final Dimension dimension) {
        this.but = this.cote * this.cote;
        this.xcote = dimension.width / this.cote + 1;
        this.ycote = dimension.height / this.cote + 1;
        this.x = 0;
        this.y = 0;
    }
    
    boolean tipMorpher(final Graphics graphics, final Image image, final Dimension dimension) {
        graphics.drawImage(image, this.x * this.xcote, this.y * this.ycote, (this.x + 1) * this.xcote, (this.y + 1) * this.ycote, this.x * this.xcote, this.y * this.ycote, (this.x + 1) * this.xcote, (this.y + 1) * this.ycote, null);
        --this.but;
        if (this.but == 0) {}
        ++this.x;
        if (this.x > this.cote) {
            this.x = 0;
            ++this.y;
        }
        return this.y >= dimension.height;
    }
    
    public SquareMorpher2() {
        this.cote = 10;
    }
}
