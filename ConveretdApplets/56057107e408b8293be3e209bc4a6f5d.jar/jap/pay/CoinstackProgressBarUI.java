// 
// Decompiled by Procyon v0.5.30
// 

package jap.pay;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class CoinstackProgressBarUI extends BasicProgressBarUI
{
    private static final int Y_OFFSET = 6;
    private static final int X_OFFSET = 6;
    private static final int[] X_SHIFT;
    private Image m_imgCoinImage;
    private int m_yFactor;
    private int m_xPos;
    private int m_yPos;
    private int m_imageHeight;
    private int m_imageWidth;
    private int m_height;
    private int m_width;
    
    public CoinstackProgressBarUI(final ImageIcon imageIcon, final int n, final int n2) {
        this.m_imgCoinImage = imageIcon.getImage();
        this.m_imageHeight = this.m_imgCoinImage.getHeight(null);
        this.m_imageWidth = this.m_imgCoinImage.getWidth(null);
        this.m_yFactor = this.m_imageHeight / 3;
        this.m_width = 12 + this.m_imageWidth + 4 + 3;
        this.m_height = 12 + this.m_imageHeight + this.m_yFactor * (n2 - n - 1);
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        final JProgressBar progressBar = (JProgressBar)component;
        this.m_height = 12 + this.m_imageHeight + this.m_yFactor * (progressBar.getMaximum() - progressBar.getMinimum() - 1);
        graphics.setColor(Color.gray);
        this.m_xPos = 6;
        this.m_yPos = this.m_height - 6;
        final int n = this.m_yPos - (this.m_imageHeight + this.m_yFactor * (progressBar.getMaximum() - progressBar.getMinimum() - 1));
        graphics.drawLine(this.m_xPos, this.m_yPos, this.m_xPos, n);
        final int n2 = this.m_yPos - (this.m_yPos - n) / 2;
        graphics.drawLine(this.m_xPos, this.m_yPos, this.m_xPos + 3, this.m_yPos);
        graphics.drawLine(this.m_xPos, n, this.m_xPos + 3, n);
        graphics.drawLine(this.m_xPos, n2, this.m_xPos + 3, n2);
        if (progressBar.getValue() == progressBar.getMinimum()) {
            return;
        }
        int n3 = 10;
        int n4 = this.m_height - 6 - this.m_imageHeight + 1;
        for (int i = 0; i < progressBar.getValue() - progressBar.getMinimum(); ++i) {
            n3 += CoinstackProgressBarUI.X_SHIFT[i % CoinstackProgressBarUI.X_SHIFT.length];
            graphics.drawImage(this.m_imgCoinImage, n3, n4, null);
            n4 -= this.m_yFactor;
        }
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        return new Dimension(this.m_width, this.m_height);
    }
    
    static {
        X_SHIFT = new int[] { 0, 3, 1, -2, -1, 0, -1, 0 };
    }
}
