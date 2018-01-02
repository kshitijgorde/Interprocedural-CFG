import java.io.InputStream;
import java.io.BufferedInputStream;
import java.applet.Applet;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class SitemapParser
{
    private String m_sitemap;
    private boolean m_fSuccess;
    private ElementList m_list;
    private HHCtrl m_applet;
    private boolean m_EOF;
    private DataInputStream m_dis;
    private boolean m_fUseFolders;
    private int m_styleSet;
    private long m_exWinStyle;
    private long m_winStyle;
    private String m_font;
    private int m_pos;
    private int m_level;
    static String[] rgEntities;
    private boolean m_fFoundSiteProps;
    private Vector m_categories;
    private Vector m_types;
    private String m_frame;
    private String m_imageList;
    private int m_imageWidth;
    private int m_numImages;
    
    long getWinStyle() {
        return this.m_winStyle;
    }
    
    public boolean success() {
        return this.m_fSuccess;
    }
    
    private String getNextTag() {
        if (!this.hasMoreTags()) {
            return null;
        }
        this.m_pos = this.m_sitemap.indexOf("<", this.m_pos);
        while (this.m_sitemap.indexOf("!--", this.m_pos) == 1) {
            this.m_pos = this.findTagEnd(this.m_pos, true);
            if (this.m_pos == -1) {
                return null;
            }
            this.m_pos = this.m_sitemap.indexOf("<", this.m_pos);
        }
        int pos = this.m_pos;
        int index;
        while (true) {
            index = this.m_sitemap.indexOf(">", pos);
            if (index == -1) {
                this.getChunk();
            }
            else {
                final int index2 = this.m_sitemap.indexOf("<", pos + 1);
                if (index2 == -1 || index2 > index) {
                    break;
                }
                pos = index + 1;
            }
        }
        if (index == -1) {
            return null;
        }
        final String trim = this.m_sitemap.substring(this.m_pos + 1, index).trim();
        this.m_pos = index;
        return trim;
    }
    
    private boolean getChunk() {
        int n = 0;
        String line = null;
        final int length = this.m_sitemap.length();
        try {
            while (n++ < 5) {
                if ((line = this.m_dis.readLine()) == null) {
                    break;
                }
                this.m_sitemap += line;
            }
        }
        catch (Exception ex) {
            this.m_EOF = true;
            return false;
        }
        if (line == null) {
            this.m_EOF = true;
            return this.m_sitemap.length() > length;
        }
        return true;
    }
    
    private int findTagEnd(final int n, final boolean b) {
        int n2 = n;
        int index;
        while (true) {
            index = this.m_sitemap.indexOf(b ? "-->" : ">", n2);
            if (index == -1) {
                this.getChunk();
            }
            else {
                final int index2 = this.m_sitemap.indexOf("<", n2 + 1);
                if (index2 == -1 || index2 > index) {
                    break;
                }
                n2 = index + 1;
            }
        }
        return index;
    }
    
    int getStyleSet() {
        return this.m_styleSet;
    }
    
    boolean useFolders() {
        return this.m_fUseFolders;
    }
    
    Vector getCategories() {
        return this.m_categories;
    }
    
    String getFrame() {
        return this.m_frame;
    }
    
    private void parseSitemap() {
        int n = 0;
        int n2 = 1;
        int n3 = 0;
        int n4 = 0;
        while (this.hasMoreTags()) {
            String s = this.getNextTag();
            if (this.getTagName(s).equalsIgnoreCase("OBJECT") && n == 0) {
                if (this.getTagParam(s, "type").equalsIgnoreCase("text/site properties")) {
                    this.m_fFoundSiteProps = true;
                }
                while (this.hasMoreTags()) {
                    s = this.getNextTag();
                    if (this.getTagName(s).equalsIgnoreCase("/OBJECT")) {
                        break;
                    }
                    if (!this.getTagName(s).equalsIgnoreCase("param")) {
                        continue;
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("FRAME") || this.getTagParam(s, "name").equalsIgnoreCase("FRAMENAME")) {
                        this.m_frame = new String(this.getTagParam(s, "value"));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("FONT")) {
                        this.m_font = new String(this.getTagParam(s, "value"));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("CATEGORY")) {
                        this.m_categories.addElement(new String(this.getTagParam(s, "value")));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("TYPE")) {
                        this.m_types.addElement(new String(this.getTagParam(s, "value")));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("IMAGELIST")) {
                        this.m_imageList = new String(this.getTagParam(s, "value"));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("IMAGE WIDTH")) {
                        this.m_imageWidth = Integer.parseInt(this.getTagParam(s, "value"));
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("EXWINDOW STYLES")) {
                        final String s2 = new String(this.getTagParam(s, "value"));
                        if (s2.indexOf("0x") == 0 || s2.indexOf("0X") == 0) {
                            final String substring = s2.substring(2);
                            try {
                                this.m_exWinStyle = Long.parseLong(substring, 16);
                                this.m_styleSet |= 0x2;
                            }
                            catch (Exception ex) {
                                this.m_exWinStyle = 0L;
                            }
                        }
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("WINDOW STYLES")) {
                        final String s3 = new String(this.getTagParam(s, "value"));
                        if (s3.indexOf("0x") == 0 || s3.indexOf("0X") == 0) {
                            final String substring2 = s3.substring(2);
                            try {
                                this.m_winStyle = Long.parseLong(substring2, 16);
                                this.m_styleSet |= 0x1;
                            }
                            catch (Exception ex2) {
                                this.m_winStyle = 0L;
                            }
                        }
                    }
                    if (this.getTagParam(s, "name").equalsIgnoreCase("IMAGETYPE") && this.getTagParam(s, "value").equalsIgnoreCase("FOLDER")) {
                        this.m_fUseFolders = true;
                    }
                    if (!this.getTagParam(s, "name").equalsIgnoreCase("NUMBERIMAGES")) {
                        continue;
                    }
                    try {
                        this.m_numImages = Integer.parseInt(this.getTagParam(s, "value"));
                    }
                    catch (Exception ex3) {
                        this.m_numImages = 0;
                    }
                }
            }
            if (this.getTagName(s).equalsIgnoreCase("OBJECT") && n != 0) {
                final String tagParam = this.getTagParam(s, "type");
                if (tagParam != null && !tagParam.equalsIgnoreCase("text/sitemap")) {
                    System.out.println("    discarded - not sitemap");
                }
                String s4 = null;
                String s5 = "";
                String s6 = "";
                int intValue = 0;
                int intValue2 = -1;
                int n5 = 0;
                String s7 = "";
                String s8 = "";
                Vector<String> related = null;
                boolean b = false;
                String url = "";
                String seeAlso = "";
                while (this.hasMoreTags()) {
                    s = this.getNextTag();
                    if (this.getTagName(s).equalsIgnoreCase("param")) {
                        if (this.getTagParam(s, "name").equalsIgnoreCase("NAME")) {
                            if (s4 == null) {
                                s4 = new String(replaceEscapes((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value")));
                            }
                            else {
                                if (n5 > 0) {
                                    if (related == null) {
                                        related = new Vector<String>();
                                    }
                                    related.addElement(s7 + ";" + s8);
                                }
                                ++n5;
                                s7 = new String(replaceEscapes((this.getTagParam(s, "value") == null) ? "" : replaceEscapes(this.getTagParam(s, "value"))));
                                s8 = "";
                            }
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("SEE ALSO")) {
                            seeAlso = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("URL") || this.getTagParam(s, "name").equalsIgnoreCase("LOCAL")) {
                            if (n5 == 0) {
                                s5 = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                            }
                            else {
                                s8 = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                            }
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("Merge")) {
                            b = true;
                            url = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                            if (s4 == null) {
                                s4 = "";
                            }
                            if (this.m_level == 1) {
                                try {
                                    new SitemapParser(new URL(url), this.m_list, this.m_applet);
                                }
                                catch (MalformedURLException ex4) {
                                    try {
                                        new SitemapParser(new URL(this.m_applet.getDocumentBase(), url), this.m_list, this.m_applet);
                                    }
                                    catch (MalformedURLException ex5) {
                                        this.m_applet.showStatus(this.m_applet.getString("cnt.merge.err") + " " + url);
                                    }
                                }
                            }
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("SECONDARY")) {
                            final String s9 = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("FRAME") || this.getTagParam(s, "name").equalsIgnoreCase("FRAMENAME")) {
                            s6 = new String((this.getTagParam(s, "value") == null) ? "" : this.getTagParam(s, "value"));
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("IMAGENUMBER")) {
                            try {
                                intValue2 = Integer.valueOf(this.getTagParam(s, "value"));
                            }
                            catch (Exception ex6) {
                                intValue2 = -1;
                            }
                        }
                        if (this.getTagParam(s, "name").equalsIgnoreCase("NEW")) {
                            try {
                                intValue = Integer.valueOf(this.getTagParam(s, "value"));
                            }
                            catch (Exception ex7) {
                                intValue = 0;
                            }
                        }
                    }
                    if (this.getTagName(s).equalsIgnoreCase("/OBJECT")) {
                        if (n5 > 0) {
                            if (related == null) {
                                related = new Vector<String>();
                            }
                            related.addElement(s7 + ";" + s8);
                        }
                        if (s4 == null) {
                            continue;
                        }
                        final Element element = new Element(s4, this.m_level - 1, null, s5, s6, this.m_applet, 1);
                        element.m_isNew = intValue;
                        element.m_imgNum = intValue2 - 1;
                        element.m_related = related;
                        n2 = 1;
                        if (b) {
                            element.m_merge = true;
                            element.m_url = url;
                        }
                        element.m_seeAlso = seeAlso;
                        final int level = this.m_level;
                        if (!b || this.m_level > 1) {
                            this.m_list.addElement(element);
                        }
                        if (++n4 % 10 == 0) {
                            this.m_applet.showStatus(this.m_applet.getString("cnt.load.status2") + " (" + n4 + ")");
                            break;
                        }
                        break;
                    }
                }
            }
            if (this.getTagName(s).equalsIgnoreCase("UL")) {
                if (n2 == 1) {
                    ++this.m_level;
                    n2 = 0;
                }
                else {
                    ++n3;
                }
            }
            if (this.getTagName(s).equalsIgnoreCase("/UL")) {
                if (n3 == 0) {
                    --this.m_level;
                }
                else {
                    --n3;
                }
                if (this.m_level < 0) {
                    this.m_level = 0;
                }
            }
            if (this.getTagName(s).equalsIgnoreCase("LI")) {
                n = 1;
            }
            if (this.getTagName(s).equalsIgnoreCase("/LI")) {
                n = 0;
            }
            this.tossParsed();
        }
    }
    
    String getFont() {
        return this.m_font;
    }
    
    private void tossParsed() {
        this.m_sitemap = this.m_sitemap.substring(this.m_pos);
        this.m_pos = 0;
    }
    
    boolean isParsed() {
        return this.m_fSuccess;
    }
    
    int getImageWidth() {
        return this.m_imageWidth;
    }
    
    boolean foundSiteProps() {
        return this.m_fFoundSiteProps;
    }
    
    static {
        SitemapParser.rgEntities = new String[] { "AElig", "\u00c6", "Aacute", "\u00c1", "Acirc", "\u00c2", "Agrave", "\u00c0", "Aring", "\u00c5", "Atilde", "\u00c3", "Auml", "\u00c4", "Ccedil", "\u00c7", "Dstrok", "\u00d0", "ETH", "\u00d0", "Eacute", "\u00c9", "Ecirc", "\u00ca", "Egrave", "\u00c8", "Euml", "\u00cb", "Iacute", "\u00cd", "Icirc", "\u00ce", "Igrave", "\u00cc", "Iuml", "\u00cf", "Ntilde", "\u00d1", "Oacute", "\u00d3", "Ocirc", "\u00d4", "Ograve", "\u00d2", "Oslash", "\u00d8", "Otilde", "\u00d5", "Ouml", "\u00d6", "THORN", "\u00de", "Uacute", "\u00da", "Ucirc", "\u00db", "Ugrave", "\u00d9", "Uuml", "\u00dc", "Yacute", "\u00dd", "aacute", "\u00e1", "acirc", "\u00e2", "acute", "´", "aelig", "\u00e6", "agrave", "\u00e0", "amp", "&", "aring", "\u00e5", "atilde", "\u00e3", "auml", "\u00e4", "brkbar", "¦", "brvbar", "¦", "ccedil", "\u00e7", "cedil", "¸", "cent", "¢", "copy", "©", "curren", "¤", "deg", "°", "die", "¨", "divide", "\u00f7", "eacute", "\u00e9", "ecirc", "\u00ea", "egrave", "\u00e8", "eth", "\u00f0", "euml", "\u00eb", "frac12", "½", "frac14", "¼", "frac34", "¾", "gt", ">", "hibar", "¯", "iacute", "\u00ed", "icirc", "\u00ee", "iexcl", "¡", "igrave", "\u00ec", "iquest", "¿", "iuml", "\u00ef", "laquo", "«", "lt", "<", "macr", "¯", "micro", "µ", "middot", "·", "nbsp", " ", "not", "¬", "ntilde", "\u00f1", "oacute", "\u00f3", "ocirc", "\u00f4", "ograve", "\u00f2", "ordf", "ª", "ordm", "º", "oslash", "\u00f8", "otilde", "\u00f5", "ouml", "\u00f6", "para", "¶", "plusmn", "±", "pound", "£", "quot", "\"", "raquo", "»", "reg", "®", "sect", "§", "shy", "\u00ad", "sup1", "¹", "sup2", "²", "sup3", "³", "szlig", "\u00df", "thorn", "\u00fe", "times", "\u00d7", "trade", "\u0099", "uacute", "\u00fa", "ucirc", "\u00fb", "ugrave", "\u00f9", "uml", "¨", "uuml", "\u00fc", "yacute", "\u00fd", "yen", "¥", "yuml", "\u00ff", "", "" };
    }
    
    long getExWinStyle() {
        return this.m_exWinStyle;
    }
    
    SitemapParser(final URL url, final ElementList list, final HHCtrl applet) {
        this.m_exWinStyle = 8388661L;
        this.m_winStyle = 512L;
        this.m_font = "";
        this.m_frame = "";
        this.m_imageWidth = 16;
        this.m_sitemap = new String();
        this.m_categories = new Vector();
        this.m_types = new Vector();
        this.m_list = list;
        (this.m_applet = applet).showStatus(this.m_applet.getString("cnt.load.status"));
        try {
            this.m_dis = (DataInputStream)Runtime.getRuntime().getLocalizedInputStream(new DataInputStream(new BufferedInputStream(url.openStream(), 8192)));
            this.getChunk();
            this.parseSitemap();
            this.m_fSuccess = true;
        }
        catch (Exception ex) {}
    }
    
    String getImageList() {
        return this.m_imageList;
    }
    
    static String replaceEscapes(final String s) {
        if (s == null) {
            return "";
        }
        if (s.indexOf("%") == -1 && s.indexOf("\\") == -1 && s.indexOf("&") == -1) {
            return s;
        }
        int i = 0;
        String s2 = "";
        try {
            while (i < s.length()) {
                char c = '\0';
                if (s.charAt(i) == '%') {
                    ++i;
                    final char char1 = s.charAt(i);
                    if (Character.isDigit(char1)) {
                        c = (char)(char1 - '0');
                        ++i;
                    }
                    else if (char1 >= 'a' && char1 <= 'f') {
                        c += (char)(char1 - 'a' + '\n');
                        ++i;
                    }
                    else if (char1 >= 'A' && char1 <= 'F') {
                        c += (char)(char1 - 'A' + '\n');
                        ++i;
                    }
                    if (i < s.length()) {
                        final char char2 = s.charAt(i);
                        if (Character.isDigit(char2)) {
                            c = (char)(c * '\u0010' + char2 - '0');
                            ++i;
                        }
                        else if (char2 >= 'a' && char2 <= 'f') {
                            c = (char)(c * '\u0010' + char2 - 'a' + '\n');
                            ++i;
                        }
                        else if (char2 >= 'A' && char2 <= 'F') {
                            c = (char)(c * '\u0010' + char2 - 'A' + '\n');
                            ++i;
                        }
                    }
                    if (c <= '\0') {
                        continue;
                    }
                    s2 += c;
                }
                else if (s.charAt(i) == '\\') {
                    ++i;
                    final char char3 = s.charAt(i);
                    if (char3 == 'n' || char3 == 'r' || char3 == 't') {
                        s2 += ' ';
                        ++i;
                    }
                    else if (char3 >= '0' && char3 <= '7') {
                        int n = char3 - '0';
                        if (++i < s.length() - 1) {
                            final char char4 = s.charAt(i);
                            if (char4 >= '0' && char4 <= '7') {
                                n = n * 8 + char4 - 48;
                                ++i;
                            }
                            if (i < s.length() - 1) {
                                final char char5 = s.charAt(i);
                                if (char5 >= '0' && char5 <= '7') {
                                    n = n * 8 + char5 - 48;
                                    ++i;
                                }
                            }
                        }
                        if (n <= 0) {
                            continue;
                        }
                        s2 += (char)n;
                    }
                    else {
                        if (char3 != 'x' && char3 != 'X') {
                            continue;
                        }
                        char c2 = '\0';
                        i += 2;
                        if (i == -1 || i >= s.length() - 1) {
                            continue;
                        }
                        final char char6 = s.charAt(i);
                        if (Character.isDigit(char6)) {
                            c2 = (char)(char6 - '0');
                            ++i;
                        }
                        else if (char6 >= 'a' && char6 <= 'f') {
                            c2 += (char)(char6 - 'a' + '\n');
                            ++i;
                        }
                        else if (char6 >= 'A' && char6 <= 'F') {
                            c2 += (char)(char6 - 'A' + '\n');
                            ++i;
                        }
                        if (i < s.length()) {
                            final char char7 = s.charAt(i);
                            if (Character.isDigit(char7)) {
                                c2 = (char)(c2 * '\u0010' + char7 - '0');
                                ++i;
                            }
                            else if (char7 >= 'a' && char7 <= 'f') {
                                c2 = (char)(c2 * '\u0010' + char7 - 'a' + '\n');
                                ++i;
                            }
                            else if (char7 >= 'A' && char7 <= 'F') {
                                c2 = (char)(c2 * '\u0010' + char7 - 'A' + '\n');
                                ++i;
                            }
                        }
                        if (c2 <= '\0') {
                            continue;
                        }
                        s2 += c2;
                    }
                }
                else if (s.charAt(i) == '&') {
                    if (s.charAt(i + 1) == '#') {
                        i += 2;
                        int n2 = 0;
                        while (s.charAt(i) != ';') {
                            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                                n2 = n2 * 10 + (s.charAt(i) - '0');
                            }
                            ++i;
                        }
                        if (n2 > 0) {
                            s2 += (char)n2;
                        }
                        ++i;
                    }
                    else {
                        int n3;
                        for (n3 = i + 1; s.charAt(n3) != ';' && n3 < s.length() - 1; ++n3) {}
                        final String substring = s.substring(i + 1, n3);
                        i = n3 + 1;
                        int n4;
                        for (n4 = 0; SitemapParser.rgEntities[n4 * 2].length() > 0; ++n4) {
                            if (substring.equalsIgnoreCase(SitemapParser.rgEntities[n4 * 2])) {
                                s2 += SitemapParser.rgEntities[n4 * 2 + 1];
                                break;
                            }
                        }
                        if (substring.equalsIgnoreCase(SitemapParser.rgEntities[n4 * 2])) {
                            continue;
                        }
                        s2 += "?";
                    }
                }
                else {
                    s2 += s.charAt(i);
                    ++i;
                }
            }
        }
        catch (Exception ex) {
            s2 += s.charAt(i - 1);
        }
        return s2;
    }
    
    private String getTagParam(final String s, final String s2) {
        String s3 = s;
        String s4 = null;
        boolean b = false;
        while (s3.toUpperCase().indexOf(s2.toUpperCase()) != -1) {
            s4 = s3.substring(s3.toUpperCase().indexOf(s2.toUpperCase()) + s2.length(), s3.length()).trim();
            if (s4.startsWith("=")) {
                s4 = s4.substring(1, s4.length()).trim();
                b = true;
                break;
            }
            s3 = s4;
        }
        if (!b) {
            return null;
        }
        if (s4.startsWith("\"")) {
            if (s4.indexOf("\"", 1) != -1) {
                return s4.substring(1, s4.indexOf("\"", 1));
            }
            return null;
        }
        else {
            if (s4.indexOf(" ") != -1) {
                return s4.substring(0, s4.indexOf(" ")).trim();
            }
            return s4.trim();
        }
    }
    
    private boolean hasMoreTags() {
        return this.m_sitemap.indexOf("<", this.m_pos) != -1 || (this.getChunk() && this.hasMoreTags());
    }
    
    private String getTagName(final String s) {
        if (s.indexOf(" ") != -1) {
            return s.substring(0, s.indexOf(" "));
        }
        return s;
    }
}
