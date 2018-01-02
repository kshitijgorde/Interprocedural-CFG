import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class picture
{
    String name;
    Image pic;
    Image[] ppic;
    int height;
    int width;
    imgslide a;
    
    public picture(final imgslide a, final String name) {
        int n = 0;
        this.name = name;
        this.a = a;
        if (imgslide.isapplet) {
            this.pic = a.getImage(a.getCodeBase(), name);
        }
        else {
            this.pic = Toolkit.getDefaultToolkit().getImage(name);
        }
        final int gridx = a.gridx;
        final int gridy = a.gridy;
        do {
            ++n;
            this.height = this.pic.getHeight(a);
            this.width = this.pic.getWidth(a);
        } while (this.height < 0 || this.width < 0);
        this.ppic = new Image[gridy * gridx];
        for (int i = 0; i < gridy * gridx; ++i) {
            final int n2 = this.width * (i % gridx) / gridx;
            final int n3 = this.width * (i % gridx + 1) / gridx;
            final int n4 = this.height * (i / gridx) / gridy;
            this.ppic[i] = a.createImage(new FilteredImageSource(this.pic.getSource(), new CropImageFilter(n2, n4, n3 - n2, this.height * (i / gridx + 1) / gridy - n4)));
        }
    }
    
    String shortname() {
        final int max = Math.max(this.name.lastIndexOf(92), this.name.lastIndexOf(47));
        if (max >= 0) {
            return this.name.substring(max + 1);
        }
        return this.name;
    }
}
