// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.image.RGBImageFilter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.AWTEventMulticaster;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.InputStream;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Color;
import com.mobius.tools.treeview.IHandleToolTips;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

public class DdiImageButton extends Canvas
{
    protected Image enabledImage;
    protected Image disabledImage;
    protected Image grayedImage;
    protected Image buttonImage;
    private int bevelWidth;
    private Dimension dfltSize;
    private String tooltip;
    private static IHandleToolTips ttHandle;
    Color flatColorPressed;
    Color tlColorPressed;
    Color brColorPressed;
    Color flatColorRaised;
    Color tlColorRaised;
    Color brColorRaised;
    Color flatColorDisabled;
    Color tlColorDisabled;
    Color brColorDisabled;
    protected boolean buttonPressed;
    protected ActionListener buttonListener;
    static /* synthetic */ Class class$com$mobius$awt$DdiImageButton;
    
    public DdiImageButton() {
        this.tooltip = null;
        this.enabledImage = null;
        this.disabledImage = null;
        this.grayedImage = null;
        this.buttonImage = null;
        this.setBevelWidth(2);
        this.buttonPressed = false;
        this.setColor(Color.lightGray);
        this.addMouseListener(new ButtonHandler());
    }
    
    public void setEnabled(final boolean b) {
        if (this.isEnabled() != b) {
            if (b) {
                super.enable();
            }
            else {
                super.disable();
            }
            this.repaint();
        }
    }
    
    public void enable() {
        this.setEnabled(true);
    }
    
    public void disable() {
        this.setEnabled(false);
    }
    
    public void setBevelWidth(final int bevelWidth) {
        this.bevelWidth = bevelWidth;
        this.dfltSize = new Dimension(bevelWidth * 2 + 2, bevelWidth * 2 + 2);
    }
    
    public int getBevelWidth() {
        return this.bevelWidth;
    }
    
    public void setButtonColor(final Color background) {
        this.setBackground(background);
    }
    
    public Color getButtonColor() {
        return this.getBackground();
    }
    
    public Dimension getPreferredSize() {
        if (this.enabledImage == null) {
            return this.dfltSize;
        }
        return new Dimension(this.dfltSize.width + this.enabledImage.getWidth(this), this.dfltSize.height + this.enabledImage.getHeight(this));
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public void setEnabledImage(final URL url) {
        this.setEnabledImage(this.loadImage(url));
    }
    
    public void setEnabledImage(final String s) {
        this.setEnabledImage(s, false);
    }
    
    public void setEnabledImage(final String s, final boolean b) {
        Image enabledImage;
        if (b) {
            enabledImage = this.loadImageFromJar(s);
        }
        else {
            enabledImage = this.loadImage(s);
        }
        this.setEnabledImage(enabledImage);
    }
    
    public void setEnabledImage(final Image enabledImage) {
        if (enabledImage != null) {
            this.flushImage(this.enabledImage);
            this.flushImage(this.grayedImage);
            this.enabledImage = enabledImage;
            this.grayedImage = null;
            this.repaint();
        }
    }
    
    public void setDisabledImage(final URL url) {
        this.setDisabledImage(this.loadImage(url));
    }
    
    public void setDisabledImage(final Image disabledImage) {
        if (disabledImage != null) {
            this.flushImage(this.disabledImage);
            this.flushImage(this.grayedImage);
            this.disabledImage = disabledImage;
            this.grayedImage = null;
            this.repaint();
        }
    }
    
    public void setDisabledImage(final String s) {
        this.setDisabledImage(s, false);
    }
    
    public void setDisabledImage(final String s, final boolean b) {
        Image disabledImage;
        if (b) {
            disabledImage = this.loadImageFromJar(s);
        }
        else {
            disabledImage = this.loadImage(s);
        }
        this.setDisabledImage(disabledImage);
    }
    
    private Image loadImage(final URL url) {
        if (url != null && !url.getFile().equals("/")) {
            final Image image = this.getToolkit().getImage(url);
            if (this.spoolImage(image) != null) {
                return image;
            }
        }
        return null;
    }
    
    private Image loadImage(final String s) {
        if (s != null) {
            final Image image = this.getToolkit().getImage(s);
            if (this.spoolImage(image) != null) {
                return image;
            }
        }
        return null;
    }
    
    private Image loadImageFromJar(final String s) {
        if (s != null) {
            try {
                final InputStream resourceAsStream = ((DdiImageButton.class$com$mobius$awt$DdiImageButton == null) ? (DdiImageButton.class$com$mobius$awt$DdiImageButton = class$("com.mobius.awt.DdiImageButton")) : DdiImageButton.class$com$mobius$awt$DdiImageButton).getResourceAsStream(s);
                final byte[] array = new byte[resourceAsStream.available()];
                resourceAsStream.read(array);
                final Image image = this.getToolkit().createImage(array);
                if (this.spoolImage(image) != null) {
                    return image;
                }
            }
            catch (Exception ex) {}
        }
        return null;
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
                if (mediaTracker.isErrorAny()) {
                    System.err.println("Error Loading Image: " + image.toString());
                    return null;
                }
                return image;
            }
        }
        return null;
    }
    
    private void flushImage(final Image image) {
        try {
            if (image != null) {
                image.flush();
            }
        }
        catch (Exception ex) {}
    }
    
    public void setColor(final Color flatColorRaised) {
        this.flatColorRaised = flatColorRaised;
        this.tlColorRaised = this.lighterColor(flatColorRaised, 60);
        this.brColorRaised = this.darkerColor(flatColorRaised, 30);
        this.flatColorPressed = this.darkerColor(flatColorRaised, 20);
        this.tlColorPressed = this.darkerColor(flatColorRaised, 50);
        this.brColorPressed = this.lighterColor(flatColorRaised, 10);
        this.flatColorDisabled = this.darkerColor(flatColorRaised, 20);
        this.tlColorDisabled = this.lighterColor(flatColorRaised, 40);
        this.brColorDisabled = this.darkerColor(flatColorRaised, 50);
    }
    
    private Color darkerColor(final Color color, final int n) {
        final double n2 = n / 100.0;
        return new Color(Math.max((int)(color.getRed() * (1.0 - n2)), 0), Math.max((int)(color.getGreen() * (1.0 - n2)), 0), Math.max((int)(color.getBlue() * (1.0 - n2)), 0));
    }
    
    private Color lighterColor(final Color color, final int n) {
        final double n2 = n / 100.0;
        final int red = color.getRed();
        final int green = color.getGreen();
        color.getBlue();
        return new Color(red + (int)((255 - red) * n2), green + (int)((255 - green) * n2), green + (int)((255 - green) * n2));
    }
    
    protected void makeImage() {
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        final int n = this.bevelWidth + 1;
        final int n2 = this.bevelWidth + 1;
        final int n3 = width - 1;
        final int n4 = height - 1;
        if (this.isInvalid()) {
            this.buttonImage = this.createImage(width, height);
            try {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(this.buttonImage, 0);
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics = this.buttonImage.getGraphics();
        if (graphics.getClip() == null) {
            graphics.clipRect(0, 0, width, height);
        }
        final Color color = graphics.getColor();
        Color color2;
        Color color3;
        Color color4;
        if (this.isEnabled()) {
            if (this.buttonPressed) {
                color2 = this.flatColorPressed;
                color3 = this.tlColorPressed;
                color4 = this.brColorPressed;
            }
            else {
                color2 = this.flatColorRaised;
                color3 = this.tlColorRaised;
                color4 = this.brColorRaised;
            }
        }
        else {
            color2 = this.flatColorDisabled;
            color3 = this.tlColorDisabled;
            color4 = this.brColorDisabled;
        }
        if (this.buttonPressed) {
            graphics.setColor(color2);
            graphics.fillRect(n, n2, n3 - n, n4 - n2);
            graphics.setColor(color3);
            for (int i = 1; i <= this.bevelWidth; ++i) {
                graphics.drawLine(i, i, i, n4);
                graphics.drawLine(i, i, n3, i);
            }
        }
        else {
            graphics.setColor(color2);
            graphics.fillRect(n, n2, n3 - n, n4 - n2);
            graphics.setColor(color3);
            for (int j = 1; j <= this.bevelWidth; ++j) {
                graphics.drawLine(j, j, j, n4 - j);
                graphics.drawLine(j, j, n3 - j, j);
            }
            graphics.setColor(color4);
            for (int k = 1; k <= this.bevelWidth; ++k) {
                graphics.drawLine(k, n4 - k, n3 - k, n4 - k);
                graphics.drawLine(n3 - k, k, n3 - k, n4 - k);
            }
        }
        graphics.setColor(Color.black);
        graphics.drawLine(1, 0, n3 - 1, 0);
        graphics.drawLine(0, 1, 0, n4 - 1);
        graphics.drawLine(1, n4, n3 - 1, n4);
        graphics.drawLine(n3, n4 - 1, n3, 1);
        graphics.clipRect(this.bevelWidth + 1, this.bevelWidth + 1, width - this.bevelWidth * 2 - 2, height - this.bevelWidth * 2 - 2);
        graphics.setColor(color);
        if (this.enabledImage == null) {
            return;
        }
        final int n5 = this.bevelWidth + 1 + (this.buttonPressed ? 2 : 0);
        final int n6 = this.bevelWidth + 1 + (this.buttonPressed ? 2 : 0);
        final int n7 = size.width - this.bevelWidth * 2 - 2;
        final int n8 = size.height - this.bevelWidth * 2 - 2;
        Image image;
        if (this.isEnabled()) {
            image = this.enabledImage;
        }
        else if (this.disabledImage == null) {
            if (this.grayedImage == null) {
                this.grayedImage = this.createImage(new FilteredImageSource(this.enabledImage.getSource(), new GrayedImageFilter()));
            }
            image = this.grayedImage;
        }
        else {
            image = this.disabledImage;
        }
        graphics.drawImage(image, n5, n6, n7, n8, this);
    }
    
    protected boolean isInvalid() {
        final Dimension size = this.getSize();
        return this.buttonImage == null || size.width != this.buttonImage.getWidth(this) || size.height != this.buttonImage.getHeight(this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isVisible()) {
            final Dimension size = this.getSize();
            if (size.width > 0 && size.height > 0) {
                this.makeImage();
                graphics.drawImage(this.buttonImage, 0, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.buttonListener = AWTEventMulticaster.add(this.buttonListener, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.buttonListener = AWTEventMulticaster.remove(this.buttonListener, actionListener);
    }
    
    public void setToolTipText(final String tooltip) {
        this.tooltip = tooltip;
    }
    
    public static void setToolTipHandle(final IHandleToolTips ttHandle) {
        DdiImageButton.ttHandle = ttHandle;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        DdiImageButton.ttHandle = null;
    }
    
    class ButtonHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (DdiImageButton.ttHandle != null) {
                DdiImageButton.ttHandle.hideTip();
            }
            if (DdiImageButton.this.isEnabled()) {
                DdiImageButton.this.buttonPressed = true;
                DdiImageButton.this.repaint();
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (DdiImageButton.this.tooltip != null && DdiImageButton.ttHandle != null) {
                DdiImageButton.ttHandle.setTip(DdiImageButton.this.tooltip, DdiImageButton.this.getLocationOnScreen().x + mouseEvent.getX(), DdiImageButton.this.getLocationOnScreen().y + mouseEvent.getY());
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (DdiImageButton.ttHandle != null) {
                DdiImageButton.ttHandle.hideTip();
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (DdiImageButton.ttHandle != null) {
                DdiImageButton.ttHandle.hideTip();
            }
            if (DdiImageButton.this.isEnabled()) {
                DdiImageButton.this.buttonPressed = false;
                DdiImageButton.this.repaint();
                if (DdiImageButton.this.buttonListener != null) {
                    DdiImageButton.this.buttonListener.actionPerformed(new ActionEvent(mouseEvent.getSource(), 1001, "MOUSE_RELEASED"));
                }
            }
        }
    }
    
    class GrayedImageFilter extends RGBImageFilter
    {
        private float[] hsb;
        
        public GrayedImageFilter() {
            this.hsb = new float[3];
            super.canFilterIndexColorModel = true;
        }
        
        public int filterRGB(final int n, final int n2, final int n3) {
            int n4;
            if (n3 == -16777216 || n3 == -1) {
                n4 = n3;
            }
            else {
                n4 = 16777215;
            }
            return n4;
        }
    }
}
