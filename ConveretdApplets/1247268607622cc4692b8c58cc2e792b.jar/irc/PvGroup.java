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
import irc.com.nick.NickInfos;
import irc.channels.PrivateWindow;
import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import javax.swing.plaf.TabbedPaneUI;
import irc.channels.components.MyJtabbedUI;
import java.awt.Color;
import irc.managers.Resources;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import irc.channels.components.MyPvPanel;
import javax.swing.JTabbedPane;
import java.awt.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.browser.Browser;
import java.awt.event.WindowStateListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;

public class PvGroup extends JFrame implements ChangeListener, WindowListener, MouseMotionListener, MouseListener, WindowStateListener
{
    Browser browser;
    boolean initbrow;
    Display display;
    Canvas canvas;
    Browser browserbas;
    boolean initbrowbas;
    Display displaybas;
    Canvas canvasbas;
    private EIRC eirc;
    private JTabbedPane tabgroup;
    public boolean flote;
    public MyPvPanel myjmainpanel;
    public Point loct;
    GridBagConstraints c;
    int i;
    Dimension dim;
    
    public PvGroup(final EIRC eirc) {
        this.browser = null;
        this.initbrow = false;
        this.browserbas = null;
        this.initbrowbas = false;
        this.flote = false;
        this.myjmainpanel = new MyPvPanel();
        this.loct = null;
        this.i = 0;
        this.dim = null;
        this.setIconImage(Resources.getImages("minlogopf"));
        this.setDefaultCloseOperation(0);
        this.setTitle("Les Discussions Priv\u00e9es");
        (this.tabgroup = new JTabbedPane(1)).setForeground(Color.GRAY);
        this.tabgroup.setUI(new MyJtabbedUI());
        this.eirc = eirc;
        this.setMaximumSize(new Dimension(1500, 1500));
        this.setContentPane(this.myjmainpanel);
        this.getContentPane().setLayout(new GridBagLayout());
        this.canvas = new Canvas();
        this.canvasbas = new Canvas();
        this.c = new GridBagConstraints();
        this.c.insets = new Insets(5, 5, 5, 5);
        this.c.fill = 10;
        this.c.anchor = 10;
        this.c.gridx = 0;
        this.c.gridy = 0;
        this.c.gridwidth = 0;
        this.getContentPane().add(this.canvas, this.c);
        this.c.insets = new Insets(15, 5, 5, 5);
        this.c.gridx = 0;
        this.c.gridy = 1;
        this.c.gridwidth = 4;
        this.c.gridheight = 4;
        this.c.weightx = 4.0;
        this.c.weighty = 4.0;
        this.c.fill = 1;
        this.c.anchor = 10;
        this.getContentPane().add(this.tabgroup, this.c);
        this.c.insets = new Insets(0, 0, 0, 0);
        this.c.gridx = 0;
        this.c.gridy = 5;
        this.c.gridwidth = 4;
        this.c.gridheight = 0;
        this.c.weightx = 0.0;
        this.c.weighty = 0.0;
        this.c.fill = 10;
        this.c.anchor = 15;
        this.getContentPane().add(this.canvasbas, this.c);
        this.canvasbas.setMinimumSize(new Dimension(728, 20));
        this.canvasbas.setPreferredSize(new Dimension(728, 20));
        this.canvasbas.setSize(new Dimension(728, 20));
        this.tabgroup.addChangeListener(this);
        this.tabgroup.addMouseListener(this);
        this.addWindowListener(this);
        this.setMinimumSize(new Dimension(600, 570));
        if (EIRC.resolutiony > 650) {
            this.setSize(700, 700);
        }
        else {
            this.setSize(700, 400);
        }
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        if (eirc.getXprivateswindows() > 0) {
            if (maximumWindowBounds.width == eirc.getXprivateswindows() && maximumWindowBounds.height == eirc.getYprivateswindows()) {
                this.loct = this.getLocation();
                this.dim = this.getSize();
                if (maximumWindowBounds.width > 0 && maximumWindowBounds.height > 0) {
                    this.setSize(maximumWindowBounds.width, maximumWindowBounds.height);
                }
                this.setLocation(0, 0);
                this.myjmainpanel.repaint();
                eirc.setXprivateswindows((int)this.getSize().getWidth());
                eirc.setYprivateswindows((int)this.getSize().getHeight());
            }
            else if (eirc.getXprivateswindows() > 0 && eirc.getYprivateswindows() > 0) {
                this.setSize(eirc.getXprivateswindows(), eirc.getYprivateswindows());
            }
        }
        this.getContentPane().addMouseListener(this);
        this.addWindowStateListener(this);
        this.getContentPane().addMouseMotionListener(this);
    }
    
    public void action() {
        this.eirc.setIsgrouppv(true);
        final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
        while (elements.hasMoreElements()) {
            final PrivateWindow privateWindow = elements.nextElement();
            String s;
            if (privateWindow.getUser().equalsIgnoreCase("news")) {
                s = "news";
            }
            else if (privateWindow.getUser().equalsIgnoreCase("horoscope")) {
                s = "horoscope";
            }
            else if (NickInfos.getSex(privateWindow.getUser().toLowerCase()) == 1) {
                s = "minlogoph";
            }
            else {
                s = "minlogopf";
            }
            this.tabgroup.addTab(privateWindow.getUser().toLowerCase(), new ImageIcon(Resources.getImages(s)), privateWindow.getGlobal());
            privateWindow.groupersetIcon("icondegrouperpv");
            privateWindow.groupertool("Degrouper toutes les discussions priv\u00e9es ");
            privateWindow.setVisible(false);
        }
        if (EIRC.resolutiony > 650) {
            this.setSize(700, 700);
        }
        else {
            this.setSize(700, 400);
        }
        if (this.eirc.getXprivateswindows() > 0 && this.eirc.getYprivateswindows() > 0) {
            this.setSize(this.eirc.getXprivateswindows(), this.eirc.getYprivateswindows());
        }
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        this.repaint();
        this.setVisible(true);
        if (this.tabgroup.indexOfTab(this.eirc.getChat_panel().getCurrentname()) != -1) {
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(this.eirc.getChat_panel().getCurrentname()));
        }
        if (!this.initbrow) {
            this.initbrow = true;
            new GetPub();
            new GetPubbas();
        }
    }
    
    public void addpv(final PrivateWindow privateWindow) {
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        String s;
        if (NickInfos.getSex(privateWindow.getUser().toLowerCase()) == 1) {
            s = "minlogoph";
        }
        else {
            s = "minlogopf";
        }
        this.tabgroup.addTab(privateWindow.getUser().toLowerCase(), new ImageIcon(Resources.getImages(s)), privateWindow.getGlobal());
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        this.repaint();
        privateWindow.setVisible(false);
        if (!this.isVisible()) {
            this.setState(1);
            this.setVisible(true);
            if (!this.initbrow) {
                this.initbrow = true;
                new GetPub();
                new GetPubbas();
            }
        }
    }
    
    public void closepv(final String s) {
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        if (this.tabgroup.indexOfTab(s.toLowerCase()) != -1) {
            this.tabgroup.remove(this.tabgroup.indexOfTab(s.toLowerCase()));
        }
    }
    
    public void colorForeground(final Color foreground) {
        this.tabgroup.setForeground(foreground);
    }
    
    public void degroup() {
        this.eirc.setIsgrouppv(false);
        this.setVisible(false);
        final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
        while (elements.hasMoreElements()) {
            try {
                final PrivateWindow privateWindow = elements.nextElement();
                if (this.tabgroup.indexOfTab(privateWindow.getUser()) == -1) {
                    continue;
                }
                privateWindow.setchannelpane((Container)this.tabgroup.getComponentAt(this.tabgroup.indexOfTab(privateWindow.getUser().toLowerCase())));
                privateWindow.groupersetIcon("iconregrouperpv");
                privateWindow.groupertool("Regrouper toutes les discussions priv\u00e9es dans une seule fen\u00eatre ");
                privateWindow.refreshpvsize();
                privateWindow.setVisible(true);
                privateWindow.validate();
            }
            catch (Exception ex) {}
        }
        this.tabgroup.removeAll();
        this.setVisible(false);
    }
    
    public void flash() {
        new flash();
    }
    
    public int gettail() {
        return this.tabgroup.getTabCount();
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        if (mouseEvent.getSource().equals(this.tabgroup)) {
            this.tabgroup.setForegroundAt(this.tabgroup.getSelectedIndex(), Color.black);
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.eirc.revenir();
        this.eirc.getPrivates().getPrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).EntryrequestFocus();
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
        this.eirc.revenir();
        this.eirc.getPrivates().getPrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).EntryrequestFocus();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.eirc.revenir();
    }
    
    public void Notifypv(final String s) {
        if (this.tabgroup.getSelectedIndex() != this.tabgroup.indexOfTab(s.toLowerCase()) && this.tabgroup.indexOfTab(s.toLowerCase()) != -1) {
            this.tabgroup.setForegroundAt(this.tabgroup.indexOfTab(s.toLowerCase()), Color.RED);
        }
        if (this.isActive() && this.tabgroup.indexOfTab(s.toLowerCase()) == this.tabgroup.getSelectedIndex()) {
            this.tabgroup.setForegroundAt(this.tabgroup.indexOfTab(s.toLowerCase()), Color.black);
        }
        this.flash();
    }
    
    public void rename(final String s, final String s2) {
        this.tabgroup.setTitleAt(this.tabgroup.indexOfTab(s.toLowerCase()), s2.toLowerCase());
    }
    
    public void Showpv(final String s) {
        if (this.tabgroup.indexOfTab(s.toLowerCase()) != -1) {
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(s.toLowerCase()));
            this.setState(0);
            this.toFront();
        }
        else {
            String s2;
            if (NickInfos.getSex(s.toLowerCase()) == 1) {
                s2 = "minlogoph";
            }
            else {
                s2 = "minlogopf";
            }
            this.tabgroup.addTab(s.toLowerCase(), new ImageIcon(Resources.getImages(s2)), this.eirc.getPrivates().getPrivate(s.toLowerCase()).getGlobal());
            this.eirc.getPrivates().getPrivate(s.toLowerCase()).setVisible(false);
            this.tabgroup.setSelectedIndex(this.tabgroup.indexOfTab(s.toLowerCase()));
        }
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        this.repaint();
        this.toFront();
    }
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.tabgroup.getTabCount() > 1) {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussions");
        }
        else {
            this.setTitle("Les Discussions Priv\u00e9es : " + this.tabgroup.getTabCount() + " Discussion");
        }
        if (this.tabgroup.getSelectedIndex() != -1) {
            for (int i = 0; i < this.tabgroup.getTabCount(); ++i) {
                if (!this.tabgroup.getForegroundAt(i).equals(Color.RED)) {
                    this.tabgroup.setForegroundAt(i, Color.GRAY);
                }
            }
            this.tabgroup.setForegroundAt(this.tabgroup.getSelectedIndex(), Color.black);
            if (this.eirc.getPrivates().getPrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()) != null) {
                this.eirc.getPrivates().getPrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).current();
            }
            else {
                this.tabgroup.remove(this.tabgroup.getSelectedIndex());
            }
        }
        if (this.tabgroup.getTabCount() == 0) {
            this.setVisible(false);
        }
        else {
            this.setVisible(true);
            if (!this.initbrow) {
                this.initbrow = true;
                new GetPub();
                new GetPubbas();
            }
        }
    }
    
    @Override
    public void windowActivated(final WindowEvent windowEvent) {
        if (this.tabgroup.getSelectedIndex() != -1) {
            this.eirc.getPrivates().getPrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase()).current();
        }
    }
    
    @Override
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowClosing(final WindowEvent windowEvent) {
        if (this.tabgroup.getTabCount() == 1) {
            final Enumeration<PrivateWindow> elements = this.eirc.getPrivates().privates.elements();
            while (elements.hasMoreElements()) {
                this.eirc.getPrivates().ClosePrivate(elements.nextElement().getUser());
            }
            this.setVisible(false);
            return;
        }
        final Object[] array = { "Toutes les discussions", "Discussion en cours " };
        final int showOptionDialog = JOptionPane.showOptionDialog(this, "Voulez-vous vraiment fermer toutes vos discussions ou la discussion en cours", "Chat-Land.org", 0, 1, null, array, array[1]);
        if (showOptionDialog == -1) {
            this.setVisible(true);
            if (!this.initbrow) {
                this.initbrow = true;
                new GetPub();
                new GetPubbas();
            }
            return;
        }
        if (showOptionDialog == 0) {
            final Enumeration<PrivateWindow> elements2 = this.eirc.getPrivates().privates.elements();
            while (elements2.hasMoreElements()) {
                this.eirc.getPrivates().ClosePrivate(elements2.nextElement().getUser());
            }
            this.setVisible(false);
        }
        else if (this.tabgroup.getSelectedIndex() != -1) {
            this.eirc.getPrivates().ClosePrivate(this.tabgroup.getTitleAt(this.tabgroup.getSelectedIndex()).toLowerCase());
        }
    }
    
    @Override
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    @Override
    public void windowStateChanged(final WindowEvent windowEvent) {
    }
    
    public void grouping(final String s) {
        new typing(s);
    }
    
    class typing extends Thread
    {
        String name;
        
        public typing(final String name) {
            this.name = name;
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            if (PvGroup.this.tabgroup.indexOfTab(this.name.toLowerCase()) != -1) {
                PvGroup.this.tabgroup.setIconAt(PvGroup.this.tabgroup.indexOfTab(this.name.toLowerCase()), new ImageIcon(Resources.getImages("writing")));
            }
            try {
                Thread.sleep(7000L);
            }
            catch (InterruptedException ex) {}
            String s;
            if (NickInfos.getSex(this.name.toLowerCase()) == 1) {
                s = "minlogoph";
            }
            else {
                s = "minlogopf";
            }
            if (PvGroup.this.tabgroup.indexOfTab(this.name.toLowerCase()) != -1) {
                PvGroup.this.tabgroup.setIconAt(PvGroup.this.tabgroup.indexOfTab(this.name.toLowerCase()), new ImageIcon(Resources.getImages(s)));
            }
        }
    }
    
    class GetPubbas extends Thread
    {
        public GetPubbas() {
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            PvGroup.this.displaybas = new Display();
            final Shell new_Shell = SWT_AWT.new_Shell(PvGroup.this.displaybas, PvGroup.this.canvasbas);
            new_Shell.setLayout(new FormLayout());
            (PvGroup.this.browserbas = new Browser(new_Shell, 0)).setLayoutData(new GridData(1808));
            final FormData layoutData = new FormData();
            layoutData.top = new FormAttachment(0, 0);
            layoutData.bottom = new FormAttachment(100, 0);
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            PvGroup.this.browserbas.setLayoutData(layoutData);
            PvGroup.this.canvasbas.setVisible(true);
            PvGroup.this.browserbas.setUrl("http://" + main.http + ".chat-land.org/modules/messenger/pubjspv.php?a=" + PvGroup.this.eirc.getUsername());
            PvGroup.this.canvasbas.setMinimumSize(new Dimension(728, 20));
            PvGroup.this.canvasbas.setPreferredSize(new Dimension(728, 20));
            PvGroup.this.canvasbas.setMaximumSize(new Dimension(728, 20));
            PvGroup.this.canvasbas.setSize(new Dimension(728, 20));
            new_Shell.setSize(728, 90);
            new_Shell.open();
            for (int i = 0; i < PvGroup.this.getComponentCount(); ++i) {
                PvGroup.this.getComponent(i).validate();
            }
            while (!new_Shell.isDisposed()) {
                if (!PvGroup.this.displaybas.readAndDispatch()) {
                    PvGroup.this.displaybas.sleep();
                }
            }
            PvGroup.this.displaybas.dispose();
        }
    }
    
    class GetPub extends Thread
    {
        public GetPub() {
            this.start();
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            PvGroup.this.display = new Display();
            final Shell new_Shell = SWT_AWT.new_Shell(PvGroup.this.display, PvGroup.this.canvas);
            new_Shell.setLayout(new FormLayout());
            (PvGroup.this.browser = new Browser(new_Shell, 0)).setLayoutData(new GridData(1808));
            final FormData layoutData = new FormData();
            layoutData.top = new FormAttachment(0, 0);
            layoutData.bottom = new FormAttachment(100, 0);
            layoutData.left = new FormAttachment(0, 0);
            layoutData.right = new FormAttachment(100, 0);
            PvGroup.this.browser.setLayoutData(layoutData);
            PvGroup.this.canvas.setVisible(true);
            PvGroup.this.browser.setUrl("http://" + main.http + ".chat-land.org/modules/messenger/pubjssalon.php?a=" + PvGroup.this.eirc.getUsername());
            PvGroup.this.canvas.setMinimumSize(new Dimension(728, 90));
            PvGroup.this.canvas.setPreferredSize(new Dimension(728, 90));
            PvGroup.this.canvas.setMaximumSize(new Dimension(728, 90));
            PvGroup.this.canvas.setSize(new Dimension(728, 90));
            new_Shell.setSize(728, 90);
            new_Shell.open();
            for (int i = 0; i < PvGroup.this.getComponentCount(); ++i) {
                PvGroup.this.getComponent(i).validate();
            }
            while (!new_Shell.isDisposed()) {
                if (!PvGroup.this.display.readAndDispatch()) {
                    PvGroup.this.display.sleep();
                }
            }
            PvGroup.this.display.dispose();
        }
    }
    
    class flash extends Thread
    {
        public flash() {
            this.setPriority(1);
            this.start();
        }
        
        @Override
        public void run() {
            if (!NativeLoader.isload()) {
                return;
            }
            try {
                final IntCall intCall = new IntCall("user32", "FindWindowA");
                final int executeCall = intCall.executeCall(new Object[] { null, PvGroup.this.getTitle() });
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
