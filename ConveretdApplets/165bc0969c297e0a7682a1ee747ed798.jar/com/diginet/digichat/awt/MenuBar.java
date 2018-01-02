// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Window;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.awt.Canvas;

public class MenuBar extends Canvas implements Runnable, MenuController
{
    private static final int N_SPACE = 6;
    private boolean fRight;
    private boolean fActive;
    private int iSelect;
    private Color clrHghlText;
    private Color clrHghlBack;
    private Color clrLight;
    private Color clrDark;
    private Vector vecLabels;
    private Vector vecMenus;
    private Vector vecRects;
    private MenuPanel mnuSelect;
    
    public void setStyle(final Font font, final Color color, final Color color2, final Color clrHghlText, final Color clrHghlBack) {
        super.setFont(font);
        super.setForeground((color == null) ? SystemColor.menuText : color);
        Color menu;
        Color background;
        if (color2 == null) {
            background = (menu = SystemColor.menu);
        }
        else {
            background = color2;
            menu = color2;
        }
        final Color color3 = menu;
        super.setBackground(background);
        final Color color4 = color3;
        final Color darker = color3.darker().darker();
        this.clrDark = darker;
        if (color4.equals(darker)) {
            this.clrDark = Color.black;
        }
        final Color color5 = color3;
        final Color brighter = color3.brighter().brighter();
        this.clrLight = brighter;
        if (color5.equals(brighter)) {
            this.clrLight = Color.white;
        }
        if ((this.clrHghlText = clrHghlText) == null) {
            this.clrHghlText = SystemColor.textHighlightText;
        }
        if ((this.clrHghlBack = clrHghlBack) == null) {
            this.clrHghlBack = SystemColor.textHighlight;
        }
        for (int i = 0; i < this.vecMenus.size(); ++i) {
            ((MenuPanel)this.vecMenus.elementAt(i)).setStyle(font, color, color2, clrHghlText, clrHghlBack);
        }
        this.repaint();
    }
    
    public MenuBar() {
        this.iSelect = -1;
        this.mnuSelect = null;
        final boolean b = false;
        this.fActive = b;
        this.fRight = b;
        this.vecMenus = new Vector();
        this.vecRects = new Vector();
        this.vecLabels = new Vector();
        this.setStyle(this.getFont(), null, null, null, null);
    }
    
    public void add(final String s, final MenuPanel menuPanel) {
        this.vecLabels.addElement(s);
        this.vecMenus.addElement(menuPanel);
        this.vecRects.addElement(new Rectangle());
        menuPanel.setDirect(this.fRight);
        menuPanel.setStyle(this.getFont(), this.getForeground(), this.getBackground(), this.clrHghlText, this.clrHghlBack);
    }
    
    private boolean setSelect(final Event event) {
        int iSelect = -1;
        for (int i = 0; i < this.vecRects.size(); ++i) {
            if (((Rectangle)this.vecRects.elementAt(i)).contains(event.x, event.y)) {
                iSelect = i;
                break;
            }
        }
        if (this.iSelect == iSelect) {
            return false;
        }
        this.iSelect = iSelect;
        return true;
    }
    
    public void setDirect(final boolean b) {
        this.fRight = b;
        for (int i = 0; i < this.vecMenus.size(); ++i) {
            ((MenuPanel)this.vecMenus.elementAt(i)).setDirect(b);
        }
        this.repaint();
    }
    
    public void setLabel(final String s, final int n) {
        this.vecLabels.setElementAt(s, n);
        this.repaint();
    }
    
    public void hidePopup(final Event select) {
        this.fActive = false;
        if (this.mnuSelect != null) {
            this.mnuSelect.hide();
            this.mnuSelect = null;
            if (select == null) {
                this.iSelect = -1;
            }
            else {
                this.setSelect(select);
            }
            this.repaint();
        }
    }
    
    public void setActive(final boolean b) {
        if (b) {
            this.fActive = true;
        }
        else {
            this.hidePopup(null);
        }
    }
    
    public MenuPanel get(final int n) {
        return this.vecMenus.elementAt(n);
    }
    
    public Dimension size() {
        final Dimension size = super.size();
        Font font;
        final Container parent;
        if ((font = this.getFont()) == null && (parent = this.getParent()) != null) {
            font = parent.getFont();
        }
        if (font != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            size.height = fontMetrics.getHeight() * 3 - (fontMetrics.getAscent() << 1) + 3;
        }
        return size;
    }
    
    public void setFont(final Font font) {
    }
    
    public void setForeground(final Color color) {
    }
    
    public void setBackground(final Color color) {
    }
    
    public void run() {
        int n = 0;
        while (this.fActive) {
            Component parent = this;
            while ((parent = parent.getParent()) != null && !(parent instanceof Window)) {}
            if (parent == null) {
                if (n != 0) {
                    this.hidePopup(null);
                    return;
                }
                n = 1;
            }
            else {
                final Component focusOwner;
                if ((focusOwner = ((Window)parent).getFocusOwner()) != this) {
                    if (!(focusOwner instanceof MenuPanel)) {
                        this.hidePopup(null);
                    }
                    return;
                }
                n = 0;
            }
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean handleEvent(final Event select) {
        switch (select.id) {
            case 1004: {
                this.fActive = true;
                break;
            }
            case 1005: {
                new Thread(this).start();
                break;
            }
            case 503: {
                if (this.mnuSelect == null && this.setSelect(select)) {
                    this.repaint();
                }
                break;
            }
            case 501: {
                if (this.iSelect >= 0 && this.mnuSelect == null) {
                    final Rectangle bounds = this.getBounds();
                    (this.mnuSelect = this.vecMenus.elementAt(this.iSelect)).show((LayeredContainer)this.getParent(), this.getParent(), this, bounds.x + (this.fRight ? bounds.width : 0), bounds.y + bounds.height);
                    this.repaint();
                    return true;
                }
                this.hidePopup(select);
                break;
            }
        }
        return super.handleEvent(select);
    }
    
    private int drawItem(final int n, int n2, final int n3, final int n4, final Graphics graphics, final FontMetrics fontMetrics) {
        final String s;
        final int n5 = fontMetrics.stringWidth(s = this.vecLabels.elementAt(n)) + 12 + 2;
        if (this.fRight) {
            n2 -= n5;
        }
        Color color2;
        Color color;
        if (n != this.iSelect) {
            color = (color2 = this.getBackground());
        }
        else if (this.mnuSelect == null) {
            color2 = this.clrLight;
            color = this.clrDark;
        }
        else {
            color2 = this.clrDark;
            color = this.clrLight;
        }
        graphics.setColor(color2);
        graphics.drawLine(n2, 1, n2, n4);
        graphics.drawLine(n2, 1, n2 + n5, 1);
        graphics.setColor(color);
        graphics.drawLine(n2 + n5, 1, n2 + n5, n4);
        graphics.drawLine(n2, n4, n2 + n5, n4);
        graphics.setColor(this.getForeground());
        graphics.drawString(s, n2 + 6 + 1, n3 + 1);
        this.vecRects.elementAt(n).setBounds(n2 + 1, 1, n5 - 2, n4 - 1);
        return n5;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size;
        final int n = (size = this.size()).height - 1;
        final FontMetrics fontMetrics;
        final int height = (fontMetrics = graphics.getFontMetrics()).getHeight();
        if (this.fRight) {
            int i = this.vecLabels.size() - 1;
            int n2 = size.width - 1;
            while (i >= 0) {
                n2 -= this.drawItem(i, n2, height, n, graphics, fontMetrics);
                --i;
            }
        }
        else {
            int j = 0;
            int n3 = 0;
            while (j < this.vecLabels.size()) {
                n3 += this.drawItem(j, n3, height, n, graphics, fontMetrics);
                ++j;
            }
        }
    }
}
