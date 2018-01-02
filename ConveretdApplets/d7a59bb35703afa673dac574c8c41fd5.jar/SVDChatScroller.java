import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDChatScroller extends Panel implements SVDScrollable
{
    SVDScrollbar myScroll;
    SVDCommunicator aplSVD;
    int nXPos;
    int nYPos;
    int nWidth;
    int nHeight;
    Color clBack;
    Color clBorderDark;
    Color clBorderLight;
    Color clOperator;
    Color clVisitor;
    Color clTextBack;
    Vector vText;
    Font fnText;
    FontMetrics fmText;
    int nLineHeight;
    int nLineWidth;
    int nFontBaseline;
    int nScrollPos;
    Image imgChat;
    Graphics gChat;
    
    public SVDChatScroller(final SVDCommunicator aplParent, final int aXPos, final int aYPos, final int aWidth, final int aHeight) {
        this.clBack = new Color(240, 240, 240);
        this.clBorderDark = new Color(50, 50, 50);
        this.clBorderLight = new Color(200, 200, 200);
        this.clOperator = new Color(0, 0, 255);
        this.clVisitor = new Color(0, 0, 0);
        this.clTextBack = new Color(16777215);
        this.aplSVD = aplParent;
        this.setBounds(this.nXPos = aXPos, this.nYPos = aYPos, this.nWidth = aWidth, this.nHeight = aHeight);
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setCursor(Cursor.getDefaultCursor());
        this.add(this.myScroll = new SVDScrollbar(aplParent, this.nWidth - 16, 0, 16, this.nHeight, new Color(Color.white.getRGB()), this.nHeight));
        this.myScroll.loadTheme();
        this.nScrollPos = 1;
        this.vText = new Vector();
        this.fnText = new Font("Dialog", 0, 11);
        this.fmText = Toolkit.getDefaultToolkit().getFontMetrics(this.fnText);
        this.nLineHeight = this.fmText.getMaxAscent() + this.fmText.getMaxDescent();
        this.nLineWidth = this.nWidth - 16 - 7;
        this.myScroll.BlockIncrement = this.nHeight - 4 - this.nLineHeight;
        this.myScroll.UnitIncrement = this.nLineHeight;
        this.nFontBaseline = this.fmText.getMaxAscent();
        this.imgChat = aplParent.createImage(this.nWidth - 16, this.nHeight);
        (this.gChat = this.imgChat.getGraphics()).setColor(this.clTextBack);
        this.gChat.fillRect(0, 0, this.nWidth, this.nHeight);
        this.myScroll.setVisible(false);
    }
    
    public void scrollNotification(final int scrollValue) {
        this.nScrollPos = scrollValue;
        this.drawText(true);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.imgChat, 0, 0, this.aplSVD);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void addText(final String str, final boolean bOperator) {
        if (!this.myScroll.isVisible()) {
            this.myScroll.setVisible(true);
        }
        int nLastBreak = -1;
        int nLineIndex = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == ' ' || str.charAt(i) == '.' || str.charAt(i) == ',' || str.charAt(i) == '!' || str.charAt(i) == '?') {
                nLastBreak = i;
            }
            if (this.fmText.stringWidth(str.substring(nLineIndex, i)) > this.nLineWidth) {
                if (nLastBreak > -1) {
                    this.vText.insertElementAt(str.substring(nLineIndex, nLastBreak + 1), this.vText.size());
                    this.vText.insertElementAt(new Boolean(bOperator), this.vText.size());
                    nLineIndex = nLastBreak + 1;
                    nLastBreak = -1;
                }
                else {
                    this.vText.insertElementAt(str.substring(nLineIndex, i), this.vText.size());
                    this.vText.insertElementAt(new Boolean(bOperator), this.vText.size());
                    nLineIndex = i;
                }
            }
        }
        if (str.substring(nLineIndex).length() > 0) {
            this.vText.insertElementAt(str.substring(nLineIndex), this.vText.size());
            this.vText.insertElementAt(new Boolean(bOperator), this.vText.size());
        }
        this.drawText(true);
        if (this.vText.size() / 2 * this.nLineHeight > this.nHeight) {
            this.myScroll.resizeScroll(this.vText.size() / 2 * this.nLineHeight, this.nHeight);
        }
        this.myScroll.repaintScrollBar();
    }
    
    void drawText(final boolean bRepaint) {
        this.gChat.setColor(this.clTextBack);
        this.gChat.fillRect(0, 0, this.nWidth - 16, this.nHeight);
        this.gChat.setFont(this.fnText);
        this.gChat.setColor(this.clOperator);
        for (int i = 0; i < this.vText.size() / 2; ++i) {
            if ((i + 1) * this.nLineHeight + 1 - this.nScrollPos >= 0 && i * this.nLineHeight + 1 - this.nScrollPos < this.nHeight) {
                this.gChat.setColor(((boolean)this.vText.elementAt(i * 2 + 1)) ? this.clOperator : this.clVisitor);
                this.gChat.drawString(this.vText.elementAt(i * 2), 1, i * this.nLineHeight + this.nFontBaseline + 1 - this.nScrollPos - 2);
            }
        }
        if (bRepaint) {
            this.getGraphics().drawImage(this.imgChat, 0, 0, this.aplSVD);
        }
    }
}
