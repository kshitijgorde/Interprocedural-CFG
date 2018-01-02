// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.swing.JPanel;

public class TextWrap extends JPanel
{
    public static int LEFT;
    public static int CENTER;
    public static int RIGHT;
    public Font _fFont;
    public Font _fBoldFont;
    protected Image _imageBackground;
    protected Color _cBackground;
    protected Color _cForeground;
    protected int _iOrient;
    protected String _sMessage;
    private int _iWidth;
    private int _iHeight;
    private Image _iImage;
    private Graphics _gNew;
    private String sOldMessage;
    int _iOWidth;
    int _iOHeight;
    public int TOP_MARGIN;
    public int LEFT_MARGIN;
    public int RIGHT_MARGIN;
    public int LINE_SPACE;
    public Dimension _dSize;
    
    static {
        TextWrap.LEFT = 0;
        TextWrap.CENTER = 1;
        TextWrap.RIGHT = 2;
    }
    
    public TextWrap() {
        this("");
    }
    
    public TextWrap(final String sMessage) {
        this._fFont = new Font("Helvetica", 0, 13);
        this._fBoldFont = new Font("Helvetica", 1, 13);
        this._cBackground = Color.white;
        this._cForeground = Color.black;
        this._iOrient = TextWrap.LEFT;
        this._sMessage = "";
        this.sOldMessage = "";
        this._iOWidth = 0;
        this._iOHeight = 0;
        this.TOP_MARGIN = 0;
        this.LEFT_MARGIN = 10;
        this.RIGHT_MARGIN = 10;
        this.LINE_SPACE = 0;
        this._dSize = new Dimension(0, 0);
        this._sMessage = sMessage;
    }
    
    public Dimension getMinimumSize() {
        return this._dSize;
    }
    
    public Dimension getPreferredSize() {
        return this._dSize;
    }
    
    public void paint(final Graphics graphics) {
        if (this._iOWidth != this.size().width || this._iOHeight != this.size().height) {
            this._iImage = this.createImage(this.getSize().width, this.getSize().height);
            GetGraphics.setText(this._gNew = this._iImage.getGraphics());
        }
        if (this._iOWidth != this.getSize().width || this._iOHeight != this.getSize().height || !this._sMessage.equals(this.sOldMessage)) {
            this._gNew.setColor(this.getBackground());
            this._gNew.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this._gNew.setColor(this._cForeground);
            final StringTokenizer stringTokenizer = new StringTokenizer(this._sMessage, "//");
            int n = 1;
            int n2 = 0;
            int top_MARGIN = this.TOP_MARGIN;
            final int n3 = this.getSize().width - this.RIGHT_MARGIN - this.LEFT_MARGIN;
            while (stringTokenizer.hasMoreElements()) {
                String s = stringTokenizer.nextToken();
                if (s.startsWith("*b*")) {
                    s = s.substring(3, s.length());
                    this._gNew.setFont(this._fBoldFont);
                }
                else if (s.startsWith("*p*")) {
                    s = s.substring(3, s.length());
                    this._gNew.setFont(this._fBoldFont);
                }
                else if (n2 == 0) {
                    this._gNew.setFont(this._fBoldFont);
                }
                else {
                    this._gNew.setFont(this._fFont);
                }
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
                final FontMetrics fontMetrics = this._gNew.getFontMetrics();
                String string = "";
                while (stringTokenizer2.hasMoreElements()) {
                    try {
                        final String string2 = String.valueOf(stringTokenizer2.nextToken()) + " ";
                        if (string2 == null) {
                            continue;
                        }
                        final int stringWidth = fontMetrics.stringWidth(String.valueOf(string) + string2);
                        if (stringWidth < n3 || stringWidth == fontMetrics.stringWidth(string2)) {
                            string = String.valueOf(string) + string2;
                        }
                        else {
                            top_MARGIN += fontMetrics.getHeight() + this.LINE_SPACE;
                            this._gNew.drawString(string, this.LEFT_MARGIN + ((this._iOrient == TextWrap.CENTER) ? ((n3 - fontMetrics.stringWidth(string)) / 2) : ((this._iOrient == TextWrap.RIGHT) ? (n3 - fontMetrics.stringWidth(string)) : 0)), top_MARGIN);
                            string = string2;
                            ++n;
                            fontMetrics.stringWidth(string);
                        }
                    }
                    catch (NoSuchElementException ex) {}
                }
                if (!string.trim().equals("")) {
                    ++n2;
                }
                top_MARGIN += fontMetrics.getHeight() + this.LINE_SPACE;
                this._gNew.drawString(string, this.LEFT_MARGIN + ((this._iOrient == TextWrap.CENTER) ? ((n3 - fontMetrics.stringWidth(string)) / 2) : ((this._iOrient == TextWrap.RIGHT) ? (n3 - fontMetrics.stringWidth(string)) : 0)), top_MARGIN);
                ++n;
            }
            this._iOWidth = this.size().width;
            this._iOHeight = this.size().height;
            this.sOldMessage = this._sMessage;
        }
        graphics.drawImage(this._iImage, 0, 0, this);
    }
    
    public void setBoldFont(final Font fBoldFont) {
        this._fBoldFont = fBoldFont;
    }
    
    public void setFont(final Font fFont) {
        this._fFont = fFont;
    }
    
    public void setForeground(final Color cForeground) {
        this._cForeground = cForeground;
    }
    
    public void setOrientation(final int iOrient) {
        this._iOrient = iOrient;
    }
    
    public void setText(final String sMessage) {
        this._sMessage = sMessage;
    }
    
    public void setTextSize(final int n, final int n2) {
        this._dSize = new Dimension(n, n2);
        this.invalidate();
        this.doLayout();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
