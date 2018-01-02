import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class PropellerAirplane extends Airplane
{
    public static Image[] airplane_image;
    
    public static void MakeImages(final Image image, final Component component) {
        for (int i = 0; i < 8; ++i) {
            component.prepareImage(PropellerAirplane.airplane_image[i] = component.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(0, 31 * i, 31, 31))), component);
        }
    }
    
    public PropellerAirplane() {
        super.w = 31;
        super.h = 31;
    }
    
    public void Draw(final Graphics graphics, final ImageObserver imageObserver) {
        graphics.drawImage(PropellerAirplane.airplane_image[super.direction], super.x, super.y, imageObserver);
    }
    
    static {
        PropellerAirplane.airplane_image = new Image[8];
    }
}
