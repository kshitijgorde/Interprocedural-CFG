// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

public class BsscImageLabel extends Canvas implements Runnable
{
    private Image m_bgImage;
    private int m_nbgWidth;
    private int m_nbgHeight;
    private Image m_cachebgImage;
    private int m_nOldWidth;
    private int m_nOldHeight;
    private boolean m_bBgUpdated;
    private Image m_imgbackground;
    private Vector m_vToDo;
    private BsscFont m_bFont;
    private String m_strValue;
    private static int m_nHeading;
    private static int m_nEnding;
    
    private Image getBackgroundImage() {
        if (this.m_nbgWidth <= 0 || this.m_nbgHeight <= 0 || this.m_bgImage == null) {
            return null;
        }
        final Rectangle bounds = this.getBounds();
        if (bounds.height == this.m_nOldHeight && bounds.width == this.m_nOldWidth && !this.m_bBgUpdated) {
            return this.m_cachebgImage;
        }
        this.m_nOldWidth = bounds.width;
        this.m_nOldHeight = bounds.height;
        this.m_cachebgImage = this.createImage(bounds.width, bounds.height);
        this.drawBackGround(this.m_cachebgImage.getGraphics());
        return this.m_cachebgImage;
    }
    
    public BsscImageLabel(final String strValue) {
        this.m_strValue = strValue;
        if (ViewSkin.m_PaneColorBg != null) {
            this.setBackground(ViewSkin.m_PaneColorBg);
        }
        if (ViewSkin.m_PaneImageBg != null) {
            this.setBgImage(ViewSkin.m_PaneImageBg);
        }
        if (ViewSkin.m_PaneFont != null) {
            this.m_bFont = ViewSkin.m_PaneFont;
        }
        this.m_vToDo = new Vector();
    }
    
    public void paint(final Graphics graphics) {
        try {
            final Rectangle bounds = this.getBounds();
            final Image image = this.createImage(bounds.width, bounds.height);
            final Graphics graphics2 = image.getGraphics();
            this.m_imgbackground = this.getBackgroundImage();
            if (this.m_imgbackground != null) {
                graphics2.drawImage(this.m_imgbackground, 0, 0, this);
            }
            Font font;
            Color color;
            if (this.m_bFont != null) {
                font = this.m_bFont.getFont();
                color = this.m_bFont.getColor();
            }
            else {
                font = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
                color = Color.black;
            }
            final FontMetrics fontMetrics = graphics.getFontMetrics(font);
            final int ascent = fontMetrics.getAscent();
            final int leading = fontMetrics.getLeading();
            graphics2.getFont();
            final Color color2 = graphics2.getColor();
            graphics2.setColor(color);
            graphics2.setFont(font);
            graphics2.drawString(this.m_strValue, 0, ascent + leading + BsscImageLabel.m_nHeading);
            graphics2.setFont(font);
            graphics2.setColor(color2);
            graphics.drawImage(image, 0, 0, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setBgImage(final Image bgImage) {
        this.m_bgImage = bgImage;
        this.m_nbgHeight = this.m_bgImage.getHeight(this);
        this.m_nbgWidth = this.m_bgImage.getWidth(this);
    }
    
    public void dispatchToDo(final String s) {
        if (s == "repaint") {
            this.repaint();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int nbgWidth, final int nbgHeight) {
        if (image == this.m_bgImage) {
            this.m_nbgWidth = nbgWidth;
            this.m_nbgHeight = nbgHeight;
            this.m_bBgUpdated = true;
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void drawBackGround(final Graphics graphics) {
        if (this.m_nbgWidth > 0 && this.m_nbgHeight > 0 && this.m_bgImage != null) {
            final Rectangle bounds = this.getBounds();
            for (int i = 0; i <= (bounds.width - 1) / this.m_nbgWidth; ++i) {
                for (int j = 0; j <= (bounds.height - 1) / this.m_nbgHeight; ++j) {
                    if (!graphics.drawImage(this.m_bgImage, i * this.m_nbgWidth, j * this.m_nbgHeight, this.m_nbgWidth, this.m_nbgHeight, null)) {
                        this.setTimeout("repaint", 100);
                        return;
                    }
                }
            }
            if (this.m_bBgUpdated) {
                this.m_bBgUpdated = false;
            }
        }
        else {
            final Color color = graphics.getColor();
            graphics.setColor(this.getBackground());
            final Rectangle bounds2 = this.getBounds();
            graphics.fillRect(0, 0, bounds2.width, bounds2.height);
            graphics.setColor(color);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        BsscImageLabel.m_nHeading = 4;
        BsscImageLabel.m_nEnding = 4;
    }
    
    public Dimension getPreferredSize() {
        new Dimension();
        Font font;
        if (this.m_bFont != null) {
            font = this.m_bFont.getFont();
        }
        else {
            font = new Font(BsscFontFixPatch.GetFontName(), 0, BsscFontFixPatch.GetFontSize());
        }
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
        final char[] array = new char[this.m_strValue.length()];
        this.m_strValue.getChars(0, this.m_strValue.length(), array, 0);
        return new Dimension(fontMetrics.charsWidth(array, 0, this.m_strValue.length()), fontMetrics.getHeight() + BsscImageLabel.m_nHeading + BsscImageLabel.m_nEnding);
    }
    
    public void run() {
        final ToDo toDo;
        synchronized (this.m_vToDo) {
            toDo = this.m_vToDo.elementAt(0);
            this.m_vToDo.removeElementAt(0);
        }
        // monitorexit(this.m_vToDo)
        try {
            Thread.sleep(toDo.m_nMSec);
            this.dispatchToDo(toDo.m_sToDo);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void setTimeout(final String s, final int n) {
        final ToDo toDo = new ToDo(s, n);
        synchronized (this.m_vToDo) {
            this.m_vToDo.addElement(toDo);
        }
        // monitorexit(this.m_vToDo)
        new Thread(this).start();
    }
    
    public void setText(final String strValue) {
        this.m_strValue = strValue;
    }
    
    private class ToDo
    {
        public String m_sToDo;
        public int m_nMSec;
        
        public ToDo(final String sToDo, final int nmSec) {
            BsscImageLabel.this.getClass();
            this.m_sToDo = sToDo;
            this.m_nMSec = nmSec;
        }
    }
}
