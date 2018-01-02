import java.awt.image.ImageObserver;
import java.awt.Graphics;
import mika.graphics.G_TextScreen;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class C_TextImage extends Panel
{
    Image m_imgBackground;
    G_TextScreen m_tsCurrentText;
    Image m_imgCurrentPicture;
    int m_iWidth;
    int m_iHeight;
    
    public void setSize(final int iWidth, final int iHeight) {
        this.m_iWidth = iWidth;
        this.m_iHeight = iHeight;
    }
    
    public void setBackgroundImage(final Image imgBackground) {
        this.m_imgBackground = imgBackground;
    }
    
    public void setText(final G_TextScreen tsCurrentText) {
        this.m_tsCurrentText = tsCurrentText;
        this.repaint();
    }
    
    public void setPicture(final Image imgCurrentPicture) {
        this.m_imgCurrentPicture = imgCurrentPicture;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(this.getGraphics());
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_imgBackground != null) {
            graphics.drawImage(this.m_imgBackground, 0, 0, this);
        }
        if (this.m_imgCurrentPicture != null) {
            graphics.drawImage(this.m_imgCurrentPicture, (this.m_iWidth - this.m_imgCurrentPicture.getWidth(this)) / 2, (this.m_iHeight - this.m_imgCurrentPicture.getHeight(this)) / 2, this);
        }
        if (this.m_tsCurrentText != null) {
            this.m_tsCurrentText.print(graphics, 0, 0, this.m_iWidth, this.m_iHeight);
        }
    }
}
