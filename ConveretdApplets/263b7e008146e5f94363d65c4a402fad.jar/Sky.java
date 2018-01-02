import java.awt.image.ImageObserver;
import java.awt.Graphics;
import gamelib.PaletteFactory;
import gamelib.Pixmap;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import java.awt.Image;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Sky extends OffComponent
{
    float wind;
    private Image img;
    private static int[][][] pdata;
    
    static {
        Sky.pdata = new int[][][] { { { 0, 128, 133, 231 }, { 63, 162, 164, 236 }, { 127, 198, 199, 242 }, { 191, 231, 231, 249 }, { 255, 255, 255, 255 } }, { new int[4], { 63, 0, 78, 0 }, { 127, 0, 140, 0 }, { 191, 0, 192, 0 }, { 255, 0, 228, 0 } }, { { 0, 56, 71, 161 }, { 63, 115, 111, 183 }, { 127, 171, 165, 211 }, { 191, 220, 215, 236 }, { 255, 255, 253, 255 } }, { { 0, 0, 0, 5 }, { 63, 0, 0, 38 }, { 127, 0, 0, 65 }, { 191, 0, 0, 88 }, { 254, 0, 0, 103 }, { 255, 255, 255, 255 } }, { { 0, 0, 0, 255 }, { 63, 0, 255, 255 }, { 127, 0, 255, 0 }, { 191, 255, 255, 0 }, { 255, 255, 0, 0 } }, { { 0, 90, 90, 255 }, { 127, 210, 210, 255 }, { 255, 255, 255, 255 } }, { { 0, 92, 0, 0 }, { 63, 166, 25, 0 }, { 127, 255, 163, 0 }, { 191, 255, 255, 0 }, { 255, 255, 255, 0 } } };
    }
    
    Sky(final ActionBuffer actionBuffer, final Rectangle rectangle) {
        super(actionBuffer, rectangle);
        this.wind = TankApplet.gen.nextFloat() * 4.0f - 2.0f;
        final int width = super.bounds.width;
        final int height = super.bounds.height;
        final Pixmap pixmap = new Pixmap(width, height);
        final float n = 254.0f / height;
        for (int i = 0; i < height; ++i) {
            final int n2 = (int)(i * n);
            for (int j = 0; j < width; ++j) {
                pixmap.setPix(j, i, n2);
            }
        }
        if (((Field)super.buffer).sheme == 3) {
            for (int k = 0; k < 256; ++k) {
                pixmap.setPix(Pixmap.gen.nextUnsigned(width - 1), Pixmap.gen.nextUnsigned(height - 1), 255);
            }
        }
        this.img = pixmap.createImage(PaletteFactory.createPalette(Sky.pdata[((Field)super.buffer).sheme], false), false);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, super.bounds.x, super.bounds.y, super.buffer);
        }
    }
}
