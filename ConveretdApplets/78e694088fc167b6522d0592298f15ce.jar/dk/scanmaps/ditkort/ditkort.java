// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import java.util.regex.PatternSyntaxException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.Image;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import netscape.javascript.JSObject;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import javax.swing.JLabel;
import java.applet.AppletContext;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.net.URL;
import java.util.HashMap;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class ditkort extends Applet implements ActionListener, WindowListener, WindowFocusListener, WindowStateListener
{
    static final long serialVersionUID = 0L;
    private HashMap<String, String> DITKORT_VARENR_MAP;
    private HashMap<String, String> navne;
    CResourceManager myResource;
    private boolean c_oversigtskortEnable;
    private boolean egnskortEnabled;
    private String productno;
    private Egn result;
    public static boolean isInEnglish;
    static final String LOOKANDFEEL;
    private URL c_imageCentrer;
    private URL c_imageZoom;
    private boolean c_centrer;
    private boolean c_zoom;
    private JFrame c_ditkortFrame;
    private boolean isScrolled;
    JButton enableScrolling;
    private ditkortView c_ditkortView;
    private EgnskortView c_egnskortView;
    private navigationskortView c_navigationskortView;
    private DanmarksKort c_oversigtskort;
    private EgnskortOversigt_DTK25 c_egnskortoversigt25;
    private ditkortView c_ditkortViewNavngiv;
    private formatView c_formatView;
    private zoomView c_zoomView;
    private formatCheckboxes c_formatCheckboxes;
    private selectServiceView c_serviceView;
    private searchCityView c_searchView;
    private centerCoordsInput c_centerCoordsInput;
    private JPanel c_tabbedPanel;
    private JTabbedPane c_tabbedPane;
    private JPanel c_findStedPanel;
    private JTextArea c_ditkortInfo;
    private JTextArea c_ditkortViewNavngivInfo;
    public AppletContext c_appletContext;
    public String c_urlBase;
    private JLabel c_heleKortetKasseLabel;
    private JLabel c_kortUdsnitKasseLabel;
    private boolean c_kortUdsnitStatus;
    private boolean c_windowActivatedOnce;
    private int AdjustCenterCoord_X;
    private int AdjustCenterCoord_Y;
    private int AdjustOverviewField;
    private int GRIDSIZE_FACTOR;
    private String servicename;
    private JButton downloadButton;
    private String downloadLogin;
    private String downloadPassword;
    private String produkt;
    private boolean isInitiated;
    
    static {
        ditkort.isInEnglish = false;
        LOOKANDFEEL = null;
    }
    
    public ditkort() {
        this.DITKORT_VARENR_MAP = new HashMap<String, String>();
        this.navne = new HashMap<String, String>();
        this.myResource = CResourceManager.instance();
        this.c_oversigtskortEnable = false;
        this.egnskortEnabled = false;
        this.c_centrer = false;
        this.c_zoom = false;
        this.isScrolled = false;
        this.enableScrolling = new JButton(this.myResource.getResource("knap.egnskort.zoom.stoerre"));
        this.c_ditkortView = new ditkortView();
        this.c_egnskortView = new EgnskortView();
        this.c_ditkortViewNavngiv = new ditkortView();
        this.c_zoomView = new zoomView(this.c_ditkortView.c_centerX, this.c_ditkortView.c_centerY);
        this.c_formatCheckboxes = new formatCheckboxes(this.c_zoomView, this.c_ditkortView, this.c_ditkortViewNavngiv);
        this.c_tabbedPanel = new JPanel();
        this.c_tabbedPane = new JTabbedPane();
        this.c_findStedPanel = new JPanel();
        this.c_ditkortInfo = new JTextArea("");
        this.c_ditkortViewNavngivInfo = new JTextArea("");
        this.c_urlBase = Constant.imageURL;
        this.c_heleKortetKasseLabel = new JLabel();
        this.c_kortUdsnitKasseLabel = new JLabel();
        this.c_kortUdsnitStatus = false;
        this.c_windowActivatedOnce = true;
        this.downloadLogin = "";
        this.downloadPassword = "";
        this.isInitiated = false;
        if (Constant.debugMode) {
            System.out.println("ditkort() starts now");
        }
        try {
            this.c_imageCentrer = new URL(String.valueOf(this.c_urlBase) + "centrer.png");
            this.c_imageZoom = new URL(String.valueOf(this.c_urlBase) + "lup.png");
        }
        catch (MalformedURLException e) {
            System.err.println("Couldn't set center and zoom icons");
        }
        this.AdjustCenterCoord_X = 0;
        this.AdjustCenterCoord_Y = 0;
        this.AdjustOverviewField = 0;
        this.GRIDSIZE_FACTOR = 1;
        this.servicename = Constant.servicename_DTK25;
        this.produkt = "DTK25";
    }
    
    public void addComponentsToEgnskortPane(final Container pane) {
        pane.setLayout(new BoxLayout(pane, 2));
        final JPanel findStedRightPane = new JPanel();
        findStedRightPane.setLayout(new BoxLayout(findStedRightPane, 3));
        final JPanel findStedLeftPane = new JPanel();
        final Dimension findStedLeftPaneDim = new Dimension(600, 475);
        findStedLeftPane.setLayout(new BoxLayout(findStedLeftPane, 3));
        findStedLeftPane.setPreferredSize(findStedLeftPaneDim);
        final JPanel findStedLeftBottomPane = new JPanel();
        findStedLeftBottomPane.setLayout(new BoxLayout(findStedLeftBottomPane, 2));
        final Dimension findStedLeftBottomPaneDim = new Dimension(600, 200);
        findStedLeftBottomPane.setMinimumSize(findStedLeftBottomPaneDim);
        findStedLeftBottomPane.setMaximumSize(findStedLeftBottomPaneDim);
        findStedLeftBottomPane.setPreferredSize(findStedLeftBottomPaneDim);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, 2));
        final Dimension buttonPanelDim = new Dimension(200, 100);
        buttonPanel.setMinimumSize(buttonPanelDim);
        buttonPanel.setMaximumSize(buttonPanelDim);
        buttonPanel.setPreferredSize(buttonPanelDim);
        final JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, 2));
        final Dimension textPanelDim = new Dimension(200, 100);
        textPanel.setMinimumSize(textPanelDim);
        textPanel.setMaximumSize(textPanelDim);
        textPanel.setPreferredSize(textPanelDim);
        this.c_formatView.c_navngivTextField.setActionCommand("navngivtext");
        this.c_formatView.c_navngivTextField.addKeyListener(new formatViewTextFieldListener());
        this.c_formatView.c_planoButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_indrammetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_falsetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_lamineretPlanoButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_lamineretFalsetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_addtocart.setActionCommand("addtocart");
        this.c_formatView.c_addtocart.setToolTipText(this.myResource.getResource("knap.egnskort.laegikurv.tip"));
        this.c_formatView.c_addtocart.addActionListener(this);
        this.c_ditkortInfo.setFont(new Font("Verdana", 1, 12));
        this.c_ditkortInfo.setBackground(new Color(238, 238, 238));
        this.c_ditkortInfo.setEditable(false);
        this.c_ditkortInfo.setFocusable(false);
        this.c_ditkortInfo.setAlignmentX(0.0f);
        this.c_ditkortInfo.setAlignmentY(0.0f);
        this.c_ditkortInfo.setLineWrap(true);
        this.c_ditkortInfo.setWrapStyleWord(true);
        this.c_formatView.c_tilbage.setActionCommand("tilbage");
        this.c_formatView.c_tilbage.addActionListener(this);
        this.c_navigationskortView.setVisibility(false);
        this.c_zoomView.setVisibility(false);
        this.c_searchView.setVisibility(false);
        this.c_serviceView.setVisibility(false);
        if (Constant.enableBorderLines) {
            findStedLeftBottomPane.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0)));
            this.c_ditkortInfo.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        }
        this.enableScrolling.setActionCommand("enablescrolling");
        this.enableScrolling.addActionListener(this);
        this.enableScrolling.setToolTipText(this.myResource.getResource("knap.scrolling.tip"));
        this.enableScrolling.setAlignmentX(0.0f);
        this.enableScrolling.setAlignmentY(0.0f);
        textPanel.add(this.c_ditkortInfo);
        buttonPanel.add(this.enableScrolling);
        findStedLeftBottomPane.add(buttonPanel);
        findStedLeftBottomPane.add(Box.createRigidArea(new Dimension(200, 1)));
        findStedLeftBottomPane.add(textPanel);
        findStedLeftPane.add(this.c_egnskortView);
        findStedLeftPane.add(findStedLeftBottomPane);
        findStedRightPane.add(this.c_formatView);
        this.c_tabbedPanel.setLayout(new BoxLayout(this.c_tabbedPanel, 2));
        this.c_tabbedPanel.add(this.c_tabbedPane);
        this.c_tabbedPanel.setAlignmentX(0.0f);
        this.c_findStedPanel.setLayout(new BoxLayout(this.c_findStedPanel, 2));
        this.c_findStedPanel.setAlignmentY(0.0f);
        this.c_findStedPanel.add(findStedLeftPane);
        this.c_findStedPanel.add(findStedRightPane);
        (this.c_egnskortoversigt25 = new EgnskortOversigt_DTK25("egnskort_DTK25")).addMouseListener(new EgnskortOversigtsAdapter());
        this.c_tabbedPane.addMouseListener(new tabbedAdapter());
        this.c_tabbedPane.add(this.myResource.getResource("oversigtPanel"), this.c_egnskortoversigt25);
        this.c_tabbedPane.add(this.myResource.getResource("findstedpanel"), this.c_findStedPanel);
        pane.add(this.c_tabbedPanel);
        this.c_tabbedPane.setEnabledAt(1, false);
    }
    
    public void addComponentsToPane(final Container pane) {
        this.c_formatView.c_navngivTextField.setActionCommand("navngivtext");
        this.c_formatView.c_navngivTextField.addKeyListener(new formatViewTextFieldListener());
        this.c_formatView.c_planoButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_indrammetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_falsetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_lamineretPlanoButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_lamineretFalsetButton.addMouseListener(new formatViewOpklaebningsListener());
        this.c_formatView.c_addtocart.setActionCommand("addtocart");
        this.c_formatView.c_addtocart.addActionListener(this);
        pane.setLayout(new BoxLayout(pane, 2));
        this.c_ditkortInfo.setFont(new Font("Verdana", 1, 12));
        this.c_ditkortInfo.setBackground(new Color(238, 238, 238));
        this.c_ditkortInfo.setEditable(false);
        this.c_ditkortInfo.setFocusable(false);
        this.c_ditkortInfo.setMinimumSize(new Dimension(230, 50));
        this.c_ditkortInfo.setMaximumSize(new Dimension(230, 50));
        this.c_ditkortInfo.setPreferredSize(new Dimension(230, 100));
        this.c_ditkortInfo.setAlignmentX(0.0f);
        this.c_ditkortInfo.setAlignmentY(0.0f);
        this.c_ditkortInfo.setLineWrap(true);
        this.c_ditkortInfo.setWrapStyleWord(true);
        this.c_ditkortViewNavngivInfo.setFont(new Font("Verdana", 1, 12));
        this.c_ditkortViewNavngivInfo.setBackground(new Color(238, 238, 238));
        this.c_ditkortViewNavngivInfo.setEditable(false);
        this.c_ditkortViewNavngivInfo.setFocusable(false);
        this.c_ditkortViewNavngivInfo.setLineWrap(true);
        this.c_ditkortViewNavngivInfo.setWrapStyleWord(true);
        final JPanel findStedLeftPane = new JPanel();
        findStedLeftPane.setLayout(new BoxLayout(findStedLeftPane, 3));
        final JPanel findStedLeftBottomPane = new JPanel();
        findStedLeftBottomPane.setLayout(new BoxLayout(findStedLeftBottomPane, 2));
        final JPanel findStedMostLeftBottomPane = new JPanel();
        findStedMostLeftBottomPane.setPreferredSize(new Dimension(230, 200));
        findStedMostLeftBottomPane.setMinimumSize(new Dimension(230, 200));
        findStedMostLeftBottomPane.setMaximumSize(new Dimension(230, 200));
        final JPanel findStedLeftButtonPane = new JPanel();
        findStedLeftButtonPane.setLayout(new FlowLayout(2));
        findStedLeftButtonPane.setAlignmentX(1.0f);
        if (Constant.enableBorderLines) {
            findStedLeftButtonPane.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0)));
        }
        final JPanel navngivLeftPane = new JPanel();
        navngivLeftPane.setLayout(new BoxLayout(navngivLeftPane, 3));
        final JPanel navngivRightPane = new JPanel();
        final JPanel navngivLeftBottomPane = new JPanel();
        navngivLeftBottomPane.setLayout(new BoxLayout(navngivLeftBottomPane, 2));
        final JPanel navngivLeftButtonPane = new JPanel();
        navngivLeftButtonPane.setLayout(new FlowLayout());
        navngivLeftButtonPane.setAlignmentX(1.0f);
        final JPanel findStedRightPane = new JPanel();
        findStedRightPane.setLayout(new BoxLayout(findStedRightPane, 3));
        this.c_navigationskortView.addMouseListener(new navigationskortAdapter());
        this.c_ditkortView.nwButton.addActionListener(this);
        this.c_ditkortView.nButton.addActionListener(this);
        this.c_ditkortView.neButton.addActionListener(this);
        this.c_ditkortView.eButton.addActionListener(this);
        this.c_ditkortView.seButton.addActionListener(this);
        this.c_ditkortView.sButton.addActionListener(this);
        this.c_ditkortView.swButton.addActionListener(this);
        this.c_ditkortView.wButton.addActionListener(this);
        this.c_ditkortView.addMouseListener(new ditkortAdapter());
        this.c_zoomView.setAlignmentX(0.0f);
        this.c_zoomView.setAlignmentY(0.0f);
        final JButton videreButton = new JButton(this.myResource.getResource("knap.fortsaet"));
        videreButton.setAlignmentX(1.0f);
        videreButton.setAlignmentY(0.0f);
        videreButton.setToolTipText(this.myResource.getResource("knap.fortsaet.tip"));
        videreButton.setActionCommand("forts\u00e6t");
        videreButton.addActionListener(this);
        (this.downloadButton = new JButton()).setText(this.myResource.getResource("knap.download"));
        this.downloadButton.setAlignmentX(1.0f);
        this.downloadButton.setAlignmentY(0.0f);
        this.downloadButton.setToolTipText(this.myResource.getResource("knap.download.tip"));
        this.downloadButton.setActionCommand("download");
        this.downloadButton.addActionListener(this);
        if (Constant.debugMode && this.downloadLogin != null && !this.downloadLogin.equals("")) {
            this.downloadButton.setVisible(true);
        }
        else {
            this.downloadButton.setVisible(false);
        }
        final JPanel videreButtonPanel = new JPanel();
        videreButtonPanel.setLayout(new BoxLayout(videreButtonPanel, 2));
        this.c_formatView.c_tilbage.setActionCommand("tilbage");
        this.c_formatView.c_tilbage.addActionListener(this);
        final JButton navngivLeftPaneCentrerButton = new JButton(new ImageIcon(this.c_imageCentrer));
        navngivLeftPaneCentrerButton.setEnabled(false);
        navngivLeftPaneCentrerButton.setText(this.myResource.getResource("knap.centrer"));
        navngivLeftPaneCentrerButton.setAlignmentX(0.5f);
        final JButton navngivLeftPaneZoomButton = new JButton(new ImageIcon(this.c_imageZoom));
        navngivLeftPaneZoomButton.setEnabled(false);
        navngivLeftPaneZoomButton.setText(this.myResource.getResource("knap.zoom"));
        navngivLeftPaneZoomButton.setAlignmentX(0.5f);
        final JButton centrerButton = new JButton(new ImageIcon(this.c_imageCentrer));
        centrerButton.setActionCommand("centrer");
        centrerButton.addActionListener(this);
        centrerButton.setText(this.myResource.getResource("knap.centrer"));
        centrerButton.setToolTipText(this.myResource.getResource("knap.centrer.tip"));
        centrerButton.setAlignmentX(0.5f);
        final JButton zoomButton = new JButton(new ImageIcon(this.c_imageZoom));
        zoomButton.setActionCommand("zoom");
        zoomButton.addActionListener(this);
        zoomButton.setText(this.myResource.getResource("knap.zoom"));
        zoomButton.setToolTipText(this.myResource.getResource("knap.zoom.tip"));
        zoomButton.setAlignmentX(0.5f);
        zoomButton.doClick();
        final JLabel navngivLeftPaneKortUdsnitKasseLabel = new JLabel();
        navngivLeftPaneKortUdsnitKasseLabel.setAlignmentX(0.5f);
        navngivLeftPaneKortUdsnitKasseLabel.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
        navngivLeftPaneKortUdsnitKasseLabel.setToolTipText(this.myResource.getResource("knap.udsnit.tip"));
        navngivLeftPaneKortUdsnitKasseLabel.setEnabled(false);
        try {
            navngivLeftPaneKortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
        }
        catch (MalformedURLException URLe) {
            System.err.println("Couldn't fetch kasse image for navngiv");
        }
        this.c_kortUdsnitKasseLabel.setAlignmentX(0.5f);
        this.c_kortUdsnitKasseLabel.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
        this.c_kortUdsnitKasseLabel.setToolTipText(this.myResource.getResource("knap.udsnit.tip"));
        try {
            this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
        }
        catch (MalformedURLException URLe) {
            System.err.println("Couldn't fetch kasse image for find sted");
        }
        this.c_kortUdsnitKasseLabel.addMouseListener(new kortUdsnitKasse());
        final JLabel navngivLeftPaneHeleKortetKasseLabel = new JLabel();
        navngivLeftPaneHeleKortetKasseLabel.setAlignmentX(0.5f);
        navngivLeftPaneHeleKortetKasseLabel.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
        navngivLeftPaneHeleKortetKasseLabel.setToolTipText(this.myResource.getResource("knap.helekortet.tip"));
        navngivLeftPaneHeleKortetKasseLabel.setEnabled(false);
        try {
            if (this.myResource.getLocale().equals("en")) {
                navngivLeftPaneHeleKortetKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "entiremap_box.jpg")));
            }
            else {
                navngivLeftPaneHeleKortetKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "helekortet_kasse.jpg")));
            }
        }
        catch (MalformedURLException URLe2) {
            System.err.println("Couldn't fetch hele kortet kasse label icon for navngiv ditkort");
        }
        this.c_heleKortetKasseLabel.setAlignmentX(0.5f);
        this.c_heleKortetKasseLabel.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153)));
        this.c_heleKortetKasseLabel.setToolTipText(this.myResource.getResource("knap.helekortet.tip"));
        this.c_heleKortetKasseLabel.setEnabled(false);
        try {
            if (this.myResource.getLocale().equals("en")) {
                this.c_heleKortetKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "entiremap_box.jpg")));
            }
            else {
                this.c_heleKortetKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "helekortet_kasse.jpg")));
            }
        }
        catch (MalformedURLException URLe2) {
            System.err.println("Couldn't fetch helekortet_kasse image for findsted");
        }
        this.c_heleKortetKasseLabel.addMouseListener(new heleKortetKasseAdapter());
        findStedLeftButtonPane.setMinimumSize(new Dimension(374, 200));
        findStedLeftButtonPane.setMaximumSize(new Dimension(374, 200));
        findStedLeftButtonPane.setPreferredSize(new Dimension(374, 200));
        findStedLeftButtonPane.setAlignmentX(1.0f);
        findStedLeftButtonPane.add(this.c_heleKortetKasseLabel);
        findStedLeftButtonPane.add(this.c_kortUdsnitKasseLabel);
        findStedLeftButtonPane.add(zoomButton);
        findStedLeftButtonPane.add(centrerButton);
        findStedLeftButtonPane.add(this.downloadButton);
        navngivLeftButtonPane.add(navngivLeftPaneHeleKortetKasseLabel);
        navngivLeftButtonPane.add(navngivLeftPaneKortUdsnitKasseLabel);
        navngivLeftButtonPane.add(navngivLeftPaneZoomButton);
        navngivLeftButtonPane.add(navngivLeftPaneCentrerButton);
        navngivLeftButtonPane.setBorder(BorderFactory.createEmptyBorder());
        this.c_searchView.setAlignmentX(1.0f);
        this.c_searchView.setAlignmentY(0.0f);
        this.c_centerCoordsInput.setAlignmentX(1.0f);
        this.c_centerCoordsInput.setAlignmentY(0.0f);
        this.c_navigationskortView.setAlignmentX(1.0f);
        this.c_navigationskortView.setAlignmentY(0.0f);
        this.c_zoomView.setAlignmentX(1.0f);
        this.c_zoomView.setAlignmentY(0.0f);
        this.c_serviceView.setAlignmentX(1.0f);
        this.c_serviceView.setAlignmentY(0.0f);
        findStedRightPane.add(this.c_serviceView);
        findStedRightPane.add(this.c_searchView);
        findStedRightPane.add(this.c_navigationskortView);
        findStedRightPane.add(this.c_zoomView);
        videreButton.setAlignmentX(1.0f);
        findStedRightPane.add(videreButton);
        findStedRightPane.add(Box.createRigidArea(new Dimension(0, 24)));
        findStedRightPane.add(videreButtonPanel);
        findStedRightPane.add(Box.createRigidArea(new Dimension(0, 80)));
        findStedLeftPane.add(this.c_ditkortView);
        findStedLeftPane.add(Box.createRigidArea(new Dimension(0, 10)));
        findStedMostLeftBottomPane.add(this.c_ditkortInfo);
        findStedLeftBottomPane.add(findStedMostLeftBottomPane);
        findStedLeftBottomPane.add(findStedLeftButtonPane);
        findStedLeftPane.add(findStedLeftBottomPane);
        findStedLeftButtonPane.add(this.c_centerCoordsInput);
        if (!Constant.debugMode) {
            this.c_centerCoordsInput.setVisible(false);
        }
        this.c_ditkortViewNavngiv.disableButtons();
        final JPanel navngivDitkortPanel = new JPanel();
        navngivDitkortPanel.setLayout(new BoxLayout(navngivDitkortPanel, 2));
        navngivLeftPane.add(this.c_ditkortViewNavngiv);
        navngivLeftPane.add(Box.createRigidArea(new Dimension(0, 12)));
        navngivLeftBottomPane.add(this.c_ditkortViewNavngivInfo);
        navngivLeftBottomPane.add(navngivLeftButtonPane);
        navngivLeftPane.add(navngivLeftBottomPane);
        navngivLeftPane.add(Box.createRigidArea(new Dimension(0, 120)));
        navngivRightPane.add(this.c_formatView);
        if ((this.servicename.equals(Constant.servicename_H1870) || this.servicename.equals(Constant.servicename_L1928) || this.servicename.equals(Constant.servicename_1953) || this.servicename.equals(Constant.servicename_1980)) && !this.c_oversigtskortEnable) {
            this.c_formatView.updateHistoricalMapPrice();
            this.c_formatView.updatePrice();
        }
        navngivDitkortPanel.add(navngivLeftPane);
        navngivDitkortPanel.add(navngivRightPane);
        this.c_findStedPanel.setLayout(new BoxLayout(this.c_findStedPanel, 2));
        this.c_findStedPanel.setAlignmentY(0.0f);
        this.c_tabbedPanel.setLayout(new BoxLayout(this.c_tabbedPanel, 2));
        this.c_tabbedPanel.add(this.c_tabbedPane);
        this.c_tabbedPanel.setAlignmentX(0.0f);
        this.c_findStedPanel.add(findStedLeftPane);
        this.c_findStedPanel.add(findStedRightPane);
        if (this.c_oversigtskortEnable) {
            this.c_oversigtskort = new DanmarksKort(this.servicename);
            this.c_ditkortView.scrollFactor = 1;
            this.c_ditkortView.disableButtons();
            this.c_formatView.c_planoPris = "Test Price";
            this.c_formatView.c_falsetPris = "125,-";
            this.c_formatView.c_indrammetPris = "425,-";
            this.c_formatView.c_lamineretPlanoPris = "285,-";
            this.c_formatView.c_lamineretFalsetPris = "285,-";
            this.c_formatView.updatePrice();
            if (!Constant.debugMode) {
                this.c_searchView.setVisible(false);
                navngivLeftPaneCentrerButton.setVisible(false);
                centrerButton.setVisible(false);
            }
            if (!this.servicename.equals(Constant.servicename_H1870)) {
                this.c_oversigtskort.addMouseListener(new DanmarksKortAdapter());
                this.c_tabbedPane.add(this.myResource.getResource("oversigtPanel"), this.c_oversigtskort);
            }
        }
        this.c_tabbedPane.add(this.myResource.getResource("findstedpanel"), this.c_findStedPanel);
        this.c_tabbedPane.add(this.myResource.getResource("KortetsNavnPanel"), navngivDitkortPanel);
        pane.add(this.c_tabbedPanel);
        this.c_tabbedPane.addMouseListener(new tabbedAdapter());
    }
    
    public void windowGainedFocus(final WindowEvent e) {
        if (!this.c_windowActivatedOnce) {
            try {
                this.c_ditkortFrame.setAlwaysOnTop(false);
            }
            catch (SecurityException ex) {}
        }
        else {
            this.c_windowActivatedOnce = false;
        }
    }
    
    public void windowActivated(final WindowEvent e) {
        final JSObject win = JSObject.getWindow((Applet)this);
        win.eval("doHideDitkortLayers();");
    }
    
    public void windowOpened(final WindowEvent e) {
    }
    
    public void windowLostFocus(final WindowEvent e) {
    }
    
    public void windowStateChanged(final WindowEvent e) {
    }
    
    public void windowDeactivated(final WindowEvent e) {
    }
    
    public void windowClosing(final WindowEvent e) {
    }
    
    public void windowClosed(final WindowEvent e) {
    }
    
    public void windowIconified(final WindowEvent e) {
    }
    
    public void windowDeiconified(final WindowEvent e) {
    }
    
    public void actionPerformed(final ActionEvent e) {
        if ("centrer".equals(e.getActionCommand())) {
            this.c_centrer = true;
            this.c_zoom = false;
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            final ImageIcon img = new ImageIcon(this.c_imageCentrer);
            final Image pointer = img.getImage();
            final Cursor centerCursor = toolkit.createCustomCursor(pointer, new Point(14, 15), "img");
            this.c_ditkortView.setCursor(centerCursor);
        }
        else if ("zoom".equals(e.getActionCommand())) {
            this.c_zoom = true;
            this.c_centrer = false;
            final Toolkit toolkit = Toolkit.getDefaultToolkit();
            final ImageIcon img = new ImageIcon(this.c_imageZoom);
            final Image pointer = img.getImage();
            final Cursor zoomCursor = toolkit.createCustomCursor(pointer, new Point(8, 11), "img");
            this.c_ditkortView.setCursor(zoomCursor);
        }
        else if ("forts\u00e6t".equals(e.getActionCommand())) {
            if (this.c_ditkortViewNavngiv.c_centerX != this.c_ditkortView.c_centerX && this.c_ditkortViewNavngiv.c_centerY != this.c_ditkortView.c_centerY) {
                this.c_ditkortViewNavngiv.c_centerX = this.c_ditkortView.c_centerX;
                this.c_ditkortViewNavngiv.c_centerY = this.c_ditkortView.c_centerY;
                this.c_ditkortViewNavngiv.updateMap();
            }
            this.c_tabbedPane.setSelectedIndex(this.c_tabbedPane.getSelectedIndex() + 1);
        }
        else if ("tilbage".equals(e.getActionCommand())) {
            this.c_tabbedPane.setSelectedIndex(0);
            this.c_formatView.c_planoButton.setSelected(true);
        }
        else if ("enablescrolling".equals(e.getActionCommand())) {
            if (Constant.debugMode) {
                System.out.println("clicked on enable scrolling");
            }
            this.c_ditkortFrame.setCursor(Cursor.getPredefinedCursor(3));
            if (!this.isScrolled) {
                this.c_egnskortView.updateImage(this.productno, "large");
                this.enableScrolling.setText(this.myResource.getResource("knap.egnskort.zoom.mindre"));
                this.isScrolled = true;
            }
            else {
                this.c_egnskortView.updateImage(this.productno, "preview");
                this.enableScrolling.setText(this.myResource.getResource("knap.egnskort.zoom.stoerre"));
                this.isScrolled = false;
            }
            this.c_ditkortFrame.setCursor(Cursor.getPredefinedCursor(0));
        }
        else if ("addtocart".equals(e.getActionCommand())) {
            String selection = "";
            if (this.c_formatView.c_planoButton.isSelected()) {
                selection = "plano";
                if (this.myResource.getLocale().equals("en")) {
                    selection = "poster";
                }
            }
            else if (this.c_formatView.c_falsetButton.isSelected()) {
                selection = "falset";
                if (this.myResource.getLocale().equals("en")) {
                    selection = "folded";
                }
            }
            else if (this.c_formatView.c_indrammetButton.isSelected()) {
                selection = "indrammet";
                if (this.myResource.getLocale().equals("en")) {
                    selection = "framed";
                }
            }
            else if (this.c_formatView.c_lamineretPlanoButton.isSelected()) {
                selection = "lamineret (plano)";
                if (this.myResource.getLocale().equals("en")) {
                    selection = "laminated (poster)";
                }
            }
            else if (this.c_formatView.c_lamineretFalsetButton.isSelected()) {
                selection = "lamineret (falset)";
                if (this.myResource.getLocale().equals("en")) {
                    selection = "laminated (folded)";
                }
            }
            if (selection != "") {
                final boolean isUTMNetChosen = this.c_formatCheckboxes.isUTMgridSelected();
                final boolean isGeoNetChosen = this.c_formatCheckboxes.isLongLatGridSelected();
                boolean isOversigtskort = false;
                try {
                    String producttype = "1";
                    String name = this.c_formatView.c_navngivTextField.getText();
                    String varenr = "";
                    String url = "";
                    if (this.c_serviceView.getServiceName().equals(Constant.servicename_DTK25)) {
                        varenr = "0935500";
                    }
                    else if (this.c_serviceView.getServiceName().equals(Constant.servicename_DTK100)) {
                        varenr = "0935600";
                    }
                    else if (this.c_serviceView.getServiceName().equals(Constant.servicename_H1870)) {
                        varenr = "0935002";
                    }
                    else if (this.c_serviceView.getServiceName().equals(Constant.servicename_L1928)) {
                        varenr = "0935003";
                    }
                    else if (this.c_serviceView.getServiceName().equals(Constant.servicename_1953)) {
                        varenr = "0935004";
                    }
                    else if (this.c_serviceView.getServiceName().equals(Constant.servicename_1980)) {
                        varenr = "0935005";
                    }
                    if (this.c_oversigtskortEnable) {
                        isOversigtskort = true;
                        final int grabText = name.indexOf("2");
                        varenr = this.DITKORT_VARENR_MAP.get(name.substring(grabText).trim());
                        if (this.c_serviceView.getServiceName().equals(Constant.servicename_1953)) {
                            varenr = "0935006";
                        }
                        producttype = "2";
                    }
                    else if (this.egnskortEnabled) {
                        if (this.result != null) {
                            varenr = this.result.getProductNo();
                        }
                        name = " ";
                        producttype = "3";
                    }
                    if (Constant.debugMode) {
                        System.out.println("name is: " + name + "\n");
                        System.out.println("varenr is: " + varenr + "\n");
                        System.out.println("ekstra tekst is: " + selection + "\n");
                    }
                    if (Constant.debugMode) {
                        if (ditkort.isInEnglish) {
                            url = String.valueOf(url) + "http://www.scanmaps.com/nybutik/shop/showcart.php";
                        }
                        else {
                            url = String.valueOf(url) + "http://192.168.0.2/";
                        }
                    }
                    else if (ditkort.isInEnglish) {
                        url = String.valueOf(url) + "http://www.scanmaps.com/nybutik/shop/showcart.php";
                    }
                    else {
                        url = String.valueOf(url) + "http://www.scanmaps.dk/";
                    }
                    if (!this.egnskortEnabled) {
                        url = String.valueOf(url) + "?task=AddToCart";
                        url = String.valueOf(url) + "&Varenr=" + varenr;
                        url = String.valueOf(url) + "&EkstraTekst=" + URLEncoder.encode(selection, "ISO-8859-1");
                        url = String.valueOf(url) + "&bbox=" + this.c_ditkortView.ULX + "," + this.c_ditkortView.LRY + "," + this.c_ditkortView.LRX + "," + this.c_ditkortView.ULY;
                        url = String.valueOf(url) + "&kmsnavn=" + URLEncoder.encode(name, "ISO-8859-1");
                        url = String.valueOf(url) + "&service=" + URLEncoder.encode(this.c_serviceView.getServiceName(), "ISO-8859-1");
                        url = String.valueOf(url) + "&producttype=" + URLEncoder.encode(producttype, "ISO-8859-1");
                        url = String.valueOf(url) + "&layers=" + URLEncoder.encode(this.c_serviceView.getLayers(), "ISO-8859-1");
                        url = String.valueOf(url) + "&width=" + this.c_ditkortView.getPrintWidth();
                        url = String.valueOf(url) + "&height=" + this.c_ditkortView.getPrintHeight();
                        url = String.valueOf(url) + "&utm_net=" + isUTMNetChosen + "&geo_net=" + isGeoNetChosen + "&fixed_center=" + isOversigtskort;
                    }
                    else {
                        url = String.valueOf(url) + "?task=AddToCart&Varenr=" + varenr;
                        url = String.valueOf(url) + "&EkstraTekst=" + URLEncoder.encode(selection, "ISO-8859-1");
                        url = String.valueOf(url) + "&bbox=0.0,0.0,0.0,0.0";
                        url = String.valueOf(url) + "&kmsnavn=Egnskort&producttype=3";
                        url = String.valueOf(url) + "&service=egnskort&layers=egnskort";
                        url = String.valueOf(url) + "&utm_net=false&geo_net=false&fixed_center=false";
                    }
                    if (Constant.debugMode) {
                        System.out.println(url);
                    }
                    this.c_appletContext.showDocument(new URL(url), "_self");
                }
                catch (MalformedURLException ex) {
                    if (Constant.debugMode) {
                        ex.printStackTrace();
                    }
                }
                catch (UnsupportedEncodingException uee) {
                    if (Constant.debugMode) {
                        uee.printStackTrace();
                    }
                }
            }
        }
        else if (!"plano".equals(e.getActionCommand()) && !"falset".equals(e.getActionCommand()) && !"indrammet".equals(e.getActionCommand()) && !"lamineret".equals(e.getActionCommand())) {
            if ("moveUpLeft".equals(e.getActionCommand())) {
                this.c_ditkortView.moveUpLeft();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveUp".equals(e.getActionCommand())) {
                this.c_ditkortView.moveUp();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveUpRight".equals(e.getActionCommand())) {
                this.c_ditkortView.moveUpRight();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveRight".equals(e.getActionCommand())) {
                this.c_ditkortView.moveRight();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveDownRight".equals(e.getActionCommand())) {
                this.c_ditkortView.moveDownRight();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveDown".equals(e.getActionCommand())) {
                this.c_ditkortView.moveDown();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveDownLeft".equals(e.getActionCommand())) {
                this.c_ditkortView.moveDownLeft();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("moveLeft".equals(e.getActionCommand())) {
                this.c_ditkortView.moveLeft();
                this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
                this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
                this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
                this.c_heleKortetKasseLabel.setEnabled(false);
                try {
                    this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse label");
                }
            }
            else if ("download".equals(e.getActionCommand())) {
                System.out.println("Downloading map");
                final Login login = new Login(this.downloadLogin, this.downloadPassword);
                String name2 = "test";
                if (name2 != null && !name2.equals("")) {
                    name2 = this.c_formatView.c_navngivTextField.getText();
                }
                String varenr2 = "0935500";
                if (this.c_oversigtskortEnable) {
                    varenr2 = this.DITKORT_VARENR_MAP.get(name2);
                }
                final int mapSizeX = this.c_ditkortView.getPrintWidth();
                final int mapSizeY = this.c_ditkortView.getPrintHeight();
                if (Constant.debugMode) {
                    System.out.println("mapSizeX: " + mapSizeX + ", mapSizeY: " + mapSizeY);
                }
                String url2 = "http://kortforsyningen.kms.dk/service?servicename=" + this.c_serviceView.getServiceName();
                url2 = String.valueOf(url2) + "&service=WMS1";
                url2 = String.valueOf(url2) + "&version=1.1.1";
                url2 = String.valueOf(url2) + "&request=GetMap";
                url2 = String.valueOf(url2) + "&srs=EPSG:25832";
                url2 = String.valueOf(url2) + "&format=" + Constant.imageFormat;
                url2 = String.valueOf(url2) + "&width=" + new Integer(mapSizeX);
                url2 = String.valueOf(url2) + "&height=" + new Integer(mapSizeY);
                url2 = String.valueOf(url2) + "&exceptions=application/vnd.ogc.se_xml";
                url2 = String.valueOf(url2) + "&layers=" + this.c_serviceView.getLayers();
                if (this.c_serviceView.getServiceName().equals(Constant.servicename_DTK25)) {
                    if (this.c_formatCheckboxes.isUTMgridSelected()) {
                        url2 = String.valueOf(url2) + ",1km_net";
                    }
                    if (this.c_formatCheckboxes.isLongLatGridSelected()) {
                        url2 = String.valueOf(url2) + ",latlong";
                    }
                }
                else if (this.c_serviceView.getServiceName().equals(Constant.DTK100)) {
                    if (this.c_formatCheckboxes.isUTMgridSelected()) {
                        url2 = String.valueOf(url2) + ",5km_net";
                    }
                    if (this.c_formatCheckboxes.isLongLatGridSelected()) {
                        url2 = String.valueOf(url2) + ",latlong";
                    }
                }
                url2 = String.valueOf(url2) + "styles=&ticket=" + login.getTicket();
                if (Constant.debugMode) {
                    url2 = String.valueOf(url2) + "&case=test";
                }
                if (Constant.debugMode) {
                    System.out.println("Downloading image using the following URL: " + url2);
                }
                final float ULX = this.c_ditkortView.c_centerX - mapSizeX / 2 * this.c_ditkortView.c_pixelSize;
                final float ULY = this.c_ditkortView.c_centerY + mapSizeY / 2 * this.c_ditkortView.c_pixelSize;
                final float LRX = this.c_ditkortView.c_centerX + mapSizeX / 2 * this.c_ditkortView.c_pixelSize - 1.0f;
                final float LRY = this.c_ditkortView.c_centerY - mapSizeY / 2 * this.c_ditkortView.c_pixelSize + 1.0f;
                url2 = String.valueOf(url2) + "&bbox=" + ULX + "," + LRY + "," + LRX + "," + ULY;
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    final URL mapUrl = new URL(url2);
                    if (Constant.debugMode) {
                        System.out.println(url2);
                    }
                    final FileOutputStream out = new FileOutputStream("c:\\temp\\" + varenr2 + "-" + name2 + ".png");
                    bis = new BufferedInputStream(mapUrl.openStream());
                    bos = new BufferedOutputStream(out);
                    final byte[] buffer = new byte[16384];
                    long total = 0L;
                    int count;
                    while (-1 != (count = bis.read(buffer, 0, buffer.length))) {
                        total += count;
                        bos.write(buffer, 0, count);
                    }
                    bis.close();
                    bos.close();
                    System.out.println("Wrote " + new Long(total) + " bytes");
                }
                catch (MalformedURLException mal) {
                    System.out.println("Downloading map resulted in an exception: " + mal.getMessage());
                }
                catch (IOException ioe) {
                    System.out.println("Downloading map resulted in an exception: " + ioe.getMessage());
                }
                finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                        if (bos != null) {
                            bos.close();
                        }
                    }
                    catch (IOException ex2) {}
                }
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                }
                catch (IOException ex3) {}
            }
        }
    }
    
    private void initLookAndFeel() {
        String lookAndFeel = null;
        if (ditkort.LOOKANDFEEL != null) {
            if (ditkort.LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
            else if (ditkort.LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            }
            else if (ditkort.LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }
            else if (ditkort.LOOKANDFEEL.equals("GTK+")) {
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            }
            else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: " + ditkort.LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
            try {
                UIManager.setLookAndFeel(lookAndFeel);
            }
            catch (ClassNotFoundException e2) {
                System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            }
            catch (UnsupportedLookAndFeelException e3) {
                System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
                System.err.println("Using the default look and feel.");
            }
            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }
    
    public void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        (this.c_ditkortFrame = new JFrame("DitKort")).setTitle(this.myResource.getResource("ditkort.title"));
        this.c_ditkortFrame.setResizable(false);
        this.c_ditkortFrame.setDefaultCloseOperation(1);
        this.c_ditkortFrame.addWindowListener(this);
        this.c_ditkortFrame.addWindowFocusListener(this);
        this.c_ditkortFrame.addWindowStateListener(this);
        if (!this.egnskortEnabled) {
            this.addComponentsToPane(this.c_ditkortFrame.getContentPane());
        }
        else {
            this.addComponentsToEgnskortPane(this.c_ditkortFrame.getContentPane());
        }
        this.c_ditkortFrame.setPreferredSize(Constant.appletFrameDim);
        this.c_ditkortFrame.setMinimumSize(Constant.appletFrameDim);
        this.c_ditkortFrame.setMaximumSize(Constant.appletFrameDim);
        this.c_ditkortFrame.pack();
        this.c_ditkortFrame.setVisible(true);
        try {
            this.c_ditkortFrame.setAlwaysOnTop(true);
        }
        catch (SecurityException ex) {}
        if (this.c_oversigtskortEnable) {
            this.c_serviceView.setService();
        }
    }
    
    public void updateDanmarksKortNavn(final int px, final int py) {
        this.updateDanmarksKortNavn(px, py, false);
    }
    
    public void updateDanmarksKortNavn(final int px, final int py, final boolean moveCenter) {
        if (Constant.coordsValues) {
            System.out.println("updateDanmarksKortNavn, px: " + px + " py: " + py);
        }
        int nameX = 0;
        int nameY = 0;
        int modX = 0;
        int modY = 0;
        boolean bornholm = false;
        boolean laesoe = false;
        boolean anholt = false;
        String name = "";
        if (Constant.debugMode) {
            System.out.println("servicename in updateDanmarksKortNavn is " + this.servicename);
        }
        if (this.servicename.equals(Constant.servicename_DTK25) || this.servicename.equals(Constant.servicename_1953)) {
            nameX = 20 + (px + 3) / 4;
            nameY = 18 - py / 4;
            modX = (px + 3) % 4;
            modY = py % 4;
            bornholm = (px >= 18 && px <= 20 && py >= 1 && py <= 4);
            laesoe = (px >= 12 && px <= 14 && py >= 4 && py <= 6);
            anholt = (px >= 15 && px <= 16 && py >= 10 && py <= 11);
            if (Constant.debugMode) {
                System.out.println("bornholm is: " + bornholm + ", l\u00e6s\u00f8 is: " + laesoe + ", anholt is: " + anholt);
            }
            name = String.valueOf(nameX) + nameY + " ";
            if (bornholm) {
                name = "2812 ";
                modY = (py - 1) % 4;
            }
            if (modX >= 2 && modX <= 3 && modY >= 0 && modY <= 1) {
                name = String.valueOf(name) + "I ";
            }
            if (modX >= 2 && modX <= 3 && modY >= 2 && modY <= 3) {
                name = String.valueOf(name) + "II ";
            }
            if (modX >= 0 && modX <= 1 && modY >= 2 && modY <= 3) {
                name = String.valueOf(name) + "III ";
            }
            if (modX >= 0 && modX <= 1 && modY >= 0 && modY <= 1) {
                name = String.valueOf(name) + "IV ";
            }
            if (modX % 2 == 0 && modY % 2 == 0) {
                name = String.valueOf(name) + "NV";
            }
            if (modX % 2 == 1 && modY % 2 == 0) {
                name = String.valueOf(name) + "N\u00d8";
            }
            if (modX % 2 == 0 && modY % 2 == 1) {
                name = String.valueOf(name) + "SV";
            }
            if (modX % 2 == 1 && modY % 2 == 1) {
                name = String.valueOf(name) + "S\u00d8";
            }
            if (anholt) {
                name = "2416 II S\u00d8";
            }
            if (name.equals("2218 II S\u00d8")) {
                name = "2318 III SV";
            }
            else if (name.equals("2218 II N\u00d8")) {
                name = "2318 III NV";
            }
            else if (name.equals("2318 III SV")) {
                name = "2318 III S\u00d8";
            }
            else if (name.equals("2318 III NV")) {
                name = "2318 III N\u00d8";
            }
            else if (name.equals("2318 III S\u00d8")) {
                name = "2318 II SV";
            }
            else if (name.equals("2318 II SV")) {
                name = "2318 II S\u00d8";
            }
            else if (name.equals("2318 III N\u00d8")) {
                name = "2318 II NV";
            }
            else if (name.equals("2318 II NV")) {
                name = "2318 II N\u00d8";
            }
            else if (name.equals("2318 I NV")) {
                name = "2318 I N\u00d8";
            }
            else if (name.equals("2318 I SV")) {
                name = "2318 I S\u00d8";
            }
            else if (name.equals("2318 IV S\u00d8")) {
                name = "2318 I SV";
            }
        }
        else if (this.servicename.equals(Constant.servicename_DTK100)) {
            nameX = 21 + px;
            nameY = 18 - py;
            bornholm = (px == 4 && py >= 0 && py <= 1);
            laesoe = (px == 3 && py == 1);
            name = String.valueOf(nameX) + nameY;
            if (bornholm) {
                name = "2812";
            }
        }
        this.c_tabbedPane.setEnabledAt(1, true);
        this.c_tabbedPane.setEnabledAt(2, true);
        if (Constant.debugMode) {
            System.out.println("--" + name + "--");
        }
        if (!this.DITKORT_VARENR_MAP.containsKey(name) && !Constant.debugMode) {
            JOptionPane.showMessageDialog(null, this.myResource.getResource("oversigtskort.fejl.kanEjKoebes"));
            this.c_formatView.c_addtocart.setEnabled(false);
            this.c_tabbedPane.setEnabledAt(1, false);
            this.c_tabbedPane.setEnabledAt(2, false);
            this.c_tabbedPane.setSelectedIndex(0);
        }
        else {
            String title = String.valueOf(px) + "," + py + ": " + name + " ";
            if (Constant.debugMode) {
                System.out.println("In choosing the bbox the servicename is: " + this.servicename);
            }
            if (bornholm) {
                title = String.valueOf(title) + "Bornholm";
                if (this.servicename.equals(Constant.servicename_DTK25) || this.servicename.equals(Constant.servicename_1953)) {
                    this.c_ditkortView.c_centerX = Constant.BORNHOLM_X_25 + ((px - 18) * this.c_ditkortView.getPrintWidth() * this.c_ditkortView.c_pixelSize + this.AdjustCenterCoord_X);
                    this.c_ditkortView.c_centerY = Constant.BORNHOLM_Y_25 - ((py - 1) * this.c_ditkortView.getPrintHeight() * this.c_ditkortView.c_pixelSize + this.AdjustCenterCoord_Y);
                    if (Constant.debugMode) {
                        System.out.println("AdjustCenterCoord_X: " + this.AdjustCenterCoord_X + "AdjustCenterCoord_Y: " + this.AdjustCenterCoord_Y);
                    }
                }
                else if (this.servicename.equals(Constant.servicename_DTK100)) {
                    this.c_ditkortView.c_centerX = Constant.BORNHOLM_X_100;
                    this.c_ditkortView.c_centerY = Constant.BORNHOLM_Y_100;
                }
            }
            else if (laesoe) {
                title = String.valueOf(title) + "L\u00e6s\u00f8";
                if (this.servicename.equals(Constant.servicename_DTK25) || this.servicename.equals(Constant.servicename_1953)) {
                    final int lpx = (int)((this.c_oversigtskort.x - 19) / (Constant.DANMARKSKORT_GRIDSIZE_X * this.GRIDSIZE_FACTOR));
                    final int lpy = (int)((this.c_oversigtskort.y - 8) / (Constant.DANMARKSKORT_GRIDSIZE_Y * this.GRIDSIZE_FACTOR));
                    this.c_ditkortView.c_centerX = Constant.LAESOE_X_25 + (lpx - 12) * this.c_ditkortView.getPrintWidth() * this.c_ditkortView.c_pixelSize;
                    this.c_ditkortView.c_centerY = Constant.LAESOE_Y_25 - (lpy - 4) * this.c_ditkortView.getPrintHeight() * this.c_ditkortView.c_pixelSize;
                }
                else if (this.servicename.equals(Constant.servicename_DTK100)) {
                    this.c_ditkortView.c_centerX = Constant.LAESOE_X_100;
                    this.c_ditkortView.c_centerY = Constant.LAESOE_Y_100;
                }
            }
            else if (anholt) {
                title = "2416 II S\u00d8 Anholt";
                if (this.servicename.equals(Constant.servicename_DTK25) || this.servicename.equals(Constant.servicename_1953)) {
                    name = "2416 II S\u00d8";
                }
                else if (this.servicename.equals(Constant.servicename_DTK100)) {
                    name = "2416";
                }
                this.c_ditkortView.c_centerX = Constant.ANHOLT_X;
                this.c_ditkortView.c_centerY = Constant.ANHOLT_Y;
            }
            else if (name.equals("2318") && this.servicename.equals(Constant.servicename_DTK100)) {
                this.c_ditkortView.c_centerX = Constant.SKAGEN_X_100;
                this.c_ditkortView.c_centerY = Constant.SKAGEN_Y_100;
            }
            else {
                this.c_ditkortView.c_centerX = Constant.DANMARKSKORT_X + (px * this.c_ditkortView.getPrintWidth() * this.c_ditkortView.c_pixelSize + this.AdjustCenterCoord_X);
                this.c_ditkortView.c_centerY = Constant.DANMARKSKORT_Y - (py * this.c_ditkortView.getPrintHeight() * this.c_ditkortView.c_pixelSize + this.AdjustCenterCoord_Y);
            }
            if (Constant.coordsValues) {
                System.out.println("updateDanmarksKortnavn. X: " + this.c_ditkortView.c_centerX + " Y: " + this.c_ditkortView.c_centerY);
            }
            this.c_ditkortViewNavngiv.c_centerX = this.c_ditkortView.c_centerX;
            this.c_ditkortViewNavngiv.c_centerY = this.c_ditkortView.c_centerY;
            if (name.equals("2513") || name.equals("2512") || name.equals("2113") || name.equals("2514") || name.equals("2114") || name.equals("2511")) {
                this.c_ditkortView.updateMap(name, this.c_formatCheckboxes.isUTMgridSelected(), this.c_formatCheckboxes.isLongLatGridSelected());
                this.c_ditkortViewNavngiv.updateMap(name, this.c_formatCheckboxes.isUTMgridSelected(), this.c_formatCheckboxes.isLongLatGridSelected());
                this.c_zoomView.updateMap(this.c_ditkortView.c_centerX, this.c_ditkortView.c_centerY, this.c_formatCheckboxes.isUTMgridSelected(), this.c_formatCheckboxes.isLongLatGridSelected());
            }
            else {
                this.c_ditkortView.updateMap();
                this.c_ditkortViewNavngiv.updateMap();
                this.c_zoomView.updateMap(this.c_ditkortView.c_centerX, this.c_ditkortView.c_centerY, this.c_formatCheckboxes.isUTMgridSelected(), this.c_formatCheckboxes.isLongLatGridSelected());
            }
            this.c_centerCoordsInput.setCenterX(this.c_ditkortView.getCenterX());
            this.c_centerCoordsInput.setCenterY(this.c_ditkortView.getCenterY());
            this.c_navigationskortView.updateHotspotPosition((int)this.c_ditkortView.c_centerX, (int)this.c_ditkortView.c_centerY);
            if (this.navne.get(name) != null) {
                final String overviewMapName = String.valueOf(this.navne.get(name)) + " " + name;
                this.c_formatView.c_navngivTextField.setEditable(true);
                this.c_formatView.c_navngivTextField.setText(overviewMapName);
                this.c_formatView.c_navngivTextField.setEditable(false);
            }
            else {
                this.c_formatView.c_navngivTextField.setEditable(true);
                this.c_formatView.c_navngivTextField.setText(name);
                this.c_formatView.c_navngivTextField.setEditable(false);
            }
            if (Constant.debugMode) {
                this.c_ditkortFrame.setTitle(title);
            }
            this.c_formatView.c_addtocart.setEnabled(true);
            this.c_tabbedPane.setSelectedIndex(1);
        }
    }
    
    public void initialiseOversigtskortNavneHashmap() {
        this.navne.put("2013 I S\u00d8", "Vejers Strand");
        this.navne.put("2013 II N\u00d8", "Bl\u00e5vands Huk");
        this.navne.put("2111 I NV", "H\u00f8jer");
        this.navne.put("2111 I N\u00d8", "T\u00f8nder");
        this.navne.put("2112 I NV", "Ribe");
        this.navne.put("2112 I N\u00d8", "S\u00f8nder Hygum");
        this.navne.put("2112 I SV", "Egeb\u00e6k");
        this.navne.put("2112 I S\u00d8", "Arnum");
        this.navne.put("2112 II NV", "Sk\u00e6rb\u00e6k");
        this.navne.put("2112 II N\u00d8", "Arrild");
        this.navne.put("2112 II SV", "Ballum");
        this.navne.put("2112 II S\u00d8", "L\u00f8gumkloster");
        this.navne.put("2112 III N\u00d8", "R\u00f8m\u00f8");
        this.navne.put("2112 III S\u00d8", "Listerdyb");
        this.navne.put("2112 IV N\u00d8", "S\u00f8nderho");
        this.navne.put("2112 IV S\u00d8", "Mand\u00f8");
        this.navne.put("2113 I NV", "Ansager");
        this.navne.put("2113 I N\u00d8", "Grindsted");
        this.navne.put("2113 I SV", "Nordenskov");
        this.navne.put("2113 I S\u00d8", "Hejnsvig");
        this.navne.put("2113 II NV", "\u00c5rre");
        this.navne.put("2113 II N\u00d8", "Holsted");
        this.navne.put("2113 II SV", "Bramming");
        this.navne.put("2113 II S\u00d8", "G\u00f8rding");
        this.navne.put("2113 III NV", "Ho");
        this.navne.put("2113 III N\u00d8", "Guldager");
        this.navne.put("2113 III SV", "Skalling Ende");
        this.navne.put("2113 III S\u00d8", "Esbjerg");
        this.navne.put("2113 IV NV", "N\u00f8rre Nebel");
        this.navne.put("2113 IV N\u00d8", "Lunde");
        this.navne.put("2113 IV SV", "Oksb\u00f8l");
        this.navne.put("2113 IV S\u00d8", "Varde");
        this.navne.put("2114 I NV", "Vildbjerg");
        this.navne.put("2114 I N\u00d8", "Herning");
        this.navne.put("2114 I SV", "Videb\u00e6k");
        this.navne.put("2114 I S\u00d8", "Kib\u00e6k");
        this.navne.put("2114 II NV", "Borris");
        this.navne.put("2114 II N\u00d8", "Skarrild");
        this.navne.put("2114 II SV", "\u00d8lgod");
        this.navne.put("2114 II S\u00d8", "S\u00f8nder Omme");
        this.navne.put("2114 III NV", "\u00c5rgab");
        this.navne.put("2114 III N\u00d8", "Skjern");
        this.navne.put("2114 III SV", "Nymindegab");
        this.navne.put("2114 III S\u00d8", "Hemmet");
        this.navne.put("2114 IV NV", "Hee");
        this.navne.put("2114 IV N\u00d8", "Spjald");
        this.navne.put("2114 IV SV", "Ringk\u00f8bing");
        this.navne.put("2114 IV S\u00d8", "Lem");
        this.navne.put("2115 I NV", "Oddesund");
        this.navne.put("2115 I N\u00d8", "R\u00f8nbjerg");
        this.navne.put("2115 I SV", "Struer");
        this.navne.put("2115 I S\u00d8", "Vinderup");
        this.navne.put("2115 II NV", "Holstebro");
        this.navne.put("2115 II N\u00d8", "Haderup");
        this.navne.put("2115 II SV", "S\u00f8rvad");
        this.navne.put("2115 II S\u00d8", "Aulum");
        this.navne.put("2115 III NV", "Nissum Fjord");
        this.navne.put("2115 III N\u00d8", "Vemb");
        this.navne.put("2115 III SV", "Ulfborg");
        this.navne.put("2115 III S\u00d8", "Lystlund");
        this.navne.put("2115 IV NV", "Lemvig");
        this.navne.put("2115 IV N\u00d8", "Gudum");
        this.navne.put("2115 IV SV", "Ramme");
        this.navne.put("2115 IV S\u00d8", "Klosterhede Plantage");
        this.navne.put("2116 I NV", "Thisted");
        this.navne.put("2116 I N\u00d8", "Feggesund");
        this.navne.put("2116 I SV", "Vilsund");
        this.navne.put("2116 I S\u00d8", "Alsted");
        this.navne.put("2116 II NV", "Redsted");
        this.navne.put("2116 II N\u00d8", "Nyk\u00f8bing");
        this.navne.put("2116 II SV", "Jegind\u00f8");
        this.navne.put("2116 II S\u00d8", "Roslev");
        this.navne.put("2116 III NV", "Agger");
        this.navne.put("2116 III N\u00d8", "Hurup");
        this.navne.put("2116 III SV", "Thybor\u00f8n");
        this.navne.put("2116 III S\u00d8", "Lyngs");
        this.navne.put("2116 IV N\u00d8", "Hundborg");
        this.navne.put("2116 IV SV", "Lodbjerg fyr");
        this.navne.put("2116 IV S\u00d8", "H\u00f8rdum");
        this.navne.put("2117 II NV", "Hanstholm Havn");
        this.navne.put("2117 II N\u00d8", "Lild Strand");
        this.navne.put("2117 II SV", "Hanstholm");
        this.navne.put("2117 II S\u00d8", "Hjardem\u00e5l");
        this.navne.put("2117 III S\u00d8", "Klitm\u00f8ller");
        this.navne.put("2211 I NV", "Gr\u00e5sten");
        this.navne.put("2211 I N\u00d8", "S\u00f8nderborg");
        this.navne.put("2211 I SV", "Kollund");
        this.navne.put("2211 I S\u00d8", "Skelde");
        this.navne.put("2211 IV NV", "Bov");
        this.navne.put("2211 IV N\u00d8", "Kliplev");
        this.navne.put("2211 IV SV", "Lille Jyndevad");
        this.navne.put("2211 IV S\u00d8", "Padborg");
        this.navne.put("2212 I NV", "Christiansfeld");
        this.navne.put("2212 I N\u00d8", "Wedellsborg");
        this.navne.put("2212 I SV", "Haderslev");
        this.navne.put("2212 I S\u00d8", "\u00c5r\u00f8");
        this.navne.put("2212 II NV", "Bars\u00f8");
        this.navne.put("2212 II N\u00d8", "Rolfsager");
        this.navne.put("2212 II SV", "Varn\u00e6s");
        this.navne.put("2212 II S\u00d8", "Nordborg");
        this.navne.put("2212 III NV", "Toftlund");
        this.navne.put("2212 III N\u00d8", "Hoptrup");
        this.navne.put("2212 III SV", "Ravsted");
        this.navne.put("2212 III S\u00d8", "\u00c5benr\u00e5");
        this.navne.put("2212 IV NV", "R\u00f8dding");
        this.navne.put("2212 IV N\u00d8", "Sommersted");
        this.navne.put("2212 IV SV", "Gram");
        this.navne.put("2212 IV S\u00d8", "Vojens");
        this.navne.put("2213 I NV", "Vejle");
        this.navne.put("2213 I N\u00d8", "Hornsyld");
        this.navne.put("2213 I SV", "B\u00f8rkop");
        this.navne.put("2213 I S\u00d8", "G\u00e5rslev");
        this.navne.put("2213 II NV", "Taulov");
        this.navne.put("2213 II N\u00d8", "Fredericia");
        this.navne.put("2213 II SV", "Kolding");
        this.navne.put("2213 II S\u00d8", "N\u00f8rre Aaby");
        this.navne.put("2213 III NV", "B\u00e6kke");
        this.navne.put("2213 III N\u00d8", "Jordrup");
        this.navne.put("2213 III SV", "Vejen");
        this.navne.put("2213 III S\u00d8", "Vamdrup");
        this.navne.put("2213 IV NV", "Billund");
        this.navne.put("2213 IV N\u00d8", "Jelling");
        this.navne.put("2213 IV SV", "Vorbasse");
        this.navne.put("2213 IV S\u00d8", "Egtved");
        this.navne.put("2214 I NV", "Silkeborg");
        this.navne.put("2214 I N\u00d8", "L\u00e5sby");
        this.navne.put("2214 I SV", "Salten Langs\u00f8");
        this.navne.put("2214 I S\u00d8", "Ry");
        this.navne.put("2214 II NV", "Br\u00e6dstrup");
        this.navne.put("2214 II N\u00d8", "\u00d8stbirk");
        this.navne.put("2214 II SV", "Uldum");
        this.navne.put("2214 II S\u00d8", "Horsens");
        this.navne.put("2214 III NV", "Brande");
        this.navne.put("2214 III N\u00d8", "N\u00f8rre Snede");
        this.navne.put("2214 III SV", "Bl\u00e5h\u00f8j");
        this.navne.put("2214 III S\u00d8", "Kollemorten");
        this.navne.put("2214 IV NV", "Ikast");
        this.navne.put("2214 IV N\u00d8", "Bording");
        this.navne.put("2214 IV SV", "K\u00f8lk\u00e6r");
        this.navne.put("2214 IV S\u00d8", "Gludsted Plantage");
        this.navne.put("2215 I NV", "Klejtrup");
        this.navne.put("2215 I N\u00d8", "F\u00e5rup");
        this.navne.put("2215 I SV", "\u00d8rum");
        this.navne.put("2215 I S\u00d8", "Hammersh\u00f8j");
        this.navne.put("2215 II NV", "Bjerringbro");
        this.navne.put("2215 II N\u00d8", "Lang\u00e5");
        this.navne.put("2215 II SV", "Ans");
        this.navne.put("2215 II S\u00d8", "Hammel");
        this.navne.put("2215 III NV", "Resen");
        this.navne.put("2215 III N\u00d8", "Dollerup");
        this.navne.put("2215 III SV", "Karup");
        this.navne.put("2215 III S\u00d8", "Kjellerup");
        this.navne.put("2215 IV NV", "Skive");
        this.navne.put("2215 IV N\u00d8", "Skals");
        this.navne.put("2215 IV SV", "Stoholm");
        this.navne.put("2215 IV S\u00d8", "Viborg");
        this.navne.put("2216 I NV", "Nibe");
        this.navne.put("2216 I N\u00d8", "Svenstrup");
        this.navne.put("2216 I SV", "Vegger");
        this.navne.put("2216 I S\u00d8", "St\u00f8vring");
        this.navne.put("2216 II NV", "Aars");
        this.navne.put("2216 II N\u00d8", "Rold");
        this.navne.put("2216 II SV", "\u00c5lestrup");
        this.navne.put("2216 II S\u00d8", "Hobro");
        this.navne.put("2216 III NV", "Selde");
        this.navne.put("2216 III N\u00d8", "Fars\u00f8");
        this.navne.put("2216 III SV", "Hvalpsund");
        this.navne.put("2216 III S\u00d8", "Gedsted");
        this.navne.put("2216 IV NV", "L\u00f8gst\u00f8r Bredning");
        this.navne.put("2216 IV N\u00d8", "L\u00f8gst\u00f8r");
        this.navne.put("2216 IV SV", "Liv\u00f8");
        this.navne.put("2216 IV S\u00d8", "Vilsted");
        this.navne.put("2217 I NV", "Hvorup Klit");
        this.navne.put("2217 I N\u00d8", "L\u00f8kken");
        this.navne.put("2217 I SV", "Blokhus");
        this.navne.put("2217 I S\u00d8", "Manna");
        this.navne.put("2217 II NV", "Tranum Klitplantage");
        this.navne.put("2217 II N\u00d8", "Aabybro");
        this.navne.put("2217 II SV", "Brovst");
        this.navne.put("2217 II S\u00d8", "Gj\u00f8l");
        this.navne.put("2217 III NV", "Bulbjerg");
        this.navne.put("2217 III N\u00d8", "Svinkl\u00f8v Klitplantage");
        this.navne.put("2217 III SV", "Klim");
        this.navne.put("2217 III S\u00d8", "Fjerritslev");
        this.navne.put("2318 III NV", "Tornby Strand");
        this.navne.put("2318 III SV", "L\u00f8nstrup");
        this.navne.put("2311 I NV", "Drej\u00f8");
        this.navne.put("2311 I N\u00d8", "Rudk\u00f8bing");
        this.navne.put("2311 I SV", "Marstal");
        this.navne.put("2311 I S\u00d8", "Humble");
        this.navne.put("2311 II N\u00d8", "Bagenkop");
        this.navne.put("2311 IV NV", "Mommark");
        this.navne.put("2311 IV N\u00d8", "S\u00f8by");
        this.navne.put("2311 IV SV", "Skovby");
        this.navne.put("2311 IV S\u00d8", "\u00d8ster Bregninge");
        this.navne.put("2312 I NV", "Odense");
        this.navne.put("2312 I N\u00d8", "Langeskov");
        this.navne.put("2312 I SV", "Ringe");
        this.navne.put("2312 I S\u00d8", "\u00d8rb\u00e6k");
        this.navne.put("2312 II NV", "Kv\u00e6rndrup");
        this.navne.put("2312 II N\u00d8", "Gudme");
        this.navne.put("2312 II SV", "Ollerup");
        this.navne.put("2312 II S\u00d8", "Svendborg");
        this.navne.put("2312 III NV", "Heln\u00e6s");
        this.navne.put("2312 III N\u00d8", "Svanninge");
        this.navne.put("2312 III SV", "Helved");
        this.navne.put("2312 III S\u00d8", "Ly\u00f8");
        this.navne.put("2312 IV NV", "Aarup");
        this.navne.put("2312 IV N\u00d8", "Tommerup");
        this.navne.put("2312 IV SV", "Glamsbjerg");
        this.navne.put("2312 IV S\u00d8", "Brobyv\u00e6rk");
        this.navne.put("2313 I NV", "Gryden\u00e6s Odde");
        this.navne.put("2313 I N\u00d8", "Lushage");
        this.navne.put("2313 I SV", "T\u00f8rres\u00f8 Strand");
        this.navne.put("2313 I S\u00d8", "Fyns Hoved");
        this.navne.put("2313 II NV", "Otterup");
        this.navne.put("2313 II N\u00d8", "Martofte");
        this.navne.put("2313 II SV", "Odense Fjord");
        this.navne.put("2313 II S\u00d8", "Kerteminde");
        this.navne.put("2313 III NV", "Bogense");
        this.navne.put("2313 III N\u00d8", "Skamby");
        this.navne.put("2313 III SV", "Brenderup");
        this.navne.put("2313 III S\u00d8", "Beldringe");
        this.navne.put("2313 IV NV", "Juelsminde");
        this.navne.put("2313 IV N\u00d8", "Endelave");
        this.navne.put("2313 IV SV", "Alb\u00e6k Hoved");
        this.navne.put("2313 IV S\u00d8", "\u00c6bel\u00f8");
        this.navne.put("2314 I NV", "Helgen\u00e6s");
        this.navne.put("2314 I N\u00d8", "Ebeltoft");
        this.navne.put("2314 I SV", "Bruges ikke");
        this.navne.put("2314 II NV", "Tun\u00f8");
        this.navne.put("2314 II N\u00d8", "Kyholm");
        this.navne.put("2314 II SV", "Kolby K\u00e5s");
        this.navne.put("2314 II S\u00d8", "Ballen");
        this.navne.put("2314 III NV", "Hundslund");
        this.navne.put("2314 III N\u00d8", "Odder");
        this.navne.put("2314 III SV", "Alr\u00f8");
        this.navne.put("2314 III S\u00d8", "Gylling");
        this.navne.put("2314 IV NV", "Brabrand");
        this.navne.put("2314 IV N\u00d8", "\u00c5rhus");
        this.navne.put("2314 IV SV", "Skanderborg");
        this.navne.put("2314 IV S\u00d8", "Malling");
        this.navne.put("2315 I NV", "Hevring Hede");
        this.navne.put("2315 I N\u00d8", "B\u00f8nnerup Strand");
        this.navne.put("2315 I SV", "L\u00f8venholm");
        this.navne.put("2315 I S\u00d8", "Glesborg");
        this.navne.put("2315 II NV", "Ryomg\u00e5rd");
        this.navne.put("2315 II N\u00d8", "Trustrup");
        this.navne.put("2315 II SV", "Mols Bjerge");
        this.navne.put("2315 II S\u00d8", "Egsmark Strand");
        this.navne.put("2315 III NV", "Hadsten");
        this.navne.put("2315 III N\u00d8", "Rosenholm");
        this.navne.put("2315 III SV", "Hinnerup");
        this.navne.put("2315 III S\u00d8", "Sk\u00f8dstrup");
        this.navne.put("2315 IV NV", "Spentrup");
        this.navne.put("2315 IV N\u00d8", "Udbyh\u00f8j");
        this.navne.put("2315 IV SV", "Randers");
        this.navne.put("2315 IV S\u00d8", "Alling\u00e5bro");
        this.navne.put("2316 III NV", "Hadsund");
        this.navne.put("2316 III N\u00d8", "Skelund");
        this.navne.put("2316 III SV", "Mariager");
        this.navne.put("2316 III S\u00d8", "Havndal");
        this.navne.put("2316 IV NV", "S\u00f8nder Tranders");
        this.navne.put("2316 IV N\u00d8", "Hals");
        this.navne.put("2316 IV SV", "Lindenborg");
        this.navne.put("2316 IV S\u00d8", "Lille Vildmose");
        this.navne.put("2317 I NV", "S\u00e6by");
        this.navne.put("2317 I SV", "Lyngs\u00e5");
        this.navne.put("2317 II NV", "Voers\u00e5");
        this.navne.put("2317 III NV", "Sulsted");
        this.navne.put("2317 III N\u00d8", "Dronninglund");
        this.navne.put("2317 III SV", "Aalborg");
        this.navne.put("2317 III S\u00d8", "Ulsted");
        this.navne.put("2317 IV NV", "Vr\u00e5");
        this.navne.put("2317 IV N\u00d8", "Lendum");
        this.navne.put("2317 IV SV", "Br\u00f8nderslev");
        this.navne.put("2317 IV S\u00d8", "Dybvad");
        this.navne.put("2318 I N\u00d8", "Skagen");
        this.navne.put("2318 I S\u00d8", "Tranestederne");
        this.navne.put("2318 II N\u00d8", "\u00c5lb\u00e6k Bugt");
        this.navne.put("2318 II S\u00d8", "Frederikshavn");
        this.navne.put("2318 III N\u00d8", "Hirtshals");
        this.navne.put("2318 II NV", "Bindslev");
        this.navne.put("2318 III S\u00d8", "Hj\u00f8rring");
        this.navne.put("2318 II SV", "Tolne");
        this.navne.put("2318 I SV", "Kandestederne");
        this.navne.put("2411 I NV", "Horslunde");
        this.navne.put("2411 I N\u00d8", "Ask\u00f8");
        this.navne.put("2411 I SV", "S\u00f8llested");
        this.navne.put("2411 I S\u00d8", "Maribo");
        this.navne.put("2411 II NV", "R\u00f8dby");
        this.navne.put("2411 II N\u00d8", "Holeby");
        this.navne.put("2411 II SV", "R\u00f8dbyhavn");
        this.navne.put("2411 II S\u00d8", "Hyllekrog");
        this.navne.put("2411 III N\u00d8", "Magleh\u00f8j Strand");
        this.navne.put("2411 IV NV", "Spodsbjerg");
        this.navne.put("2411 IV N\u00d8", "Onsevig");
        this.navne.put("2411 IV SV", "Albuen");
        this.navne.put("2411 IV S\u00d8", "Nakskov");
        this.navne.put("2412 I NV", "Flakkebjerg");
        this.navne.put("2412 I N\u00d8", "Fuglebjerg");
        this.navne.put("2412 I SV", "Sk\u00e6lsk\u00f8r");
        this.navne.put("2412 I S\u00d8", "Hyllinge");
        this.navne.put("2412 II N\u00d8", "Karreb\u00e6ksminde Bugt");
        this.navne.put("2412 II SV", "Vejr\u00f8");
        this.navne.put("2412 II S\u00d8", "Fem\u00f8");
        this.navne.put("2412 III NV", "Lohals");
        this.navne.put("2412 III N\u00d8", "Om\u00f8");
        this.navne.put("2412 III SV", "Tranek\u00e6r Slot");
        this.navne.put("2412 IV NV", "Nyborg");
        this.navne.put("2412 IV N\u00d8", "Kors\u00f8r");
        this.navne.put("2412 IV SV", "T\u00e5rup Strand");
        this.navne.put("2412 IV S\u00d8", "Agers\u00f8");
        this.navne.put("2413 I NV", "Svinninge");
        this.navne.put("2413 I N\u00d8", "Gislinge");
        this.navne.put("2413 I SV", "Jyderup");
        this.navne.put("2413 I S\u00d8", "M\u00f8rk\u00f8v");
        this.navne.put("2413 II NV", "H\u00f8ng");
        this.navne.put("2413 II N\u00d8", "Dianalund");
        this.navne.put("2413 II SV", "Slagelse");
        this.navne.put("2413 II S\u00d8", "Sor\u00f8");
        this.navne.put("2413 III N\u00d8", "Reers\u00f8");
        this.navne.put("2413 III S\u00d8", "Stillinge Strand");
        this.navne.put("2413 IV NV", "R\u00f8sn\u00e6s");
        this.navne.put("2413 IV N\u00d8", "Saltb\u00e6k Vig");
        this.navne.put("2413 IV SV", "Asn\u00e6s");
        this.navne.put("2413 IV S\u00d8", "Kalundborg");
        this.navne.put("2414 I SV", "Gniben");
        this.navne.put("2414 II NV", "Sj\u00e6llands Odde");
        this.navne.put("2414 II N\u00d8", "Nyk\u00f8bing Sj\u00e6lland");
        this.navne.put("2414 II SV", "Veddinge Strand");
        this.navne.put("2414 II S\u00d8", "Vig");
        this.navne.put("2414 III N\u00d8", "Nordby");
        this.navne.put("2414 III S\u00d8", "Sejerby");
        this.navne.put("2415 III NV", "Katholm");
        this.navne.put("2415 III SV", "Rothes Grund");
        this.navne.put("2415 IV NV", "Gjerrild Klint");
        this.navne.put("2415 IV SV", "Grenaa");
        this.navne.put("2417 IV NV", "Vester\u00f8 Havn");
        this.navne.put("2417 IV N\u00d8", "\u00d8sterby Havn");
        this.navne.put("2417 IV SV", "Byrum");
        this.navne.put("2417 IV S\u00d8", "Kobbergrund");
        this.navne.put("2511 I NV", "Damsholte");
        this.navne.put("2511 I SV", "Hesn\u00e6s");
        this.navne.put("2511 III NV", "Toreby");
        this.navne.put("2511 III N\u00d8", "Marielyst");
        this.navne.put("2511 III SV", "Nysted");
        this.navne.put("2511 III S\u00d8", "Gedser");
        this.navne.put("2511 IV NV", "Guldborg");
        this.navne.put("2511 IV N\u00d8", "Stubbek\u00f8bing");
        this.navne.put("2511 IV SV", "Radsted");
        this.navne.put("2511 IV S\u00d8", "Horbelev");
        this.navne.put("2512 I NV", "Karise");
        this.navne.put("2512 I N\u00d8", "Store Heddinge");
        this.navne.put("2512 I SV", "Faxe Ladeplads");
        this.navne.put("2512 I S\u00d8", "Sk\u00f8rpinge Strand");
        this.navne.put("2512 II NV", "B\u00f8nsvig");
        this.navne.put("2512 II SV", "Stege");
        this.navne.put("2512 II S\u00d8", "Borre");
        this.navne.put("2512 III NV", "Svin\u00f8");
        this.navne.put("2512 III N\u00d8", "Pr\u00e6st\u00f8");
        this.navne.put("2512 III SV", "Knudshoved Odde");
        this.navne.put("2512 III S\u00d8", "Vordingborg");
        this.navne.put("2512 IV NV", "Herlufmagle");
        this.navne.put("2512 IV N\u00d8", "Haslev");
        this.navne.put("2512 IV SV", "N\u00e6stved");
        this.navne.put("2512 IV S\u00d8", "Kongsted");
        this.navne.put("2513 I NV", "Ballerup");
        this.navne.put("2513 I N\u00d8", "K\u00f8benhavn");
        this.navne.put("2513 I SV", "Taastrup");
        this.navne.put("2513 I S\u00d8", "Kalveboderne");
        this.navne.put("2513 II NV", "Solr\u00f8d");
        this.navne.put("2513 II SV", "K\u00f8ge");
        this.navne.put("2513 II S\u00d8", "Gjorslev");
        this.navne.put("2513 III NV", "Haraldsted S\u00f8");
        this.navne.put("2513 III N\u00d8", "Borup");
        this.navne.put("2513 III SV", "Ringsted");
        this.navne.put("2513 III S\u00d8", "\u00d8rslev");
        this.navne.put("2513 IV NV", "Holb\u00e6k");
        this.navne.put("2513 IV N\u00d8", "Jyllinge");
        this.navne.put("2513 IV SV", "T\u00f8ll\u00f8se");
        this.navne.put("2513 IV S\u00d8", "Roskilde");
        this.navne.put("2514 I NV", "Gilleleje");
        this.navne.put("2514 I N\u00d8", "Hornb\u00e6k");
        this.navne.put("2514 I SV", "Helsinge");
        this.navne.put("2514 I S\u00d8", "Helsing\u00f8r");
        this.navne.put("2514 II NV", "Hiller\u00f8d");
        this.navne.put("2514 II N\u00d8", "Rungsted");
        this.navne.put("2514 II SV", "Ganl\u00f8se");
        this.navne.put("2514 II S\u00d8", "Holte");
        this.navne.put("2514 III NV", "Hundested");
        this.navne.put("2514 III N\u00d8", "\u00d8lsted");
        this.navne.put("2514 III SV", "Kyndby");
        this.navne.put("2514 III S\u00d8", "Frederikssund");
        this.navne.put("2514 IV N\u00d8", "Vejby Strand");
        this.navne.put("2514 IV SV", "Kikhavn");
        this.navne.put("2514 IV S\u00d8", "Tisvilde Hegn");
        this.navne.put("2415 II S\u00d8", "Hessel\u00f8");
        this.navne.put("2416 II S\u00d8", "Anholt");
        this.navne.put("2612 III SV", "M\u00f8ns Klint");
        this.navne.put("2613 IV NV", "Refshale\u00f8en");
        this.navne.put("2613 IV SV", "Drag\u00f8r");
        this.navne.put("2812 I N\u00d8", "Ertholmene");
        this.navne.put("2812 I SV", "Gudhjem");
        this.navne.put("2812 II NV", "Almindingen");
        this.navne.put("2812 II N\u00d8", "Svaneke");
        this.navne.put("2812 II SV", "Aakirkeby");
        this.navne.put("2812 II S\u00d8", "Nex\u00f8");
        this.navne.put("2812 III N\u00d8", "Hasle");
        this.navne.put("2812 III S\u00d8", "R\u00f8nne");
        this.navne.put("2812 IV S\u00d8", "Hammeren");
        this.navne.put("2111", "T\u00f8nder");
        this.navne.put("2112", "Ribe");
        this.navne.put("2113", "Esbjerg");
        this.navne.put("2114", "Ringk\u00f8bing");
        this.navne.put("2115", "Holstebro");
        this.navne.put("2116", "Thisted");
        this.navne.put("2117", "Hanstholm");
        this.navne.put("2211", "S\u00f8nderborg");
        this.navne.put("2212", "Haderslev");
        this.navne.put("2213", "Vejle");
        this.navne.put("2214", "Silkeborg");
        this.navne.put("2215", "Viborg");
        this.navne.put("2216", "L\u00f8gst\u00f8r");
        this.navne.put("2217", "Fjerritslev");
        this.navne.put("2311", "\u00c6r\u00f8sk\u00f8bing");
        this.navne.put("2312", "Odense");
        this.navne.put("2313", "Bogense");
        this.navne.put("2314", "\u00c5rhus");
        this.navne.put("2315", "Randers");
        this.navne.put("2316", "Mariager");
        this.navne.put("2317", "Aalborg");
        this.navne.put("2318", "Skagen");
        this.navne.put("2411", "Nakskov");
        this.navne.put("2412", "Kors\u00f8r");
        this.navne.put("2413", "Kalundborg");
        this.navne.put("2414", "Nyk\u00f8bing S");
        this.navne.put("2415", "Grenaa & Anholt");
        this.navne.put("2417", "L\u00e6s\u00f8");
        this.navne.put("2511", "Falster");
        this.navne.put("2512", "N\u00e6stved");
        this.navne.put("2513", "K\u00f8benhavn");
        this.navne.put("2514", "Hiller\u00f8d");
        this.navne.put("2812", "Bornholm");
    }
    
    public void setHashMap() {
        this.DITKORT_VARENR_MAP.put("2111", "0250445");
        this.DITKORT_VARENR_MAP.put("2112", "0250446");
        this.DITKORT_VARENR_MAP.put("2113", "0250447");
        this.DITKORT_VARENR_MAP.put("2114", "0250448");
        this.DITKORT_VARENR_MAP.put("2115", "0250449");
        this.DITKORT_VARENR_MAP.put("2116", "0250450");
        this.DITKORT_VARENR_MAP.put("2117", "0250451");
        this.DITKORT_VARENR_MAP.put("2211", "0250452");
        this.DITKORT_VARENR_MAP.put("2212", "0250453");
        this.DITKORT_VARENR_MAP.put("2213", "0250454");
        this.DITKORT_VARENR_MAP.put("2214", "0250455");
        this.DITKORT_VARENR_MAP.put("2215", "0250456");
        this.DITKORT_VARENR_MAP.put("2216", "0250457");
        this.DITKORT_VARENR_MAP.put("2217", "0250458");
        this.DITKORT_VARENR_MAP.put("2311", "0250459");
        this.DITKORT_VARENR_MAP.put("2312", "0250460");
        this.DITKORT_VARENR_MAP.put("2313", "0250461");
        this.DITKORT_VARENR_MAP.put("2314", "0250462");
        this.DITKORT_VARENR_MAP.put("2315", "0250463");
        this.DITKORT_VARENR_MAP.put("2316", "0250464");
        this.DITKORT_VARENR_MAP.put("2317", "0250465");
        this.DITKORT_VARENR_MAP.put("2318", "0250466");
        this.DITKORT_VARENR_MAP.put("2411", "0250467");
        this.DITKORT_VARENR_MAP.put("2412", "0250468");
        this.DITKORT_VARENR_MAP.put("2413", "0250469");
        this.DITKORT_VARENR_MAP.put("2414", "0250470");
        this.DITKORT_VARENR_MAP.put("2415", "0250471");
        this.DITKORT_VARENR_MAP.put("2417", "0250472");
        this.DITKORT_VARENR_MAP.put("2511", "0250473");
        this.DITKORT_VARENR_MAP.put("2512", "0250474");
        this.DITKORT_VARENR_MAP.put("2513", "0250475");
        this.DITKORT_VARENR_MAP.put("2514", "0250476");
        this.DITKORT_VARENR_MAP.put("2812", "0250477");
        this.DITKORT_VARENR_MAP.put("2013 I S\u00d8", "0250001");
        this.DITKORT_VARENR_MAP.put("2013 II N\u00d8", "0250002");
        this.DITKORT_VARENR_MAP.put("2111 I NV", "0250003");
        this.DITKORT_VARENR_MAP.put("2111 I N\u00d8", "0250004");
        this.DITKORT_VARENR_MAP.put("2112 I NV", "0250007");
        this.DITKORT_VARENR_MAP.put("2112 I N\u00d8", "0250008");
        this.DITKORT_VARENR_MAP.put("2112 I SV", "0250009");
        this.DITKORT_VARENR_MAP.put("2112 I S\u00d8", "0250010");
        this.DITKORT_VARENR_MAP.put("2112 II NV", "0250011");
        this.DITKORT_VARENR_MAP.put("2112 II N\u00d8", "0250012");
        this.DITKORT_VARENR_MAP.put("2112 II SV", "0250013");
        this.DITKORT_VARENR_MAP.put("2112 II S\u00d8", "0250014");
        this.DITKORT_VARENR_MAP.put("2112 III N\u00d8", "0250015");
        this.DITKORT_VARENR_MAP.put("2112 III S\u00d8", "0250016");
        this.DITKORT_VARENR_MAP.put("2112 IV N\u00d8", "0250017");
        this.DITKORT_VARENR_MAP.put("2112 IV S\u00d8", "0250018");
        this.DITKORT_VARENR_MAP.put("2113 I NV", "0250019");
        this.DITKORT_VARENR_MAP.put("2113 I N\u00d8", "0250020");
        this.DITKORT_VARENR_MAP.put("2113 I SV", "0250021");
        this.DITKORT_VARENR_MAP.put("2113 I S\u00d8", "0250022");
        this.DITKORT_VARENR_MAP.put("2113 II NV", "0250023");
        this.DITKORT_VARENR_MAP.put("2113 II N\u00d8", "0250024");
        this.DITKORT_VARENR_MAP.put("2113 II SV", "0250025");
        this.DITKORT_VARENR_MAP.put("2113 II S\u00d8", "0250026");
        this.DITKORT_VARENR_MAP.put("2113 III NV", "0250027");
        this.DITKORT_VARENR_MAP.put("2113 III N\u00d8", "0250028");
        this.DITKORT_VARENR_MAP.put("2113 III SV", "0250029");
        this.DITKORT_VARENR_MAP.put("2113 III S\u00d8", "0250030");
        this.DITKORT_VARENR_MAP.put("2113 IV NV", "0250031");
        this.DITKORT_VARENR_MAP.put("2113 IV N\u00d8", "0250032");
        this.DITKORT_VARENR_MAP.put("2113 IV SV", "0250033");
        this.DITKORT_VARENR_MAP.put("2113 IV S\u00d8", "0250034");
        this.DITKORT_VARENR_MAP.put("2114 I NV", "0250035");
        this.DITKORT_VARENR_MAP.put("2114 I N\u00d8", "0250036");
        this.DITKORT_VARENR_MAP.put("2114 I SV", "0250037");
        this.DITKORT_VARENR_MAP.put("2114 I S\u00d8", "0250038");
        this.DITKORT_VARENR_MAP.put("2114 II NV", "0250039");
        this.DITKORT_VARENR_MAP.put("2114 II N\u00d8", "0250040");
        this.DITKORT_VARENR_MAP.put("2114 II SV", "0250041");
        this.DITKORT_VARENR_MAP.put("2114 II S\u00d8", "0250042");
        this.DITKORT_VARENR_MAP.put("2114 III NV", "0250043");
        this.DITKORT_VARENR_MAP.put("2114 III N\u00d8", "0250044");
        this.DITKORT_VARENR_MAP.put("2114 III SV", "0250045");
        this.DITKORT_VARENR_MAP.put("2114 III S\u00d8", "0250046");
        this.DITKORT_VARENR_MAP.put("2114 IV NV", "0250047");
        this.DITKORT_VARENR_MAP.put("2114 IV N\u00d8", "0250048");
        this.DITKORT_VARENR_MAP.put("2114 IV SV", "0250049");
        this.DITKORT_VARENR_MAP.put("2114 IV S\u00d8", "0250050");
        this.DITKORT_VARENR_MAP.put("2115 I NV", "0250051");
        this.DITKORT_VARENR_MAP.put("2115 I N\u00d8", "0250052");
        this.DITKORT_VARENR_MAP.put("2115 I SV", "0250053");
        this.DITKORT_VARENR_MAP.put("2115 I S\u00d8", "0250054");
        this.DITKORT_VARENR_MAP.put("2115 II NV", "0250055");
        this.DITKORT_VARENR_MAP.put("2115 II N\u00d8", "0250056");
        this.DITKORT_VARENR_MAP.put("2115 II SV", "0250057");
        this.DITKORT_VARENR_MAP.put("2115 II S\u00d8", "0250058");
        this.DITKORT_VARENR_MAP.put("2115 III NV", "0250059");
        this.DITKORT_VARENR_MAP.put("2115 III N\u00d8", "0250060");
        this.DITKORT_VARENR_MAP.put("2115 III SV", "0250061");
        this.DITKORT_VARENR_MAP.put("2115 III S\u00d8", "0250062");
        this.DITKORT_VARENR_MAP.put("2115 IV NV", "0250063");
        this.DITKORT_VARENR_MAP.put("2115 IV N\u00d8", "0250064");
        this.DITKORT_VARENR_MAP.put("2115 IV SV", "0250065");
        this.DITKORT_VARENR_MAP.put("2115 IV S\u00d8", "0250066");
        this.DITKORT_VARENR_MAP.put("2116 I NV", "0250067");
        this.DITKORT_VARENR_MAP.put("2116 I N\u00d8", "0250068");
        this.DITKORT_VARENR_MAP.put("2116 I SV", "0250069");
        this.DITKORT_VARENR_MAP.put("2116 I S\u00d8", "0250070");
        this.DITKORT_VARENR_MAP.put("2116 II NV", "0250071");
        this.DITKORT_VARENR_MAP.put("2116 II N\u00d8", "0250072");
        this.DITKORT_VARENR_MAP.put("2116 II SV", "0250073");
        this.DITKORT_VARENR_MAP.put("2116 II S\u00d8", "0250074");
        this.DITKORT_VARENR_MAP.put("2116 III NV", "0250075");
        this.DITKORT_VARENR_MAP.put("2116 III N\u00d8", "0250076");
        this.DITKORT_VARENR_MAP.put("2116 III SV", "0250077");
        this.DITKORT_VARENR_MAP.put("2116 III S\u00d8", "0250078");
        this.DITKORT_VARENR_MAP.put("2116 IV N\u00d8", "0250079");
        this.DITKORT_VARENR_MAP.put("2116 IV SV", "0250080");
        this.DITKORT_VARENR_MAP.put("2116 IV S\u00d8", "0250081");
        this.DITKORT_VARENR_MAP.put("2117 II NV", "0250082");
        this.DITKORT_VARENR_MAP.put("2117 II N\u00d8", "0250083");
        this.DITKORT_VARENR_MAP.put("2117 II SV", "0250084");
        this.DITKORT_VARENR_MAP.put("2117 II S\u00d8", "0250085");
        this.DITKORT_VARENR_MAP.put("2117 III S\u00d8", "0250086");
        this.DITKORT_VARENR_MAP.put("2211 I NV", "0250087");
        this.DITKORT_VARENR_MAP.put("2211 I N\u00d8", "0250088");
        this.DITKORT_VARENR_MAP.put("2211 I SV", "0250089");
        this.DITKORT_VARENR_MAP.put("2211 I S\u00d8", "0250090");
        this.DITKORT_VARENR_MAP.put("2211 IV NV", "0250091");
        this.DITKORT_VARENR_MAP.put("2211 IV N\u00d8", "0250092");
        this.DITKORT_VARENR_MAP.put("2211 IV SV", "0250093");
        this.DITKORT_VARENR_MAP.put("2211 IV S\u00d8", "0250094");
        this.DITKORT_VARENR_MAP.put("2212 I NV", "0250095");
        this.DITKORT_VARENR_MAP.put("2212 I N\u00d8", "0250096");
        this.DITKORT_VARENR_MAP.put("2212 I SV", "0250097");
        this.DITKORT_VARENR_MAP.put("2212 I S\u00d8", "0250098");
        this.DITKORT_VARENR_MAP.put("2212 II NV", "0250099");
        this.DITKORT_VARENR_MAP.put("2212 II N\u00d8", "0250100");
        this.DITKORT_VARENR_MAP.put("2212 II SV", "0250101");
        this.DITKORT_VARENR_MAP.put("2212 II S\u00d8", "0250102");
        this.DITKORT_VARENR_MAP.put("2212 III NV", "0250103");
        this.DITKORT_VARENR_MAP.put("2212 III N\u00d8", "0250104");
        this.DITKORT_VARENR_MAP.put("2212 III SV", "0250105");
        this.DITKORT_VARENR_MAP.put("2212 III S\u00d8", "0250106");
        this.DITKORT_VARENR_MAP.put("2212 IV NV", "0250107");
        this.DITKORT_VARENR_MAP.put("2212 IV N\u00d8", "0250108");
        this.DITKORT_VARENR_MAP.put("2212 IV SV", "0250109");
        this.DITKORT_VARENR_MAP.put("2212 IV S\u00d8", "0250110");
        this.DITKORT_VARENR_MAP.put("2213 I NV", "0250111");
        this.DITKORT_VARENR_MAP.put("2213 I N\u00d8", "0250112");
        this.DITKORT_VARENR_MAP.put("2213 I SV", "0250113");
        this.DITKORT_VARENR_MAP.put("2213 I S\u00d8", "0250114");
        this.DITKORT_VARENR_MAP.put("2213 II NV", "0250115");
        this.DITKORT_VARENR_MAP.put("2213 II N\u00d8", "0250116");
        this.DITKORT_VARENR_MAP.put("2213 II SV", "0250117");
        this.DITKORT_VARENR_MAP.put("2213 II S\u00d8", "0250118");
        this.DITKORT_VARENR_MAP.put("2213 III NV", "0250119");
        this.DITKORT_VARENR_MAP.put("2213 III N\u00d8", "0250120");
        this.DITKORT_VARENR_MAP.put("2213 III SV", "0250121");
        this.DITKORT_VARENR_MAP.put("2213 III S\u00d8", "0250122");
        this.DITKORT_VARENR_MAP.put("2213 IV NV", "0250123");
        this.DITKORT_VARENR_MAP.put("2213 IV N\u00d8", "0250124");
        this.DITKORT_VARENR_MAP.put("2213 IV SV", "0250125");
        this.DITKORT_VARENR_MAP.put("2213 IV S\u00d8", "0250126");
        this.DITKORT_VARENR_MAP.put("2214 I NV", "0250127");
        this.DITKORT_VARENR_MAP.put("2214 I N\u00d8", "0250128");
        this.DITKORT_VARENR_MAP.put("2214 I SV", "0250129");
        this.DITKORT_VARENR_MAP.put("2214 I S\u00d8", "0250130");
        this.DITKORT_VARENR_MAP.put("2214 II NV", "0250131");
        this.DITKORT_VARENR_MAP.put("2214 II N\u00d8", "0250132");
        this.DITKORT_VARENR_MAP.put("2214 II SV", "0250133");
        this.DITKORT_VARENR_MAP.put("2214 II S\u00d8", "0250134");
        this.DITKORT_VARENR_MAP.put("2214 III NV", "0250135");
        this.DITKORT_VARENR_MAP.put("2214 III N\u00d8", "0250136");
        this.DITKORT_VARENR_MAP.put("2214 III SV", "0250137");
        this.DITKORT_VARENR_MAP.put("2214 III S\u00d8", "0250138");
        this.DITKORT_VARENR_MAP.put("2214 IV NV", "0250139");
        this.DITKORT_VARENR_MAP.put("2214 IV N\u00d8", "0250140");
        this.DITKORT_VARENR_MAP.put("2214 IV SV", "0250141");
        this.DITKORT_VARENR_MAP.put("2214 IV S\u00d8", "0250142");
        this.DITKORT_VARENR_MAP.put("2215 I NV", "0250143");
        this.DITKORT_VARENR_MAP.put("2215 I N\u00d8", "0250144");
        this.DITKORT_VARENR_MAP.put("2215 I SV", "0250145");
        this.DITKORT_VARENR_MAP.put("2215 I S\u00d8", "0250146");
        this.DITKORT_VARENR_MAP.put("2215 II NV", "0250147");
        this.DITKORT_VARENR_MAP.put("2215 II N\u00d8", "0250148");
        this.DITKORT_VARENR_MAP.put("2215 II SV", "0250149");
        this.DITKORT_VARENR_MAP.put("2215 II S\u00d8", "0250150");
        this.DITKORT_VARENR_MAP.put("2215 III NV", "0250151");
        this.DITKORT_VARENR_MAP.put("2215 III N\u00d8", "0250152");
        this.DITKORT_VARENR_MAP.put("2215 III SV", "0250153");
        this.DITKORT_VARENR_MAP.put("2215 III S\u00d8", "0250154");
        this.DITKORT_VARENR_MAP.put("2215 IV NV", "0250155");
        this.DITKORT_VARENR_MAP.put("2215 IV N\u00d8", "0250156");
        this.DITKORT_VARENR_MAP.put("2215 IV SV", "0250157");
        this.DITKORT_VARENR_MAP.put("2215 IV S\u00d8", "0250158");
        this.DITKORT_VARENR_MAP.put("2216 I NV", "0250159");
        this.DITKORT_VARENR_MAP.put("2216 I N\u00d8", "0250160");
        this.DITKORT_VARENR_MAP.put("2216 I SV", "0250161");
        this.DITKORT_VARENR_MAP.put("2216 I S\u00d8", "0250162");
        this.DITKORT_VARENR_MAP.put("2216 II NV", "0250163");
        this.DITKORT_VARENR_MAP.put("2216 II N\u00d8", "0250164");
        this.DITKORT_VARENR_MAP.put("2216 II SV", "0250165");
        this.DITKORT_VARENR_MAP.put("2216 II S\u00d8", "0250166");
        this.DITKORT_VARENR_MAP.put("2216 III NV", "0250167");
        this.DITKORT_VARENR_MAP.put("2216 III N\u00d8", "0250168");
        this.DITKORT_VARENR_MAP.put("2216 III SV", "0250169");
        this.DITKORT_VARENR_MAP.put("2216 III S\u00d8", "0250170");
        this.DITKORT_VARENR_MAP.put("2216 IV NV", "0250171");
        this.DITKORT_VARENR_MAP.put("2216 IV N\u00d8", "0250172");
        this.DITKORT_VARENR_MAP.put("2216 IV SV", "0250173");
        this.DITKORT_VARENR_MAP.put("2216 IV S\u00d8", "0250174");
        this.DITKORT_VARENR_MAP.put("2217 I NV", "0250175");
        this.DITKORT_VARENR_MAP.put("2217 I N\u00d8", "0250176");
        this.DITKORT_VARENR_MAP.put("2217 I SV", "0250177");
        this.DITKORT_VARENR_MAP.put("2217 I S\u00d8", "0250178");
        this.DITKORT_VARENR_MAP.put("2217 II NV", "0250179");
        this.DITKORT_VARENR_MAP.put("2217 II N\u00d8", "0250180");
        this.DITKORT_VARENR_MAP.put("2217 II SV", "0250181");
        this.DITKORT_VARENR_MAP.put("2217 II S\u00d8", "0250182");
        this.DITKORT_VARENR_MAP.put("2217 III NV", "0250183");
        this.DITKORT_VARENR_MAP.put("2217 III N\u00d8", "0250184");
        this.DITKORT_VARENR_MAP.put("2217 III SV", "0250185");
        this.DITKORT_VARENR_MAP.put("2217 III S\u00d8", "0250186");
        this.DITKORT_VARENR_MAP.put("2318 III NV", "0250187");
        this.DITKORT_VARENR_MAP.put("2318 III SV", "0250188");
        this.DITKORT_VARENR_MAP.put("2311 I NV", "0250189");
        this.DITKORT_VARENR_MAP.put("2311 I N\u00d8", "0250190");
        this.DITKORT_VARENR_MAP.put("2311 I SV", "0250191");
        this.DITKORT_VARENR_MAP.put("2311 I S\u00d8", "0250192");
        this.DITKORT_VARENR_MAP.put("2311 II N\u00d8", "0250193");
        this.DITKORT_VARENR_MAP.put("2311 IV NV", "0250194");
        this.DITKORT_VARENR_MAP.put("2311 IV N\u00d8", "0250195");
        this.DITKORT_VARENR_MAP.put("2311 IV SV", "0250196");
        this.DITKORT_VARENR_MAP.put("2311 IV S\u00d8", "0250197");
        this.DITKORT_VARENR_MAP.put("2312 I NV", "0250198");
        this.DITKORT_VARENR_MAP.put("2312 I N\u00d8", "0250199");
        this.DITKORT_VARENR_MAP.put("2312 I SV", "0250200");
        this.DITKORT_VARENR_MAP.put("2312 I S\u00d8", "0250201");
        this.DITKORT_VARENR_MAP.put("2312 II NV", "0250202");
        this.DITKORT_VARENR_MAP.put("2312 II N\u00d8", "0250203");
        this.DITKORT_VARENR_MAP.put("2312 II SV", "0250204");
        this.DITKORT_VARENR_MAP.put("2312 II S\u00d8", "0250205");
        this.DITKORT_VARENR_MAP.put("2312 III NV", "0250206");
        this.DITKORT_VARENR_MAP.put("2312 III N\u00d8", "0250207");
        this.DITKORT_VARENR_MAP.put("2312 III SV", "0250208");
        this.DITKORT_VARENR_MAP.put("2312 III S\u00d8", "0250209");
        this.DITKORT_VARENR_MAP.put("2312 IV NV", "0250210");
        this.DITKORT_VARENR_MAP.put("2312 IV N\u00d8", "0250211");
        this.DITKORT_VARENR_MAP.put("2312 IV SV", "0250212");
        this.DITKORT_VARENR_MAP.put("2312 IV S\u00d8", "0250213");
        this.DITKORT_VARENR_MAP.put("2313 I NV", "0250214");
        this.DITKORT_VARENR_MAP.put("2313 I N\u00d8", "0250215");
        this.DITKORT_VARENR_MAP.put("2313 I SV", "0250216");
        this.DITKORT_VARENR_MAP.put("2313 I S\u00d8", "0250217");
        this.DITKORT_VARENR_MAP.put("2313 II NV", "0250218");
        this.DITKORT_VARENR_MAP.put("2313 II N\u00d8", "0250219");
        this.DITKORT_VARENR_MAP.put("2313 II SV", "0250220");
        this.DITKORT_VARENR_MAP.put("2313 II S\u00d8", "0250221");
        this.DITKORT_VARENR_MAP.put("2313 III NV", "0250222");
        this.DITKORT_VARENR_MAP.put("2313 III N\u00d8", "0250223");
        this.DITKORT_VARENR_MAP.put("2313 III SV", "0250224");
        this.DITKORT_VARENR_MAP.put("2313 III S\u00d8", "0250225");
        this.DITKORT_VARENR_MAP.put("2313 IV NV", "0250226");
        this.DITKORT_VARENR_MAP.put("2313 IV N\u00d8", "0250227");
        this.DITKORT_VARENR_MAP.put("2313 IV SV", "0250228");
        this.DITKORT_VARENR_MAP.put("2313 IV S\u00d8", "0250229");
        this.DITKORT_VARENR_MAP.put("2314 I NV", "0250230");
        this.DITKORT_VARENR_MAP.put("2314 I N\u00d8", "0250231");
        this.DITKORT_VARENR_MAP.put("2314 I SV", "0250232");
        this.DITKORT_VARENR_MAP.put("2314 II NV", "0250233");
        this.DITKORT_VARENR_MAP.put("2314 II N\u00d8", "0250234");
        this.DITKORT_VARENR_MAP.put("2314 II SV", "0250235");
        this.DITKORT_VARENR_MAP.put("2314 II S\u00d8", "0250236");
        this.DITKORT_VARENR_MAP.put("2314 III NV", "0250237");
        this.DITKORT_VARENR_MAP.put("2314 III N\u00d8", "0250238");
        this.DITKORT_VARENR_MAP.put("2314 III SV", "0250239");
        this.DITKORT_VARENR_MAP.put("2314 III S\u00d8", "0250240");
        this.DITKORT_VARENR_MAP.put("2314 IV NV", "0250241");
        this.DITKORT_VARENR_MAP.put("2314 IV N\u00d8", "0250242");
        this.DITKORT_VARENR_MAP.put("2314 IV SV", "0250243");
        this.DITKORT_VARENR_MAP.put("2314 IV S\u00d8", "0250244");
        this.DITKORT_VARENR_MAP.put("2315 I NV", "0250245");
        this.DITKORT_VARENR_MAP.put("2315 I N\u00d8", "0250246");
        this.DITKORT_VARENR_MAP.put("2315 I SV", "0250247");
        this.DITKORT_VARENR_MAP.put("2315 I S\u00d8", "0250248");
        this.DITKORT_VARENR_MAP.put("2315 II NV", "0250249");
        this.DITKORT_VARENR_MAP.put("2315 II N\u00d8", "0250250");
        this.DITKORT_VARENR_MAP.put("2315 II SV", "0250251");
        this.DITKORT_VARENR_MAP.put("2315 II S\u00d8", "0250252");
        this.DITKORT_VARENR_MAP.put("2315 III NV", "0250253");
        this.DITKORT_VARENR_MAP.put("2315 III N\u00d8", "0250254");
        this.DITKORT_VARENR_MAP.put("2315 III SV", "0250255");
        this.DITKORT_VARENR_MAP.put("2315 III S\u00d8", "0250256");
        this.DITKORT_VARENR_MAP.put("2315 IV NV", "0250257");
        this.DITKORT_VARENR_MAP.put("2315 IV N\u00d8", "0250258");
        this.DITKORT_VARENR_MAP.put("2315 IV SV", "0250259");
        this.DITKORT_VARENR_MAP.put("2315 IV S\u00d8", "0250260");
        this.DITKORT_VARENR_MAP.put("2316 III NV", "0250261");
        this.DITKORT_VARENR_MAP.put("2316 III N\u00d8", "0250262");
        this.DITKORT_VARENR_MAP.put("2316 III SV", "0250263");
        this.DITKORT_VARENR_MAP.put("2316 III S\u00d8", "0250264");
        this.DITKORT_VARENR_MAP.put("2316 IV NV", "0250265");
        this.DITKORT_VARENR_MAP.put("2316 IV N\u00d8", "0250266");
        this.DITKORT_VARENR_MAP.put("2316 IV SV", "0250267");
        this.DITKORT_VARENR_MAP.put("2316 IV S\u00d8", "0250268");
        this.DITKORT_VARENR_MAP.put("2317 I NV", "0250269");
        this.DITKORT_VARENR_MAP.put("2317 I SV", "0250270");
        this.DITKORT_VARENR_MAP.put("2317 II NV", "0250271");
        this.DITKORT_VARENR_MAP.put("2317 III NV", "0250272");
        this.DITKORT_VARENR_MAP.put("2317 III N\u00d8", "0250273");
        this.DITKORT_VARENR_MAP.put("2317 III SV", "0250274");
        this.DITKORT_VARENR_MAP.put("2317 III S\u00d8", "0250275");
        this.DITKORT_VARENR_MAP.put("2317 IV NV", "0250276");
        this.DITKORT_VARENR_MAP.put("2317 IV N\u00d8", "0250277");
        this.DITKORT_VARENR_MAP.put("2317 IV SV", "0250278");
        this.DITKORT_VARENR_MAP.put("2317 IV S\u00d8", "0250279");
        this.DITKORT_VARENR_MAP.put("2318 I N\u00d8", "0250280");
        this.DITKORT_VARENR_MAP.put("2318 I S\u00d8", "0250281");
        this.DITKORT_VARENR_MAP.put("2318 II N\u00d8", "0250282");
        this.DITKORT_VARENR_MAP.put("2318 II S\u00d8", "0250283");
        this.DITKORT_VARENR_MAP.put("2318 III N\u00d8", "0250284");
        this.DITKORT_VARENR_MAP.put("2318 II NV", "0250285");
        this.DITKORT_VARENR_MAP.put("2318 III S\u00d8", "0250286");
        this.DITKORT_VARENR_MAP.put("2318 II SV", "0250287");
        this.DITKORT_VARENR_MAP.put("2318 I SV", "0250289");
        this.DITKORT_VARENR_MAP.put("2411 I NV", "0250290");
        this.DITKORT_VARENR_MAP.put("2411 I N\u00d8", "0250291");
        this.DITKORT_VARENR_MAP.put("2411 I SV", "0250292");
        this.DITKORT_VARENR_MAP.put("2411 I S\u00d8", "0250293");
        this.DITKORT_VARENR_MAP.put("2411 II NV", "0250294");
        this.DITKORT_VARENR_MAP.put("2411 II N\u00d8", "0250295");
        this.DITKORT_VARENR_MAP.put("2411 II SV", "0250296");
        this.DITKORT_VARENR_MAP.put("2411 II S\u00d8", "0250297");
        this.DITKORT_VARENR_MAP.put("2411 III N\u00d8", "0250298");
        this.DITKORT_VARENR_MAP.put("2411 IV NV", "0250299");
        this.DITKORT_VARENR_MAP.put("2411 IV N\u00d8", "0250300");
        this.DITKORT_VARENR_MAP.put("2411 IV SV", "0250301");
        this.DITKORT_VARENR_MAP.put("2411 IV S\u00d8", "0250302");
        this.DITKORT_VARENR_MAP.put("2412 I NV", "0250303");
        this.DITKORT_VARENR_MAP.put("2412 I N\u00d8", "0250304");
        this.DITKORT_VARENR_MAP.put("2412 I SV", "0250305");
        this.DITKORT_VARENR_MAP.put("2412 I S\u00d8", "0250306");
        this.DITKORT_VARENR_MAP.put("2412 II N\u00d8", "0250308");
        this.DITKORT_VARENR_MAP.put("2412 II SV", "0250309");
        this.DITKORT_VARENR_MAP.put("2412 II S\u00d8", "0250310");
        this.DITKORT_VARENR_MAP.put("2412 III NV", "0250311");
        this.DITKORT_VARENR_MAP.put("2412 III N\u00d8", "0250312");
        this.DITKORT_VARENR_MAP.put("2412 III SV", "0250313");
        this.DITKORT_VARENR_MAP.put("2412 IV NV", "0250314");
        this.DITKORT_VARENR_MAP.put("2412 IV N\u00d8", "0250315");
        this.DITKORT_VARENR_MAP.put("2412 IV SV", "0250316");
        this.DITKORT_VARENR_MAP.put("2412 IV S\u00d8", "0250317");
        this.DITKORT_VARENR_MAP.put("2413 I NV", "0250318");
        this.DITKORT_VARENR_MAP.put("2413 I N\u00d8", "0250319");
        this.DITKORT_VARENR_MAP.put("2413 I SV", "0250320");
        this.DITKORT_VARENR_MAP.put("2413 I S\u00d8", "0250321");
        this.DITKORT_VARENR_MAP.put("2413 II NV", "0250322");
        this.DITKORT_VARENR_MAP.put("2413 II N\u00d8", "0250323");
        this.DITKORT_VARENR_MAP.put("2413 II SV", "0250324");
        this.DITKORT_VARENR_MAP.put("2413 II S\u00d8", "0250325");
        this.DITKORT_VARENR_MAP.put("2413 III N\u00d8", "0250327");
        this.DITKORT_VARENR_MAP.put("2413 III S\u00d8", "0250328");
        this.DITKORT_VARENR_MAP.put("2413 IV NV", "0250329");
        this.DITKORT_VARENR_MAP.put("2413 IV N\u00d8", "0250330");
        this.DITKORT_VARENR_MAP.put("2413 IV SV", "0250331");
        this.DITKORT_VARENR_MAP.put("2413 IV S\u00d8", "0250332");
        this.DITKORT_VARENR_MAP.put("2414 I SV", "0250333");
        this.DITKORT_VARENR_MAP.put("2414 II NV", "0250334");
        this.DITKORT_VARENR_MAP.put("2414 II N\u00d8", "0250335");
        this.DITKORT_VARENR_MAP.put("2414 II SV", "0250336");
        this.DITKORT_VARENR_MAP.put("2414 II S\u00d8", "0250337");
        this.DITKORT_VARENR_MAP.put("2414 III N\u00d8", "0250338");
        this.DITKORT_VARENR_MAP.put("2414 III S\u00d8", "0250339");
        this.DITKORT_VARENR_MAP.put("2415 III NV", "0250340");
        this.DITKORT_VARENR_MAP.put("2415 III SV", "0250341");
        this.DITKORT_VARENR_MAP.put("2415 IV NV", "0250342");
        this.DITKORT_VARENR_MAP.put("2415 IV SV", "0250343");
        this.DITKORT_VARENR_MAP.put("2417 IV NV", "0250344");
        this.DITKORT_VARENR_MAP.put("2417 IV N\u00d8", "0250345");
        this.DITKORT_VARENR_MAP.put("2417 IV SV", "0250346");
        this.DITKORT_VARENR_MAP.put("2417 IV S\u00d8", "0250347");
        this.DITKORT_VARENR_MAP.put("2511 I NV", "0250348");
        this.DITKORT_VARENR_MAP.put("2511 I SV", "0250349");
        this.DITKORT_VARENR_MAP.put("2511 III NV", "0250350");
        this.DITKORT_VARENR_MAP.put("2511 III N\u00d8", "0250351");
        this.DITKORT_VARENR_MAP.put("2511 III SV", "0250352");
        this.DITKORT_VARENR_MAP.put("2511 III S\u00d8", "0250353");
        this.DITKORT_VARENR_MAP.put("2511 IV NV", "0250354");
        this.DITKORT_VARENR_MAP.put("2511 IV N\u00d8", "0250355");
        this.DITKORT_VARENR_MAP.put("2511 IV SV", "0250356");
        this.DITKORT_VARENR_MAP.put("2511 IV S\u00d8", "0250357");
        this.DITKORT_VARENR_MAP.put("2512 I NV", "0250358");
        this.DITKORT_VARENR_MAP.put("2512 I N\u00d8", "0250359");
        this.DITKORT_VARENR_MAP.put("2512 I SV", "0250360");
        this.DITKORT_VARENR_MAP.put("2512 I S\u00d8", "0250361");
        this.DITKORT_VARENR_MAP.put("2512 II NV", "0250362");
        this.DITKORT_VARENR_MAP.put("2512 II SV", "0250363");
        this.DITKORT_VARENR_MAP.put("2512 II S\u00d8", "0250364");
        this.DITKORT_VARENR_MAP.put("2512 III NV", "0250365");
        this.DITKORT_VARENR_MAP.put("2512 III N\u00d8", "0250366");
        this.DITKORT_VARENR_MAP.put("2512 III SV", "0250367");
        this.DITKORT_VARENR_MAP.put("2512 III S\u00d8", "0250368");
        this.DITKORT_VARENR_MAP.put("2512 IV NV", "0250369");
        this.DITKORT_VARENR_MAP.put("2512 IV N\u00d8", "0250370");
        this.DITKORT_VARENR_MAP.put("2512 IV SV", "0250371");
        this.DITKORT_VARENR_MAP.put("2512 IV S\u00d8", "0250372");
        this.DITKORT_VARENR_MAP.put("2513 I NV", "0250373");
        this.DITKORT_VARENR_MAP.put("2513 I N\u00d8", "0250374");
        this.DITKORT_VARENR_MAP.put("2513 I SV", "0250375");
        this.DITKORT_VARENR_MAP.put("2513 I S\u00d8", "0250376");
        this.DITKORT_VARENR_MAP.put("2513 II NV", "0250377");
        this.DITKORT_VARENR_MAP.put("2513 II SV", "0250378");
        this.DITKORT_VARENR_MAP.put("2513 II S\u00d8", "0250379");
        this.DITKORT_VARENR_MAP.put("2513 III NV", "0250380");
        this.DITKORT_VARENR_MAP.put("2513 III N\u00d8", "0250381");
        this.DITKORT_VARENR_MAP.put("2513 III SV", "0250382");
        this.DITKORT_VARENR_MAP.put("2513 III S\u00d8", "0250383");
        this.DITKORT_VARENR_MAP.put("2513 IV NV", "0250384");
        this.DITKORT_VARENR_MAP.put("2513 IV N\u00d8", "0250385");
        this.DITKORT_VARENR_MAP.put("2513 IV SV", "0250386");
        this.DITKORT_VARENR_MAP.put("2513 IV S\u00d8", "0250387");
        this.DITKORT_VARENR_MAP.put("2514 I NV", "0250388");
        this.DITKORT_VARENR_MAP.put("2514 I N\u00d8", "0250389");
        this.DITKORT_VARENR_MAP.put("2514 I SV", "0250390");
        this.DITKORT_VARENR_MAP.put("2514 I S\u00d8", "0250391");
        this.DITKORT_VARENR_MAP.put("2514 II NV", "0250392");
        this.DITKORT_VARENR_MAP.put("2514 II N\u00d8", "0250393");
        this.DITKORT_VARENR_MAP.put("2514 II SV", "0250394");
        this.DITKORT_VARENR_MAP.put("2514 II S\u00d8", "0250395");
        this.DITKORT_VARENR_MAP.put("2514 III NV", "0250396");
        this.DITKORT_VARENR_MAP.put("2514 III N\u00d8", "0250397");
        this.DITKORT_VARENR_MAP.put("2514 III SV", "0250398");
        this.DITKORT_VARENR_MAP.put("2514 III S\u00d8", "0250399");
        this.DITKORT_VARENR_MAP.put("2514 IV N\u00d8", "0250400");
        this.DITKORT_VARENR_MAP.put("2514 IV SV", "0250401");
        this.DITKORT_VARENR_MAP.put("2514 IV S\u00d8", "0250402");
        this.DITKORT_VARENR_MAP.put("2415 II S\u00d8", "0250403");
        this.DITKORT_VARENR_MAP.put("2416 II S\u00d8", "0250404");
        this.DITKORT_VARENR_MAP.put("2612 III SV", "0250405");
        this.DITKORT_VARENR_MAP.put("2613 IV NV", "0250406");
        this.DITKORT_VARENR_MAP.put("2613 IV SV", "0250407");
        this.DITKORT_VARENR_MAP.put("2812 I N\u00d8", "0250408");
        this.DITKORT_VARENR_MAP.put("2812 I SV", "0250409");
        this.DITKORT_VARENR_MAP.put("2812 II NV", "0250410");
        this.DITKORT_VARENR_MAP.put("2812 II N\u00d8", "0250411");
        this.DITKORT_VARENR_MAP.put("2812 II SV", "0250412");
        this.DITKORT_VARENR_MAP.put("2812 II S\u00d8", "0250413");
        this.DITKORT_VARENR_MAP.put("2812 III N\u00d8", "0250414");
        this.DITKORT_VARENR_MAP.put("2812 III S\u00d8", "0250415");
        this.DITKORT_VARENR_MAP.put("2812 IV S\u00d8", "0250416");
        this.DITKORT_VARENR_MAP.put("Amager & K\u00f8benhavn", "0250417");
        this.DITKORT_VARENR_MAP.put("Als", "0250418");
        this.DITKORT_VARENR_MAP.put("Silkeborg", "0250419");
        this.DITKORT_VARENR_MAP.put("T\u00e5singe", "0250420");
        this.DITKORT_VARENR_MAP.put("R\u00f8m\u00f8", "0250421");
        this.DITKORT_VARENR_MAP.put("\u00c6r\u00f8", "0250422");
        this.DITKORT_VARENR_MAP.put("Sj\u00e6llands Odde", "0250423");
        this.DITKORT_VARENR_MAP.put("Nordsj\u00e6lland", "0250424");
        this.DITKORT_VARENR_MAP.put("Langeland", "0250425");
        this.DITKORT_VARENR_MAP.put("Skagen", "0250426");
        this.DITKORT_VARENR_MAP.put("Sams\u00f8", "0250427");
        this.DITKORT_VARENR_MAP.put("Bornholm", "0250428");
        this.DITKORT_VARENR_MAP.put("L\u00e6s\u00f8", "0250429");
        this.DITKORT_VARENR_MAP.put("M\u00f8n", "0250430");
        this.DITKORT_VARENR_MAP.put("Fan\u00f8", "0250431");
        this.DITKORT_VARENR_MAP.put("Hessel\u00f8", "0250432");
        this.DITKORT_VARENR_MAP.put("Anholt", "0250433");
        this.DITKORT_VARENR_MAP.put("Fem\u00f8", "0250434");
        this.DITKORT_VARENR_MAP.put("Fej\u00f8", "0250435");
        this.DITKORT_VARENR_MAP.put("Sejer\u00f8", "0250436");
        this.DITKORT_VARENR_MAP.put("Fur", "0250437");
        this.DITKORT_VARENR_MAP.put("Endelave", "0250438");
        this.DITKORT_VARENR_MAP.put("Ven\u00f8", "0250439");
        this.DITKORT_VARENR_MAP.put("Bog\u00f8", "0250440");
        this.DITKORT_VARENR_MAP.put("Mand\u00f8", "0250441");
        this.DITKORT_VARENR_MAP.put("Odense", "0250442");
        this.DITKORT_VARENR_MAP.put("\u00c5rhus", "0250443");
        this.DITKORT_VARENR_MAP.put("Aalborg", "0250444");
    }
    
    public void init() {
        if (Constant.debugMode) {
            System.out.println("init starts now");
        }
        this.downloadLogin = this.getParameter("login");
        this.downloadPassword = this.getParameter("password");
        final String oversigtskort = this.getParameter("oversigtskort");
        final String language = this.getParameter("language");
        this.produkt = this.getParameter("produkt");
        if (Constant.debugMode) {
            System.out.println("produkt: " + this.produkt + "\toversigtskort: " + oversigtskort + "\tsprog: " + language);
        }
        try {
            if (language == null || language.equals("dk")) {
                this.myResource.setLocale("dk");
            }
            else {
                this.myResource.setLocale(language);
                this.c_ditkortView.changeLocaleInDitkortView();
                this.c_zoomView.changeLocaleInZoomView();
                ditkort.isInEnglish = true;
            }
            this.c_searchView = new searchCityView();
            this.c_navigationskortView = new navigationskortView();
            this.c_serviceView = new selectServiceView();
            this.c_centerCoordsInput = new centerCoordsInput();
            if (oversigtskort != null && oversigtskort.equals("oversigtskort")) {
                if (Constant.debugMode) {
                    System.out.println("Calling add1953");
                }
                this.c_serviceView.add1953();
            }
            else if (oversigtskort != null && oversigtskort.equals("false")) {
                this.c_serviceView.addHistoricalMapsToList();
            }
            else {
                this.c_serviceView.addHistoricalMapsToList();
            }
            this.c_ditkortViewNavngiv.setFormatCheckBoxes(this.c_formatCheckboxes);
            this.c_ditkortView.setFormatCheckBoxes(this.c_formatCheckboxes);
            try {
                if (this.produkt != null) {
                    if (this.produkt.equals("DTK25")) {
                        this.c_formatCheckboxes.setUTMText(Constant.servicename_DTK25);
                        this.c_serviceView.c_serviceName.setSelectedIndex(Constant.DTK25);
                    }
                    else if (this.produkt.equals("DTK100")) {
                        this.c_formatCheckboxes.setUTMText(Constant.servicename_DTK100);
                        this.c_serviceView.c_serviceName.setSelectedIndex(Constant.DTK100);
                    }
                    else if (this.produkt.equals("DTK20_1870H")) {
                        this.servicename = Constant.servicename_H1870;
                        this.c_serviceView.c_serviceName.setSelectedIndex(Constant.H1870);
                        this.c_formatCheckboxes.showCheckboxes(false);
                    }
                    else if (this.produkt.equals("DTK20_1928L")) {
                        this.servicename = Constant.servicename_H1870;
                        this.c_serviceView.c_serviceName.setSelectedIndex(Constant.L1928);
                        this.c_formatCheckboxes.showCheckboxes(false);
                    }
                    else if (this.produkt.equals("DTK25_1953")) {
                        this.servicename = Constant.servicename_1953;
                        if (oversigtskort.equals("oversigtskort")) {
                            this.c_serviceView.c_serviceName.setSelectedIndex(2);
                        }
                        else {
                            this.c_serviceView.c_serviceName.setSelectedIndex(Constant.D25_1953);
                        }
                        this.c_formatCheckboxes.showCheckboxes(false);
                    }
                    else if (this.produkt.equals("DTK25_1980")) {
                        this.servicename = Constant.servicename_1980;
                        this.c_serviceView.c_serviceName.setSelectedIndex(Constant.D25_1980);
                        this.c_formatCheckboxes.showCheckboxes(false);
                    }
                }
                else {
                    this.c_serviceView.c_serviceName.setSelectedIndex(0);
                }
            }
            catch (IllegalArgumentException e) {
                if (Constant.debugMode) {
                    System.out.println("init()'s setSelectedIndex threw an exception: " + e.getMessage());
                }
            }
            if (oversigtskort != null && !oversigtskort.equals("false") && (!this.produkt.equals("DTK20_1870H") || !this.produkt.equals("DTK20_1928L"))) {
                if (oversigtskort.equals("oversigtskort")) {
                    this.c_oversigtskortEnable = true;
                    this.initialiseOversigtskortNavneHashmap();
                    this.setHashMap();
                }
                else if (oversigtskort.equals("egnskort")) {
                    if (Constant.debugMode) {
                        System.out.println("User has chosen egnskort");
                    }
                    this.egnskortEnabled = true;
                    this.initialiseOversigtskortNavneHashmap();
                    this.setHashMap();
                }
                else {
                    System.out.println("An error has occured, unable to determine the desired service.");
                }
            }
            else if (Constant.debugMode) {
                System.out.println("Parameter 'oversigtskort' is NOT set.");
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("init() threw an exception: " + e.getCause());
        }
        if (oversigtskort.equals("egnskort")) {
            this.c_formatView = new formatView("egnskort");
        }
        else {
            this.c_formatView = new formatView((String)null);
        }
        this.c_serviceView.setFormatText();
        if (!this.c_oversigtskortEnable || !this.egnskortEnabled) {
            this.c_formatView.enableToolTip();
        }
        final JSObject win = JSObject.getWindow((Applet)this);
        final Object loc = win.eval("window.location.hostname;");
        final String hostname = loc.toString();
        if ("www.scanmaps.dk".equals(hostname) || "www.scanmaps.com".equals(hostname) || "linux.scanmaps.dk".equals(hostname) || "129.142.228.51".equals(hostname) || "83.72.192.6".equals(hostname) || "192.168.0.2".equals(hostname)) {
            this.c_appletContext = this.getAppletContext();
            this.initLookAndFeel();
            this.initGUI();
            this.isInitiated = true;
        }
        else {
            System.out.println("I can only start from scanmaps.dk");
            win.eval("doHideDitkortLayers();");
        }
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    static /* synthetic */ void access$8(final ditkort ditkort, final boolean c_kortUdsnitStatus) {
        ditkort.c_kortUdsnitStatus = c_kortUdsnitStatus;
    }
    
    static /* synthetic */ void access$20(final ditkort ditkort, final Egn result) {
        ditkort.result = result;
    }
    
    static /* synthetic */ void access$22(final ditkort ditkort, final String productno) {
        ditkort.productno = productno;
    }
    
    static /* synthetic */ void access$26(final ditkort ditkort, final boolean isScrolled) {
        ditkort.isScrolled = isScrolled;
    }
    
    static /* synthetic */ void access$30(final ditkort ditkort, final int adjustCenterCoord_X) {
        ditkort.AdjustCenterCoord_X = adjustCenterCoord_X;
    }
    
    static /* synthetic */ void access$31(final ditkort ditkort, final int adjustCenterCoord_Y) {
        ditkort.AdjustCenterCoord_Y = adjustCenterCoord_Y;
    }
    
    static /* synthetic */ void access$32(final ditkort ditkort, final int adjustOverviewField) {
        ditkort.AdjustOverviewField = adjustOverviewField;
    }
    
    static /* synthetic */ void access$33(final ditkort ditkort, final int gridsize_FACTOR) {
        ditkort.GRIDSIZE_FACTOR = gridsize_FACTOR;
    }
    
    static /* synthetic */ void access$34(final ditkort ditkort, final String servicename) {
        ditkort.servicename = servicename;
    }
    
    class tabbedAdapter extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            if (ditkort.this.c_ditkortViewNavngiv.c_centerX != ditkort.this.c_ditkortView.c_centerX || ditkort.this.c_ditkortViewNavngiv.c_centerY != ditkort.this.c_ditkortView.c_centerY) {
                ditkort.this.c_ditkortViewNavngiv.c_centerX = ditkort.this.c_ditkortView.c_centerX;
                ditkort.this.c_ditkortViewNavngiv.c_centerY = ditkort.this.c_ditkortView.c_centerY;
                ditkort.this.c_ditkortViewNavngiv.updateMap();
            }
        }
    }
    
    class formatViewTextFieldListener extends KeyAdapter
    {
        public void keyPressed(final KeyEvent e) {
            final String name = ditkort.this.c_formatView.c_navngivTextField.getText().trim();
            boolean nameOK = false;
            try {
                if (name.length() > 2) {
                    nameOK = true;
                }
            }
            catch (PatternSyntaxException pse) {
                if (Constant.debugMode) {
                    pse.printStackTrace();
                }
            }
            if (nameOK && (ditkort.this.c_formatView.c_planoButton.isSelected() || ditkort.this.c_formatView.c_falsetButton.isSelected() || ditkort.this.c_formatView.c_indrammetButton.isSelected() || ditkort.this.c_formatView.c_lamineretPlanoButton.isSelected() || ditkort.this.c_formatView.c_lamineretFalsetButton.isSelected())) {
                ditkort.this.c_formatView.c_addtocart.setToolTipText(ditkort.this.myResource.getResource("ditkort.knap.laegikurv.tip"));
                if (ditkort.this.c_ditkortView.isMapShown() || ditkort.this.egnskortEnabled) {
                    ditkort.this.c_formatView.c_addtocart.setEnabled(true);
                }
            }
            else {
                if (!ditkort.this.c_oversigtskortEnable) {
                    ditkort.this.c_formatView.c_addtocart.setToolTipText(ditkort.this.myResource.getResource("format.laegikurvtip"));
                }
                ditkort.this.c_formatView.c_addtocart.setEnabled(false);
            }
            nameOK = false;
        }
    }
    
    class formatViewOpklaebningsListener extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            if (ditkort.this.c_formatView.c_navngivTextField.getText().length() > 0 && (ditkort.this.c_formatView.c_planoButton.isSelected() || ditkort.this.c_formatView.c_falsetButton.isSelected() || ditkort.this.c_formatView.c_indrammetButton.isSelected() || ditkort.this.c_formatView.c_lamineretPlanoButton.isSelected() || ditkort.this.c_formatView.c_lamineretFalsetButton.isSelected())) {
                if (ditkort.this.c_ditkortView.isMapShown()) {
                    ditkort.this.c_formatView.c_addtocart.setEnabled(true);
                }
                if (ditkort.this.egnskortEnabled && (ditkort.this.c_formatView.c_falsetButton.isSelected() || ditkort.this.c_formatView.c_indrammetButton.isSelected() || ditkort.this.c_formatView.c_lamineretPlanoButton.isSelected() || ditkort.this.c_formatView.c_lamineretFalsetButton.isSelected())) {
                    ditkort.this.c_formatView.c_addtocart.setEnabled(false);
                }
            }
            else {
                ditkort.this.c_formatView.c_addtocart.setToolTipText(ditkort.this.myResource.getResource("format.laegikurvtip"));
                ditkort.this.c_formatView.c_addtocart.setEnabled(false);
            }
        }
    }
    
    class heleKortetKasseAdapter extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            if (ditkort.this.c_kortUdsnitStatus) {
                ditkort.this.c_heleKortetKasseLabel.setEnabled(false);
                ditkort.this.c_ditkortView.updateMap();
                try {
                    ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse1");
                }
                ditkort.access$8(ditkort.this, false);
                ditkort.this.c_ditkortView.c_kortUdsnitShown = false;
            }
        }
    }
    
    class kortUdsnitKasse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            ditkort.access$8(ditkort.this, true);
            if (!ditkort.this.c_heleKortetKasseLabel.isEnabled()) {
                ditkort.this.c_heleKortetKasseLabel.setEnabled(true);
            }
            if (e.getX() >= 0 && e.getX() <= 30 && e.getY() >= 0 && e.getY() <= 24) {
                ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(3));
                try {
                    ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse1.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse1");
                }
                if (ditkort.this.c_ditkortView.showZoomViewOne()) {
                    ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
            else if (e.getX() >= 31 && e.getX() <= 60 && e.getY() >= 0 && e.getY() <= 24) {
                ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(3));
                try {
                    ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse2.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse2");
                }
                if (ditkort.this.c_ditkortView.showZoomViewTwo()) {
                    ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
            else if (e.getX() >= 0 && e.getX() <= 30 && e.getY() >= 25 && e.getY() <= 48) {
                ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(3));
                try {
                    ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse3.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse3");
                }
                if (ditkort.this.c_ditkortView.showZoomViewThree()) {
                    ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
            else if (e.getX() >= 31 && e.getX() <= 60 && e.getY() >= 25 && e.getY() <= 48) {
                ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(3));
                try {
                    ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse4.jpg")));
                }
                catch (MalformedURLException URLe) {
                    System.err.println("Couldn't set kasse4");
                }
                if (ditkort.this.c_ditkortView.showZoomViewFour()) {
                    ditkort.this.c_kortUdsnitKasseLabel.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
        }
    }
    
    class ditkortAdapter extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            final SwingWorker worker = new SwingWorker() {
                public Object construct() {
                    final int x = e.getX();
                    final int y = e.getY() + 30;
                    if (ditkort.this.c_centrer) {
                        final Cursor oldCursor = ditkort.this.c_ditkortView.getCursor();
                        ditkort.this.c_ditkortView.setCursor(Cursor.getPredefinedCursor(3));
                        ditkort.this.c_ditkortView.ditkortUpdateCenter(x, y);
                        if (ditkort.this.c_ditkortView.updateMap()) {
                            ditkort.this.c_ditkortView.setCursor(oldCursor);
                        }
                        ditkort.this.c_centerCoordsInput.setCenterX(ditkort.this.c_ditkortView.c_centerX);
                        ditkort.this.c_centerCoordsInput.setCenterY(ditkort.this.c_ditkortView.c_centerY);
                        ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.c_centerX, ditkort.this.c_ditkortView.c_centerY, ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected());
                        if (Constant.debugMode) {
                            ditkort.this.c_ditkortFrame.setTitle("x=" + new Integer((int)ditkort.this.c_ditkortView.c_centerX) + ", y=" + new Integer((int)ditkort.this.c_ditkortView.c_centerY));
                        }
                        ditkort.this.c_navigationskortView.updateHotspotPosition((int)ditkort.this.c_ditkortView.c_centerX, (int)ditkort.this.c_ditkortView.c_centerY);
                        if (ditkort.this.c_kortUdsnitStatus) {
                            if (Constant.coordsValues) {
                                System.out.println("i c_kortUdsnitStatus");
                            }
                            ditkort.this.c_heleKortetKasseLabel.setEnabled(false);
                            ditkort.this.c_ditkortView.updateMap();
                            try {
                                ditkort.this.c_kortUdsnitKasseLabel.setIcon(new ImageIcon(new URL(String.valueOf(ditkort.this.c_urlBase) + "kasse.jpg")));
                            }
                            catch (MalformedURLException URLe) {
                                System.err.println("Couldn't set c_kortUdsnitKasseLabel");
                            }
                            ditkort.access$8(ditkort.this, false);
                            ditkort.this.c_ditkortView.c_kortUdsnitShown = false;
                        }
                    }
                    else if (ditkort.this.c_zoom) {
                        final Cursor oldCursor = ditkort.this.c_ditkortView.getCursor();
                        ditkort.this.c_ditkortView.setCursor(Cursor.getPredefinedCursor(3));
                        if (Constant.coordsValues) {
                            System.out.println("i c_zoom");
                        }
                        if (ditkort.this.c_ditkortView.c_kortUdsnitShown) {
                            ditkort.this.c_ditkortView.ditkortUpdateZoomCoord(x - 28, y - 50);
                            if (Constant.coordsValues) {
                                System.out.println("If: ditkortUpdateZoomCoord X: " + x + " Y: " + y);
                            }
                        }
                        else {
                            ditkort.this.c_ditkortView.ditkortUpdateZoomCoord(x - 22, y - 53);
                            if (Constant.coordsValues) {
                                System.out.println("Else: ditkortUpdateZoomCoord X: " + x + " Y: " + y);
                            }
                        }
                        if (ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.c_zoomCoordX, ditkort.this.c_ditkortView.c_zoomCoordY, ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected())) {
                            ditkort.this.c_ditkortView.setCursor(oldCursor);
                        }
                    }
                    return true;
                }
            };
            worker.start();
        }
        
        public void mouseEntered(final MouseEvent e) {
        }
        
        public void mouseExited(final MouseEvent e) {
        }
    }
    
    class navigationskortAdapter extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            if (Constant.debugMode || !ditkort.this.c_oversigtskortEnable) {
                int x = e.getX() - 6;
                int y = e.getY() - 28;
                ditkort.this.c_navigationskortView.setCursor(Cursor.getPredefinedCursor(3));
                if (x < 0 || x > 237 || y < 0 || y > 193) {
                    x = 0;
                    y = 0;
                }
                ditkort.this.c_ditkortView.navigationskortUpdateCenter(x, y);
                ditkort.this.c_ditkortView.updateMap();
                ditkort.this.c_navigationskortView.updateHotspotPosition((int)ditkort.this.c_ditkortView.c_centerX, (int)ditkort.this.c_ditkortView.c_centerY);
                if (ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.c_centerX, ditkort.this.c_ditkortView.c_centerY, ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected())) {
                    ditkort.this.c_navigationskortView.setCursor(Cursor.getPredefinedCursor(12));
                }
                ditkort.this.c_centerCoordsInput.setCenterX(ditkort.this.c_ditkortView.c_centerX);
                ditkort.this.c_centerCoordsInput.setCenterY(ditkort.this.c_ditkortView.c_centerY);
            }
        }
        
        public void mouseReleased(final MouseEvent e) {
            ditkort.this.c_navigationskortView.setCursor(Cursor.getPredefinedCursor(12));
        }
        
        public void mouseEntered(final MouseEvent e) {
            ditkort.this.c_navigationskortView.setCursor(Cursor.getPredefinedCursor(12));
        }
        
        public void mouseExited(final MouseEvent e) {
            ditkort.this.c_navigationskortView.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    class egnskortViewAdapter extends MouseInputAdapter
    {
        public void mouseMoved(final MouseEvent e) {
        }
    }
    
    class Historical1870Adapter extends MouseInputAdapter
    {
        Point point;
        private int padding_x;
        private int padding_y;
        private int vertical;
        private int horizontal;
        private Integer region_x;
        private Integer region_y;
        
        public Historical1870Adapter() {
            this.point = new Point();
            this.padding_x = 56;
            this.padding_y = 75;
            this.vertical = 1873 / this.padding_x;
            this.horizontal = 2241 / this.padding_y;
        }
        
        public void mouseMoved(final MouseEvent e) {
            this.point.x = e.getX();
            this.point.y = e.getY();
            for (int x = 0; x < this.vertical; ++x) {
                for (int y = 0; y < this.horizontal; ++y) {
                    if (this.point.x > x * this.padding_x && this.point.x < x * this.padding_x + this.padding_x && this.point.y > y * this.padding_y && this.point.y < y * this.padding_y + this.padding_y) {
                        this.region_x = x;
                        this.region_y = y;
                        break;
                    }
                }
            }
        }
        
        public void mouseClicked(final MouseEvent e) {
            final String region = String.valueOf(this.region_x.toString()) + this.region_y.toString();
            ditkort.this.c_ditkortView.setMapDimension(375, 450);
        }
    }
    
    class DanmarksKortAdapter extends MouseAdapter
    {
        private int LAESOE_X0;
        private int LAESOE_X1;
        private int LAESOE_X2;
        private int LAESOE_Y0;
        private int LAESOE_Y1;
        private int LAESOE_Y2;
        
        DanmarksKortAdapter() {
            this.LAESOE_X0 = 300;
            this.LAESOE_X1 = 324;
            this.LAESOE_X2 = 352;
            this.LAESOE_Y0 = 75;
            this.LAESOE_Y1 = 102;
            this.LAESOE_Y2 = 132;
        }
        
        public void mouseClicked(final MouseEvent e) {
            final SwingWorker worker = new SwingWorker() {
                public Object construct() {
                    if (ditkort.this.c_oversigtskort.getSpecialRegion() != 0) {
                        ditkort.this.c_oversigtskort.setSpecialRegion(0);
                    }
                    ditkort.this.c_oversigtskort.showClickedBox(true);
                    ditkort.this.c_oversigtskort.setCursor(Cursor.getPredefinedCursor(3));
                    ditkort.this.c_oversigtskort.x = e.getX() - ditkort.this.AdjustOverviewField;
                    ditkort.this.c_oversigtskort.y = e.getY();
                    if (Constant.coordsValues) {
                        System.out.println("ditkort mouseClicked(), c_oversigtskort.x: " + ditkort.this.c_oversigtskort.x + " c_oversigtskort.y: " + ditkort.this.c_oversigtskort.y);
                    }
                    if (ditkort.this.c_serviceView.getServiceName().equals(Constant.servicename_DTK25) || ditkort.this.c_serviceView.getServiceName().equals(Constant.servicename_1953)) {
                        if (ditkort.this.c_oversigtskort.x > 351 && ditkort.this.c_oversigtskort.x < 398 && ditkort.this.c_oversigtskort.y > 187 && ditkort.this.c_oversigtskort.y < 225) {
                            DanmarksKortAdapter.this.handleAnholt_DTK25();
                        }
                        else if (ditkort.this.c_oversigtskort.x > DanmarksKortAdapter.this.LAESOE_X0 && ditkort.this.c_oversigtskort.x < DanmarksKortAdapter.this.LAESOE_X2 && ditkort.this.c_oversigtskort.y > DanmarksKortAdapter.this.LAESOE_Y0 && ditkort.this.c_oversigtskort.y < DanmarksKortAdapter.this.LAESOE_Y2) {
                            DanmarksKortAdapter.this.handleLaesoe_DTK25();
                        }
                        else {
                            DanmarksKortAdapter.this.JavaCoordToPPCoord();
                            DanmarksKortAdapter.this.drawBox();
                        }
                    }
                    else if (ditkort.this.c_serviceView.getServiceName().equals(Constant.servicename_DTK100)) {
                        DanmarksKortAdapter.this.JavaCoordToPPCoord();
                        if (ditkort.this.c_oversigtskort.px == 0 && ditkort.this.c_oversigtskort.py == 5) {
                            ditkort.this.c_oversigtskort.setSpecialRegion(2113);
                        }
                        else if (ditkort.this.c_oversigtskort.px == 0 && ditkort.this.c_oversigtskort.py == 4) {
                            ditkort.this.c_oversigtskort.setSpecialRegion(2114);
                        }
                        else if (ditkort.this.c_oversigtskort.px == 4 && ditkort.this.c_oversigtskort.py == 5) {
                            ditkort.this.c_oversigtskort.setSpecialRegion(2513);
                        }
                        if (ditkort.this.c_oversigtskort.px == 3 && ditkort.this.c_oversigtskort.py == 1) {
                            DanmarksKortAdapter.this.drawBox(-3, 0);
                        }
                        else if (ditkort.this.c_oversigtskort.px == 4 && (ditkort.this.c_oversigtskort.py == 0 || ditkort.this.c_oversigtskort.py == 1)) {
                            ditkort.this.c_oversigtskort.px = 4;
                            ditkort.this.c_oversigtskort.py = 0;
                            DanmarksKortAdapter.this.drawBox(4, 17);
                        }
                        else if (ditkort.this.c_oversigtskort.x > 163 && ditkort.this.c_oversigtskort.x < 282 && ditkort.this.c_oversigtskort.y > 0 && ditkort.this.c_oversigtskort.y < 76) {
                            ditkort.this.c_oversigtskort.px = 2;
                            ditkort.this.c_oversigtskort.py = 0;
                            DanmarksKortAdapter.this.drawBox(-23, 0);
                        }
                        else if (ditkort.this.c_oversigtskort.x > 93 && ditkort.this.c_oversigtskort.x < 163 && ditkort.this.c_oversigtskort.y > 0 && ditkort.this.c_oversigtskort.y < 76) {
                            ditkort.this.c_oversigtskort.px = 1;
                            ditkort.this.c_oversigtskort.py = 0;
                        }
                        else {
                            DanmarksKortAdapter.this.drawBox();
                        }
                    }
                    else {
                        System.out.println("Can't draw box!");
                    }
                    if (Constant.coordsValues) {
                        System.out.println("DanmarksKortAdapter, c_oversigtskort.px: " + ditkort.this.c_oversigtskort.px + " c_oversigtskort.py: " + ditkort.this.c_oversigtskort.py);
                    }
                    if (Constant.coordsValues) {
                        System.out.println("MouseEvent X: " + ditkort.this.c_oversigtskort.px + " MouseEvent Y: " + ditkort.this.c_oversigtskort.py + "\n");
                    }
                    ditkort.this.updateDanmarksKortNavn(ditkort.this.c_oversigtskort.px, ditkort.this.c_oversigtskort.py);
                    ditkort.this.c_oversigtskort.setCursor(Cursor.getPredefinedCursor(0));
                    return true;
                }
            };
            worker.start();
        }
        
        public void JavaCoordToPPCoord() {
            ditkort.this.c_oversigtskort.px = (int)(ditkort.this.c_oversigtskort.x / (Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR));
            ditkort.this.c_oversigtskort.py = (int)(ditkort.this.c_oversigtskort.y / (Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR));
        }
        
        public void drawBox() {
            ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR), 0);
        }
        
        public void drawBox(final int adjust_X, final int adjust_Y) {
            ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField + adjust_X), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + adjust_Y), 0);
        }
        
        public void handleAnholt_DTK25() {
            ditkort.this.c_oversigtskort.px = 16;
            ditkort.this.c_oversigtskort.py = 10;
            ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField - 7.0f), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + 5.0f), 0);
        }
        
        public void handleLaesoe_DTK25() {
            if (ditkort.this.c_oversigtskort.x >= this.LAESOE_X0 && ditkort.this.c_oversigtskort.x <= this.LAESOE_X1 && ditkort.this.c_oversigtskort.y >= this.LAESOE_Y0 && ditkort.this.c_oversigtskort.y <= this.LAESOE_Y1) {
                ditkort.this.c_oversigtskort.px = 13;
                ditkort.this.c_oversigtskort.py = 4;
                ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField - 4.0f), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + 7.0f), 0);
            }
            else if (ditkort.this.c_oversigtskort.x > this.LAESOE_X1 && ditkort.this.c_oversigtskort.x <= this.LAESOE_X2 && ditkort.this.c_oversigtskort.y >= this.LAESOE_Y0 && ditkort.this.c_oversigtskort.y <= this.LAESOE_Y1) {
                ditkort.this.c_oversigtskort.px = 14;
                ditkort.this.c_oversigtskort.py = 4;
                ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField - 3.0f), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + 7.0f), 0);
            }
            else if (ditkort.this.c_oversigtskort.x >= this.LAESOE_X0 && ditkort.this.c_oversigtskort.x <= this.LAESOE_X1 && ditkort.this.c_oversigtskort.y > this.LAESOE_Y1 && ditkort.this.c_oversigtskort.y <= this.LAESOE_Y2) {
                ditkort.this.c_oversigtskort.px = 13;
                ditkort.this.c_oversigtskort.py = 5;
                ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField - 4.0f), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + 8.0f), 0);
            }
            else if (ditkort.this.c_oversigtskort.x >= this.LAESOE_X1 && ditkort.this.c_oversigtskort.x <= this.LAESOE_X2 && ditkort.this.c_oversigtskort.y >= this.LAESOE_Y1 && ditkort.this.c_oversigtskort.y <= this.LAESOE_Y2) {
                ditkort.this.c_oversigtskort.px = 14;
                ditkort.this.c_oversigtskort.py = 5;
                ditkort.this.c_oversigtskort.drawBox((int)(ditkort.this.c_oversigtskort.px * Constant.DANMARKSKORT_GRIDSIZE_X * ditkort.this.GRIDSIZE_FACTOR + ditkort.this.AdjustOverviewField - 3.0f), (int)(ditkort.this.c_oversigtskort.py * Constant.DANMARKSKORT_GRIDSIZE_Y * ditkort.this.GRIDSIZE_FACTOR + 8.0f), 0);
            }
            else if (Constant.debugMode) {
                System.out.println("WTF");
            }
        }
    }
    
    class EgnskortOversigtsAdapter extends MouseAdapter
    {
        public void updatePrice(final String priceclass) {
            ditkort.this.c_formatView.c_planoPris = ditkort.this.myResource.getResource("egnskort.pris.plano.pristype" + priceclass);
            ditkort.this.c_formatView.c_falsetPris = ditkort.this.myResource.getResource("egnskort.pris.falset.pristype" + priceclass);
            ditkort.this.c_formatView.c_indrammetPris = ditkort.this.myResource.getResource("egnskort.pris.indrammet.pristype" + priceclass);
            ditkort.this.c_formatView.c_lamineretPlanoPris = ditkort.this.myResource.getResource("egnskort.pris.lamineretPlano.pristype" + priceclass);
            ditkort.this.c_formatView.c_lamineretFalsetPris = ditkort.this.myResource.getResource("egnskort.pris.lamineretFalset.pristype" + priceclass);
            ditkort.this.c_formatView.updatePrice();
        }
        
        public void mouseClicked(final MouseEvent e) {
            final SwingWorker worker = new SwingWorker() {
                public Object construct() {
                    final Point point = e.getPoint();
                    ditkort.access$20(ditkort.this, EgnskortCoordinates.detectRectangularRegion(point));
                    if (ditkort.this.result != null) {
                        ditkort.this.c_ditkortFrame.setCursor(Cursor.getPredefinedCursor(3));
                        ditkort.access$22(ditkort.this, ditkort.this.result.getProductNo());
                        ditkort.this.c_egnskortView.setBorderAroundImage(true);
                        ditkort.this.c_egnskortView.updateImage(ditkort.this.productno, "preview");
                        final String detteKortEr = ditkort.this.myResource.getResource("ditkort.detteKortEr");
                        if (ditkort.this.productno.equals("0250432") || ditkort.this.productno.equals("0250433") || ditkort.this.productno.equals("0250434") || ditkort.this.productno.equals("0250435") || ditkort.this.productno.equals("0250436") || ditkort.this.productno.equals("0250437") || ditkort.this.productno.equals("0250437") || ditkort.this.productno.equals("0250438") || ditkort.this.productno.equals("0250439") || ditkort.this.productno.equals("0250440") || ditkort.this.productno.equals("0250441") || ditkort.this.productno.equals("0250420")) {
                            EgnskortOversigtsAdapter.this.updatePrice("A");
                            ditkort.this.c_egnskortView.setBorderAroundImage(false);
                            ditkort.this.c_ditkortInfo.setText("Dette kort er 60x54 cm");
                        }
                        else if (ditkort.this.productno.equals("0250421") || ditkort.this.productno.equals("0250431") || ditkort.this.productno.equals("0250442") || ditkort.this.productno.equals("0250443") || ditkort.this.productno.equals("0250444")) {
                            EgnskortOversigtsAdapter.this.updatePrice("B");
                            if (ditkort.this.productno.equals("0250421")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 48x83 cm");
                            }
                            else if (ditkort.this.productno.equals("0250431")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 48x77 cm");
                            }
                            else {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 78x69 cm");
                            }
                        }
                        else if (ditkort.this.productno.equals("0250417") || ditkort.this.productno.equals("0250419") || ditkort.this.productno.equals("0250422") || ditkort.this.productno.equals("0250427") || ditkort.this.productno.equals("0250429") || ditkort.this.productno.equals("0250430") || ditkort.this.productno.equals("0250423") || ditkort.this.productno.equals("0250418")) {
                            EgnskortOversigtsAdapter.this.updatePrice("C");
                            if (ditkort.this.productno.equals("0250417") || ditkort.this.productno.equals("0250419")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 117x100 cm");
                            }
                            else if (ditkort.this.productno.equals("0250422")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 98x89 cm");
                            }
                            else if (ditkort.this.productno.equals("0250417")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 57x122 cm");
                            }
                            else if (ditkort.this.productno.equals("0250429")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 98x94 cm");
                            }
                            else if (ditkort.this.productno.equals("0250430")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 122x95 cm");
                            }
                            else if (ditkort.this.productno.equals("0250423")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 160x109 cm");
                            }
                            else if (ditkort.this.productno.equals("0250427")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 57x122 cm");
                            }
                            else if (ditkort.this.productno.equals("0250418")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 123x113 cm");
                            }
                        }
                        else if (ditkort.this.productno.equals("0250424") || ditkort.this.productno.equals("0250425") || ditkort.this.productno.equals("0250426") || ditkort.this.productno.equals("0250428")) {
                            EgnskortOversigtsAdapter.this.updatePrice("D");
                            if (ditkort.this.productno.equals("0250424")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 203x107 cm");
                            }
                            else if (ditkort.this.productno.equals("0250425")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 101x211 cm");
                            }
                            else if (ditkort.this.productno.equals("0250426")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 188x100 cm");
                            }
                            else if (ditkort.this.productno.equals("0250428")) {
                                ditkort.this.c_ditkortInfo.setText(String.valueOf(detteKortEr) + " 133x153 cm");
                            }
                        }
                        ditkort.this.c_formatView.c_navngivTextField.setText(ditkort.this.result.getRegionName());
                        ditkort.this.c_formatView.c_navngivTextField.setEditable(false);
                        ditkort.access$26(ditkort.this, false);
                        ditkort.this.enableScrolling.setText(ditkort.this.myResource.getResource("knap.egnskort.zoom.stoerre"));
                        ditkort.this.c_formatView.c_addtocart.setEnabled(true);
                        ditkort.this.c_tabbedPane.setEnabledAt(1, true);
                        ditkort.this.c_tabbedPane.setSelectedIndex(1);
                        ditkort.this.c_ditkortFrame.setCursor(Cursor.getPredefinedCursor(0));
                    }
                    return true;
                }
            };
            worker.start();
        }
    }
    
    class centerCoordsInput extends JPanel implements ActionListener
    {
        static final long serialVersionUID = 0L;
        private JTextField c_centerXField;
        private JTextField c_centerYField;
        private JButton c_centerSearchButton;
        private JPanel c_centerCoordsPanel;
        
        public centerCoordsInput() {
            this.c_centerXField = new JTextField("585150");
            this.c_centerYField = new JTextField("6251825");
            this.c_centerSearchButton = new JButton(ditkort.this.myResource.getResource("knap.soeg"));
            (this.c_centerCoordsPanel = new JPanel()).setLayout(new BoxLayout(this.c_centerCoordsPanel, 2));
            this.c_centerCoordsPanel.setPreferredSize(new Dimension(243, 50));
            this.c_centerCoordsPanel.setBorder(BorderFactory.createTitledBorder(ditkort.this.myResource.getResource("findsted.centrumsoegning")));
            this.setAlignmentX(0.0f);
            this.c_centerXField.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent e) {
                }
                
                public void mouseEntered(final MouseEvent e) {
                }
                
                public void mouseExited(final MouseEvent e) {
                }
                
                public void mousePressed(final MouseEvent e) {
                }
                
                public void mouseReleased(final MouseEvent e) {
                }
            });
            this.c_centerYField.addMouseListener(new MouseListener() {
                public void mouseClicked(final MouseEvent e) {
                }
                
                public void mouseEntered(final MouseEvent e) {
                }
                
                public void mouseExited(final MouseEvent e) {
                }
                
                public void mousePressed(final MouseEvent e) {
                }
                
                public void mouseReleased(final MouseEvent e) {
                }
            });
            this.c_centerSearchButton.setActionCommand("centerSearch");
            this.c_centerSearchButton.addActionListener(this);
            this.c_centerCoordsPanel.add(this.c_centerXField);
            this.c_centerCoordsPanel.add(this.c_centerYField);
            this.c_centerCoordsPanel.add(this.c_centerSearchButton);
            this.add(this.c_centerCoordsPanel);
        }
        
        public void setCenterX(final Float centerX) {
            this.c_centerXField.setText(centerX.toString());
        }
        
        public void setCenterY(final Float centerY) {
            this.c_centerYField.setText(centerY.toString());
        }
        
        public void actionPerformed(final ActionEvent event) {
            if ("centerSearch".equals(event.getActionCommand())) {
                ditkort.this.c_ditkortView.c_centerX = Float.valueOf(this.c_centerXField.getText());
                ditkort.this.c_ditkortView.c_centerY = Float.valueOf(this.c_centerYField.getText());
                ditkort.this.c_ditkortView.updateMap();
                ditkort.this.c_navigationskortView.updateHotspotPosition((int)ditkort.this.c_ditkortView.c_centerX, (int)ditkort.this.c_ditkortView.c_centerY);
                ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.c_centerX, ditkort.this.c_ditkortView.c_centerY, ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected());
            }
        }
    }
    
    class service
    {
        private String displayLabel;
        private String name;
        private String layers;
        private float pixelSize;
        private int pixelPrmm;
        
        public service(final String name, final String layers, final String displayLabel, final float pixelSize, final int pixelPrmm) {
            this.pixelPrmm = 10;
            this.displayLabel = displayLabel;
            this.name = name;
            this.layers = layers;
            this.pixelSize = pixelSize;
            this.pixelPrmm = pixelPrmm;
        }
        
        public String toString() {
            return this.displayLabel;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getLayers() {
            return this.layers;
        }
        
        public float getPixelSize() {
            return this.pixelSize;
        }
        
        public int getPixelPrmm() {
            return this.pixelPrmm;
        }
    }
    
    class selectServiceView extends JPanel implements ActionListener
    {
        static final long serialVersionUID = 0L;
        public JComboBox c_serviceName;
        private JPanel c_serviceBoxPanel;
        public JCheckBox c_gridLayer;
        
        public void actionPerformed(final ActionEvent event) {
            final SwingWorker worker = new SwingWorker() {
                public Object construct() {
                    if ("service select".equals(event.getActionCommand())) {
                        selectServiceView.this.setService();
                    }
                    return true;
                }
            };
            worker.start();
        }
        
        public selectServiceView() {
            final Dimension boxdim = new Dimension(350, 25);
            (this.c_serviceName = new JComboBox()).setMinimumSize(boxdim);
            this.c_serviceName.setPreferredSize(boxdim);
            this.c_serviceName.setMaximumSize(boxdim);
            this.c_serviceName.setActionCommand("service select");
            this.c_serviceName.addActionListener(this);
            this.c_serviceName.addItem(new service(Constant.servicename_DTK25, Constant.layerName_DTK25, ditkort.this.myResource.getResource("ditkort.DTK25"), 1.25f, 20));
            this.c_serviceName.addItem(new service(Constant.servicename_DTK100, Constant.layerName_DTK100, ditkort.this.myResource.getResource("ditkort.DTK100"), 5.0f, 20));
            (this.c_serviceBoxPanel = new JPanel()).setLayout(new BoxLayout(this.c_serviceBoxPanel, 3));
            this.c_serviceBoxPanel.setBorder(BorderFactory.createTitledBorder(ditkort.this.myResource.getResource("findsted.korttype")));
            final Dimension dim = new Dimension(243, 105);
            this.c_serviceBoxPanel.setMinimumSize(dim);
            this.c_serviceBoxPanel.setPreferredSize(dim);
            this.c_serviceBoxPanel.setMaximumSize(dim);
            ditkort.this.c_formatCheckboxes.setAlignmentX(0.0f);
            this.c_serviceBoxPanel.add(this.c_serviceName);
            this.c_serviceBoxPanel.add(ditkort.this.c_formatCheckboxes);
            this.setGridCheckboxes();
            this.add(this.c_serviceBoxPanel);
        }
        
        public void add1953() {
            this.c_serviceName.addItem(new service(Constant.servicename_1953, Constant.layerName_1953, ditkort.this.myResource.getResource("ditkort.DTK1953"), 2.5f, 10));
        }
        
        public void setVisibility(final boolean set) {
            this.c_serviceBoxPanel.setEnabled(set);
            this.c_serviceName.setEnabled(set);
        }
        
        public void addHistoricalMapsToList() {
            this.c_serviceName.addItem(new service(Constant.servicename_H1870, Constant.layerName_H1870, ditkort.this.myResource.getResource("ditkort.DTK1870"), 1.0f, 20));
            this.c_serviceName.addItem(new service(Constant.servicename_L1928, Constant.layerName_L1928, ditkort.this.myResource.getResource("ditkort.DTK1920"), 1.0f, 20));
            this.c_serviceName.addItem(new service(Constant.servicename_1953, Constant.layerName_1953, ditkort.this.myResource.getResource("ditkort.DTK1953"), 2.5f, 10));
            this.c_serviceName.addItem(new service(Constant.servicename_1980, Constant.layerName_1980, ditkort.this.myResource.getResource("ditkort.DTK1980"), 2.5f, 10));
        }
        
        public void setGridCheckboxes() {
            if (Constant.debugMode) {
                System.out.println("setGridCheckboxes " + this.getServiceName());
            }
            if (this.getServiceName().equals(Constant.servicename_H1870) || this.getServiceName().equals(Constant.servicename_L1928) || this.getServiceName().equals(Constant.servicename_1953) || this.getServiceName().equals(Constant.servicename_1980)) {
                ditkort.this.c_formatCheckboxes.showCheckboxes(false);
            }
            else {
                ditkort.this.c_formatCheckboxes.showCheckboxes(true);
            }
        }
        
        public void setFormatText() {
            if (this.getServiceName().equals(Constant.servicename_H1870) || this.getServiceName().equals(Constant.servicename_L1928)) {
                ditkort.this.c_formatView.c_maalestok.setText("1:20.000");
            }
            else if (this.getServiceName().equals(Constant.servicename_DTK25) || this.getServiceName().equals(Constant.servicename_1953) || this.getServiceName().equals(Constant.servicename_1980)) {
                ditkort.this.c_formatView.c_maalestok.setText("1:25.000");
            }
            else if (this.getServiceName().equals(Constant.servicename_DTK100)) {
                ditkort.this.c_formatView.c_maalestok.setText("1:100.000");
            }
        }
        
        public void setService() {
            try {
                if (Constant.debugMode) {
                    System.out.println("in setService, servicename is found to be: " + ditkort.this.servicename);
                    System.out.println("getServiceName in setService returned: " + this.getServiceName());
                }
                this.setFormatText();
                if (!ditkort.this.c_oversigtskortEnable) {
                    if (this.getServiceName().equals(Constant.servicename_H1870) || this.getServiceName().equals(Constant.servicename_L1928) || this.getServiceName().equals(Constant.servicename_1953) || this.getServiceName().equals(Constant.servicename_1980)) {
                        ditkort.this.c_formatView.updateHistoricalMapPrice();
                        ditkort.this.c_formatView.updatePrice();
                    }
                    else {
                        ditkort.this.c_formatView.c_planoPris = ditkort.this.myResource.getResource("format.opklaebning.pris.ditkort.udenLaminering");
                        ditkort.this.c_formatView.c_falsetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.ditkort.udenLaminering");
                        ditkort.this.c_formatView.c_indrammetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.ditkort.indrammet");
                        ditkort.this.c_formatView.c_lamineretPlanoPris = ditkort.this.myResource.getResource("format.opklaebning.pris.ditkort.laminering");
                        ditkort.this.c_formatView.c_lamineretFalsetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.ditkort.laminering");
                        ditkort.this.c_formatView.updatePrice();
                    }
                }
                else if (this.getServiceName().equals(Constant.servicename_DTK25) || this.getServiceName().equals(Constant.servicename_DTK100)) {
                    ditkort.this.c_formatView.c_planoPris = ditkort.this.myResource.getResource("format.opklaebning.pris.oversigt.udenLaminering");
                    ditkort.this.c_formatView.c_falsetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.oversigt.udenLaminering");
                    ditkort.this.c_formatView.c_indrammetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.oversigt.indrammet");
                    ditkort.this.c_formatView.c_lamineretPlanoPris = ditkort.this.myResource.getResource("format.opklaebning.pris.oversigt.laminering");
                    ditkort.this.c_formatView.c_lamineretFalsetPris = ditkort.this.myResource.getResource("format.opklaebning.pris.oversigt.laminering");
                    ditkort.this.c_formatView.updatePrice();
                }
                else if (Constant.debugMode) {
                    System.out.println("getServiceName in setService returned: " + this.getServiceName());
                }
            }
            catch (Exception e) {
                if (Constant.debugMode) {
                    System.out.println("setService threw an exception: ");
                }
                e.printStackTrace(System.out);
            }
            this.setGridCheckboxes();
            ditkort.this.c_formatCheckboxes.setUTMText(this.getServiceName());
            ditkort.this.c_formatCheckboxes.setLongLatText();
            try {
                final ResourceBundle ServRes = ResourceBundle.getBundle("dk_service", new Locale(this.getServiceName().toLowerCase()));
                final String info = ditkort.this.myResource.getResource("service.mapSizeText." + this.getServiceName());
                ditkort.this.c_ditkortInfo.setForeground(Color.BLACK);
                ditkort.this.c_ditkortInfo.setText(info);
                ditkort.this.c_ditkortViewNavngivInfo.setText(info);
                if (this.getServiceName().equals(Constant.servicename_H1870) || this.getServiceName().equals(Constant.servicename_L1928) || this.getServiceName().equals(Constant.servicename_1953) || this.getServiceName().equals(Constant.servicename_1980)) {
                    ditkort.this.c_ditkortInfo.setForeground(Color.RED);
                    ditkort.this.c_ditkortViewNavngivInfo.setForeground(Color.RED);
                    ditkort.this.c_ditkortInfo.append(ditkort.this.myResource.getResource("service.mapSizeText.AdditionalInfo"));
                    ditkort.this.c_ditkortViewNavngivInfo.append(ditkort.this.myResource.getResource("service.mapSizeText.AdditionalInfo"));
                }
                ditkort.this.c_navigationskortView.setHotSpotSize(ServRes.getString("HotSpotSizeX"), ServRes.getString("HotSpotSizeY"));
                ditkort.access$30(ditkort.this, Integer.parseInt(ServRes.getString("AdjustCenterCoord_X")));
                ditkort.access$31(ditkort.this, Integer.parseInt(ServRes.getString("AdjustCenterCoord_Y")));
                if (Constant.debugMode) {
                    System.out.format("AdjustCenterCoord_X: %s, AdjustCenterCoord_Y: %s, zoomfactor: %s, servicename: %s\n", ServRes.getString("AdjustCenterCoord_X"), ServRes.getString("AdjustCenterCoord_Y"), ServRes.getString("zoomfactor"), ServRes.getString("servicename"));
                }
                if (Constant.debugMode) {
                    System.out.println("c_oversigtskortEnable is: " + ditkort.this.c_oversigtskortEnable);
                }
                if (ditkort.this.c_oversigtskortEnable) {
                    ditkort.this.c_oversigtskort.showClickedBox(false);
                    ditkort.access$32(ditkort.this, Integer.parseInt(ServRes.getString("AdjustOverviewField")));
                    ditkort.access$33(ditkort.this, Integer.parseInt(ServRes.getString("GRIDSIZE_FACTOR")));
                    ditkort.access$34(ditkort.this, ServRes.getString("servicename"));
                    if (Constant.debugMode) {
                        System.out.println("servicename er " + ditkort.this.servicename);
                    }
                    ditkort.this.c_oversigtskort.setGridsizeFactor(ditkort.this.GRIDSIZE_FACTOR);
                    ditkort.this.c_oversigtskort.setSpecialRegion(0);
                    ditkort.this.c_oversigtskort.setTextForOverviewMap(ServRes.getString("servicename"));
                    ditkort.this.c_oversigtskort.setOverviewMap(ServRes.getString("servicename"));
                }
            }
            catch (MissingResourceException e2) {
                if (Constant.debugMode) {
                    System.err.println("ResourceBundle exception: " + e2.getMessage());
                }
            }
            catch (NullPointerException e3) {
                if (Constant.debugMode) {
                    System.err.println("setService threw an NullPointerException: " + e3.getCause());
                }
            }
            catch (Exception e4) {
                if (Constant.debugMode) {
                    System.err.println("setService threw an exception (getMessage): " + e4.getMessage() + "\t getStackTrace: " + e4.getStackTrace().toString());
                }
            }
            ditkort.this.c_ditkortView.setServiceNamePrint(this.getServiceName());
            ditkort.this.c_ditkortView.setServiceNamePrintLayer(this.getLayers());
            ditkort.this.c_ditkortView.setMapURL(this.getServiceName(), this.getLayers());
            ditkort.this.c_ditkortView.c_pixelSize = this.getPixelSize();
            ditkort.this.c_ditkortView.c_pixelPrmm = this.getPixelPrmm();
            ditkort.this.c_ditkortViewNavngiv.setMapURL(this.getServiceName(), this.getLayers());
            ditkort.this.c_ditkortViewNavngiv.c_pixelSize = this.getPixelSize();
            ditkort.this.c_ditkortViewNavngiv.c_pixelPrmm = this.getPixelPrmm();
            if (Constant.debugMode) {
                System.out.println("getServiceName(): " + this.getServiceName() + ", and getLayers(): " + this.getLayers());
            }
            ditkort.this.c_zoomView.setMapURL(this.getServiceName(), this.getLayers());
            ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.getCenterX(), ditkort.this.c_ditkortView.getCenterY(), ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected());
            if (!ditkort.this.c_oversigtskortEnable) {
                ditkort.this.c_ditkortView.updateMap();
                ditkort.this.c_ditkortViewNavngiv.updateMap();
            }
            else if (ditkort.this.isInitiated) {
                ditkort.this.c_tabbedPane.setSelectedIndex(0);
            }
        }
        
        public String getLayers() {
            String layers = null;
            final service selectedService = (service)this.c_serviceName.getSelectedItem();
            if (selectedService != null) {
                layers = selectedService.getLayers();
            }
            return layers;
        }
        
        public String getServiceName() {
            String name = null;
            final service selectedService = (service)this.c_serviceName.getSelectedItem();
            if (selectedService != null) {
                name = selectedService.getName();
            }
            if (name == null) {
                return "ERROR";
            }
            return name;
        }
        
        public float getPixelSize() {
            float pixelSize = 0.0f;
            final service selectedService = (service)this.c_serviceName.getSelectedItem();
            if (selectedService != null) {
                pixelSize = selectedService.getPixelSize();
            }
            return pixelSize;
        }
        
        public int getPixelPrmm() {
            int pixelPrmm = 0;
            final service selectedService = (service)this.c_serviceName.getSelectedItem();
            if (selectedService != null) {
                pixelPrmm = selectedService.getPixelPrmm();
            }
            return pixelPrmm;
        }
    }
    
    class searchCityView extends JPanel implements ActionListener
    {
        CResourceManager myResource;
        static final long serialVersionUID = 0L;
        private Login c_l;
        private JTextField c_searchField;
        private JButton c_searchButton;
        private Boolean c_hasEntered;
        private JComboBox c_searchResult;
        private String[][] fResults;
        
        public searchCityView() {
            this.myResource = CResourceManager.instance();
            this.c_l = new Login();
            this.c_searchField = new JTextField(this.myResource.getResource("findsted.soegeftersted.tekst"));
            this.c_searchButton = new JButton(this.myResource.getResource("knap.soeg"));
            this.c_hasEntered = false;
            this.c_searchResult = new JComboBox();
            if (Constant.debugMode) {
                System.out.println("I searchCityView returnerer getLocale " + this.myResource.getLocale());
            }
            this.c_searchField.addMouseListener(new myMouseListener());
            this.c_searchField.addKeyListener(new myKeyListener());
            final JPanel searchBoxPanel = new JPanel();
            final JPanel searchPanel = new JPanel();
            this.c_searchButton.setActionCommand("search");
            this.c_searchButton.addActionListener(this);
            this.c_searchResult.setActionCommand("combobox select");
            this.c_searchResult.addActionListener(this);
            searchPanel.setLayout(new BoxLayout(searchPanel, 2));
            searchBoxPanel.setLayout(new BoxLayout(searchBoxPanel, 3));
            searchBoxPanel.setBorder(BorderFactory.createTitledBorder(this.myResource.getResource("findsted.soegeftersted")));
            searchBoxPanel.setPreferredSize(new Dimension(243, 77));
            searchPanel.add(this.c_searchField);
            searchPanel.add(this.c_searchButton);
            searchBoxPanel.add(searchPanel);
            searchBoxPanel.add(this.c_searchResult);
            this.add(searchBoxPanel);
            this.setAlignmentX(0.0f);
        }
        
        public void doSearch(final String searchQuery) {
            this.c_searchField.setText(searchQuery);
            this.c_searchButton.doClick();
        }
        
        public void actionPerformed(final ActionEvent event) {
            final SwingWorker worker = new SwingWorker() {
                public Object construct() {
                    if ("search".equals(event.getActionCommand())) {
                        searchCityView.this.c_searchResult.removeAllItems();
                        searchCityView.this.c_searchResult.addItem(searchCityView.this.myResource.getResource("findsted.arbejder"));
                        try {
                            if (!URLEncoder.encode(searchCityView.this.c_searchField.getText(), "ISO-8859-1").equals("")) {
                                try {
                                    searchCityView.this.doPlaceSearch(URLEncoder.encode(searchCityView.this.c_searchField.getText(), "ISO-8859-1"));
                                }
                                catch (UnsupportedEncodingException ex) {}
                                return true;
                            }
                            return true;
                        }
                        catch (UnsupportedEncodingException e) {
                            System.out.println("DitKort Exception: " + e.getMessage());
                            return true;
                        }
                    }
                    if ("combobox select".equals(event.getActionCommand()) && searchCityView.this.c_searchResult.getSelectedIndex() != -1) {
                        if (Constant.debugMode) {
                            System.out.println("in searchCityView event handler " + searchCityView.this.fResults[searchCityView.this.c_searchResult.getSelectedIndex()][7] + "," + searchCityView.this.fResults[searchCityView.this.c_searchResult.getSelectedIndex()][8]);
                        }
                        ditkort.this.c_ditkortView.c_centerX = Float.valueOf(searchCityView.this.fResults[searchCityView.this.c_searchResult.getSelectedIndex()][8]);
                        ditkort.this.c_ditkortView.c_centerY = Float.valueOf(searchCityView.this.fResults[searchCityView.this.c_searchResult.getSelectedIndex()][7]);
                        ditkort.this.c_ditkortView.updateMap();
                        ditkort.this.c_centerCoordsInput.setCenterX(ditkort.this.c_ditkortView.getCenterX());
                        ditkort.this.c_centerCoordsInput.setCenterY(ditkort.this.c_ditkortView.getCenterY());
                        ditkort.this.c_zoomView.updateMap(ditkort.this.c_ditkortView.c_centerX, ditkort.this.c_ditkortView.c_centerY, ditkort.this.c_formatCheckboxes.isUTMgridSelected(), ditkort.this.c_formatCheckboxes.isLongLatGridSelected());
                        ditkort.this.c_navigationskortView.updateHotspotPosition((int)ditkort.this.c_ditkortView.c_centerX, (int)ditkort.this.c_ditkortView.c_centerY);
                    }
                    return true;
                }
            };
            worker.start();
        }
        
        public void setVisibility(final boolean set) {
            this.c_searchField.setEnabled(set);
            this.c_searchButton.setEnabled(set);
            this.c_searchResult.setEnabled(set);
        }
        
        public void doPlaceSearch(final String placeQuery) {
            final String geoUrl = "http://kortforsyningen.kms.dk/service?ServiceName=geoS&soegemetode=1&format=ASCII&ticket=" + this.c_l.getTicket() + "&Stednavn=";
            URL url = null;
            InputStream in = null;
            String results = null;
            String numResults = null;
            Integer startPos = null;
            Integer endPos = null;
            String rawResult = null;
            String[] rawResultArr = null;
            try {
                url = new URL(String.valueOf(geoUrl) + placeQuery);
                in = url.openStream();
                final BufferedReader br = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
                rawResult = br.readLine();
                if (Constant.debugMode) {
                    System.out.println("KMS has returned the data to doPlaceSearch: " + rawResult);
                }
            }
            catch (Exception e) {
                System.err.println("Couldn't do geoS query!");
            }
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                }
                catch (IOException ex) {}
            }
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex2) {}
            startPos = rawResult.indexOf("#") + 1;
            results = rawResult.substring(startPos);
            if (results.indexOf("#") != -1) {
                endPos = results.indexOf("#");
                numResults = rawResult.substring(rawResult.indexOf("#") - 2, rawResult.indexOf("#"));
                numResults = numResults.trim();
                results = results.substring(0, endPos);
                rawResultArr = results.split(";");
                for (int i = 0; i < rawResultArr.length; ++i) {
                    if (rawResultArr[i].indexOf(",") != -1) {
                        rawResultArr[i] = rawResultArr[i].replace(",", ".");
                    }
                    if (Constant.debugMode) {
                        System.out.println(rawResultArr[i]);
                    }
                }
                this.c_searchResult.removeAllItems();
                this.fResults = new String[(int)Integer.valueOf(numResults)][10];
                int l = 0;
                int j = 0;
                final String[] array = rawResultArr;
                for (int k = 0; k < array.length; ++k) {
                    final String s = array[k];
                    if (j % 9 == 0 && j != 0) {
                        ++l;
                    }
                    if (j > 8) {
                        j = 0;
                    }
                    this.fResults[l][j] = s;
                    ++j;
                }
                final String[][] fResults = this.fResults;
                for (int n = 0; n < fResults.length; ++n) {
                    final String[] r = fResults[n];
                    this.c_searchResult.addItem(String.valueOf(r[1]) + " (" + r[3] + ")");
                }
            }
            else if (this.c_searchResult.getItemCount() <= 0) {
                this.c_searchResult.addItem(this.myResource.getResource("findsted.searchresult"));
            }
            else {
                this.c_searchResult.removeAllItems();
                this.c_searchResult.addItem(this.myResource.getResource("findsted.searchresult"));
            }
        }
        
        static /* synthetic */ void access$2(final searchCityView searchCityView, final Boolean c_hasEntered) {
            searchCityView.c_hasEntered = c_hasEntered;
        }
        
        class myKeyListener extends KeyAdapter
        {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    searchCityView.this.c_searchButton.doClick();
                }
            }
        }
        
        class myMouseListener extends MouseAdapter
        {
            public void mouseEntered(final MouseEvent e) {
                if (!searchCityView.this.c_hasEntered) {
                    searchCityView.access$2(searchCityView.this, true);
                    searchCityView.this.c_searchField.setText("");
                }
            }
        }
    }
}
