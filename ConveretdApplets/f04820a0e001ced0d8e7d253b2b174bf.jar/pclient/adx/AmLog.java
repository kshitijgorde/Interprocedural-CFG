// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import pclient.adv.TextAreaCopy;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import java.awt.Point;
import pclient.shd.ClientUtil;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmLog extends JPanel implements ActionListener, WindowListener
{
    private static final String ACT_CLR = "clear";
    private static final String ACT_POP = "pop";
    private JTextArea logTextArea;
    private JPanel statusPanel;
    private JScrollPane statusPane;
    private JFrame popWindow;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmLog(final AdmHandler adminHandler) {
        this.popWindow = null;
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("clear".equals(actionCommand)) {
            this.logTextArea.setText("");
        }
        else if ("pop".equals(actionCommand)) {
            this.popWin();
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.popWindow.setVisible(false);
        this.popWindow.removeAll();
        this.popWindow.dispose();
        this.popWindow = null;
        this.statusPanel.add(this.statusPane);
        this.statusPanel.invalidate();
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    protected void popWin() {
        if (this.popWindow != null) {
            this.popWindow.setVisible(true);
            return;
        }
        (this.popWindow = new JFrame()).setSize(500, 360);
        this.popWindow.addWindowListener(this);
        this.popWindow.setDefaultCloseOperation(0);
        this.statusPanel.remove(this.statusPane);
        this.popWindow.getContentPane().setLayout(new BorderLayout());
        this.popWindow.getContentPane().add(this.statusPane, "Center");
        this.popWindow.setVisible(true);
        this.statusPanel.invalidate();
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    protected void appendLog(final String s) {
        this.logTextArea.append(s);
        this.scrollBottom();
    }
    
    private void scrollBottom() {
        ClientUtil.doze(70);
        final JViewport viewport = this.statusPane.getViewport();
        viewport.setViewPosition(new Point(0, viewport.getViewSize().height - viewport.getExtentSize().height));
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Log History", null, this.createLog(), "Admin console history log");
        this.add(panel, "Center");
    }
    
    private JComponent createLog() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getStatus());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getStatus() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(new JLabel("<html><p>" + "Administrator activity that occurs during this session, and responses from the chat server, are logged below.  Click Clear to erase the log. Click Pop to load the log data in a separate window for copy/paste." + "</p></html>"));
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton button = new JButton("Clear");
        horizontalPanel.add(button);
        button.setActionCommand("clear");
        button.addActionListener(this);
        final JButton button2 = new JButton("Pop");
        horizontalPanel.add(button2);
        button2.setActionCommand("pop");
        button2.addActionListener(this);
        final TextAreaCopy logTextArea = new TextAreaCopy(this.adminHandler.paraConf);
        logTextArea.setRows(12);
        logTextArea.setColumns(20);
        logTextArea.setEditable(false);
        final JScrollPane statusPane = new JScrollPane(logTextArea, 20, 31);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Log", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(statusPane);
        this.statusPanel = verticalPanel;
        this.statusPane = statusPane;
        this.logTextArea = logTextArea;
        return verticalPanel;
    }
}
