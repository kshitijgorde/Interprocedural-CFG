// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
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

public class AmHelp extends JPanel implements ActionListener
{
    private static final String ACT_FAQ = "FAQ";
    private static final String ACT_ADM = "adm";
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmHelp(final AdmHandler adminHandler) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("com=" + actionCommand);
        if ("FAQ".equals(actionCommand)) {
            this.adminHandler.browser(this.adminHandler.paraConf.get("Adm.Hp.DocPage", "http://www.parachat.com/help/"));
        }
        else if ("adm".equals(actionCommand)) {
            this.adminHandler.browser(this.adminHandler.paraConf.get("Adm.Hp.AdmPage", "http://www.parachat.com/hosted_rooms.html"));
        }
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Help", null, this.createBasic(), "Help and FAQ");
        tabbedPane.addTab("About", null, AmAbout.createBasic(this.adminHandler.paraApplet.paraConf), "About and Copyright");
        this.add(panel, "Center");
    }
    
    private JComponent createBasic() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getHelp());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getAdmin());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getHelp() {
        final JLabel label = new JLabel("<html><p>" + "Load the Documentation in a new web page for information on customizing, managing and administering your chat service." + "</p></html>");
        final JButton button = new JButton("Documentation");
        button.setActionCommand("FAQ");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(true);
        horizontalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Documentation", 1, 2), this.border5));
        horizontalPanel.setAlignmentY(0.0f);
        horizontalPanel.add(label);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button);
        return horizontalPanel;
    }
    
    private JComponent getAdmin() {
        final JLabel label = new JLabel("<html><p>" + "Load the Service Administration Area in a new web page to manage your chat service." + "</p></html>");
        final JButton button = new JButton("Admin Area");
        button.setActionCommand("adm");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(true);
        horizontalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Service Administration Area", 1, 2), this.border5));
        horizontalPanel.setAlignmentY(0.0f);
        horizontalPanel.add(label);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 2)));
        horizontalPanel.add(button);
        return horizontalPanel;
    }
}
