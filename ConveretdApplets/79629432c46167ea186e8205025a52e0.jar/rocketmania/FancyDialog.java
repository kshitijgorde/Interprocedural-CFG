// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.util.Enumeration;
import java.util.Hashtable;
import sexy.gui.ButtonListener;
import sexy.gui.WidgetManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import sexy.gui.SexyColor;
import java.io.BufferedReader;
import sexy.util.XMLParser;
import sexy.util.XMLElement;
import sexy.gui.SexyFont;
import java.util.Vector;
import sexy.gui.SexyGraphics;
import sexy.gui.OutlineDialogWidget;

public class FancyDialog extends OutlineDialogWidget
{
    static final int skTouchHack = 1;
    static final int ALIGN_LEFT = -1;
    static final int ALIGN_CENTER = 0;
    static final int ALIGN_RIGHT = 1;
    public FancyLayout mLayout;
    public String mFancyText;
    
    public void PopFont(final SexyGraphics sexyGraphics, final Vector vector) {
        sexyGraphics.SetFont(vector.lastElement());
        vector.removeElementAt(vector.size() - 1);
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        int n = 0;
        if (super.mColors.length > 4) {
            sexyGraphics.SetColor(super.mColors[4]);
            sexyGraphics.FillRect(0, 0, super.mWidth - super.mDropShadowSize, super.mHeight - super.mDropShadowSize);
        }
        if (!super.mHideOutline) {
            sexyGraphics.SetColor(super.mColors[0]);
            sexyGraphics.DrawRect(0, 0, super.mWidth - 1 - super.mDropShadowSize, super.mHeight - 1 - super.mDropShadowSize);
            sexyGraphics.DrawRect(1, 1, super.mWidth - 3 - super.mDropShadowSize, super.mHeight - 3 - super.mDropShadowSize);
        }
        sexyGraphics.SetFont(super.mHeaderFont);
        sexyGraphics.SetColor(super.mColors[1]);
        if (super.mHeader != null) {
            sexyGraphics.DrawString(super.mHeader, (super.mWidth - super.mDropShadowSize - sexyGraphics.GetFont().StringWidth(super.mHeader)) / 2, 10 + sexyGraphics.GetFont().GetAscent());
            n += 10 + sexyGraphics.GetFont().GetAscent();
        }
        sexyGraphics.SetFont(super.mLinesFont);
        sexyGraphics.SetColor(super.mColors[2]);
        int n8;
        if (this.mFancyText != null) {
            final XMLElement xmlElement = new XMLElement();
            final Vector<Object> vector = new Vector<Object>();
            final Vector<Object> vector2 = new Vector<Object>();
            final Vector<Object> vector3 = new Vector<Object>();
            SexyColor sexyColor = null;
            final XMLParser xmlParser = new XMLParser(null);
            xmlParser.SetStringSource(this.mFancyText);
            final int n2 = this.mLayout.mPadLeft + (super.mHideOutline ? 0 : 2);
            final int n3 = super.mWidth - super.mDropShadowSize - this.mLayout.mPadRight - (super.mHideOutline ? 0 : 2);
            int n4 = n2;
            int n5 = sexyGraphics.GetFont().GetHeight() + this.mLayout.mPadLine;
            try {
                while (xmlParser.NextElement(xmlElement)) {
                    switch (xmlElement.mType) {
                        case 1: {
                            if (xmlElement.mValue.equals("BR")) {
                                n4 = n2;
                                continue;
                            }
                            if (xmlElement.mValue.equals("Shadow")) {
                                sexyColor = new SexyColor(0, 0, 0, 255);
                                continue;
                            }
                            if (xmlElement.mValue.equals("Font")) {
                                vector.addElement(sexyGraphics.GetFont().clone());
                                String s = "SansSerif";
                                int n6 = 0;
                                int int1 = 10;
                                if (xmlElement.mAttributes.containsKey("Name")) {
                                    s = xmlElement.mAttributes.get("Name");
                                }
                                if (xmlElement.mAttributes.containsKey("Style")) {
                                    final String s2 = xmlElement.mAttributes.get("Style");
                                    if (s2.equalsIgnoreCase("plain")) {
                                        n6 = 0;
                                    }
                                    else if (s2.equalsIgnoreCase("bold")) {
                                        n6 = 1;
                                    }
                                    else if (s2.equalsIgnoreCase("italic")) {
                                        n6 = 2;
                                    }
                                }
                                if (xmlElement.mAttributes.containsKey("Size")) {
                                    int1 = Integer.parseInt(xmlElement.mAttributes.get("Size"), 10);
                                }
                                final SexyFont findFont = this.FindFont(s, n6, int1);
                                if (findFont != null) {
                                    sexyGraphics.SetFont(findFont);
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (xmlElement.mValue.equals("DialogText")) {
                                    vector.addElement(sexyGraphics.GetFont().clone());
                                    vector2.addElement(sexyGraphics.GetColor().clone());
                                    vector3.addElement(this.mLayout.clone());
                                    continue;
                                }
                                if (xmlElement.mValue.equals("Color")) {
                                    vector2.addElement(sexyGraphics.GetColor().clone());
                                    int mRed;
                                    int mGreen;
                                    int mBlue;
                                    if (xmlElement.mAttributes.containsKey("Value")) {
                                        final int int2 = Integer.parseInt(xmlElement.mAttributes.get("Value"), 16);
                                        mRed = (int2 & 0xFF0000) >> 16;
                                        mGreen = (int2 & 0xFF00) >> 8;
                                        mBlue = (int2 & 0xFF);
                                    }
                                    else {
                                        mRed = sexyGraphics.GetColor().mRed;
                                        mGreen = sexyGraphics.GetColor().mGreen;
                                        mBlue = sexyGraphics.GetColor().mBlue;
                                    }
                                    int n7;
                                    if (xmlElement.mAttributes.containsKey("Alpha")) {
                                        n7 = Integer.parseInt(xmlElement.mAttributes.get("Alpha"));
                                    }
                                    else {
                                        n7 = sexyGraphics.GetColor().mAlpha;
                                    }
                                    sexyGraphics.SetColor(new SexyColor(mRed, mGreen, mBlue, n7));
                                    continue;
                                }
                                if (xmlElement.mValue.equals("Bold")) {
                                    vector.addElement(sexyGraphics.GetFont().clone());
                                    final Font findFont2 = this.FindFont(sexyGraphics.GetFont());
                                    if (findFont2 == null) {
                                        continue;
                                    }
                                    final SexyFont findFont3 = this.FindFont(findFont2.getName(), 1, findFont2.getSize());
                                    if (findFont3 != null) {
                                        sexyGraphics.SetFont(findFont3);
                                        continue;
                                    }
                                    continue;
                                }
                                else {
                                    if (xmlElement.mValue.equals("Center")) {
                                        vector3.addElement(this.mLayout.clone());
                                        this.mLayout.Center();
                                        continue;
                                    }
                                    if (xmlElement.mValue.equals("Right")) {
                                        vector3.addElement(this.mLayout.clone());
                                        if (xmlElement.mAttributes.containsKey("Pad")) {
                                            this.mLayout.RightJustify(Integer.parseInt((String)xmlElement.mAttributes.get("Pad")));
                                            continue;
                                        }
                                        this.mLayout.Justify(1);
                                        continue;
                                    }
                                    else {
                                        if (!xmlElement.mValue.equals("Left")) {
                                            continue;
                                        }
                                        vector3.addElement(this.mLayout.clone());
                                        if (xmlElement.mAttributes.containsKey("Pad")) {
                                            this.mLayout.LeftJustify(Integer.parseInt((String)xmlElement.mAttributes.get("Pad")));
                                            continue;
                                        }
                                        this.mLayout.Justify(-1);
                                        continue;
                                    }
                                }
                            }
                            break;
                        }
                        case 3: {
                            switch (this.mLayout.mAlignment) {
                                default: {
                                    continue;
                                }
                                case 0: {
                                    n4 = n2 + (Math.max(n3, n2) - Math.min(n2, n3)) / 2;
                                    this.DrawString(sexyGraphics, xmlElement.mValue, n4 - sexyGraphics.GetFont().StringWidth(xmlElement.mValue) / 2, n5, sexyColor);
                                    continue;
                                }
                                case -1: {
                                    this.DrawString(sexyGraphics, xmlElement.mValue, n4, n5, sexyColor);
                                    n4 += sexyGraphics.GetFont().StringWidth(xmlElement.mValue);
                                    continue;
                                }
                                case 1: {
                                    this.DrawString(sexyGraphics, xmlElement.mValue, n3 - sexyGraphics.GetFont().StringWidth(xmlElement.mValue), n5, sexyColor);
                                    n5 += sexyGraphics.GetFont().GetHeight() + this.mLayout.mPadLine;
                                    n4 = n3;
                                    continue;
                                }
                            }
                            break;
                        }
                        case 2: {
                            if (xmlElement.mValue.equals("BR")) {
                                n5 += sexyGraphics.GetFont().GetHeight() + this.mLayout.mPadLine;
                                n4 = this.mLayout.mPadLeft + (super.mHideOutline ? 0 : 2);
                                continue;
                            }
                            if (xmlElement.mValue.equals("Shadow")) {
                                sexyColor = null;
                                continue;
                            }
                            if (xmlElement.mValue.equals("Font") || xmlElement.mValue.equals("Bold")) {
                                this.PopFont(sexyGraphics, vector);
                                continue;
                            }
                            if (xmlElement.mValue.equals("DialogText")) {
                                this.PopFont(sexyGraphics, vector);
                                this.PopColor(sexyGraphics, vector2);
                                this.PopLayout(sexyGraphics, vector3);
                                continue;
                            }
                            if (xmlElement.mValue.equals("Color")) {
                                this.PopColor(sexyGraphics, vector2);
                                continue;
                            }
                            if (xmlElement.mValue.equals("Left") || xmlElement.mValue.equals("Right")) {
                                this.PopLayout(sexyGraphics, vector3);
                                n4 = n2;
                                continue;
                            }
                            if (xmlElement.mValue.equals("Center")) {
                                this.PopLayout(sexyGraphics, vector3);
                                n5 += sexyGraphics.GetFont().GetHeight() + this.mLayout.mPadLine;
                                n4 = n2;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                }
            }
            catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
                super.mWidgetManager.mApplet.FatalError("clone", "clone not supported in FancyDialog");
            }
            n8 = n + n5;
        }
        else {
            n8 = n + this.WriteWordWrapped(sexyGraphics, new Rectangle(0, n + sexyGraphics.GetFont().GetHeight(), super.mWidth - super.mDropShadowSize, super.mHeight - 40), super.mLines, -1, 0);
        }
        if (super.mButtonMode != 2 && super.mFooter != null) {
            sexyGraphics.SetFont(super.mHeaderFont);
            sexyGraphics.SetColor(super.mColors[3]);
            sexyGraphics.DrawString(super.mFooter, (super.mWidth - super.mDropShadowSize - sexyGraphics.GetFont().StringWidth(super.mFooter)) / 2, n8 + 50 + sexyGraphics.GetFont().GetAscent());
        }
        if (super.mDropShadowSize > 0) {
            if (super.mColors.length > 5) {
                sexyGraphics.SetColor(super.mColors[5]);
            }
            else {
                sexyGraphics.SetColor(Color.black);
            }
            sexyGraphics.FillRect(super.mWidth - super.mDropShadowSize, super.mDropShadowSize, super.mDropShadowSize, super.mHeight - super.mDropShadowSize);
            sexyGraphics.FillRect(super.mDropShadowSize, super.mHeight - super.mDropShadowSize, super.mWidth - super.mDropShadowSize * 2, super.mDropShadowSize);
        }
    }
    
    public FancyDialog(final WidgetManager widgetManager, final ButtonListener buttonListener, final String s, final String s2, final String s3, final int n) {
        super(widgetManager, buttonListener, s, s2, s3, n);
        this.mLayout = new FancyLayout();
    }
    
    public void DrawString(final SexyGraphics sexyGraphics, final String s, final int n, final int n2, final SexyColor sexyColor) {
        if (sexyColor != null) {
            final SexyColor getColor = sexyGraphics.GetColor();
            sexyGraphics.SetColor(sexyColor);
            sexyGraphics.DrawString(s, n + 1, n2 + 1);
            sexyGraphics.SetColor(getColor);
        }
        sexyGraphics.DrawString(s, n, n2);
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    public void PopLayout(final SexyGraphics sexyGraphics, final Vector vector) {
        this.mLayout = vector.lastElement();
        vector.removeElementAt(vector.size() - 1);
    }
    
    public Font FindFont(final SexyFont sexyFont) {
        Font font = null;
        final Hashtable mSexyFontHash = super.mWidgetManager.mApplet.mSexyFontHash;
        Font font2;
        for (Enumeration keys = mSexyFontHash.keys(); keys.hasMoreElements() && font == null; font = font2) {
            font2 = keys.nextElement();
            if (mSexyFontHash.get(font2).equals(sexyFont)) {}
        }
        return font;
    }
    
    public SexyFont FindFont(final String s, final int n, final int n2) {
        SexyFont sexyFont = null;
        Font font = null;
        final Enumeration keys = super.mWidgetManager.mApplet.mSexyFontHash.keys();
        while (keys.hasMoreElements() && sexyFont == null) {
            final Font font2 = keys.nextElement();
            if (font2.getName().equals(s)) {
                if (font2.getStyle() == n) {
                    if (font2.getSize() == n2) {
                        sexyFont = super.mWidgetManager.mApplet.GetSexyFont(font2);
                    }
                    else if (font == null) {
                        font = font2;
                    }
                    else {
                        if (Math.abs(font2.getSize() - n2) >= Math.abs(font.getSize() - n2)) {
                            continue;
                        }
                        font = font2;
                    }
                }
                else if (font == null) {
                    font = font2;
                }
                else if (n == 0) {
                    if (font.getStyle() != 2 || font2.getStyle() != 1) {
                        continue;
                    }
                    font = font2;
                }
                else if (n == 2) {
                    if (font.getStyle() != 0 || font2.getStyle() != 1) {
                        continue;
                    }
                    font = font2;
                }
                else {
                    if (n != 1 || font.getStyle() != 0 || font2.getStyle() != 2) {
                        continue;
                    }
                    font = font2;
                }
            }
        }
        if (sexyFont == null) {
            if (font != null) {
                sexyFont = super.mWidgetManager.mApplet.GetSexyFont(font);
            }
            else {
                sexyFont = super.mWidgetManager.mApplet.CreateFont(s, n, n2);
            }
        }
        return sexyFont;
    }
    
    public void PopColor(final SexyGraphics sexyGraphics, final Vector vector) {
        sexyGraphics.SetColor(vector.lastElement());
        vector.removeElementAt(vector.size() - 1);
    }
    
    public void SetFancyText(final String mFancyText, final int n, final int n2) {
        this.mFancyText = mFancyText;
    }
    
    public class FancyLayout implements Cloneable
    {
        int mAlignment;
        int mPadLeft;
        int mPadRight;
        int mPadLine;
        
        public void Center() {
            this.Justify(0);
        }
        
        public FancyLayout() {
            FancyDialog.this.getClass();
            this.mAlignment = -1;
        }
        
        public void PadLine(final int mPadLine) {
            this.mPadLine = mPadLine;
        }
        
        public int Justify(final int mAlignment) {
            switch (mAlignment) {
                case -1:
                case 0:
                case 1: {
                    this.mAlignment = mAlignment;
                    break;
                }
            }
            return this.mAlignment;
        }
        
        public void LeftJustify(final int mPadLeft) {
            this.Justify(-1);
            this.mPadLeft = mPadLeft;
        }
        
        public void RightJustify(final int mPadRight) {
            this.Justify(1);
            this.mPadRight = mPadRight;
        }
        
        public synchronized Object clone() throws CloneNotSupportedException {
            final FancyLayout fancyLayout = new FancyLayout();
            fancyLayout.mAlignment = this.mAlignment;
            fancyLayout.mPadLeft = this.mPadLeft;
            fancyLayout.mPadRight = this.mPadRight;
            fancyLayout.mPadLine = this.mPadLine;
            return fancyLayout;
        }
    }
}
