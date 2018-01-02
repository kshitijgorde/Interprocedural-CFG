import java.util.Hashtable;
import java.awt.image.DirectColorModel;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.Canvas;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub32_Sub1 extends Class98_Sub32 implements ImageProducer
{
    static IncomingOpcode aClass58_5883;
    private Canvas aCanvas5884;
    private ImageConsumer anImageConsumer5885;
    static float aFloat5886;
    static OutgoingOpcode aClass171_5887;
    private ColorModel aColorModel5888;
    static Class155[] aClass155Array5889;
    private Image anImage5890;
    
    public static void method1442(final boolean b) {
        try {
            if (!b) {
                method1442(true);
            }
            Class98_Sub32_Sub1.aClass58_5883 = null;
            Class98_Sub32_Sub1.aClass155Array5889 = null;
            Class98_Sub32_Sub1.aClass171_5887 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.L(" + b + ')');
        }
    }
    
    private final synchronized void method1443(final byte b) {
        try {
            if (this.anImageConsumer5885 != null) {
                this.anImageConsumer5885.setPixels(0, 0, super.anInt4105, super.anInt4110, this.aColorModel5888, super.anIntArray4108, 0, super.anInt4105);
                this.anImageConsumer5885.imageComplete(2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.K(" + b + ')');
        }
    }
    
    @Override
    final void method1434(final Graphics graphics, final int n, final byte b, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            this.method1444((byte)(-75), n4, n3, n2, n5);
            if (b != -125) {
                this.method1443((byte)87);
            }
            final Shape clip = graphics.getClip();
            graphics.clipRect(n, n6, n3, n5);
            graphics.drawImage(this.anImage5890, n - n2, -n4 + n6, this.aCanvas5884);
            graphics.setClip(clip);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.A(" + ((graphics != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method1441(final int anInt4110, final int anInt4111, final int n, final Canvas aCanvas5884) {
        try {
            super.anInt4105 = anInt4111;
            super.anInt4110 = anInt4110;
            this.aCanvas5884 = aCanvas5884;
            super.anIntArray4108 = new int[super.anInt4110 * super.anInt4105];
            this.aColorModel5888 = new DirectColorModel(32, 16711680, 65280, 255);
            this.anImage5890 = this.aCanvas5884.createImage(this);
            this.method1443((byte)115);
            if (n != 4095) {
                this.method1443((byte)120);
            }
            this.aCanvas5884.prepareImage(this.anImage5890, this.aCanvas5884);
            this.method1443((byte)115);
            this.aCanvas5884.prepareImage(this.anImage5890, this.aCanvas5884);
            this.method1443((byte)115);
            this.aCanvas5884.prepareImage(this.anImage5890, this.aCanvas5884);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.B(" + anInt4110 + ',' + anInt4111 + ',' + n + ',' + ((aCanvas5884 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void startProduction(final ImageConsumer imageConsumer) {
        try {
            this.addConsumer(imageConsumer);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.startProduction(" + ((imageConsumer != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final synchronized void method1444(final byte b, final int n, final int n2, final int n3, final int n4) {
        try {
            if (b != -75) {
                this.addConsumer(null);
            }
            if (this.anImageConsumer5885 != null) {
                this.anImageConsumer5885.setPixels(n3, n, n2, n4, this.aColorModel5888, super.anIntArray4108, super.anInt4105 * n - -n3, super.anInt4105);
                this.anImageConsumer5885.imageComplete(2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.J(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final synchronized void addConsumer(final ImageConsumer anImageConsumer5885) {
        try {
            (this.anImageConsumer5885 = anImageConsumer5885).setDimensions(super.anInt4105, super.anInt4110);
            anImageConsumer5885.setProperties(null);
            anImageConsumer5885.setColorModel(this.aColorModel5888);
            anImageConsumer5885.setHints(14);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.addConsumer(" + ((anImageConsumer5885 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        try {
            return this.anImageConsumer5885 == imageConsumer;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.isConsumer(" + ((imageConsumer != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        try {
            if (imageConsumer == this.anImageConsumer5885) {
                this.anImageConsumer5885 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fu.removeConsumer(" + ((imageConsumer != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    static {
        Class98_Sub32_Sub1.aClass58_5883 = new IncomingOpcode(3, 5);
        Class98_Sub32_Sub1.aClass171_5887 = new OutgoingOpcode(84, 4);
    }
}
