// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.DataBufferInt;
import java.awt.GraphicsEnvironment;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.Serializable;

public class JSVideo implements Serializable
{
    private static final long serialVersionUID = 701607876730703063L;
    public static final int DEFAULT_WIDTH = 160;
    public static final int DEFAULT_HEIGHT = 200;
    public static final int DEFAULT_PHOSPHOR_BLEND = 77;
    private static Random myRandomGenerator;
    private static final int[] PALETTE_GRAY_STANDARD;
    private JSConsole myConsole;
    private transient int[] myNormalPalette;
    private transient int[][] myBlendedPalette;
    private transient int[] myGrayPalette;
    private transient int[] myCurrentFrameBuffer;
    private transient int[] myPreviousFrameBuffer;
    private transient int[] myResidualColorBuffer;
    private boolean myGrayPaletteMode;
    private boolean myRedrawTIAIndicator;
    private boolean myUsePhosphor;
    private int myPhosphorBlendPercent;
    private transient ClipRectangle myClipRect;
    private transient BufferedImage myBackBuffer;
    private transient int[] myBackBufferData;
    private transient ImageIcon myTestPattern;
    
    protected JSVideo(final JSConsole aConsole) {
        this.myConsole = null;
        this.myNormalPalette = new int[256];
        this.myBlendedPalette = new int[256][256];
        this.myGrayPalette = new int[256];
        this.myCurrentFrameBuffer = null;
        this.myPreviousFrameBuffer = null;
        this.myResidualColorBuffer = null;
        this.myGrayPaletteMode = false;
        this.myRedrawTIAIndicator = true;
        this.myUsePhosphor = false;
        this.myPhosphorBlendPercent = 77;
        this.myClipRect = new ClipRectangle();
        this.myBackBuffer = null;
        this.myBackBufferData = null;
        this.myTestPattern = null;
        this.myConsole = aConsole;
        this.myRedrawTIAIndicator = true;
        this.myUsePhosphor = false;
        this.myPhosphorBlendPercent = 77;
        this.myCurrentFrameBuffer = new int[48000];
        this.myPreviousFrameBuffer = new int[48000];
        this.initBackBuffer(160, 200);
        this.initPalettes();
        this.loadImages();
        this.initialize();
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (this.myClipRect == null) {
            this.myClipRect = new ClipRectangle();
        }
        if (this.myCurrentFrameBuffer == null) {
            this.myCurrentFrameBuffer = new int[48000];
        }
        if (this.myPreviousFrameBuffer == null) {
            this.myPreviousFrameBuffer = new int[48000];
        }
        this.initBackBuffer(this.getWidth(), this.getHeight());
        this.initPalettes();
        this.loadImages();
        this.initialize();
        this.refresh();
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    
    private static BufferedImage createBackBuffer(final int aWidth, final int aHeight) {
        final BufferedImage zReturn = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(aWidth, aHeight);
        return zReturn;
    }
    
    private void initBackBuffer(final int aWidth, final int aHeight) {
        this.myBackBuffer = createBackBuffer(aWidth, aHeight);
        switch (this.myBackBuffer.getType()) {
            case 1:
            case 2:
            case 3: {
                this.myBackBufferData = ((DataBufferInt)this.myBackBuffer.getRaster().getDataBuffer()).getData();
                break;
            }
            default: {
                System.out.println("Backbuffer is not integer RGB type");
                this.myBackBufferData = null;
                break;
            }
        }
    }
    
    private void initPalettes() {
        this.myNormalPalette = new int[256];
        this.myBlendedPalette = new int[256][256];
        this.myGrayPalette = new int[256];
    }
    
    protected void clearBuffers() {
        for (int i = 0; i < this.myCurrentFrameBuffer.length; ++i) {
            this.myCurrentFrameBuffer[i] = 0;
            this.myPreviousFrameBuffer[i] = 0;
        }
    }
    
    protected void swapFrameBuffers() {
        final int[] tmp = this.myCurrentFrameBuffer;
        this.myCurrentFrameBuffer = this.myPreviousFrameBuffer;
        this.myPreviousFrameBuffer = tmp;
    }
    
    protected int[] getCurrentFrameBuffer() {
        return this.myCurrentFrameBuffer;
    }
    
    protected int[] getPreviousFrameBuffer() {
        return this.myPreviousFrameBuffer;
    }
    
    protected int getWidth() {
        return this.myConsole.getDisplayWidth();
    }
    
    protected int getHeight() {
        return this.myConsole.getDisplayHeight();
    }
    
    protected void grayCurrentFrame() {
        final boolean zOldMode = this.myGrayPaletteMode;
        this.myGrayPaletteMode = true;
        this.updateVideoFrame();
        this.myGrayPaletteMode = zOldMode;
    }
    
    protected void updateVideoFrame() {
        this.refresh();
        switch (this.myConsole.getTelevisionMode()) {
            case 1: {
                this.doFrameVideo();
                break;
            }
            case 3: {
                this.doSnow();
                break;
            }
            case 2: {
                this.doTestPattern();
                break;
            }
        }
    }
    
    protected BufferedImage getBackBuffer() {
        return this.myBackBuffer;
    }
    
    protected void clearBackBuffer() {
        final Graphics2D z2D = this.myBackBuffer.createGraphics();
        z2D.setColor(Color.BLACK);
        z2D.fillRect(0, 0, this.myBackBuffer.getWidth(), this.myBackBuffer.getHeight());
        z2D.dispose();
    }
    
    protected void adjustBackBuffer(final int aNewWidth, final int aNewHeight) {
        if (aNewWidth > this.myBackBuffer.getWidth() || aNewHeight > this.myBackBuffer.getHeight()) {
            this.initBackBuffer(Math.max(this.myBackBuffer.getWidth(), aNewWidth), Math.max(this.myBackBuffer.getHeight(), aNewHeight));
        }
    }
    
    protected void initialize() {
        this.setTIAPalette(JSConstants.PALETTE_NTSC);
    }
    
    protected void refresh() {
        this.myRedrawTIAIndicator = true;
    }
    
    private void loadImages() {
        final URL zTestPatternURL = this.getClass().getResource("/jstella/resources/testpattern.gif");
        if (zTestPatternURL != null) {
            this.myTestPattern = new ImageIcon(zTestPatternURL);
        }
    }
    
    protected void doSnow() {
        if (this.myBackBuffer != null) {
            this.snowBackBuffer();
            if (this.getCanvas() != null) {
                this.getCanvas().paintCanvas(this.myBackBuffer, this.myBackBuffer.getWidth(), this.myBackBuffer.getHeight());
            }
        }
    }
    
    private void snowBackBuffer() {
        if (this.myBackBuffer != null) {
            final int zWidth = this.myBackBuffer.getWidth();
            for (int zHeight = this.myBackBuffer.getHeight(), iY = 0; iY < zHeight; ++iY) {
                for (int iX = 0; iX < zWidth; ++iX) {
                    final int zRandomValue = Math.min((int)(JSVideo.myRandomGenerator.nextDouble() * 256.0), 255);
                    this.myBackBuffer.setRGB(iX, iY, JSVideo.PALETTE_GRAY_STANDARD[zRandomValue]);
                }
            }
        }
    }
    
    private IfcCanvas getCanvas() {
        if (this.myConsole.getConsoleClient() != null) {
            return this.myConsole.getConsoleClient().getCanvas();
        }
        return null;
    }
    
    protected void doFrameVideo() {
        this.prepareBackBuffer();
        this.paintBackBufferToCanvas();
    }
    
    private void prepareBackBuffer() {
        final int[] zCurrentBuffer = this.getCurrentFrameBuffer();
        final int[] zPrevBuffer = this.getPreviousFrameBuffer();
        if (this.myResidualColorBuffer == null) {
            this.myResidualColorBuffer = new int[zCurrentBuffer.length];
        }
        final int zWidth = Math.min(this.getWidth(), this.myBackBuffer.getWidth());
        final int zHeight = Math.min(this.getHeight(), this.myBackBuffer.getHeight());
        int zBufferIndexAtLineStart = 0;
        for (int y = 0; y < zHeight; ++y) {
            for (int x = 0; x < zWidth; ++x) {
                final int zBufferIndex = zBufferIndexAtLineStart + x;
                final int zNewColorIndex = zCurrentBuffer[zBufferIndex];
                final int zOldColorIndex = zPrevBuffer[zBufferIndex];
                final int zOldPaintedColor = this.myResidualColorBuffer[zBufferIndex];
                final int zNewPaintedColor = this.myUsePhosphor ? this.getBlendedColorInt(zOldColorIndex, zNewColorIndex) : this.getColorInt(zNewColorIndex);
                if (zNewPaintedColor != zOldPaintedColor || this.myRedrawTIAIndicator) {
                    this.myClipRect.addPoint(x, y);
                    this.myResidualColorBuffer[zBufferIndex] = zNewPaintedColor;
                    if (this.myBackBufferData != null) {
                        this.myBackBufferData[zBufferIndex] = zNewPaintedColor;
                    }
                    else {
                        this.myBackBuffer.setRGB(x, y, zNewPaintedColor);
                    }
                }
            }
            zBufferIndexAtLineStart += zWidth;
        }
        this.myRedrawTIAIndicator = false;
    }
    
    private void paintBackBufferToCanvas() {
        if (this.getCanvas() != null) {
            this.getCanvas().paintCanvas(this.myBackBuffer, this.getWidth(), this.getHeight(), this.myClipRect);
            this.myClipRect.resetRect();
        }
    }
    
    protected void doTestPattern() {
        final Graphics2D z2D = this.myBackBuffer.createGraphics();
        final double zScaleX = this.myBackBuffer.getWidth() / this.myTestPattern.getIconWidth();
        final double zScaleY = this.myBackBuffer.getHeight() / this.myTestPattern.getIconHeight();
        final double zScale = Math.min(zScaleX, zScaleY);
        final int zWidth = (int)(this.myTestPattern.getIconWidth() * zScale);
        final int zHeight = (int)(this.myTestPattern.getIconHeight() * zScale);
        z2D.drawImage(this.myTestPattern.getImage(), 0, 0, zWidth, zHeight, 0, 0, this.myTestPattern.getIconWidth(), this.myTestPattern.getIconHeight(), null);
        z2D.dispose();
        if (this.getCanvas() != null) {
            this.myClipRect.resetRect();
            this.getCanvas().paintCanvas(this.myBackBuffer, zWidth, zHeight, this.myClipRect);
        }
    }
    
    protected boolean getPhosphorEnabled() {
        return this.myUsePhosphor;
    }
    
    protected void setPhosphorEnabled(final boolean aEnable) {
        this.myUsePhosphor = aEnable;
    }
    
    protected void setPhosphorEnabled(final boolean aEnable, final int aBlendPercent) {
        this.setPhosphorEnabled(aEnable);
        this.myPhosphorBlendPercent = aBlendPercent;
    }
    
    private int getColorInt(final int aIndex) {
        if (this.myGrayPaletteMode) {
            return this.myGrayPalette[aIndex & 0xFF];
        }
        return this.myNormalPalette[aIndex & 0xFF];
    }
    
    private int getBlendedColorInt(final int aOldIndex, final int aNewIndex) {
        return this.myBlendedPalette[aOldIndex & 0xFF][aNewIndex & 0xFF];
    }
    
    protected void setTIAPalette(final int[] palette) {
        for (int i = 0; i < 256; ++i) {
            final int r = palette[i] >> 16 & 0xFF;
            final int g = palette[i] >> 8 & 0xFF;
            final int b = palette[i] & 0xFF;
            this.myNormalPalette[i] = calculateNormalColor(r, g, b);
            this.myGrayPalette[i] = calculateGrayColor(r, g, b);
        }
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 256; ++j) {
                final int ri = palette[i] >> 16 & 0xFF;
                final int gi = palette[i] >> 8 & 0xFF;
                final int bi = palette[i] & 0xFF;
                final int rj = palette[j] >> 16 & 0xFF;
                final int gj = palette[j] >> 8 & 0xFF;
                final int bj = palette[j] & 0xFF;
                final int r2 = calculatePhosphorColor(ri, rj, this.myPhosphorBlendPercent);
                final int g2 = calculatePhosphorColor(gi, gj, this.myPhosphorBlendPercent);
                final int b2 = calculatePhosphorColor(bi, bj, this.myPhosphorBlendPercent);
                this.myBlendedPalette[i][j] = calculateNormalColor(r2, g2, b2);
            }
        }
        this.myRedrawTIAIndicator = true;
    }
    
    private static int calculateNormalColor(final int r, final int g, final int b) {
        assert r >= 0 && g >= 0 && b >= 0;
        return new Color(r, g, b).getRGB();
    }
    
    private static int calculatePhosphorColor(final int aColorComponentA, final int aColorComponentB, final int aPhosphorBlend) {
        final int zDifference = Math.abs(aColorComponentA - aColorComponentB);
        final double zBlendFactor = aPhosphorBlend / 100.0;
        final int zPhosp = Math.min(aColorComponentA, aColorComponentB) + (int)(zBlendFactor * zDifference);
        assert zPhosp >= 0 && zPhosp < 256;
        return zPhosp;
    }
    
    private static int calculateGrayColor(final int aRed, final int aGreen, final int aBlue) {
        final int zAverage = (aRed + aGreen + aBlue) / 3;
        return calculateNormalColor(zAverage, zAverage, zAverage);
    }
    
    static {
        JSVideo.myRandomGenerator = new Random();
        PALETTE_GRAY_STANDARD = new int[256];
        for (int i = 0; i < 256; ++i) {
            JSVideo.PALETTE_GRAY_STANDARD[i] = new Color(i, i, i).getRGB();
        }
    }
    
    private class ClipRectangle extends Rectangle
    {
        private static final long serialVersionUID = -5127735308538463352L;
        private boolean isClear;
        
        private ClipRectangle() {
            this.isClear = true;
        }
        
        public void resetRect() {
            this.isClear = true;
            this.x = 0;
            this.y = 0;
            this.width = 0;
            this.height = 0;
        }
        
        public void addPoint(final int aX, final int aY) {
            if (this.isClear) {
                this.x = aX - 1;
                this.y = aY - 1;
                this.width = 3;
                this.height = 3;
                this.isClear = false;
            }
            else {
                if (aX >= this.x + this.width) {
                    this.width = aX - this.x + 2;
                }
                else if (aX <= this.x) {
                    this.width += this.x - aX + 2;
                    this.x = aX - 1;
                }
                if (aY >= this.y + this.height) {
                    this.height = aY - this.y + 2;
                }
                else if (aY <= this.y) {
                    this.height += this.y - aY + 2;
                    this.y = aY - 1;
                }
            }
        }
    }
}
