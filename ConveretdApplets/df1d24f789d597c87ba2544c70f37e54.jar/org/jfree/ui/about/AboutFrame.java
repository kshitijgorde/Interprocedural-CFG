// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;
import java.awt.Image;
import java.util.ResourceBundle;
import javax.swing.border.Border;
import java.awt.Dimension;
import javax.swing.JFrame;

public class AboutFrame extends JFrame
{
    public static final Dimension PREFERRED_SIZE;
    public static final Border STANDARD_BORDER;
    private ResourceBundle resources;
    private String application;
    private String version;
    private String copyright;
    private String info;
    private Image logo;
    private List contributors;
    private String licence;
    private List libraries;
    
    public AboutFrame(final String s, final ProjectInfo projectInfo) {
        this(s, projectInfo.getName(), "Version " + projectInfo.getVersion(), projectInfo.getInfo(), projectInfo.getLogo(), projectInfo.getCopyright(), projectInfo.getLicenceText(), projectInfo.getContributors(), Arrays.asList(projectInfo.getLibraries()));
    }
    
    public AboutFrame(final String s, final String application, final String version, final String info, final Image logo, final String copyright, final String licence, final List contributors, final List libraries) {
        super(s);
        this.application = application;
        this.version = version;
        this.copyright = copyright;
        this.info = info;
        this.logo = logo;
        this.contributors = contributors;
        this.licence = licence;
        this.libraries = libraries;
        this.resources = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
        final JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(AboutFrame.STANDARD_BORDER);
        contentPane.add(this.createTabs());
        this.setContentPane(contentPane);
        this.pack();
    }
    
    public Dimension getPreferredSize() {
        return AboutFrame.PREFERRED_SIZE;
    }
    
    private JTabbedPane createTabs() {
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel aboutPanel = this.createAboutPanel();
        aboutPanel.setBorder(AboutFrame.STANDARD_BORDER);
        tabbedPane.add(this.resources.getString("about-frame.tab.about"), aboutPanel);
        final SystemPropertiesPanel systemPropertiesPanel = new SystemPropertiesPanel();
        systemPropertiesPanel.setBorder(AboutFrame.STANDARD_BORDER);
        tabbedPane.add(this.resources.getString("about-frame.tab.system"), systemPropertiesPanel);
        return tabbedPane;
    }
    
    private JPanel createAboutPanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        final AboutPanel aboutPanel = new AboutPanel(this.application, this.version, this.copyright, this.info, this.logo);
        boolean b = false;
        final JTabbedPane tabbedPane = new JTabbedPane();
        if (this.contributors != null) {
            final ContributorsPanel contributorsPanel = new ContributorsPanel(this.contributors);
            contributorsPanel.setBorder(AboutFrame.STANDARD_BORDER);
            tabbedPane.add(this.resources.getString("about-frame.tab.contributors"), contributorsPanel);
            b = true;
        }
        if (this.licence != null) {
            final JPanel licencePanel = this.createLicencePanel();
            licencePanel.setBorder(AboutFrame.STANDARD_BORDER);
            tabbedPane.add(this.resources.getString("about-frame.tab.licence"), licencePanel);
            b = true;
        }
        if (this.libraries != null) {
            final LibraryPanel libraryPanel = new LibraryPanel(this.libraries);
            libraryPanel.setBorder(AboutFrame.STANDARD_BORDER);
            tabbedPane.add(this.resources.getString("about-frame.tab.libraries"), libraryPanel);
            b = true;
        }
        panel.add(aboutPanel, "North");
        if (b) {
            panel.add(tabbedPane);
        }
        return panel;
    }
    
    private JPanel createLicencePanel() {
        final JPanel panel = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea(this.licence);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea));
        return panel;
    }
    
    static {
        PREFERRED_SIZE = new Dimension(560, 360);
        STANDARD_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    }
}
