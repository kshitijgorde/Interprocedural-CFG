// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class BorderSide extends JPanel
{
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final String SOLID = "SOLID";
    public static final String ROUND = "ROUND";
    public static final String NONE = "NONE";
    private int iArcSize;
    protected String sTitle;
    protected int iTitle;
    private int iMin;
    protected CalculatorApplet CA;
    protected FontMetrics fontMetrics;
    protected Color _cWhite;
    protected Color _cBlack;
    protected Color _cPage;
    protected Color _cFont;
    protected int _iOrientation;
    protected int _iWidth;
    protected int _iMinX;
    protected int _iMinY;
    protected String _sType;
    protected Font f;
    protected Font sf;
    private Image _iImage;
    private Graphics g;
    private String sOldMessage;
    private boolean z;
    int _iOWidth;
    int _iOHeight;
    
    public BorderSide(final CalculatorApplet ca, final int iOrientation, int iWidth, final Color cBlack, final Color cWhite, final Color cPage, final Color cFont, final String sType, final int iTitle, final int iArcSize) {
        this.iArcSize = 4;
        this.iTitle = 0;
        this.iMin = 0;
        this._iImage = null;
        this.g = null;
        this.sOldMessage = "";
        this.z = false;
        this._iOWidth = 0;
        this._iOHeight = 0;
        this.iArcSize = iArcSize;
        if (iWidth < 0) {
            iWidth = 0;
        }
        this._sType = sType;
        this._iOrientation = iOrientation;
        this._iWidth = iWidth;
        final int n = (this._sType.equals("ROUND") ? this.iArcSize : 0) + this._iWidth;
        this._iMinY = n;
        this._iMinX = n;
        this._cWhite = cWhite;
        this._cBlack = cBlack;
        this._cFont = cFont;
        this._cPage = cPage;
        if (ca != null) {
            this.CA = ca;
            this.iTitle = iTitle;
            final FontMetrics fontMetrics = this.getFontMetrics(ca.getTitleFont());
            this.f = ca.getTitleFont();
            this.sf = ca.getBoldFont();
            this._iMinY = (int)(fontMetrics.getHeight() * 1.7);
            if (this.CA.iL != null) {
                this._iMinY = ((this.CA.iL.getHeight(null) < this._iMinY) ? this._iMinY : this.CA.iL.getHeight(null));
            }
            this._iMinX = this.iArcSize + this._iWidth;
            this._iMinY += iWidth;
        }
        this.resize(this._iMinX, this._iMinY);
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public Dimension getPreferredSize() {
        return this.minimumSize();
    }
    
    public Dimension getSize() {
        return this.size();
    }
    
    public Dimension minimumSize() {
        return new Dimension(this._iMinX, this._iMinY);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this._iOWidth != bounds.width || this._iOHeight != bounds.height) {
            this._iImage = this.createImage(this.getSize().width, bounds.height);
            GetGraphics.setGraphics(this.g = this._iImage.getGraphics());
        }
        boolean b = false;
        if (this.iTitle > 0 && this.sTitle != null) {
            b = (this.sTitle.equals(this.sOldMessage) ^ true);
        }
        if (this._iOWidth != bounds.width || this._iOHeight != bounds.height || b) {
            this.g.setColor(this._cWhite);
            this.g.fillRect(0, 0, bounds.width, bounds.height);
            if (!this._sType.equals("NONE")) {
                if (this._sType.equals("SOLID")) {
                    this.g.setColor(this._cBlack);
                    if (this._iOrientation == 1) {
                        this.g.fillRect(0, 0, bounds.width, this._iWidth);
                        this.g.fillRect(0, 0, this._iWidth, bounds.height);
                        this.g.fillRect(bounds.width - this._iWidth, 0, this._iWidth, bounds.height);
                    }
                    else {
                        this.g.fillRect(0, 0, bounds.width, bounds.height);
                    }
                }
                else if (this._sType.equals("ROUND")) {
                    this.g.setColor(this._cBlack);
                    if (this._iOrientation == 3) {
                        this.g.fillRect(0, 0, this._iWidth, bounds.height);
                    }
                    else if (this._iOrientation == 4) {
                        this.g.fillRect(bounds.width - this._iWidth, 0, bounds.width, bounds.height);
                    }
                    else {
                        this.g.setColor(this._cPage);
                        this.g.fillRect(0, 0, this._iWidth + this.iArcSize, this._iWidth + this.iArcSize);
                        this.g.fillRect(bounds.width - this._iWidth - this.iArcSize, 0, this._iWidth + this.iArcSize, this._iWidth + this.iArcSize);
                        this.g.setColor(this._cBlack);
                        if (this._iOrientation == 1) {
                            this.g.fillRect(0, this.iArcSize, this._iWidth, bounds.height - this.iArcSize);
                            this.g.fillRect(bounds.width - this._iWidth, this.iArcSize, this._iWidth, bounds.height - this.iArcSize);
                            this.g.fillRect(this.iArcSize + this._iWidth, 0, bounds.width - 2 * (this._iWidth + this.iArcSize), this._iWidth);
                            this.g.fillArc(0, 0, 2 * (this._iWidth + this.iArcSize), 2 * (this._iWidth + this.iArcSize), 90, 90);
                            this.g.fillArc(bounds.width - (this.iArcSize + this._iWidth) * 2, 0, 2 * (this._iWidth + this.iArcSize), 2 * (this._iWidth + this.iArcSize), 0, 90);
                            this.g.setColor(this._cWhite);
                            this.g.fillArc(this._iWidth, this._iWidth, 2 * this.iArcSize, 2 * this.iArcSize, 90, 90);
                            this.g.fillArc(bounds.width - (this.iArcSize + this._iWidth) * 2 + this._iWidth, this._iWidth, 2 * this.iArcSize, 2 * this.iArcSize, 0, 90);
                        }
                        else {
                            this.g.fillRect(this.iArcSize + this._iWidth, bounds.height - this._iWidth, bounds.width - 2 * (this._iWidth + this.iArcSize), bounds.height);
                            this.g.fillArc(0, -this.iArcSize - this._iWidth, 2 * (this._iWidth + this.iArcSize), 2 * (this._iWidth + this.iArcSize), 180, 90);
                            this.g.fillArc(bounds.width - (this._iWidth + this.iArcSize) * 2, -(this._iWidth + this.iArcSize), 2 * (this._iWidth + this.iArcSize), 2 * (this._iWidth + this.iArcSize), 0, -90);
                            this.g.setColor(this._cWhite);
                            this.g.fillArc(this._iWidth, -this.iArcSize, 2 * this.iArcSize, 2 * this.iArcSize, 180, 90);
                            this.g.fillArc(bounds.width - this._iWidth - this.iArcSize * 2, -this.iArcSize, 2 * this.iArcSize, 2 * this.iArcSize, 0, -90);
                        }
                    }
                }
            }
            if (this.iTitle > 0 && this.sTitle != null) {
                final int width = this.size().width;
                final int height = this.size().height;
                this.g.setFont(this.f);
                this.fontMetrics = this.getFontMetrics(this.f);
                this.g.setColor(this.CA.getForeground());
                int n = this._iWidth + (this._sType.equals("ROUND") ? this.iArcSize : 0);
                if (this.CA.iL != null) {
                    this.g.drawImage(this.CA.iL, n, this._iWidth, this);
                    n += this.CA.iL.getWidth(null);
                }
                int n2 = (this.iTitle == 3) ? 17 : (width / 2 - this.fontMetrics.stringWidth(this.sTitle) / 2);
                if (n2 < n) {
                    n2 = n;
                }
                final int n3 = height / 2 + this.fontMetrics.getHeight() / 2 - this.fontMetrics.getDescent() + this._iWidth / 2;
                this.g.setColor(this._cFont);
                if (this.z) {
                    GetGraphics.setText(this.g);
                }
                this.g.drawString(this.sTitle, (this.iMin > n2) ? this.iMin : n2, n3);
            }
            this._iOWidth = this.size().width;
            this._iOHeight = this.size().height;
            this.sOldMessage = this.sTitle;
        }
        graphics.drawImage(this._iImage, 0, 0, this);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void printAll(final Graphics graphics) {
        final Image image = this.createImage(graphics.getClipBounds().width, graphics.getClipBounds().height);
        final Graphics graphics2 = image.getGraphics();
        final Color cWhite = this._cWhite;
        this._cWhite = Color.white;
        this.paint(graphics2);
        this._cWhite = cWhite;
        graphics.drawImage(image, 0, 0, this);
    }
    
    public void setTitle(final String sTitle, final Color cFont) {
        this.sTitle = sTitle;
        this._cFont = cFont;
        if (this.CA != null) {
            this.f = this.CA.getTitleFont();
        }
        this.repaint();
    }
    
    public void setTitle(final String sTitle, final Color cFont, final Font f) {
        this.sTitle = sTitle;
        this._cFont = cFont;
        this.f = f;
        this.repaint();
    }
}
