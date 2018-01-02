// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.render;

import java.awt.image.RGBImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import edu.berkeley.guir.prefuse.render.ImageFactory;

public class SharingImageFactory extends ImageFactory
{
    private DesaturateFilter dsat;
    private ImageFactory m_shared;
    private ImageFactory m_bwfactory1;
    private ImageFactory m_bwfactory2;
    
    public SharingImageFactory(final ImageFactory f2, final ImageFactory bwf1, final ImageFactory bwf2) {
        this.dsat = new DesaturateFilter(150);
        this.m_shared = f2;
    }
    
    public Image addImage(final String location, final Image image) {
        final Image img = super.addImage(location, image);
        final ImageProducer prod = new FilteredImageSource(img.getSource(), this.dsat);
        final Image img2 = this.m_shared.addImage(location, image);
        return img;
    }
    
    public class DesaturateFilter extends RGBImageFilter
    {
        static final float CO_RedBandWeight = 0.2125f;
        static final float CO_GreenBandWeight = 0.7154f;
        static final float CO_BlueBandWeight = 0.0721f;
        private int lighten;
        
        public DesaturateFilter(final SharingImageFactory sharingImageFactory) {
            this(sharingImageFactory, 0);
        }
        
        public DesaturateFilter(final int lighten) {
            this.lighten = 0;
            this.lighten = lighten;
            this.canFilterIndexColorModel = false;
        }
        
        public int filterRGB(final int x, final int y, final int rgb) {
            final int a = rgb & 0xFF000000;
            float r = (rgb & 0xFF0000) >> 16;
            float g = (rgb & 0xFF00) >> 8;
            float b = rgb & 0xFF;
            r *= 0.2125f;
            g *= 0.7154f;
            b *= 0.0721f;
            int gray = Math.min((int)(r + g + b) + this.lighten, 255);
            gray &= 0xFF;
            return a | gray << 16 | gray << 8 | gray;
        }
    }
}
