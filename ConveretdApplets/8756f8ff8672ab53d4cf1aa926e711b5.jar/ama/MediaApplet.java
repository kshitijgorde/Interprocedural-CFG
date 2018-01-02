// 
// Decompiled by Procyon v0.5.30
// 

package ama;

import java.net.PasswordAuthentication;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.accessibility.Accessible;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.util.Enumeration;
import java.lang.reflect.InvocationTargetException;
import java.net.Authenticator;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.net.URL;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JApplet;

public class MediaApplet extends JApplet
{
    private static final String version = "1.0.0";
    private static final int REQUIRED_VM_MAJOR = 1;
    private static final int REQUIRED_VM_MINOR = 4;
    private static final int DEFAULT_IMAGE_PANEL_WIDTH = 320;
    private static final int DEFAULT_IMAGE_PANEL_HEIGHT = 240;
    private static final int NUMPANELS = 4;
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private final String info;
    private final String DEFAULT_PARAM_CGI_PATH = "operator/param.cgi";
    private boolean tooOldVM;
    private static JFrame frame;
    private Hashtable parameters;
    private JDesktopPane desktopPane;
    private ImagePanel imagePanel;
    private JPanel clickPanel;
    private JPopupMenu popupMenu;
    private URL videoURL;
    private String wwwRoot;
    private JLabel fpsLabel;
    private JButton playButton;
    private JButton stopButton;
    private JMenuItem playMenuButton;
    private JMenuItem stopMenuButton;
    private String cgiPath;
    private JComponent[] panels;
    private int[] componentCounts;
    private Vector plugins;
    private Hashtable CGIParameters;
    
    public MediaApplet() {
        this.info = this.getClass().toString() + " version " + "1.0.0";
        this.tooOldVM = false;
        this.parameters = null;
        this.imagePanel = null;
        this.clickPanel = null;
        this.popupMenu = null;
        this.videoURL = null;
        this.panels = new JComponent[4];
        this.componentCounts = new int[4];
        this.plugins = new Vector();
        this.CGIParameters = new Hashtable();
        if (MediaApplet.frame != null) {
            Authenticator.setDefault(new RootPassAuthenticator());
        }
    }
    
    private Plugin loadPlugin(final String s) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Loading plug-in: " + s);
        return (Plugin)Class.forName(s).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
    }
    
    public JPopupMenu getPopupMenu() {
        return this.popupMenu;
    }
    
    public String getCGIParameter(final String s) {
        return this.CGIParameters.get(s);
    }
    
    public String buildURL(final String s, final String s2, final Hashtable hashtable) {
        String s3 = this.getParameter(s);
        if (s3 == null) {
            s3 = "/" + this.cgiPath + "/" + s2;
        }
        if (hashtable == null) {
            return this.wwwRoot + "/" + s3;
        }
        String s4 = "";
        final Enumeration<String> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s5 = keys.nextElement();
            final String cgiParameter = this.getCGIParameter(s5);
            if (cgiParameter != null) {
                s4 = s4 + hashtable.get(s5) + "=" + cgiParameter;
            }
            if (keys.hasMoreElements()) {
                s4 += "&";
            }
        }
        String s6;
        if (s3.indexOf(63) == -1) {
            s6 = s3 + "?" + s4;
        }
        else {
            s6 = s3 + "&" + s4;
        }
        return this.wwwRoot + "/" + s6;
    }
    
    public void addComponent(final JComponent component, final int n) {
        if (this.panels[n] == null) {
            this.panels[n] = component;
        }
        else {
            if (this.componentCounts[n] == 1) {
                final JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.add(this.panels[n]);
                this.panels[n] = tabbedPane;
            }
            this.panels[n].add(component);
        }
        final int[] componentCounts = this.componentCounts;
        ++componentCounts[n];
    }
    
    public void addOverlay(final JComponent component) {
        this.desktopPane.add(component, JDesktopPane.DEFAULT_LAYER);
        this.desktopPane.moveToFront(component);
    }
    
    public JComponent getClickPanel() {
        return this.clickPanel;
    }
    
    public JDesktopPane getDesktopPane() {
        return this.desktopPane;
    }
    
    public void setParameter(final String s, final String s2) {
        if (this.parameters == null) {
            this.parameters = new Hashtable();
        }
        this.parameters.put(s, s2);
    }
    
    public String getParameter(final String s) {
        if (this.parameters != null) {
            return this.parameters.get(s);
        }
        return super.getParameter(s);
    }
    
    public String getAppletInfo() {
        return this.info;
    }
    
    public void init() {
        System.out.println("INIT " + this.info);
        try {
            final String replaceFirst = System.getProperty("java.vm.version").replaceFirst("^[^0-9]+", "");
            final StringTokenizer stringTokenizer = new StringTokenizer(replaceFirst, ".");
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println("VM version: " + int1 + "." + int2);
            this.tooOldVM = (int1 < 1 || int2 < 4);
            if (this.tooOldVM) {
                final JPanel panel = new JPanel();
                System.err.println("Required VM version: 1.4");
                panel.add(new JLabel("Your Java VM is too old!"));
                panel.add(new JLabel("Installed Java version: " + replaceFirst));
                panel.add(new JLabel("Required Java version: >=1.4"));
                this.getContentPane().add(panel);
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        int int3 = 320;
        int int4 = 240;
        final String parameter = this.getParameter("ama_url");
        try {
            if (parameter == null || parameter.startsWith("/")) {
                final URL documentBase = this.getDocumentBase();
                this.wwwRoot = documentBase.getProtocol() + "://" + documentBase.getHost() + ((documentBase.getPort() == -1) ? "" : (":" + documentBase.getPort()));
                if (parameter != null) {
                    this.videoURL = new URL(this.wwwRoot + parameter);
                }
                else {
                    this.videoURL = new URL(this.wwwRoot);
                }
            }
            else {
                this.videoURL = new URL(parameter);
                this.wwwRoot = this.videoURL.getProtocol() + "://" + this.videoURL.getHost() + ((this.videoURL.getPort() == -1) ? "" : (":" + this.videoURL.getPort()));
            }
            this.cgiPath = this.getParameter("ama_cgi-path");
            if (this.cgiPath == null) {
                this.cgiPath = "axis-cgi";
            }
            if (this.videoURL.getPath().length() < 2) {
                this.videoURL = new URL(this.wwwRoot + "/" + this.cgiPath + "/mjpg/video.cgi?showlength=1");
            }
            this.setCGIParameters(this.videoURL);
            final String s = this.CGIParameters.get("resolution");
            if (s != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "x");
                try {
                    int3 = Integer.parseInt(stringTokenizer2.nextToken());
                    int4 = Integer.parseInt(stringTokenizer2.nextToken());
                }
                catch (RuntimeException ex5) {
                    System.err.println("Unknown resolution: \"" + s + "\"! Using default.");
                    int3 = 320;
                    int4 = 240;
                }
            }
            if (this.getCGIParameter("camera") == null) {
                this.CGIParameters.put("camera", "1");
            }
            String s2 = this.getParameter("ama_param-path");
            if (s2 == null) {
                s2 = "/" + this.cgiPath + "/" + "operator/param.cgi";
            }
            ParamTool.setDefaultURL(this.wwwRoot + "/" + s2);
        }
        catch (MalformedURLException ex2) {
            ex2.printStackTrace(System.err);
            return;
        }
        final ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MediaApplet.this.start();
            }
        };
        (this.playButton = new JButton("Play")).addActionListener(actionListener);
        (this.playMenuButton = new JMenuItem("Play")).addActionListener(actionListener);
        final ActionListener actionListener2 = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MediaApplet.this.stop();
            }
        };
        (this.stopButton = new JButton("Stop")).addActionListener(actionListener2);
        (this.stopMenuButton = new JMenuItem("Stop")).addActionListener(actionListener2);
        final ImagePanelObserver imagePanelObserver = new ImagePanelObserver() {
            public void updateFPS(final int n) {
                MediaApplet.this.fpsLabel.setText("" + n + " fps");
            }
            
            public void stop() {
                MediaApplet.this.stop();
            }
        };
        this.desktopPane = new JDesktopPane() {
            public Dimension getMinimumSize() {
                return MediaApplet.this.imagePanel.getSize();
            }
            
            public Dimension getMaximumSize() {
                return MediaApplet.this.imagePanel.getSize();
            }
            
            public Dimension getPreferredSize() {
                return MediaApplet.this.imagePanel.getSize();
            }
        };
        final String parameter2 = this.getParameter("ama_zoom");
        float float1;
        boolean b;
        if (parameter2 == null) {
            float1 = 1.0f;
            b = false;
        }
        else if (parameter2.equals("auto")) {
            float1 = 0.0f;
            b = false;
        }
        else if (parameter2.equals("free")) {
            float1 = 0.0f;
            b = true;
        }
        else {
            try {
                float1 = Float.parseFloat(parameter2);
            }
            catch (NumberFormatException ex3) {
                if (MediaApplet.frame == null) {
                    ex3.printStackTrace();
                }
                else {
                    System.err.println("Invalid zoom: " + parameter2);
                    printUsageAndExit(1);
                }
                float1 = 1.0f;
            }
            b = false;
        }
        final boolean b2 = float1 == 0.0f;
        System.out.println("Z: " + float1);
        this.imagePanel = new ImagePanel(this.desktopPane, int3, int4, this.videoURL, float1, imagePanelObserver);
        this.desktopPane.add(this.imagePanel, JDesktopPane.DEFAULT_LAYER);
        this.fpsLabel = new JLabel("00 fps", 4);
        final Dimension preferredSize = this.fpsLabel.getPreferredSize();
        this.fpsLabel.setMinimumSize(preferredSize);
        this.fpsLabel.setPreferredSize(preferredSize);
        this.fpsLabel.setMaximumSize(preferredSize);
        this.fpsLabel.setText("0 fps");
        final JLabel label = new JLabel() {
            public Dimension getMinimumSize() {
                return MediaApplet.this.fpsLabel.getMinimumSize();
            }
            
            public Dimension getPreferredSize() {
                return MediaApplet.this.fpsLabel.getPreferredSize();
            }
            
            public Dimension getMaximumSize() {
                return MediaApplet.this.fpsLabel.getMaximumSize();
            }
        };
        final String parameter3 = this.getParameter("ama_hide-video-controls");
        if (parameter3 == null || !parameter3.equals("yes")) {
            final JPanel panel2 = new JPanel();
            panel2.setLayout(new BoxLayout(panel2, 0));
            panel2.add(label);
            panel2.add(Box.createHorizontalGlue());
            panel2.add(this.stopButton);
            panel2.add(this.playButton);
            panel2.add(Box.createHorizontalGlue());
            panel2.add(this.fpsLabel);
            panel2.setName("Video");
            this.addComponent(panel2, 1);
        }
        (this.popupMenu = new JPopupMenu()).add(this.stopMenuButton);
        this.popupMenu.add(this.playMenuButton);
        (this.clickPanel = new JPanel()).setSize(int3, int4);
        this.clickPanel.setOpaque(false);
        this.clickPanel.addMouseListener(new MouseAdapter() {
            public void maybeShowPopup(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    MediaApplet.this.popupMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                this.maybeShowPopup(mouseEvent);
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                this.maybeShowPopup(mouseEvent);
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
                this.maybeShowPopup(mouseEvent);
                if (mouseEvent.getButton() == 1 && MediaApplet.this.getCGIParameter("camera").equals("quad") && MediaApplet.frame == null) {
                    final int x = mouseEvent.getX();
                    final int y = mouseEvent.getY();
                    final int width = MediaApplet.this.imagePanel.getWidth();
                    final int height = MediaApplet.this.imagePanel.getHeight();
                    int n;
                    if (x < width / 2) {
                        if (y < height / 2) {
                            n = 1;
                        }
                        else {
                            n = 3;
                        }
                    }
                    else if (y < height / 2) {
                        n = 2;
                    }
                    else {
                        n = 4;
                    }
                    try {
                        final String parameter = MediaApplet.this.getParameter("ama_camera" + n + "-path");
                        if (parameter != null) {
                            MediaApplet.this.getAppletContext().showDocument(new URL(MediaApplet.this.wwwRoot + "/" + parameter), "_self");
                        }
                        else {
                            MediaApplet.this.getAppletContext().showDocument(new URL(MediaApplet.this.wwwRoot + "/view/view.shtml?imagepath=/mjpg/" + n + "/video.mjpg"), "_self");
                        }
                    }
                    catch (MalformedURLException ex) {
                        ex.printStackTrace(System.err);
                    }
                }
            }
        });
        final String parameter4 = this.getParameter("ama_plugins");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter4, ",");
            while (stringTokenizer3.hasMoreTokens()) {
                try {
                    final Plugin loadPlugin = this.loadPlugin(stringTokenizer3.nextToken());
                    loadPlugin.init(this);
                    this.plugins.add(loadPlugin);
                }
                catch (Exception ex4) {
                    ex4.printStackTrace(System.err);
                }
            }
        }
        for (int i = 0; i < 4; ++i) {
            if (this.componentCounts[i] == 1) {
                this.panels[i].setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), this.panels[i].getName()));
            }
        }
        this.addOverlay(this.clickPanel);
        Accessible desktopPane;
        if (b) {
            desktopPane = this.desktopPane;
        }
        else {
            desktopPane = new JPanel();
            ((Container)desktopPane).setLayout(new GridBagLayout());
            ((Container)desktopPane).add(this.desktopPane);
            if (b2) {
                ((Component)desktopPane).setBackground(Color.BLACK);
            }
        }
        if (b2) {
            ((Component)desktopPane).addComponentListener(new ComponentAdapter() {
                private final /* synthetic */ Container val$centerPanel = (Container)desktopPane;
                
                public void componentResized(final ComponentEvent componentEvent) {
                    if (b) {
                        MediaApplet.this.imagePanel.setSize(this.val$centerPanel.getSize());
                    }
                    else {
                        Dimension dimension = MediaApplet.this.imagePanel.getImageSize();
                        if (dimension.getWidth() == 0.0 || dimension.getHeight() == 0.0) {
                            dimension = MediaApplet.this.imagePanel.getSize();
                        }
                        final double n = dimension.getWidth() / dimension.getHeight();
                        final int width = this.val$centerPanel.getWidth();
                        final int height = this.val$centerPanel.getHeight();
                        int n2;
                        int n3;
                        if (width / height > n) {
                            n2 = (int)(height * n);
                            n3 = height;
                        }
                        else {
                            n2 = width;
                            n3 = (int)(width / n);
                        }
                        MediaApplet.this.imagePanel.setSize(n2, n3);
                    }
                    MediaApplet.this.clickPanel.setSize(MediaApplet.this.imagePanel.getSize());
                    MediaApplet.this.desktopPane.setSize(MediaApplet.this.imagePanel.getSize());
                    this.val$centerPanel.validate();
                }
            });
        }
        else {
            this.imagePanel.addComponentListener(new ComponentAdapter() {
                private final /* synthetic */ Container val$centerPanel = (Container)desktopPane;
                
                public void componentResized(final ComponentEvent componentEvent) {
                    MediaApplet.this.clickPanel.setSize(MediaApplet.this.imagePanel.getSize());
                    this.val$centerPanel.validate();
                    if (MediaApplet.frame != null) {
                        MediaApplet.frame.pack();
                    }
                }
            });
        }
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (this.panels[2] != null) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.weighty = 1.0;
            if (b2) {
                gridBagConstraints.fill = 3;
                gridBagConstraints.weightx = 0.0;
            }
            else {
                gridBagConstraints.fill = 1;
                gridBagConstraints.weightx = 0.5;
            }
            contentPane.add(this.panels[2], gridBagConstraints);
        }
        if (this.panels[0] != null) {
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            if (b2) {
                gridBagConstraints.fill = 2;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
            }
            else {
                gridBagConstraints.fill = 1;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.5;
            }
            contentPane.add(this.panels[0], gridBagConstraints);
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        if (b2) {
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
        }
        else {
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
        }
        contentPane.add((Component)desktopPane, gridBagConstraints);
        if (this.panels[1] != null) {
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            if (b2) {
                gridBagConstraints.fill = 2;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 0.0;
            }
            else {
                gridBagConstraints.fill = 1;
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.weighty = 0.5;
            }
            contentPane.add(this.panels[1], gridBagConstraints);
        }
        if (this.panels[3] != null) {
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.weighty = 1.0;
            if (b2) {
                gridBagConstraints.fill = 3;
                gridBagConstraints.weightx = 0.0;
            }
            else {
                gridBagConstraints.fill = 1;
                gridBagConstraints.weightx = 0.5;
            }
            contentPane.add(this.panels[3], gridBagConstraints);
        }
        this.setVisible(true);
        new Thread(this.imagePanel).start();
    }
    
    public synchronized void start() {
        if (this.tooOldVM) {
            System.out.println("START refused");
            return;
        }
        System.out.println("START");
        this.playButton.setEnabled(false);
        this.stopButton.setEnabled(true);
        this.playMenuButton.setEnabled(false);
        this.stopMenuButton.setEnabled(true);
        this.imagePanel.setStopped(false);
        final Enumeration<Plugin> elements = this.plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().start();
        }
    }
    
    public synchronized void stop() {
        System.out.println("STOP");
        this.playButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        this.playMenuButton.setEnabled(true);
        this.stopMenuButton.setEnabled(false);
        if (!this.tooOldVM) {
            this.imagePanel.setStopped(true);
        }
        final Enumeration<Plugin> elements = this.plugins.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().stop();
        }
        this.fpsLabel.setText("0 fps");
    }
    
    public synchronized void destroy() {
        try {
            Thread.currentThread();
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex) {
            System.err.println("Interrupted in sleep");
        }
        System.out.println("DESTROY");
    }
    
    private void setCGIParameters(final URL url) {
        final String string = url.toString();
        final String replaceFirst = string.replaceFirst(".*[?](.*[=].*)", "$1");
        if (!replaceFirst.equals(string)) {
            final StringTokenizer stringTokenizer = new StringTokenizer(replaceFirst, "&=");
            while (stringTokenizer.hasMoreElements()) {
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                if (nextToken != null && nextToken2 != null) {
                    this.CGIParameters.put(nextToken, nextToken2);
                }
            }
        }
    }
    
    private void parseOptions(final String[] array) {
        if (array.length < 1) {
            printUsageAndExit(1);
        }
        this.setParameter("ama_password", "pass");
        this.setParameter("ama_user", "root");
        for (int i = 0; i < array.length; ++i) {
            if (array[i].startsWith("--")) {
                if (i == array.length - 1) {
                    printUsageAndExit(1);
                }
                if (array[i].equals("--help") || array[i].equals("--hide-video-controls")) {
                    this.setParameter("ama_" + array[i].substring(2), "yes");
                }
                else if (array[i].equals("--cgi-path") || array[i].equals("--param-path") || array[i].equals("--plugins") || array[i].equals("--password") || array[i].equals("--user") || array[i].equals("--zoom")) {
                    if (i == array.length - 2 || array[i + 1].startsWith("--")) {
                        System.err.println("Option '" + array[i] + "' " + "requires an argument");
                        printUsageAndExit(1);
                    }
                    this.setParameter("ama_" + array[i].substring(2), array[i + 1]);
                    ++i;
                }
                else if (this.getParameter("ama_plugins") != null) {
                    System.out.println("Unrecognized option '" + array[i] + "' will be stored for plug-ins to use.");
                    if (i != array.length - 2 && !array[i + 1].startsWith("--")) {
                        this.setParameter("ama_" + array[i].substring(2), array[i + 1]);
                        ++i;
                    }
                    else {
                        this.setParameter("ama_" + array[i].substring(2), "yes");
                    }
                }
                else {
                    System.err.println("Invalid option: '" + array[i] + "'");
                    printUsageAndExit(1);
                }
            }
            else {
                if (i == array.length - 1) {
                    this.setParameter("ama_url", array[i]);
                    return;
                }
                System.err.println("Invalid argument: '" + array[i] + "'");
                printUsageAndExit(1);
            }
        }
        printUsageAndExit(1);
    }
    
    private static void printUsageAndExit(final int n) {
        ((n == 0) ? System.out : System.err).println("Usage: [OPTIONS] URL\n\nOptions:\n    --cgi-path             relative path to CGI directory\n    --param-path           relative path to parameters supported by the device\n    --help                 show this help and exit\n    --hide-video-controls  do not show the video control panel\n    --password PASSWD      http-password, default is 'pass'\n    --plugins P1[,P2...]]  list of plug-ins to load\n    --user NAME            http-user, default is 'root'\n    --zoom R|auto|free     zoom rate:\n                               R:    zoom R times, e.g '0.5' or '2'\n                               auto: zoom to fit, preserve aspect ratio\n                               free: zoom to fit, no aspect ratio\n                           default is '1x'\n\nNote: when using --plugins, any other options will be accepted as well.");
        System.exit(n);
    }
    
    public static void main(final String[] array) {
        try {
            (MediaApplet.frame = new JFrame("Live View")).addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
            final MediaApplet mediaApplet = new MediaApplet();
            mediaApplet.parseOptions(array);
            if (mediaApplet.getParameter("ama_help") != null) {
                printUsageAndExit(0);
            }
            MediaApplet.frame.setContentPane(mediaApplet.getContentPane());
            mediaApplet.init();
            MediaApplet.frame.pack();
            MediaApplet.frame.setVisible(true);
            mediaApplet.start();
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    static {
        MediaApplet.frame = null;
    }
    
    private class RootPassAuthenticator extends Authenticator
    {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MediaApplet.this.getParameter("ama_user"), MediaApplet.this.getParameter("ama_password").toCharArray());
        }
    }
}
