// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Font;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;

public class MenuPanel extends Canvas implements Runnable
{
    private static final int TICK_WIDTH = 9;
    private static final int ICON_WIDTH = 16;
    private static final int ICON_HEIGHT = 16;
    private boolean fRight;
    private boolean fDraw;
    private int iSelect;
    private int nWidth;
    private int nHeight;
    private int nX;
    private int nY;
    private int nSuffW;
    private int nArrowY;
    private int nTextY;
    private int nItemH;
    private int nImageY;
    private Component cmpTarget;
    private Color clrHghlText;
    private Color clrHghlBack;
    private Image imgBuf;
    private Graphics gBuf;
    private Vector vecItems;
    private Thread trdExec;
    private LayeredContainer lrcParent;
    private MenuController ctrParent;
    private MenuItem itmEvent;
    private MenuPanel mnuSelect;
    
    public void setStyle(final Font font, final Color color, final Color color2, final Color clrHghlText, final Color clrHghlBack) {
        this.nWidth = -1;
        super.setFont(font);
        super.setForeground((color == null) ? SystemColor.menuText : color);
        final SystemColor menu;
        super.setBackground((color2 == null) ? (menu = SystemColor.menu) : color2);
        this.clrHghlText = clrHghlText;
        if (clrHghlText == null) {
            this.clrHghlText = SystemColor.textHighlightText;
        }
        if ((this.clrHghlBack = clrHghlBack) == null) {
            this.clrHghlBack = SystemColor.textHighlight;
        }
        for (int i = 0; i < this.vecItems.size(); ++i) {
            final MenuItem menuItem;
            if ((menuItem = this.vecItems.elementAt(i)) != null) {
                menuItem.setStyle(font, color, color2, clrHghlText, clrHghlBack);
            }
        }
        this.repaint();
    }
    
    public MenuPanel() {
        this.vecItems = new Vector();
        final int n = -1;
        this.nWidth = n;
        this.iSelect = n;
        this.lrcParent = null;
        this.cmpTarget = null;
        this.mnuSelect = null;
        this.imgBuf = null;
        final boolean b = false;
        this.fDraw = b;
        this.fRight = b;
        this.setStyle(this.getFont(), null, null, null, null);
    }
    
    public void check(final int n, final boolean b) {
        for (int i = 0; i < this.vecItems.size(); ++i) {
            ((MenuItem)this.vecItems.elementAt(i)).check(b && i == n);
        }
    }
    
    public void check(final int n) {
        this.check(n, true);
    }
    
    private void setItem(final MenuItem menuItem, final int n) {
        if (menuItem != null) {
            menuItem.setDirect(this.fRight);
            menuItem.setStyle(this.getFont(), this.getForeground(), this.getBackground(), this.clrHghlText, this.clrHghlBack);
            if (menuItem.isChecked()) {
                this.check(n);
            }
        }
    }
    
    public void add(final MenuItem menuItem) {
        this.nWidth = -1;
        this.vecItems.addElement(menuItem);
        this.setItem(menuItem, this.vecItems.size() - 1);
    }
    
    public void addSeparator() {
        this.add((MenuItem)null);
    }
    
    public void insert(final int n, final MenuItem menuItem) {
        this.nWidth = -1;
        this.vecItems.insertElementAt(menuItem, n);
        this.setItem(menuItem, n);
    }
    
    public void set(final int n, final MenuItem menuItem) {
        this.nWidth = -1;
        this.vecItems.elementAt(n).set(menuItem);
        this.setItem(menuItem, n);
    }
    
    public void insertSeparator(final int n) {
        this.insert(n, null);
    }
    
    public void remove(final int n) {
        this.nWidth = -1;
        this.vecItems.removeElementAt(n);
    }
    
    public void remove(final Object o) {
        this.nWidth = -1;
        this.vecItems.removeElement(o);
    }
    
    public int length() {
        return this.vecItems.size();
    }
    
    public String getLabel(final int n) {
        return this.vecItems.elementAt(n).getLabel();
    }
    
    public int indexOf(final Object o) {
        return this.vecItems.indexOf(o);
    }
    
    public void setDirect(final boolean b) {
        this.fRight = b;
        for (int i = 0; i < this.vecItems.size(); ++i) {
            final MenuItem menuItem;
            if ((menuItem = this.vecItems.elementAt(i)) != null) {
                menuItem.setDirect(b);
            }
        }
        this.repaint();
    }
    
    public void setFont(final Font font) {
    }
    
    public void setForeground(final Color color) {
    }
    
    public void setBackground(final Color color) {
    }
    
    private void setLocation() {
        int nx = this.nX;
        int ny = this.nY;
        if (!(this.ctrParent instanceof MenuBar)) {
            final Rectangle area = this.lrcParent.getArea();
            if (this.fRight) {
                int n;
                if ((n = nx - this.nWidth) >= area.x || nx > (n = area.x + area.width - this.nWidth)) {
                    nx = n;
                }
            }
            else if (nx + this.nWidth > area.x + area.width) {
                final int n2;
                nx = (((n2 = nx - this.nWidth) < area.x) ? area.x : n2);
            }
            if (ny + this.nHeight > area.y + area.height) {
                final int n3;
                ny = (((n3 = ny - this.nHeight) < area.y) ? area.y : n3);
            }
        }
        else if (this.fRight) {
            nx -= this.nWidth;
        }
        this.setLocation(nx, ny);
    }
    
    private void drawImage(final Image image, final int n, final int n2, final int n3, final int n4) {
        int width = image.getWidth(this);
        int height;
        if ((height = image.getHeight(this)) > n4 || width > n3) {
            double n5;
            final double n6;
            if ((n5 = n3 / width) > (n6 = n4 / height)) {
                n5 = n6;
            }
            width *= (int)n5;
            height *= (int)n5;
        }
        this.gBuf.drawImage(image, n + (n3 - width >> 1), n2 + (n4 - height >> 1) + this.nImageY, width, height, this);
    }
    
    private void createMenu() {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (this.nWidth < 0) {
            this.fDraw = false;
            final int height;
            this.nItemH = (((height = fontMetrics.getHeight()) < 18) ? 18 : height);
            this.nTextY = (this.nItemH - height >> 1) + fontMetrics.getAscent();
            this.nImageY = this.nItemH - 16 >> 1;
            this.nArrowY = this.nItemH - 10 >> 1;
            final int nSuffW = 0;
            this.nSuffW = nSuffW;
            int n = nSuffW;
            int n2 = nSuffW;
            final int size = this.vecItems.size();
            for (int i = 0; i < size; ++i) {
                final MenuItem menuItem;
                if ((menuItem = this.vecItems.elementAt(i)) == null) {
                    n2 += 4;
                }
                else {
                    final int stringWidth;
                    if ((stringWidth = fontMetrics.stringWidth(menuItem.getLabel())) > n) {
                        n = stringWidth;
                    }
                    final String suff;
                    final int stringWidth2;
                    if ((suff = menuItem.getSuff()) != null && (stringWidth2 = fontMetrics.stringWidth(suff)) > this.nSuffW) {
                        this.nSuffW = stringWidth2;
                    }
                    n2 += this.nItemH;
                }
            }
            int n3 = n + 9 + 16 + 32;
            if (this.nSuffW > 0) {
                final int n4 = n3;
                final int nSuffW2 = this.nSuffW + 16;
                this.nSuffW = nSuffW2;
                n3 = n4 + nSuffW2;
            }
            this.nHeight = n2 + 6;
            int n5 = 3;
            for (int j = 0; j < size; ++j) {
                final MenuItem menuItem2;
                if ((menuItem2 = this.vecItems.elementAt(j)) == null) {
                    n5 += 4;
                }
                else {
                    menuItem2.setBounds(3, n5, n3, this.nItemH);
                    n5 += this.nItemH;
                }
            }
            final int nWidth = n3 + 6;
            this.nWidth = nWidth;
            final Image image = this.createImage(nWidth, this.nHeight);
            this.imgBuf = image;
            this.gBuf = image.getGraphics();
            this.setSize(this.nWidth, this.nHeight);
            this.setLocation();
        }
        this.gBuf.setColor(this.getBackground());
        final int nWidth2;
        final int nHeight;
        this.gBuf.fillRect(0, 0, nWidth2 = this.nWidth, nHeight = this.nHeight);
        this.gBuf.setColor(SystemColor.control);
        this.gBuf.drawLine(0, nHeight - 2, 0, 0);
        this.gBuf.drawLine(1, 0, nWidth2 - 2, 0);
        this.gBuf.setColor(SystemColor.controlDkShadow);
        this.gBuf.drawLine(nWidth2 - 1, 0, nWidth2 - 1, nHeight - 1);
        this.gBuf.drawLine(nWidth2 - 2, nHeight - 1, 0, nHeight - 1);
        this.gBuf.setColor(SystemColor.controlLtHighlight);
        this.gBuf.drawLine(1, nHeight - 3, 1, 1);
        this.gBuf.drawLine(1, 1, nWidth2 - 3, 1);
        this.gBuf.setColor(SystemColor.controlShadow);
        this.gBuf.drawLine(nWidth2 - 2, 1, nWidth2 - 2, nHeight - 2);
        this.gBuf.drawLine(nWidth2 - 3, nHeight - 2, 1, nHeight - 2);
        final int size2 = this.vecItems.size();
        int n6 = 3;
        for (int k = 0; k < size2; ++k) {
            final MenuItem menuItem3;
            if ((menuItem3 = this.vecItems.elementAt(k)) == null) {
                this.gBuf.setColor(SystemColor.controlDkShadow);
                ++n6;
                this.gBuf.drawLine(4, n6, nWidth2 - 5, n6);
                ++n6;
                this.gBuf.setColor(SystemColor.controlLtHighlight);
                this.gBuf.drawLine(4, n6, nWidth2 - 5, n6);
                n6 += 2;
            }
            else {
                int n7;
                int n8;
                int n20;
                if (this.fRight) {
                    final String suff2;
                    n7 = this.nSuffW - (((suff2 = menuItem3.getSuff()) == null) ? 0 : fontMetrics.stringWidth(suff2));
                    n8 = this.nWidth - 16 - 9 - 4;
                    final int[] array3 = array;
                    final int n9 = 1;
                    final int[] array4 = array;
                    final int n10 = 2;
                    final int[] array5 = array;
                    final int n11 = 3;
                    final int[] array6 = array;
                    final int n12 = 0;
                    final int n13 = 13;
                    array5[n11] = (array6[n12] = n13);
                    array3[n9] = (array4[n10] = n13 - 5);
                    final int[] array7 = array2;
                    final int n14 = 3;
                    final int[] array8 = array2;
                    final int n15 = 1;
                    final int[] array9 = array2;
                    final int n16 = 2;
                    final int[] array10 = array2;
                    final int n17 = 0;
                    final int n18 = n6 + this.nArrowY;
                    array10[n17] = n18;
                    final int n19 = n18 + 4;
                    array9[n16] = n19;
                    array7[n14] = (array8[n15] = n19 + 1) + 4;
                    n20 = this.nWidth - fontMetrics.stringWidth(menuItem3.getLabel()) - 9 - 16 - 13;
                }
                else {
                    final int[] array11 = array2;
                    final int n21 = 3;
                    final int[] array12 = array2;
                    final int n22 = 1;
                    final int[] array13 = array2;
                    final int n23 = 2;
                    final int[] array14 = array2;
                    final int n24 = 0;
                    final int n25 = n6 + this.nArrowY;
                    array14[n24] = n25;
                    final int n26 = n25 + 4;
                    array13[n23] = n26;
                    array11[n21] = (array12[n22] = n26 + 1) + 4;
                    final int[] array15 = array;
                    final int n27 = 1;
                    final int[] array16 = array;
                    final int n28 = 2;
                    final int[] array17 = array;
                    final int n29 = 3;
                    final int[] array18 = array;
                    final int n30 = 0;
                    final int n31 = nWidth2 - 13;
                    array17[n29] = (array18[n30] = n31);
                    array15[n27] = (array16[n28] = n31 + 5);
                    n20 = 37;
                    n8 = 22;
                    n7 = this.nWidth - this.nSuffW;
                }
                if (menuItem3.isEnabled()) {
                    final int n32 = this.fRight ? (this.nWidth - 16 - 4) : 4;
                    final Image image2;
                    if ((image2 = menuItem3.getImage()) != null) {
                        this.drawImage(image2, n32, n6, 16, 16);
                    }
                    int n34;
                    int n33 = nWidth2 - (n34 = 3) - 3;
                    if (image2 != null) {
                        if (this.fRight) {
                            n33 = nWidth2 - 16 - 9;
                        }
                        else {
                            n34 = 22;
                            n33 = nWidth2 - 16 - 9;
                        }
                    }
                    if (this.fDraw && k == this.iSelect) {
                        if (image2 != null) {
                            this.gBuf.setColor(SystemColor.controlLtHighlight);
                            final int n35;
                            this.gBuf.drawLine(n32 - 1, (n35 = n6 + this.nItemH - 1) - 1, n32 - 1, n6);
                            this.gBuf.drawLine(n32, n6, n32 + 16 - 1, n6);
                            this.gBuf.setColor(SystemColor.controlDkShadow);
                            this.gBuf.drawLine(n32 + 16, n6, n32 + 16, n35);
                            this.gBuf.drawLine(n32 + 16 - 1, n35, n32 - 1, n35);
                        }
                        this.gBuf.setColor(this.clrHghlBack);
                        this.gBuf.fillRect(n34, n6, n33, this.nItemH);
                        this.gBuf.setColor(this.clrHghlText);
                    }
                    else {
                        final Color back;
                        if ((back = menuItem3.getBack()) != null) {
                            this.gBuf.setColor(back);
                            this.gBuf.fillRect(n34, n6, n33, this.nItemH);
                        }
                        final Color text;
                        this.gBuf.setColor(((text = menuItem3.getText()) == null) ? this.getForeground() : text);
                    }
                }
                else {
                    final Image image3;
                    if ((image3 = menuItem3.getImage()) != null) {
                        this.drawImage(image3, this.fRight ? (this.nWidth - 16 - 2) : 0, n6, 17, 17);
                    }
                    this.gBuf.setColor(SystemColor.controlLtHighlight);
                    if (menuItem3.isChecked()) {
                        for (int l = 0, n36 = n6 + (this.nItemH - 7 >> 1) + 3; l < 7; n36 += ((l++ < 2) ? 1 : -1)) {
                            this.gBuf.drawLine(l + n8 + 1, n36, l + n8 + 1, n36 + 3);
                        }
                    }
                    this.gBuf.drawString(menuItem3.getLabel(), n20 + 1, n6 + this.nTextY + 1);
                    final String suff3;
                    if ((suff3 = menuItem3.getSuff()) != null) {
                        this.gBuf.drawString(suff3, n7 + 1, n6 + this.nTextY + 1);
                    }
                    if (menuItem3.getMenu() != null) {
                        this.gBuf.drawLine(array[3] + 1, array2[3] - 1, array[1] - 1, array2[1] + 1);
                        this.gBuf.drawLine(array[3] + 1, array2[3], array[1], array2[1] + 1);
                    }
                    this.gBuf.setColor(SystemColor.controlDkShadow);
                }
                if (menuItem3.isChecked()) {
                    for (int n37 = 0, n38 = n6 + (this.nItemH - 7 >> 1) + 2; n37 < 7; n38 += ((n37++ < 2) ? 1 : -1)) {
                        this.gBuf.drawLine(n37 + n8, n38, n37 + n8, n38 + 3);
                    }
                }
                this.gBuf.drawString(menuItem3.getLabel(), n20, n6 + this.nTextY);
                final String suff4;
                if ((suff4 = menuItem3.getSuff()) != null) {
                    this.gBuf.drawString(suff4, n7, n6 + this.nTextY);
                }
                if (menuItem3.getMenu() != null) {
                    this.gBuf.fillPolygon(array, array2, 4);
                }
                n6 += this.nItemH;
            }
        }
    }
    
    public void show(final LayeredContainer lrcParent, final Component cmpTarget, final MenuController ctrParent, final int nx, final int ny) {
        this.nX = nx;
        this.nY = ny;
        this.iSelect = -1;
        this.ctrParent = ctrParent;
        this.cmpTarget = cmpTarget;
        (this.lrcParent = lrcParent).add(this, 0);
        if (this.nWidth > 0) {
            this.setLocation();
        }
        this.createMenu();
    }
    
    public void hide() {
        if (this.mnuSelect != null) {
            this.mnuSelect.hide();
            this.mnuSelect = null;
        }
        if (this.lrcParent != null) {
            this.lrcParent.remove(this);
            this.lrcParent = null;
        }
    }
    
    public void run() {
        if (Thread.currentThread() == this.trdExec) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.cmpTarget.postEvent(new Event(this.itmEvent, 1001, null));
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                final MenuItem itmEvent;
                if (this.iSelect >= 0 && (itmEvent = this.vecItems.elementAt(this.iSelect)) != null && itmEvent.isEnabled() && itmEvent.getMenu() == null) {
                    this.itmEvent = itmEvent;
                    this.ctrParent.hidePopup(null);
                    (this.trdExec = new Thread(this)).start();
                }
                else {
                    this.ctrParent.requestFocus();
                }
                break;
            }
            case 503:
            case 504: {
                int iSelect = -1;
                for (int size = this.vecItems.size(), i = 0; i < size; ++i) {
                    final MenuItem menuItem;
                    if ((menuItem = this.vecItems.elementAt(i)) != null && menuItem.contains(event.x, event.y)) {
                        iSelect = i;
                        break;
                    }
                }
                if (this.iSelect == iSelect) {
                    break;
                }
                this.iSelect = iSelect;
                if (this.mnuSelect != null) {
                    this.mnuSelect.hide();
                    this.mnuSelect = null;
                }
                this.createMenu();
                this.repaint();
                final MenuItem menuItem2;
                if (iSelect >= 0 && (menuItem2 = this.vecItems.elementAt(iSelect)) != null && menuItem2.isEnabled() && (this.mnuSelect = menuItem2.getMenu()) != null) {
                    final Rectangle bounds = menuItem2.getBounds();
                    this.mnuSelect.show(this.lrcParent, this.cmpTarget, this.ctrParent, this.fRight ? (this.nX - this.nWidth + 3) : (this.nX + bounds.x + bounds.width), this.nY + bounds.y);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.imgBuf != null) {
            graphics.drawImage(this.imgBuf, 0, 0, this);
            this.fDraw = true;
        }
    }
}
