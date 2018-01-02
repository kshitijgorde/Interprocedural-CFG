// 
// Decompiled by Procyon v0.5.30
// 

package testvm2_1;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.BufferedInputStream;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JEditorPane;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Font;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.util.StringTokenizer;
import javax.swing.SwingUtilities;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.AppletContext;
import javax.swing.JLabel;
import java.util.ResourceBundle;
import javax.swing.JPanel;

public class TestVM extends JPanel
{
    private static String latestVersion_s;
    private static final String IMGFILE_S = "animation_no_s.gif";
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
    private AppletContext appletContext;
    
    public TestVM(final URL codebase) {
        final SwingWorker worker = new SwingWorker() {
            String version;
            
            public Object construct() {
                try {
                    final URL versionURL = new URL(codebase, "JreCurrentVersion2.txt");
                    final BufferedReader brVersion = new BufferedReader(new InputStreamReader(versionURL.openStream()));
                    while (brVersion.ready()) {
                        this.version = brVersion.readLine();
                    }
                }
                catch (Exception e) {
                    System.out.println("Error reading latest JRE version.");
                }
                finally {
                    return this.version;
                }
            }
        };
        worker.start();
        this.initOutputStrings();
        this.getProperties();
        this.createGUI();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final String s = worker.get().toString();
                TestVM.this.updateSubtitleLabel(s);
            }
        });
    }
    
    private void updateSubtitleLabel(final String s) {
        if (this.userVersion.equals(s)) {
            this.subtitleLabel.setText(this.latest_s);
        }
        else if (this.userVersion.compareTo(s) < 1) {
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
        this.os_sb.append("<b>").append(System.getProperty("os.name")).append(" ").append(System.getProperty("os.version")).append("</b>");
        this.arch_sb.append("<b>").append(System.getProperty("os.arch")).append("</b>");
    }
    
    private void createGUI() {
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        final JPanel bannerPanel = new JPanel(new FlowLayout(3));
        bannerPanel.setBackground(Color.WHITE);
        final JPanel headlinePanel = new JPanel();
        headlinePanel.setLayout(new BoxLayout(headlinePanel, 1));
        headlinePanel.setBackground(Color.WHITE);
        final JLabel titleLabel = new JLabel(this.title_s);
        titleLabel.setFont(new Font("AERIAL", 1, 22));
        titleLabel.setForeground(Color.decode("#53819F"));
        titleLabel.setBackground(Color.WHITE);
        (this.subtitleLabel = new JLabel()).setFont(new Font("AERIAL", 1, 16));
        this.subtitleLabel.setForeground(Color.decode("#E56F0E"));
        headlinePanel.add(titleLabel);
        headlinePanel.add(this.subtitleLabel);
        bannerPanel.add(new JLabel(this.createImageIcon()), "West");
        bannerPanel.add(headlinePanel, "Center");
        this.add(bannerPanel, "North");
        if (this.userVersion.equals("1.6.0_22")) {
            final ParserDelegator workaround = new ParserDelegator();
        }
        final JEditorPane infoEditorPane = new JEditorPane();
        infoEditorPane.setBorder(BorderFactory.createEmptyBorder(20, 100, 3, 0));
        infoEditorPane.setBackground(Color.decode("#F3F3F3"));
        infoEditorPane.setContentType("text/html");
        infoEditorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(final HyperlinkEvent r) {
                try {
                    if (r.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        TestVMApplet.showJavaVendorURL();
                    }
                }
                catch (Exception ex) {}
            }
        });
        infoEditorPane.setEditable(false);
        infoEditorPane.setText(this.config_s + "<br/><br/>" + (Object)this.vendor_sb + "<br/>" + (Object)this.version_sb + "<br/>" + (Object)this.os_sb + "<br/>" + (Object)this.arch_sb);
        final JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createMatteBorder(15, 30, 15, 30, Color.WHITE));
        infoPanel.add(infoEditorPane, "Center");
        this.add(infoPanel);
    }
    
    private ImageIcon createImageIcon() {
        ImageIcon imgIcon = null;
        final int MAX_IMAGE_SIZE = 50000;
        final BufferedInputStream iStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("animation_no_s.gif"));
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
