// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Rectangle;
import java.awt.Dimension;
import KJEcalculation.Calculation;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Panel;

public class BorderSide extends Panel
{
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    public static final String THREED = "3D";
    public static final String SOLID = "SOLID";
    public static final String ROUND = "ROUND";
    public static final String NONE = "NONE";
    public static int iArcSize;
    protected String sTitle;
    protected boolean bTitle;
    public boolean bButton;
    private KJEButton b;
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
    int _iOWidth;
    int _iOHeight;
    
    static {
        BorderSide.iArcSize = 8;
    }
    
    public BorderSide(final CalculatorApplet ca, final String s, final int iOrientation, int iWidth, final Color cBlack, final Color cWhite, final Color cPage, final Color cFont, final String sType) {
        this.bTitle = false;
        this.bButton = false;
        this.iMin = 0;
        this._iImage = null;
        this.g = null;
        this.sOldMessage = "";
        this._iOWidth = 0;
        this._iOHeight = 0;
        if (iWidth < 0) {
            iWidth = 0;
        }
        if (sType.equals("3D")) {
            iWidth = 3;
        }
        this._sType = sType;
        this._iOrientation = iOrientation;
        this._iWidth = iWidth;
        final int n = (this._sType.equals("ROUND") ? BorderSide.iArcSize : 0) + this._iWidth;
        this._iMinY = n;
        this._iMinX = n;
        this._cWhite = cWhite;
        this._cBlack = cBlack;
        this._cFont = cFont;
        this._cPage = cPage;
        if (ca != null) {
            this.CA = ca;
            this.bTitle = true;
            final FontMetrics fontMetrics = this.getFontMetrics(ca.getTitleFont());
            this.f = ca.getTitleFont();
            this.sf = ca.getBoldFont();
            int height = 0;
            if (this.CA.iL != null) {
                height = this.CA.iL.getHeight(ca);
            }
            this._iMinX = BorderSide.iArcSize + this._iWidth;
            this._iMinY = iWidth + (((height < (int)(fontMetrics.getHeight() * 1.75)) ? ((int)(fontMetrics.getHeight() * 1.75)) : height) + (this._sType.equals("ROUND") ? (BorderSide.iArcSize / 2) : 0));
        }
        if (s != null) {
            this.bButton = true;
            (this.b = new KJEButton(s)).setFont(ca.getPlainFont());
            final FontMetrics fontMetrics2 = this.getFontMetrics(ca.getPlainFont());
            final int n2 = (int)(fontMetrics2.stringWidth(s) * 1.5);
            this.setLayout(null);
            this.b.setBounds(5, 3, n2, (int)((fontMetrics2.getHeight() + fontMetrics2.getDescent()) * 1.8));
            this.iMin = n2 + 6;
            this.add(this.b);
        }
        this.resize(this._iMinX, this._iMinY);
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.b != null && event.target == this.b) {
            final String sJavaScriptReport = this.CA.sJavaScriptReport("HTML");
            final Calculation cc = this.CA.CC;
            final String replace = Calculation.replace("HTTP-EQUIV", "IGNORE", sJavaScriptReport);
            final Calculation cc2 = this.CA.CC;
            final String replace2 = Calculation.replace("</H1><P>", "</H1>", replace);
            final Calculation cc3 = this.CA.CC;
            final String replace3 = Calculation.replace("<H1>", "<BR><DIV class=large>", replace2);
            final Calculation cc4 = this.CA.CC;
            final String replace4 = Calculation.replace("</H1>", "</DIV><BR>", replace3);
            final Calculation cc5 = this.CA.CC;
            final String replace5 = Calculation.replace("<P><DIV", "<BR><DIV", replace4);
            final Calculation cc6 = this.CA.CC;
            final String replace6 = Calculation.replace("DIV.", ".", replace5);
            final Calculation cc7 = this.CA.CC;
            final String replace7 = Calculation.replace("P.", ".", replace6);
            final Calculation cc8 = this.CA.CC;
            String s = Calculation.replace("<img src='", "<img src='" + this.CA.getCodeBase(), replace7);
            for (int i = 15; i >= 9; --i) {
                final Calculation cc9 = this.CA.CC;
                s = Calculation.replace(String.valueOf(i) + "pt;", String.valueOf(i + 2) + "pt;", s);
            }
            final Calculation cc10 = this.CA.CC;
            final ShowHtml showHtml = new ShowHtml(this.sTitle, Calculation.replace("7.5pt;", "9pt;", s));
            return true;
        }
        return super.action(event, o);
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
            this.g = this._iImage.getGraphics();
        }
        boolean b = false;
        final boolean bTitle = this.sTitle != null;
        this.bTitle = bTitle;
        if (bTitle) {
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
                        this.g.fillRect(0, 0, this._iWidth + BorderSide.iArcSize, this._iWidth + BorderSide.iArcSize);
                        this.g.fillRect(bounds.width - this._iWidth - BorderSide.iArcSize, 0, this._iWidth + BorderSide.iArcSize, this._iWidth + BorderSide.iArcSize);
                        this.g.setColor(this._cBlack);
                        if (this._iOrientation == 1) {
                            this.g.fillRect(0, BorderSide.iArcSize, this._iWidth, bounds.height - BorderSide.iArcSize);
                            this.g.fillRect(bounds.width - this._iWidth, BorderSide.iArcSize, this._iWidth, bounds.height - BorderSide.iArcSize);
                            this.g.fillRect(BorderSide.iArcSize + this._iWidth, 0, bounds.width - 2 * (this._iWidth + BorderSide.iArcSize), this._iWidth);
                            this.g.fillArc(0, 0, 2 * (this._iWidth + BorderSide.iArcSize), 2 * (this._iWidth + BorderSide.iArcSize), 90, 90);
                            this.g.fillArc(bounds.width - (BorderSide.iArcSize + this._iWidth) * 2, 0, 2 * (this._iWidth + BorderSide.iArcSize), 2 * (this._iWidth + BorderSide.iArcSize), 0, 90);
                            this.g.setColor(this._cWhite);
                            this.g.fillArc(this._iWidth, this._iWidth, 2 * BorderSide.iArcSize, 2 * BorderSide.iArcSize, 90, 90);
                            this.g.fillArc(bounds.width - (BorderSide.iArcSize + this._iWidth) * 2 + this._iWidth, this._iWidth, 2 * BorderSide.iArcSize, 2 * BorderSide.iArcSize, 0, 90);
                        }
                        else {
                            this.g.fillRect(BorderSide.iArcSize + this._iWidth, bounds.height - this._iWidth, bounds.width - 2 * (this._iWidth + BorderSide.iArcSize), bounds.height);
                            this.g.fillArc(0, -BorderSide.iArcSize - this._iWidth, 2 * (this._iWidth + BorderSide.iArcSize), 2 * (this._iWidth + BorderSide.iArcSize), 180, 90);
                            this.g.fillArc(bounds.width - (this._iWidth + BorderSide.iArcSize) * 2, -(this._iWidth + BorderSide.iArcSize), 2 * (this._iWidth + BorderSide.iArcSize), 2 * (this._iWidth + BorderSide.iArcSize), 0, -90);
                            this.g.setColor(this._cWhite);
                            this.g.fillArc(this._iWidth, -BorderSide.iArcSize, 2 * BorderSide.iArcSize, 2 * BorderSide.iArcSize, 180, 90);
                            this.g.fillArc(bounds.width - this._iWidth - BorderSide.iArcSize * 2, -BorderSide.iArcSize, 2 * BorderSide.iArcSize, 2 * BorderSide.iArcSize, 0, -90);
                        }
                    }
                }
                else if (this._sType.equals("3D")) {
                    this.g.setColor(this._cBlack);
                    if (this._iOrientation == 1) {
                        this.g.fillRect(0, 0, bounds.width, this._iWidth);
                        this.g.fillRect(0, 0, this._iWidth, bounds.height);
                        this.g.fillRect(bounds.width - this._iWidth, 0, this._iWidth, bounds.height);
                        this.g.setColor(Color.gray);
                        this.g.fillRect(bounds.width - 1, 0, 1, bounds.height);
                    }
                    else if (this._iOrientation == 4) {
                        this.g.fillRect(0, 0, bounds.width, bounds.height);
                        this.g.setColor(Color.gray);
                        this.g.fillRect(bounds.width - 1, 0, 1, bounds.height);
                    }
                    else if (this._iOrientation == 2) {
                        this.g.fillRect(0, 0, bounds.width, bounds.height);
                        this.g.setColor(Color.gray);
                        this.g.fillRect(0, bounds.height - 1, bounds.width, 1);
                        this.g.fillRect(bounds.width - 1, 0, 1, bounds.height);
                    }
                    else {
                        this.g.fillRect(0, 0, bounds.width, bounds.height);
                    }
                }
            }
            if (this.bTitle && this.sTitle != null) {
                final int width = this.size().width;
                final int height = this.size().height;
                this.g.setFont(this.f);
                this.fontMetrics = this.getFontMetrics(this.f);
                this.g.setColor(this.CA.getForeground());
                if (this.CA.iL != null && !this.bButton) {
                    this.g.drawImage(this.CA.iL, this._iWidth + (this._sType.equals("ROUND") ? BorderSide.iArcSize : 0), this._iWidth, this);
                }
                final int n = width / 2 - this.fontMetrics.stringWidth(this.sTitle) / 2;
                final int n2 = height / 2 + this.fontMetrics.getHeight() / 2 - this.fontMetrics.getDescent() + this._iWidth / 2;
                this.g.setColor(this._cFont);
                this.g.drawString(this.sTitle, (this.iMin > n) ? this.iMin : n, n2);
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
    
    public void setTitle(final String sTitle, final Color cWhite) {
        this.sTitle = sTitle;
        this._cWhite = cWhite;
        if (this.CA != null) {
            this.f = this.CA.getTitleFont();
        }
        this.repaint();
    }
    
    public void setTitle(final String sTitle, final Color cWhite, final Font f) {
        this.sTitle = sTitle;
        this._cWhite = cWhite;
        this.f = f;
        this.repaint();
    }
}
