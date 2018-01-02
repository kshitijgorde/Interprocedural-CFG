// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import com.eaio.nativecall.IntCall;
import com.eaio.util.lang.NativeLoader;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.awt.SWT_AWT;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import irc.channels.ChannelWindow;
import irc.managers.CHANNELS;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import javax.swing.plaf.TabbedPaneUI;
import irc.channels.components.MyJtabbedUI;
import irc.managers.Resources;
import java.awt.GridBagConstraints;
import irc.channels.components.MyPvPanel;
import java.awt.Canvas;
import javax.swing.JTabbedPane;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.browser.Browser;
import java.awt.event.WindowStateListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class ChannelGroup extends JFrame implements ChangeListener, WindowListener, MouseMotionListener, MouseListener, WindowStateListener
{
    Browser browser1;
    boolean initbrow;
    Display display;
    private EIRC eirc;
    private JTabbedPane tabgroup;
    private Canvas canvas;
    private String urlpub;
    private MyPvPanel myjmainpanel;
    private GridBagConstraints c;
    int i;
    String salonname;
    
    public ChannelGroup(final EIRC eirc) {
        this.browser1 = null;
        this.initbrow = false;
        this.urlpub = "www.chat-land.org";
        this.myjmainpanel = new MyPvPanel();
        this.i = 0;
        this.salonname = "";
        this.setIconImage(Resources.getImages("minlogosalon"));
        this.setDefaultCloseOperation(0);
        this.setTitle("Les Salons");
        (this.tabgroup = new JTabbedPane(1)).setUI(new MyJtabbedUI());
        this.eirc = eirc;
        this.tabgroup.setForeground(Color.white);
        this.canvas = new Canvas();
        this.tabgroup.setFont(new Font("Times New Roman", 1, 16));
        this.setContentPane(this.myjmainpanel);
        this.myjmainpanel.setLayout(new GridBagLayout());
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add(this.canvas, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 10, 10, new Insets(0, 0, 0, 0), 0, 0));
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setOpaque(false);
        this.c = new GridBagConstraints();
        this.c.insets = new Insets(10, 5, 5, 5);
        this.c.fill = 2;
        this.c.anchor = 10;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 4;
        this.myjmainpanel.add(panel, this.c);
        this.c.insets = new Insets(5, 5, 5, 5);
        this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.gridwidth = 4;
        this.c.gridheight = 4;
        this.c.weightx = 4.0;
        this.c.weighty = 4.0;
        this.c.fill = 1;
        this.c.anchor = 10;
        this.myjmainpanel.add(this.tabgroup, this.c);
        this.tabgroup.addChangeListener(this);
        this.tabgroup.addMouseListener(this);
        this.setMinimumSize(new Dimension(500, 400));
        if (EIRC.resolutiony > 650) {
            this.setSize(700, 700);
        }
        else {
            this.setSize(700, 400);
        }
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        if (eirc.getXchannelswindows() > 0) {
            if (maximumWindowBounds.width == eirc.getXchannelswindows() && maximumWindowBounds.height == eirc.getYchannelwindows()) {
                if (maximumWindowBounds.width > 0 && maximumWindowBounds.height > 0) {
                    this.setSize(maximumWindowBounds.width, maximumWindowBounds.height);
                }
                this.setLocation(0, 0);
                this.myjmainpanel.repaint();
                eirc.setXchannelswindows((int)this.getSize().getWidth());
                eirc.setYchannelswindows((int)this.getSize().getHeight());
            }
            else {
                this.setLocation(maximumWindowBounds.width / 10, maximumWindowBounds.height / 10);
                if (eirc.getXchannelswindows() > 0 && eirc.getYchannelwindows() > 0) {
                    this.setSize(eirc.getXchannelswindows(), eirc.getYchannelwindows());
                }
            }
        }
        else {
            final Dimension size = this.getSize();
            eirc.setXchannelswindows(size.width);
            eirc.setYchannelswindows(size.height);
        }
        this.myjmainpanel.addMouseListener(this);
        this.myjmainpanel.addMouseMotionListener(this);
        this.tabgroup.addMouseMotionListener(this);
        this.addWindowListener(this);
        this.addWindowStateListener(this);
    }
    
    public void action() {
        this.setVisible1(true);
        this.eirc.setIsgroupchannel(true);
        synchronized (CHANNELS.channels) {
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                try {
                    final ChannelWindow channelWindow = elements.nextElement();
                    this.tabgroup.addTab(channelWindow.getTag().toLowerCase(), new ImageIcon(Resources.getImages("minlogosalon")), channelWindow.getchannelpane());
                    channelWindow.setIconBgrouper("icondegrouper");
                    channelWindow.settoolBgrouper("Degrouper tous les salons");
                    channelWindow.setVisible(false);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (EIRC.resolutiony > 650) {
            this.setSize(700, 700);
        }
        else {
            this.setSize(700, 400);
        }
        if (this.tabgroup.indexOfTab(this.eirc.getChat_panel().getCurrentname()) != -1) {
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(this.eirc.getChat_panel().getCurrentname()));
        }
    }
    
    public void closeall() {
        this.tabgroup.removeAll();
    }
    
    public void closechannel(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (this.tabgroup.indexOfTab(lowerCase) != -1) {
            this.tabgroup.remove(this.tabgroup.indexOfTab(lowerCase));
        }
    }
    
    public void colorForeground(final Color foreground) {
        this.tabgroup.setForeground(foreground);
    }
    
    public void degroup() {
        this.eirc.setIsgroupchannel(false);
        synchronized (CHANNELS.channels) {
            final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
            while (elements.hasMoreElements()) {
                try {
                    final ChannelWindow channelWindow = elements.nextElement();
                    channelWindow.setchannelpane((Container)this.tabgroup.getComponentAt(this.tabgroup.indexOfTab(channelWindow.getTag().toLowerCase())));
                    channelWindow.setIconBgrouper("iconregrouper");
                    channelWindow.settoolBgrouper("Regrouper tous les salons dans une seule fen\u00eatre ");
                    channelWindow.setVisible(true);
                    channelWindow.setTitle();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.setVisible1(false);
    }
    
    public void flash() {
        new flash();
    }
    
    public JTabbedPane getTabgroup() {
        return this.tabgroup;
    }
    
    public int gettail() {
        return this.tabgroup.getTabCount();
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.tabgroup)) {
            this.tabgroup.setForegroundAt(this.tabgroup.getSelectedIndex(), Color.black);
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).entryrequestFocus();
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).entryrequestFocus();
        this.eirc.revenir();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void Notifychannel(final String s) {
        this.tabgroup.setForegroundAt(this.tabgroup.indexOfTab(s.toLowerCase()), Color.RED);
        if (this.isActive() && this.tabgroup.indexOfTab(s.toLowerCase()) == this.tabgroup.getSelectedIndex()) {
            this.tabgroup.setForegroundAt(this.tabgroup.indexOfTab(s.toLowerCase()), Color.black);
        }
    }
    
    public void setVisible1(final boolean visible) {
        this.setVisible(visible);
        if (!this.initbrow) {
            this.initbrow = true;
            new GetPub();
        }
    }
    
    public void Showchannel(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (this.tabgroup.indexOfTab(lowerCase) != -1) {
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(lowerCase));
            this.setState(0);
            this.toFront();
        }
        else {
            this.tabgroup.addTab(CHANNELS.getChannelWindow(lowerCase).getTag(), new ImageIcon(Resources.getImages("minlogosalon")), CHANNELS.getChannelWindow(lowerCase).getchannelpane());
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(lowerCase));
            this.setState(0);
            this.toFront();
            this.setVisible(true);
            if (!this.initbrow) {
                this.initbrow = true;
                new GetPub();
            }
        }
    }
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        if (this.tabgroup.getSelectedIndex() != -1) {
            for (int i = 0; i < this.tabgroup.getTabCount(); ++i) {
                if (!this.tabgroup.getForegroundAt(i).equals(Color.RED)) {
                    this.tabgroup.setForegroundAt(i, Color.GRAY);
                }
            }
            this.tabgroup.setForegroundAt(this.tabgroup.getSelectedIndex(), Color.black);
            CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).entryrequestFocus();
            CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).current();
            this.tabgroup.setIconAt(this.tabgroup.getSelectedIndex(), new ImageIcon(Resources.getImages("minlogosalon")));
        }
        else if (this.tabgroup.getTabCount() > 0) {
            this.tabgroup.setSelectedIndex(0);
        }
        if (this.tabgroup.getTabCount() == 0) {
            this.setVisible1(false);
        }
        else {
            this.setVisible1(true);
            if (!this.initbrow) {
                this.initbrow = true;
                new GetPub();
            }
        }
        if (this.tabgroup.getSelectedIndex() >= 0) {
            this.salonname = this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex());
        }
        if (this.browser1 != null) {
            this.display.asyncExec(new Runnable() {
                @Override
                public void run() {
                    ChannelGroup.this.browser1.setUrl("http://" + main.http + ".chat-land.org/modules/messenger/pubjssalon.php?a=" + ChannelGroup.this.eirc.getUsername() + "&b=" + ChannelGroup.this.salonname + "&c=1");
                }
            });
        }
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
        if (this.tabgroup.getSelectedIndex() != -1 && CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()) != null) {
            CHANNELS.getChannelWindow(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).current();
        }
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.tabgroup.getTabCount() == 1) {
            this.setVisible1(false);
            synchronized (CHANNELS.channels) {
                final Enumeration<ChannelWindow> elements = CHANNELS.channels.elements();
                while (elements.hasMoreElements()) {
                    CHANNELS.CloseChannel(elements.nextElement().getTag().toLowerCase());
                }
            }
            return;
        }
        final Object[] array = { "Tous les salons", "  Le salon en cours " };
        final int showOptionDialog = JOptionPane.showOptionDialog(this, "Voulez-vous vraiment fermer tous vos salons ou le salon en cours ", "Chat-Land.org", 0, 1, null, array, array[1]);
        if (showOptionDialog == -1) {
            this.setVisible1(true);
            return;
        }
        if (showOptionDialog == 0) {
            this.setVisible1(false);
            synchronized (CHANNELS.channels) {
                final Enumeration<ChannelWindow> elements2 = CHANNELS.channels.elements();
                while (elements2.hasMoreElements()) {
                    CHANNELS.CloseChannel(elements2.nextElement().getTag().toLowerCase());
                }
            }
        }
        else if (this.tabgroup.getSelectedIndex() != -1) {
            CHANNELS.CloseChannel(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase());
        }
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void windowStateChanged(final WindowEvent windowEvent) {
    }
    
    class GetPub extends Thread
    {
        public GetPub() {
            this.start();
        }
        
        @Override
        public void run() {
            ChannelGroup.this.display = new Display();
            final Shell new_Shell = SWT_AWT.new_Shell(ChannelGroup.this.display, ChannelGroup.this.canvas);
            new_Shell.setLayout(new FormLayout());
            (ChannelGroup.this.browser1 = new Browser(new_Shell, 0)).setLayoutData(new GridData(1808));
            final FormData layoutData = new FormData();
            layoutData.top = new FormAttachment(0, 0);
            layoutData.bottom = new FormAttachment(100, 0);
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            ChannelGroup.this.browser1.setLayoutData(layoutData);
            ChannelGroup.this.canvas.setVisible(true);
            ChannelGroup.this.browser1.setUrl("http://" + main.http + ".chat-land.org/modules/messenger/pubjssalon.php?a=" + ChannelGroup.this.eirc.getUsername() + "&b=" + ChannelGroup.this.salonname + "&c=1");
            ChannelGroup.this.canvas.setMinimumSize(new Dimension(728, 90));
            ChannelGroup.this.canvas.setPreferredSize(new Dimension(728, 90));
            ChannelGroup.this.canvas.setSize(new Dimension(728, 90));
            new_Shell.setSize(728, 90);
            new_Shell.open();
            for (int i = 0; i < ChannelGroup.this.getComponentCount(); ++i) {
                ChannelGroup.this.getComponent(i).validate();
            }
            while (!new_Shell.isDisposed()) {
                if (!ChannelGroup.this.display.readAndDispatch()) {
                    ChannelGroup.this.display.sleep();
                }
            }
            ChannelGroup.this.display.dispose();
        }
    }
    
    class flash extends Thread
    {
        public flash() {
            this.start();
        }
        
        @Override
        public void run() {
            if (!NativeLoader.isload()) {
                return;
            }
            try {
                final IntCall intCall = new IntCall("user32", "FindWindowA");
                final int executeCall = intCall.executeCall(new Object[] { null, ChannelGroup.this.getTitle() });
                intCall.destroy();
                final IntCall intCall2 = new IntCall("user32", "FlashWindow");
                intCall2.executeCall(new Object[] { new Integer(executeCall), new Integer(3) });
                intCall2.destroy();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
