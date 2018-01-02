import javax.swing.JFrame;
import java.applet.AppletContext;
import javax.swing.event.HyperlinkEvent;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.awt.AWTEvent;
import java.beans.PropertyChangeSupport;
import java.io.DataInputStream;
import java.awt.Insets;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URLConnection;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.util.Enumeration;
import java.awt.event.WindowListener;
import java.awt.Dialog;
import java.io.IOException;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Canvas;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JEditorPane;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.applet.AppletStub;
import java.awt.Image;
import java.util.Hashtable;
import java.io.InputStream;
import java.io.File;
import javax.swing.event.HyperlinkListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CommonOmegaPanel extends JPanel implements ActionListener, AppInterface, HyperlinkListener, WirisAppletInterface
{
    public static int minor_version;
    public static int major_version;
    public static int acceptable_minor_version;
    public static int acceptable_major_version;
    static boolean ACTIVATED;
    static boolean FLOATING;
    static boolean JAVA2;
    static boolean TRUE;
    private boolean failedFormSend;
    private File lastSavedFile;
    private InputStream omegaResponseStream;
    private static Hashtable sendRedirected;
    private static final String[] paramColorNames;
    public static int[] paramColorNumbers;
    private static final String[] parameters;
    public FormulaEditorCalc activeFormula;
    private WToolTipPanel formulaParent;
    private LightPanel mainPane;
    private static CommonOmegaPanel$FloatDialog dialogToolbar;
    private static WEditorToolBar toolbar;
    private static CommonOmegaPanel$FloatDialog dialogToolbar2;
    private static WEditorToolBar toolbar2;
    private static CommonOmegaPanel$FloatDialog dialogErrors;
    private static WList staticLlistaErrors;
    private WToolBar mainToolbar;
    private FormulaToolBarFactory toolbarFactory;
    int acceptTooltip;
    String add;
    private int zoom;
    private String customToolbar;
    private int wirisLevel;
    boolean addHyperlinkListener;
    boolean addNewToolTip;
    WList addNotify;
    boolean addParametersToHashtable;
    boolean addPlotterFrameListener;
    boolean addRequestProperty;
    static Object addWindowListener;
    static int append;
    private boolean calcula_visible;
    public static Image sponsor;
    public static Image sponsorg;
    boolean applet_class;
    boolean calcButtonVisible;
    private ResourceProvider resourceProvider;
    private AppletStub stub;
    boolean charAt;
    static Class choose_save_request_exec_file;
    
    public CommonOmegaPanel(final AppletStub stub) {
        this.failedFormSend = true;
        this.lastSavedFile = null;
        this.omegaResponseStream = null;
        this.activeFormula = null;
        this.toolbarFactory = null;
        this.add = "fix";
        this.zoom = 100;
        this.customToolbar = null;
        this.wirisLevel = 0;
        this.addHyperlinkListener = false;
        this.addNewToolTip = false;
        this.addNotify = null;
        this.addParametersToHashtable = false;
        this.addRequestProperty = false;
        this.calcula_visible = true;
        this.applet_class = true;
        this.calcButtonVisible = true;
        this.charAt = false;
        this.stub = stub;
        this.enableEvents(8L);
    }
    
    public final void init() {
        OmegaSystem.code_base = this.getCodeBase().toString();
        OmegaSystem.document_base = ((this.getDocumentBase() != null) ? this.getDocumentBase().toString() : null);
        System.err.println("2.3.5 net Feb 2, 2012");
        final String sendReferer = this.sendReferer();
        this.setName("wirisCommonApplet");
        this.failedFormSend = false;
        this.resourceProvider = new ResourceProvider((CommonOmegaPanel.choose_save_request_exec_file == null) ? (CommonOmegaPanel.choose_save_request_exec_file = acceptable_minor_version("CommonOmegaApplet")) : CommonOmegaPanel.choose_save_request_exec_file, this.stub);
        this.requestFocus();
        new OmegaSystem(System.err);
        this.enableEvents(2999L);
        synchronized (CommonOmegaPanel.addWindowListener) {
            ++CommonOmegaPanel.append;
            if (CommonOmegaPanel.sponsor == null) {
                CommonOmegaPanel.sponsor = this.resourceProvider.getInternalImage("Icones/sponsor.gif");
            }
            if (CommonOmegaPanel.sponsorg == null) {
                CommonOmegaPanel.sponsorg = this.resourceProvider.getInternalImage("Icones/sponsorg.gif");
            }
        }
        try {
            String s = null;
            String s2 = " ";
            final String parameter = this.getParameter("Version");
            if (parameter != null) {
                try {
                    final int index = parameter.indexOf(46);
                    final int int1 = Integer.parseInt(parameter.substring(0, index));
                    final int int2 = Integer.parseInt(parameter.substring(index + 1));
                    if (int1 < CommonOmegaPanel.acceptable_major_version || (int1 == CommonOmegaPanel.acceptable_major_version && int2 < CommonOmegaPanel.acceptable_minor_version)) {
                        final TextArea textArea = new TextArea();
                        textArea.append("Error:\n\nThis \"applet\" tag has a\nlower version than the \"applet\" version.\n\n   This document might be updated.");
                        textArea.setEditable(false);
                        textArea.setFont(new Font("Monospaced", 0, 16));
                        this.setLayout(new GridLayout(1, 1));
                        this.add(textArea);
                        this.doLayout();
                        this.addParametersToHashtable = false;
                        return;
                    }
                }
                catch (Throwable t2) {}
            }
            this.setLayout(new BorderLayout());
            OmegaSystem.dual = this.ACTIVATED("dual", "true");
            final String parameter2 = this.getParameter("TextInicial");
            if (parameter2 != null) {
                s2 = parameter2;
            }
            final String parameter3 = this.getParameter("InitialText");
            if (parameter3 != null) {
                s2 = parameter3;
            }
            final String parameter4 = this.getParameter("XMLInitialText");
            if (parameter4 != null) {
                s = parameter4;
                final String parameter5 = this.getParameter("XMLInitialText1");
                if (parameter5 != null) {
                    s += parameter5;
                }
            }
            if (this.ACTIVATED("compressed", "true") && s != null && s.length() > 0) {
                s = Base64.uncompressString(s);
            }
            boolean b = false;
            boolean b2 = false;
            String s3;
            if (s != null || s2.trim().length() == 0) {
                b = true;
                b2 = (s == null);
                final String acceptable_major_version = this.acceptable_major_version(s);
                s3 = ((acceptable_major_version == null) ? "" : acceptable_major_version);
                final boolean b3 = s3.length() == 0;
                if (b3 || !this.FLOATING("Command", "Comanda", "false")) {
                    s3 = "<command><input><math>" + s3 + "</math></input></command>";
                }
                if (b3 || !this.FLOATING("Commands", "Comandes", "false")) {
                    s3 = "<group>" + s3 + "</group>";
                }
                if (b3 || !this.FLOATING("Interface", "Interficie", "false")) {
                    s3 = "<session>" + s3 + "</session>";
                }
            }
            else {
                s3 = this.acceptable_major_version(s2);
                if (!this.FLOATING("Command", "Comanda", "false")) {
                    s3 = "\\command{\\beginmultiline " + s3 + "\\endmultiline}{}";
                }
                if (!this.FLOATING("Commands", "Comandes", "false")) {
                    s3 = "\\commands{\\beginvbox " + s3 + "\\endvbox }";
                }
                if (!this.FLOATING("Interface", "Interficie", "false")) {
                    s3 = "\\interface{\\beginvbox " + s3 + "\\endvbox}";
                }
            }
            final boolean b4 = this.ACTIVATED("Domain", "true") || this.ACTIVATED("Domini", "true");
            if (this.ACTIVATED("requestFocus", "true")) {
                this.addPlotterFrameListener = true;
            }
            else {
                this.addPlotterFrameListener = false;
            }
            if (this.ACTIVATED("calculate", "false")) {
                this.applet_class = false;
            }
            else {
                this.applet_class = true;
            }
            if (this.ACTIVATED("scrollbar", "false")) {
                this.calcButtonVisible = false;
            }
            else {
                this.calcButtonVisible = true;
            }
            this.customToolbar = this.getParameter("ToolbarDef");
            final String parameter6 = this.getParameter("Toolbar");
            this.add = "fixe";
            if (this.customToolbar == null && parameter6 != null) {
                if (Utils.equals(parameter6, "Flotant") || Utils.equals(parameter6, "floating")) {
                    this.add = "floating";
                }
                else if (Utils.equals(parameter6, "none")) {
                    this.add = "none";
                }
            }
            if (this.ACTIVATED("Level", "primary")) {
                this.wirisLevel = 1;
                CommonOmegaPanel.TRUE = true;
            }
            else {
                this.wirisLevel = 0;
                CommonOmegaPanel.TRUE = false;
            }
            this.addHyperlinkListener = this.ACTIVATED("Info", "true");
            if (this.ACTIVATED("calculate_button", "false")) {
                this.calcula_visible = false;
            }
            else {
                this.calcula_visible = true;
            }
            if (Utils.equals(this.add, "floating")) {
                if (CommonOmegaPanel.staticLlistaErrors == null) {
                    CommonOmegaPanel.staticLlistaErrors = WList.newList(3);
                }
                this.addNotify = CommonOmegaPanel.staticLlistaErrors;
            }
            else {
                this.addNotify = WList.newList(3);
            }
            int int3 = 10;
            int int4 = 16;
            final String parameter7 = this.getParameter("LeftMargin");
            if (parameter7 != null) {
                try {
                    int3 = Integer.parseInt(parameter7);
                }
                catch (NumberFormatException ex) {}
            }
            final String parameter8 = this.getParameter("TopMargin");
            if (parameter8 != null) {
                try {
                    int4 = Integer.parseInt(parameter8);
                }
                catch (NumberFormatException ex2) {}
            }
            this.toolbarFactory = FormulaToolBarFactory.getInstance(this.resourceProvider);
            final FormulaToolBarFactory toolbarFactory = this.toolbarFactory;
            FormulaToolBarFactory.sponsorImage = CommonOmegaPanel.sponsor;
            final FormulaToolBarFactory toolbarFactory2 = this.toolbarFactory;
            FormulaToolBarFactory.sponsorOrgImage = CommonOmegaPanel.sponsorg;
            this.mainPane = new LightPanel(new BorderLayout());
            this.add(new WToolTipPanel(this.mainPane), "Center");
            this.toolbarFactory.addNewToolTip(this.mainPane);
            final FormulaCalcComponent formulaCalcComponent = new FormulaCalcComponent();
            this.formulaParent = new WToolTipPanel(formulaCalcComponent);
            ScrollPaneWrapper instance;
            Component scrollPane;
            if (this.calcButtonVisible) {
                instance = ScrollPaneWrapper.newInstance();
                instance.setView(this.formulaParent);
                scrollPane = instance.getScrollPane();
                scrollPane.setName("formulaScroll");
            }
            else {
                final WPanel wPanel = new WPanel(new GridLayout(1, 1));
                wPanel.add(this.formulaParent);
                scrollPane = wPanel;
                instance = null;
            }
            final FormulaEditorCalc activeFormula = new FormulaEditorCalc(null, this.addNotify, this.add, this.applet_class, instance, this, b4, formulaCalcComponent);
            activeFormula.setMargins(new Dimension(int3, int4));
            formulaCalcComponent.setFormula(this.activeFormula = activeFormula);
            final Hashtable hashtable = new Hashtable();
            this.addParametersToHashtable(hashtable);
            this.exchangeProperties(hashtable, 0);
            this.formulaParent.acceptTooltip = false;
            if (this.applet_class) {
                activeFormula.O = this.toolbarFactory.createItem("compute", activeFormula, 268435457);
                this.formulaParent.add(activeFormula.O, WToolTipPanel.FLOATING, 0);
                activeFormula.calcButtonVisible = this.calcula_visible;
                if (!this.calcula_visible) {
                    activeFormula.O.setVisible(false);
                }
            }
            this.mainPane.add(scrollPane, "Center");
            if (this.getParameter("overrideDocumentBase") != null) {
                activeFormula.overrideDocumentBase = (this.getParameter("overrideDocumentBase") != null);
            }
            final String parameter9 = this.getParameter("initialAction");
            CommonOmegaPanel.FLOATING = this.ACTIVATED("requestfirstevaluation", "true");
            final boolean activated = this.ACTIVATED("check", "true");
            activeFormula.setInitialString(s3, CommonOmegaPanel.FLOATING, activated, b, parameter9, !b2 || parameter9 != null || activated || CommonOmegaPanel.FLOATING);
            activeFormula.inicialitza();
            final String parameter10 = this.getParameter("randomSeed");
            if (parameter10 != null && parameter10.length() > 0) {
                try {
                    activeFormula.setSeed(Integer.parseInt(parameter10));
                }
                catch (Error error) {
                    error.printStackTrace();
                }
            }
            if (Utils.equals(this.add, "none")) {
                this.acceptTooltip = 2;
            }
            else if (Utils.equals(this.add, "floating")) {
                this.acceptTooltip = 1;
            }
            else {
                this.acceptTooltip = 0;
                this.mainToolbar = this.createMainToolbar(activeFormula);
                this.mainPane.add(this.mainToolbar, "North");
                if (this.wirisLevel == 1) {
                    this.mainPane.add(this.createEastToolbar(activeFormula), "East");
                }
                this.mainPane.add(this.addNotify, "South");
            }
            if (sendReferer != null && sendReferer.length() > 0) {
                final JEditorPane editorPane = new JEditorPane();
                editorPane.setContentType("text/html; charset=UTF-8");
                editorPane.putClientProperty("charset", "UTF-8");
                editorPane.setText(sendReferer);
                editorPane.setEditable(false);
                editorPane.addHyperlinkListener(this);
                this.add(editorPane, "South");
            }
            this.addParametersToHashtable = true;
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.addParametersToHashtable = false;
            throw new Error(t);
        }
    }
    
    public final URL getDocumentBase() {
        String s = this.getParameter("overrideDocumentBase");
        URL url = null;
        try {
            if (s != null) {
                s = s.trim();
                if (!s.endsWith("/")) {
                    s += "/";
                }
                if (s.startsWith("/")) {
                    final URL documentBase = this.stub.getDocumentBase();
                    url = new URL(documentBase.getProtocol(), documentBase.getHost(), documentBase.getPort(), s);
                }
                else {
                    url = new URL(s);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Invalid document base : " + s);
        }
        if (url != null) {
            return url;
        }
        return this.stub.getDocumentBase();
    }
    
    public final Font dialog_font() {
        return new Font("SansSerif", 0, 12 * this.zoom / 100);
    }
    
    private final boolean ACTIVATED(final String s, final String s2) {
        return Utils.equals(this.getParameter(s), s2);
    }
    
    private final boolean FLOATING(final String s, final String s2, final String s3) {
        return Utils.equals(this.JAVA2(s, s2), s3);
    }
    
    private final String JAVA2(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return this.getParameter(s2);
    }
    
    public final void start() {
        if (this.activeFormula != null) {
            this.activeFormula.getComponent().setEnabled(true);
            if (this.addPlotterFrameListener && !this.addRequestProperty) {
                this.addRequestProperty = true;
                this.activeFormula.requestFocus();
            }
        }
    }
    
    public final void stop() {
        this.setVisible(true);
        this.addRequestProperty = false;
    }
    
    public final void destroy() {
        if (this.activeFormula != null) {
            this.activeFormula.finalitza();
        }
        if (this.mainToolbar != null) {
            this.mainPane.remove(this.mainToolbar);
            this.mainToolbar.removeContent();
            this.mainToolbar = null;
        }
        synchronized (CommonOmegaPanel.addWindowListener) {
            --CommonOmegaPanel.append;
            if (CommonOmegaPanel.append == 0) {
                if (CommonOmegaPanel.dialogToolbar != null) {
                    CommonOmegaPanel.dialogToolbar.I(false, this);
                    CommonOmegaPanel.dialogToolbar = null;
                    CommonOmegaPanel.toolbar = null;
                }
                if (CommonOmegaPanel.dialogToolbar2 != null) {
                    CommonOmegaPanel.dialogToolbar2.I(false, this);
                    CommonOmegaPanel.dialogToolbar2 = null;
                    CommonOmegaPanel.toolbar2 = null;
                }
                if (CommonOmegaPanel.dialogErrors != null) {
                    CommonOmegaPanel.dialogErrors.I(false, this);
                    CommonOmegaPanel.dialogErrors = null;
                }
                FormulaEditorParse.I();
            }
        }
    }
    
    public final void removeNotify() {
        super.removeNotify();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("floatingToolbar")) {
            if (CommonOmegaPanel.dialogToolbar == null) {
                CommonOmegaPanel.toolbar = (WEditorToolBar)this.createMainToolbar(this.activeFormula);
                CommonOmegaPanel.dialogToolbar = new CommonOmegaPanel$FloatDialog(this, CommonOmegaPanel.toolbar, "Toolbar");
            }
            boolean i = CommonOmegaPanel.dialogToolbar.I();
            if (this.wirisLevel == 1) {
                if (CommonOmegaPanel.dialogToolbar2 == null) {
                    CommonOmegaPanel.toolbar2 = (WEditorToolBar)this.createEastToolbar(this.activeFormula);
                    CommonOmegaPanel.dialogToolbar2 = new CommonOmegaPanel$FloatDialog(this, CommonOmegaPanel.toolbar2, "Toolbar");
                }
                i = (i && CommonOmegaPanel.dialogToolbar2.I());
                CommonOmegaPanel.dialogToolbar2.I(!i, this);
            }
            CommonOmegaPanel.dialogToolbar.I(!i, this);
        }
        else if (actionCommand.equals("errorWindow")) {
            if (CommonOmegaPanel.dialogErrors == null) {
                CommonOmegaPanel.dialogErrors = new CommonOmegaPanel$FloatDialog(this, CommonOmegaPanel.staticLlistaErrors, "Error messages");
            }
            CommonOmegaPanel.dialogErrors.I(!CommonOmegaPanel.dialogErrors.I(), this);
        }
        else if (actionCommand.equals("help")) {
            try {
                final URL url = new URL(this.getCodeBase(), Configuration.manualURL);
                this.getAppletContext().showDocument(url, "_blank");
                System.err.println(url.toExternalForm());
            }
            catch (MalformedURLException ex) {
                System.out.println("URL incorrecta: " + ex.toString());
            }
        }
        else if (actionCommand.equals("news")) {
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), "http:../novetats/index.htm"), "_blank");
            }
            catch (MalformedURLException ex2) {
                System.out.println("URL incorrecta: " + ex2.toString());
            }
        }
        else {
            if (!actionCommand.equals("print")) {
                if (!actionCommand.equals("printDialog")) {
                    if (actionCommand.equals("printPreview")) {
                        final PrintPreview printPreview = new PrintPreview(this.getMainFrame(), this.activeFormula);
                        printPreview.setSize(600, 400);
                        printPreview.show();
                        return;
                    }
                    if (actionCommand.equals("save")) {
                        this.TRUE();
                        return;
                    }
                    if (actionCommand.startsWith("sponsorlogo")) {
                        this.showDocumentNewWindow("http://www.wiris.com/");
                    }
                    return;
                }
            }
            try {
                final FormulaPrintManager instance = FormulaPrintManager.newInstance(this.getMainFrame());
                if (actionCommand.equals("print")) {
                    instance.print(this.activeFormula);
                }
                else if (instance.showPrintDialog()) {
                    instance.print(this.activeFormula);
                }
            }
            catch (SecurityException ex3) {
                new MessageBox(this.getMainFrame(), "Error", "Cannot access to the printing system due to security restriction.", 1).show();
            }
        }
    }
    
    private final void TRUE() {
        final boolean b = true;
        final boolean b2 = false;
        final boolean b3 = false;
        final MessageBox messageBox = new MessageBox(this.getMainFrame(), "Save options...", 3);
        final Canvas canvas = new Canvas();
        canvas.setSize(200, 1);
        messageBox.add(canvas);
        final Checkbox checkbox = new Checkbox("", new CheckboxGroup(), b);
        final Checkbox checkbox2 = new Checkbox("Focus on load", CommonOmegaPanel.ACTIVATED);
        messageBox.add(checkbox2);
        final Checkbox checkbox3 = new Checkbox("Execute on load", CommonOmegaPanel.FLOATING);
        messageBox.add(checkbox3);
        final Checkbox checkbox4 = new Checkbox("Hide toolbar", CommonOmegaPanel.JAVA2);
        messageBox.add(checkbox4);
        final Checkbox checkbox5 = new Checkbox("Primary", CommonOmegaPanel.TRUE);
        messageBox.add(checkbox5);
        if (messageBox.showDialog().equals("Ok")) {
            CommonOmegaPanel.ACTIVATED = checkbox2.getState();
            CommonOmegaPanel.FLOATING = checkbox3.getState();
            CommonOmegaPanel.JAVA2 = checkbox4.getState();
            CommonOmegaPanel.TRUE = checkbox5.getState();
            if (b || b2) {
                final String acceptTooltip = this.acceptTooltip(false, checkbox3.getState(), checkbox2.getState(), checkbox4.getState(), CommonOmegaPanel.TRUE, "UTF-8");
                try {
                    this.saveFile(acceptTooltip.getBytes("UTF8"), b);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else if (b3) {
                final TextArea textArea = new TextArea(this.acceptTooltip(false, checkbox3.getState(), checkbox2.getState(), checkbox4.getState(), CommonOmegaPanel.TRUE, null), 25, 50);
                final Dialog dialog = new Dialog(this.getMainFrame(), "HTML source code", false);
                dialog.setLayout(new GridLayout(1, 1));
                dialog.addWindowListener(new WindowListenerWithClose());
                dialog.setSize(300, 400);
                dialog.add(textArea);
                dialog.doLayout();
                dialog.show();
            }
        }
    }
    
    private final String acceptTooltip(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final String s) {
        final AbstractBox lacapsa = this.activeFormula.lacapsa;
        final int n = lacapsa.width + 2 * this.activeFormula.tl.x + 32;
        final int n2 = lacapsa.height + 2 * this.activeFormula.tl.y + 32;
        final String line_separator = OmegaSystem.line_separator;
        final Font defaultFont = this.activeFormula.getFontUtils().getDefaultFont(4, null);
        final Font nominalFont = lacapsa.getNominalFont();
        if (nominalFont.getName().equals(defaultFont.getName())) {
            if (nominalFont.getSize() == defaultFont.getSize()) {
                if (nominalFont.getStyle() != defaultFont.getStyle()) {}
            }
        }
        final boolean b6 = this.activeFormula.tl.y != 16 || this.activeFormula.tl.x != 10;
        final StringBuffer sb = new StringBuffer();
        sb.append("<!-- saved from url=(0013)about:internet -->\r\n");
        sb.append("<HTML>" + line_separator);
        if (s != null) {
            sb.append("<HEAD>\n<meta http-equiv=\"content-type\" content=\"text/html; charset=");
            sb.append(s);
            sb.append("\">\n<HEAD>\n");
        }
        if (b) {
            sb.append("<BODY BGCOLOR='#d0d0d0'" + line_separator);
        }
        else {
            sb.append("<BODY BGCOLOR='#d0d0d0'" + line_separator);
        }
        sb.append(">" + line_separator);
        sb.append("<applet" + line_separator);
        sb.append("  CODE='" + Configuration.applet_class + "'" + line_separator);
        sb.append("  CODEBASE='" + this.getCodeBaseForSave() + "'" + line_separator);
        sb.append("  ARCHIVE='" + Configuration.jar + (OmegaSystem.dual ? ".dual" : "") + "'" + line_separator);
        if (b) {
            sb.append("  WIDTH=" + ((n < 800) ? ("" + n) : "100%") + "" + line_separator);
            sb.append("  HEIGHT=" + n2 + "" + line_separator);
        }
        else {
            sb.append("  WIDTH='100%'" + line_separator);
            sb.append("  HEIGHT='100%'" + line_separator);
        }
        sb.append(">" + line_separator);
        sb.append("   <PARAM NAME='version'         VALUE='" + CommonOmegaPanel.major_version + "." + CommonOmegaPanel.minor_version);
        sb.append("'></PARAM>" + line_separator);
        if (this.addHyperlinkListener) {
            sb.append("   <PARAM NAME='info'         VALUE='true'></PARAM>" + line_separator);
        }
        if (b4) {
            sb.append("   <PARAM NAME='toolbar'         VALUE='floating'></PARAM>" + line_separator);
        }
        if (b2) {
            sb.append("   <PARAM NAME='requestFirstEvaluation'    VALUE='true'></PARAM>" + line_separator);
        }
        if (b3) {
            sb.append("   <PARAM NAME='requestFocus'    VALUE='true'></PARAM>" + line_separator);
        }
        if ((lacapsa.estil & lacapsa.forca_estil & 0x2) != 0x0) {
            sb.append("   <PARAM NAME='calculate'    VALUE='false'></PARAM>" + line_separator);
        }
        if (b6) {
            sb.append("   <PARAM NAME='leftMargin'      VALUE='" + this.activeFormula.tl.x + "'></PARAM>" + line_separator);
            sb.append("   <PARAM NAME='topMargin'       VALUE='" + this.activeFormula.tl.y + "'></PARAM>" + line_separator);
        }
        if (OmegaSystem.dual) {
            sb.append("   <PARAM NAME='dual' VALUE='true'></PARAM>" + line_separator);
        }
        sb.append("   <PARAM NAME='command'        VALUE='false'></PARAM>" + line_separator);
        sb.append("   <PARAM NAME='commands'       VALUE='false'></PARAM>" + line_separator);
        sb.append("   <PARAM NAME='interface'      VALUE='false'></PARAM>" + line_separator);
        final Hashtable properties = this.getProperties();
        final Enumeration<String> keys = properties.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            sb.append("   <PARAM NAME='" + s2 + "'      VALUE='" + properties.get(s2) + "'></PARAM>" + line_separator);
        }
        sb.append("   <PARAM NAME='XMLinitialText'     VALUE=\n'");
        sb.append(unicode2JavaScriptBis(this.activeFormula.script(this.activeFormula.lacapsa)));
        sb.append("'\n></PARAM>" + line_separator);
        if (b5) {
            sb.append("   <PARAM NAME='Level'     VALUE='primary' />");
            sb.append(line_separator);
        }
        if (this.customToolbar != null) {
            sb.append("   <PARAM NAME='ToolbarDef'     VALUE=\n'");
            sb.append(this.customToolbar);
            sb.append("' />");
            sb.append(line_separator);
        }
        sb.append("</applet>" + line_separator);
        sb.append("</BODY>" + line_separator);
        sb.append("</HTML>" + line_separator);
        return sb.toString();
    }
    
    private void saveFile(final byte[] array, final boolean b) {
        final URLConnection openConnection = new URL(OmegaSystem.translateURL(Utils.urlCat(Configuration.url_exec(), Configuration.choose_save_request_exec_file()))).openConnection();
        openConnection.setDoOutput(true);
        openConnection.setDoInput(true);
        openConnection.setAllowUserInteraction(false);
        openConnection.addRequestProperty("Accept-Charset", "utf-8");
        if (b) {
            openConnection.addRequestProperty("Wiris-Direct", "true");
        }
        final DataOutputStream dataOutputStream = new DataOutputStream(new PostMethodFilter(openConnection.getOutputStream()));
        dataOutputStream.write(array);
        dataOutputStream.flush();
        final InputStream inputStream = openConnection.getInputStream();
        OmegaClientURL.recvCookies(openConnection);
        final StringBuffer sb = new StringBuffer();
        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
            sb.append((char)i);
        }
        final URL url = new URL(Utils.urlCat(Configuration.url_save_download, sb.toString()));
        if (b) {
            this.getAppletContext().showDocument(url);
        }
        else {
            this.getAppletContext().showDocument(url, "_blank");
        }
    }
    
    public final String getCodeBaseForSave() {
        final String translateURL = OmegaSystem.translateURL(Configuration.url_code_base() + '/' + Configuration.jar);
        return translateURL.substring(0, translateURL.lastIndexOf("/"));
    }
    
    private WToolBar createMainToolbar(final FormulaEditorCalc formulaEditorCalc) {
        final WToolBar toolBar = this.toolbarFactory.newToolBar(formulaEditorCalc, null, 0);
        toolBar.setBackground(Configuration.colorMainToolBar);
        Reader reader;
        if (this.customToolbar != null) {
            reader = new StringReader(this.customToolbar);
        }
        else {
            String s;
            if (this.wirisLevel == 1) {
                s = "toolbar_prim.ini";
            }
            else {
                s = "toolbar.ini";
            }
            final InputStream internalResourceStream = this.resourceProvider.getInternalResourceStream(s);
            if (internalResourceStream == null) {
                return toolBar;
            }
            try {
                reader = new InputStreamReader(internalResourceStream, "ASCII");
            }
            catch (UnsupportedEncodingException ex) {
                reader = new InputStreamReader(internalResourceStream);
            }
        }
        this.toolbarFactory.createToolbar(reader, toolBar, null);
        final InputStream internalResourceStream2 = this.resourceProvider.getInternalResourceStream("toolbar_sponsor.ini");
        if (internalResourceStream2 != null) {
            try {
                reader = new InputStreamReader(internalResourceStream2, "ASCII");
            }
            catch (UnsupportedEncodingException ex2) {
                reader = new InputStreamReader(internalResourceStream2);
            }
        }
        this.toolbarFactory.createToolbar(reader, toolBar, null);
        return toolBar;
    }
    
    private WToolBar createEastToolbar(final FormulaEditorCalc formulaEditorCalc) {
        final InputStream internalResourceStream = this.resourceProvider.getInternalResourceStream("toolbar_east.ini");
        Reader reader = null;
        try {
            reader = new InputStreamReader(internalResourceStream, "ASCII");
        }
        catch (UnsupportedEncodingException ex) {}
        final WToolBar toolBar = this.toolbarFactory.newToolBar(formulaEditorCalc, "Icones/toolbar_east_lf.wbi", 1);
        toolBar.setBackground(Configuration.colorEastToolBar);
        toolBar.itemButtonModel.padding = new Insets(0, 0, 0, 0);
        toolBar.sectionPadding = new Insets(5, 6, 5, 6);
        toolBar.itemSpacing = new Insets(2, 2, 2, 2);
        toolBar.itemButtonModel.pressDeltaX = 0;
        toolBar.itemButtonModel.pressDeltaY = 0;
        synchronized (this.toolbarFactory) {
            final int zoom = this.toolbarFactory.getZoom();
            this.toolbarFactory.setZoom(100);
            this.toolbarFactory.createToolbar(reader, toolBar, null);
            this.toolbarFactory.setZoom(zoom);
        }
        if (WBackPanel.JAVA2) {
            toolBar.getSection(0).doubleBuffering = true;
        }
        return toolBar;
    }
    
    public final void receiveAndShowDocument(final InputStream inputStream) {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        String s = null;
        int int1;
        do {
            int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            switch (int1) {
                case 28:
                case 31: {
                    final byte[] array = new byte[int2];
                    dataInputStream.read(array);
                    s = new String(array);
                    continue;
                }
                default: {
                    for (int i = 0; i < int2; ++i) {
                        dataInputStream.read();
                    }
                    continue;
                }
            }
        } while (int1 != 18 && (int1 & Integer.MIN_VALUE) == 0x0);
        if (s != null) {
            this.showDocument(Utils.urlCat(Configuration.url_save_download, s));
        }
    }
    
    public final void showDocumentNewWindow(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void showDocument(final String s) {
        try {
            this.getAppletContext().showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {}
    }
    
    public final String getXML() {
        return XMLParser.string2CharData(XMLParser.opt_char2entity, XMLBoxUtils.toValidMathML(this.activeFormula.lacapsa, true));
    }
    
    public final void setLaTeX(final String s) {
        CapsaNeutraImatge.clearCache();
        this.activeFormula.clearUndoMemory();
        this.activeFormula.setString(s, false);
    }
    
    public final String getSelection() {
        return this.activeFormula.getSelection();
    }
    
    public final void setXML(final String s) {
        try {
            CapsaNeutraImatge.clearCache();
            this.activeFormula.clearUndoMemory();
            this.activeFormula.setString(s, true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new Error(t);
        }
    }
    
    public final boolean isLoading() {
        return this.activeFormula.loading();
    }
    
    public final boolean isCalculating() {
        return this.activeFormula.isCalculating();
    }
    
    public final int[] getCaret() {
        return this.activeFormula.getCaretCoordinates();
    }
    
    public final void setCaret(final int[] caretCoordinates) {
        this.activeFormula.setCaretCoordinates(caretCoordinates);
    }
    
    public final PropertyChangeSupport getPropertyChangeSupport() {
        return this.activeFormula.getPropertyChangeSupport();
    }
    
    public final void processEvent(final AWTEvent awtEvent) {
        super.processEvent(awtEvent);
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        final Object newValue = propertyChangeEvent.getNewValue();
        propertyChangeEvent.getOldValue();
        if (propertyName.equals("activeFormula")) {
            if (newValue == this.activeFormula && this.acceptTooltip != 0) {
                if (CommonOmegaPanel.dialogToolbar != null) {
                    CommonOmegaPanel.dialogToolbar.I(CommonOmegaPanel.dialogToolbar.I(), this);
                    CommonOmegaPanel.toolbar.setFormulaEditor(this.activeFormula);
                }
                if (CommonOmegaPanel.dialogToolbar2 != null) {
                    CommonOmegaPanel.dialogToolbar2.I(CommonOmegaPanel.dialogToolbar2.I(), this);
                    CommonOmegaPanel.toolbar2.setFormulaEditor(this.activeFormula);
                }
                if (CommonOmegaPanel.dialogErrors != null) {
                    CommonOmegaPanel.dialogErrors.I(CommonOmegaPanel.dialogErrors.I(), this);
                }
                this.activeFormula.requestFocus();
            }
        }
        else if (propertyName.equals("zoom")) {
            this.zoom = (int)newValue;
            if (this.acceptTooltip == 1 && CommonOmegaPanel.dialogToolbar != null) {
                CommonOmegaPanel.dialogToolbar.I.pack();
            }
            this.toolbarFactory.addNewToolTip(this.mainPane);
            if (this.applet_class) {
                final Component o = this.activeFormula.O;
                (this.activeFormula.O = this.toolbarFactory.createItem("compute", this.activeFormula, 268435457)).setLocation(o.getLocation());
                this.activeFormula.O.setVisible(o.isVisible());
                this.formulaParent.remove(o);
                this.formulaParent.add(this.activeFormula.O, WToolTipPanel.FLOATING, 0);
            }
        }
    }
    
    public final String getStringProperty(final String s, final String s2) {
        String s3;
        if (s.equals("toolbarType")) {
            s3 = this.add;
        }
        else if (s.equals("zoom")) {
            s3 = Integer.toString(this.zoom);
        }
        else if (s.equals("designMode")) {
            s3 = "" + this.activeFormula.isDesign();
        }
        else {
            s3 = this.getParameter(s);
        }
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    public final ResourceProvider getResourceProvider() {
        return this.resourceProvider;
    }
    
    public final Frame getMainFrame() {
        return Utils.getMainFrame(this);
    }
    
    public final Object getActiveFormula() {
        return this.activeFormula;
    }
    
    private final String acceptable_major_version(final String s) {
        if (s != null && this.getParent() != null && this.getParent().getClass().getName().equals("sun.applet.AppletViewerPanel")) {
            final StringBuffer sb = new StringBuffer();
            int i;
            int n = i = 0;
            while (i >= 0) {
                i = s.indexOf("&amp;", n);
                if (i >= 0) {
                    sb.append(s.substring(n, i));
                    n = i + 5;
                    sb.append('&');
                }
            }
            sb.append(s.substring(n));
            return sb.toString();
        }
        return s;
    }
    
    public final void clearAllPlotters() {
        this.activeFormula.clearAllPlotters();
    }
    
    public final void addPlotterFrameListener(final Object o) {
        this.activeFormula.getPlotterManager().addPlotterFrameListener(o);
    }
    
    public final Component getPlotterPanel(final DataInputStream dataInputStream) {
        final FormulaEditorCalc activeFormula = this.activeFormula;
        PlotterPanel plotterPanel;
        if (FormulaEditorCalc.isGraphics3D(dataInputStream)) {
            plotterPanel = new Dialog3d(dataInputStream, null, this.activeFormula, this.activeFormula.context);
        }
        else {
            plotterPanel = new PlotterPanel(new PlotCanvas(dataInputStream, null, this.activeFormula, this.activeFormula.context));
        }
        return plotterPanel;
    }
    
    public final void setLoadedPlotterPanels(final Vector loadedPLotters) {
        this.activeFormula.setLoadedPLotters(loadedPLotters);
    }
    
    public final void update(final Graphics graphics) {
        if (this.isShowing()) {
            this.paint(graphics);
        }
    }
    
    public final void addNotify() {
        if (this.getPeer() == null) {
            this.charAt = true;
        }
        super.addNotify();
    }
    
    public final Toolkit getToolkit() {
        return super.getToolkit();
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        String name;
        int style;
        int size;
        if (n == 1) {
            final Font defaultFont = this.activeFormula.getFontUtils().getDefaultFont(4, null);
            name = defaultFont.getName();
            style = defaultFont.getStyle();
            size = defaultFont.getSize();
        }
        else {
            name = null;
            style = 0;
            size = 0;
        }
        for (int i = 0; i < CommonOmegaPanel.paramColorNumbers.length; ++i) {
            final int n2 = CommonOmegaPanel.paramColorNumbers[i];
            this.activeFormula.colors[n2] = Attributes.exchangeColor(hashtable, CommonOmegaPanel.paramColorNames[2 * i], n, this.activeFormula.colors[n2], AbstractBox.default_colors[n2]);
        }
        this.zoom = Attributes.exchangeInt(hashtable, "toolbarZoom", n, this.zoom, 100);
        final Font defaultFont2 = this.activeFormula.getFontUtils().getDefaultFont(4, null);
        final String exchangeString = Attributes.exchangeString(hashtable, "font", n, name, defaultFont2.getName());
        final int exchangeInt = Attributes.exchangeInt(hashtable, "fontStyle", n, style, defaultFont2.getStyle());
        final int exchangeInt2 = Attributes.exchangeInt(hashtable, "fontSize", n, size, defaultFont2.getSize());
        if (n == 0 || n == 4097) {
            this.activeFormula.setDefaultFont(4, new Font(exchangeString, exchangeInt, exchangeInt2));
            final int zoom = this.toolbarFactory.getZoom();
            if (this.zoom != zoom) {
                this.toolbarFactory.setZoom(this.zoom);
                if (this.mainToolbar != null) {
                    this.toolbarFactory.rebuild(this.mainToolbar, this, this.mainToolbar.getOrientation());
                    if (this.activeFormula.context != null) {
                        this.activeFormula.context.propertyChange(new PropertyChangeEvent(this.mainToolbar, "zoom", new Integer(zoom), new Integer(this.zoom)));
                    }
                }
            }
        }
    }
    
    public final void addParametersToHashtable(final Hashtable hashtable) {
        for (int i = 0; i < CommonOmegaPanel.paramColorNumbers.length; i += 2) {
            final String java2 = this.JAVA2(CommonOmegaPanel.paramColorNames[i], CommonOmegaPanel.paramColorNames[i + 1]);
            if (java2 != null) {
                hashtable.put(CommonOmegaPanel.paramColorNames[i], java2);
            }
        }
        for (int j = 0; j < CommonOmegaPanel.parameters.length; ++j) {
            final String parameter = this.getParameter(CommonOmegaPanel.parameters[j]);
            if (parameter != null) {
                hashtable.put(CommonOmegaPanel.parameters[j], parameter);
            }
        }
    }
    
    public final Hashtable getProperties() {
        final Hashtable hashtable = new Hashtable();
        this.exchangeProperties(hashtable, 1);
        return hashtable;
    }
    
    public final Image getImage() {
        final Dimension size = this.getSize();
        final Image image = this.createImage(size.width, size.height);
        this.printAll(image.getGraphics());
        return image;
    }
    
    public final byte[] getImageBytes(final String s) {
        return WImage.image2Bytes(this.getImage(), s);
    }
    
    public final String getImageBase64(final String s) {
        return Utils.encode(this.getImageBytes(s));
    }
    
    public final void updatePlotterFrame() {
        this.activeFormula.getPlotterManager().updatesPlotterFrame(this.activeFormula);
    }
    
    public final void setGraphicsCreator(final Class graphicsClass) {
        IsGraphics2D.graphicsClass = graphicsClass;
    }
    
    public static final String unicode2JavaScriptBis(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
                case 39: {
                    sb.append("&apos;");
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        return XMLParser.string2CharData(XMLParser.opt_char2entity, sb.toString());
    }
    
    public final String sendReferer() {
        final URL documentBase = this.getDocumentBase();
        if (documentBase == null) {
            return "";
        }
        final URL codeBase = this.getCodeBase();
        if (codeBase == null || !codeBase.getProtocol().startsWith("http")) {
            return "";
        }
        final String string = documentBase.getProtocol() + "://" + documentBase.getHost() + documentBase.getPath();
        System.out.println(string);
        if (CommonOmegaPanel.sendRedirected.get(string) != null) {
            return "";
        }
        CommonOmegaPanel.sendRedirected.put(string, Boolean.TRUE);
        try {
            final URLConnection openConnection = new URL(codeBase, "../tick.html").openConnection();
            openConnection.setRequestProperty("Referer", OmegaSystem.document_base);
            openConnection.setRequestProperty("PRAGMA", "NO-CACHE");
            openConnection.setRequestProperty("CACHE-CONTROL", "NO-CACHE");
            final InputStream inputStream = openConnection.getInputStream();
            int i = inputStream.read();
            final StringBuffer sb = new StringBuffer();
            while (i >= 0) {
                sb.append((char)i);
                i = inputStream.read();
            }
            inputStream.close();
            return sb.toString();
        }
        catch (Throwable t) {
            System.out.println("Failed to call tick.");
            return "";
        }
    }
    
    public final void hyperlinkUpdate(final HyperlinkEvent hyperlinkEvent) {
        if (hyperlinkEvent.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
            this.getAppletContext().showDocument(hyperlinkEvent.getURL(), "_blank");
        }
    }
    
    public final URL getCodeBase() {
        return this.stub.getCodeBase();
    }
    
    public final String getParameter(final String s) {
        return this.stub.getParameter(s);
    }
    
    public final AppletContext getAppletContext() {
        return this.stub.getAppletContext();
    }
    
    public final String getAppletInfo() {
        return null;
    }
    
    public final String[][] getParameterInfo() {
        return new String[0][];
    }
    
    public static final void main(final String[] array) {
        final JFrame frame = new JFrame();
        final CommonOmegaPanel commonOmegaPanel = new CommonOmegaPanel((AppletStub)null);
        frame.getContentPane().add(commonOmegaPanel);
        commonOmegaPanel.init();
        commonOmegaPanel.start();
        frame.setSize(600, 450);
        frame.setVisible(true);
    }
    
    private static final Class acceptable_minor_version(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        CommonOmegaPanel.minor_version = 0;
        CommonOmegaPanel.major_version = 2;
        CommonOmegaPanel.acceptable_minor_version = 0;
        CommonOmegaPanel.acceptable_major_version = 0;
        CommonOmegaPanel.ACTIVATED = false;
        CommonOmegaPanel.FLOATING = false;
        CommonOmegaPanel.JAVA2 = false;
        CommonOmegaPanel.TRUE = false;
        CommonOmegaPanel.sendRedirected = new Hashtable();
        paramColorNames = new String[] { "NumberColor", "ColorNumero", "IdentColor", "ColorIdent", "SymbolColor", "ColorSimbol", "ErrorColor", "ColorError", "WarningColor", "ColorWarning", "CalcColor", "ColorCalculant", "EmptyBoxColor", "ColorCapsaBuida", "ArrowColor", "ColorFletxa", "BackgroundColor", "ColorFons", "SelectionColor", "ColorSeleccio", "CommentColor", "ColorComentari" };
        CommonOmegaPanel.paramColorNumbers = new int[] { 2, 0, 3, 7, 8, 9, 6, 5, 11, 12, 10 };
        parameters = new String[] { "toolbarZoom", "fontStyle", "fontSize", "font", "version" };
        CommonOmegaPanel.dialogToolbar = null;
        CommonOmegaPanel.dialogToolbar2 = null;
        CommonOmegaPanel.dialogErrors = null;
        CommonOmegaPanel.staticLlistaErrors = null;
        CommonOmegaPanel.addWindowListener = new Object();
        CommonOmegaPanel.append = 0;
    }
}
