// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics;

import java.awt.Graphics;
import java.awt.image.DirectColorModel;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.Component;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

public class d extends PixelGrabber
{
    public static boolean b;
    private static boolean e;
    private static boolean c;
    private static ColorModel a;
    private Component d;
    
    public d(final Component d, final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        super(image, n, n2, n3, n4, array, n5, n6);
        this.d = d;
    }
    
    public d(final Component d, final Image image, final int n, final int n2, final int n3, final int n4, final boolean b) {
        super(image, n, n2, n3, n4, b);
        this.d = d;
    }
    
    public d(final Component d, final ImageProducer imageProducer, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        super(imageProducer, n, n2, n3, n4, array, n5, n6);
        this.d = d;
    }
    
    private ColorModel a(final ColorModel colorModel) {
        if (!ru.zhuk.graphics.d.c && colorModel.getPixelSize() >= 24) {
            ru.zhuk.graphics.d.c = true;
            final Color color = new Color(1, 2, 3);
            final Image image = this.d.createImage(1, 1);
            final Graphics graphics = image.getGraphics();
            graphics.setColor(color);
            graphics.drawLine(0, 0, 0, 0);
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, true);
            try {
                if (!pixelGrabber.grabPixels()) {
                    return colorModel;
                }
            }
            catch (Exception ex) {
                return colorModel;
            }
            ru.zhuk.graphics.d.e = (((((int[])pixelGrabber.getPixels())[0] ^ color.getRGB()) & 0xFFFFFF) != 0x0);
            if (ru.zhuk.graphics.d.e) {
                ru.zhuk.graphics.d.a = (ru.zhuk.graphics.d.b ? new DirectColorModel(32, 65280, 65280, 65280) : new DirectColorModel(32, 65280, 16711680, -16777216));
            }
        }
        return (ru.zhuk.graphics.d.e && colorModel.getPixelSize() >= 24) ? ru.zhuk.graphics.d.a : colorModel;
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        super.setPixels(n, n2, n3, n4, this.a(colorModel), array, n5, n6);
    }
}
