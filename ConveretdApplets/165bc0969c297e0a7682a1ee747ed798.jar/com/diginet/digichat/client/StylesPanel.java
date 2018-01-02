// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import java.util.Vector;
import java.awt.Container;
import com.esial.util.c;
import java.awt.Frame;
import com.diginet.digichat.network.v;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Color;
import com.diginet.digichat.util.s;
import com.diginet.digichat.awt.GridPanel;

public class StylesPanel extends GridPanel implements s
{
    private Color clrText;
    private Color clrBack;
    private Color clrTextBase;
    private Color clrBackBase;
    private Font fntPlain;
    private Font fntBold;
    private Font fntItalic;
    private Font fntBoldItalic;
    private Canvas btnText;
    private Canvas btnBack;
    private Canvas btnBold;
    private Canvas btnItal;
    private TextPanel txpParent;
    private i iUser;
    
    public StylesPanel(final i iUser, final TextPanel txpParent) {
        super(2, 2);
        this.iUser = iUser;
        this.txpParent = txpParent;
        final Color color = null;
        this.clrBack = color;
        this.clrText = color;
        this.add(this.btnText = TotalButtons.createButton(iUser, "color", "colorTextIcon.gif", 16, 120), 0, 0);
        this.add(this.btnBack = TotalButtons.createButton(iUser, "back", "backTextIcon.gif", 16, 121), 0, 1);
        this.add(this.btnBold = TotalButtons.createCheck(iUser, "bold", "boldTextIcon.gif", 122), 1, 0);
        this.add(this.btnItal = TotalButtons.createCheck(iUser, "italic", "italicTextIcon.gif", 123), 1, 1);
    }
    
    private void setStyle() {
        final Font font;
        if ((font = this.txpParent.getFont()) != null) {
            boolean checked;
            boolean checked2;
            if (v.a(this.iUser.lPlus, 18) && this.iUser.i(89)) {
                checked = TotalButtons.getChecked(this.btnBold);
                checked2 = TotalButtons.getChecked(this.btnItal);
            }
            else {
                checked = (checked2 = false);
            }
            Font fontImp;
            if (checked && checked2) {
                if ((fontImp = this.fntBoldItalic) == null) {
                    fontImp = (this.fntBoldItalic = new Font(font.getName(), 3, font.getSize()));
                }
            }
            else if (checked) {
                if ((fontImp = this.fntBold) == null) {
                    fontImp = (this.fntBold = new Font(font.getName(), 1, font.getSize()));
                }
            }
            else if (checked2) {
                if ((fontImp = this.fntItalic) == null) {
                    fontImp = (this.fntItalic = new Font(font.getName(), 2, font.getSize()));
                }
            }
            else if ((fontImp = this.fntPlain) == null) {
                fontImp = (this.fntPlain = new Font(font.getName(), 0, font.getSize()));
            }
            this.txpParent.setFontImp(fontImp);
        }
    }
    
    public void setButtons() {
        this.btnText.setVisible(v.a(this.iUser.lPlus, 16) && this.iUser.i(90));
        this.btnBack.setVisible(v.a(this.iUser.lPlus, 17) && this.iUser.i(91));
        this.btnBold.setVisible(v.a(this.iUser.lPlus, 18) && this.iUser.i(89));
        this.btnItal.setVisible(v.a(this.iUser.lPlus, 18) && this.iUser.i(89));
        this.setStyle();
    }
    
    public void setBackground(final Color clrBackBase) {
        this.clrBackBase = clrBackBase;
    }
    
    public void setForeground(final Color clrTextBase) {
        this.clrTextBase = clrTextBase;
    }
    
    public void setFont(final Font font) {
        boolean b2;
        boolean b = b2 = false;
        final Font font2 = null;
        this.fntBoldItalic = font2;
        this.fntItalic = font2;
        this.fntBold = font2;
        this.fntPlain = font2;
        switch (font.getStyle()) {
            case 0: {
                this.fntPlain = font;
                break;
            }
            case 1: {
                b2 = true;
                this.fntBold = font;
                break;
            }
            case 2: {
                b = true;
                this.fntItalic = font;
                break;
            }
            case 3: {
                b = (b2 = true);
                this.fntBoldItalic = font;
                break;
            }
        }
        TotalButtons.setChecked(this.btnBold, b2);
        TotalButtons.setChecked(this.btnItal, b);
    }
    
    public long getStyle() {
        long n = 0L;
        if (v.a(this.iUser.lPlus, 18) && this.iUser.i(89)) {
            n = Long.MIN_VALUE;
            if (!TotalButtons.getChecked(this.btnBold) && !TotalButtons.getChecked(this.btnItal)) {
                n |= 0x0L;
            }
            if (TotalButtons.getChecked(this.btnBold)) {
                n |= 0x200000000000000L;
            }
            if (TotalButtons.getChecked(this.btnItal)) {
                n |= 0x400000000000000L;
            }
        }
        if (this.clrBack != null) {
            n |= (0x1000000L | (this.clrBack.getRGB() & 0xFFFFFF)) << 32;
        }
        if (this.clrText != null) {
            n |= (0x1000000 | (this.clrText.getRGB() & 0xFFFFFF));
        }
        return n;
    }
    
    private int getColor(final String s, final Color color, final int n) {
        Container parent = this;
        do {
            if ((parent = parent.getParent()) == null) {
                parent = new Frame();
            }
        } while (!(parent instanceof Frame));
        final Vector vector;
        return ((vector = this.iUser.palettes[n]) == null) ? new ColorDialog((Frame)parent, this.iUser).getResult(c.a(s), color) : new PaletteBox((Frame)parent, this.iUser, vector).getResult(c.a(s), color);
    }
    
    public String a(final Object o) {
        return c.a((o == this.btnText) ? "Click here to change color of text of message." : ((o == this.btnBack) ? "Click here to change background color of message." : ((o == this.btnBold) ? (TotalButtons.getChecked(this.btnBold) ? "Click here to do the font of message not bold." : "Click here to do the font of message to bold.") : ((o == this.btnItal) ? (TotalButtons.getChecked(this.btnItal) ? "Click here to do the font of message not italic." : "Click here to do the font of message to italic.") : null))));
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.btnText) {
                final int color;
                if ((color = this.getColor("Color of text of message.", (this.clrText == null) ? this.clrTextBase : this.clrText, (this.iUser.nBill != 2) ? 1 : 0)) != 0) {
                    final Color clrTextBase = this.clrTextBase;
                    this.txpParent.setForeground(this.clrText = new Color(color));
                    this.clrTextBase = clrTextBase;
                }
                return true;
            }
            if (event.target == this.btnBack) {
                final int color2;
                if ((color2 = this.getColor("Color of background of message.", (this.clrBack == null) ? this.clrBackBase : this.clrBack, (this.iUser.nBill == 2) ? 2 : 3)) != 0) {
                    final Color clrBackBase = this.clrBackBase;
                    this.txpParent.setBackground(this.clrBack = new Color(color2));
                    this.clrBackBase = clrBackBase;
                }
                return true;
            }
            if (event.target == this.btnItal || event.target == this.btnBold) {
                this.setStyle();
                return true;
            }
        }
        return super.handleEvent(event);
    }
}
