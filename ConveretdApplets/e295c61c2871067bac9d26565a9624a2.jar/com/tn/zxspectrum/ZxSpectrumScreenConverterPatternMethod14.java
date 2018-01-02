// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Component;

public class ZxSpectrumScreenConverterPatternMethod14 extends ZxSpectrumScreenConverterAbstract
{
    private byte[][] ivPixelData;
    private byte[][] ivAttrData;
    private int[] ivBorderData;
    private int ivFlashMask;
    private int[] ivPatternIndices;
    private int ivIntScalingX;
    private int ivIntScalingY;
    private Component ivComponent;
    private BufferedImage ivOffScreenImage;
    private Graphics ivOffScreenGraphics;
    
    public ZxSpectrumScreenConverterPatternMethod14(final Graphics pTargetGraphics, final Component pComponent) {
        super(pTargetGraphics);
        this.ivComponent = null;
        this.ivComponent = pComponent;
        this.ivPixelData = new byte[192][32];
        this.ivAttrData = new byte[192][32];
        this.ivBorderData = new int[312];
        this.resetCaches();
    }
    
    @Override
    public void endFrame() {
        this.ivDstGraphics.drawImage(this.ivOffScreenImage, this.ivDstWindow.x, this.ivDstWindow.y, this.ivDstWindow.x + this.ivDstWindow.width, this.ivDstWindow.y + this.ivDstWindow.height, 0, 0, this.ivDstWindow.width, this.ivDstWindow.height, null);
    }
    
    private void initializeOffScreenImage() {
        this.ivOffScreenImage = this.ivComponent.getGraphicsConfiguration().createCompatibleImage(Math.max(this.ivDstWindow.width, 263 * this.ivIntScalingX), this.ivDstWindow.height + 128 * this.ivIntScalingY);
        this.ivOffScreenGraphics = this.ivOffScreenImage.getGraphics();
    }
    
    private void initializePatterns() {
        int reg = 1;
        final int fb = 29;
        final boolean[] pixels = new boolean[263];
        pixels[0] = false;
        for (int i = 1; i <= 262; ++i) {
            reg <<= 1;
            if (reg > 255) {
                reg = ((reg ^ fb) & 0xFF);
                pixels[i] = true;
            }
            else {
                pixels[i] = false;
            }
        }
        this.ivPatternIndices = new int[256];
        for (int i = 0; i < 256; ++i) {
            reg = 0;
            for (int b = i; b < i + 8; ++b) {
                reg <<= 1;
                if (pixels[b]) {
                    ++reg;
                }
            }
            this.ivPatternIndices[reg] = i * this.ivIntScalingX;
        }
        for (int attr = 0; attr < 128; ++attr) {
            final Color ink = this.ivColorTable[(attr & 0x7) | (attr >> 3 & 0x8)];
            final Color paper = this.ivColorTable[attr >> 3 & 0xF];
            this.ivOffScreenGraphics.setColor(paper);
            this.ivOffScreenGraphics.fillRect(0, this.ivDstWindow.height + attr * this.ivIntScalingY, 263 * this.ivIntScalingX, this.ivIntScalingY);
            this.ivOffScreenGraphics.setColor(ink);
            for (int x = 0; x <= 262; ++x) {
                if (pixels[x]) {
                    this.ivOffScreenGraphics.fillRect(x * this.ivIntScalingX, this.ivDstWindow.height + attr * this.ivIntScalingY, this.ivIntScalingX, this.ivIntScalingY);
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
            final byte[] pixelData = this.ivPixelData[displayFileY];
            final byte[] attrData = this.ivAttrData[displayFileY];
            int offsetPixels = ((displayFileY & 0xC0) << 5 | (displayFileY & 0x7) << 8 | (displayFileY & 0x38) << 2) + pOffsetBitmapData;
            int offsetAttrs = ((displayFileY & 0xF8) << 2) + pOffsetBitmapData + 6144;
            int dstX = dstBitmapXMin;
            final int patternXSize = 8 * this.ivIntScalingX;
            final int patternYSize = this.ivIntScalingY;
            for (int i = 0; i < 32; ++i) {
                byte pixels = (byte)pMemory[offsetPixels++];
                final byte attr = (byte)pMemory[offsetAttrs++];
                if ((attr & this.ivFlashMask) != 0x0) {
                    pixels ^= (byte)255;
                }
                if (pixels != pixelData[i] || attr != attrData[i]) {
                    pixelData[i] = pixels;
                    attrData[i] = attr;
                    final int sx = this.ivPatternIndices[pixels & 0xFF];
                    final int sy = this.ivDstWindow.height + (attr & 0x7F) * patternYSize;
                    this.ivOffScreenGraphics.copyArea(sx, sy, patternXSize, patternYSize, dstX - sx, dstY - sy);
                }
                dstX += patternXSize;
            }
            final int borderbits = pPortFE & 0x7;
            if (borderbits != this.ivBorderData[pSrcY]) {
                this.ivBorderData[pSrcY] = borderbits;
                final Color border = this.ivColorTable[borderbits];
                this.ivOffScreenGraphics.setColor(border);
                this.ivOffScreenGraphics.fillRect(dstBorderXMin, dstY, dstBitmapXMin - dstBorderXMin, this.ivIntScalingY);
                this.ivOffScreenGraphics.fillRect(dstBitmapXMax, dstY, dstBorderXMax - dstBitmapXMax, this.ivIntScalingY);
            }
        }
        else {
            final int borderbits2 = pPortFE & 0x7;
            if (borderbits2 != this.ivBorderData[pSrcY]) {
                this.ivBorderData[pSrcY] = borderbits2;
                final Color border2 = this.ivColorTable[borderbits2];
                this.ivOffScreenGraphics.setColor(border2);
                this.ivOffScreenGraphics.fillRect(dstBorderXMin, dstY, dstBorderXMax - dstBorderXMin, this.ivIntScalingY);
            }
        }
    }
    
    @Override
    protected void validate() {
        super.validate();
        this.ivIntScalingX = ((this.ivScalingX > 1.0f) ? ((int)this.ivScalingX) : 1);
        this.ivIntScalingY = ((this.ivScalingY > 1.0f) ? ((int)this.ivScalingY) : 1);
        this.initializeOffScreenImage();
        this.initializePatterns();
    }
}
