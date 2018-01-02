import java.util.StringTokenizer;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaToolBarFactory extends ToolBarFactory
{
    public static final WButton ALONE_BUTTON_MODEL;
    private Vector defaultToolbarLF;
    public static final Color SYMBOL_COLOR;
    private ResourceProvider resourceProvider;
    public static final Insets DEFAULT_ITEM_INSETS;
    public static final Insets DEFAULT_TABS_INSETS;
    public static Image sponsorImage;
    public static Image sponsorOrgImage;
    private static FormulaToolBarFactory instance;
    protected static final Hashtable I;
    protected static final Vector Z;
    static Font SEPARATOR;
    private Font greekFont;
    private static final Hashtable greekCodes;
    
    protected FormulaToolBarFactory(final ResourceProvider resourceProvider) {
        this.defaultToolbarLF = null;
        this.greekFont = FormulaToolBarFactory.SEPARATOR;
        this.resourceProvider = resourceProvider;
        this.init();
    }
    
    public static final synchronized FormulaToolBarFactory getInstance(final ResourceProvider resourceProvider) {
        if (FormulaToolBarFactory.instance == null) {
            FormulaToolBarFactory.instance = new FormulaToolBarFactory(resourceProvider);
        }
        return FormulaToolBarFactory.instance;
    }
    
    public final Object clone() {
        throw new CloneNotSupportedException();
    }
    
    private void init() {
        this.setIconList(loadImages("Icones/toolbar_items.wbi", this.resourceProvider));
        try {
            final Reader itemsDef = getItemsDef(this.resourceProvider);
            if (itemsDef == null) {
                throw new FileNotFoundException("Resource file not found");
            }
            this.defineItems(itemsDef);
            itemsDef.close();
        }
        catch (Exception ex) {
            System.err.println("Error while loading items definition:" + ex.getMessage());
        }
        this.defaultToolbarLF = loadImages("Icones/toolbar_lf.wbi", this.resourceProvider);
        try {
            FormulaToolBarFactory.ALONE_BUTTON_MODEL.maskOver = new WImage(10, 10, 268435711);
            final WImage wImage = this.defaultToolbarLF.elementAt(3);
            final WImage wImage2 = this.defaultToolbarLF.elementAt(5);
            FormulaToolBarFactory.ALONE_BUTTON_MODEL.backImage = wImage.grow(wImage2.getWidth(), wImage2.getHeight(), 1, 1, wImage).paste(wImage2, 0, 0, true, null);
            FormulaToolBarFactory.ALONE_BUTTON_MODEL.backPressed = this.defaultToolbarLF.elementAt(4);
        }
        catch (ArrayIndexOutOfBoundsException ex2) {}
    }
    
    private static Vector loadImages(final String s, final ResourceProvider resourceProvider) {
        Vector list;
        try {
            final InputStream internalResourceStream = resourceProvider.getInternalResourceStream(s);
            list = WImage.readList(internalResourceStream, 500);
            internalResourceStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error while loading L&F icons");
            list = new Vector();
        }
        return list;
    }
    
    public static final Reader getItemsDef(final ResourceProvider resourceProvider) {
        try {
            final InputStream internalResourceStream = resourceProvider.getInternalResourceStream("actions_XML_" + "en" + ".txt");
            if (internalResourceStream != null) {
                return new InputStreamReader(internalResourceStream, "UTF8");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public final Component addNewItem(final WToolPane wToolPane, final String name, final int n, final Vector vector, final ActionListener actionListener) {
        Component component = null;
        if ("sponsorlogo".equals(name)) {
            if (FormulaToolBarFactory.sponsorImage != null) {
                final WBackPanel wBackPanel = new WBackPanel(new GridLayout(1, 1));
                wBackPanel.doubleBuffering = true;
                wBackPanel.add(this.createItem(name, actionListener, 1));
                wToolPane.add(wBackPanel, WToolPane.SEPARATOR);
                return wBackPanel;
            }
            return null;
        }
        else {
            if (actionListener instanceof Controller) {
                final Controller controller = (Controller)actionListener;
                if ((component = this.createFormatComp(wToolPane, name, controller)) == null && (component = this.createUnitComp(name, controller)) == null && (component = this.createGreekComp(name, controller)) == null) {
                    component = null;
                }
            }
            if (component == null) {
                return super.addNewItem(wToolPane, name, n, vector, actionListener);
            }
            component.setName(name);
            wToolPane.add(component);
            return component;
        }
    }
    
    public final WToolPane addNewSection(final WToolBar wToolBar, final String name, final int n) {
        WToolPane addNewSection;
        if ("S_sponsor".equals(name)) {
            addNewSection = new WToolPane(n, wToolBar.getOrientation(), wToolBar.itemButtonModel);
            addNewSection.setName(name);
            WImage wImage = wToolBar.getImage(18);
            if (wImage == null) {
                wImage = wToolBar.getImage(2);
            }
            addNewSection.setBgImage(wImage, 0);
            addNewSection.sepImage = wToolBar.getImage(0);
            wToolBar.add(addNewSection, "East");
        }
        else {
            addNewSection = super.addNewSection(wToolBar, name, n);
        }
        return addNewSection;
    }
    
    public final synchronized WToolBar createToolbar(final Reader reader, WToolBar toolbar, ActionListener controller) {
        if (toolbar != null && toolbar instanceof WEditorToolBar) {
            controller = ((WEditorToolBar)toolbar).getController();
        }
        this.greekFont = new Font(FormulaToolBarFactory.SEPARATOR.getName(), FormulaToolBarFactory.SEPARATOR.getStyle(), FormulaToolBarFactory.SEPARATOR.getSize() * this.getZoom() / 100);
        toolbar = super.createToolbar(reader, toolbar, controller);
        if (toolbar instanceof WEditorToolBar) {
            final Controller controller2 = ((WEditorToolBar)toolbar).getController();
            final FormulaEditorCalc i = controller2.I;
            controller2.I(null);
        }
        return toolbar;
    }
    
    public final Component createItem(final String actionCommand, final ActionListener actionListener, int n) {
        boolean b = false;
        if ((n & 0x10000000) != 0x0) {
            b = true;
            n &= 0xEFFFFFFF;
        }
        if (actionCommand.equals("tooltip")) {
            final WButton wButton = (WButton)super.createItem(actionCommand, null, 0);
            if (19 < this.defaultToolbarLF.size()) {
                wButton.backImage = this.defaultToolbarLF.elementAt(19);
            }
            wButton.padding.right = 4;
            final WToolTip wToolTip = new WToolTip(wButton);
            wToolTip.setFont(this.getFont());
            wToolTip.setBackground(Configuration.colorTooltip);
            return wToolTip;
        }
        Component item;
        if (actionCommand.startsWith("sponsorlogo")) {
            final WButton wButton2 = (WButton)super.createItem(actionCommand, actionListener, 1);
            wButton2.padding = new Insets(0, 0, 0, 0);
            wButton2.pressDeltaX = 0;
            wButton2.pressDeltaY = 0;
            wButton2.setCursor(new Cursor(12));
            wButton2.tooltipText = "http://www.wiris.com/";
            item = wButton2;
        }
        else {
            item = super.createItem(actionCommand, actionListener, n);
            if (actionCommand.startsWith("pr_")) {
                item.setForeground(FormulaToolBarFactory.SYMBOL_COLOR);
            }
        }
        if (b) {
            WToolPane.applyModel(FormulaToolBarFactory.ALONE_BUTTON_MODEL, item);
            if (actionCommand.equals("compute")) {
                final WBackPanel wBackPanel = new WBackPanel(new GridLayout(1, 1));
                ((WButton)item).setActionCommand(actionCommand);
                wBackPanel.add(item);
                item = wBackPanel;
            }
            item.setFont(this.getFont());
            item.setSize(item.getPreferredSize());
        }
        return item;
    }
    
    public final WImage getIcon(final String s) {
        if ("sponsorlogo".equals(s)) {
            return WImage.create(FormulaToolBarFactory.sponsorImage, -1, -1);
        }
        if ("sponsorlogo_org".equals(s)) {
            return WImage.create(FormulaToolBarFactory.sponsorOrgImage, -1, -1);
        }
        return super.getIcon(s);
    }
    
    public final String getToolTip(final String s) {
        return super.getToolTip(s);
    }
    
    public final String getCommand(final String s) {
        final String s2 = super.C.get(s);
        if (s2 != null) {
            return "insert: " + s2;
        }
        if (s.equals("big_compute")) {
            return "compute";
        }
        return s;
    }
    
    public final WToolBar newToolBar(final FormulaEditorCalc formulaEditorCalc, final String s, final int n) {
        final WEditorToolBar wEditorToolBar = new WEditorToolBar(formulaEditorCalc, this, n);
        this.initToolbar(wEditorToolBar, s);
        return wEditorToolBar;
    }
    
    public final WToolBar newToolBar(final String s, final int n) {
        final WToolBar wToolBar = new WToolBar(n);
        this.initToolbar(wToolBar, s);
        return wToolBar;
    }
    
    private void initToolbar(final WToolBar wToolBar, final String s) {
        wToolBar.getSelector().padding = new Insets(0, 0, 0, 0);
        wToolBar.getSelector().itemMargin = new Insets(0, 0, 0, 0);
        wToolBar.selectButtonModel.padding = FormulaToolBarFactory.DEFAULT_TABS_INSETS;
        wToolBar.selectButtonModel.pressDeltaX = 0;
        wToolBar.selectButtonModel.pressDeltaY = -1;
        wToolBar.itemButtonModel.padding = FormulaToolBarFactory.DEFAULT_ITEM_INSETS;
        Vector imagesList;
        if (s == null) {
            imagesList = this.defaultToolbarLF;
        }
        else {
            imagesList = loadImages(s, this.resourceProvider);
        }
        wToolBar.setImagesList(imagesList);
        this.I(wToolBar);
    }
    
    protected final void I(final WToolBar wToolBar) {
        final WToolTip addNewToolTip = this.addNewToolTip(wToolBar);
        final WImage image = wToolBar.getImage(19);
        if (image != null) {
            addNewToolTip.content.backImage = image;
        }
    }
    
    public final WToolTip addNewToolTip(final WToolContainer wToolContainer) {
        final WToolTip toolTipComp = wToolContainer.getToolTipComp();
        if (toolTipComp != null) {
            toolTipComp.disposeToolTip();
        }
        final WToolTip toolTipComp2 = (WToolTip)this.createItem("tooltip", null, 0);
        wToolContainer.setToolTipComp(toolTipComp2);
        return toolTipComp2;
    }
    
    private Component createUnitComp(final String s, final Controller controller) {
        if (s.equals("unitprefixes")) {
            if (controller.Z == null) {
                controller.Z = WList.newChoice();
                for (int i = 0; i < FormulaToolBarFactory.Z.size(); ++i) {
                    final String s2 = FormulaToolBarFactory.I.get(FormulaToolBarFactory.Z.elementAt(i));
                    String s3;
                    if (s2.endsWith("null")) {
                        s3 = " ";
                    }
                    else {
                        s3 = ((Hashtable<K, String>)CapsaUnitat.translate).get(s2);
                    }
                    controller.Z.add(s3);
                }
                controller.Z.setSelectedItem(" ", false);
            }
            return controller.Z;
        }
        final int index = s.indexOf(95);
        final String substring = s.substring(index + 1);
        if (FormulaToolBarFactory.I.containsKey(substring)) {
            final String s4 = FormulaToolBarFactory.I.get(substring);
            String string = NeuterBox.name2string.get(s4);
            String s5 = CapsaUnitat.getUnitName(s4);
            String string2;
            if (index >= 1) {
                final String substring2 = s.substring(0, index);
                if (!FormulaToolBarFactory.Z.contains(substring2)) {
                    return null;
                }
                final String s6 = FormulaToolBarFactory.I.get(substring2);
                s5 = CapsaUnitat.getUnitName(s6) + s5;
                string = (String)NeuterBox.name2string.get(s6) + string;
                string2 = "insert:" + this.I(s6, s4);
            }
            else {
                if (FormulaToolBarFactory.Z.contains(substring)) {
                    return null;
                }
                string2 = "__unit__";
            }
            final WButton wButton = new WButton(this.getIcon(s), string);
            wButton.setActionCommand(string2);
            wButton.addActionListener(controller);
            wButton.setForeground(FormulaToolBarFactory.SYMBOL_COLOR);
            if (s5 != null) {
                wButton.tooltipText = ToolBarFactory.formatToolTip(s5.toLowerCase());
            }
            return wButton;
        }
        return null;
    }
    
    protected final String I(final String s, final String s2) {
        final String s3 = MathML2BoxCalc.special2csymbol.get(s2);
        String string = NeuterBox.name2string.get(s2);
        String s4 = "<csymbol definitionURL='http://.../units/" + s3;
        if (s != null && !"null".equals(s)) {
            string = NeuterBox.name2string.get(s) + string;
            s4 = s4 + "#" + MathML2BoxCalc.special2csymbol.get(s);
        }
        return s4 + "'>" + XMLParser.string2CharData(string) + "</csymbol>";
    }
    
    private Component createGreekComp(final String s, final ActionListener actionListener) {
        if (FormulaToolBarFactory.greekCodes.containsKey(s)) {
            final String s2 = FormulaToolBarFactory.greekCodes.get(s);
            final WButton wButton = new WButton(null, s2);
            wButton.setFont(this.greekFont);
            wButton.setActionCommand("insert: <mi>" + s2 + "</mi>");
            wButton.addActionListener(actionListener);
            return wButton;
        }
        return null;
    }
    
    private Component createFormatComp(final WToolPane wToolPane, final String s, final Controller controller) {
        if (s.equals("font")) {
            final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
            controller.C = WList.newChoice();
            for (int i = 0; i < fontList.length; ++i) {
                controller.C.add(fontList[i]);
            }
            controller.C.add("Verdana");
            controller.C.addItemListener(controller);
            return controller.C;
        }
        if (s.equals("fontsize")) {
            controller.B = WList.newChoice();
            int j;
            for (j = 8; j < 16; j += 2) {
                controller.B.add(String.valueOf(j));
            }
            while (j < 24) {
                controller.B.add(String.valueOf(j));
                j += 4;
            }
            while (j < 40) {
                controller.B.add(String.valueOf(j));
                j += 4;
            }
            while (j < 100) {
                controller.B.add(String.valueOf(j));
                j += 10;
            }
            controller.B.addItemListener(controller);
            return controller.B;
        }
        if (s.equals("boldstyle")) {
            return controller.S = (WButton)super.createItem(s, controller, 2);
        }
        if (s.equals("italicstyle")) {
            return controller.A = (WButton)super.createItem(s, controller, 2);
        }
        if (s.equals("design")) {
            (controller.J = new WButton(null, "Dise\ufffdo", 2)).addActionListener(controller);
            return controller.J;
        }
        if (s.equals("localcomment")) {
            (controller.E = new WButton(null, s, 2)).addActionListener(controller);
            return controller.E;
        }
        if (s.equals("applystyleto")) {
            final WBackPanel wBackPanel = new WBackPanel(new FlowLayout(0, 0, 0));
            (controller.F = WList.newChoice()).add("Default");
            controller.F.add("Selection");
            controller.F.addItemListener(controller);
            wBackPanel.add(new WButton(null, "Applies to", 0));
            wBackPanel.add(controller.F);
            return wBackPanel;
        }
        if (s.equals("iconszoom")) {
            final WBackPanel wBackPanel2 = new WBackPanel(new FlowLayout(0, 0, 0));
            final WButton wButton = new WButton(null, "Icon zoom", 0);
            wButton.padding.right = 3;
            wBackPanel2.add(wButton);
            (controller.D = WList.newChoice()).add("100%");
            controller.D.add("125%");
            controller.D.add("150%");
            controller.D.add("200%");
            controller.D.setSelectedItem(String.valueOf(this.getZoom()) + "%", false);
            controller.D.addItemListener(controller);
            wBackPanel2.add(controller.D);
            return wBackPanel2;
        }
        if (s.equals("colors")) {
            final WButton wButton2 = (WButton)this.createItem(s, controller, 1);
            wButton2.setActionCommand(null);
            return wButton2;
        }
        return null;
    }
    
    static {
        ALONE_BUTTON_MODEL = new WButton(null, null);
        SYMBOL_COLOR = new Color(0, 0, 200);
        DEFAULT_ITEM_INSETS = new Insets(3, 4, 3, 4);
        DEFAULT_TABS_INSETS = new Insets(3, 5, 3, 5);
        FormulaToolBarFactory.instance = null;
        I = new Hashtable();
        Z = new Vector(32);
        Utils.loadBinHashtable("m@\\SImeter@g@\\SIgram@s@\\SIsecond@A@\\SIampere@K@\\SIkelvin@mol@\\SImole@cd@\\SIcandela@l@\\SIliter@rad@\\SIradian@sr@\\SIsteradian@Hz@\\SIhertz@N@\\SInewton@Pa@\\SIpascal@J@\\SIjoule@W@\\SIwatt@C@\\SIcoulomb@V@\\SIvolt@F@\\SIfarad@ohm@\\SIohm@S@\\SIsiemens@Wb@\\SIweber@T@\\SItesla@H@\\SIhenry@lm@\\SIlumen@lx@\\SIlux@Bq@\\SIbecquerel@Gy@\\SIgray@Sv@\\SIsievert@kat@\\SIkatal@min@\\SIminute@h@\\SIhour@b@\\SIbar@", FormulaToolBarFactory.I, null);
        final StringTokenizer stringTokenizer = new StringTokenizer("yocto@\\SIyocto@zepto@\\SIzepto@atto@\\SIatto@femto@\\SIfemto@pico@\\SIpico@nano@\\SInano@micro@\\SImicro@milli@\\SImilli@centi@\\SIcenti@deci@\\SIdeci@ @null@deka@\\SIdeka@hecto@\\SIhecto@kilo@\\SIkilo@mega@\\SImega@giga@\\SIgiga@tera@\\SItera@peta@\\SIpeta@exa@\\SIexa@zetta@\\SIzetta@yotta@\\SIyotta@", "@");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            FormulaToolBarFactory.Z.addElement(nextToken);
            FormulaToolBarFactory.I.put(nextToken, nextToken2);
        }
        FormulaToolBarFactory.SEPARATOR = new Font("Serif", 0, 12);
        greekCodes = new Hashtable();
        final StringTokenizer stringTokenizer2 = new StringTokenizer("alpha#\u03b1#beta#\u03b2#gamma#\u03b3#delta#\u03b4#epsilon#\u03b5#zeta#\u03b6#eta#\u03b7#theta#\u03b8#iota#\u03b9#kappa#\u03ba#lambda#\u03bb#mu#\u03bc#nu#\u03bd#xi#\u03be#pi#\u03c0#rho#\u03c1#sigma#\u03c3#tau#\u03c4#upsilon#\u03c5#phi#\u03c6#chi#\u03c7#psi#\u03c8#omega#\u03c9#ALPHA#\u0391#BETA#\u0392#GAMMA#\u0393#DELTA#\u0394#EPSILON#\u0395#ZETA#\u0396#ETA#\u0397#THETA#\u0398#IOTA#\u0399#KAPPA#\u039a#LAMBDA#\u039b#MU#\u039c#NU#\u039d#XI#\u039e#PI#\u03a0#RHO#\u03a1#SIGMA#\u03a3#TAU#\u03a4#UPSILON#\u03a5#PHI#\u03a6#CHI#\u03a7#PSI#\u03a8#OMEGA#\u03a9#", "#");
        while (stringTokenizer2.hasMoreTokens()) {
            FormulaToolBarFactory.greekCodes.put(stringTokenizer2.nextToken(), stringTokenizer2.nextToken());
        }
    }
}
