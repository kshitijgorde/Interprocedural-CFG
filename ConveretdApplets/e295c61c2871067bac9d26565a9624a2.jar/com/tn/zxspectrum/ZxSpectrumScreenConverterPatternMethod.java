// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;

public class ZxSpectrumScreenConverterPatternMethod extends ZxSpectrumScreenConverterAbstract
{
    private int[][] ivPixelData;
    private int[][] ivAttrData;
    private int[] ivBorderData;
    private int ivFlashMask;
    private Image[] ivPatternImages;
    private int[] ivPatternIndices;
    private int ivIntScalingX;
    private int ivIntScalingY;
    private Component ivComponent;
    
    public ZxSpectrumScreenConverterPatternMethod(final Graphics pTargetGraphics, final Component pComponent) {
        super(pTargetGraphics);
        this.ivComponent = null;
        this.ivComponent = pComponent;
        this.ivPixelData = new int[192][32];
        this.ivAttrData = new int[192][32];
        this.ivBorderData = new int[312];
        this.resetCaches();
    }
    
    private void initializePatternImages() {
        this.ivPatternImages = new Image[128];
        for (int i = 0; i < 128; ++i) {
            this.ivPatternImages[i] = this.ivComponent.createImage(263 * this.ivIntScalingX, this.ivIntScalingY);
        }
        int reg = 1;
        final int fb = 29;
        final boolean[] pixels = new boolean[263];
        pixels[0] = false;
        for (int j = 1; j <= 262; ++j) {
            reg <<= 1;
            if (reg > 255) {
                reg = ((reg ^ fb) & 0xFF);
                pixels[j] = true;
            }
            else {
                pixels[j] = false;
            }
        }
        this.ivPatternIndices = new int[256];
        for (int j = 0; j < 256; ++j) {
            reg = 0;
            for (int b = j; b < j + 8; ++b) {
                reg <<= 1;
                if (pixels[b]) {
                    ++reg;
                }
            }
            this.ivPatternIndices[reg] = j * this.ivIntScalingX;
        }
        for (int attr = 0; attr < 128; ++attr) {
            final Graphics g = this.ivPatternImages[attr].getGraphics();
            final Color ink = this.ivColorTable[(attr & 0x7) | (attr >> 3 & 0x8)];
            final Color paper = this.ivColorTable[attr >> 3 & 0xF];
            g.setColor(paper);
            g.fillRect(0, 0, 263 * this.ivIntScalingX, this.ivIntScalingY);
            g.setColor(ink);
            for (int x = 0; x <= 262; ++x) {
                if (pixels[x]) {
                    g.fillRect(x * this.ivIntScalingX, 0, this.ivIntScalingX, this.ivIntScalingY);
                }
            }
        }
    }
    
    @Override
    public void refresh() {
        this.resetCaches();
    }
    
    private void resetCaches() {
        for (int y = 0; y < 192; ++y) {
            for (int x = 0; x < 32; ++x) {
                this.ivPixelData[y][x] = -1;
                this.ivAttrData[y][x] = -1;
            }
        }
        for (int y = 0; y < 312; ++y) {
            this.ivBorderData[y] = -1;
        }
    }
    
    @Override
    public void setFlash(final boolean pFlashValue) {
        if (pFlashValue) {
            this.ivFlashMask = 128;
        }
        else {
            this.ivFlashMask = 0;
        }
    }
    
    @Override
    public void updatePixelLine(final int[] pMemory, final int pOffsetBitmapData, final int pSrcY, final int pPortFE) {
        if (pSrcY < this.ivSrcWindow.y || pSrcY >= this.ivSrcWindow.y + this.ivSrcWindow.height) {
            return;
        }
        final int dstY = this.ivDstWindow.y + (pSrcY - this.ivSrcWindow.y) * this.ivIntScalingY;
        final int dstBorderXMin = this.ivDstWindow.x;
        final int dstBorderXMax = this.ivDstWindow.x + this.ivSrcWindow.width * this.ivIntScalingX;
        final int dstBitmapXMin = this.ivDstWindow.x + (48 - this.ivSrcWindow.x) * this.ivIntScalingX;
        final int dstBitmapXMax = this.ivDstWindow.x + (304 - this.ivSrcWindow.x) * this.ivIntScalingX;
        if (pSrcY >= 64 && pSrcY < 256) {
            final int displayFileY = pSrcY - 64;
            final int[] pixelData = this.ivPixelData[displayFileY];
            final int[] attrData = this.ivAttrData[displayFileY];
            int offsetPixels = ((displayFileY & 0xC0) << 5 | (displayFileY & 0x7) << 8 | (displayFileY & 0x38) << 2) + pOffsetBitmapData;
            int offsetAttrs = ((displayFileY & 0xF8) << 2) + pOffsetBitmapData + 6144;
            int dstX = dstBitmapXMin;
            final int patternXSize = 8 * this.ivIntScalingX;
            final int patternYSize = this.ivIntScalingY;
            for (int i = 0; i < 32; ++i) {
                int pixels = pMemory[offsetPixels++];
                final int attr = pMemory[offsetAttrs++];
                if ((attr & this.ivFlashMask) != 0x0) {
                    pixels ^= 0xFF;
                }
                if (pixels != pixelData[i] || attr != attrData[i]) {
                    pixelData[i] = pixels;
                    attrData[i] = attr;
                    final int sx = this.ivPatternIndices[pixels];
                    final int sy = attr & 0x7F;
                    this.ivDstGraphics.drawImage(this.ivPatternImages[sy], dstX, dstY, dstX + patternXSize, dstY + patternYSize, sx, 0, sx + patternXSize, patternYSize, null);
                }
                dstX += patternXSize;
            }
            final int borderbits = pPortFE & 0x7;
            if (borderbits != this.ivBorderData[pSrcY]) {
                this.ivBorderData[pSrcY] = borderbits;
                final Color border = this.ivColorTable[borderbits];
                this.ivDstGraphics.setColor(border);
                this.ivDstGraphics.fillRect(dstBorderXMin, dstY, dstBitmapXMin - dstBorderXMin, this.ivIntScalingY);
                this.ivDstGraphics.fillRect(dstBitmapXMax, dstY, dstBorderXMax - dstBitmapXMax, this.ivIntScalingY);
            }
        }
        else {
            final int borderbits2 = pPortFE & 0x7;
            if (borderbits2 != this.ivBorderData[pSrcY]) {
                this.ivBorderData[pSrcY] = borderbits2;
                final Color border2 = this.ivColorTable[borderbits2];
                this.ivDstGraphics.setColor(border2);
                this.ivDstGraphics.fillRect(dstBorderXMin, dstY, dstBorderXMax - dstBorderXMin, this.ivIntScalingY);
            }
        }
    }
    
    @Override
    protected void validate() {
        super.validate();
        this.ivIntScalingX = ((this.ivScalingX > 1.0f) ? ((int)this.ivScalingX) : 1);
        this.ivIntScalingY = ((this.ivScalingY > 1.0f) ? ((int)this.ivScalingY) : 1);
        this.initializePatternImages();
    }
}
