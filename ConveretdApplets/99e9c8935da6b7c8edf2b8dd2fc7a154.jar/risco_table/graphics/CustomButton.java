// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

public class CustomButton
{
    static final int WIDTH = 49;
    static final int HEIGHT = 19;
    private static StringFormatter buttonFormatter;
    private Image buttonNormal;
    private Image buttonPressed;
    
    static {
        CustomButton.buttonFormatter = new StringFormatter(4, 4);
    }
    
    private Image createButton(final Component c, final String sLabel, final Color oButtonColor) {
        final Image buttonImage = c.createImage(49, 19);
        final Graphics g = buttonImage.getGraphics();
        g.setColor(oButtonColor);
        g.fillRect(0, 0, 49, 19);
        g.setColor(Color.darkGray);
        g.drawRect(0, 0, 48, 18);
        g.setColor(Color.black);
        CustomButton.buttonFormatter.formatString(g, g.getFontMetrics(new Font("SansSerif", 0, 10)), sLabel, 0, 0, 49, 19);
        return buttonImage;
    }
    
    public CustomButton(final Component c, final Image buttonNormalImage, final Image buttonPressedImage, final String sLabel) {
        if (buttonNormalImage != null) {
            this.buttonNormal = buttonNormalImage;
        }
        else {
            final float[] lightBlue = new float[3];
            Color.RGBtoHSB(173, 186, 214, lightBlue);
            this.buttonNormal = this.createButton(c, sLabel, Color.getHSBColor(lightBlue[0], lightBlue[1], lightBlue[2]));
        }
        if (buttonPressedImage != null) {
            this.buttonPressed = buttonPressedImage;
        }
        else {
            final float[] medGreen = new float[3];
            Color.RGBtoHSB(150, 191, 111, medGreen);
            this.buttonPressed = this.createButton(c, sLabel, Color.getHSBColor(medGreen[0], medGreen[1], medGreen[2]));
        }
    }
    
    public void paint(final Graphics g, final boolean state, final int x, final int y, final int width, final int height) {
        final int left = x + (width - 49) / 2;
        final int top = y + (height - 19) / 2;
        if (state) {
            g.drawImage(this.buttonPressed, left, top, null);
        }
        else {
            g.drawImage(this.buttonNormal, left, top, null);
        }
    }
}
