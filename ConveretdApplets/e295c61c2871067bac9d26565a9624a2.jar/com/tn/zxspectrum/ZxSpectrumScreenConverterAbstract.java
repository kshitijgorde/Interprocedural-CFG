// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public abstract class ZxSpectrumScreenConverterAbstract implements ZxSpectrumScreenConverter
{
    protected Color[] ivColorTable;
    protected Rectangle ivSrcWindow;
    protected Rectangle ivDstWindow;
    protected float ivScalingX;
    protected float ivScalingY;
    protected Graphics ivDstGraphics;
    private Rectangle ivNewSrcWindow;
    private Rectangle ivNewDstWindow;
    private Color[] ivNewColorTable;
    private boolean ivValid;
    public static final Color[] COLOR_TABLE_BW;
    public static final int rgb0 = 2630178;
    public static final int rgb1 = 12630176;
    public static final int rgb2 = 16775125;
    public static final int rgb3 = 0;
    public static final int rgb4 = 12632256;
    public static final int rgb5 = 16777215;
    public static final Color[] COLOR_TABLE_RGB;
    public static final Color[] COLOR_TABLE_ISSUE2;
    public static final String COLOR_TABLE_ID_RGB = "COLOUR";
    public static final String COLOR_TABLE_ID_BW = "B/W";
    public static final String COLOR_TABLE_ID_ISSUE2 = "ISSUE2";
    
    static {
        COLOR_TABLE_BW = new Color[] { new Color(0), new Color(1776411), new Color(3552822), new Color(5329233), new Color(7105644), new Color(8882055), new Color(10658466), new Color(12434877), new Color(0), new Color(2368548), new Color(4737096), new Color(7105644), new Color(9474192), new Color(11842740), new Color(14211288), new Color(16579836) };
        COLOR_TABLE_RGB = new Color[] { color(0, 16777215, 0, 0), color(0, 16777215, 1, 0), color(0, 16777215, 2, 0), color(0, 16777215, 3, 0), color(0, 16777215, 4, 0), color(0, 16777215, 5, 0), color(0, 16777215, 6, 0), color(0, 16777215, 7, 0), color(0, 16777215, 0, 1), color(0, 16777215, 1, 1), color(0, 16777215, 2, 1), color(0, 16777215, 3, 1), color(0, 16777215, 4, 1), color(0, 16777215, 5, 1), color(0, 16777215, 6, 1), color(0, 16777215, 7, 1) };
        COLOR_TABLE_ISSUE2 = new Color[] { color(2630178, 16775125, 0, 0), color(2630178, 16775125, 1, 0), color(2630178, 16775125, 2, 0), color(2630178, 16775125, 3, 0), color(2630178, 16775125, 4, 0), color(2630178, 16775125, 5, 0), color(2630178, 16775125, 6, 0), color(2630178, 16775125, 7, 0), color(2630178, 16775125, 0, 1), color(2630178, 16775125, 1, 1), color(2630178, 16775125, 2, 1), color(2630178, 16775125, 3, 1), color(2630178, 16775125, 4, 1), color(2630178, 16775125, 5, 1), color(2630178, 16775125, 6, 1), color(2630178, 16775125, 7, 1) };
    }
    
    public ZxSpectrumScreenConverterAbstract(final Graphics pTargetGraphics) {
        this.ivSrcWindow = null;
        this.ivDstWindow = null;
        this.ivScalingX = -1.0f;
        this.ivScalingY = -1.0f;
        this.ivDstGraphics = null;
        this.ivNewSrcWindow = null;
        this.ivNewDstWindow = null;
        this.ivNewColorTable = null;
        this.ivValid = false;
        this.ivDstGraphics = pTargetGraphics;
        this.ivColorTable = ZxSpectrumScreenConverterAbstract.COLOR_TABLE_RGB.clone();
        this.ivNewSrcWindow = new Rectangle(48, 64, 256, 192);
        this.ivNewDstWindow = new Rectangle(0, 0, 256, 192);
        this.invalidate();
    }
    
    @Override
    public void beginFrame() {
        if (!this.ivValid) {
            this.validate();
            this.refresh();
        }
    }
    
    private static Color color(final int rgb0, final int rgb1, final int number, final int bright) {
        final float t = (bright == 1) ? (0.75f + 0.25f * number / 7.0f) : 0.75f;
        final float tr = ((number & 0x2) == 0x0) ? 0.0f : t;
        final float tg = ((number & 0x4) == 0x0) ? 0.0f : t;
        final float tb = ((number & 0x1) == 0x0) ? 0.0f : t;
        final int r = (int)((rgb0 & 0xFF0000) + tr * (rgb1 - rgb0 & 0xFF0000)) & 0xFF0000;
        final int g = (int)((rgb0 & 0xFF00) + tg * (rgb1 - rgb0 & 0xFF00)) & 0xFF00;
        final int b = (int)((rgb0 & 0xFF) + tb * (rgb1 - rgb0 & 0xFF)) & 0xFF;
        return new Color(r | g | b);
    }
    
    @Override
    public void endFrame() {
    }
    
    protected synchronized void invalidate() {
        this.ivValid = false;
    }
    
    public synchronized void setColor(final int pPaletteIndex, final Color pColor) {
        if (pPaletteIndex < 0 || pPaletteIndex > 15) {
            throw new IllegalArgumentException("Parameter pPaletteIndex out of range (0..15): " + pPaletteIndex);
        }
        if (pColor == null) {
            throw new NullPointerException("Parameter pColor is null");
        }
        if (this.ivNewColorTable == null) {
            this.ivNewColorTable = this.ivColorTable.clone();
        }
        this.ivNewColorTable[pPaletteIndex] = pColor;
        this.invalidate();
    }
    
    public void setColorTable(final Color[] pColorTable) {
        if (pColorTable.length != 16) {
            throw new IllegalArgumentException("Invalid length of parameter pColorTable (16): " + pColorTable.length);
        }
        for (int i = 0; i < 16; ++i) {
            this.setColor(i, pColorTable[i]);
        }
    }
    
    public void setColorTable(final String pColorTableId) {
        if (pColorTableId.equals("COLOUR")) {
            this.setColorTable(ZxSpectrumScreenConverterAbstract.COLOR_TABLE_RGB);
        }
        else if (pColorTableId.equals("B/W")) {
            this.setColorTable(ZxSpectrumScreenConverterAbstract.COLOR_TABLE_BW);
        }
        else {
            if (!pColorTableId.equals("ISSUE2")) {
                throw new RuntimeException("Unknown color table: " + pColorTableId);
            }
            this.setColorTable(ZxSpectrumScreenConverterAbstract.COLOR_TABLE_ISSUE2);
        }
    }
    
    @Override
    public synchronized void setSourceWindow(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
        this.ivNewSrcWindow = new Rectangle(pXMin, pYMin, pXMax - pXMin, pYMax - pYMin);
        this.invalidate();
    }
    
    @Override
    public void setTargetGraphics(final Graphics pGraphics) {
        this.ivDstGraphics = pGraphics;
    }
    
    @Override
    public synchronized void setTargetWindow(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
        this.ivNewDstWindow = new Rectangle(pXMin, pYMin, pXMax - pXMin, pYMax - pYMin);
        this.invalidate();
    }
    
    public void terminate() {
    }
    
    protected void validate() {
        if (this.ivNewSrcWindow != null) {
            this.ivSrcWindow = this.ivNewSrcWindow;
            this.ivNewSrcWindow = null;
        }
        if (this.ivNewDstWindow != null) {
            this.ivDstWindow = this.ivNewDstWindow;
            this.ivNewDstWindow = null;
        }
        this.ivScalingX = this.ivDstWindow.width / this.ivSrcWindow.width;
        this.ivScalingY = this.ivDstWindow.height / this.ivSrcWindow.height;
        if (this.ivNewColorTable != null) {
            this.ivColorTable = this.ivNewColorTable;
            this.ivNewColorTable = null;
        }
        this.ivValid = true;
    }
}
