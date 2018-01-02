import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class glossary extends Applet
{
    static final int diagonal = 4;
    static final int diagonal_not = 0;
    static final int right_border = 16;
    static final int border = 6;
    static final int box_space = 4;
    static final int termArrow = 0;
    static final int defArrow = 1;
    static final int NO_USER_INPUT = 0;
    static final int NEW_SELECTION = 1;
    static final int SCROLLING_DEF = 2;
    static final int NOTHING_NEW = 0;
    static final int CHANGE_NEXT = 1;
    static final int CHANGE_CLEAR = 2;
    static final int REFRESH = 1;
    static final int ENABLE = 1;
    static final int DISABLE = 2;
    static final int TERM_INSET = 8;
    static final boolean CLEAR_NEEDED = true;
    static final boolean NO_CLEAR = false;
    static final boolean RAISED = true;
    static final boolean DEPRESSED = false;
    boolean clear_hit;
    boolean tooLong;
    boolean allLinesFit;
    boolean bottom;
    boolean tabs;
    boolean[] highlightDown;
    boolean[] highlightUp;
    boolean[] display_fields;
    boolean[] search_fields;
    boolean newDefaultSpace;
    Color bgColor;
    Color bodyColor;
    Color termTextColor;
    Color defTextColor;
    Color defHyperlinkColor;
    Color bottomButtonTextColor;
    Color clearButtonTextColor;
    Color clearButtonDisabledTextColor;
    Color searchTextColor;
    Color searchFieldTextColor;
    Color bottomTextColor;
    Color bottomButtonColor;
    Color clearButtonColor;
    Color borderColor;
    Color arrowBorderColor;
    Color arrowActiveColor;
    Color arrowInactiveColor;
    Color termBgColor;
    Color termFgColor;
    Color[] tabColor;
    Color[] tabTextColor;
    Font microFont;
    Font plainFont;
    Font boldFont;
    Font regFont;
    Font smallFont;
    int maxParameters;
    int maxIndecies;
    int maxStrings;
    int maxUrls;
    int numTabs;
    int lineNumber;
    int wordLoop;
    int widthTest;
    int tabLoop;
    int urlLoop;
    int numUrls;
    int strLength;
    int stry;
    int regHeight;
    int regAscent;
    int firstLine;
    int lastLine;
    int maxRows;
    int box_top;
    int currentTab;
    int microHeight;
    int ap_height;
    int upy;
    int totalStringWidth;
    int bottom_display;
    int rowTwoStartsAt;
    int arrowx;
    int extra;
    int user_input;
    int tabInt;
    int def_box_width;
    int def_box_height;
    int loop;
    int str_loop;
    int left_edge;
    int downy;
    int totalStringWidthRow2;
    int totalStringWidthRow1;
    int rowLoop;
    int numTabRows;
    int tab_field;
    int url_field;
    int see_also_field;
    int termInset;
    int search_loop;
    int index;
    int terminc;
    int numterms;
    int done;
    int currentItem;
    int currentMatch;
    int scrollArrowx;
    int scrollDowny;
    int scrollUpy;
    int ap_width;
    int box_width;
    int search_field;
    int clear_button;
    int clear_button_state;
    int[] box_left;
    int[][] tabStrWidth;
    Image definitionImage;
    Image termImage;
    Image bigImage;
    Integer tabInteger;
    Graphics definitionBufG;
    Graphics termBufG;
    Graphics bigBufG;
    Polygon upperBody;
    Polygon lowerBody;
    Polygon[] upArrow;
    Polygon[] downArrow;
    Rectangle jumpButtonRect;
    Rectangle clearButton;
    Rectangle searchFieldRect;
    Rectangle[] clickTerms;
    Rectangle[] urlRects;
    Rectangle[] tabRects;
    String[] tabStr;
    String[] termDisplayStr;
    String[][] terms;
    String[][] definitions;
    String[] definitionWords;
    String clearButtonStr;
    String currentSearchStr;
    String data_sep;
    String newDefaultStr;
    String moreinfoLabelStr;
    String searchLabelStr;
    String newDefault;
    String newDefault2Str;
    String inputStr;
    String nextWord;
    String newStr;
    String newLine;
    String tempStr;
    URL[] externalURLs;
    URL productURL;
    
    public void init() {
        this.ap_height = this.size().height;
        this.ap_width = this.size().width;
        this.box_width = this.ap_width - 16 - 6 - 3;
        this.initGetParameters();
        this.initRectsAndRegions();
        this.bigImage = this.createImage(this.ap_width, this.ap_height);
        (this.bigBufG = this.bigImage.getGraphics()).setColor(this.bgColor);
        this.bigBufG.fillRect(0, 0, this.ap_width, this.ap_height);
        this.termImage = this.createImage(this.box_width, 100);
        (this.termBufG = this.termImage.getGraphics()).setColor(this.bgColor);
        this.termBufG.fillRect(0, 0, this.box_width, 100);
        this.definitionImage = this.createImage(this.def_box_width - 2 - 12, this.def_box_height - 2);
        this.definitionBufG = this.definitionImage.getGraphics();
        this.setTermArrows();
        this.drawTermsOffScreen();
        this.drawDefinition();
        this.drawApplet();
    }
    
    public void initGetParameters() {
        String[] parse = new String[20];
        try {
            if (this.getParameter("use-default-colors").trim().startsWith("no") || this.getParameter("use-default-colors").trim().startsWith("false")) {
                final int[] int1 = this.parseInt(this.getParameter("background-color"), " ");
                this.bgColor = new Color(int1[0], int1[1], int1[2]);
                final int[] int2 = this.parseInt(this.getParameter("body-color"), " ");
                this.bodyColor = new Color(int2[0], int2[1], int2[2]);
                final int[] int3 = this.parseInt(this.getParameter("border-color"), " ");
                this.borderColor = new Color(int3[0], int3[1], int3[2]);
                final int[] int4 = this.parseInt(this.getParameter("arrow-border-color"), " ");
                this.arrowBorderColor = new Color(int4[0], int4[1], int4[2]);
                final int[] int5 = this.parseInt(this.getParameter("arrow-active-color"), " ");
                this.arrowActiveColor = new Color(int5[0], int5[1], int5[2]);
                final int[] int6 = this.parseInt(this.getParameter("arrow-inactive-color"), " ");
                this.arrowInactiveColor = new Color(int6[0], int6[1], int6[2]);
                final int[] int7 = this.parseInt(this.getParameter("term-background-color"), " ");
                this.termBgColor = new Color(int7[0], int7[1], int7[2]);
                final int[] int8 = this.parseInt(this.getParameter("term-foreground-color"), " ");
                this.termFgColor = new Color(int8[0], int8[1], int8[2]);
                final int[] int9 = this.parseInt(this.getParameter("term-selected-text-color"), " ");
                this.termTextColor = new Color(int9[0], int9[1], int9[2]);
                final int[] int10 = this.parseInt(this.getParameter("search-text-color"), " ");
                this.searchTextColor = new Color(int10[0], int10[1], int10[2]);
                final int[] int11 = this.parseInt(this.getParameter("search-field-text-color"), " ");
                this.searchFieldTextColor = new Color(int11[0], int11[1], int11[2]);
                final int[] int12 = this.parseInt(this.getParameter("definition-text-color"), " ");
                this.defTextColor = new Color(int12[0], int12[1], int12[2]);
                final int[] int13 = this.parseInt(this.getParameter("definition-hyperlink-color"), " ");
                this.defHyperlinkColor = new Color(int13[0], int13[1], int13[2]);
                final int[] int14 = this.parseInt(this.getParameter("clear-button-color"), " ");
                this.clearButtonColor = new Color(int14[0], int14[1], int14[2]);
                final int[] int15 = this.parseInt(this.getParameter("clear-button-text-color"), " ");
                this.clearButtonTextColor = new Color(int15[0], int15[1], int15[2]);
                final int[] int16 = this.parseInt(this.getParameter("clear-button-disabled-text-color"), " ");
                this.clearButtonDisabledTextColor = new Color(int16[0], int16[1], int16[2]);
            }
            this.tabInteger = new Integer(this.getParameter("number-of-terms").trim());
            this.maxParameters = this.tabInteger;
            this.termDisplayStr = new String[this.maxParameters];
            this.tabInteger = new Integer(this.getParameter("max-indecies").trim());
            this.maxIndecies = this.tabInteger;
            this.terms = new String[this.maxParameters][this.maxIndecies];
            this.tabInteger = new Integer(this.getParameter("max-strings").trim());
            this.maxStrings = this.tabInteger;
            this.definitions = new String[this.maxParameters][this.maxStrings];
            this.display_fields = new boolean[this.maxIndecies];
            this.search_fields = new boolean[this.maxIndecies];
            this.tabInteger = new Integer(this.getParameter("max-urls").trim());
            this.maxUrls = this.tabInteger;
            this.urlRects = new Rectangle[this.maxUrls];
            this.externalURLs = new URL[this.maxUrls];
            this.regFont = this.plainFont;
            this.smallFont = this.plainFont;
            if (this.getParameter("term-text-bold").trim().startsWith("yes") || this.getParameter("term-text-bold").trim().startsWith("true")) {
                this.smallFont = this.boldFont;
            }
            if (this.getParameter("using-bottom-button").trim().startsWith("yes") || this.getParameter("using-bottom-button").trim().startsWith("true")) {
                this.bottom = true;
            }
            if (this.getParameter("using-tabs").trim().startsWith("yes") || this.getParameter("using-tabs").trim().startsWith("true")) {
                this.tabs = true;
            }
            if (this.getParameter("term-match-default-plus-space").trim().startsWith("yes") || this.getParameter("term-match-default-plus-space").trim().startsWith("true")) {
                this.newDefaultSpace = true;
            }
            this.data_sep = new String(this.getParameter("data-separator"));
            this.searchLabelStr = new String(this.getParameter("search-text"));
            this.newDefault = new String(this.getParameter("term-match-default-string"));
            parse = this.parse(this.getParameter("term-data-format"), this.data_sep);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.bottom) {
            this.extra = 24;
            try {
                final int[] int17 = this.parseInt(this.getParameter("bottom-button-color"), " ");
                this.bottomButtonColor = new Color(int17[0], int17[1], int17[2]);
                final int[] int18 = this.parseInt(this.getParameter("bottom-button-text-color"), " ");
                this.bottomButtonTextColor = new Color(int18[0], int18[1], int18[2]);
                final int[] int19 = this.parseInt(this.getParameter("bottom-text-color"), " ");
                this.bottomTextColor = new Color(int19[0], int19[1], int19[2]);
                this.moreinfoLabelStr = new String(this.getParameter("bottom-button-text"));
                this.bottom_display = new Integer(this.getParameter("bottom-text-from-term-field-number").trim());
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        this.termInset = 2;
        if (this.tabs) {
            try {
                this.tabInteger = new Integer(this.getParameter("num-tabs").trim());
                this.numTabs = this.tabInteger;
                this.tabColor = new Color[this.numTabs];
                this.tabTextColor = new Color[this.numTabs];
                this.tabRects = new Rectangle[this.numTabs];
                this.tabStr = new String[this.numTabs];
                this.tabStrWidth = new int[2][this.numTabs];
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            this.termInset = 8;
            this.done = 0;
            this.loop = -1;
            do {
                ++this.loop;
                try {
                    if (this.getParameter("tab-text-" + this.loop) == null) {
                        this.done = 1;
                    }
                    else {
                        this.tabStr[this.loop] = this.getParameter("tab-text-" + this.loop);
                        final int[] int20 = this.parseInt(this.getParameter("tab-color-" + this.loop), " ");
                        this.tabColor[this.loop] = new Color(int20[0], int20[1], int20[2]);
                        if (this.getParameter("tab-text-color-" + this.loop) != null) {
                            final int[] int21 = this.parseInt(this.getParameter("tab-text-color-" + this.loop), " ");
                            this.tabTextColor[this.loop] = new Color(int21[0], int21[1], int21[2]);
                        }
                        else {
                            this.tabTextColor[this.loop] = new Color(102, 51, 0);
                        }
                    }
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            } while (this.done == 0);
            this.numTabs = this.loop;
        }
        this.loop = 0;
        while (this.loop < parse.length) {
            this.search_fields[this.loop] = false;
            this.display_fields[this.loop] = false;
            if (parse[this.loop].startsWith("tab")) {
                this.tab_field = this.loop;
            }
            else if (parse[this.loop].startsWith("URL")) {
                this.url_field = this.loop;
            }
            else if (parse[this.loop].startsWith("see")) {
                this.see_also_field = this.loop;
            }
            else if (parse[this.loop].startsWith("dis")) {
                this.display_fields[this.loop] = true;
            }
            else if (parse[this.loop].startsWith("search&d")) {
                this.search_fields[this.loop] = true;
                this.display_fields[this.loop] = true;
            }
            else if (parse[this.loop].startsWith("search")) {
                this.search_fields[this.loop] = true;
            }
            ++this.loop;
        }
        this.done = 0;
        this.loop = -1;
        do {
            ++this.loop;
            try {
                if (this.getParameter("t" + this.loop) == null) {
                    this.done = 1;
                }
                else {
                    this.terms[this.loop] = this.parse(this.getParameter("t" + this.loop), this.data_sep);
                    this.termDisplayStr[this.loop] = new String("");
                    this.str_loop = 0;
                    while (this.str_loop < this.terms[this.loop].length) {
                        if (this.display_fields[this.str_loop]) {
                            this.tempStr = new String(this.termDisplayStr[this.loop] + " " + this.terms[this.loop][this.str_loop]);
                            this.termDisplayStr[this.loop] = this.tempStr;
                        }
                        ++this.str_loop;
                    }
                    this.definitions[this.loop] = this.parse(this.getParameter("d" + this.loop), this.data_sep);
                }
            }
            catch (Exception ex5) {
                ex5.printStackTrace();
            }
        } while (this.done == 0);
        this.numterms = this.loop;
    }
    
    public void initRectsAndRegions() {
        this.microHeight = Toolkit.getDefaultToolkit().getFontMetrics(this.microFont).getHeight() + 3;
        this.regHeight = Toolkit.getDefaultToolkit().getFontMetrics(this.regFont).getHeight() + 3;
        this.regAscent = Toolkit.getDefaultToolkit().getFontMetrics(this.regFont).getAscent();
        final int n = Toolkit.getDefaultToolkit().getFontMetrics(this.regFont).stringWidth("Clear") + 8;
        final int n2 = 80;
        this.clearButton = new Rectangle(this.ap_width - 1 - 16 - (this.ap_width - 1 - 16 - (Toolkit.getDefaultToolkit().getFontMetrics(this.regFont).stringWidth(this.searchLabelStr) + n + n2 + 12)) / 2 - n, 6, n, this.regHeight + 6);
        this.searchFieldRect = new Rectangle(this.clearButton.x - 6 - n2, 6, n2, this.regHeight + 6);
        this.clear_button = 2;
        this.box_top = this.clearButton.y + this.clearButton.height + this.microHeight + 5 + 6;
        if (this.tabs) {
            this.loop = 0;
            while (this.loop < this.numTabs) {
                this.tabStrWidth[0][this.loop] = Toolkit.getDefaultToolkit().getFontMetrics(this.microFont).stringWidth(this.tabStr[this.loop]) - 2;
                this.totalStringWidth += this.tabStrWidth[0][this.loop];
                ++this.loop;
            }
            this.box_left[0] = (this.ap_width - 1 - 17 - (this.totalStringWidth + this.numTabs * 12)) / 2;
            this.numTabRows = 1;
            this.rowTwoStartsAt = 0;
            if (this.box_left[0] < 10) {
                this.rowTwoStartsAt = this.numTabs / 2;
                this.numTabRows = 2;
                this.loop = 0;
                while (this.loop < this.rowTwoStartsAt) {
                    this.tabStrWidth[0][this.loop] = Toolkit.getDefaultToolkit().getFontMetrics(this.microFont).stringWidth(this.tabStr[this.loop]) - 2;
                    this.totalStringWidthRow1 += this.tabStrWidth[0][this.loop];
                    ++this.loop;
                }
                this.box_left[0] = (this.ap_width - 1 - 17 - (this.totalStringWidthRow1 + (this.rowTwoStartsAt - 1) * 12)) / 2;
                this.loop = this.rowTwoStartsAt;
                while (this.loop < this.numTabs) {
                    this.tabStrWidth[1][this.loop] = Toolkit.getDefaultToolkit().getFontMetrics(this.microFont).stringWidth(this.tabStr[this.loop]) - 2;
                    this.totalStringWidthRow2 += this.tabStrWidth[1][this.loop];
                    ++this.loop;
                }
                this.box_left[1] = (this.ap_width - 1 - 17 - (this.totalStringWidthRow2 + (this.numTabs - this.rowTwoStartsAt) * 12)) / 2;
            }
            if (this.numTabRows != 1) {
                this.box_top += 4 + this.microHeight + 8;
            }
        }
        else {
            this.box_top -= 4 + this.microHeight;
        }
        this.upy = this.box_top + 15;
        this.downy = this.box_top + 100 - 15;
        this.def_box_width = this.ap_width - 12 - 16 - 1;
        this.def_box_height = this.ap_height - (this.box_top + 100 + 12 + this.extra + 2);
        this.arrowx = this.ap_width - 15;
        (this.upArrow[0] = new Polygon()).addPoint(this.arrowx, this.upy);
        this.upArrow[0].addPoint(this.arrowx + 6, this.upy - 12);
        this.upArrow[0].addPoint(this.arrowx + 12, this.upy);
        this.upArrow[0].addPoint(this.arrowx + 6, this.upy + 6);
        this.upArrow[0].addPoint(this.arrowx, this.upy);
        (this.downArrow[0] = new Polygon()).addPoint(this.arrowx, this.downy);
        this.downArrow[0].addPoint(this.arrowx + 6, this.downy + 12);
        this.downArrow[0].addPoint(this.arrowx + 12, this.downy);
        this.downArrow[0].addPoint(this.arrowx + 6, this.downy - 6);
        this.downArrow[0].addPoint(this.arrowx, this.downy);
        this.loop = 0;
        while (this.loop < 5) {
            this.clickTerms[this.loop] = new Rectangle(5, this.box_top + 1 + 20 * this.loop, this.box_width, 19);
            ++this.loop;
        }
        this.scrollArrowx = this.ap_width - 35;
        this.scrollUpy = this.box_top + 101 + 6 + 15;
        this.scrollDowny = this.ap_height - 6 - this.extra - 15;
        (this.upArrow[1] = new Polygon()).addPoint(this.scrollArrowx, this.scrollUpy);
        this.upArrow[1].addPoint(this.scrollArrowx + 4, this.scrollUpy - 8);
        this.upArrow[1].addPoint(this.scrollArrowx + 8, this.scrollUpy);
        this.upArrow[1].addPoint(this.scrollArrowx + 4, this.scrollUpy + 4);
        this.upArrow[1].addPoint(this.scrollArrowx, this.scrollUpy);
        (this.downArrow[1] = new Polygon()).addPoint(this.scrollArrowx, this.scrollDowny);
        this.downArrow[1].addPoint(this.scrollArrowx + 4, this.scrollDowny + 8);
        this.downArrow[1].addPoint(this.scrollArrowx + 8, this.scrollDowny);
        this.downArrow[1].addPoint(this.scrollArrowx + 4, this.scrollDowny - 4);
        this.downArrow[1].addPoint(this.scrollArrowx, this.scrollDowny);
        this.maxRows = (this.def_box_height - 18) / (this.regHeight + 2);
        if (this.tabs) {
            this.rowLoop = 0;
            this.loop = 0;
            while (this.loop < this.numTabs) {
                if (this.rowTwoStartsAt != 0 && this.loop >= this.rowTwoStartsAt) {
                    this.rowLoop = 1;
                }
                if (this.rowTwoStartsAt != 0) {
                    this.tabRects[this.loop] = new Rectangle(this.box_left[this.rowLoop], this.box_top - (this.microHeight + 4) * (2 - this.rowLoop) - 3, this.tabStrWidth[this.rowLoop][this.loop] + 8, this.microHeight + 2);
                }
                else {
                    this.tabRects[this.loop] = new Rectangle(this.box_left[this.rowLoop], this.box_top - this.microHeight - 5, this.tabStrWidth[this.rowLoop][this.loop] + 8, this.microHeight + 2);
                }
                final int[] box_left = this.box_left;
                final int rowLoop = this.rowLoop;
                box_left[rowLoop] += this.tabStrWidth[this.rowLoop][this.loop] + 12;
                ++this.loop;
            }
        }
        this.upperBody.addPoint(0, 0);
        this.upperBody.addPoint(this.ap_width - 1 - 16, 0);
        this.upperBody.addPoint(this.ap_width - 1 - 16, 4);
        this.upperBody.addPoint(this.ap_width - 1 - 16, this.box_top - 1 - 4);
        this.upperBody.addPoint(this.ap_width - 1 - 16 - 4, this.box_top - 1);
        this.upperBody.addPoint(4, this.box_top - 1);
        this.upperBody.addPoint(0, this.box_top - 1 - 4);
        this.upperBody.addPoint(0, 0);
        this.upperBody.addPoint(0, 0);
        this.lowerBody.addPoint(4, this.box_top + 101);
        this.lowerBody.addPoint(this.ap_width - 1 - 16 - 4, this.box_top + 101);
        this.lowerBody.addPoint(this.ap_width - 1 - 16, this.box_top + 101 + 4);
        this.lowerBody.addPoint(this.ap_width - 1 - 16, this.ap_height - 1);
        this.lowerBody.addPoint(this.ap_width - 1 - 16, this.ap_height - 1);
        this.lowerBody.addPoint((this.ap_width - 1 - 16) / 2, this.ap_height - 1);
        this.lowerBody.addPoint((this.ap_width - 1 - 16) / 2, this.ap_height - 1 - 6 - this.extra);
        this.lowerBody.addPoint(this.ap_width - 1 - 16 - 6, this.ap_height - 1 - 6 - this.extra);
        this.lowerBody.addPoint(this.ap_width - 1 - 16 - 6, this.box_top + 101 + 6);
        this.lowerBody.addPoint(6, this.box_top + 101 + 6);
        this.lowerBody.addPoint(6, this.ap_height - 1 - 6 - this.extra);
        this.lowerBody.addPoint((this.ap_width - 1 - 16) / 2, this.ap_height - 1 - 6 - this.extra);
        this.lowerBody.addPoint((this.ap_width - 1 - 16) / 2, this.ap_height - 1);
        this.lowerBody.addPoint(0, this.ap_height - 1);
        this.lowerBody.addPoint(0, this.ap_height - 1);
        this.lowerBody.addPoint(0, this.box_top + 101 + 4);
        this.lowerBody.addPoint(4, this.box_top + 101);
        if (this.bottom) {
            this.jumpButtonRect = new Rectangle(6, this.ap_height - this.regHeight - 2 - 6, Toolkit.getDefaultToolkit().getFontMetrics(this.regFont).stringWidth(this.moreinfoLabelStr) + 8, this.regHeight + 2 + 2);
        }
    }
    
    public void drawTermsOffScreen() {
        this.termBufG.setFont(this.smallFont);
        this.terminc = -2;
        this.index = 0;
        do {
            this.index = this.currentItem + this.terminc;
            this.termBufG.setColor(this.termFgColor);
            this.termBufG.fillRect(0, (this.terminc + 2) * 20, this.box_width, 19);
            if (this.index >= 0 && this.index < this.numterms) {
                if (this.tabs) {
                    this.tabInteger = new Integer(this.terms[this.index][this.tab_field].trim());
                    this.tabInt = this.tabInteger;
                    this.termBufG.setColor(this.tabColor[this.tabInt]);
                    this.termBufG.fillRect(1, (this.terminc + 2) * 20 + 1, this.termInset, 17);
                    if (this.terminc != 0) {
                        this.termBufG.setColor(this.termBgColor);
                    }
                    else {
                        this.termBufG.setColor(this.tabColor[this.tabInt]);
                        this.termBufG.fillRect(1, (this.terminc + 2) * 20 + 1, this.box_width - 2, 17);
                        this.termBufG.setColor(this.tabTextColor[this.tabInt]);
                    }
                }
                else if (this.terminc == 0) {
                    this.termBufG.setColor(this.termBgColor);
                    this.termBufG.fillRect(0, (this.terminc + 2) * 20, this.box_width, 19);
                    this.termBufG.setColor(this.termTextColor);
                }
                else {
                    this.termBufG.setColor(this.termBgColor);
                }
                if (Toolkit.getDefaultToolkit().getFontMetrics(this.smallFont).stringWidth(this.termDisplayStr[this.index]) < this.box_width - this.termInset) {
                    this.termBufG.drawString(this.termDisplayStr[this.index], this.termInset + 2, 14 + (this.terminc + 2) * 20);
                }
                else {
                    this.termBufG.setFont(this.microFont);
                    this.termBufG.drawString(this.termDisplayStr[this.index], this.termInset + 2, 13 + (this.terminc + 2) * 20);
                    this.termBufG.setFont(this.smallFont);
                }
            }
            ++this.terminc;
        } while (this.terminc < 3);
    }
    
    public void drawTabs(final int n, final boolean b) {
        this.bigBufG.setColor(this.tabColor[n]);
        this.bigBufG.draw3DRect(this.tabRects[n].x - 1, this.tabRects[n].y - 1, this.tabRects[n].width + 1, this.tabRects[n].height + 1, b);
        this.bigBufG.draw3DRect(this.tabRects[n].x, this.tabRects[n].y, this.tabRects[n].width - 1, this.tabRects[n].height - 1, b);
        this.bigBufG.setColor(this.tabColor[n]);
        this.bigBufG.fillRect(this.tabRects[n].x + 1, this.tabRects[n].y + 1, this.tabRects[n].width - 2, this.tabRects[n].height - 2);
        this.bigBufG.setFont(this.microFont);
        this.bigBufG.setColor(this.tabTextColor[n]);
        this.bigBufG.drawString(this.tabStr[n], this.tabRects[n].x + 2, this.tabRects[n].y + this.microHeight - 3);
    }
    
    public void drawApplet() {
        this.bigBufG.setColor(this.bgColor);
        this.bigBufG.fillRect(0, 0, this.ap_width, this.ap_height);
        this.bigBufG.setColor(this.bodyColor);
        this.bigBufG.fillPolygon(this.upperBody);
        this.bigBufG.fillPolygon(this.lowerBody);
        this.bigBufG.setColor(this.borderColor);
        this.bigBufG.drawPolygon(this.upperBody);
        this.bigBufG.drawPolygon(this.lowerBody);
        this.bigBufG.setColor(this.bodyColor);
        this.bigBufG.drawLine(this.lowerBody.xpoints[11], this.lowerBody.ypoints[11] + 1, this.lowerBody.xpoints[12], this.lowerBody.ypoints[12] - 1);
        if (this.tabs) {
            this.bigBufG.setFont(this.microFont);
            this.loop = 0;
            while (this.loop < this.numTabs) {
                if (this.loop == this.currentTab) {
                    this.drawTabs(this.currentTab, false);
                }
                else {
                    this.drawTabs(this.loop, true);
                }
                ++this.loop;
            }
        }
        this.updateArrows(0);
        this.bigBufG.setColor(this.arrowBorderColor);
        this.bigBufG.drawLine(this.arrowx, this.upy + 3, this.arrowx + 6, this.upy + 3 + 6);
        this.bigBufG.drawLine(this.arrowx + 6, this.upy + 3 + 6, this.arrowx + 12, this.upy + 3);
        this.bigBufG.drawLine(this.arrowx + 6, this.upy + 3 + 6, this.arrowx + 6, this.downy - 3 - 6);
        this.bigBufG.drawLine(this.arrowx, this.downy - 3, this.arrowx + 6, this.downy - 3 - 6);
        this.bigBufG.drawLine(this.arrowx + 6, this.downy - 3 - 6, this.arrowx + 12, this.downy - 3);
        this.bigBufG.drawLine(4 + this.box_width, this.box_top + 50, this.arrowx + 6, this.box_top + 50);
        this.updateArrows(1);
        this.bigBufG.setColor(this.arrowBorderColor);
        this.bigBufG.drawLine(this.scrollArrowx + 4, this.scrollUpy + 4, this.scrollArrowx + 4, this.scrollDowny - 4);
        this.bigBufG.drawImage(this.termImage, 5, this.box_top + 1, this);
        this.bigBufG.drawImage(this.definitionImage, 7, this.box_top + 101 + 1 + 6, this);
        this.bigBufG.setColor(this.searchTextColor);
        this.bigBufG.setFont(this.regFont);
        this.bigBufG.drawString(this.searchLabelStr, this.searchFieldRect.x - this.getFontMetrics(this.regFont).stringWidth(this.searchLabelStr) - 6, 6 + this.regHeight);
        this.bigBufG.setColor(this.bodyColor);
        this.bigBufG.draw3DRect(this.searchFieldRect.x, this.searchFieldRect.y, this.searchFieldRect.width - 1, this.searchFieldRect.height - 1, false);
        this.bigBufG.fill3DRect(this.searchFieldRect.x + 1, this.searchFieldRect.y + 1, this.searchFieldRect.width - 2, this.searchFieldRect.height - 2, false);
        this.bigBufG.setColor(this.clearButtonColor);
        this.bigBufG.draw3DRect(this.clearButton.x, this.clearButton.y, this.clearButton.width - 1, this.clearButton.height - 1, true);
        this.bigBufG.fill3DRect(this.clearButton.x + 1, this.clearButton.y + 1, this.clearButton.width - 2, this.clearButton.height - 2, true);
        if (this.bottom) {
            this.bigBufG.setColor(this.bottomButtonColor);
            this.bigBufG.draw3DRect(this.jumpButtonRect.x, this.jumpButtonRect.y, this.jumpButtonRect.width - 1, this.jumpButtonRect.height - 1, true);
            this.bigBufG.fill3DRect(this.jumpButtonRect.x + 1, this.jumpButtonRect.y + 1, this.jumpButtonRect.width - 2, this.jumpButtonRect.height - 2, true);
            this.bigBufG.setColor(this.bottomButtonTextColor);
            this.bigBufG.drawString(this.moreinfoLabelStr, this.jumpButtonRect.x + 4, this.ap_height - 6 - 4);
            this.drawProductNameString(false);
        }
    }
    
    public void drawProductNameString(final boolean b) {
        if (b) {
            this.bigBufG.setColor(this.bodyColor);
            this.bigBufG.fillRect(this.jumpButtonRect.width + 6 + 6, this.ap_height - 6 - 2 - this.regHeight, this.ap_width - 16 - 2 - (this.jumpButtonRect.x + this.jumpButtonRect.width + 6 + 6), 6 + this.regHeight);
        }
        this.tempStr = new String(this.terms[this.currentItem][this.bottom_display].replace('-', ' '));
        if (this.tempStr.length() > 32) {
            this.tempStr = this.tempStr.substring(0, 32) + "...";
        }
        this.bigBufG.setFont(this.regFont);
        this.bigBufG.setColor(this.bottomTextColor);
        this.bigBufG.drawString(this.tempStr, this.jumpButtonRect.width + 6 + 6, this.ap_height - 6 - 4);
    }
    
    public void updateArrows(final int n) {
        if (this.highlightUp[n]) {
            this.bigBufG.setColor(this.arrowActiveColor);
        }
        else {
            this.bigBufG.setColor(this.arrowInactiveColor);
        }
        this.bigBufG.fillPolygon(this.upArrow[n]);
        if (this.highlightDown[n]) {
            this.bigBufG.setColor(this.arrowActiveColor);
        }
        else {
            this.bigBufG.setColor(this.arrowInactiveColor);
        }
        this.bigBufG.fillPolygon(this.downArrow[n]);
        this.bigBufG.setColor(this.arrowBorderColor);
        this.bigBufG.drawPolygon(this.upArrow[n]);
        this.bigBufG.drawPolygon(this.downArrow[n]);
    }
    
    public void setTermArrows() {
        this.highlightUp[0] = true;
        this.highlightDown[0] = true;
        if (this.currentItem == 0) {
            this.highlightUp[0] = false;
            return;
        }
        if (this.currentItem == this.numterms - 1) {
            this.highlightDown[0] = false;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.user_input == 1) {
            this.drawTermsOffScreen();
            this.bigBufG.drawImage(this.termImage, 5, this.box_top + 1, this);
            this.drawDefinition();
            this.bigBufG.drawImage(this.definitionImage, 7, this.box_top + 101 + 1 + 6, this);
            if (this.tabs) {
                this.tabInteger = new Integer(this.terms[this.currentItem][this.tab_field].trim());
                if (this.currentTab != this.tabInteger) {
                    this.drawTabs(this.currentTab, true);
                    this.drawTabs(this.tabInteger, false);
                    this.currentTab = this.tabInteger;
                }
            }
            this.updateArrows(0);
            if (this.bottom) {
                this.drawProductNameString(true);
            }
            this.user_input = 0;
        }
        else if (this.user_input == 2) {
            this.drawDefinition();
            this.bigBufG.drawImage(this.definitionImage, 7, this.box_top + 101 + 1 + 6, this);
            this.user_input = 0;
        }
        else if (!this.clear_hit) {
            this.searchFieldUpdate(this.currentSearchStr);
            if (this.clear_button_state == 1) {
                this.clearButtonUpdate("Enable");
            }
            else if (this.clear_button_state == 2) {
                this.clearButtonUpdate("Disable");
            }
        }
        if (this.clear_button == 1) {
            this.clearButtonUpdate("Next");
        }
        else if (this.clear_button == 2) {
            this.clearButtonUpdate("Clear");
        }
        if (this.clear_button_state == 1) {
            this.clearButtonUpdate("Enable");
        }
        else if (this.clear_button_state == 2) {
            this.clearButtonUpdate("Disable");
        }
        if (this.search_field == 1) {
            this.searchFieldUpdate(this.currentSearchStr);
        }
        graphics.drawImage(this.bigImage, 0, 0, this);
        this.search_field = 0;
        this.clear_button = 0;
        this.clear_hit = false;
        this.requestFocus();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawDefinition() {
        this.definitionBufG.setColor(this.bgColor);
        this.definitionBufG.fillRect(0, 0, this.def_box_width - 2 - 12, this.def_box_height - 2);
        this.numUrls = 0;
        this.definitionBufG.setColor(this.defTextColor);
        this.definitionBufG.setFont(this.regFont);
        this.lineNumber = 0;
        this.loop = Math.abs(this.firstLine);
        this.left_edge = 0;
        this.index = this.currentItem;
        while (this.loop < this.definitions[this.index].length) {
            if (this.definitions[this.index][this.loop].toLowerCase().startsWith("mailt") || this.definitions[this.index][this.loop].toLowerCase().startsWith("ht") || this.definitions[this.index][this.loop].toLowerCase().startsWith("/")) {
                if (this.definitions[this.index][this.loop].toLowerCase().startsWith("/")) {
                    try {
                        this.externalURLs[this.numUrls] = new URL("http", this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), this.definitions[this.index][this.loop].trim());
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    try {
                        this.externalURLs[this.numUrls] = new URL(this.definitions[this.index][this.loop].trim());
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                ++this.loop;
                --this.lineNumber;
                this.definitionBufG.setColor(this.defHyperlinkColor);
                this.strLength = this.definitionBufG.getFontMetrics(this.regFont).stringWidth(this.definitions[this.index][this.loop].trim());
                if (this.strLength + this.left_edge + 6 + 5 > this.def_box_width - 12 - 5) {
                    ++this.lineNumber;
                    this.left_edge = 0;
                }
                this.stry = 7 + this.regAscent + this.lineNumber * this.regHeight;
                if (this.lineNumber <= this.maxRows) {
                    this.definitionBufG.drawString(this.definitions[this.index][this.loop].trim(), 5 + this.left_edge, this.stry);
                    this.definitionBufG.drawLine(5 + this.left_edge, this.stry, 5 + this.left_edge + this.strLength, this.stry);
                }
                this.urlRects[this.numUrls] = new Rectangle(11 + this.left_edge, 14 + this.stry + this.box_top + 100 - this.regHeight, this.strLength, this.regHeight);
                this.definitionBufG.setColor(this.defTextColor);
                ++this.lineNumber;
                ++this.numUrls;
            }
            else {
                this.definitionWords = this.parse(this.definitions[this.index][this.loop], " ");
                this.wordLoop = 0;
                this.allLinesFit = true;
                while (this.wordLoop < this.definitionWords.length && this.allLinesFit) {
                    this.newLine = new String("");
                    this.tooLong = false;
                    while (!this.tooLong && this.wordLoop < this.definitionWords.length) {
                        this.nextWord = new String(this.definitionWords[this.wordLoop]);
                        if (this.definitionBufG.getFontMetrics(this.regFont).stringWidth(this.newLine + " " + this.nextWord) > this.def_box_width - 12 - 5) {
                            this.tooLong = true;
                        }
                        else {
                            this.tempStr = new String(this.newLine + " " + this.nextWord);
                            this.newLine = this.tempStr;
                            ++this.wordLoop;
                        }
                    }
                    if (this.lineNumber <= this.maxRows) {
                        this.definitionBufG.drawString(this.newLine.trim(), 5, 7 + this.regAscent + this.lineNumber * this.regHeight);
                    }
                    else {
                        this.allLinesFit = false;
                    }
                    ++this.lineNumber;
                    this.left_edge = this.definitionBufG.getFontMetrics(this.regFont).stringWidth(this.newLine) + 3;
                }
            }
            ++this.loop;
        }
        this.lastLine = this.lineNumber;
        if (!this.allLinesFit) {
            this.highlightDown[1] = true;
        }
        else {
            this.highlightDown[1] = false;
        }
        if (this.firstLine < 0) {
            this.highlightUp[1] = true;
        }
        else {
            this.highlightUp[1] = false;
        }
        this.updateArrows(1);
    }
    
    public void jumpToRegion(final int n) {
        this.loop = 0;
        this.done = 0;
        while (this.done == 0 && this.loop < this.numterms) {
            this.tabInteger = new Integer(this.terms[this.loop][this.tab_field].trim());
            if (this.tabInteger == n) {
                this.currentItem = this.loop;
                this.setTermArrows();
                this.user_input = 1;
                this.firstLine = 0;
                this.paint(this.getGraphics());
                this.done = 1;
            }
            ++this.loop;
        }
    }
    
    public void searchFieldUpdate(final String tempStr) {
        this.bigBufG.setColor(this.bgColor);
        this.bigBufG.fillRect(this.searchFieldRect.x + 2, this.searchFieldRect.y + 2, this.searchFieldRect.width - 4, this.searchFieldRect.height - 4);
        this.bigBufG.setColor(this.searchFieldTextColor);
        this.bigBufG.setFont(this.regFont);
        this.left_edge = this.searchFieldRect.x + 4 + 2 + this.bigBufG.getFontMetrics(this.regFont).stringWidth(tempStr);
        if (tempStr.length() > 0) {
            if (this.left_edge < this.searchFieldRect.x + this.searchFieldRect.width - 5) {
                this.bigBufG.drawString(tempStr, this.searchFieldRect.x + 4, this.searchFieldRect.y + this.regHeight - 2);
            }
            else {
                this.tempStr = tempStr;
                while (this.left_edge >= this.searchFieldRect.x + this.searchFieldRect.width - 5) {
                    this.tempStr = this.tempStr.substring(1);
                    this.left_edge = this.searchFieldRect.x + 4 + 2 + this.bigBufG.getFontMetrics(this.regFont).stringWidth(this.tempStr);
                }
                this.bigBufG.drawString(this.tempStr, this.searchFieldRect.x + 4, this.searchFieldRect.y + this.regHeight - 2);
            }
        }
        this.loop = -2;
        while (this.loop < 3) {
            this.bigBufG.drawLine(this.left_edge, this.searchFieldRect.y + this.searchFieldRect.height - 8, this.left_edge + this.loop, this.searchFieldRect.y + this.searchFieldRect.height - 8 + 4);
            ++this.loop;
        }
    }
    
    public void clearButtonUpdate(final String clearButtonStr) {
        this.bigBufG.setColor(this.bodyColor);
        this.bigBufG.fillRect(this.clearButton.x + 2, this.clearButton.y + 2, this.clearButton.width - 4, this.clearButton.height - 4);
        if (clearButtonStr.startsWith("E")) {
            this.bigBufG.setColor(this.clearButtonTextColor);
        }
        else if (clearButtonStr.startsWith("D")) {
            this.bigBufG.setColor(this.clearButtonDisabledTextColor);
        }
        else {
            this.clearButtonStr = clearButtonStr;
            this.bigBufG.setColor(this.clearButtonTextColor);
        }
        this.bigBufG.setFont(this.regFont);
        this.bigBufG.drawString(this.clearButtonStr, this.clearButton.x + 4, this.clearButton.y + this.regHeight);
    }
    
    public void stringMatch(final char c) {
        if (this.currentMatch != 0) {
            this.terminc = this.currentMatch;
            this.firstLine = 0;
        }
        if (c != '\uffff') {
            this.newStr = this.currentSearchStr;
            this.newDefaultStr = this.newDefault + this.newStr;
            if (this.newDefaultSpace) {
                this.newDefault2Str = this.newDefault + " " + this.newStr;
            }
            this.terminc = 0;
        }
        this.done = 0;
        while (this.terminc < this.numterms && this.done == 0) {
            this.loop = 0;
            while (this.loop < this.terms[this.terminc].length && this.done == 0) {
                if (this.search_fields[this.loop] && (this.terms[this.terminc][this.loop].toLowerCase().startsWith(this.newStr) || this.terms[this.terminc][this.loop].toLowerCase().startsWith(this.newDefaultStr) || (this.newDefaultSpace && this.terms[this.terminc][this.loop].toLowerCase().startsWith(this.newDefault2Str)))) {
                    this.done = 1;
                    this.currentItem = this.terminc;
                    this.currentMatch = this.terminc + 1;
                    this.clear_button = 1;
                }
                ++this.loop;
            }
            ++this.terminc;
        }
        if (this.terminc == this.numterms) {
            this.currentMatch = 0;
            this.clear_button = 2;
        }
        this.setTermArrows();
        this.user_input = 1;
        this.paint(this.getGraphics());
    }
    
    public boolean keyDown(final Event event, final int n) {
        if ((Character.isLetterOrDigit((char)n) || (char)n == ' ') && n != 1005 && n != 1004 && n != 8 && n != 127 && n != 1006 && n != 1007) {
            this.newStr = this.currentSearchStr + String.valueOf((char)n).toLowerCase();
            this.currentSearchStr = this.newStr;
            this.search_field = 1;
            this.clear_button_state = 1;
            this.stringMatch((char)n);
        }
        else if (n == 1005 && this.currentItem <= this.numterms - 2) {
            ++this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.paint(this.getGraphics());
        }
        else if (n == 1004 && this.currentItem >= 1) {
            --this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.paint(this.getGraphics());
        }
        else if ((n == 127 || n == 8) && this.currentSearchStr.length() > 0) {
            if (this.currentSearchStr.length() > 1) {
                this.newStr = this.currentSearchStr.substring(0, this.currentSearchStr.length() - 1);
                this.currentSearchStr = this.newStr;
                this.search_field = 1;
                this.clear_button_state = 1;
                this.stringMatch((char)n);
            }
            else {
                this.currentSearchStr = "";
                this.clear_button_state = 2;
                this.clear_button = 2;
                this.paint(this.getGraphics());
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.upArrow[0].inside(n, n2) && this.currentItem >= 1) {
            --this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.downArrow[0].inside(n, n2) && this.currentItem <= this.numterms - 2) {
            ++this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.clearButton.inside(n, n2)) {
            if (this.currentMatch == 0) {
                this.currentSearchStr = "";
                this.search_field = 1;
                this.clear_button_state = 2;
                this.clear_hit = true;
                this.paint(this.getGraphics());
            }
            else if (this.currentSearchStr != "") {
                this.stringMatch('\uffff');
            }
        }
        else if (this.clickTerms[0].inside(n, n2) && this.currentItem >= 2) {
            this.currentItem -= 2;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.clickTerms[1].inside(n, n2) && this.currentItem >= 1) {
            --this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.clickTerms[3].inside(n, n2) && this.currentItem <= this.numterms - 2) {
            ++this.currentItem;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.clickTerms[4].inside(n, n2) && this.currentItem <= this.numterms - 3) {
            this.currentItem += 2;
            this.setTermArrows();
            this.user_input = 1;
            this.firstLine = 0;
            this.paint(this.getGraphics());
        }
        else if (this.downArrow[1].inside(n, n2) && !this.allLinesFit) {
            --this.firstLine;
            this.user_input = 2;
            this.paint(this.getGraphics());
        }
        else if (this.upArrow[1].inside(n, n2) && this.firstLine < 0) {
            ++this.firstLine;
            this.user_input = 2;
            this.paint(this.getGraphics());
        }
        else if (this.bottom && this.jumpButtonRect.inside(n, n2)) {
            try {
                if (this.terms[this.currentItem][this.url_field].trim().startsWith("ht")) {
                    this.productURL = new URL(this.terms[this.currentItem][this.url_field].trim());
                }
                else {
                    this.productURL = new URL("http", this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), this.terms[this.currentItem][this.url_field].trim());
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.getAppletContext().showDocument(this.productURL);
        }
        else if (n2 > this.box_top + 101 + 1 + 6 && this.numUrls > 0) {
            this.urlLoop = 0;
            while (this.urlLoop < this.numUrls) {
                if (this.urlRects[this.urlLoop].inside(n, n2)) {
                    this.getAppletContext().showDocument(this.externalURLs[this.urlLoop]);
                }
                ++this.urlLoop;
            }
        }
        else if (this.tabs && n2 >= this.tabRects[0].y && n2 <= this.tabRects[this.numTabs - 1].y + this.tabRects[this.numTabs - 1].height) {
            this.tabLoop = 0;
            this.done = 0;
            while (this.tabLoop < this.numTabs && this.done == 0) {
                if (this.tabRects[this.tabLoop].inside(n, n2)) {
                    this.jumpToRegion(this.tabLoop);
                    this.done = 1;
                }
                ++this.tabLoop;
            }
        }
        return true;
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public String getAppletInfo() {
        return "Title: Glossary Applet\nAuthor: John Hoffmann, 1997-98 \nGlossary featuring text search";
    }
    
    public glossary() {
        this.tabs = false;
        this.highlightDown = new boolean[2];
        this.highlightUp = new boolean[2];
        this.newDefaultSpace = false;
        this.bgColor = Color.white;
        this.bodyColor = new Color(255, 204, 153);
        this.termTextColor = new Color(102, 51, 0);
        this.defTextColor = new Color(102, 51, 0);
        this.defHyperlinkColor = new Color(0, 0, 255);
        this.bottomButtonTextColor = new Color(102, 51, 0);
        this.clearButtonTextColor = new Color(102, 51, 0);
        this.clearButtonDisabledTextColor = new Color(204, 153, 153);
        this.searchTextColor = new Color(102, 51, 0);
        this.searchFieldTextColor = new Color(102, 51, 0);
        this.bottomTextColor = new Color(102, 51, 0);
        this.bottomButtonColor = new Color(255, 204, 153);
        this.clearButtonColor = new Color(255, 204, 153);
        this.borderColor = new Color(102, 51, 0);
        this.arrowBorderColor = new Color(102, 51, 0);
        this.arrowActiveColor = new Color(255, 51, 0);
        this.arrowInactiveColor = new Color(153, 0, 0);
        this.termBgColor = new Color(204, 204, 255);
        this.termFgColor = new Color(153, 153, 255);
        this.microFont = new Font("Dialog", 0, 10);
        this.plainFont = new Font("Dialog", 0, 12);
        this.boldFont = new Font("Dialog", 1, 12);
        this.maxParameters = 100;
        this.maxIndecies = 10;
        this.maxStrings = 10;
        this.maxUrls = 10;
        this.numTabs = 10;
        this.search_field = 1;
        this.clear_button = 2;
        this.clear_button_state = 2;
        this.box_left = new int[2];
        this.upperBody = new Polygon();
        this.lowerBody = new Polygon();
        this.upArrow = new Polygon[2];
        this.downArrow = new Polygon[2];
        this.clickTerms = new Rectangle[5];
        this.clearButtonStr = new String("Clear");
        this.currentSearchStr = new String("");
        this.newDefaultStr = new String("");
        this.searchLabelStr = new String("");
        this.newDefault2Str = new String("");
        this.tempStr = new String("");
    }
}
