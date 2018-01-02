// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Random;

public class ImageTransition
{
    public static final int Random = 0;
    public static final int FromLeft = 1;
    public static final int FromRight = 2;
    public static final int FromTop = 3;
    public static final int FromBottom = 4;
    public static final int CenterTwoWay = 5;
    public static final int CenterVertical = 6;
    public static final int CenterHorizontal = 7;
    public static final int UpperLeftTwoWay = 8;
    public static final int BottomLeftTwoWay = 9;
    public static final int UpperRightTwoWay = 10;
    public static final int BottomRightTwoWay = 11;
    public static final int FadeInOut = 12;
    public static final int ZoomOutFade = 13;
    public static final int WindBlow = 14;
    public static final int ExplosionFade = 15;
    public static final int FlashFade = 16;
    public static final int SpiralFade = 17;
    public static final int DissolveFade = 18;
    public static final int TearAway = 19;
    public static final int PressDown = 20;
    public static final int PressUp = 21;
    public static final int L_FromLeft = 1024;
    public static final int L_FromRight = 1025;
    public static final int L_FromTop = 1026;
    public static final int L_FromBottom = 1027;
    public static final int L_CenterTwoWay = 1028;
    public static final int L_CenterVertical = 1029;
    public static final int L_CenterHorizontal = 1030;
    public static final int L_UpperLeftTwoWay = 1031;
    public static final int L_BottomLeftTwoWay = 1032;
    public static final int L_UpperRightTwoWay = 1033;
    public static final int L_BottomRightTwoWay = 1034;
    public static final int L_ColumnTurn = 1035;
    public static final int L_RowTurn = 1036;
    public static final int L_CenterTurnCurtain = 1037;
    public static final int L_MergeMiddle = 1038;
    public static final int L_MergeUpDownMiddle = 1039;
    public static final int L_MergeQuarterCenter = 1040;
    public static final int L_MergeThree = 1041;
    public static final int L_SplitMiddle = 1042;
    public static final int L_SplitUpDownMiddle = 1043;
    public static final int L_SplitQuarterGone = 1044;
    public static final int L_SplitThree = 1045;
    private static final int HEAVY_STYLE = 21;
    private static final int FIRST_LIGHT = 1024;
    private static final int LAST_LIGHT = 1045;
    private static final int SizeThreshold = 62500;
    Random random;
    private boolean styleSatisfied;
    
    public ImageTransition() {
        this.random = null;
        this.styleSatisfied = true;
    }
    
    public AnimRenderer obtainType(final int n, final Component component, final Graphics graphics, final Image image, final Image image2) {
        return this.obtainOneType(n, component, graphics, image, image2);
    }
    
    public AnimRenderer obtainType(final int n, final Component component, final Graphics graphics, final Image image, final Image image2, final int n2, final int n3) {
        final ImageMotionTrans obtainOneType = this.obtainOneType(n, component, graphics, image, image2);
        obtainOneType.setPosition(n2, n3);
        return obtainOneType;
    }
    
    public boolean IsSatisfied() {
        return this.styleSatisfied;
    }
    
    private ImageMotionTrans obtainOneType(final int n, final Component component, final Graphics graphics, final Image image, final Image image2) {
        int n2 = n;
        if (n2 == 0) {
            n2 = this.generateRandom();
        }
        this.validateRequest(image, n2);
        if (!this.styleSatisfied) {
            n2 = this.getRandom(1024, 1045);
        }
        return this.createMotion(n2, component, graphics, image, image2);
    }
    
    private void validateRequest(final Image image, final int n) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        this.styleSatisfied = true;
        if (width * height > 62500 && n <= 21) {
            this.styleSatisfied = false;
            System.out.println("Requested animation is not satisfied because");
            System.out.println("the image is too large. A random animation is");
            System.out.println("used instead.");
        }
    }
    
    private ImageMotionTrans createMotion(final int n, final Component component, final Graphics graphics, final Image image, final Image image2) {
        RectangleTransLight rectangleTransLight2 = null;
        switch (n) {
            case 1024: {
                final RectangleTransLight rectangleTransLight = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight.setMode(1);
                rectangleTransLight2 = rectangleTransLight;
                break;
            }
            case 1025: {
                final RectangleTransLight rectangleTransLight3 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight3.setMode(2);
                rectangleTransLight2 = rectangleTransLight3;
                break;
            }
            case 1026: {
                final RectangleTransLight rectangleTransLight4 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight4.setMode(3);
                rectangleTransLight2 = rectangleTransLight4;
                break;
            }
            case 1027: {
                final RectangleTransLight rectangleTransLight5 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight5.setMode(4);
                rectangleTransLight2 = rectangleTransLight5;
                break;
            }
            case 1028: {
                final RectangleTransLight rectangleTransLight6 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight6.setMode(5);
                rectangleTransLight2 = rectangleTransLight6;
                break;
            }
            case 1029: {
                final RectangleTransLight rectangleTransLight7 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight7.setMode(6);
                rectangleTransLight2 = rectangleTransLight7;
                break;
            }
            case 1030: {
                final RectangleTransLight rectangleTransLight8 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight8.setMode(7);
                rectangleTransLight2 = rectangleTransLight8;
                break;
            }
            case 1031: {
                final RectangleTransLight rectangleTransLight9 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight9.setMode(8);
                rectangleTransLight2 = rectangleTransLight9;
                break;
            }
            case 1032: {
                final RectangleTransLight rectangleTransLight10 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight10.setMode(9);
                rectangleTransLight2 = rectangleTransLight10;
                break;
            }
            case 1033: {
                final RectangleTransLight rectangleTransLight11 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight11.setMode(10);
                rectangleTransLight2 = rectangleTransLight11;
                break;
            }
            case 1034: {
                final RectangleTransLight rectangleTransLight12 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight12.setMode(11);
                rectangleTransLight2 = rectangleTransLight12;
                break;
            }
            default: {
                final RectangleTransLight rectangleTransLight13 = new RectangleTransLight(component, graphics, image, image2);
                rectangleTransLight13.setMode(11);
                rectangleTransLight2 = rectangleTransLight13;
                break;
            }
        }
        return rectangleTransLight2;
    }
    
    private int getRandom(final int n, final int n2) {
        if (this.random == null) {
            this.random = new Random();
        }
        return (this.random.nextInt() & Integer.MAX_VALUE) % (n2 - n + 1) + n;
    }
    
    private int generateRandom() {
        int random = this.getRandom(1, 43);
        if (random > 21) {
            random = random - 21 - 1 + 1024;
        }
        return random;
    }
}
