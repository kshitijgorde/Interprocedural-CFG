// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import pclient.shd.PrefDef;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import com.pchat.sc.WindowUtil;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Vector;
import com.pchat.sc.MsgOptions;
import pclient.shd.Config;
import java.awt.Color;
import java.awt.Font;

public class Formatter
{
    private Font userFont;
    private Color userColor;
    private Font textFont;
    public Color textColor;
    private Font systemFont;
    private Color systemColor;
    private Font linkFont;
    private Color linkColor;
    private Font questionFont;
    private Color questionColor;
    private Font answerFont;
    private Color answerColor;
    private static final int lineMargin = 3;
    private static final int widthLeftMargin = 2;
    private static final int widthRightMargin = 1;
    private static final int wordMargin = 2;
    private int currentX;
    private int currentY;
    private int lineHeight;
    private boolean whetherParseURL;
    private MsgParser messageParser;
    private Config paraConf;
    
    public Formatter(final Config paraConf) {
        this.whetherParseURL = true;
        this.paraConf = paraConf;
        this.messageParser = new MsgParser(paraConf.getSmiley().getEmoticon());
        this.setDefaults(paraConf.getPref());
        this.resetPositions();
    }
    
    public void ignoreURL() {
        this.whetherParseURL = false;
    }
    
    public void reset() {
        this.resetPositions();
    }
    
    public void changeFontSize(final int n) {
        this.userFont = this.changeSize(n, this.userFont);
        this.textFont = this.changeSize(n, this.textFont);
        this.systemFont = this.changeSize(n, this.systemFont);
        this.linkFont = this.changeSize(n, this.linkFont);
        this.questionFont = this.changeSize(n, this.questionFont);
        this.answerFont = this.changeSize(n, this.answerFont);
    }
    
    public int getTotalLen() {
        return this.currentY + 3;
    }
    
    public Vector addChatLine(final String s, final String s2, final int n, final String s3, final MsgOptions msgOptions) {
        final Vector vector = new Vector();
        this.generateBasicItems(vector, s3 + s, this.userColor, this.userFont, n);
        final PrefixItem prefix = this.parsePrefix(msgOptions);
        if (prefix == null) {
            this.generateBasicItems(vector, s2, this.textColor, this.textFont, n);
        }
        else {
            this.processPrefix(prefix, this.textColor, this.textFont);
            this.generateBasicItems(vector, s2, prefix.realColor, prefix.font, n);
        }
        this.startNewLine();
        return vector;
    }
    
    public Vector appendText(final String s, final int n, final MsgOptions msgOptions) {
        final Vector vector = new Vector();
        this.generateBasicItems(vector, s, this.systemColor, this.systemFont, n);
        this.startNewLine();
        return vector;
    }
    
    public Vector addQuestion(final String s, final String s2, final int n, final String s3) {
        final Vector vector = new Vector();
        this.generateBasicItems(vector, s3 + s, this.userColor, this.userFont, n);
        this.generateBasicItems(vector, s2, this.questionColor, this.questionFont, n);
        this.startNewLine();
        return vector;
    }
    
    public Vector addAnswer(final String s, final String s2, final int n, final String s3) {
        final Vector vector = new Vector();
        this.generateBasicItems(vector, s3 + s, this.userColor, this.userFont, n);
        this.generateBasicItems(vector, s2, this.answerColor, this.answerFont, n);
        this.startNewLine();
        return vector;
    }
    
    public Vector getPlainText(final Vector vector) {
        final Vector vector2 = new Vector();
        for (int i = 0; i < vector.size(); ++i) {
            this.plainLine(vector2, vector.elementAt(i));
        }
        return vector2;
    }
    
    private void plainLine(final Vector vector, final ChatMessage chatMessage) {
        String fromUser = "";
        if (chatMessage.fromUser != null) {
            fromUser = chatMessage.fromUser;
        }
        vector.addElement(fromUser + chatMessage.message);
    }
    
    private void resetPositions() {
        this.lineHeight = 0;
        this.currentX = 2;
        this.currentY = 3;
    }
    
    private void generateBasicItems(final Vector vector, final String s, final Color color, final Font font, final int n) {
        final Vector message = this.messageParser.parseMessage(s);
        for (int i = 0; i < message.size(); ++i) {
            final MessageElement messageElement = message.elementAt(i);
            if (messageElement.isImage) {
                this.formatImage(vector, messageElement, color, font, n);
            }
            else {
                this.formatText(vector, messageElement, color, font, n);
            }
        }
    }
    
    private boolean isBadImage(final Image image) {
        return image == null || (image.getWidth(null) <= 0 || image.getHeight(null) <= 0);
    }
    
    private void formatImage(final Vector vector, final MessageElement messageElement, final Color color, final Font font, final int n) {
        final Image retrieveImage = this.retrieveImage(messageElement.imageID);
        if (this.isBadImage(retrieveImage)) {
            this.formatText(vector, messageElement, color, font, n);
            return;
        }
        final int width = retrieveImage.getWidth(null);
        retrieveImage.getHeight(null);
        if (width > n - 2 - 1) {
            this.addBasicImage(vector, retrieveImage, messageElement.imageID);
            return;
        }
        if (width > n - 1 - this.currentX) {
            this.startNewLine();
            this.addBasicImage(vector, retrieveImage, messageElement.imageID);
            return;
        }
        this.addBasicImage(vector, retrieveImage, messageElement.imageID);
    }
    
    private GBasicImage addBasicImage(final Vector vector, final Image image, final String s) {
        final GBasicImage gBasicImage = new GBasicImage(image, this.currentX, this.currentY);
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int n = 1;
        this.currentX += n;
        gBasicImage.setSize(width, height);
        gBasicImage.setPosition(this.currentX, this.currentY);
        this.currentX = this.currentX + width + n + n;
        this.updateLineHeight(height);
        vector.addElement(gBasicImage);
        return gBasicImage;
    }
    
    private void formatText(final Vector vector, final MessageElement messageElement, final Color color, final Font font, final int n) {
        final String[] splitBySpace = this.splitBySpace(messageElement.text);
        StringBuffer sb = new StringBuffer(messageElement.text.length());
        for (int i = 0; i < splitBySpace.length; ++i) {
            final String s = splitBySpace[i];
            if (WindowUtil.isLink(s)) {
                this.addString(vector, sb.toString(), color, font, n);
                sb = new StringBuffer();
                this.addLinkText(vector, s, this.linkColor, font, n);
            }
            else if (this.calculateLength(font, sb.toString() + s) > n - 1 - this.currentX) {
                this.addString(vector, sb.toString(), color, font, n);
                this.startNewLine();
                sb = new StringBuffer();
                sb.append(s);
                if (this.calculateLength(font, s) > n - 1 - 2) {
                    this.addBreakString(vector, s, color, font, n);
                    sb = new StringBuffer();
                }
            }
            else {
                sb.append(s);
            }
        }
        final String string = sb.toString();
        if (string.length() != 0) {
            this.addBasicText(vector, string, color, font);
        }
    }
    
    private void addLinkText(final Vector vector, final String s, final Color color, final Font font, final int n) {
        final int calculateLength = this.calculateLength(font, s);
        final String generateURL = this.generateURL(s);
        if (calculateLength > n - 1 - this.currentX) {
            this.addBreakLink(vector, s, color, font, n, generateURL);
            return;
        }
        this.addBasicLink(vector, s, color, font, generateURL);
    }
    
    private void addBreakLink(final Vector vector, final String s, final Color color, final Font font, final int n, final String s2) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (this.calculateLength(font, sb.toString() + char1) <= n - 1 - this.currentX) {
                sb.append(char1);
            }
            else {
                final String string = sb.toString();
                if (string.length() == 0) {
                    sb.append(char1);
                }
                else {
                    this.addBasicLink(vector, string, color, font, s2);
                    this.startNewLine();
                    sb = new StringBuffer();
                    sb.append(char1);
                }
            }
        }
        final String string2 = sb.toString();
        if (string2.length() != 0) {
            this.addBasicLink(vector, string2, color, font, s2);
        }
    }
    
    private void addBasicLink(final Vector vector, final String s, final Color color, final Font font, final String s2) {
        if (s == null) {
            return;
        }
        if (s.length() == 0) {
            return;
        }
        final GLinkText gLinkText = new GLinkText(this.paraConf, 0, s, color, font, s2);
        final int calculateLength = this.calculateLength(font, s);
        final int calculateFontHeight = this.calculateFontHeight(font);
        gLinkText.setSize(calculateLength, calculateFontHeight);
        gLinkText.setPosition(this.currentX, this.currentY);
        this.currentX += calculateLength;
        this.updateLineHeight(calculateFontHeight);
        vector.addElement(gLinkText);
    }
    
    private void addString(final Vector vector, final String s, final Color color, final Font font, final int n) {
        this.addBasicText(vector, s, color, font);
    }
    
    private void addBreakString(final Vector vector, final String s, final Color color, final Font font, final int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (this.calculateLength(font, sb.toString() + char1) <= n - 1 - this.currentX) {
                sb.append(char1);
            }
            else {
                final String string = sb.toString();
                if (string.length() == 0) {
                    sb.append(char1);
                }
                else {
                    this.addBasicText(vector, string, color, font);
                    this.startNewLine();
                    sb = new StringBuffer();
                    sb.append(char1);
                }
            }
        }
        final String string2 = sb.toString();
        if (string2.length() != 0) {
            this.addBasicText(vector, string2, color, font);
        }
    }
    
    private void addBasicText(final Vector vector, final String s, final Color color, final Font font) {
        if (s == null) {
            return;
        }
        if (s.length() == 0) {
            return;
        }
        final GBasicText gBasicText = new GBasicText(0, s, color, font);
        final int calculateLength = this.calculateLength(font, s);
        final int calculateFontHeight = this.calculateFontHeight(font);
        gBasicText.setSize(calculateLength, calculateFontHeight);
        gBasicText.setPosition(this.currentX, this.currentY);
        this.currentX += calculateLength;
        this.updateLineHeight(calculateFontHeight);
        vector.addElement(gBasicText);
    }
    
    private int calculateFontHeight(final Font font) {
        final int n = 0;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return n + fontMetrics.getAscent() + fontMetrics.getDescent();
    }
    
    private void updateLineHeight(final int lineHeight) {
        if (lineHeight > this.lineHeight) {
            this.lineHeight = lineHeight;
        }
    }
    
    private void startNewLine() {
        this.currentX = 2;
        this.currentY = this.currentY + this.lineHeight + 3;
        this.lineHeight = 0;
    }
    
    private int calculateLength(final Font font, final String s) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font).stringWidth(s);
    }
    
    private String[] splitBySpace(final String s) {
        final Vector vector = new Vector<String>(8);
        int n = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                while (i < s.length() && s.charAt(i) == ' ') {
                    ++i;
                }
                vector.addElement(s.substring(n, i));
                n = i;
            }
            if (++i == s.length()) {
                vector.addElement(s.substring(n, i));
                break;
            }
        }
        final int size = vector.size();
        final String[] array = new String[size];
        for (int j = 0; j < size; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
    
    private void processPrefix(final PrefixItem prefixItem, final Color realColor, final Font font) {
        if (prefixItem.realColor == null) {
            prefixItem.realColor = realColor;
        }
        int n;
        if (prefixItem.bold && prefixItem.italic) {
            n = 3;
        }
        else if (prefixItem.bold) {
            n = 1;
        }
        else if (prefixItem.italic) {
            n = 2;
        }
        else {
            n = 0;
        }
        final int size = font.getSize();
        String s = font.getName();
        if (prefixItem.fontname != null) {
            s = prefixItem.fontname;
        }
        Font font2 = new Font(s, n, size);
        if (font2 == null) {
            font2 = font;
        }
        prefixItem.font = font2;
    }
    
    private PrefixItem parsePrefix(final MsgOptions msgOptions) {
        if (msgOptions == null) {
            return null;
        }
        final PrefixItem prefixItem = new PrefixItem();
        prefixItem.fontname = msgOptions.fontname;
        prefixItem.realColor = msgOptions.color;
        prefixItem.bold = msgOptions.fontBold;
        prefixItem.italic = msgOptions.fontItalic;
        return prefixItem;
    }
    
    private String generateURL(final String s) {
        return s;
    }
    
    private Image retrieveImage(final String s) {
        return this.paraConf.getSmiley().getImage(s);
    }
    
    private Font changeSize(final int n, final Font font) {
        font.getName();
        return new Font(font.getName(), font.getStyle(), n);
    }
    
    private void setDefaults(final PrefDef prefDef) {
        this.userFont = new Font("Dialog", 1, 12);
        this.textFont = new Font("Dialog", 0, 12);
        this.systemFont = this.textFont;
        this.linkFont = this.textFont;
        this.questionFont = this.textFont;
        this.answerFont = this.textFont;
        this.userColor = prefDef.userName;
        this.textColor = prefDef.textColor;
        this.systemColor = prefDef.systemColor;
        this.linkColor = prefDef.linkColor;
        this.questionColor = prefDef.questionColor;
        this.answerColor = prefDef.answerColor;
    }
}
