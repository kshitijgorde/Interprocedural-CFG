// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.io.InputStream;
import java.net.URL;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.Component;

public class DdiImageViewer extends Component
{
    private Image currImage;
    private boolean animated;
    private int viewerStyle;
    private Image animatedImage;
    private Image stillImage;
    private FrameFilter stillImageFilter;
    private FilteredImageSource stillImageSource;
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_CENTERED = 1;
    public static final int STYLE_SCALED = 2;
    static /* synthetic */ Class class$com$mobius$awt$DdiImageViewer;
    
    public DdiImageViewer() {
        this.currImage = null;
        this.animatedImage = null;
        this.stillImage = null;
        this.stillImageFilter = null;
        this.stillImageSource = null;
        this.viewerStyle = 0;
        this.animated = false;
    }
    
    public DdiImageViewer(final URL url) {
        this();
        this.loadImage(url);
    }
    
    public DdiImageViewer(final String s) {
        this();
        this.loadImageFromJar(s);
    }
    
    public void setStyle(final int viewerStyle) {
        this.viewerStyle = viewerStyle;
        this.repaint();
    }
    
    public int getStyle() {
        return this.viewerStyle;
    }
    
    public void setAnimated(final boolean animated) {
        this.animated = animated;
        if (this.animated) {
            this.currImage = this.animatedImage;
        }
        else {
            this.flushImage(this.animatedImage);
            this.currImage = this.stillImage;
        }
        this.repaint();
    }
    
    public boolean isAnimated() {
        return this.animated;
    }
    
    public void setImage(final URL url) {
        this.loadImage(url);
    }
    
    public void setImage(final String s) {
        this.loadImageFromJar(s);
    }
    
    private void loadImage(final URL url) {
        try {
            if (url != null && !url.getFile().equals("/")) {
                this.animatedImage = this.getToolkit().getImage(url);
                this.getFilteredImage();
            }
        }
        catch (Exception ex) {
            System.out.println("Error: Image not Loaded");
        }
        this.repaint();
    }
    
    private void loadImageFromJar(final String s) {
        try {
            if (s != null) {
                final InputStream resourceAsStream = ((DdiImageViewer.class$com$mobius$awt$DdiImageViewer == null) ? (DdiImageViewer.class$com$mobius$awt$DdiImageViewer = class$("com.mobius.awt.DdiImageViewer")) : DdiImageViewer.class$com$mobius$awt$DdiImageViewer).getResourceAsStream(s);
                final byte[] array = new byte[resourceAsStream.available()];
                resourceAsStream.read(array);
                this.animatedImage = this.getToolkit().createImage(array);
                this.getFilteredImage();
            }
        }
        catch (Exception ex) {
            System.out.println("Error: Image not Loaded - " + s);
        }
        this.repaint();
    }
    
    private void getFilteredImage() {
        if (this.spoolImage(this.animatedImage) != null) {
            this.currImage = this.animatedImage;
            this.stillImageFilter = new FrameFilter(false);
            this.stillImageSource = new FilteredImageSource(this.animatedImage.getSource(), this.stillImageFilter);
            if (this.stillImageSource != null) {
                this.stillImage = this.createImage(this.stillImageSource);
                if (this.spoolImage(this.stillImage) != null) {
                    this.currImage = this.stillImage;
                }
            }
        }
    }
    
    private void flushImage(final Image image) {
        try {
            if (image != null) {
                image.flush();
            }
        }
        catch (Exception ex) {}
    }
    
    public void flushImage() {
        this.flushImage(this.animatedImage);
        this.flushImage(this.stillImage);
    }
    
    private Image spoolImage(final Image image) {
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            if (mediaTracker != null) {
                try {
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(0)) {
                    System.err.println("Error Loading Image: " + image.toString());
                    return null;
                }
                return image;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.isVisible() && this.currImage != null) {
            switch (this.viewerStyle) {
                default: {
                    graphics.drawImage(this.currImage, 0, 0, this);
                    break;
                }
                case 1: {
                    final int width = this.currImage.getWidth(this);
                    final int height = this.currImage.getHeight(this);
                    graphics.drawImage(this.currImage, (size.width - width) / 2, (size.height - height) / 2, width, height, this);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.currImage, 0, 0, size.width, size.height, this);
                    break;
                }
            }
        }
        else {
            graphics.clearRect(0, 0, size.width, size.height);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    class FrameFilter extends ImageFilter
    {
        private int frameCounter;
        private int targetFrame;
        private boolean animated;
        
        FrameFilter(final boolean animated) {
            this.frameCounter = 1;
            this.targetFrame = 1;
            this.animated = animated;
        }
        
        FrameFilter(final boolean animated, final int targetFrame) {
            this.frameCounter = 1;
            this.targetFrame = targetFrame;
            this.animated = animated;
        }
        
        public void setAnimated(final boolean animated) {
            this.animated = animated;
        }
        
        public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
            if (!this.animated || this.targetFrame == this.frameCounter) {
                super.consumer.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
            }
        }
        
        public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
            if (!this.animated || this.targetFrame == this.frameCounter) {
                super.consumer.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
            }
        }
        
        public void imageComplete(int n) {
            if (!this.animated) {
                switch (n) {
                    case 16: {
                        this.targetFrame = 1;
                        break;
                    }
                    case 2: {
                        if (this.frameCounter < this.targetFrame) {
                            ++this.frameCounter;
                            return;
                        }
                        n = 3;
                        break;
                    }
                }
            }
            super.consumer.imageComplete(n);
        }
    }
}
