// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Image;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

public class DrawRoutines
{
    static final int[][] checkA;
    static final int[][] radioA;
    static GraphicsConfiguration conf;
    
    public static void drawBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
    }
    
    public static void drawEditableComboBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawLine(n, n2 + 3, n, n4 - 4);
        graphics.drawLine(n + 3, n2, n3 - 1, n2);
        graphics.drawLine(n + 3, n4 - 1, n3 - 1, n4 - 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 216));
        graphics.drawLine(n + 2, n2, n + 2, n2);
        graphics.drawLine(n + n3 - 3, n2, n + n3 - 3, n2);
        graphics.drawLine(n, n2 + 2, n, n2 + 2);
        graphics.drawLine(n, n2 + n4 - 3, n, n2 + n4 - 3);
        graphics.drawLine(n + 2, n2 + n4 - 1, n + 2, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 3, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 130));
        graphics.drawLine(n + 1, n2, n + 1, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + 1);
        graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 24));
        graphics.drawLine(n, n2, n, n2);
        graphics.drawLine(n, n2 + n4 - 1, n, n2 + n4 - 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 112));
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 104));
        graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
    }
    
    public static void drawRoundedBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawLine(n + 3, n2, n + n3 - 4, n2);
        graphics.drawLine(n + 3, n2 + n4 - 1, n + n3 - 4, n2 + n4 - 1);
        graphics.drawLine(n, n2 + 3, n, n2 + n4 - 4);
        graphics.drawLine(n + n3 - 1, n2 + 3, n + n3 - 1, n2 + n4 - 4);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 216));
        graphics.drawLine(n + 2, n2, n + 2, n2);
        graphics.drawLine(n + n3 - 3, n2, n + n3 - 3, n2);
        graphics.drawLine(n, n2 + 2, n, n2 + 2);
        graphics.drawLine(n, n2 + n4 - 3, n, n2 + n4 - 3);
        graphics.drawLine(n + 2, n2 + n4 - 1, n + 2, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 3, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + 2);
        graphics.drawLine(n + n3 - 1, n2 + n4 - 3, n + n3 - 1, n2 + n4 - 3);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 130));
        graphics.drawLine(n + 1, n2, n + 1, n2);
        graphics.drawLine(n + n3 - 2, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + 1);
        graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 2, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
        graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 24));
        graphics.drawLine(n, n2, n, n2);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2);
        graphics.drawLine(n, n2 + n4 - 1, n, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 112));
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
        graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 104));
        graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
    }
    
    public static synchronized void drawProgressBarBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
        graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
        graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
    }
    
    public static void drawRolloverBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(ColorRoutines.darken(color, 10));
        graphics.drawLine(n + 2, n2 + n4 - 2, n + n3 - 3, n2 + n4 - 2);
        graphics.setColor(color);
        graphics.drawLine(n + 1, n2 + n4 - 3, n + n3 - 2, n2 + n4 - 3);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 144));
        graphics.drawLine(n + 2, n2 + 2, n + n3 - 3, n2 + 2);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 64));
        graphics.drawLine(n + 2, n2 + 1, n + n3 - 3, n2 + 1);
        final int n5 = 191 / (n4 - 5);
        int n6 = 64 + n5;
        for (int i = n2 + 2; i < n2 + n4 - 3; ++i) {
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), n6));
            graphics.drawLine(n + 1, i, n + 1, i);
            graphics.drawLine(n + n3 - 2, i, n + n3 - 2, i);
            n6 += n5;
        }
        final int n7 = 111 / (n4 - 6);
        int n8 = 144 + n7;
        for (int j = n2 + 3; j < n2 + n4 - 3; ++j) {
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), n8));
            graphics.drawLine(n + 2, j, n + 2, j);
            graphics.drawLine(n + n3 - 3, j, n + n3 - 3, j);
            n8 += n7;
        }
    }
    
    public static void drawRolloverCheckBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                if (DrawRoutines.checkA[i][j] > 0) {
                    graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), DrawRoutines.checkA[i][j]));
                    graphics.drawLine(j + 1, i + 1, j + 1, i + 1);
                }
            }
        }
        graphics.translate(-n, -n2);
    }
    
    public static void drawSelectedXpTabBorder(final Graphics graphics, final Color color, int n, int n2, int n3, int n4, final int n5) {
        final Color adjustedColor = SBChooser.getAdjustedColor(Theme.tabRolloverColor[Theme.style].getColor(), 20, -30);
        graphics.setColor(adjustedColor);
        final Color average = ColorRoutines.getAverage(Theme.backColor[Theme.style].getColor(), adjustedColor);
        switch (n5) {
            case 2: {
                --n4;
                graphics.drawLine(n, n2 + 2, n, n2 + n4 - 3);
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                graphics.drawLine(n + 2, n2, n + 2, n2);
                graphics.drawLine(n + 2, n2 + n4 - 1, n + 2, n2 + n4 - 1);
                graphics.setColor(Theme.tabRolloverColor[Theme.style].getColor());
                graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + n4 - 3);
                graphics.drawLine(n + 2, n2 + 1, n + 2, n2 + n4 - 2);
                graphics.setColor(average);
                graphics.drawLine(n, n2 + 1, n, n2 + 1);
                graphics.drawLine(n + 1, n2, n + 1, n2);
                graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
                graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
                graphics.setColor(color);
                graphics.drawLine(n + 3, n2, n + n3 - 2, n2);
                graphics.drawLine(n + 3, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
                break;
            }
            case 4: {
                --n4;
                n -= 2;
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + n4 - 3);
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                graphics.drawLine(n + n3 - 3, n2, n + n3 - 3, n2);
                graphics.drawLine(n + n3 - 3, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
                graphics.setColor(Theme.tabRolloverColor[Theme.style].getColor());
                graphics.drawLine(n + n3 - 2, n2 + 2, n + n3 - 2, n2 + n4 - 3);
                graphics.drawLine(n + n3 - 3, n2 + 1, n + n3 - 3, n2 + n4 - 2);
                graphics.setColor(average);
                graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2, n + n3 - 2, n2);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
                graphics.setColor(color);
                graphics.drawLine(n, n2, n + n3 - 4, n2);
                graphics.drawLine(n, n2 + n4 - 1, n + n3 - 4, n2 + n4 - 1);
                break;
            }
            case 3: {
                --n3;
                n2 -= 2;
                graphics.drawLine(n + 2, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                graphics.drawLine(n, n2 + n4 - 3, n, n2 + n4 - 3);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 3, n + n3 - 1, n2 + n4 - 3);
                graphics.setColor(Theme.tabRolloverColor[Theme.style].getColor());
                graphics.drawLine(n + 2, n2 + n4 - 2, n + n3 - 3, n2 + n4 - 2);
                graphics.drawLine(n + 1, n2 + n4 - 3, n + n3 - 2, n2 + n4 - 3);
                graphics.setColor(color);
                graphics.drawLine(n, n2, n, n2 + n4 - 4);
                graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 4);
                graphics.setColor(average);
                graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
                graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
                break;
            }
            default: {
                --n3;
                graphics.drawLine(n + 2, n2, n + n3 - 3, n2);
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                graphics.drawLine(n, n2 + 2, n, n2 + 2);
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + 2);
                graphics.setColor(Theme.tabRolloverColor[Theme.style].getColor());
                graphics.drawLine(n + 2, n2 + 1, n + n3 - 3, n2 + 1);
                graphics.drawLine(n + 1, n2 + 2, n + n3 - 2, n2 + 2);
                graphics.setColor(average);
                graphics.drawLine(n + 1, n2, n + 1, n2);
                graphics.drawLine(n, n2 + 1, n, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2, n + n3 - 2, n2);
                graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
                graphics.setColor(color);
                graphics.drawLine(n, n2 + 3, n, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + 3, n + n3 - 1, n2 + n4 - 1);
                break;
            }
        }
    }
    
    public static void drawXpTabBorder(final Graphics graphics, final Color color, int n, int n2, int n3, int n4, final int n5) {
        graphics.setColor(color);
        switch (n5) {
            case 2: {
                --n4;
                graphics.drawLine(n + 2, n2, n + n3 - 1, n2);
                graphics.drawLine(n + 2, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
                graphics.drawLine(n, n2 + 2, n, n2 + n4 - 3);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 56));
                graphics.drawLine(n, n2, n, n2);
                graphics.drawLine(n, n2 + n4 - 1, n, n2 + n4 - 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 183));
                graphics.drawLine(n + 1, n2, n + 1, n2);
                graphics.drawLine(n, n2 + 1, n, n2 + 1);
                graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
                graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 76));
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                break;
            }
            case 4: {
                --n4;
                n -= 2;
                graphics.drawLine(n, n2, n + n3 - 3, n2);
                graphics.drawLine(n, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + n4 - 3);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 56));
                graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 183));
                graphics.drawLine(n + n3 - 2, n2, n + n3 - 2, n2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 76));
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                break;
            }
            case 3: {
                --n3;
                n2 -= 2;
                graphics.drawLine(n + 2, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
                graphics.drawLine(n, n2, n, n2 + n4 - 3);
                graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 3);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 56));
                graphics.drawLine(n, n2 + n4 - 1, n, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 183));
                graphics.drawLine(n, n2 + n4 - 2, n, n2 + n4 - 2);
                graphics.drawLine(n + 1, n2 + n4 - 1, n + 1, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 1, n + n3 - 2, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 76));
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                break;
            }
            default: {
                --n3;
                graphics.drawLine(n + 2, n2, n + n3 - 3, n2);
                graphics.drawLine(n, n2 + 2, n, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + n4 - 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 56));
                graphics.drawLine(n, n2, n, n2);
                graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 183));
                graphics.drawLine(n + 1, n2, n + 1, n2);
                graphics.drawLine(n + n3 - 2, n2, n + n3 - 2, n2);
                graphics.drawLine(n, n2 + 1, n, n2 + 1);
                graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 76));
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                break;
            }
        }
    }
    
    public static void drawWinTabBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.setColor(Color.WHITE);
        switch (n5) {
            case 2: {
                graphics.drawLine(n + 2, n2, n + n3 - 1, n2);
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n, n2 + 2, n, n2 + n4 - 3);
                graphics.setColor(color);
                graphics.drawLine(n + 2, n2 + n4 - 2, n + n3 - 1, n2 + n4 - 2);
                graphics.setColor(ColorRoutines.darken(color, 50));
                graphics.drawLine(n + 2, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                break;
            }
            case 4: {
                graphics.drawLine(n + n3 - 3, n2, n, n2);
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + n4 - 3);
                graphics.setColor(color);
                graphics.drawLine(n + n3 - 3, n2 + n4 - 2, n, n2 + n4 - 2);
                graphics.setColor(ColorRoutines.darken(color, 50));
                graphics.drawLine(n + n3 - 3, n2 + n4 - 1, n, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                break;
            }
            case 3: {
                graphics.drawLine(n + 2, n2 + n4 - 1, n + n3 - 3, n2 + n4 - 1);
                graphics.drawLine(n, n2 + n4 - 3, n, n2);
                graphics.drawLine(n + 1, n2 + n4 - 2, n + 1, n2 + n4 - 2);
                graphics.setColor(color);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 3, n + n3 - 2, n2);
                graphics.setColor(ColorRoutines.darken(color, 50));
                graphics.drawLine(n + n3 - 1, n2 + n4 - 3, n + n3 - 1, n2);
                graphics.drawLine(n + n3 - 2, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
                break;
            }
            default: {
                graphics.drawLine(n + 2, n2, n + n3 - 3, n2);
                graphics.drawLine(n, n2 + 2, n, n2 + n4 - 1);
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.setColor(color);
                graphics.drawLine(n + n3 - 2, n2 + 2, n + n3 - 2, n2 + n4 - 1);
                graphics.setColor(ColorRoutines.darken(color, 50));
                graphics.drawLine(n + n3 - 1, n2 + 2, n + n3 - 1, n2 + n4 - 1);
                graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + 1);
                break;
            }
        }
    }
    
    public static void drawXpRadioRolloverBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.translate(n, n2);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                if (DrawRoutines.radioA[i][j] > 0) {
                    graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), DrawRoutines.radioA[i][j]));
                    graphics.drawLine(j + 1, i + 1, j + 1, i + 1);
                }
            }
        }
        graphics.translate(-n, -n2);
    }
    
    public static void drawXpRadioBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(color);
        graphics.drawLine(n + 6, n2, n + 6, n2);
        graphics.drawLine(n + 3, n2 + 1, n + 3, n2 + 1);
        graphics.drawLine(n + 9, n2 + 1, n + 9, n2 + 1);
        graphics.drawLine(n + 1, n2 + 3, n + 1, n2 + 3);
        graphics.drawLine(n + 11, n2 + 3, n + 11, n2 + 3);
        graphics.drawLine(n, n2 + 6, n, n2 + 6);
        graphics.drawLine(n + 12, n2 + 6, n + 12, n2 + 6);
        graphics.drawLine(n + 1, n2 + 9, n + 1, n2 + 9);
        graphics.drawLine(n + 11, n2 + 9, n + 11, n2 + 9);
        graphics.drawLine(n + 3, n2 + 11, n + 3, n2 + 11);
        graphics.drawLine(n + 9, n2 + 11, n + 9, n2 + 11);
        graphics.drawLine(n + 6, n2 + 12, n + 6, n2 + 12);
        graphics.setColor(ColorRoutines.getAlphaColor(color, 168));
        graphics.drawLine(n + 5, n2, n + 5, n2);
        graphics.drawLine(n + 7, n2, n + 7, n2);
        graphics.drawLine(n + 4, n2 + 1, n + 4, n2 + 1);
        graphics.drawLine(n + 8, n2 + 1, n + 8, n2 + 1);
        graphics.drawLine(n + 2, n2 + 2, n + 2, n2 + 2);
        graphics.drawLine(n + 10, n2 + 2, n + 10, n2 + 2);
        graphics.drawLine(n + 1, n2 + 4, n + 1, n2 + 4);
        graphics.drawLine(n + 11, n2 + 4, n + 11, n2 + 4);
        graphics.drawLine(n, n2 + 5, n, n2 + 5);
        graphics.drawLine(n + 12, n2 + 5, n + 12, n2 + 5);
        graphics.drawLine(n, n2 + 7, n, n2 + 7);
        graphics.drawLine(n + 12, n2 + 7, n + 12, n2 + 7);
        graphics.drawLine(n + 1, n2 + 8, n + 1, n2 + 8);
        graphics.drawLine(n + 11, n2 + 8, n + 11, n2 + 8);
        graphics.drawLine(n + 2, n2 + 10, n + 2, n2 + 10);
        graphics.drawLine(n + 10, n2 + 10, n + 10, n2 + 10);
        graphics.drawLine(n + 4, n2 + 11, n + 4, n2 + 11);
        graphics.drawLine(n + 8, n2 + 11, n + 8, n2 + 11);
        graphics.drawLine(n + 5, n2 + 12, n + 5, n2 + 12);
        graphics.drawLine(n + 7, n2 + 12, n + 7, n2 + 12);
        graphics.setColor(ColorRoutines.getAlphaColor(color, 64));
        graphics.drawLine(n + 4, n2, n + 4, n2);
        graphics.drawLine(n + 8, n2, n + 8, n2);
        graphics.drawLine(n + 2, n2 + 1, n + 2, n2 + 1);
        graphics.drawLine(n + 2, n2 + 3, n + 2, n2 + 3);
        graphics.drawLine(n + 10, n2 + 1, n + 10, n2 + 1);
        graphics.drawLine(n + 10, n2 + 3, n + 10, n2 + 3);
        graphics.drawLine(n + 5, n2 + 1, n + 5, n2 + 1);
        graphics.drawLine(n + 7, n2 + 1, n + 7, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + 2);
        graphics.drawLine(n + 1, n2 + 5, n + 1, n2 + 5);
        graphics.drawLine(n + 1, n2 + 7, n + 1, n2 + 7);
        graphics.drawLine(n + 11, n2 + 2, n + 11, n2 + 2);
        graphics.drawLine(n + 3, n2 + 2, n + 3, n2 + 2);
        graphics.drawLine(n + 9, n2 + 2, n + 9, n2 + 2);
        graphics.drawLine(n, n2 + 4, n, n2 + 4);
        graphics.drawLine(n + 12, n2 + 4, n + 12, n2 + 4);
        graphics.drawLine(n, n2 + 8, n, n2 + 8);
        graphics.drawLine(n + 12, n2 + 8, n + 12, n2 + 8);
        graphics.drawLine(n + 2, n2 + 9, n + 2, n2 + 9);
        graphics.drawLine(n + 10, n2 + 9, n + 10, n2 + 9);
        graphics.drawLine(n + 1, n2 + 10, n + 1, n2 + 10);
        graphics.drawLine(n + 11, n2 + 5, n + 11, n2 + 5);
        graphics.drawLine(n + 11, n2 + 7, n + 11, n2 + 7);
        graphics.drawLine(n + 11, n2 + 10, n + 11, n2 + 10);
        graphics.drawLine(n + 3, n2 + 10, n + 3, n2 + 10);
        graphics.drawLine(n + 9, n2 + 10, n + 9, n2 + 10);
        graphics.drawLine(n + 2, n2 + 11, n + 2, n2 + 11);
        graphics.drawLine(n + 10, n2 + 11, n + 10, n2 + 11);
        graphics.drawLine(n + 5, n2 + 11, n + 5, n2 + 11);
        graphics.drawLine(n + 7, n2 + 11, n + 7, n2 + 11);
        graphics.drawLine(n + 4, n2 + 12, n + 4, n2 + 12);
        graphics.drawLine(n + 8, n2 + 12, n + 8, n2 + 12);
        graphics.setColor(ColorRoutines.getAlphaColor(color, 16));
        graphics.drawLine(n + 3, n2, n + 3, n2);
        graphics.drawLine(n + 9, n2, n + 9, n2);
        graphics.drawLine(n, n2 + 3, n, n2 + 3);
        graphics.drawLine(n + 12, n2 + 3, n + 12, n2 + 3);
        graphics.drawLine(n, n2 + 9, n, n2 + 9);
        graphics.drawLine(n + 12, n2 + 9, n + 12, n2 + 9);
        graphics.drawLine(n + 3, n2 + 12, n + 3, n2 + 12);
        graphics.drawLine(n + 9, n2 + 12, n + 9, n2 + 12);
    }
    
    public static ImageIcon colorize(final Image image, final Color color) {
        final ColorRoutines colorRoutines = new ColorRoutines(color);
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage compatibleImage = DrawRoutines.conf.createCompatibleImage(width, height, 3);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("PixelGrabber interrupted waiting for pixels");
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println("Image fetch aborted or errored.");
        }
        else {
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    compatibleImage.setRGB(j, i, colorize(array[i * width + j], colorRoutines));
                }
            }
        }
        return new ImageIcon(compatibleImage);
    }
    
    public static ImageIcon colorize(final Image image, final int n, final int n2, final int n3, final boolean b) {
        final ColorRoutines colorRoutines = new ColorRoutines(n, n2, n3, b);
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage compatibleImage = DrawRoutines.conf.createCompatibleImage(width, height, 3);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("PixelGrabber interrupted waiting for pixels");
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println("Image fetch aborted or errored.");
        }
        else {
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    compatibleImage.setRGB(j, i, colorize(array[i * width + j], colorRoutines));
                }
            }
        }
        return new ImageIcon(compatibleImage);
    }
    
    protected static int colorize(final int n, final ColorRoutines colorRoutines) {
        final int n2 = n >> 24 & 0xFF;
        if (n2 == 0) {
            return n;
        }
        return colorRoutines.colorize(n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n2);
    }
    
    public static BufferedImage getBufferedImage(final Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage compatibleImage = DrawRoutines.conf.createCompatibleImage(width, height);
        compatibleImage.getGraphics().drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
        return compatibleImage;
    }
    
    static {
        checkA = new int[][] { { 53, 66, 78, 99, 115, 136, 144, 156, 165, 177, 189 }, { 66, 78, 99, 115, 136, 144, 156, 165, 177, 189, 202 }, { 78, 99, 0, 0, 0, 0, 0, 0, 0, 202, 210 }, { 99, 115, 0, 0, 0, 0, 0, 0, 0, 210, 214 }, { 115, 136, 0, 0, 0, 0, 0, 0, 0, 214, 226 }, { 136, 144, 0, 0, 0, 0, 0, 0, 0, 226, 230 }, { 144, 156, 0, 0, 0, 0, 0, 0, 0, 230, 239 }, { 156, 165, 0, 0, 0, 0, 0, 0, 0, 239, 243 }, { 165, 177, 0, 0, 0, 0, 0, 0, 0, 243, 247 }, { 177, 189, 202, 210, 214, 226, 230, 239, 243, 247, 251 }, { 189, 202, 210, 214, 226, 230, 239, 243, 247, 251, 255 } };
        radioA = new int[][] { { 0, 0, 78, 99, 115, 136, 144, 156, 165, 0, 0 }, { 0, 78, 99, 115, 136, 144, 156, 165, 177, 189, 0 }, { 78, 99, 115, 136, 92, 48, 92, 177, 189, 202, 210 }, { 99, 115, 136, 0, 0, 0, 0, 0, 202, 210, 214 }, { 115, 136, 92, 0, 0, 0, 0, 0, 128, 214, 226 }, { 136, 144, 48, 0, 0, 0, 0, 0, 64, 226, 230 }, { 144, 156, 92, 0, 0, 0, 0, 0, 128, 230, 239 }, { 156, 165, 177, 0, 0, 0, 0, 0, 230, 239, 243 }, { 165, 177, 189, 202, 128, 64, 128, 230, 239, 243, 247 }, { 0, 189, 202, 210, 214, 226, 230, 239, 243, 247, 0 }, { 0, 0, 210, 214, 226, 230, 239, 243, 247, 0, 0 } };
        DrawRoutines.conf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}
