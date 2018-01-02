// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.Shape;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import KJEgraph.AutoColor;
import java.awt.Choice;
import java.awt.Container;
import KJEcalculation.CalculationException;
import KJEgraph.Graph;
import java.awt.Event;
import java.awt.Font;
import KJEcalculation.Calculation;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

public abstract class CalculatorApplet extends Applet
{
    public String sLANGUAGE;
    public String _COLON;
    private String sCP;
    private String sCS;
    private String sPP;
    private String sPS;
    private String sNP;
    private String sNS;
    private String YES;
    private String NO;
    private String IEM;
    private String RLE;
    private String RHE;
    public String[] sReportCols;
    public String sDecimals;
    public static final int ALL_OTHERS = 0;
    public static final int MAC_IE = 1;
    public static final int MAC_SAFARI = 2;
    public static final int PC_MSJAVA = 3;
    public int iBrowser;
    private double dRORMax;
    private boolean bRORMax;
    private String M;
    public Image _imageBackground;
    public Image iL;
    private MediaTracker MT;
    public Color _cError;
    public Color _cPageBackground;
    public Color cTitle;
    private Color[] _cColor;
    private Color[] _cGColor;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    private Component[] order;
    private int count;
    public String sShowReportButton;
    public int nStack;
    public boolean bUseNorth;
    public boolean bUseSouth;
    public boolean bUseWest;
    public boolean bUseEast;
    public int GRID_GAP_HOR;
    public int GRID_GAP_VERT;
    public Panel pTitle;
    private BorderSide[] border;
    public Calculation CC;
    public String sTitle;
    public boolean bLogo;
    private Font _f1;
    private Font _f;
    private Font _ff;
    private Font _fff;
    private Font _fg;
    
    public CalculatorApplet() {
        this.sLANGUAGE = "ENGLISH";
        this._COLON = ":";
        this.sCP = "$";
        this.sCS = "";
        this.sPP = "";
        this.sPS = "%";
        this.sNP = "";
        this.sNS = "";
        this.YES = "yes";
        this.NO = "no";
        this.IEM = "Please enter a valid ";
        this.RLE = " must be greater than or equal to ";
        this.RHE = " must be less than or equal to ";
        this.sReportCols = new String[15];
        this.sDecimals = "FALSE";
        this.iBrowser = 0;
        this.dRORMax = 12.0;
        this.bRORMax = false;
        this.M = null;
        this._cError = Color.red;
        this._cColor = new Color[10];
        this._cGColor = new Color[10];
        this.order = new Component[15];
        this.count = 0;
        this.sShowReportButton = "AUTO";
        this.nStack = 1;
        this.bUseNorth = true;
        this.bUseSouth = false;
        this.bUseWest = false;
        this.bUseEast = false;
        this.GRID_GAP_HOR = 5;
        this.GRID_GAP_VERT = 10;
        this.border = new BorderSide[3];
        this.sTitle = "";
        this.bLogo = false;
    }
    
    public boolean action(final Event event, final Object o) {
        this.calculate();
        return super.action(event, o);
    }
    
    public void addComponent(final Component component) {
        if (component != null) {
            this.order[this.count++] = component;
        }
    }
    
    public void addDataPanel(final DataPanel dataPanel) {
        if (dataPanel != null) {
            this.order[this.count++] = dataPanel;
        }
    }
    
    public void addGraph(final Graph graph) {
        if (graph != null) {
            this.order[this.count++] = graph;
        }
    }
    
    public void addPanel(final Panel panel) {
        if (panel != null) {
            this.order[this.count++] = panel;
        }
    }
    
    public void calculate() {
        this.setLanguage();
        this.CC.clearInputed();
        try {
            this.setValues();
        }
        catch (NumberFormatException ex) {
            this.setError(ex.getMessage());
            return;
        }
        this.disable();
        try {
            this.CC.recalculate();
        }
        catch (CalculationException ex2) {
            this.setError(ex2.getMessage());
            this.enable();
            return;
        }
        this.clearError();
        this.refresh();
        this.enable();
    }
    
    public void clear() {
    }
    
    public void clearError() {
    }
    
    public void clearTitle() {
        if (this.pTitle instanceof BorderSide) {
            ((BorderSide)this.pTitle).setTitle("", this.getBackground());
        }
        this.sTitle = "";
    }
    
    public void destroy() {
        if (System.getProperty("java.version").equals("1.1.5")) {
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void disable() {
    }
    
    public void enable() {
    }
    
    public boolean findNbr(final Component[] array) {
        boolean nbr = true;
        for (int n = 0; n < array.length && nbr; ++n) {
            if (array[n] instanceof Nbr) {
                ((Nbr)array[n]).requestFocus();
                ((Nbr)array[n]).select(0, 0);
                nbr = false;
            }
            else if (array[n] instanceof Container) {
                nbr = this.findNbr(((Container)array[n]).getComponents());
            }
        }
        return nbr;
    }
    
    public Font getBoldFont() {
        return this._ff;
    }
    
    public Color getColor(int n) {
        if (n > 10) {
            n %= 10;
            ++n;
        }
        return this._cColor[--n];
    }
    
    public Color getGraphColor(int n) {
        if (n > 10) {
            n %= 10;
            this._cGColor[n].darker();
        }
        return this._cGColor[--n];
    }
    
    public Color[] getGraphColors() {
        return this._cGColor;
    }
    
    public Font getGraphTitleFont() {
        return this._fg;
    }
    
    public int getMortgageTerm(final Choice choice) {
        int int1;
        try {
            int1 = Integer.parseInt(choice.getSelectedItem().substring(0, 2).trim());
        }
        catch (NumberFormatException ex) {
            int1 = 15;
        }
        return int1;
    }
    
    public Choice getMortgageTermChoice(final int n) {
        return this.getTermChoice(n, this.getParameter("TERM_SHOW_ALL", false), this.getParameter("TERM_MAXIMUM", 30), this.getParameter("SHOW_ALL_MAXIMUM", this.getParameter("TERM_MAXIMUM", 30)), "");
    }
    
    public double getParameter(final String s, final double n) {
        double double1 = n;
        if (this.getParameter(s) != null) {
            try {
                double1 = Nbr.toDouble(this.getParameter(s));
            }
            catch (NumberFormatException ex) {
                double1 = n;
            }
        }
        return double1;
    }
    
    public int getParameter(final String s, final int n) {
        int int1 = n;
        if (this.getParameter(s) != null) {
            try {
                int1 = Integer.parseInt(this.getParameter(s));
            }
            catch (NumberFormatException ex) {
                int1 = n;
            }
        }
        return int1;
    }
    
    public String getParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String replace;
        if (parameter == null) {
            replace = s2;
        }
        else {
            replace = Calculation.replace("\\'i", "\u00ed", Calculation.replace("\\'u", "\u00fa", Calculation.replace("\\'o", "\u00f3", Calculation.replace("\\?", "¿", Calculation.replace("\\~n", "\u00f1", Calculation.replace("\\'e", "\u00e9", parameter))))));
        }
        return replace;
    }
    
    public boolean getParameter(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        boolean b2 = b;
        if (parameter != null) {
            final String upperCase = parameter.trim().toUpperCase();
            if (upperCase.equals("FALSE") && b) {
                b2 = false;
            }
            else if (upperCase.equals("TRUE") && !b) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public Font getParameterFont(final String s, final String s2, final int n, final int n2) {
        String parameter = this.getParameter("FONT_" + s, s2);
        final String parameter2 = this.getParameter("FONTSTYLE_" + s, "");
        final String parameter3 = this.getParameter("FONTSIZE_" + s, Fmt.number(n2));
        int n3 = n;
        if (parameter == null) {
            parameter = s2;
        }
        if (parameter2.equals("BOLD")) {
            n3 = 1;
        }
        else if (parameter2.equals("ITALIC")) {
            n3 = 2;
        }
        else if (parameter2.equals("BOLDITALIC")) {
            n3 = 3;
        }
        int int1;
        try {
            int1 = Integer.parseInt(parameter3);
        }
        catch (NumberFormatException ex) {
            int1 = n2;
        }
        return new Font(parameter, n3, int1);
    }
    
    public Font getPlainFont() {
        return this._f;
    }
    
    public double getRORMaximum(final double n) {
        return this.bRORMax ? this.dRORMax : n;
    }
    
    public Choice getTermChoice(final int n, final boolean b, final int n2, final int n3, final String s) {
        final KJEChoice kjeChoice = new KJEChoice();
        String s2 = "";
        final int parameter = this.getParameter("TERM_SHOW_ADDITIONAL", 12);
        for (int i = n2; i >= 1; --i) {
            if (((b && i <= n3) || i % 5 == 0 || i == parameter) && (parameter != 0 || (i != 5 && i != 35))) {
                final String string = String.valueOf(i) + " " + ((i == 1) ? this.CC.MSG_YEAR_LBL : this.CC.MSG_YEARS_LBL) + ((i > n3) ? s : "");
                kjeChoice.addItem(string);
                if (i == n) {
                    s2 = string;
                }
            }
        }
        kjeChoice.select(s2);
        return kjeChoice;
    }
    
    public Font getTinyFont() {
        return this._f1;
    }
    
    public Font getTitleFont() {
        return this._fff;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401 && (event.key == 9 || event.key == 10) && event.target instanceof Nbr && ((Nbr)event.target).bManualRecalc()) {
            this.calculate();
        }
        return super.handleEvent(event);
    }
    
    public void hideCalculatorWindows() {
    }
    
    public Image imageBackground() {
        return this._imageBackground;
    }
    
    public void init() {
        if (this.getAppletContext().getClass().getName().indexOf("apple.mrj") != -1) {
            this.iBrowser = 1;
        }
        else if (this.getAppletContext().getClass().getName().indexOf("apple.applet") != -1) {
            this.iBrowser = 2;
        }
        else if (System.getProperty("java.vendor").toLowerCase().startsWith("microsoft")) {
            this.iBrowser = 3;
        }
        else {
            this.iBrowser = 0;
        }
        final String upperCase = this.getParameter("SHOW_REPORT_BUTTON", "AUTO").toUpperCase();
        this.sDecimals = this.getParameter("EUROPE_DECIMALS", "FALSE");
        this.sCP = this.getParameter("CURRENCY_PREFIX", this.sCP);
        this.sPP = this.getParameter("PRECENT_PREFIX", this.sPP);
        this.sNP = this.getParameter("NUMBER_PREFIX", this.sNP);
        if (this.getParameter("COLON_SUFFIX") != null) {
            this._COLON = " " + this.getParameter("COLON_SUFFIX");
        }
        if (this.getParameter("CURRENCY_SUFFIX") != null) {
            this.sCS = " " + this.getParameter("CURRENCY_SUFFIX");
        }
        if (this.getParameter("PRECENT_SUFFIX") != null) {
            this.sPS = " " + this.getParameter("PRECENT_SUFFIX");
        }
        if (this.getParameter("NUMBER_SUFFIX") != null) {
            this.sNS = " " + this.getParameter("NUMBER_SUFFIX");
        }
        if (this.getParameter("MSG_YES") != null) {
            this.YES = " " + this.getParameter("MSG_YES") + " ";
        }
        if (this.getParameter("MSG_NO") != null) {
            this.NO = " " + this.getParameter("MSG_NO") + " ";
        }
        if (this.getParameter("INPUT_ERROR_MSG") != null) {
            this.IEM = String.valueOf(this.getParameter("INPUT_ERROR_MSG")) + " ";
        }
        if (this.getParameter("RANGE_LOW_ERROR_MSG") != null) {
            this.RLE = " " + this.getParameter("RANGE_LOW_ERROR_MSG") + " ";
        }
        if (this.getParameter("RANGE_HIGH_ERROR_MSG") != null) {
            this.RHE = " " + this.getParameter("RANGE_HIGH_ERROR_MSG") + " ";
        }
        this.setLanguage();
        this.MT = new MediaTracker(this);
        this._imageBackground = this.loadImage(this.MT, "BACKGROUND", 1);
        if (this.getParameter("LOGO") != null) {
            this.iL = this.loadImage(this.MT, "LOGO", 2);
        }
        this.M = this.getParameter("MSG_TITLE_LBL", null);
        this._fff = this.getParameterFont("TITLE", "Helvetica", 1, 17);
        this._ff = this.getParameterFont("BOLD", "Helvetica", 1, 11);
        this._f = this.getParameterFont("PLAIN", "Helvetica", 0, 11);
        this._f1 = this.getParameterFont("TINY", "Helvetica", 0, 2);
        this._fg = this.getParameterFont("GRAPHTITLE", "Helvetica", 1, 13);
        this.setBackground(AutoColor.getColor(this.getParameter("BACKGROUND_COLOR", "FFFFFF")));
        this.setForeground(AutoColor.getColor(this.getParameter("FOREGROUND_COLOR", "000000")));
        this._cPageBackground = AutoColor.getColor(this.getParameter("PAGEBACKGROUND_COLOR", "FFFFFF"));
        this.cTitle = AutoColor.getColor(this.getParameter("FONTCOLOR_TITLE", this.getParameter("FOREGROUND_COLOR", "000000")));
        for (int i = 1; i <= 10; ++i) {
            this._cColor[i - 1] = AutoColor.getColor(this.getParameter("COLOR" + i, "CCCCDDCCDDCCCCDDDDCCCCDDDDCCDDCCCCCCCCCCDDCCDDCCCCDDDDCCCCDD".substring((i - 1) * 6, (i - 1) * 6 + 6)));
            this._cGColor[i - 1] = AutoColor.getColor(this.getParameter("GRAPHCOLOR" + i, "7F7FFF7F1F5FFFFFBF9FDFDF5F007FFF7F7F007FBFBFBFFF00007FFF00FF".substring((i - 1) * 6, (i - 1) * 6 + 6)));
        }
        final String parameter = this.getParameter("BORDER", "3D");
        final Color color = AutoColor.getColor(this.getParameter("BORDER_COLOR", this.getParameter("FOREGROUND_COLOR", "FFFFFF")));
        final int parameter2 = this.getParameter("BORDER_WIDTH", 6);
        if (this.sTitle == null) {
            this.pTitle = new BorderSide(null, null, 1, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
        }
        else if (!this.sTitle.equals("other")) {
            if (upperCase.equals("FALSE")) {
                this.pTitle = new BorderSide(this, null, 1, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
            }
            else if ((this.getAppletContext().getClass().getName().indexOf("apple.mrj") != -1 && upperCase.equals("AUTO")) || upperCase.equals("TRUE")) {
                this.pTitle = new BorderSide(this, this.getParameter("MSG_VIEW_REPORT", this.getParameter("LANGUAGE", "ENGLISH").equals("FRENCH") ? "Rapport" : "View Report"), 1, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
            }
            else {
                this.pTitle = new BorderSide(this, null, 1, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
            }
        }
        this.border[0] = new BorderSide(null, null, 2, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
        this.border[1] = new BorderSide(null, null, 3, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
        this.border[2] = new BorderSide(null, null, 4, parameter2, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter);
        if (this.getParameter("ROR_MAXIMUM", false)) {
            this.bRORMax = true;
        }
        else if (this.getParameter("ROR_MAXIMUM", -1) > 0) {
            this.dRORMax = this.getParameter("ROR_MAXIMUM", this.dRORMax);
            this.bRORMax = true;
        }
        this.initCalculatorApplet();
        this.setLayout(new BorderLayout());
        this.initPanels();
        this.calculate();
        this.setValues();
        this.add("South", this.border[0]);
        this.add("North", this.pTitle);
        this.add("East", this.border[2]);
        this.add("West", this.border[1]);
        int n = 0;
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        if (this.bUseNorth && n < this.count) {
            if (this.order[n] != null) {
                panel.add("North", this.order[n]);
            }
            ++n;
        }
        if (this.bUseWest && n < this.count) {
            if (this.order[n] != null) {
                panel.add("West", this.order[n]);
            }
            ++n;
        }
        if (this.bUseEast && n < this.count) {
            if (this.order[n] != null) {
                panel.add("East", this.order[n]);
            }
            ++n;
        }
        if (this.bUseSouth && n < this.count) {
            if (this.order[n] != null) {
                panel.add("South", this.order[n]);
            }
            ++n;
        }
        if (n == this.count - 1) {
            if (this.order[n] != null) {
                panel.add("Center", this.order[n]);
            }
        }
        else if (n < this.count - 1) {
            final Panel panel2 = new Panel();
            if (this.nStack == 1) {
                panel2.setLayout(new GridLayout(this.count - n, 1, this.GRID_GAP_HOR, this.GRID_GAP_VERT));
            }
            else {
                panel2.setLayout(new GridLayout(1, this.count - n, this.GRID_GAP_HOR, this.GRID_GAP_VERT));
            }
            for (int j = n; j < this.count; ++j) {
                if (this.order[j] != null) {
                    panel2.add(this.order[j]);
                }
            }
            panel.add("Center", panel2);
        }
        this.add("Center", panel);
        this.layout();
        final String parameter3 = this.getParameter("TEXT_FOR_REPORT", "");
        if (parameter3.equals("")) {
            try {
                this.CC._sMessage = Calculation.getMessage(new URL(this.getDocumentBase(), this.getParameter("FILE_FOR_REPORT", this.getParameter("FILE_FOR_TEMPLATE", ""))));
            }
            catch (MalformedURLException ex) {}
        }
        else {
            this.CC._sMessage = parameter3;
        }
        try {
            this.MT.waitForAll();
        }
        catch (Exception ex2) {}
    }
    
    public abstract void initCalculatorApplet();
    
    public abstract void initPanels();
    
    public Image loadImage(final MediaTracker mediaTracker, final String s, final int n) {
        Image image = null;
        final String parameter = this.getParameter(s);
        try {
            if (parameter != null) {
                image = this.getImage(this.getDocumentBase(), parameter);
                mediaTracker.addImage(image, n);
            }
        }
        catch (Exception ex) {
            image = null;
        }
        return image;
    }
    
    public void printAll(final Graphics graphics) {
        if (this.iBrowser == 3) {
            try {
                final Enumeration<Applet> applets = this.getAppletContext().getApplets();
                while (applets.hasMoreElements()) {
                    final Applet applet = applets.nextElement();
                    if (applet.isShowing() && applet instanceof CalculatorApplet) {
                        ((CalculatorApplet)applet).realPrint(graphics);
                    }
                }
            }
            catch (Throwable t) {}
        }
        else if (System.getProperty("java.vendor").toLowerCase().startsWith("netscape")) {
            final Rectangle bounds = this.getBounds();
            graphics.setClip(bounds);
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, bounds.width, bounds.height);
        }
        else {
            this.realPrint(graphics);
        }
    }
    
    public void realPrint(final Graphics graphics) {
        this.findNbr(this.getComponents());
        super.printAll(graphics);
    }
    
    public abstract void refresh();
    
    public String sJavaScriptReport(final String s) {
        String javaScriptReport;
        try {
            this.setValues();
            javaScriptReport = this.CC.JavaScriptReport(s);
        }
        catch (Exception ex) {
            return Calculation.replace("MSG_ERROR", ex.getMessage(), this.getParameter("TEXT_FOR_ERROR", "<HTML><HEAD><TITLE>Calculation Error</TITLE></HEAD><BODY><P>The following calculation error has occurred.  Please close this window and correct the problem to view this report:<B><P>MSG_ERROR</BODY></HTML>"));
        }
        return javaScriptReport;
    }
    
    public void setCalculation(final Calculation cc) {
        this.CC = cc;
        this.CC.MHI = this.getParameter("QQ_HEADER_QQ", "");
        this.sLANGUAGE = this.getParameter("LANGUAGE", "ENGLISH");
        if (this.sLANGUAGE.equals("FRENCH")) {
            this.IEM = "S.V.P. entrez un valide ";
            this.RLE = " doit \u00eatre sup\u00e9rieur ou \u00e9gal \u00e0 ";
            this.RHE = " doit \u00eatre inf\u00e9rieur ou \u00e9gal \u00e0 ";
            this.CC.sScaleLabel3 = "Billions de ";
            this.CC.sScaleLabel2 = "Millions de ";
            this.CC.sScaleLabel1 = "Milliers de ";
            this.CC.MSG_YEARS_LBL = "ans";
            this.CC.MSG_YEAR_LBL = "an";
            this.CC.MSG_MONTHS_LBL = "mois";
            this.CC.MSG_MONTH_LBL = "moi";
            this.CC.MSG_AND_LBL = "et";
            this.CC.sCurrency = "dollars";
        }
        else if (this.sLANGUAGE.equals("UK")) {
            this.CC.sCurrency = "Pounds";
            this.sCP = "£";
        }
        else if (this.getParameter("MSG_YEARS_LBL", this.CC.MSG_YEARS_LBL).equals("a\u00f1os")) {
            this.CC.MSG_YEARS_LBL = "a\u00f1os";
            this.CC.MSG_YEAR_LBL = "a\u00f1o";
            this.CC.MSG_MONTHS_LBL = "meses";
            this.CC.MSG_MONTH_LBL = "mes";
            this.CC.MSG_AND_LBL = "y";
        }
        this.CC.MSG_YEARS_LBL = this.getParameter("MSG_YEARS_LBL", this.CC.MSG_YEARS_LBL);
        this.CC.MSG_YEAR_LBL = this.getParameter("MSG_YEAR_LBL", this.CC.MSG_YEAR_LBL);
        this.CC.MSG_MONTHS_LBL = this.getParameter("MSG_MONTHS_LBL", this.CC.MSG_MONTHS_LBL);
        this.CC.MSG_MONTH_LBL = this.getParameter("MSG_MONTH_LBL", this.CC.MSG_MONTH_LBL);
        this.CC.MSG_AND_LBL = this.getParameter("MSG_AND_LBL", this.CC.MSG_AND_LBL);
        if (this.getParameter("SCALE_LABEL_3") != null) {
            this.CC.sScaleLabel3 = String.valueOf(this.getParameter("SCALE_LABEL_3")) + " ";
        }
        if (this.getParameter("SCALE_LABEL_2") != null) {
            this.CC.sScaleLabel2 = String.valueOf(this.getParameter("SCALE_LABEL_2")) + " ";
        }
        if (this.getParameter("SCALE_LABEL_1") != null) {
            this.CC.sScaleLabel1 = String.valueOf(this.getParameter("SCALE_LABEL_1")) + " ";
        }
        if (this.getParameter("CURRENCY_LABEL") != null) {
            this.CC.sCurrency = String.valueOf(this.getParameter("CURRENCY_LABEL")) + " ";
        }
        for (int i = 0; i < this.sReportCols.length; ++i) {
            this.sReportCols[i] = this.getParameter("MSG_REPORT_COL" + (i + 1), null);
        }
        this.CC.sReportCols = this.sReportCols;
        this.CC._sCell = this.getParameter("CELL_HEADER", this.CC._sCell);
        this.CC._sCellFooter = this.getParameter("CELL_FOOTER", this.CC._sCellFooter);
        this.CC._sTableHeader = this.getParameter("TABLE_HEADER", this.CC._sTableHeader);
        this.CC._sTopRow = this.getParameter("TABLE_TOP_ROW", this.CC._sTopRow);
        this.CC._sEvenRow = this.getParameter("TABLE_EVEN_ROW", this.CC._sEvenRow);
        this.CC._sOddRow = this.getParameter("TABLE_ODD_ROW", this.CC._sOddRow);
        this.CC._sTableFooter = this.getParameter("TABLE_FOOTER", this.CC._sTableFooter);
        this.CC._sRowFooter = this.getParameter("TABLE_ROW_FOOTER", this.CC._sRowFooter);
    }
    
    public void setError(final String s) {
        if (this.pTitle instanceof BorderSide) {
            ((BorderSide)this.pTitle).setTitle(s, this._cError, this.getGraphTitleFont());
        }
        this.setErrorMsg(s, Color.red);
    }
    
    public void setErrorMsg(final String s, final Color color) {
    }
    
    public void setLanguage() {
        Fmt.setDecimalType(this.sDecimals);
        Fmt.sCP = this.sCP;
        Fmt.sCS = this.sCS;
        Fmt.sPP = this.sPP;
        Fmt.sPS = this.sPS;
        Fmt.sPP = this.sNP;
        Fmt.sNS = this.sNS;
        Fmt.YES = this.YES;
        Fmt.NO = this.NO;
        Nbr.CL = this._COLON;
        Nbr.INPUT_ERROR_MSG = this.IEM;
        Nbr.RANGE_LOW_ERROR_MSG = this.RLE;
        Nbr.RANGE_HIGH_ERROR_MSG = this.RHE;
    }
    
    public void setTitle(final String sTitle) {
        if (this.pTitle instanceof BorderSide) {
            ((BorderSide)this.pTitle).setTitle((this.M != null) ? this.M : sTitle, this.getBackground());
        }
        this.sTitle = sTitle;
        this.setTitleMsg();
    }
    
    public void setTitleMsg() {
    }
    
    public abstract void setValues() throws NumberFormatException;
}
