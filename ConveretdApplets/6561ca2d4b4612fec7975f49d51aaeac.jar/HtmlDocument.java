import java.util.Hashtable;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class HtmlDocument
{
    private IntVector codes;
    private StringVector strings;
    private ImageVector images;
    private String title;
    private String location;
    private String start;
    private Color bgColor;
    private Color textColor;
    private Color linkColor;
    private URL base;
    private String endOfText;
    public static int TYPE_HTML;
    public static int TYPE_IMAGE;
    public static int TYPE_TEXT;
    public static int TYPE_SOUND;
    private static final int TEXT = 0;
    private static final int A = 1;
    private static final int ADDRESS = 2;
    private static final int B = 3;
    private static final int BASE = 4;
    private static final int BIG = 5;
    private static final int BLOCKQUOTE = 6;
    private static final int BODY = 7;
    private static final int BR = 8;
    private static final int CENTER = 9;
    private static final int CITE = 10;
    private static final int CODE = 11;
    private static final int DD = 12;
    private static final int DIR = 13;
    private static final int DL = 14;
    private static final int DT = 15;
    private static final int EM = 16;
    private static final int FONT = 17;
    private static final int H1 = 18;
    private static final int H2 = 19;
    private static final int H3 = 20;
    private static final int H4 = 21;
    private static final int H5 = 22;
    private static final int H6 = 23;
    private static final int HR = 24;
    private static final int I = 25;
    private static final int IMG = 26;
    private static final int KBD = 27;
    private static final int LI = 28;
    private static final int LISTING = 29;
    private static final int MENU = 30;
    private static final int OL = 31;
    private static final int P = 32;
    private static final int PRE = 33;
    private static final int SAMP = 34;
    private static final int SMALL = 35;
    private static final int STRONG = 36;
    private static final int TITLE = 37;
    private static final int TT = 38;
    private static final int UL = 39;
    private static final int VAR = 40;
    private static final int XMP = 41;
    private static final int MAXCODES = 42;
    protected static final String[] names;
    
    public HtmlDocument(final URL base) throws IOException {
        this.codes = new IntVector();
        this.strings = new StringVector();
        this.images = new ImageVector();
        this.fillLocationAndStart(this.base = base);
        this.parse(new HtmlTokenizer(base.openStream()));
        this.codes.trimToSize();
        this.strings.trimToSize();
        this.images.trimToSize();
    }
    
    public HtmlDocument(final URL base, final int n) throws IOException {
        this.codes = new IntVector();
        this.strings = new StringVector();
        this.images = new ImageVector();
        if (n == HtmlDocument.TYPE_HTML) {
            this.fillLocationAndStart(this.base = base);
            this.parse(new HtmlTokenizer(base.openStream()));
            this.codes.trimToSize();
            this.strings.trimToSize();
            this.images.trimToSize();
            return;
        }
        if (n == HtmlDocument.TYPE_IMAGE) {
            this.base = null;
            this.parse(new HtmlTokenizer(new StringBufferInputStream("<IMG SRC=\"" + base.toString() + "\">")));
            this.codes.trimToSize();
            this.strings.trimToSize();
            this.images.trimToSize();
            return;
        }
        if (n != HtmlDocument.TYPE_SOUND && n == HtmlDocument.TYPE_TEXT) {
            this.base = null;
            final StringBuffer sb = new StringBuffer();
            sb.append("<PRE>\n");
            final DataInputStream dataInputStream = new DataInputStream(base.openStream());
            final String s = new String();
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                sb.append(String.valueOf(line) + "\n");
            }
            sb.append("</PRE>\n");
            this.parse(new HtmlTokenizer(new StringBufferInputStream(sb.toString())));
            this.codes.trimToSize();
            this.strings.trimToSize();
            this.images.trimToSize();
        }
    }
    
    public HtmlDocument(final InputStream inputStream) throws IOException {
        this.codes = new IntVector();
        this.strings = new StringVector();
        this.images = new ImageVector();
        this.base = null;
        this.parse(new HtmlTokenizer(inputStream));
        this.codes.trimToSize();
        this.strings.trimToSize();
        this.images.trimToSize();
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getStart() {
        return this.start;
    }
    
    public String getURLString() {
        if (this.start == null) {
            return this.location;
        }
        return String.valueOf(this.location) + "#" + this.start;
    }
    
    protected synchronized void draw(final HtmlPager htmlPager) {
        htmlPager.setBase(this.base);
        htmlPager.setColors(this.bgColor, this.textColor, this.linkColor);
        this.codes.reset();
        this.strings.reset();
        this.images.reset();
        while (this.codes.hasMoreElements()) {
            final int nextElement = this.codes.nextElement();
            if (nextElement >= 0) {
                this.drawOpen(nextElement, htmlPager);
            }
            else {
                this.drawClose(-nextElement, htmlPager);
            }
        }
        htmlPager.finish();
    }
    
    private void fillLocationAndStart(final URL url) {
        final String protocol = url.getProtocol();
        final String host = url.getHost();
        final int port = url.getPort();
        String s;
        for (s = url.getFile(); s.startsWith("/"); s = s.substring(1)) {}
        if (port < 0) {
            this.location = String.valueOf(protocol) + "://" + host + "/" + s;
        }
        else {
            this.location = String.valueOf(protocol) + "://" + host + ":" + port + "/" + s;
        }
        this.start = url.getRef();
    }
    
    private void parse(final HtmlTokenizer htmlTokenizer) {
        while (!htmlTokenizer.eof()) {
            if (!this.parseTextItem(htmlTokenizer) && !this.parseOpenItem(htmlTokenizer) && !this.parseCloseItem(htmlTokenizer)) {
                htmlTokenizer.getTagOrText();
            }
        }
    }
    
    private boolean parseTextItem(final HtmlTokenizer htmlTokenizer) {
        String s;
        if (this.endOfText == null) {
            s = htmlTokenizer.getText();
        }
        else {
            s = htmlTokenizer.getPreformattedText(this.endOfText);
        }
        if (s != null) {
            this.codes.addElement(0);
            this.strings.addElement(s);
            return true;
        }
        return false;
    }
    
    private boolean parseOpenItem(final HtmlTokenizer htmlTokenizer) {
        for (int i = 1; i < 42; ++i) {
            final Hashtable openTag = htmlTokenizer.getOpenTag(HtmlDocument.names[i]);
            if (openTag != null) {
                this.codes.addElement(i);
                switch (i) {
                    case 1: {
                        this.strings.addElement(openTag.get("HREF"));
                        this.strings.addElement(openTag.get("NAME"));
                        break;
                    }
                    case 4: {
                        final String s = openTag.get("HREF");
                        this.strings.addElement(s);
                        try {
                            this.base = new URL(s);
                        }
                        catch (Exception ex) {}
                        break;
                    }
                    case 7: {
                        this.bgColor = getColor(openTag.get("BGCOLOR"), this.bgColor);
                        this.textColor = getColor(openTag.get("TEXT"), this.textColor);
                        this.linkColor = getColor(openTag.get("LINK"), this.linkColor);
                        break;
                    }
                    case 17: {
                        this.strings.addElement(openTag.get("SIZE"));
                        this.strings.addElement(openTag.get("COLOR"));
                        break;
                    }
                    case 26: {
                        this.images.addElement(this.base, openTag.get("SRC"), getInt(openTag.get("WIDTH")), getInt(openTag.get("HEIGHT")));
                        this.strings.addElement(openTag.get("ALIGN"));
                        break;
                    }
                    case 33: {
                        this.endOfText = "";
                        break;
                    }
                    case 37: {
                        this.title = htmlTokenizer.getText();
                        while (!htmlTokenizer.getCloseTag(HtmlDocument.names[i])) {
                            htmlTokenizer.getTagOrText();
                        }
                        break;
                    }
                    case 29:
                    case 41: {
                        this.endOfText = "/" + HtmlDocument.names[i];
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean parseCloseItem(final HtmlTokenizer htmlTokenizer) {
        for (int i = 1; i < 42; ++i) {
            if (htmlTokenizer.getCloseTag(HtmlDocument.names[i])) {
                this.codes.addElement(-i);
                if (i == 33 || i == 29 || i == 41) {
                    this.endOfText = null;
                }
                return true;
            }
        }
        return false;
    }
    
    private void drawOpen(final int n, final HtmlPager htmlPager) {
        switch (n) {
            case 0: {
                htmlPager.drawText(this.strings.nextElement());
            }
            case 1: {
                htmlPager.pushAnchor(this.strings.nextElement(), this.strings.nextElement());
            }
            case 2: {
                htmlPager.pushItalic();
            }
            case 3: {
                htmlPager.pushBold();
            }
            case 4: {}
            case 5: {
                htmlPager.pushFontSize(htmlPager.getFontSize() - 1);
            }
            case 6: {
                htmlPager.pushRightMargin();
                htmlPager.pushLeftMargin(true);
            }
            case 7: {}
            case 8: {
                htmlPager.drawNewLine(false);
            }
            case 9: {
                htmlPager.pushCenter();
            }
            case 10: {}
            case 11: {
                htmlPager.pushFixedFont();
            }
            case 12: {
                htmlPager.drawNewLine(false);
            }
            case 13: {
                htmlPager.pushLeftMargin(true);
                htmlPager.pushListButton();
            }
            case 14: {
                htmlPager.pushLeftMargin(true);
            }
            case 15: {
                htmlPager.popLeftMargin(true);
                htmlPager.pushLeftMargin(false);
            }
            case 16: {
                htmlPager.pushItalic();
            }
            case 17: {
                final int fontSize = getFontSize(this.strings.nextElement(), htmlPager);
                htmlPager.pushFontColor(getColor(this.strings.nextElement(), htmlPager.getFontColor()));
                htmlPager.pushFontSize(fontSize);
            }
            case 18: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(1);
            }
            case 19: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(2);
            }
            case 20: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(3);
            }
            case 21: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(4);
            }
            case 22: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(5);
            }
            case 23: {
                htmlPager.drawNewLine(true);
                htmlPager.pushStandardFont();
                htmlPager.pushBold();
                htmlPager.pushFontSize(6);
            }
            case 24: {
                htmlPager.drawRule();
            }
            case 25: {
                htmlPager.pushItalic();
            }
            case 26: {
                htmlPager.drawImage(this.images.nextElement(), this.strings.nextElement());
            }
            case 27: {
                htmlPager.pushFixedFont();
            }
            case 28: {
                htmlPager.drawNewLine(false);
                htmlPager.drawListItem();
            }
            case 29: {
                htmlPager.pushPreformatted();
            }
            case 30: {
                htmlPager.pushLeftMargin(true);
                htmlPager.pushListButton();
            }
            case 31: {
                htmlPager.pushLeftMargin(true);
                htmlPager.pushListNumber();
            }
            case 32: {
                htmlPager.drawNewLine(true);
            }
            case 33: {
                htmlPager.pushPreformatted();
            }
            case 34: {
                htmlPager.pushFixedFont();
            }
            case 35: {
                htmlPager.pushFontSize(htmlPager.getFontSize() + 1);
            }
            case 36: {
                htmlPager.pushBold();
            }
            case 37: {}
            case 38: {
                htmlPager.pushFixedFont();
            }
            case 39: {
                htmlPager.pushLeftMargin(true);
                htmlPager.pushListButton();
            }
            case 40: {
                htmlPager.pushFixedFont();
                htmlPager.pushBold();
            }
            case 41: {
                htmlPager.pushPreformatted();
            }
            default: {}
        }
    }
    
    private void drawClose(final int n, final HtmlPager htmlPager) {
        switch (n) {
            case 0: {}
            case 1: {
                htmlPager.popAnchor();
            }
            case 2: {
                htmlPager.popFont();
            }
            case 3: {
                htmlPager.popFont();
            }
            case 4: {}
            case 5: {
                htmlPager.popFont();
            }
            case 6: {
                htmlPager.popRightMargin();
                htmlPager.popLeftMargin(true);
            }
            case 7: {}
            case 8: {}
            case 9: {
                htmlPager.popCenter();
            }
            case 10: {}
            case 11: {
                htmlPager.popFont();
            }
            case 12: {}
            case 13: {
                htmlPager.popList();
                htmlPager.popLeftMargin(true);
            }
            case 14: {
                htmlPager.popLeftMargin(true);
            }
            case 15: {}
            case 16: {
                htmlPager.popFont();
            }
            case 17: {
                htmlPager.popFontColor();
                htmlPager.popFont();
            }
            case 18: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 19: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 20: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 21: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 22: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 23: {
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.popFont();
                htmlPager.drawNewLine(true);
            }
            case 24: {}
            case 25: {
                htmlPager.popFont();
            }
            case 26: {}
            case 27: {
                htmlPager.popFont();
            }
            case 28: {}
            case 29: {
                htmlPager.popPreformatted();
            }
            case 30: {
                htmlPager.popList();
                htmlPager.popLeftMargin(true);
            }
            case 31: {
                htmlPager.popList();
                htmlPager.popLeftMargin(true);
            }
            case 32: {}
            case 33: {
                htmlPager.popPreformatted();
            }
            case 34: {
                htmlPager.popFont();
            }
            case 35: {
                htmlPager.popFont();
            }
            case 36: {
                htmlPager.popFont();
            }
            case 37: {}
            case 38: {
                htmlPager.popFont();
            }
            case 39: {
                htmlPager.popList();
                htmlPager.popLeftMargin(true);
            }
            case 40: {
                htmlPager.popFont();
                htmlPager.popFont();
            }
            case 41: {
                htmlPager.popPreformatted();
            }
            default: {}
        }
    }
    
    private void pushHeader(final int n, final HtmlPager htmlPager) {
        htmlPager.pushStandardFont();
        htmlPager.pushBold();
        htmlPager.pushFontSize(n);
    }
    
    private void popHeader(final HtmlPager htmlPager) {
        htmlPager.popFont();
        htmlPager.popFont();
        htmlPager.popFont();
    }
    
    private static int getFontSize(final String s, final HtmlPager htmlPager) {
        if (s == null) {
            return htmlPager.getFontSize();
        }
        try {
            if (s.charAt(0) == '+') {
                return htmlPager.getFontSize() - Integer.parseInt(s.substring(1), 10);
            }
            if (s.charAt(0) == '-') {
                return htmlPager.getFontSize() + Integer.parseInt(s.substring(1), 10);
            }
            return 7 - Integer.parseInt(s, 10);
        }
        catch (Exception ex) {
            return htmlPager.getFontSize();
        }
    }
    
    private static Color getColor(String substring, final Color color) {
        if (substring == null) {
            return color;
        }
        if (substring.charAt(0) == '#') {
            substring = substring.substring(1);
        }
        try {
            final int int1 = Integer.parseInt(substring.substring(0, 2), 16);
            final int int2 = Integer.parseInt(substring.substring(2, 4), 16);
            final int int3 = Integer.parseInt(substring.substring(4, 6), 16);
            if (int1 < 0 || int2 < 0 || int3 < 0) {
                return color;
            }
            return new Color(int1, int2, int3);
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    private static int getInt(final String s) {
        try {
            return Integer.parseInt(s, 10);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    private static String get(final Hashtable hashtable, final String s) {
        return hashtable.get(s);
    }
    
    static {
        HtmlDocument.TYPE_IMAGE = 1;
        HtmlDocument.TYPE_TEXT = 2;
        HtmlDocument.TYPE_SOUND = 3;
        names = new String[] { null, "A", "ADDRESS", "B", "BASE", "BIG", "BLOCKQUOTE", "BODY", "BR", "CENTER", "CITE", "CODE", "DD", "DIR", "DL", "DT", "EM", "FONT", "H1", "H2", "H3", "H4", "H5", "H6", "HR", "I", "IMG", "KBD", "LI", "LISTING", "MENU", "OL", "P", "PRE", "SAMP", "SMALL", "STRONG", "TITLE", "TT", "UL", "VAR", "XMP" };
    }
}
