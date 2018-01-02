// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ListModel;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.PopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

public class JUpload extends Applet implements ActionListener, MouseListener
{
    protected JPanel controlPanel;
    protected JPanel statuspanel;
    protected StatPanel statpanel;
    DefaultListModel tModel;
    JList list;
    String host;
    startup applet;
    protected JButton add;
    protected JButton remove;
    protected JButton upload;
    protected JPanel buttonPanel;
    protected JPanel infoPanel;
    protected JScrollPane listScroller;
    protected LogoPanel myLogoPanel;
    protected PopupMenu popup;
    protected JSplitPane jsp;
    
    public JUpload(final startup applet) {
        this.host = null;
        Configurator.readConfiguration(applet);
        ProxyConfig.readConfiguration();
        this.applet = applet;
        this.init();
        this.start();
    }
    
    public Applet getStartupApplet() {
        return this.applet;
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.debug(e.toString());
        if (e.getActionCommand().equalsIgnoreCase("recurse_on")) {
            Configurator.changeProperty("useRecursivePaths", "true");
        }
        if (e.getActionCommand().equalsIgnoreCase("recurse_off")) {
            Configurator.changeProperty("useRecursivePaths", "false");
        }
        if (e.getActionCommand().equalsIgnoreCase("help_applet_tag")) {
            new HelpWindow(Configurator.helpPage());
        }
        if (e.getActionCommand().equalsIgnoreCase("help_about")) {
            new AboutWindow();
        }
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
    
    public void init() {
        this.debug("JUpload() init()");
        this.applet.setBackground(Configurator.getBackgroundColor());
        this.applet.getAppletContext().showStatus(this.getAppletInfo());
        this.host = this.applet.getCodeBase().getHost();
        this.debug("JUpload() init() setting up actionURL");
        this.debug("JUpload() init() creating layout");
        this.debug("JUpload() init() creating defaultlistmodel");
        this.tModel = new DefaultListModel();
        (this.list = new JList(this.tModel)).setSelectionMode(1);
        this.list.setVisibleRowCount(-1);
        this.debug("JUpload() init() creating scroller");
        this.listScroller = new JScrollPane(this.list);
        this.debug("JUpload() init() creating buttonpanel");
        (this.controlPanel = new JPanel()).setLayout(new BorderLayout());
        this.createPopupMenu();
        this.controlPanel.add(this.popup);
        this.controlPanel.addMouseListener(this);
        this.controlPanel.setBackground(Configurator.getBackgroundColor());
        (this.buttonPanel = new JPanel()).setLayout(new BoxLayout(this.buttonPanel, 1));
        this.buttonPanel.setBackground(Configurator.getBackgroundColor());
        this.add = new JButton(Configurator.getLabelAdd());
        this.remove = new JButton(Configurator.getLabelRemove());
        this.upload = new JButton(Configurator.getLabelUpload());
        this.add.setToolTipText(Configurator.getAddToolTip());
        this.remove.setToolTipText(Configurator.getRemoveToolTip());
        this.upload.setToolTipText(Configurator.getUploadToolTip());
        this.debug("JUpload() init() creating listeners and adding to button");
        this.add.addActionListener(new myAddListener(this, this.tModel));
        this.remove.addActionListener(new myRemoveListener(this, this.tModel, this.list));
        this.upload.addActionListener(new myUploadListener(this, this.tModel, this.applet.context));
        this.buttonPanel.add(this.add);
        this.buttonPanel.add(this.remove);
        this.buttonPanel.add(this.upload);
        this.add.setAlignmentX(0.5f);
        this.remove.setAlignmentX(0.5f);
        this.upload.setAlignmentX(0.5f);
        this.controlPanel.add(this.buttonPanel, "North");
        (this.infoPanel = new JPanel()).setLayout(new BorderLayout());
        this.infoPanel.setBackground(Configurator.getBackgroundColor());
        (this.statpanel = new StatPanel()).setBackground(Configurator.getBackgroundColor());
        this.statpanel.updateColor();
        this.statpanel.setAlignmentX(0.5f);
        (this.statuspanel = new JPanel()).setBackground(Configurator.getBackgroundColor());
        this.statuspanel.setAlignmentX(0.5f);
        this.infoPanel.add(this.statpanel, "North");
        this.infoPanel.add(this.statuspanel, "Center");
        this.controlPanel.add(this.infoPanel, "Center");
        (this.myLogoPanel = new LogoPanel(this.applet)).init();
        this.myLogoPanel.setBackground(Configurator.getBackgroundColor());
        this.myLogoPanel.setAlignmentX(0.5f);
        this.controlPanel.add(this.myLogoPanel, "South");
        (this.jsp = new JSplitPane()).setPreferredSize(new Dimension(this.applet.getWidth(), this.applet.getHeight()));
        this.jsp.setDividerSize(2);
        this.jsp.setDividerLocation(0.75);
        this.applet.add(this.jsp);
        this.jsp.add(this.listScroller, "left");
        this.jsp.add(this.controlPanel, "right");
        this.applet.validate();
        this.applet.invalidate();
        this.controlPanel.invalidate();
        this.listScroller.invalidate();
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.debug(e.toString());
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.debug(e.toString());
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.debug(e.toString());
            this.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    public void start() {
        this.debug("JUpload() start() running from host " + this.host);
    }
    
    public void stop() {
        this.debug("JUpload() stop(). ");
        this.debug("   stop() removing listScroller and buttonpanel");
        this.controlPanel.remove(this.myLogoPanel);
        this.applet.remove(this.listScroller);
        this.applet.remove(this.controlPanel);
        this.listScroller = null;
        this.controlPanel = null;
        this.myLogoPanel = null;
    }
    
    private void createPopupMenu() {
        this.popup = new PopupMenu("JUpload Settings");
        MenuItem mi = new MenuItem("About JUpload...");
        mi.setActionCommand("help_about");
        mi.addActionListener(this);
        this.popup.add(mi);
        this.popup.addSeparator();
        mi = new MenuItem("Help - APPLET Tag");
        mi.setActionCommand("help_applet_tag");
        mi.addActionListener(this);
        this.popup.add(mi);
    }
}
