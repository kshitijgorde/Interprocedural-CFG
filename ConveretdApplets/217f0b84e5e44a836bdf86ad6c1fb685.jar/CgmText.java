import java.awt.geom.AffineTransform;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class CgmText extends CgmPrimitive
{
    String Content;
    int TextAlignHor;
    int state;
    HersheyFont hf;
    CgmViewApplet applet;
    double CharacterExpansion;
    double CharacterSpacing;
    double CharacterHeight;
    double as;
    double ac;
    double aa;
    double extW;
    double extH;
    int TextPath;
    double CharOri;
    double CharSlant;
    boolean underlined;
    boolean selected;
    String fontname;
    int style;
    int xe;
    int ye;
    int xr;
    int yr;
    CgmText Previous;
    
    CgmText(final double extW, final double extH, final double n, final double n2, final double n3, final double charOri, final double charSlant, final double characterSpacing, final String content, final int textAlignHor, int n4, final int textPath) {
        this.state = 0;
        this.hf = null;
        this.CharacterExpansion = 1.0;
        this.CharacterSpacing = 0.0;
        this.as = 0.0;
        this.ac = 1.0;
        this.aa = 0.0;
        this.extW = 0.0;
        this.extH = 0.0;
        this.xe = 0;
        this.ye = 0;
        this.xr = 0;
        this.yr = 0;
        this.CharacterHeight = n3;
        this.TextAlignHor = textAlignHor;
        this.TextPath = textPath;
        if (textAlignHor == 0) {
            switch (textPath) {
                case 1: {
                    this.TextAlignHor = 3;
                    break;
                }
                case 2: {
                    this.TextAlignHor = 2;
                    break;
                }
                default: {
                    this.TextAlignHor = 1;
                    break;
                }
            }
        }
        if (n4 == 0) {
            n4 = ((textPath == 4) ? 1 : 4);
        }
        final double n5 = (9 - 2 * n4) * n3 / 8.0;
        this.CharOri = charOri;
        this.CharSlant = charSlant;
        super.Height = n3;
        super.Width = 2.0;
        super.noclip = (textPath != 0 || charOri != 0.0);
        super.x = n + Math.sin(charOri) * n5;
        super.y = n2 + Math.cos(charOri) * n5;
        this.extW = extW;
        this.extH = extH;
        this.Content = content;
        this.CharacterSpacing = characterSpacing;
    }
    
    final void draw(final Graphics graphics, final double n, final double n2, final boolean b) {
        float n3 = (float)(this.CharacterHeight * n2);
        if (super.FillColor != null && n3 > 1.0f) {
            FontMetrics fontMetrics = null;
            Shape clipBounds = null;
            AffineTransform transform = null;
            int xe;
            int ye;
            if (this.Previous != null) {
                xe = this.Previous.xe;
                ye = this.Previous.ye;
                this.xr = this.Previous.xr;
                this.yr = this.Previous.yr;
                this.extW = this.Previous.extW;
                this.extH = this.Previous.extH;
            }
            else {
                xe = (int)(super.x * n + 0.6);
                ye = (int)(super.y * n2 + 0.6);
                this.xr = xe;
                this.yr = ye;
            }
            graphics.setColor(super.FillColor);
            if (this.state == 0) {
                if (this.applet.Fonts != null) {
                    this.state = 2;
                    final String fontname = this.applet.fontname(this.fontname);
                    if ((this.style & 0x1) > 0 && (this.style & 0x2) > 0 && (this.hf = (HersheyFont)this.applet.Fonts.get(String.valueOf(fontname) + "_BoldItalic")) != null) {
                        this.style = 0;
                    }
                    if ((this.style & 0x1) > 0 && (this.hf = (HersheyFont)this.applet.Fonts.get(String.valueOf(fontname) + "_Bold")) != null) {
                        --this.style;
                    }
                    if ((this.style & 0x2) > 0 && (this.hf = (HersheyFont)this.applet.Fonts.get(String.valueOf(fontname) + "_Italic")) != null) {
                        this.style -= 2;
                    }
                    if (this.hf == null) {
                        this.hf = (HersheyFont)this.applet.Fonts.get(fontname);
                    }
                    if (this.hf == null && this.CharOri != 0.0) {
                        this.hf = this.applet.Fonts.get("FUTURAL");
                    }
                }
                if (this.hf == null) {
                    this.CharOri = 0.0;
                    this.state = 1;
                }
                else {
                    this.as = Math.sin(this.CharOri);
                    this.ac = Math.cos(this.CharOri);
                    this.aa = this.CharOri * 180.0 / 3.141592653589793;
                }
            }
            if (this.state == 1) {
                if (this.CharOri != 0.0) {
                    final Graphics2D graphics2D = (Graphics2D)graphics;
                    transform = graphics2D.getTransform();
                    graphics2D.translate(this.xr, this.yr);
                    graphics2D.rotate(-this.CharOri);
                    graphics2D.translate(-this.xr, -this.yr);
                }
                final Font font;
                graphics.setFont(font = new Font(this.fontname, this.style & 0xFFFFFF7F, (int)n3));
                fontMetrics = graphics.getFontMetrics(font);
                if (this.extW > 0.0) {
                    n3 *= (float)(this.extH * n2 / (fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()));
                    final Font font2;
                    graphics.setFont(font2 = new Font(this.fontname, this.style & 0xFFFFFF7F, (int)n3));
                    fontMetrics = graphics.getFontMetrics(font2);
                }
            }
            else {
                this.hf.setHeight(n3 / 26.0f);
                this.hf.setWidth((float)(this.CharacterExpansion * n3 / 26.0));
                this.hf.setItalics((this.style & 0x2) > 0);
                if (Math.abs(this.CharSlant) > 0.05) {
                    this.hf.setItalics(true);
                    this.hf.setItalicsSlant((float)this.CharSlant);
                }
                else {
                    this.hf.setItalicsSlant(0.75f);
                }
                this.hf.setLineWidth(((this.style & 0x1) > 0) ? 2 : 1);
                this.hf.setRotation(this.aa);
            }
            if (this.extW > 0.0) {
                clipBounds = graphics.getClipBounds();
                final int n4 = (int)(this.extW * n + 0.5);
                int n5 = xe;
                switch (this.TextAlignHor) {
                    case 2: {
                        n5 -= n4 >> 1;
                        break;
                    }
                    case 3: {
                        n5 -= n4;
                        break;
                    }
                }
                graphics.clipRect(n5, (int)(ye - ((this.state == 1) ? fontMetrics.getAscent() : n3)), n4, (int)(this.extH * n2));
            }
            final int n6 = (this.state == 1) ? fontMetrics.stringWidth(this.Content) : this.hf.getTextLength(this.Content);
            final int length = this.Content.length();
            final double n7 = n6 * this.CharacterSpacing / length;
            int n8 = n6 + (int)((length - 1) * n7);
            switch (this.TextPath) {
                case 1: {
                    n8 = -n8;
                    break;
                }
                case 2:
                case 3: {
                    n8 /= length;
                    break;
                }
            }
            int n9 = 0;
            switch (this.TextAlignHor) {
                case 2: {
                    n9 = n8 >> 1;
                    break;
                }
                case 3: {
                    n9 = n8;
                    break;
                }
                default: {
                    n9 = 0;
                    break;
                }
            }
            final int xe2 = xe - (int)(this.ac * n9);
            int ye2 = ye + (int)(this.as * n9);
            if (this.CharacterSpacing != 0.0 || this.TextPath > 0) {
                this.xe = xe2;
                this.ye = ye2;
                for (int i = 0; i < length; ++i) {
                    final String substring = this.Content.substring(i, i + 1);
                    final double n10 = (this.TextPath < 2) ? (n7 + ((this.state == 1) ? fontMetrics.stringWidth(substring) : this.hf.getTextLength(substring))) : (-(n7 + n3));
                    if (this.TextPath == 1 || this.TextPath == 2) {
                        this.xe -= (int)(n10 * this.ac);
                        this.ye += (int)(n10 * this.as);
                    }
                    if (this.state == 1) {
                        graphics.drawString(substring, this.xe, this.ye);
                    }
                    else {
                        this.hf.drawString(substring, this.xe, this.ye, graphics);
                    }
                    if (this.TextPath == 0 || this.TextPath == 3) {
                        this.xe += (int)(n10 * this.ac);
                        this.ye -= (int)(n10 * this.as);
                    }
                }
            }
            else {
                if (this.state == 1) {
                    graphics.drawString(this.Content, xe2, ye2);
                }
                else {
                    this.hf.drawString(this.Content, xe2, ye2, graphics);
                }
                this.xe = xe2 + (int)(this.ac * n8);
                this.ye = ye2 + (int)(this.as * n8);
            }
            if (this.selected) {
                graphics.setColor(Color.red);
                ye2 += 2;
                final int n11;
                CgmPolygon.drawLine(graphics, xe2 + (int)(this.as * (n11 = (int)(n3 * 0.05 + 0.5))), ye2 + (int)(this.ac * n11), xe2 + (int)(this.ac * n8 + this.as * n11), ye2 + (int)(this.ac * n11 - this.as * n8), 4);
            }
            else if (this.underlined) {
                final int n12;
                graphics.drawLine(xe2 + (int)(this.as * (n12 = (int)(n3 * 0.05 + 0.5))), ye2 + (int)(this.ac * n12), xe2 + (int)(this.ac * n8 + this.as * n12), ye2 + (int)(this.ac * n12 - this.as * n8));
            }
            if (transform != null) {
                ((Graphics2D)graphics).setTransform(transform);
            }
            if (this.extW > 0.0) {
                graphics.setClip(clipBounds);
            }
        }
    }
    
    final int find(final String s, final int n) {
        if (n == -2) {
            if (this.selected) {
                this.selected = false;
                return -1;
            }
            return n;
        }
        else {
            if (n == -1 && this.Content.toUpperCase().indexOf(s.toUpperCase()) >= 0) {
                this.selected = true;
                return 0;
            }
            this.selected = false;
            return n;
        }
    }
    
    final void setFont(final Font font) {
        this.fontname = font.getName();
        this.style = font.getStyle();
        this.underlined = ((this.style & 0x80) > 0);
        this.style &= 0xFFFFFF7F;
    }
}
