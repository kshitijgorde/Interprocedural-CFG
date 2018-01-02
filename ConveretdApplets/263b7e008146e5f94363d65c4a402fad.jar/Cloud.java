import java.awt.image.ImageObserver;
import java.awt.Graphics;
import gamelib.PaletteFactory;
import gamelib.Pixmap;
import gamelib.ActionBuffer;
import java.awt.image.IndexColorModel;
import java.awt.Image;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Cloud extends OffComponent
{
    private Image img;
    private float xpos;
    private Sky sky;
    private int nr;
    private IndexColorModel pal;
    private static int oldsheme;
    private static int[][][] pdata;
    
    static {
        Cloud.pdata = new int[][][] { { { 0, 111, 106, 107 }, { 85, 164, 162, 160 }, { 170, 212, 212, 207 }, { 255, 248, 249, 242 } }, { { 0, 0, 0, 30 }, { 85, 8, 8, 80 }, { 170, 39, 39, 182 }, { 255, 64, 64, 255 } }, { { 0, 81, 75, 89 }, { 85, 144, 143, 150 }, { 170, 205, 206, 207 }, { 255, 251, 255, 250 } }, { { 0, 35, 35, 32 }, { 85, 86, 86, 81 }, { 170, 138, 138, 133 }, { 255, 179, 179, 176 } }, { { 0, 255, 0, 0 }, { 106, 255, 0, 0 }, { 127, 255, 255, 0 }, { 148, 0, 255, 0 }, { 170, 0, 255, 255 }, { 191, 0, 0, 255 }, { 255, 0, 0, 255 } }, { { 0, 90, 90, 255 }, { 85, 90, 90, 255 }, { 170, 210, 210, 255 }, { 255, 255, 255, 255 } }, { { 0, 53, 21, 4 }, { 85, 117, 80, 32 }, { 170, 178, 146, 103 }, { 255, 224, 202, 154 } } };
    }
    
    Cloud(final ActionBuffer actionBuffer, final int nr) {
        super(actionBuffer);
        this.sky = ((Field)super.buffer).sky;
        this.nr = nr;
        this.setPosition(-10000, 10);
        this.setSize((int)(480.0f / (4.0f + this.nr)), (int)(200.0f / (4.0f + this.nr)));
        this.xpos = Pixmap.gen.nextUnsigned(this.sky.getBounds().width);
        final float n = super.bounds.width;
        final float n2 = super.bounds.height;
        final Pixmap pixmap = new Pixmap((int)n, (int)n2);
        pixmap.fillRandom();
        for (float n3 = 0.0f; n3 < n2; ++n3) {
            for (float n4 = 0.0f; n4 < n; ++n4) {
                final float n5 = (n4 - n / 2.0f) / (n / 2.0f);
                final float n6 = n3 - 0.65f * n2;
                float n7;
                if (n3 < 0.65f * n2) {
                    n7 = n6 / (0.65f * n2);
                }
                else {
                    n7 = n6 / (0.35f * n2);
                }
                pixmap.mixPix((int)n4, (int)n3, (256 + 3 * (int)(255.0f - 255.0f * (n5 * n5 + n7 * n7))) / 5);
            }
        }
        for (int n8 = 0; n8 < n2; ++n8) {
            for (int n9 = 0; n9 < n; ++n9) {
                if (pixmap.getPix(n9, n8) < 110) {
                    pixmap.setPix(n9, n8, 0);
                }
            }
        }
        final int sheme = ((Field)super.buffer).sheme;
        if (this.pal == null || sheme != Cloud.oldsheme) {
            this.pal = PaletteFactory.createPalette(Cloud.pdata[sheme], true);
        }
        this.img = pixmap.createImage(this.pal, false);
        this.setVisible(true);
        this.setActive(true);
    }
    
    protected void go() {
        this.xpos += this.sky.wind * (4.0f / (4.0f + this.nr));
        if (this.xpos > this.sky.getBounds().width) {
            this.xpos = -super.bounds.width;
        }
        if (this.xpos < -super.bounds.width) {
            this.xpos = this.sky.getBounds().width;
        }
        this.setPosition((int)this.xpos, super.bounds.y);
    }
    
    protected void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, super.bounds.x, super.bounds.y, super.buffer);
        }
    }
}
