// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import javax.swing.JToolBar;
import irc.managers.Resources;
import javax.swing.event.ChangeEvent;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.plaf.TabbedPaneUI;
import irc.channels.components.MyJtabbedUI;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import irc.channels.friends.FriendWindow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import irc.channels.chanlist.ChanListPanel;
import irc.channels.wholist.WhoListPanel;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class RightPanel extends JPanel implements ActionListener, ChangeListener
{
    JComponent friendwin;
    public WhoListPanel whoListPanel;
    public ChanListPanel chanListPanel;
    private EIRC eirc;
    private String currentpanel;
    private static final ImageIcon amis_arrow_down;
    private static final ImageIcon amis_arrow_right;
    private static final ImageIcon salon_arrow_down;
    private static final ImageIcon salon_arrow_right;
    private static final ImageIcon find_arrow_down;
    private static final ImageIcon find_arrow_right;
    JTabbedPane jtabbedPane;
    
    public RightPanel(final EIRC eirc) {
        this.currentpanel = "friends";
        this.eirc = eirc;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.eirc.revenir();
    }
    
    public void colorForeground(final Color foreground) {
        this.whoListPanel.colorForeground(foreground);
        this.chanListPanel.colorForeground(foreground);
        this.jtabbedPane.setForeground(foreground);
    }
    
    public void enabled(final boolean connected) {
        this.whoListPanel.enabled(connected);
        this.chanListPanel.enabled(connected);
        if (this.friendwin != null && this.friendwin instanceof FriendWindow) {
            ((FriendWindow)this.friendwin).setConnected(connected);
        }
    }
    
    public String getCurrentpanel() {
        return this.currentpanel;
    }
    
    private void jbInit() throws Exception {
        this.setLayout(new GridLayout(1, 1));
        (this.jtabbedPane = new JTabbedPane(3)).setUI(new MyJtabbedUI());
        this.add(this.jtabbedPane);
        this.setToolTipText("");
        this.setOpaque(false);
        this.whoListPanel = new WhoListPanel(this.eirc);
        this.chanListPanel = new ChanListPanel(this.eirc);
        this.friendwin = new NovipPanel(this.eirc);
        this.jtabbedPane.addTab("Mes amis  ", RightPanel.amis_arrow_right, this.friendwin);
        this.jtabbedPane.addTab("Rechercher", RightPanel.find_arrow_right, this.whoListPanel);
        this.jtabbedPane.addTab("Salons    ", RightPanel.salon_arrow_right, this.chanListPanel);
        this.jtabbedPane.addChangeListener(this);
        this.jtabbedPane.setSelectedIndex(1);
    }
    
    public void refreshFriendList() {
        if (this.eirc.isRegister()) {
            if (this.friendwin instanceof FriendWindow) {
                ((FriendWindow)this.friendwin).refresh();
            }
            else {
                this.friendwin = new FriendWindow(this.eirc);
            }
        }
        else {
            this.friendwin = new NovipPanel(this.eirc);
        }
        this.jtabbedPane.setComponentAt(0, this.friendwin);
    }
    
    public void setConnected(final boolean b) {
        this.whoListPanel.setConnected(b);
        this.chanListPanel.setConnected(b);
    }
    
    public void setCurrentpanel(final String currentpanel) {
        this.currentpanel = currentpanel;
    }
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.jtabbedPane.getSelectedIndex() == 0) {
            this.jtabbedPane.setIconAt(0, RightPanel.amis_arrow_down);
            this.jtabbedPane.setIconAt(1, RightPanel.find_arrow_right);
            this.jtabbedPane.setIconAt(2, RightPanel.salon_arrow_right);
        }
        else if (this.jtabbedPane.getSelectedIndex() == 1) {
            this.jtabbedPane.setIconAt(0, RightPanel.amis_arrow_right);
            this.jtabbedPane.setIconAt(1, RightPanel.find_arrow_down);
            this.jtabbedPane.setIconAt(2, RightPanel.salon_arrow_right);
        }
        else if (this.jtabbedPane.getSelectedIndex() == 2) {
            this.jtabbedPane.setIconAt(0, RightPanel.amis_arrow_right);
            this.jtabbedPane.setIconAt(1, RightPanel.find_arrow_right);
            this.jtabbedPane.setIconAt(2, RightPanel.salon_arrow_down);
        }
    }
    
    static {
        amis_arrow_down = new ImageIcon(Resources.getImages("amis_arrow_down"));
        amis_arrow_right = new ImageIcon(Resources.getImages("amis_arrow_right"));
        salon_arrow_down = new ImageIcon(Resources.getImages("salon_arrow_down"));
        salon_arrow_right = new ImageIcon(Resources.getImages("salon_arrow_right"));
        find_arrow_down = new ImageIcon(Resources.getImages("find_arrow_down"));
        find_arrow_right = new ImageIcon(Resources.getImages("find_arrow_right"));
    }
    
    class TopButtonPanel extends JToolBar
    {
        GridLayout gridLayout1;
        
        public TopButtonPanel() {
            this.setOrientation(1);
            this.setFloatable(false);
            this.setOpaque(false);
            this.setLayout(this.gridLayout1 = new GridLayout());
        }
        
        public void setRows(final int rows) {
            this.gridLayout1.setRows(rows);
        }
    }
    
    class BottomButtonPanel extends JToolBar
    {
        GridLayout gridLayout1;
        
        public BottomButtonPanel() {
            this.setOrientation(1);
            this.setFloatable(false);
            this.gridLayout1 = new GridLayout();
            this.setOpaque(false);
            this.setLayout(this.gridLayout1);
        }
        
        public void setRows(final int rows) {
            this.gridLayout1.setRows(rows);
        }
    }
}
