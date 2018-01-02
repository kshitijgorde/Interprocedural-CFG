// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import javax.swing.plaf.FontUIResource;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import KJEgraph.AutoColor;
import javax.swing.UIManager;
import java.awt.Container;
import KJEcalculation.CalculationException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.Font;
import KJEcalculation.Calculation;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import KJEgraph.Graph;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public abstract class CalculatorApplet extends JApplet implements ActionListener
{
    private Graph[] G;
    private String sAppletID;
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
    public int iBrowser;
    public boolean bInitialized;
    private double dRORMax;
    private boolean bRORMax;
    public int iArc;
    public int iSpace;
    private String gCB;
    private String M;
    public Image _imageBackground;
    public Image iL;
    private MediaTracker MT;
    public Color _cError;
    public Color _cPageBackground;
    public Color cTitle;
    private Color cTitleBack;
    private String sPageBackground;
    private Color[] _cColor;
    private Color[] _cGColor;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    private JPanel[] order;
    private int count;
    public int nStack;
    public boolean bUseNorth;
    public boolean bUseSouth;
    public boolean bUseWest;
    public boolean bUseEast;
    public int GRID_GAP_HOR;
    public int GRID_GAP_VERT;
    public JPanel pTitle;
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
        this.G = new Graph[2];
        this.sAppletID = "";
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
        this.bInitialized = false;
        this.dRORMax = 12.0;
        this.bRORMax = false;
        this.iArc = 0;
        this.iSpace = 0;
        this.gCB = "";
        this.M = null;
        this._cError = Color.red;
        this.sPageBackground = "FFFFFF";
        this._cColor = new Color[10];
        this._cGColor = new Color[10];
        this.order = new JPanel[15];
        this.count = 0;
        this.nStack = 1;
        this.bUseNorth = true;
        this.bUseSouth = false;
        this.bUseWest = false;
        this.bUseEast = false;
        this.GRID_GAP_HOR = 0;
        this.GRID_GAP_VERT = 0;
        this.border = new BorderSide[3];
        this.sTitle = "";
        this.bLogo = false;
    }
    
    public ButtonGroup ButtonGroup(final JRadioButton radioButton, final JRadioButton radioButton2, final JRadioButton radioButton3, final JRadioButton radioButton4, final JRadioButton radioButton5) {
        final ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton);
        buttonGroup.add(radioButton2);
        if (radioButton3 != null) {
            buttonGroup.add(radioButton3);
        }
        if (radioButton4 != null) {
            buttonGroup.add(radioButton4);
        }
        if (radioButton5 != null) {
            buttonGroup.add(radioButton5);
        }
        return buttonGroup;
    }
    
    public JPanel JBorderPanel(final JComponent component, final JComponent component2, final JComponent component3, final JComponent component4, final JComponent component5, final Color color) {
        return this.JBorderPanel(component, component2, component3, component4, component5, color, this.iSpace, this.iSpace);
    }
    
    public JPanel JBorderPanel(final JComponent component, final JComponent component2, final JComponent component3, final JComponent component4, final JComponent component5, final Color background, final int n, final int n2) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(n, n2));
        if (component != null) {
            panel.add("North", component);
        }
        if (component2 != null) {
            panel.add("South", component2);
        }
        if (component3 != null) {
            panel.add("East", component3);
        }
        if (component4 != null) {
            panel.add("West", component4);
        }
        if (component5 != null) {
            panel.add("Center", component5);
        }
        panel.setBackground(background);
        return panel;
    }
    
    public JButton JButton(final String s, final String s2, final Color color) {
        String parameter = this.getParameter("HOVER_" + Calculation.replace("MSG_", "", s), "");
        if (parameter.equals("")) {
            parameter = null;
        }
        return this.JButton(this.getParameter(s, s2), parameter, this.getForeground(), color, this.getPlainFont());
    }
    
    public JButton JButton(final String s, final String toolTipText, final Color foreground, final Color background, final Font font) {
        final JButton button = new JButton(s);
        button.setToolTipText(toolTipText);
        button.setForeground(foreground);
        button.setBackground(background);
        button.setFont(font);
        button.addActionListener(this);
        return button;
    }
    
    public JButton JButton(final String s, final String s2, final Color color, final Component component, final boolean b) {
        final JButton jButton = this.JButton(s, s2, color);
        jButton.setPreferredSize(new Dimension((b ? component : jButton).getPreferredSize().width, component.getPreferredSize().height));
        return jButton;
    }
    
    public JCheckBox JCheckBox(final String s) {
        return this.JCheckBox(s, null, this.getForeground(), this.getBackground(), this.getPlainFont(), false);
    }
    
    public JCheckBox JCheckBox(final String s, final String toolTipText, final Color foreground, final Color background, final Font font, final boolean b) {
        final JCheckBox checkBox = new JCheckBox(s, null, b);
        checkBox.setToolTipText(toolTipText);
        checkBox.setForeground(foreground);
        checkBox.setBackground(background);
        checkBox.setFont(font);
        checkBox.addActionListener(this);
        return checkBox;
    }
    
    public JCheckBox JCheckBox(final String s, final String s2, final Color color, final boolean b) {
        return this.JCheckBox(this.getParameter(s, s2), this.getParameter("HOVER_" + s, null), this.getForeground(), color, this.getPlainFont(), b);
    }
    
    public JComboBox JCombo() {
        return this.JCombo(this.getForeground(), this.getBackground(), this.getPlainFont());
    }
    
    public JComboBox JCombo(final Color foreground, final Color background, final Font font) {
        final JComboBox comboBox = new JComboBox();
        comboBox.setFont(font);
        comboBox.setForeground(foreground);
        comboBox.setBackground(background);
        comboBox.setEnabled(true);
        comboBox.addActionListener(this);
        return comboBox;
    }
    
    public DataPanel JDataPanel(final Color color, final Color color2, final Component component) {
        return new DataPanel(color, color2, component);
    }
    
    public JPanel JGridPanel(final Color color, final Color color2, final int n, final int n2) {
        return this.JPanel(color, color2, new GridLayout(n, n2));
    }
    
    public JLabel JOutputLabel() {
        return this.JOutputLabel("", null, this.getForeground(), this.getBackground(), 2, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final int n) {
        return this.JOutputLabel("", null, this.getForeground(), this.getBackground(), n, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final Color color) {
        return this.JOutputLabel("", null, this.getForeground(), color, 2, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s) {
        return this.JOutputLabel(s, null, this.getForeground(), this.getBackground(), 2, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s, final int n) {
        return this.JOutputLabel(s, null, this.getForeground(), this.getBackground(), n, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s, final String s2) {
        return this.JOutputLabel(s, s2, 2, "");
    }
    
    public JLabel JOutputLabel(final String s, final String s2, final int n) {
        return this.JOutputLabel(this.getParameter(s, s2), this.getParameter(Calculation.replace("MSG_", "HOVER_", s)), this.getForeground(), this.getBackground(), n, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s, final String s2, final int n, final String s3) {
        return this.JOutputLabel(String.valueOf(this.getParameter(s, s2)) + s3, this.getParameter(String.valueOf(Calculation.replace("MSG_", "HOVER_", s)) + s3), this.getForeground(), this.getBackground(), n, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s, final String s2, final int n, final boolean b) {
        return this.JOutputLabel(String.valueOf(this.getParameter(s, s2)) + this._COLON + " ", this.getParameter(Calculation.replace("MSG_", "HOVER_", s)), this.getForeground(), this.getBackground(), n, this.getPlainFont());
    }
    
    public JLabel JOutputLabel(final String s, final String toolTipText, final Color foreground, final Color background, final int n, final Font font) {
        JLabel label;
        if (toolTipText == null) {
            label = new JLabel(s, n);
        }
        else {
            label = new ClickLabel(s, n);
            label.setToolTipText(toolTipText);
        }
        label.setForeground(foreground);
        label.setBackground(background);
        label.setFont(font);
        return label;
    }
    
    public JPanel JPanel(final Color foreground, final Color background, final LayoutManager layout) {
        final JPanel panel = new JPanel();
        panel.setForeground(foreground);
        panel.setBackground(background);
        panel.setLayout(layout);
        return panel;
    }
    
    public JRadioButton JRadio(final String s, final Color color, final boolean b) {
        return this.JRadio(null, s, this.getForeground(), color, this.getPlainFont(), b);
    }
    
    public JRadioButton JRadio(final String s, final String s2, final Color foreground, final Color background, final Font font, final boolean b) {
        final JRadioButton radioButton = new JRadioButton(this.getParameter(s, s2), null, b);
        radioButton.setForeground(foreground);
        radioButton.setBackground(background);
        radioButton.setFont(font);
        radioButton.addActionListener(this);
        return radioButton;
    }
    
    public JRadioButton JRadio(final String s, final String s2, final Color color, final boolean b) {
        return this.JRadio(s, s2, this.getForeground(), color, this.getPlainFont(), b);
    }
    
    public JRadioButton JRadio(final String s, final String s2, final boolean b) {
        return this.JRadio(s, s2, this.getForeground(), this.getBackground(), this.getPlainFont(), b);
    }
    
    public JPanel JSpacer(final Color color) {
        return this.JSpacer(color, this.iSpace);
    }
    
    public JPanel JSpacer(final Color background, final int n) {
        final JPanel panel = new JPanel(true);
        panel.setBackground(background);
        panel.setPreferredSize(new Dimension(n, n));
        return panel;
    }
    
    public JTextField JTextField(final String s, final Color foreground, final Color background, final Font font) {
        final JTextField textField = new JTextField();
        textField.setForeground(foreground);
        textField.setBackground(background);
        textField.setFont(font);
        return textField;
    }
    
    public JPanel JTwoButtonPanel(final JRadioButton radioButton, final JRadioButton radioButton2, final Color color, final Color background, final Font font, final boolean b) {
        JPanel jPanel;
        if (b) {
            jPanel = new DataPanel(color, background, radioButton);
            ((DataPanel)jPanel).addRow("", radioButton, "", radioButton2, font, 1);
        }
        else {
            jPanel = this.JPanel(color, background, new FlowLayout());
            radioButton.setFont(font);
            radioButton2.setFont(font);
            jPanel.add(radioButton);
            jPanel.add(radioButton2);
        }
        this.ButtonGroup(radioButton, radioButton2, null, null, null);
        radioButton.setBackground(background);
        radioButton2.setBackground(background);
        jPanel.setBackground(background);
        return jPanel;
    }
    
    public Slider Slider(final int n, final int n2, final Font font, final String[] array, final boolean b) {
        return this.Slider(n, n2, font, array, b, 0);
    }
    
    public Slider Slider(final int n, final int n2, final Font font, final String[] array, final boolean b, final int n3) {
        final Slider slider = new Slider(n, n2, font, array, b, n3);
        slider.setDefaultFont(font, n3);
        slider.addActionListener(this);
        return slider;
    }
    
    public TextWrap TextWrap(final String s, final Color foreground, final Color background) {
        final TextWrap textWrap = new TextWrap(s);
        textWrap.setFont(this.getPlainFont());
        textWrap.setBoldFont(this.getGraphTitleFont());
        textWrap.setBackground(background);
        textWrap.setForeground(foreground);
        return textWrap;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.bInitialized) {
            this.calculate();
        }
    }
    
    public void addPanel(final JPanel panel) {
        if (panel != null) {
            (this.order[this.count++] = panel).validate();
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
            ((BorderSide)this.pTitle).setTitle("", this.cTitleBack);
        }
        this.sTitle = "";
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
    
    public String getAppletID() {
        return this.sAppletID.trim();
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
    
    public Graph[] getGraph() {
        return this.G;
    }
    
    public String getGraphApplet(final int n) {
        if (this.G[n] == null) {
            return "";
        }
        final String string = "\"" + this.getParameter("REPORT_GRAPH_HEIGHT", "380") + "\"";
        final String string2 = "\"" + this.getParameter("REPORT_GRAPH_WIDTH", "565") + "\"";
        return "<div align=center><!--[if IE]><object  name=\"graph\" classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\" height=" + string + " width=" + string2 + "  >" + "<param name=\"code\" value=\"KJEgui.GraphApplet\" />" + "<![endif]-->" + "<![if !IE]>" + "<object name=\"calculator\" classid=\"java:KJEgui.GraphApplet.class\" type=\"application/x-java-applet\" archive=\"dinkytown.jar\" height=" + string + " width=" + string2 + " > " + "<![endif]>" + "<param name=\"archive\" value=\"dinkytown.jar\" />" + "<param name=\"codebase\" value=\"" + this.gCB + "\" />" + "<param name=\"APPLET_ID\" value=\"" + this.getAppletID() + "\" />" + "<param name=\"GRAPH_ID\" value=\"" + n + "\" />" + "<param name=\"PAGEBACKGROUND_COLOR\" value=\"" + this.sPageBackground + "\" />" + "</object></div>";
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
    
    public int getMortgageTerm(final JComboBox comboBox) {
        int int1;
        try {
            int1 = Integer.parseInt(((String)comboBox.getSelectedItem()).substring(0, 2).trim());
        }
        catch (NumberFormatException ex) {
            int1 = 15;
        }
        return int1;
    }
    
    public JComboBox getMortgageTermChoice(final int n) {
        return this.getTermChoice(n, this.getParameter("TERM_SHOW_ALL", false), this.getParameter("TERM_MAXIMUM", 50), this.getParameter("SHOW_ALL_MAXIMUM", this.getParameter("TERM_MAXIMUM", 50)), "");
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
        String replace = s2;
        if (s != null) {
            final String parameter = this.getParameter(s);
            if (parameter == null) {
                replace = s2;
            }
            else {
                replace = Calculation.replace("\\'i", "\u00ed", Calculation.replace("\\'u", "\u00fa", Calculation.replace("\\'o", "\u00f3", Calculation.replace("\\?", "¿", Calculation.replace("\\~n", "\u00f1", Calculation.replace("\\'e", "\u00e9", parameter))))));
            }
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
    
    public JComboBox getTermChoice(final int n, final boolean b, final int n2, final int n3, final String s) {
        final JComboBox comboBox = new JComboBox<String>();
        String selectedItem = "";
        final int parameter = this.getParameter("TERM_SHOW_ADDITIONAL", 12);
        for (int i = n2; i >= 1; --i) {
            if (((b && i <= n3) || i % 5 == 0 || i == parameter) && (parameter != 0 || (i != 5 && i != 35))) {
                final String string = String.valueOf(i) + " " + ((i == 1) ? this.CC.MSG_YEAR_LBL : this.CC.MSG_YEARS_LBL) + ((i > n3) ? s : "");
                comboBox.addItem(string);
                if (i == n) {
                    selectedItem = string;
                }
            }
        }
        comboBox.setSelectedItem(selectedItem);
        comboBox.addActionListener(this);
        return comboBox;
    }
    
    public Font getTinyFont() {
        return this._f1;
    }
    
    public Font getTitleFont() {
        return this._fff;
    }
    
    public void hideCalculatorWindows() {
    }
    
    public Image imageBackground() {
        return this._imageBackground;
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            System.out.println("Error setting native LAF: " + ex);
        }
        this.gCB = this.getParameter("GRAPH_CODEBASE", this.getCodeBase().toString().trim());
        this.sDecimals = this.getParameter("EUROPE_DECIMALS", "FALSE");
        this.sCP = this.getParameter("CURRENCY_PREFIX", this.sCP);
        this.sPP = this.getParameter("PRECENT_PREFIX", this.sPP);
        this.sNP = this.getParameter("NUMBER_PREFIX", this.sNP);
        if (this.sCP.endsWith("_")) {
            this.sCP = Calculation.replace("_", " ", this.sCP);
        }
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
        final String parameter = this.getParameter("HOVER_FOREGROUND", this.getParameter("FOREGROUND_COLOR", "000000"));
        UIManager.put("ToolTip.background", new ColorUIResource(AutoColor.getColor(this.getParameter("HOVER_BACKGROUND", "FFFFFF"))));
        UIManager.put("ToolTip.foreground", new ColorUIResource(AutoColor.getColor(parameter)));
        UIManager.put("ToolTip.border", new LineBorder(AutoColor.getColor(this.getParameter("HOVER_BORDER", parameter)), 3));
        UIManager.put("ToolTip.font", new FontUIResource(this.getPlainFont()));
        this.sPageBackground = this.getParameter("PAGEBACKGROUND_COLOR", "FFFFFF");
        this._cPageBackground = AutoColor.getColor(this.sPageBackground);
        this.cTitle = AutoColor.getColor(this.getParameter("FONTCOLOR_TITLE", this.getParameter("FOREGROUND_COLOR", "000000")));
        this.cTitleBack = AutoColor.getColor(this.getParameter("BACKGROUNDCOLOR_TITLE", this.getParameter("BACKGROUND_COLOR", "FFFFFF")));
        this._cError = AutoColor.getColor(this.getParameter("ERRORCOLOR_TITLE", "FF0000"));
        for (int i = 1; i <= 10; ++i) {
            this._cColor[i - 1] = AutoColor.getColor(this.getParameter("COLOR" + i, "CCCCDDCCDDCCCCDDDDDDDDCCBBCCBBCCCCCCCCCCDDCCDDCCCCDDDDCCCCDD".substring((i - 1) * 6, (i - 1) * 6 + 6)));
            this._cGColor[i - 1] = AutoColor.getColor(this.getParameter("GRAPHCOLOR" + i, "7F7FFF7F1F5FFFFFBF9FDFDF5F007FFF7F7F007FBFBFBFFF00007FFF00FF".substring((i - 1) * 6, (i - 1) * 6 + 6)));
        }
        try {
            this.MT.waitForAll();
        }
        catch (Exception ex2) {}
        final String parameter2 = this.getParameter("BORDER", this.getParameter("BORDER_TYPE", "ROUND"));
        this.iArc = this.getParameter("ARC_SIZE", 4);
        this.iSpace = this.iArc / 2;
        final Color color = AutoColor.getColor(this.getParameter("BORDER_COLOR", "666666"));
        final int parameter3 = this.getParameter("BORDER_WIDTH", 1);
        final int parameter4 = this.getParameter("JUSTIFY_TITLE", 4);
        if (this.sTitle == null) {
            this.pTitle = new BorderSide(null, 1, parameter3, color, this.cTitleBack, this._cPageBackground, this.cTitle, parameter2, parameter4, this.iArc);
        }
        else if (!this.sTitle.equals("other")) {
            this.pTitle = new BorderSide(this, 1, parameter3, color, this.cTitleBack, this._cPageBackground, this.cTitle, parameter2, parameter4, this.iArc);
        }
        this.border[0] = new BorderSide(null, 2, parameter3, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter2, 0, this.iArc);
        this.border[1] = new BorderSide(null, 3, parameter3, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter2, 0, this.iArc);
        this.border[2] = new BorderSide(null, 4, parameter3, color, this.getBackground(), this._cPageBackground, this.cTitle, parameter2, 0, this.iArc);
        if (this.getParameter("ROR_MAXIMUM", false)) {
            this.bRORMax = true;
        }
        else if (this.getParameter("ROR_MAXIMUM", -1) > 0) {
            this.dRORMax = this.getParameter("ROR_MAXIMUM", this.dRORMax);
            this.bRORMax = true;
        }
        this.sAppletID = String.valueOf(this.getClass().getName()) + Fmt.input(new Random(19580427L).nextInt());
        this.initCalculatorApplet();
        this.getContentPane().setLayout(new BorderLayout());
        this.initPanels();
        try {
            this.calculate();
        }
        catch (NumberFormatException ex3) {}
        this.getContentPane().add("South", this.border[0]);
        this.getContentPane().add("North", this.pTitle);
        this.getContentPane().add("East", this.border[2]);
        this.getContentPane().add("West", this.border[1]);
        int n = 0;
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(this.iSpace, this.iSpace));
        panel.setBackground(this.getBackground());
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
            final JPanel panel2 = new JPanel();
            panel2.setBackground(this.getBackground());
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
        panel.validate();
        this.getContentPane().add("Center", panel);
        final String parameter5 = this.getParameter("TEXT_FOR_REPORT", "");
        if (parameter5.equals("")) {
            try {
                this.CC._sMessage = Calculation.getMessage(new URL(this.getDocumentBase(), this.getParameter("FILE_FOR_REPORT", this.getParameter("FILE_FOR_TEMPLATE", ""))));
            }
            catch (MalformedURLException ex4) {}
        }
        else {
            this.CC._sMessage = parameter5;
        }
        this.bInitialized = true;
    }
    
    public abstract void initCalculatorApplet();
    
    public abstract void initPanels();
    
    public Image loadImage(final MediaTracker mediaTracker, final String s, final int n) {
        Image image = null;
        String parameter = this.getParameter(s);
        if (s.equals("BACKGROUND") && parameter == null) {
            parameter = "background.jpg";
        }
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
        this.findNbr(this.getComponents());
        super.printAll(graphics);
    }
    
    public abstract void refresh();
    
    public String sJavaScriptReport(final String s) {
        String javaScriptReport;
        try {
            this.setValues();
            javaScriptReport = this.CC.JavaScriptReport(this.getGraphApplet(0), this.getGraphApplet(1));
            this.refresh();
        }
        catch (Exception ex) {
            return Calculation.replace("MSG_ERROR", ex.getMessage(), this.getParameter("TEXT_FOR_ERROR", "<HTML><HEAD><TITLE>Calculation Error</TITLE></HEAD><BODY><P>The following calculation error has occurred.  Please close this window and correct the problem to view this report:<B><P>MSG_ERROR</BODY></HTML>"));
        }
        return javaScriptReport;
    }
    
    public void setCalculation(final Calculation cc) {
        this.CC = cc;
        this.CC.MHI = this.getParameter("QQ_HEADER_QQ", "");
        this.CC.bHT = this.getParameter("HIDE_ALL_TAXES", false);
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
        this.setErrorMsg(s, this._cError);
    }
    
    public void setErrorMsg(final String s, final Color color) {
    }
    
    public void setGraphDefaults(final Graph graph, final Color foreground, final Color background) {
        graph.FONT_TITLE = this.getGraphTitleFont();
        graph.FONT_BOLD = this.getBoldFont();
        graph.FONT_PLAIN = this.getPlainFont();
        graph.setBackground(background);
        graph.setForeground(foreground);
        graph._cGrid = AutoColor.getColor(this.getParameter("COLOR_GRID", "BBBBBB"));
        graph._cAxisLine = AutoColor.getColor(this.getParameter("COLOR_AXIS", "666666"));
        int n = 0;
        if (this.G[0] != null) {
            ++n;
        }
        this.G[n] = graph;
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
            ((BorderSide)this.pTitle).setTitle((this.M != null) ? this.M : sTitle, this.cTitle);
        }
        this.sTitle = sTitle;
        this.setTitleMsg();
    }
    
    public void setTitleMsg() {
    }
    
    public abstract void setValues() throws NumberFormatException;
}
