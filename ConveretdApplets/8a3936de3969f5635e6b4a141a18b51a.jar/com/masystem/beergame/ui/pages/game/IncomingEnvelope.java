// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import com.masystem.beergame.resource.ResourceManager;
import java.awt.image.BufferedImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;

public final class IncomingEnvelope extends BeerGameImage
{
    private final BufferedImage original;
    private int incomingOrder;
    
    public IncomingEnvelope() {
        super(ResourceManager.getOptimizedImage("order_envelope_open.png"));
        this.incomingOrder = -1;
        this.original = this.getImage();
        this.setImage(new BufferedImage(this.original.getWidth(), this.original.getHeight(), this.original.getType()));
        this.setIncomingOrder(0);
    }
    
    public final void setIncomingOrder(final int incomingOrder) {
        if (incomingOrder != this.incomingOrder) {
            final String value = String.valueOf(incomingOrder);
            final BufferedImage image;
            final Graphics2D graphics2D;
            final Composite composite = (graphics2D = (Graphics2D)(image = this.getImage()).getGraphics()).getComposite();
            graphics2D.setComposite(AlphaComposite.getInstance(1, 0.0f));
            graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());
            graphics2D.setComposite(composite);
            graphics2D.drawImage(this.original, 0, 0, null);
            Font font = (Font)ResourceManager.getResource("InGameOrderFont");
            int n = image.getHeight() / 2 + 1;
            if (value.length() > 3) {
                font = font.deriveFont(2, (int)(font.getSize() * 0.6f / 2.0f) << 1);
                n = image.getHeight() / 2 - 7;
            }
            else if (value.length() > 2) {
                font = font.deriveFont(2, (int)(font.getSize() * 0.75f / 2.0f) << 1);
                n = image.getHeight() / 2 - 2;
            }
            final int stringWidth = this.getComponent().getFontMetrics(font).stringWidth(value);
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(font);
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.drawString(value, (image.getWidth() - stringWidth) / 2 - 3, n);
            this.incomingOrder = incomingOrder;
        }
    }
}
