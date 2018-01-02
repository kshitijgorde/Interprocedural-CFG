// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import irc.EventDispatcher;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.Vector;
import irc.StyleContext;
import java.awt.Dimension;
import irc.IRCConfiguration;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;

public class FormattedStringDrawer implements ImageObserver
{
    private Font _font;
    private Font _fontPlain;
    private Font _fontBold;
    private Color[] _cols;
    private CharactersDrawer _drawer;
    private IRCConfiguration _config;
    private Dimension _tmp;
    private LineItem[] _lines;
    private int _vdirection;
    private int _hdirection;
    private FormattedStringDrawerListener _listener;
    public static final int BOTTOM = 0;
    public static final int TOP = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    
    public FormattedStringDrawer(final IRCConfiguration config, final StyleContext styleContext, final FormattedStringDrawerListener listener) {
        this._listener = listener;
        this._tmp = new Dimension();
        this._lines = new LineItem[8];
        for (int i = 0; i < this._lines.length; ++i) {
            this._lines[i] = new LineItem();
        }
        this._config = config;
        this.setFont(config.getStyleFont(styleContext));
        this._drawer = new CharactersDrawer(this._config);
        this.setStyleContext(styleContext);
        this._vdirection = 0;
        this._hdirection = 0;
        if (config.getB("style:righttoleft")) {
            this.setHorizontalDirection(1);
        }
    }
    
    public FormattedStringDrawer(final IRCConfiguration ircConfiguration, final StyleContext styleContext) {
        this(ircConfiguration, styleContext, null);
    }
    
    public void setVerticalDirection(final int vdirection) {
        this._vdirection = vdirection;
    }
    
    public int getVerticalDirection() {
        return this._vdirection;
    }
    
    public void setHorizontalDirection(final int hdirection) {
        this._hdirection = hdirection;
    }
    
    public int getHorizontalDirection() {
        return this._hdirection;
    }
    
    public void setStyleContext(final StyleContext styleContext) {
        this._cols = this._config.getStyleColors(styleContext);
    }
    
    public DecodedLine decodeLine(String string) {
        final DecodedLineInternal decodedLineInternal = new DecodedLineInternal();
        decodedLineInternal.original = string;
        string += '\u000f';
        final String decodeLine = this._drawer.decodeLine(string);
        decodedLineInternal.decoded = decodeLine;
        decodedLineInternal.decoded_stripped = this.getStripped(decodeLine);
        final Vector doWords = this.doWords(string, decodeLine);
        decodedLineInternal.words = new WordItem[doWords.size()];
        for (int i = 0; i < decodedLineInternal.words.length; ++i) {
            decodedLineInternal.words[i] = doWords.elementAt(i);
        }
        return decodedLineInternal;
    }
    
    private Vector doWords(String substring, String substring2) {
        final Vector<WordItem> vector = new Vector<WordItem>();
        CharacterInfo lastInfo = new CharacterInfo();
        lastInfo.frontColor = this._cols[1];
        lastInfo.backColor = this._cols[0];
        lastInfo.isTransparent = true;
        while (substring2.length() > 0) {
            final int index = substring.indexOf(32);
            final int index2 = substring2.indexOf(32);
            WordItem wordItem;
            if (index2 == -1) {
                wordItem = this.decodeWord(lastInfo, substring2 + " ", this._cols);
                wordItem.originalword = substring + " ";
                wordItem.originalstrippedword = this.getStripped(substring + " ");
                substring2 = "";
            }
            else {
                final String substring3 = substring.substring(0, index);
                wordItem = this.decodeWord(lastInfo, substring2.substring(0, index2) + " ", this._cols);
                wordItem.originalword = substring3 + " ";
                wordItem.originalstrippedword = this.getStripped(substring3 + " ");
                substring = substring.substring(index + 1);
                substring2 = substring2.substring(index2 + 1);
            }
            vector.insertElementAt(wordItem, vector.size());
            lastInfo = wordItem.lastInfo;
        }
        return vector;
    }
    
    public int getHeight(final DecodedLine decodedLine, final FontMetrics fontMetrics) {
        return this._drawer.getHeight(decodedLine.decoded_stripped, fontMetrics, this);
    }
    
    public int getWidth(final DecodedLine decodedLine, final FontMetrics fontMetrics) {
        return this._drawer.getWidth(decodedLine.decoded_stripped, fontMetrics, this);
    }
    
    private Font deriveFont(final Font font, final int n) {
        return new Font(font.getName(), n, font.getSize());
    }
    
    public void setColors(final Color[] cols) {
        this._cols = cols;
    }
    
    public Color getColor(final int n) {
        return this._cols[n];
    }
    
    public void setFont(final Font font) {
        this._font = font;
        this._fontPlain = this.deriveFont(this._font, 0);
        this._fontBold = this.deriveFont(this._font, 1);
    }
    
    public Font getFont() {
        return this._font;
    }
    
    private WordItem decodeWord(final CharacterInfo characterInfo, final String s, final Color[] array) {
        final Vector vector = new Vector<CharacterGroupItem>();
        final CharacterInfo characterInfo2 = new CharacterInfo(characterInfo);
        CharacterGroupItem characterGroupItem = new CharacterGroupItem(new CharacterInfo(characterInfo2));
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 < ' ') {
                final char c = char1;
                if (c == '\u000f') {
                    characterInfo2.isBold = false;
                    characterInfo2.isUnderline = false;
                    characterInfo2.isReverse = false;
                    characterInfo2.frontColor = array[1];
                    characterInfo2.backColor = array[0];
                    characterInfo2.isTransparent = true;
                }
                else if (c == '\u0002') {
                    characterInfo2.isBold = !characterInfo2.isBold;
                }
                else if (c == '\u001f') {
                    characterInfo2.isUnderline = !characterInfo2.isUnderline;
                }
                else if (c == '\u0016') {
                    characterInfo2.isReverse = !characterInfo2.isReverse;
                    if (characterInfo2.isReverse) {
                        characterInfo2.frontColor = array[0];
                        characterInfo2.backColor = array[1];
                        characterInfo2.isTransparent = false;
                    }
                    else {
                        characterInfo2.frontColor = array[1];
                        characterInfo2.backColor = array[0];
                        characterInfo2.isTransparent = true;
                    }
                }
                else if (c == '\u0003') {
                    int n = 1;
                    String string = "";
                    String string2 = "";
                    ++i;
                    while (i < length) {
                        final char char2 = s.charAt(i);
                        if (char2 >= '0' && char2 <= '9') {
                            if (n != 0) {
                                if (string.length() == 2) {
                                    --i;
                                    break;
                                }
                                string += char2;
                            }
                            else {
                                if (string2.length() == 2) {
                                    --i;
                                    break;
                                }
                                string2 += char2;
                            }
                            ++i;
                        }
                        else {
                            if (char2 != ',') {
                                --i;
                                break;
                            }
                            if (n == 0) {
                                --i;
                                break;
                            }
                            n = 0;
                            ++i;
                        }
                    }
                    if (string.length() == 0) {
                        string2 = "";
                    }
                    if (string.length() > 0) {
                        characterInfo2.frontColor = array[Integer.parseInt(string) % this._cols.length];
                    }
                    if (string2.length() > 0) {
                        final int n2 = Integer.parseInt(string2) % this._cols.length;
                        characterInfo2.backColor = array[n2];
                        characterInfo2.isTransparent = (n2 == 0);
                    }
                    if (string.length() == 0 && string2.length() == 0) {
                        characterInfo2.frontColor = array[1];
                        characterInfo2.backColor = array[0];
                        characterInfo2.isTransparent = true;
                    }
                }
                if (!characterInfo2.equals(characterGroupItem.info)) {
                    vector.insertElementAt(characterGroupItem, vector.size());
                    characterGroupItem = new CharacterGroupItem(new CharacterInfo(characterInfo2));
                }
            }
            else {
                final StringBuffer sb = new StringBuffer();
                final CharacterGroupItem characterGroupItem2 = characterGroupItem;
                characterGroupItem2.s = sb.append(characterGroupItem2.s).append(char1).toString();
            }
        }
        vector.insertElementAt(characterGroupItem, vector.size());
        final CharacterGroupItem[] array2 = new CharacterGroupItem[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array2[j] = vector.elementAt(j);
        }
        return new WordItem(array2, characterInfo2);
    }
    
    private FontMetrics getFontMetrics(final Graphics graphics, final CharacterInfo characterInfo) {
        final Font font = graphics.getFont();
        if (characterInfo.isBold) {
            graphics.setFont(this._fontBold);
        }
        else {
            graphics.setFont(this._fontPlain);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setFont(font);
        return fontMetrics;
    }
    
    private int drawPart(final Graphics graphics, final CharacterInfo characterInfo, final String s, final int n, int n2, final FontMetrics fontMetrics, final int n3, final int n4, final ImageObserver imageObserver, final Vector vector) {
        final int descent = fontMetrics.getDescent();
        if (characterInfo.isBold) {
            graphics.setFont(this._fontBold);
        }
        final FontMetrics fontMetrics2 = graphics.getFontMetrics();
        final int width = this._drawer.getWidth(s, fontMetrics2, this);
        if (n <= n4 && n + width > n3) {
            final int height = this._drawer.getHeight(s, fontMetrics2, this);
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics.clipRect(n3, n2 - height, n4 - n3 + 1, height);
            graphics.setColor(characterInfo.backColor);
            if (!characterInfo.isTransparent) {
                graphics.fillRect(n, n2 - height, width, height);
            }
            n2 -= descent;
            graphics.setColor(characterInfo.frontColor);
            this._drawer.draw(s, graphics, fontMetrics2, n, n2, imageObserver, vector);
            if (characterInfo.isUnderline) {
                graphics.drawLine(n, n2 + 1, n + width - 1, n2 + 1);
            }
            if (clipBounds != null) {
                graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            else {
                graphics.setClip(null);
            }
        }
        if (characterInfo.isBold) {
            graphics.setFont(this._fontPlain);
        }
        return width;
    }
    
    private void drawWord(final Graphics graphics, final WordItem wordItem, int n, final int n2, final boolean b, final FontMetrics fontMetrics, final int n3, final int n4, final ImageObserver imageObserver, final Vector vector) {
        for (int i = 0; i < wordItem.items.length; ++i) {
            final CharacterGroupItem characterGroupItem = wordItem.items[i];
            n += this.drawPart(graphics, characterGroupItem.info, characterGroupItem.s, n, n2, fontMetrics, n3, n4, imageObserver, vector);
        }
    }
    
    public String getStripped(String substring) {
        final CharacterInfo characterInfo = new CharacterInfo();
        characterInfo.frontColor = this._cols[1];
        characterInfo.backColor = this._cols[0];
        characterInfo.isTransparent = true;
        String s = "";
        while (substring.length() > 0) {
            final int index = substring.indexOf(32);
            WordItem wordItem;
            if (index == -1) {
                wordItem = this.decodeWord(characterInfo, substring, this._cols);
                substring = "";
            }
            else {
                wordItem = this.decodeWord(characterInfo, substring.substring(0, index) + " ", this._cols);
                substring = substring.substring(index + 1);
            }
            if (s.length() > 0) {
                s = s + " " + wordItem.originalword;
            }
            else {
                s += wordItem.originalword;
            }
        }
        return s;
    }
    
    private boolean isAlphaNum(final char c) {
        return c != '(' && c != ')' && c != '<' && c != '>' && c != '\"' && c != '\"' && c != '{' && c != '}' && c != '.' && c != ',' && c != ':';
    }
    
    private String trimAlphaNum(String s) {
        int n;
        for (n = 0; n < s.length() && !this.isAlphaNum(s.charAt(n)); ++n) {}
        if (n == s.length()) {
            return "";
        }
        int n2;
        for (s = s.substring(n), n2 = s.length() - 1; n2 >= 0 && !this.isAlphaNum(s.charAt(n2)); --n2) {}
        if (n2 == -1) {
            return "";
        }
        s = s.substring(0, n2 + 1);
        return s;
    }
    
    private void getWordItemWidthHeight(final Graphics graphics, final WordItem wordItem, final Dimension dimension) {
        int width = 0;
        int height = 0;
        for (int i = 0; i < wordItem.items.length; ++i) {
            this._drawer.getWidthHeight(wordItem.items[i].s, this.getFontMetrics(graphics, wordItem.items[i].info), dimension, this);
            width += dimension.width;
            final int height2 = dimension.height;
            if (height2 > height) {
                height = height2;
            }
        }
        dimension.width = width;
        dimension.height = height;
    }
    
    private void expandLines() {
        final LineItem[] lines = new LineItem[this._lines.length * 2];
        System.arraycopy(this._lines, 0, lines, 0, this._lines.length);
        for (int i = this._lines.length; i < lines.length; ++i) {
            lines[i] = new LineItem();
        }
        this._lines = lines;
    }
    
    public int getHeight(final DecodedLine decodedLine, final Graphics graphics, final int n, final int n2, final boolean b) {
        final WordItem[] words = ((DecodedLineInternal)decodedLine).words;
        graphics.setFont(this._fontPlain);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n3 = 0;
        int width = 0;
        int n4 = 0;
        int height = 0;
        for (int i = 0; i < words.length; ++i) {
            this.getWordItemWidthHeight(graphics, words[i], this._tmp);
            final int width2 = this._tmp.width;
            if (width + width2 > n2 && n3 > 0 && b) {
                width = this._drawer.getWidth("  ", fontMetrics, this);
                n3 = 0;
                n4 += height;
                height = 0;
            }
            if (this._tmp.height > height) {
                height = this._tmp.height;
            }
            ++n3;
            width += width2;
        }
        if (n3 > 0) {
            n4 += height;
        }
        return n4;
    }
    
    public void draw(final DecodedLine decodedLine, final Graphics graphics, final int n, final int n2, int y, final int n3, final int n4, final boolean b, final boolean b2, final DrawResult drawResult) {
        final WordItem[] words = ((DecodedLineInternal)decodedLine).words;
        if (drawResult.updateHandles == null) {
            drawResult.updateHandles = new Vector();
        }
        else if (drawResult.updateHandles.size() > 0) {
            drawResult.updateHandles.removeAllElements();
        }
        graphics.setFont(this._fontPlain);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        final int n8 = n2 - n + 1;
        if (b2) {
            int width = 0;
            for (int i = 0; i < words.length; ++i) {
                this.getWordItemWidthHeight(graphics, words[i], this._tmp);
                final int width2 = this._tmp.width;
                if (width + width2 > n8 && n7 > 0) {
                    width = this._drawer.getWidth("  ", fontMetrics, this);
                    final LineItem lineItem = this._lines[n5++];
                    if (n5 == this._lines.length) {
                        this.expandLines();
                    }
                    lineItem.first = n6;
                    lineItem.count = n7;
                    n6 = i;
                    n7 = 0;
                }
                ++n7;
                width += width2;
            }
            if (n7 != 0) {
                final LineItem lineItem2 = this._lines[n5++];
                if (n5 == this._lines.length) {
                    this.expandLines();
                }
                lineItem2.first = n6;
                lineItem2.count = n7;
            }
        }
        else {
            final LineItem lineItem3 = this._lines[n5++];
            lineItem3.first = 0;
            lineItem3.count = words.length;
        }
        int length = 0;
        if (b) {
            length = words.length;
        }
        if (drawResult.items == null || drawResult.items.length != length) {
            drawResult.items = new DrawResultItem[length];
        }
        final DrawResultItem[] items = drawResult.items;
        int n9 = n2;
        int n10 = n;
        int height = 0;
        int n11 = 0;
        final int width3 = this._drawer.getWidth("  ", fontMetrics, this);
        int n12 = 1;
        if (this._hdirection == 1) {
            n12 = -1;
        }
        if (this._vdirection == 0) {
            for (int j = n5 - 1; j >= 0; --j) {
                int n13 = n;
                if (n12 == -1) {
                    n13 = n2;
                }
                int n14 = 0;
                if (j != 0) {
                    n13 += n12 * width3;
                }
                final LineItem lineItem4 = this._lines[j];
                for (int k = lineItem4.first; k < lineItem4.first + lineItem4.count; ++k) {
                    this.getWordItemWidthHeight(graphics, words[k], this._tmp);
                    final int width4 = this._tmp.width;
                    final int height2 = this._tmp.height;
                    if (n12 == -1) {
                        n13 -= width4;
                    }
                    final int n15 = width4;
                    if (n14 < height2) {
                        n14 = height2;
                    }
                    if (n13 + width4 > n3 && n13 <= n4) {
                        this.drawWord(graphics, words[k], n13, y, k == lineItem4.first + lineItem4.count - 1, fontMetrics, n3, n4, this, drawResult.updateHandles);
                    }
                    if (b) {
                        final String originalword = words[k].originalword;
                        final String originalstrippedword = words[k].originalstrippedword;
                        final String trimAlphaNum = this.trimAlphaNum(originalstrippedword.trim());
                        if (items[k] == null) {
                            items[k] = new DrawResultItem();
                        }
                        final DrawResultItem drawResultItem = items[k];
                        drawResultItem.parent = drawResult;
                        drawResultItem.item = trimAlphaNum;
                        drawResultItem.originalword = originalword;
                        drawResultItem.originalstrippedword = originalstrippedword;
                        drawResultItem.rectangle = new StyledRectangle(n13, n11 - height2, n15, height2);
                    }
                    if (n12 == 1) {
                        n13 += width4;
                    }
                    if (n13 > n10) {
                        n10 = n13;
                    }
                    if (n13 < n9) {
                        n9 = n13;
                    }
                }
                y -= n14;
                n11 -= n14;
                height += n14;
            }
            if (b) {
                for (int l = 0; l < items.length; ++l) {
                    final StyledRectangle rectangle = items[l].rectangle;
                    rectangle.y += height;
                }
            }
        }
        else if (this._vdirection == 1) {
            for (int n16 = 0; n16 < n5; ++n16) {
                int n17 = n;
                if (n12 == -1) {
                    n17 = n2;
                }
                int n18 = 0;
                if (n16 != 0) {
                    n17 += n12 * width3;
                }
                final LineItem lineItem5 = this._lines[n16];
                for (int first = lineItem5.first; first < lineItem5.first + lineItem5.count; ++first) {
                    this.getWordItemWidthHeight(graphics, words[first], this._tmp);
                    final int width5 = this._tmp.width;
                    final int height3 = this._tmp.height;
                    if (n12 == -1) {
                        n17 -= width5;
                    }
                    final int n19 = width5;
                    if (n18 < height3) {
                        n18 = height3;
                    }
                    if (n17 + width5 > n3 && n17 <= n4) {
                        this.drawWord(graphics, words[first], n17, y + height3, first == lineItem5.first + lineItem5.count - 1, fontMetrics, n3, n4, this, drawResult.updateHandles);
                    }
                    if (b) {
                        final String decodedword = words[first].decodedword;
                        final String originalword2 = words[first].originalword;
                        final String originalstrippedword2 = words[first].originalstrippedword;
                        final String trimAlphaNum2 = this.trimAlphaNum(decodedword.trim());
                        if (items[first] == null) {
                            items[first] = new DrawResultItem();
                        }
                        final DrawResultItem drawResultItem2 = items[first];
                        drawResultItem2.parent = drawResult;
                        drawResultItem2.item = trimAlphaNum2;
                        drawResultItem2.originalword = originalword2;
                        drawResultItem2.originalstrippedword = originalstrippedword2;
                        drawResultItem2.rectangle = new StyledRectangle(n17, n11, n19, height3);
                    }
                    if (n12 == 1) {
                        n17 += width5;
                    }
                    if (n17 > n10) {
                        n10 = n17;
                    }
                    if (n17 < n9) {
                        n9 = n17;
                    }
                }
                y += n18;
                n11 += n18;
                height += n18;
            }
            y -= height;
        }
        int x = n;
        int n20 = n10;
        if (n12 == -1) {
            x = n9;
            n20 = n2;
        }
        if (b) {
            for (int n21 = 0; n21 < drawResult.items.length; ++n21) {
                final StyledRectangle rectangle2 = drawResult.items[n21].rectangle;
                rectangle2.x -= x;
            }
        }
        if (drawResult.rectangle == null) {
            drawResult.rectangle = new StyledRectangle(x, y, n20 - x + 1, height);
        }
        else {
            drawResult.rectangle.x = x;
            drawResult.rectangle.y = y;
            drawResult.rectangle.width = n20 - x + 1;
            drawResult.rectangle.height = height;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x40) != 0x0) {
            return false;
        }
        if ((n & 0x80) != 0x0) {
            return false;
        }
        if ((n & 0x4) != 0x0) {
            return true;
        }
        if (this._listener != null) {
            int n6 = 0;
            if ((n & 0x1) != 0x0) {
                n6 |= 0x2;
            }
            if ((n & 0x2) != 0x0) {
                n6 |= 0x2;
            }
            if ((n & 0x20) != 0x0) {
                n6 |= 0x1;
            }
            if ((n & 0x10) != 0x0) {
                n6 |= 0x4;
            }
            if ((n & 0x8) != 0x0) {
                n6 |= 0x1;
            }
            try {
                return (boolean)EventDispatcher.dispatchEventAsyncAndWaitEx(this._listener, "displayUpdated", new Object[] { image, new Integer(n6) });
            }
            catch (Throwable t) {
                t.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
