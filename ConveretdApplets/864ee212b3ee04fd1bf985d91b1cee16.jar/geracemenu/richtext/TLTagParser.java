// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import geracemenu.util.Utilities;
import java.awt.Font;
import geracemenu.Visualizable;
import java.net.MalformedURLException;
import java.net.URL;
import geracemenu.parseMenuApplet;
import geracemenu.GuiUtil;
import java.awt.Color;
import geracemenu.VImage;

public class TLTagParser
{
    private static boolean debug;
    public static final int TAG_FONT = 0;
    public static final int TAG_COLOR = 1;
    public static final int TAG_SIZE = 2;
    public static final int TAG_BOLDSTYLE = 3;
    public static final int TAG_ITALICSTYLE = 4;
    public static final int TAG_UNDERLINEDSTYLE = 5;
    public static final int TAG_HREF = 6;
    public static final int TAG_ALIGN = 7;
    public static final int TAG_LISTITEM = 8;
    public static final int TAG_IMAGE = 9;
    public static final int TEXT_TAG = 0;
    public static final int ATTACHMENT_TAG = 1;
    private static final Object[] defaultTextStyleProperties;
    private static Object[] textStyleProperties;
    private static final int idFontName = 0;
    private static final int idFontStyle = 1;
    private static final int idFontSize = 2;
    private static final int idForegroundColor = 3;
    private static final int idBackgroundColor = 5;
    private static final int idStyleUnderlined = 4;
    private static final int idAlignment = 6;
    private static final int idClickable = 7;
    protected TLTagTokenizer tokenizer;
    protected boolean tagClosing;
    private VImage image;
    private boolean animated;
    private int parsedTagType;
    
    public static void flushTextStyleProperties() {
        TLTagParser.textStyleProperties[0] = new String((String)TLTagParser.defaultTextStyleProperties[0]);
        TLTagParser.textStyleProperties[1] = new Integer((int)TLTagParser.defaultTextStyleProperties[1]);
        TLTagParser.textStyleProperties[2] = new Integer((int)TLTagParser.defaultTextStyleProperties[2]);
        TLTagParser.textStyleProperties[3] = new Color(((Color)TLTagParser.defaultTextStyleProperties[3]).getRGB());
        TLTagParser.textStyleProperties[4] = new Boolean((boolean)TLTagParser.defaultTextStyleProperties[4]);
        TLTagParser.textStyleProperties[5] = new Color(((Color)TLTagParser.defaultTextStyleProperties[5]).getRGB());
        TLTagParser.textStyleProperties[6] = new Integer((int)TLTagParser.defaultTextStyleProperties[6]);
        TLTagParser.textStyleProperties[7] = null;
    }
    
    public void parseTag() {
        String s = "";
        try {
            s = this.expect("General", 1);
        }
        catch (TagSyntaxException ex) {}
        this.parsedTagType = 0;
        if (this.tokenizer.ttype() == 4) {
            s = this.expect("General\\Closing tag", 1);
            this.tagClosing = true;
        }
        else if (this.tokenizer.ttype() != 1) {
            throw new TagSyntaxException("General", "no tag name found");
        }
        if ("font".equalsIgnoreCase(s) || (s.length() == 1 && 'f' == s.toLowerCase().charAt(0))) {
            if (this.tagClosing) {
                this.parseClosingTag(0);
            }
            else {
                this.parseFontTag();
            }
        }
        else if ("color".equalsIgnoreCase(s) || (s.length() == 1 && 'c' == s.toLowerCase().charAt(0))) {
            if (this.tagClosing) {
                this.parseClosingTag(1);
            }
            else {
                this.parseColorTag();
            }
        }
        else if ("size".equalsIgnoreCase(s) || (s.length() == 1 && 's' == s.toLowerCase().charAt(0))) {
            if (this.tagClosing) {
                this.parseClosingTag(2);
            }
            else {
                this.parseSizeTag();
            }
        }
        else if ("b".equalsIgnoreCase(s)) {
            if (this.tagClosing) {
                this.parseClosingTag(3);
            }
            else {
                this.parseBoldStyleTag();
            }
        }
        else if ("i".equalsIgnoreCase(s)) {
            if (this.tagClosing) {
                this.parseClosingTag(4);
            }
            else {
                this.parseItalicStyleTag();
            }
        }
        else if ("u".equalsIgnoreCase(s)) {
            if (this.tagClosing) {
                this.parseClosingTag(5);
            }
            else {
                this.parseUnderlinedStyleTag();
            }
        }
        else if ("href".equalsIgnoreCase(s) || (s.length() == 1 && 'h' == s.toLowerCase().charAt(0))) {
            if (this.tagClosing) {
                this.parseClosingTag(6);
            }
            else {
                this.parseHRefTag();
            }
        }
        else if ("align".equalsIgnoreCase(s) || (s.length() == 1 && 'a' == s.toLowerCase().charAt(0))) {
            if (this.tagClosing) {
                this.parseClosingTag(7);
            }
            else {
                this.parseAlignmentTag();
            }
        }
        else {
            if (!"img".equalsIgnoreCase(s)) {
                throw new TagSyntaxException(s, "no tag found");
            }
            this.parseImgTag();
            this.parsedTagType = 1;
        }
    }
    
    public final int ttype() {
        return this.parsedTagType;
    }
    
    protected void parseFontTag() {
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Font", "expected '='");
        }
        this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 2) {
            throw new TagSyntaxException("Font", "expected '='");
        }
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Font", "missing font name");
        }
        final String nextToken = this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 1 && this.tokenizer.ttype() != 0) {
            throw new TagSyntaxException("Font", "expected font face name");
        }
        TLTagParser.textStyleProperties[0] = nextToken;
        if (TLTagParser.debug) {
            System.out.println("parseFontTag(): [fontName:" + nextToken + ']');
        }
    }
    
    protected void parseColorTag() {
        String nextToken = null;
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Color", "expected '='");
        }
        this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 2) {
            throw new TagSyntaxException("Color", "expected '='");
        }
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Color", "missing foreground color value");
        }
        final String nextToken2 = this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 1 && this.tokenizer.ttype() != 0) {
            throw new TagSyntaxException("Color", "omitted foreground color value");
        }
        if (this.tokenizer.hasMoreTokens()) {
            this.tokenizer.nextToken();
            if (this.tokenizer.ttype() != 3) {
                throw new TagSyntaxException("Color", "expected ','");
            }
            if (!this.tokenizer.hasMoreTokens()) {
                throw new TagSyntaxException("Color", "missing background color value");
            }
            nextToken = this.tokenizer.nextToken();
            if (this.tokenizer.ttype() != 1 && this.tokenizer.ttype() != 0) {
                throw new TagSyntaxException("Color", "omitted background color value");
            }
        }
        TLTagParser.textStyleProperties[3] = GuiUtil.findColor(nextToken2, (Color)TLTagParser.textStyleProperties[3]);
        if (nextToken != null) {
            TLTagParser.textStyleProperties[5] = GuiUtil.findColor(nextToken, (Color)TLTagParser.textStyleProperties[5]);
        }
        if (TLTagParser.debug) {
            System.out.print("parseColorTag(): [fg:" + nextToken2 + ']');
            if (nextToken != null) {
                System.out.print("[bk:" + nextToken + "]\n");
            }
            else {
                System.out.println();
            }
        }
    }
    
    protected void parseSizeTag() {
        boolean b = false;
        int n = 1;
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Size", "expected '='");
        }
        this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 2) {
            throw new TagSyntaxException("Size", "expected '='");
        }
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Size", "missing size value");
        }
        int int1;
        try {
            String s = this.tokenizer.nextToken().trim();
            if ("+-".indexOf(s.charAt(0)) != -1) {
                b = true;
                if (s.charAt(0) == '-') {
                    n = -1;
                }
                s = s.substring(1).trim();
            }
            int1 = Integer.parseInt(s, 10);
        }
        catch (NumberFormatException ex) {
            throw new TagSyntaxException("Size", "size numeric value format fault");
        }
        if (b) {
            TLTagParser.textStyleProperties[2] = new Integer((int)TLTagParser.textStyleProperties[2] + n * int1);
        }
        else {
            TLTagParser.textStyleProperties[2] = new Integer(int1);
        }
        if (TLTagParser.debug) {
            System.out.println("parseSizeTag(): [size: " + int1 + ']');
        }
    }
    
    protected void parseItalicStyleTag() {
        TLTagParser.textStyleProperties[1] = new Integer((int)TLTagParser.textStyleProperties[1] | 0x2);
        if (TLTagParser.debug) {
            System.out.println("parseItalicStyleTag(): parsed fine!");
        }
    }
    
    protected void parseBoldStyleTag() {
        TLTagParser.textStyleProperties[1] = new Integer((int)TLTagParser.textStyleProperties[1] | 0x1);
        if (TLTagParser.debug) {
            System.out.println("parseBoldStyleTag(): parsed fine!");
        }
    }
    
    protected void parseUnderlinedStyleTag() {
        TLTagParser.textStyleProperties[4] = new Boolean(true);
        if (TLTagParser.debug) {
            System.out.println("parseUnderlinedStyleTag(): parsed fine!");
        }
    }
    
    protected void parseHRefTag() {
        String s2;
        String s = s2 = null;
        this.expect("Href", 2);
        final String expect = this.expect("Href", 1);
        for (int i = 0; i < 2; ++i) {
            final String[] attribute = this.parseAttribute("Href");
            if (attribute != null) {
                if ("title".equals(attribute[0].toLowerCase())) {
                    s2 = attribute[1];
                }
                else {
                    if (!"target".equals(attribute[0].toLowerCase())) {
                        throw new TagSyntaxException("Href", "unexpected " + attribute[0] + '=' + attribute[1]);
                    }
                    s = attribute[1];
                }
            }
        }
        int n = -1;
        final int index = expect.indexOf(58);
        final boolean b = index != -1;
        final String s3 = b ? expect.substring(0, index).trim() : "";
        final String s4 = expect;
        if (b) {
            n = 0;
        }
        TLTagParser.textStyleProperties[7] = new ClickableTextAction(n, s4, s2, s);
        if (TLTagParser.debug) {
            System.out.print("parseHRefTag(): [href:" + s4);
            if (s2 != null) {
                System.out.println(" title:" + s2);
            }
            if (s != null) {
                System.out.println(" target:" + s);
            }
            System.out.println(']');
        }
    }
    
    protected void parseAlignmentTag() {
        int n = 2;
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Align", "expected '='");
        }
        this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 2) {
            throw new TagSyntaxException("Align", "expected '='");
        }
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException("Align", "missing alignment value");
        }
        final String nextToken = this.tokenizer.nextToken();
        if (this.tokenizer.ttype() != 1 && this.tokenizer.ttype() != 0) {
            throw new TagSyntaxException("Align", "omitted alignment value");
        }
        if ("right".equals(nextToken.toLowerCase())) {
            n = 3;
        }
        else if ("center".equals(nextToken.toLowerCase())) {
            n = 1;
        }
        TLTagParser.textStyleProperties[6] = new Integer(n);
        if (TLTagParser.debug) {
            System.out.println("parseAlignTag(): [alignment: " + nextToken + ']');
        }
    }
    
    protected void parseImgTag() {
        final String[] attribute = this.parseAttribute("IMG");
        this.animated = "animated".equalsIgnoreCase(this.parseModifier("IMG"));
        if (attribute == null) {
            throw new TagSyntaxException("Image", "no SRC attribute specified");
        }
        if (!"src".equals(attribute[0].toLowerCase())) {
            throw new TagSyntaxException("Image", "expected SRC attribute: " + attribute[0]);
        }
        try {
            this.image = new VImage(new URL(parseMenuApplet.URLspec + attribute[1]));
        }
        catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        if (TLTagParser.debug) {
            System.out.println("parseImgTag(): [src: " + attribute[1] + ']');
        }
    }
    
    protected String[] parseAttribute(final String s) {
        String expect;
        try {
            expect = this.expect(s, 1);
        }
        catch (TagSyntaxException ex) {
            return null;
        }
        this.expect(s, 2);
        return new String[] { expect, this.expect(s, 1) };
    }
    
    protected String parseModifier(final String s) {
        String expect;
        try {
            expect = this.expect(s, 1);
        }
        catch (TagSyntaxException ex) {
            return null;
        }
        return expect;
    }
    
    protected void parseClosingTag(final int n) {
        switch (n) {
            case 0: {
                TLTagParser.textStyleProperties[0] = new String((String)TLTagParser.defaultTextStyleProperties[0]);
                break;
            }
            case 1: {
                TLTagParser.textStyleProperties[3] = new Color(((Color)TLTagParser.defaultTextStyleProperties[3]).getRGB());
                TLTagParser.textStyleProperties[5] = new Color(((Color)TLTagParser.defaultTextStyleProperties[5]).getRGB());
                break;
            }
            case 2: {
                TLTagParser.textStyleProperties[2] = new Integer((int)TLTagParser.defaultTextStyleProperties[2]);
                break;
            }
            case 3: {
                TLTagParser.textStyleProperties[1] = new Integer((int)TLTagParser.textStyleProperties[1] & 0xFFFFFFFE);
                break;
            }
            case 4: {
                TLTagParser.textStyleProperties[1] = new Integer((int)TLTagParser.textStyleProperties[1] & 0xFFFFFFFD);
                break;
            }
            case 5: {
                TLTagParser.textStyleProperties[4] = new Boolean((boolean)TLTagParser.defaultTextStyleProperties[4]);
                break;
            }
            case 6: {
                TLTagParser.textStyleProperties[7] = null;
                break;
            }
            case 7: {
                TLTagParser.textStyleProperties[6] = new Integer((int)TLTagParser.defaultTextStyleProperties[6]);
                break;
            }
        }
    }
    
    public Text generateRichTextToken() {
        final Font font = TextStyle.DEFAULT_STYLE.getFont();
        return new Text(new TextAttachment(this.image, this.animated), new TextStyle(font.getName(), font.getStyle(), font.getSize(), Color.black, false, Color.white, (int)TLTagParser.textStyleProperties[6]));
    }
    
    public TextStyle generateTextStyle() {
        if (TLTagParser.textStyleProperties[7] == null) {
            return new TextStyle((String)TLTagParser.textStyleProperties[0], (int)TLTagParser.textStyleProperties[1], (int)TLTagParser.textStyleProperties[2], (Color)TLTagParser.textStyleProperties[3], (boolean)TLTagParser.textStyleProperties[4], (Color)TLTagParser.textStyleProperties[5], (int)TLTagParser.textStyleProperties[6]);
        }
        return new TextStyle((String)TLTagParser.textStyleProperties[0], (int)TLTagParser.textStyleProperties[1], (int)TLTagParser.textStyleProperties[2], (Color)TLTagParser.textStyleProperties[3], (boolean)TLTagParser.textStyleProperties[4], (Color)TLTagParser.textStyleProperties[5], (int)TLTagParser.textStyleProperties[6], (ClickableTextAction)TLTagParser.textStyleProperties[7]);
    }
    
    private String expect(final String s, final int n) throws TagSyntaxException {
        if (!this.tokenizer.hasMoreTokens()) {
            throw new TagSyntaxException(s, "expected " + this.tokenizer.typeToString(n));
        }
        final String nextToken = this.tokenizer.nextToken();
        final int ttype;
        if ((ttype = this.tokenizer.ttype()) != n) {
            throw new TagSyntaxException(s, "expected '" + this.tokenizer.typeToString(n) + " but found " + this.tokenizer.typeToString(ttype));
        }
        return nextToken;
    }
    
    public TLTagParser(final TLTagTokenizer tokenizer) {
        this.tagClosing = false;
        this.image = GuiUtil.NULL_ICON;
        this.animated = false;
        this.parsedTagType = 0;
        this.tokenizer = tokenizer;
    }
    
    static {
        TLTagParser.debug = false;
        defaultTextStyleProperties = new Object[] { Utilities.getSystemFont("Helvetica"), new Integer(0), new Integer(12), Color.black, new Boolean(false), Color.lightGray, new Integer(2) };
        TLTagParser.textStyleProperties = new Object[] { Utilities.getSystemFont("Helvetica"), new Integer(0), new Integer(12), Color.black, new Boolean(false), Color.lightGray, new Integer(2), null };
    }
}
