// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jmaster.jumploader.model.api.B;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class ImageScrollControl extends JPanel
{
    private static final long C = 8566879568829198742L;
    protected ImageControl B;
    protected JScrollPane A;
    
    public ImageScrollControl(final B b) {
        this.setLayout(new BorderLayout());
        this.B = new ImageControl(b);
        this.add(this.A = new JScrollPane(this.B), "Center");
    }
    
    public ImageControl getImageControl() {
        return this.B;
    }
    
    public void setZoomFactor(final double zoomFactor) {
        this.B.setZoomFactor(zoomFactor);
    }
    
    public void setImage(final BufferedImage image) {
        this.B.setImage(image);
    }
    
    public void discardImage() {
        this.B.discardImage();
    }
    
    public Point screenToImage(final Point point) {
        return this.B.screenToImage(point);
    }
    
    public Point imageToScreen(final Point point) {
        return this.B.imageToScreen(point);
    }
    
    public void setBandCoords(final int n, final int n2, final int n3, final int n4) {
        this.B.setBandCoords(n, n2, n3, n4);
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this.B.isWaitMode() && this.B.getWaitImage() != null) {
            final Image waitImage = this.B.getWaitImage();
            graphics.drawImage(waitImage, this.getWidth() / 2 - waitImage.getWidth(null) / 2, this.getHeight() / 2 - waitImage.getHeight(null) / 2, null);
        }
    }
}
