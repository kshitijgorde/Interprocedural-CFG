import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletAnimFrame
{
    public int time;
    private int \u00c0;
    private int \u00c1;
    private AnimFramePoint[] \u00c2;
    private Image \u00c3;
    
    AppletAnimFrame(final AppletAnimation appletAnimation, final int time) {
        this.\u00c0 = appletAnimation.width;
        this.\u00c1 = appletAnimation.height;
        this.\u00c2 = new AnimFramePoint[this.\u00c0 * this.\u00c1];
        this.time = time;
        for (int i = 0; i < this.\u00c0; ++i) {
            for (int j = 0; j < this.\u00c1; ++j) {
                final int calcZvalue = appletAnimation.calcZvalue(i, j, time);
                final int[] rotatePoints = appletAnimation.rotator.rotatePoints(new int[] { i, j, calcZvalue });
                final int n = j * this.\u00c0 + i;
                this.\u00c2[n] = new AnimFramePoint(rotatePoints[0], rotatePoints[1] - appletAnimation.height / 2, rotatePoints[2], appletAnimation.originalPixels[n]);
                final int color = this.\u00c2[n].color;
                int n2 = color >> 16 & 0xFF;
                int n3 = color >> 8 & 0xFF;
                int n4 = color & 0xFF;
                final double n5 = (calcZvalue + 50) / 100.0;
                final double n6 = n5 + n5 - 1.0;
                if (n6 > 0.0) {
                    n2 += (int)((255 - n2) * n6);
                    n3 += (int)((255 - n3) * n6);
                    n4 += (int)((255 - n4) * n6);
                }
                else if (n6 < 0.0) {
                    n2 -= (int)(n2 * n6 * -1.0);
                    n3 -= (int)(n3 * n6 * -1.0);
                    n4 -= (int)(n4 * n6 * -1.0);
                }
                this.\u00c2[n].color = new Color(n2, n3, n4).getRGB();
            }
        }
        final int rgb = appletAnimation.background.getRGB();
        this.\u00c0 += 4;
        this.\u00c1 += 4;
        final int[] array = new int[this.\u00c0 * this.\u00c1];
        for (int k = 0; k < array.length; ++k) {
            array[k] = rgb;
        }
        for (int l = 0; l < this.\u00c2.length; ++l) {
            final int pointX = this.\u00c2[l].pointX();
            final int n7 = this.\u00c2[l].pointY() + appletAnimation.height / 2 + 4;
            if (pointX < this.\u00c0 - 1 && n7 < this.\u00c1 - 1 && pointX > 0 && n7 > 0) {
                array[n7 * this.\u00c0 + pointX] = this.\u00c2[l].color;
                array[n7 * this.\u00c0 + pointX + 1] = this.\u00c2[l].color;
                array[(n7 + 1) * this.\u00c0 + pointX] = this.\u00c2[l].color;
                if (array[n7 * this.\u00c0 + pointX - 1] == rgb) {
                    array[n7 * this.\u00c0 + pointX - 1] = this.\u00c2[l].color;
                }
                if (array[(n7 - 1) * this.\u00c0 + pointX] == rgb) {
                    array[(n7 - 1) * this.\u00c0 + pointX] = this.\u00c2[l].color;
                }
            }
        }
        this.\u00c3 = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.\u00c0, this.\u00c1, ColorModel.getRGBdefault(), array, 0, this.\u00c0));
    }
    
    public Image getFrame() {
        return this.\u00c3;
    }
}
