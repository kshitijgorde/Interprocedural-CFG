import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.DirectColorModel;
import java.awt.image.DataBufferInt;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Canvas;
import java.awt.Shape;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub32_Sub2 extends Class98_Sub32
{
    private Shape aShape5891;
    private Canvas aCanvas5892;
    private Rectangle aRectangle5893;
    private Image anImage5894;
    
    @Override
    final void method1441(final int anInt4110, final int anInt4111, final int n, final Canvas aCanvas5892) {
        try {
            this.aCanvas5892 = aCanvas5892;
            this.aRectangle5893 = new Rectangle();
            this.anInt4110 = anInt4110;
            this.anInt4105 = anInt4111;
            this.anIntArray4108 = new int[this.anInt4110 * this.anInt4105];
            if (n != 4095) {
                this.method1441(-47, -17, -7, null);
            }
            final DataBufferInt dataBufferInt = new DataBufferInt(this.anIntArray4108, this.anIntArray4108.length);
            final DirectColorModel directColorModel = new DirectColorModel(32, 16711680, 65280, 255);
            this.anImage5894 = new BufferedImage(directColorModel, Raster.createWritableRaster(directColorModel.createCompatibleSampleModel(this.anInt4105, this.anInt4110), dataBufferInt, null), false, new Hashtable<Object, Object>());
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1434(final Graphics graphics, final int x, final byte b, final int n, final int width, final int n2, final int height, final int y) {
        try {
            if (b == -125) {
                this.aShape5891 = graphics.getClip();
                this.aRectangle5893.width = width;
                this.aRectangle5893.x = x;
                this.aRectangle5893.y = y;
                this.aRectangle5893.height = height;
                graphics.setClip(this.aRectangle5893);
                graphics.drawImage(this.anImage5894, x + -n, -n2 + y, this.aCanvas5892);
                graphics.setClip(this.aShape5891);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
