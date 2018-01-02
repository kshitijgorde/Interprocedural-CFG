// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Random;

public class FacText
{
    public static final int L_FromLeft = 1024;
    public static final int L_FromRight = 1025;
    public static final int L_CenterHorizontal = 1026;
    public static final int M_FADE_IN_OUT = 1027;
    public static final int M_TEXT_SCROLL = 1028;
    public static final int T_TELE_TYPE = 1029;
    private static final int FIRST_LIGHT = 1024;
    private static final int LAST_LIGHT = 1026;
    private static final int LAST_TYPE = 1029;
    Random random;
    
    public FacText() {
        this.random = null;
    }
    
    public AnimRenderer obtainRandomType(final long n, final Component component, final Graphics graphics, final Font font, final String s, final int n2, final int n3, final Color color, final Color color2) {
        final int generateRandom = this.generateRandom();
        if (this.isLight(generateRandom)) {
            return this.obtainType(n, generateRandom, component, graphics, font, s, n2, n3, color, color2);
        }
        MotionTextParent motionTextParent = null;
        switch (generateRandom) {
            case 1027: {
                motionTextParent = this.obtainText(n, generateRandom, component, graphics, font, s, n2, n3, color, color2);
                break;
            }
            case 1028: {
                motionTextParent = this.obtainText(n, generateRandom, component, graphics, font, s, n2, n3, color, color2);
                break;
            }
            case 1029: {
                motionTextParent = this.obtainText(n, generateRandom, component, graphics, font, s, n2, n3, color, color2);
                break;
            }
            default: {
                motionTextParent = this.obtainText(n, generateRandom, component, graphics, font, s, n2, n3, color, color2);
                break;
            }
        }
        return motionTextParent;
    }
    
    public MotionTextParent obtainText(final long holdTime, final int n, final Component component, final Graphics graphics, final Font font, final String s, final int n2, final int n3, final Color foreground, final Color background) {
        MotionSingleText motionSingleText = null;
        switch (n) {
            case 1027: {
                motionSingleText = new TextFadeInOut(component, graphics, s);
                break;
            }
            case 1028: {
                motionSingleText = new TextScroll(component, graphics, s);
                break;
            }
            case 1029: {
                motionSingleText = new TeleType(component, graphics, s);
                break;
            }
            default: {
                motionSingleText = new TextFadeInOut(component, graphics, s);
                break;
            }
        }
        motionSingleText.setHoldTime(holdTime);
        motionSingleText.setFont(font);
        motionSingleText.setForeground(foreground);
        motionSingleText.setBackground(background);
        motionSingleText.setArea(n2, n3);
        return motionSingleText;
    }
    
    private AnimRenderer obtainType(final long holdTime, final int n, final Component component, final Graphics graphics, final Font font, final String s, final int n2, final int n3, final Color color, final Color color2) {
        final Image genImage = this.genImage(component, s, graphics, font, n2, n3, color, color2);
        final Image generateBackground = this.generateBackground(component, graphics, n2, n3, color2);
        if (genImage == null) {
            System.err.println("image not generated. to show." + s);
        }
        if (generateBackground == null) {
            System.err.println("image not generated. to fade.");
        }
        final RectangleTransLight motion = this.createMotion(n, component, graphics, genImage, generateBackground);
        motion.setHoldTime(holdTime);
        return motion;
    }
    
    private RectangleTransLight createMotion(final int n, final Component component, final Graphics graphics, final Image image, final Image image2) {
        final RectangleTransLight rectangleTransLight = new RectangleTransLight(component, graphics, image, image2);
        switch (n) {
            case 1024: {
                rectangleTransLight.setMode(1);
                break;
            }
            case 1025: {
                rectangleTransLight.setMode(2);
                break;
            }
            case 1026: {
                rectangleTransLight.setMode(7);
                break;
            }
            default: {
                rectangleTransLight.setMode(1);
                break;
            }
        }
        return rectangleTransLight;
    }
    
    private int getRandom(final int n, final int n2) {
        if (this.random == null) {
            this.random = new Random(System.currentTimeMillis());
        }
        return (this.random.nextInt() & Integer.MAX_VALUE) % (n2 - n + 1) + n;
    }
    
    private int generateRandom() {
        return this.getRandom(1024, 1029);
    }
    
    private boolean isLight(final int n) {
        return n >= 1024 && n <= 1026;
    }
    
    private Image genImage(final Component component, final String s, final Graphics graphics, final Font font, final int n, final int n2, final Color color, final Color color2) {
        final Image image = component.createImage(n, n2);
        if (image == null) {
            return image;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(color2);
        graphics2.fillRect(0, 0, n, n2);
        graphics2.setColor(color);
        graphics2.setFont(font);
        final int[] centerStringPosition = MotionSingleText.centerStringPosition(n, n2, font, s);
        graphics2.drawString(s, centerStringPosition[0], centerStringPosition[1]);
        return image;
    }
    
    private Image generateBackground(final Component component, final Graphics graphics, final int n, final int n2, final Color color) {
        final Image image = component.createImage(n, n2);
        if (image == null) {
            return image;
        }
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(color);
        graphics2.fillRect(0, 0, n, n2);
        return image;
    }
}
