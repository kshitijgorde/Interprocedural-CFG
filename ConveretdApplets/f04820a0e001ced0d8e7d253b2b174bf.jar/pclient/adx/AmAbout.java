// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
import pclient.shd.Config;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmAbout extends JPanel implements ActionListener
{
    private AdmHandler adminHandler;
    private static EmptyBorder border5;
    private static EmptyBorder border10;
    
    public AmAbout(final AdmHandler adminHandler) {
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("About", null, createBasic(this.adminHandler.paraApplet.paraConf), "About");
        this.add(panel, "Center");
    }
    
    protected static JComponent createBasic(final Config config) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(AmAbout.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(getCopy(config));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 30)));
        verticalPanel.add(getVersion(config));
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private static JComponent getCopy(final Config config) {
        return getComponent("<html><CENTER>" + config.serverConf("Abt.Site", "http://www.parachat.com") + "<BR>" + config.serverConf("Abt.Copy", "Copyright 1996-2010 by ParaChat Group") + "<BR>" + " All Rights Reserved" + " <BR> </CENTER></html>", "Copyright");
    }
    
    private static JComponent getVersion(final Config config) {
        return getComponent("<html><CENTER><BR>" + config.serverConf("Abt.Version", "ParaChat Version: ") + "9.12" + "<BR>" + getOS() + "<BR>" + getJava() + "<BR>" + " <BR> </CENTER></html>", "Version");
    }
    
    private static JComponent getComponent(final String s, final String s2) {
        final JLabel label = new JLabel(s);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.5f);
        verticalPanel.setAlignmentX(0.5f);
        verticalPanel.add(label);
        final JPanel verticalPanel2 = CompUtil.createVerticalPanel(true);
        verticalPanel2.setBorder(new CompoundBorder(new TitledBorder(null, s2, 1, 2), AmAbout.border5));
        verticalPanel2.setAlignmentY(0.0f);
        verticalPanel2.setAlignmentX(0.5f);
        verticalPanel2.add(verticalPanel);
        verticalPanel2.add(Box.createRigidArea(new Dimension(2, 2)));
        return verticalPanel2;
    }
    
    private static String getOS() {
        return "Your Operating System: " + System.getProperty("os.name", "unknown") + " " + System.getProperty("os.version", "unknown");
    }
    
    private static String getJava() {
        return "Your Java Version: " + System.getProperty("java.vendor", "unknown") + " " + System.getProperty("java.version", "unknown");
    }
    
    static {
        AmAbout.border5 = new EmptyBorder(5, 5, 5, 5);
        AmAbout.border10 = new EmptyBorder(10, 10, 10, 10);
    }
}
