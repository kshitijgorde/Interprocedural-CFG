// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Enumeration;
import java.awt.Font;
import lotus.notes.util.XMLHandler;
import java.util.Stack;
import java.awt.Color;
import java.util.Vector;
import lotus.notes.util.XMLParser;
import lotus.notes.util.XMLHandlerBase;

public class XMLViewDesignParser extends XMLHandlerBase implements DesignConstants
{
    private ViewDesignInfo designInfo;
    private String url;
    private XMLParser parser;
    private ViewColumnInfo cInfo;
    private Integer i;
    private Vector fonts;
    private Vector colors;
    private String repeat;
    private String name;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private Color fontColor;
    private String lang;
    private boolean handleHFONT;
    private boolean handleCFONT;
    private boolean handleVIEWBACKGROUND;
    private boolean handleIMAGEREF;
    private Stack elementStack;
    private static final boolean PARSE_DEBUG = false;
    
    public XMLViewDesignParser(final String url, final ViewDesignInfo designInfo) {
        this.fonts = new Vector();
        this.colors = new Vector();
        this.repeat = "";
        this.name = null;
        this.fontName = null;
        this.fontStyle = -1;
        this.fontSize = -1;
        this.fontColor = null;
        this.lang = "";
        this.handleHFONT = false;
        this.handleCFONT = false;
        this.handleVIEWBACKGROUND = false;
        this.handleIMAGEREF = false;
        this.elementStack = new Stack();
        this.designInfo = designInfo;
        this.url = url;
        (this.parser = new XMLParser()).setHandler(this);
    }
    
    public XMLViewDesignParser(final String s, final ViewDesignInfo viewDesignInfo, final String lang) {
        this(s, viewDesignInfo);
        this.lang = lang;
    }
    
    public void parse() {
        try {
            this.parser.parse(this.url, null);
        }
        catch (Exception ex) {
            System.out.println("Exception occurred while reading view design data: " + ex);
        }
    }
    
    public void attribute(final String s, final String s2, final boolean b) throws Exception {
        if (s.equalsIgnoreCase("headerlines")) {
            this.i = Integer.valueOf(s2);
            this.designInfo.headerLines = this.i;
        }
        else if (s.equalsIgnoreCase("rowlines")) {
            this.i = Integer.valueOf(s2);
            final int intValue = this.i;
            if (intValue > 0) {
                this.designInfo.rowsPerLine = intValue;
            }
        }
        else if (s.equalsIgnoreCase("spacing")) {
            this.i = Integer.valueOf(s2);
            this.designInfo.rowSpacing = this.i;
        }
        else if (s.equalsIgnoreCase("columns")) {
            this.i = Integer.valueOf(s2);
            this.designInfo.numColumns = this.i;
        }
        else if (s.equalsIgnoreCase("categories")) {
            this.i = Integer.valueOf(s2);
            this.designInfo.numCategories = this.i;
        }
        else if (s.equalsIgnoreCase("direction")) {
            this.i = Integer.valueOf(s2);
            this.designInfo.direction = this.i;
        }
        else if (s.equalsIgnoreCase("extendlastcolumn")) {
            this.designInfo.extendLastColumnWidth = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("hidemarginborder")) {
            this.designInfo.hideMarginBorder = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("name")) {
            this.name = s2;
        }
        else if (s.equalsIgnoreCase("repeat")) {
            this.repeat = s2;
        }
        else if (s.equalsIgnoreCase("totalscolor")) {
            this.designInfo.totalsColor = this.findColor(s2);
        }
        else if (s.equalsIgnoreCase("altrowcolor")) {
            this.designInfo.altrowColor = this.findColor(s2);
        }
        else if (s.equalsIgnoreCase("headerbgcolor")) {
            this.designInfo.headerColor = this.findColor(s2);
        }
        else if (s.equalsIgnoreCase("columnnumber")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.colNumber = this.i;
        }
        else if (s.equalsIgnoreCase("title")) {
            this.cInfo.title = s2;
        }
        else if (s.equalsIgnoreCase("totals")) {
            this.cInfo.columnTotal = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("sort")) {
            this.cInfo.sort = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("sortdescending")) {
            this.cInfo.sortDescending = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("sortcategorize")) {
            this.cInfo.sortCategorize = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("width")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.width = this.i;
        }
        else if (s.equalsIgnoreCase("response")) {
            this.cInfo.response = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("hidden")) {
            this.cInfo.hidden = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("resize")) {
            this.cInfo.resizable = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("align")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.alignment = this.i;
        }
        else if (s.equalsIgnoreCase("headeralign")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.headerAlignment = this.i;
        }
        else if (s.equalsIgnoreCase("readingorder")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.readingOrder = this.i;
        }
        else if (s.equalsIgnoreCase("headerreadingorder")) {
            this.i = Integer.valueOf(s2);
            this.cInfo.headerReadingOrder = this.i;
        }
        else if (s.equalsIgnoreCase("resortascending")) {
            this.cInfo.resortAscending = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("resortdescending")) {
            this.cInfo.resortDescending = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("resorttoview")) {
            this.cInfo.resortToView = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("resortviewunid")) {
            this.cInfo.resortViewUNID = s2;
        }
        else if (s.equalsIgnoreCase("twistie")) {
            this.cInfo.twistie = s2.equalsIgnoreCase("true");
        }
        else if (s.equalsIgnoreCase("twistieimage")) {
            this.cInfo.twistieimage = s2;
        }
        else if (s.equalsIgnoreCase("face")) {
            this.fontName = s2;
        }
        else if (s.equalsIgnoreCase("style")) {
            if (s2.equalsIgnoreCase("b")) {
                this.fontStyle = 1;
            }
            else if (s2.equalsIgnoreCase("i")) {
                this.fontStyle = 2;
            }
            else if (s2.equalsIgnoreCase("bi")) {
                this.fontStyle = 3;
            }
            else {
                this.fontStyle = 0;
            }
        }
        else if (s.equalsIgnoreCase("size")) {
            this.i = Integer.valueOf(s2);
            this.fontSize = this.i;
        }
        else if (s.equalsIgnoreCase("color")) {
            this.fontColor = this.findColor(s2);
        }
        else if (s.equalsIgnoreCase("icon")) {
            this.cInfo.isIcon = true;
        }
        else if (s.equalsIgnoreCase("format")) {
            if (s2.equalsIgnoreCase("general")) {
                this.cInfo.numberFormat.format = 13;
            }
            else if (s2.equalsIgnoreCase("fixed")) {
                this.cInfo.numberFormat.format = 14;
            }
            else if (s2.equalsIgnoreCase("scientific")) {
                this.cInfo.numberFormat.format = 15;
            }
            else if (s2.equalsIgnoreCase("currency")) {
                this.cInfo.numberFormat.format = 16;
            }
        }
        else if (s.equalsIgnoreCase("digits")) {
            this.cInfo.numberFormat.digits = Integer.parseInt(s2);
        }
        else if (s.equalsIgnoreCase("punctuated")) {
            this.cInfo.numberFormat.isPunctuated = true;
        }
        else if (s.equalsIgnoreCase("percent")) {
            this.cInfo.numberFormat.isPercent = true;
        }
        else if (s.equalsIgnoreCase("parens")) {
            this.cInfo.numberFormat.isParens = true;
        }
        else if (s.equalsIgnoreCase("varying")) {
            this.cInfo.numberFormat.isVarying = true;
        }
        else if (s.equalsIgnoreCase("date")) {
            if (s2.equalsIgnoreCase("yearmonthday")) {
                this.cInfo.dateTimeFormat.dateFormat = 7;
            }
            else if (s2.equalsIgnoreCase("monthday")) {
                this.cInfo.dateTimeFormat.dateFormat = 6;
            }
            else if (s2.equalsIgnoreCase("yearmonth")) {
                this.cInfo.dateTimeFormat.dateFormat = 3;
            }
        }
        else if (s.equalsIgnoreCase("time")) {
            if (s2.equalsIgnoreCase("hourminutesecond")) {
                this.cInfo.dateTimeFormat.dateFormat = 7;
            }
            else if (s2.equalsIgnoreCase("hourminute")) {
                this.cInfo.dateTimeFormat.dateFormat = 3;
            }
            else if (s2.equalsIgnoreCase("hour")) {
                this.cInfo.dateTimeFormat.dateFormat = 1;
            }
        }
        else if (s.equalsIgnoreCase("zone")) {
            if (s2.equalsIgnoreCase("never")) {
                this.cInfo.dateTimeFormat.dateFormat = 1;
            }
            else if (s2.equalsIgnoreCase("sometimes")) {
                this.cInfo.dateTimeFormat.dateFormat = 2;
            }
            else if (s2.equalsIgnoreCase("always")) {
                this.cInfo.dateTimeFormat.dateFormat = 3;
            }
        }
        else if (s.equalsIgnoreCase("show")) {
            if (s2.equalsIgnoreCase("date")) {
                this.cInfo.dateTimeFormat.dateFormat = 1;
            }
            else if (s2.equalsIgnoreCase("time")) {
                this.cInfo.dateTimeFormat.dateFormat = 2;
            }
            else if (s2.equalsIgnoreCase("datetime")) {
                this.cInfo.dateTimeFormat.dateFormat = 3;
            }
        }
        else if (s.equalsIgnoreCase("listseparator")) {
            if (s2.equalsIgnoreCase("comma")) {
                this.cInfo.listSeparator = ',';
            }
            else if (s2.equalsIgnoreCase("semicolon")) {
                this.cInfo.listSeparator = ';';
            }
            else if (s2.equalsIgnoreCase("newline")) {
                this.cInfo.listSeparator = '\n';
            }
            else if (s2.equalsIgnoreCase("space")) {
                this.cInfo.listSeparator = ' ';
            }
        }
    }
    
    public void startElement(final String s) throws Exception {
        this.elementStack.push(s);
        if (!s.equalsIgnoreCase("viewdesign")) {
            if (s.equalsIgnoreCase("cfont")) {
                this.handleVIEWBACKGROUND = true;
            }
            else if (s.equalsIgnoreCase("cfont")) {
                this.handleIMAGEREF = true;
            }
            else if (s.equalsIgnoreCase("column")) {
                this.cInfo = new ViewColumnInfo();
                if (this.designInfo.vColumnInfo == null) {
                    this.designInfo.vColumnInfo = new Vector(this.designInfo.numColumns);
                }
                this.designInfo.vColumnInfo.addElement(this.cInfo);
            }
            else if (s.equalsIgnoreCase("cfont")) {
                this.handleCFONT = true;
            }
            else if (s.equalsIgnoreCase("hfont")) {
                this.handleHFONT = true;
            }
        }
    }
    
    public void endElement(final String s) throws Exception {
        if (s.equalsIgnoreCase("viewbackground")) {
            this.handleVIEWBACKGROUND = false;
            if (this.repeat.equals("hrepeat")) {
                this.designInfo.bgImageRepeat = 1;
            }
            else if (this.repeat.equals("vrepeat")) {
                this.designInfo.bgImageRepeat = 2;
            }
            else if (this.repeat.equals("tile")) {
                this.designInfo.bgImageRepeat = 3;
            }
            else if (this.repeat.equals("resize")) {
                this.designInfo.bgImageRepeat = 4;
            }
            else if (this.repeat.equals("center")) {
                this.designInfo.bgImageRepeat = 5;
            }
            else {
                this.designInfo.bgImageRepeat = 0;
            }
        }
        else if (s.equalsIgnoreCase("imageref")) {
            this.handleIMAGEREF = false;
            this.designInfo.bgImage = this.name;
        }
        else if (s.equalsIgnoreCase("cfont")) {
            this.handleCFONT = false;
            this.cInfo.columnFont = this.findFont(this.fontName, this.fontStyle, this.fontSize);
            this.cInfo.textColor = this.fontColor;
        }
        else if (s.equalsIgnoreCase("hfont")) {
            this.handleHFONT = false;
            this.cInfo.headerFont = this.findFont(this.fontName, this.fontStyle, this.fontSize);
            this.cInfo.headerTextColor = this.fontColor;
        }
        this.fontName = null;
        this.fontStyle = -1;
        this.fontSize = -1;
        this.fontColor = Color.black;
        this.elementStack.pop();
    }
    
    private Font findFont(String s, int n, int n2) {
        String property = "";
        try {
            property = System.getProperty("os.name");
        }
        catch (Exception ex) {}
        if (this.lang != null && this.lang.length() > 0 && this.lang.equals("th") && property != null && property.indexOf("Win") != -1 && property.indexOf("98") != -1) {
            s = "DialogInput";
        }
        else if (s == null) {
            s = "Helvetica";
        }
        else if (s.indexOf("Helv") != -1) {
            s = "Helvetica";
        }
        else if (s.indexOf("Times") != -1) {
            s = "TimesRoman";
        }
        else if (s.indexOf("Sans") != -1) {
            s = "SansSerif";
        }
        else if (s.indexOf("Courier") != -1) {
            s = "Courier";
        }
        else {
            s = "Helvetica";
        }
        if (n == -1) {
            n = 0;
        }
        if (n2 == -1) {
            n2 = 12;
        }
        else {
            n2 += (int)(n2 * 0.4);
        }
        final Enumeration<Font> elements = this.fonts.elements();
        while (elements.hasMoreElements()) {
            final Font font = elements.nextElement();
            if (s.equalsIgnoreCase(font.getName()) && n == font.getStyle() && n2 == font.getSize()) {
                return font;
            }
        }
        final Font font2 = new Font(s, n, n2);
        this.fonts.addElement(font2);
        return font2;
    }
    
    private Color findColor(final String s) {
        final Color convertStringToColor = this.ConvertStringToColor(s, Color.black);
        if (convertStringToColor != Color.black) {
            final Enumeration<Color> elements = this.colors.elements();
            while (elements.hasMoreElements()) {
                final Color color = elements.nextElement();
                if (convertStringToColor.equals(color)) {
                    return color;
                }
            }
            this.colors.addElement(convertStringToColor);
        }
        return convertStringToColor;
    }
    
    private Color ConvertStringToColor(String substring, final Color color) {
        if (substring.startsWith("#")) {
            substring = substring.substring(1, 7);
        }
        Color color2;
        try {
            color2 = new Color(Integer.parseInt(substring, 16));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            color2 = color;
        }
        return color2;
    }
    
    private boolean isCurrentElement(final String s) {
        return this.elementStack.peek().equals(s);
    }
}
