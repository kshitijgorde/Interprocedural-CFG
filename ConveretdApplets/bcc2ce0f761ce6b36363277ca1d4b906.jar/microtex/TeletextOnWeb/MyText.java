// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

class MyText extends Panel
{
    private static int MAX_CHAR;
    private int m_FontDim;
    private int x;
    private int y;
    private int w;
    private int h;
    private int CenteredX;
    private int CenteredY;
    private String m_Text2Display;
    private boolean m_Editable;
    public boolean m_Highlighted;
    private boolean EditingText;
    private Font Myfont;
    private Color m_bgColor;
    private Color m_onColor;
    private Color m_offColor;
    private FontMetrics fm;
    private Image ActualBg;
    private Image[] BgImages;
    private TextField EnterData;
    private String PageNumber;
    private ControlPanel m_parent;
    
    public void paint(final Graphics g) {
        this.fm = g.getFontMetrics();
        final int numX = this.fm.stringWidth("000");
        this.CenteredX = (this.w - (numX + 20) - this.fm.stringWidth(this.m_Text2Display)) / 2;
        this.CenteredY = this.fm.getAscent() + (this.h - (this.fm.getAscent() + this.fm.getDescent())) / 2;
        if (this.ActualBg != null) {
            g.drawImage(this.ActualBg, 0, 0, this.w, this.h, this);
        }
        else {
            this.setBackground(this.m_bgColor);
        }
        if (!this.EditingText) {
            g.setColor(Color.white);
            g.drawString(this.PageNumber, 10, this.CenteredY);
            g.drawString(this.m_Text2Display, numX + 10 + this.CenteredX, this.CenteredY);
        }
    }
    
    public void setHighlightOn() {
        if (this.BgImages != null) {
            this.ActualBg = this.BgImages[1];
        }
        this.m_Highlighted = true;
        this.m_bgColor = this.m_onColor;
        this.repaint();
    }
    
    public void setHighlightOff() {
        if (this.BgImages != null) {
            this.ActualBg = this.BgImages[0];
        }
        this.m_Highlighted = false;
        this.m_bgColor = this.m_offColor;
        this.repaint();
    }
    
    public void setText(final String Text) {
        this.m_Text2Display = Text;
        this.repaint();
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.setHighlightOn();
        return true;
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        this.setHighlightOff();
        return true;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.m_parent.requestPage(this.PageNumber);
        return true;
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.w, this.h);
    }
    
    public MyText(final ControlPanel parent, final Image[] Backgrounds, final String DefaultText, final int FontDim, final boolean editable, final int Xpos, final int Ypos, final int Width, final int Height) {
        this.m_bgColor = Color.black;
        this.m_onColor = Color.red;
        this.m_offColor = Color.black;
        this.ActualBg = null;
        this.BgImages = new Image[2];
        if (Backgrounds != null) {
            for (int k = 0; k < Backgrounds.length; ++k) {
                this.BgImages[k] = Backgrounds[k];
            }
        }
        this.m_Text2Display = DefaultText;
        if (DefaultText.length() > MyText.MAX_CHAR) {
            this.m_Text2Display = DefaultText.substring(0, MyText.MAX_CHAR);
        }
        this.m_FontDim = FontDim;
        this.m_Editable = editable;
        this.x = Xpos;
        this.y = Ypos;
        this.w = Width;
        this.h = Height;
        this.m_Highlighted = false;
        this.EditingText = false;
        this.setFont(this.Myfont = new Font("Arial", 0, FontDim));
        if (this.BgImages != null) {
            this.ActualBg = this.BgImages[0];
        }
        this.PageNumber = this.m_Text2Display.substring(0, 3);
        this.m_Text2Display = this.m_Text2Display.substring(4);
        this.m_parent = parent;
        this.m_bgColor = this.m_offColor;
    }
    
    static {
        MyText.MAX_CHAR = 29;
    }
}
