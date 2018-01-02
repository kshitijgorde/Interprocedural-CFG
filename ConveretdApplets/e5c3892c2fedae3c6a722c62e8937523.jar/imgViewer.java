import javax.swing.AbstractAction;
import java.awt.print.PrinterException;
import java.awt.Graphics2D;
import javax.swing.RepaintManager;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.print.Printable;
import java.util.Observer;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class imgViewer extends JApplet implements ActionListener, MouseListener, FocusListener, Observer, Printable
{
    public SensorMenu sensorMenu;
    public JFrame mainWindow;
    public ImagePane imgArea;
    public MosaicData md;
    public SceneListPanel sceneListPanel;
    public JScrollPane imgScroll;
    public JScrollPane locatorMapScroll;
    public CloudCoverLimit maxCC;
    private JPanel ccPanel;
    private CardLayout ccLayout;
    public NavigateDate navDate;
    private JPanel datePanel;
    private CardLayout dateLayout;
    public SearchForSceneDialog searchForSceneDialog;
    public SearchLimitDialog searchLimitDialog;
    public SceneListDialog sceneListDialog;
    public HideSceneDialog hideSceneDialog;
    public UserDefinedAreaDialog userDefinedAreaDialog;
    public PointOfInterestDialog pointOfInterestDialog;
    public SearchForAddressDialog searchForAddressDialog;
    public NDVIGraphDialog ndviGraphDialog;
    public StatusBar statusBar;
    public Font normalFont;
    public Font boldFont;
    public Font smallFont;
    public Font largeBoldFont;
    private ArrowPane controls;
    private JButton dateIncButton;
    private JButton dateDecButton;
    private GridCellEntry gridCellEntry;
    private LatLongEntry latLongEntry;
    private LocatorMap locatorMap;
    private SceneInfo sceneInfo;
    public Cursor crosshairCursor;
    public Cursor moveCursor;
    public Cursor handCursor;
    private Cursor waitCursor;
    private Cursor defaultCursor;
    public MapLayerMenu mapLayerMenu;
    public ResolutionMenu resolutionMenu;
    public HelpMenu helpMenu;
    public ToolsMenu toolsMenu;
    public FileMenu fileMenu;
    public boolean showingWait;
    private boolean showingBusy;
    public boolean slowdown;
    public boolean verboseOutput;
    private boolean useMainWindowForDialogs;
    public boolean usingIE;
    public boolean grantedPrivileges;
    private boolean initialized;
    private boolean limitCollectionMenu;
    private boolean popOut;
    
    public imgViewer() {
        this.slowdown = false;
        this.verboseOutput = false;
        this.useMainWindowForDialogs = false;
        this.usingIE = false;
        this.grantedPrivileges = false;
        this.initialized = false;
        this.limitCollectionMenu = false;
        this.popOut = false;
    }
    
    @Override
    public void init() {
        final int n = 650;
        final int n2 = 650;
        String s = "SansSerif";
        try {
            if (System.getProperty("os.name").equals("Linux")) {
                s = "Serif";
                this.useMainWindowForDialogs = true;
            }
            final String parameter = this.getParameter("browserIsIE");
            this.usingIE = false;
            if (parameter.equals("true")) {
                this.usingIE = true;
            }
        }
        catch (Exception ex) {}
        this.normalFont = new Font(s, 0, 12);
        this.boldFont = new Font(s, 1, 12);
        this.smallFont = new Font(s, 0, 10);
        this.largeBoldFont = new Font(s, 1, 18);
        this.getContentPane().setBackground(Color.WHITE);
        this.getContentPane().setFont(this.normalFont);
        try {
            System.getProperty("user.home");
            this.grantedPrivileges = true;
        }
        catch (Exception ex2) {}
        final String parameter2 = this.getParameter("sensor");
        SensorStatus sensorStatus;
        if (parameter2.equals("ASTERVNIR") || parameter2.equals("ASTERL2V")) {
            sensorStatus = SensorStatus.ASTER_VNIR;
        }
        else if (parameter2.equals("ASTERTIR")) {
            sensorStatus = SensorStatus.ASTER_TIR;
        }
        else if (parameter2.equals("ASTER_VNIR_DATAPOOL")) {
            sensorStatus = SensorStatus.ASTER_VNIR_DATAPOOL;
        }
        else if (parameter2.equals("ASTER_TIR_DATAPOOL")) {
            sensorStatus = SensorStatus.ASTER_TIR_DATAPOOL;
        }
        else if (parameter2.equals("TM")) {
            sensorStatus = SensorStatus.LANDSAT_TM;
        }
        else if (parameter2.equals("ETM")) {
            sensorStatus = SensorStatus.LANDSAT_ETM;
        }
        else if (parameter2.equals("4_5MSS")) {
            sensorStatus = SensorStatus.LANDSAT_4_5MSS;
        }
        else if (parameter2.equals("1_3MSS")) {
            sensorStatus = SensorStatus.LANDSAT_1_3MSS;
        }
        else if (parameter2.equals("COMBINED")) {
            sensorStatus = SensorStatus.LANDSAT_COMBINED;
        }
        else if (parameter2.equals("ORTHOTM")) {
            sensorStatus = SensorStatus.ORTHO_TM;
        }
        else if (parameter2.equals("ORTHOETM")) {
            sensorStatus = SensorStatus.ORTHO_ETM_PLUS;
        }
        else if (parameter2.equals("PANSHARPETM")) {
            sensorStatus = SensorStatus.PANSHARP_ETM_PLUS;
        }
        else if (parameter2.equals("ORTHO1_3MSS")) {
            sensorStatus = SensorStatus.ORTHO1_3MSS;
        }
        else if (parameter2.equals("ORTHO4_5MSS")) {
            sensorStatus = SensorStatus.ORTHO4_5MSS;
        }
        else if (parameter2.equals("MRLC_2001TC")) {
            sensorStatus = SensorStatus.MRLC_2001TC;
        }
        else if (parameter2.equals("MRLC_2001RA")) {
            sensorStatus = SensorStatus.MRLC_2001RA;
        }
        else if (parameter2.equals("SLCOFF")) {
            sensorStatus = SensorStatus.LANDSAT_ETM_SLC_OFF;
        }
        else if (parameter2.equals("MYD09A1")) {
            sensorStatus = SensorStatus.MODIS_MYD09A1;
        }
        else if (parameter2.equals("MYD09GA")) {
            sensorStatus = SensorStatus.MODIS_MYD09GA;
        }
        else if (parameter2.equals("MYD09GQ")) {
            sensorStatus = SensorStatus.MODIS_MYD09GQ;
        }
        else if (parameter2.equals("MYD09Q1")) {
            sensorStatus = SensorStatus.MODIS_MYD09Q1;
        }
        else if (parameter2.equals("MYD11A1DAY")) {
            sensorStatus = SensorStatus.MODIS_MYD11A1_DAY;
        }
        else if (parameter2.equals("MYD11A1NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MYD11A1_NIGHT;
        }
        else if (parameter2.equals("MYD11A2DAY")) {
            sensorStatus = SensorStatus.MODIS_MYD11A2_DAY;
        }
        else if (parameter2.equals("MYD11A2NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MYD11A2_NIGHT;
        }
        else if (parameter2.equals("MYD11B1DAY")) {
            sensorStatus = SensorStatus.MODIS_MYD11B1_DAY;
        }
        else if (parameter2.equals("MYD11B1NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MYD11B1_NIGHT;
        }
        else if (parameter2.equals("MYD13A1EVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A1_EVI;
        }
        else if (parameter2.equals("MYD13A1NDVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A1_NDVI;
        }
        else if (parameter2.equals("MYD13A2EVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A2_EVI;
        }
        else if (parameter2.equals("MYD13A2NDVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A2_NDVI;
        }
        else if (parameter2.equals("MYD13A3EVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A3_EVI;
        }
        else if (parameter2.equals("MYD13A3NDVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13A3_NDVI;
        }
        else if (parameter2.equals("MYD13Q1EVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13Q1_EVI;
        }
        else if (parameter2.equals("MYD13Q1NDVI")) {
            sensorStatus = SensorStatus.MODIS_MYD13Q1_NDVI;
        }
        else if (parameter2.equals("MYD14A1")) {
            sensorStatus = SensorStatus.MODIS_MYD14A1;
        }
        else if (parameter2.equals("MYD14A2")) {
            sensorStatus = SensorStatus.MODIS_MYD14A2;
        }
        else if (parameter2.equals("MYD15A2FPAR")) {
            sensorStatus = SensorStatus.MODIS_MYD15A2_FPAR;
        }
        else if (parameter2.equals("MYD15A2LAI")) {
            sensorStatus = SensorStatus.MODIS_MYD15A2_LAI;
        }
        else if (parameter2.equals("MYD17A2GPP")) {
            sensorStatus = SensorStatus.MODIS_MYD17A2_GPP;
        }
        else if (parameter2.equals("MYD17A2NETPSN")) {
            sensorStatus = SensorStatus.MODIS_MYD17A2_NETPSN;
        }
        else if (parameter2.equals("MOD09A1")) {
            sensorStatus = SensorStatus.MODIS_MOD09A1;
        }
        else if (parameter2.equals("MOD09GA")) {
            sensorStatus = SensorStatus.MODIS_MOD09GA;
        }
        else if (parameter2.equals("MOD09GQ")) {
            sensorStatus = SensorStatus.MODIS_MOD09GQ;
        }
        else if (parameter2.equals("MOD09Q1")) {
            sensorStatus = SensorStatus.MODIS_MOD09Q1;
        }
        else if (parameter2.equals("MOD11A1DAY")) {
            sensorStatus = SensorStatus.MODIS_MOD11A1_DAY;
        }
        else if (parameter2.equals("MOD11A1NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MOD11A1_NIGHT;
        }
        else if (parameter2.equals("MOD11A2DAY")) {
            sensorStatus = SensorStatus.MODIS_MOD11A2_DAY;
        }
        else if (parameter2.equals("MOD11A2NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MOD11A2_NIGHT;
        }
        else if (parameter2.equals("MOD11B1DAY")) {
            sensorStatus = SensorStatus.MODIS_MOD11B1_DAY;
        }
        else if (parameter2.equals("MOD11B1NIGHT")) {
            sensorStatus = SensorStatus.MODIS_MOD11B1_NIGHT;
        }
        else if (parameter2.equals("MOD13A1EVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A1_EVI;
        }
        else if (parameter2.equals("MOD13A1NDVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A1_NDVI;
        }
        else if (parameter2.equals("MOD13A3EVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A3_EVI;
        }
        else if (parameter2.equals("MOD13A3NDVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A3_NDVI;
        }
        else if (parameter2.equals("MOD13A2EVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A2_EVI;
        }
        else if (parameter2.equals("MOD13A2NDVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13A2_NDVI;
        }
        else if (parameter2.equals("MOD13Q1EVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13Q1_EVI;
        }
        else if (parameter2.equals("MOD13Q1NDVI")) {
            sensorStatus = SensorStatus.MODIS_MOD13Q1_NDVI;
        }
        else if (parameter2.equals("MOD14A1")) {
            sensorStatus = SensorStatus.MODIS_MOD14A1;
        }
        else if (parameter2.equals("MOD14A2")) {
            sensorStatus = SensorStatus.MODIS_MOD14A2;
        }
        else if (parameter2.equals("MOD15A2FPAR")) {
            sensorStatus = SensorStatus.MODIS_MOD15A2_FPAR;
        }
        else if (parameter2.equals("MOD15A2LAI")) {
            sensorStatus = SensorStatus.MODIS_MOD15A2_LAI;
        }
        else if (parameter2.equals("MOD17A2GPP")) {
            sensorStatus = SensorStatus.MODIS_MOD17A2_GPP;
        }
        else if (parameter2.equals("MOD17A2NETPSN")) {
            sensorStatus = SensorStatus.MODIS_MOD17A2_NETPSN;
        }
        else if (parameter2.equals("MOD43B1")) {
            sensorStatus = SensorStatus.MODIS_MOD43B1;
        }
        else if (parameter2.equals("MOD43B3")) {
            sensorStatus = SensorStatus.MODIS_MOD43B3;
        }
        else if (parameter2.equals("MOD43B4")) {
            sensorStatus = SensorStatus.MODIS_MOD43B4;
        }
        else if (parameter2.equals("MCD15A2FPAR")) {
            sensorStatus = SensorStatus.MODIS_MCD15A2_FPAR;
        }
        else if (parameter2.equals("MCD15A2LAI")) {
            sensorStatus = SensorStatus.MODIS_MCD15A2_LAI;
        }
        else if (parameter2.equals("MCD43A1")) {
            sensorStatus = SensorStatus.MODIS_MCD43A1;
        }
        else if (parameter2.equals("MCD43A2")) {
            sensorStatus = SensorStatus.MODIS_MCD43A2;
        }
        else if (parameter2.equals("MCD43A3")) {
            sensorStatus = SensorStatus.MODIS_MCD43A3;
        }
        else if (parameter2.equals("MCD43A4")) {
            sensorStatus = SensorStatus.MODIS_MCD43A4;
        }
        else if (parameter2.equals("MCD43B1")) {
            sensorStatus = SensorStatus.MODIS_MCD43B1;
        }
        else if (parameter2.equals("MCD43B2")) {
            sensorStatus = SensorStatus.MODIS_MCD43B2;
        }
        else if (parameter2.equals("MCD43B3")) {
            sensorStatus = SensorStatus.MODIS_MCD43B3;
        }
        else if (parameter2.equals("MCD43B4")) {
            sensorStatus = SensorStatus.MODIS_MCD43B4;
        }
        else if (parameter2.equals("ALI")) {
            sensorStatus = SensorStatus.EO1_ALI;
        }
        else if (parameter2.equals("HYP")) {
            sensorStatus = SensorStatus.EO1_HYP;
        }
        else if (parameter2.equals("TERRALOOK_ASTERVNIR")) {
            sensorStatus = SensorStatus.TERRALOOK_ASTER_VNIR;
        }
        else if (parameter2.equals("TERRALOOK_GLS2005")) {
            sensorStatus = SensorStatus.TERRALOOK_GLS2005;
        }
        else if (parameter2.equals("TERRALOOK_GLS2000")) {
            sensorStatus = SensorStatus.TERRALOOK_GLS2000;
        }
        else if (parameter2.equals("TERRALOOK_GLS1990")) {
            sensorStatus = SensorStatus.TERRALOOK_GLS1990;
        }
        else if (parameter2.equals("TERRALOOK_GLS1975_L4_5")) {
            sensorStatus = SensorStatus.TERRALOOK_GLS1975_L4_5;
        }
        else if (parameter2.equals("TERRALOOK_GLS1975_L1_3")) {
            sensorStatus = SensorStatus.TERRALOOK_GLS1975_L1_3;
        }
        else if (parameter2.equals("NAPP")) {
            sensorStatus = SensorStatus.NAPP;
        }
        else if (parameter2.equals("NALC")) {
            sensorStatus = SensorStatus.NALC;
        }
        else if (parameter2.equals("ETM_MOSAIC")) {
            sensorStatus = SensorStatus.ETM_MOSAIC;
        }
        else if (parameter2.equals("TM_MOSAIC")) {
            sensorStatus = SensorStatus.TM_MOSAIC;
        }
        else if (parameter2.equals("NHAP_BW")) {
            sensorStatus = SensorStatus.NHAP_BW;
        }
        else if (parameter2.equals("NHAP_CIR")) {
            sensorStatus = SensorStatus.NHAP_CIR;
        }
        else if (parameter2.equals("GLS2010")) {
            sensorStatus = SensorStatus.GLS2010;
        }
        else if (parameter2.equals("GLS2005")) {
            sensorStatus = SensorStatus.GLS2005;
        }
        else if (parameter2.equals("GLS2005_EO1")) {
            sensorStatus = SensorStatus.GLS2005_EO1;
        }
        else if (parameter2.equals("GLS2000")) {
            sensorStatus = SensorStatus.GLS2000;
        }
        else if (parameter2.equals("GLS1990")) {
            sensorStatus = SensorStatus.GLS1990;
        }
        else if (parameter2.equals("GLS1975_4_5MSS")) {
            sensorStatus = SensorStatus.GLS1975_4_5MSS;
        }
        else if (parameter2.equals("GLS1975_1_3MSS")) {
            sensorStatus = SensorStatus.GLS1975_1_3MSS;
        }
        else if (parameter2.equals("SYSTEMATIC_L1G")) {
            sensorStatus = SensorStatus.SYSTEMATIC_L1G;
        }
        else {
            sensorStatus = SensorStatus.LANDSAT_ETM_SLC_OFF;
        }
        try {
            final String parameter3 = this.getParameter("popout");
            if (parameter3.equalsIgnoreCase("TRUE") || parameter3.equalsIgnoreCase("YES")) {
                this.popOut = true;
            }
        }
        catch (Exception ex3) {}
        if (this.popOut) {
            final JFrame mainWindow = new JFrame("USGS Global Visualization Viewer");
            mainWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent windowEvent) {
                    mainWindow.setVisible(false);
                }
            });
            (this.mainWindow = mainWindow).setIconImage(this.getImage(this.getCodeBase(), "graphics/glovis.gif"));
            this.mainWindow.setFont(this.normalFont);
            this.showAppletMessage(s);
            this.setVisible(true);
            this.mainWindow.setLocation(this.getLocationOnScreen());
        }
        else {
            this.getContentPane().setFont(this.normalFont);
            this.getContentPane().setFocusable(true);
            this.getContentPane().addFocusListener(this);
        }
        this.crosshairCursor = new Cursor(1);
        this.handCursor = new Cursor(12);
        this.moveCursor = new Cursor(13);
        this.waitCursor = new Cursor(3);
        this.defaultCursor = new Cursor(0);
        (this.statusBar = new StatusBar(this)).showStatus("USGS Browse Image Viewer Applet");
        try {
            final String parameter4 = this.getParameter("limitMissions");
            if (parameter4.equalsIgnoreCase("TRUE") || parameter4.equalsIgnoreCase("YES")) {
                this.limitCollectionMenu = true;
            }
        }
        catch (Exception ex4) {}
        this.sensorMenu = new SensorMenu(this, sensorStatus, this.limitCollectionMenu);
        (this.locatorMap = new LocatorMap(this)).setCursor(this.crosshairCursor);
        (this.locatorMapScroll = new JScrollPane(this.locatorMap, 22, 32)).setBackground(Color.LIGHT_GRAY);
        this.locatorMapScroll.getHorizontalScrollBar().setUnitIncrement(10);
        this.locatorMapScroll.getVerticalScrollBar().setUnitIncrement(10);
        this.locatorMap.setFocusable(false);
        this.locatorMapScroll.setFocusable(false);
        this.locatorMapScroll.setMaximumSize(new Dimension(240, 400));
        this.locatorMapScroll.setMinimumSize(new Dimension(240, 100));
        this.locatorMapScroll.setPreferredSize(new Dimension(240, 240));
        (this.imgArea = new ImagePane(this, this.locatorMap)).setBackground(Color.BLACK);
        (this.imgScroll = new JScrollPane(this.imgArea, 20, 30)).setSize(n, n2);
        this.imgScroll.setBackground(Color.LIGHT_GRAY);
        this.imgScroll.getHorizontalScrollBar().setUnitIncrement(10);
        this.imgScroll.getVerticalScrollBar().setUnitIncrement(10);
        this.imgScroll.getViewport().setScrollMode(0);
        this.md = this.imgArea.md;
        this.statusBar.progress.addWorker(this.md);
        this.statusBar.progress.addWorker(this.md.imageLoader);
        this.statusBar.progress.addWorker(this.md.mapLayers);
        this.userDefinedAreaDialog = new UserDefinedAreaDialog(this.getDialogParent(), this, this.md);
        this.pointOfInterestDialog = new PointOfInterestDialog(this.getDialogParent(), this, this.md, this.md.mapLayers.getPointOfInterestMapLayer());
        this.gridCellEntry = new GridCellEntry(this, this.md);
        this.latLongEntry = new LatLongEntry(this, this.md);
        this.maxCC = new CloudCoverLimit(this, this.md);
        this.controls = new ArrowPane(this);
        this.sceneInfo = new SceneInfo(this, this.md);
        this.navDate = new NavigateDate(this, this.md);
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        (this.dateDecButton = new JButton("Prev Scene")).setMnemonic(80);
        this.dateDecButton.setToolTipText("Previous scene");
        this.dateDecButton.addActionListener(this);
        (this.dateIncButton = new JButton("Next Scene")).setMnemonic(78);
        this.dateIncButton.setToolTipText("Next scene");
        this.dateIncButton.addActionListener(this);
        panel.add(this.dateDecButton);
        panel.add(this.dateIncButton);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.add(this.navDate);
        panel2.add(Box.createVerticalStrut(3));
        panel2.add(panel);
        this.datePanel = new JPanel();
        this.dateLayout = new CardLayout();
        this.datePanel.setLayout(this.dateLayout);
        this.datePanel.add("visible", panel2);
        this.datePanel.add("notVisible", new JPanel());
        final Dimension preferredSize = this.datePanel.getPreferredSize();
        preferredSize.width = 100;
        this.datePanel.setMinimumSize(preferredSize);
        preferredSize.width = 240;
        this.datePanel.setMaximumSize(preferredSize);
        this.sceneListPanel = new SceneListPanel(this);
        final JMenuBar menuBar = new JMenuBar();
        if (this.popOut) {
            this.mainWindow.setJMenuBar(menuBar);
        }
        else {
            this.setJMenuBar(menuBar);
        }
        this.sensorMenu.setToolTipText("Collection menu");
        menuBar.add(this.sensorMenu);
        (this.resolutionMenu = new ResolutionMenu(this, this.md)).setToolTipText("Resolution menu");
        menuBar.add(this.resolutionMenu);
        (this.mapLayerMenu = new MapLayerMenu(this.imgArea.mapLayers, this)).setToolTipText("Map Layer menu");
        menuBar.add(this.mapLayerMenu);
        (this.toolsMenu = new ToolsMenu(this)).setToolTipText("Tools menu");
        menuBar.add(this.toolsMenu);
        if (this.grantedPrivileges) {
            (this.fileMenu = new FileMenu(this)).setToolTipText("File menu");
            menuBar.add(this.fileMenu);
        }
        (this.helpMenu = new HelpMenu(this, "Help")).setToolTipText("Help menu");
        menuBar.add(this.helpMenu);
        this.md.addObserver(this.sceneInfo);
        if (this.fileMenu != null) {
            this.md.addObserver(this.fileMenu);
        }
        this.md.addObserver(this.navDate);
        this.md.addObserver(this);
        this.md.addObserver(this.gridCellEntry);
        this.md.addObserver(this.latLongEntry);
        this.md.addObserver(this.locatorMap);
        this.md.addObserver(this.imgArea);
        this.md.addObserver(this.sceneListPanel);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        panel3.add(this.locatorMapScroll);
        panel3.add(this.gridCellEntry);
        panel3.add(this.latLongEntry);
        this.ccPanel = new JPanel();
        this.ccLayout = new CardLayout();
        this.ccPanel.setLayout(this.ccLayout);
        this.ccPanel.add("visible", this.maxCC);
        this.ccPanel.add("notVisible", new JPanel());
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        panel4.add(this.ccPanel);
        panel4.add(this.controls);
        panel4.add(Box.createHorizontalStrut(5));
        final Dimension preferredSize2 = panel4.getPreferredSize();
        preferredSize2.width = 100;
        panel4.setMinimumSize(preferredSize2);
        preferredSize2.width = 240;
        panel4.setMaximumSize(preferredSize2);
        panel3.add(Box.createVerticalStrut(3));
        panel3.add(panel4);
        panel3.add(Box.createVerticalStrut(3));
        panel3.add(this.sceneInfo);
        panel3.add(Box.createVerticalStrut(3));
        panel3.add(this.datePanel);
        panel3.add(Box.createVerticalStrut(3));
        panel3.add(this.sceneListPanel);
        panel3.setPreferredSize(new Dimension(240, n2));
        panel3.setMinimumSize(new Dimension(240, n2));
        panel3.setMaximumSize(new Dimension(240, 2000));
        panel3.add(Box.createHorizontalStrut(240));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 1;
        panel5.add(panel3, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.fill = 1;
        panel5.add(this.imgScroll, gridBagConstraints);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        panel6.add(panel5, "Center");
        panel6.add(this.statusBar, "South");
        panel6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if (this.popOut) {
            this.mainWindow.getContentPane().add(panel6);
            this.mainWindow.validate();
            this.mainWindow.setSize(800, 650);
            this.mainWindow.setVisible(true);
        }
        else {
            this.getContentPane().add(panel6);
        }
        this.searchForSceneDialog = new SearchForSceneDialog(this.getDialogParent(), this);
        this.searchLimitDialog = new SearchLimitDialog(this.getDialogParent(), this, this.md);
        this.searchForAddressDialog = new SearchForAddressDialog(this.getDialogParent(), this);
        this.md.addObserver(this.searchLimitDialog);
        this.sceneListDialog = new SceneListDialog(this.getDialogParent(), this, this.md);
        this.md.addObserver(this.sceneListDialog);
        this.hideSceneDialog = new HideSceneDialog(this.getDialogParent(), this, this.md);
        this.md.addObserver(this.hideSceneDialog);
        this.ndviGraphDialog = new NDVIGraphDialog(this.getDialogParent(), this, this.md);
        this.md.addObserver(this.ndviGraphDialog);
        this.imgArea.getInputMap(1).put(KeyStroke.getKeyStroke(83, 0, false), "disable");
        this.imgArea.getActionMap().put("disable", new DisableMapLayersAction());
        this.imgArea.getInputMap(1).put(KeyStroke.getKeyStroke(83, 0, true), "enable");
        this.imgArea.getActionMap().put("enable", new EnableMapLayersAction());
        ToolTipManager.sharedInstance().setDismissDelay(6000);
        this.initialized = true;
    }
    
    private void showAppletMessage(final String s) {
        final int n = 8;
        final JLabel[] array = new JLabel[n];
        int n2 = 0;
        this.getContentPane().setLayout(new GridLayout(n, 1));
        array[n2] = new JLabel("The Browse Image Viewer application should open in a separate window.");
        ++n2;
        array[n2] = new JLabel();
        ++n2;
        array[n2] = new JLabel("The web browser has to remain open to this page to use the application window.");
        ++n2;
        array[n2] = new JLabel();
        ++n2;
        (array[n2] = new JLabel("Click the mouse in this area to show the application window if it is not visible.")).setFont(this.boldFont);
        ++n2;
        array[n2] = new JLabel();
        ++n2;
        array[n2] = new JLabel("NOTE: Some browsers and 'Add-on' security products that block pop-up windows could prevent the shopping cart,");
        final Font font = new Font(s, 2, 12);
        array[n2].setFont(font);
        array[n2].setForeground(Color.RED);
        ++n2;
        (array[n2] = new JLabel("metadata, help, tools, and image windows from appearing in GloVis.  See the User Guide for more information.")).setFont(font);
        array[n2].setForeground(Color.RED);
        ++n2;
        for (int i = 0; i < array.length; ++i) {
            this.getContentPane().add(array[i]);
            array[i].addMouseListener(this);
        }
    }
    
    @Override
    public void start() {
        final String parameter = this.getParameter("sceneid");
        double doubleValue = 43.55;
        double doubleValue2 = -96.7;
        try {
            final String parameter2 = this.getParameter("lat");
            String s = this.getParameter("lon");
            if (s == null) {
                s = this.getParameter("long");
            }
            if (parameter2 != null && parameter2 != "undefined") {
                doubleValue = Double.valueOf(parameter2);
            }
            if (s != null && s != "undefined") {
                doubleValue2 = Double.valueOf(s);
            }
        }
        catch (Exception ex) {
            if (parameter == null || parameter == "undefined") {
                System.out.println("exception reading lat/lon parameters");
            }
        }
        Metadata searchForScene = null;
        if (parameter != null && parameter != "undefined") {
            searchForScene = this.searchForSceneDialog.searchForScene(this.sensorMenu.getCurrentSensor(), parameter);
        }
        this.locatorMap.initialize(doubleValue, doubleValue2);
        this.md.initialize(doubleValue, doubleValue2);
        if (searchForScene != null) {
            this.md.showScene(searchForScene);
        }
        else if (parameter != null && parameter != "undefined") {
            JOptionPane.showMessageDialog(this.getContentPane(), "The scene " + parameter + " was not found", "Scene not found", 0);
        }
        if (this.popOut) {
            this.maxCC.requestFocus();
        }
        else {
            this.requestFocusInWindow();
        }
    }
    
    @Override
    public void stop() {
        if (this.popOut) {
            this.mainWindow.setVisible(false);
        }
        this.md.killThread();
        this.md.mapLayers.killThread();
        this.searchForSceneDialog.dispose();
        this.searchForSceneDialog = null;
        this.searchLimitDialog.dispose();
        this.searchLimitDialog = null;
        this.sceneListDialog.dispose();
        this.sceneListDialog = null;
        this.hideSceneDialog.dispose();
        this.hideSceneDialog = null;
        this.ndviGraphDialog.dispose();
        this.ndviGraphDialog = null;
        this.userDefinedAreaDialog.dispose();
        this.userDefinedAreaDialog = null;
        this.pointOfInterestDialog.dispose();
        this.pointOfInterestDialog = null;
        this.searchForAddressDialog.dispose();
        this.searchForAddressDialog = null;
        this.md.mapLayers.cleanup();
        this.controls.cleanup();
        this.locatorMap.cleanup();
        this.imgArea.cleanup();
        this.md.cleanup();
        if (this.popOut) {
            this.mainWindow.getIconImage().flush();
            this.mainWindow.dispose();
        }
        super.stop();
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Prev Scene")) {
            this.md.sceneFilter.prevDate();
        }
        else if (actionCommand.equals("Next Scene")) {
            this.md.sceneFilter.nextDate();
        }
    }
    
    public JFrame getDialogParent() {
        if (this.popOut) {
            return this.mainWindow;
        }
        return new JFrame();
    }
    
    public Point getDialogLoc() {
        if (this.popOut) {
            return this.mainWindow.getLocationOnScreen();
        }
        return this.getLocationOnScreen();
    }
    
    public Container getDialogContainer() {
        if (this.popOut) {
            return this.mainWindow;
        }
        return this;
    }
    
    public synchronized void updateBusyIndicators() {
        final boolean unstableTOC = this.md.isUnstableTOC();
        final boolean showingBusy = this.md.isBusy() || this.md.mapLayers.isBusy();
        if (unstableTOC) {
            if (!this.showingWait) {
                this.imgArea.setCursor(this.waitCursor);
            }
        }
        else if (this.showingWait) {
            this.imgArea.setCursor(this.imgArea.currentCursor);
        }
        this.showingWait = unstableTOC;
        if (showingBusy) {
            if (!this.showingBusy) {
                this.statusBar.progress.setBusy();
            }
        }
        else if (this.showingBusy) {
            this.statusBar.progress.clearBusy();
        }
        this.showingBusy = showingBusy;
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        if (this.md.getCurrentScene() != null) {
            if (this.md.sceneFilter.isNextDateAvailable()) {
                this.dateIncButton.setEnabled(true);
            }
            else {
                this.dateIncButton.setEnabled(false);
            }
            if (this.md.sceneFilter.isPrevDateAvailable()) {
                this.dateDecButton.setEnabled(true);
            }
            else {
                this.dateDecButton.setEnabled(false);
            }
        }
        else {
            this.dateDecButton.setEnabled(false);
            this.dateIncButton.setEnabled(false);
        }
        if (this.sensorMenu.getCurrentSensor().hasCloudCover) {
            this.ccLayout.show(this.ccPanel, "visible");
        }
        else {
            this.ccLayout.show(this.ccPanel, "notVisible");
        }
        if (this.sensorMenu.getCurrentSensor().hasAcqDate) {
            this.dateLayout.show(this.datePanel, "visible");
        }
        else {
            this.dateLayout.show(this.datePanel, "notVisible");
        }
    }
    
    public Sensor[] getSensors() {
        return this.sensorMenu.getSensors();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.initialized && this.popOut) {
            this.mainWindow.setVisible(true);
            this.mainWindow.toFront();
            this.mainWindow.setState(0);
        }
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void focusGained(final FocusEvent focusEvent) {
        this.requestFocusInWindow();
    }
    
    @Override
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void print() {
        final PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);
        final PageFormat defaultPage = printerJob.defaultPage();
        if (printerJob.pageDialog(defaultPage) != defaultPage && printerJob.printDialog()) {
            try {
                printerJob.print();
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int n) throws PrinterException {
        if (n >= 1) {
            return 1;
        }
        Container container;
        if (this.popOut) {
            container = this.mainWindow.getContentPane();
        }
        else {
            container = this.getContentPane();
        }
        final Dimension size = container.getSize();
        double n2 = pageFormat.getImageableWidth() / size.width;
        final double n3 = pageFormat.getImageableHeight() / size.height;
        if (n3 < n2) {
            n2 = n3;
        }
        final RepaintManager currentManager = RepaintManager.currentManager(container);
        currentManager.setDoubleBufferingEnabled(false);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        graphics2D.scale(n2, n2);
        container.paint(graphics2D);
        currentManager.setDoubleBufferingEnabled(true);
        return 0;
    }
    
    class EnableMapLayersAction extends AbstractAction
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            imgViewer.this.md.mapLayers.disableExtraMapLayers(false);
        }
    }
    
    class DisableMapLayersAction extends AbstractAction
    {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            imgViewer.this.md.mapLayers.disableExtraMapLayers(true);
        }
    }
}
