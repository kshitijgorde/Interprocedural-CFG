// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import BsscXML.IBsscXMLElementReader;
import java.awt.Color;
import java.awt.Image;

public class ViewSkin
{
    private Image m_imageBg;
    private Color m_colorBg;
    private BsscFont m_fontNormal;
    private BsscFont m_fontHover;
    private Color m_colorActiveBg;
    private static Color GREEN;
    private int m_nIndent;
    private int m_nMargin;
    public static Color m_PaneColorBg;
    public static Image m_PaneImageBg;
    public static BsscFont m_PaneFont;
    
    public void loadFromDom(final IBsscXMLElementReader bsscXMLElementReader) {
        int n = 0;
        while (true) {
            final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
            if (child == null) {
                break;
            }
            if (child.getName().equals("background")) {
                final Color color = getColor(child.getAttribute("color"));
                if (color != null) {
                    this.m_colorBg = color;
                }
                final Image image = getImage(child.getAttribute("img"));
                if (image == null) {
                    continue;
                }
                this.m_imageBg = image;
            }
            else if (child.getName().equals("fonts")) {
                int n2 = 0;
                while (true) {
                    final IBsscXMLElementReader child2 = child.getChild(n2++);
                    if (child2 == null) {
                        break;
                    }
                    if (child2.getName().equals("normal")) {
                        final BsscFont font = getFont(child2);
                        if (font == null) {
                            continue;
                        }
                        this.m_fontNormal = font;
                    }
                    else {
                        if (!child2.getName().equals("hover")) {
                            continue;
                        }
                        final BsscFont font2 = getFont(child2);
                        if (font2 == null) {
                            continue;
                        }
                        this.m_fontHover = font2;
                    }
                }
            }
            else if (child.getName().equals("margin")) {
                final int size = this.getSize(child.getAttribute("attr"));
                if (size < 0) {
                    continue;
                }
                this.m_nMargin = size;
            }
            else if (child.getName().equals("indent")) {
                final int size2 = this.getSize(child.getAttribute("attr"));
                if (size2 < 0) {
                    continue;
                }
                this.m_nIndent = size2;
            }
            else {
                if (!child.getName().equals("activebackgroundcolor")) {
                    continue;
                }
                final Color color2 = getColor(child.getAttribute("attr"));
                if (color2 == null) {
                    continue;
                }
                this.m_colorActiveBg = color2;
            }
        }
    }
    
    public ViewSkin() {
        this.m_imageBg = null;
        this.m_colorBg = new Color(255, 255, 255);
        this.m_fontNormal = new BsscFont(BsscFontFixPatch.GetFontName(), BsscFontFixPatch.GetFontSize() + "pt", "normal", "normal", Color.black, "none");
        this.m_fontHover = new BsscFont(BsscFontFixPatch.GetFontName(), BsscFontFixPatch.GetFontSize() + "pt", "normal", "normal", ViewSkin.GREEN, "underline");
        this.m_colorActiveBg = new Color(204, 204, 204);
        this.m_nIndent = 16;
        this.m_nMargin = 0;
    }
    
    public ViewSkin(final Image imageBg, final Color colorBg, final BsscFont fontNormal, final BsscFont fontHover, final Color colorActiveBg, final int nIndent, final int nMargin) {
        this.m_imageBg = imageBg;
        this.m_colorBg = colorBg;
        this.m_fontNormal = fontNormal;
        this.m_fontHover = fontHover;
        this.m_colorActiveBg = colorActiveBg;
        this.m_nIndent = nIndent;
        this.m_nMargin = nMargin;
    }
    
    public BsscFont getHoverFont() {
        return this.m_fontHover;
    }
    
    public static Color getColor(final String s) {
        if (s != null) {
            if (s.length() == 7 && s.indexOf("#") == 0) {
                final String substring = s.substring(1, 3);
                final String substring2 = s.substring(3, 5);
                final String substring3 = s.substring(5, 7);
                try {
                    return new Color(Integer.parseInt(substring, 16), Integer.parseInt(substring2, 16), Integer.parseInt(substring3, 16));
                }
                catch (NumberFormatException ex) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }
    
    public Image getBgImage() {
        return this.m_imageBg;
    }
    
    public BsscFont getNormalFont() {
        return this.m_fontNormal;
    }
    
    public static BsscFont getFont(final IBsscXMLElementReader bsscXMLElementReader) {
        if (bsscXMLElementReader != null) {
            final String attribute = bsscXMLElementReader.getAttribute("name");
            final String attribute2 = bsscXMLElementReader.getAttribute("size");
            final String attribute3 = bsscXMLElementReader.getAttribute("style");
            final String attribute4 = bsscXMLElementReader.getAttribute("weight");
            final String attribute5 = bsscXMLElementReader.getAttribute("decoration");
            final String attribute6 = bsscXMLElementReader.getAttribute("color");
            if (attribute != null && attribute.length() > 0 && attribute2 != null && attribute2.length() > 0 && attribute3 != null && attribute3.length() > 0 && attribute4 != null && attribute4.length() > 0 && attribute6 != null && attribute6.length() > 0) {
                return new BsscFont(attribute, attribute2, attribute3, attribute4, getColor(attribute6), attribute5);
            }
        }
        return null;
    }
    
    public Color getActiveColor() {
        return this.m_colorActiveBg;
    }
    
    static {
        ViewSkin.GREEN = new Color(0, 127, 0);
        ViewSkin.m_PaneColorBg = null;
        ViewSkin.m_PaneImageBg = null;
        ViewSkin.m_PaneFont = null;
    }
    
    public Color getBgColor() {
        return this.m_colorBg;
    }
    
    protected int getSize(String substring) {
        if (substring != null) {
            final int lastIndex = substring.lastIndexOf("pt");
            if (lastIndex != -1) {
                substring = substring.substring(0, lastIndex);
            }
            try {
                return Integer.parseInt(substring);
            }
            catch (NumberFormatException ex) {
                return -1;
            }
        }
        return -1;
    }
    
    public static Image getImage(final String s) {
        if (s != null && s.length() > 0) {
            return ImageCache.getInstance().GetImage(s);
        }
        return null;
    }
    
    public int getMargin() {
        return this.m_nMargin;
    }
    
    public int getIndent() {
        return this.m_nIndent;
    }
}
