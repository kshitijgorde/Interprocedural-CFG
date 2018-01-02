// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import BsscXML.IBsscXMLElementReader;
import java.awt.Color;
import java.awt.Image;

public class FtsViewSkin extends ViewSkin
{
    private Image m_imageFormBg;
    private Color m_colorFormBg;
    private int m_nFormEditWidth;
    private BsscFont m_fontFormTitle;
    private Image m_imageFormBtnN;
    private Image m_imageFormBtnH;
    private BsscFont m_fontFtsError;
    
    public void loadFromDom(final IBsscXMLElementReader bsscXMLElementReader) {
        super.loadFromDom(bsscXMLElementReader);
        int n = 0;
        while (true) {
            final IBsscXMLElementReader child = bsscXMLElementReader.getChild(n++);
            if (child == null) {
                break;
            }
            if (child.getName().equals("form")) {
                int n2 = 0;
                while (true) {
                    final IBsscXMLElementReader child2 = child.getChild(n2++);
                    if (child2 == null) {
                        break;
                    }
                    if (!child2.getName().equals("background") && !child2.getName().equals("font") && !child2.getName().equals("button") && child2.getName().equals("editwidth")) {
                        continue;
                    }
                }
            }
            else {
                if (!child.getName().equals("fonts")) {
                    continue;
                }
                int n3 = 0;
                while (true) {
                    final IBsscXMLElementReader child3 = child.getChild(n3++);
                    if (child3 == null) {
                        break;
                    }
                    if (!child3.getName().equals("error")) {
                        continue;
                    }
                    final BsscFont font = ViewSkin.getFont(child3);
                    if (font == null) {
                        continue;
                    }
                    this.m_fontFtsError = font;
                }
            }
        }
    }
    
    public BsscFont getErrorFont() {
        return this.m_fontFtsError;
    }
    
    public FtsViewSkin() {
        this.m_imageFormBg = null;
        this.m_colorFormBg = null;
        this.m_nFormEditWidth = 28;
        this.m_fontFormTitle = null;
        this.m_imageFormBtnN = null;
        this.m_imageFormBtnH = null;
        this.m_fontFtsError = new BsscFont(BsscFontFixPatch.GetFontName(), BsscFontFixPatch.GetFontSize() + "pt", "normal", "bold", Color.black, "none");
    }
}
