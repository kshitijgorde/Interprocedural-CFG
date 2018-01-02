// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class OverviewImageDisplayPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private OverviewImagePanel overviewImagePanel;
    private Image image;
    
    public OverviewImageDisplayPanel(final OverviewImagePanel overviewImagePanel) {
        this.image = null;
        this.overviewImagePanel = overviewImagePanel;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.image = this.overviewImagePanel.getImage();
        if (this.image != null) {
            final Point imageDisplayOffset = this.overviewImagePanel.getImageDisplayOffset();
            this.paintImage(graphics, imageDisplayOffset.x, imageDisplayOffset.y);
        }
    }
    
    public void paintImage(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(this.getBackground());
        final int width = this.getWidth();
        final int height = this.getHeight();
        if (n > 0) {
            graphics.fillRect(0, 0, n, height);
        }
        else if (n < 0) {
            graphics.fillRect(width + n, 0, -n, height);
        }
        if (n2 > 0) {
            graphics.fillRect(0, 0, width, n2);
        }
        else if (n2 < 0) {
            graphics.fillRect(0, height + n2, width, -n2);
        }
        graphics.drawImage(this.image, n, n2, this);
    }
}
