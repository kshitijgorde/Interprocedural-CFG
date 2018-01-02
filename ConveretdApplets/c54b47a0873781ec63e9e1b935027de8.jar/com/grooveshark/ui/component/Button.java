// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Button extends JPanel implements MouseListener
{
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    private static final long serialVersionUID = 2425188576227121958L;
    private MouseToActionListener listener;
    private int spacing;
    private int border;
    private int orientation;
    private ImageIcon labelIcon;
    private String labelText;
    private ImageIcon corner;
    private ImageIcon middle;
    private ImageIcon defaultCorner;
    private ImageIcon defaultMiddle;
    private ImageIcon disabledCorner;
    private ImageIcon disabledMiddle;
    private ImageIcon hoverCorner;
    private ImageIcon hoverMiddle;
    private ImageIcon pressedCorner;
    private ImageIcon pressedMiddle;
    
    public Button(final ImageIcon corner, final ImageIcon middle) {
        this.spacing = 10;
        this.border = 15;
        this.listener = new MouseToActionListener();
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this.listener);
        this.addMouseListener(this);
        this.orientation = 2;
        this.labelText = "";
        this.setCurrentImages(corner, middle);
        this.setDefaultImages(corner, middle);
        this.setPressedImages(corner, middle);
        this.setHoverImages(corner, middle);
        this.setDisabledImages(corner, middle);
        this.labelIcon = new ImageIcon();
        this.setOpaque(false);
        this.updateSize();
    }
    
    private void updateSize() {
        final Font font = this.getFont().deriveFont(1).deriveFont(14.0f);
        final int textWidth = this.getFontMetrics(font).stringWidth(this.labelText);
        final int iconWidth = this.labelIcon.getIconWidth();
        final int panelWidth = 2 * this.border + this.spacing + textWidth + iconWidth + 2 * this.corner.getIconWidth();
        final int panelHeight = this.corner.getIconHeight();
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        final Graphics2D canvas = (Graphics2D)g;
        final Image leftCornerImage = this.corner.getImage();
        final Image middleImage = this.middle.getImage();
        final int iconHeight = this.labelIcon.getIconHeight();
        final int iconWidth = this.labelIcon.getIconWidth();
        canvas.setColor(this.getForeground());
        canvas.setFont(canvas.getFont().deriveFont(1).deriveFont(14.0f));
        final LineMetrics m = canvas.getFont().getLineMetrics(this.labelText, canvas.getFontRenderContext());
        final float textHeight = m.getAscent() - m.getDescent();
        final int textWidth = canvas.getFontMetrics().stringWidth(this.labelText);
        final int panelHeight = this.corner.getIconHeight();
        final int panelWidth = this.getWidth();
        final int middleHeight = this.corner.getIconHeight();
        final int middleWidth = panelWidth - this.corner.getIconWidth() * 2;
        canvas.drawImage(leftCornerImage, 0, 0, this);
        canvas.drawImage(middleImage, this.corner.getIconWidth(), 0, middleWidth, middleHeight, this);
        canvas.drawImage(leftCornerImage, panelWidth - this.corner.getIconWidth(), 0, panelWidth, this.corner.getIconHeight(), this.corner.getIconWidth(), 0, 0, this.corner.getIconHeight(), this);
        final int labelWidth = textWidth + this.spacing + iconWidth;
        float textX = this.centerFromTop(panelWidth, labelWidth);
        final float textY = this.centerFromBase(panelHeight, textHeight);
        int iconX = (int)(textX + textWidth + this.spacing);
        final int iconY = (int)this.centerFromTop(panelHeight, iconHeight);
        if (this.orientation == 1) {
            iconX = (int)textX;
            textX = iconX + iconWidth + this.spacing;
        }
        canvas.drawString(this.labelText, textX, textY);
        canvas.drawImage(this.labelIcon.getImage(), iconX, iconY, this);
        super.paintComponent(g);
    }
    
    private float centerFromTop(final float panel, final float object) {
        return panel / 2.0f - object / 2.0f;
    }
    
    public void setIconOrientation(final int orientation) {
        this.orientation = orientation;
    }
    
    private float centerFromBase(final float panel, final float object) {
        return panel / 2.0f + object / 2.0f;
    }
    
    public void setAllImages(final ImageIcon corner, final ImageIcon middle) {
        this.setCurrentImages(corner, middle);
        this.setDefaultImages(corner, middle);
        this.setPressedImages(corner, middle);
        this.setHoverImages(corner, middle);
        this.setDisabledImages(corner, middle);
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.listener.setEnabled(enabled);
        if (enabled) {
            this.setCurrentImages(this.defaultCorner, this.defaultMiddle);
        }
        else {
            this.setCurrentImages(this.disabledCorner, this.disabledMiddle);
        }
        this.repaint();
    }
    
    public void setIcon(final ImageIcon icon) {
        this.labelIcon = icon;
        this.updateSize();
    }
    
    private void setCurrentImages(final ImageIcon corner, final ImageIcon middle) {
        this.corner = corner;
        this.middle = middle;
        this.repaint();
    }
    
    public void setDefaultImages(final ImageIcon corner, final ImageIcon middle) {
        this.defaultCorner = corner;
        this.defaultMiddle = middle;
    }
    
    public void setDisabledImages(final ImageIcon corner, final ImageIcon middle) {
        this.disabledCorner = corner;
        this.disabledMiddle = middle;
    }
    
    public void setHoverImages(final ImageIcon corner, final ImageIcon middle) {
        this.hoverCorner = corner;
        this.hoverMiddle = middle;
    }
    
    public void setPressedImages(final ImageIcon corner, final ImageIcon middle) {
        this.pressedCorner = corner;
        this.pressedMiddle = middle;
    }
    
    public void setText(final String text) {
        this.labelText = text;
        this.updateSize();
    }
    
    public void setActionCommand(final String actionCommand) {
        this.listener.setActionCommand(actionCommand);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.listener.setActionListener(actionListener);
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        if (this.isEnabled()) {
            this.setCurrentImages(this.hoverCorner, this.hoverMiddle);
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        if (this.isEnabled()) {
            this.setCurrentImages(this.defaultCorner, this.defaultMiddle);
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        if (this.isEnabled()) {
            this.setCurrentImages(this.pressedCorner, this.pressedMiddle);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.isEnabled()) {
            this.setCurrentImages(this.defaultCorner, this.defaultMiddle);
        }
    }
}
