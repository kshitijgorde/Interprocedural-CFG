// 
// Decompiled by Procyon v0.5.30
// 

package testvm2;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.Locale;
import java.applet.AppletContext;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class Main extends JApplet
{
    private final String imgFileName = "animation.gif";
    private static String javaVendorURL;
    private String javaVersion;
    private ImageIcon imgIcon;
    private String title;
    private String subtitle;
    private String[] sysPropertiesString;
    private ResourceBundle resourceBundle;
    private static AppletContext appletContext;
    private BannerPanel bannerPanel;
    private int subtitleSize;
    
    public Main() {
        this.subtitle = "";
        this.sysPropertiesString = new String[12];
        this.subtitleSize = 25;
    }
    
    public void init() {
        this.setSize(500, 340);
        Main.appletContext = this.getAppletContext();
        Main.javaVendorURL = System.getProperty("java.vendor.url");
        if (Main.javaVendorURL.equals("http://java.sun.com/")) {
            Main.javaVendorURL = "http://www.java.com/";
        }
        this.subtitleSize = new Integer(this.getParameter("subtitleSize"));
        final String param = this.getParameter("locale");
        Locale locale;
        if (param.length() == 2) {
            locale = new Locale(param);
        }
        else {
            locale = new Locale(param.substring(0, 2), param.substring(3));
        }
        this.resourceBundle = ResourceBundle.getBundle("testVM", locale);
        this.sysPropertiesString[0] = this.resourceBundle.getString("config");
        this.sysPropertiesString[1] = "";
        this.sysPropertiesString[2] = this.resourceBundle.getString("vendor");
        this.sysPropertiesString[3] = this.resourceBundle.getString("version");
        this.sysPropertiesString[4] = this.resourceBundle.getString("os");
        this.sysPropertiesString[5] = this.resourceBundle.getString("arch");
        this.sysPropertiesString[6] = "";
        this.sysPropertiesString[7] = "";
        this.sysPropertiesString[8] = System.getProperty("java.vendor");
        this.javaVersion = System.getProperty("java.version");
        final String family = this.javaVersion.substring(0, 5);
        String update = "";
        if (this.javaVersion.length() > 5 && this.javaVersion.length() < 9) {
            update = this.javaVersion.substring(6, 8);
        }
        if (family.compareTo("1.5.0") >= 0) {
            this.sysPropertiesString[9] = "Java " + family.substring(2, 3) + " Update " + update;
        }
        else {
            this.sysPropertiesString[9] = "Java " + family + " Update " + update;
        }
        this.sysPropertiesString[10] = System.getProperty("os.name") + " " + System.getProperty("os.version");
        this.sysPropertiesString[11] = System.getProperty("os.arch");
        this.title = this.resourceBundle.getString("working");
        final int MAX_IMAGE_SIZE = 50000;
        final BufferedInputStream iStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("animation.gif"));
        int count = 0;
        if (iStream != null) {
            final byte[] buf = new byte[50000];
            try {
                count = iStream.read(buf);
                iStream.close();
            }
            catch (IOException ex2) {
                System.err.println("Cannot read animated file");
            }
            if (count <= 0) {
                System.err.println("Empty file");
            }
            this.imgIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(buf));
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Main.this.createGUI();
                }
            });
        }
        catch (Exception ex) {
            System.out.println("Cannot create GUI");
            ex.printStackTrace();
        }
    }
    
    public void start() {
        super.start();
        String testVM_latestJRE = "";
        testVM_latestJRE = this.getLatestJREVersion();
        final int i = testVM_latestJRE.compareTo(this.javaVersion);
        if (!this.sysPropertiesString[10].matches(".*Mac.*")) {
            if (i == 0) {
                this.subtitle = this.resourceBundle.getString("latest");
            }
            else if (i > 0) {
                this.subtitle = this.resourceBundle.getString("update");
            }
        }
        this.bannerPanel.updateSubtitle(this.subtitle);
    }
    
    private String getLatestJREVersion() {
        final ReadFileThread t = new ReadFileThread(this.getCodeBase());
        t.start();
        try {
            t.join();
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return t.getLatestJREVersion();
    }
    
    private void createGUI() {
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getRootPane().setBorder(new LineBorder(Color.BLACK));
        (this.bannerPanel = new BannerPanel(this.imgIcon, this.title, this.subtitle, this.subtitleSize)).setBackground(Color.WHITE);
        this.getContentPane().add(this.bannerPanel, "North");
        final InfoPanel infoPanel = new InfoPanel(this.sysPropertiesString);
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setSize(490, 170);
        this.getContentPane().add(infoPanel, "Center");
    }
    
    protected static void showJavaVendor() {
        final AppletContext context = Main.appletContext;
        try {
            context.showDocument(new URL(Main.javaVendorURL), "_self");
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
