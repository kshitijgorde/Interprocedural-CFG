// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import javax.swing.UIManager;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.beans.PropertyDescriptor;
import java.net.URL;
import java.io.IOException;
import com.itt.J2KViewer.util.J2KViewerBeanInfo;
import java.util.Properties;
import com.itt.J2KViewer.util.Helper;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;
import java.net.URI;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.itt.J2KViewer.util.Log;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class BeanTestFrame extends JFrame implements KeyListener
{
    private static final long serialVersionUID = 1L;
    private J2KViewerBean viewerBean;
    private static Log log;
    private int commandCount;
    private String use;
    private MouseMotionListener[] mouseMotionListeners;
    private MouseListener[] mouseListeners;
    private String defaultPropFile;
    private String userPropFile;
    private String homeDir;
    private TextField typingArea;
    private TextArea displayArea;
    public static final String CLOSE = "Close Image";
    public static final String ZOOM_IN = "Zoom In";
    public static final String ZOOM_OUT = "Zoom Out";
    public static final String PANNING = "Panning";
    public static final String INC_QUALITY = "Increase Quality";
    public static final String DEC_QUALITY = "Decrease Quality";
    public static final String PROPS = "Code-Stream Properties";
    public static final String AUTO_DRA = "Auto DRA";
    public static final String XML_PROPS = "XML Properties";
    public static final String HELP = "Help";
    static /* synthetic */ Class class$com$itt$J2KViewer$BeanTestFrame;
    
    public BeanTestFrame(final String defaultPropFile, final String imageURL) {
        this.viewerBean = null;
        this.commandCount = 0;
        this.use = null;
        this.mouseMotionListeners = null;
        this.mouseListeners = null;
        this.defaultPropFile = "J2KViewer.properties";
        this.userPropFile = "EnterpriseViewer.properties";
        if (defaultPropFile != null) {
            this.defaultPropFile = defaultPropFile;
        }
        this.viewerBean = new J2KViewerBean(this);
        this.initComponents();
        this.setBeanProps(this.defaultPropFile);
        this.homeDir = System.getProperty("user.home");
        this.viewerBean.setPropFile(this.homeDir + "/" + "EnterpriseViewer" + "/", this.userPropFile);
        if (this.viewerBean.propFileWasSet()) {
            this.setBeanProps(this.userPropFile);
        }
        try {
            this.viewerBean.setImageURL("jpip://iasdemo.ittvis.com/JP2Server/Nashville_QB_EPJE");
            this.viewerBean.setShowToolBar(true);
            this.viewerBean.setShowSecurityBanner(false);
            this.viewerBean.setShowSplitPane(true);
            this.viewerBean.setShowGeoLocationPanel(false);
            System.out.println("isShowSplitPane: " + this.viewerBean.isShowSplitPane());
            System.out.println("isShowSecurityBanner : " + this.viewerBean.isShowSecurityBanner());
        }
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        this.getContentPane().add(this.viewerBean.getMainPanel());
        this.setLocation(50, 50);
        (this.typingArea = new TextField(20)).addKeyListener(this);
        this.getContentPane().add("South", this.typingArea);
        (this.displayArea = new TextArea(17, 20)).setEditable(false);
        this.getContentPane().add("North", this.displayArea);
        this.setSize(900, 1070);
        BeanTestFrame.log.debug("Showing frame");
        if (imageURL != null) {
            try {
                System.out.println("***************here");
                final URI uri = new URI(imageURL);
                this.viewerBean.setImageURL(imageURL);
            }
            catch (URISyntaxException ex12) {
                BeanTestFrame.log.error("The image URL is invalid");
            }
            catch (PropertyVetoException ex2) {
                BeanTestFrame.log.error("Error setting bean properties", ex2);
            }
        }
        this.viewerBean.openImage();
        this.use = "CLOSE      = F3\nZOOM_IN    = F4\nZOOM_OUT   = F5\nPANNING = 2:downarrow\n          4:leftarrow\n          6:rightarrow\n          8:uparrow\nINC_QUALITY = F6\nDEC_QUALITY = F7\nPROPS       = F8\nAUTO_DRA    = F9\nXML_PROPS   = F10\nHELP        = F12\nRED_BAND    = r\nGREEN_BAND  = g\nBLUE_BAND   = b\nSAVE_JPEG    = j\nSAVE_GEOTIFF = k\nSHOW_BANDS  = l\nMODE_ROTATE = m\nMODE_PAN = n\nAPPLY_DRA (stretch) = o\n";
        this.displayArea.append(this.use);
        this.typingArea.setText("Enter Commands Here");
        this.mouseListeners = this.viewerBean.getImageMouseListeners();
        System.out.println("getImageMouseListeners() " + this.mouseListeners);
        System.out.println("getBytesTransferred() = " + this.viewerBean.getBytesTransferred());
        System.out.println("getCurrentDimensions() = " + this.viewerBean.getCurrentDimensions());
        System.out.println("getDiscardedZoomLevels() = " + this.viewerBean.getDiscardedZoomLevels());
        System.out.println("getImageURL() = " + this.viewerBean.getImageURL());
        final String loginId = this.viewerBean.getLoginId();
        System.out.println("getLoginId() = " + loginId);
        try {
            this.viewerBean.setLoginId(loginId);
            System.out.println("setLoginId(" + loginId + ")");
        }
        catch (PropertyVetoException ex3) {
            BeanTestFrame.log.error("FAILED to setLoginId(" + loginId + ")", ex3);
        }
        final String loginPwd = this.viewerBean.getLoginPwd();
        System.out.println("getLoginPwd() = " + loginPwd);
        try {
            this.viewerBean.setLoginPwd(loginPwd);
            System.out.println("setLoginPwd(" + loginPwd + ")");
        }
        catch (PropertyVetoException ex4) {
            BeanTestFrame.log.error("FAILED to setLoginPwd(" + loginPwd + ")", ex4);
        }
        System.out.println("getMainPanel() = " + this.viewerBean.getMainPanel().getName());
        System.out.println("getMaxDiscardedZoomLevels() = " + this.viewerBean.getMaxDiscardedZoomLevels());
        System.out.println("getMaxQualityLayers() = " + this.viewerBean.getMaxQualityLayers());
        final int maxZoomOutLevel = this.viewerBean.getMaxZoomOutLevel();
        System.out.println("getMaxZoomOutLevel() = " + maxZoomOutLevel);
        try {
            this.viewerBean.setMaxZoomOutLevel(maxZoomOutLevel);
            System.out.println("setMaxZoomOutLevel(" + maxZoomOutLevel + ")");
        }
        catch (PropertyVetoException ex5) {
            BeanTestFrame.log.error("FAILED to setMaxZoomOutLevel(" + maxZoomOutLevel + ")", ex5);
        }
        System.out.println("getQualityLayers() = " + this.viewerBean.getQualityLayers());
        System.out.println("getTotalBytes() = " + this.viewerBean.getTotalBytes());
        System.out.println("getTotalDimensions() = " + this.viewerBean.getTotalDimensions());
        final boolean allowChangeQuality = this.viewerBean.isAllowChangeQuality();
        System.out.println("isAllowChangeQuality() = " + allowChangeQuality);
        try {
            this.viewerBean.setAllowChangeQuality(allowChangeQuality);
            System.out.println("setAllowChangeQuality(" + allowChangeQuality + ")");
        }
        catch (PropertyVetoException ex6) {
            BeanTestFrame.log.error("FAILED to setAllowChangeQuality(" + allowChangeQuality + ")", ex6);
        }
        final boolean allowPan = this.viewerBean.isAllowPan();
        System.out.println("isAllowPan() = " + allowPan);
        try {
            this.viewerBean.setAllowPan(allowPan);
            System.out.println("setAllowPan(" + allowPan + ")");
        }
        catch (PropertyVetoException ex7) {
            BeanTestFrame.log.error("FAILED to setAllowPan(" + allowPan + ")", ex7);
        }
        final boolean allowZoom = this.viewerBean.isAllowZoom();
        System.out.println("isAllowZoom() = " + allowZoom);
        try {
            this.viewerBean.setAllowZoom(allowZoom);
            System.out.println("setAllowZoom(" + allowZoom + ")");
        }
        catch (PropertyVetoException ex8) {
            BeanTestFrame.log.error("FAILED to setAllowZoom(" + allowZoom + ")", ex8);
        }
        System.out.println("isOpen() = " + this.viewerBean.isOpen());
        final boolean showContextMenu = this.viewerBean.isShowContextMenu();
        System.out.println("isShowContextMenu() = " + showContextMenu);
        try {
            this.viewerBean.setShowContextMenu(showContextMenu);
            System.out.println("setShowContextMenu(" + showContextMenu + ")");
        }
        catch (Exception ex9) {
            BeanTestFrame.log.error("FAILED to setShowContextMenu(" + showContextMenu + ")", ex9);
        }
        final boolean showScrollbars = this.viewerBean.isShowScrollbars();
        System.out.println("isShowScrollbars() = " + showScrollbars);
        try {
            this.viewerBean.setShowScrollbars(showScrollbars);
            System.out.println("setShowScrollbars(" + showScrollbars + ")");
        }
        catch (PropertyVetoException ex10) {
            BeanTestFrame.log.error("FAILED to setShowScrollbars(" + showScrollbars + ")", ex10);
        }
        this.mouseMotionListeners = this.viewerBean.getImageMouseMotionListeners();
        System.out.println("getImageMouseMotionListeners() " + this.mouseMotionListeners);
        final boolean showToolBar = this.viewerBean.isShowToolBar();
        System.out.println("isShowToolbar() = " + showToolBar);
        try {
            this.viewerBean.setShowToolBar(showToolBar);
            System.out.println("setShowToolBar(" + showToolBar + ")");
        }
        catch (PropertyVetoException ex11) {
            BeanTestFrame.log.error("FAILED to setShowToolBar(" + showToolBar + ")", ex11);
        }
        System.out.println("Number of Components: " + this.viewerBean.getNumberOfComponents());
        System.out.println("RGB Map");
        for (int i = 0; i < this.viewerBean.getRGBMap().length; ++i) {
            System.out.println(this.viewerBean.getRGBMap()[i]);
        }
        final double radians = Math.toRadians(5.0);
        System.out.println("Set Rotation Angle to 5.0 degrees");
        this.viewerBean.setRotationAngle(radians);
        System.out.println("Rotation Angle: " + this.viewerBean.getRotationAngle());
        System.out.println("Setting default DRA type to 1 (Preferred)");
        try {
            this.viewerBean.setDefaultDRAType(1);
        }
        catch (Exception ex13) {
            System.out.println("Setting default DRA failed!");
        }
        System.out.println("Default DRA Type is : " + this.viewerBean.getDefaultDRAType());
        System.out.println("Setting % stretch value to 2.5");
        this.viewerBean.setDefaultPercentStretch(2.5);
        System.out.println("The default percent stretch is now " + this.viewerBean.getDefaultPercentStretch());
        System.out.println("Ignore value for DRA : " + this.viewerBean.getIgnoreValueDRA());
        System.out.println("isEnableIgnoreValueDRA : " + this.viewerBean.isEnableIgnoreValDRA());
        System.out.println("isUponLoadDoAutoDRA: " + this.viewerBean.isUponLoadDoAutoDRA());
        System.out.println("Setting Geographic Display format to DMS ");
        this.viewerBean.setGeographicDisplayFormat(2);
        System.out.println("Geographic Display format is : " + this.viewerBean.getGeographicDisplayFormat());
        final int[] applicationSize = this.viewerBean.getApplicationSize();
        System.out.println("Application Size: " + applicationSize[0] + ", " + applicationSize[1]);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.displayInfo(keyEvent);
        this.typingArea.setText("");
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private String getRequestForKey(final String s, final int n) {
        int n2 = -1;
        if (n >= 0) {
            n2 = n;
        }
        if (s != null && s.length() > 0 && n2 < 0) {
            if (s.equalsIgnoreCase("2")) {
                return "PAN_DOWN";
            }
            if (s.equalsIgnoreCase("8")) {
                return "PAN_UP";
            }
            if (s.equalsIgnoreCase("4")) {
                return "PAN_LEFT";
            }
            if (s.equalsIgnoreCase("6")) {
                return "PAN_RIGHT";
            }
            if (s.equalsIgnoreCase("x")) {
                return "DISPLAY_COMMANDS";
            }
            if (s.equalsIgnoreCase("r")) {
                return "RED_BAND";
            }
            if (s.equalsIgnoreCase("g")) {
                return "GREEN_BAND";
            }
            if (s.equalsIgnoreCase("b")) {
                return "BLUE_BAND";
            }
            if (s.equalsIgnoreCase("j")) {
                return "SAVE_JPEG";
            }
            if (s.equalsIgnoreCase("k")) {
                return "SAVE_GEOTIFF";
            }
            if (s.equalsIgnoreCase("l")) {
                return "SHOW_BANDS";
            }
            if (s.equalsIgnoreCase("m")) {
                return "MODE_ROTATE";
            }
            if (s.equalsIgnoreCase("n")) {
                return "MODE_PAN";
            }
            if (s.equalsIgnoreCase("o")) {
                return "APPLY_DRA";
            }
            if (!s.equalsIgnoreCase("p")) {
                return "NOT A VALID COMMAND.  Press 'c' to see a list of commands.";
            }
        }
        else if (n2 >= 0) {
            if (n2 == 40) {
                return "PAN_DOWN";
            }
            if (n2 == 38) {
                return "PAN_UP";
            }
            if (n2 == 37) {
                return "PAN_LEFT";
            }
            if (n2 == 39) {
                return "PAN_RIGHT";
            }
            if (n2 == 114) {
                return "CLOSE";
            }
            if (n2 == 115) {
                return "ZOOM_IN";
            }
            if (n2 == 116) {
                return "ZOOM_OUT";
            }
            if (n2 == 117) {
                return "INC_QUALITY";
            }
            if (n2 == 118) {
                return "DEC_QUALITY";
            }
            if (n2 == 119) {
                return "PROPS";
            }
            if (n2 == 120) {
                return "AUTO_DRA";
            }
            if (n2 == 121) {
                return "XML_PROPS";
            }
            if (n2 == 123) {
                return "HELP";
            }
            return "NOT A VALID COMMAND.  Press 'c' to see a list of commands.";
        }
        return s;
    }
    
    private void displayInfo(final KeyEvent keyEvent) {
        String s = "NOT A VALID COMMAND";
        final char keyChar = keyEvent.getKeyChar();
        int keyCode = keyEvent.getKeyCode();
        if (!Character.isISOControl(keyChar)) {
            if (keyChar == '\uffff') {
                s = this.getRequestForKey(String.valueOf(keyChar), keyCode);
            }
            else {
                keyCode = -1;
                s = this.getRequestForKey(String.valueOf(keyChar), keyCode);
            }
        }
        final String string = keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        ++this.commandCount;
        if (keyCode == -1) {
            this.displayArea.append(this.commandCount + ". " + keyChar + " = " + s + "\n");
        }
        else {
            this.displayArea.append(this.commandCount + ". " + string + " = " + s + "\n");
        }
        if (s != null && s.length() > 0) {
            this.handleRequest(s);
        }
    }
    
    private void handleRequest(final String s) {
        try {
            if (s.equalsIgnoreCase("DISPLAY_COMMANDS")) {
                this.displayArea.append(this.use);
            }
            if (s.equalsIgnoreCase("PAN_DOWN")) {
                this.viewerBean.pan(0.0f, 5.0f);
            }
            if (s.equalsIgnoreCase("PAN_UP")) {
                this.viewerBean.pan(0.0f, -5.0f);
            }
            if (s.equalsIgnoreCase("PAN_LEFT")) {
                this.viewerBean.pan(-5.0f, 0.0f);
            }
            if (s.equalsIgnoreCase("PAN_RIGHT")) {
                this.viewerBean.pan(5.0f, 0.0f);
            }
            if (s.equalsIgnoreCase("CLOSE")) {
                this.viewerBean.closeImage();
            }
            if (s.equalsIgnoreCase("ZOOM_IN")) {
                this.viewerBean.zoom(-1);
            }
            if (s.equalsIgnoreCase("ZOOM_OUT")) {
                this.viewerBean.zoom(1);
            }
            if (s.equalsIgnoreCase("INC_QUALITY")) {
                this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() + 1);
            }
            if (s.equalsIgnoreCase("DEC_QUALITY")) {
                this.viewerBean.setQualityLayers(this.viewerBean.getQualityLayers() - 1);
            }
            if (s.equalsIgnoreCase("PROPS")) {
                try {
                    this.viewerBean.showCodestreamPropsDialog();
                }
                catch (Exception ex) {
                    BeanTestFrame.log.error("FAILED to open codestream property dialog.", ex);
                }
            }
            if (s.equalsIgnoreCase("AUTO_DRA")) {
                this.viewerBean.applyAutoDRA();
            }
            if (s.equalsIgnoreCase("XML_PROPS")) {
                try {
                    this.viewerBean.showXmlPropsDialog();
                }
                catch (Exception ex2) {
                    BeanTestFrame.log.error("FAILED to open XML Properties Dialog.", ex2);
                }
            }
            if (s.equalsIgnoreCase("HELP")) {
                try {
                    this.viewerBean.showHelpDialog();
                }
                catch (Exception ex3) {
                    BeanTestFrame.log.error("FAILED to open help dialog.", ex3);
                }
            }
            if (s.equalsIgnoreCase("RED_BAND")) {
                this.incrementRGBMap(0);
            }
            if (s.equalsIgnoreCase("GREEN_BAND")) {
                this.incrementRGBMap(1);
            }
            if (s.equalsIgnoreCase("BLUE_BAND")) {
                this.incrementRGBMap(2);
            }
            if (s.equalsIgnoreCase("SAVE_JPEG")) {
                try {
                    this.viewerBean.saveViewToFile();
                }
                catch (Exception ex4) {
                    BeanTestFrame.log.error("Failed to save JPEG!", ex4);
                }
            }
            if (s.equalsIgnoreCase("SAVE_GEOTIFF")) {
                try {
                    if (this.viewerBean.isGeoTIFFAvailable()) {
                        this.viewerBean.saveViewToGeoTIFFFile();
                    }
                    else {
                        BeanTestFrame.log.info("Geolocation info not available!");
                    }
                }
                catch (Exception ex5) {
                    BeanTestFrame.log.error("Failed to save GEOTIFF!", ex5);
                }
            }
            if (s.equalsIgnoreCase("SHOW_BANDS")) {
                try {
                    this.viewerBean.showBandsDialog();
                }
                catch (Exception ex7) {
                    BeanTestFrame.log.error("Failed to show Bands dialog");
                }
            }
            if (s.equalsIgnoreCase("MODE_ROTATE")) {
                this.viewerBean.setTransformationMode("Manual Rotation");
            }
            if (s.equalsIgnoreCase("MODE_PAN")) {
                this.viewerBean.setTransformationMode("Pan");
            }
            if (s.equalsIgnoreCase("APPLY_DRA")) {
                this.viewerBean.applyDRA(2);
            }
        }
        catch (PropertyVetoException ex6) {
            ex6.printStackTrace();
        }
    }
    
    private void setBeanProps(final String s) {
        try {
            BeanTestFrame.log.info("Attempting to load properties from '" + s + "'");
            final URL urlAsResource = Helper.getURLAsResource(s);
            final Properties properties = new Properties();
            if (urlAsResource != null) {
                properties.load(urlAsResource.openStream());
                if (properties.size() > 0) {
                    final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
                    for (int i = 0; i < propertyDescriptors.length; ++i) {
                        final PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                        this.viewerBean.initProperty(propertyDescriptor, properties.getProperty(propertyDescriptor.getName()));
                    }
                }
            }
        }
        catch (IOException ex) {
            BeanTestFrame.log.error("Error loading properties file", ex);
        }
    }
    
    private void incrementRGBMap(final int n) throws PropertyVetoException {
        final int numberOfComponents = this.viewerBean.getNumberOfComponents();
        final int[] rgbMap = this.viewerBean.getRGBMap();
        if (n >= 0 && n <= rgbMap.length && numberOfComponents > 1) {
            switch (n) {
                case 0: {
                    rgbMap[0] = 0;
                    rgbMap[1] = 1;
                    rgbMap[2] = 2;
                    break;
                }
                case 1: {
                    rgbMap[0] = 2;
                    rgbMap[1] = 0;
                    rgbMap[2] = 1;
                    break;
                }
                case 2: {
                    rgbMap[0] = 1;
                    rgbMap[rgbMap[1] = 2] = 0;
                    break;
                }
            }
            System.out.print("RGB map = ");
            for (int i = 0; i < rgbMap.length; ++i) {
                System.out.print(rgbMap[i]);
                System.out.print(" ");
            }
            System.out.println();
            this.viewerBean.setRGBMap(rgbMap);
        }
    }
    
    private void initComponents() {
        this.setDefaultCloseOperation(3);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                BeanTestFrame.this.exitForm(windowEvent);
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                BeanTestFrame.this.formComponentResized(componentEvent);
            }
        });
        this.pack();
    }
    
    private void formComponentResized(final ComponentEvent componentEvent) {
        final Component component = componentEvent.getComponent();
        if (component.getWidth() < 475) {
            component.setSize(475, component.getHeight());
        }
    }
    
    private void exitForm(final WindowEvent windowEvent) {
        System.runFinalization();
        System.gc();
        System.exit(0);
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.viewerBean.closeImage();
        }
        super.processWindowEvent(windowEvent);
    }
    
    public static void main(final String[] array) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            BeanTestFrame.log.info(ex.getMessage());
        }
        String s = null;
        String s2 = null;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals("-propfile")) {
                s = array[++i];
                BeanTestFrame.log.debug(s);
            }
            else if (array[i].equals("-help") || array[i].equals("-h")) {
                System.out.println("\nUsage for J2KViewerFrame. No arguments are required.\n  For help info:\n    -help  or -h\n  To specify an alternate properties file. File should be in classpath:\n    -propfile <file name>");
                System.exit(0);
            }
            else if (array[i].equals("-proplist")) {
                final PropertyDescriptor[] propertyDescriptors = new J2KViewerBeanInfo().getPropertyDescriptors();
                System.out.println("\nThese are the available properties for use in IAS Viewer properties file:");
                for (int j = 0; j < propertyDescriptors.length; ++j) {
                    if (propertyDescriptors[j].getWriteMethod() != null) {
                        System.out.println(propertyDescriptors[j].getName() + " - Type = " + propertyDescriptors[j].getPropertyType());
                    }
                }
                System.exit(0);
            }
            else if (s2 == null) {
                s2 = array[i];
            }
        }
        new BeanTestFrame(s, s2).setVisible(true);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        BeanTestFrame.log = new Log((BeanTestFrame.class$com$itt$J2KViewer$BeanTestFrame == null) ? (BeanTestFrame.class$com$itt$J2KViewer$BeanTestFrame = class$("com.itt.J2KViewer.BeanTestFrame")) : BeanTestFrame.class$com$itt$J2KViewer$BeanTestFrame);
    }
}
