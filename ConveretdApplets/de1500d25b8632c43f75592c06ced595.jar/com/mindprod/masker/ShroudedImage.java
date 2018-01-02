// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masker;

import java.awt.font.LineMetrics;
import java.awt.font.FontRenderContext;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Component;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.prefs.Preferences;
import java.util.Map;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

final class ShroudedImage extends JPanel
{
    private String text;
    private int ascent;
    private int height;
    private int width;
    
    public ShroudedImage(final String text) {
        this.setOpaque(false);
        this.setText(text);
    }
    
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setFont(this.getFont());
        g2d.setColor(this.getForeground());
        g2d.drawString(this.text, 0, this.ascent);
    }
    
    public void saveImage(final String saveName) {
        final Preferences userPrefs = Preferences.userRoot().node("/com/mindprod/masker");
        final File suggestedFile = new File(userPrefs.get("SAVEDIR", "C:"), saveName);
        final JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(suggestedFile);
        fc.addChoosableFileFilter(new PNGFileFilter());
        final int result = fc.showSaveDialog(this);
        switch (result) {
            case 0: {
                final File file = fc.getSelectedFile();
                try {
                    final BufferedImage bufferedImage = new BufferedImage(this.width, this.height, 7);
                    final Graphics2D g2d = bufferedImage.createGraphics();
                    this.paintComponent(g2d);
                    ImageIO.write(bufferedImage, "png", file);
                }
                catch (IOException e) {
                    System.err.println("image not saved successfully");
                }
                userPrefs.put("SAVEDIR", fc.getCurrentDirectory().getAbsolutePath());
            }
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.refresh();
    }
    
    public void setText(final String text) {
        this.text = text;
        this.refresh();
    }
    
    private void refresh() {
        if (this.text == null) {
            return;
        }
        final Font font = this.getFont();
        final FontMetrics fm = this.getFontMetrics(font);
        this.width = fm.stringWidth(this.text);
        final BufferedImage bufferedImage = new BufferedImage(200, 200, 7);
        final Graphics2D g2d = bufferedImage.createGraphics();
        final FontRenderContext fr = g2d.getFontRenderContext();
        final LineMetrics lm = font.getLineMetrics(this.text, fr);
        this.ascent = (int)(lm.getAscent() + 0.5);
        this.height = (int)(lm.getDescent() + lm.getAscent() + 0.5);
        final Dimension d = new Dimension(this.width, this.height);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.invalidate();
        this.repaint();
    }
}
