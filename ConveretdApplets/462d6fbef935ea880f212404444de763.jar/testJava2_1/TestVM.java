// 
// Decompiled by Procyon v0.5.30
// 

package testJava2_1;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.BufferedInputStream;
import javax.swing.ImageIcon;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.JEditorPane;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JLabel;
import java.util.ResourceBundle;
import javax.swing.JPanel;

public class TestVM extends JPanel
{
    private static final String IMGFILE_S = "animation_no_s_crop_sm.gif";
    private static final String IMGFILE_CHECK = "greencheck.png";
    private static final String IMGFILE_TIP = "ic_tipblue24x24.png";
    private static final int TITLEFONTSIZE_I = 22;
    private static final int SUBTITLEFONTSIZE_I = 16;
    private String title_s;
    private String latest_s;
    private String update_s;
    private String config_s;
    private ResourceBundle resource;
    private String userVersion;
    private StringBuffer version_sb;
    private StringBuffer vendor_sb;
    private StringBuffer os_sb;
    private StringBuffer arch_sb;
    private JLabel subtitleLabel;
    private boolean isMac;
    private final SwingWorker worker;
    
    public TestVM(final URL codebase) {
        this.isMac = false;
        (this.worker = new SwingWorker() {
            String version = null;
            
            public Object construct() {
                try {
                    final URL versionURL = new URL(codebase, "JreCurrentVersion2.txt");
                    final BufferedReader brVersion = new BufferedReader(new InputStreamReader(versionURL.openStream()));
                    while (brVersion.ready()) {
                        this.version = brVersion.readLine();
                        this.version = this.version.replaceAll("[^\\d\\x2E_]", "");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error reading latest JRE version.");
                }
                finally {
                    return this.version;
                }
            }
        }).start();
        this.initOutputStrings();
        this.getProperties();
        this.createGUI();
        final Object obj = this.worker.get();
        if (!this.isMac) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (obj != null) {
                        System.out.println("Obj:" + obj);
                        System.out.println("Exit worker loop");
                        TestVM.this.updateSubtitleLabel(obj.toString());
                    }
                    else {
                        System.out.println("Cannot get latest version");
                        System.out.println("Obj:" + obj);
                    }
                }
            });
        }
    }
    
    private void updateSubtitleLabel(final String s) {
        if (this.userVersion.equals(s)) {
            this.subtitleLabel.setIcon(this.createImageIcon("greencheck.png"));
            this.subtitleLabel.setText(this.latest_s);
        }
        else if (this.userVersion.compareTo(s) < 1) {
            this.subtitleLabel.setIcon(this.createImageIcon("ic_tipblue24x24.png"));
            this.subtitleLabel.setText(this.update_s);
        }
    }
    
    private void initOutputStrings() {
        this.resource = ResourceBundle.getBundle("testVM");
        this.title_s = this.resource.getString("working");
        this.latest_s = this.resource.getString("latest");
        this.update_s = this.resource.getString("update");
        this.config_s = this.resource.getString("config");
        this.version_sb = new StringBuffer(this.resource.getString("version") + " ");
        this.vendor_sb = new StringBuffer(this.resource.getString("vendor") + " ");
        this.os_sb = new StringBuffer(this.resource.getString("os") + " ");
        this.arch_sb = new StringBuffer(this.resource.getString("arch") + " ");
    }
    
    private void getProperties() {
        this.userVersion = System.getProperty("java.version");
        final StringTokenizer st = new StringTokenizer(this.userVersion, ".");
        if (st.countTokens() == 3) {
            st.nextElement();
            final String family = st.nextElement().toString();
            if (family.equals("4")) {
                this.version_sb.append("<b>Java 2 Runtime, SE v").append(this.userVersion);
            }
            else if (family.equals("5")) {
                this.version_sb.append("<b>J2SE Runtime ").append(family);
                final String[] subV = st.nextElement().toString().split("_");
                if (subV.length == 2) {
                    if (!subV[0].equals("0")) {
                        this.version_sb.append(".").append(subV[0]);
                    }
                    this.version_sb.append(" Update ").append(subV[1]);
                }
            }
            else if (family.equals("6")) {
                this.version_sb.append("<b>Java SE ").append(family);
                final String[] subV = st.nextElement().toString().split("_");
                if (subV.length == 2) {
                    if (!subV[0].equals("0")) {
                        this.version_sb.append(".").append(subV[0]);
                    }
                    this.version_sb.append(" Update ").append(subV[1]);
                }
            }
            else {
                this.version_sb.append("<b>Java SE ").append(family);
                final String[] subV = st.nextElement().toString().split("_");
                if (subV.length == 2) {
                    if (!subV[0].equals("0")) {
                        this.version_sb.append(".").append(subV[0]);
                    }
                    this.version_sb.append(" Update ").append(subV[1]);
                }
            }
            this.version_sb.append("</b>");
        }
        else {
            this.version_sb.append("<b>").append(this.userVersion).append("</b>");
        }
        this.vendor_sb.append("<b><a href=\"http://java.com\">").append(System.getProperty("java.vendor")).append("</a></b>");
        final String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("mac")) {
            this.isMac = true;
        }
        this.os_sb.append("<b>").append(os).append(" ").append(System.getProperty("os.version")).append("</b>");
        this.arch_sb.append("<b>").append(System.getProperty("os.arch")).append("</b>");
    }
    
    private void createGUI() {
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        final JPanel bannerPanel = new JPanel(new FlowLayout(3));
        bannerPanel.setBackground(Color.WHITE);
        final JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, 1));
        headlinePanel.setBackground(Color.WHITE);
        final JLabel titleLabel = new JLabel(this.title_s);
        titleLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
        titleLabel.setFont(new Font("AERIAL", 1, 22));
        titleLabel.setForeground(Color.decode("#000000"));
        titleLabel.setBackground(Color.WHITE);
        (this.subtitleLabel = new JLabel()).setBorder(new EmptyBorder(0, 10, 0, 0));
        this.subtitleLabel.setFont(new Font("AERIAL", 1, 16));
        this.subtitleLabel.setForeground(Color.decode("#000000"));
        headlinePanel.add(titleLabel);
        headlinePanel.add(this.subtitleLabel);
        bannerPanel.add(new JLabel(this.createImageIcon("animation_no_s_crop_sm.gif")), "West");
        bannerPanel.add(headlinePanel, "Center");
        this.add(bannerPanel, "North");
        final JEditorPane infoEditorPane = new JEditorPane();
        infoEditorPane.setEditorKit(new HTMLEditorKit() {
            protected Parser getParser() {
                try {
                    final Class c = Class.forName("javax.swing.text.html.parser.ParserDelegator");
                    final Parser defaultParser = c.newInstance();
                    return defaultParser;
                }
                catch (Throwable e) {
                    return null;
                }
            }
        });
        infoEditorPane.putClientProperty("JEditorPane.honorDisplayProperties", Boolean.TRUE);
        infoEditorPane.setFont(new Font("Arial", 0, 12));
        infoEditorPane.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 0));
        infoEditorPane.setBackground(Color.decode("#F3F3F3"));
        infoEditorPane.setContentType("text/html");
        infoEditorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(final HyperlinkEvent r) {
                try {
                    if (r.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        TestVMApplet.showJavaVendorURL(r.getURL());
                    }
                }
                catch (Exception ex) {}
            }
        });
        infoEditorPane.setEditable(false);
        if (this.isMac) {
            this.config_s = "<br/>" + this.config_s;
        }
        System.out.println(this.config_s + "<br/> <br/>" + (Object)this.vendor_sb + "<br/>" + (Object)this.version_sb + "<br/>" + (Object)this.os_sb + "<br/>" + (Object)this.arch_sb);
        infoEditorPane.setText(this.config_s + "<br><br>" + (Object)this.vendor_sb + "<br>" + (Object)this.version_sb + "<br>" + (Object)this.os_sb + "<br>" + (Object)this.arch_sb);
        final JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createMatteBorder(5, 30, 10, 30, Color.decode("#F3F3F3")));
        infoPanel.add(infoEditorPane, "Center");
        this.add(infoPanel);
    }
    
    private ImageIcon createImageIcon(final String imageFileName) {
        ImageIcon imgIcon = null;
        final int MAX_IMAGE_SIZE = 50000;
        final BufferedInputStream iStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(imageFileName));
        int count = 0;
        if (iStream != null) {
            final byte[] buf = new byte[50000];
            try {
                count = iStream.read(buf);
                iStream.close();
            }
            catch (IOException ex) {
                System.err.println("Cannot read animated file");
            }
            if (count <= 0) {
                System.err.println("Empty file");
            }
            imgIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(buf));
        }
        return imgIcon;
    }
}
