import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class AniCanvas extends Canvas
{
    Image[] baseImages;
    Image[][] portalImages;
    Image[][] overlayImages;
    Image fimg;
    Image offscreen;
    Graphics grOff;
    Graphics[] gr;
    int[] portalHeight;
    int[] portalWidth;
    int[] portalX;
    int[] portalY;
    int[] overlayX;
    int[] overlayY;
    int[] imageWidth;
    int[] imageHeight;
    boolean[] portalScaling;
    int baseWidth;
    int baseHeight;
    int xMouse;
    int yMouse;
    int xDragStart;
    int yDragStart;
    boolean hasPortals;
    boolean hasOverlays;
    boolean hasImages;
    boolean forceImage;
    boolean dragging;
    boolean drawingLine;
    boolean blank_screen;
    Color background;
    int currentFrame;
    int numOverlays;
    int numPortals;
    int[] preserve;
    Checkbox[] overlay;
    int[] overlayOrder;
    boolean[] overlayZoom;
    boolean doWaitMessage;
    String waitMessage;
    String forcedMessage;
    AniS parent;
    int pcount;
    int z_width;
    int z_height;
    int z_xoff;
    int z_yoff;
    int z_xend;
    int z_yend;
    int zoomFactor;
    int xOffset;
    int yOffset;
    boolean isZoomed;
    boolean canZoom;
    boolean roamStop;
    boolean useProgressBar;
    int gotFrames;
    int totalFrames;
    Dimension dim;
    String noImagesYet;
    boolean showPortalPointers;
    double[][] latlon;
    boolean doingNav;
    
    public AniCanvas(final AniS parent, final boolean blank_screen, final Color background, final boolean roamStop, final int[] preserve, final String noImagesYet) {
        this.baseImages = null;
        this.grOff = null;
        this.gr = null;
        this.xMouse = 0;
        this.yMouse = 0;
        this.xDragStart = 0;
        this.yDragStart = 0;
        this.hasPortals = false;
        this.hasOverlays = false;
        this.hasImages = false;
        this.forceImage = false;
        this.dragging = false;
        this.drawingLine = false;
        this.blank_screen = false;
        this.currentFrame = -1;
        this.numOverlays = 0;
        this.numPortals = 0;
        this.preserve = null;
        this.overlay = null;
        this.overlayOrder = null;
        this.overlayZoom = null;
        this.doWaitMessage = false;
        this.parent = null;
        this.pcount = 0;
        this.doingNav = false;
        this.blank_screen = blank_screen;
        this.background = background;
        this.canZoom = false;
        this.roamStop = roamStop;
        this.resetZoom();
        this.baseWidth = -1;
        this.baseHeight = -1;
        this.parent = parent;
        this.preserve = preserve;
        this.useProgressBar = false;
        this.noImagesYet = noImagesYet;
        this.showPortalPointers = true;
    }
    
    public void setLatLonCorners(final boolean doingNav, final double[][] latlon) {
        this.doingNav = doingNav;
        this.latlon = latlon;
    }
    
    public boolean getZoomState() {
        return this.isZoomed;
    }
    
    public void setShowPortalPointers(final boolean showPortalPointers) {
        this.showPortalPointers = showPortalPointers;
    }
    
    public void setCanZoom(final boolean canZoom) {
        this.canZoom = canZoom;
    }
    
    public void resetZoom() {
        this.zoomFactor = 1;
        this.xOffset = 0;
        this.yOffset = 0;
        this.isZoomed = false;
        this.z_xoff = 0;
        this.z_yoff = 0;
        this.z_xend = this.baseWidth;
        this.z_yend = this.baseHeight;
        if (this.parent != null) {
            this.parent.setUnzoomed();
        }
    }
    
    public void setPortals(final Image[][] portalImages, final int[] portalWidth, final int[] portalHeight, final int[] portalX, final int[] portalY, final boolean[] portalScaling) {
        this.portalImages = portalImages;
        this.portalHeight = portalHeight;
        this.portalWidth = portalWidth;
        this.portalX = portalX;
        this.portalY = portalY;
        this.portalScaling = portalScaling;
        this.numPortals = this.portalImages.length;
        this.hasPortals = true;
        this.gr = null;
        this.repaint();
    }
    
    public void pleaseWaitMessage(final String waitMessage) {
        this.waitMessage = waitMessage;
        this.doWaitMessage = true;
        this.repaint();
    }
    
    public void resize(final int z_width, final int z_height) {
        super.resize(z_width, z_height);
        this.z_width = z_width;
        this.z_height = z_height;
    }
    
    public void setProgress(final boolean useProgressBar, final int gotFrames, final int totalFrames) {
        this.useProgressBar = useProgressBar;
        this.gotFrames = gotFrames;
        this.totalFrames = totalFrames;
    }
    
    public void setOverlayOrder(final int[] overlayOrder) {
        this.overlayOrder = overlayOrder;
    }
    
    public void setOverlayZoom(final boolean[] overlayZoom) {
        this.overlayZoom = overlayZoom;
    }
    
    public void setCurrentFrame(final int currentFrame) {
        this.currentFrame = currentFrame;
        this.paint(this.getGraphics());
    }
    
    public void setForcedImage(final Image fimg, final String forcedMessage) {
        if (fimg == null) {
            return;
        }
        this.fimg = fimg;
        this.forcedMessage = forcedMessage;
        this.forceImage = true;
        this.paint(this.getGraphics());
    }
    
    public synchronized void setBaseImages(final Image[] baseImages, final int baseWidth, final int baseHeight) {
        this.hasImages = false;
        this.doWaitMessage = false;
        if (this.offscreen != null) {
            this.offscreen.flush();
        }
        if (this.grOff != null) {
            this.grOff.dispose();
        }
        if (this.baseWidth < 0 || this.baseHeight < 0) {
            this.baseWidth = baseWidth;
            this.baseHeight = baseHeight;
            this.resetZoom();
        }
        if (this.xMouse == 0) {
            this.xMouse = this.baseWidth / 2;
            this.yMouse = this.baseHeight / 2;
        }
        this.dim = this.size();
        this.offscreen = this.createImage(this.dim.width, this.dim.height);
        this.grOff = this.offscreen.getGraphics();
        if (this.gr != null) {
            for (int i = 0; i < this.gr.length; ++i) {
                this.gr[i].dispose();
            }
        }
        this.gr = null;
        this.baseImages = baseImages;
        this.hasImages = true;
        this.paint(this.getGraphics());
    }
    
    public void setOverlays(final Image[][] overlayImages, final Checkbox[] overlay, final int[] overlayX, final int[] overlayY) {
        this.overlayImages = overlayImages;
        this.overlay = overlay;
        this.numOverlays = overlay.length;
        this.hasOverlays = true;
        this.doWaitMessage = false;
        this.overlayX = overlayX;
        this.overlayY = overlayY;
    }
    
    public void setOverlayImages(final Image[][] overlayImages) {
        this.overlayImages = overlayImages;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.xDragStart = n;
        this.yDragStart = n2;
        this.xMouse = n;
        this.yMouse = n2;
        if (event.shiftDown()) {
            this.drawingLine = true;
        }
        else {
            this.drawingLine = false;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int xMouse, final int yMouse) {
        this.xMouse = xMouse;
        this.yMouse = yMouse;
        if (this.drawingLine) {
            this.repaint();
            return true;
        }
        if (!this.dragging && (Math.abs(this.xDragStart - this.xMouse) > 2 || Math.abs(this.yDragStart - this.yMouse) > 2)) {
            this.dragging = true;
        }
        if (this.hasPortals) {
            this.repaint();
        }
        else if (this.canZoom && this.isZoomed) {
            this.xOffset = (xMouse - this.xDragStart) * this.baseWidth / this.zoomFactor / this.dim.width;
            this.yOffset = (yMouse - this.yDragStart) * this.baseHeight / this.zoomFactor / this.dim.height;
            if (this.roamStop) {
                if (this.xOffset > this.z_xoff) {
                    this.xOffset = this.z_xoff;
                }
                if (this.yOffset > this.z_yoff) {
                    this.yOffset = this.z_yoff;
                }
                if (this.z_xend - this.xOffset > this.baseWidth) {
                    this.xOffset = this.z_xend - this.baseWidth;
                }
                if (this.z_yend - this.yOffset > this.baseHeight) {
                    this.yOffset = this.z_yend - this.baseHeight;
                }
            }
            this.paint(this.getGraphics());
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int xMouse, final int yMouse) {
        this.xMouse = xMouse;
        this.yMouse = yMouse;
        if (this.drawingLine) {
            this.drawingLine = false;
            this.repaint();
            return true;
        }
        if (this.dragging) {
            this.z_xoff -= this.xOffset;
            this.z_yoff -= this.yOffset;
            this.z_xend -= this.xOffset;
            this.z_yend -= this.yOffset;
            this.xOffset = 0;
            this.yOffset = 0;
            this.dragging = false;
            this.paint(this.getGraphics());
            return true;
        }
        if (this.canZoom) {
            if ((event.modifiers & 0x4) == 0x4) {
                if (this.zoomFactor <= 2 || (event.modifiers & 0x8) == 0x8) {
                    this.resetZoom();
                    this.repaint();
                    return true;
                }
                this.z_xoff = this.z_xoff - xMouse * this.baseWidth / (this.zoomFactor - 1) / this.dim.width + xMouse * this.baseWidth / this.zoomFactor / this.dim.width;
                this.z_yoff = this.z_yoff - yMouse * this.baseHeight / (this.zoomFactor - 1) / this.dim.height + yMouse * this.baseHeight / this.zoomFactor / this.dim.height;
                --this.zoomFactor;
            }
            else {
                if ((event.modifiers & 0x8) == 0x8) {
                    this.resetZoom();
                    this.repaint();
                    return true;
                }
                ++this.zoomFactor;
                this.z_xoff = this.z_xoff + xMouse * this.baseWidth / (this.zoomFactor - 1) / this.dim.width - xMouse * this.baseWidth / this.zoomFactor / this.dim.width;
                this.z_yoff = this.z_yoff + yMouse * this.baseHeight / (this.zoomFactor - 1) / this.dim.height - yMouse * this.baseHeight / this.zoomFactor / this.dim.height;
            }
            this.z_xend = this.z_xoff + this.baseWidth / this.zoomFactor;
            this.z_yend = this.z_yoff + this.baseHeight / this.zoomFactor;
            this.isZoomed = true;
            if (this.roamStop) {
                if (this.xOffset > this.z_xoff) {
                    this.xOffset = this.z_xoff;
                }
                if (this.yOffset > this.z_yoff) {
                    this.yOffset = this.z_yoff;
                }
                if (this.z_xend - this.xOffset > this.baseWidth) {
                    this.xOffset = this.z_xend - this.baseWidth;
                }
                if (this.z_yend - this.yOffset > this.baseHeight) {
                    this.yOffset = this.z_yend - this.baseHeight;
                }
            }
        }
        this.dragging = false;
        this.repaint();
        return true;
    }
    
    void showProgressBar(final Graphics graphics) {
        if (!this.useProgressBar || this.totalFrames <= 0) {
            return;
        }
        if (graphics == null) {
            return;
        }
        final Dimension size = this.size();
        final int n = size.width / 3;
        graphics.setColor(Color.black);
        graphics.fillRect(size.width / 2 - n / 2, size.height / 2, n, 19);
        graphics.setColor(Color.white);
        graphics.drawRect(size.width / 2 - n / 2 + 5, size.height / 2 + 4, n - 10, 10);
        graphics.setColor(Color.orange);
        graphics.fillRect(size.width / 2 - n / 2 + 6, size.height / 2 + 5, (n - 10) * this.gotFrames / this.totalFrames, 9);
    }
    
    void paintMyMessage(final Graphics graphics, final String s) {
        if (graphics == null) {
            return;
        }
        if (s == null) {
            return;
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth(s);
        final Dimension size = this.size();
        graphics.setColor(Color.black);
        graphics.fillRect(size.width / 2 - stringWidth / 2 - 10, size.height / 2 - height / 2 - 5, stringWidth + 20, height + 10);
        graphics.setColor(Color.white);
        graphics.drawString(s, size.width / 2 - stringWidth / 2, size.height / 2 + height / 2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        ++this.pcount;
        if (graphics == null) {
            return;
        }
        if (this.grOff == null) {
            final Dimension size = this.size();
            this.offscreen = this.createImage(size.width, size.height);
            this.grOff = this.offscreen.getGraphics();
        }
        if (this.forceImage) {
            if (this.fimg != null) {
                if (this.offscreen == null) {
                    this.offscreen = this.createImage(this.fimg.getWidth(this.parent), this.fimg.getHeight(this.parent));
                }
                (this.grOff = this.offscreen.getGraphics()).drawImage(this.fimg, 0, 0, null);
            }
            this.forceImage = false;
            if (this.useProgressBar) {
                this.showProgressBar(this.grOff);
            }
            else {
                this.paintMyMessage(this.grOff, this.forcedMessage);
            }
            graphics.drawImage(this.offscreen, 0, 0, null);
            if (!this.doWaitMessage) {
                return;
            }
        }
        if (this.doWaitMessage) {
            if (this.useProgressBar) {
                this.showProgressBar(this.grOff);
            }
            else {
                this.paintMyMessage(this.grOff, this.waitMessage);
            }
            graphics.drawImage(this.offscreen, 0, 0, null);
            return;
        }
        if (!this.hasImages) {
            if (this.useProgressBar) {
                this.showProgressBar(this.grOff);
            }
            else {
                this.paintMyMessage(this.grOff, this.noImagesYet);
            }
        }
        else {
            if (this.currentFrame >= 0) {
                if (this.blank_screen) {
                    this.grOff.setColor(this.background);
                    this.grOff.fillRect(0, 0, this.dim.width, this.dim.height);
                }
                if (this.baseImages[this.currentFrame] != null) {
                    this.grOff.setColor(this.background);
                    this.grOff.fillRect(0, 0, this.dim.width, this.dim.height);
                    this.grOff.drawImage(this.baseImages[this.currentFrame], 0, 0, this.z_width, this.z_height, this.z_xoff - this.xOffset, this.z_yoff - this.yOffset, this.z_xend - this.xOffset, this.z_yend - this.yOffset, null);
                }
                if (this.hasOverlays) {
                    for (int i = 0; i < this.numOverlays; ++i) {
                        int n = i;
                        if (this.overlayOrder != null) {
                            n = this.overlayOrder[i];
                        }
                        if (this.overlay[n].getState() && this.overlayImages[n][this.currentFrame] != null) {
                            if (this.overlayX == null) {
                                if (this.overlayZoom[i]) {
                                    this.grOff.drawImage(this.overlayImages[n][this.currentFrame], 0, 0, this.z_width, this.z_height, this.z_xoff - this.xOffset, this.z_yoff - this.yOffset, this.z_xend - this.xOffset, this.z_yend - this.yOffset, null);
                                }
                                else {
                                    this.grOff.drawImage(this.overlayImages[n][this.currentFrame], 0, 0, this.z_width, this.z_height, null);
                                }
                            }
                            else if (this.overlayZoom[i]) {
                                this.grOff.drawImage(this.overlayImages[n][this.currentFrame], this.overlayX[n], this.overlayY[n], this.z_width - this.overlayX[n], this.z_height - this.overlayY[n], this.z_xoff, this.z_yoff, this.z_xend, this.z_yend, null);
                            }
                            else {
                                this.grOff.drawImage(this.overlayImages[n][this.currentFrame], this.overlayX[n], this.overlayY[n], this.z_width, this.z_height, null);
                            }
                        }
                    }
                }
                if (this.hasPortals) {
                    if (this.gr == null) {
                        this.gr = new Graphics[this.numPortals];
                        this.imageWidth = new int[this.numPortals];
                        this.imageHeight = new int[this.numPortals];
                        for (int j = 0; j < this.numPortals; ++j) {
                            this.gr[j] = this.grOff.create(this.portalX[j], this.portalY[j], this.portalWidth[j], this.portalHeight[j]);
                            this.imageWidth[j] = this.portalImages[j][0].getWidth(this.parent);
                            this.imageHeight[j] = this.portalImages[j][0].getHeight(this.parent);
                        }
                    }
                    for (int k = 0; k < this.numPortals; ++k) {
                        this.gr[k].setColor(Color.black);
                        this.gr[k].fillRect(0, 0, this.portalWidth[k], this.portalHeight[k]);
                        if (this.isZoomed) {
                            int n2 = this.z_xoff + this.xMouse / this.zoomFactor - this.portalWidth[k] / 2 * this.imageWidth[k] / this.zoomFactor / this.baseWidth;
                            int n3 = this.z_yoff + this.yMouse / this.zoomFactor - this.portalHeight[k] / 2 * this.imageHeight[k] / this.zoomFactor / this.baseHeight;
                            int n4 = 0;
                            int n5 = 0;
                            if (n2 < 0) {
                                n4 = -n2;
                                n2 = 0;
                            }
                            if (n3 < 0) {
                                n5 = -n3;
                                n3 = 0;
                            }
                            this.gr[k].drawImage(this.portalImages[k][this.currentFrame], n4, n5, this.portalWidth[k], this.portalHeight[k], n2, n3, n2 + (this.portalWidth[k] - n4) / this.zoomFactor, n3 + (this.portalHeight[k] - n5) / this.zoomFactor, null);
                        }
                        else if (this.portalScaling[k]) {
                            this.gr[k].drawImage(this.portalImages[k][this.currentFrame], 0, 0, this.portalWidth[k], this.portalHeight[k], null);
                        }
                        else {
                            this.gr[k].drawImage(this.portalImages[k][this.currentFrame], -this.xMouse * 100 * this.imageWidth[k] / this.baseWidth / 100 + this.portalWidth[k] / 2, -this.yMouse * 100 * this.imageHeight[k] / this.baseHeight / 100 + this.portalHeight[k] / 2, null);
                        }
                        this.gr[k].setColor(Color.black);
                        this.gr[k].drawRect(0, 0, this.portalWidth[k] - 1, this.portalHeight[k] - 1);
                        if (this.showPortalPointers) {
                            this.gr[k].drawRect(this.portalWidth[k] / 2 - 3, this.portalHeight[k] / 2 - 3, 7, 7);
                            this.gr[k].setColor(Color.white);
                            this.gr[k].drawRect(this.portalWidth[k] / 2 - 2, this.portalHeight[k] / 2 - 2, 5, 5);
                        }
                    }
                    if (this.showPortalPointers) {
                        this.grOff.setColor(Color.white);
                        this.grOff.drawRect(this.xMouse - 3, this.yMouse - 3, 7, 7);
                        this.grOff.setColor(Color.black);
                        this.grOff.drawRect(this.xMouse - 2, this.yMouse - 2, 5, 5);
                    }
                }
                if (this.preserve != null && this.baseImages[this.currentFrame] != null) {
                    for (int length = this.preserve.length, l = 0; l < length; l += 4) {
                        this.grOff.drawImage(this.baseImages[this.currentFrame], this.preserve[0 + l], this.preserve[1 + l], this.preserve[2 + l], this.preserve[3 + l], this.preserve[0 + l], this.preserve[1 + l], this.preserve[2 + l], this.preserve[3 + l], null);
                    }
                }
            }
            if (this.drawingLine) {
                this.grOff.setColor(Color.white);
                this.grOff.drawLine(this.xDragStart, this.yDragStart, this.xMouse, this.yMouse);
            }
            if (this.doingNav) {
                final double n6 = this.latlon[0][0] - (this.latlon[0][0] - this.latlon[1][0]) * (this.yMouse / this.dim.height);
                final double n7 = this.latlon[0][1] - (this.latlon[0][1] - this.latlon[1][1]) * (this.yMouse / this.dim.width);
                final String value = String.valueOf(n6);
                final int index = value.indexOf(".");
                String s;
                if (index != -1) {
                    s = (value + "000").substring(0, index + 2);
                }
                else {
                    s = value + ".00";
                }
                final String value2 = String.valueOf(n7);
                final int index2 = value2.indexOf(".");
                String s2;
                if (index2 != -1) {
                    s2 = (value2 + "000").substring(0, index2 + 2);
                }
                else {
                    s2 = value2 + ".00";
                }
                this.grOff.setColor(Color.white);
                this.grOff.drawString(s + "    " + s2, this.xMouse, this.yMouse);
            }
        }
        if (this.useProgressBar) {
            this.showProgressBar(this.grOff);
        }
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
}
