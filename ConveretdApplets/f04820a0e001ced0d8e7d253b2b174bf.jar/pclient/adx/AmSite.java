// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import pclient.shd.Config;
import pclient.adv.TextFieldCopy;
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
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmSite extends JPanel implements ActionListener
{
    protected JButton broadcastSite;
    protected JButton clearSite;
    private static final String ACT_SEND = "send";
    private static final String ACT_EMP = "emp";
    private JTextField textInput;
    private JTextField emptyInput;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmSite(final AdmHandler adminHandler) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("send".equals(actionCommand)) {
            final String text = this.textInput.getText();
            if (StringUtil.isEmpty(text)) {
                this.adminHandler.error("Message is empty.");
                return;
            }
            this.adminHandler.siteCast(text);
        }
        if ("emp".equals(actionCommand)) {
            this.adminHandler.siteKick(this.emptyInput.getText());
        }
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Message", null, this.createBasic(), "Broadcast messages");
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
        verticalPanel.add(this.getMessage());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getEmpty());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getMessage() {
        final JLabel label = new JLabel("<html><p>Send a message to all the active rooms of your site. </p></html>");
        final TextFieldCopy textInput = new TextFieldCopy((Config)null);
        textInput.setColumns(32);
        textInput.setPreferredSize(new Dimension(620, 24));
        textInput.setMaximumSize(new Dimension(1200, 24));
        final JButton broadcastSite = new JButton("Send");
        broadcastSite.setActionCommand("send");
        broadcastSite.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(textInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(broadcastSite);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Broadcast", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        this.broadcastSite = broadcastSite;
        this.textInput = textInput;
        return verticalPanel;
    }
    
    private JComponent getEmpty() {
        final JLabel label = new JLabel("<html><p>Empty all rooms of your site with this message. </p></html>");
        final TextFieldCopy emptyInput = new TextFieldCopy((Config)null);
        emptyInput.setColumns(32);
        emptyInput.setPreferredSize(new Dimension(620, 24));
        emptyInput.setMaximumSize(new Dimension(1200, 24));
        final JButton clearSite = new JButton("Empty");
        clearSite.setActionCommand("emp");
        clearSite.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(emptyInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(clearSite);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Kick out All Users", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        this.clearSite = clearSite;
        this.emptyInput = emptyInput;
        return verticalPanel;
    }
}
