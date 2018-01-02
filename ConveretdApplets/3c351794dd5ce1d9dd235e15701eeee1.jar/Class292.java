import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.lang.reflect.Field;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class292 implements Interface10
{
    private int anInt3332;
    private int anInt3333;
    private Image anImage3334;
    private int anInt3335;
    private Image anImage3336;
    private Color aColor3337;
    private int anInt3338;
    static OutgoingOpcode aClass171_3339;
    private int anInt3340;
    private Image anImage3341;
    private Image anImage3342;
    private int anInt3343;
    private Image anImage3344;
    private Image anImage3345;
    private Image anImage3346;
    private boolean aBoolean3347;
    private Font aFont3348;
    private int anInt3349;
    private int anInt3350;
    private Image anImage3351;
    private Image anImage3352;
    private Image anImage3353;
    private int anInt3354;
    static int[] anIntArray3355;
    static long aLong3356;
    private boolean aBoolean3357;
    private boolean aBoolean3358;
    static int anInt3359;
    private FontMetrics aFontMetrics3360;
    
    private final int method3445(final String s, final int n, final Object o, final Class clazz) throws IllegalAccessException, NoSuchFieldException {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            if (n != 2235) {
                return -79;
            }
            return declaredField.getInt(o);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.P(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + ((o != null) ? "{...}" : "null") + ',' + ((clazz != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3446(final int n) {
        try {
            for (Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2418(32); class98_Sub42 != null; class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2417(88)) {
                if (class98_Sub42.aBoolean4207) {
                    class98_Sub42.method1478(true);
                }
            }
            if (n != 3) {
                Class292.aLong3356 = 44L;
            }
            for (Class98_Sub42 class98_Sub43 = (Class98_Sub42)Class358.aClass148_3032.method2418(32); class98_Sub43 != null; class98_Sub43 = (Class98_Sub42)Class358.aClass148_3032.method2417(103)) {
                if (class98_Sub43.aBoolean4207) {
                    class98_Sub43.method1478(true);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.J(" + n + ')');
        }
    }
    
    @Override
    public final boolean method28(final int n, final long n2) {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.N(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method3447(final int n) {
        try {
            if (n != 2) {
                this.aFontMetrics3360 = null;
            }
            Class283.method3347(Class140.aClass47_3241.method440(-119), Class224_Sub1.aColorArray5036[Class98_Sub9.anInt3855], Class301.aColorArray2508[Class98_Sub9.anInt3855], Class98_Sub5_Sub1.aColorArray5533[Class98_Sub9.anInt3855], Class140.aClass47_3241.method443((byte)(-46)), 90);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.A(" + n + ')');
        }
    }
    
    public static void method3448(final int n) {
        try {
            if (n != 1) {
                method3448(-1);
            }
            Class292.aClass171_3339 = null;
            Class292.anIntArray3355 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.M(" + n + ')');
        }
    }
    
    private final void method3449(final int n) throws IllegalAccessException, NoSuchFieldException {
        try {
            final Class<? extends Applet> class1 = Class76_Sub11.anApplet3799.getClass();
            this.anImage3352 = (Image)this.method3454(class1, "bar", (byte)60);
            this.anImage3334 = (Image)this.method3454(class1, "background", (byte)121);
            this.anImage3345 = (Image)this.method3454(class1, "left", (byte)(-107));
            this.anImage3351 = (Image)this.method3454(class1, "right", (byte)86);
            this.anImage3336 = (Image)this.method3454(class1, "top", (byte)79);
            this.anImage3353 = (Image)this.method3454(class1, "bottom", (byte)(-116));
            this.anImage3344 = (Image)this.method3454(class1, "bodyLeft", (byte)33);
            this.anImage3341 = (Image)this.method3454(class1, "bodyRight", (byte)(-113));
            this.anImage3342 = (Image)this.method3454(class1, "bodyFill", (byte)(-116));
            this.aFont3348 = (Font)this.method3454(class1, "bf", (byte)(-125));
            this.aFontMetrics3360 = (FontMetrics)this.method3454(class1, "bfm", (byte)49);
            if (n == 2) {
                this.aColor3337 = (Color)this.method3454(class1, "colourtext", (byte)(-125));
                final Object method3454 = this.method3454(class1, "lb", (byte)(-116));
                final Class<?> class2 = method3454.getClass();
                this.aBoolean3357 = this.method3452(class2, (byte)103, "xMiddle", method3454);
                this.aBoolean3347 = this.method3452(class2, (byte)97, "yMiddle", method3454);
                this.anInt3350 = this.method3445("xOffset", 2235, method3454, class2);
                this.anInt3340 = this.method3445("yOffset", 2235, method3454, class2);
                this.anInt3333 = this.method3445("width", 2235, method3454, class2);
                this.anInt3335 = this.method3445("height", 2235, method3454, class2);
                this.anInt3332 = this.method3445("boxXOffset", n + 2233, method3454, class2);
                this.anInt3354 = this.method3445("boxYOffset", 2235, method3454, class2);
                this.anInt3343 = this.method3445("boxWidth", 2235, method3454, class2);
                this.anInt3349 = this.method3445("textYOffset", n ^ 0x8B9, method3454, class2);
                this.anInt3338 = this.method3445("offsetPerTenCycles", n ^ 0x8B9, method3454, class2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.C(" + n + ')');
        }
    }
    
    private final int method3450(final int n, final int n2) {
        try {
            if (n2 != 0) {
                return 49;
            }
            if (this.aBoolean3347) {
                return (-n + Class98_Sub25.anInt4024) / 2;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.O(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final void method24(final int n) {
        try {
            Class98_Sub40.method1470(3796);
            if (n != -26363) {
                method3448(65);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.K(" + n + ')');
        }
    }
    
    static final int method3451(final int n, final int n2, final boolean b, final int n3, int n4, int n5, int n6) {
        try {
            n4 &= 0x3;
            if (~(n2 & 0x1) == 0xFFFFFFFE) {
                final int n7 = n5;
                n5 = n6;
                n6 = n7;
            }
            if (b) {
                Class292.anInt3359 = -70;
            }
            if (~n4 == -1) {
                return n;
            }
            if (n4 == 1) {
                return n3;
            }
            if (~n4 == 0xFFFFFFFD) {
                return -n + 7 - n5 + 1;
            }
            return -n6 + 1 + -n3 + 7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.L(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    private final boolean method3452(final Class clazz, final byte b, final String s, final Object o) throws IllegalAccessException, NoSuchFieldException {
        try {
            return clazz.getDeclaredField(s).getBoolean(o);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.E(" + ((clazz != null) ? "{...}" : "null") + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ',' + ((o != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method27(final int n) {
        try {
            if (n != -31295) {
                this.method3453(-13, 77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.H(" + n + ')');
        }
    }
    
    @Override
    public final int method26(final int n) {
        try {
            if (n != -794) {
                this.anImage3345 = null;
            }
            return 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.F(" + n + ')');
        }
    }
    
    @Override
    public final int method25(final int n) {
        try {
            if (n != -24591) {
                method3451(-96, 3, true, -103, -108, -61, -101);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.D(" + n + ')');
        }
    }
    
    private final int method3453(final int n, final int n2) {
        try {
            if (this.aBoolean3357) {
                return (Class39_Sub1.anInt3593 + -n) / 2;
            }
            if (n2 != 7) {
                return -28;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.B(" + n + ',' + n2 + ')');
        }
    }
    
    private final Object method3454(final Class clazz, final String s, final byte b) throws IllegalAccessException, NoSuchFieldException {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            final Object value = declaredField.get(Class76_Sub11.anApplet3799);
            declaredField.set(Class76_Sub11.anApplet3799, null);
            return value;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.G(" + ((clazz != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    public final void method23(final int n, final boolean b) {
        try {
            if (!this.aBoolean3358) {
                if (Class76_Sub11.anApplet3799 != null) {
                    if (this.aFont3348 == null) {
                        try {
                            this.method3449(n ^ 0x7DD0);
                        }
                        catch (Exception ex2) {
                            this.aBoolean3358 = true;
                        }
                    }
                }
                else {
                    this.aBoolean3358 = true;
                }
            }
            if (this.aBoolean3358) {
                this.method3447(2);
            }
            else {
                if (n != 32210) {
                    this.method26(51);
                }
                final Graphics graphics = Class42_Sub3.aCanvas5361.getGraphics();
                if (graphics != null) {
                    try {
                        final int method440 = Class140.aClass47_3241.method440(-127);
                        final String method441 = Class140.aClass47_3241.method443((byte)(-46));
                        if (Class3.anImage74 == null) {
                            Class3.anImage74 = Class42_Sub3.aCanvas5361.createImage(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                        }
                        final Graphics graphics2 = Class3.anImage74.getGraphics();
                        graphics2.clearRect(0, 0, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                        final int width = this.anImage3344.getWidth(null);
                        final int width2 = this.anImage3341.getWidth(null);
                        final int width3 = this.anImage3342.getWidth(null);
                        final int height = this.anImage3344.getHeight(null);
                        final int height2 = this.anImage3341.getHeight(null);
                        final int height3 = this.anImage3342.getHeight(null);
                        graphics2.drawImage(this.anImage3344, this.method3453(width, 7) - (-this.anInt3332 - -(this.anInt3343 / 2)), this.method3450(height, 0) + this.anInt3354, null);
                        final int n2 = width + (this.anInt3332 + -(this.anInt3343 / 2));
                        for (int n3 = this.anInt3332 + this.anInt3343 / 2, n4 = n2; ~n3 <= ~n4; n4 += width3) {
                            graphics2.drawImage(this.anImage3342, this.method3453(width, 7) + this.anInt3332 - -n4, this.method3450(height3, 0) + this.anInt3354, null);
                        }
                        graphics2.drawImage(this.anImage3341, this.method3453(width2, 7) - (-this.anInt3332 + -(this.anInt3343 / 2)), this.method3450(height2, 0) + this.anInt3354, null);
                        final int width4 = this.anImage3345.getWidth(null);
                        final int height4 = this.anImage3345.getHeight(null);
                        final int width5 = this.anImage3351.getWidth(null);
                        final int height5 = this.anImage3351.getHeight(null);
                        final int height6 = this.anImage3353.getHeight(null);
                        final int width6 = this.anImage3336.getWidth(null);
                        final int height7 = this.anImage3336.getHeight(null);
                        final int width7 = this.anImage3353.getWidth(null);
                        final int width8 = this.anImage3352.getWidth(null);
                        final int width9 = this.anImage3334.getWidth(null);
                        final int n5 = this.method3453(this.anInt3333, n ^ 0x7DD5) + this.anInt3350;
                        final int n6 = this.method3450(this.anInt3335, 0) + this.anInt3340;
                        graphics2.drawImage(this.anImage3345, n5, n6 - -((this.anInt3335 - height4) / 2), null);
                        graphics2.drawImage(this.anImage3351, -width5 + n5 + this.anInt3333, (-height5 + this.anInt3335) / 2 + n6, null);
                        if (this.anImage3346 == null) {
                            this.anImage3346 = Class42_Sub3.aCanvas5361.createImage(-width5 + (this.anInt3333 - width4), this.anInt3335);
                        }
                        final Graphics graphics3 = this.anImage3346.getGraphics();
                        for (int i = 0; i < -width5 + (-width4 + this.anInt3333); i += width6) {
                            graphics3.drawImage(this.anImage3336, i, 0, null);
                        }
                        for (int n7 = 0; ~n7 > ~(-width5 + (-width4 + this.anInt3333)); n7 += width7) {
                            graphics3.drawImage(this.anImage3353, n7, this.anInt3335 + -height6, null);
                        }
                        final int n8 = method440 * (-width5 + (-width4 + this.anInt3333)) / 100;
                        if (n8 > 0) {
                            final Image image = Class42_Sub3.aCanvas5361.createImage(n8, -height6 + (-height7 + this.anInt3335));
                            final int width10 = image.getWidth(null);
                            final Graphics graphics4 = image.getGraphics();
                            for (int n9 = this.anInt3338 * RuntimeException_Sub1.method4012(true) / 10 % width8 + -width8; ~n9 > ~width10; n9 += width8) {
                                graphics4.drawImage(this.anImage3352, n9, 0, null);
                            }
                            graphics3.drawImage(image, 0, height7, null);
                        }
                        final int n10 = n8;
                        final int n11 = -n8 + -width5 + this.anInt3333 - width4;
                        if (~n11 < -1) {
                            final Image image2 = Class42_Sub3.aCanvas5361.createImage(n11, -height6 + (-height7 + this.anInt3335));
                            final int width11 = image2.getWidth(null);
                            final Graphics graphics5 = image2.getGraphics();
                            for (int j = 0; j < width11; j += width9) {
                                graphics5.drawImage(this.anImage3334, j, 0, null);
                            }
                            graphics3.drawImage(image2, n10, height7, null);
                        }
                        graphics2.drawImage(this.anImage3346, width4 + n5, n6, null);
                        graphics2.setFont(this.aFont3348);
                        graphics2.setColor(this.aColor3337);
                        graphics2.drawString(method441, (this.anInt3333 - this.aFontMetrics3360.stringWidth(method441)) / 2 + n5, this.anInt3349 + n6 + this.anInt3335 / 2 + 4);
                        graphics.drawImage(Class3.anImage74, 0, 0, null);
                    }
                    catch (Exception ex3) {
                        this.aBoolean3358 = true;
                    }
                }
                else {
                    Class42_Sub3.aCanvas5361.repaint();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sa.I(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class292.aClass171_3339 = new OutgoingOpcode(75, 0);
        Class292.aLong3356 = -1L;
        Class292.anIntArray3355 = new int[14];
        Class292.anInt3359 = 64;
    }
}
