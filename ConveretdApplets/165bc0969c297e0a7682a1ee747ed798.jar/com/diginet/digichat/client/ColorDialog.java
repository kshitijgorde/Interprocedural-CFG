// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.esial.util.c;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class ColorDialog extends Dialog implements ActionListener
{
    private static final int N_HLSMAX = 240;
    private static final int N_UNDEF = 160;
    private static final int N_HLSMAX2 = 120;
    private static final int N_HLSMAX3 = 80;
    private static final int N_HLSMAX6 = 40;
    private static final int N_HLSMAX12 = 20;
    private static final int N_RGBMAX = 255;
    private static final int N_RGBMAX2 = 510;
    private static final int N_WIDTH = 298;
    private static final int N_HEIGHT = 166;
    private static final int S_PALETTE = 0;
    private static final int S_GRAD = 1;
    private static final int S_CUSTOM = 2;
    private static final int N_BASIC_X = 4;
    private static final int N_BASIC_Y = 14;
    private static final int N_BASIC_W = 140;
    private static final int N_BASIC_H = 86;
    private static final int N_CUSTOM_X = 6;
    private static final int N_CUSTOM_Y = 117;
    private static final int N_CUSTOM_W = 15;
    private static final int N_CUSTOM_H = 12;
    private static final int N_PALETTE_X = 152;
    private static final int N_PALETTE_Y = 4;
    private static final int N_PALETTE_W = 118;
    private static final int N_PALETTE_H = 116;
    private static final int N_GRAD_X = 280;
    private static final int N_GRAD_Y = 4;
    private static final int N_GRAD_W = 8;
    private static final int N_GRAD_H = 116;
    private static final int N_SOLID_X = 152;
    private static final int N_SOLID_Y = 124;
    private static final int N_SOLID_W = 40;
    private static final int N_SOLID_H = 26;
    private static final int C_OK = 2;
    private static final int C_CANCEL = 3;
    private static final int C_HUE = 5;
    private static final int C_SAT = 7;
    private static final int C_LUM = 9;
    private static final int C_RED = 11;
    private static final int C_GREEN = 13;
    private static final int C_BLUE = 15;
    private static final int[][] nBasic;
    private static final int nCols;
    private static final int nRows;
    private static final int[][] nItems;
    private static final String[] strItems;
    private static final Insets insZero;
    private static final Class[] clsItems;
    private static final Class[] clsParamS;
    private boolean fPalette;
    private boolean fGrad;
    private int nRes;
    private int nColor;
    private int nHue;
    private int nLum;
    private int nSat;
    private int nCustom;
    private int nPaletteX;
    private int nPaletteY;
    private int nGrad;
    private int nSens;
    private int nBasicW;
    private int nBasicH;
    private int[] nPalette;
    private Insets insShifts;
    private Rectangle recSolid;
    private Rectangle recPalette;
    private Rectangle recGrad;
    private Rectangle recBasic;
    private Rectangle recMark;
    private Rectangle recCustom;
    private Rectangle[] recSens;
    private Polygon polGrad;
    private Image imgSolid;
    private Image imgPalette;
    private Image imgGrad;
    private Image imgBasic;
    private Image imgCustom;
    private i iUser;
    
    public ColorDialog(final Frame frame, final i iUser) {
        super(frame, "", true);
        this.insShifts = null;
        this.insShifts = frame.getInsets();
        this.iUser = iUser;
    }
    
    private int normHue(final int n) {
        return (n < 0) ? (n + 240) : ((n > 240) ? (n - 240) : n);
    }
    
    private int hueToRGB(final int n, final int n2, int normHue) {
        return ((normHue = this.normHue(normHue)) < 40) ? (n + ((n2 - n) * normHue + 20) / 40) : ((normHue < 120) ? n2 : ((normHue < 160) ? (n + ((n2 - n) * (160 - normHue) + 20) / 40) : n));
    }
    
    private int[] rgbToHLS(final int n) {
        final int n2 = n >> 16 & 0xFF;
        final int n3 = n >> 8 & 0xFF;
        final int n4 = n & 0xFF;
        final int max;
        final int min;
        final int n6;
        final int n5 = ((n6 = (max = Math.max(Math.max(n2, n3), n4)) + (min = Math.min(Math.min(n2, n3), n4))) * 240 + 255) / 510;
        final int n7;
        int n8;
        int normHue;
        if ((n7 = max - min) == 0) {
            n8 = 0;
            normHue = 160;
        }
        else {
            n8 = ((n5 <= 120) ? ((n7 * 240 + (n6 >> 1)) / n6) : ((n7 * 240 + (510 - n6 >> 1)) / (510 - n6)));
            final int n10;
            final int n9 = ((max - n2) * 40 + (n10 = n7 >> 1)) / n7;
            final int n11 = ((max - n3) * 40 + n10) / n7;
            final int n12 = ((max - n4) * 40 + n10) / n7;
            normHue = this.normHue((n2 == max) ? (n12 - n11) : ((n3 == max) ? (80 + n9 - n12) : (160 + n11 - n9)));
        }
        return new int[] { normHue, n5, n8 };
    }
    
    private int hlsToRGB(final int n, final int n2, final int n3) {
        int n6;
        int n5;
        int n4;
        if (n3 == 0) {
            n4 = (n5 = (n6 = n2 * 255 / 240));
        }
        else {
            final int n8;
            final int n7 = (n2 << 1) - (n8 = ((n2 <= 120) ? ((n2 * (240 + n3) + 120) / 240) : (n2 + n3 - (n2 * n3 + 120) / 240)));
            n5 = (this.hueToRGB(n7, n8, n + 80) * 255 + 120) / 240;
            n4 = (this.hueToRGB(n7, n8, n) * 255 + 120) / 240;
            n6 = (this.hueToRGB(n7, n8, n - 80) * 255 + 120) / 240;
        }
        return (n5 << 8 | n4) << 8 | n6;
    }
    
    private void drawSunken(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n + n3 - 1;
        final int n6 = n2 + n4 - 1;
        graphics.setColor(SystemColor.controlDkShadow);
        graphics.drawLine(n, n6, n, n2);
        graphics.drawLine(n, n2, n5, n2);
        graphics.setColor(SystemColor.controlShadow);
        graphics.drawLine(n + 1, n6 - 2, n + 1, n2 + 1);
        graphics.drawLine(n + 1, n2 + 1, n5 - 2, n2 + 1);
        graphics.setColor(SystemColor.controlLtHighlight);
        graphics.drawLine(n5, n2, n5, n6);
        graphics.drawLine(n5, n6, n, n6);
    }
    
    private void drawSunken(final Graphics graphics, final Rectangle rectangle) {
        this.drawSunken(graphics, 0, 0, rectangle.width, rectangle.height);
    }
    
    private int calcWidth(final FontMetrics fontMetrics) {
        final int[] widths;
        final int length;
        int n = (length = (widths = fontMetrics.getWidths()).length) - 1;
        for (int i = 0; i < length; ++i) {
            n += widths[i];
        }
        return n / length;
    }
    
    private Rectangle recalcRect(final FontMetrics fontMetrics, final int n, final int n2, final int n3, final int n4) {
        final int height = fontMetrics.getHeight();
        final int calcWidth = this.calcWidth(fontMetrics);
        final Insets insets = (this.insShifts == null) ? ColorDialog.insZero : this.insShifts;
        return new Rectangle((n * calcWidth >> 2) + insets.left, (n2 * height >> 3) + insets.top, n3 * calcWidth >> 2, n4 * height >> 3);
    }
    
    private Image createImage(final FontMetrics fontMetrics, final Rectangle rectangle, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Rectangle recalcRect;
        rectangle.setBounds(this.recSens[n] = (recalcRect = this.recalcRect(fontMetrics, n2, n3, n4, n5)));
        recalcRect.setBounds(recalcRect.x + 2, recalcRect.y + 2, recalcRect.width - 3, recalcRect.height - 3);
        return DigiChatAppletAbstract.applet.createImage(rectangle.width, rectangle.height);
    }
    
    private void setRGB(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        image.getGraphics().drawImage(this.createImage(new MemoryImageSource(n3, n4, array, n5, n6)), n, n2, this);
    }
    
    private void setSolid(final int nColor, final int nHue, final int nLum, final int nSat, final boolean fPalette) {
        this.nColor = nColor;
        final int n = nColor | 0xFF000000;
        final int n2;
        final int[] array = new int[n2 = this.recSolid.width * this.recSolid.height];
        for (int i = 0; i < n2; ++i) {
            array[i] = n;
        }
        this.setRGB(this.imgSolid, 0, 0, this.recSolid.width, this.recSolid.height, array, 0, this.recSolid.width);
        this.drawSunken(this.imgSolid.getGraphics(), this.recSolid);
        ((TextField)this.getComponent(11)).setText(Integer.toString(nColor >> 16 & 0xFF));
        ((TextField)this.getComponent(13)).setText(Integer.toString(nColor >> 8 & 0xFF));
        ((TextField)this.getComponent(15)).setText(Integer.toString(nColor & 0xFF));
        if (nHue != this.nHue || nSat != this.nSat) {
            final int n3;
            final int[] array2 = new int[n3 = this.recGrad.width * this.recGrad.height];
            double n5;
            final double n4 = (n5 = 240.0) / (this.recGrad.height - 4);
            int n7;
            for (int n6 = this.recGrad.width << 1; (n7 = n6 + this.recGrad.width) < n3; n6 = n7) {
                final int n8 = this.hlsToRGB(nHue, (int)(n5 + 0.5), nSat) | 0xFF000000;
                for (int j = n6; j < n7; ++j) {
                    array2[j] = n8;
                }
                n5 -= n4;
            }
            this.setRGB(this.imgGrad, 0, 0, this.recGrad.width, this.recGrad.height, array2, 0, this.recGrad.width);
            this.drawSunken(this.imgGrad.getGraphics(), this.recGrad);
        }
        if (nLum != this.nLum) {
            final int nGrad;
            this.polGrad.translate(0, (nGrad = this.recGrad.y + (int)((240 - nLum) * (this.recGrad.height - 4) / 240.0 + 0.5) + 2) - this.nGrad);
            this.nGrad = nGrad;
        }
        final TextField textField = (TextField)this.getComponent(5);
        this.nHue = nHue;
        textField.setText(Integer.toString(nHue));
        final TextField textField2 = (TextField)this.getComponent(9);
        this.nLum = nLum;
        textField2.setText(Integer.toString(nLum));
        final TextField textField3 = (TextField)this.getComponent(7);
        this.nSat = nSat;
        textField3.setText(Integer.toString(nSat));
        final int nPaletteX = (int)(this.nHue * (this.recPalette.width - 4) / 240.0 + 0.5);
        final int nPaletteY = this.recPalette.height - (int)(this.nSat * (this.recPalette.height - 4) / 240.0 + 0.5);
        if (fPalette != this.fPalette || (fPalette && (nPaletteX != this.nPaletteX || nPaletteY != this.nPaletteY))) {
            this.nPaletteX = nPaletteX;
            this.nPaletteY = nPaletteY;
            this.setRGB(this.imgPalette, 0, 0, this.recPalette.width, this.recPalette.height, this.nPalette, 0, this.recPalette.width);
            final Graphics graphics = this.imgPalette.getGraphics();
            if (this.fPalette = fPalette) {
                graphics.setColor(Color.black);
                graphics.fillRect(nPaletteX - 8, nPaletteY - 2, 6, 3);
                graphics.fillRect(nPaletteX + 1, nPaletteY - 11, 3, 6);
                graphics.fillRect(nPaletteX + 7, nPaletteY - 2, 6, 3);
                graphics.fillRect(nPaletteX + 1, nPaletteY + 4, 3, 6);
            }
            this.drawSunken(graphics, this.recPalette);
        }
        this.recMark.x = -this.recBasic.x;
        this.recMark.y = -this.recBasic.y;
    Label_0878:
        for (int k = 0, y = this.recBasic.y; k < ColorDialog.nRows; ++k, y += this.nBasicH) {
            final int[] array3 = ColorDialog.nBasic[k];
            for (int l = 0, x = this.recBasic.x; l < ColorDialog.nCols; ++l, x += this.nBasicW) {
                if (array3[l] == nColor) {
                    this.recMark.x = x + 2;
                    this.recMark.y = y + 2;
                    break Label_0878;
                }
            }
        }
        this.repaint();
    }
    
    private void setSolid(final int n) {
        final int[] rgbToHLS = this.rgbToHLS(n);
        this.setSolid(n, rgbToHLS[0], rgbToHLS[1], rgbToHLS[2], true);
    }
    
    private void setSolid(final int n, final int n2, final int n3, final boolean b) {
        this.setSolid(this.hlsToRGB(n, n2, n3), n, n2, n3, b);
    }
    
    private void setSolid(final int n, final int n2, final int n3) {
        this.setSolid(n, n2, n3, true);
    }
    
    private void createItems(final Dialog dialog, int n, int n2, final int[][] array, final String[] array2, final Class[] array3) {
        final String[] array4 = { null };
        dialog.setLayout(null);
        dialog.setBackground(this.iUser.cc.c);
        Font font;
        if ((font = dialog.getParent().getFont()) == null) {
            font = new Font("Arial", 0, 12);
        }
        dialog.setFont(font);
        final FontMetrics fontMetrics;
        final int height = (fontMetrics = dialog.getFontMetrics(font)).getHeight();
        final int calcWidth = this.calcWidth(fontMetrics);
        Insets insets;
        if ((insets = this.insShifts) == null) {
            insets = ColorDialog.insZero;
        }
        final int length = array3.length;
        final Rectangle bounds = new Rectangle();
        for (int i = 0; i < length; ++i) {
            final int[] array5 = array[i];
            bounds.setBounds((array5[0] * calcWidth >> 2) + insets.left, (array5[1] * height >> 3) + insets.top, array5[2] * calcWidth >> 2, array5[3] * height >> 3);
            final Class clazz;
            if ((clazz = array3[i]) != null) {
                Component component = null;
                try {
                    array4[0] = array2[i];
                    component = clazz.getConstructor((Class<?>[])ColorDialog.clsParamS).newInstance((Object[])array4);
                }
                catch (Exception ex) {
                    try {
                        component = clazz.newInstance();
                    }
                    catch (Exception ex2) {}
                }
                dialog.add(component);
                component.setBounds(bounds);
                if (component instanceof TextField) {
                    ((TextField)component).addActionListener(this);
                }
            }
        }
        n = (n * calcWidth >> 2) + insets.left + insets.right;
        n2 = (n2 * height >> 3) + insets.top + insets.bottom;
        final Rectangle bounds2 = dialog.getParent().getBounds();
        dialog.setBounds(bounds2.x + (bounds2.width - n >> 1), bounds2.y + (bounds2.height - n2 >> 1), n, n2);
    }
    
    public int getResult(final String title, final Color color) {
        this.setTitle(title);
        this.createItems(this, 298, 166, ColorDialog.nItems, ColorDialog.strItems, ColorDialog.clsItems);
        this.nRes = 0;
        int n;
        final int nSens = (n = 2) + ColorDialog.nCols * ColorDialog.nRows + 1;
        this.nSens = nSens;
        this.recSens = new Rectangle[nSens];
        final FontMetrics fontMetrics2;
        final FontMetrics fontMetrics = fontMetrics2 = this.getFontMetrics(this.getFont());
        final Rectangle recBasic = new Rectangle();
        this.recBasic = recBasic;
        final Image image = this.createImage(fontMetrics, recBasic, 0, 4, 14, 140, 86);
        this.imgBasic = image;
        final Graphics graphics = image.getGraphics();
        final int nBasicW = this.recBasic.width / ColorDialog.nCols;
        this.nBasicW = nBasicW;
        this.recMark = new Rectangle(nBasicW - 4, (this.nBasicH = this.recBasic.height / ColorDialog.nRows) - 4);
        graphics.setColor(this.iUser.cc.c);
        graphics.fillRect(0, 0, this.recBasic.width, this.recBasic.height);
        for (int i = 0, n2 = 0; i < ColorDialog.nRows; ++i, n2 += this.nBasicH) {
            final int[] array = ColorDialog.nBasic[i];
            for (int j = 0, n3 = 0; j < ColorDialog.nCols; ++j, n3 += this.nBasicW) {
                final Rectangle rectangle = this.recSens[++n] = new Rectangle();
                graphics.setColor(new Color(array[j]));
                graphics.fillRect(rectangle.x = n3 + 5, rectangle.y = n2 + 5, rectangle.width = this.nBasicW - 9, rectangle.height = this.nBasicH - 9);
                rectangle.translate(this.recBasic.x, this.recBasic.y);
                this.drawSunken(graphics, n3 + 3, n2 + 3, this.nBasicW - 5, this.nBasicH - 5);
            }
        }
        this.recSens[2] = new Rectangle();
        final FontMetrics fontMetrics3 = fontMetrics2;
        final Rectangle recPalette = new Rectangle();
        this.recPalette = recPalette;
        this.imgPalette = this.createImage(fontMetrics3, recPalette, 0, 152, 4, 118, 116);
        final int n4;
        this.nPalette = new int[n4 = this.recPalette.width * this.recPalette.height];
        double n6;
        final double n5 = (n6 = 240.0) / (this.recPalette.height - 4);
        final double n7 = 240.0 / (this.recPalette.width - 4);
        for (int n8 = n4 - this.recPalette.width, k = this.recPalette.width << 1; k < n8; k += this.recPalette.width) {
            double n9 = 0.0;
            for (int l = 2; l < this.recPalette.width; ++l) {
                this.nPalette[l + k] = (this.hlsToRGB((int)(n9 + 0.5), 120, (int)(n6 + 0.5)) | 0xFF000000);
                n9 += n7;
            }
            n6 -= n5;
        }
        this.nLum = 0;
        this.nHue = -1;
        final boolean b = false;
        this.fGrad = b;
        this.fPalette = b;
        final FontMetrics fontMetrics4 = fontMetrics2;
        final Rectangle recGrad = new Rectangle();
        this.recGrad = recGrad;
        this.imgGrad = this.createImage(fontMetrics4, recGrad, 1, 280, 4, 8, 116);
        (this.polGrad = new Polygon(new int[] { 0, 5, 5 }, new int[] { 0, -5, 5 }, 3)).translate(this.recGrad.x + this.recGrad.width + 1, this.nGrad = this.recGrad.y + this.recGrad.height - 1);
        final FontMetrics fontMetrics5 = fontMetrics2;
        final Rectangle recSolid = new Rectangle();
        this.recSolid = recSolid;
        this.imgSolid = this.createImage(fontMetrics5, recSolid, 2, 152, 124, 40, 26);
        final FontMetrics fontMetrics6 = fontMetrics2;
        final Rectangle recCustom = new Rectangle();
        this.recCustom = recCustom;
        final Image image2 = this.createImage(fontMetrics6, recCustom, 2, 6, 117, 15, 12);
        this.imgCustom = image2;
        final Graphics graphics2;
        (graphics2 = image2.getGraphics()).setColor(color);
        graphics2.fillRect(0, 0, this.recCustom.width, this.recCustom.height);
        this.drawSunken(graphics2, this.recCustom);
        this.setSolid(this.nCustom = (color.getRGB() & 0xFFFFFF));
        this.enableEvents(112L);
        this.show();
        return this.nRes;
    }
    
    private int getRGB(final Image image, final int n, final int n2) {
        try {
            final int[] array;
            if (new PixelGrabber(image, n, n2, 1, 1, array = new int[] { 0 }, 0, 1).grabPixels()) {
                return array[0];
            }
        }
        catch (InterruptedException ex) {}
        return 0;
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                final int x;
                final int y;
                if (this.polGrad.contains(x = mouseEvent.getX(), y = mouseEvent.getY())) {
                    this.fGrad = true;
                }
                else {
                    for (int i = 0; i < this.nSens; ++i) {
                        final Rectangle rectangle;
                        if ((rectangle = this.recSens[i]).contains(x, y)) {
                            final int n = x - (rectangle.x - 2);
                            final int n2 = y - (rectangle.y - 2);
                            switch (i) {
                                case 0: {
                                    final int[] rgbToHLS = this.rgbToHLS(this.getRGB(this.imgPalette, n, n2));
                                    this.setSolid(rgbToHLS[0], this.nLum, rgbToHLS[2], false);
                                    break;
                                }
                                case 1: {
                                    this.setSolid(this.nHue, this.rgbToHLS(this.getRGB(this.imgGrad, n, n2))[1], this.nSat);
                                    break;
                                }
                                case 2: {
                                    this.setSolid(this.nCustom);
                                    break;
                                }
                                default: {
                                    final int n3 = i - 2 - 1;
                                    this.setSolid(ColorDialog.nBasic[n3 / ColorDialog.nCols][n3 % ColorDialog.nCols]);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 502: {
                if (!this.fPalette) {
                    final int[] rgbToHLS2 = this.rgbToHLS(this.getRGB(this.imgPalette, mouseEvent.getX() - this.recPalette.x, mouseEvent.getY() - this.recPalette.y));
                    this.setSolid(rgbToHLS2[0], this.nLum, rgbToHLS2[2]);
                    break;
                }
                break;
            }
        }
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (this.fGrad) {
                    final Rectangle bounds;
                    final boolean fGrad = x >= (bounds = this.polGrad.getBounds()).x && x <= bounds.x + bounds.width;
                    this.fGrad = fGrad;
                    if (fGrad) {
                        x = this.recGrad.x + 3;
                    }
                }
                for (int i = 0; i <= 2; ++i) {
                    final Rectangle rectangle;
                    if ((rectangle = this.recSens[i]).contains(x, y)) {
                        final int n = x - (rectangle.x - 2);
                        final int n2 = y - (rectangle.y - 2);
                        switch (i) {
                            case 0: {
                                final int[] rgbToHLS = this.rgbToHLS(this.getRGB(this.imgPalette, n, n2));
                                this.setSolid(rgbToHLS[0], this.nLum, rgbToHLS[2], false);
                                break;
                            }
                            case 1: {
                                this.setSolid(this.nHue, this.rgbToHLS(this.getRGB(this.imgGrad, n, n2))[1], this.nSat);
                                break;
                            }
                        }
                        return;
                    }
                }
                if (!this.fPalette) {
                    this.setSolid(this.nHue, this.nLum, this.nSat);
                    break;
                }
                break;
            }
        }
    }
    
    private void shiftItems(final Dialog dialog) {
        if (this.insShifts == null) {
            this.insShifts = dialog.getInsets();
        }
        final Dimension size = dialog.getSize();
        dialog.setSize(size.width + this.insShifts.left + this.insShifts.right, size.height + this.insShifts.top + this.insShifts.bottom);
        for (int componentCount = dialog.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component;
            final Point location = (component = dialog.getComponent(i)).getLocation();
            component.setLocation(location.x + this.insShifts.left, location.y + this.insShifts.top);
        }
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        switch (windowEvent.getID()) {
            case 200: {
                if (this.insShifts == null) {
                    this.shiftItems(this);
                    for (int i = 0; i < this.nSens; ++i) {
                        this.recSens[i].translate(this.insShifts.left, this.insShifts.top);
                    }
                    this.recSolid.translate(this.insShifts.left, this.insShifts.top);
                    this.recPalette.translate(this.insShifts.left, this.insShifts.top);
                    this.recGrad.translate(this.insShifts.left, this.insShifts.top);
                    this.polGrad.translate(this.insShifts.left, 0);
                    this.recBasic.translate(this.insShifts.left, this.insShifts.top);
                    this.recMark.translate(this.insShifts.left, this.insShifts.top);
                    this.recCustom.translate(this.insShifts.left, this.insShifts.top);
                }
                break;
            }
            case 201: {
                this.dispose();
                break;
            }
        }
    }
    
    private void setSolid(final Object o, final int n) {
        try {
            final int n2;
            if ((n2 = (Integer.parseInt(((TextField)o).getText()) & 0xFF)) != (this.nColor >> n & 0xFF)) {
                this.setSolid((this.nColor & ~(255 << n)) | n2 << n);
            }
        }
        catch (NumberFormatException ex) {}
    }
    
    private boolean execEvent(final Object o) {
        Component[] components;
        for (int length = (components = this.getComponents()).length, i = 0; i < length; ++i) {
            if (o == components[i]) {
                switch (i) {
                    default: {
                        return false;
                    }
                    case 2: {
                        this.nRes = (this.nColor | 0xFF000000);
                    }
                    case 3: {
                        this.dispose();
                        return true;
                    }
                    case 5: {
                        try {
                            this.setSolid(Integer.parseInt(((TextField)o).getText()), this.nLum, this.nSat);
                        }
                        catch (NumberFormatException ex) {}
                        break;
                    }
                    case 7: {
                        try {
                            this.setSolid(this.nHue, this.nLum, Integer.parseInt(((TextField)o).getText()));
                        }
                        catch (NumberFormatException ex2) {}
                        break;
                    }
                    case 9: {
                        try {
                            this.setSolid(this.nHue, Integer.parseInt(((TextField)o).getText()), this.nSat);
                        }
                        catch (NumberFormatException ex3) {}
                        break;
                    }
                    case 11: {
                        this.setSolid(o, 16);
                        break;
                    }
                    case 13: {
                        this.setSolid(o, 8);
                        break;
                    }
                    case 15: {
                        this.setSolid(o, 0);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        return event.id == 1001 && this.execEvent(event.target);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.execEvent(actionEvent.getSource());
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.iUser.cc.c);
        graphics.fillRect(this.polGrad.getBounds().x, this.recGrad.y - 3, 5, this.recGrad.height + 6);
        graphics.setColor(SystemColor.controlDkShadow);
        graphics.drawImage(this.imgBasic, this.recBasic.x, this.recBasic.y, this);
        graphics.drawRect(this.recMark.x, this.recMark.y, this.recMark.width, this.recMark.height);
        graphics.drawImage(this.imgCustom, this.recCustom.x, this.recCustom.y, this);
        graphics.drawImage(this.imgGrad, this.recGrad.x, this.recGrad.y, this);
        graphics.fillPolygon(this.polGrad);
        graphics.drawImage(this.imgPalette, this.recPalette.x, this.recPalette.y, this);
        graphics.drawImage(this.imgSolid, this.recSolid.x, this.recSolid.y, this);
    }
    
    static {
        nBasic = new int[][] { { 16744576, 16777088, 8454016, 65408, 8454143, 33023, 16744640, 16744703 }, { 16711680, 16776960, 8453888, 65344, 65535, 32960, 8421568, 16711935 }, { 8405056, 16744512, 65280, 32896, 16512, 8421631, 8388672, 16711808 }, { 8388608, 16744448, 32768, 32832, 255, 160, 8388736, 8388863 }, { 4194304, 8404992, 16384, 16448, 128, 64, 4194368, 4194432 }, { 0, 8421376, 8421440, 8421504, 4227200, 12632256, 4194368, 16777215 } };
        nCols = ColorDialog.nBasic[0].length;
        nRows = ColorDialog.nBasic.length;
        nItems = new int[][] { { 4, 4, 140, 9 }, { 4, 106, 140, 9 }, { 4, 150, 44, 14 }, { 52, 150, 44, 14 }, { 152, 151, 40, 9 }, { 216, 124, 18, 12 }, { 194, 126, 20, 9 }, { 216, 138, 18, 12 }, { 194, 140, 20, 9 }, { 216, 152, 18, 12 }, { 194, 154, 20, 9 }, { 269, 124, 18, 12 }, { 243, 126, 24, 9 }, { 269, 138, 18, 12 }, { 243, 140, 24, 9 }, { 269, 152, 18, 12 }, { 243, 154, 24, 9 } };
        strItems = new String[] { c.a("Basic colors:"), c.a("Custom color:"), c.a("OK"), c.a("Cancel"), c.a("Color|Solid"), "", c.a("Hue:"), "", c.a("Sat:"), "", c.a("Lum:"), "", c.a("Red:"), "", c.a("Green:"), "", c.a("Blue:") };
        insZero = new Insets(0, 0, 0, 0);
        clsItems = new Class[] { Class.forName("java.awt.Label"), Class.forName("java.awt.Label"), Class.forName("com.diginet.digichat.awt.r"), Class.forName("com.diginet.digichat.awt.r"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label"), Class.forName("java.awt.TextField"), Class.forName("java.awt.Label") };
        clsParamS = new Class[] { Class.forName("java.lang.String") };
    }
}
